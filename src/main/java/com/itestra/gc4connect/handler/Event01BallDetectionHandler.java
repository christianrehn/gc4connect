package com.itestra.gc4connect.handler;

import com.itestra.gc4connect.message.GC4Message;
import org.apache.commons.lang3.Validate;

public class Event01BallDetectionHandler extends AbstractMessageHandler {

    public static final int MESSAGE_LENGTH_BYTES = 51;
    public static final String OPERATION_000 = "01";
    public static final String DIRECTION_001 = "00";
    public static final String UNKNOWN_002_TO_005 = "2d" + "00" + "00" + "00";
    public static final String UNKNOWN_050 = "00";

    @Override
    public String getDefaultHexMessageString() {
        return null;
    }

    public void handleHexMessageString(String hexMessageString) {
        Validate.notEmpty(hexMessageString);
        Validate.isTrue(hexMessageString.length() == 2 * MESSAGE_LENGTH_BYTES, "" + hexMessageString.length());

        super.handleHexMessageString(hexMessageString);

        validateHexStringBytes(0, OPERATION_000, hexMessageString);
        validateHexStringBytes(1, DIRECTION_001, hexMessageString);
        validateHexStringBytes(2, UNKNOWN_002_TO_005, hexMessageString);
        validateHexStringBytes(50, UNKNOWN_050, hexMessageString);

        // get ball message id (0-2)
        {
            String ballMessageIdAsHexString = getHexStringBytes(6, 2, hexMessageString);
            byte[] ballMessageIdAsBytes = GC4Message.hexStringToByteArray(ballMessageIdAsHexString);
            short ballMessageId = GC4Message.hBytesToShort(GC4Message.bytesReverseOrder(ballMessageIdAsBytes));
            System.out.println(String.format("ball message id=%d", ballMessageId));
        }

        // get ball ready flag
        {
            String ballReadyAsHexString = getHexStringBytes(8, 1, hexMessageString);
            byte[] ballReadyAsBytes = GC4Message.hexStringToByteArray(ballReadyAsHexString);
            boolean ballReady = ballReadyAsBytes[0] != 0;
            System.out.println(String.format("ball ready=%s", ballReady));
        }

        // get number of balls detected
        String numberOfBallsAsHexString = getHexStringBytes(9, 1, hexMessageString);
        byte numberOfBalls = GC4Message.hexStringToByteArray(numberOfBallsAsHexString)[0];
        System.out.println(String.format("%d ball(s) detected", numberOfBalls));

        // get ball positions
        for (int i = 0; i < numberOfBalls; i++) {
            String xBallPositionAsHexString = getHexStringBytes(10 + i * 8, 4, hexMessageString);
            int xBallPosition = GC4Message.hBytesToInt(GC4Message.bytesReverseOrder(GC4Message.hexStringToByteArray(xBallPositionAsHexString)));

            String yBallPositionAsHexString = getHexStringBytes(10 + i * 8 + 4, 4, hexMessageString);
            int yBallPosition = GC4Message.hBytesToInt(GC4Message.bytesReverseOrder(GC4Message.hexStringToByteArray(yBallPositionAsHexString)));
            System.out.println(String.format("ball %d position: x=%d, y=%d", i, xBallPosition, yBallPosition));
        }
    }

    private String getHexStringBytes(int position, int byteLen, String hexMessageString) {
        int beginIndex = position * 2;
        return hexMessageString.substring(beginIndex, beginIndex + byteLen * 2);
    }

    private String validateHexStringBytes(int position, String expected, String hexMessageString) {
        Validate.notEmpty(hexMessageString);
        Validate.isTrue(hexMessageString.length() >= position * 2 + expected.length());
        Validate.notEmpty(expected);

        int charLen = expected.length();
        Validate.isTrue(charLen % 2 == 0);
        String hexStringBytes = getHexStringBytes(position, charLen / 2, hexMessageString);
        if (!expected.equals(hexStringBytes)) {
            throw new RuntimeException(String.format("wrong byte at position %d, expected: %s, actual: %s", position, expected, hexStringBytes));
        }
        return hexStringBytes;
    }

}
