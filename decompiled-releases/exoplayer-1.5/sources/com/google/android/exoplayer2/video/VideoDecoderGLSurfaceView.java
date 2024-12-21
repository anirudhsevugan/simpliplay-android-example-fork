package com.google.android.exoplayer2.video;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.GlUtil;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.concurrent.atomic.AtomicReference;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public final class VideoDecoderGLSurfaceView extends GLSurfaceView implements VideoDecoderOutputBufferRenderer {
    private final Renderer a;

    final class Renderer implements GLSurfaceView.Renderer {
        private static final float[] a = {1.164f, 1.164f, 1.164f, 0.0f, -0.213f, 2.112f, 1.793f, -0.533f, 0.0f};
        private static final String[] b = {"y_tex", "u_tex", "v_tex"};
        private static final FloatBuffer c = GlUtil.a(new float[]{-1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f, -1.0f});
        private final int[] d = new int[3];
        private final int[] e = new int[3];
        private final int[] f = new int[3];
        private final int[] g = new int[3];
        private final AtomicReference h = new AtomicReference();
        private final FloatBuffer[] i = new FloatBuffer[3];
        private int j;
        private int k;
        private VideoDecoderOutputBuffer l;

        public Renderer() {
            for (int i2 = 0; i2 < 3; i2++) {
                int[] iArr = this.f;
                this.g[i2] = -1;
                iArr[i2] = -1;
            }
        }

        public final void onDrawFrame(GL10 gl10) {
            VideoDecoderOutputBuffer videoDecoderOutputBuffer = (VideoDecoderOutputBuffer) this.h.getAndSet((Object) null);
            if (videoDecoderOutputBuffer != null || this.l != null) {
                if (videoDecoderOutputBuffer != null) {
                    VideoDecoderOutputBuffer videoDecoderOutputBuffer2 = this.l;
                    if (videoDecoderOutputBuffer2 != null) {
                        videoDecoderOutputBuffer2.f();
                    }
                    this.l = videoDecoderOutputBuffer;
                }
                Assertions.b((Object) this.l);
                GLES20.glUniformMatrix3fv(this.k, 1, false, a, 0);
                int[] iArr = (int[]) Assertions.b((Object) null);
                ByteBuffer[] byteBufferArr = (ByteBuffer[]) Assertions.b((Object) null);
                for (int i2 = 0; i2 < 3; i2++) {
                    GLES20.glActiveTexture(33984 + i2);
                    GLES20.glBindTexture(3553, this.d[i2]);
                    GLES20.glPixelStorei(3317, 1);
                    GLES20.glTexImage2D(3553, 0, 6409, iArr[i2], 0, 0, 6409, 5121, byteBufferArr[i2]);
                }
                int[] iArr2 = new int[3];
                iArr2[0] = 0;
                int i3 = (0 + 1) / 2;
                iArr2[2] = i3;
                iArr2[1] = i3;
                for (int i4 = 0; i4 < 3; i4++) {
                    if (this.f[i4] != iArr2[i4] || this.g[i4] != iArr[i4]) {
                        Assertions.b(iArr[i4] != 0);
                        float f2 = ((float) iArr2[i4]) / ((float) iArr[i4]);
                        this.i[i4] = GlUtil.a(new float[]{0.0f, 0.0f, 0.0f, 1.0f, f2, 0.0f, f2, 1.0f});
                        GLES20.glVertexAttribPointer(this.e[i4], 2, 5126, false, 0, this.i[i4]);
                        this.f[i4] = iArr2[i4];
                        this.g[i4] = iArr[i4];
                    }
                }
                GLES20.glClear(16384);
                GLES20.glDrawArrays(5, 0, 4);
                GlUtil.b();
            }
        }

        public final void onSurfaceChanged(GL10 gl10, int i2, int i3) {
            GLES20.glViewport(0, 0, i2, i3);
        }

        public final void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
            int a2 = GlUtil.a("varying vec2 interp_tc_y;\nvarying vec2 interp_tc_u;\nvarying vec2 interp_tc_v;\nattribute vec4 in_pos;\nattribute vec2 in_tc_y;\nattribute vec2 in_tc_u;\nattribute vec2 in_tc_v;\nvoid main() {\n  gl_Position = in_pos;\n  interp_tc_y = in_tc_y;\n  interp_tc_u = in_tc_u;\n  interp_tc_v = in_tc_v;\n}\n", "precision mediump float;\nvarying vec2 interp_tc_y;\nvarying vec2 interp_tc_u;\nvarying vec2 interp_tc_v;\nuniform sampler2D y_tex;\nuniform sampler2D u_tex;\nuniform sampler2D v_tex;\nuniform mat3 mColorConversion;\nvoid main() {\n  vec3 yuv;\n  yuv.x = texture2D(y_tex, interp_tc_y).r - 0.0625;\n  yuv.y = texture2D(u_tex, interp_tc_u).r - 0.5;\n  yuv.z = texture2D(v_tex, interp_tc_v).r - 0.5;\n  gl_FragColor = vec4(mColorConversion * yuv, 1.0);\n}\n");
            this.j = a2;
            GLES20.glUseProgram(a2);
            int glGetAttribLocation = GLES20.glGetAttribLocation(this.j, "in_pos");
            GLES20.glEnableVertexAttribArray(glGetAttribLocation);
            GLES20.glVertexAttribPointer(glGetAttribLocation, 2, 5126, false, 0, c);
            this.e[0] = GLES20.glGetAttribLocation(this.j, "in_tc_y");
            GLES20.glEnableVertexAttribArray(this.e[0]);
            this.e[1] = GLES20.glGetAttribLocation(this.j, "in_tc_u");
            GLES20.glEnableVertexAttribArray(this.e[1]);
            this.e[2] = GLES20.glGetAttribLocation(this.j, "in_tc_v");
            GLES20.glEnableVertexAttribArray(this.e[2]);
            GlUtil.b();
            this.k = GLES20.glGetUniformLocation(this.j, "mColorConversion");
            GlUtil.b();
            GLES20.glGenTextures(3, this.d, 0);
            for (int i2 = 0; i2 < 3; i2++) {
                GLES20.glUniform1i(GLES20.glGetUniformLocation(this.j, b[i2]), i2);
                GLES20.glActiveTexture(33984 + i2);
                GLES20.glBindTexture(3553, this.d[i2]);
                GLES20.glTexParameterf(3553, 10241, 9729.0f);
                GLES20.glTexParameterf(3553, 10240, 9729.0f);
                GLES20.glTexParameterf(3553, 10242, 33071.0f);
                GLES20.glTexParameterf(3553, 10243, 33071.0f);
            }
            GlUtil.b();
            GlUtil.b();
        }
    }

    public VideoDecoderGLSurfaceView(Context context) {
        this(context, (byte) 0);
    }

    private VideoDecoderGLSurfaceView(Context context, byte b) {
        super(context, (AttributeSet) null);
        Renderer renderer = new Renderer();
        this.a = renderer;
        setPreserveEGLContextOnPause(true);
        setEGLContextClientVersion(2);
        setRenderer(renderer);
        setRenderMode(0);
    }
}
