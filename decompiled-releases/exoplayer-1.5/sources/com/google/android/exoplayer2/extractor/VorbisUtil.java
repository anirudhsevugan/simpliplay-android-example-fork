package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.Arrays;

public final class VorbisUtil {

    final class CodeBook {
    }

    public final class CommentHeader {
        public final String[] a;

        public CommentHeader(String[] strArr) {
            this.a = strArr;
        }
    }

    public final class Mode {
        public final boolean a;

        public Mode(boolean z) {
            this.a = z;
        }
    }

    public final class VorbisIdHeader {
        public final int a;
        public final int b;
        public final int c;
        public final int d;
        public final int e;
        public final int f;
        public final byte[] g;

        public VorbisIdHeader(int i, int i2, int i3, int i4, int i5, int i6, byte[] bArr) {
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.e = i5;
            this.f = i6;
            this.g = bArr;
        }
    }

    public static int a(int i) {
        int i2 = 0;
        while (i > 0) {
            i2++;
            i >>>= 1;
        }
        return i2;
    }

    public static CommentHeader a(ParsableByteArray parsableByteArray, boolean z, boolean z2) {
        if (z) {
            a(3, parsableByteArray, false);
        }
        parsableByteArray.f((int) parsableByteArray.i()).length();
        long i = parsableByteArray.i();
        String[] strArr = new String[((int) i)];
        for (int i2 = 0; ((long) i2) < i; i2++) {
            String f = parsableByteArray.f((int) parsableByteArray.i());
            strArr[i2] = f;
            f.length();
        }
        if (!z2 || (parsableByteArray.c() & 1) != 0) {
            return new CommentHeader(strArr);
        }
        throw new ParserException("framing bit expected to be set");
    }

    public static VorbisIdHeader a(ParsableByteArray parsableByteArray) {
        a(1, parsableByteArray, false);
        parsableByteArray.p();
        int c = parsableByteArray.c();
        int p = parsableByteArray.p();
        int k = parsableByteArray.k();
        int i = k <= 0 ? -1 : k;
        int k2 = parsableByteArray.k();
        int i2 = k2 <= 0 ? -1 : k2;
        parsableByteArray.k();
        int c2 = parsableByteArray.c();
        parsableByteArray.c();
        return new VorbisIdHeader(c, p, i, i2, (int) Math.pow(2.0d, (double) (c2 & 15)), (int) Math.pow(2.0d, (double) ((c2 & 240) >> 4)), Arrays.copyOf(parsableByteArray.a, parsableByteArray.c));
    }

    private static void a(int i, VorbisBitArray vorbisBitArray) {
        int a = vorbisBitArray.a(6) + 1;
        for (int i2 = 0; i2 < a; i2++) {
            int a2 = vorbisBitArray.a(16);
            if (a2 != 0) {
                Log.d("VorbisUtil", new StringBuilder(52).append("mapping type other than 0 not supported: ").append(a2).toString());
            } else {
                int a3 = vorbisBitArray.a() ? vorbisBitArray.a(4) + 1 : 1;
                if (vorbisBitArray.a()) {
                    int a4 = vorbisBitArray.a(8) + 1;
                    for (int i3 = 0; i3 < a4; i3++) {
                        int i4 = i - 1;
                        vorbisBitArray.b(a(i4));
                        vorbisBitArray.b(a(i4));
                    }
                }
                if (vorbisBitArray.a(2) == 0) {
                    if (a3 > 1) {
                        for (int i5 = 0; i5 < i; i5++) {
                            vorbisBitArray.b(4);
                        }
                    }
                    for (int i6 = 0; i6 < a3; i6++) {
                        vorbisBitArray.b(8);
                        vorbisBitArray.b(8);
                        vorbisBitArray.b(8);
                    }
                } else {
                    throw new ParserException("to reserved bits must be zero after mapping coupling steps");
                }
            }
        }
    }

    public static boolean a(int i, ParsableByteArray parsableByteArray, boolean z) {
        if (parsableByteArray.a() < 7) {
            if (z) {
                return false;
            }
            throw new ParserException(new StringBuilder(29).append("too short header: ").append(parsableByteArray.a()).toString());
        } else if (parsableByteArray.c() != i) {
            if (z) {
                return false;
            }
            String valueOf = String.valueOf(Integer.toHexString(i));
            throw new ParserException(valueOf.length() != 0 ? "expected header type ".concat(valueOf) : new String("expected header type "));
        } else if (parsableByteArray.c() == 118 && parsableByteArray.c() == 111 && parsableByteArray.c() == 114 && parsableByteArray.c() == 98 && parsableByteArray.c() == 105 && parsableByteArray.c() == 115) {
            return true;
        } else {
            if (z) {
                return false;
            }
            throw new ParserException("expected characters 'vorbis'");
        }
    }

    private static Mode[] a(VorbisBitArray vorbisBitArray) {
        int a = vorbisBitArray.a(6) + 1;
        Mode[] modeArr = new Mode[a];
        for (int i = 0; i < a; i++) {
            boolean a2 = vorbisBitArray.a();
            vorbisBitArray.a(16);
            vorbisBitArray.a(16);
            vorbisBitArray.a(8);
            modeArr[i] = new Mode(a2);
        }
        return modeArr;
    }

