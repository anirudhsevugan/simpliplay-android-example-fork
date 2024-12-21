package com.google.android.exoplayer2.extractor.ts;

import android.net.Uri;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory$$CC;
import java.util.Map;

final /* synthetic */ class AdtsExtractor$$Lambda$0 implements ExtractorsFactory {
    static final ExtractorsFactory b = new AdtsExtractor$$Lambda$0();

    static {
        ExtractorsFactory$$CC.b();
    }

    private AdtsExtractor$$Lambda$0() {
    }

    public final Extractor[] a() {
        return AdtsExtractor.a();
    }

    public final Extractor[] a(Uri uri, Map map) {
        return ExtractorsFactory$$CC.a(this);
    }
}
