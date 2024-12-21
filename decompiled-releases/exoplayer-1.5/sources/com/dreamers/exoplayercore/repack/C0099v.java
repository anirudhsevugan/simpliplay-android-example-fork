package com.dreamers.exoplayercore.repack;

import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentTransaction;
import com.google.appinventor.components.runtime.util.ScreenDensityUtil;

/* renamed from: com.dreamers.exoplayercore.repack.v  reason: case insensitive filesystem */
public final class C0099v extends U {
    private int l;
    private C0102y m;
    private D n;
    private boolean o;
    private boolean p;
    private int q;
    private int r;
    private C0103z s;
    private C0100w t;
    private final C0101x u;
    private int v;

    public C0099v() {
        this((byte) 0);
    }

    private C0099v(byte b) {
        this.l = 1;
        this.o = false;
        this.p = true;
        this.q = -1;
        this.r = Integer.MIN_VALUE;
        this.s = null;
        this.t = new C0100w();
        this.u = new C0101x();
        this.v = 2;
        a((String) null);
        if (1 != this.l || this.n == null) {
            E e = new E(this);
            this.n = e;
            this.t.a = e;
            this.l = 1;
            h();
        }
        a((String) null);
    }

    private int a(int i, C0004ac acVar, C0008ag agVar, boolean z) {
        int c;
        int c2 = this.n.c() - i;
        if (c2 <= 0) {
            return 0;
        }
        int i2 = -d(-c2, acVar, agVar);
        int i3 = i + i2;
        if (!z || (c = this.n.c() - i3) <= 0) {
            return i2;
        }
        this.n.a(c);
        return c + i2;
    }

    private int a(C0004ac acVar, C0102y yVar, C0008ag agVar, boolean z) {
        int i = yVar.c;
        if (yVar.g != Integer.MIN_VALUE) {
            if (yVar.c < 0) {
                yVar.g += yVar.c;
            }
            a(acVar, yVar);
        }
        int i2 = yVar.c + yVar.h;
        C0101x xVar = this.u;
        while (true) {
            if ((!yVar.k && i2 <= 0) || !yVar.a(agVar)) {
                break;
            }
            xVar.a();
            a(acVar, yVar, xVar);
            if (xVar.b) {
                break;
            }
            yVar.b += xVar.a * yVar.f;
            if (!xVar.c || this.m.j != null || !agVar.g) {
                yVar.c -= xVar.a;
                i2 -= xVar.a;
            }
            if (yVar.g != Integer.MIN_VALUE) {
                yVar.g += xVar.a;
                if (yVar.c < 0) {
                    yVar.g += yVar.c;
                }
                a(acVar, yVar);
            }
            if (z && xVar.d) {
                break;
            }
        }
        return i - yVar.c;
    }

    private View a(int i, int i2, boolean z) {
        q();
        return (this.l == 0 ? this.c : this.d).a(i, i2, z ? 24579 : ScreenDensityUtil.DEFAULT_NORMAL_SHORT_DIMENSION, ScreenDensityUtil.DEFAULT_NORMAL_SHORT_DIMENSION);
    }

    private View a(boolean z) {
        int i;
        int i2;
        if (this.o) {
            i = i() - 1;
            i2 = -1;
        } else {
            i = 0;
            i2 = i();
        }
        return a(i, i2, z);
    }

    private void a(int i, int i2, boolean z, C0008ag agVar) {
        int i3;
        this.m.k = r();
        this.m.h = h(agVar);
        this.m.f = i;
        int i4 = -1;
        if (i == 1) {
            this.m.h += this.n.f();
            View t2 = t();
            C0102y yVar = this.m;
            if (!this.o) {
                i4 = 1;
            }
            yVar.e = i4;
            this.m.d = e(t2) + this.m.e;
            this.m.b = this.n.b(t2);
            i3 = this.n.b(t2) - this.n.c();
        } else {
            View s2 = s();
            this.m.h += this.n.b();
            C0102y yVar2 = this.m;
            if (this.o) {
                i4 = 1;
            }
            yVar2.e = i4;
            this.m.d = e(s2) + this.m.e;
            this.m.b = this.n.a(s2);
            i3 = (-this.n.a(s2)) + this.n.b();
        }
        this.m.c = i2;
        if (z) {
            this.m.c -= i3;
        }
        this.m.g = i3;
    }

    private void a(C0004ac acVar, int i, int i2) {
        if (i != i2) {
            if (i2 > i) {
                for (int i3 = i2 - 1; i3 >= i; i3--) {
                    a(i3, acVar);
                }
                return;
            }
            while (i > i2) {
                a(i, acVar);
                i--;
            }
        }
    }

    private void a(C0004ac acVar, C0102y yVar) {
        if (yVar.a && !yVar.k) {
            int i = yVar.f;
            int i2 = yVar.g;
            if (i == -1) {
                int i3 = i();
                if (i2 >= 0) {
                    int d = this.n.d() - i2;
                    if (this.o) {
                        for (int i4 = 0; i4 < i3; i4++) {
                            View b = b(i4);
                            if (this.n.a(b) < d || this.n.d(b) < d) {
                                a(acVar, 0, i4);
                                return;
                            }
                        }
                        return;
                    }
                    int i5 = i3 - 1;
                    for (int i6 = i5; i6 >= 0; i6--) {
                        View b2 = b(i6);
                        if (this.n.a(b2) < d || this.n.d(b2) < d) {
                            a(acVar, i5, i6);
                            return;
                        }
                    }
                }
            } else if (i2 >= 0) {
                int i7 = i();
                if (this.o) {
                    int i8 = i7 - 1;
                    for (int i9 = i8; i9 >= 0; i9--) {
                        View b3 = b(i9);
                        if (this.n.b(b3) > i2 || this.n.c(b3) > i2) {
                            a(acVar, i8, i9);
                            return;
                        }
                    }
                    return;
                }
                for (int i10 = 0; i10 < i7; i10++) {
                    View b4 = b(i10);
                    if (this.n.b(b4) > i2 || this.n.c(b4) > i2) {
                        a(acVar, 0, i10);
                        return;
                    }
                }
            }
        }
    }

