package com.google.android.exoplayer2.text.webvtt;

import com.google.android.exoplayer2.text.cea.Cea708Decoder$Cea708CueInfo$$ExternalSyntheticBackport0;
import com.google.android.exoplayer2.text.webvtt.WebvttCueParser;
import java.util.Comparator;

final /* synthetic */ class WebvttCueParser$Element$$Lambda$0 implements Comparator {
    static final Comparator a = new WebvttCueParser$Element$$Lambda$0();

    private WebvttCueParser$Element$$Lambda$0() {
    }

    public final int compare(Object obj, Object obj2) {
        return Cea708Decoder$Cea708CueInfo$$ExternalSyntheticBackport0.m(((WebvttCueParser.Element) obj).b.b, ((WebvttCueParser.Element) obj2).b.b);
    }
}
