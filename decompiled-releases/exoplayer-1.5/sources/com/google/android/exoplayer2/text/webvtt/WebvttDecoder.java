package com.google.android.exoplayer2.text.webvtt;

import android.text.TextUtils;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.List;

public final class WebvttDecoder extends SimpleSubtitleDecoder {
    private final ParsableByteArray d = new ParsableByteArray();
    private final CssParser e = new CssParser();

    private static int a(ParsableByteArray parsableByteArray) {
        int i = -1;
        int i2 = 0;
        while (i == -1) {
            i2 = parsableByteArray.b;
            String s = parsableByteArray.s();
            i = s == null ? 0 : "STYLE".equals(s) ? 2 : s.startsWith("NOTE") ? 1 : 3;
        }
        parsableByteArray.d(i2);
        return i;
    }

    private static void b(ParsableByteArray parsableByteArray) {
        do {
        } while (!TextUtils.isEmpty(parsableByteArray.s()));
    }

    public final Subtitle a(byte[] bArr, int i, boolean z) {
        WebvttCueInfo a;
        this.d.a(bArr, i);
        ArrayList arrayList = new ArrayList();
        try {
            WebvttParserUtil.a(this.d);
            do {
            } while (!TextUtils.isEmpty(this.d.s()));
            ArrayList arrayList2 = new ArrayList();
            while (true) {
                int a2 = a(this.d);
                if (a2 == 0) {
                    return new WebvttSubtitle(arrayList2);
                }
                if (a2 == 1) {
                    b(this.d);
                } else if (a2 == 2) {
                    if (arrayList2.isEmpty()) {
                        this.d.s();
                        arrayList.addAll(this.e.a(this.d));
                    } else {
                        throw new SubtitleDecoderException("A style block was found after the first cue.");
                    }
                } else if (a2 == 3 && (a = WebvttCueParser.a(this.d, (List) arrayList)) != null) {
                    arrayList2.add(a);
                }
            }
        } catch (ParserException e2) {
            throw new SubtitleDecoderException((Throwable) e2);
        }
    }
}
