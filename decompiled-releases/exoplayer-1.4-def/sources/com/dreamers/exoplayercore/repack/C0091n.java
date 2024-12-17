package com.dreamers.exoplayercore.repack;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* renamed from: com.dreamers.exoplayercore.repack.n  reason: case insensitive filesystem */
final class C0091n extends AnimatorListenerAdapter {
    private /* synthetic */ C0093p a;
    private /* synthetic */ ViewPropertyAnimator b;
    private /* synthetic */ View c;
    private /* synthetic */ C0084g d;

    C0091n(C0084g gVar, C0093p pVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
        this.d = gVar;
        this.a = pVar;
        this.b = viewPropertyAnimator;
        this.c = view;
    }

    public final void onAnimationEnd(Animator animator) {
        this.b.setListener((Animator.AnimatorListener) null);
        this.c.setAlpha(1.0f);
        this.c.setTranslationX(0.0f);
        this.c.setTranslationY(0.0f);
        this.d.f(this.a.a);
        this.d.g.remove(this.a.a);
        this.d.c();
    }

    public final void onAnimationStart(Animator animator) {
    }
}
