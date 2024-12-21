package com.google.android.exoplayer2;

import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import android.view.SurfaceView;
import android.view.TextureView;
import com.dreamers.exoplayercore.repack.bG;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerImplInternal;
import com.google.android.exoplayer2.MediaSourceList;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.PlayerMessage;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.analytics.AnalyticsCollector;
import com.google.android.exoplayer2.analytics.AnalyticsCollector$$Lambda$1;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceFactory;
import com.google.android.exoplayer2.source.ShuffleOrder;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.ExoTrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.HandlerWrapper;
import com.google.android.exoplayer2.util.ListenerSet;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

public final class ExoPlayerImpl extends BasePlayer implements ExoPlayer {
    private ShuffleOrder A;
    private int B;
    private long C;
    final HandlerWrapper b;
    final ListenerSet c;
    final CopyOnWriteArraySet d;
    final Looper e;
    int f;
    boolean g;
    Player.Commands h;
    MediaMetadata i;
    public PlaybackInfo j;
    private TrackSelectorResult k;
    private Player.Commands l;
    private final TrackSelector m;
    private final ExoPlayerImplInternal.PlaybackInfoUpdateListener n;
    private final ExoPlayerImplInternal o;
    private final Timeline.Period p;
    private final List q;
    private final boolean r;
    private final MediaSourceFactory s;
    private final AnalyticsCollector t;
    private final BandwidthMeter u;
    private final Clock v;
    private int w;
    private int x;
    private boolean y;
    private int z;

    final class MediaSourceHolderSnapshot implements MediaSourceInfoHolder {
        private final Object a;
        /* access modifiers changed from: private */
        public Timeline b;

        public MediaSourceHolderSnapshot(Object obj, Timeline timeline) {
            this.a = obj;
            this.b = timeline;
        }

        public final Object a() {
            return this.a;
        }

        public final Timeline b() {
            return this.b;
        }
    }

    public ExoPlayerImpl(Renderer[] rendererArr, TrackSelector trackSelector, MediaSourceFactory mediaSourceFactory, LoadControl loadControl, BandwidthMeter bandwidthMeter, AnalyticsCollector analyticsCollector, boolean z2, SeekParameters seekParameters, LivePlaybackSpeedControl livePlaybackSpeedControl, long j2, boolean z3, Clock clock, Looper looper, Player player, Player.Commands commands) {
        Renderer[] rendererArr2 = rendererArr;
        BandwidthMeter bandwidthMeter2 = bandwidthMeter;
        AnalyticsCollector analyticsCollector2 = analyticsCollector;
        Clock clock2 = clock;
        Looper looper2 = looper;
        String hexString = Integer.toHexString(System.identityHashCode(this));
        String str = Util.e;
        Log.b("ExoPlayerImpl", new StringBuilder(String.valueOf(hexString).length() + 30 + String.valueOf(str).length()).append("Init ").append(hexString).append(" [ExoPlayerLib/2.14.1] [").append(str).append("]").toString());
        boolean z4 = true;
        Assertions.b(rendererArr2.length > 0);
        Assertions.b((Object) rendererArr);
        this.m = (TrackSelector) Assertions.b((Object) trackSelector);
        this.s = mediaSourceFactory;
        this.u = bandwidthMeter2;
        this.t = analyticsCollector2;
        this.r = z2;
        this.e = looper2;
        this.v = clock2;
        this.f = 0;
        this.c = new ListenerSet(looper2, clock2, new ExoPlayerImpl$$Lambda$0(player));
        this.d = new CopyOnWriteArraySet();
        this.q = new ArrayList();
        this.A = new ShuffleOrder.DefaultShuffleOrder();
        this.k = new TrackSelectorResult(new RendererConfiguration[rendererArr2.length], new ExoTrackSelection[rendererArr2.length], (Object) null);
        this.p = new Timeline.Period();
        this.l = new Player.Commands.Builder().a(1, 2, 8, 9, 10, 11, 12, 13, 14).a(commands).a();
        this.h = new Player.Commands.Builder().a(this.l).a(3).a(7).a();
        this.i = MediaMetadata.a;
        this.B = -1;
        this.b = clock2.a(looper2, (Handler.Callback) null);
        ExoPlayerImpl$$Lambda$1 exoPlayerImpl$$Lambda$1 = new ExoPlayerImpl$$Lambda$1(this);
        this.n = exoPlayerImpl$$Lambda$1;
        this.j = PlaybackInfo.a(this.k);
        if (analyticsCollector2 != null) {
            if (analyticsCollector2.d != null && !analyticsCollector2.a.b.isEmpty()) {
                z4 = false;
            }
            Assertions.b(z4);
            analyticsCollector2.d = (Player) Assertions.b((Object) player);
            ListenerSet listenerSet = analyticsCollector2.c;
            analyticsCollector2.c = new ListenerSet(listenerSet.d, looper2, listenerSet.a, new AnalyticsCollector$$Lambda$1(analyticsCollector2));
            a((Player.EventListener) analyticsCollector2);
            bandwidthMeter2.a(new Handler(looper2), analyticsCollector2);
        }
        ExoPlayerImplInternal exoPlayerImplInternal = r1;
        ExoPlayerImplInternal exoPlayerImplInternal2 = new ExoPlayerImplInternal(rendererArr, trackSelector, this.k, loadControl, bandwidthMeter, this.f, this.g, analyticsCollector, seekParameters, livePlaybackSpeedControl, j2, z3, looper, clock, exoPlayerImpl$$Lambda$1);
        this.o = exoPlayerImplInternal;
    }

