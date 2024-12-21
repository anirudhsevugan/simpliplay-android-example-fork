package com.google.android.exoplayer2.source.dash;

import android.util.Pair;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.dreamers.exoplayercore.repack.cR;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.source.CompositeSequenceableLoaderFactory;
import com.google.android.exoplayer2.source.EmptySampleStream;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.SampleQueue;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.SequenceableLoader;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.chunk.BaseMediaChunk;
import com.google.android.exoplayer2.source.chunk.ChunkSampleStream;
import com.google.android.exoplayer2.source.dash.DashChunkSource;
import com.google.android.exoplayer2.source.dash.PlayerEmsgHandler;
import com.google.android.exoplayer2.source.dash.manifest.AdaptationSet;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.source.dash.manifest.Descriptor;
import com.google.android.exoplayer2.source.dash.manifest.EventStream;
import com.google.android.exoplayer2.source.dash.manifest.Period;
import com.google.android.exoplayer2.source.dash.manifest.Representation;
import com.google.android.exoplayer2.trackselection.ExoTrackSelection;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class DashMediaPeriod implements MediaPeriod, SequenceableLoader.Callback, ChunkSampleStream.ReleaseCallback {
    private static final Pattern e = Pattern.compile("CC([1-4])=(.+)");
    private static final Pattern f = Pattern.compile("([1-4])=lang:(\\w+)(,.+)?");
    final int a;
    final PlayerEmsgHandler b;
    MediaPeriod.Callback c;
    ChunkSampleStream[] d = new ChunkSampleStream[0];
    private final DashChunkSource.Factory g;
    private final TransferListener h;
    private final DrmSessionManager i;
    private final LoadErrorHandlingPolicy j;
    private final long k;
    private final LoaderErrorThrower l;
    private final Allocator m;
    private final TrackGroupArray n;
    private final TrackGroupInfo[] o;
    private final CompositeSequenceableLoaderFactory p;
    private final IdentityHashMap q = new IdentityHashMap();
    private final MediaSourceEventListener.EventDispatcher r;
    private final DrmSessionEventListener.EventDispatcher s;
    private EventSampleStream[] t = new EventSampleStream[0];
    private SequenceableLoader u;
    private DashManifest v;
    private int w;
    private List x;

    final class TrackGroupInfo {
        public final int[] a;
        public final int b;
        public final int c;
        public final int d;
        public final int e;
        public final int f;
        public final int g;

        private TrackGroupInfo(int i, int i2, int[] iArr, int i3, int i4, int i5, int i6) {
            this.b = i;
            this.a = iArr;
            this.c = i2;
            this.e = i3;
            this.f = i4;
            this.g = i5;
            this.d = i6;
        }

        public static TrackGroupInfo a(int i) {
            return new TrackGroupInfo(5, 2, new int[0], -1, -1, -1, i);
        }

        public static TrackGroupInfo a(int i, int[] iArr, int i2, int i3, int i4) {
            return new TrackGroupInfo(i, 0, iArr, i2, i3, i4, -1);
        }

        public static TrackGroupInfo a(int[] iArr, int i) {
            return new TrackGroupInfo(5, 1, iArr, i, -1, -1, -1);
        }

        public static TrackGroupInfo b(int[] iArr, int i) {
            return new TrackGroupInfo(3, 1, iArr, i, -1, -1, -1);
        }
    }

    public DashMediaPeriod(int i2, DashManifest dashManifest, int i3, DashChunkSource.Factory factory, TransferListener transferListener, DrmSessionManager drmSessionManager, DrmSessionEventListener.EventDispatcher eventDispatcher, LoadErrorHandlingPolicy loadErrorHandlingPolicy, MediaSourceEventListener.EventDispatcher eventDispatcher2, long j2, LoaderErrorThrower loaderErrorThrower, Allocator allocator, CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory, PlayerEmsgHandler.PlayerEmsgCallback playerEmsgCallback) {
        this.a = i2;
        this.v = dashManifest;
        this.w = i3;
        this.g = factory;
        this.h = transferListener;
        this.i = drmSessionManager;
        this.s = eventDispatcher;
        this.j = loadErrorHandlingPolicy;
        this.r = eventDispatcher2;
        this.k = j2;
        this.l = loaderErrorThrower;
        this.m = allocator;
        this.p = compositeSequenceableLoaderFactory;
        this.b = new PlayerEmsgHandler(dashManifest, playerEmsgCallback, allocator);
        this.u = compositeSequenceableLoaderFactory.a(this.d);
        Period a2 = dashManifest.a(i3);
        this.x = a2.d;
        List list = a2.c;
        List list2 = this.x;
        int[][] a3 = a(list);
        int length = a3.length;
        boolean[] zArr = new boolean[length];
        Format[][] formatArr = new Format[length][];
        int a4 = a(length, list, a3, zArr, formatArr) + length + list2.size();
        TrackGroup[] trackGroupArr = new TrackGroup[a4];
        TrackGroupInfo[] trackGroupInfoArr = new TrackGroupInfo[a4];
        a(list2, trackGroupArr, trackGroupInfoArr, a(drmSessionManager, list, a3, length, zArr, formatArr, trackGroupArr, trackGroupInfoArr));
        Pair create = Pair.create(new TrackGroupArray(trackGroupArr), trackGroupInfoArr);
        this.n = (TrackGroupArray) create.first;
        this.o = (TrackGroupInfo[]) create.second;
    }

    private static int a(int i2, List list, int[][] iArr, boolean[] zArr, Format[][] formatArr) {
        boolean z;
        Format[] formatArr2;
        Format a2;
        Pattern pattern;
        List list2 = list;
        int i3 = 0;
        int i4 = i2;
        for (int i5 = 0; i5 < i4; i5++) {
            int[] iArr2 = iArr[i5];
            int length = iArr2.length;
            int i6 = 0;
            while (true) {
                if (i6 >= length) {
                    z = false;
                    break;
                }
                List list3 = ((AdaptationSet) list2.get(iArr2[i6])).c;
                for (int i7 = 0; i7 < list3.size(); i7++) {
                    if (!((Representation) list3.get(i7)).d.isEmpty()) {
                        z = true;
                        break;
                    }
                }
                i6++;
            }
            if (z) {
                zArr[i5] = true;
                i3++;
            }
            for (int i8 : iArr[i5]) {
                AdaptationSet adaptationSet = (AdaptationSet) list2.get(i8);
                List list4 = ((AdaptationSet) list2.get(i8)).d;
                int i9 = 0;
                while (i9 < list4.size()) {
                    Descriptor descriptor = (Descriptor) list4.get(i9);
                    if ("urn:scte:dash:cc:cea-608:2015".equals(descriptor.a)) {
                        Format.Builder builder = new Format.Builder();
                        builder.k = "application/cea-608";
                        builder.a = new StringBuilder(18).append(adaptationSet.a).append(":cea608").toString();
                        a2 = builder.a();
                        pattern = e;
                    } else if ("urn:scte:dash:cc:cea-708:2015".equals(descriptor.a)) {
                        Format.Builder builder2 = new Format.Builder();
                        builder2.k = "application/cea-708";
                        builder2.a = new StringBuilder(18).append(adaptationSet.a).append(":cea708").toString();
                        a2 = builder2.a();
                        pattern = f;
                    } else {
                        i9++;
                    }
                    formatArr2 = a(descriptor, pattern, a2);
                }
            }
            formatArr2 = new Format[0];
            formatArr[i5] = formatArr2;
            if (formatArr2.length != 0) {
                i3++;
            }
        }
        return i3;
    }

    private int a(int i2, int[] iArr) {
        int i3 = iArr[i2];
        if (i3 == -1) {
            return -1;
        }
        int i4 = this.o[i3].e;
        for (int i5 = 0; i5 < iArr.length; i5++) {
            int i6 = iArr[i5];
            if (i6 == i4 && this.o[i6].c == 0) {
                return i5;
            }
        }
        return -1;
    }

    private static int a(DrmSessionManager drmSessionManager, List list, int[][] iArr, int i2, boolean[] zArr, Format[][] formatArr, TrackGroup[] trackGroupArr, TrackGroupInfo[] trackGroupInfoArr) {
        int i3;
        int i4;
        List list2 = list;
        int i5 = i2;
        int i6 = 0;
        int i7 = 0;
        while (i6 < i5) {
            int[] iArr2 = iArr[i6];
            ArrayList arrayList = new ArrayList();
            for (int i8 : iArr2) {
                arrayList.addAll(((AdaptationSet) list2.get(i8)).c);
            }
            int size = arrayList.size();
            Format[] formatArr2 = new Format[size];
            for (int i9 = 0; i9 < size; i9++) {
                Format format = ((Representation) arrayList.get(i9)).a;
                DrmSessionManager drmSessionManager2 = drmSessionManager;
                formatArr2[i9] = format.a(drmSessionManager.a(format));
            }
            DrmSessionManager drmSessionManager3 = drmSessionManager;
            AdaptationSet adaptationSet = (AdaptationSet) list2.get(iArr2[0]);
            int i10 = i7 + 1;
            if (zArr[i6]) {
                i3 = i10 + 1;
            } else {
                i3 = i10;
                i10 = -1;
            }
            if (formatArr[i6].length != 0) {
                i4 = i3 + 1;
            } else {
                i4 = i3;
                i3 = -1;
            }
            trackGroupArr[i7] = new TrackGroup(formatArr2);
            trackGroupInfoArr[i7] = TrackGroupInfo.a(adaptationSet.b, iArr2, i7, i10, i3);
            if (i10 != -1) {
                Format.Builder builder = new Format.Builder();
                builder.a = new StringBuilder(16).append(adaptationSet.a).append(":emsg").toString();
                builder.k = "application/x-emsg";
                trackGroupArr[i10] = new TrackGroup(builder.a());
                trackGroupInfoArr[i10] = TrackGroupInfo.a(iArr2, i7);
            }
            if (i3 != -1) {
                trackGroupArr[i3] = new TrackGroup(formatArr[i6]);
                trackGroupInfoArr[i3] = TrackGroupInfo.b(iArr2, i7);
            }
            i6++;
            i7 = i4;
        }
        return i7;
    }

    private ChunkSampleStream a(TrackGroupInfo trackGroupInfo, ExoTrackSelection exoTrackSelection, long j2) {
        int i2;
        TrackGroup trackGroup;
        TrackGroup trackGroup2;
        int i3;
        PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler;
        TrackGroupInfo trackGroupInfo2 = trackGroupInfo;
        boolean z = trackGroupInfo2.f != -1;
        if (z) {
            trackGroup = this.n.b[trackGroupInfo2.f];
            i2 = 1;
        } else {
            trackGroup = null;
            i2 = 0;
        }
        boolean z2 = trackGroupInfo2.g != -1;
        if (z2) {
            trackGroup2 = this.n.b[trackGroupInfo2.g];
            i2 += trackGroup2.a;
        } else {
            trackGroup2 = null;
        }
        Format[] formatArr = new Format[i2];
        int[] iArr = new int[i2];
        if (z) {
            formatArr[0] = trackGroup.b[0];
            iArr[0] = 5;
            i3 = 1;
        } else {
            i3 = 0;
        }
        ArrayList arrayList = new ArrayList();
        if (z2) {
            for (int i4 = 0; i4 < trackGroup2.a; i4++) {
                Format format = trackGroup2.b[i4];
                formatArr[i3] = format;
                iArr[i3] = 3;
                arrayList.add(format);
                i3++;
            }
        }
        if (!this.v.d || !z) {
            playerTrackEmsgHandler = null;
        } else {
            PlayerEmsgHandler playerEmsgHandler = this.b;
            playerTrackEmsgHandler = new PlayerEmsgHandler.PlayerTrackEmsgHandler(playerEmsgHandler.a);
        }
        PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler2 = playerTrackEmsgHandler;
        ChunkSampleStream chunkSampleStream = new ChunkSampleStream(trackGroupInfo2.b, iArr, formatArr, this.g.a(this.l, this.v, this.w, trackGroupInfo2.a, exoTrackSelection, trackGroupInfo2.b, this.k, z, arrayList, playerTrackEmsgHandler, this.h), this, this.m, j2, this.i, this.s, this.j, this.r);
        synchronized (this) {
            this.q.put(chunkSampleStream, playerTrackEmsgHandler2);
        }
        return chunkSampleStream;
    }

    private static Descriptor a(List list, String str) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            Descriptor descriptor = (Descriptor) list.get(i2);
            if (str.equals(descriptor.a)) {
                return descriptor;
            }
        }
        return null;
    }

    private static void a(List list, TrackGroup[] trackGroupArr, TrackGroupInfo[] trackGroupInfoArr, int i2) {
        int i3 = 0;
        while (i3 < list.size()) {
            Format.Builder builder = new Format.Builder();
            builder.a = ((EventStream) list.get(i3)).a();
            builder.k = "application/x-emsg";
            trackGroupArr[i2] = new TrackGroup(builder.a());
            trackGroupInfoArr[i2] = TrackGroupInfo.a(i3);
            i3++;
            i2++;
        }
    }

    private static Format[] a(Descriptor descriptor, Pattern pattern, Format format) {
        String str = descriptor.b;
        if (str == null) {
            return new Format[]{format};
        }
        String[] a2 = Util.a(str, ";");
        Format[] formatArr = new Format[a2.length];
        for (int i2 = 0; i2 < a2.length; i2++) {
            Matcher matcher = pattern.matcher(a2[i2]);
            if (!matcher.matches()) {
                return new Format[]{format};
            }
            int parseInt = Integer.parseInt(matcher.group(1));
            Format.Builder a3 = format.a();
            String str2 = format.a;
            a3.a = new StringBuilder(String.valueOf(str2).length() + 12).append(str2).append(":").append(parseInt).toString();
            a3.C = parseInt;
            a3.c = matcher.group(2);
            formatArr[i2] = a3.a();
        }
        return formatArr;
    }

    private static int[][] a(List list) {
        int i2;
        Descriptor a2;
        int size = list.size();
        SparseIntArray sparseIntArray = new SparseIntArray(size);
        ArrayList arrayList = new ArrayList(size);
        SparseArray sparseArray = new SparseArray(size);
        for (int i3 = 0; i3 < size; i3++) {
            sparseIntArray.put(((AdaptationSet) list.get(i3)).a, i3);
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(Integer.valueOf(i3));
            arrayList.add(arrayList2);
            sparseArray.put(i3, arrayList2);
        }
        for (int i4 = 0; i4 < size; i4++) {
            AdaptationSet adaptationSet = (AdaptationSet) list.get(i4);
            Descriptor b2 = b(adaptationSet.e);
            if (b2 == null) {
                b2 = b(adaptationSet.f);
            }
            if (b2 == null || (i2 = sparseIntArray.get(Integer.parseInt(b2.b), -1)) == -1) {
                i2 = i4;
            }
            if (i2 == i4 && (a2 = a(adaptationSet.f, "urn:mpeg:dash:adaptation-set-switching:2016")) != null) {
                for (String parseInt : Util.a(a2.b, ",")) {
                    int i5 = sparseIntArray.get(Integer.parseInt(parseInt), -1);
                    if (i5 != -1) {
                        i2 = Math.min(i2, i5);
                    }
                }
            }
            if (i2 != i4) {
                List list2 = (List) sparseArray.get(i4);
                List list3 = (List) sparseArray.get(i2);
                list3.addAll(list2);
                sparseArray.put(i4, list3);
                arrayList.remove(list2);
            }
        }
        int size2 = arrayList.size();
        int[][] iArr = new int[size2][];
        for (int i6 = 0; i6 < size2; i6++) {
            int[] a3 = cR.a((Collection) arrayList.get(i6));
            iArr[i6] = a3;
            Arrays.sort(a3);
        }
        return iArr;
    }

    private static Descriptor b(List list) {
        return a(list, "http://dashif.org/guidelines/trickmode");
    }

    public final long a(long j2, SeekParameters seekParameters) {
        for (ChunkSampleStream chunkSampleStream : this.d) {
            if (chunkSampleStream.a == 2) {
                return chunkSampleStream.d.a(j2, seekParameters);
            }
        }
        return j2;
    }

    public final long a(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j2) {
        SampleStream sampleStream;
        int[] iArr = new int[exoTrackSelectionArr.length];
        for (int i2 = 0; i2 < exoTrackSelectionArr.length; i2++) {
            ExoTrackSelection exoTrackSelection = exoTrackSelectionArr[i2];
            if (exoTrackSelection != null) {
                iArr[i2] = this.n.a(exoTrackSelection.e());
            } else {
                iArr[i2] = -1;
            }
        }
        for (int i3 = 0; i3 < exoTrackSelectionArr.length; i3++) {
            if (exoTrackSelectionArr[i3] == null || !zArr[i3]) {
                ChunkSampleStream chunkSampleStream = sampleStreamArr[i3];
                if (chunkSampleStream instanceof ChunkSampleStream) {
                    chunkSampleStream.a((ChunkSampleStream.ReleaseCallback) this);
                } else if (chunkSampleStream instanceof ChunkSampleStream.EmbeddedSampleStream) {
                    ((ChunkSampleStream.EmbeddedSampleStream) chunkSampleStream).c();
                }
                sampleStreamArr[i3] = null;
            }
        }
        int i4 = 0;
        while (true) {
            boolean z = true;
            if (i4 >= exoTrackSelectionArr.length) {
                break;
            }
            SampleStream sampleStream2 = sampleStreamArr[i4];
            if ((sampleStream2 instanceof EmptySampleStream) || (sampleStream2 instanceof ChunkSampleStream.EmbeddedSampleStream)) {
                int a2 = a(i4, iArr);
                if (a2 == -1) {
                    z = sampleStreamArr[i4] instanceof EmptySampleStream;
                } else {
                    ChunkSampleStream.EmbeddedSampleStream embeddedSampleStream = sampleStreamArr[i4];
                    if (!(embeddedSampleStream instanceof ChunkSampleStream.EmbeddedSampleStream) || embeddedSampleStream.a != sampleStreamArr[a2]) {
                        z = false;
                    }
                }
                if (!z) {
                    ChunkSampleStream.EmbeddedSampleStream embeddedSampleStream2 = sampleStreamArr[i4];
                    if (embeddedSampleStream2 instanceof ChunkSampleStream.EmbeddedSampleStream) {
                        embeddedSampleStream2.c();
                    }
                    sampleStreamArr[i4] = null;
                }
            }
            i4++;
        }
        for (int i5 = 0; i5 < exoTrackSelectionArr.length; i5++) {
            ExoTrackSelection exoTrackSelection2 = exoTrackSelectionArr[i5];
            if (exoTrackSelection2 != null) {
                ChunkSampleStream chunkSampleStream2 = sampleStreamArr[i5];
                if (chunkSampleStream2 == null) {
                    zArr2[i5] = true;
                    TrackGroupInfo trackGroupInfo = this.o[iArr[i5]];
                    if (trackGroupInfo.c == 0) {
                        sampleStreamArr[i5] = a(trackGroupInfo, exoTrackSelection2, j2);
                    } else if (trackGroupInfo.c == 2) {
                        sampleStreamArr[i5] = new EventSampleStream((EventStream) this.x.get(trackGroupInfo.d), exoTrackSelection2.e().b[0], this.v.d);
                    }
                } else if (chunkSampleStream2 instanceof ChunkSampleStream) {
                    ((DashChunkSource) chunkSampleStream2.d).a(exoTrackSelection2);
                }
            }
        }
        for (int i6 = 0; i6 < exoTrackSelectionArr.length; i6++) {
            if (sampleStreamArr[i6] == null && exoTrackSelectionArr[i6] != null) {
                TrackGroupInfo trackGroupInfo2 = this.o[iArr[i6]];
                if (trackGroupInfo2.c != 1) {
                    continue;
                } else {
                    int a3 = a(i6, iArr);
                    if (a3 == -1) {
                        sampleStream = new EmptySampleStream();
                    } else {
                        ChunkSampleStream chunkSampleStream3 = sampleStreamArr[a3];
                        int i7 = trackGroupInfo2.b;
                        int i8 = 0;
                        while (i8 < chunkSampleStream3.h.length) {
                            if (chunkSampleStream3.b[i8] == i7) {
                                Assertions.b(!chunkSampleStream3.c[i8]);
                                chunkSampleStream3.c[i8] = true;
                                chunkSampleStream3.h[i8].a(j2, true);
                                sampleStream = new ChunkSampleStream.EmbeddedSampleStream(chunkSampleStream3, chunkSampleStream3.h[i8], i8);
                            } else {
                                i8++;
                            }
                        }
                        throw new IllegalStateException();
                    }
                    sampleStreamArr[i6] = sampleStream;
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (ChunkSampleStream chunkSampleStream4 : sampleStreamArr) {
            if (chunkSampleStream4 instanceof ChunkSampleStream) {
                arrayList.add(chunkSampleStream4);
            } else if (chunkSampleStream4 instanceof EventSampleStream) {
                arrayList2.add((EventSampleStream) chunkSampleStream4);
            }
        }
        ChunkSampleStream[] chunkSampleStreamArr = new ChunkSampleStream[arrayList.size()];
        this.d = chunkSampleStreamArr;
        arrayList.toArray(chunkSampleStreamArr);
        EventSampleStream[] eventSampleStreamArr = new EventSampleStream[arrayList2.size()];
        this.t = eventSampleStreamArr;
        arrayList2.toArray(eventSampleStreamArr);
        this.u = this.p.a(this.d);
        return j2;
    }

    public final void a() {
        this.l.a();
    }

    public final void a(long j2, boolean z) {
        for (ChunkSampleStream chunkSampleStream : this.d) {
            if (!chunkSampleStream.h()) {
                int i2 = chunkSampleStream.g.c;
                chunkSampleStream.g.a(j2, z, true);
                int i3 = chunkSampleStream.g.c;
                if (i3 > i2) {
                    long j3 = chunkSampleStream.g.j();
                    for (int i4 = 0; i4 < chunkSampleStream.h.length; i4++) {
                        chunkSampleStream.h[i4].a(j3, z, chunkSampleStream.c[i4]);
                    }
                }
                int min = Math.min(chunkSampleStream.a(i3, 0), chunkSampleStream.k);
                if (min > 0) {
                    Util.a((List) chunkSampleStream.f, 0, min);
                    chunkSampleStream.k -= min;
                }
            }
        }
    }

    public final void a(MediaPeriod.Callback callback, long j2) {
        this.c = callback;
        callback.a(this);
    }

    public final /* bridge */ /* synthetic */ void a(SequenceableLoader sequenceableLoader) {
        this.c.a(this);
    }

    public final synchronized void a(ChunkSampleStream chunkSampleStream) {
        PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler = (PlayerEmsgHandler.PlayerTrackEmsgHandler) this.q.remove(chunkSampleStream);
        if (playerTrackEmsgHandler != null) {
            playerTrackEmsgHandler.a.a();
        }
    }

    public final void a(DashManifest dashManifest, int i2) {
        this.v = dashManifest;
        this.w = i2;
        PlayerEmsgHandler playerEmsgHandler = this.b;
        playerEmsgHandler.h = false;
        playerEmsgHandler.f = -9223372036854775807L;
        playerEmsgHandler.e = dashManifest;
        Iterator it = playerEmsgHandler.d.entrySet().iterator();
        while (it.hasNext()) {
            if (((Long) ((Map.Entry) it.next()).getKey()).longValue() < playerEmsgHandler.e.h) {
                it.remove();
            }
        }
        ChunkSampleStream[] chunkSampleStreamArr = this.d;
        if (chunkSampleStreamArr != null) {
            for (ChunkSampleStream chunkSampleStream : chunkSampleStreamArr) {
                ((DashChunkSource) chunkSampleStream.d).a(dashManifest, i2);
            }
            this.c.a(this);
        }
        this.x = dashManifest.a(i2).d;
        for (EventSampleStream eventSampleStream : this.t) {
            Iterator it2 = this.x.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                EventStream eventStream = (EventStream) it2.next();
                if (eventStream.a().equals(eventSampleStream.a.a())) {
                    boolean z = true;
                    int a2 = dashManifest.a() - 1;
                    if (!dashManifest.d || i2 != a2) {
                        z = false;
                    }
                    eventSampleStream.a(eventStream, z);
                }
            }
        }
    }

    public final void a_(long j2) {
        this.u.a_(j2);
    }

    public final long b(long j2) {
        BaseMediaChunk baseMediaChunk;
        boolean z;
        long j3 = j2;
        for (ChunkSampleStream chunkSampleStream : this.d) {
            chunkSampleStream.j = j3;
            if (chunkSampleStream.h()) {
                chunkSampleStream.i = j3;
            } else {
                int i2 = 0;
                while (true) {
                    if (i2 >= chunkSampleStream.f.size()) {
                        break;
                    }
                    baseMediaChunk = (BaseMediaChunk) chunkSampleStream.f.get(i2);
                    long j4 = baseMediaChunk.j;
                    if (j4 != j3 || baseMediaChunk.a != -9223372036854775807L) {
                        if (j4 > j3) {
                            break;
                        }
                        i2++;
                    } else {
                        break;
                    }
                }
                baseMediaChunk = null;
                if (baseMediaChunk != null) {
                    z = chunkSampleStream.g.c(baseMediaChunk.a(0));
                } else {
                    z = chunkSampleStream.g.a(j3, j3 < chunkSampleStream.e());
                }
                if (z) {
                    chunkSampleStream.k = chunkSampleStream.a(chunkSampleStream.g.e(), 0);
                    for (SampleQueue a2 : chunkSampleStream.h) {
                        a2.a(j3, true);
                    }
                } else {
                    chunkSampleStream.i = j3;
                    chunkSampleStream.l = false;
                    chunkSampleStream.f.clear();
                    chunkSampleStream.k = 0;
                    if (chunkSampleStream.e.c()) {
                        chunkSampleStream.g.k();
                        for (SampleQueue k2 : chunkSampleStream.h) {
                            k2.k();
                        }
                        chunkSampleStream.e.d();
                    } else {
                        chunkSampleStream.e.d = null;
                        chunkSampleStream.c();
                    }
                }
            }
        }
        for (EventSampleStream b2 : this.t) {
            b2.b(j3);
        }
        return j3;
    }

    public final TrackGroupArray b() {
        return this.n;
    }

    public final long c() {
        return -9223372036854775807L;
    }

    public final boolean c(long j2) {
        return this.u.c(j2);
    }

    public final long d() {
        return this.u.d();
    }

    public final long e() {
        return this.u.e();
    }

    public final boolean f() {
        return this.u.f();
    }
}
