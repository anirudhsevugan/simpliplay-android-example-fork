package com.google.android.exoplayer2.decoder;

public abstract class OutputBuffer extends Buffer {
    public long b;

    public interface Owner {
        void a(OutputBuffer outputBuffer);
    }

    public abstract void f();
}
