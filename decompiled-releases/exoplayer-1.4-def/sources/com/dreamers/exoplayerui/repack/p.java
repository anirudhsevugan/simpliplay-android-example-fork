package com.dreamers.exoplayerui.repack;

import com.google.appinventor.components.common.PropertyTypeConstants;

public class p {
    private p() {
    }

    public /* synthetic */ p(byte b) {
        this();
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x007b A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static /* synthetic */ int a(java.lang.CharSequence r8, java.lang.CharSequence r9, int r10) {
        /*
            com.dreamers.exoplayerui.repack.r r0 = new com.dreamers.exoplayerui.repack.r
            int r1 = r8.length()
            if (r10 <= r1) goto L_0x0009
            r10 = r1
        L_0x0009:
            r1 = 0
            r0.<init>(r1, r10)
            com.dreamers.exoplayerui.repack.o r0 = (com.dreamers.exoplayerui.repack.o) r0
            boolean r10 = r8 instanceof java.lang.String
            java.lang.String r2 = "other"
            if (r10 == 0) goto L_0x003d
            boolean r10 = r9 instanceof java.lang.String
            if (r10 == 0) goto L_0x003d
            int r10 = r0.a
            int r0 = r0.b
            if (r10 > r0) goto L_0x0081
        L_0x001f:
            r3 = r9
            java.lang.String r3 = (java.lang.String) r3
            r4 = r8
            java.lang.String r4 = (java.lang.String) r4
            int r5 = r9.length()
            java.lang.String r6 = "$this$regionMatches"
            com.dreamers.exoplayerui.repack.n.b(r3, r6)
            com.dreamers.exoplayerui.repack.n.b(r4, r2)
            boolean r3 = r3.regionMatches(r1, r4, r10, r5)
            if (r3 == 0) goto L_0x0038
            return r10
        L_0x0038:
            if (r10 == r0) goto L_0x0081
            int r10 = r10 + 1
            goto L_0x001f
        L_0x003d:
            int r10 = r0.a
            int r0 = r0.b
            if (r10 > r0) goto L_0x0081
        L_0x0043:
            int r3 = r9.length()
            java.lang.String r4 = "$this$regionMatchesImpl"
            com.dreamers.exoplayerui.repack.n.b(r9, r4)
            com.dreamers.exoplayerui.repack.n.b(r8, r2)
            if (r10 < 0) goto L_0x0078
            int r4 = r9.length()
            int r4 = r4 - r3
            if (r4 < 0) goto L_0x0078
            int r4 = r8.length()
            int r4 = r4 - r3
            if (r10 <= r4) goto L_0x0060
            goto L_0x0078
        L_0x0060:
            r4 = 0
        L_0x0061:
            r5 = 1
            if (r4 >= r3) goto L_0x0079
            char r6 = r9.charAt(r4)
            int r7 = r10 + r4
            char r7 = r8.charAt(r7)
            if (r6 != r7) goto L_0x0071
            goto L_0x0072
        L_0x0071:
            r5 = 0
        L_0x0072:
            if (r5 != 0) goto L_0x0075
            goto L_0x0078
        L_0x0075:
            int r4 = r4 + 1
            goto L_0x0061
        L_0x0078:
            r5 = 0
        L_0x0079:
            if (r5 == 0) goto L_0x007c
            return r10
        L_0x007c:
            if (r10 == r0) goto L_0x0081
            int r10 = r10 + 1
            goto L_0x0043
        L_0x0081:
            r8 = -1
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dreamers.exoplayerui.repack.p.a(java.lang.CharSequence, java.lang.CharSequence, int):int");
    }

    public static boolean a(CharSequence charSequence, CharSequence charSequence2) {
        n.b(charSequence, "$this$contains");
        n.b(charSequence2, "other");
        String str = (String) charSequence2;
        n.b(charSequence, "$this$indexOf");
        n.b(str, PropertyTypeConstants.PROPERTY_TYPE_STRING);
        return (!(charSequence instanceof String) ? a(charSequence, str, charSequence.length()) : ((String) charSequence).indexOf(str, 0)) >= 0;
    }
}
