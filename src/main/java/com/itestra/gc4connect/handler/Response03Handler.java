package com.itestra.gc4connect.handler;

import com.itestra.gc4connect.message.GC4RequestResponse;
import org.apache.commons.lang3.Validate;

public class Response03Handler extends GC4MessageHandler {

    public static final int MESSAGE_LENGTH_BYTES = 26;
    public static final String OPERATION_000 = "03";

    @Override
    public String getDefaultHexMessageString() {
        return GC4RequestResponse.RESPONSE_0312_STRING_80;
    }

    public void handleHexMessageString(String hexMessageString) {
        Validate.notEmpty(hexMessageString);
        Validate.isTrue(hexMessageString.length() == 2 * MESSAGE_LENGTH_BYTES, "" + hexMessageString.length());

        super.handleHexMessageString(hexMessageString);

        validateHexString(0, OPERATION_000, hexMessageString);
        validateHexString(1, DIRECTION_001_GC4_TO_HOST, hexMessageString);
    }

}
