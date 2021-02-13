package com.itestra.gc4connect.handler;

import org.apache.commons.lang3.Validate;

public class Response12Handler extends GC4MessageHandler {

    public static final int MESSAGE_LENGTH_BYTES = 726;
    public static final String OPERATION_000 = "12";

    @Override
    public String getDefaultHexMessageString() {
        return null;
    }

    public void handleHexMessageString(String hexMessageString) {
        Validate.notEmpty(hexMessageString);
        Validate.isTrue(hexMessageString.length() == 2 * MESSAGE_LENGTH_BYTES, "" + hexMessageString.length());

        super.handleHexMessageString(hexMessageString);

        validateHexString(0, OPERATION_000, hexMessageString);
        validateHexString(1, DIRECTION_001_GC4_TO_HOST, hexMessageString);
    }

}
