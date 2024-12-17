package com.google.android.exoplayer2.drm;

import android.media.MediaDrm;
import com.google.android.exoplayer2.drm.ExoMediaDrm;

final /* synthetic */ class FrameworkMediaDrm$$Lambda$0 implements MediaDrm.OnEventListener {
    private final ExoMediaDrm.OnEventListener a;

    FrameworkMediaDrm$$Lambda$0(ExoMediaDrm.OnEventListener onEventListener) {
        this.a = onEventListener;
    }

    public final void onEvent(MediaDrm mediaDrm, byte[] bArr, int i, int i2, byte[] bArr2) {
        this.a.a(bArr, i);
    }
}
