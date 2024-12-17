package com.google.android.exoplayer2.mediacodec;

import android.graphics.Point;
import android.media.MediaCodecInfo;
import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.decoder.DecoderReuseEvaluation;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;

public final class MediaCodecInfo {
    public final String a;
    public final String b;
    public final String c;
    public final MediaCodecInfo.CodecCapabilities d;
    public final boolean e;
    private boolean f;
    private final boolean g;

    private MediaCodecInfo(String str, String str2, String str3, MediaCodecInfo.CodecCapabilities codecCapabilities, boolean z, boolean z2) {
        this.a = (String) Assertions.b((Object) str);
        this.b = str2;
        this.c = str3;
        this.d = codecCapabilities;
        this.f = z;
        this.e = z2;
        this.g = MimeTypes.b(str2);
    }

    private static int a(String str, String str2, int i) {
        if (i > 1 || ((Util.a >= 26 && i > 0) || "audio/mpeg".equals(str2) || "audio/3gpp".equals(str2) || "audio/amr-wb".equals(str2) || "audio/mp4a-latm".equals(str2) || "audio/vorbis".equals(str2) || "audio/opus".equals(str2) || "audio/raw".equals(str2) || "audio/flac".equals(str2) || "audio/g711-alaw".equals(str2) || "audio/g711-mlaw".equals(str2) || "audio/gsm".equals(str2))) {
            return i;
        }
        int i2 = "audio/ac3".equals(str2) ? 6 : "audio/eac3".equals(str2) ? 16 : 30;
        Log.c("MediaCodecInfo", new StringBuilder(String.valueOf(str).length() + 59).append("AssumedMaxChannelAdjustment: ").append(str).append(", [").append(i).append(" to ").append(i2).append("]").toString());
        return i2;
    }

