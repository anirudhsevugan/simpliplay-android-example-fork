package com.dreamers.exoplayerui.repack;

import com.dreamers.exoplayerui.ExoplayerUi;
import com.google.android.exoplayer2.ui.StyledPlayerControlView;

public final /* synthetic */ class c implements StyledPlayerControlView.OnFullScreenModeChangedListener {
    private final ExoplayerUi a;

    public c(ExoplayerUi exoplayerUi) {
        this.a = exoplayerUi;
    }

    public final void onFullScreenModeChanged(boolean z) {
        ExoplayerUi.a(this.a, z);
    }
}
