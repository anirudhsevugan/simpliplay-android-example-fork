package com.google.android.exoplayer2.source.dash.manifest;

import android.text.TextUtils;
import android.util.Pair;
import android.util.Xml;
import androidx.core.location.LocationRequestCompat;
import com.dreamers.exoplayercore.repack.C0000a;
import com.dreamers.exoplayercore.repack.aC;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.metadata.emsg.EventMessage;
import com.google.android.exoplayer2.source.dash.manifest.SegmentBase;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.UriUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.util.XmlPullParserUtil;
import com.google.appinventor.components.common.PropertyTypeConstants;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

public class DashManifestParser extends DefaultHandler implements ParsingLoadable.Parser {
    private static final Pattern a = Pattern.compile("(\\d+)(?:/(\\d+))?");
    private static final Pattern b = Pattern.compile("CC([1-4])=.*");
    private static final Pattern c = Pattern.compile("([1-9]|[1-5][0-9]|6[0-3])=.*");
    private static final int[] d = {-1, 1, 2, 3, 4, 5, 6, 8, 2, 3, 4, 7, 8, 24, 8, 12, 10, 12, 14, 12, 14};
    private final XmlPullParserFactory e;

    public final class RepresentationInfo {
        public final Format a;
        public final String b;
        public final SegmentBase c;
        public final String d;
        public final ArrayList e;
        public final ArrayList f;

        public RepresentationInfo(Format format, String str, SegmentBase segmentBase, String str2, ArrayList arrayList, ArrayList arrayList2) {
            this.a = format;
            this.b = str;
            this.c = segmentBase;
            this.d = str2;
            this.e = arrayList;
            this.f = arrayList2;
        }
    }

