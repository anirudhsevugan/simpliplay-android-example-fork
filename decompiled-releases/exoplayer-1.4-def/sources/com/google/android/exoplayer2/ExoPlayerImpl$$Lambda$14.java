package com.google.android.exoplayer2;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class ExoPlayerImpl$$Lambda$14 implements ListenerSet.Event {
    private final PlaybackInfo a;

    ExoPlayerImpl$$Lambda$14(PlaybackInfo playbackInfo) {
        this.a = playbackInfo;
    }

    public final void a(Object obj) {
        ((Player.EventListener) obj).onPlayerStateChanged(this.a.l, this.a.e);
    }
}
