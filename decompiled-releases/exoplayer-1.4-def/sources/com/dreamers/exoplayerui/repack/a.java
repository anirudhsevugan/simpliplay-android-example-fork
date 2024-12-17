package com.dreamers.exoplayerui.repack;

import com.dreamers.exoplayerui.ExoplayerUi;
import com.google.android.exoplayer2.ui.PlayerControlView;

public final /* synthetic */ class a implements PlayerControlView.VisibilityListener {
    private final ExoplayerUi a;

    public a(ExoplayerUi exoplayerUi) {
        this.a = exoplayerUi;
    }

    public final void onVisibilityChange(int i) {
        ExoplayerUi.a(this.a, i);
    }
}
