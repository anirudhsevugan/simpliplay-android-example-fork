package com.google.android.exoplayer2.text.ssa;

final class SsaDialogueFormat {
    public final int a;
    public final int b;
    public final int c;
    public final int d;
    public final int e;

    private SsaDialogueFormat(int i, int i2, int i3, int i4, int i5) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = i5;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.exoplayer2.text.ssa.SsaDialogueFormat a(java.lang.String r9) {
        /*
            java.lang.String r0 = "Format:"
            boolean r0 = r9.startsWith(r0)
            com.google.android.exoplayer2.util.Assertions.a((boolean) r0)
            r0 = 7
            java.lang.String r9 = r9.substring(r0)
            java.lang.String r0 = ","
            java.lang.String[] r9 = android.text.TextUtils.split(r9, r0)
            r0 = -1
            r1 = 0
            r2 = 0
            r4 = -1
            r5 = -1
            r6 = -1
            r7 = -1
        L_0x001b:
            int r3 = r9.length
            if (r2 >= r3) goto L_0x0067
            r3 = r9[r2]
            java.lang.String r3 = r3.trim()
            java.lang.String r3 = com.dreamers.exoplayercore.repack.C0000a.a((java.lang.String) r3)
            int r8 = r3.hashCode()
            switch(r8) {
                case 100571: goto L_0x004e;
                case 3556653: goto L_0x0044;
                case 109757538: goto L_0x003a;
                case 109780401: goto L_0x0030;
                default: goto L_0x002f;
            }
        L_0x002f:
            goto L_0x0058
        L_0x0030:
            java.lang.String r8 = "style"
            boolean r3 = r3.equals(r8)
            if (r3 == 0) goto L_0x0058
            r3 = 2
            goto L_0x0059
        L_0x003a:
            java.lang.String r8 = "start"
            boolean r3 = r3.equals(r8)
            if (r3 == 0) goto L_0x0058
            r3 = 0
            goto L_0x0059
        L_0x0044:
            java.lang.String r8 = "text"
            boolean r3 = r3.equals(r8)
            if (r3 == 0) goto L_0x0058
            r3 = 3
            goto L_0x0059
        L_0x004e:
            java.lang.String r8 = "end"
            boolean r3 = r3.equals(r8)
            if (r3 == 0) goto L_0x0058
            r3 = 1
            goto L_0x0059
        L_0x0058:
            r3 = -1
        L_0x0059:
            switch(r3) {
                case 0: goto L_0x0063;
                case 1: goto L_0x0061;
                case 2: goto L_0x005f;
                case 3: goto L_0x005d;
                default: goto L_0x005c;
            }
        L_0x005c:
            goto L_0x0064
        L_0x005d:
            r7 = r2
            goto L_0x0064
        L_0x005f:
            r6 = r2
            goto L_0x0064
        L_0x0061:
            r5 = r2
            goto L_0x0064
        L_0x0063:
            r4 = r2
        L_0x0064:
            int r2 = r2 + 1
            goto L_0x001b
        L_0x0067:
            if (r4 == r0) goto L_0x0075
            if (r5 == r0) goto L_0x0075
            if (r7 == r0) goto L_0x0075
            com.google.android.exoplayer2.text.ssa.SsaDialogueFormat r0 = new com.google.android.exoplayer2.text.ssa.SsaDialogueFormat
            int r8 = r9.length
            r3 = r0
            r3.<init>(r4, r5, r6, r7, r8)
            return r0
        L_0x0075:
            r9 = 0
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.ssa.SsaDialogueFormat.a(java.lang.String):com.google.android.exoplayer2.text.ssa.SsaDialogueFormat");
    }
}
