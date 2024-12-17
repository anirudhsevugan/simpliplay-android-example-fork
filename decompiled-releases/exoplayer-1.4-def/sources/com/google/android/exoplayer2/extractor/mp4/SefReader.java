package com.google.android.exoplayer2.extractor.mp4;

import com.dreamers.exoplayercore.repack.C0024ax;
import com.dreamers.exoplayercore.repack.aF;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.metadata.mp4.SlowMotionData;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.List;

final class SefReader {
    private static final aF d = aF.a(C0024ax.a(':'));
    private static final aF e = aF.a(C0024ax.a('*'));
    final List a = new ArrayList();
    int b = 0;
    int c;

    final class DataReference {
        public final long a;
        public final int b;

        public DataReference(long j, int i) {
            this.a = j;
            this.b = i;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int a(java.lang.String r1) {
        /*
            int r0 = r1.hashCode()
            switch(r0) {
                case -1711564334: goto L_0x0030;
                case -1332107749: goto L_0x0026;
                case -1251387154: goto L_0x001c;
                case -830665521: goto L_0x0012;
                case 1760745220: goto L_0x0008;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x003a
        L_0x0008:
            java.lang.String r0 = "Super_SlowMotion_BGM"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x003a
            r1 = 2
            goto L_0x003b
        L_0x0012:
            java.lang.String r0 = "Super_SlowMotion_Deflickering_On"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x003a
            r1 = 4
            goto L_0x003b
        L_0x001c:
            java.lang.String r0 = "Super_SlowMotion_Data"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x003a
            r1 = 1
            goto L_0x003b
        L_0x0026:
            java.lang.String r0 = "Super_SlowMotion_Edit_Data"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x003a
            r1 = 3
            goto L_0x003b
        L_0x0030:
            java.lang.String r0 = "SlowMotion_Data"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x003a
            r1 = 0
            goto L_0x003b
        L_0x003a:
            r1 = -1
        L_0x003b:
            switch(r1) {
                case 0: goto L_0x0052;
                case 1: goto L_0x004f;
                case 2: goto L_0x004c;
                case 3: goto L_0x0049;
                case 4: goto L_0x0046;
                default: goto L_0x003e;
            }
        L_0x003e:
            com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
            java.lang.String r0 = "Invalid SEF name"
            r1.<init>((java.lang.String) r0)
            throw r1
        L_0x0046:
            r1 = 2820(0xb04, float:3.952E-42)
            return r1
        L_0x0049:
            r1 = 2819(0xb03, float:3.95E-42)
            return r1
        L_0x004c:
            r1 = 2817(0xb01, float:3.947E-42)
            return r1
        L_0x004f:
            r1 = 2816(0xb00, float:3.946E-42)
            return r1
        L_0x0052:
            r1 = 2192(0x890, float:3.072E-42)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp4.SefReader.a(java.lang.String):int");
    }

    static SlowMotionData a(ParsableByteArray parsableByteArray, int i) {
        ArrayList arrayList = new ArrayList();
        List a2 = e.a((CharSequence) parsableByteArray.f(i));
        int i2 = 0;
        while (i2 < a2.size()) {
            List a3 = d.a((CharSequence) a2.get(i2));
            if (a3.size() == 3) {
                try {
                    arrayList.add(new SlowMotionData.Segment(Long.parseLong((String) a3.get(0)), Long.parseLong((String) a3.get(1)), 1 << (Integer.parseInt((String) a3.get(2)) - 1)));
                    i2++;
                } catch (NumberFormatException e2) {
                    throw new ParserException((Throwable) e2);
                }
            } else {
                throw new ParserException();
            }
        }
        return new SlowMotionData(arrayList);
    }

    public final void a() {
        this.a.clear();
        this.b = 0;
    }
}
