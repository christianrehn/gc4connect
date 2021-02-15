package com.itestra.gc4connect.handler;

import com.itestra.gc4connect.data.GC4Response12Data;
import org.apache.commons.lang3.Validate;

public class Response12Handler extends GC4MessageHandler {

    @Override
    public String getDefaultHexMessageString() {
        return null;
    }

    public GC4Response12Data handleHexMessageString(String hexMessageString) {
        Validate.notEmpty(hexMessageString);
        Validate.isTrue(hexMessageString.length() == 2 * MESSAGE_LENGTH_BYTES_RESPONSE12, "" + hexMessageString.length());

        super.handleHexMessageString(hexMessageString);

        GC4Response12Data gc4Response12Data = new GC4Response12Data();

        validateHexString(0, OPERATION_RESPONSE12, hexMessageString);
        validateHexString(1, DIRECTION_001_GC4_TO_HOST, hexMessageString);

        return gc4Response12Data;
    }

}
