package com.google.android.exoplayer2.extractor.flac;

import android.net.Uri;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory$$CC;
import java.util.Map;

final /* synthetic */ class FlacExtractor$$Lambda$0 implements ExtractorsFactory {
    static final ExtractorsFactory b = new FlacExtractor$$Lambda$0();

    static {
        ExtractorsFactory$$CC.b();
    }

    private FlacExtractor$$Lambda$0() {
    }

    public final Extractor[] a() {
        return FlacExtractor.a();
    }

    public final Extractor[] a(Uri uri, Map map) {
        return ExtractorsFactory$$CC.a(this);
    }
}
