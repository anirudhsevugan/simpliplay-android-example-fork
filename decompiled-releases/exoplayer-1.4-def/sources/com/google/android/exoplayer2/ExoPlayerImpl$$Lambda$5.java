package com.google.android.exoplayer2;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class ExoPlayerImpl$$Lambda$5 implements ListenerSet.Event {
    private final ExoPlayerImpl a;

    ExoPlayerImpl$$Lambda$5(ExoPlayerImpl exoPlayerImpl) {
        this.a = exoPlayerImpl;
    }

    public final void a(Object obj) {
        ((Player.EventListener) obj).onMediaMetadataChanged(this.a.i);
    }
}
