package com.itestra.gc4connect.handler;

import com.itestra.gc4connect.message.GC4Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Event05Shot3HandlerTest {

    @Test
    void handleHexMessageString() {
        new Event05Shot3Handler().handleHexMessageString(GC4Event.EVENT_0500_STRING_116_SHOT_3);
    }

    @Test
    void handleHexMessageString2() {
        String event0500String116Shot3 =
                "0500" + "38000000" + "4b070000" +
                        "7a000000" + "2c879242" + "ffff7f7f" + "772b38c0" +
                        "a853333f" + "ffff7f7f" + "ffff7f7f" + "ffff7f7f" +
                        "ffff7f7f" + "ffff7f7f" + "ffff7f7f" + "ffff7f7f" +
                        "ffff7f7f";
        new Event05Shot3Handler().handleHexMessageString(event0500String116Shot3);
    }
}