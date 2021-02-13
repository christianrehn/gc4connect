package com.itestra.gc4connect.handler;

import com.itestra.gc4connect.message.GC4RequestResponse;
import org.apache.commons.lang3.Validate;

public class Response06Handler extends GC4MessageHandler {

    @Override
    public String getDefaultHexMessageString() {
        return GC4RequestResponse.RESPONSE_0612_STRING_120;
    }

    public void handleHexMessageString(String hexMessageString) {
        Validate.notEmpty(hexMessageString);
        Validate.isTrue(hexMessageString.length() == 2 * MESSAGE_LENGTH_BYTES_RESPONSE06, "" + hexMessageString.length());

        super.handleHexMessageString(hexMessageString);

        validateHexString(0, OPERATION_RESPONSE06, hexMessageString);
        validateHexString(1, DIRECTION_001_GC4_TO_HOST, hexMessageString);
    }

}
