package com.google.android.exoplayer2.source.chunk;

import androidx.appcompat.widget.ActivityChooserView;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.DefaultExtractorInput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Util;

public final class SingleSampleMediaChunk extends BaseMediaChunk {
    private final int n;
    private final Format o;
    private long p;
    private boolean q;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SingleSampleMediaChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i, Object obj, long j, long j2, long j3, int i2, Format format2) {
        super(dataSource, dataSpec, format, i, (Object) null, j, j2, -9223372036854775807L, -9223372036854775807L, j3);
        this.n = i2;
        this.o = format2;
    }

    public final void a() {
    }

    /* JADX INFO: finally extract failed */
    public final void b() {
        BaseMediaChunkOutput c = c();
        c.a(0);
        TrackOutput a = c.a(this.n);
        a.a(this.o);
        try {
            long a2 = this.l.a(this.e.a(this.p));
            if (a2 != -1) {
                a2 += this.p;
            }
            DefaultExtractorInput defaultExtractorInput = new DefaultExtractorInput(this.l, this.p, a2);
            for (int i = 0; i != -1; i = a.b(defaultExtractorInput, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, true)) {
                this.p += (long) i;
            }
            a.a(this.j, 1, (int) this.p, 0, (TrackOutput.CryptoData) null);
            Util.a((DataSource) this.l);
            this.q = true;
        } catch (Throwable th) {
            Util.a((DataSource) this.l);
            throw th;
        }
    }

    public final boolean h() {
        return this.q;
    }
}
