package com.google.android.exoplayer2;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class ExoPlayerImpl$$Lambda$3 implements ListenerSet.Event {
    private final boolean a;

    ExoPlayerImpl$$Lambda$3(boolean z) {
        this.a = z;
    }

    public final void a(Object obj) {
        ((Player.EventListener) obj).onShuffleModeEnabledChanged(this.a);
    }
}
