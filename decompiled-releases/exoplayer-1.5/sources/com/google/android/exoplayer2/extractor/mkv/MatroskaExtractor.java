package com.google.android.exoplayer2.extractor.mkv;

import android.util.Pair;
import android.util.SparseArray;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.audio.Ac3Util;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.LongArray;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.appinventor.components.common.ComponentConstants;
import com.google.appinventor.components.common.YaVersion;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import gnu.expr.Declaration;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import kawa.Telnet;

public class MatroskaExtractor implements Extractor {
    private static final byte[] J = {Ev3Constants.Opcode.MOVE8_16, 10, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE32_32, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE32_32, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.RL8, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE8_8, 32, Ev3Constants.Opcode.RL16, Ev3Constants.Opcode.RL16, Ev3Constants.Opcode.MOVEF_32, 32, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE32_32, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE32_32, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.RL8, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE8_8, 10};
    private static final byte[] K = {Ev3Constants.Opcode.CP_LT8, Ev3Constants.Opcode.JR_GT16, Ev3Constants.Opcode.PORT_CNV_OUTPUT, Ev3Constants.Opcode.JR_EQ8, Ev3Constants.Opcode.JR_EQF, Ev3Constants.Opcode.JR_LTF, Ev3Constants.Opcode.JR_LTEQ16, Ev3Constants.Opcode.JR_LT16, Ev3Constants.Opcode.MOVE32_32, 32, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE32_32, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE32_32, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE32_32, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.RL8, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE32_32, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE32_32, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE32_32, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.RL8};
    /* access modifiers changed from: private */
    public static final UUID L = new UUID(72057594037932032L, -9223371306706625679L);
    static final byte[] a = Util.c("Format: Start, End, ReadOrder, Layer, Style, Name, MarginL, MarginR, MarginV, Effect, Text");
    static final Map b;
    int A;
    int B;
    int[] C;
    int D;
    int E;
    int F;
    int G;
    boolean H;
    ExtractorOutput I;
    private final EbmlReader M;
    private final ParsableByteArray N;
    private final ParsableByteArray O;
    private final ParsableByteArray P;
    private final ParsableByteArray Q;
    private final ParsableByteArray R;
    private final ParsableByteArray S;
    private final ParsableByteArray T;
    private ByteBuffer U;
    private long V;
    private boolean W;
    private int X;
    private int Y;
    private int Z;
    private boolean aa;
    private boolean ab;
    private boolean ac;
    private int ad;
    private byte ae;
    private boolean af;
    final VarintReader c;
    final SparseArray d;
    final boolean e;
    final ParsableByteArray f;
    final ParsableByteArray g;
    final ParsableByteArray h;
    long i;
    long j;
    long k;
    long l;
    long m;
    Track n;
    boolean o;
    int p;
    long q;
    boolean r;
    long s;
    long t;
    LongArray u;
    LongArray v;
    boolean w;
    int x;
    long y;
    long z;

    final class InnerEbmlProcessor implements EbmlProcessor {
        private InnerEbmlProcessor() {
        }

        /* synthetic */ InnerEbmlProcessor(MatroskaExtractor matroskaExtractor, byte b) {
            this();
        }

        public final int a(int i) {
            return MatroskaExtractor.a(i);
        }

        public final void a(int i, double d) {
            MatroskaExtractor matroskaExtractor = MatroskaExtractor.this;
            switch (i) {
                case 181:
                    matroskaExtractor.e(i).P = (int) d;
                    return;
                case 17545:
                    matroskaExtractor.l = (long) d;
                    return;
                case 21969:
                    matroskaExtractor.e(i).C = (float) d;
                    return;
                case 21970:
                    matroskaExtractor.e(i).D = (float) d;
                    return;
                case 21971:
                    matroskaExtractor.e(i).E = (float) d;
                    return;
                case 21972:
                    matroskaExtractor.e(i).F = (float) d;
                    return;
                case 21973:
                    matroskaExtractor.e(i).G = (float) d;
                    return;
                case 21974:
                    matroskaExtractor.e(i).H = (float) d;
                    return;
                case 21975:
                    matroskaExtractor.e(i).I = (float) d;
                    return;
                case 21976:
                    matroskaExtractor.e(i).J = (float) d;
                    return;
                case 21977:
                    matroskaExtractor.e(i).K = (float) d;
                    return;
                case 21978:
                    matroskaExtractor.e(i).L = (float) d;
                    return;
                case 30323:
                    matroskaExtractor.e(i).r = (float) d;
                    return;
                case 30324:
                    matroskaExtractor.e(i).s = (float) d;
                    return;
                case 30325:
                    matroskaExtractor.e(i).t = (float) d;
                    return;
                default:
                    return;
            }
        }

        public final void a(int i, int i2, ExtractorInput extractorInput) {
            int i3;
            long j;
            byte b;
            int i4 = i;
            int i5 = i2;
            ExtractorInput extractorInput2 = extractorInput;
            MatroskaExtractor matroskaExtractor = MatroskaExtractor.this;
            int i6 = 4;
            int i7 = 0;
            int i8 = 1;
            switch (i4) {
                case 161:
                case 163:
                    int i9 = 8;
                    if (matroskaExtractor.x == 0) {
                        matroskaExtractor.D = (int) matroskaExtractor.c.a(extractorInput2, false, true, 8);
                        matroskaExtractor.E = matroskaExtractor.c.a;
                        matroskaExtractor.z = -9223372036854775807L;
                        matroskaExtractor.x = 1;
                        matroskaExtractor.f.a(0);
                    }
                    Track track = (Track) matroskaExtractor.d.get(matroskaExtractor.D);
                    if (track == null) {
                        extractorInput2.b(i5 - matroskaExtractor.E);
                        matroskaExtractor.x = 0;
                        return;
                    }
                    Assertions.b((Object) track.W);
                    if (matroskaExtractor.x == 1) {
                        matroskaExtractor.a(extractorInput2, 3);
                        int i10 = (matroskaExtractor.f.a[2] & 6) >> 1;
                        if (i10 == 0) {
                            matroskaExtractor.B = 1;
                            matroskaExtractor.C = MatroskaExtractor.a(matroskaExtractor.C, 1);
                            matroskaExtractor.C[0] = (i5 - matroskaExtractor.E) - 3;
                        } else {
                            matroskaExtractor.a(extractorInput2, 4);
                            matroskaExtractor.B = (matroskaExtractor.f.a[3] & Ev3Constants.Opcode.TST) + 1;
                            matroskaExtractor.C = MatroskaExtractor.a(matroskaExtractor.C, matroskaExtractor.B);
                            if (i10 == 2) {
                                Arrays.fill(matroskaExtractor.C, 0, matroskaExtractor.B, ((i5 - matroskaExtractor.E) - 4) / matroskaExtractor.B);
                            } else if (i10 == 1) {
                                int i11 = 0;
                                for (int i12 = 0; i12 < matroskaExtractor.B - 1; i12++) {
                                    matroskaExtractor.C[i12] = 0;
                                    do {
                                        i6++;
                                        matroskaExtractor.a(extractorInput2, i6);
                                        b = matroskaExtractor.f.a[i6 - 1] & Ev3Constants.Opcode.TST;
                                        int[] iArr = matroskaExtractor.C;
                                        iArr[i12] = iArr[i12] + b;
                                    } while (b == 255);
                                    i11 += matroskaExtractor.C[i12];
                                }
                                matroskaExtractor.C[matroskaExtractor.B - 1] = ((i5 - matroskaExtractor.E) - i6) - i11;
                            } else if (i10 == 3) {
                                int i13 = 0;
                                int i14 = 0;
                                while (i13 < matroskaExtractor.B - i8) {
                                    matroskaExtractor.C[i13] = i7;
                                    i6++;
                                    matroskaExtractor.a(extractorInput2, i6);
                                    int i15 = i6 - 1;
                                    if (matroskaExtractor.f.a[i15] != 0) {
                                        int i16 = 0;
                                        while (true) {
                                            if (i16 < i9) {
                                                int i17 = i8 << (7 - i16);
                                                if ((matroskaExtractor.f.a[i15] & i17) != 0) {
                                                    i6 += i16;
                                                    matroskaExtractor.a(extractorInput2, i6);
                                                    j = (long) (matroskaExtractor.f.a[i15] & Ev3Constants.Opcode.TST & (i17 ^ -1));
                                                    int i18 = i15 + 1;
                                                    while (i18 < i6) {
                                                        j = (j << i9) | ((long) (matroskaExtractor.f.a[i18] & Ev3Constants.Opcode.TST));
                                                        i18++;
                                                        i14 = i14;
                                                        i9 = 8;
                                                    }
                                                    i3 = i14;
                                                    if (i13 > 0) {
                                                        j -= (1 << ((i16 * 7) + 6)) - 1;
                                                    }
                                                } else {
                                                    int i19 = i14;
                                                    i16++;
                                                    i9 = 8;
                                                    i8 = 1;
                                                }
                                            } else {
                                                i3 = i14;
                                                j = 0;
                                            }
                                        }
                                        if (j < -2147483648L || j > 2147483647L) {
                                            throw new ParserException("EBML lacing sample size out of range.");
                                        }
                                        int i20 = (int) j;
                                        int[] iArr2 = matroskaExtractor.C;
                                        if (i13 != 0) {
                                            i20 += matroskaExtractor.C[i13 - 1];
                                        }
                                        iArr2[i13] = i20;
                                        i14 = i3 + matroskaExtractor.C[i13];
                                        i13++;
                                        i9 = 8;
                                        i7 = 0;
                                        i8 = 1;
                                    } else {
                                        throw new ParserException("No valid varint length mask found");
                                    }
                                }
                                matroskaExtractor.C[matroskaExtractor.B - 1] = ((i5 - matroskaExtractor.E) - i6) - i14;
                            } else {
                                throw new ParserException(new StringBuilder(36).append("Unexpected lacing value: ").append(i10).toString());
                            }
                        }
                        matroskaExtractor.y = matroskaExtractor.t + matroskaExtractor.a((long) ((matroskaExtractor.f.a[0] << 8) | (matroskaExtractor.f.a[1] & Ev3Constants.Opcode.TST)));
                        matroskaExtractor.F = (track.d == 2 || (i4 == 163 && (matroskaExtractor.f.a[2] & 128) == 128)) ? 1 : 0;
                        matroskaExtractor.x = 2;
                        matroskaExtractor.A = 0;
                    }
                    if (i4 == 163) {
                        while (matroskaExtractor.A < matroskaExtractor.B) {
                            matroskaExtractor.a(track, matroskaExtractor.y + ((long) ((matroskaExtractor.A * track.e) / 1000)), matroskaExtractor.F, matroskaExtractor.a(extractorInput2, track, matroskaExtractor.C[matroskaExtractor.A]), 0);
                            matroskaExtractor.A++;
                        }
                        matroskaExtractor.x = 0;
                        return;
                    }
                    while (matroskaExtractor.A < matroskaExtractor.B) {
                        matroskaExtractor.C[matroskaExtractor.A] = matroskaExtractor.a(extractorInput2, track, matroskaExtractor.C[matroskaExtractor.A]);
                        matroskaExtractor.A++;
                    }
                    return;
                case 165:
                    if (matroskaExtractor.x == 2) {
                        Track track2 = (Track) matroskaExtractor.d.get(matroskaExtractor.D);
                        if (matroskaExtractor.G != 4 || !"V_VP9".equals(track2.b)) {
                            extractorInput2.b(i5);
                            return;
                        }
                        matroskaExtractor.h.a(i5);
                        extractorInput2.b(matroskaExtractor.h.a, 0, i5);
                        return;
                    }
                    return;
                case 16877:
                    Track e = matroskaExtractor.e(i4);
                    if (e.Y == 1685485123 || e.Y == 1685480259) {
                        e.M = new byte[i5];
                        extractorInput2.b(e.M, 0, i5);
                        return;
                    }
                    extractorInput2.b(i5);
                    return;
                case 16981:
                    matroskaExtractor.c(i4);
                    matroskaExtractor.n.h = new byte[i5];
                    extractorInput2.b(matroskaExtractor.n.h, 0, i5);
                    return;
                case 18402:
                    byte[] bArr = new byte[i5];
                    extractorInput2.b(bArr, 0, i5);
                    matroskaExtractor.e(i4).i = new TrackOutput.CryptoData(1, bArr, 0, 0);
                    return;
                case 21419:
                    Arrays.fill(matroskaExtractor.g.a, (byte) 0);
                    extractorInput2.b(matroskaExtractor.g.a, 4 - i5, i5);
                    matroskaExtractor.g.d(0);
                    matroskaExtractor.p = (int) matroskaExtractor.g.h();
                    return;
                case 25506:
                    matroskaExtractor.c(i4);
                    matroskaExtractor.n.j = new byte[i5];
                    extractorInput2.b(matroskaExtractor.n.j, 0, i5);
                    return;
                case 30322:
                    matroskaExtractor.c(i4);
                    matroskaExtractor.n.u = new byte[i5];
                    extractorInput2.b(matroskaExtractor.n.u, 0, i5);
                    return;
                default:
                    throw new ParserException(new StringBuilder(26).append("Unexpected id: ").append(i4).toString());
            }
        }

