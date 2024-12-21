package com.google.android.exoplayer2.trackselection;

import com.google.android.exoplayer2.RendererConfiguration;
import com.google.android.exoplayer2.util.Util;

public final class TrackSelectorResult {
    public final int a;
    public final RendererConfiguration[] b;
    public final ExoTrackSelection[] c;
    public final Object d;

    public TrackSelectorResult(RendererConfiguration[] rendererConfigurationArr, ExoTrackSelection[] exoTrackSelectionArr, Object obj) {
        this.b = rendererConfigurationArr;
        this.c = (ExoTrackSelection[]) exoTrackSelectionArr.clone();
        this.d = obj;
        this.a = rendererConfigurationArr.length;
    }

    public final boolean a(int i) {
        return this.b[i] != null;
    }

    public final boolean a(TrackSelectorResult trackSelectorResult, int i) {
        return trackSelectorResult != null && Util.a((Object) this.b[i], (Object) trackSelectorResult.b[i]) && Util.a((Object) this.c[i], (Object) trackSelectorResult.c[i]);
    }
}
