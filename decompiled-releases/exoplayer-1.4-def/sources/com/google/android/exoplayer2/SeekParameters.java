package com.google.android.exoplayer2;

import androidx.core.location.LocationRequestCompat;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

public final class SeekParameters {
    public static final SeekParameters a = d;
    private static SeekParameters d = new SeekParameters(0, 0);
    public final long b;
    public final long c;

    static {
        new SeekParameters(LocationRequestCompat.PASSIVE_INTERVAL, LocationRequestCompat.PASSIVE_INTERVAL);
        new SeekParameters(LocationRequestCompat.PASSIVE_INTERVAL, 0);
        new SeekParameters(0, LocationRequestCompat.PASSIVE_INTERVAL);
    }

    public SeekParameters(long j, long j2) {
        boolean z = true;
        Assertions.a(j >= 0);
        Assertions.a(j2 < 0 ? false : z);
        this.b = j;
        this.c = j2;
    }

    public final long a(long j, long j2, long j3) {
        long j4 = this.b;
        if (j4 == 0 && this.c == 0) {
            return j;
        }
        long c2 = Util.c(j, j4);
        long b2 = Util.b(j, this.c);
        boolean z = true;
        boolean z2 = c2 <= j2 && j2 <= b2;
        if (c2 > j3 || j3 > b2) {
            z = false;
        }
        return (!z2 || !z) ? z2 ? j2 : z ? j3 : c2 : Math.abs(j2 - j) <= Math.abs(j3 - j) ? j2 : j3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            SeekParameters seekParameters = (SeekParameters) obj;
            return this.b == seekParameters.b && this.c == seekParameters.c;
        }
    }

    public final int hashCode() {
        return (((int) this.b) * 31) + ((int) this.c);
    }
}
