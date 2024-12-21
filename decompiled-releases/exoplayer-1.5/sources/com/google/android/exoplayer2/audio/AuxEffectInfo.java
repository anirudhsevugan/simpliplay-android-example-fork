package com.google.android.exoplayer2.audio;

public final class AuxEffectInfo {
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && Float.compare(0.0f, 0.0f) == 0;
    }

    public final int hashCode() {
        return Float.floatToIntBits(0.0f) + 16337;
    }
}
