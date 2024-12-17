package com.google.android.exoplayer2.source.dash;

import com.google.android.exoplayer2.source.dash.manifest.RangedUri;
import com.google.android.exoplayer2.source.dash.manifest.Representation;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.UriUtil;

public final class DashUtil {
    public static DataSpec a(Representation representation, RangedUri rangedUri, int i) {
        DataSpec.Builder builder = new DataSpec.Builder();
        builder.a = UriUtil.a(representation.b, rangedUri.c);
        builder.e = rangedUri.a;
        builder.f = rangedUri.b;
        builder.g = null;
        builder.h = i;
        return builder.a();
    }
}
