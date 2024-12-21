package com.google.android.exoplayer2.extractor.mp3;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.audio.MpegAudioUtil;
import com.google.android.exoplayer2.extractor.DummyTrackOutput;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.GaplessInfoHolder;
import com.google.android.exoplayer2.extractor.Id3Peeker;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.id3.Id3Decoder;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;

public final class Mp3Extractor implements Extractor {
    private static final Id3Decoder.FramePredicate b = Mp3Extractor$$Lambda$1.a;
    public boolean a;
    private final int c;
    private final long d;
    private final ParsableByteArray e;
    private final MpegAudioUtil.Header f;
    private final GaplessInfoHolder g;
    private final Id3Peeker h;
    private final TrackOutput i;
    private ExtractorOutput j;
    private TrackOutput k;
    private TrackOutput l;
    private int m;
    private Metadata n;
    private long o;
    private long p;
    private long q;
    private int r;
    private Seeker s;
    private boolean t;
    private long u;

    static {
        ExtractorsFactory extractorsFactory = Mp3Extractor$$Lambda$0.b;
    }

    public Mp3Extractor() {
        this((byte) 0);
    }

    public Mp3Extractor(byte b2) {
        this(-9223372036854775807L);
    }

    public Mp3Extractor(long j2) {
        this.c = 0;
        this.d = j2;
        this.e = new ParsableByteArray(10);
        this.f = new MpegAudioUtil.Header();
        this.g = new GaplessInfoHolder();
        this.o = -9223372036854775807L;
        this.h = new Id3Peeker();
        DummyTrackOutput dummyTrackOutput = new DummyTrackOutput();
        this.i = dummyTrackOutput;
        this.l = dummyTrackOutput;
    }

    private long a(long j2) {
        return this.o + ((j2 * 1000000) / ((long) this.f.d));
    }

    static final /* synthetic */ boolean a(int i2, int i3, int i4, int i5, int i6) {
        if (i3 == 67 && i4 == 79 && i5 == 77 && (i6 == 77 || i2 == 2)) {
            return true;
        }
        if (i3 == 77 && i4 == 76 && i5 == 76) {
            return i6 == 84 || i2 == 2;
        }
        return false;
    }

    private static boolean a(int i2, long j2) {
        return ((long) (i2 & -128000)) == (j2 & -128000);
    }

    private boolean a(ExtractorInput extractorInput, boolean z) {
        int i2;
        int i3;
        int a2;
        int i4 = z ? 32768 : 131072;
        extractorInput.a();
        if (extractorInput.c() == 0) {
            Metadata a3 = this.h.a(extractorInput, (Id3Decoder.FramePredicate) null);
            this.n = a3;
            if (a3 != null) {
                this.g.a(a3);
            }
            i2 = (int) extractorInput.b();
            if (!z) {
                extractorInput.b(i2);
            }
            i3 = 0;
        } else {
            i3 = 0;
            i2 = 0;
        }
        int i5 = 0;
        int i6 = 0;
        while (true) {
            if (!c(extractorInput)) {
                this.e.d(0);
                int j2 = this.e.j();
                if ((i3 == 0 || a(j2, (long) i3)) && (a2 = MpegAudioUtil.a(j2)) != -1) {
                    i5++;
                    if (i5 != 1) {
                        if (i5 == 4) {
                            break;
                        }
                    } else {
                        this.f.a(j2);
                        i3 = j2;
                    }
                    extractorInput.c(a2 - 4);
                } else {
                    int i7 = i6 + 1;
                    if (i6 != i4) {
                        if (z) {
                            extractorInput.a();
                            extractorInput.c(i2 + i7);
                        } else {
                            extractorInput.b(1);
                        }
                        i6 = i7;
                        i3 = 0;
                        i5 = 0;
                    } else if (z) {
                        return false;
                    } else {
                        throw new ParserException("Searched too many bytes.");
                    }
                }
            } else if (i5 <= 0) {
                throw new EOFException();
            }
        }
        if (z) {
            extractorInput.b(i2 + i6);
        } else {
            extractorInput.a();
        }
        this.m = i3;
        return true;
    }

