package com.google.android.exoplayer2;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Pair;
import androidx.core.location.LocationRequestCompat;
import com.dreamers.exoplayercore.repack.aK;
import com.dreamers.exoplayercore.repack.bG;
import com.dreamers.exoplayercore.repack.bH;
import com.google.android.exoplayer2.DefaultMediaClock;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.MediaSourceList;
import com.google.android.exoplayer2.PlayerMessage;
import com.google.android.exoplayer2.Renderer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.analytics.AnalyticsCollector;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.SequenceableLoader;
import com.google.android.exoplayer2.source.ShuffleOrder;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.text.TextRenderer;
import com.google.android.exoplayer2.trackselection.ExoTrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.HandlerWrapper;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

final class ExoPlayerImplInternal implements Handler.Callback, DefaultMediaClock.PlaybackParametersListener, MediaSourceList.MediaSourceListInfoRefreshListener, PlayerMessage.Sender, MediaPeriod.Callback, TrackSelector.InvalidationListener {
    private boolean A;
    private boolean B;
    private boolean C;
    private int D;
    private boolean E;
    private boolean F;
    /* access modifiers changed from: private */
    public boolean G;
    private boolean H;
    private int I;
    private SeekPosition J;
    private long K;
    private int L;
    private boolean M;
    private ExoPlaybackException N;
    /* access modifiers changed from: package-private */
    public final HandlerWrapper a;
    final Looper b;
    boolean c;
    private final Renderer[] d;
    private final RendererCapabilities[] e;
    private final TrackSelector f;
    private final TrackSelectorResult g;
    private final LoadControl h;
    private final BandwidthMeter i;
    private final HandlerThread j;
    private final Timeline.Window k;
    private final Timeline.Period l;
    private final long m;
    private final boolean n = false;
    private final DefaultMediaClock o;
    private final ArrayList p;
    private final Clock q;
    private final PlaybackInfoUpdateListener r;
    private final MediaPeriodQueue s;
    private final MediaSourceList t;
    private final LivePlaybackSpeedControl u;
    private final long v;
    private SeekParameters w;
    private PlaybackInfo x;
    private PlaybackInfoUpdate y;
    private boolean z;

    final class MediaSourceListUpdateMessage {
        /* access modifiers changed from: private */
        public final List a;
        /* access modifiers changed from: private */
        public final ShuffleOrder b;

        private MediaSourceListUpdateMessage(List list, ShuffleOrder shuffleOrder) {
            this.a = list;
            this.b = shuffleOrder;
        }

        /* synthetic */ MediaSourceListUpdateMessage(List list, ShuffleOrder shuffleOrder, byte b2) {
            this(list, shuffleOrder);
        }

        static /* synthetic */ int a() {
            return -1;
        }
    }

    class MoveMediaItemsMessage {
        public final int a;
        public final int b;
        public final int c;
        public final ShuffleOrder d;
    }

    final class PendingMessageInfo implements Comparable {
        public final PlayerMessage a;
        public int b;
        public long c;
        public Object d;

        public PendingMessageInfo(PlayerMessage playerMessage) {
            this.a = playerMessage;
        }

        public final void a(int i, long j, Object obj) {
            this.b = i;
            this.c = j;
            this.d = obj;
        }

        public final /* synthetic */ int compareTo(Object obj) {
            PendingMessageInfo pendingMessageInfo = (PendingMessageInfo) obj;
            Object obj2 = this.d;
            if ((obj2 == null) != (pendingMessageInfo.d == null)) {
                return obj2 != null ? -1 : 1;
            }
            if (obj2 == null) {
                return 0;
            }
            int i = this.b - pendingMessageInfo.b;
            return i != 0 ? i : Util.d(this.c, pendingMessageInfo.c);
        }
    }

    public final class PlaybackInfoUpdate {
        public PlaybackInfo a;
        public int b;
        public boolean c;
        public int d;
        public boolean e;
        public int f;
        /* access modifiers changed from: private */
        public boolean g;

        public PlaybackInfoUpdate(PlaybackInfo playbackInfo) {
            this.a = playbackInfo;
        }

        public final void a(int i) {
            this.g |= i > 0;
            this.b += i;
        }

        public final void a(PlaybackInfo playbackInfo) {
            this.g |= this.a != playbackInfo;
            this.a = playbackInfo;
        }

        public final void b(int i) {
            boolean z = true;
            if (!this.c || this.d == 5) {
                this.g = true;
                this.c = true;
                this.d = i;
                return;
            }
            if (i != 5) {
                z = false;
            }
            Assertions.a(z);
        }

        public final void c(int i) {
            this.g = true;
            this.e = true;
            this.f = i;
        }
    }

    public interface PlaybackInfoUpdateListener {
        void a(PlaybackInfoUpdate playbackInfoUpdate);
    }

    final class PositionUpdateForPlaylistChange {
        public final MediaSource.MediaPeriodId a;
        public final long b;
        public final long c;
        public final boolean d;
        public final boolean e;
        public final boolean f;

        public PositionUpdateForPlaylistChange(MediaSource.MediaPeriodId mediaPeriodId, long j, long j2, boolean z, boolean z2, boolean z3) {
            this.a = mediaPeriodId;
            this.b = j;
            this.c = j2;
            this.d = z;
            this.e = z2;
            this.f = z3;
        }
    }

    final class SeekPosition {
        public final Timeline a;
        public final int b;
        public final long c;

        public SeekPosition(Timeline timeline, int i, long j) {
            this.a = timeline;
            this.b = i;
            this.c = j;
        }
    }

    public ExoPlayerImplInternal(Renderer[] rendererArr, TrackSelector trackSelector, TrackSelectorResult trackSelectorResult, LoadControl loadControl, BandwidthMeter bandwidthMeter, int i2, boolean z2, AnalyticsCollector analyticsCollector, SeekParameters seekParameters, LivePlaybackSpeedControl livePlaybackSpeedControl, long j2, boolean z3, Looper looper, Clock clock, PlaybackInfoUpdateListener playbackInfoUpdateListener) {
        Renderer[] rendererArr2 = rendererArr;
        AnalyticsCollector analyticsCollector2 = analyticsCollector;
        Clock clock2 = clock;
        this.r = playbackInfoUpdateListener;
        this.d = rendererArr2;
        this.f = trackSelector;
        this.g = trackSelectorResult;
        this.h = loadControl;
        this.i = bandwidthMeter;
        this.D = i2;
        this.E = z2;
        this.w = seekParameters;
        this.u = livePlaybackSpeedControl;
        this.v = j2;
        this.z = z3;
        this.q = clock2;
        this.m = loadControl.e();
        PlaybackInfo a2 = PlaybackInfo.a(trackSelectorResult);
        this.x = a2;
        this.y = new PlaybackInfoUpdate(a2);
        this.e = new RendererCapabilities[rendererArr2.length];
        for (int i3 = 0; i3 < rendererArr2.length; i3++) {
            rendererArr2[i3].a(i3);
            this.e[i3] = rendererArr2[i3].b();
        }
        this.o = new DefaultMediaClock(this, clock2);
        this.p = new ArrayList();
        this.k = new Timeline.Window();
        this.l = new Timeline.Period();
        trackSelector.init(this, bandwidthMeter);
        this.M = true;
        Handler handler = new Handler(looper);
        this.s = new MediaPeriodQueue(analyticsCollector2, handler);
        this.t = new MediaSourceList(this, analyticsCollector2, handler);
        HandlerThread handlerThread = new HandlerThread("ExoPlayer:Playback", -16);
        this.j = handlerThread;
        handlerThread.start();
        Looper looper2 = handlerThread.getLooper();
        this.b = looper2;
        this.a = clock2.a(looper2, this);
    }

    private long a(Timeline timeline, Object obj, long j2) {
        timeline.a(timeline.a(obj, this.l).c, this.k, 0);
        if (this.k.f == -9223372036854775807L || !this.k.a() || !this.k.i) {
            return -9223372036854775807L;
        }
        return C.b(Util.a(this.k.g) - this.k.f) - (j2 + this.l.e);
    }

    private long a(MediaSource.MediaPeriodId mediaPeriodId, long j2, boolean z2) {
        return a(mediaPeriodId, j2, this.s.d != this.s.e, z2);
    }

    private long a(MediaSource.MediaPeriodId mediaPeriodId, long j2, boolean z2, boolean z3) {
        h();
        this.B = false;
        if (z3 || this.x.e == 3) {
            a(2);
        }
        MediaPeriodHolder mediaPeriodHolder = this.s.d;
        MediaPeriodHolder mediaPeriodHolder2 = mediaPeriodHolder;
        while (mediaPeriodHolder2 != null && !mediaPeriodId.equals(mediaPeriodHolder2.f.a)) {
            mediaPeriodHolder2 = mediaPeriodHolder2.h;
        }
        if (z2 || mediaPeriodHolder != mediaPeriodHolder2 || (mediaPeriodHolder2 != null && mediaPeriodHolder2.k + j2 < 0)) {
            for (Renderer b2 : this.d) {
                b(b2);
            }
            if (mediaPeriodHolder2 != null) {
                while (this.s.d != mediaPeriodHolder2) {
                    this.s.a();
                }
                this.s.a(mediaPeriodHolder2);
                mediaPeriodHolder2.k = 0;
                t();
            }
        }
        MediaPeriodQueue mediaPeriodQueue = this.s;
        if (mediaPeriodHolder2 != null) {
            mediaPeriodQueue.a(mediaPeriodHolder2);
            if (!mediaPeriodHolder2.d) {
                mediaPeriodHolder2.f = mediaPeriodHolder2.f.a(j2);
            } else {
                if (mediaPeriodHolder2.f.e != -9223372036854775807L && j2 >= mediaPeriodHolder2.f.e) {
                    j2 = Math.max(0, mediaPeriodHolder2.f.e - 1);
                }
                if (mediaPeriodHolder2.e) {
                    long b3 = mediaPeriodHolder2.a.b(j2);
                    mediaPeriodHolder2.a.a(b3 - this.m, false);
                    j2 = b3;
                }
            }
            a(j2);
            p();
        } else {
            mediaPeriodQueue.b();
            a(j2);
        }
        b(false);
        this.a.b(2);
        return j2;
    }

    private Pair a(Timeline timeline) {
        long j2 = 0;
        if (timeline.c()) {
            return Pair.create(PlaybackInfo.a(), 0L);
        }
        Timeline timeline2 = timeline;
        Pair a2 = timeline2.a(this.k, this.l, timeline.b(this.E), -9223372036854775807L);
        MediaSource.MediaPeriodId a3 = this.s.a(timeline, a2.first, 0);
        long longValue = ((Long) a2.second).longValue();
        if (a3.a()) {
            timeline.a(a3.a, this.l);
            this.l.b(a3.b);
        } else {
            j2 = longValue;
        }
        return Pair.create(a3, Long.valueOf(j2));
    }

    private static Pair a(Timeline timeline, SeekPosition seekPosition, boolean z2, int i2, boolean z3, Timeline.Window window, Timeline.Period period) {
        Object a2;
        Timeline timeline2 = timeline;
        SeekPosition seekPosition2 = seekPosition;
        Timeline.Period period2 = period;
        Timeline timeline3 = seekPosition2.a;
        if (timeline.c()) {
            return null;
        }
        Timeline timeline4 = timeline3.c() ? timeline2 : timeline3;
        try {
            Pair a3 = timeline4.a(window, period, seekPosition2.b, seekPosition2.c);
            if (timeline.equals(timeline4)) {
                return a3;
            }
            if (timeline.c(a3.first) == -1) {
                Timeline.Window window2 = window;
                if (!z2 || (a2 = a(window, period, i2, z3, a3.first, timeline4, timeline)) == null) {
                    return null;
                }
                return timeline.a(window, period, timeline.a(a2, period2).c, -9223372036854775807L);
            } else if (!timeline4.a(a3.first, period2).f || timeline4.a(period2.c, window, 0).n != timeline4.c(a3.first)) {
                return a3;
            } else {
                return timeline.a(window, period, timeline.a(a3.first, period2).c, seekPosition2.c);
            }
        } catch (IndexOutOfBoundsException e2) {
            return null;
        }
    }

    private static bG a(ExoTrackSelection[] exoTrackSelectionArr) {
        bH bHVar = new bH();
        boolean z2 = false;
        for (ExoTrackSelection exoTrackSelection : exoTrackSelectionArr) {
            if (exoTrackSelection != null) {
                Format a2 = exoTrackSelection.a(0);
                if (a2.j == null) {
                    bHVar.b(new Metadata(new Metadata.Entry[0]));
                } else {
                    bHVar.b(a2.j);
                    z2 = true;
                }
            }
        }
        return z2 ? bHVar.a() : bG.g();
    }

    private PlaybackInfo a(MediaSource.MediaPeriodId mediaPeriodId, long j2, long j3, long j4, boolean z2, int i2) {
        bG bGVar;
        TrackSelectorResult trackSelectorResult;
        TrackGroupArray trackGroupArray;
        MediaSource.MediaPeriodId mediaPeriodId2 = mediaPeriodId;
        long j5 = j3;
        this.M = this.M || j2 != this.x.s || !mediaPeriodId.equals(this.x.b);
        o();
        TrackGroupArray trackGroupArray2 = this.x.h;
        TrackSelectorResult trackSelectorResult2 = this.x.i;
        List list = this.x.j;
        if (this.t.h) {
            MediaPeriodHolder mediaPeriodHolder = this.s.d;
            TrackGroupArray trackGroupArray3 = mediaPeriodHolder == null ? TrackGroupArray.a : mediaPeriodHolder.i;
            TrackSelectorResult trackSelectorResult3 = mediaPeriodHolder == null ? this.g : mediaPeriodHolder.j;
            bG a2 = a(trackSelectorResult3.c);
            if (!(mediaPeriodHolder == null || mediaPeriodHolder.f.c == j5)) {
                mediaPeriodHolder.f = mediaPeriodHolder.f.b(j5);
            }
            trackGroupArray = trackGroupArray3;
            trackSelectorResult = trackSelectorResult3;
            bGVar = a2;
        } else {
            if (!mediaPeriodId.equals(this.x.b)) {
                trackGroupArray2 = TrackGroupArray.a;
                trackSelectorResult2 = this.g;
                list = bG.g();
            }
            trackGroupArray = trackGroupArray2;
            trackSelectorResult = trackSelectorResult2;
            bGVar = list;
        }
        if (z2) {
            this.y.b(i2);
        }
        return this.x.a(mediaPeriodId, j2, j3, j4, u(), trackGroupArray, trackSelectorResult, bGVar);
    }

    static Object a(Timeline.Window window, Timeline.Period period, int i2, boolean z2, Object obj, Timeline timeline, Timeline timeline2) {
        int c2 = timeline.c(obj);
        int b2 = timeline.b();
        int i3 = c2;
        int i4 = -1;
        for (int i5 = 0; i5 < b2 && i4 == -1; i5++) {
            i3 = timeline.a(i3, period, window, i2, z2);
            if (i3 == -1) {
                break;
            }
            i4 = timeline2.c(timeline.a(i3));
        }
        if (i4 == -1) {
            return null;
        }
        return timeline2.a(i4);
    }

    private void a(float f2) {
        for (MediaPeriodHolder mediaPeriodHolder = this.s.d; mediaPeriodHolder != null; mediaPeriodHolder = mediaPeriodHolder.h) {
            for (ExoTrackSelection exoTrackSelection : mediaPeriodHolder.j.c) {
                if (exoTrackSelection != null) {
                    exoTrackSelection.a(f2);
                }
            }
        }
    }

    private void a(int i2) {
        if (this.x.e != i2) {
            this.x = this.x.a(i2);
        }
    }

    private void a(int i2, boolean z2) {
        Renderer renderer = this.d[i2];
        if (!c(renderer)) {
            MediaPeriodHolder mediaPeriodHolder = this.s.e;
            boolean z3 = mediaPeriodHolder == this.s.d;
            TrackSelectorResult trackSelectorResult = mediaPeriodHolder.j;
            RendererConfiguration rendererConfiguration = trackSelectorResult.b[i2];
            Format[] a2 = a(trackSelectorResult.c[i2]);
            boolean z4 = v() && this.x.e == 3;
            boolean z5 = !z2 && z4;
            this.I++;
            renderer.a(rendererConfiguration, a2, mediaPeriodHolder.c[i2], this.K, z5, z3, mediaPeriodHolder.a(), mediaPeriodHolder.k);
            renderer.a(103, new Renderer.WakeupListener() {
                public final void a() {
                    ExoPlayerImplInternal.this.a.b(2);
                }

                public final void a(long j) {
                    if (j >= 2000) {
                        boolean unused = ExoPlayerImplInternal.this.G = true;
                    }
                }
            });
            this.o.a(renderer);
            if (z4) {
                renderer.e();
            }
        }
    }

    private void a(long j2) {
        MediaPeriodHolder mediaPeriodHolder = this.s.d;
        if (mediaPeriodHolder != null) {
            j2 += mediaPeriodHolder.k;
        }
        this.K = j2;
        this.o.a(j2);
        for (Renderer renderer : this.d) {
            if (c(renderer)) {
                renderer.a(this.K);
            }
        }
        m();
    }

    private void a(long j2, long j3) {
        this.a.b();
        this.a.a(j2 + j3);
    }

    private synchronized void a(aK aKVar, long j2) {
        long a2 = this.q.a() + j2;
        boolean z2 = false;
        while (!((Boolean) aKVar.a()).booleanValue() && j2 > 0) {
            try {
                wait(j2);
            } catch (InterruptedException e2) {
                z2 = true;
            }
            j2 = a2 - this.q.a();
        }
        if (z2) {
            Thread.currentThread().interrupt();
        }
    }

    private void a(PlaybackParameters playbackParameters, float f2, boolean z2, boolean z3) {
        if (z2) {
            if (z3) {
                this.y.a(1);
            }
            this.x = this.x.a(playbackParameters);
        }
        a(playbackParameters.b);
        for (Renderer renderer : this.d) {
            if (renderer != null) {
                renderer.a(f2, playbackParameters.b);
            }
        }
    }

    private void a(PlaybackParameters playbackParameters, boolean z2) {
        a(playbackParameters, playbackParameters.b, true, z2);
    }

    private static void a(Renderer renderer) {
        if (renderer.b_() == 2) {
            renderer.l();
        }
    }

    private static void a(Renderer renderer, long j2) {
        renderer.i();
        if (renderer instanceof TextRenderer) {
            ((TextRenderer) renderer).c(j2);
        }
    }

    private static void a(Timeline timeline, PendingMessageInfo pendingMessageInfo, Timeline.Window window, Timeline.Period period) {
        int i2 = timeline.a(timeline.a(pendingMessageInfo.d, period).c, window, 0).o;
        pendingMessageInfo.a(i2, period.d != -9223372036854775807L ? period.d - 1 : LocationRequestCompat.PASSIVE_INTERVAL, timeline.a(i2, period, true).b);
    }

    private void a(Timeline timeline, Timeline timeline2) {
        if (!timeline.c() || !timeline2.c()) {
            for (int size = this.p.size() - 1; size >= 0; size--) {
                if (!a((PendingMessageInfo) this.p.get(size), timeline, timeline2, this.D, this.E, this.k, this.l)) {
                    ((PendingMessageInfo) this.p.get(size)).a.a(false);
                    this.p.remove(size);
                }
            }
            Collections.sort(this.p);
        }
    }

