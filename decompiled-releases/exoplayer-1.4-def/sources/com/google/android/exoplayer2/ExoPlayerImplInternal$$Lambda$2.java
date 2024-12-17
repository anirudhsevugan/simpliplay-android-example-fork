package com.google.android.exoplayer2;

import com.google.android.exoplayer2.util.Log;

final /* synthetic */ class ExoPlayerImplInternal$$Lambda$2 implements Runnable {
    private final PlayerMessage a;

    ExoPlayerImplInternal$$Lambda$2(PlayerMessage playerMessage) {
        this.a = playerMessage;
    }

    public final void run() {
        try {
            ExoPlayerImplInternal.b(this.a);
        } catch (ExoPlaybackException e) {
            Log.b("ExoPlayerImplInternal", "Unexpected error delivering message on external thread.", e);
            throw new RuntimeException(e);
        }
    }
}