    private int G() {
        return this.j.a.c() ? this.B : this.j.a.a(this.j.b.a, this.p).c;
    }

    private void H() {
        Player.Commands commands = this.h;
        Player.Commands a = a(this.l);
        this.h = a;
        if (!a.equals(commands)) {
            this.c.a(14, new ExoPlayerImpl$$Lambda$21(this));
        }
    }

    private Timeline I() {
        return new PlaylistTimeline(this.q, this.A);
    }

    private long a(PlaybackInfo playbackInfo) {
        return playbackInfo.a.c() ? C.b(this.C) : playbackInfo.b.a() ? playbackInfo.s : a(playbackInfo.a, playbackInfo.b, playbackInfo.s);
    }

    private long a(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, long j2) {
        timeline.a(mediaPeriodId.a, this.p);
        return j2 + this.p.e;
    }

    private Pair a(PlaybackInfo playbackInfo, PlaybackInfo playbackInfo2, boolean z2, int i2, boolean z3) {
        Timeline timeline = playbackInfo2.a;
        Timeline timeline2 = playbackInfo.a;
        if (timeline2.c() && timeline.c()) {
            return new Pair(Boolean.FALSE, -1);
        }
        int i3 = 3;
        if (timeline2.c() != timeline.c()) {
            return new Pair(Boolean.TRUE, 3);
        }
        if (timeline.a(timeline.a(playbackInfo2.b.a, this.p).c, this.a, 0).b.equals(timeline2.a(timeline2.a(playbackInfo.b.a, this.p).c, this.a, 0).b)) {
            return (!z2 || i2 != 0 || playbackInfo2.b.d >= playbackInfo.b.d) ? new Pair(Boolean.FALSE, -1) : new Pair(Boolean.TRUE, 0);
        }
        if (z2 && i2 == 0) {
            i3 = 1;
        } else if (z2 && i2 == 1) {
            i3 = 2;
        } else if (!z3) {
            throw new IllegalStateException();
        }
        return new Pair(Boolean.TRUE, Integer.valueOf(i3));
    }

    private Pair a(Timeline timeline, int i2, long j2) {
        if (timeline.c()) {
            this.B = i2;
            if (j2 == -9223372036854775807L) {
                j2 = 0;
            }
            this.C = j2;
            return null;
        }
        if (i2 == -1 || i2 >= timeline.a()) {
            i2 = timeline.b(this.g);
            j2 = C.a(timeline.a(i2, this.a, 0).l);
        }
        return timeline.a(this.a, this.p, i2, C.b(j2));
    }

