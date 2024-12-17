package com.google.android.exoplayer2;

import com.google.android.exoplayer2.trackselection.TrackSelector;

public interface ExoPlayer extends Player {

    public interface AudioOffloadListener {
        void c();

        void d();
    }

    TrackSelector getTrackSelector();
}
