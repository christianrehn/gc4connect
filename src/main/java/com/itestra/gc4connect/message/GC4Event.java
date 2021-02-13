package com.itestra.gc4connect.message;

public final class GC4Event extends GC4Message {

    public static String EVENT_0100_STRING_105_BALL_DETECTION =
            "0100" + "2d000000" + "0100" + "0102" +
                    "01000000" + "f7000000" + "2e000000" + "5f010000" +
                    "8600000089000000" + "1111111111111111" +
                    "2222222222222222" + "00";
    public static byte[] EVENT_0100_BYTES_105_BALL_DETECTION = hexStringToByteArray(EVENT_0100_STRING_105_BALL_DETECTION);
}
