package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.Format;

public final class Track {
    public final int a;
    public final int b;
    public final long c;
    public final long d;
    public final long e;
    public final Format f;
    public final int g;
    public final long[] h;
    public final long[] i;
    public final int j;
    private final TrackEncryptionBox[] k;

    public Track(int i2, int i3, long j2, long j3, long j4, Format format, int i4, TrackEncryptionBox[] trackEncryptionBoxArr, int i5, long[] jArr, long[] jArr2) {
        this.a = i2;
        this.b = i3;
        this.c = j2;
        this.d = j3;
        this.e = j4;
        this.f = format;
        this.g = i4;
        this.k = trackEncryptionBoxArr;
        this.j = i5;
        this.h = jArr;
        this.i = jArr2;
    }

    public final TrackEncryptionBox a(int i2) {
        TrackEncryptionBox[] trackEncryptionBoxArr = this.k;
        if (trackEncryptionBoxArr == null) {
            return null;
        }
        return trackEncryptionBoxArr[i2];
    }
}
