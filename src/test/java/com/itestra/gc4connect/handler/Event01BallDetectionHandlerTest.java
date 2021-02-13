package com.itestra.gc4connect.handler;

import com.itestra.gc4connect.message.GC4Event;
import com.itestra.gc4connect.message.GC4Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Event01BallDetectionHandlerTest {

    @Test
    void handleHexMessageString() {
        new Event01BallDetectionHandler().handleHexMessageString(GC4Event.EVENT_0100_STRING_105_BALL_DETECTION);
    }
}