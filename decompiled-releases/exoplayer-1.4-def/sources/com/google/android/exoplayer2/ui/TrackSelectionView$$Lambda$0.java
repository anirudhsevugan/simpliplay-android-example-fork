package com.google.android.exoplayer2.ui;

import com.google.android.exoplayer2.ui.TrackSelectionView;
import java.util.Comparator;

final /* synthetic */ class TrackSelectionView$$Lambda$0 implements Comparator {
    private final Comparator arg$1;

    TrackSelectionView$$Lambda$0(Comparator comparator) {
        this.arg$1 = comparator;
    }

    public final int compare(Object obj, Object obj2) {
        return this.arg$1.compare(((TrackSelectionView.TrackInfo) obj).format, ((TrackSelectionView.TrackInfo) obj2).format);
    }
}
