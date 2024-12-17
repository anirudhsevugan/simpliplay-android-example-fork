package com.dreamers.exoplayercore.repack;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* renamed from: com.dreamers.exoplayercore.repack.k  reason: case insensitive filesystem */
final class C0088k extends AnimatorListenerAdapter {
    private /* synthetic */ C0010ai a;
    private /* synthetic */ ViewPropertyAnimator b;
    private /* synthetic */ View c;
    private /* synthetic */ C0084g d;

    C0088k(C0084g gVar, C0010ai aiVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
        this.d = gVar;
        this.a = aiVar;
        this.b = viewPropertyAnimator;
        this.c = view;
    }

    public final void onAnimationEnd(Animator animator) {
        this.b.setListener((Animator.AnimatorListener) null);
        this.c.setAlpha(1.0f);
        this.d.f(this.a);
        this.d.f.remove(this.a);
        this.d.c();
    }

    public final void onAnimationStart(Animator animator) {
    }
}