    public static Point a(MediaCodecInfo.VideoCapabilities videoCapabilities, int i, int i2) {
        int widthAlignment = videoCapabilities.getWidthAlignment();
        int heightAlignment = videoCapabilities.getHeightAlignment();
        return new Point(Util.a(i, widthAlignment) * widthAlignment, Util.a(i2, heightAlignment) * heightAlignment);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x006b, code lost:
        if ((com.google.android.exoplayer2.util.Util.a >= 21 && r11.isFeatureSupported("secure-playback")) != false) goto L_0x0070;
     */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x005a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.exoplayer2.mediacodec.MediaCodecInfo a(java.lang.String r8, java.lang.String r9, java.lang.String r10, android.media.MediaCodecInfo.CodecCapabilities r11, boolean r12) {
        /*
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r7 = new com.google.android.exoplayer2.mediacodec.MediaCodecInfo
            r0 = 1
            r1 = 0
            if (r11 == 0) goto L_0x004a
            int r2 = com.google.android.exoplayer2.util.Util.a
            r3 = 19
            if (r2 < r3) goto L_0x0016
            java.lang.String r2 = "adaptive-playback"
            boolean r2 = r11.isFeatureSupported(r2)
            if (r2 == 0) goto L_0x0016
            r2 = 1
            goto L_0x0017
        L_0x0016:
            r2 = 0
        L_0x0017:
            if (r2 == 0) goto L_0x004a
            int r2 = com.google.android.exoplayer2.util.Util.a
            r3 = 22
            if (r2 > r3) goto L_0x0045
            java.lang.String r2 = "ODROID-XU3"
            java.lang.String r3 = com.google.android.exoplayer2.util.Util.d
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0033
            java.lang.String r2 = "Nexus 10"
            java.lang.String r3 = com.google.android.exoplayer2.util.Util.d
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0045
        L_0x0033:
            java.lang.String r2 = "OMX.Exynos.AVC.Decoder"
            boolean r2 = r2.equals(r8)
            if (r2 != 0) goto L_0x0043
            java.lang.String r2 = "OMX.Exynos.AVC.Decoder.secure"
            boolean r2 = r2.equals(r8)
            if (r2 == 0) goto L_0x0045
        L_0x0043:
            r2 = 1
            goto L_0x0046
        L_0x0045:
            r2 = 0
        L_0x0046:
            if (r2 != 0) goto L_0x004a
            r5 = 1
            goto L_0x004b
        L_0x004a:
            r5 = 0
        L_0x004b:
            r2 = 21
            if (r11 == 0) goto L_0x0058
            int r3 = com.google.android.exoplayer2.util.Util.a
            if (r3 < r2) goto L_0x0058
            java.lang.String r3 = "tunneled-playback"
            r11.isFeatureSupported(r3)
        L_0x0058:
            if (r12 != 0) goto L_0x0070
            if (r11 == 0) goto L_0x006e
            int r12 = com.google.android.exoplayer2.util.Util.a
            if (r12 < r2) goto L_0x006a
            java.lang.String r12 = "secure-playback"
            boolean r12 = r11.isFeatureSupported(r12)
            if (r12 == 0) goto L_0x006a
            r12 = 1
            goto L_0x006b
        L_0x006a:
            r12 = 0
        L_0x006b:
            if (r12 == 0) goto L_0x006e
            goto L_0x0070
        L_0x006e:
            r6 = 0
            goto L_0x0071
        L_0x0070:
            r6 = 1
        L_0x0071:
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r11
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.mediacodec.MediaCodecInfo.a(java.lang.String, java.lang.String, java.lang.String, android.media.MediaCodecInfo$CodecCapabilities, boolean):com.google.android.exoplayer2.mediacodec.MediaCodecInfo");
    }

    private void a(String str) {
        String str2 = this.a;
        String str3 = this.b;
        String str4 = Util.e;
        Log.a("MediaCodecInfo", new StringBuilder(String.valueOf(str).length() + 20 + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(str4).length()).append("NoSupport [").append(str).append("] [").append(str2).append(", ").append(str3).append("] [").append(str4).append("]").toString());
    }

    private boolean a(int i) {
        String sb;
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.d;
        if (codecCapabilities == null) {
            sb = "sampleRate.caps";
        } else {
            MediaCodecInfo.AudioCapabilities audioCapabilities = codecCapabilities.getAudioCapabilities();
            if (audioCapabilities == null) {
                sb = "sampleRate.aCaps";
            } else if (audioCapabilities.isSampleRateSupported(i)) {
                return true;
            } else {
                sb = new StringBuilder(31).append("sampleRate.support, ").append(i).toString();
            }
        }
        a(sb);
        return false;
    }

    private static boolean a(MediaCodecInfo.VideoCapabilities videoCapabilities, int i, int i2, double d2) {
        Point a2 = a(videoCapabilities, i, i2);
        int i3 = a2.x;
        int i4 = a2.y;
        return (d2 == -1.0d || d2 < 1.0d) ? videoCapabilities.isSizeSupported(i3, i4) : videoCapabilities.areSizeAndRateSupported(i3, i4, Math.floor(d2));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r3 = r3.getVideoCapabilities();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.media.MediaCodecInfo.CodecProfileLevel[] a(android.media.MediaCodecInfo.CodecCapabilities r3) {
        /*
            r0 = 0
            if (r3 == 0) goto L_0x0018
            android.media.MediaCodecInfo$VideoCapabilities r3 = r3.getVideoCapabilities()
            if (r3 == 0) goto L_0x0018
            android.util.Range r3 = r3.getBitrateRange()
            java.lang.Comparable r3 = r3.getUpper()
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            goto L_0x0019
        L_0x0018:
            r3 = 0
        L_0x0019:
            r1 = 180000000(0xaba9500, float:1.7967196E-32)
            r2 = 1
            if (r3 < r1) goto L_0x0022
            r3 = 1024(0x400, float:1.435E-42)
            goto L_0x0069
        L_0x0022:
            r1 = 120000000(0x7270e00, float:1.2567798E-34)
            if (r3 < r1) goto L_0x002a
            r3 = 512(0x200, float:7.175E-43)
            goto L_0x0069
        L_0x002a:
            r1 = 60000000(0x3938700, float:8.670878E-37)
            if (r3 < r1) goto L_0x0032
            r3 = 256(0x100, float:3.59E-43)
            goto L_0x0069
        L_0x0032:
            r1 = 30000000(0x1c9c380, float:7.411627E-38)
            if (r3 < r1) goto L_0x003a
            r3 = 128(0x80, float:1.794E-43)
            goto L_0x0069
        L_0x003a:
            r1 = 18000000(0x112a880, float:2.6936858E-38)
            if (r3 < r1) goto L_0x0042
            r3 = 64
            goto L_0x0069
        L_0x0042:
            r1 = 12000000(0xb71b00, float:1.6815582E-38)
            if (r3 < r1) goto L_0x004a
            r3 = 32
            goto L_0x0069
        L_0x004a:
            r1 = 7200000(0x6ddd00, float:1.0089349E-38)
            if (r3 < r1) goto L_0x0052
            r3 = 16
            goto L_0x0069
        L_0x0052:
            r1 = 3600000(0x36ee80, float:5.044674E-39)
            if (r3 < r1) goto L_0x005a
            r3 = 8
            goto L_0x0069
        L_0x005a:
            r1 = 1800000(0x1b7740, float:2.522337E-39)
            if (r3 < r1) goto L_0x0061
            r3 = 4
            goto L_0x0069
        L_0x0061:
            r1 = 800000(0xc3500, float:1.121039E-39)
            if (r3 < r1) goto L_0x0068
            r3 = 2
            goto L_0x0069
        L_0x0068:
            r3 = 1
        L_0x0069:
            android.media.MediaCodecInfo$CodecProfileLevel r1 = new android.media.MediaCodecInfo$CodecProfileLevel
            r1.<init>()
            r1.profile = r2
            r1.level = r3
            android.media.MediaCodecInfo$CodecProfileLevel[] r3 = new android.media.MediaCodecInfo.CodecProfileLevel[r2]
            r3[r0] = r1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.mediacodec.MediaCodecInfo.a(android.media.MediaCodecInfo$CodecCapabilities):android.media.MediaCodecInfo$CodecProfileLevel[]");
    }

    private void b(String str) {
        String str2 = this.a;
        String str3 = this.b;
        String str4 = Util.e;
        Log.a("MediaCodecInfo", new StringBuilder(String.valueOf(str).length() + 25 + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(str4).length()).append("AssumedSupport [").append(str).append("] [").append(str2).append(", ").append(str3).append("] [").append(str4).append("]").toString());
    }

    private boolean b(int i) {
        String sb;
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.d;
        if (codecCapabilities == null) {
            sb = "channelCount.caps";
        } else {
            MediaCodecInfo.AudioCapabilities audioCapabilities = codecCapabilities.getAudioCapabilities();
            if (audioCapabilities == null) {
                sb = "channelCount.aCaps";
            } else if (a(this.a, this.b, audioCapabilities.getMaxInputChannelCount()) >= i) {
                return true;
            } else {
                sb = new StringBuilder(33).append("channelCount.support, ").append(i).toString();
            }
        }
        a(sb);
        return false;
    }

    private boolean c(Format format) {
        String g2;
        String str;
        StringBuilder sb;
        String str2;
        if (format.i == null || this.b == null || (g2 = MimeTypes.g(format.i)) == null) {
            return true;
        }
        if (!this.b.equals(g2)) {
            str = format.i;
            sb = new StringBuilder(String.valueOf(str).length() + 13 + String.valueOf(g2).length());
            str2 = "codec.mime ";
        } else {
            Pair a2 = MediaCodecUtil.a(format);
            if (a2 == null) {
                return true;
            }
            int intValue = ((Integer) a2.first).intValue();
            int intValue2 = ((Integer) a2.second).intValue();
            if (!this.g && intValue != 42) {
                return true;
            }
            MediaCodecInfo.CodecProfileLevel[] a3 = a();
            if (Util.a <= 23 && "video/x-vnd.on2.vp9".equals(this.b) && a3.length == 0) {
                a3 = a(this.d);
            }
            for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : a3) {
                if (codecProfileLevel.profile == intValue && codecProfileLevel.level >= intValue2) {
                    return true;
                }
            }
            str = format.i;
            sb = new StringBuilder(String.valueOf(str).length() + 22 + String.valueOf(g2).length());
            str2 = "codec.profileLevel, ";
        }
        a(sb.append(str2).append(str).append(", ").append(g2).toString());
        return false;
    }

    private static boolean c(String str) {
        return Util.d.startsWith("SM-T230") && "OMX.MARVELL.VIDEO.HW.CODA7542DECODER".equals(str);
    }

    private static final boolean d(String str) {
        return !"OMX.MTK.VIDEO.DECODER.HEVC".equals(str) || !"mcv5a".equals(Util.b);
    }

    public final DecoderReuseEvaluation a(Format format, Format format2) {
        int i = !Util.a((Object) format.l, (Object) format2.l) ? 8 : 0;
        if (this.g) {
            if (format.r != format2.r) {
                i |= 1024;
            }
            if (!this.f && !(format.width == format2.width && format.height == format2.height)) {
                i |= 512;
            }
            if (!Util.a((Object) format.v, (Object) format2.v)) {
                i |= 2048;
            }
            if (c(this.a) && !format.b(format2)) {
                i |= 2;
            }
            if (i == 0) {
                return new DecoderReuseEvaluation(this.a, format, format2, format.b(format2) ? 3 : 2, 0);
            }
        } else {
            if (format.w != format2.w) {
                i |= 4096;
            }
            if (format.x != format2.x) {
                i |= 8192;
            }
            if (format.y != format2.y) {
                i |= 16384;
            }
            if (i == 0 && "audio/mp4a-latm".equals(this.b)) {
                Pair a2 = MediaCodecUtil.a(format);
                Pair a3 = MediaCodecUtil.a(format2);
                if (!(a2 == null || a3 == null)) {
                    int intValue = ((Integer) a2.first).intValue();
                    int intValue2 = ((Integer) a3.first).intValue();
                    if (intValue == 42 && intValue2 == 42) {
                        return new DecoderReuseEvaluation(this.a, format, format2, 3, 0);
                    }
                }
            }
            if (!format.b(format2)) {
                i |= 32;
            }
            if ("audio/opus".equals(this.b)) {
                i |= 2;
            }
            if (i == 0) {
                return new DecoderReuseEvaluation(this.a, format, format2, 1, 0);
            }
        }
        return new DecoderReuseEvaluation(this.a, format, format2, 0, i);
    }

    public final boolean a(int i, int i2, double d2) {
        String sb;
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.d;
        if (codecCapabilities == null) {
            sb = "sizeAndRate.caps";
        } else {
            MediaCodecInfo.VideoCapabilities videoCapabilities = codecCapabilities.getVideoCapabilities();
            if (videoCapabilities == null) {
                sb = "sizeAndRate.vCaps";
            } else if (a(videoCapabilities, i, i2, d2)) {
                return true;
            } else {
                if (i >= i2 || !d(this.a) || !a(videoCapabilities, i2, i, d2)) {
                    sb = new StringBuilder(69).append("sizeAndRate.support, ").append(i).append("x").append(i2).append("x").append(d2).toString();
                } else {
                    b(new StringBuilder(69).append("sizeAndRate.rotated, ").append(i).append("x").append(i2).append("x").append(d2).toString());
                    return true;
                }
            }
        }
        a(sb);
        return false;
    }

    public final boolean a(Format format) {
        boolean z = false;
        if (!c(format)) {
            return false;
        }
        if (!this.g) {
            return Util.a < 21 || ((format.x == -1 || a(format.x)) && (format.w == -1 || b(format.w)));
        }
        if (format.width <= 0 || format.height <= 0) {
            return true;
        }
        if (Util.a >= 21) {
            return a(format.width, format.height, (double) format.q);
        }
        if (format.width * format.height <= MediaCodecUtil.b()) {
            z = true;
        }
        if (!z) {
            a(new StringBuilder(40).append("legacyFrameSize, ").append(format.width).append("x").append(format.height).toString());
        }
        return z;
    }

    public final MediaCodecInfo.CodecProfileLevel[] a() {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.d;
        return (codecCapabilities == null || codecCapabilities.profileLevels == null) ? new MediaCodecInfo.CodecProfileLevel[0] : this.d.profileLevels;
    }

    public final boolean b(Format format) {
        if (this.g) {
            return this.f;
        }
        Pair a2 = MediaCodecUtil.a(format);
        return a2 != null && ((Integer) a2.first).intValue() == 42;
    }

    public final String toString() {
        return this.a;
    }
}
