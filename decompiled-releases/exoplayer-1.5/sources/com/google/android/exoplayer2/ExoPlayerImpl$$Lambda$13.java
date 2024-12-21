package com.google.android.exoplayer2;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class ExoPlayerImpl$$Lambda$13 implements ListenerSet.Event {
    private final PlaybackInfo a;

    ExoPlayerImpl$$Lambda$13(PlaybackInfo playbackInfo) {
        this.a = playbackInfo;
    }

    public final void a(Object obj) {
        ExoPlayerImpl.f(this.a, (Player.EventListener) obj);
    }
}
