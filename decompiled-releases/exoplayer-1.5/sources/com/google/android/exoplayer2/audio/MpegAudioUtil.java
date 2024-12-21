package com.google.android.exoplayer2.audio;

public final class MpegAudioUtil {
    /* access modifiers changed from: private */
    public static final String[] a = {"audio/mpeg-L1", "audio/mpeg-L2", "audio/mpeg"};
    /* access modifiers changed from: private */
    public static final int[] b = {44100, 48000, 32000};
    /* access modifiers changed from: private */
    public static final int[] c = {32000, 64000, 96000, 128000, 160000, 192000, 224000, 256000, 288000, 320000, 352000, 384000, 416000, 448000};
    /* access modifiers changed from: private */
    public static final int[] d = {32000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 176000, 192000, 224000, 256000};
    /* access modifiers changed from: private */
    public static final int[] e = {32000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 160000, 192000, 224000, 256000, 320000, 384000};
    /* access modifiers changed from: private */
    public static final int[] f = {32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 160000, 192000, 224000, 256000, 320000};
    /* access modifiers changed from: private */
    public static final int[] g = {8000, 16000, 24000, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000};

    public final class Header {
        public int a;
        public String b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;

        /* JADX WARNING: Removed duplicated region for block: B:23:0x0052  */
        /* JADX WARNING: Removed duplicated region for block: B:28:0x006f  */
        /* JADX WARNING: Removed duplicated region for block: B:40:0x00a3  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean a(int r9) {
            /*
                r8 = this;
                boolean r0 = com.google.android.exoplayer2.audio.MpegAudioUtil.d(r9)
                r1 = 0
                if (r0 != 0) goto L_0x0008
                return r1
            L_0x0008:
                int r0 = r9 >>> 19
                r2 = 3
                r0 = r0 & r2
                r3 = 1
                if (r0 != r3) goto L_0x0010
                return r1
            L_0x0010:
                int r4 = r9 >>> 17
                r4 = r4 & r2
                if (r4 != 0) goto L_0x0016
                return r1
            L_0x0016:
                int r5 = r9 >>> 12
                r6 = 15
                r5 = r5 & r6
                if (r5 == 0) goto L_0x00a7
                if (r5 != r6) goto L_0x0021
                goto L_0x00a7
            L_0x0021:
                int r6 = r9 >>> 10
                r6 = r6 & r2
                if (r6 != r2) goto L_0x0027
                return r1
            L_0x0027:
                r8.a = r0
                java.lang.String[] r1 = com.google.android.exoplayer2.audio.MpegAudioUtil.a
                int r7 = 3 - r4
                r1 = r1[r7]
                r8.b = r1
                int[] r1 = com.google.android.exoplayer2.audio.MpegAudioUtil.b
                r1 = r1[r6]
                r8.d = r1
                r6 = 2
                if (r0 != r6) goto L_0x0042
                int r1 = r1 / r6
            L_0x003f:
                r8.d = r1
                goto L_0x0047
            L_0x0042:
                if (r0 != 0) goto L_0x0047
                int r1 = r1 / 4
                goto L_0x003f
            L_0x0047:
                int r1 = r9 >>> 9
                r1 = r1 & r3
                int r7 = com.google.android.exoplayer2.audio.MpegAudioUtil.b(r0, r4)
                r8.g = r7
                if (r4 != r2) goto L_0x006f
                if (r0 != r2) goto L_0x005c
                int[] r0 = com.google.android.exoplayer2.audio.MpegAudioUtil.c
                int r5 = r5 - r3
                r0 = r0[r5]
                goto L_0x0063
            L_0x005c:
                int[] r0 = com.google.android.exoplayer2.audio.MpegAudioUtil.d
                int r5 = r5 - r3
                r0 = r0[r5]
            L_0x0063:
                r8.f = r0
                int r0 = r0 * 12
                int r4 = r8.d
                int r0 = r0 / r4
                int r0 = r0 + r1
                int r0 = r0 << r6
            L_0x006c:
                r8.c = r0
                goto L_0x009e
            L_0x006f:
                if (r0 != r2) goto L_0x0085
                if (r4 != r6) goto L_0x007b
                int[] r0 = com.google.android.exoplayer2.audio.MpegAudioUtil.e
                int r5 = r5 - r3
                r0 = r0[r5]
                goto L_0x0082
            L_0x007b:
                int[] r0 = com.google.android.exoplayer2.audio.MpegAudioUtil.f
                int r5 = r5 - r3
                r0 = r0[r5]
            L_0x0082:
                r8.f = r0
                goto L_0x0093
            L_0x0085:
                int[] r0 = com.google.android.exoplayer2.audio.MpegAudioUtil.g
                int r5 = r5 - r3
                r0 = r0[r5]
                r8.f = r0
                if (r4 != r3) goto L_0x0093
                r0 = 72
                goto L_0x0095
            L_0x0093:
                r0 = 144(0x90, float:2.02E-43)
            L_0x0095:
                int r4 = r8.f
                int r0 = r0 * r4
                int r4 = r8.d
                int r0 = r0 / r4
                int r0 = r0 + r1
                goto L_0x006c
            L_0x009e:
                int r9 = r9 >> 6
                r9 = r9 & r2
                if (r9 != r2) goto L_0x00a4
                r6 = 1
            L_0x00a4:
                r8.e = r6
                return r3
            L_0x00a7:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.audio.MpegAudioUtil.Header.a(int):boolean");
        }
    }

    public static int a(int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        if (!d(i) || (i2 = (i >>> 19) & 3) == 1 || (i3 = (i >>> 17) & 3) == 0 || (i4 = (i >>> 12) & 15) == 0 || i4 == 15 || (i5 = (i >>> 10) & 3) == 3) {
            return -1;
        }
        int i6 = b[i5];
        if (i2 == 2) {
            i6 /= 2;
        } else if (i2 == 0) {
            i6 /= 4;
        }
        int i7 = (i >>> 9) & 1;
        if (i3 == 3) {
            return ((((i2 == 3 ? c[i4 - 1] : d[i4 - 1]) * 12) / i6) + i7) << 2;
        }
        int i8 = i2 == 3 ? i3 == 2 ? e[i4 - 1] : f[i4 - 1] : g[i4 - 1];
        int i9 = 144;
        if (i2 == 3) {
            return ((i8 * 144) / i6) + i7;
        }
        if (i3 == 1) {
            i9 = 72;
        }
        return ((i9 * i8) / i6) + i7;
    }

    public static int b(int i) {
        int i2;
        int i3;
        if (!d(i) || (i2 = (i >>> 19) & 3) == 1 || (i3 = (i >>> 17) & 3) == 0) {
            return -1;
        }
        int i4 = (i >>> 12) & 15;
        int i5 = (i >>> 10) & 3;
        if (i4 == 0 || i4 == 15 || i5 == 3) {
            return -1;
        }
        return b(i2, i3);
    }

    /* access modifiers changed from: private */
    public static int b(int i, int i2) {
        switch (i2) {
            case 1:
                return i == 3 ? 1152 : 576;
            case 2:
                return 1152;
            case 3:
                return 384;
            default:
                throw new IllegalArgumentException();
        }
    }

    /* access modifiers changed from: private */
    public static boolean d(int i) {
        return (i & -2097152) == -2097152;
    }
}
