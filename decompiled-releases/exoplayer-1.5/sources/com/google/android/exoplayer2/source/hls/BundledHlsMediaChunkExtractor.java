package com.google.android.exoplayer2.source.hls;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.mp3.Mp3Extractor;
import com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor;
import com.google.android.exoplayer2.extractor.ts.Ac3Extractor;
import com.google.android.exoplayer2.extractor.ts.Ac4Extractor;
import com.google.android.exoplayer2.extractor.ts.AdtsExtractor;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.TimestampAdjuster;

public final class BundledHlsMediaChunkExtractor implements HlsMediaChunkExtractor {
    private static final PositionHolder a = new PositionHolder();
    private Extractor b;
    private final Format c;
    private final TimestampAdjuster d;

    public BundledHlsMediaChunkExtractor(Extractor extractor, Format format, TimestampAdjuster timestampAdjuster) {
        this.b = extractor;
        this.c = format;
        this.d = timestampAdjuster;
    }

    public final void a(ExtractorOutput extractorOutput) {
        this.b.a(extractorOutput);
    }

    public final boolean a() {
        Extractor extractor = this.b;
        return (extractor instanceof AdtsExtractor) || (extractor instanceof Ac3Extractor) || (extractor instanceof Ac4Extractor) || (extractor instanceof Mp3Extractor);
    }

    public final boolean a(ExtractorInput extractorInput) {
        return this.b.a(extractorInput, a) == 0;
    }

    public final boolean b() {
        Extractor extractor = this.b;
        return (extractor instanceof TsExtractor) || (extractor instanceof FragmentedMp4Extractor);
    }

    public final HlsMediaChunkExtractor c() {
        Extractor extractor;
        Assertions.b(!b());
        Extractor extractor2 = this.b;
        if (extractor2 instanceof WebvttExtractor) {
            extractor = new WebvttExtractor(this.c.c, this.d);
        } else if (extractor2 instanceof AdtsExtractor) {
            extractor = new AdtsExtractor();
        } else if (extractor2 instanceof Ac3Extractor) {
            extractor = new Ac3Extractor();
        } else if (extractor2 instanceof Ac4Extractor) {
            extractor = new Ac4Extractor();
        } else if (extractor2 instanceof Mp3Extractor) {
            extractor = new Mp3Extractor();
        } else {
            String valueOf = String.valueOf(this.b.getClass().getSimpleName());
            throw new IllegalStateException(valueOf.length() != 0 ? "Unexpected extractor type for recreation: ".concat(valueOf) : new String("Unexpected extractor type for recreation: "));
        }
        return new BundledHlsMediaChunkExtractor(extractor, this.c, this.d);
    }

    public final void d() {
        this.b.a(0, 0);
    }
}
