package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.trackselection.ExoTrackSelection;
import com.google.android.exoplayer2.util.Assertions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;

final class MergingMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
    final MediaPeriod[] a;
    private final IdentityHashMap b;
    private final CompositeSequenceableLoaderFactory c;
    private final ArrayList d = new ArrayList();
    private MediaPeriod.Callback e;
    private TrackGroupArray f;
    private MediaPeriod[] g;
    private SequenceableLoader h;

    final class TimeOffsetMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
        /* access modifiers changed from: private */
        public final MediaPeriod a;
        private final long b;
        private MediaPeriod.Callback c;

        public TimeOffsetMediaPeriod(MediaPeriod mediaPeriod, long j) {
            this.a = mediaPeriod;
            this.b = j;
        }

        public final long a(long j, SeekParameters seekParameters) {
            return this.a.a(j - this.b, seekParameters) + this.b;
        }

        public final long a(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
            SampleStream[] sampleStreamArr2 = sampleStreamArr;
            SampleStream[] sampleStreamArr3 = new SampleStream[sampleStreamArr2.length];
            int i = 0;
            while (true) {
                SampleStream sampleStream = null;
                if (i >= sampleStreamArr2.length) {
                    break;
                }
                TimeOffsetSampleStream timeOffsetSampleStream = (TimeOffsetSampleStream) sampleStreamArr2[i];
                if (timeOffsetSampleStream != null) {
                    sampleStream = timeOffsetSampleStream.a;
                }
                sampleStreamArr3[i] = sampleStream;
                i++;
            }
            long a2 = this.a.a(exoTrackSelectionArr, zArr, sampleStreamArr3, zArr2, j - this.b);
            for (int i2 = 0; i2 < sampleStreamArr2.length; i2++) {
                SampleStream sampleStream2 = sampleStreamArr3[i2];
                if (sampleStream2 == null) {
                    sampleStreamArr2[i2] = null;
                } else {
                    SampleStream sampleStream3 = sampleStreamArr2[i2];
                    if (sampleStream3 == null || ((TimeOffsetSampleStream) sampleStream3).a != sampleStream2) {
                        sampleStreamArr2[i2] = new TimeOffsetSampleStream(sampleStream2, this.b);
                    }
                }
            }
            return a2 + this.b;
        }

        public final void a() {
            this.a.a();
        }

        public final void a(long j, boolean z) {
            this.a.a(j - this.b, z);
        }

        public final void a(MediaPeriod.Callback callback, long j) {
            this.c = callback;
            this.a.a((MediaPeriod.Callback) this, j - this.b);
        }

        public final void a(MediaPeriod mediaPeriod) {
            ((MediaPeriod.Callback) Assertions.b((Object) this.c)).a(this);
        }

        public final /* synthetic */ void a(SequenceableLoader sequenceableLoader) {
            ((MediaPeriod.Callback) Assertions.b((Object) this.c)).a(this);
        }

        public final void a_(long j) {
            this.a.a_(j - this.b);
        }

        public final long b(long j) {
            return this.a.b(j - this.b) + this.b;
        }

        public final TrackGroupArray b() {
            return this.a.b();
        }

        public final long c() {
            long c2 = this.a.c();
            if (c2 == -9223372036854775807L) {
                return -9223372036854775807L;
            }
            return c2 + this.b;
        }

        public final boolean c(long j) {
            return this.a.c(j - this.b);
        }

        public final long d() {
            long d = this.a.d();
            if (d == Long.MIN_VALUE) {
                return Long.MIN_VALUE;
            }
            return d + this.b;
        }

        public final long e() {
            long e = this.a.e();
            if (e == Long.MIN_VALUE) {
                return Long.MIN_VALUE;
            }
            return e + this.b;
        }

        public final boolean f() {
            return this.a.f();
        }
    }

    final class TimeOffsetSampleStream implements SampleStream {
        final SampleStream a;
        private final long b;

        public TimeOffsetSampleStream(SampleStream sampleStream, long j) {
            this.a = sampleStream;
            this.b = j;
        }

        public final int a(long j) {
            return this.a.a(j - this.b);
        }

        public final int a(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i) {
            int a2 = this.a.a(formatHolder, decoderInputBuffer, i);
            if (a2 == -4) {
                decoderInputBuffer.e = Math.max(0, decoderInputBuffer.e + this.b);
            }
            return a2;
        }

        public final boolean a() {
            return this.a.a();
        }

        public final void b() {
            this.a.b();
        }
    }

    public MergingMediaPeriod(CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory, long[] jArr, MediaPeriod... mediaPeriodArr) {
        this.c = compositeSequenceableLoaderFactory;
        this.a = mediaPeriodArr;
        this.h = compositeSequenceableLoaderFactory.a(new SequenceableLoader[0]);
        this.b = new IdentityHashMap();
        this.g = new MediaPeriod[0];
        for (int i = 0; i < mediaPeriodArr.length; i++) {
            long j = jArr[i];
            if (j != 0) {
                this.a[i] = new TimeOffsetMediaPeriod(mediaPeriodArr[i], j);
            }
        }
    }

    public final long a(long j, SeekParameters seekParameters) {
        MediaPeriod[] mediaPeriodArr = this.g;
        return (mediaPeriodArr.length > 0 ? mediaPeriodArr[0] : this.a[0]).a(j, seekParameters);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v8, resolved type: java.lang.Integer} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long a(com.google.android.exoplayer2.trackselection.ExoTrackSelection[] r21, boolean[] r22, com.google.android.exoplayer2.source.SampleStream[] r23, boolean[] r24, long r25) {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            r2 = r23
            int r3 = r1.length
            int[] r3 = new int[r3]
            int r4 = r1.length
            int[] r4 = new int[r4]
            r6 = 0
        L_0x000d:
            int r7 = r1.length
            if (r6 >= r7) goto L_0x004f
            r7 = r2[r6]
            if (r7 != 0) goto L_0x0016
            r8 = 0
            goto L_0x001f
        L_0x0016:
            java.util.IdentityHashMap r8 = r0.b
            java.lang.Object r7 = r8.get(r7)
            r8 = r7
            java.lang.Integer r8 = (java.lang.Integer) r8
        L_0x001f:
            r7 = -1
            if (r8 != 0) goto L_0x0024
            r8 = -1
            goto L_0x0028
        L_0x0024:
            int r8 = r8.intValue()
        L_0x0028:
            r3[r6] = r8
            r4[r6] = r7
            r8 = r1[r6]
            if (r8 == 0) goto L_0x004c
            com.google.android.exoplayer2.source.TrackGroup r8 = r8.e()
            r9 = 0
        L_0x0035:
            com.google.android.exoplayer2.source.MediaPeriod[] r10 = r0.a
            int r11 = r10.length
            if (r9 >= r11) goto L_0x004c
            r10 = r10[r9]
            com.google.android.exoplayer2.source.TrackGroupArray r10 = r10.b()
            int r10 = r10.a(r8)
            if (r10 == r7) goto L_0x0049
            r4[r6] = r9
            goto L_0x004c
        L_0x0049:
            int r9 = r9 + 1
            goto L_0x0035
        L_0x004c:
            int r6 = r6 + 1
            goto L_0x000d
        L_0x004f:
            java.util.IdentityHashMap r6 = r0.b
            r6.clear()
            int r6 = r1.length
            com.google.android.exoplayer2.source.SampleStream[] r7 = new com.google.android.exoplayer2.source.SampleStream[r6]
            int r9 = r1.length
            com.google.android.exoplayer2.source.SampleStream[] r9 = new com.google.android.exoplayer2.source.SampleStream[r9]
            int r10 = r1.length
            com.google.android.exoplayer2.trackselection.ExoTrackSelection[] r15 = new com.google.android.exoplayer2.trackselection.ExoTrackSelection[r10]
            java.util.ArrayList r14 = new java.util.ArrayList
            com.google.android.exoplayer2.source.MediaPeriod[] r10 = r0.a
            int r10 = r10.length
            r14.<init>(r10)
            r17 = r25
            r13 = 0
        L_0x0068:
            com.google.android.exoplayer2.source.MediaPeriod[] r10 = r0.a
            int r10 = r10.length
            if (r13 >= r10) goto L_0x00ed
            r10 = 0
        L_0x006e:
            int r11 = r1.length
            if (r10 >= r11) goto L_0x0088
            r11 = r3[r10]
            if (r11 != r13) goto L_0x0078
            r11 = r2[r10]
            goto L_0x0079
        L_0x0078:
            r11 = 0
        L_0x0079:
            r9[r10] = r11
            r11 = r4[r10]
            if (r11 != r13) goto L_0x0082
            r11 = r1[r10]
            goto L_0x0083
        L_0x0082:
            r11 = 0
        L_0x0083:
            r15[r10] = r11
            int r10 = r10 + 1
            goto L_0x006e
        L_0x0088:
            com.google.android.exoplayer2.source.MediaPeriod[] r10 = r0.a
            r10 = r10[r13]
            r11 = r15
            r12 = r22
            r8 = r13
            r13 = r9
            r5 = r14
            r14 = r24
            r19 = r15
            r15 = r17
            long r10 = r10.a(r11, r12, r13, r14, r15)
            if (r8 != 0) goto L_0x00a1
            r17 = r10
            goto L_0x00a5
        L_0x00a1:
            int r12 = (r10 > r17 ? 1 : (r10 == r17 ? 0 : -1))
            if (r12 != 0) goto L_0x00e5
        L_0x00a5:
            r10 = 0
            r11 = 0
        L_0x00a7:
            int r12 = r1.length
            if (r10 >= r12) goto L_0x00d6
            r12 = r4[r10]
            r13 = 1
            if (r12 != r8) goto L_0x00c6
            r11 = r9[r10]
            java.lang.Object r11 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r11)
            com.google.android.exoplayer2.source.SampleStream r11 = (com.google.android.exoplayer2.source.SampleStream) r11
            r12 = r9[r10]
            r7[r10] = r12
            java.util.IdentityHashMap r12 = r0.b
            java.lang.Integer r14 = java.lang.Integer.valueOf(r8)
            r12.put(r11, r14)
            r11 = 1
            goto L_0x00d3
        L_0x00c6:
            r12 = r3[r10]
            if (r12 != r8) goto L_0x00d3
            r12 = r9[r10]
            if (r12 != 0) goto L_0x00cf
            goto L_0x00d0
        L_0x00cf:
            r13 = 0
        L_0x00d0:
            com.google.android.exoplayer2.util.Assertions.b((boolean) r13)
        L_0x00d3:
            int r10 = r10 + 1
            goto L_0x00a7
        L_0x00d6:
            if (r11 == 0) goto L_0x00df
            com.google.android.exoplayer2.source.MediaPeriod[] r10 = r0.a
            r10 = r10[r8]
            r5.add(r10)
        L_0x00df:
            int r13 = r8 + 1
            r14 = r5
            r15 = r19
            goto L_0x0068
        L_0x00e5:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "Children enabled at different positions."
            r1.<init>(r2)
            throw r1
        L_0x00ed:
            r5 = r14
            r1 = 0
            java.lang.System.arraycopy(r7, r1, r2, r1, r6)
            com.google.android.exoplayer2.source.MediaPeriod[] r1 = new com.google.android.exoplayer2.source.MediaPeriod[r1]
            java.lang.Object[] r1 = r5.toArray(r1)
            com.google.android.exoplayer2.source.MediaPeriod[] r1 = (com.google.android.exoplayer2.source.MediaPeriod[]) r1
            r0.g = r1
            com.google.android.exoplayer2.source.CompositeSequenceableLoaderFactory r2 = r0.c
            com.google.android.exoplayer2.source.SequenceableLoader r1 = r2.a(r1)
            r0.h = r1
            return r17
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.MergingMediaPeriod.a(com.google.android.exoplayer2.trackselection.ExoTrackSelection[], boolean[], com.google.android.exoplayer2.source.SampleStream[], boolean[], long):long");
    }

    public final void a() {
        for (MediaPeriod a2 : this.a) {
            a2.a();
        }
    }

    public final void a(long j, boolean z) {
        for (MediaPeriod a2 : this.g) {
            a2.a(j, z);
        }
    }

    public final void a(MediaPeriod.Callback callback, long j) {
        this.e = callback;
        Collections.addAll(this.d, this.a);
        for (MediaPeriod a2 : this.a) {
            a2.a((MediaPeriod.Callback) this, j);
        }
    }

    public final void a(MediaPeriod mediaPeriod) {
        this.d.remove(mediaPeriod);
        if (this.d.isEmpty()) {
            int i = 0;
            for (MediaPeriod b2 : this.a) {
                i += b2.b().length;
            }
            TrackGroup[] trackGroupArr = new TrackGroup[i];
            int i2 = 0;
            for (MediaPeriod b3 : this.a) {
                TrackGroupArray b4 = b3.b();
                int i3 = b4.length;
                int i4 = 0;
                while (i4 < i3) {
                    trackGroupArr[i2] = b4.b[i4];
                    i4++;
                    i2++;
                }
            }
            this.f = new TrackGroupArray(trackGroupArr);
            ((MediaPeriod.Callback) Assertions.b((Object) this.e)).a(this);
        }
    }

    public final /* synthetic */ void a(SequenceableLoader sequenceableLoader) {
        ((MediaPeriod.Callback) Assertions.b((Object) this.e)).a(this);
    }

    public final void a_(long j) {
        this.h.a_(j);
    }

    public final long b(long j) {
        long b2 = this.g[0].b(j);
        int i = 1;
        while (true) {
            MediaPeriod[] mediaPeriodArr = this.g;
            if (i >= mediaPeriodArr.length) {
                return b2;
            }
            if (mediaPeriodArr[i].b(b2) == b2) {
                i++;
            } else {
                throw new IllegalStateException("Unexpected child seekToUs result.");
            }
        }
    }

    public final TrackGroupArray b() {
        return (TrackGroupArray) Assertions.b((Object) this.f);
    }

    public final long c() {
        long j = -9223372036854775807L;
        for (MediaPeriod mediaPeriod : this.g) {
            long c2 = mediaPeriod.c();
            if (c2 != -9223372036854775807L) {
                if (j == -9223372036854775807L) {
                    MediaPeriod[] mediaPeriodArr = this.g;
                    int length = mediaPeriodArr.length;
                    int i = 0;
                    while (i < length) {
                        MediaPeriod mediaPeriod2 = mediaPeriodArr[i];
                        if (mediaPeriod2 == mediaPeriod) {
                            break;
                        } else if (mediaPeriod2.b(c2) == c2) {
                            i++;
                        } else {
                            throw new IllegalStateException("Unexpected child seekToUs result.");
                        }
                    }
                    j = c2;
                } else if (c2 != j) {
                    throw new IllegalStateException("Conflicting discontinuities.");
                }
            } else if (!(j == -9223372036854775807L || mediaPeriod.b(j) == j)) {
                throw new IllegalStateException("Unexpected child seekToUs result.");
            }
        }
        return j;
    }

    public final boolean c(long j) {
        if (this.d.isEmpty()) {
            return this.h.c(j);
        }
        int size = this.d.size();
        for (int i = 0; i < size; i++) {
            ((MediaPeriod) this.d.get(i)).c(j);
        }
        return false;
    }

    public final long d() {
        return this.h.d();
    }

    public final long e() {
        return this.h.e();
    }

    public final boolean f() {
        return this.h.f();
    }
}
