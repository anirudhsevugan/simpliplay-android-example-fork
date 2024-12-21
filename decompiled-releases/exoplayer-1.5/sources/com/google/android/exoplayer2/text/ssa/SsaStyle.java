package com.google.android.exoplayer2.text.ssa;

import android.graphics.Color;
import android.graphics.PointF;
import android.text.TextUtils;
import com.dreamers.exoplayercore.repack.cR;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import java.util.regex.Pattern;

final class SsaStyle {
    public final String a;
    public final int b;
    public final Integer c;
    public final float d;
    public final boolean e;
    public final boolean f;
    public final boolean g;
    public final boolean h;

    final class Format {
        public final int a;
        public final int b;
        public final int c;
        public final int d;
        public final int e;
        public final int f;
        public final int g;
        public final int h;
        public final int i;

        private Format(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
            this.a = i2;
            this.b = i3;
            this.c = i4;
            this.d = i5;
            this.e = i6;
            this.f = i7;
            this.g = i8;
            this.h = i9;
            this.i = i10;
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static com.google.android.exoplayer2.text.ssa.SsaStyle.Format a(java.lang.String r14) {
            /*
                r0 = 7
                java.lang.String r14 = r14.substring(r0)
                java.lang.String r1 = ","
                java.lang.String[] r14 = android.text.TextUtils.split(r14, r1)
                r1 = -1
                r2 = 0
                r3 = 0
                r5 = -1
                r6 = -1
                r7 = -1
                r8 = -1
                r9 = -1
                r10 = -1
                r11 = -1
                r12 = -1
            L_0x0016:
                int r4 = r14.length
                if (r3 >= r4) goto L_0x0092
                r4 = r14[r3]
                java.lang.String r4 = r4.trim()
                java.lang.String r4 = com.dreamers.exoplayercore.repack.C0000a.a((java.lang.String) r4)
                int r13 = r4.hashCode()
                switch(r13) {
                    case -1178781136: goto L_0x0071;
                    case -1026963764: goto L_0x0067;
                    case -192095652: goto L_0x005d;
                    case -70925746: goto L_0x0053;
                    case 3029637: goto L_0x0049;
                    case 3373707: goto L_0x003f;
                    case 366554320: goto L_0x0035;
                    case 1767875043: goto L_0x002b;
                    default: goto L_0x002a;
                }
            L_0x002a:
                goto L_0x007b
            L_0x002b:
                java.lang.String r13 = "alignment"
                boolean r4 = r4.equals(r13)
                if (r4 == 0) goto L_0x007b
                r4 = 1
                goto L_0x007c
            L_0x0035:
                java.lang.String r13 = "fontsize"
                boolean r4 = r4.equals(r13)
                if (r4 == 0) goto L_0x007b
                r4 = 3
                goto L_0x007c
            L_0x003f:
                java.lang.String r13 = "name"
                boolean r4 = r4.equals(r13)
                if (r4 == 0) goto L_0x007b
                r4 = 0
                goto L_0x007c
            L_0x0049:
                java.lang.String r13 = "bold"
                boolean r4 = r4.equals(r13)
                if (r4 == 0) goto L_0x007b
                r4 = 4
                goto L_0x007c
            L_0x0053:
                java.lang.String r13 = "primarycolour"
                boolean r4 = r4.equals(r13)
                if (r4 == 0) goto L_0x007b
                r4 = 2
                goto L_0x007c
            L_0x005d:
                java.lang.String r13 = "strikeout"
                boolean r4 = r4.equals(r13)
                if (r4 == 0) goto L_0x007b
                r4 = 7
                goto L_0x007c
            L_0x0067:
                java.lang.String r13 = "underline"
                boolean r4 = r4.equals(r13)
                if (r4 == 0) goto L_0x007b
                r4 = 6
                goto L_0x007c
            L_0x0071:
                java.lang.String r13 = "italic"
                boolean r4 = r4.equals(r13)
                if (r4 == 0) goto L_0x007b
                r4 = 5
                goto L_0x007c
            L_0x007b:
                r4 = -1
            L_0x007c:
                switch(r4) {
                    case 0: goto L_0x008e;
                    case 1: goto L_0x008c;
                    case 2: goto L_0x008a;
                    case 3: goto L_0x0088;
                    case 4: goto L_0x0086;
                    case 5: goto L_0x0084;
                    case 6: goto L_0x0082;
                    case 7: goto L_0x0080;
                    default: goto L_0x007f;
                }
            L_0x007f:
                goto L_0x008f
            L_0x0080:
                r12 = r3
                goto L_0x008f
            L_0x0082:
                r11 = r3
                goto L_0x008f
            L_0x0084:
                r10 = r3
                goto L_0x008f
            L_0x0086:
                r9 = r3
                goto L_0x008f
            L_0x0088:
                r8 = r3
                goto L_0x008f
            L_0x008a:
                r7 = r3
                goto L_0x008f
            L_0x008c:
                r6 = r3
                goto L_0x008f
            L_0x008e:
                r5 = r3
            L_0x008f:
                int r3 = r3 + 1
                goto L_0x0016
            L_0x0092:
                if (r5 == r1) goto L_0x009c
                com.google.android.exoplayer2.text.ssa.SsaStyle$Format r0 = new com.google.android.exoplayer2.text.ssa.SsaStyle$Format
                int r13 = r14.length
                r4 = r0
                r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13)
                return r0
            L_0x009c:
                r14 = 0
                return r14
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.ssa.SsaStyle.Format.a(java.lang.String):com.google.android.exoplayer2.text.ssa.SsaStyle$Format");
        }
    }

    final class Overrides {
        private static final Pattern c = Pattern.compile("\\{([^}]*)\\}");
        private static final Pattern d = Pattern.compile(Util.a("\\\\pos\\((%1$s),(%1$s)\\)", "\\s*\\d+(?:\\.\\d+)?\\s*"));
        private static final Pattern e = Pattern.compile(Util.a("\\\\move\\(%1$s,%1$s,(%1$s),(%1$s)(?:,%1$s,%1$s)?\\)", "\\s*\\d+(?:\\.\\d+)?\\s*"));
        private static final Pattern f = Pattern.compile("\\\\an(\\d+)");
        public final int a;
        public final PointF b;

        private Overrides(int i, PointF pointF) {
            this.a = i;
            this.b = pointF;
        }

        /* JADX WARNING: Removed duplicated region for block: B:15:0x0097  */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x00a6 A[Catch:{ RuntimeException -> 0x00bb }] */
        /* JADX WARNING: Removed duplicated region for block: B:21:0x00b5  */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x00b8  */
        /* JADX WARNING: Removed duplicated region for block: B:29:0x000a A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static com.google.android.exoplayer2.text.ssa.SsaStyle.Overrides a(java.lang.String r12) {
            /*
                java.util.regex.Pattern r0 = c
                java.util.regex.Matcher r12 = r0.matcher(r12)
                r0 = -1
                r1 = 0
                r3 = r1
                r2 = -1
            L_0x000a:
                boolean r4 = r12.find()
                if (r4 == 0) goto L_0x00be
                r4 = 1
                java.lang.String r5 = r12.group(r4)
                java.lang.Object r5 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r5)
                java.lang.String r5 = (java.lang.String) r5
                java.util.regex.Pattern r6 = d     // Catch:{ RuntimeException -> 0x0099 }
                java.util.regex.Matcher r6 = r6.matcher(r5)     // Catch:{ RuntimeException -> 0x0099 }
                java.util.regex.Pattern r7 = e     // Catch:{ RuntimeException -> 0x0099 }
                java.util.regex.Matcher r7 = r7.matcher(r5)     // Catch:{ RuntimeException -> 0x0099 }
                boolean r8 = r6.find()     // Catch:{ RuntimeException -> 0x0099 }
                boolean r9 = r7.find()     // Catch:{ RuntimeException -> 0x0099 }
                r10 = 2
                if (r8 == 0) goto L_0x0065
                if (r9 == 0) goto L_0x005c
                java.lang.String r7 = "SsaStyle.Overrides"
                java.lang.String r8 = java.lang.String.valueOf(r5)     // Catch:{ RuntimeException -> 0x0099 }
                int r8 = r8.length()     // Catch:{ RuntimeException -> 0x0099 }
                int r8 = r8 + 82
                java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x0099 }
                r9.<init>(r8)     // Catch:{ RuntimeException -> 0x0099 }
                java.lang.String r8 = "Override has both \\pos(x,y) and \\move(x1,y1,x2,y2); using \\pos values. override='"
                java.lang.StringBuilder r8 = r9.append(r8)     // Catch:{ RuntimeException -> 0x0099 }
                java.lang.StringBuilder r8 = r8.append(r5)     // Catch:{ RuntimeException -> 0x0099 }
                java.lang.String r9 = "'"
                java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ RuntimeException -> 0x0099 }
                java.lang.String r8 = r8.toString()     // Catch:{ RuntimeException -> 0x0099 }
                com.google.android.exoplayer2.util.Log.b(r7, r8)     // Catch:{ RuntimeException -> 0x0099 }
            L_0x005c:
                java.lang.String r7 = r6.group(r4)     // Catch:{ RuntimeException -> 0x0099 }
                java.lang.String r6 = r6.group(r10)     // Catch:{ RuntimeException -> 0x0099 }
                goto L_0x0072
            L_0x0065:
                if (r9 == 0) goto L_0x0094
                java.lang.String r6 = r7.group(r4)     // Catch:{ RuntimeException -> 0x0099 }
                java.lang.String r7 = r7.group(r10)     // Catch:{ RuntimeException -> 0x0099 }
                r11 = r7
                r7 = r6
                r6 = r11
            L_0x0072:
                android.graphics.PointF r8 = new android.graphics.PointF     // Catch:{ RuntimeException -> 0x0099 }
                java.lang.Object r7 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r7)     // Catch:{ RuntimeException -> 0x0099 }
                java.lang.String r7 = (java.lang.String) r7     // Catch:{ RuntimeException -> 0x0099 }
                java.lang.String r7 = r7.trim()     // Catch:{ RuntimeException -> 0x0099 }
                float r7 = java.lang.Float.parseFloat(r7)     // Catch:{ RuntimeException -> 0x0099 }
                java.lang.Object r6 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r6)     // Catch:{ RuntimeException -> 0x0099 }
                java.lang.String r6 = (java.lang.String) r6     // Catch:{ RuntimeException -> 0x0099 }
                java.lang.String r6 = r6.trim()     // Catch:{ RuntimeException -> 0x0099 }
                float r6 = java.lang.Float.parseFloat(r6)     // Catch:{ RuntimeException -> 0x0099 }
                r8.<init>(r7, r6)     // Catch:{ RuntimeException -> 0x0099 }
                goto L_0x0095
            L_0x0094:
                r8 = r1
            L_0x0095:
                if (r8 == 0) goto L_0x009a
                r3 = r8
                goto L_0x009a
            L_0x0099:
                r6 = move-exception
            L_0x009a:
                java.util.regex.Pattern r6 = f     // Catch:{ RuntimeException -> 0x00bb }
                java.util.regex.Matcher r5 = r6.matcher(r5)     // Catch:{ RuntimeException -> 0x00bb }
                boolean r6 = r5.find()     // Catch:{ RuntimeException -> 0x00bb }
                if (r6 == 0) goto L_0x00b5
                java.lang.String r4 = r5.group(r4)     // Catch:{ RuntimeException -> 0x00bb }
                java.lang.Object r4 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r4)     // Catch:{ RuntimeException -> 0x00bb }
                java.lang.String r4 = (java.lang.String) r4     // Catch:{ RuntimeException -> 0x00bb }
                int r4 = com.google.android.exoplayer2.text.ssa.SsaStyle.b(r4)     // Catch:{ RuntimeException -> 0x00bb }
                goto L_0x00b6
            L_0x00b5:
                r4 = -1
            L_0x00b6:
                if (r4 == r0) goto L_0x000a
                r2 = r4
                goto L_0x000a
            L_0x00bb:
                r4 = move-exception
                goto L_0x000a
            L_0x00be:
                com.google.android.exoplayer2.text.ssa.SsaStyle$Overrides r12 = new com.google.android.exoplayer2.text.ssa.SsaStyle$Overrides
                r12.<init>(r2, r3)
                return r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.ssa.SsaStyle.Overrides.a(java.lang.String):com.google.android.exoplayer2.text.ssa.SsaStyle$Overrides");
        }

        public static String b(String str) {
            return c.matcher(str).replaceAll("");
        }
    }

    private SsaStyle(String str, int i, Integer num, float f2, boolean z, boolean z2, boolean z3, boolean z4) {
        this.a = str;
        this.b = i;
        this.c = num;
        this.d = f2;
        this.e = z;
        this.f = z2;
        this.g = z3;
        this.h = z4;
    }

    public static SsaStyle a(String str, Format format) {
        Assertions.a(str.startsWith("Style:"));
        String[] split = TextUtils.split(str.substring(6), ",");
        if (split.length != format.i) {
            Log.c("SsaStyle", Util.a("Skipping malformed 'Style:' line (expected %s values, found %s): '%s'", Integer.valueOf(format.i), Integer.valueOf(split.length), str));
            return null;
        }
        try {
            return new SsaStyle(split[format.a].trim(), format.b != -1 ? b(split[format.b].trim()) : -1, format.c != -1 ? c(split[format.c].trim()) : null, format.d != -1 ? d(split[format.d].trim()) : -3.4028235E38f, format.e != -1 ? e(split[format.e].trim()) : false, format.f != -1 ? e(split[format.f].trim()) : false, format.g != -1 ? e(split[format.g].trim()) : false, format.h != -1 ? e(split[format.h].trim()) : false);
        } catch (RuntimeException e2) {
            Log.a("SsaStyle", new StringBuilder(String.valueOf(str).length() + 36).append("Skipping malformed 'Style:' line: '").append(str).append("'").toString(), e2);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static int b(String str) {
        boolean z;
        try {
            int parseInt = Integer.parseInt(str.trim());
            switch (parseInt) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    z = true;
                    break;
                default:
                    z = false;
                    break;
            }
            if (z) {
                return parseInt;
            }
        } catch (NumberFormatException e2) {
        }
        String valueOf = String.valueOf(str);
        Log.c("SsaStyle", valueOf.length() != 0 ? "Ignoring unknown alignment: ".concat(valueOf) : new String("Ignoring unknown alignment: "));
        return -1;
    }

    private static Integer c(String str) {
        try {
            long parseLong = str.startsWith("&H") ? Long.parseLong(str.substring(2), 16) : Long.parseLong(str);
            Assertions.a(parseLong <= 4294967295L);
            return Integer.valueOf(Color.argb(cR.a(((parseLong >> 24) & 255) ^ 255), cR.a(parseLong & 255), cR.a((parseLong >> 8) & 255), cR.a((parseLong >> 16) & 255)));
        } catch (IllegalArgumentException e2) {
            Log.a("SsaStyle", new StringBuilder(String.valueOf(str).length() + 36).append("Failed to parse color expression: '").append(str).append("'").toString(), e2);
            return null;
        }
    }

    private static float d(String str) {
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e2) {
            Log.a("SsaStyle", new StringBuilder(String.valueOf(str).length() + 29).append("Failed to parse font size: '").append(str).append("'").toString(), e2);
            return -3.4028235E38f;
        }
    }

    private static boolean e(String str) {
        try {
            int parseInt = Integer.parseInt(str);
            return parseInt == 1 || parseInt == -1;
        } catch (NumberFormatException e2) {
            Log.a("SsaStyle", new StringBuilder(String.valueOf(str).length() + 33).append("Failed to parse boolean value: '").append(str).append("'").toString(), e2);
            return false;
        }
    }
}
