package p;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import jdk.internal.misc.Unsafe;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

/**
 * Benchmark to help inspect and understand potential pollution issues when using Unsafe primitives.
 * The benchmark only tests 4-byte/int primitives, but other widths are likely similar.
 *
 *  BBOnHeap2Arg - models a ByteBuffer backed by an on-heap byte[], uses 2-arg Unsafe primitives.
 *  BBDirect2Arg - models a ByteBuffer backed by off-heap memory,   uses 2-arg Unsafe primitives.
 *  BBOnHeap3Arg - models a ByteBuffer backed by an on-heap byte[], uses 3-arg Unsafe primitives.
 *  BBDirect3Arg - models a ByteBuffer backed by off-heap memory,   uses 3-arg Unsafe primitives.
 *
 *  2-arg Unsafe primitives do not offer byte swapping (swapping is done in user-code).
 *  3-arg Unsafe primitives offer byte swapping in their API (third arg)
 */
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 5, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Fork(value = 3, jvmArgsAppend = { "--add-opens", "java.base/jdk.internal.misc=ALL-UNNAMED" })
public class BufferUnsafeBench {

    static final Unsafe UNSAFE = getUnsafe();

    @Param(value = {"false", "true"})
    boolean pollute;

    int size;

    Buffers.BBOnHeap2Arg bbOnHeap2Arg;
    Buffers.BBDirect2Arg bbDirect2Arg;
    Buffers.BBOnHeap3Arg bbOnHeap3Arg;
    Buffers.BBDirect3Arg bbDirect3Arg;

    @Setup
    public void setup() {
        size = 8192;   // 8K buffer sizes.

        bbOnHeap2Arg = Buffers.BBOnHeap2Arg.of(size);
        bbOnHeap3Arg = Buffers.BBOnHeap3Arg.of(size);
        bbDirect2Arg = Buffers.BBDirect2Arg.of(size);
        bbDirect3Arg = Buffers.BBDirect3Arg.of(size);

        if (pollute) {
            int r = 0;
            final int len = size;
            for (int j=0; j<5; j++) {
                for (boolean big : new boolean[] {true, false} ) {
                    bbOnHeap2Arg.bigEndian(big);
                    bbDirect2Arg.bigEndian(big);
                    bbOnHeap3Arg.bigEndian(big);
                    bbDirect3Arg.bigEndian(big);
                    for (int i = 0; i < len; i += 4) {
                        // effective loop bodies of the benches
                        bbOnHeap2Arg.putInt(i, i);
                        r += bbOnHeap2Arg.getInt(i);
                        bbDirect2Arg.putInt(i, i);
                        r += bbDirect2Arg.getInt(i);
                        bbOnHeap3Arg.putInt(i, i);
                        r += bbOnHeap3Arg.getInt(i);
                        bbDirect3Arg.putInt(i, i);
                        r += bbDirect3Arg.getInt(i);
                    }
                }
            }
            // reset to original values.
            bbOnHeap2Arg.bigEndian(false);
            bbDirect2Arg.bigEndian(false);
            bbOnHeap3Arg.bigEndian(false);
            bbDirect3Arg.bigEndian(false);
        }
    }

    @Benchmark
    public int testOnHeap2Arg() {
        int r = 0;
        final var buffer = bbOnHeap2Arg;
        final int len = size;
        for (int i=0; i<len; i+=4) {
            buffer.putInt(i, i);
            r += buffer.getInt(i);
        }
        return r;
    }

    @Benchmark
    public int testDirect2Arg() {
        int r = 0;
        final var buffer = bbDirect2Arg;
        final int len = size;
        for (int i=0; i<len; i+=4) {
            buffer.putInt(i, i);
            r += buffer.getInt(i);
        }
        return r;
    }

    @Benchmark
    public int testOnHeap3Arg() {
        int r = 0;
        final var buffer = bbOnHeap3Arg;
        final int len = size;
        for (int i=0; i<len; i+=4) {
            buffer.putInt(i, i);
            r += buffer.getInt(i);
        }
        return r;
    }

    @Benchmark
    public int testDirect3Arg() {
        int r = 0;
        final var buffer = bbDirect3Arg;
        final int len = size;
        for (int i=0; i<len; i+=4) {
            buffer.putInt(i, i);
            r += buffer.getInt(i);
        }
        return r;
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
