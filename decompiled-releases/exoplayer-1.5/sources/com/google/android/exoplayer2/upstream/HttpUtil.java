package com.google.android.exoplayer2.upstream;

import android.text.TextUtils;
import com.google.android.exoplayer2.util.Assertions;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HttpUtil {
    private static final Pattern a = Pattern.compile("bytes (\\d+)-(\\d+)/(?:\\d+|\\*)");
    private static final Pattern b = Pattern.compile("bytes (?:(?:\\d+-\\d+)|\\*)/(\\d+)");

    public static long a(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        Matcher matcher = b.matcher(str);
        if (matcher.matches()) {
            return Long.parseLong((String) Assertions.b((Object) matcher.group(1)));
        }
        return -1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x003c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long a(java.lang.String r9, java.lang.String r10) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r9)
            java.lang.String r1 = "]"
            java.lang.String r2 = "HttpUtil"
            if (r0 != 0) goto L_0x0034
            long r3 = java.lang.Long.parseLong(r9)     // Catch:{ NumberFormatException -> 0x000f }
            goto L_0x0036
        L_0x000f:
            r0 = move-exception
            java.lang.String r0 = java.lang.String.valueOf(r9)
            int r0 = r0.length()
            int r0 = r0 + 28
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r0)
            java.lang.String r0 = "Unexpected Content-Length ["
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.StringBuilder r0 = r0.append(r9)
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.google.android.exoplayer2.util.Log.d(r2, r0)
        L_0x0034:
            r3 = -1
        L_0x0036:
            boolean r0 = android.text.TextUtils.isEmpty(r10)
            if (r0 != 0) goto L_0x00d8
            java.util.regex.Pattern r0 = a
            java.util.regex.Matcher r0 = r0.matcher(r10)
            boolean r5 = r0.matches()
            if (r5 == 0) goto L_0x00d8
            r5 = 2
            java.lang.String r5 = r0.group(r5)     // Catch:{ NumberFormatException -> 0x00b3 }
            java.lang.Object r5 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r5)     // Catch:{ NumberFormatException -> 0x00b3 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ NumberFormatException -> 0x00b3 }
            long r5 = java.lang.Long.parseLong(r5)     // Catch:{ NumberFormatException -> 0x00b3 }
            r7 = 1
            java.lang.String r0 = r0.group(r7)     // Catch:{ NumberFormatException -> 0x00b3 }
            java.lang.Object r0 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r0)     // Catch:{ NumberFormatException -> 0x00b3 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ NumberFormatException -> 0x00b3 }
            long r7 = java.lang.Long.parseLong(r0)     // Catch:{ NumberFormatException -> 0x00b3 }
            long r5 = r5 - r7
            r7 = 1
            long r5 = r5 + r7
            r7 = 0
            int r0 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r0 >= 0) goto L_0x0072
            r3 = r5
            goto L_0x00d8
        L_0x0072:
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 == 0) goto L_0x00d8
            java.lang.String r0 = java.lang.String.valueOf(r9)     // Catch:{ NumberFormatException -> 0x00b3 }
            int r0 = r0.length()     // Catch:{ NumberFormatException -> 0x00b3 }
            int r0 = r0 + 26
            java.lang.String r7 = java.lang.String.valueOf(r10)     // Catch:{ NumberFormatException -> 0x00b3 }
            int r7 = r7.length()     // Catch:{ NumberFormatException -> 0x00b3 }
            int r0 = r0 + r7
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x00b3 }
            r7.<init>(r0)     // Catch:{ NumberFormatException -> 0x00b3 }
            java.lang.String r0 = "Inconsistent headers ["
            java.lang.StringBuilder r0 = r7.append(r0)     // Catch:{ NumberFormatException -> 0x00b3 }
            java.lang.StringBuilder r9 = r0.append(r9)     // Catch:{ NumberFormatException -> 0x00b3 }
            java.lang.String r0 = "] ["
            java.lang.StringBuilder r9 = r9.append(r0)     // Catch:{ NumberFormatException -> 0x00b3 }
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ NumberFormatException -> 0x00b3 }
            java.lang.StringBuilder r9 = r9.append(r1)     // Catch:{ NumberFormatException -> 0x00b3 }
            java.lang.String r9 = r9.toString()     // Catch:{ NumberFormatException -> 0x00b3 }
            com.google.android.exoplayer2.util.Log.c(r2, r9)     // Catch:{ NumberFormatException -> 0x00b3 }
            long r9 = java.lang.Math.max(r3, r5)     // Catch:{ NumberFormatException -> 0x00b3 }
            r3 = r9
            goto L_0x00d8
        L_0x00b3:
            r9 = move-exception
            java.lang.String r9 = java.lang.String.valueOf(r10)
            int r9 = r9.length()
            int r9 = r9 + 27
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r9)
            java.lang.String r9 = "Unexpected Content-Range ["
            java.lang.StringBuilder r9 = r0.append(r9)
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.StringBuilder r9 = r9.append(r1)
            java.lang.String r9 = r9.toString()
            com.google.android.exoplayer2.util.Log.d(r2, r9)
        L_0x00d8:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.upstream.HttpUtil.a(java.lang.String, java.lang.String):long");
    }

    public static String a(long j, long j2) {
        if (j == 0 && j2 == -1) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("bytes=");
        sb.append(j);
        sb.append("-");
        if (j2 != -1) {
            sb.append((j + j2) - 1);
        }
        return sb.toString();
    }
}