        public final void a(int i, long j) {
            MatroskaExtractor matroskaExtractor = MatroskaExtractor.this;
            boolean z = false;
            switch (i) {
                case 131:
                    matroskaExtractor.e(i).d = (int) j;
                    return;
                case 136:
                    Track e = matroskaExtractor.e(i);
                    if (j == 1) {
                        z = true;
                    }
                    e.U = z;
                    return;
                case 155:
                    matroskaExtractor.z = matroskaExtractor.a(j);
                    return;
                case 159:
                    matroskaExtractor.e(i).N = (int) j;
                    return;
                case 176:
                    matroskaExtractor.e(i).l = (int) j;
                    return;
                case 179:
                    matroskaExtractor.d(i);
                    matroskaExtractor.u.a(matroskaExtractor.a(j));
                    return;
                case 186:
                    matroskaExtractor.e(i).m = (int) j;
                    return;
                case 215:
                    matroskaExtractor.e(i).c = (int) j;
                    return;
                case YaVersion.YOUNG_ANDROID_VERSION /*231*/:
                    matroskaExtractor.t = matroskaExtractor.a(j);
                    return;
                case 238:
                    matroskaExtractor.G = (int) j;
                    return;
                case LispEscapeFormat.ESCAPE_NORMAL:
                    if (!matroskaExtractor.w) {
                        matroskaExtractor.d(i);
                        matroskaExtractor.v.a(j);
                        matroskaExtractor.w = true;
                        return;
                    }
                    return;
                case Telnet.WILL:
                    matroskaExtractor.H = true;
                    return;
                case 16871:
                    int unused = matroskaExtractor.e(i).Y = (int) j;
                    return;
                case 16980:
                    if (j != 3) {
                        throw new ParserException(new StringBuilder(50).append("ContentCompAlgo ").append(j).append(" not supported").toString());
                    }
                    return;
                case 17029:
                    if (j < 1 || j > 2) {
                        throw new ParserException(new StringBuilder(53).append("DocTypeReadVersion ").append(j).append(" not supported").toString());
                    }
                    return;
                case 17143:
                    if (j != 1) {
                        throw new ParserException(new StringBuilder(50).append("EBMLReadVersion ").append(j).append(" not supported").toString());
                    }
                    return;
                case 18401:
                    if (j != 5) {
                        throw new ParserException(new StringBuilder(49).append("ContentEncAlgo ").append(j).append(" not supported").toString());
                    }
                    return;
                case 18408:
                    if (j != 1) {
                        throw new ParserException(new StringBuilder(56).append("AESSettingsCipherMode ").append(j).append(" not supported").toString());
                    }
                    return;
                case 20529:
                    if (j != 0) {
                        throw new ParserException(new StringBuilder(55).append("ContentEncodingOrder ").append(j).append(" not supported").toString());
                    }
                    return;
                case 20530:
                    if (j != 1) {
                        throw new ParserException(new StringBuilder(55).append("ContentEncodingScope ").append(j).append(" not supported").toString());
                    }
                    return;
                case 21420:
                    matroskaExtractor.q = j + matroskaExtractor.j;
                    return;
                case 21432:
                    int i2 = (int) j;
                    matroskaExtractor.c(i);
                    switch (i2) {
                        case 0:
                            matroskaExtractor.n.v = 0;
                            return;
                        case 1:
                            matroskaExtractor.n.v = 2;
                            return;
                        case 3:
                            matroskaExtractor.n.v = 1;
                            return;
                        case 15:
                            matroskaExtractor.n.v = 3;
                            return;
                        default:
                            return;
                    }
                case 21680:
                    matroskaExtractor.e(i).n = (int) j;
                    return;
                case 21682:
                    matroskaExtractor.e(i).p = (int) j;
                    return;
                case 21690:
                    matroskaExtractor.e(i).o = (int) j;
                    return;
                case 21930:
                    Track e2 = matroskaExtractor.e(i);
                    if (j == 1) {
                        z = true;
                    }
                    e2.T = z;
                    return;
                case 21945:
                    matroskaExtractor.c(i);
                    switch ((int) j) {
                        case 1:
                            matroskaExtractor.n.z = 2;
                            return;
                        case 2:
                            matroskaExtractor.n.z = 1;
                            return;
                        default:
                            return;
                    }
                case 21946:
                    matroskaExtractor.c(i);
                    switch ((int) j) {
                        case 1:
                        case 6:
                        case 7:
                            matroskaExtractor.n.y = 3;
                            return;
                        case 16:
                            matroskaExtractor.n.y = 6;
                            return;
                        case 18:
                            matroskaExtractor.n.y = 7;
                            return;
                        default:
                            return;
                    }
                case 21947:
                    matroskaExtractor.c(i);
                    matroskaExtractor.n.w = true;
                    switch ((int) j) {
                        case 1:
                            matroskaExtractor.n.x = 1;
                            return;
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                            matroskaExtractor.n.x = 2;
                            return;
                        case 9:
                            matroskaExtractor.n.x = 6;
                            return;
                        default:
                            return;
                    }
                case 21948:
                    matroskaExtractor.e(i).A = (int) j;
                    return;
                case 21949:
                    matroskaExtractor.e(i).B = (int) j;
                    return;
                case 21998:
                    matroskaExtractor.e(i).f = (int) j;
                    return;
                case 22186:
                    matroskaExtractor.e(i).Q = j;
                    return;
                case 22203:
                    matroskaExtractor.e(i).R = j;
                    return;
                case 25188:
                    matroskaExtractor.e(i).O = (int) j;
                    return;
                case 30321:
                    matroskaExtractor.c(i);
                    switch ((int) j) {
                        case 0:
                            matroskaExtractor.n.q = 0;
                            return;
                        case 1:
                            matroskaExtractor.n.q = 1;
                            return;
                        case 2:
                            matroskaExtractor.n.q = 2;
                            return;
                        case 3:
                            matroskaExtractor.n.q = 3;
                            return;
                        default:
                            return;
                    }
                case 2352003:
                    matroskaExtractor.e(i).e = (int) j;
                    return;
                case 2807729:
                    matroskaExtractor.k = j;
                    return;
                default:
                    return;
            }
        }

        public final void a(int i, long j, long j2) {
            MatroskaExtractor matroskaExtractor = MatroskaExtractor.this;
            matroskaExtractor.a();
            switch (i) {
                case ComponentConstants.TEXTBOX_PREFERRED_WIDTH:
                    matroskaExtractor.H = false;
                    return;
                case 174:
                    matroskaExtractor.n = new Track((byte) 0);
                    return;
                case 187:
                    matroskaExtractor.w = false;
                    return;
                case 19899:
                    matroskaExtractor.p = -1;
                    matroskaExtractor.q = -1;
                    return;
                case 20533:
                    matroskaExtractor.e(i).g = true;
                    return;
                case 21968:
                    matroskaExtractor.e(i).w = true;
                    return;
                case 25152:
                    return;
                case 408125543:
                    if (matroskaExtractor.j == -1 || matroskaExtractor.j == j) {
                        matroskaExtractor.j = j;
                        matroskaExtractor.i = j2;
                        return;
                    }
                    throw new ParserException("Multiple Segment elements not supported");
                case 475249515:
                    matroskaExtractor.u = new LongArray();
                    matroskaExtractor.v = new LongArray();
                    return;
                case 524531317:
                    if (matroskaExtractor.o) {
                        return;
                    }
                    if (!matroskaExtractor.e || matroskaExtractor.s == -1) {
                        matroskaExtractor.I.a(new SeekMap.Unseekable(matroskaExtractor.m));
                        matroskaExtractor.o = true;
                        return;
                    }
                    matroskaExtractor.r = true;
                    return;
                default:
                    return;
            }
        }

        public final void a(int i, String str) {
            MatroskaExtractor matroskaExtractor = MatroskaExtractor.this;
            switch (i) {
                case 134:
                    matroskaExtractor.e(i).b = str;
                    return;
                case 17026:
                    if (!"webm".equals(str) && !"matroska".equals(str)) {
                        throw new ParserException(new StringBuilder(String.valueOf(str).length() + 22).append("DocType ").append(str).append(" not supported").toString());
                    }
                    return;
                case 21358:
                    matroskaExtractor.e(i).a = str;
                    return;
                case 2274716:
                    String unused = matroskaExtractor.e(i).V = str;
                    return;
                default:
                    return;
            }
        }

