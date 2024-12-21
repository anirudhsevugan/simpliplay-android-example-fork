package com.google.android.exoplayer2;

import com.google.android.exoplayer2.source.ClippingMediaPeriod;
import com.google.android.exoplayer2.source.EmptySampleStream;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.ExoTrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;

final class MediaPeriodHolder {
    public final MediaPeriod a;
    public final Object b;
    public final SampleStream[] c;
    public boolean d;
    public boolean e;
    public MediaPeriodInfo f;
    public boolean g;
    MediaPeriodHolder h;
    TrackGroupArray i = TrackGroupArray.a;
    TrackSelectorResult j;
    long k;
    private final boolean[] l;
    private final RendererCapabilities[] m;
    private final TrackSelector n;
    private final MediaSourceList o;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: com.google.android.exoplayer2.source.MaskingMediaPeriod} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: com.google.android.exoplayer2.source.MaskingMediaPeriod} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: com.google.android.exoplayer2.source.ClippingMediaPeriod} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: com.google.android.exoplayer2.source.MaskingMediaPeriod} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MediaPeriodHolder(com.google.android.exoplayer2.RendererCapabilities[] r8, long r9, com.google.android.exoplayer2.trackselection.TrackSelector r11, com.google.android.exoplayer2.upstream.Allocator r12, com.google.android.exoplayer2.MediaSourceList r13, com.google.android.exoplayer2.MediaPeriodInfo r14, com.google.android.exoplayer2.trackselection.TrackSelectorResult r15) {
        /*
            r7 = this;
            r7.<init>()
            r7.m = r8
            r7.k = r9
            r7.n = r11
            r7.o = r13
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r9 = r14.a
            java.lang.Object r9 = r9.a
            r7.b = r9
            r7.f = r14
            com.google.android.exoplayer2.source.TrackGroupArray r9 = com.google.android.exoplayer2.source.TrackGroupArray.a
            r7.i = r9
            r7.j = r15
            int r9 = r8.length
            com.google.android.exoplayer2.source.SampleStream[] r9 = new com.google.android.exoplayer2.source.SampleStream[r9]
            r7.c = r9
            int r8 = r8.length
            boolean[] r8 = new boolean[r8]
            r7.l = r8
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r8 = r14.a
            long r9 = r14.b
            long r5 = r14.d
            java.lang.Object r11 = r8.a
            java.lang.Object r11 = com.google.android.exoplayer2.MediaSourceList.a((java.lang.Object) r11)
            java.lang.Object r14 = r8.a
            java.lang.Object r14 = com.google.android.exoplayer2.MediaSourceList.b((java.lang.Object) r14)
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r8 = r8.a(r14)
            java.util.Map r14 = r13.c
            java.lang.Object r11 = r14.get(r11)
            com.google.android.exoplayer2.MediaSourceList$MediaSourceHolder r11 = (com.google.android.exoplayer2.MediaSourceList.MediaSourceHolder) r11
            java.lang.Object r11 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r11)
            com.google.android.exoplayer2.MediaSourceList$MediaSourceHolder r11 = (com.google.android.exoplayer2.MediaSourceList.MediaSourceHolder) r11
            java.util.Set r14 = r13.f
            r14.add(r11)
            java.util.HashMap r14 = r13.e
            java.lang.Object r14 = r14.get(r11)
            com.google.android.exoplayer2.MediaSourceList$MediaSourceAndListener r14 = (com.google.android.exoplayer2.MediaSourceList.MediaSourceAndListener) r14
            if (r14 == 0) goto L_0x005d
            com.google.android.exoplayer2.source.MediaSource r15 = r14.a
            com.google.android.exoplayer2.source.MediaSource$MediaSourceCaller r14 = r14.b
            r15.a((com.google.android.exoplayer2.source.MediaSource.MediaSourceCaller) r14)
        L_0x005d:
            java.util.List r14 = r11.c
            r14.add(r8)
            com.google.android.exoplayer2.source.MaskingMediaSource r14 = r11.a
            com.google.android.exoplayer2.source.MaskingMediaPeriod r1 = r14.a(r8, r12, r9)
            java.util.IdentityHashMap r8 = r13.b
            r8.put(r1, r11)
            r13.d()
            r8 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r10 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r10 == 0) goto L_0x0089
            r8 = -9223372036854775808
            int r10 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r10 == 0) goto L_0x0089
            com.google.android.exoplayer2.source.ClippingMediaPeriod r8 = new com.google.android.exoplayer2.source.ClippingMediaPeriod
            r2 = 1
            r3 = 0
            r0 = r8
            r0.<init>(r1, r2, r3, r5)
            r1 = r8
        L_0x0089:
            r7.a = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.MediaPeriodHolder.<init>(com.google.android.exoplayer2.RendererCapabilities[], long, com.google.android.exoplayer2.trackselection.TrackSelector, com.google.android.exoplayer2.upstream.Allocator, com.google.android.exoplayer2.MediaSourceList, com.google.android.exoplayer2.MediaPeriodInfo, com.google.android.exoplayer2.trackselection.TrackSelectorResult):void");
    }

    private void a(SampleStream[] sampleStreamArr) {
        int i2 = 0;
        while (true) {
            RendererCapabilities[] rendererCapabilitiesArr = this.m;
            if (i2 < rendererCapabilitiesArr.length) {
                if (rendererCapabilitiesArr[i2].a() == 7) {
                    sampleStreamArr[i2] = null;
                }
                i2++;
            } else {
                return;
            }
        }
    }

    private void b(SampleStream[] sampleStreamArr) {
        int i2 = 0;
        while (true) {
            RendererCapabilities[] rendererCapabilitiesArr = this.m;
            if (i2 < rendererCapabilitiesArr.length) {
                if (rendererCapabilitiesArr[i2].a() == 7 && this.j.a(i2)) {
                    sampleStreamArr[i2] = new EmptySampleStream();
                }
                i2++;
            } else {
                return;
            }
        }
    }

    private void f() {
        if (h()) {
            for (int i2 = 0; i2 < this.j.a; i2++) {
                boolean a2 = this.j.a(i2);
                ExoTrackSelection exoTrackSelection = this.j.c[i2];
                if (a2 && exoTrackSelection != null) {
                    exoTrackSelection.c();
                }
            }
        }
    }

    private void g() {
        if (h()) {
            for (int i2 = 0; i2 < this.j.a; i2++) {
                boolean a2 = this.j.a(i2);
                ExoTrackSelection exoTrackSelection = this.j.c[i2];
                if (a2 && exoTrackSelection != null) {
                    exoTrackSelection.d();
                }
            }
        }
    }

    private boolean h() {
        return this.h == null;
    }

    public final long a() {
        return this.f.b + this.k;
    }

    public final long a(TrackSelectorResult trackSelectorResult, long j2) {
        return a(trackSelectorResult, j2, false, new boolean[this.m.length]);
    }

    public final long a(TrackSelectorResult trackSelectorResult, long j2, boolean z, boolean[] zArr) {
        TrackSelectorResult trackSelectorResult2 = trackSelectorResult;
        int i2 = 0;
        while (true) {
            boolean z2 = true;
            if (i2 >= trackSelectorResult2.a) {
                break;
            }
            boolean[] zArr2 = this.l;
            if (z || !trackSelectorResult.a(this.j, i2)) {
                z2 = false;
            }
            zArr2[i2] = z2;
            i2++;
        }
        a(this.c);
        g();
        this.j = trackSelectorResult2;
        f();
        long a2 = this.a.a(trackSelectorResult2.c, this.l, this.c, zArr, j2);
        b(this.c);
        this.e = false;
        int i3 = 0;
        while (true) {
            SampleStream[] sampleStreamArr = this.c;
            if (i3 >= sampleStreamArr.length) {
                return a2;
            }
            if (sampleStreamArr[i3] != null) {
                Assertions.b(trackSelectorResult.a(i3));
                if (this.m[i3].a() != 7) {
                    this.e = true;
                }
            } else {
                Assertions.b(trackSelectorResult2.c[i3] == null);
            }
            i3++;
        }
    }

    public final TrackSelectorResult a(float f2, Timeline timeline) {
        TrackSelectorResult selectTracks = this.n.selectTracks(this.m, this.i, this.f.a, timeline);
        for (ExoTrackSelection exoTrackSelection : selectTracks.c) {
            if (exoTrackSelection != null) {
                exoTrackSelection.a(f2);
            }
        }
        return selectTracks;
    }

    public final void a(long j2) {
        Assertions.b(h());
        if (this.d) {
            this.a.a_(j2 - this.k);
        }
    }

    public final void a(MediaPeriodHolder mediaPeriodHolder) {
        if (mediaPeriodHolder != this.h) {
            g();
            this.h = mediaPeriodHolder;
            f();
        }
    }

    public final void b(long j2) {
        Assertions.b(h());
        this.a.c(j2 - this.k);
    }

    public final boolean b() {
        if (this.d) {
            return !this.e || this.a.d() == Long.MIN_VALUE;
        }
        return false;
    }

    public final long c() {
        if (!this.d) {
            return this.f.b;
        }
        long d2 = this.e ? this.a.d() : Long.MIN_VALUE;
        return d2 == Long.MIN_VALUE ? this.f.e : d2;
    }

    public final long d() {
        if (!this.d) {
            return 0;
        }
        return this.a.e();
    }

    public final void e() {
        g();
        long j2 = this.f.d;
        MediaSourceList mediaSourceList = this.o;
        MediaPeriod mediaPeriod = this.a;
        if (j2 == -9223372036854775807L || j2 == Long.MIN_VALUE) {
            mediaSourceList.a(mediaPeriod);
            return;
        }
        try {
            mediaSourceList.a(((ClippingMediaPeriod) mediaPeriod).a);
        } catch (RuntimeException e2) {
            Log.b("MediaPeriodHolder", "Period release failed.", e2);
        }
    }
}
