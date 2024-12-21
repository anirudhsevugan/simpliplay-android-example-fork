package com.google.android.exoplayer2.extractor.flac;

import com.google.android.exoplayer2.extractor.BinarySearchSeeker;
import com.google.android.exoplayer2.extractor.FlacStreamMetadata;

final /* synthetic */ class FlacBinarySearchSeeker$$Lambda$0 implements BinarySearchSeeker.SeekTimestampConverter {
    private final FlacStreamMetadata a;

    private FlacBinarySearchSeeker$$Lambda$0(FlacStreamMetadata flacStreamMetadata) {
        this.a = flacStreamMetadata;
    }

    static BinarySearchSeeker.SeekTimestampConverter a(FlacStreamMetadata flacStreamMetadata) {
        return new FlacBinarySearchSeeker$$Lambda$0(flacStreamMetadata);
    }

    public final long a(long j) {
        return this.a.a(j);
    }
}