        public final boolean b(int i) {
            return MatroskaExtractor.b(i);
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v20, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v21, resolved type: java.lang.String} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v34, resolved type: java.util.ArrayList} */
        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* JADX WARNING: Code restructure failed: missing block: B:293:0x053e, code lost:
            r4 = r10.append(r6).append(r4).append(". Setting mimeType to ").append(r9).toString();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:294:0x0552, code lost:
            com.google.android.exoplayer2.util.Log.c("MatroskaExtractor", r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:295:0x0557, code lost:
            r9 = "audio/raw";
            r7 = r15;
            r4 = -1;
            r5 = null;
            r6 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:313:0x05d0, code lost:
            r4 = 4096;
            r9 = r6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:319:0x066d, code lost:
            r5 = r4;
            r9 = r6;
            r4 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:322:0x06a4, code lost:
            r9 = r6;
            r7 = -1;
            r6 = r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:323:0x06a7, code lost:
            r4 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:333:0x06c3, code lost:
            r9 = r6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:334:0x06c4, code lost:
            r4 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:335:0x06c5, code lost:
            r5 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:336:0x06c6, code lost:
            r6 = null;
            r7 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:338:0x06ca, code lost:
            if (r1.M == null) goto L_0x06dd;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:339:0x06cc, code lost:
            r10 = com.google.android.exoplayer2.video.DolbyVisionConfig.a(new com.google.android.exoplayer2.util.ParsableByteArray(r1.M));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:340:0x06d7, code lost:
            if (r10 == null) goto L_0x06dd;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:341:0x06d9, code lost:
            r6 = r10.a;
            r9 = "video/dolby-vision";
         */
        /* JADX WARNING: Code restructure failed: missing block: B:342:0x06dd, code lost:
            r10 = r1.U | 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:343:0x06e3, code lost:
            if (r1.T == false) goto L_0x06e7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:344:0x06e5, code lost:
            r13 = 2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:345:0x06e7, code lost:
            r13 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:346:0x06e8, code lost:
            r10 = r10 | r13;
            r13 = new com.google.android.exoplayer2.Format.Builder();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:347:0x06f2, code lost:
            if (com.google.android.exoplayer2.util.MimeTypes.a(r9) == false) goto L_0x0701;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:348:0x06f4, code lost:
            r13.x = r1.N;
            r13.y = r1.P;
            r13.z = r7;
            r14 = 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:350:0x0705, code lost:
            if (com.google.android.exoplayer2.util.MimeTypes.b(r9) == false) goto L_0x08a1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:352:0x0709, code lost:
            if (r1.p != 0) goto L_0x0721;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:354:0x070d, code lost:
            if (r1.n != -1) goto L_0x0712;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:355:0x070f, code lost:
            r7 = r1.l;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:356:0x0712, code lost:
            r7 = r1.n;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:357:0x0714, code lost:
            r1.n = r7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:358:0x0718, code lost:
            if (r1.o != -1) goto L_0x071d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:359:0x071a, code lost:
            r7 = r1.m;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:360:0x071d, code lost:
            r7 = r1.o;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:361:0x071f, code lost:
            r1.o = r7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:363:0x0725, code lost:
            if (r1.n == -1) goto L_0x073b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:365:0x0729, code lost:
            if (r1.o == -1) goto L_0x073b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:366:0x072b, code lost:
            r7 = ((float) (r1.m * r1.n)) / ((float) (r1.l * r1.o));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:367:0x073b, code lost:
            r7 = -1.0f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:369:0x073f, code lost:
            if (r1.w == false) goto L_0x0818;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:371:0x0745, code lost:
            if (r1.C == -1.0f) goto L_0x080b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:373:0x074b, code lost:
            if (r1.D == -1.0f) goto L_0x080b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:375:0x0751, code lost:
            if (r1.E == -1.0f) goto L_0x080b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:377:0x0757, code lost:
            if (r1.F == -1.0f) goto L_0x080b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:379:0x075d, code lost:
            if (r1.G == -1.0f) goto L_0x080b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:381:0x0763, code lost:
            if (r1.H == -1.0f) goto L_0x080b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:383:0x0769, code lost:
            if (r1.I == -1.0f) goto L_0x080b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:385:0x076f, code lost:
            if (r1.J == -1.0f) goto L_0x080b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:387:0x0775, code lost:
            if (r1.K == -1.0f) goto L_0x080b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:389:0x077b, code lost:
            if (r1.L != -1.0f) goto L_0x077f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:390:0x077f, code lost:
            r11 = new byte[25];
            r12 = java.nio.ByteBuffer.wrap(r11).order(java.nio.ByteOrder.LITTLE_ENDIAN);
            r12.put((byte) 0);
            r12.putShort((short) ((int) ((r1.C * 50000.0f) + 0.5f)));
            r12.putShort((short) ((int) ((r1.D * 50000.0f) + 0.5f)));
            r12.putShort((short) ((int) ((r1.E * 50000.0f) + 0.5f)));
            r12.putShort((short) ((int) ((r1.F * 50000.0f) + 0.5f)));
            r12.putShort((short) ((int) ((r1.G * 50000.0f) + 0.5f)));
            r12.putShort((short) ((int) ((r1.H * 50000.0f) + 0.5f)));
            r12.putShort((short) ((int) ((r1.I * 50000.0f) + 0.5f)));
            r12.putShort((short) ((int) ((r1.J * 50000.0f) + 0.5f)));
            r12.putShort((short) ((int) (r1.K + 0.5f)));
            r12.putShort((short) ((int) (r1.L + 0.5f)));
            r12.putShort((short) r1.A);
            r12.putShort((short) r1.B);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:391:0x080b, code lost:
            r11 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:392:0x080c, code lost:
            r12 = new com.google.android.exoplayer2.video.ColorInfo(r1.x, r1.z, r1.y, r11);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:393:0x0818, code lost:
            r12 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:395:0x081b, code lost:
            if (r1.a == null) goto L_0x0836;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:397:0x0825, code lost:
            if (com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.b.containsKey(r1.a) == false) goto L_0x0836;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:398:0x0827, code lost:
            r11 = ((java.lang.Integer) com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.b.get(r1.a)).intValue();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:399:0x0836, code lost:
            r11 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:401:0x0839, code lost:
            if (r1.q != 0) goto L_0x0888;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:403:0x0842, code lost:
            if (java.lang.Float.compare(r1.r, 0.0f) != 0) goto L_0x0888;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:405:0x084a, code lost:
            if (java.lang.Float.compare(r1.s, 0.0f) != 0) goto L_0x0888;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:407:0x0852, code lost:
            if (java.lang.Float.compare(r1.t, 0.0f) != 0) goto L_0x0856;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:408:0x0854, code lost:
            r0 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:410:0x085e, code lost:
            if (java.lang.Float.compare(r1.s, 90.0f) != 0) goto L_0x0863;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:411:0x0860, code lost:
            r0 = 90;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:413:0x086b, code lost:
            if (java.lang.Float.compare(r1.s, -180.0f) == 0) goto L_0x0885;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:415:0x0875, code lost:
            if (java.lang.Float.compare(r1.s, 180.0f) != 0) goto L_0x0878;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:417:0x0880, code lost:
            if (java.lang.Float.compare(r1.s, -90.0f) != 0) goto L_0x0888;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:418:0x0882, code lost:
            r0 = 270;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:419:0x0885, code lost:
            r0 = 180;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:420:0x0888, code lost:
            r0 = r11;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:421:0x0889, code lost:
            r13.p = r1.l;
            r13.q = r1.m;
            r13.t = r7;
            r13.s = r0;
            r13.u = r1.u;
            r13.v = r1.v;
            r13.w = r12;
            r14 = 2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:423:0x08a7, code lost:
            if ("application/x-subrip".equals(r9) != false) goto L_0x08d2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:425:0x08af, code lost:
            if ("text/x-ssa".equals(r9) != false) goto L_0x08d2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:427:0x08b7, code lost:
            if ("application/vobsub".equals(r9) != false) goto L_0x08d2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:429:0x08bf, code lost:
            if ("application/pgs".equals(r9) != false) goto L_0x08d2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:431:0x08c7, code lost:
            if ("application/dvbsubs".equals(r9) == false) goto L_0x08ca;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:433:0x08d1, code lost:
            throw new com.google.android.exoplayer2.ParserException("Unexpected MIME type.");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:435:0x08d4, code lost:
            if (r1.a == null) goto L_0x08e4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:437:0x08de, code lost:
            if (com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.b.containsKey(r1.a) != false) goto L_0x08e4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:438:0x08e0, code lost:
            r13.b = r1.a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:439:0x08e4, code lost:
            r0 = r13.a(r3);
            r0.k = r9;
            r0.l = r4;
            r0.c = r1.V;
            r0.d = r10;
            r0.m = r5;
            r0.h = r6;
            r0.n = r1.k;
            r0 = r0.a();
            r1.W = r2.a(r1.c, r14);
            r1.W.a(r0);
            r8.d.put(r1.c, r1);
            r0 = null;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void c(int r22) {
            /*
                r21 = this;
                r0 = r21
                r1 = r22
                com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor r8 = com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.this
                r8.a()
                r2 = -1
                r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
                java.lang.String r7 = "MatroskaExtractor"
                r9 = 0
                r10 = 1
                r11 = -1
                switch(r1) {
                    case 160: goto L_0x0920;
                    case 174: goto L_0x017a;
                    case 19899: goto L_0x015c;
                    case 25152: goto L_0x0127;
                    case 28032: goto L_0x010e;
                    case 357149030: goto L_0x00f4;
                    case 374648427: goto L_0x00dd;
                    case 475249515: goto L_0x001a;
                    default: goto L_0x0018;
                }
            L_0x0018:
                goto L_0x096c
            L_0x001a:
                boolean r1 = r8.o
                if (r1 != 0) goto L_0x00d8
                com.google.android.exoplayer2.extractor.ExtractorOutput r1 = r8.I
                com.google.android.exoplayer2.util.LongArray r6 = r8.u
                com.google.android.exoplayer2.util.LongArray r11 = r8.v
                long r13 = r8.j
                int r15 = (r13 > r2 ? 1 : (r13 == r2 ? 0 : -1))
                if (r15 == 0) goto L_0x00cc
                long r2 = r8.m
                int r13 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r13 == 0) goto L_0x00cc
                if (r6 == 0) goto L_0x00cc
                int r2 = r6.a
                if (r2 == 0) goto L_0x00cc
                if (r11 == 0) goto L_0x00cc
                int r2 = r11.a
                int r3 = r6.a
                if (r2 == r3) goto L_0x0040
                goto L_0x00cc
            L_0x0040:
                int r2 = r6.a
                int[] r3 = new int[r2]
                long[] r4 = new long[r2]
                long[] r5 = new long[r2]
                long[] r13 = new long[r2]
                r14 = 0
            L_0x004b:
                if (r14 >= r2) goto L_0x0064
                long r15 = r6.a((int) r14)
                r13[r14] = r15
                r16 = r13
                long r12 = r8.j
                long r17 = r11.a((int) r14)
                long r12 = r12 + r17
                r4[r14] = r12
                int r14 = r14 + 1
                r13 = r16
                goto L_0x004b
            L_0x0064:
                r16 = r13
                r12 = 0
            L_0x0067:
                int r6 = r2 + -1
                if (r12 >= r6) goto L_0x0080
                int r6 = r12 + 1
                r13 = r4[r6]
                r17 = r4[r12]
                long r13 = r13 - r17
                int r11 = (int) r13
                r3[r12] = r11
                r13 = r16[r6]
                r17 = r16[r12]
                long r13 = r13 - r17
                r5[r12] = r13
                r12 = r6
                goto L_0x0067
            L_0x0080:
                long r11 = r8.j
                long r13 = r8.i
                long r11 = r11 + r13
                r13 = r4[r6]
                long r11 = r11 - r13
                int r2 = (int) r11
                r3[r6] = r2
                long r11 = r8.m
                r13 = r16[r6]
                long r11 = r11 - r13
                r5[r6] = r11
                r13 = 0
                int r2 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
                if (r2 > 0) goto L_0x00c3
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r13 = 72
                r2.<init>(r13)
                java.lang.String r13 = "Discarding last cue point with unexpected duration: "
                java.lang.StringBuilder r2 = r2.append(r13)
                java.lang.StringBuilder r2 = r2.append(r11)
                java.lang.String r2 = r2.toString()
                com.google.android.exoplayer2.util.Log.c(r7, r2)
                int[] r3 = java.util.Arrays.copyOf(r3, r6)
                long[] r4 = java.util.Arrays.copyOf(r4, r6)
                long[] r5 = java.util.Arrays.copyOf(r5, r6)
                r2 = r16
                long[] r13 = java.util.Arrays.copyOf(r2, r6)
                goto L_0x00c6
            L_0x00c3:
                r2 = r16
                r13 = r2
            L_0x00c6:
                com.google.android.exoplayer2.extractor.ChunkIndex r2 = new com.google.android.exoplayer2.extractor.ChunkIndex
                r2.<init>(r3, r4, r5, r13)
                goto L_0x00d3
            L_0x00cc:
                com.google.android.exoplayer2.extractor.SeekMap$Unseekable r2 = new com.google.android.exoplayer2.extractor.SeekMap$Unseekable
                long r3 = r8.m
                r2.<init>(r3)
            L_0x00d3:
                r1.a(r2)
                r8.o = r10
            L_0x00d8:
                r8.u = r9
                r8.v = r9
                return
            L_0x00dd:
                android.util.SparseArray r1 = r8.d
                int r1 = r1.size()
                if (r1 == 0) goto L_0x00ec
                com.google.android.exoplayer2.extractor.ExtractorOutput r1 = r8.I
                r1.c_()
                goto L_0x096c
            L_0x00ec:
                com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
                java.lang.String r2 = "No valid tracks were found"
                r1.<init>((java.lang.String) r2)
                throw r1
            L_0x00f4:
                long r1 = r8.k
                int r3 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
                if (r3 != 0) goto L_0x00ff
                r1 = 1000000(0xf4240, double:4.940656E-318)
                r8.k = r1
            L_0x00ff:
                long r1 = r8.l
                int r3 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
                if (r3 == 0) goto L_0x096c
                long r1 = r8.l
                long r1 = r8.a((long) r1)
                r8.m = r1
                return
            L_0x010e:
                r8.c(r1)
                com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r1 = r8.n
                boolean r1 = r1.g
                if (r1 == 0) goto L_0x096c
                com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r1 = r8.n
                byte[] r1 = r1.h
                if (r1 != 0) goto L_0x011f
                goto L_0x096c
            L_0x011f:
                com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
                java.lang.String r2 = "Combining encryption and compression is not supported"
                r1.<init>((java.lang.String) r2)
                throw r1
            L_0x0127:
                r8.c(r1)
                com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r1 = r8.n
                boolean r1 = r1.g
                if (r1 == 0) goto L_0x096c
                com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r1 = r8.n
                com.google.android.exoplayer2.extractor.TrackOutput$CryptoData r1 = r1.i
                if (r1 == 0) goto L_0x0154
                com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r1 = r8.n
                com.google.android.exoplayer2.drm.DrmInitData r2 = new com.google.android.exoplayer2.drm.DrmInitData
                com.google.android.exoplayer2.drm.DrmInitData$SchemeData[] r3 = new com.google.android.exoplayer2.drm.DrmInitData.SchemeData[r10]
                com.google.android.exoplayer2.drm.DrmInitData$SchemeData r4 = new com.google.android.exoplayer2.drm.DrmInitData$SchemeData
                java.util.UUID r5 = com.google.android.exoplayer2.C.a
                com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r6 = r8.n
                com.google.android.exoplayer2.extractor.TrackOutput$CryptoData r6 = r6.i
                byte[] r6 = r6.b
                java.lang.String r7 = "video/webm"
                r4.<init>(r5, r7, r6)
                r5 = 0
                r3[r5] = r4
                r2.<init>((com.google.android.exoplayer2.drm.DrmInitData.SchemeData[]) r3)
                r1.k = r2
                return
            L_0x0154:
                com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
                java.lang.String r2 = "Encrypted Track found but ContentEncKeyID was not found"
                r1.<init>((java.lang.String) r2)
                throw r1
            L_0x015c:
                int r1 = r8.p
                if (r1 == r11) goto L_0x0172
                long r4 = r8.q
                int r1 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
                if (r1 == 0) goto L_0x0172
                int r1 = r8.p
                r2 = 475249515(0x1c53bb6b, float:7.0056276E-22)
                if (r1 != r2) goto L_0x096c
                long r1 = r8.q
                r8.s = r1
                return
            L_0x0172:
                com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
                java.lang.String r2 = "Mandatory element SeekID or SeekPosition not found"
                r1.<init>((java.lang.String) r2)
                throw r1
            L_0x017a:
                com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r1 = r8.n
                java.lang.Object r1 = com.google.android.exoplayer2.util.Assertions.a((java.lang.Object) r1)
                com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r1 = (com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.Track) r1
                java.lang.String r2 = r1.b
                if (r2 == 0) goto L_0x0918
                java.lang.String r2 = r1.b
                int r3 = r2.hashCode()
                java.lang.String r4 = "V_MPEG4/ISO/SP"
                java.lang.String r5 = "V_MPEG4/ISO/AP"
                r12 = 25
                r13 = 16
                r14 = 3
                r10 = 8
                r15 = 4
                switch(r3) {
                    case -2095576542: goto L_0x02ff;
                    case -2095575984: goto L_0x02f7;
                    case -1985379776: goto L_0x02ec;
                    case -1784763192: goto L_0x02e1;
                    case -1730367663: goto L_0x02d6;
                    case -1482641358: goto L_0x02cb;
                    case -1482641357: goto L_0x02c0;
                    case -1373388978: goto L_0x02b5;
                    case -933872740: goto L_0x02aa;
                    case -538363189: goto L_0x02a0;
                    case -538363109: goto L_0x0295;
                    case -425012669: goto L_0x0289;
                    case -356037306: goto L_0x027d;
                    case 62923557: goto L_0x0271;
                    case 62923603: goto L_0x0265;
                    case 62927045: goto L_0x0259;
                    case 82318131: goto L_0x024e;
                    case 82338133: goto L_0x0243;
                    case 82338134: goto L_0x0238;
                    case 99146302: goto L_0x022c;
                    case 444813526: goto L_0x0220;
                    case 542569478: goto L_0x0214;
                    case 635596514: goto L_0x0208;
                    case 725948237: goto L_0x01fc;
                    case 725957860: goto L_0x01f0;
                    case 738597099: goto L_0x01e4;
                    case 855502857: goto L_0x01d8;
                    case 1422270023: goto L_0x01cc;
                    case 1809237540: goto L_0x01c1;
                    case 1950749482: goto L_0x01b5;
                    case 1950789798: goto L_0x01a9;
                    case 1951062397: goto L_0x019d;
                    default: goto L_0x019b;
                }
            L_0x019b:
                goto L_0x0307
            L_0x019d:
                java.lang.String r3 = "A_OPUS"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0307
                r2 = 11
                goto L_0x0308
            L_0x01a9:
                java.lang.String r3 = "A_FLAC"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0307
                r2 = 22
                goto L_0x0308
            L_0x01b5:
                java.lang.String r3 = "A_EAC3"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0307
                r2 = 17
                goto L_0x0308
            L_0x01c1:
                java.lang.String r3 = "V_MPEG2"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0307
                r2 = 3
                goto L_0x0308
            L_0x01cc:
                java.lang.String r3 = "S_TEXT/UTF8"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0307
                r2 = 27
                goto L_0x0308
            L_0x01d8:
                java.lang.String r3 = "V_MPEGH/ISO/HEVC"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0307
                r2 = 8
                goto L_0x0308
            L_0x01e4:
                java.lang.String r3 = "S_TEXT/ASS"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0307
                r2 = 28
                goto L_0x0308
            L_0x01f0:
                java.lang.String r3 = "A_PCM/INT/LIT"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0307
                r2 = 24
                goto L_0x0308
            L_0x01fc:
                java.lang.String r3 = "A_PCM/INT/BIG"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0307
                r2 = 25
                goto L_0x0308
            L_0x0208:
                java.lang.String r3 = "A_PCM/FLOAT/IEEE"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0307
                r2 = 26
                goto L_0x0308
            L_0x0214:
                java.lang.String r3 = "A_DTS/EXPRESS"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0307
                r2 = 20
                goto L_0x0308
            L_0x0220:
                java.lang.String r3 = "V_THEORA"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0307
                r2 = 10
                goto L_0x0308
            L_0x022c:
                java.lang.String r3 = "S_HDMV/PGS"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0307
                r2 = 30
                goto L_0x0308
            L_0x0238:
                java.lang.String r3 = "V_VP9"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0307
                r2 = 1
                goto L_0x0308
            L_0x0243:
                java.lang.String r3 = "V_VP8"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0307
                r2 = 0
                goto L_0x0308
            L_0x024e:
                java.lang.String r3 = "V_AV1"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0307
                r2 = 2
                goto L_0x0308
            L_0x0259:
                java.lang.String r3 = "A_DTS"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0307
                r2 = 19
                goto L_0x0308
            L_0x0265:
                java.lang.String r3 = "A_AC3"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0307
                r2 = 16
                goto L_0x0308
            L_0x0271:
                java.lang.String r3 = "A_AAC"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0307
                r2 = 13
                goto L_0x0308
            L_0x027d:
                java.lang.String r3 = "A_DTS/LOSSLESS"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0307
                r2 = 21
                goto L_0x0308
            L_0x0289:
                java.lang.String r3 = "S_VOBSUB"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0307
                r2 = 29
                goto L_0x0308
            L_0x0295:
                java.lang.String r3 = "V_MPEG4/ISO/AVC"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0307
                r2 = 7
                goto L_0x0308
            L_0x02a0:
                java.lang.String r3 = "V_MPEG4/ISO/ASP"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0307
                r2 = 5
                goto L_0x0308
            L_0x02aa:
                java.lang.String r3 = "S_DVBSUB"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0307
                r2 = 31
                goto L_0x0308
            L_0x02b5:
                java.lang.String r3 = "V_MS/VFW/FOURCC"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0307
                r2 = 9
                goto L_0x0308
            L_0x02c0:
                java.lang.String r3 = "A_MPEG/L3"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0307
                r2 = 15
                goto L_0x0308
            L_0x02cb:
                java.lang.String r3 = "A_MPEG/L2"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0307
                r2 = 14
                goto L_0x0308
            L_0x02d6:
                java.lang.String r3 = "A_VORBIS"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0307
                r2 = 12
                goto L_0x0308
            L_0x02e1:
                java.lang.String r3 = "A_TRUEHD"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0307
                r2 = 18
                goto L_0x0308
            L_0x02ec:
                java.lang.String r3 = "A_MS/ACM"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0307
                r2 = 23
                goto L_0x0308
            L_0x02f7:
                boolean r2 = r2.equals(r4)
                if (r2 == 0) goto L_0x0307
                r2 = 4
                goto L_0x0308
            L_0x02ff:
                boolean r2 = r2.equals(r5)
                if (r2 == 0) goto L_0x0307
                r2 = 6
                goto L_0x0308
            L_0x0307:
                r2 = -1
            L_0x0308:
                switch(r2) {
                    case 0: goto L_0x030d;
                    case 1: goto L_0x030d;
                    case 2: goto L_0x030d;
                    case 3: goto L_0x030d;
                    case 4: goto L_0x030d;
                    case 5: goto L_0x030d;
                    case 6: goto L_0x030d;
                    case 7: goto L_0x030d;
                    case 8: goto L_0x030d;
                    case 9: goto L_0x030d;
                    case 10: goto L_0x030d;
                    case 11: goto L_0x030d;
                    case 12: goto L_0x030d;
                    case 13: goto L_0x030d;
                    case 14: goto L_0x030d;
                    case 15: goto L_0x030d;
                    case 16: goto L_0x030d;
                    case 17: goto L_0x030d;
                    case 18: goto L_0x030d;
                    case 19: goto L_0x030d;
                    case 20: goto L_0x030d;
                    case 21: goto L_0x030d;
                    case 22: goto L_0x030d;
                    case 23: goto L_0x030d;
                    case 24: goto L_0x030d;
                    case 25: goto L_0x030d;
                    case 26: goto L_0x030d;
                    case 27: goto L_0x030d;
                    case 28: goto L_0x030d;
                    case 29: goto L_0x030d;
                    case 30: goto L_0x030d;
                    case 31: goto L_0x030d;
                    default: goto L_0x030b;
                }
            L_0x030b:
                r2 = 0
                goto L_0x030e
            L_0x030d:
                r2 = 1
            L_0x030e:
                if (r2 == 0) goto L_0x0914
                com.google.android.exoplayer2.extractor.ExtractorOutput r2 = r8.I
                int r3 = r1.c
                java.lang.String r6 = r1.b
                int r18 = r6.hashCode()
                switch(r18) {
                    case -2095576542: goto L_0x0481;
                    case -2095575984: goto L_0x0479;
                    case -1985379776: goto L_0x046e;
                    case -1784763192: goto L_0x0463;
                    case -1730367663: goto L_0x0458;
                    case -1482641358: goto L_0x044d;
                    case -1482641357: goto L_0x0442;
                    case -1373388978: goto L_0x0437;
                    case -933872740: goto L_0x042c;
                    case -538363189: goto L_0x0422;
                    case -538363109: goto L_0x0417;
                    case -425012669: goto L_0x040b;
                    case -356037306: goto L_0x03ff;
                    case 62923557: goto L_0x03f3;
                    case 62923603: goto L_0x03e7;
                    case 62927045: goto L_0x03db;
                    case 82318131: goto L_0x03d0;
                    case 82338133: goto L_0x03c5;
                    case 82338134: goto L_0x03ba;
                    case 99146302: goto L_0x03ae;
                    case 444813526: goto L_0x03a2;
                    case 542569478: goto L_0x0396;
                    case 635596514: goto L_0x038a;
                    case 725948237: goto L_0x037e;
                    case 725957860: goto L_0x0372;
                    case 738597099: goto L_0x0366;
                    case 855502857: goto L_0x035a;
                    case 1422270023: goto L_0x034e;
                    case 1809237540: goto L_0x0343;
                    case 1950749482: goto L_0x0337;
                    case 1950789798: goto L_0x032b;
                    case 1951062397: goto L_0x031f;
                    default: goto L_0x031d;
                }
            L_0x031d:
                goto L_0x0489
            L_0x031f:
                java.lang.String r4 = "A_OPUS"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 12
                goto L_0x048a
            L_0x032b:
                java.lang.String r4 = "A_FLAC"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 22
                goto L_0x048a
            L_0x0337:
                java.lang.String r4 = "A_EAC3"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 17
                goto L_0x048a
            L_0x0343:
                java.lang.String r4 = "V_MPEG2"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 3
                goto L_0x048a
            L_0x034e:
                java.lang.String r4 = "S_TEXT/UTF8"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 27
                goto L_0x048a
            L_0x035a:
                java.lang.String r4 = "V_MPEGH/ISO/HEVC"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 8
                goto L_0x048a
            L_0x0366:
                java.lang.String r4 = "S_TEXT/ASS"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 28
                goto L_0x048a
            L_0x0372:
                java.lang.String r4 = "A_PCM/INT/LIT"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 24
                goto L_0x048a
            L_0x037e:
                java.lang.String r4 = "A_PCM/INT/BIG"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 25
                goto L_0x048a
            L_0x038a:
                java.lang.String r4 = "A_PCM/FLOAT/IEEE"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 26
                goto L_0x048a
            L_0x0396:
                java.lang.String r4 = "A_DTS/EXPRESS"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 20
                goto L_0x048a
            L_0x03a2:
                java.lang.String r4 = "V_THEORA"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 10
                goto L_0x048a
            L_0x03ae:
                java.lang.String r4 = "S_HDMV/PGS"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 30
                goto L_0x048a
            L_0x03ba:
                java.lang.String r4 = "V_VP9"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 1
                goto L_0x048a
            L_0x03c5:
                java.lang.String r4 = "V_VP8"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 0
                goto L_0x048a
            L_0x03d0:
                java.lang.String r4 = "V_AV1"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 2
                goto L_0x048a
            L_0x03db:
                java.lang.String r4 = "A_DTS"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 19
                goto L_0x048a
            L_0x03e7:
                java.lang.String r4 = "A_AC3"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 16
                goto L_0x048a
            L_0x03f3:
                java.lang.String r4 = "A_AAC"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 13
                goto L_0x048a
            L_0x03ff:
                java.lang.String r4 = "A_DTS/LOSSLESS"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 21
                goto L_0x048a
            L_0x040b:
                java.lang.String r4 = "S_VOBSUB"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 29
                goto L_0x048a
            L_0x0417:
                java.lang.String r4 = "V_MPEG4/ISO/AVC"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 7
                goto L_0x048a
            L_0x0422:
                java.lang.String r4 = "V_MPEG4/ISO/ASP"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 5
                goto L_0x048a
            L_0x042c:
                java.lang.String r4 = "S_DVBSUB"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 31
                goto L_0x048a
            L_0x0437:
                java.lang.String r4 = "V_MS/VFW/FOURCC"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 9
                goto L_0x048a
            L_0x0442:
                java.lang.String r4 = "A_MPEG/L3"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 15
                goto L_0x048a
            L_0x044d:
                java.lang.String r4 = "A_MPEG/L2"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 14
                goto L_0x048a
            L_0x0458:
                java.lang.String r4 = "A_VORBIS"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 11
                goto L_0x048a
            L_0x0463:
                java.lang.String r4 = "A_TRUEHD"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 18
                goto L_0x048a
            L_0x046e:
                java.lang.String r4 = "A_MS/ACM"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 23
                goto L_0x048a
            L_0x0479:
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0489
                r4 = 4
                goto L_0x048a
            L_0x0481:
                boolean r4 = r6.equals(r5)
                if (r4 == 0) goto L_0x0489
                r4 = 6
                goto L_0x048a
            L_0x0489:
                r4 = -1
            L_0x048a:
                java.lang.String r5 = ". Setting mimeType to "
                java.lang.String r6 = "audio/raw"
                java.lang.String r9 = "audio/x-unknown"
                switch(r4) {
                    case 0: goto L_0x06c1;
                    case 1: goto L_0x06be;
                    case 2: goto L_0x06bb;
                    case 3: goto L_0x06b8;
                    case 4: goto L_0x06a9;
                    case 5: goto L_0x06a9;
                    case 6: goto L_0x06a9;
                    case 7: goto L_0x068b;
                    case 8: goto L_0x0671;
                    case 9: goto L_0x0655;
                    case 10: goto L_0x0651;
                    case 11: goto L_0x063d;
                    case 12: goto L_0x05f7;
                    case 13: goto L_0x05d5;
                    case 14: goto L_0x05ce;
                    case 15: goto L_0x05cb;
                    case 16: goto L_0x05c7;
                    case 17: goto L_0x05c3;
                    case 18: goto L_0x05b8;
                    case 19: goto L_0x05b4;
                    case 20: goto L_0x05b4;
                    case 21: goto L_0x05b0;
                    case 22: goto L_0x05a2;
                    case 23: goto L_0x055e;
                    case 24: goto L_0x0523;
                    case 25: goto L_0x04f6;
                    case 26: goto L_0x04d5;
                    case 27: goto L_0x04d1;
                    case 28: goto L_0x04c1;
                    case 29: goto L_0x04b3;
                    case 30: goto L_0x04af;
                    case 31: goto L_0x049b;
                    default: goto L_0x0493;
                }
            L_0x0493:
                com.google.android.exoplayer2.ParserException r0 = new com.google.android.exoplayer2.ParserException
                java.lang.String r1 = "Unrecognized codec identifier."
                r0.<init>((java.lang.String) r1)
                throw r0
            L_0x049b:
                byte[] r4 = new byte[r15]
                java.lang.String r5 = r1.b
                byte[] r5 = r1.a((java.lang.String) r5)
                r6 = 0
                java.lang.System.arraycopy(r5, r6, r4, r6, r15)
                com.dreamers.exoplayercore.repack.bG r4 = com.dreamers.exoplayercore.repack.bG.a((java.lang.Object) r4)
                java.lang.String r6 = "application/dvbsubs"
                goto L_0x066d
            L_0x04af:
                java.lang.String r6 = "application/pgs"
                goto L_0x06c3
            L_0x04b3:
                java.lang.String r4 = r1.b
                byte[] r4 = r1.a((java.lang.String) r4)
                com.dreamers.exoplayercore.repack.bG r4 = com.dreamers.exoplayercore.repack.bG.a((java.lang.Object) r4)
                java.lang.String r6 = "application/vobsub"
                goto L_0x066d
            L_0x04c1:
                byte[] r4 = com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.a
                java.lang.String r5 = r1.b
                byte[] r5 = r1.a((java.lang.String) r5)
                com.dreamers.exoplayercore.repack.bG r4 = com.dreamers.exoplayercore.repack.bG.a((java.lang.Object) r4, (java.lang.Object) r5)
                java.lang.String r6 = "text/x-ssa"
                goto L_0x066d
            L_0x04d1:
                java.lang.String r6 = "application/x-subrip"
                goto L_0x06c3
            L_0x04d5:
                int r4 = r1.O
                r10 = 32
                if (r4 != r10) goto L_0x04e2
                r9 = r6
                r4 = -1
                r5 = 0
                r6 = 0
                r7 = 4
                goto L_0x06c8
            L_0x04e2:
                int r4 = r1.O
                java.lang.String r6 = java.lang.String.valueOf(r9)
                int r6 = r6.length()
                int r6 = r6 + 75
                java.lang.StringBuilder r10 = new java.lang.StringBuilder
                r10.<init>(r6)
                java.lang.String r6 = "Unsupported floating point PCM bit depth: "
                goto L_0x053e
            L_0x04f6:
                int r4 = r1.O
                if (r4 != r10) goto L_0x0501
                r9 = r6
                r4 = -1
                r5 = 0
                r6 = 0
                r7 = 3
                goto L_0x06c8
            L_0x0501:
                int r4 = r1.O
                if (r4 != r13) goto L_0x050f
                r15 = 268435456(0x10000000, float:2.5243549E-29)
                r9 = r6
                r4 = -1
                r5 = 0
                r6 = 0
                r7 = 268435456(0x10000000, float:2.5243549E-29)
                goto L_0x06c8
            L_0x050f:
                int r4 = r1.O
                java.lang.String r6 = java.lang.String.valueOf(r9)
                int r6 = r6.length()
                int r6 = r6 + 71
                java.lang.StringBuilder r10 = new java.lang.StringBuilder
                r10.<init>(r6)
                java.lang.String r6 = "Unsupported big endian PCM bit depth: "
                goto L_0x053e
            L_0x0523:
                int r4 = r1.O
                int r15 = com.google.android.exoplayer2.util.Util.b((int) r4)
                if (r15 != 0) goto L_0x0557
                int r4 = r1.O
                java.lang.String r6 = java.lang.String.valueOf(r9)
                int r6 = r6.length()
                int r6 = r6 + 74
                java.lang.StringBuilder r10 = new java.lang.StringBuilder
                r10.<init>(r6)
                java.lang.String r6 = "Unsupported little endian PCM bit depth: "
            L_0x053e:
                java.lang.StringBuilder r6 = r10.append(r6)
                java.lang.StringBuilder r4 = r6.append(r4)
                java.lang.StringBuilder r4 = r4.append(r5)
                java.lang.StringBuilder r4 = r4.append(r9)
                java.lang.String r4 = r4.toString()
            L_0x0552:
                com.google.android.exoplayer2.util.Log.c(r7, r4)
                goto L_0x06c4
            L_0x0557:
                r9 = r6
                r7 = r15
                r4 = -1
                r5 = 0
                r6 = 0
                goto L_0x06c8
            L_0x055e:
                com.google.android.exoplayer2.util.ParsableByteArray r4 = new com.google.android.exoplayer2.util.ParsableByteArray
                java.lang.String r10 = r1.b
                byte[] r10 = r1.a((java.lang.String) r10)
                r4.<init>((byte[]) r10)
                boolean r4 = com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.Track.b((com.google.android.exoplayer2.util.ParsableByteArray) r4)
                if (r4 == 0) goto L_0x058b
                int r4 = r1.O
                int r15 = com.google.android.exoplayer2.util.Util.b((int) r4)
                if (r15 != 0) goto L_0x0557
                int r4 = r1.O
                java.lang.String r6 = java.lang.String.valueOf(r9)
                int r6 = r6.length()
                int r6 = r6 + 60
                java.lang.StringBuilder r10 = new java.lang.StringBuilder
                r10.<init>(r6)
                java.lang.String r6 = "Unsupported PCM bit depth: "
                goto L_0x053e
            L_0x058b:
                java.lang.String r4 = java.lang.String.valueOf(r9)
                int r5 = r4.length()
                java.lang.String r6 = "Non-PCM MS/ACM is unsupported. Setting mimeType to "
                if (r5 == 0) goto L_0x059c
                java.lang.String r4 = r6.concat(r4)
                goto L_0x0552
            L_0x059c:
                java.lang.String r4 = new java.lang.String
                r4.<init>(r6)
                goto L_0x0552
            L_0x05a2:
                java.lang.String r4 = r1.b
                byte[] r4 = r1.a((java.lang.String) r4)
                java.util.List r4 = java.util.Collections.singletonList(r4)
                java.lang.String r6 = "audio/flac"
                goto L_0x066d
            L_0x05b0:
                java.lang.String r6 = "audio/vnd.dts.hd"
                goto L_0x06c3
            L_0x05b4:
                java.lang.String r6 = "audio/vnd.dts"
                goto L_0x06c3
            L_0x05b8:
                com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$TrueHdSampleRechunker r4 = new com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$TrueHdSampleRechunker
                r4.<init>()
                r1.S = r4
                java.lang.String r6 = "audio/true-hd"
                goto L_0x06c3
            L_0x05c3:
                java.lang.String r6 = "audio/eac3"
                goto L_0x06c3
            L_0x05c7:
                java.lang.String r6 = "audio/ac3"
                goto L_0x06c3
            L_0x05cb:
                java.lang.String r6 = "audio/mpeg"
                goto L_0x05d0
            L_0x05ce:
                java.lang.String r6 = "audio/mpeg-L2"
            L_0x05d0:
                r4 = 4096(0x1000, float:5.74E-42)
                r9 = r6
                goto L_0x06c5
            L_0x05d5:
                java.lang.String r4 = r1.b
                byte[] r4 = r1.a((java.lang.String) r4)
                java.util.List r4 = java.util.Collections.singletonList(r4)
                byte[] r5 = r1.j
                com.google.android.exoplayer2.audio.AacUtil$Config r5 = com.google.android.exoplayer2.audio.AacUtil.a((byte[]) r5)
                int r6 = r5.a
                r1.P = r6
                int r6 = r5.b
                r1.N = r6
                java.lang.String r5 = r5.c
                java.lang.String r6 = "audio/mp4a-latm"
                r9 = r6
                r7 = -1
                r6 = r5
                r5 = r4
                goto L_0x06a7
            L_0x05f7:
                java.util.ArrayList r4 = new java.util.ArrayList
                r4.<init>(r14)
                java.lang.String r5 = r1.b
                byte[] r5 = r1.a((java.lang.String) r5)
                r4.add(r5)
                java.nio.ByteBuffer r5 = java.nio.ByteBuffer.allocate(r10)
                java.nio.ByteOrder r6 = java.nio.ByteOrder.LITTLE_ENDIAN
                java.nio.ByteBuffer r5 = r5.order(r6)
                long r6 = r1.Q
                java.nio.ByteBuffer r5 = r5.putLong(r6)
                byte[] r5 = r5.array()
                r4.add(r5)
                java.nio.ByteBuffer r5 = java.nio.ByteBuffer.allocate(r10)
                java.nio.ByteOrder r6 = java.nio.ByteOrder.LITTLE_ENDIAN
                java.nio.ByteBuffer r5 = r5.order(r6)
                long r6 = r1.R
                java.nio.ByteBuffer r5 = r5.putLong(r6)
                byte[] r5 = r5.array()
                r4.add(r5)
                java.lang.String r6 = "audio/opus"
                r5 = 5760(0x1680, float:8.071E-42)
                r5 = r4
                r9 = r6
                r4 = 5760(0x1680, float:8.071E-42)
                goto L_0x06c6
            L_0x063d:
                java.lang.String r4 = r1.b
                byte[] r4 = r1.a((java.lang.String) r4)
                java.util.List r4 = com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.Track.a((byte[]) r4)
                java.lang.String r6 = "audio/vorbis"
                r5 = 8192(0x2000, float:1.14794E-41)
                r5 = r4
                r9 = r6
                r4 = 8192(0x2000, float:1.14794E-41)
                goto L_0x06c6
            L_0x0651:
                java.lang.String r6 = "video/x-unknown"
                goto L_0x06c3
            L_0x0655:
                com.google.android.exoplayer2.util.ParsableByteArray r4 = new com.google.android.exoplayer2.util.ParsableByteArray
                java.lang.String r5 = r1.b
                byte[] r5 = r1.a((java.lang.String) r5)
                r4.<init>((byte[]) r5)
                android.util.Pair r4 = com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.Track.a((com.google.android.exoplayer2.util.ParsableByteArray) r4)
                java.lang.Object r5 = r4.first
                r6 = r5
                java.lang.String r6 = (java.lang.String) r6
                java.lang.Object r4 = r4.second
                java.util.List r4 = (java.util.List) r4
            L_0x066d:
                r5 = r4
                r9 = r6
                r4 = -1
                goto L_0x06c6
            L_0x0671:
                com.google.android.exoplayer2.util.ParsableByteArray r4 = new com.google.android.exoplayer2.util.ParsableByteArray
                java.lang.String r5 = r1.b
                byte[] r5 = r1.a((java.lang.String) r5)
                r4.<init>((byte[]) r5)
                com.google.android.exoplayer2.video.HevcConfig r4 = com.google.android.exoplayer2.video.HevcConfig.a(r4)
                java.util.List r5 = r4.a
                int r6 = r4.b
                r1.X = r6
                java.lang.String r4 = r4.c
                java.lang.String r6 = "video/hevc"
                goto L_0x06a4
            L_0x068b:
                com.google.android.exoplayer2.util.ParsableByteArray r4 = new com.google.android.exoplayer2.util.ParsableByteArray
                java.lang.String r5 = r1.b
                byte[] r5 = r1.a((java.lang.String) r5)
                r4.<init>((byte[]) r5)
                com.google.android.exoplayer2.video.AvcConfig r4 = com.google.android.exoplayer2.video.AvcConfig.a(r4)
                java.util.List r5 = r4.a
                int r6 = r4.b
                r1.X = r6
                java.lang.String r4 = r4.f
                java.lang.String r6 = "video/avc"
            L_0x06a4:
                r9 = r6
                r7 = -1
                r6 = r4
            L_0x06a7:
                r4 = -1
                goto L_0x06c8
            L_0x06a9:
                byte[] r4 = r1.j
                if (r4 != 0) goto L_0x06af
                r4 = 0
                goto L_0x06b5
            L_0x06af:
                byte[] r4 = r1.j
                java.util.List r4 = java.util.Collections.singletonList(r4)
            L_0x06b5:
                java.lang.String r6 = "video/mp4v-es"
                goto L_0x066d
            L_0x06b8:
                java.lang.String r6 = "video/mpeg2"
                goto L_0x06c3
            L_0x06bb:
                java.lang.String r6 = "video/av01"
                goto L_0x06c3
            L_0x06be:
                java.lang.String r6 = "video/x-vnd.on2.vp9"
                goto L_0x06c3
            L_0x06c1:
                java.lang.String r6 = "video/x-vnd.on2.vp8"
            L_0x06c3:
                r9 = r6
            L_0x06c4:
                r4 = -1
            L_0x06c5:
                r5 = 0
            L_0x06c6:
                r6 = 0
                r7 = -1
            L_0x06c8:
                byte[] r10 = r1.M
                if (r10 == 0) goto L_0x06dd
                com.google.android.exoplayer2.util.ParsableByteArray r10 = new com.google.android.exoplayer2.util.ParsableByteArray
                byte[] r13 = r1.M
                r10.<init>((byte[]) r13)
                com.google.android.exoplayer2.video.DolbyVisionConfig r10 = com.google.android.exoplayer2.video.DolbyVisionConfig.a(r10)
                if (r10 == 0) goto L_0x06dd
                java.lang.String r6 = r10.a
                java.lang.String r9 = "video/dolby-vision"
            L_0x06dd:
                boolean r10 = r1.U
                r13 = 0
                r10 = r10 | r13
                boolean r13 = r1.T
                if (r13 == 0) goto L_0x06e7
                r13 = 2
                goto L_0x06e8
            L_0x06e7:
                r13 = 0
            L_0x06e8:
                r10 = r10 | r13
                com.google.android.exoplayer2.Format$Builder r13 = new com.google.android.exoplayer2.Format$Builder
                r13.<init>()
                boolean r19 = com.google.android.exoplayer2.util.MimeTypes.a((java.lang.String) r9)
                if (r19 == 0) goto L_0x0701
                int r11 = r1.N
                r13.x = r11
                int r11 = r1.P
                r13.y = r11
                r13.z = r7
                r14 = 1
                goto L_0x08d2
            L_0x0701:
                boolean r7 = com.google.android.exoplayer2.util.MimeTypes.b(r9)
                if (r7 == 0) goto L_0x08a1
                int r7 = r1.p
                if (r7 != 0) goto L_0x0721
                int r7 = r1.n
                if (r7 != r11) goto L_0x0712
                int r7 = r1.l
                goto L_0x0714
            L_0x0712:
                int r7 = r1.n
            L_0x0714:
                r1.n = r7
                int r7 = r1.o
                if (r7 != r11) goto L_0x071d
                int r7 = r1.m
                goto L_0x071f
            L_0x071d:
                int r7 = r1.o
            L_0x071f:
                r1.o = r7
            L_0x0721:
                int r7 = r1.n
                r14 = -1082130432(0xffffffffbf800000, float:-1.0)
                if (r7 == r11) goto L_0x073b
                int r7 = r1.o
                if (r7 == r11) goto L_0x073b
                int r7 = r1.m
                int r11 = r1.n
                int r7 = r7 * r11
                float r7 = (float) r7
                int r11 = r1.l
                int r15 = r1.o
                int r11 = r11 * r15
                float r11 = (float) r11
                float r7 = r7 / r11
                goto L_0x073d
            L_0x073b:
                r7 = -1082130432(0xffffffffbf800000, float:-1.0)
            L_0x073d:
                boolean r11 = r1.w
                if (r11 == 0) goto L_0x0818
                float r11 = r1.C
                int r11 = (r11 > r14 ? 1 : (r11 == r14 ? 0 : -1))
                if (r11 == 0) goto L_0x080b
                float r11 = r1.D
                int r11 = (r11 > r14 ? 1 : (r11 == r14 ? 0 : -1))
                if (r11 == 0) goto L_0x080b
                float r11 = r1.E
                int r11 = (r11 > r14 ? 1 : (r11 == r14 ? 0 : -1))
                if (r11 == 0) goto L_0x080b
                float r11 = r1.F
                int r11 = (r11 > r14 ? 1 : (r11 == r14 ? 0 : -1))
                if (r11 == 0) goto L_0x080b
                float r11 = r1.G
                int r11 = (r11 > r14 ? 1 : (r11 == r14 ? 0 : -1))
                if (r11 == 0) goto L_0x080b
                float r11 = r1.H
                int r11 = (r11 > r14 ? 1 : (r11 == r14 ? 0 : -1))
                if (r11 == 0) goto L_0x080b
                float r11 = r1.I
                int r11 = (r11 > r14 ? 1 : (r11 == r14 ? 0 : -1))
                if (r11 == 0) goto L_0x080b
                float r11 = r1.J
                int r11 = (r11 > r14 ? 1 : (r11 == r14 ? 0 : -1))
                if (r11 == 0) goto L_0x080b
                float r11 = r1.K
                int r11 = (r11 > r14 ? 1 : (r11 == r14 ? 0 : -1))
                if (r11 == 0) goto L_0x080b
                float r11 = r1.L
                int r11 = (r11 > r14 ? 1 : (r11 == r14 ? 0 : -1))
                if (r11 != 0) goto L_0x077f
                goto L_0x080b
            L_0x077f:
                byte[] r11 = new byte[r12]
                java.nio.ByteBuffer r12 = java.nio.ByteBuffer.wrap(r11)
                java.nio.ByteOrder r14 = java.nio.ByteOrder.LITTLE_ENDIAN
                java.nio.ByteBuffer r12 = r12.order(r14)
                r14 = 0
                r12.put(r14)
                float r14 = r1.C
                r19 = 1195593728(0x47435000, float:50000.0)
                float r14 = r14 * r19
                r20 = 1056964608(0x3f000000, float:0.5)
                float r14 = r14 + r20
                int r14 = (int) r14
                short r14 = (short) r14
                r12.putShort(r14)
                float r14 = r1.D
                float r14 = r14 * r19
                float r14 = r14 + r20
                int r14 = (int) r14
                short r14 = (short) r14
                r12.putShort(r14)
                float r14 = r1.E
                float r14 = r14 * r19
                float r14 = r14 + r20
                int r14 = (int) r14
                short r14 = (short) r14
                r12.putShort(r14)
                float r14 = r1.F
                float r14 = r14 * r19
                float r14 = r14 + r20
                int r14 = (int) r14
                short r14 = (short) r14
                r12.putShort(r14)
                float r14 = r1.G
                float r14 = r14 * r19
                float r14 = r14 + r20
                int r14 = (int) r14
                short r14 = (short) r14
                r12.putShort(r14)
                float r14 = r1.H
                float r14 = r14 * r19
                float r14 = r14 + r20
                int r14 = (int) r14
                short r14 = (short) r14
                r12.putShort(r14)
                float r14 = r1.I
                float r14 = r14 * r19
                float r14 = r14 + r20
                int r14 = (int) r14
                short r14 = (short) r14
                r12.putShort(r14)
                float r14 = r1.J
                float r14 = r14 * r19
                float r14 = r14 + r20
                int r14 = (int) r14
                short r14 = (short) r14
                r12.putShort(r14)
                float r14 = r1.K
                float r14 = r14 + r20
                int r14 = (int) r14
                short r14 = (short) r14
                r12.putShort(r14)
                float r14 = r1.L
                float r14 = r14 + r20
                int r14 = (int) r14
                short r14 = (short) r14
                r12.putShort(r14)
                int r14 = r1.A
                short r14 = (short) r14
                r12.putShort(r14)
                int r14 = r1.B
                short r14 = (short) r14
                r12.putShort(r14)
                goto L_0x080c
            L_0x080b:
                r11 = 0
            L_0x080c:
                com.google.android.exoplayer2.video.ColorInfo r12 = new com.google.android.exoplayer2.video.ColorInfo
                int r14 = r1.x
                int r15 = r1.z
                int r0 = r1.y
                r12.<init>(r14, r15, r0, r11)
                goto L_0x0819
            L_0x0818:
                r12 = 0
            L_0x0819:
                java.lang.String r0 = r1.a
                if (r0 == 0) goto L_0x0836
                java.util.Map r0 = com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.b
                java.lang.String r11 = r1.a
                boolean r0 = r0.containsKey(r11)
                if (r0 == 0) goto L_0x0836
                java.util.Map r0 = com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.b
                java.lang.String r11 = r1.a
                java.lang.Object r0 = r0.get(r11)
                java.lang.Integer r0 = (java.lang.Integer) r0
                int r11 = r0.intValue()
                goto L_0x0837
            L_0x0836:
                r11 = -1
            L_0x0837:
                int r0 = r1.q
                if (r0 != 0) goto L_0x0888
                float r0 = r1.r
                r14 = 0
                int r0 = java.lang.Float.compare(r0, r14)
                if (r0 != 0) goto L_0x0888
                float r0 = r1.s
                int r0 = java.lang.Float.compare(r0, r14)
                if (r0 != 0) goto L_0x0888
                float r0 = r1.t
                int r0 = java.lang.Float.compare(r0, r14)
                if (r0 != 0) goto L_0x0856
                r0 = 0
                goto L_0x0889
            L_0x0856:
                float r0 = r1.s
                r14 = 1119092736(0x42b40000, float:90.0)
                int r0 = java.lang.Float.compare(r0, r14)
                if (r0 != 0) goto L_0x0863
                r0 = 90
                goto L_0x0889
            L_0x0863:
                float r0 = r1.s
                r14 = -1020002304(0xffffffffc3340000, float:-180.0)
                int r0 = java.lang.Float.compare(r0, r14)
                if (r0 == 0) goto L_0x0885
                float r0 = r1.s
                r14 = 1127481344(0x43340000, float:180.0)
                int r0 = java.lang.Float.compare(r0, r14)
                if (r0 != 0) goto L_0x0878
                goto L_0x0885
            L_0x0878:
                float r0 = r1.s
                r14 = -1028390912(0xffffffffc2b40000, float:-90.0)
                int r0 = java.lang.Float.compare(r0, r14)
                if (r0 != 0) goto L_0x0888
                r0 = 270(0x10e, float:3.78E-43)
                goto L_0x0889
            L_0x0885:
                r0 = 180(0xb4, float:2.52E-43)
                goto L_0x0889
            L_0x0888:
                r0 = r11
            L_0x0889:
                int r11 = r1.l
                r13.p = r11
                int r11 = r1.m
                r13.q = r11
                r13.t = r7
                r13.s = r0
                byte[] r0 = r1.u
                r13.u = r0
                int r0 = r1.v
                r13.v = r0
                r13.w = r12
                r14 = 2
                goto L_0x08d2
            L_0x08a1:
                java.lang.String r0 = "application/x-subrip"
                boolean r0 = r0.equals(r9)
                if (r0 != 0) goto L_0x08d2
                java.lang.String r0 = "text/x-ssa"
                boolean r0 = r0.equals(r9)
                if (r0 != 0) goto L_0x08d2
                java.lang.String r0 = "application/vobsub"
                boolean r0 = r0.equals(r9)
                if (r0 != 0) goto L_0x08d2
                java.lang.String r0 = "application/pgs"
                boolean r0 = r0.equals(r9)
                if (r0 != 0) goto L_0x08d2
                java.lang.String r0 = "application/dvbsubs"
                boolean r0 = r0.equals(r9)
                if (r0 == 0) goto L_0x08ca
                goto L_0x08d2
            L_0x08ca:
                com.google.android.exoplayer2.ParserException r0 = new com.google.android.exoplayer2.ParserException
                java.lang.String r1 = "Unexpected MIME type."
                r0.<init>((java.lang.String) r1)
                throw r0
            L_0x08d2:
                java.lang.String r0 = r1.a
                if (r0 == 0) goto L_0x08e4
                java.util.Map r0 = com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.b
                java.lang.String r7 = r1.a
                boolean r0 = r0.containsKey(r7)
                if (r0 != 0) goto L_0x08e4
                java.lang.String r0 = r1.a
                r13.b = r0
            L_0x08e4:
                com.google.android.exoplayer2.Format$Builder r0 = r13.a((int) r3)
                r0.k = r9
                r0.l = r4
                java.lang.String r3 = r1.V
                r0.c = r3
                r0.d = r10
                r0.m = r5
                r0.h = r6
                com.google.android.exoplayer2.drm.DrmInitData r3 = r1.k
                r0.n = r3
                com.google.android.exoplayer2.Format r0 = r0.a()
                int r3 = r1.c
                com.google.android.exoplayer2.extractor.TrackOutput r2 = r2.a(r3, r14)
                r1.W = r2
                com.google.android.exoplayer2.extractor.TrackOutput r2 = r1.W
                r2.a(r0)
                android.util.SparseArray r0 = r8.d
                int r2 = r1.c
                r0.put(r2, r1)
                r0 = 0
                goto L_0x0915
            L_0x0914:
                r0 = r9
            L_0x0915:
                r8.n = r0
                return
            L_0x0918:
                com.google.android.exoplayer2.ParserException r0 = new com.google.android.exoplayer2.ParserException
                java.lang.String r1 = "CodecId is missing in TrackEntry element"
                r0.<init>((java.lang.String) r1)
                throw r0
            L_0x0920:
                int r0 = r8.x
                r1 = 2
                if (r0 != r1) goto L_0x096c
                r0 = 0
                r5 = 0
            L_0x0927:
                int r1 = r8.B
                if (r5 >= r1) goto L_0x0933
                int[] r1 = r8.C
                r1 = r1[r5]
                int r0 = r0 + r1
                int r5 = r5 + 1
                goto L_0x0927
            L_0x0933:
                android.util.SparseArray r1 = r8.d
                int r2 = r8.D
                java.lang.Object r1 = r1.get(r2)
                r9 = r1
                com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r9 = (com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.Track) r9
                com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r9.W)
                r10 = 0
            L_0x0942:
                int r1 = r8.B
                if (r10 >= r1) goto L_0x0969
                long r1 = r8.y
                int r3 = r9.e
                int r3 = r3 * r10
                int r3 = r3 / 1000
                long r3 = (long) r3
                long r3 = r3 + r1
                int r1 = r8.F
                if (r10 != 0) goto L_0x095a
                boolean r2 = r8.H
                if (r2 != 0) goto L_0x095a
                r1 = r1 | 1
            L_0x095a:
                r5 = r1
                int[] r1 = r8.C
                r6 = r1[r10]
                int r0 = r0 - r6
                r1 = r8
                r2 = r9
                r7 = r0
                r1.a(r2, r3, r5, r6, r7)
                int r10 = r10 + 1
                goto L_0x0942
            L_0x0969:
                r0 = 0
                r8.x = r0
            L_0x096c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.InnerEbmlProcessor.c(int):void");
        }
    }

    final class Track {
        public int A;
        public int B;
        public float C;
        public float D;
        public float E;
        public float F;
        public float G;
        public float H;
        public float I;
        public float J;
        public float K;
        public float L;
        public byte[] M;
        public int N;
        public int O;
        public int P;
        public long Q;
        public long R;
        public TrueHdSampleRechunker S;
        public boolean T;
        public boolean U;
        /* access modifiers changed from: package-private */
        public String V;
        public TrackOutput W;
        public int X;
        /* access modifiers changed from: private */
        public int Y;
        public String a;
        public String b;
        public int c;
        public int d;
        public int e;
        public int f;
        public boolean g;
        public byte[] h;
        public TrackOutput.CryptoData i;
        public byte[] j;
        public DrmInitData k;
        public int l;
        public int m;
        public int n;
        public int o;
        public int p;
        public int q;
        public float r;
        public float s;
        public float t;
        public byte[] u;
        public int v;
        public boolean w;
        public int x;
        public int y;
        public int z;

        private Track() {
            this.l = -1;
            this.m = -1;
            this.n = -1;
            this.o = -1;
            this.p = 0;
            this.q = -1;
            this.r = 0.0f;
            this.s = 0.0f;
            this.t = 0.0f;
            this.u = null;
            this.v = -1;
            this.w = false;
            this.x = -1;
            this.y = -1;
            this.z = -1;
            this.A = 1000;
            this.B = 200;
            this.C = -1.0f;
            this.D = -1.0f;
            this.E = -1.0f;
            this.F = -1.0f;
            this.G = -1.0f;
            this.H = -1.0f;
            this.I = -1.0f;
            this.J = -1.0f;
            this.K = -1.0f;
            this.L = -1.0f;
            this.N = 1;
            this.O = -1;
            this.P = 8000;
            this.Q = 0;
            this.R = 0;
            this.U = true;
            this.V = "eng";
        }

        /* synthetic */ Track(byte b2) {
            this();
        }

        static Pair a(ParsableByteArray parsableByteArray) {
            try {
                parsableByteArray.e(16);
                long i2 = parsableByteArray.i();
                if (i2 == 1482049860) {
                    return new Pair("video/divx", (Object) null);
                }
                if (i2 == 859189832) {
                    return new Pair("video/3gpp", (Object) null);
                }
                if (i2 == 826496599) {
                    byte[] bArr = parsableByteArray.a;
                    for (int i3 = parsableByteArray.b + 20; i3 < bArr.length - 4; i3++) {
                        if (bArr[i3] == 0 && bArr[i3 + 1] == 0 && bArr[i3 + 2] == 1 && bArr[i3 + 3] == 15) {
                            return new Pair("video/wvc1", Collections.singletonList(Arrays.copyOfRange(bArr, i3, bArr.length)));
                        }
                    }
                    throw new ParserException("Failed to find FourCC VC1 initialization data");
                }
                Log.c("MatroskaExtractor", "Unknown FourCC. Setting mimeType to video/x-unknown");
                return new Pair("video/x-unknown", (Object) null);
            } catch (ArrayIndexOutOfBoundsException e2) {
                throw new ParserException("Error parsing FourCC private data");
            }
        }

        static List a(byte[] bArr) {
            byte b2;
            byte b3;
            try {
                if (bArr[0] == 2) {
                    int i2 = 1;
                    int i3 = 0;
                    while (true) {
                        b2 = bArr[i2];
                        if ((b2 & Ev3Constants.Opcode.TST) != 255) {
                            break;
                        }
                        i3 += 255;
                        i2++;
                    }
                    int i4 = i2 + 1;
                    int i5 = i3 + (b2 & Ev3Constants.Opcode.TST);
                    int i6 = 0;
                    while (true) {
                        b3 = bArr[i4];
                        if ((b3 & Ev3Constants.Opcode.TST) != 255) {
                            break;
                        }
                        i6 += 255;
                        i4++;
                    }
                    int i7 = i4 + 1;
                    int i8 = i6 + (b3 & Ev3Constants.Opcode.TST);
                    if (bArr[i7] == 1) {
                        byte[] bArr2 = new byte[i5];
                        System.arraycopy(bArr, i7, bArr2, 0, i5);
                        int i9 = i7 + i5;
                        if (bArr[i9] == 3) {
                            int i10 = i9 + i8;
                            if (bArr[i10] == 5) {
                                byte[] bArr3 = new byte[(bArr.length - i10)];
                                System.arraycopy(bArr, i10, bArr3, 0, bArr.length - i10);
                                ArrayList arrayList = new ArrayList(2);
                                arrayList.add(bArr2);
                                arrayList.add(bArr3);
                                return arrayList;
                            }
                            throw new ParserException("Error parsing vorbis codec private");
                        }
                        throw new ParserException("Error parsing vorbis codec private");
                    }
                    throw new ParserException("Error parsing vorbis codec private");
                }
                throw new ParserException("Error parsing vorbis codec private");
            } catch (ArrayIndexOutOfBoundsException e2) {
                throw new ParserException("Error parsing vorbis codec private");
            }
        }

        static boolean b(ParsableByteArray parsableByteArray) {
            try {
                int e2 = parsableByteArray.e();
                if (e2 == 1) {
                    return true;
                }
                if (e2 == 65534) {
                    parsableByteArray.d(24);
                    return parsableByteArray.l() == MatroskaExtractor.L.getMostSignificantBits() && parsableByteArray.l() == MatroskaExtractor.L.getLeastSignificantBits();
                }
            } catch (ArrayIndexOutOfBoundsException e3) {
                throw new ParserException("Error parsing MS/ACM codec private");
            }
        }

        /* access modifiers changed from: package-private */
        public final byte[] a(String str) {
            byte[] bArr = this.j;
            if (bArr != null) {
                return bArr;
            }
            String valueOf = String.valueOf(str);
            throw new ParserException(valueOf.length() != 0 ? "Missing CodecPrivate for codec ".concat(valueOf) : new String("Missing CodecPrivate for codec "));
        }
    }

