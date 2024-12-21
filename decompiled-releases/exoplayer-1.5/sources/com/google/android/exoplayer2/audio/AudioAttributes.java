package com.google.android.exoplayer2.audio;

import android.media.AudioAttributes;
import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.util.Util;

public final class AudioAttributes implements Bundleable {
    public static final AudioAttributes a;
    public final int b;
    private int c;
    private int d;
    private int e;
    private android.media.AudioAttributes f;

    public final class Builder {
        int a = 1;
        int b = 1;
    }

    static {
        Builder builder = new Builder();
        a = new AudioAttributes(builder.a, builder.b, (byte) 0);
        Bundleable.Creator creator = AudioAttributes$$Lambda$0.a;
    }

    private AudioAttributes(int i, int i2) {
        this.c = 0;
        this.d = 0;
        this.b = i;
        this.e = i2;
    }

    private /* synthetic */ AudioAttributes(int i, int i2, byte b2) {
        this(i, i2);
    }

    public final android.media.AudioAttributes a() {
        if (this.f == null) {
            AudioAttributes.Builder usage = new AudioAttributes.Builder().setContentType(0).setFlags(0).setUsage(this.b);
            if (Util.a >= 29) {
                usage.setAllowedCapturePolicy(this.e);
            }
            this.f = usage.build();
        }
        return this.f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            AudioAttributes audioAttributes = (AudioAttributes) obj;
            return this.b == audioAttributes.b && this.e == audioAttributes.e;
        }
    }

    public final int hashCode() {
        return ((this.b + 506447) * 31) + this.e;
    }
}
