package com.google.android.exoplayer2.text;

import androidx.core.location.LocationRequestCompat;
import com.google.android.exoplayer2.decoder.OutputBuffer;
import com.google.android.exoplayer2.util.Assertions;
import java.util.List;

public abstract class SubtitleOutputBuffer extends OutputBuffer implements Subtitle {
    private Subtitle c;
    private long d;

    public final int a(long j) {
        return ((Subtitle) Assertions.b((Object) this.c)).a(j - this.d);
    }

    public final long a(int i) {
        return ((Subtitle) Assertions.b((Object) this.c)).a(i) + this.d;
    }

    public final void a() {
        super.a();
        this.c = null;
    }

    public final void a(long j, Subtitle subtitle, long j2) {
        this.b = j;
        this.c = subtitle;
        if (j2 == LocationRequestCompat.PASSIVE_INTERVAL) {
            j2 = this.b;
        }
        this.d = j2;
    }

    public final int b() {
        return ((Subtitle) Assertions.b((Object) this.c)).b();
    }

    public final List b(long j) {
        return ((Subtitle) Assertions.b((Object) this.c)).b(j - this.d);
    }
}
