package com.itestra.gc4connect.handler;

import com.itestra.gc4connect.message.GC4RequestResponse;
import org.apache.commons.lang3.Validate;

public class Response11Handler extends MessageHandler {

    public static final int MESSAGE_LENGTH_BYTES = 12;
    public static final String OPERATION_000 = "11";

    @Override
    public String getDefaultHexMessageString() {
        return GC4RequestResponse.RESPONSE_1112_STRING_66;
    }

    public void handleHexMessageString(String hexMessageString) {
        Validate.notEmpty(hexMessageString);
        Validate.isTrue(hexMessageString.length() == 2 * MESSAGE_LENGTH_BYTES, "" + hexMessageString.length());

        super.handleHexMessageString(hexMessageString);

        validateHexStringBytes(0, OPERATION_000, hexMessageString);
        validateHexStringBytes(1, DIRECTION_001_GC4_TO_HOST, hexMessageString);
    }

}
