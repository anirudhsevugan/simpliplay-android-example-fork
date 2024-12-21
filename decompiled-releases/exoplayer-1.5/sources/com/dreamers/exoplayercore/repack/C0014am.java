package com.dreamers.exoplayercore.repack;

import android.view.View;

/* renamed from: com.dreamers.exoplayercore.repack.am  reason: case insensitive filesystem */
public abstract class C0014am extends Q {
    private boolean a = true;

    public abstract boolean a(C0010ai aiVar);

    public abstract boolean a(C0010ai aiVar, int i, int i2, int i3, int i4);

    public final boolean a(C0010ai aiVar, S s, S s2) {
        int i = s.a;
        int i2 = s.b;
        View view = aiVar.itemView;
        int left = s2 == null ? view.getLeft() : s2.a;
        int top = s2 == null ? view.getTop() : s2.b;
        if (aiVar.isRemoved() || (i == left && i2 == top)) {
            return a(aiVar);
        }
        view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
        return a(aiVar, i, i2, left, top);
    }

    public abstract boolean a(C0010ai aiVar, C0010ai aiVar2, int i, int i2, int i3, int i4);

    public final boolean a(C0010ai aiVar, C0010ai aiVar2, S s, S s2) {
        int i;
        int i2;
        int i3 = s.a;
        int i4 = s.b;
        if (aiVar2.shouldIgnore()) {
            int i5 = s.a;
            i = s.b;
            i2 = i5;
        } else {
            i2 = s2.a;
            i = s2.b;
        }
        return a(aiVar, aiVar2, i3, i4, i2, i);
    }

    public abstract boolean b(C0010ai aiVar);

    public final boolean b(C0010ai aiVar, S s, S s2) {
        if (s == null || (s.a == s2.a && s.b == s2.b)) {
            return b(aiVar);
        }
        return a(aiVar, s.a, s.b, s2.a, s2.b);
    }

    public final boolean c(C0010ai aiVar, S s, S s2) {
        if (s.a == s2.a && s.b == s2.b) {
            f(aiVar);
            return false;
        }
        return a(aiVar, s.a, s.b, s2.a, s2.b);
    }

    public final boolean g(C0010ai aiVar) {
        return !this.a || aiVar.isInvalid();
    }
}
