package com.google.android.exoplayer2.extractor.mp3;

import com.google.android.exoplayer2.audio.MpegAudioUtil;
import com.google.android.exoplayer2.extractor.ConstantBitrateSeekMap;

final class ConstantBitrateSeeker extends ConstantBitrateSeekMap implements Seeker {
    public ConstantBitrateSeeker(long j, long j2, MpegAudioUtil.Header header) {
        super(j, j2, header.f, header.c);
    }

    public final long c() {
        return -1;
    }

    public final long c(long j) {
        return b(j);
    }
}
