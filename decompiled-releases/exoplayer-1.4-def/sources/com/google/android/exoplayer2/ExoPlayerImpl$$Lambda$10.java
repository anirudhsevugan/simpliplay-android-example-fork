package com.google.android.exoplayer2;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class ExoPlayerImpl$$Lambda$10 implements ListenerSet.Event {
    private final PlaybackInfo a;
    private final TrackSelectionArray b;

    ExoPlayerImpl$$Lambda$10(PlaybackInfo playbackInfo, TrackSelectionArray trackSelectionArray) {
        this.a = playbackInfo;
        this.b = trackSelectionArray;
    }

    public final void a(Object obj) {
        ((Player.EventListener) obj).onTracksChanged(this.a.h, this.b);
    }
}
