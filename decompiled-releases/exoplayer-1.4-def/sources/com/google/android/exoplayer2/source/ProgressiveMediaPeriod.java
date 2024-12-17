package com.google.android.exoplayer2.source;

import android.net.Uri;
import android.os.Handler;
import androidx.core.location.LocationRequestCompat;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.source.IcyDataSource;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.SampleQueue;
import com.google.android.exoplayer2.trackselection.ExoTrackSelection;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataReader;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.StatsDataSource;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ConditionVariable;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.appinventor.components.runtime.Component;
import java.io.InterruptedIOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

final class ProgressiveMediaPeriod implements ExtractorOutput, MediaPeriod, SampleQueue.UpstreamFormatChangedListener, Loader.Callback, Loader.ReleaseCallback {
    /* access modifiers changed from: private */
    public static final Map p;
    /* access modifiers changed from: private */
    public static final Format q;
    private final ProgressiveMediaExtractor A;
    private final ConditionVariable B;
    private final Runnable C;
    /* access modifiers changed from: private */
    public final Runnable D;
    private TrackId[] E;
    private boolean F;
    private boolean G;
    private TrackState H;
    private boolean I;
    private boolean J;
    private int K;
    private long L;
    private long M;
    private boolean N;
    private int O;
    final Listener b;
    final Loader c = new Loader("ProgressiveMediaPeriod");
    /* access modifiers changed from: package-private */
    public final Handler d;
    MediaPeriod.Callback e;
    /* access modifiers changed from: package-private */
    public IcyHeaders f;
    SampleQueue[] g;
    boolean h;
    SeekMap i;
    long j;
    boolean k;
    int l;
    long m;
    boolean n;
    boolean o;
    private final Uri r;
    private final DataSource s;
    private final DrmSessionManager t;
    private final LoadErrorHandlingPolicy u;
    private final MediaSourceEventListener.EventDispatcher v;
    private final DrmSessionEventListener.EventDispatcher w;
    private final Allocator x;
    /* access modifiers changed from: private */
    public final String y;
    /* access modifiers changed from: private */
    public final long z;

    final class ExtractingLoadable implements IcyDataSource.Listener, Loader.Loadable {
        /* access modifiers changed from: private */
        public final long a = LoadEventInfo.a();
        private final Uri b;
        /* access modifiers changed from: private */
        public final StatsDataSource c;
        private final ProgressiveMediaExtractor d;
        private final ExtractorOutput e;
        private final ConditionVariable f;
        private final PositionHolder g = new PositionHolder();
        private volatile boolean h;
        private boolean i = true;
        /* access modifiers changed from: private */
        public long j;
        /* access modifiers changed from: private */
        public DataSpec k = a(0);
        /* access modifiers changed from: private */
        public long l = -1;
        private TrackOutput m;
        private boolean n;

        public ExtractingLoadable(Uri uri, DataSource dataSource, ProgressiveMediaExtractor progressiveMediaExtractor, ExtractorOutput extractorOutput, ConditionVariable conditionVariable) {
            this.b = uri;
            this.c = new StatsDataSource(dataSource);
            this.d = progressiveMediaExtractor;
            this.e = extractorOutput;
            this.f = conditionVariable;
        }

        private DataSpec a(long j2) {
            DataSpec.Builder builder = new DataSpec.Builder();
            builder.a = this.b;
            builder.e = j2;
            builder.g = ProgressiveMediaPeriod.this.y;
            builder.h = 6;
            builder.d = ProgressiveMediaPeriod.p;
            return builder.a();
        }

        static /* synthetic */ void a(ExtractingLoadable extractingLoadable, long j2, long j3) {
            extractingLoadable.g.a = j2;
            extractingLoadable.j = j3;
            extractingLoadable.i = true;
            extractingLoadable.n = false;
        }

        public final void a() {
            this.h = true;
        }

        public final void a(ParsableByteArray parsableByteArray) {
            long max = !this.n ? this.j : Math.max(ProgressiveMediaPeriod.this.p(), this.j);
            int a2 = parsableByteArray.a();
            TrackOutput trackOutput = (TrackOutput) Assertions.b((Object) this.m);
            trackOutput.b(parsableByteArray, a2);
            trackOutput.a(max, 1, a2, 0, (TrackOutput.CryptoData) null);
            this.n = true;
        }

