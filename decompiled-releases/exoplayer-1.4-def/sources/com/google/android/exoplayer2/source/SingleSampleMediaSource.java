package com.google.android.exoplayer2.source;

import android.net.Uri;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import java.util.Collections;

public final class SingleSampleMediaSource extends BaseMediaSource {
    private final DataSpec a;
    private final DataSource.Factory b;
    private final Format c;
    private final long d;
    private final LoadErrorHandlingPolicy e;
    private final boolean f;
    private final Timeline g;
    private final MediaItem h;
    private TransferListener i;

    public final class Factory {
        final DataSource.Factory a;
        LoadErrorHandlingPolicy b = new DefaultLoadErrorHandlingPolicy();
        boolean c = true;

        public Factory(DataSource.Factory factory) {
            this.a = (DataSource.Factory) Assertions.b((Object) factory);
        }
    }

    private SingleSampleMediaSource(MediaItem.Subtitle subtitle, DataSource.Factory factory, LoadErrorHandlingPolicy loadErrorHandlingPolicy, boolean z) {
        this.b = factory;
        this.d = -9223372036854775807L;
        this.e = loadErrorHandlingPolicy;
        this.f = z;
        MediaItem.Builder builder = new MediaItem.Builder();
        builder.a = Uri.EMPTY;
        MediaItem.Builder b2 = builder.a(subtitle.a.toString()).b(Collections.singletonList(subtitle));
        b2.b = null;
        MediaItem a2 = b2.a();
        this.h = a2;
        Format.Builder builder2 = new Format.Builder();
        builder2.a = null;
        builder2.k = subtitle.b;
        builder2.c = subtitle.c;
        builder2.d = subtitle.d;
        builder2.e = 0;
        builder2.b = subtitle.e;
        this.c = builder2.a();
        DataSpec.Builder builder3 = new DataSpec.Builder();
        builder3.a = subtitle.a;
        builder3.h = 1;
        this.a = builder3.a();
        this.g = new SinglePeriodTimeline(-9223372036854775807L, true, false, a2);
    }

    /* synthetic */ SingleSampleMediaSource(MediaItem.Subtitle subtitle, DataSource.Factory factory, LoadErrorHandlingPolicy loadErrorHandlingPolicy, boolean z, byte b2) {
        this(subtitle, factory, loadErrorHandlingPolicy, z);
    }

    public final MediaPeriod a(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        return new SingleSampleMediaPeriod(this.a, this.b, this.i, this.c, -9223372036854775807L, this.e, a(mediaPeriodId), this.f);
    }

    public final void a(MediaPeriod mediaPeriod) {
        ((SingleSampleMediaPeriod) mediaPeriod).a.a((Loader.ReleaseCallback) null);
    }

    /* access modifiers changed from: protected */
    public final void a(TransferListener transferListener) {
        this.i = transferListener;
        a(this.g);
    }

    /* access modifiers changed from: protected */
    public final void c() {
    }

    public final MediaItem g() {
        return this.h;
    }

    public final void h() {
    }
}
