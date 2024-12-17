package com.google.android.exoplayer2.extractor;

public final class SeekPoint {
    public static final SeekPoint a = new SeekPoint(0, 0);
    public final long b;
    public final long c;

    public SeekPoint(long j, long j2) {
        this.b = j;
        this.c = j2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            SeekPoint seekPoint = (SeekPoint) obj;
            return this.b == seekPoint.b && this.c == seekPoint.c;
        }
    }

    public final int hashCode() {
        return (((int) this.b) * 31) + ((int) this.c);
    }

    public final String toString() {
        long j = this.b;
        return new StringBuilder(60).append("[timeUs=").append(j).append(", position=").append(this.c).append("]").toString();
    }
}