    private void a(C0004ac acVar, C0102y yVar, C0101x xVar) {
        int i;
        int i2;
        int i3;
        int i4;
        View a = yVar.a(acVar);
        if (a == null) {
            xVar.b = true;
            return;
        }
        Y y = (Y) a.getLayoutParams();
        boolean z = false;
        if (yVar.j == null) {
            boolean z2 = this.o;
            if (yVar.f == -1) {
                z = true;
            }
            if (z2 == z) {
                c(a);
            } else {
                d(a);
            }
        } else {
            boolean z3 = this.o;
            if (yVar.f == -1) {
                z = true;
            }
            if (z3 == z) {
                a(a);
            } else {
                b(a);
            }
        }
        f(a);
        xVar.a = this.n.e(a);
        if (this.l == 1) {
            if (p()) {
                i4 = this.j - l();
                i3 = i4 - this.n.f(a);
            } else {
                i3 = j();
                i4 = this.n.f(a) + i3;
            }
            if (yVar.f == -1) {
                i2 = yVar.b;
                i = yVar.b - xVar.a;
            } else {
                int i5 = yVar.b;
                i2 = yVar.b + xVar.a;
                i = i5;
            }
        } else {
            int k = k();
            int f = this.n.f(a) + k;
            if (yVar.f == -1) {
                int i6 = yVar.b;
                int i7 = f;
                i3 = yVar.b - xVar.a;
                i = k;
                i4 = i6;
                i2 = i7;
            } else {
                int i8 = yVar.b;
                int i9 = k;
                i4 = yVar.b + xVar.a;
                i = i9;
                int i10 = i8;
                i2 = f;
                i3 = i10;
            }
        }
        a(a, i3, i, i4, i2);
        if (y.a.isRemoved() || y.a.isUpdated()) {
            xVar.c = true;
        }
        xVar.d = a.hasFocusable();
    }

    private void a(C0100w wVar) {
        d(wVar.b, wVar.c);
    }

    private int b(int i, C0004ac acVar, C0008ag agVar, boolean z) {
        int b;
        int b2 = i - this.n.b();
        if (b2 <= 0) {
            return 0;
        }
        int i2 = -d(b2, acVar, agVar);
        int i3 = i + i2;
        if (!z || (b = i3 - this.n.b()) <= 0) {
            return i2;
        }
        this.n.a(-b);
        return i2 - b;
    }

