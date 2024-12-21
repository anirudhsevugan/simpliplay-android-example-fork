package com.google.android.exoplayer2;

import androidx.core.location.LocationRequestCompat;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MediaClock;

public abstract class BaseRenderer implements Renderer, RendererCapabilities {
    private final int a;
    private final FormatHolder b = new FormatHolder();
    private RendererConfiguration c;
    private int d;
    private int e;
    private SampleStream f;
    private Format[] g;
    private long h;
    private long i = Long.MIN_VALUE;
    private boolean j;
    private boolean k;

    public BaseRenderer(int i2) {
        this.a = i2;
    }

    public final int a() {
        return this.a;
    }

    /* access modifiers changed from: protected */
    public final int a(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i2) {
        int a2 = ((SampleStream) Assertions.b((Object) this.f)).a(formatHolder, decoderInputBuffer, i2);
        if (a2 == -4) {
            if (decoderInputBuffer.c()) {
                this.i = Long.MIN_VALUE;
                return this.j ? -4 : -3;
            }
            decoderInputBuffer.e += this.h;
            this.i = Math.max(this.i, decoderInputBuffer.e);
        } else if (a2 == -5) {
            Format format = (Format) Assertions.b((Object) formatHolder.b);
            if (format.p != LocationRequestCompat.PASSIVE_INTERVAL) {
                Format.Builder a3 = format.a();
                a3.o = format.p + this.h;
                formatHolder.b = a3.a();
            }
        }
        return a2;
    }

    /* access modifiers changed from: protected */
    public final ExoPlaybackException a(Throwable th, Format format) {
        return a(th, format, false);
    }

    /* access modifiers changed from: protected */
    public final ExoPlaybackException a(Throwable th, Format format, boolean z) {
        int i2;
        if (format != null && !this.k) {
            this.k = true;
            try {
                int b2 = RendererCapabilities$$CC.b(a(format));
                this.k = false;
                i2 = b2;
            } catch (ExoPlaybackException e2) {
                this.k = false;
            } catch (Throwable th2) {
                this.k = false;
                throw th2;
            }
            return ExoPlaybackException.a(th, x(), this.d, format, i2, z);
        }
        i2 = 4;
        return ExoPlaybackException.a(th, x(), this.d, format, i2, z);
    }

    public void a(float f2, float f3) {
        Renderer$$CC.a();
    }

    public final void a(int i2) {
        this.d = i2;
    }

    public void a(int i2, Object obj) {
    }

    public final void a(long j2) {
        this.j = false;
        this.i = j2;
        a(j2, false);
    }

    /* access modifiers changed from: protected */
    public void a(long j2, boolean z) {
    }

    public final void a(RendererConfiguration rendererConfiguration, Format[] formatArr, SampleStream sampleStream, long j2, boolean z, boolean z2, long j3, long j4) {
        boolean z3 = z;
        Assertions.b(this.e == 0);
        this.c = rendererConfiguration;
        this.e = 1;
        a(z, z2);
        a(formatArr, sampleStream, j3, j4);
        long j5 = j2;
        a(j2, z);
    }

    /* access modifiers changed from: protected */
    public void a(boolean z, boolean z2) {
    }

    /* access modifiers changed from: protected */
    public void a(Format[] formatArr, long j2, long j3) {
    }

    public final void a(Format[] formatArr, SampleStream sampleStream, long j2, long j3) {
        Assertions.b(!this.j);
        this.f = sampleStream;
        this.i = j3;
        this.g = formatArr;
        this.h = j3;
        a(formatArr, j2, j3);
    }

    /* access modifiers changed from: protected */
    public final int b(long j2) {
        return ((SampleStream) Assertions.b((Object) this.f)).a(j2 - this.h);
    }

    public final RendererCapabilities b() {
        return this;
    }

    public final int b_() {
        return this.e;
    }

    public MediaClock c() {
        return null;
    }

    public final void e() {
        boolean z = true;
        if (this.e != 1) {
            z = false;
        }
        Assertions.b(z);
        this.e = 2;
        p();
    }

    public final SampleStream f() {
        return this.f;
    }

    public final boolean g() {
        return this.i == Long.MIN_VALUE;
    }

    public final long h() {
        return this.i;
    }

    public final void i() {
        this.j = true;
    }

    public final boolean j() {
        return this.j;
    }

    public final void k() {
        ((SampleStream) Assertions.b((Object) this.f)).b();
    }

    public final void l() {
        Assertions.b(this.e == 2);
        this.e = 1;
        q();
    }

    public final void m() {
        boolean z = true;
        if (this.e != 1) {
            z = false;
        }
        Assertions.b(z);
        this.b.a();
        this.e = 0;
        this.f = null;
        this.g = null;
        this.j = false;
        r();
    }

    public final void n() {
        Assertions.b(this.e == 0);
        this.b.a();
        s();
    }

    public int o() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void p() {
    }

    /* access modifiers changed from: protected */
    public void q() {
    }

    /* access modifiers changed from: protected */
    public void r() {
    }

    /* access modifiers changed from: protected */
    public void s() {
    }

    /* access modifiers changed from: protected */
    public final FormatHolder t() {
        this.b.a();
        return this.b;
    }

    /* access modifiers changed from: protected */
    public final Format[] u() {
        return (Format[]) Assertions.b((Object) this.g);
    }

    /* access modifiers changed from: protected */
    public final RendererConfiguration v() {
        return (RendererConfiguration) Assertions.b((Object) this.c);
    }

    /* access modifiers changed from: protected */
    public final boolean w() {
        return g() ? this.j : ((SampleStream) Assertions.b((Object) this.f)).a();
    }
}
