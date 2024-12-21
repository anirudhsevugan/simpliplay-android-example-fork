package com.google.android.exoplayer2;

import com.google.android.exoplayer2.PlayerMessage;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.util.MediaClock;

public interface Renderer extends PlayerMessage.Target {

    public interface WakeupListener {
        void a();

        void a(long j);
    }

    int a();

    void a(float f, float f2);

    void a(int i);

    void a(long j);

    void a(long j, long j2);

    void a(RendererConfiguration rendererConfiguration, Format[] formatArr, SampleStream sampleStream, long j, boolean z, boolean z2, long j2, long j3);

    void a(Format[] formatArr, SampleStream sampleStream, long j, long j2);

    RendererCapabilities b();

    int b_();

    MediaClock c();

    void e();

    SampleStream f();

    boolean g();

    long h();

    void i();

    boolean j();

    void k();

    void l();

    void m();

    void n();

    String x();

    boolean y();

    boolean z();
}
