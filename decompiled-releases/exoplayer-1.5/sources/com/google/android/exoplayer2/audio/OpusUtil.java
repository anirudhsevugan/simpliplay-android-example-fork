package com.google.android.exoplayer2.audio;

import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class OpusUtil {
    public static int a(byte[] bArr) {
        return bArr[9] & Ev3Constants.Opcode.TST;
    }

    private static byte[] a(long j) {
        return ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong(j).array();
    }

    private static long b(long j) {
        return (j * 1000000000) / 48000;
    }

    public static List b(byte[] bArr) {
        long b = b((long) (((bArr[11] & Ev3Constants.Opcode.TST) << 8) | (bArr[10] & Ev3Constants.Opcode.TST)));
        long b2 = b(3840);
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(bArr);
        arrayList.add(a(b));
        arrayList.add(a(b2));
        return arrayList;
    }
}
