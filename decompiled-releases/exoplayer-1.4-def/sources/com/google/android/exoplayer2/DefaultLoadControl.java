package com.google.android.exoplayer2;

import com.google.android.exoplayer2.trackselection.ExoTrackSelection;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import com.google.appinventor.components.runtime.Component;

public class DefaultLoadControl implements LoadControl {
    private final DefaultAllocator a;
    private final long b;
    private final long c;
    private final long d;
    private final long e;
    private final long f;
    private int g;
    private boolean h;

    public DefaultLoadControl() {
        this(new DefaultAllocator());
    }

    private DefaultLoadControl(DefaultAllocator defaultAllocator) {
        a(2500, 0, "bufferForPlaybackMs", Component.TYPEFACE_DEFAULT);
        a(5000, 0, "bufferForPlaybackAfterRebufferMs", Component.TYPEFACE_DEFAULT);
        a(50000, 2500, "minBufferMs", "bufferForPlaybackMs");
        a(50000, 5000, "minBufferMs", "bufferForPlaybackAfterRebufferMs");
        a(50000, 50000, "maxBufferMs", "minBufferMs");
        a(0, 0, "backBufferDurationMs", Component.TYPEFACE_DEFAULT);
        this.a = defaultAllocator;
        this.b = C.b(50000);
        this.c = C.b(50000);
        this.d = C.b(2500);
        this.e = C.b(5000);
        this.g = 13107200;
        this.f = C.b(0);
    }

    private static void a(int i, int i2, String str, String str2) {
        Assertions.a(i >= i2, (Object) new StringBuilder(String.valueOf(str).length() + 21 + String.valueOf(str2).length()).append(str).append(" cannot be less than ").append(str2).toString());
    }

    private void a(boolean z) {
        this.g = 13107200;
        this.h = false;
        if (z) {
            this.a.c();
        }
    }

    public final void a() {
        a(false);
    }

    public final void a(Renderer[] rendererArr, ExoTrackSelection[] exoTrackSelectionArr) {
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = 13107200;
            if (i < rendererArr.length) {
                if (exoTrackSelectionArr[i] != null) {
                    switch (rendererArr[i].a()) {
                        case 0:
                            i3 = 144310272;
                            break;
                        case 1:
                            break;
                        case 2:
                            i3 = 131072000;
                            break;
                        case 3:
                        case 5:
                        case 6:
                            i3 = 131072;
                            break;
                        case 7:
                            i3 = 0;
                            break;
                        default:
                            throw new IllegalArgumentException();
                    }
                    i2 += i3;
                }
                i++;
            } else {
                int max = Math.max(13107200, i2);
                this.g = max;
                this.a.a(max);
                return;
            }
        }
    }

    public final boolean a(long j, float f2) {
        boolean z = this.a.d() >= this.g;
        long j2 = this.b;
        if (f2 > 1.0f) {
            j2 = Math.min(Util.a(j2, f2), this.c);
        }
        if (j < Math.max(j2, 500000)) {
            boolean z2 = !z;
            this.h = z2;
            if (!z2 && j < 500000) {
                Log.c("DefaultLoadControl", "Target buffer size reached with less than 500ms of buffered media data.");
            }
        } else if (j >= this.c || z) {
            this.h = false;
        }
        return this.h;
    }

    public final boolean a(long j, float f2, boolean z, long j2) {
        long b2 = Util.b(j, f2);
        long j3 = z ? this.e : this.d;
        if (j2 != -9223372036854775807L) {
            j3 = Math.min(j2 / 2, j3);
        }
        return j3 <= 0 || b2 >= j3 || this.a.d() >= this.g;
    }

    public final void b() {
        a(true);
    }

    public final void c() {
        a(true);
    }

    public final Allocator d() {
        return this.a;
    }

    public final long e() {
        return this.f;
    }
}
