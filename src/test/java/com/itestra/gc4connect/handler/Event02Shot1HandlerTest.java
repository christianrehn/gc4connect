package com.itestra.gc4connect.handler;

import com.itestra.gc4connect.message.GC4Event;
import org.junit.jupiter.api.Test;

class Event02Shot1HandlerTest {

    @Test
    void handleHexMessageString() {
        new Event02ShotBallDataHandler().handleHexMessageString(GC4Event.EVENT_0200_STRING_104_SHOT_BALL_DATA);
    }
}