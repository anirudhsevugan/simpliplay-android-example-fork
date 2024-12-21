package com.google.android.exoplayer2.util;

import androidx.core.location.LocationRequestCompat;
import gnu.expr.Declaration;

public final class TimestampAdjuster {
    private boolean a;
    private long b;
    private long c;
    private long d = -9223372036854775807L;

    public TimestampAdjuster(long j) {
        this.b = j;
    }

    public static long d(long j) {
        return (j * 1000000) / 90000;
    }

    public static long e(long j) {
        return f(j) % Declaration.ENUM_ACCESS;
    }

    private static long f(long j) {
        return (j * 90000) / 1000000;
    }

    public final synchronized long a() {
        return this.b;
    }

    public final synchronized void a(long j) {
        this.b = j;
        this.d = -9223372036854775807L;
        this.a = false;
    }

    public final synchronized void a(boolean z, long j) {
        if (z) {
            try {
                if (!this.a) {
                    this.b = j;
                    this.a = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (!z || j != this.b) {
            while (this.d == -9223372036854775807L) {
                wait();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        if (r0 == androidx.core.location.LocationRequestCompat.PASSIVE_INTERVAL) goto L_0x001e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001e, code lost:
        return -9223372036854775807L;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized long b() {
        /*
            r7 = this;
            monitor-enter(r7)
            long r0 = r7.d     // Catch:{ all -> 0x001f }
            r2 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0011
            long r2 = r7.c     // Catch:{ all -> 0x001f }
            long r0 = r0 + r2
            monitor-exit(r7)
            return r0
        L_0x0011:
            long r0 = r7.b     // Catch:{ all -> 0x001f }
            r4 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            monitor-exit(r7)
            if (r6 == 0) goto L_0x001e
            return r0
        L_0x001e:
            return r2
        L_0x001f:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.util.TimestampAdjuster.b():long");
    }

    public final synchronized long b(long j) {
        if (j == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        long j2 = this.d;
        if (j2 != -9223372036854775807L) {
            long f = f(j2);
            long j3 = (Declaration.TRANSIENT_ACCESS + f) / Declaration.ENUM_ACCESS;
            long j4 = ((j3 - 1) * Declaration.ENUM_ACCESS) + j;
            j += j3 * Declaration.ENUM_ACCESS;
            if (Math.abs(j4 - f) < Math.abs(j - f)) {
                j = j4;
            }
        }
        return c(d(j));
    }

    public final synchronized long c() {
        if (this.b == LocationRequestCompat.PASSIVE_INTERVAL) {
            return 0;
        }
        if (this.d == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        return this.c;
    }

    public final synchronized long c(long j) {
        if (j == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        if (this.d != -9223372036854775807L) {
            this.d = j;
        } else {
            long j2 = this.b;
            if (j2 != LocationRequestCompat.PASSIVE_INTERVAL) {
                this.c = j2 - j;
            }
            this.d = j;
            notifyAll();
        }
        return j + this.c;
    }
}
