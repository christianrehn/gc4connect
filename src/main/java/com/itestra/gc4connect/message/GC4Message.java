package com.itestra.gc4connect.message;

import org.apache.commons.lang3.Validate;

import java.nio.charset.StandardCharsets;

public abstract class GC4Message {

    private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);

    public static String bytesToHexString(byte[] bytes, int length) {
        Validate.isTrue(bytes.length >= length);

        byte[] hexChars = new byte[length * 2];
        for (int j = 0; j < length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }

    public static byte[] hexStringToByteArray(String s) {
        int charLen = s.length();
        Validate.isTrue(charLen % 2 == 0);
        byte[] data = new byte[charLen / 2];
        for (int i = 0; i < charLen; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    /**
     * High byte array converted to float
     *
     * @param b byte[]
     * @return float
     */
    public static float hBytesToFloat(byte[] b) {
        Validate.isTrue(b.length == 4);

        int intBits = Long.valueOf(bytesToHexString(b, 4), 16).intValue();
//        Float f = new Float(0.0);
//        int i = ((((b[0] & 0xff) << 8 | (b[1] & 0xff)) << 8) | (b[2] & 0xff)) << 8 | (b[3] & 0xff);
//        return f.intBitsToFloat(i);
        return Float.intBitsToFloat(intBits);
    }

    /**
     * Low byte array converted to float
     *
     * @param b byte[]
     * @return float
     */
    public static float lBytesToFloat(byte[] b) {
        Validate.isTrue(b.length == 4);

        int intBits = Long.valueOf(bytesToHexString(bytesReverseOrder(b), 4), 16).intValue();
//        Float f = new Float(0.0);
//        int i = ((((b[3] & 0xff) << 8 | (b[2] & 0xff)) << 8) | (b[1] & 0xff)) << 8 | (b[0] & 0xff);
        return Float.intBitsToFloat(intBits);
    }

    /**
     * converts float to low byte before, byte array after high BYTE
     */
    public static byte[] toLH(float f) {
        return toLH(Float.floatToRawIntBits(f));
    }

    /**
     * Float is converted to high byte before, byte array after low byte
     */
    public static byte[] toHH(float f) {
        return toHH(Float.floatToRawIntBits(f));
    }

    /**
     * Converts a float value to a byte order inversion corresponding float
     *
     * @param f float
     * @return float
     */
    public static float reverseFloat(float f) {
        float result = hBytesToFloat(toLH(f));
        return result;
    }

    /**
     * Converts the value of the short type to a byte-order inversion corresponding to the short value
     *
     * @param s short
     * @return Short
     */
    public static short reverseShort(short s) {
        short result = hBytesToShort(toLH(s));
        return result;
    }

    /**
     * converts int to low byte in front, high byte array
     *
     * @param n int
     * @return byte[]
     */
    public static byte[] toLH(int n) {
        byte[] b = new byte[4];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        b[2] = (byte) (n >> 16 & 0xff);
        b[3] = (byte) (n >> 24 & 0xff);
        return b;
    }

    /**
     * converts int to high byte before, byte array after low BYTE
     *
     * @param n int
     * @return byte[]
     */
    public static byte[] toHH(int n) {
        byte[] b = new byte[4];
        b[3] = (byte) (n & 0xff);
        b[2] = (byte) (n >> 8 & 0xff);
        b[1] = (byte) (n >> 16 & 0xff);
        b[0] = (byte) (n >> 24 & 0xff);
        return b;
    }

    /**
     * Convert short to low byte before, byte array after high byte
     *
     * @param n Short
     * @return byte[]
     */
    public static byte[] toLH(short n) {
        byte[] b = new byte[2];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        return b;
    }

    /**
     * Converts short to high byte before, byte array after low byte
     *
     * @param n Short
     * @return byte[]
     */
    public static byte[] toHH(short n) {
        byte[] b = new byte[2];
        b[1] = (byte) (n & 0xff);
        b[0] = (byte) (n >> 8 & 0xff);
        return b;
    }

    /**
     * Align the elements in the byte array in reverse order
     */
    public static byte[] bytesReverseOrder(byte[] b) {
        int length = b.length;
        byte[] result = new byte[length];
        for (int i = 0; i < length; i++) {
            result[length - i - 1] = b[i];
        }
        return result;
    }

    /**
     * Converts a high byte array to an int
     *
     * @param b byte[]
     * @return int
     */
    public static int hBytesToInt(byte[] b) {
        int s = 0;
        for (int i = 0; i < 3; i++) {
            if (b[i] >= 0) {
                s = s + b[i];
            } else {
                s = s + 256 + b[i];
            }
            s = s * 256;
        }
        if (b[3] >= 0) {
            s = s + b[3];
        } else {
            s = s + 256 + b[3];
        }
        return s;
    }

    /**
     * Converts a low byte array to an int
     *
     * @param b byte[]
     * @return int
     */
    public static int lBytesToInt(byte[] b) {
        int s = 0;
        for (int i = 0; i < 3; i++) {
            if (b[3 - i] >= 0) {
                s = s + b[3 - i];
            } else {
                s = s + 256 + b[3 - i];
            }
            s = s * 256;
        }
        if (b[0] >= 0) {
            s = s + b[0];
        } else {
            s = s + 256 + b[0];
        }
        return s;
    }

    /**
     * Converts a high byte array to a short
     *
     * @param b byte[]
     * @return short
     */
    public static short hBytesToShort(byte[] b) {
        short s = 0;
        if (b[0] >= 0) {
            s = (short) (s + b[0]);
        } else {
            s = (short) (s + 256 + b[0]);
        }
        s = (short) (s * 256);

        if (b[1] >= 0) {
            s = (short) (s + b[1]);
        } else {
            s = (short) (s + 256 + b[1]);
        }
        return s;
    }
}
