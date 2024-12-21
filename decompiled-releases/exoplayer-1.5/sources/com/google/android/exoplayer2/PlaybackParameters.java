package com.google.android.exoplayer2;

import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

public final class PlaybackParameters implements Bundleable {
    public static final PlaybackParameters a = new PlaybackParameters();
    public final float b;
    public final float c;
    public final int d;

    static {
        Bundleable.Creator creator = PlaybackParameters$$Lambda$0.a;
    }

    private PlaybackParameters() {
        this(1.0f, 1.0f);
    }

    public PlaybackParameters(float f, float f2) {
        boolean z = true;
        Assertions.a(f > 0.0f);
        Assertions.a(f2 <= 0.0f ? false : z);
        this.b = f;
        this.c = f2;
        this.d = Math.round(f * 1000.0f);
    }

    public final PlaybackParameters a(float f) {
        return new PlaybackParameters(f, this.c);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            PlaybackParameters playbackParameters = (PlaybackParameters) obj;
            return this.b == playbackParameters.b && this.c == playbackParameters.c;
        }
    }

    public final int hashCode() {
        return ((Float.floatToRawIntBits(this.b) + 527) * 31) + Float.floatToRawIntBits(this.c);
    }

    public final String toString() {
        return Util.a("PlaybackParameters(speed=%.2f, pitch=%.2f)", Float.valueOf(this.b), Float.valueOf(this.c));
    }
}
