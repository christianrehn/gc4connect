package com.itestra.gc4connect.handler;

import com.itestra.gc4connect.message.GC4Message;
import org.apache.commons.lang3.Validate;

public class Event03Shot2Handler extends EventOrResponse03Handler {

    public static final int MESSAGE_LENGTH_BYTES = 51;

    @Override
    public String getDefaultHexMessageString() {
        return null;
    }

    public void handleHexMessageString(String hexMessageString) {
        Validate.notEmpty(hexMessageString);
        Validate.isTrue(hexMessageString.length() == 2 * MESSAGE_LENGTH_BYTES, "" + hexMessageString.length());

        super.handleHexMessageString(hexMessageString);

        validateHexStringBytes(0, OPERATION_000, hexMessageString);
        validateHexStringBytes(1, DIRECTION_001_GC4_EVENT, hexMessageString);
    }

}
