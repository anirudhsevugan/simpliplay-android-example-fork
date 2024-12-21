package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.source.SequenceableLoader;
import com.google.android.exoplayer2.trackselection.ExoTrackSelection;

public interface MediaPeriod extends SequenceableLoader {

    public interface Callback extends SequenceableLoader.Callback {
        void a(MediaPeriod mediaPeriod);
    }

    long a(long j, SeekParameters seekParameters);

    long a(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j);

    void a();

    void a(long j, boolean z);

    void a(Callback callback, long j);

    void a_(long j);

    long b(long j);

    TrackGroupArray b();

    long c();

    boolean c(long j);

    long d();

    long e();

    boolean f();
}
