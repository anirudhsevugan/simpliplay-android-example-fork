package com.google.android.exoplayer2;

import com.google.android.exoplayer2.ExoPlayerImplInternal;

final /* synthetic */ class ExoPlayerImpl$$Lambda$1 implements ExoPlayerImplInternal.PlaybackInfoUpdateListener {
    private final ExoPlayerImpl a;

    ExoPlayerImpl$$Lambda$1(ExoPlayerImpl exoPlayerImpl) {
        this.a = exoPlayerImpl;
    }

    public final void a(ExoPlayerImplInternal.PlaybackInfoUpdate playbackInfoUpdate) {
        ExoPlayerImpl exoPlayerImpl = this.a;
        exoPlayerImpl.b.a((Runnable) new ExoPlayerImpl$$Lambda$22(exoPlayerImpl, playbackInfoUpdate));
    }
}