    final class TrueHdSampleRechunker {
        final byte[] a = new byte[10];
        boolean b;
        int c;
        long d;
        int e;
        int f;
        int g;

        public final void a(Track track) {
            if (this.c > 0) {
                track.W.a(this.d, this.e, this.f, this.g, track.i);
                this.c = 0;
            }
        }
    }

    static {
        ExtractorsFactory extractorsFactory = MatroskaExtractor$$Lambda$0.b;
        HashMap hashMap = new HashMap();
        hashMap.put("htc_video_rotA-000", 0);
        hashMap.put("htc_video_rotA-090", 90);
        hashMap.put("htc_video_rotA-180", 180);
        hashMap.put("htc_video_rotA-270", 270);
        b = Collections.unmodifiableMap(hashMap);
    }

    public MatroskaExtractor() {
        this(0);
    }

    public MatroskaExtractor(int i2) {
        this(new DefaultEbmlReader(), i2);
    }

    private MatroskaExtractor(EbmlReader ebmlReader, int i2) {
        this.j = -1;
        this.k = -9223372036854775807L;
        this.l = -9223372036854775807L;
        this.m = -9223372036854775807L;
        this.s = -1;
        this.V = -1;
        this.t = -9223372036854775807L;
        this.M = ebmlReader;
        boolean z2 = false;
        ebmlReader.a((EbmlProcessor) new InnerEbmlProcessor(this, (byte) 0));
        this.e = (i2 & 1) == 0 ? true : z2;
        this.c = new VarintReader();
        this.d = new SparseArray();
        this.f = new ParsableByteArray(4);
        this.P = new ParsableByteArray(ByteBuffer.allocate(4).putInt(-1).array());
        this.g = new ParsableByteArray(4);
        this.N = new ParsableByteArray(NalUnitUtil.a);
        this.O = new ParsableByteArray(4);
        this.Q = new ParsableByteArray();
        this.R = new ParsableByteArray();
        this.S = new ParsableByteArray(8);
        this.T = new ParsableByteArray();
        this.h = new ParsableByteArray();
        this.C = new int[1];
    }

