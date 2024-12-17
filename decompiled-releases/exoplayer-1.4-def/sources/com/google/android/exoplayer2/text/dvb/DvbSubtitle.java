package com.google.android.exoplayer2.text.dvb;

import com.google.android.exoplayer2.text.Subtitle;
import java.util.List;

final class DvbSubtitle implements Subtitle {
    private final List a;

    public DvbSubtitle(List list) {
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
