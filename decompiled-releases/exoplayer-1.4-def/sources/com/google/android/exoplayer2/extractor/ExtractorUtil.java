package com.google.android.exoplayer2.extractor;

final class ExtractorUtil {
    public static int a(ExtractorInput extractorInput, byte[] bArr, int i, int i2) {
        int i3 = 0;
        while (i3 < i2) {
            int c = extractorInput.c(bArr, i + i3, i2 - i3);
            if (c == -1) {
                break;
            }
            i3 += c;
        }
        return i3;
    }
}