    private Pair a(Timeline timeline, Timeline timeline2) {
        long z2 = z();
        int i2 = -1;
        if (timeline.c() || timeline2.c()) {
            boolean z3 = !timeline.c() && timeline2.c();
            if (!z3) {
                i2 = G();
            }
            if (z3) {
                z2 = -9223372036854775807L;
            }
            return a(timeline2, i2, z2);
        }
        Pair a = timeline.a(this.a, this.p, r(), C.b(z2));
        Object obj = ((Pair) Util.a((Object) a)).first;
        if (timeline2.c(obj) != -1) {
            return a;
        }
        Object a2 = ExoPlayerImplInternal.a(this.a, this.p, this.f, this.g, obj, timeline, timeline2);
        if (a2 == null) {
            return a(timeline2, -1, -9223372036854775807L);
        }
        timeline2.a(a2, this.p);
        return a(timeline2, this.p.c, C.a(timeline2.a(this.p.c, this.a, 0).l));
    }

    private PlaybackInfo a(PlaybackInfo playbackInfo, Timeline timeline, Pair pair) {
        Timeline timeline2 = timeline;
        Pair pair2 = pair;
        Assertions.a(timeline.c() || pair2 != null);
        Timeline timeline3 = playbackInfo.a;
        PlaybackInfo a = playbackInfo.a(timeline);
        if (timeline.c()) {
            MediaSource.MediaPeriodId a2 = PlaybackInfo.a();
            long b2 = C.b(this.C);
            PlaybackInfo a3 = a.a(a2, b2, b2, b2, 0, TrackGroupArray.a, this.k, bG.g()).a(a2);
            a3.q = a3.s;
            return a3;
        }
        Object obj = a.b.a;
        boolean z2 = !obj.equals(((Pair) Util.a((Object) pair)).first);
        MediaSource.MediaPeriodId mediaPeriodId = z2 ? new MediaSource.MediaPeriodId(pair2.first) : a.b;
        long longValue = ((Long) pair2.second).longValue();
        long b3 = C.b(z());
        if (!timeline3.c()) {
            b3 -= timeline3.a(obj, this.p).e;
        }
        if (z2 || longValue < b3) {
            MediaSource.MediaPeriodId mediaPeriodId2 = mediaPeriodId;
            Assertions.b(!mediaPeriodId2.a());
            MediaSource.MediaPeriodId mediaPeriodId3 = mediaPeriodId2;
            PlaybackInfo a4 = a.a(mediaPeriodId3, longValue, longValue, longValue, 0, z2 ? TrackGroupArray.a : a.h, z2 ? this.k : a.i, z2 ? bG.g() : a.j).a(mediaPeriodId3);
            a4.q = longValue;
            return a4;
        }
        if (longValue == b3) {
            int c2 = timeline2.c(a.k.a);
            if (c2 == -1 || timeline2.a(c2, this.p, false).c != timeline2.a(mediaPeriodId.a, this.p).c) {
                timeline2.a(mediaPeriodId.a, this.p);
                long j2 = mediaPeriodId.a() ? -9223372036854775807L : this.p.d;
                a = a.a(mediaPeriodId, a.s, a.s, a.d, j2 - a.s, a.h, a.i, a.j).a(mediaPeriodId);
                a.q = j2;
            }
        } else {
            MediaSource.MediaPeriodId mediaPeriodId4 = mediaPeriodId;
            Assertions.b(!mediaPeriodId4.a());
            long max = Math.max(0, a.r - (longValue - b3));
            long j3 = a.q;
            if (a.k.equals(a.b)) {
                j3 = longValue + max;
            }
            a = a.a(mediaPeriodId4, longValue, longValue, longValue, max, a.h, a.i, a.j);
            a.q = j2;
        }
        return a;
    }

