package com.dreamers.exoplayercore.repack;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* renamed from: com.dreamers.exoplayercore.repack.l  reason: case insensitive filesystem */
final class C0089l extends AnimatorListenerAdapter {
    private /* synthetic */ C0010ai a;
    private /* synthetic */ View b;
    private /* synthetic */ ViewPropertyAnimator c;
    private /* synthetic */ C0084g d;

    C0089l(C0084g gVar, C0010ai aiVar, View view, ViewPropertyAnimator viewPropertyAnimator) {
        this.d = gVar;
        this.a = aiVar;
        this.b = view;
        this.c = viewPropertyAnimator;
    }

    public final void onAnimationCancel(Animator animator) {
        this.b.setAlpha(1.0f);
    }

    public final void onAnimationEnd(Animator animator) {
        this.c.setListener((Animator.AnimatorListener) null);
        this.d.f(this.a);
        this.d.d.remove(this.a);
        this.d.c();
    }

    public final void onAnimationStart(Animator animator) {
    }
}
