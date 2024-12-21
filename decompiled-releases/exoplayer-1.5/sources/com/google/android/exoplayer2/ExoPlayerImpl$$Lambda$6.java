package com.google.android.exoplayer2;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class ExoPlayerImpl$$Lambda$6 implements ListenerSet.Event {
    private final PlaybackInfo a;
    private final int b;

    ExoPlayerImpl$$Lambda$6(PlaybackInfo playbackInfo, int i) {
        this.a = playbackInfo;
        this.b = i;
    }

    public final void a(Object obj) {
        ExoPlayerImpl.b(this.a, this.b, (Player.EventListener) obj);
    }
}
