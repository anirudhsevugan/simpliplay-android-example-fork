package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;

public final class EmptySampleStream implements SampleStream {
    public final int a(long j) {
        return 0;
    }

    public final int a(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i) {
        decoderInputBuffer.a = 4;
        return -4;
    }

    public final boolean a() {
        return true;
    }

    public final void b() {
    }
}
