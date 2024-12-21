package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;

public final class CeaUtil {
    private static int a(ParsableByteArray parsableByteArray) {
        int i = 0;
        while (parsableByteArray.a() != 0) {
            int c = parsableByteArray.c();
            i += c;
            if (c != 255) {
                return i;
            }
        }
        return -1;
    }

    public static void a(long j, ParsableByteArray parsableByteArray, TrackOutput[] trackOutputArr) {
        while (true) {
            boolean z = true;
            if (parsableByteArray.a() > 1) {
                int a = a(parsableByteArray);
                int a2 = a(parsableByteArray);
                int i = parsableByteArray.b + a2;
                if (a2 == -1 || a2 > parsableByteArray.a()) {
                    Log.c("CeaUtil", "Skipping remainder of malformed SEI NAL unit.");
                    i = parsableByteArray.c;
                } else if (a == 4 && a2 >= 8) {
                    int c = parsableByteArray.c();
                    int d = parsableByteArray.d();
                    int j2 = d == 49 ? parsableByteArray.j() : 0;
                    int c2 = parsableByteArray.c();
                    if (d == 47) {
                        parsableByteArray.e(1);
                    }
                    boolean z2 = c == 181 && (d == 49 || d == 47) && c2 == 3;
                    if (d == 49) {
                        if (j2 != 1195456820) {
                            z = false;
                        }
                        z2 &= z;
                    }
                    if (z2) {
                        b(j, parsableByteArray, trackOutputArr);
                    }
                }
                parsableByteArray.d(i);
            } else {
                return;
            }
        }
    }

    public static void b(long j, ParsableByteArray parsableByteArray, TrackOutput[] trackOutputArr) {
        int c = parsableByteArray.c();
        if ((c & 64) != 0) {
            parsableByteArray.e(1);
            int i = (c & 31) * 3;
            int i2 = parsableByteArray.b;
            for (TrackOutput trackOutput : trackOutputArr) {
                parsableByteArray.d(i2);
                trackOutput.b(parsableByteArray, i);
                trackOutput.a(j, 1, i, 0, (TrackOutput.CryptoData) null);
            }
        }
    }
}
