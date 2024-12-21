package com.google.android.exoplayer2.extractor.jpeg;

import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.extractor.TrackOutput;

public final class StartOffsetExtractorOutput implements ExtractorOutput {
    /* access modifiers changed from: private */
    public final long b;
    private final ExtractorOutput c;

    public StartOffsetExtractorOutput(long j, ExtractorOutput extractorOutput) {
        this.b = j;
        this.c = extractorOutput;
    }

    public final TrackOutput a(int i, int i2) {
        return this.c.a(i, i2);
    }

    public final void a(final SeekMap seekMap) {
        this.c.a(new SeekMap() {
            public final SeekMap.SeekPoints a(long j) {
                SeekMap.SeekPoints a2 = seekMap.a(j);
                return new SeekMap.SeekPoints(new SeekPoint(a2.a.b, a2.a.c + StartOffsetExtractorOutput.this.b), new SeekPoint(a2.b.b, a2.b.c + StartOffsetExtractorOutput.this.b));
            }

            public final boolean a() {
                return seekMap.a();
            }

            public final long b() {
                return seekMap.b();
            }
        });
    }

    public final void c_() {
        this.c.c_();
    }
}
