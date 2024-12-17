package com.google.android.exoplayer2.source.hls;

import com.google.android.exoplayer2.upstream.DataSource;

public final class DefaultHlsDataSourceFactory implements HlsDataSourceFactory {
    private final DataSource.Factory a;

    public DefaultHlsDataSourceFactory(DataSource.Factory factory) {
        this.a = factory;
    }

    public final DataSource a() {
        return this.a.a();
    }
}
