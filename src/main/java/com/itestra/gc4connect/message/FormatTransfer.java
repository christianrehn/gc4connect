package com.itestra.gc4connect.message;

/**
 * Communication Format Conversion
 * <p>
 * See https://topic.alibabacloud.com/a/java-font-colorredbytefont-order-font-colorredbytefont-sequence-conversion-for-network-data-transfer-in-different-languages-__java_1_27_20240425.html
 * <p>
 * Java and some Windows programming languages such as C, C + +, Delphi written by the network program to communicate, the need for the corresponding conversion
 * high, low byte conversion
 * Windows byte order is low byte start
 * Linux,unix byte order is High-byte start
 * Java is a high byte beginning regardless of platform change
 */
public class FormatTransfer {

    /**
     * Convert int to high byte before, low byte array
     */
//    public static byte[] toHH(int number) {
//        int temp = number;
//        byte[] b = new byte[4];
//        for (int i = b.length - 1; i > -1; i--) {
//            b = new Integer(temp & 0xff).byteValue();
//            temp = temp >> 8;
//        }
//        return b;
//    }
    public static byte[] intToByteArray(int i) {
        byte[] abyte0 = new byte[4];
        abyte0[3] = (byte) (0xff & i);
        abyte0[2] = (byte) ((0xff00 & i) >> 8);
        abyte0[1] = (byte) ((0xff0000 & i) >> 16);
        abyte0[0] = (byte) ((0xff000000 & i) >> 24);
        return abyte0;
    }


    /**
     * Converts a string to a byte array
     */
    public static byte[] stringToBytes(String s, int length) {
        while (s.getBytes().length < length) {
            s += " ";
        }
        return s.getBytes();
    }

    /**
     * Converts a byte array to a string * @param b byte[]
     *
     * @return string
     */
//    public static String bytesToString(byte[] b) {
//        StringBuffer result = new StringBuffer("");
//        int length = b.length;
//        for (int i = 0; i < length; i++) {
//            result.append((char) (b && 0xff));
//        }
//        return result.toString();
//    }

    /**
     * Converts a string to a byte array
     *
     * @param s String
     * @return byte[]
     */
    public static byte[] stringToBytes(String s) {
        return s.getBytes();
    }



    /**
     * High byte array to short conversion
     *
     * @param b byte[]
     * @return Short
     */
    public static short hBytesToShort(byte[] b) {
        int s = 0;
        if (b[0] >= 0) {
            s = s + b[0];
        } else {
            s = s + 256 + b[0];
        }
        s = s * 256;
        if (b[1] >= 0) {
            s = s + b[1];
        } else {
            s = s + 256 + b[1];
        }
        short result = (short) s;
        return result;
    }

    /**
     * Low byte array to short conversion
     *
     * @param b byte[]
     * @return Short
     */
    public static short lBytesToShort(byte[] b) {
        int s = 0;
        if (b[1] >= 0) {
            s = s + b[1];
        } else {
            s = s + 256 + b[1];
        }
        s = s * 256;
        if (b[0] >= 0) {
            s = s + b[0];
        } else {
            s = s + 256 + b[0];
        }
        short result = (short) s;
        return result;
    }

    public static void printBytes(byte[] bb) {
        int length = bb.length;
        for (int i = 0; i < length; i++) {
            System.out.print(bb + " ");
        }
        System.out.println("");
    }

    public static void logBytes(byte[] bb) {
        int length = bb.length;
        String out = "";
        for (int i = 0; i < length; i++) {
            out = out + bb + " ";
        }
    }

    /**
     * Converts the value of type int to byte order inversion corresponding int value
     *
     * @param i int
     * @return int
     */
//    public static int reverseInt(int i) {
//        int result = FormatTransfer.hBytesToInt(FormatTransfer.toLH(i));
//        return result;
//    }



}
