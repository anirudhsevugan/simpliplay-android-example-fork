package com.google.android.exoplayer2.source.hls.playlist;

import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import androidx.appcompat.widget.ActivityChooserView;
import com.dreamers.exoplayercore.repack.C0000a;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.source.LoadEventInfo;
import com.google.android.exoplayer2.source.MediaLoadData;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.hls.HlsDataSourceFactory;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class DefaultHlsPlaylistTracker implements HlsPlaylistTracker, Loader.Callback {
    public static final HlsPlaylistTracker.Factory a = DefaultHlsPlaylistTracker$$Lambda$0.a;
    /* access modifiers changed from: private */
    public final HlsDataSourceFactory b;
    /* access modifiers changed from: private */
    public final HlsPlaylistParserFactory c;
    /* access modifiers changed from: private */
    public final LoadErrorHandlingPolicy d;
    private final HashMap e;
    private final List f;
    /* access modifiers changed from: private */
    public MediaSourceEventListener.EventDispatcher g;
    private Loader h;
    /* access modifiers changed from: private */
    public Handler i;
    private HlsPlaylistTracker.PrimaryPlaylistListener j;
    /* access modifiers changed from: private */
    public HlsMasterPlaylist k;
    /* access modifiers changed from: private */
    public Uri l;
    private HlsMediaPlaylist m;
    private boolean n;
    private long o;

    final class MediaPlaylistBundle implements Loader.Callback {
        final Loader a = new Loader("DefaultHlsPlaylistTracker:MediaPlaylist");
        HlsMediaPlaylist b;
        long c;
        boolean d;
        /* access modifiers changed from: private */
        public final Uri e;
        private final DataSource f;
        private long g;
        private long h;
        /* access modifiers changed from: private */
        public long i;
        private IOException j;

        public MediaPlaylistBundle(Uri uri) {
            this.e = uri;
            this.f = DefaultHlsPlaylistTracker.this.b.a();
        }

        /* access modifiers changed from: private */
        public void a(HlsMediaPlaylist hlsMediaPlaylist) {
            long j2;
            Uri uri;
            HlsMediaPlaylist hlsMediaPlaylist2 = this.b;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.c = elapsedRealtime;
            HlsMediaPlaylist a2 = DefaultHlsPlaylistTracker.a(DefaultHlsPlaylistTracker.this, hlsMediaPlaylist2, hlsMediaPlaylist);
            this.b = a2;
            boolean z = true;
            if (a2 != hlsMediaPlaylist2) {
                this.j = null;
                this.g = elapsedRealtime;
                DefaultHlsPlaylistTracker.a(DefaultHlsPlaylistTracker.this, this.e, a2);
            } else if (!a2.k) {
                if (hlsMediaPlaylist.g + ((long) hlsMediaPlaylist.n.size()) < this.b.g) {
                    this.j = new HlsPlaylistTracker.PlaylistResetException();
                    DefaultHlsPlaylistTracker.a(DefaultHlsPlaylistTracker.this, this.e, -9223372036854775807L);
                } else {
                    double a3 = (double) C.a(this.b.i);
                    double f2 = DefaultHlsPlaylistTracker.f();
                    Double.isNaN(a3);
                    if (((double) (elapsedRealtime - this.g)) > a3 * f2) {
                        this.j = new HlsPlaylistTracker.PlaylistStuckException();
                        new MediaLoadData(4);
                        long a4 = DefaultHlsPlaylistTracker.this.d.a(new LoadErrorHandlingPolicy.LoadErrorInfo(this.j, 1));
                        DefaultHlsPlaylistTracker.a(DefaultHlsPlaylistTracker.this, this.e, a4);
                        if (a4 != -9223372036854775807L) {
                            a(a4);
                        }
                    }
                }
            }
            if (!this.b.r.e) {
                HlsMediaPlaylist hlsMediaPlaylist3 = this.b;
                j2 = hlsMediaPlaylist3.i;
                if (hlsMediaPlaylist3 == hlsMediaPlaylist2) {
                    j2 /= 2;
                }
            } else {
                j2 = 0;
            }
            this.h = elapsedRealtime + C.a(j2);
            if (this.b.j == -9223372036854775807L && !this.e.equals(DefaultHlsPlaylistTracker.this.l)) {
                z = false;
            }
            if (z && !this.b.k) {
                HlsMediaPlaylist hlsMediaPlaylist4 = this.b;
                if (hlsMediaPlaylist4 == null || (hlsMediaPlaylist4.r.a == -9223372036854775807L && !this.b.r.e)) {
                    uri = this.e;
                } else {
                    Uri.Builder buildUpon = this.e.buildUpon();
                    if (this.b.r.e) {
                        buildUpon.appendQueryParameter("_HLS_msn", String.valueOf(this.b.g + ((long) this.b.n.size())));
                        if (this.b.j != -9223372036854775807L) {
                            List list = this.b.o;
                            int size = list.size();
                            if (!list.isEmpty() && ((HlsMediaPlaylist.Part) C0000a.b((Iterable) list)).b) {
                                size--;
                            }
                            buildUpon.appendQueryParameter("_HLS_part", String.valueOf(size));
                        }
                    }
                    if (this.b.r.a != -9223372036854775807L) {
                        buildUpon.appendQueryParameter("_HLS_skip", this.b.r.b ? "v2" : "YES");
                    }
                    uri = buildUpon.build();
                }
                b(uri);
            }
        }

        private boolean a(long j2) {
            this.i = SystemClock.elapsedRealtime() + j2;
            return this.e.equals(DefaultHlsPlaylistTracker.this.l) && !DefaultHlsPlaylistTracker.h(DefaultHlsPlaylistTracker.this);
        }

        /* access modifiers changed from: private */
        public void b(Uri uri) {
            this.i = 0;
            if (!this.d && !this.a.c() && !this.a.b()) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (elapsedRealtime < this.h) {
                    this.d = true;
                    DefaultHlsPlaylistTracker.this.i.postDelayed(new DefaultHlsPlaylistTracker$MediaPlaylistBundle$$Lambda$0(this, uri), this.h - elapsedRealtime);
                    return;
                }
                a(uri);
            }
        }

        public final /* synthetic */ Loader.LoadErrorAction a(Loader.Loadable loadable, IOException iOException, int i2) {
            Loader.LoadErrorAction loadErrorAction;
            ParsingLoadable parsingLoadable = (ParsingLoadable) loadable;
            LoadEventInfo loadEventInfo = new LoadEventInfo((byte) 0);
            boolean z = iOException instanceof HlsPlaylistParser.DeltaUpdateException;
            if ((parsingLoadable.c.b.getQueryParameter("_HLS_msn") != null) || z) {
                int i3 = iOException instanceof HttpDataSource.InvalidResponseCodeException ? ((HttpDataSource.InvalidResponseCodeException) iOException).a : ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
                if (z || i3 == 400 || i3 == 503) {
                    this.h = SystemClock.elapsedRealtime();
                    a();
                    ((MediaSourceEventListener.EventDispatcher) Util.a((Object) DefaultHlsPlaylistTracker.this.g)).a(loadEventInfo, parsingLoadable.b, iOException, true);
                    return Loader.b;
                }
            }
            new MediaLoadData(parsingLoadable.b);
            LoadErrorHandlingPolicy.LoadErrorInfo loadErrorInfo = new LoadErrorHandlingPolicy.LoadErrorInfo(iOException, i2);
            long a2 = DefaultHlsPlaylistTracker.this.d.a(loadErrorInfo);
            boolean z2 = a2 != -9223372036854775807L;
            boolean z3 = DefaultHlsPlaylistTracker.a(DefaultHlsPlaylistTracker.this, this.e, a2) || !z2;
            if (z2) {
                z3 |= a(a2);
            }
            if (z3) {
                long b2 = DefaultHlsPlaylistTracker.this.d.b(loadErrorInfo);
                loadErrorAction = b2 != -9223372036854775807L ? Loader.a(false, b2) : Loader.c;
            } else {
                loadErrorAction = Loader.b;
            }
            boolean z4 = !loadErrorAction.a();
            DefaultHlsPlaylistTracker.this.g.a(loadEventInfo, parsingLoadable.b, iOException, z4);
            if (z4) {
                DefaultHlsPlaylistTracker.this.d.a();
            }
            return loadErrorAction;
        }

        public final void a() {
            b(this.e);
        }

        /* access modifiers changed from: package-private */
        public final void a(Uri uri) {
            ParsingLoadable parsingLoadable = new ParsingLoadable(this.f, uri, 4, DefaultHlsPlaylistTracker.this.c.a(DefaultHlsPlaylistTracker.this.k, this.b));
            this.a.a(parsingLoadable, this, DefaultHlsPlaylistTracker.this.d.a(parsingLoadable.b));
            DefaultHlsPlaylistTracker.this.g.a(new LoadEventInfo(), parsingLoadable.b);
        }

        public final /* synthetic */ void a(Loader.Loadable loadable, long j2, long j3) {
            HlsPlaylist hlsPlaylist = (HlsPlaylist) ((ParsingLoadable) loadable).d;
            LoadEventInfo loadEventInfo = new LoadEventInfo((byte) 0);
            if (hlsPlaylist instanceof HlsMediaPlaylist) {
                a((HlsMediaPlaylist) hlsPlaylist);
                DefaultHlsPlaylistTracker.this.g.b(loadEventInfo, 4);
            } else {
                this.j = new ParserException("Loaded playlist has unexpected type.");
                DefaultHlsPlaylistTracker.this.g.a(loadEventInfo, 4, this.j, true);
            }
            DefaultHlsPlaylistTracker.this.d.a();
        }

        public final /* synthetic */ void a(Loader.Loadable loadable, boolean z) {
            LoadEventInfo loadEventInfo = new LoadEventInfo((byte) 0);
            DefaultHlsPlaylistTracker.this.d.a();
            DefaultHlsPlaylistTracker.this.g.c(loadEventInfo, 4);
        }

        public final void b() {
            this.a.a(Integer.MIN_VALUE);
            IOException iOException = this.j;
            if (iOException != null) {
                throw iOException;
            }
        }
    }

    public DefaultHlsPlaylistTracker(HlsDataSourceFactory hlsDataSourceFactory, LoadErrorHandlingPolicy loadErrorHandlingPolicy, HlsPlaylistParserFactory hlsPlaylistParserFactory) {
        this(hlsDataSourceFactory, loadErrorHandlingPolicy, hlsPlaylistParserFactory, (byte) 0);
    }

    private DefaultHlsPlaylistTracker(HlsDataSourceFactory hlsDataSourceFactory, LoadErrorHandlingPolicy loadErrorHandlingPolicy, HlsPlaylistParserFactory hlsPlaylistParserFactory, byte b2) {
        this.b = hlsDataSourceFactory;
        this.c = hlsPlaylistParserFactory;
        this.d = loadErrorHandlingPolicy;
        this.f = new ArrayList();
        this.e = new HashMap();
        this.o = -9223372036854775807L;
    }

    private static HlsMediaPlaylist.Segment a(HlsMediaPlaylist hlsMediaPlaylist, HlsMediaPlaylist hlsMediaPlaylist2) {
        int i2 = (int) (hlsMediaPlaylist2.g - hlsMediaPlaylist.g);
        List list = hlsMediaPlaylist.n;
        if (i2 < list.size()) {
            return (HlsMediaPlaylist.Segment) list.get(i2);
        }
        return null;
    }

    static /* synthetic */ HlsMediaPlaylist a(DefaultHlsPlaylistTracker defaultHlsPlaylistTracker, HlsMediaPlaylist hlsMediaPlaylist, HlsMediaPlaylist hlsMediaPlaylist2) {
        long j2;
        int i2;
        HlsMediaPlaylist.Segment a2;
        int size;
        int size2;
        int size3;
        DefaultHlsPlaylistTracker defaultHlsPlaylistTracker2 = defaultHlsPlaylistTracker;
        HlsMediaPlaylist hlsMediaPlaylist3 = hlsMediaPlaylist;
        HlsMediaPlaylist hlsMediaPlaylist4 = hlsMediaPlaylist2;
        boolean z = true;
        if (hlsMediaPlaylist3 != null && hlsMediaPlaylist4.g <= hlsMediaPlaylist3.g && (hlsMediaPlaylist4.g < hlsMediaPlaylist3.g || ((size = hlsMediaPlaylist4.n.size() - hlsMediaPlaylist3.n.size()) == 0 ? !((size2 = hlsMediaPlaylist4.o.size()) > (size3 = hlsMediaPlaylist3.o.size()) || (size2 == size3 && hlsMediaPlaylist4.k && !hlsMediaPlaylist3.k)) : size <= 0))) {
            z = false;
        }
        if (z) {
            if (hlsMediaPlaylist4.l) {
                j2 = hlsMediaPlaylist4.d;
            } else {
                HlsMediaPlaylist hlsMediaPlaylist5 = defaultHlsPlaylistTracker2.m;
                j2 = hlsMediaPlaylist5 != null ? hlsMediaPlaylist5.d : 0;
                if (hlsMediaPlaylist3 != null) {
                    int size4 = hlsMediaPlaylist3.n.size();
                    HlsMediaPlaylist.Segment a3 = a(hlsMediaPlaylist, hlsMediaPlaylist2);
                    if (a3 != null) {
                        j2 = hlsMediaPlaylist3.d + a3.g;
                    } else if (((long) size4) == hlsMediaPlaylist4.g - hlsMediaPlaylist3.g) {
                        j2 = hlsMediaPlaylist.a();
                    }
                }
            }
            long j3 = j2;
            if (hlsMediaPlaylist4.e) {
                i2 = hlsMediaPlaylist4.f;
            } else {
                HlsMediaPlaylist hlsMediaPlaylist6 = defaultHlsPlaylistTracker2.m;
                i2 = hlsMediaPlaylist6 != null ? hlsMediaPlaylist6.f : 0;
                if (!(hlsMediaPlaylist3 == null || (a2 = a(hlsMediaPlaylist, hlsMediaPlaylist2)) == null)) {
                    i2 = (hlsMediaPlaylist3.f + a2.f) - ((HlsMediaPlaylist.Segment) hlsMediaPlaylist4.n.get(0)).f;
                }
            }
            return new HlsMediaPlaylist(hlsMediaPlaylist4.a, hlsMediaPlaylist4.s, hlsMediaPlaylist4.t, hlsMediaPlaylist4.b, hlsMediaPlaylist4.c, j3, true, i2, hlsMediaPlaylist4.g, hlsMediaPlaylist4.h, hlsMediaPlaylist4.i, hlsMediaPlaylist4.j, hlsMediaPlaylist4.u, hlsMediaPlaylist4.k, hlsMediaPlaylist4.l, hlsMediaPlaylist4.m, hlsMediaPlaylist4.n, hlsMediaPlaylist4.o, hlsMediaPlaylist4.r, hlsMediaPlaylist4.p);
        } else if (!hlsMediaPlaylist4.k || hlsMediaPlaylist3.k) {
            return hlsMediaPlaylist3;
        } else {
            return new HlsMediaPlaylist(hlsMediaPlaylist3.a, hlsMediaPlaylist3.s, hlsMediaPlaylist3.t, hlsMediaPlaylist3.b, hlsMediaPlaylist3.c, hlsMediaPlaylist3.d, hlsMediaPlaylist3.e, hlsMediaPlaylist3.f, hlsMediaPlaylist3.g, hlsMediaPlaylist3.h, hlsMediaPlaylist3.i, hlsMediaPlaylist3.j, hlsMediaPlaylist3.u, true, hlsMediaPlaylist3.l, hlsMediaPlaylist3.m, hlsMediaPlaylist3.n, hlsMediaPlaylist3.o, hlsMediaPlaylist3.r, hlsMediaPlaylist3.p);
        }
    }

    static /* synthetic */ void a(DefaultHlsPlaylistTracker defaultHlsPlaylistTracker, Uri uri, HlsMediaPlaylist hlsMediaPlaylist) {
        if (uri.equals(defaultHlsPlaylistTracker.l)) {
            if (defaultHlsPlaylistTracker.m == null) {
                defaultHlsPlaylistTracker.n = !hlsMediaPlaylist.k;
                defaultHlsPlaylistTracker.o = hlsMediaPlaylist.d;
            }
            defaultHlsPlaylistTracker.m = hlsMediaPlaylist;
            defaultHlsPlaylistTracker.j.a(hlsMediaPlaylist);
        }
        int size = defaultHlsPlaylistTracker.f.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((HlsPlaylistTracker.PlaylistEventListener) defaultHlsPlaylistTracker.f.get(i2)).h();
        }
    }

    static /* synthetic */ boolean a(DefaultHlsPlaylistTracker defaultHlsPlaylistTracker, Uri uri, long j2) {
        int size = defaultHlsPlaylistTracker.f.size();
        boolean z = false;
        for (int i2 = 0; i2 < size; i2++) {
            z |= !((HlsPlaylistTracker.PlaylistEventListener) defaultHlsPlaylistTracker.f.get(i2)).a(uri, j2);
        }
        return z;
    }

    private Uri d(Uri uri) {
        HlsMediaPlaylist.RenditionReport renditionReport;
        HlsMediaPlaylist hlsMediaPlaylist = this.m;
        if (hlsMediaPlaylist == null || !hlsMediaPlaylist.r.e || (renditionReport = (HlsMediaPlaylist.RenditionReport) this.m.p.get(uri)) == null) {
            return uri;
        }
        Uri.Builder buildUpon = uri.buildUpon();
        buildUpon.appendQueryParameter("_HLS_msn", String.valueOf(renditionReport.a));
        if (renditionReport.b != -1) {
            buildUpon.appendQueryParameter("_HLS_part", String.valueOf(renditionReport.b));
        }
        return buildUpon.build();
    }

    static /* synthetic */ double f() {
        return 3.5d;
    }

    static /* synthetic */ boolean h(DefaultHlsPlaylistTracker defaultHlsPlaylistTracker) {
        List list = defaultHlsPlaylistTracker.k.c;
        int size = list.size();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        for (int i2 = 0; i2 < size; i2++) {
            MediaPlaylistBundle mediaPlaylistBundle = (MediaPlaylistBundle) Assertions.b((Object) (MediaPlaylistBundle) defaultHlsPlaylistTracker.e.get(((HlsMasterPlaylist.Variant) list.get(i2)).a));
            if (elapsedRealtime > mediaPlaylistBundle.i) {
                Uri b2 = mediaPlaylistBundle.e;
                defaultHlsPlaylistTracker.l = b2;
                mediaPlaylistBundle.b(defaultHlsPlaylistTracker.d(b2));
                return true;
            }
        }
        return false;
    }

    public final HlsMediaPlaylist a(Uri uri, boolean z) {
        HlsMediaPlaylist hlsMediaPlaylist;
        HlsMediaPlaylist hlsMediaPlaylist2 = ((MediaPlaylistBundle) this.e.get(uri)).b;
        if (hlsMediaPlaylist2 != null && z && !uri.equals(this.l)) {
            List list = this.k.c;
            boolean z2 = false;
            int i2 = 0;
            while (true) {
                if (i2 >= list.size()) {
                    break;
                } else if (uri.equals(((HlsMasterPlaylist.Variant) list.get(i2)).a)) {
                    z2 = true;
                    break;
                } else {
                    i2++;
                }
            }
            if (z2 && ((hlsMediaPlaylist = this.m) == null || !hlsMediaPlaylist.k)) {
                this.l = uri;
                ((MediaPlaylistBundle) this.e.get(uri)).b(d(uri));
            }
        }
        return hlsMediaPlaylist2;
    }

    public final /* synthetic */ Loader.LoadErrorAction a(Loader.Loadable loadable, IOException iOException, int i2) {
        ParsingLoadable parsingLoadable = (ParsingLoadable) loadable;
        LoadEventInfo loadEventInfo = new LoadEventInfo((byte) 0);
        new MediaLoadData(parsingLoadable.b);
        long b2 = this.d.b(new LoadErrorHandlingPolicy.LoadErrorInfo(iOException, i2));
        boolean z = b2 == -9223372036854775807L;
        this.g.a(loadEventInfo, parsingLoadable.b, iOException, z);
        if (z) {
            this.d.a();
        }
        return z ? Loader.c : Loader.a(false, b2);
    }

    public final void a() {
        this.l = null;
        this.m = null;
        this.k = null;
        this.o = -9223372036854775807L;
        this.h.a((Loader.ReleaseCallback) null);
        this.h = null;
        for (MediaPlaylistBundle mediaPlaylistBundle : this.e.values()) {
            mediaPlaylistBundle.a.a((Loader.ReleaseCallback) null);
        }
        this.i.removeCallbacksAndMessages((Object) null);
        this.i = null;
        this.e.clear();
    }

    public final void a(Uri uri, MediaSourceEventListener.EventDispatcher eventDispatcher, HlsPlaylistTracker.PrimaryPlaylistListener primaryPlaylistListener) {
        this.i = Util.a();
        this.g = eventDispatcher;
        this.j = primaryPlaylistListener;
        ParsingLoadable parsingLoadable = new ParsingLoadable(this.b.a(), uri, 4, this.c.a());
        Assertions.b(this.h == null);
        Loader loader = new Loader("DefaultHlsPlaylistTracker:MasterPlaylist");
        this.h = loader;
        loader.a(parsingLoadable, this, this.d.a(parsingLoadable.b));
        eventDispatcher.a(new LoadEventInfo(), parsingLoadable.b);
    }

    public final void a(HlsPlaylistTracker.PlaylistEventListener playlistEventListener) {
        Assertions.b((Object) playlistEventListener);
        this.f.add(playlistEventListener);
    }

    public final /* synthetic */ void a(Loader.Loadable loadable, long j2, long j3) {
        HlsPlaylist hlsPlaylist = (HlsPlaylist) ((ParsingLoadable) loadable).d;
        boolean z = hlsPlaylist instanceof HlsMediaPlaylist;
        HlsMasterPlaylist a2 = z ? HlsMasterPlaylist.a(hlsPlaylist.s) : (HlsMasterPlaylist) hlsPlaylist;
        this.k = a2;
        this.l = ((HlsMasterPlaylist.Variant) a2.c.get(0)).a;
        List list = a2.b;
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            Uri uri = (Uri) list.get(i2);
            this.e.put(uri, new MediaPlaylistBundle(uri));
        }
        LoadEventInfo loadEventInfo = new LoadEventInfo((byte) 0);
        MediaPlaylistBundle mediaPlaylistBundle = (MediaPlaylistBundle) this.e.get(this.l);
        if (z) {
            mediaPlaylistBundle.a((HlsMediaPlaylist) hlsPlaylist);
        } else {
            mediaPlaylistBundle.a();
        }
        this.d.a();
        this.g.b(loadEventInfo, 4);
    }

    public final /* synthetic */ void a(Loader.Loadable loadable, boolean z) {
        LoadEventInfo loadEventInfo = new LoadEventInfo((byte) 0);
        this.d.a();
        this.g.c(loadEventInfo, 4);
    }

    public final boolean a(Uri uri) {
        MediaPlaylistBundle mediaPlaylistBundle = (MediaPlaylistBundle) this.e.get(uri);
        if (mediaPlaylistBundle.b == null) {
            return false;
        }
        return mediaPlaylistBundle.b.k || mediaPlaylistBundle.b.a == 2 || mediaPlaylistBundle.b.a == 1 || mediaPlaylistBundle.c + Math.max(30000, C.a(mediaPlaylistBundle.b.q)) > SystemClock.elapsedRealtime();
    }

    public final HlsMasterPlaylist b() {
        return this.k;
    }

    public final void b(Uri uri) {
        ((MediaPlaylistBundle) this.e.get(uri)).b();
    }

    public final void b(HlsPlaylistTracker.PlaylistEventListener playlistEventListener) {
        this.f.remove(playlistEventListener);
    }

    public final long c() {
        return this.o;
    }

    public final void c(Uri uri) {
        ((MediaPlaylistBundle) this.e.get(uri)).a();
    }

    public final void d() {
        Loader loader = this.h;
        if (loader != null) {
            loader.a(Integer.MIN_VALUE);
        }
        Uri uri = this.l;
        if (uri != null) {
            b(uri);
        }
    }

    public final boolean e() {
        return this.n;
    }
}
