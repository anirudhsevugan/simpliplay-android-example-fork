package com.google.android.exoplayer2.mediacodec;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.text.TextUtils;
import android.util.Pair;
import com.dreamers.exoplayercore.repack.C0000a;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.ColorInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public final class MediaCodecUtil {
    private static final Pattern a = Pattern.compile("^\\D?(\\d+)$");
    private static final HashMap b = new HashMap();
    private static int c = -1;

    final class CodecKey {
        public final String a;
        public final boolean b;
        public final boolean c;

        public CodecKey(String str, boolean z, boolean z2) {
            this.a = str;
            this.b = z;
            this.c = z2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && obj.getClass() == CodecKey.class) {
                CodecKey codecKey = (CodecKey) obj;
                return TextUtils.equals(this.a, codecKey.a) && this.b == codecKey.b && this.c == codecKey.c;
            }
        }

        public final int hashCode() {
            int i = 1231;
            int hashCode = (((this.a.hashCode() + 31) * 31) + (this.b ? 1231 : 1237)) * 31;
            if (!this.c) {
                i = 1237;
            }
            return hashCode + i;
        }
    }

    public class DecoderQueryException extends Exception {
        private DecoderQueryException(Throwable th) {
            super("Failed to query underlying media codecs", th);
        }

        /* synthetic */ DecoderQueryException(Throwable th, byte b) {
            this(th);
        }
    }

    interface MediaCodecListCompat {
        int a();

        MediaCodecInfo a(int i);

        boolean a(String str, MediaCodecInfo.CodecCapabilities codecCapabilities);

        boolean a(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities);

        boolean b();
    }

    final class MediaCodecListCompatV16 implements MediaCodecListCompat {
        private MediaCodecListCompatV16() {
        }

        /* synthetic */ MediaCodecListCompatV16(byte b) {
            this();
        }

        public final int a() {
            return MediaCodecList.getCodecCount();
        }

        public final MediaCodecInfo a(int i) {
            return MediaCodecList.getCodecInfoAt(i);
        }

        public final boolean a(String str, MediaCodecInfo.CodecCapabilities codecCapabilities) {
            return false;
        }

        public final boolean a(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities) {
            return "secure-playback".equals(str) && "video/avc".equals(str2);
        }

        public final boolean b() {
            return false;
        }
    }

    final class MediaCodecListCompatV21 implements MediaCodecListCompat {
        private final int a;
        private MediaCodecInfo[] b;

        public MediaCodecListCompatV21(boolean z, boolean z2) {
            this.a = (z || z2) ? 1 : 0;
        }

        private void c() {
            if (this.b == null) {
                this.b = new MediaCodecList(this.a).getCodecInfos();
            }
        }

        public final int a() {
            c();
            return this.b.length;
        }

        public final MediaCodecInfo a(int i) {
            c();
            return this.b[i];
        }

        public final boolean a(String str, MediaCodecInfo.CodecCapabilities codecCapabilities) {
            return codecCapabilities.isFeatureRequired(str);
        }

        public final boolean a(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities) {
            return codecCapabilities.isFeatureSupported(str);
        }

        public final boolean b() {
            return true;
        }
    }

    interface ScoreProvider {
        int a(Object obj);
    }

    static final /* synthetic */ int a(Format format, MediaCodecInfo mediaCodecInfo) {
        try {
            return mediaCodecInfo.a(format) ? 1 : 0;
        } catch (DecoderQueryException e) {
            return -1;
        }
    }

    static final /* synthetic */ int a(MediaCodecInfo mediaCodecInfo) {
        return mediaCodecInfo.a.startsWith("OMX.google") ? 1 : 0;
    }

    static final /* synthetic */ int a(ScoreProvider scoreProvider, Object obj, Object obj2) {
        return scoreProvider.a(obj2) - scoreProvider.a(obj);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x01e0, code lost:
        r1 = java.lang.Integer.valueOf(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:302:0x047f, code lost:
        r3 = java.lang.Integer.valueOf(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.util.Pair a(com.google.android.exoplayer2.Format r18) {
        /*
            r0 = r18
            java.lang.String r1 = r0.i
            r2 = 0
            if (r1 != 0) goto L_0x0008
            return r2
        L_0x0008:
            java.lang.String r1 = r0.i
            java.lang.String r3 = "\\."
            java.lang.String[] r1 = r1.split(r3)
            java.lang.String r3 = "video/dolby-vision"
            java.lang.String r4 = r0.l
            boolean r3 = r3.equals(r4)
            r6 = 512(0x200, float:7.175E-43)
            r7 = 256(0x100, float:3.59E-43)
            r8 = 128(0x80, float:1.794E-43)
            r9 = 64
            r10 = 32
            r11 = 16
            r15 = 0
            r16 = 8
            r4 = 3
            r5 = 4
            java.lang.String r12 = "MediaCodecUtil"
            r17 = 2
            r13 = 1
            if (r3 == 0) goto L_0x023a
            java.lang.String r0 = r0.i
            int r3 = r1.length
            java.lang.String r14 = "Ignoring malformed Dolby Vision codec string: "
            if (r3 >= r4) goto L_0x004f
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r1 = r0.length()
            if (r1 == 0) goto L_0x0046
            java.lang.String r0 = r14.concat(r0)
            goto L_0x004b
        L_0x0046:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r14)
        L_0x004b:
            com.google.android.exoplayer2.util.Log.c(r12, r0)
            return r2
        L_0x004f:
            java.util.regex.Pattern r3 = a
            r4 = r1[r13]
            java.util.regex.Matcher r3 = r3.matcher(r4)
            boolean r4 = r3.matches()
            if (r4 != 0) goto L_0x0075
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r1 = r0.length()
            if (r1 == 0) goto L_0x006c
            java.lang.String r0 = r14.concat(r0)
            goto L_0x0071
        L_0x006c:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r14)
        L_0x0071:
            com.google.android.exoplayer2.util.Log.c(r12, r0)
            return r2
        L_0x0075:
            java.lang.String r0 = r3.group(r13)
            java.lang.String r3 = "01"
            if (r0 == 0) goto L_0x0121
            int r4 = r0.hashCode()
            switch(r4) {
                case 1536: goto L_0x00e0;
                case 1537: goto L_0x00d8;
                case 1538: goto L_0x00ce;
                case 1539: goto L_0x00c4;
                case 1540: goto L_0x00ba;
                case 1541: goto L_0x00b0;
                case 1542: goto L_0x00a6;
                case 1543: goto L_0x009c;
                case 1544: goto L_0x0091;
                case 1545: goto L_0x0086;
                default: goto L_0x0084;
            }
        L_0x0084:
            goto L_0x00ea
        L_0x0086:
            java.lang.String r4 = "09"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x00ea
            r4 = 9
            goto L_0x00eb
        L_0x0091:
            java.lang.String r4 = "08"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x00ea
            r4 = 8
            goto L_0x00eb
        L_0x009c:
            java.lang.String r4 = "07"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x00ea
            r4 = 7
            goto L_0x00eb
        L_0x00a6:
            java.lang.String r4 = "06"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x00ea
            r4 = 6
            goto L_0x00eb
        L_0x00b0:
            java.lang.String r4 = "05"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x00ea
            r4 = 5
            goto L_0x00eb
        L_0x00ba:
            java.lang.String r4 = "04"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x00ea
            r4 = 4
            goto L_0x00eb
        L_0x00c4:
            java.lang.String r4 = "03"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x00ea
            r4 = 3
            goto L_0x00eb
        L_0x00ce:
            java.lang.String r4 = "02"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x00ea
            r4 = 2
            goto L_0x00eb
        L_0x00d8:
            boolean r4 = r0.equals(r3)
            if (r4 == 0) goto L_0x00ea
            r4 = 1
            goto L_0x00eb
        L_0x00e0:
            java.lang.String r4 = "00"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x00ea
            r4 = 0
            goto L_0x00eb
        L_0x00ea:
            r4 = -1
        L_0x00eb:
            switch(r4) {
                case 0: goto L_0x011c;
                case 1: goto L_0x0117;
                case 2: goto L_0x0112;
                case 3: goto L_0x010d;
                case 4: goto L_0x0108;
                case 5: goto L_0x0103;
                case 6: goto L_0x00fe;
                case 7: goto L_0x00f9;
                case 8: goto L_0x00f4;
                case 9: goto L_0x00ef;
                default: goto L_0x00ee;
            }
        L_0x00ee:
            goto L_0x0121
        L_0x00ef:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r6)
            goto L_0x0122
        L_0x00f4:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r7)
            goto L_0x0122
        L_0x00f9:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r8)
            goto L_0x0122
        L_0x00fe:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r9)
            goto L_0x0122
        L_0x0103:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            goto L_0x0122
        L_0x0108:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r11)
            goto L_0x0122
        L_0x010d:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r16)
            goto L_0x0122
        L_0x0112:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r5)
            goto L_0x0122
        L_0x0117:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r17)
            goto L_0x0122
        L_0x011c:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r13)
            goto L_0x0122
        L_0x0121:
            r4 = r2
        L_0x0122:
            if (r4 != 0) goto L_0x013e
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r1 = r0.length()
            java.lang.String r3 = "Unknown Dolby Vision profile string: "
            if (r1 == 0) goto L_0x0135
            java.lang.String r0 = r3.concat(r0)
            goto L_0x013a
        L_0x0135:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r3)
        L_0x013a:
            com.google.android.exoplayer2.util.Log.c(r12, r0)
            return r2
        L_0x013e:
            r0 = r1[r17]
            if (r0 == 0) goto L_0x0217
            int r1 = r0.hashCode()
            switch(r1) {
                case 1537: goto L_0x01cb;
                case 1538: goto L_0x01c1;
                case 1539: goto L_0x01b7;
                case 1540: goto L_0x01ad;
                case 1541: goto L_0x01a3;
                case 1542: goto L_0x0199;
                case 1543: goto L_0x018f;
                case 1544: goto L_0x0185;
                case 1545: goto L_0x017a;
                case 1567: goto L_0x016f;
                case 1568: goto L_0x0163;
                case 1569: goto L_0x0157;
                case 1570: goto L_0x014b;
                default: goto L_0x0149;
            }
        L_0x0149:
            goto L_0x01d3
        L_0x014b:
            java.lang.String r1 = "13"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x01d3
            r1 = 12
            goto L_0x01d4
        L_0x0157:
            java.lang.String r1 = "12"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x01d3
            r1 = 11
            goto L_0x01d4
        L_0x0163:
            java.lang.String r1 = "11"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x01d3
            r1 = 10
            goto L_0x01d4
        L_0x016f:
            java.lang.String r1 = "10"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x01d3
            r1 = 9
            goto L_0x01d4
        L_0x017a:
            java.lang.String r1 = "09"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x01d3
            r1 = 8
            goto L_0x01d4
        L_0x0185:
            java.lang.String r1 = "08"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x01d3
            r1 = 7
            goto L_0x01d4
        L_0x018f:
            java.lang.String r1 = "07"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x01d3
            r1 = 6
            goto L_0x01d4
        L_0x0199:
            java.lang.String r1 = "06"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x01d3
            r1 = 5
            goto L_0x01d4
        L_0x01a3:
            java.lang.String r1 = "05"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x01d3
            r1 = 4
            goto L_0x01d4
        L_0x01ad:
            java.lang.String r1 = "04"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x01d3
            r1 = 3
            goto L_0x01d4
        L_0x01b7:
            java.lang.String r1 = "03"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x01d3
            r1 = 2
            goto L_0x01d4
        L_0x01c1:
            java.lang.String r1 = "02"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x01d3
            r1 = 1
            goto L_0x01d4
        L_0x01cb:
            boolean r1 = r0.equals(r3)
            if (r1 == 0) goto L_0x01d3
            r1 = 0
            goto L_0x01d4
        L_0x01d3:
            r1 = -1
        L_0x01d4:
            switch(r1) {
                case 0: goto L_0x0212;
                case 1: goto L_0x020d;
                case 2: goto L_0x0208;
                case 3: goto L_0x0203;
                case 4: goto L_0x01fe;
                case 5: goto L_0x01f9;
                case 6: goto L_0x01f4;
                case 7: goto L_0x01ef;
                case 8: goto L_0x01ea;
                case 9: goto L_0x01e5;
                case 10: goto L_0x01de;
                case 11: goto L_0x01db;
                case 12: goto L_0x01d8;
                default: goto L_0x01d7;
            }
        L_0x01d7:
            goto L_0x0217
        L_0x01d8:
            r1 = 4096(0x1000, float:5.74E-42)
            goto L_0x01e0
        L_0x01db:
            r1 = 2048(0x800, float:2.87E-42)
            goto L_0x01e0
        L_0x01de:
            r1 = 1024(0x400, float:1.435E-42)
        L_0x01e0:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            goto L_0x0218
        L_0x01e5:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r6)
            goto L_0x0218
        L_0x01ea:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r7)
            goto L_0x0218
        L_0x01ef:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r8)
            goto L_0x0218
        L_0x01f4:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r9)
            goto L_0x0218
        L_0x01f9:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r10)
            goto L_0x0218
        L_0x01fe:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r11)
            goto L_0x0218
        L_0x0203:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r16)
            goto L_0x0218
        L_0x0208:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r5)
            goto L_0x0218
        L_0x020d:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r17)
            goto L_0x0218
        L_0x0212:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r13)
            goto L_0x0218
        L_0x0217:
            r1 = r2
        L_0x0218:
            if (r1 != 0) goto L_0x0234
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r1 = r0.length()
            java.lang.String r3 = "Unknown Dolby Vision level string: "
            if (r1 == 0) goto L_0x022b
            java.lang.String r0 = r3.concat(r0)
            goto L_0x0230
        L_0x022b:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r3)
        L_0x0230:
            com.google.android.exoplayer2.util.Log.c(r12, r0)
            return r2
        L_0x0234:
            android.util.Pair r0 = new android.util.Pair
            r0.<init>(r4, r1)
            return r0
        L_0x023a:
            r3 = r1[r15]
            int r4 = r3.hashCode()
            switch(r4) {
                case 3004662: goto L_0x0280;
                case 3006243: goto L_0x0276;
                case 3006244: goto L_0x026c;
                case 3199032: goto L_0x0262;
                case 3214780: goto L_0x0258;
                case 3356560: goto L_0x024e;
                case 3624515: goto L_0x0244;
                default: goto L_0x0243;
            }
        L_0x0243:
            goto L_0x028a
        L_0x0244:
            java.lang.String r4 = "vp09"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x028a
            r3 = 2
            goto L_0x028b
        L_0x024e:
            java.lang.String r4 = "mp4a"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x028a
            r3 = 6
            goto L_0x028b
        L_0x0258:
            java.lang.String r4 = "hvc1"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x028a
            r3 = 4
            goto L_0x028b
        L_0x0262:
            java.lang.String r4 = "hev1"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x028a
            r3 = 3
            goto L_0x028b
        L_0x026c:
            java.lang.String r4 = "avc2"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x028a
            r3 = 1
            goto L_0x028b
        L_0x0276:
            java.lang.String r4 = "avc1"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x028a
            r3 = 0
            goto L_0x028b
        L_0x0280:
            java.lang.String r4 = "av01"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x028a
            r3 = 5
            goto L_0x028b
        L_0x028a:
            r3 = -1
        L_0x028b:
            switch(r3) {
                case 0: goto L_0x04e5;
                case 1: goto L_0x04e5;
                case 2: goto L_0x04de;
                case 3: goto L_0x029f;
                case 4: goto L_0x029f;
                case 5: goto L_0x0296;
                case 6: goto L_0x028f;
                default: goto L_0x028e;
            }
        L_0x028e:
            return r2
        L_0x028f:
            java.lang.String r0 = r0.i
            android.util.Pair r0 = c(r0, r1)
            return r0
        L_0x0296:
            java.lang.String r2 = r0.i
            com.google.android.exoplayer2.video.ColorInfo r0 = r0.v
            android.util.Pair r0 = a((java.lang.String) r2, (java.lang.String[]) r1, (com.google.android.exoplayer2.video.ColorInfo) r0)
            return r0
        L_0x029f:
            java.lang.String r0 = r0.i
            int r3 = r1.length
            java.lang.String r4 = "Ignoring malformed HEVC codec string: "
            if (r3 >= r5) goto L_0x02be
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r1 = r0.length()
            if (r1 == 0) goto L_0x02b5
            java.lang.String r0 = r4.concat(r0)
            goto L_0x02ba
        L_0x02b5:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r4)
        L_0x02ba:
            com.google.android.exoplayer2.util.Log.c(r12, r0)
            return r2
        L_0x02be:
            java.util.regex.Pattern r3 = a
            r14 = r1[r13]
            java.util.regex.Matcher r3 = r3.matcher(r14)
            boolean r14 = r3.matches()
            if (r14 != 0) goto L_0x02e4
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r1 = r0.length()
            if (r1 == 0) goto L_0x02db
            java.lang.String r0 = r4.concat(r0)
            goto L_0x02e0
        L_0x02db:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r4)
        L_0x02e0:
            com.google.android.exoplayer2.util.Log.c(r12, r0)
            return r2
        L_0x02e4:
            java.lang.String r0 = r3.group(r13)
            java.lang.String r3 = "1"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x02f3
            r0 = 1
        L_0x02f1:
            r3 = 3
            goto L_0x02fd
        L_0x02f3:
            java.lang.String r3 = "2"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x04c4
            r0 = 2
            goto L_0x02f1
        L_0x02fd:
            r1 = r1[r3]
            if (r1 == 0) goto L_0x049d
            int r4 = r1.hashCode()
            switch(r4) {
                case 70821: goto L_0x0425;
                case 70914: goto L_0x041a;
                case 70917: goto L_0x040f;
                case 71007: goto L_0x0404;
                case 71010: goto L_0x03f9;
                case 74665: goto L_0x03ef;
                case 74758: goto L_0x03e5;
                case 74761: goto L_0x03db;
                case 74851: goto L_0x03d1;
                case 74854: goto L_0x03c7;
                case 2193639: goto L_0x03bb;
                case 2193642: goto L_0x03af;
                case 2193732: goto L_0x03a3;
                case 2193735: goto L_0x0397;
                case 2193738: goto L_0x038b;
                case 2193825: goto L_0x037f;
                case 2193828: goto L_0x0373;
                case 2193831: goto L_0x0367;
                case 2312803: goto L_0x035c;
                case 2312806: goto L_0x0351;
                case 2312896: goto L_0x0346;
                case 2312899: goto L_0x033a;
                case 2312902: goto L_0x032e;
                case 2312989: goto L_0x0322;
                case 2312992: goto L_0x0316;
                case 2312995: goto L_0x030a;
                default: goto L_0x0308;
            }
        L_0x0308:
            goto L_0x0430
        L_0x030a:
            java.lang.String r3 = "L186"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0430
            r4 = 12
            goto L_0x0431
        L_0x0316:
            java.lang.String r3 = "L183"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0430
            r4 = 11
            goto L_0x0431
        L_0x0322:
            java.lang.String r3 = "L180"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0430
            r4 = 10
            goto L_0x0431
        L_0x032e:
            java.lang.String r3 = "L156"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0430
            r4 = 9
            goto L_0x0431
        L_0x033a:
            java.lang.String r3 = "L153"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0430
            r4 = 8
            goto L_0x0431
        L_0x0346:
            java.lang.String r3 = "L150"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0430
            r4 = 7
            goto L_0x0431
        L_0x0351:
            java.lang.String r3 = "L123"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0430
            r4 = 6
            goto L_0x0431
        L_0x035c:
            java.lang.String r3 = "L120"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0430
            r4 = 5
            goto L_0x0431
        L_0x0367:
            java.lang.String r3 = "H186"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0430
            r4 = 25
            goto L_0x0431
        L_0x0373:
            java.lang.String r3 = "H183"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0430
            r4 = 24
            goto L_0x0431
        L_0x037f:
            java.lang.String r3 = "H180"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0430
            r4 = 23
            goto L_0x0431
        L_0x038b:
            java.lang.String r3 = "H156"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0430
            r4 = 22
            goto L_0x0431
        L_0x0397:
            java.lang.String r3 = "H153"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0430
            r4 = 21
            goto L_0x0431
        L_0x03a3:
            java.lang.String r3 = "H150"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0430
            r4 = 20
            goto L_0x0431
        L_0x03af:
            java.lang.String r3 = "H123"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0430
            r4 = 19
            goto L_0x0431
        L_0x03bb:
            java.lang.String r3 = "H120"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0430
            r4 = 18
            goto L_0x0431
        L_0x03c7:
            java.lang.String r3 = "L93"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0430
            r4 = 4
            goto L_0x0431
        L_0x03d1:
            java.lang.String r4 = "L90"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x0430
            r4 = 3
            goto L_0x0431
        L_0x03db:
            java.lang.String r3 = "L63"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0430
            r4 = 2
            goto L_0x0431
        L_0x03e5:
            java.lang.String r3 = "L60"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0430
            r4 = 1
            goto L_0x0431
        L_0x03ef:
            java.lang.String r3 = "L30"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0430
            r4 = 0
            goto L_0x0431
        L_0x03f9:
            java.lang.String r3 = "H93"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0430
            r4 = 17
            goto L_0x0431
        L_0x0404:
            java.lang.String r3 = "H90"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0430
            r4 = 16
            goto L_0x0431
        L_0x040f:
            java.lang.String r3 = "H63"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0430
            r4 = 15
            goto L_0x0431
        L_0x041a:
            java.lang.String r3 = "H60"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0430
            r4 = 14
            goto L_0x0431
        L_0x0425:
            java.lang.String r3 = "H30"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0430
            r4 = 13
            goto L_0x0431
        L_0x0430:
            r4 = -1
        L_0x0431:
            switch(r4) {
                case 0: goto L_0x0498;
                case 1: goto L_0x0493;
                case 2: goto L_0x048e;
                case 3: goto L_0x0489;
                case 4: goto L_0x0484;
                case 5: goto L_0x047d;
                case 6: goto L_0x047a;
                case 7: goto L_0x0477;
                case 8: goto L_0x0474;
                case 9: goto L_0x0471;
                case 10: goto L_0x046e;
                case 11: goto L_0x046b;
                case 12: goto L_0x0468;
                case 13: goto L_0x0463;
                case 14: goto L_0x045e;
                case 15: goto L_0x0459;
                case 16: goto L_0x0454;
                case 17: goto L_0x044f;
                case 18: goto L_0x044c;
                case 19: goto L_0x0449;
                case 20: goto L_0x0445;
                case 21: goto L_0x0442;
                case 22: goto L_0x043f;
                case 23: goto L_0x043c;
                case 24: goto L_0x0439;
                case 25: goto L_0x0436;
                default: goto L_0x0434;
            }
        L_0x0434:
            goto L_0x049d
        L_0x0436:
            r3 = 33554432(0x2000000, float:9.403955E-38)
            goto L_0x047f
        L_0x0439:
            r3 = 8388608(0x800000, float:1.17549435E-38)
            goto L_0x047f
        L_0x043c:
            r3 = 2097152(0x200000, float:2.938736E-39)
            goto L_0x047f
        L_0x043f:
            r3 = 524288(0x80000, float:7.34684E-40)
            goto L_0x047f
        L_0x0442:
            r3 = 131072(0x20000, float:1.83671E-40)
            goto L_0x047f
        L_0x0445:
            r3 = 32768(0x8000, float:4.5918E-41)
            goto L_0x047f
        L_0x0449:
            r3 = 8192(0x2000, float:1.14794E-41)
            goto L_0x047f
        L_0x044c:
            r3 = 2048(0x800, float:2.87E-42)
            goto L_0x047f
        L_0x044f:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r6)
            goto L_0x049e
        L_0x0454:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r8)
            goto L_0x049e
        L_0x0459:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r10)
            goto L_0x049e
        L_0x045e:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r16)
            goto L_0x049e
        L_0x0463:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r17)
            goto L_0x049e
        L_0x0468:
            r3 = 16777216(0x1000000, float:2.3509887E-38)
            goto L_0x047f
        L_0x046b:
            r3 = 4194304(0x400000, float:5.877472E-39)
            goto L_0x047f
        L_0x046e:
            r3 = 1048576(0x100000, float:1.469368E-39)
            goto L_0x047f
        L_0x0471:
            r3 = 262144(0x40000, float:3.67342E-40)
            goto L_0x047f
        L_0x0474:
            r3 = 65536(0x10000, float:9.18355E-41)
            goto L_0x047f
        L_0x0477:
            r3 = 16384(0x4000, float:2.2959E-41)
            goto L_0x047f
        L_0x047a:
            r3 = 4096(0x1000, float:5.74E-42)
            goto L_0x047f
        L_0x047d:
            r3 = 1024(0x400, float:1.435E-42)
        L_0x047f:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            goto L_0x049e
        L_0x0484:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r7)
            goto L_0x049e
        L_0x0489:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r9)
            goto L_0x049e
        L_0x048e:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r11)
            goto L_0x049e
        L_0x0493:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r5)
            goto L_0x049e
        L_0x0498:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r13)
            goto L_0x049e
        L_0x049d:
            r3 = r2
        L_0x049e:
            if (r3 != 0) goto L_0x04ba
            java.lang.String r0 = java.lang.String.valueOf(r1)
            int r1 = r0.length()
            java.lang.String r3 = "Unknown HEVC level string: "
            if (r1 == 0) goto L_0x04b1
            java.lang.String r0 = r3.concat(r0)
            goto L_0x04b6
        L_0x04b1:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r3)
        L_0x04b6:
            com.google.android.exoplayer2.util.Log.c(r12, r0)
            return r2
        L_0x04ba:
            android.util.Pair r1 = new android.util.Pair
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1.<init>(r0, r3)
            return r1
        L_0x04c4:
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r1 = r0.length()
            java.lang.String r3 = "Unknown HEVC profile string: "
            if (r1 == 0) goto L_0x04d5
            java.lang.String r0 = r3.concat(r0)
            goto L_0x04da
        L_0x04d5:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r3)
        L_0x04da:
            com.google.android.exoplayer2.util.Log.c(r12, r0)
            return r2
        L_0x04de:
            java.lang.String r0 = r0.i
            android.util.Pair r0 = b(r0, r1)
            return r0
        L_0x04e5:
            java.lang.String r0 = r0.i
            android.util.Pair r0 = a((java.lang.String) r0, (java.lang.String[]) r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.mediacodec.MediaCodecUtil.a(com.google.android.exoplayer2.Format):android.util.Pair");
    }

    private static Pair a(String str, String[] strArr) {
        int i;
        int i2;
        StringBuilder append;
        int i3 = 2;
        if (strArr.length < 2) {
            String valueOf = String.valueOf(str);
            Log.c("MediaCodecUtil", valueOf.length() != 0 ? "Ignoring malformed AVC codec string: ".concat(valueOf) : new String("Ignoring malformed AVC codec string: "));
            return null;
        }
        int i4 = 1;
        try {
            if (strArr[1].length() == 6) {
                i2 = Integer.parseInt(strArr[1].substring(0, 2), 16);
                i = Integer.parseInt(strArr[1].substring(4), 16);
            } else if (strArr.length >= 3) {
                i2 = Integer.parseInt(strArr[1]);
                i = Integer.parseInt(strArr[2]);
            } else {
                String valueOf2 = String.valueOf(str);
                Log.c("MediaCodecUtil", valueOf2.length() != 0 ? "Ignoring malformed AVC codec string: ".concat(valueOf2) : new String("Ignoring malformed AVC codec string: "));
                return null;
            }
            switch (i2) {
                case 66:
                    i3 = 1;
                    break;
                case 77:
                    break;
                case 88:
                    i3 = 4;
                    break;
                case 100:
                    i3 = 8;
                    break;
                case 110:
                    i3 = 16;
                    break;
                case 122:
                    i3 = 32;
                    break;
                case 244:
                    i3 = 64;
                    break;
                default:
                    i3 = -1;
                    break;
            }
            if (i3 == -1) {
                append = new StringBuilder(32).append("Unknown AVC profile: ").append(i2);
            } else {
                switch (i) {
                    case 10:
                        break;
                    case 11:
                        i4 = 4;
                        break;
                    case 12:
                        i4 = 8;
                        break;
                    case 13:
                        i4 = 16;
                        break;
                    case 20:
                        i4 = 32;
                        break;
                    case 21:
                        i4 = 64;
                        break;
                    case 22:
                        i4 = 128;
                        break;
                    case 30:
                        i4 = 256;
                        break;
                    case 31:
                        i4 = 512;
                        break;
                    case 32:
                        i4 = 1024;
                        break;
                    case 40:
                        i4 = 2048;
                        break;
                    case 41:
                        i4 = 4096;
                        break;
                    case 42:
                        i4 = 8192;
                        break;
                    case 50:
                        i4 = 16384;
                        break;
                    case 51:
                        i4 = 32768;
                        break;
                    case 52:
                        i4 = 65536;
                        break;
                    default:
                        i4 = -1;
                        break;
                }
                if (i4 != -1) {
                    return new Pair(Integer.valueOf(i3), Integer.valueOf(i4));
                }
                append = new StringBuilder(30).append("Unknown AVC level: ").append(i);
            }
            Log.c("MediaCodecUtil", append.toString());
            return null;
        } catch (NumberFormatException e) {
            String valueOf3 = String.valueOf(str);
            Log.c("MediaCodecUtil", valueOf3.length() != 0 ? "Ignoring malformed AVC codec string: ".concat(valueOf3) : new String("Ignoring malformed AVC codec string: "));
            return null;
        }
    }

    private static Pair a(String str, String[] strArr, ColorInfo colorInfo) {
        StringBuilder append;
        int i = 4;
        if (strArr.length < 4) {
            String valueOf = String.valueOf(str);
            Log.c("MediaCodecUtil", valueOf.length() != 0 ? "Ignoring malformed AV1 codec string: ".concat(valueOf) : new String("Ignoring malformed AV1 codec string: "));
            return null;
        }
        try {
            int parseInt = Integer.parseInt(strArr[1]);
            int parseInt2 = Integer.parseInt(strArr[2].substring(0, 2));
            int parseInt3 = Integer.parseInt(strArr[3]);
            if (parseInt != 0) {
                append = new StringBuilder(32).append("Unknown AV1 profile: ").append(parseInt);
            } else if (parseInt3 == 8 || parseInt3 == 10) {
                int i2 = parseInt3 == 8 ? 1 : (colorInfo == null || !(colorInfo.d != null || colorInfo.c == 7 || colorInfo.c == 6)) ? 2 : 4096;
                switch (parseInt2) {
                    case 0:
                        i = 1;
                        break;
                    case 1:
                        i = 2;
                        break;
                    case 2:
                        break;
                    case 3:
                        i = 8;
                        break;
                    case 4:
                        i = 16;
                        break;
                    case 5:
                        i = 32;
                        break;
                    case 6:
                        i = 64;
                        break;
                    case 7:
                        i = 128;
                        break;
                    case 8:
                        i = 256;
                        break;
                    case 9:
                        i = 512;
                        break;
                    case 10:
                        i = 1024;
                        break;
                    case 11:
                        i = 2048;
                        break;
                    case 12:
                        i = 4096;
                        break;
                    case 13:
                        i = 8192;
                        break;
                    case 14:
                        i = 16384;
                        break;
                    case 15:
                        i = 32768;
                        break;
                    case 16:
                        i = 65536;
                        break;
                    case 17:
                        i = 131072;
                        break;
                    case 18:
                        i = 262144;
                        break;
                    case 19:
                        i = 524288;
                        break;
                    case 20:
                        i = 1048576;
                        break;
                    case 21:
                        i = 2097152;
                        break;
                    case 22:
                        i = 4194304;
                        break;
                    case 23:
                        i = 8388608;
                        break;
                    default:
                        i = -1;
                        break;
                }
                if (i != -1) {
                    return new Pair(Integer.valueOf(i2), Integer.valueOf(i));
                }
                append = new StringBuilder(30).append("Unknown AV1 level: ").append(parseInt2);
            } else {
                append = new StringBuilder(34).append("Unknown AV1 bit depth: ").append(parseInt3);
            }
            Log.c("MediaCodecUtil", append.toString());
            return null;
        } catch (NumberFormatException e) {
            String valueOf2 = String.valueOf(str);
            Log.c("MediaCodecUtil", valueOf2.length() != 0 ? "Ignoring malformed AV1 codec string: ".concat(valueOf2) : new String("Ignoring malformed AV1 codec string: "));
            return null;
        }
    }

    public static MediaCodecInfo a() {
        return a("audio/raw");
    }

    private static MediaCodecInfo a(String str) {
        List a2 = a(str, false, false);
        if (a2.isEmpty()) {
            return null;
        }
        return (MediaCodecInfo) a2.get(0);
    }

    private static String a(MediaCodecInfo mediaCodecInfo, String str, String str2) {
        for (String str3 : mediaCodecInfo.getSupportedTypes()) {
            if (str3.equalsIgnoreCase(str2)) {
                return str3;
            }
        }
        if (str2.equals("video/dolby-vision")) {
            if ("OMX.MS.HEVCDV.Decoder".equals(str)) {
                return "video/hevcdv";
            }
            if ("OMX.RTK.video.decoder".equals(str) || "OMX.realtek.video.decoder.tunneled".equals(str)) {
                return "video/dv_hevc";
            }
            return null;
        } else if (str2.equals("audio/alac") && "OMX.lge.alac.decoder".equals(str)) {
            return "audio/x-lg-alac";
        } else {
            if (!str2.equals("audio/flac") || !"OMX.lge.flac.decoder".equals(str)) {
                return null;
            }
            return "audio/x-lg-flac";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:172:0x028d A[Catch:{ Exception -> 0x02ec }] */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0291 A[Catch:{ Exception -> 0x02ec }] */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x029d A[Catch:{ Exception -> 0x02ec }] */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x02a1 A[Catch:{ Exception -> 0x02ec }] */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x02c0 A[Catch:{ Exception -> 0x02ec }] */
    /* JADX WARNING: Removed duplicated region for block: B:190:0x02cd A[Catch:{ Exception -> 0x02ec }] */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x02d7 A[ADDED_TO_REGION, Catch:{ Exception -> 0x02ec }] */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x02f9 A[SYNTHETIC, Splitter:B:203:0x02f9] */
    /* JADX WARNING: Removed duplicated region for block: B:217:0x0326 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.ArrayList a(com.google.android.exoplayer2.mediacodec.MediaCodecUtil.CodecKey r17, com.google.android.exoplayer2.mediacodec.MediaCodecUtil.MediaCodecListCompat r18) {
        /*
            r1 = r17
            r2 = r18
            java.lang.String r3 = "secure-playback"
            java.lang.String r4 = "tunneled-playback"
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ Exception -> 0x0369 }
            r6.<init>()     // Catch:{ Exception -> 0x0369 }
            java.lang.String r7 = r1.a     // Catch:{ Exception -> 0x0369 }
            int r8 = r18.a()     // Catch:{ Exception -> 0x0369 }
            boolean r9 = r18.b()     // Catch:{ Exception -> 0x0369 }
            r10 = 0
        L_0x0018:
            if (r10 >= r8) goto L_0x0368
            android.media.MediaCodecInfo r0 = r2.a(r10)     // Catch:{ Exception -> 0x0369 }
            int r11 = com.google.android.exoplayer2.util.Util.a     // Catch:{ Exception -> 0x0369 }
            r12 = 29
            if (r11 < r12) goto L_0x002c
            boolean r11 = r0.isAlias()     // Catch:{ Exception -> 0x0369 }
            if (r11 == 0) goto L_0x002c
            r11 = 1
            goto L_0x002d
        L_0x002c:
            r11 = 0
        L_0x002d:
            if (r11 != 0) goto L_0x0360
            java.lang.String r11 = r0.getName()     // Catch:{ Exception -> 0x0369 }
            boolean r14 = r0.isEncoder()     // Catch:{ Exception -> 0x0369 }
            java.lang.String r15 = ".secure"
            if (r14 != 0) goto L_0x024c
            if (r9 != 0) goto L_0x0045
            boolean r14 = r11.endsWith(r15)     // Catch:{ Exception -> 0x0369 }
            if (r14 == 0) goto L_0x0045
            goto L_0x024c
        L_0x0045:
            int r14 = com.google.android.exoplayer2.util.Util.a     // Catch:{ Exception -> 0x0369 }
            r13 = 21
            if (r14 >= r13) goto L_0x007d
            java.lang.String r13 = "CIPAACDecoder"
            boolean r13 = r13.equals(r11)     // Catch:{ Exception -> 0x0369 }
            if (r13 != 0) goto L_0x024c
            java.lang.String r13 = "CIPMP3Decoder"
            boolean r13 = r13.equals(r11)     // Catch:{ Exception -> 0x0369 }
            if (r13 != 0) goto L_0x024c
            java.lang.String r13 = "CIPVorbisDecoder"
            boolean r13 = r13.equals(r11)     // Catch:{ Exception -> 0x0369 }
            if (r13 != 0) goto L_0x024c
            java.lang.String r13 = "CIPAMRNBDecoder"
            boolean r13 = r13.equals(r11)     // Catch:{ Exception -> 0x0369 }
            if (r13 != 0) goto L_0x024c
            java.lang.String r13 = "AACDecoder"
            boolean r13 = r13.equals(r11)     // Catch:{ Exception -> 0x0369 }
            if (r13 != 0) goto L_0x024c
            java.lang.String r13 = "MP3Decoder"
            boolean r13 = r13.equals(r11)     // Catch:{ Exception -> 0x0369 }
            if (r13 == 0) goto L_0x007d
            goto L_0x024c
        L_0x007d:
            int r13 = com.google.android.exoplayer2.util.Util.a     // Catch:{ Exception -> 0x0369 }
            r14 = 18
            if (r13 >= r14) goto L_0x00ab
            java.lang.String r13 = "OMX.MTK.AUDIO.DECODER.AAC"
            boolean r13 = r13.equals(r11)     // Catch:{ Exception -> 0x0369 }
            if (r13 == 0) goto L_0x00ab
            java.lang.String r13 = "a70"
            java.lang.String r14 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            boolean r13 = r13.equals(r14)     // Catch:{ Exception -> 0x0369 }
            if (r13 != 0) goto L_0x024c
            java.lang.String r13 = "Xiaomi"
            java.lang.String r14 = com.google.android.exoplayer2.util.Util.c     // Catch:{ Exception -> 0x0369 }
            boolean r13 = r13.equals(r14)     // Catch:{ Exception -> 0x0369 }
            if (r13 == 0) goto L_0x00ab
            java.lang.String r13 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            java.lang.String r14 = "HM"
            boolean r13 = r13.startsWith(r14)     // Catch:{ Exception -> 0x0369 }
            if (r13 == 0) goto L_0x00ab
            goto L_0x024c
        L_0x00ab:
            int r13 = com.google.android.exoplayer2.util.Util.a     // Catch:{ Exception -> 0x0369 }
            r14 = 16
            if (r13 != r14) goto L_0x0133
            java.lang.String r13 = "OMX.qcom.audio.decoder.mp3"
            boolean r13 = r13.equals(r11)     // Catch:{ Exception -> 0x0369 }
            if (r13 == 0) goto L_0x0133
            java.lang.String r13 = "dlxu"
            java.lang.String r5 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            boolean r5 = r13.equals(r5)     // Catch:{ Exception -> 0x0369 }
            if (r5 != 0) goto L_0x024c
            java.lang.String r5 = "protou"
            java.lang.String r13 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            boolean r5 = r5.equals(r13)     // Catch:{ Exception -> 0x0369 }
            if (r5 != 0) goto L_0x024c
            java.lang.String r5 = "ville"
            java.lang.String r13 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            boolean r5 = r5.equals(r13)     // Catch:{ Exception -> 0x0369 }
            if (r5 != 0) goto L_0x024c
            java.lang.String r5 = "villeplus"
            java.lang.String r13 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            boolean r5 = r5.equals(r13)     // Catch:{ Exception -> 0x0369 }
            if (r5 != 0) goto L_0x024c
            java.lang.String r5 = "villec2"
            java.lang.String r13 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            boolean r5 = r5.equals(r13)     // Catch:{ Exception -> 0x0369 }
            if (r5 != 0) goto L_0x024c
            java.lang.String r5 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            java.lang.String r13 = "gee"
            boolean r5 = r5.startsWith(r13)     // Catch:{ Exception -> 0x0369 }
            if (r5 != 0) goto L_0x024c
            java.lang.String r5 = "C6602"
            java.lang.String r13 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            boolean r5 = r5.equals(r13)     // Catch:{ Exception -> 0x0369 }
            if (r5 != 0) goto L_0x024c
            java.lang.String r5 = "C6603"
            java.lang.String r13 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            boolean r5 = r5.equals(r13)     // Catch:{ Exception -> 0x0369 }
            if (r5 != 0) goto L_0x024c
            java.lang.String r5 = "C6606"
            java.lang.String r13 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            boolean r5 = r5.equals(r13)     // Catch:{ Exception -> 0x0369 }
            if (r5 != 0) goto L_0x024c
            java.lang.String r5 = "C6616"
            java.lang.String r13 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            boolean r5 = r5.equals(r13)     // Catch:{ Exception -> 0x0369 }
            if (r5 != 0) goto L_0x024c
            java.lang.String r5 = "L36h"
            java.lang.String r13 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            boolean r5 = r5.equals(r13)     // Catch:{ Exception -> 0x0369 }
            if (r5 != 0) goto L_0x024c
            java.lang.String r5 = "SO-02E"
            java.lang.String r13 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            boolean r5 = r5.equals(r13)     // Catch:{ Exception -> 0x0369 }
            if (r5 == 0) goto L_0x0133
            goto L_0x024c
        L_0x0133:
            int r5 = com.google.android.exoplayer2.util.Util.a     // Catch:{ Exception -> 0x0369 }
            if (r5 != r14) goto L_0x0169
            java.lang.String r5 = "OMX.qcom.audio.decoder.aac"
            boolean r5 = r5.equals(r11)     // Catch:{ Exception -> 0x0369 }
            if (r5 == 0) goto L_0x0169
            java.lang.String r5 = "C1504"
            java.lang.String r13 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            boolean r5 = r5.equals(r13)     // Catch:{ Exception -> 0x0369 }
            if (r5 != 0) goto L_0x024c
            java.lang.String r5 = "C1505"
            java.lang.String r13 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            boolean r5 = r5.equals(r13)     // Catch:{ Exception -> 0x0369 }
            if (r5 != 0) goto L_0x024c
            java.lang.String r5 = "C1604"
            java.lang.String r13 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            boolean r5 = r5.equals(r13)     // Catch:{ Exception -> 0x0369 }
            if (r5 != 0) goto L_0x024c
            java.lang.String r5 = "C1605"
            java.lang.String r13 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            boolean r5 = r5.equals(r13)     // Catch:{ Exception -> 0x0369 }
            if (r5 == 0) goto L_0x0169
            goto L_0x024c
        L_0x0169:
            int r5 = com.google.android.exoplayer2.util.Util.a     // Catch:{ Exception -> 0x0369 }
            r13 = 24
            java.lang.String r14 = "samsung"
            if (r5 >= r13) goto L_0x01db
            java.lang.String r5 = "OMX.SEC.aac.dec"
            boolean r5 = r5.equals(r11)     // Catch:{ Exception -> 0x0369 }
            if (r5 != 0) goto L_0x0181
            java.lang.String r5 = "OMX.Exynos.AAC.Decoder"
            boolean r5 = r5.equals(r11)     // Catch:{ Exception -> 0x0369 }
            if (r5 == 0) goto L_0x01db
        L_0x0181:
            java.lang.String r5 = com.google.android.exoplayer2.util.Util.c     // Catch:{ Exception -> 0x0369 }
            boolean r5 = r14.equals(r5)     // Catch:{ Exception -> 0x0369 }
            if (r5 == 0) goto L_0x01db
            java.lang.String r5 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            java.lang.String r13 = "zeroflte"
            boolean r5 = r5.startsWith(r13)     // Catch:{ Exception -> 0x0369 }
            if (r5 != 0) goto L_0x024c
            java.lang.String r5 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            java.lang.String r13 = "zerolte"
            boolean r5 = r5.startsWith(r13)     // Catch:{ Exception -> 0x0369 }
            if (r5 != 0) goto L_0x024c
            java.lang.String r5 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            java.lang.String r13 = "zenlte"
            boolean r5 = r5.startsWith(r13)     // Catch:{ Exception -> 0x0369 }
            if (r5 != 0) goto L_0x024c
            java.lang.String r5 = "SC-05G"
            java.lang.String r13 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            boolean r5 = r5.equals(r13)     // Catch:{ Exception -> 0x0369 }
            if (r5 != 0) goto L_0x024c
            java.lang.String r5 = "marinelteatt"
            java.lang.String r13 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            boolean r5 = r5.equals(r13)     // Catch:{ Exception -> 0x0369 }
            if (r5 != 0) goto L_0x024c
            java.lang.String r5 = "404SC"
            java.lang.String r13 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            boolean r5 = r5.equals(r13)     // Catch:{ Exception -> 0x0369 }
            if (r5 != 0) goto L_0x024c
            java.lang.String r5 = "SC-04G"
            java.lang.String r13 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            boolean r5 = r5.equals(r13)     // Catch:{ Exception -> 0x0369 }
            if (r5 != 0) goto L_0x024c
            java.lang.String r5 = "SCV31"
            java.lang.String r13 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            boolean r5 = r5.equals(r13)     // Catch:{ Exception -> 0x0369 }
            if (r5 == 0) goto L_0x01db
            goto L_0x024c
        L_0x01db:
            int r5 = com.google.android.exoplayer2.util.Util.a     // Catch:{ Exception -> 0x0369 }
            java.lang.String r13 = "jflte"
            r12 = 19
            if (r5 > r12) goto L_0x0224
            java.lang.String r5 = "OMX.SEC.vp8.dec"
            boolean r5 = r5.equals(r11)     // Catch:{ Exception -> 0x0369 }
            if (r5 == 0) goto L_0x0224
            java.lang.String r5 = com.google.android.exoplayer2.util.Util.c     // Catch:{ Exception -> 0x0369 }
            boolean r5 = r14.equals(r5)     // Catch:{ Exception -> 0x0369 }
            if (r5 == 0) goto L_0x0224
            java.lang.String r5 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            java.lang.String r14 = "d2"
            boolean r5 = r5.startsWith(r14)     // Catch:{ Exception -> 0x0369 }
            if (r5 != 0) goto L_0x024c
            java.lang.String r5 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            java.lang.String r14 = "serrano"
            boolean r5 = r5.startsWith(r14)     // Catch:{ Exception -> 0x0369 }
            if (r5 != 0) goto L_0x024c
            java.lang.String r5 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            boolean r5 = r5.startsWith(r13)     // Catch:{ Exception -> 0x0369 }
            if (r5 != 0) goto L_0x024c
            java.lang.String r5 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            java.lang.String r14 = "santos"
            boolean r5 = r5.startsWith(r14)     // Catch:{ Exception -> 0x0369 }
            if (r5 != 0) goto L_0x024c
            java.lang.String r5 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            java.lang.String r14 = "t0"
            boolean r5 = r5.startsWith(r14)     // Catch:{ Exception -> 0x0369 }
            if (r5 == 0) goto L_0x0224
            goto L_0x024c
        L_0x0224:
            int r5 = com.google.android.exoplayer2.util.Util.a     // Catch:{ Exception -> 0x0369 }
            if (r5 > r12) goto L_0x0239
            java.lang.String r5 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0369 }
            boolean r5 = r5.startsWith(r13)     // Catch:{ Exception -> 0x0369 }
            if (r5 == 0) goto L_0x0239
            java.lang.String r5 = "OMX.qcom.video.decoder.vp8"
            boolean r5 = r5.equals(r11)     // Catch:{ Exception -> 0x0369 }
            if (r5 == 0) goto L_0x0239
            goto L_0x024c
        L_0x0239:
            java.lang.String r5 = "audio/eac3-joc"
            boolean r5 = r5.equals(r7)     // Catch:{ Exception -> 0x0369 }
            if (r5 == 0) goto L_0x024a
            java.lang.String r5 = "OMX.MTK.AUDIO.DECODER.DSPAC3"
            boolean r5 = r5.equals(r11)     // Catch:{ Exception -> 0x0369 }
            if (r5 == 0) goto L_0x024a
            goto L_0x024c
        L_0x024a:
            r5 = 1
            goto L_0x024d
        L_0x024c:
            r5 = 0
        L_0x024d:
            if (r5 == 0) goto L_0x0360
            java.lang.String r5 = a((android.media.MediaCodecInfo) r0, (java.lang.String) r11, (java.lang.String) r7)     // Catch:{ Exception -> 0x0369 }
            if (r5 == 0) goto L_0x0360
            android.media.MediaCodecInfo$CodecCapabilities r12 = r0.getCapabilitiesForType(r5)     // Catch:{ Exception -> 0x02ee }
            boolean r13 = r2.a(r4, r5, r12)     // Catch:{ Exception -> 0x02ee }
            boolean r14 = r2.a(r4, r12)     // Catch:{ Exception -> 0x02ee }
            r16 = r4
            boolean r4 = r1.c     // Catch:{ Exception -> 0x02ec }
            if (r4 != 0) goto L_0x0269
            if (r14 != 0) goto L_0x0362
        L_0x0269:
            boolean r4 = r1.c     // Catch:{ Exception -> 0x02ec }
            if (r4 == 0) goto L_0x0271
            if (r13 != 0) goto L_0x0271
            goto L_0x0362
        L_0x0271:
            boolean r4 = r2.a(r3, r5, r12)     // Catch:{ Exception -> 0x02ec }
            boolean r13 = r2.a(r3, r12)     // Catch:{ Exception -> 0x02ec }
            boolean r14 = r1.b     // Catch:{ Exception -> 0x02ec }
            if (r14 != 0) goto L_0x027f
            if (r13 != 0) goto L_0x0362
        L_0x027f:
            boolean r13 = r1.b     // Catch:{ Exception -> 0x02ec }
            if (r13 == 0) goto L_0x0287
            if (r4 != 0) goto L_0x0287
            goto L_0x0362
        L_0x0287:
            int r13 = com.google.android.exoplayer2.util.Util.a     // Catch:{ Exception -> 0x02ec }
            r14 = 29
            if (r13 < r14) goto L_0x0291
            r0.isHardwareAccelerated()     // Catch:{ Exception -> 0x02ec }
            goto L_0x0294
        L_0x0291:
            a((android.media.MediaCodecInfo) r0)     // Catch:{ Exception -> 0x02ec }
        L_0x0294:
            a((android.media.MediaCodecInfo) r0)     // Catch:{ Exception -> 0x02ec }
            int r13 = com.google.android.exoplayer2.util.Util.a     // Catch:{ Exception -> 0x02ec }
            r14 = 29
            if (r13 < r14) goto L_0x02a1
            r0.isVendor()     // Catch:{ Exception -> 0x02ec }
            goto L_0x02be
        L_0x02a1:
            java.lang.String r0 = r0.getName()     // Catch:{ Exception -> 0x02ec }
            java.lang.String r0 = com.dreamers.exoplayercore.repack.C0000a.a((java.lang.String) r0)     // Catch:{ Exception -> 0x02ec }
            java.lang.String r13 = "omx.google."
            boolean r13 = r0.startsWith(r13)     // Catch:{ Exception -> 0x02ec }
            if (r13 != 0) goto L_0x02be
            java.lang.String r13 = "c2.android."
            boolean r13 = r0.startsWith(r13)     // Catch:{ Exception -> 0x02ec }
            if (r13 != 0) goto L_0x02be
            java.lang.String r13 = "c2.google."
            r0.startsWith(r13)     // Catch:{ Exception -> 0x02ec }
        L_0x02be:
            if (r9 == 0) goto L_0x02c7
            boolean r0 = r1.b     // Catch:{ Exception -> 0x02ec }
            if (r0 == r4) goto L_0x02c5
            goto L_0x02c7
        L_0x02c5:
            r4 = 0
            goto L_0x02ce
        L_0x02c7:
            if (r9 != 0) goto L_0x02d7
            boolean r0 = r1.b     // Catch:{ Exception -> 0x02ec }
            if (r0 != 0) goto L_0x02d7
            goto L_0x02c5
        L_0x02ce:
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r0 = com.google.android.exoplayer2.mediacodec.MediaCodecInfo.a(r11, r7, r5, r12, r4)     // Catch:{ Exception -> 0x02ec }
            r6.add(r0)     // Catch:{ Exception -> 0x02ec }
            goto L_0x0362
        L_0x02d7:
            if (r9 != 0) goto L_0x0362
            if (r4 == 0) goto L_0x0362
            java.lang.String r0 = java.lang.String.valueOf(r11)     // Catch:{ Exception -> 0x02ec }
            java.lang.String r0 = r0.concat(r15)     // Catch:{ Exception -> 0x02ec }
            r4 = 1
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r0 = com.google.android.exoplayer2.mediacodec.MediaCodecInfo.a(r0, r7, r5, r12, r4)     // Catch:{ Exception -> 0x02ec }
            r6.add(r0)     // Catch:{ Exception -> 0x02ec }
            return r6
        L_0x02ec:
            r0 = move-exception
            goto L_0x02f1
        L_0x02ee:
            r0 = move-exception
            r16 = r4
        L_0x02f1:
            int r4 = com.google.android.exoplayer2.util.Util.a     // Catch:{ Exception -> 0x0369 }
            r12 = 23
            java.lang.String r13 = "MediaCodecUtil"
            if (r4 > r12) goto L_0x0326
            boolean r4 = r6.isEmpty()     // Catch:{ Exception -> 0x0369 }
            if (r4 != 0) goto L_0x0326
            java.lang.String r0 = java.lang.String.valueOf(r11)     // Catch:{ Exception -> 0x0369 }
            int r0 = r0.length()     // Catch:{ Exception -> 0x0369 }
            int r0 = r0 + 46
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0369 }
            r4.<init>(r0)     // Catch:{ Exception -> 0x0369 }
            java.lang.String r0 = "Skipping codec "
            java.lang.StringBuilder r0 = r4.append(r0)     // Catch:{ Exception -> 0x0369 }
            java.lang.StringBuilder r0 = r0.append(r11)     // Catch:{ Exception -> 0x0369 }
            java.lang.String r4 = " (failed to query capabilities)"
            java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ Exception -> 0x0369 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0369 }
            com.google.android.exoplayer2.util.Log.d(r13, r0)     // Catch:{ Exception -> 0x0369 }
            goto L_0x0362
        L_0x0326:
            java.lang.String r1 = java.lang.String.valueOf(r11)     // Catch:{ Exception -> 0x0369 }
            int r1 = r1.length()     // Catch:{ Exception -> 0x0369 }
            int r1 = r1 + 25
            java.lang.String r2 = java.lang.String.valueOf(r5)     // Catch:{ Exception -> 0x0369 }
            int r2 = r2.length()     // Catch:{ Exception -> 0x0369 }
            int r1 = r1 + r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0369 }
            r2.<init>(r1)     // Catch:{ Exception -> 0x0369 }
            java.lang.String r1 = "Failed to query codec "
            java.lang.StringBuilder r1 = r2.append(r1)     // Catch:{ Exception -> 0x0369 }
            java.lang.StringBuilder r1 = r1.append(r11)     // Catch:{ Exception -> 0x0369 }
            java.lang.String r2 = " ("
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Exception -> 0x0369 }
            java.lang.StringBuilder r1 = r1.append(r5)     // Catch:{ Exception -> 0x0369 }
            java.lang.String r2 = ")"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Exception -> 0x0369 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0369 }
            com.google.android.exoplayer2.util.Log.d(r13, r1)     // Catch:{ Exception -> 0x0369 }
            throw r0     // Catch:{ Exception -> 0x0369 }
        L_0x0360:
            r16 = r4
        L_0x0362:
            int r10 = r10 + 1
            r4 = r16
            goto L_0x0018
        L_0x0368:
            return r6
        L_0x0369:
            r0 = move-exception
            com.google.android.exoplayer2.mediacodec.MediaCodecUtil$DecoderQueryException r1 = new com.google.android.exoplayer2.mediacodec.MediaCodecUtil$DecoderQueryException
            r2 = 0
            r1.<init>(r0, r2)
            goto L_0x0372
        L_0x0371:
            throw r1
        L_0x0372:
            goto L_0x0371
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.mediacodec.MediaCodecUtil.a(com.google.android.exoplayer2.mediacodec.MediaCodecUtil$CodecKey, com.google.android.exoplayer2.mediacodec.MediaCodecUtil$MediaCodecListCompat):java.util.ArrayList");
    }

    public static synchronized List a(String str, boolean z, boolean z2) {
        synchronized (MediaCodecUtil.class) {
            CodecKey codecKey = new CodecKey(str, z, z2);
            HashMap hashMap = b;
            List list = (List) hashMap.get(codecKey);
            if (list != null) {
                return list;
            }
            ArrayList a2 = a(codecKey, Util.a >= 21 ? new MediaCodecListCompatV21(z, z2) : new MediaCodecListCompatV16((byte) 0));
            if (z && a2.isEmpty() && 21 <= Util.a && Util.a <= 23) {
                a2 = a(codecKey, (MediaCodecListCompat) new MediaCodecListCompatV16((byte) 0));
                if (!a2.isEmpty()) {
                    String str2 = ((MediaCodecInfo) a2.get(0)).a;
                    Log.c("MediaCodecUtil", new StringBuilder(String.valueOf(str).length() + 63 + String.valueOf(str2).length()).append("MediaCodecList API didn't list secure decoder for: ").append(str).append(". Assuming: ").append(str2).toString());
                }
            }
            if ("audio/raw".equals(str)) {
                if (Util.a < 26 && Util.b.equals("R9") && a2.size() == 1 && ((MediaCodecInfo) a2.get(0)).a.equals("OMX.MTK.AUDIO.DECODER.RAW")) {
                    a2.add(MediaCodecInfo.a("OMX.google.raw.decoder", "audio/raw", "audio/raw", (MediaCodecInfo.CodecCapabilities) null, false));
                }
                a((List) a2, MediaCodecUtil$$Lambda$1.a);
            }
            if (Util.a < 21 && a2.size() > 1) {
                String str3 = ((MediaCodecInfo) a2.get(0)).a;
                if ("OMX.SEC.mp3.dec".equals(str3) || "OMX.SEC.MP3.Decoder".equals(str3) || "OMX.brcm.audio.mp3.decoder".equals(str3)) {
                    a((List) a2, MediaCodecUtil$$Lambda$2.a);
                }
            }
            if (Util.a < 30 && a2.size() > 1 && "OMX.qti.audio.decoder.flac".equals(((MediaCodecInfo) a2.get(0)).a)) {
                a2.add((MediaCodecInfo) a2.remove(0));
            }
            List unmodifiableList = Collections.unmodifiableList(a2);
            hashMap.put(codecKey, unmodifiableList);
            return unmodifiableList;
        }
    }

    public static List a(List list, Format format) {
        ArrayList arrayList = new ArrayList(list);
        a((List) arrayList, (ScoreProvider) new MediaCodecUtil$$Lambda$0(format));
        return arrayList;
    }

    private static void a(List list, ScoreProvider scoreProvider) {
        Collections.sort(list, new MediaCodecUtil$$Lambda$3(scoreProvider));
    }

    private static boolean a(MediaCodecInfo mediaCodecInfo) {
        if (Util.a >= 29) {
            return mediaCodecInfo.isSoftwareOnly();
        }
        String a2 = C0000a.a(mediaCodecInfo.getName());
        if (a2.startsWith("arc.")) {
            return false;
        }
        if (a2.startsWith("omx.google.") || a2.startsWith("omx.ffmpeg.")) {
            return true;
        }
        if ((!a2.startsWith("omx.sec.") || !a2.contains(".sw.")) && !a2.equals("omx.qcom.video.decoder.hevcswvdec") && !a2.startsWith("c2.android.") && !a2.startsWith("c2.google.")) {
            return !a2.startsWith("omx.") && !a2.startsWith("c2.");
        }
        return true;
    }

    public static int b() {
        int i;
        if (c == -1) {
            MediaCodecInfo a2 = a("video/avc");
            int i2 = 0;
            if (a2 != null) {
                MediaCodecInfo.CodecProfileLevel[] a3 = a2.a();
                int length = a3.length;
                int i3 = 0;
                while (i2 < length) {
                    switch (a3[i2].level) {
                        case 1:
                        case 2:
                            i = 25344;
                            break;
                        case 8:
                        case 16:
                        case 32:
                            i = 101376;
                            break;
                        case 64:
                            i = 202752;
                            break;
                        case 128:
                        case 256:
                            i = 414720;
                            break;
                        case 512:
                            i = 921600;
                            break;
                        case 1024:
                            i = 1310720;
                            break;
                        case 2048:
                        case 4096:
                            i = 2097152;
                            break;
                        case 8192:
                            i = 2228224;
                            break;
                        case 16384:
                            i = 5652480;
                            break;
                        case 32768:
                        case 65536:
                            i = 9437184;
                            break;
                        case 131072:
                        case 262144:
                        case 524288:
                            i = 35651584;
                            break;
                        default:
                            i = -1;
                            break;
                    }
                    i3 = Math.max(i, i3);
                    i2++;
                }
                i2 = Math.max(i3, Util.a >= 21 ? 345600 : 172800);
            }
            c = i2;
        }
        return c;
    }

    static final /* synthetic */ int b(MediaCodecInfo mediaCodecInfo) {
        String str = mediaCodecInfo.a;
        if (str.startsWith("OMX.google") || str.startsWith("c2.android")) {
            return 1;
        }
        return (Util.a >= 26 || !str.equals("OMX.MTK.AUDIO.DECODER.RAW")) ? 0 : -1;
    }

    private static Pair b(String str, String[] strArr) {
        int i;
        StringBuilder append;
        if (strArr.length < 3) {
            String valueOf = String.valueOf(str);
            Log.c("MediaCodecUtil", valueOf.length() != 0 ? "Ignoring malformed VP9 codec string: ".concat(valueOf) : new String("Ignoring malformed VP9 codec string: "));
            return null;
        }
        int i2 = 1;
        try {
            int parseInt = Integer.parseInt(strArr[1]);
            int parseInt2 = Integer.parseInt(strArr[2]);
            switch (parseInt) {
                case 0:
                    i = 1;
                    break;
                case 1:
                    i = 2;
                    break;
                case 2:
                    i = 4;
                    break;
                case 3:
                    i = 8;
                    break;
                default:
                    i = -1;
                    break;
            }
            if (i == -1) {
                append = new StringBuilder(32).append("Unknown VP9 profile: ").append(parseInt);
            } else {
                switch (parseInt2) {
                    case 10:
                        break;
                    case 11:
                        i2 = 2;
                        break;
                    case 20:
                        i2 = 4;
                        break;
                    case 21:
                        i2 = 8;
                        break;
                    case 30:
                        i2 = 16;
                        break;
                    case 31:
                        i2 = 32;
                        break;
                    case 40:
                        i2 = 64;
                        break;
                    case 41:
                        i2 = 128;
                        break;
                    case 50:
                        i2 = 256;
                        break;
                    case 51:
                        i2 = 512;
                        break;
                    case 60:
                        i2 = 2048;
                        break;
                    case 61:
                        i2 = 4096;
                        break;
                    case 62:
                        i2 = 8192;
                        break;
                    default:
                        i2 = -1;
                        break;
                }
                if (i2 != -1) {
                    return new Pair(Integer.valueOf(i), Integer.valueOf(i2));
                }
                append = new StringBuilder(30).append("Unknown VP9 level: ").append(parseInt2);
            }
            Log.c("MediaCodecUtil", append.toString());
            return null;
        } catch (NumberFormatException e) {
            String valueOf2 = String.valueOf(str);
            Log.c("MediaCodecUtil", valueOf2.length() != 0 ? "Ignoring malformed VP9 codec string: ".concat(valueOf2) : new String("Ignoring malformed VP9 codec string: "));
            return null;
        }
    }

    private static Pair c(String str, String[] strArr) {
        int i = 3;
        if (strArr.length != 3) {
            String valueOf = String.valueOf(str);
            Log.c("MediaCodecUtil", valueOf.length() != 0 ? "Ignoring malformed MP4A codec string: ".concat(valueOf) : new String("Ignoring malformed MP4A codec string: "));
            return null;
        }
        try {
            if ("audio/mp4a-latm".equals(MimeTypes.a(Integer.parseInt(strArr[1], 16)))) {
                switch (Integer.parseInt(strArr[2])) {
                    case 1:
                        i = 1;
                        break;
                    case 2:
                        i = 2;
                        break;
                    case 3:
                        break;
                    case 4:
                        i = 4;
                        break;
                    case 5:
                        i = 5;
                        break;
                    case 6:
                        i = 6;
                        break;
                    case 17:
                        i = 17;
                        break;
                    case 20:
                        i = 20;
                        break;
                    case 23:
                        i = 23;
                        break;
                    case 29:
                        i = 29;
                        break;
                    case 39:
                        i = 39;
                        break;
                    case 42:
                        i = 42;
                        break;
                    default:
                        i = -1;
                        break;
                }
                if (i != -1) {
                    return new Pair(Integer.valueOf(i), 0);
                }
            }
        } catch (NumberFormatException e) {
            String valueOf2 = String.valueOf(str);
            Log.c("MediaCodecUtil", valueOf2.length() != 0 ? "Ignoring malformed MP4A codec string: ".concat(valueOf2) : new String("Ignoring malformed MP4A codec string: "));
        }
        return null;
    }
}
