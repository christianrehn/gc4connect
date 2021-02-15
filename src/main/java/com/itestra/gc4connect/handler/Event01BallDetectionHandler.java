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

        // unknown short 1
        {
            String ballMessageIdAsHexString = hexSubString(6, 2, hexMessageString);
            byte[] ballMessageIdAsBytes = GC4Message.hexStringToByteArray(ballMessageIdAsHexString);
            gc4BallDetectionData.unknownShort1 = GC4Message.hBytesToShort(GC4Message.bytesReverseOrder(ballMessageIdAsBytes));
            System.out.println(String.format("position 6 to 7 (0x%s) ??? unknownShort1=%d", ballMessageIdAsHexString, gc4BallDetectionData.unknownShort1));
        }

        // unknown byte
        validateHexString(8, UNKNOWN_008, hexMessageString);

        // get number of balls detected
        {
            String numberOfBallsAsHexString = hexSubString(9, 1, hexMessageString);
            gc4BallDetectionData.numberOfBalls = GC4Message.hexStringToByteArray(numberOfBallsAsHexString)[0];
            System.out.println(String.format("position 9 to 9 (0x%s) numberOfBalls=%d", numberOfBallsAsHexString, gc4BallDetectionData.numberOfBalls));
        }

        // get ball positions
        for (int i = 0; i < GC4BallDetectionData.MAX_BALL_POSITIONB_DATAS; i++) {
            int bytePositionX = 10 + i * 8;
            String xBallPositionAsHexString = hexSubString(bytePositionX, 4, hexMessageString);
            gc4BallDetectionData.xBallPosition[i] = GC4Message.lBytesToInt(GC4Message.hexStringToByteArray(xBallPositionAsHexString));
            System.out.println(String.format("position %d to %d (0x%s) ball %d xBallPosition=%d", bytePositionX, bytePositionX + 3, xBallPositionAsHexString, i, gc4BallDetectionData.xBallPosition[i]));

            int bytePositionY = bytePositionX + 4;
            String yBallPositionAsHexString = hexSubString(bytePositionY, 4, hexMessageString);
            gc4BallDetectionData.yBallPosition[i] = GC4Message.lBytesToInt(GC4Message.hexStringToByteArray(yBallPositionAsHexString));
            System.out.println(String.format("position %d to %d (0x%s) ball %d yBallPosition=%d", bytePositionY, bytePositionY + 3, yBallPositionAsHexString, i, gc4BallDetectionData.yBallPosition[i]));
        }

        validateHexString(50, UNKNOWN_050, hexMessageString);

        return gc4BallDetectionData;
    }

}
