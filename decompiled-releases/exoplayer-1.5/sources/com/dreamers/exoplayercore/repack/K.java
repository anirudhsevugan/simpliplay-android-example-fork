package com.dreamers.exoplayercore.repack;

import android.view.View;
import android.view.ViewGroup;

final class K implements C0083f {
    private /* synthetic */ F a;

    K(F f) {
        this.a = f;
    }

    public final int a() {
        return this.a.getChildCount();
    }

    public final int a(View view) {
        return this.a.indexOfChild(view);
    }

    public final void a(int i) {
        View childAt = this.a.getChildAt(i);
        if (childAt != null) {
            this.a.c(childAt);
            childAt.clearAnimation();
        }
        this.a.removeViewAt(i);
    }

    public final void a(View view, int i) {
        this.a.addView(view, i);
        F f = this.a;
        C0010ai a2 = F.a(view);
        if (f.k != null && a2 != null) {
            f.k.onViewAttachedToWindow(a2);
        }
    }

    public final void a(View view, int i, ViewGroup.LayoutParams layoutParams) {
        C0010ai a2 = F.a(view);
        if (a2 != null) {
            if (a2.isTmpDetached() || a2.shouldIgnore()) {
                a2.clearTmpDetachFlag();
            } else {
                throw new IllegalArgumentException("Called attach on a child which is not detached: " + a2 + this.a.a());
            }
        }
        this.a.attachViewToParent(view, i, layoutParams);
    }

    public final View b(int i) {
        return this.a.getChildAt(i);
    }

    public final C0010ai b(View view) {
        return F.a(view);
    }

    public final void b() {
        int childCount = this.a.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View b = b(i);
            this.a.c(b);
            b.clearAnimation();
        }
        this.a.removeAllViews();
    }

    public final void c(int i) {
        C0010ai a2;
        View b = b(i);
        if (!(b == null || (a2 = F.a(b)) == null)) {
            if (!a2.isTmpDetached() || a2.shouldIgnore()) {
                a2.addFlags(256);
            } else {
                throw new IllegalArgumentException("called detach on an already detached child " + a2 + this.a.a());
            }
        }
        this.a.detachViewFromParent(i);
    }

    public final void c(View view) {
        C0010ai a2 = F.a(view);
        if (a2 != null) {
            a2.onEnteredHiddenState(this.a);
        }
    }

    public final void d(View view) {
        C0010ai a2 = F.a(view);
        if (a2 != null) {
            a2.onLeftHiddenState(this.a);
        }
    }
}
