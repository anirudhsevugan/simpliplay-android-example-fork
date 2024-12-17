package com.google.android.exoplayer2.text.webvtt;

import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.regex.Pattern;

final class CssParser {
    private static final Pattern a = Pattern.compile("\\[voice=\"([^\"]*)\"\\]");
    private final ParsableByteArray b = new ParsableByteArray();
    private final StringBuilder c = new StringBuilder();

    private static String a(ParsableByteArray parsableByteArray, StringBuilder sb) {
        c(parsableByteArray);
        if (parsableByteArray.a() == 0) {
            return null;
        }
        String c2 = c(parsableByteArray, sb);
        if (!"".equals(c2)) {
            return c2;
        }
        return new StringBuilder(1).append((char) parsableByteArray.c()).toString();
    }

    private static String b(ParsableByteArray parsableByteArray) {
        int i = parsableByteArray.b;
        int i2 = parsableByteArray.c;
        boolean z = false;
        while (i < i2 && !z) {
            int i3 = i + 1;
            z = ((char) parsableByteArray.a[i]) == ')';
            i = i3;
        }
        return parsableByteArray.f((i - 1) - parsableByteArray.b).trim();
    }

    private static String b(ParsableByteArray parsableByteArray, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder();
        boolean z = false;
        while (!z) {
            int i = parsableByteArray.b;
            String a2 = a(parsableByteArray, sb);
            if (a2 == null) {
                return null;
            }
            if ("}".equals(a2) || ";".equals(a2)) {
                parsableByteArray.d(i);
                z = true;
            } else {
                sb2.append(a2);
            }
        }
        return sb2.toString();
    }

