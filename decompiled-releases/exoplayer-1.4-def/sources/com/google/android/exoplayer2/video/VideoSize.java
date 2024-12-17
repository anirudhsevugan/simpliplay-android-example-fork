package com.google.android.exoplayer2.video;

import com.google.android.exoplayer2.Bundleable;

public final class VideoSize implements Bundleable {
    public static final VideoSize a = new VideoSize();
    public final int b;
    public final int c;
    public final int d;
    public final float e;

    static {
        Bundleable.Creator creator = VideoSize$$Lambda$0.a;
    }

    private VideoSize() {
        this(0, 0, 0, 1.0f);
    }

    public VideoSize(int i, int i2, int i3, float f) {
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof VideoSize) {
            VideoSize videoSize = (VideoSize) obj;
            return this.b == videoSize.b && this.c == videoSize.c && this.d == videoSize.d && this.e == videoSize.e;
        }
    }

    public final int hashCode() {
        return ((((((this.b + 217) * 31) + this.c) * 31) + this.d) * 31) + Float.floatToRawIntBits(this.e);
    }
}
