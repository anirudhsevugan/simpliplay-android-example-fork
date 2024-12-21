package com.dreamers.exoplayercore.repack;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* renamed from: com.dreamers.exoplayercore.repack.m  reason: case insensitive filesystem */
final class C0090m extends AnimatorListenerAdapter {
    private /* synthetic */ C0010ai a;
    private /* synthetic */ int b;
    private /* synthetic */ View c;
    private /* synthetic */ int d;
    private /* synthetic */ ViewPropertyAnimator e;
    private /* synthetic */ C0084g f;

    C0090m(C0084g gVar, C0010ai aiVar, int i, View view, int i2, ViewPropertyAnimator viewPropertyAnimator) {
        this.f = gVar;
        this.a = aiVar;
        this.b = i;
        this.c = view;
        this.d = i2;
        this.e = viewPropertyAnimator;
    }

    public final void onAnimationCancel(Animator animator) {
        if (this.b != 0) {
            this.c.setTranslationX(0.0f);
        }
        if (this.d != 0) {
            this.c.setTranslationY(0.0f);
        }
    }

    public final void onAnimationEnd(Animator animator) {
        this.e.setListener((Animator.AnimatorListener) null);
        this.f.f(this.a);
        this.f.e.remove(this.a);
        this.f.c();
    }

    public final void onAnimationStart(Animator animator) {
    }
}
