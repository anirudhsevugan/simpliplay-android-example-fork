package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ads.AdPlaybackState;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

public final class MaskingMediaSource extends CompositeMediaSource {
    public MaskingTimeline a;
    private final MediaSource b;
    private final boolean c;
    private final Timeline.Window d;
    private final Timeline.Period e;
    private MaskingMediaPeriod f;
    private boolean g;
    private boolean h;
    private boolean i;

    final class MaskingTimeline extends ForwardingTimeline {
        public static final Object c = new Object();
        private final Object d;
        /* access modifiers changed from: private */
        public final Object e;

        private MaskingTimeline(Timeline timeline, Object obj, Object obj2) {
            super(timeline);
            this.d = obj;
            this.e = obj2;
        }

        public static MaskingTimeline a(MediaItem mediaItem) {
            return new MaskingTimeline(new PlaceholderTimeline(mediaItem), Timeline.Window.a, c);
        }

        public static MaskingTimeline a(Timeline timeline, Object obj, Object obj2) {
            return new MaskingTimeline(timeline, obj, obj2);
        }

        public final Timeline.Period a(int i, Timeline.Period period, boolean z) {
            this.a.a(i, period, z);
            if (Util.a(period.b, this.e) && z) {
                period.b = c;
            }
            return period;
        }

        public final Timeline.Window a(int i, Timeline.Window window, long j) {
            this.a.a(i, window, j);
            if (Util.a(window.b, this.d)) {
                window.b = Timeline.Window.a;
            }
            return window;
        }

        public final MaskingTimeline a(Timeline timeline) {
            return new MaskingTimeline(timeline, this.d, this.e);
        }

        public final Object a(int i) {
            Object a = this.a.a(i);
            return Util.a(a, this.e) ? c : a;
        }

        public final int c(Object obj) {
            Object obj2;
            Timeline timeline = this.a;
            if (c.equals(obj) && (obj2 = this.e) != null) {
                obj = obj2;
            }
            return timeline.c(obj);
        }
    }

    public final class PlaceholderTimeline extends Timeline {
        private final MediaItem a;

        public PlaceholderTimeline(MediaItem mediaItem) {
            this.a = mediaItem;
        }

        public final int a() {
            return 1;
        }

        public final Timeline.Period a(int i, Timeline.Period period, boolean z) {
            Object obj = null;
            Integer num = z ? 0 : null;
            if (z) {
                obj = MaskingTimeline.c;
            }
            period.a(num, obj, -9223372036854775807L, 0, AdPlaybackState.a, true);
            return period;
        }

        public final Timeline.Window a(int i, Timeline.Window window, long j) {
            Timeline.Window window2 = window;
            window.a(Timeline.Window.a, this.a, (Object) null, -9223372036854775807L, -9223372036854775807L, -9223372036854775807L, false, true, (MediaItem.LiveConfiguration) null, 0, -9223372036854775807L, 0, 0);
            Timeline.Window window3 = window;
            window3.k = true;
            return window3;
        }

        public final Object a(int i) {
            return MaskingTimeline.c;
        }

        public final int b() {
            return 1;
        }

        public final int c(Object obj) {
            return obj == MaskingTimeline.c ? 0 : -1;
        }
    }

    public MaskingMediaSource(MediaSource mediaSource, boolean z) {
        this.b = mediaSource;
        this.c = z && mediaSource.f();
        this.d = new Timeline.Window();
        this.e = new Timeline.Period();
        Timeline e2 = mediaSource.e();
        if (e2 != null) {
            this.a = MaskingTimeline.a(e2, (Object) null, (Object) null);
            this.i = true;
            return;
        }
        this.a = MaskingTimeline.a(mediaSource.g());
    }

    private Object a(Object obj) {
        return (this.a.e == null || !obj.equals(MaskingTimeline.c)) ? obj : this.a.e;
    }

