package com.google.android.exoplayer2.source.hls;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.drm.DefaultDrmSessionManagerProvider;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.DrmSessionManagerProvider;
import com.google.android.exoplayer2.source.BaseMediaSource;
import com.google.android.exoplayer2.source.CompositeSequenceableLoaderFactory;
import com.google.android.exoplayer2.source.DefaultCompositeSequenceableLoaderFactory;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.MediaSourceFactory;
import com.google.android.exoplayer2.source.SinglePeriodTimeline;
import com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper;
import com.google.android.exoplayer2.source.hls.playlist.DefaultHlsPlaylistParserFactory;
import com.google.android.exoplayer2.source.hls.playlist.DefaultHlsPlaylistTracker;
import com.google.android.exoplayer2.source.hls.playlist.FilteringHlsPlaylistParserFactory;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParserFactory;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.Collections;
import java.util.List;

public final class HlsMediaSource extends BaseMediaSource implements HlsPlaylistTracker.PrimaryPlaylistListener {
    private final HlsExtractorFactory a;
    private final MediaItem.PlaybackProperties b;
    private final HlsDataSourceFactory c;
    private final CompositeSequenceableLoaderFactory d;
    private final DrmSessionManager e;
    private final LoadErrorHandlingPolicy f;
    private final boolean g;
    private final int h;
    private final boolean i;
    private final HlsPlaylistTracker j;
    private final long k;
    private final MediaItem l;
    private MediaItem.LiveConfiguration m;
    private TransferListener n;

    public final class Factory implements MediaSourceFactory {
        private final HlsDataSourceFactory a;
        private HlsExtractorFactory b;
        private HlsPlaylistParserFactory c;
        private HlsPlaylistTracker.Factory d;
        private CompositeSequenceableLoaderFactory e;
        private DrmSessionManagerProvider f;
        private LoadErrorHandlingPolicy g;
        private int h;
        private List i;
        private long j;

        private Factory(HlsDataSourceFactory hlsDataSourceFactory) {
            this.a = (HlsDataSourceFactory) Assertions.b((Object) hlsDataSourceFactory);
            this.f = new DefaultDrmSessionManagerProvider();
            this.c = new DefaultHlsPlaylistParserFactory();
            this.d = DefaultHlsPlaylistTracker.a;
            this.b = HlsExtractorFactory.a;
            this.g = new DefaultLoadErrorHandlingPolicy();
            this.e = new DefaultCompositeSequenceableLoaderFactory();
            this.h = 1;
            this.i = Collections.emptyList();
            this.j = -9223372036854775807L;
        }

        public Factory(DataSource.Factory factory) {
            this((HlsDataSourceFactory) new DefaultHlsDataSourceFactory(factory));
        }

        public final /* synthetic */ MediaSource a(MediaItem mediaItem) {
            Assertions.b((Object) mediaItem.b);
            HlsPlaylistParserFactory hlsPlaylistParserFactory = this.c;
            List list = mediaItem.b.e.isEmpty() ? this.i : mediaItem.b.e;
            if (!list.isEmpty()) {
                hlsPlaylistParserFactory = new FilteringHlsPlaylistParserFactory(hlsPlaylistParserFactory, list);
            }
            if (mediaItem.b.e.isEmpty() && !list.isEmpty()) {
                mediaItem = mediaItem.a().a(list).a();
            }
            MediaItem mediaItem2 = mediaItem;
            HlsDataSourceFactory hlsDataSourceFactory = this.a;
            HlsExtractorFactory hlsExtractorFactory = this.b;
            CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory = this.e;
            DrmSessionManager a2 = this.f.a(mediaItem2);
            LoadErrorHandlingPolicy loadErrorHandlingPolicy = this.g;
            return new HlsMediaSource(mediaItem2, hlsDataSourceFactory, hlsExtractorFactory, compositeSequenceableLoaderFactory, a2, loadErrorHandlingPolicy, this.d.a(this.a, loadErrorHandlingPolicy, hlsPlaylistParserFactory), this.j, this.h, (byte) 0);
        }
    }

