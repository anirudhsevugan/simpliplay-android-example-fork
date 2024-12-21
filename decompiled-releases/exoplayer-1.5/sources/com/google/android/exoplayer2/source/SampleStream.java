package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;

public interface SampleStream {
    int a(long j);

    int a(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i);

    boolean a();

    void b();
}
