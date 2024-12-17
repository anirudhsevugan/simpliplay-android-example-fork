package com.google.android.exoplayer2.ui;

import android.view.View;
import com.google.android.exoplayer2.ui.StyledPlayerControlView;

final /* synthetic */ class StyledPlayerControlView$AudioTrackSelectionAdapter$$Lambda$0 implements View.OnClickListener {
    private final StyledPlayerControlView.AudioTrackSelectionAdapter arg$1;

    StyledPlayerControlView$AudioTrackSelectionAdapter$$Lambda$0(StyledPlayerControlView.AudioTrackSelectionAdapter audioTrackSelectionAdapter) {
        this.arg$1 = audioTrackSelectionAdapter;
    }

    public final void onClick(View view) {
        this.arg$1.lambda$onBindViewHolderAtZeroPosition$0$StyledPlayerControlView$AudioTrackSelectionAdapter(view);
    }
}
