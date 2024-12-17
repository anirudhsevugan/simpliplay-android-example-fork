package com.google.android.exoplayer2.extractor.mp4;

import android.util.Pair;
import androidx.core.location.LocationRequestCompat;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.GaplessInfoHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.mp4.Atom;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public final class Mp4Extractor implements Extractor, SeekMap {
    public ExtractorOutput a;
    private final ParsableByteArray b;
    private final ParsableByteArray c;
    private final ParsableByteArray d;
    private final ParsableByteArray e;
    private final ArrayDeque f;
    private final SefReader g;
    private final List h;
    private int i;
    private int j;
    private long k;
    private int l;
    private ParsableByteArray m;
    private int n;
    private int o;
    private int p;
    private int q;
    private Mp4Track[] r;
    private long[][] s;
    private int t;
    private long u;
    private int v;

    final class Mp4Track {
        public final Track a;
        public final TrackSampleTable b;
        public final TrackOutput c;
        public int d;

        public Mp4Track(Track track, TrackSampleTable trackSampleTable, TrackOutput trackOutput) {
            this.a = track;
            this.b = trackSampleTable;
            this.c = trackOutput;
        }
    }

    static {
        ExtractorsFactory extractorsFactory = Mp4Extractor$$Lambda$1.b;
    }

    public Mp4Extractor() {
        this((byte) 0);
    }

    public Mp4Extractor(byte b2) {
        this.i = 0;
        this.g = new SefReader();
        this.h = new ArrayList();
        this.e = new ParsableByteArray(16);
        this.f = new ArrayDeque();
        this.b = new ParsableByteArray(NalUnitUtil.a);
        this.c = new ParsableByteArray(4);
        this.d = new ParsableByteArray();
        this.n = -1;
    }

    private static int a(int i2) {
        switch (i2) {
            case 1751476579:
                return 2;
            case 1903435808:
                return 1;
            default:
                return 0;
        }
    }

    private static int a(TrackSampleTable trackSampleTable, long j2) {
        int a2 = trackSampleTable.a(j2);
        return a2 == -1 ? trackSampleTable.b(j2) : a2;
    }

    private static long a(TrackSampleTable trackSampleTable, long j2, long j3) {
        int a2 = a(trackSampleTable, j2);
        return a2 == -1 ? j3 : Math.min(trackSampleTable.c[a2], j3);
    }

    static final /* synthetic */ Track a(Track track) {
        return track;
    }

    private void a(Atom.ContainerAtom containerAtom) {
        Metadata metadata;
        Metadata metadata2;
        int i2;
        List list;
        ArrayList arrayList;
        int i3;
        Atom.ContainerAtom containerAtom2 = containerAtom;
        ArrayList arrayList2 = new ArrayList();
        boolean z = this.v == 1;
        GaplessInfoHolder gaplessInfoHolder = new GaplessInfoHolder();
        Atom.LeafAtom d2 = containerAtom2.d(1969517665);
        if (d2 != null) {
            Pair a2 = AtomParsers.a(d2);
            Metadata metadata3 = (Metadata) a2.first;
            Metadata metadata4 = (Metadata) a2.second;
            if (metadata3 != null) {
                gaplessInfoHolder.a(metadata3);
            }
            metadata2 = metadata4;
            metadata = metadata3;
        } else {
            metadata2 = null;
            metadata = null;
        }
        Atom.ContainerAtom e2 = containerAtom2.e(1835365473);
        Metadata a3 = e2 != null ? AtomParsers.a(e2) : null;
        List a4 = AtomParsers.a(containerAtom, gaplessInfoHolder, -9223372036854775807L, (DrmInitData) null, false, z, Mp4Extractor$$Lambda$0.a);
        ExtractorOutput extractorOutput = (ExtractorOutput) Assertions.b((Object) this.a);
        int size = a4.size();
        int i4 = 0;
        int i5 = -1;
        long j2 = -9223372036854775807L;
        while (i4 < size) {
            TrackSampleTable trackSampleTable = (TrackSampleTable) a4.get(i4);
            if (trackSampleTable.b != 0) {
                Track track = trackSampleTable.a;
                int i6 = i5;
                arrayList = arrayList2;
                long j3 = track.e != -9223372036854775807L ? track.e : trackSampleTable.h;
                long max = Math.max(j2, j3);
                list = a4;
                i2 = size;
                Mp4Track mp4Track = new Mp4Track(track, trackSampleTable, extractorOutput.a(i4, track.b));
                long j4 = max;
                Format.Builder a5 = track.f.a();
                a5.l = trackSampleTable.e + 30;
                if (track.b == 2 && j3 > 0 && trackSampleTable.b > 1) {
                    a5.r = ((float) trackSampleTable.b) / (((float) j3) / 1000000.0f);
                }
                MetadataUtil.a(track.b, gaplessInfoHolder, a5);
                int i7 = track.b;
                Metadata[] metadataArr = new Metadata[2];
                metadataArr[0] = metadata2;
                metadataArr[1] = this.h.isEmpty() ? null : new Metadata(this.h);
                MetadataUtil.a(i7, metadata, a3, a5, metadataArr);
                mp4Track.c.a(a5.a());
                if (track.b == 2) {
                    i3 = i6;
                    if (i3 == -1) {
                        i3 = arrayList.size();
                    }
                } else {
                    i3 = i6;
                }
                i5 = i3;
                arrayList.add(mp4Track);
                j2 = j4;
            } else {
                list = a4;
                i2 = size;
                int i8 = i5;
                arrayList = arrayList2;
            }
            i4++;
            arrayList2 = arrayList;
            a4 = list;
            size = i2;
        }
        this.t = i5;
        this.u = j2;
        Mp4Track[] mp4TrackArr = (Mp4Track[]) arrayList2.toArray(new Mp4Track[0]);
        this.r = mp4TrackArr;
        this.s = a(mp4TrackArr);
        extractorOutput.c_();
        extractorOutput.a(this);
    }

    private static long[][] a(Mp4Track[] mp4TrackArr) {
        long[][] jArr = new long[mp4TrackArr.length][];
        int[] iArr = new int[mp4TrackArr.length];
        long[] jArr2 = new long[mp4TrackArr.length];
        boolean[] zArr = new boolean[mp4TrackArr.length];
        for (int i2 = 0; i2 < mp4TrackArr.length; i2++) {
            jArr[i2] = new long[mp4TrackArr[i2].b.b];
            jArr2[i2] = mp4TrackArr[i2].b.f[0];
        }
        long j2 = 0;
        int i3 = 0;
        while (i3 < mp4TrackArr.length) {
            long j3 = LocationRequestCompat.PASSIVE_INTERVAL;
            int i4 = -1;
            for (int i5 = 0; i5 < mp4TrackArr.length; i5++) {
                if (!zArr[i5]) {
                    long j4 = jArr2[i5];
                    if (j4 <= j3) {
                        i4 = i5;
                        j3 = j4;
                    }
                }
            }
            int i6 = iArr[i4];
            jArr[i4][i6] = j2;
            j2 += (long) mp4TrackArr[i4].b.d[i6];
            int i7 = i6 + 1;
            iArr[i4] = i7;
            if (i7 < jArr[i4].length) {
                jArr2[i4] = mp4TrackArr[i4].b.f[i7];
            } else {
                zArr[i4] = true;
                i3++;
            }
        }
        return jArr;
    }

    private void b(long j2) {
        while (!this.f.isEmpty() && ((Atom.ContainerAtom) this.f.peek()).b == j2) {
            Atom.ContainerAtom containerAtom = (Atom.ContainerAtom) this.f.pop();
            if (containerAtom.a == 1836019574) {
                a(containerAtom);
                this.f.clear();
                this.i = 2;
            } else if (!this.f.isEmpty()) {
                ((Atom.ContainerAtom) this.f.peek()).a(containerAtom);
            }
        }
        if (this.i != 2) {
            d();
        }
    }

    private void c(long j2) {
        for (Mp4Track mp4Track : this.r) {
            TrackSampleTable trackSampleTable = mp4Track.b;
            int a2 = trackSampleTable.a(j2);
            if (a2 == -1) {
                a2 = trackSampleTable.b(j2);
            }
            mp4Track.d = a2;
        }
    }

    static final /* synthetic */ Extractor[] c() {
        return new Extractor[]{new Mp4Extractor()};
    }

    private void d() {
        this.i = 0;
        this.l = 0;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0086, code lost:
        r2.a = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0110, code lost:
        r1 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x012f, code lost:
        if (r2.a != 0) goto L_0x0134;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0131, code lost:
        d();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0134, code lost:
        return r1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x0359  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x035b  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x03d8  */
    /* JADX WARNING: Removed duplicated region for block: B:248:0x050b A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:252:0x035e A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:253:0x050c A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:254:0x0006 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x0006 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int a(com.google.android.exoplayer2.extractor.ExtractorInput r34, com.google.android.exoplayer2.extractor.PositionHolder r35) {
        /*
            r33 = this;
            r0 = r33
            r1 = r34
            r2 = r35
        L_0x0006:
            int r3 = r0.i
            r5 = 8
            r7 = -1
            r11 = 2
            r12 = 4
            r13 = -1
            r4 = 8
            r10 = 0
            switch(r3) {
                case 0: goto L_0x0360;
                case 1: goto L_0x02da;
                case 2: goto L_0x0135;
                case 3: goto L_0x001b;
                default: goto L_0x0015;
            }
        L_0x0015:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            r1.<init>()
            throw r1
        L_0x001b:
            com.google.android.exoplayer2.extractor.mp4.SefReader r3 = r0.g
            java.util.List r13 = r0.h
            int r9 = r3.b
            switch(r9) {
                case 0: goto L_0x0112;
                case 1: goto L_0x00e3;
                case 2: goto L_0x008a;
                case 3: goto L_0x002a;
                default: goto L_0x0024;
            }
        L_0x0024:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            r1.<init>()
            throw r1
        L_0x002a:
            long r5 = r34.c()
            long r7 = r34.d()
            long r16 = r34.c()
            long r7 = r7 - r16
            int r9 = r3.c
            long r14 = (long) r9
            long r7 = r7 - r14
            int r8 = (int) r7
            com.google.android.exoplayer2.util.ParsableByteArray r7 = new com.google.android.exoplayer2.util.ParsableByteArray
            r7.<init>((int) r8)
            byte[] r9 = r7.a
            r1.b(r9, r10, r8)
        L_0x0047:
            java.util.List r1 = r3.a
            int r1 = r1.size()
            if (r10 >= r1) goto L_0x0084
            java.util.List r1 = r3.a
            java.lang.Object r1 = r1.get(r10)
            com.google.android.exoplayer2.extractor.mp4.SefReader$DataReference r1 = (com.google.android.exoplayer2.extractor.mp4.SefReader.DataReference) r1
            long r8 = r1.a
            long r8 = r8 - r5
            int r9 = (int) r8
            r7.d(r9)
            r7.e(r12)
            int r8 = r7.k()
            java.lang.String r9 = r7.f(r8)
            int r9 = com.google.android.exoplayer2.extractor.mp4.SefReader.a(r9)
            int r1 = r1.b
            int r8 = r8 + r4
            int r1 = r1 - r8
            switch(r9) {
                case 2192: goto L_0x007a;
                case 2816: goto L_0x0081;
                case 2817: goto L_0x0081;
                case 2819: goto L_0x0081;
                case 2820: goto L_0x0081;
                default: goto L_0x0074;
            }
        L_0x0074:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            r1.<init>()
            throw r1
        L_0x007a:
            com.google.android.exoplayer2.metadata.mp4.SlowMotionData r1 = com.google.android.exoplayer2.extractor.mp4.SefReader.a(r7, r1)
            r13.add(r1)
        L_0x0081:
            int r10 = r10 + 1
            goto L_0x0047
        L_0x0084:
            r3 = 0
        L_0x0086:
            r2.a = r3
            goto L_0x0110
        L_0x008a:
            long r5 = r34.d()
            int r7 = r3.c
            int r7 = r7 + -12
            int r7 = r7 - r4
            com.google.android.exoplayer2.util.ParsableByteArray r8 = new com.google.android.exoplayer2.util.ParsableByteArray
            r8.<init>((int) r7)
            byte[] r9 = r8.a
            r1.b(r9, r10, r7)
            r1 = 0
        L_0x009e:
            int r9 = r7 / 12
            if (r1 >= r9) goto L_0x00cc
            r8.e(r11)
            short r9 = r8.f()
            switch(r9) {
                case 2192: goto L_0x00b0;
                case 2816: goto L_0x00b0;
                case 2817: goto L_0x00b0;
                case 2819: goto L_0x00b0;
                case 2820: goto L_0x00b0;
                default: goto L_0x00ac;
            }
        L_0x00ac:
            r8.e(r4)
            goto L_0x00c9
        L_0x00b0:
            int r9 = r3.c
            long r12 = (long) r9
            long r12 = r5 - r12
            int r9 = r8.k()
            long r14 = (long) r9
            long r12 = r12 - r14
            int r9 = r8.k()
            java.util.List r14 = r3.a
            com.google.android.exoplayer2.extractor.mp4.SefReader$DataReference r15 = new com.google.android.exoplayer2.extractor.mp4.SefReader$DataReference
            r15.<init>(r12, r9)
            r14.add(r15)
        L_0x00c9:
            int r1 = r1 + 1
            goto L_0x009e
        L_0x00cc:
            java.util.List r1 = r3.a
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x00d5
            goto L_0x00fd
        L_0x00d5:
            r1 = 3
            r3.b = r1
            java.util.List r1 = r3.a
            java.lang.Object r1 = r1.get(r10)
            com.google.android.exoplayer2.extractor.mp4.SefReader$DataReference r1 = (com.google.android.exoplayer2.extractor.mp4.SefReader.DataReference) r1
            long r3 = r1.a
            goto L_0x0086
        L_0x00e3:
            com.google.android.exoplayer2.util.ParsableByteArray r5 = new com.google.android.exoplayer2.util.ParsableByteArray
            r5.<init>((int) r4)
            byte[] r6 = r5.a
            r1.b(r6, r10, r4)
            int r6 = r5.k()
            int r6 = r6 + r4
            r3.c = r6
            int r4 = r5.j()
            r5 = 1397048916(0x53454654, float:8.4728847E11)
            if (r4 == r5) goto L_0x0102
        L_0x00fd:
            r4 = 0
            r2.a = r4
            goto L_0x0110
        L_0x0102:
            long r4 = r34.c()
            int r1 = r3.c
            int r1 = r1 + -12
            long r6 = (long) r1
            long r4 = r4 - r6
            r2.a = r4
            r3.b = r11
        L_0x0110:
            r1 = 1
            goto L_0x0129
        L_0x0112:
            long r9 = r34.d()
            int r1 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r1 == 0) goto L_0x0122
            int r1 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r1 >= 0) goto L_0x011f
            goto L_0x0122
        L_0x011f:
            long r5 = r9 - r5
            goto L_0x0124
        L_0x0122:
            r5 = 0
        L_0x0124:
            r2.a = r5
            r1 = 1
            r3.b = r1
        L_0x0129:
            long r2 = r2.a
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 != 0) goto L_0x0134
            r33.d()
        L_0x0134:
            return r1
        L_0x0135:
            long r3 = r34.c()
            int r7 = r0.n
            if (r7 != r13) goto L_0x01c4
            r7 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r21 = r7
            r23 = r21
            r26 = r23
            r9 = 1
            r14 = 1
            r15 = 0
            r16 = -1
            r25 = -1
        L_0x014f:
            com.google.android.exoplayer2.extractor.mp4.Mp4Extractor$Mp4Track[] r12 = r0.r
            java.lang.Object r12 = com.google.android.exoplayer2.util.Util.a((java.lang.Object) r12)
            com.google.android.exoplayer2.extractor.mp4.Mp4Extractor$Mp4Track[] r12 = (com.google.android.exoplayer2.extractor.mp4.Mp4Extractor.Mp4Track[]) r12
            int r12 = r12.length
            if (r15 >= r12) goto L_0x01aa
            com.google.android.exoplayer2.extractor.mp4.Mp4Extractor$Mp4Track[] r12 = r0.r
            r12 = r12[r15]
            int r11 = r12.d
            com.google.android.exoplayer2.extractor.mp4.TrackSampleTable r10 = r12.b
            int r10 = r10.b
            if (r11 == r10) goto L_0x01a5
            com.google.android.exoplayer2.extractor.mp4.TrackSampleTable r10 = r12.b
            long[] r10 = r10.c
            r30 = r10[r11]
            long[][] r10 = r0.s
            java.lang.Object r10 = com.google.android.exoplayer2.util.Util.a((java.lang.Object) r10)
            long[][] r10 = (long[][]) r10
            r10 = r10[r15]
            r11 = r10[r11]
            long r30 = r30 - r3
            r19 = 0
            int r10 = (r30 > r19 ? 1 : (r30 == r19 ? 0 : -1))
            if (r10 < 0) goto L_0x018a
            r17 = 262144(0x40000, double:1.295163E-318)
            int r10 = (r30 > r17 ? 1 : (r30 == r17 ? 0 : -1))
            if (r10 < 0) goto L_0x0188
            goto L_0x018a
        L_0x0188:
            r10 = 0
            goto L_0x018b
        L_0x018a:
            r10 = 1
        L_0x018b:
            if (r10 != 0) goto L_0x018f
            if (r14 != 0) goto L_0x0195
        L_0x018f:
            if (r10 != r14) goto L_0x019c
            int r32 = (r30 > r26 ? 1 : (r30 == r26 ? 0 : -1))
            if (r32 >= 0) goto L_0x019c
        L_0x0195:
            r14 = r10
            r23 = r11
            r25 = r15
            r26 = r30
        L_0x019c:
            int r30 = (r11 > r21 ? 1 : (r11 == r21 ? 0 : -1))
            if (r30 >= 0) goto L_0x01a5
            r9 = r10
            r21 = r11
            r16 = r15
        L_0x01a5:
            int r15 = r15 + 1
            r10 = 0
            r11 = 2
            goto L_0x014f
        L_0x01aa:
            int r10 = (r21 > r7 ? 1 : (r21 == r7 ? 0 : -1))
            if (r10 == 0) goto L_0x01bd
            if (r9 == 0) goto L_0x01bd
            r7 = 10485760(0xa00000, double:5.180654E-317)
            long r21 = r21 + r7
            int r7 = (r23 > r21 ? 1 : (r23 == r21 ? 0 : -1))
            if (r7 >= 0) goto L_0x01ba
            goto L_0x01bd
        L_0x01ba:
            r7 = r16
            goto L_0x01bf
        L_0x01bd:
            r7 = r25
        L_0x01bf:
            r0.n = r7
            if (r7 != r13) goto L_0x01c4
            return r13
        L_0x01c4:
            com.google.android.exoplayer2.extractor.mp4.Mp4Extractor$Mp4Track[] r7 = r0.r
            java.lang.Object r7 = com.google.android.exoplayer2.util.Util.a((java.lang.Object) r7)
            com.google.android.exoplayer2.extractor.mp4.Mp4Extractor$Mp4Track[] r7 = (com.google.android.exoplayer2.extractor.mp4.Mp4Extractor.Mp4Track[]) r7
            int r8 = r0.n
            r7 = r7[r8]
            com.google.android.exoplayer2.extractor.TrackOutput r8 = r7.c
            int r9 = r7.d
            com.google.android.exoplayer2.extractor.mp4.TrackSampleTable r10 = r7.b
            long[] r10 = r10.c
            r11 = r10[r9]
            com.google.android.exoplayer2.extractor.mp4.TrackSampleTable r10 = r7.b
            int[] r10 = r10.d
            r10 = r10[r9]
            long r3 = r11 - r3
            int r14 = r0.o
            long r14 = (long) r14
            long r3 = r3 + r14
            r14 = 0
            int r16 = (r3 > r14 ? 1 : (r3 == r14 ? 0 : -1))
            if (r16 < 0) goto L_0x02d6
            r14 = 262144(0x40000, double:1.295163E-318)
            int r16 = (r3 > r14 ? 1 : (r3 == r14 ? 0 : -1))
            if (r16 < 0) goto L_0x01f5
            goto L_0x02d6
        L_0x01f5:
            com.google.android.exoplayer2.extractor.mp4.Track r2 = r7.a
            int r2 = r2.g
            r11 = 1
            if (r2 != r11) goto L_0x01ff
            long r3 = r3 + r5
            int r10 = r10 + -8
        L_0x01ff:
            int r2 = (int) r3
            r1.b(r2)
            com.google.android.exoplayer2.extractor.mp4.Track r2 = r7.a
            int r2 = r2.j
            if (r2 == 0) goto L_0x0270
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.c
            byte[] r2 = r2.a
            r3 = 0
            r2[r3] = r3
            r4 = 1
            r2[r4] = r3
            r4 = 2
            r2[r4] = r3
            com.google.android.exoplayer2.extractor.mp4.Track r3 = r7.a
            int r3 = r3.j
            com.google.android.exoplayer2.extractor.mp4.Track r4 = r7.a
            int r4 = r4.j
            r5 = 4
            int r12 = 4 - r4
        L_0x0221:
            int r4 = r0.p
            if (r4 >= r10) goto L_0x02af
            int r4 = r0.q
            if (r4 != 0) goto L_0x025b
            r1.b(r2, r12, r3)
            int r4 = r0.o
            int r4 = r4 + r3
            r0.o = r4
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r0.c
            r5 = 0
            r4.d(r5)
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r0.c
            int r4 = r4.j()
            if (r4 < 0) goto L_0x0253
            r0.q = r4
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r0.b
            r4.d(r5)
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r0.b
            r5 = 4
            r8.b(r4, r5)
            int r4 = r0.p
            int r4 = r4 + r5
            r0.p = r4
            int r10 = r10 + r12
            goto L_0x0221
        L_0x0253:
            com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
            java.lang.String r2 = "Invalid NAL length"
            r1.<init>((java.lang.String) r2)
            throw r1
        L_0x025b:
            r5 = 0
            int r4 = r8.b(r1, r4, r5)
            int r5 = r0.o
            int r5 = r5 + r4
            r0.o = r5
            int r5 = r0.p
            int r5 = r5 + r4
            r0.p = r5
            int r5 = r0.q
            int r5 = r5 - r4
            r0.q = r5
            goto L_0x0221
        L_0x0270:
            com.google.android.exoplayer2.extractor.mp4.Track r2 = r7.a
            com.google.android.exoplayer2.Format r2 = r2.f
            java.lang.String r2 = r2.l
            java.lang.String r3 = "audio/ac4"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0294
            int r2 = r0.p
            if (r2 != 0) goto L_0x0292
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.d
            com.google.android.exoplayer2.audio.Ac4Util.a((int) r10, (com.google.android.exoplayer2.util.ParsableByteArray) r2)
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.d
            r3 = 7
            r8.b(r2, r3)
            int r2 = r0.p
            int r2 = r2 + r3
            r0.p = r2
        L_0x0292:
            int r10 = r10 + 7
        L_0x0294:
            int r2 = r0.p
            if (r2 >= r10) goto L_0x02af
            int r2 = r10 - r2
            r3 = 0
            int r2 = r8.b(r1, r2, r3)
            int r3 = r0.o
            int r3 = r3 + r2
            r0.o = r3
            int r3 = r0.p
            int r3 = r3 + r2
            r0.p = r3
            int r3 = r0.q
            int r3 = r3 - r2
            r0.q = r3
            goto L_0x0294
        L_0x02af:
            r25 = r10
            com.google.android.exoplayer2.extractor.mp4.TrackSampleTable r1 = r7.b
            long[] r1 = r1.f
            r22 = r1[r9]
            com.google.android.exoplayer2.extractor.mp4.TrackSampleTable r1 = r7.b
            int[] r1 = r1.g
            r24 = r1[r9]
            r26 = 0
            r27 = 0
            r21 = r8
            r21.a(r22, r24, r25, r26, r27)
            int r1 = r7.d
            r3 = 1
            int r1 = r1 + r3
            r7.d = r1
            r0.n = r13
            r1 = 0
            r0.o = r1
            r0.p = r1
            r0.q = r1
            return r1
        L_0x02d6:
            r3 = 1
            r2.a = r11
            return r3
        L_0x02da:
            long r5 = r0.k
            int r3 = r0.l
            long r7 = (long) r3
            long r5 = r5 - r7
            long r7 = r34.c()
            long r7 = r7 + r5
            com.google.android.exoplayer2.util.ParsableByteArray r3 = r0.m
            if (r3 == 0) goto L_0x033a
            byte[] r9 = r3.a
            int r10 = r0.l
            int r6 = (int) r5
            r1.b(r9, r10, r6)
            int r5 = r0.j
            r6 = 1718909296(0x66747970, float:2.8862439E23)
            if (r5 != r6) goto L_0x031f
            r3.d(r4)
            int r4 = r3.j()
            int r4 = a((int) r4)
            if (r4 == 0) goto L_0x0306
            goto L_0x031c
        L_0x0306:
            r4 = 4
            r3.e(r4)
        L_0x030a:
            int r4 = r3.a()
            if (r4 <= 0) goto L_0x031b
            int r4 = r3.j()
            int r4 = a((int) r4)
            if (r4 == 0) goto L_0x030a
            goto L_0x031c
        L_0x031b:
            r4 = 0
        L_0x031c:
            r0.v = r4
            goto L_0x0345
        L_0x031f:
            java.util.ArrayDeque r4 = r0.f
            boolean r4 = r4.isEmpty()
            if (r4 != 0) goto L_0x0345
            java.util.ArrayDeque r4 = r0.f
            java.lang.Object r4 = r4.peek()
            com.google.android.exoplayer2.extractor.mp4.Atom$ContainerAtom r4 = (com.google.android.exoplayer2.extractor.mp4.Atom.ContainerAtom) r4
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r5 = new com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom
            int r6 = r0.j
            r5.<init>(r6, r3)
            r4.a((com.google.android.exoplayer2.extractor.mp4.Atom.LeafAtom) r5)
            goto L_0x0345
        L_0x033a:
            r3 = 262144(0x40000, double:1.295163E-318)
            int r9 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r9 >= 0) goto L_0x0347
            int r3 = (int) r5
            r1.b(r3)
        L_0x0345:
            r3 = 0
            goto L_0x034f
        L_0x0347:
            long r3 = r34.c()
            long r3 = r3 + r5
            r2.a = r3
            r3 = 1
        L_0x034f:
            r0.b(r7)
            if (r3 == 0) goto L_0x035b
            int r3 = r0.i
            r4 = 2
            if (r3 == r4) goto L_0x035b
            r10 = 1
            goto L_0x035c
        L_0x035b:
            r10 = 0
        L_0x035c:
            if (r10 == 0) goto L_0x0006
            r3 = 1
            return r3
        L_0x0360:
            r3 = 1
            int r5 = r0.l
            if (r5 != 0) goto L_0x038a
            com.google.android.exoplayer2.util.ParsableByteArray r5 = r0.e
            byte[] r5 = r5.a
            r6 = 0
            boolean r5 = r1.a(r5, r6, r4, r3)
            if (r5 != 0) goto L_0x0373
            r9 = 0
            goto L_0x0509
        L_0x0373:
            r0.l = r4
            com.google.android.exoplayer2.util.ParsableByteArray r3 = r0.e
            r3.d(r6)
            com.google.android.exoplayer2.util.ParsableByteArray r3 = r0.e
            long r5 = r3.h()
            r0.k = r5
            com.google.android.exoplayer2.util.ParsableByteArray r3 = r0.e
            int r3 = r3.j()
            r0.j = r3
        L_0x038a:
            long r5 = r0.k
            r9 = 1
            int r3 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r3 != 0) goto L_0x03a7
            com.google.android.exoplayer2.util.ParsableByteArray r3 = r0.e
            byte[] r3 = r3.a
            r1.b(r3, r4, r4)
            int r3 = r0.l
            int r3 = r3 + r4
            r0.l = r3
            com.google.android.exoplayer2.util.ParsableByteArray r3 = r0.e
            long r5 = r3.q()
        L_0x03a4:
            r0.k = r5
            goto L_0x03cf
        L_0x03a7:
            r9 = 0
            int r3 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r3 != 0) goto L_0x03cf
            long r5 = r34.d()
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 != 0) goto L_0x03c1
            java.util.ArrayDeque r3 = r0.f
            java.lang.Object r3 = r3.peek()
            com.google.android.exoplayer2.extractor.mp4.Atom$ContainerAtom r3 = (com.google.android.exoplayer2.extractor.mp4.Atom.ContainerAtom) r3
            if (r3 == 0) goto L_0x03c1
            long r5 = r3.b
        L_0x03c1:
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 == 0) goto L_0x03cf
            long r7 = r34.c()
            long r5 = r5 - r7
            int r3 = r0.l
            long r7 = (long) r3
            long r5 = r5 + r7
            goto L_0x03a4
        L_0x03cf:
            long r5 = r0.k
            int r3 = r0.l
            long r7 = (long) r3
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 < 0) goto L_0x050c
            int r5 = r0.j
            r6 = 1836019574(0x6d6f6f76, float:4.631354E27)
            r7 = 1835365473(0x6d657461, float:4.4382975E27)
            if (r5 == r6) goto L_0x0400
            r6 = 1953653099(0x7472616b, float:7.681346E31)
            if (r5 == r6) goto L_0x0400
            r6 = 1835297121(0x6d646961, float:4.4181236E27)
            if (r5 == r6) goto L_0x0400
            r6 = 1835626086(0x6d696e66, float:4.515217E27)
            if (r5 == r6) goto L_0x0400
            r6 = 1937007212(0x7374626c, float:1.9362132E31)
            if (r5 == r6) goto L_0x0400
            r6 = 1701082227(0x65647473, float:6.742798E22)
            if (r5 == r6) goto L_0x0400
            if (r5 != r7) goto L_0x03fe
            goto L_0x0400
        L_0x03fe:
            r6 = 0
            goto L_0x0401
        L_0x0400:
            r6 = 1
        L_0x0401:
            if (r6 == 0) goto L_0x0452
            long r5 = r34.c()
            long r8 = r0.k
            long r5 = r5 + r8
            int r3 = r0.l
            long r10 = (long) r3
            long r5 = r5 - r10
            long r10 = (long) r3
            int r3 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r3 == 0) goto L_0x0433
            int r3 = r0.j
            if (r3 != r7) goto L_0x0433
            com.google.android.exoplayer2.util.ParsableByteArray r3 = r0.d
            r3.a(r4)
            com.google.android.exoplayer2.util.ParsableByteArray r3 = r0.d
            byte[] r3 = r3.a
            r7 = 0
            r1.d(r3, r7, r4)
            com.google.android.exoplayer2.util.ParsableByteArray r3 = r0.d
            com.google.android.exoplayer2.extractor.mp4.AtomParsers.a((com.google.android.exoplayer2.util.ParsableByteArray) r3)
            com.google.android.exoplayer2.util.ParsableByteArray r3 = r0.d
            int r3 = r3.b
            r1.b(r3)
            r34.a()
        L_0x0433:
            java.util.ArrayDeque r3 = r0.f
            com.google.android.exoplayer2.extractor.mp4.Atom$ContainerAtom r4 = new com.google.android.exoplayer2.extractor.mp4.Atom$ContainerAtom
            int r7 = r0.j
            r4.<init>(r7, r5)
            r3.push(r4)
            long r3 = r0.k
            int r7 = r0.l
            long r7 = (long) r7
            int r9 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r9 != 0) goto L_0x044c
            r0.b(r5)
            goto L_0x044f
        L_0x044c:
            r33.d()
        L_0x044f:
            r3 = 1
            goto L_0x0508
        L_0x0452:
            r6 = 1835296868(0x6d646864, float:4.418049E27)
            if (r5 == r6) goto L_0x04af
            r6 = 1836476516(0x6d766864, float:4.7662196E27)
            if (r5 == r6) goto L_0x04af
            r6 = 1751411826(0x68646c72, float:4.3148E24)
            if (r5 == r6) goto L_0x04af
            r6 = 1937011556(0x73747364, float:1.9367383E31)
            if (r5 == r6) goto L_0x04af
            r6 = 1937011827(0x73747473, float:1.9367711E31)
            if (r5 == r6) goto L_0x04af
            r6 = 1937011571(0x73747373, float:1.9367401E31)
            if (r5 == r6) goto L_0x04af
            r6 = 1668576371(0x63747473, float:4.5093966E21)
            if (r5 == r6) goto L_0x04af
            r6 = 1701606260(0x656c7374, float:6.9788014E22)
            if (r5 == r6) goto L_0x04af
            r6 = 1937011555(0x73747363, float:1.9367382E31)
            if (r5 == r6) goto L_0x04af
            r6 = 1937011578(0x7374737a, float:1.936741E31)
            if (r5 == r6) goto L_0x04af
            r6 = 1937013298(0x73747a32, float:1.9369489E31)
            if (r5 == r6) goto L_0x04af
            r6 = 1937007471(0x7374636f, float:1.9362445E31)
            if (r5 == r6) goto L_0x04af
            r6 = 1668232756(0x636f3634, float:4.4126776E21)
            if (r5 == r6) goto L_0x04af
            r6 = 1953196132(0x746b6864, float:7.46037E31)
            if (r5 == r6) goto L_0x04af
            r6 = 1718909296(0x66747970, float:2.8862439E23)
            if (r5 == r6) goto L_0x04af
            r6 = 1969517665(0x75647461, float:2.8960062E32)
            if (r5 == r6) goto L_0x04af
            r6 = 1801812339(0x6b657973, float:2.7741754E26)
            if (r5 == r6) goto L_0x04af
            r6 = 1768715124(0x696c7374, float:1.7865732E25)
            if (r5 != r6) goto L_0x04ad
            goto L_0x04af
        L_0x04ad:
            r5 = 0
            goto L_0x04b0
        L_0x04af:
            r5 = 1
        L_0x04b0:
            if (r5 == 0) goto L_0x04dc
            if (r3 != r4) goto L_0x04b6
            r3 = 1
            goto L_0x04b7
        L_0x04b6:
            r3 = 0
        L_0x04b7:
            com.google.android.exoplayer2.util.Assertions.b((boolean) r3)
            long r5 = r0.k
            r7 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 > 0) goto L_0x04c5
            r3 = 1
            goto L_0x04c6
        L_0x04c5:
            r3 = 0
        L_0x04c6:
            com.google.android.exoplayer2.util.Assertions.b((boolean) r3)
            com.google.android.exoplayer2.util.ParsableByteArray r3 = new com.google.android.exoplayer2.util.ParsableByteArray
            long r5 = r0.k
            int r6 = (int) r5
            r3.<init>((int) r6)
            com.google.android.exoplayer2.util.ParsableByteArray r5 = r0.e
            byte[] r5 = r5.a
            byte[] r6 = r3.a
            r7 = 0
            java.lang.System.arraycopy(r5, r7, r6, r7, r4)
            goto L_0x0503
        L_0x04dc:
            long r3 = r34.c()
            int r5 = r0.l
            long r5 = (long) r5
            long r22 = r3 - r5
            int r3 = r0.j
            r4 = 1836086884(0x6d707664, float:4.6512205E27)
            if (r3 != r4) goto L_0x0502
            com.google.android.exoplayer2.metadata.mp4.MotionPhotoMetadata r19 = new com.google.android.exoplayer2.metadata.mp4.MotionPhotoMetadata
            r20 = 0
            r24 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r3 = r0.l
            long r4 = (long) r3
            long r26 = r22 + r4
            long r4 = r0.k
            long r6 = (long) r3
            long r28 = r4 - r6
            r19.<init>(r20, r22, r24, r26, r28)
        L_0x0502:
            r3 = 0
        L_0x0503:
            r0.m = r3
            r3 = 1
            r0.i = r3
        L_0x0508:
            r9 = 1
        L_0x0509:
            if (r9 != 0) goto L_0x0006
            return r13
        L_0x050c:
            com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
            java.lang.String r2 = "Atom size less than header length (unsupported)."
            r1.<init>((java.lang.String) r2)
            goto L_0x0515
        L_0x0514:
            throw r1
        L_0x0515:
            goto L_0x0514
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp4.Mp4Extractor.a(com.google.android.exoplayer2.extractor.ExtractorInput, com.google.android.exoplayer2.extractor.PositionHolder):int");
    }

    public final SeekMap.SeekPoints a(long j2) {
        long j3;
        long j4;
        long j5;
        long j6;
        int b2;
        if (((Mp4Track[]) Assertions.b((Object) this.r)).length == 0) {
            return new SeekMap.SeekPoints(SeekPoint.a);
        }
        int i2 = this.t;
        if (i2 != -1) {
            TrackSampleTable trackSampleTable = this.r[i2].b;
            int a2 = a(trackSampleTable, j2);
            if (a2 == -1) {
                return new SeekMap.SeekPoints(SeekPoint.a);
            }
            long j7 = trackSampleTable.f[a2];
            j3 = trackSampleTable.c[a2];
            if (j7 >= j2 || a2 >= trackSampleTable.b - 1 || (b2 = trackSampleTable.b(j2)) == -1 || b2 == a2) {
                j6 = -1;
                j4 = -9223372036854775807L;
            } else {
                j4 = trackSampleTable.f[b2];
                j6 = trackSampleTable.c[b2];
            }
            j5 = j6;
            j2 = j7;
        } else {
            j3 = LocationRequestCompat.PASSIVE_INTERVAL;
            j5 = -1;
            j4 = -9223372036854775807L;
        }
        int i3 = 0;
        while (true) {
            Mp4Track[] mp4TrackArr = this.r;
            if (i3 >= mp4TrackArr.length) {
                break;
            }
            if (i3 != this.t) {
                TrackSampleTable trackSampleTable2 = mp4TrackArr[i3].b;
                long a3 = a(trackSampleTable2, j2, j3);
                if (j4 != -9223372036854775807L) {
                    j5 = a(trackSampleTable2, j4, j5);
                }
                j3 = a3;
            }
            i3++;
        }
        SeekPoint seekPoint = new SeekPoint(j2, j3);
        return j4 == -9223372036854775807L ? new SeekMap.SeekPoints(seekPoint) : new SeekMap.SeekPoints(seekPoint, new SeekPoint(j4, j5));
    }

    public final void a(long j2, long j3) {
        this.f.clear();
        this.l = 0;
        this.n = -1;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        if (j2 == 0) {
            if (this.i != 3) {
                d();
                return;
            }
            this.g.a();
            this.h.clear();
        } else if (this.r != null) {
            c(j3);
        }
    }

    public final void a(ExtractorOutput extractorOutput) {
        this.a = extractorOutput;
    }

    public final boolean a() {
        return true;
    }

    public final boolean a(ExtractorInput extractorInput) {
        return Sniffer.a(extractorInput, false);
    }

    public final long b() {
        return this.u;
    }
}
