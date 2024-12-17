package com.google.android.exoplayer2.video;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.List;

public final class AvcConfig {
    public final List a;
    public final int b;
    public final int c;
    public final int d;
    public final float e;
    public final String f;

    private AvcConfig(List list, int i, int i2, int i3, float f2, String str) {
        this.a = list;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = f2;
        this.f = str;
    }

    public static AvcConfig a(ParsableByteArray parsableByteArray) {
        String str;
        float f2;
        int i;
        int i2;
        try {
            parsableByteArray.e(4);
            int c2 = (parsableByteArray.c() & 3) + 1;
            if (c2 != 3) {
                ArrayList arrayList = new ArrayList();
                int c3 = parsableByteArray.c() & 31;
                for (int i3 = 0; i3 < c3; i3++) {
                    arrayList.add(b(parsableByteArray));
                }
                int c4 = parsableByteArray.c();
                for (int i4 = 0; i4 < c4; i4++) {
                    arrayList.add(b(parsableByteArray));
                }
                if (c3 > 0) {
                    NalUnitUtil.SpsData a2 = NalUnitUtil.a((byte[]) arrayList.get(0), c2, ((byte[]) arrayList.get(0)).length);
                    int i5 = a2.e;
                    int i6 = a2.f;
                    float f3 = a2.g;
                    str = CodecSpecificDataUtil.a(a2.a, a2.b, a2.c);
                    i2 = i5;
                    i = i6;
                    f2 = f3;
                } else {
                    str = null;
                    i2 = -1;
                    i = -1;
                    f2 = 1.0f;
                }
                return new AvcConfig(arrayList, c2, i2, i, f2, str);
            }
            throw new IllegalStateException();
        } catch (ArrayIndexOutOfBoundsException e2) {
            throw new ParserException("Error parsing AVC config", e2);
        }
    }

    private static byte[] b(ParsableByteArray parsableByteArray) {
        int d2 = parsableByteArray.d();
        int i = parsableByteArray.b;
        parsableByteArray.e(d2);
        return CodecSpecificDataUtil.a(parsableByteArray.a, i, d2);
    }
}
