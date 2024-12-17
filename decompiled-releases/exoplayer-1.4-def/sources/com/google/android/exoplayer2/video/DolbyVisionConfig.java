package com.google.android.exoplayer2.video;

import com.google.android.exoplayer2.util.ParsableByteArray;

public final class DolbyVisionConfig {
    public final String a;

    private DolbyVisionConfig(String str) {
        this.a = str;
    }

    public static DolbyVisionConfig a(ParsableByteArray parsableByteArray) {
        String str;
        parsableByteArray.e(2);
        int c = parsableByteArray.c();
        int i = c >> 1;
        int c2 = ((parsableByteArray.c() >> 3) & 31) | ((c & 1) << 5);
        if (i == 4 || i == 5 || i == 7) {
            str = "dvhe";
        } else if (i == 8) {
            str = "hev1";
        } else if (i != 9) {
            return null;
        } else {
            str = "avc3";
        }
        String str2 = c2 < 10 ? ".0" : ".";
        return new DolbyVisionConfig(new StringBuilder(String.valueOf(str).length() + 24 + String.valueOf(str2).length()).append(str).append(".0").append(i).append(str2).append(c2).toString());
    }
}
