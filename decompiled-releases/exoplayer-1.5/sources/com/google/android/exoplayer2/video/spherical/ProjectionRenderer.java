package com.google.android.exoplayer2.video.spherical;

import com.google.android.exoplayer2.util.GlUtil;
import com.google.android.exoplayer2.video.spherical.Projection;
import java.nio.FloatBuffer;

final class ProjectionRenderer {
    static final String[] a = {"uniform mat4 uMvpMatrix;", "uniform mat3 uTexMatrix;", "attribute vec4 aPosition;", "attribute vec2 aTexCoords;", "varying vec2 vTexCoords;", "void main() {", "  gl_Position = uMvpMatrix * aPosition;", "  vTexCoords = (uTexMatrix * vec3(aTexCoords, 1)).xy;", "}"};
    static final String[] b = {"#extension GL_OES_EGL_image_external : require", "precision mediump float;", "uniform samplerExternalOES uTexture;", "varying vec2 vTexCoords;", "void main() {", "  gl_FragColor = texture2D(uTexture, vTexCoords);", "}"};
    static final float[] c = {1.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 1.0f};
    static final float[] d = {1.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, 0.5f, 1.0f};
    static final float[] e = {0.5f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 1.0f};
    int f;
    MeshData g;
    int h;
    int i;
    int j;
    int k;
    int l;
    int m;

    class MeshData {
        /* access modifiers changed from: private */
        public final int a;
        /* access modifiers changed from: private */
        public final FloatBuffer b;
        /* access modifiers changed from: private */
        public final FloatBuffer c;
        /* access modifiers changed from: private */
        public final int d;

        public MeshData(Projection.SubMesh subMesh) {
            int i;
            this.a = subMesh.c.length / 3;
            this.b = GlUtil.a(subMesh.c);
            this.c = GlUtil.a(subMesh.d);
            switch (subMesh.b) {
                case 1:
                    i = 5;
                    break;
                case 2:
                    i = 6;
                    break;
                default:
                    i = 4;
                    break;
            }
            this.d = i;
        }
    }

    ProjectionRenderer() {
    }

    public static boolean a(Projection projection) {
        Projection.Mesh mesh = projection.a;
        Projection.Mesh mesh2 = projection.b;
        return mesh.a.length == 1 && mesh.a[0].a == 0 && mesh2.a.length == 1 && mesh2.a[0].a == 0;
    }
}
