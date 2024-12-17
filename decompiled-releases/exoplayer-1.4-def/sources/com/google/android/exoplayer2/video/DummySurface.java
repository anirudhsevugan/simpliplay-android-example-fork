package com.google.android.exoplayer2.video;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Surface;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.EGLSurfaceTexture;
import com.google.android.exoplayer2.util.GlUtil;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;

public final class DummySurface extends Surface {
    private static int b;
    private static boolean c;
    public final boolean a;
    private final DummySurfaceThread d;
    private boolean e;

    class DummySurfaceThread extends HandlerThread implements Handler.Callback {
        Handler a;
        private EGLSurfaceTexture b;
        private Error c;
        private RuntimeException d;
        private DummySurface e;

        public DummySurfaceThread() {
            super("ExoPlayer:DummySurface");
        }

        public final DummySurface a(int i) {
            boolean z;
            start();
            this.a = new Handler(getLooper(), this);
            this.b = new EGLSurfaceTexture(this.a);
            synchronized (this) {
                z = false;
                this.a.obtainMessage(1, i, 0).sendToTarget();
                while (this.e == null && this.d == null && this.c == null) {
                    try {
                        wait();
                    } catch (InterruptedException e2) {
                        z = true;
                    }
                }
            }
            if (z) {
                Thread.currentThread().interrupt();
            }
            RuntimeException runtimeException = this.d;
            if (runtimeException == null) {
                Error error = this.c;
                if (error == null) {
                    return (DummySurface) Assertions.b((Object) this.e);
                }
                throw error;
            }
            throw runtimeException;
        }

