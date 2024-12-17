package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.Collections;
import java.util.List;

public final class DvbSubtitleReader implements ElementaryStreamReader {
    private final List a;
    private final TrackOutput[] b;
    private boolean c;
    private int d;
    private int e;
    private long f;

    public DvbSubtitleReader(List list) {
        this.a = list;
        this.b = new TrackOutput[list.size()];
    }

    private boolean a(ParsableByteArray parsableByteArray, int i) {
        if (parsableByteArray.a() == 0) {
            return false;
        }
        if (parsableByteArray.c() != i) {
            this.c = false;
        }
        this.d--;
        return this.c;
    }

    public final void a() {
        this.c = false;
    }

    public final void a(long j, int i) {
        if ((i & 4) != 0) {
            this.c = true;
            this.f = j;
            this.e = 0;
            this.d = 2;
        }
    }

    public final void a(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        for (int i = 0; i < this.b.length; i++) {
            TsPayloadReader.DvbSubtitleInfo dvbSubtitleInfo = (TsPayloadReader.DvbSubtitleInfo) this.a.get(i);
            trackIdGenerator.a();
            TrackOutput a2 = extractorOutput.a(trackIdGenerator.b(), 3);
            Format.Builder builder = new Format.Builder();
            builder.a = trackIdGenerator.c();
            builder.k = "application/dvbsubs";
            builder.m = Collections.singletonList(dvbSubtitleInfo.b);
            builder.c = dvbSubtitleInfo.a;
            a2.a(builder.a());
            this.b[i] = a2;
        }
    }

    public final void a(ParsableByteArray parsableByteArray) {
        if (!this.c) {
            return;
        }
        if (this.d != 2 || a(parsableByteArray, 32)) {
            if (this.d != 1 || a(parsableByteArray, 0)) {
                int i = parsableByteArray.b;
                int a2 = parsableByteArray.a();
                for (TrackOutput b2 : this.b) {
                    parsableByteArray.d(i);
                    b2.b(parsableByteArray, a2);
                }
                this.e += a2;
            }
        }
    }

    public final void b() {
        if (this.c) {
            for (TrackOutput a2 : this.b) {
                a2.a(this.f, 1, this.e, 0, (TrackOutput.CryptoData) null);
            }
            this.c = false;
        }
    }
}