        public final void b() {
            int i2 = 0;
            while (i2 == 0 && !this.h) {
                try {
                    long j2 = this.g.a;
                    DataSpec a2 = a(j2);
                    this.k = a2;
                    long a3 = this.c.a(a2);
                    this.l = a3;
                    if (a3 != -1) {
                        this.l = a3 + j2;
                    }
                    IcyHeaders unused = ProgressiveMediaPeriod.this.f = IcyHeaders.a(this.c.b());
                    DataReader dataReader = this.c;
                    if (!(ProgressiveMediaPeriod.this.f == null || ProgressiveMediaPeriod.this.f.b == -1)) {
                        dataReader = new IcyDataSource(this.c, ProgressiveMediaPeriod.this.f.b, this);
                        TrackOutput a4 = ProgressiveMediaPeriod.this.a(new TrackId(0, true));
                        this.m = a4;
                        a4.a(ProgressiveMediaPeriod.q);
                    }
                    ProgressiveMediaExtractor progressiveMediaExtractor = this.d;
                    Uri uri = this.b;
                    Map b2 = this.c.b();
                    long j3 = this.l;
                    long j4 = j2;
                    progressiveMediaExtractor.a(dataReader, uri, b2, j2, j3, this.e);
                    if (ProgressiveMediaPeriod.this.f != null) {
                        this.d.b();
                    }
                    if (this.i) {
                        this.d.a(j4, this.j);
                        this.i = false;
                    }
                    while (true) {
                        long j5 = j4;
                        while (i2 == 0 && !this.h) {
                            this.f.c();
                            i2 = this.d.a(this.g);
                            j4 = this.d.c();
                            if (j4 > ProgressiveMediaPeriod.this.z + j5) {
                                this.f.b();
                                ProgressiveMediaPeriod.this.d.post(ProgressiveMediaPeriod.this.D);
                            }
                        }
                    }
                    if (i2 == 1) {
                        i2 = 0;
                    } else if (this.d.c() != -1) {
                        this.g.a = this.d.c();
                    }
                    Util.a((DataSource) this.c);
                } catch (InterruptedException e2) {
                    throw new InterruptedIOException();
                } catch (Throwable th) {
                    if (!(i2 == 1 || this.d.c() == -1)) {
                        this.g.a = this.d.c();
                    }
                    Util.a((DataSource) this.c);
                    throw th;
                }
            }
        }
    }

    interface Listener {
        void a(long j, boolean z, boolean z2);
    }

    final class SampleStreamImpl implements SampleStream {
        /* access modifiers changed from: private */
        public final int a;

        public SampleStreamImpl(int i) {
            this.a = i;
        }

        public final int a(long j) {
            ProgressiveMediaPeriod progressiveMediaPeriod = ProgressiveMediaPeriod.this;
            int i = this.a;
            if (progressiveMediaPeriod.i()) {
                return 0;
            }
            progressiveMediaPeriod.a(i);
            SampleQueue sampleQueue = progressiveMediaPeriod.g[i];
            int b2 = sampleQueue.b(j, progressiveMediaPeriod.n);
            sampleQueue.d(b2);
            if (b2 == 0) {
                progressiveMediaPeriod.b(i);
            }
            return b2;
        }

        public final int a(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i) {
            ProgressiveMediaPeriod progressiveMediaPeriod = ProgressiveMediaPeriod.this;
            int i2 = this.a;
            if (progressiveMediaPeriod.i()) {
                return -3;
            }
            progressiveMediaPeriod.a(i2);
            int a2 = progressiveMediaPeriod.g[i2].a(formatHolder, decoderInputBuffer, i, progressiveMediaPeriod.n);
            if (a2 == -3) {
                progressiveMediaPeriod.b(i2);
            }
            return a2;
        }

        public final boolean a() {
            ProgressiveMediaPeriod progressiveMediaPeriod = ProgressiveMediaPeriod.this;
            return !progressiveMediaPeriod.i() && progressiveMediaPeriod.g[this.a].b(progressiveMediaPeriod.n);
        }

