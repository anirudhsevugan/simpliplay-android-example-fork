package com.google.android.exoplayer2.video.spherical;

import android.graphics.SurfaceTexture;

final /* synthetic */ class SceneRenderer$$Lambda$0 implements SurfaceTexture.OnFrameAvailableListener {
    private final SceneRenderer a;

    SceneRenderer$$Lambda$0(SceneRenderer sceneRenderer) {
        this.a = sceneRenderer;
    }

    public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.a.a.set(true);
    }
}
