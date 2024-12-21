package com.google.android.exoplayer2.extractor.jpeg;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.mp4.Mp4Extractor;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.mp4.MotionPhotoMetadata;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;

public final class JpegExtractor implements Extractor {
    private final ParsableByteArray a = new ParsableByteArray(6);
    private ExtractorOutput b;
    private int c;
    private int d;
    private int e;
    private long f = -1;
    private MotionPhotoMetadata g;
    private ExtractorInput h;
    private StartOffsetExtractorInput i;
    private Mp4Extractor j;

    private void a() {
        a(new Metadata.Entry[0]);
        ((ExtractorOutput) Assertions.b((Object) this.b)).c_();
        this.b.a(new SeekMap.Unseekable(-9223372036854775807L));
        this.c = 6;
    }

    private void a(Metadata.Entry... entryArr) {
        TrackOutput a2 = ((ExtractorOutput) Assertions.b((Object) this.b)).a(1024, 4);
        Format.Builder builder = new Format.Builder();
        builder.i = new Metadata(entryArr);
        a2.a(builder.a());
    }

    private int b(ExtractorInput extractorInput) {
        this.a.a(2);
        extractorInput.d(this.a.a, 0, 2);
        return this.a.d();
    }

    /* JADX WARNING: Removed duplicated region for block: B:68:0x014d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int a(com.google.android.exoplayer2.extractor.ExtractorInput r25, com.google.android.exoplayer2.extractor.PositionHolder r26) {
        /*
            r24 = this;
            r0 = r24
            r1 = r25
            r2 = r26
            int r3 = r0.c
            r4 = 1
            r5 = 2
            r6 = -1
            r8 = 0
            switch(r3) {
                case 0: goto L_0x0172;
                case 1: goto L_0x015a;
                case 2: goto L_0x00a3;
                case 3: goto L_0x0010;
                case 4: goto L_0x0043;
                case 5: goto L_0x0018;
                case 6: goto L_0x0016;
                default: goto L_0x0010;
            }
        L_0x0010:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            r1.<init>()
            throw r1
        L_0x0016:
            r1 = -1
            return r1
        L_0x0018:
            com.google.android.exoplayer2.extractor.jpeg.StartOffsetExtractorInput r3 = r0.i
            if (r3 == 0) goto L_0x0020
            com.google.android.exoplayer2.extractor.ExtractorInput r3 = r0.h
            if (r1 == r3) goto L_0x002b
        L_0x0020:
            r0.h = r1
            com.google.android.exoplayer2.extractor.jpeg.StartOffsetExtractorInput r3 = new com.google.android.exoplayer2.extractor.jpeg.StartOffsetExtractorInput
            long r5 = r0.f
            r3.<init>(r1, r5)
            r0.i = r3
        L_0x002b:
            com.google.android.exoplayer2.extractor.mp4.Mp4Extractor r1 = r0.j
            java.lang.Object r1 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r1)
            com.google.android.exoplayer2.extractor.mp4.Mp4Extractor r1 = (com.google.android.exoplayer2.extractor.mp4.Mp4Extractor) r1
            com.google.android.exoplayer2.extractor.jpeg.StartOffsetExtractorInput r3 = r0.i
            int r1 = r1.a((com.google.android.exoplayer2.extractor.ExtractorInput) r3, (com.google.android.exoplayer2.extractor.PositionHolder) r2)
            if (r1 != r4) goto L_0x0042
            long r3 = r2.a
            long r5 = r0.f
            long r3 = r3 + r5
            r2.a = r3
        L_0x0042:
            return r1
        L_0x0043:
            long r5 = r25.c()
            long r9 = r0.f
            int r3 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r3 == 0) goto L_0x0050
            r2.a = r9
            return r4
        L_0x0050:
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.a
            byte[] r2 = r2.a
            boolean r2 = r1.b(r2, r8, r4, r4)
            if (r2 == 0) goto L_0x009f
            r25.a()
            com.google.android.exoplayer2.extractor.mp4.Mp4Extractor r2 = r0.j
            if (r2 != 0) goto L_0x0068
            com.google.android.exoplayer2.extractor.mp4.Mp4Extractor r2 = new com.google.android.exoplayer2.extractor.mp4.Mp4Extractor
            r2.<init>()
            r0.j = r2
        L_0x0068:
            com.google.android.exoplayer2.extractor.jpeg.StartOffsetExtractorInput r2 = new com.google.android.exoplayer2.extractor.jpeg.StartOffsetExtractorInput
            long r5 = r0.f
            r2.<init>(r1, r5)
            r0.i = r2
            com.google.android.exoplayer2.extractor.mp4.Mp4Extractor r1 = r0.j
            boolean r1 = r1.a((com.google.android.exoplayer2.extractor.ExtractorInput) r2)
            if (r1 == 0) goto L_0x009f
            com.google.android.exoplayer2.extractor.mp4.Mp4Extractor r1 = r0.j
            com.google.android.exoplayer2.extractor.jpeg.StartOffsetExtractorOutput r2 = new com.google.android.exoplayer2.extractor.jpeg.StartOffsetExtractorOutput
            long r5 = r0.f
            com.google.android.exoplayer2.extractor.ExtractorOutput r3 = r0.b
            java.lang.Object r3 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r3)
            com.google.android.exoplayer2.extractor.ExtractorOutput r3 = (com.google.android.exoplayer2.extractor.ExtractorOutput) r3
            r2.<init>(r5, r3)
            r1.a = r2
            com.google.android.exoplayer2.metadata.Metadata$Entry[] r1 = new com.google.android.exoplayer2.metadata.Metadata.Entry[r4]
            com.google.android.exoplayer2.metadata.mp4.MotionPhotoMetadata r2 = r0.g
            java.lang.Object r2 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r2)
            com.google.android.exoplayer2.metadata.Metadata$Entry r2 = (com.google.android.exoplayer2.metadata.Metadata.Entry) r2
            r1[r8] = r2
            r0.a((com.google.android.exoplayer2.metadata.Metadata.Entry[]) r1)
            r1 = 5
            r0.c = r1
            goto L_0x00a2
        L_0x009f:
            r24.a()
        L_0x00a2:
            return r8
        L_0x00a3:
            int r2 = r0.d
            r3 = 65505(0xffe1, float:9.1792E-41)
            if (r2 != r3) goto L_0x0152
            com.google.android.exoplayer2.util.ParsableByteArray r2 = new com.google.android.exoplayer2.util.ParsableByteArray
            int r3 = r0.e
            r2.<init>((int) r3)
            byte[] r3 = r2.a
            int r9 = r0.e
            r1.b(r3, r8, r9)
            com.google.android.exoplayer2.metadata.mp4.MotionPhotoMetadata r3 = r0.g
            if (r3 != 0) goto L_0x0157
            java.lang.String r3 = "http://ns.adobe.com/xap/1.0/"
            java.lang.String r9 = r2.r()
            boolean r3 = r3.equals(r9)
            if (r3 == 0) goto L_0x0157
            java.lang.String r2 = r2.r()
            if (r2 == 0) goto L_0x0157
            long r9 = r25.d()
            int r3 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
            if (r3 != 0) goto L_0x00d9
        L_0x00d6:
            r1 = 0
            goto L_0x0149
        L_0x00d9:
            com.google.android.exoplayer2.extractor.jpeg.MotionPhotoDescription r2 = com.google.android.exoplayer2.extractor.jpeg.XmpMotionPhotoDescriptionParser.a((java.lang.String) r2)
            if (r2 != 0) goto L_0x00e0
            goto L_0x00d6
        L_0x00e0:
            java.util.List r3 = r2.b
            int r3 = r3.size()
            if (r3 >= r5) goto L_0x00e9
            goto L_0x00d6
        L_0x00e9:
            java.util.List r3 = r2.b
            int r3 = r3.size()
            int r3 = r3 - r4
            r12 = r6
            r14 = r12
            r18 = r14
            r20 = r18
            r4 = 0
        L_0x00f7:
            if (r3 < 0) goto L_0x012e
            java.util.List r5 = r2.b
            java.lang.Object r5 = r5.get(r3)
            com.google.android.exoplayer2.extractor.jpeg.MotionPhotoDescription$ContainerItem r5 = (com.google.android.exoplayer2.extractor.jpeg.MotionPhotoDescription.ContainerItem) r5
            java.lang.String r11 = "video/mp4"
            java.lang.String r1 = r5.a
            boolean r1 = r11.equals(r1)
            r1 = r1 | r4
            if (r3 != 0) goto L_0x0112
            long r4 = r5.c
            long r9 = r9 - r4
            r4 = 0
            goto L_0x0116
        L_0x0112:
            long r4 = r5.b
            long r4 = r9 - r4
        L_0x0116:
            r22 = r4
            r4 = r9
            r9 = r22
            if (r1 == 0) goto L_0x0126
            int r11 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r11 == 0) goto L_0x0126
            long r20 = r4 - r9
            r18 = r9
            r1 = 0
        L_0x0126:
            if (r3 != 0) goto L_0x012a
            r14 = r4
            r12 = r9
        L_0x012a:
            int r3 = r3 + -1
            r4 = r1
            goto L_0x00f7
        L_0x012e:
            int r1 = (r18 > r6 ? 1 : (r18 == r6 ? 0 : -1))
            if (r1 == 0) goto L_0x00d6
            int r1 = (r20 > r6 ? 1 : (r20 == r6 ? 0 : -1))
            if (r1 == 0) goto L_0x00d6
            int r1 = (r12 > r6 ? 1 : (r12 == r6 ? 0 : -1))
            if (r1 == 0) goto L_0x00d6
            int r1 = (r14 > r6 ? 1 : (r14 == r6 ? 0 : -1))
            if (r1 != 0) goto L_0x013f
            goto L_0x00d6
        L_0x013f:
            com.google.android.exoplayer2.metadata.mp4.MotionPhotoMetadata r1 = new com.google.android.exoplayer2.metadata.mp4.MotionPhotoMetadata
            long r2 = r2.a
            r11 = r1
            r16 = r2
            r11.<init>(r12, r14, r16, r18, r20)
        L_0x0149:
            r0.g = r1
            if (r1 == 0) goto L_0x0157
            long r1 = r1.a
            r0.f = r1
            goto L_0x0157
        L_0x0152:
            int r2 = r0.e
            r1.b(r2)
        L_0x0157:
            r0.c = r8
            return r8
        L_0x015a:
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.a
            r2.a(r5)
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.a
            byte[] r2 = r2.a
            r1.b(r2, r8, r5)
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r0.a
            int r1 = r1.d()
            int r1 = r1 - r5
            r0.e = r1
            r0.c = r5
            return r8
        L_0x0172:
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.a
            r2.a(r5)
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.a
            byte[] r2 = r2.a
            r1.b(r2, r8, r5)
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r0.a
            int r1 = r1.d()
            r0.d = r1
            r2 = 65498(0xffda, float:9.1782E-41)
            if (r1 != r2) goto L_0x0199
            long r1 = r0.f
            int r3 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r3 == 0) goto L_0x0195
            r1 = 4
            r0.c = r1
            goto L_0x01aa
        L_0x0195:
            r24.a()
            goto L_0x01aa
        L_0x0199:
            r2 = 65488(0xffd0, float:9.1768E-41)
            if (r1 < r2) goto L_0x01a3
            r2 = 65497(0xffd9, float:9.1781E-41)
            if (r1 <= r2) goto L_0x01aa
        L_0x01a3:
            r2 = 65281(0xff01, float:9.1478E-41)
            if (r1 == r2) goto L_0x01aa
            r0.c = r4
        L_0x01aa:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.jpeg.JpegExtractor.a(com.google.android.exoplayer2.extractor.ExtractorInput, com.google.android.exoplayer2.extractor.PositionHolder):int");
    }

    public final void a(long j2, long j3) {
        if (j2 == 0) {
            this.c = 0;
            this.j = null;
        } else if (this.c == 5) {
            ((Mp4Extractor) Assertions.b((Object) this.j)).a(j2, j3);
        }
    }

    public final void a(ExtractorOutput extractorOutput) {
        this.b = extractorOutput;
    }

    public final boolean a(ExtractorInput extractorInput) {
        if (b(extractorInput) != 65496) {
            return false;
        }
        int b2 = b(extractorInput);
        this.d = b2;
        if (b2 == 65504) {
            this.a.a(2);
            extractorInput.d(this.a.a, 0, 2);
            extractorInput.c(this.a.d() - 2);
            this.d = b(extractorInput);
        }
        if (this.d != 65505) {
            return false;
        }
        extractorInput.c(2);
        this.a.a(6);
        extractorInput.d(this.a.a, 0, 6);
        return this.a.h() == 1165519206 && this.a.d() == 0;
    }
}
