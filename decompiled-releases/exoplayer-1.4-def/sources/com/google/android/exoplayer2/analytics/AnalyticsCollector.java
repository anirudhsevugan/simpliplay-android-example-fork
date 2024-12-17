package com.google.android.exoplayer2.analytics;

import android.util.SparseArray;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.PointerIconCompat;
import com.dreamers.exoplayercore.repack.C0000a;
import com.dreamers.exoplayercore.repack.bG;
import com.dreamers.exoplayercore.repack.bM;
import com.dreamers.exoplayercore.repack.bN;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Player$EventListener$$CC;
import com.google.android.exoplayer2.Player$Listener$$CC;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.audio.AudioRendererEventListener$$CC;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.decoder.DecoderReuseEvaluation;
import com.google.android.exoplayer2.device.DeviceInfo;
import com.google.android.exoplayer2.device.DeviceListener$$CC;
import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.drm.DrmSessionEventListener$$CC;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.source.LoadEventInfo;
import com.google.android.exoplayer2.source.MediaLoadData;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.ListenerSet;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoListener$$CC;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import com.google.android.exoplayer2.video.VideoRendererEventListener$$CC;
import com.google.android.exoplayer2.video.VideoSize;
import java.io.IOException;
import java.util.List;

public class AnalyticsCollector implements Player.Listener, AudioRendererEventListener, DrmSessionEventListener, MediaSourceEventListener, BandwidthMeter.EventListener, VideoRendererEventListener {
    public final MediaPeriodQueueTracker a;
    final SparseArray b;
    public ListenerSet c;
    public Player d;
    private final Clock e;
    private final Timeline.Period f;
    private final Timeline.Window g = new Timeline.Window();
    private boolean h;

    public final class MediaPeriodQueueTracker {
        public final Timeline.Period a;
        public bG b = bG.g();
        public MediaSource.MediaPeriodId c;
        public MediaSource.MediaPeriodId d;
        public MediaSource.MediaPeriodId e;
        private bM f = bM.a();

        public MediaPeriodQueueTracker(Timeline.Period period) {
            this.a = period;
        }

        public static MediaSource.MediaPeriodId a(Player player, bG bGVar, MediaSource.MediaPeriodId mediaPeriodId, Timeline.Period period) {
            Timeline E = player.E();
            int q = player.q();
            Object a2 = E.c() ? null : E.a(q);
            int b2 = (player.w() || E.c()) ? -1 : E.a(q, period, false).b(C.b(player.t()) - period.e);
            for (int i = 0; i < bGVar.size(); i++) {
                MediaSource.MediaPeriodId mediaPeriodId2 = (MediaSource.MediaPeriodId) bGVar.get(i);
                if (a(mediaPeriodId2, a2, player.w(), player.x(), player.y(), b2)) {
                    return mediaPeriodId2;
                }
            }
            if (bGVar.isEmpty() && mediaPeriodId != null) {
                if (a(mediaPeriodId, a2, player.w(), player.x(), player.y(), b2)) {
                    return mediaPeriodId;
                }
            }
            return null;
        }

        private void a(bN bNVar, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline) {
            if (mediaPeriodId != null) {
                if (timeline.c(mediaPeriodId.a) != -1) {
                    bNVar.a(mediaPeriodId, timeline);
                    return;
                }
                Timeline timeline2 = (Timeline) this.f.get(mediaPeriodId);
                if (timeline2 != null) {
                    bNVar.a(mediaPeriodId, timeline2);
                }
            }
        }

        private static boolean a(MediaSource.MediaPeriodId mediaPeriodId, Object obj, boolean z, int i, int i2, int i3) {
            if (!mediaPeriodId.a.equals(obj)) {
                return false;
            }
            if (z && mediaPeriodId.b == i && mediaPeriodId.c == i2) {
                return true;
            }
            return !z && mediaPeriodId.b == -1 && mediaPeriodId.e == i3;
        }

