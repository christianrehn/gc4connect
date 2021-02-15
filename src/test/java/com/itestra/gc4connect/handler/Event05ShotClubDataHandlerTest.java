package com.itestra.gc4connect.handler;

import com.itestra.gc4connect.data.GC4ShotClubData;
import com.itestra.gc4connect.message.GC4Event;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class Event05ShotClubDataHandlerTest {

    private static final double DELTA = 0.000001;

    @Test
    void handleHexMessageString() {
        new Event05ShotClubDataHandler().handleHexMessageString(GC4Event.EVENT_0500_STRING_116_SHOT_CLUB_DATA);
    }

    /**
     * FSX2020 data:
     * shotNumber=7
     * clubSpeed=35.2 MPH
     * efficiency=1.23
     * angleOfAttack=4.3° Dn
     * clubPath=11.4° Out-In
     * faceToPath=6.2° Open
     * lie=1.9° Toe Up
     * loft=33.3° Toe Up
     * faceImpactLateral=9mm heel
     * faceImpactVertical=13mm low
     * closureRate=863dps
     * <p>
     * carry=31 YDS
     * total=44 YDS
     */
    @Test
    void handleHexMessageString1() {
        String event0500String116ShotClubData =
                "0500" + "38000000" + "51070000" +
                        "07000000" + "62E40D42" + "17420D42" + "74348AC0" +
                        "E39A36C1" + "7CC3A6C0" + "EE28F13F" + "9D180542" +
                        "BEF41341" + "DB1050C1" + "3C213041" + "F3311842" +
                        "3EC95744";
        GC4ShotClubData gc4ShotClubData = new Event05ShotClubDataHandler().handleHexMessageString(event0500String116ShotClubData);
        Assert.assertEquals(1873, gc4ShotClubData.unknownInt2);
        Assert.assertEquals(7, gc4ShotClubData.shotNumber);
        Assert.assertEquals(35.473030, gc4ShotClubData.clubSpeed, DELTA);
        Assert.assertEquals(35.314541, gc4ShotClubData.unknownFloat2, DELTA);
        Assert.assertEquals(-4.318903, gc4ShotClubData.angleOfAttack, DELTA);
        Assert.assertEquals(-11.412814, gc4ShotClubData.clubPath, DELTA);
        Assert.assertEquals(-5.211363, gc4ShotClubData.faceToPath, DELTA);
        Assert.assertEquals(1.884062, gc4ShotClubData.lie, DELTA);
        Assert.assertEquals(33.274036, gc4ShotClubData.loft, DELTA);
        Assert.assertEquals(9.247252, gc4ShotClubData.faceImpactLateral, DELTA);
        Assert.assertEquals(-13.004115, gc4ShotClubData.faceImpactVertical, DELTA);
        Assert.assertEquals(11.008113, gc4ShotClubData.unknownFloat10, DELTA);
        Assert.assertEquals(38.048778, gc4ShotClubData.unknownFloat11, DELTA);
        Assert.assertEquals(863.144409, gc4ShotClubData.closureRate, DELTA);
    }

    /**
     * FSX2020 data:
     * shotNumber=7
     * clubSpeed=35.2 MPH
     * efficiency=1.23
     * angleOfAttack=4.3° Dn
     * clubPath=11.4° Out-In
     * faceToPath=6.2° Open
     * lie=1.9° Toe Up
     * loft=33.3° Toe Up
     * faceImpactLateral=9mm heel
     * faceImpactVertical=13mm low
     * closureRate=863dps
     * <p>
     * carry=12 YDS
     * total=30 YDS
     */
    @Test
    void handleHexMessageString2() {
        String event0500String116ShotClubData =
                "0500" + "38000000" + "E7060000" +
                        "09000000" + "522F1542" + "81AD1842" + "1B9CF1C0" +
                        "F7F7A5C1" + "374E1141" + "91324640" + "DCFE1E42" +
                        "C3C67740" + "F30B07C2" + "EC10FC41" + "1BFC5A42" +
                        "FC719144";
        GC4ShotClubData gc4ShotClubData = new Event05ShotClubDataHandler().handleHexMessageString(event0500String116ShotClubData);
        Assert.assertEquals(1767, gc4ShotClubData.unknownInt2);
        Assert.assertEquals(9, gc4ShotClubData.shotNumber);
        Assert.assertEquals(37.296211, gc4ShotClubData.clubSpeed, DELTA);
        Assert.assertEquals(38.169437, gc4ShotClubData.unknownFloat2, DELTA);
        Assert.assertEquals(-7.550306, gc4ShotClubData.angleOfAttack, DELTA);
        Assert.assertEquals(-20.746077, gc4ShotClubData.clubPath, DELTA);
        Assert.assertEquals(9.081595, gc4ShotClubData.faceToPath, DELTA);
        Assert.assertEquals(3.096836, gc4ShotClubData.lie, DELTA);
        Assert.assertEquals(39.748886, gc4ShotClubData.loft, DELTA);
        Assert.assertEquals(3.871506, gc4ShotClubData.faceImpactLateral, DELTA);
        Assert.assertEquals(-33.761669, gc4ShotClubData.faceImpactVertical, DELTA);
        Assert.assertEquals(31.508263, gc4ShotClubData.unknownFloat10, DELTA);
        Assert.assertEquals(54.746197, gc4ShotClubData.unknownFloat11, DELTA);
        Assert.assertEquals(1163.562012, gc4ShotClubData.closureRate, DELTA);
    }
}