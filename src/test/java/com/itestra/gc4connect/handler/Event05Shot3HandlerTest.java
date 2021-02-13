package com.itestra.gc4connect.handler;

import com.itestra.gc4connect.message.GC4Event;
import org.junit.jupiter.api.Test;

class Event05Shot3HandlerTest {

    @Test
    void handleHexMessageString() {
        new Event05ShotClubDataHandler().handleHexMessageString(GC4Event.EVENT_0500_STRING_116_SHOT_CLUB_DATA);
    }

    @Test
    void handleHexMessageString2() {
        String event0500String116Shot3 =
                "0500" + "38000000" + "4b070000" +
                        "7a000000" + "2c879242" + "ffff7f7f" + "772b38c0" +
                        "a853333f" + "ffff7f7f" + "ffff7f7f" + "ffff7f7f" +
                        "ffff7f7f" + "ffff7f7f" + "ffff7f7f" + "ffff7f7f" +
                        "ffff7f7f";
        new Event05ShotClubDataHandler().handleHexMessageString(event0500String116Shot3);
    }
}