    protected static int a(int i2) {
        switch (i2) {
            case 131:
            case 136:
            case 155:
            case 159:
            case 176:
            case 179:
            case 186:
            case 215:
            case YaVersion.YOUNG_ANDROID_VERSION /*231*/:
            case 238:
            case LispEscapeFormat.ESCAPE_NORMAL:
            case Telnet.WILL:
            case 16871:
            case 16980:
            case 17029:
            case 17143:
            case 18401:
            case 18408:
            case 20529:
            case 20530:
            case 21420:
            case 21432:
            case 21680:
            case 21682:
            case 21690:
            case 21930:
            case 21945:
            case 21946:
            case 21947:
            case 21948:
            case 21949:
            case 21998:
            case 22186:
            case 22203:
            case 25188:
            case 30321:
            case 2352003:
            case 2807729:
                return 2;
            case 134:
            case 17026:
            case 21358:
            case 2274716:
                return 3;
            case ComponentConstants.TEXTBOX_PREFERRED_WIDTH:
            case 166:
            case 174:
            case 183:
            case 187:
            case 224:
            case 225:
            case 16868:
            case 18407:
            case 19899:
            case 20532:
            case 20533:
            case 21936:
            case 21968:
            case 25152:
            case 28032:
            case 30113:
            case 30320:
            case 290298740:
            case 357149030:
            case 374648427:
            case 408125543:
            case 440786851:
            case 475249515:
            case 524531317:
                return 1;
            case 161:
            case 163:
            case 165:
            case 16877:
            case 16981:
            case 18402:
            case 21419:
            case 25506:
            case 30322:
                return 4;
            case 181:
            case 17545:
            case 21969:
            case 21970:
            case 21971:
            case 21972:
            case 21973:
            case 21974:
            case 21975:
            case 21976:
            case 21977:
            case 21978:
            case 30323:
            case 30324:
            case 30325:
                return 5;
            default:
                return 0;
        }
    }

