package com.itestra.gc4connect.handler;

import org.apache.commons.lang3.Validate;

public abstract class GC4MessageHandler {

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