        public final Timeline a(MediaSource.MediaPeriodId mediaPeriodId) {
            return (Timeline) this.f.get(mediaPeriodId);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0054, code lost:
            if (r3.b.contains(r3.c) == false) goto L_0x0056;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0032, code lost:
            if (com.dreamers.exoplayercore.repack.P.a(r3.c, r3.e) == false) goto L_0x0056;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void a(com.google.android.exoplayer2.Timeline r4) {
            /*
                r3 = this;
                com.dreamers.exoplayercore.repack.bN r0 = com.dreamers.exoplayercore.repack.bM.b()
                com.dreamers.exoplayercore.repack.bG r1 = r3.b
                boolean r1 = r1.isEmpty()
                if (r1 == 0) goto L_0x0035
                com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r1 = r3.d
                r3.a(r0, r1, r4)
                com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r1 = r3.e
                com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r2 = r3.d
                boolean r1 = com.dreamers.exoplayercore.repack.P.a(r1, r2)
                if (r1 != 0) goto L_0x0020
                com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r1 = r3.e
                r3.a(r0, r1, r4)
            L_0x0020:
                com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r1 = r3.c
                com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r2 = r3.d
                boolean r1 = com.dreamers.exoplayercore.repack.P.a(r1, r2)
                if (r1 != 0) goto L_0x005b
                com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r1 = r3.c
                com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r2 = r3.e
                boolean r1 = com.dreamers.exoplayercore.repack.P.a(r1, r2)
                if (r1 != 0) goto L_0x005b
                goto L_0x0056
            L_0x0035:
                r1 = 0
            L_0x0036:
                com.dreamers.exoplayercore.repack.bG r2 = r3.b
                int r2 = r2.size()
                if (r1 >= r2) goto L_0x004c
                com.dreamers.exoplayercore.repack.bG r2 = r3.b
                java.lang.Object r2 = r2.get(r1)
                com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r2 = (com.google.android.exoplayer2.source.MediaSource.MediaPeriodId) r2
                r3.a(r0, r2, r4)
                int r1 = r1 + 1
                goto L_0x0036
            L_0x004c:
                com.dreamers.exoplayercore.repack.bG r1 = r3.b
                com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r2 = r3.c
                boolean r1 = r1.contains(r2)
                if (r1 != 0) goto L_0x005b
            L_0x0056:
                com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r1 = r3.c
                r3.a(r0, r1, r4)
            L_0x005b:
                com.dreamers.exoplayercore.repack.bM r4 = r0.a()
                r3.f = r4
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.analytics.AnalyticsCollector.MediaPeriodQueueTracker.a(com.google.android.exoplayer2.Timeline):void");
        }
    }

    public AnalyticsCollector(Clock clock) {
        this.e = (Clock) Assertions.b((Object) clock);
        this.c = new ListenerSet(Util.c(), clock, AnalyticsCollector$$Lambda$0.a);
        Timeline.Period period = new Timeline.Period();
        this.f = period;
        this.a = new MediaPeriodQueueTracker(period);
        this.b = new SparseArray();
    }

    private AnalyticsListener.EventTime a(Timeline timeline, int i, MediaSource.MediaPeriodId mediaPeriodId) {
        Timeline timeline2 = timeline;
        int i2 = i;
        MediaSource.MediaPeriodId mediaPeriodId2 = timeline.c() ? null : mediaPeriodId;
        long a2 = this.e.a();
        boolean z = true;
        boolean z2 = timeline2.equals(this.d.E()) && i2 == this.d.r();
        long j = 0;
        if (mediaPeriodId2 != null && mediaPeriodId2.a()) {
            if (!(z2 && this.d.x() == mediaPeriodId2.b && this.d.y() == mediaPeriodId2.c)) {
                z = false;
            }
            if (z) {
                j = this.d.t();
            }
        } else if (z2) {
            j = this.d.z();
        } else if (!timeline.c()) {
            j = C.a(timeline2.a(i2, this.g, 0).l);
        }
        return new AnalyticsListener.EventTime(a2, timeline, i, mediaPeriodId2, j, this.d.E(), this.d.r(), this.a.c, this.d.t(), this.d.v());
    }

    private AnalyticsListener.EventTime a(MediaSource.MediaPeriodId mediaPeriodId) {
        Assertions.b((Object) this.d);
        Timeline a2 = mediaPeriodId == null ? null : this.a.a(mediaPeriodId);
        if (mediaPeriodId != null && a2 != null) {
            return a(a2, a2.a(mediaPeriodId.a, this.f).c, mediaPeriodId);
        }
        int r = this.d.r();
        Timeline E = this.d.E();
        if (!(r < E.a())) {
            E = Timeline.b;
        }
        return a(E, r, (MediaSource.MediaPeriodId) null);
    }

    static final /* synthetic */ void a(AnalyticsListener.EventTime eventTime, int i, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, AnalyticsListener analyticsListener) {
        analyticsListener.b();
        analyticsListener.a(eventTime, positionInfo, positionInfo2, i);
    }

    static final /* synthetic */ void a(AnalyticsListener.EventTime eventTime, int i, AnalyticsListener analyticsListener) {
        analyticsListener.u();
        analyticsListener.g(eventTime, i);
    }

    private void a(AnalyticsListener.EventTime eventTime, int i, ListenerSet.Event event) {
        this.b.put(i, eventTime);
        this.c.b(i, event);
    }

    static final /* synthetic */ void a(AnalyticsListener.EventTime eventTime, Format format, AnalyticsListener analyticsListener) {
        analyticsListener.q();
        analyticsListener.b(eventTime, format);
        analyticsListener.i();
    }

    static final /* synthetic */ void a(AnalyticsListener.EventTime eventTime, VideoSize videoSize, AnalyticsListener analyticsListener) {
        analyticsListener.a(eventTime, videoSize);
        analyticsListener.t();
    }

    static final /* synthetic */ void b(AnalyticsListener.EventTime eventTime, Format format, AnalyticsListener analyticsListener) {
        analyticsListener.l();
        analyticsListener.a(eventTime, format);
        analyticsListener.i();
    }

    static final /* synthetic */ void b(AnalyticsListener.EventTime eventTime, String str, AnalyticsListener analyticsListener) {
        analyticsListener.c(eventTime, str);
        analyticsListener.p();
        analyticsListener.h();
    }

    static final /* synthetic */ void c(AnalyticsListener.EventTime eventTime, boolean z, AnalyticsListener analyticsListener) {
        analyticsListener.e();
        analyticsListener.c(eventTime, z);
    }

    private AnalyticsListener.EventTime d(int i, MediaSource.MediaPeriodId mediaPeriodId) {
        Assertions.b((Object) this.d);
        boolean z = true;
        if (mediaPeriodId != null) {
            if (this.a.a(mediaPeriodId) == null) {
                z = false;
            }
            return z ? a(mediaPeriodId) : a(Timeline.b, i, mediaPeriodId);
        }
        Timeline E = this.d.E();
        if (i >= E.a()) {
            z = false;
        }
        if (!z) {
            E = Timeline.b;
        }
        return a(E, i, (MediaSource.MediaPeriodId) null);
    }

    static final /* synthetic */ void d() {
    }

    static final /* synthetic */ void d(AnalyticsListener.EventTime eventTime, AnalyticsListener analyticsListener) {
        analyticsListener.d(eventTime);
        analyticsListener.j();
    }

    static final /* synthetic */ void d(AnalyticsListener.EventTime eventTime, String str, AnalyticsListener analyticsListener) {
        analyticsListener.a(eventTime, str);
        analyticsListener.k();
        analyticsListener.h();
    }

    static final /* synthetic */ void e() {
    }

    static final /* synthetic */ void e(AnalyticsListener.EventTime eventTime, AnalyticsListener analyticsListener) {
        analyticsListener.c(eventTime);
        analyticsListener.g();
    }

    static final /* synthetic */ void f() {
    }

    static final /* synthetic */ void f(AnalyticsListener.EventTime eventTime, AnalyticsListener analyticsListener) {
        analyticsListener.b(eventTime);
        analyticsListener.j();
    }

    static final /* synthetic */ void g(AnalyticsListener.EventTime eventTime, AnalyticsListener analyticsListener) {
        analyticsListener.a(eventTime);
        analyticsListener.g();
    }

    static final /* synthetic */ void i() {
    }

    static final /* synthetic */ void j() {
    }

    private AnalyticsListener.EventTime k() {
        return a(this.a.c);
    }

    private AnalyticsListener.EventTime l() {
        return a(this.a.d);
    }

    private AnalyticsListener.EventTime m() {
        return a(this.a.e);
    }

    public final void a() {
        DrmSessionEventListener$$CC.a();
    }

    public final void a(int i, long j) {
        AnalyticsListener.EventTime l = l();
        a(l, 1023, (ListenerSet.Event) new AnalyticsCollector$$Lambda$21(l, i));
    }

    public final void a(int i, long j, long j2) {
        AnalyticsListener.EventTime m = m();
        a(m, (int) PointerIconCompat.TYPE_NO_DROP, (ListenerSet.Event) new AnalyticsCollector$$Lambda$9(m, i, j, j2));
    }

    public final void a(int i, MediaSource.MediaPeriodId mediaPeriodId) {
        AnalyticsListener.EventTime d2 = d(i, mediaPeriodId);
        a(d2, 1031, (ListenerSet.Event) new AnalyticsCollector$$Lambda$54(d2));
    }

    public final void a(int i, MediaSource.MediaPeriodId mediaPeriodId, int i2) {
        AnalyticsListener.EventTime d2 = d(i, mediaPeriodId);
        a(d2, 1030, (ListenerSet.Event) new AnalyticsCollector$$Lambda$53(d2, i2));
    }

    public final void a(int i, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
        a(d(i, mediaPeriodId), 1000, (ListenerSet.Event) new AnalyticsCollector$$Lambda$29());
    }

    public final void a(int i, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
        AnalyticsListener.EventTime d2 = d(i, mediaPeriodId);
        a(d2, 1003, (ListenerSet.Event) new AnalyticsCollector$$Lambda$32(d2, iOException));
    }

    public final void a(int i, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
        AnalyticsListener.EventTime d2 = d(i, mediaPeriodId);
        a(d2, 1005, (ListenerSet.Event) new AnalyticsCollector$$Lambda$33(d2, mediaLoadData));
    }

    public final void a(int i, MediaSource.MediaPeriodId mediaPeriodId, Exception exc) {
        AnalyticsListener.EventTime d2 = d(i, mediaPeriodId);
        a(d2, 1032, (ListenerSet.Event) new AnalyticsCollector$$Lambda$55(d2, exc));
    }

    public final void a(long j) {
        AnalyticsListener.EventTime m = m();
        a(m, (int) PointerIconCompat.TYPE_COPY, (ListenerSet.Event) new AnalyticsCollector$$Lambda$8(m, j));
    }

    public final void a(long j, int i) {
        AnalyticsListener.EventTime l = l();
        a(l, 1026, (ListenerSet.Event) new AnalyticsCollector$$Lambda$26(l, j, i));
    }

    public final void a(Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
        AnalyticsListener.EventTime m = m();
        a(m, 1022, (ListenerSet.Event) new AnalyticsCollector$$Lambda$20(m, format));
    }

    public final void a(AnalyticsListener analyticsListener) {
        Assertions.b((Object) analyticsListener);
        this.c.a(analyticsListener);
    }

    public final void a(DecoderCounters decoderCounters) {
        AnalyticsListener.EventTime m = m();
        a(m, (int) PointerIconCompat.TYPE_GRAB, (ListenerSet.Event) new AnalyticsCollector$$Lambda$18(m, decoderCounters));
    }

    public final void a(Exception exc) {
        AnalyticsListener.EventTime m = m();
        a(m, 1038, (ListenerSet.Event) new AnalyticsCollector$$Lambda$27(m, exc));
    }

    public final void a(Object obj, long j) {
        AnalyticsListener.EventTime m = m();
        a(m, 1027, (ListenerSet.Event) new AnalyticsCollector$$Lambda$25(m, obj));
    }

    public final void a(String str) {
        AnalyticsListener.EventTime m = m();
        a(m, 1024, (ListenerSet.Event) new AnalyticsCollector$$Lambda$22(m, str));
    }

    public final void a(String str, long j, long j2) {
        AnalyticsListener.EventTime m = m();
        a(m, (int) PointerIconCompat.TYPE_GRABBING, (ListenerSet.Event) new AnalyticsCollector$$Lambda$19(m, str, j2, j));
    }

    public final void b() {
        AnalyticsListener.EventTime k = k();
        this.b.put(1036, k);
        ListenerSet listenerSet = this.c;
        listenerSet.b.a(1, 1036, 0, new AnalyticsCollector$$Lambda$2(k)).a();
    }

    public final void b(int i, long j, long j2) {
        MediaPeriodQueueTracker mediaPeriodQueueTracker = this.a;
        a(a(mediaPeriodQueueTracker.b.isEmpty() ? null : (MediaSource.MediaPeriodId) C0000a.b((Iterable) mediaPeriodQueueTracker.b)), (int) PointerIconCompat.TYPE_CELL, (ListenerSet.Event) new AnalyticsCollector$$Lambda$52());
    }

    public final void b(int i, MediaSource.MediaPeriodId mediaPeriodId) {
        AnalyticsListener.EventTime d2 = d(i, mediaPeriodId);
        a(d2, 1033, (ListenerSet.Event) new AnalyticsCollector$$Lambda$56(d2));
    }

    public final void b(int i, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
        a(d(i, mediaPeriodId), 1001, (ListenerSet.Event) new AnalyticsCollector$$Lambda$30());
    }

    public final void b(int i, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
        AnalyticsListener.EventTime d2 = d(i, mediaPeriodId);
        a(d2, 1004, (ListenerSet.Event) new AnalyticsCollector$$Lambda$34(d2, mediaLoadData));
    }

    public final void b(Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
        AnalyticsListener.EventTime m = m();
        a(m, (int) PointerIconCompat.TYPE_ALIAS, (ListenerSet.Event) new AnalyticsCollector$$Lambda$7(m, format));
    }

    public final void b(DecoderCounters decoderCounters) {
        AnalyticsListener.EventTime l = l();
        a(l, (int) InputDeviceCompat.SOURCE_GAMEPAD, (ListenerSet.Event) new AnalyticsCollector$$Lambda$23(l, decoderCounters));
    }

    public final void b(Exception exc) {
        AnalyticsListener.EventTime m = m();
        a(m, (int) PointerIconCompat.TYPE_ZOOM_IN, (ListenerSet.Event) new AnalyticsCollector$$Lambda$13(m, exc));
    }

    public final void b(String str) {
        AnalyticsListener.EventTime m = m();
        a(m, (int) PointerIconCompat.TYPE_ALL_SCROLL, (ListenerSet.Event) new AnalyticsCollector$$Lambda$10(m, str));
    }

    public final void b(String str, long j, long j2) {
        AnalyticsListener.EventTime m = m();
        a(m, (int) PointerIconCompat.TYPE_VERTICAL_TEXT, (ListenerSet.Event) new AnalyticsCollector$$Lambda$6(m, str, j2, j));
    }

    public final void c() {
        if (!this.h) {
            AnalyticsListener.EventTime k = k();
            this.h = true;
            a(k, -1, (ListenerSet.Event) new AnalyticsCollector$$Lambda$3(k));
        }
    }

    public final void c(int i, MediaSource.MediaPeriodId mediaPeriodId) {
        AnalyticsListener.EventTime d2 = d(i, mediaPeriodId);
        a(d2, 1035, (ListenerSet.Event) new AnalyticsCollector$$Lambda$58(d2));
    }

    public final void c(int i, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
        a(d(i, mediaPeriodId), 1002, (ListenerSet.Event) new AnalyticsCollector$$Lambda$31());
    }

    public final void c(DecoderCounters decoderCounters) {
        AnalyticsListener.EventTime m = m();
        a(m, (int) PointerIconCompat.TYPE_TEXT, (ListenerSet.Event) new AnalyticsCollector$$Lambda$5(m, decoderCounters));
    }

    public final void c(Exception exc) {
        AnalyticsListener.EventTime m = m();
        a(m, 1037, (ListenerSet.Event) new AnalyticsCollector$$Lambda$14(m, exc));
    }

    public final void d(DecoderCounters decoderCounters) {
        AnalyticsListener.EventTime l = l();
        a(l, (int) PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW, (ListenerSet.Event) new AnalyticsCollector$$Lambda$11(l, decoderCounters));
    }

    public final void g() {
        AudioRendererEventListener$$CC.a();
    }

    public final void h() {
        VideoRendererEventListener$$CC.a();
    }

    public void onAvailableCommandsChanged(Player.Commands commands) {
        Player$EventListener$$CC.i();
    }

    public void onCues(List list) {
        Player$Listener$$CC.a();
    }

    public void onDeviceInfoChanged(DeviceInfo deviceInfo) {
        DeviceListener$$CC.a();
    }

    public void onDeviceVolumeChanged(int i, boolean z) {
        DeviceListener$$CC.b();
    }

    public void onEvents(Player player, Player.Events events) {
        Player$EventListener$$CC.v();
    }

    public final void onIsLoadingChanged(boolean z) {
        AnalyticsListener.EventTime k = k();
        a(k, 4, (ListenerSet.Event) new AnalyticsCollector$$Lambda$39(k, z));
    }

    public void onIsPlayingChanged(boolean z) {
        AnalyticsListener.EventTime k = k();
        a(k, 8, (ListenerSet.Event) new AnalyticsCollector$$Lambda$44(k, z));
    }

    public void onLoadingChanged(boolean z) {
        Player$EventListener$$CC.h();
    }

    public final void onMediaItemTransition(MediaItem mediaItem, int i) {
        AnalyticsListener.EventTime k = k();
        a(k, 1, (ListenerSet.Event) new AnalyticsCollector$$Lambda$36(k, i));
    }

    public void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
        AnalyticsListener.EventTime k = k();
        a(k, 15, (ListenerSet.Event) new AnalyticsCollector$$Lambda$50(k, mediaMetadata));
    }

    public final void onMetadata(Metadata metadata) {
        AnalyticsListener.EventTime k = k();
        a(k, (int) PointerIconCompat.TYPE_CROSSHAIR, (ListenerSet.Event) new AnalyticsCollector$$Lambda$4(k, metadata));
    }

    public final void onPlayWhenReadyChanged(boolean z, int i) {
        AnalyticsListener.EventTime k = k();
        a(k, 6, (ListenerSet.Event) new AnalyticsCollector$$Lambda$42(k, z, i));
    }

    public final void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        AnalyticsListener.EventTime k = k();
        a(k, 13, (ListenerSet.Event) new AnalyticsCollector$$Lambda$49(k, playbackParameters));
    }

