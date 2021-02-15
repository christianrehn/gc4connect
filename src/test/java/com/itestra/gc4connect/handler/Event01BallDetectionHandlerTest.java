package com.itestra.gc4connect.handler;

import com.itestra.gc4connect.data.GC4BallDetectionData;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class Event01BallDetectionHandlerTest {

    @Test
    void handleHexMessageString() {
        String event0100String105BallDetectionbData =
                "0100" + "2d000000" + "0100" + "0102" +
                        "01000000" + "f7000000" + "2e000000" + "5f010000" +
                        "86000000" + "89000000" + "00000000" + "00000000" +
                        "00000000" + "00000000" + "00";
        GC4BallDetectionData gc4BallDetectionData = new Event01BallDetectionHandler().handleHexMessageString(event0100String105BallDetectionbData);
        Assert.assertEquals(1, gc4BallDetectionData.unknownShort1);
        Assert.assertEquals(2, gc4BallDetectionData.numberOfBalls);
        Assert.assertEquals(1, gc4BallDetectionData.xBallPosition[0]);
        Assert.assertEquals(247, gc4BallDetectionData.yBallPosition[0]);
        Assert.assertEquals(46, gc4BallDetectionData.xBallPosition[1]);
        Assert.assertEquals(351, gc4BallDetectionData.yBallPosition[1]);
        Assert.assertEquals(134, gc4BallDetectionData.xBallPosition[2]);
        Assert.assertEquals(137, gc4BallDetectionData.yBallPosition[2]);
        Assert.assertEquals(0, gc4BallDetectionData.xBallPosition[3]);
        Assert.assertEquals(0, gc4BallDetectionData.yBallPosition[3]);
        Assert.assertEquals(0, gc4BallDetectionData.xBallPosition[4]);
        Assert.assertEquals(0, gc4BallDetectionData.yBallPosition[4]);
    }

    @Test
    void handleHexMessageString2() {
        String event0100String105BallDetectionbData =
                "0100" + "2d000000" + "0000" + "0100" +
                        "bc000000" + "ad000000" + "2e000000" + "5f010000" +
                        "86000000" + "89000000" + "00000000" + "00000000" +
                        "00000000" + "00000000" + "00";
        new Event01BallDetectionHandler().handleHexMessageString(event0100String105BallDetectionbData);
    }
}