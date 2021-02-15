package com.itestra.gc4connect.handler;

import com.itestra.gc4connect.data.GC4Response11Data;
import org.apache.commons.lang3.Validate;

public class Response11Handler extends GC4MessageHandler {

    public static final String UNKNOWN_002_TO_007 = "060000000101";
    public static final String UNKNOWN_011 = "42";

    @Override
    public String getDefaultHexMessageString() {
        return null;
    }

    public GC4Response11Data handleHexMessageString(String hexMessageString) {
        Validate.notEmpty(hexMessageString);
        Validate.isTrue(hexMessageString.length() == 2 * MESSAGE_LENGTH_BYTES_RESPONSE11, "" + hexMessageString.length());

        super.handleHexMessageString(hexMessageString);

        GC4Response11Data gc4Response11Data = new GC4Response11Data();

        validateHexString(0, OPERATION_RESPONSE11, hexMessageString);
        validateHexString(1, DIRECTION_001_GC4_TO_HOST, hexMessageString);
        validateHexString(2, UNKNOWN_002_TO_007, hexMessageString);
        validateHexString(11, UNKNOWN_011, hexMessageString);

        return gc4Response11Data;
    }

}
