package com.itestra.gc4connect.message;

public final class GC4Event extends GC4Message {

    public static String EVENT_0100_STRING_105_BALL_DETECTION =
            "0100" + "2d000000" + "0100" + "0102" +
                    "01000000" + "f7000000" + "2e000000" + "5f010000" +
                    "86000000" + "89000000" + "11111111" + "11111111" +
                    "22222222" + "22222222" + "00";

    public static String EVENT_0200_STRING_104_SHOT_1 =
            "0200" + "2c000000" + "87000000" +
                    "63000000" + "567ebd42" + "a5f8a341" + "5460dabf" +
                    "cdbf5a45" + "00000000" + "01000000" + "c8d0bbc3" +
                    "e5656742" + "a8a319c4";

    public static String EVENT_0300_STRING_80_SHOT_2 =
            "0300" + "14000000" + "a9010000" +
                    "63000000" + "00108845" + "008026c4" + "01000000";

    public static String EVENT_0500_STRING_116_SHOT_3 =
            "0500" + "38000000" + "81070000" +
//           0500     38000000     4b070000
                    "63000000" + "ff0c8e42" + "022f8e42" + "7b498dc0" +
//                   7a000000     2c879242     ffff7f7f     772b38c0
                    "15d91340" + "e9c9e6bf" + "d4ddc33f" + "2210d441" +
//                f4 a853333f     ffff7f7f     ffff7f7f     ffff7f7f
                    "fe719f3f" + "6b85aec0" + "52a1fcc0" + "4e6df941" +
//                f8 ffff7f7f     ffff7f7f     ffff7f7f     ffff7f7f
                    "80fa0845";
//               f12 ffff7f7f
}
