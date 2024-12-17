package com.google.android.exoplayer2.text.webvtt;

import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.util.Assertions;
import java.util.Collections;
import java.util.List;

final class Mp4WebvttSubtitle implements Subtitle {
    private final List a;

    public Mp4WebvttSubtitle(List list) {
        this.a = Collections.unmodifiableList(list);
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
