package com.google.android.exoplayer2.text.subrip;

import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.util.Assertions;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SubripDecoder extends SimpleSubtitleDecoder {
    private static final Pattern d = Pattern.compile("\\s*((?:(\\d+):)?(\\d+):(\\d+)(?:,(\\d+))?)\\s*-->\\s*((?:(\\d+):)?(\\d+):(\\d+)(?:,(\\d+))?)\\s*");
    private static final Pattern e = Pattern.compile("\\{\\\\.*?\\}");
    private final StringBuilder f = new StringBuilder();
    private final ArrayList g = new ArrayList();

    private static float a(int i) {
        switch (i) {
            case 0:
                return 0.08f;
            case 1:
                return 0.5f;
            case 2:
                return 0.92f;
            default:
                throw new IllegalArgumentException();
        }
    }

    private static long a(Matcher matcher, int i) {
        String group = matcher.group(i + 1);
        long parseLong = (group != null ? Long.parseLong(group) * 60 * 60 * 1000 : 0) + (Long.parseLong((String) Assertions.b((Object) matcher.group(i + 2))) * 60 * 1000) + (Long.parseLong((String) Assertions.b((Object) matcher.group(i + 3))) * 1000);
        String group2 = matcher.group(i + 4);
        if (group2 != null) {
            parseLong += Long.parseLong(group2);
        }
        return parseLong * 1000;
    }

    private static String a(String str, ArrayList arrayList) {
        String trim = str.trim();
        StringBuilder sb = new StringBuilder(trim);
        Matcher matcher = e.matcher(trim);
        int i = 0;
        while (matcher.find()) {
            String group = matcher.group();
            arrayList.add(group);
            int start = matcher.start() - i;
            int length = group.length();
            sb.replace(start, start + length, "");
            i += length;
        }
        return sb.toString();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x012a, code lost:
        r9.g = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0130, code lost:
        switch(r10.hashCode()) {
            case -685620710: goto L_0x0175;
            case -685620679: goto L_0x016d;
            case -685620648: goto L_0x0165;
            case -685620617: goto L_0x015d;
            case -685620586: goto L_0x0155;
            case -685620555: goto L_0x014c;
            case -685620524: goto L_0x0144;
            case -685620493: goto L_0x013c;
            case -685620462: goto L_0x0134;
            default: goto L_0x0133;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0138, code lost:
        if (r10.equals("{\\an9}") == false) goto L_0x017d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x013a, code lost:
        r8 = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0140, code lost:
        if (r10.equals("{\\an8}") == false) goto L_0x017d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0142, code lost:
        r8 = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0148, code lost:
        if (r10.equals("{\\an7}") == false) goto L_0x017d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x014a, code lost:
        r8 = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0150, code lost:
        if (r10.equals("{\\an6}") == false) goto L_0x017d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0152, code lost:
        r8 = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0159, code lost:
        if (r10.equals("{\\an5}") == false) goto L_0x017d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x015b, code lost:
        r8 = 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0161, code lost:
        if (r10.equals("{\\an4}") == false) goto L_0x017d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0163, code lost:
        r8 = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0169, code lost:
        if (r10.equals("{\\an3}") == false) goto L_0x017d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x016b, code lost:
        r8 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0171, code lost:
        if (r10.equals("{\\an2}") == false) goto L_0x017d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0173, code lost:
        r8 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0179, code lost:
        if (r10.equals("{\\an1}") == false) goto L_0x017d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x017b, code lost:
        r8 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x017d, code lost:
        r8 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x017f, code lost:
        switch(r8) {
            case 0: goto L_0x0189;
            case 1: goto L_0x0189;
            case 2: goto L_0x0189;
            case 3: goto L_0x0186;
            case 4: goto L_0x0186;
            case 5: goto L_0x0186;
            default: goto L_0x0182;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0182, code lost:
        r9.e = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0186, code lost:
        r9.e = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0189, code lost:
        r9.e = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x018b, code lost:
        r9.f = a(r9.g);
        r0 = r9.a(a(r9.e), 0).a();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.exoplayer2.text.Subtitle a(byte[] r19, int r20, boolean r21) {
        /*
            r18 = this;
            r1 = r18
            java.lang.String r2 = "SubripDecoder"
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            com.google.android.exoplayer2.util.LongArray r4 = new com.google.android.exoplayer2.util.LongArray
            r4.<init>()
            com.google.android.exoplayer2.util.ParsableByteArray r5 = new com.google.android.exoplayer2.util.ParsableByteArray
            r0 = r19
            r6 = r20
            r5.<init>(r0, r6)
        L_0x0017:
            java.lang.String r6 = r5.s()
            r0 = 0
            if (r6 == 0) goto L_0x01e5
            int r7 = r6.length()
            if (r7 == 0) goto L_0x01e1
            java.lang.Integer.parseInt(r6)     // Catch:{ NumberFormatException -> 0x01bf }
            java.lang.String r6 = r5.s()
            if (r6 != 0) goto L_0x0034
            java.lang.String r5 = "Unexpected end"
            com.google.android.exoplayer2.util.Log.c(r2, r5)
            goto L_0x01e5
        L_0x0034:
            java.util.regex.Pattern r7 = d
            java.util.regex.Matcher r7 = r7.matcher(r6)
            boolean r8 = r7.matches()
            if (r8 == 0) goto L_0x01aa
            r6 = 1
            long r8 = a((java.util.regex.Matcher) r7, (int) r6)
            r4.a((long) r8)
            r8 = 6
            long r9 = a((java.util.regex.Matcher) r7, (int) r8)
            r4.a((long) r9)
            java.lang.StringBuilder r7 = r1.f
            r7.setLength(r0)
            java.util.ArrayList r7 = r1.g
            r7.clear()
        L_0x005a:
            java.lang.String r7 = r5.s()
            boolean r9 = android.text.TextUtils.isEmpty(r7)
            if (r9 != 0) goto L_0x007f
            java.lang.StringBuilder r9 = r1.f
            int r9 = r9.length()
            if (r9 <= 0) goto L_0x0073
            java.lang.StringBuilder r9 = r1.f
            java.lang.String r10 = "<br>"
            r9.append(r10)
        L_0x0073:
            java.lang.StringBuilder r9 = r1.f
            java.util.ArrayList r10 = r1.g
            java.lang.String r7 = a((java.lang.String) r7, (java.util.ArrayList) r10)
            r9.append(r7)
            goto L_0x005a
        L_0x007f:
            java.lang.StringBuilder r7 = r1.f
            java.lang.String r7 = r7.toString()
            android.text.Spanned r7 = android.text.Html.fromHtml(r7)
            r9 = 0
        L_0x008a:
            java.util.ArrayList r10 = r1.g
            int r10 = r10.size()
            if (r9 >= r10) goto L_0x00a6
            java.util.ArrayList r10 = r1.g
            java.lang.Object r10 = r10.get(r9)
            java.lang.String r10 = (java.lang.String) r10
            java.lang.String r11 = "\\{\\\\an[1-9]\\}"
            boolean r11 = r10.matches(r11)
            if (r11 == 0) goto L_0x00a3
            goto L_0x00a7
        L_0x00a3:
            int r9 = r9 + 1
            goto L_0x008a
        L_0x00a6:
            r10 = 0
        L_0x00a7:
            com.google.android.exoplayer2.text.Cue$Builder r9 = new com.google.android.exoplayer2.text.Cue$Builder
            r9.<init>()
            r9.a = r7
            if (r10 != 0) goto L_0x00b8
            com.google.android.exoplayer2.text.Cue r0 = r9.a()
            r17 = r5
            goto L_0x01a1
        L_0x00b8:
            int r7 = r10.hashCode()
            java.lang.String r8 = "{\\an9}"
            java.lang.String r11 = "{\\an8}"
            java.lang.String r12 = "{\\an7}"
            java.lang.String r13 = "{\\an6}"
            java.lang.String r14 = "{\\an5}"
            java.lang.String r15 = "{\\an4}"
            java.lang.String r6 = "{\\an3}"
            java.lang.String r0 = "{\\an2}"
            java.lang.String r1 = "{\\an1}"
            r16 = -1
            r17 = r5
            r5 = 2
            switch(r7) {
                case -685620710: goto L_0x0118;
                case -685620679: goto L_0x0110;
                case -685620648: goto L_0x0108;
                case -685620617: goto L_0x0100;
                case -685620586: goto L_0x00f8;
                case -685620555: goto L_0x00f0;
                case -685620524: goto L_0x00e8;
                case -685620493: goto L_0x00df;
                case -685620462: goto L_0x00d7;
                default: goto L_0x00d6;
            }
        L_0x00d6:
            goto L_0x0120
        L_0x00d7:
            boolean r7 = r10.equals(r8)
            if (r7 == 0) goto L_0x0120
            r7 = 5
            goto L_0x0121
        L_0x00df:
            boolean r7 = r10.equals(r11)
            if (r7 == 0) goto L_0x0120
            r7 = 8
            goto L_0x0121
        L_0x00e8:
            boolean r7 = r10.equals(r12)
            if (r7 == 0) goto L_0x0120
            r7 = 2
            goto L_0x0121
        L_0x00f0:
            boolean r7 = r10.equals(r13)
            if (r7 == 0) goto L_0x0120
            r7 = 4
            goto L_0x0121
        L_0x00f8:
            boolean r7 = r10.equals(r14)
            if (r7 == 0) goto L_0x0120
            r7 = 7
            goto L_0x0121
        L_0x0100:
            boolean r7 = r10.equals(r15)
            if (r7 == 0) goto L_0x0120
            r7 = 1
            goto L_0x0121
        L_0x0108:
            boolean r7 = r10.equals(r6)
            if (r7 == 0) goto L_0x0120
            r7 = 3
            goto L_0x0121
        L_0x0110:
            boolean r7 = r10.equals(r0)
            if (r7 == 0) goto L_0x0120
            r7 = 6
            goto L_0x0121
        L_0x0118:
            boolean r7 = r10.equals(r1)
            if (r7 == 0) goto L_0x0120
            r7 = 0
            goto L_0x0121
        L_0x0120:
            r7 = -1
        L_0x0121:
            switch(r7) {
                case 0: goto L_0x0129;
                case 1: goto L_0x0129;
                case 2: goto L_0x0129;
                case 3: goto L_0x0126;
                case 4: goto L_0x0126;
                case 5: goto L_0x0126;
                default: goto L_0x0124;
            }
        L_0x0124:
            r7 = 1
            goto L_0x012a
        L_0x0126:
            r9.g = r5
            goto L_0x012c
        L_0x0129:
            r7 = 0
        L_0x012a:
            r9.g = r7
        L_0x012c:
            int r7 = r10.hashCode()
            switch(r7) {
                case -685620710: goto L_0x0175;
                case -685620679: goto L_0x016d;
                case -685620648: goto L_0x0165;
                case -685620617: goto L_0x015d;
                case -685620586: goto L_0x0155;
                case -685620555: goto L_0x014c;
                case -685620524: goto L_0x0144;
                case -685620493: goto L_0x013c;
                case -685620462: goto L_0x0134;
                default: goto L_0x0133;
            }
        L_0x0133:
            goto L_0x017d
        L_0x0134:
            boolean r0 = r10.equals(r8)
            if (r0 == 0) goto L_0x017d
            r8 = 5
            goto L_0x017e
        L_0x013c:
            boolean r0 = r10.equals(r11)
            if (r0 == 0) goto L_0x017d
            r8 = 4
            goto L_0x017e
        L_0x0144:
            boolean r0 = r10.equals(r12)
            if (r0 == 0) goto L_0x017d
            r8 = 3
            goto L_0x017e
        L_0x014c:
            boolean r0 = r10.equals(r13)
            if (r0 == 0) goto L_0x017d
            r8 = 8
            goto L_0x017e
        L_0x0155:
            boolean r0 = r10.equals(r14)
            if (r0 == 0) goto L_0x017d
            r8 = 7
            goto L_0x017e
        L_0x015d:
            boolean r0 = r10.equals(r15)
            if (r0 == 0) goto L_0x017d
            r8 = 6
            goto L_0x017e
        L_0x0165:
            boolean r0 = r10.equals(r6)
            if (r0 == 0) goto L_0x017d
            r8 = 2
            goto L_0x017e
        L_0x016d:
            boolean r0 = r10.equals(r0)
            if (r0 == 0) goto L_0x017d
            r8 = 1
            goto L_0x017e
        L_0x0175:
            boolean r0 = r10.equals(r1)
            if (r0 == 0) goto L_0x017d
            r8 = 0
            goto L_0x017e
        L_0x017d:
            r8 = -1
        L_0x017e:
            r0 = 0
            switch(r8) {
                case 0: goto L_0x0189;
                case 1: goto L_0x0189;
                case 2: goto L_0x0189;
                case 3: goto L_0x0186;
                case 4: goto L_0x0186;
                case 5: goto L_0x0186;
                default: goto L_0x0182;
            }
        L_0x0182:
            r1 = 1
            r9.e = r1
            goto L_0x018b
        L_0x0186:
            r9.e = r0
            goto L_0x018b
        L_0x0189:
            r9.e = r5
        L_0x018b:
            int r1 = r9.g
            float r1 = a(r1)
            r9.f = r1
            int r1 = r9.e
            float r1 = a(r1)
            com.google.android.exoplayer2.text.Cue$Builder r0 = r9.a(r1, r0)
            com.google.android.exoplayer2.text.Cue r0 = r0.a()
        L_0x01a1:
            r3.add(r0)
            com.google.android.exoplayer2.text.Cue r0 = com.google.android.exoplayer2.text.Cue.a
            r3.add(r0)
            goto L_0x01db
        L_0x01aa:
            r17 = r5
            java.lang.String r0 = java.lang.String.valueOf(r6)
            int r1 = r0.length()
            java.lang.String r5 = "Skipping invalid timing: "
            if (r1 == 0) goto L_0x01b9
            goto L_0x01ce
        L_0x01b9:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r5)
            goto L_0x01d8
        L_0x01bf:
            r0 = move-exception
            r17 = r5
            java.lang.String r0 = java.lang.String.valueOf(r6)
            int r1 = r0.length()
            java.lang.String r5 = "Skipping invalid index: "
            if (r1 == 0) goto L_0x01d3
        L_0x01ce:
            java.lang.String r0 = r5.concat(r0)
            goto L_0x01d8
        L_0x01d3:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r5)
        L_0x01d8:
            com.google.android.exoplayer2.util.Log.c(r2, r0)
        L_0x01db:
            r1 = r18
            r5 = r17
            goto L_0x0017
        L_0x01e1:
            r1 = r18
            goto L_0x0017
        L_0x01e5:
            com.google.android.exoplayer2.text.Cue[] r0 = new com.google.android.exoplayer2.text.Cue[r0]
            java.lang.Object[] r0 = r3.toArray(r0)
            com.google.android.exoplayer2.text.Cue[] r0 = (com.google.android.exoplayer2.text.Cue[]) r0
            long[] r1 = r4.b
            int r2 = r4.a
            long[] r1 = java.util.Arrays.copyOf(r1, r2)
            com.google.android.exoplayer2.text.subrip.SubripSubtitle r2 = new com.google.android.exoplayer2.text.subrip.SubripSubtitle
            r2.<init>(r0, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.subrip.SubripDecoder.a(byte[], int, boolean):com.google.android.exoplayer2.text.Subtitle");
    }
}
