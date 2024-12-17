package com.google.android.exoplayer2;

import com.google.android.exoplayer2.MediaItem;

public interface LivePlaybackSpeedControl {
    float a(long j, long j2);

    void a();

    void a(long j);

    void a(MediaItem.LiveConfiguration liveConfiguration);

    long b();
}
