package com.google.android.exoplayer2.source.hls.playlist;

import com.google.android.exoplayer2.offline.FilteringManifestParser;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import java.util.List;

public final class FilteringHlsPlaylistParserFactory implements HlsPlaylistParserFactory {
    private final HlsPlaylistParserFactory a;
    private final List b;

    public FilteringHlsPlaylistParserFactory(HlsPlaylistParserFactory hlsPlaylistParserFactory, List list) {
        this.a = hlsPlaylistParserFactory;
        this.b = list;
    }

    public final ParsingLoadable.Parser a() {
        return new FilteringManifestParser(this.a.a(), this.b);
    }

    public final ParsingLoadable.Parser a(HlsMasterPlaylist hlsMasterPlaylist, HlsMediaPlaylist hlsMediaPlaylist) {
        return new FilteringManifestParser(this.a.a(hlsMasterPlaylist, hlsMediaPlaylist), this.b);
    }
}
