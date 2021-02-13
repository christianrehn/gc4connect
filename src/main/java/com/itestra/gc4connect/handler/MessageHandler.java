package com.itestra.gc4connect.handler;

import org.apache.commons.lang3.Validate;

public abstract class MessageHandler {

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

        if (hexMessageString.equals(getDefaultHexMessageString())) {
            System.out.println("received message is equal to the expected default message");
            return;
        }
        throw new RuntimeException("received message does not match default message: " + hexMessageString);
    }

    protected String getHexStringBytes(int position, int byteLen, String hexMessageString) {
        int beginIndex = position * 2;
        return hexMessageString.substring(beginIndex, beginIndex + byteLen * 2);
    }

    protected String validateHexStringBytes(int position, String expected, String hexMessageString) {
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
