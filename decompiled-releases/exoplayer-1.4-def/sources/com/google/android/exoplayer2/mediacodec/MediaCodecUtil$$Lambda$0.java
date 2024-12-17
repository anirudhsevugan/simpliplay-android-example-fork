package com.google.android.exoplayer2.mediacodec;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;

final /* synthetic */ class MediaCodecUtil$$Lambda$0 implements MediaCodecUtil.ScoreProvider {
    private final Format a;

    MediaCodecUtil$$Lambda$0(Format format) {
        this.a = format;
    }

    public final int a(Object obj) {
        return MediaCodecUtil.a(this.a, (MediaCodecInfo) obj);
    }
}
