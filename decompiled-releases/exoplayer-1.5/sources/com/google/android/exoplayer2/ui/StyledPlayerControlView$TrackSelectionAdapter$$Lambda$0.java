package com.google.android.exoplayer2.ui;

import android.view.View;
import com.google.android.exoplayer2.ui.StyledPlayerControlView;

final /* synthetic */ class StyledPlayerControlView$TrackSelectionAdapter$$Lambda$0 implements View.OnClickListener {
    private final StyledPlayerControlView.TrackSelectionAdapter arg$1;
    private final StyledPlayerControlView.TrackInfo arg$2;
    private final String arg$3;

    StyledPlayerControlView$TrackSelectionAdapter$$Lambda$0(StyledPlayerControlView.TrackSelectionAdapter trackSelectionAdapter, StyledPlayerControlView.TrackInfo trackInfo, String str) {
        this.arg$1 = trackSelectionAdapter;
        this.arg$2 = trackInfo;
        this.arg$3 = str;
    }

    public final void onClick(View view) {
        this.arg$1.lambda$onBindViewHolder$0$StyledPlayerControlView$TrackSelectionAdapter(this.arg$2, this.arg$3, view);
    }
}
