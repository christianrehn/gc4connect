package com.itestra.gc4connect.message;

public final class GC4Event extends GC4Message {

    public static String EVENT_0100_STRING_105_BALL_DETECTION =
            "0100" + "2d000000" + "0100" + "0102" +
                    "01000000" + "f7000000" + "2e000000" + "5f010000" +
                    "86000000" + "89000000" + "11111111" + "11111111" +
                    "22222222" + "22222222" + "00";

    public static String EVENT_0200_STRING_104_SHOT_BALL_DATA =
            "0200" + "2c000000" + "87000000" +
//           0200     2C000000     8C000000
                    "63000000" + "567ebd42" + "a5f8a341" + "5460dabf" +
//                   shot#        ball speed   launch angle side angle
//                   6            65,224541    14,189055    -5,727109
//                   06000000     F7728242     5F066341     7A44B7C0
//                   9
//                   09000000     D93B2242     B6CA0541     779896BE

                    "cdbf5a45" + "00000000" + "01000000" + "c8d0bbc3" +
//                   CDBF5A45     00000000     01000000     F46075C3
//                   U            U            U            descent angle?
//                   CDBF5A45     00000000     01000000     4E3D38C3
                    "e5656742" + "a8a319c4";
//                   347CCF41     C3D102C4
//                   4DF4BC41     BDE21FC4

//    position 38 to 41 (0xF46075C3) float value=-245,378723
//    position 42 to 45 (0x347CCF41) float value=25,935646
//    position 46 to 49 (0xC3D102C4) float value=-523,277527

    public static String EVENT_0300_STRING_80_SHOT_SPIN_DATA =
            "0300" + "14000000" + "a9010000" +
                    "63000000" + "00108845" + "008026c4" + "01000000";

    public static String EVENT_0500_STRING_116_SHOT_CLUB_DATA =
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
