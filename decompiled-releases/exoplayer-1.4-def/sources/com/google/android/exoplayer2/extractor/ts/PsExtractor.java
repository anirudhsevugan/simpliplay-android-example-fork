package com.google.android.exoplayer2.extractor.ts;

import android.util.SparseArray;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.appinventor.components.runtime.util.Ev3Constants;

public final class PsExtractor implements Extractor {
    private final TimestampAdjuster a;
    private final SparseArray b;
    private final ParsableByteArray c;
    private final PsDurationReader d;
    private boolean e;
    private boolean f;
    private boolean g;
    private long h;
    private PsBinarySearchSeeker i;
    private ExtractorOutput j;
    private boolean k;

    final class PesReader {
        final ElementaryStreamReader a;
        final TimestampAdjuster b;
        final ParsableBitArray c = new ParsableBitArray(new byte[64]);
        boolean d;
        boolean e;
        boolean f;
        int g;
        long h;

        public PesReader(ElementaryStreamReader elementaryStreamReader, TimestampAdjuster timestampAdjuster) {
            this.a = elementaryStreamReader;
            this.b = timestampAdjuster;
        }
    }

    static {
        ExtractorsFactory extractorsFactory = PsExtractor$$Lambda$0.b;
    }

    public PsExtractor() {
        this(new TimestampAdjuster(0));
    }

    private PsExtractor(TimestampAdjuster timestampAdjuster) {
        this.a = timestampAdjuster;
        this.c = new ParsableByteArray(4096);
        this.b = new SparseArray();
        this.d = new PsDurationReader();
    }

    static final /* synthetic */ Extractor[] a() {
        return new Extractor[]{new PsExtractor()};
    }