    private Player.PositionInfo a(int i2, PlaybackInfo playbackInfo, int i3) {
        int i4;
        Object obj;
        int i5;
        Object obj2;
        long j2;
        long j3;
        PlaybackInfo playbackInfo2 = playbackInfo;
        Timeline.Period period = new Timeline.Period();
        if (!playbackInfo2.a.c()) {
            Object obj3 = playbackInfo2.b.a;
            playbackInfo2.a.a(obj3, period);
            int i6 = period.c;
            obj = obj3;
            i4 = playbackInfo2.a.c(obj3);
            obj2 = playbackInfo2.a.a(i6, this.a, 0).b;
            i5 = i6;
        } else {
            i5 = i3;
            obj2 = null;
            obj = null;
            i4 = -1;
        }
        if (i2 == 0) {
            j2 = period.e + period.d;
            if (playbackInfo2.b.a()) {
                j3 = b(playbackInfo);
                j2 = -9223372036854775807L;
                return new Player.PositionInfo(obj2, i5, obj, i4, C.a(j2), C.a(j3), playbackInfo2.b.b, playbackInfo2.b.c);
            } else if (playbackInfo2.b.e != -1 && this.j.b.a()) {
                j2 = b(this.j);
            }
        } else if (playbackInfo2.b.a()) {
            j2 = playbackInfo2.s;
            j3 = b(playbackInfo);
            return new Player.PositionInfo(obj2, i5, obj, i4, C.a(j2), C.a(j3), playbackInfo2.b.b, playbackInfo2.b.c);
        } else {
            j2 = period.e + playbackInfo2.s;
        }
        j3 = j2;
        return new Player.PositionInfo(obj2, i5, obj, i4, C.a(j2), C.a(j3), playbackInfo2.b.b, playbackInfo2.b.c);
    }

    private Player.PositionInfo a(long j2) {
        int i2;
        Object obj;
        Object obj2;
        int r2 = r();
        if (!this.j.a.c()) {
            Object obj3 = this.j.b.a;
            this.j.a.a(obj3, this.p);
            i2 = this.j.a.c(obj3);
            obj2 = this.j.a.a(r2, this.a, 0).b;
            obj = obj3;
        } else {
            obj2 = null;
            obj = null;
            i2 = -1;
        }
        long a = C.a(j2);
        return new Player.PositionInfo(obj2, r2, obj, i2, a, this.j.b.a() ? C.a(b(this.j)) : a, this.j.b.b, this.j.b.c);
    }

    static final /* synthetic */ void a(int i2, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, Player.EventListener eventListener) {
        eventListener.onPositionDiscontinuity(i2);
        eventListener.onPositionDiscontinuity(positionInfo, positionInfo2, i2);
    }

