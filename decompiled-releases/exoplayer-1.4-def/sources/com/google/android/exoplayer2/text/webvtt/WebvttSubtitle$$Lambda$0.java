package com.google.android.exoplayer2.text.webvtt;

import java.util.Comparator;

final /* synthetic */ class WebvttSubtitle$$Lambda$0 implements Comparator {
    static final Comparator a = new WebvttSubtitle$$Lambda$0();

    private WebvttSubtitle$$Lambda$0() {
    }

    public final int compare(Object obj, Object obj2) {
        return WebvttSubtitle.a((WebvttCueInfo) obj, (WebvttCueInfo) obj2);
    }
}
