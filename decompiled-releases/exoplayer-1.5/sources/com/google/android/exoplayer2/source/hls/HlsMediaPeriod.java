package com.google.android.exoplayer2.source.hls;

import android.net.Uri;
import android.text.TextUtils;
import com.dreamers.exoplayercore.repack.C0000a;
import com.dreamers.exoplayercore.repack.cR;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.source.CompositeSequenceableLoaderFactory;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.SequenceableLoader;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public final class HlsMediaPeriod implements MediaPeriod, HlsSampleStreamWrapper.Callback, HlsPlaylistTracker.PlaylistEventListener {
    final HlsPlaylistTracker a;
    MediaPeriod.Callback b;
    HlsSampleStreamWrapper[] c = new HlsSampleStreamWrapper[0];
    private final HlsExtractorFactory d;
    private final HlsDataSourceFactory e;
    private final TransferListener f;
    private final DrmSessionManager g;
    private final DrmSessionEventListener.EventDispatcher h;
    private final LoadErrorHandlingPolicy i;
    private final MediaSourceEventListener.EventDispatcher j;
    private final Allocator k;
    private final IdentityHashMap l = new IdentityHashMap();
    private final TimestampAdjusterProvider m = new TimestampAdjusterProvider();
    private final CompositeSequenceableLoaderFactory n;
    private final boolean o;
    private final int p;
    private final boolean q;
    private int r;
    private TrackGroupArray s;
    private HlsSampleStreamWrapper[] t = new HlsSampleStreamWrapper[0];
    private int u;
    private SequenceableLoader v;

    public HlsMediaPeriod(HlsExtractorFactory hlsExtractorFactory, HlsPlaylistTracker hlsPlaylistTracker, HlsDataSourceFactory hlsDataSourceFactory, TransferListener transferListener, DrmSessionManager drmSessionManager, DrmSessionEventListener.EventDispatcher eventDispatcher, LoadErrorHandlingPolicy loadErrorHandlingPolicy, MediaSourceEventListener.EventDispatcher eventDispatcher2, Allocator allocator, CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory, boolean z, int i2, boolean z2) {
        this.d = hlsExtractorFactory;
        this.a = hlsPlaylistTracker;
        this.e = hlsDataSourceFactory;
        this.f = transferListener;
        this.g = drmSessionManager;
        this.h = eventDispatcher;
        this.i = loadErrorHandlingPolicy;
        this.j = eventDispatcher2;
        this.k = allocator;
        this.n = compositeSequenceableLoaderFactory;
        this.o = z;
        this.p = i2;
        this.q = z2;
        this.v = compositeSequenceableLoaderFactory.a(new SequenceableLoader[0]);
    }

    private static Format a(Format format, Format format2, boolean z) {
        String str;
        String str2;
        int i2;
        int i3;
        int i4;
        Metadata metadata;
        String str3;
        int i5 = -1;
        if (format2 != null) {
            str3 = format2.i;
            metadata = format2.j;
            i4 = format2.w;
            i3 = format2.d;
            i2 = format2.e;
            str2 = format2.c;
            str = format2.b;
        } else {
            str3 = Util.b(format.i, 1);
            metadata = format.j;
            if (z) {
                i4 = format.w;
                i3 = format.d;
                i2 = format.e;
                str2 = format.c;
                str = format.b;
            } else {
                i3 = 0;
                str2 = null;
                str = null;
                i4 = -1;
                i2 = 0;
            }
        }
        String g2 = MimeTypes.g(str3);
        int i6 = z ? format.f : -1;
        if (z) {
            i5 = format.g;
        }
        Format.Builder builder = new Format.Builder();
        builder.a = format.a;
        builder.b = str;
        builder.j = format.k;
        builder.k = g2;
        builder.h = str3;
        builder.i = metadata;
        builder.f = i6;
        builder.g = i5;
        builder.x = i4;
        builder.d = i3;
        builder.e = i2;
        builder.c = str2;
        return builder.a();
    }

    private HlsSampleStreamWrapper a(int i2, Uri[] uriArr, Format[] formatArr, Format format, List list, Map map, long j2) {
        return new HlsSampleStreamWrapper(i2, this, new HlsChunkSource(this.d, this.a, uriArr, formatArr, this.e, this.f, this.m, list), map, this.k, j2, format, this.g, this.h, this.i, this.j, this.p);
    }

    private void a(long j2, List list, List list2, List list3, Map map) {
        List list4 = list;
        ArrayList arrayList = new ArrayList(list.size());
        ArrayList arrayList2 = new ArrayList(list.size());
        ArrayList arrayList3 = new ArrayList(list.size());
        HashSet hashSet = new HashSet();
        for (int i2 = 0; i2 < list.size(); i2++) {
            String str = ((HlsMasterPlaylist.Rendition) list4.get(i2)).c;
            if (hashSet.add(str)) {
                arrayList.clear();
                arrayList2.clear();
                arrayList3.clear();
                boolean z = true;
                for (int i3 = 0; i3 < list.size(); i3++) {
                    if (Util.a((Object) str, (Object) ((HlsMasterPlaylist.Rendition) list4.get(i3)).c)) {
                        HlsMasterPlaylist.Rendition rendition = (HlsMasterPlaylist.Rendition) list4.get(i3);
                        arrayList3.add(Integer.valueOf(i3));
                        arrayList.add(rendition.a);
                        arrayList2.add(rendition.b);
                        z &= Util.a(rendition.b.i, 1) == 1;
                    }
                }
                HlsSampleStreamWrapper a2 = a(1, (Uri[]) arrayList.toArray((Uri[]) Util.a((Object[]) new Uri[0])), (Format[]) arrayList2.toArray(new Format[0]), (Format) null, Collections.emptyList(), map, j2);
                list3.add(cR.a((Collection) arrayList3));
                list2.add(a2);
                if (this.o && z) {
                    a2.a(new TrackGroup[]{new TrackGroup((Format[]) arrayList2.toArray(new Format[0]))}, new int[0]);
                }
            } else {
                List list5 = list2;
                List list6 = list3;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0064 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist r20, long r21, java.util.List r23, java.util.List r24, java.util.Map r25) {
        /*
            r19 = this;
            r0 = r20
            java.util.List r1 = r0.c
            int r1 = r1.size()
            int[] r2 = new int[r1]
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
        L_0x000e:
            java.util.List r7 = r0.c
            int r7 = r7.size()
            r8 = 2
            r9 = 1
            if (r4 >= r7) goto L_0x0047
            java.util.List r7 = r0.c
            java.lang.Object r7 = r7.get(r4)
            com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$Variant r7 = (com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist.Variant) r7
            com.google.android.exoplayer2.Format r7 = r7.b
            int r10 = r7.height
            if (r10 > 0) goto L_0x0040
            java.lang.String r10 = r7.i
            java.lang.String r10 = com.google.android.exoplayer2.util.Util.b((java.lang.String) r10, (int) r8)
            if (r10 == 0) goto L_0x002f
            goto L_0x0040
        L_0x002f:
            java.lang.String r7 = r7.i
            java.lang.String r7 = com.google.android.exoplayer2.util.Util.b((java.lang.String) r7, (int) r9)
            if (r7 == 0) goto L_0x003c
            r2[r4] = r9
            int r6 = r6 + 1
            goto L_0x0044
        L_0x003c:
            r7 = -1
            r2[r4] = r7
            goto L_0x0044
        L_0x0040:
            r2[r4] = r8
            int r5 = r5 + 1
        L_0x0044:
            int r4 = r4 + 1
            goto L_0x000e
        L_0x0047:
            if (r5 <= 0) goto L_0x004c
            r1 = r5
            r4 = 1
            goto L_0x0053
        L_0x004c:
            if (r6 >= r1) goto L_0x0052
            int r1 = r1 - r6
            r4 = 0
            r5 = 1
            goto L_0x0054
        L_0x0052:
            r4 = 0
        L_0x0053:
            r5 = 0
        L_0x0054:
            android.net.Uri[] r12 = new android.net.Uri[r1]
            com.google.android.exoplayer2.Format[] r6 = new com.google.android.exoplayer2.Format[r1]
            int[] r7 = new int[r1]
            r10 = 0
            r11 = 0
        L_0x005c:
            java.util.List r13 = r0.c
            int r13 = r13.size()
            if (r10 >= r13) goto L_0x0088
            if (r4 == 0) goto L_0x006a
            r13 = r2[r10]
            if (r13 != r8) goto L_0x0085
        L_0x006a:
            if (r5 == 0) goto L_0x0070
            r13 = r2[r10]
            if (r13 == r9) goto L_0x0085
        L_0x0070:
            java.util.List r13 = r0.c
            java.lang.Object r13 = r13.get(r10)
            com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$Variant r13 = (com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist.Variant) r13
            android.net.Uri r14 = r13.a
            r12[r11] = r14
            com.google.android.exoplayer2.Format r13 = r13.b
            r6[r11] = r13
            int r13 = r11 + 1
            r7[r11] = r10
            r11 = r13
        L_0x0085:
            int r10 = r10 + 1
            goto L_0x005c
        L_0x0088:
            r2 = r6[r3]
            java.lang.String r2 = r2.i
            int r4 = com.google.android.exoplayer2.util.Util.a((java.lang.String) r2, (int) r8)
            int r2 = com.google.android.exoplayer2.util.Util.a((java.lang.String) r2, (int) r9)
            if (r2 > r9) goto L_0x009e
            if (r4 > r9) goto L_0x009e
            int r5 = r2 + r4
            if (r5 <= 0) goto L_0x009e
            r5 = 1
            goto L_0x009f
        L_0x009e:
            r5 = 0
        L_0x009f:
            r11 = 0
            com.google.android.exoplayer2.Format r14 = r0.f
            java.util.List r15 = r0.g
            r10 = r19
            r13 = r6
            r16 = r25
            r17 = r21
            com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper r10 = r10.a(r11, r12, r13, r14, r15, r16, r17)
            r11 = r23
            r11.add(r10)
            r11 = r24
            r11.add(r7)
            r7 = r19
            boolean r11 = r7.o
            if (r11 == 0) goto L_0x01ac
            if (r5 == 0) goto L_0x01ac
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            if (r4 <= 0) goto L_0x0162
            com.google.android.exoplayer2.Format[] r4 = new com.google.android.exoplayer2.Format[r1]
            r11 = 0
        L_0x00cb:
            if (r11 >= r1) goto L_0x0118
            r12 = r6[r11]
            java.lang.String r13 = r12.i
            java.lang.String r13 = com.google.android.exoplayer2.util.Util.b((java.lang.String) r13, (int) r8)
            java.lang.String r14 = com.google.android.exoplayer2.util.MimeTypes.g(r13)
            com.google.android.exoplayer2.Format$Builder r15 = new com.google.android.exoplayer2.Format$Builder
            r15.<init>()
            java.lang.String r8 = r12.a
            r15.a = r8
            java.lang.String r8 = r12.b
            r15.b = r8
            java.lang.String r8 = r12.k
            r15.j = r8
            r15.k = r14
            r15.h = r13
            com.google.android.exoplayer2.metadata.Metadata r8 = r12.j
            r15.i = r8
            int r8 = r12.f
            r15.f = r8
            int r8 = r12.g
            r15.g = r8
            int r8 = r12.width
            r15.p = r8
            int r8 = r12.height
            r15.q = r8
            float r8 = r12.q
            r15.r = r8
            int r8 = r12.d
            r15.d = r8
            int r8 = r12.e
            r15.e = r8
            com.google.android.exoplayer2.Format r8 = r15.a()
            r4[r11] = r8
            int r11 = r11 + 1
            r8 = 2
            goto L_0x00cb
        L_0x0118:
            com.google.android.exoplayer2.source.TrackGroup r1 = new com.google.android.exoplayer2.source.TrackGroup
            r1.<init>((com.google.android.exoplayer2.Format[]) r4)
            r5.add(r1)
            if (r2 <= 0) goto L_0x0142
            com.google.android.exoplayer2.Format r1 = r0.f
            if (r1 != 0) goto L_0x012e
            java.util.List r1 = r0.d
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x0142
        L_0x012e:
            com.google.android.exoplayer2.source.TrackGroup r1 = new com.google.android.exoplayer2.source.TrackGroup
            com.google.android.exoplayer2.Format[] r2 = new com.google.android.exoplayer2.Format[r9]
            r4 = r6[r3]
            com.google.android.exoplayer2.Format r6 = r0.f
            com.google.android.exoplayer2.Format r4 = a(r4, r6, r3)
            r2[r3] = r4
            r1.<init>((com.google.android.exoplayer2.Format[]) r2)
            r5.add(r1)
        L_0x0142:
            java.util.List r0 = r0.g
            if (r0 == 0) goto L_0x017c
            r1 = 0
        L_0x0147:
            int r2 = r0.size()
            if (r1 >= r2) goto L_0x017c
            com.google.android.exoplayer2.source.TrackGroup r2 = new com.google.android.exoplayer2.source.TrackGroup
            com.google.android.exoplayer2.Format[] r4 = new com.google.android.exoplayer2.Format[r9]
            java.lang.Object r6 = r0.get(r1)
            com.google.android.exoplayer2.Format r6 = (com.google.android.exoplayer2.Format) r6
            r4[r3] = r6
            r2.<init>((com.google.android.exoplayer2.Format[]) r4)
            r5.add(r2)
            int r1 = r1 + 1
            goto L_0x0147
        L_0x0162:
            com.google.android.exoplayer2.Format[] r2 = new com.google.android.exoplayer2.Format[r1]
            r4 = 0
        L_0x0165:
            if (r4 >= r1) goto L_0x0174
            r8 = r6[r4]
            com.google.android.exoplayer2.Format r11 = r0.f
            com.google.android.exoplayer2.Format r8 = a(r8, r11, r9)
            r2[r4] = r8
            int r4 = r4 + 1
            goto L_0x0165
        L_0x0174:
            com.google.android.exoplayer2.source.TrackGroup r0 = new com.google.android.exoplayer2.source.TrackGroup
            r0.<init>((com.google.android.exoplayer2.Format[]) r2)
            r5.add(r0)
        L_0x017c:
            com.google.android.exoplayer2.source.TrackGroup r0 = new com.google.android.exoplayer2.source.TrackGroup
            com.google.android.exoplayer2.Format[] r1 = new com.google.android.exoplayer2.Format[r9]
            com.google.android.exoplayer2.Format$Builder r2 = new com.google.android.exoplayer2.Format$Builder
            r2.<init>()
            java.lang.String r4 = "ID3"
            r2.a = r4
            java.lang.String r4 = "application/id3"
            r2.k = r4
            com.google.android.exoplayer2.Format r2 = r2.a()
            r1[r3] = r2
            r0.<init>((com.google.android.exoplayer2.Format[]) r1)
            r5.add(r0)
            com.google.android.exoplayer2.source.TrackGroup[] r1 = new com.google.android.exoplayer2.source.TrackGroup[r3]
            java.lang.Object[] r1 = r5.toArray(r1)
            com.google.android.exoplayer2.source.TrackGroup[] r1 = (com.google.android.exoplayer2.source.TrackGroup[]) r1
            int r0 = r5.indexOf(r0)
            int[] r0 = new int[]{r0}
            r10.a((com.google.android.exoplayer2.source.TrackGroup[]) r1, (int[]) r0)
        L_0x01ac:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.hls.HlsMediaPeriod.a(com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist, long, java.util.List, java.util.List, java.util.Map):void");
    }

    public final long a(long j2, SeekParameters seekParameters) {
        return j2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:106:0x01f0  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x01fb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long a(com.google.android.exoplayer2.trackselection.ExoTrackSelection[] r32, boolean[] r33, com.google.android.exoplayer2.source.SampleStream[] r34, boolean[] r35, long r36) {
        /*
            r31 = this;
            r0 = r31
            r1 = r32
            r2 = r34
            r3 = r36
            int r5 = r1.length
            int[] r5 = new int[r5]
            int r6 = r1.length
            int[] r6 = new int[r6]
            r8 = 0
        L_0x000f:
            int r9 = r1.length
            r10 = -1
            if (r8 >= r9) goto L_0x004c
            r9 = r2[r8]
            if (r9 != 0) goto L_0x0019
            r9 = -1
            goto L_0x0025
        L_0x0019:
            java.util.IdentityHashMap r11 = r0.l
            java.lang.Object r9 = r11.get(r9)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
        L_0x0025:
            r5[r8] = r9
            r6[r8] = r10
            r9 = r1[r8]
            if (r9 == 0) goto L_0x0049
            com.google.android.exoplayer2.source.TrackGroup r9 = r9.e()
            r11 = 0
        L_0x0032:
            com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper[] r12 = r0.c
            int r13 = r12.length
            if (r11 >= r13) goto L_0x0049
            r12 = r12[r11]
            com.google.android.exoplayer2.source.TrackGroupArray r12 = r12.c()
            int r12 = r12.a(r9)
            if (r12 == r10) goto L_0x0046
            r6[r8] = r11
            goto L_0x0049
        L_0x0046:
            int r11 = r11 + 1
            goto L_0x0032
        L_0x0049:
            int r8 = r8 + 1
            goto L_0x000f
        L_0x004c:
            java.util.IdentityHashMap r8 = r0.l
            r8.clear()
            int r8 = r1.length
            com.google.android.exoplayer2.source.SampleStream[] r9 = new com.google.android.exoplayer2.source.SampleStream[r8]
            int r11 = r1.length
            com.google.android.exoplayer2.source.SampleStream[] r12 = new com.google.android.exoplayer2.source.SampleStream[r11]
            int r13 = r1.length
            com.google.android.exoplayer2.trackselection.ExoTrackSelection[] r14 = new com.google.android.exoplayer2.trackselection.ExoTrackSelection[r13]
            com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper[] r15 = r0.c
            int r15 = r15.length
            com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper[] r15 = new com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper[r15]
            r7 = 0
            r16 = 0
            r17 = 0
        L_0x0064:
            com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper[] r10 = r0.c
            int r10 = r10.length
            if (r7 >= r10) goto L_0x0294
            r18 = r8
            r10 = 0
        L_0x006c:
            int r8 = r1.length
            r19 = r15
            if (r10 >= r8) goto L_0x008a
            r8 = r5[r10]
            if (r8 != r7) goto L_0x0078
            r8 = r2[r10]
            goto L_0x0079
        L_0x0078:
            r8 = 0
        L_0x0079:
            r12[r10] = r8
            r8 = r6[r10]
            if (r8 != r7) goto L_0x0082
            r15 = r1[r10]
            goto L_0x0083
        L_0x0082:
            r15 = 0
        L_0x0083:
            r14[r10] = r15
            int r10 = r10 + 1
            r15 = r19
            goto L_0x006c
        L_0x008a:
            com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper[] r8 = r0.c
            r8 = r8[r7]
            r8.o()
            int r10 = r8.n
            r15 = 0
        L_0x0094:
            if (r15 >= r13) goto L_0x00eb
            r20 = r12[r15]
            r2 = r20
            com.google.android.exoplayer2.source.hls.HlsSampleStream r2 = (com.google.android.exoplayer2.source.hls.HlsSampleStream) r2
            if (r2 == 0) goto L_0x00dd
            r20 = r14[r15]
            if (r20 == 0) goto L_0x00a6
            boolean r20 = r33[r15]
            if (r20 != 0) goto L_0x00dd
        L_0x00a6:
            r20 = r5
            int r5 = r8.n
            r21 = 1
            int r5 = r5 + -1
            r8.n = r5
            int r5 = r2.c
            r0 = -1
            if (r5 == r0) goto L_0x00d7
            com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper r0 = r2.b
            int r5 = r2.a
            r0.o()
            r22 = r9
            int[] r9 = r0.s
            com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r9)
            int[] r9 = r0.s
            r5 = r9[r5]
            boolean[] r9 = r0.u
            boolean r9 = r9[r5]
            com.google.android.exoplayer2.util.Assertions.b((boolean) r9)
            boolean[] r0 = r0.u
            r9 = 0
            r0[r5] = r9
            r0 = -1
            r2.c = r0
            goto L_0x00d9
        L_0x00d7:
            r22 = r9
        L_0x00d9:
            r2 = 0
            r12[r15] = r2
            goto L_0x00e2
        L_0x00dd:
            r20 = r5
            r22 = r9
            r0 = -1
        L_0x00e2:
            int r15 = r15 + 1
            r0 = r31
            r5 = r20
            r9 = r22
            goto L_0x0094
        L_0x00eb:
            r20 = r5
            r22 = r9
            r0 = -1
            if (r17 != 0) goto L_0x0102
            boolean r2 = r8.x
            if (r2 == 0) goto L_0x00f9
            if (r10 != 0) goto L_0x0100
            goto L_0x0102
        L_0x00f9:
            long r9 = r8.v
            int r2 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r2 == 0) goto L_0x0100
            goto L_0x0102
        L_0x0100:
            r2 = 0
            goto L_0x0103
        L_0x0102:
            r2 = 1
        L_0x0103:
            com.google.android.exoplayer2.source.hls.HlsChunkSource r5 = r8.c
            com.google.android.exoplayer2.trackselection.ExoTrackSelection r5 = r5.h
            r9 = r2
            r10 = r5
            r2 = 0
        L_0x010a:
            if (r2 >= r13) goto L_0x0166
            r15 = r14[r2]
            if (r15 == 0) goto L_0x015e
            com.google.android.exoplayer2.source.TrackGroupArray r0 = r8.r
            r30 = r13
            com.google.android.exoplayer2.source.TrackGroup r13 = r15.e()
            int r0 = r0.a(r13)
            int r13 = r8.t
            if (r0 != r13) goto L_0x0125
            com.google.android.exoplayer2.source.hls.HlsChunkSource r10 = r8.c
            r10.h = r15
            r10 = r15
        L_0x0125:
            r13 = r12[r2]
            if (r13 != 0) goto L_0x0160
            int r13 = r8.n
            r15 = 1
            int r13 = r13 + r15
            r8.n = r13
            com.google.android.exoplayer2.source.hls.HlsSampleStream r13 = new com.google.android.exoplayer2.source.hls.HlsSampleStream
            r13.<init>(r8, r0)
            r12[r2] = r13
            r35[r2] = r15
            int[] r13 = r8.s
            if (r13 == 0) goto L_0x0160
            r13 = r12[r2]
            com.google.android.exoplayer2.source.hls.HlsSampleStream r13 = (com.google.android.exoplayer2.source.hls.HlsSampleStream) r13
            r13.c()
            if (r9 != 0) goto L_0x0160
            com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper$HlsSampleQueue[] r9 = r8.j
            int[] r13 = r8.s
            r0 = r13[r0]
            r0 = r9[r0]
            boolean r9 = r0.a((long) r3, (boolean) r15)
            if (r9 != 0) goto L_0x015b
            int r0 = r0.e()
            if (r0 == 0) goto L_0x015b
            r0 = 1
            goto L_0x015c
        L_0x015b:
            r0 = 0
        L_0x015c:
            r9 = r0
            goto L_0x0160
        L_0x015e:
            r30 = r13
        L_0x0160:
            int r2 = r2 + 1
            r13 = r30
            r0 = -1
            goto L_0x010a
        L_0x0166:
            r30 = r13
            int r0 = r8.n
            if (r0 != 0) goto L_0x01a1
            com.google.android.exoplayer2.source.hls.HlsChunkSource r0 = r8.c
            r2 = 0
            r0.f = r2
            r8.p = r2
            r0 = 1
            r8.w = r0
            java.util.ArrayList r0 = r8.f
            r0.clear()
            com.google.android.exoplayer2.upstream.Loader r0 = r8.d
            boolean r0 = r0.c()
            if (r0 == 0) goto L_0x019b
            boolean r0 = r8.l
            if (r0 == 0) goto L_0x0195
            com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper$HlsSampleQueue[] r0 = r8.j
            int r2 = r0.length
            r5 = 0
        L_0x018b:
            if (r5 >= r2) goto L_0x0195
            r10 = r0[r5]
            r10.k()
            int r5 = r5 + 1
            goto L_0x018b
        L_0x0195:
            com.google.android.exoplayer2.upstream.Loader r0 = r8.d
            r0.d()
            goto L_0x019e
        L_0x019b:
            r8.k()
        L_0x019e:
            r0 = r14
            goto L_0x020b
        L_0x01a1:
            java.util.ArrayList r0 = r8.f
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x01f6
            boolean r0 = com.google.android.exoplayer2.util.Util.a((java.lang.Object) r10, (java.lang.Object) r5)
            if (r0 != 0) goto L_0x01f6
            boolean r0 = r8.x
            if (r0 != 0) goto L_0x01ec
            r23 = 0
            int r0 = (r3 > r23 ? 1 : (r3 == r23 ? 0 : -1))
            if (r0 >= 0) goto L_0x01be
            r0 = r14
            long r13 = -r3
            r24 = r13
            goto L_0x01c1
        L_0x01be:
            r0 = r14
            r24 = r23
        L_0x01c1:
            com.google.android.exoplayer2.source.hls.HlsMediaChunk r2 = r8.m()
            com.google.android.exoplayer2.source.hls.HlsChunkSource r5 = r8.c
            com.google.android.exoplayer2.source.chunk.MediaChunkIterator[] r29 = r5.a((com.google.android.exoplayer2.source.hls.HlsMediaChunk) r2, (long) r3)
            r26 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            java.util.List r5 = r8.g
            r23 = r10
            r28 = r5
            r23.a(r24, r26, r28, r29)
            com.google.android.exoplayer2.source.hls.HlsChunkSource r5 = r8.c
            com.google.android.exoplayer2.source.TrackGroup r5 = r5.b
            com.google.android.exoplayer2.Format r2 = r2.g
            int r2 = r5.a((com.google.android.exoplayer2.Format) r2)
            int r5 = r10.h()
            if (r5 == r2) goto L_0x01ea
            goto L_0x01ed
        L_0x01ea:
            r2 = 0
            goto L_0x01ee
        L_0x01ec:
            r0 = r14
        L_0x01ed:
            r2 = 1
        L_0x01ee:
            if (r2 == 0) goto L_0x01f7
            r2 = 1
            r8.w = r2
            r2 = 1
            r9 = 1
            goto L_0x01f9
        L_0x01f6:
            r0 = r14
        L_0x01f7:
            r2 = r17
        L_0x01f9:
            if (r9 == 0) goto L_0x020b
            r8.a((long) r3, (boolean) r2)
            r2 = 0
        L_0x01ff:
            if (r2 >= r11) goto L_0x020b
            r5 = r12[r2]
            if (r5 == 0) goto L_0x0208
            r5 = 1
            r35[r2] = r5
        L_0x0208:
            int r2 = r2 + 1
            goto L_0x01ff
        L_0x020b:
            java.util.ArrayList r2 = r8.i
            r2.clear()
            r2 = 0
        L_0x0211:
            if (r2 >= r11) goto L_0x0221
            r5 = r12[r2]
            if (r5 == 0) goto L_0x021e
            java.util.ArrayList r10 = r8.i
            com.google.android.exoplayer2.source.hls.HlsSampleStream r5 = (com.google.android.exoplayer2.source.hls.HlsSampleStream) r5
            r10.add(r5)
        L_0x021e:
            int r2 = r2 + 1
            goto L_0x0211
        L_0x0221:
            r2 = 1
            r8.x = r2
            r2 = 0
            r5 = 0
        L_0x0226:
            int r10 = r1.length
            if (r2 >= r10) goto L_0x0252
            r10 = r12[r2]
            r13 = r6[r2]
            if (r13 != r7) goto L_0x0241
            com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r10)
            r22[r2] = r10
            r13 = r31
            java.util.IdentityHashMap r5 = r13.l
            java.lang.Integer r14 = java.lang.Integer.valueOf(r7)
            r5.put(r10, r14)
            r5 = 1
            goto L_0x024f
        L_0x0241:
            r13 = r31
            r14 = r20[r2]
            if (r14 != r7) goto L_0x024f
            if (r10 != 0) goto L_0x024b
            r10 = 1
            goto L_0x024c
        L_0x024b:
            r10 = 0
        L_0x024c:
            com.google.android.exoplayer2.util.Assertions.b((boolean) r10)
        L_0x024f:
            int r2 = r2 + 1
            goto L_0x0226
        L_0x0252:
            r13 = r31
            r2 = r16
            if (r5 == 0) goto L_0x0282
            r19[r2] = r8
            int r16 = r2 + 1
            if (r2 != 0) goto L_0x0278
            r2 = 1
            r8.a((boolean) r2)
            if (r9 != 0) goto L_0x026e
            com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper[] r5 = r13.t
            int r9 = r5.length
            if (r9 == 0) goto L_0x026e
            r9 = 0
            r5 = r5[r9]
            if (r8 == r5) goto L_0x0282
        L_0x026e:
            com.google.android.exoplayer2.source.hls.TimestampAdjusterProvider r5 = r13.m
            android.util.SparseArray r5 = r5.a
            r5.clear()
            r17 = 1
            goto L_0x0282
        L_0x0278:
            r2 = 1
            int r5 = r13.u
            if (r7 >= r5) goto L_0x027e
            goto L_0x027f
        L_0x027e:
            r2 = 0
        L_0x027f:
            r8.a((boolean) r2)
        L_0x0282:
            int r7 = r7 + 1
            r2 = r34
            r14 = r0
            r0 = r13
            r8 = r18
            r15 = r19
            r5 = r20
            r9 = r22
            r13 = r30
            goto L_0x0064
        L_0x0294:
            r13 = r0
            r5 = r2
            r7 = r8
            r8 = r9
            r19 = r15
            r2 = r16
            r9 = 0
            java.lang.System.arraycopy(r8, r9, r5, r9, r7)
            r0 = r19
            java.lang.Object[] r0 = com.google.android.exoplayer2.util.Util.a((java.lang.Object[]) r0, (int) r2)
            com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper[] r0 = (com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper[]) r0
            r13.t = r0
            com.google.android.exoplayer2.source.CompositeSequenceableLoaderFactory r1 = r13.n
            com.google.android.exoplayer2.source.SequenceableLoader r0 = r1.a(r0)
            r13.v = r0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.hls.HlsMediaPeriod.a(com.google.android.exoplayer2.trackselection.ExoTrackSelection[], boolean[], com.google.android.exoplayer2.source.SampleStream[], boolean[], long):long");
    }

    public final void a() {
        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr = this.c;
        int length = hlsSampleStreamWrapperArr.length;
        int i2 = 0;
        while (i2 < length) {
            HlsSampleStreamWrapper hlsSampleStreamWrapper = hlsSampleStreamWrapperArr[i2];
            hlsSampleStreamWrapper.h();
            if (!hlsSampleStreamWrapper.y || hlsSampleStreamWrapper.m) {
                i2++;
            } else {
                throw new ParserException("Loading finished before preparation is complete.");
            }
        }
    }

    public final void a(long j2, boolean z) {
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.t) {
            if (hlsSampleStreamWrapper.l && !hlsSampleStreamWrapper.n()) {
                int length = hlsSampleStreamWrapper.j.length;
                for (int i2 = 0; i2 < length; i2++) {
                    hlsSampleStreamWrapper.j[i2].a(j2, z, hlsSampleStreamWrapper.u[i2]);
                }
            }
        }
    }

    public final void a(Uri uri) {
        this.a.c(uri);
    }

    public final void a(MediaPeriod.Callback callback, long j2) {
        HashMap hashMap;
        this.b = callback;
        this.a.a((HlsPlaylistTracker.PlaylistEventListener) this);
        HlsMasterPlaylist hlsMasterPlaylist = (HlsMasterPlaylist) Assertions.b((Object) this.a.b());
        char c2 = 0;
        if (this.q) {
            List list = hlsMasterPlaylist.i;
            ArrayList arrayList = new ArrayList(list);
            HashMap hashMap2 = new HashMap();
            int i2 = 0;
            while (i2 < arrayList.size()) {
                DrmInitData drmInitData = (DrmInitData) list.get(i2);
                String str = drmInitData.b;
                i2++;
                int i3 = i2;
                while (i3 < arrayList.size()) {
                    DrmInitData drmInitData2 = (DrmInitData) arrayList.get(i3);
                    if (TextUtils.equals(drmInitData2.b, str)) {
                        Assertions.b(drmInitData.b == null || drmInitData2.b == null || TextUtils.equals(drmInitData.b, drmInitData2.b));
                        DrmInitData drmInitData3 = new DrmInitData(drmInitData.b != null ? drmInitData.b : drmInitData2.b, (DrmInitData.SchemeData[]) Util.a((Object[]) drmInitData.a, (Object[]) drmInitData2.a));
                        arrayList.remove(i3);
                        drmInitData = drmInitData3;
                    } else {
                        i3++;
                    }
                }
                hashMap2.put(str, drmInitData);
            }
            hashMap = hashMap2;
        } else {
            hashMap = Collections.emptyMap();
        }
        Map map = hashMap;
        boolean z = !hlsMasterPlaylist.c.isEmpty();
        List list2 = hlsMasterPlaylist.d;
        List list3 = hlsMasterPlaylist.e;
        this.r = 0;
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        if (z) {
            a(hlsMasterPlaylist, j2, (List) arrayList2, (List) arrayList3, map);
        }
        a(j2, list2, (List) arrayList2, (List) arrayList3, map);
        this.u = arrayList2.size();
        int i4 = 0;
        while (i4 < list3.size()) {
            HlsMasterPlaylist.Rendition rendition = (HlsMasterPlaylist.Rendition) list3.get(i4);
            Uri[] uriArr = new Uri[1];
            uriArr[c2] = rendition.a;
            Format[] formatArr = new Format[1];
            formatArr[c2] = rendition.b;
            int i5 = i4;
            HlsSampleStreamWrapper a2 = a(3, uriArr, formatArr, (Format) null, Collections.emptyList(), map, j2);
            arrayList3.add(new int[]{i5});
            arrayList2.add(a2);
            a2.a(new TrackGroup[]{new TrackGroup(rendition.b)}, new int[0]);
            i4 = i5 + 1;
            c2 = 0;
        }
        this.c = (HlsSampleStreamWrapper[]) arrayList2.toArray(new HlsSampleStreamWrapper[0]);
        arrayList3.toArray(new int[0][]);
        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr = this.c;
        this.r = hlsSampleStreamWrapperArr.length;
        hlsSampleStreamWrapperArr[0].a(true);
        for (HlsSampleStreamWrapper b2 : this.c) {
            b2.b();
        }
        this.t = this.c;
    }

    public final /* bridge */ /* synthetic */ void a(SequenceableLoader sequenceableLoader) {
        this.b.a(this);
    }

    public final boolean a(Uri uri, long j2) {
        boolean z;
        int c2;
        boolean z2 = true;
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.c) {
            HlsChunkSource hlsChunkSource = hlsSampleStreamWrapper.c;
            int i2 = 0;
            while (true) {
                if (i2 >= hlsChunkSource.a.length) {
                    i2 = -1;
                    break;
                } else if (hlsChunkSource.a[i2].equals(uri)) {
                    break;
                } else {
                    i2++;
                }
            }
            if (!(i2 == -1 || (c2 = hlsChunkSource.h.c(i2)) == -1)) {
                hlsChunkSource.i |= uri.equals(hlsChunkSource.g);
                if (j2 != -9223372036854775807L && !hlsChunkSource.h.a(c2, j2)) {
                    z = false;
                    z2 &= z;
                }
            }
            z = true;
            z2 &= z;
        }
        this.b.a(this);
        return z2;
    }

    public final void a_(long j2) {
        this.v.a_(j2);
    }

    public final long b(long j2) {
        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr = this.t;
        if (hlsSampleStreamWrapperArr.length > 0) {
            boolean a2 = hlsSampleStreamWrapperArr[0].a(j2, false);
            int i2 = 1;
            while (true) {
                HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr2 = this.t;
                if (i2 >= hlsSampleStreamWrapperArr2.length) {
                    break;
                }
                hlsSampleStreamWrapperArr2[i2].a(j2, a2);
                i2++;
            }
            if (a2) {
                this.m.a.clear();
            }
        }
        return j2;
    }

    public final TrackGroupArray b() {
        return (TrackGroupArray) Assertions.b((Object) this.s);
    }

    public final long c() {
        return -9223372036854775807L;
    }

    public final boolean c(long j2) {
        if (this.s != null) {
            return this.v.c(j2);
        }
        for (HlsSampleStreamWrapper b2 : this.c) {
            b2.b();
        }
        return false;
    }

    public final long d() {
        return this.v.d();
    }

    public final long e() {
        return this.v.e();
    }

    public final boolean f() {
        return this.v.f();
    }

    public final void g() {
        int i2 = this.r - 1;
        this.r = i2;
        if (i2 <= 0) {
            int i3 = 0;
            for (HlsSampleStreamWrapper c2 : this.c) {
                i3 += c2.c().length;
            }
            TrackGroup[] trackGroupArr = new TrackGroup[i3];
            int i4 = 0;
            for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.c) {
                int i5 = hlsSampleStreamWrapper.c().length;
                int i6 = 0;
                while (i6 < i5) {
                    trackGroupArr[i4] = hlsSampleStreamWrapper.c().b[i6];
                    i6++;
                    i4++;
                }
            }
            this.s = new TrackGroupArray(trackGroupArr);
            this.b.a(this);
        }
    }

    public final void h() {
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.c) {
            if (!hlsSampleStreamWrapper.f.isEmpty()) {
                HlsMediaChunk hlsMediaChunk = (HlsMediaChunk) C0000a.b((Iterable) hlsSampleStreamWrapper.f);
                int a2 = hlsSampleStreamWrapper.c.a(hlsMediaChunk);
                if (a2 == 1) {
                    hlsMediaChunk.o = true;
                } else if (a2 == 2 && !hlsSampleStreamWrapper.y && hlsSampleStreamWrapper.d.c()) {
                    hlsSampleStreamWrapper.d.d();
                }
            }
        }
        this.b.a(this);
    }
}
