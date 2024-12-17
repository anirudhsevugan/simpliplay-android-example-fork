package com.google.android.exoplayer2.audio;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Pair;
import com.dreamers.exoplayercore.repack.cT;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.android.exoplayer2.audio.AudioSink;
import com.google.android.exoplayer2.audio.AudioTrackPositionTracker;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.google.appinventor.components.runtime.Form;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public final class DefaultAudioSink implements AudioSink {
    public static boolean a = false;
    private int A;
    private boolean B;
    private boolean C;
    private long D;
    private float E;
    private AudioProcessor[] F;
    private ByteBuffer[] G;
    private ByteBuffer H;
    private int I;
    private ByteBuffer J;
    private byte[] K;
    private int L;
    private int M;
    private boolean N;
    private boolean O;
    /* access modifiers changed from: private */
    public boolean P;
    private boolean Q;
    private int R;
    private AuxEffectInfo S;
    private boolean T;
    /* access modifiers changed from: private */
    public long U;
    private boolean V;
    private boolean W;
    private final AudioCapabilities b;
    private final AudioProcessorChain c;
    private final ChannelMappingAudioProcessor d;
    private final TrimmingAudioProcessor e;
    private final AudioProcessor[] f;
    private final AudioProcessor[] g;
    /* access modifiers changed from: private */
    public final ConditionVariable h = new ConditionVariable(true);
    private final AudioTrackPositionTracker i = new AudioTrackPositionTracker(new PositionTrackerListener(this, (byte) 0));
    private final ArrayDeque j;
    private StreamEventCallbackV29 k;
    private final PendingExceptionHolder l;
    private final PendingExceptionHolder m;
    /* access modifiers changed from: private */
    public AudioSink.Listener n;
    private Configuration o;
    private Configuration p;
    /* access modifiers changed from: private */
    public AudioTrack q;
    private AudioAttributes r;
    private MediaPositionParameters s;
    private MediaPositionParameters t;
    private ByteBuffer u;
    private int v;
    private long w;
    private long x;
    private long y;
    private long z;

    public interface AudioProcessorChain {
        long a(long j);

        PlaybackParameters a(PlaybackParameters playbackParameters);

        boolean a(boolean z);

        AudioProcessor[] a();

        long b();
    }

    final class Configuration {
        public final Format a;
        public final int b;
        public final int c;
        public final int d;
        public final int e;
        public final int f;
        public final int g;
        public final int h;
        public final AudioProcessor[] i;

        public Configuration(Format format, int i2, int i3, int i4, int i5, int i6, int i7, boolean z, AudioProcessor[] audioProcessorArr) {
            int i8;
            this.a = format;
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.e = i5;
            this.f = i6;
            this.g = i7;
            this.i = audioProcessorArr;
            long j = 250000;
            switch (i3) {
                case 0:
                    int minBufferSize = AudioTrack.getMinBufferSize(i5, i6, i7);
                    Assertions.b(minBufferSize != -2);
                    i8 = Util.a(minBufferSize << 2, ((int) b(250000)) * i4, Math.max(minBufferSize, ((int) b(750000)) * i4));
                    break;
                case 1:
                    j = 50000000;
                    break;
                case 2:
                    break;
                default:
                    throw new IllegalStateException();
            }
            i8 = c(j);
            this.h = i8;
        }

        private static AudioAttributes a(AudioAttributes audioAttributes, boolean z) {
            return z ? b() : audioAttributes.a();
        }

        private long b(long j) {
            return (j * ((long) this.e)) / 1000000;
        }

        private static AudioAttributes b() {
            return new AudioAttributes.Builder().setContentType(3).setFlags(16).setUsage(1).build();
        }

        private int c(long j) {
            int b2 = DefaultAudioSink.b(this.g);
            if (this.g == 5) {
                b2 <<= 1;
            }
            return (int) ((j * ((long) b2)) / 1000000);
        }

        public final long a(long j) {
            return (j * 1000000) / ((long) this.e);
        }

        public final AudioTrack a(boolean z, AudioAttributes audioAttributes, int i2) {
            AudioTrack audioTrack;
            try {
                if (Util.a >= 29) {
                    audioTrack = new AudioTrack.Builder().setAudioAttributes(a(audioAttributes, z)).setAudioFormat(new AudioFormat.Builder().setSampleRate(this.e).setChannelMask(this.f).setEncoding(this.g).build()).setTransferMode(1).setBufferSizeInBytes(this.h).setSessionId(i2).setOffloadedPlayback(this.c == 1).build();
                } else if (Util.a >= 21) {
                    audioTrack = new AudioTrack(a(audioAttributes, z), new AudioFormat.Builder().setSampleRate(this.e).setChannelMask(this.f).setEncoding(this.g).build(), this.h, 1, i2);
                } else {
                    int f2 = Util.f(audioAttributes.b);
                    audioTrack = i2 == 0 ? new AudioTrack(f2, this.e, this.f, this.g, this.h, 1) : new AudioTrack(f2, this.e, this.f, this.g, this.h, 1, i2);
                }
                int state = audioTrack.getState();
                if (state == 1) {
                    return audioTrack;
                }
                try {
                    audioTrack.release();
                } catch (Exception e2) {
                }
                throw new AudioSink.InitializationException(state, this.e, this.f, this.h, this.a, a(), (Exception) null);
            } catch (IllegalArgumentException | UnsupportedOperationException e3) {
                throw new AudioSink.InitializationException(0, this.e, this.f, this.h, this.a, a(), e3);
            }
        }

        public final boolean a() {
            return this.c == 1;
        }
    }

    public class DefaultAudioProcessorChain implements AudioProcessorChain {
        private final AudioProcessor[] a;
        private final SilenceSkippingAudioProcessor b;
        private final SonicAudioProcessor c;

        public DefaultAudioProcessorChain(AudioProcessor... audioProcessorArr) {
            this(audioProcessorArr, new SilenceSkippingAudioProcessor(), new SonicAudioProcessor());
        }

        private DefaultAudioProcessorChain(AudioProcessor[] audioProcessorArr, SilenceSkippingAudioProcessor silenceSkippingAudioProcessor, SonicAudioProcessor sonicAudioProcessor) {
            AudioProcessor[] audioProcessorArr2 = new AudioProcessor[(audioProcessorArr.length + 2)];
            this.a = audioProcessorArr2;
            System.arraycopy(audioProcessorArr, 0, audioProcessorArr2, 0, audioProcessorArr.length);
            this.b = silenceSkippingAudioProcessor;
            this.c = sonicAudioProcessor;
            audioProcessorArr2[audioProcessorArr.length] = silenceSkippingAudioProcessor;
            audioProcessorArr2[audioProcessorArr.length + 1] = sonicAudioProcessor;
        }

        public final long a(long j) {
            SonicAudioProcessor sonicAudioProcessor = this.c;
            if (sonicAudioProcessor.i >= 1024) {
                long j2 = sonicAudioProcessor.h;
                Sonic sonic = (Sonic) Assertions.b((Object) sonicAudioProcessor.g);
                long j3 = j2 - ((long) ((sonic.g * sonic.a) << 1));
                if (sonicAudioProcessor.e.b == sonicAudioProcessor.d.b) {
                    return Util.b(j, j3, sonicAudioProcessor.i);
                }
                return Util.b(j, j3 * ((long) sonicAudioProcessor.e.b), sonicAudioProcessor.i * ((long) sonicAudioProcessor.d.b));
            }
            double d = (double) sonicAudioProcessor.b;
            double d2 = (double) j;
            Double.isNaN(d);
            Double.isNaN(d2);
            return (long) (d * d2);
        }

        public final PlaybackParameters a(PlaybackParameters playbackParameters) {
            SonicAudioProcessor sonicAudioProcessor = this.c;
            float f = playbackParameters.b;
            if (sonicAudioProcessor.b != f) {
                sonicAudioProcessor.b = f;
                sonicAudioProcessor.f = true;
            }
            SonicAudioProcessor sonicAudioProcessor2 = this.c;
            float f2 = playbackParameters.c;
            if (sonicAudioProcessor2.c != f2) {
                sonicAudioProcessor2.c = f2;
                sonicAudioProcessor2.f = true;
            }
            return playbackParameters;
        }

        public final boolean a(boolean z) {
            this.b.d = z;
            return z;
        }

        public final AudioProcessor[] a() {
            return this.a;
        }

        public final long b() {
            return this.b.e;
        }
    }

    final class MediaPositionParameters {
        public final PlaybackParameters a;
        public final boolean b;
        public final long c;
        public final long d;

        private MediaPositionParameters(PlaybackParameters playbackParameters, boolean z, long j, long j2) {
            this.a = playbackParameters;
            this.b = z;
            this.c = j;
            this.d = j2;
        }

        /* synthetic */ MediaPositionParameters(PlaybackParameters playbackParameters, boolean z, long j, long j2, byte b2) {
            this(playbackParameters, z, j, j2);
        }
    }

    final class PendingExceptionHolder {
        Exception a;
        private long b;

        public final void a(Exception exc) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (this.a == null) {
                this.a = exc;
                this.b = 100 + elapsedRealtime;
            }
            if (elapsedRealtime >= this.b) {
                Exception exc2 = this.a;
                if (exc2 != exc) {
                    cT.a(exc2, exc);
                }
                Exception exc3 = this.a;
                this.a = null;
                throw exc3;
            }
        }
    }

    final class PositionTrackerListener implements AudioTrackPositionTracker.Listener {
        private PositionTrackerListener() {
        }

        /* synthetic */ PositionTrackerListener(DefaultAudioSink defaultAudioSink, byte b) {
            this();
        }

        public final void a(int i, long j) {
            if (DefaultAudioSink.this.n != null) {
                DefaultAudioSink.this.n.a(i, j, SystemClock.elapsedRealtime() - DefaultAudioSink.this.U);
            }
        }

        public final void a(long j) {
            if (DefaultAudioSink.this.n != null) {
                DefaultAudioSink.this.n.a(j);
            }
        }

        public final void a(long j, long j2, long j3, long j4) {
            long e = DefaultAudioSink.this.v();
            String sb = new StringBuilder(182).append("Spurious audio timestamp (frame position mismatch): ").append(j).append(", ").append(j2).append(", ").append(j3).append(", ").append(j4).append(", ").append(e).append(", ").append(DefaultAudioSink.this.w()).toString();
            boolean z = DefaultAudioSink.a;
            Log.c("DefaultAudioSink", sb);
        }

        public final void b(long j) {
            Log.c("DefaultAudioSink", new StringBuilder(61).append("Ignoring impossibly large audio latency: ").append(j).toString());
        }

        public final void b(long j, long j2, long j3, long j4) {
            long e = DefaultAudioSink.this.v();
            String sb = new StringBuilder(180).append("Spurious audio timestamp (system clock mismatch): ").append(j).append(", ").append(j2).append(", ").append(j3).append(", ").append(j4).append(", ").append(e).append(", ").append(DefaultAudioSink.this.w()).toString();
            boolean z = DefaultAudioSink.a;
            Log.c("DefaultAudioSink", sb);
        }
    }

    final class StreamEventCallbackV29 {
        final Handler a = new Handler();
        final AudioTrack.StreamEventCallback b = new AudioTrack.StreamEventCallback() {
            public void onDataRequest(AudioTrack audioTrack, int i) {
                Assertions.b(audioTrack == DefaultAudioSink.this.q);
                if (DefaultAudioSink.this.n != null && DefaultAudioSink.this.P) {
                    DefaultAudioSink.this.n.b();
                }
            }

            public void onTearDown(AudioTrack audioTrack) {
                Assertions.b(audioTrack == DefaultAudioSink.this.q);
                if (DefaultAudioSink.this.n != null && DefaultAudioSink.this.P) {
                    DefaultAudioSink.this.n.b();
                }
            }
        };

        public StreamEventCallbackV29() {
        }

        public final void a(AudioTrack audioTrack) {
            audioTrack.unregisterStreamEventCallback(this.b);
            this.a.removeCallbacksAndMessages((Object) null);
        }
    }

    public DefaultAudioSink(AudioCapabilities audioCapabilities, AudioProcessorChain audioProcessorChain) {
        this.b = audioCapabilities;
        this.c = (AudioProcessorChain) Assertions.b((Object) audioProcessorChain);
        int i2 = Util.a;
        int i3 = Util.a;
        int i4 = Util.a;
        ChannelMappingAudioProcessor channelMappingAudioProcessor = new ChannelMappingAudioProcessor();
        this.d = channelMappingAudioProcessor;
        TrimmingAudioProcessor trimmingAudioProcessor = new TrimmingAudioProcessor();
        this.e = trimmingAudioProcessor;
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, new BaseAudioProcessor[]{new ResamplingAudioProcessor(), channelMappingAudioProcessor, trimmingAudioProcessor});
        Collections.addAll(arrayList, audioProcessorChain.a());
        this.f = (AudioProcessor[]) arrayList.toArray(new AudioProcessor[0]);
        this.g = new AudioProcessor[]{new FloatResamplingAudioProcessor()};
        this.E = 1.0f;
        this.r = AudioAttributes.a;
        this.R = 0;
        this.S = new AuxEffectInfo();
        this.t = new MediaPositionParameters(PlaybackParameters.a, false, 0, 0, (byte) 0);
        PlaybackParameters playbackParameters = PlaybackParameters.a;
        this.M = -1;
        this.F = new AudioProcessor[0];
        this.G = new ByteBuffer[0];
        this.j = new ArrayDeque();
        this.l = new PendingExceptionHolder();
        this.m = new PendingExceptionHolder();
    }

    private int a(AudioTrack audioTrack, ByteBuffer byteBuffer, int i2, long j2) {
        if (Util.a >= 26) {
            return audioTrack.write(byteBuffer, i2, 1, j2 * 1000);
        }
        if (this.u == null) {
            ByteBuffer allocate = ByteBuffer.allocate(16);
            this.u = allocate;
            allocate.order(ByteOrder.BIG_ENDIAN);
            this.u.putInt(1431633921);
        }
        if (this.v == 0) {
            this.u.putInt(4, i2);
            this.u.putLong(8, j2 * 1000);
            this.u.position(0);
            this.v = i2;
        }
        int remaining = this.u.remaining();
        if (remaining > 0) {
            int write = audioTrack.write(this.u, remaining, 1);
            if (write < 0) {
                this.v = 0;
                return write;
            } else if (write < remaining) {
                return 0;
            }
        }
        int write2 = audioTrack.write(byteBuffer, i2, 1);
        if (write2 < 0) {
            this.v = 0;
            return write2;
        }
        this.v -= write2;
        return write2;
    }

    private void a(long j2) {
        ByteBuffer byteBuffer;
        int length = this.F.length;
        int i2 = length;
        while (i2 >= 0) {
            if (i2 > 0) {
                byteBuffer = this.G[i2 - 1];
            } else {
                byteBuffer = this.H;
                if (byteBuffer == null) {
                    byteBuffer = AudioProcessor.a;
                }
            }
            if (i2 == length) {
                a(byteBuffer, j2);
            } else {
                AudioProcessor audioProcessor = this.F[i2];
                if (i2 > this.M) {
                    audioProcessor.a(byteBuffer);
                }
                ByteBuffer c2 = audioProcessor.c();
                this.G[i2] = c2;
                if (c2.hasRemaining()) {
                    i2++;
                }
            }
            if (!byteBuffer.hasRemaining()) {
                i2--;
            } else {
                return;
            }
        }
    }

    private void a(PlaybackParameters playbackParameters, boolean z2) {
        MediaPositionParameters s2 = s();
        if (!playbackParameters.equals(s2.a) || z2 != s2.b) {
            MediaPositionParameters mediaPositionParameters = new MediaPositionParameters(playbackParameters, z2, -9223372036854775807L, -9223372036854775807L, (byte) 0);
            if (u()) {
                this.s = mediaPositionParameters;
            } else {
                this.t = mediaPositionParameters;
            }
        }
    }

    private void a(ByteBuffer byteBuffer, long j2) {
        int i2;
        if (byteBuffer.hasRemaining()) {
            ByteBuffer byteBuffer2 = this.J;
            boolean z2 = true;
            if (byteBuffer2 != null) {
                Assertions.a(byteBuffer2 == byteBuffer);
            } else {
                this.J = byteBuffer;
                if (Util.a < 21) {
                    int remaining = byteBuffer.remaining();
                    byte[] bArr = this.K;
                    if (bArr == null || bArr.length < remaining) {
                        this.K = new byte[remaining];
                    }
                    int position = byteBuffer.position();
                    byteBuffer.get(this.K, 0, remaining);
                    byteBuffer.position(position);
                    this.L = 0;
                }
            }
            int remaining2 = byteBuffer.remaining();
            if (Util.a < 21) {
                int a2 = this.i.a(this.y);
                if (a2 > 0) {
                    i2 = this.q.write(this.K, this.L, Math.min(remaining2, a2));
                    if (i2 > 0) {
                        this.L += i2;
                        byteBuffer.position(byteBuffer.position() + i2);
                    }
                } else {
                    i2 = 0;
                }
            } else if (this.T) {
                Assertions.b(j2 != -9223372036854775807L);
                i2 = a(this.q, byteBuffer, remaining2, j2);
            } else {
                i2 = this.q.write(byteBuffer, remaining2, 1);
            }
            this.U = SystemClock.elapsedRealtime();
            if (i2 < 0) {
                boolean c2 = c(i2);
                if (c2) {
                    o();
                }
                AudioSink.WriteException writeException = new AudioSink.WriteException(i2, this.p.a, c2);
                AudioSink.Listener listener = this.n;
                if (listener != null) {
                    listener.a((Exception) writeException);
                }
                if (!writeException.a) {
                    this.m.a(writeException);
                    return;
                }
                throw writeException;
            }
            this.m.a = null;
            if (a(this.q)) {
                long j3 = this.z;
                if (j3 > 0) {
                    this.W = false;
                }
                if (this.P && this.n != null && i2 < remaining2 && !this.W) {
                    this.n.b(this.i.b(j3));
                }
            }
            if (this.p.c == 0) {
                this.y += (long) i2;
            }
            if (i2 == remaining2) {
                if (this.p.c != 0) {
                    if (byteBuffer != this.H) {
                        z2 = false;
                    }
                    Assertions.b(z2);
                    this.z += (long) (this.A * this.I);
                }
                this.J = null;
            }
        }
    }

    private static boolean a(AudioTrack audioTrack) {
        return Util.a >= 29 && audioTrack.isOffloadedPlayback();
    }

    private static boolean a(Format format, AudioCapabilities audioCapabilities) {
        return b(format, audioCapabilities) != null;
    }

    static /* synthetic */ int b(int i2) {
        switch (i2) {
            case 5:
                return 80000;
            case 6:
            case 18:
                return 768000;
            case 7:
                return 192000;
            case 8:
                return 2250000;
            case 9:
                return 40000;
            case 10:
                return Form.MAX_PERMISSION_NONCE;
            case 11:
                return 16000;
            case 12:
                return 7000;
            case 14:
                return 3062500;
            case 15:
                return 8000;
            case 16:
                return 256000;
            case 17:
                return 336000;
            default:
                throw new IllegalArgumentException();
        }
    }

    private static Pair b(Format format, AudioCapabilities audioCapabilities) {
        if (audioCapabilities == null) {
            return null;
        }
        int d2 = MimeTypes.d((String) Assertions.b((Object) format.l), format.i);
        int i2 = 6;
        if (!(d2 == 5 || d2 == 6 || d2 == 18 || d2 == 17 || d2 == 7 || d2 == 8 || d2 == 14)) {
            return null;
        }
        if (d2 == 18 && !audioCapabilities.a(18)) {
            d2 = 6;
        } else if (d2 == 8 && !audioCapabilities.a(8)) {
            d2 = 7;
        }
        if (!audioCapabilities.a(d2)) {
            return null;
        }
        if (d2 != 18) {
            i2 = format.w;
            if (i2 > audioCapabilities.a) {
                return null;
            }
        } else if (Util.a >= 29 && (i2 = d(format.x)) == 0) {
            Log.c("DefaultAudioSink", "E-AC3 JOC encoding supported but no channel count supported");
            return null;
        }
        int e2 = e(i2);
        if (e2 == 0) {
            return null;
        }
        return Pair.create(Integer.valueOf(d2), Integer.valueOf(e2));
    }

    private void b(long j2) {
        PlaybackParameters a2 = t() ? this.c.a(s().a) : PlaybackParameters.a;
        boolean a3 = t() ? this.c.a(s().b) : false;
        this.j.add(new MediaPositionParameters(a2, a3, Math.max(0, j2), this.p.a(w()), (byte) 0));
        l();
        AudioSink.Listener listener = this.n;
        if (listener != null) {
            listener.a(a3);
        }
    }

    private static boolean c(int i2) {
        return (Util.a >= 24 && i2 == -6) || i2 == -32;
    }

    private static int d(int i2) {
        AudioAttributes build = new AudioAttributes.Builder().setUsage(1).setContentType(3).build();
        for (int i3 = 8; i3 > 0; i3--) {
            if (AudioTrack.isDirectPlaybackSupported(new AudioFormat.Builder().setEncoding(18).setSampleRate(i2).setChannelMask(Util.e(i3)).build(), build)) {
                return i3;
            }
        }
        return 0;
    }

    private static int e(int i2) {
        if (Util.a <= 28) {
            if (i2 == 7) {
                i2 = 8;
            } else if (i2 == 3 || i2 == 4 || i2 == 5) {
                i2 = 6;
            }
        }
        if (Util.a <= 26 && "fugu".equals(Util.b) && i2 == 1) {
            i2 = 2;
        }
        return Util.e(i2);
    }

    private void l() {
        AudioProcessor[] audioProcessorArr = this.p.i;
        ArrayList arrayList = new ArrayList();
        for (AudioProcessor audioProcessor : audioProcessorArr) {
            if (audioProcessor.a()) {
                arrayList.add(audioProcessor);
            } else {
                audioProcessor.e();
            }
        }
        int size = arrayList.size();
        this.F = (AudioProcessor[]) arrayList.toArray(new AudioProcessor[size]);
        this.G = new ByteBuffer[size];
        m();
    }

    private void m() {
        int i2 = 0;
        while (true) {
            AudioProcessor[] audioProcessorArr = this.F;
            if (i2 < audioProcessorArr.length) {
                AudioProcessor audioProcessor = audioProcessorArr[i2];
                audioProcessor.e();
                this.G[i2] = audioProcessor.c();
                i2++;
            } else {
                return;
            }
        }
    }

    private AudioTrack n() {
        try {
            return ((Configuration) Assertions.b((Object) this.p)).a(this.T, this.r, this.R);
        } catch (AudioSink.InitializationException e2) {
            o();
            AudioSink.Listener listener = this.n;
            if (listener != null) {
                listener.a((Exception) e2);
            }
            throw e2;
        }
    }

    private void o() {
        if (this.p.a()) {
            this.V = true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0018  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean p() {
        /*
            r9 = this;
            int r0 = r9.M
            r1 = 1
            r2 = 0
            r3 = -1
            if (r0 != r3) goto L_0x000b
            r9.M = r2
        L_0x0009:
            r0 = 1
            goto L_0x000c
        L_0x000b:
            r0 = 0
        L_0x000c:
            int r4 = r9.M
            com.google.android.exoplayer2.audio.AudioProcessor[] r5 = r9.F
            int r6 = r5.length
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r4 >= r6) goto L_0x002f
            r4 = r5[r4]
            if (r0 == 0) goto L_0x001f
            r4.b()
        L_0x001f:
            r9.a((long) r7)
            boolean r0 = r4.d()
            if (r0 != 0) goto L_0x0029
            return r2
        L_0x0029:
            int r0 = r9.M
            int r0 = r0 + r1
            r9.M = r0
            goto L_0x0009
        L_0x002f:
            java.nio.ByteBuffer r0 = r9.J
            if (r0 == 0) goto L_0x003b
            r9.a((java.nio.ByteBuffer) r0, (long) r7)
            java.nio.ByteBuffer r0 = r9.J
            if (r0 == 0) goto L_0x003b
            return r2
        L_0x003b:
            r9.M = r3
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.audio.DefaultAudioSink.p():boolean");
    }

    private void q() {
        if (!u()) {
            return;
        }
        if (Util.a >= 21) {
            this.q.setVolume(this.E);
            return;
        }
        AudioTrack audioTrack = this.q;
        float f2 = this.E;
        audioTrack.setStereoVolume(f2, f2);
    }

    private void r() {
        this.w = 0;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.W = false;
        this.A = 0;
        this.t = new MediaPositionParameters(s().a, s().b, 0, 0, (byte) 0);
        this.D = 0;
        this.s = null;
        this.j.clear();
        this.H = null;
        this.I = 0;
        this.J = null;
        this.O = false;
        this.N = false;
        this.M = -1;
        this.u = null;
        this.v = 0;
        this.e.f = 0;
        m();
    }

    private MediaPositionParameters s() {
        MediaPositionParameters mediaPositionParameters = this.s;
        return mediaPositionParameters != null ? mediaPositionParameters : !this.j.isEmpty() ? (MediaPositionParameters) this.j.getLast() : this.t;
    }

    private boolean t() {
        return !this.T && "audio/raw".equals(this.p.a.l);
    }

    private boolean u() {
        return this.q != null;
    }

    /* access modifiers changed from: private */
    public long v() {
        return this.p.c == 0 ? this.w / ((long) this.p.b) : this.x;
    }

    /* access modifiers changed from: private */
    public long w() {
        return this.p.c == 0 ? this.y / ((long) this.p.d) : this.z;
    }

    private void x() {
        if (!this.O) {
            this.O = true;
            this.i.c(w());
            this.q.stop();
            this.v = 0;
        }
    }

    public final long a(boolean z2) {
        long j2;
        if (!u() || this.C) {
            return Long.MIN_VALUE;
        }
        long min = Math.min(this.i.a(z2), this.p.a(w()));
        while (!this.j.isEmpty() && min >= ((MediaPositionParameters) this.j.getFirst()).d) {
            this.t = (MediaPositionParameters) this.j.remove();
        }
        long j3 = min - this.t.d;
        if (this.t.a.equals(PlaybackParameters.a)) {
            j2 = this.t.c + j3;
        } else if (this.j.isEmpty()) {
            j2 = this.c.a(j3) + this.t.c;
        } else {
            MediaPositionParameters mediaPositionParameters = (MediaPositionParameters) this.j.getFirst();
            j2 = mediaPositionParameters.c - Util.a(mediaPositionParameters.d - min, this.t.a.b);
        }
        return j2 + this.p.a(this.c.b());
    }

    public final void a() {
        this.P = true;
        if (u()) {
            ((AudioTimestampPoller) Assertions.b((Object) this.i.e)).a();
            this.q.play();
        }
    }

    public final void a(float f2) {
        if (this.E != f2) {
            this.E = f2;
            q();
        }
    }

    public final void a(int i2) {
        if (this.R != i2) {
            this.R = i2;
            this.Q = i2 != 0;
            j();
        }
    }

    public final void a(Format format, int[] iArr) {
        AudioProcessor[] audioProcessorArr;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int[] iArr2;
        if ("audio/raw".equals(format.l)) {
            Assertions.a(Util.c(format.y));
            int c2 = Util.c(format.y, format.w);
            AudioProcessor[] audioProcessorArr2 = this.f;
            TrimmingAudioProcessor trimmingAudioProcessor = this.e;
            int i8 = format.z;
            int i9 = format.A;
            trimmingAudioProcessor.d = i8;
            trimmingAudioProcessor.e = i9;
            if (Util.a < 21 && format.w == 8 && iArr == null) {
                iArr2 = new int[6];
                for (int i10 = 0; i10 < 6; i10++) {
                    iArr2[i10] = i10;
                }
            } else {
                iArr2 = iArr;
            }
            this.d.d = iArr2;
            AudioProcessor.AudioFormat audioFormat = new AudioProcessor.AudioFormat(format.x, format.w, format.y);
            int length = audioProcessorArr2.length;
            int i11 = 0;
            while (i11 < length) {
                AudioProcessor audioProcessor = audioProcessorArr2[i11];
                try {
                    AudioProcessor.AudioFormat a2 = audioProcessor.a(audioFormat);
                    if (audioProcessor.a()) {
                        audioFormat = a2;
                    }
                    i11++;
                } catch (AudioProcessor.UnhandledAudioFormatException e2) {
                    throw new AudioSink.ConfigurationException((Throwable) e2, format);
                }
            }
            int i12 = audioFormat.d;
            i4 = audioFormat.b;
            i3 = Util.e(audioFormat.c);
            audioProcessorArr = audioProcessorArr2;
            i2 = i12;
            i7 = c2;
            i5 = Util.c(i12, audioFormat.c);
            i6 = 0;
        } else {
            AudioProcessor[] audioProcessorArr3 = new AudioProcessor[0];
            int i13 = format.x;
            int i14 = Util.a;
            Pair b2 = b(format, this.b);
            if (b2 != null) {
                int intValue = ((Integer) b2.first).intValue();
                audioProcessorArr = audioProcessorArr3;
                i4 = i13;
                i3 = ((Integer) b2.second).intValue();
                i2 = intValue;
                i7 = -1;
                i6 = 2;
                i5 = -1;
            } else {
                String valueOf = String.valueOf(format);
                throw new AudioSink.ConfigurationException(new StringBuilder(String.valueOf(valueOf).length() + 37).append("Unable to configure passthrough for: ").append(valueOf).toString(), format);
            }
        }
        if (i2 == 0) {
            String valueOf2 = String.valueOf(format);
            throw new AudioSink.ConfigurationException(new StringBuilder(String.valueOf(valueOf2).length() + 48).append("Invalid output encoding (mode=").append(i6).append(") for: ").append(valueOf2).toString(), format);
        } else if (i3 != 0) {
            this.V = false;
            Configuration configuration = new Configuration(format, i7, i6, i5, i4, i3, i2, false, audioProcessorArr);
            if (u()) {
                this.o = configuration;
            } else {
                this.p = configuration;
            }
        } else {
            String valueOf3 = String.valueOf(format);
            throw new AudioSink.ConfigurationException(new StringBuilder(String.valueOf(valueOf3).length() + 54).append("Invalid output channel config (mode=").append(i6).append(") for: ").append(valueOf3).toString(), format);
        }
    }

    public final void a(PlaybackParameters playbackParameters) {
        a(new PlaybackParameters(Util.a(playbackParameters.b, 0.1f, 8.0f), Util.a(playbackParameters.c, 0.1f, 8.0f)), s().b);
    }

    public final void a(AudioAttributes audioAttributes) {
        if (!this.r.equals(audioAttributes)) {
            this.r = audioAttributes;
            if (!this.T) {
                j();
            }
        }
    }

    public final void a(AudioSink.Listener listener) {
        this.n = listener;
    }

    public final void a(AuxEffectInfo auxEffectInfo) {
        if (!this.S.equals(auxEffectInfo)) {
            this.S = auxEffectInfo;
        }
    }

    public final boolean a(Format format) {
        return b(format) != 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:82:0x019c, code lost:
        if (r5.d() == 0) goto L_0x0191;
     */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x011f A[Catch:{ InitializationException -> 0x014d }] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0126 A[Catch:{ InitializationException -> 0x014d }] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01c0 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x01c1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(java.nio.ByteBuffer r17, long r18, int r20) {
        /*
            r16 = this;
            r1 = r16
            r0 = r17
            r2 = r18
            r4 = r20
            java.nio.ByteBuffer r5 = r1.H
            r6 = 1
            r7 = 0
            if (r5 == 0) goto L_0x0013
            if (r0 != r5) goto L_0x0011
            goto L_0x0013
        L_0x0011:
            r5 = 0
            goto L_0x0014
        L_0x0013:
            r5 = 1
        L_0x0014:
            com.google.android.exoplayer2.util.Assertions.a((boolean) r5)
            com.google.android.exoplayer2.audio.DefaultAudioSink$Configuration r5 = r1.o
            r8 = 0
            if (r5 == 0) goto L_0x0081
            boolean r5 = r16.p()
            if (r5 != 0) goto L_0x0023
            return r7
        L_0x0023:
            com.google.android.exoplayer2.audio.DefaultAudioSink$Configuration r5 = r1.o
            com.google.android.exoplayer2.audio.DefaultAudioSink$Configuration r9 = r1.p
            int r10 = r9.c
            int r11 = r5.c
            if (r10 != r11) goto L_0x0047
            int r10 = r9.g
            int r11 = r5.g
            if (r10 != r11) goto L_0x0047
            int r10 = r9.e
            int r11 = r5.e
            if (r10 != r11) goto L_0x0047
            int r10 = r9.f
            int r11 = r5.f
            if (r10 != r11) goto L_0x0047
            int r9 = r9.d
            int r5 = r5.d
            if (r9 != r5) goto L_0x0047
            r5 = 1
            goto L_0x0048
        L_0x0047:
            r5 = 0
        L_0x0048:
            if (r5 != 0) goto L_0x0058
            r16.x()
            boolean r5 = r16.e()
            if (r5 == 0) goto L_0x0054
            return r7
        L_0x0054:
            r16.j()
            goto L_0x007e
        L_0x0058:
            com.google.android.exoplayer2.audio.DefaultAudioSink$Configuration r5 = r1.o
            r1.p = r5
            r1.o = r8
            android.media.AudioTrack r5 = r1.q
            boolean r5 = a((android.media.AudioTrack) r5)
            if (r5 == 0) goto L_0x007e
            android.media.AudioTrack r5 = r1.q
            r5.setOffloadEndOfStream()
            android.media.AudioTrack r5 = r1.q
            com.google.android.exoplayer2.audio.DefaultAudioSink$Configuration r9 = r1.p
            com.google.android.exoplayer2.Format r9 = r9.a
            int r9 = r9.z
            com.google.android.exoplayer2.audio.DefaultAudioSink$Configuration r10 = r1.p
            com.google.android.exoplayer2.Format r10 = r10.a
            int r10 = r10.A
            r5.setOffloadDelayPadding(r9, r10)
            r1.W = r6
        L_0x007e:
            r1.b((long) r2)
        L_0x0081:
            boolean r5 = r16.u()
            r9 = 2
            r12 = 0
            if (r5 != 0) goto L_0x0159
            android.os.ConditionVariable r5 = r1.h     // Catch:{ InitializationException -> 0x014d }
            r5.block()     // Catch:{ InitializationException -> 0x014d }
            android.media.AudioTrack r5 = r16.n()     // Catch:{ InitializationException -> 0x014d }
            r1.q = r5     // Catch:{ InitializationException -> 0x014d }
            boolean r5 = a((android.media.AudioTrack) r5)     // Catch:{ InitializationException -> 0x014d }
            if (r5 == 0) goto L_0x00c9
            android.media.AudioTrack r5 = r1.q     // Catch:{ InitializationException -> 0x014d }
            com.google.android.exoplayer2.audio.DefaultAudioSink$StreamEventCallbackV29 r14 = r1.k     // Catch:{ InitializationException -> 0x014d }
            if (r14 != 0) goto L_0x00a8
            com.google.android.exoplayer2.audio.DefaultAudioSink$StreamEventCallbackV29 r14 = new com.google.android.exoplayer2.audio.DefaultAudioSink$StreamEventCallbackV29     // Catch:{ InitializationException -> 0x014d }
            r14.<init>()     // Catch:{ InitializationException -> 0x014d }
            r1.k = r14     // Catch:{ InitializationException -> 0x014d }
        L_0x00a8:
            com.google.android.exoplayer2.audio.DefaultAudioSink$StreamEventCallbackV29 r14 = r1.k     // Catch:{ InitializationException -> 0x014d }
            android.os.Handler r15 = r14.a     // Catch:{ InitializationException -> 0x014d }
            r15.getClass()     // Catch:{ InitializationException -> 0x014d }
            java.util.concurrent.Executor r15 = com.google.android.exoplayer2.audio.DefaultAudioSink$StreamEventCallbackV29$$Lambda$0.a(r15)     // Catch:{ InitializationException -> 0x014d }
            android.media.AudioTrack$StreamEventCallback r14 = r14.b     // Catch:{ InitializationException -> 0x014d }
            r5.registerStreamEventCallback(r15, r14)     // Catch:{ InitializationException -> 0x014d }
            android.media.AudioTrack r5 = r1.q     // Catch:{ InitializationException -> 0x014d }
            com.google.android.exoplayer2.audio.DefaultAudioSink$Configuration r14 = r1.p     // Catch:{ InitializationException -> 0x014d }
            com.google.android.exoplayer2.Format r14 = r14.a     // Catch:{ InitializationException -> 0x014d }
            int r14 = r14.z     // Catch:{ InitializationException -> 0x014d }
            com.google.android.exoplayer2.audio.DefaultAudioSink$Configuration r15 = r1.p     // Catch:{ InitializationException -> 0x014d }
            com.google.android.exoplayer2.Format r15 = r15.a     // Catch:{ InitializationException -> 0x014d }
            int r15 = r15.A     // Catch:{ InitializationException -> 0x014d }
            r5.setOffloadDelayPadding(r14, r15)     // Catch:{ InitializationException -> 0x014d }
        L_0x00c9:
            android.media.AudioTrack r5 = r1.q     // Catch:{ InitializationException -> 0x014d }
            int r5 = r5.getAudioSessionId()     // Catch:{ InitializationException -> 0x014d }
            r1.R = r5     // Catch:{ InitializationException -> 0x014d }
            com.google.android.exoplayer2.audio.AudioTrackPositionTracker r5 = r1.i     // Catch:{ InitializationException -> 0x014d }
            android.media.AudioTrack r14 = r1.q     // Catch:{ InitializationException -> 0x014d }
            com.google.android.exoplayer2.audio.DefaultAudioSink$Configuration r15 = r1.p     // Catch:{ InitializationException -> 0x014d }
            int r15 = r15.c     // Catch:{ InitializationException -> 0x014d }
            if (r15 != r9) goto L_0x00dd
            r15 = 1
            goto L_0x00de
        L_0x00dd:
            r15 = 0
        L_0x00de:
            com.google.android.exoplayer2.audio.DefaultAudioSink$Configuration r9 = r1.p     // Catch:{ InitializationException -> 0x014d }
            int r9 = r9.g     // Catch:{ InitializationException -> 0x014d }
            com.google.android.exoplayer2.audio.DefaultAudioSink$Configuration r8 = r1.p     // Catch:{ InitializationException -> 0x014d }
            int r8 = r8.d     // Catch:{ InitializationException -> 0x014d }
            com.google.android.exoplayer2.audio.DefaultAudioSink$Configuration r6 = r1.p     // Catch:{ InitializationException -> 0x014d }
            int r6 = r6.h     // Catch:{ InitializationException -> 0x014d }
            r5.b = r14     // Catch:{ InitializationException -> 0x014d }
            r5.c = r8     // Catch:{ InitializationException -> 0x014d }
            r5.d = r6     // Catch:{ InitializationException -> 0x014d }
            com.google.android.exoplayer2.audio.AudioTimestampPoller r10 = new com.google.android.exoplayer2.audio.AudioTimestampPoller     // Catch:{ InitializationException -> 0x014d }
            r10.<init>(r14)     // Catch:{ InitializationException -> 0x014d }
            r5.e = r10     // Catch:{ InitializationException -> 0x014d }
            int r10 = r14.getSampleRate()     // Catch:{ InitializationException -> 0x014d }
            r5.f = r10     // Catch:{ InitializationException -> 0x014d }
            if (r15 == 0) goto L_0x0112
            int r10 = com.google.android.exoplayer2.util.Util.a     // Catch:{ InitializationException -> 0x014d }
            r11 = 23
            if (r10 >= r11) goto L_0x010d
            r10 = 5
            if (r9 == r10) goto L_0x010b
            r10 = 6
            if (r9 != r10) goto L_0x010d
        L_0x010b:
            r10 = 1
            goto L_0x010e
        L_0x010d:
            r10 = 0
        L_0x010e:
            if (r10 == 0) goto L_0x0112
            r10 = 1
            goto L_0x0113
        L_0x0112:
            r10 = 0
        L_0x0113:
            r5.g = r10     // Catch:{ InitializationException -> 0x014d }
            boolean r9 = com.google.android.exoplayer2.util.Util.c((int) r9)     // Catch:{ InitializationException -> 0x014d }
            r5.l = r9     // Catch:{ InitializationException -> 0x014d }
            boolean r9 = r5.l     // Catch:{ InitializationException -> 0x014d }
            if (r9 == 0) goto L_0x0126
            int r6 = r6 / r8
            long r8 = (long) r6     // Catch:{ InitializationException -> 0x014d }
            long r8 = r5.e(r8)     // Catch:{ InitializationException -> 0x014d }
            goto L_0x012b
        L_0x0126:
            r8 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L_0x012b:
            r5.h = r8     // Catch:{ InitializationException -> 0x014d }
            r5.n = r12     // Catch:{ InitializationException -> 0x014d }
            r5.o = r12     // Catch:{ InitializationException -> 0x014d }
            r5.p = r12     // Catch:{ InitializationException -> 0x014d }
            r5.k = r7     // Catch:{ InitializationException -> 0x014d }
            r8 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r5.q = r8     // Catch:{ InitializationException -> 0x014d }
            r5.r = r8     // Catch:{ InitializationException -> 0x014d }
            r5.m = r12     // Catch:{ InitializationException -> 0x014d }
            r5.j = r12     // Catch:{ InitializationException -> 0x014d }
            r6 = 1065353216(0x3f800000, float:1.0)
            r5.i = r6     // Catch:{ InitializationException -> 0x014d }
            r16.q()     // Catch:{ InitializationException -> 0x014d }
            r5 = 1
            r1.C = r5     // Catch:{ InitializationException -> 0x014d }
            goto L_0x0159
        L_0x014d:
            r0 = move-exception
            boolean r2 = r0.a
            if (r2 != 0) goto L_0x0158
            com.google.android.exoplayer2.audio.DefaultAudioSink$PendingExceptionHolder r2 = r1.l
            r2.a(r0)
            return r7
        L_0x0158:
            throw r0
        L_0x0159:
            com.google.android.exoplayer2.audio.DefaultAudioSink$PendingExceptionHolder r5 = r1.l
            r6 = 0
            r5.a = r6
            boolean r5 = r1.C
            if (r5 == 0) goto L_0x0176
            long r5 = java.lang.Math.max(r12, r2)
            r1.D = r5
            r1.B = r7
            r1.C = r7
            r1.b((long) r2)
            boolean r5 = r1.P
            if (r5 == 0) goto L_0x0176
            r16.a()
        L_0x0176:
            com.google.android.exoplayer2.audio.AudioTrackPositionTracker r5 = r1.i
            long r8 = r16.w()
            android.media.AudioTrack r6 = r5.b
            java.lang.Object r6 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r6)
            android.media.AudioTrack r6 = (android.media.AudioTrack) r6
            int r6 = r6.getPlayState()
            boolean r10 = r5.g
            if (r10 == 0) goto L_0x019f
            r10 = 2
            if (r6 != r10) goto L_0x0193
            r5.k = r7
        L_0x0191:
            r5 = 0
            goto L_0x01be
        L_0x0193:
            r10 = 1
            if (r6 != r10) goto L_0x019f
            long r10 = r5.d()
            int r14 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r14 != 0) goto L_0x019f
            goto L_0x0191
        L_0x019f:
            boolean r10 = r5.k
            boolean r8 = r5.d(r8)
            r5.k = r8
            if (r10 == 0) goto L_0x01bd
            boolean r8 = r5.k
            if (r8 != 0) goto L_0x01bd
            r8 = 1
            if (r6 == r8) goto L_0x01bd
            com.google.android.exoplayer2.audio.AudioTrackPositionTracker$Listener r6 = r5.a
            int r8 = r5.d
            long r9 = r5.h
            long r9 = com.google.android.exoplayer2.C.a((long) r9)
            r6.a(r8, r9)
        L_0x01bd:
            r5 = 1
        L_0x01be:
            if (r5 != 0) goto L_0x01c1
            return r7
        L_0x01c1:
            java.nio.ByteBuffer r5 = r1.H
            if (r5 != 0) goto L_0x02d1
            java.nio.ByteOrder r5 = r17.order()
            java.nio.ByteOrder r6 = java.nio.ByteOrder.LITTLE_ENDIAN
            if (r5 != r6) goto L_0x01cf
            r5 = 1
            goto L_0x01d0
        L_0x01cf:
            r5 = 0
        L_0x01d0:
            com.google.android.exoplayer2.util.Assertions.a((boolean) r5)
            boolean r5 = r17.hasRemaining()
            if (r5 != 0) goto L_0x01db
            r5 = 1
            return r5
        L_0x01db:
            com.google.android.exoplayer2.audio.DefaultAudioSink$Configuration r5 = r1.p
            int r5 = r5.c
            if (r5 == 0) goto L_0x0248
            int r5 = r1.A
            if (r5 != 0) goto L_0x0248
            com.google.android.exoplayer2.audio.DefaultAudioSink$Configuration r5 = r1.p
            int r5 = r5.g
            r6 = 1024(0x400, float:1.435E-42)
            r8 = -1
            switch(r5) {
                case 5: goto L_0x023e;
                case 6: goto L_0x023e;
                case 7: goto L_0x0239;
                case 8: goto L_0x0239;
                case 9: goto L_0x0224;
                case 10: goto L_0x0242;
                case 11: goto L_0x0221;
                case 12: goto L_0x0221;
                case 13: goto L_0x01ef;
                case 14: goto L_0x0212;
                case 15: goto L_0x020f;
                case 16: goto L_0x0242;
                case 17: goto L_0x020a;
                case 18: goto L_0x023e;
                default: goto L_0x01ef;
            }
        L_0x01ef:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r3 = 38
            r2.<init>(r3)
            java.lang.String r3 = "Unexpected audio encoding: "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r5)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x020a:
            int r6 = com.google.android.exoplayer2.audio.Ac4Util.a((java.nio.ByteBuffer) r17)
            goto L_0x0242
        L_0x020f:
            r6 = 512(0x200, float:7.175E-43)
            goto L_0x0242
        L_0x0212:
            int r5 = com.google.android.exoplayer2.audio.Ac3Util.b((java.nio.ByteBuffer) r17)
            if (r5 != r8) goto L_0x021a
            r6 = 0
            goto L_0x0242
        L_0x021a:
            int r5 = com.google.android.exoplayer2.audio.Ac3Util.a((java.nio.ByteBuffer) r0, (int) r5)
            int r6 = r5 << 4
            goto L_0x0242
        L_0x0221:
            r6 = 2048(0x800, float:2.87E-42)
            goto L_0x0242
        L_0x0224:
            int r5 = r17.position()
            int r5 = com.google.android.exoplayer2.util.Util.a((java.nio.ByteBuffer) r0, (int) r5)
            int r6 = com.google.android.exoplayer2.audio.MpegAudioUtil.b(r5)
            if (r6 == r8) goto L_0x0233
            goto L_0x0242
        L_0x0233:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>()
            throw r0
        L_0x0239:
            int r6 = com.google.android.exoplayer2.audio.DtsUtil.a((java.nio.ByteBuffer) r17)
            goto L_0x0242
        L_0x023e:
            int r6 = com.google.android.exoplayer2.audio.Ac3Util.a((java.nio.ByteBuffer) r17)
        L_0x0242:
            r1.A = r6
            if (r6 != 0) goto L_0x0248
            r5 = 1
            return r5
        L_0x0248:
            com.google.android.exoplayer2.audio.DefaultAudioSink$MediaPositionParameters r5 = r1.s
            if (r5 == 0) goto L_0x0259
            boolean r5 = r16.p()
            if (r5 != 0) goto L_0x0253
            return r7
        L_0x0253:
            r1.b((long) r2)
            r5 = 0
            r1.s = r5
        L_0x0259:
            long r5 = r1.D
            com.google.android.exoplayer2.audio.DefaultAudioSink$Configuration r8 = r1.p
            long r9 = r16.v()
            com.google.android.exoplayer2.audio.TrimmingAudioProcessor r11 = r1.e
            long r14 = r11.f
            long r9 = r9 - r14
            r14 = 1000000(0xf4240, double:4.940656E-318)
            long r9 = r9 * r14
            com.google.android.exoplayer2.Format r8 = r8.a
            int r8 = r8.x
            long r14 = (long) r8
            long r9 = r9 / r14
            long r5 = r5 + r9
            boolean r8 = r1.B
            if (r8 != 0) goto L_0x0290
            long r8 = r5 - r2
            long r8 = java.lang.Math.abs(r8)
            r10 = 200000(0x30d40, double:9.8813E-319)
            int r14 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r14 <= 0) goto L_0x0290
            com.google.android.exoplayer2.audio.AudioSink$Listener r8 = r1.n
            com.google.android.exoplayer2.audio.AudioSink$UnexpectedDiscontinuityException r9 = new com.google.android.exoplayer2.audio.AudioSink$UnexpectedDiscontinuityException
            r9.<init>(r2, r5)
            r8.a((java.lang.Exception) r9)
            r8 = 1
            r1.B = r8
        L_0x0290:
            boolean r8 = r1.B
            if (r8 == 0) goto L_0x02b2
            boolean r8 = r16.p()
            if (r8 != 0) goto L_0x029b
            return r7
        L_0x029b:
            long r5 = r2 - r5
            long r8 = r1.D
            long r8 = r8 + r5
            r1.D = r8
            r1.B = r7
            r1.b((long) r2)
            com.google.android.exoplayer2.audio.AudioSink$Listener r8 = r1.n
            if (r8 == 0) goto L_0x02b2
            int r9 = (r5 > r12 ? 1 : (r5 == r12 ? 0 : -1))
            if (r9 == 0) goto L_0x02b2
            r8.a()
        L_0x02b2:
            com.google.android.exoplayer2.audio.DefaultAudioSink$Configuration r5 = r1.p
            int r5 = r5.c
            if (r5 != 0) goto L_0x02c3
            long r5 = r1.w
            int r8 = r17.remaining()
            long r8 = (long) r8
            long r5 = r5 + r8
            r1.w = r5
            goto L_0x02cd
        L_0x02c3:
            long r5 = r1.x
            int r8 = r1.A
            int r8 = r8 * r4
            long r8 = (long) r8
            long r5 = r5 + r8
            r1.x = r5
        L_0x02cd:
            r1.H = r0
            r1.I = r4
        L_0x02d1:
            r1.a((long) r2)
            java.nio.ByteBuffer r0 = r1.H
            boolean r0 = r0.hasRemaining()
            if (r0 != 0) goto L_0x02e3
            r0 = 0
            r1.H = r0
            r1.I = r7
        L_0x02e1:
            r0 = 1
            return r0
        L_0x02e3:
            com.google.android.exoplayer2.audio.AudioTrackPositionTracker r0 = r1.i
            long r2 = r16.w()
            long r4 = r0.r
            r8 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r6 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r6 == 0) goto L_0x0307
            int r4 = (r2 > r12 ? 1 : (r2 == r12 ? 0 : -1))
            if (r4 <= 0) goto L_0x0307
            long r2 = android.os.SystemClock.elapsedRealtime()
            long r4 = r0.r
            long r2 = r2 - r4
            r4 = 200(0xc8, double:9.9E-322)
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 < 0) goto L_0x0307
            r5 = 1
            goto L_0x0308
        L_0x0307:
            r5 = 0
        L_0x0308:
            if (r5 == 0) goto L_0x0315
            java.lang.String r0 = "DefaultAudioSink"
            java.lang.String r2 = "Resetting stalled audio track"
            com.google.android.exoplayer2.util.Log.c(r0, r2)
            r16.j()
            goto L_0x02e1
        L_0x0315:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.audio.DefaultAudioSink.a(java.nio.ByteBuffer, long, int):boolean");
    }

    public final int b(Format format) {
        if ("audio/raw".equals(format.l)) {
            boolean c2 = Util.c(format.y);
            int i2 = format.y;
            if (c2) {
                return i2 == 2 ? 2 : 1;
            }
            Log.c("DefaultAudioSink", new StringBuilder(33).append("Invalid PCM encoding: ").append(i2).toString());
            return 0;
        }
        if (!this.V) {
            int i3 = Util.a;
        }
        return a(format, this.b) ? 2 : 0;
    }

    public final void b() {
        this.B = true;
    }

    public final void b(boolean z2) {
        a(s().a, z2);
    }

    public final void c() {
        if (!this.N && u() && p()) {
            x();
            this.N = true;
        }
    }

    public final boolean d() {
        if (u()) {
            return this.N && !e();
        }
        return true;
    }

    public final boolean e() {
        return u() && this.i.d(w());
    }

    public final PlaybackParameters f() {
        return s().a;
    }

    public final void g() {
        Assertions.b(Util.a >= 21);
        Assertions.b(this.Q);
        if (!this.T) {
            this.T = true;
            j();
        }
    }

    public final void h() {
        if (this.T) {
            this.T = false;
            j();
        }
    }

    public final void i() {
        boolean z2 = false;
        this.P = false;
        if (u()) {
            AudioTrackPositionTracker audioTrackPositionTracker = this.i;
            audioTrackPositionTracker.c();
            if (audioTrackPositionTracker.q == -9223372036854775807L) {
                ((AudioTimestampPoller) Assertions.b((Object) audioTrackPositionTracker.e)).a();
                z2 = true;
            }
            if (z2) {
                this.q.pause();
            }
        }
    }

    public final void j() {
        if (u()) {
            r();
            if (this.i.a()) {
                this.q.pause();
            }
            if (a(this.q)) {
                ((StreamEventCallbackV29) Assertions.b((Object) this.k)).a(this.q);
            }
            final AudioTrack audioTrack = this.q;
            this.q = null;
            if (Util.a < 21 && !this.Q) {
                this.R = 0;
            }
            Configuration configuration = this.o;
            if (configuration != null) {
                this.p = configuration;
                this.o = null;
            }
            this.i.b();
            this.h.close();
            new Thread("ExoPlayer:AudioTrackReleaseThread") {
                public void run() {
                    try {
                        audioTrack.flush();
                        audioTrack.release();
                    } finally {
                        DefaultAudioSink.this.h.open();
                    }
                }
            }.start();
        }
        this.m.a = null;
        this.l.a = null;
    }

    public final void k() {
        j();
        for (AudioProcessor f2 : this.f) {
            f2.f();
        }
        AudioProcessor[] audioProcessorArr = this.g;
        for (int i2 = 0; i2 <= 0; i2++) {
            audioProcessorArr[i2].f();
        }
        this.P = false;
        this.V = false;
    }
}
