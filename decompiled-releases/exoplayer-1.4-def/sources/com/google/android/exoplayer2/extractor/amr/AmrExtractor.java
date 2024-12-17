package com.google.android.exoplayer2.extractor.amr;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public final class AmrExtractor implements Extractor {
    private static final int[] a = {13, 14, 16, 18, 20, 21, 27, 32, 6, 7, 6, 6, 1, 1, 1, 1};
    private static final int[] b;
    private static final byte[] c = Util.c("#!AMR\n");
    private static final byte[] d = Util.c("#!AMR-WB\n");
    private static final int e;
    private final byte[] f;
    private boolean g;
    private long h;
    private int i;
    private int j;
    private boolean k;
    private int l;
    private int m;
    private long n;
    private ExtractorOutput o;
    private TrackOutput p;
    private SeekMap q;
    private boolean r;

    static {
        ExtractorsFactory extractorsFactory = AmrExtractor$$Lambda$0.b;
        int[] iArr = {18, 24, 33, 37, 41, 47, 51, 59, 61, 6, 1, 1, 1, 1, 1, 1};
        b = iArr;
        e = iArr[8];
    }

    public AmrExtractor() {
        this((byte) 0);
    }

    public AmrExtractor(byte b2) {
        this.f = new byte[1];
        this.l = -1;
    }

    private static boolean a(ExtractorInput extractorInput, byte[] bArr) {
        extractorInput.a();
        byte[] bArr2 = new byte[bArr.length];
        extractorInput.d(bArr2, 0, bArr.length);
        return Arrays.equals(bArr2, bArr);
    }

    static final /* synthetic */ Extractor[] a() {
        return new Extractor[]{new AmrExtractor()};
    }

    private boolean b(ExtractorInput extractorInput) {
        int length;
        byte[] bArr = c;
        if (a(extractorInput, bArr)) {
            this.g = false;
            length = bArr.length;
        } else {
            byte[] bArr2 = d;
            if (!a(extractorInput, bArr2)) {
                return false;
            }
            this.g = true;
            length = bArr2.length;
        }
        extractorInput.b(length);
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003e, code lost:
        if ((!r4 && (r0 < 12 || r0 > 14)) != false) goto L_0x0040;
     */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0045 A[Catch:{ EOFException -> 0x00be }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x007b A[Catch:{ EOFException -> 0x00be }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int c(com.google.android.exoplayer2.extractor.ExtractorInput r12) {
        /*
            r11 = this;
            int r0 = r11.j
            r1 = -1
            r2 = 1
            r3 = 0
            if (r0 != 0) goto L_0x00c0
            r12.a()     // Catch:{ EOFException -> 0x00be }
            byte[] r0 = r11.f     // Catch:{ EOFException -> 0x00be }
            r12.d(r0, r3, r2)     // Catch:{ EOFException -> 0x00be }
            byte[] r0 = r11.f     // Catch:{ EOFException -> 0x00be }
            byte r0 = r0[r3]     // Catch:{ EOFException -> 0x00be }
            r4 = r0 & 131(0x83, float:1.84E-43)
            if (r4 > 0) goto L_0x00a3
            int r0 = r0 >> 3
            r4 = 15
            r0 = r0 & r4
            if (r0 < 0) goto L_0x0042
            if (r0 > r4) goto L_0x0042
            boolean r4 = r11.g     // Catch:{ EOFException -> 0x00be }
            if (r4 == 0) goto L_0x002e
            r5 = 10
            if (r0 < r5) goto L_0x002c
            r5 = 13
            if (r0 <= r5) goto L_0x002e
        L_0x002c:
            r5 = 1
            goto L_0x002f
        L_0x002e:
            r5 = 0
        L_0x002f:
            if (r5 != 0) goto L_0x0040
            if (r4 != 0) goto L_0x003d
            r4 = 12
            if (r0 < r4) goto L_0x003b
            r4 = 14
            if (r0 <= r4) goto L_0x003d
        L_0x003b:
            r4 = 1
            goto L_0x003e
        L_0x003d:
            r4 = 0
        L_0x003e:
            if (r4 == 0) goto L_0x0042
        L_0x0040:
            r4 = 1
            goto L_0x0043
        L_0x0042:
            r4 = 0
        L_0x0043:
            if (r4 != 0) goto L_0x007b
            com.google.android.exoplayer2.ParserException r12 = new com.google.android.exoplayer2.ParserException     // Catch:{ EOFException -> 0x00be }
            boolean r2 = r11.g     // Catch:{ EOFException -> 0x00be }
            if (r2 == 0) goto L_0x004e
            java.lang.String r2 = "WB"
            goto L_0x0050
        L_0x004e:
            java.lang.String r2 = "NB"
        L_0x0050:
            java.lang.String r3 = java.lang.String.valueOf(r2)     // Catch:{ EOFException -> 0x00be }
            int r3 = r3.length()     // Catch:{ EOFException -> 0x00be }
            int r3 = r3 + 35
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ EOFException -> 0x00be }
            r4.<init>(r3)     // Catch:{ EOFException -> 0x00be }
            java.lang.String r3 = "Illegal AMR "
            java.lang.StringBuilder r3 = r4.append(r3)     // Catch:{ EOFException -> 0x00be }
            java.lang.StringBuilder r2 = r3.append(r2)     // Catch:{ EOFException -> 0x00be }
            java.lang.String r3 = " frame type "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ EOFException -> 0x00be }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ EOFException -> 0x00be }
            java.lang.String r0 = r0.toString()     // Catch:{ EOFException -> 0x00be }
            r12.<init>((java.lang.String) r0)     // Catch:{ EOFException -> 0x00be }
            throw r12     // Catch:{ EOFException -> 0x00be }
        L_0x007b:
            boolean r4 = r11.g     // Catch:{ EOFException -> 0x00be }
            if (r4 == 0) goto L_0x0084
            int[] r4 = b     // Catch:{ EOFException -> 0x00be }
            r0 = r4[r0]     // Catch:{ EOFException -> 0x00be }
            goto L_0x0088
        L_0x0084:
            int[] r4 = a     // Catch:{ EOFException -> 0x00be }
            r0 = r4[r0]     // Catch:{ EOFException -> 0x00be }
        L_0x0088:
            r11.i = r0     // Catch:{ EOFException -> 0x00be }
            r11.j = r0
            int r0 = r11.l
            if (r0 != r1) goto L_0x0097
            r12.c()
            int r0 = r11.i
            r11.l = r0
        L_0x0097:
            int r0 = r11.l
            int r4 = r11.i
            if (r0 != r4) goto L_0x00c0
            int r0 = r11.m
            int r0 = r0 + r2
            r11.m = r0
            goto L_0x00c0
        L_0x00a3:
            com.google.android.exoplayer2.ParserException r12 = new com.google.android.exoplayer2.ParserException     // Catch:{ EOFException -> 0x00be }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ EOFException -> 0x00be }
            r3 = 42
            r2.<init>(r3)     // Catch:{ EOFException -> 0x00be }
            java.lang.String r3 = "Invalid padding bits for frame header "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ EOFException -> 0x00be }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ EOFException -> 0x00be }
            java.lang.String r0 = r0.toString()     // Catch:{ EOFException -> 0x00be }
            r12.<init>((java.lang.String) r0)     // Catch:{ EOFException -> 0x00be }
            throw r12     // Catch:{ EOFException -> 0x00be }
        L_0x00be:
            r12 = move-exception
            return r1
        L_0x00c0:
            com.google.android.exoplayer2.extractor.TrackOutput r0 = r11.p
            int r4 = r11.j
            int r12 = r0.b(r12, r4, r2)
            if (r12 != r1) goto L_0x00cb
            return r1
        L_0x00cb:
            int r0 = r11.j
            int r0 = r0 - r12
            r11.j = r0
            if (r0 <= 0) goto L_0x00d3
            return r3
        L_0x00d3:
            com.google.android.exoplayer2.extractor.TrackOutput r4 = r11.p
            long r0 = r11.n
            long r5 = r11.h
            long r5 = r5 + r0
            r7 = 1
            int r8 = r11.i
            r9 = 0
            r10 = 0
            r4.a(r5, r7, r8, r9, r10)
            long r0 = r11.h
            r4 = 20000(0x4e20, double:9.8813E-320)
            long r0 = r0 + r4
            r11.h = r0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.amr.AmrExtractor.c(com.google.android.exoplayer2.extractor.ExtractorInput):int");
    }

    public final int a(ExtractorInput extractorInput, PositionHolder positionHolder) {
        Assertions.a((Object) this.p);
        Util.a((Object) this.o);
        if (extractorInput.c() != 0 || b(extractorInput)) {
            if (!this.r) {
                this.r = true;
                boolean z = this.g;
                String str = z ? "audio/amr-wb" : "audio/3gpp";
                int i2 = z ? 16000 : 8000;
                TrackOutput trackOutput = this.p;
                Format.Builder builder = new Format.Builder();
                builder.k = str;
                builder.l = e;
                builder.x = 1;
                builder.y = i2;
                trackOutput.a(builder.a());
            }
            int c2 = c(extractorInput);
            extractorInput.d();
            if (!this.k) {
                SeekMap.Unseekable unseekable = new SeekMap.Unseekable(-9223372036854775807L);
                this.q = unseekable;
                this.o.a(unseekable);
                this.k = true;
            }
            return c2;
        }
        throw new ParserException("Could not find AMR header.");
    }

    public final void a(long j2, long j3) {
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.n = 0;
    }

    public final void a(ExtractorOutput extractorOutput) {
        this.o = extractorOutput;
        this.p = extractorOutput.a(0, 1);
        extractorOutput.c_();
    }

    public final boolean a(ExtractorInput extractorInput) {
        return b(extractorInput);
    }
}