        public final void b() {
            ProgressiveMediaPeriod progressiveMediaPeriod = ProgressiveMediaPeriod.this;
            progressiveMediaPeriod.g[this.a].d();
            progressiveMediaPeriod.h();
        }
    }

    final class TrackId {
        public final boolean a;
        private int b;

        public TrackId(int i, boolean z) {
            this.b = i;
            this.a = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                TrackId trackId = (TrackId) obj;
                return this.b == trackId.b && this.a == trackId.a;
            }
        }

        public final int hashCode() {
            return (this.b * 31) + (this.a ? 1 : 0);
        }
    }

    final class TrackState {
        public final TrackGroupArray a;
        public final boolean[] b;
        public final boolean[] c;
        public final boolean[] d;

        public TrackState(TrackGroupArray trackGroupArray, boolean[] zArr) {
            this.a = trackGroupArray;
            this.b = zArr;
            this.c = new boolean[trackGroupArray.length];
            this.d = new boolean[trackGroupArray.length];
        }
    }

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("Icy-MetaData", Component.TYPEFACE_SANSSERIF);
        p = Collections.unmodifiableMap(hashMap);
        Format.Builder builder = new Format.Builder();
        builder.a = "icy";
        builder.k = "application/x-icy";
        q = builder.a();
    }

    public ProgressiveMediaPeriod(Uri uri, DataSource dataSource, ProgressiveMediaExtractor progressiveMediaExtractor, DrmSessionManager drmSessionManager, DrmSessionEventListener.EventDispatcher eventDispatcher, LoadErrorHandlingPolicy loadErrorHandlingPolicy, MediaSourceEventListener.EventDispatcher eventDispatcher2, Listener listener, Allocator allocator, String str, int i2) {
        this.r = uri;
        this.s = dataSource;
        this.t = drmSessionManager;
        this.w = eventDispatcher;
        this.u = loadErrorHandlingPolicy;
        this.v = eventDispatcher2;
        this.b = listener;
        this.x = allocator;
        this.y = str;
        this.z = (long) i2;
        this.A = progressiveMediaExtractor;
        this.B = new ConditionVariable();
        this.C = new ProgressiveMediaPeriod$$Lambda$0(this);
        this.D = new ProgressiveMediaPeriod$$Lambda$1(this);
        this.d = Util.a();
        this.E = new TrackId[0];
        this.g = new SampleQueue[0];
        this.M = -9223372036854775807L;
        this.m = -1;
        this.j = -9223372036854775807L;
        this.l = 1;
    }

    private void a(ExtractingLoadable extractingLoadable) {
        if (this.m == -1) {
            this.m = extractingLoadable.l;
        }
    }

    private boolean a(boolean[] zArr, long j2) {
        int length = this.g.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (!this.g[i2].a(j2, false) && (zArr[i2] || !this.G)) {
                return false;
            }
        }
        return true;
    }

    private void n() {
        ExtractingLoadable extractingLoadable = new ExtractingLoadable(this.r, this.s, this.A, this, this.B);
        if (this.h) {
            Assertions.b(q());
            long j2 = this.j;
            if (j2 == -9223372036854775807L || this.M <= j2) {
                ExtractingLoadable.a(extractingLoadable, ((SeekMap) Assertions.b((Object) this.i)).a(this.M).a.c, this.M);
                for (SampleQueue sampleQueue : this.g) {
                    sampleQueue.d = this.M;
                }
                this.M = -9223372036854775807L;
            } else {
                this.n = true;
                this.M = -9223372036854775807L;
                return;
            }
        }
        this.O = o();
        this.c.a(extractingLoadable, this, this.u.a(this.l));
        DataSpec unused = extractingLoadable.k;
        MediaSourceEventListener.EventDispatcher eventDispatcher = this.v;
        long unused2 = extractingLoadable.a;
        eventDispatcher.a(new LoadEventInfo(), 1, -1, (Format) null, 0, (Object) null, extractingLoadable.j, this.j);
    }

    private int o() {
        int i2 = 0;
        for (SampleQueue b2 : this.g) {
            i2 += b2.b();
        }
        return i2;
    }

    /* access modifiers changed from: private */
    public long p() {
        long j2 = Long.MIN_VALUE;
        for (SampleQueue h2 : this.g) {
            j2 = Math.max(j2, h2.h());
        }
        return j2;
    }

    private boolean q() {
        return this.M != -9223372036854775807L;
    }

    private void r() {
        Assertions.b(this.h);
        Assertions.b((Object) this.H);
        Assertions.b((Object) this.i);
    }

    public final long a(long j2, SeekParameters seekParameters) {
        r();
        if (!this.i.a()) {
            return 0;
        }
        SeekMap.SeekPoints a = this.i.a(j2);
        return seekParameters.a(j2, a.a.b, a.b.b);
    }

    public final long a(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j2) {
        ExoTrackSelection exoTrackSelection;
        r();
        TrackGroupArray trackGroupArray = this.H.a;
        boolean[] zArr3 = this.H.c;
        int i2 = this.K;
        int i3 = 0;
        for (int i4 = 0; i4 < exoTrackSelectionArr.length; i4++) {
            SampleStreamImpl sampleStreamImpl = sampleStreamArr[i4];
            if (sampleStreamImpl != null && (exoTrackSelectionArr[i4] == null || !zArr[i4])) {
                int a = sampleStreamImpl.a;
                Assertions.b(zArr3[a]);
                this.K--;
                zArr3[a] = false;
                sampleStreamArr[i4] = null;
            }
        }
        boolean z2 = !this.I ? j2 != 0 : i2 == 0;
        for (int i5 = 0; i5 < exoTrackSelectionArr.length; i5++) {
            if (sampleStreamArr[i5] == null && (exoTrackSelection = exoTrackSelectionArr[i5]) != null) {
                Assertions.b(exoTrackSelection.f() == 1);
                Assertions.b(exoTrackSelection.b(0) == 0);
                int a2 = trackGroupArray.a(exoTrackSelection.e());
                Assertions.b(!zArr3[a2]);
                this.K++;
                zArr3[a2] = true;
                sampleStreamArr[i5] = new SampleStreamImpl(a2);
                zArr2[i5] = true;
                if (!z2) {
                    SampleQueue sampleQueue = this.g[a2];
                    z2 = !sampleQueue.a(j2, true) && sampleQueue.e() != 0;
                }
            }
        }
        if (this.K == 0) {
            this.N = false;
            this.J = false;
            if (this.c.c()) {
                SampleQueue[] sampleQueueArr = this.g;
                int length = sampleQueueArr.length;
                while (i3 < length) {
                    sampleQueueArr[i3].k();
                    i3++;
                }
                this.c.d();
            } else {
                for (SampleQueue a3 : this.g) {
                    a3.a(false);
                }
            }
        } else if (z2) {
            j2 = b(j2);
            while (i3 < sampleStreamArr.length) {
                if (sampleStreamArr[i3] != null) {
                    zArr2[i3] = true;
                }
                i3++;
            }
        }
        this.I = true;
        return j2;
    }

    public final TrackOutput a(int i2, int i3) {
        return a(new TrackId(i2, false));
    }

    /* access modifiers changed from: package-private */
    public final TrackOutput a(TrackId trackId) {
        int length = this.g.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (trackId.equals(this.E[i2])) {
                return this.g[i2];
            }
        }
        SampleQueue a = SampleQueue.a(this.x, this.d.getLooper(), this.t, this.w);
        a.b = this;
        int i3 = length + 1;
        TrackId[] trackIdArr = (TrackId[]) Arrays.copyOf(this.E, i3);
        trackIdArr[length] = trackId;
        this.E = (TrackId[]) Util.a((Object[]) trackIdArr);
        SampleQueue[] sampleQueueArr = (SampleQueue[]) Arrays.copyOf(this.g, i3);
        sampleQueueArr[length] = a;
        this.g = (SampleQueue[]) Util.a((Object[]) sampleQueueArr);
        return a;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ com.google.android.exoplayer2.upstream.Loader.LoadErrorAction a(com.google.android.exoplayer2.upstream.Loader.Loadable r18, java.io.IOException r19, int r20) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            com.google.android.exoplayer2.source.ProgressiveMediaPeriod$ExtractingLoadable r1 = (com.google.android.exoplayer2.source.ProgressiveMediaPeriod.ExtractingLoadable) r1
            r0.a((com.google.android.exoplayer2.source.ProgressiveMediaPeriod.ExtractingLoadable) r1)
            com.google.android.exoplayer2.upstream.StatsDataSource unused = r1.c
            com.google.android.exoplayer2.source.LoadEventInfo r3 = new com.google.android.exoplayer2.source.LoadEventInfo
            long unused = r1.a
            com.google.android.exoplayer2.upstream.DataSpec unused = r1.k
            r2 = 0
            r3.<init>(r2)
            com.google.android.exoplayer2.source.MediaLoadData r4 = new com.google.android.exoplayer2.source.MediaLoadData
            r5 = 1
            r6 = -1
            r7 = 0
            r8 = 0
            r9 = 0
            long r10 = r1.j
            long r10 = com.google.android.exoplayer2.C.a((long) r10)
            long r12 = r0.j
            long r12 = com.google.android.exoplayer2.C.a((long) r12)
            r4.<init>(r5, r6, r7, r8, r9, r10, r12)
            com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy r4 = r0.u
            com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy$LoadErrorInfo r5 = new com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy$LoadErrorInfo
            r13 = r19
            r6 = r20
            r5.<init>(r13, r6)
            long r4 = r4.b(r5)
            r6 = 1
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r9 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r9 != 0) goto L_0x004d
            com.google.android.exoplayer2.upstream.Loader$LoadErrorAction r2 = com.google.android.exoplayer2.upstream.Loader.c
        L_0x004b:
            r15 = r2
            goto L_0x00a3
        L_0x004d:
            int r9 = r17.o()
            int r10 = r0.O
            if (r9 <= r10) goto L_0x0057
            r10 = 1
            goto L_0x0058
        L_0x0057:
            r10 = 0
        L_0x0058:
            long r11 = r0.m
            r14 = -1
            int r16 = (r11 > r14 ? 1 : (r11 == r14 ? 0 : -1))
            if (r16 != 0) goto L_0x0096
            com.google.android.exoplayer2.extractor.SeekMap r11 = r0.i
            if (r11 == 0) goto L_0x006d
            long r11 = r11.b()
            int r14 = (r11 > r7 ? 1 : (r11 == r7 ? 0 : -1))
            if (r14 == 0) goto L_0x006d
            goto L_0x0096
        L_0x006d:
            boolean r7 = r0.h
            if (r7 == 0) goto L_0x007a
            boolean r7 = r17.i()
            if (r7 != 0) goto L_0x007a
            r0.N = r6
            goto L_0x0099
        L_0x007a:
            boolean r7 = r0.h
            r0.J = r7
            r7 = 0
            r0.L = r7
            r0.O = r2
            com.google.android.exoplayer2.source.SampleQueue[] r9 = r0.g
            int r11 = r9.length
            r12 = 0
        L_0x0088:
            if (r12 >= r11) goto L_0x0092
            r14 = r9[r12]
            r14.a((boolean) r2)
            int r12 = r12 + 1
            goto L_0x0088
        L_0x0092:
            com.google.android.exoplayer2.source.ProgressiveMediaPeriod.ExtractingLoadable.a(r1, r7, r7)
            goto L_0x0098
        L_0x0096:
            r0.O = r9
        L_0x0098:
            r2 = 1
        L_0x0099:
            if (r2 == 0) goto L_0x00a0
            com.google.android.exoplayer2.upstream.Loader$LoadErrorAction r2 = com.google.android.exoplayer2.upstream.Loader.a((boolean) r10, (long) r4)
            goto L_0x004b
        L_0x00a0:
            com.google.android.exoplayer2.upstream.Loader$LoadErrorAction r2 = com.google.android.exoplayer2.upstream.Loader.b
            goto L_0x004b
        L_0x00a3:
            boolean r2 = r15.a()
            r16 = r2 ^ 1
            com.google.android.exoplayer2.source.MediaSourceEventListener$EventDispatcher r2 = r0.v
            r4 = 1
            r5 = -1
            r6 = 0
            r7 = 0
            r8 = 0
            long r9 = r1.j
            long r11 = r0.j
            r13 = r19
            r14 = r16
            r2.a(r3, r4, r5, r6, r7, r8, r9, r11, r13, r14)
            if (r16 == 0) goto L_0x00c7
            com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy r2 = r0.u
            long unused = r1.a
            r2.a()
        L_0x00c7:
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.ProgressiveMediaPeriod.a(com.google.android.exoplayer2.upstream.Loader$Loadable, java.io.IOException, int):com.google.android.exoplayer2.upstream.Loader$LoadErrorAction");
    }

    public final void a() {
        h();
        if (this.n && !this.h) {
            throw new ParserException("Loading finished before preparation is complete.");
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(int i2) {
        r();
        boolean[] zArr = this.H.d;
        if (!zArr[i2]) {
            Format format = this.H.a.b[i2].b[0];
            this.v.a(MimeTypes.h(format.l), format, 0, (Object) null, this.L);
            zArr[i2] = true;
        }
    }

    public final void a(long j2, boolean z2) {
        r();
        if (!q()) {
            boolean[] zArr = this.H.c;
            int length = this.g.length;
            for (int i2 = 0; i2 < length; i2++) {
                this.g[i2].a(j2, z2, zArr[i2]);
            }
        }
    }

    public final void a(SeekMap seekMap) {
        this.d.post(new ProgressiveMediaPeriod$$Lambda$2(this, seekMap));
    }

    public final void a(MediaPeriod.Callback callback, long j2) {
        this.e = callback;
        this.B.a();
        n();
    }

    public final /* synthetic */ void a(Loader.Loadable loadable, long j2, long j3) {
        SeekMap seekMap;
        ExtractingLoadable extractingLoadable = (ExtractingLoadable) loadable;
        if (this.j == -9223372036854775807L && (seekMap = this.i) != null) {
            boolean a = seekMap.a();
            long p2 = p();
            long j4 = p2 == Long.MIN_VALUE ? 0 : p2 + 10000;
            this.j = j4;
            this.b.a(j4, a, this.k);
        }
        StatsDataSource unused = extractingLoadable.c;
        long unused2 = extractingLoadable.a;
        DataSpec unused3 = extractingLoadable.k;
        LoadEventInfo loadEventInfo = new LoadEventInfo((byte) 0);
        LoadErrorHandlingPolicy loadErrorHandlingPolicy = this.u;
        long unused4 = extractingLoadable.a;
        loadErrorHandlingPolicy.a();
        this.v.b(loadEventInfo, 1, -1, (Format) null, 0, (Object) null, extractingLoadable.j, this.j);
        a(extractingLoadable);
        this.n = true;
        ((MediaPeriod.Callback) Assertions.b((Object) this.e)).a(this);
    }

    public final /* synthetic */ void a(Loader.Loadable loadable, boolean z2) {
        ExtractingLoadable extractingLoadable = (ExtractingLoadable) loadable;
        StatsDataSource unused = extractingLoadable.c;
        long unused2 = extractingLoadable.a;
        DataSpec unused3 = extractingLoadable.k;
        LoadEventInfo loadEventInfo = new LoadEventInfo((byte) 0);
        LoadErrorHandlingPolicy loadErrorHandlingPolicy = this.u;
        long unused4 = extractingLoadable.a;
        loadErrorHandlingPolicy.a();
        this.v.c(loadEventInfo, 1, -1, (Format) null, 0, (Object) null, extractingLoadable.j, this.j);
        if (!z2) {
            a(extractingLoadable);
            for (SampleQueue a : this.g) {
                a.a(false);
            }
            if (this.K > 0) {
                ((MediaPeriod.Callback) Assertions.b((Object) this.e)).a(this);
            }
        }
    }

    public final void a_(long j2) {
    }

    public final long b(long j2) {
        r();
        boolean[] zArr = this.H.b;
        if (!this.i.a()) {
            j2 = 0;
        }
        this.J = false;
        this.L = j2;
        if (q()) {
            this.M = j2;
            return j2;
        } else if (this.l != 7 && a(zArr, j2)) {
            return j2;
        } else {
            this.N = false;
            this.M = j2;
            this.n = false;
            if (this.c.c()) {
                for (SampleQueue k2 : this.g) {
                    k2.k();
                }
                this.c.d();
            } else {
                this.c.d = null;
                for (SampleQueue a : this.g) {
                    a.a(false);
                }
            }
            return j2;
        }
    }

    public final TrackGroupArray b() {
        r();
        return this.H.a;
    }

    /* access modifiers changed from: package-private */
    public final void b(int i2) {
        r();
        boolean[] zArr = this.H.b;
        if (this.N && zArr[i2] && !this.g[i2].b(false)) {
            this.M = 0;
            this.N = false;
            this.J = true;
            this.L = 0;
            this.O = 0;
            for (SampleQueue a : this.g) {
                a.a(false);
            }
            ((MediaPeriod.Callback) Assertions.b((Object) this.e)).a(this);
        }
    }

    public final long c() {
        if (!this.J) {
            return -9223372036854775807L;
        }
        if (!this.n && o() <= this.O) {
            return -9223372036854775807L;
        }
        this.J = false;
        return this.L;
    }

    public final boolean c(long j2) {
        if (this.n || this.c.b() || this.N) {
            return false;
        }
        if (this.h && this.K == 0) {
            return false;
        }
        boolean a = this.B.a();
        if (this.c.c()) {
            return a;
        }
        n();
        return true;
    }

    public final void c_() {
        this.F = true;
        this.d.post(this.C);
    }

    public final long d() {
        long j2;
        r();
        boolean[] zArr = this.H.b;
        if (this.n) {
            return Long.MIN_VALUE;
        }
        if (q()) {
            return this.M;
        }
        if (this.G) {
            int length = this.g.length;
            j2 = Long.MAX_VALUE;
            for (int i2 = 0; i2 < length; i2++) {
                if (zArr[i2] && !this.g[i2].i()) {
                    j2 = Math.min(j2, this.g[i2].h());
                }
            }
        } else {
            j2 = Long.MAX_VALUE;
        }
        if (j2 == LocationRequestCompat.PASSIVE_INTERVAL) {
            j2 = p();
        }
        return j2 == Long.MIN_VALUE ? this.L : j2;
    }

    public final long e() {
        if (this.K == 0) {
            return Long.MIN_VALUE;
        }
        return d();
    }

    public final boolean f() {
        return this.c.c() && this.B.e();
    }

    public final void g() {
        for (SampleQueue a : this.g) {
            a.a();
        }
        this.A.a();
    }

    /* access modifiers changed from: package-private */
    public final void h() {
        this.c.a(this.u.a(this.l));
    }

    /* access modifiers changed from: package-private */
    public final boolean i() {
        return this.J || q();
    }

    public final void j() {
        this.d.post(this.C);
    }

    /* access modifiers changed from: package-private */
    public final void k() {
        if (!this.o && !this.h && this.F && this.i != null) {
            SampleQueue[] sampleQueueArr = this.g;
            int length = sampleQueueArr.length;
            int i2 = 0;
            while (i2 < length) {
                if (sampleQueueArr[i2].g() != null) {
                    i2++;
                } else {
                    return;
                }
            }
            this.B.b();
            int length2 = this.g.length;
            TrackGroup[] trackGroupArr = new TrackGroup[length2];
            boolean[] zArr = new boolean[length2];
            for (int i3 = 0; i3 < length2; i3++) {
                Format format = (Format) Assertions.b((Object) this.g[i3].g());
                String str = format.l;
                boolean a = MimeTypes.a(str);
                boolean z2 = a || MimeTypes.b(str);
                zArr[i3] = z2;
                this.G = z2 | this.G;
                IcyHeaders icyHeaders = this.f;
                if (icyHeaders != null) {
                    if (a || this.E[i3].a) {
                        Metadata metadata = format.j;
                        Metadata metadata2 = metadata == null ? new Metadata(icyHeaders) : metadata.a(icyHeaders);
                        Format.Builder a2 = format.a();
                        a2.i = metadata2;
                        format = a2.a();
                    }
                    if (a && format.f == -1 && format.g == -1 && icyHeaders.a != -1) {
                        Format.Builder a3 = format.a();
                        a3.f = icyHeaders.a;
                        format = a3.a();
                    }
                }
                trackGroupArr[i3] = new TrackGroup(format.a(this.t.a(format)));
            }
            this.H = new TrackState(new TrackGroupArray(trackGroupArr), zArr);
            this.h = true;
            ((MediaPeriod.Callback) Assertions.b((Object) this.e)).a(this);
        }
    }
}
