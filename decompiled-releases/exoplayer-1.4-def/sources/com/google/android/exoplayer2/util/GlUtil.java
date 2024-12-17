package com.google.android.exoplayer2.util;

import android.opengl.GLES20;
import android.opengl.GLU;
import android.text.TextUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public final class GlUtil {
    public static int a(String str, String str2) {
        int glCreateProgram = GLES20.glCreateProgram();
        b();
        a(35633, str, glCreateProgram);
        a(35632, str2, glCreateProgram);
        GLES20.glLinkProgram(glCreateProgram);
        int[] iArr = {0};
        GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
        if (iArr[0] != 1) {
            String valueOf = String.valueOf(GLES20.glGetProgramInfoLog(glCreateProgram));
            Log.d("GlUtil", valueOf.length() != 0 ? "Unable to link shader program: \n".concat(valueOf) : new String("Unable to link shader program: \n"));
        }
        b();
        return glCreateProgram;
    }

    public static int a(String[] strArr, String[] strArr2) {
        return a(TextUtils.join("\n", strArr), TextUtils.join("\n", strArr2));
    }

    public static FloatBuffer a(float[] fArr) {
        return (FloatBuffer) ByteBuffer.allocateDirect(fArr.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer().put(fArr).flip();
    }

    private static void a(int i, String str, int i2) {
        int glCreateShader = GLES20.glCreateShader(i);
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        int[] iArr = {0};
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] != 1) {
            String glGetShaderInfoLog = GLES20.glGetShaderInfoLog(glCreateShader);
            Log.d("GlUtil", new StringBuilder(String.valueOf(glGetShaderInfoLog).length() + 10 + String.valueOf(str).length()).append(glGetShaderInfoLog).append(", source: ").append(str).toString());
        }
        GLES20.glAttachShader(i2, glCreateShader);
        GLES20.glDeleteShader(glCreateShader);
        b();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0008, code lost:
        r0 = android.opengl.EGL14.eglQueryString(android.opengl.EGL14.eglGetDisplay(0), 12373);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a() {
        /*
            int r0 = com.google.android.exoplayer2.util.Util.a
            r1 = 17
            r2 = 0
            if (r0 >= r1) goto L_0x0008
            return r2
        L_0x0008:
            android.opengl.EGLDisplay r0 = android.opengl.EGL14.eglGetDisplay(r2)
            r1 = 12373(0x3055, float:1.7338E-41)
            java.lang.String r0 = android.opengl.EGL14.eglQueryString(r0, r1)
            if (r0 == 0) goto L_0x001e
            java.lang.String r1 = "EGL_KHR_surfaceless_context"
            boolean r0 = r0.contains(r1)
            if (r0 == 0) goto L_0x001e
            r0 = 1
            return r0
        L_0x001e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.util.GlUtil.a():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0034, code lost:
        r4 = android.opengl.EGL14.eglQueryString(android.opengl.EGL14.eglGetDisplay(0), 12373);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(android.content.Context r4) {
        /*
            int r0 = com.google.android.exoplayer2.util.Util.a
            r1 = 24
            r2 = 0
            if (r0 >= r1) goto L_0x0008
            return r2
        L_0x0008:
            int r0 = com.google.android.exoplayer2.util.Util.a
            r1 = 26
            if (r0 >= r1) goto L_0x0023
            java.lang.String r0 = "samsung"
            java.lang.String r3 = com.google.android.exoplayer2.util.Util.c
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x0022
            java.lang.String r0 = "XT1650"
            java.lang.String r3 = com.google.android.exoplayer2.util.Util.d
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0023
        L_0x0022:
            return r2
        L_0x0023:
            int r0 = com.google.android.exoplayer2.util.Util.a
            if (r0 >= r1) goto L_0x0034
            android.content.pm.PackageManager r4 = r4.getPackageManager()
            java.lang.String r0 = "android.hardware.vr.high_performance"
            boolean r4 = r4.hasSystemFeature(r0)
            if (r4 != 0) goto L_0x0034
            return r2
        L_0x0034:
            android.opengl.EGLDisplay r4 = android.opengl.EGL14.eglGetDisplay(r2)
            r0 = 12373(0x3055, float:1.7338E-41)
            java.lang.String r4 = android.opengl.EGL14.eglQueryString(r4, r0)
            if (r4 == 0) goto L_0x004a
            java.lang.String r0 = "EGL_EXT_protected_content"
            boolean r4 = r4.contains(r0)
            if (r4 == 0) goto L_0x004a
            r4 = 1
            return r4
        L_0x004a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.util.GlUtil.a(android.content.Context):boolean");
    }

    public static void b() {
        while (true) {
            int glGetError = GLES20.glGetError();
            if (glGetError != 0) {
                String valueOf = String.valueOf(GLU.gluErrorString(glGetError));
                Log.d("GlUtil", valueOf.length() != 0 ? "glError ".concat(valueOf) : new String("glError "));
            } else {
                return;
            }
        }
    }

    public static int c() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, IntBuffer.wrap(iArr));
        GLES20.glBindTexture(36197, iArr[0]);
        GLES20.glTexParameteri(36197, 10241, 9729);
        GLES20.glTexParameteri(36197, 10240, 9729);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        b();
        return iArr[0];
    }
}
