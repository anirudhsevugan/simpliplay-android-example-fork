package com.google.android.exoplayer2.source.dash;

import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.core.location.LocationRequestCompat;
import com.dreamers.exoplayercore.repack.aC;
import com.dreamers.exoplayercore.repack.cP;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.drm.DefaultDrmSessionManagerProvider;
import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.DrmSessionManagerProvider;
import com.google.android.exoplayer2.offline.FilteringManifestParser;
import com.google.android.exoplayer2.source.BaseMediaSource;
import com.google.android.exoplayer2.source.CompositeSequenceableLoaderFactory;
import com.google.android.exoplayer2.source.DefaultCompositeSequenceableLoaderFactory;
import com.google.android.exoplayer2.source.LoadEventInfo;
import com.google.android.exoplayer2.source.MediaLoadData;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.MediaSourceFactory;
import com.google.android.exoplayer2.source.chunk.ChunkSampleStream;
import com.google.android.exoplayer2.source.dash.DashChunkSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.source.dash.PlayerEmsgHandler;
import com.google.android.exoplayer2.source.dash.manifest.AdaptationSet;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.source.dash.manifest.DashManifestParser;
import com.google.android.exoplayer2.source.dash.manifest.Period;
import com.google.android.exoplayer2.source.dash.manifest.Representation;
import com.google.android.exoplayer2.source.dash.manifest.UtcTimingElement;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.SntpClient;
import com.google.android.exoplayer2.util.Util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DashMediaSource extends BaseMediaSource {
    private DataSource A;
    /* access modifiers changed from: private */
    public Loader B;
    private TransferListener C;
    private MediaItem.LiveConfiguration D;
    private Uri E;
    private long F;
    final LoadErrorHandlingPolicy a;
    final MediaSourceEventListener.EventDispatcher b;
    final Object c;
    final Runnable d;
    /* access modifiers changed from: package-private */
    public IOException e;
    Handler f;
    Uri g;
    DashManifest h;
    boolean i;
    long j;
    long k;
    int l;
    long m;
    int n;
    private final MediaItem o;
    private final DataSource.Factory p;
    private final DashChunkSource.Factory q;
    private final CompositeSequenceableLoaderFactory r;
    private final DrmSessionManager s;
    private final long t;
    private final ParsingLoadable.Parser u;
    private final ManifestCallback v;
    private final SparseArray w;
    private final Runnable x;
    private final PlayerEmsgHandler.PlayerEmsgCallback y;
    private final LoaderErrorThrower z;

    final class DashTimeline extends Timeline {
        private final long a;
        private final long c;
        private final long d;
        private final int e;
        private final long f;
        private final long g;
        private final long h;
        private final DashManifest i;
        private final MediaItem j;
        private final MediaItem.LiveConfiguration k;

        public DashTimeline(long j2, long j3, long j4, int i2, long j5, long j6, long j7, DashManifest dashManifest, MediaItem mediaItem, MediaItem.LiveConfiguration liveConfiguration) {
            DashManifest dashManifest2 = dashManifest;
            MediaItem.LiveConfiguration liveConfiguration2 = liveConfiguration;
            Assertions.b(dashManifest2.d != (liveConfiguration2 != null) ? false : true);
            this.a = j2;
            this.c = j3;
            this.d = j4;
            this.e = i2;
            this.f = j5;
            this.g = j6;
            this.h = j7;
            this.i = dashManifest2;
            this.j = mediaItem;
            this.k = liveConfiguration2;
        }

        private static boolean a(DashManifest dashManifest) {
            return dashManifest.d && dashManifest.e != -9223372036854775807L && dashManifest.b == -9223372036854775807L;
        }

        public final int a() {
            return 1;
        }

        public final Timeline.Period a(int i2, Timeline.Period period, boolean z) {
            Assertions.a(i2, this.i.a());
            Integer num = null;
            String str = z ? this.i.a(i2).a : null;
            if (z) {
                num = Integer.valueOf(this.e + i2);
            }
            return period.a(str, num, this.i.b(i2), C.b(this.i.a(i2).b - this.i.a(0).b) - this.f);
        }

        public final Timeline.Window a(int i2, Timeline.Window window, long j2) {
            DashSegmentIndex d2;
            Assertions.a(i2, 1);
            long j3 = this.h;
            if (a(this.i)) {
                if (j2 > 0) {
                    j3 += j2;
                    if (j3 > this.g) {
                        j3 = -9223372036854775807L;
                    }
                }
                long j4 = this.f + j3;
                long b = this.i.b(0);
                int i3 = 0;
                while (i3 < this.i.a() - 1 && j4 >= b) {
                    j4 -= b;
                    i3++;
                    b = this.i.b(i3);
                }
                Period a2 = this.i.a(i3);
                int size = a2.c.size();
                int i4 = 0;
                while (true) {
                    if (i4 >= size) {
                        i4 = -1;
                        break;
                    } else if (((AdaptationSet) a2.c.get(i4)).b == 2) {
                        break;
                    } else {
                        i4++;
                    }
                }
                if (!(i4 == -1 || (d2 = ((Representation) ((AdaptationSet) a2.c.get(i4)).c.get(0)).d()) == null || d2.c(b) == 0)) {
                    j3 = (j3 + d2.a(d2.a(j4, b))) - j4;
                }
            }
            long j5 = j3;
            Object obj = Timeline.Window.a;
            MediaItem mediaItem = this.j;
            DashManifest dashManifest = this.i;
            return window.a(obj, mediaItem, dashManifest, this.a, this.c, this.d, true, a(dashManifest), this.k, j5, this.g, this.i.a() - 1, this.f);
        }

        public final Object a(int i2) {
            Assertions.a(i2, this.i.a());
            return Integer.valueOf(this.e + i2);
        }

        public final int b() {
            return this.i.a();
        }

        public final int c(Object obj) {
            int intValue;
            if ((obj instanceof Integer) && (intValue = ((Integer) obj).intValue() - this.e) >= 0 && intValue < this.i.a()) {
                return intValue;
            }
            return -1;
        }
    }

    final class DefaultPlayerEmsgCallback implements PlayerEmsgHandler.PlayerEmsgCallback {
        private DefaultPlayerEmsgCallback() {
        }

        /* synthetic */ DefaultPlayerEmsgCallback(DashMediaSource dashMediaSource, byte b) {
            this();
        }

        public final void a() {
            DashMediaSource dashMediaSource = DashMediaSource.this;
            dashMediaSource.f.removeCallbacks(dashMediaSource.d);
            dashMediaSource.j();
        }

        public final void a(long j) {
            DashMediaSource dashMediaSource = DashMediaSource.this;
            if (dashMediaSource.m == -9223372036854775807L || dashMediaSource.m < j) {
                dashMediaSource.m = j;
            }
        }
    }

    public final class Factory implements MediaSourceFactory {
        private final DashChunkSource.Factory a;
        private final DataSource.Factory b;
        private DrmSessionManagerProvider c;
        private CompositeSequenceableLoaderFactory d;
        private LoadErrorHandlingPolicy e;
        private long f;
        private long g;
        private List h;

        private Factory(DashChunkSource.Factory factory, DataSource.Factory factory2) {
            this.a = (DashChunkSource.Factory) Assertions.b((Object) factory);
            this.b = factory2;
            this.c = new DefaultDrmSessionManagerProvider();
            this.e = new DefaultLoadErrorHandlingPolicy();
            this.f = -9223372036854775807L;
            this.g = 30000;
            this.d = new DefaultCompositeSequenceableLoaderFactory();
            this.h = Collections.emptyList();
        }

        public Factory(DataSource.Factory factory) {
            this(new DefaultDashChunkSource.Factory(factory), factory);
        }

        public final /* synthetic */ MediaSource a(MediaItem mediaItem) {
            Assertions.b((Object) mediaItem.b);
            ParsingLoadable.Parser dashManifestParser = new DashManifestParser();
            List list = mediaItem.b.e.isEmpty() ? this.h : mediaItem.b.e;
            ParsingLoadable.Parser filteringManifestParser = !list.isEmpty() ? new FilteringManifestParser(dashManifestParser, list) : dashManifestParser;
            boolean z = true;
            boolean z2 = mediaItem.b.e.isEmpty() && !list.isEmpty();
            if (mediaItem.c.a != -9223372036854775807L || this.f == -9223372036854775807L) {
                z = false;
            }
            if (z2 || z) {
                MediaItem.Builder a2 = mediaItem.a();
                if (z2) {
                    a2.a(list);
                }
                if (z) {
                    a2.c = this.f;
                }
                mediaItem = a2.a();
            }
            MediaItem mediaItem2 = mediaItem;
            return new DashMediaSource(mediaItem2, this.b, filteringManifestParser, this.a, this.d, this.c.a(mediaItem2), this.e, this.g, (byte) 0);
        }
    }

    final class Iso8601Parser implements ParsingLoadable.Parser {
        private static final Pattern a = Pattern.compile("(.+?)(Z|((\\+|-|−)(\\d\\d)(:?(\\d\\d))?))");

        Iso8601Parser() {
        }

        private static Long a(InputStream inputStream) {
            String readLine = new BufferedReader(new InputStreamReader(inputStream, aC.c)).readLine();
            try {
                Matcher matcher = a.matcher(readLine);
                if (!matcher.matches()) {
                    String valueOf = String.valueOf(readLine);
                    throw new ParserException(valueOf.length() != 0 ? "Couldn't parse timestamp: ".concat(valueOf) : new String("Couldn't parse timestamp: "));
                }
                String group = matcher.group(1);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                long time = simpleDateFormat.parse(group).getTime();
                if (!"Z".equals(matcher.group(2))) {
                    long j = "+".equals(matcher.group(4)) ? 1 : -1;
                    long parseLong = Long.parseLong(matcher.group(5));
                    String group2 = matcher.group(7);
                    time -= j * ((((parseLong * 60) + (TextUtils.isEmpty(group2) ? 0 : Long.parseLong(group2))) * 60) * 1000);
                }
                return Long.valueOf(time);
            } catch (ParseException e) {
                throw new ParserException((Throwable) e);
            }
        }

        public final /* bridge */ /* synthetic */ Object a(Uri uri, InputStream inputStream) {
            return a(inputStream);
        }
    }

    final class ManifestCallback implements Loader.Callback {
        private ManifestCallback() {
        }

        /* synthetic */ ManifestCallback(DashMediaSource dashMediaSource, byte b) {
            this();
        }

        public final /* synthetic */ Loader.LoadErrorAction a(Loader.Loadable loadable, IOException iOException, int i) {
            ParsingLoadable parsingLoadable = (ParsingLoadable) loadable;
            DashMediaSource dashMediaSource = DashMediaSource.this;
            LoadEventInfo loadEventInfo = new LoadEventInfo((byte) 0);
            new MediaLoadData(parsingLoadable.b);
            long b = dashMediaSource.a.b(new LoadErrorHandlingPolicy.LoadErrorInfo(iOException, i));
            Loader.LoadErrorAction a2 = b == -9223372036854775807L ? Loader.c : Loader.a(false, b);
            boolean z = !a2.a();
            dashMediaSource.b.a(loadEventInfo, parsingLoadable.b, iOException, z);
            if (z) {
                dashMediaSource.a.a();
            }
            return a2;
        }

        /* JADX WARNING: Removed duplicated region for block: B:22:0x009d  */
        /* JADX WARNING: Removed duplicated region for block: B:28:0x00c5  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final /* synthetic */ void a(com.google.android.exoplayer2.upstream.Loader.Loadable r17, long r18, long r20) {
            /*
                r16 = this;
                r0 = r18
                r2 = r17
                com.google.android.exoplayer2.upstream.ParsingLoadable r2 = (com.google.android.exoplayer2.upstream.ParsingLoadable) r2
                r3 = r16
                com.google.android.exoplayer2.source.dash.DashMediaSource r4 = com.google.android.exoplayer2.source.dash.DashMediaSource.this
                com.google.android.exoplayer2.source.LoadEventInfo r5 = new com.google.android.exoplayer2.source.LoadEventInfo
                r6 = 0
                r5.<init>(r6)
                com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy r7 = r4.a
                r7.a()
                com.google.android.exoplayer2.source.MediaSourceEventListener$EventDispatcher r7 = r4.b
                int r8 = r2.b
                r7.b((com.google.android.exoplayer2.source.LoadEventInfo) r5, (int) r8)
                java.lang.Object r5 = r2.d
                com.google.android.exoplayer2.source.dash.manifest.DashManifest r5 = (com.google.android.exoplayer2.source.dash.manifest.DashManifest) r5
                com.google.android.exoplayer2.source.dash.manifest.DashManifest r7 = r4.h
                if (r7 != 0) goto L_0x0026
                r7 = 0
                goto L_0x002c
            L_0x0026:
                com.google.android.exoplayer2.source.dash.manifest.DashManifest r7 = r4.h
                int r7 = r7.a()
            L_0x002c:
                com.google.android.exoplayer2.source.dash.manifest.Period r8 = r5.a((int) r6)
                long r8 = r8.b
                r10 = 0
            L_0x0033:
                if (r10 >= r7) goto L_0x0044
                com.google.android.exoplayer2.source.dash.manifest.DashManifest r11 = r4.h
                com.google.android.exoplayer2.source.dash.manifest.Period r11 = r11.a((int) r10)
                long r11 = r11.b
                int r13 = (r11 > r8 ? 1 : (r11 == r8 ? 0 : -1))
                if (r13 >= 0) goto L_0x0044
                int r10 = r10 + 1
                goto L_0x0033
            L_0x0044:
                boolean r8 = r5.d
                r9 = 1
                if (r8 == 0) goto L_0x00c8
                int r8 = r7 - r10
                int r11 = r5.a()
                if (r8 <= r11) goto L_0x005a
                java.lang.String r8 = "DashMediaSource"
                java.lang.String r11 = "Loaded out of sync manifest"
                com.google.android.exoplayer2.util.Log.c(r8, r11)
            L_0x0058:
                r6 = 1
                goto L_0x009b
            L_0x005a:
                long r11 = r4.m
                r13 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
                int r8 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
                if (r8 == 0) goto L_0x009a
                long r11 = r5.h
                r13 = 1000(0x3e8, double:4.94E-321)
                long r11 = r11 * r13
                long r13 = r4.m
                int r8 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
                if (r8 > 0) goto L_0x009a
                java.lang.String r8 = "DashMediaSource"
                long r11 = r5.h
                long r13 = r4.m
                java.lang.StringBuilder r15 = new java.lang.StringBuilder
                r6 = 73
                r15.<init>(r6)
                java.lang.String r6 = "Loaded stale dynamic manifest: "
                java.lang.StringBuilder r6 = r15.append(r6)
                java.lang.StringBuilder r6 = r6.append(r11)
                java.lang.String r11 = ", "
                java.lang.StringBuilder r6 = r6.append(r11)
                java.lang.StringBuilder r6 = r6.append(r13)
                java.lang.String r6 = r6.toString()
                com.google.android.exoplayer2.util.Log.c(r8, r6)
                goto L_0x0058
            L_0x009a:
                r6 = 0
            L_0x009b:
                if (r6 == 0) goto L_0x00c5
                int r0 = r4.l
                int r1 = r0 + 1
                r4.l = r1
                com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy r1 = r4.a
                int r2 = r2.b
                int r1 = r1.a((int) r2)
                if (r0 >= r1) goto L_0x00bd
                int r0 = r4.l
                int r0 = r0 - r9
                int r0 = r0 * 1000
                r1 = 5000(0x1388, float:7.006E-42)
                int r0 = java.lang.Math.min(r0, r1)
                long r0 = (long) r0
                r4.b((long) r0)
                return
            L_0x00bd:
                com.google.android.exoplayer2.source.dash.DashManifestStaleException r0 = new com.google.android.exoplayer2.source.dash.DashManifestStaleException
                r0.<init>()
                r4.e = r0
                return
            L_0x00c5:
                r6 = 0
                r4.l = r6
            L_0x00c8:
                r4.h = r5
                boolean r5 = r4.i
                com.google.android.exoplayer2.source.dash.manifest.DashManifest r6 = r4.h
                boolean r6 = r6.d
                r5 = r5 & r6
                r4.i = r5
                long r5 = r0 - r20
                r4.j = r5
                r4.k = r0
                java.lang.Object r1 = r4.c
                monitor-enter(r1)
                com.google.android.exoplayer2.upstream.DataSpec r0 = r2.a     // Catch:{ all -> 0x0198 }
                android.net.Uri r0 = r0.a     // Catch:{ all -> 0x0198 }
                android.net.Uri r5 = r4.g     // Catch:{ all -> 0x0198 }
                if (r0 != r5) goto L_0x00e6
                r6 = 1
                goto L_0x00e7
            L_0x00e6:
                r6 = 0
            L_0x00e7:
                if (r6 == 0) goto L_0x00fa
                com.google.android.exoplayer2.source.dash.manifest.DashManifest r0 = r4.h     // Catch:{ all -> 0x0198 }
                android.net.Uri r0 = r0.k     // Catch:{ all -> 0x0198 }
                if (r0 == 0) goto L_0x00f4
                com.google.android.exoplayer2.source.dash.manifest.DashManifest r0 = r4.h     // Catch:{ all -> 0x0198 }
                android.net.Uri r0 = r0.k     // Catch:{ all -> 0x0198 }
                goto L_0x00f8
            L_0x00f4:
                com.google.android.exoplayer2.upstream.StatsDataSource r0 = r2.c     // Catch:{ all -> 0x0198 }
                android.net.Uri r0 = r0.b     // Catch:{ all -> 0x0198 }
            L_0x00f8:
                r4.g = r0     // Catch:{ all -> 0x0198 }
            L_0x00fa:
                monitor-exit(r1)     // Catch:{ all -> 0x0198 }
                if (r7 != 0) goto L_0x018f
                com.google.android.exoplayer2.source.dash.manifest.DashManifest r0 = r4.h
                boolean r0 = r0.d
                if (r0 == 0) goto L_0x018b
                com.google.android.exoplayer2.source.dash.manifest.DashManifest r0 = r4.h
                com.google.android.exoplayer2.source.dash.manifest.UtcTimingElement r0 = r0.i
                if (r0 == 0) goto L_0x0187
                com.google.android.exoplayer2.source.dash.manifest.DashManifest r0 = r4.h
                com.google.android.exoplayer2.source.dash.manifest.UtcTimingElement r0 = r0.i
                java.lang.String r1 = r0.a
                java.lang.String r2 = "urn:mpeg:dash:utc:direct:2014"
                boolean r2 = com.google.android.exoplayer2.util.Util.a((java.lang.Object) r1, (java.lang.Object) r2)
                if (r2 != 0) goto L_0x0175
                java.lang.String r2 = "urn:mpeg:dash:utc:direct:2012"
                boolean r2 = com.google.android.exoplayer2.util.Util.a((java.lang.Object) r1, (java.lang.Object) r2)
                if (r2 == 0) goto L_0x0120
                goto L_0x0175
            L_0x0120:
                java.lang.String r2 = "urn:mpeg:dash:utc:http-iso:2014"
                boolean r2 = com.google.android.exoplayer2.util.Util.a((java.lang.Object) r1, (java.lang.Object) r2)
                if (r2 != 0) goto L_0x016c
                java.lang.String r2 = "urn:mpeg:dash:utc:http-iso:2012"
                boolean r2 = com.google.android.exoplayer2.util.Util.a((java.lang.Object) r1, (java.lang.Object) r2)
                if (r2 == 0) goto L_0x0131
                goto L_0x016c
            L_0x0131:
                java.lang.String r2 = "urn:mpeg:dash:utc:http-xsdate:2014"
                boolean r2 = com.google.android.exoplayer2.util.Util.a((java.lang.Object) r1, (java.lang.Object) r2)
                if (r2 != 0) goto L_0x0162
                java.lang.String r2 = "urn:mpeg:dash:utc:http-xsdate:2012"
                boolean r2 = com.google.android.exoplayer2.util.Util.a((java.lang.Object) r1, (java.lang.Object) r2)
                if (r2 == 0) goto L_0x0142
                goto L_0x0162
            L_0x0142:
                java.lang.String r0 = "urn:mpeg:dash:utc:ntp:2014"
                boolean r0 = com.google.android.exoplayer2.util.Util.a((java.lang.Object) r1, (java.lang.Object) r0)
                if (r0 != 0) goto L_0x015e
                java.lang.String r0 = "urn:mpeg:dash:utc:ntp:2012"
                boolean r0 = com.google.android.exoplayer2.util.Util.a((java.lang.Object) r1, (java.lang.Object) r0)
                if (r0 == 0) goto L_0x0153
                goto L_0x015e
            L_0x0153:
                java.io.IOException r0 = new java.io.IOException
                java.lang.String r1 = "Unsupported UTC timing scheme"
                r0.<init>(r1)
                r4.a((java.io.IOException) r0)
                return
            L_0x015e:
                r4.i()
                return
            L_0x0162:
                com.google.android.exoplayer2.source.dash.DashMediaSource$XsDateTimeParser r1 = new com.google.android.exoplayer2.source.dash.DashMediaSource$XsDateTimeParser
                r2 = 0
                r1.<init>(r2)
                r4.a((com.google.android.exoplayer2.source.dash.manifest.UtcTimingElement) r0, (com.google.android.exoplayer2.upstream.ParsingLoadable.Parser) r1)
                return
            L_0x016c:
                com.google.android.exoplayer2.source.dash.DashMediaSource$Iso8601Parser r1 = new com.google.android.exoplayer2.source.dash.DashMediaSource$Iso8601Parser
                r1.<init>()
                r4.a((com.google.android.exoplayer2.source.dash.manifest.UtcTimingElement) r0, (com.google.android.exoplayer2.upstream.ParsingLoadable.Parser) r1)
                return
            L_0x0175:
                java.lang.String r0 = r0.b     // Catch:{ ParserException -> 0x0182 }
                long r0 = com.google.android.exoplayer2.util.Util.e((java.lang.String) r0)     // Catch:{ ParserException -> 0x0182 }
                long r5 = r4.k     // Catch:{ ParserException -> 0x0182 }
                long r0 = r0 - r5
                r4.a((long) r0)     // Catch:{ ParserException -> 0x0182 }
                return
            L_0x0182:
                r0 = move-exception
                r4.a((java.io.IOException) r0)
                return
            L_0x0187:
                r4.i()
                return
            L_0x018b:
                r4.a((boolean) r9)
                return
            L_0x018f:
                int r0 = r4.n
                int r0 = r0 + r10
                r4.n = r0
                r4.a((boolean) r9)
                return
            L_0x0198:
                r0 = move-exception
                monitor-exit(r1)
                goto L_0x019c
            L_0x019b:
                throw r0
            L_0x019c:
                goto L_0x019b
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.DashMediaSource.ManifestCallback.a(com.google.android.exoplayer2.upstream.Loader$Loadable, long, long):void");
        }

        public final /* bridge */ /* synthetic */ void a(Loader.Loadable loadable, boolean z) {
            DashMediaSource.this.a((ParsingLoadable) loadable);
        }
    }

    final class ManifestLoadErrorThrower implements LoaderErrorThrower {
        ManifestLoadErrorThrower() {
        }

        public final void a() {
            DashMediaSource.this.B.a(Integer.MIN_VALUE);
            if (DashMediaSource.this.e != null) {
                throw DashMediaSource.this.e;
            }
        }
    }

    final class UtcTimestampCallback implements Loader.Callback {
        private UtcTimestampCallback() {
        }

        /* synthetic */ UtcTimestampCallback(DashMediaSource dashMediaSource, byte b) {
            this();
        }

        public final /* synthetic */ Loader.LoadErrorAction a(Loader.Loadable loadable, IOException iOException, int i) {
            DashMediaSource dashMediaSource = DashMediaSource.this;
            dashMediaSource.b.a(new LoadEventInfo((byte) 0), ((ParsingLoadable) loadable).b, iOException, true);
            dashMediaSource.a.a();
            dashMediaSource.a(iOException);
            return Loader.b;
        }

        public final /* synthetic */ void a(Loader.Loadable loadable, long j, long j2) {
            ParsingLoadable parsingLoadable = (ParsingLoadable) loadable;
            DashMediaSource dashMediaSource = DashMediaSource.this;
            LoadEventInfo loadEventInfo = new LoadEventInfo((byte) 0);
            dashMediaSource.a.a();
            dashMediaSource.b.b(loadEventInfo, parsingLoadable.b);
            dashMediaSource.a(((Long) parsingLoadable.d).longValue() - j);
        }

        public final /* bridge */ /* synthetic */ void a(Loader.Loadable loadable, boolean z) {
            DashMediaSource.this.a((ParsingLoadable) loadable);
        }
    }

    final class XsDateTimeParser implements ParsingLoadable.Parser {
        private XsDateTimeParser() {
        }

        /* synthetic */ XsDateTimeParser(byte b) {
            this();
        }

        public final /* synthetic */ Object a(Uri uri, InputStream inputStream) {
            return Long.valueOf(Util.e(new BufferedReader(new InputStreamReader(inputStream)).readLine()));
        }
    }

    static {
        ExoPlayerLibraryInfo.a("goog.exo.dash");
    }

    private DashMediaSource(MediaItem mediaItem, DataSource.Factory factory, ParsingLoadable.Parser parser, DashChunkSource.Factory factory2, CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory, DrmSessionManager drmSessionManager, LoadErrorHandlingPolicy loadErrorHandlingPolicy, long j2) {
        this.o = mediaItem;
        this.D = mediaItem.c;
        this.g = ((MediaItem.PlaybackProperties) Assertions.b((Object) mediaItem.b)).a;
        this.E = mediaItem.b.a;
        this.h = null;
        this.p = factory;
        this.u = parser;
        this.q = factory2;
        this.s = drmSessionManager;
        this.a = loadErrorHandlingPolicy;
        this.t = j2;
        this.r = compositeSequenceableLoaderFactory;
        this.b = a((MediaSource.MediaPeriodId) null);
        this.c = new Object();
        this.w = new SparseArray();
        this.y = new DefaultPlayerEmsgCallback(this, (byte) 0);
        this.m = -9223372036854775807L;
        this.F = -9223372036854775807L;
        this.v = new ManifestCallback(this, (byte) 0);
        this.z = new ManifestLoadErrorThrower();
        this.x = new DashMediaSource$$Lambda$0(this);
        this.d = new DashMediaSource$$Lambda$1(this);
    }

    /* synthetic */ DashMediaSource(MediaItem mediaItem, DataSource.Factory factory, ParsingLoadable.Parser parser, DashChunkSource.Factory factory2, CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory, DrmSessionManager drmSessionManager, LoadErrorHandlingPolicy loadErrorHandlingPolicy, long j2, byte b2) {
        this(mediaItem, factory, parser, factory2, compositeSequenceableLoaderFactory, drmSessionManager, loadErrorHandlingPolicy, j2);
    }

    private static long a(DashManifest dashManifest, long j2) {
        DashSegmentIndex d2;
        DashManifest dashManifest2 = dashManifest;
        int a2 = dashManifest.a() - 1;
        Period a3 = dashManifest2.a(a2);
        long b2 = C.b(a3.b);
        long b3 = dashManifest2.b(a2);
        long b4 = C.b(j2);
        long b5 = C.b(dashManifest2.a);
        long b6 = C.b(5000);
        for (int i2 = 0; i2 < a3.c.size(); i2++) {
            List list = ((AdaptationSet) a3.c.get(i2)).c;
            if (!list.isEmpty() && (d2 = ((Representation) list.get(0)).d()) != null) {
                long e2 = ((b5 + b2) + d2.e(b3, b4)) - b4;
                if (e2 < b6 - 100000 || (e2 > b6 && e2 < b6 + 100000)) {
                    b6 = e2;
                }
            }
        }
        return cP.a(b6, RoundingMode.CEILING);
    }

    private static long a(Period period, long j2, long j3) {
        Period period2 = period;
        long j4 = j2;
        long j5 = j3;
        long b2 = C.b(period2.b);
        boolean b3 = b(period);
        long j6 = b2;
        for (int i2 = 0; i2 < period2.c.size(); i2++) {
            AdaptationSet adaptationSet = (AdaptationSet) period2.c.get(i2);
            List list = adaptationSet.c;
            if ((!b3 || adaptationSet.b != 3) && !list.isEmpty()) {
                DashSegmentIndex d2 = ((Representation) list.get(0)).d();
                if (d2 == null || d2.d(j4, j5) == 0) {
                    return b2;
                }
                j6 = Math.max(j6, d2.a(d2.c(j4, j5)) + b2);
            }
        }
        return j6;
    }

    private void a(long j2, long j3) {
        long a2;
        float f2;
        float f3;
        long a3 = this.o.c.c != -9223372036854775807L ? this.o.c.c : (this.h.j == null || this.h.j.c == -9223372036854775807L) ? C.a(j2) : this.h.j.c;
        if (this.o.c.b != -9223372036854775807L) {
            a2 = this.o.c.b;
        } else if (this.h.j == null || this.h.j.b == -9223372036854775807L) {
            a2 = C.a(j2 - j3);
            if (a2 < 0 && a3 > 0) {
                a2 = 0;
            }
            if (this.h.c != -9223372036854775807L) {
                a2 = Math.min(a2 + this.h.c, a3);
            }
        } else {
            a2 = this.h.j.b;
        }
        long j4 = a2;
        long j5 = this.D.a != -9223372036854775807L ? this.D.a : (this.h.j == null || this.h.j.a == -9223372036854775807L) ? this.h.g != -9223372036854775807L ? this.h.g : this.t : this.h.j.a;
        if (j5 < j4) {
            j5 = j4;
        }
        if (j5 > a3) {
            j5 = Util.a(C.a(j2 - Math.min(5000000, j3 / 2)), j4, a3);
        }
        long j6 = j5;
        float f4 = this.o.c.d != -3.4028235E38f ? this.o.c.d : this.h.j != null ? this.h.j.d : -3.4028235E38f;
        if (this.o.c.e != -3.4028235E38f) {
            f3 = this.o.c.e;
        } else if (this.h.j != null) {
            f3 = this.h.j.e;
        } else {
            f2 = -3.4028235E38f;
            this.D = new MediaItem.LiveConfiguration(j6, j4, a3, f4, f2);
        }
        f2 = f3;
        this.D = new MediaItem.LiveConfiguration(j6, j4, a3, f4, f2);
    }

    private void a(ParsingLoadable parsingLoadable, Loader.Callback callback, int i2) {
        this.B.a(parsingLoadable, callback, i2);
        this.b.a(new LoadEventInfo(), parsingLoadable.b);
    }

    private static boolean a(Period period) {
        for (int i2 = 0; i2 < period.c.size(); i2++) {
            DashSegmentIndex d2 = ((Representation) ((AdaptationSet) period.c.get(i2)).c.get(0)).d();
            if (d2 == null || d2.b()) {
                return true;
            }
        }
        return false;
    }

    private static long b(Period period, long j2, long j3) {
        Period period2 = period;
        long j4 = j2;
        long j5 = j3;
        long b2 = C.b(period2.b);
        boolean b3 = b(period);
        long j6 = LocationRequestCompat.PASSIVE_INTERVAL;
        for (int i2 = 0; i2 < period2.c.size(); i2++) {
            AdaptationSet adaptationSet = (AdaptationSet) period2.c.get(i2);
            List list = adaptationSet.c;
            if ((!b3 || adaptationSet.b != 3) && !list.isEmpty()) {
                DashSegmentIndex d2 = ((Representation) list.get(0)).d();
                if (d2 == null) {
                    return b2 + j4;
                }
                long d3 = d2.d(j4, j5);
                if (d3 == 0) {
                    return b2;
                }
                long c2 = (d2.c(j4, j5) + d3) - 1;
                j6 = Math.min(j6, d2.b(c2, j4) + d2.a(c2) + b2);
            }
        }
        return j6;
    }

    private static boolean b(Period period) {
        for (int i2 = 0; i2 < period.c.size(); i2++) {
            int i3 = ((AdaptationSet) period.c.get(i2)).b;
            if (i3 == 1 || i3 == 2) {
                return true;
            }
        }
        return false;
    }

    public final MediaPeriod a(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j2) {
        MediaSource.MediaPeriodId mediaPeriodId2 = mediaPeriodId;
        int intValue = ((Integer) mediaPeriodId2.a).intValue() - this.n;
        MediaSourceEventListener.EventDispatcher a2 = a(mediaPeriodId2, this.h.a(intValue).b);
        DrmSessionEventListener.EventDispatcher b2 = b(mediaPeriodId);
        int i2 = this.n + intValue;
        DashManifest dashManifest = this.h;
        DashChunkSource.Factory factory = this.q;
        TransferListener transferListener = this.C;
        DrmSessionManager drmSessionManager = this.s;
        LoadErrorHandlingPolicy loadErrorHandlingPolicy = this.a;
        long j3 = this.F;
        LoaderErrorThrower loaderErrorThrower = this.z;
        DashMediaPeriod dashMediaPeriod = new DashMediaPeriod(i2, dashManifest, intValue, factory, transferListener, drmSessionManager, b2, loadErrorHandlingPolicy, a2, j3, loaderErrorThrower, allocator, this.r, this.y);
        this.w.put(dashMediaPeriod.a, dashMediaPeriod);
        return dashMediaPeriod;
    }

    /* access modifiers changed from: package-private */
    public final void a(long j2) {
        this.F = j2;
        a(true);
    }

    public final void a(MediaPeriod mediaPeriod) {
        DashMediaPeriod dashMediaPeriod = (DashMediaPeriod) mediaPeriod;
        PlayerEmsgHandler playerEmsgHandler = dashMediaPeriod.b;
        playerEmsgHandler.i = true;
        playerEmsgHandler.c.removeCallbacksAndMessages((Object) null);
        for (ChunkSampleStream a2 : dashMediaPeriod.d) {
            a2.a((ChunkSampleStream.ReleaseCallback) dashMediaPeriod);
        }
        dashMediaPeriod.c = null;
        this.w.remove(dashMediaPeriod.a);
    }

    /* access modifiers changed from: package-private */
    public final void a(UtcTimingElement utcTimingElement, ParsingLoadable.Parser parser) {
        a(new ParsingLoadable(this.A, Uri.parse(utcTimingElement.b), 5, parser), (Loader.Callback) new UtcTimestampCallback(this, (byte) 0), 1);
    }

    /* access modifiers changed from: package-private */
    public final void a(ParsingLoadable parsingLoadable) {
        LoadEventInfo loadEventInfo = new LoadEventInfo((byte) 0);
        this.a.a();
        this.b.c(loadEventInfo, parsingLoadable.b);
    }

    public final void a(TransferListener transferListener) {
        this.C = transferListener;
        this.s.a();
        this.A = this.p.a();
        this.B = new Loader("DashMediaSource");
        this.f = Util.a();
        j();
    }

    /* access modifiers changed from: package-private */
    public final void a(IOException iOException) {
        Log.b("DashMediaSource", "Failed to resolve time offset.", iOException);
        a(true);
    }

    /* access modifiers changed from: package-private */
    public final void a(boolean z2) {
        long j2;
        long j3;
        boolean z3 = false;
        for (int i2 = 0; i2 < this.w.size(); i2++) {
            int keyAt = this.w.keyAt(i2);
            if (keyAt >= this.n) {
                ((DashMediaPeriod) this.w.valueAt(i2)).a(this.h, keyAt - this.n);
            }
        }
        Period a2 = this.h.a(0);
        int a3 = this.h.a() - 1;
        Period a4 = this.h.a(a3);
        long b2 = this.h.b(a3);
        long b3 = C.b(Util.a(this.F));
        long a5 = a(a2, this.h.b(0), b3);
        long b4 = b(a4, b2, b3);
        boolean z4 = this.h.d && !a(a4);
        if (z4 && this.h.f != -9223372036854775807L) {
            a5 = Math.max(a5, b4 - C.b(this.h.f));
        }
        long j4 = b4 - a5;
        if (this.h.d) {
            if (this.h.a != -9223372036854775807L) {
                z3 = true;
            }
            Assertions.b(z3);
            long b5 = (b3 - C.b(this.h.a)) - a5;
            a(b5, j4);
            j3 = this.h.a + C.a(a5);
            long b6 = b5 - C.b(this.D.a);
            long min = Math.min(5000000, j4 / 2);
            j2 = b6 < min ? min : b6;
        } else {
            j3 = -9223372036854775807L;
            j2 = 0;
        }
        long b7 = a5 - C.b(a2.b);
        long j5 = this.h.a;
        long j6 = this.F;
        int i3 = this.n;
        DashManifest dashManifest = this.h;
        a((Timeline) new DashTimeline(j5, j3, j6, i3, b7, j4, j2, dashManifest, this.o, dashManifest.d ? this.D : null));
        this.f.removeCallbacks(this.d);
        if (z4) {
            this.f.postDelayed(this.d, a(this.h, Util.a(this.F)));
        }
        if (this.i) {
            j();
        } else if (z2 && this.h.d && this.h.e != -9223372036854775807L) {
            long j7 = this.h.e;
            if (j7 == 0) {
                j7 = 5000;
            }
            b(Math.max(0, (this.j + j7) - SystemClock.elapsedRealtime()));
        }
    }

    /* access modifiers changed from: package-private */
    public final void b(long j2) {
        this.f.postDelayed(this.x, j2);
    }

    public final void c() {
        this.i = false;
        this.A = null;
        Loader loader = this.B;
        if (loader != null) {
            loader.a((Loader.ReleaseCallback) null);
            this.B = null;
        }
        this.j = 0;
        this.k = 0;
        this.h = null;
        this.g = this.E;
        this.e = null;
        Handler handler = this.f;
        if (handler != null) {
            handler.removeCallbacksAndMessages((Object) null);
            this.f = null;
        }
        this.F = -9223372036854775807L;
        this.l = 0;
        this.m = -9223372036854775807L;
        this.n = 0;
        this.w.clear();
        this.s.b();
    }

    public final MediaItem g() {
        return this.o;
    }

    public final void h() {
        this.z.a();
    }

    /* access modifiers changed from: package-private */
    public final void i() {
        SntpClient.a(this.B, (SntpClient.InitializationCallback) new SntpClient.InitializationCallback() {
            public final void a() {
                DashMediaSource.this.a(SntpClient.b());
            }

            public final void a(IOException iOException) {
                DashMediaSource.this.a(iOException);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public final void j() {
        Uri uri;
        this.f.removeCallbacks(this.x);
        if (!this.B.b()) {
            if (this.B.c()) {
                this.i = true;
                return;
            }
            synchronized (this.c) {
                uri = this.g;
            }
            this.i = false;
            a(new ParsingLoadable(this.A, uri, 4, this.u), (Loader.Callback) this.v, this.a.a(4));
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void k() {
        a(false);
    }
}
