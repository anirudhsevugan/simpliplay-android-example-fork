package com.google.android.exoplayer2.source.hls;

import android.net.Uri;
import android.os.SystemClock;
import android.util.Pair;
import com.dreamers.exoplayercore.repack.bG;
import com.dreamers.exoplayercore.repack.cR;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.chunk.BaseMediaChunkIterator;
import com.google.android.exoplayer2.source.chunk.Chunk;
import com.google.android.exoplayer2.source.chunk.DataChunk;
import com.google.android.exoplayer2.source.chunk.MediaChunkIterator;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker;
import com.google.android.exoplayer2.trackselection.BaseTrackSelection;
import com.google.android.exoplayer2.trackselection.ExoTrackSelection;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.UriUtil;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class HlsChunkSource {
    final Uri[] a;
    final TrackGroup b;
    final FullSegmentEncryptionKeyCache c = new FullSegmentEncryptionKeyCache();
    boolean d;
    byte[] e = Util.f;
    IOException f;
    Uri g;
    ExoTrackSelection h;
    boolean i;
    private final HlsExtractorFactory j;
    private final DataSource k;
    private final DataSource l;
    private final TimestampAdjusterProvider m;
    private final Format[] n;
    private final HlsPlaylistTracker o;
    private final List p;
    private boolean q;
    private long r = -9223372036854775807L;

    final class EncryptionKeyChunk extends DataChunk {
        byte[] b;

        public EncryptionKeyChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i, Object obj, byte[] bArr) {
            super(dataSource, dataSpec, format, i, (Object) null, bArr);
        }

        public final void a(byte[] bArr, int i) {
            this.b = Arrays.copyOf(bArr, i);
        }
    }

    public final class HlsChunkHolder {
        public Chunk a;
        public boolean b;
        public Uri c;

        public HlsChunkHolder() {
            a();
        }

        public final void a() {
            this.a = null;
            this.b = false;
            this.c = null;
        }
    }

    final class HlsMediaPlaylistSegmentIterator extends BaseMediaChunkIterator {
        private final List b;
        private final long c;

        public HlsMediaPlaylistSegmentIterator(long j, List list) {
            super(0, (long) (list.size() - 1));
            this.c = j;
            this.b = list;
        }

        public final long d() {
            b();
            return this.c + ((HlsMediaPlaylist.SegmentBase) this.b.get((int) c())).g;
        }

        public final long e() {
            b();
            HlsMediaPlaylist.SegmentBase segmentBase = (HlsMediaPlaylist.SegmentBase) this.b.get((int) c());
            return this.c + segmentBase.g + segmentBase.e;
        }
    }

    final class InitializationTrackSelection extends BaseTrackSelection {
        private int b;

        public InitializationTrackSelection(TrackGroup trackGroup, int[] iArr) {
            super(trackGroup, iArr);
            this.b = a(trackGroup.b[iArr[0]]);
        }

        public final int a() {
            return this.b;
        }

        public final void a(long j, long j2, List list, MediaChunkIterator[] mediaChunkIteratorArr) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (b(this.b, elapsedRealtime)) {
                for (int i = this.a - 1; i >= 0; i--) {
                    if (!b(i, elapsedRealtime)) {
                        this.b = i;
                        return;
                    }
                }
                throw new IllegalStateException();
            }
        }

        public final int b() {
            return 0;
        }
    }

    final class SegmentBaseHolder {
        public final HlsMediaPlaylist.SegmentBase a;
        public final long b;
        public final int c;
        public final boolean d;

        public SegmentBaseHolder(HlsMediaPlaylist.SegmentBase segmentBase, long j, int i) {
            this.a = segmentBase;
            this.b = j;
            this.c = i;
            this.d = (segmentBase instanceof HlsMediaPlaylist.Part) && ((HlsMediaPlaylist.Part) segmentBase).b;
        }
    }

    public HlsChunkSource(HlsExtractorFactory hlsExtractorFactory, HlsPlaylistTracker hlsPlaylistTracker, Uri[] uriArr, Format[] formatArr, HlsDataSourceFactory hlsDataSourceFactory, TransferListener transferListener, TimestampAdjusterProvider timestampAdjusterProvider, List list) {
        this.j = hlsExtractorFactory;
        this.o = hlsPlaylistTracker;
        this.a = uriArr;
        this.n = formatArr;
        this.m = timestampAdjusterProvider;
        this.p = list;
        DataSource a2 = hlsDataSourceFactory.a();
        this.k = a2;
        if (transferListener != null) {
            a2.a(transferListener);
        }
        this.l = hlsDataSourceFactory.a();
        this.b = new TrackGroup(formatArr);
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < uriArr.length; i2++) {
            if ((formatArr[i2].e & 16384) == 0) {
                arrayList.add(Integer.valueOf(i2));
            }
        }
        this.h = new InitializationTrackSelection(this.b, cR.a((Collection) arrayList));
    }

    private static Uri a(HlsMediaPlaylist hlsMediaPlaylist, HlsMediaPlaylist.SegmentBase segmentBase) {
        if (segmentBase == null || segmentBase.i == null) {
            return null;
        }
        return UriUtil.a(hlsMediaPlaylist.s, segmentBase.i);
    }

    private Pair a(HlsMediaChunk hlsMediaChunk, boolean z, HlsMediaPlaylist hlsMediaPlaylist, long j2, long j3) {
        boolean z2 = true;
        int i2 = -1;
        if (hlsMediaChunk == null || z) {
            long j4 = hlsMediaPlaylist.q + j2;
            if (hlsMediaChunk != null && !this.q) {
                j3 = hlsMediaChunk.j;
            }
            if (!hlsMediaPlaylist.k && j3 >= j4) {
                return new Pair(Long.valueOf(hlsMediaPlaylist.g + ((long) hlsMediaPlaylist.n.size())), -1);
            }
            long j5 = j3 - j2;
            List list = hlsMediaPlaylist.n;
            Long valueOf = Long.valueOf(j5);
            int i3 = 0;
            if (this.o.e() && hlsMediaChunk != null) {
                z2 = false;
            }
            int a2 = Util.a(list, (Comparable) valueOf, z2);
            long j6 = ((long) a2) + hlsMediaPlaylist.g;
            if (a2 >= 0) {
                HlsMediaPlaylist.Segment segment = (HlsMediaPlaylist.Segment) hlsMediaPlaylist.n.get(a2);
                List list2 = j5 < segment.g + segment.e ? segment.a : hlsMediaPlaylist.o;
                while (true) {
                    if (i3 >= list2.size()) {
                        break;
                    }
                    HlsMediaPlaylist.Part part = (HlsMediaPlaylist.Part) list2.get(i3);
                    if (j5 >= part.g + part.e) {
                        i3++;
                    } else if (part.a) {
                        j6 += list2 == hlsMediaPlaylist.o ? 1 : 0;
                        i2 = i3;
                    }
                }
            }
            return new Pair(Long.valueOf(j6), Integer.valueOf(i2));
        } else if (!hlsMediaChunk.d) {
            return new Pair(Long.valueOf(hlsMediaChunk.m), Integer.valueOf(hlsMediaChunk.c));
        } else {
            Long valueOf2 = Long.valueOf(hlsMediaChunk.c == -1 ? hlsMediaChunk.g() : hlsMediaChunk.m);
            if (hlsMediaChunk.c != -1) {
                i2 = hlsMediaChunk.c + 1;
            }
            return new Pair(valueOf2, Integer.valueOf(i2));
        }
    }

    private Chunk a(Uri uri, int i2) {
        if (uri == null) {
            return null;
        }
        byte[] b2 = this.c.b(uri);
        if (b2 != null) {
            this.c.a(uri, b2);
            return null;
        }
        DataSpec.Builder builder = new DataSpec.Builder();
        builder.a = uri;
        builder.h = 1;
        return new EncryptionKeyChunk(this.l, builder.a(), this.n[i2], this.h.b(), (Object) null, this.e);
    }

    private static List a(HlsMediaPlaylist hlsMediaPlaylist, long j2, int i2) {
        int i3 = (int) (j2 - hlsMediaPlaylist.g);
        if (i3 < 0 || hlsMediaPlaylist.n.size() < i3) {
            return bG.g();
        }
        ArrayList arrayList = new ArrayList();
        int i4 = 0;
        if (i3 < hlsMediaPlaylist.n.size()) {
            if (i2 != -1) {
                HlsMediaPlaylist.Segment segment = (HlsMediaPlaylist.Segment) hlsMediaPlaylist.n.get(i3);
                if (i2 == 0) {
                    arrayList.add(segment);
                } else if (i2 < segment.a.size()) {
                    List list = segment.a;
                    arrayList.addAll(list.subList(i2, list.size()));
                }
                i3++;
            }
            List list2 = hlsMediaPlaylist.n;
            arrayList.addAll(list2.subList(i3, list2.size()));
            i2 = 0;
        }
        if (hlsMediaPlaylist.j != -9223372036854775807L) {
            if (i2 != -1) {
                i4 = i2;
            }
            if (i4 < hlsMediaPlaylist.o.size()) {
                List list3 = hlsMediaPlaylist.o;
                arrayList.addAll(list3.subList(i4, list3.size()));
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public final int a(HlsMediaChunk hlsMediaChunk) {
        if (hlsMediaChunk.c == -1) {
            return 1;
        }
        HlsMediaPlaylist hlsMediaPlaylist = (HlsMediaPlaylist) Assertions.b((Object) this.o.a(this.a[this.b.a(hlsMediaChunk.g)], false));
        int i2 = (int) (hlsMediaChunk.m - hlsMediaPlaylist.g);
        if (i2 < 0) {
            return 1;
        }
        List list = i2 < hlsMediaPlaylist.n.size() ? ((HlsMediaPlaylist.Segment) hlsMediaPlaylist.n.get(i2)).a : hlsMediaPlaylist.o;
        if (hlsMediaChunk.c >= list.size()) {
            return 2;
        }
        HlsMediaPlaylist.Part part = (HlsMediaPlaylist.Part) list.get(hlsMediaChunk.c);
        if (part.b) {
            return 0;
        }
        return Util.a((Object) Uri.parse(UriUtil.b(hlsMediaPlaylist.s, part.c)), (Object) hlsMediaChunk.e.a) ? 1 : 2;
    }

    public final void a() {
        IOException iOException = this.f;
        if (iOException == null) {
            Uri uri = this.g;
            if (uri != null && this.i) {
                this.o.b(uri);
                return;
            }
            return;
        }
        throw iOException;
    }

    /* JADX WARNING: Removed duplicated region for block: B:64:0x01b3  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x01ef  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0208 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0209  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(long r31, long r33, java.util.List r35, boolean r36, com.google.android.exoplayer2.source.hls.HlsChunkSource.HlsChunkHolder r37) {
        /*
            r30 = this;
            r8 = r30
            r9 = r33
            r11 = r37
            boolean r0 = r35.isEmpty()
            if (r0 == 0) goto L_0x000e
            r15 = 0
            goto L_0x0015
        L_0x000e:
            java.lang.Object r0 = com.dreamers.exoplayercore.repack.C0000a.b((java.lang.Iterable) r35)
            com.google.android.exoplayer2.source.hls.HlsMediaChunk r0 = (com.google.android.exoplayer2.source.hls.HlsMediaChunk) r0
            r15 = r0
        L_0x0015:
            if (r15 != 0) goto L_0x0019
            r14 = -1
            goto L_0x0022
        L_0x0019:
            com.google.android.exoplayer2.source.TrackGroup r0 = r8.b
            com.google.android.exoplayer2.Format r1 = r15.g
            int r0 = r0.a((com.google.android.exoplayer2.Format) r1)
            r14 = r0
        L_0x0022:
            long r0 = r9 - r31
            long r2 = r8.r
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r16 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r16 == 0) goto L_0x0032
            r16 = 1
            goto L_0x0034
        L_0x0032:
            r16 = 0
        L_0x0034:
            if (r16 == 0) goto L_0x0039
            long r2 = r2 - r31
            goto L_0x003a
        L_0x0039:
            r2 = r4
        L_0x003a:
            if (r15 == 0) goto L_0x0055
            boolean r7 = r8.q
            if (r7 != 0) goto L_0x0055
            long r12 = r15.k
            long r6 = r15.j
            long r12 = r12 - r6
            long r0 = r0 - r12
            r6 = 0
            long r0 = java.lang.Math.max(r6, r0)
            int r20 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r20 == 0) goto L_0x0055
            long r2 = r2 - r12
            long r2 = java.lang.Math.max(r6, r2)
        L_0x0055:
            r21 = r0
            r23 = r2
            com.google.android.exoplayer2.source.chunk.MediaChunkIterator[] r26 = r8.a((com.google.android.exoplayer2.source.hls.HlsMediaChunk) r15, (long) r9)
            com.google.android.exoplayer2.trackselection.ExoTrackSelection r0 = r8.h
            r20 = r0
            r25 = r35
            r20.a(r21, r23, r25, r26)
            com.google.android.exoplayer2.trackselection.ExoTrackSelection r0 = r8.h
            int r12 = r0.h()
            if (r14 == r12) goto L_0x0070
            r13 = 1
            goto L_0x0071
        L_0x0070:
            r13 = 0
        L_0x0071:
            android.net.Uri[] r0 = r8.a
            r6 = r0[r12]
            com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker r0 = r8.o
            boolean r0 = r0.a((android.net.Uri) r6)
            if (r0 != 0) goto L_0x008d
            r11.c = r6
            boolean r0 = r8.i
            android.net.Uri r1 = r8.g
            boolean r1 = r6.equals(r1)
            r0 = r0 & r1
            r8.i = r0
            r8.g = r6
            return
        L_0x008d:
            com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker r0 = r8.o
            r7 = 1
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist r3 = r0.a(r6, r7)
            com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r3)
            boolean r0 = r3.u
            r8.q = r0
            boolean r0 = r3.k
            if (r0 == 0) goto L_0x00a0
            goto L_0x00ac
        L_0x00a0:
            long r0 = r3.a()
            com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker r2 = r8.o
            long r4 = r2.c()
            long r4 = r0 - r4
        L_0x00ac:
            r8.r = r4
            long r0 = r3.d
            com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker r2 = r8.o
            long r4 = r2.c()
            long r19 = r0 - r4
            r0 = r30
            r1 = r15
            r2 = r13
            r4 = r3
            r9 = r4
            r4 = r19
            r16 = r6
            r10 = 1
            r6 = r33
            android.util.Pair r0 = r0.a((com.google.android.exoplayer2.source.hls.HlsMediaChunk) r1, (boolean) r2, (com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist) r3, (long) r4, (long) r6)
            java.lang.Object r1 = r0.first
            java.lang.Long r1 = (java.lang.Long) r1
            long r1 = r1.longValue()
            java.lang.Object r0 = r0.second
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            long r3 = r9.g
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 >= 0) goto L_0x011c
            if (r15 == 0) goto L_0x011c
            if (r13 == 0) goto L_0x011c
            android.net.Uri[] r0 = r8.a
            r9 = r0[r14]
            com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker r0 = r8.o
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist r12 = r0.a(r9, r10)
            com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r12)
            long r0 = r12.d
            com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker r2 = r8.o
            long r2 = r2.c()
            long r19 = r0 - r2
            r2 = 0
            r0 = r30
            r1 = r15
            r3 = r12
            r4 = r19
            r6 = r33
            android.util.Pair r0 = r0.a((com.google.android.exoplayer2.source.hls.HlsMediaChunk) r1, (boolean) r2, (com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist) r3, (long) r4, (long) r6)
            java.lang.Object r1 = r0.first
            java.lang.Long r1 = (java.lang.Long) r1
            long r1 = r1.longValue()
            java.lang.Object r0 = r0.second
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            r7 = r0
            r0 = r9
            r9 = r12
            r12 = r14
            goto L_0x011f
        L_0x011c:
            r7 = r0
            r0 = r16
        L_0x011f:
            long r3 = r9.g
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 >= 0) goto L_0x012d
            com.google.android.exoplayer2.source.BehindLiveWindowException r0 = new com.google.android.exoplayer2.source.BehindLiveWindowException
            r0.<init>()
            r8.f = r0
            return
        L_0x012d:
            long r3 = r9.g
            long r3 = r1 - r3
            int r4 = (int) r3
            java.util.List r3 = r9.n
            int r3 = r3.size()
            r5 = 1
            if (r4 != r3) goto L_0x0159
            r3 = -1
            if (r7 == r3) goto L_0x0140
            goto L_0x0141
        L_0x0140:
            r7 = 0
        L_0x0141:
            java.util.List r3 = r9.o
            int r3 = r3.size()
            if (r7 >= r3) goto L_0x0157
            com.google.android.exoplayer2.source.hls.HlsChunkSource$SegmentBaseHolder r3 = new com.google.android.exoplayer2.source.hls.HlsChunkSource$SegmentBaseHolder
            java.util.List r4 = r9.o
            java.lang.Object r4 = r4.get(r7)
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$SegmentBase r4 = (com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist.SegmentBase) r4
            r3.<init>(r4, r1, r7)
            goto L_0x01b1
        L_0x0157:
            r3 = 0
            goto L_0x01b1
        L_0x0159:
            java.util.List r3 = r9.n
            java.lang.Object r3 = r3.get(r4)
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$Segment r3 = (com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist.Segment) r3
            r13 = -1
            if (r7 != r13) goto L_0x016b
            com.google.android.exoplayer2.source.hls.HlsChunkSource$SegmentBaseHolder r4 = new com.google.android.exoplayer2.source.hls.HlsChunkSource$SegmentBaseHolder
            r4.<init>(r3, r1, r13)
        L_0x0169:
            r3 = r4
            goto L_0x01b1
        L_0x016b:
            java.util.List r13 = r3.a
            int r13 = r13.size()
            if (r7 >= r13) goto L_0x0181
            com.google.android.exoplayer2.source.hls.HlsChunkSource$SegmentBaseHolder r4 = new com.google.android.exoplayer2.source.hls.HlsChunkSource$SegmentBaseHolder
            java.util.List r3 = r3.a
            java.lang.Object r3 = r3.get(r7)
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$SegmentBase r3 = (com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist.SegmentBase) r3
            r4.<init>(r3, r1, r7)
            goto L_0x0169
        L_0x0181:
            int r4 = r4 + r10
            java.util.List r3 = r9.n
            int r3 = r3.size()
            if (r4 >= r3) goto L_0x019a
            com.google.android.exoplayer2.source.hls.HlsChunkSource$SegmentBaseHolder r3 = new com.google.android.exoplayer2.source.hls.HlsChunkSource$SegmentBaseHolder
            java.util.List r7 = r9.n
            java.lang.Object r4 = r7.get(r4)
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$SegmentBase r4 = (com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist.SegmentBase) r4
            long r1 = r1 + r5
            r7 = -1
            r3.<init>(r4, r1, r7)
            goto L_0x01b1
        L_0x019a:
            java.util.List r3 = r9.o
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L_0x0157
            com.google.android.exoplayer2.source.hls.HlsChunkSource$SegmentBaseHolder r3 = new com.google.android.exoplayer2.source.hls.HlsChunkSource$SegmentBaseHolder
            java.util.List r4 = r9.o
            r7 = 0
            java.lang.Object r4 = r4.get(r7)
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$SegmentBase r4 = (com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist.SegmentBase) r4
            long r1 = r1 + r5
            r3.<init>(r4, r1, r7)
        L_0x01b1:
            if (r3 != 0) goto L_0x01ef
            boolean r1 = r9.k
            if (r1 != 0) goto L_0x01c7
            r11.c = r0
            boolean r1 = r8.i
            android.net.Uri r2 = r8.g
            boolean r2 = r0.equals(r2)
            r1 = r1 & r2
            r8.i = r1
            r8.g = r0
            return
        L_0x01c7:
            if (r36 != 0) goto L_0x01ec
            java.util.List r1 = r9.n
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x01d2
            goto L_0x01ec
        L_0x01d2:
            com.google.android.exoplayer2.source.hls.HlsChunkSource$SegmentBaseHolder r1 = new com.google.android.exoplayer2.source.hls.HlsChunkSource$SegmentBaseHolder
            java.util.List r2 = r9.n
            java.lang.Object r2 = com.dreamers.exoplayercore.repack.C0000a.b((java.lang.Iterable) r2)
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$SegmentBase r2 = (com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist.SegmentBase) r2
            long r3 = r9.g
            java.util.List r7 = r9.n
            int r7 = r7.size()
            long r13 = (long) r7
            long r3 = r3 + r13
            long r3 = r3 - r5
            r5 = -1
            r1.<init>(r2, r3, r5)
            goto L_0x01f0
        L_0x01ec:
            r11.b = r10
            return
        L_0x01ef:
            r1 = r3
        L_0x01f0:
            r2 = 0
            r8.i = r2
            r2 = 0
            r8.g = r2
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$SegmentBase r2 = r1.a
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$Segment r2 = r2.d
            android.net.Uri r2 = a((com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist) r9, (com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist.SegmentBase) r2)
            com.google.android.exoplayer2.source.chunk.Chunk r3 = r8.a((android.net.Uri) r2, (int) r12)
            r11.a = r3
            com.google.android.exoplayer2.source.chunk.Chunk r3 = r11.a
            if (r3 == 0) goto L_0x0209
            return
        L_0x0209:
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$SegmentBase r3 = r1.a
            android.net.Uri r3 = a((com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist) r9, (com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist.SegmentBase) r3)
            com.google.android.exoplayer2.source.chunk.Chunk r4 = r8.a((android.net.Uri) r3, (int) r12)
            r11.a = r4
            com.google.android.exoplayer2.source.chunk.Chunk r4 = r11.a
            if (r4 == 0) goto L_0x021a
            return
        L_0x021a:
            r31 = r15
            r32 = r0
            r33 = r9
            r34 = r1
            r35 = r19
            boolean r29 = com.google.android.exoplayer2.source.hls.HlsMediaChunk.a(r31, r32, r33, r34, r35)
            if (r29 == 0) goto L_0x022f
            boolean r4 = r1.d
            if (r4 == 0) goto L_0x022f
            return
        L_0x022f:
            com.google.android.exoplayer2.source.hls.HlsExtractorFactory r13 = r8.j
            com.google.android.exoplayer2.upstream.DataSource r14 = r8.k
            com.google.android.exoplayer2.Format[] r4 = r8.n
            r4 = r4[r12]
            r12 = r15
            r15 = r4
            java.util.List r4 = r8.p
            r21 = r4
            com.google.android.exoplayer2.trackselection.ExoTrackSelection r4 = r8.h
            int r22 = r4.b()
            r23 = 0
            boolean r4 = r8.d
            r24 = r4
            com.google.android.exoplayer2.source.hls.TimestampAdjusterProvider r4 = r8.m
            r25 = r4
            com.google.android.exoplayer2.source.hls.FullSegmentEncryptionKeyCache r4 = r8.c
            byte[] r27 = r4.a(r3)
            com.google.android.exoplayer2.source.hls.FullSegmentEncryptionKeyCache r3 = r8.c
            byte[] r28 = r3.a(r2)
            r16 = r19
            r18 = r9
            r19 = r1
            r20 = r0
            r26 = r12
            com.google.android.exoplayer2.source.hls.HlsMediaChunk r0 = com.google.android.exoplayer2.source.hls.HlsMediaChunk.a(r13, r14, r15, r16, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29)
            r11.a = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.hls.HlsChunkSource.a(long, long, java.util.List, boolean, com.google.android.exoplayer2.source.hls.HlsChunkSource$HlsChunkHolder):void");
    }

    public final MediaChunkIterator[] a(HlsMediaChunk hlsMediaChunk, long j2) {
        int i2;
        HlsMediaChunk hlsMediaChunk2 = hlsMediaChunk;
        int a2 = hlsMediaChunk2 == null ? -1 : this.b.a(hlsMediaChunk2.g);
        int f2 = this.h.f();
        MediaChunkIterator[] mediaChunkIteratorArr = new MediaChunkIterator[f2];
        boolean z = false;
        int i3 = 0;
        while (i3 < f2) {
            int b2 = this.h.b(i3);
            Uri uri = this.a[b2];
            if (!this.o.a(uri)) {
                mediaChunkIteratorArr[i3] = MediaChunkIterator.a;
                i2 = i3;
            } else {
                HlsMediaPlaylist a3 = this.o.a(uri, z);
                Assertions.b((Object) a3);
                long c2 = a3.d - this.o.c();
                i2 = i3;
                Pair a4 = a(hlsMediaChunk, b2 != a2, a3, c2, j2);
                mediaChunkIteratorArr[i2] = new HlsMediaPlaylistSegmentIterator(c2, a(a3, ((Long) a4.first).longValue(), ((Integer) a4.second).intValue()));
            }
            i3 = i2 + 1;
            z = false;
        }
        return mediaChunkIteratorArr;
    }
}
