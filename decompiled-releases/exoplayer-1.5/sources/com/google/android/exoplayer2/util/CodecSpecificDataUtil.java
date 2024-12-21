package com.google.android.exoplayer2.util;

import android.util.Pair;
import java.util.Collections;
import java.util.List;

public final class CodecSpecificDataUtil {
    private static final byte[] a = {0, 0, 0, 1};
    private static final String[] b = {"", "A", "B", "C"};

    public static Pair a(byte[] bArr) {
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr);
        parsableByteArray.d(9);
        int c = parsableByteArray.c();
        parsableByteArray.d(20);
        return Pair.create(Integer.valueOf(parsableByteArray.o()), Integer.valueOf(c));
    }

    public static String a(int i, int i2, int i3) {
        return String.format("avc1.%02X%02X%02X", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
    }

    public static String a(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        parsableNalUnitBitArray.a(24);
        int c = parsableNalUnitBitArray.c(2);
        boolean b2 = parsableNalUnitBitArray.b();
        int c2 = parsableNalUnitBitArray.c(5);
        int i = 0;
        for (int i2 = 0; i2 < 32; i2++) {
            if (parsableNalUnitBitArray.b()) {
                i |= 1 << i2;
            }
        }
        int i3 = 6;
        int[] iArr = new int[6];
        for (int i4 = 0; i4 < 6; i4++) {
            iArr[i4] = parsableNalUnitBitArray.c(8);
        }
        int c3 = parsableNalUnitBitArray.c(8);
        Object[] objArr = new Object[5];
        objArr[0] = b[c];
        objArr[1] = Integer.valueOf(c2);
        objArr[2] = Integer.valueOf(i);
        objArr[3] = Character.valueOf(b2 ? 'H' : 'L');
        objArr[4] = Integer.valueOf(c3);
        StringBuilder sb = new StringBuilder(Util.a("hvc1.%s%d.%X.%c%d", objArr));
        while (i3 > 0 && iArr[i3 - 1] == 0) {
            i3--;
        }
        for (int i5 = 0; i5 < i3; i5++) {
            sb.append(String.format(".%02X", new Object[]{Integer.valueOf(iArr[i5])}));
        }
        return sb.toString();
    }

    public static List a(boolean z) {
        return Collections.singletonList(z ? new byte[]{1} : new byte[]{0});
    }

    public static boolean a(List list) {
        return list.size() == 1 && ((byte[]) list.get(0)).length == 1 && ((byte[]) list.get(0))[0] == 1;
    }

    public static byte[] a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[(i2 + 4)];
        System.arraycopy(a, 0, bArr2, 0, 4);
        System.arraycopy(bArr, i, bArr2, 4, i2);
        return bArr2;
    }
}
