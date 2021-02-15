package com.itestra.gc4connect.handler;

import com.itestra.gc4connect.data.GC4ShotSpinData;
import com.itestra.gc4connect.message.GC4Event;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class Event03ShotSpinDataHandlerTest {

    private static final double DELTA = 0.000001;

    @Test
    void handleHexMessageString() {
        new Event03ShotSpinDataHandler().handleHexMessageString(GC4Event.EVENT_0300_STRING_80_SHOT_SPIN_DATA);
    }

    /**
     * FSX2020 data:
     * shotNumber=7
     * backspin=3344 RPM
     * sidespin= 803 RPM
     */
    @Test
    void handleHexMessageString1() {
        String event0300String80ShotSpinData =
                "0300" + "14000000" + "B7010000" +
                        "07000000" + "00005145" + "00C04844" + "01000000";
        GC4ShotSpinData gc4ShotSpinData = new Event03ShotSpinDataHandler().handleHexMessageString(event0300String80ShotSpinData);
        Assert.assertEquals(439, gc4ShotSpinData.unknownInt2);
        Assert.assertEquals(7, gc4ShotSpinData.shotNumber);
        Assert.assertEquals(3344, gc4ShotSpinData.backspin, DELTA);
        Assert.assertEquals(803, gc4ShotSpinData.sidespin, DELTA);
    }

    /**
     * FSX2020 data:
     * shotNumber=9
     * backspin=2465 RPM
     * sidespin= 3614 RPM
     */
    @Test
    void handleHexMessageString2() {
        String event0300String80ShotSpinData =
                "0300" + "14000000" + "BB010000" +
                        "09000000" + "00101A45" + "00E06145" + "01000000";
        GC4ShotSpinData gc4ShotSpinData = new Event03ShotSpinDataHandler().handleHexMessageString(event0300String80ShotSpinData);
        Assert.assertEquals(443, gc4ShotSpinData.unknownInt2);
        Assert.assertEquals(9, gc4ShotSpinData.shotNumber);
        Assert.assertEquals(2465, gc4ShotSpinData.backspin, DELTA);
        Assert.assertEquals(3614, gc4ShotSpinData.sidespin, DELTA);
    }
}