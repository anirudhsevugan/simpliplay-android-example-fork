package com.google.android.exoplayer2.text.ttml;

import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.util.XmlPullParserUtil;
import com.google.appinventor.components.common.ComponentDescriptorConstants;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public final class TtmlDecoder extends SimpleSubtitleDecoder {
    private static final Pattern d = Pattern.compile("^([0-9][0-9]+):([0-9][0-9]):([0-9][0-9])(?:(\\.[0-9]+)|:([0-9][0-9])(?:\\.([0-9]+))?)?$");
    private static final Pattern e = Pattern.compile("^([0-9]+(?:\\.[0-9]+)?)(h|m|s|ms|f|t)$");
    private static final Pattern f = Pattern.compile("^(([0-9]*.)?[0-9]+)(px|em|%)$");
    private static Pattern g = Pattern.compile("^([-+]?\\d+\\.?\\d*?)%$");
    private static Pattern h = Pattern.compile("^(\\d+\\.?\\d*?)% (\\d+\\.?\\d*?)%$");
    private static final Pattern i = Pattern.compile("^(\\d+\\.?\\d*?)px (\\d+\\.?\\d*?)px$");
    private static final Pattern j = Pattern.compile("^(\\d+) (\\d+)$");
    private static final FrameAndTickRate k = new FrameAndTickRate(30.0f, 1, 1);
    private static final CellResolution l = new CellResolution(15);
    private final XmlPullParserFactory m;

    final class CellResolution {
        final int a;

        CellResolution(int i) {
            this.a = i;
        }
    }

    final class FrameAndTickRate {
        final float a;
        final int b;
        final int c;

        FrameAndTickRate(float f, int i, int i2) {
            this.a = f;
            this.b = i;
            this.c = i2;
        }
    }

    final class TtsExtent {
        final int a;
        final int b;

        TtsExtent(int i, int i2) {
            this.a = i;
            this.b = i2;
        }
    }

    public TtmlDecoder() {
        try {
            XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
            this.m = newInstance;
            newInstance.setNamespaceAware(true);
        } catch (XmlPullParserException e2) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e2);
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00d6, code lost:
        if (r14.equals("t") != false) goto L_0x0102;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x010d, code lost:
        java.lang.Double.isNaN(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0116, code lost:
        r9 = r9 / r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0120, code lost:
        r9 = r9 * r14;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static long a(java.lang.String r14, com.google.android.exoplayer2.text.ttml.TtmlDecoder.FrameAndTickRate r15) {
        /*
            java.util.regex.Pattern r0 = d
            java.util.regex.Matcher r0 = r0.matcher(r14)
            boolean r1 = r0.matches()
            r2 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            r4 = 5
            r5 = 4
            r6 = 3
            r7 = 2
            r8 = 1
            if (r1 == 0) goto L_0x009a
            java.lang.String r14 = r0.group(r8)
            java.lang.Object r14 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r14)
            java.lang.String r14 = (java.lang.String) r14
            long r8 = java.lang.Long.parseLong(r14)
            r10 = 3600(0xe10, double:1.7786E-320)
            long r8 = r8 * r10
            double r8 = (double) r8
            java.lang.String r14 = r0.group(r7)
            java.lang.Object r14 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r14)
            java.lang.String r14 = (java.lang.String) r14
            long r10 = java.lang.Long.parseLong(r14)
            r12 = 60
            long r10 = r10 * r12
            double r10 = (double) r10
            java.lang.Double.isNaN(r8)
            java.lang.Double.isNaN(r10)
            double r8 = r8 + r10
            java.lang.String r14 = r0.group(r6)
            java.lang.Object r14 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r14)
            java.lang.String r14 = (java.lang.String) r14
            long r6 = java.lang.Long.parseLong(r14)
            double r6 = (double) r6
            java.lang.Double.isNaN(r6)
            double r8 = r8 + r6
            java.lang.String r14 = r0.group(r5)
            r5 = 0
            if (r14 == 0) goto L_0x0063
            double r10 = java.lang.Double.parseDouble(r14)
            goto L_0x0064
        L_0x0063:
            r10 = r5
        L_0x0064:
            double r8 = r8 + r10
            java.lang.String r14 = r0.group(r4)
            if (r14 == 0) goto L_0x0075
            long r10 = java.lang.Long.parseLong(r14)
            float r14 = (float) r10
            float r1 = r15.a
            float r14 = r14 / r1
            double r10 = (double) r14
            goto L_0x0076
        L_0x0075:
            r10 = r5
        L_0x0076:
            double r8 = r8 + r10
            r14 = 6
            java.lang.String r14 = r0.group(r14)
            if (r14 == 0) goto L_0x0095
            long r0 = java.lang.Long.parseLong(r14)
            double r0 = (double) r0
            int r14 = r15.b
            double r4 = (double) r14
            java.lang.Double.isNaN(r0)
            java.lang.Double.isNaN(r4)
            double r0 = r0 / r4
            float r14 = r15.a
            double r14 = (double) r14
            java.lang.Double.isNaN(r14)
            double r5 = r0 / r14
        L_0x0095:
            double r8 = r8 + r5
            double r8 = r8 * r2
            long r14 = (long) r8
            return r14
        L_0x009a:
            java.util.regex.Pattern r0 = e
            java.util.regex.Matcher r0 = r0.matcher(r14)
            boolean r1 = r0.matches()
            if (r1 == 0) goto L_0x0126
            java.lang.String r14 = r0.group(r8)
            java.lang.Object r14 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r14)
            java.lang.String r14 = (java.lang.String) r14
            double r9 = java.lang.Double.parseDouble(r14)
            java.lang.String r14 = r0.group(r7)
            java.lang.Object r14 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r14)
            java.lang.String r14 = (java.lang.String) r14
            int r0 = r14.hashCode()
            switch(r0) {
                case 102: goto L_0x00f7;
                case 104: goto L_0x00ed;
                case 109: goto L_0x00e3;
                case 115: goto L_0x00d9;
                case 116: goto L_0x00d0;
                case 3494: goto L_0x00c6;
                default: goto L_0x00c5;
            }
        L_0x00c5:
            goto L_0x0101
        L_0x00c6:
            java.lang.String r0 = "ms"
            boolean r14 = r14.equals(r0)
            if (r14 == 0) goto L_0x0101
            r4 = 3
            goto L_0x0102
        L_0x00d0:
            java.lang.String r0 = "t"
            boolean r14 = r14.equals(r0)
            if (r14 == 0) goto L_0x0101
            goto L_0x0102
        L_0x00d9:
            java.lang.String r0 = "s"
            boolean r14 = r14.equals(r0)
            if (r14 == 0) goto L_0x0101
            r4 = 2
            goto L_0x0102
        L_0x00e3:
            java.lang.String r0 = "m"
            boolean r14 = r14.equals(r0)
            if (r14 == 0) goto L_0x0101
            r4 = 1
            goto L_0x0102
        L_0x00ed:
            java.lang.String r0 = "h"
            boolean r14 = r14.equals(r0)
            if (r14 == 0) goto L_0x0101
            r4 = 0
            goto L_0x0102
        L_0x00f7:
            java.lang.String r0 = "f"
            boolean r14 = r14.equals(r0)
            if (r14 == 0) goto L_0x0101
            r4 = 4
            goto L_0x0102
        L_0x0101:
            r4 = -1
        L_0x0102:
            switch(r4) {
                case 0: goto L_0x011b;
                case 1: goto L_0x0118;
                case 2: goto L_0x0122;
                case 3: goto L_0x0111;
                case 4: goto L_0x010a;
                case 5: goto L_0x0106;
                default: goto L_0x0105;
            }
        L_0x0105:
            goto L_0x0122
        L_0x0106:
            int r14 = r15.c
            double r14 = (double) r14
            goto L_0x010d
        L_0x010a:
            float r14 = r15.a
            double r14 = (double) r14
        L_0x010d:
            java.lang.Double.isNaN(r14)
            goto L_0x0116
        L_0x0111:
            r14 = 4652007308841189376(0x408f400000000000, double:1000.0)
        L_0x0116:
            double r9 = r9 / r14
            goto L_0x0122
        L_0x0118:
            r14 = 4633641066610819072(0x404e000000000000, double:60.0)
            goto L_0x0120
        L_0x011b:
            r14 = 4660134898793709568(0x40ac200000000000, double:3600.0)
        L_0x0120:
            double r9 = r9 * r14
        L_0x0122:
            double r9 = r9 * r2
            long r14 = (long) r9
            return r14
        L_0x0126:
            com.google.android.exoplayer2.text.SubtitleDecoderException r15 = new com.google.android.exoplayer2.text.SubtitleDecoderException
            java.lang.String r14 = java.lang.String.valueOf(r14)
            int r0 = r14.length()
            java.lang.String r1 = "Malformed time expression: "
            if (r0 == 0) goto L_0x0139
            java.lang.String r14 = r1.concat(r14)
            goto L_0x013e
        L_0x0139:
            java.lang.String r14 = new java.lang.String
            r14.<init>(r1)
        L_0x013e:
            r15.<init>((java.lang.String) r14)
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.ttml.TtmlDecoder.a(java.lang.String, com.google.android.exoplayer2.text.ttml.TtmlDecoder$FrameAndTickRate):long");
    }

    private static CellResolution a(XmlPullParser xmlPullParser, CellResolution cellResolution) {
        String attributeValue = xmlPullParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "cellResolution");
        if (attributeValue == null) {
            return cellResolution;
        }
        Matcher matcher = j.matcher(attributeValue);
        if (!matcher.matches()) {
            String valueOf = String.valueOf(attributeValue);
            Log.c("TtmlDecoder", valueOf.length() != 0 ? "Ignoring malformed cell resolution: ".concat(valueOf) : new String("Ignoring malformed cell resolution: "));
            return cellResolution;
        }
        try {
            int parseInt = Integer.parseInt((String) Assertions.b((Object) matcher.group(1)));
            int parseInt2 = Integer.parseInt((String) Assertions.b((Object) matcher.group(2)));
            if (parseInt != 0 && parseInt2 != 0) {
                return new CellResolution(parseInt2);
            }
            throw new SubtitleDecoderException(new StringBuilder(47).append("Invalid cell resolution ").append(parseInt).append(" ").append(parseInt2).toString());
        } catch (NumberFormatException e2) {
            String valueOf2 = String.valueOf(attributeValue);
            Log.c("TtmlDecoder", valueOf2.length() != 0 ? "Ignoring malformed cell resolution: ".concat(valueOf2) : new String("Ignoring malformed cell resolution: "));
            return cellResolution;
        }
    }

    private static TtsExtent a(XmlPullParser xmlPullParser) {
        String d2 = XmlPullParserUtil.d(xmlPullParser, "extent");
        if (d2 == null) {
            return null;
        }
        Matcher matcher = i.matcher(d2);
        if (!matcher.matches()) {
            String valueOf = String.valueOf(d2);
            Log.c("TtmlDecoder", valueOf.length() != 0 ? "Ignoring non-pixel tts extent: ".concat(valueOf) : new String("Ignoring non-pixel tts extent: "));
            return null;
        }
        try {
            return new TtsExtent(Integer.parseInt((String) Assertions.b((Object) matcher.group(1))), Integer.parseInt((String) Assertions.b((Object) matcher.group(2))));
        } catch (NumberFormatException e2) {
            String valueOf2 = String.valueOf(d2);
            Log.c("TtmlDecoder", valueOf2.length() != 0 ? "Ignoring malformed tts extent: ".concat(valueOf2) : new String("Ignoring malformed tts extent: "));
            return null;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.exoplayer2.text.ttml.TtmlNode a(org.xmlpull.v1.XmlPullParser r20, com.google.android.exoplayer2.text.ttml.TtmlNode r21, java.util.Map r22, com.google.android.exoplayer2.text.ttml.TtmlDecoder.FrameAndTickRate r23) {
        /*
            r0 = r20
            r9 = r21
            r1 = r23
            int r2 = r20.getAttributeCount()
            r3 = 0
            com.google.android.exoplayer2.text.ttml.TtmlStyle r5 = a((org.xmlpull.v1.XmlPullParser) r0, (com.google.android.exoplayer2.text.ttml.TtmlStyle) r3)
            java.lang.String r4 = ""
            r10 = r3
            r12 = r10
            r11 = r4
            r3 = 0
            r13 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r15 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r17 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L_0x0024:
            if (r3 >= r2) goto L_0x00b4
            java.lang.String r4 = r0.getAttributeName(r3)
            java.lang.String r8 = r0.getAttributeValue(r3)
            int r19 = r4.hashCode()
            r6 = 1
            switch(r19) {
                case -934795532: goto L_0x0069;
                case 99841: goto L_0x005f;
                case 100571: goto L_0x0055;
                case 93616297: goto L_0x004b;
                case 109780401: goto L_0x0041;
                case 1292595405: goto L_0x0037;
                default: goto L_0x0036;
            }
        L_0x0036:
            goto L_0x0073
        L_0x0037:
            java.lang.String r7 = "backgroundImage"
            boolean r4 = r4.equals(r7)
            if (r4 == 0) goto L_0x0073
            r4 = 5
            goto L_0x0074
        L_0x0041:
            java.lang.String r7 = "style"
            boolean r4 = r4.equals(r7)
            if (r4 == 0) goto L_0x0073
            r4 = 3
            goto L_0x0074
        L_0x004b:
            java.lang.String r7 = "begin"
            boolean r4 = r4.equals(r7)
            if (r4 == 0) goto L_0x0073
            r4 = 0
            goto L_0x0074
        L_0x0055:
            java.lang.String r7 = "end"
            boolean r4 = r4.equals(r7)
            if (r4 == 0) goto L_0x0073
            r4 = 1
            goto L_0x0074
        L_0x005f:
            java.lang.String r7 = "dur"
            boolean r4 = r4.equals(r7)
            if (r4 == 0) goto L_0x0073
            r4 = 2
            goto L_0x0074
        L_0x0069:
            java.lang.String r7 = "region"
            boolean r4 = r4.equals(r7)
            if (r4 == 0) goto L_0x0073
            r4 = 4
            goto L_0x0074
        L_0x0073:
            r4 = -1
        L_0x0074:
            switch(r4) {
                case 0: goto L_0x00aa;
                case 1: goto L_0x00a3;
                case 2: goto L_0x009c;
                case 3: goto L_0x0091;
                case 4: goto L_0x0087;
                case 5: goto L_0x0078;
                default: goto L_0x0077;
            }
        L_0x0077:
            goto L_0x0084
        L_0x0078:
            java.lang.String r4 = "#"
            boolean r4 = r8.startsWith(r4)
            if (r4 == 0) goto L_0x0084
            java.lang.String r12 = r8.substring(r6)
        L_0x0084:
            r4 = r22
            goto L_0x00b0
        L_0x0087:
            r4 = r22
            boolean r6 = r4.containsKey(r8)
            if (r6 == 0) goto L_0x00b0
            r11 = r8
            goto L_0x00b0
        L_0x0091:
            r4 = r22
            java.lang.String[] r6 = a((java.lang.String) r8)
            int r7 = r6.length
            if (r7 <= 0) goto L_0x00b0
            r10 = r6
            goto L_0x00b0
        L_0x009c:
            r4 = r22
            long r17 = a((java.lang.String) r8, (com.google.android.exoplayer2.text.ttml.TtmlDecoder.FrameAndTickRate) r1)
            goto L_0x00b0
        L_0x00a3:
            r4 = r22
            long r15 = a((java.lang.String) r8, (com.google.android.exoplayer2.text.ttml.TtmlDecoder.FrameAndTickRate) r1)
            goto L_0x00b0
        L_0x00aa:
            r4 = r22
            long r13 = a((java.lang.String) r8, (com.google.android.exoplayer2.text.ttml.TtmlDecoder.FrameAndTickRate) r1)
        L_0x00b0:
            int r3 = r3 + 1
            goto L_0x0024
        L_0x00b4:
            if (r9 == 0) goto L_0x00d0
            long r1 = r9.b
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r6 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r6 == 0) goto L_0x00d5
            int r1 = (r13 > r3 ? 1 : (r13 == r3 ? 0 : -1))
            if (r1 == 0) goto L_0x00c8
            long r1 = r9.b
            long r13 = r13 + r1
        L_0x00c8:
            int r1 = (r15 > r3 ? 1 : (r15 == r3 ? 0 : -1))
            if (r1 == 0) goto L_0x00d5
            long r1 = r9.b
            long r15 = r15 + r1
            goto L_0x00d5
        L_0x00d0:
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L_0x00d5:
            r1 = r13
            int r6 = (r15 > r3 ? 1 : (r15 == r3 ? 0 : -1))
            if (r6 != 0) goto L_0x00ee
            int r6 = (r17 > r3 ? 1 : (r17 == r3 ? 0 : -1))
            if (r6 == 0) goto L_0x00e3
            long r17 = r1 + r17
            r3 = r17
            goto L_0x00ef
        L_0x00e3:
            if (r9 == 0) goto L_0x00ee
            long r6 = r9.c
            int r8 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r8 == 0) goto L_0x00ee
            long r3 = r9.c
            goto L_0x00ef
        L_0x00ee:
            r3 = r15
        L_0x00ef:
            java.lang.String r0 = r20.getName()
            r6 = r10
            r7 = r11
            r8 = r12
            r9 = r21
            com.google.android.exoplayer2.text.ttml.TtmlNode r0 = com.google.android.exoplayer2.text.ttml.TtmlNode.a(r0, r1, r3, r5, r6, r7, r8, r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.ttml.TtmlDecoder.a(org.xmlpull.v1.XmlPullParser, com.google.android.exoplayer2.text.ttml.TtmlNode, java.util.Map, com.google.android.exoplayer2.text.ttml.TtmlDecoder$FrameAndTickRate):com.google.android.exoplayer2.text.ttml.TtmlNode");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.exoplayer2.text.ttml.TtmlRegion a(org.xmlpull.v1.XmlPullParser r17, com.google.android.exoplayer2.text.ttml.TtmlDecoder.CellResolution r18, com.google.android.exoplayer2.text.ttml.TtmlDecoder.TtsExtent r19) {
        /*
            r0 = r17
            r1 = r19
            java.lang.String r2 = "id"
            java.lang.String r4 = com.google.android.exoplayer2.util.XmlPullParserUtil.d(r0, r2)
            r2 = 0
            if (r4 != 0) goto L_0x000e
            return r2
        L_0x000e:
            java.lang.String r3 = "origin"
            java.lang.String r3 = com.google.android.exoplayer2.util.XmlPullParserUtil.d(r0, r3)
            java.lang.String r5 = "TtmlDecoder"
            if (r3 == 0) goto L_0x024a
            java.util.regex.Pattern r6 = h
            java.util.regex.Matcher r6 = r6.matcher(r3)
            java.util.regex.Pattern r7 = i
            java.util.regex.Matcher r8 = r7.matcher(r3)
            boolean r9 = r6.matches()
            java.lang.String r10 = "Ignoring region with malformed origin: "
            java.lang.String r11 = "Ignoring region with missing tts:extent: "
            r12 = 1120403456(0x42c80000, float:100.0)
            r13 = 2
            r14 = 1
            if (r9 == 0) goto L_0x006f
            java.lang.String r8 = r6.group(r14)     // Catch:{ NumberFormatException -> 0x0056 }
            java.lang.Object r8 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r8)     // Catch:{ NumberFormatException -> 0x0056 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ NumberFormatException -> 0x0056 }
            float r8 = java.lang.Float.parseFloat(r8)     // Catch:{ NumberFormatException -> 0x0056 }
            float r8 = r8 / r12
            java.lang.String r6 = r6.group(r13)     // Catch:{ NumberFormatException -> 0x0056 }
            java.lang.Object r6 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r6)     // Catch:{ NumberFormatException -> 0x0056 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ NumberFormatException -> 0x0056 }
            float r6 = java.lang.Float.parseFloat(r6)     // Catch:{ NumberFormatException -> 0x0056 }
            float r6 = r6 / r12
            r16 = r8
            r8 = r6
            r6 = r16
            goto L_0x00b5
        L_0x0056:
            r0 = move-exception
            java.lang.String r0 = java.lang.String.valueOf(r3)
            int r1 = r0.length()
            if (r1 == 0) goto L_0x0066
            java.lang.String r0 = r10.concat(r0)
            goto L_0x006b
        L_0x0066:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r10)
        L_0x006b:
            com.google.android.exoplayer2.util.Log.c(r5, r0)
            return r2
        L_0x006f:
            boolean r6 = r8.matches()
            if (r6 == 0) goto L_0x0230
            if (r1 != 0) goto L_0x008f
            java.lang.String r0 = java.lang.String.valueOf(r3)
            int r1 = r0.length()
            if (r1 == 0) goto L_0x0086
            java.lang.String r0 = r11.concat(r0)
            goto L_0x008b
        L_0x0086:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r11)
        L_0x008b:
            com.google.android.exoplayer2.util.Log.c(r5, r0)
            return r2
        L_0x008f:
            java.lang.String r6 = r8.group(r14)     // Catch:{ NumberFormatException -> 0x0217 }
            java.lang.Object r6 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r6)     // Catch:{ NumberFormatException -> 0x0217 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ NumberFormatException -> 0x0217 }
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ NumberFormatException -> 0x0217 }
            java.lang.String r8 = r8.group(r13)     // Catch:{ NumberFormatException -> 0x0217 }
            java.lang.Object r8 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r8)     // Catch:{ NumberFormatException -> 0x0217 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ NumberFormatException -> 0x0217 }
            int r8 = java.lang.Integer.parseInt(r8)     // Catch:{ NumberFormatException -> 0x0217 }
            float r6 = (float) r6     // Catch:{ NumberFormatException -> 0x0217 }
            int r9 = r1.a     // Catch:{ NumberFormatException -> 0x0217 }
            float r9 = (float) r9     // Catch:{ NumberFormatException -> 0x0217 }
            float r6 = r6 / r9
            float r8 = (float) r8     // Catch:{ NumberFormatException -> 0x0217 }
            int r9 = r1.b     // Catch:{ NumberFormatException -> 0x0217 }
            float r9 = (float) r9
            float r8 = r8 / r9
        L_0x00b5:
            java.lang.String r9 = "extent"
            java.lang.String r9 = com.google.android.exoplayer2.util.XmlPullParserUtil.d(r0, r9)
            if (r9 == 0) goto L_0x0211
            java.util.regex.Pattern r10 = h
            java.util.regex.Matcher r10 = r10.matcher(r9)
            java.util.regex.Matcher r7 = r7.matcher(r9)
            boolean r9 = r10.matches()
            java.lang.String r15 = "Ignoring region with malformed extent: "
            if (r9 == 0) goto L_0x0109
            java.lang.String r1 = r10.group(r14)     // Catch:{ NumberFormatException -> 0x00f0 }
            java.lang.Object r1 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r1)     // Catch:{ NumberFormatException -> 0x00f0 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ NumberFormatException -> 0x00f0 }
            float r1 = java.lang.Float.parseFloat(r1)     // Catch:{ NumberFormatException -> 0x00f0 }
            float r1 = r1 / r12
            java.lang.String r7 = r10.group(r13)     // Catch:{ NumberFormatException -> 0x00f0 }
            java.lang.Object r7 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r7)     // Catch:{ NumberFormatException -> 0x00f0 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ NumberFormatException -> 0x00f0 }
            float r2 = java.lang.Float.parseFloat(r7)     // Catch:{ NumberFormatException -> 0x00f0 }
            float r2 = r2 / r12
            r9 = r1
            r10 = r2
            goto L_0x0150
        L_0x00f0:
            r0 = move-exception
            java.lang.String r0 = java.lang.String.valueOf(r3)
            int r1 = r0.length()
            if (r1 == 0) goto L_0x0100
            java.lang.String r0 = r15.concat(r0)
            goto L_0x0105
        L_0x0100:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r15)
        L_0x0105:
            com.google.android.exoplayer2.util.Log.c(r5, r0)
            return r2
        L_0x0109:
            boolean r9 = r7.matches()
            if (r9 == 0) goto L_0x01f7
            if (r1 != 0) goto L_0x0129
            java.lang.String r0 = java.lang.String.valueOf(r3)
            int r1 = r0.length()
            if (r1 == 0) goto L_0x0120
            java.lang.String r0 = r11.concat(r0)
            goto L_0x0125
        L_0x0120:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r11)
        L_0x0125:
            com.google.android.exoplayer2.util.Log.c(r5, r0)
            return r2
        L_0x0129:
            java.lang.String r9 = r7.group(r14)     // Catch:{ NumberFormatException -> 0x01de }
            java.lang.Object r9 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r9)     // Catch:{ NumberFormatException -> 0x01de }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ NumberFormatException -> 0x01de }
            int r9 = java.lang.Integer.parseInt(r9)     // Catch:{ NumberFormatException -> 0x01de }
            java.lang.String r7 = r7.group(r13)     // Catch:{ NumberFormatException -> 0x01de }
            java.lang.Object r7 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r7)     // Catch:{ NumberFormatException -> 0x01de }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ NumberFormatException -> 0x01de }
            int r7 = java.lang.Integer.parseInt(r7)     // Catch:{ NumberFormatException -> 0x01de }
            float r9 = (float) r9     // Catch:{ NumberFormatException -> 0x01de }
            int r10 = r1.a     // Catch:{ NumberFormatException -> 0x01de }
            float r10 = (float) r10     // Catch:{ NumberFormatException -> 0x01de }
            float r9 = r9 / r10
            float r7 = (float) r7     // Catch:{ NumberFormatException -> 0x01de }
            int r1 = r1.b     // Catch:{ NumberFormatException -> 0x01de }
            float r1 = (float) r1
            float r7 = r7 / r1
            r10 = r7
        L_0x0150:
            java.lang.String r1 = "displayAlign"
            java.lang.String r1 = com.google.android.exoplayer2.util.XmlPullParserUtil.d(r0, r1)
            r2 = -1
            r3 = 0
            if (r1 == 0) goto L_0x018d
            java.lang.String r1 = com.dreamers.exoplayercore.repack.C0000a.a((java.lang.String) r1)
            int r5 = r1.hashCode()
            switch(r5) {
                case -1364013995: goto L_0x0170;
                case 92734940: goto L_0x0166;
                default: goto L_0x0165;
            }
        L_0x0165:
            goto L_0x017a
        L_0x0166:
            java.lang.String r5 = "after"
            boolean r1 = r1.equals(r5)
            if (r1 == 0) goto L_0x017a
            r1 = 1
            goto L_0x017b
        L_0x0170:
            java.lang.String r5 = "center"
            boolean r1 = r1.equals(r5)
            if (r1 == 0) goto L_0x017a
            r1 = 0
            goto L_0x017b
        L_0x017a:
            r1 = -1
        L_0x017b:
            switch(r1) {
                case 0: goto L_0x0184;
                case 1: goto L_0x017f;
                default: goto L_0x017e;
            }
        L_0x017e:
            goto L_0x018d
        L_0x017f:
            float r8 = r8 + r10
            r5 = r18
            r1 = 2
            goto L_0x0190
        L_0x0184:
            r1 = 1073741824(0x40000000, float:2.0)
            float r1 = r10 / r1
            float r8 = r8 + r1
            r5 = r18
            r1 = 1
            goto L_0x0190
        L_0x018d:
            r5 = r18
            r1 = 0
        L_0x0190:
            int r5 = r5.a
            float r5 = (float) r5
            r7 = 1065353216(0x3f800000, float:1.0)
            float r12 = r7 / r5
            java.lang.String r5 = "writingMode"
            java.lang.String r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.d(r0, r5)
            if (r0 == 0) goto L_0x01ce
            java.lang.String r0 = com.dreamers.exoplayercore.repack.C0000a.a((java.lang.String) r0)
            int r5 = r0.hashCode()
            switch(r5) {
                case 3694: goto L_0x01bf;
                case 3553396: goto L_0x01b5;
                case 3553576: goto L_0x01ab;
                default: goto L_0x01aa;
            }
        L_0x01aa:
            goto L_0x01c8
        L_0x01ab:
            java.lang.String r3 = "tbrl"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x01c8
            r2 = 2
            goto L_0x01c8
        L_0x01b5:
            java.lang.String r3 = "tblr"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x01c8
            r2 = 1
            goto L_0x01c8
        L_0x01bf:
            java.lang.String r5 = "tb"
            boolean r0 = r0.equals(r5)
            if (r0 == 0) goto L_0x01c8
            r2 = 0
        L_0x01c8:
            switch(r2) {
                case 0: goto L_0x01d2;
                case 1: goto L_0x01d2;
                case 2: goto L_0x01cc;
                default: goto L_0x01cb;
            }
        L_0x01cb:
            goto L_0x01ce
        L_0x01cc:
            r13 = 1
            goto L_0x01d2
        L_0x01ce:
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            r13 = -2147483648(0xffffffff80000000, float:-0.0)
        L_0x01d2:
            com.google.android.exoplayer2.text.ttml.TtmlRegion r0 = new com.google.android.exoplayer2.text.ttml.TtmlRegion
            r7 = 0
            r11 = 1
            r3 = r0
            r5 = r6
            r6 = r8
            r8 = r1
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            return r0
        L_0x01de:
            r0 = move-exception
            java.lang.String r0 = java.lang.String.valueOf(r3)
            int r1 = r0.length()
            if (r1 == 0) goto L_0x01ee
            java.lang.String r0 = r15.concat(r0)
            goto L_0x01f3
        L_0x01ee:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r15)
        L_0x01f3:
            com.google.android.exoplayer2.util.Log.c(r5, r0)
            return r2
        L_0x01f7:
            java.lang.String r0 = java.lang.String.valueOf(r3)
            int r1 = r0.length()
            java.lang.String r3 = "Ignoring region with unsupported extent: "
            if (r1 == 0) goto L_0x0208
            java.lang.String r0 = r3.concat(r0)
            goto L_0x020d
        L_0x0208:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r3)
        L_0x020d:
            com.google.android.exoplayer2.util.Log.c(r5, r0)
            return r2
        L_0x0211:
            java.lang.String r0 = "Ignoring region without an extent"
        L_0x0213:
            com.google.android.exoplayer2.util.Log.c(r5, r0)
            return r2
        L_0x0217:
            r0 = move-exception
            java.lang.String r0 = java.lang.String.valueOf(r3)
            int r1 = r0.length()
            if (r1 == 0) goto L_0x0227
            java.lang.String r0 = r10.concat(r0)
            goto L_0x022c
        L_0x0227:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r10)
        L_0x022c:
            com.google.android.exoplayer2.util.Log.c(r5, r0)
            return r2
        L_0x0230:
            java.lang.String r0 = java.lang.String.valueOf(r3)
            int r1 = r0.length()
            java.lang.String r3 = "Ignoring region with unsupported origin: "
            if (r1 == 0) goto L_0x0241
            java.lang.String r0 = r3.concat(r0)
            goto L_0x0246
        L_0x0241:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r3)
        L_0x0246:
            com.google.android.exoplayer2.util.Log.c(r5, r0)
            return r2
        L_0x024a:
            java.lang.String r0 = "Ignoring region without an origin"
            goto L_0x0213
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.ttml.TtmlDecoder.a(org.xmlpull.v1.XmlPullParser, com.google.android.exoplayer2.text.ttml.TtmlDecoder$CellResolution, com.google.android.exoplayer2.text.ttml.TtmlDecoder$TtsExtent):com.google.android.exoplayer2.text.ttml.TtmlRegion");
    }

    private static TtmlStyle a(TtmlStyle ttmlStyle) {
        return ttmlStyle == null ? new TtmlStyle() : ttmlStyle;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0111, code lost:
        if (r3.equals("nounderline") != false) goto L_0x0115;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x01a6, code lost:
        if (r3.equals("delimiter") != false) goto L_0x01be;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.exoplayer2.text.ttml.TtmlStyle a(org.xmlpull.v1.XmlPullParser r13, com.google.android.exoplayer2.text.ttml.TtmlStyle r14) {
        /*
            int r0 = r13.getAttributeCount()
            r1 = 0
            r2 = 0
        L_0x0006:
            if (r2 >= r0) goto L_0x03b0
            java.lang.String r3 = r13.getAttributeValue(r2)
            java.lang.String r4 = r13.getAttributeName(r2)
            int r5 = r4.hashCode()
            r6 = 5
            r7 = 4
            r8 = 3
            r9 = -1
            r10 = 2
            r11 = 1
            switch(r5) {
                case -1550943582: goto L_0x00b7;
                case -1224696685: goto L_0x00ad;
                case -1065511464: goto L_0x00a3;
                case -879295043: goto L_0x0098;
                case -734428249: goto L_0x008e;
                case 3355: goto L_0x0084;
                case 3511770: goto L_0x0079;
                case 94842723: goto L_0x006f;
                case 109403361: goto L_0x0064;
                case 110138194: goto L_0x0059;
                case 365601008: goto L_0x004e;
                case 921125321: goto L_0x0042;
                case 1115953443: goto L_0x0036;
                case 1287124693: goto L_0x002b;
                case 1754920356: goto L_0x001f;
                default: goto L_0x001d;
            }
        L_0x001d:
            goto L_0x00c1
        L_0x001f:
            java.lang.String r5 = "multiRowAlign"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x00c1
            r4 = 8
            goto L_0x00c2
        L_0x002b:
            java.lang.String r5 = "backgroundColor"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x00c1
            r4 = 1
            goto L_0x00c2
        L_0x0036:
            java.lang.String r5 = "rubyPosition"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x00c1
            r4 = 11
            goto L_0x00c2
        L_0x0042:
            java.lang.String r5 = "textEmphasis"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x00c1
            r4 = 13
            goto L_0x00c2
        L_0x004e:
            java.lang.String r5 = "fontSize"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x00c1
            r4 = 4
            goto L_0x00c2
        L_0x0059:
            java.lang.String r5 = "textCombine"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x00c1
            r4 = 9
            goto L_0x00c2
        L_0x0064:
            java.lang.String r5 = "shear"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x00c1
            r4 = 14
            goto L_0x00c2
        L_0x006f:
            java.lang.String r5 = "color"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x00c1
            r4 = 2
            goto L_0x00c2
        L_0x0079:
            java.lang.String r5 = "ruby"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x00c1
            r4 = 10
            goto L_0x00c2
        L_0x0084:
            java.lang.String r5 = "id"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x00c1
            r4 = 0
            goto L_0x00c2
        L_0x008e:
            java.lang.String r5 = "fontWeight"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x00c1
            r4 = 5
            goto L_0x00c2
        L_0x0098:
            java.lang.String r5 = "textDecoration"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x00c1
            r4 = 12
            goto L_0x00c2
        L_0x00a3:
            java.lang.String r5 = "textAlign"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x00c1
            r4 = 7
            goto L_0x00c2
        L_0x00ad:
            java.lang.String r5 = "fontFamily"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x00c1
            r4 = 3
            goto L_0x00c2
        L_0x00b7:
            java.lang.String r5 = "fontStyle"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x00c1
            r4 = 6
            goto L_0x00c2
        L_0x00c1:
            r4 = -1
        L_0x00c2:
            java.lang.String r5 = "TtmlDecoder"
            switch(r4) {
                case 0: goto L_0x039a;
                case 1: goto L_0x037a;
                case 2: goto L_0x035a;
                case 3: goto L_0x0353;
                case 4: goto L_0x024f;
                case 5: goto L_0x0241;
                case 6: goto L_0x0233;
                case 7: goto L_0x0227;
                case 8: goto L_0x021b;
                case 9: goto L_0x01e3;
                case 10: goto L_0x0176;
                case 11: goto L_0x0142;
                case 12: goto L_0x00e1;
                case 13: goto L_0x00d5;
                case 14: goto L_0x00c9;
                default: goto L_0x00c7;
            }
        L_0x00c7:
            goto L_0x03ac
        L_0x00c9:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r14 = a((com.google.android.exoplayer2.text.ttml.TtmlStyle) r14)
            float r3 = c(r3)
            r14.s = r3
            goto L_0x03ac
        L_0x00d5:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r14 = a((com.google.android.exoplayer2.text.ttml.TtmlStyle) r14)
            com.google.android.exoplayer2.text.ttml.TextEmphasis r3 = com.google.android.exoplayer2.text.ttml.TextEmphasis.a(r3)
            r14.r = r3
            goto L_0x03ac
        L_0x00e1:
            java.lang.String r3 = com.dreamers.exoplayercore.repack.C0000a.a((java.lang.String) r3)
            int r4 = r3.hashCode()
            switch(r4) {
                case -1461280213: goto L_0x010b;
                case -1026963764: goto L_0x0101;
                case 913457136: goto L_0x00f7;
                case 1679736913: goto L_0x00ed;
                default: goto L_0x00ec;
            }
        L_0x00ec:
            goto L_0x0114
        L_0x00ed:
            java.lang.String r4 = "linethrough"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0114
            r8 = 0
            goto L_0x0115
        L_0x00f7:
            java.lang.String r4 = "nolinethrough"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0114
            r8 = 1
            goto L_0x0115
        L_0x0101:
            java.lang.String r4 = "underline"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0114
            r8 = 2
            goto L_0x0115
        L_0x010b:
            java.lang.String r4 = "nounderline"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0114
            goto L_0x0115
        L_0x0114:
            r8 = -1
        L_0x0115:
            switch(r8) {
                case 0: goto L_0x0138;
                case 1: goto L_0x012e;
                case 2: goto L_0x0124;
                case 3: goto L_0x011a;
                default: goto L_0x0118;
            }
        L_0x0118:
            goto L_0x03ac
        L_0x011a:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r14 = a((com.google.android.exoplayer2.text.ttml.TtmlStyle) r14)
            com.google.android.exoplayer2.text.ttml.TtmlStyle r14 = r14.b((boolean) r1)
            goto L_0x03ac
        L_0x0124:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r14 = a((com.google.android.exoplayer2.text.ttml.TtmlStyle) r14)
            com.google.android.exoplayer2.text.ttml.TtmlStyle r14 = r14.b((boolean) r11)
            goto L_0x03ac
        L_0x012e:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r14 = a((com.google.android.exoplayer2.text.ttml.TtmlStyle) r14)
            com.google.android.exoplayer2.text.ttml.TtmlStyle r14 = r14.a((boolean) r1)
            goto L_0x03ac
        L_0x0138:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r14 = a((com.google.android.exoplayer2.text.ttml.TtmlStyle) r14)
            com.google.android.exoplayer2.text.ttml.TtmlStyle r14 = r14.a((boolean) r11)
            goto L_0x03ac
        L_0x0142:
            java.lang.String r3 = com.dreamers.exoplayercore.repack.C0000a.a((java.lang.String) r3)
            int r4 = r3.hashCode()
            switch(r4) {
                case -1392885889: goto L_0x0158;
                case 92734940: goto L_0x014e;
                default: goto L_0x014d;
            }
        L_0x014d:
            goto L_0x0161
        L_0x014e:
            java.lang.String r4 = "after"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0161
            r9 = 1
            goto L_0x0161
        L_0x0158:
            java.lang.String r4 = "before"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0161
            r9 = 0
        L_0x0161:
            switch(r9) {
                case 0: goto L_0x016e;
                case 1: goto L_0x0166;
                default: goto L_0x0164;
            }
        L_0x0164:
            goto L_0x03ac
        L_0x0166:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r14 = a((com.google.android.exoplayer2.text.ttml.TtmlStyle) r14)
            r14.n = r10
            goto L_0x03ac
        L_0x016e:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r14 = a((com.google.android.exoplayer2.text.ttml.TtmlStyle) r14)
            r14.n = r11
            goto L_0x03ac
        L_0x0176:
            java.lang.String r3 = com.dreamers.exoplayercore.repack.C0000a.a((java.lang.String) r3)
            int r4 = r3.hashCode()
            switch(r4) {
                case -618561360: goto L_0x01b3;
                case -410956671: goto L_0x01a9;
                case -250518009: goto L_0x01a0;
                case -136074796: goto L_0x0196;
                case 3016401: goto L_0x018c;
                case 3556653: goto L_0x0182;
                default: goto L_0x0181;
            }
        L_0x0181:
            goto L_0x01bd
        L_0x0182:
            java.lang.String r4 = "text"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x01bd
            r6 = 3
            goto L_0x01be
        L_0x018c:
            java.lang.String r4 = "base"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x01bd
            r6 = 1
            goto L_0x01be
        L_0x0196:
            java.lang.String r4 = "textContainer"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x01bd
            r6 = 4
            goto L_0x01be
        L_0x01a0:
            java.lang.String r4 = "delimiter"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x01bd
            goto L_0x01be
        L_0x01a9:
            java.lang.String r4 = "container"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x01bd
            r6 = 0
            goto L_0x01be
        L_0x01b3:
            java.lang.String r4 = "baseContainer"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x01bd
            r6 = 2
            goto L_0x01be
        L_0x01bd:
            r6 = -1
        L_0x01be:
            switch(r6) {
                case 0: goto L_0x01db;
                case 1: goto L_0x01d3;
                case 2: goto L_0x01d3;
                case 3: goto L_0x01cb;
                case 4: goto L_0x01cb;
                case 5: goto L_0x01c3;
                default: goto L_0x01c1;
            }
        L_0x01c1:
            goto L_0x03ac
        L_0x01c3:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r14 = a((com.google.android.exoplayer2.text.ttml.TtmlStyle) r14)
            r14.m = r7
            goto L_0x03ac
        L_0x01cb:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r14 = a((com.google.android.exoplayer2.text.ttml.TtmlStyle) r14)
            r14.m = r8
            goto L_0x03ac
        L_0x01d3:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r14 = a((com.google.android.exoplayer2.text.ttml.TtmlStyle) r14)
            r14.m = r10
            goto L_0x03ac
        L_0x01db:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r14 = a((com.google.android.exoplayer2.text.ttml.TtmlStyle) r14)
            r14.m = r11
            goto L_0x03ac
        L_0x01e3:
            java.lang.String r3 = com.dreamers.exoplayercore.repack.C0000a.a((java.lang.String) r3)
            int r4 = r3.hashCode()
            switch(r4) {
                case 96673: goto L_0x01f9;
                case 3387192: goto L_0x01ef;
                default: goto L_0x01ee;
            }
        L_0x01ee:
            goto L_0x0202
        L_0x01ef:
            java.lang.String r4 = "none"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0202
            r9 = 0
            goto L_0x0202
        L_0x01f9:
            java.lang.String r4 = "all"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0202
            r9 = 1
        L_0x0202:
            switch(r9) {
                case 0: goto L_0x0211;
                case 1: goto L_0x0207;
                default: goto L_0x0205;
            }
        L_0x0205:
            goto L_0x03ac
        L_0x0207:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r14 = a((com.google.android.exoplayer2.text.ttml.TtmlStyle) r14)
            com.google.android.exoplayer2.text.ttml.TtmlStyle r14 = r14.c(r11)
            goto L_0x03ac
        L_0x0211:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r14 = a((com.google.android.exoplayer2.text.ttml.TtmlStyle) r14)
            com.google.android.exoplayer2.text.ttml.TtmlStyle r14 = r14.c(r1)
            goto L_0x03ac
        L_0x021b:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r14 = a((com.google.android.exoplayer2.text.ttml.TtmlStyle) r14)
            android.text.Layout$Alignment r3 = b(r3)
            r14.p = r3
            goto L_0x03ac
        L_0x0227:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r14 = a((com.google.android.exoplayer2.text.ttml.TtmlStyle) r14)
            android.text.Layout$Alignment r3 = b(r3)
            r14.o = r3
            goto L_0x03ac
        L_0x0233:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r14 = a((com.google.android.exoplayer2.text.ttml.TtmlStyle) r14)
            java.lang.String r4 = "italic"
            boolean r3 = r4.equalsIgnoreCase(r3)
            r14.i = r3
            goto L_0x03ac
        L_0x0241:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r14 = a((com.google.android.exoplayer2.text.ttml.TtmlStyle) r14)
            java.lang.String r4 = "bold"
            boolean r3 = r4.equalsIgnoreCase(r3)
            r14.h = r3
            goto L_0x03ac
        L_0x024f:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r14 = a((com.google.android.exoplayer2.text.ttml.TtmlStyle) r14)     // Catch:{ SubtitleDecoderException -> 0x0338 }
            java.lang.String r4 = "\\s+"
            java.lang.String[] r4 = com.google.android.exoplayer2.util.Util.a((java.lang.String) r3, (java.lang.String) r4)     // Catch:{ SubtitleDecoderException -> 0x0338 }
            int r6 = r4.length     // Catch:{ SubtitleDecoderException -> 0x0338 }
            if (r6 != r11) goto L_0x0263
            java.util.regex.Pattern r4 = f     // Catch:{ SubtitleDecoderException -> 0x0338 }
            java.util.regex.Matcher r4 = r4.matcher(r3)     // Catch:{ SubtitleDecoderException -> 0x0338 }
            goto L_0x0273
        L_0x0263:
            int r6 = r4.length     // Catch:{ SubtitleDecoderException -> 0x0338 }
            if (r6 != r10) goto L_0x0316
            java.util.regex.Pattern r6 = f     // Catch:{ SubtitleDecoderException -> 0x0338 }
            r4 = r4[r11]     // Catch:{ SubtitleDecoderException -> 0x0338 }
            java.util.regex.Matcher r4 = r6.matcher(r4)     // Catch:{ SubtitleDecoderException -> 0x0338 }
            java.lang.String r6 = "Multiple values in fontSize attribute. Picking the second value for vertical font size and ignoring the first."
            com.google.android.exoplayer2.util.Log.c(r5, r6)     // Catch:{ SubtitleDecoderException -> 0x0338 }
        L_0x0273:
            boolean r6 = r4.matches()     // Catch:{ SubtitleDecoderException -> 0x0338 }
            java.lang.String r7 = "'."
            if (r6 == 0) goto L_0x02ef
            java.lang.String r6 = r4.group(r8)     // Catch:{ SubtitleDecoderException -> 0x0338 }
            java.lang.Object r6 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r6)     // Catch:{ SubtitleDecoderException -> 0x0338 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ SubtitleDecoderException -> 0x0338 }
            int r12 = r6.hashCode()     // Catch:{ SubtitleDecoderException -> 0x0338 }
            switch(r12) {
                case 37: goto L_0x02a1;
                case 3240: goto L_0x0297;
                case 3592: goto L_0x028d;
                default: goto L_0x028c;
            }     // Catch:{ SubtitleDecoderException -> 0x0338 }
        L_0x028c:
            goto L_0x02aa
        L_0x028d:
            java.lang.String r12 = "px"
            boolean r12 = r6.equals(r12)     // Catch:{ SubtitleDecoderException -> 0x0338 }
            if (r12 == 0) goto L_0x02aa
            r9 = 0
            goto L_0x02aa
        L_0x0297:
            java.lang.String r12 = "em"
            boolean r12 = r6.equals(r12)     // Catch:{ SubtitleDecoderException -> 0x0338 }
            if (r12 == 0) goto L_0x02aa
            r9 = 1
            goto L_0x02aa
        L_0x02a1:
            java.lang.String r12 = "%"
            boolean r12 = r6.equals(r12)     // Catch:{ SubtitleDecoderException -> 0x0338 }
            if (r12 == 0) goto L_0x02aa
            r9 = 2
        L_0x02aa:
            switch(r9) {
                case 0: goto L_0x02b6;
                case 1: goto L_0x02b3;
                case 2: goto L_0x02b0;
                default: goto L_0x02ad;
            }     // Catch:{ SubtitleDecoderException -> 0x0338 }
        L_0x02ad:
            com.google.android.exoplayer2.text.SubtitleDecoderException r4 = new com.google.android.exoplayer2.text.SubtitleDecoderException     // Catch:{ SubtitleDecoderException -> 0x0338 }
            goto L_0x02ca
        L_0x02b0:
            r14.j = r8     // Catch:{ SubtitleDecoderException -> 0x0338 }
            goto L_0x02b8
        L_0x02b3:
            r14.j = r10     // Catch:{ SubtitleDecoderException -> 0x0338 }
            goto L_0x02b8
        L_0x02b6:
            r14.j = r11     // Catch:{ SubtitleDecoderException -> 0x0338 }
        L_0x02b8:
            java.lang.String r4 = r4.group(r11)     // Catch:{ SubtitleDecoderException -> 0x0338 }
            java.lang.Object r4 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r4)     // Catch:{ SubtitleDecoderException -> 0x0338 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ SubtitleDecoderException -> 0x0338 }
            float r4 = java.lang.Float.parseFloat(r4)     // Catch:{ SubtitleDecoderException -> 0x0338 }
            r14.k = r4     // Catch:{ SubtitleDecoderException -> 0x0338 }
            goto L_0x03ac
        L_0x02ca:
            java.lang.String r8 = java.lang.String.valueOf(r6)     // Catch:{ SubtitleDecoderException -> 0x0338 }
            int r8 = r8.length()     // Catch:{ SubtitleDecoderException -> 0x0338 }
            int r8 = r8 + 30
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ SubtitleDecoderException -> 0x0338 }
            r9.<init>(r8)     // Catch:{ SubtitleDecoderException -> 0x0338 }
            java.lang.String r8 = "Invalid unit for fontSize: '"
            java.lang.StringBuilder r8 = r9.append(r8)     // Catch:{ SubtitleDecoderException -> 0x0338 }
            java.lang.StringBuilder r6 = r8.append(r6)     // Catch:{ SubtitleDecoderException -> 0x0338 }
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ SubtitleDecoderException -> 0x0338 }
            java.lang.String r6 = r6.toString()     // Catch:{ SubtitleDecoderException -> 0x0338 }
            r4.<init>((java.lang.String) r6)     // Catch:{ SubtitleDecoderException -> 0x0338 }
            throw r4     // Catch:{ SubtitleDecoderException -> 0x0338 }
        L_0x02ef:
            com.google.android.exoplayer2.text.SubtitleDecoderException r4 = new com.google.android.exoplayer2.text.SubtitleDecoderException     // Catch:{ SubtitleDecoderException -> 0x0338 }
            java.lang.String r6 = java.lang.String.valueOf(r3)     // Catch:{ SubtitleDecoderException -> 0x0338 }
            int r6 = r6.length()     // Catch:{ SubtitleDecoderException -> 0x0338 }
            int r6 = r6 + 36
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ SubtitleDecoderException -> 0x0338 }
            r8.<init>(r6)     // Catch:{ SubtitleDecoderException -> 0x0338 }
            java.lang.String r6 = "Invalid expression for fontSize: '"
            java.lang.StringBuilder r6 = r8.append(r6)     // Catch:{ SubtitleDecoderException -> 0x0338 }
            java.lang.StringBuilder r6 = r6.append(r3)     // Catch:{ SubtitleDecoderException -> 0x0338 }
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ SubtitleDecoderException -> 0x0338 }
            java.lang.String r6 = r6.toString()     // Catch:{ SubtitleDecoderException -> 0x0338 }
            r4.<init>((java.lang.String) r6)     // Catch:{ SubtitleDecoderException -> 0x0338 }
            throw r4     // Catch:{ SubtitleDecoderException -> 0x0338 }
        L_0x0316:
            com.google.android.exoplayer2.text.SubtitleDecoderException r6 = new com.google.android.exoplayer2.text.SubtitleDecoderException     // Catch:{ SubtitleDecoderException -> 0x0338 }
            int r4 = r4.length     // Catch:{ SubtitleDecoderException -> 0x0338 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ SubtitleDecoderException -> 0x0338 }
            r8 = 52
            r7.<init>(r8)     // Catch:{ SubtitleDecoderException -> 0x0338 }
            java.lang.String r8 = "Invalid number of entries for fontSize: "
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ SubtitleDecoderException -> 0x0338 }
            java.lang.StringBuilder r4 = r7.append(r4)     // Catch:{ SubtitleDecoderException -> 0x0338 }
            java.lang.String r7 = "."
            java.lang.StringBuilder r4 = r4.append(r7)     // Catch:{ SubtitleDecoderException -> 0x0338 }
            java.lang.String r4 = r4.toString()     // Catch:{ SubtitleDecoderException -> 0x0338 }
            r6.<init>((java.lang.String) r4)     // Catch:{ SubtitleDecoderException -> 0x0338 }
            throw r6     // Catch:{ SubtitleDecoderException -> 0x0338 }
        L_0x0338:
            r4 = move-exception
            java.lang.String r3 = java.lang.String.valueOf(r3)
            int r4 = r3.length()
            java.lang.String r6 = "Failed parsing fontSize value: "
            if (r4 == 0) goto L_0x034a
        L_0x0345:
            java.lang.String r3 = r6.concat(r3)
            goto L_0x034f
        L_0x034a:
            java.lang.String r3 = new java.lang.String
            r3.<init>(r6)
        L_0x034f:
            com.google.android.exoplayer2.util.Log.c(r5, r3)
            goto L_0x03ac
        L_0x0353:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r14 = a((com.google.android.exoplayer2.text.ttml.TtmlStyle) r14)
            r14.a = r3
            goto L_0x03ac
        L_0x035a:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r14 = a((com.google.android.exoplayer2.text.ttml.TtmlStyle) r14)
            int r4 = com.google.android.exoplayer2.util.ColorParser.a(r3)     // Catch:{ IllegalArgumentException -> 0x0366 }
            r14.a((int) r4)     // Catch:{ IllegalArgumentException -> 0x0366 }
            goto L_0x03ac
        L_0x0366:
            r4 = move-exception
            java.lang.String r3 = java.lang.String.valueOf(r3)
            int r4 = r3.length()
            java.lang.String r6 = "Failed parsing color value: "
            if (r4 == 0) goto L_0x0374
            goto L_0x0345
        L_0x0374:
            java.lang.String r3 = new java.lang.String
            r3.<init>(r6)
            goto L_0x034f
        L_0x037a:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r14 = a((com.google.android.exoplayer2.text.ttml.TtmlStyle) r14)
            int r4 = com.google.android.exoplayer2.util.ColorParser.a(r3)     // Catch:{ IllegalArgumentException -> 0x0386 }
            r14.b((int) r4)     // Catch:{ IllegalArgumentException -> 0x0386 }
            goto L_0x03ac
        L_0x0386:
            r4 = move-exception
            java.lang.String r3 = java.lang.String.valueOf(r3)
            int r4 = r3.length()
            java.lang.String r6 = "Failed parsing background value: "
            if (r4 == 0) goto L_0x0394
            goto L_0x0345
        L_0x0394:
            java.lang.String r3 = new java.lang.String
            r3.<init>(r6)
            goto L_0x034f
        L_0x039a:
            java.lang.String r4 = "style"
            java.lang.String r5 = r13.getName()
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x03ac
            com.google.android.exoplayer2.text.ttml.TtmlStyle r14 = a((com.google.android.exoplayer2.text.ttml.TtmlStyle) r14)
            r14.l = r3
        L_0x03ac:
            int r2 = r2 + 1
            goto L_0x0006
        L_0x03b0:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.ttml.TtmlDecoder.a(org.xmlpull.v1.XmlPullParser, com.google.android.exoplayer2.text.ttml.TtmlStyle):com.google.android.exoplayer2.text.ttml.TtmlStyle");
    }

    private static Map a(XmlPullParser xmlPullParser, Map map, CellResolution cellResolution, TtsExtent ttsExtent, Map map2, Map map3) {
        String d2;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.b(xmlPullParser, "style")) {
                String d3 = XmlPullParserUtil.d(xmlPullParser, "style");
                TtmlStyle a = a(xmlPullParser, new TtmlStyle());
                if (d3 != null) {
                    for (String str : a(d3)) {
                        a.a((TtmlStyle) map.get(str));
                    }
                }
                String str2 = a.l;
                if (str2 != null) {
                    map.put(str2, a);
                }
            } else if (XmlPullParserUtil.b(xmlPullParser, "region")) {
                TtmlRegion a2 = a(xmlPullParser, cellResolution, ttsExtent);
                if (a2 != null) {
                    map2.put(a2.a, a2);
                }
            } else if (XmlPullParserUtil.b(xmlPullParser, ComponentDescriptorConstants.METADATA_TARGET)) {
                do {
                    xmlPullParser.next();
                    if (XmlPullParserUtil.b(xmlPullParser, "image") && (d2 = XmlPullParserUtil.d(xmlPullParser, "id")) != null) {
                        map3.put(d2, xmlPullParser.nextText());
                    }
                } while (!XmlPullParserUtil.a(xmlPullParser, ComponentDescriptorConstants.METADATA_TARGET));
            }
        } while (!XmlPullParserUtil.a(xmlPullParser, "head"));
        return map;
    }

    private static String[] a(String str) {
        String trim = str.trim();
        return trim.isEmpty() ? new String[0] : Util.a(trim, "\\s+");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.text.Layout.Alignment b(java.lang.String r1) {
        /*
            java.lang.String r1 = com.dreamers.exoplayercore.repack.C0000a.a((java.lang.String) r1)
            int r0 = r1.hashCode()
            switch(r0) {
                case -1364013995: goto L_0x0034;
                case 100571: goto L_0x002a;
                case 3317767: goto L_0x0020;
                case 108511772: goto L_0x0016;
                case 109757538: goto L_0x000c;
                default: goto L_0x000b;
            }
        L_0x000b:
            goto L_0x003e
        L_0x000c:
            java.lang.String r0 = "start"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x003e
            r1 = 1
            goto L_0x003f
        L_0x0016:
            java.lang.String r0 = "right"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x003e
            r1 = 2
            goto L_0x003f
        L_0x0020:
            java.lang.String r0 = "left"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x003e
            r1 = 0
            goto L_0x003f
        L_0x002a:
            java.lang.String r0 = "end"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x003e
            r1 = 3
            goto L_0x003f
        L_0x0034:
            java.lang.String r0 = "center"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x003e
            r1 = 4
            goto L_0x003f
        L_0x003e:
            r1 = -1
        L_0x003f:
            switch(r1) {
                case 0: goto L_0x004a;
                case 1: goto L_0x004a;
                case 2: goto L_0x0047;
                case 3: goto L_0x0047;
                case 4: goto L_0x0044;
                default: goto L_0x0042;
            }
        L_0x0042:
            r1 = 0
            return r1
        L_0x0044:
            android.text.Layout$Alignment r1 = android.text.Layout.Alignment.ALIGN_CENTER
            return r1
        L_0x0047:
            android.text.Layout$Alignment r1 = android.text.Layout.Alignment.ALIGN_OPPOSITE
            return r1
        L_0x004a:
            android.text.Layout$Alignment r1 = android.text.Layout.Alignment.ALIGN_NORMAL
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.ttml.TtmlDecoder.b(java.lang.String):android.text.Layout$Alignment");
    }

    private static float c(String str) {
        Matcher matcher = g.matcher(str);
        if (!matcher.matches()) {
            String valueOf = String.valueOf(str);
            Log.c("TtmlDecoder", valueOf.length() != 0 ? "Invalid value for shear: ".concat(valueOf) : new String("Invalid value for shear: "));
            return Float.MAX_VALUE;
        }
        try {
            return Math.min(100.0f, Math.max(-100.0f, Float.parseFloat((String) Assertions.b((Object) matcher.group(1)))));
        } catch (NumberFormatException e2) {
            String valueOf2 = String.valueOf(str);
            Log.a("TtmlDecoder", valueOf2.length() != 0 ? "Failed to parse shear: ".concat(valueOf2) : new String("Failed to parse shear: "), e2);
            return Float.MAX_VALUE;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:69:0x0153  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0175 A[Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.exoplayer2.text.Subtitle a(byte[] r21, int r22, boolean r23) {
        /*
            r20 = this;
            java.lang.String r0 = ""
            java.lang.String r1 = "http://www.w3.org/ns/ttml#parameter"
            r2 = r20
            org.xmlpull.v1.XmlPullParserFactory r3 = r2.m     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            org.xmlpull.v1.XmlPullParser r3 = r3.newPullParser()     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            java.util.HashMap r10 = new java.util.HashMap     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            r10.<init>()     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            java.util.HashMap r11 = new java.util.HashMap     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            r11.<init>()     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            java.util.HashMap r12 = new java.util.HashMap     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            r12.<init>()     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            com.google.android.exoplayer2.text.ttml.TtmlRegion r4 = new com.google.android.exoplayer2.text.ttml.TtmlRegion     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            r4.<init>(r0)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            r11.put(r0, r4)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            r13 = 0
            r4 = r21
            r5 = r22
            r0.<init>(r4, r13, r5)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            r4 = 0
            r3.setInput(r0, r4)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            java.util.ArrayDeque r14 = new java.util.ArrayDeque     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            r14.<init>()     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            int r0 = r3.getEventType()     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            com.google.android.exoplayer2.text.ttml.TtmlDecoder$FrameAndTickRate r5 = k     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            com.google.android.exoplayer2.text.ttml.TtmlDecoder$CellResolution r6 = l     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            r15 = r4
            r16 = 0
        L_0x0041:
            r7 = 1
            if (r0 == r7) goto L_0x01f3
            java.lang.Object r8 = r14.peek()     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            com.google.android.exoplayer2.text.ttml.TtmlNode r8 = (com.google.android.exoplayer2.text.ttml.TtmlNode) r8     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            r9 = 2
            if (r16 != 0) goto L_0x01dd
            java.lang.String r7 = r3.getName()     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            java.lang.String r13 = "tt"
            if (r0 != r9) goto L_0x01a4
            boolean r0 = r13.equals(r7)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            if (r0 == 0) goto L_0x00ce
            java.lang.String r0 = "frameRate"
            java.lang.String r0 = r3.getAttributeValue(r1, r0)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            if (r0 == 0) goto L_0x0068
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            goto L_0x006a
        L_0x0068:
            r0 = 30
        L_0x006a:
            java.lang.String r4 = "frameRateMultiplier"
            java.lang.String r4 = r3.getAttributeValue(r1, r4)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            if (r4 == 0) goto L_0x0096
            java.lang.String r5 = " "
            java.lang.String[] r4 = com.google.android.exoplayer2.util.Util.a((java.lang.String) r4, (java.lang.String) r5)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            int r5 = r4.length     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            if (r5 != r9) goto L_0x008e
            r17 = 0
            r5 = r4[r17]     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            int r5 = java.lang.Integer.parseInt(r5)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            float r5 = (float) r5     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            r9 = 1
            r4 = r4[r9]     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            float r4 = (float) r4     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            float r5 = r5 / r4
            goto L_0x009b
        L_0x008e:
            com.google.android.exoplayer2.text.SubtitleDecoderException r0 = new com.google.android.exoplayer2.text.SubtitleDecoderException     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            java.lang.String r1 = "frameRateMultiplier doesn't have 2 parts"
            r0.<init>((java.lang.String) r1)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            throw r0     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
        L_0x0096:
            r9 = 1
            r17 = 0
            r5 = 1065353216(0x3f800000, float:1.0)
        L_0x009b:
            com.google.android.exoplayer2.text.ttml.TtmlDecoder$FrameAndTickRate r4 = k     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            int r6 = r4.b     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            java.lang.String r9 = "subFrameRate"
            java.lang.String r9 = r3.getAttributeValue(r1, r9)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            if (r9 == 0) goto L_0x00ab
            int r6 = java.lang.Integer.parseInt(r9)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
        L_0x00ab:
            int r4 = r4.c     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            java.lang.String r9 = "tickRate"
            java.lang.String r9 = r3.getAttributeValue(r1, r9)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            if (r9 == 0) goto L_0x00b9
            int r4 = java.lang.Integer.parseInt(r9)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
        L_0x00b9:
            com.google.android.exoplayer2.text.ttml.TtmlDecoder$FrameAndTickRate r9 = new com.google.android.exoplayer2.text.ttml.TtmlDecoder$FrameAndTickRate     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            float r0 = (float) r0     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            float r0 = r0 * r5
            r9.<init>(r0, r6, r4)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            com.google.android.exoplayer2.text.ttml.TtmlDecoder$CellResolution r0 = l     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            com.google.android.exoplayer2.text.ttml.TtmlDecoder$CellResolution r6 = a((org.xmlpull.v1.XmlPullParser) r3, (com.google.android.exoplayer2.text.ttml.TtmlDecoder.CellResolution) r0)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            com.google.android.exoplayer2.text.ttml.TtmlDecoder$TtsExtent r4 = a((org.xmlpull.v1.XmlPullParser) r3)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            r18 = r4
            goto L_0x00d3
        L_0x00ce:
            r17 = 0
            r18 = r4
            r9 = r5
        L_0x00d3:
            r19 = r6
            boolean r0 = r7.equals(r13)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            java.lang.String r4 = "head"
            if (r0 != 0) goto L_0x014e
            boolean r0 = r7.equals(r4)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            if (r0 != 0) goto L_0x014e
            java.lang.String r0 = "body"
            boolean r0 = r7.equals(r0)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            if (r0 != 0) goto L_0x014e
            java.lang.String r0 = "div"
            boolean r0 = r7.equals(r0)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            if (r0 != 0) goto L_0x014e
            java.lang.String r0 = "p"
            boolean r0 = r7.equals(r0)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            if (r0 != 0) goto L_0x014e
            java.lang.String r0 = "span"
            boolean r0 = r7.equals(r0)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            if (r0 != 0) goto L_0x014e
            java.lang.String r0 = "br"
            boolean r0 = r7.equals(r0)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            if (r0 != 0) goto L_0x014e
            java.lang.String r0 = "style"
            boolean r0 = r7.equals(r0)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            if (r0 != 0) goto L_0x014e
            java.lang.String r0 = "styling"
            boolean r0 = r7.equals(r0)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            if (r0 != 0) goto L_0x014e
            java.lang.String r0 = "layout"
            boolean r0 = r7.equals(r0)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            if (r0 != 0) goto L_0x014e
            java.lang.String r0 = "region"
            boolean r0 = r7.equals(r0)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            if (r0 != 0) goto L_0x014e
            java.lang.String r0 = "metadata"
            boolean r0 = r7.equals(r0)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            if (r0 != 0) goto L_0x014e
            java.lang.String r0 = "image"
            boolean r0 = r7.equals(r0)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            if (r0 != 0) goto L_0x014e
            java.lang.String r0 = "data"
            boolean r0 = r7.equals(r0)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            if (r0 != 0) goto L_0x014e
            java.lang.String r0 = "information"
            boolean r0 = r7.equals(r0)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            if (r0 == 0) goto L_0x014c
            goto L_0x014e
        L_0x014c:
            r0 = 0
            goto L_0x014f
        L_0x014e:
            r0 = 1
        L_0x014f:
            java.lang.String r5 = "TtmlDecoder"
            if (r0 != 0) goto L_0x0175
            java.lang.String r0 = "Ignoring unsupported tag: "
            java.lang.String r4 = r3.getName()     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            int r6 = r4.length()     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            if (r6 == 0) goto L_0x0168
            java.lang.String r0 = r0.concat(r4)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            goto L_0x016e
        L_0x0168:
            java.lang.String r4 = new java.lang.String     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            r4.<init>(r0)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            r0 = r4
        L_0x016e:
            com.google.android.exoplayer2.util.Log.b(r5, r0)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            int r16 = r16 + 1
            r5 = r9
            goto L_0x0196
        L_0x0175:
            boolean r0 = r4.equals(r7)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            if (r0 == 0) goto L_0x0188
            r4 = r3
            r5 = r10
            r6 = r19
            r7 = r18
            r8 = r11
            r13 = r9
            r9 = r12
            a(r4, r5, r6, r7, r8, r9)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            goto L_0x0195
        L_0x0188:
            r13 = r9
            com.google.android.exoplayer2.text.ttml.TtmlNode r0 = a(r3, r8, r11, r13)     // Catch:{ SubtitleDecoderException -> 0x019b }
            r14.push(r0)     // Catch:{ SubtitleDecoderException -> 0x019b }
            if (r8 == 0) goto L_0x0195
            r8.a((com.google.android.exoplayer2.text.ttml.TtmlNode) r0)     // Catch:{ SubtitleDecoderException -> 0x019b }
        L_0x0195:
            r5 = r13
        L_0x0196:
            r4 = r18
            r6 = r19
            goto L_0x01e9
        L_0x019b:
            r0 = move-exception
            java.lang.String r4 = "Suppressing parser error"
            com.google.android.exoplayer2.util.Log.a(r5, r4, r0)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            int r16 = r16 + 1
            goto L_0x0195
        L_0x01a4:
            r17 = 0
            r7 = 4
            if (r0 != r7) goto L_0x01bb
            java.lang.Object r0 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r8)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            com.google.android.exoplayer2.text.ttml.TtmlNode r0 = (com.google.android.exoplayer2.text.ttml.TtmlNode) r0     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            java.lang.String r7 = r3.getText()     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            com.google.android.exoplayer2.text.ttml.TtmlNode r7 = com.google.android.exoplayer2.text.ttml.TtmlNode.a((java.lang.String) r7)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            r0.a((com.google.android.exoplayer2.text.ttml.TtmlNode) r7)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            goto L_0x01e9
        L_0x01bb:
            r7 = 3
            if (r0 != r7) goto L_0x01e9
            java.lang.String r0 = r3.getName()     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            boolean r0 = r0.equals(r13)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            if (r0 == 0) goto L_0x01d9
            com.google.android.exoplayer2.text.ttml.TtmlSubtitle r15 = new com.google.android.exoplayer2.text.ttml.TtmlSubtitle     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            java.lang.Object r0 = r14.peek()     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            com.google.android.exoplayer2.text.ttml.TtmlNode r0 = (com.google.android.exoplayer2.text.ttml.TtmlNode) r0     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            java.lang.Object r0 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r0)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            com.google.android.exoplayer2.text.ttml.TtmlNode r0 = (com.google.android.exoplayer2.text.ttml.TtmlNode) r0     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            r15.<init>(r0, r10, r11, r12)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
        L_0x01d9:
            r14.pop()     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            goto L_0x01e9
        L_0x01dd:
            r17 = 0
            if (r0 != r9) goto L_0x01e4
            int r16 = r16 + 1
            goto L_0x01e9
        L_0x01e4:
            r7 = 3
            if (r0 != r7) goto L_0x01e9
            int r16 = r16 + -1
        L_0x01e9:
            r3.next()     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            int r0 = r3.getEventType()     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            r13 = 0
            goto L_0x0041
        L_0x01f3:
            if (r15 == 0) goto L_0x01f6
            return r15
        L_0x01f6:
            com.google.android.exoplayer2.text.SubtitleDecoderException r0 = new com.google.android.exoplayer2.text.SubtitleDecoderException     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            java.lang.String r1 = "No TTML subtitles found"
            r0.<init>((java.lang.String) r1)     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
            throw r0     // Catch:{ XmlPullParserException -> 0x0207, IOException -> 0x01fe }
        L_0x01fe:
            r0 = move-exception
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r3 = "Unexpected error when reading input."
            r1.<init>(r3, r0)
            throw r1
        L_0x0207:
            r0 = move-exception
            com.google.android.exoplayer2.text.SubtitleDecoderException r1 = new com.google.android.exoplayer2.text.SubtitleDecoderException
            java.lang.String r3 = "Unable to decode source"
            r1.<init>(r3, r0)
            goto L_0x0211
        L_0x0210:
            throw r1
        L_0x0211:
            goto L_0x0210
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.ttml.TtmlDecoder.a(byte[], int, boolean):com.google.android.exoplayer2.text.Subtitle");
    }
}
