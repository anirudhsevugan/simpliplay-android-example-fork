package com.google.android.exoplayer2.offline;

import android.net.Uri;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import java.io.InputStream;
import java.util.List;

public final class FilteringManifestParser implements ParsingLoadable.Parser {
    private final ParsingLoadable.Parser a;
    private final List b;

    public FilteringManifestParser(ParsingLoadable.Parser parser, List list) {
        this.a = parser;
        this.b = list;
    }

    public final /* synthetic */ Object a(Uri uri, InputStream inputStream) {
        FilterableManifest filterableManifest = (FilterableManifest) this.a.a(uri, inputStream);
        List list = this.b;
        return (list == null || list.isEmpty()) ? filterableManifest : (FilterableManifest) filterableManifest.a(this.b);
    }
}
