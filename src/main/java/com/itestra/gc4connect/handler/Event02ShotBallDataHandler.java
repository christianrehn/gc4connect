package com.itestra.gc4connect.handler;

import com.itestra.gc4connect.message.GC4Message;
import org.apache.commons.lang3.Validate;

/**
 * Ball Data
 */
public class Event02ShotBallDataHandler extends GC4MessageHandler {

    public static final String UNKNOWN_002_TO_005 = "2c000000";
    public static final String UNKNOWN_026_TO_029 = "CDBF5A45";
    public static final String UNKNOWN_030_TO_033 = "00000000";
    public static final String UNKNOWN_034_TO_037 = "01000000";

    @Override
    public String getDefaultHexMessageString() {
        return null;
    }

    public void handleHexMessageString(String hexMessageString) {
        Validate.notEmpty(hexMessageString);
        Validate.isTrue(hexMessageString.length() == 2 * MESSAGE_LENGTH_BYTES_EVENT02_SHOT1, "" + hexMessageString.length());

        super.handleHexMessageString(hexMessageString);

        System.out.println(String.format("SHOT BALL DATA EVENT"));

        validateHexString(0, OPERATION_EVENT02_SHOT1, hexMessageString);
        validateHexString(1, DIRECTION_001_GC4_EVENT, hexMessageString);

        // unknown int 1, always 0x2c000000
        validateHexString(2, UNKNOWN_002_TO_005, hexMessageString);

        // unkown int 2
        {
            String unknownAsHexString = hexSubString(6, 4, hexMessageString);
            int unknownInt = GC4Message.lBytesToInt(GC4Message.hexStringToByteArray(unknownAsHexString));
            System.out.println(String.format("position 6 to 9 (0x%s) ??? int value=%d", unknownAsHexString, unknownInt));
        }

        // shot number
        {
            String shotNumberAsHexString = hexSubString(10, 4, hexMessageString);
            int shotNumber = GC4Message.lBytesToInt(GC4Message.hexStringToByteArray(shotNumberAsHexString));
            System.out.println(String.format("position 10 to 13 (0x%s) int shotNumber=%d", shotNumberAsHexString, shotNumber));
        }

        // ball speed
        {
            String ballSpeedAsHexString = hexSubString(14, 4, hexMessageString);
            float ballSpeed = GC4Message.lBytesToFloat(GC4Message.hexStringToByteArray(ballSpeedAsHexString));
            System.out.println(String.format("position 14 to 17 (0x%s) ballSpeed=%f", ballSpeedAsHexString, ballSpeed));
        }

        // launch angle
        {
            String launchAngleAsHexString = hexSubString(18, 4, hexMessageString);
            float launchAngle = GC4Message.lBytesToFloat(GC4Message.hexStringToByteArray(launchAngleAsHexString));
            System.out.println(String.format("position 18 to 21 (0x%s) launchAngle=%f", launchAngleAsHexString, launchAngle));
        }

        // side angle
        {
            String sideAngleAsHexString = hexSubString(22, 4, hexMessageString);
            float sideAngle = GC4Message.lBytesToFloat(GC4Message.hexStringToByteArray(sideAngleAsHexString));
            System.out.println(String.format("position 22 to 25 (0x%s) sideAngle=%f", sideAngleAsHexString, sideAngle));
        }

        // unknown, always 0xCDBF5A45
        validateHexString(26, UNKNOWN_026_TO_029, hexMessageString);

        // unknown, always 0x00000000
        validateHexString(30, UNKNOWN_030_TO_033, hexMessageString);

        // unknown, always 0x01000000
        validateHexString(34, UNKNOWN_034_TO_037, hexMessageString);

        // unkown float 5
        {
            String unknownAsHexString = hexSubString(38, 4, hexMessageString);
            float unknownFloat = GC4Message.lBytesToFloat(GC4Message.hexStringToByteArray(unknownAsHexString));
            System.out.println(String.format("position 38 to 41 (0x%s) ??? float value=%f", unknownAsHexString, unknownFloat));

            int unknownInt = GC4Message.lBytesToInt(GC4Message.hexStringToByteArray(unknownAsHexString));
            System.out.println(String.format("position 38 to 41 (0x%s) ??? int value=%d", unknownAsHexString, unknownInt));
        }
        // unkown float 6
        {
            String unknownAsHexString = hexSubString(42, 4, hexMessageString);
            float unknownFloat = GC4Message.lBytesToFloat(GC4Message.hexStringToByteArray(unknownAsHexString));
            System.out.println(String.format("position 42 to 45 (0x%s) ??? float value=%f", unknownAsHexString, unknownFloat));

            int unknownInt = GC4Message.lBytesToInt(GC4Message.hexStringToByteArray(unknownAsHexString));
            System.out.println(String.format("position 42 to 45 (0x%s) ??? int value=%d", unknownAsHexString, unknownInt));
        }
        // unkown float 7
        {
            String unknownAsHexString = hexSubString(46, 4, hexMessageString);
            float unknownFloat = GC4Message.lBytesToFloat(GC4Message.hexStringToByteArray(unknownAsHexString));
            System.out.println(String.format("position 46 to 49 (0x%s) float value=%f", unknownAsHexString, unknownFloat));

            int unknownInt = GC4Message.lBytesToInt(GC4Message.hexStringToByteArray(unknownAsHexString));
            System.out.println(String.format("position 46 to 49 (0x%s) ??? int value=%d", unknownAsHexString, unknownInt));
        }
    }

}
