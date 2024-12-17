package com.google.android.exoplayer2.source.hls;

import android.net.Uri;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import java.util.List;
import java.util.Map;

public interface HlsExtractorFactory {
    public static final HlsExtractorFactory a = new DefaultHlsExtractorFactory();

    HlsMediaChunkExtractor a(Uri uri, Format format, List list, TimestampAdjuster timestampAdjuster, Map map, ExtractorInput extractorInput);
}
