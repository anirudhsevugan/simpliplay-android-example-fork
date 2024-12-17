package com.google.android.exoplayer2.source.chunk;

import android.util.SparseArray;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.extractor.DummyTrackOutput;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.TrackOutput$$CC;
import com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor;
import com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor;
import com.google.android.exoplayer2.extractor.rawcc.RawCcExtractor;
import com.google.android.exoplayer2.source.chunk.ChunkExtractor;
import com.google.android.exoplayer2.upstream.DataReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;
import java.util.List;

public final class BundledChunkExtractor implements ExtractorOutput, ChunkExtractor {
    public static final ChunkExtractor.Factory b = BundledChunkExtractor$$Lambda$0.a;
    private static final PositionHolder c = new PositionHolder();
    private final Extractor d;
    private final int e;
    private final Format f;
    private final SparseArray g = new SparseArray();
    private boolean h;
    private ChunkExtractor.TrackOutputProvider i;
    private long j;
    private SeekMap k;
    private Format[] l;

    final class BindingTrackOutput implements TrackOutput {
        public Format a;
        private final int b;
        private final Format c;
        private final DummyTrackOutput d = new DummyTrackOutput();
        private TrackOutput e;
        private long f;

        public BindingTrackOutput(int i, Format format) {
            this.b = i;
            this.c = format;
        }

        public final int a(DataReader dataReader, int i, boolean z) {
            return ((TrackOutput) Util.a((Object) this.e)).b(dataReader, i, z);
        }

        public final void a(long j, int i, int i2, int i3, TrackOutput.CryptoData cryptoData) {
            long j2 = this.f;
            if (j2 != -9223372036854775807L && j >= j2) {
                this.e = this.d;
            }
            ((TrackOutput) Util.a((Object) this.e)).a(j, i, i2, i3, cryptoData);
        }

        public final void a(Format format) {
            Format format2 = this.c;
            if (format2 != null) {
                format = format.a(format2);
            }
            this.a = format;
            ((TrackOutput) Util.a((Object) this.e)).a(this.a);
        }

        public final void a(ChunkExtractor.TrackOutputProvider trackOutputProvider, long j) {
            if (trackOutputProvider == null) {
                this.e = this.d;
                return;
            }
            this.f = j;
            TrackOutput a2 = trackOutputProvider.a(this.b);
            this.e = a2;
            Format format = this.a;
            if (format != null) {
                a2.a(format);
            }
        }

        public final void a(ParsableByteArray parsableByteArray, int i) {
            ((TrackOutput) Util.a((Object) this.e)).b(parsableByteArray, i);
        }

        public final int b(DataReader dataReader, int i, boolean z) {
            return TrackOutput$$CC.a(this, dataReader, i, z);
        }

        public final void b(ParsableByteArray parsableByteArray, int i) {
            TrackOutput$$CC.a(this, parsableByteArray, i);
        }
    }

    private BundledChunkExtractor(Extractor extractor, int i2, Format format) {
        this.d = extractor;
        this.e = i2;
        this.f = format;
    }

    static final /* synthetic */ ChunkExtractor a(int i2, Format format, boolean z, List list, TrackOutput trackOutput) {
        Extractor extractor;
        String str = format.k;
        if (MimeTypes.c(str)) {
            if (!"application/x-rawcc".equals(str)) {
                return null;
            }
            extractor = new RawCcExtractor(format);
        } else if (MimeTypes.k(str)) {
            extractor = new MatroskaExtractor(1);
        } else {
            extractor = new FragmentedMp4Extractor(z ? 4 : 0, (TimestampAdjuster) null, list, trackOutput);
        }
        return new BundledChunkExtractor(extractor, i2, format);
    }

    public final TrackOutput a(int i2, int i3) {
        BindingTrackOutput bindingTrackOutput = (BindingTrackOutput) this.g.get(i2);
        if (bindingTrackOutput == null) {
            Assertions.b(this.l == null);
            bindingTrackOutput = new BindingTrackOutput(i3, i3 == this.e ? this.f : null);
            bindingTrackOutput.a(this.i, this.j);
            this.g.put(i2, bindingTrackOutput);
        }
        return bindingTrackOutput;
    }

    public final void a(SeekMap seekMap) {
        this.k = seekMap;
    }

    public final void a(ChunkExtractor.TrackOutputProvider trackOutputProvider, long j2, long j3) {
        this.i = trackOutputProvider;
        this.j = j3;
        if (!this.h) {
            this.d.a((ExtractorOutput) this);
            if (j2 != -9223372036854775807L) {
                this.d.a(0, j2);
            }
            this.h = true;
            return;
        }
        Extractor extractor = this.d;
        if (j2 == -9223372036854775807L) {
            j2 = 0;
        }
        extractor.a(0, j2);
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            ((BindingTrackOutput) this.g.valueAt(i2)).a(trackOutputProvider, j3);
        }
    }

    public final boolean a(ExtractorInput extractorInput) {
        int a = this.d.a(extractorInput, c);
        Assertions.b(a != 1);
        return a == 0;
    }

    public final ChunkIndex b() {
        SeekMap seekMap = this.k;
        if (seekMap instanceof ChunkIndex) {
            return (ChunkIndex) seekMap;
        }
        return null;
    }

    public final Format[] c() {
        return this.l;
    }

    public final void c_() {
        Format[] formatArr = new Format[this.g.size()];
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            formatArr[i2] = (Format) Assertions.a((Object) ((BindingTrackOutput) this.g.valueAt(i2)).a);
        }
        this.l = formatArr;
    }
}
