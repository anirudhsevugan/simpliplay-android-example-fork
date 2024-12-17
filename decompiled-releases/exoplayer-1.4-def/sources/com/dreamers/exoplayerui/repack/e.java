package com.dreamers.exoplayerui.repack;

import com.dreamers.exoplayerui.ExoplayerUi;
import com.google.android.exoplayer2.ui.StyledPlayerControlView;

public final /* synthetic */ class e implements StyledPlayerControlView.OnSettingsWindowDismissListener {
    private final ExoplayerUi a;

    public e(ExoplayerUi exoplayerUi) {
        this.a = exoplayerUi;
    }

    public final void onDismiss(boolean z) {
        ExoplayerUi.b(this.a, z);
    }
}
