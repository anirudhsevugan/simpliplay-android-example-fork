package com.google.android.exoplayer2.trackselection;

import com.dreamers.exoplayercore.repack.C0000a;
import com.dreamers.exoplayercore.repack.C0065ck;
import com.dreamers.exoplayercore.repack.C0075cu;
import com.dreamers.exoplayercore.repack.bG;
import com.dreamers.exoplayercore.repack.bH;
import com.dreamers.exoplayercore.repack.bZ;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.trackselection.ExoTrackSelection;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class AdaptiveTrackSelection extends BaseTrackSelection {
    private final BandwidthMeter b;
    private final long c = 10000000;
    private final long d = 25000000;
    private final long e;
    private final float f;
    private final float g;
    private final bG h;
    private final Clock i;
    private float j;
    private int k;
    private int l;
    private long m;
    private MediaChunk n;

    public final class AdaptationCheckpoint {
        public final long a;
        public final long b;

        public AdaptationCheckpoint(long j, long j2) {
            this.a = j;
            this.b = j2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AdaptationCheckpoint)) {
                return false;
            }
            AdaptationCheckpoint adaptationCheckpoint = (AdaptationCheckpoint) obj;
            return this.a == adaptationCheckpoint.a && this.b == adaptationCheckpoint.b;
        }

        public final int hashCode() {
            return (((int) this.a) * 31) + ((int) this.b);
        }
    }

    public class Factory implements ExoTrackSelection.Factory {
        private final Clock a;

        public Factory() {
            this(Clock.a);
        }

        private Factory(Clock clock) {
            this.a = clock;
        }

        /* JADX WARNING: type inference failed for: r6v6, types: [com.google.android.exoplayer2.trackselection.FixedTrackSelection] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.google.android.exoplayer2.trackselection.ExoTrackSelection[] a(com.google.android.exoplayer2.trackselection.ExoTrackSelection.Definition[] r17, com.google.android.exoplayer2.upstream.BandwidthMeter r18) {
            /*
                r16 = this;
                r0 = r17
                com.dreamers.exoplayercore.repack.bG r1 = com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection.a((com.google.android.exoplayer2.trackselection.ExoTrackSelection.Definition[]) r17)
                int r2 = r0.length
                com.google.android.exoplayer2.trackselection.ExoTrackSelection[] r2 = new com.google.android.exoplayer2.trackselection.ExoTrackSelection[r2]
                r3 = 0
                r4 = 0
            L_0x000b:
                int r5 = r0.length
                if (r4 >= r5) goto L_0x004c
                r5 = r0[r4]
                if (r5 == 0) goto L_0x0047
                int[] r6 = r5.b
                int r6 = r6.length
                if (r6 == 0) goto L_0x0047
                int[] r6 = r5.b
                int r6 = r6.length
                r7 = 1
                if (r6 != r7) goto L_0x002b
                com.google.android.exoplayer2.trackselection.FixedTrackSelection r6 = new com.google.android.exoplayer2.trackselection.FixedTrackSelection
                com.google.android.exoplayer2.source.TrackGroup r7 = r5.a
                int[] r5 = r5.b
                r5 = r5[r3]
                r6.<init>(r7, r5)
                r5 = r16
                goto L_0x0044
            L_0x002b:
                com.google.android.exoplayer2.source.TrackGroup r9 = r5.a
                int[] r10 = r5.b
                java.lang.Object r5 = r1.get(r4)
                r14 = r5
                com.dreamers.exoplayercore.repack.bG r14 = (com.dreamers.exoplayercore.repack.bG) r14
                com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection r6 = new com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
                r12 = 25000(0x61a8, double:1.23516E-319)
                r5 = r16
                com.google.android.exoplayer2.util.Clock r15 = r5.a
                r8 = r6
                r11 = r18
                r8.<init>(r9, r10, r11, r12, r14, r15)
            L_0x0044:
                r2[r4] = r6
                goto L_0x0049
            L_0x0047:
                r5 = r16
            L_0x0049:
                int r4 = r4 + 1
                goto L_0x000b
            L_0x004c:
                r5 = r16
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection.Factory.a(com.google.android.exoplayer2.trackselection.ExoTrackSelection$Definition[], com.google.android.exoplayer2.upstream.BandwidthMeter):com.google.android.exoplayer2.trackselection.ExoTrackSelection[]");
        }
    }

    protected AdaptiveTrackSelection(TrackGroup trackGroup, int[] iArr, BandwidthMeter bandwidthMeter, long j2, List list, Clock clock) {
        super(trackGroup, iArr, (byte) 0);
        this.b = bandwidthMeter;
        this.e = j2 * 1000;
        this.f = 0.7f;
        this.g = 0.75f;
        this.h = bG.a((Collection) list);
        this.i = clock;
        this.j = 1.0f;
        this.l = 0;
        this.m = -9223372036854775807L;
    }

    private int a(long j2, long j3) {
        long a = a(j3);
        int i2 = 0;
        for (int i3 = 0; i3 < this.a; i3++) {
            if (j2 == Long.MIN_VALUE || !b(i3, j2)) {
                if (c(a(i3).h, a)) {
                    return i3;
                }
                i2 = i3;
            }
        }
        return i2;
    }

    private long a(long j2) {
        long b2 = b(j2);
        if (this.h.isEmpty()) {
            return b2;
        }
        int i2 = 1;
        while (i2 < this.h.size() - 1 && ((AdaptationCheckpoint) this.h.get(i2)).a < b2) {
            i2++;
        }
        AdaptationCheckpoint adaptationCheckpoint = (AdaptationCheckpoint) this.h.get(i2 - 1);
        AdaptationCheckpoint adaptationCheckpoint2 = (AdaptationCheckpoint) this.h.get(i2);
        return adaptationCheckpoint.b + ((long) ((((float) (b2 - adaptationCheckpoint.a)) / ((float) (adaptationCheckpoint2.a - adaptationCheckpoint.a))) * ((float) (adaptationCheckpoint2.b - adaptationCheckpoint.b))));
    }

    private static long a(List list) {
        if (list.isEmpty()) {
            return -9223372036854775807L;
        }
        MediaChunk mediaChunk = (MediaChunk) C0000a.b((Iterable) list);
        if (mediaChunk.j == -9223372036854775807L || mediaChunk.k == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        return mediaChunk.k - mediaChunk.j;
    }

    static /* synthetic */ bG a(ExoTrackSelection.Definition[] definitionArr) {
        bH bHVar;
        ArrayList arrayList = new ArrayList();
        for (ExoTrackSelection.Definition definition : definitionArr) {
            if (definition == null || definition.b.length <= 1) {
                bHVar = null;
            } else {
                bHVar = bG.i();
                bHVar.b(new AdaptationCheckpoint(0, 0));
            }
            arrayList.add(bHVar);
        }
        long[][] b2 = b(definitionArr);
        int[] iArr = new int[b2.length];
        long[] jArr = new long[b2.length];
        for (int i2 = 0; i2 < b2.length; i2++) {
            long[] jArr2 = b2[i2];
            jArr[i2] = jArr2.length == 0 ? 0 : jArr2[0];
        }
        a((List) arrayList, jArr);
        bG a = a(b2);
        for (int i3 = 0; i3 < a.size(); i3++) {
            int intValue = ((Integer) a.get(i3)).intValue();
            int i4 = iArr[intValue] + 1;
            iArr[intValue] = i4;
            jArr[intValue] = b2[intValue][i4];
            a((List) arrayList, jArr);
        }
        for (int i5 = 0; i5 < definitionArr.length; i5++) {
            if (arrayList.get(i5) != null) {
                jArr[i5] = jArr[i5] << 1;
            }
        }
        a((List) arrayList, jArr);
        bH i6 = bG.i();
        for (int i7 = 0; i7 < arrayList.size(); i7++) {
            bH bHVar2 = (bH) arrayList.get(i7);
            i6.b(bHVar2 == null ? bG.g() : bHVar2.a());
        }
        return i6.a();
    }

    private static bG a(long[][] jArr) {
        C0075cu b2 = C0075cu.b();
        C0000a.a((Object) b2);
        bZ a = new C0065ck(b2).b().a();
        for (int i2 = 0; i2 < jArr.length; i2++) {
            long[] jArr2 = jArr[i2];
            if (jArr2.length > 1) {
                int length = jArr2.length;
                double[] dArr = new double[length];
                int i3 = 0;
                while (true) {
                    long[] jArr3 = jArr[i2];
                    double d2 = 0.0d;
                    if (i3 >= jArr3.length) {
                        break;
                    }
                    long j2 = jArr3[i3];
                    if (j2 != -1) {
                        d2 = Math.log((double) j2);
                    }
                    dArr[i3] = d2;
                    i3++;
                }
                int i4 = length - 1;
                double d3 = dArr[i4] - dArr[0];
                int i5 = 0;
                while (i5 < i4) {
                    double d4 = dArr[i5];
                    i5++;
                    a.a(Double.valueOf(d3 == 0.0d ? 1.0d : (((d4 + dArr[i5]) * 0.5d) - dArr[0]) / d3), Integer.valueOf(i2));
                }
            }
        }
        return bG.a(a.h());
    }

    private static void a(List list, long[] jArr) {
        long j2 = 0;
        for (long j3 : jArr) {
            j2 += j3;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            bH bHVar = (bH) list.get(i2);
            if (bHVar != null) {
                bHVar.b(new AdaptationCheckpoint(j2, jArr[i2]));
            }
        }
    }

    private long b(long j2) {
        long a = (long) (((float) this.b.a()) * 0.7f);
        long b2 = this.b.b();
        if (b2 == -9223372036854775807L || j2 == -9223372036854775807L) {
            return (long) (((float) a) / this.j);
        }
        float f2 = (float) j2;
        return (long) ((((float) a) * Math.max((f2 / this.j) - ((float) b2), 0.0f)) / f2);
    }

    private static long[][] b(ExoTrackSelection.Definition[] definitionArr) {
        long[][] jArr = new long[definitionArr.length][];
        for (int i2 = 0; i2 < definitionArr.length; i2++) {
            ExoTrackSelection.Definition definition = definitionArr[i2];
            if (definition == null) {
                jArr[i2] = new long[0];
            } else {
                jArr[i2] = new long[definition.b.length];
                for (int i3 = 0; i3 < definition.b.length; i3++) {
                    long[] jArr2 = jArr[i2];
                    TrackGroup trackGroup = definition.a;
                    jArr2[i3] = (long) trackGroup.b[definition.b[i3]].h;
                }
                Arrays.sort(jArr[i2]);
            }
        }
        return jArr;
    }

    private static boolean c(int i2, long j2) {
        return ((long) i2) <= j2;
    }

    public final int a() {
        return this.k;
    }

    public final int a(long j2, List list) {
        long a = this.i.a();
        long j3 = this.m;
        if (!(j3 == -9223372036854775807L || a - j3 >= 1000 || (!list.isEmpty() && !((MediaChunk) C0000a.b((Iterable) list)).equals(this.n)))) {
            return list.size();
        }
        this.m = a;
        this.n = list.isEmpty() ? null : (MediaChunk) C0000a.b((Iterable) list);
        if (list.isEmpty()) {
            return 0;
        }
        int size = list.size();
        long b2 = Util.b(((MediaChunk) list.get(size - 1)).j - j2, this.j);
        long j4 = this.e;
        if (b2 < j4) {
            return size;
        }
        Format a2 = a(a(a, a(list)));
        for (int i2 = 0; i2 < size; i2++) {
            MediaChunk mediaChunk = (MediaChunk) list.get(i2);
            Format format = mediaChunk.g;
            if (Util.b(mediaChunk.j - j2, this.j) >= j4 && format.h < a2.h && format.height != -1 && format.height < 720 && format.width != -1 && format.width < 1280 && format.height < a2.height) {
                return i2;
            }
        }
        return size;
    }

    public final void a(float f2) {
        this.j = f2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00a6, code lost:
        if (r16 < r13) goto L_0x00b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00b4, code lost:
        if (r16 >= 25000000) goto L_0x00b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b6, code lost:
        r6 = r10;
     */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0051  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(long r16, long r18, java.util.List r20, com.google.android.exoplayer2.source.chunk.MediaChunkIterator[] r21) {
        /*
            r15 = this;
            r0 = r15
            r1 = r18
            r3 = r21
            com.google.android.exoplayer2.util.Clock r4 = r0.i
            long r4 = r4.a()
            int r6 = r0.k
            int r7 = r3.length
            r8 = 0
            if (r6 >= r7) goto L_0x0027
            r6 = r3[r6]
            boolean r6 = r6.a()
            if (r6 == 0) goto L_0x0027
            int r6 = r0.k
            r3 = r3[r6]
            long r6 = r3.e()
            long r9 = r3.d()
        L_0x0025:
            long r6 = r6 - r9
            goto L_0x0043
        L_0x0027:
            int r6 = r3.length
            r7 = 0
        L_0x0029:
            if (r7 >= r6) goto L_0x003f
            r9 = r3[r7]
            boolean r10 = r9.a()
            if (r10 == 0) goto L_0x003c
            long r6 = r9.e()
            long r9 = r9.d()
            goto L_0x0025
        L_0x003c:
            int r7 = r7 + 1
            goto L_0x0029
        L_0x003f:
            long r6 = a((java.util.List) r20)
        L_0x0043:
            int r3 = r0.l
            r9 = 1
            if (r3 != 0) goto L_0x0051
            r0.l = r9
            int r1 = r15.a((long) r4, (long) r6)
            r0.k = r1
            return
        L_0x0051:
            int r10 = r0.k
            boolean r11 = r20.isEmpty()
            r12 = -1
            if (r11 == 0) goto L_0x005c
            r11 = -1
            goto L_0x0068
        L_0x005c:
            java.lang.Object r11 = com.dreamers.exoplayercore.repack.C0000a.b((java.lang.Iterable) r20)
            com.google.android.exoplayer2.source.chunk.MediaChunk r11 = (com.google.android.exoplayer2.source.chunk.MediaChunk) r11
            com.google.android.exoplayer2.Format r11 = r11.g
            int r11 = r15.a((com.google.android.exoplayer2.Format) r11)
        L_0x0068:
            if (r11 == r12) goto L_0x0073
            java.lang.Object r3 = com.dreamers.exoplayercore.repack.C0000a.b((java.lang.Iterable) r20)
            com.google.android.exoplayer2.source.chunk.MediaChunk r3 = (com.google.android.exoplayer2.source.chunk.MediaChunk) r3
            int r3 = r3.h
            r10 = r11
        L_0x0073:
            int r6 = r15.a((long) r4, (long) r6)
            boolean r4 = r15.b(r10, r4)
            if (r4 != 0) goto L_0x00b7
            com.google.android.exoplayer2.Format r4 = r15.a((int) r10)
            com.google.android.exoplayer2.Format r5 = r15.a((int) r6)
            int r7 = r5.h
            int r11 = r4.h
            if (r7 <= r11) goto L_0x00a9
            r11 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r13 = 10000000(0x989680, double:4.9406565E-317)
            int r7 = (r1 > r11 ? 1 : (r1 == r11 ? 0 : -1))
            if (r7 == 0) goto L_0x009c
            int r7 = (r1 > r13 ? 1 : (r1 == r13 ? 0 : -1))
            if (r7 > 0) goto L_0x009c
            r8 = 1
        L_0x009c:
            if (r8 == 0) goto L_0x00a4
            float r1 = (float) r1
            r2 = 1061158912(0x3f400000, float:0.75)
            float r1 = r1 * r2
            long r13 = (long) r1
        L_0x00a4:
            int r1 = (r16 > r13 ? 1 : (r16 == r13 ? 0 : -1))
            if (r1 >= 0) goto L_0x00a9
            goto L_0x00b6
        L_0x00a9:
            int r1 = r5.h
            int r2 = r4.h
            if (r1 >= r2) goto L_0x00b7
            r1 = 25000000(0x17d7840, double:1.2351641E-316)
            int r4 = (r16 > r1 ? 1 : (r16 == r1 ? 0 : -1))
            if (r4 < 0) goto L_0x00b7
        L_0x00b6:
            r6 = r10
        L_0x00b7:
            if (r6 != r10) goto L_0x00ba
            goto L_0x00bb
        L_0x00ba:
            r3 = 3
        L_0x00bb:
            r0.l = r3
            r0.k = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection.a(long, long, java.util.List, com.google.android.exoplayer2.source.chunk.MediaChunkIterator[]):void");
    }

    public final int b() {
        return this.l;
    }

    public final void c() {
        this.m = -9223372036854775807L;
        this.n = null;
    }

    public final void d() {
        this.n = null;
    }
}
