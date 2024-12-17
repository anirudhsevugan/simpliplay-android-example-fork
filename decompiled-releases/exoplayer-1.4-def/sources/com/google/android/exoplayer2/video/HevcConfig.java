package com.google.android.exoplayer2.video;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.ParsableNalUnitBitArray;
import java.util.Collections;
import java.util.List;

public final class HevcConfig {
    public final List a;
    public final int b;
    public final String c;

    private HevcConfig(List list, int i, String str) {
        this.a = list;
        this.b = i;
        this.c = str;
    }

    public static HevcConfig a(ParsableByteArray parsableByteArray) {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        try {
            parsableByteArray2.e(21);
            int c2 = parsableByteArray.c() & 3;
            int c3 = parsableByteArray.c();
            int i = parsableByteArray2.b;
            int i2 = 0;
            for (int i3 = 0; i3 < c3; i3++) {
                parsableByteArray2.e(1);
                int d = parsableByteArray.d();
                for (int i4 = 0; i4 < d; i4++) {
                    int d2 = parsableByteArray.d();
                    i2 += d2 + 4;
                    parsableByteArray2.e(d2);
                }
            }
            parsableByteArray2.d(i);
            byte[] bArr = new byte[i2];
            String str = null;
            int i5 = 0;
            for (int i6 = 0; i6 < c3; i6++) {
                int c4 = parsableByteArray.c() & 127;
                int d3 = parsableByteArray.d();
                for (int i7 = 0; i7 < d3; i7++) {
                    int d4 = parsableByteArray.d();
                    byte[] bArr2 = NalUnitUtil.a;
                    byte[] bArr3 = NalUnitUtil.a;
                    System.arraycopy(bArr2, 0, bArr, i5, 4);
                    byte[] bArr4 = NalUnitUtil.a;
                    int i8 = i5 + 4;
                    System.arraycopy(parsableByteArray2.a, parsableByteArray2.b, bArr, i8, d4);
                    if (c4 == 33 && i7 == 0) {
                        str = CodecSpecificDataUtil.a(new ParsableNalUnitBitArray(bArr, i8, i8 + d4));
                    }
                    i5 = i8 + d4;
                    parsableByteArray2.e(d4);
                }
            }
            return new HevcConfig(i2 == 0 ? null : Collections.singletonList(bArr), c2 + 1, str);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParserException("Error parsing HEVC config", e);
        }
    }
}
