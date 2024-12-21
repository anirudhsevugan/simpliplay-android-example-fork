package com.google.android.exoplayer2.extractor.mp4;

import android.net.Uri;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory$$CC;
import java.util.Map;

final /* synthetic */ class FragmentedMp4Extractor$$Lambda$1 implements ExtractorsFactory {
    static final ExtractorsFactory b = new FragmentedMp4Extractor$$Lambda$1();

    static {
        ExtractorsFactory$$CC.b();
    }

    private FragmentedMp4Extractor$$Lambda$1() {
    }

    public final Extractor[] a() {
        return FragmentedMp4Extractor.a();
    }

    public final Extractor[] a(Uri uri, Map map) {
        return ExtractorsFactory$$CC.a(this);
    }
}
