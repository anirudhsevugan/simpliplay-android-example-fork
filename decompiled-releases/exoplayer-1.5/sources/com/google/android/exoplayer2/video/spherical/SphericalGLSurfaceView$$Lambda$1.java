package com.google.android.exoplayer2.video.spherical;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import com.google.android.exoplayer2.video.spherical.SphericalGLSurfaceView;
import java.util.Iterator;

final /* synthetic */ class SphericalGLSurfaceView$$Lambda$1 implements Runnable {
    private final SphericalGLSurfaceView a;
    private final SurfaceTexture b;

    SphericalGLSurfaceView$$Lambda$1(SphericalGLSurfaceView sphericalGLSurfaceView, SurfaceTexture surfaceTexture) {
        this.a = sphericalGLSurfaceView;
        this.b = surfaceTexture;
    }

    public final void run() {
        SphericalGLSurfaceView sphericalGLSurfaceView = this.a;
        SurfaceTexture surfaceTexture = this.b;
        SurfaceTexture surfaceTexture2 = sphericalGLSurfaceView.c;
        Surface surface = sphericalGLSurfaceView.d;
        Surface surface2 = new Surface(surfaceTexture);
        sphericalGLSurfaceView.c = surfaceTexture;
        sphericalGLSurfaceView.d = surface2;
        Iterator it = sphericalGLSurfaceView.a.iterator();
        while (it.hasNext()) {
            ((SphericalGLSurfaceView.VideoSurfaceListener) it.next()).a(surface2);
        }
        SphericalGLSurfaceView.a(surfaceTexture2, surface);
    }
}
