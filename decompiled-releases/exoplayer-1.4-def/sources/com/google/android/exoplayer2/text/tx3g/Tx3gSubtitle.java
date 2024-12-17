package com.google.android.exoplayer2.text.tx3g;

import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.util.Assertions;
import java.util.Collections;
import java.util.List;

final class Tx3gSubtitle implements Subtitle {
    public static final Tx3gSubtitle a = new Tx3gSubtitle();
    private final List b;

    private Tx3gSubtitle() {
        this.b = Collections.emptyList();
    }

    public Tx3gSubtitle(Cue cue) {
        this.b = Collections.singletonList(cue);
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
        return j >= 0 ? this.b : Collections.emptyList();
    }
}
