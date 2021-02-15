package com.itestra.gc4connect.handler;

import com.itestra.gc4connect.data.GC4ShotBallData;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class Event02ShotBallDataHandlerTest {

    private static final double DELTA = 0.000001;

    /**
     * FSX2020 data:
     * shotNumber=7
     * ballSpeed=43.6 MPH
     * launchAngle=22.8°
     * sideAngle=7.4° L
     * descentAngle=26.6°
     * offline=4.4 YDS L
     * peak=4 YDS
     * carry=31 YDS
     * total=44 YDS
     */
    @Test
    void handleHexMessageString1() {
        String event0200String104ShotBallData =
                "0200" + "2C000000" + "96000000" +
                        "07000000" + "CD502E42" + "AD5CB641" + "6A35ECC0" +
                        "CDBF5A45" + "00000000" + "01000000" + "E76E84C3" +
                        "1C4EC541" + "C80F19C4";
        GC4ShotBallData gc4ShotBallData = new Event02ShotBallDataHandler().handleHexMessageString(event0200String104ShotBallData);
        Assert.assertEquals(150, gc4ShotBallData.unknownInt2);
        Assert.assertEquals(7, gc4ShotBallData.shotNumber);
        Assert.assertEquals(43.578907, gc4ShotBallData.ballSpeed, DELTA);
        Assert.assertEquals(22.795252, gc4ShotBallData.launchAngle, DELTA);
        Assert.assertEquals(-7.381520, gc4ShotBallData.sideAngle, DELTA);
        Assert.assertEquals(-264.866425, gc4ShotBallData.unknownFloat5, DELTA);
        Assert.assertEquals(-1014731033, gc4ShotBallData.unknownInt5, DELTA);
        Assert.assertEquals(24.663139, gc4ShotBallData.unknownFloat6, DELTA);
        Assert.assertEquals(0, gc4ShotBallData.unknownInt6, DELTA);
        Assert.assertEquals(-612.246582, gc4ShotBallData.unknownFloat7, DELTA);
        Assert.assertEquals(-1004990520, gc4ShotBallData.unknownInt7, DELTA);
    }

    /**
     * FSX2020 data:
     * shotNumber=9
     * ballSpeed=40,6 MPH
     * launchAngle=8,4°
     * sideAngle=0,3° L
     * descentAngle=9,2°
     * offline=1,9 YDS L
     * peak=0 YDS
     * carry=12 YDS
     * total=30 YDS
     */
    @Test
    void handleHexMessageString2() {
        String event0200String104ShotBallData =
                "0200" + "2C000000" + "99000000" +
                        "09000000" + "D93B2242" + "B6CA0541" + "779896BE" +
                        "CDBF5A45" + "00000000" + "01000000" + "4E3D38C3" +
                        "4DF4BC41" + "BDE21FC4";
        GC4ShotBallData gc4ShotBallData = new Event02ShotBallDataHandler().handleHexMessageString(event0200String104ShotBallData);
        Assert.assertEquals(153, gc4ShotBallData.unknownInt2);
        Assert.assertEquals(9, gc4ShotBallData.shotNumber);
        Assert.assertEquals(40.558445, gc4ShotBallData.ballSpeed, DELTA);
        Assert.assertEquals(8.361990, gc4ShotBallData.launchAngle, DELTA);
        Assert.assertEquals(-0.294132, gc4ShotBallData.sideAngle, DELTA);
        Assert.assertEquals(-184.239471, gc4ShotBallData.unknownFloat5, DELTA);
        Assert.assertEquals(-1019724466, gc4ShotBallData.unknownInt5, DELTA);
        Assert.assertEquals(23.619287, gc4ShotBallData.unknownFloat6, DELTA);
        Assert.assertEquals(0, gc4ShotBallData.unknownInt6, DELTA);
        Assert.assertEquals(-639.542786, gc4ShotBallData.unknownFloat7, DELTA);
        Assert.assertEquals(-1004543299, gc4ShotBallData.unknownInt7, DELTA);
    }

}