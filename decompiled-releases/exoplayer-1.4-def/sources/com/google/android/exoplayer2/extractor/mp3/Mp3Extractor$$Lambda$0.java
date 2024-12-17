package com.google.android.exoplayer2.extractor.mp3;

import android.net.Uri;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory$$CC;
import java.util.Map;

final /* synthetic */ class Mp3Extractor$$Lambda$0 implements ExtractorsFactory {
    static final ExtractorsFactory b = new Mp3Extractor$$Lambda$0();

    static {
        ExtractorsFactory$$CC.b();
    }

    private Mp3Extractor$$Lambda$0() {
    }

    public final Extractor[] a() {
        return Mp3Extractor.a();
    }

    public final Extractor[] a(Uri uri, Map map) {
        return ExtractorsFactory$$CC.a(this);
    }
}
