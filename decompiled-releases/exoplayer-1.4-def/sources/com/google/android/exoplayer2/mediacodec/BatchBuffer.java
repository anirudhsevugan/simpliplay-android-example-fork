package com.google.android.exoplayer2.mediacodec;

import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.util.Assertions;

final class BatchBuffer extends DecoderInputBuffer {
    long g;
    int h;
    private int i = 32;

    public BatchBuffer() {
        super(2);
    }

    public final void a() {
        super.a();
        this.h = 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0033, code lost:
        r0 = r5.c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(com.google.android.exoplayer2.decoder.DecoderInputBuffer r5) {
        /*
            r4 = this;
            boolean r0 = r5.g()
            r1 = 1
            r0 = r0 ^ r1
            com.google.android.exoplayer2.util.Assertions.a((boolean) r0)
            boolean r0 = r5.e()
            r0 = r0 ^ r1
            com.google.android.exoplayer2.util.Assertions.a((boolean) r0)
            boolean r0 = r5.c()
            r0 = r0 ^ r1
            com.google.android.exoplayer2.util.Assertions.a((boolean) r0)
            boolean r0 = r4.i()
            r2 = 0
            if (r0 == 0) goto L_0x004c
            int r0 = r4.h
            int r3 = r4.i
            if (r0 < r3) goto L_0x0028
        L_0x0026:
            r0 = 0
            goto L_0x004d
        L_0x0028:
            boolean r0 = r5.d_()
            boolean r3 = r4.d_()
            if (r0 == r3) goto L_0x0033
            goto L_0x0026
        L_0x0033:
            java.nio.ByteBuffer r0 = r5.c
            if (r0 == 0) goto L_0x004c
            java.nio.ByteBuffer r3 = r4.c
            if (r3 == 0) goto L_0x004c
            java.nio.ByteBuffer r3 = r4.c
            int r3 = r3.position()
            int r0 = r0.remaining()
            int r3 = r3 + r0
            r0 = 3072000(0x2ee000, float:4.304789E-39)
            if (r3 <= r0) goto L_0x004c
            goto L_0x0026
        L_0x004c:
            r0 = 1
        L_0x004d:
            if (r0 != 0) goto L_0x0050
            return r2
        L_0x0050:
            int r0 = r4.h
            int r2 = r0 + 1
            r4.h = r2
            if (r0 != 0) goto L_0x0064
            long r2 = r5.e
            r4.e = r2
            boolean r0 = r5.d()
            if (r0 == 0) goto L_0x0064
            r4.a = r1
        L_0x0064:
            boolean r0 = r5.d_()
            if (r0 == 0) goto L_0x006e
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            r4.a = r0
        L_0x006e:
            java.nio.ByteBuffer r0 = r5.c
            if (r0 == 0) goto L_0x007e
            int r2 = r0.remaining()
            r4.d(r2)
            java.nio.ByteBuffer r2 = r4.c
            r2.put(r0)
        L_0x007e:
            long r2 = r5.e
            r4.g = r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.mediacodec.BatchBuffer.a(com.google.android.exoplayer2.decoder.DecoderInputBuffer):boolean");
    }

    public final void e(int i2) {
        Assertions.a(i2 > 0);
        this.i = i2;
    }

    public final boolean i() {
        return this.h > 0;
    }
}
