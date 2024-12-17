package com.google.android.exoplayer2;

import com.google.android.exoplayer2.trackselection.ExoTrackSelection;
import com.google.android.exoplayer2.upstream.Allocator;

public interface LoadControl {
    void a();

    void a(Renderer[] rendererArr, ExoTrackSelection[] exoTrackSelectionArr);

    boolean a(long j, float f);

    boolean a(long j, float f, boolean z, long j2);

    void b();

    void c();

    Allocator d();

    long e();
}
