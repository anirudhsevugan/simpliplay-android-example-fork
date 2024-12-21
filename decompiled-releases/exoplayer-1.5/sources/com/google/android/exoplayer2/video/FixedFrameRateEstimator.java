package com.google.android.exoplayer2.video;

import java.util.Arrays;

final class FixedFrameRateEstimator {
    Matcher a = new Matcher();
    Matcher b = new Matcher();
    boolean c;
    long d = -9223372036854775807L;
    int e;

    final class Matcher {
        long a;
        long b;
        final boolean[] c = new boolean[15];
        private long d;
        private long e;
        private long f;
        private long g;
        private int h;

        static int b(long j) {
            return (int) (j % 15);
        }

        public final void a() {
            this.a = 0;
            this.g = 0;
            this.b = 0;
            this.h = 0;
            Arrays.fill(this.c, false);
        }

        public final void a(long j) {
            int i;
            long j2 = this.a;
            if (j2 == 0) {
                this.d = j;
            } else if (j2 == 1) {
                long j3 = j - this.d;
                this.e = j3;
                this.b = j3;
                this.g = 1;
            } else {
                long j4 = j - this.f;
                int i2 = (int) (j2 % 15);
                if (Math.abs(j4 - this.e) <= 1000000) {
                    this.g++;
                    this.b += j4;
                    boolean[] zArr = this.c;
                    if (zArr[i2]) {
                        zArr[i2] = false;
                        i = this.h - 1;
                    }
                } else {
                    boolean[] zArr2 = this.c;
                    if (!zArr2[i2]) {
                        zArr2[i2] = true;
                        i = this.h + 1;
                    }
                }
                this.h = i;
            }
            this.a++;
            this.f = j;
        }

        public final boolean b() {
            return this.a > 15 && this.h == 0;
        }

        public final long c() {
            long j = this.g;
            if (j == 0) {
                return 0;
            }
            return this.b / j;
        }
    }

    public final long a() {
        if (this.a.b()) {
            return this.a.b;
        }
        return -9223372036854775807L;
    }

    public final float b() {
        if (!this.a.b()) {
            return -1.0f;
        }
        double c2 = (double) this.a.c();
        Double.isNaN(c2);
        return (float) (1.0E9d / c2);
    }
}