    public final void onPlaybackStateChanged(int i) {
        AnalyticsListener.EventTime k = k();
        a(k, 5, (ListenerSet.Event) new AnalyticsCollector$$Lambda$41(k, i));
    }

    public final void onPlaybackSuppressionReasonChanged(int i) {
        AnalyticsListener.EventTime k = k();
        a(k, 7, (ListenerSet.Event) new AnalyticsCollector$$Lambda$43(k, i));
    }

    public final void onPlayerError(ExoPlaybackException exoPlaybackException) {
        AnalyticsListener.EventTime a2 = exoPlaybackException.b != null ? a(new MediaSource.MediaPeriodId(exoPlaybackException.b)) : k();
        a(a2, 11, (ListenerSet.Event) new AnalyticsCollector$$Lambda$47(a2, exoPlaybackException));
    }

    public final void onPlayerStateChanged(boolean z, int i) {
        AnalyticsListener.EventTime k = k();
        a(k, -1, (ListenerSet.Event) new AnalyticsCollector$$Lambda$40(k, z, i));
    }

    public void onPositionDiscontinuity(int i) {
        Player$EventListener$$CC.r();
    }

    public final void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
        if (i == 1) {
            this.h = false;
        }
        MediaPeriodQueueTracker mediaPeriodQueueTracker = this.a;
        mediaPeriodQueueTracker.c = MediaPeriodQueueTracker.a((Player) Assertions.b((Object) this.d), mediaPeriodQueueTracker.b, mediaPeriodQueueTracker.d, mediaPeriodQueueTracker.a);
        AnalyticsListener.EventTime k = k();
        a(k, 12, (ListenerSet.Event) new AnalyticsCollector$$Lambda$48(k, i, positionInfo, positionInfo2));
    }