    static {
        ExoPlayerLibraryInfo.a("goog.exo.hls");
    }

    private HlsMediaSource(MediaItem mediaItem, HlsDataSourceFactory hlsDataSourceFactory, HlsExtractorFactory hlsExtractorFactory, CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory, DrmSessionManager drmSessionManager, LoadErrorHandlingPolicy loadErrorHandlingPolicy, HlsPlaylistTracker hlsPlaylistTracker, long j2, int i2) {
        this.b = (MediaItem.PlaybackProperties) Assertions.b((Object) mediaItem.b);
        this.l = mediaItem;
        this.m = mediaItem.c;
        this.c = hlsDataSourceFactory;
        this.a = hlsExtractorFactory;
        this.d = compositeSequenceableLoaderFactory;
        this.e = drmSessionManager;
        this.f = loadErrorHandlingPolicy;
        this.j = hlsPlaylistTracker;
        this.k = j2;
        this.g = false;
        this.h = i2;
        this.i = false;
    }

    /* synthetic */ HlsMediaSource(MediaItem mediaItem, HlsDataSourceFactory hlsDataSourceFactory, HlsExtractorFactory hlsExtractorFactory, CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory, DrmSessionManager drmSessionManager, LoadErrorHandlingPolicy loadErrorHandlingPolicy, HlsPlaylistTracker hlsPlaylistTracker, long j2, int i2, byte b2) {
        this(mediaItem, hlsDataSourceFactory, hlsExtractorFactory, compositeSequenceableLoaderFactory, drmSessionManager, loadErrorHandlingPolicy, hlsPlaylistTracker, j2, i2);
    }

    private static HlsMediaPlaylist.Part a(List list, long j2) {
        HlsMediaPlaylist.Part part = null;
        for (int i2 = 0; i2 < list.size(); i2++) {
            HlsMediaPlaylist.Part part2 = (HlsMediaPlaylist.Part) list.get(i2);
            if (part2.g > j2 || !part2.a) {
                if (part2.g > j2) {
                    break;
                }
            } else {
                part = part2;
            }
        }
        return part;
    }

    private static HlsMediaPlaylist.Segment b(List list, long j2) {
        return (HlsMediaPlaylist.Segment) list.get(Util.a(list, (Comparable) Long.valueOf(j2), true));
    }

    public final MediaPeriod a(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j2) {
        MediaSourceEventListener.EventDispatcher a2 = a(mediaPeriodId);
        return new HlsMediaPeriod(this.a, this.j, this.c, this.n, this.e, b(mediaPeriodId), this.f, a2, allocator, this.d, false, this.h, false);
    }

