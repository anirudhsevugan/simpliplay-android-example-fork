package com.google.android.exoplayer2.video.spherical;

import android.graphics.SurfaceTexture;
import android.media.MediaFormat;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.TimedValueQueue;
import com.google.android.exoplayer2.video.VideoFrameMetadataListener;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

final class SceneRenderer implements VideoFrameMetadataListener, CameraMotionListener {
    final AtomicBoolean a = new AtomicBoolean();
    final AtomicBoolean b = new AtomicBoolean(true);
    final ProjectionRenderer c = new ProjectionRenderer();
    final FrameRotationQueue d = new FrameRotationQueue();
    final TimedValueQueue e = new TimedValueQueue();
    final TimedValueQueue f = new TimedValueQueue();
    final float[] g = new float[16];
    final float[] h = new float[16];
    int i;
    SurfaceTexture j;
    private volatile int k = 0;
    private int l = -1;
    private byte[] m;

    public final void a() {
        this.e.a();
        FrameRotationQueue frameRotationQueue = this.d;
        frameRotationQueue.c.a();
        frameRotationQueue.d = false;
        this.b.set(true);
    }

    public final void a(long j2, long j3, Format format, MediaFormat mediaFormat) {
        this.e.a(j3, (Object) Long.valueOf(j2));
        byte[] bArr = format.t;
        int i2 = format.u;
        byte[] bArr2 = this.m;
        int i3 = this.l;
        this.m = bArr;
        if (i2 == -1) {
            i2 = this.k;
        }
        this.l = i2;
        if (i3 != i2 || !Arrays.equals(bArr2, this.m)) {
            byte[] bArr3 = this.m;
            Projection a2 = bArr3 != null ? ProjectionDecoder.a(bArr3, this.l) : null;
            if (a2 == null || !ProjectionRenderer.a(a2)) {
                a2 = Projection.a(this.l);
            }
            this.f.a(j3, (Object) a2);
        }
    }

    public final void a(long j2, float[] fArr) {
        this.d.c.a(j2, (Object) fArr);
    }
}
