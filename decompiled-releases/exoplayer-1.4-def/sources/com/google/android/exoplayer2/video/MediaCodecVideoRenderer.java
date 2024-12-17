package com.google.android.exoplayer2.video;

import android.content.Context;
import android.graphics.Point;
import android.media.MediaCodecInfo;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Pair;
import android.view.Surface;
import androidx.core.location.LocationRequestCompat;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.RendererCapabilities$$CC;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.decoder.DecoderReuseEvaluation;
import com.google.android.exoplayer2.mediacodec.MediaCodecAdapter;
import com.google.android.exoplayer2.mediacodec.MediaCodecDecoderException;
import com.google.android.exoplayer2.mediacodec.MediaCodecInfo;
import com.google.android.exoplayer2.mediacodec.MediaCodecRenderer;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MediaFormatUtil;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.TraceUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoFrameReleaseHelper;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public class MediaCodecVideoRenderer extends MediaCodecRenderer {
    private static final int[] c = {1920, 1600, 1440, 1280, 960, 854, 640, 540, 480};
    private static boolean d;
    private static boolean e;
    private long A;
    private long B;
    private long C;
    private int D;
    private int E;
    private int F;
    private int G;
    private float H;
    private VideoSize I;
    private boolean J;
    private int K;
    private VideoFrameMetadataListener L;
    OnFrameRenderedListenerV23 a;
    private final Context f;
    private final VideoFrameReleaseHelper g;
    private final VideoRendererEventListener.EventDispatcher h;
    private final long i;
    private final boolean j;
    private CodecMaxValues k;
    private boolean l;
    private boolean m;
    private Surface n;
    private DummySurface o;
    private boolean p;
    private int q;
    private boolean r;
    private boolean s;
    private boolean t;
    private long u;
    private long v;
    private long w;
    private int x;
    private int y;
    private int z;

    public final class CodecMaxValues {
        public final int a;
        public final int b;
        public final int c;

        public CodecMaxValues(int i, int i2, int i3) {
            this.a = i;
            this.b = i2;
            this.c = i3;
        }
    }

    final class OnFrameRenderedListenerV23 implements Handler.Callback, MediaCodecAdapter.OnFrameRenderedListener {
        private final Handler a;

        public OnFrameRenderedListenerV23(MediaCodecAdapter mediaCodecAdapter) {
            Handler a2 = Util.a((Handler.Callback) this);
            this.a = a2;
            mediaCodecAdapter.a((MediaCodecAdapter.OnFrameRenderedListener) this, a2);
        }

        private void b(long j) {
            if (this == MediaCodecVideoRenderer.this.a) {
                if (j == LocationRequestCompat.PASSIVE_INTERVAL) {
                    MediaCodecVideoRenderer.this.L();
                    return;
                }
                try {
                    MediaCodecVideoRenderer.this.e(j);
                } catch (ExoPlaybackException e) {
                    MediaCodecVideoRenderer.this.a(e);
                }
            }
        }

        public final void a(long j) {
            if (Util.a < 30) {
                this.a.sendMessageAtFrontOfQueue(Message.obtain(this.a, 0, (int) (j >> 32), (int) j));
                return;
            }
            b(j);
        }

        public final boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    b(Util.b(message.arg1, message.arg2));
                    return true;
                default:
                    return false;
            }
        }
    }

    private MediaCodecVideoRenderer(Context context, MediaCodecAdapter.Factory factory, MediaCodecSelector mediaCodecSelector, long j2, Handler handler, VideoRendererEventListener videoRendererEventListener) {
        super(2, factory, mediaCodecSelector, false, 30.0f);
        this.i = j2;
        Context applicationContext = context.getApplicationContext();
        this.f = applicationContext;
        this.g = new VideoFrameReleaseHelper(applicationContext);
        this.h = new VideoRendererEventListener.EventDispatcher(handler, videoRendererEventListener);
        this.j = "NVIDIA".equals(Util.c);
        this.v = -9223372036854775807L;
        this.E = -1;
        this.F = -1;
        this.H = -1.0f;
        this.q = 1;
        this.K = 0;
        this.I = null;
    }

    public MediaCodecVideoRenderer(Context context, MediaCodecSelector mediaCodecSelector, long j2, Handler handler, VideoRendererEventListener videoRendererEventListener) {
        this(context, MediaCodecAdapter.Factory.a, mediaCodecSelector, j2, handler, videoRendererEventListener);
    }

    private void N() {
        this.v = this.i > 0 ? SystemClock.elapsedRealtime() + this.i : -9223372036854775807L;
    }

    private void O() {
        MediaCodecAdapter E2;
        this.r = false;
        if (Util.a >= 23 && this.J && (E2 = E()) != null) {
            this.a = new OnFrameRenderedListenerV23(E2);
        }
    }

    private void P() {
        this.t = true;
        if (!this.r) {
            this.r = true;
            this.h.a((Object) this.n);
            this.p = true;
        }
    }

    private void Q() {
        if (this.E != -1 || this.F != -1) {
            VideoSize videoSize = this.I;
            if (videoSize == null || videoSize.b != this.E || this.I.c != this.F || this.I.d != this.G || this.I.e != this.H) {
                VideoSize videoSize2 = new VideoSize(this.E, this.F, this.G, this.H);
                this.I = videoSize2;
                this.h.a(videoSize2);
            }
        }
    }

    private void R() {
        VideoSize videoSize = this.I;
        if (videoSize != null) {
            this.h.a(videoSize);
        }
    }

    private void S() {
        if (this.x > 0) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.h.a(this.x, elapsedRealtime - this.w);
            this.x = 0;
            this.w = elapsedRealtime;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int a(com.google.android.exoplayer2.mediacodec.MediaCodecInfo r5, java.lang.String r6, int r7, int r8) {
        /*
            r0 = -1
            if (r7 == r0) goto L_0x00a8
            if (r8 != r0) goto L_0x0007
            goto L_0x00a8
        L_0x0007:
            int r1 = r6.hashCode()
            r2 = 3
            r3 = 4
            r4 = 2
            switch(r1) {
                case -1851077871: goto L_0x004e;
                case -1664118616: goto L_0x0044;
                case -1662541442: goto L_0x003a;
                case 1187890754: goto L_0x0030;
                case 1331836730: goto L_0x0026;
                case 1599127256: goto L_0x001c;
                case 1599127257: goto L_0x0012;
                default: goto L_0x0011;
            }
        L_0x0011:
            goto L_0x0058
        L_0x0012:
            java.lang.String r1 = "video/x-vnd.on2.vp9"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0058
            r6 = 6
            goto L_0x0059
        L_0x001c:
            java.lang.String r1 = "video/x-vnd.on2.vp8"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0058
            r6 = 4
            goto L_0x0059
        L_0x0026:
            java.lang.String r1 = "video/avc"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0058
            r6 = 3
            goto L_0x0059
        L_0x0030:
            java.lang.String r1 = "video/mp4v-es"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0058
            r6 = 1
            goto L_0x0059
        L_0x003a:
            java.lang.String r1 = "video/hevc"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0058
            r6 = 5
            goto L_0x0059
        L_0x0044:
            java.lang.String r1 = "video/3gpp"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0058
            r6 = 0
            goto L_0x0059
        L_0x004e:
            java.lang.String r1 = "video/dolby-vision"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0058
            r6 = 2
            goto L_0x0059
        L_0x0058:
            r6 = -1
        L_0x0059:
            switch(r6) {
                case 0: goto L_0x009f;
                case 1: goto L_0x009f;
                case 2: goto L_0x0060;
                case 3: goto L_0x0060;
                case 4: goto L_0x009f;
                case 5: goto L_0x005d;
                case 6: goto L_0x005d;
                default: goto L_0x005c;
            }
        L_0x005c:
            return r0
        L_0x005d:
            int r7 = r7 * r8
            goto L_0x00a2
        L_0x0060:
            java.lang.String r6 = "BRAVIA 4K 2015"
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.d
            boolean r6 = r6.equals(r1)
            if (r6 != 0) goto L_0x009e
            java.lang.String r6 = "Amazon"
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.c
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x008d
            java.lang.String r6 = "KFSOWI"
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.d
            boolean r6 = r6.equals(r1)
            if (r6 != 0) goto L_0x009e
            java.lang.String r6 = "AFTS"
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.d
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x008d
            boolean r5 = r5.e
            if (r5 == 0) goto L_0x008d
            goto L_0x009e
        L_0x008d:
            r5 = 16
            int r6 = com.google.android.exoplayer2.util.Util.a((int) r7, (int) r5)
            int r5 = com.google.android.exoplayer2.util.Util.a((int) r8, (int) r5)
            int r6 = r6 * r5
            int r5 = r6 << 4
            int r7 = r5 << 4
            goto L_0x00a1
        L_0x009e:
            return r0
        L_0x009f:
            int r7 = r7 * r8
        L_0x00a1:
            r3 = 2
        L_0x00a2:
            int r7 = r7 * 3
            int r3 = r3 * 2
            int r7 = r7 / r3
            return r7
        L_0x00a8:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.video.MediaCodecVideoRenderer.a(com.google.android.exoplayer2.mediacodec.MediaCodecInfo, java.lang.String, int, int):int");
    }

    private static Point a(MediaCodecInfo mediaCodecInfo, Format format) {
        MediaCodecInfo.VideoCapabilities videoCapabilities;
        int i2 = 0;
        boolean z2 = format.height > format.width;
        int i3 = z2 ? format.height : format.width;
        int i4 = z2 ? format.width : format.height;
        float f2 = ((float) i4) / ((float) i3);
        int[] iArr = c;
        while (true) {
            Point point = null;
            if (i2 >= 9) {
                break;
            }
            int i5 = iArr[i2];
            int i6 = (int) (((float) i5) * f2);
            if (i5 <= i3 || i6 <= i4) {
                break;
            }
            if (Util.a >= 21) {
                int i7 = z2 ? i6 : i5;
                if (!z2) {
                    i5 = i6;
                }
                if (!(mediaCodecInfo.d == null || (videoCapabilities = mediaCodecInfo.d.getVideoCapabilities()) == null)) {
                    point = com.google.android.exoplayer2.mediacodec.MediaCodecInfo.a(videoCapabilities, i7, i5);
                }
                if (mediaCodecInfo.a(point.x, point.y, (double) format.q)) {
                    return point;
                }
            } else {
                try {
                    int a2 = Util.a(i5, 16) << 4;
                    int a3 = Util.a(i6, 16) << 4;
                    if (a2 * a3 <= MediaCodecUtil.b()) {
                        int i8 = z2 ? a3 : a2;
                        if (!z2) {
                            a2 = a3;
                        }
                        return new Point(i8, a2);
                    }
                } catch (MediaCodecUtil.DecoderQueryException e2) {
                }
            }
            i2++;
        }
        return null;
    }

    private static List a(MediaCodecSelector mediaCodecSelector, Format format, boolean z2, boolean z3) {
        Pair a2;
        String str;
        String str2 = format.l;
        if (str2 == null) {
            return Collections.emptyList();
        }
        List a3 = MediaCodecUtil.a(mediaCodecSelector.a(str2, z2, z3), format);
        if ("video/dolby-vision".equals(str2) && (a2 = MediaCodecUtil.a(format)) != null) {
            int intValue = ((Integer) a2.first).intValue();
            if (intValue == 16 || intValue == 256) {
                str = "video/hevc";
            } else if (intValue == 512) {
                str = "video/avc";
            }
            a3.addAll(mediaCodecSelector.a(str, z2, z3));
        }
        return Collections.unmodifiableList(a3);
    }

    private void a(long j2, long j3, Format format) {
        VideoFrameMetadataListener videoFrameMetadataListener = this.L;
        if (videoFrameMetadataListener != null) {
            videoFrameMetadataListener.a(j2, j3, format, F());
        }
    }

    private void a(MediaCodecAdapter mediaCodecAdapter, int i2) {
        TraceUtil.a("skipVideoBuffer");
        mediaCodecAdapter.a(i2, false);
        TraceUtil.a();
        this.b.f++;
    }

    private void a(MediaCodecAdapter mediaCodecAdapter, int i2, long j2) {
        Q();
        TraceUtil.a("releaseOutputBuffer");
        mediaCodecAdapter.a(i2, j2);
        TraceUtil.a();
        this.B = SystemClock.elapsedRealtime() * 1000;
        this.b.e++;
        this.y = 0;
        P();
    }

    private static int b(com.google.android.exoplayer2.mediacodec.MediaCodecInfo mediaCodecInfo, Format format) {
        if (format.m == -1) {
            return a(mediaCodecInfo, format.l, format.width, format.height);
        }
        int size = format.n.size();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += ((byte[]) format.n.get(i3)).length;
        }
        return format.m + i2;
    }

    private void b(int i2) {
        this.b.g += i2;
        this.x += i2;
        this.y += i2;
        this.b.h = Math.max(this.y, this.b.h);
        if (this.x >= 50) {
            S();
        }
    }

    private void b(MediaCodecAdapter mediaCodecAdapter, int i2) {
        Q();
        TraceUtil.a("releaseOutputBuffer");
        mediaCodecAdapter.a(i2, true);
        TraceUtil.a();
        this.B = SystemClock.elapsedRealtime() * 1000;
        this.b.e++;
        this.y = 0;
        P();
    }

    private boolean b(com.google.android.exoplayer2.mediacodec.MediaCodecInfo mediaCodecInfo) {
        if (Util.a < 23 || this.J || b(mediaCodecInfo.a)) {
            return false;
        }
        return !mediaCodecInfo.e || DummySurface.a(this.f);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x006e, code lost:
        r1 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x006f, code lost:
        switch(r1) {
            case 0: goto L_0x0073;
            case 1: goto L_0x0073;
            case 2: goto L_0x0073;
            case 3: goto L_0x0073;
            case 4: goto L_0x0073;
            case 5: goto L_0x0073;
            case 6: goto L_0x0073;
            default: goto L_0x0072;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:468:0x0716, code lost:
        r2 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:469:0x0717, code lost:
        switch(r2) {
            case 0: goto L_0x0073;
            case 1: goto L_0x0073;
            case 2: goto L_0x0073;
            case 3: goto L_0x0073;
            case 4: goto L_0x0073;
            case 5: goto L_0x0073;
            case 6: goto L_0x0073;
            case 7: goto L_0x0073;
            case 8: goto L_0x0073;
            case 9: goto L_0x0073;
            case 10: goto L_0x0073;
            case 11: goto L_0x0073;
            case 12: goto L_0x0073;
            case 13: goto L_0x0073;
            case 14: goto L_0x0073;
            case 15: goto L_0x0073;
            case 16: goto L_0x0073;
            case 17: goto L_0x0073;
            case 18: goto L_0x0073;
            case 19: goto L_0x0073;
            case 20: goto L_0x0073;
            case 21: goto L_0x0073;
            case 22: goto L_0x0073;
            case 23: goto L_0x0073;
            case 24: goto L_0x0073;
            case 25: goto L_0x0073;
            case 26: goto L_0x0073;
            case 27: goto L_0x0073;
            case 28: goto L_0x0073;
            case 29: goto L_0x0073;
            case 30: goto L_0x0073;
            case 31: goto L_0x0073;
            case 32: goto L_0x0073;
            case 33: goto L_0x0073;
            case 34: goto L_0x0073;
            case 35: goto L_0x0073;
            case 36: goto L_0x0073;
            case 37: goto L_0x0073;
            case 38: goto L_0x0073;
            case 39: goto L_0x0073;
            case 40: goto L_0x0073;
            case 41: goto L_0x0073;
            case 42: goto L_0x0073;
            case 43: goto L_0x0073;
            case 44: goto L_0x0073;
            case 45: goto L_0x0073;
            case 46: goto L_0x0073;
            case 47: goto L_0x0073;
            case 48: goto L_0x0073;
            case 49: goto L_0x0073;
            case 50: goto L_0x0073;
            case 51: goto L_0x0073;
            case 52: goto L_0x0073;
            case 53: goto L_0x0073;
            case 54: goto L_0x0073;
            case 55: goto L_0x0073;
            case 56: goto L_0x0073;
            case 57: goto L_0x0073;
            case 58: goto L_0x0073;
            case 59: goto L_0x0073;
            case 60: goto L_0x0073;
            case 61: goto L_0x0073;
            case 62: goto L_0x0073;
            case 63: goto L_0x0073;
            case 64: goto L_0x0073;
            case 65: goto L_0x0073;
            case 66: goto L_0x0073;
            case 67: goto L_0x0073;
            case 68: goto L_0x0073;
            case 69: goto L_0x0073;
            case 70: goto L_0x0073;
            case 71: goto L_0x0073;
            case 72: goto L_0x0073;
            case 73: goto L_0x0073;
            case 74: goto L_0x0073;
            case 75: goto L_0x0073;
            case 76: goto L_0x0073;
            case 77: goto L_0x0073;
            case 78: goto L_0x0073;
            case 79: goto L_0x0073;
            case 80: goto L_0x0073;
            case 81: goto L_0x0073;
            case 82: goto L_0x0073;
            case 83: goto L_0x0073;
            case 84: goto L_0x0073;
            case 85: goto L_0x0073;
            case 86: goto L_0x0073;
            case 87: goto L_0x0073;
            case 88: goto L_0x0073;
            case 89: goto L_0x0073;
            case 90: goto L_0x0073;
            case 91: goto L_0x0073;
            case 92: goto L_0x0073;
            case 93: goto L_0x0073;
            case 94: goto L_0x0073;
            case 95: goto L_0x0073;
            case 96: goto L_0x0073;
            case 97: goto L_0x0073;
            case 98: goto L_0x0073;
            case 99: goto L_0x0073;
            case 100: goto L_0x0073;
            case 101: goto L_0x0073;
            case 102: goto L_0x0073;
            case 103: goto L_0x0073;
            case 104: goto L_0x0073;
            case 105: goto L_0x0073;
            case 106: goto L_0x0073;
            case 107: goto L_0x0073;
            case 108: goto L_0x0073;
            case 109: goto L_0x0073;
            case 110: goto L_0x0073;
            case 111: goto L_0x0073;
            case 112: goto L_0x0073;
            case 113: goto L_0x0073;
            case 114: goto L_0x0073;
            case 115: goto L_0x0073;
            case 116: goto L_0x0073;
            case 117: goto L_0x0073;
            case 118: goto L_0x0073;
            case 119: goto L_0x0073;
            case 120: goto L_0x0073;
            case 121: goto L_0x0073;
            case 122: goto L_0x0073;
            case 123: goto L_0x0073;
            case 124: goto L_0x0073;
            case 125: goto L_0x0073;
            case 126: goto L_0x0073;
            case 127: goto L_0x0073;
            case 128: goto L_0x0073;
            case 129: goto L_0x0073;
            case 130: goto L_0x0073;
            case 131: goto L_0x0073;
            case 132: goto L_0x0073;
            case 133: goto L_0x0073;
            case 134: goto L_0x0073;
            case 135: goto L_0x0073;
            case 136: goto L_0x0073;
            case 137: goto L_0x0073;
            case 138: goto L_0x0073;
            case 139: goto L_0x0073;
            default: goto L_0x071a;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:470:0x071a, code lost:
        r1 = com.google.android.exoplayer2.util.Util.d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:471:0x0720, code lost:
        switch(r1.hashCode()) {
            case -594534941: goto L_0x0738;
            case 2006354: goto L_0x072e;
            case 2006367: goto L_0x0724;
            default: goto L_0x0723;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:474:0x072a, code lost:
        if (r1.equals("AFTN") == false) goto L_0x0741;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:475:0x072c, code lost:
        r7 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:477:0x0734, code lost:
        if (r1.equals("AFTA") == false) goto L_0x0741;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:478:0x0736, code lost:
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:480:0x073e, code lost:
        if (r1.equals("JSN-L21") == false) goto L_0x0741;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:482:0x0741, code lost:
        r7 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:483:0x0742, code lost:
        switch(r7) {
            case 0: goto L_0x0073;
            case 1: goto L_0x0073;
            case 2: goto L_0x0073;
            default: goto L_0x0745;
        };
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean b(java.lang.String r13) {
        /*
            java.lang.String r0 = "OMX.google"
            boolean r13 = r13.startsWith(r0)
            r0 = 0
            if (r13 == 0) goto L_0x000a
            return r0
        L_0x000a:
            java.lang.Class<com.google.android.exoplayer2.video.MediaCodecVideoRenderer> r13 = com.google.android.exoplayer2.video.MediaCodecVideoRenderer.class
            monitor-enter(r13)
            boolean r1 = d     // Catch:{ all -> 0x074d }
            if (r1 != 0) goto L_0x0749
            int r1 = com.google.android.exoplayer2.util.Util.a     // Catch:{ all -> 0x074d }
            r2 = 4
            r3 = 5
            r4 = 3
            r5 = 6
            r6 = 28
            r7 = 2
            r8 = -1
            r9 = 1
            if (r1 > r6) goto L_0x0076
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.b     // Catch:{ all -> 0x074d }
            int r10 = r1.hashCode()     // Catch:{ all -> 0x074d }
            switch(r10) {
                case -1339091551: goto L_0x0064;
                case -1220081023: goto L_0x005a;
                case -1220066608: goto L_0x0050;
                case -1012436106: goto L_0x0046;
                case -64886864: goto L_0x003c;
                case 3415681: goto L_0x0032;
                case 825323514: goto L_0x0028;
                default: goto L_0x0027;
            }     // Catch:{ all -> 0x074d }
        L_0x0027:
            goto L_0x006e
        L_0x0028:
            java.lang.String r10 = "machuca"
            boolean r1 = r1.equals(r10)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x006e
            r1 = 4
            goto L_0x006f
        L_0x0032:
            java.lang.String r10 = "once"
            boolean r1 = r1.equals(r10)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x006e
            r1 = 5
            goto L_0x006f
        L_0x003c:
            java.lang.String r10 = "magnolia"
            boolean r1 = r1.equals(r10)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x006e
            r1 = 3
            goto L_0x006f
        L_0x0046:
            java.lang.String r10 = "oneday"
            boolean r1 = r1.equals(r10)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x006e
            r1 = 6
            goto L_0x006f
        L_0x0050:
            java.lang.String r10 = "dangalUHD"
            boolean r1 = r1.equals(r10)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x006e
            r1 = 1
            goto L_0x006f
        L_0x005a:
            java.lang.String r10 = "dangalFHD"
            boolean r1 = r1.equals(r10)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x006e
            r1 = 2
            goto L_0x006f
        L_0x0064:
            java.lang.String r10 = "dangal"
            boolean r1 = r1.equals(r10)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x006e
            r1 = 0
            goto L_0x006f
        L_0x006e:
            r1 = -1
        L_0x006f:
            switch(r1) {
                case 0: goto L_0x0073;
                case 1: goto L_0x0073;
                case 2: goto L_0x0073;
                case 3: goto L_0x0073;
                case 4: goto L_0x0073;
                case 5: goto L_0x0073;
                case 6: goto L_0x0073;
                default: goto L_0x0072;
            }     // Catch:{ all -> 0x074d }
        L_0x0072:
            goto L_0x0076
        L_0x0073:
            r0 = 1
            goto L_0x0745
        L_0x0076:
            int r1 = com.google.android.exoplayer2.util.Util.a     // Catch:{ all -> 0x074d }
            r10 = 27
            if (r1 > r10) goto L_0x0087
            java.lang.String r1 = "HWEML"
            java.lang.String r11 = com.google.android.exoplayer2.util.Util.b     // Catch:{ all -> 0x074d }
            boolean r1 = r1.equals(r11)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0087
            goto L_0x0073
        L_0x0087:
            int r1 = com.google.android.exoplayer2.util.Util.a     // Catch:{ all -> 0x074d }
            r11 = 26
            if (r1 > r11) goto L_0x0745
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.b     // Catch:{ all -> 0x074d }
            int r12 = r1.hashCode()     // Catch:{ all -> 0x074d }
            switch(r12) {
                case -2144781245: goto L_0x070b;
                case -2144781185: goto L_0x0700;
                case -2144781160: goto L_0x06f5;
                case -2097309513: goto L_0x06ea;
                case -2022874474: goto L_0x06df;
                case -1978993182: goto L_0x06d4;
                case -1978990237: goto L_0x06c9;
                case -1936688988: goto L_0x06be;
                case -1936688066: goto L_0x06b3;
                case -1936688065: goto L_0x06a7;
                case -1931988508: goto L_0x069b;
                case -1885099851: goto L_0x068f;
                case -1696512866: goto L_0x0683;
                case -1680025915: goto L_0x0677;
                case -1615810839: goto L_0x066b;
                case -1600724499: goto L_0x065f;
                case -1554255044: goto L_0x0653;
                case -1481772737: goto L_0x0647;
                case -1481772730: goto L_0x063b;
                case -1481772729: goto L_0x062f;
                case -1320080169: goto L_0x0623;
                case -1217592143: goto L_0x0617;
                case -1180384755: goto L_0x060b;
                case -1139198265: goto L_0x05ff;
                case -1052835013: goto L_0x05f3;
                case -993250464: goto L_0x05e8;
                case -993250458: goto L_0x05dd;
                case -965403638: goto L_0x05d1;
                case -958336948: goto L_0x05c5;
                case -879245230: goto L_0x05b9;
                case -842500323: goto L_0x05ad;
                case -821392978: goto L_0x05a1;
                case -797483286: goto L_0x0595;
                case -794946968: goto L_0x0589;
                case -788334647: goto L_0x057d;
                case -782144577: goto L_0x0571;
                case -575125681: goto L_0x0565;
                case -521118391: goto L_0x0559;
                case -430914369: goto L_0x054d;
                case -290434366: goto L_0x0541;
                case -282781963: goto L_0x0535;
                case -277133239: goto L_0x0529;
                case -173639913: goto L_0x051d;
                case -56598463: goto L_0x0511;
                case 2126: goto L_0x0505;
                case 2564: goto L_0x04f9;
                case 2715: goto L_0x04ed;
                case 2719: goto L_0x04e1;
                case 3091: goto L_0x04d5;
                case 3483: goto L_0x04c9;
                case 73405: goto L_0x04bd;
                case 75537: goto L_0x04b1;
                case 75739: goto L_0x04a5;
                case 76779: goto L_0x0499;
                case 78669: goto L_0x048d;
                case 79305: goto L_0x0481;
                case 80618: goto L_0x0475;
                case 88274: goto L_0x0469;
                case 98846: goto L_0x045d;
                case 98848: goto L_0x0451;
                case 99329: goto L_0x0445;
                case 101481: goto L_0x0439;
                case 1513190: goto L_0x042e;
                case 1514184: goto L_0x0423;
                case 1514185: goto L_0x0418;
                case 2133089: goto L_0x040c;
                case 2133091: goto L_0x0400;
                case 2133120: goto L_0x03f4;
                case 2133151: goto L_0x03e8;
                case 2133182: goto L_0x03dc;
                case 2133184: goto L_0x03d0;
                case 2436959: goto L_0x03c4;
                case 2463773: goto L_0x03b8;
                case 2464648: goto L_0x03ac;
                case 2689555: goto L_0x03a0;
                case 3154429: goto L_0x0394;
                case 3284551: goto L_0x0388;
                case 3351335: goto L_0x037c;
                case 3386211: goto L_0x0370;
                case 41325051: goto L_0x0364;
                case 51349633: goto L_0x0359;
                case 51350594: goto L_0x034f;
                case 55178625: goto L_0x0343;
                case 61542055: goto L_0x0338;
                case 65355429: goto L_0x032c;
                case 66214468: goto L_0x0320;
                case 66214470: goto L_0x0314;
                case 66214473: goto L_0x0308;
                case 66215429: goto L_0x02fc;
                case 66215431: goto L_0x02f0;
                case 66215433: goto L_0x02e4;
                case 66216390: goto L_0x02d8;
                case 76402249: goto L_0x02cc;
                case 76404105: goto L_0x02c0;
                case 76404911: goto L_0x02b4;
                case 80963634: goto L_0x02a8;
                case 82882791: goto L_0x029c;
                case 98715550: goto L_0x0290;
                case 101370885: goto L_0x0284;
                case 102844228: goto L_0x0278;
                case 165221241: goto L_0x026c;
                case 182191441: goto L_0x0260;
                case 245388979: goto L_0x0254;
                case 287431619: goto L_0x0248;
                case 307593612: goto L_0x023c;
                case 308517133: goto L_0x0230;
                case 316215098: goto L_0x0224;
                case 316215116: goto L_0x0218;
                case 316246811: goto L_0x020c;
                case 316246818: goto L_0x0200;
                case 407160593: goto L_0x01f4;
                case 507412548: goto L_0x01e8;
                case 793982701: goto L_0x01dc;
                case 794038622: goto L_0x01d0;
                case 794040393: goto L_0x01c4;
                case 835649806: goto L_0x01b8;
                case 917340916: goto L_0x01ac;
                case 958008161: goto L_0x01a0;
                case 1060579533: goto L_0x0194;
                case 1150207623: goto L_0x0188;
                case 1176899427: goto L_0x017c;
                case 1280332038: goto L_0x0170;
                case 1306947716: goto L_0x0164;
                case 1349174697: goto L_0x0158;
                case 1522194893: goto L_0x014c;
                case 1691543273: goto L_0x0140;
                case 1691544261: goto L_0x0134;
                case 1709443163: goto L_0x0128;
                case 1865889110: goto L_0x011c;
                case 1906253259: goto L_0x0110;
                case 1977196784: goto L_0x0104;
                case 2006372676: goto L_0x00f8;
                case 2019281702: goto L_0x00ec;
                case 2029784656: goto L_0x00e0;
                case 2030379515: goto L_0x00d4;
                case 2033393791: goto L_0x00c8;
                case 2047190025: goto L_0x00bc;
                case 2047252157: goto L_0x00b0;
                case 2048319463: goto L_0x00a4;
                case 2048855701: goto L_0x0098;
                default: goto L_0x0096;
            }     // Catch:{ all -> 0x074d }
        L_0x0096:
            goto L_0x0716
        L_0x0098:
            java.lang.String r2 = "HWWAS-H"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 66
            goto L_0x0717
        L_0x00a4:
            java.lang.String r2 = "HWVNS-H"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 65
            goto L_0x0717
        L_0x00b0:
            java.lang.String r2 = "ELUGA_Prim"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 33
            goto L_0x0717
        L_0x00bc:
            java.lang.String r2 = "ELUGA_Note"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 32
            goto L_0x0717
        L_0x00c8:
            java.lang.String r2 = "ASUS_X00AD_2"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 14
            goto L_0x0717
        L_0x00d4:
            java.lang.String r2 = "HWCAM-H"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 64
            goto L_0x0717
        L_0x00e0:
            java.lang.String r2 = "HWBLN-H"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 63
            goto L_0x0717
        L_0x00ec:
            java.lang.String r2 = "DM-01K"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 29
            goto L_0x0717
        L_0x00f8:
            java.lang.String r2 = "BRAVIA_ATV3_4K"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 19
            goto L_0x0717
        L_0x0104:
            java.lang.String r2 = "Infinix-X572"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 69
            goto L_0x0717
        L_0x0110:
            java.lang.String r2 = "PB2-670M"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 100
            goto L_0x0717
        L_0x011c:
            java.lang.String r2 = "santoni"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 117(0x75, float:1.64E-43)
            goto L_0x0717
        L_0x0128:
            java.lang.String r2 = "iball8735_9806"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 68
            goto L_0x0717
        L_0x0134:
            java.lang.String r2 = "CPH1715"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 24
            goto L_0x0717
        L_0x0140:
            java.lang.String r2 = "CPH1609"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 23
            goto L_0x0717
        L_0x014c:
            java.lang.String r2 = "woods_f"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 133(0x85, float:1.86E-43)
            goto L_0x0717
        L_0x0158:
            java.lang.String r2 = "htc_e56ml_dtul"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 61
            goto L_0x0717
        L_0x0164:
            java.lang.String r2 = "EverStar_S"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 35
            goto L_0x0717
        L_0x0170:
            java.lang.String r2 = "hwALE-H"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 62
            goto L_0x0717
        L_0x017c:
            java.lang.String r2 = "itel_S41"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 71
            goto L_0x0717
        L_0x0188:
            java.lang.String r2 = "LS-5017"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 78
            goto L_0x0717
        L_0x0194:
            java.lang.String r2 = "panell_d"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 96
            goto L_0x0717
        L_0x01a0:
            java.lang.String r2 = "j2xlteins"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 72
            goto L_0x0717
        L_0x01ac:
            java.lang.String r2 = "A7000plus"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 10
            goto L_0x0717
        L_0x01b8:
            java.lang.String r2 = "manning"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 81
            goto L_0x0717
        L_0x01c4:
            java.lang.String r2 = "GIONEE_WBL7519"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 59
            goto L_0x0717
        L_0x01d0:
            java.lang.String r2 = "GIONEE_WBL7365"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 58
            goto L_0x0717
        L_0x01dc:
            java.lang.String r2 = "GIONEE_WBL5708"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 57
            goto L_0x0717
        L_0x01e8:
            java.lang.String r2 = "QM16XE_U"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 114(0x72, float:1.6E-43)
            goto L_0x0717
        L_0x01f4:
            java.lang.String r2 = "Pixi5-10_4G"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 106(0x6a, float:1.49E-43)
            goto L_0x0717
        L_0x0200:
            java.lang.String r2 = "TB3-850M"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 125(0x7d, float:1.75E-43)
            goto L_0x0717
        L_0x020c:
            java.lang.String r2 = "TB3-850F"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 124(0x7c, float:1.74E-43)
            goto L_0x0717
        L_0x0218:
            java.lang.String r2 = "TB3-730X"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 123(0x7b, float:1.72E-43)
            goto L_0x0717
        L_0x0224:
            java.lang.String r2 = "TB3-730F"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 122(0x7a, float:1.71E-43)
            goto L_0x0717
        L_0x0230:
            java.lang.String r2 = "A7020a48"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 12
            goto L_0x0717
        L_0x023c:
            java.lang.String r2 = "A7010a48"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 11
            goto L_0x0717
        L_0x0248:
            java.lang.String r2 = "griffin"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 60
            goto L_0x0717
        L_0x0254:
            java.lang.String r2 = "marino_f"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 82
            goto L_0x0717
        L_0x0260:
            java.lang.String r2 = "CPY83_I00"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 25
            goto L_0x0717
        L_0x026c:
            java.lang.String r2 = "A2016a40"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 8
            goto L_0x0717
        L_0x0278:
            java.lang.String r2 = "le_x6"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 77
            goto L_0x0717
        L_0x0284:
            java.lang.String r2 = "l5460"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 76
            goto L_0x0717
        L_0x0290:
            java.lang.String r2 = "i9031"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 67
            goto L_0x0717
        L_0x029c:
            java.lang.String r2 = "X3_HK"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 135(0x87, float:1.89E-43)
            goto L_0x0717
        L_0x02a8:
            java.lang.String r2 = "V23GB"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 128(0x80, float:1.794E-43)
            goto L_0x0717
        L_0x02b4:
            java.lang.String r2 = "Q4310"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 112(0x70, float:1.57E-43)
            goto L_0x0717
        L_0x02c0:
            java.lang.String r2 = "Q4260"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 110(0x6e, float:1.54E-43)
            goto L_0x0717
        L_0x02cc:
            java.lang.String r2 = "PRO7S"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 108(0x6c, float:1.51E-43)
            goto L_0x0717
        L_0x02d8:
            java.lang.String r2 = "F3311"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 48
            goto L_0x0717
        L_0x02e4:
            java.lang.String r2 = "F3215"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 47
            goto L_0x0717
        L_0x02f0:
            java.lang.String r2 = "F3213"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 46
            goto L_0x0717
        L_0x02fc:
            java.lang.String r2 = "F3211"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 45
            goto L_0x0717
        L_0x0308:
            java.lang.String r2 = "F3116"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 44
            goto L_0x0717
        L_0x0314:
            java.lang.String r2 = "F3113"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 43
            goto L_0x0717
        L_0x0320:
            java.lang.String r2 = "F3111"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 42
            goto L_0x0717
        L_0x032c:
            java.lang.String r2 = "E5643"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 30
            goto L_0x0717
        L_0x0338:
            java.lang.String r2 = "A1601"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 7
            goto L_0x0717
        L_0x0343:
            java.lang.String r2 = "Aura_Note_2"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 15
            goto L_0x0717
        L_0x034f:
            java.lang.String r3 = "602LV"
            boolean r1 = r1.equals(r3)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            goto L_0x0717
        L_0x0359:
            java.lang.String r2 = "601LV"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 3
            goto L_0x0717
        L_0x0364:
            java.lang.String r2 = "MEIZU_M5"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 83
            goto L_0x0717
        L_0x0370:
            java.lang.String r2 = "p212"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 92
            goto L_0x0717
        L_0x037c:
            java.lang.String r2 = "mido"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 85
            goto L_0x0717
        L_0x0388:
            java.lang.String r2 = "kate"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 75
            goto L_0x0717
        L_0x0394:
            java.lang.String r2 = "fugu"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 50
            goto L_0x0717
        L_0x03a0:
            java.lang.String r2 = "XE2X"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 136(0x88, float:1.9E-43)
            goto L_0x0717
        L_0x03ac:
            java.lang.String r2 = "Q427"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 111(0x6f, float:1.56E-43)
            goto L_0x0717
        L_0x03b8:
            java.lang.String r2 = "Q350"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 109(0x6d, float:1.53E-43)
            goto L_0x0717
        L_0x03c4:
            java.lang.String r2 = "P681"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 93
            goto L_0x0717
        L_0x03d0:
            java.lang.String r2 = "F04J"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 41
            goto L_0x0717
        L_0x03dc:
            java.lang.String r2 = "F04H"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 40
            goto L_0x0717
        L_0x03e8:
            java.lang.String r2 = "F03H"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 39
            goto L_0x0717
        L_0x03f4:
            java.lang.String r2 = "F02H"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 38
            goto L_0x0717
        L_0x0400:
            java.lang.String r2 = "F01J"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 37
            goto L_0x0717
        L_0x040c:
            java.lang.String r2 = "F01H"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 36
            goto L_0x0717
        L_0x0418:
            java.lang.String r2 = "1714"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 2
            goto L_0x0717
        L_0x0423:
            java.lang.String r2 = "1713"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 1
            goto L_0x0717
        L_0x042e:
            java.lang.String r2 = "1601"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 0
            goto L_0x0717
        L_0x0439:
            java.lang.String r2 = "flo"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 49
            goto L_0x0717
        L_0x0445:
            java.lang.String r2 = "deb"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 28
            goto L_0x0717
        L_0x0451:
            java.lang.String r2 = "cv3"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 27
            goto L_0x0717
        L_0x045d:
            java.lang.String r2 = "cv1"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 26
            goto L_0x0717
        L_0x0469:
            java.lang.String r2 = "Z80"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 139(0x8b, float:1.95E-43)
            goto L_0x0717
        L_0x0475:
            java.lang.String r2 = "QX1"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 115(0x73, float:1.61E-43)
            goto L_0x0717
        L_0x0481:
            java.lang.String r2 = "PLE"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 107(0x6b, float:1.5E-43)
            goto L_0x0717
        L_0x048d:
            java.lang.String r2 = "P85"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 94
            goto L_0x0717
        L_0x0499:
            java.lang.String r2 = "MX6"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 86
            goto L_0x0717
        L_0x04a5:
            java.lang.String r2 = "M5c"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 80
            goto L_0x0717
        L_0x04b1:
            java.lang.String r2 = "M04"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 79
            goto L_0x0717
        L_0x04bd:
            java.lang.String r2 = "JGZ"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 73
            goto L_0x0717
        L_0x04c9:
            java.lang.String r2 = "mh"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 84
            goto L_0x0717
        L_0x04d5:
            java.lang.String r2 = "b5"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 16
            goto L_0x0717
        L_0x04e1:
            java.lang.String r2 = "V5"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 129(0x81, float:1.81E-43)
            goto L_0x0717
        L_0x04ed:
            java.lang.String r2 = "V1"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 127(0x7f, float:1.78E-43)
            goto L_0x0717
        L_0x04f9:
            java.lang.String r2 = "Q5"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 113(0x71, float:1.58E-43)
            goto L_0x0717
        L_0x0505:
            java.lang.String r2 = "C1"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 20
            goto L_0x0717
        L_0x0511:
            java.lang.String r2 = "woods_fn"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 134(0x86, float:1.88E-43)
            goto L_0x0717
        L_0x051d:
            java.lang.String r2 = "ELUGA_A3_Pro"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 31
            goto L_0x0717
        L_0x0529:
            java.lang.String r2 = "Z12_PRO"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 138(0x8a, float:1.93E-43)
            goto L_0x0717
        L_0x0535:
            java.lang.String r2 = "BLACK-1X"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 17
            goto L_0x0717
        L_0x0541:
            java.lang.String r2 = "taido_row"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 121(0x79, float:1.7E-43)
            goto L_0x0717
        L_0x054d:
            java.lang.String r2 = "Pixi4-7_3G"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 105(0x69, float:1.47E-43)
            goto L_0x0717
        L_0x0559:
            java.lang.String r2 = "GIONEE_GBL7360"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 53
            goto L_0x0717
        L_0x0565:
            java.lang.String r2 = "GiONEE_CBL7513"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 51
            goto L_0x0717
        L_0x0571:
            java.lang.String r2 = "OnePlus5T"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 91
            goto L_0x0717
        L_0x057d:
            java.lang.String r2 = "whyred"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 132(0x84, float:1.85E-43)
            goto L_0x0717
        L_0x0589:
            java.lang.String r2 = "watson"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 131(0x83, float:1.84E-43)
            goto L_0x0717
        L_0x0595:
            java.lang.String r2 = "SVP-DTV15"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 119(0x77, float:1.67E-43)
            goto L_0x0717
        L_0x05a1:
            java.lang.String r2 = "A7000-a"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 9
            goto L_0x0717
        L_0x05ad:
            java.lang.String r2 = "nicklaus_f"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 88
            goto L_0x0717
        L_0x05b9:
            java.lang.String r2 = "tcl_eu"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 126(0x7e, float:1.77E-43)
            goto L_0x0717
        L_0x05c5:
            java.lang.String r2 = "ELUGA_Ray_X"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 34
            goto L_0x0717
        L_0x05d1:
            java.lang.String r2 = "s905x018"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 120(0x78, float:1.68E-43)
            goto L_0x0717
        L_0x05dd:
            java.lang.String r2 = "A10-70L"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 6
            goto L_0x0717
        L_0x05e8:
            java.lang.String r2 = "A10-70F"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 5
            goto L_0x0717
        L_0x05f3:
            java.lang.String r2 = "namath"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 87
            goto L_0x0717
        L_0x05ff:
            java.lang.String r2 = "Slate_Pro"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 118(0x76, float:1.65E-43)
            goto L_0x0717
        L_0x060b:
            java.lang.String r2 = "iris60"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 70
            goto L_0x0717
        L_0x0617:
            java.lang.String r2 = "BRAVIA_ATV2"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 18
            goto L_0x0717
        L_0x0623:
            java.lang.String r2 = "GiONEE_GBL7319"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 52
            goto L_0x0717
        L_0x062f:
            java.lang.String r2 = "panell_dt"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 99
            goto L_0x0717
        L_0x063b:
            java.lang.String r2 = "panell_ds"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 98
            goto L_0x0717
        L_0x0647:
            java.lang.String r2 = "panell_dl"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 97
            goto L_0x0717
        L_0x0653:
            java.lang.String r2 = "vernee_M5"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 130(0x82, float:1.82E-43)
            goto L_0x0717
        L_0x065f:
            java.lang.String r2 = "pacificrim"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 95
            goto L_0x0717
        L_0x066b:
            java.lang.String r2 = "Phantom6"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 104(0x68, float:1.46E-43)
            goto L_0x0717
        L_0x0677:
            java.lang.String r2 = "ComioS1"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 21
            goto L_0x0717
        L_0x0683:
            java.lang.String r2 = "XT1663"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 137(0x89, float:1.92E-43)
            goto L_0x0717
        L_0x068f:
            java.lang.String r2 = "RAIJIN"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 116(0x74, float:1.63E-43)
            goto L_0x0717
        L_0x069b:
            java.lang.String r2 = "AquaPowerM"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 13
            goto L_0x0717
        L_0x06a7:
            java.lang.String r2 = "PGN611"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 103(0x67, float:1.44E-43)
            goto L_0x0717
        L_0x06b3:
            java.lang.String r2 = "PGN610"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 102(0x66, float:1.43E-43)
            goto L_0x0717
        L_0x06be:
            java.lang.String r2 = "PGN528"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 101(0x65, float:1.42E-43)
            goto L_0x0717
        L_0x06c9:
            java.lang.String r2 = "NX573J"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 90
            goto L_0x0717
        L_0x06d4:
            java.lang.String r2 = "NX541J"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 89
            goto L_0x0717
        L_0x06df:
            java.lang.String r2 = "CP8676_I02"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 22
            goto L_0x0717
        L_0x06ea:
            java.lang.String r2 = "K50a40"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 74
            goto L_0x0717
        L_0x06f5:
            java.lang.String r2 = "GIONEE_SWW1631"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 56
            goto L_0x0717
        L_0x0700:
            java.lang.String r2 = "GIONEE_SWW1627"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 55
            goto L_0x0717
        L_0x070b:
            java.lang.String r2 = "GIONEE_SWW1609"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0716
            r2 = 54
            goto L_0x0717
        L_0x0716:
            r2 = -1
        L_0x0717:
            switch(r2) {
                case 0: goto L_0x0073;
                case 1: goto L_0x0073;
                case 2: goto L_0x0073;
                case 3: goto L_0x0073;
                case 4: goto L_0x0073;
                case 5: goto L_0x0073;
                case 6: goto L_0x0073;
                case 7: goto L_0x0073;
                case 8: goto L_0x0073;
                case 9: goto L_0x0073;
                case 10: goto L_0x0073;
                case 11: goto L_0x0073;
                case 12: goto L_0x0073;
                case 13: goto L_0x0073;
                case 14: goto L_0x0073;
                case 15: goto L_0x0073;
                case 16: goto L_0x0073;
                case 17: goto L_0x0073;
                case 18: goto L_0x0073;
                case 19: goto L_0x0073;
                case 20: goto L_0x0073;
                case 21: goto L_0x0073;
                case 22: goto L_0x0073;
                case 23: goto L_0x0073;
                case 24: goto L_0x0073;
                case 25: goto L_0x0073;
                case 26: goto L_0x0073;
                case 27: goto L_0x0073;
                case 28: goto L_0x0073;
                case 29: goto L_0x0073;
                case 30: goto L_0x0073;
                case 31: goto L_0x0073;
                case 32: goto L_0x0073;
                case 33: goto L_0x0073;
                case 34: goto L_0x0073;
                case 35: goto L_0x0073;
                case 36: goto L_0x0073;
                case 37: goto L_0x0073;
                case 38: goto L_0x0073;
                case 39: goto L_0x0073;
                case 40: goto L_0x0073;
                case 41: goto L_0x0073;
                case 42: goto L_0x0073;
                case 43: goto L_0x0073;
                case 44: goto L_0x0073;
                case 45: goto L_0x0073;
                case 46: goto L_0x0073;
                case 47: goto L_0x0073;
                case 48: goto L_0x0073;
                case 49: goto L_0x0073;
                case 50: goto L_0x0073;
                case 51: goto L_0x0073;
                case 52: goto L_0x0073;
                case 53: goto L_0x0073;
                case 54: goto L_0x0073;
                case 55: goto L_0x0073;
                case 56: goto L_0x0073;
                case 57: goto L_0x0073;
                case 58: goto L_0x0073;
                case 59: goto L_0x0073;
                case 60: goto L_0x0073;
                case 61: goto L_0x0073;
                case 62: goto L_0x0073;
                case 63: goto L_0x0073;
                case 64: goto L_0x0073;
                case 65: goto L_0x0073;
                case 66: goto L_0x0073;
                case 67: goto L_0x0073;
                case 68: goto L_0x0073;
                case 69: goto L_0x0073;
                case 70: goto L_0x0073;
                case 71: goto L_0x0073;
                case 72: goto L_0x0073;
                case 73: goto L_0x0073;
                case 74: goto L_0x0073;
                case 75: goto L_0x0073;
                case 76: goto L_0x0073;
                case 77: goto L_0x0073;
                case 78: goto L_0x0073;
                case 79: goto L_0x0073;
                case 80: goto L_0x0073;
                case 81: goto L_0x0073;
                case 82: goto L_0x0073;
                case 83: goto L_0x0073;
                case 84: goto L_0x0073;
                case 85: goto L_0x0073;
                case 86: goto L_0x0073;
                case 87: goto L_0x0073;
                case 88: goto L_0x0073;
                case 89: goto L_0x0073;
                case 90: goto L_0x0073;
                case 91: goto L_0x0073;
                case 92: goto L_0x0073;
                case 93: goto L_0x0073;
                case 94: goto L_0x0073;
                case 95: goto L_0x0073;
                case 96: goto L_0x0073;
                case 97: goto L_0x0073;
                case 98: goto L_0x0073;
                case 99: goto L_0x0073;
                case 100: goto L_0x0073;
                case 101: goto L_0x0073;
                case 102: goto L_0x0073;
                case 103: goto L_0x0073;
                case 104: goto L_0x0073;
                case 105: goto L_0x0073;
                case 106: goto L_0x0073;
                case 107: goto L_0x0073;
                case 108: goto L_0x0073;
                case 109: goto L_0x0073;
                case 110: goto L_0x0073;
                case 111: goto L_0x0073;
                case 112: goto L_0x0073;
                case 113: goto L_0x0073;
                case 114: goto L_0x0073;
                case 115: goto L_0x0073;
                case 116: goto L_0x0073;
                case 117: goto L_0x0073;
                case 118: goto L_0x0073;
                case 119: goto L_0x0073;
                case 120: goto L_0x0073;
                case 121: goto L_0x0073;
                case 122: goto L_0x0073;
                case 123: goto L_0x0073;
                case 124: goto L_0x0073;
                case 125: goto L_0x0073;
                case 126: goto L_0x0073;
                case 127: goto L_0x0073;
                case 128: goto L_0x0073;
                case 129: goto L_0x0073;
                case 130: goto L_0x0073;
                case 131: goto L_0x0073;
                case 132: goto L_0x0073;
                case 133: goto L_0x0073;
                case 134: goto L_0x0073;
                case 135: goto L_0x0073;
                case 136: goto L_0x0073;
                case 137: goto L_0x0073;
                case 138: goto L_0x0073;
                case 139: goto L_0x0073;
                default: goto L_0x071a;
            }     // Catch:{ all -> 0x074d }
        L_0x071a:
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.d     // Catch:{ all -> 0x074d }
            int r2 = r1.hashCode()     // Catch:{ all -> 0x074d }
            switch(r2) {
                case -594534941: goto L_0x0738;
                case 2006354: goto L_0x072e;
                case 2006367: goto L_0x0724;
                default: goto L_0x0723;
            }     // Catch:{ all -> 0x074d }
        L_0x0723:
            goto L_0x0741
        L_0x0724:
            java.lang.String r2 = "AFTN"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0741
            r7 = 1
            goto L_0x0742
        L_0x072e:
            java.lang.String r2 = "AFTA"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0741
            r7 = 0
            goto L_0x0742
        L_0x0738:
            java.lang.String r2 = "JSN-L21"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x074d }
            if (r1 == 0) goto L_0x0741
            goto L_0x0742
        L_0x0741:
            r7 = -1
        L_0x0742:
            switch(r7) {
                case 0: goto L_0x0073;
                case 1: goto L_0x0073;
                case 2: goto L_0x0073;
                default: goto L_0x0745;
            }     // Catch:{ all -> 0x074d }
        L_0x0745:
            e = r0     // Catch:{ all -> 0x074d }
            d = r9     // Catch:{ all -> 0x074d }
        L_0x0749:
            monitor-exit(r13)     // Catch:{ all -> 0x074d }
            boolean r13 = e
            return r13
        L_0x074d:
            r0 = move-exception
            monitor-exit(r13)
            goto L_0x0751
        L_0x0750:
            throw r0
        L_0x0751:
            goto L_0x0750
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.video.MediaCodecVideoRenderer.b(java.lang.String):boolean");
    }

    private void f(long j2) {
        this.b.a(j2);
        this.C += j2;
        this.D++;
    }

    private static boolean g(long j2) {
        return j2 < -30000;
    }

    public final void A() {
        super.A();
        O();
    }

    public final boolean D() {
        return this.J && Util.a < 23;
    }

    public final void J() {
        super.J();
        this.z = 0;
    }

    public final float a(float f2, Format[] formatArr) {
        float f3 = -1.0f;
        for (Format format : formatArr) {
            float f4 = format.q;
            if (f4 != -1.0f) {
                f3 = Math.max(f3, f4);
            }
        }
        if (f3 == -1.0f) {
            return -1.0f;
        }
        return f3 * f2;
    }

    public final int a(MediaCodecSelector mediaCodecSelector, Format format) {
        int i2 = 0;
        if (!MimeTypes.b(format.l)) {
            return RendererCapabilities$$CC.a(0);
        }
        boolean z2 = format.o != null;
        List a2 = a(mediaCodecSelector, format, z2, false);
        if (z2 && a2.isEmpty()) {
            a2 = a(mediaCodecSelector, format, false, false);
        }
        if (a2.isEmpty()) {
            return RendererCapabilities$$CC.a(1);
        }
        if (!c(format)) {
            return RendererCapabilities$$CC.a(2);
        }
        com.google.android.exoplayer2.mediacodec.MediaCodecInfo mediaCodecInfo = (com.google.android.exoplayer2.mediacodec.MediaCodecInfo) a2.get(0);
        boolean a3 = mediaCodecInfo.a(format);
        int i3 = mediaCodecInfo.b(format) ? 16 : 8;
        if (a3) {
            List a4 = a(mediaCodecSelector, format, z2, true);
            if (!a4.isEmpty()) {
                com.google.android.exoplayer2.mediacodec.MediaCodecInfo mediaCodecInfo2 = (com.google.android.exoplayer2.mediacodec.MediaCodecInfo) a4.get(0);
                if (mediaCodecInfo2.a(format) && mediaCodecInfo2.b(format)) {
                    i2 = 32;
                }
            }
        }
        return RendererCapabilities$$CC.a(a3 ? 4 : 3, i3, i2);
    }

    public final DecoderReuseEvaluation a(FormatHolder formatHolder) {
        DecoderReuseEvaluation a2 = super.a(formatHolder);
        VideoRendererEventListener.EventDispatcher eventDispatcher = this.h;
        Format format = formatHolder.b;
        if (eventDispatcher.a != null) {
            eventDispatcher.a.post(new VideoRendererEventListener$EventDispatcher$$Lambda$2(eventDispatcher, format, a2));
        }
        return a2;
    }

    public final DecoderReuseEvaluation a(com.google.android.exoplayer2.mediacodec.MediaCodecInfo mediaCodecInfo, Format format, Format format2) {
        DecoderReuseEvaluation a2 = mediaCodecInfo.a(format, format2);
        int i2 = a2.b;
        if (format2.width > this.k.a || format2.height > this.k.b) {
            i2 |= 256;
        }
        if (b(mediaCodecInfo, format2) > this.k.c) {
            i2 |= 64;
        }
        int i3 = i2;
        return new DecoderReuseEvaluation(mediaCodecInfo.a, format, format2, i3 != 0 ? 0 : a2.a, i3);
    }

    public final MediaCodecAdapter.Configuration a(com.google.android.exoplayer2.mediacodec.MediaCodecInfo mediaCodecInfo, Format format, MediaCrypto mediaCrypto, float f2) {
        CodecMaxValues codecMaxValues;
        boolean z2;
        Pair a2;
        int a3;
        com.google.android.exoplayer2.mediacodec.MediaCodecInfo mediaCodecInfo2 = mediaCodecInfo;
        Format format2 = format;
        float f3 = f2;
        DummySurface dummySurface = this.o;
        if (!(dummySurface == null || dummySurface.a == mediaCodecInfo2.e)) {
            this.o.release();
            this.o = null;
        }
        String str = mediaCodecInfo2.c;
        Format[] u2 = u();
        int i2 = format2.width;
        int i3 = format2.height;
        int b = b(mediaCodecInfo, format);
        if (u2.length == 1) {
            if (!(b == -1 || (a3 = a(mediaCodecInfo2, format2.l, format2.width, format2.height)) == -1)) {
                b = Math.min((int) (((float) b) * 1.5f), a3);
            }
            codecMaxValues = new CodecMaxValues(i2, i3, b);
        } else {
            int length = u2.length;
            boolean z3 = false;
            for (int i4 = 0; i4 < length; i4++) {
                Format format3 = u2[i4];
                if (format2.v != null && format3.v == null) {
                    Format.Builder a4 = format3.a();
                    a4.w = format2.v;
                    format3 = a4.a();
                }
                if (mediaCodecInfo2.a(format2, format3).a != 0) {
                    z3 |= format3.width == -1 || format3.height == -1;
                    i2 = Math.max(i2, format3.width);
                    i3 = Math.max(i3, format3.height);
                    b = Math.max(b, b(mediaCodecInfo2, format3));
                }
            }
            if (z3) {
                Log.c("MediaCodecVideoRenderer", new StringBuilder(66).append("Resolutions unknown. Codec max resolution: ").append(i2).append("x").append(i3).toString());
                Point a5 = a(mediaCodecInfo, format);
                if (a5 != null) {
                    i2 = Math.max(i2, a5.x);
                    i3 = Math.max(i3, a5.y);
                    b = Math.max(b, a(mediaCodecInfo2, format2.l, i2, i3));
                    Log.c("MediaCodecVideoRenderer", new StringBuilder(57).append("Codec max resolution adjusted to: ").append(i2).append("x").append(i3).toString());
                }
            }
            codecMaxValues = new CodecMaxValues(i2, i3, b);
        }
        this.k = codecMaxValues;
        boolean z4 = this.j;
        int i5 = this.J ? this.K : 0;
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", str);
        mediaFormat.setInteger("width", format2.width);
        mediaFormat.setInteger("height", format2.height);
        MediaFormatUtil.a(mediaFormat, format2.n);
        MediaFormatUtil.a(mediaFormat, "frame-rate", format2.q);
        MediaFormatUtil.a(mediaFormat, "rotation-degrees", format2.r);
        MediaFormatUtil.a(mediaFormat, format2.v);
        if ("video/dolby-vision".equals(format2.l) && (a2 = MediaCodecUtil.a(format)) != null) {
            MediaFormatUtil.a(mediaFormat, "profile", ((Integer) a2.first).intValue());
        }
        mediaFormat.setInteger("max-width", codecMaxValues.a);
        mediaFormat.setInteger("max-height", codecMaxValues.b);
        MediaFormatUtil.a(mediaFormat, "max-input-size", codecMaxValues.c);
        if (Util.a >= 23) {
            mediaFormat.setInteger("priority", 0);
            if (f3 != -1.0f) {
                mediaFormat.setFloat("operating-rate", f3);
            }
        }
        if (z4) {
            z2 = true;
            mediaFormat.setInteger("no-post-process", 1);
            mediaFormat.setInteger("auto-frc", 0);
        } else {
            z2 = true;
        }
        if (i5 != 0) {
            mediaFormat.setFeatureEnabled("tunneled-playback", z2);
            mediaFormat.setInteger("audio-session-id", i5);
        }
        if (this.n == null) {
            if (b(mediaCodecInfo)) {
                if (this.o == null) {
                    this.o = DummySurface.a(this.f, mediaCodecInfo2.e);
                }
                this.n = this.o;
            } else {
                throw new IllegalStateException();
            }
        }
        return new MediaCodecAdapter.Configuration(mediaCodecInfo2, mediaFormat, this.n, mediaCrypto);
    }

    public final MediaCodecDecoderException a(Throwable th, com.google.android.exoplayer2.mediacodec.MediaCodecInfo mediaCodecInfo) {
        return new MediaCodecVideoDecoderException(th, mediaCodecInfo, this.n);
    }

    public final List a(MediaCodecSelector mediaCodecSelector, Format format, boolean z2) {
        return a(mediaCodecSelector, format, z2, this.J);
    }

    public final void a(float f2, float f3) {
        super.a(f2, f3);
        VideoFrameReleaseHelper videoFrameReleaseHelper = this.g;
        videoFrameReleaseHelper.h = f2;
        videoFrameReleaseHelper.a();
        videoFrameReleaseHelper.a(false);
    }

    /* JADX WARNING: Failed to insert additional move for type inference */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(int r5, java.lang.Object r6) {
        /*
            r4 = this;
            switch(r5) {
                case 1: goto L_0x0035;
                case 4: goto L_0x0021;
                case 6: goto L_0x001c;
                case 102: goto L_0x0008;
                default: goto L_0x0003;
            }
        L_0x0003:
            super.a((int) r5, (java.lang.Object) r6)
            goto L_0x00c8
        L_0x0008:
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r5 = r6.intValue()
            int r6 = r4.K
            if (r6 == r5) goto L_0x00c8
            r4.K = r5
            boolean r5 = r4.J
            if (r5 == 0) goto L_0x00c8
            r4.H()
            return
        L_0x001c:
            com.google.android.exoplayer2.video.VideoFrameMetadataListener r6 = (com.google.android.exoplayer2.video.VideoFrameMetadataListener) r6
            r4.L = r6
            return
        L_0x0021:
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r5 = r6.intValue()
            r4.q = r5
            com.google.android.exoplayer2.mediacodec.MediaCodecAdapter r5 = r4.E()
            if (r5 == 0) goto L_0x00c8
            int r6 = r4.q
            r5.c(r6)
            return
        L_0x0035:
            boolean r5 = r6 instanceof android.view.Surface
            r0 = 0
            if (r5 == 0) goto L_0x003d
            android.view.Surface r6 = (android.view.Surface) r6
            goto L_0x003e
        L_0x003d:
            r6 = r0
        L_0x003e:
            if (r6 != 0) goto L_0x005c
            com.google.android.exoplayer2.video.DummySurface r5 = r4.o
            if (r5 == 0) goto L_0x0046
            r6 = r5
            goto L_0x005c
        L_0x0046:
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r5 = r4.G()
            if (r5 == 0) goto L_0x005c
            boolean r1 = r4.b((com.google.android.exoplayer2.mediacodec.MediaCodecInfo) r5)
            if (r1 == 0) goto L_0x005c
            android.content.Context r6 = r4.f
            boolean r5 = r5.e
            com.google.android.exoplayer2.video.DummySurface r6 = com.google.android.exoplayer2.video.DummySurface.a(r6, r5)
            r4.o = r6
        L_0x005c:
            android.view.Surface r5 = r4.n
            if (r5 == r6) goto L_0x00b4
            r4.n = r6
            com.google.android.exoplayer2.video.VideoFrameReleaseHelper r5 = r4.g
            boolean r1 = r6 instanceof com.google.android.exoplayer2.video.DummySurface
            if (r1 == 0) goto L_0x006a
            r1 = r0
            goto L_0x006b
        L_0x006a:
            r1 = r6
        L_0x006b:
            android.view.Surface r2 = r5.f
            if (r2 == r1) goto L_0x0078
            r5.c()
            r5.f = r1
            r1 = 1
            r5.a((boolean) r1)
        L_0x0078:
            r5 = 0
            r4.p = r5
            int r5 = r4.b_()
            com.google.android.exoplayer2.mediacodec.MediaCodecAdapter r1 = r4.E()
            if (r1 == 0) goto L_0x009b
            int r2 = com.google.android.exoplayer2.util.Util.a
            r3 = 23
            if (r2 < r3) goto L_0x0095
            if (r6 == 0) goto L_0x0095
            boolean r2 = r4.l
            if (r2 != 0) goto L_0x0095
            r1.a((android.view.Surface) r6)
            goto L_0x009b
        L_0x0095:
            r4.H()
            r4.C()
        L_0x009b:
            if (r6 == 0) goto L_0x00ae
            com.google.android.exoplayer2.video.DummySurface r1 = r4.o
            if (r6 == r1) goto L_0x00ae
            r4.R()
            r4.O()
            r6 = 2
            if (r5 != r6) goto L_0x00b3
            r4.N()
            return
        L_0x00ae:
            r4.I = r0
            r4.O()
        L_0x00b3:
            return
        L_0x00b4:
            if (r6 == 0) goto L_0x00c8
            com.google.android.exoplayer2.video.DummySurface r5 = r4.o
            if (r6 == r5) goto L_0x00c8
            r4.R()
            boolean r5 = r4.p
            if (r5 == 0) goto L_0x00c8
            com.google.android.exoplayer2.video.VideoRendererEventListener$EventDispatcher r5 = r4.h
            android.view.Surface r6 = r4.n
            r5.a((java.lang.Object) r6)
        L_0x00c8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.video.MediaCodecVideoRenderer.a(int, java.lang.Object):void");
    }

    public final void a(long j2, boolean z2) {
        super.a(j2, z2);
        O();
        this.g.a();
        this.A = -9223372036854775807L;
        this.u = -9223372036854775807L;
        this.y = 0;
        if (z2) {
            N();
        } else {
            this.v = -9223372036854775807L;
        }
    }

    public final void a(Format format, MediaFormat mediaFormat) {
        int i2;
        MediaCodecAdapter E2 = E();
        if (E2 != null) {
            E2.c(this.q);
        }
        if (this.J) {
            this.E = format.width;
            i2 = format.height;
        } else {
            Assertions.b((Object) mediaFormat);
            boolean z2 = mediaFormat.containsKey("crop-right") && mediaFormat.containsKey("crop-left") && mediaFormat.containsKey("crop-bottom") && mediaFormat.containsKey("crop-top");
            this.E = z2 ? (mediaFormat.getInteger("crop-right") - mediaFormat.getInteger("crop-left")) + 1 : mediaFormat.getInteger("width");
            i2 = z2 ? (mediaFormat.getInteger("crop-bottom") - mediaFormat.getInteger("crop-top")) + 1 : mediaFormat.getInteger("height");
        }
        this.F = i2;
        this.H = format.s;
        if (Util.a < 21) {
            this.G = format.r;
        } else if (format.r == 90 || format.r == 270) {
            int i3 = this.E;
            this.E = this.F;
            this.F = i3;
            this.H = 1.0f / this.H;
        }
        VideoFrameReleaseHelper videoFrameReleaseHelper = this.g;
        videoFrameReleaseHelper.g = format.q;
        FixedFrameRateEstimator fixedFrameRateEstimator = videoFrameReleaseHelper.a;
        fixedFrameRateEstimator.a.a();
        fixedFrameRateEstimator.b.a();
        fixedFrameRateEstimator.c = false;
        fixedFrameRateEstimator.d = -9223372036854775807L;
        fixedFrameRateEstimator.e = 0;
        videoFrameReleaseHelper.b();
    }

    public final void a(DecoderInputBuffer decoderInputBuffer) {
        if (!this.J) {
            this.z++;
        }
        if (Util.a < 23 && this.J) {
            e(decoderInputBuffer.e);
        }
    }

    public final void a(Exception exc) {
        Log.b("MediaCodecVideoRenderer", "Video codec error", exc);
        VideoRendererEventListener.EventDispatcher eventDispatcher = this.h;
        if (eventDispatcher.a != null) {
            eventDispatcher.a.post(new VideoRendererEventListener$EventDispatcher$$Lambda$9(eventDispatcher, exc));
        }
    }

    public final void a(String str) {
        VideoRendererEventListener.EventDispatcher eventDispatcher = this.h;
        if (eventDispatcher.a != null) {
            eventDispatcher.a.post(new VideoRendererEventListener$EventDispatcher$$Lambda$7(eventDispatcher, str));
        }
    }

    public final void a(String str, long j2, long j3) {
        VideoRendererEventListener.EventDispatcher eventDispatcher = this.h;
        if (eventDispatcher.a != null) {
            eventDispatcher.a.post(new VideoRendererEventListener$EventDispatcher$$Lambda$1(eventDispatcher, str, j2, j3));
        }
        this.l = b(str);
        com.google.android.exoplayer2.mediacodec.MediaCodecInfo mediaCodecInfo = (com.google.android.exoplayer2.mediacodec.MediaCodecInfo) Assertions.b((Object) G());
        boolean z2 = false;
        if (Util.a >= 29 && "video/x-vnd.on2.vp9".equals(mediaCodecInfo.b)) {
            MediaCodecInfo.CodecProfileLevel[] a2 = mediaCodecInfo.a();
            int length = a2.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                } else if (a2[i2].profile == 16384) {
                    z2 = true;
                    break;
                } else {
                    i2++;
                }
            }
        }
        this.m = z2;
        if (Util.a >= 23 && this.J) {
            this.a = new OnFrameRenderedListenerV23((MediaCodecAdapter) Assertions.b((Object) E()));
        }
    }

    public final void a(boolean z2, boolean z3) {
        super.a(z2, z3);
        boolean z4 = v().b;
        Assertions.b(!z4 || this.K != 0);
        if (this.J != z4) {
            this.J = z4;
            H();
        }
        VideoRendererEventListener.EventDispatcher eventDispatcher = this.h;
        DecoderCounters decoderCounters = this.b;
        if (eventDispatcher.a != null) {
            eventDispatcher.a.post(new VideoRendererEventListener$EventDispatcher$$Lambda$0(eventDispatcher, decoderCounters));
        }
        VideoFrameReleaseHelper videoFrameReleaseHelper = this.g;
        if (videoFrameReleaseHelper.b != null) {
            ((VideoFrameReleaseHelper.VSyncSampler) Assertions.b((Object) videoFrameReleaseHelper.c)).b.sendEmptyMessage(1);
            if (videoFrameReleaseHelper.d != null) {
                VideoFrameReleaseHelper.DefaultDisplayListener defaultDisplayListener = videoFrameReleaseHelper.d;
                defaultDisplayListener.a.registerDisplayListener(defaultDisplayListener, Util.a());
            }
            videoFrameReleaseHelper.d();
        }
        this.s = z3;
        this.t = false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0078, code lost:
        if ((r11.a == 0 ? false : r11.c[com.google.android.exoplayer2.video.FixedFrameRateEstimator.Matcher.b(r11.a - 1)]) != false) goto L_0x007a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x013d, code lost:
        if ((g(r5) && r16 > 100000) != false) goto L_0x013f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0157, code lost:
        if (com.google.android.exoplayer2.util.Util.a >= 21) goto L_0x0278;
     */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x01fa  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x01fc  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0204  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x0206  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x0210  */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x0245  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x0260  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0146  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x015b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(long r27, long r29, com.google.android.exoplayer2.mediacodec.MediaCodecAdapter r31, java.nio.ByteBuffer r32, int r33, int r34, int r35, long r36, boolean r38, boolean r39, com.google.android.exoplayer2.Format r40) {
        /*
            r26 = this;
            r1 = r26
            r2 = r27
            r0 = r31
            r4 = r33
            r5 = r36
            com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r31)
            long r7 = r1.u
            r9 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 != 0) goto L_0x001a
            r1.u = r2
        L_0x001a:
            long r7 = r1.A
            r11 = -1
            r13 = 1000(0x3e8, double:4.94E-321)
            r15 = 0
            int r16 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r16 == 0) goto L_0x00bb
            com.google.android.exoplayer2.video.VideoFrameReleaseHelper r7 = r1.g
            long r9 = r7.l
            int r8 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r8 == 0) goto L_0x0035
            long r8 = r7.l
            r7.n = r8
            long r8 = r7.m
            r7.o = r8
        L_0x0035:
            long r8 = r7.k
            r16 = 1
            long r8 = r8 + r16
            r7.k = r8
            com.google.android.exoplayer2.video.FixedFrameRateEstimator r8 = r7.a
            long r9 = r5 * r13
            com.google.android.exoplayer2.video.FixedFrameRateEstimator$Matcher r11 = r8.a
            r11.a(r9)
            com.google.android.exoplayer2.video.FixedFrameRateEstimator$Matcher r11 = r8.a
            boolean r11 = r11.b()
            if (r11 == 0) goto L_0x0051
            r8.c = r15
            goto L_0x008e
        L_0x0051:
            long r11 = r8.d
            r18 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r20 = (r11 > r18 ? 1 : (r11 == r18 ? 0 : -1))
            if (r20 == 0) goto L_0x008e
            boolean r11 = r8.c
            if (r11 == 0) goto L_0x007a
            com.google.android.exoplayer2.video.FixedFrameRateEstimator$Matcher r11 = r8.b
            long r13 = r11.a
            r20 = 0
            int r12 = (r13 > r20 ? 1 : (r13 == r20 ? 0 : -1))
            if (r12 != 0) goto L_0x006c
            r11 = 0
            goto L_0x0078
        L_0x006c:
            boolean[] r12 = r11.c
            long r13 = r11.a
            long r13 = r13 - r16
            int r11 = com.google.android.exoplayer2.video.FixedFrameRateEstimator.Matcher.b(r13)
            boolean r11 = r12[r11]
        L_0x0078:
            if (r11 == 0) goto L_0x0086
        L_0x007a:
            com.google.android.exoplayer2.video.FixedFrameRateEstimator$Matcher r11 = r8.b
            r11.a()
            com.google.android.exoplayer2.video.FixedFrameRateEstimator$Matcher r11 = r8.b
            long r12 = r8.d
            r11.a(r12)
        L_0x0086:
            r11 = 1
            r8.c = r11
            com.google.android.exoplayer2.video.FixedFrameRateEstimator$Matcher r11 = r8.b
            r11.a(r9)
        L_0x008e:
            boolean r11 = r8.c
            if (r11 == 0) goto L_0x00a4
            com.google.android.exoplayer2.video.FixedFrameRateEstimator$Matcher r11 = r8.b
            boolean r11 = r11.b()
            if (r11 == 0) goto L_0x00a4
            com.google.android.exoplayer2.video.FixedFrameRateEstimator$Matcher r11 = r8.a
            com.google.android.exoplayer2.video.FixedFrameRateEstimator$Matcher r12 = r8.b
            r8.a = r12
            r8.b = r11
            r8.c = r15
        L_0x00a4:
            r8.d = r9
            com.google.android.exoplayer2.video.FixedFrameRateEstimator$Matcher r9 = r8.a
            boolean r9 = r9.b()
            if (r9 == 0) goto L_0x00b0
            r9 = 0
            goto L_0x00b4
        L_0x00b0:
            int r9 = r8.e
            r10 = 1
            int r9 = r9 + r10
        L_0x00b4:
            r8.e = r9
            r7.b()
            r1.A = r5
        L_0x00bb:
            long r7 = r26.M()
            long r9 = r5 - r7
            if (r38 == 0) goto L_0x00ca
            if (r39 != 0) goto L_0x00ca
            r1.a((com.google.android.exoplayer2.mediacodec.MediaCodecAdapter) r0, (int) r4)
        L_0x00c8:
            r0 = 1
            return r0
        L_0x00ca:
            float r11 = r26.K()
            double r11 = (double) r11
            int r13 = r26.b_()
            r14 = 2
            if (r13 != r14) goto L_0x00d8
            r13 = 1
            goto L_0x00d9
        L_0x00d8:
            r13 = 0
        L_0x00d9:
            long r16 = android.os.SystemClock.elapsedRealtime()
            r18 = 1000(0x3e8, double:4.94E-321)
            long r16 = r16 * r18
            long r5 = r5 - r2
            double r5 = (double) r5
            java.lang.Double.isNaN(r5)
            java.lang.Double.isNaN(r11)
            double r5 = r5 / r11
            long r5 = (long) r5
            if (r13 == 0) goto L_0x00f0
            long r11 = r16 - r29
            long r5 = r5 - r11
        L_0x00f0:
            android.view.Surface r11 = r1.n
            com.google.android.exoplayer2.video.DummySurface r12 = r1.o
            if (r11 != r12) goto L_0x0104
            boolean r2 = g(r5)
            if (r2 == 0) goto L_0x0103
            r1.a((com.google.android.exoplayer2.mediacodec.MediaCodecAdapter) r0, (int) r4)
        L_0x00ff:
            r1.f(r5)
            goto L_0x00c8
        L_0x0103:
            return r15
        L_0x0104:
            long r11 = r1.B
            long r16 = r16 - r11
            boolean r11 = r1.t
            if (r11 != 0) goto L_0x0113
            if (r13 != 0) goto L_0x0117
            boolean r11 = r1.s
            if (r11 == 0) goto L_0x0119
            goto L_0x0117
        L_0x0113:
            boolean r11 = r1.r
            if (r11 != 0) goto L_0x0119
        L_0x0117:
            r11 = 1
            goto L_0x011a
        L_0x0119:
            r11 = 0
        L_0x011a:
            long r14 = r1.v
            r20 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r22 = (r14 > r20 ? 1 : (r14 == r20 ? 0 : -1))
            if (r22 != 0) goto L_0x0141
            int r14 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r14 < 0) goto L_0x0141
            if (r11 != 0) goto L_0x013f
            if (r13 == 0) goto L_0x0141
            boolean r7 = g(r5)
            if (r7 == 0) goto L_0x013c
            r7 = 100000(0x186a0, double:4.94066E-319)
            int r11 = (r16 > r7 ? 1 : (r16 == r7 ? 0 : -1))
            if (r11 <= 0) goto L_0x013c
            r7 = 1
            goto L_0x013d
        L_0x013c:
            r7 = 0
        L_0x013d:
            if (r7 == 0) goto L_0x0141
        L_0x013f:
            r7 = 1
            goto L_0x0142
        L_0x0141:
            r7 = 0
        L_0x0142:
            r8 = 21
            if (r7 == 0) goto L_0x015b
            long r2 = java.lang.System.nanoTime()
            r34 = r26
            r35 = r9
            r37 = r2
            r39 = r40
            r34.a((long) r35, (long) r37, (com.google.android.exoplayer2.Format) r39)
            int r7 = com.google.android.exoplayer2.util.Util.a
            if (r7 < r8) goto L_0x02a9
            goto L_0x0278
        L_0x015b:
            if (r13 == 0) goto L_0x02b0
            long r13 = r1.u
            int r7 = (r2 > r13 ? 1 : (r2 == r13 ? 0 : -1))
            if (r7 != 0) goto L_0x0165
            goto L_0x02b0
        L_0x0165:
            long r13 = java.lang.System.nanoTime()
            r15 = 1000(0x3e8, double:4.94E-321)
            long r5 = r5 * r15
            long r5 = r5 + r13
            com.google.android.exoplayer2.video.VideoFrameReleaseHelper r7 = r1.g
            r15 = r9
            long r8 = r7.n
            r10 = -1
            int r17 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r17 == 0) goto L_0x01b6
            com.google.android.exoplayer2.video.FixedFrameRateEstimator r8 = r7.a
            com.google.android.exoplayer2.video.FixedFrameRateEstimator$Matcher r8 = r8.a
            boolean r8 = r8.b()
            if (r8 == 0) goto L_0x01b6
            com.google.android.exoplayer2.video.FixedFrameRateEstimator r8 = r7.a
            com.google.android.exoplayer2.video.FixedFrameRateEstimator$Matcher r9 = r8.a
            boolean r9 = r9.b()
            if (r9 == 0) goto L_0x0194
            com.google.android.exoplayer2.video.FixedFrameRateEstimator$Matcher r8 = r8.a
            long r8 = r8.c()
            goto L_0x0199
        L_0x0194:
            r8 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L_0x0199:
            long r10 = r7.o
            r36 = r13
            long r12 = r7.k
            long r2 = r7.n
            long r12 = r12 - r2
            long r8 = r8 * r12
            float r2 = (float) r8
            float r3 = r7.h
            float r2 = r2 / r3
            long r2 = (long) r2
            long r10 = r10 + r2
            boolean r2 = com.google.android.exoplayer2.video.VideoFrameReleaseHelper.a((long) r5, (long) r10)
            if (r2 == 0) goto L_0x01b2
            r5 = r10
            goto L_0x01b8
        L_0x01b2:
            r7.a()
            goto L_0x01b8
        L_0x01b6:
            r36 = r13
        L_0x01b8:
            long r2 = r7.k
            r7.l = r2
            r7.m = r5
            com.google.android.exoplayer2.video.VideoFrameReleaseHelper$VSyncSampler r2 = r7.c
            if (r2 == 0) goto L_0x01e7
            long r2 = r7.i
            r8 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r10 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
            if (r10 != 0) goto L_0x01ce
            goto L_0x01e7
        L_0x01ce:
            com.google.android.exoplayer2.video.VideoFrameReleaseHelper$VSyncSampler r2 = r7.c
            long r2 = r2.a
            int r10 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
            if (r10 != 0) goto L_0x01d7
            goto L_0x01e7
        L_0x01d7:
            long r8 = r7.i
            r20 = r5
            r22 = r2
            r24 = r8
            long r2 = com.google.android.exoplayer2.video.VideoFrameReleaseHelper.a(r20, r22, r24)
            long r5 = r7.j
            long r2 = r2 - r5
            goto L_0x01e8
        L_0x01e7:
            r2 = r5
        L_0x01e8:
            r5 = r36
            long r5 = r2 - r5
            r7 = 1000(0x3e8, double:4.94E-321)
            long r5 = r5 / r7
            long r7 = r1.v
            r9 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 == 0) goto L_0x01fc
            r7 = 1
            goto L_0x01fd
        L_0x01fc:
            r7 = 0
        L_0x01fd:
            r8 = -500000(0xfffffffffff85ee0, double:NaN)
            int r10 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r10 >= 0) goto L_0x0206
            r8 = 1
            goto L_0x0207
        L_0x0206:
            r8 = 0
        L_0x0207:
            if (r8 == 0) goto L_0x020d
            if (r39 != 0) goto L_0x020d
            r8 = 1
            goto L_0x020e
        L_0x020d:
            r8 = 0
        L_0x020e:
            if (r8 == 0) goto L_0x0238
            int r8 = r26.b(r27)
            if (r8 != 0) goto L_0x0218
            r8 = 0
            goto L_0x0234
        L_0x0218:
            com.google.android.exoplayer2.decoder.DecoderCounters r9 = r1.b
            int r10 = r9.i
            r11 = 1
            int r10 = r10 + r11
            r9.i = r10
            int r9 = r1.z
            int r9 = r9 + r8
            if (r7 == 0) goto L_0x022d
            com.google.android.exoplayer2.decoder.DecoderCounters r8 = r1.b
            int r10 = r8.f
            int r10 = r10 + r9
            r8.f = r10
            goto L_0x0230
        L_0x022d:
            r1.b((int) r9)
        L_0x0230:
            r26.I()
            r8 = 1
        L_0x0234:
            if (r8 == 0) goto L_0x0238
            r8 = 0
            return r8
        L_0x0238:
            boolean r8 = g(r5)
            if (r8 == 0) goto L_0x0242
            if (r39 != 0) goto L_0x0242
            r8 = 1
            goto L_0x0243
        L_0x0242:
            r8 = 0
        L_0x0243:
            if (r8 == 0) goto L_0x0260
            if (r7 == 0) goto L_0x024c
            r1.a((com.google.android.exoplayer2.mediacodec.MediaCodecAdapter) r0, (int) r4)
            r0 = 1
            goto L_0x025c
        L_0x024c:
            java.lang.String r2 = "dropVideoBuffer"
            com.google.android.exoplayer2.util.TraceUtil.a(r2)
            r2 = 0
            r0.a((int) r4, (boolean) r2)
            com.google.android.exoplayer2.util.TraceUtil.a()
            r0 = 1
            r1.b((int) r0)
        L_0x025c:
            r1.f(r5)
            return r0
        L_0x0260:
            int r7 = com.google.android.exoplayer2.util.Util.a
            r8 = 21
            if (r7 < r8) goto L_0x027d
            r7 = 50000(0xc350, double:2.47033E-319)
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 >= 0) goto L_0x02ae
            r34 = r26
            r35 = r15
            r37 = r2
            r39 = r40
            r34.a((long) r35, (long) r37, (com.google.android.exoplayer2.Format) r39)
        L_0x0278:
            r1.a((com.google.android.exoplayer2.mediacodec.MediaCodecAdapter) r0, (int) r4, (long) r2)
            goto L_0x00ff
        L_0x027d:
            r7 = 30000(0x7530, double:1.4822E-319)
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 >= 0) goto L_0x02ae
            r7 = 11000(0x2af8, double:5.4347E-320)
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 <= 0) goto L_0x029e
            r7 = 10000(0x2710, double:4.9407E-320)
            long r7 = r5 - r7
            r9 = 1000(0x3e8, double:4.94E-321)
            long r7 = r7 / r9
            java.lang.Thread.sleep(r7)     // Catch:{ InterruptedException -> 0x0294 }
            goto L_0x029e
        L_0x0294:
            r0 = move-exception
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            r0.interrupt()
            r2 = 0
            return r2
        L_0x029e:
            r34 = r26
            r35 = r15
            r37 = r2
            r39 = r40
            r34.a((long) r35, (long) r37, (com.google.android.exoplayer2.Format) r39)
        L_0x02a9:
            r1.b((com.google.android.exoplayer2.mediacodec.MediaCodecAdapter) r0, (int) r4)
            goto L_0x00ff
        L_0x02ae:
            r2 = 0
            return r2
        L_0x02b0:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.video.MediaCodecVideoRenderer.a(long, long, com.google.android.exoplayer2.mediacodec.MediaCodecAdapter, java.nio.ByteBuffer, int, int, int, long, boolean, boolean, com.google.android.exoplayer2.Format):boolean");
    }

    public final boolean a(com.google.android.exoplayer2.mediacodec.MediaCodecInfo mediaCodecInfo) {
        return this.n != null || b(mediaCodecInfo);
    }

    public final void b(DecoderInputBuffer decoderInputBuffer) {
        if (this.m) {
            ByteBuffer byteBuffer = (ByteBuffer) Assertions.b((Object) decoderInputBuffer.f);
            if (byteBuffer.remaining() >= 7) {
                byte b = byteBuffer.get();
                short s2 = byteBuffer.getShort();
                short s3 = byteBuffer.getShort();
                byte b2 = byteBuffer.get();
                byte b3 = byteBuffer.get();
                byteBuffer.position(0);
                if (b == -75 && s2 == 60 && s3 == 1 && b2 == 4 && b3 == 0) {
                    byte[] bArr = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bArr);
                    byteBuffer.position(0);
                    MediaCodecAdapter E2 = E();
                    Bundle bundle = new Bundle();
                    bundle.putByteArray("hdr10-plus-info", bArr);
                    E2.a(bundle);
                }
            }
        }
    }

    public final void d(long j2) {
        super.d(j2);
        if (!this.J) {
            this.z--;
        }
    }

    /* access modifiers changed from: protected */
    public final void e(long j2) {
        c(j2);
        Q();
        this.b.e++;
        P();
        d(j2);
    }

    public final void p() {
        super.p();
        this.x = 0;
        this.w = SystemClock.elapsedRealtime();
        this.B = SystemClock.elapsedRealtime() * 1000;
        this.C = 0;
        this.D = 0;
        VideoFrameReleaseHelper videoFrameReleaseHelper = this.g;
        videoFrameReleaseHelper.e = true;
        videoFrameReleaseHelper.a();
        videoFrameReleaseHelper.a(false);
    }

    public final void q() {
        this.v = -9223372036854775807L;
        S();
        int i2 = this.D;
        if (i2 != 0) {
            VideoRendererEventListener.EventDispatcher eventDispatcher = this.h;
            long j2 = this.C;
            if (eventDispatcher.a != null) {
                eventDispatcher.a.post(new VideoRendererEventListener$EventDispatcher$$Lambda$4(eventDispatcher, j2, i2));
            }
            this.C = 0;
            this.D = 0;
        }
        VideoFrameReleaseHelper videoFrameReleaseHelper = this.g;
        videoFrameReleaseHelper.e = false;
        videoFrameReleaseHelper.c();
        super.q();
    }

    public final void r() {
        this.I = null;
        O();
        this.p = false;
        VideoFrameReleaseHelper videoFrameReleaseHelper = this.g;
        if (videoFrameReleaseHelper.b != null) {
            if (videoFrameReleaseHelper.d != null) {
                VideoFrameReleaseHelper.DefaultDisplayListener defaultDisplayListener = videoFrameReleaseHelper.d;
                defaultDisplayListener.a.unregisterDisplayListener(defaultDisplayListener);
            }
            ((VideoFrameReleaseHelper.VSyncSampler) Assertions.b((Object) videoFrameReleaseHelper.c)).b.sendEmptyMessage(2);
        }
        this.a = null;
        try {
            super.r();
        } finally {
            this.h.a(this.b);
        }
    }

    public final void s() {
        try {
            super.s();
            DummySurface dummySurface = this.o;
            if (dummySurface != null) {
                if (this.n == dummySurface) {
                    this.n = null;
                }
                dummySurface.release();
                this.o = null;
            }
        } catch (Throwable th) {
            if (this.o != null) {
                Surface surface = this.n;
                DummySurface dummySurface2 = this.o;
                if (surface == dummySurface2) {
                    this.n = null;
                }
                dummySurface2.release();
                this.o = null;
            }
            throw th;
        }
    }

    public final String x() {
        return "MediaCodecVideoRenderer";
    }

    public final boolean y() {
        DummySurface dummySurface;
        if (super.y() && (this.r || (((dummySurface = this.o) != null && this.n == dummySurface) || E() == null || this.J))) {
            this.v = -9223372036854775807L;
            return true;
        } else if (this.v == -9223372036854775807L) {
            return false;
        } else {
            if (SystemClock.elapsedRealtime() < this.v) {
                return true;
            }
            this.v = -9223372036854775807L;
            return false;
        }
    }
}