    private View b(int i, int i2, int i3) {
        q();
        int b = this.n.b();
        int c = this.n.c();
        int i4 = i2 > i ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i != i2) {
            View b2 = b(i);
            int e = e(b2);
            if (e >= 0 && e < i3) {
                if (((Y) b2.getLayoutParams()).a.isRemoved()) {
                    if (view2 == null) {
                        view2 = b2;
                    }
                } else if (this.n.a(b2) < c && this.n.b(b2) >= b) {
                    return b2;
                } else {
                    if (view == null) {
                        view = b2;
                    }
                }
            }
            i += i4;
        }
        return view != null ? view : view2;
    }

    private View b(boolean z) {
        int i;
        int i2;
        if (this.o) {
            i = 0;
            i2 = i();
        } else {
            i = i() - 1;
            i2 = -1;
        }
        return a(i, i2, z);
    }

    private void b(C0100w wVar) {
        e(wVar.b, wVar.c);
    }

    private int d(int i, C0004ac acVar, C0008ag agVar) {
        if (i() == 0 || i == 0) {
            return 0;
        }
        this.m.a = true;
        q();
        int i2 = i > 0 ? 1 : -1;
        int abs = Math.abs(i);
        a(i2, abs, true, agVar);
        int a = this.m.g + a(acVar, this.m, agVar, false);
        if (a < 0) {
            return 0;
        }
        if (abs > a) {
            i = i2 * a;
        }
        this.n.a(-i);
        this.m.i = i;
        return i;
    }

    private void d(int i, int i2) {
        this.m.c = this.n.c() - i2;
        this.m.e = this.o ? -1 : 1;
        this.m.d = i;
        this.m.f = 1;
        this.m.b = i2;
        this.m.g = Integer.MIN_VALUE;
    }

    private void e(int i, int i2) {
        this.m.c = i2 - this.n.b();
        this.m.d = i;
        this.m.e = this.o ? 1 : -1;
        this.m.f = -1;
        this.m.b = i2;
        this.m.g = Integer.MIN_VALUE;
    }

    private View f(int i, int i2) {
        int i3;
        int i4;
        q();
        if ((i2 > i ? 1 : i2 < i ? (char) 65535 : 0) == 0) {
            return b(i);
        }
        if (this.n.a(b(i)) < this.n.b()) {
            i4 = 16644;
            i3 = 16388;
        } else {
            i4 = 4161;
            i3 = FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
        }
        return (this.l == 0 ? this.c : this.d).a(i, i2, i4, i3);
    }

    private int h(C0008ag agVar) {
        if (agVar.a != -1) {
            return this.n.e();
        }
        return 0;
    }

    private int i(C0008ag agVar) {
        if (i() == 0) {
            return 0;
        }
        q();
        return C0013al.a(agVar, this.n, a(!this.p), b(!this.p), this, this.p, this.o);
    }

    private int j(C0008ag agVar) {
        if (i() == 0) {
            return 0;
        }
        q();
        return C0013al.a(agVar, this.n, a(!this.p), b(!this.p), this, this.p);
    }

    private int k(C0008ag agVar) {
        if (i() == 0) {
            return 0;
        }
        q();
        return C0013al.b(agVar, this.n, a(!this.p), b(!this.p), this, this.p);
    }

    private View l(C0008ag agVar) {
        return b(i() - 1, -1, agVar.a());
    }

    private void o() {
        boolean z = true;
        if (this.l == 1 || !p()) {
            z = false;
        }
        this.o = z;
    }

    private boolean p() {
        return ViewCompat.getLayoutDirection(this.b) == 1;
    }

    private void q() {
        if (this.m == null) {
            this.m = new C0102y();
        }
    }

    private boolean r() {
        return this.n.g() == 0 && this.n.d() == 0;
    }

    private View s() {
        return b(this.o ? i() - 1 : 0);
    }

    private View t() {
        return b(this.o ? 0 : i() - 1);
    }

    private View u() {
        return f(0, i());
    }

    private View v() {
        return f(i() - 1, -1);
    }

    public final int a(int i, C0004ac acVar, C0008ag agVar) {
        if (this.l == 1) {
            return 0;
        }
        return d(i, acVar, agVar);
    }

    public final View a(int i) {
        int i2 = i();
        if (i2 == 0) {
            return null;
        }
        int e = i - e(b(0));
        if (e >= 0 && e < i2) {
            View b = b(e);
            if (e(b) == i) {
                return b;
            }
        }
        return super.a(i);
    }

    public final void a(int i, int i2, C0008ag agVar, X x) {
        if (this.l != 0) {
            i = i2;
        }
        if (i() != 0 && i != 0) {
            q();
            a(i > 0 ? 1 : -1, Math.abs(i), true, agVar);
            C0102y yVar = this.m;
            int i3 = yVar.d;
            if (i3 >= 0 && i3 < agVar.a()) {
                x.a(i3, Math.max(0, yVar.g));
            }
        }
    }

    public final void a(int i, X x) {
        int i2;
        boolean z;
        C0103z zVar = this.s;
        int i3 = -1;
        if (zVar == null || !zVar.a()) {
            o();
            z = this.o;
            i2 = this.q;
            if (i2 == -1) {
                i2 = z ? i - 1 : 0;
            }
        } else {
            z = this.s.c;
            i2 = this.s.a;
        }
        if (!z) {
            i3 = 1;
        }
        for (int i4 = 0; i4 < this.v && i2 >= 0 && i2 < i; i4++) {
            x.a(i2, 0);
            i2 += i3;
        }
    }

    public final void a(Parcelable parcelable) {
        if (parcelable instanceof C0103z) {
            this.s = (C0103z) parcelable;
            h();
        }
    }

    public final void a(AccessibilityEvent accessibilityEvent) {
        super.a(accessibilityEvent);
        if (i() > 0) {
            View a = a(0, i(), false);
            int i = -1;
            accessibilityEvent.setFromIndex(a == null ? -1 : e(a));
            View a2 = a(i() - 1, -1, false);
            if (a2 != null) {
                i = e(a2);
            }
            accessibilityEvent.setToIndex(i);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:119:0x021f  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0178  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.dreamers.exoplayercore.repack.C0004ac r17, com.dreamers.exoplayercore.repack.C0008ag r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            com.dreamers.exoplayercore.repack.z r3 = r0.s
            r4 = -1
            if (r3 != 0) goto L_0x000f
            int r3 = r0.q
            if (r3 == r4) goto L_0x0019
        L_0x000f:
            int r3 = r18.a()
            if (r3 != 0) goto L_0x0019
            r16.c((com.dreamers.exoplayercore.repack.C0004ac) r17)
            return
        L_0x0019:
            com.dreamers.exoplayercore.repack.z r3 = r0.s
            if (r3 == 0) goto L_0x0029
            boolean r3 = r3.a()
            if (r3 == 0) goto L_0x0029
            com.dreamers.exoplayercore.repack.z r3 = r0.s
            int r3 = r3.a
            r0.q = r3
        L_0x0029:
            r16.q()
            com.dreamers.exoplayercore.repack.y r3 = r0.m
            r5 = 0
            r3.a = r5
            r16.o()
            android.view.View r3 = r16.n()
            com.dreamers.exoplayercore.repack.w r6 = r0.t
            boolean r6 = r6.e
            r7 = -2147483648(0xffffffff80000000, float:-0.0)
            r8 = 1
            if (r6 == 0) goto L_0x0073
            int r6 = r0.q
            if (r6 != r4) goto L_0x0073
            com.dreamers.exoplayercore.repack.z r6 = r0.s
            if (r6 == 0) goto L_0x004a
            goto L_0x0073
        L_0x004a:
            if (r3 == 0) goto L_0x0228
            com.dreamers.exoplayercore.repack.D r6 = r0.n
            int r6 = r6.a((android.view.View) r3)
            com.dreamers.exoplayercore.repack.D r9 = r0.n
            int r9 = r9.c()
            if (r6 >= r9) goto L_0x0068
            com.dreamers.exoplayercore.repack.D r6 = r0.n
            int r6 = r6.b(r3)
            com.dreamers.exoplayercore.repack.D r9 = r0.n
            int r9 = r9.b()
            if (r6 > r9) goto L_0x0228
        L_0x0068:
            com.dreamers.exoplayercore.repack.w r6 = r0.t
            int r9 = e((android.view.View) r3)
            r6.a(r3, r9)
            goto L_0x0228
        L_0x0073:
            com.dreamers.exoplayercore.repack.w r3 = r0.t
            r3.a()
            com.dreamers.exoplayercore.repack.w r3 = r0.t
            boolean r6 = r0.o
            r3.d = r6
            com.dreamers.exoplayercore.repack.w r3 = r0.t
            boolean r6 = r2.g
            if (r6 != 0) goto L_0x0175
            int r6 = r0.q
            if (r6 != r4) goto L_0x008a
            goto L_0x0175
        L_0x008a:
            if (r6 < 0) goto L_0x0171
            int r9 = r18.a()
            if (r6 < r9) goto L_0x0094
            goto L_0x0171
        L_0x0094:
            int r6 = r0.q
            r3.b = r6
            com.dreamers.exoplayercore.repack.z r6 = r0.s
            if (r6 == 0) goto L_0x00c6
            boolean r6 = r6.a()
            if (r6 == 0) goto L_0x00c6
            com.dreamers.exoplayercore.repack.z r6 = r0.s
            boolean r6 = r6.c
            r3.d = r6
            boolean r6 = r3.d
            if (r6 == 0) goto L_0x00b9
            com.dreamers.exoplayercore.repack.D r6 = r0.n
            int r6 = r6.c()
            com.dreamers.exoplayercore.repack.z r9 = r0.s
            int r9 = r9.b
            int r6 = r6 - r9
            goto L_0x016d
        L_0x00b9:
            com.dreamers.exoplayercore.repack.D r6 = r0.n
            int r6 = r6.b()
            com.dreamers.exoplayercore.repack.z r9 = r0.s
            int r9 = r9.b
            int r6 = r6 + r9
            goto L_0x016d
        L_0x00c6:
            int r6 = r0.r
            if (r6 != r7) goto L_0x0152
            int r6 = r0.q
            android.view.View r6 = r0.a((int) r6)
            if (r6 == 0) goto L_0x0133
            com.dreamers.exoplayercore.repack.D r9 = r0.n
            int r9 = r9.e(r6)
            com.dreamers.exoplayercore.repack.D r10 = r0.n
            int r10 = r10.e()
            if (r9 <= r10) goto L_0x00e5
        L_0x00e0:
            r3.b()
            goto L_0x016f
        L_0x00e5:
            com.dreamers.exoplayercore.repack.D r9 = r0.n
            int r9 = r9.a((android.view.View) r6)
            com.dreamers.exoplayercore.repack.D r10 = r0.n
            int r10 = r10.b()
            int r9 = r9 - r10
            if (r9 >= 0) goto L_0x0100
            com.dreamers.exoplayercore.repack.D r6 = r0.n
            int r6 = r6.b()
            r3.c = r6
            r3.d = r5
            goto L_0x016f
        L_0x0100:
            com.dreamers.exoplayercore.repack.D r9 = r0.n
            int r9 = r9.c()
            com.dreamers.exoplayercore.repack.D r10 = r0.n
            int r10 = r10.b(r6)
            int r9 = r9 - r10
            if (r9 >= 0) goto L_0x011a
            com.dreamers.exoplayercore.repack.D r6 = r0.n
            int r6 = r6.c()
            r3.c = r6
            r3.d = r8
            goto L_0x016f
        L_0x011a:
            boolean r9 = r3.d
            if (r9 == 0) goto L_0x012c
            com.dreamers.exoplayercore.repack.D r9 = r0.n
            int r6 = r9.b(r6)
            com.dreamers.exoplayercore.repack.D r9 = r0.n
            int r9 = r9.a()
            int r6 = r6 + r9
            goto L_0x016d
        L_0x012c:
            com.dreamers.exoplayercore.repack.D r9 = r0.n
            int r6 = r9.a((android.view.View) r6)
            goto L_0x016d
        L_0x0133:
            int r6 = r16.i()
            if (r6 <= 0) goto L_0x00e0
            android.view.View r6 = r0.b((int) r5)
            int r6 = e((android.view.View) r6)
            int r9 = r0.q
            if (r9 >= r6) goto L_0x0147
            r6 = 1
            goto L_0x0148
        L_0x0147:
            r6 = 0
        L_0x0148:
            boolean r9 = r0.o
            if (r6 != r9) goto L_0x014e
            r6 = 1
            goto L_0x014f
        L_0x014e:
            r6 = 0
        L_0x014f:
            r3.d = r6
            goto L_0x00e0
        L_0x0152:
            boolean r6 = r0.o
            r3.d = r6
            boolean r6 = r0.o
            if (r6 == 0) goto L_0x0164
            com.dreamers.exoplayercore.repack.D r6 = r0.n
            int r6 = r6.c()
            int r9 = r0.r
            int r6 = r6 - r9
            goto L_0x016d
        L_0x0164:
            com.dreamers.exoplayercore.repack.D r6 = r0.n
            int r6 = r6.b()
            int r9 = r0.r
            int r6 = r6 + r9
        L_0x016d:
            r3.c = r6
        L_0x016f:
            r6 = 1
            goto L_0x0176
        L_0x0171:
            r0.q = r4
            r0.r = r7
        L_0x0175:
            r6 = 0
        L_0x0176:
            if (r6 != 0) goto L_0x0224
            int r6 = r16.i()
            if (r6 == 0) goto L_0x021c
            android.view.View r6 = r16.n()
            if (r6 == 0) goto L_0x01b5
            android.view.ViewGroup$LayoutParams r9 = r6.getLayoutParams()
            com.dreamers.exoplayercore.repack.Y r9 = (com.dreamers.exoplayercore.repack.Y) r9
            com.dreamers.exoplayercore.repack.ai r10 = r9.a
            boolean r10 = r10.isRemoved()
            if (r10 != 0) goto L_0x01a8
            com.dreamers.exoplayercore.repack.ai r10 = r9.a
            int r10 = r10.getLayoutPosition()
            if (r10 < 0) goto L_0x01a8
            com.dreamers.exoplayercore.repack.ai r9 = r9.a
            int r9 = r9.getLayoutPosition()
            int r10 = r18.a()
            if (r9 >= r10) goto L_0x01a8
            r9 = 1
            goto L_0x01a9
        L_0x01a8:
            r9 = 0
        L_0x01a9:
            if (r9 == 0) goto L_0x01b5
            int r9 = e((android.view.View) r6)
            r3.a(r6, r9)
        L_0x01b2:
            r6 = 1
            goto L_0x021d
        L_0x01b5:
            boolean r6 = r3.d
            if (r6 == 0) goto L_0x01be
            boolean r6 = r0.o
            if (r6 != 0) goto L_0x01c7
            goto L_0x01c2
        L_0x01be:
            boolean r6 = r0.o
            if (r6 == 0) goto L_0x01c7
        L_0x01c2:
            android.view.View r6 = r0.l(r2)
            goto L_0x01d3
        L_0x01c7:
            int r6 = r16.i()
            int r9 = r18.a()
            android.view.View r6 = r0.b((int) r5, (int) r6, (int) r9)
        L_0x01d3:
            if (r6 == 0) goto L_0x021c
            int r9 = e((android.view.View) r6)
            r3.b(r6, r9)
            boolean r9 = r2.g
            if (r9 != 0) goto L_0x01b2
            boolean r9 = r16.g()
            if (r9 == 0) goto L_0x01b2
            com.dreamers.exoplayercore.repack.D r9 = r0.n
            int r9 = r9.a((android.view.View) r6)
            com.dreamers.exoplayercore.repack.D r10 = r0.n
            int r10 = r10.c()
            if (r9 >= r10) goto L_0x0205
            com.dreamers.exoplayercore.repack.D r9 = r0.n
            int r6 = r9.b(r6)
            com.dreamers.exoplayercore.repack.D r9 = r0.n
            int r9 = r9.b()
            if (r6 >= r9) goto L_0x0203
            goto L_0x0205
        L_0x0203:
            r6 = 0
            goto L_0x0206
        L_0x0205:
            r6 = 1
        L_0x0206:
            if (r6 == 0) goto L_0x01b2
            boolean r6 = r3.d
            if (r6 == 0) goto L_0x0213
            com.dreamers.exoplayercore.repack.D r6 = r0.n
            int r6 = r6.c()
            goto L_0x0219
        L_0x0213:
            com.dreamers.exoplayercore.repack.D r6 = r0.n
            int r6 = r6.b()
        L_0x0219:
            r3.c = r6
            goto L_0x01b2
        L_0x021c:
            r6 = 0
        L_0x021d:
            if (r6 != 0) goto L_0x0224
            r3.b()
            r3.b = r5
        L_0x0224:
            com.dreamers.exoplayercore.repack.w r3 = r0.t
            r3.e = r8
        L_0x0228:
            int r3 = r0.h(r2)
            com.dreamers.exoplayercore.repack.y r6 = r0.m
            int r6 = r6.i
            if (r6 < 0) goto L_0x0235
            r6 = r3
            r3 = 0
            goto L_0x0236
        L_0x0235:
            r6 = 0
        L_0x0236:
            com.dreamers.exoplayercore.repack.D r9 = r0.n
            int r9 = r9.b()
            int r3 = r3 + r9
            com.dreamers.exoplayercore.repack.D r9 = r0.n
            int r9 = r9.f()
            int r6 = r6 + r9
            boolean r9 = r2.g
            if (r9 == 0) goto L_0x027f
            int r9 = r0.q
            if (r9 == r4) goto L_0x027f
            int r10 = r0.r
            if (r10 == r7) goto L_0x027f
            android.view.View r7 = r0.a((int) r9)
            if (r7 == 0) goto L_0x027f
            boolean r9 = r0.o
            if (r9 == 0) goto L_0x026a
            com.dreamers.exoplayercore.repack.D r9 = r0.n
            int r9 = r9.c()
            com.dreamers.exoplayercore.repack.D r10 = r0.n
            int r7 = r10.b(r7)
            int r9 = r9 - r7
            int r7 = r0.r
            goto L_0x0279
        L_0x026a:
            com.dreamers.exoplayercore.repack.D r9 = r0.n
            int r7 = r9.a((android.view.View) r7)
            com.dreamers.exoplayercore.repack.D r9 = r0.n
            int r9 = r9.b()
            int r7 = r7 - r9
            int r9 = r0.r
        L_0x0279:
            int r9 = r9 - r7
            if (r9 <= 0) goto L_0x027e
            int r3 = r3 + r9
            goto L_0x027f
        L_0x027e:
            int r6 = r6 - r9
        L_0x027f:
            r16.a((com.dreamers.exoplayercore.repack.C0004ac) r17)
            com.dreamers.exoplayercore.repack.y r7 = r0.m
            boolean r9 = r16.r()
            r7.k = r9
            com.dreamers.exoplayercore.repack.w r7 = r0.t
            boolean r7 = r7.d
            if (r7 == 0) goto L_0x02e9
            com.dreamers.exoplayercore.repack.w r7 = r0.t
            r0.b((com.dreamers.exoplayercore.repack.C0100w) r7)
            com.dreamers.exoplayercore.repack.y r7 = r0.m
            r7.h = r3
            com.dreamers.exoplayercore.repack.y r3 = r0.m
            r0.a((com.dreamers.exoplayercore.repack.C0004ac) r1, (com.dreamers.exoplayercore.repack.C0102y) r3, (com.dreamers.exoplayercore.repack.C0008ag) r2, (boolean) r5)
            com.dreamers.exoplayercore.repack.y r3 = r0.m
            int r3 = r3.b
            com.dreamers.exoplayercore.repack.y r7 = r0.m
            int r7 = r7.d
            com.dreamers.exoplayercore.repack.y r9 = r0.m
            int r9 = r9.c
            if (r9 <= 0) goto L_0x02b1
            com.dreamers.exoplayercore.repack.y r9 = r0.m
            int r9 = r9.c
            int r6 = r6 + r9
        L_0x02b1:
            com.dreamers.exoplayercore.repack.w r9 = r0.t
            r0.a((com.dreamers.exoplayercore.repack.C0100w) r9)
            com.dreamers.exoplayercore.repack.y r9 = r0.m
            r9.h = r6
            com.dreamers.exoplayercore.repack.y r6 = r0.m
            int r9 = r6.d
            com.dreamers.exoplayercore.repack.y r10 = r0.m
            int r10 = r10.e
            int r9 = r9 + r10
            r6.d = r9
            com.dreamers.exoplayercore.repack.y r6 = r0.m
            r0.a((com.dreamers.exoplayercore.repack.C0004ac) r1, (com.dreamers.exoplayercore.repack.C0102y) r6, (com.dreamers.exoplayercore.repack.C0008ag) r2, (boolean) r5)
            com.dreamers.exoplayercore.repack.y r6 = r0.m
            int r6 = r6.b
            com.dreamers.exoplayercore.repack.y r9 = r0.m
            int r9 = r9.c
            if (r9 <= 0) goto L_0x0341
            com.dreamers.exoplayercore.repack.y r9 = r0.m
            int r9 = r9.c
            r0.e(r7, r3)
            com.dreamers.exoplayercore.repack.y r3 = r0.m
            r3.h = r9
            com.dreamers.exoplayercore.repack.y r3 = r0.m
            r0.a((com.dreamers.exoplayercore.repack.C0004ac) r1, (com.dreamers.exoplayercore.repack.C0102y) r3, (com.dreamers.exoplayercore.repack.C0008ag) r2, (boolean) r5)
            com.dreamers.exoplayercore.repack.y r3 = r0.m
            int r3 = r3.b
            goto L_0x0341
        L_0x02e9:
            com.dreamers.exoplayercore.repack.w r7 = r0.t
            r0.a((com.dreamers.exoplayercore.repack.C0100w) r7)
            com.dreamers.exoplayercore.repack.y r7 = r0.m
            r7.h = r6
            com.dreamers.exoplayercore.repack.y r6 = r0.m
            r0.a((com.dreamers.exoplayercore.repack.C0004ac) r1, (com.dreamers.exoplayercore.repack.C0102y) r6, (com.dreamers.exoplayercore.repack.C0008ag) r2, (boolean) r5)
            com.dreamers.exoplayercore.repack.y r6 = r0.m
            int r6 = r6.b
            com.dreamers.exoplayercore.repack.y r7 = r0.m
            int r7 = r7.d
            com.dreamers.exoplayercore.repack.y r9 = r0.m
            int r9 = r9.c
            if (r9 <= 0) goto L_0x030a
            com.dreamers.exoplayercore.repack.y r9 = r0.m
            int r9 = r9.c
            int r3 = r3 + r9
        L_0x030a:
            com.dreamers.exoplayercore.repack.w r9 = r0.t
            r0.b((com.dreamers.exoplayercore.repack.C0100w) r9)
            com.dreamers.exoplayercore.repack.y r9 = r0.m
            r9.h = r3
            com.dreamers.exoplayercore.repack.y r3 = r0.m
            int r9 = r3.d
            com.dreamers.exoplayercore.repack.y r10 = r0.m
            int r10 = r10.e
            int r9 = r9 + r10
            r3.d = r9
            com.dreamers.exoplayercore.repack.y r3 = r0.m
            r0.a((com.dreamers.exoplayercore.repack.C0004ac) r1, (com.dreamers.exoplayercore.repack.C0102y) r3, (com.dreamers.exoplayercore.repack.C0008ag) r2, (boolean) r5)
            com.dreamers.exoplayercore.repack.y r3 = r0.m
            int r3 = r3.b
            com.dreamers.exoplayercore.repack.y r9 = r0.m
            int r9 = r9.c
            if (r9 <= 0) goto L_0x0341
            com.dreamers.exoplayercore.repack.y r9 = r0.m
            int r9 = r9.c
            r0.d(r7, r6)
            com.dreamers.exoplayercore.repack.y r6 = r0.m
            r6.h = r9
            com.dreamers.exoplayercore.repack.y r6 = r0.m
            r0.a((com.dreamers.exoplayercore.repack.C0004ac) r1, (com.dreamers.exoplayercore.repack.C0102y) r6, (com.dreamers.exoplayercore.repack.C0008ag) r2, (boolean) r5)
            com.dreamers.exoplayercore.repack.y r6 = r0.m
            int r6 = r6.b
        L_0x0341:
            int r7 = r16.i()
            if (r7 <= 0) goto L_0x0362
            boolean r7 = r0.o
            if (r7 == 0) goto L_0x0356
            int r7 = r0.a((int) r6, (com.dreamers.exoplayercore.repack.C0004ac) r1, (com.dreamers.exoplayercore.repack.C0008ag) r2, (boolean) r8)
            int r3 = r3 + r7
            int r6 = r6 + r7
            int r7 = r0.b(r3, r1, r2, r5)
            goto L_0x0360
        L_0x0356:
            int r7 = r0.b(r3, r1, r2, r8)
            int r3 = r3 + r7
            int r6 = r6 + r7
            int r7 = r0.a((int) r6, (com.dreamers.exoplayercore.repack.C0004ac) r1, (com.dreamers.exoplayercore.repack.C0008ag) r2, (boolean) r5)
        L_0x0360:
            int r3 = r3 + r7
            int r6 = r6 + r7
        L_0x0362:
            boolean r7 = r2.k
            if (r7 == 0) goto L_0x0407
            int r7 = r16.i()
            if (r7 == 0) goto L_0x0407
            boolean r7 = r2.g
            if (r7 != 0) goto L_0x0407
            boolean r7 = r16.g()
            if (r7 != 0) goto L_0x0378
            goto L_0x0407
        L_0x0378:
            java.util.List r7 = r1.d
            int r9 = r7.size()
            android.view.View r10 = r0.b((int) r5)
            int r10 = e((android.view.View) r10)
            r11 = 0
            r12 = 0
            r13 = 0
        L_0x0389:
            if (r11 >= r9) goto L_0x03c0
            java.lang.Object r14 = r7.get(r11)
            com.dreamers.exoplayercore.repack.ai r14 = (com.dreamers.exoplayercore.repack.C0010ai) r14
            boolean r15 = r14.isRemoved()
            if (r15 != 0) goto L_0x03bc
            int r15 = r14.getLayoutPosition()
            if (r15 >= r10) goto L_0x039f
            r15 = 1
            goto L_0x03a0
        L_0x039f:
            r15 = 0
        L_0x03a0:
            boolean r8 = r0.o
            if (r15 == r8) goto L_0x03a6
            r8 = -1
            goto L_0x03a7
        L_0x03a6:
            r8 = 1
        L_0x03a7:
            if (r8 != r4) goto L_0x03b3
            com.dreamers.exoplayercore.repack.D r8 = r0.n
            android.view.View r14 = r14.itemView
            int r8 = r8.e(r14)
            int r12 = r12 + r8
            goto L_0x03bc
        L_0x03b3:
            com.dreamers.exoplayercore.repack.D r8 = r0.n
            android.view.View r14 = r14.itemView
            int r8 = r8.e(r14)
            int r13 = r13 + r8
        L_0x03bc:
            int r11 = r11 + 1
            r8 = 1
            goto L_0x0389
        L_0x03c0:
            com.dreamers.exoplayercore.repack.y r4 = r0.m
            r4.j = r7
            r4 = 0
            if (r12 <= 0) goto L_0x03e4
            android.view.View r7 = r16.s()
            int r7 = e((android.view.View) r7)
            r0.e(r7, r3)
            com.dreamers.exoplayercore.repack.y r3 = r0.m
            r3.h = r12
            com.dreamers.exoplayercore.repack.y r3 = r0.m
            r3.c = r5
            com.dreamers.exoplayercore.repack.y r3 = r0.m
            r3.a((android.view.View) r4)
            com.dreamers.exoplayercore.repack.y r3 = r0.m
            r0.a((com.dreamers.exoplayercore.repack.C0004ac) r1, (com.dreamers.exoplayercore.repack.C0102y) r3, (com.dreamers.exoplayercore.repack.C0008ag) r2, (boolean) r5)
        L_0x03e4:
            if (r13 <= 0) goto L_0x0403
            android.view.View r3 = r16.t()
            int r3 = e((android.view.View) r3)
            r0.d(r3, r6)
            com.dreamers.exoplayercore.repack.y r3 = r0.m
            r3.h = r13
            com.dreamers.exoplayercore.repack.y r3 = r0.m
            r3.c = r5
            com.dreamers.exoplayercore.repack.y r3 = r0.m
            r3.a((android.view.View) r4)
            com.dreamers.exoplayercore.repack.y r3 = r0.m
            r0.a((com.dreamers.exoplayercore.repack.C0004ac) r1, (com.dreamers.exoplayercore.repack.C0102y) r3, (com.dreamers.exoplayercore.repack.C0008ag) r2, (boolean) r5)
        L_0x0403:
            com.dreamers.exoplayercore.repack.y r1 = r0.m
            r1.j = r4
        L_0x0407:
            boolean r1 = r2.g
            if (r1 != 0) goto L_0x0414
            com.dreamers.exoplayercore.repack.D r1 = r0.n
            int r2 = r1.e()
            r1.b = r2
            return
        L_0x0414:
            com.dreamers.exoplayercore.repack.w r1 = r0.t
            r1.a()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dreamers.exoplayercore.repack.C0099v.a(com.dreamers.exoplayercore.repack.ac, com.dreamers.exoplayercore.repack.ag):void");
    }

    public final void a(C0008ag agVar) {
        super.a(agVar);
        this.s = null;
        this.q = -1;
        this.r = Integer.MIN_VALUE;
        this.t.a();
    }

    public final void a(String str) {
        if (this.s == null) {
            super.a(str);
        }
    }

    public final boolean a() {
        return true;
    }

    public final int b(int i, C0004ac acVar, C0008ag agVar) {
        if (this.l == 0) {
            return 0;
        }
        return d(i, acVar, agVar);
    }

    public final int b(C0008ag agVar) {
        return i(agVar);
    }

    public final Y b() {
        return new Y();
    }

    public final int c(C0008ag agVar) {
        return i(agVar);
    }

    public final Parcelable c() {
        int i;
        if (this.s != null) {
            return new C0103z(this.s);
        }
        C0103z zVar = new C0103z();
        if (i() > 0) {
            q();
            boolean z = this.o;
            zVar.c = z;
            if (z) {
                View t2 = t();
                zVar.b = this.n.c() - this.n.b(t2);
                i = e(t2);
            } else {
                View s2 = s();
                zVar.a = e(s2);
                zVar.b = this.n.a(s2) - this.n.b();
                return zVar;
            }
        } else {
            i = -1;
        }
        zVar.a = i;
        return zVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0021, code lost:
        if (r6.l == 1) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0026, code lost:
        if (r6.l == 0) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002d, code lost:
        r7 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0033, code lost:
        if (p() == false) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003a, code lost:
        r7 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0040, code lost:
        if (p() == false) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0043, code lost:
        if (r7 != Integer.MIN_VALUE) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0045, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0046, code lost:
        q();
        q();
        a(r7, (int) (((float) r6.n.e()) * 0.33333334f), false, r9);
        r6.m.g = Integer.MIN_VALUE;
        r6.m.a = false;
        a(r8, r6.m, r9, true);
        r8 = r6.o;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006c, code lost:
        if (r7 != -1) goto L_0x007a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x006e, code lost:
        if (r8 == false) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0070, code lost:
        r8 = v();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0075, code lost:
        r8 = u();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x007a, code lost:
        if (r8 == false) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x007d, code lost:
        if (r7 != -1) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x007f, code lost:
        r7 = s();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0084, code lost:
        r7 = t();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x008c, code lost:
        if (r7.hasFocusable() == false) goto L_0x0092;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x008e, code lost:
        if (r8 != null) goto L_0x0091;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0090, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0091, code lost:
        return r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0092, code lost:
        return r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0012, code lost:
        r7 = Integer.MIN_VALUE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0017, code lost:
        if (r6.l == 1) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r6.l == 0) goto L_0x002d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.view.View c(int r7, com.dreamers.exoplayercore.repack.C0004ac r8, com.dreamers.exoplayercore.repack.C0008ag r9) {
        /*
            r6 = this;
            r6.o()
            int r0 = r6.i()
            r1 = 0
            if (r0 != 0) goto L_0x000b
            return r1
        L_0x000b:
            r0 = -1
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = 1
            switch(r7) {
                case 1: goto L_0x0036;
                case 2: goto L_0x0029;
                case 17: goto L_0x0024;
                case 33: goto L_0x001f;
                case 66: goto L_0x001a;
                case 130: goto L_0x0015;
                default: goto L_0x0012;
            }
        L_0x0012:
            r7 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x0043
        L_0x0015:
            int r7 = r6.l
            if (r7 != r3) goto L_0x0012
            goto L_0x002d
        L_0x001a:
            int r7 = r6.l
            if (r7 != 0) goto L_0x0012
            goto L_0x002d
        L_0x001f:
            int r7 = r6.l
            if (r7 != r3) goto L_0x0012
            goto L_0x003a
        L_0x0024:
            int r7 = r6.l
            if (r7 != 0) goto L_0x0012
            goto L_0x003a
        L_0x0029:
            int r7 = r6.l
            if (r7 != r3) goto L_0x002f
        L_0x002d:
            r7 = 1
            goto L_0x0043
        L_0x002f:
            boolean r7 = r6.p()
            if (r7 == 0) goto L_0x002d
            goto L_0x003a
        L_0x0036:
            int r7 = r6.l
            if (r7 != r3) goto L_0x003c
        L_0x003a:
            r7 = -1
            goto L_0x0043
        L_0x003c:
            boolean r7 = r6.p()
            if (r7 == 0) goto L_0x003a
            goto L_0x002d
        L_0x0043:
            if (r7 != r2) goto L_0x0046
            return r1
        L_0x0046:
            r6.q()
            r6.q()
            com.dreamers.exoplayercore.repack.D r4 = r6.n
            int r4 = r4.e()
            float r4 = (float) r4
            r5 = 1051372203(0x3eaaaaab, float:0.33333334)
            float r4 = r4 * r5
            int r4 = (int) r4
            r5 = 0
            r6.a((int) r7, (int) r4, (boolean) r5, (com.dreamers.exoplayercore.repack.C0008ag) r9)
            com.dreamers.exoplayercore.repack.y r4 = r6.m
            r4.g = r2
            com.dreamers.exoplayercore.repack.y r2 = r6.m
            r2.a = r5
            com.dreamers.exoplayercore.repack.y r2 = r6.m
            r6.a((com.dreamers.exoplayercore.repack.C0004ac) r8, (com.dreamers.exoplayercore.repack.C0102y) r2, (com.dreamers.exoplayercore.repack.C0008ag) r9, (boolean) r3)
            boolean r8 = r6.o
            if (r7 != r0) goto L_0x007a
            if (r8 == 0) goto L_0x0075
        L_0x0070:
            android.view.View r8 = r6.v()
            goto L_0x007d
        L_0x0075:
            android.view.View r8 = r6.u()
            goto L_0x007d
        L_0x007a:
            if (r8 == 0) goto L_0x0070
            goto L_0x0075
        L_0x007d:
            if (r7 != r0) goto L_0x0084
            android.view.View r7 = r6.s()
            goto L_0x0088
        L_0x0084:
            android.view.View r7 = r6.t()
        L_0x0088:
            boolean r9 = r7.hasFocusable()
            if (r9 == 0) goto L_0x0092
            if (r8 != 0) goto L_0x0091
            return r1
        L_0x0091:
            return r7
        L_0x0092:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dreamers.exoplayercore.repack.C0099v.c(int, com.dreamers.exoplayercore.repack.ac, com.dreamers.exoplayercore.repack.ag):android.view.View");
    }

    public final int d(C0008ag agVar) {
        return j(agVar);
    }

    public final boolean d() {
        return this.l == 0;
    }

    public final int e(C0008ag agVar) {
        return j(agVar);
    }

    public final boolean e() {
        return this.l == 1;
    }

    public final int f(C0008ag agVar) {
        return k(agVar);
    }

    /* access modifiers changed from: package-private */
    public final boolean f() {
        boolean z;
        if (!(this.i == 1073741824 || this.h == 1073741824)) {
            int i = i();
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    z = false;
                    break;
                }
                ViewGroup.LayoutParams layoutParams = b(i2).getLayoutParams();
                if (layoutParams.width < 0 && layoutParams.height < 0) {
                    z = true;
                    break;
                }
                i2++;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    public final int g(C0008ag agVar) {
        return k(agVar);
    }

    public final boolean g() {
        return this.s == null;
    }
}
