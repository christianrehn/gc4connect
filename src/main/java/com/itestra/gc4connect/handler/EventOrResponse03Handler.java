package com.itestra.gc4connect.handler;

import com.itestra.gc4connect.message.GC4RequestResponse;
import org.apache.commons.lang3.Validate;

public class EventOrResponse03Handler extends MessageHandler {

    public static final String OPERATION_000 = "03";

    @Override
    public String getDefaultHexMessageString() {
        return GC4RequestResponse.RESPONSE_0312_STRING_80;
    }

    public void handleHexMessageString(String hexMessageString) {
        Validate.notEmpty(hexMessageString);

        validateHexStringBytes(0, OPERATION_000, hexMessageString);

        String directionAsHexString = getHexStringBytes(1, 1, hexMessageString);
        if (DIRECTION_001_GC4_EVENT.equals(directionAsHexString)) {
            new Event03Shot2Handler().handleHexMessageString(hexMessageString);
        } else if (DIRECTION_001_GC4_TO_HOST.equals(directionAsHexString)) {
            new Response03Handler().handleHexMessageString(hexMessageString);
        } else {
            throw new RuntimeException("direction unknown: " + directionAsHexString);
        }
    }

}
