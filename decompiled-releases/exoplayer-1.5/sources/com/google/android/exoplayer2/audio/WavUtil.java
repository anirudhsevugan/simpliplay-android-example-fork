package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.util.Util;

public final class WavUtil {
    public static int a(int i, int i2) {
        switch (i) {
            case 1:
            case 65534:
                return Util.b(i2);
            case 3:
                return i2 == 32 ? 4 : 0;
            default:
                return 0;
        }
    }
}
