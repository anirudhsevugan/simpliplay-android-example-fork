package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import java.util.List;

public interface ChunkExtractor {

    public interface Factory {
        ChunkExtractor a(int i, Format format, boolean z, List list, TrackOutput trackOutput);
    }

    public interface TrackOutputProvider {
        TrackOutput a(int i);
    }

    void a(TrackOutputProvider trackOutputProvider, long j, long j2);

    boolean a(ExtractorInput extractorInput);

    ChunkIndex b();

    Format[] c();
}
