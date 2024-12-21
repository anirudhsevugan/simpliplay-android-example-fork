package com.google.android.exoplayer2.source.hls.playlist;

import android.net.Uri;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.hls.HlsDataSourceFactory;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import java.io.IOException;

public interface HlsPlaylistTracker {

    public interface Factory {
        HlsPlaylistTracker a(HlsDataSourceFactory hlsDataSourceFactory, LoadErrorHandlingPolicy loadErrorHandlingPolicy, HlsPlaylistParserFactory hlsPlaylistParserFactory);
    }

    public interface PlaylistEventListener {
        boolean a(Uri uri, long j);

        void h();
    }

    public final class PlaylistResetException extends IOException {
    }

    public final class PlaylistStuckException extends IOException {
    }

    public interface PrimaryPlaylistListener {
        void a(HlsMediaPlaylist hlsMediaPlaylist);
    }

    HlsMediaPlaylist a(Uri uri, boolean z);

    void a();

    void a(Uri uri, MediaSourceEventListener.EventDispatcher eventDispatcher, PrimaryPlaylistListener primaryPlaylistListener);

    void a(PlaylistEventListener playlistEventListener);

    boolean a(Uri uri);

    HlsMasterPlaylist b();

    void b(Uri uri);

    void b(PlaylistEventListener playlistEventListener);

    long c();

    void c(Uri uri);

    void d();

    boolean e();
}
