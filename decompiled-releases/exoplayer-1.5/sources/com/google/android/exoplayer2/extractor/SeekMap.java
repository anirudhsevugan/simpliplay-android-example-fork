package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.util.Assertions;

public interface SeekMap {

    public final class SeekPoints {
        public final SeekPoint a;
        public final SeekPoint b;

        public SeekPoints(SeekPoint seekPoint) {
            this(seekPoint, seekPoint);
        }

        public SeekPoints(SeekPoint seekPoint, SeekPoint seekPoint2) {
            this.a = (SeekPoint) Assertions.b((Object) seekPoint);
            this.b = (SeekPoint) Assertions.b((Object) seekPoint2);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                SeekPoints seekPoints = (SeekPoints) obj;
                return this.a.equals(seekPoints.a) && this.b.equals(seekPoints.b);
            }
        }

        public final int hashCode() {
            return (this.a.hashCode() * 31) + this.b.hashCode();
        }

        public final String toString() {
            String str;
            String valueOf = String.valueOf(this.a);
            if (this.a.equals(this.b)) {
                str = "";
            } else {
                String valueOf2 = String.valueOf(this.b);
                str = new StringBuilder(String.valueOf(valueOf2).length() + 2).append(", ").append(valueOf2).toString();
            }
            return new StringBuilder(String.valueOf(valueOf).length() + 2 + String.valueOf(str).length()).append("[").append(valueOf).append(str).append("]").toString();
        }
    }

    public class Unseekable implements SeekMap {
        private final long a;
        private final SeekPoints b;

        public Unseekable(long j) {
            this(j, 0);
        }

        public Unseekable(long j, long j2) {
            this.a = j;
            this.b = new SeekPoints(j2 == 0 ? SeekPoint.a : new SeekPoint(0, j2));
        }

        public final SeekPoints a(long j) {
            return this.b;
        }

        public final boolean a() {
            return false;
        }

        public final long b() {
            return this.a;
        }
    }

    SeekPoints a(long j);

    boolean a();

    long b();
}