    private void a(PlaybackInfo playbackInfo, int i2, int i3, boolean z2, boolean z3, int i4, long j2, int i5) {
        PlaybackInfo playbackInfo2 = playbackInfo;
        int i6 = i4;
        PlaybackInfo playbackInfo3 = this.j;
        this.j = playbackInfo2;
        Pair a = a(playbackInfo, playbackInfo3, z3, i4, !playbackInfo3.a.equals(playbackInfo2.a));
        boolean booleanValue = ((Boolean) a.first).booleanValue();
        int intValue = ((Integer) a.second).intValue();
        MediaMetadata mediaMetadata = this.i;
        MediaItem mediaItem = null;
        if (booleanValue) {
            if (!playbackInfo2.a.c()) {
                mediaItem = playbackInfo2.a.a(playbackInfo2.a.a(playbackInfo2.b.a, this.p).c, this.a, 0).c;
            }
            this.i = mediaItem != null ? mediaItem.d : MediaMetadata.a;
        }
        if (!playbackInfo3.j.equals(playbackInfo2.j)) {
            mediaMetadata = mediaMetadata.a().a(playbackInfo2.j).a();
        }
        boolean z4 = !mediaMetadata.equals(this.i);
        this.i = mediaMetadata;
        if (!playbackInfo3.a.equals(playbackInfo2.a)) {
            int i7 = i2;
            this.c.a(0, new ExoPlayerImpl$$Lambda$6(playbackInfo, i2));
        }
        if (z3) {
            this.c.a(12, new ExoPlayerImpl$$Lambda$7(i6, a(i6, playbackInfo3, i5), a(j2)));
        }
        if (booleanValue) {
            this.c.a(1, new ExoPlayerImpl$$Lambda$8(mediaItem, intValue));
        }
        if (!(playbackInfo3.f == playbackInfo2.f || playbackInfo2.f == null)) {
            this.c.a(11, new ExoPlayerImpl$$Lambda$9(playbackInfo));
        }
        if (playbackInfo3.i != playbackInfo2.i) {
            this.m.onSelectionActivated(playbackInfo2.i.d);
            this.c.a(2, new ExoPlayerImpl$$Lambda$10(playbackInfo, new TrackSelectionArray(playbackInfo2.i.c)));
        }
        if (!playbackInfo3.j.equals(playbackInfo2.j)) {
            this.c.a(3, new ExoPlayerImpl$$Lambda$11(playbackInfo));
        }
        if (z4) {
            this.c.a(15, new ExoPlayerImpl$$Lambda$12(this.i));
        }
        if (playbackInfo3.g != playbackInfo2.g) {
            this.c.a(4, new ExoPlayerImpl$$Lambda$13(playbackInfo));
        }
        if (!(playbackInfo3.e == playbackInfo2.e && playbackInfo3.l == playbackInfo2.l)) {
            this.c.a(-1, new ExoPlayerImpl$$Lambda$14(playbackInfo));
        }
        if (playbackInfo3.e != playbackInfo2.e) {
            this.c.a(5, new ExoPlayerImpl$$Lambda$15(playbackInfo));
        }
        if (playbackInfo3.l != playbackInfo2.l) {
            this.c.a(6, new ExoPlayerImpl$$Lambda$16(playbackInfo, i3));
        }
        if (playbackInfo3.m != playbackInfo2.m) {
            this.c.a(7, new ExoPlayerImpl$$Lambda$17(playbackInfo));
        }
        if (c(playbackInfo3) != c(playbackInfo)) {
            this.c.a(8, new ExoPlayerImpl$$Lambda$18(playbackInfo));
        }
        if (!playbackInfo3.n.equals(playbackInfo2.n)) {
            this.c.a(13, new ExoPlayerImpl$$Lambda$19(playbackInfo));
        }
        if (z2) {
            this.c.a(-1, ExoPlayerImpl$$Lambda$20.a);
        }
        H();
        this.c.a();
        if (playbackInfo3.o != playbackInfo2.o) {
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                ((ExoPlayer.AudioOffloadListener) it.next()).c();
            }
        }
        if (playbackInfo3.p != playbackInfo2.p) {
            Iterator it2 = this.d.iterator();
            while (it2.hasNext()) {
                ((ExoPlayer.AudioOffloadListener) it2.next()).d();
            }
        }
    }

    private static long b(PlaybackInfo playbackInfo) {
        Timeline.Window window = new Timeline.Window();
        Timeline.Period period = new Timeline.Period();
        playbackInfo.a.a(playbackInfo.b.a, period);
        return playbackInfo.c == -9223372036854775807L ? playbackInfo.a.a(period.c, window, 0).l : period.e + playbackInfo.c;
    }

    private PlaybackInfo b(int i2, int i3) {
        boolean z2 = false;
        Assertions.a(i2 >= 0 && i3 >= i2 && i3 <= this.q.size());
        int r2 = r();
        Timeline timeline = this.j.a;
        int size = this.q.size();
        this.w++;
        c(i2, i3);
        Timeline I = I();
        PlaybackInfo a = a(this.j, I, a(timeline, I));
        if (a.e != 1 && a.e != 4 && i2 < i3 && i3 == size && r2 >= a.a.a()) {
            z2 = true;
        }
        if (z2) {
            a = a.a(4);
        }
        this.o.a(i2, i3, this.A);
        return a;
    }

    private List b(int i2, List list) {
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < list.size(); i3++) {
            MediaSourceList.MediaSourceHolder mediaSourceHolder = new MediaSourceList.MediaSourceHolder((MediaSource) list.get(i3), this.r);
            arrayList.add(mediaSourceHolder);
            this.q.add(i3 + i2, new MediaSourceHolderSnapshot(mediaSourceHolder.b, mediaSourceHolder.a.a));
        }
        this.A = this.A.a(i2, arrayList.size());
        return arrayList;
    }

    static final /* synthetic */ void b(PlaybackInfo playbackInfo, int i2, Player.EventListener eventListener) {
        Object obj;
        if (playbackInfo.a.a() == 1) {
            obj = playbackInfo.a.a(0, new Timeline.Window(), 0).d;
        } else {
            obj = null;
        }
        eventListener.onTimelineChanged(playbackInfo.a, obj, i2);
        eventListener.onTimelineChanged(playbackInfo.a, i2);
    }

    private void c(int i2, int i3) {
        for (int i4 = i3 - 1; i4 >= i2; i4--) {
            this.q.remove(i4);
        }
        this.A = this.A.b(i2, i3);
    }

    private static boolean c(PlaybackInfo playbackInfo) {
        return playbackInfo.e == 3 && playbackInfo.l && playbackInfo.m == 0;
    }

    static final /* synthetic */ void f(PlaybackInfo playbackInfo, Player.EventListener eventListener) {
        eventListener.onLoadingChanged(playbackInfo.g);
        eventListener.onIsLoadingChanged(playbackInfo.g);
    }

    public final long A() {
        long a;
        if (this.j.a.c()) {
            return this.C;
        }
        int i2 = (this.j.k.d > this.j.b.d ? 1 : (this.j.k.d == this.j.b.d ? 0 : -1));
        PlaybackInfo playbackInfo = this.j;
        if (i2 != 0) {
            a = playbackInfo.a.a(r(), this.a, 0).m;
        } else {
            long j2 = playbackInfo.q;
            if (this.j.k.a()) {
                Timeline.Period a2 = this.j.a.a(this.j.k.a, this.p);
                long a3 = a2.a(this.j.k.b);
                j2 = a3 == Long.MIN_VALUE ? a2.d : a3;
            }
            a = a(this.j.a, this.j.k, j2);
        }
        return C.a(a);
    }

    public final TrackGroupArray B() {
        return this.j.h;
    }

    public final TrackSelectionArray C() {
        return new TrackSelectionArray(this.j.i.c);
    }

    public final List D() {
        return this.j.j;
    }

    public final Timeline E() {
        return this.j.a;
    }

    public final /* synthetic */ List F() {
        return bG.g();
    }

    public final PlayerMessage a(PlayerMessage.Target target) {
        return new PlayerMessage(this.o, target, this.j.a, r(), this.v, this.o.b);
    }

    public final void a(int i2, int i3) {
        PlaybackInfo b2 = b(i2, Math.min(i3, this.q.size()));
        a(b2, 0, 1, false, !b2.b.a.equals(this.j.b.a), 4, a(b2), -1);
    }

    public final void a(int i2, long j2) {
        int i3 = i2;
        Timeline timeline = this.j.a;
        if (i3 < 0 || (!timeline.c() && i3 >= timeline.a())) {
            throw new IllegalSeekPositionException();
        }
        int i4 = 1;
        this.w++;
        if (w()) {
            Log.c("ExoPlayerImpl", "seekTo ignored because an ad is playing");
            ExoPlayerImplInternal.PlaybackInfoUpdate playbackInfoUpdate = new ExoPlayerImplInternal.PlaybackInfoUpdate(this.j);
            playbackInfoUpdate.a(1);
            this.n.a(playbackInfoUpdate);
            return;
        }
        if (this.j.e != 1) {
            i4 = 2;
        }
        int r2 = r();
        PlaybackInfo a = a(this.j.a(i4), timeline, a(timeline, i2, j2));
        this.o.a.a(3, new ExoPlayerImplInternal.SeekPosition(timeline, i2, C.b(j2))).a();
        a(a, 0, 1, true, true, 1, a(a), r2);
    }

    public final void a(int i2, List list) {
        int min = Math.min(i2, this.q.size());
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < list.size(); i3++) {
            arrayList.add(this.s.a((MediaItem) list.get(i3)));
        }
        Assertions.a(min >= 0);
        Timeline timeline = this.j.a;
        this.w++;
        List b2 = b(min, (List) arrayList);
        Timeline I = I();
        PlaybackInfo a = a(this.j, I, a(timeline, I));
        this.o.a.a(18, min, 0, new ExoPlayerImplInternal.MediaSourceListUpdateMessage(b2, this.A, (byte) 0)).a();
        a(a, 0, 1, false, false, 5, -9223372036854775807L, -1);
    }

    public final void a(SurfaceView surfaceView) {
    }

    public final void a(TextureView textureView) {
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void a(ExoPlayerImplInternal.PlaybackInfoUpdate playbackInfoUpdate) {
        long j2;
        boolean z2;
        this.w -= playbackInfoUpdate.b;
        boolean z3 = true;
        if (playbackInfoUpdate.c) {
            this.x = playbackInfoUpdate.d;
            this.y = true;
        }
        if (playbackInfoUpdate.e) {
            this.z = playbackInfoUpdate.f;
        }
        if (this.w == 0) {
            Timeline timeline = playbackInfoUpdate.a.a;
            if (!this.j.a.c() && timeline.c()) {
                this.B = -1;
                this.C = 0;
            }
            if (!timeline.c()) {
                List asList = Arrays.asList(((PlaylistTimeline) timeline).a);
                Assertions.b(asList.size() == this.q.size());
                for (int i2 = 0; i2 < asList.size(); i2++) {
                    Timeline unused = ((MediaSourceHolderSnapshot) this.q.get(i2)).b = (Timeline) asList.get(i2);
                }
            }
            long j3 = -9223372036854775807L;
            if (this.y) {
                if (playbackInfoUpdate.a.b.equals(this.j.b) && playbackInfoUpdate.a.d == this.j.s) {
                    z3 = false;
                }
                if (z3) {
                    j3 = (timeline.c() || playbackInfoUpdate.a.b.a()) ? playbackInfoUpdate.a.d : a(timeline, playbackInfoUpdate.a.b, playbackInfoUpdate.a.d);
                }
                j2 = j3;
                z2 = z3;
            } else {
                j2 = -9223372036854775807L;
                z2 = false;
            }
            this.y = false;
            a(playbackInfoUpdate.a, 1, this.z, false, z2, this.x, j2, -1);
        }
    }

    public final void a(PlaybackParameters playbackParameters) {
        if (playbackParameters == null) {
            playbackParameters = PlaybackParameters.a;
        }
        if (!this.j.n.equals(playbackParameters)) {
            PlaybackInfo a = this.j.a(playbackParameters);
            this.w++;
            this.o.a.a(4, playbackParameters).a();
            a(a, 0, 1, false, false, 5, -9223372036854775807L, -1);
        }
    }

    public final void a(Player.EventListener eventListener) {
        this.c.a(eventListener);
    }

    public final void a(Player.Listener listener) {
        a((Player.EventListener) listener);
    }

    public final void a(boolean z2) {
        a(z2, 0, 1);
    }

    public final void a(boolean z2, int i2, int i3) {
        if (this.j.l != z2 || this.j.m != i2) {
            this.w++;
            PlaybackInfo a = this.j.a(z2, i2);
            this.o.a(z2, i2);
            a(a, 0, i3, false, false, 5, -9223372036854775807L, -1);
        }
    }

    public final void a(boolean z2, ExoPlaybackException exoPlaybackException) {
        PlaybackInfo playbackInfo;
        if (z2) {
            playbackInfo = b(0, this.q.size()).a((ExoPlaybackException) null);
        } else {
            PlaybackInfo playbackInfo2 = this.j;
            playbackInfo = playbackInfo2.a(playbackInfo2.b);
            playbackInfo.q = playbackInfo.s;
            playbackInfo.r = 0;
        }
        PlaybackInfo a = playbackInfo.a(1);
        if (exoPlaybackException != null) {
            a = a.a(exoPlaybackException);
        }
        PlaybackInfo playbackInfo3 = a;
        this.w++;
        this.o.a();
        a(playbackInfo3, 0, 1, false, playbackInfo3.a.c() && !this.j.a.c(), 4, a(playbackInfo3), -1);
    }

    public final void b(int i2) {
        if (this.f != i2) {
            this.f = i2;
            this.o.a.a(11, i2, 0).a();
            this.c.a(9, new ExoPlayerImpl$$Lambda$2(i2));
            H();
            this.c.a();
        }
    }

    public final void b(SurfaceView surfaceView) {
    }

    public final void b(TextureView textureView) {
    }

    public final void b(Player.EventListener eventListener) {
        this.c.b(eventListener);
    }

    public final void b(Player.Listener listener) {
        b((Player.EventListener) listener);
    }

    public final void b(boolean z2) {
        if (this.g != z2) {
            this.g = z2;
            this.o.a.a(12, z2 ? 1 : 0, 0).a();
            this.c.a(10, new ExoPlayerImpl$$Lambda$3(z2));
            H();
            this.c.a();
        }
    }

    public final void c(boolean z2) {
        a(z2, (ExoPlaybackException) null);
    }

    public final Looper f() {
        return this.e;
    }

    public final Player.Commands g() {
        return this.h;
    }

    public final TrackSelector getTrackSelector() {
        return this.m;
    }

    public final int h() {
        return this.j.e;
    }

    public final int i() {
        return this.j.m;
    }

    public final ExoPlaybackException j() {
        return this.j.f;
    }

    public final void k() {
        if (this.j.e == 1) {
            PlaybackInfo a = this.j.a((ExoPlaybackException) null);
            PlaybackInfo a2 = a.a(a.a.c() ? 4 : 2);
            this.w++;
            this.o.a.a(0).a();
            a(a2, 1, 1, false, false, 5, -9223372036854775807L, -1);
        }
    }

    public final boolean l() {
        return this.j.l;
    }

    public final int m() {
        return this.f;
    }

    public final boolean n() {
        return this.g;
    }

    public final PlaybackParameters o() {
        return this.j.n;
    }

    public final void p() {
        String hexString = Integer.toHexString(System.identityHashCode(this));
        String str = Util.e;
        String a = ExoPlayerLibraryInfo.a();
        Log.b("ExoPlayerImpl", new StringBuilder(String.valueOf(hexString).length() + 36 + String.valueOf(str).length() + String.valueOf(a).length()).append("Release ").append(hexString).append(" [ExoPlayerLib/2.14.1] [").append(str).append("] [").append(a).append("]").toString());
        if (!this.o.b()) {
            this.c.b(11, ExoPlayerImpl$$Lambda$4.a);
        }
        this.c.b();
        this.b.c();
        AnalyticsCollector analyticsCollector = this.t;
        if (analyticsCollector != null) {
            this.u.a(analyticsCollector);
        }
        PlaybackInfo a2 = this.j.a(1);
        this.j = a2;
        PlaybackInfo a3 = a2.a(a2.b);
        this.j = a3;
        a3.q = a3.s;
        this.j.r = 0;
    }

    public final int q() {
        if (this.j.a.c()) {
            return 0;
        }
        return this.j.a.c(this.j.b.a);
    }

    public final int r() {
        int G = G();
        if (G == -1) {
            return 0;
        }
        return G;
    }

    public final long s() {
        if (!w()) {
            return e();
        }
        this.j.a.a(this.j.b.a, this.p);
        return C.a(-9223372036854775807L);
    }

    public final long t() {
        return C.a(a(this.j));
    }

    public final long u() {
        return w() ? this.j.k.equals(this.j.b) ? C.a(this.j.q) : s() : A();
    }

    public final long v() {
        return C.a(this.j.r);
    }

    public final boolean w() {
        return this.j.b.a();
    }

    public final int x() {
        if (w()) {
            return this.j.b.b;
        }
        return -1;
    }

    public final int y() {
        if (w()) {
            return this.j.b.c;
        }
        return -1;
    }

    public final long z() {
        if (!w()) {
            return t();
        }
        this.j.a.a(this.j.b.a, this.p);
        return this.j.c == -9223372036854775807L ? C.a(this.j.a.a(r(), this.a, 0).l) : C.a(this.p.e) + C.a(this.j.c);
    }
}
