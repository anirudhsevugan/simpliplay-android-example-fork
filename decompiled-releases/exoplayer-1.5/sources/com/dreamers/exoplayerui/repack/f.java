package com.dreamers.exoplayerui.repack;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ui.TrackNameProvider;

public final /* synthetic */ class f implements TrackNameProvider {
    public static final TrackNameProvider a = new f();

    private f() {
    }

    public final String getTrackName(Format format) {
        return n.b(format, "format");
    }
}
