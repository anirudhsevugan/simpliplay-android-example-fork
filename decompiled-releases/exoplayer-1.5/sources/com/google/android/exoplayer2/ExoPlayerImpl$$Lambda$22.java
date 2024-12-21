package com.google.android.exoplayer2;

import com.google.android.exoplayer2.ExoPlayerImplInternal;

final /* synthetic */ class ExoPlayerImpl$$Lambda$22 implements Runnable {
    private final ExoPlayerImpl a;
    private final ExoPlayerImplInternal.PlaybackInfoUpdate b;

    ExoPlayerImpl$$Lambda$22(ExoPlayerImpl exoPlayerImpl, ExoPlayerImplInternal.PlaybackInfoUpdate playbackInfoUpdate) {
        this.a = exoPlayerImpl;
        this.b = playbackInfoUpdate;
    }

    public final void run() {
        this.a.a(this.b);
    }
}
