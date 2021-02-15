package com.itestra.gc4connect.handler;

import com.itestra.gc4connect.message.GC4Event;
import com.itestra.gc4connect.message.GC4Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Event01BallDetectionHandlerTest {

    private static final double DELTA = 0.000001;

    @Test
    void handleHexMessageString() {
        new Event01BallDetectionHandler().handleHexMessageString(GC4Event.EVENT_0100_STRING_105_BALL_DETECTION);
    }

    @Test
    void handleHexMessageString2() {
        String event0100String105BallDetectionbData =
                "01002" +
                "d00000000000100bc000000ad0000002e0000005f01000086000000890000000000000000000000000000000000000000";
        new Event01BallDetectionHandler().handleHexMessageString(event0100String105BallDetectionbData);
    }
}