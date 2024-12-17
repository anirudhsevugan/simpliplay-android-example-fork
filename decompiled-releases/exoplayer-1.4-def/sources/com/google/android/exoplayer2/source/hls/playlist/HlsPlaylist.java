package com.google.android.exoplayer2.source.hls.playlist;

import com.google.android.exoplayer2.offline.FilterableManifest;
import java.util.Collections;
import java.util.List;

public abstract class HlsPlaylist implements FilterableManifest {
    public final String s;
    public final List t;
    public final boolean u;

    protected HlsPlaylist(String str, List list, boolean z) {
        this.s = str;
        this.t = Collections.unmodifiableList(list);
        this.u = z;
    }
}
