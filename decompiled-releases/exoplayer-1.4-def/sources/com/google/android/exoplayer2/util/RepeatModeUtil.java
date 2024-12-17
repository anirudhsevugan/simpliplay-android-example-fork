package com.google.android.exoplayer2.util;

public final class RepeatModeUtil {
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0010, code lost:
        if ((r6 & 2) != 0) goto L_0x0017;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0015, code lost:
        if ((r6 & 1) != 0) goto L_0x0017;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int a(int r5, int r6) {
        /*
            r0 = 1
            r1 = 1
        L_0x0002:
            r2 = 2
            if (r1 > r2) goto L_0x001e
            int r2 = r5 + r1
            int r2 = r2 % 3
            r3 = 0
            switch(r2) {
                case 0: goto L_0x0017;
                case 1: goto L_0x0013;
                case 2: goto L_0x000e;
                default: goto L_0x000d;
            }
        L_0x000d:
            goto L_0x0018
        L_0x000e:
            r4 = r6 & 2
            if (r4 == 0) goto L_0x0018
            goto L_0x0017
        L_0x0013:
            r4 = r6 & 1
            if (r4 == 0) goto L_0x0018
        L_0x0017:
            r3 = 1
        L_0x0018:
            if (r3 == 0) goto L_0x001b
            return r2
        L_0x001b:
            int r1 = r1 + 1
            goto L_0x0002
        L_0x001e:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.util.RepeatModeUtil.a(int, int):int");
    }
}
