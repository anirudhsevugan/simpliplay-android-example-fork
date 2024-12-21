package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.drm.ExoMediaDrm;
import java.util.UUID;

final /* synthetic */ class FrameworkMediaDrm$$Lambda$3 implements ExoMediaDrm.Provider {
    static final ExoMediaDrm.Provider a = new FrameworkMediaDrm$$Lambda$3();

    private FrameworkMediaDrm$$Lambda$3() {
    }

    public final ExoMediaDrm a(UUID uuid) {
        return FrameworkMediaDrm.a(uuid);
    }
}
