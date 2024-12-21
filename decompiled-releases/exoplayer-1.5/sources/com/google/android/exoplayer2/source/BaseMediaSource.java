package com.google.android.exoplayer2.source;

import android.os.Handler;
import android.os.Looper;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public abstract class BaseMediaSource implements MediaSource {
    private final ArrayList a = new ArrayList(1);
    private final HashSet b = new HashSet(1);
    private final MediaSourceEventListener.EventDispatcher c = new MediaSourceEventListener.EventDispatcher();
    private final DrmSessionEventListener.EventDispatcher d = new DrmSessionEventListener.EventDispatcher();
    private Looper e;
    private Timeline f;

    /* access modifiers changed from: protected */
    public final MediaSourceEventListener.EventDispatcher a(int i, MediaSource.MediaPeriodId mediaPeriodId) {
        return this.c.a(i, mediaPeriodId, 0);
    }

    public final MediaSourceEventListener.EventDispatcher a(MediaSource.MediaPeriodId mediaPeriodId) {
        return this.c.a(0, mediaPeriodId, 0);
    }

    /* access modifiers changed from: protected */
    public final MediaSourceEventListener.EventDispatcher a(MediaSource.MediaPeriodId mediaPeriodId, long j) {
        Assertions.b((Object) mediaPeriodId);
        return this.c.a(0, mediaPeriodId, j);
    }

    /* access modifiers changed from: protected */
    public void a() {
    }

    public final void a(Handler handler, DrmSessionEventListener drmSessionEventListener) {
        Assertions.b((Object) handler);
        Assertions.b((Object) drmSessionEventListener);
        this.d.a(handler, drmSessionEventListener);
    }

    public final void a(Handler handler, MediaSourceEventListener mediaSourceEventListener) {
        Assertions.b((Object) handler);
        Assertions.b((Object) mediaSourceEventListener);
        this.c.a(handler, mediaSourceEventListener);
    }

    /* access modifiers changed from: protected */
    public final void a(Timeline timeline) {
        this.f = timeline;
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((MediaSource.MediaSourceCaller) it.next()).a(this, timeline);
        }
    }

    public final void a(DrmSessionEventListener drmSessionEventListener) {
        DrmSessionEventListener.EventDispatcher eventDispatcher = this.d;
        Iterator it = eventDispatcher.c.iterator();
        while (it.hasNext()) {
            DrmSessionEventListener.EventDispatcher.ListenerAndHandler listenerAndHandler = (DrmSessionEventListener.EventDispatcher.ListenerAndHandler) it.next();
            if (listenerAndHandler.b == drmSessionEventListener) {
                eventDispatcher.c.remove(listenerAndHandler);
            }
        }
    }

    public final void a(MediaSource.MediaSourceCaller mediaSourceCaller) {
        Assertions.b((Object) this.e);
        boolean isEmpty = this.b.isEmpty();
        this.b.add(mediaSourceCaller);
        if (isEmpty) {
            a();
        }
    }

    public final void a(MediaSource.MediaSourceCaller mediaSourceCaller, TransferListener transferListener) {
        Looper myLooper = Looper.myLooper();
        Looper looper = this.e;
        Assertions.a(looper == null || looper == myLooper);
        Timeline timeline = this.f;
        this.a.add(mediaSourceCaller);
        if (this.e == null) {
            this.e = myLooper;
            this.b.add(mediaSourceCaller);
            a(transferListener);
        } else if (timeline != null) {
            a(mediaSourceCaller);
            mediaSourceCaller.a(this, timeline);
        }
    }

    public final void a(MediaSourceEventListener mediaSourceEventListener) {
        MediaSourceEventListener.EventDispatcher eventDispatcher = this.c;
        Iterator it = eventDispatcher.c.iterator();
        while (it.hasNext()) {
            MediaSourceEventListener.EventDispatcher.ListenerAndHandler listenerAndHandler = (MediaSourceEventListener.EventDispatcher.ListenerAndHandler) it.next();
            if (listenerAndHandler.b == mediaSourceEventListener) {
                eventDispatcher.c.remove(listenerAndHandler);
            }
        }
    }

    /* access modifiers changed from: protected */
    public abstract void a(TransferListener transferListener);

    /* access modifiers changed from: protected */
    public final DrmSessionEventListener.EventDispatcher b(int i, MediaSource.MediaPeriodId mediaPeriodId) {
        return this.d.a(i, mediaPeriodId);
    }

    /* access modifiers changed from: protected */
    public final DrmSessionEventListener.EventDispatcher b(MediaSource.MediaPeriodId mediaPeriodId) {
        return this.d.a(0, mediaPeriodId);
    }

    /* access modifiers changed from: protected */
    public void b() {
    }

    public final void b(MediaSource.MediaSourceCaller mediaSourceCaller) {
        boolean z = !this.b.isEmpty();
        this.b.remove(mediaSourceCaller);
        if (z && this.b.isEmpty()) {
            b();
        }
    }

    /* access modifiers changed from: protected */
    public abstract void c();

    public final void c(MediaSource.MediaSourceCaller mediaSourceCaller) {
        this.a.remove(mediaSourceCaller);
        if (this.a.isEmpty()) {
            this.e = null;
            this.f = null;
            this.b.clear();
            c();
            return;
        }
        b(mediaSourceCaller);
    }

    /* access modifiers changed from: protected */
    public final boolean d() {
        return !this.b.isEmpty();
    }

    public final Timeline e() {
        return MediaSource$$CC.a();
    }

    public final boolean f() {
        return MediaSource$$CC.b();
    }
}
