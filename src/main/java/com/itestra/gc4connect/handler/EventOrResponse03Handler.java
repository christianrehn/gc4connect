package com.itestra.gc4connect.handler;

import com.itestra.gc4connect.message.GC4RequestResponse;
import org.apache.commons.lang3.Validate;

public abstract class EventOrResponse03Handler extends GC4MessageHandler {

    public static final String UNKNOWN_002_TO_005 = "14000000";

    @Override
    public String getDefaultHexMessageString() {
        return GC4RequestResponse.RESPONSE_0312_STRING_80;
    }

}
