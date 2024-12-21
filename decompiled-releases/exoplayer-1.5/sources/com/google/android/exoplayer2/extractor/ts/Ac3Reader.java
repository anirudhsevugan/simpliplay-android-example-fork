package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.audio.Ac3Util;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.appinventor.components.runtime.util.Ev3Constants;

public final class Ac3Reader implements ElementaryStreamReader {
    long a;
    private final ParsableBitArray b;
    private final ParsableByteArray c;
    private final String d;
    private String e;
    private TrackOutput f;
    private int g;
    private int h;
    private boolean i;
    private long j;
    private Format k;
    private int l;

    public Ac3Reader() {
        this((String) null);
    }

    public Ac3Reader(String str) {
        ParsableBitArray parsableBitArray = new ParsableBitArray(new byte[128]);
        this.b = parsableBitArray;
        this.c = new ParsableByteArray(parsableBitArray.a);
        this.g = 0;
        this.d = str;
    }

    private boolean a(ParsableByteArray parsableByteArray, byte[] bArr) {
        int min = Math.min(parsableByteArray.a(), 128 - this.h);
        parsableByteArray.a(bArr, this.h, min);
        int i2 = this.h + min;
        this.h = i2;
        return i2 == 128;
    }

    private boolean b(ParsableByteArray parsableByteArray) {
        while (true) {
            boolean z = false;
            if (parsableByteArray.a() <= 0) {
                return false;
            }
            if (this.i) {
                int c2 = parsableByteArray.c();
                if (c2 == 119) {
                    this.i = false;
                    return true;
                } else if (c2 != 11) {
                    this.i = z;
                }
            } else if (parsableByteArray.c() != 11) {
                this.i = z;
            }
            z = true;
            this.i = z;
        }
    }

    private void c() {
        this.b.a(0);
        Ac3Util.SyncFrameInfo a2 = Ac3Util.a(this.b);
        if (this.k == null || a2.c != this.k.w || a2.b != this.k.x || !Util.a((Object) a2.a, (Object) this.k.l)) {
            Format.Builder builder = new Format.Builder();
            builder.a = this.e;
            builder.k = a2.a;
            builder.x = a2.c;
            builder.y = a2.b;
            builder.c = this.d;
            Format a3 = builder.a();
            this.k = a3;
            this.f.a(a3);
        }
        this.l = a2.d;
        this.j = (((long) a2.e) * 1000000) / ((long) this.k.x);
    }

    public final void a() {
        this.g = 0;
        this.h = 0;
        this.i = false;
    }

    public final void a(long j2, int i2) {
        this.a = j2;
    }

    public final void a(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.a();
        this.e = trackIdGenerator.c();
        this.f = extractorOutput.a(trackIdGenerator.b(), 1);
    }

    public final void a(ParsableByteArray parsableByteArray) {
        Assertions.a((Object) this.f);
        while (parsableByteArray.a() > 0) {
            switch (this.g) {
                case 0:
                    if (!b(parsableByteArray)) {
                        break;
                    } else {
                        this.g = 1;
                        this.c.a[0] = 11;
                        this.c.a[1] = Ev3Constants.Opcode.JR_LTEQF;
                        this.h = 2;
                        break;
                    }
                case 1:
                    if (!a(parsableByteArray, this.c.a)) {
                        break;
                    } else {
                        c();
                        this.c.d(0);
                        this.f.b(this.c, 128);
                        this.g = 2;
                        break;
                    }
                case 2:
                    int min = Math.min(parsableByteArray.a(), this.l - this.h);
                    this.f.b(parsableByteArray, min);
                    int i2 = this.h + min;
                    this.h = i2;
                    int i3 = this.l;
                    if (i2 != i3) {
                        break;
                    } else {
                        this.f.a(this.a, 1, i3, 0, (TrackOutput.CryptoData) null);
                        this.a += this.j;
                        this.g = 0;
                        break;
                    }
            }
        }
    }

    public final void b() {
    }
}
