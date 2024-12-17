package com.google.android.exoplayer2.source.dash;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.source.BehindLiveWindowException;
import com.google.android.exoplayer2.source.chunk.BaseMediaChunkIterator;
import com.google.android.exoplayer2.source.chunk.BundledChunkExtractor;
import com.google.android.exoplayer2.source.chunk.Chunk;
import com.google.android.exoplayer2.source.chunk.ChunkExtractor;
import com.google.android.exoplayer2.source.chunk.InitializationChunk;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.source.dash.DashChunkSource;
import com.google.android.exoplayer2.source.dash.PlayerEmsgHandler;
import com.google.android.exoplayer2.source.dash.manifest.AdaptationSet;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.source.dash.manifest.RangedUri;
import com.google.android.exoplayer2.source.dash.manifest.Representation;
import com.google.android.exoplayer2.trackselection.ExoTrackSelection;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DefaultDashChunkSource implements DashChunkSource {
    private final LoaderErrorThrower a;
    private final int[] b;
    private final int c;
    private final DataSource d;
    private final long e;
    private final int f = 1;
    private final PlayerEmsgHandler.PlayerTrackEmsgHandler g;
    private RepresentationHolder[] h;
    private ExoTrackSelection i;
    private DashManifest j;
    private int k;
    private IOException l;
    private boolean m;

    public final class Factory implements DashChunkSource.Factory {
        private final DataSource.Factory a;

        public Factory(DataSource.Factory factory) {
            this(factory, (byte) 0);
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        private Factory(DataSource.Factory factory, byte b) {
            this(factory, 0);
            ChunkExtractor.Factory factory2 = BundledChunkExtractor.b;
        }

        private Factory(DataSource.Factory factory, char c) {
            this.a = factory;
        }

        public final DashChunkSource a(LoaderErrorThrower loaderErrorThrower, DashManifest dashManifest, int i, int[] iArr, ExoTrackSelection exoTrackSelection, int i2, long j, boolean z, List list, PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler, TransferListener transferListener) {
            TransferListener transferListener2 = transferListener;
            DataSource a2 = this.a.a();
            if (transferListener2 != null) {
                a2.a(transferListener2);
            }
            return new DefaultDashChunkSource(loaderErrorThrower, dashManifest, i, iArr, exoTrackSelection, i2, a2, j, 1, z, list, playerTrackEmsgHandler);
        }
    }

    public final class RepresentationHolder {
        final ChunkExtractor a;
        public final Representation b;
        public final DashSegmentIndex c;
        /* access modifiers changed from: package-private */
        public final long d;
        final long e;

        RepresentationHolder(long j, Representation representation, ChunkExtractor chunkExtractor, long j2, DashSegmentIndex dashSegmentIndex) {
            this.d = j;
            this.b = representation;
            this.e = j2;
            this.a = chunkExtractor;
            this.c = dashSegmentIndex;
        }

        public final long a() {
            return this.c.a() + this.e;
        }

        public final long a(long j) {
            return this.c.c(this.d, j) + this.e;
        }

        public final boolean a(long j, long j2) {
            return this.c.b() || j2 == -9223372036854775807L || c(j) <= j2;
        }

        public final long b() {
            return this.c.c(this.d);
        }

        public final long b(long j) {
            return this.c.a(j - this.e);
        }

        public final long c(long j) {
            return b(j) + this.c.b(j - this.e, this.d);
        }

        public final long d(long j) {
            return this.c.a(j, this.d) + this.e;
        }

        public final RangedUri e(long j) {
            return this.c.b(j - this.e);
        }

        public final long f(long j) {
            return (a(j) + this.c.d(this.d, j)) - 1;
        }
    }

    public final class RepresentationSegmentIterator extends BaseMediaChunkIterator {
        private final RepresentationHolder b;

        public RepresentationSegmentIterator(RepresentationHolder representationHolder, long j, long j2) {
            super(j, j2);
            this.b = representationHolder;
        }

        public final long d() {
            b();
            return this.b.b(c());
        }

        public final long e() {
            b();
            return this.b.c(c());
        }
    }

    public DefaultDashChunkSource(LoaderErrorThrower loaderErrorThrower, DashManifest dashManifest, int i2, int[] iArr, ExoTrackSelection exoTrackSelection, int i3, DataSource dataSource, long j2, int i4, boolean z, List list, PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler) {
        ExoTrackSelection exoTrackSelection2 = exoTrackSelection;
        this.a = loaderErrorThrower;
        this.j = dashManifest;
        this.b = iArr;
        this.i = exoTrackSelection2;
        this.c = i3;
        this.d = dataSource;
        this.k = i2;
        this.e = j2;
        this.g = playerTrackEmsgHandler;
        long b2 = dashManifest.b(i2);
        ArrayList c2 = c();
        this.h = new RepresentationHolder[exoTrackSelection.f()];
        int i5 = 0;
        while (i5 < this.h.length) {
            Representation representation = (Representation) c2.get(exoTrackSelection2.b(i5));
            RepresentationHolder[] representationHolderArr = this.h;
            int i6 = i5;
            representationHolderArr[i6] = new RepresentationHolder(b2, representation, BundledChunkExtractor.b.a(i3, representation.a, z, list, playerTrackEmsgHandler), 0, representation.d());
            i5 = i6 + 1;
            c2 = c2;
        }
    }

    private long a(long j2) {
        if (this.j.a == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        return j2 - C.b(this.j.a + this.j.a(this.k).b);
    }

    private static long a(RepresentationHolder representationHolder, MediaChunk mediaChunk, long j2, long j3, long j4) {
        return mediaChunk != null ? mediaChunk.g() : Util.a(representationHolder.d(j2), j3, j4);
    }

    private ArrayList c() {
        List list = this.j.a(this.k).c;
        ArrayList arrayList = new ArrayList();
        for (int i2 : this.b) {
            arrayList.addAll(((AdaptationSet) list.get(i2)).c);
        }
        return arrayList;
    }

    public final int a(long j2, List list) {
        return (this.l != null || this.i.f() < 2) ? list.size() : this.i.a(j2, list);
    }

    public final long a(long j2, SeekParameters seekParameters) {
        long j3 = j2;
        for (RepresentationHolder representationHolder : this.h) {
            if (representationHolder.c != null) {
                long d2 = representationHolder.d(j3);
                long b2 = representationHolder.b(d2);
                long b3 = representationHolder.b();
                return seekParameters.a(j2, b2, (b2 >= j3 || (b3 != -1 && d2 >= (representationHolder.a() + b3) - 1)) ? b2 : representationHolder.b(d2 + 1));
            }
        }
        return j3;
    }

    public final void a() {
        IOException iOException = this.l;
        if (iOException == null) {
            this.a.a();
            return;
        }
        throw iOException;
    }

    /* JADX WARNING: type inference failed for: r3v17, types: [com.google.android.exoplayer2.source.chunk.Chunk] */
    /* JADX WARNING: type inference failed for: r29v2, types: [com.google.android.exoplayer2.source.chunk.ContainerMediaChunk] */
    /* JADX WARNING: type inference failed for: r29v3, types: [com.google.android.exoplayer2.source.chunk.SingleSampleMediaChunk] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(long r52, long r54, java.util.List r56, com.google.android.exoplayer2.source.chunk.ChunkHolder r57) {
        /*
            r51 = this;
            r0 = r51
            r9 = r57
            java.io.IOException r1 = r0.l
            if (r1 == 0) goto L_0x0009
            return
        L_0x0009:
            long r10 = r54 - r52
            com.google.android.exoplayer2.source.dash.manifest.DashManifest r1 = r0.j
            long r1 = r1.a
            long r1 = com.google.android.exoplayer2.C.b(r1)
            com.google.android.exoplayer2.source.dash.manifest.DashManifest r3 = r0.j
            int r4 = r0.k
            com.google.android.exoplayer2.source.dash.manifest.Period r3 = r3.a((int) r4)
            long r3 = r3.b
            long r3 = com.google.android.exoplayer2.C.b(r3)
            long r1 = r1 + r3
            long r1 = r1 + r54
            com.google.android.exoplayer2.source.dash.PlayerEmsgHandler$PlayerTrackEmsgHandler r3 = r0.g
            r12 = 0
            r13 = 1
            if (r3 == 0) goto L_0x0076
            com.google.android.exoplayer2.source.dash.PlayerEmsgHandler r3 = com.google.android.exoplayer2.source.dash.PlayerEmsgHandler.this
            com.google.android.exoplayer2.source.dash.manifest.DashManifest r4 = r3.e
            boolean r4 = r4.d
            if (r4 != 0) goto L_0x0034
            r1 = 0
            goto L_0x0073
        L_0x0034:
            boolean r4 = r3.h
            if (r4 == 0) goto L_0x003a
            r1 = 1
            goto L_0x0073
        L_0x003a:
            com.google.android.exoplayer2.source.dash.manifest.DashManifest r4 = r3.e
            long r4 = r4.h
            java.util.TreeMap r6 = r3.d
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            java.util.Map$Entry r4 = r6.ceilingEntry(r4)
            if (r4 == 0) goto L_0x006d
            java.lang.Object r5 = r4.getValue()
            java.lang.Long r5 = (java.lang.Long) r5
            long r5 = r5.longValue()
            int r7 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r7 >= 0) goto L_0x006d
            java.lang.Object r1 = r4.getKey()
            java.lang.Long r1 = (java.lang.Long) r1
            long r1 = r1.longValue()
            r3.f = r1
            com.google.android.exoplayer2.source.dash.PlayerEmsgHandler$PlayerEmsgCallback r1 = r3.b
            long r4 = r3.f
            r1.a(r4)
            r1 = 1
            goto L_0x006e
        L_0x006d:
            r1 = 0
        L_0x006e:
            if (r1 == 0) goto L_0x0073
            r3.a()
        L_0x0073:
            if (r1 == 0) goto L_0x0076
            return
        L_0x0076:
            long r1 = r0.e
            long r1 = com.google.android.exoplayer2.util.Util.a((long) r1)
            long r14 = com.google.android.exoplayer2.C.b(r1)
            long r7 = r0.a((long) r14)
            boolean r1 = r56.isEmpty()
            r16 = 0
            if (r1 == 0) goto L_0x0091
            r5 = r56
            r17 = r16
            goto L_0x00a0
        L_0x0091:
            int r1 = r56.size()
            int r1 = r1 - r13
            r5 = r56
            java.lang.Object r1 = r5.get(r1)
            com.google.android.exoplayer2.source.chunk.MediaChunk r1 = (com.google.android.exoplayer2.source.chunk.MediaChunk) r1
            r17 = r1
        L_0x00a0:
            com.google.android.exoplayer2.trackselection.ExoTrackSelection r1 = r0.i
            int r6 = r1.f()
            com.google.android.exoplayer2.source.chunk.MediaChunkIterator[] r3 = new com.google.android.exoplayer2.source.chunk.MediaChunkIterator[r6]
            r4 = 0
        L_0x00a9:
            if (r4 >= r6) goto L_0x0100
            com.google.android.exoplayer2.source.dash.DefaultDashChunkSource$RepresentationHolder[] r1 = r0.h
            r2 = r1[r4]
            com.google.android.exoplayer2.source.dash.DashSegmentIndex r1 = r2.c
            if (r1 != 0) goto L_0x00c0
            com.google.android.exoplayer2.source.chunk.MediaChunkIterator r1 = com.google.android.exoplayer2.source.chunk.MediaChunkIterator.a
            r3[r4] = r1
            r24 = r3
            r25 = r4
            r26 = r6
            r27 = r7
            goto L_0x00f5
        L_0x00c0:
            long r18 = r2.a((long) r14)
            long r22 = r2.f(r14)
            r1 = r2
            r20 = r2
            r2 = r17
            r24 = r3
            r25 = r4
            r3 = r54
            r26 = r6
            r5 = r18
            r27 = r7
            r7 = r22
            long r1 = a(r1, r2, r3, r5, r7)
            int r3 = (r1 > r18 ? 1 : (r1 == r18 ? 0 : -1))
            if (r3 >= 0) goto L_0x00e8
            com.google.android.exoplayer2.source.chunk.MediaChunkIterator r1 = com.google.android.exoplayer2.source.chunk.MediaChunkIterator.a
            r24[r25] = r1
            goto L_0x00f5
        L_0x00e8:
            com.google.android.exoplayer2.source.dash.DefaultDashChunkSource$RepresentationSegmentIterator r3 = new com.google.android.exoplayer2.source.dash.DefaultDashChunkSource$RepresentationSegmentIterator
            r18 = r3
            r19 = r20
            r20 = r1
            r18.<init>(r19, r20, r22)
            r24[r25] = r3
        L_0x00f5:
            int r4 = r25 + 1
            r5 = r56
            r3 = r24
            r6 = r26
            r7 = r27
            goto L_0x00a9
        L_0x0100:
            r24 = r3
            r27 = r7
            com.google.android.exoplayer2.source.dash.manifest.DashManifest r1 = r0.j
            boolean r1 = r1.d
            r7 = 0
            r18 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r1 != 0) goto L_0x0114
            r5 = r18
            goto L_0x0133
        L_0x0114:
            com.google.android.exoplayer2.source.dash.DefaultDashChunkSource$RepresentationHolder[] r1 = r0.h
            r1 = r1[r12]
            long r1 = r1.f(r14)
            com.google.android.exoplayer2.source.dash.DefaultDashChunkSource$RepresentationHolder[] r3 = r0.h
            r3 = r3[r12]
            long r1 = r3.c(r1)
            long r3 = r0.a((long) r14)
            long r1 = java.lang.Math.min(r3, r1)
            long r1 = r1 - r52
            long r1 = java.lang.Math.max(r7, r1)
            r5 = r1
        L_0x0133:
            com.google.android.exoplayer2.trackselection.ExoTrackSelection r2 = r0.i
            r3 = r10
            r10 = r7
            r7 = r56
            r8 = r24
            r2.a(r3, r5, r7, r8)
            com.google.android.exoplayer2.source.dash.DefaultDashChunkSource$RepresentationHolder[] r1 = r0.h
            com.google.android.exoplayer2.trackselection.ExoTrackSelection r2 = r0.i
            int r2 = r2.a()
            r7 = r1[r2]
            com.google.android.exoplayer2.source.chunk.ChunkExtractor r1 = r7.a
            if (r1 == 0) goto L_0x019b
            com.google.android.exoplayer2.source.dash.manifest.Representation r1 = r7.b
            com.google.android.exoplayer2.source.chunk.ChunkExtractor r2 = r7.a
            com.google.android.exoplayer2.Format[] r2 = r2.c()
            if (r2 != 0) goto L_0x0159
            com.google.android.exoplayer2.source.dash.manifest.RangedUri r2 = r1.e
            goto L_0x015b
        L_0x0159:
            r2 = r16
        L_0x015b:
            com.google.android.exoplayer2.source.dash.DashSegmentIndex r3 = r7.c
            if (r3 != 0) goto L_0x0163
            com.google.android.exoplayer2.source.dash.manifest.RangedUri r16 = r1.c()
        L_0x0163:
            r1 = r16
            if (r2 != 0) goto L_0x0169
            if (r1 == 0) goto L_0x019b
        L_0x0169:
            com.google.android.exoplayer2.upstream.DataSource r3 = r0.d
            com.google.android.exoplayer2.trackselection.ExoTrackSelection r4 = r0.i
            com.google.android.exoplayer2.Format r23 = r4.g()
            com.google.android.exoplayer2.trackselection.ExoTrackSelection r4 = r0.i
            int r24 = r4.b()
            com.google.android.exoplayer2.source.dash.manifest.Representation r4 = r7.b
            if (r2 == 0) goto L_0x0184
            java.lang.String r5 = r4.b
            com.google.android.exoplayer2.source.dash.manifest.RangedUri r1 = r2.a(r1, r5)
            if (r1 != 0) goto L_0x0184
            goto L_0x0185
        L_0x0184:
            r2 = r1
        L_0x0185:
            com.google.android.exoplayer2.upstream.DataSpec r22 = com.google.android.exoplayer2.source.dash.DashUtil.a(r4, r2, r12)
            com.google.android.exoplayer2.source.chunk.InitializationChunk r1 = new com.google.android.exoplayer2.source.chunk.InitializationChunk
            r25 = 0
            com.google.android.exoplayer2.source.chunk.ChunkExtractor r2 = r7.a
            r20 = r1
            r21 = r3
            r26 = r2
            r20.<init>(r21, r22, r23, r24, r25, r26)
            r9.a = r1
            return
        L_0x019b:
            long r20 = r7.d
            int r1 = (r20 > r18 ? 1 : (r20 == r18 ? 0 : -1))
            if (r1 == 0) goto L_0x01a5
            r8 = 1
            goto L_0x01a6
        L_0x01a5:
            r8 = 0
        L_0x01a6:
            long r1 = r7.b()
            int r3 = (r1 > r10 ? 1 : (r1 == r10 ? 0 : -1))
            if (r3 != 0) goto L_0x01b1
            r9.b = r8
            return
        L_0x01b1:
            long r10 = r7.a((long) r14)
            long r14 = r7.f(r14)
            r1 = r7
            r2 = r17
            r3 = r54
            r5 = r10
            r12 = r7
            r13 = r8
            r7 = r14
            long r1 = a(r1, r2, r3, r5, r7)
            int r3 = (r1 > r10 ? 1 : (r1 == r10 ? 0 : -1))
            if (r3 >= 0) goto L_0x01d2
            com.google.android.exoplayer2.source.BehindLiveWindowException r1 = new com.google.android.exoplayer2.source.BehindLiveWindowException
            r1.<init>()
            r0.l = r1
            return
        L_0x01d2:
            int r3 = (r1 > r14 ? 1 : (r1 == r14 ? 0 : -1))
            if (r3 > 0) goto L_0x02c7
            boolean r3 = r0.m
            if (r3 == 0) goto L_0x01e0
            int r3 = (r1 > r14 ? 1 : (r1 == r14 ? 0 : -1))
            if (r3 < 0) goto L_0x01e0
            goto L_0x02c7
        L_0x01e0:
            if (r13 == 0) goto L_0x01ee
            long r3 = r12.b(r1)
            int r5 = (r3 > r20 ? 1 : (r3 == r20 ? 0 : -1))
            if (r5 < 0) goto L_0x01ee
            r3 = 1
            r9.b = r3
            return
        L_0x01ee:
            int r3 = r0.f
            long r3 = (long) r3
            long r14 = r14 - r1
            r5 = 1
            long r14 = r14 + r5
            long r3 = java.lang.Math.min(r3, r14)
            int r4 = (int) r3
            int r3 = (r20 > r18 ? 1 : (r20 == r18 ? 0 : -1))
            if (r3 == 0) goto L_0x020f
            r3 = 1
        L_0x01ff:
            if (r4 <= r3) goto L_0x0210
            long r7 = (long) r4
            long r7 = r7 + r1
            long r7 = r7 - r5
            long r7 = r12.b(r7)
            int r10 = (r7 > r20 ? 1 : (r7 == r20 ? 0 : -1))
            if (r10 < 0) goto L_0x0210
            int r4 = r4 + -1
            goto L_0x01ff
        L_0x020f:
            r3 = 1
        L_0x0210:
            boolean r7 = r56.isEmpty()
            if (r7 == 0) goto L_0x0219
            r39 = r54
            goto L_0x021b
        L_0x0219:
            r39 = r18
        L_0x021b:
            com.google.android.exoplayer2.upstream.DataSource r7 = r0.d
            int r8 = r0.c
            com.google.android.exoplayer2.trackselection.ExoTrackSelection r10 = r0.i
            com.google.android.exoplayer2.Format r10 = r10.g()
            com.google.android.exoplayer2.trackselection.ExoTrackSelection r11 = r0.i
            int r33 = r11.b()
            com.google.android.exoplayer2.source.dash.manifest.Representation r11 = r12.b
            long r35 = r12.b(r1)
            com.google.android.exoplayer2.source.dash.manifest.RangedUri r13 = r12.e(r1)
            java.lang.String r14 = r11.b
            com.google.android.exoplayer2.source.chunk.ChunkExtractor r15 = r12.a
            r17 = 8
            if (r15 != 0) goto L_0x0265
            long r37 = r12.c(r1)
            r3 = r27
            boolean r3 = r12.a(r1, r3)
            if (r3 == 0) goto L_0x024b
            r12 = 0
            goto L_0x024d
        L_0x024b:
            r12 = 8
        L_0x024d:
            com.google.android.exoplayer2.upstream.DataSpec r31 = com.google.android.exoplayer2.source.dash.DashUtil.a(r11, r13, r12)
            com.google.android.exoplayer2.source.chunk.SingleSampleMediaChunk r3 = new com.google.android.exoplayer2.source.chunk.SingleSampleMediaChunk
            r34 = 0
            r29 = r3
            r30 = r7
            r32 = r10
            r39 = r1
            r41 = r8
            r42 = r10
            r29.<init>(r30, r31, r32, r33, r34, r35, r37, r39, r41, r42)
            goto L_0x02c4
        L_0x0265:
            r49 = r27
            r3 = r13
            r8 = 1
            r13 = 1
        L_0x026a:
            if (r13 >= r4) goto L_0x0280
            long r5 = (long) r13
            long r5 = r5 + r1
            com.google.android.exoplayer2.source.dash.manifest.RangedUri r5 = r12.e(r5)
            com.google.android.exoplayer2.source.dash.manifest.RangedUri r5 = r3.a(r5, r14)
            if (r5 == 0) goto L_0x0280
            int r8 = r8 + 1
            int r13 = r13 + 1
            r3 = r5
            r5 = 1
            goto L_0x026a
        L_0x0280:
            long r4 = (long) r8
            long r4 = r4 + r1
            r13 = 1
            long r4 = r4 - r13
            long r37 = r12.c(r4)
            long r13 = r12.d
            int r6 = (r13 > r18 ? 1 : (r13 == r18 ? 0 : -1))
            if (r6 == 0) goto L_0x0298
            int r6 = (r13 > r37 ? 1 : (r13 == r37 ? 0 : -1))
            if (r6 > 0) goto L_0x0298
            r41 = r13
            goto L_0x029a
        L_0x0298:
            r41 = r18
        L_0x029a:
            r13 = r49
            boolean r4 = r12.a(r4, r13)
            if (r4 == 0) goto L_0x02a4
            r4 = 0
            goto L_0x02a6
        L_0x02a4:
            r4 = 8
        L_0x02a6:
            com.google.android.exoplayer2.upstream.DataSpec r31 = com.google.android.exoplayer2.source.dash.DashUtil.a(r11, r3, r4)
            long r3 = r11.c
            long r3 = -r3
            r46 = r3
            com.google.android.exoplayer2.source.chunk.ContainerMediaChunk r3 = new com.google.android.exoplayer2.source.chunk.ContainerMediaChunk
            r29 = r3
            r34 = 0
            com.google.android.exoplayer2.source.chunk.ChunkExtractor r4 = r12.a
            r48 = r4
            r30 = r7
            r32 = r10
            r43 = r1
            r45 = r8
            r29.<init>(r30, r31, r32, r33, r34, r35, r37, r39, r41, r43, r45, r46, r48)
        L_0x02c4:
            r9.a = r3
            return
        L_0x02c7:
            r9.b = r13
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.DefaultDashChunkSource.a(long, long, java.util.List, com.google.android.exoplayer2.source.chunk.ChunkHolder):void");
    }

    public final void a(Chunk chunk) {
        ChunkIndex b2;
        if (chunk instanceof InitializationChunk) {
            int a2 = this.i.a(((InitializationChunk) chunk).g);
            RepresentationHolder representationHolder = this.h[a2];
            if (representationHolder.c == null && (b2 = representationHolder.a.b()) != null) {
                this.h[a2] = new RepresentationHolder(representationHolder.d, representationHolder.b, representationHolder.a, representationHolder.e, new DashWrappingSegmentIndex(b2, representationHolder.b.c));
            }
        }
        PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler = this.g;
        if (playerTrackEmsgHandler != null) {
            if (playerTrackEmsgHandler.b == -9223372036854775807L || chunk.k > playerTrackEmsgHandler.b) {
                playerTrackEmsgHandler.b = chunk.k;
            }
            PlayerEmsgHandler.this.g = true;
        }
    }

    public final void a(DashManifest dashManifest, int i2) {
        ArrayList arrayList;
        RepresentationHolder[] representationHolderArr;
        int i3;
        RepresentationHolder representationHolder;
        long a2;
        RepresentationHolder representationHolder2;
        DefaultDashChunkSource defaultDashChunkSource = this;
        try {
            defaultDashChunkSource.j = dashManifest;
            defaultDashChunkSource.k = i2;
            long b2 = dashManifest.b(i2);
            ArrayList c2 = c();
            int i4 = 0;
            while (i4 < defaultDashChunkSource.h.length) {
                Representation representation = (Representation) c2.get(defaultDashChunkSource.i.b(i4));
                RepresentationHolder[] representationHolderArr2 = defaultDashChunkSource.h;
                RepresentationHolder representationHolder3 = representationHolderArr2[i4];
                DashSegmentIndex d2 = representationHolder3.b.d();
                DashSegmentIndex d3 = representation.d();
                if (d2 == null) {
                    try {
                        representationHolder = new RepresentationHolder(b2, representation, representationHolder3.a, representationHolder3.e, d2);
                        arrayList = c2;
                        i3 = i4;
                        representationHolderArr = representationHolderArr2;
                    } catch (BehindLiveWindowException e2) {
                        e = e2;
                    }
                } else {
                    if (!d2.b()) {
                        representationHolder2 = new RepresentationHolder(b2, representation, representationHolder3.a, representationHolder3.e, d3);
                    } else {
                        long c3 = d2.c(b2);
                        if (c3 == 0) {
                            representationHolder2 = new RepresentationHolder(b2, representation, representationHolder3.a, representationHolder3.e, d3);
                        } else {
                            long a3 = d2.a();
                            arrayList = c2;
                            long a4 = d2.a(a3);
                            long j2 = (c3 + a3) - 1;
                            long a5 = d2.a(j2) + d2.b(j2, b2);
                            i3 = i4;
                            representationHolderArr = representationHolderArr2;
                            long a6 = d3.a();
                            DashSegmentIndex dashSegmentIndex = d2;
                            long a7 = d3.a(a6);
                            Representation representation2 = representation;
                            long j3 = a3;
                            long j4 = representationHolder3.e;
                            if (a5 == a7) {
                                a2 = j4 + ((j2 + 1) - a6);
                            } else if (a5 >= a7) {
                                a2 = a7 < a4 ? j4 - (d3.a(a4, b2) - j3) : j4 + (dashSegmentIndex.a(a7, b2) - a6);
                            } else {
                                throw new BehindLiveWindowException();
                            }
                            representationHolder = new RepresentationHolder(b2, representation2, representationHolder3.a, a2, d3);
                        }
                    }
                    arrayList = c2;
                    i3 = i4;
                    representationHolderArr = representationHolderArr2;
                    representationHolder = representationHolder2;
                }
                representationHolderArr[i3] = representationHolder;
                i4 = i3 + 1;
                defaultDashChunkSource = this;
                c2 = arrayList;
            }
        } catch (BehindLiveWindowException e3) {
            e = e3;
            defaultDashChunkSource = this;
            defaultDashChunkSource.l = e;
        }
    }

    public final void a(ExoTrackSelection exoTrackSelection) {
        this.i = exoTrackSelection;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0036 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(com.google.android.exoplayer2.source.chunk.Chunk r10, boolean r11, java.lang.Exception r12, long r13) {
        /*
            r9 = this;
            r0 = 0
            if (r11 != 0) goto L_0x0004
            return r0
        L_0x0004:
            com.google.android.exoplayer2.source.dash.PlayerEmsgHandler$PlayerTrackEmsgHandler r11 = r9.g
            r1 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r3 = 1
            if (r11 == 0) goto L_0x0037
            long r4 = r11.b
            int r6 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r6 == 0) goto L_0x001e
            long r4 = r11.b
            long r6 = r10.j
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 >= 0) goto L_0x001e
            r4 = 1
            goto L_0x001f
        L_0x001e:
            r4 = 0
        L_0x001f:
            com.google.android.exoplayer2.source.dash.PlayerEmsgHandler r11 = com.google.android.exoplayer2.source.dash.PlayerEmsgHandler.this
            com.google.android.exoplayer2.source.dash.manifest.DashManifest r5 = r11.e
            boolean r5 = r5.d
            if (r5 == 0) goto L_0x0033
            boolean r5 = r11.h
            if (r5 == 0) goto L_0x002d
        L_0x002b:
            r11 = 1
            goto L_0x0034
        L_0x002d:
            if (r4 == 0) goto L_0x0033
            r11.a()
            goto L_0x002b
        L_0x0033:
            r11 = 0
        L_0x0034:
            if (r11 == 0) goto L_0x0037
            return r3
        L_0x0037:
            com.google.android.exoplayer2.source.dash.manifest.DashManifest r11 = r9.j
            boolean r11 = r11.d
            if (r11 != 0) goto L_0x007f
            boolean r11 = r10 instanceof com.google.android.exoplayer2.source.chunk.MediaChunk
            if (r11 == 0) goto L_0x007f
            boolean r11 = r12 instanceof com.google.android.exoplayer2.upstream.HttpDataSource.InvalidResponseCodeException
            if (r11 == 0) goto L_0x007f
            com.google.android.exoplayer2.upstream.HttpDataSource$InvalidResponseCodeException r12 = (com.google.android.exoplayer2.upstream.HttpDataSource.InvalidResponseCodeException) r12
            int r11 = r12.a
            r12 = 404(0x194, float:5.66E-43)
            if (r11 != r12) goto L_0x007f
            com.google.android.exoplayer2.source.dash.DefaultDashChunkSource$RepresentationHolder[] r11 = r9.h
            com.google.android.exoplayer2.trackselection.ExoTrackSelection r12 = r9.i
            com.google.android.exoplayer2.Format r4 = r10.g
            int r12 = r12.a((com.google.android.exoplayer2.Format) r4)
            r11 = r11[r12]
            long r4 = r11.b()
            r6 = -1
            int r12 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r12 == 0) goto L_0x007f
            r6 = 0
            int r12 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r12 == 0) goto L_0x007f
            long r11 = r11.a()
            long r11 = r11 + r4
            r4 = 1
            long r11 = r11 - r4
            r4 = r10
            com.google.android.exoplayer2.source.chunk.MediaChunk r4 = (com.google.android.exoplayer2.source.chunk.MediaChunk) r4
            long r4 = r4.g()
            int r6 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r6 <= 0) goto L_0x007f
            r9.m = r3
            return r3
        L_0x007f:
            int r11 = (r13 > r1 ? 1 : (r13 == r1 ? 0 : -1))
            if (r11 == 0) goto L_0x0092
            com.google.android.exoplayer2.trackselection.ExoTrackSelection r11 = r9.i
            com.google.android.exoplayer2.Format r10 = r10.g
            int r10 = r11.a((com.google.android.exoplayer2.Format) r10)
            boolean r10 = r11.a((int) r10, (long) r13)
            if (r10 == 0) goto L_0x0092
            return r3
        L_0x0092:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.DefaultDashChunkSource.a(com.google.android.exoplayer2.source.chunk.Chunk, boolean, java.lang.Exception, long):boolean");
    }

    public final boolean b() {
        if (this.l != null) {
            return false;
        }
        return this.i.l();
    }
}
