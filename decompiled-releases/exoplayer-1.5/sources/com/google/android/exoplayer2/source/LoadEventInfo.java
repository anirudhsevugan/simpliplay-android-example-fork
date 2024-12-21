package com.google.android.exoplayer2.source;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicLong;

public final class LoadEventInfo {
    private static final AtomicLong a = new AtomicLong();

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public LoadEventInfo() {
        this((byte) 0);
        Collections.emptyMap();
    }

    public LoadEventInfo(byte b) {
    }

    public static long a() {
        return a.getAndIncrement();
    }
}
