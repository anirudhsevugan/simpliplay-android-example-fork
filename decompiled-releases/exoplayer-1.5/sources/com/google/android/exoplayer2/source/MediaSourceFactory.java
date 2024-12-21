package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.MediaItem;

public interface MediaSourceFactory {
    MediaSource a(MediaItem mediaItem);
}
