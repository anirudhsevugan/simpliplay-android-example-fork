package com.google.android.exoplayer2.mediacodec;

import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;
import java.util.Comparator;

final /* synthetic */ class MediaCodecUtil$$Lambda$3 implements Comparator {
    private final MediaCodecUtil.ScoreProvider a;

    MediaCodecUtil$$Lambda$3(MediaCodecUtil.ScoreProvider scoreProvider) {
        this.a = scoreProvider;
    }

    public final int compare(Object obj, Object obj2) {
        return MediaCodecUtil.a(this.a, obj, obj2);
    }
}
