package com.google.android.exoplayer2.text.subrip;

import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.Collections;
import java.util.List;

final class SubripSubtitle implements Subtitle {
    private final Cue[] a;
    private final long[] b;

    public SubripSubtitle(Cue[] cueArr, long[] jArr) {
        this.a = cueArr;
        this.b = jArr;
    }

    public final int a(long j) {
        int b2 = Util.b(this.b, j, false);
        if (b2 < this.b.length) {
            return b2;
        }
        return -1;
    }

    public final long a(int i) {
        boolean z = true;
        Assertions.a(i >= 0);
        if (i >= this.b.length) {
            z = false;
        }
        Assertions.a(z);
        return this.b[i];
    }

    public final int b() {
        return this.b.length;
    }

    public final List b(long j) {
        int a2 = Util.a(this.b, j, false);
        return (a2 == -1 || this.a[a2] == Cue.a) ? Collections.emptyList() : Collections.singletonList(this.a[a2]);
    }
}
