package com.google.android.exoplayer2.source.hls.playlist;

import android.net.Uri;
import com.google.android.exoplayer2.source.hls.playlist.DefaultHlsPlaylistTracker;

final /* synthetic */ class DefaultHlsPlaylistTracker$MediaPlaylistBundle$$Lambda$0 implements Runnable {
    private final DefaultHlsPlaylistTracker.MediaPlaylistBundle a;
    private final Uri b;

    DefaultHlsPlaylistTracker$MediaPlaylistBundle$$Lambda$0(DefaultHlsPlaylistTracker.MediaPlaylistBundle mediaPlaylistBundle, Uri uri) {
        this.a = mediaPlaylistBundle;
        this.b = uri;
    }

    public final void run() {
        DefaultHlsPlaylistTracker.MediaPlaylistBundle mediaPlaylistBundle = this.a;
        Uri uri = this.b;
        mediaPlaylistBundle.d = false;
        mediaPlaylistBundle.a(uri);
    }
}
