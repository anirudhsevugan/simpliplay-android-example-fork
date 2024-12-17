package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.audio.Ac4Util;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.appinventor.components.runtime.util.Ev3Constants;

public final class Ac4Reader implements ElementaryStreamReader {
    long a;
    private final ParsableBitArray b;
    private final ParsableByteArray c;
    private final String d;
    private String e;
    private TrackOutput f;
    private int g;
    private int h;
    private boolean i;
    private boolean j;
    private long k;
    private Format l;
    private int m;

    public Ac4Reader() {
        this((String) null);
    }

    public Ac4Reader(String str) {
        ParsableBitArray parsableBitArray = new ParsableBitArray(new byte[16]);
        this.b = parsableBitArray;
        this.c = new ParsableByteArray(parsableBitArray.a);
        this.g = 0;
        this.h = 0;
        this.i = false;
        this.j = false;
        this.d = str;
    }

    private boolean a(ParsableByteArray parsableByteArray, byte[] bArr) {
        int min = Math.min(parsableByteArray.a(), 16 - this.h);
        parsableByteArray.a(bArr, this.h, min);
        int i2 = this.h + min;
        this.h = i2;
        return i2 == 16;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x002d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean b(com.google.android.exoplayer2.util.ParsableByteArray r6) {
        /*
            r5 = this;
        L_0x0000:
            int r0 = r6.a()
            r1 = 0
            if (r0 <= 0) goto L_0x0031
            boolean r0 = r5.i
            r2 = 172(0xac, float:2.41E-43)
            r3 = 1
            if (r0 != 0) goto L_0x0018
            int r0 = r6.c()
            if (r0 != r2) goto L_0x0015
            r1 = 1
        L_0x0015:
            r5.i = r1
            goto L_0x0000
        L_0x0018:
            int r0 = r6.c()
            if (r0 != r2) goto L_0x0020
            r2 = 1
            goto L_0x0021
        L_0x0020:
            r2 = 0
        L_0x0021:
            r5.i = r2
            r2 = 64
            r4 = 65
            if (r0 == r2) goto L_0x002b
            if (r0 != r4) goto L_0x0000
        L_0x002b:
            if (r0 != r4) goto L_0x002e
            r1 = 1
        L_0x002e:
            r5.j = r1
            return r3
        L_0x0031:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.ts.Ac4Reader.b(com.google.android.exoplayer2.util.ParsableByteArray):boolean");
    }

    private void c() {
        this.b.a(0);
        Ac4Util.SyncFrameInfo a2 = Ac4Util.a(this.b);
        Format format = this.l;
        if (format == null || 2 != format.w || a2.a != this.l.x || !"audio/ac4".equals(this.l.l)) {
            Format.Builder builder = new Format.Builder();
            builder.a = this.e;
            builder.k = "audio/ac4";
            builder.x = 2;
            builder.y = a2.a;
            builder.c = this.d;
            Format a3 = builder.a();
            this.l = a3;
            this.f.a(a3);
        }
        this.m = a2.b;
        this.k = (((long) a2.c) * 1000000) / ((long) this.l.x);
    }

    public final void a() {
        this.g = 0;
        this.h = 0;
        this.i = false;
        this.j = false;
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
                        this.c.a[0] = Ev3Constants.Opcode.OUTPUT_STEP_POWER;
                        this.c.a[1] = (byte) (this.j ? 65 : 64);
                        this.h = 2;
                        break;
                    }
                case 1:
                    if (!a(parsableByteArray, this.c.a)) {
                        break;
                    } else {
                        c();
                        this.c.d(0);
                        this.f.b(this.c, 16);
                        this.g = 2;
                        break;
                    }
                case 2:
                    int min = Math.min(parsableByteArray.a(), this.m - this.h);
                    this.f.b(parsableByteArray, min);
                    int i2 = this.h + min;
                    this.h = i2;
                    int i3 = this.m;
                    if (i2 != i3) {
                        break;
                    } else {
                        this.f.a(this.a, 1, i3, 0, (TrackOutput.CryptoData) null);
                        this.a += this.k;
                        this.g = 0;
                        break;
                    }
            }
        }
    }

    public final void b() {
    }
}
