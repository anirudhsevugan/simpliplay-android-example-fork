package com.google.android.exoplayer2.source.hls;

import android.text.TextUtils;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.text.webvtt.WebvttParserUtil;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class WebvttExtractor implements Extractor {
    private static final Pattern a = Pattern.compile("LOCAL:([^,]+)");
    private static final Pattern b = Pattern.compile("MPEGTS:(-?\\d+)");
    private final String c;
    private final TimestampAdjuster d;
    private final ParsableByteArray e = new ParsableByteArray();
    private ExtractorOutput f;
    private byte[] g = new byte[1024];
    private int h;

    public WebvttExtractor(String str, TimestampAdjuster timestampAdjuster) {
        this.c = str;
        this.d = timestampAdjuster;
    }

    private TrackOutput a(long j) {
        TrackOutput a2 = this.f.a(0, 3);
        Format.Builder builder = new Format.Builder();
        builder.k = "text/vtt";
        builder.c = this.c;
        builder.o = j;
        a2.a(builder.a());
        this.f.c_();
        return a2;
    }

    public final int a(ExtractorInput extractorInput, PositionHolder positionHolder) {
        Assertions.b((Object) this.f);
        int d2 = (int) extractorInput.d();
        int i = this.h;
        byte[] bArr = this.g;
        if (i == bArr.length) {
            this.g = Arrays.copyOf(bArr, ((d2 != -1 ? d2 : bArr.length) * 3) / 2);
        }
        byte[] bArr2 = this.g;
        int i2 = this.h;
        int a2 = extractorInput.a(bArr2, i2, bArr2.length - i2);
        if (a2 != -1) {
            int i3 = this.h + a2;
            this.h = i3;
            if (d2 == -1 || i3 != d2) {
                return 0;
            }
        }
        ParsableByteArray parsableByteArray = new ParsableByteArray(this.g);
        WebvttParserUtil.a(parsableByteArray);
        long j = 0;
        long j2 = 0;
        for (String s = parsableByteArray.s(); !TextUtils.isEmpty(s); s = parsableByteArray.s()) {
            if (s.startsWith("X-TIMESTAMP-MAP")) {
                Matcher matcher = a.matcher(s);
                if (!matcher.find()) {
                    String valueOf = String.valueOf(s);
                    throw new ParserException(valueOf.length() != 0 ? "X-TIMESTAMP-MAP doesn't contain local timestamp: ".concat(valueOf) : new String("X-TIMESTAMP-MAP doesn't contain local timestamp: "));
                }
                Matcher matcher2 = b.matcher(s);
                if (!matcher2.find()) {
                    String valueOf2 = String.valueOf(s);
                    throw new ParserException(valueOf2.length() != 0 ? "X-TIMESTAMP-MAP doesn't contain media timestamp: ".concat(valueOf2) : new String("X-TIMESTAMP-MAP doesn't contain media timestamp: "));
                }
                j2 = WebvttParserUtil.a((String) Assertions.b((Object) matcher.group(1)));
                j = TimestampAdjuster.d(Long.parseLong((String) Assertions.b((Object) matcher2.group(1))));
            }
        }
        Matcher c2 = WebvttParserUtil.c(parsableByteArray);
        if (c2 == null) {
            a(0);
        } else {
            long a3 = WebvttParserUtil.a((String) Assertions.b((Object) c2.group(1)));
            long b2 = this.d.b(TimestampAdjuster.e((j + a3) - j2));
            TrackOutput a4 = a(b2 - a3);
            this.e.a(this.g, this.h);
            a4.b(this.e, this.h);
            a4.a(b2, 1, this.h, 0, (TrackOutput.CryptoData) null);
        }
        return -1;
    }

    public final void a(long j, long j2) {
        throw new IllegalStateException();
    }

    public final void a(ExtractorOutput extractorOutput) {
        this.f = extractorOutput;
        extractorOutput.a(new SeekMap.Unseekable(-9223372036854775807L));
    }

    public final boolean a(ExtractorInput extractorInput) {
        extractorInput.b(this.g, 0, 6, false);
        this.e.a(this.g, 6);
        if (WebvttParserUtil.b(this.e)) {
            return true;
        }
        extractorInput.b(this.g, 6, 3, false);
        this.e.a(this.g, 9);
        return WebvttParserUtil.b(this.e);
    }
}
