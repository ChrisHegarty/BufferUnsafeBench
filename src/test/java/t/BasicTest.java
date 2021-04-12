package t;

import jdk.internal.misc.Unsafe;
import org.junit.Test;
import p.Buffers;
import p.Buffers.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import static org.junit.Assert.*;

public class BasicTest {

    @Test
    public void testBBOnHeap2Arg() {
        var buffer = Buffers.BBOnHeap2Arg.of(4);

        buffer.bigEndian(true);
        buffer.putInt(0, 0x01020304);
        byte[] ba = getByteArray(buffer);
        assertArrayEquals(new byte[] {01, 02, 03, 04}, ba);
        assertEquals(0x01020304, buffer.getInt(0));

        buffer.bigEndian(false);
        buffer.putInt(0, 0x01020304);
        ba = getByteArray(buffer);
        assertArrayEquals(new byte[] {04, 03, 02, 01}, ba);
        assertEquals(0x01020304, buffer.getInt(0));
    }

    @Test
    public void testBBOnHeap3Arg() {
        var buffer = Buffers.BBOnHeap3Arg.of(8);

        buffer.bigEndian(true);
        buffer.putInt(1, 0x01020304);
        byte[] ba = getByteArray(buffer);
        assertArrayEquals(new byte[] {01, 02, 03, 04}, Arrays.copyOfRange(ba, 1, 5));
        assertEquals(0x01020304, buffer.getInt(1));

        buffer.bigEndian(false);
        buffer.putInt(3, 0x01020304);
        ba = getByteArray(buffer);
        assertArrayEquals(new byte[] {04, 03, 02, 01}, Arrays.copyOfRange(ba, 3, 7));
        assertEquals(0x01020304, buffer.getInt(3));
    }

    @Test
    public void testBBDirect2Arg() {
        var buffer = Buffers.BBDirect2Arg.of(4);

        buffer.bigEndian(true);
        buffer.putInt(0, 0x04050607);
        byte b0 = getByte(addr(buffer), 0);
        byte b1 = getByte(addr(buffer), 1);
        byte b2 = getByte(addr(buffer), 2);
        byte b3 = getByte(addr(buffer), 3);
        assertArrayEquals(new byte[] {04, 05, 06, 07}, new byte[] {b0, b1, b2, b3});
        assertEquals(0x04050607, buffer.getInt(0));

        buffer.bigEndian(false);
        buffer.putInt(0, 0x04050607);
        b0 = getByte(addr(buffer), 0);
        b1 = getByte(addr(buffer), 1);
        b2 = getByte(addr(buffer), 2);
        b3 = getByte(addr(buffer), 3);
        assertArrayEquals(new byte[] {07, 06, 05, 04}, new byte[] {b0, b1, b2, b3});
        assertEquals(0x04050607, buffer.getInt(0));
    }

    @Test
    public void testBBDirect3Arg() {
        var buffer = Buffers.BBDirect3Arg.of(10);

        buffer.bigEndian(true);
        buffer.putInt(6, 0x01030507);
        byte b0 = getByte(addr(buffer), 6);
        byte b1 = getByte(addr(buffer), 7);
        byte b2 = getByte(addr(buffer), 8);
        byte b3 = getByte(addr(buffer), 9);
        assertArrayEquals(new byte[] {01, 03, 05, 07}, new byte[] {b0, b1, b2, b3});
        assertEquals(0x01030507, buffer.getInt(6));

        buffer.bigEndian(false);
        buffer.putInt(0, 0x01030507);
        b0 = getByte(addr(buffer), 0);
        b1 = getByte(addr(buffer), 1);
        b2 = getByte(addr(buffer), 2);
        b3 = getByte(addr(buffer), 3);
        assertArrayEquals(new byte[] {07, 05, 03, 01}, new byte[] {b0, b1, b2, b3});
        assertEquals(0x01030507, buffer.getInt(0));
    }

    static byte getByte(long addr, int index) {
        return UNSAFE.getByte(null, addr + index);
    }

    private static final Unsafe UNSAFE = getUnsafe();

    static final Field BBOnHeap2Arg_BA;
    static final Field BBOnHeap3Arg_BA;
    static final Field BBDirect2Arg_ADDR;
    static final Field BBDirect3Arg_ADDR;

    static byte[] getByteArray(BBOnHeap2Arg obj) {
        try {
            return (byte[])BBOnHeap2Arg_BA.get(obj);
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException(e);
        }
    }

    static byte[] getByteArray(BBOnHeap3Arg obj) {
        try {
            return (byte[])BBOnHeap3Arg_BA.get(obj);
        } catch (Exception e) {
            throw new AssertionError();
        }
    }

    static long addr(BBDirect2Arg buffer) {
        try {
            return (long) BBDirect2Arg_ADDR.get(buffer);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    static long addr(BBDirect3Arg buffer) {
        try {
            return (long) BBDirect3Arg_ADDR.get(buffer);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    static {
        try {
            BBOnHeap2Arg_BA   = BBOnHeap2Arg.class.getDeclaredField("ba");
            BBOnHeap3Arg_BA   = BBOnHeap3Arg.class.getDeclaredField("ba");
            BBDirect2Arg_ADDR = BBDirect2Arg.class.getDeclaredField("addr");
            BBDirect3Arg_ADDR = BBDirect3Arg.class.getDeclaredField("addr");
            BBOnHeap2Arg_BA.setAccessible(true);
            BBOnHeap3Arg_BA.setAccessible(true);
            BBDirect2Arg_ADDR.setAccessible(true);
            BBDirect3Arg_ADDR.setAccessible(true);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    static Unsafe getUnsafe() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe)f.get(null);
        } catch (ReflectiveOperationException ex) {
            throw new IllegalStateException();
        }
    }
}
