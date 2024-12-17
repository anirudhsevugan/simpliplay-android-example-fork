package com.google.android.exoplayer2;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class ExoPlayerImpl$$Lambda$4 implements ListenerSet.Event {
    static final ListenerSet.Event a = new ExoPlayerImpl$$Lambda$4();

    private ExoPlayerImpl$$Lambda$4() {
    }

    public final void a(Object obj) {
        ((Player.EventListener) obj).onPlayerError(ExoPlaybackException.a((Exception) new ExoTimeoutException(1)));
    }
}
