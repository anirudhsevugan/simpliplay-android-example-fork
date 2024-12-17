package com.google.android.exoplayer2.ui;

import android.view.View;
import com.google.android.exoplayer2.ui.StyledPlayerControlView;

final /* synthetic */ class StyledPlayerControlView$PlaybackSpeedAdapter$$Lambda$0 implements View.OnClickListener {
    private final StyledPlayerControlView.PlaybackSpeedAdapter arg$1;
    private final int arg$2;

    StyledPlayerControlView$PlaybackSpeedAdapter$$Lambda$0(StyledPlayerControlView.PlaybackSpeedAdapter playbackSpeedAdapter, int i) {
        this.arg$1 = playbackSpeedAdapter;
        this.arg$2 = i;
    }

    public final void onClick(View view) {
        this.arg$1.lambda$onBindViewHolder$0$StyledPlayerControlView$PlaybackSpeedAdapter(this.arg$2, view);
    }
}
