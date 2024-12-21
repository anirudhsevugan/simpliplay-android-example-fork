package com.google.android.exoplayer2.source;

import android.os.Handler;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.TransferListener;

public interface MediaSource {

    public final class MediaPeriodId extends MediaPeriodId {
        public MediaPeriodId(MediaPeriodId mediaPeriodId) {
            super(mediaPeriodId);
        }

        public MediaPeriodId(Object obj) {
            super(obj);
        }

        public MediaPeriodId(Object obj, int i, int i2, long j) {
            super(obj, i, i2, j);
        }

        public MediaPeriodId(Object obj, long j, int i) {
            super(obj, j, i);
        }

        /* renamed from: b */
        public final MediaPeriodId a(Object obj) {
            return new MediaPeriodId(super.a(obj));
        }
    }

    public interface MediaSourceCaller {
        void a(MediaSource mediaSource, Timeline timeline);
    }

    MediaPeriod a(MediaPeriodId mediaPeriodId, Allocator allocator, long j);

    void a(Handler handler, DrmSessionEventListener drmSessionEventListener);

    void a(Handler handler, MediaSourceEventListener mediaSourceEventListener);

    void a(DrmSessionEventListener drmSessionEventListener);

    void a(MediaPeriod mediaPeriod);

    void a(MediaSourceCaller mediaSourceCaller);

    void a(MediaSourceCaller mediaSourceCaller, TransferListener transferListener);

    void a(MediaSourceEventListener mediaSourceEventListener);

    void b(MediaSourceCaller mediaSourceCaller);

    void c(MediaSourceCaller mediaSourceCaller);

    Timeline e();

    boolean f();

    MediaItem g();

    void h();
}
