package com.google.android.exoplayer2.video;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Choreographer;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;

public final class VideoFrameReleaseHelper {
    final FixedFrameRateEstimator a = new FixedFrameRateEstimator();
    final WindowManager b;
    final VSyncSampler c;
    final DefaultDisplayListener d;
    boolean e;
    Surface f;
    float g;
    float h;
    long i;
    long j;
    long k;
    long l;
    long m;
    long n;
    long o;
    private float p;
    private float q;

    final class DefaultDisplayListener implements DisplayManager.DisplayListener {
        final DisplayManager a;

        public DefaultDisplayListener(DisplayManager displayManager) {
            this.a = displayManager;
        }

        public final void onDisplayAdded(int i) {
        }

        public final void onDisplayChanged(int i) {
            if (i == 0) {
                VideoFrameReleaseHelper.this.d();
            }
        }

        public final void onDisplayRemoved(int i) {
        }
    }

    final class VSyncSampler implements Handler.Callback, Choreographer.FrameCallback {
        private static final VSyncSampler c = new VSyncSampler();
        public volatile long a = -9223372036854775807L;
        final Handler b;
        private final HandlerThread d;
        private Choreographer e;
        private int f;

        private VSyncSampler() {
            HandlerThread handlerThread = new HandlerThread("ExoPlayer:FrameReleaseChoreographer");
            this.d = handlerThread;
            handlerThread.start();
            Handler a2 = Util.a(handlerThread.getLooper(), (Handler.Callback) this);
            this.b = a2;
            a2.sendEmptyMessage(0);
        }

        public static VSyncSampler a() {
            return c;
        }

        public final void doFrame(long j) {
            this.a = j;
            ((Choreographer) Assertions.b((Object) this.e)).postFrameCallbackDelayed(this, 500);
        }

