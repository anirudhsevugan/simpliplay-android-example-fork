package com.google.android.exoplayer2.text.webvtt;

import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Collections;

public final class Mp4WebvttDecoder extends SimpleSubtitleDecoder {
    private final ParsableByteArray d = new ParsableByteArray();

    private static Cue a(ParsableByteArray parsableByteArray, int i) {
        CharSequence charSequence = null;
        Cue.Builder builder = null;
        while (i > 0) {
            if (i >= 8) {
                int j = parsableByteArray.j();
                int j2 = parsableByteArray.j();
                int i2 = j - 8;
                String a = Util.a(parsableByteArray.a, parsableByteArray.b, i2);
                parsableByteArray.e(i2);
                i = (i - 8) - i2;
                if (j2 == 1937011815) {
                    builder = WebvttCueParser.a(a);
                } else if (j2 == 1885436268) {
                    charSequence = WebvttCueParser.a((String) null, a.trim(), Collections.emptyList());
                }
            } else {
                throw new SubtitleDecoderException("Incomplete vtt cue box header found.");
            }
        }
        if (charSequence == null) {
            charSequence = "";
        }
        if (builder == null) {
            return WebvttCueParser.a(charSequence);
        }
        builder.a = charSequence;
        return builder.a();
    }

    public final Subtitle a(byte[] bArr, int i, boolean z) {
        this.d.a(bArr, i);
        ArrayList arrayList = new ArrayList();
        while (this.d.a() > 0) {
            if (this.d.a() >= 8) {
                int j = this.d.j();
                if (this.d.j() == 1987343459) {
                    arrayList.add(a(this.d, j - 8));
                } else {
                    this.d.e(j - 8);
                }
            } else {
                throw new SubtitleDecoderException("Incomplete Mp4Webvtt Top Level box header found.");
            }
        }
        return new Mp4WebvttSubtitle(arrayList);
    }
}
