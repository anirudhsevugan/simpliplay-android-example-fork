package com.google.android.exoplayer2;

import com.dreamers.exoplayercore.repack.bG;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;
import java.util.List;

public final class PlaybackInfo {
    private static final MediaSource.MediaPeriodId t = new MediaSource.MediaPeriodId(new Object());
    public final Timeline a;
    public final MediaSource.MediaPeriodId b;
    public final long c;
    public final long d;
    public final int e;
    public final ExoPlaybackException f;
    public final boolean g;
    public final TrackGroupArray h;
    public final TrackSelectorResult i;
    public final List j;
    public final MediaSource.MediaPeriodId k;
    public final boolean l;
    public final int m;
    public final PlaybackParameters n;
    public final boolean o;
    public final boolean p;
    public volatile long q;
    public volatile long r;
    public volatile long s;

    public PlaybackInfo(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, long j2, long j3, int i2, ExoPlaybackException exoPlaybackException, boolean z, TrackGroupArray trackGroupArray, TrackSelectorResult trackSelectorResult, List list, MediaSource.MediaPeriodId mediaPeriodId2, boolean z2, int i3, PlaybackParameters playbackParameters, long j4, long j5, long j6, boolean z3, boolean z4) {
        this.a = timeline;
        this.b = mediaPeriodId;
        this.c = j2;
        this.d = j3;
        this.e = i2;
        this.f = exoPlaybackException;
        this.g = z;
        this.h = trackGroupArray;
        this.i = trackSelectorResult;
        this.j = list;
        this.k = mediaPeriodId2;
        this.l = z2;
        this.m = i3;
        this.n = playbackParameters;
        this.q = j4;
        this.r = j5;
        this.s = j6;
        this.o = z3;
        this.p = z4;
    }

    public static PlaybackInfo a(TrackSelectorResult trackSelectorResult) {
        Timeline timeline = Timeline.b;
        MediaSource.MediaPeriodId mediaPeriodId = t;
        return new PlaybackInfo(timeline, mediaPeriodId, -9223372036854775807L, 0, 1, (ExoPlaybackException) null, false, TrackGroupArray.a, trackSelectorResult, bG.g(), mediaPeriodId, false, 0, PlaybackParameters.a, 0, 0, 0, false, false);
    }

    public static MediaSource.MediaPeriodId a() {
        return t;
    }

    public final PlaybackInfo a(int i2) {
        Timeline timeline = this.a;
        return new PlaybackInfo(timeline, this.b, this.c, this.d, i2, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.q, this.r, this.s, this.o, this.p);
    }

    public final PlaybackInfo a(ExoPlaybackException exoPlaybackException) {
        Timeline timeline = this.a;
        return new PlaybackInfo(timeline, this.b, this.c, this.d, this.e, exoPlaybackException, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.q, this.r, this.s, this.o, this.p);
    }

    public final PlaybackInfo a(PlaybackParameters playbackParameters) {
        Timeline timeline = this.a;
        return new PlaybackInfo(timeline, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, playbackParameters, this.q, this.r, this.s, this.o, this.p);
    }

    public final PlaybackInfo a(Timeline timeline) {
        return new PlaybackInfo(timeline, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.q, this.r, this.s, this.o, this.p);
    }

    public final PlaybackInfo a(MediaSource.MediaPeriodId mediaPeriodId) {
        Timeline timeline = this.a;
        return new PlaybackInfo(timeline, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, mediaPeriodId, this.l, this.m, this.n, this.q, this.r, this.s, this.o, this.p);
    }

    public final PlaybackInfo a(MediaSource.MediaPeriodId mediaPeriodId, long j2, long j3, long j4, long j5, TrackGroupArray trackGroupArray, TrackSelectorResult trackSelectorResult, List list) {
        long j6 = j2;
        TrackGroupArray trackGroupArray2 = trackGroupArray;
        TrackSelectorResult trackSelectorResult2 = trackSelectorResult;
        List list2 = list;
        Timeline timeline = this.a;
        return new PlaybackInfo(timeline, mediaPeriodId, j3, j4, this.e, this.f, this.g, trackGroupArray2, trackSelectorResult2, list2, this.k, this.l, this.m, this.n, this.q, j5, j6, this.o, this.p);
    }

    public final PlaybackInfo a(boolean z) {
        Timeline timeline = this.a;
        return new PlaybackInfo(timeline, this.b, this.c, this.d, this.e, this.f, z, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.q, this.r, this.s, this.o, this.p);
    }

    public final PlaybackInfo a(boolean z, int i2) {
        Timeline timeline = this.a;
        return new PlaybackInfo(timeline, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, z, i2, this.n, this.q, this.r, this.s, this.o, this.p);
    }

    public final PlaybackInfo b(boolean z) {
        Timeline timeline = this.a;
        return new PlaybackInfo(timeline, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.q, this.r, this.s, z, this.p);
    }
}
