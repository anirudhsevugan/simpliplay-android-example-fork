package com.google.android.exoplayer2;

import android.os.Handler;
import com.google.android.exoplayer2.analytics.AnalyticsCollector;
import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.drm.DrmSessionEventListener$$CC;
import com.google.android.exoplayer2.source.LoadEventInfo;
import com.google.android.exoplayer2.source.MaskingMediaPeriod;
import com.google.android.exoplayer2.source.MaskingMediaSource;
import com.google.android.exoplayer2.source.MediaLoadData;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.ShuffleOrder;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class MediaSourceList {
    final List a = new ArrayList();
    final IdentityHashMap b = new IdentityHashMap();
    final Map c = new HashMap();
    final MediaSourceListInfoRefreshListener d;
    final HashMap e;
    final Set f;
    ShuffleOrder g = new ShuffleOrder.DefaultShuffleOrder();
    boolean h;
    TransferListener i;
    /* access modifiers changed from: private */
    public final MediaSourceEventListener.EventDispatcher j;
    /* access modifiers changed from: private */
    public final DrmSessionEventListener.EventDispatcher k;

    final class ForwardingEventListener implements DrmSessionEventListener, MediaSourceEventListener {
        private final MediaSourceHolder a;
        private MediaSourceEventListener.EventDispatcher b;
        private DrmSessionEventListener.EventDispatcher c;

        public ForwardingEventListener(MediaSourceHolder mediaSourceHolder) {
            this.b = MediaSourceList.this.j;
            this.c = MediaSourceList.this.k;
            this.a = mediaSourceHolder;
        }

        private boolean d(int i, MediaSource.MediaPeriodId mediaPeriodId) {
            MediaSource.MediaPeriodId mediaPeriodId2;
            if (mediaPeriodId != null) {
                mediaPeriodId2 = MediaSourceList.a(this.a, mediaPeriodId);
                if (mediaPeriodId2 == null) {
                    return false;
                }
            } else {
                mediaPeriodId2 = null;
            }
            int a2 = MediaSourceList.a(this.a, i);
            if (this.b.a != a2 || !Util.a((Object) this.b.b, (Object) mediaPeriodId2)) {
                this.b = MediaSourceList.this.j.a(a2, mediaPeriodId2, 0);
            }
            if (this.c.a == a2 && Util.a((Object) this.c.b, (Object) mediaPeriodId2)) {
                return true;
            }
            this.c = MediaSourceList.this.k.a(a2, mediaPeriodId2);
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
                this.b.a(loadEventInfo, mediaLoadData);
            }
        }

        public final void a(int i, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
            if (d(i, mediaPeriodId)) {
                this.b.a(loadEventInfo, mediaLoadData, iOException, z);
            }
        }

        public final void a(int i, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
            if (d(i, mediaPeriodId)) {
                this.b.a(mediaLoadData);
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
                this.b.b(loadEventInfo, mediaLoadData);
            }
        }

        public final void b(int i, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
            if (d(i, mediaPeriodId)) {
                this.b.b(mediaLoadData);
            }
        }

        public final void c(int i, MediaSource.MediaPeriodId mediaPeriodId) {
            if (d(i, mediaPeriodId)) {
                this.c.c();
            }
        }

        public final void c(int i, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            if (d(i, mediaPeriodId)) {
                this.b.c(loadEventInfo, mediaLoadData);
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

    final class MediaSourceHolder implements MediaSourceInfoHolder {
        public final MaskingMediaSource a;
        public final Object b = new Object();
        public final List c = new ArrayList();
        public int d;
        public boolean e;

        public MediaSourceHolder(MediaSource mediaSource, boolean z) {
            this.a = new MaskingMediaSource(mediaSource, z);
        }

        public final Object a() {
            return this.b;
        }

        public final void a(int i) {
            this.d = i;
            this.e = false;
            this.c.clear();
        }

        public final Timeline b() {
            return this.a.a;
        }
    }

    public interface MediaSourceListInfoRefreshListener {
        void c();
    }

    public MediaSourceList(MediaSourceListInfoRefreshListener mediaSourceListInfoRefreshListener, AnalyticsCollector analyticsCollector, Handler handler) {
        this.d = mediaSourceListInfoRefreshListener;
        MediaSourceEventListener.EventDispatcher eventDispatcher = new MediaSourceEventListener.EventDispatcher();
        this.j = eventDispatcher;
        DrmSessionEventListener.EventDispatcher eventDispatcher2 = new DrmSessionEventListener.EventDispatcher();
        this.k = eventDispatcher2;
        this.e = new HashMap();
        this.f = new HashSet();
        if (analyticsCollector != null) {
            eventDispatcher.a(handler, (MediaSourceEventListener) analyticsCollector);
            eventDispatcher2.a(handler, (DrmSessionEventListener) analyticsCollector);
        }
    }

    static /* synthetic */ int a(MediaSourceHolder mediaSourceHolder, int i2) {
        return i2 + mediaSourceHolder.d;
    }

    static /* synthetic */ MediaSource.MediaPeriodId a(MediaSourceHolder mediaSourceHolder, MediaSource.MediaPeriodId mediaPeriodId) {
        for (int i2 = 0; i2 < mediaSourceHolder.c.size(); i2++) {
            if (((MediaSource.MediaPeriodId) mediaSourceHolder.c.get(i2)).d == mediaPeriodId.d) {
                return mediaPeriodId.a(PlaylistTimeline.a(mediaSourceHolder.b, mediaPeriodId.a));
            }
        }
        return null;
    }

    static Object a(Object obj) {
        return PlaylistTimeline.a(obj);
    }

    static Object b(Object obj) {
        return PlaylistTimeline.b(obj);
    }

    private void b(int i2, int i3) {
        while (i2 < this.a.size()) {
            ((MediaSourceHolder) this.a.get(i2)).d += i3;
            i2++;
        }
    }

    private void b(MediaSourceHolder mediaSourceHolder) {
        MediaSourceAndListener mediaSourceAndListener = (MediaSourceAndListener) this.e.get(mediaSourceHolder);
        if (mediaSourceAndListener != null) {
            mediaSourceAndListener.a.b(mediaSourceAndListener.b);
        }
    }

    private void c(MediaSourceHolder mediaSourceHolder) {
        if (mediaSourceHolder.e && mediaSourceHolder.c.isEmpty()) {
            MediaSourceAndListener mediaSourceAndListener = (MediaSourceAndListener) Assertions.b((Object) (MediaSourceAndListener) this.e.remove(mediaSourceHolder));
            mediaSourceAndListener.a.c(mediaSourceAndListener.b);
            mediaSourceAndListener.a.a((MediaSourceEventListener) mediaSourceAndListener.c);
            mediaSourceAndListener.a.a((DrmSessionEventListener) mediaSourceAndListener.c);
            this.f.remove(mediaSourceHolder);
        }
    }

    public final int a() {
        return this.a.size();
    }

    public final Timeline a(int i2, int i3, int i4, ShuffleOrder shuffleOrder) {
        Assertions.a(i2 >= 0 && i2 <= i3 && i3 <= a() && i4 >= 0);
        this.g = shuffleOrder;
        if (i2 == i3 || i2 == i4) {
            return c();
        }
        int min = Math.min(i2, i4);
        int max = Math.max(((i3 - i2) + i4) - 1, i3 - 1);
        int i5 = ((MediaSourceHolder) this.a.get(min)).d;
        Util.a(this.a, i2, i3, i4);
        while (min <= max) {
            MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) this.a.get(min);
            mediaSourceHolder.d = i5;
            i5 += mediaSourceHolder.a.a.a();
            min++;
        }
        return c();
    }

    public final Timeline a(int i2, List list, ShuffleOrder shuffleOrder) {
        int i3;
        if (!list.isEmpty()) {
            this.g = shuffleOrder;
            for (int i4 = i2; i4 < list.size() + i2; i4++) {
                MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) list.get(i4 - i2);
                if (i4 > 0) {
                    MediaSourceHolder mediaSourceHolder2 = (MediaSourceHolder) this.a.get(i4 - 1);
                    i3 = mediaSourceHolder2.d + mediaSourceHolder2.a.a.a();
                } else {
                    i3 = 0;
                }
                mediaSourceHolder.a(i3);
                b(i4, mediaSourceHolder.a.a.a());
                this.a.add(i4, mediaSourceHolder);
                this.c.put(mediaSourceHolder.b, mediaSourceHolder);
                if (this.h) {
                    a(mediaSourceHolder);
                    if (this.b.isEmpty()) {
                        this.f.add(mediaSourceHolder);
                    } else {
                        b(mediaSourceHolder);
                    }
                }
            }
        }
        return c();
    }

    /* access modifiers changed from: package-private */
    public final void a(int i2, int i3) {
        while (true) {
            i3--;
            if (i3 >= i2) {
                MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) this.a.remove(i3);
                this.c.remove(mediaSourceHolder.b);
                b(i3, -mediaSourceHolder.a.a.a());
                mediaSourceHolder.e = true;
                if (this.h) {
                    c(mediaSourceHolder);
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(MediaSourceHolder mediaSourceHolder) {
        MaskingMediaSource maskingMediaSource = mediaSourceHolder.a;
        MediaSourceList$$Lambda$0 mediaSourceList$$Lambda$0 = new MediaSourceList$$Lambda$0(this);
        ForwardingEventListener forwardingEventListener = new ForwardingEventListener(mediaSourceHolder);
        this.e.put(mediaSourceHolder, new MediaSourceAndListener(maskingMediaSource, mediaSourceList$$Lambda$0, forwardingEventListener));
        maskingMediaSource.a(Util.b(), (MediaSourceEventListener) forwardingEventListener);
        maskingMediaSource.a(Util.b(), (DrmSessionEventListener) forwardingEventListener);
        maskingMediaSource.a((MediaSource.MediaSourceCaller) mediaSourceList$$Lambda$0, this.i);
    }

    public final void a(MediaPeriod mediaPeriod) {
        MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) Assertions.b((Object) (MediaSourceHolder) this.b.remove(mediaPeriod));
        mediaSourceHolder.a.a(mediaPeriod);
        mediaSourceHolder.c.remove(((MaskingMediaPeriod) mediaPeriod).a);
        if (!this.b.isEmpty()) {
            d();
        }
        c(mediaSourceHolder);
    }

    public final void b() {
        for (MediaSourceAndListener mediaSourceAndListener : this.e.values()) {
            try {
                mediaSourceAndListener.a.c(mediaSourceAndListener.b);
            } catch (RuntimeException e2) {
                Log.b("MediaSourceList", "Failed to release child source.", e2);
            }
            mediaSourceAndListener.a.a((MediaSourceEventListener) mediaSourceAndListener.c);
            mediaSourceAndListener.a.a((DrmSessionEventListener) mediaSourceAndListener.c);
        }
        this.e.clear();
        this.f.clear();
        this.h = false;
    }

    public final Timeline c() {
        if (this.a.isEmpty()) {
            return Timeline.b;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.a.size(); i3++) {
            MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) this.a.get(i3);
            mediaSourceHolder.d = i2;
            i2 += mediaSourceHolder.a.a.a();
        }
        return new PlaylistTimeline(this.a, this.g);
    }

    /* access modifiers changed from: package-private */
    public final void d() {
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) it.next();
            if (mediaSourceHolder.c.isEmpty()) {
                b(mediaSourceHolder);
                it.remove();
            }
        }
    }
}
