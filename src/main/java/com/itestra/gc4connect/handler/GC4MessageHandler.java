package com.itestra.gc4connect.handler;

import org.apache.commons.lang3.Validate;

public abstract class GC4MessageHandler {

    // operations (1st byte in message) and message lengths in bytes
    public static final String OPERATION_BALL_DETECTION = "01";
    public static final int MESSAGE_LENGTH_BYTES_BALL_DETECTION = 51;

    public static final String OPERATION_EVENT02_SHOT1 = "02";
    public static final int MESSAGE_LENGTH_BYTES_EVENT02_SHOT1 = 50;

    public static final String OPERATION_RESPONSE03_OR_EVENT03_SHOT2 = "03";
    public static final int MESSAGE_LENGTH_BYTES_RESPONSE03_OR_EVENT03_SHOT2 = 26;

    public static final String OPERATION_RESPONSE05 = "05";
    public static final int MESSAGE_LENGTH_BYTES_RESPONSE05 = 62;

    public static final String OPERATION_RESPONSE06 = "06";
    public static final int MESSAGE_LENGTH_BYTES_RESPONSE06 = 66;

    public static final String OPERATION_RESPONSE11 = "11";
    public static final int MESSAGE_LENGTH_BYTES_RESPONSE11 = 12;

    public static final String OPERATION_RESPONSE12 = "12";
    public static final int MESSAGE_LENGTH_BYTES_RESPONSE12 = 726;

    public static int getMessageLengthBytes(String operationHexString) {
        if (GC4MessageHandler.OPERATION_BALL_DETECTION.equals(operationHexString)) {
            return MESSAGE_LENGTH_BYTES_BALL_DETECTION;
        }
        if (GC4MessageHandler.OPERATION_EVENT02_SHOT1.equals(operationHexString)) {
            return MESSAGE_LENGTH_BYTES_EVENT02_SHOT1;
        }
        if (GC4MessageHandler.OPERATION_RESPONSE03_OR_EVENT03_SHOT2.equals(operationHexString)) {
            return MESSAGE_LENGTH_BYTES_RESPONSE03_OR_EVENT03_SHOT2;
        }
        if (GC4MessageHandler.OPERATION_RESPONSE05.equals(operationHexString)) {
            return MESSAGE_LENGTH_BYTES_RESPONSE05;
        }
        if (GC4MessageHandler.OPERATION_RESPONSE06.equals(operationHexString)) {
            return MESSAGE_LENGTH_BYTES_RESPONSE06;
        }
        if (GC4MessageHandler.OPERATION_RESPONSE11.equals(operationHexString)) {
            return MESSAGE_LENGTH_BYTES_RESPONSE11;
        }
        if (GC4MessageHandler.OPERATION_RESPONSE12.equals(operationHexString)) {
            return MESSAGE_LENGTH_BYTES_RESPONSE12;
        }

        throw new RuntimeException("unknown operation: " + operationHexString);
    }

    // dircetion values
    public static final String DIRECTION_001_GC4_EVENT = "00";
    public static final String DIRECTION_001_GC4_TO_HOST = "12";

    public abstract String getDefaultHexMessageString();

    public void handleHexMessageString(String hexMessageString) {
        Validate.notEmpty(hexMessageString);

        compareHexMessageStringWithDefault(hexMessageString);
    }

    private void compareHexMessageStringWithDefault(String hexMessageString) {
        Validate.notEmpty(hexMessageString);

        if (getDefaultHexMessageString() == null) {
            return;
        }

        if (hexMessageString.equalsIgnoreCase(getDefaultHexMessageString())) {
            System.out.println("received message is equal to the expected default message");
            return;
        }
        throw new RuntimeException(
                "received message does not match default message\n"
                        + "expected: " + getDefaultHexMessageString() + "\n"
                        + "received: " + hexMessageString + "\n"
        );
    }

    public static String hexSubString(int position, int byteLen, String hexString) {
        int beginIndex = position * 2;
        return hexString.substring(beginIndex, beginIndex + byteLen * 2);
    }

    protected static String validateHexString(int position, String expected, String hexString) {
        Validate.notEmpty(hexString);
        Validate.isTrue(hexString.length() >= position * 2 + expected.length());
        Validate.notEmpty(expected);

        int charLen = expected.length();
        Validate.isTrue(charLen % 2 == 0);
        String hexStringBytes = hexSubString(position, charLen / 2, hexString);
        if (!expected.equalsIgnoreCase(hexStringBytes)) {
            throw new RuntimeException(String.format("wrong byte at position %d, expected: %s, actual: %s", position, expected, hexStringBytes));
        }
        return hexStringBytes;
    }
}
