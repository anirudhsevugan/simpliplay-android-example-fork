package com.google.android.exoplayer2.video.spherical;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.SurfaceTexture;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.WindowManager;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.GlUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.spherical.OrientationListener;
import com.google.android.exoplayer2.video.spherical.ProjectionRenderer;
import com.google.android.exoplayer2.video.spherical.TouchTracker;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public final class SphericalGLSurfaceView extends GLSurfaceView {
    public final CopyOnWriteArrayList a;
    public final SceneRenderer b;
    SurfaceTexture c;
    public Surface d;
    private final SensorManager e;
    private final Sensor f;
    private final OrientationListener g;
    private final Handler h;
    private final TouchTracker i;
    private boolean j;
    private boolean k;
    private boolean l;

    final class Renderer implements GLSurfaceView.Renderer, OrientationListener.Listener, TouchTracker.Listener {
        private final SceneRenderer a;
        private final float[] b = new float[16];
        private final float[] c = new float[16];
        private final float[] d;
        private final float[] e;
        private final float[] f;
        private float g;
        private float h;
        private final float[] i;
        private final float[] j;

        public Renderer(SceneRenderer sceneRenderer) {
            float[] fArr = new float[16];
            this.d = fArr;
            float[] fArr2 = new float[16];
            this.e = fArr2;
            float[] fArr3 = new float[16];
            this.f = fArr3;
            this.i = new float[16];
            this.j = new float[16];
            this.a = sceneRenderer;
            Matrix.setIdentityM(fArr, 0);
            Matrix.setIdentityM(fArr2, 0);
            Matrix.setIdentityM(fArr3, 0);
            this.h = 3.1415927f;
        }

        private void b() {
            Matrix.setRotateM(this.e, 0, -this.g, (float) Math.cos((double) this.h), (float) Math.sin((double) this.h), 0.0f);
        }

        public final synchronized void a(PointF pointF) {
            this.g = pointF.y;
            b();
            Matrix.setRotateM(this.f, 0, -pointF.x, 0.0f, 1.0f, 0.0f);
        }

        public final synchronized void a(float[] fArr, float f2) {
            System.arraycopy(fArr, 0, this.d, 0, 16);
            this.h = -f2;
            b();
        }

        public final boolean a() {
            return SphericalGLSurfaceView.this.performClick();
        }

        public final void onDrawFrame(GL10 gl10) {
            synchronized (this) {
                Matrix.multiplyMM(this.j, 0, this.d, 0, this.f, 0);
                Matrix.multiplyMM(this.i, 0, this.e, 0, this.j, 0);
            }
            Matrix.multiplyMM(this.c, 0, this.b, 0, this.i, 0);
            SceneRenderer sceneRenderer = this.a;
            float[] fArr = this.c;
            GLES20.glClear(16384);
            GlUtil.b();
            if (sceneRenderer.a.compareAndSet(true, false)) {
                ((SurfaceTexture) Assertions.b((Object) sceneRenderer.j)).updateTexImage();
                GlUtil.b();
                if (sceneRenderer.b.compareAndSet(true, false)) {
                    Matrix.setIdentityM(sceneRenderer.g, 0);
                }
                long timestamp = sceneRenderer.j.getTimestamp();
                Long l = (Long) sceneRenderer.e.b(timestamp);
                if (l != null) {
                    FrameRotationQueue frameRotationQueue = sceneRenderer.d;
                    float[] fArr2 = sceneRenderer.g;
                    float[] fArr3 = (float[]) frameRotationQueue.c.a(l.longValue());
                    if (fArr3 != null) {
                        float[] fArr4 = frameRotationQueue.b;
                        float f2 = fArr3[0];
                        float f3 = -fArr3[1];
                        float f4 = -fArr3[2];
                        float length = Matrix.length(f2, f3, f4);
                        float[] fArr5 = fArr2;
                        if (length != 0.0f) {
                            Matrix.setRotateM(fArr4, 0, (float) Math.toDegrees((double) length), f2 / length, f3 / length, f4 / length);
                        } else {
                            Matrix.setIdentityM(fArr4, 0);
                        }
                        if (!frameRotationQueue.d) {
                            FrameRotationQueue.a(frameRotationQueue.a, frameRotationQueue.b);
                            frameRotationQueue.d = true;
                        }
                        Matrix.multiplyMM(fArr5, 0, frameRotationQueue.a, 0, frameRotationQueue.b, 0);
                    }
                }
                Projection projection = (Projection) sceneRenderer.f.a(timestamp);
                if (projection != null) {
                    ProjectionRenderer projectionRenderer = sceneRenderer.c;
                    if (ProjectionRenderer.a(projection)) {
                        projectionRenderer.f = projection.c;
                        projectionRenderer.g = new ProjectionRenderer.MeshData(projection.a.a[0]);
                        if (!projection.d) {
                            new ProjectionRenderer.MeshData(projection.b.a[0]);
                        }
                    }
                }
            }
            Matrix.multiplyMM(sceneRenderer.h, 0, fArr, 0, sceneRenderer.g, 0);
            ProjectionRenderer projectionRenderer2 = sceneRenderer.c;
            int i2 = sceneRenderer.i;
            float[] fArr6 = sceneRenderer.h;
            ProjectionRenderer.MeshData meshData = projectionRenderer2.g;
            if (meshData != null) {
                GLES20.glUseProgram(projectionRenderer2.h);
                GlUtil.b();
                GLES20.glEnableVertexAttribArray(projectionRenderer2.k);
                GLES20.glEnableVertexAttribArray(projectionRenderer2.l);
                GlUtil.b();
                GLES20.glUniformMatrix3fv(projectionRenderer2.j, 1, false, projectionRenderer2.f == 1 ? ProjectionRenderer.d : projectionRenderer2.f == 2 ? ProjectionRenderer.e : ProjectionRenderer.c, 0);
                GLES20.glUniformMatrix4fv(projectionRenderer2.i, 1, false, fArr6, 0);
                GLES20.glActiveTexture(33984);
                GLES20.glBindTexture(36197, i2);
                GLES20.glUniform1i(projectionRenderer2.m, 0);
                GlUtil.b();
                GLES20.glVertexAttribPointer(projectionRenderer2.k, 3, 5126, false, 12, meshData.b);
                GlUtil.b();
                GLES20.glVertexAttribPointer(projectionRenderer2.l, 2, 5126, false, 8, meshData.c);
                GlUtil.b();
                GLES20.glDrawArrays(meshData.d, 0, meshData.a);
                GlUtil.b();
                GLES20.glDisableVertexAttribArray(projectionRenderer2.k);
                GLES20.glDisableVertexAttribArray(projectionRenderer2.l);
            }
        }

        public final void onSurfaceChanged(GL10 gl10, int i2, int i3) {
            float f2;
            boolean z = false;
            GLES20.glViewport(0, 0, i2, i3);
            float f3 = ((float) i2) / ((float) i3);
            if (f3 > 1.0f) {
                z = true;
            }
            if (z) {
                double tan = Math.tan(Math.toRadians(45.0d));
                double d2 = (double) f3;
                Double.isNaN(d2);
                f2 = (float) (Math.toDegrees(Math.atan(tan / d2)) * 2.0d);
            } else {
                f2 = 90.0f;
            }
            Matrix.perspectiveM(this.b, 0, f2, f3, 0.1f, 100.0f);
        }

        public final synchronized void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
            SphericalGLSurfaceView sphericalGLSurfaceView = SphericalGLSurfaceView.this;
            SceneRenderer sceneRenderer = this.a;
            GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
            GlUtil.b();
            ProjectionRenderer projectionRenderer = sceneRenderer.c;
            projectionRenderer.h = GlUtil.a(ProjectionRenderer.a, ProjectionRenderer.b);
            projectionRenderer.i = GLES20.glGetUniformLocation(projectionRenderer.h, "uMvpMatrix");
            projectionRenderer.j = GLES20.glGetUniformLocation(projectionRenderer.h, "uTexMatrix");
            projectionRenderer.k = GLES20.glGetAttribLocation(projectionRenderer.h, "aPosition");
            projectionRenderer.l = GLES20.glGetAttribLocation(projectionRenderer.h, "aTexCoords");
            projectionRenderer.m = GLES20.glGetUniformLocation(projectionRenderer.h, "uTexture");
            GlUtil.b();
            sceneRenderer.i = GlUtil.c();
            sceneRenderer.j = new SurfaceTexture(sceneRenderer.i);
            sceneRenderer.j.setOnFrameAvailableListener(new SceneRenderer$$Lambda$0(sceneRenderer));
            sphericalGLSurfaceView.h.post(new SphericalGLSurfaceView$$Lambda$1(sphericalGLSurfaceView, sceneRenderer.j));
        }
    }

    public interface VideoSurfaceListener {
        void a(Surface surface);

        void e();
    }

    public SphericalGLSurfaceView(Context context) {
        this(context, (byte) 0);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private SphericalGLSurfaceView(Context context, byte b2) {
        super(context, (AttributeSet) null);
        Sensor sensor = null;
        this.a = new CopyOnWriteArrayList();
        this.h = new Handler(Looper.getMainLooper());
        SensorManager sensorManager = (SensorManager) Assertions.b(context.getSystemService("sensor"));
        this.e = sensorManager;
        sensor = Util.a >= 18 ? sensorManager.getDefaultSensor(15) : sensor;
        this.f = sensor == null ? sensorManager.getDefaultSensor(11) : sensor;
        SceneRenderer sceneRenderer = new SceneRenderer();
        this.b = sceneRenderer;
        Renderer renderer = new Renderer(sceneRenderer);
        TouchTracker touchTracker = new TouchTracker(context, renderer);
        this.i = touchTracker;
        this.g = new OrientationListener(((WindowManager) Assertions.b((Object) (WindowManager) context.getSystemService("window"))).getDefaultDisplay(), touchTracker, renderer);
        this.j = true;
        setEGLContextClientVersion(2);
        setRenderer(renderer);
        setOnTouchListener(touchTracker);
    }

    private void a() {
        boolean z = this.j && this.k;
        Sensor sensor = this.f;
        if (sensor != null && z != this.l) {
            if (z) {
                this.e.registerListener(this.g, sensor, 0);
            } else {
                this.e.unregisterListener(this.g);
            }
            this.l = z;
        }
    }

    static void a(SurfaceTexture surfaceTexture, Surface surface) {
        if (surfaceTexture != null) {
            surfaceTexture.release();
        }
        if (surface != null) {
            surface.release();
        }
    }

    public final void a(VideoSurfaceListener videoSurfaceListener) {
        this.a.remove(videoSurfaceListener);
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.h.post(new SphericalGLSurfaceView$$Lambda$0(this));
    }

    public final void onPause() {
        this.k = false;
        a();
        super.onPause();
    }

    public final void onResume() {
        super.onResume();
        this.k = true;
        a();
    }
}
