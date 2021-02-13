package com.itestra.gc4connect.handler;

import com.itestra.gc4connect.message.GC4Message;
import org.apache.commons.lang3.Validate;

/**
 * X inside: ~ 65 -
 * Y inside: ~ 22 - 215
 */
public class Event01BallDetectionHandler extends GC4MessageHandler {

    public static final int MESSAGE_LENGTH_BYTES = 51;
    public static final String OPERATION_000 = "01";
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

        validateHexString(0, OPERATION_000, hexMessageString);
        validateHexString(1, DIRECTION_001_GC4_EVENT, hexMessageString);
        validateHexString(2, UNKNOWN_002_TO_005, hexMessageString);
        validateHexString(50, UNKNOWN_050, hexMessageString);

        // get ball message id (0-2)
        {
            String ballMessageIdAsHexString = hexSubString(6, 2, hexMessageString);
            byte[] ballMessageIdAsBytes = GC4Message.hexStringToByteArray(ballMessageIdAsHexString);
            short ballMessageId = GC4Message.hBytesToShort(GC4Message.bytesReverseOrder(ballMessageIdAsBytes));
            System.out.println(String.format("ball message id=%d", ballMessageId));
        }

        // get ball ready flag
        {
            String ballReadyAsHexString = hexSubString(8, 1, hexMessageString);
            byte[] ballReadyAsBytes = GC4Message.hexStringToByteArray(ballReadyAsHexString);
            boolean ballReady = ballReadyAsBytes[0] != 0;
            System.out.println(String.format("ball ready=%s", ballReadyAsBytes[0]));
        }

        // get number of balls detected
        String numberOfBallsAsHexString = hexSubString(9, 1, hexMessageString);
        byte numberOfBalls = GC4Message.hexStringToByteArray(numberOfBallsAsHexString)[0];
        System.out.println(String.format("%d ball(s) detected", numberOfBalls));

        // get ball positions
        for (int i = 0; i < numberOfBalls; i++) {
            String xBallPositionAsHexString = hexSubString(10 + i * 8, 4, hexMessageString);
            int xBallPosition = GC4Message.lBytesToInt(GC4Message.hexStringToByteArray(xBallPositionAsHexString));

            String yBallPositionAsHexString = hexSubString(10 + i * 8 + 4, 4, hexMessageString);
            int yBallPosition = GC4Message.lBytesToInt(GC4Message.hexStringToByteArray(yBallPositionAsHexString));
            System.out.println(String.format("ball %d position: x=%d, y=%d", i, xBallPosition, yBallPosition));
        }
    }

}
