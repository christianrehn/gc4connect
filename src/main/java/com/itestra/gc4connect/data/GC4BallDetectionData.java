package com.itestra.gc4connect.data;

public class GC4BallDetectionData extends GC4Data {
    public static final int MAX_BALL_POSITIONB_DATAS = 5;

    public short unknownShort1;
    public byte numberOfBalls;
    public int[] xBallPosition = new int[MAX_BALL_POSITIONB_DATAS];
    public int[] yBallPosition = new int[MAX_BALL_POSITIONB_DATAS];
}
