package com.google.android.exoplayer2.extractor.jpeg;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ForwardingExtractorInput;
import com.google.android.exoplayer2.util.Assertions;

final class StartOffsetExtractorInput extends ForwardingExtractorInput {
    private final long a;

    public StartOffsetExtractorInput(ExtractorInput extractorInput, long j) {
        super(extractorInput);
        Assertions.a(extractorInput.c() >= j);
        this.a = j;
    }

    public final long b() {
        return super.b() - this.a;
    }

    public final long c() {
        return super.c() - this.a;
    }

    public final long d() {
        return super.d() - this.a;
    }
}
