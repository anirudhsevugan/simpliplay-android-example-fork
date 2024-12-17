package com.google.android.exoplayer2.util;

import android.text.TextUtils;
import com.dreamers.exoplayercore.repack.C0000a;
import com.google.appinventor.components.common.PropertyTypeConstants;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MimeTypes {
    private static final ArrayList a = new ArrayList();
    private static final Pattern b = Pattern.compile("^mp4a\\.([a-zA-Z0-9]{2})(?:\\.([0-9]{1,2}))?$");

    final class CustomMimeType {
        public final String a;
        public final String b;
        public final int c;
    }

    final class Mp4aObjectType {
        public final int a;
        public final int b;

        public Mp4aObjectType(int i, int i2) {
            this.a = i;
            this.b = i2;
        }
    }

    public static String a(int i) {
        switch (i) {
            case 32:
                return "video/mp4v-es";
            case 33:
                return "video/avc";
            case 35:
                return "video/hevc";
            case 64:
            case 102:
            case 103:
            case 104:
                return "audio/mp4a-latm";
            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
                return "video/mpeg2";
            case 105:
            case 107:
                return "audio/mpeg";
            case 106:
                return "video/mpeg";
            case 163:
                return "video/wvc1";
            case 165:
                return "audio/ac3";
            case 166:
                return "audio/eac3";
            case 169:
            case 172:
                return "audio/vnd.dts";
            case 170:
            case 171:
                return "audio/vnd.dts.hd";
            case 173:
                return "audio/opus";
            case 174:
                return "audio/ac4";
            case 177:
                return "video/x-vnd.on2.vp9";
            default:
                return null;
        }
    }

    public static boolean a(String str) {
        return "audio".equals(l(str));
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x008f, code lost:
        r3 = com.google.android.exoplayer2.audio.AacUtil.a((r3 = n(r4)).b);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.lang.String r3, java.lang.String r4) {
        /*
            r0 = 0
            if (r3 != 0) goto L_0x0004
            return r0
        L_0x0004:
            int r1 = r3.hashCode()
            r2 = 1
            switch(r1) {
                case -2123537834: goto L_0x0075;
                case -432837260: goto L_0x006b;
                case -432837259: goto L_0x0061;
                case -53558318: goto L_0x0056;
                case 187078296: goto L_0x004c;
                case 187094639: goto L_0x0042;
                case 1504578661: goto L_0x0037;
                case 1504619009: goto L_0x002d;
                case 1504831518: goto L_0x0023;
                case 1903231877: goto L_0x0019;
                case 1903589369: goto L_0x000e;
                default: goto L_0x000c;
            }
        L_0x000c:
            goto L_0x0080
        L_0x000e:
            java.lang.String r1 = "audio/g711-mlaw"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0080
            r3 = 5
            goto L_0x0081
        L_0x0019:
            java.lang.String r1 = "audio/g711-alaw"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0080
            r3 = 4
            goto L_0x0081
        L_0x0023:
            java.lang.String r1 = "audio/mpeg"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0080
            r3 = 0
            goto L_0x0081
        L_0x002d:
            java.lang.String r1 = "audio/flac"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0080
            r3 = 6
            goto L_0x0081
        L_0x0037:
            java.lang.String r1 = "audio/eac3"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0080
            r3 = 8
            goto L_0x0081
        L_0x0042:
            java.lang.String r1 = "audio/raw"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0080
            r3 = 3
            goto L_0x0081
        L_0x004c:
            java.lang.String r1 = "audio/ac3"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0080
            r3 = 7
            goto L_0x0081
        L_0x0056:
            java.lang.String r1 = "audio/mp4a-latm"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0080
            r3 = 10
            goto L_0x0081
        L_0x0061:
            java.lang.String r1 = "audio/mpeg-L2"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0080
            r3 = 2
            goto L_0x0081
        L_0x006b:
            java.lang.String r1 = "audio/mpeg-L1"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0080
            r3 = 1
            goto L_0x0081
        L_0x0075:
            java.lang.String r1 = "audio/eac3-joc"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0080
            r3 = 9
            goto L_0x0081
        L_0x0080:
            r3 = -1
        L_0x0081:
            switch(r3) {
                case 0: goto L_0x009d;
                case 1: goto L_0x009d;
                case 2: goto L_0x009d;
                case 3: goto L_0x009d;
                case 4: goto L_0x009d;
                case 5: goto L_0x009d;
                case 6: goto L_0x009d;
                case 7: goto L_0x009d;
                case 8: goto L_0x009d;
                case 9: goto L_0x009d;
                case 10: goto L_0x0085;
                default: goto L_0x0084;
            }
        L_0x0084:
            return r0
        L_0x0085:
            if (r4 != 0) goto L_0x0088
            return r0
        L_0x0088:
            com.google.android.exoplayer2.util.MimeTypes$Mp4aObjectType r3 = n(r4)
            if (r3 != 0) goto L_0x008f
            return r0
        L_0x008f:
            int r3 = r3.b
            int r3 = com.google.android.exoplayer2.audio.AacUtil.a((int) r3)
            if (r3 == 0) goto L_0x009c
            r4 = 16
            if (r3 == r4) goto L_0x009c
            return r2
        L_0x009c:
            return r0
        L_0x009d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.util.MimeTypes.a(java.lang.String, java.lang.String):boolean");
    }

    public static boolean b(String str) {
        return "video".equals(l(str));
    }

    public static boolean b(String str, String str2) {
        return c(str, str2) != null;
    }

    public static String c(String str, String str2) {
        if (!(str == null || str2 == null)) {
            String[] f = Util.f(str);
            StringBuilder sb = new StringBuilder();
            for (String str3 : f) {
                if (str2.equals(g(str3))) {
                    if (sb.length() > 0) {
                        sb.append(",");
                    }
                    sb.append(str3);
                }
            }
            if (sb.length() > 0) {
                return sb.toString();
            }
        }
        return null;
    }

    public static boolean c(String str) {
        return PropertyTypeConstants.PROPERTY_TYPE_TEXT.equals(l(str)) || "application/cea-608".equals(str) || "application/cea-708".equals(str) || "application/x-mp4-cea-608".equals(str) || "application/x-subrip".equals(str) || "application/ttml+xml".equals(str) || "application/x-quicktime-tx3g".equals(str) || "application/x-mp4-vtt".equals(str) || "application/x-rawcc".equals(str) || "application/vobsub".equals(str) || "application/pgs".equals(str) || "application/dvbsubs".equals(str);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int d(java.lang.String r6, java.lang.String r7) {
        /*
            int r0 = r6.hashCode()
            r1 = 8
            r2 = 7
            r3 = 6
            r4 = 5
            r5 = 0
            switch(r0) {
                case -2123537834: goto L_0x005f;
                case -1095064472: goto L_0x0055;
                case -53558318: goto L_0x004b;
                case 187078296: goto L_0x0041;
                case 187078297: goto L_0x0037;
                case 1504578661: goto L_0x002d;
                case 1504831518: goto L_0x0023;
                case 1505942594: goto L_0x0019;
                case 1556697186: goto L_0x000e;
                default: goto L_0x000d;
            }
        L_0x000d:
            goto L_0x0069
        L_0x000e:
            java.lang.String r0 = "audio/true-hd"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0069
            r6 = 8
            goto L_0x006a
        L_0x0019:
            java.lang.String r0 = "audio/vnd.dts.hd"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0069
            r6 = 7
            goto L_0x006a
        L_0x0023:
            java.lang.String r0 = "audio/mpeg"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0069
            r6 = 0
            goto L_0x006a
        L_0x002d:
            java.lang.String r0 = "audio/eac3"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0069
            r6 = 3
            goto L_0x006a
        L_0x0037:
            java.lang.String r0 = "audio/ac4"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0069
            r6 = 5
            goto L_0x006a
        L_0x0041:
            java.lang.String r0 = "audio/ac3"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0069
            r6 = 2
            goto L_0x006a
        L_0x004b:
            java.lang.String r0 = "audio/mp4a-latm"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0069
            r6 = 1
            goto L_0x006a
        L_0x0055:
            java.lang.String r0 = "audio/vnd.dts"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0069
            r6 = 6
            goto L_0x006a
        L_0x005f:
            java.lang.String r0 = "audio/eac3-joc"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0069
            r6 = 4
            goto L_0x006a
        L_0x0069:
            r6 = -1
        L_0x006a:
            switch(r6) {
                case 0: goto L_0x008c;
                case 1: goto L_0x007b;
                case 2: goto L_0x007a;
                case 3: goto L_0x0079;
                case 4: goto L_0x0076;
                case 5: goto L_0x0073;
                case 6: goto L_0x0072;
                case 7: goto L_0x0071;
                case 8: goto L_0x006e;
                default: goto L_0x006d;
            }
        L_0x006d:
            return r5
        L_0x006e:
            r6 = 14
            return r6
        L_0x0071:
            return r1
        L_0x0072:
            return r2
        L_0x0073:
            r6 = 17
            return r6
        L_0x0076:
            r6 = 18
            return r6
        L_0x0079:
            return r3
        L_0x007a:
            return r4
        L_0x007b:
            if (r7 != 0) goto L_0x007e
            return r5
        L_0x007e:
            com.google.android.exoplayer2.util.MimeTypes$Mp4aObjectType r6 = n(r7)
            if (r6 != 0) goto L_0x0085
            return r5
        L_0x0085:
            int r6 = r6.b
            int r6 = com.google.android.exoplayer2.audio.AacUtil.a((int) r6)
            return r6
        L_0x008c:
            r6 = 9
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.util.MimeTypes.d(java.lang.String, java.lang.String):int");
    }

    public static String d(String str) {
        if (str == null) {
            return null;
        }
        for (String g : Util.f(str)) {
            String g2 = g(g);
            if (g2 != null && b(g2)) {
                return g2;
            }
        }
        return null;
    }

    public static String e(String str) {
        if (str == null) {
            return null;
        }
        for (String g : Util.f(str)) {
            String g2 = g(g);
            if (g2 != null && a(g2)) {
                return g2;
            }
        }
        return null;
    }

    public static String f(String str) {
        if (str == null) {
            return null;
        }
        for (String g : Util.f(str)) {
            String g2 = g(g);
            if (g2 != null && c(g2)) {
                return g2;
            }
        }
        return null;
    }

    public static String g(String str) {
        Mp4aObjectType n;
        String str2 = null;
        if (str == null) {
            return null;
        }
        String a2 = C0000a.a(str.trim());
        if (a2.startsWith("avc1") || a2.startsWith("avc3")) {
            return "video/avc";
        }
        if (a2.startsWith("hev1") || a2.startsWith("hvc1")) {
            return "video/hevc";
        }
        if (a2.startsWith("dvav") || a2.startsWith("dva1") || a2.startsWith("dvhe") || a2.startsWith("dvh1")) {
            return "video/dolby-vision";
        }
        if (a2.startsWith("av01")) {
            return "video/av01";
        }
        if (a2.startsWith("vp9") || a2.startsWith("vp09")) {
            return "video/x-vnd.on2.vp9";
        }
        if (a2.startsWith("vp8") || a2.startsWith("vp08")) {
            return "video/x-vnd.on2.vp8";
        }
        if (!a2.startsWith("mp4a")) {
            return a2.startsWith("mha1") ? "audio/mha1" : a2.startsWith("mhm1") ? "audio/mhm1" : (a2.startsWith("ac-3") || a2.startsWith("dac3")) ? "audio/ac3" : (a2.startsWith("ec-3") || a2.startsWith("dec3")) ? "audio/eac3" : a2.startsWith("ec+3") ? "audio/eac3-joc" : (a2.startsWith("ac-4") || a2.startsWith("dac4")) ? "audio/ac4" : (a2.startsWith("dtsc") || a2.startsWith("dtse")) ? "audio/vnd.dts" : (a2.startsWith("dtsh") || a2.startsWith("dtsl")) ? "audio/vnd.dts.hd" : a2.startsWith("opus") ? "audio/opus" : a2.startsWith("vorbis") ? "audio/vorbis" : a2.startsWith("flac") ? "audio/flac" : a2.startsWith("stpp") ? "application/ttml+xml" : a2.startsWith("wvtt") ? "text/vtt" : a2.contains("cea708") ? "application/cea-708" : (a2.contains("eia608") || a2.contains("cea608")) ? "application/cea-608" : m(a2);
        }
        if (a2.startsWith("mp4a.") && (n = n(a2)) != null) {
            str2 = a(n.a);
        }
        return str2 == null ? "audio/mp4a-latm" : str2;
    }

    public static int h(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (a(str)) {
            return 1;
        }
        if (b(str)) {
            return 2;
        }
        if (c(str)) {
            return 3;
        }
        if ("application/id3".equals(str) || "application/x-emsg".equals(str) || "application/x-scte35".equals(str)) {
            return 5;
        }
        if ("application/x-camera-motion".equals(str)) {
            return 6;
        }
        int size = a.size();
        for (int i = 0; i < size; i++) {
            CustomMimeType customMimeType = (CustomMimeType) a.get(i);
            if (str.equals(customMimeType.a)) {
                return customMimeType.c;
            }
        }
        return -1;
    }

    public static int i(String str) {
        return h(g(str));
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String j(java.lang.String r1) {
        /*
            int r0 = r1.hashCode()
            switch(r0) {
                case -1007807498: goto L_0x001c;
                case -586683234: goto L_0x0012;
                case 187090231: goto L_0x0008;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x0026
        L_0x0008:
            java.lang.String r0 = "audio/mp3"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0026
            r0 = 1
            goto L_0x0027
        L_0x0012:
            java.lang.String r0 = "audio/x-wav"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0026
            r0 = 2
            goto L_0x0027
        L_0x001c:
            java.lang.String r0 = "audio/x-flac"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0026
            r0 = 0
            goto L_0x0027
        L_0x0026:
            r0 = -1
        L_0x0027:
            switch(r0) {
                case 0: goto L_0x0031;
                case 1: goto L_0x002e;
                case 2: goto L_0x002b;
                default: goto L_0x002a;
            }
        L_0x002a:
            return r1
        L_0x002b:
            java.lang.String r1 = "audio/wav"
            return r1
        L_0x002e:
            java.lang.String r1 = "audio/mpeg"
            return r1
        L_0x0031:
            java.lang.String r1 = "audio/flac"
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.util.MimeTypes.j(java.lang.String):java.lang.String");
    }

    public static boolean k(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith("video/webm") || str.startsWith("audio/webm") || str.startsWith("application/webm") || str.startsWith("video/x-matroska") || str.startsWith("audio/x-matroska") || str.startsWith("application/x-matroska");
    }

    private static String l(String str) {
        int indexOf;
        if (str == null || (indexOf = str.indexOf(47)) == -1) {
            return null;
        }
        return str.substring(0, indexOf);
    }

    private static String m(String str) {
        int size = a.size();
        for (int i = 0; i < size; i++) {
            CustomMimeType customMimeType = (CustomMimeType) a.get(i);
            if (str.startsWith(customMimeType.b)) {
                return customMimeType.a;
            }
        }
        return null;
    }

    private static Mp4aObjectType n(String str) {
        Matcher matcher = b.matcher(str);
        if (!matcher.matches()) {
            return null;
        }
        String str2 = (String) Assertions.b((Object) matcher.group(1));
        String group = matcher.group(2);
        try {
            return new Mp4aObjectType(Integer.parseInt(str2, 16), group != null ? Integer.parseInt(group) : 0);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
