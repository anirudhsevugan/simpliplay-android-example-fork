package com.google.android.exoplayer2.text.ttml;

import com.dreamers.exoplayercore.repack.bU;
import java.util.regex.Pattern;

final class TextEmphasis {
    private static final Pattern d = Pattern.compile("\\s+");
    private static final bU e = bU.a((Object) "auto", (Object) "none");
    private static final bU f = bU.a("dot", "sesame", "circle");
    private static final bU g = bU.a((Object) "filled", (Object) "open");
    private static final bU h = bU.a("after", "before", "outside");
    public final int a;
    public final int b;
    public final int c;

    private TextEmphasis(int i, int i2, int i3) {
        this.a = i;
        this.b = i2;
        this.c = i3;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x008a, code lost:
        if (r8.equals("auto") != false) goto L_0x008e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.exoplayer2.text.ttml.TextEmphasis a(java.lang.String r8) {
        /*
            r0 = 0
            if (r8 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.String r8 = r8.trim()
            java.lang.String r8 = com.dreamers.exoplayercore.repack.C0000a.a((java.lang.String) r8)
            boolean r1 = r8.isEmpty()
            if (r1 == 0) goto L_0x0013
            return r0
        L_0x0013:
            java.util.regex.Pattern r0 = d
            java.lang.String[] r8 = android.text.TextUtils.split(r8, r0)
            com.dreamers.exoplayercore.repack.bU r8 = com.dreamers.exoplayercore.repack.bU.a((java.lang.Object[]) r8)
            com.dreamers.exoplayercore.repack.bU r0 = h
            com.dreamers.exoplayercore.repack.cJ r0 = com.dreamers.exoplayercore.repack.cF.a((java.util.Set) r0, (java.util.Set) r8)
            java.lang.String r1 = "outside"
            java.lang.Object r0 = com.dreamers.exoplayercore.repack.C0000a.a((java.lang.Iterable) r0, (java.lang.Object) r1)
            java.lang.String r0 = (java.lang.String) r0
            int r2 = r0.hashCode()
            r3 = 2
            r4 = -1
            r5 = 0
            r6 = 1
            switch(r2) {
                case -1392885889: goto L_0x0049;
                case -1106037339: goto L_0x0041;
                case 92734940: goto L_0x0037;
                default: goto L_0x0036;
            }
        L_0x0036:
            goto L_0x0053
        L_0x0037:
            java.lang.String r1 = "after"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0053
            r0 = 0
            goto L_0x0054
        L_0x0041:
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0053
            r0 = 1
            goto L_0x0054
        L_0x0049:
            java.lang.String r1 = "before"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0053
            r0 = 2
            goto L_0x0054
        L_0x0053:
            r0 = -1
        L_0x0054:
            switch(r0) {
                case 0: goto L_0x005b;
                case 1: goto L_0x0059;
                default: goto L_0x0057;
            }
        L_0x0057:
            r0 = 1
            goto L_0x005c
        L_0x0059:
            r0 = -2
            goto L_0x005c
        L_0x005b:
            r0 = 2
        L_0x005c:
            com.dreamers.exoplayercore.repack.bU r1 = e
            com.dreamers.exoplayercore.repack.cJ r1 = com.dreamers.exoplayercore.repack.cF.a((java.util.Set) r1, (java.util.Set) r8)
            boolean r2 = r1.isEmpty()
            if (r2 != 0) goto L_0x0099
            java.util.Iterator r8 = r1.iterator()
            java.lang.Object r8 = r8.next()
            java.lang.String r8 = (java.lang.String) r8
            int r1 = r8.hashCode()
            switch(r1) {
                case 3005871: goto L_0x0084;
                case 3387192: goto L_0x007a;
                default: goto L_0x0079;
            }
        L_0x0079:
            goto L_0x008d
        L_0x007a:
            java.lang.String r1 = "none"
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L_0x008d
            r6 = 0
            goto L_0x008e
        L_0x0084:
            java.lang.String r1 = "auto"
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L_0x008d
            goto L_0x008e
        L_0x008d:
            r6 = -1
        L_0x008e:
            switch(r6) {
                case 0: goto L_0x0092;
                default: goto L_0x0091;
            }
        L_0x0091:
            goto L_0x0093
        L_0x0092:
            r4 = 0
        L_0x0093:
            com.google.android.exoplayer2.text.ttml.TextEmphasis r8 = new com.google.android.exoplayer2.text.ttml.TextEmphasis
            r8.<init>(r4, r5, r0)
            return r8
        L_0x0099:
            com.dreamers.exoplayercore.repack.bU r1 = g
            com.dreamers.exoplayercore.repack.cJ r1 = com.dreamers.exoplayercore.repack.cF.a((java.util.Set) r1, (java.util.Set) r8)
            com.dreamers.exoplayercore.repack.bU r2 = f
            com.dreamers.exoplayercore.repack.cJ r8 = com.dreamers.exoplayercore.repack.cF.a((java.util.Set) r2, (java.util.Set) r8)
            boolean r2 = r1.isEmpty()
            if (r2 == 0) goto L_0x00b7
            boolean r2 = r8.isEmpty()
            if (r2 == 0) goto L_0x00b7
            com.google.android.exoplayer2.text.ttml.TextEmphasis r8 = new com.google.android.exoplayer2.text.ttml.TextEmphasis
            r8.<init>(r4, r5, r0)
            return r8
        L_0x00b7:
            java.lang.String r2 = "filled"
            java.lang.Object r1 = com.dreamers.exoplayercore.repack.C0000a.a((java.lang.Iterable) r1, (java.lang.Object) r2)
            java.lang.String r1 = (java.lang.String) r1
            int r7 = r1.hashCode()
            switch(r7) {
                case -1274499742: goto L_0x00d1;
                case 3417674: goto L_0x00c7;
                default: goto L_0x00c6;
            }
        L_0x00c6:
            goto L_0x00d9
        L_0x00c7:
            java.lang.String r2 = "open"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x00d9
            r1 = 0
            goto L_0x00da
        L_0x00d1:
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x00d9
            r1 = 1
            goto L_0x00da
        L_0x00d9:
            r1 = -1
        L_0x00da:
            switch(r1) {
                case 0: goto L_0x00df;
                default: goto L_0x00dd;
            }
        L_0x00dd:
            r1 = 1
            goto L_0x00e0
        L_0x00df:
            r1 = 2
        L_0x00e0:
            java.lang.String r2 = "circle"
            java.lang.Object r8 = com.dreamers.exoplayercore.repack.C0000a.a((java.lang.Iterable) r8, (java.lang.Object) r2)
            java.lang.String r8 = (java.lang.String) r8
            int r7 = r8.hashCode()
            switch(r7) {
                case -1360216880: goto L_0x0104;
                case -905816648: goto L_0x00fa;
                case 99657: goto L_0x00f0;
                default: goto L_0x00ef;
            }
        L_0x00ef:
            goto L_0x010b
        L_0x00f0:
            java.lang.String r2 = "dot"
            boolean r8 = r8.equals(r2)
            if (r8 == 0) goto L_0x010b
            r4 = 0
            goto L_0x010b
        L_0x00fa:
            java.lang.String r2 = "sesame"
            boolean r8 = r8.equals(r2)
            if (r8 == 0) goto L_0x010b
            r4 = 1
            goto L_0x010b
        L_0x0104:
            boolean r8 = r8.equals(r2)
            if (r8 == 0) goto L_0x010b
            r4 = 2
        L_0x010b:
            switch(r4) {
                case 0: goto L_0x0111;
                case 1: goto L_0x0110;
                default: goto L_0x010e;
            }
        L_0x010e:
            r3 = 1
            goto L_0x0111
        L_0x0110:
            r3 = 3
        L_0x0111:
            com.google.android.exoplayer2.text.ttml.TextEmphasis r8 = new com.google.android.exoplayer2.text.ttml.TextEmphasis
            r8.<init>(r3, r1, r0)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.ttml.TextEmphasis.a(java.lang.String):com.google.android.exoplayer2.text.ttml.TextEmphasis");
    }
}
