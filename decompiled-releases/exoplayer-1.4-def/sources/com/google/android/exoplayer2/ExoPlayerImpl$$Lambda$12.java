package com.google.android.exoplayer2;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class ExoPlayerImpl$$Lambda$12 implements ListenerSet.Event {
    private final MediaMetadata a;

    ExoPlayerImpl$$Lambda$12(MediaMetadata mediaMetadata) {
        this.a = mediaMetadata;
    }

    public final void a(Object obj) {
        ((Player.EventListener) obj).onMediaMetadataChanged(this.a);
    }
}
