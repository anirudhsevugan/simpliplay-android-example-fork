package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.extractor.TrackOutput;

public final class TrackEncryptionBox {
    public final boolean a;
    public final String b;
    public final TrackOutput.CryptoData c;
    public final int d;
    public final byte[] e;

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0039, code lost:
        if (r6.equals("cenc") != false) goto L_0x0051;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TrackEncryptionBox(boolean r5, java.lang.String r6, int r7, byte[] r8, int r9, int r10, byte[] r11) {
        /*
            r4 = this;
            r4.<init>()
            r0 = 0
            r1 = 1
            if (r7 != 0) goto L_0x0009
            r2 = 1
            goto L_0x000a
        L_0x0009:
            r2 = 0
        L_0x000a:
            if (r11 != 0) goto L_0x000e
            r3 = 1
            goto L_0x000f
        L_0x000e:
            r3 = 0
        L_0x000f:
            r2 = r2 ^ r3
            com.google.android.exoplayer2.util.Assertions.a((boolean) r2)
            r4.a = r5
            r4.b = r6
            r4.d = r7
            r4.e = r11
            com.google.android.exoplayer2.extractor.TrackOutput$CryptoData r5 = new com.google.android.exoplayer2.extractor.TrackOutput$CryptoData
            if (r6 != 0) goto L_0x0020
            goto L_0x007e
        L_0x0020:
            int r7 = r6.hashCode()
            r11 = 2
            switch(r7) {
                case 3046605: goto L_0x0046;
                case 3046671: goto L_0x003c;
                case 3049879: goto L_0x0033;
                case 3049895: goto L_0x0029;
                default: goto L_0x0028;
            }
        L_0x0028:
            goto L_0x0050
        L_0x0029:
            java.lang.String r7 = "cens"
            boolean r7 = r6.equals(r7)
            if (r7 == 0) goto L_0x0050
            r0 = 1
            goto L_0x0051
        L_0x0033:
            java.lang.String r7 = "cenc"
            boolean r7 = r6.equals(r7)
            if (r7 == 0) goto L_0x0050
            goto L_0x0051
        L_0x003c:
            java.lang.String r7 = "cbcs"
            boolean r7 = r6.equals(r7)
            if (r7 == 0) goto L_0x0050
            r0 = 3
            goto L_0x0051
        L_0x0046:
            java.lang.String r7 = "cbc1"
            boolean r7 = r6.equals(r7)
            if (r7 == 0) goto L_0x0050
            r0 = 2
            goto L_0x0051
        L_0x0050:
            r0 = -1
        L_0x0051:
            switch(r0) {
                case 0: goto L_0x007e;
                case 1: goto L_0x007e;
                case 2: goto L_0x007d;
                case 3: goto L_0x007d;
                default: goto L_0x0054;
            }
        L_0x0054:
            java.lang.String r7 = java.lang.String.valueOf(r6)
            int r7 = r7.length()
            int r7 = r7 + 68
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>(r7)
            java.lang.String r7 = "Unsupported protection scheme type '"
            java.lang.StringBuilder r7 = r11.append(r7)
            java.lang.StringBuilder r6 = r7.append(r6)
            java.lang.String r7 = "'. Assuming AES-CTR crypto mode."
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r6 = r6.toString()
            java.lang.String r7 = "TrackEncryptionBox"
            com.google.android.exoplayer2.util.Log.c(r7, r6)
            goto L_0x007e
        L_0x007d:
            r1 = 2
        L_0x007e:
            r5.<init>(r1, r8, r9, r10)
            r4.c = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox.<init>(boolean, java.lang.String, int, byte[], int, int, byte[]):void");
    }
}