    private int a(ExtractorInput extractorInput, TrackOutput trackOutput, int i2) {
        int a2 = this.Q.a();
        if (a2 <= 0) {
            return trackOutput.b(extractorInput, i2, false);
        }
        int min = Math.min(i2, a2);
        trackOutput.b(this.Q, min);
        return min;
    }

    private void a(ExtractorInput extractorInput, byte[] bArr, int i2) {
        int length = bArr.length + i2;
        if (this.R.a.length < length) {
            ParsableByteArray parsableByteArray = this.R;
            byte[] copyOf = Arrays.copyOf(bArr, length + i2);
            parsableByteArray.a(copyOf, copyOf.length);
        } else {
            System.arraycopy(bArr, 0, this.R.a, 0, bArr.length);
        }
        extractorInput.b(this.R.a, bArr.length, i2);
        this.R.d(0);
        this.R.c(length);
    }

    private static byte[] a(long j2, String str, long j3) {
        Assertions.a(j2 != -9223372036854775807L);
        int i2 = (int) (j2 / 3600000000L);
        long j4 = j2 - (((long) (i2 * 3600)) * 1000000);
        int i3 = (int) (j4 / 60000000);
        long j5 = j4 - (((long) (i3 * 60)) * 1000000);
        int i4 = (int) (j5 / 1000000);
        return Util.c(String.format(Locale.US, str, new Object[]{Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf((int) ((j5 - (((long) i4) * 1000000)) / j3))}));
    }

