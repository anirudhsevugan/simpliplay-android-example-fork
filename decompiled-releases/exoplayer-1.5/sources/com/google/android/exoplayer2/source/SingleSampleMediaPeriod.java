package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.trackselection.ExoTrackSelection;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.StatsDataSource;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

final class SingleSampleMediaPeriod implements MediaPeriod, Loader.Callback {
    final Loader a = new Loader("SingleSampleMediaPeriod");
    final Format b;
    final boolean c;
    boolean d;
    byte[] e;
    int f;
    private final DataSpec g;
    private final DataSource.Factory h;
    private final TransferListener i;
    private final LoadErrorHandlingPolicy j;
    /* access modifiers changed from: private */
    public final MediaSourceEventListener.EventDispatcher k;
    private final TrackGroupArray l;
    private final ArrayList m = new ArrayList();
    private final long n;

    final class SampleStreamImpl implements SampleStream {
        int a;
        private boolean b;

        private SampleStreamImpl() {
        }

        /* synthetic */ SampleStreamImpl(SingleSampleMediaPeriod singleSampleMediaPeriod, byte b2) {
            this();
        }

        private void c() {
            if (!this.b) {
                SingleSampleMediaPeriod.this.k.a(MimeTypes.h(SingleSampleMediaPeriod.this.b.l), SingleSampleMediaPeriod.this.b, 0, (Object) null, 0);
                this.b = true;
            }
        }

        public final int a(long j) {
            c();
            if (j <= 0 || this.a == 2) {
                return 0;
            }
            this.a = 2;
            return 1;
        }

        public final int a(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i) {
            c();
            int i2 = this.a;
            if (i2 == 2) {
                decoderInputBuffer.a_(4);
                return -4;
            } else if ((i & 2) != 0 || i2 == 0) {
                formatHolder.b = SingleSampleMediaPeriod.this.b;
                this.a = 1;
                return -5;
            } else if (!SingleSampleMediaPeriod.this.d) {
                return -3;
            } else {
                if (SingleSampleMediaPeriod.this.e == null) {
                    decoderInputBuffer.a_(4);
                    this.a = 2;
                    return -4;
                }
                decoderInputBuffer.a_(1);
                decoderInputBuffer.e = 0;
                if ((i & 4) == 0) {
                    decoderInputBuffer.d(SingleSampleMediaPeriod.this.f);
                    decoderInputBuffer.c.put(SingleSampleMediaPeriod.this.e, 0, SingleSampleMediaPeriod.this.f);
                }
                if ((i & 1) == 0) {
                    this.a = 2;
                }
                return -4;
            }
        }

        public final boolean a() {
            return SingleSampleMediaPeriod.this.d;
        }

        public final void b() {
            if (!SingleSampleMediaPeriod.this.c) {
                SingleSampleMediaPeriod.this.a.a(Integer.MIN_VALUE);
            }
        }
    }

    final class SourceLoadable implements Loader.Loadable {
        private DataSpec a;
        /* access modifiers changed from: private */
        public final StatsDataSource b;
        /* access modifiers changed from: private */
        public byte[] c;

        public SourceLoadable(DataSpec dataSpec, DataSource dataSource) {
            LoadEventInfo.a();
            this.a = dataSpec;
            this.b = new StatsDataSource(dataSource);
        }

        public final void a() {
        }

        public final void b() {
            this.b.a = 0;
            try {
                this.b.a(this.a);
                int i = 0;
                while (i != -1) {
                    int i2 = (int) this.b.a;
                    byte[] bArr = this.c;
                    if (bArr == null) {
                        this.c = new byte[1024];
                    } else if (i2 == bArr.length) {
                        this.c = Arrays.copyOf(bArr, bArr.length << 1);
                    }
                    StatsDataSource statsDataSource = this.b;
                    byte[] bArr2 = this.c;
                    i = statsDataSource.a(bArr2, i2, bArr2.length - i2);
                }
            } finally {
                Util.a((DataSource) this.b);
            }
        }
    }

    public SingleSampleMediaPeriod(DataSpec dataSpec, DataSource.Factory factory, TransferListener transferListener, Format format, long j2, LoadErrorHandlingPolicy loadErrorHandlingPolicy, MediaSourceEventListener.EventDispatcher eventDispatcher, boolean z) {
        this.g = dataSpec;
        this.h = factory;
        this.i = transferListener;
        this.b = format;
        this.n = j2;
        this.j = loadErrorHandlingPolicy;
        this.k = eventDispatcher;
        this.c = z;
        this.l = new TrackGroupArray(new TrackGroup(format));
    }