    public static Mode[] a(ParsableByteArray parsableByteArray, int i) {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int i2 = 5;
        a(5, parsableByteArray, false);
        int c = parsableByteArray.c() + 1;
        VorbisBitArray vorbisBitArray = new VorbisBitArray(parsableByteArray2.a);
        vorbisBitArray.b(parsableByteArray2.b << 3);
        int i3 = 0;
        while (i3 < c) {
            if (vorbisBitArray.a(24) == 5653314) {
                int a = vorbisBitArray.a(16);
                int a2 = vorbisBitArray.a(24);
                long[] jArr = new long[a2];
                long j = 0;
                if (!vorbisBitArray.a()) {
                    boolean a3 = vorbisBitArray.a();
                    for (int i4 = 0; i4 < a2; i4++) {
                        if (!a3 || vorbisBitArray.a()) {
                            jArr[i4] = (long) (vorbisBitArray.a(i2) + 1);
                        } else {
                            jArr[i4] = 0;
                        }
                    }
                } else {
                    int a4 = vorbisBitArray.a(i2) + 1;
                    int i5 = 0;
                    while (i5 < a2) {
                        int a5 = vorbisBitArray.a(a(a2 - i5));
                        for (int i6 = 0; i6 < a5 && i5 < a2; i6++) {
                            jArr[i5] = (long) a4;
                            i5++;
                        }
                        a4++;
                    }
                }
                int a6 = vorbisBitArray.a(4);
                if (a6 <= 2) {
                    if (a6 == 1 || a6 == 2) {
                        vorbisBitArray.b(32);
                        vorbisBitArray.b(32);
                        int a7 = vorbisBitArray.a(4) + 1;
                        vorbisBitArray.b(1);
                        if (a6 != 1) {
                            j = ((long) a2) * ((long) a);
                        } else if (a != 0) {
                            double d = (double) ((long) a);
                            Double.isNaN(d);
                            j = (long) Math.floor(Math.pow((double) ((long) a2), 1.0d / d));
                        }
                        vorbisBitArray.b((int) (j * ((long) a7)));
                    }
                    new CodeBook();
                    i3++;
                    i2 = 5;
                } else {
                    throw new ParserException(new StringBuilder(53).append("lookup type greater than 2 not decodable: ").append(a6).toString());
                }
            } else {
                throw new ParserException(new StringBuilder(66).append("expected code book to start with [0x56, 0x43, 0x42] at ").append((vorbisBitArray.a << 3) + vorbisBitArray.b).toString());
            }
        }
        int a8 = vorbisBitArray.a(6) + 1;
        int i7 = 0;
        while (i7 < a8) {
            if (vorbisBitArray.a(16) == 0) {
                i7++;
            } else {
                throw new ParserException("placeholder of time domain transforms not zeroed out");
            }
        }
        c(vorbisBitArray);
        b(vorbisBitArray);
        a(i, vorbisBitArray);
        Mode[] a9 = a(vorbisBitArray);
        if (vorbisBitArray.a()) {
            return a9;
        }
        throw new ParserException("framing bit after modes not set as expected");
    }

    public static CommentHeader b(ParsableByteArray parsableByteArray) {
        return a(parsableByteArray, true, true);
    }

    private static void b(VorbisBitArray vorbisBitArray) {
        int a = vorbisBitArray.a(6) + 1;
        int i = 0;
        while (i < a) {
            if (vorbisBitArray.a(16) <= 2) {
                vorbisBitArray.b(24);
                vorbisBitArray.b(24);
                vorbisBitArray.b(24);
                int a2 = vorbisBitArray.a(6) + 1;
                vorbisBitArray.b(8);
                int[] iArr = new int[a2];
                for (int i2 = 0; i2 < a2; i2++) {
                    iArr[i2] = ((vorbisBitArray.a() ? vorbisBitArray.a(5) : 0) << 3) + vorbisBitArray.a(3);
                }
                for (int i3 = 0; i3 < a2; i3++) {
                    for (int i4 = 0; i4 < 8; i4++) {
                        if ((iArr[i3] & (1 << i4)) != 0) {
                            vorbisBitArray.b(8);
                        }
                    }
                }
                i++;
            } else {
                throw new ParserException("residueType greater than 2 is not decodable");
            }
        }
    }

    private static void c(VorbisBitArray vorbisBitArray) {
        int a = vorbisBitArray.a(6) + 1;
        for (int i = 0; i < a; i++) {
            int a2 = vorbisBitArray.a(16);
            switch (a2) {
                case 0:
                    vorbisBitArray.b(8);
                    vorbisBitArray.b(16);
                    vorbisBitArray.b(16);
                    vorbisBitArray.b(6);
                    vorbisBitArray.b(8);
                    int a3 = vorbisBitArray.a(4) + 1;
                    for (int i2 = 0; i2 < a3; i2++) {
                        vorbisBitArray.b(8);
                    }
                    break;
                case 1:
                    int a4 = vorbisBitArray.a(5);
                    int[] iArr = new int[a4];
                    int i3 = -1;
                    for (int i4 = 0; i4 < a4; i4++) {
                        int a5 = vorbisBitArray.a(4);
                        iArr[i4] = a5;
                        if (a5 > i3) {
                            i3 = a5;
                        }
                    }
                    int i5 = i3 + 1;
                    int[] iArr2 = new int[i5];
                    for (int i6 = 0; i6 < i5; i6++) {
                        iArr2[i6] = vorbisBitArray.a(3) + 1;
                        int a6 = vorbisBitArray.a(2);
                        if (a6 > 0) {
                            vorbisBitArray.b(8);
                        }
                        for (int i7 = 0; i7 < (1 << a6); i7++) {
                            vorbisBitArray.b(8);
                        }
                    }
                    vorbisBitArray.b(2);
                    int a7 = vorbisBitArray.a(4);
                    int i8 = 0;
                    int i9 = 0;
                    for (int i10 = 0; i10 < a4; i10++) {
                        i8 += iArr2[iArr[i10]];
                        while (i9 < i8) {
                            vorbisBitArray.b(a7);
                            i9++;
                        }
                    }
                    break;
                default:
                    throw new ParserException(new StringBuilder(52).append("floor type greater than 1 not decodable: ").append(a2).toString());
            }
        }
    }
}
