package com.google.android.exoplayer2.source;

import android.content.Context;
import android.util.SparseArray;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.SingleSampleMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import java.util.List;

public final class DefaultMediaSourceFactory implements MediaSourceFactory {
    private final DataSource.Factory a;
    private final SparseArray b;
    private final int[] c;
    private long d;
    private long e;
    private long f;
    private float g;
    private float h;

    public DefaultMediaSourceFactory(Context context, ExtractorsFactory extractorsFactory) {
        this((DataSource.Factory) new DefaultDataSourceFactory(context), extractorsFactory);
    }

    private DefaultMediaSourceFactory(DataSource.Factory factory, ExtractorsFactory extractorsFactory) {
        this.a = factory;
        SparseArray a2 = a(factory, extractorsFactory);
        this.b = a2;
        this.c = new int[a2.size()];
        for (int i = 0; i < this.b.size(); i++) {
            this.c[i] = this.b.keyAt(i);
        }
        this.d = -9223372036854775807L;
        this.e = -9223372036854775807L;
        this.f = -9223372036854775807L;
        this.g = -3.4028235E38f;
        this.h = -3.4028235E38f;
    }

    private static SparseArray a(DataSource.Factory factory, ExtractorsFactory extractorsFactory) {
        SparseArray sparseArray = new SparseArray();
        try {
            sparseArray.put(0, (MediaSourceFactory) Class.forName("com.google.android.exoplayer2.source.dash.DashMediaSource$Factory").asSubclass(MediaSourceFactory.class).getConstructor(new Class[]{DataSource.Factory.class}).newInstance(new Object[]{factory}));
        } catch (Exception e2) {
        }
        try {
            sparseArray.put(1, (MediaSourceFactory) Class.forName("com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource$Factory").asSubclass(MediaSourceFactory.class).getConstructor(new Class[]{DataSource.Factory.class}).newInstance(new Object[]{factory}));
        } catch (Exception e3) {
        }
        try {
            sparseArray.put(2, (MediaSourceFactory) Class.forName("com.google.android.exoplayer2.source.hls.HlsMediaSource$Factory").asSubclass(MediaSourceFactory.class).getConstructor(new Class[]{DataSource.Factory.class}).newInstance(new Object[]{factory}));
        } catch (Exception e4) {
        }
        try {
            sparseArray.put(3, (MediaSourceFactory) Class.forName("com.google.android.exoplayer2.source.rtsp.RtspMediaSource$Factory").asSubclass(MediaSourceFactory.class).getConstructor(new Class[0]).newInstance(new Object[0]));
        } catch (Exception e5) {
        }
        sparseArray.put(4, new ProgressiveMediaSource.Factory(factory, extractorsFactory));
        return sparseArray;
    }

    public final MediaSource a(MediaItem mediaItem) {
        Assertions.b((Object) mediaItem.b);
        int a2 = Util.a(mediaItem.b.a, mediaItem.b.b);
        MediaSourceFactory mediaSourceFactory = (MediaSourceFactory) this.b.get(a2);
        Assertions.b((Object) mediaSourceFactory, (Object) new StringBuilder(68).append("No suitable media source factory found for content type: ").append(a2).toString());
        if ((mediaItem.c.a == -9223372036854775807L && this.d != -9223372036854775807L) || ((mediaItem.c.d == -3.4028235E38f && this.g != -3.4028235E38f) || ((mediaItem.c.e == -3.4028235E38f && this.h != -3.4028235E38f) || ((mediaItem.c.b == -9223372036854775807L && this.e != -9223372036854775807L) || (mediaItem.c.c == -9223372036854775807L && this.f != -9223372036854775807L))))) {
            MediaItem.Builder a3 = mediaItem.a();
            a3.c = mediaItem.c.a == -9223372036854775807L ? this.d : mediaItem.c.a;
            a3.f = mediaItem.c.d == -3.4028235E38f ? this.g : mediaItem.c.d;
            a3.g = mediaItem.c.e == -3.4028235E38f ? this.h : mediaItem.c.e;
            a3.d = mediaItem.c.b == -9223372036854775807L ? this.e : mediaItem.c.b;
            a3.e = mediaItem.c.c == -9223372036854775807L ? this.f : mediaItem.c.c;
            mediaItem = a3.a();
        }
        MediaSource a4 = mediaSourceFactory.a(mediaItem);
        List list = ((MediaItem.PlaybackProperties) Util.a((Object) mediaItem.b)).g;
        if (!list.isEmpty()) {
            MediaSource[] mediaSourceArr = new MediaSource[(list.size() + 1)];
            int i = 0;
            mediaSourceArr[0] = a4;
            SingleSampleMediaSource.Factory factory = new SingleSampleMediaSource.Factory(this.a);
            factory.b = new DefaultLoadErrorHandlingPolicy();
            while (i < list.size()) {
                int i2 = i + 1;
                mediaSourceArr[i2] = new SingleSampleMediaSource((MediaItem.Subtitle) list.get(i), factory.a, factory.b, factory.c, (byte) 0);
                i = i2;
            }
            a4 = new MergingMediaSource(mediaSourceArr);
        }
        ClippingMediaSource clippingMediaSource = a4;
        if (!(mediaItem.e.a == 0 && mediaItem.e.b == Long.MIN_VALUE && !mediaItem.e.d)) {
            clippingMediaSource = new ClippingMediaSource(clippingMediaSource, C.b(mediaItem.e.a), C.b(mediaItem.e.b), !mediaItem.e.e, mediaItem.e.c, mediaItem.e.d);
        }
        Assertions.b((Object) mediaItem.b);
        if (mediaItem.b.d == null) {
            return clippingMediaSource;
        }
        Log.c("DefaultMediaSourceFactory", "Playing media without ads. Configure ad support by calling setAdsLoaderProvider and setAdViewProvider.");
        return clippingMediaSource;
    }
}
