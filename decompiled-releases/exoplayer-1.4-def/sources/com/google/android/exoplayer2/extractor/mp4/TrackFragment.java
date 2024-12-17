package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.util.ParsableByteArray;

final class TrackFragment {
    public DefaultSampleValues a;
    public long b;
    public long c;
    public int d;
    public int e;
    public long[] f = new long[0];
    public int[] g = new int[0];
    public int[] h = new int[0];
    public int[] i = new int[0];
    public long[] j = new long[0];
    public boolean[] k = new boolean[0];
    public boolean l;
    public boolean[] m = new boolean[0];
    public TrackEncryptionBox n;
    public final ParsableByteArray o = new ParsableByteArray();
    public boolean p;
    public long q;
    public boolean r;

    public final void a() {
        this.d = 0;
        this.q = 0;
        this.r = false;
        this.l = false;
        this.p = false;
        this.n = null;
    }

    public final void a(int i2) {
        this.o.a(i2);
        this.l = true;
        this.p = true;
    }

    public final void a(int i2, int i3) {
        this.d = i2;
        this.e = i3;
        if (this.g.length < i2) {
            this.f = new long[i2];
            this.g = new int[i2];
        }
        if (this.h.length < i3) {
            int i4 = (i3 * 125) / 100;
            this.h = new int[i4];
            this.i = new int[i4];
            this.j = new long[i4];
            this.k = new boolean[i4];
            this.m = new boolean[i4];
        }
    }

    public final void a(ParsableByteArray parsableByteArray) {
        parsableByteArray.a(this.o.a, 0, this.o.c);
        this.o.d(0);
        this.p = false;
    }

    public final long b(int i2) {
        return this.j[i2] + ((long) this.i[i2]);
    }

    public final boolean c(int i2) {
        return this.l && this.m[i2];
    }
}
