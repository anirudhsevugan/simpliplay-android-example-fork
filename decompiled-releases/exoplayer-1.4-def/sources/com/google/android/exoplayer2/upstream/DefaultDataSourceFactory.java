package com.google.android.exoplayer2.upstream;

import android.content.Context;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;

public final class DefaultDataSourceFactory implements DataSource.Factory {
    private final Context a;
    private final DataSource.Factory b;

    public DefaultDataSourceFactory(Context context) {
        this(context, (byte) 0);
    }

    private DefaultDataSourceFactory(Context context, byte b2) {
        this(context, (DataSource.Factory) new DefaultHttpDataSource.Factory());
    }

    private DefaultDataSourceFactory(Context context, DataSource.Factory factory) {
        this.a = context.getApplicationContext();
        this.b = factory;
    }

    public final /* synthetic */ DataSource a() {
        return new DefaultDataSource(this.a, this.b.a());
    }
}
