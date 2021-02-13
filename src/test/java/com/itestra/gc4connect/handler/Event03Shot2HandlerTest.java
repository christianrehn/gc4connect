package com.itestra.gc4connect.handler;

import com.itestra.gc4connect.message.GC4Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Event03Shot2HandlerTest {

    @Test
    void handleHexMessageString() {
        new Event03Shot2Handler().handleHexMessageString(GC4Event.EVENT_0300_STRING_80_SHOT_2);
    }
}