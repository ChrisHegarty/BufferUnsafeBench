package p;

import java.lang.reflect.Field;
import jdk.internal.misc.Unsafe;

public class Buffers {

    private static final Unsafe UNSAFE = getUnsafe();
    private static final int ARRAY_BASE_OFFSET = UNSAFE.arrayBaseOffset(byte[].class);

    // true iff the native order is big-endian
    private static final boolean NORD_IS_BIG = UNSAFE.isBigEndian();
    private static int swap(boolean big, int n) {
        return big == NORD_IS_BIG ? n : Integer.reverseBytes(n);
    }

    /** Models a Buffer backed by an on-heap byte[], uses 2-arg Unsafe primitives. */
    public static class BBOnHeap2Arg implements Buffer {
        private final byte[] ba;
        private boolean bigEndian;
        private BBOnHeap2Arg(int size) { ba = new byte[size]; }
        public static BBOnHeap2Arg of(int size) { return new BBOnHeap2Arg(size); }
        @Override
        public void bigEndian(boolean big) {
            bigEndian = big;
        }
        @Override
        public int getInt(int index) {
            return swap(bigEndian, UNSAFE.getIntUnaligned(ba, ARRAY_BASE_OFFSET + index));
        }
        public void putInt(int index, int value) {
            UNSAFE.putIntUnaligned(ba, ARRAY_BASE_OFFSET + index, swap(bigEndian, value));
        }
    }

    /** Models a Buffer backed by an on-heap byte[], uses 3-arg Unsafe primitives. */
    public static class BBOnHeap3Arg implements Buffer {
        private final byte[] ba;
        private boolean bigEndian;
        private BBOnHeap3Arg(int size) { ba = new byte[size]; }
        public static BBOnHeap3Arg of(int size) { return new BBOnHeap3Arg(size); }
        @Override
        public void bigEndian(boolean big) {
            bigEndian = big;
        }
        @Override
        public int getInt(int index) {
            return UNSAFE.getIntUnaligned(ba, ARRAY_BASE_OFFSET + index, bigEndian);
        }
        public void putInt(int index, int value) {
            UNSAFE.putIntUnaligned(ba, ARRAY_BASE_OFFSET + index, value, bigEndian);
        }
    }

    /** Models a Buffer backed by off-heap memory, uses 2-arg Unsafe primitives. */
    public static class BBDirect2Arg implements Buffer {
        private final long addr;
        private boolean bigEndian;
        private BBDirect2Arg(int size) { addr = UNSAFE.allocateMemory(size); }
        public static BBDirect2Arg of(int size) { return new BBDirect2Arg(size); }
        @Override
        public void bigEndian(boolean big) {
            bigEndian = big;
        }
        @Override
        public int getInt(int index) {
            return swap(bigEndian, UNSAFE.getIntUnaligned(null, addr + index));
        }
        public void putInt(int index, int value) {
            UNSAFE.putIntUnaligned(null, addr + index, swap(bigEndian, value));
        }
    }

    /** Models a Buffer backed by off-heap memory, uses 3-arg Unsafe primitives. */
    public static class BBDirect3Arg implements Buffer {
        private final long addr;
        private boolean bigEndian;
        private BBDirect3Arg(int size) { addr = UNSAFE.allocateMemory(size); }
        public static BBDirect3Arg of(int size) { return new BBDirect3Arg(size); }
        @Override
        public void bigEndian(boolean big) {
            bigEndian = big;
        }
        @Override
        public int getInt(int index) {
            return UNSAFE.getIntUnaligned(null, addr + index, bigEndian);
        }
        public void putInt(int index, int value) {
            UNSAFE.putIntUnaligned(null, addr + index, value, bigEndian);
        }
    }

    private static Unsafe getUnsafe() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe)f.get(null);
        } catch (ReflectiveOperationException ex) {
            throw new IllegalStateException();
        }
    }
}
