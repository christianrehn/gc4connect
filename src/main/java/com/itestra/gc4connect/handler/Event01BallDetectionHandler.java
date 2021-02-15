package com.itestra.gc4connect.handler;

import com.itestra.gc4connect.data.GC4BallDetectionData;
import com.itestra.gc4connect.message.GC4Message;
import org.apache.commons.lang3.Validate;

/**
 * X inside: ~ 65 -
 * Y inside: ~ 22 - 215
 */
public class Event01BallDetectionHandler extends GC4MessageHandler {

    public static final String UNKNOWN_002_TO_005 = "2d" + "00" + "00" + "00";
    public static final String UNKNOWN_008 = "01";
    public static final String UNKNOWN_050 = "00";

    @Override
    public String getDefaultHexMessageString() {
        return null;
    }

    public GC4BallDetectionData handleHexMessageString(String hexMessageString) {
        Validate.notEmpty(hexMessageString);
        Validate.isTrue(hexMessageString.length() == 2 * MESSAGE_LENGTH_BYTES_BALL_DETECTION, "" + hexMessageString.length());

        super.handleHexMessageString(hexMessageString);

        System.out.println(String.format("BALL DETECTION EVENT"));
        GC4BallDetectionData gc4BallDetectionData = new GC4BallDetectionData();

        validateHexString(0, OPERATION_BALL_DETECTION, hexMessageString);
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

        // unknown byte
        validateHexString(8, UNKNOWN_008, hexMessageString);

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

        return gc4BallDetectionData;
    }

}
