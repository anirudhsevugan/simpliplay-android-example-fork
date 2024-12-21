package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.source.chunk.ChunkExtractor;
import java.util.List;

final /* synthetic */ class BundledChunkExtractor$$Lambda$0 implements ChunkExtractor.Factory {
    static final ChunkExtractor.Factory a = new BundledChunkExtractor$$Lambda$0();

    private BundledChunkExtractor$$Lambda$0() {
    }

    public final ChunkExtractor a(int i, Format format, boolean z, List list, TrackOutput trackOutput) {
        return BundledChunkExtractor.a(i, format, z, list, trackOutput);
    }
}
