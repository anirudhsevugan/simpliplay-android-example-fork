package com.google.android.exoplayer2.text.cea;

import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.util.Assertions;
import java.util.Collections;
import java.util.List;

final class CeaSubtitle implements Subtitle {
    private final List a;

    public CeaSubtitle(List list) {
        this.a = list;
    }

    public final int a(long j) {
        return j < 0 ? 0 : -1;
    }

    public final long a(int i) {
        Assertions.a(i == 0);
        return 0;
    }

    public final int b() {
        return 1;
    }

    public final List b(long j) {
        return j >= 0 ? this.a : Collections.emptyList();
    }
}