    private void a(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline2, MediaSource.MediaPeriodId mediaPeriodId2, long j2) {
        if (!timeline.c() && a(timeline, mediaPeriodId)) {
            timeline.a(timeline.a(mediaPeriodId.a, this.l).c, this.k, 0);
            this.u.a((MediaItem.LiveConfiguration) Util.a((Object) this.k.j));
            if (j2 != -9223372036854775807L) {
                this.u.a(a(timeline, mediaPeriodId.a, j2));
                return;
            }
            if (!Util.a(!timeline2.c() ? timeline2.a(timeline2.a(mediaPeriodId2.a, this.l).c, this.k, 0).b : null, this.k.b)) {
                this.u.a(-9223372036854775807L);
            }
        } else if (this.o.d().b != this.x.n.b) {
            this.o.a(this.x.n);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:123:0x0254, code lost:
        if (r0.a(r5) != false) goto L_0x0256;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0256, code lost:
        r3 = false;
     */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x0241 A[Catch:{ all -> 0x0367 }] */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x02a3 A[Catch:{ all -> 0x0367 }, LOOP:1: B:116:0x023f->B:149:0x02a3, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x02ad A[Catch:{ all -> 0x0367 }] */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x02b2 A[Catch:{ all -> 0x0367 }] */
    /* JADX WARNING: Removed duplicated region for block: B:195:0x037d  */
    /* JADX WARNING: Removed duplicated region for block: B:196:0x037f  */
    /* JADX WARNING: Removed duplicated region for block: B:201:0x0394  */
    /* JADX WARNING: Removed duplicated region for block: B:202:0x0396  */
    /* JADX WARNING: Removed duplicated region for block: B:216:0x03ee  */
    /* JADX WARNING: Removed duplicated region for block: B:224:0x027a A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:227:0x02aa A[EDGE_INSN: B:227:0x02aa->B:150:0x02aa ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0134  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0150  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0167  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0169  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0185  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x018c  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x01a4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(com.google.android.exoplayer2.Timeline r37, boolean r38) {
        /*
            r36 = this;
            r11 = r36
            r12 = r37
            com.google.android.exoplayer2.PlaybackInfo r0 = r11.x
            com.google.android.exoplayer2.ExoPlayerImplInternal$SeekPosition r8 = r11.J
            com.google.android.exoplayer2.MediaPeriodQueue r9 = r11.s
            int r4 = r11.D
            boolean r10 = r11.E
            com.google.android.exoplayer2.Timeline$Window r13 = r11.k
            com.google.android.exoplayer2.Timeline$Period r14 = r11.l
            boolean r1 = r37.c()
            r6 = 0
            r16 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r5 = -1
            if (r1 == 0) goto L_0x003e
            com.google.android.exoplayer2.ExoPlayerImplInternal$PositionUpdateForPlaylistChange r0 = new com.google.android.exoplayer2.ExoPlayerImplInternal$PositionUpdateForPlaylistChange
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r19 = com.google.android.exoplayer2.PlaybackInfo.a()
            r20 = 0
            r22 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r24 = 0
            r25 = 1
            r26 = 0
            r18 = r0
            r18.<init>(r19, r20, r22, r24, r25, r26)
            r1 = r0
            r24 = r6
            r8 = -1
            goto L_0x01b0
        L_0x003e:
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r1 = r0.b
            java.lang.Object r15 = r1.a
            boolean r19 = a((com.google.android.exoplayer2.PlaybackInfo) r0, (com.google.android.exoplayer2.Timeline.Period) r14)
            if (r19 == 0) goto L_0x004b
            long r2 = r0.c
            goto L_0x004d
        L_0x004b:
            long r2 = r0.s
        L_0x004d:
            r22 = r2
            if (r8 == 0) goto L_0x009d
            r3 = 1
            r2 = r1
            r1 = r37
            r11 = r2
            r2 = r8
            r5 = r10
            r6 = r13
            r7 = r14
            android.util.Pair r1 = a((com.google.android.exoplayer2.Timeline) r1, (com.google.android.exoplayer2.ExoPlayerImplInternal.SeekPosition) r2, (boolean) r3, (int) r4, (boolean) r5, (com.google.android.exoplayer2.Timeline.Window) r6, (com.google.android.exoplayer2.Timeline.Period) r7)
            if (r1 != 0) goto L_0x006d
            int r1 = r12.b(r10)
            r4 = r1
            r8 = -1
        L_0x0066:
            r24 = 0
            r33 = 0
            r34 = 1
            goto L_0x00b2
        L_0x006d:
            long r2 = r8.c
            int r4 = (r2 > r16 ? 1 : (r2 == r16 ? 0 : -1))
            if (r4 != 0) goto L_0x007d
            java.lang.Object r1 = r1.first
            com.google.android.exoplayer2.Timeline$Period r1 = r12.a(r1, r14)
            int r5 = r1.c
            r3 = 0
            goto L_0x0089
        L_0x007d:
            java.lang.Object r15 = r1.first
            java.lang.Object r1 = r1.second
            java.lang.Long r1 = (java.lang.Long) r1
            long r22 = r1.longValue()
            r3 = 1
            r5 = -1
        L_0x0089:
            int r1 = r0.e
            r2 = 4
            if (r1 != r2) goto L_0x0090
            r1 = 1
            goto L_0x0091
        L_0x0090:
            r1 = 0
        L_0x0091:
            r33 = r1
            r35 = r3
            r4 = r5
            r8 = -1
            r24 = 0
            r34 = 0
            goto L_0x0132
        L_0x009d:
            r11 = r1
            com.google.android.exoplayer2.Timeline r1 = r0.a
            boolean r1 = r1.c()
            if (r1 == 0) goto L_0x00b6
            int r1 = r12.b(r10)
            r4 = r1
            r8 = -1
        L_0x00ac:
            r24 = 0
        L_0x00ae:
            r33 = 0
            r34 = 0
        L_0x00b2:
            r35 = 0
            goto L_0x0132
        L_0x00b6:
            int r1 = r12.c(r15)
            r8 = -1
            if (r1 != r8) goto L_0x00da
            com.google.android.exoplayer2.Timeline r6 = r0.a
            r1 = r13
            r2 = r14
            r3 = r4
            r4 = r10
            r5 = r15
            r7 = r37
            java.lang.Object r1 = a((com.google.android.exoplayer2.Timeline.Window) r1, (com.google.android.exoplayer2.Timeline.Period) r2, (int) r3, (boolean) r4, (java.lang.Object) r5, (com.google.android.exoplayer2.Timeline) r6, (com.google.android.exoplayer2.Timeline) r7)
            if (r1 != 0) goto L_0x00d2
            int r1 = r12.b(r10)
            r4 = r1
            goto L_0x0066
        L_0x00d2:
            com.google.android.exoplayer2.Timeline$Period r1 = r12.a(r1, r14)
        L_0x00d6:
            int r1 = r1.c
            r4 = r1
            goto L_0x00ac
        L_0x00da:
            if (r19 == 0) goto L_0x012d
            int r1 = (r22 > r16 ? 1 : (r22 == r16 ? 0 : -1))
            if (r1 != 0) goto L_0x00e5
            com.google.android.exoplayer2.Timeline$Period r1 = r12.a(r15, r14)
            goto L_0x00d6
        L_0x00e5:
            com.google.android.exoplayer2.Timeline r1 = r0.a
            java.lang.Object r2 = r11.a
            r1.a(r2, r14)
            com.google.android.exoplayer2.Timeline r1 = r0.a
            int r2 = r14.c
            r5 = 0
            com.google.android.exoplayer2.Timeline$Window r1 = r1.a((int) r2, (com.google.android.exoplayer2.Timeline.Window) r13, (long) r5)
            int r1 = r1.n
            com.google.android.exoplayer2.Timeline r2 = r0.a
            java.lang.Object r3 = r11.a
            int r2 = r2.c(r3)
            if (r1 != r2) goto L_0x0123
            long r1 = r14.e
            long r19 = r22 + r1
            com.google.android.exoplayer2.Timeline$Period r1 = r12.a(r15, r14)
            int r4 = r1.c
            r1 = r37
            r2 = r13
            r3 = r14
            r24 = r5
            r5 = r19
            android.util.Pair r1 = r1.a(r2, r3, r4, r5)
            java.lang.Object r15 = r1.first
            java.lang.Object r1 = r1.second
            java.lang.Long r1 = (java.lang.Long) r1
            long r22 = r1.longValue()
            goto L_0x0125
        L_0x0123:
            r24 = r5
        L_0x0125:
            r4 = -1
            r33 = 0
            r34 = 0
            r35 = 1
            goto L_0x0132
        L_0x012d:
            r24 = 0
            r4 = -1
            goto L_0x00ae
        L_0x0132:
            if (r4 == r8) goto L_0x0150
            r5 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r1 = r37
            r2 = r13
            r3 = r14
            android.util.Pair r1 = r1.a(r2, r3, r4, r5)
            java.lang.Object r15 = r1.first
            java.lang.Object r1 = r1.second
            java.lang.Long r1 = (java.lang.Long) r1
            long r22 = r1.longValue()
            r31 = r16
            r1 = r22
            goto L_0x0154
        L_0x0150:
            r1 = r22
            r31 = r1
        L_0x0154:
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r3 = r9.a((com.google.android.exoplayer2.Timeline) r12, (java.lang.Object) r15, (long) r1)
            int r4 = r3.e
            if (r4 == r8) goto L_0x0169
            int r4 = r11.e
            if (r4 == r8) goto L_0x0167
            int r4 = r3.b
            int r5 = r11.e
            if (r4 < r5) goto L_0x0167
            goto L_0x0169
        L_0x0167:
            r4 = 0
            goto L_0x016a
        L_0x0169:
            r4 = 1
        L_0x016a:
            java.lang.Object r5 = r11.a
            boolean r5 = r5.equals(r15)
            if (r5 == 0) goto L_0x0182
            boolean r5 = r11.a()
            if (r5 != 0) goto L_0x0182
            boolean r5 = r3.a()
            if (r5 != 0) goto L_0x0182
            if (r4 == 0) goto L_0x0182
            r4 = 1
            goto L_0x0183
        L_0x0182:
            r4 = 0
        L_0x0183:
            if (r4 == 0) goto L_0x0186
            r3 = r11
        L_0x0186:
            boolean r4 = r3.a()
            if (r4 == 0) goto L_0x01a4
            boolean r1 = r3.equals(r11)
            if (r1 == 0) goto L_0x0195
            long r6 = r0.s
            goto L_0x01a1
        L_0x0195:
            java.lang.Object r0 = r3.a
            r12.a(r0, r14)
            int r0 = r3.b
            r14.b((int) r0)
            r6 = r24
        L_0x01a1:
            r29 = r6
            goto L_0x01a6
        L_0x01a4:
            r29 = r1
        L_0x01a6:
            com.google.android.exoplayer2.ExoPlayerImplInternal$PositionUpdateForPlaylistChange r0 = new com.google.android.exoplayer2.ExoPlayerImplInternal$PositionUpdateForPlaylistChange
            r27 = r0
            r28 = r3
            r27.<init>(r28, r29, r31, r33, r34, r35)
            r1 = r0
        L_0x01b0:
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r9 = r1.a
            long r10 = r1.c
            boolean r0 = r1.d
            long r13 = r1.b
            r15 = r36
            com.google.android.exoplayer2.PlaybackInfo r2 = r15.x
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r2 = r2.b
            boolean r2 = r2.equals(r9)
            if (r2 == 0) goto L_0x01d0
            com.google.android.exoplayer2.PlaybackInfo r2 = r15.x
            long r2 = r2.s
            int r4 = (r13 > r2 ? 1 : (r13 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x01cd
            goto L_0x01d0
        L_0x01cd:
            r19 = 0
            goto L_0x01d2
        L_0x01d0:
            r19 = 1
        L_0x01d2:
            r20 = 3
            boolean r2 = r1.e     // Catch:{ all -> 0x0369 }
            if (r2 == 0) goto L_0x01ea
            com.google.android.exoplayer2.PlaybackInfo r2 = r15.x     // Catch:{ all -> 0x0369 }
            int r2 = r2.e     // Catch:{ all -> 0x0369 }
            r7 = 1
            if (r2 == r7) goto L_0x01e4
            r5 = 4
            r15.a((int) r5)     // Catch:{ all -> 0x0369 }
            goto L_0x01e5
        L_0x01e4:
            r5 = 4
        L_0x01e5:
            r4 = 0
            r15.a((boolean) r4, (boolean) r4, (boolean) r4, (boolean) r7)     // Catch:{ all -> 0x0369 }
            goto L_0x01ed
        L_0x01ea:
            r4 = 0
            r5 = 4
            r7 = 1
        L_0x01ed:
            if (r19 != 0) goto L_0x02b4
            com.google.android.exoplayer2.MediaPeriodQueue r0 = r15.s     // Catch:{ all -> 0x0369 }
            long r2 = r15.K     // Catch:{ all -> 0x0369 }
            com.google.android.exoplayer2.MediaPeriodHolder r5 = r0.e     // Catch:{ all -> 0x0369 }
            r21 = -9223372036854775808
            if (r5 != 0) goto L_0x01fe
            r6 = r24
        L_0x01fb:
            r24 = r10
            goto L_0x023c
        L_0x01fe:
            long r6 = r5.k     // Catch:{ all -> 0x0369 }
            boolean r8 = r5.d     // Catch:{ all -> 0x0369 }
            if (r8 == 0) goto L_0x01fb
            r7 = r6
            r6 = 0
        L_0x0206:
            com.google.android.exoplayer2.Renderer[] r4 = r15.d     // Catch:{ all -> 0x0369 }
            r24 = r10
            int r10 = r4.length     // Catch:{ all -> 0x0367 }
            if (r6 >= r10) goto L_0x023b
            r4 = r4[r6]     // Catch:{ all -> 0x0367 }
            boolean r4 = c((com.google.android.exoplayer2.Renderer) r4)     // Catch:{ all -> 0x0367 }
            if (r4 == 0) goto L_0x0236
            com.google.android.exoplayer2.Renderer[] r4 = r15.d     // Catch:{ all -> 0x0367 }
            r4 = r4[r6]     // Catch:{ all -> 0x0367 }
            com.google.android.exoplayer2.source.SampleStream r4 = r4.f()     // Catch:{ all -> 0x0367 }
            com.google.android.exoplayer2.source.SampleStream[] r10 = r5.c     // Catch:{ all -> 0x0367 }
            r10 = r10[r6]     // Catch:{ all -> 0x0367 }
            if (r4 != r10) goto L_0x0236
            com.google.android.exoplayer2.Renderer[] r4 = r15.d     // Catch:{ all -> 0x0367 }
            r4 = r4[r6]     // Catch:{ all -> 0x0367 }
            long r10 = r4.h()     // Catch:{ all -> 0x0367 }
            int r4 = (r10 > r21 ? 1 : (r10 == r21 ? 0 : -1))
            if (r4 != 0) goto L_0x0232
            r6 = r21
            goto L_0x023c
        L_0x0232:
            long r7 = java.lang.Math.max(r10, r7)     // Catch:{ all -> 0x0367 }
        L_0x0236:
            int r6 = r6 + 1
            r10 = r24
            goto L_0x0206
        L_0x023b:
            r6 = r7
        L_0x023c:
            com.google.android.exoplayer2.MediaPeriodHolder r4 = r0.d     // Catch:{ all -> 0x0367 }
            r5 = 0
        L_0x023f:
            if (r4 == 0) goto L_0x02aa
            com.google.android.exoplayer2.MediaPeriodInfo r8 = r4.f     // Catch:{ all -> 0x0367 }
            if (r5 != 0) goto L_0x024a
            com.google.android.exoplayer2.MediaPeriodInfo r5 = r0.a((com.google.android.exoplayer2.Timeline) r12, (com.google.android.exoplayer2.MediaPeriodInfo) r8)     // Catch:{ all -> 0x0367 }
            goto L_0x0266
        L_0x024a:
            com.google.android.exoplayer2.MediaPeriodInfo r10 = r0.a((com.google.android.exoplayer2.Timeline) r12, (com.google.android.exoplayer2.MediaPeriodHolder) r5, (long) r2)     // Catch:{ all -> 0x0367 }
            if (r10 != 0) goto L_0x0258
            boolean r0 = r0.a((com.google.android.exoplayer2.MediaPeriodHolder) r5)     // Catch:{ all -> 0x0367 }
            if (r0 == 0) goto L_0x02aa
        L_0x0256:
            r3 = 0
            goto L_0x02ab
        L_0x0258:
            boolean r11 = com.google.android.exoplayer2.MediaPeriodQueue.a((com.google.android.exoplayer2.MediaPeriodInfo) r8, (com.google.android.exoplayer2.MediaPeriodInfo) r10)     // Catch:{ all -> 0x0367 }
            if (r11 != 0) goto L_0x0265
            boolean r0 = r0.a((com.google.android.exoplayer2.MediaPeriodHolder) r5)     // Catch:{ all -> 0x0367 }
            if (r0 == 0) goto L_0x02aa
            goto L_0x0256
        L_0x0265:
            r5 = r10
        L_0x0266:
            long r10 = r8.c     // Catch:{ all -> 0x0367 }
            com.google.android.exoplayer2.MediaPeriodInfo r10 = r5.b(r10)     // Catch:{ all -> 0x0367 }
            r4.f = r10     // Catch:{ all -> 0x0367 }
            long r10 = r8.e     // Catch:{ all -> 0x0367 }
            r28 = r2
            long r2 = r5.e     // Catch:{ all -> 0x0367 }
            boolean r2 = com.google.android.exoplayer2.MediaPeriodQueue.a((long) r10, (long) r2)     // Catch:{ all -> 0x0367 }
            if (r2 != 0) goto L_0x02a3
            long r2 = r5.e     // Catch:{ all -> 0x0367 }
            int r8 = (r2 > r16 ? 1 : (r2 == r16 ? 0 : -1))
            if (r8 != 0) goto L_0x0286
            r2 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            goto L_0x028b
        L_0x0286:
            long r2 = r5.e     // Catch:{ all -> 0x0367 }
            long r10 = r4.k     // Catch:{ all -> 0x0367 }
            long r2 = r2 + r10
        L_0x028b:
            com.google.android.exoplayer2.MediaPeriodHolder r5 = r0.e     // Catch:{ all -> 0x0367 }
            if (r4 != r5) goto L_0x0299
            int r5 = (r6 > r21 ? 1 : (r6 == r21 ? 0 : -1))
            if (r5 == 0) goto L_0x0297
            int r5 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r5 < 0) goto L_0x0299
        L_0x0297:
            r3 = 1
            goto L_0x029a
        L_0x0299:
            r3 = 0
        L_0x029a:
            boolean r0 = r0.a((com.google.android.exoplayer2.MediaPeriodHolder) r4)     // Catch:{ all -> 0x0367 }
            if (r0 != 0) goto L_0x0256
            if (r3 == 0) goto L_0x02aa
            goto L_0x0256
        L_0x02a3:
            com.google.android.exoplayer2.MediaPeriodHolder r2 = r4.h     // Catch:{ all -> 0x0367 }
            r5 = r4
            r4 = r2
            r2 = r28
            goto L_0x023f
        L_0x02aa:
            r3 = 1
        L_0x02ab:
            if (r3 != 0) goto L_0x02b2
            r4 = 0
            r15.a((boolean) r4)     // Catch:{ all -> 0x0367 }
            goto L_0x02de
        L_0x02b2:
            r4 = 0
            goto L_0x02de
        L_0x02b4:
            r24 = r10
            boolean r2 = r37.c()     // Catch:{ all -> 0x0367 }
            if (r2 != 0) goto L_0x02de
            com.google.android.exoplayer2.MediaPeriodQueue r2 = r15.s     // Catch:{ all -> 0x0367 }
            com.google.android.exoplayer2.MediaPeriodHolder r2 = r2.d     // Catch:{ all -> 0x0367 }
        L_0x02c0:
            if (r2 == 0) goto L_0x02d9
            com.google.android.exoplayer2.MediaPeriodInfo r3 = r2.f     // Catch:{ all -> 0x0367 }
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r3 = r3.a     // Catch:{ all -> 0x0367 }
            boolean r3 = r3.equals(r9)     // Catch:{ all -> 0x0367 }
            if (r3 == 0) goto L_0x02d6
            com.google.android.exoplayer2.MediaPeriodQueue r3 = r15.s     // Catch:{ all -> 0x0367 }
            com.google.android.exoplayer2.MediaPeriodInfo r5 = r2.f     // Catch:{ all -> 0x0367 }
            com.google.android.exoplayer2.MediaPeriodInfo r3 = r3.a((com.google.android.exoplayer2.Timeline) r12, (com.google.android.exoplayer2.MediaPeriodInfo) r5)     // Catch:{ all -> 0x0367 }
            r2.f = r3     // Catch:{ all -> 0x0367 }
        L_0x02d6:
            com.google.android.exoplayer2.MediaPeriodHolder r2 = r2.h     // Catch:{ all -> 0x0367 }
            goto L_0x02c0
        L_0x02d9:
            long r2 = r15.a((com.google.android.exoplayer2.source.MediaSource.MediaPeriodId) r9, (long) r13, (boolean) r0)     // Catch:{ all -> 0x0367 }
            r13 = r2
        L_0x02de:
            com.google.android.exoplayer2.PlaybackInfo r0 = r15.x
            com.google.android.exoplayer2.Timeline r0 = r0.a
            com.google.android.exoplayer2.PlaybackInfo r2 = r15.x
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r5 = r2.b
            boolean r1 = r1.f
            if (r1 == 0) goto L_0x02ec
            r6 = r13
            goto L_0x02ee
        L_0x02ec:
            r6 = r16
        L_0x02ee:
            r1 = r36
            r2 = r37
            r3 = r9
            r11 = 0
            r4 = r0
            r8 = 4
            r10 = 0
            r18 = 1
            r1.a(r2, r3, r4, r5, r6)
            if (r19 != 0) goto L_0x0309
            com.google.android.exoplayer2.PlaybackInfo r0 = r15.x
            long r0 = r0.c
            int r2 = (r24 > r0 ? 1 : (r24 == r0 ? 0 : -1))
            if (r2 == 0) goto L_0x0307
            goto L_0x0309
        L_0x0307:
            r13 = r10
            goto L_0x0349
        L_0x0309:
            com.google.android.exoplayer2.PlaybackInfo r0 = r15.x
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r0 = r0.b
            java.lang.Object r0 = r0.a
            com.google.android.exoplayer2.PlaybackInfo r1 = r15.x
            com.google.android.exoplayer2.Timeline r1 = r1.a
            if (r19 == 0) goto L_0x0328
            if (r38 == 0) goto L_0x0328
            boolean r2 = r1.c()
            if (r2 != 0) goto L_0x0328
            com.google.android.exoplayer2.Timeline$Period r2 = r15.l
            com.google.android.exoplayer2.Timeline$Period r1 = r1.a(r0, r2)
            boolean r1 = r1.f
            if (r1 != 0) goto L_0x0328
            goto L_0x032a
        L_0x0328:
            r18 = 0
        L_0x032a:
            com.google.android.exoplayer2.PlaybackInfo r1 = r15.x
            long r5 = r1.d
            int r0 = r12.c(r0)
            r1 = -1
            if (r0 != r1) goto L_0x0337
            r20 = 4
        L_0x0337:
            r1 = r36
            r2 = r9
            r3 = r13
            r7 = r5
            r5 = r24
            r9 = r18
            r13 = r10
            r10 = r20
            com.google.android.exoplayer2.PlaybackInfo r0 = r1.a(r2, r3, r5, r7, r9, r10)
            r15.x = r0
        L_0x0349:
            r36.o()
            com.google.android.exoplayer2.PlaybackInfo r0 = r15.x
            com.google.android.exoplayer2.Timeline r0 = r0.a
            r15.a((com.google.android.exoplayer2.Timeline) r12, (com.google.android.exoplayer2.Timeline) r0)
            com.google.android.exoplayer2.PlaybackInfo r0 = r15.x
            com.google.android.exoplayer2.PlaybackInfo r0 = r0.a((com.google.android.exoplayer2.Timeline) r12)
            r15.x = r0
            boolean r0 = r37.c()
            if (r0 != 0) goto L_0x0363
            r15.J = r13
        L_0x0363:
            r15.b((boolean) r11)
            return
        L_0x0367:
            r0 = move-exception
            goto L_0x036c
        L_0x0369:
            r0 = move-exception
            r24 = r10
        L_0x036c:
            r8 = 4
            r10 = 0
            r11 = 0
            r18 = 1
            com.google.android.exoplayer2.PlaybackInfo r2 = r15.x
            com.google.android.exoplayer2.Timeline r4 = r2.a
            com.google.android.exoplayer2.PlaybackInfo r2 = r15.x
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r5 = r2.b
            boolean r1 = r1.f
            if (r1 == 0) goto L_0x037f
            r6 = r13
            goto L_0x0381
        L_0x037f:
            r6 = r16
        L_0x0381:
            r1 = r36
            r2 = r37
            r3 = r9
            r1.a(r2, r3, r4, r5, r6)
            if (r19 != 0) goto L_0x0396
            com.google.android.exoplayer2.PlaybackInfo r1 = r15.x
            long r1 = r1.c
            int r3 = (r24 > r1 ? 1 : (r24 == r1 ? 0 : -1))
            if (r3 == 0) goto L_0x0394
            goto L_0x0396
        L_0x0394:
            r13 = r10
            goto L_0x03d6
        L_0x0396:
            com.google.android.exoplayer2.PlaybackInfo r1 = r15.x
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r1 = r1.b
            java.lang.Object r1 = r1.a
            com.google.android.exoplayer2.PlaybackInfo r2 = r15.x
            com.google.android.exoplayer2.Timeline r2 = r2.a
            if (r19 == 0) goto L_0x03b5
            if (r38 == 0) goto L_0x03b5
            boolean r3 = r2.c()
            if (r3 != 0) goto L_0x03b5
            com.google.android.exoplayer2.Timeline$Period r3 = r15.l
            com.google.android.exoplayer2.Timeline$Period r2 = r2.a(r1, r3)
            boolean r2 = r2.f
            if (r2 != 0) goto L_0x03b5
            goto L_0x03b7
        L_0x03b5:
            r18 = 0
        L_0x03b7:
            com.google.android.exoplayer2.PlaybackInfo r2 = r15.x
            long r5 = r2.d
            int r1 = r12.c(r1)
            r2 = -1
            if (r1 != r2) goto L_0x03c4
            r20 = 4
        L_0x03c4:
            r1 = r36
            r2 = r9
            r3 = r13
            r7 = r5
            r5 = r24
            r9 = r18
            r13 = r10
            r10 = r20
            com.google.android.exoplayer2.PlaybackInfo r1 = r1.a(r2, r3, r5, r7, r9, r10)
            r15.x = r1
        L_0x03d6:
            r36.o()
            com.google.android.exoplayer2.PlaybackInfo r1 = r15.x
            com.google.android.exoplayer2.Timeline r1 = r1.a
            r15.a((com.google.android.exoplayer2.Timeline) r12, (com.google.android.exoplayer2.Timeline) r1)
            com.google.android.exoplayer2.PlaybackInfo r1 = r15.x
            com.google.android.exoplayer2.PlaybackInfo r1 = r1.a((com.google.android.exoplayer2.Timeline) r12)
            r15.x = r1
            boolean r1 = r37.c()
            if (r1 != 0) goto L_0x03f0
            r15.J = r13
        L_0x03f0:
            r15.b((boolean) r11)
            goto L_0x03f5
        L_0x03f4:
            throw r0
        L_0x03f5:
            goto L_0x03f4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ExoPlayerImplInternal.a(com.google.android.exoplayer2.Timeline, boolean):void");
    }

    private void a(TrackSelectorResult trackSelectorResult) {
        this.h.a(this.d, trackSelectorResult.c);
    }

    private void a(boolean z2) {
        MediaSource.MediaPeriodId mediaPeriodId = this.s.d.f.a;
        long a2 = a(mediaPeriodId, this.x.s, true, false);
        if (a2 != this.x.s) {
            this.x = a(mediaPeriodId, a2, this.x.c, this.x.d, z2, 5);
        }
    }

    private void a(boolean z2, int i2, boolean z3, int i3) {
        this.y.a(z3 ? 1 : 0);
        this.y.c(i3);
        this.x = this.x.a(z2, i2);
        this.B = false;
        f();
        if (!v()) {
            h();
            i();
        } else if (this.x.e == 3) {
            g();
            this.a.b(2);
        } else if (this.x.e == 2) {
            this.a.b(2);
        }
    }

    private void a(boolean z2, AtomicBoolean atomicBoolean) {
        if (this.F != z2) {
            this.F = z2;
            if (!z2) {
                for (Renderer renderer : this.d) {
                    if (!c(renderer)) {
                        renderer.n();
                    }
                }
            }
        }
        if (atomicBoolean != null) {
            synchronized (this) {
                atomicBoolean.set(true);
                notifyAll();
            }
        }
    }

    private void a(boolean z2, boolean z3) {
        a(z2 || !this.F, false, true, false);
        this.y.a(z3 ? 1 : 0);
        this.h.b();
        a(1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00d7  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:54:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(boolean r35, boolean r36, boolean r37, boolean r38) {
        /*
            r34 = this;
            r1 = r34
            com.google.android.exoplayer2.util.HandlerWrapper r0 = r1.a
            r0.b()
            r2 = 0
            r1.N = r2
            r3 = 0
            r1.B = r3
            com.google.android.exoplayer2.DefaultMediaClock r0 = r1.o
            r0.b()
            r4 = 0
            r1.K = r4
            com.google.android.exoplayer2.Renderer[] r4 = r1.d
            int r5 = r4.length
            r6 = 0
        L_0x001a:
            java.lang.String r7 = "ExoPlayerImplInternal"
            if (r6 >= r5) goto L_0x002f
            r0 = r4[r6]
            r1.b((com.google.android.exoplayer2.Renderer) r0)     // Catch:{ ExoPlaybackException -> 0x0026, RuntimeException -> 0x0024 }
            goto L_0x002c
        L_0x0024:
            r0 = move-exception
            goto L_0x0027
        L_0x0026:
            r0 = move-exception
        L_0x0027:
            java.lang.String r8 = "Disable failed."
            com.google.android.exoplayer2.util.Log.b(r7, r8, r0)
        L_0x002c:
            int r6 = r6 + 1
            goto L_0x001a
        L_0x002f:
            if (r35 == 0) goto L_0x0047
            com.google.android.exoplayer2.Renderer[] r4 = r1.d
            int r5 = r4.length
            r6 = 0
        L_0x0035:
            if (r6 >= r5) goto L_0x0047
            r0 = r4[r6]
            r0.n()     // Catch:{ RuntimeException -> 0x003d }
            goto L_0x0044
        L_0x003d:
            r0 = move-exception
            r8 = r0
            java.lang.String r0 = "Reset failed."
            com.google.android.exoplayer2.util.Log.b(r7, r0, r8)
        L_0x0044:
            int r6 = r6 + 1
            goto L_0x0035
        L_0x0047:
            r1.I = r3
            com.google.android.exoplayer2.PlaybackInfo r0 = r1.x
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r0 = r0.b
            com.google.android.exoplayer2.PlaybackInfo r4 = r1.x
            long r4 = r4.s
            com.google.android.exoplayer2.PlaybackInfo r6 = r1.x
            com.google.android.exoplayer2.Timeline$Period r7 = r1.l
            boolean r6 = a((com.google.android.exoplayer2.PlaybackInfo) r6, (com.google.android.exoplayer2.Timeline.Period) r7)
            if (r6 == 0) goto L_0x0060
            com.google.android.exoplayer2.PlaybackInfo r6 = r1.x
            long r6 = r6.c
            goto L_0x0064
        L_0x0060:
            com.google.android.exoplayer2.PlaybackInfo r6 = r1.x
            long r6 = r6.s
        L_0x0064:
            if (r36 == 0) goto L_0x0098
            r1.J = r2
            com.google.android.exoplayer2.PlaybackInfo r0 = r1.x
            com.google.android.exoplayer2.Timeline r0 = r0.a
            android.util.Pair r0 = r1.a((com.google.android.exoplayer2.Timeline) r0)
            java.lang.Object r4 = r0.first
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r4 = (com.google.android.exoplayer2.source.MediaSource.MediaPeriodId) r4
            java.lang.Object r0 = r0.second
            java.lang.Long r0 = (java.lang.Long) r0
            long r5 = r0.longValue()
            com.google.android.exoplayer2.PlaybackInfo r0 = r1.x
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r0 = r0.b
            boolean r0 = r4.equals(r0)
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r0 != 0) goto L_0x0092
            r0 = 1
            r22 = r4
            r30 = r5
            r12 = r7
            goto L_0x009e
        L_0x0092:
            r22 = r4
            r30 = r5
            r12 = r7
            goto L_0x009d
        L_0x0098:
            r22 = r0
            r30 = r4
            r12 = r6
        L_0x009d:
            r0 = 0
        L_0x009e:
            com.google.android.exoplayer2.MediaPeriodQueue r4 = r1.s
            r4.b()
            r1.C = r3
            com.google.android.exoplayer2.PlaybackInfo r3 = new com.google.android.exoplayer2.PlaybackInfo
            com.google.android.exoplayer2.PlaybackInfo r4 = r1.x
            com.google.android.exoplayer2.Timeline r10 = r4.a
            com.google.android.exoplayer2.PlaybackInfo r4 = r1.x
            int r4 = r4.e
            if (r38 == 0) goto L_0x00b2
            goto L_0x00b6
        L_0x00b2:
            com.google.android.exoplayer2.PlaybackInfo r2 = r1.x
            com.google.android.exoplayer2.ExoPlaybackException r2 = r2.f
        L_0x00b6:
            r17 = r2
            r18 = 0
            if (r0 == 0) goto L_0x00bf
            com.google.android.exoplayer2.source.TrackGroupArray r2 = com.google.android.exoplayer2.source.TrackGroupArray.a
            goto L_0x00c3
        L_0x00bf:
            com.google.android.exoplayer2.PlaybackInfo r2 = r1.x
            com.google.android.exoplayer2.source.TrackGroupArray r2 = r2.h
        L_0x00c3:
            r19 = r2
            if (r0 == 0) goto L_0x00ca
            com.google.android.exoplayer2.trackselection.TrackSelectorResult r2 = r1.g
            goto L_0x00ce
        L_0x00ca:
            com.google.android.exoplayer2.PlaybackInfo r2 = r1.x
            com.google.android.exoplayer2.trackselection.TrackSelectorResult r2 = r2.i
        L_0x00ce:
            r20 = r2
            if (r0 == 0) goto L_0x00d7
            com.dreamers.exoplayercore.repack.bG r0 = com.dreamers.exoplayercore.repack.bG.g()
            goto L_0x00db
        L_0x00d7:
            com.google.android.exoplayer2.PlaybackInfo r0 = r1.x
            java.util.List r0 = r0.j
        L_0x00db:
            r21 = r0
            com.google.android.exoplayer2.PlaybackInfo r0 = r1.x
            boolean r0 = r0.l
            r23 = r0
            com.google.android.exoplayer2.PlaybackInfo r0 = r1.x
            int r0 = r0.m
            r24 = r0
            com.google.android.exoplayer2.PlaybackInfo r0 = r1.x
            com.google.android.exoplayer2.PlaybackParameters r0 = r0.n
            r25 = r0
            r28 = 0
            boolean r0 = r1.H
            r32 = r0
            r33 = 0
            r9 = r3
            r11 = r22
            r14 = r30
            r16 = r4
            r26 = r30
            r9.<init>(r10, r11, r12, r14, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r28, r30, r32, r33)
            r1.x = r3
            if (r37 == 0) goto L_0x010c
            com.google.android.exoplayer2.MediaSourceList r0 = r1.t
            r0.b()
        L_0x010c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ExoPlayerImplInternal.a(boolean, boolean, boolean, boolean):void");
    }

    private void a(boolean[] zArr) {
        MediaPeriodHolder mediaPeriodHolder = this.s.e;
        TrackSelectorResult trackSelectorResult = mediaPeriodHolder.j;
        for (int i2 = 0; i2 < this.d.length; i2++) {
            if (!trackSelectorResult.a(i2)) {
                this.d[i2].n();
            }
        }
        for (int i3 = 0; i3 < this.d.length; i3++) {
            if (trackSelectorResult.a(i3)) {
                a(i3, zArr[i3]);
            }
        }
        mediaPeriodHolder.g = true;
    }

    private static boolean a(PendingMessageInfo pendingMessageInfo, Timeline timeline, Timeline timeline2, int i2, boolean z2, Timeline.Window window, Timeline.Period period) {
        PendingMessageInfo pendingMessageInfo2 = pendingMessageInfo;
        Timeline timeline3 = timeline;
        Timeline timeline4 = timeline2;
        Timeline.Window window2 = window;
        Timeline.Period period2 = period;
        if (pendingMessageInfo2.d == null) {
            Pair a2 = a(timeline, new SeekPosition(pendingMessageInfo2.a.b, pendingMessageInfo2.a.f, pendingMessageInfo2.a.g == Long.MIN_VALUE ? -9223372036854775807L : C.b(pendingMessageInfo2.a.g)), false, i2, z2, window, period);
            if (a2 == null) {
                return false;
            }
            pendingMessageInfo.a(timeline3.c(a2.first), ((Long) a2.second).longValue(), a2.first);
            if (pendingMessageInfo2.a.g == Long.MIN_VALUE) {
                a(timeline3, pendingMessageInfo, window2, period2);
            }
            return true;
        }
        int c2 = timeline3.c(pendingMessageInfo2.d);
        if (c2 == -1) {
            return false;
        }
        if (pendingMessageInfo2.a.g == Long.MIN_VALUE) {
            a(timeline3, pendingMessageInfo, window2, period2);
            return true;
        }
        pendingMessageInfo2.b = c2;
        timeline4.a(pendingMessageInfo2.d, period2);
        if (period2.f && timeline4.a(period2.c, window2, 0).n == timeline4.c(pendingMessageInfo2.d)) {
            Pair a3 = timeline.a(window, period, timeline3.a(pendingMessageInfo2.d, period2).c, pendingMessageInfo2.c + period2.e);
            pendingMessageInfo.a(timeline3.c(a3.first), ((Long) a3.second).longValue(), a3.first);
        }
        return true;
    }

    private static boolean a(PlaybackInfo playbackInfo, Timeline.Period period) {
        MediaSource.MediaPeriodId mediaPeriodId = playbackInfo.b;
        Timeline timeline = playbackInfo.a;
        return mediaPeriodId.a() || timeline.c() || timeline.a(mediaPeriodId.a, period).f;
    }

    private boolean a(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId) {
        if (!mediaPeriodId.a() && !timeline.c()) {
            timeline.a(timeline.a(mediaPeriodId.a, this.l).c, this.k, 0);
            return this.k.a() && this.k.i && this.k.f != -9223372036854775807L;
        }
    }

    private static Format[] a(ExoTrackSelection exoTrackSelection) {
        int f2 = exoTrackSelection != null ? exoTrackSelection.f() : 0;
        Format[] formatArr = new Format[f2];
        for (int i2 = 0; i2 < f2; i2++) {
            formatArr[i2] = exoTrackSelection.a(i2);
        }
        return formatArr;
    }

    private long b(long j2) {
        MediaPeriodHolder mediaPeriodHolder = this.s.f;
        if (mediaPeriodHolder == null) {
            return 0;
        }
        return Math.max(0, j2 - (this.K - mediaPeriodHolder.k));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0046, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0047, code lost:
        if (r3 == null) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x004b, code lost:
        if (r3.b > r0) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004f, code lost:
        if (r3.b != r0) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0055, code lost:
        if (r3.c <= r8) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0057, code lost:
        r1 = r1 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0059, code lost:
        if (r1 <= 0) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0062, code lost:
        if (r1 >= r7.p.size()) goto L_0x006d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0064, code lost:
        r3 = (com.google.android.exoplayer2.ExoPlayerImplInternal.PendingMessageInfo) r7.p.get(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006d, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006e, code lost:
        if (r3 == null) goto L_0x008d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0072, code lost:
        if (r3.d == null) goto L_0x008d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0076, code lost:
        if (r3.b < r0) goto L_0x0082;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x007a, code lost:
        if (r3.b != r0) goto L_0x008d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0080, code lost:
        if (r3.c > r8) goto L_0x008d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0082, code lost:
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x008a, code lost:
        if (r1 >= r7.p.size()) goto L_0x006d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x008d, code lost:
        if (r3 == null) goto L_0x00d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0091, code lost:
        if (r3.d == null) goto L_0x00d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0095, code lost:
        if (r3.b != r0) goto L_0x00d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x009b, code lost:
        if (r3.c <= r8) goto L_0x00d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00a1, code lost:
        if (r3.c > r10) goto L_0x00d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        c(r3.a);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00b4, code lost:
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00bc, code lost:
        if (r1 >= r7.p.size()) goto L_0x00c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00be, code lost:
        r3 = (com.google.android.exoplayer2.ExoPlayerImplInternal.PendingMessageInfo) r7.p.get(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00c7, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00c9, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00ce, code lost:
        if (r3.a.h != false) goto L_0x00d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00d0, code lost:
        r7.p.remove(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00d5, code lost:
        throw r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00d6, code lost:
        r7.L = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0039, code lost:
        if (r1 > 0) goto L_0x003b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003b, code lost:
        r3 = (com.google.android.exoplayer2.ExoPlayerImplInternal.PendingMessageInfo) r7.p.get(r1 - 1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(long r8, long r10) {
        /*
            r7 = this;
            java.util.ArrayList r0 = r7.p
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x00d8
            com.google.android.exoplayer2.PlaybackInfo r0 = r7.x
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r0 = r0.b
            boolean r0 = r0.a()
            if (r0 == 0) goto L_0x0014
            goto L_0x00d8
        L_0x0014:
            boolean r0 = r7.M
            if (r0 == 0) goto L_0x001e
            r0 = 1
            long r8 = r8 - r0
            r0 = 0
            r7.M = r0
        L_0x001e:
            com.google.android.exoplayer2.PlaybackInfo r0 = r7.x
            com.google.android.exoplayer2.Timeline r0 = r0.a
            com.google.android.exoplayer2.PlaybackInfo r1 = r7.x
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r1 = r1.b
            java.lang.Object r1 = r1.a
            int r0 = r0.c(r1)
            int r1 = r7.L
            java.util.ArrayList r2 = r7.p
            int r2 = r2.size()
            int r1 = java.lang.Math.min(r1, r2)
            r2 = 0
            if (r1 <= 0) goto L_0x0046
        L_0x003b:
            java.util.ArrayList r3 = r7.p
            int r4 = r1 + -1
            java.lang.Object r3 = r3.get(r4)
            com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo r3 = (com.google.android.exoplayer2.ExoPlayerImplInternal.PendingMessageInfo) r3
            goto L_0x0047
        L_0x0046:
            r3 = r2
        L_0x0047:
            if (r3 == 0) goto L_0x005c
            int r4 = r3.b
            if (r4 > r0) goto L_0x0057
            int r4 = r3.b
            if (r4 != r0) goto L_0x005c
            long r3 = r3.c
            int r5 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r5 <= 0) goto L_0x005c
        L_0x0057:
            int r1 = r1 + -1
            if (r1 <= 0) goto L_0x0046
            goto L_0x003b
        L_0x005c:
            java.util.ArrayList r3 = r7.p
            int r3 = r3.size()
            if (r1 >= r3) goto L_0x006d
        L_0x0064:
            java.util.ArrayList r3 = r7.p
            java.lang.Object r3 = r3.get(r1)
            com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo r3 = (com.google.android.exoplayer2.ExoPlayerImplInternal.PendingMessageInfo) r3
            goto L_0x006e
        L_0x006d:
            r3 = r2
        L_0x006e:
            if (r3 == 0) goto L_0x008d
            java.lang.Object r4 = r3.d
            if (r4 == 0) goto L_0x008d
            int r4 = r3.b
            if (r4 < r0) goto L_0x0082
            int r4 = r3.b
            if (r4 != r0) goto L_0x008d
            long r4 = r3.c
            int r6 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r6 > 0) goto L_0x008d
        L_0x0082:
            int r1 = r1 + 1
            java.util.ArrayList r3 = r7.p
            int r3 = r3.size()
            if (r1 >= r3) goto L_0x006d
            goto L_0x0064
        L_0x008d:
            if (r3 == 0) goto L_0x00d6
            java.lang.Object r4 = r3.d
            if (r4 == 0) goto L_0x00d6
            int r4 = r3.b
            if (r4 != r0) goto L_0x00d6
            long r4 = r3.c
            int r6 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r6 <= 0) goto L_0x00d6
            long r4 = r3.c
            int r6 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r6 > 0) goto L_0x00d6
            com.google.android.exoplayer2.PlayerMessage r4 = r3.a     // Catch:{ all -> 0x00c9 }
            r7.c((com.google.android.exoplayer2.PlayerMessage) r4)     // Catch:{ all -> 0x00c9 }
            com.google.android.exoplayer2.PlayerMessage r3 = r3.a
            boolean r3 = r3.h
            if (r3 == 0) goto L_0x00b4
            java.util.ArrayList r3 = r7.p
            r3.remove(r1)
            goto L_0x00b6
        L_0x00b4:
            int r1 = r1 + 1
        L_0x00b6:
            java.util.ArrayList r3 = r7.p
            int r3 = r3.size()
            if (r1 >= r3) goto L_0x00c7
            java.util.ArrayList r3 = r7.p
            java.lang.Object r3 = r3.get(r1)
            com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo r3 = (com.google.android.exoplayer2.ExoPlayerImplInternal.PendingMessageInfo) r3
            goto L_0x008d
        L_0x00c7:
            r3 = r2
            goto L_0x008d
        L_0x00c9:
            r8 = move-exception
            com.google.android.exoplayer2.PlayerMessage r9 = r3.a
            boolean r9 = r9.h
            if (r9 == 0) goto L_0x00d5
            java.util.ArrayList r9 = r7.p
            r9.remove(r1)
        L_0x00d5:
            throw r8
        L_0x00d6:
            r7.L = r1
        L_0x00d8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ExoPlayerImplInternal.b(long, long):void");
    }

    static void b(PlayerMessage playerMessage) {
        try {
            playerMessage.a.a(playerMessage.c, playerMessage.d);
        } finally {
            playerMessage.a(true);
        }
    }

    private void b(Renderer renderer) {
        if (c(renderer)) {
            this.o.b(renderer);
            a(renderer);
            renderer.m();
            this.I--;
        }
    }

    private void b(boolean z2) {
        MediaPeriodHolder mediaPeriodHolder = this.s.f;
        MediaSource.MediaPeriodId mediaPeriodId = mediaPeriodHolder == null ? this.x.b : mediaPeriodHolder.f.a;
        boolean z3 = !this.x.k.equals(mediaPeriodId);
        if (z3) {
            this.x = this.x.a(mediaPeriodId);
        }
        PlaybackInfo playbackInfo = this.x;
        playbackInfo.q = mediaPeriodHolder == null ? playbackInfo.s : mediaPeriodHolder.c();
        this.x.r = u();
        if ((z3 || z2) && mediaPeriodHolder != null && mediaPeriodHolder.d) {
            a(mediaPeriodHolder.j);
        }
    }

    private void c(PlayerMessage playerMessage) {
        if (playerMessage.e == this.b) {
            b(playerMessage);
            if (this.x.e == 3 || this.x.e == 2) {
                this.a.b(2);
                return;
            }
            return;
        }
        this.a.a(15, playerMessage).a();
    }

    private static boolean c(Renderer renderer) {
        return renderer.b_() != 0;
    }

    private void e() {
        this.y.a(this.x);
        if (this.y.g) {
            this.r.a(this.y);
            this.y = new PlaybackInfoUpdate(this.x);
        }
    }

    private void f() {
        for (MediaPeriodHolder mediaPeriodHolder = this.s.d; mediaPeriodHolder != null; mediaPeriodHolder = mediaPeriodHolder.h) {
            for (ExoTrackSelection exoTrackSelection : mediaPeriodHolder.j.c) {
                if (exoTrackSelection != null) {
                    exoTrackSelection.k();
                }
            }
        }
    }

    private void g() {
        this.B = false;
        this.o.a();
        for (Renderer renderer : this.d) {
            if (c(renderer)) {
                renderer.e();
            }
        }
    }

    private void h() {
        this.o.b();
        for (Renderer renderer : this.d) {
            if (c(renderer)) {
                a(renderer);
            }
        }
    }

    private void i() {
        MediaPeriodHolder mediaPeriodHolder = this.s.d;
        if (mediaPeriodHolder != null) {
            long c2 = mediaPeriodHolder.d ? mediaPeriodHolder.a.c() : -9223372036854775807L;
            if (c2 != -9223372036854775807L) {
                a(c2);
                if (c2 != this.x.s) {
                    this.x = a(this.x.b, c2, this.x.c, c2, true, 5);
                }
            } else {
                long a2 = this.o.a(mediaPeriodHolder != this.s.e);
                this.K = a2;
                long j2 = a2 - mediaPeriodHolder.k;
                b(this.x.s, j2);
                this.x.s = j2;
            }
            this.x.q = this.s.f.c();
            this.x.r = u();
            if (this.x.l && this.x.e == 3 && a(this.x.a, this.x.b) && this.x.n.b == 1.0f) {
                float a3 = this.u.a(j(), u());
                if (this.o.d().b != a3) {
                    this.o.a(this.x.n.a(a3));
                    a(this.x.n, this.o.d().b, false, false);
                }
            }
        }
    }

    private long j() {
        return a(this.x.a, this.x.b.a, this.x.s);
    }

    private void k() {
        a(true, false, true, false);
        this.h.c();
        a(1);
        this.j.quit();
        synchronized (this) {
            this.c = true;
            notifyAll();
        }
    }

    private void l() {
        boolean z2;
        float f2 = this.o.d().b;
        MediaPeriodHolder mediaPeriodHolder = this.s.d;
        MediaPeriodHolder mediaPeriodHolder2 = this.s.e;
        boolean z3 = true;
        while (mediaPeriodHolder != null && mediaPeriodHolder.d) {
            TrackSelectorResult a2 = mediaPeriodHolder.a(f2, this.x.a);
            TrackSelectorResult trackSelectorResult = mediaPeriodHolder.j;
            if (trackSelectorResult != null && trackSelectorResult.c.length == a2.c.length) {
                int i2 = 0;
                while (true) {
                    if (i2 >= a2.c.length) {
                        z2 = true;
                        break;
                    } else if (!a2.a(trackSelectorResult, i2)) {
                        break;
                    } else {
                        i2++;
                    }
                }
            }
            z2 = false;
            if (z2) {
                if (mediaPeriodHolder == mediaPeriodHolder2) {
                    z3 = false;
                }
                mediaPeriodHolder = mediaPeriodHolder.h;
            } else {
                MediaPeriodQueue mediaPeriodQueue = this.s;
                if (z3) {
                    MediaPeriodHolder mediaPeriodHolder3 = mediaPeriodQueue.d;
                    boolean a3 = this.s.a(mediaPeriodHolder3);
                    boolean[] zArr = new boolean[this.d.length];
                    long a4 = mediaPeriodHolder3.a(a2, this.x.s, a3, zArr);
                    boolean z4 = (this.x.e == 4 || a4 == this.x.s) ? false : true;
                    boolean[] zArr2 = zArr;
                    MediaPeriodHolder mediaPeriodHolder4 = mediaPeriodHolder3;
                    this.x = a(this.x.b, a4, this.x.c, this.x.d, z4, 5);
                    if (z4) {
                        a(a4);
                    }
                    boolean[] zArr3 = new boolean[this.d.length];
                    int i3 = 0;
                    while (true) {
                        Renderer[] rendererArr = this.d;
                        if (i3 >= rendererArr.length) {
                            break;
                        }
                        Renderer renderer = rendererArr[i3];
                        zArr3[i3] = c(renderer);
                        SampleStream sampleStream = mediaPeriodHolder4.c[i3];
                        if (zArr3[i3]) {
                            if (sampleStream != renderer.f()) {
                                b(renderer);
                            } else if (zArr2[i3]) {
                                renderer.a(this.K);
                            }
                        }
                        i3++;
                    }
                    a(zArr3);
                } else {
                    mediaPeriodQueue.a(mediaPeriodHolder);
                    if (mediaPeriodHolder.d) {
                        mediaPeriodHolder.a(a2, Math.max(mediaPeriodHolder.f.b, this.K - mediaPeriodHolder.k));
                    }
                }
                b(true);
                if (this.x.e != 4) {
                    p();
                    i();
                    this.a.b(2);
                    return;
                }
                return;
            }
        }
    }

    private void m() {
        for (MediaPeriodHolder mediaPeriodHolder = this.s.d; mediaPeriodHolder != null; mediaPeriodHolder = mediaPeriodHolder.h) {
            for (ExoTrackSelection exoTrackSelection : mediaPeriodHolder.j.c) {
                if (exoTrackSelection != null) {
                    exoTrackSelection.i();
                }
            }
        }
    }

    private boolean n() {
        MediaPeriodHolder mediaPeriodHolder = this.s.d;
        long j2 = mediaPeriodHolder.f.e;
        if (mediaPeriodHolder.d) {
            return j2 == -9223372036854775807L || this.x.s < j2 || !v();
        }
        return false;
    }

    private void o() {
        MediaPeriodHolder mediaPeriodHolder = this.s.d;
        this.A = mediaPeriodHolder != null && mediaPeriodHolder.f.g && this.z;
    }

    private void p() {
        boolean q2 = q();
        this.C = q2;
        if (q2) {
            this.s.f.b(this.K);
        }
        s();
    }

    private boolean q() {
        if (!r()) {
            return false;
        }
        return this.h.a(b(this.s.f.d()), this.o.d().b);
    }

    private boolean r() {
        MediaPeriodHolder mediaPeriodHolder = this.s.f;
        return (mediaPeriodHolder == null || mediaPeriodHolder.d() == Long.MIN_VALUE) ? false : true;
    }

    private void s() {
        MediaPeriodHolder mediaPeriodHolder = this.s.f;
        boolean z2 = this.C || (mediaPeriodHolder != null && mediaPeriodHolder.a.f());
        if (z2 != this.x.g) {
            this.x = this.x.a(z2);
        }
    }

    private void t() {
        a(new boolean[this.d.length]);
    }

    private long u() {
        return b(this.x.q);
    }

    private boolean v() {
        return this.x.l && this.x.m == 0;
    }

    public final void a() {
        this.a.a(6).a();
    }

    public final void a(int i2, int i3, ShuffleOrder shuffleOrder) {
        this.a.a(20, i2, i3, shuffleOrder).a();
    }

    public final void a(PlaybackParameters playbackParameters) {
        this.a.a(16, playbackParameters).a();
    }

    public final synchronized void a(PlayerMessage playerMessage) {
        if (!this.c) {
            if (this.j.isAlive()) {
                this.a.a(14, playerMessage).a();
                return;
            }
        }
        Log.c("ExoPlayerImplInternal", "Ignoring messages sent after release.");
        playerMessage.a(false);
    }

    public final void a(MediaPeriod mediaPeriod) {
        this.a.a(8, mediaPeriod).a();
    }

    public final /* bridge */ /* synthetic */ void a(SequenceableLoader sequenceableLoader) {
        this.a.a(9, (MediaPeriod) sequenceableLoader).a();
    }

    public final void a(boolean z2, int i2) {
        this.a.a(1, z2 ? 1 : 0, i2).a();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0023, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean b() {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.c     // Catch:{ all -> 0x0025 }
            if (r0 != 0) goto L_0x0022
            android.os.HandlerThread r0 = r3.j     // Catch:{ all -> 0x0025 }
            boolean r0 = r0.isAlive()     // Catch:{ all -> 0x0025 }
            if (r0 != 0) goto L_0x000e
            goto L_0x0022
        L_0x000e:
            com.google.android.exoplayer2.util.HandlerWrapper r0 = r3.a     // Catch:{ all -> 0x0025 }
            r1 = 7
            r0.b(r1)     // Catch:{ all -> 0x0025 }
            com.google.android.exoplayer2.ExoPlayerImplInternal$$Lambda$1 r0 = new com.google.android.exoplayer2.ExoPlayerImplInternal$$Lambda$1     // Catch:{ all -> 0x0025 }
            r0.<init>(r3)     // Catch:{ all -> 0x0025 }
            long r1 = r3.v     // Catch:{ all -> 0x0025 }
            r3.a((com.dreamers.exoplayercore.repack.aK) r0, (long) r1)     // Catch:{ all -> 0x0025 }
            boolean r0 = r3.c     // Catch:{ all -> 0x0025 }
            monitor-exit(r3)
            return r0
        L_0x0022:
            monitor-exit(r3)
            r0 = 1
            return r0
        L_0x0025:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ExoPlayerImplInternal.b():boolean");
    }

    public final void c() {
        this.a.b(22);
    }

    public final void d() {
        this.a.b(10);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v16, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v17, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v21, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v22, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v24, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v26, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v28, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v4, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v32, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v6, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v35, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v7, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v58, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v93, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v107, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v13, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v14, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v176, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v179, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v180, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v181, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v182, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v184, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v185, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v187, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v188, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v193, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v194, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v195, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v196, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v197, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v199, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v202, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v205, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v206, resolved type: com.google.android.exoplayer2.ExoPlayerImplInternal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v214, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r2v23 */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x02a5, code lost:
        r2 = r11;
        r22 = r12;
        r23 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x0439, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x043a, code lost:
        r1 = r0;
        r2 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x043e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x043f, code lost:
        r1 = r0;
        r2 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0046, code lost:
        r11.x = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x0540, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:231:0x0541, code lost:
        r3 = r0;
        r2 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:232:0x0544, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:233:0x0545, code lost:
        r1 = false;
        r2 = r53;
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:234:0x0549, code lost:
        r22 = r12;
        r23 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:235:0x054f, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x0550, code lost:
        r2 = r53;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:237:0x0554, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x0555, code lost:
        r2 = r53;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0067, code lost:
        r11.b(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x009a, code lost:
        r11.a(r1, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:391:0x078b, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:392:0x078d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:393:0x078e, code lost:
        r2 = r53;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:394:0x0792, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:395:0x0793, code lost:
        r2 = r53;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:400:0x07a9, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:401:0x07aa, code lost:
        r22 = r12;
        r23 = r13;
        r3 = r0;
        r2 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:531:0x0939, code lost:
        if (r6 == false) goto L_0x093b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:578:0x09d4, code lost:
        if (r8.x.e != 3) goto L_0x09d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x014c, code lost:
        r1.a(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:657:0x0b1a, code lost:
        e();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:658:0x0b1e, code lost:
        r0 = e;
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:662:0x0b24, code lost:
        r0 = e;
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:665:0x0b2b, code lost:
        r0 = e;
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:678:0x0b66, code lost:
        r1 = r1.a((com.google.android.exoplayer2.source.MediaPeriodId) r3.f.a);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:691:0x0b9c, code lost:
        com.google.android.exoplayer2.util.Log.a(r4, "Recoverable renderer error", r1);
        r2.N = r1;
        r3 = r2.a;
        r3.a(r3.a(25, r1));
        r4 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:692:0x0bb0, code lost:
        r3 = r2.N;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:693:0x0bb2, code lost:
        if (r3 != null) goto L_0x0bb4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:694:0x0bb4, code lost:
        com.dreamers.exoplayercore.repack.cT.a(r3, r1);
        r1 = r2.N;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:695:0x0bb9, code lost:
        com.google.android.exoplayer2.util.Log.b(r4, r5, r1);
        r4 = true;
        r2.a(true, false);
        r2.x = r2.x.a(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0209, code lost:
        p();
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:7:0x001c, B:228:0x053c] */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:7:0x001c, B:239:0x0559] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:174:0x0439 A[ExcHandler: IOException (r0v34 'e' java.io.IOException A[CUSTOM_DECLARE]), PHI: r11 r12 r13 
      PHI: (r11v13 com.google.android.exoplayer2.ExoPlayerImplInternal) = (r11v0 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v6 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v6 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v6 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v6 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v7 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v7 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v7 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v7 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v6 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v6 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v6 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v6 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v6 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v11 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v11 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v11 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v0 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v0 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v0 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v0 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v0 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v0 com.google.android.exoplayer2.ExoPlayerImplInternal) binds: [B:182:0x0457, B:239:0x0559, B:240:?, B:328:0x068c, B:329:?, B:361:0x06fe, B:380:0x072a, B:381:?, B:364:0x0704, B:331:0x0692, B:248:0x0570, B:244:0x0567, B:242:0x055d, B:243:?, B:223:0x052b, B:228:0x053c, B:229:?, B:188:0x046c, B:120:0x035c, B:165:0x040d, B:162:0x03ef, B:148:0x03d1, B:7:0x001c] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r12v15 java.lang.String) = (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v9 java.lang.String), (r12v9 java.lang.String), (r12v9 java.lang.String), (r12v9 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String) binds: [B:182:0x0457, B:239:0x0559, B:240:?, B:328:0x068c, B:329:?, B:361:0x06fe, B:380:0x072a, B:381:?, B:364:0x0704, B:331:0x0692, B:248:0x0570, B:244:0x0567, B:242:0x055d, B:243:?, B:223:0x052b, B:228:0x053c, B:229:?, B:188:0x046c, B:120:0x035c, B:165:0x040d, B:162:0x03ef, B:148:0x03d1, B:7:0x001c] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r13v13 java.lang.String) = (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v8 java.lang.String), (r13v8 java.lang.String), (r13v8 java.lang.String), (r13v8 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String) binds: [B:182:0x0457, B:239:0x0559, B:240:?, B:328:0x068c, B:329:?, B:361:0x06fe, B:380:0x072a, B:381:?, B:364:0x0704, B:331:0x0692, B:248:0x0570, B:244:0x0567, B:242:0x055d, B:243:?, B:223:0x052b, B:228:0x053c, B:229:?, B:188:0x046c, B:120:0x035c, B:165:0x040d, B:162:0x03ef, B:148:0x03d1, B:7:0x001c] A[DONT_GENERATE, DONT_INLINE], Splitter:B:7:0x001c] */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x043e A[ExcHandler: ExoPlaybackException (r0v33 'e' com.google.android.exoplayer2.ExoPlaybackException A[CUSTOM_DECLARE]), PHI: r11 r12 r13 
      PHI: (r11v12 com.google.android.exoplayer2.ExoPlayerImplInternal) = (r11v0 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v6 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v6 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v6 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v6 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v7 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v7 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v7 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v7 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v6 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v6 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v6 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v6 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v6 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v11 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v11 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v11 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v0 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v0 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v0 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v0 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v0 com.google.android.exoplayer2.ExoPlayerImplInternal), (r11v0 com.google.android.exoplayer2.ExoPlayerImplInternal) binds: [B:182:0x0457, B:239:0x0559, B:240:?, B:328:0x068c, B:329:?, B:361:0x06fe, B:380:0x072a, B:381:?, B:364:0x0704, B:331:0x0692, B:248:0x0570, B:244:0x0567, B:242:0x055d, B:243:?, B:223:0x052b, B:228:0x053c, B:229:?, B:188:0x046c, B:120:0x035c, B:165:0x040d, B:162:0x03ef, B:148:0x03d1, B:7:0x001c] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r12v14 java.lang.String) = (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v9 java.lang.String), (r12v9 java.lang.String), (r12v9 java.lang.String), (r12v9 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String), (r12v0 java.lang.String) binds: [B:182:0x0457, B:239:0x0559, B:240:?, B:328:0x068c, B:329:?, B:361:0x06fe, B:380:0x072a, B:381:?, B:364:0x0704, B:331:0x0692, B:248:0x0570, B:244:0x0567, B:242:0x055d, B:243:?, B:223:0x052b, B:228:0x053c, B:229:?, B:188:0x046c, B:120:0x035c, B:165:0x040d, B:162:0x03ef, B:148:0x03d1, B:7:0x001c] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r13v12 java.lang.String) = (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v8 java.lang.String), (r13v8 java.lang.String), (r13v8 java.lang.String), (r13v8 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String), (r13v0 java.lang.String) binds: [B:182:0x0457, B:239:0x0559, B:240:?, B:328:0x068c, B:329:?, B:361:0x06fe, B:380:0x072a, B:381:?, B:364:0x0704, B:331:0x0692, B:248:0x0570, B:244:0x0567, B:242:0x055d, B:243:?, B:223:0x052b, B:228:0x053c, B:229:?, B:188:0x046c, B:120:0x035c, B:165:0x040d, B:162:0x03ef, B:148:0x03d1, B:7:0x001c] A[DONT_GENERATE, DONT_INLINE], Splitter:B:7:0x001c] */
    /* JADX WARNING: Removed duplicated region for block: B:201:0x0492 A[Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }] */
    /* JADX WARNING: Removed duplicated region for block: B:235:0x054f A[ExcHandler: IOException (e java.io.IOException), PHI: r12 r13 
      PHI: (r12v13 java.lang.String) = (r12v9 java.lang.String), (r12v9 java.lang.String), (r12v0 java.lang.String) binds: [B:382:0x0742, B:383:?, B:215:0x04ef] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r13v11 java.lang.String) = (r13v8 java.lang.String), (r13v8 java.lang.String), (r13v0 java.lang.String) binds: [B:382:0x0742, B:383:?, B:215:0x04ef] A[DONT_GENERATE, DONT_INLINE], Splitter:B:215:0x04ef] */
    /* JADX WARNING: Removed duplicated region for block: B:237:0x0554 A[ExcHandler: ExoPlaybackException (e com.google.android.exoplayer2.ExoPlaybackException), PHI: r12 r13 
      PHI: (r12v12 java.lang.String) = (r12v9 java.lang.String), (r12v9 java.lang.String), (r12v0 java.lang.String) binds: [B:382:0x0742, B:383:?, B:215:0x04ef] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r13v10 java.lang.String) = (r13v8 java.lang.String), (r13v8 java.lang.String), (r13v0 java.lang.String) binds: [B:382:0x0742, B:383:?, B:215:0x04ef] A[DONT_GENERATE, DONT_INLINE], Splitter:B:215:0x04ef] */
    /* JADX WARNING: Removed duplicated region for block: B:242:0x055d A[SYNTHETIC, Splitter:B:242:0x055d] */
    /* JADX WARNING: Removed duplicated region for block: B:244:0x0567 A[SYNTHETIC, Splitter:B:244:0x0567] */
    /* JADX WARNING: Removed duplicated region for block: B:248:0x0570 A[SYNTHETIC, Splitter:B:248:0x0570] */
    /* JADX WARNING: Removed duplicated region for block: B:331:0x0692 A[SYNTHETIC, Splitter:B:331:0x0692] */
    /* JADX WARNING: Removed duplicated region for block: B:364:0x0704 A[SYNTHETIC, Splitter:B:364:0x0704] */
    /* JADX WARNING: Removed duplicated region for block: B:378:0x0725 A[Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }] */
    /* JADX WARNING: Removed duplicated region for block: B:392:0x078d A[ExcHandler: IOException (e java.io.IOException), PHI: r22 r23 
      PHI: (r22v21 java.lang.String) = (r22v19 java.lang.String), (r22v19 java.lang.String), (r22v25 java.lang.String) binds: [B:604:0x0a1f, B:605:?, B:385:0x075b] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r23v21 java.lang.String) = (r23v19 java.lang.String), (r23v19 java.lang.String), (r23v25 java.lang.String) binds: [B:604:0x0a1f, B:605:?, B:385:0x075b] A[DONT_GENERATE, DONT_INLINE], Splitter:B:385:0x075b] */
    /* JADX WARNING: Removed duplicated region for block: B:394:0x0792 A[ExcHandler: ExoPlaybackException (e com.google.android.exoplayer2.ExoPlaybackException), PHI: r22 r23 
      PHI: (r22v20 java.lang.String) = (r22v19 java.lang.String), (r22v19 java.lang.String), (r22v25 java.lang.String) binds: [B:604:0x0a1f, B:605:?, B:385:0x075b] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r23v20 java.lang.String) = (r23v19 java.lang.String), (r23v19 java.lang.String), (r23v25 java.lang.String) binds: [B:604:0x0a1f, B:605:?, B:385:0x075b] A[DONT_GENERATE, DONT_INLINE], Splitter:B:385:0x075b] */
    /* JADX WARNING: Removed duplicated region for block: B:460:0x0854 A[Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }] */
    /* JADX WARNING: Removed duplicated region for block: B:482:0x0893 A[Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }] */
    /* JADX WARNING: Removed duplicated region for block: B:484:0x0899 A[SYNTHETIC, Splitter:B:484:0x0899] */
    /* JADX WARNING: Removed duplicated region for block: B:548:0x0971  */
    /* JADX WARNING: Removed duplicated region for block: B:570:0x09bf A[SYNTHETIC, Splitter:B:570:0x09bf] */
    /* JADX WARNING: Removed duplicated region for block: B:575:0x09cf A[SYNTHETIC, Splitter:B:575:0x09cf] */
    /* JADX WARNING: Removed duplicated region for block: B:587:0x09e4 A[Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }] */
    /* JADX WARNING: Removed duplicated region for block: B:588:0x09e6 A[Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }] */
    /* JADX WARNING: Removed duplicated region for block: B:591:0x09ed A[SYNTHETIC, Splitter:B:591:0x09ed] */
    /* JADX WARNING: Removed duplicated region for block: B:603:0x0a09 A[Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }] */
    /* JADX WARNING: Removed duplicated region for block: B:611:0x0a76  */
    /* JADX WARNING: Removed duplicated region for block: B:662:0x0b24 A[ExcHandler: IOException (e java.io.IOException), PHI: r2 r22 r23 
      PHI: (r2v14 com.google.android.exoplayer2.ExoPlayerImplInternal) = (r2v182 com.google.android.exoplayer2.ExoPlayerImplInternal), (r2v185 com.google.android.exoplayer2.ExoPlayerImplInternal), (r2v188 com.google.android.exoplayer2.ExoPlayerImplInternal), (r2v191 com.google.android.exoplayer2.ExoPlayerImplInternal), (r2v196 com.google.android.exoplayer2.ExoPlayerImplInternal), (r2v199 com.google.android.exoplayer2.ExoPlayerImplInternal), (r2v202 com.google.android.exoplayer2.ExoPlayerImplInternal), (r2v205 com.google.android.exoplayer2.ExoPlayerImplInternal), (r2v208 com.google.android.exoplayer2.ExoPlayerImplInternal), (r2v211 com.google.android.exoplayer2.ExoPlayerImplInternal) binds: [B:641:0x0aca, B:642:?, B:643:0x0acd, B:179:0x0447, B:402:0x07b2, B:613:0x0a78, B:614:?, B:615:0x0a7a, B:607:0x0a6e, B:608:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r22v8 java.lang.String) = (r22v13 java.lang.String), (r22v13 java.lang.String), (r22v13 java.lang.String), (r22v19 java.lang.String), (r22v19 java.lang.String), (r22v19 java.lang.String), (r22v19 java.lang.String), (r22v19 java.lang.String), (r22v19 java.lang.String) binds: [B:641:0x0aca, B:642:?, B:643:0x0acd, B:402:0x07b2, B:613:0x0a78, B:614:?, B:615:0x0a7a, B:607:0x0a6e, B:608:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r23v8 java.lang.String) = (r23v13 java.lang.String), (r23v13 java.lang.String), (r23v13 java.lang.String), (r23v19 java.lang.String), (r23v19 java.lang.String), (r23v19 java.lang.String), (r23v19 java.lang.String), (r23v19 java.lang.String), (r23v19 java.lang.String) binds: [B:641:0x0aca, B:642:?, B:643:0x0acd, B:402:0x07b2, B:613:0x0a78, B:614:?, B:615:0x0a7a, B:607:0x0a6e, B:608:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:607:0x0a6e] */
    /* JADX WARNING: Removed duplicated region for block: B:665:0x0b2b A[ExcHandler: ExoPlaybackException (e com.google.android.exoplayer2.ExoPlaybackException), PHI: r2 r22 r23 
      PHI: (r2v11 com.google.android.exoplayer2.ExoPlayerImplInternal) = (r2v181 com.google.android.exoplayer2.ExoPlayerImplInternal), (r2v184 com.google.android.exoplayer2.ExoPlayerImplInternal), (r2v187 com.google.android.exoplayer2.ExoPlayerImplInternal), (r2v190 com.google.android.exoplayer2.ExoPlayerImplInternal), (r2v195 com.google.android.exoplayer2.ExoPlayerImplInternal), (r2v198 com.google.android.exoplayer2.ExoPlayerImplInternal), (r2v201 com.google.android.exoplayer2.ExoPlayerImplInternal), (r2v204 com.google.android.exoplayer2.ExoPlayerImplInternal), (r2v207 com.google.android.exoplayer2.ExoPlayerImplInternal), (r2v210 com.google.android.exoplayer2.ExoPlayerImplInternal) binds: [B:641:0x0aca, B:642:?, B:643:0x0acd, B:179:0x0447, B:402:0x07b2, B:613:0x0a78, B:614:?, B:615:0x0a7a, B:607:0x0a6e, B:608:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r22v5 java.lang.String) = (r22v13 java.lang.String), (r22v13 java.lang.String), (r22v13 java.lang.String), (r22v19 java.lang.String), (r22v19 java.lang.String), (r22v19 java.lang.String), (r22v19 java.lang.String), (r22v19 java.lang.String), (r22v19 java.lang.String) binds: [B:641:0x0aca, B:642:?, B:643:0x0acd, B:402:0x07b2, B:613:0x0a78, B:614:?, B:615:0x0a7a, B:607:0x0a6e, B:608:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r23v5 java.lang.String) = (r23v13 java.lang.String), (r23v13 java.lang.String), (r23v13 java.lang.String), (r23v19 java.lang.String), (r23v19 java.lang.String), (r23v19 java.lang.String), (r23v19 java.lang.String), (r23v19 java.lang.String), (r23v19 java.lang.String) binds: [B:641:0x0aca, B:642:?, B:643:0x0acd, B:402:0x07b2, B:613:0x0a78, B:614:?, B:615:0x0a7a, B:607:0x0a6e, B:608:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:607:0x0a6e] */
    /* JADX WARNING: Removed duplicated region for block: B:678:0x0b66  */
    /* JADX WARNING: Removed duplicated region for block: B:691:0x0b9c  */
    /* JADX WARNING: Removed duplicated region for block: B:692:0x0bb0  */
    /* JADX WARNING: Removed duplicated region for block: B:718:0x07a1 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:722:0x0857 A[SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:239:0x0559=Splitter:B:239:0x0559, B:380:0x072a=Splitter:B:380:0x072a, B:120:0x035c=Splitter:B:120:0x035c, B:328:0x068c=Splitter:B:328:0x068c, B:361:0x06fe=Splitter:B:361:0x06fe} */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean handleMessage(android.os.Message r54) {
        /*
            r53 = this;
            r11 = r53
            r1 = r54
            java.lang.String r12 = "Playback error"
            java.lang.String r13 = "ExoPlayerImplInternal"
            r14 = 0
            r15 = 1
            int r2 = r1.what     // Catch:{ ExoPlaybackException -> 0x0b7c, IOException -> 0x0b57, RuntimeException -> 0x0b32 }
            r10 = 0
            r3 = 0
            r7 = 4
            r8 = 2
            r16 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            switch(r2) {
                case 0: goto L_0x0abc;
                case 1: goto L_0x0aa9;
                case 2: goto L_0x0443;
                case 3: goto L_0x02c6;
                case 4: goto L_0x02b3;
                case 5: goto L_0x02ac;
                case 6: goto L_0x02a2;
                case 7: goto L_0x029e;
                case 8: goto L_0x020e;
                case 9: goto L_0x01f6;
                case 10: goto L_0x01f1;
                case 11: goto L_0x01da;
                case 12: goto L_0x01be;
                case 13: goto L_0x01ae;
                case 14: goto L_0x0161;
                case 15: goto L_0x0135;
                case 16: goto L_0x012c;
                case 17: goto L_0x0101;
                case 18: goto L_0x00e0;
                case 19: goto L_0x00c8;
                case 20: goto L_0x009f;
                case 21: goto L_0x0077;
                case 22: goto L_0x006c;
                case 23: goto L_0x004a;
                case 24: goto L_0x0021;
                case 25: goto L_0x001c;
                default: goto L_0x0019;
            }
        L_0x0019:
            r2 = r11
            r1 = 0
            return r1
        L_0x001c:
            r11.a((boolean) r15)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            goto L_0x02a5
        L_0x0021:
            int r1 = r1.arg1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r1 != r15) goto L_0x0027
            r1 = 1
            goto L_0x0028
        L_0x0027:
            r1 = 0
        L_0x0028:
            boolean r2 = r11.H     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r1 == r2) goto L_0x02a5
            r11.H = r1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.PlaybackInfo r2 = r11.x     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            int r2 = r2.e     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r1 != 0) goto L_0x0040
            if (r2 == r7) goto L_0x0040
            if (r2 != r15) goto L_0x0039
            goto L_0x0040
        L_0x0039:
            com.google.android.exoplayer2.util.HandlerWrapper r1 = r11.a     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r1.b(r8)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            goto L_0x02a5
        L_0x0040:
            com.google.android.exoplayer2.PlaybackInfo r2 = r11.x     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.PlaybackInfo r1 = r2.b(r1)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
        L_0x0046:
            r11.x = r1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            goto L_0x02a5
        L_0x004a:
            int r1 = r1.arg1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r1 == 0) goto L_0x0050
            r1 = 1
            goto L_0x0051
        L_0x0050:
            r1 = 0
        L_0x0051:
            r11.z = r1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r53.o()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            boolean r1 = r11.A     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r1 == 0) goto L_0x02a5
            com.google.android.exoplayer2.MediaPeriodQueue r1 = r11.s     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodHolder r1 = r1.e     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodQueue r2 = r11.s     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodHolder r2 = r2.d     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r1 == r2) goto L_0x02a5
            r11.a((boolean) r15)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
        L_0x0067:
            r11.b((boolean) r14)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            goto L_0x02a5
        L_0x006c:
            com.google.android.exoplayer2.MediaSourceList r1 = r11.t     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.Timeline r1 = r1.c()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r11.a((com.google.android.exoplayer2.Timeline) r1, (boolean) r15)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            goto L_0x02a5
        L_0x0077:
            java.lang.Object r1 = r1.obj     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.source.ShuffleOrder r1 = (com.google.android.exoplayer2.source.ShuffleOrder) r1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfoUpdate r2 = r11.y     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r2.a((int) r15)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaSourceList r2 = r11.t     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            int r3 = r2.a()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            int r4 = r1.a()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r4 == r3) goto L_0x0094
            com.google.android.exoplayer2.source.ShuffleOrder r1 = r1.d()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.source.ShuffleOrder r1 = r1.a(r14, r3)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
        L_0x0094:
            r2.g = r1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.Timeline r1 = r2.c()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
        L_0x009a:
            r11.a((com.google.android.exoplayer2.Timeline) r1, (boolean) r14)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            goto L_0x02a5
        L_0x009f:
            int r2 = r1.arg1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            int r3 = r1.arg2     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            java.lang.Object r1 = r1.obj     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.source.ShuffleOrder r1 = (com.google.android.exoplayer2.source.ShuffleOrder) r1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfoUpdate r4 = r11.y     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r4.a((int) r15)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaSourceList r4 = r11.t     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r2 < 0) goto L_0x00ba
            if (r2 > r3) goto L_0x00ba
            int r5 = r4.a()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r3 > r5) goto L_0x00ba
            r5 = 1
            goto L_0x00bb
        L_0x00ba:
            r5 = 0
        L_0x00bb:
            com.google.android.exoplayer2.util.Assertions.a((boolean) r5)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r4.g = r1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r4.a((int) r2, (int) r3)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.Timeline r1 = r4.c()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            goto L_0x009a
        L_0x00c8:
            java.lang.Object r1 = r1.obj     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.ExoPlayerImplInternal$MoveMediaItemsMessage r1 = (com.google.android.exoplayer2.ExoPlayerImplInternal.MoveMediaItemsMessage) r1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfoUpdate r2 = r11.y     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r2.a((int) r15)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaSourceList r2 = r11.t     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            int r3 = r1.a     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            int r4 = r1.b     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            int r5 = r1.c     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.source.ShuffleOrder r1 = r1.d     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.Timeline r1 = r2.a(r3, r4, r5, r1)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            goto L_0x009a
        L_0x00e0:
            java.lang.Object r2 = r1.obj     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.ExoPlayerImplInternal$MediaSourceListUpdateMessage r2 = (com.google.android.exoplayer2.ExoPlayerImplInternal.MediaSourceListUpdateMessage) r2     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            int r1 = r1.arg1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfoUpdate r3 = r11.y     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r3.a((int) r15)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaSourceList r3 = r11.t     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r4 = -1
            if (r1 != r4) goto L_0x00f4
            int r1 = r3.a()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
        L_0x00f4:
            java.util.List r4 = r2.a     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.source.ShuffleOrder r2 = r2.b     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.Timeline r1 = r3.a(r1, r4, r2)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            goto L_0x009a
        L_0x0101:
            java.lang.Object r1 = r1.obj     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.ExoPlayerImplInternal$MediaSourceListUpdateMessage r1 = (com.google.android.exoplayer2.ExoPlayerImplInternal.MediaSourceListUpdateMessage) r1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfoUpdate r2 = r11.y     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r2.a((int) r15)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.ExoPlayerImplInternal.MediaSourceListUpdateMessage.a()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaSourceList r2 = r11.t     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            java.util.List r3 = r1.a     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.source.ShuffleOrder r1 = r1.b     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            java.util.List r4 = r2.a     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            int r4 = r4.size()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r2.a((int) r14, (int) r4)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            java.util.List r4 = r2.a     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            int r4 = r4.size()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.Timeline r1 = r2.a(r4, r3, r1)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            goto L_0x009a
        L_0x012c:
            java.lang.Object r1 = r1.obj     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.PlaybackParameters r1 = (com.google.android.exoplayer2.PlaybackParameters) r1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r11.a((com.google.android.exoplayer2.PlaybackParameters) r1, (boolean) r14)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            goto L_0x02a5
        L_0x0135:
            java.lang.Object r1 = r1.obj     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.PlayerMessage r1 = (com.google.android.exoplayer2.PlayerMessage) r1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            android.os.Looper r2 = r1.e     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            java.lang.Thread r3 = r2.getThread()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            boolean r3 = r3.isAlive()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r3 != 0) goto L_0x0151
            java.lang.String r2 = "TAG"
            java.lang.String r3 = "Trying to send message on a dead thread."
            com.google.android.exoplayer2.util.Log.c(r2, r3)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
        L_0x014c:
            r1.a((boolean) r14)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            goto L_0x02a5
        L_0x0151:
            com.google.android.exoplayer2.util.Clock r3 = r11.q     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.util.HandlerWrapper r2 = r3.a(r2, r10)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.ExoPlayerImplInternal$$Lambda$2 r3 = new com.google.android.exoplayer2.ExoPlayerImplInternal$$Lambda$2     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r3.<init>(r1)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r2.a((java.lang.Runnable) r3)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            goto L_0x02a5
        L_0x0161:
            java.lang.Object r1 = r1.obj     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.PlayerMessage r1 = (com.google.android.exoplayer2.PlayerMessage) r1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r2 = r1.g     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            int r4 = (r2 > r16 ? 1 : (r2 == r16 ? 0 : -1))
            if (r4 != 0) goto L_0x0170
            r11.c((com.google.android.exoplayer2.PlayerMessage) r1)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            goto L_0x02a5
        L_0x0170:
            com.google.android.exoplayer2.PlaybackInfo r2 = r11.x     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.Timeline r2 = r2.a     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            boolean r2 = r2.c()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r2 == 0) goto L_0x0186
            java.util.ArrayList r2 = r11.p     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo r3 = new com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r3.<init>(r1)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r2.add(r3)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            goto L_0x02a5
        L_0x0186:
            com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo r2 = new com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r2.<init>(r1)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.PlaybackInfo r3 = r11.x     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.Timeline r5 = r3.a     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.PlaybackInfo r3 = r11.x     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.Timeline r6 = r3.a     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            int r7 = r11.D     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            boolean r8 = r11.E     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.Timeline$Window r9 = r11.k     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.Timeline$Period r10 = r11.l     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r4 = r2
            boolean r3 = a((com.google.android.exoplayer2.ExoPlayerImplInternal.PendingMessageInfo) r4, (com.google.android.exoplayer2.Timeline) r5, (com.google.android.exoplayer2.Timeline) r6, (int) r7, (boolean) r8, (com.google.android.exoplayer2.Timeline.Window) r9, (com.google.android.exoplayer2.Timeline.Period) r10)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r3 == 0) goto L_0x014c
            java.util.ArrayList r1 = r11.p     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r1.add(r2)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            java.util.ArrayList r1 = r11.p     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            java.util.Collections.sort(r1)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            goto L_0x02a5
        L_0x01ae:
            int r2 = r1.arg1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r2 == 0) goto L_0x01b4
            r2 = 1
            goto L_0x01b5
        L_0x01b4:
            r2 = 0
        L_0x01b5:
            java.lang.Object r1 = r1.obj     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            java.util.concurrent.atomic.AtomicBoolean r1 = (java.util.concurrent.atomic.AtomicBoolean) r1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r11.a((boolean) r2, (java.util.concurrent.atomic.AtomicBoolean) r1)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            goto L_0x02a5
        L_0x01be:
            int r1 = r1.arg1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r1 == 0) goto L_0x01c4
            r1 = 1
            goto L_0x01c5
        L_0x01c4:
            r1 = 0
        L_0x01c5:
            r11.E = r1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodQueue r2 = r11.s     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.PlaybackInfo r3 = r11.x     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.Timeline r3 = r3.a     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r2.c = r1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            boolean r1 = r2.a((com.google.android.exoplayer2.Timeline) r3)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r1 != 0) goto L_0x0067
            r11.a((boolean) r15)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            goto L_0x0067
        L_0x01da:
            int r1 = r1.arg1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r11.D = r1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodQueue r2 = r11.s     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.PlaybackInfo r3 = r11.x     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.Timeline r3 = r3.a     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r2.b = r1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            boolean r1 = r2.a((com.google.android.exoplayer2.Timeline) r3)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r1 != 0) goto L_0x0067
            r11.a((boolean) r15)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            goto L_0x0067
        L_0x01f1:
            r53.l()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            goto L_0x02a5
        L_0x01f6:
            java.lang.Object r1 = r1.obj     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.source.MediaPeriod r1 = (com.google.android.exoplayer2.source.MediaPeriod) r1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodQueue r2 = r11.s     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            boolean r1 = r2.a((com.google.android.exoplayer2.source.MediaPeriod) r1)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r1 == 0) goto L_0x02a5
            com.google.android.exoplayer2.MediaPeriodQueue r1 = r11.s     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r2 = r11.K     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r1.a((long) r2)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
        L_0x0209:
            r53.p()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            goto L_0x02a5
        L_0x020e:
            java.lang.Object r1 = r1.obj     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.source.MediaPeriod r1 = (com.google.android.exoplayer2.source.MediaPeriod) r1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodQueue r2 = r11.s     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            boolean r1 = r2.a((com.google.android.exoplayer2.source.MediaPeriod) r1)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r1 == 0) goto L_0x02a5
            com.google.android.exoplayer2.MediaPeriodQueue r1 = r11.s     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodHolder r1 = r1.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.DefaultMediaClock r2 = r11.o     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.PlaybackParameters r2 = r2.d()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            float r2 = r2.b     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.PlaybackInfo r5 = r11.x     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.Timeline r5 = r5.a     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r1.d = r15     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.source.MediaPeriod r6 = r1.a     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.source.TrackGroupArray r6 = r6.b()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r1.i = r6     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.trackselection.TrackSelectorResult r2 = r1.a((float) r2, (com.google.android.exoplayer2.Timeline) r5)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodInfo r5 = r1.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r5 = r5.b     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodInfo r7 = r1.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r7 = r7.e     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            int r9 = (r7 > r16 ? 1 : (r7 == r16 ? 0 : -1))
            if (r9 == 0) goto L_0x0257
            com.google.android.exoplayer2.MediaPeriodInfo r7 = r1.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r7 = r7.e     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 < 0) goto L_0x0257
            com.google.android.exoplayer2.MediaPeriodInfo r5 = r1.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r5 = r5.e     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r7 = 1
            long r5 = r5 - r7
            long r5 = java.lang.Math.max(r3, r5)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
        L_0x0257:
            long r2 = r1.a((com.google.android.exoplayer2.trackselection.TrackSelectorResult) r2, (long) r5)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r4 = r1.k     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodInfo r6 = r1.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r6 = r6.b     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r6 = r6 - r2
            long r4 = r4 + r6
            r1.k = r4     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodInfo r4 = r1.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodInfo r2 = r4.a(r2)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r1.f = r2     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.trackselection.TrackSelectorResult r2 = r1.j     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r11.a((com.google.android.exoplayer2.trackselection.TrackSelectorResult) r2)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodQueue r2 = r11.s     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodHolder r2 = r2.d     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r1 != r2) goto L_0x0209
            com.google.android.exoplayer2.MediaPeriodInfo r2 = r1.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r2 = r2.b     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r11.a((long) r2)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r53.t()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.PlaybackInfo r2 = r11.x     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r2 = r2.b     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodInfo r3 = r1.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r3 = r3.b     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.PlaybackInfo r5 = r11.x     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r5 = r5.c     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodInfo r1 = r1.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r7 = r1.b     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r9 = 0
            r10 = 5
            r1 = r53
            com.google.android.exoplayer2.PlaybackInfo r1 = r1.a(r2, r3, r5, r7, r9, r10)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r11.x = r1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            goto L_0x0209
        L_0x029e:
            r53.k()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            return r15
        L_0x02a2:
            r11.a((boolean) r14, (boolean) r15)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
        L_0x02a5:
            r2 = r11
            r22 = r12
            r23 = r13
            goto L_0x0b1a
        L_0x02ac:
            java.lang.Object r1 = r1.obj     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.SeekParameters r1 = (com.google.android.exoplayer2.SeekParameters) r1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r11.w = r1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            goto L_0x02a5
        L_0x02b3:
            java.lang.Object r1 = r1.obj     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.PlaybackParameters r1 = (com.google.android.exoplayer2.PlaybackParameters) r1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.DefaultMediaClock r2 = r11.o     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r2.a((com.google.android.exoplayer2.PlaybackParameters) r1)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.DefaultMediaClock r1 = r11.o     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.PlaybackParameters r1 = r1.d()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r11.a((com.google.android.exoplayer2.PlaybackParameters) r1, (boolean) r15)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            goto L_0x02a5
        L_0x02c6:
            java.lang.Object r1 = r1.obj     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.ExoPlayerImplInternal$SeekPosition r1 = (com.google.android.exoplayer2.ExoPlayerImplInternal.SeekPosition) r1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfoUpdate r2 = r11.y     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r2.a((int) r15)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.PlaybackInfo r2 = r11.x     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.Timeline r2 = r2.a     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r20 = 1
            int r5 = r11.D     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            boolean r6 = r11.E     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.Timeline$Window r10 = r11.k     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.Timeline$Period r9 = r11.l     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r18 = r2
            r19 = r1
            r21 = r5
            r22 = r6
            r23 = r10
            r24 = r9
            android.util.Pair r2 = a((com.google.android.exoplayer2.Timeline) r18, (com.google.android.exoplayer2.ExoPlayerImplInternal.SeekPosition) r19, (boolean) r20, (int) r21, (boolean) r22, (com.google.android.exoplayer2.Timeline.Window) r23, (com.google.android.exoplayer2.Timeline.Period) r24)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r2 != 0) goto L_0x0310
            com.google.android.exoplayer2.PlaybackInfo r5 = r11.x     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.Timeline r5 = r5.a     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            android.util.Pair r5 = r11.a((com.google.android.exoplayer2.Timeline) r5)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            java.lang.Object r6 = r5.first     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r6 = (com.google.android.exoplayer2.source.MediaSource.MediaPeriodId) r6     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            java.lang.Object r5 = r5.second     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            java.lang.Long r5 = (java.lang.Long) r5     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r9 = r5.longValue()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.PlaybackInfo r5 = r11.x     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.Timeline r5 = r5.a     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            boolean r5 = r5.c()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r5 = r5 ^ r15
            r22 = r5
            r8 = r6
            goto L_0x035c
        L_0x0310:
            java.lang.Object r5 = r2.first     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            java.lang.Object r6 = r2.second     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            java.lang.Long r6 = (java.lang.Long) r6     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r9 = r6.longValue()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r3 = r1.c     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            int r6 = (r3 > r16 ? 1 : (r3 == r16 ? 0 : -1))
            if (r6 != 0) goto L_0x0323
            r3 = r16
            goto L_0x0324
        L_0x0323:
            r3 = r9
        L_0x0324:
            com.google.android.exoplayer2.MediaPeriodQueue r6 = r11.s     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.PlaybackInfo r8 = r11.x     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.Timeline r8 = r8.a     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r5 = r6.a((com.google.android.exoplayer2.Timeline) r8, (java.lang.Object) r5, (long) r9)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            boolean r6 = r5.a()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r6 == 0) goto L_0x034e
            com.google.android.exoplayer2.PlaybackInfo r6 = r11.x     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.Timeline r6 = r6.a     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            java.lang.Object r8 = r5.a     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.Timeline$Period r9 = r11.l     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r6.a(r8, r9)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.Timeline$Period r6 = r11.l     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            int r8 = r5.b     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r6.b((int) r8)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r16 = r3
            r8 = r5
            r9 = 0
            r22 = 1
            goto L_0x035c
        L_0x034e:
            long r7 = r1.c     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            int r6 = (r7 > r16 ? 1 : (r7 == r16 ? 0 : -1))
            if (r6 != 0) goto L_0x0356
            r6 = 1
            goto L_0x0357
        L_0x0356:
            r6 = 0
        L_0x0357:
            r16 = r3
            r8 = r5
            r22 = r6
        L_0x035c:
            com.google.android.exoplayer2.PlaybackInfo r3 = r11.x     // Catch:{ all -> 0x0419 }
            com.google.android.exoplayer2.Timeline r3 = r3.a     // Catch:{ all -> 0x0419 }
            boolean r3 = r3.c()     // Catch:{ all -> 0x0419 }
            if (r3 == 0) goto L_0x0369
            r11.J = r1     // Catch:{ all -> 0x0419 }
            goto L_0x0378
        L_0x0369:
            if (r2 != 0) goto L_0x037e
            com.google.android.exoplayer2.PlaybackInfo r1 = r11.x     // Catch:{ all -> 0x0419 }
            int r1 = r1.e     // Catch:{ all -> 0x0419 }
            if (r1 == r15) goto L_0x0375
            r1 = 4
            r11.a((int) r1)     // Catch:{ all -> 0x0419 }
        L_0x0375:
            r11.a((boolean) r14, (boolean) r15, (boolean) r14, (boolean) r15)     // Catch:{ all -> 0x0419 }
        L_0x0378:
            r18 = r9
            r9 = r22
            goto L_0x0403
        L_0x037e:
            com.google.android.exoplayer2.PlaybackInfo r1 = r11.x     // Catch:{ all -> 0x0419 }
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r1 = r1.b     // Catch:{ all -> 0x0419 }
            boolean r1 = r8.equals(r1)     // Catch:{ all -> 0x0419 }
            if (r1 == 0) goto L_0x03d7
            com.google.android.exoplayer2.MediaPeriodQueue r1 = r11.s     // Catch:{ all -> 0x0419 }
            com.google.android.exoplayer2.MediaPeriodHolder r1 = r1.d     // Catch:{ all -> 0x0419 }
            if (r1 == 0) goto L_0x03a1
            boolean r2 = r1.d     // Catch:{ all -> 0x0419 }
            if (r2 == 0) goto L_0x03a1
            r2 = 0
            int r4 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x03a1
            com.google.android.exoplayer2.source.MediaPeriod r1 = r1.a     // Catch:{ all -> 0x0419 }
            com.google.android.exoplayer2.SeekParameters r2 = r11.w     // Catch:{ all -> 0x0419 }
            long r1 = r1.a((long) r9, (com.google.android.exoplayer2.SeekParameters) r2)     // Catch:{ all -> 0x0419 }
            goto L_0x03a2
        L_0x03a1:
            r1 = r9
        L_0x03a2:
            long r3 = com.google.android.exoplayer2.C.a((long) r1)     // Catch:{ all -> 0x0419 }
            com.google.android.exoplayer2.PlaybackInfo r5 = r11.x     // Catch:{ all -> 0x0419 }
            long r5 = r5.s     // Catch:{ all -> 0x0419 }
            long r5 = com.google.android.exoplayer2.C.a((long) r5)     // Catch:{ all -> 0x0419 }
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x03d8
            com.google.android.exoplayer2.PlaybackInfo r3 = r11.x     // Catch:{ all -> 0x0419 }
            int r3 = r3.e     // Catch:{ all -> 0x0419 }
            r7 = 2
            if (r3 == r7) goto L_0x03c0
            com.google.android.exoplayer2.PlaybackInfo r3 = r11.x     // Catch:{ all -> 0x0419 }
            int r3 = r3.e     // Catch:{ all -> 0x0419 }
            r5 = 3
            if (r3 != r5) goto L_0x03d8
        L_0x03c0:
            com.google.android.exoplayer2.PlaybackInfo r1 = r11.x     // Catch:{ all -> 0x0419 }
            long r9 = r1.s     // Catch:{ all -> 0x0419 }
            r18 = 2
            r1 = r53
            r2 = r8
            r3 = r9
            r5 = r16
            r7 = r9
            r9 = r22
            r10 = r18
            com.google.android.exoplayer2.PlaybackInfo r1 = r1.a(r2, r3, r5, r7, r9, r10)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            goto L_0x0046
        L_0x03d7:
            r1 = r9
        L_0x03d8:
            com.google.android.exoplayer2.PlaybackInfo r3 = r11.x     // Catch:{ all -> 0x0419 }
            int r3 = r3.e     // Catch:{ all -> 0x0419 }
            r6 = 4
            if (r3 != r6) goto L_0x03e1
            r3 = 1
            goto L_0x03e2
        L_0x03e1:
            r3 = 0
        L_0x03e2:
            long r18 = r11.a((com.google.android.exoplayer2.source.MediaSource.MediaPeriodId) r8, (long) r1, (boolean) r3)     // Catch:{ all -> 0x0419 }
            int r1 = (r9 > r18 ? 1 : (r9 == r18 ? 0 : -1))
            if (r1 == 0) goto L_0x03ec
            r1 = 1
            goto L_0x03ed
        L_0x03ec:
            r1 = 0
        L_0x03ed:
            r9 = r22 | r1
            com.google.android.exoplayer2.PlaybackInfo r1 = r11.x     // Catch:{ all -> 0x0413 }
            com.google.android.exoplayer2.Timeline r2 = r1.a     // Catch:{ all -> 0x0413 }
            com.google.android.exoplayer2.PlaybackInfo r1 = r11.x     // Catch:{ all -> 0x0413 }
            com.google.android.exoplayer2.Timeline r4 = r1.a     // Catch:{ all -> 0x0413 }
            com.google.android.exoplayer2.PlaybackInfo r1 = r11.x     // Catch:{ all -> 0x0413 }
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r5 = r1.b     // Catch:{ all -> 0x0413 }
            r1 = r53
            r3 = r8
            r6 = r16
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0413 }
        L_0x0403:
            r10 = 2
            r1 = r53
            r2 = r8
            r3 = r18
            r5 = r16
            r7 = r18
            com.google.android.exoplayer2.PlaybackInfo r1 = r1.a(r2, r3, r5, r7, r9, r10)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            goto L_0x0046
        L_0x0413:
            r0 = move-exception
            r22 = r9
            r9 = r18
            goto L_0x041a
        L_0x0419:
            r0 = move-exception
        L_0x041a:
            r18 = r0
            r19 = 2
            r1 = r53
            r2 = r8
            r3 = r9
            r5 = r16
            r7 = r9
            r9 = r22
            r10 = r19
            com.google.android.exoplayer2.PlaybackInfo r1 = r1.a(r2, r3, r5, r7, r9, r10)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r11.x = r1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            throw r18     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
        L_0x0430:
            r0 = move-exception
            r3 = r0
            r2 = r11
            r22 = r12
            r23 = r13
            goto L_0x0b22
        L_0x0439:
            r0 = move-exception
            r1 = r0
            r2 = r11
            goto L_0x0a9e
        L_0x043e:
            r0 = move-exception
            r1 = r0
            r2 = r11
            goto L_0x0aa5
        L_0x0443:
            r2 = r3
            r5 = 3
            r6 = 4
            r7 = 2
            com.google.android.exoplayer2.util.Clock r1 = r11.q     // Catch:{ ExoPlaybackException -> 0x0aa2, IOException -> 0x0a9b, RuntimeException -> 0x0a93 }
            long r8 = r1.b()     // Catch:{ ExoPlaybackException -> 0x0aa2, IOException -> 0x0a9b, RuntimeException -> 0x0a93 }
            com.google.android.exoplayer2.PlaybackInfo r1 = r11.x     // Catch:{ ExoPlaybackException -> 0x0aa2, IOException -> 0x0a9b, RuntimeException -> 0x0a93 }
            com.google.android.exoplayer2.Timeline r1 = r1.a     // Catch:{ ExoPlaybackException -> 0x0aa2, IOException -> 0x0a9b, RuntimeException -> 0x0a93 }
            boolean r1 = r1.c()     // Catch:{ ExoPlaybackException -> 0x0aa2, IOException -> 0x0a9b, RuntimeException -> 0x0a93 }
            if (r1 != 0) goto L_0x07a1
            com.google.android.exoplayer2.MediaSourceList r1 = r11.t     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x07a9 }
            boolean r1 = r1.h     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x07a9 }
            if (r1 != 0) goto L_0x045f
            goto L_0x07a1
        L_0x045f:
            com.google.android.exoplayer2.MediaPeriodQueue r1 = r11.s     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x07a9 }
            long r2 = r11.K     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x07a9 }
            r1.a((long) r2)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x07a9 }
            com.google.android.exoplayer2.MediaPeriodQueue r1 = r11.s     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x07a9 }
            com.google.android.exoplayer2.MediaPeriodHolder r2 = r1.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x07a9 }
            if (r2 == 0) goto L_0x048f
            com.google.android.exoplayer2.MediaPeriodHolder r2 = r1.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodInfo r2 = r2.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            boolean r2 = r2.h     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r2 != 0) goto L_0x048d
            com.google.android.exoplayer2.MediaPeriodHolder r2 = r1.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            boolean r2 = r2.b()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r2 == 0) goto L_0x048d
            com.google.android.exoplayer2.MediaPeriodHolder r2 = r1.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodInfo r2 = r2.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r2 = r2.e     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            int r4 = (r2 > r16 ? 1 : (r2 == r16 ? 0 : -1))
            if (r4 == 0) goto L_0x048d
            int r1 = r1.g     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r2 = 100
            if (r1 >= r2) goto L_0x048d
            goto L_0x048f
        L_0x048d:
            r1 = 0
            goto L_0x0490
        L_0x048f:
            r1 = 1
        L_0x0490:
            if (r1 == 0) goto L_0x0559
            com.google.android.exoplayer2.MediaPeriodQueue r1 = r11.s     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r2 = r11.K     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.PlaybackInfo r4 = r11.x     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodHolder r5 = r1.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r5 != 0) goto L_0x04b3
            com.google.android.exoplayer2.Timeline r2 = r4.a     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r3 = r4.b     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r6 = r4.c     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r14 = r4.s     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r20 = r1
            r21 = r2
            r22 = r3
            r23 = r6
            r25 = r14
            com.google.android.exoplayer2.MediaPeriodInfo r1 = r20.a(r21, r22, r23, r25)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            goto L_0x04bb
        L_0x04b3:
            com.google.android.exoplayer2.Timeline r4 = r4.a     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodHolder r6 = r1.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodInfo r1 = r1.a((com.google.android.exoplayer2.Timeline) r4, (com.google.android.exoplayer2.MediaPeriodHolder) r6, (long) r2)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
        L_0x04bb:
            if (r1 == 0) goto L_0x0559
            com.google.android.exoplayer2.MediaPeriodQueue r2 = r11.s     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.RendererCapabilities[] r3 = r11.e     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.trackselection.TrackSelector r4 = r11.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.LoadControl r6 = r11.h     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.upstream.Allocator r33 = r6.d()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaSourceList r6 = r11.t     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.trackselection.TrackSelectorResult r7 = r11.g     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodHolder r14 = r2.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r14 != 0) goto L_0x04e7
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r14 = r1.a     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            boolean r14 = r14.a()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r14 == 0) goto L_0x04e4
            long r14 = r1.c     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            int r20 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r20 == 0) goto L_0x04e4
            long r14 = r1.c     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r30 = r14
            goto L_0x04f8
        L_0x04e4:
            r30 = 0
            goto L_0x04f8
        L_0x04e7:
            com.google.android.exoplayer2.MediaPeriodHolder r14 = r2.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r14 = r14.k     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodHolder r5 = r2.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodInfo r5 = r5.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r10 = r5.e     // Catch:{ ExoPlaybackException -> 0x0554, IOException -> 0x054f, RuntimeException -> 0x0544 }
            long r14 = r14 + r10
            long r10 = r1.b     // Catch:{ ExoPlaybackException -> 0x0554, IOException -> 0x054f, RuntimeException -> 0x0544 }
            long r10 = r14 - r10
            r30 = r10
        L_0x04f8:
            com.google.android.exoplayer2.MediaPeriodHolder r5 = new com.google.android.exoplayer2.MediaPeriodHolder     // Catch:{ ExoPlaybackException -> 0x0554, IOException -> 0x054f, RuntimeException -> 0x0544 }
            r28 = r5
            r29 = r3
            r32 = r4
            r34 = r6
            r35 = r1
            r36 = r7
            r28.<init>(r29, r30, r32, r33, r34, r35, r36)     // Catch:{ ExoPlaybackException -> 0x0554, IOException -> 0x054f, RuntimeException -> 0x0544 }
            com.google.android.exoplayer2.MediaPeriodHolder r3 = r2.f     // Catch:{ ExoPlaybackException -> 0x0554, IOException -> 0x054f, RuntimeException -> 0x0544 }
            if (r3 == 0) goto L_0x0513
            com.google.android.exoplayer2.MediaPeriodHolder r3 = r2.f     // Catch:{ ExoPlaybackException -> 0x0554, IOException -> 0x054f, RuntimeException -> 0x0544 }
            r3.a((com.google.android.exoplayer2.MediaPeriodHolder) r5)     // Catch:{ ExoPlaybackException -> 0x0554, IOException -> 0x054f, RuntimeException -> 0x0544 }
            goto L_0x0517
        L_0x0513:
            r2.d = r5     // Catch:{ ExoPlaybackException -> 0x0554, IOException -> 0x054f, RuntimeException -> 0x0544 }
            r2.e = r5     // Catch:{ ExoPlaybackException -> 0x0554, IOException -> 0x054f, RuntimeException -> 0x0544 }
        L_0x0517:
            r10 = 0
            r2.h = r10     // Catch:{ ExoPlaybackException -> 0x0554, IOException -> 0x054f, RuntimeException -> 0x0544 }
            r2.f = r5     // Catch:{ ExoPlaybackException -> 0x0554, IOException -> 0x054f, RuntimeException -> 0x0544 }
            int r3 = r2.g     // Catch:{ ExoPlaybackException -> 0x0554, IOException -> 0x054f, RuntimeException -> 0x0544 }
            r4 = 1
            int r3 = r3 + r4
            r2.g = r3     // Catch:{ ExoPlaybackException -> 0x0554, IOException -> 0x054f, RuntimeException -> 0x0544 }
            r2.c()     // Catch:{ ExoPlaybackException -> 0x0554, IOException -> 0x054f, RuntimeException -> 0x0544 }
            com.google.android.exoplayer2.source.MediaPeriod r2 = r5.a     // Catch:{ ExoPlaybackException -> 0x0554, IOException -> 0x054f, RuntimeException -> 0x0544 }
            long r3 = r1.b     // Catch:{ ExoPlaybackException -> 0x0554, IOException -> 0x054f, RuntimeException -> 0x0544 }
            r11 = r53
            r2.a((com.google.android.exoplayer2.source.MediaPeriod.Callback) r11, (long) r3)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodQueue r1 = r11.s     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodHolder r1 = r1.d     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r1 != r5) goto L_0x053b
            long r1 = r5.a()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r11.a((long) r1)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
        L_0x053b:
            r1 = 0
            r11.b((boolean) r1)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0540 }
            goto L_0x0559
        L_0x0540:
            r0 = move-exception
            r3 = r0
            r2 = r11
            goto L_0x0549
        L_0x0544:
            r0 = move-exception
            r1 = 0
            r2 = r53
            r3 = r0
        L_0x0549:
            r22 = r12
            r23 = r13
            goto L_0x0b3a
        L_0x054f:
            r0 = move-exception
            r2 = r53
            goto L_0x0a9d
        L_0x0554:
            r0 = move-exception
            r2 = r53
            goto L_0x0aa4
        L_0x0559:
            boolean r1 = r11.C     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x07a9 }
            if (r1 == 0) goto L_0x0567
            boolean r1 = r53.r()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r11.C = r1     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r53.s()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            goto L_0x056a
        L_0x0567:
            r53.p()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x07a9 }
        L_0x056a:
            com.google.android.exoplayer2.MediaPeriodQueue r1 = r11.s     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x07a9 }
            com.google.android.exoplayer2.MediaPeriodHolder r1 = r1.e     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x07a9 }
            if (r1 == 0) goto L_0x068c
            com.google.android.exoplayer2.MediaPeriodHolder r2 = r1.h     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r2 == 0) goto L_0x0646
            boolean r2 = r11.A     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r2 == 0) goto L_0x057a
            goto L_0x0646
        L_0x057a:
            com.google.android.exoplayer2.MediaPeriodQueue r2 = r11.s     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodHolder r2 = r2.e     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            boolean r3 = r2.d     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r3 != 0) goto L_0x0584
        L_0x0582:
            r2 = 0
            goto L_0x05a3
        L_0x0584:
            r3 = 0
        L_0x0585:
            com.google.android.exoplayer2.Renderer[] r4 = r11.d     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            int r5 = r4.length     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r3 >= r5) goto L_0x05a2
            r4 = r4[r3]     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.source.SampleStream[] r5 = r2.c     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r5 = r5[r3]     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.source.SampleStream r6 = r4.f()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r6 != r5) goto L_0x0582
            if (r5 == 0) goto L_0x059f
            boolean r4 = r4.g()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r4 != 0) goto L_0x059f
            goto L_0x0582
        L_0x059f:
            int r3 = r3 + 1
            goto L_0x0585
        L_0x05a2:
            r2 = 1
        L_0x05a3:
            if (r2 == 0) goto L_0x068c
            com.google.android.exoplayer2.MediaPeriodHolder r2 = r1.h     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            boolean r2 = r2.d     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r2 != 0) goto L_0x05b7
            long r2 = r11.K     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodHolder r4 = r1.h     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r4 = r4.a()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 < 0) goto L_0x068c
        L_0x05b7:
            com.google.android.exoplayer2.trackselection.TrackSelectorResult r1 = r1.j     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodQueue r2 = r11.s     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodHolder r3 = r2.e     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r3 == 0) goto L_0x05c7
            com.google.android.exoplayer2.MediaPeriodHolder r3 = r2.e     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodHolder r3 = r3.h     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r3 == 0) goto L_0x05c7
            r3 = 1
            goto L_0x05c8
        L_0x05c7:
            r3 = 0
        L_0x05c8:
            com.google.android.exoplayer2.util.Assertions.b((boolean) r3)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodHolder r3 = r2.e     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodHolder r3 = r3.h     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r2.e = r3     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r2.c()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodHolder r2 = r2.e     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.trackselection.TrackSelectorResult r3 = r2.j     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            boolean r4 = r2.d     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r4 == 0) goto L_0x05fe
            com.google.android.exoplayer2.source.MediaPeriod r4 = r2.a     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r4 = r4.c()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            int r6 = (r4 > r16 ? 1 : (r4 == r16 ? 0 : -1))
            if (r6 == 0) goto L_0x05fe
            long r1 = r2.a()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.Renderer[] r3 = r11.d     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            int r4 = r3.length     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r5 = 0
        L_0x05ee:
            if (r5 >= r4) goto L_0x068c
            r6 = r3[r5]     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.source.SampleStream r7 = r6.f()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r7 == 0) goto L_0x05fb
            a((com.google.android.exoplayer2.Renderer) r6, (long) r1)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
        L_0x05fb:
            int r5 = r5 + 1
            goto L_0x05ee
        L_0x05fe:
            r4 = 0
        L_0x05ff:
            com.google.android.exoplayer2.Renderer[] r5 = r11.d     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            int r5 = r5.length     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r4 >= r5) goto L_0x068c
            boolean r5 = r1.a(r4)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            boolean r6 = r3.a(r4)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r5 == 0) goto L_0x0643
            com.google.android.exoplayer2.Renderer[] r5 = r11.d     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r5 = r5[r4]     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            boolean r5 = r5.j()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r5 != 0) goto L_0x0643
            com.google.android.exoplayer2.RendererCapabilities[] r5 = r11.e     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r5 = r5[r4]     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            int r5 = r5.a()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r7 = 7
            if (r5 != r7) goto L_0x0625
            r5 = 1
            goto L_0x0626
        L_0x0625:
            r5 = 0
        L_0x0626:
            com.google.android.exoplayer2.RendererConfiguration[] r7 = r1.b     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r7 = r7[r4]     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.RendererConfiguration[] r14 = r3.b     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r14 = r14[r4]     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r6 == 0) goto L_0x0638
            boolean r6 = r14.equals(r7)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r6 == 0) goto L_0x0638
            if (r5 == 0) goto L_0x0643
        L_0x0638:
            com.google.android.exoplayer2.Renderer[] r5 = r11.d     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r5 = r5[r4]     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r6 = r2.a()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            a((com.google.android.exoplayer2.Renderer) r5, (long) r6)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
        L_0x0643:
            int r4 = r4 + 1
            goto L_0x05ff
        L_0x0646:
            com.google.android.exoplayer2.MediaPeriodInfo r2 = r1.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            boolean r2 = r2.h     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r2 != 0) goto L_0x0650
            boolean r2 = r11.A     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r2 == 0) goto L_0x068c
        L_0x0650:
            r2 = 0
        L_0x0651:
            com.google.android.exoplayer2.Renderer[] r3 = r11.d     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            int r4 = r3.length     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r2 >= r4) goto L_0x068c
            r3 = r3[r2]     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.source.SampleStream[] r4 = r1.c     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r4 = r4[r2]     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r4 == 0) goto L_0x0689
            com.google.android.exoplayer2.source.SampleStream r5 = r3.f()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r5 != r4) goto L_0x0689
            boolean r4 = r3.g()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r4 == 0) goto L_0x0689
            com.google.android.exoplayer2.MediaPeriodInfo r4 = r1.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r4 = r4.e     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            int r6 = (r4 > r16 ? 1 : (r4 == r16 ? 0 : -1))
            if (r6 == 0) goto L_0x0684
            com.google.android.exoplayer2.MediaPeriodInfo r4 = r1.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r4 = r4.e     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r6 = -9223372036854775808
            int r14 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r14 == 0) goto L_0x0684
            long r4 = r1.k     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodInfo r6 = r1.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r6 = r6.e     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r4 = r4 + r6
            goto L_0x0686
        L_0x0684:
            r4 = r16
        L_0x0686:
            a((com.google.android.exoplayer2.Renderer) r3, (long) r4)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
        L_0x0689:
            int r2 = r2 + 1
            goto L_0x0651
        L_0x068c:
            com.google.android.exoplayer2.MediaPeriodQueue r1 = r11.s     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x07a9 }
            com.google.android.exoplayer2.MediaPeriodHolder r1 = r1.e     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x07a9 }
            if (r1 == 0) goto L_0x06fd
            com.google.android.exoplayer2.MediaPeriodQueue r2 = r11.s     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodHolder r2 = r2.d     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r2 == r1) goto L_0x06fd
            boolean r1 = r1.g     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r1 == 0) goto L_0x069d
            goto L_0x06fd
        L_0x069d:
            com.google.android.exoplayer2.MediaPeriodQueue r1 = r11.s     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodHolder r1 = r1.e     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.trackselection.TrackSelectorResult r2 = r1.j     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r3 = 0
            r4 = 0
        L_0x06a5:
            com.google.android.exoplayer2.Renderer[] r5 = r11.d     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            int r6 = r5.length     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r3 >= r6) goto L_0x06f5
            r5 = r5[r3]     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            boolean r6 = c((com.google.android.exoplayer2.Renderer) r5)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r6 == 0) goto L_0x06f2
            com.google.android.exoplayer2.source.SampleStream r6 = r5.f()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.source.SampleStream[] r7 = r1.c     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r7 = r7[r3]     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r6 == r7) goto L_0x06be
            r6 = 1
            goto L_0x06bf
        L_0x06be:
            r6 = 0
        L_0x06bf:
            boolean r7 = r2.a(r3)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r7 == 0) goto L_0x06c7
            if (r6 == 0) goto L_0x06f2
        L_0x06c7:
            boolean r6 = r5.j()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r6 != 0) goto L_0x06e7
            com.google.android.exoplayer2.trackselection.ExoTrackSelection[] r6 = r2.c     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r6 = r6[r3]     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.Format[] r29 = a((com.google.android.exoplayer2.trackselection.ExoTrackSelection) r6)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.source.SampleStream[] r6 = r1.c     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r30 = r6[r3]     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r31 = r1.a()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r6 = r1.k     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            r28 = r5
            r33 = r6
            r28.a(r29, r30, r31, r33)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            goto L_0x06f2
        L_0x06e7:
            boolean r6 = r5.z()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r6 == 0) goto L_0x06f1
            r11.b((com.google.android.exoplayer2.Renderer) r5)     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            goto L_0x06f2
        L_0x06f1:
            r4 = 1
        L_0x06f2:
            int r3 = r3 + 1
            goto L_0x06a5
        L_0x06f5:
            r1 = 1
            r2 = r4 ^ 1
            if (r2 == 0) goto L_0x06fd
            r53.t()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
        L_0x06fd:
            r1 = 0
        L_0x06fe:
            boolean r2 = r53.v()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x07a9 }
            if (r2 == 0) goto L_0x0722
            boolean r2 = r11.A     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r2 != 0) goto L_0x0722
            com.google.android.exoplayer2.MediaPeriodQueue r2 = r11.s     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            com.google.android.exoplayer2.MediaPeriodHolder r2 = r2.d     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r2 == 0) goto L_0x0722
            com.google.android.exoplayer2.MediaPeriodHolder r2 = r2.h     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r2 == 0) goto L_0x0722
            long r3 = r11.K     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            long r5 = r2.a()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 < 0) goto L_0x0722
            boolean r2 = r2.g     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
            if (r2 == 0) goto L_0x0722
            r2 = 1
            goto L_0x0723
        L_0x0722:
            r2 = 0
        L_0x0723:
            if (r2 == 0) goto L_0x07a1
            if (r1 == 0) goto L_0x072a
            r53.e()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x0430 }
        L_0x072a:
            com.google.android.exoplayer2.MediaPeriodQueue r1 = r11.s     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x07a9 }
            com.google.android.exoplayer2.MediaPeriodHolder r14 = r1.d     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x07a9 }
            com.google.android.exoplayer2.MediaPeriodQueue r1 = r11.s     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x07a9 }
            com.google.android.exoplayer2.MediaPeriodHolder r15 = r1.a()     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x07a9 }
            com.google.android.exoplayer2.MediaPeriodInfo r1 = r15.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x07a9 }
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r2 = r1.a     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x07a9 }
            com.google.android.exoplayer2.MediaPeriodInfo r1 = r15.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x07a9 }
            long r3 = r1.b     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x07a9 }
            com.google.android.exoplayer2.MediaPeriodInfo r1 = r15.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x07a9 }
            long r5 = r1.c     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x07a9 }
            com.google.android.exoplayer2.MediaPeriodInfo r1 = r15.f     // Catch:{ ExoPlaybackException -> 0x043e, IOException -> 0x0439, RuntimeException -> 0x07a9 }
            long r10 = r1.b     // Catch:{ ExoPlaybackException -> 0x0554, IOException -> 0x054f, RuntimeException -> 0x0797 }
            r19 = 1
            r20 = 0
            r1 = r53
            r7 = 4
            r21 = 3
            r22 = r12
            r23 = r13
            r12 = r8
            r9 = 4
            r7 = r10
            r10 = 4
            r11 = 3
            r9 = r19
            r11 = 4
            r10 = r20
            com.google.android.exoplayer2.PlaybackInfo r1 = r1.a(r2, r3, r5, r7, r9, r10)     // Catch:{ ExoPlaybackException -> 0x0792, IOException -> 0x078d, RuntimeException -> 0x078b }
            r8 = r53
            r8.x = r1     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            com.google.android.exoplayer2.Timeline r2 = r1.a     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            com.google.android.exoplayer2.MediaPeriodInfo r1 = r15.f     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r3 = r1.a     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            com.google.android.exoplayer2.PlaybackInfo r1 = r8.x     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            com.google.android.exoplayer2.Timeline r4 = r1.a     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            com.google.android.exoplayer2.MediaPeriodInfo r1 = r14.f     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r5 = r1.a     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            r6 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r1 = r53
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            r53.o()     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            r53.i()     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            r11 = r8
            r8 = r12
            r12 = r22
            r13 = r23
            r1 = 1
            r10 = 0
            goto L_0x06fe
        L_0x078b:
            r0 = move-exception
            goto L_0x079c
        L_0x078d:
            r0 = move-exception
            r2 = r53
            goto L_0x0b25
        L_0x0792:
            r0 = move-exception
            r2 = r53
            goto L_0x0b2c
        L_0x0797:
            r0 = move-exception
            r22 = r12
            r23 = r13
        L_0x079c:
            r1 = 0
            r2 = r53
            goto L_0x0b39
        L_0x07a1:
            r22 = r12
            r23 = r13
            r12 = r8
            r8 = r11
            r11 = 4
            goto L_0x07b2
        L_0x07a9:
            r0 = move-exception
            r22 = r12
            r23 = r13
            r3 = r0
            r2 = r11
            goto L_0x0b22
        L_0x07b2:
            com.google.android.exoplayer2.PlaybackInfo r1 = r8.x     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            int r1 = r1.e     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            r2 = 1
            if (r1 == r2) goto L_0x0a7f
            com.google.android.exoplayer2.PlaybackInfo r1 = r8.x     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            int r1 = r1.e     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            if (r1 != r11) goto L_0x07c1
            goto L_0x0a7f
        L_0x07c1:
            com.google.android.exoplayer2.MediaPeriodQueue r1 = r8.s     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            com.google.android.exoplayer2.MediaPeriodHolder r1 = r1.d     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            r2 = 10
            if (r1 != 0) goto L_0x07de
            r8.a((long) r12, (long) r2)     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            r2 = r8
            goto L_0x0b1a
        L_0x07cf:
            r0 = move-exception
            r3 = r0
            r2 = r8
            goto L_0x0b22
        L_0x07d4:
            r0 = move-exception
            r1 = r0
            r2 = r8
            goto L_0x0b26
        L_0x07d9:
            r0 = move-exception
            r1 = r0
            r2 = r8
            goto L_0x0b2d
        L_0x07de:
            java.lang.String r4 = "doSomeWork"
            com.google.android.exoplayer2.util.TraceUtil.a(r4)     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            r53.i()     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            boolean r4 = r1.d     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            r5 = 1000(0x3e8, double:4.94E-321)
            if (r4 == 0) goto L_0x085a
            long r9 = android.os.SystemClock.elapsedRealtime()     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            long r9 = r9 * r5
            com.google.android.exoplayer2.source.MediaPeriod r4 = r1.a     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            com.google.android.exoplayer2.PlaybackInfo r7 = r8.x     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            long r14 = r7.s     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            long r5 = r8.m     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            long r14 = r14 - r5
            r5 = 0
            r4.a((long) r14, (boolean) r5)     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            r4 = 0
            r5 = 1
            r6 = 1
        L_0x0802:
            com.google.android.exoplayer2.Renderer[] r7 = r8.d     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            int r14 = r7.length     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r4 >= r14) goto L_0x0861
            r7 = r7[r4]     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            boolean r14 = c((com.google.android.exoplayer2.Renderer) r7)     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r14 == 0) goto L_0x0857
            long r14 = r8.K     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            r7.a((long) r14, (long) r9)     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r5 == 0) goto L_0x081e
            boolean r5 = r7.z()     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r5 == 0) goto L_0x081e
            r5 = 1
            goto L_0x081f
        L_0x081e:
            r5 = 0
        L_0x081f:
            com.google.android.exoplayer2.source.SampleStream[] r14 = r1.c     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            r14 = r14[r4]     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            com.google.android.exoplayer2.source.SampleStream r15 = r7.f()     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r14 == r15) goto L_0x082b
            r14 = 1
            goto L_0x082c
        L_0x082b:
            r14 = 0
        L_0x082c:
            if (r14 != 0) goto L_0x0836
            boolean r15 = r7.g()     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r15 == 0) goto L_0x0836
            r15 = 1
            goto L_0x0837
        L_0x0836:
            r15 = 0
        L_0x0837:
            if (r14 != 0) goto L_0x084a
            if (r15 != 0) goto L_0x084a
            boolean r14 = r7.y()     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r14 != 0) goto L_0x084a
            boolean r14 = r7.z()     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r14 == 0) goto L_0x0848
            goto L_0x084a
        L_0x0848:
            r14 = 0
            goto L_0x084b
        L_0x084a:
            r14 = 1
        L_0x084b:
            if (r6 == 0) goto L_0x0851
            if (r14 == 0) goto L_0x0851
            r6 = 1
            goto L_0x0852
        L_0x0851:
            r6 = 0
        L_0x0852:
            if (r14 != 0) goto L_0x0857
            r7.k()     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
        L_0x0857:
            int r4 = r4 + 1
            goto L_0x0802
        L_0x085a:
            com.google.android.exoplayer2.source.MediaPeriod r4 = r1.a     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            r4.a()     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            r5 = 1
            r6 = 1
        L_0x0861:
            com.google.android.exoplayer2.MediaPeriodInfo r4 = r1.f     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            long r9 = r4.e     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            if (r5 == 0) goto L_0x0879
            boolean r4 = r1.d     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r4 == 0) goto L_0x0879
            int r4 = (r9 > r16 ? 1 : (r9 == r16 ? 0 : -1))
            if (r4 == 0) goto L_0x0877
            com.google.android.exoplayer2.PlaybackInfo r4 = r8.x     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            long r4 = r4.s     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            int r7 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r7 > 0) goto L_0x0879
        L_0x0877:
            r4 = 1
            goto L_0x087a
        L_0x0879:
            r4 = 0
        L_0x087a:
            if (r4 == 0) goto L_0x088b
            boolean r5 = r8.A     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r5 == 0) goto L_0x088b
            r5 = 0
            r8.A = r5     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            com.google.android.exoplayer2.PlaybackInfo r7 = r8.x     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            int r7 = r7.m     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            r9 = 5
            r8.a((boolean) r5, (int) r7, (boolean) r5, (int) r9)     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
        L_0x088b:
            if (r4 == 0) goto L_0x0899
            com.google.android.exoplayer2.MediaPeriodInfo r4 = r1.f     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            boolean r4 = r4.h     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r4 == 0) goto L_0x0899
            r8.a((int) r11)     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            r5 = 2
            goto L_0x0968
        L_0x0899:
            com.google.android.exoplayer2.PlaybackInfo r4 = r8.x     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            int r4 = r4.e     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            r5 = 2
            if (r4 != r5) goto L_0x0927
            int r4 = r8.I     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r4 != 0) goto L_0x08aa
            boolean r4 = r53.n()     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            goto L_0x0914
        L_0x08aa:
            if (r6 == 0) goto L_0x0913
            com.google.android.exoplayer2.PlaybackInfo r4 = r8.x     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            boolean r4 = r4.g     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r4 != 0) goto L_0x08b4
        L_0x08b2:
            r4 = 1
            goto L_0x0914
        L_0x08b4:
            com.google.android.exoplayer2.PlaybackInfo r4 = r8.x     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            com.google.android.exoplayer2.Timeline r4 = r4.a     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            com.google.android.exoplayer2.MediaPeriodQueue r7 = r8.s     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            com.google.android.exoplayer2.MediaPeriodHolder r7 = r7.d     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            com.google.android.exoplayer2.MediaPeriodInfo r7 = r7.f     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r7 = r7.a     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            boolean r4 = r8.a((com.google.android.exoplayer2.Timeline) r4, (com.google.android.exoplayer2.source.MediaSource.MediaPeriodId) r7)     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r4 == 0) goto L_0x08cc
            com.google.android.exoplayer2.LivePlaybackSpeedControl r4 = r8.u     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            long r16 = r4.b()     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
        L_0x08cc:
            r33 = r16
            com.google.android.exoplayer2.MediaPeriodQueue r4 = r8.s     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            com.google.android.exoplayer2.MediaPeriodHolder r4 = r4.f     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            boolean r7 = r4.b()     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r7 == 0) goto L_0x08e0
            com.google.android.exoplayer2.MediaPeriodInfo r7 = r4.f     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            boolean r7 = r7.h     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r7 == 0) goto L_0x08e0
            r7 = 1
            goto L_0x08e1
        L_0x08e0:
            r7 = 0
        L_0x08e1:
            com.google.android.exoplayer2.MediaPeriodInfo r9 = r4.f     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r9 = r9.a     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            boolean r9 = r9.a()     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r9 == 0) goto L_0x08f1
            boolean r4 = r4.d     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r4 != 0) goto L_0x08f1
            r4 = 1
            goto L_0x08f2
        L_0x08f1:
            r4 = 0
        L_0x08f2:
            if (r7 != 0) goto L_0x08b2
            if (r4 != 0) goto L_0x08b2
            com.google.android.exoplayer2.LoadControl r4 = r8.h     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            long r29 = r53.u()     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            com.google.android.exoplayer2.DefaultMediaClock r7 = r8.o     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            com.google.android.exoplayer2.PlaybackParameters r7 = r7.d()     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            float r7 = r7.b     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            boolean r9 = r8.B     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            r28 = r4
            r31 = r7
            r32 = r9
            boolean r4 = r28.a(r29, r31, r32, r33)     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r4 == 0) goto L_0x0913
            goto L_0x08b2
        L_0x0913:
            r4 = 0
        L_0x0914:
            if (r4 == 0) goto L_0x0927
            r4 = 3
            r8.a((int) r4)     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            r4 = 0
            r8.N = r4     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            boolean r4 = r53.v()     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r4 == 0) goto L_0x096b
            r53.g()     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            goto L_0x096b
        L_0x0927:
            com.google.android.exoplayer2.PlaybackInfo r4 = r8.x     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            int r4 = r4.e     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            r7 = 3
            if (r4 != r7) goto L_0x096b
            int r4 = r8.I     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r4 != 0) goto L_0x0939
            boolean r4 = r53.n()     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r4 == 0) goto L_0x093b
            goto L_0x096b
        L_0x0939:
            if (r6 != 0) goto L_0x096b
        L_0x093b:
            boolean r4 = r53.v()     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            r8.B = r4     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            r8.a((int) r5)     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            boolean r4 = r8.B     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r4 == 0) goto L_0x0968
            com.google.android.exoplayer2.MediaPeriodQueue r4 = r8.s     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            com.google.android.exoplayer2.MediaPeriodHolder r4 = r4.d     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
        L_0x094c:
            if (r4 == 0) goto L_0x0963
            com.google.android.exoplayer2.trackselection.TrackSelectorResult r6 = r4.j     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            com.google.android.exoplayer2.trackselection.ExoTrackSelection[] r6 = r6.c     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            int r7 = r6.length     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            r9 = 0
        L_0x0954:
            if (r9 >= r7) goto L_0x0960
            r10 = r6[r9]     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r10 == 0) goto L_0x095d
            r10.j()     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
        L_0x095d:
            int r9 = r9 + 1
            goto L_0x0954
        L_0x0960:
            com.google.android.exoplayer2.MediaPeriodHolder r4 = r4.h     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            goto L_0x094c
        L_0x0963:
            com.google.android.exoplayer2.LivePlaybackSpeedControl r4 = r8.u     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            r4.a()     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
        L_0x0968:
            r53.h()     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
        L_0x096b:
            com.google.android.exoplayer2.PlaybackInfo r4 = r8.x     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            int r4 = r4.e     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            if (r4 != r5) goto L_0x09b7
            r4 = 0
        L_0x0972:
            com.google.android.exoplayer2.Renderer[] r6 = r8.d     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            int r7 = r6.length     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r4 >= r7) goto L_0x0997
            r6 = r6[r4]     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            boolean r6 = c((com.google.android.exoplayer2.Renderer) r6)     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r6 == 0) goto L_0x0994
            com.google.android.exoplayer2.Renderer[] r6 = r8.d     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            r6 = r6[r4]     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            com.google.android.exoplayer2.source.SampleStream r6 = r6.f()     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            com.google.android.exoplayer2.source.SampleStream[] r7 = r1.c     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            r7 = r7[r4]     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r6 != r7) goto L_0x0994
            com.google.android.exoplayer2.Renderer[] r6 = r8.d     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            r6 = r6[r4]     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            r6.k()     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
        L_0x0994:
            int r4 = r4 + 1
            goto L_0x0972
        L_0x0997:
            com.google.android.exoplayer2.PlaybackInfo r1 = r8.x     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            boolean r1 = r1.g     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r1 != 0) goto L_0x09b7
            com.google.android.exoplayer2.PlaybackInfo r1 = r8.x     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            long r6 = r1.r     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            r9 = 500000(0x7a120, double:2.47033E-318)
            int r1 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r1 >= 0) goto L_0x09b7
            boolean r1 = r53.r()     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r1 != 0) goto L_0x09af
            goto L_0x09b7
        L_0x09af:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            java.lang.String r2 = "Playback stuck buffering and not loading"
            r1.<init>(r2)     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            throw r1     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
        L_0x09b7:
            boolean r1 = r8.H     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            com.google.android.exoplayer2.PlaybackInfo r4 = r8.x     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            boolean r4 = r4.o     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            if (r1 == r4) goto L_0x09c9
            com.google.android.exoplayer2.PlaybackInfo r1 = r8.x     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            boolean r4 = r8.H     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            com.google.android.exoplayer2.PlaybackInfo r1 = r1.b(r4)     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            r8.x = r1     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
        L_0x09c9:
            boolean r1 = r53.v()     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            if (r1 == 0) goto L_0x09d6
            com.google.android.exoplayer2.PlaybackInfo r1 = r8.x     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            int r1 = r1.e     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            r4 = 3
            if (r1 == r4) goto L_0x09dc
        L_0x09d6:
            com.google.android.exoplayer2.PlaybackInfo r1 = r8.x     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            int r1 = r1.e     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            if (r1 != r5) goto L_0x09ed
        L_0x09dc:
            boolean r1 = r8.H     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r1 == 0) goto L_0x09e6
            boolean r1 = r8.G     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r1 == 0) goto L_0x09e6
            r1 = 0
            goto L_0x09ea
        L_0x09e6:
            r8.a((long) r12, (long) r2)     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            r1 = 1
        L_0x09ea:
            r2 = 1
            r1 = r1 ^ r2
            goto L_0x0a03
        L_0x09ed:
            int r1 = r8.I     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            if (r1 == 0) goto L_0x09fd
            com.google.android.exoplayer2.PlaybackInfo r1 = r8.x     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            int r1 = r1.e     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            if (r1 == r11) goto L_0x09fd
            r1 = 1000(0x3e8, double:4.94E-321)
            r8.a((long) r12, (long) r1)     // Catch:{ ExoPlaybackException -> 0x07d9, IOException -> 0x07d4, RuntimeException -> 0x07cf }
            goto L_0x0a02
        L_0x09fd:
            com.google.android.exoplayer2.util.HandlerWrapper r1 = r8.a     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            r1.b()     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
        L_0x0a02:
            r1 = 0
        L_0x0a03:
            com.google.android.exoplayer2.PlaybackInfo r2 = r8.x     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            boolean r2 = r2.p     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            if (r2 == r1) goto L_0x0a76
            com.google.android.exoplayer2.PlaybackInfo r2 = r8.x     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            com.google.android.exoplayer2.PlaybackInfo r3 = new com.google.android.exoplayer2.PlaybackInfo     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            com.google.android.exoplayer2.Timeline r4 = r2.a     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r5 = r2.b     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            long r6 = r2.c     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            long r9 = r2.d     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            int r11 = r2.e     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            com.google.android.exoplayer2.ExoPlaybackException r12 = r2.f     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            boolean r13 = r2.g     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            com.google.android.exoplayer2.source.TrackGroupArray r14 = r2.h     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            com.google.android.exoplayer2.trackselection.TrackSelectorResult r15 = r2.i     // Catch:{ ExoPlaybackException -> 0x0a8f, IOException -> 0x0a8b, RuntimeException -> 0x0a87 }
            java.util.List r8 = r2.j     // Catch:{ ExoPlaybackException -> 0x0792, IOException -> 0x078d, RuntimeException -> 0x0a71 }
            r16 = r1
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r1 = r2.k     // Catch:{ ExoPlaybackException -> 0x0792, IOException -> 0x078d, RuntimeException -> 0x0a71 }
            r17 = r1
            boolean r1 = r2.l     // Catch:{ ExoPlaybackException -> 0x0792, IOException -> 0x078d, RuntimeException -> 0x0a71 }
            r18 = r1
            int r1 = r2.m     // Catch:{ ExoPlaybackException -> 0x0792, IOException -> 0x078d, RuntimeException -> 0x0a71 }
            r19 = r1
            com.google.android.exoplayer2.PlaybackParameters r1 = r2.n     // Catch:{ ExoPlaybackException -> 0x0792, IOException -> 0x078d, RuntimeException -> 0x0a71 }
            r20 = r14
            r21 = r15
            long r14 = r2.q     // Catch:{ ExoPlaybackException -> 0x0792, IOException -> 0x078d, RuntimeException -> 0x0a71 }
            r24 = r14
            long r14 = r2.r     // Catch:{ ExoPlaybackException -> 0x0792, IOException -> 0x078d, RuntimeException -> 0x0a71 }
            r26 = r14
            long r14 = r2.s     // Catch:{ ExoPlaybackException -> 0x0792, IOException -> 0x078d, RuntimeException -> 0x0a71 }
            boolean r2 = r2.o     // Catch:{ ExoPlaybackException -> 0x0792, IOException -> 0x078d, RuntimeException -> 0x0a71 }
            r28 = r3
            r29 = r4
            r30 = r5
            r31 = r6
            r33 = r9
            r35 = r11
            r36 = r12
            r37 = r13
            r38 = r20
            r39 = r21
            r40 = r8
            r41 = r17
            r42 = r18
            r43 = r19
            r44 = r1
            r45 = r24
            r47 = r26
            r49 = r14
            r51 = r2
            r52 = r16
            r28.<init>(r29, r30, r31, r33, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r47, r49, r51, r52)     // Catch:{ ExoPlaybackException -> 0x0792, IOException -> 0x078d, RuntimeException -> 0x0a71 }
            r2 = r53
            r2.x = r3     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            goto L_0x0a77
        L_0x0a71:
            r0 = move-exception
            r2 = r53
            goto L_0x0b21
        L_0x0a76:
            r2 = r8
        L_0x0a77:
            r1 = 0
            r2.G = r1     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b1e }
            com.google.android.exoplayer2.util.TraceUtil.a()     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            goto L_0x0b1a
        L_0x0a7f:
            r2 = r8
            com.google.android.exoplayer2.util.HandlerWrapper r1 = r2.a     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            r1.b()     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            goto L_0x0b1a
        L_0x0a87:
            r0 = move-exception
            r2 = r8
            goto L_0x0b21
        L_0x0a8b:
            r0 = move-exception
            r2 = r8
            goto L_0x0b25
        L_0x0a8f:
            r0 = move-exception
            r2 = r8
            goto L_0x0b2c
        L_0x0a93:
            r0 = move-exception
            r2 = r11
            r22 = r12
            r23 = r13
            goto L_0x0b21
        L_0x0a9b:
            r0 = move-exception
            r2 = r11
        L_0x0a9d:
            r1 = r0
        L_0x0a9e:
            r5 = r12
            r4 = r13
            goto L_0x0b5c
        L_0x0aa2:
            r0 = move-exception
            r2 = r11
        L_0x0aa4:
            r1 = r0
        L_0x0aa5:
            r5 = r12
            r4 = r13
            goto L_0x0b81
        L_0x0aa9:
            r2 = r11
            r22 = r12
            r23 = r13
            int r3 = r1.arg1     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            if (r3 == 0) goto L_0x0ab4
            r3 = 1
            goto L_0x0ab5
        L_0x0ab4:
            r3 = 0
        L_0x0ab5:
            int r1 = r1.arg2     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            r4 = 1
            r2.a((boolean) r3, (int) r1, (boolean) r4, (int) r4)     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            goto L_0x0b1a
        L_0x0abc:
            r2 = r11
            r22 = r12
            r23 = r13
            r5 = 2
            r11 = 4
            com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfoUpdate r1 = r2.y     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            r3 = 1
            r1.a((int) r3)     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            r1 = 0
            r2.a((boolean) r1, (boolean) r1, (boolean) r1, (boolean) r3)     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b1e }
            com.google.android.exoplayer2.LoadControl r1 = r2.h     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            r1.a()     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            com.google.android.exoplayer2.PlaybackInfo r1 = r2.x     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            com.google.android.exoplayer2.Timeline r1 = r1.a     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            boolean r1 = r1.c()     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            if (r1 == 0) goto L_0x0ade
            r7 = 4
            goto L_0x0adf
        L_0x0ade:
            r7 = 2
        L_0x0adf:
            r2.a((int) r7)     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            com.google.android.exoplayer2.MediaSourceList r1 = r2.t     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            com.google.android.exoplayer2.upstream.BandwidthMeter r3 = r2.i     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            com.google.android.exoplayer2.upstream.TransferListener r3 = r3.c()     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            boolean r4 = r1.h     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            if (r4 != 0) goto L_0x0af0
            r4 = 1
            goto L_0x0af1
        L_0x0af0:
            r4 = 0
        L_0x0af1:
            com.google.android.exoplayer2.util.Assertions.b((boolean) r4)     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            r1.i = r3     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            r3 = 0
        L_0x0af7:
            java.util.List r4 = r1.a     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            int r4 = r4.size()     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            if (r3 >= r4) goto L_0x0b12
            java.util.List r4 = r1.a     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            java.lang.Object r4 = r4.get(r3)     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            com.google.android.exoplayer2.MediaSourceList$MediaSourceHolder r4 = (com.google.android.exoplayer2.MediaSourceList.MediaSourceHolder) r4     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            r1.a((com.google.android.exoplayer2.MediaSourceList.MediaSourceHolder) r4)     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            java.util.Set r6 = r1.f     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            r6.add(r4)     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            int r3 = r3 + 1
            goto L_0x0af7
        L_0x0b12:
            r3 = 1
            r1.h = r3     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            com.google.android.exoplayer2.util.HandlerWrapper r1 = r2.a     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            r1.b(r5)     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
        L_0x0b1a:
            r53.e()     // Catch:{ ExoPlaybackException -> 0x0b2b, IOException -> 0x0b24, RuntimeException -> 0x0b20 }
            goto L_0x0b54
        L_0x0b1e:
            r0 = move-exception
            goto L_0x0b39
        L_0x0b20:
            r0 = move-exception
        L_0x0b21:
            r3 = r0
        L_0x0b22:
            r1 = 0
            goto L_0x0b3a
        L_0x0b24:
            r0 = move-exception
        L_0x0b25:
            r1 = r0
        L_0x0b26:
            r5 = r22
            r4 = r23
            goto L_0x0b5c
        L_0x0b2b:
            r0 = move-exception
        L_0x0b2c:
            r1 = r0
        L_0x0b2d:
            r5 = r22
            r4 = r23
            goto L_0x0b81
        L_0x0b32:
            r0 = move-exception
            r2 = r11
            r22 = r12
            r23 = r13
            r1 = 0
        L_0x0b39:
            r3 = r0
        L_0x0b3a:
            com.google.android.exoplayer2.ExoPlaybackException r3 = com.google.android.exoplayer2.ExoPlaybackException.a((java.lang.RuntimeException) r3)
            r5 = r22
            r4 = r23
            com.google.android.exoplayer2.util.Log.b(r4, r5, r3)
            r4 = 1
            r2.a((boolean) r4, (boolean) r1)
            com.google.android.exoplayer2.PlaybackInfo r1 = r2.x
            com.google.android.exoplayer2.PlaybackInfo r1 = r1.a((com.google.android.exoplayer2.ExoPlaybackException) r3)
        L_0x0b4f:
            r2.x = r1
            r53.e()
        L_0x0b54:
            r4 = 1
            goto L_0x0bcc
        L_0x0b57:
            r0 = move-exception
            r2 = r11
            r5 = r12
            r4 = r13
            r1 = r0
        L_0x0b5c:
            com.google.android.exoplayer2.ExoPlaybackException r1 = com.google.android.exoplayer2.ExoPlaybackException.a((java.io.IOException) r1)
            com.google.android.exoplayer2.MediaPeriodQueue r3 = r2.s
            com.google.android.exoplayer2.MediaPeriodHolder r3 = r3.d
            if (r3 == 0) goto L_0x0b6e
            com.google.android.exoplayer2.MediaPeriodInfo r3 = r3.f
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r3 = r3.a
            com.google.android.exoplayer2.ExoPlaybackException r1 = r1.a((com.google.android.exoplayer2.source.MediaPeriodId) r3)
        L_0x0b6e:
            com.google.android.exoplayer2.util.Log.b(r4, r5, r1)
            r3 = 0
            r2.a((boolean) r3, (boolean) r3)
            com.google.android.exoplayer2.PlaybackInfo r3 = r2.x
            com.google.android.exoplayer2.PlaybackInfo r1 = r3.a((com.google.android.exoplayer2.ExoPlaybackException) r1)
            goto L_0x0b4f
        L_0x0b7c:
            r0 = move-exception
            r2 = r11
            r5 = r12
            r4 = r13
            r1 = r0
        L_0x0b81:
            int r3 = r1.a
            r6 = 1
            if (r3 != r6) goto L_0x0b94
            com.google.android.exoplayer2.MediaPeriodQueue r3 = r2.s
            com.google.android.exoplayer2.MediaPeriodHolder r3 = r3.e
            if (r3 == 0) goto L_0x0b94
            com.google.android.exoplayer2.MediaPeriodInfo r3 = r3.f
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r3 = r3.a
            com.google.android.exoplayer2.ExoPlaybackException r1 = r1.a((com.google.android.exoplayer2.source.MediaPeriodId) r3)
        L_0x0b94:
            boolean r3 = r1.c
            if (r3 == 0) goto L_0x0bb0
            com.google.android.exoplayer2.ExoPlaybackException r3 = r2.N
            if (r3 != 0) goto L_0x0bb0
            java.lang.String r3 = "Recoverable renderer error"
            com.google.android.exoplayer2.util.Log.a(r4, r3, r1)
            r2.N = r1
            com.google.android.exoplayer2.util.HandlerWrapper r3 = r2.a
            r4 = 25
            com.google.android.exoplayer2.util.HandlerWrapper$Message r1 = r3.a(r4, r1)
            r3.a((com.google.android.exoplayer2.util.HandlerWrapper.Message) r1)
            r4 = 1
            goto L_0x0bc9
        L_0x0bb0:
            com.google.android.exoplayer2.ExoPlaybackException r3 = r2.N
            if (r3 == 0) goto L_0x0bb9
            com.dreamers.exoplayercore.repack.cT.a(r3, r1)
            com.google.android.exoplayer2.ExoPlaybackException r1 = r2.N
        L_0x0bb9:
            com.google.android.exoplayer2.util.Log.b(r4, r5, r1)
            r3 = 0
            r4 = 1
            r2.a((boolean) r4, (boolean) r3)
            com.google.android.exoplayer2.PlaybackInfo r3 = r2.x
            com.google.android.exoplayer2.PlaybackInfo r1 = r3.a((com.google.android.exoplayer2.ExoPlaybackException) r1)
            r2.x = r1
        L_0x0bc9:
            r53.e()
        L_0x0bcc:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ExoPlayerImplInternal.handleMessage(android.os.Message):boolean");
    }
}
