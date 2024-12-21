package com.google.android.exoplayer2;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class ExoPlayerImpl$$Lambda$8 implements ListenerSet.Event {
    private final MediaItem a;
    private final int b;

    ExoPlayerImpl$$Lambda$8(MediaItem mediaItem, int i) {
        this.a = mediaItem;
        this.b = i;
    }

    public final void a(Object obj) {
        ((Player.EventListener) obj).onMediaItemTransition(this.a, this.b);
    }
}
