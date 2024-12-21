package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;

public final class SilenceSkippingAudioProcessor extends BaseAudioProcessor {
    boolean d;
    long e;
    private int f;
    private byte[] g;
    private byte[] h;
    private int i;
    private int j;
    private int k;
    private boolean l;

    public SilenceSkippingAudioProcessor() {
        this((byte) 0);
    }

    private SilenceSkippingAudioProcessor(byte b) {
        Assertions.a(true);
        this.g = Util.f;
        this.h = Util.f;
    }

    private int a(long j2) {
        return (int) ((j2 * ((long) this.b.b)) / 1000000);
    }

    private void a(ByteBuffer byteBuffer, byte[] bArr, int i2) {
        int min = Math.min(byteBuffer.remaining(), this.k);
        int i3 = this.k - min;
        System.arraycopy(bArr, i2 - i3, this.h, 0, i3);
        byteBuffer.position(byteBuffer.limit() - min);
        byteBuffer.get(this.h, i3, min);
    }

    private void a(byte[] bArr, int i2) {
        a(i2).put(bArr, 0, i2).flip();
        if (i2 > 0) {
            this.l = true;
        }
    }

    private int b(ByteBuffer byteBuffer) {
        for (int position = byteBuffer.position(); position < byteBuffer.limit(); position += 2) {
            if (Math.abs(byteBuffer.getShort(position)) > 1024) {
                int i2 = this.f;
                return i2 * (position / i2);
            }
        }
        return byteBuffer.limit();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.nio.ByteBuffer r10) {
        /*
            r9 = this;
        L_0x0000:
            boolean r0 = r10.hasRemaining()
            if (r0 == 0) goto L_0x0110
            boolean r0 = r9.g()
            if (r0 != 0) goto L_0x0110
            int r0 = r9.i
            r1 = 2
            r2 = 1
            r3 = 0
            switch(r0) {
                case 0: goto L_0x00b8;
                case 1: goto L_0x0045;
                case 2: goto L_0x001a;
                default: goto L_0x0014;
            }
        L_0x0014:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            r10.<init>()
            throw r10
        L_0x001a:
            int r0 = r10.limit()
            int r1 = r9.b((java.nio.ByteBuffer) r10)
            r10.limit(r1)
            long r4 = r9.e
            int r2 = r10.remaining()
            int r6 = r9.f
            int r2 = r2 / r6
            long r6 = (long) r2
            long r4 = r4 + r6
            r9.e = r4
            byte[] r2 = r9.h
            int r4 = r9.k
            r9.a(r10, r2, r4)
            if (r1 >= r0) goto L_0x0000
            byte[] r1 = r9.h
            int r2 = r9.k
            r9.a(r1, r2)
            r9.i = r3
            goto L_0x00b3
        L_0x0045:
            int r0 = r10.limit()
            int r4 = r9.b((java.nio.ByteBuffer) r10)
            int r5 = r10.position()
            int r5 = r4 - r5
            byte[] r6 = r9.g
            int r7 = r6.length
            int r8 = r9.j
            int r7 = r7 - r8
            if (r4 >= r0) goto L_0x0065
            if (r5 >= r7) goto L_0x0065
            r9.a(r6, r8)
            r9.j = r3
            r9.i = r3
            goto L_0x0000
        L_0x0065:
            int r4 = java.lang.Math.min(r5, r7)
            int r5 = r10.position()
            int r5 = r5 + r4
            r10.limit(r5)
            byte[] r5 = r9.g
            int r6 = r9.j
            r10.get(r5, r6, r4)
            int r5 = r9.j
            int r5 = r5 + r4
            r9.j = r5
            byte[] r4 = r9.g
            int r6 = r4.length
            if (r5 != r6) goto L_0x00b3
            boolean r6 = r9.l
            if (r6 == 0) goto L_0x009c
            int r5 = r9.k
            r9.a(r4, r5)
            long r4 = r9.e
            int r6 = r9.j
            int r7 = r9.k
            int r2 = r7 << 1
            int r6 = r6 - r2
            int r2 = r9.f
            int r6 = r6 / r2
            long r6 = (long) r6
            long r4 = r4 + r6
            r9.e = r4
            goto L_0x00a8
        L_0x009c:
            long r6 = r9.e
            int r2 = r9.k
            int r5 = r5 - r2
            int r2 = r9.f
            int r5 = r5 / r2
            long r4 = (long) r5
            long r6 = r6 + r4
            r9.e = r6
        L_0x00a8:
            byte[] r2 = r9.g
            int r4 = r9.j
            r9.a(r10, r2, r4)
            r9.j = r3
            r9.i = r1
        L_0x00b3:
            r10.limit(r0)
            goto L_0x0000
        L_0x00b8:
            int r0 = r10.limit()
            int r3 = r10.position()
            byte[] r4 = r9.g
            int r4 = r4.length
            int r3 = r3 + r4
            int r3 = java.lang.Math.min(r0, r3)
            r10.limit(r3)
            int r3 = r10.limit()
            int r3 = r3 - r1
        L_0x00d0:
            int r1 = r10.position()
            if (r3 < r1) goto L_0x00ec
            short r1 = r10.getShort(r3)
            int r1 = java.lang.Math.abs(r1)
            r4 = 1024(0x400, float:1.435E-42)
            if (r1 <= r4) goto L_0x00e9
            int r1 = r9.f
            int r3 = r3 / r1
            int r3 = r3 * r1
            int r3 = r3 + r1
            goto L_0x00f0
        L_0x00e9:
            int r3 = r3 + -2
            goto L_0x00d0
        L_0x00ec:
            int r3 = r10.position()
        L_0x00f0:
            int r1 = r10.position()
            if (r3 != r1) goto L_0x00f9
            r9.i = r2
            goto L_0x00b3
        L_0x00f9:
            r10.limit(r3)
            int r1 = r10.remaining()
            java.nio.ByteBuffer r3 = r9.a((int) r1)
            java.nio.ByteBuffer r3 = r3.put(r10)
            r3.flip()
            if (r1 <= 0) goto L_0x00b3
            r9.l = r2
            goto L_0x00b3
        L_0x0110:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.audio.SilenceSkippingAudioProcessor.a(java.nio.ByteBuffer):void");
    }

    public final boolean a() {
        return this.d;
    }

    public final AudioProcessor.AudioFormat b(AudioProcessor.AudioFormat audioFormat) {
        if (audioFormat.d == 2) {
            return this.d ? audioFormat : AudioProcessor.AudioFormat.a;
        }
        throw new AudioProcessor.UnhandledAudioFormatException(audioFormat);
    }

    /* access modifiers changed from: protected */
    public final void h() {
        int i2 = this.j;
        if (i2 > 0) {
            a(this.g, i2);
        }
        if (!this.l) {
            this.e += (long) (this.k / this.f);
        }
    }

    /* access modifiers changed from: protected */
    public final void i() {
        if (this.d) {
            this.f = this.b.e;
            int a = a(150000) * this.f;
            if (this.g.length != a) {
                this.g = new byte[a];
            }
            int a2 = a(20000) * this.f;
            this.k = a2;
            if (this.h.length != a2) {
                this.h = new byte[a2];
            }
        }
        this.i = 0;
        this.e = 0;
        this.j = 0;
        this.l = false;
    }

    /* access modifiers changed from: protected */
    public final void j() {
        this.d = false;
        this.k = 0;
        this.g = Util.f;
        this.h = Util.f;
    }
}
