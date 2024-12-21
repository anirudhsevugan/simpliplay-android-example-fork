package com.google.android.exoplayer2.extractor.ts;

import android.net.Uri;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory$$CC;
import java.util.Map;

final /* synthetic */ class Ac4Extractor$$Lambda$0 implements ExtractorsFactory {
    static final ExtractorsFactory b = new Ac4Extractor$$Lambda$0();

    static {
        ExtractorsFactory$$CC.b();
    }

    private Ac4Extractor$$Lambda$0() {
    }

    public final Extractor[] a() {
        return Ac4Extractor.a();
    }

    public final Extractor[] a(Uri uri, Map map) {
        return ExtractorsFactory$$CC.a(this);
    }
}
