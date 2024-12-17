package com.google.android.exoplayer2.source.dash;

import com.google.android.exoplayer2.source.chunk.ChunkSource;
import com.google.android.exoplayer2.source.dash.PlayerEmsgHandler;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.trackselection.ExoTrackSelection;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower;
import com.google.android.exoplayer2.upstream.TransferListener;
import java.util.List;

public interface DashChunkSource extends ChunkSource {

    public interface Factory {
        DashChunkSource a(LoaderErrorThrower loaderErrorThrower, DashManifest dashManifest, int i, int[] iArr, ExoTrackSelection exoTrackSelection, int i2, long j, boolean z, List list, PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler, TransferListener transferListener);
    }

    void a(DashManifest dashManifest, int i);

    void a(ExoTrackSelection exoTrackSelection);
}
