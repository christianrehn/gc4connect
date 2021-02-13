package com.itestra.gc4connect.handler;

import com.itestra.gc4connect.message.GC4RequestResponse;
import org.apache.commons.lang3.Validate;

public class Response06Handler extends AbstractMessageHandler {

    @Override
    public String getDefaultHexMessageString() {
        return GC4RequestResponse.REQUEST_0602_STRING_60;
    }

    public void handleHexMessageString(String hexMessageString) {
        Validate.notEmpty(hexMessageString);

        super.handleHexMessageString(hexMessageString);
    }

}
