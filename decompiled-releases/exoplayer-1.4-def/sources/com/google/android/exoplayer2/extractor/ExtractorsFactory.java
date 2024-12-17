package com.google.android.exoplayer2.extractor;

import android.net.Uri;
import java.util.Map;

public interface ExtractorsFactory {
    public static final ExtractorsFactory a = ExtractorsFactory$$Lambda$0.b;

    Extractor[] a();

    Extractor[] a(Uri uri, Map map);
}
