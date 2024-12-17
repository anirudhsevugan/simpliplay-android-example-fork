package com.google.android.exoplayer2.source;

import androidx.core.location.LocationRequestCompat;

public class CompositeSequenceableLoader implements SequenceableLoader {
    private SequenceableLoader[] a;

    public CompositeSequenceableLoader(SequenceableLoader[] sequenceableLoaderArr) {
        this.a = sequenceableLoaderArr;
    }

    public final void a_(long j) {
        for (SequenceableLoader a_ : this.a) {
            a_.a_(j);
        }
    }

    public final boolean c(long j) {
        long j2 = j;
        boolean z = false;
        while (true) {
            long e = e();
            if (e == Long.MIN_VALUE) {
                break;
            }
            boolean z2 = false;
            for (SequenceableLoader sequenceableLoader : this.a) {
                long e2 = sequenceableLoader.e();
                boolean z3 = e2 != Long.MIN_VALUE && e2 <= j2;
                if (e2 == e || z3) {
                    z2 |= sequenceableLoader.c(j2);
                }
            }
            z |= z2;
            if (!z2) {
                break;
            }
        }
        return z;
    }

    public final long d() {
        long j = Long.MAX_VALUE;
        for (SequenceableLoader d : this.a) {
            long d2 = d.d();
            if (d2 != Long.MIN_VALUE) {
                j = Math.min(j, d2);
            }
        }
        if (j == LocationRequestCompat.PASSIVE_INTERVAL) {
            return Long.MIN_VALUE;
        }
        return j;
    }

    public final long e() {
        long j = Long.MAX_VALUE;
        for (SequenceableLoader e : this.a) {
            long e2 = e.e();
            if (e2 != Long.MIN_VALUE) {
                j = Math.min(j, e2);
            }
        }
        if (j == LocationRequestCompat.PASSIVE_INTERVAL) {
            return Long.MIN_VALUE;
        }
        return j;
    }

    public final boolean f() {
        for (SequenceableLoader f : this.a) {
            if (f.f()) {
                return true;
            }
        }
        return false;
    }
}
