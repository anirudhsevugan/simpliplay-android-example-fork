package com.google.android.exoplayer2.source.hls;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;

public interface HlsMediaChunkExtractor {
    void a(ExtractorOutput extractorOutput);

    boolean a();

    boolean a(ExtractorInput extractorInput);

    boolean b();

    HlsMediaChunkExtractor c();

    void d();
}