    private static String c(ParsableByteArray parsableByteArray, StringBuilder sb) {
        boolean z = false;
        sb.setLength(0);
        int i = parsableByteArray.b;
        int i2 = parsableByteArray.c;
        while (i < i2 && !z) {
            char c2 = (char) parsableByteArray.a[i];
            if ((c2 < 'A' || c2 > 'Z') && ((c2 < 'a' || c2 > 'z') && !((c2 >= '0' && c2 <= '9') || c2 == '#' || c2 == '-' || c2 == '.' || c2 == '_'))) {
                z = true;
            } else {
                i++;
                sb.append(c2);
            }
        }
        parsableByteArray.e(i - parsableByteArray.b);
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0058 A[LOOP:1: B:2:0x0002->B:27:0x0058, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0001 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void c(com.google.android.exoplayer2.util.ParsableByteArray r8) {
        /*
            r0 = 1
        L_0x0001:
            r1 = 1
        L_0x0002:
            int r2 = r8.a()
            if (r2 <= 0) goto L_0x005a
            if (r1 == 0) goto L_0x005a
            int r1 = r8.b
            byte[] r2 = r8.a
            byte r1 = r2[r1]
            char r1 = (char) r1
            r2 = 0
            switch(r1) {
                case 9: goto L_0x0017;
                case 10: goto L_0x0017;
                case 12: goto L_0x0017;
                case 13: goto L_0x0017;
                case 32: goto L_0x0017;
                default: goto L_0x0015;
            }
        L_0x0015:
            r1 = 0
            goto L_0x001b
        L_0x0017:
            r8.e(r0)
            r1 = 1
        L_0x001b:
            if (r1 != 0) goto L_0x0001
            int r1 = r8.b
            int r3 = r8.c
            byte[] r4 = r8.a
            int r5 = r1 + 2
            if (r5 > r3) goto L_0x0054
            int r5 = r1 + 1
            byte r1 = r4[r1]
            r6 = 47
            if (r1 != r6) goto L_0x0054
            int r1 = r5 + 1
            byte r5 = r4[r5]
            r7 = 42
            if (r5 != r7) goto L_0x0054
        L_0x0037:
            int r5 = r1 + 1
            if (r5 >= r3) goto L_0x004c
            byte r1 = r4[r1]
            char r1 = (char) r1
            if (r1 != r7) goto L_0x004a
            byte r1 = r4[r5]
            char r1 = (char) r1
            if (r1 != r6) goto L_0x004a
            int r5 = r5 + 1
            r1 = r5
            r3 = r1
            goto L_0x0037
        L_0x004a:
            r1 = r5
            goto L_0x0037
        L_0x004c:
            int r1 = r8.b
            int r3 = r3 - r1
            r8.e(r3)
            r1 = 1
            goto L_0x0055
        L_0x0054:
            r1 = 0
        L_0x0055:
            if (r1 == 0) goto L_0x0058
            goto L_0x0001
        L_0x0058:
            r1 = 0
            goto L_0x0002
        L_0x005a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.webvtt.CssParser.c(com.google.android.exoplayer2.util.ParsableByteArray):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0073, code lost:
        if (")".equals(a(r0, r2)) == false) goto L_0x0038;
     */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0086 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0087  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List a(com.google.android.exoplayer2.util.ParsableByteArray r15) {
        /*
            r14 = this;
            java.lang.StringBuilder r0 = r14.c
            r1 = 0
            r0.setLength(r1)
            int r0 = r15.b
        L_0x0008:
            java.lang.String r2 = r15.s()
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 == 0) goto L_0x0008
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r14.b
            byte[] r3 = r15.a
            int r15 = r15.b
            r2.a((byte[]) r3, (int) r15)
            com.google.android.exoplayer2.util.ParsableByteArray r15 = r14.b
            r15.d(r0)
            java.util.ArrayList r15 = new java.util.ArrayList
            r15.<init>()
        L_0x0025:
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r14.b
            java.lang.StringBuilder r2 = r14.c
            c(r0)
            int r3 = r0.a()
            java.lang.String r4 = "{"
            r5 = 5
            java.lang.String r6 = ""
            r7 = 0
            if (r3 >= r5) goto L_0x003a
        L_0x0038:
            r3 = r7
            goto L_0x0076
        L_0x003a:
            java.lang.String r3 = r0.f(r5)
            java.lang.String r5 = "::cue"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x0047
            goto L_0x0038
        L_0x0047:
            int r3 = r0.b
            java.lang.String r5 = a(r0, r2)
            if (r5 != 0) goto L_0x0050
            goto L_0x0038
        L_0x0050:
            boolean r8 = r4.equals(r5)
            if (r8 == 0) goto L_0x005b
            r0.d(r3)
            r3 = r6
            goto L_0x0076
        L_0x005b:
            java.lang.String r3 = "("
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L_0x0068
            java.lang.String r3 = b(r0)
            goto L_0x0069
        L_0x0068:
            r3 = r7
        L_0x0069:
            java.lang.String r0 = a(r0, r2)
            java.lang.String r2 = ")"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0076
            goto L_0x0038
        L_0x0076:
            if (r3 == 0) goto L_0x0219
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r14.b
            java.lang.StringBuilder r2 = r14.c
            java.lang.String r0 = a(r0, r2)
            boolean r0 = r4.equals(r0)
            if (r0 != 0) goto L_0x0087
            return r15
        L_0x0087:
            com.google.android.exoplayer2.text.webvtt.WebvttCssStyle r0 = new com.google.android.exoplayer2.text.webvtt.WebvttCssStyle
            r0.<init>()
            boolean r2 = r6.equals(r3)
            r4 = 1
            if (r2 != 0) goto L_0x00f2
            r2 = 91
            int r2 = r3.indexOf(r2)
            r5 = -1
            if (r2 == r5) goto L_0x00bc
            java.util.regex.Pattern r8 = a
            java.lang.String r9 = r3.substring(r2)
            java.util.regex.Matcher r8 = r8.matcher(r9)
            boolean r9 = r8.matches()
            if (r9 == 0) goto L_0x00b8
            java.lang.String r8 = r8.group(r4)
            java.lang.Object r8 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r8)
            java.lang.String r8 = (java.lang.String) r8
            r0.d = r8
        L_0x00b8:
            java.lang.String r3 = r3.substring(r1, r2)
        L_0x00bc:
            java.lang.String r2 = "\\."
            java.lang.String[] r2 = com.google.android.exoplayer2.util.Util.a((java.lang.String) r3, (java.lang.String) r2)
            r3 = r2[r1]
            r8 = 35
            int r8 = r3.indexOf(r8)
            if (r8 == r5) goto L_0x00db
            java.lang.String r5 = r3.substring(r1, r8)
            r0.b = r5
            int r8 = r8 + 1
            java.lang.String r3 = r3.substring(r8)
            r0.a = r3
            goto L_0x00dd
        L_0x00db:
            r0.b = r3
        L_0x00dd:
            int r3 = r2.length
            if (r3 <= r4) goto L_0x00f2
            int r3 = r2.length
            java.lang.Object[] r2 = com.google.android.exoplayer2.util.Util.b((java.lang.Object[]) r2, (int) r3)
            java.lang.String[] r2 = (java.lang.String[]) r2
            java.util.HashSet r3 = new java.util.HashSet
            java.util.List r2 = java.util.Arrays.asList(r2)
            r3.<init>(r2)
            r0.c = r3
        L_0x00f2:
            r3 = r7
            r2 = 0
        L_0x00f4:
            java.lang.String r5 = "}"
            if (r2 != 0) goto L_0x020e
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r14.b
            int r2 = r2.b
            com.google.android.exoplayer2.util.ParsableByteArray r3 = r14.b
            java.lang.StringBuilder r8 = r14.c
            java.lang.String r3 = a(r3, r8)
            if (r3 == 0) goto L_0x010f
            boolean r8 = r5.equals(r3)
            if (r8 == 0) goto L_0x010d
            goto L_0x010f
        L_0x010d:
            r8 = 0
            goto L_0x0110
        L_0x010f:
            r8 = 1
        L_0x0110:
            if (r8 != 0) goto L_0x020b
            com.google.android.exoplayer2.util.ParsableByteArray r9 = r14.b
            r9.d(r2)
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r14.b
            java.lang.StringBuilder r9 = r14.c
            c(r2)
            java.lang.String r10 = c(r2, r9)
            boolean r11 = r6.equals(r10)
            if (r11 != 0) goto L_0x020b
            java.lang.String r11 = ":"
            java.lang.String r12 = a(r2, r9)
            boolean r11 = r11.equals(r12)
            if (r11 == 0) goto L_0x020b
            c(r2)
            java.lang.String r11 = b(r2, r9)
            if (r11 == 0) goto L_0x020b
            boolean r12 = r6.equals(r11)
            if (r12 == 0) goto L_0x0145
            goto L_0x020b
        L_0x0145:
            int r12 = r2.b
            java.lang.String r9 = a(r2, r9)
            java.lang.String r13 = ";"
            boolean r13 = r13.equals(r9)
            if (r13 != 0) goto L_0x015c
            boolean r5 = r5.equals(r9)
            if (r5 == 0) goto L_0x020b
            r2.d(r12)
        L_0x015c:
            java.lang.String r2 = "color"
            boolean r2 = r2.equals(r10)
            if (r2 == 0) goto L_0x016e
            int r2 = com.google.android.exoplayer2.util.ColorParser.b(r11)
            r0.f = r2
            r0.g = r4
            goto L_0x020b
        L_0x016e:
            java.lang.String r2 = "background-color"
            boolean r2 = r2.equals(r10)
            if (r2 == 0) goto L_0x0180
            int r2 = com.google.android.exoplayer2.util.ColorParser.b(r11)
            r0.h = r2
            r0.i = r4
            goto L_0x020b
        L_0x0180:
            java.lang.String r2 = "ruby-position"
            boolean r2 = r2.equals(r10)
            if (r2 == 0) goto L_0x01a1
            java.lang.String r2 = "over"
            boolean r2 = r2.equals(r11)
            if (r2 == 0) goto L_0x0194
            r0.o = r4
            goto L_0x020b
        L_0x0194:
            java.lang.String r2 = "under"
            boolean r2 = r2.equals(r11)
            if (r2 == 0) goto L_0x020b
            r2 = 2
            r0.o = r2
            goto L_0x020b
        L_0x01a1:
            java.lang.String r2 = "text-combine-upright"
            boolean r2 = r2.equals(r10)
            if (r2 == 0) goto L_0x01c0
            java.lang.String r2 = "all"
            boolean r2 = r2.equals(r11)
            if (r2 != 0) goto L_0x01bc
            java.lang.String r2 = "digits"
            boolean r2 = r11.startsWith(r2)
            if (r2 == 0) goto L_0x01ba
            goto L_0x01bc
        L_0x01ba:
            r2 = 0
            goto L_0x01bd
        L_0x01bc:
            r2 = 1
        L_0x01bd:
            r0.p = r2
            goto L_0x020b
        L_0x01c0:
            java.lang.String r2 = "text-decoration"
            boolean r2 = r2.equals(r10)
            if (r2 == 0) goto L_0x01d3
            java.lang.String r2 = "underline"
            boolean r2 = r2.equals(r11)
            if (r2 == 0) goto L_0x020b
            r0.k = r4
            goto L_0x020b
        L_0x01d3:
            java.lang.String r2 = "font-family"
            boolean r2 = r2.equals(r10)
            if (r2 == 0) goto L_0x01e6
            if (r11 != 0) goto L_0x01df
            r2 = r7
            goto L_0x01e3
        L_0x01df:
            java.lang.String r2 = com.dreamers.exoplayercore.repack.C0000a.a((java.lang.String) r11)
        L_0x01e3:
            r0.e = r2
            goto L_0x020b
        L_0x01e6:
            java.lang.String r2 = "font-weight"
            boolean r2 = r2.equals(r10)
            if (r2 == 0) goto L_0x01f9
            java.lang.String r2 = "bold"
            boolean r2 = r2.equals(r11)
            if (r2 == 0) goto L_0x020b
            r0.l = r4
            goto L_0x020b
        L_0x01f9:
            java.lang.String r2 = "font-style"
            boolean r2 = r2.equals(r10)
            if (r2 == 0) goto L_0x020b
            java.lang.String r2 = "italic"
            boolean r2 = r2.equals(r11)
            if (r2 == 0) goto L_0x020b
            r0.m = r4
        L_0x020b:
            r2 = r8
            goto L_0x00f4
        L_0x020e:
            boolean r2 = r5.equals(r3)
            if (r2 == 0) goto L_0x0025
            r15.add(r0)
            goto L_0x0025
        L_0x0219:
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.webvtt.CssParser.a(com.google.android.exoplayer2.util.ParsableByteArray):java.util.List");
    }
}
