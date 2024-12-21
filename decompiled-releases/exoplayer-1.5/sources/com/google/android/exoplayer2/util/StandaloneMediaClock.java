package com.google.android.exoplayer2.util;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.PlaybackParameters;

public final class StandaloneMediaClock implements MediaClock {
    public PlaybackParameters a = PlaybackParameters.a;
    private final Clock b;
    private boolean c;
    private long d;
    private long e;

    public StandaloneMediaClock(Clock clock) {
        this.b = clock;
    }

    public final void a() {
        if (!this.c) {
            this.e = this.b.a();
            this.c = true;
        }
    }

    public final void a(long j) {
        this.d = j;
        if (this.c) {
            this.e = this.b.a();
        }
    }

    public final void a(PlaybackParameters playbackParameters) {
        if (this.c) {
            a(a_());
        }
        this.a = playbackParameters;
    }

    public final long a_() {
        long j = this.d;
        if (!this.c) {
            return j;
        }
        long a2 = this.b.a() - this.e;
        return j + (this.a.b == 1.0f ? C.b(a2) : a2 * ((long) this.a.d));
    }

    public final void b() {
        if (this.c) {
            a(a_());
            this.c = false;
        }
    }

    public final PlaybackParameters d() {
        return this.a;
    }
}
