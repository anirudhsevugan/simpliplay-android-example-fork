package com.google.android.exoplayer2.source.chunk;

import java.util.NoSuchElementException;

public interface MediaChunkIterator {
    public static final MediaChunkIterator a = new MediaChunkIterator() {
        public final boolean a() {
            return false;
        }

        public final long d() {
            throw new NoSuchElementException();
        }

        public final long e() {
            throw new NoSuchElementException();
        }
    };

    boolean a();

    long d();

    long e();
}