    static int[] a(int[] iArr, int i2) {
        return iArr == null ? new int[i2] : iArr.length >= i2 ? iArr : new int[Math.max(iArr.length << 1, i2)];
    }

    protected static boolean b(int i2) {
        return i2 == 357149030 || i2 == 524531317 || i2 == 475249515 || i2 == 374648427;
    }

    static final /* synthetic */ Extractor[] b() {
        return new Extractor[]{new MatroskaExtractor()};
    }

    private int d() {
        int i2 = this.Y;
        e();
        return i2;
    }

    private void e() {
        this.X = 0;
        this.Y = 0;
        this.Z = 0;
        this.aa = false;
        this.ab = false;
        this.ac = false;
        this.ad = 0;
        this.ae = 0;
        this.af = false;
        this.Q.a(0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0039 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0005 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int a(com.google.android.exoplayer2.extractor.ExtractorInput r9, com.google.android.exoplayer2.extractor.PositionHolder r10) {
        /*
            r8 = this;
            r0 = 0
            r8.W = r0
            r1 = 1
            r2 = 1
        L_0x0005:
            if (r2 == 0) goto L_0x003a
            boolean r3 = r8.W
            if (r3 != 0) goto L_0x003a
            com.google.android.exoplayer2.extractor.mkv.EbmlReader r2 = r8.M
            boolean r2 = r2.a((com.google.android.exoplayer2.extractor.ExtractorInput) r9)
            if (r2 == 0) goto L_0x0005
            long r3 = r9.c()
            boolean r5 = r8.r
            if (r5 == 0) goto L_0x0025
            r8.V = r3
            long r3 = r8.s
            r10.a = r3
            r8.r = r0
        L_0x0023:
            r3 = 1
            goto L_0x0037
        L_0x0025:
            boolean r3 = r8.o
            if (r3 == 0) goto L_0x0036
            long r3 = r8.V
            r5 = -1
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x0036
            r10.a = r3
            r8.V = r5
            goto L_0x0023
        L_0x0036:
            r3 = 0
        L_0x0037:
            if (r3 == 0) goto L_0x0005
            return r1
        L_0x003a:
            if (r2 != 0) goto L_0x005d
        L_0x003c:
            android.util.SparseArray r9 = r8.d
            int r9 = r9.size()
            if (r0 >= r9) goto L_0x005b
            android.util.SparseArray r9 = r8.d
            java.lang.Object r9 = r9.valueAt(r0)
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r9 = (com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.Track) r9
            com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r9.W)
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$TrueHdSampleRechunker r10 = r9.S
            if (r10 == 0) goto L_0x0058
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$TrueHdSampleRechunker r10 = r9.S
            r10.a(r9)
        L_0x0058:
            int r0 = r0 + 1
            goto L_0x003c
        L_0x005b:
            r9 = -1
            return r9
        L_0x005d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.a(com.google.android.exoplayer2.extractor.ExtractorInput, com.google.android.exoplayer2.extractor.PositionHolder):int");
    }

    /* access modifiers changed from: package-private */
    public final int a(ExtractorInput extractorInput, Track track, int i2) {
        int i3;
        if ("S_TEXT/UTF8".equals(track.b)) {
            a(extractorInput, J, i2);
        } else if ("S_TEXT/ASS".equals(track.b)) {
            a(extractorInput, K, i2);
        } else {
            TrackOutput trackOutput = track.W;
            if (!this.aa) {
                if (track.g) {
                    this.F &= -1073741825;
                    int i4 = 128;
                    if (!this.ab) {
                        extractorInput.b(this.f.a, 0, 1);
                        this.X++;
                        if ((this.f.a[0] & 128) != 128) {
                            this.ae = this.f.a[0];
                            this.ab = true;
                        } else {
                            throw new ParserException("Extension bit is set in signal byte");
                        }
                    }
                    byte b2 = this.ae;
                    if ((b2 & 1) == 1) {
                        boolean z2 = (b2 & 2) == 2;
                        this.F |= Declaration.MODULE_REFERENCE;
                        if (!this.af) {
                            extractorInput.b(this.S.a, 0, 8);
                            this.X += 8;
                            this.af = true;
                            byte[] bArr = this.f.a;
                            if (!z2) {
                                i4 = 0;
                            }
                            bArr[0] = (byte) (i4 | 8);
                            this.f.d(0);
                            trackOutput.a(this.f, 1);
                            this.Y++;
                            this.S.d(0);
                            trackOutput.a(this.S, 8);
                            this.Y += 8;
                        }
                        if (z2) {
                            if (!this.ac) {
                                extractorInput.b(this.f.a, 0, 1);
                                this.X++;
                                this.f.d(0);
                                this.ad = this.f.c();
                                this.ac = true;
                            }
                            int i5 = this.ad << 2;
                            this.f.a(i5);
                            extractorInput.b(this.f.a, 0, i5);
                            this.X += i5;
                            short s2 = (short) ((this.ad / 2) + 1);
                            int i6 = (s2 * 6) + 2;
                            ByteBuffer byteBuffer = this.U;
                            if (byteBuffer == null || byteBuffer.capacity() < i6) {
                                this.U = ByteBuffer.allocate(i6);
                            }
                            this.U.position(0);
                            this.U.putShort(s2);
                            int i7 = 0;
                            int i8 = 0;
                            while (true) {
                                i3 = this.ad;
                                if (i7 >= i3) {
                                    break;
                                }
                                int o2 = this.f.o();
                                if (i7 % 2 == 0) {
                                    this.U.putShort((short) (o2 - i8));
                                } else {
                                    this.U.putInt(o2 - i8);
                                }
                                i7++;
                                i8 = o2;
                            }
                            int i9 = (i2 - this.X) - i8;
                            int i10 = i3 % 2;
                            ByteBuffer byteBuffer2 = this.U;
                            if (i10 == 1) {
                                byteBuffer2.putInt(i9);
                            } else {
                                byteBuffer2.putShort((short) i9);
                                this.U.putInt(0);
                            }
                            this.T.a(this.U.array(), i6);
                            trackOutput.a(this.T, i6);
                            this.Y += i6;
                        }
                    }
                } else if (track.h != null) {
                    this.Q.a(track.h, track.h.length);
                }
                if (track.f > 0) {
                    this.F |= Declaration.IS_DYNAMIC;
                    this.h.a(0);
                    this.f.a(4);
                    this.f.a[0] = (byte) (i2 >>> 24);
                    this.f.a[1] = (byte) (i2 >> 16);
                    this.f.a[2] = (byte) (i2 >> 8);
                    this.f.a[3] = (byte) i2;
                    trackOutput.a(this.f, 4);
                    this.Y += 4;
                }
                this.aa = true;
            }
            int i11 = i2 + this.Q.c;
            if (!"V_MPEG4/ISO/AVC".equals(track.b) && !"V_MPEGH/ISO/HEVC".equals(track.b)) {
                if (track.S != null) {
                    Assertions.b(this.Q.c == 0);
                    TrueHdSampleRechunker trueHdSampleRechunker = track.S;
                    if (!trueHdSampleRechunker.b) {
                        extractorInput.d(trueHdSampleRechunker.a, 0, 10);
                        extractorInput.a();
                        if (Ac3Util.b(trueHdSampleRechunker.a) != 0) {
                            trueHdSampleRechunker.b = true;
                        }
                    }
                }
                while (true) {
                    int i12 = this.X;
                    if (i12 >= i11) {
                        break;
                    }
                    int a2 = a(extractorInput, trackOutput, i11 - i12);
                    this.X += a2;
                    this.Y += a2;
                }
            } else {
                byte[] bArr2 = this.O.a;
                bArr2[0] = 0;
                bArr2[1] = 0;
                bArr2[2] = 0;
                int i13 = track.X;
                int i14 = 4 - track.X;
                while (this.X < i11) {
                    int i15 = this.Z;
                    if (i15 == 0) {
                        int min = Math.min(i13, this.Q.a());
                        extractorInput.b(bArr2, i14 + min, i13 - min);
                        if (min > 0) {
                            this.Q.a(bArr2, i14, min);
                        }
                        this.X += i13;
                        this.O.d(0);
                        this.Z = this.O.o();
                        this.N.d(0);
                        trackOutput.b(this.N, 4);
                        this.Y += 4;
                    } else {
                        int a3 = a(extractorInput, trackOutput, i15);
                        this.X += a3;
                        this.Y += a3;
                        this.Z -= a3;
                    }
                }
            }
            if ("A_VORBIS".equals(track.b)) {
                this.P.d(0);
                trackOutput.b(this.P, 4);
                this.Y += 4;
            }
            return d();
        }
        return d();
    }

    /* access modifiers changed from: package-private */
    public final long a(long j2) {
        long j3 = this.k;
        if (j3 != -9223372036854775807L) {
            return Util.b(j2, j3, 1000);
        }
        throw new ParserException("Can't scale timecode prior to timecodeScale being set.");
    }

    /* access modifiers changed from: package-private */
    public final void a() {
        Assertions.a((Object) this.I);
    }

    public final void a(long j2, long j3) {
        this.t = -9223372036854775807L;
        this.x = 0;
        this.M.a();
        this.c.a();
        e();
        for (int i2 = 0; i2 < this.d.size(); i2++) {
            Track track = (Track) this.d.valueAt(i2);
            if (track.S != null) {
                TrueHdSampleRechunker trueHdSampleRechunker = track.S;
                trueHdSampleRechunker.b = false;
                trueHdSampleRechunker.c = 0;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(ExtractorInput extractorInput, int i2) {
        if (this.f.c < i2) {
            if (this.f.a.length < i2) {
                ParsableByteArray parsableByteArray = this.f;
                parsableByteArray.b(Math.max(parsableByteArray.a.length << 1, i2));
            }
            extractorInput.b(this.f.a, this.f.c, i2 - this.f.c);
            this.f.c(i2);
        }
    }

    public final void a(ExtractorOutput extractorOutput) {
        this.I = extractorOutput;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00df  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.Track r17, long r18, int r20, int r21, int r22) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r20
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$TrueHdSampleRechunker r3 = r1.S
            r4 = 0
            r5 = 1
            if (r3 == 0) goto L_0x0037
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$TrueHdSampleRechunker r3 = r1.S
            boolean r6 = r3.b
            if (r6 == 0) goto L_0x0101
            int r6 = r3.c
            int r7 = r6 + 1
            r3.c = r7
            if (r6 != 0) goto L_0x0022
            r6 = r18
            r3.d = r6
            r3.e = r2
            r3.f = r4
        L_0x0022:
            int r2 = r3.f
            int r2 = r2 + r21
            r3.f = r2
            r13 = r22
            r3.g = r13
            int r2 = r3.c
            r4 = 16
            if (r2 < r4) goto L_0x0101
            r3.a(r1)
            goto L_0x0101
        L_0x0037:
            r6 = r18
            r13 = r22
            java.lang.String r3 = r1.b
            java.lang.String r8 = "S_TEXT/UTF8"
            boolean r3 = r8.equals(r3)
            java.lang.String r9 = "S_TEXT/ASS"
            if (r3 != 0) goto L_0x004f
            java.lang.String r3 = r1.b
            boolean r3 = r9.equals(r3)
            if (r3 == 0) goto L_0x0069
        L_0x004f:
            int r3 = r0.B
            java.lang.String r10 = "MatroskaExtractor"
            if (r3 <= r5) goto L_0x005b
            java.lang.String r3 = "Skipping subtitle sample in laced block."
        L_0x0057:
            com.google.android.exoplayer2.util.Log.c(r10, r3)
            goto L_0x0069
        L_0x005b:
            long r11 = r0.z
            r14 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r3 = (r11 > r14 ? 1 : (r11 == r14 ? 0 : -1))
            if (r3 != 0) goto L_0x006d
            java.lang.String r3 = "Skipping subtitle sample with no duration."
            goto L_0x0057
        L_0x0069:
            r3 = r21
            goto L_0x00da
        L_0x006d:
            java.lang.String r3 = r1.b
            long r10 = r0.z
            com.google.android.exoplayer2.util.ParsableByteArray r12 = r0.R
            byte[] r12 = r12.a
            int r14 = r3.hashCode()
            switch(r14) {
                case 738597099: goto L_0x0085;
                case 1422270023: goto L_0x007d;
                default: goto L_0x007c;
            }
        L_0x007c:
            goto L_0x008d
        L_0x007d:
            boolean r3 = r3.equals(r8)
            if (r3 == 0) goto L_0x008d
            r3 = 0
            goto L_0x008e
        L_0x0085:
            boolean r3 = r3.equals(r9)
            if (r3 == 0) goto L_0x008d
            r3 = 1
            goto L_0x008e
        L_0x008d:
            r3 = -1
        L_0x008e:
            switch(r3) {
                case 0: goto L_0x00a2;
                case 1: goto L_0x0097;
                default: goto L_0x0091;
            }
        L_0x0091:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            r1.<init>()
            throw r1
        L_0x0097:
            java.lang.String r3 = "%01d:%02d:%02d:%02d"
            r8 = 10000(0x2710, double:4.9407E-320)
            byte[] r3 = a((long) r10, (java.lang.String) r3, (long) r8)
            r8 = 21
            goto L_0x00ac
        L_0x00a2:
            java.lang.String r3 = "%02d:%02d:%02d,%03d"
            r8 = 1000(0x3e8, double:4.94E-321)
            byte[] r3 = a((long) r10, (java.lang.String) r3, (long) r8)
            r8 = 19
        L_0x00ac:
            int r9 = r3.length
            java.lang.System.arraycopy(r3, r4, r12, r8, r9)
            com.google.android.exoplayer2.util.ParsableByteArray r3 = r0.R
            int r3 = r3.b
        L_0x00b4:
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r0.R
            int r4 = r4.c
            if (r3 >= r4) goto L_0x00cb
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r0.R
            byte[] r4 = r4.a
            byte r4 = r4[r3]
            if (r4 != 0) goto L_0x00c8
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r0.R
            r4.c(r3)
            goto L_0x00cb
        L_0x00c8:
            int r3 = r3 + 1
            goto L_0x00b4
        L_0x00cb:
            com.google.android.exoplayer2.extractor.TrackOutput r3 = r1.W
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r0.R
            int r8 = r4.c
            r3.b(r4, r8)
            com.google.android.exoplayer2.util.ParsableByteArray r3 = r0.R
            int r3 = r3.c
            int r3 = r21 + r3
        L_0x00da:
            r4 = 268435456(0x10000000, float:2.5243549E-29)
            r4 = r4 & r2
            if (r4 == 0) goto L_0x00f4
            int r4 = r0.B
            if (r4 <= r5) goto L_0x00e8
            r4 = -268435457(0xffffffffefffffff, float:-1.5845632E29)
            r2 = r2 & r4
            goto L_0x00f4
        L_0x00e8:
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r0.h
            int r4 = r4.c
            com.google.android.exoplayer2.extractor.TrackOutput r8 = r1.W
            com.google.android.exoplayer2.util.ParsableByteArray r9 = r0.h
            r8.a(r9, r4)
            int r3 = r3 + r4
        L_0x00f4:
            r11 = r2
            r12 = r3
            com.google.android.exoplayer2.extractor.TrackOutput r8 = r1.W
            com.google.android.exoplayer2.extractor.TrackOutput$CryptoData r14 = r1.i
            r9 = r18
            r13 = r22
            r8.a(r9, r11, r12, r13, r14)
        L_0x0101:
            r0.W = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.a(com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track, long, int, int, int):void");
    }

    public final boolean a(ExtractorInput extractorInput) {
        ExtractorInput extractorInput2 = extractorInput;
        Sniffer sniffer = new Sniffer();
        long d2 = extractorInput.d();
        long j2 = 1024;
        if (d2 != -1 && d2 <= 1024) {
            j2 = d2;
        }
        int i2 = (int) j2;
        extractorInput2.d(sniffer.a.a, 0, 4);
        long h2 = sniffer.a.h();
        sniffer.b = 4;
        while (true) {
            if (h2 != 440786851) {
                int i3 = sniffer.b + 1;
                sniffer.b = i3;
                if (i3 == i2) {
                    break;
                }
                extractorInput2.d(sniffer.a.a, 0, 1);
                h2 = ((h2 << 8) & -256) | ((long) (sniffer.a.a[0] & Ev3Constants.Opcode.TST));
            } else {
                long a2 = sniffer.a(extractorInput2);
                long j3 = (long) sniffer.b;
                if (a2 != Long.MIN_VALUE && (d2 == -1 || j3 + a2 < d2)) {
                    while (true) {
                        long j4 = j3 + a2;
                        if (((long) sniffer.b) < j4) {
                            if (sniffer.a(extractorInput2) == Long.MIN_VALUE) {
                                break;
                            }
                            long a3 = sniffer.a(extractorInput2);
                            if (a3 < 0 || a3 > 2147483647L) {
                                return false;
                            }
                            if (a3 != 0) {
                                int i4 = (int) a3;
                                extractorInput2.c(i4);
                                sniffer.b += i4;
                            }
                        } else if (((long) sniffer.b) == j4) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final void c(int i2) {
        if (this.n == null) {
            throw new ParserException(new StringBuilder(43).append("Element ").append(i2).append(" must be in a TrackEntry").toString());
        }
    }

    /* access modifiers changed from: package-private */
    public final void d(int i2) {
        if (this.u == null || this.v == null) {
            throw new ParserException(new StringBuilder(37).append("Element ").append(i2).append(" must be in a Cues").toString());
        }
    }

    /* access modifiers changed from: package-private */
    public final Track e(int i2) {
        c(i2);
        return this.n;
    }
}
