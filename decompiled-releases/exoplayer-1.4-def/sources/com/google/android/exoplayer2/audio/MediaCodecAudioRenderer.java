package com.google.android.exoplayer2.audio;

import android.content.Context;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.view.Surface;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Renderer;
import com.google.android.exoplayer2.RendererCapabilities$$CC;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.audio.AudioSink;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.decoder.DecoderReuseEvaluation;
import com.google.android.exoplayer2.mediacodec.MediaCodecAdapter;
import com.google.android.exoplayer2.mediacodec.MediaCodecInfo;
import com.google.android.exoplayer2.mediacodec.MediaCodecRenderer;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MediaClock;
import com.google.android.exoplayer2.util.MediaFormatUtil;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MediaCodecAudioRenderer extends MediaCodecRenderer implements MediaClock {
    boolean a;
    private final Context c;
    /* access modifiers changed from: private */
    public final AudioRendererEventListener.EventDispatcher d;
    private final AudioSink e;
    private int f;
    private boolean g;
    private Format h;
    private long i;
    private boolean j;
    private boolean k;
    /* access modifiers changed from: private */
    public Renderer.WakeupListener l;

    final class AudioSinkListener implements AudioSink.Listener {
        private AudioSinkListener() {
        }

        /* synthetic */ AudioSinkListener(MediaCodecAudioRenderer mediaCodecAudioRenderer, byte b) {
            this();
        }

        public final void a() {
            MediaCodecAudioRenderer.this.a = true;
        }

        public final void a(int i, long j, long j2) {
            AudioRendererEventListener.EventDispatcher a2 = MediaCodecAudioRenderer.this.d;
            if (a2.a != null) {
                a2.a.post(new AudioRendererEventListener$EventDispatcher$$Lambda$4(a2, i, j, j2));
            }
        }

        public final void a(long j) {
            AudioRendererEventListener.EventDispatcher a2 = MediaCodecAudioRenderer.this.d;
            if (a2.a != null) {
                a2.a.post(new AudioRendererEventListener$EventDispatcher$$Lambda$3(a2, j));
            }
        }

        public final void a(Exception exc) {
            Log.b("MediaCodecAudioRenderer", "Audio sink error", exc);
            AudioRendererEventListener.EventDispatcher a2 = MediaCodecAudioRenderer.this.d;
            if (a2.a != null) {
                a2.a.post(new AudioRendererEventListener$EventDispatcher$$Lambda$8(a2, exc));
            }
        }

        public final void a(boolean z) {
            AudioRendererEventListener.EventDispatcher a2 = MediaCodecAudioRenderer.this.d;
            if (a2.a != null) {
                a2.a.post(new AudioRendererEventListener$EventDispatcher$$Lambda$7(a2, z));
            }
        }

        public final void b() {
            if (MediaCodecAudioRenderer.this.l != null) {
                MediaCodecAudioRenderer.this.l.a();
            }
        }

        public final void b(long j) {
            if (MediaCodecAudioRenderer.this.l != null) {
                MediaCodecAudioRenderer.this.l.a(j);
            }
        }
    }

    private MediaCodecAudioRenderer(Context context, MediaCodecAdapter.Factory factory, MediaCodecSelector mediaCodecSelector, Handler handler, AudioRendererEventListener audioRendererEventListener, AudioSink audioSink) {
        super(1, factory, mediaCodecSelector, false, 44100.0f);
        this.c = context.getApplicationContext();
        this.e = audioSink;
        this.d = new AudioRendererEventListener.EventDispatcher(handler, audioRendererEventListener);
        audioSink.a((AudioSink.Listener) new AudioSinkListener(this, (byte) 0));
    }

    public MediaCodecAudioRenderer(Context context, MediaCodecSelector mediaCodecSelector, Handler handler, AudioRendererEventListener audioRendererEventListener, AudioSink audioSink) {
        this(context, MediaCodecAdapter.Factory.a, mediaCodecSelector, handler, audioRendererEventListener, audioSink);
    }

    private void N() {
        long a2 = this.e.a(z());
        if (a2 != Long.MIN_VALUE) {
            if (!this.a) {
                a2 = Math.max(this.i, a2);
            }
            this.i = a2;
            this.a = false;
        }
    }

    private int a(MediaCodecInfo mediaCodecInfo, Format format) {
        if (!"OMX.google.raw.decoder".equals(mediaCodecInfo.a) || Util.a >= 24 || (Util.a == 23 && Util.b(this.c))) {
            return format.m;
        }
        return -1;
    }

    public final void A() {
        super.A();
        this.e.b();
    }

    public final void B() {
        try {
            this.e.c();
        } catch (AudioSink.WriteException e2) {
            throw a((Throwable) e2, e2.b, e2.a);
        }
    }

    public final float a(float f2, Format[] formatArr) {
        int i2 = -1;
        for (Format format : formatArr) {
            int i3 = format.x;
            if (i3 != -1) {
                i2 = Math.max(i2, i3);
            }
        }
        if (i2 == -1) {
            return -1.0f;
        }
        return ((float) i2) * f2;
    }

    public final int a(MediaCodecSelector mediaCodecSelector, Format format) {
        if (!MimeTypes.a(format.l)) {
            return RendererCapabilities$$CC.a(0);
        }
        int i2 = Util.a >= 21 ? 32 : 0;
        boolean z = format.C != null;
        boolean c2 = c(format);
        int i3 = 8;
        int i4 = 4;
        if (c2 && this.e.a(format) && (!z || MediaCodecUtil.a() != null)) {
            return RendererCapabilities$$CC.a(4, 8, i2);
        }
        if ("audio/raw".equals(format.l) && !this.e.a(format)) {
            return RendererCapabilities$$CC.a(1);
        }
        if (!this.e.a(Util.b(2, format.w, format.x))) {
            return RendererCapabilities$$CC.a(1);
        }
        List a2 = a(mediaCodecSelector, format, false);
        if (a2.isEmpty()) {
            return RendererCapabilities$$CC.a(1);
        }
        if (!c2) {
            return RendererCapabilities$$CC.a(2);
        }
        MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) a2.get(0);
        boolean a3 = mediaCodecInfo.a(format);
        if (a3 && mediaCodecInfo.b(format)) {
            i3 = 16;
        }
        if (!a3) {
            i4 = 3;
        }
        return RendererCapabilities$$CC.a(i4, i3, i2);
    }

    public final DecoderReuseEvaluation a(FormatHolder formatHolder) {
        DecoderReuseEvaluation a2 = super.a(formatHolder);
        AudioRendererEventListener.EventDispatcher eventDispatcher = this.d;
        Format format = formatHolder.b;
        if (eventDispatcher.a != null) {
            eventDispatcher.a.post(new AudioRendererEventListener$EventDispatcher$$Lambda$2(eventDispatcher, format, a2));
        }
        return a2;
    }

    public final DecoderReuseEvaluation a(MediaCodecInfo mediaCodecInfo, Format format, Format format2) {
        DecoderReuseEvaluation a2 = mediaCodecInfo.a(format, format2);
        int i2 = a2.b;
        if (a(mediaCodecInfo, format2) > this.f) {
            i2 |= 64;
        }
        int i3 = i2;
        return new DecoderReuseEvaluation(mediaCodecInfo.a, format, format2, i3 != 0 ? 0 : a2.a, i3);
    }

    public final MediaCodecAdapter.Configuration a(MediaCodecInfo mediaCodecInfo, Format format, MediaCrypto mediaCrypto, float f2) {
        Format[] u = u();
        int a2 = a(mediaCodecInfo, format);
        boolean z = false;
        if (u.length != 1) {
            for (Format format2 : u) {
                if (mediaCodecInfo.a(format, format2).a != 0) {
                    a2 = Math.max(a2, a(mediaCodecInfo, format2));
                }
            }
        }
        this.f = a2;
        this.g = Util.a < 24 && "OMX.SEC.aac.dec".equals(mediaCodecInfo.a) && "samsung".equals(Util.c) && (Util.b.startsWith("zeroflte") || Util.b.startsWith("herolte") || Util.b.startsWith("heroqlte"));
        String str = mediaCodecInfo.c;
        int i2 = this.f;
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", str);
        mediaFormat.setInteger("channel-count", format.w);
        mediaFormat.setInteger("sample-rate", format.x);
        MediaFormatUtil.a(mediaFormat, format.n);
        MediaFormatUtil.a(mediaFormat, "max-input-size", i2);
        if (Util.a >= 23) {
            mediaFormat.setInteger("priority", 0);
            if (f2 != -1.0f) {
                if (!(Util.a == 23 && ("ZTE B2017G".equals(Util.d) || "AXON 7 mini".equals(Util.d)))) {
                    mediaFormat.setFloat("operating-rate", f2);
                }
            }
        }
        if (Util.a <= 28 && "audio/ac4".equals(format.l)) {
            mediaFormat.setInteger("ac4-is-sync", 1);
        }
        if (Util.a >= 24 && this.e.b(Util.b(4, format.w, format.x)) == 2) {
            mediaFormat.setInteger("pcm-encoding", 4);
        }
        if ("audio/raw".equals(mediaCodecInfo.b) && !"audio/raw".equals(format.l)) {
            z = true;
        }
        if (!z) {
            format = null;
        }
        this.h = format;
        return new MediaCodecAdapter.Configuration(mediaCodecInfo, mediaFormat, (Surface) null, mediaCrypto);
    }

    public final List a(MediaCodecSelector mediaCodecSelector, Format format, boolean z) {
        MediaCodecInfo a2;
        String str = format.l;
        if (str == null) {
            return Collections.emptyList();
        }
        if (this.e.a(format) && (a2 = MediaCodecUtil.a()) != null) {
            return Collections.singletonList(a2);
        }
        List a3 = MediaCodecUtil.a(mediaCodecSelector.a(str, z, false), format);
        if ("audio/eac3-joc".equals(str)) {
            ArrayList arrayList = new ArrayList(a3);
            arrayList.addAll(mediaCodecSelector.a("audio/eac3", z, false));
            a3 = arrayList;
        }
        return Collections.unmodifiableList(a3);
    }

    public final void a(int i2, Object obj) {
        switch (i2) {
            case 2:
                this.e.a(((Float) obj).floatValue());
                return;
            case 3:
                this.e.a((AudioAttributes) obj);
                return;
            case 5:
                this.e.a((AuxEffectInfo) obj);
                return;
            case 101:
                this.e.b(((Boolean) obj).booleanValue());
                return;
            case 102:
                this.e.a(((Integer) obj).intValue());
                return;
            case 103:
                this.l = (Renderer.WakeupListener) obj;
                return;
            default:
                super.a(i2, obj);
                return;
        }
    }

    public final void a(long j2, boolean z) {
        super.a(j2, z);
        this.e.j();
        this.i = j2;
        this.j = true;
        this.a = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0086 A[LOOP:0: B:26:0x0082->B:28:0x0086, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.google.android.exoplayer2.Format r5, android.media.MediaFormat r6) {
        /*
            r4 = this;
            com.google.android.exoplayer2.Format r0 = r4.h
            r1 = 0
            if (r0 == 0) goto L_0x0008
            r5 = r0
            goto L_0x008c
        L_0x0008:
            com.google.android.exoplayer2.mediacodec.MediaCodecAdapter r0 = r4.E()
            if (r0 != 0) goto L_0x0010
            goto L_0x008c
        L_0x0010:
            java.lang.String r0 = r5.l
            java.lang.String r2 = "audio/raw"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x001d
        L_0x001a:
            int r0 = r5.y
            goto L_0x004b
        L_0x001d:
            int r0 = com.google.android.exoplayer2.util.Util.a
            r3 = 24
            if (r0 < r3) goto L_0x0030
            java.lang.String r0 = "pcm-encoding"
            boolean r3 = r6.containsKey(r0)
            if (r3 == 0) goto L_0x0030
            int r0 = r6.getInteger(r0)
            goto L_0x004b
        L_0x0030:
            java.lang.String r0 = "v-bits-per-sample"
            boolean r3 = r6.containsKey(r0)
            if (r3 == 0) goto L_0x0041
            int r0 = r6.getInteger(r0)
            int r0 = com.google.android.exoplayer2.util.Util.b((int) r0)
            goto L_0x004b
        L_0x0041:
            java.lang.String r0 = r5.l
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x004a
            goto L_0x001a
        L_0x004a:
            r0 = 2
        L_0x004b:
            com.google.android.exoplayer2.Format$Builder r3 = new com.google.android.exoplayer2.Format$Builder
            r3.<init>()
            r3.k = r2
            r3.z = r0
            int r0 = r5.z
            r3.A = r0
            int r0 = r5.A
            r3.B = r0
            java.lang.String r0 = "channel-count"
            int r0 = r6.getInteger(r0)
            r3.x = r0
            java.lang.String r0 = "sample-rate"
            int r6 = r6.getInteger(r0)
            r3.y = r6
            com.google.android.exoplayer2.Format r6 = r3.a()
            boolean r0 = r4.g
            if (r0 == 0) goto L_0x008b
            int r0 = r6.w
            r2 = 6
            if (r0 != r2) goto L_0x008b
            int r0 = r5.w
            if (r0 >= r2) goto L_0x008b
            int r0 = r5.w
            int[] r1 = new int[r0]
            r0 = 0
        L_0x0082:
            int r2 = r5.w
            if (r0 >= r2) goto L_0x008b
            r1[r0] = r0
            int r0 = r0 + 1
            goto L_0x0082
        L_0x008b:
            r5 = r6
        L_0x008c:
            com.google.android.exoplayer2.audio.AudioSink r6 = r4.e     // Catch:{ ConfigurationException -> 0x0092 }
            r6.a(r5, r1)     // Catch:{ ConfigurationException -> 0x0092 }
            return
        L_0x0092:
            r5 = move-exception
            com.google.android.exoplayer2.Format r6 = r5.a
            com.google.android.exoplayer2.ExoPlaybackException r5 = r4.a((java.lang.Throwable) r5, (com.google.android.exoplayer2.Format) r6)
            goto L_0x009b
        L_0x009a:
            throw r5
        L_0x009b:
            goto L_0x009a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.audio.MediaCodecAudioRenderer.a(com.google.android.exoplayer2.Format, android.media.MediaFormat):void");
    }

    public final void a(PlaybackParameters playbackParameters) {
        this.e.a(playbackParameters);
    }

    public final void a(DecoderInputBuffer decoderInputBuffer) {
        if (this.j && !decoderInputBuffer.d_()) {
            if (Math.abs(decoderInputBuffer.e - this.i) > 500000) {
                this.i = decoderInputBuffer.e;
            }
            this.j = false;
        }
    }

    public final void a(Exception exc) {
        Log.b("MediaCodecAudioRenderer", "Audio codec error", exc);
        AudioRendererEventListener.EventDispatcher eventDispatcher = this.d;
        if (eventDispatcher.a != null) {
            eventDispatcher.a.post(new AudioRendererEventListener$EventDispatcher$$Lambda$9(eventDispatcher, exc));
        }
    }

    public final void a(String str) {
        AudioRendererEventListener.EventDispatcher eventDispatcher = this.d;
        if (eventDispatcher.a != null) {
            eventDispatcher.a.post(new AudioRendererEventListener$EventDispatcher$$Lambda$5(eventDispatcher, str));
        }
    }

    public final void a(String str, long j2, long j3) {
        AudioRendererEventListener.EventDispatcher eventDispatcher = this.d;
        if (eventDispatcher.a != null) {
            eventDispatcher.a.post(new AudioRendererEventListener$EventDispatcher$$Lambda$1(eventDispatcher, str, j2, j3));
        }
    }

    public final void a(boolean z, boolean z2) {
        super.a(z, z2);
        AudioRendererEventListener.EventDispatcher eventDispatcher = this.d;
        DecoderCounters decoderCounters = this.b;
        if (eventDispatcher.a != null) {
            eventDispatcher.a.post(new AudioRendererEventListener$EventDispatcher$$Lambda$0(eventDispatcher, decoderCounters));
        }
        if (v().b) {
            this.e.g();
        } else {
            this.e.h();
        }
    }

    public final boolean a(long j2, long j3, MediaCodecAdapter mediaCodecAdapter, ByteBuffer byteBuffer, int i2, int i3, int i4, long j4, boolean z, boolean z2, Format format) {
        Assertions.b((Object) byteBuffer);
        if (this.h != null && (i3 & 2) != 0) {
            ((MediaCodecAdapter) Assertions.b((Object) mediaCodecAdapter)).a(i2, false);
            return true;
        } else if (z) {
            if (mediaCodecAdapter != null) {
                mediaCodecAdapter.a(i2, false);
            }
            this.b.f += i4;
            this.e.b();
            return true;
        } else {
            try {
                if (!this.e.a(byteBuffer, j4, i4)) {
                    return false;
                }
                if (mediaCodecAdapter != null) {
                    mediaCodecAdapter.a(i2, false);
                }
                this.b.e += i4;
                return true;
            } catch (AudioSink.InitializationException e2) {
                throw a((Throwable) e2, e2.b, e2.a);
            } catch (AudioSink.WriteException e3) {
                throw a((Throwable) e3, format, e3.a);
            }
        }
    }

    public final long a_() {
        if (b_() == 2) {
            N();
        }
        return this.i;
    }

    public final boolean b(Format format) {
        return this.e.a(format);
    }

    public final MediaClock c() {
        return this;
    }

    public final PlaybackParameters d() {
        return this.e.f();
    }

    public final void p() {
        super.p();
        this.e.a();
    }

    public final void q() {
        N();
        this.e.i();
        super.q();
    }

    public final void r() {
        this.k = true;
        try {
            this.e.j();
            try {
                super.r();
            } finally {
                this.d.a(this.b);
            }
        } catch (Throwable th) {
            super.r();
            throw th;
        } finally {
            this.d.a(this.b);
        }
    }

    public final void s() {
        try {
            super.s();
        } finally {
            if (this.k) {
                this.k = false;
                this.e.k();
            }
        }
    }

    public final String x() {
        return "MediaCodecAudioRenderer";
    }

    public final boolean y() {
        return this.e.e() || super.y();
    }

    public final boolean z() {
        return super.z() && this.e.d();
    }
}
