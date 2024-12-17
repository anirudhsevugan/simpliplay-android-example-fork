package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import com.google.android.exoplayer2.source.LoadEventInfo;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.Closeable;
import java.io.InputStream;

public final class ParsingLoadable implements Loader.Loadable {
    public final DataSpec a;
    public final int b;
    public final StatsDataSource c;
    public volatile Object d;
    private final Parser e;

    public interface Parser {
        Object a(Uri uri, InputStream inputStream);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ParsingLoadable(com.google.android.exoplayer2.upstream.DataSource r2, android.net.Uri r3, int r4, com.google.android.exoplayer2.upstream.ParsingLoadable.Parser r5) {
        /*
            r1 = this;
            com.google.android.exoplayer2.upstream.DataSpec$Builder r0 = new com.google.android.exoplayer2.upstream.DataSpec$Builder
            r0.<init>()
            r0.a = r3
            r3 = 1
            r0.h = r3
            com.google.android.exoplayer2.upstream.DataSpec r3 = r0.a()
            r1.<init>((com.google.android.exoplayer2.upstream.DataSource) r2, (com.google.android.exoplayer2.upstream.DataSpec) r3, (int) r4, (com.google.android.exoplayer2.upstream.ParsingLoadable.Parser) r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.upstream.ParsingLoadable.<init>(com.google.android.exoplayer2.upstream.DataSource, android.net.Uri, int, com.google.android.exoplayer2.upstream.ParsingLoadable$Parser):void");
    }

    private ParsingLoadable(DataSource dataSource, DataSpec dataSpec, int i, Parser parser) {
        this.c = new StatsDataSource(dataSource);
        this.a = dataSpec;
        this.b = i;
        this.e = parser;
        LoadEventInfo.a();
    }

    public final void a() {
    }

    public final void b() {
        this.c.a = 0;
        DataSourceInputStream dataSourceInputStream = new DataSourceInputStream(this.c, this.a);
        try {
            dataSourceInputStream.a();
            this.d = this.e.a((Uri) Assertions.b((Object) this.c.a()), dataSourceInputStream);
        } finally {
            Util.a((Closeable) dataSourceInputStream);
        }
    }
}
