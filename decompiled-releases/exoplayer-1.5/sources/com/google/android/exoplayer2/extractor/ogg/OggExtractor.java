package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.util.ParsableByteArray;

public class OggExtractor implements Extractor {
    private ExtractorOutput a;
    private StreamReader b;
    private boolean c;

    static {
        ExtractorsFactory extractorsFactory = OggExtractor$$Lambda$0.b;
    }

    static final /* synthetic */ Extractor[] a() {
        return new Extractor[]{new OggExtractor()};
    }

    private boolean b(ExtractorInput extractorInput) {
        StreamReader opusReader;
        OggPageHeader oggPageHeader = new OggPageHeader();
        if (oggPageHeader.a(extractorInput, true) && (oggPageHeader.a & 2) == 2) {
            int min = Math.min(oggPageHeader.e, 8);
            ParsableByteArray parsableByteArray = new ParsableByteArray(min);
            extractorInput.d(parsableByteArray.a, 0, min);
            parsableByteArray.d(0);
            if (FlacReader.a(parsableByteArray)) {
                opusReader = new FlacReader();
            } else {
                parsableByteArray.d(0);
                if (VorbisReader.a(parsableByteArray)) {
                    opusReader = new VorbisReader();
                } else {
                    parsableByteArray.d(0);
                    if (OpusReader.a(parsableByteArray)) {
                        opusReader = new OpusReader();
                    }
                }
            }
            this.b = opusReader;
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x00da  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int a(com.google.android.exoplayer2.extractor.ExtractorInput r19, com.google.android.exoplayer2.extractor.PositionHolder r20) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            com.google.android.exoplayer2.extractor.ExtractorOutput r2 = r0.a
            com.google.android.exoplayer2.util.Assertions.a((java.lang.Object) r2)
            com.google.android.exoplayer2.extractor.ogg.StreamReader r2 = r0.b
            if (r2 != 0) goto L_0x001f
            boolean r2 = r18.b(r19)
            if (r2 == 0) goto L_0x0017
            r19.a()
            goto L_0x001f
        L_0x0017:
            com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
            java.lang.String r2 = "Failed to determine bitstream type"
            r1.<init>((java.lang.String) r2)
            throw r1
        L_0x001f:
            boolean r2 = r0.c
            r3 = 1
            r4 = 0
            if (r2 != 0) goto L_0x0039
            com.google.android.exoplayer2.extractor.ExtractorOutput r2 = r0.a
            com.google.android.exoplayer2.extractor.TrackOutput r2 = r2.a(r4, r3)
            com.google.android.exoplayer2.extractor.ExtractorOutput r5 = r0.a
            r5.c_()
            com.google.android.exoplayer2.extractor.ogg.StreamReader r5 = r0.b
            com.google.android.exoplayer2.extractor.ExtractorOutput r6 = r0.a
            r5.a((com.google.android.exoplayer2.extractor.ExtractorOutput) r6, (com.google.android.exoplayer2.extractor.TrackOutput) r2)
            r0.c = r3
        L_0x0039:
            com.google.android.exoplayer2.extractor.ogg.StreamReader r2 = r0.b
            com.google.android.exoplayer2.extractor.TrackOutput r5 = r2.b
            com.google.android.exoplayer2.util.Assertions.a((java.lang.Object) r5)
            com.google.android.exoplayer2.extractor.ExtractorOutput r5 = r2.c
            com.google.android.exoplayer2.util.Util.a((java.lang.Object) r5)
            int r5 = r2.f
            r6 = -1
            r15 = 2
            switch(r5) {
                case 0: goto L_0x0068;
                case 1: goto L_0x005f;
                case 2: goto L_0x0053;
                case 3: goto L_0x0052;
                default: goto L_0x004c;
            }
        L_0x004c:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            r1.<init>()
            throw r1
        L_0x0052:
            return r6
        L_0x0053:
            com.google.android.exoplayer2.extractor.ogg.OggSeeker r3 = r2.d
            com.google.android.exoplayer2.util.Util.a((java.lang.Object) r3)
            r3 = r20
            int r1 = r2.a((com.google.android.exoplayer2.extractor.ExtractorInput) r1, (com.google.android.exoplayer2.extractor.PositionHolder) r3)
            return r1
        L_0x005f:
            long r5 = r2.e
            int r3 = (int) r5
            r1.b(r3)
            r2.f = r15
            return r4
        L_0x0068:
            boolean r5 = r2.a((com.google.android.exoplayer2.extractor.ExtractorInput) r1)
            if (r5 != 0) goto L_0x006f
            return r6
        L_0x006f:
            com.google.android.exoplayer2.extractor.ogg.StreamReader$SetupData r5 = r2.h
            com.google.android.exoplayer2.Format r5 = r5.a
            int r5 = r5.x
            r2.g = r5
            boolean r5 = r2.i
            if (r5 != 0) goto L_0x0086
            com.google.android.exoplayer2.extractor.TrackOutput r5 = r2.b
            com.google.android.exoplayer2.extractor.ogg.StreamReader$SetupData r6 = r2.h
            com.google.android.exoplayer2.Format r6 = r6.a
            r5.a(r6)
            r2.i = r3
        L_0x0086:
            com.google.android.exoplayer2.extractor.ogg.StreamReader$SetupData r5 = r2.h
            com.google.android.exoplayer2.extractor.ogg.OggSeeker r5 = r5.b
            if (r5 == 0) goto L_0x0094
            com.google.android.exoplayer2.extractor.ogg.StreamReader$SetupData r1 = r2.h
            com.google.android.exoplayer2.extractor.ogg.OggSeeker r1 = r1.b
        L_0x0090:
            r2.d = r1
            r1 = 2
            goto L_0x00cc
        L_0x0094:
            long r5 = r19.d()
            r7 = -1
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 != 0) goto L_0x00a4
            com.google.android.exoplayer2.extractor.ogg.StreamReader$UnseekableOggSeeker r1 = new com.google.android.exoplayer2.extractor.ogg.StreamReader$UnseekableOggSeeker
            r1.<init>(r4)
            goto L_0x0090
        L_0x00a4:
            com.google.android.exoplayer2.extractor.ogg.OggPacket r5 = r2.a
            com.google.android.exoplayer2.extractor.ogg.OggPageHeader r5 = r5.a
            int r6 = r5.a
            r6 = r6 & 4
            if (r6 == 0) goto L_0x00b1
            r17 = 1
            goto L_0x00b3
        L_0x00b1:
            r17 = 0
        L_0x00b3:
            com.google.android.exoplayer2.extractor.ogg.DefaultOggSeeker r3 = new com.google.android.exoplayer2.extractor.ogg.DefaultOggSeeker
            long r9 = r2.e
            long r11 = r19.d()
            int r1 = r5.d
            int r6 = r5.e
            int r1 = r1 + r6
            long r13 = (long) r1
            long r5 = r5.b
            r7 = r3
            r8 = r2
            r1 = 2
            r15 = r5
            r7.<init>(r8, r9, r11, r13, r15, r17)
            r2.d = r3
        L_0x00cc:
            r2.f = r1
            com.google.android.exoplayer2.extractor.ogg.OggPacket r1 = r2.a
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r1.b
            byte[] r2 = r2.a
            int r2 = r2.length
            r3 = 65025(0xfe01, float:9.112E-41)
            if (r2 == r3) goto L_0x00f3
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r1.b
            com.google.android.exoplayer2.util.ParsableByteArray r5 = r1.b
            byte[] r5 = r5.a
            com.google.android.exoplayer2.util.ParsableByteArray r6 = r1.b
            int r6 = r6.c
            int r3 = java.lang.Math.max(r3, r6)
            byte[] r3 = java.util.Arrays.copyOf(r5, r3)
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r1.b
            int r1 = r1.c
            r2.a((byte[]) r3, (int) r1)
        L_0x00f3:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.ogg.OggExtractor.a(com.google.android.exoplayer2.extractor.ExtractorInput, com.google.android.exoplayer2.extractor.PositionHolder):int");
    }

    public final void a(long j, long j2) {
        StreamReader streamReader = this.b;
        if (streamReader != null) {
            streamReader.a(j, j2);
        }
    }

    public final void a(ExtractorOutput extractorOutput) {
        this.a = extractorOutput;
    }

    public final boolean a(ExtractorInput extractorInput) {
        try {
            return b(extractorInput);
        } catch (ParserException e) {
            return false;
        }
    }
}
