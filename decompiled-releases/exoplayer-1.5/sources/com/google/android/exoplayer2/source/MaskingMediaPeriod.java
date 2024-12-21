package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.ExoTrackSelection;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

public final class MaskingMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
    public final MediaSource.MediaPeriodId a;
    final long b;
    MediaSource c;
    MediaPeriod d;
    long e = -9223372036854775807L;
    private final Allocator f;
    private MediaPeriod.Callback g;

    public MaskingMediaPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        this.a = mediaPeriodId;
        this.f = allocator;
        this.b = j;
    }

    private long d(long j) {
        long j2 = this.e;
        return j2 != -9223372036854775807L ? j2 : j;
    }

    public final long a(long j, SeekParameters seekParameters) {
        return ((MediaPeriod) Util.a((Object) this.d)).a(j, seekParameters);
    }

    public final long a(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        long j2;
        long j3 = this.e;
        if (j3 == -9223372036854775807L || j != this.b) {
            j2 = j;
        } else {
            this.e = -9223372036854775807L;
            j2 = j3;
        }
        return ((MediaPeriod) Util.a((Object) this.d)).a(exoTrackSelectionArr, zArr, sampleStreamArr, zArr2, j2);
    }

    public final void a() {
        MediaPeriod mediaPeriod = this.d;
        if (mediaPeriod != null) {
            mediaPeriod.a();
            return;
        }
        MediaSource mediaSource = this.c;
        if (mediaSource != null) {
            mediaSource.h();
        }
    }

    public final void a(long j, boolean z) {
        ((MediaPeriod) Util.a((Object) this.d)).a(j, z);
    }

    public final void a(MediaPeriod.Callback callback, long j) {
        this.g = callback;
        MediaPeriod mediaPeriod = this.d;
        if (mediaPeriod != null) {
            mediaPeriod.a((MediaPeriod.Callback) this, d(this.b));
        }
    }

    public final void a(MediaPeriod mediaPeriod) {
        ((MediaPeriod.Callback) Util.a((Object) this.g)).a(this);
    }

    public final void a(MediaSource.MediaPeriodId mediaPeriodId) {
        long d2 = d(this.b);
        MediaPeriod a2 = ((MediaSource) Assertions.b((Object) this.c)).a(mediaPeriodId, this.f, d2);
        this.d = a2;
        if (this.g != null) {
            a2.a((MediaPeriod.Callback) this, d2);
        }
    }

    public final void a(MediaSource mediaSource) {
        Assertions.b(this.c == null);
        this.c = mediaSource;
    }

    public final /* bridge */ /* synthetic */ void a(SequenceableLoader sequenceableLoader) {
        ((MediaPeriod.Callback) Util.a((Object) this.g)).a(this);
    }

    public final void a_(long j) {
        ((MediaPeriod) Util.a((Object) this.d)).a_(j);
    }

    public final long b(long j) {
        return ((MediaPeriod) Util.a((Object) this.d)).b(j);
    }

    public final TrackGroupArray b() {
        return ((MediaPeriod) Util.a((Object) this.d)).b();
    }

    public final long c() {
        return ((MediaPeriod) Util.a((Object) this.d)).c();
    }

    public final boolean c(long j) {
        MediaPeriod mediaPeriod = this.d;
        return mediaPeriod != null && mediaPeriod.c(j);
    }

    public final long d() {
        return ((MediaPeriod) Util.a((Object) this.d)).d();
    }

    public final long e() {
        return ((MediaPeriod) Util.a((Object) this.d)).e();
    }

    public final boolean f() {
        MediaPeriod mediaPeriod = this.d;
        return mediaPeriod != null && mediaPeriod.f();
    }
}
