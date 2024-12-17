package com.google.android.exoplayer2.text.ttml;

import android.text.SpannableStringBuilder;
import java.util.ArrayDeque;
import java.util.Map;

final class TtmlRenderUtil {
    private static TtmlNode a(TtmlNode ttmlNode, Map map) {
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.push(ttmlNode);
        while (!arrayDeque.isEmpty()) {
            TtmlNode ttmlNode2 = (TtmlNode) arrayDeque.pop();
            TtmlStyle a = a(ttmlNode2.d, ttmlNode2.e, map);
            if (a != null && a.m == 3) {
                return ttmlNode2;
            }
            for (int a2 = ttmlNode2.a() - 1; a2 >= 0; a2--) {
                arrayDeque.push(ttmlNode2.a(a2));
            }
        }
        return null;
    }

    public static TtmlStyle a(TtmlStyle ttmlStyle, String[] strArr, Map map) {
        int i = 0;
        if (ttmlStyle == null) {
            if (strArr == null) {
                return null;
            }
            if (strArr.length == 1) {
                return (TtmlStyle) map.get(strArr[0]);
            }
            if (strArr.length > 1) {
                TtmlStyle ttmlStyle2 = new TtmlStyle();
                int length = strArr.length;
                while (i < length) {
                    ttmlStyle2.a((TtmlStyle) map.get(strArr[i]));
                    i++;
                }
                return ttmlStyle2;
            }
        } else if (strArr != null && strArr.length == 1) {
            return ttmlStyle.a((TtmlStyle) map.get(strArr[0]));
        } else {
            if (strArr != null && strArr.length > 1) {
                int length2 = strArr.length;
                while (i < length2) {
                    ttmlStyle.a((TtmlStyle) map.get(strArr[i]));
                    i++;
                }
            }
        }
        return ttmlStyle;
    }

