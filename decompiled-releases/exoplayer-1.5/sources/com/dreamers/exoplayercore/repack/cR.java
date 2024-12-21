package com.dreamers.exoplayercore.repack;

import java.util.Collection;

public final class cR {
    public static int a(long j) {
        int i = (int) j;
        C0000a.a(((long) i) == j, "Out of range: %s", j);
        return i;
    }

    public static int a(int[] iArr, int i, int i2, int i3) {
        while (i2 < i3) {
            if (iArr[i2] == i) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public static int[] a(Collection collection) {
        if (collection instanceof cS) {
            return ((cS) collection).a();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = ((Number) C0000a.a(array[i])).intValue();
        }
        return iArr;
    }
}
