package com.itestra.gc4connect.handler;

import com.itestra.gc4connect.message.GC4Message;
import org.apache.commons.lang3.Validate;

/**
 * Spin data
 */
public class Event03ShotSpinDataHandler extends EventOrResponse03Handler {

    public static final String UNKNOWN_022_TO_025 = "01000000";

    @Override
    public String getDefaultHexMessageString() {
        return null;
    }

    public void handleHexMessageString(String hexMessageString) {
        Validate.notEmpty(hexMessageString);
        Validate.isTrue(hexMessageString.length() == 2 * MESSAGE_LENGTH_BYTES_RESPONSE03_OR_EVENT03_SHOT2, "" + hexMessageString.length());

        super.handleHexMessageString(hexMessageString);

        System.out.println(String.format("SHOT SPIN DATA EVENT"));

        validateHexString(0, OPERATION_RESPONSE03_OR_EVENT03_SHOT2, hexMessageString);
        validateHexString(1, DIRECTION_001_GC4_EVENT, hexMessageString);

        // unknown int 1, always 0x14000000
        validateHexString(2, UNKNOWN_002_TO_005, hexMessageString);

        // unkown int 2
        {
            String unknownAsHexString = hexSubString(6, 4, hexMessageString);
            int unknown = GC4Message.lBytesToInt(GC4Message.hexStringToByteArray(unknownAsHexString));
            System.out.println(String.format("position 6 to 9 (0x%s) int value=%d", unknownAsHexString, unknown));
        }

        // shotNumber int 3
        {
            String shotNumberAsHexString = hexSubString(10, 4, hexMessageString);
            int shotNumber = GC4Message.lBytesToInt(GC4Message.hexStringToByteArray(shotNumberAsHexString));
            System.out.println(String.format("position 10 to 13 (0x%s) int shotNumber=%d", shotNumberAsHexString, shotNumber));
        }

        // backspin
        {
            String backspinAsHexString = hexSubString(14, 4, hexMessageString);
            float backspin = GC4Message.lBytesToFloat(GC4Message.hexStringToByteArray(backspinAsHexString));
            System.out.println(String.format("position 14 to 17 (0x%s) backspin=%f", backspinAsHexString, backspin));
        }

        // sidespin
        {
            String unknownAsHexString = hexSubString(18, 4, hexMessageString);
            float sidespin = GC4Message.lBytesToFloat(GC4Message.hexStringToByteArray(unknownAsHexString));
            System.out.println(String.format("position 18 to 21 (0x%s) sidespin=%f", unknownAsHexString, sidespin));
        }

        // unknown int 4, always 0x01000000
        validateHexString(22, UNKNOWN_022_TO_025, hexMessageString);

    }

}
