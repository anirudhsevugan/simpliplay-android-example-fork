package com.google.android.exoplayer2.extractor.wav;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;
import kawa.Telnet;

public final class WavExtractor implements Extractor {
    private ExtractorOutput a;
    private TrackOutput b;
    private OutputWriter c;
    private int d = -1;
    private long e = -1;

    final class ImaAdPcmOutputWriter implements OutputWriter {
        private static final int[] a = {-1, -1, -1, -1, 2, 4, 6, 8, -1, -1, -1, -1, 2, 4, 6, 8};
        private static final int[] b = {7, 8, 9, 10, 11, 12, 13, 14, 16, 17, 19, 21, 23, 25, 28, 31, 34, 37, 41, 45, 50, 55, 60, 66, 73, 80, 88, 97, 107, 118, 130, 143, 157, 173, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SEEK, 209, 230, Telnet.DO, 279, ErrorMessages.ERROR_TWITTER_REQUEST_MENTIONS_FAILED, 337, 371, ErrorMessages.ERROR_NXT_INVALID_SENSOR_PORT, 449, 494, 544, 598, 658, 724, 796, 876, 963, 1060, 1166, 1282, 1411, 1552, 1707, 1878, 2066, 2272, 2499, 2749, 3024, 3327, 3660, 4026, 4428, 4871, 5358, 5894, 6484, 7132, 7845, 8630, 9493, 10442, 11487, 12635, 13899, 15289, 16818, 18500, 20350, 22385, 24623, 27086, 29794, 32767};
        private final ExtractorOutput c;
        private final TrackOutput d;
        private final WavHeader e;
        private final int f;
        private final byte[] g;
        private final ParsableByteArray h;
        private final int i;
        private final Format j;
        private int k;
        private long l;
        private int m;
        private long n;

        public ImaAdPcmOutputWriter(ExtractorOutput extractorOutput, TrackOutput trackOutput, WavHeader wavHeader) {
            this.c = extractorOutput;
            this.d = trackOutput;
            this.e = wavHeader;
            int max = Math.max(1, wavHeader.c / 10);
            this.i = max;
            ParsableByteArray parsableByteArray = new ParsableByteArray(wavHeader.f);
            parsableByteArray.e();
            int e2 = parsableByteArray.e();
            this.f = e2;
            int i2 = wavHeader.b;
            int i3 = (((wavHeader.d - (i2 * 4)) << 3) / (wavHeader.e * i2)) + 1;
            if (e2 == i3) {
                int a2 = Util.a(max, e2);
                this.g = new byte[(wavHeader.d * a2)];
                this.h = new ParsableByteArray(a2 * (e2 << 1) * i2);
                int i4 = ((wavHeader.c * wavHeader.d) << 3) / e2;
                Format.Builder builder = new Format.Builder();
                builder.k = "audio/raw";
                builder.f = i4;
                builder.g = i4;
                builder.l = (max << 1) * i2;
                builder.x = wavHeader.b;
                builder.y = wavHeader.c;
                builder.z = 2;
                this.j = builder.a();
                return;
            }
            throw new ParserException(new StringBuilder(56).append("Expected frames per block: ").append(i3).append("; got: ").append(e2).toString());
        }

        private void a(int i2) {
            long b2 = this.l + Util.b(this.n, 1000000, (long) this.e.c);
            int c2 = c(i2);
            this.d.a(b2, 1, c2, this.m - c2, (TrackOutput.CryptoData) null);
            this.n += (long) i2;
            this.m -= c2;
        }

        private int b(int i2) {
            return i2 / (this.e.b * 2);
        }

        private int c(int i2) {
            return (i2 << 1) * this.e.b;
        }

        public final void a(int i2, long j2) {
            this.c.a(new WavSeekMap(this.e, this.f, (long) i2, j2));
            this.d.a(this.j);
        }

