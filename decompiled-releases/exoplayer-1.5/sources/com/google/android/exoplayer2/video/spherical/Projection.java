package com.google.android.exoplayer2.video.spherical;

import com.google.android.exoplayer2.util.Assertions;

final class Projection {
    public final Mesh a;
    public final Mesh b;
    public final int c;
    public final boolean d;

    public final class Mesh {
        final SubMesh[] a;

        public Mesh(SubMesh... subMeshArr) {
            this.a = subMeshArr;
        }
    }

    public final class SubMesh {
        public final int a;
        public final int b;
        public final float[] c;
        public final float[] d;

        public SubMesh(int i, float[] fArr, float[] fArr2, int i2) {
            this.a = i;
            Assertions.a((((long) fArr.length) << 1) != ((long) fArr2.length) * 3 ? false : true);
            this.c = fArr;
            this.d = fArr2;
            this.b = i2;
        }
    }

    public Projection(Mesh mesh, int i) {
        this(mesh, mesh, i);
    }

    public Projection(Mesh mesh, Mesh mesh2, int i) {
        this.a = mesh;
        this.b = mesh2;
        this.c = i;
        this.d = mesh == mesh2;
    }

    public static Projection a(int i) {
        int i2;
        Assertions.a(true);
        Assertions.a(true);
        Assertions.a(true);
        Assertions.a(true);
        Assertions.a(true);
        float radians = (float) Math.toRadians(180.0d);
        float radians2 = (float) Math.toRadians(360.0d);
        float f = radians / 36.0f;
        float f2 = radians2 / 72.0f;
        float[] fArr = new float[15984];
        float[] fArr2 = new float[10656];
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 < 36) {
            float f3 = radians / 2.0f;
            float f4 = (((float) i3) * f) - f3;
            int i6 = i3 + 1;
            float f5 = (((float) i6) * f) - f3;
            int i7 = 0;
            while (i7 < 73) {
                int i8 = 0;
                while (i8 < 2) {
                    float f6 = ((float) i7) * f2;
                    float f7 = f2;
                    int i9 = i4 + 1;
                    int i10 = i6;
                    float f8 = f5;
                    double d2 = (double) ((f6 + 3.1415927f) - (radians2 / 2.0f));
                    double d3 = (double) (i8 == 0 ? f4 : f5);
                    float f9 = f;
                    float f10 = radians;
                    fArr[i4] = -((float) (Math.sin(d2) * 50.0d * Math.cos(d3)));
                    int i11 = i9 + 1;
                    int i12 = i8;
                    int i13 = i3;
                    fArr[i9] = (float) (Math.sin(d3) * 50.0d);
                    int i14 = i11 + 1;
                    fArr[i11] = (float) (Math.cos(d2) * 50.0d * Math.cos(d3));
                    int i15 = i5 + 1;
                    fArr2[i5] = f6 / radians2;
                    int i16 = i15 + 1;
                    fArr2[i15] = (((float) (i13 + i12)) * f9) / f10;
                    if (i7 == 0 && i12 == 0) {
                        i2 = i12;
                    } else {
                        if (i7 == 72) {
                            i2 = i12;
                            if (i2 != 1) {
                            }
                        } else {
                            i2 = i12;
                        }
                        i5 = i16;
                        i4 = i14;
                        i8 = i2 + 1;
                        i3 = i13;
                        i6 = i10;
                        f2 = f7;
                        f5 = f8;
                        f = f9;
                        radians = f10;
                    }
                    System.arraycopy(fArr, i14 - 3, fArr, i14, 3);
                    i14 += 3;
                    System.arraycopy(fArr2, i16 - 2, fArr2, i16, 2);
                    i16 += 2;
                    i5 = i16;
                    i4 = i14;
                    i8 = i2 + 1;
                    i3 = i13;
                    i6 = i10;
                    f2 = f7;
                    f5 = f8;
                    f = f9;
                    radians = f10;
                }
                float f11 = radians;
                float f12 = f;
                float f13 = f2;
                int i17 = i3;
                int i18 = i6;
                float f14 = f5;
                i7++;
                f2 = f13;
            }
            i3 = i6;
        }
        return new Projection(new Mesh(new SubMesh(0, fArr, fArr2, 1)), i);
    }
}
