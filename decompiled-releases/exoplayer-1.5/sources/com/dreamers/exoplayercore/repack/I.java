package com.dreamers.exoplayercore.repack;

import android.view.animation.Interpolator;

final class I implements Interpolator {
    I() {
    }

    public final float getInterpolation(float f) {
        float f2 = f - 1.0f;
        return (f2 * f2 * f2 * f2 * f2) + 1.0f;
    }
}
