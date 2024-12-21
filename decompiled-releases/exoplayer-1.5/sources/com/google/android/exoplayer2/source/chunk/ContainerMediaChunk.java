package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.DefaultExtractorInput;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Util;

public class ContainerMediaChunk extends BaseMediaChunk {
    private final int n;
    private final long o;
    private final ChunkExtractor p;
    private long q;
    private volatile boolean r;
    private boolean s;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ContainerMediaChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i, Object obj, long j, long j2, long j3, long j4, long j5, int i2, long j6, ChunkExtractor chunkExtractor) {
        super(dataSource, dataSpec, format, i, (Object) null, j, j2, j3, j4, j5);
        this.n = i2;
        this.o = j6;
        this.p = chunkExtractor;
    }

    public final void a() {
        this.r = true;
    }

    public final void b() {
        DefaultExtractorInput defaultExtractorInput;
        if (this.q == 0) {
            BaseMediaChunkOutput c = c();
            c.a(this.o);
            this.p.a(c, this.a == -9223372036854775807L ? -9223372036854775807L : this.a - this.o, this.b == -9223372036854775807L ? -9223372036854775807L : this.b - this.o);
        }
        try {
            DataSpec a = this.e.a(this.q);
            defaultExtractorInput = new DefaultExtractorInput(this.l, a.f, this.l.a(a));
            do {
                if (this.r || !this.p.a(defaultExtractorInput)) {
                    break;
                }
                break;
                break;
            } while (!this.p.a(defaultExtractorInput));
            break;
            this.q = defaultExtractorInput.c() - this.e.f;
            Util.a((DataSource) this.l);
            this.s = !this.r;
        } catch (Throwable th) {
            Util.a((DataSource) this.l);
            throw th;
        }
    }

    public final long g() {
        return this.m + ((long) this.n);
    }

    public final boolean h() {
        return this.s;
    }
}
