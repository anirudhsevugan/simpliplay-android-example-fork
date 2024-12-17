package com.google.android.exoplayer2;

import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.MediaClock;
import com.google.android.exoplayer2.util.StandaloneMediaClock;

final class DefaultMediaClock implements MediaClock {
    private final StandaloneMediaClock a;
    private final PlaybackParametersListener b;
    private Renderer c;
    private MediaClock d;
    private boolean e = true;
    private boolean f;

    public interface PlaybackParametersListener {
        void a(PlaybackParameters playbackParameters);
    }

    public DefaultMediaClock(PlaybackParametersListener playbackParametersListener, Clock clock) {
        this.b = playbackParametersListener;
        this.a = new StandaloneMediaClock(clock);
    }

    public final long a(boolean z) {
        Renderer renderer = this.c;
        if (renderer == null || renderer.z() || (!this.c.y() && (z || this.c.g()))) {
            this.e = true;
            if (this.f) {
                this.a.a();
            }
        } else {
            MediaClock mediaClock = (MediaClock) Assertions.b((Object) this.d);
            long a_ = mediaClock.a_();
            if (this.e) {
                if (a_ < this.a.a_()) {
                    this.a.b();
                } else {
                    this.e = false;
                    if (this.f) {
                        this.a.a();
                    }
                }
            }
            this.a.a(a_);
            PlaybackParameters d2 = mediaClock.d();
            if (!d2.equals(this.a.a)) {
                this.a.a(d2);
                this.b.a(d2);
            }
        }
        return a_();
    }

    public final void a() {
        this.f = true;
        this.a.a();
    }

    public final void a(long j) {
        this.a.a(j);
    }

    public final void a(PlaybackParameters playbackParameters) {
        MediaClock mediaClock = this.d;
        if (mediaClock != null) {
            mediaClock.a(playbackParameters);
            playbackParameters = this.d.d();
        }
        this.a.a(playbackParameters);
    }

    public final void a(Renderer renderer) {
        MediaClock mediaClock;
        MediaClock c2 = renderer.c();
        if (c2 != null && c2 != (mediaClock = this.d)) {
            if (mediaClock == null) {
                this.d = c2;
                this.c = renderer;
                c2.a(this.a.a);
                return;
            }
            throw ExoPlaybackException.a((RuntimeException) new IllegalStateException("Multiple renderer media clocks enabled."));
        }
    }

    public final long a_() {
        return this.e ? this.a.a_() : ((MediaClock) Assertions.b((Object) this.d)).a_();
    }

    public final void b() {
        this.f = false;
        this.a.b();
    }

    public final void b(Renderer renderer) {
        if (renderer == this.c) {
            this.d = null;
            this.c = null;
            this.e = true;
        }
    }

    public final PlaybackParameters d() {
        MediaClock mediaClock = this.d;
        return mediaClock != null ? mediaClock.d() : this.a.a;
    }
}