        public boolean handleMessage(Message message) {
            EGLConfig eGLConfig;
            EGLSurface eGLSurface;
            EGLSurfaceTexture eGLSurfaceTexture;
            switch (message.what) {
                case 1:
                    try {
                        int i = message.arg1;
                        Assertions.b((Object) this.b);
                        EGLSurfaceTexture eGLSurfaceTexture2 = this.b;
                        EGLDisplay eglGetDisplay = EGL14.eglGetDisplay(0);
                        if (eglGetDisplay != null) {
                            int[] iArr = new int[2];
                            if (EGL14.eglInitialize(eglGetDisplay, iArr, 0, iArr, 1)) {
                                eGLSurfaceTexture2.d = eglGetDisplay;
                                EGLConfig[] eGLConfigArr = new EGLConfig[1];
                                int[] iArr2 = new int[1];
                                boolean eglChooseConfig = EGL14.eglChooseConfig(eGLSurfaceTexture2.d, EGLSurfaceTexture.a, 0, eGLConfigArr, 0, 1, iArr2, 0);
                                if (!eglChooseConfig || iArr2[0] <= 0 || (eGLConfig = eGLConfigArr[0]) == null) {
                                    throw new EGLSurfaceTexture.GlException(Util.a("eglChooseConfig failed: success=%b, numConfigs[0]=%d, configs[0]=%s", Boolean.valueOf(eglChooseConfig), Integer.valueOf(iArr2[0]), eGLConfigArr[0]), (byte) 0);
                                }
                                EGLContext eglCreateContext = EGL14.eglCreateContext(eGLSurfaceTexture2.d, eGLConfig, EGL14.EGL_NO_CONTEXT, i == 0 ? new int[]{12440, 2, 12344} : new int[]{12440, 2, 12992, 1, 12344}, 0);
                                if (eglCreateContext != null) {
                                    eGLSurfaceTexture2.e = eglCreateContext;
                                    EGLDisplay eGLDisplay = eGLSurfaceTexture2.d;
                                    EGLContext eGLContext = eGLSurfaceTexture2.e;
                                    if (i == 1) {
                                        eGLSurface = EGL14.EGL_NO_SURFACE;
                                    } else {
                                        eGLSurface = EGL14.eglCreatePbufferSurface(eGLDisplay, eGLConfig, i == 2 ? new int[]{12375, 1, 12374, 1, 12992, 1, 12344} : new int[]{12375, 1, 12374, 1, 12344}, 0);
                                        if (eGLSurface == null) {
                                            throw new EGLSurfaceTexture.GlException("eglCreatePbufferSurface failed", (byte) 0);
                                        }
                                    }
                                    if (EGL14.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, eGLContext)) {
                                        eGLSurfaceTexture2.f = eGLSurface;
                                        GLES20.glGenTextures(1, eGLSurfaceTexture2.c, 0);
                                        GlUtil.b();
                                        eGLSurfaceTexture2.g = new SurfaceTexture(eGLSurfaceTexture2.c[0]);
                                        eGLSurfaceTexture2.g.setOnFrameAvailableListener(eGLSurfaceTexture2);
                                        this.e = new DummySurface(this, (SurfaceTexture) Assertions.b((Object) this.b.g), i != 0, (byte) 0);
                                        synchronized (this) {
                                            notify();
                                        }
                                        return true;
                                    }
                                    throw new EGLSurfaceTexture.GlException("eglMakeCurrent failed", (byte) 0);
                                }
                                throw new EGLSurfaceTexture.GlException("eglCreateContext failed", (byte) 0);
                            }
                            throw new EGLSurfaceTexture.GlException("eglInitialize failed", (byte) 0);
                        }
                        throw new EGLSurfaceTexture.GlException("eglGetDisplay failed", (byte) 0);
                    } catch (RuntimeException e2) {
                        Log.b("DummySurface", "Failed to initialize dummy surface", e2);
                        this.d = e2;
                        synchronized (this) {
                            notify();
                        }
                    } catch (Error e3) {
                        try {
                            Log.b("DummySurface", "Failed to initialize dummy surface", e3);
                            this.c = e3;
                            synchronized (this) {
                                notify();
                            }
                        } catch (Throwable th) {
                            synchronized (this) {
                                notify();
                                throw th;
                            }
                        }
                    }
                    break;
                case 2:
                    try {
                        Assertions.b((Object) this.b);
                        eGLSurfaceTexture = this.b;
                        eGLSurfaceTexture.b.removeCallbacks(eGLSurfaceTexture);
                        if (eGLSurfaceTexture.g != null) {
                            eGLSurfaceTexture.g.release();
                            GLES20.glDeleteTextures(1, eGLSurfaceTexture.c, 0);
                        }
                        if (eGLSurfaceTexture.d != null && !eGLSurfaceTexture.d.equals(EGL14.EGL_NO_DISPLAY)) {
                            EGLDisplay eGLDisplay2 = eGLSurfaceTexture.d;
                            EGLSurface eGLSurface2 = EGL14.EGL_NO_SURFACE;
                            EGL14.eglMakeCurrent(eGLDisplay2, eGLSurface2, eGLSurface2, EGL14.EGL_NO_CONTEXT);
                        }
                        if (eGLSurfaceTexture.f != null && !eGLSurfaceTexture.f.equals(EGL14.EGL_NO_SURFACE)) {
                            EGL14.eglDestroySurface(eGLSurfaceTexture.d, eGLSurfaceTexture.f);
                        }
                        if (eGLSurfaceTexture.e != null) {
                            EGL14.eglDestroyContext(eGLSurfaceTexture.d, eGLSurfaceTexture.e);
                        }
                        if (Util.a >= 19) {
                            EGL14.eglReleaseThread();
                        }
                        if (eGLSurfaceTexture.d != null && !eGLSurfaceTexture.d.equals(EGL14.EGL_NO_DISPLAY)) {
                            EGL14.eglTerminate(eGLSurfaceTexture.d);
                        }
                        eGLSurfaceTexture.d = null;
                        eGLSurfaceTexture.e = null;
                        eGLSurfaceTexture.f = null;
                        eGLSurfaceTexture.g = null;
                    } catch (Throwable th2) {
                        try {
                            Log.b("DummySurface", "Failed to release dummy surface", th2);
                        } catch (Throwable th3) {
                            quit();
                            throw th3;
                        }
                    }
                    quit();
                    return true;
                default:
                    return true;
            }
        }
    }

    private DummySurface(DummySurfaceThread dummySurfaceThread, SurfaceTexture surfaceTexture, boolean z) {
        super(surfaceTexture);
        this.d = dummySurfaceThread;
        this.a = z;
    }

    /* synthetic */ DummySurface(DummySurfaceThread dummySurfaceThread, SurfaceTexture surfaceTexture, boolean z, byte b2) {
        this(dummySurfaceThread, surfaceTexture, z);
    }

    public static DummySurface a(Context context, boolean z) {
        int i = 0;
        Assertions.b(!z || a(context));
        DummySurfaceThread dummySurfaceThread = new DummySurfaceThread();
        if (z) {
            i = b;
        }
        return dummySurfaceThread.a(i);
    }

    public static synchronized boolean a(Context context) {
        int i;
        synchronized (DummySurface.class) {
            if (!c) {
                b = GlUtil.a(context) ? GlUtil.a() ? 1 : 2 : 0;
                c = true;
            }
            i = b;
        }
        return i != 0;
    }

    public final void release() {
        super.release();
        synchronized (this.d) {
            if (!this.e) {
                DummySurfaceThread dummySurfaceThread = this.d;
                Assertions.b((Object) dummySurfaceThread.a);
                dummySurfaceThread.a.sendEmptyMessage(2);
                this.e = true;
            }
        }
    }
}
