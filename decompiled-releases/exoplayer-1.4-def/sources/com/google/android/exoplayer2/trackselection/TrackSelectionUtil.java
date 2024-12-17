package com.google.android.exoplayer2.trackselection;

import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.util.MimeTypes;

public final class TrackSelectionUtil {
    public static DefaultTrackSelector.Parameters a(DefaultTrackSelector.Parameters parameters, int i, TrackGroupArray trackGroupArray, boolean z, DefaultTrackSelector.SelectionOverride selectionOverride) {
        DefaultTrackSelector.ParametersBuilder a = parameters.a().a(i).a(i, z);
        if (selectionOverride != null) {
            a.a(i, trackGroupArray, selectionOverride);
        }
        return a.b();
    }

    public static boolean a(TrackSelectionArray trackSelectionArray) {
        for (int i = 0; i < trackSelectionArray.a; i++) {
            TrackSelection trackSelection = trackSelectionArray.b[i];
            if (trackSelection != null) {
                for (int i2 = 0; i2 < trackSelection.f(); i2++) {
                    if (MimeTypes.h(trackSelection.a(i2).l) == 2) {
                        return true;
                    }
                }
                continue;
            }
        }
        return false;
    }
}