        public final void a(long j2) {
            this.k = 0;
            this.l = j2;
            this.m = 0;
            this.n = 0;
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        /* JADX WARNING: Removed duplicated region for block: B:39:0x0045 A[EDGE_INSN: B:39:0x0045->B:10:0x0045 ?: BREAK  , SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:5:0x0025  */
        public final boolean a(com.google.android.exoplayer2.extractor.ExtractorInput r20, long r21) {
            /*
                r19 = this;
                r0 = r19
                r1 = r21
                int r3 = r0.i
                int r4 = r0.m
                int r4 = r0.b(r4)
                int r3 = r3 - r4
                int r4 = r0.f
                int r3 = com.google.android.exoplayer2.util.Util.a((int) r3, (int) r4)
                com.google.android.exoplayer2.extractor.wav.WavHeader r4 = r0.e
                int r4 = r4.d
                int r3 = r3 * r4
                r4 = 0
                r7 = 1
                int r8 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
                if (r8 != 0) goto L_0x0022
            L_0x0020:
                r4 = 1
                goto L_0x0023
            L_0x0022:
                r4 = 0
            L_0x0023:
                if (r4 != 0) goto L_0x0045
                int r5 = r0.k
                if (r5 >= r3) goto L_0x0045
                int r5 = r3 - r5
                long r8 = (long) r5
                long r8 = java.lang.Math.min(r8, r1)
                int r5 = (int) r8
                byte[] r8 = r0.g
                int r9 = r0.k
                r10 = r20
                int r5 = r10.a(r8, r9, r5)
                r8 = -1
                if (r5 != r8) goto L_0x003f
                goto L_0x0020
            L_0x003f:
                int r8 = r0.k
                int r8 = r8 + r5
                r0.k = r8
                goto L_0x0023
            L_0x0045:
                int r1 = r0.k
                com.google.android.exoplayer2.extractor.wav.WavHeader r2 = r0.e
                int r2 = r2.d
                int r1 = r1 / r2
                if (r1 <= 0) goto L_0x0151
                byte[] r2 = r0.g
                com.google.android.exoplayer2.util.ParsableByteArray r3 = r0.h
                r5 = 0
            L_0x0053:
                if (r5 >= r1) goto L_0x0119
                r8 = 0
            L_0x0056:
                com.google.android.exoplayer2.extractor.wav.WavHeader r9 = r0.e
                int r9 = r9.b
                if (r8 >= r9) goto L_0x010e
                byte[] r9 = r3.a
                com.google.android.exoplayer2.extractor.wav.WavHeader r10 = r0.e
                int r10 = r10.d
                com.google.android.exoplayer2.extractor.wav.WavHeader r11 = r0.e
                int r11 = r11.b
                int r12 = r5 * r10
                int r13 = r8 << 2
                int r12 = r12 + r13
                int r13 = r11 << 2
                int r13 = r13 + r12
                int r10 = r10 / r11
                int r10 = r10 + -4
                int r14 = r12 + 1
                byte r14 = r2[r14]
                r14 = r14 & 255(0xff, float:3.57E-43)
                int r14 = r14 << 8
                byte r15 = r2[r12]
                r15 = r15 & 255(0xff, float:3.57E-43)
                r14 = r14 | r15
                short r14 = (short) r14
                int r12 = r12 + 2
                byte r12 = r2[r12]
                r12 = r12 & 255(0xff, float:3.57E-43)
                r15 = 88
                int r12 = java.lang.Math.min(r12, r15)
                int[] r16 = b
                r16 = r16[r12]
                int r6 = r0.f
                int r6 = r6 * r5
                int r6 = r6 * r11
                int r6 = r6 + r8
                int r6 = r6 << r7
                byte r15 = (byte) r14
                r9[r6] = r15
                int r15 = r6 + 1
                int r7 = r14 >> 8
                byte r7 = (byte) r7
                r9[r15] = r7
                r18 = r4
                r7 = 0
                r15 = 1
            L_0x00a5:
                int r4 = r10 << 1
                if (r7 >= r4) goto L_0x0103
                int r4 = r7 / 8
                int r15 = r7 / 2
                int r15 = r15 % 4
                int r4 = r4 * r11
                int r4 = r4 << 2
                int r4 = r4 + r13
                int r4 = r4 + r15
                byte r4 = r2[r4]
                r4 = r4 & 255(0xff, float:3.57E-43)
                int r15 = r7 % 2
                if (r15 != 0) goto L_0x00c0
                r4 = r4 & 15
                goto L_0x00c2
            L_0x00c0:
                int r4 = r4 >> 4
            L_0x00c2:
                r15 = r4 & 7
                int r15 = r15 * 2
                r17 = 1
                int r15 = r15 + 1
                int r15 = r15 * r16
                int r15 = r15 >> 3
                r16 = r4 & 8
                if (r16 == 0) goto L_0x00d3
                int r15 = -r15
            L_0x00d3:
                int r14 = r14 + r15
                r15 = -32768(0xffffffffffff8000, float:NaN)
                r16 = r2
                r2 = 32767(0x7fff, float:4.5916E-41)
                int r14 = com.google.android.exoplayer2.util.Util.a((int) r14, (int) r15, (int) r2)
                int r2 = r11 * 2
                int r6 = r6 + r2
                byte r2 = (byte) r14
                r9[r6] = r2
                int r2 = r6 + 1
                int r15 = r14 >> 8
                byte r15 = (byte) r15
                r9[r2] = r15
                int[] r2 = a
                r2 = r2[r4]
                int r12 = r12 + r2
                r2 = 88
                r4 = 0
                int r12 = com.google.android.exoplayer2.util.Util.a((int) r12, (int) r4, (int) r2)
                int[] r4 = b
                r4 = r4[r12]
                int r7 = r7 + 1
                r2 = r16
                r15 = 1
                r16 = r4
                goto L_0x00a5
            L_0x0103:
                r16 = r2
                r17 = 1
                int r8 = r8 + 1
                r4 = r18
                r7 = 1
                goto L_0x0056
            L_0x010e:
                r16 = r2
                r18 = r4
                r17 = 1
                int r5 = r5 + 1
                r7 = 1
                goto L_0x0053
            L_0x0119:
                r18 = r4
                int r2 = r0.f
                int r2 = r2 * r1
                int r2 = r0.c(r2)
                r4 = 0
                r3.d(r4)
                r3.c(r2)
                int r2 = r0.k
                com.google.android.exoplayer2.extractor.wav.WavHeader r3 = r0.e
                int r3 = r3.d
                int r1 = r1 * r3
                int r2 = r2 - r1
                r0.k = r2
                com.google.android.exoplayer2.util.ParsableByteArray r1 = r0.h
                int r1 = r1.c
                com.google.android.exoplayer2.extractor.TrackOutput r2 = r0.d
                com.google.android.exoplayer2.util.ParsableByteArray r3 = r0.h
                r2.b(r3, r1)
                int r2 = r0.m
                int r2 = r2 + r1
                r0.m = r2
                int r1 = r0.b(r2)
                int r2 = r0.i
                if (r1 < r2) goto L_0x0153
                r0.a((int) r2)
                goto L_0x0153
            L_0x0151:
                r18 = r4
            L_0x0153:
                if (r18 == 0) goto L_0x0160
                int r1 = r0.m
                int r1 = r0.b(r1)
                if (r1 <= 0) goto L_0x0160
                r0.a((int) r1)
            L_0x0160:
                return r18
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.wav.WavExtractor.ImaAdPcmOutputWriter.a(com.google.android.exoplayer2.extractor.ExtractorInput, long):boolean");
        }
    }

    interface OutputWriter {
        void a(int i, long j);

        void a(long j);

        boolean a(ExtractorInput extractorInput, long j);
    }

    final class PassthroughOutputWriter implements OutputWriter {
        private final ExtractorOutput a;
        private final TrackOutput b;
        private final WavHeader c;
        private final Format d;
        private final int e;
        private long f;
        private int g;
        private long h;

        public PassthroughOutputWriter(ExtractorOutput extractorOutput, TrackOutput trackOutput, WavHeader wavHeader, String str, int i) {
            this.a = extractorOutput;
            this.b = trackOutput;
            this.c = wavHeader;
            int i2 = (wavHeader.b * wavHeader.e) / 8;
            if (wavHeader.d == i2) {
                int i3 = (wavHeader.c * i2) << 3;
                int max = Math.max(i2, (wavHeader.c * i2) / 10);
                this.e = max;
                Format.Builder builder = new Format.Builder();
                builder.k = str;
                builder.f = i3;
                builder.g = i3;
                builder.l = max;
                builder.x = wavHeader.b;
                builder.y = wavHeader.c;
                builder.z = i;
                this.d = builder.a();
                return;
            }
            throw new ParserException(new StringBuilder(50).append("Expected block size: ").append(i2).append("; got: ").append(wavHeader.d).toString());
        }

        public final void a(int i, long j) {
            this.a.a(new WavSeekMap(this.c, 1, (long) i, j));
            this.b.a(this.d);
        }

        public final void a(long j) {
            this.f = j;
            this.g = 0;
            this.h = 0;
        }

        public final boolean a(ExtractorInput extractorInput, long j) {
            int i;
            int i2;
            long j2 = j;
            while (j2 > 0 && (i = this.g) < (i2 = this.e)) {
                int b2 = this.b.b(extractorInput, (int) Math.min((long) (i2 - i), j2), true);
                if (b2 == -1) {
                    j2 = 0;
                } else {
                    this.g += b2;
                    j2 -= (long) b2;
                }
            }
            int i3 = this.c.d;
            int i4 = this.g / i3;
            if (i4 > 0) {
                int i5 = i4 * i3;
                int i6 = this.g - i5;
                this.b.a(this.f + Util.b(this.h, 1000000, (long) this.c.c), 1, i5, i6, (TrackOutput.CryptoData) null);
                this.h += (long) i4;
                this.g = i6;
            }
            return j2 <= 0;
        }
    }

    static {
        ExtractorsFactory extractorsFactory = WavExtractor$$Lambda$0.b;
    }

    static final /* synthetic */ Extractor[] a() {
        return new Extractor[]{new WavExtractor()};
    }

    /* JADX WARNING: type inference failed for: r8v24, types: [com.google.android.exoplayer2.extractor.wav.WavExtractor$ImaAdPcmOutputWriter] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int a(com.google.android.exoplayer2.extractor.ExtractorInput r7, com.google.android.exoplayer2.extractor.PositionHolder r8) {
        /*
            r6 = this;
            com.google.android.exoplayer2.extractor.TrackOutput r8 = r6.b
            com.google.android.exoplayer2.util.Assertions.a((java.lang.Object) r8)
            com.google.android.exoplayer2.extractor.ExtractorOutput r8 = r6.a
            com.google.android.exoplayer2.util.Util.a((java.lang.Object) r8)
            com.google.android.exoplayer2.extractor.wav.WavExtractor$OutputWriter r8 = r6.c
            if (r8 != 0) goto L_0x0088
            com.google.android.exoplayer2.extractor.wav.WavHeader r3 = com.google.android.exoplayer2.extractor.wav.WavHeaderReader.a(r7)
            if (r3 == 0) goto L_0x0080
            int r8 = r3.a
            r0 = 17
            if (r8 != r0) goto L_0x0026
            com.google.android.exoplayer2.extractor.wav.WavExtractor$ImaAdPcmOutputWriter r8 = new com.google.android.exoplayer2.extractor.wav.WavExtractor$ImaAdPcmOutputWriter
            com.google.android.exoplayer2.extractor.ExtractorOutput r0 = r6.a
            com.google.android.exoplayer2.extractor.TrackOutput r1 = r6.b
            r8.<init>(r0, r1, r3)
        L_0x0023:
            r6.c = r8
            goto L_0x0088
        L_0x0026:
            int r8 = r3.a
            r0 = 6
            if (r8 != r0) goto L_0x0039
            com.google.android.exoplayer2.extractor.wav.WavExtractor$PassthroughOutputWriter r8 = new com.google.android.exoplayer2.extractor.wav.WavExtractor$PassthroughOutputWriter
            com.google.android.exoplayer2.extractor.ExtractorOutput r1 = r6.a
            com.google.android.exoplayer2.extractor.TrackOutput r2 = r6.b
            java.lang.String r4 = "audio/g711-alaw"
            r5 = -1
            r0 = r8
            r0.<init>(r1, r2, r3, r4, r5)
            goto L_0x0023
        L_0x0039:
            int r8 = r3.a
            r0 = 7
            if (r8 != r0) goto L_0x004c
            com.google.android.exoplayer2.extractor.wav.WavExtractor$PassthroughOutputWriter r8 = new com.google.android.exoplayer2.extractor.wav.WavExtractor$PassthroughOutputWriter
            com.google.android.exoplayer2.extractor.ExtractorOutput r1 = r6.a
            com.google.android.exoplayer2.extractor.TrackOutput r2 = r6.b
            java.lang.String r4 = "audio/g711-mlaw"
            r5 = -1
            r0 = r8
            r0.<init>(r1, r2, r3, r4, r5)
            goto L_0x0023
        L_0x004c:
            int r8 = r3.a
            int r0 = r3.e
            int r5 = com.google.android.exoplayer2.audio.WavUtil.a(r8, r0)
            if (r5 == 0) goto L_0x0063
            com.google.android.exoplayer2.extractor.wav.WavExtractor$PassthroughOutputWriter r8 = new com.google.android.exoplayer2.extractor.wav.WavExtractor$PassthroughOutputWriter
            com.google.android.exoplayer2.extractor.ExtractorOutput r1 = r6.a
            com.google.android.exoplayer2.extractor.TrackOutput r2 = r6.b
            java.lang.String r4 = "audio/raw"
            r0 = r8
            r0.<init>(r1, r2, r3, r4, r5)
            goto L_0x0023
        L_0x0063:
            com.google.android.exoplayer2.ParserException r7 = new com.google.android.exoplayer2.ParserException
            int r8 = r3.a
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = 40
            r0.<init>(r1)
            java.lang.String r1 = "Unsupported WAV format type: "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r8 = r0.append(r8)
            java.lang.String r8 = r8.toString()
            r7.<init>((java.lang.String) r8)
            throw r7
        L_0x0080:
            com.google.android.exoplayer2.ParserException r7 = new com.google.android.exoplayer2.ParserException
            java.lang.String r8 = "Unsupported or unrecognized wav header."
            r7.<init>((java.lang.String) r8)
            throw r7
        L_0x0088:
            int r8 = r6.d
            r0 = -1
            if (r8 != r0) goto L_0x00ad
            android.util.Pair r8 = com.google.android.exoplayer2.extractor.wav.WavHeaderReader.b(r7)
            java.lang.Object r1 = r8.first
            java.lang.Long r1 = (java.lang.Long) r1
            int r1 = r1.intValue()
            r6.d = r1
            java.lang.Object r8 = r8.second
            java.lang.Long r8 = (java.lang.Long) r8
            long r1 = r8.longValue()
            r6.e = r1
            com.google.android.exoplayer2.extractor.wav.WavExtractor$OutputWriter r8 = r6.c
            int r3 = r6.d
            r8.a((int) r3, (long) r1)
            goto L_0x00bc
        L_0x00ad:
            long r1 = r7.c()
            r3 = 0
            int r8 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r8 != 0) goto L_0x00bc
            int r8 = r6.d
            r7.b(r8)
        L_0x00bc:
            long r1 = r6.e
            r3 = -1
            r8 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x00c7
            r1 = 1
            goto L_0x00c8
        L_0x00c7:
            r1 = 0
        L_0x00c8:
            com.google.android.exoplayer2.util.Assertions.b((boolean) r1)
            long r1 = r6.e
            long r3 = r7.c()
            long r1 = r1 - r3
            com.google.android.exoplayer2.extractor.wav.WavExtractor$OutputWriter r3 = r6.c
            boolean r7 = r3.a((com.google.android.exoplayer2.extractor.ExtractorInput) r7, (long) r1)
            if (r7 == 0) goto L_0x00db
            return r0
        L_0x00db:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.wav.WavExtractor.a(com.google.android.exoplayer2.extractor.ExtractorInput, com.google.android.exoplayer2.extractor.PositionHolder):int");
    }

    public final void a(long j, long j2) {
        OutputWriter outputWriter = this.c;
        if (outputWriter != null) {
            outputWriter.a(j2);
        }
    }

    public final void a(ExtractorOutput extractorOutput) {
        this.a = extractorOutput;
        this.b = extractorOutput.a(0, 1);
        extractorOutput.c_();
    }

    public final boolean a(ExtractorInput extractorInput) {
        return WavHeaderReader.a(extractorInput) != null;
    }
}
