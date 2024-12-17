package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.drm.DefaultDrmSessionManagerProvider;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.DrmSessionManagerProvider;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaExtractor;
import com.google.android.exoplayer2.source.ProgressiveMediaPeriod;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;

public final class ProgressiveMediaSource extends BaseMediaSource implements ProgressiveMediaPeriod.Listener {
    private final MediaItem a;
    private final MediaItem.PlaybackProperties b;
    private final DataSource.Factory c;
    private final ProgressiveMediaExtractor.Factory d;
    private final DrmSessionManager e;
    private final LoadErrorHandlingPolicy f;
    private final int g;
    private boolean h;
    private long i;
    private boolean j;
    private boolean k;
    private TransferListener l;

    public final class Factory implements MediaSourceFactory {
        private final DataSource.Factory a;
        private ProgressiveMediaExtractor.Factory b;
        private DrmSessionManagerProvider c;
        private LoadErrorHandlingPolicy d;
        private int e;

        public Factory(DataSource.Factory factory, ExtractorsFactory extractorsFactory) {
            this(factory, (ProgressiveMediaExtractor.Factory) new ProgressiveMediaSource$Factory$$Lambda$0(extractorsFactory));
        }

        private Factory(DataSource.Factory factory, ProgressiveMediaExtractor.Factory factory2) {
            this.a = factory;
            this.b = factory2;
            this.c = new DefaultDrmSessionManagerProvider();
            this.d = new DefaultLoadErrorHandlingPolicy();
            this.e = 1048576;
        }

        static final /* synthetic */ ProgressiveMediaExtractor a(ExtractorsFactory extractorsFactory) {
            return new BundledExtractorsAdapter(extractorsFactory);
        }

        public final /* synthetic */ MediaSource a(MediaItem mediaItem) {
            Assertions.b((Object) mediaItem.b);
            return new ProgressiveMediaSource(mediaItem, this.a, this.b, this.c.a(mediaItem), this.d, this.e, (byte) 0);
        }
    }

    private ProgressiveMediaSource(MediaItem mediaItem, DataSource.Factory factory, ProgressiveMediaExtractor.Factory factory2, DrmSessionManager drmSessionManager, LoadErrorHandlingPolicy loadErrorHandlingPolicy, int i2) {
        this.b = (MediaItem.PlaybackProperties) Assertions.b((Object) mediaItem.b);
        this.a = mediaItem;
        this.c = factory;
        this.d = factory2;
        this.e = drmSessionManager;
        this.f = loadErrorHandlingPolicy;
        this.g = i2;
        this.h = true;
        this.i = -9223372036854775807L;
    }

    /* synthetic */ ProgressiveMediaSource(MediaItem mediaItem, DataSource.Factory factory, ProgressiveMediaExtractor.Factory factory2, DrmSessionManager drmSessionManager, LoadErrorHandlingPolicy loadErrorHandlingPolicy, int i2, byte b2) {
        this(mediaItem, factory, factory2, drmSessionManager, loadErrorHandlingPolicy, i2);
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [com.google.android.exoplayer2.source.ProgressiveMediaSource$1] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void i() {
        /*
            r7 = this;
            com.google.android.exoplayer2.source.SinglePeriodTimeline r6 = new com.google.android.exoplayer2.source.SinglePeriodTimeline
            long r1 = r7.i
            boolean r3 = r7.j
            boolean r4 = r7.k
            com.google.android.exoplayer2.MediaItem r5 = r7.a
            r0 = r6
            r0.<init>(r1, r3, r4, r5)
            boolean r0 = r7.h
            if (r0 == 0) goto L_0x0018
            com.google.android.exoplayer2.source.ProgressiveMediaSource$1 r0 = new com.google.android.exoplayer2.source.ProgressiveMediaSource$1
            r0.<init>(r6)
            r6 = r0
        L_0x0018:
            r7.a((com.google.android.exoplayer2.Timeline) r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.ProgressiveMediaSource.i():void");
    }

    public final MediaPeriod a(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j2) {
        DataSource a2 = this.c.a();
        TransferListener transferListener = this.l;
        if (transferListener != null) {
            a2.a(transferListener);
        }
        return new ProgressiveMediaPeriod(this.b.a, a2, this.d.a(), this.e, b(mediaPeriodId), this.f, a(mediaPeriodId), this, allocator, this.b.f, this.g);
    }

    public final void a(long j2, boolean z, boolean z2) {
        if (j2 == -9223372036854775807L) {
            j2 = this.i;
        }
        if (this.h || this.i != j2 || this.j != z || this.k != z2) {
            this.i = j2;
            this.j = z;
            this.k = z2;
            this.h = false;
            i();
        }
    }

    public final void a(MediaPeriod mediaPeriod) {
        ProgressiveMediaPeriod progressiveMediaPeriod = (ProgressiveMediaPeriod) mediaPeriod;
        if (progressiveMediaPeriod.h) {
            for (SampleQueue c2 : progressiveMediaPeriod.g) {
                c2.c();
            }
        }
        progressiveMediaPeriod.c.a((Loader.ReleaseCallback) progressiveMediaPeriod);
        progressiveMediaPeriod.d.removeCallbacksAndMessages((Object) null);
        progressiveMediaPeriod.e = null;
        progressiveMediaPeriod.o = true;
    }

    /* access modifiers changed from: protected */
    public final void a(TransferListener transferListener) {
        this.l = transferListener;
        this.e.a();
        i();
    }

    /* access modifiers changed from: protected */
    public final void c() {
        this.e.b();
    }

    public final MediaItem g() {
        return this.a;
    }

    public final void h() {
    }
}
