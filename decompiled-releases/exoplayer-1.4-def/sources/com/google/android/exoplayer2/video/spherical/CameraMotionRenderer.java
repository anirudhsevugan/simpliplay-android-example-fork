package com.google.android.exoplayer2.video.spherical;

import com.google.android.exoplayer2.BaseRenderer;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.RendererCapabilities$$CC;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;

public final class CameraMotionRenderer extends BaseRenderer {
    private final DecoderInputBuffer a = new DecoderInputBuffer(1);
    private final ParsableByteArray b = new ParsableByteArray();
    private long c;
    private CameraMotionListener d;
    private long e;

    public CameraMotionRenderer() {
        super(6);
    }

    private void A() {
        CameraMotionListener cameraMotionListener = this.d;
        if (cameraMotionListener != null) {
            cameraMotionListener.a();
        }
    }

    public final int a(Format format) {
        return RendererCapabilities$$CC.a("application/x-camera-motion".equals(format.l) ? 4 : 0);
    }

    public final void a(int i, Object obj) {
        if (i == 7) {
            this.d = (CameraMotionListener) obj;
        } else {
            super.a(i, obj);
        }
    }

    public final void a(long j, long j2) {
        float[] fArr;
        while (!g() && this.e < 100000 + j) {
            this.a.a();
            if (a(t(), this.a, 0) == -4 && !this.a.c()) {
                this.e = this.a.e;
                if (this.d != null && !this.a.d_()) {
                    this.a.h();
                    ByteBuffer byteBuffer = (ByteBuffer) Util.a((Object) this.a.c);
                    if (byteBuffer.remaining() != 16) {
                        fArr = null;
                    } else {
                        this.b.a(byteBuffer.array(), byteBuffer.limit());
                        this.b.d(byteBuffer.arrayOffset() + 4);
                        float[] fArr2 = new float[3];
                        for (int i = 0; i < 3; i++) {
                            fArr2[i] = Float.intBitsToFloat(this.b.k());
                        }
                        fArr = fArr2;
                    }
                    if (fArr != null) {
                        ((CameraMotionListener) Util.a((Object) this.d)).a(this.e - this.c, fArr);
                    }
                }
            } else {
                return;
            }
        }
    }

    public final void a(long j, boolean z) {
        this.e = Long.MIN_VALUE;
        A();
    }

    public final void a(Format[] formatArr, long j, long j2) {
        this.c = j2;
    }

    public final void r() {
        A();
    }

    public final String x() {
        return "CameraMotionRenderer";
    }

    public final boolean y() {
        return true;
    }

    public final boolean z() {
        return g();
    }
}