    public final long a(long j2, SeekParameters seekParameters) {
        return j2;
    }

    public final long a(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j2) {
        for (int i2 = 0; i2 < exoTrackSelectionArr.length; i2++) {
            SampleStream sampleStream = sampleStreamArr[i2];
            if (sampleStream != null && (exoTrackSelectionArr[i2] == null || !zArr[i2])) {
                this.m.remove(sampleStream);
                sampleStreamArr[i2] = null;
            }
            if (sampleStreamArr[i2] == null && exoTrackSelectionArr[i2] != null) {
                SampleStreamImpl sampleStreamImpl = new SampleStreamImpl(this, (byte) 0);
                this.m.add(sampleStreamImpl);
                sampleStreamArr[i2] = sampleStreamImpl;
                zArr2[i2] = true;
            }
        }
        return j2;
    }

    public final /* synthetic */ Loader.LoadErrorAction a(Loader.Loadable loadable, IOException iOException, int i2) {
        Loader.LoadErrorAction loadErrorAction;
        IOException iOException2 = iOException;
        int i3 = i2;
        StatsDataSource unused = ((SourceLoadable) loadable).b;
        LoadEventInfo loadEventInfo = new LoadEventInfo((byte) 0);
        new MediaLoadData(1, -1, this.b, 0, (Object) null, 0, C.a(this.n));
        long b2 = this.j.b(new LoadErrorHandlingPolicy.LoadErrorInfo(iOException2, i3));
        boolean z = b2 == -9223372036854775807L || i3 >= this.j.a(1);
        if (!this.c || !z) {
            loadErrorAction = b2 != -9223372036854775807L ? Loader.a(false, b2) : Loader.c;
        } else {
            Log.a("SingleSampleMediaPeriod", "Loading failed, treating as end-of-stream.", iOException2);
            this.d = true;
            loadErrorAction = Loader.b;
        }
        Loader.LoadErrorAction loadErrorAction2 = loadErrorAction;
        boolean z2 = !loadErrorAction2.a();
        this.k.a(loadEventInfo, 1, -1, this.b, 0, (Object) null, 0, this.n, iOException, z2);
        if (z2) {
            this.j.a();
        }
        return loadErrorAction2;
    }

    public final void a() {
    }

    public final void a(long j2, boolean z) {
    }

    public final void a(MediaPeriod.Callback callback, long j2) {
        callback.a(this);
    }

    public final /* synthetic */ void a(Loader.Loadable loadable, long j2, long j3) {
        SourceLoadable sourceLoadable = (SourceLoadable) loadable;
        this.f = (int) sourceLoadable.b.a;
        this.e = (byte[]) Assertions.b((Object) sourceLoadable.c);
        this.d = true;
        StatsDataSource unused = sourceLoadable.b;
        LoadEventInfo loadEventInfo = new LoadEventInfo((byte) 0);
        this.j.a();
        this.k.b(loadEventInfo, 1, -1, this.b, 0, (Object) null, 0, this.n);
    }

    public final /* synthetic */ void a(Loader.Loadable loadable, boolean z) {
        StatsDataSource unused = ((SourceLoadable) loadable).b;
        LoadEventInfo loadEventInfo = new LoadEventInfo((byte) 0);
        this.j.a();
        this.k.c(loadEventInfo, 1, -1, (Format) null, 0, (Object) null, 0, this.n);
    }

    public final void a_(long j2) {
    }

    public final long b(long j2) {
        for (int i2 = 0; i2 < this.m.size(); i2++) {
            SampleStreamImpl sampleStreamImpl = (SampleStreamImpl) this.m.get(i2);
            if (sampleStreamImpl.a == 2) {
                sampleStreamImpl.a = 1;
            }
        }
        return j2;
    }

    public final TrackGroupArray b() {
        return this.l;
    }

    public final long c() {
        return -9223372036854775807L;
    }

    public final boolean c(long j2) {
        if (this.d || this.a.c() || this.a.b()) {
            return false;
        }
        DataSource a2 = this.h.a();
        TransferListener transferListener = this.i;
        if (transferListener != null) {
            a2.a(transferListener);
        }
        this.a.a(new SourceLoadable(this.g, a2), this, this.j.a(1));
        this.k.a(new LoadEventInfo(), 1, -1, this.b, 0, (Object) null, 0, this.n);
        return true;
    }

    public final long d() {
        return this.d ? Long.MIN_VALUE : 0;
    }

    public final long e() {
        return (this.d || this.a.c()) ? Long.MIN_VALUE : 0;
    }

    public final boolean f() {
        return this.a.c();
    }
}
