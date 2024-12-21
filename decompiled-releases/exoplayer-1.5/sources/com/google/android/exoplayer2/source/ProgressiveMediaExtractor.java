package com.google.android.exoplayer2.source;

import android.net.Uri;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.upstream.DataReader;
import java.util.Map;

public interface ProgressiveMediaExtractor {

    public interface Factory {
        ProgressiveMediaExtractor a();
    }

    int a(PositionHolder positionHolder);

    void a();

    void a(long j, long j2);

    void a(DataReader dataReader, Uri uri, Map map, long j, long j2, ExtractorOutput extractorOutput);

    void b();

    long c();
}
