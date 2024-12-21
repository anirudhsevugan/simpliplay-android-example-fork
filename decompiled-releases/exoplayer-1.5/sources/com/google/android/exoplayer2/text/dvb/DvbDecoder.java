package com.google.android.exoplayer2.text.dvb;

import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.dvb.DvbParser;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.List;

public final class DvbDecoder extends SimpleSubtitleDecoder {
    private final DvbParser d;

    public DvbDecoder(List list) {
        ParsableByteArray parsableByteArray = new ParsableByteArray((byte[]) list.get(0));
        this.d = new DvbParser(parsableByteArray.d(), parsableByteArray.d());
    }

    public final Subtitle a(byte[] bArr, int i, boolean z) {
        if (z) {
            DvbParser.SubtitleService subtitleService = this.d.a;
            subtitleService.c.clear();
            subtitleService.d.clear();
            subtitleService.e.clear();
            subtitleService.f.clear();
            subtitleService.g.clear();
            subtitleService.h = null;
            subtitleService.i = null;
        }
        return new DvbSubtitle(this.d.a(bArr, i));
    }
}
