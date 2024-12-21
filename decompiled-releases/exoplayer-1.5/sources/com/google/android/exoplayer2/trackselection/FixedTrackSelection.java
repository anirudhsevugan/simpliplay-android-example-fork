package com.google.android.exoplayer2.trackselection;

import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.chunk.MediaChunkIterator;
import java.util.List;

public final class FixedTrackSelection extends BaseTrackSelection {
    public FixedTrackSelection(TrackGroup trackGroup, int i) {
        this(trackGroup, i, (byte) 0);
    }

    private FixedTrackSelection(TrackGroup trackGroup, int i, byte b) {
        super(trackGroup, new int[]{i}, (byte) 0);
    }

    public final int a() {
        return 0;
    }

    public final void a(long j, long j2, List list, MediaChunkIterator[] mediaChunkIteratorArr) {
    }

    public final int b() {
        return 0;
    }
}
