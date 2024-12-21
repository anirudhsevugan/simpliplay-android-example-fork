package com.google.android.exoplayer2.text.ssa;

import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.Collections;
import java.util.List;

final class SsaSubtitle implements Subtitle {
    private final List a;
    private final List b;

    public SsaSubtitle(List list, List list2) {
        this.a = list;
        this.b = list2;
    }

    public final int a(long j) {
        int a2 = Util.a(this.b, (Comparable) Long.valueOf(j));
        if (a2 < this.b.size()) {
            return a2;
        }
        return -1;
    }

    public final long a(int i) {
        boolean z = true;
        Assertions.a(i >= 0);
        if (i >= this.b.size()) {
            z = false;
        }
        Assertions.a(z);
        return ((Long) this.b.get(i)).longValue();
    }

    public final int b() {
        return this.b.size();
    }

    public final List b(long j) {
        int a2 = Util.a(this.b, (Comparable) Long.valueOf(j), false);
        return a2 == -1 ? Collections.emptyList() : (List) this.a.get(a2);
    }
}
