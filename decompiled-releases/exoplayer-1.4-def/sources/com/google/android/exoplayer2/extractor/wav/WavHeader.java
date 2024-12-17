package com.google.android.exoplayer2.extractor.wav;

final class WavHeader {
    public final int a;
    public final int b;
    public final int c;
    public final int d;
    public final int e;
    public final byte[] f;

    public WavHeader(int i, int i2, int i3, int i4, int i5, byte[] bArr) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = i5;
        this.f = bArr;
    }
}
