package com.itestra.gc4connect.handler;

import com.itestra.gc4connect.data.GC4Response03Data;
import com.itestra.gc4connect.message.GC4RequestResponse;
import org.apache.commons.lang3.Validate;

public class Response03Handler extends EventOrResponse03Handler {

    @Override
    public String getDefaultHexMessageString() {
        return GC4RequestResponse.RESPONSE_0312_STRING_80;
    }

    public GC4Response03Data handleHexMessageString(String hexMessageString) {
        Validate.notEmpty(hexMessageString);
        Validate.isTrue(hexMessageString.length() == 2 * MESSAGE_LENGTH_BYTES_RESPONSE03_OR_EVENT03_SHOT2, "" + hexMessageString.length());

        super.handleHexMessageString(hexMessageString);

        GC4Response03Data gc4Response03Data = new GC4Response03Data();

        validateHexString(0, OPERATION_RESPONSE03_OR_EVENT03_SHOT2, hexMessageString);
        validateHexString(1, DIRECTION_001_GC4_TO_HOST, hexMessageString);

        // unknown int 1, always 0x14000000
        validateHexString(2, UNKNOWN_002_TO_005, hexMessageString);

        return gc4Response03Data;
    }

}
