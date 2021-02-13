package com.itestra.gc4connect.message;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GC4MessageTest {

    @Test
    void tolh() {
        byte[] bytes = GC4Message.toLH(1);
        byte[] expected = {0x01, 0x00, 0x00, 0x00};
        assertArrayEquals(expected, bytes);
    }

    @Test
    void tohh() {
        byte[] bytes = GC4Message.toHH(1);
        byte[] expected = {0x00, 0x00, 0x00, 0x01};
        assertArrayEquals(expected, bytes);
    }

    @Test
    void testTolh() {
        byte[] bytes = GC4Message.toLH((short) 1);
        byte[] expected = {0x01, 0x00};
        assertArrayEquals(expected, bytes);
    }

    @Test
    void testTohh() {
        byte[] bytes = GC4Message.toHH((short) 1);
        byte[] expected = {0x00, 0x01};
        assertArrayEquals(expected, bytes);
    }

    @Test
    void bytesReverseOrder() {
        byte[] bytes = {(byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04};
        byte[] bytesReversed = GC4Message.bytesReverseOrder(bytes);
        byte[] bytesExpected = {(byte) 0x04, (byte) 0x03, (byte) 0x02, (byte) 0x01};
        assertArrayEquals(bytesExpected, bytesReversed);
    }

    @Test
    void hBytesToInt1() {
        byte[] bytes = {0x00, 0x00, 0x00, 0x01};
        int intValue = GC4Message.hBytesToInt(bytes);
        assertEquals(1, intValue);
    }

    @Test
    void hBytesToInt16777216() {
        byte[] bytes = {0x01, 0x00, 0x00, 0x00};
        int intValue = GC4Message.hBytesToInt(bytes);
        assertEquals(16777216, intValue);
    }

    @Test
    void hBytesToIntNeg1() {
        byte[] bytes = {(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff};
        int intValue = GC4Message.hBytesToInt(bytes);
        assertEquals(-1, intValue);
    }

    @Test
    void hBytesToInt2147483647() {
        byte[] bytes = {(byte) 0x7f, (byte) 0xff, (byte) 0xff, (byte) 0xff};
        int intValue = GC4Message.hBytesToInt(bytes);
        assertEquals(2147483647, intValue);
    }

    @Test
    void hBytesToIntNeg129() {
        byte[] bytes = {(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x7f};
        int intValue = GC4Message.hBytesToInt(bytes);
        assertEquals(-129, intValue);
    }

    @Test
    void hBytesToFloatPositiveInfinity() {
        byte[] bytes = {(byte) 0x7f, (byte) 0x80, (byte) 0x00, (byte) 0x00};
        float f = GC4Message.hBytesToFloat(bytes);
        assertEquals(Float.POSITIVE_INFINITY, f);
    }

    @Test
    void hBytesToFloatNegativeInfinity() {
        byte[] bytes = {(byte) 0xff, (byte) 0x80, (byte) 0x00, (byte) 0x00};
        float f = GC4Message.hBytesToFloat(bytes);
        assertEquals(Float.NEGATIVE_INFINITY, f);
    }

    @Test
    void hBytesToFloatNaN() {
        byte[] bytes = {(byte) 0x7f, (byte) 0xff, (byte) 0xff, (byte) 0xff};
        float f = GC4Message.hBytesToFloat(bytes);
        assertEquals(Float.NaN, f);
    }

    @Test
    void lBytesToFloat() {
        byte[] bytes = {(byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0x7f};
        float f = GC4Message.lBytesToFloat(bytes);
        assertEquals(Float.POSITIVE_INFINITY, f);
    }

    @Test
    void lBytesToFloatNegativeInfinity() {
        byte[] bytes = {(byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0xff};
        float f = GC4Message.lBytesToFloat(bytes);
        assertEquals(Float.NEGATIVE_INFINITY, f);
    }

    @Test
    void lBytesToFloatNaN() {
        byte[] bytes = {(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x7f};
        float f = GC4Message.lBytesToFloat(bytes);
        assertEquals(Float.NaN, f);
    }

    @Test
    void lBytesToFloatMaxValue() {
        byte[] bytes = {(byte) 0xff, (byte) 0xff, (byte) 0x7f, (byte) 0x7f};
        float f = GC4Message.lBytesToFloat(bytes);
        assertEquals(Float.MAX_VALUE, f);
    }
}