    public void onRenderedFirstFrame() {
        VideoListener$$CC.d();
    }

    public final void onRepeatModeChanged(int i) {
        AnalyticsListener.EventTime k = k();
        a(k, 9, (ListenerSet.Event) new AnalyticsCollector$$Lambda$45(k, i));
    }

    public final void onSeekProcessed() {
        AnalyticsListener.EventTime k = k();
        a(k, -1, (ListenerSet.Event) new AnalyticsCollector$$Lambda$51(k));
    }

    public final void onShuffleModeEnabledChanged(boolean z) {
        AnalyticsListener.EventTime k = k();
        a(k, 10, (ListenerSet.Event) new AnalyticsCollector$$Lambda$46(k, z));
    }

    public final void onSkipSilenceEnabledChanged(boolean z) {
        AnalyticsListener.EventTime m = m();
        a(m, (int) PointerIconCompat.TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW, (ListenerSet.Event) new AnalyticsCollector$$Lambda$12(m, z));
    }

    public final void onStaticMetadataChanged(List list) {
        AnalyticsListener.EventTime k = k();
        a(k, 3, (ListenerSet.Event) new AnalyticsCollector$$Lambda$38(k, list));
    }

    public void onSurfaceSizeChanged(int i, int i2) {
        AnalyticsListener.EventTime m = m();
        a(m, 1029, (ListenerSet.Event) new AnalyticsCollector$$Lambda$28(m, i, i2));
    }

