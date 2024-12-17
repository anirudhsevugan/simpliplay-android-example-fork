package com.google.android.exoplayer2.source.dash;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.metadata.emsg.EventMessageEncoder;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.dash.manifest.EventStream;
import com.google.android.exoplayer2.util.Util;

final class EventSampleStream implements SampleStream {
    EventStream a;
    private final Format b;
    private final EventMessageEncoder c = new EventMessageEncoder();
    private long[] d;
    private boolean e;
    private boolean f;
    private int g;
    private long h = -9223372036854775807L;

    public EventSampleStream(EventStream eventStream, Format format, boolean z) {
        this.b = format;
        this.a = eventStream;
        this.d = eventStream.b;
        a(eventStream, z);
    }

    public final int a(long j) {
        int max = Math.max(this.g, Util.b(this.d, j, true));
        int i = max - this.g;
        this.g = max;
        return i;
    }

    public final int a(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i) {
        if ((i & 2) != 0 || !this.f) {
            formatHolder.b = this.b;
            this.f = true;
            return -5;
        }
        int i2 = this.g;
        if (i2 != this.d.length) {
            this.g = i2 + 1;
            byte[] a2 = this.c.a(this.a.a[i2]);
            decoderInputBuffer.d(a2.length);
            decoderInputBuffer.c.put(a2);
            decoderInputBuffer.e = this.d[i2];
            decoderInputBuffer.a = 1;
            return -4;
        } else if (this.e) {
            return -3;
        } else {
            decoderInputBuffer.a = 4;
            return -4;
        }
    }

    public final void a(EventStream eventStream, boolean z) {
        int i = this.g;
        long j = i == 0 ? -9223372036854775807L : this.d[i - 1];
        this.e = z;
        this.a = eventStream;
        long[] jArr = eventStream.b;
        this.d = jArr;
        long j2 = this.h;
        if (j2 != -9223372036854775807L) {
            b(j2);
        } else if (j != -9223372036854775807L) {
            this.g = Util.b(jArr, j, false);
        }
    }

    public final boolean a() {
        return true;
    }

    public final void b() {
    }

    public final void b(long j) {
        boolean z = true;
        int b2 = Util.b(this.d, j, true);
        this.g = b2;
        if (!this.e || b2 != this.d.length) {
            z = false;
        }
        if (!z) {
            j = -9223372036854775807L;
        }
        this.h = j;
    }
}
