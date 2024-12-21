package com.google.android.exoplayer2.mediacodec;

import android.media.MediaCodec;
import com.google.android.exoplayer2.mediacodec.MediaCodecAdapter;

final /* synthetic */ class SynchronousMediaCodecAdapter$$Lambda$0 implements MediaCodec.OnFrameRenderedListener {
    private final MediaCodecAdapter.OnFrameRenderedListener a;

    SynchronousMediaCodecAdapter$$Lambda$0(MediaCodecAdapter.OnFrameRenderedListener onFrameRenderedListener) {
        this.a = onFrameRenderedListener;
    }

    public final void onFrameRendered(MediaCodec mediaCodec, long j, long j2) {
        this.a.a(j);
    }
}
