package com.google.android.exoplayer2;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class ExoPlayerImpl$$Lambda$7 implements ListenerSet.Event {
    private final int a;
    private final Player.PositionInfo b;
    private final Player.PositionInfo c;

    ExoPlayerImpl$$Lambda$7(int i, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2) {
        this.a = i;
        this.b = positionInfo;
        this.c = positionInfo2;
    }

    public final void a(Object obj) {
        ExoPlayerImpl.a(this.a, this.b, this.c, (Player.EventListener) obj);
    }
}
