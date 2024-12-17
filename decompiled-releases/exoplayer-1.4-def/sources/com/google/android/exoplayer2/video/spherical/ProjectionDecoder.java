package com.google.android.exoplayer2.video.spherical;

import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.spherical.Projection;
import java.util.ArrayList;
import java.util.zip.Inflater;

final class ProjectionDecoder {
    private static int a(int i) {
        return (-(i & 1)) ^ (i >> 1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0046, code lost:
        r0.c(r5);
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0052 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0053  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.exoplayer2.video.spherical.Projection a(byte[] r7, int r8) {
        /*
            com.google.android.exoplayer2.util.ParsableByteArray r0 = new com.google.android.exoplayer2.util.ParsableByteArray
            r0.<init>((byte[]) r7)
            r7 = 4
            r1 = 1
            r2 = 0
            r3 = 0
            r0.e(r7)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x004e }
            int r7 = r0.j()     // Catch:{ ArrayIndexOutOfBoundsException -> 0x004e }
            r0.d(r2)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x004e }
            r4 = 1886547818(0x70726f6a, float:3.0012025E29)
            if (r7 != r4) goto L_0x001a
            r7 = 1
            goto L_0x001b
        L_0x001a:
            r7 = 0
        L_0x001b:
            if (r7 == 0) goto L_0x0049
            r7 = 8
            r0.e(r7)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x004e }
            int r7 = r0.b     // Catch:{ ArrayIndexOutOfBoundsException -> 0x004e }
            int r4 = r0.c     // Catch:{ ArrayIndexOutOfBoundsException -> 0x004e }
        L_0x0026:
            if (r7 >= r4) goto L_0x004f
            int r5 = r0.j()     // Catch:{ ArrayIndexOutOfBoundsException -> 0x004e }
            int r5 = r5 + r7
            if (r5 <= r7) goto L_0x004f
            if (r5 <= r4) goto L_0x0032
            goto L_0x004f
        L_0x0032:
            int r7 = r0.j()     // Catch:{ ArrayIndexOutOfBoundsException -> 0x004e }
            r6 = 2037673328(0x79746d70, float:7.9321256E34)
            if (r7 == r6) goto L_0x0046
            r6 = 1836279920(0x6d736870, float:4.7081947E27)
            if (r7 != r6) goto L_0x0041
            goto L_0x0046
        L_0x0041:
            r0.d(r5)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x004e }
            r7 = r5
            goto L_0x0026
        L_0x0046:
            r0.c(r5)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x004e }
        L_0x0049:
            java.util.ArrayList r7 = a((com.google.android.exoplayer2.util.ParsableByteArray) r0)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x004e }
            goto L_0x0050
        L_0x004e:
            r7 = move-exception
        L_0x004f:
            r7 = r3
        L_0x0050:
            if (r7 != 0) goto L_0x0053
            return r3
        L_0x0053:
            int r0 = r7.size()
            switch(r0) {
                case 1: goto L_0x006d;
                case 2: goto L_0x005b;
                default: goto L_0x005a;
            }
        L_0x005a:
            return r3
        L_0x005b:
            com.google.android.exoplayer2.video.spherical.Projection r0 = new com.google.android.exoplayer2.video.spherical.Projection
            java.lang.Object r2 = r7.get(r2)
            com.google.android.exoplayer2.video.spherical.Projection$Mesh r2 = (com.google.android.exoplayer2.video.spherical.Projection.Mesh) r2
            java.lang.Object r7 = r7.get(r1)
            com.google.android.exoplayer2.video.spherical.Projection$Mesh r7 = (com.google.android.exoplayer2.video.spherical.Projection.Mesh) r7
            r0.<init>(r2, r7, r8)
            return r0
        L_0x006d:
            com.google.android.exoplayer2.video.spherical.Projection r0 = new com.google.android.exoplayer2.video.spherical.Projection
            java.lang.Object r7 = r7.get(r2)
            com.google.android.exoplayer2.video.spherical.Projection$Mesh r7 = (com.google.android.exoplayer2.video.spherical.Projection.Mesh) r7
            r0.<init>(r7, r8)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.video.spherical.ProjectionDecoder.a(byte[], int):com.google.android.exoplayer2.video.spherical.Projection");
    }

    private static ArrayList a(ParsableByteArray parsableByteArray) {
        if (parsableByteArray.c() != 0) {
            return null;
        }
        parsableByteArray.e(7);
        int j = parsableByteArray.j();
        if (j == 1684433976) {
            ParsableByteArray parsableByteArray2 = new ParsableByteArray();
            Inflater inflater = new Inflater(true);
            try {
                if (!Util.a(parsableByteArray, parsableByteArray2, inflater)) {
                    return null;
                }
                parsableByteArray = parsableByteArray2;
            } finally {
                inflater.end();
            }
        } else if (j != 1918990112) {
            return null;
        }
        return b(parsableByteArray);
    }

    private static ArrayList b(ParsableByteArray parsableByteArray) {
        ArrayList arrayList = new ArrayList();
        int i = parsableByteArray.b;
        int i2 = parsableByteArray.c;
        while (i < i2) {
            int j = parsableByteArray.j() + i;
            if (j <= i || j > i2) {
                return null;
            }
            if (parsableByteArray.j() == 1835365224) {
                Projection.Mesh c = c(parsableByteArray);
                if (c == null) {
                    return null;
                }
                arrayList.add(c);
            }
            parsableByteArray.d(j);
            i = j;
        }
        return arrayList;
    }

    private static Projection.Mesh c(ParsableByteArray parsableByteArray) {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int j = parsableByteArray.j();
        Projection.Mesh mesh = null;
        if (j > 10000) {
            return null;
        }
        float[] fArr = new float[j];
        for (int i = 0; i < j; i++) {
            fArr[i] = Float.intBitsToFloat(parsableByteArray.j());
        }
        int j2 = parsableByteArray.j();
        if (j2 > 32000) {
            return null;
        }
        double d = 2.0d;
        double log = Math.log(2.0d);
        double d2 = (double) j;
        Double.isNaN(d2);
        int ceil = (int) Math.ceil(Math.log(d2 * 2.0d) / log);
        ParsableBitArray parsableBitArray = new ParsableBitArray(parsableByteArray2.a);
        parsableBitArray.a(parsableByteArray2.b << 3);
        float[] fArr2 = new float[(j2 * 5)];
        int i2 = 5;
        int[] iArr = new int[5];
        int i3 = 0;
        int i4 = 0;
        while (i3 < j2) {
            int i5 = 0;
            while (i5 < i2) {
                int a = iArr[i5] + a(parsableBitArray.c(ceil));
                if (a >= j || a < 0) {
                    return null;
                }
                fArr2[i4] = fArr[a];
                iArr[i5] = a;
                i5++;
                i4++;
                i2 = 5;
            }
            i3++;
            i2 = 5;
        }
        parsableBitArray.a((parsableBitArray.b() + 7) & -8);
        int i6 = 32;
        int c = parsableBitArray.c(32);
        Projection.SubMesh[] subMeshArr = new Projection.SubMesh[c];
        int i7 = 0;
        while (i7 < c) {
            int c2 = parsableBitArray.c(8);
            int c3 = parsableBitArray.c(8);
            int c4 = parsableBitArray.c(i6);
            if (c4 > 128000) {
                return mesh;
            }
            int i8 = c;
            double d3 = (double) j2;
            Double.isNaN(d3);
            int ceil2 = (int) Math.ceil(Math.log(d3 * d) / log);
            float[] fArr3 = new float[(c4 * 3)];
            float[] fArr4 = new float[(c4 << 1)];
            int i9 = 0;
            int i10 = 0;
            while (i9 < c4) {
                int a2 = i10 + a(parsableBitArray.c(ceil2));
                if (a2 < 0 || a2 >= j2) {
                    return null;
                }
                int i11 = i9 * 3;
                int i12 = a2 * 5;
                fArr3[i11] = fArr2[i12];
                fArr3[i11 + 1] = fArr2[i12 + 1];
                fArr3[i11 + 2] = fArr2[i12 + 2];
                int i13 = i9 << 1;
                fArr4[i13] = fArr2[i12 + 3];
                fArr4[i13 + 1] = fArr2[i12 + 4];
                i9++;
                i10 = a2;
                mesh = null;
            }
            subMeshArr[i7] = new Projection.SubMesh(c2, fArr3, fArr4, c3);
            i7++;
            mesh = mesh;
            c = i8;
            i6 = 32;
            d = 2.0d;
        }
        return new Projection.Mesh(subMeshArr);
    }
}
