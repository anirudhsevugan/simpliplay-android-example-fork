package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.DefaultExtractorInput;
import com.google.android.exoplayer2.source.chunk.ChunkExtractor;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Util;

public final class InitializationChunk extends Chunk {
    ChunkExtractor.TrackOutputProvider a;
    private final ChunkExtractor b;
    private long c;
    private volatile boolean d;

    public InitializationChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i, Object obj, ChunkExtractor chunkExtractor) {
        super(dataSource, dataSpec, 2, format, i, (Object) null, -9223372036854775807L, -9223372036854775807L);
        this.b = chunkExtractor;
    }

    public final void a() {
        this.d = true;
    }

    public final void b() {
        DefaultExtractorInput defaultExtractorInput;
        if (this.c == 0) {
            this.b.a(this.a, -9223372036854775807L, -9223372036854775807L);
        }
        try {
            DataSpec a2 = this.e.a(this.c);
            defaultExtractorInput = new DefaultExtractorInput(this.l, a2.f, this.l.a(a2));
            do {
                if (this.d || !this.b.a(defaultExtractorInput)) {
                    break;
                }
                break;
                break;
            } while (!this.b.a(defaultExtractorInput));
            break;
            this.c = defaultExtractorInput.c() - this.e.f;
            Util.a((DataSource) this.l);
        } catch (Throwable th) {
            Util.a((DataSource) this.l);
            throw th;
        }
    }
}
