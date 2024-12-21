package com.google.android.exoplayer2.source.hls;

import com.dreamers.exoplayercore.repack.C0000a;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.List;

final class HlsSampleStream implements SampleStream {
    final int a;
    final HlsSampleStreamWrapper b;
    int c = -1;

    public HlsSampleStream(HlsSampleStreamWrapper hlsSampleStreamWrapper, int i) {
        this.b = hlsSampleStreamWrapper;
        this.a = i;
    }

    private boolean d() {
        int i = this.c;
        return (i == -1 || i == -3 || i == -2) ? false : true;
    }

    public final int a(long j) {
        if (!d()) {
            return 0;
        }
        HlsSampleStreamWrapper hlsSampleStreamWrapper = this.b;
        int i = this.c;
        if (hlsSampleStreamWrapper.n()) {
            return 0;
        }
        HlsSampleStreamWrapper.HlsSampleQueue hlsSampleQueue = hlsSampleStreamWrapper.j[i];
        int b2 = hlsSampleQueue.b(j, hlsSampleStreamWrapper.y);
        ArrayList arrayList = hlsSampleStreamWrapper.f;
        HlsMediaChunk hlsMediaChunk = (HlsMediaChunk) (arrayList.isEmpty() ? null : C0000a.a((List) arrayList));
        if (hlsMediaChunk != null && !hlsMediaChunk.o) {
            b2 = Math.min(b2, hlsMediaChunk.a(i) - hlsSampleQueue.e());
        }
        hlsSampleQueue.d(b2);
        return b2;
    }

    public final int a(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i) {
        FormatHolder formatHolder2 = formatHolder;
        DecoderInputBuffer decoderInputBuffer2 = decoderInputBuffer;
        if (this.c == -3) {
            decoderInputBuffer2.a_(4);
            return -4;
        }
        if (d()) {
            HlsSampleStreamWrapper hlsSampleStreamWrapper = this.b;
            int i2 = this.c;
            if (!hlsSampleStreamWrapper.n()) {
                int i3 = 0;
                if (!hlsSampleStreamWrapper.f.isEmpty()) {
                    int i4 = 0;
                    while (true) {
                        boolean z = true;
                        if (i4 >= hlsSampleStreamWrapper.f.size() - 1) {
                            break;
                        }
                        int i5 = ((HlsMediaChunk) hlsSampleStreamWrapper.f.get(i4)).a;
                        int length = hlsSampleStreamWrapper.j.length;
                        int i6 = 0;
                        while (true) {
                            if (i6 < length) {
                                if (hlsSampleStreamWrapper.u[i6] && hlsSampleStreamWrapper.j[i6].f() == i5) {
                                    z = false;
                                    break;
                                }
                                i6++;
                            } else {
                                break;
                            }
                        }
                        if (!z) {
                            break;
                        }
                        i4++;
                    }
                    Util.a((List) hlsSampleStreamWrapper.f, 0, i4);
                    HlsMediaChunk hlsMediaChunk = (HlsMediaChunk) hlsSampleStreamWrapper.f.get(0);
                    Format format = hlsMediaChunk.g;
                    if (!format.equals(hlsSampleStreamWrapper.p)) {
                        hlsSampleStreamWrapper.e.a(hlsSampleStreamWrapper.b, format, hlsMediaChunk.h, hlsMediaChunk.i, hlsMediaChunk.j);
                    }
                    hlsSampleStreamWrapper.p = format;
                }
                if (hlsSampleStreamWrapper.f.isEmpty() || ((HlsMediaChunk) hlsSampleStreamWrapper.f.get(0)).o) {
                    int a2 = hlsSampleStreamWrapper.j[i2].a(formatHolder2, decoderInputBuffer2, i, hlsSampleStreamWrapper.y);
                    if (a2 == -5) {
                        Format format2 = (Format) Assertions.b((Object) formatHolder2.b);
                        if (i2 == hlsSampleStreamWrapper.k) {
                            int f = hlsSampleStreamWrapper.j[i2].f();
                            while (i3 < hlsSampleStreamWrapper.f.size() && ((HlsMediaChunk) hlsSampleStreamWrapper.f.get(i3)).a != f) {
                                i3++;
                            }
                            format2 = format2.a(i3 < hlsSampleStreamWrapper.f.size() ? ((HlsMediaChunk) hlsSampleStreamWrapper.f.get(i3)).g : (Format) Assertions.b((Object) hlsSampleStreamWrapper.o));
                        }
                        formatHolder2.b = format2;
                    }
                    return a2;
                }
            }
        }
        return -3;
    }

    public final boolean a() {
        if (this.c != -3) {
            if (d()) {
                HlsSampleStreamWrapper hlsSampleStreamWrapper = this.b;
                if (!hlsSampleStreamWrapper.n() && hlsSampleStreamWrapper.j[this.c].b(hlsSampleStreamWrapper.y)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final void b() {
        int i = this.c;
        if (i == -2) {
            TrackGroupArray c2 = this.b.c();
            throw new SampleQueueMappingException(c2.b[this.a].b[0].l);
        } else if (i == -1) {
            this.b.h();
        } else if (i != -3) {
            HlsSampleStreamWrapper hlsSampleStreamWrapper = this.b;
            hlsSampleStreamWrapper.h();
            hlsSampleStreamWrapper.j[i].d();
        }
    }

    public final void c() {
        Assertions.a(this.c == -1);
        this.c = this.b.a(this.a);
    }
}
