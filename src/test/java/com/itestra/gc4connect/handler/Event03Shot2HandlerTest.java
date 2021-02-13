package com.itestra.gc4connect.handler;

import com.itestra.gc4connect.message.GC4Event;
import org.junit.jupiter.api.Test;

class Event03Shot2HandlerTest {

    @Test
    void handleHexMessageString() {
        new Event03ShotSpinDataHandler().handleHexMessageString(GC4Event.EVENT_0300_STRING_80_SHOT_SPIN_DATA);
    }
}