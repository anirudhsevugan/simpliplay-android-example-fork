package com.google.android.exoplayer2;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class ExoPlayerImpl$$Lambda$2 implements ListenerSet.Event {
    private final int a;

    ExoPlayerImpl$$Lambda$2(int i) {
        this.a = i;
    }

    public final void a(Object obj) {
        ((Player.EventListener) obj).onRepeatModeChanged(this.a);
    }
}
