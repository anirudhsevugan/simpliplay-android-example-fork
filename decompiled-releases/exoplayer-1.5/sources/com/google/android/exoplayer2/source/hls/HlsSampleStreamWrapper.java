package com.google.android.exoplayer2.source.hls;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.SparseIntArray;
import com.dreamers.exoplayercore.repack.C0000a;
import com.dreamers.exoplayercore.repack.bG;
import com.dreamers.exoplayercore.repack.bH;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.extractor.DummyTrackOutput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.TrackOutput$$CC;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.emsg.EventMessage;
import com.google.android.exoplayer2.metadata.emsg.EventMessageDecoder;
import com.google.android.exoplayer2.metadata.id3.PrivFrame;
import com.google.android.exoplayer2.source.LoadEventInfo;
import com.google.android.exoplayer2.source.MediaLoadData;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.SampleQueue;
import com.google.android.exoplayer2.source.SequenceableLoader;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.chunk.Chunk;
import com.google.android.exoplayer2.source.hls.HlsChunkSource;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataReader;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class HlsSampleStreamWrapper implements ExtractorOutput, SampleQueue.UpstreamFormatChangedListener, SequenceableLoader, Loader.Callback, Loader.ReleaseCallback {
    private static final Set z = Collections.unmodifiableSet(new HashSet(Arrays.asList(new Integer[]{1, 2, 5})));
    private final Callback A;
    private final Allocator B;
    private final Format C;
    private final DrmSessionManager D;
    private final DrmSessionEventListener.EventDispatcher E;
    private final LoadErrorHandlingPolicy F;
    private final int G;
    private final HlsChunkSource.HlsChunkHolder H = new HlsChunkSource.HlsChunkHolder();
    private final Runnable I;
    private final Runnable J;
    private final Map K;
    private Chunk L;
    private int[] M = new int[0];
    private Set N;
    private SparseIntArray O;
    private TrackOutput P;
    private int Q;
    private Set R;
    private boolean S;
    private boolean[] T;
    private long U;
    private boolean V;
    private long W;
    private DrmInitData X;
    private HlsMediaChunk Y;
    final int b;
    final HlsChunkSource c;
    final Loader d = new Loader("Loader:HlsSampleStreamWrapper");
    final MediaSourceEventListener.EventDispatcher e;
    final ArrayList f;
    final List g;
    final Handler h;
    final ArrayList i;
    HlsSampleQueue[] j;
    int k;
    boolean l;
    boolean m;
    int n;
    Format o;
    Format p;
    boolean q;
    TrackGroupArray r;
    int[] s;
    int t;
    boolean[] u;
    long v;
    boolean w;
    boolean x;
    boolean y;

    public interface Callback extends SequenceableLoader.Callback {
        void a(Uri uri);

        void g();
    }

    class EmsgUnwrappingTrackOutput implements TrackOutput {
        private static final Format a;
        private static final Format b;
        private final TrackOutput c;
        private final Format d;
        private Format e;
        private byte[] f;
        private int g;

        static {
            Format.Builder builder = new Format.Builder();
            builder.k = "application/id3";
            a = builder.a();
            Format.Builder builder2 = new Format.Builder();
            builder2.k = "application/x-emsg";
            b = builder2.a();
        }

        public EmsgUnwrappingTrackOutput(TrackOutput trackOutput, int i) {
            Format format;
            new EventMessageDecoder();
            this.c = trackOutput;
            switch (i) {
                case 1:
                    format = a;
                    break;
                case 3:
                    format = b;
                    break;
                default:
                    throw new IllegalArgumentException(new StringBuilder(33).append("Unknown metadataType: ").append(i).toString());
            }
            this.d = format;
            this.f = new byte[0];
            this.g = 0;
        }

        private void a(int i) {
            byte[] bArr = this.f;
            if (bArr.length < i) {
                this.f = Arrays.copyOf(bArr, i + (i / 2));
            }
        }

        public final int a(DataReader dataReader, int i, boolean z) {
            a(this.g + i);
            int a2 = dataReader.a(this.f, this.g, i);
            if (a2 != -1) {
                this.g += a2;
                return a2;
            } else if (z) {
                return -1;
            } else {
                throw new EOFException();
            }
        }

        public final void a(long j, int i, int i2, int i3, TrackOutput.CryptoData cryptoData) {
            Assertions.b((Object) this.e);
            int i4 = this.g - i3;
            ParsableByteArray parsableByteArray = new ParsableByteArray(Arrays.copyOfRange(this.f, i4 - i2, i4));
            byte[] bArr = this.f;
            System.arraycopy(bArr, i4, bArr, 0, i3);
            this.g = i3;
            if (!Util.a((Object) this.e.l, (Object) this.d.l)) {
                if ("application/x-emsg".equals(this.e.l)) {
                    EventMessage a2 = EventMessageDecoder.a(parsableByteArray);
                    Format a3 = a2.a();
                    if (!(a3 != null && Util.a((Object) this.d.l, (Object) a3.l))) {
                        Log.c("EmsgUnwrappingTrackOutput", String.format("Ignoring EMSG. Expected it to contain wrapped %s but actual wrapped format: %s", new Object[]{this.d.l, a2.a()}));
                        return;
                    }
                    parsableByteArray = new ParsableByteArray((byte[]) Assertions.b((Object) a2.b()));
                } else {
                    String valueOf = String.valueOf(this.e.l);
                    Log.c("EmsgUnwrappingTrackOutput", valueOf.length() != 0 ? "Ignoring sample for unsupported format: ".concat(valueOf) : new String("Ignoring sample for unsupported format: "));
                    return;
                }
            }
            int a4 = parsableByteArray.a();
            this.c.b(parsableByteArray, a4);
            this.c.a(j, i, a4, i3, cryptoData);
        }

        public final void a(Format format) {
            this.e = format;
            this.c.a(this.d);
        }

        public final void a(ParsableByteArray parsableByteArray, int i) {
            a(this.g + i);
            parsableByteArray.a(this.f, this.g, i);
            this.g += i;
        }

        public final int b(DataReader dataReader, int i, boolean z) {
            return TrackOutput$$CC.a(this, dataReader, i, z);
        }

        public final void b(ParsableByteArray parsableByteArray, int i) {
            TrackOutput$$CC.a(this, parsableByteArray, i);
        }
    }

    final class HlsSampleQueue extends SampleQueue {
        private final Map f;
        private DrmInitData g;

        private HlsSampleQueue(Allocator allocator, Looper looper, DrmSessionManager drmSessionManager, DrmSessionEventListener.EventDispatcher eventDispatcher, Map map) {
            super(allocator, looper, drmSessionManager, eventDispatcher);
            this.f = map;
        }

        /* synthetic */ HlsSampleQueue(Allocator allocator, Looper looper, DrmSessionManager drmSessionManager, DrmSessionEventListener.EventDispatcher eventDispatcher, Map map, byte b) {
            this(allocator, looper, drmSessionManager, eventDispatcher, map);
        }

        public final void a(long j, int i, int i2, int i3, TrackOutput.CryptoData cryptoData) {
            super.a(j, i, i2, i3, cryptoData);
        }

        public final void a(DrmInitData drmInitData) {
            this.g = drmInitData;
            l();
        }

        public final void a(HlsMediaChunk hlsMediaChunk) {
            a(hlsMediaChunk.a);
        }

        public final Format b(Format format) {
            DrmInitData drmInitData;
            DrmInitData drmInitData2 = this.g;
            if (drmInitData2 == null) {
                drmInitData2 = format.o;
            }
            if (!(drmInitData2 == null || (drmInitData = (DrmInitData) this.f.get(drmInitData2.b)) == null)) {
                drmInitData2 = drmInitData;
            }
            Metadata metadata = format.j;
            if (metadata != null) {
                int length = metadata.a.length;
                int i = 0;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        i2 = -1;
                        break;
                    }
                    Metadata.Entry entry = metadata.a[i2];
                    if ((entry instanceof PrivFrame) && "com.apple.streaming.transportStreamTimestamp".equals(((PrivFrame) entry).a)) {
                        break;
                    }
                    i2++;
                }
                if (i2 != -1) {
                    if (length != 1) {
                        Metadata.Entry[] entryArr = new Metadata.Entry[(length - 1)];
                        while (i < length) {
                            if (i != i2) {
                                entryArr[i < i2 ? i : i - 1] = metadata.a[i];
                            }
                            i++;
                        }
                        metadata = new Metadata(entryArr);
                    }
                }
                if (!(drmInitData2 == format.o && metadata == format.j)) {
                    Format.Builder a = format.a();
                    a.n = drmInitData2;
                    a.i = metadata;
                    format = a.a();
                }
                return super.b(format);
            }
            metadata = null;
            Format.Builder a2 = format.a();
            a2.n = drmInitData2;
            a2.i = metadata;
            format = a2.a();
            return super.b(format);
        }
    }

    public HlsSampleStreamWrapper(int i2, Callback callback, HlsChunkSource hlsChunkSource, Map map, Allocator allocator, long j2, Format format, DrmSessionManager drmSessionManager, DrmSessionEventListener.EventDispatcher eventDispatcher, LoadErrorHandlingPolicy loadErrorHandlingPolicy, MediaSourceEventListener.EventDispatcher eventDispatcher2, int i3) {
        this.b = i2;
        this.A = callback;
        this.c = hlsChunkSource;
        this.K = map;
        this.B = allocator;
        this.C = format;
        this.D = drmSessionManager;
        this.E = eventDispatcher;
        this.F = loadErrorHandlingPolicy;
        this.e = eventDispatcher2;
        this.G = i3;
        Set set = z;
        this.N = new HashSet(set.size());
        this.O = new SparseIntArray(set.size());
        this.j = new HlsSampleQueue[0];
        this.T = new boolean[0];
        this.u = new boolean[0];
        ArrayList arrayList = new ArrayList();
        this.f = arrayList;
        this.g = Collections.unmodifiableList(arrayList);
        this.i = new ArrayList();
        this.I = new HlsSampleStreamWrapper$$Lambda$0(this);
        this.J = new HlsSampleStreamWrapper$$Lambda$1(this);
        this.h = Util.a();
        this.v = j2;
        this.U = j2;
    }

    private static Format a(Format format, Format format2, boolean z2) {
        String str;
        String str2;
        if (format == null) {
            return format2;
        }
        int h2 = MimeTypes.h(format2.l);
        if (Util.a(format.i, h2) == 1) {
            str2 = Util.b(format.i, h2);
            str = MimeTypes.g(str2);
        } else {
            str2 = MimeTypes.c(format.i, format2.l);
            str = format2.l;
        }
        Format.Builder a = format2.a();
        a.a = format.a;
        a.b = format.b;
        a.c = format.c;
        a.d = format.d;
        a.e = format.e;
        a.f = z2 ? format.f : -1;
        a.g = z2 ? format.g : -1;
        a.h = str2;
        a.p = format.width;
        a.q = format.height;
        if (str != null) {
            a.k = str;
        }
        if (format.w != -1) {
            a.x = format.w;
        }
        if (format.j != null) {
            Metadata metadata = format.j;
            if (format2.j != null) {
                metadata = format2.j.a(metadata);
            }
            a.i = metadata;
        }
        return a.a();
    }

    private TrackGroupArray a(TrackGroup[] trackGroupArr) {
        for (int i2 = 0; i2 < trackGroupArr.length; i2++) {
            TrackGroup trackGroup = trackGroupArr[i2];
            Format[] formatArr = new Format[trackGroup.a];
            for (int i3 = 0; i3 < trackGroup.a; i3++) {
                Format format = trackGroup.b[i3];
                formatArr[i3] = format.a(this.D.a(format));
            }
            trackGroupArr[i2] = new TrackGroup(formatArr);
        }
        return new TrackGroupArray(trackGroupArr);
    }

    private void a(HlsMediaChunk hlsMediaChunk) {
        this.Y = hlsMediaChunk;
        this.o = hlsMediaChunk.g;
        this.U = -9223372036854775807L;
        this.f.add(hlsMediaChunk);
        bH i2 = bG.i();
        for (HlsSampleQueue b2 : this.j) {
            i2.b(Integer.valueOf(b2.b()));
        }
        hlsMediaChunk.a(this, i2.a());
        for (HlsSampleQueue hlsSampleQueue : this.j) {
            hlsSampleQueue.a(hlsMediaChunk);
            if (hlsMediaChunk.b) {
                hlsSampleQueue.e = true;
            }
        }
    }

    private static DummyTrackOutput b(int i2, int i3) {
        Log.c("HlsSampleStreamWrapper", new StringBuilder(54).append("Unmapped track with id ").append(i2).append(" of type ").append(i3).toString());
        return new DummyTrackOutput();
    }

    private void b(int i2) {
        Assertions.b(!this.d.c());
        while (true) {
            if (i2 >= this.f.size()) {
                i2 = -1;
                break;
            } else if (c(i2)) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 != -1) {
            long j2 = m().k;
            HlsMediaChunk d2 = d(i2);
            if (this.f.isEmpty()) {
                this.U = this.v;
            } else {
                ((HlsMediaChunk) C0000a.b((Iterable) this.f)).n = true;
            }
            this.y = false;
            this.e.a(this.Q, d2.j, j2);
        }
    }

    private boolean c(int i2) {
        for (int i3 = i2; i3 < this.f.size(); i3++) {
            if (((HlsMediaChunk) this.f.get(i3)).b) {
                return false;
            }
        }
        HlsMediaChunk hlsMediaChunk = (HlsMediaChunk) this.f.get(i2);
        for (int i4 = 0; i4 < this.j.length; i4++) {
            if (this.j[i4].e() > hlsMediaChunk.a(i4)) {
                return false;
            }
        }
        return true;
    }

    private HlsMediaChunk d(int i2) {
        HlsMediaChunk hlsMediaChunk = (HlsMediaChunk) this.f.get(i2);
        ArrayList arrayList = this.f;
        Util.a((List) arrayList, i2, arrayList.size());
        for (int i3 = 0; i3 < this.j.length; i3++) {
            this.j[i3].b(hlsMediaChunk.a(i3));
        }
        return hlsMediaChunk;
    }

    private boolean d(long j2) {
        int length = this.j.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (!this.j[i2].a(j2, false) && (this.T[i2] || !this.S)) {
                return false;
            }
        }
        return true;
    }

    private static int e(int i2) {
        switch (i2) {
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 1;
            default:
                return 0;
        }
    }

    public final int a(int i2) {
        o();
        Assertions.b((Object) this.s);
        int i3 = this.s[i2];
        if (i3 == -1) {
            return this.R.contains(this.r.b[i2]) ? -3 : -2;
        }
        boolean[] zArr = this.u;
        if (zArr[i3]) {
            return -2;
        }
        zArr[i3] = true;
        return i3;
    }

    public final TrackOutput a(int i2, int i3) {
        Set set = z;
        boolean z2 = false;
        HlsSampleQueue hlsSampleQueue = null;
        if (set.contains(Integer.valueOf(i3))) {
            Assertions.a(set.contains(Integer.valueOf(i3)));
            int i4 = this.O.get(i3, -1);
            if (i4 != -1) {
                if (this.N.add(Integer.valueOf(i3))) {
                    this.M[i4] = i2;
                }
                hlsSampleQueue = this.M[i4] == i2 ? this.j[i4] : b(i2, i3);
            }
        } else {
            int i5 = 0;
            while (true) {
                HlsSampleQueue[] hlsSampleQueueArr = this.j;
                if (i5 >= hlsSampleQueueArr.length) {
                    break;
                } else if (this.M[i5] == i2) {
                    hlsSampleQueue = hlsSampleQueueArr[i5];
                    break;
                } else {
                    i5++;
                }
            }
        }
        if (hlsSampleQueue == null) {
            if (this.V) {
                return b(i2, i3);
            }
            int length = this.j.length;
            if (i3 == 1 || i3 == 2) {
                z2 = true;
            }
            HlsSampleQueue hlsSampleQueue2 = new HlsSampleQueue(this.B, this.h.getLooper(), this.D, this.E, this.K, (byte) 0);
            hlsSampleQueue2.d = this.v;
            if (z2) {
                hlsSampleQueue2.a(this.X);
            }
            hlsSampleQueue2.a(this.W);
            HlsMediaChunk hlsMediaChunk = this.Y;
            if (hlsMediaChunk != null) {
                hlsSampleQueue2.a(hlsMediaChunk);
            }
            hlsSampleQueue2.b = this;
            int i6 = length + 1;
            int[] copyOf = Arrays.copyOf(this.M, i6);
            this.M = copyOf;
            copyOf[length] = i2;
            this.j = (HlsSampleQueue[]) Util.b((Object[]) this.j, (Object) hlsSampleQueue2);
            boolean[] copyOf2 = Arrays.copyOf(this.T, i6);
            this.T = copyOf2;
            copyOf2[length] = z2;
            this.S |= z2;
            this.N.add(Integer.valueOf(i3));
            this.O.append(i3, length);
            if (e(i3) > e(this.Q)) {
                this.k = length;
                this.Q = i3;
            }
            this.u = Arrays.copyOf(this.u, i6);
            hlsSampleQueue = hlsSampleQueue2;
        }
        if (i3 != 5) {
            return hlsSampleQueue;
        }
        if (this.P == null) {
            this.P = new EmsgUnwrappingTrackOutput(hlsSampleQueue, this.G);
        }
        return this.P;
    }

    public final /* synthetic */ Loader.LoadErrorAction a(Loader.Loadable loadable, IOException iOException, int i2) {
        boolean z2;
        Loader.LoadErrorAction loadErrorAction;
        int i3;
        IOException iOException2 = iOException;
        Chunk chunk = (Chunk) loadable;
        boolean z3 = chunk instanceof HlsMediaChunk;
        if (z3 && !((HlsMediaChunk) chunk).o && (iOException2 instanceof HttpDataSource.InvalidResponseCodeException) && ((i3 = ((HttpDataSource.InvalidResponseCodeException) iOException2).a) == 410 || i3 == 404)) {
            return Loader.a;
        }
        long d2 = chunk.d();
        chunk.e();
        chunk.f();
        LoadEventInfo loadEventInfo = new LoadEventInfo((byte) 0);
        new MediaLoadData(chunk.f, this.b, chunk.g, chunk.h, chunk.i, C.a(chunk.j), C.a(chunk.k));
        LoadErrorHandlingPolicy.LoadErrorInfo loadErrorInfo = new LoadErrorHandlingPolicy.LoadErrorInfo(iOException2, i2);
        long a = this.F.a(loadErrorInfo);
        if (a != -9223372036854775807L) {
            HlsChunkSource hlsChunkSource = this.c;
            z2 = hlsChunkSource.h.a(hlsChunkSource.h.c(hlsChunkSource.b.a(chunk.g)), a);
        } else {
            z2 = false;
        }
        if (z2) {
            if (z3 && d2 == 0) {
                ArrayList arrayList = this.f;
                Assertions.b(((HlsMediaChunk) arrayList.remove(arrayList.size() - 1)) == chunk);
                if (this.f.isEmpty()) {
                    this.U = this.v;
                } else {
                    ((HlsMediaChunk) C0000a.b((Iterable) this.f)).n = true;
                }
            }
            loadErrorAction = Loader.b;
        } else {
            long b2 = this.F.b(loadErrorInfo);
            loadErrorAction = b2 != -9223372036854775807L ? Loader.a(false, b2) : Loader.c;
        }
        Loader.LoadErrorAction loadErrorAction2 = loadErrorAction;
        boolean z4 = !loadErrorAction2.a();
        this.e.a(loadEventInfo, chunk.f, this.b, chunk.g, chunk.h, chunk.i, chunk.j, chunk.k, iOException, z4);
        if (z4) {
            this.L = null;
            this.F.a();
        }
        if (z2) {
            if (!this.m) {
                c(this.v);
            } else {
                this.A.a(this);
            }
        }
        return loadErrorAction2;
    }

    public final void a(DrmInitData drmInitData) {
        if (!Util.a((Object) this.X, (Object) drmInitData)) {
            this.X = drmInitData;
            int i2 = 0;
            while (true) {
                HlsSampleQueue[] hlsSampleQueueArr = this.j;
                if (i2 < hlsSampleQueueArr.length) {
                    if (this.T[i2]) {
                        hlsSampleQueueArr[i2].a(drmInitData);
                    }
                    i2++;
                } else {
                    return;
                }
            }
        }
    }

    public final void a(SeekMap seekMap) {
    }

    public final /* synthetic */ void a(Loader.Loadable loadable, long j2, long j3) {
        Chunk chunk = (Chunk) loadable;
        this.L = null;
        HlsChunkSource hlsChunkSource = this.c;
        if (chunk instanceof HlsChunkSource.EncryptionKeyChunk) {
            HlsChunkSource.EncryptionKeyChunk encryptionKeyChunk = (HlsChunkSource.EncryptionKeyChunk) chunk;
            hlsChunkSource.e = encryptionKeyChunk.a;
            hlsChunkSource.c.a(encryptionKeyChunk.e.a, (byte[]) Assertions.b((Object) encryptionKeyChunk.b));
        }
        chunk.e();
        chunk.f();
        chunk.d();
        LoadEventInfo loadEventInfo = new LoadEventInfo((byte) 0);
        this.F.a();
        this.e.b(loadEventInfo, chunk.f, this.b, chunk.g, chunk.h, chunk.i, chunk.j, chunk.k);
        if (!this.m) {
            c(this.v);
        } else {
            this.A.a(this);
        }
    }

    public final /* synthetic */ void a(Loader.Loadable loadable, boolean z2) {
        Chunk chunk = (Chunk) loadable;
        this.L = null;
        chunk.e();
        chunk.f();
        chunk.d();
        LoadEventInfo loadEventInfo = new LoadEventInfo((byte) 0);
        this.F.a();
        this.e.c(loadEventInfo, chunk.f, this.b, chunk.g, chunk.h, chunk.i, chunk.j, chunk.k);
        if (!z2) {
            if (n() || this.n == 0) {
                k();
            }
            if (this.n > 0) {
                this.A.a(this);
            }
        }
    }

    public final void a(boolean z2) {
        this.c.d = z2;
    }

    public final void a(TrackGroup[] trackGroupArr, int... iArr) {
        this.r = a(trackGroupArr);
        this.R = new HashSet();
        for (int i2 : iArr) {
            this.R.add(this.r.b[i2]);
        }
        this.t = 0;
        Handler handler = this.h;
        Callback callback = this.A;
        callback.getClass();
        handler.post(HlsSampleStreamWrapper$$Lambda$2.a(callback));
        this.m = true;
    }

    public final boolean a(long j2, boolean z2) {
        this.v = j2;
        if (n()) {
            this.U = j2;
            return true;
        }
        if (this.l && !z2 && d(j2)) {
            return false;
        }
        this.U = j2;
        this.y = false;
        this.f.clear();
        if (this.d.c()) {
            if (this.l) {
                for (HlsSampleQueue k2 : this.j) {
                    k2.k();
                }
            }
            this.d.d();
        } else {
            this.d.d = null;
            k();
        }
        return true;
    }

    public final void a_(long j2) {
        if (!this.d.b() && !n()) {
            if (this.d.c()) {
                Assertions.b((Object) this.L);
                HlsChunkSource hlsChunkSource = this.c;
                if (hlsChunkSource.f != null ? false : hlsChunkSource.h.l()) {
                    this.d.d();
                    return;
                }
                return;
            }
            int size = this.g.size();
            while (size > 0 && this.c.a((HlsMediaChunk) this.g.get(size - 1)) == 2) {
                size--;
            }
            if (size < this.g.size()) {
                b(size);
            }
            HlsChunkSource hlsChunkSource2 = this.c;
            List list = this.g;
            int size2 = (hlsChunkSource2.f != null || hlsChunkSource2.h.f() < 2) ? list.size() : hlsChunkSource2.h.a(j2, list);
            if (size2 < this.f.size()) {
                b(size2);
            }
        }
    }

    public final void b() {
        if (!this.m) {
            c(this.v);
        }
    }

    public final void b(long j2) {
        if (this.W != j2) {
            this.W = j2;
            for (HlsSampleQueue a : this.j) {
                a.a(j2);
            }
        }
    }

    public final TrackGroupArray c() {
        o();
        return this.r;
    }

    public final boolean c(long j2) {
        long j3;
        List list;
        if (this.y || this.d.c() || this.d.b()) {
            return false;
        }
        if (n()) {
            list = Collections.emptyList();
            j3 = this.U;
            for (HlsSampleQueue hlsSampleQueue : this.j) {
                hlsSampleQueue.d = this.U;
            }
        } else {
            list = this.g;
            HlsMediaChunk m2 = m();
            j3 = m2.d ? m2.k : Math.max(this.v, m2.j);
        }
        List list2 = list;
        long j4 = j3;
        this.H.a();
        this.c.a(j2, j4, list2, this.m || !list2.isEmpty(), this.H);
        boolean z2 = this.H.b;
        Chunk chunk = this.H.a;
        Uri uri = this.H.c;
        if (z2) {
            this.U = -9223372036854775807L;
            this.y = true;
            return true;
        } else if (chunk == null) {
            if (uri != null) {
                this.A.a(uri);
            }
            return false;
        } else {
            if (chunk instanceof HlsMediaChunk) {
                a((HlsMediaChunk) chunk);
            }
            this.L = chunk;
            this.d.a(chunk, this, this.F.a(chunk.f));
            this.e.a(new LoadEventInfo(), chunk.f, this.b, chunk.g, chunk.h, chunk.i, chunk.j, chunk.k);
            return true;
        }
    }

    public final void c_() {
        this.V = true;
        this.h.post(this.J);
    }

    public final long d() {
        if (this.y) {
            return Long.MIN_VALUE;
        }
        if (n()) {
            return this.U;
        }
        long j2 = this.v;
        HlsMediaChunk m2 = m();
        if (!m2.d) {
            if (this.f.size() > 1) {
                ArrayList arrayList = this.f;
                m2 = (HlsMediaChunk) arrayList.get(arrayList.size() - 2);
            } else {
                m2 = null;
            }
        }
        if (m2 != null) {
            j2 = Math.max(j2, m2.k);
        }
        if (this.l) {
            for (HlsSampleQueue h2 : this.j) {
                j2 = Math.max(j2, h2.h());
            }
        }
        return j2;
    }

    public final long e() {
        if (n()) {
            return this.U;
        }
        if (this.y) {
            return Long.MIN_VALUE;
        }
        return m().k;
    }

    public final boolean f() {
        return this.d.c();
    }

    public final void g() {
        for (HlsSampleQueue a : this.j) {
            a.a();
        }
    }

    public final void h() {
        this.d.a(Integer.MIN_VALUE);
        this.c.a();
    }

    public final void i() {
        this.N.clear();
    }

    public final void j() {
        this.h.post(this.I);
    }

    /* access modifiers changed from: package-private */
    public final void k() {
        for (HlsSampleQueue a : this.j) {
            a.a(this.w);
        }
        this.w = false;
    }

    /* access modifiers changed from: package-private */
    public final void l() {
        if (!this.q && this.s == null && this.l) {
            HlsSampleQueue[] hlsSampleQueueArr = this.j;
            int length = hlsSampleQueueArr.length;
            boolean z2 = false;
            int i2 = 0;
            while (i2 < length) {
                if (hlsSampleQueueArr[i2].g() != null) {
                    i2++;
                } else {
                    return;
                }
            }
            TrackGroupArray trackGroupArray = this.r;
            if (trackGroupArray != null) {
                int i3 = trackGroupArray.length;
                int[] iArr = new int[i3];
                this.s = iArr;
                Arrays.fill(iArr, -1);
                for (int i4 = 0; i4 < i3; i4++) {
                    int i5 = 0;
                    while (true) {
                        HlsSampleQueue[] hlsSampleQueueArr2 = this.j;
                        if (i5 >= hlsSampleQueueArr2.length) {
                            break;
                        }
                        Format format = (Format) Assertions.a((Object) hlsSampleQueueArr2[i5].g());
                        Format format2 = this.r.b[i4].b[0];
                        String str = format.l;
                        String str2 = format2.l;
                        int h2 = MimeTypes.h(str);
                        if (h2 == 3 ? Util.a((Object) str, (Object) str2) && ((!"application/cea-608".equals(str) && !"application/cea-708".equals(str)) || format.B == format2.B) : h2 == MimeTypes.h(str2)) {
                            this.s[i4] = i5;
                            break;
                        }
                        i5++;
                    }
                }
                Iterator it = this.i.iterator();
                while (it.hasNext()) {
                    ((HlsSampleStream) it.next()).c();
                }
                return;
            }
            int length2 = this.j.length;
            int i6 = 0;
            int i7 = 7;
            int i8 = -1;
            while (true) {
                int i9 = 2;
                if (i6 >= length2) {
                    break;
                }
                String str3 = ((Format) Assertions.a((Object) this.j[i6].g())).l;
                if (!MimeTypes.b(str3)) {
                    i9 = MimeTypes.a(str3) ? 1 : MimeTypes.c(str3) ? 3 : 7;
                }
                if (e(i9) > e(i7)) {
                    i8 = i6;
                    i7 = i9;
                } else if (i9 == i7 && i8 != -1) {
                    i8 = -1;
                }
                i6++;
            }
            TrackGroup trackGroup = this.c.b;
            int i10 = trackGroup.a;
            this.t = -1;
            this.s = new int[length2];
            for (int i11 = 0; i11 < length2; i11++) {
                this.s[i11] = i11;
            }
            TrackGroup[] trackGroupArr = new TrackGroup[length2];
            for (int i12 = 0; i12 < length2; i12++) {
                Format format3 = (Format) Assertions.a((Object) this.j[i12].g());
                if (i12 == i8) {
                    Format[] formatArr = new Format[i10];
                    if (i10 == 1) {
                        formatArr[0] = format3.a(trackGroup.b[0]);
                    } else {
                        for (int i13 = 0; i13 < i10; i13++) {
                            formatArr[i13] = a(trackGroup.b[i13], format3, true);
                        }
                    }
                    trackGroupArr[i12] = new TrackGroup(formatArr);
                    this.t = i12;
                } else {
                    trackGroupArr[i12] = new TrackGroup(a((i7 != 2 || !MimeTypes.a(format3.l)) ? null : this.C, format3, false));
                }
            }
            this.r = a(trackGroupArr);
            if (this.R == null) {
                z2 = true;
            }
            Assertions.b(z2);
            this.R = Collections.emptySet();
            this.m = true;
            this.A.g();
        }
    }

    /* access modifiers changed from: package-private */
    public final HlsMediaChunk m() {
        ArrayList arrayList = this.f;
        return (HlsMediaChunk) arrayList.get(arrayList.size() - 1);
    }

    /* access modifiers changed from: package-private */
    public final boolean n() {
        return this.U != -9223372036854775807L;
    }

    /* access modifiers changed from: package-private */
    public final void o() {
        Assertions.b(this.m);
        Assertions.b((Object) this.r);
        Assertions.b((Object) this.R);
    }
}
