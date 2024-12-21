package com.dreamers.exoplayerui.repack;

import com.dreamers.exoplayerui.ExoplayerUi;
import com.google.android.exoplayer2.ui.StyledPlayerControlView;

public final /* synthetic */ class b implements StyledPlayerControlView.VisibilityListener {
    private final ExoplayerUi a;

    public b(ExoplayerUi exoplayerUi) {
        this.a = exoplayerUi;
    }

    public final void onVisibilityChange(int i) {
        ExoplayerUi.b(this.a, i);
    }
}
