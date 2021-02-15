package com.itestra.gc4connect.handler;

import org.junit.jupiter.api.Test;

class Event01BallDetectionHandlerTest {

    private static final double DELTA = 0.000001;

    @Test
    void handleHexMessageString() {
        String event0100String105BallDetectionbData =
                "0100" + "2d000000" + "0100" + "0102" +
                        "01000000" + "f7000000" + "2e000000" + "5f010000" +
                        "86000000" + "89000000" + "11111111" + "11111111" +
                        "22222222" + "22222222" + "00";
        new Event01BallDetectionHandler().handleHexMessageString(event0100String105BallDetectionbData);
    }

    @Test
    void handleHexMessageString2() {
        String event0100String105BallDetectionbData =
                "0100" + "2d000000" + "0000" + "0100" +
                        "bc000000" + "ad000000" + "2e000000" + "5f010000" +
                        "86000000" + "89000000" + "00000000" + "00000000" +
                        "00000000" + "00000000" + "00";
        new Event01BallDetectionHandler().handleHexMessageString(event0100String105BallDetectionbData);
    }
}