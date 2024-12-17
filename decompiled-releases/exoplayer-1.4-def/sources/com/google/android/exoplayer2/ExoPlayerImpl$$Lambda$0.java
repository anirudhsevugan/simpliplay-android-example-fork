package com.google.android.exoplayer2;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.util.ExoFlags;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class ExoPlayerImpl$$Lambda$0 implements ListenerSet.IterationFinishedEvent {
    private final Player a;

    ExoPlayerImpl$$Lambda$0(Player player) {
        this.a = player;
    }

    public final void a(Object obj, ExoFlags exoFlags) {
        ((Player.EventListener) obj).onEvents(this.a, new Player.Events(exoFlags));
    }
}
