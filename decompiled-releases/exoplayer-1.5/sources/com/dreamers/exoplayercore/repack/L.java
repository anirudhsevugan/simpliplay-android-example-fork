package com.dreamers.exoplayercore.repack;

import android.view.View;

final class L implements C0027b {
    private /* synthetic */ F a;

    L(F f) {
        this.a = f;
    }

    public final C0010ai a(int i) {
        F f = this.a;
        int b = f.g.b();
        int i2 = 0;
        C0010ai aiVar = null;
        while (true) {
            if (i2 >= b) {
                break;
            }
            C0010ai a2 = F.a(f.g.c(i2));
            if (a2 != null && !a2.isRemoved() && a2.mPosition == i) {
                if (!f.g.d(a2.itemView)) {
                    aiVar = a2;
                    break;
                }
                aiVar = a2;
            }
            i2++;
        }
        if (aiVar != null && !this.a.g.d(aiVar.itemView)) {
            return aiVar;
        }
        return null;
    }

    public final void a(int i, int i2) {
        this.a.a(i, i2, true);
        this.a.v = true;
        this.a.u.c += i2;
    }

    public final void a(int i, int i2, Object obj) {
        int i3;
        F f = this.a;
        int b = f.g.b();
        int i4 = i2 + i;
        for (int i5 = 0; i5 < b; i5++) {
            View c = f.g.c(i5);
            C0010ai a2 = F.a(c);
            if (a2 != null && !a2.shouldIgnore() && a2.mPosition >= i && a2.mPosition < i4) {
                a2.addFlags(2);
                a2.addChangePayload(obj);
                ((Y) c.getLayoutParams()).c = true;
            }
        }
        C0004ac acVar = f.e;
        for (int size = acVar.c.size() - 1; size >= 0; size--) {
            C0010ai aiVar = (C0010ai) acVar.c.get(size);
            if (aiVar != null && (i3 = aiVar.mPosition) >= i && i3 < i4) {
                aiVar.addFlags(2);
                acVar.b(size);
            }
        }
        this.a.w = true;
    }

    public final void b(int i, int i2) {
        this.a.a(i, i2, false);
        this.a.v = true;
    }

    public final void c(int i, int i2) {
        F f = this.a;
        int b = f.g.b();
        for (int i3 = 0; i3 < b; i3++) {
            C0010ai a2 = F.a(f.g.c(i3));
            if (a2 != null && !a2.shouldIgnore() && a2.mPosition >= i) {
                a2.offsetPosition(i2, false);
                f.u.f = true;
            }
        }
        C0004ac acVar = f.e;
        int size = acVar.c.size();
        for (int i4 = 0; i4 < size; i4++) {
            C0010ai aiVar = (C0010ai) acVar.c.get(i4);
            if (aiVar != null && aiVar.mPosition >= i) {
                aiVar.offsetPosition(i2, true);
            }
        }
        f.requestLayout();
        this.a.v = true;
    }

    public final void d(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        F f = this.a;
        int b = f.g.b();
        int i8 = -1;
        if (i < i2) {
            i5 = i;
            i4 = i2;
            i3 = -1;
        } else {
            i4 = i;
            i5 = i2;
            i3 = 1;
        }
        for (int i9 = 0; i9 < b; i9++) {
            C0010ai a2 = F.a(f.g.c(i9));
            if (a2 != null && a2.mPosition >= i5 && a2.mPosition <= i4) {
                if (a2.mPosition == i) {
                    a2.offsetPosition(i2 - i, false);
                } else {
                    a2.offsetPosition(i3, false);
                }
                f.u.f = true;
            }
        }
        C0004ac acVar = f.e;
        if (i < i2) {
            i7 = i;
            i6 = i2;
        } else {
            i6 = i;
            i7 = i2;
            i8 = 1;
        }
        int size = acVar.c.size();
        for (int i10 = 0; i10 < size; i10++) {
            C0010ai aiVar = (C0010ai) acVar.c.get(i10);
            if (aiVar != null && aiVar.mPosition >= i7 && aiVar.mPosition <= i6) {
                if (aiVar.mPosition == i) {
                    aiVar.offsetPosition(i2 - i, false);
                } else {
                    aiVar.offsetPosition(i8, false);
                }
            }
        }
        f.requestLayout();
        this.a.v = true;
    }
}