    static String a(String str) {
        return str.replaceAll("\r\n", "\n").replaceAll(" *\n *", "\n").replaceAll("\n", " ").replaceAll("[ \t\\x0B\f\r]+", " ");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.text.Spannable r8, int r9, int r10, com.google.android.exoplayer2.text.ttml.TtmlStyle r11, com.google.android.exoplayer2.text.ttml.TtmlNode r12, java.util.Map r13, int r14) {
        /*
            int r0 = r11.a()
            r1 = 33
            r2 = -1
            if (r0 == r2) goto L_0x0015
            android.text.style.StyleSpan r0 = new android.text.style.StyleSpan
            int r3 = r11.a()
            r0.<init>(r3)
            r8.setSpan(r0, r9, r10, r1)
        L_0x0015:
            int r0 = r11.f
            r3 = 0
            r4 = 1
            if (r0 != r4) goto L_0x001d
            r0 = 1
            goto L_0x001e
        L_0x001d:
            r0 = 0
        L_0x001e:
            if (r0 == 0) goto L_0x0028
            android.text.style.StrikethroughSpan r0 = new android.text.style.StrikethroughSpan
            r0.<init>()
            r8.setSpan(r0, r9, r10, r1)
        L_0x0028:
            int r0 = r11.g
            if (r0 != r4) goto L_0x002e
            r0 = 1
            goto L_0x002f
        L_0x002e:
            r0 = 0
        L_0x002f:
            if (r0 == 0) goto L_0x0039
            android.text.style.UnderlineSpan r0 = new android.text.style.UnderlineSpan
            r0.<init>()
            r8.setSpan(r0, r9, r10, r1)
        L_0x0039:
            boolean r0 = r11.c
            if (r0 == 0) goto L_0x0054
            android.text.style.ForegroundColorSpan r0 = new android.text.style.ForegroundColorSpan
            boolean r5 = r11.c
            if (r5 == 0) goto L_0x004c
            int r5 = r11.b
            r0.<init>(r5)
            com.google.android.exoplayer2.text.span.SpanUtil.a(r8, r0, r9, r10)
            goto L_0x0054
        L_0x004c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "Font color has not been defined."
            r8.<init>(r9)
            throw r8
        L_0x0054:
            boolean r0 = r11.e
            if (r0 == 0) goto L_0x006f
            android.text.style.BackgroundColorSpan r0 = new android.text.style.BackgroundColorSpan
            boolean r5 = r11.e
            if (r5 == 0) goto L_0x0067
            int r5 = r11.d
            r0.<init>(r5)
            com.google.android.exoplayer2.text.span.SpanUtil.a(r8, r0, r9, r10)
            goto L_0x006f
        L_0x0067:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "Background color has not been defined."
            r8.<init>(r9)
            throw r8
        L_0x006f:
            java.lang.String r0 = r11.a
            if (r0 == 0) goto L_0x007d
            android.text.style.TypefaceSpan r0 = new android.text.style.TypefaceSpan
            java.lang.String r5 = r11.a
            r0.<init>(r5)
            com.google.android.exoplayer2.text.span.SpanUtil.a(r8, r0, r9, r10)
        L_0x007d:
            com.google.android.exoplayer2.text.ttml.TextEmphasis r0 = r11.r
            if (r0 == 0) goto L_0x00ad
            com.google.android.exoplayer2.text.ttml.TextEmphasis r0 = r11.r
            java.lang.Object r0 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r0)
            com.google.android.exoplayer2.text.ttml.TextEmphasis r0 = (com.google.android.exoplayer2.text.ttml.TextEmphasis) r0
            int r5 = r0.a
            if (r5 != r2) goto L_0x0098
            r5 = 2
            if (r14 == r5) goto L_0x0095
            if (r14 != r4) goto L_0x0093
            goto L_0x0095
        L_0x0093:
            r14 = 1
            goto L_0x0096
        L_0x0095:
            r14 = 3
        L_0x0096:
            r5 = 1
            goto L_0x009c
        L_0x0098:
            int r14 = r0.a
            int r5 = r0.b
        L_0x009c:
            int r6 = r0.c
            r7 = -2
            if (r6 != r7) goto L_0x00a3
            r0 = 1
            goto L_0x00a5
        L_0x00a3:
            int r0 = r0.c
        L_0x00a5:
            com.google.android.exoplayer2.text.span.TextEmphasisSpan r6 = new com.google.android.exoplayer2.text.span.TextEmphasisSpan
            r6.<init>(r14, r5, r0)
            com.google.android.exoplayer2.text.span.SpanUtil.a(r8, r6, r9, r10)
        L_0x00ad:
            int r14 = r11.m
            switch(r14) {
                case 2: goto L_0x00bc;
                case 3: goto L_0x00b3;
                case 4: goto L_0x00b3;
                default: goto L_0x00b2;
            }
        L_0x00b2:
            goto L_0x00f7
        L_0x00b3:
            com.google.android.exoplayer2.text.ttml.DeleteTextSpan r12 = new com.google.android.exoplayer2.text.ttml.DeleteTextSpan
            r12.<init>()
        L_0x00b8:
            r8.setSpan(r12, r9, r10, r1)
            goto L_0x00f7
        L_0x00bc:
            com.google.android.exoplayer2.text.ttml.TtmlNode r12 = b(r12, r13)
            if (r12 == 0) goto L_0x00f7
            com.google.android.exoplayer2.text.ttml.TtmlNode r13 = a(r12, r13)
            if (r13 == 0) goto L_0x00f7
            int r14 = r13.a()
            if (r14 != r4) goto L_0x00f0
            com.google.android.exoplayer2.text.ttml.TtmlNode r14 = r13.a((int) r3)
            java.lang.String r14 = r14.a
            if (r14 == 0) goto L_0x00f0
            com.google.android.exoplayer2.text.ttml.TtmlNode r13 = r13.a((int) r3)
            java.lang.String r13 = r13.a
            java.lang.Object r13 = com.google.android.exoplayer2.util.Util.a((java.lang.Object) r13)
            java.lang.String r13 = (java.lang.String) r13
            com.google.android.exoplayer2.text.ttml.TtmlStyle r14 = r12.d
            if (r14 == 0) goto L_0x00ea
            com.google.android.exoplayer2.text.ttml.TtmlStyle r12 = r12.d
            int r2 = r12.n
        L_0x00ea:
            com.google.android.exoplayer2.text.span.RubySpan r12 = new com.google.android.exoplayer2.text.span.RubySpan
            r12.<init>(r13, r2)
            goto L_0x00b8
        L_0x00f0:
            java.lang.String r12 = "TtmlRenderUtil"
            java.lang.String r13 = "Skipping rubyText node without exactly one text child."
            com.google.android.exoplayer2.util.Log.b(r12, r13)
        L_0x00f7:
            int r12 = r11.q
            if (r12 != r4) goto L_0x00fc
            r3 = 1
        L_0x00fc:
            if (r3 == 0) goto L_0x0106
            com.google.android.exoplayer2.text.span.HorizontalTextInVerticalContextSpan r12 = new com.google.android.exoplayer2.text.span.HorizontalTextInVerticalContextSpan
            r12.<init>()
            com.google.android.exoplayer2.text.span.SpanUtil.a(r8, r12, r9, r10)
        L_0x0106:
            int r12 = r11.j
            switch(r12) {
                case 1: goto L_0x0125;
                case 2: goto L_0x011a;
                case 3: goto L_0x010c;
                default: goto L_0x010b;
            }
        L_0x010b:
            goto L_0x012e
        L_0x010c:
            android.text.style.RelativeSizeSpan r12 = new android.text.style.RelativeSizeSpan
            float r11 = r11.k
            r13 = 1120403456(0x42c80000, float:100.0)
            float r11 = r11 / r13
            r12.<init>(r11)
            com.google.android.exoplayer2.text.span.SpanUtil.a(r8, r12, r9, r10)
            goto L_0x012e
        L_0x011a:
            android.text.style.RelativeSizeSpan r12 = new android.text.style.RelativeSizeSpan
            float r11 = r11.k
            r12.<init>(r11)
        L_0x0121:
            com.google.android.exoplayer2.text.span.SpanUtil.a(r8, r12, r9, r10)
            return
        L_0x0125:
            android.text.style.AbsoluteSizeSpan r12 = new android.text.style.AbsoluteSizeSpan
            float r11 = r11.k
            int r11 = (int) r11
            r12.<init>(r11, r4)
            goto L_0x0121
        L_0x012e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.ttml.TtmlRenderUtil.a(android.text.Spannable, int, int, com.google.android.exoplayer2.text.ttml.TtmlStyle, com.google.android.exoplayer2.text.ttml.TtmlNode, java.util.Map, int):void");
    }

    static void a(SpannableStringBuilder spannableStringBuilder) {
        int length = spannableStringBuilder.length() - 1;
        while (length >= 0 && spannableStringBuilder.charAt(length) == ' ') {
            length--;
        }
        if (length >= 0 && spannableStringBuilder.charAt(length) != 10) {
            spannableStringBuilder.append(10);
        }
    }

    private static TtmlNode b(TtmlNode ttmlNode, Map map) {
        while (ttmlNode != null) {
            TtmlStyle a = a(ttmlNode.d, ttmlNode.e, map);
            if (a != null && a.m == 1) {
                return ttmlNode;
            }
            ttmlNode = ttmlNode.g;
        }
        return null;
    }
}
