package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.ParsableByteArray;

final class Sniffer {
    private static final int[] a = {1769172845, 1769172786, 1769172787, 1769172788, 1769172789, 1769172790, 1769172793, 1635148593, 1752589105, 1751479857, 1635135537, 1836069937, 1836069938, 862401121, 862401122, 862417462, 862417718, 862414134, 862414646, 1295275552, 1295270176, 1714714144, 1801741417, 1295275600, 1903435808, 1297305174, 1684175153, 1769172332, 1885955686};

    private static boolean a(int i, boolean z) {
        if ((i >>> 8) == 3368816) {
            return true;
        }
        if (i == 1751476579 && z) {
            return true;
        }
        int[] iArr = a;
        for (int i2 = 0; i2 < 29; i2++) {
            if (iArr[i2] == i) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(ExtractorInput extractorInput) {
        return a(extractorInput, true, false);
    }

    public static boolean a(ExtractorInput extractorInput, boolean z) {
        return a(extractorInput, false, false);
    }

    private static boolean a(ExtractorInput extractorInput, boolean z, boolean z2) {
        boolean z3;
        int i;
        ExtractorInput extractorInput2 = extractorInput;
        long d = extractorInput.d();
        long j = 4096;
        long j2 = -1;
        if (d != -1 && d <= 4096) {
            j = d;
        }
        int i2 = (int) j;
        ParsableByteArray parsableByteArray = new ParsableByteArray(64);
        boolean z4 = false;
        int i3 = 0;
        boolean z5 = false;
        while (true) {
            if (i3 >= i2) {
                break;
            }
            parsableByteArray.a(8);
            if (!extractorInput2.b(parsableByteArray.a, z4 ? 1 : 0, 8, true)) {
                break;
            }
            long h = parsableByteArray.h();
            int j3 = parsableByteArray.j();
            if (h == 1) {
                extractorInput2.d(parsableByteArray.a, 8, 8);
                parsableByteArray.c(16);
                h = parsableByteArray.l();
                i = 16;
            } else {
                if (h == 0) {
                    long d2 = extractorInput.d();
                    if (d2 != j2) {
                        h = 8 + (d2 - extractorInput.b());
                    }
                }
                i = 8;
            }
            long j4 = (long) i;
            if (h >= j4) {
                i3 += i;
                if (j3 != 1836019574) {
                    if (j3 != 1836019558 && j3 != 1836475768) {
                        int i4 = i3;
                        if ((((long) i3) + h) - j4 >= ((long) i2)) {
                            break;
                        }
                        int i5 = (int) (h - j4);
                        i3 = i4 + i5;
                        if (j3 != 1718909296) {
                            boolean z6 = z2;
                            if (i5 != 0) {
                                extractorInput2.c(i5);
                            }
                        } else if (i5 < 8) {
                            return false;
                        } else {
                            parsableByteArray.a(i5);
                            extractorInput2.d(parsableByteArray.a, 0, i5);
                            int i6 = i5 / 4;
                            int i7 = 0;
                            while (true) {
                                if (i7 >= i6) {
                                    boolean z7 = z2;
                                    break;
                                }
                                if (i7 == 1) {
                                    parsableByteArray.e(4);
                                    boolean z8 = z2;
                                } else if (a(parsableByteArray.j(), z2)) {
                                    z5 = true;
                                    break;
                                }
                                i7++;
                            }
                            if (!z5) {
                                return false;
                            }
                        }
                        j2 = -1;
                        z4 = false;
                    } else {
                        z3 = true;
                    }
                } else {
                    i2 += (int) h;
                    if (d != j2 && ((long) i2) > d) {
                        i2 = (int) d;
                    }
                }
            } else {
                return z4;
            }
        }
        z3 = false;
        return z5 && z == z3;
    }
}
