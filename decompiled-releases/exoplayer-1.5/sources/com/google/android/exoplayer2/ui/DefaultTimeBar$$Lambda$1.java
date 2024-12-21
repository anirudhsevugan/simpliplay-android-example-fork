package com.google.android.exoplayer2.ui;

import android.animation.ValueAnimator;

final /* synthetic */ class DefaultTimeBar$$Lambda$1 implements ValueAnimator.AnimatorUpdateListener {
    private final DefaultTimeBar arg$1;

    DefaultTimeBar$$Lambda$1(DefaultTimeBar defaultTimeBar) {
        this.arg$1 = defaultTimeBar;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.arg$1.lambda$new$1$DefaultTimeBar(valueAnimator);
    }
}
