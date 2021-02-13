package com.itestra.gc4connect.handler;

import com.itestra.gc4connect.message.GC4Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Event02Shot1HandlerTest {

    @Test
    void handleHexMessageString() {
        new Event02Shot1Handler().handleHexMessageString(GC4Event.EVENT_0200_STRING_104_SHOT_1);
    }
}