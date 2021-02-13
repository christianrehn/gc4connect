package com.itestra.gc4connect.handler;

import com.itestra.gc4connect.message.GC4Message;
import org.apache.commons.lang3.Validate;

public class Event02Shot1Handler extends GC4MessageHandler {

    public static final int MESSAGE_LENGTH_BYTES = 50;
    public static final String OPERATION_000 = "02";
    public static final String UNKNOWN_002_TO_005 = "2c000000";
    public static final String UNKNOWN_030_TO_033 = "00000000";
    public static final String UNKNOWN_034_TO_037 = "01000000";

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

        // unknown int 1, always 0x2c000000
        validateHexString(2, UNKNOWN_002_TO_005, hexMessageString);

        // unkown int 2
        {
            String unknownAsHexString = hexSubString(6, 4, hexMessageString);
            int unknownInt = GC4Message.lBytesToInt(GC4Message.hexStringToByteArray(unknownAsHexString));
            System.out.println(String.format("position 6 to 9 (0x%s) int value=%d", unknownAsHexString, unknownInt));
        }

        // shotNumber int 3
        {
            String shotNumberAsHexString = hexSubString(10, 4, hexMessageString);
            int shotNumber = GC4Message.lBytesToInt(GC4Message.hexStringToByteArray(shotNumberAsHexString));
            System.out.println(String.format("position 10 to 13 (0x%s) int shotNumber=%d", shotNumberAsHexString, shotNumber));
        }

        // unkown float 1
        {
            String unknownAsHexString = hexSubString(14, 4, hexMessageString);
            float unknownFloat = GC4Message.lBytesToFloat(GC4Message.hexStringToByteArray(unknownAsHexString));
            System.out.println(String.format("position 14 to 17 (0x%s) float value=%f", unknownAsHexString, unknownFloat));
        }

        // unkown float 2
        {
            String unknownAsHexString = hexSubString(18, 4, hexMessageString);
            float unknownFloat = GC4Message.lBytesToFloat(GC4Message.hexStringToByteArray(unknownAsHexString));
            System.out.println(String.format("position 18 to 21 (0x%s) float value=%f", unknownAsHexString, unknownFloat));
        }

        // unkown float 3
        {
            String unknownAsHexString = hexSubString(22, 4, hexMessageString);
            float unknownFloat = GC4Message.lBytesToFloat(GC4Message.hexStringToByteArray(unknownAsHexString));
            System.out.println(String.format("position 22 to 25 (0x%s) float value=%f", unknownAsHexString, unknownFloat));
        }

        // unkown float 4
        {
            String unknownAsHexString = hexSubString(26, 4, hexMessageString);
            float unknownFloat = GC4Message.lBytesToFloat(GC4Message.hexStringToByteArray(unknownAsHexString));
            System.out.println(String.format("position 26 to 29 (0x%s) float value=%f", unknownAsHexString, unknownFloat));
        }

        // unknown int 4, always 0x00000000
        validateHexString(30, UNKNOWN_030_TO_033, hexMessageString);

        // unknown int 5, always 0x01000000
        validateHexString(34, UNKNOWN_034_TO_037, hexMessageString);

        // unkown float 5
        {
            String unknownAsHexString = hexSubString(38, 4, hexMessageString);
            float unknownFloat = GC4Message.lBytesToFloat(GC4Message.hexStringToByteArray(unknownAsHexString));
            System.out.println(String.format("position 38 to 41 (0x%s) float value=%f", unknownAsHexString, unknownFloat));
        }
        // unkown float 6
        {
            String unknownAsHexString = hexSubString(42, 4, hexMessageString);
            float unknownFloat = GC4Message.lBytesToFloat(GC4Message.hexStringToByteArray(unknownAsHexString));
            System.out.println(String.format("position 42 to 45 (0x%s) float value=%f", unknownAsHexString, unknownFloat));
        }
        // unkown float 7
        {
            String unknownAsHexString = hexSubString(46, 4, hexMessageString);
            float unknownFloat = GC4Message.lBytesToFloat(GC4Message.hexStringToByteArray(unknownAsHexString));
            System.out.println(String.format("position 46 to 49 (0x%s) float value=%f", unknownAsHexString, unknownFloat));
        }
    }

}