    public final void a(MediaPeriod mediaPeriod) {
        HlsMediaPeriod hlsMediaPeriod = (HlsMediaPeriod) mediaPeriod;
        hlsMediaPeriod.a.b((HlsPlaylistTracker.PlaylistEventListener) hlsMediaPeriod);
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : hlsMediaPeriod.c) {
            if (hlsSampleStreamWrapper.m) {
                for (HlsSampleStreamWrapper.HlsSampleQueue c2 : hlsSampleStreamWrapper.j) {
                    c2.c();
                }
            }
            hlsSampleStreamWrapper.d.a((Loader.ReleaseCallback) hlsSampleStreamWrapper);
            hlsSampleStreamWrapper.h.removeCallbacksAndMessages((Object) null);
            hlsSampleStreamWrapper.q = true;
            hlsSampleStreamWrapper.i.clear();
        }
        hlsMediaPeriod.b = null;
    }

    public final void a(HlsMediaPlaylist hlsMediaPlaylist) {
        SinglePeriodTimeline singlePeriodTimeline;
        long j2;
        long j3;
        long j4;
        HlsMediaPlaylist hlsMediaPlaylist2 = hlsMediaPlaylist;
        long a2 = hlsMediaPlaylist2.l ? C.a(hlsMediaPlaylist2.d) : -9223372036854775807L;
        long j5 = (hlsMediaPlaylist2.a == 2 || hlsMediaPlaylist2.a == 1) ? a2 : -9223372036854775807L;
        Assertions.b((Object) this.j.b());
        HlsManifest hlsManifest = new HlsManifest();
        if (this.j.e()) {
            long c2 = hlsMediaPlaylist2.d - this.j.c();
            long j6 = hlsMediaPlaylist2.k ? c2 + hlsMediaPlaylist2.q : -9223372036854775807L;
            long b2 = hlsMediaPlaylist2.l ? C.b(Util.a(this.k)) - hlsMediaPlaylist.a() : 0;
            if (this.m.a != -9223372036854775807L) {
                j3 = C.b(this.m.a);
            } else {
                HlsMediaPlaylist.ServerControl serverControl = hlsMediaPlaylist2.r;
                j3 = (hlsMediaPlaylist2.b != -9223372036854775807L ? hlsMediaPlaylist2.q - hlsMediaPlaylist2.b : (serverControl.d == -9223372036854775807L || hlsMediaPlaylist2.j == -9223372036854775807L) ? serverControl.c != -9223372036854775807L ? serverControl.c : hlsMediaPlaylist2.i * 3 : serverControl.d) + b2;
            }
            long a3 = C.a(Util.a(j3, b2, hlsMediaPlaylist2.q + b2));
            if (a3 != this.m.a) {
                MediaItem.Builder a4 = this.l.a();
                a4.c = a3;
                this.m = a4.a().c;
            }
            long b3 = hlsMediaPlaylist2.b != -9223372036854775807L ? hlsMediaPlaylist2.b : (hlsMediaPlaylist2.q + b2) - C.b(this.m.a);
            if (!hlsMediaPlaylist2.c) {
                HlsMediaPlaylist.Part a5 = a(hlsMediaPlaylist2.o, b3);
                if (a5 != null) {
                    b3 = a5.g;
                } else if (hlsMediaPlaylist2.n.isEmpty()) {
                    j4 = 0;
                    singlePeriodTimeline = new SinglePeriodTimeline(j5, a2, j6, hlsMediaPlaylist2.q, c2, j4, true, !hlsMediaPlaylist2.k, hlsManifest, this.l, this.m);
                } else {
                    HlsMediaPlaylist.Segment b4 = b(hlsMediaPlaylist2.n, b3);
                    HlsMediaPlaylist.Part a6 = a(b4.a, b3);
                    b3 = a6 != null ? a6.g : b4.g;
                }
            }
            j4 = b3;
            singlePeriodTimeline = new SinglePeriodTimeline(j5, a2, j6, hlsMediaPlaylist2.q, c2, j4, true, !hlsMediaPlaylist2.k, hlsManifest, this.l, this.m);
        } else {
            if (hlsMediaPlaylist2.b == -9223372036854775807L || hlsMediaPlaylist2.n.isEmpty()) {
                j2 = 0;
            } else {
                j2 = (hlsMediaPlaylist2.c || hlsMediaPlaylist2.b == hlsMediaPlaylist2.q) ? hlsMediaPlaylist2.b : b(hlsMediaPlaylist2.n, hlsMediaPlaylist2.b).g;
            }
            singlePeriodTimeline = new SinglePeriodTimeline(j5, a2, hlsMediaPlaylist2.q, hlsMediaPlaylist2.q, 0, j2, true, false, hlsManifest, this.l, (MediaItem.LiveConfiguration) null);
        }
        a((Timeline) singlePeriodTimeline);
    }

    public final void a(TransferListener transferListener) {
        this.n = transferListener;
        this.e.a();
        this.j.a(this.b.a, a((MediaSource.MediaPeriodId) null), this);
    }

    public final void c() {
        this.j.a();
        this.e.b();
    }

    public final MediaItem g() {
        return this.l;
    }

    public final void h() {
        this.j.d();
    }
}
