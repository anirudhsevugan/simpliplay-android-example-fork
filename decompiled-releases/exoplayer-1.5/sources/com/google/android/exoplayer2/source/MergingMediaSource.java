package com.google.android.exoplayer2.source;

import com.dreamers.exoplayercore.repack.C0000a;
import com.dreamers.exoplayercore.repack.C0064cj;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MergingMediaPeriod;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.TransferListener;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public final class MergingMediaSource extends CompositeMediaSource {
    private static final MediaItem a = new MediaItem.Builder().a("MergingMediaSource").a();
    private final boolean b;
    private final MediaSource[] c;
    private final Timeline[] d;
    private final ArrayList e;
    private final CompositeSequenceableLoaderFactory f;
    private int g;
    private long[][] h;
    private IllegalMergeException i;

    public final class IllegalMergeException extends IOException {
    }

    private MergingMediaSource(CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory, MediaSource... mediaSourceArr) {
        this.b = false;
        this.c = mediaSourceArr;
        this.f = compositeSequenceableLoaderFactory;
        this.e = new ArrayList(Arrays.asList(mediaSourceArr));
        this.g = -1;
        this.d = new Timeline[mediaSourceArr.length];
        this.h = new long[0][];
        new HashMap();
        C0000a.a(8, "expectedKeys");
        new C0064cj().b().a();
    }

    public MergingMediaSource(MediaSource... mediaSourceArr) {
        this(mediaSourceArr, (byte) 0);
    }

    private MergingMediaSource(MediaSource[] mediaSourceArr, byte b2) {
        this(mediaSourceArr, 0);
    }

    private MergingMediaSource(MediaSource[] mediaSourceArr, char c2) {
        this((CompositeSequenceableLoaderFactory) new DefaultCompositeSequenceableLoaderFactory(), mediaSourceArr);
    }

    public final MediaPeriod a(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        int length = this.c.length;
        MediaPeriod[] mediaPeriodArr = new MediaPeriod[length];
        int c2 = this.d[0].c(mediaPeriodId.a);
        for (int i2 = 0; i2 < length; i2++) {
            mediaPeriodArr[i2] = this.c[i2].a(mediaPeriodId.a(this.d[i2].a(c2)), allocator, j - this.h[c2][i2]);
        }
        return new MergingMediaPeriod(this.f, this.h[c2], mediaPeriodArr);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ MediaSource.MediaPeriodId a(Object obj, MediaSource.MediaPeriodId mediaPeriodId) {
        if (((Integer) obj).intValue() == 0) {
            return mediaPeriodId;
        }
        return null;
    }

    public final void a(MediaPeriod mediaPeriod) {
        MergingMediaPeriod mergingMediaPeriod = (MergingMediaPeriod) mediaPeriod;
        int i2 = 0;
        while (true) {
            MediaSource[] mediaSourceArr = this.c;
            if (i2 < mediaSourceArr.length) {
                mediaSourceArr[i2].a(mergingMediaPeriod.a[i2] instanceof MergingMediaPeriod.TimeOffsetMediaPeriod ? ((MergingMediaPeriod.TimeOffsetMediaPeriod) mergingMediaPeriod.a[i2]).a : mergingMediaPeriod.a[i2]);
                i2++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void a(TransferListener transferListener) {
        super.a(transferListener);
        for (int i2 = 0; i2 < this.c.length; i2++) {
            a((Object) Integer.valueOf(i2), this.c[i2]);
        }
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void a(Object obj, MediaSource mediaSource, Timeline timeline) {
        Integer num = (Integer) obj;
        if (this.i == null) {
            if (this.g == -1) {
                this.g = timeline.b();
            } else if (timeline.b() != this.g) {
                this.i = new IllegalMergeException();
                return;
            }
            if (this.h.length == 0) {
                this.h = (long[][]) Array.newInstance(Long.TYPE, new int[]{this.g, this.d.length});
            }
            this.e.remove(mediaSource);
            this.d[num.intValue()] = timeline;
            if (this.e.isEmpty()) {
                a(this.d[0]);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void c() {
        super.c();
        Arrays.fill(this.d, (Object) null);
        this.g = -1;
        this.i = null;
        this.e.clear();
        Collections.addAll(this.e, this.c);
    }

    public final MediaItem g() {
        MediaSource[] mediaSourceArr = this.c;
        return mediaSourceArr.length > 0 ? mediaSourceArr[0].g() : a;
    }

    public final void h() {
        IllegalMergeException illegalMergeException = this.i;
        if (illegalMergeException == null) {
            super.h();
            return;
        }
        throw illegalMergeException;
    }
}
