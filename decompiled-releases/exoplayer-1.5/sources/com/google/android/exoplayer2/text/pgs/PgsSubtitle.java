package com.google.android.exoplayer2.text.pgs;

import com.google.android.exoplayer2.text.Subtitle;
import java.util.List;

final class PgsSubtitle implements Subtitle {
    private final List a;

    public PgsSubtitle(List list) {
        this.a = list;
    }

    public final int a(long j) {
        return -1;
    }

    public final long a(int i) {
        return 0;
    }

    public final int b() {
        return 1;
    }

    public final List b(long j) {
        return this.a;
    }
}
