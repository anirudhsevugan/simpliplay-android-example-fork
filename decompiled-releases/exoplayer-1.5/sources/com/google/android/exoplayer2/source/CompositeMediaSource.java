package com.google.android.exoplayer2.source;

import android.os.Handler;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.drm.DrmSessionEventListener$$CC;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.HashMap;

public abstract class CompositeMediaSource extends BaseMediaSource {
    private final HashMap a = new HashMap();
    private Handler b;
    private TransferListener c;

    final class ForwardingEventListener implements DrmSessionEventListener, MediaSourceEventListener {
        private final Object a;
        private MediaSourceEventListener.EventDispatcher b;
        private DrmSessionEventListener.EventDispatcher c;

        public ForwardingEventListener(Object obj) {
            this.b = CompositeMediaSource.this.a((MediaSource.MediaPeriodId) null);
            this.c = CompositeMediaSource.this.b((MediaSource.MediaPeriodId) null);
            this.a = obj;
        }

        private static MediaLoadData a(MediaLoadData mediaLoadData) {
            long a2 = CompositeMediaSource.a(mediaLoadData.f);
            long a3 = CompositeMediaSource.a(mediaLoadData.g);
            return (a2 == mediaLoadData.f && a3 == mediaLoadData.g) ? mediaLoadData : new MediaLoadData(mediaLoadData.a, mediaLoadData.b, mediaLoadData.c, mediaLoadData.d, mediaLoadData.e, a2, a3);
        }

        private boolean d(int i, MediaSource.MediaPeriodId mediaPeriodId) {
            MediaSource.MediaPeriodId mediaPeriodId2;
            if (mediaPeriodId != null) {
                mediaPeriodId2 = CompositeMediaSource.this.a(this.a, mediaPeriodId);
                if (mediaPeriodId2 == null) {
                    return false;
                }
            } else {
                mediaPeriodId2 = null;
            }
            int a2 = CompositeMediaSource.a(i);
            if (this.b.a != a2 || !Util.a((Object) this.b.b, (Object) mediaPeriodId2)) {
                this.b = CompositeMediaSource.this.a(a2, mediaPeriodId2);
            }
            if (this.c.a == a2 && Util.a((Object) this.c.b, (Object) mediaPeriodId2)) {
                return true;
            }
            this.c = CompositeMediaSource.this.b(a2, mediaPeriodId2);
            return true;
        }

        public final void a() {
            DrmSessionEventListener$$CC.a();
        }

        public final void a(int i, MediaSource.MediaPeriodId mediaPeriodId) {
            if (d(i, mediaPeriodId)) {
                this.c.a();
            }
        }

        public final void a(int i, MediaSource.MediaPeriodId mediaPeriodId, int i2) {
            if (d(i, mediaPeriodId)) {
                this.c.a(i2);
            }
        }

        public final void a(int i, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            if (d(i, mediaPeriodId)) {
                this.b.a(loadEventInfo, a(mediaLoadData));
            }
        }

        public final void a(int i, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
            if (d(i, mediaPeriodId)) {
                this.b.a(loadEventInfo, a(mediaLoadData), iOException, z);
            }
        }

        public final void a(int i, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
            if (d(i, mediaPeriodId)) {
                this.b.a(a(mediaLoadData));
            }
        }

        public final void a(int i, MediaSource.MediaPeriodId mediaPeriodId, Exception exc) {
            if (d(i, mediaPeriodId)) {
                this.c.a(exc);
            }
        }

        public final void b(int i, MediaSource.MediaPeriodId mediaPeriodId) {
            if (d(i, mediaPeriodId)) {
                this.c.b();
            }
        }

        public final void b(int i, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            if (d(i, mediaPeriodId)) {
                this.b.b(loadEventInfo, a(mediaLoadData));
            }
        }

        public final void b(int i, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
            if (d(i, mediaPeriodId)) {
                this.b.b(a(mediaLoadData));
            }
        }

        public final void c(int i, MediaSource.MediaPeriodId mediaPeriodId) {
            if (d(i, mediaPeriodId)) {
                this.c.c();
            }
        }

        public final void c(int i, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            if (d(i, mediaPeriodId)) {
                this.b.c(loadEventInfo, a(mediaLoadData));
            }
        }
    }

    final class MediaSourceAndListener {
        public final MediaSource a;
        public final MediaSource.MediaSourceCaller b;
        public final ForwardingEventListener c;

        public MediaSourceAndListener(MediaSource mediaSource, MediaSource.MediaSourceCaller mediaSourceCaller, ForwardingEventListener forwardingEventListener) {
            this.a = mediaSource;
            this.b = mediaSourceCaller;
            this.c = forwardingEventListener;
        }
    }

    protected static int a(int i) {
        return i;
    }

    protected static long a(long j) {
        return j;
    }

    /* access modifiers changed from: protected */
    public MediaSource.MediaPeriodId a(Object obj, MediaSource.MediaPeriodId mediaPeriodId) {
        return mediaPeriodId;
    }

    /* access modifiers changed from: protected */
    public final void a() {
        for (MediaSourceAndListener mediaSourceAndListener : this.a.values()) {
            mediaSourceAndListener.a.a(mediaSourceAndListener.b);
        }
    }

    public void a(TransferListener transferListener) {
        this.c = transferListener;
        this.b = Util.a();
    }

    public final void a(Object obj, MediaSource mediaSource) {
        Assertions.a(!this.a.containsKey(obj));
        CompositeMediaSource$$Lambda$0 compositeMediaSource$$Lambda$0 = new CompositeMediaSource$$Lambda$0(this, obj);
        ForwardingEventListener forwardingEventListener = new ForwardingEventListener(obj);
        this.a.put(obj, new MediaSourceAndListener(mediaSource, compositeMediaSource$$Lambda$0, forwardingEventListener));
        mediaSource.a((Handler) Assertions.b((Object) this.b), (MediaSourceEventListener) forwardingEventListener);
        mediaSource.a((Handler) Assertions.b((Object) this.b), (DrmSessionEventListener) forwardingEventListener);
        mediaSource.a((MediaSource.MediaSourceCaller) compositeMediaSource$$Lambda$0, this.c);
        if (!d()) {
            mediaSource.b(compositeMediaSource$$Lambda$0);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void b(Object obj, MediaSource mediaSource, Timeline timeline);

    /* access modifiers changed from: protected */
    public final void b() {
        for (MediaSourceAndListener mediaSourceAndListener : this.a.values()) {
            mediaSourceAndListener.a.b(mediaSourceAndListener.b);
        }
    }

    public void c() {
        for (MediaSourceAndListener mediaSourceAndListener : this.a.values()) {
            mediaSourceAndListener.a.c(mediaSourceAndListener.b);
            mediaSourceAndListener.a.a((MediaSourceEventListener) mediaSourceAndListener.c);
            mediaSourceAndListener.a.a((DrmSessionEventListener) mediaSourceAndListener.c);
        }
        this.a.clear();
    }

    public void h() {
        for (MediaSourceAndListener mediaSourceAndListener : this.a.values()) {
            mediaSourceAndListener.a.h();
        }
    }
}
