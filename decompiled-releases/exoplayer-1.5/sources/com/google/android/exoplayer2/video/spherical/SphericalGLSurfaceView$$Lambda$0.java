package com.google.android.exoplayer2.video.spherical;

import android.view.Surface;
import com.google.android.exoplayer2.video.spherical.SphericalGLSurfaceView;
import java.util.Iterator;

final /* synthetic */ class SphericalGLSurfaceView$$Lambda$0 implements Runnable {
    private final SphericalGLSurfaceView a;

    SphericalGLSurfaceView$$Lambda$0(SphericalGLSurfaceView sphericalGLSurfaceView) {
        this.a = sphericalGLSurfaceView;
    }

    public final void run() {
        SphericalGLSurfaceView sphericalGLSurfaceView = this.a;
        Surface surface = sphericalGLSurfaceView.d;
        if (surface != null) {
            Iterator it = sphericalGLSurfaceView.a.iterator();
            while (it.hasNext()) {
                ((SphericalGLSurfaceView.VideoSurfaceListener) it.next()).e();
            }
        }
        SphericalGLSurfaceView.a(sphericalGLSurfaceView.c, surface);
        sphericalGLSurfaceView.c = null;
        sphericalGLSurfaceView.d = null;
    }
}
