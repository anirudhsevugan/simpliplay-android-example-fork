package com.google.android.exoplayer2.source.chunk;

import android.os.Looper;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.source.LoadEventInfo;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.SampleQueue;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.SequenceableLoader;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChunkSampleStream implements SampleStream, SequenceableLoader, Loader.Callback, Loader.ReleaseCallback {
    public final int a;
    public final int[] b;
    public final boolean[] c;
    public final ChunkSource d;
    public final Loader e = new Loader("ChunkSampleStream");
    public final ArrayList f;
    public final SampleQueue g;
    public final SampleQueue[] h;
    public long i;
    public long j;
    public int k;
    public boolean l;
    /* access modifiers changed from: private */
    public final Format[] m;
    private final SequenceableLoader.Callback n;
    /* access modifiers changed from: private */
    public final MediaSourceEventListener.EventDispatcher o;
    private final LoadErrorHandlingPolicy p;
    private final ChunkHolder q = new ChunkHolder();
    private final List r;
    private final BaseMediaChunkOutput s;
    private Chunk t;
    private Format u;
    private ReleaseCallback v;
    /* access modifiers changed from: private */
    public BaseMediaChunk w;

    public final class EmbeddedSampleStream implements SampleStream {
        public final ChunkSampleStream a;
        private final SampleQueue b;
        private final int c;
        private boolean d;

        public EmbeddedSampleStream(ChunkSampleStream chunkSampleStream, SampleQueue sampleQueue, int i) {
            this.a = chunkSampleStream;
            this.b = sampleQueue;
            this.c = i;
        }

        private void d() {
            if (!this.d) {
                ChunkSampleStream.this.o.a(ChunkSampleStream.this.b[this.c], ChunkSampleStream.this.m[this.c], 0, (Object) null, ChunkSampleStream.this.j);
                this.d = true;
            }
        }

        public final int a(long j) {
            if (ChunkSampleStream.this.h()) {
                return 0;
            }
            int b2 = this.b.b(j, ChunkSampleStream.this.l);
            if (ChunkSampleStream.this.w != null) {
                b2 = Math.min(b2, ChunkSampleStream.this.w.a(this.c + 1) - this.b.e());
            }
            this.b.d(b2);
            if (b2 > 0) {
                d();
            }
            return b2;
        }

        public final int a(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i) {
            if (ChunkSampleStream.this.h()) {
                return -3;
            }
            if (ChunkSampleStream.this.w != null && ChunkSampleStream.this.w.a(this.c + 1) <= this.b.e()) {
                return -3;
            }
            d();
            return this.b.a(formatHolder, decoderInputBuffer, i, ChunkSampleStream.this.l);
        }

        public final boolean a() {
            return !ChunkSampleStream.this.h() && this.b.b(ChunkSampleStream.this.l);
        }

        public final void b() {
        }

        public final void c() {
            Assertions.b(ChunkSampleStream.this.c[this.c]);
            ChunkSampleStream.this.c[this.c] = false;
        }
    }

    public interface ReleaseCallback {
        void a(ChunkSampleStream chunkSampleStream);
    }

    public ChunkSampleStream(int i2, int[] iArr, Format[] formatArr, ChunkSource chunkSource, SequenceableLoader.Callback callback, Allocator allocator, long j2, DrmSessionManager drmSessionManager, DrmSessionEventListener.EventDispatcher eventDispatcher, LoadErrorHandlingPolicy loadErrorHandlingPolicy, MediaSourceEventListener.EventDispatcher eventDispatcher2) {
        this.a = i2;
        this.b = iArr;
        this.m = formatArr;
        this.d = chunkSource;
        this.n = callback;
        this.o = eventDispatcher2;
        this.p = loadErrorHandlingPolicy;
        ArrayList arrayList = new ArrayList();
        this.f = arrayList;
        this.r = Collections.unmodifiableList(arrayList);
        int length = iArr.length;
        this.h = new SampleQueue[length];
        this.c = new boolean[length];
        int i3 = length + 1;
        int[] iArr2 = new int[i3];
        SampleQueue[] sampleQueueArr = new SampleQueue[i3];
        SampleQueue a2 = SampleQueue.a(allocator, (Looper) Assertions.b((Object) Looper.myLooper()), drmSessionManager, eventDispatcher);
        this.g = a2;
        int i4 = 0;
        iArr2[0] = i2;
        sampleQueueArr[0] = a2;
        while (i4 < length) {
            SampleQueue a3 = SampleQueue.a(allocator);
            this.h[i4] = a3;
            int i5 = i4 + 1;
            sampleQueueArr[i5] = a3;
            iArr2[i5] = this.b[i4];
            i4 = i5;
        }
        this.s = new BaseMediaChunkOutput(iArr2, sampleQueueArr);
        this.i = j2;
        this.j = j2;
    }

    private boolean a(int i2) {
        int e2;
        BaseMediaChunk baseMediaChunk = (BaseMediaChunk) this.f.get(i2);
        if (this.g.e() > baseMediaChunk.a(0)) {
            return true;
        }
        int i3 = 0;
        do {
            SampleQueue[] sampleQueueArr = this.h;
            if (i3 >= sampleQueueArr.length) {
                return false;
            }
            e2 = sampleQueueArr[i3].e();
            i3++;
        } while (e2 <= baseMediaChunk.a(i3));
        return true;
    }

    private void b(int i2) {
        BaseMediaChunk baseMediaChunk = (BaseMediaChunk) this.f.get(i2);
        Format format = baseMediaChunk.g;
        if (!format.equals(this.u)) {
            this.o.a(this.a, format, baseMediaChunk.h, baseMediaChunk.i, baseMediaChunk.j);
        }
        this.u = format;
    }

    private BaseMediaChunk c(int i2) {
        BaseMediaChunk baseMediaChunk = (BaseMediaChunk) this.f.get(i2);
        ArrayList arrayList = this.f;
        Util.a((List) arrayList, i2, arrayList.size());
        this.k = Math.max(this.k, this.f.size());
        SampleQueue sampleQueue = this.g;
        int i3 = 0;
        while (true) {
            sampleQueue.b(baseMediaChunk.a(i3));
            SampleQueue[] sampleQueueArr = this.h;
            if (i3 >= sampleQueueArr.length) {
                return baseMediaChunk;
            }
            sampleQueue = sampleQueueArr[i3];
            i3++;
        }
    }

    private void i() {
        int a2 = a(this.g.e(), this.k - 1);
        while (true) {
            int i2 = this.k;
            if (i2 <= a2) {
                this.k = i2 + 1;
                b(i2);
            } else {
                return;
            }
        }
    }

    private BaseMediaChunk j() {
        ArrayList arrayList = this.f;
        return (BaseMediaChunk) arrayList.get(arrayList.size() - 1);
    }

    public final int a(int i2, int i3) {
        do {
            i3++;
            if (i3 >= this.f.size()) {
                return this.f.size() - 1;
            }
        } while (((BaseMediaChunk) this.f.get(i3)).a(0) <= i2);
        return i3 - 1;
    }

    public final int a(long j2) {
        if (h()) {
            return 0;
        }
        int b2 = this.g.b(j2, this.l);
        BaseMediaChunk baseMediaChunk = this.w;
        if (baseMediaChunk != null) {
            b2 = Math.min(b2, baseMediaChunk.a(0) - this.g.e());
        }
        this.g.d(b2);
        i();
        return b2;
    }

    public final int a(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i2) {
        if (h()) {
            return -3;
        }
        BaseMediaChunk baseMediaChunk = this.w;
        if (baseMediaChunk != null && baseMediaChunk.a(0) <= this.g.e()) {
            return -3;
        }
        i();
        return this.g.a(formatHolder, decoderInputBuffer, i2, this.l);
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00e6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ com.google.android.exoplayer2.upstream.Loader.LoadErrorAction a(com.google.android.exoplayer2.upstream.Loader.Loadable r27, java.io.IOException r28, int r29) {
        /*
            r26 = this;
            r0 = r26
            r7 = r27
            com.google.android.exoplayer2.source.chunk.Chunk r7 = (com.google.android.exoplayer2.source.chunk.Chunk) r7
            long r1 = r7.d()
            boolean r8 = r7 instanceof com.google.android.exoplayer2.source.chunk.BaseMediaChunk
            java.util.ArrayList r3 = r0.f
            int r3 = r3.size()
            r9 = 1
            int r10 = r3 + -1
            r3 = 0
            r11 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x0027
            if (r8 == 0) goto L_0x0027
            boolean r1 = r0.a((int) r10)
            if (r1 != 0) goto L_0x0025
            goto L_0x0027
        L_0x0025:
            r12 = 0
            goto L_0x0028
        L_0x0027:
            r12 = 1
        L_0x0028:
            com.google.android.exoplayer2.source.LoadEventInfo r14 = new com.google.android.exoplayer2.source.LoadEventInfo
            r7.e()
            r7.f()
            r14.<init>(r11)
            com.google.android.exoplayer2.source.MediaLoadData r15 = new com.google.android.exoplayer2.source.MediaLoadData
            int r1 = r7.f
            int r2 = r0.a
            com.google.android.exoplayer2.Format r3 = r7.g
            int r4 = r7.h
            java.lang.Object r5 = r7.i
            r13 = r10
            long r9 = r7.j
            long r21 = com.google.android.exoplayer2.C.a((long) r9)
            long r9 = r7.k
            long r23 = com.google.android.exoplayer2.C.a((long) r9)
            r16 = r1
            r17 = r2
            r18 = r3
            r19 = r4
            r20 = r5
            r15.<init>(r16, r17, r18, r19, r20, r21, r23)
            com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy$LoadErrorInfo r9 = new com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy$LoadErrorInfo
            r10 = r28
            r1 = r29
            r9.<init>(r10, r1)
            r15 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r12 == 0) goto L_0x0071
            com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy r1 = r0.p
            long r1 = r1.a((com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy.LoadErrorInfo) r9)
            r5 = r1
            goto L_0x0072
        L_0x0071:
            r5 = r15
        L_0x0072:
            com.google.android.exoplayer2.source.chunk.ChunkSource r1 = r0.d
            r2 = r7
            r3 = r12
            r4 = r28
            boolean r1 = r1.a((com.google.android.exoplayer2.source.chunk.Chunk) r2, (boolean) r3, (java.lang.Exception) r4, (long) r5)
            if (r1 == 0) goto L_0x00a5
            if (r12 == 0) goto L_0x009e
            com.google.android.exoplayer2.upstream.Loader$LoadErrorAction r1 = com.google.android.exoplayer2.upstream.Loader.b
            if (r8 == 0) goto L_0x00a6
            r3 = r13
            com.google.android.exoplayer2.source.chunk.BaseMediaChunk r3 = r0.c((int) r3)
            if (r3 != r7) goto L_0x008d
            r3 = 1
            goto L_0x008e
        L_0x008d:
            r3 = 0
        L_0x008e:
            com.google.android.exoplayer2.util.Assertions.b((boolean) r3)
            java.util.ArrayList r3 = r0.f
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto L_0x00a6
            long r3 = r0.j
            r0.i = r3
            goto L_0x00a6
        L_0x009e:
            java.lang.String r1 = "ChunkSampleStream"
            java.lang.String r3 = "Ignoring attempt to cancel non-cancelable load."
            com.google.android.exoplayer2.util.Log.c(r1, r3)
        L_0x00a5:
            r1 = 0
        L_0x00a6:
            if (r1 != 0) goto L_0x00b9
            com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy r1 = r0.p
            long r3 = r1.b(r9)
            int r1 = (r3 > r15 ? 1 : (r3 == r15 ? 0 : -1))
            if (r1 == 0) goto L_0x00b7
            com.google.android.exoplayer2.upstream.Loader$LoadErrorAction r1 = com.google.android.exoplayer2.upstream.Loader.a((boolean) r11, (long) r3)
            goto L_0x00b9
        L_0x00b7:
            com.google.android.exoplayer2.upstream.Loader$LoadErrorAction r1 = com.google.android.exoplayer2.upstream.Loader.c
        L_0x00b9:
            boolean r3 = r1.a()
            r4 = 1
            r3 = r3 ^ r4
            com.google.android.exoplayer2.source.MediaSourceEventListener$EventDispatcher r13 = r0.o
            int r15 = r7.f
            int r4 = r0.a
            com.google.android.exoplayer2.Format r5 = r7.g
            int r6 = r7.h
            java.lang.Object r8 = r7.i
            long r11 = r7.j
            r29 = r3
            long r2 = r7.k
            r16 = r4
            r17 = r5
            r18 = r6
            r19 = r8
            r20 = r11
            r22 = r2
            r24 = r28
            r25 = r29
            r13.a(r14, r15, r16, r17, r18, r19, r20, r22, r24, r25)
            if (r29 == 0) goto L_0x00f3
            r2 = 0
            r0.t = r2
            com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy r2 = r0.p
            r2.a()
            com.google.android.exoplayer2.source.SequenceableLoader$Callback r2 = r0.n
            r2.a(r0)
        L_0x00f3:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.chunk.ChunkSampleStream.a(com.google.android.exoplayer2.upstream.Loader$Loadable, java.io.IOException, int):com.google.android.exoplayer2.upstream.Loader$LoadErrorAction");
    }

    public final void a(ReleaseCallback releaseCallback) {
        this.v = releaseCallback;
        this.g.c();
        for (SampleQueue c2 : this.h) {
            c2.c();
        }
        this.e.a((Loader.ReleaseCallback) this);
    }

    public final /* synthetic */ void a(Loader.Loadable loadable, long j2, long j3) {
        Chunk chunk = (Chunk) loadable;
        this.t = null;
        this.d.a(chunk);
        chunk.e();
        chunk.f();
        chunk.d();
        LoadEventInfo loadEventInfo = new LoadEventInfo((byte) 0);
        this.p.a();
        this.o.b(loadEventInfo, chunk.f, this.a, chunk.g, chunk.h, chunk.i, chunk.j, chunk.k);
        this.n.a(this);
    }

    public final /* synthetic */ void a(Loader.Loadable loadable, boolean z) {
        Chunk chunk = (Chunk) loadable;
        this.t = null;
        this.w = null;
        chunk.e();
        chunk.f();
        chunk.d();
        LoadEventInfo loadEventInfo = new LoadEventInfo((byte) 0);
        this.p.a();
        this.o.c(loadEventInfo, chunk.f, this.a, chunk.g, chunk.h, chunk.i, chunk.j, chunk.k);
        if (!z) {
            if (h()) {
                c();
            } else if (chunk instanceof BaseMediaChunk) {
                c(this.f.size() - 1);
                if (this.f.isEmpty()) {
                    this.i = this.j;
                }
            }
            this.n.a(this);
        }
    }

    public final boolean a() {
        return !h() && this.g.b(this.l);
    }

    public final void a_(long j2) {
        if (!this.e.b() && !h()) {
            if (this.e.c()) {
                Chunk chunk = (Chunk) Assertions.b((Object) this.t);
                boolean z = chunk instanceof BaseMediaChunk;
                if ((!z || !a(this.f.size() - 1)) && this.d.b()) {
                    this.e.d();
                    if (z) {
                        this.w = (BaseMediaChunk) chunk;
                        return;
                    }
                    return;
                }
                return;
            }
            int a2 = this.d.a(j2, this.r);
            if (a2 < this.f.size()) {
                Assertions.b(!this.e.c());
                int size = this.f.size();
                while (true) {
                    if (a2 >= size) {
                        a2 = -1;
                        break;
                    } else if (!a(a2)) {
                        break;
                    } else {
                        a2++;
                    }
                }
                if (a2 != -1) {
                    long j3 = j().k;
                    BaseMediaChunk c2 = c(a2);
                    if (this.f.isEmpty()) {
                        this.i = this.j;
                    }
                    this.l = false;
                    this.o.a(this.a, c2.j, j3);
                }
            }
        }
    }

    public final void b() {
        this.e.a(Integer.MIN_VALUE);
        this.g.d();
        if (!this.e.c()) {
            this.d.a();
        }
    }

    public final void c() {
        this.g.a(false);
        for (SampleQueue a2 : this.h) {
            a2.a(false);
        }
    }

    public final boolean c(long j2) {
        long j3;
        List list;
        if (this.l || this.e.c() || this.e.b()) {
            return false;
        }
        boolean h2 = h();
        if (h2) {
            list = Collections.emptyList();
            j3 = this.i;
        } else {
            list = this.r;
            j3 = j().k;
        }
        this.d.a(j2, j3, list, this.q);
        boolean z = this.q.b;
        Chunk chunk = this.q.a;
        ChunkHolder chunkHolder = this.q;
        chunkHolder.a = null;
        chunkHolder.b = false;
        if (z) {
            this.i = -9223372036854775807L;
            this.l = true;
            return true;
        } else if (chunk == null) {
            return false;
        } else {
            this.t = chunk;
            if (chunk instanceof BaseMediaChunk) {
                BaseMediaChunk baseMediaChunk = (BaseMediaChunk) chunk;
                if (h2) {
                    long j4 = baseMediaChunk.j;
                    long j5 = this.i;
                    if (j4 != j5) {
                        this.g.d = j5;
                        for (SampleQueue sampleQueue : this.h) {
                            sampleQueue.d = this.i;
                        }
                    }
                    this.i = -9223372036854775807L;
                }
                BaseMediaChunkOutput baseMediaChunkOutput = this.s;
                baseMediaChunk.c = baseMediaChunkOutput;
                int[] iArr = new int[baseMediaChunkOutput.a.length];
                for (int i2 = 0; i2 < baseMediaChunkOutput.a.length; i2++) {
                    iArr[i2] = baseMediaChunkOutput.a[i2].b();
                }
                baseMediaChunk.d = iArr;
                this.f.add(baseMediaChunk);
            } else if (chunk instanceof InitializationChunk) {
                ((InitializationChunk) chunk).a = this.s;
            }
            this.e.a(chunk, this, this.p.a(chunk.f));
            this.o.a(new LoadEventInfo(), chunk.f, this.a, chunk.g, chunk.h, chunk.i, chunk.j, chunk.k);
            return true;
        }
    }

    public final long d() {
        if (this.l) {
            return Long.MIN_VALUE;
        }
        if (h()) {
            return this.i;
        }
        long j2 = this.j;
        BaseMediaChunk j3 = j();
        if (!j3.h()) {
            if (this.f.size() > 1) {
                ArrayList arrayList = this.f;
                j3 = (BaseMediaChunk) arrayList.get(arrayList.size() - 2);
            } else {
                j3 = null;
            }
        }
        if (j3 != null) {
            j2 = Math.max(j2, j3.k);
        }
        return Math.max(j2, this.g.h());
    }

    public final long e() {
        if (h()) {
            return this.i;
        }
        if (this.l) {
            return Long.MIN_VALUE;
        }
        return j().k;
    }

    public final boolean f() {
        return this.e.c();
    }

    public final void g() {
        this.g.a();
        for (SampleQueue a2 : this.h) {
            a2.a();
        }
        ReleaseCallback releaseCallback = this.v;
        if (releaseCallback != null) {
            releaseCallback.a(this);
        }
    }

    public final boolean h() {
        return this.i != -9223372036854775807L;
    }
}
