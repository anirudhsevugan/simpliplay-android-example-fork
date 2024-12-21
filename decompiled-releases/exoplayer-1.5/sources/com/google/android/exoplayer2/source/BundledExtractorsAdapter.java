package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.mp3.Mp3Extractor;
import com.google.android.exoplayer2.util.Assertions;

public final class BundledExtractorsAdapter implements ProgressiveMediaExtractor {
    private final ExtractorsFactory a;
    private Extractor b;
    private ExtractorInput c;

    public BundledExtractorsAdapter(ExtractorsFactory extractorsFactory) {
        this.a = extractorsFactory;
    }

    public final int a(PositionHolder positionHolder) {
        return ((Extractor) Assertions.b((Object) this.b)).a((ExtractorInput) Assertions.b((Object) this.c), positionHolder);
    }

    public final void a() {
        if (this.b != null) {
            this.b = null;
        }
        this.c = null;
    }

    public final void a(long j, long j2) {
        ((Extractor) Assertions.b((Object) this.b)).a(j, j2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004a, code lost:
        if (r0.c() != r11) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x006d, code lost:
        if (r0.c() != r11) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0070, code lost:
        r1 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.google.android.exoplayer2.upstream.DataReader r8, android.net.Uri r9, java.util.Map r10, long r11, long r13, com.google.android.exoplayer2.extractor.ExtractorOutput r15) {
        /*
            r7 = this;
            com.google.android.exoplayer2.extractor.DefaultExtractorInput r6 = new com.google.android.exoplayer2.extractor.DefaultExtractorInput
            r0 = r6
            r1 = r8
            r2 = r11
            r4 = r13
            r0.<init>(r1, r2, r4)
            r7.c = r6
            com.google.android.exoplayer2.extractor.Extractor r8 = r7.b
            if (r8 == 0) goto L_0x0010
            return
        L_0x0010:
            com.google.android.exoplayer2.extractor.ExtractorsFactory r8 = r7.a
            com.google.android.exoplayer2.extractor.Extractor[] r8 = r8.a(r9, r10)
            int r10 = r8.length
            r13 = 0
            r14 = 1
            if (r10 != r14) goto L_0x0020
            r8 = r8[r13]
            r7.b = r8
            goto L_0x0080
        L_0x0020:
            int r10 = r8.length
            r0 = 0
        L_0x0022:
            if (r0 >= r10) goto L_0x007c
            r1 = r8[r0]
            boolean r2 = r1.a((com.google.android.exoplayer2.extractor.ExtractorInput) r6)     // Catch:{ EOFException -> 0x0062, all -> 0x004d }
            if (r2 == 0) goto L_0x0040
            r7.b = r1     // Catch:{ EOFException -> 0x0062, all -> 0x004d }
            if (r1 != 0) goto L_0x0038
            long r0 = r6.c()
            int r10 = (r0 > r11 ? 1 : (r0 == r11 ? 0 : -1))
            if (r10 != 0) goto L_0x0039
        L_0x0038:
            r13 = 1
        L_0x0039:
            com.google.android.exoplayer2.util.Assertions.b((boolean) r13)
            r6.a()
            goto L_0x007c
        L_0x0040:
            com.google.android.exoplayer2.extractor.Extractor r1 = r7.b
            if (r1 != 0) goto L_0x0072
            long r1 = r6.c()
            int r3 = (r1 > r11 ? 1 : (r1 == r11 ? 0 : -1))
            if (r3 != 0) goto L_0x0070
            goto L_0x0072
        L_0x004d:
            r8 = move-exception
            com.google.android.exoplayer2.extractor.Extractor r9 = r7.b
            if (r9 != 0) goto L_0x005a
            long r9 = r6.c()
            int r15 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r15 != 0) goto L_0x005b
        L_0x005a:
            r13 = 1
        L_0x005b:
            com.google.android.exoplayer2.util.Assertions.b((boolean) r13)
            r6.a()
            throw r8
        L_0x0062:
            r1 = move-exception
            com.google.android.exoplayer2.extractor.Extractor r1 = r7.b
            if (r1 != 0) goto L_0x0072
            long r1 = r6.c()
            int r3 = (r1 > r11 ? 1 : (r1 == r11 ? 0 : -1))
            if (r3 != 0) goto L_0x0070
            goto L_0x0072
        L_0x0070:
            r1 = 0
            goto L_0x0073
        L_0x0072:
            r1 = 1
        L_0x0073:
            com.google.android.exoplayer2.util.Assertions.b((boolean) r1)
            r6.a()
            int r0 = r0 + 1
            goto L_0x0022
        L_0x007c:
            com.google.android.exoplayer2.extractor.Extractor r10 = r7.b
            if (r10 == 0) goto L_0x0086
        L_0x0080:
            com.google.android.exoplayer2.extractor.Extractor r8 = r7.b
            r8.a((com.google.android.exoplayer2.extractor.ExtractorOutput) r15)
            return
        L_0x0086:
            com.google.android.exoplayer2.source.UnrecognizedInputFormatException r10 = new com.google.android.exoplayer2.source.UnrecognizedInputFormatException
            java.lang.String r8 = com.google.android.exoplayer2.util.Util.b((java.lang.Object[]) r8)
            java.lang.String r11 = java.lang.String.valueOf(r8)
            int r11 = r11.length()
            int r11 = r11 + 58
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>(r11)
            java.lang.String r11 = "None of the available extractors ("
            java.lang.StringBuilder r11 = r12.append(r11)
            java.lang.StringBuilder r8 = r11.append(r8)
            java.lang.String r11 = ") could read the stream."
            java.lang.StringBuilder r8 = r8.append(r11)
            java.lang.String r8 = r8.toString()
            com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r9)
            r10.<init>(r8)
            goto L_0x00b7
        L_0x00b6:
            throw r10
        L_0x00b7:
            goto L_0x00b6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.BundledExtractorsAdapter.a(com.google.android.exoplayer2.upstream.DataReader, android.net.Uri, java.util.Map, long, long, com.google.android.exoplayer2.extractor.ExtractorOutput):void");
    }

    public final void b() {
        Extractor extractor = this.b;
        if (extractor instanceof Mp3Extractor) {
            ((Mp3Extractor) extractor).a = true;
        }
    }

    public final long c() {
        ExtractorInput extractorInput = this.c;
        if (extractorInput != null) {
            return extractorInput.c();
        }
        return -1;
    }
}