    public final void onTimelineChanged(Timeline timeline, int i) {
        MediaPeriodQueueTracker mediaPeriodQueueTracker = this.a;
        Player player = (Player) Assertions.b((Object) this.d);
        mediaPeriodQueueTracker.c = MediaPeriodQueueTracker.a(player, mediaPeriodQueueTracker.b, mediaPeriodQueueTracker.d, mediaPeriodQueueTracker.a);
        mediaPeriodQueueTracker.a(player.E());
        AnalyticsListener.EventTime k = k();
        a(k, 0, (ListenerSet.Event) new AnalyticsCollector$$Lambda$35(k, i));
    }

    public void onTimelineChanged(Timeline timeline, Object obj, int i) {
        Player$EventListener$$CC.b();
    }

    public final void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
        AnalyticsListener.EventTime k = k();
        a(k, 2, (ListenerSet.Event) new AnalyticsCollector$$Lambda$37(k, trackSelectionArray));
    }

    public void onVideoSizeChanged(int i, int i2, int i3, float f2) {
        VideoListener$$CC.b();
    }

    public final void onVideoSizeChanged(VideoSize videoSize) {
        AnalyticsListener.EventTime m = m();
        a(m, 1028, (ListenerSet.Event) new AnalyticsCollector$$Lambda$24(m, videoSize));
    }
}
