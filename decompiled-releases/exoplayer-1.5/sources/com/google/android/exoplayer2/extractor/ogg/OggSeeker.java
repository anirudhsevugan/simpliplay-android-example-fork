package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.SeekMap;

interface OggSeeker {
    long a(ExtractorInput extractorInput);

    SeekMap a();

    void a(long j);
}