        public final boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    this.e = Choreographer.getInstance();
                    return true;
                case 1:
                    int i = this.f + 1;
                    this.f = i;
                    if (i == 1) {
                        ((Choreographer) Assertions.b((Object) this.e)).postFrameCallback(this);
                    }
                    return true;
                case 2:
                    int i2 = this.f - 1;
                    this.f = i2;
                    if (i2 == 0) {
                        ((Choreographer) Assertions.b((Object) this.e)).removeFrameCallback(this);
                        this.a = -9223372036854775807L;
                    }
                    return true;
                default:
                    return false;
            }
        }
    }

    public VideoFrameReleaseHelper(Context context) {
        DisplayManager displayManager;
        DefaultDisplayListener defaultDisplayListener = null;
        if (context != null) {
            context = context.getApplicationContext();
            this.b = (WindowManager) context.getSystemService("window");
        } else {
            this.b = null;
        }
        if (this.b != null) {
            if (Util.a >= 17 && (displayManager = (DisplayManager) ((Context) Assertions.b((Object) context)).getSystemService("display")) != null) {
                defaultDisplayListener = new DefaultDisplayListener(displayManager);
            }
            this.d = defaultDisplayListener;
            this.c = VSyncSampler.a();
        } else {
            this.d = null;
            this.c = null;
        }
        this.i = -9223372036854775807L;
        this.j = -9223372036854775807L;
        this.g = -1.0f;
        this.h = 1.0f;
    }

    static long a(long j2, long j3, long j4) {
        long j5;
        long j6 = j3 + (((j2 - j3) / j4) * j4);
        if (j2 <= j6) {
            j5 = j6 - j4;
        } else {
            long j7 = j6;
            j6 = j4 + j6;
            j5 = j7;
        }
        return j6 - j2 < j2 - j5 ? j6 : j5;
    }

    private static void a(Surface surface, float f2) {
        try {
            surface.setFrameRate(f2, f2 == 0.0f ? 0 : 1);
        } catch (IllegalStateException e2) {
            Log.b("VideoFrameReleaseHelper", "Failed to call Surface.setFrameRate", e2);
        }
    }

    static boolean a(long j2, long j3) {
        return Math.abs(j2 - j3) <= 20000000;
    }

    /* access modifiers changed from: package-private */
    public final void a() {
        this.k = 0;
        this.n = -1;
        this.l = -1;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0025 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(boolean r4) {
        /*
            r3 = this;
            int r0 = com.google.android.exoplayer2.util.Util.a
            r1 = 30
            if (r0 < r1) goto L_0x002b
            android.view.Surface r0 = r3.f
            if (r0 != 0) goto L_0x000b
            goto L_0x002b
        L_0x000b:
            boolean r1 = r3.e
            if (r1 == 0) goto L_0x001c
            float r1 = r3.p
            r2 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r2 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r2 == 0) goto L_0x001c
            float r2 = r3.h
            float r1 = r1 * r2
            goto L_0x001d
        L_0x001c:
            r1 = 0
        L_0x001d:
            if (r4 != 0) goto L_0x0026
            float r4 = r3.q
            int r4 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r4 != 0) goto L_0x0026
            return
        L_0x0026:
            r3.q = r1
            a((android.view.Surface) r0, (float) r1)
        L_0x002b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.video.VideoFrameReleaseHelper.a(boolean):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0060, code lost:
        if (java.lang.Math.abs(r0 - r8.p) >= (r8.a.a.b() && (r8.a.a() > 5000000000L ? 1 : (r8.a.a() == 5000000000L ? 0 : -1)) >= 0 ? 0.02f : 1.0f)) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x006e, code lost:
        if (r8.a.e >= 30) goto L_0x0070;
     */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b() {
        /*
            r8 = this;
            int r0 = com.google.android.exoplayer2.util.Util.a
            r1 = 30
            if (r0 < r1) goto L_0x0077
            android.view.Surface r0 = r8.f
            if (r0 != 0) goto L_0x000c
            goto L_0x0077
        L_0x000c:
            com.google.android.exoplayer2.video.FixedFrameRateEstimator r0 = r8.a
            com.google.android.exoplayer2.video.FixedFrameRateEstimator$Matcher r0 = r0.a
            boolean r0 = r0.b()
            if (r0 == 0) goto L_0x001d
            com.google.android.exoplayer2.video.FixedFrameRateEstimator r0 = r8.a
            float r0 = r0.b()
            goto L_0x001f
        L_0x001d:
            float r0 = r8.g
        L_0x001f:
            float r2 = r8.p
            int r3 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r3 != 0) goto L_0x0026
            return
        L_0x0026:
            r3 = -1082130432(0xffffffffbf800000, float:-1.0)
            r4 = 0
            r5 = 1
            int r6 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r6 == 0) goto L_0x0065
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 == 0) goto L_0x0065
            com.google.android.exoplayer2.video.FixedFrameRateEstimator r1 = r8.a
            com.google.android.exoplayer2.video.FixedFrameRateEstimator$Matcher r1 = r1.a
            boolean r1 = r1.b()
            if (r1 == 0) goto L_0x004d
            com.google.android.exoplayer2.video.FixedFrameRateEstimator r1 = r8.a
            long r1 = r1.a()
            r6 = 5000000000(0x12a05f200, double:2.470328229E-314)
            int r3 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r3 < 0) goto L_0x004d
            r1 = 1
            goto L_0x004e
        L_0x004d:
            r1 = 0
        L_0x004e:
            if (r1 == 0) goto L_0x0054
            r1 = 1017370378(0x3ca3d70a, float:0.02)
            goto L_0x0056
        L_0x0054:
            r1 = 1065353216(0x3f800000, float:1.0)
        L_0x0056:
            float r2 = r8.p
            float r2 = r0 - r2
            float r2 = java.lang.Math.abs(r2)
            int r1 = (r2 > r1 ? 1 : (r2 == r1 ? 0 : -1))
            if (r1 < 0) goto L_0x0063
            goto L_0x0070
        L_0x0063:
            r5 = 0
            goto L_0x0070
        L_0x0065:
            int r2 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r2 == 0) goto L_0x006a
            goto L_0x0070
        L_0x006a:
            com.google.android.exoplayer2.video.FixedFrameRateEstimator r2 = r8.a
            int r2 = r2.e
            if (r2 < r1) goto L_0x0063
        L_0x0070:
            if (r5 == 0) goto L_0x0077
            r8.p = r0
            r8.a((boolean) r4)
        L_0x0077:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.video.VideoFrameReleaseHelper.b():void");
    }

    /* access modifiers changed from: package-private */
    public final void c() {
        Surface surface;
        if (Util.a >= 30 && (surface = this.f) != null && this.q != 0.0f) {
            this.q = 0.0f;
            a(surface, 0.0f);
        }
    }

    /* access modifiers changed from: package-private */
    public final void d() {
        long j2;
        Display defaultDisplay = ((WindowManager) Assertions.b((Object) this.b)).getDefaultDisplay();
        if (defaultDisplay != null) {
            double refreshRate = (double) defaultDisplay.getRefreshRate();
            Double.isNaN(refreshRate);
            long j3 = (long) (1.0E9d / refreshRate);
            this.i = j3;
            j2 = (j3 * 80) / 100;
        } else {
            Log.c("VideoFrameReleaseHelper", "Unable to query display refresh rate");
            j2 = -9223372036854775807L;
            this.i = -9223372036854775807L;
        }
        this.j = j2;
    }
}
