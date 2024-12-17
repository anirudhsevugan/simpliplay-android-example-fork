package com.google.android.exoplayer2.decoder;

import gnu.expr.Declaration;
import java.nio.ByteBuffer;

public class DecoderInputBuffer extends Buffer {
    public final CryptoInfo b;
    public ByteBuffer c;
    public boolean d;
    public long e;
    public ByteBuffer f;
    private final int g;

    public final class InsufficientCapacityException extends IllegalStateException {
        public InsufficientCapacityException(int i, int i2) {
            super(new StringBuilder(44).append("Buffer too small (").append(i).append(" < ").append(i2).append(")").toString());
        }
    }

    public DecoderInputBuffer(int i) {
        this(i, (byte) 0);
    }

    private DecoderInputBuffer(int i, byte b2) {
        this.b = new CryptoInfo();
        this.g = i;
    }

    private ByteBuffer e(int i) {
        int i2 = this.g;
        if (i2 == 1) {
            return ByteBuffer.allocate(i);
        }
        if (i2 == 2) {
            return ByteBuffer.allocateDirect(i);
        }
        ByteBuffer byteBuffer = this.c;
        throw new InsufficientCapacityException(byteBuffer == null ? 0 : byteBuffer.capacity(), i);
    }

    public static DecoderInputBuffer f() {
        return new DecoderInputBuffer(0);
    }

    public void a() {
        super.a();
        ByteBuffer byteBuffer = this.c;
        if (byteBuffer != null) {
            byteBuffer.clear();
        }
        ByteBuffer byteBuffer2 = this.f;
        if (byteBuffer2 != null) {
            byteBuffer2.clear();
        }
        this.d = false;
    }

    public final void c(int i) {
        ByteBuffer byteBuffer = this.f;
        if (byteBuffer == null || byteBuffer.capacity() < i) {
            this.f = ByteBuffer.allocate(i);
        } else {
            this.f.clear();
        }
    }

    public final void d(int i) {
        ByteBuffer byteBuffer = this.c;
        if (byteBuffer == null) {
            this.c = e(i);
            return;
        }
        int capacity = byteBuffer.capacity();
        int position = byteBuffer.position();
        int i2 = i + position;
        if (capacity >= i2) {
            this.c = byteBuffer;
            return;
        }
        ByteBuffer e2 = e(i2);
        e2.order(byteBuffer.order());
        if (position > 0) {
            byteBuffer.flip();
            e2.put(byteBuffer);
        }
        this.c = e2;
    }

    public final boolean g() {
        return b(Declaration.MODULE_REFERENCE);
    }

    public final void h() {
        ByteBuffer byteBuffer = this.c;
        if (byteBuffer != null) {
            byteBuffer.flip();
        }
        ByteBuffer byteBuffer2 = this.f;
        if (byteBuffer2 != null) {
            byteBuffer2.flip();
        }
    }
}
