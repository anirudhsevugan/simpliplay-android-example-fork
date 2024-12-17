package com.google.android.exoplayer2.ui;

import android.content.DialogInterface;

final /* synthetic */ class TrackSelectionDialogBuilder$$Lambda$1 implements DialogInterface.OnClickListener {
    private final TrackSelectionDialogBuilder arg$1;
    private final TrackSelectionView arg$2;

    TrackSelectionDialogBuilder$$Lambda$1(TrackSelectionDialogBuilder trackSelectionDialogBuilder, TrackSelectionView trackSelectionView) {
        this.arg$1 = trackSelectionDialogBuilder;
        this.arg$2 = trackSelectionView;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.lambda$setUpDialogView$1$TrackSelectionDialogBuilder(this.arg$2, dialogInterface, i);
    }
}
