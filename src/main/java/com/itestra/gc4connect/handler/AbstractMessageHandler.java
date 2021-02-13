package com.itestra.gc4connect.handler;

import org.apache.commons.lang3.Validate;

public abstract class AbstractMessageHandler {

    public abstract String getDefaultHexMessageString();

    public void handleHexMessageString(String hexMessageString) {
        Validate.notEmpty(hexMessageString);

        compareHexMessageStringWithDefault(hexMessageString);
    }

    private void compareHexMessageStringWithDefault(String hexMessageString) {
        Validate.notEmpty(hexMessageString);

        if (getDefaultHexMessageString() == null) {
            return;
        }

        if (hexMessageString.equals(getDefaultHexMessageString())) {
            System.out.println("received message is equal to the expected default message");
            return;
        }
        throw new RuntimeException("received message does not match default message: " + hexMessageString);
    }
}