    public DashManifestParser() {
        try {
            this.e = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException e2) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e2);
        }
    }

    private static float a(XmlPullParser xmlPullParser, float f) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, "frameRate");
        if (attributeValue == null) {
            return f;
        }
        Matcher matcher = a.matcher(attributeValue);
        if (!matcher.matches()) {
            return f;
        }
        int parseInt = Integer.parseInt(matcher.group(1));
        String group = matcher.group(2);
        float f2 = (float) parseInt;
        return !TextUtils.isEmpty(group) ? f2 / ((float) Integer.parseInt(group)) : f2;
    }

    private static int a(int i, int i2) {
        if (i == -1) {
            return i2;
        }
        if (i2 == -1) {
            return i;
        }
        Assertions.b(i == i2);
        return i;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int a(java.lang.String r6) {
        /*
            r0 = 0
            if (r6 != 0) goto L_0x0004
            return r0
        L_0x0004:
            int r1 = r6.hashCode()
            r2 = 8
            r3 = 4
            r4 = 2
            r5 = 1
            switch(r1) {
                case -2060497896: goto L_0x0085;
                case -1724546052: goto L_0x007a;
                case -1580883024: goto L_0x006f;
                case -1574842690: goto L_0x0065;
                case -1408024454: goto L_0x005b;
                case 99825: goto L_0x0051;
                case 3343801: goto L_0x0047;
                case 3530173: goto L_0x003c;
                case 552573414: goto L_0x0032;
                case 899152809: goto L_0x0028;
                case 1629013393: goto L_0x001d;
                case 1855372047: goto L_0x0012;
                default: goto L_0x0010;
            }
        L_0x0010:
            goto L_0x0090
        L_0x0012:
            java.lang.String r1 = "supplementary"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0090
            r6 = 2
            goto L_0x0091
        L_0x001d:
            java.lang.String r1 = "emergency"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0090
            r6 = 5
            goto L_0x0091
        L_0x0028:
            java.lang.String r1 = "commentary"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0090
            r6 = 3
            goto L_0x0091
        L_0x0032:
            java.lang.String r1 = "caption"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0090
            r6 = 6
            goto L_0x0091
        L_0x003c:
            java.lang.String r1 = "sign"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0090
            r6 = 9
            goto L_0x0091
        L_0x0047:
            java.lang.String r1 = "main"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0090
            r6 = 0
            goto L_0x0091
        L_0x0051:
            java.lang.String r1 = "dub"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0090
            r6 = 4
            goto L_0x0091
        L_0x005b:
            java.lang.String r1 = "alternate"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0090
            r6 = 1
            goto L_0x0091
        L_0x0065:
            java.lang.String r1 = "forced_subtitle"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0090
            r6 = 7
            goto L_0x0091
        L_0x006f:
            java.lang.String r1 = "enhanced-audio-intelligibility"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0090
            r6 = 11
            goto L_0x0091
        L_0x007a:
            java.lang.String r1 = "description"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0090
            r6 = 10
            goto L_0x0091
        L_0x0085:
            java.lang.String r1 = "subtitle"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0090
            r6 = 8
            goto L_0x0091
        L_0x0090:
            r6 = -1
        L_0x0091:
            switch(r6) {
                case 0: goto L_0x00ad;
                case 1: goto L_0x00ac;
                case 2: goto L_0x00ab;
                case 3: goto L_0x00aa;
                case 4: goto L_0x00a7;
                case 5: goto L_0x00a4;
                case 6: goto L_0x00a1;
                case 7: goto L_0x009e;
                case 8: goto L_0x009e;
                case 9: goto L_0x009b;
                case 10: goto L_0x0098;
                case 11: goto L_0x0095;
                default: goto L_0x0094;
            }
        L_0x0094:
            return r0
        L_0x0095:
            r6 = 2048(0x800, float:2.87E-42)
            return r6
        L_0x0098:
            r6 = 512(0x200, float:7.175E-43)
            return r6
        L_0x009b:
            r6 = 256(0x100, float:3.59E-43)
            return r6
        L_0x009e:
            r6 = 128(0x80, float:1.794E-43)
            return r6
        L_0x00a1:
            r6 = 64
            return r6
        L_0x00a4:
            r6 = 32
            return r6
        L_0x00a7:
            r6 = 16
            return r6
        L_0x00aa:
            return r2
        L_0x00ab:
            return r3
        L_0x00ac:
            return r4
        L_0x00ad:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.a(java.lang.String):int");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int a(java.util.List r6) {
        /*
            r0 = 0
            r1 = 0
            r2 = 0
        L_0x0003:
            int r3 = r6.size()
            if (r1 >= r3) goto L_0x0046
            java.lang.Object r3 = r6.get(r1)
            com.google.android.exoplayer2.source.dash.manifest.Descriptor r3 = (com.google.android.exoplayer2.source.dash.manifest.Descriptor) r3
            java.lang.String r4 = "urn:mpeg:dash:role:2011"
            java.lang.String r5 = r3.a
            boolean r4 = com.dreamers.exoplayercore.repack.C0000a.a((java.lang.CharSequence) r4, (java.lang.CharSequence) r5)
            if (r4 == 0) goto L_0x0043
            java.lang.String r3 = r3.b
            if (r3 == 0) goto L_0x0041
            int r4 = r3.hashCode()
            r5 = 1
            switch(r4) {
                case -1574842690: goto L_0x0030;
                case 3343801: goto L_0x0026;
                default: goto L_0x0025;
            }
        L_0x0025:
            goto L_0x003a
        L_0x0026:
            java.lang.String r4 = "main"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x003a
            r3 = 0
            goto L_0x003b
        L_0x0030:
            java.lang.String r4 = "forced_subtitle"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x003a
            r3 = 1
            goto L_0x003b
        L_0x003a:
            r3 = -1
        L_0x003b:
            switch(r3) {
                case 0: goto L_0x0042;
                case 1: goto L_0x003f;
                default: goto L_0x003e;
            }
        L_0x003e:
            goto L_0x0041
        L_0x003f:
            r5 = 2
            goto L_0x0042
        L_0x0041:
            r5 = 0
        L_0x0042:
            r2 = r2 | r5
        L_0x0043:
            int r1 = r1 + 1
            goto L_0x0003
        L_0x0046:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.a(java.util.List):int");
    }

    private static int a(XmlPullParser xmlPullParser, String str, int i) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        return attributeValue == null ? i : Integer.parseInt(attributeValue);
    }

    private static long a(long j, long j2) {
        if (j2 != -9223372036854775807L) {
            j = j2;
        }
        if (j == LocationRequestCompat.PASSIVE_INTERVAL) {
            return -9223372036854775807L;
        }
        return j;
    }

    private static long a(List list, long j, long j2, int i, long j3) {
        int a2 = i >= 0 ? i + 1 : (int) Util.a(j3 - j, j2);
        for (int i2 = 0; i2 < a2; i2++) {
            list.add(new SegmentBase.SegmentTimelineElement(j, j2));
            j += j2;
        }
        return j;
    }

    private static long a(XmlPullParser xmlPullParser, long j) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, "availabilityTimeOffset");
        return attributeValue == null ? j : "INF".equals(attributeValue) ? LocationRequestCompat.PASSIVE_INTERVAL : (long) (Float.parseFloat(attributeValue) * 1000000.0f);
    }

    private static long a(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        return attributeValue == null ? j : Util.d(attributeValue);
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x0167 A[LOOP:0: B:5:0x003c->B:36:0x0167, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x014c A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.util.Pair a(org.xmlpull.v1.XmlPullParser r34, java.lang.String r35, long r36, long r38, long r40, long r42) {
        /*
            r33 = this;
            r14 = r34
            java.lang.String r0 = "id"
            r15 = 0
            java.lang.String r16 = r14.getAttributeValue(r15, r0)
            java.lang.String r0 = "start"
            r1 = r36
            long r17 = a((org.xmlpull.v1.XmlPullParser) r14, (java.lang.String) r0, (long) r1)
            r12 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r0 = (r40 > r12 ? 1 : (r40 == r12 ? 0 : -1))
            if (r0 == 0) goto L_0x001f
            long r0 = r40 + r17
            r19 = r0
            goto L_0x0021
        L_0x001f:
            r19 = r12
        L_0x0021:
            java.lang.String r0 = "duration"
            long r21 = a((org.xmlpull.v1.XmlPullParser) r14, (java.lang.String) r0, (long) r12)
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            r0 = 0
            r8 = r35
            r6 = r38
            r25 = r12
            r24 = r15
            r23 = 0
        L_0x003c:
            r34.next()
            java.lang.String r0 = "BaseURL"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r14, r0)
            if (r0 == 0) goto L_0x006a
            if (r23 != 0) goto L_0x005e
            long r0 = a((org.xmlpull.v1.XmlPullParser) r14, (long) r6)
            java.lang.String r2 = a((org.xmlpull.v1.XmlPullParser) r14, (java.lang.String) r8)
            r3 = 1
            r6 = r0
            r8 = r2
            r30 = r11
            r31 = r12
            r13 = r15
            r23 = 1
            r15 = r10
            goto L_0x0144
        L_0x005e:
            r27 = r6
            r29 = r8
            r30 = r11
            r31 = r12
            r13 = r15
            r15 = r10
            goto L_0x0140
        L_0x006a:
            java.lang.String r0 = "AdaptationSet"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r14, r0)
            if (r0 == 0) goto L_0x0097
            r0 = r33
            r1 = r34
            r2 = r8
            r3 = r24
            r4 = r21
            r27 = r6
            r29 = r8
            r8 = r25
            r15 = r10
            r30 = r11
            r10 = r19
            r12 = r42
            com.google.android.exoplayer2.source.dash.manifest.AdaptationSet r0 = r0.a((org.xmlpull.v1.XmlPullParser) r1, (java.lang.String) r2, (com.google.android.exoplayer2.source.dash.manifest.SegmentBase) r3, (long) r4, (long) r6, (long) r8, (long) r10, (long) r12)
            r15.add(r0)
        L_0x008f:
            r13 = 0
            r31 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            goto L_0x0140
        L_0x0097:
            r27 = r6
            r29 = r8
            r15 = r10
            r30 = r11
            java.lang.String r0 = "EventStream"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r14, r0)
            if (r0 == 0) goto L_0x00b0
            com.google.android.exoplayer2.source.dash.manifest.EventStream r0 = d((org.xmlpull.v1.XmlPullParser) r34)
            r13 = r30
            r13.add(r0)
            goto L_0x008f
        L_0x00b0:
            r13 = r30
            java.lang.String r0 = "SegmentBase"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r14, r0)
            r12 = 0
            if (r0 == 0) goto L_0x00cd
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase r24 = a((org.xmlpull.v1.XmlPullParser) r14, (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SingleSegmentBase) r12)
            r30 = r13
            r6 = r27
            r8 = r29
            r31 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r13 = r12
            goto L_0x0144
        L_0x00cd:
            java.lang.String r0 = "SegmentList"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r14, r0)
            if (r0 == 0) goto L_0x00fc
            r10 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            long r24 = a((org.xmlpull.v1.XmlPullParser) r14, (long) r10)
            r1 = 0
            r0 = r34
            r2 = r19
            r4 = r21
            r6 = r27
            r8 = r24
            r30 = r13
            r12 = r10
            r10 = r42
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentList r0 = a(r0, r1, r2, r4, r6, r8, r10)
            r31 = r12
            r25 = r24
            r8 = r29
            r13 = 0
        L_0x00f9:
            r24 = r0
            goto L_0x0144
        L_0x00fc:
            r30 = r13
            r12 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            java.lang.String r0 = "SegmentTemplate"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r14, r0)
            if (r0 == 0) goto L_0x012e
            long r24 = a((org.xmlpull.v1.XmlPullParser) r14, (long) r12)
            r1 = 0
            com.dreamers.exoplayercore.repack.bG r2 = com.dreamers.exoplayercore.repack.bG.g()
            r0 = r34
            r3 = r19
            r5 = r21
            r7 = r27
            r9 = r24
            r31 = r12
            r13 = 0
            r11 = r42
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentTemplate r0 = a((org.xmlpull.v1.XmlPullParser) r0, (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentTemplate) r1, (java.util.List) r2, (long) r3, (long) r5, (long) r7, (long) r9, (long) r11)
            r25 = r24
            r6 = r27
            r8 = r29
            goto L_0x00f9
        L_0x012e:
            r31 = r12
            r13 = 0
            java.lang.String r0 = "AssetIdentifier"
            boolean r1 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r14, r0)
            if (r1 == 0) goto L_0x013d
            b((org.xmlpull.v1.XmlPullParser) r14, (java.lang.String) r0)
            goto L_0x0140
        L_0x013d:
            h(r34)
        L_0x0140:
            r6 = r27
            r8 = r29
        L_0x0144:
            java.lang.String r0 = "Period"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.a(r14, r0)
            if (r0 == 0) goto L_0x0167
            com.google.android.exoplayer2.source.dash.manifest.Period r0 = new com.google.android.exoplayer2.source.dash.manifest.Period
            r1 = 0
            r34 = r0
            r35 = r16
            r36 = r17
            r38 = r15
            r39 = r30
            r40 = r1
            r34.<init>(r35, r36, r38, r39, r40)
            java.lang.Long r1 = java.lang.Long.valueOf(r21)
            android.util.Pair r0 = android.util.Pair.create(r0, r1)
            return r0
        L_0x0167:
            r10 = r15
            r11 = r30
            r15 = r13
            r12 = r31
            goto L_0x003c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.a(org.xmlpull.v1.XmlPullParser, java.lang.String, long, long, long, long):android.util.Pair");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r34v5, resolved type: com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v47, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r39v4, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0340 A[LOOP:0: B:1:0x0075->B:83:0x0340, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x02d6 A[EDGE_INSN: B:84:0x02d6->B:68:0x02d6 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.android.exoplayer2.source.dash.manifest.AdaptationSet a(org.xmlpull.v1.XmlPullParser r54, java.lang.String r55, com.google.android.exoplayer2.source.dash.manifest.SegmentBase r56, long r57, long r59, long r61, long r63, long r65) {
        /*
            r53 = this;
            r15 = r54
            java.lang.String r0 = "id"
            r1 = -1
            int r26 = a((org.xmlpull.v1.XmlPullParser) r15, (java.lang.String) r0, (int) r1)
            int r0 = b((org.xmlpull.v1.XmlPullParser) r54)
            java.lang.String r2 = "mimeType"
            r14 = 0
            java.lang.String r27 = r15.getAttributeValue(r14, r2)
            java.lang.String r2 = "codecs"
            java.lang.String r28 = r15.getAttributeValue(r14, r2)
            java.lang.String r2 = "width"
            int r29 = a((org.xmlpull.v1.XmlPullParser) r15, (java.lang.String) r2, (int) r1)
            java.lang.String r2 = "height"
            int r30 = a((org.xmlpull.v1.XmlPullParser) r15, (java.lang.String) r2, (int) r1)
            r2 = -1082130432(0xffffffffbf800000, float:-1.0)
            float r31 = a((org.xmlpull.v1.XmlPullParser) r15, (float) r2)
            java.lang.String r2 = "audioSamplingRate"
            int r32 = a((org.xmlpull.v1.XmlPullParser) r15, (java.lang.String) r2, (int) r1)
            java.lang.String r13 = "lang"
            java.lang.String r2 = r15.getAttributeValue(r14, r13)
            java.lang.String r3 = "label"
            java.lang.String r3 = r15.getAttributeValue(r14, r3)
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            r33 = 0
            r5 = r55
            r34 = r56
            r36 = r2
            r37 = r3
            r39 = r14
            r35 = -1
            r38 = 0
            r3 = r59
            r1 = r61
        L_0x0075:
            r54.next()
            java.lang.String r14 = "BaseURL"
            boolean r14 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r15, r14)
            if (r14 == 0) goto L_0x00ba
            if (r38 != 0) goto L_0x00a2
            long r3 = a((org.xmlpull.v1.XmlPullParser) r15, (long) r3)
            java.lang.String r5 = a((org.xmlpull.v1.XmlPullParser) r15, (java.lang.String) r5)
            r38 = 1
        L_0x008c:
            r41 = r3
            r14 = r6
            r45 = r7
            r46 = r8
            r47 = r9
            r48 = r10
            r3 = r11
            r50 = r12
            r51 = r13
            r4 = r37
            r52 = 0
            goto L_0x02ce
        L_0x00a2:
            r41 = r3
            r43 = r5
            r14 = r6
            r45 = r7
            r46 = r8
            r47 = r9
            r48 = r10
            r3 = r11
            r50 = r12
            r51 = r13
            r52 = 0
            r13 = r0
            r0 = r1
            goto L_0x02c8
        L_0x00ba:
            java.lang.String r14 = "ContentProtection"
            boolean r14 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r15, r14)
            if (r14 == 0) goto L_0x00e0
            android.util.Pair r14 = c((org.xmlpull.v1.XmlPullParser) r54)
            r55 = r1
            java.lang.Object r1 = r14.first
            if (r1 == 0) goto L_0x00d2
            java.lang.Object r1 = r14.first
            r39 = r1
            java.lang.String r39 = (java.lang.String) r39
        L_0x00d2:
            java.lang.Object r1 = r14.second
            if (r1 == 0) goto L_0x00dd
            java.lang.Object r1 = r14.second
            com.google.android.exoplayer2.drm.DrmInitData$SchemeData r1 = (com.google.android.exoplayer2.drm.DrmInitData.SchemeData) r1
            r12.add(r1)
        L_0x00dd:
            r1 = r55
            goto L_0x008c
        L_0x00e0:
            r55 = r1
            java.lang.String r1 = "ContentComponent"
            boolean r1 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r15, r1)
            if (r1 == 0) goto L_0x0120
            r14 = 0
            java.lang.String r1 = r15.getAttributeValue(r14, r13)
            r2 = r36
            if (r2 != 0) goto L_0x00f6
            r36 = r1
            goto L_0x0102
        L_0x00f6:
            if (r1 != 0) goto L_0x00f9
            goto L_0x0100
        L_0x00f9:
            boolean r1 = r2.equals(r1)
            com.google.android.exoplayer2.util.Assertions.b((boolean) r1)
        L_0x0100:
            r36 = r2
        L_0x0102:
            int r1 = b((org.xmlpull.v1.XmlPullParser) r54)
            int r0 = a((int) r0, (int) r1)
            r1 = r55
            r41 = r3
            r45 = r7
            r46 = r8
            r47 = r9
            r48 = r10
            r3 = r11
            r50 = r12
            r51 = r13
            r52 = r14
            r4 = r37
            goto L_0x0172
        L_0x0120:
            r2 = r36
            r14 = 0
            java.lang.String r1 = "Role"
            boolean r16 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r15, r1)
            if (r16 == 0) goto L_0x014d
            com.google.android.exoplayer2.source.dash.manifest.Descriptor r1 = b((org.xmlpull.v1.XmlPullParser) r15, (java.lang.String) r1)
            r9.add(r1)
        L_0x0132:
            r36 = r2
            r41 = r3
            r43 = r5
            r45 = r7
            r46 = r8
            r47 = r9
            r48 = r10
            r3 = r11
            r50 = r12
            r51 = r13
            r52 = r14
            r13 = r0
            r14 = r6
            r0 = r55
            goto L_0x02c8
        L_0x014d:
            java.lang.String r1 = "AudioChannelConfiguration"
            boolean r1 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r15, r1)
            if (r1 == 0) goto L_0x0175
            int r1 = g((org.xmlpull.v1.XmlPullParser) r54)
            r35 = r1
            r36 = r2
            r41 = r3
            r45 = r7
            r46 = r8
            r47 = r9
            r48 = r10
            r3 = r11
            r50 = r12
            r51 = r13
            r52 = r14
            r4 = r37
            r1 = r55
        L_0x0172:
            r14 = r6
            goto L_0x02ce
        L_0x0175:
            java.lang.String r1 = "Accessibility"
            boolean r16 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r15, r1)
            if (r16 == 0) goto L_0x0185
            com.google.android.exoplayer2.source.dash.manifest.Descriptor r1 = b((org.xmlpull.v1.XmlPullParser) r15, (java.lang.String) r1)
            r10.add(r1)
            goto L_0x0132
        L_0x0185:
            java.lang.String r1 = "EssentialProperty"
            boolean r16 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r15, r1)
            if (r16 == 0) goto L_0x0195
            com.google.android.exoplayer2.source.dash.manifest.Descriptor r1 = b((org.xmlpull.v1.XmlPullParser) r15, (java.lang.String) r1)
            r8.add(r1)
            goto L_0x0132
        L_0x0195:
            java.lang.String r1 = "SupplementalProperty"
            boolean r16 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r15, r1)
            if (r16 == 0) goto L_0x01a5
            com.google.android.exoplayer2.source.dash.manifest.Descriptor r1 = b((org.xmlpull.v1.XmlPullParser) r15, (java.lang.String) r1)
            r7.add(r1)
            goto L_0x0132
        L_0x01a5:
            java.lang.String r1 = "Representation"
            boolean r1 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r15, r1)
            if (r1 == 0) goto L_0x0215
            r1 = r0
            r0 = r53
            r40 = r1
            r1 = r54
            r36 = r2
            r2 = r5
            r41 = r3
            r3 = r27
            r4 = r28
            r43 = r5
            r5 = r29
            r44 = r6
            r6 = r30
            r45 = r7
            r7 = r31
            r46 = r8
            r8 = r35
            r47 = r9
            r9 = r32
            r48 = r10
            r10 = r36
            r49 = r11
            r11 = r47
            r50 = r12
            r12 = r48
            r51 = r13
            r13 = r46
            r52 = r14
            r14 = r45
            r15 = r34
            r16 = r63
            r18 = r57
            r20 = r41
            r22 = r55
            r24 = r65
            com.google.android.exoplayer2.source.dash.manifest.DashManifestParser$RepresentationInfo r0 = r0.a(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r18, r20, r22, r24)
            com.google.android.exoplayer2.Format r1 = r0.a
            java.lang.String r1 = r1.l
            int r1 = com.google.android.exoplayer2.util.MimeTypes.h(r1)
            r13 = r40
            int r1 = a((int) r13, (int) r1)
            r14 = r44
            r14.add(r0)
            r15 = r54
            r0 = r1
            r4 = r37
            r5 = r43
            r3 = r49
            r1 = r55
            goto L_0x02ce
        L_0x0215:
            r36 = r2
            r41 = r3
            r43 = r5
            r45 = r7
            r46 = r8
            r47 = r9
            r48 = r10
            r49 = r11
            r50 = r12
            r51 = r13
            r52 = r14
            r13 = r0
            r14 = r6
            java.lang.String r0 = "SegmentBase"
            r15 = r54
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r15, r0)
            if (r0 == 0) goto L_0x024c
            r0 = r34
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase r0 = (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SingleSegmentBase) r0
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase r0 = a((org.xmlpull.v1.XmlPullParser) r15, (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SingleSegmentBase) r0)
            r1 = r55
            r34 = r0
            r0 = r13
        L_0x0244:
            r4 = r37
            r5 = r43
            r3 = r49
            goto L_0x02ce
        L_0x024c:
            java.lang.String r0 = "SegmentList"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r15, r0)
            if (r0 == 0) goto L_0x0274
            r0 = r55
            long r16 = a((org.xmlpull.v1.XmlPullParser) r15, (long) r0)
            r1 = r34
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentList r1 = (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentList) r1
            r0 = r54
            r2 = r63
            r4 = r57
            r6 = r41
            r8 = r16
            r10 = r65
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentList r0 = a(r0, r1, r2, r4, r6, r8, r10)
        L_0x026e:
            r34 = r0
            r0 = r13
            r1 = r16
            goto L_0x0244
        L_0x0274:
            r0 = r55
            java.lang.String r2 = "SegmentTemplate"
            boolean r2 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r15, r2)
            if (r2 == 0) goto L_0x0299
            long r16 = a((org.xmlpull.v1.XmlPullParser) r15, (long) r0)
            r1 = r34
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentTemplate r1 = (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentTemplate) r1
            r0 = r54
            r2 = r45
            r3 = r63
            r5 = r57
            r7 = r41
            r9 = r16
            r11 = r65
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentTemplate r0 = a((org.xmlpull.v1.XmlPullParser) r0, (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentTemplate) r1, (java.util.List) r2, (long) r3, (long) r5, (long) r7, (long) r9, (long) r11)
            goto L_0x026e
        L_0x0299:
            java.lang.String r2 = "InbandEventStream"
            boolean r3 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r15, r2)
            if (r3 == 0) goto L_0x02ab
            com.google.android.exoplayer2.source.dash.manifest.Descriptor r2 = b((org.xmlpull.v1.XmlPullParser) r15, (java.lang.String) r2)
            r3 = r49
            r3.add(r2)
            goto L_0x02c8
        L_0x02ab:
            r3 = r49
            java.lang.String r2 = "Label"
            boolean r4 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r15, r2)
            if (r4 == 0) goto L_0x02bf
            java.lang.String r2 = d(r15, r2)
            r4 = r2
            r5 = r43
            r1 = r0
            r0 = r13
            goto L_0x02ce
        L_0x02bf:
            boolean r2 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r54)
            if (r2 == 0) goto L_0x02c8
            h(r54)
        L_0x02c8:
            r1 = r0
            r0 = r13
            r4 = r37
            r5 = r43
        L_0x02ce:
            java.lang.String r6 = "AdaptationSet"
            boolean r6 = com.google.android.exoplayer2.util.XmlPullParserUtil.a(r15, r6)
            if (r6 == 0) goto L_0x0340
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = r14.size()
            r1.<init>(r2)
            r2 = 0
        L_0x02e0:
            int r5 = r14.size()
            if (r2 >= r5) goto L_0x032c
            java.lang.Object r5 = r14.get(r2)
            com.google.android.exoplayer2.source.dash.manifest.DashManifestParser$RepresentationInfo r5 = (com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.RepresentationInfo) r5
            com.google.android.exoplayer2.Format r6 = r5.a
            com.google.android.exoplayer2.Format$Builder r6 = r6.a()
            if (r4 == 0) goto L_0x02f6
            r6.b = r4
        L_0x02f6:
            java.lang.String r7 = r5.d
            if (r7 != 0) goto L_0x02fc
            r7 = r39
        L_0x02fc:
            java.util.ArrayList r8 = r5.e
            r9 = r50
            r8.addAll(r9)
            boolean r10 = r8.isEmpty()
            if (r10 != 0) goto L_0x0313
            a((java.util.ArrayList) r8)
            com.google.android.exoplayer2.drm.DrmInitData r10 = new com.google.android.exoplayer2.drm.DrmInitData
            r10.<init>((java.lang.String) r7, (java.util.List) r8)
            r6.n = r10
        L_0x0313:
            java.util.ArrayList r7 = r5.f
            r7.addAll(r3)
            com.google.android.exoplayer2.Format r6 = r6.a()
            java.lang.String r8 = r5.b
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase r5 = r5.c
            com.google.android.exoplayer2.source.dash.manifest.Representation r5 = com.google.android.exoplayer2.source.dash.manifest.Representation.a(r6, r8, r5, r7)
            r1.add(r5)
            int r2 = r2 + 1
            r50 = r9
            goto L_0x02e0
        L_0x032c:
            com.google.android.exoplayer2.source.dash.manifest.AdaptationSet r2 = new com.google.android.exoplayer2.source.dash.manifest.AdaptationSet
            r54 = r2
            r55 = r26
            r56 = r0
            r57 = r1
            r58 = r48
            r59 = r46
            r60 = r45
            r54.<init>(r55, r56, r57, r58, r59, r60)
            return r2
        L_0x0340:
            r11 = r3
            r37 = r4
            r6 = r14
            r3 = r41
            r7 = r45
            r8 = r46
            r9 = r47
            r10 = r48
            r12 = r50
            r13 = r51
            r14 = r52
            goto L_0x0075
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.a(org.xmlpull.v1.XmlPullParser, java.lang.String, com.google.android.exoplayer2.source.dash.manifest.SegmentBase, long, long, long, long, long):com.google.android.exoplayer2.source.dash.manifest.AdaptationSet");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v16, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v4, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v5, resolved type: com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentTemplate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v6, resolved type: com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v7, resolved type: com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0261  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x029f  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x02ac  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x02e2  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x02e5  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x02fe A[LOOP:0: B:1:0x006a->B:88:0x02fe, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0210 A[EDGE_INSN: B:89:0x0210->B:44:0x0210 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.RepresentationInfo a(org.xmlpull.v1.XmlPullParser r34, java.lang.String r35, java.lang.String r36, java.lang.String r37, int r38, int r39, float r40, int r41, int r42, java.lang.String r43, java.util.List r44, java.util.List r45, java.util.List r46, java.util.List r47, com.google.android.exoplayer2.source.dash.manifest.SegmentBase r48, long r49, long r51, long r53, long r55, long r57) {
        /*
            r33 = this;
            r13 = r34
            java.lang.String r0 = "id"
            r14 = 0
            java.lang.String r15 = r13.getAttributeValue(r14, r0)
            java.lang.String r0 = "bandwidth"
            r12 = -1
            int r10 = a((org.xmlpull.v1.XmlPullParser) r13, (java.lang.String) r0, (int) r12)
            java.lang.String r0 = "mimeType"
            r1 = r36
            java.lang.String r11 = b((org.xmlpull.v1.XmlPullParser) r13, (java.lang.String) r0, (java.lang.String) r1)
            java.lang.String r0 = "codecs"
            r1 = r37
            java.lang.String r8 = b((org.xmlpull.v1.XmlPullParser) r13, (java.lang.String) r0, (java.lang.String) r1)
            java.lang.String r0 = "width"
            r1 = r38
            int r9 = a((org.xmlpull.v1.XmlPullParser) r13, (java.lang.String) r0, (int) r1)
            java.lang.String r0 = "height"
            r1 = r39
            int r6 = a((org.xmlpull.v1.XmlPullParser) r13, (java.lang.String) r0, (int) r1)
            r0 = r40
            float r7 = a((org.xmlpull.v1.XmlPullParser) r13, (float) r0)
            java.lang.String r0 = "audioSamplingRate"
            r1 = r42
            int r4 = a((org.xmlpull.v1.XmlPullParser) r13, (java.lang.String) r0, (int) r1)
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.ArrayList r3 = new java.util.ArrayList
            r0 = r46
            r3.<init>(r0)
            java.util.ArrayList r1 = new java.util.ArrayList
            r0 = r47
            r1.<init>(r0)
            r16 = 0
            r16 = r41
            r17 = r48
            r19 = r55
            r18 = r10
            r36 = r11
            r22 = r14
            r21 = 0
            r14 = r35
            r10 = r53
        L_0x006a:
            r34.next()
            java.lang.String r12 = "BaseURL"
            boolean r12 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r13, r12)
            if (r12 == 0) goto L_0x00b4
            if (r21 != 0) goto L_0x0099
            long r10 = a((org.xmlpull.v1.XmlPullParser) r13, (long) r10)
            java.lang.String r12 = a((org.xmlpull.v1.XmlPullParser) r13, (java.lang.String) r14)
            r14 = 1
            r14 = r2
            r25 = r4
            r26 = r6
            r27 = r7
            r38 = r8
            r28 = r9
            r24 = r15
            r0 = r16
            r29 = r18
            r18 = r19
            r20 = -1
            r21 = 1
            r4 = r1
            goto L_0x00d6
        L_0x0099:
            r25 = r4
            r26 = r6
            r27 = r7
            r38 = r8
            r28 = r9
            r30 = r10
            r35 = r14
            r24 = r15
            r29 = r18
            r4 = r1
            r14 = r2
            r15 = r5
            r0 = r19
            r20 = -1
            goto L_0x0200
        L_0x00b4:
            java.lang.String r12 = "AudioChannelConfiguration"
            boolean r12 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r13, r12)
            if (r12 == 0) goto L_0x00d9
            int r12 = g((org.xmlpull.v1.XmlPullParser) r34)
            r25 = r4
            r26 = r6
            r27 = r7
            r38 = r8
            r28 = r9
            r0 = r12
            r12 = r14
            r24 = r15
        L_0x00ce:
            r29 = r18
            r18 = r19
            r20 = -1
            r4 = r1
            r14 = r2
        L_0x00d6:
            r15 = r5
            goto L_0x0208
        L_0x00d9:
            java.lang.String r12 = "SegmentBase"
            boolean r12 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r13, r12)
            if (r12 == 0) goto L_0x00fb
            r12 = r17
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase r12 = (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SingleSegmentBase) r12
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase r12 = a((org.xmlpull.v1.XmlPullParser) r13, (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SingleSegmentBase) r12)
            r25 = r4
            r26 = r6
            r27 = r7
            r38 = r8
            r28 = r9
            r17 = r12
            r12 = r14
            r24 = r15
            r0 = r16
            goto L_0x00ce
        L_0x00fb:
            java.lang.String r12 = "SegmentList"
            boolean r12 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r13, r12)
            if (r12 == 0) goto L_0x0148
            r37 = r1
            r0 = r19
            long r19 = a((org.xmlpull.v1.XmlPullParser) r13, (long) r0)
            r1 = r17
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentList r1 = (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentList) r1
            r0 = r34
            r12 = r37
            r35 = r14
            r24 = r15
            r14 = r2
            r15 = r3
            r2 = r49
            r25 = r4
            r37 = r15
            r15 = r5
            r4 = r51
            r26 = r6
            r27 = r7
            r6 = r10
            r38 = r8
            r28 = r9
            r8 = r19
            r30 = r10
            r29 = r18
            r10 = r57
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentList r0 = a(r0, r1, r2, r4, r6, r8, r10)
            r3 = r37
            r17 = r0
            r4 = r12
            r0 = r16
            r18 = r19
            r10 = r30
            r20 = -1
            r12 = r35
            goto L_0x0208
        L_0x0148:
            r12 = r1
            r37 = r3
            r25 = r4
            r26 = r6
            r27 = r7
            r38 = r8
            r28 = r9
            r30 = r10
            r35 = r14
            r24 = r15
            r29 = r18
            r0 = r19
            r14 = r2
            r15 = r5
            java.lang.String r2 = "SegmentTemplate"
            boolean r2 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r13, r2)
            if (r2 == 0) goto L_0x0195
            long r18 = a((org.xmlpull.v1.XmlPullParser) r13, (long) r0)
            r1 = r17
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentTemplate r1 = (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentTemplate) r1
            r0 = r34
            r2 = r47
            r3 = r49
            r5 = r51
            r7 = r30
            r9 = r18
            r32 = r12
            r20 = -1
            r11 = r57
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentTemplate r0 = a((org.xmlpull.v1.XmlPullParser) r0, (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentTemplate) r1, (java.util.List) r2, (long) r3, (long) r5, (long) r7, (long) r9, (long) r11)
            r12 = r35
            r3 = r37
            r17 = r0
        L_0x018d:
            r0 = r16
            r10 = r30
            r4 = r32
            goto L_0x0208
        L_0x0195:
            r32 = r12
            r20 = -1
            java.lang.String r2 = "ContentProtection"
            boolean r2 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r13, r2)
            if (r2 == 0) goto L_0x01c1
            android.util.Pair r2 = c((org.xmlpull.v1.XmlPullParser) r34)
            java.lang.Object r3 = r2.first
            if (r3 == 0) goto L_0x01af
            java.lang.Object r3 = r2.first
            r22 = r3
            java.lang.String r22 = (java.lang.String) r22
        L_0x01af:
            java.lang.Object r3 = r2.second
            if (r3 == 0) goto L_0x01ba
            java.lang.Object r2 = r2.second
            com.google.android.exoplayer2.drm.DrmInitData$SchemeData r2 = (com.google.android.exoplayer2.drm.DrmInitData.SchemeData) r2
            r15.add(r2)
        L_0x01ba:
            r12 = r35
            r3 = r37
            r18 = r0
            goto L_0x018d
        L_0x01c1:
            java.lang.String r2 = "InbandEventStream"
            boolean r3 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r13, r2)
            if (r3 == 0) goto L_0x01d5
            com.google.android.exoplayer2.source.dash.manifest.Descriptor r2 = b((org.xmlpull.v1.XmlPullParser) r13, (java.lang.String) r2)
            r14.add(r2)
            r3 = r37
        L_0x01d2:
            r4 = r32
            goto L_0x0200
        L_0x01d5:
            java.lang.String r2 = "EssentialProperty"
            boolean r3 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r13, r2)
            if (r3 == 0) goto L_0x01e7
            com.google.android.exoplayer2.source.dash.manifest.Descriptor r2 = b((org.xmlpull.v1.XmlPullParser) r13, (java.lang.String) r2)
            r3 = r37
            r3.add(r2)
            goto L_0x01d2
        L_0x01e7:
            r3 = r37
            java.lang.String r2 = "SupplementalProperty"
            boolean r4 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r13, r2)
            if (r4 == 0) goto L_0x01fb
            com.google.android.exoplayer2.source.dash.manifest.Descriptor r2 = b((org.xmlpull.v1.XmlPullParser) r13, (java.lang.String) r2)
            r4 = r32
            r4.add(r2)
            goto L_0x0200
        L_0x01fb:
            r4 = r32
            h(r34)
        L_0x0200:
            r12 = r35
            r18 = r0
            r0 = r16
            r10 = r30
        L_0x0208:
            java.lang.String r1 = "Representation"
            boolean r1 = com.google.android.exoplayer2.util.XmlPullParserUtil.a(r13, r1)
            if (r1 == 0) goto L_0x02fe
            boolean r1 = com.google.android.exoplayer2.util.MimeTypes.a((java.lang.String) r36)
            if (r1 == 0) goto L_0x021d
            java.lang.String r1 = com.google.android.exoplayer2.util.MimeTypes.e(r38)
        L_0x021a:
            r2 = r36
            goto L_0x0259
        L_0x021d:
            boolean r1 = com.google.android.exoplayer2.util.MimeTypes.b(r36)
            if (r1 == 0) goto L_0x0228
            java.lang.String r1 = com.google.android.exoplayer2.util.MimeTypes.d(r38)
            goto L_0x021a
        L_0x0228:
            boolean r1 = com.google.android.exoplayer2.util.MimeTypes.c(r36)
            if (r1 == 0) goto L_0x023f
            java.lang.String r1 = "application/x-rawcc"
            r2 = r36
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x023d
            java.lang.String r1 = com.google.android.exoplayer2.util.MimeTypes.f(r38)
            goto L_0x0259
        L_0x023d:
            r1 = r2
            goto L_0x0259
        L_0x023f:
            r2 = r36
            java.lang.String r1 = "application/mp4"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0258
            java.lang.String r1 = com.google.android.exoplayer2.util.MimeTypes.g(r38)
            java.lang.String r5 = "text/vtt"
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L_0x0259
            java.lang.String r1 = "application/x-mp4-vtt"
            goto L_0x0259
        L_0x0258:
            r1 = 0
        L_0x0259:
            java.lang.String r5 = "audio/eac3"
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L_0x0265
            java.lang.String r1 = g((java.util.List) r4)
        L_0x0265:
            int r5 = a((java.util.List) r44)
            int r6 = b((java.util.List) r44)
            int r7 = c((java.util.List) r45)
            r6 = r6 | r7
            int r3 = d((java.util.List) r3)
            r3 = r3 | r6
            int r4 = d((java.util.List) r4)
            r3 = r3 | r4
            com.google.android.exoplayer2.Format$Builder r4 = new com.google.android.exoplayer2.Format$Builder
            r4.<init>()
            r6 = r24
            r4.a = r6
            r4.j = r2
            r4.k = r1
            r7 = r38
            r4.h = r7
            r8 = r29
            r4.g = r8
            r4.d = r5
            r4.e = r3
            r5 = r43
            r4.c = r5
            boolean r2 = com.google.android.exoplayer2.util.MimeTypes.b(r1)
            if (r2 == 0) goto L_0x02ac
            r9 = r28
            r4.p = r9
            r1 = r26
            r4.q = r1
            r0 = r27
            r4.r = r0
            goto L_0x02dc
        L_0x02ac:
            boolean r2 = com.google.android.exoplayer2.util.MimeTypes.a((java.lang.String) r1)
            if (r2 == 0) goto L_0x02b9
            r4.x = r0
            r0 = r25
            r4.y = r0
            goto L_0x02dc
        L_0x02b9:
            boolean r0 = com.google.android.exoplayer2.util.MimeTypes.c(r1)
            if (r0 == 0) goto L_0x02dc
            java.lang.String r0 = "application/cea-608"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x02cc
            int r0 = e((java.util.List) r45)
            goto L_0x02da
        L_0x02cc:
            java.lang.String r0 = "application/cea-708"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x02d9
            int r0 = f((java.util.List) r45)
            goto L_0x02da
        L_0x02d9:
            r0 = -1
        L_0x02da:
            r4.C = r0
        L_0x02dc:
            com.google.android.exoplayer2.Format r0 = r4.a()
            if (r17 == 0) goto L_0x02e5
            r1 = r17
            goto L_0x02ea
        L_0x02e5:
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase r1 = new com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase
            r1.<init>()
        L_0x02ea:
            com.google.android.exoplayer2.source.dash.manifest.DashManifestParser$RepresentationInfo r2 = new com.google.android.exoplayer2.source.dash.manifest.DashManifestParser$RepresentationInfo
            r34 = r2
            r35 = r0
            r36 = r12
            r37 = r1
            r38 = r22
            r39 = r15
            r40 = r14
            r34.<init>(r35, r36, r37, r38, r39, r40)
            return r2
        L_0x02fe:
            r5 = r43
            r8 = r38
            r16 = r0
            r1 = r4
            r2 = r14
            r5 = r15
            r19 = r18
            r15 = r24
            r4 = r25
            r6 = r26
            r7 = r27
            r9 = r28
            r18 = r29
            r0 = r47
            r14 = r12
            r12 = -1
            goto L_0x006a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.a(org.xmlpull.v1.XmlPullParser, java.lang.String, java.lang.String, java.lang.String, int, int, float, int, int, java.lang.String, java.util.List, java.util.List, java.util.List, java.util.List, com.google.android.exoplayer2.source.dash.manifest.SegmentBase, long, long, long, long, long):com.google.android.exoplayer2.source.dash.manifest.DashManifestParser$RepresentationInfo");
    }

    private static RangedUri a(String str, long j, long j2) {
        return new RangedUri(str, j, j2);
    }

    private static RangedUri a(XmlPullParser xmlPullParser, String str, String str2) {
        long j;
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        String attributeValue2 = xmlPullParser.getAttributeValue((String) null, str2);
        long j2 = -1;
        if (attributeValue2 != null) {
            String[] split = attributeValue2.split("-");
            j = Long.parseLong(split[0]);
            if (split.length == 2) {
                j2 = (Long.parseLong(split[1]) - j) + 1;
            }
        } else {
            j = 0;
        }
        return a(attributeValue, j, j2);
    }

    private static SegmentBase.SegmentList a(XmlPullParser xmlPullParser, SegmentBase.SegmentList segmentList, long j, long j2, long j3, long j4, long j5) {
        XmlPullParser xmlPullParser2 = xmlPullParser;
        SegmentBase.SegmentList segmentList2 = segmentList;
        long j6 = 1;
        long b2 = b(xmlPullParser2, "timescale", segmentList2 != null ? segmentList2.b : 1);
        long b3 = b(xmlPullParser2, "presentationTimeOffset", segmentList2 != null ? segmentList2.c : 0);
        long b4 = b(xmlPullParser2, "duration", segmentList2 != null ? segmentList2.e : -9223372036854775807L);
        if (segmentList2 != null) {
            j6 = segmentList2.d;
        }
        long b5 = b(xmlPullParser2, "startNumber", j6);
        long a2 = a(j3, j4);
        List list = null;
        RangedUri rangedUri = null;
        List list2 = null;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.b(xmlPullParser2, "Initialization")) {
                rangedUri = e(xmlPullParser);
                long j7 = j2;
            } else if (XmlPullParserUtil.b(xmlPullParser2, "SegmentTimeline")) {
                list2 = a(xmlPullParser2, b2, j2);
            } else {
                long j8 = j2;
                if (XmlPullParserUtil.b(xmlPullParser2, "SegmentURL")) {
                    if (list == null) {
                        list = new ArrayList();
                    }
                    list.add(a(xmlPullParser2, "media", "mediaRange"));
                } else {
                    h(xmlPullParser);
                }
            }
        } while (!XmlPullParserUtil.a(xmlPullParser2, "SegmentList"));
        if (segmentList2 != null) {
            if (rangedUri == null) {
                rangedUri = segmentList2.a;
            }
            if (list2 == null) {
                list2 = segmentList2.f;
            }
            if (list == null) {
                list = segmentList2.h;
            }
        }
        return new SegmentBase.SegmentList(rangedUri, b2, b3, b5, b4, list2, a2, list, C.b(j5), C.b(j));
    }

    private static SegmentBase.SegmentTemplate a(XmlPullParser xmlPullParser, SegmentBase.SegmentTemplate segmentTemplate, List list, long j, long j2, long j3, long j4, long j5) {
        long j6;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        SegmentBase.SegmentTemplate segmentTemplate2 = segmentTemplate;
        long j7 = 1;
        long b2 = b(xmlPullParser2, "timescale", segmentTemplate2 != null ? segmentTemplate2.b : 1);
        long b3 = b(xmlPullParser2, "presentationTimeOffset", segmentTemplate2 != null ? segmentTemplate2.c : 0);
        long b4 = b(xmlPullParser2, "duration", segmentTemplate2 != null ? segmentTemplate2.e : -9223372036854775807L);
        if (segmentTemplate2 != null) {
            j7 = segmentTemplate2.d;
        }
        long b5 = b(xmlPullParser2, "startNumber", j7);
        int i = 0;
        while (true) {
            if (i >= list.size()) {
                j6 = -1;
                break;
            }
            Descriptor descriptor = (Descriptor) list.get(i);
            if (C0000a.a((CharSequence) "http://dashif.org/guidelines/last-segment-number", (CharSequence) descriptor.a)) {
                j6 = Long.parseLong(descriptor.b);
                break;
            }
            i++;
        }
        long j8 = j6;
        long a2 = a(j3, j4);
        RangedUri rangedUri = null;
        UrlTemplate a3 = a(xmlPullParser2, "media", segmentTemplate2 != null ? segmentTemplate2.i : null);
        UrlTemplate a4 = a(xmlPullParser2, "initialization", segmentTemplate2 != null ? segmentTemplate2.h : null);
        List list2 = null;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.b(xmlPullParser2, "Initialization")) {
                rangedUri = e(xmlPullParser);
                long j9 = j2;
            } else if (XmlPullParserUtil.b(xmlPullParser2, "SegmentTimeline")) {
                list2 = a(xmlPullParser2, b2, j2);
            } else {
                long j10 = j2;
                h(xmlPullParser);
            }
        } while (!XmlPullParserUtil.a(xmlPullParser2, "SegmentTemplate"));
        if (segmentTemplate2 != null) {
            if (rangedUri == null) {
                rangedUri = segmentTemplate2.a;
            }
            if (list2 == null) {
                list2 = segmentTemplate2.f;
            }
        }
        return new SegmentBase.SegmentTemplate(rangedUri, b2, b3, b5, j8, b4, list2, a2, a4, a3, C.b(j5), C.b(j));
    }

    private static SegmentBase.SingleSegmentBase a(XmlPullParser xmlPullParser, SegmentBase.SingleSegmentBase singleSegmentBase) {
        long j;
        long j2;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        SegmentBase.SingleSegmentBase singleSegmentBase2 = singleSegmentBase;
        long b2 = b(xmlPullParser2, "timescale", singleSegmentBase2 != null ? singleSegmentBase2.b : 1);
        long j3 = 0;
        long b3 = b(xmlPullParser2, "presentationTimeOffset", singleSegmentBase2 != null ? singleSegmentBase2.c : 0);
        long j4 = singleSegmentBase2 != null ? singleSegmentBase2.d : 0;
        if (singleSegmentBase2 != null) {
            j3 = singleSegmentBase2.e;
        }
        RangedUri rangedUri = null;
        String attributeValue = xmlPullParser2.getAttributeValue((String) null, "indexRange");
        if (attributeValue != null) {
            String[] split = attributeValue.split("-");
            j2 = Long.parseLong(split[0]);
            j = (Long.parseLong(split[1]) - j2) + 1;
        } else {
            j = j3;
            j2 = j4;
        }
        if (singleSegmentBase2 != null) {
            rangedUri = singleSegmentBase2.a;
        }
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.b(xmlPullParser2, "Initialization")) {
                rangedUri = e(xmlPullParser);
            } else {
                h(xmlPullParser);
            }
        } while (!XmlPullParserUtil.a(xmlPullParser2, "SegmentBase"));
        return new SegmentBase.SingleSegmentBase(rangedUri, b2, b3, j2, j);
    }

    private static ServiceDescriptionElement a(XmlPullParser xmlPullParser) {
        XmlPullParser xmlPullParser2 = xmlPullParser;
        float f = -3.4028235E38f;
        long j = -9223372036854775807L;
        long j2 = -9223372036854775807L;
        long j3 = -9223372036854775807L;
        float f2 = -3.4028235E38f;
        while (true) {
            xmlPullParser.next();
            if (XmlPullParserUtil.b(xmlPullParser2, "Latency")) {
                j = b(xmlPullParser2, "target", -9223372036854775807L);
                j2 = b(xmlPullParser2, "min", -9223372036854775807L);
                j3 = b(xmlPullParser2, "max", -9223372036854775807L);
            } else if (XmlPullParserUtil.b(xmlPullParser2, "PlaybackRate")) {
                f = e(xmlPullParser2, "min");
                f2 = e(xmlPullParser2, "max");
            }
            float f3 = f;
            float f4 = f2;
            long j4 = j;
            long j5 = j2;
            long j6 = j3;
            if (XmlPullParserUtil.a(xmlPullParser2, "ServiceDescription")) {
                return new ServiceDescriptionElement(j4, j5, j6, f3, f4);
            }
            j = j4;
            j2 = j5;
            j3 = j6;
            f = f3;
            f2 = f4;
        }
    }

    private static UrlTemplate a(XmlPullParser xmlPullParser, String str, UrlTemplate urlTemplate) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        return attributeValue != null ? UrlTemplate.a(attributeValue) : urlTemplate;
    }

    private static String a(XmlPullParser xmlPullParser, String str) {
        return UriUtil.b(str, d(xmlPullParser, "BaseURL"));
    }

    private static List a(XmlPullParser xmlPullParser, long j, long j2) {
        XmlPullParser xmlPullParser2 = xmlPullParser;
        ArrayList arrayList = new ArrayList();
        long j3 = 0;
        long j4 = -9223372036854775807L;
        boolean z = false;
        int i = 0;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.b(xmlPullParser2, "S")) {
                long b2 = b(xmlPullParser2, "t", -9223372036854775807L);
                if (z) {
                    j3 = a(arrayList, j3, j4, i, b2);
                }
                if (b2 == -9223372036854775807L) {
                    b2 = j3;
                }
                j4 = b(xmlPullParser2, "d", -9223372036854775807L);
                i = a(xmlPullParser2, "r", 0);
                j3 = b2;
                z = true;
            } else {
                h(xmlPullParser);
            }
        } while (!XmlPullParserUtil.a(xmlPullParser2, "SegmentTimeline"));
        if (z) {
            a(arrayList, j3, j4, i, Util.b(j2, j, 1000));
        }
        return arrayList;
    }

    private static void a(ArrayList arrayList) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            DrmInitData.SchemeData schemeData = (DrmInitData.SchemeData) arrayList.get(size);
            if (!schemeData.a()) {
                int i = 0;
                while (true) {
                    if (i >= arrayList.size()) {
                        break;
                    }
                    DrmInitData.SchemeData schemeData2 = (DrmInitData.SchemeData) arrayList.get(i);
                    if (schemeData2.a() && !schemeData.a() && schemeData2.a(schemeData.a)) {
                        arrayList.remove(size);
                        break;
                    }
                    i++;
                }
            }
        }
    }

    private static byte[] a(XmlPullParser xmlPullParser, ByteArrayOutputStream byteArrayOutputStream) {
        byteArrayOutputStream.reset();
        XmlSerializer newSerializer = Xml.newSerializer();
        newSerializer.setOutput(byteArrayOutputStream, aC.c.name());
        while (true) {
            xmlPullParser.nextToken();
            if (!XmlPullParserUtil.a(xmlPullParser, "Event")) {
                switch (xmlPullParser.getEventType()) {
                    case 0:
                        newSerializer.startDocument((String) null, Boolean.FALSE);
                        break;
                    case 1:
                        newSerializer.endDocument();
                        break;
                    case 2:
                        newSerializer.startTag(xmlPullParser.getNamespace(), xmlPullParser.getName());
                        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
                            newSerializer.attribute(xmlPullParser.getAttributeNamespace(i), xmlPullParser.getAttributeName(i), xmlPullParser.getAttributeValue(i));
                        }
                        break;
                    case 3:
                        newSerializer.endTag(xmlPullParser.getNamespace(), xmlPullParser.getName());
                        break;
                    case 4:
                        newSerializer.text(xmlPullParser.getText());
                        break;
                    case 5:
                        newSerializer.cdsect(xmlPullParser.getText());
                        break;
                    case 6:
                        newSerializer.entityRef(xmlPullParser.getText());
                        break;
                    case 7:
                        newSerializer.ignorableWhitespace(xmlPullParser.getText());
                        break;
                    case 8:
                        newSerializer.processingInstruction(xmlPullParser.getText());
                        break;
                    case 9:
                        newSerializer.comment(xmlPullParser.getText());
                        break;
                    case 10:
                        newSerializer.docdecl(xmlPullParser.getText());
                        break;
                }
            } else {
                newSerializer.flush();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    private static int b(List list) {
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            Descriptor descriptor = (Descriptor) list.get(i2);
            if (C0000a.a((CharSequence) "urn:mpeg:dash:role:2011", (CharSequence) descriptor.a)) {
                i |= a(descriptor.b);
            }
        }
        return i;
    }

    private static int b(XmlPullParser xmlPullParser) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, "contentType");
        if (TextUtils.isEmpty(attributeValue)) {
            return -1;
        }
        if ("audio".equals(attributeValue)) {
            return 1;
        }
        if ("video".equals(attributeValue)) {
            return 2;
        }
        return PropertyTypeConstants.PROPERTY_TYPE_TEXT.equals(attributeValue) ? 3 : -1;
    }

    private static long b(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        return attributeValue == null ? j : Long.parseLong(attributeValue);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01be A[Catch:{ XmlPullParserException -> 0x01ca }, LOOP:0: B:24:0x0095->B:75:0x01be, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0188 A[SYNTHETIC] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.exoplayer2.source.dash.manifest.DashManifest a(android.net.Uri r46, java.io.InputStream r47) {
        /*
            r45 = this;
            java.lang.String r0 = "MPD"
            r12 = r45
            org.xmlpull.v1.XmlPullParserFactory r1 = r12.e     // Catch:{ XmlPullParserException -> 0x01ca }
            org.xmlpull.v1.XmlPullParser r13 = r1.newPullParser()     // Catch:{ XmlPullParserException -> 0x01ca }
            r14 = 0
            r1 = r47
            r13.setInput(r1, r14)     // Catch:{ XmlPullParserException -> 0x01ca }
            int r1 = r13.next()     // Catch:{ XmlPullParserException -> 0x01ca }
            r2 = 2
            if (r1 != r2) goto L_0x01c2
            java.lang.String r1 = r13.getName()     // Catch:{ XmlPullParserException -> 0x01ca }
            boolean r1 = r0.equals(r1)     // Catch:{ XmlPullParserException -> 0x01ca }
            if (r1 == 0) goto L_0x01c2
            java.lang.String r1 = r46.toString()     // Catch:{ XmlPullParserException -> 0x01ca }
            java.lang.String r2 = "availabilityStartTime"
            long r16 = c(r13, r2)     // Catch:{ XmlPullParserException -> 0x01ca }
            java.lang.String r2 = "mediaPresentationDuration"
            r10 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            long r18 = a((org.xmlpull.v1.XmlPullParser) r13, (java.lang.String) r2, (long) r10)     // Catch:{ XmlPullParserException -> 0x01ca }
            java.lang.String r2 = "minBufferTime"
            long r20 = a((org.xmlpull.v1.XmlPullParser) r13, (java.lang.String) r2, (long) r10)     // Catch:{ XmlPullParserException -> 0x01ca }
            java.lang.String r2 = "type"
            java.lang.String r2 = r13.getAttributeValue(r14, r2)     // Catch:{ XmlPullParserException -> 0x01ca }
            java.lang.String r3 = "dynamic"
            boolean r22 = r3.equals(r2)     // Catch:{ XmlPullParserException -> 0x01ca }
            if (r22 == 0) goto L_0x0053
            java.lang.String r2 = "minimumUpdatePeriod"
            long r2 = a((org.xmlpull.v1.XmlPullParser) r13, (java.lang.String) r2, (long) r10)     // Catch:{ XmlPullParserException -> 0x01ca }
            r23 = r2
            goto L_0x0055
        L_0x0053:
            r23 = r10
        L_0x0055:
            if (r22 == 0) goto L_0x0060
            java.lang.String r2 = "timeShiftBufferDepth"
            long r2 = a((org.xmlpull.v1.XmlPullParser) r13, (java.lang.String) r2, (long) r10)     // Catch:{ XmlPullParserException -> 0x01ca }
            r25 = r2
            goto L_0x0062
        L_0x0060:
            r25 = r10
        L_0x0062:
            if (r22 == 0) goto L_0x006d
            java.lang.String r2 = "suggestedPresentationDelay"
            long r2 = a((org.xmlpull.v1.XmlPullParser) r13, (java.lang.String) r2, (long) r10)     // Catch:{ XmlPullParserException -> 0x01ca }
            r27 = r2
            goto L_0x006f
        L_0x006d:
            r27 = r10
        L_0x006f:
            java.lang.String r2 = "publishTime"
            long r29 = c(r13, r2)     // Catch:{ XmlPullParserException -> 0x01ca }
            r2 = 0
            if (r22 == 0) goto L_0x007b
            r4 = r2
            goto L_0x007c
        L_0x007b:
            r4 = r10
        L_0x007c:
            java.util.ArrayList r15 = new java.util.ArrayList     // Catch:{ XmlPullParserException -> 0x01ca }
            r15.<init>()     // Catch:{ XmlPullParserException -> 0x01ca }
            if (r22 == 0) goto L_0x0084
            r2 = r10
        L_0x0084:
            r6 = 0
            r8 = r1
            r31 = r2
            r6 = r4
            r35 = r14
            r36 = r35
            r37 = r36
            r38 = r37
            r33 = 0
            r34 = 0
        L_0x0095:
            r13.next()     // Catch:{ XmlPullParserException -> 0x01ca }
            java.lang.String r1 = "BaseURL"
            boolean r1 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r13, r1)     // Catch:{ XmlPullParserException -> 0x01ca }
            r39 = 1
            if (r1 == 0) goto L_0x00bc
            if (r33 != 0) goto L_0x00b4
            long r1 = a((org.xmlpull.v1.XmlPullParser) r13, (long) r6)     // Catch:{ XmlPullParserException -> 0x01ca }
            java.lang.String r3 = a((org.xmlpull.v1.XmlPullParser) r13, (java.lang.String) r8)     // Catch:{ XmlPullParserException -> 0x01ca }
            r6 = r1
            r8 = r3
            r43 = r10
            r33 = 1
            goto L_0x0182
        L_0x00b4:
            r40 = r6
            r42 = r8
            r43 = r10
            goto L_0x017e
        L_0x00bc:
            java.lang.String r1 = "ProgramInformation"
            boolean r1 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r13, r1)     // Catch:{ XmlPullParserException -> 0x01ca }
            if (r1 == 0) goto L_0x00ce
            com.google.android.exoplayer2.source.dash.manifest.ProgramInformation r1 = f((org.xmlpull.v1.XmlPullParser) r13)     // Catch:{ XmlPullParserException -> 0x01ca }
            r35 = r1
        L_0x00ca:
            r43 = r10
            goto L_0x0182
        L_0x00ce:
            java.lang.String r1 = "UTCTiming"
            boolean r1 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r13, r1)     // Catch:{ XmlPullParserException -> 0x01ca }
            if (r1 == 0) goto L_0x00ea
            java.lang.String r1 = "schemeIdUri"
            java.lang.String r1 = r13.getAttributeValue(r14, r1)     // Catch:{ XmlPullParserException -> 0x01ca }
            java.lang.String r2 = "value"
            java.lang.String r2 = r13.getAttributeValue(r14, r2)     // Catch:{ XmlPullParserException -> 0x01ca }
            com.google.android.exoplayer2.source.dash.manifest.UtcTimingElement r3 = new com.google.android.exoplayer2.source.dash.manifest.UtcTimingElement     // Catch:{ XmlPullParserException -> 0x01ca }
            r3.<init>(r1, r2)     // Catch:{ XmlPullParserException -> 0x01ca }
            r36 = r3
            goto L_0x00ca
        L_0x00ea:
            java.lang.String r1 = "Location"
            boolean r1 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r13, r1)     // Catch:{ XmlPullParserException -> 0x01ca }
            if (r1 == 0) goto L_0x00fd
            java.lang.String r1 = r13.nextText()     // Catch:{ XmlPullParserException -> 0x01ca }
            android.net.Uri r1 = android.net.Uri.parse(r1)     // Catch:{ XmlPullParserException -> 0x01ca }
            r37 = r1
            goto L_0x00ca
        L_0x00fd:
            java.lang.String r1 = "ServiceDescription"
            boolean r1 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r13, r1)     // Catch:{ XmlPullParserException -> 0x01ca }
            if (r1 == 0) goto L_0x010c
            com.google.android.exoplayer2.source.dash.manifest.ServiceDescriptionElement r1 = a((org.xmlpull.v1.XmlPullParser) r13)     // Catch:{ XmlPullParserException -> 0x01ca }
            r38 = r1
            goto L_0x00ca
        L_0x010c:
            java.lang.String r1 = "Period"
            boolean r1 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r13, r1)     // Catch:{ XmlPullParserException -> 0x01ca }
            if (r1 == 0) goto L_0x0175
            if (r34 != 0) goto L_0x0175
            r1 = r45
            r2 = r13
            r3 = r8
            r4 = r31
            r40 = r6
            r42 = r8
            r8 = r16
            r43 = r10
            r10 = r25
            android.util.Pair r1 = r1.a(r2, r3, r4, r6, r8, r10)     // Catch:{ XmlPullParserException -> 0x01ca }
            java.lang.Object r2 = r1.first     // Catch:{ XmlPullParserException -> 0x01ca }
            com.google.android.exoplayer2.source.dash.manifest.Period r2 = (com.google.android.exoplayer2.source.dash.manifest.Period) r2     // Catch:{ XmlPullParserException -> 0x01ca }
            long r3 = r2.b     // Catch:{ XmlPullParserException -> 0x01ca }
            int r5 = (r3 > r43 ? 1 : (r3 == r43 ? 0 : -1))
            if (r5 != 0) goto L_0x015c
            if (r22 == 0) goto L_0x013d
            r6 = r40
            r8 = r42
            r34 = 1
            goto L_0x0182
        L_0x013d:
            com.google.android.exoplayer2.ParserException r0 = new com.google.android.exoplayer2.ParserException     // Catch:{ XmlPullParserException -> 0x01ca }
            int r1 = r15.size()     // Catch:{ XmlPullParserException -> 0x01ca }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x01ca }
            r3 = 47
            r2.<init>(r3)     // Catch:{ XmlPullParserException -> 0x01ca }
            java.lang.String r3 = "Unable to determine start of period "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ XmlPullParserException -> 0x01ca }
            java.lang.StringBuilder r1 = r2.append(r1)     // Catch:{ XmlPullParserException -> 0x01ca }
            java.lang.String r1 = r1.toString()     // Catch:{ XmlPullParserException -> 0x01ca }
            r0.<init>((java.lang.String) r1)     // Catch:{ XmlPullParserException -> 0x01ca }
            throw r0     // Catch:{ XmlPullParserException -> 0x01ca }
        L_0x015c:
            java.lang.Object r1 = r1.second     // Catch:{ XmlPullParserException -> 0x01ca }
            java.lang.Long r1 = (java.lang.Long) r1     // Catch:{ XmlPullParserException -> 0x01ca }
            long r3 = r1.longValue()     // Catch:{ XmlPullParserException -> 0x01ca }
            int r1 = (r3 > r43 ? 1 : (r3 == r43 ? 0 : -1))
            if (r1 != 0) goto L_0x016b
            r10 = r43
            goto L_0x016f
        L_0x016b:
            long r5 = r2.b     // Catch:{ XmlPullParserException -> 0x01ca }
            long r10 = r5 + r3
        L_0x016f:
            r15.add(r2)     // Catch:{ XmlPullParserException -> 0x01ca }
            r31 = r10
            goto L_0x017e
        L_0x0175:
            r40 = r6
            r42 = r8
            r43 = r10
            h(r13)     // Catch:{ XmlPullParserException -> 0x01ca }
        L_0x017e:
            r6 = r40
            r8 = r42
        L_0x0182:
            boolean r1 = com.google.android.exoplayer2.util.XmlPullParserUtil.a(r13, r0)     // Catch:{ XmlPullParserException -> 0x01ca }
            if (r1 == 0) goto L_0x01be
            int r0 = (r18 > r43 ? 1 : (r18 == r43 ? 0 : -1))
            if (r0 != 0) goto L_0x019e
            int r0 = (r31 > r43 ? 1 : (r31 == r43 ? 0 : -1))
            if (r0 == 0) goto L_0x0193
            r18 = r31
            goto L_0x019e
        L_0x0193:
            if (r22 == 0) goto L_0x0196
            goto L_0x019e
        L_0x0196:
            com.google.android.exoplayer2.ParserException r0 = new com.google.android.exoplayer2.ParserException     // Catch:{ XmlPullParserException -> 0x01ca }
            java.lang.String r1 = "Unable to determine duration of static manifest."
            r0.<init>((java.lang.String) r1)     // Catch:{ XmlPullParserException -> 0x01ca }
            throw r0     // Catch:{ XmlPullParserException -> 0x01ca }
        L_0x019e:
            boolean r0 = r15.isEmpty()     // Catch:{ XmlPullParserException -> 0x01ca }
            if (r0 != 0) goto L_0x01b6
            com.google.android.exoplayer2.source.dash.manifest.DashManifest r0 = new com.google.android.exoplayer2.source.dash.manifest.DashManifest     // Catch:{ XmlPullParserException -> 0x01ca }
            r1 = r15
            r15 = r0
            r31 = r35
            r32 = r36
            r33 = r38
            r34 = r37
            r35 = r1
            r15.<init>(r16, r18, r20, r22, r23, r25, r27, r29, r31, r32, r33, r34, r35)     // Catch:{ XmlPullParserException -> 0x01ca }
            return r0
        L_0x01b6:
            com.google.android.exoplayer2.ParserException r0 = new com.google.android.exoplayer2.ParserException     // Catch:{ XmlPullParserException -> 0x01ca }
            java.lang.String r1 = "No periods found."
            r0.<init>((java.lang.String) r1)     // Catch:{ XmlPullParserException -> 0x01ca }
            throw r0     // Catch:{ XmlPullParserException -> 0x01ca }
        L_0x01be:
            r10 = r43
            goto L_0x0095
        L_0x01c2:
            com.google.android.exoplayer2.ParserException r0 = new com.google.android.exoplayer2.ParserException     // Catch:{ XmlPullParserException -> 0x01ca }
            java.lang.String r1 = "inputStream does not contain a valid media presentation description"
            r0.<init>((java.lang.String) r1)     // Catch:{ XmlPullParserException -> 0x01ca }
            throw r0     // Catch:{ XmlPullParserException -> 0x01ca }
        L_0x01ca:
            r0 = move-exception
            com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
            r1.<init>((java.lang.Throwable) r0)
            goto L_0x01d2
        L_0x01d1:
            throw r1
        L_0x01d2:
            goto L_0x01d1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.a(android.net.Uri, java.io.InputStream):com.google.android.exoplayer2.source.dash.manifest.DashManifest");
    }

    private static Descriptor b(XmlPullParser xmlPullParser, String str) {
        String b2 = b(xmlPullParser, "schemeIdUri", "");
        String b3 = b(xmlPullParser, "value", (String) null);
        String b4 = b(xmlPullParser, "id", (String) null);
        do {
            xmlPullParser.next();
        } while (!XmlPullParserUtil.a(xmlPullParser, str));
        return new Descriptor(b2, b3, b4);
    }

    private static String b(XmlPullParser xmlPullParser, String str, String str2) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        return attributeValue == null ? str2 : attributeValue;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int c(java.util.List r7) {
        /*
            r0 = 0
            r1 = 0
            r2 = 0
        L_0x0003:
            int r3 = r7.size()
            if (r1 >= r3) goto L_0x0081
            java.lang.Object r3 = r7.get(r1)
            com.google.android.exoplayer2.source.dash.manifest.Descriptor r3 = (com.google.android.exoplayer2.source.dash.manifest.Descriptor) r3
            java.lang.String r4 = "urn:mpeg:dash:role:2011"
            java.lang.String r5 = r3.a
            boolean r4 = com.dreamers.exoplayercore.repack.C0000a.a((java.lang.CharSequence) r4, (java.lang.CharSequence) r5)
            if (r4 == 0) goto L_0x0020
            java.lang.String r3 = r3.b
            int r3 = a((java.lang.String) r3)
            goto L_0x007d
        L_0x0020:
            java.lang.String r4 = "urn:tva:metadata:cs:AudioPurposeCS:2007"
            java.lang.String r5 = r3.a
            boolean r4 = com.dreamers.exoplayercore.repack.C0000a.a((java.lang.CharSequence) r4, (java.lang.CharSequence) r5)
            if (r4 == 0) goto L_0x007e
            java.lang.String r3 = r3.b
            if (r3 == 0) goto L_0x007c
            int r4 = r3.hashCode()
            r5 = 4
            r6 = 1
            switch(r4) {
                case 49: goto L_0x0060;
                case 50: goto L_0x0056;
                case 51: goto L_0x004c;
                case 52: goto L_0x0042;
                case 53: goto L_0x0037;
                case 54: goto L_0x0038;
                default: goto L_0x0037;
            }
        L_0x0037:
            goto L_0x006a
        L_0x0038:
            java.lang.String r4 = "6"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x006a
            r3 = 4
            goto L_0x006b
        L_0x0042:
            java.lang.String r4 = "4"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x006a
            r3 = 3
            goto L_0x006b
        L_0x004c:
            java.lang.String r4 = "3"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x006a
            r3 = 2
            goto L_0x006b
        L_0x0056:
            java.lang.String r4 = "2"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x006a
            r3 = 1
            goto L_0x006b
        L_0x0060:
            java.lang.String r4 = "1"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x006a
            r3 = 0
            goto L_0x006b
        L_0x006a:
            r3 = -1
        L_0x006b:
            switch(r3) {
                case 0: goto L_0x0079;
                case 1: goto L_0x0076;
                case 2: goto L_0x0074;
                case 3: goto L_0x0071;
                case 4: goto L_0x006f;
                default: goto L_0x006e;
            }
        L_0x006e:
            goto L_0x007c
        L_0x006f:
            r3 = 1
            goto L_0x007d
        L_0x0071:
            r3 = 8
            goto L_0x007d
        L_0x0074:
            r3 = 4
            goto L_0x007d
        L_0x0076:
            r3 = 2048(0x800, float:2.87E-42)
            goto L_0x007d
        L_0x0079:
            r3 = 512(0x200, float:7.175E-43)
            goto L_0x007d
        L_0x007c:
            r3 = 0
        L_0x007d:
            r2 = r2 | r3
        L_0x007e:
            int r1 = r1 + 1
            goto L_0x0003
        L_0x0081:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.c(java.util.List):int");
    }

    private static long c(XmlPullParser xmlPullParser, String str) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        if (attributeValue == null) {
            return -9223372036854775807L;
        }
        return Util.e(attributeValue);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003e, code lost:
        r3 = null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.util.Pair c(org.xmlpull.v1.XmlPullParser r9) {
        /*
            java.lang.String r0 = "schemeIdUri"
            r1 = 0
            java.lang.String r0 = r9.getAttributeValue(r1, r0)
            r2 = 0
            if (r0 == 0) goto L_0x0084
            java.lang.String r0 = com.dreamers.exoplayercore.repack.C0000a.a((java.lang.String) r0)
            int r3 = r0.hashCode()
            switch(r3) {
                case 489446379: goto L_0x002a;
                case 755418770: goto L_0x0020;
                case 1812765994: goto L_0x0016;
                default: goto L_0x0015;
            }
        L_0x0015:
            goto L_0x0034
        L_0x0016:
            java.lang.String r3 = "urn:mpeg:dash:mp4protection:2011"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0034
            r0 = 0
            goto L_0x0035
        L_0x0020:
            java.lang.String r3 = "urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0034
            r0 = 2
            goto L_0x0035
        L_0x002a:
            java.lang.String r3 = "urn:uuid:9a04f079-9840-4286-ab92-e65be0885f95"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0034
            r0 = 1
            goto L_0x0035
        L_0x0034:
            r0 = -1
        L_0x0035:
            switch(r0) {
                case 0: goto L_0x0040;
                case 1: goto L_0x003c;
                case 2: goto L_0x0039;
                default: goto L_0x0038;
            }
        L_0x0038:
            goto L_0x0084
        L_0x0039:
            java.util.UUID r0 = com.google.android.exoplayer2.C.d
            goto L_0x003e
        L_0x003c:
            java.util.UUID r0 = com.google.android.exoplayer2.C.e
        L_0x003e:
            r3 = r1
            goto L_0x0086
        L_0x0040:
            java.lang.String r0 = "value"
            java.lang.String r0 = r9.getAttributeValue(r1, r0)
            java.lang.String r3 = "default_KID"
            java.lang.String r3 = com.google.android.exoplayer2.util.XmlPullParserUtil.e(r9, r3)
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 != 0) goto L_0x0080
            java.lang.String r4 = "00000000-0000-0000-0000-000000000000"
            boolean r4 = r4.equals(r3)
            if (r4 != 0) goto L_0x0080
            java.lang.String r4 = "\\s+"
            java.lang.String[] r3 = r3.split(r4)
            int r4 = r3.length
            java.util.UUID[] r4 = new java.util.UUID[r4]
            r5 = 0
        L_0x0064:
            int r6 = r3.length
            if (r5 >= r6) goto L_0x0072
            r6 = r3[r5]
            java.util.UUID r6 = java.util.UUID.fromString(r6)
            r4[r5] = r6
            int r5 = r5 + 1
            goto L_0x0064
        L_0x0072:
            java.util.UUID r3 = com.google.android.exoplayer2.C.b
            byte[] r3 = com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil.a(r3, r4, r1)
            java.util.UUID r4 = com.google.android.exoplayer2.C.b
            r5 = r1
            r8 = r3
            r3 = r0
            r0 = r4
            r4 = r8
            goto L_0x0088
        L_0x0080:
            r3 = r0
            r0 = r1
            r4 = r0
            goto L_0x0087
        L_0x0084:
            r0 = r1
            r3 = r0
        L_0x0086:
            r4 = r3
        L_0x0087:
            r5 = r4
        L_0x0088:
            r9.next()
            java.lang.String r6 = "ms:laurl"
            boolean r6 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r9, r6)
            if (r6 == 0) goto L_0x009a
            java.lang.String r5 = "licenseUrl"
            java.lang.String r5 = r9.getAttributeValue(r1, r5)
            goto L_0x00f1
        L_0x009a:
            r6 = 4
            if (r4 != 0) goto L_0x00c7
            java.lang.String r7 = "pssh"
            boolean r7 = com.google.android.exoplayer2.util.XmlPullParserUtil.c(r9, r7)
            if (r7 == 0) goto L_0x00c7
            int r7 = r9.next()
            if (r7 != r6) goto L_0x00c7
            java.lang.String r0 = r9.getText()
            byte[] r0 = android.util.Base64.decode(r0, r2)
            java.util.UUID r4 = com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil.b(r0)
            if (r4 != 0) goto L_0x00c3
            java.lang.String r0 = "MpdParser"
            java.lang.String r6 = "Skipping malformed cenc:pssh data"
            com.google.android.exoplayer2.util.Log.c(r0, r6)
            r0 = r4
            r4 = r1
            goto L_0x00f1
        L_0x00c3:
            r8 = r4
            r4 = r0
            r0 = r8
            goto L_0x00f1
        L_0x00c7:
            if (r4 != 0) goto L_0x00ee
            java.util.UUID r7 = com.google.android.exoplayer2.C.e
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x00ee
            java.lang.String r7 = "mspr:pro"
            boolean r7 = com.google.android.exoplayer2.util.XmlPullParserUtil.b(r9, r7)
            if (r7 == 0) goto L_0x00ee
            int r7 = r9.next()
            if (r7 != r6) goto L_0x00ee
            java.util.UUID r4 = com.google.android.exoplayer2.C.e
            java.lang.String r6 = r9.getText()
            byte[] r6 = android.util.Base64.decode(r6, r2)
            byte[] r4 = com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil.a((java.util.UUID) r4, (byte[]) r6)
            goto L_0x00f1
        L_0x00ee:
            h(r9)
        L_0x00f1:
            java.lang.String r6 = "ContentProtection"
            boolean r6 = com.google.android.exoplayer2.util.XmlPullParserUtil.a(r9, r6)
            if (r6 == 0) goto L_0x0088
            if (r0 == 0) goto L_0x0102
            com.google.android.exoplayer2.drm.DrmInitData$SchemeData r1 = new com.google.android.exoplayer2.drm.DrmInitData$SchemeData
            java.lang.String r9 = "video/mp4"
            r1.<init>(r0, r5, r9, r4)
        L_0x0102:
            android.util.Pair r9 = android.util.Pair.create(r3, r1)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.c(org.xmlpull.v1.XmlPullParser):android.util.Pair");
    }

    private static int d(List list) {
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (C0000a.a((CharSequence) "http://dashif.org/guidelines/trickmode", (CharSequence) ((Descriptor) list.get(i2)).a)) {
                i |= 16384;
            }
        }
        return i;
    }

    private static EventStream d(XmlPullParser xmlPullParser) {
        long j;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        String b2 = b(xmlPullParser2, "schemeIdUri", "");
        String b3 = b(xmlPullParser2, "value", "");
        long b4 = b(xmlPullParser2, "timescale", 1);
        ArrayList arrayList = new ArrayList();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
        while (true) {
            xmlPullParser.next();
            if (XmlPullParserUtil.b(xmlPullParser2, "Event")) {
                long b5 = b(xmlPullParser2, "id", 0);
                long b6 = b(xmlPullParser2, "duration", -9223372036854775807L);
                long b7 = b(xmlPullParser2, "presentationTime", 0);
                long j2 = b4;
                long b8 = Util.b(b6, 1000, j2);
                long b9 = Util.b(b7, 1000000, j2);
                String b10 = b(xmlPullParser2, "messageData", (String) null);
                byte[] a2 = a(xmlPullParser2, byteArrayOutputStream);
                Long valueOf = Long.valueOf(b9);
                byte[] c2 = b10 == null ? a2 : Util.c(b10);
                long j3 = b8;
                j = b4;
                EventMessage eventMessage = r3;
                EventMessage eventMessage2 = new EventMessage(b2, b3, j3, b5, c2);
                arrayList.add(Pair.create(valueOf, eventMessage));
            } else {
                j = b4;
                h(xmlPullParser);
            }
            if (XmlPullParserUtil.a(xmlPullParser2, "EventStream")) {
                break;
            }
            b4 = j;
        }
        long[] jArr = new long[arrayList.size()];
        EventMessage[] eventMessageArr = new EventMessage[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            Pair pair = (Pair) arrayList.get(i);
            jArr[i] = ((Long) pair.first).longValue();
            eventMessageArr[i] = (EventMessage) pair.second;
        }
        return new EventStream(b2, b3, jArr, eventMessageArr);
    }

    private static String d(XmlPullParser xmlPullParser, String str) {
        String str2 = "";
        do {
            xmlPullParser.next();
            if (xmlPullParser.getEventType() == 4) {
                str2 = xmlPullParser.getText();
            } else {
                h(xmlPullParser);
            }
        } while (!XmlPullParserUtil.a(xmlPullParser, str));
        return str2;
    }

    private static float e(XmlPullParser xmlPullParser, String str) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        if (attributeValue == null) {
            return -3.4028235E38f;
        }
        return Float.parseFloat(attributeValue);
    }

    private static int e(List list) {
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = (Descriptor) list.get(i);
            if ("urn:scte:dash:cc:cea-608:2015".equals(descriptor.a) && descriptor.b != null) {
                Matcher matcher = b.matcher(descriptor.b);
                if (matcher.matches()) {
                    return Integer.parseInt(matcher.group(1));
                }
                String valueOf = String.valueOf(descriptor.b);
                Log.c("MpdParser", valueOf.length() != 0 ? "Unable to parse CEA-608 channel number from: ".concat(valueOf) : new String("Unable to parse CEA-608 channel number from: "));
            }
        }
        return -1;
    }

    private static RangedUri e(XmlPullParser xmlPullParser) {
        return a(xmlPullParser, "sourceURL", "range");
    }

    private static int f(List list) {
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = (Descriptor) list.get(i);
            if ("urn:scte:dash:cc:cea-708:2015".equals(descriptor.a) && descriptor.b != null) {
                Matcher matcher = c.matcher(descriptor.b);
                if (matcher.matches()) {
                    return Integer.parseInt(matcher.group(1));
                }
                String valueOf = String.valueOf(descriptor.b);
                Log.c("MpdParser", valueOf.length() != 0 ? "Unable to parse CEA-708 service block number from: ".concat(valueOf) : new String("Unable to parse CEA-708 service block number from: "));
            }
        }
        return -1;
    }

    private static ProgramInformation f(XmlPullParser xmlPullParser) {
        String str = null;
        String b2 = b(xmlPullParser, "moreInformationURL", (String) null);
        String b3 = b(xmlPullParser, "lang", (String) null);
        String str2 = null;
        String str3 = null;
        while (true) {
            xmlPullParser.next();
            if (XmlPullParserUtil.b(xmlPullParser, "Title")) {
                str = xmlPullParser.nextText();
            } else if (XmlPullParserUtil.b(xmlPullParser, "Source")) {
                str2 = xmlPullParser.nextText();
            } else if (XmlPullParserUtil.b(xmlPullParser, "Copyright")) {
                str3 = xmlPullParser.nextText();
            } else {
                h(xmlPullParser);
            }
            String str4 = str3;
            if (XmlPullParserUtil.a(xmlPullParser, "ProgramInformation")) {
                return new ProgramInformation(str, str2, str4, b2, b3);
            }
            str3 = str4;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005c, code lost:
        if (r0.equals("fa01") != false) goto L_0x007e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int g(org.xmlpull.v1.XmlPullParser r8) {
        /*
            java.lang.String r0 = "schemeIdUri"
            r1 = 0
            java.lang.String r0 = b((org.xmlpull.v1.XmlPullParser) r8, (java.lang.String) r0, (java.lang.String) r1)
            int r2 = r0.hashCode()
            r3 = 3
            r4 = 0
            r5 = 1
            r6 = 2
            r7 = -1
            switch(r2) {
                case -1352850286: goto L_0x0032;
                case -1138141449: goto L_0x0028;
                case -986633423: goto L_0x001e;
                case 2036691300: goto L_0x0014;
                default: goto L_0x0013;
            }
        L_0x0013:
            goto L_0x003c
        L_0x0014:
            java.lang.String r2 = "urn:dolby:dash:audio_channel_configuration:2011"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x003c
            r0 = 3
            goto L_0x003d
        L_0x001e:
            java.lang.String r2 = "urn:mpeg:mpegB:cicp:ChannelConfiguration"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x003c
            r0 = 1
            goto L_0x003d
        L_0x0028:
            java.lang.String r2 = "tag:dolby.com,2014:dash:audio_channel_configuration:2011"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x003c
            r0 = 2
            goto L_0x003d
        L_0x0032:
            java.lang.String r2 = "urn:mpeg:dash:23003:3:audio_channel_configuration:2011"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x003c
            r0 = 0
            goto L_0x003d
        L_0x003c:
            r0 = -1
        L_0x003d:
            java.lang.String r2 = "value"
            switch(r0) {
                case 0: goto L_0x009c;
                case 1: goto L_0x008c;
                case 2: goto L_0x0044;
                case 3: goto L_0x0044;
                default: goto L_0x0042;
            }
        L_0x0042:
            goto L_0x00a0
        L_0x0044:
            java.lang.String r0 = r8.getAttributeValue(r1, r2)
            if (r0 == 0) goto L_0x0089
            java.lang.String r0 = com.dreamers.exoplayercore.repack.C0000a.a((java.lang.String) r0)
            int r1 = r0.hashCode()
            switch(r1) {
                case 1596796: goto L_0x0073;
                case 2937391: goto L_0x0069;
                case 3094035: goto L_0x005f;
                case 3133436: goto L_0x0056;
                default: goto L_0x0055;
            }
        L_0x0055:
            goto L_0x007d
        L_0x0056:
            java.lang.String r1 = "fa01"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x007d
            goto L_0x007e
        L_0x005f:
            java.lang.String r1 = "f801"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x007d
            r3 = 2
            goto L_0x007e
        L_0x0069:
            java.lang.String r1 = "a000"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x007d
            r3 = 1
            goto L_0x007e
        L_0x0073:
            java.lang.String r1 = "4000"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x007d
            r3 = 0
            goto L_0x007e
        L_0x007d:
            r3 = -1
        L_0x007e:
            switch(r3) {
                case 0: goto L_0x008a;
                case 1: goto L_0x0087;
                case 2: goto L_0x0085;
                case 3: goto L_0x0082;
                default: goto L_0x0081;
            }
        L_0x0081:
            goto L_0x0089
        L_0x0082:
            r5 = 8
            goto L_0x008a
        L_0x0085:
            r5 = 6
            goto L_0x008a
        L_0x0087:
            r5 = 2
            goto L_0x008a
        L_0x0089:
            r5 = -1
        L_0x008a:
            r7 = r5
            goto L_0x00a0
        L_0x008c:
            int r0 = a((org.xmlpull.v1.XmlPullParser) r8, (java.lang.String) r2, (int) r7)
            if (r0 < 0) goto L_0x00a0
            r1 = 21
            if (r0 >= r1) goto L_0x00a0
            int[] r1 = d
            r0 = r1[r0]
            r7 = r0
            goto L_0x00a0
        L_0x009c:
            int r7 = a((org.xmlpull.v1.XmlPullParser) r8, (java.lang.String) r2, (int) r7)
        L_0x00a0:
            r8.next()
            java.lang.String r0 = "AudioChannelConfiguration"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.a(r8, r0)
            if (r0 == 0) goto L_0x00a0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.g(org.xmlpull.v1.XmlPullParser):int");
    }

    private static String g(List list) {
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = (Descriptor) list.get(i);
            String str = descriptor.a;
            if ("tag:dolby.com,2018:dash:EC3_ExtensionType:2018".equals(str) && "JOC".equals(descriptor.b)) {
                return "audio/eac3-joc";
            }
            if ("tag:dolby.com,2014:dash:DolbyDigitalPlusExtensionType:2014".equals(str) && "ec+3".equals(descriptor.b)) {
                return "audio/eac3-joc";
            }
        }
        return "audio/eac3";
    }

    private static void h(XmlPullParser xmlPullParser) {
        if (XmlPullParserUtil.b(xmlPullParser)) {
            int i = 1;
            while (i != 0) {
                xmlPullParser.next();
                if (XmlPullParserUtil.b(xmlPullParser)) {
                    i++;
                } else if (XmlPullParserUtil.a(xmlPullParser)) {
                    i--;
                }
            }
        }
    }
}