    private void b(long j) {
        MaskingMediaPeriod maskingMediaPeriod = this.f;
        int c2 = this.a.c(maskingMediaPeriod.a.a);
        if (c2 != -1) {
            long j2 = this.a.a(c2, this.e, false).d;
            if (j2 != -9223372036854775807L && j >= j2) {
                j = Math.max(0, j2 - 1);
            }
            maskingMediaPeriod.e = j;
        }
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ MediaSource.MediaPeriodId a(Object obj, MediaSource.MediaPeriodId mediaPeriodId) {
        Object obj2 = mediaPeriodId.a;
        if (this.a.e != null && this.a.e.equals(obj2)) {
            obj2 = MaskingTimeline.c;
        }
        return mediaPeriodId.a(obj2);
    }

    public final void a(MediaPeriod mediaPeriod) {
        MaskingMediaPeriod maskingMediaPeriod = (MaskingMediaPeriod) mediaPeriod;
        if (maskingMediaPeriod.d != null) {
            ((MediaSource) Assertions.b((Object) maskingMediaPeriod.c)).a(maskingMediaPeriod.d);
        }
        if (mediaPeriod == this.f) {
            this.f = null;
        }
    }

    public final void a(TransferListener transferListener) {
        super.a(transferListener);
        if (!this.c) {
            this.g = true;
            a((Object) null, this.b);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ void a(java.lang.Object r14, com.google.android.exoplayer2.source.MediaSource r15, com.google.android.exoplayer2.Timeline r16) {
        /*
            r13 = this;
            r0 = r13
            r7 = r16
            boolean r1 = r0.h
            if (r1 == 0) goto L_0x001a
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r1 = r0.a
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r1 = r1.a((com.google.android.exoplayer2.Timeline) r7)
            r0.a = r1
            com.google.android.exoplayer2.source.MaskingMediaPeriod r1 = r0.f
            if (r1 == 0) goto L_0x00aa
            long r1 = r1.e
            r13.b(r1)
            goto L_0x00aa
        L_0x001a:
            boolean r1 = r16.c()
            if (r1 == 0) goto L_0x0037
            boolean r1 = r0.i
            if (r1 == 0) goto L_0x002b
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r1 = r0.a
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r1 = r1.a((com.google.android.exoplayer2.Timeline) r7)
            goto L_0x0033
        L_0x002b:
            java.lang.Object r1 = com.google.android.exoplayer2.Timeline.Window.a
            java.lang.Object r2 = com.google.android.exoplayer2.source.MaskingMediaSource.MaskingTimeline.c
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r1 = com.google.android.exoplayer2.source.MaskingMediaSource.MaskingTimeline.a((com.google.android.exoplayer2.Timeline) r7, (java.lang.Object) r1, (java.lang.Object) r2)
        L_0x0033:
            r0.a = r1
            goto L_0x00aa
        L_0x0037:
            com.google.android.exoplayer2.Timeline$Window r1 = r0.d
            r2 = 0
            r3 = 0
            r7.a((int) r2, (com.google.android.exoplayer2.Timeline.Window) r1, (long) r3)
            com.google.android.exoplayer2.Timeline$Window r1 = r0.d
            long r5 = r1.l
            com.google.android.exoplayer2.Timeline$Window r1 = r0.d
            java.lang.Object r8 = r1.b
            com.google.android.exoplayer2.source.MaskingMediaPeriod r1 = r0.f
            if (r1 == 0) goto L_0x006e
            long r9 = r1.b
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r1 = r0.a
            com.google.android.exoplayer2.source.MaskingMediaPeriod r11 = r0.f
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r11 = r11.a
            java.lang.Object r11 = r11.a
            com.google.android.exoplayer2.Timeline$Period r12 = r0.e
            r1.a(r11, r12)
            com.google.android.exoplayer2.Timeline$Period r1 = r0.e
            long r11 = r1.e
            long r11 = r11 + r9
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r1 = r0.a
            com.google.android.exoplayer2.Timeline$Window r9 = r0.d
            com.google.android.exoplayer2.Timeline$Window r1 = r1.a((int) r2, (com.google.android.exoplayer2.Timeline.Window) r9, (long) r3)
            long r1 = r1.l
            int r3 = (r11 > r1 ? 1 : (r11 == r1 ? 0 : -1))
            if (r3 == 0) goto L_0x006e
            r5 = r11
        L_0x006e:
            com.google.android.exoplayer2.Timeline$Window r2 = r0.d
            com.google.android.exoplayer2.Timeline$Period r3 = r0.e
            r4 = 0
            r1 = r16
            android.util.Pair r1 = r1.a(r2, r3, r4, r5)
            java.lang.Object r2 = r1.first
            java.lang.Object r1 = r1.second
            java.lang.Long r1 = (java.lang.Long) r1
            long r3 = r1.longValue()
            boolean r1 = r0.i
            if (r1 == 0) goto L_0x008e
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r1 = r0.a
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r1 = r1.a((com.google.android.exoplayer2.Timeline) r7)
            goto L_0x0092
        L_0x008e:
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r1 = com.google.android.exoplayer2.source.MaskingMediaSource.MaskingTimeline.a((com.google.android.exoplayer2.Timeline) r7, (java.lang.Object) r8, (java.lang.Object) r2)
        L_0x0092:
            r0.a = r1
            com.google.android.exoplayer2.source.MaskingMediaPeriod r1 = r0.f
            if (r1 == 0) goto L_0x00aa
            r13.b(r3)
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r2 = r1.a
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r1 = r1.a
            java.lang.Object r1 = r1.a
            java.lang.Object r1 = r13.a((java.lang.Object) r1)
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r1 = r2.a(r1)
            goto L_0x00ab
        L_0x00aa:
            r1 = 0
        L_0x00ab:
            r2 = 1
            r0.i = r2
            r0.h = r2
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r2 = r0.a
            r13.a((com.google.android.exoplayer2.Timeline) r2)
            if (r1 == 0) goto L_0x00c2
            com.google.android.exoplayer2.source.MaskingMediaPeriod r2 = r0.f
            java.lang.Object r2 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r2)
            com.google.android.exoplayer2.source.MaskingMediaPeriod r2 = (com.google.android.exoplayer2.source.MaskingMediaPeriod) r2
            r2.a((com.google.android.exoplayer2.source.MediaSource.MediaPeriodId) r1)
        L_0x00c2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.MaskingMediaSource.a(java.lang.Object, com.google.android.exoplayer2.source.MediaSource, com.google.android.exoplayer2.Timeline):void");
    }

    /* renamed from: b */
    public final MaskingMediaPeriod a(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        MaskingMediaPeriod maskingMediaPeriod = new MaskingMediaPeriod(mediaPeriodId, allocator, j);
        maskingMediaPeriod.a(this.b);
        if (this.h) {
            maskingMediaPeriod.a(mediaPeriodId.a(a(mediaPeriodId.a)));
        } else {
            this.f = maskingMediaPeriod;
            if (!this.g) {
                this.g = true;
                a((Object) null, this.b);
            }
        }
        return maskingMediaPeriod;
    }

    public final void c() {
        this.h = false;
        this.g = false;
        super.c();
    }

    public final MediaItem g() {
        return this.b.g();
    }

    public final void h() {
    }
}