    static final /* synthetic */ Extractor[] a() {
        return new Extractor[]{new Mp3Extractor()};
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x007e A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0106  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0153  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0159  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int b(com.google.android.exoplayer2.extractor.ExtractorInput r20) {
        /*
            r19 = this;
            r1 = r19
            r0 = r20
            int r2 = r1.m
            r3 = -1
            r4 = 0
            if (r2 != 0) goto L_0x0010
            r1.a((com.google.android.exoplayer2.extractor.ExtractorInput) r0, (boolean) r4)     // Catch:{ EOFException -> 0x000e }
            goto L_0x0010
        L_0x000e:
            r0 = move-exception
            return r3
        L_0x0010:
            com.google.android.exoplayer2.extractor.mp3.Seeker r2 = r1.s
            r5 = 0
            r9 = 1
            if (r2 != 0) goto L_0x01b0
            com.google.android.exoplayer2.util.ParsableByteArray r15 = new com.google.android.exoplayer2.util.ParsableByteArray
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r2 = r1.f
            int r2 = r2.c
            r15.<init>((int) r2)
            byte[] r2 = r15.a
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r10 = r1.f
            int r10 = r10.c
            r0.d(r2, r4, r10)
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r2 = r1.f
            int r2 = r2.a
            r2 = r2 & r9
            r10 = 36
            r11 = 21
            if (r2 == 0) goto L_0x003d
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r2 = r1.f
            int r2 = r2.e
            if (r2 == r9) goto L_0x0043
            r2 = 36
            goto L_0x004a
        L_0x003d:
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r2 = r1.f
            int r2 = r2.e
            if (r2 == r9) goto L_0x0046
        L_0x0043:
            r2 = 21
            goto L_0x004a
        L_0x0046:
            r11 = 13
            r2 = 13
        L_0x004a:
            int r11 = r15.c
            int r12 = r2 + 4
            r13 = 1483304551(0x58696e67, float:1.02664153E15)
            r14 = 1231971951(0x496e666f, float:976486.94)
            r9 = 1447187017(0x56425249, float:5.3414667E13)
            if (r11 < r12) goto L_0x0066
            r15.d(r2)
            int r11 = r15.j()
            if (r11 == r13) goto L_0x0064
            if (r11 != r14) goto L_0x0066
        L_0x0064:
            r12 = r11
            goto L_0x007a
        L_0x0066:
            int r11 = r15.c
            r12 = 40
            if (r11 < r12) goto L_0x0079
            r15.d(r10)
            int r10 = r15.j()
            if (r10 != r9) goto L_0x0079
            r12 = 1447187017(0x56425249, float:5.3414667E13)
            goto L_0x007a
        L_0x0079:
            r12 = 0
        L_0x007a:
            r16 = 0
            if (r12 == r13) goto L_0x009f
            if (r12 != r14) goto L_0x0081
            goto L_0x009f
        L_0x0081:
            if (r12 != r9) goto L_0x0099
            long r10 = r20.d()
            long r12 = r20.c()
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r14 = r1.f
            com.google.android.exoplayer2.extractor.mp3.VbriSeeker r2 = com.google.android.exoplayer2.extractor.mp3.VbriSeeker.a(r10, r12, r14, r15)
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r9 = r1.f
            int r9 = r9.c
            r0.b(r9)
            goto L_0x00fe
        L_0x0099:
            r20.a()
            r2 = r16
            goto L_0x00fe
        L_0x009f:
            long r10 = r20.d()
            long r17 = r20.c()
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r9 = r1.f
            r7 = r12
            r12 = r17
            r8 = 1231971951(0x496e666f, float:976486.94)
            r14 = r9
            com.google.android.exoplayer2.extractor.mp3.XingSeeker r9 = com.google.android.exoplayer2.extractor.mp3.XingSeeker.a(r10, r12, r14, r15)
            if (r9 == 0) goto L_0x00e7
            com.google.android.exoplayer2.extractor.GaplessInfoHolder r10 = r1.g
            boolean r10 = r10.a()
            if (r10 != 0) goto L_0x00e7
            r20.a()
            int r2 = r2 + 141
            r0.c(r2)
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r1.e
            byte[] r2 = r2.a
            r10 = 3
            r0.d(r2, r4, r10)
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r1.e
            r2.d(r4)
            com.google.android.exoplayer2.extractor.GaplessInfoHolder r2 = r1.g
            com.google.android.exoplayer2.util.ParsableByteArray r10 = r1.e
            int r10 = r10.g()
            int r11 = r10 >> 12
            r10 = r10 & 4095(0xfff, float:5.738E-42)
            if (r11 > 0) goto L_0x00e3
            if (r10 <= 0) goto L_0x00e7
        L_0x00e3:
            r2.a = r11
            r2.b = r10
        L_0x00e7:
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r2 = r1.f
            int r2 = r2.c
            r0.b(r2)
            if (r9 == 0) goto L_0x00fd
            boolean r2 = r9.a()
            if (r2 != 0) goto L_0x00fd
            if (r7 != r8) goto L_0x00fd
            com.google.android.exoplayer2.extractor.mp3.Seeker r2 = r19.d(r20)
            goto L_0x00fe
        L_0x00fd:
            r2 = r9
        L_0x00fe:
            com.google.android.exoplayer2.metadata.Metadata r7 = r1.n
            long r8 = r20.c()
            if (r7 == 0) goto L_0x014d
            com.google.android.exoplayer2.metadata.Metadata$Entry[] r10 = r7.a
            int r10 = r10.length
            r11 = 0
        L_0x010a:
            if (r11 >= r10) goto L_0x014d
            com.google.android.exoplayer2.metadata.Metadata$Entry[] r12 = r7.a
            r12 = r12[r11]
            boolean r13 = r12 instanceof com.google.android.exoplayer2.metadata.id3.MlltFrame
            if (r13 == 0) goto L_0x014a
            com.google.android.exoplayer2.metadata.id3.MlltFrame r12 = (com.google.android.exoplayer2.metadata.id3.MlltFrame) r12
            if (r7 == 0) goto L_0x0140
            com.google.android.exoplayer2.metadata.Metadata$Entry[] r10 = r7.a
            int r10 = r10.length
            r11 = 0
        L_0x011c:
            if (r11 >= r10) goto L_0x0140
            com.google.android.exoplayer2.metadata.Metadata$Entry[] r13 = r7.a
            r13 = r13[r11]
            boolean r14 = r13 instanceof com.google.android.exoplayer2.metadata.id3.TextInformationFrame
            if (r14 == 0) goto L_0x013d
            com.google.android.exoplayer2.metadata.id3.TextInformationFrame r13 = (com.google.android.exoplayer2.metadata.id3.TextInformationFrame) r13
            java.lang.String r14 = r13.c
            java.lang.String r15 = "TLEN"
            boolean r14 = r14.equals(r15)
            if (r14 == 0) goto L_0x013d
            java.lang.String r7 = r13.a
            long r10 = java.lang.Long.parseLong(r7)
            long r10 = com.google.android.exoplayer2.C.b(r10)
            goto L_0x0145
        L_0x013d:
            int r11 = r11 + 1
            goto L_0x011c
        L_0x0140:
            r10 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L_0x0145:
            com.google.android.exoplayer2.extractor.mp3.MlltSeeker r7 = com.google.android.exoplayer2.extractor.mp3.MlltSeeker.a((long) r8, (com.google.android.exoplayer2.metadata.id3.MlltFrame) r12, (long) r10)
            goto L_0x014f
        L_0x014a:
            int r11 = r11 + 1
            goto L_0x010a
        L_0x014d:
            r7 = r16
        L_0x014f:
            boolean r8 = r1.a
            if (r8 == 0) goto L_0x0159
            com.google.android.exoplayer2.extractor.mp3.Seeker$UnseekableSeeker r2 = new com.google.android.exoplayer2.extractor.mp3.Seeker$UnseekableSeeker
            r2.<init>()
            goto L_0x016e
        L_0x0159:
            if (r7 == 0) goto L_0x015e
            r16 = r7
            goto L_0x0162
        L_0x015e:
            if (r2 == 0) goto L_0x0162
            r16 = r2
        L_0x0162:
            if (r16 == 0) goto L_0x016a
            r16.a()
            r2 = r16
            goto L_0x016e
        L_0x016a:
            com.google.android.exoplayer2.extractor.mp3.Seeker r2 = r19.d(r20)
        L_0x016e:
            r1.s = r2
            com.google.android.exoplayer2.extractor.ExtractorOutput r7 = r1.j
            r7.a(r2)
            com.google.android.exoplayer2.extractor.TrackOutput r2 = r1.l
            com.google.android.exoplayer2.Format$Builder r7 = new com.google.android.exoplayer2.Format$Builder
            r7.<init>()
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r8 = r1.f
            java.lang.String r8 = r8.b
            r7.k = r8
            r8 = 4096(0x1000, float:5.74E-42)
            r7.l = r8
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r8 = r1.f
            int r8 = r8.e
            r7.x = r8
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r8 = r1.f
            int r8 = r8.d
            r7.y = r8
            com.google.android.exoplayer2.extractor.GaplessInfoHolder r8 = r1.g
            int r8 = r8.a
            r7.A = r8
            com.google.android.exoplayer2.extractor.GaplessInfoHolder r8 = r1.g
            int r8 = r8.b
            r7.B = r8
            com.google.android.exoplayer2.metadata.Metadata r8 = r1.n
            r7.i = r8
            com.google.android.exoplayer2.Format r7 = r7.a()
            r2.a(r7)
            long r7 = r20.c()
            r1.q = r7
            goto L_0x01c5
        L_0x01b0:
            long r7 = r1.q
            int r2 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r2 == 0) goto L_0x01c5
            long r7 = r20.c()
            long r9 = r1.q
            int r2 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r2 >= 0) goto L_0x01c5
            long r9 = r9 - r7
            int r2 = (int) r9
            r0.b(r2)
        L_0x01c5:
            int r2 = r1.r
            if (r2 != 0) goto L_0x026d
            r20.a()
            boolean r2 = r19.c(r20)
            if (r2 == 0) goto L_0x01d3
            return r3
        L_0x01d3:
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r1.e
            r2.d(r4)
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r1.e
            int r2 = r2.j()
            int r7 = r1.m
            long r7 = (long) r7
            boolean r7 = a((int) r2, (long) r7)
            if (r7 == 0) goto L_0x0266
            int r7 = com.google.android.exoplayer2.audio.MpegAudioUtil.a(r2)
            if (r7 != r3) goto L_0x01ef
            goto L_0x0266
        L_0x01ef:
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r7 = r1.f
            r7.a(r2)
            long r7 = r1.o
            r9 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r2 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r2 != 0) goto L_0x021f
            com.google.android.exoplayer2.extractor.mp3.Seeker r2 = r1.s
            long r7 = r20.c()
            long r7 = r2.c(r7)
            r1.o = r7
            long r7 = r1.d
            int r2 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r2 == 0) goto L_0x021f
            com.google.android.exoplayer2.extractor.mp3.Seeker r2 = r1.s
            long r5 = r2.c(r5)
            long r7 = r1.o
            long r9 = r1.d
            long r9 = r9 - r5
            long r7 = r7 + r9
            r1.o = r7
        L_0x021f:
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r2 = r1.f
            int r2 = r2.c
            r1.r = r2
            com.google.android.exoplayer2.extractor.mp3.Seeker r2 = r1.s
            boolean r5 = r2 instanceof com.google.android.exoplayer2.extractor.mp3.IndexSeeker
            if (r5 == 0) goto L_0x026d
            com.google.android.exoplayer2.extractor.mp3.IndexSeeker r2 = (com.google.android.exoplayer2.extractor.mp3.IndexSeeker) r2
            long r5 = r1.p
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r7 = r1.f
            int r7 = r7.g
            long r7 = (long) r7
            long r5 = r5 + r7
            long r5 = r1.a((long) r5)
            long r7 = r20.c()
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r9 = r1.f
            int r9 = r9.c
            long r9 = (long) r9
            long r7 = r7 + r9
            boolean r9 = r2.b(r5)
            if (r9 != 0) goto L_0x0253
            com.google.android.exoplayer2.util.LongArray r9 = r2.a
            r9.a((long) r5)
            com.google.android.exoplayer2.util.LongArray r5 = r2.b
            r5.a((long) r7)
        L_0x0253:
            boolean r5 = r1.t
            if (r5 == 0) goto L_0x026d
            long r5 = r1.u
            boolean r2 = r2.b(r5)
            if (r2 == 0) goto L_0x026d
            r1.t = r4
            com.google.android.exoplayer2.extractor.TrackOutput r2 = r1.k
            r1.l = r2
            goto L_0x026d
        L_0x0266:
            r2 = 1
            r0.b(r2)
            r1.m = r4
            return r4
        L_0x026d:
            r2 = 1
            com.google.android.exoplayer2.extractor.TrackOutput r5 = r1.l
            int r6 = r1.r
            int r0 = r5.b(r0, r6, r2)
            if (r0 != r3) goto L_0x0279
            return r3
        L_0x0279:
            int r2 = r1.r
            int r2 = r2 - r0
            r1.r = r2
            if (r2 <= 0) goto L_0x0281
            return r4
        L_0x0281:
            com.google.android.exoplayer2.extractor.TrackOutput r5 = r1.l
            long r2 = r1.p
            long r6 = r1.a((long) r2)
            r8 = 1
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r0 = r1.f
            int r9 = r0.c
            r10 = 0
            r11 = 0
            r5.a(r6, r8, r9, r10, r11)
            long r2 = r1.p
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r0 = r1.f
            int r0 = r0.g
            long r5 = (long) r0
            long r2 = r2 + r5
            r1.p = r2
            r1.r = r4
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp3.Mp3Extractor.b(com.google.android.exoplayer2.extractor.ExtractorInput):int");
    }

    private boolean c(ExtractorInput extractorInput) {
        Seeker seeker = this.s;
        if (seeker != null) {
            long c2 = seeker.c();
            if (c2 != -1 && extractorInput.b() > c2 - 4) {
                return true;
            }
        }
        try {
            return !extractorInput.b(this.e.a, 0, 4, true);
        } catch (EOFException e2) {
            return true;
        }
    }

    private Seeker d(ExtractorInput extractorInput) {
        extractorInput.d(this.e.a, 0, 4);
        this.e.d(0);
        this.f.a(this.e.j());
        return new ConstantBitrateSeeker(extractorInput.d(), extractorInput.c(), this.f);
    }

    public final int a(ExtractorInput extractorInput, PositionHolder positionHolder) {
        Assertions.a((Object) this.k);
        Util.a((Object) this.j);
        int b2 = b(extractorInput);
        if (b2 == -1 && (this.s instanceof IndexSeeker)) {
            long a2 = a(this.p);
            if (this.s.b() != a2) {
                ((IndexSeeker) this.s).c = a2;
                this.j.a(this.s);
            }
        }
        return b2;
    }

    public final void a(long j2, long j3) {
        this.m = 0;
        this.o = -9223372036854775807L;
        this.p = 0;
        this.r = 0;
        this.u = j3;
        Seeker seeker = this.s;
        if ((seeker instanceof IndexSeeker) && !((IndexSeeker) seeker).b(j3)) {
            this.t = true;
            this.l = this.i;
        }
    }

    public final void a(ExtractorOutput extractorOutput) {
        this.j = extractorOutput;
        TrackOutput a2 = extractorOutput.a(0, 1);
        this.k = a2;
        this.l = a2;
        this.j.c_();
    }

    public final boolean a(ExtractorInput extractorInput) {
        return a(extractorInput, true);
    }
}