    /* JADX WARNING: Removed duplicated region for block: B:106:0x0219  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int a(com.google.android.exoplayer2.extractor.ExtractorInput r20, com.google.android.exoplayer2.extractor.PositionHolder r21) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r2 = r21
            com.google.android.exoplayer2.extractor.ExtractorOutput r3 = r0.j
            com.google.android.exoplayer2.util.Assertions.a((java.lang.Object) r3)
            long r10 = r20.d()
            r3 = 1
            r12 = 0
            r13 = -1
            int r4 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            if (r4 == 0) goto L_0x0019
            r4 = 1
            goto L_0x001a
        L_0x0019:
            r4 = 0
        L_0x001a:
            r15 = 442(0x1ba, float:6.2E-43)
            r8 = 0
            r6 = 3
            r7 = 4
            r16 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r4 == 0) goto L_0x0102
            com.google.android.exoplayer2.extractor.ts.PsDurationReader r4 = r0.d
            boolean r4 = r4.c
            if (r4 != 0) goto L_0x0102
            com.google.android.exoplayer2.extractor.ts.PsDurationReader r4 = r0.d
            boolean r5 = r4.e
            r10 = 20000(0x4e20, double:9.8813E-320)
            if (r5 != 0) goto L_0x0083
            long r5 = r20.d()
            long r8 = java.lang.Math.min(r10, r5)
            int r9 = (int) r8
            long r10 = (long) r9
            long r5 = r5 - r10
            long r10 = r20.c()
            int r8 = (r10 > r5 ? 1 : (r10 == r5 ? 0 : -1))
            if (r8 == 0) goto L_0x004b
            r2.a = r5
            return r3
        L_0x004b:
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r4.b
            r2.a(r9)
            r20.a()
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r4.b
            byte[] r2 = r2.a
            r1.d(r2, r12, r9)
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r4.b
            int r2 = r1.b
            int r5 = r1.c
            int r5 = r5 - r7
        L_0x0061:
            if (r5 < r2) goto L_0x007c
            byte[] r6 = r1.a
            int r6 = com.google.android.exoplayer2.extractor.ts.PsDurationReader.a(r6, r5)
            if (r6 != r15) goto L_0x0079
            int r6 = r5 + 4
            r1.d(r6)
            long r6 = com.google.android.exoplayer2.extractor.ts.PsDurationReader.a((com.google.android.exoplayer2.util.ParsableByteArray) r1)
            int r8 = (r6 > r16 ? 1 : (r6 == r16 ? 0 : -1))
            if (r8 == 0) goto L_0x0079
            goto L_0x007e
        L_0x0079:
            int r5 = r5 + -1
            goto L_0x0061
        L_0x007c:
            r6 = r16
        L_0x007e:
            r4.g = r6
            r4.e = r3
            return r12
        L_0x0083:
            long r13 = r4.g
            int r5 = (r13 > r16 ? 1 : (r13 == r16 ? 0 : -1))
            if (r5 != 0) goto L_0x008e
            int r1 = r4.a((com.google.android.exoplayer2.extractor.ExtractorInput) r1)
            return r1
        L_0x008e:
            boolean r5 = r4.d
            if (r5 != 0) goto L_0x00df
            long r13 = r20.d()
            long r10 = java.lang.Math.min(r10, r13)
            int r5 = (int) r10
            long r10 = r20.c()
            int r7 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r7 == 0) goto L_0x00a6
            r2.a = r8
            return r3
        L_0x00a6:
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r4.b
            r2.a(r5)
            r20.a()
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r4.b
            byte[] r2 = r2.a
            r1.d(r2, r12, r5)
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r4.b
            int r2 = r1.b
            int r5 = r1.c
        L_0x00bb:
            int r7 = r5 + -3
            if (r2 >= r7) goto L_0x00d8
            byte[] r7 = r1.a
            int r7 = com.google.android.exoplayer2.extractor.ts.PsDurationReader.a(r7, r2)
            if (r7 != r15) goto L_0x00d5
            int r7 = r2 + 4
            r1.d(r7)
            long r7 = com.google.android.exoplayer2.extractor.ts.PsDurationReader.a((com.google.android.exoplayer2.util.ParsableByteArray) r1)
            int r9 = (r7 > r16 ? 1 : (r7 == r16 ? 0 : -1))
            if (r9 == 0) goto L_0x00d5
            goto L_0x00da
        L_0x00d5:
            int r2 = r2 + 1
            goto L_0x00bb
        L_0x00d8:
            r7 = r16
        L_0x00da:
            r4.f = r7
            r4.d = r3
            return r12
        L_0x00df:
            long r2 = r4.f
            int r5 = (r2 > r16 ? 1 : (r2 == r16 ? 0 : -1))
            if (r5 != 0) goto L_0x00ea
            int r1 = r4.a((com.google.android.exoplayer2.extractor.ExtractorInput) r1)
            return r1
        L_0x00ea:
            com.google.android.exoplayer2.util.TimestampAdjuster r2 = r4.a
            long r5 = r4.f
            long r2 = r2.b(r5)
            com.google.android.exoplayer2.util.TimestampAdjuster r5 = r4.a
            long r6 = r4.g
            long r5 = r5.b(r6)
            long r5 = r5 - r2
            r4.h = r5
            int r1 = r4.a((com.google.android.exoplayer2.extractor.ExtractorInput) r1)
            return r1
        L_0x0102:
            boolean r4 = r0.k
            if (r4 != 0) goto L_0x013f
            r0.k = r3
            com.google.android.exoplayer2.extractor.ts.PsDurationReader r4 = r0.d
            long r4 = r4.h
            int r18 = (r4 > r16 ? 1 : (r4 == r16 ? 0 : -1))
            if (r18 == 0) goto L_0x012f
            com.google.android.exoplayer2.extractor.ts.PsBinarySearchSeeker r5 = new com.google.android.exoplayer2.extractor.ts.PsBinarySearchSeeker
            com.google.android.exoplayer2.extractor.ts.PsDurationReader r4 = r0.d
            com.google.android.exoplayer2.util.TimestampAdjuster r4 = r4.a
            com.google.android.exoplayer2.extractor.ts.PsDurationReader r6 = r0.d
            long r7 = r6.h
            r6 = r4
            r4 = r5
            r9 = r5
            r5 = r6
            r15 = 4
            r6 = r7
            r3 = r9
            r8 = r10
            r4.<init>(r5, r6, r8)
            r0.i = r3
            com.google.android.exoplayer2.extractor.ExtractorOutput r4 = r0.j
            com.google.android.exoplayer2.extractor.BinarySearchSeeker$BinarySearchSeekMap r3 = r3.a
            r4.a(r3)
            goto L_0x0140
        L_0x012f:
            r15 = 4
            com.google.android.exoplayer2.extractor.ExtractorOutput r3 = r0.j
            com.google.android.exoplayer2.extractor.SeekMap$Unseekable r4 = new com.google.android.exoplayer2.extractor.SeekMap$Unseekable
            com.google.android.exoplayer2.extractor.ts.PsDurationReader r5 = r0.d
            long r5 = r5.h
            r4.<init>(r5)
            r3.a(r4)
            goto L_0x0140
        L_0x013f:
            r15 = 4
        L_0x0140:
            com.google.android.exoplayer2.extractor.ts.PsBinarySearchSeeker r3 = r0.i
            if (r3 == 0) goto L_0x0151
            boolean r3 = r3.a()
            if (r3 == 0) goto L_0x0151
            com.google.android.exoplayer2.extractor.ts.PsBinarySearchSeeker r3 = r0.i
            int r1 = r3.a((com.google.android.exoplayer2.extractor.ExtractorInput) r1, (com.google.android.exoplayer2.extractor.PositionHolder) r2)
            return r1
        L_0x0151:
            r20.a()
            int r2 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            if (r2 == 0) goto L_0x015e
            long r2 = r20.b()
            long r10 = r10 - r2
            goto L_0x015f
        L_0x015e:
            r10 = r13
        L_0x015f:
            r2 = -1
            int r3 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            if (r3 == 0) goto L_0x016b
            r3 = 4
            int r5 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r5 >= 0) goto L_0x016b
            return r2
        L_0x016b:
            com.google.android.exoplayer2.util.ParsableByteArray r3 = r0.c
            byte[] r3 = r3.a
            r4 = 1
            boolean r3 = r1.b(r3, r12, r15, r4)
            if (r3 != 0) goto L_0x0177
            return r2
        L_0x0177:
            com.google.android.exoplayer2.util.ParsableByteArray r3 = r0.c
            r3.d(r12)
            com.google.android.exoplayer2.util.ParsableByteArray r3 = r0.c
            int r3 = r3.j()
            r4 = 441(0x1b9, float:6.18E-43)
            if (r3 != r4) goto L_0x0187
            return r2
        L_0x0187:
            r2 = 442(0x1ba, float:6.2E-43)
            if (r3 != r2) goto L_0x01a9
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.c
            byte[] r2 = r2.a
            r3 = 10
            r1.d(r2, r12, r3)
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.c
            r3 = 9
            r2.d(r3)
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.c
            int r2 = r2.c()
            r2 = r2 & 7
            int r2 = r2 + 14
            r1.b(r2)
            return r12
        L_0x01a9:
            r2 = 443(0x1bb, float:6.21E-43)
            r4 = 2
            r5 = 6
            if (r3 != r2) goto L_0x01c6
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.c
            byte[] r2 = r2.a
            r1.d(r2, r12, r4)
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.c
            r2.d(r12)
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.c
            int r2 = r2.d()
            int r2 = r2 + r5
            r1.b(r2)
            return r12
        L_0x01c6:
            r2 = r3 & -256(0xffffffffffffff00, float:NaN)
            r6 = 8
            int r2 = r2 >> r6
            r7 = 1
            if (r2 == r7) goto L_0x01d2
            r1.b(r7)
            return r12
        L_0x01d2:
            r2 = r3 & 255(0xff, float:3.57E-43)
            android.util.SparseArray r3 = r0.b
            java.lang.Object r3 = r3.get(r2)
            com.google.android.exoplayer2.extractor.ts.PsExtractor$PesReader r3 = (com.google.android.exoplayer2.extractor.ts.PsExtractor.PesReader) r3
            boolean r7 = r0.e
            if (r7 != 0) goto L_0x0252
            if (r3 != 0) goto L_0x0231
            r7 = 189(0xbd, float:2.65E-43)
            if (r2 != r7) goto L_0x01f5
            com.google.android.exoplayer2.extractor.ts.Ac3Reader r7 = new com.google.android.exoplayer2.extractor.ts.Ac3Reader
            r7.<init>()
            r8 = 1
        L_0x01ec:
            r0.f = r8
            long r9 = r20.c()
            r0.h = r9
            goto L_0x0217
        L_0x01f5:
            r8 = 1
            r7 = r2 & 224(0xe0, float:3.14E-43)
            r9 = 192(0xc0, float:2.69E-43)
            if (r7 != r9) goto L_0x0202
            com.google.android.exoplayer2.extractor.ts.MpegAudioReader r7 = new com.google.android.exoplayer2.extractor.ts.MpegAudioReader
            r7.<init>()
            goto L_0x01ec
        L_0x0202:
            r7 = r2 & 240(0xf0, float:3.36E-43)
            r9 = 224(0xe0, float:3.14E-43)
            if (r7 != r9) goto L_0x0216
            com.google.android.exoplayer2.extractor.ts.H262Reader r7 = new com.google.android.exoplayer2.extractor.ts.H262Reader
            r7.<init>()
            r0.g = r8
            long r8 = r20.c()
            r0.h = r8
            goto L_0x0217
        L_0x0216:
            r7 = 0
        L_0x0217:
            if (r7 == 0) goto L_0x0231
            com.google.android.exoplayer2.extractor.ts.TsPayloadReader$TrackIdGenerator r3 = new com.google.android.exoplayer2.extractor.ts.TsPayloadReader$TrackIdGenerator
            r8 = 256(0x100, float:3.59E-43)
            r3.<init>(r2, r8)
            com.google.android.exoplayer2.extractor.ExtractorOutput r8 = r0.j
            r7.a((com.google.android.exoplayer2.extractor.ExtractorOutput) r8, (com.google.android.exoplayer2.extractor.ts.TsPayloadReader.TrackIdGenerator) r3)
            com.google.android.exoplayer2.extractor.ts.PsExtractor$PesReader r3 = new com.google.android.exoplayer2.extractor.ts.PsExtractor$PesReader
            com.google.android.exoplayer2.util.TimestampAdjuster r8 = r0.a
            r3.<init>(r7, r8)
            android.util.SparseArray r7 = r0.b
            r7.put(r2, r3)
        L_0x0231:
            boolean r2 = r0.f
            if (r2 == 0) goto L_0x023f
            boolean r2 = r0.g
            if (r2 == 0) goto L_0x023f
            long r7 = r0.h
            r9 = 8192(0x2000, double:4.0474E-320)
            long r7 = r7 + r9
            goto L_0x0242
        L_0x023f:
            r7 = 1048576(0x100000, double:5.180654E-318)
        L_0x0242:
            long r9 = r20.c()
            int r2 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r2 <= 0) goto L_0x0252
            r2 = 1
            r0.e = r2
            com.google.android.exoplayer2.extractor.ExtractorOutput r2 = r0.j
            r2.c_()
        L_0x0252:
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.c
            byte[] r2 = r2.a
            r1.d(r2, r12, r4)
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.c
            r2.d(r12)
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.c
            int r2 = r2.d()
            int r2 = r2 + r5
            if (r3 != 0) goto L_0x026c
            r1.b(r2)
            goto L_0x0354
        L_0x026c:
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r0.c
            r4.a(r2)
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r0.c
            byte[] r4 = r4.a
            r1.b(r4, r12, r2)
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r0.c
            r1.d(r5)
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r0.c
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r3.c
            byte[] r2 = r2.a
            r4 = 3
            r1.a(r2, r12, r4)
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r3.c
            r2.a(r12)
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r3.c
            r2.b(r6)
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r3.c
            boolean r2 = r2.e()
            r3.d = r2
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r3.c
            boolean r2 = r2.e()
            r3.e = r2
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r3.c
            r2.b(r5)
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r3.c
            int r2 = r2.c(r6)
            r3.g = r2
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r3.c
            byte[] r2 = r2.a
            int r5 = r3.g
            r1.a(r2, r12, r5)
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r3.c
            r2.a(r12)
            r5 = 0
            r3.h = r5
            boolean r2 = r3.d
            if (r2 == 0) goto L_0x033b
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r3.c
            r2.b(r15)
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r3.c
            int r2 = r2.c(r4)
            long r5 = (long) r2
            r2 = 30
            long r5 = r5 << r2
            com.google.android.exoplayer2.util.ParsableBitArray r7 = r3.c
            r8 = 1
            r7.b(r8)
            com.google.android.exoplayer2.util.ParsableBitArray r7 = r3.c
            r9 = 15
            int r7 = r7.c(r9)
            int r7 = r7 << r9
            long r10 = (long) r7
            long r5 = r5 | r10
            com.google.android.exoplayer2.util.ParsableBitArray r7 = r3.c
            r7.b(r8)
            com.google.android.exoplayer2.util.ParsableBitArray r7 = r3.c
            int r7 = r7.c(r9)
            long r10 = (long) r7
            long r5 = r5 | r10
            com.google.android.exoplayer2.util.ParsableBitArray r7 = r3.c
            r7.b(r8)
            boolean r7 = r3.f
            if (r7 != 0) goto L_0x0333
            boolean r7 = r3.e
            if (r7 == 0) goto L_0x0333
            com.google.android.exoplayer2.util.ParsableBitArray r7 = r3.c
            r7.b(r15)
            com.google.android.exoplayer2.util.ParsableBitArray r7 = r3.c
            int r4 = r7.c(r4)
            long r7 = (long) r4
            long r7 = r7 << r2
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r3.c
            r4 = 1
            r2.b(r4)
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r3.c
            int r2 = r2.c(r9)
            int r2 = r2 << r9
            long r10 = (long) r2
            long r7 = r7 | r10
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r3.c
            r2.b(r4)
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r3.c
            int r2 = r2.c(r9)
            long r9 = (long) r2
            long r7 = r7 | r9
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r3.c
            r2.b(r4)
            com.google.android.exoplayer2.util.TimestampAdjuster r2 = r3.b
            r2.b(r7)
            r3.f = r4
        L_0x0333:
            com.google.android.exoplayer2.util.TimestampAdjuster r2 = r3.b
            long r4 = r2.b(r5)
            r3.h = r4
        L_0x033b:
            com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader r2 = r3.a
            long r4 = r3.h
            r2.a((long) r4, (int) r15)
            com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader r2 = r3.a
            r2.a(r1)
            com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader r1 = r3.a
            r1.b()
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r0.c
            byte[] r2 = r1.a
            int r2 = r2.length
            r1.c(r2)
        L_0x0354:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.ts.PsExtractor.a(com.google.android.exoplayer2.extractor.ExtractorInput, com.google.android.exoplayer2.extractor.PositionHolder):int");
    }

    public final void a(long j2, long j3) {
        if ((this.a.c() == -9223372036854775807L) || !(this.a.a() == 0 || this.a.a() == j3)) {
            this.a.a(j3);
        }
        PsBinarySearchSeeker psBinarySearchSeeker = this.i;
        if (psBinarySearchSeeker != null) {
            psBinarySearchSeeker.a(j3);
        }
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            PesReader pesReader = (PesReader) this.b.valueAt(i2);
            pesReader.f = false;
            pesReader.a.a();
        }
    }

    public final void a(ExtractorOutput extractorOutput) {
        this.j = extractorOutput;
    }

    public final boolean a(ExtractorInput extractorInput) {
        byte[] bArr = new byte[14];
        extractorInput.d(bArr, 0, 14);
        if (442 != (((bArr[0] & Ev3Constants.Opcode.TST) << 24) | ((bArr[1] & Ev3Constants.Opcode.TST) << 16) | ((bArr[2] & Ev3Constants.Opcode.TST) << 8) | (bArr[3] & Ev3Constants.Opcode.TST)) || (bArr[4] & Ev3Constants.Opcode.ARRAY_APPEND) != 68 || (bArr[6] & 4) != 4 || (bArr[8] & 4) != 4 || (bArr[9] & 1) != 1 || (bArr[12] & 3) != 3) {
            return false;
        }
        extractorInput.c(bArr[13] & 7);
        extractorInput.d(bArr, 0, 3);
        return 1 == ((((bArr[0] & Ev3Constants.Opcode.TST) << 16) | ((bArr[1] & Ev3Constants.Opcode.TST) << 8)) | (bArr[2] & Ev3Constants.Opcode.TST));
    }
}
