package com.dreamers.exoplayercore.repack;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.core.os.TraceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.NestedScrollingChild2;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.ScrollingView;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import gnu.expr.Declaration;
import java.util.ArrayList;
import java.util.List;

public class F extends ViewGroup implements NestedScrollingChild2, ScrollingView {
    static final Interpolator B = new I();
    private static final boolean C = false;
    private static final boolean D = false;
    static final boolean a = (Build.VERSION.SDK_INT == 18 || Build.VERSION.SDK_INT == 19 || Build.VERSION.SDK_INT == 20);
    static final boolean b = (Build.VERSION.SDK_INT >= 23);
    static final boolean c = true;
    static final boolean d = (Build.VERSION.SDK_INT >= 21);
    final int[] A;
    private final C0005ad E;
    private C0006ae F;
    private boolean G;
    private final Rect H;
    private ArrayList I;
    private Z J;
    private int K;
    private boolean L;
    private int M;
    private final AccessibilityManager N;
    private boolean O;
    private int P;
    private int Q;
    private EdgeEffect R;
    private EdgeEffect S;
    private EdgeEffect T;
    private EdgeEffect U;
    private int V;
    private int W;
    private VelocityTracker aa;
    private int ab;
    private int ac;
    private int ad;
    private int ae;
    private int af;
    private final int ag;
    private final int ah;
    private float ai;
    private float aj;
    private boolean ak;
    private C0009ah al;
    private R am;
    private final int[] an;
    private NestedScrollingChildHelper ao;
    private final int[] ap;
    private final int[] aq;
    private List ar;
    private Runnable as;
    private final C0020as at;
    final C0004ac e;
    C0000a f;
    C0081d g;
    final C0018aq h;
    final Rect i;
    final RectF j;
    M k;
    U l;
    final ArrayList m;
    boolean n;
    boolean o;
    boolean p;
    boolean q;
    Q r;
    C0095r s;
    C0097t t;
    final C0008ag u;
    boolean v;
    boolean w;
    boolean x;
    C0011aj y;
    final int[] z;

    static {
        Class[] clsArr = {Context.class, AttributeSet.class, Integer.TYPE, Integer.TYPE};
    }

    public F(Context context) {
        this(context, (byte) 0);
    }

    private F(Context context, byte b2) {
        this(context, 0);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private F(Context context, char c2) {
        super(context, (AttributeSet) null, 0);
        C0097t tVar = null;
        boolean z2 = false;
        this.E = new C0005ad(this);
        this.e = new C0004ac(this);
        this.h = new C0018aq();
        new G(this);
        this.i = new Rect();
        this.H = new Rect();
        this.j = new RectF();
        this.m = new ArrayList();
        this.I = new ArrayList();
        this.K = 0;
        this.q = false;
        this.O = false;
        this.P = 0;
        this.Q = 0;
        new P();
        this.r = new C0084g();
        this.V = 0;
        this.W = -1;
        this.ai = Float.MIN_VALUE;
        this.aj = Float.MIN_VALUE;
        this.ak = true;
        this.al = new C0009ah(this);
        this.t = d ? new C0097t() : tVar;
        this.u = new C0008ag();
        this.v = false;
        this.w = false;
        this.am = new T(this);
        this.x = false;
        this.an = new int[2];
        this.ap = new int[2];
        this.z = new int[2];
        this.aq = new int[2];
        this.A = new int[2];
        this.ar = new ArrayList();
        this.as = new H(this);
        this.at = new J(this);
        this.G = true;
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.af = viewConfiguration.getScaledTouchSlop();
        this.ai = ViewConfigurationCompat.getScaledHorizontalScrollFactor(viewConfiguration, context);
        this.aj = ViewConfigurationCompat.getScaledVerticalScrollFactor(viewConfiguration, context);
        this.ag = viewConfiguration.getScaledMinimumFlingVelocity();
        this.ah = viewConfiguration.getScaledMaximumFlingVelocity();
        setWillNotDraw(getOverScrollMode() == 2 ? true : z2);
        this.r.h = this.am;
        this.f = new C0000a(new L(this));
        this.g = new C0081d(new K(this));
        if (ViewCompat.getImportantForAutofill(this) == 0) {
            ViewCompat.setImportantForAutofill(this, 8);
        }
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            ViewCompat.setImportantForAccessibility(this, 1);
        }
        this.N = (AccessibilityManager) getContext().getSystemService("accessibility");
        C0011aj ajVar = new C0011aj(this);
        this.y = ajVar;
        ViewCompat.setAccessibilityDelegate(this, ajVar);
        setDescendantFocusability(262144);
        setNestedScrollingEnabled(true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00f7, code lost:
        r0 = java.lang.Math.min(r0, r2) - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00fd, code lost:
        if (r0 < 0) goto L_0x0113;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00ff, code lost:
        r1 = b(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0103, code lost:
        if (r1 == null) goto L_0x0113;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x010b, code lost:
        if (r1.itemView.hasFocusable() == false) goto L_0x0110;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x010d, code lost:
        r0 = r1.itemView;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0110, code lost:
        r0 = r0 - 1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:103:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0115  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A() {
        /*
            r13 = this;
            boolean r0 = r13.ak
            if (r0 == 0) goto L_0x0132
            com.dreamers.exoplayercore.repack.M r0 = r13.k
            if (r0 == 0) goto L_0x0132
            boolean r0 = r13.hasFocus()
            if (r0 == 0) goto L_0x0132
            int r0 = r13.getDescendantFocusability()
            r1 = 393216(0x60000, float:5.51013E-40)
            if (r0 == r1) goto L_0x0132
            int r0 = r13.getDescendantFocusability()
            r1 = 131072(0x20000, float:1.83671E-40)
            if (r0 != r1) goto L_0x0026
            boolean r0 = r13.isFocused()
            if (r0 == 0) goto L_0x0026
            goto L_0x0132
        L_0x0026:
            boolean r0 = r13.isFocused()
            if (r0 != 0) goto L_0x0055
            android.view.View r0 = r13.getFocusedChild()
            boolean r1 = D
            if (r1 == 0) goto L_0x004c
            android.view.ViewParent r1 = r0.getParent()
            if (r1 == 0) goto L_0x0040
            boolean r1 = r0.hasFocus()
            if (r1 != 0) goto L_0x004c
        L_0x0040:
            com.dreamers.exoplayercore.repack.d r0 = r13.g
            int r0 = r0.a()
            if (r0 != 0) goto L_0x0055
            r13.requestFocus()
            return
        L_0x004c:
            com.dreamers.exoplayercore.repack.d r1 = r13.g
            boolean r0 = r1.d((android.view.View) r0)
            if (r0 != 0) goto L_0x0055
            return
        L_0x0055:
            com.dreamers.exoplayercore.repack.ag r0 = r13.u
            long r0 = r0.m
            r2 = 0
            r3 = -1
            r5 = 0
            int r6 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r6 == 0) goto L_0x00ad
            com.dreamers.exoplayercore.repack.M r0 = r13.k
            boolean r0 = r0.hasStableIds()
            if (r0 == 0) goto L_0x00ad
            com.dreamers.exoplayercore.repack.ag r0 = r13.u
            long r0 = r0.m
            com.dreamers.exoplayercore.repack.M r6 = r13.k
            if (r6 == 0) goto L_0x00ad
            boolean r6 = r6.hasStableIds()
            if (r6 != 0) goto L_0x0078
            goto L_0x00ad
        L_0x0078:
            com.dreamers.exoplayercore.repack.d r6 = r13.g
            int r6 = r6.b()
            r8 = r5
            r7 = 0
        L_0x0080:
            if (r7 >= r6) goto L_0x00ae
            com.dreamers.exoplayercore.repack.d r9 = r13.g
            android.view.View r9 = r9.c((int) r7)
            com.dreamers.exoplayercore.repack.ai r9 = a((android.view.View) r9)
            if (r9 == 0) goto L_0x00aa
            boolean r10 = r9.isRemoved()
            if (r10 != 0) goto L_0x00aa
            long r10 = r9.getItemId()
            int r12 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r12 != 0) goto L_0x00aa
            com.dreamers.exoplayercore.repack.d r8 = r13.g
            android.view.View r10 = r9.itemView
            boolean r8 = r8.d((android.view.View) r10)
            if (r8 == 0) goto L_0x00a8
            r8 = r9
            goto L_0x00aa
        L_0x00a8:
            r8 = r9
            goto L_0x00ae
        L_0x00aa:
            int r7 = r7 + 1
            goto L_0x0080
        L_0x00ad:
            r8 = r5
        L_0x00ae:
            if (r8 == 0) goto L_0x00c6
            com.dreamers.exoplayercore.repack.d r0 = r13.g
            android.view.View r1 = r8.itemView
            boolean r0 = r0.d((android.view.View) r1)
            if (r0 != 0) goto L_0x00c6
            android.view.View r0 = r8.itemView
            boolean r0 = r0.hasFocusable()
            if (r0 != 0) goto L_0x00c3
            goto L_0x00c6
        L_0x00c3:
            android.view.View r5 = r8.itemView
            goto L_0x0113
        L_0x00c6:
            com.dreamers.exoplayercore.repack.d r0 = r13.g
            int r0 = r0.a()
            if (r0 <= 0) goto L_0x0113
            com.dreamers.exoplayercore.repack.ag r0 = r13.u
            int r0 = r0.l
            r1 = -1
            if (r0 == r1) goto L_0x00d9
            com.dreamers.exoplayercore.repack.ag r0 = r13.u
            int r2 = r0.l
        L_0x00d9:
            com.dreamers.exoplayercore.repack.ag r0 = r13.u
            int r0 = r0.a()
            r1 = r2
        L_0x00e0:
            if (r1 >= r0) goto L_0x00f7
            com.dreamers.exoplayercore.repack.ai r6 = r13.b((int) r1)
            if (r6 == 0) goto L_0x00f7
            android.view.View r7 = r6.itemView
            boolean r7 = r7.hasFocusable()
            if (r7 == 0) goto L_0x00f4
            android.view.View r0 = r6.itemView
        L_0x00f2:
            r5 = r0
            goto L_0x0113
        L_0x00f4:
            int r1 = r1 + 1
            goto L_0x00e0
        L_0x00f7:
            int r0 = java.lang.Math.min(r0, r2)
            int r0 = r0 + -1
        L_0x00fd:
            if (r0 < 0) goto L_0x0113
            com.dreamers.exoplayercore.repack.ai r1 = r13.b((int) r0)
            if (r1 == 0) goto L_0x0113
            android.view.View r2 = r1.itemView
            boolean r2 = r2.hasFocusable()
            if (r2 == 0) goto L_0x0110
            android.view.View r0 = r1.itemView
            goto L_0x00f2
        L_0x0110:
            int r0 = r0 + -1
            goto L_0x00fd
        L_0x0113:
            if (r5 == 0) goto L_0x0132
            com.dreamers.exoplayercore.repack.ag r0 = r13.u
            int r0 = r0.n
            long r0 = (long) r0
            int r2 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r2 == 0) goto L_0x012f
            com.dreamers.exoplayercore.repack.ag r0 = r13.u
            int r0 = r0.n
            android.view.View r0 = r5.findViewById(r0)
            if (r0 == 0) goto L_0x012f
            boolean r1 = r0.isFocusable()
            if (r1 == 0) goto L_0x012f
            r5 = r0
        L_0x012f:
            r5.requestFocus()
        L_0x0132:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dreamers.exoplayercore.repack.F.A():void");
    }

    private void B() {
        if (this.V == 2) {
            OverScroller overScroller = this.al.c;
            overScroller.getFinalX();
            overScroller.getCurrX();
            overScroller.getFinalY();
            overScroller.getCurrY();
        }
    }

    private void C() {
        this.u.a(1);
        B();
        this.u.i = false;
        d();
        this.h.a();
        e();
        w();
        y();
        C0008ag agVar = this.u;
        agVar.h = agVar.j && this.w;
        this.w = false;
        this.v = false;
        C0008ag agVar2 = this.u;
        agVar2.g = agVar2.k;
        this.u.e = this.k.getItemCount();
        a(this.an);
        if (this.u.j) {
            int a2 = this.g.a();
            for (int i2 = 0; i2 < a2; i2++) {
                C0010ai a3 = a(this.g.b(i2));
                if (!a3.shouldIgnore() && (!a3.isInvalid() || this.k.hasStableIds())) {
                    Q.e(a3);
                    a3.getUnmodifiedPayloads();
                    this.h.a(a3, new S().a(a3));
                    if (this.u.h && a3.isUpdated() && !a3.isRemoved() && !a3.shouldIgnore() && !a3.isInvalid()) {
                        this.h.a(e(a3), a3);
                    }
                }
            }
        }
        if (this.u.k) {
            G();
            boolean z2 = this.u.f;
            this.u.f = false;
            this.l.a(this.e, this.u);
            this.u.f = z2;
            for (int i3 = 0; i3 < this.g.a(); i3++) {
                C0010ai a4 = a(this.g.b(i3));
                if (!a4.shouldIgnore() && !this.h.b(a4)) {
                    Q.e(a4);
                    boolean hasAnyOfTheFlags = a4.hasAnyOfTheFlags(8192);
                    a4.getUnmodifiedPayloads();
                    S a5 = new S().a(a4);
                    if (hasAnyOfTheFlags) {
                        a(a4, a5);
                    } else {
                        this.h.b(a4, a5);
                    }
                }
            }
        }
        H();
        b(true);
        a(false);
        this.u.d = 2;
    }

    private void D() {
        d();
        e();
        this.u.a(6);
        this.f.e();
        this.u.e = this.k.getItemCount();
        this.u.c = 0;
        this.u.g = false;
        this.l.a(this.e, this.u);
        this.u.f = false;
        this.F = null;
        C0008ag agVar = this.u;
        agVar.j = agVar.j && this.r != null;
        this.u.d = 4;
        b(true);
        a(false);
    }

    private void E() {
        this.u.a(4);
        d();
        e();
        this.u.d = 1;
        if (this.u.j) {
            for (int a2 = this.g.a() - 1; a2 >= 0; a2--) {
                C0010ai a3 = a(this.g.b(a2));
                if (!a3.shouldIgnore()) {
                    long e2 = e(a3);
                    S a4 = new S().a(a3);
                    C0010ai a5 = this.h.a(e2);
                    if (a5 != null && !a5.shouldIgnore()) {
                        boolean a6 = this.h.a(a5);
                        boolean a7 = this.h.a(a3);
                        if (!a6 || a5 != a3) {
                            S a8 = this.h.a(a5, 4);
                            this.h.c(a3, a4);
                            S a9 = this.h.a(a3, 8);
                            if (a8 == null) {
                                a(e2, a3, a5);
                            } else {
                                a(a5, a3, a8, a9, a6, a7);
                            }
                        }
                    }
                    this.h.c(a3, a4);
                }
            }
            this.h.a(this.at);
        }
        this.l.b(this.e);
        C0008ag agVar = this.u;
        agVar.b = agVar.e;
        this.q = false;
        this.O = false;
        this.u.j = false;
        this.u.k = false;
        if (this.e.b != null) {
            this.e.b.clear();
        }
        if (this.l.g) {
            this.l.f = 0;
            this.l.g = false;
            this.e.b();
        }
        this.l.a(this.u);
        b(true);
        a(false);
        this.h.a();
        int[] iArr = this.an;
        if (e(iArr[0], iArr[1])) {
            h();
        }
        A();
        z();
    }

    private void F() {
        int b2 = this.g.b();
        for (int i2 = 0; i2 < b2; i2++) {
            ((Y) this.g.c(i2).getLayoutParams()).c = true;
        }
        C0004ac acVar = this.e;
        int size = acVar.c.size();
        for (int i3 = 0; i3 < size; i3++) {
            Y y2 = (Y) ((C0010ai) acVar.c.get(i3)).itemView.getLayoutParams();
            if (y2 != null) {
                y2.c = true;
            }
        }
    }

    private void G() {
        int b2 = this.g.b();
        for (int i2 = 0; i2 < b2; i2++) {
            C0010ai a2 = a(this.g.c(i2));
            if (!a2.shouldIgnore()) {
                a2.saveOldPosition();
            }
        }
    }

    private void H() {
        int b2 = this.g.b();
        for (int i2 = 0; i2 < b2; i2++) {
            C0010ai a2 = a(this.g.c(i2));
            if (!a2.shouldIgnore()) {
                a2.clearOldPosition();
            }
        }
        this.e.e();
    }

    private void I() {
        int b2 = this.g.b();
        for (int i2 = 0; i2 < b2; i2++) {
            C0010ai a2 = a(this.g.c(i2));
            if (a2 != null && !a2.shouldIgnore()) {
                a2.addFlags(6);
            }
        }
        F();
        this.e.d();
    }

    private void J() {
        int i2;
        for (int size = this.ar.size() - 1; size >= 0; size--) {
            C0010ai aiVar = (C0010ai) this.ar.get(size);
            if (aiVar.itemView.getParent() == this && !aiVar.shouldIgnore() && (i2 = aiVar.mPendingAccessibilityState) != -1) {
                ViewCompat.setImportantForAccessibility(aiVar.itemView, i2);
                aiVar.mPendingAccessibilityState = -1;
            }
        }
        this.ar.clear();
    }

    private NestedScrollingChildHelper K() {
        if (this.ao == null) {
            this.ao = new NestedScrollingChildHelper(this);
        }
        return this.ao;
    }

    static C0010ai a(View view) {
        if (view == null) {
            return null;
        }
        return ((Y) view.getLayoutParams()).a;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(float r7, float r8, float r9, float r10) {
        /*
            r6 = this;
            r0 = 1065353216(0x3f800000, float:1.0)
            r1 = 1
            r2 = 0
            int r3 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r3 >= 0) goto L_0x0021
            r6.m()
            android.widget.EdgeEffect r3 = r6.R
            float r4 = -r8
            int r5 = r6.getWidth()
            float r5 = (float) r5
            float r4 = r4 / r5
            int r5 = r6.getHeight()
            float r5 = (float) r5
            float r9 = r9 / r5
            float r9 = r0 - r9
        L_0x001c:
            androidx.core.widget.EdgeEffectCompat.onPull(r3, r4, r9)
            r9 = 1
            goto L_0x0039
        L_0x0021:
            int r3 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r3 <= 0) goto L_0x0038
            r6.n()
            android.widget.EdgeEffect r3 = r6.T
            int r4 = r6.getWidth()
            float r4 = (float) r4
            float r4 = r8 / r4
            int r5 = r6.getHeight()
            float r5 = (float) r5
            float r9 = r9 / r5
            goto L_0x001c
        L_0x0038:
            r9 = 0
        L_0x0039:
            int r3 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r3 >= 0) goto L_0x0053
            r6.o()
            android.widget.EdgeEffect r9 = r6.S
            float r0 = -r10
            int r3 = r6.getHeight()
            float r3 = (float) r3
            float r0 = r0 / r3
            int r3 = r6.getWidth()
            float r3 = (float) r3
            float r7 = r7 / r3
            androidx.core.widget.EdgeEffectCompat.onPull(r9, r0, r7)
            goto L_0x006f
        L_0x0053:
            int r3 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r3 <= 0) goto L_0x006e
            r6.p()
            android.widget.EdgeEffect r9 = r6.U
            int r3 = r6.getHeight()
            float r3 = (float) r3
            float r3 = r10 / r3
            int r4 = r6.getWidth()
            float r4 = (float) r4
            float r7 = r7 / r4
            float r0 = r0 - r7
            androidx.core.widget.EdgeEffectCompat.onPull(r9, r3, r0)
            goto L_0x006f
        L_0x006e:
            r1 = r9
        L_0x006f:
            if (r1 != 0) goto L_0x0079
            int r7 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r7 != 0) goto L_0x0079
            int r7 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r7 == 0) goto L_0x007c
        L_0x0079:
            androidx.core.view.ViewCompat.postInvalidateOnAnimation(r6)
        L_0x007c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dreamers.exoplayercore.repack.F.a(float, float, float, float):void");
    }

    private void a(long j2, C0010ai aiVar, C0010ai aiVar2) {
        int a2 = this.g.a();
        int i2 = 0;
        while (i2 < a2) {
            C0010ai a3 = a(this.g.b(i2));
            if (a3 == aiVar || e(a3) != j2) {
                i2++;
            } else {
                M m2 = this.k;
                if (m2 == null || !m2.hasStableIds()) {
                    throw new IllegalStateException("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:" + a3 + " \n View Holder 2:" + aiVar + a());
                }
                throw new IllegalStateException("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:" + a3 + " \n View Holder 2:" + aiVar + a());
            }
        }
        Log.e("RecyclerView", "Problem while matching changed view holders with the newones. The pre-layout information for the change holder " + aiVar2 + " cannot be found but it is necessary for " + aiVar + a());
    }

    private void a(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.W) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.W = motionEvent.getPointerId(i2);
            int x2 = (int) (motionEvent.getX(i2) + 0.5f);
            this.ad = x2;
            this.ab = x2;
            int y2 = (int) (motionEvent.getY(i2) + 0.5f);
            this.ae = y2;
            this.ac = y2;
        }
    }

    static void a(View view, Rect rect) {
        Y y2 = (Y) view.getLayoutParams();
        Rect rect2 = y2.b;
        rect.set((view.getLeft() - rect2.left) - y2.leftMargin, (view.getTop() - rect2.top) - y2.topMargin, view.getRight() + rect2.right + y2.rightMargin, view.getBottom() + rect2.bottom + y2.bottomMargin);
    }

    private void a(View view, View view2) {
        View view3 = view2 != null ? view2 : view;
        this.i.set(0, 0, view3.getWidth(), view3.getHeight());
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof Y) {
            Y y2 = (Y) layoutParams;
            if (!y2.c) {
                Rect rect = y2.b;
                this.i.left -= rect.left;
                this.i.right += rect.right;
                this.i.top -= rect.top;
                this.i.bottom += rect.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, this.i);
            offsetRectIntoDescendantCoords(view, this.i);
        }
        this.l.a(this, view, this.i, !this.o, view2 == null);
    }

    private void a(C0010ai aiVar, C0010ai aiVar2, S s2, S s3, boolean z2, boolean z3) {
        aiVar.setIsRecyclable(false);
        if (z2) {
            a(aiVar);
        }
        if (aiVar != aiVar2) {
            if (z3) {
                a(aiVar2);
            }
            aiVar.mShadowedHolder = aiVar2;
            a(aiVar);
            this.e.b(aiVar);
            aiVar2.setIsRecyclable(false);
            aiVar2.mShadowingHolder = aiVar;
        }
        if (this.r.a(aiVar, aiVar2, s2, s3)) {
            g();
        }
    }

    private void a(int[] iArr) {
        int a2 = this.g.a();
        if (a2 == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i2 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        int i3 = Integer.MIN_VALUE;
        for (int i4 = 0; i4 < a2; i4++) {
            C0010ai a3 = a(this.g.b(i4));
            if (!a3.shouldIgnore()) {
                int layoutPosition = a3.getLayoutPosition();
                if (layoutPosition < i2) {
                    i2 = layoutPosition;
                }
                if (layoutPosition > i3) {
                    i3 = layoutPosition;
                }
            }
        }
        iArr[0] = i2;
        iArr[1] = i3;
    }

    private boolean a(int i2, int i3, MotionEvent motionEvent) {
        int i4;
        int i5;
        int i6;
        int i7;
        int i8 = i2;
        int i9 = i3;
        MotionEvent motionEvent2 = motionEvent;
        c();
        if (this.k != null) {
            a(i8, i9, this.A);
            int[] iArr = this.A;
            int i10 = iArr[0];
            int i11 = iArr[1];
            i6 = i11;
            i5 = i10;
            i4 = i8 - i10;
            i7 = i9 - i11;
        } else {
            i7 = 0;
            i6 = 0;
            i5 = 0;
            i4 = 0;
        }
        if (!this.m.isEmpty()) {
            invalidate();
        }
        int i12 = i7;
        if (dispatchNestedScroll(i5, i6, i4, i7, this.ap, 0)) {
            int i13 = this.ad;
            int[] iArr2 = this.ap;
            int i14 = iArr2[0];
            this.ad = i13 - i14;
            int i15 = this.ae;
            int i16 = iArr2[1];
            this.ae = i15 - i16;
            if (motionEvent2 != null) {
                motionEvent2.offsetLocation((float) i14, (float) i16);
            }
            int[] iArr3 = this.aq;
            int i17 = iArr3[0];
            int[] iArr4 = this.ap;
            iArr3[0] = i17 + iArr4[0];
            iArr3[1] = iArr3[1] + iArr4[1];
        } else if (getOverScrollMode() != 2) {
            if (motionEvent2 != null && !MotionEventCompat.isFromSource(motionEvent2, 8194)) {
                a(motionEvent.getX(), (float) i4, motionEvent.getY(), (float) i12);
            }
            b(i2, i3);
        }
        if (!(i5 == 0 && i6 == 0)) {
            h();
        }
        if (!awakenScrollBars()) {
            invalidate();
        }
        return (i5 == 0 && i6 == 0) ? false : true;
    }

    static F b(View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof F) {
            return (F) view;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            F b2 = b(viewGroup.getChildAt(i2));
            if (b2 != null) {
                return b2;
            }
        }
        return null;
    }

    private C0010ai b(int i2) {
        C0010ai aiVar = null;
        if (this.q) {
            return null;
        }
        int b2 = this.g.b();
        for (int i3 = 0; i3 < b2; i3++) {
            C0010ai a2 = a(this.g.c(i3));
            if (a2 != null && !a2.isRemoved() && d(a2) == i2) {
                if (!this.g.d(a2.itemView)) {
                    return a2;
                }
                aiVar = a2;
            }
        }
        return aiVar;
    }

    static void c(C0010ai aiVar) {
        if (aiVar.mNestedRecyclerView != null) {
            Object obj = aiVar.mNestedRecyclerView.get();
            while (true) {
                View view = (View) obj;
                while (true) {
                    if (view == null) {
                        aiVar.mNestedRecyclerView = null;
                        return;
                    } else if (view != aiVar.itemView) {
                        obj = view.getParent();
                        if (!(obj instanceof View)) {
                            view = null;
                        }
                    } else {
                        return;
                    }
                }
            }
        }
    }

    private C0010ai d(View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return a(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    private long e(C0010ai aiVar) {
        return this.k.hasStableIds() ? aiVar.getItemId() : (long) aiVar.mPosition;
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x0012 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0013 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.view.View e(android.view.View r3) {
        /*
            r2 = this;
        L_0x0000:
            android.view.ViewParent r0 = r3.getParent()
            if (r0 == 0) goto L_0x0010
            if (r0 == r2) goto L_0x0010
            boolean r1 = r0 instanceof android.view.View
            if (r1 == 0) goto L_0x0010
            r3 = r0
            android.view.View r3 = (android.view.View) r3
            goto L_0x0000
        L_0x0010:
            if (r0 != r2) goto L_0x0013
            return r3
        L_0x0013:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dreamers.exoplayercore.repack.F.e(android.view.View):android.view.View");
    }

    private boolean e(int i2, int i3) {
        a(this.an);
        int[] iArr = this.an;
        return (iArr[0] == i2 && iArr[1] == i3) ? false : true;
    }

    static long j() {
        if (d) {
            return System.nanoTime();
        }
        return 0;
    }

    private void k() {
        a(0);
        this.al.b();
    }

    private void l() {
        boolean z2;
        EdgeEffect edgeEffect = this.R;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            z2 = this.R.isFinished();
        } else {
            z2 = false;
        }
        EdgeEffect edgeEffect2 = this.S;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            z2 |= this.S.isFinished();
        }
        EdgeEffect edgeEffect3 = this.T;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            z2 |= this.T.isFinished();
        }
        EdgeEffect edgeEffect4 = this.U;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            z2 |= this.U.isFinished();
        }
        if (z2) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private void m() {
        int measuredHeight;
        int measuredWidth;
        if (this.R == null) {
            EdgeEffect a2 = P.a(this);
            this.R = a2;
            if (this.G) {
                measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
                measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
            } else {
                measuredHeight = getMeasuredHeight();
                measuredWidth = getMeasuredWidth();
            }
            a2.setSize(measuredHeight, measuredWidth);
        }
    }

    private void n() {
        int measuredHeight;
        int measuredWidth;
        if (this.T == null) {
            EdgeEffect a2 = P.a(this);
            this.T = a2;
            if (this.G) {
                measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
                measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
            } else {
                measuredHeight = getMeasuredHeight();
                measuredWidth = getMeasuredWidth();
            }
            a2.setSize(measuredHeight, measuredWidth);
        }
    }

    private void o() {
        int measuredWidth;
        int measuredHeight;
        if (this.S == null) {
            EdgeEffect a2 = P.a(this);
            this.S = a2;
            if (this.G) {
                measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
                measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
            } else {
                measuredWidth = getMeasuredWidth();
                measuredHeight = getMeasuredHeight();
            }
            a2.setSize(measuredWidth, measuredHeight);
        }
    }

    private void p() {
        int measuredWidth;
        int measuredHeight;
        if (this.U == null) {
            EdgeEffect a2 = P.a(this);
            this.U = a2;
            if (this.G) {
                measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
                measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
            } else {
                measuredWidth = getMeasuredWidth();
                measuredHeight = getMeasuredHeight();
            }
            a2.setSize(measuredWidth, measuredHeight);
        }
    }

    private void q() {
        this.U = null;
        this.S = null;
        this.T = null;
        this.R = null;
    }

    private void r() {
        VelocityTracker velocityTracker = this.aa;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        stopNestedScroll(0);
        l();
    }

    private void s() {
        r();
        a(0);
    }

    private void t() {
        int i2 = this.M;
        this.M = 0;
        if (i2 != 0 && f()) {
            AccessibilityEvent obtain = AccessibilityEvent.obtain();
            obtain.setEventType(2048);
            AccessibilityEventCompat.setContentChangeTypes(obtain, i2);
            sendAccessibilityEventUnchecked(obtain);
        }
    }

    private boolean u() {
        return this.P > 0;
    }

    private boolean v() {
        return this.r != null && this.l.g();
    }

    private void w() {
        boolean z2;
        if (this.q) {
            this.f.a();
        }
        if (v()) {
            this.f.b();
        } else {
            this.f.e();
        }
        boolean z3 = false;
        boolean z4 = this.v || this.w;
        this.u.j = this.o && this.r != null && ((z2 = this.q) || z4) && (!z2 || this.k.hasStableIds());
        C0008ag agVar = this.u;
        if (agVar.j && z4 && !this.q && v()) {
            z3 = true;
        }
        agVar.k = z3;
    }

    private void x() {
        String str;
        if (this.k == null) {
            str = "No adapter attached; skipping layout";
        } else if (this.l == null) {
            str = "No layout manager attached; skipping layout";
        } else {
            this.u.i = false;
            if (this.u.d == 1) {
                C();
            } else if (!this.f.f() && this.l.j == getWidth() && this.l.k == getHeight()) {
                this.l.b(this);
                E();
                return;
            }
            this.l.b(this);
            D();
            E();
            return;
        }
        Log.e("RecyclerView", str);
    }

    private void y() {
        int id;
        View e2;
        C0010ai aiVar = null;
        View focusedChild = (!this.ak || !hasFocus() || this.k == null) ? null : getFocusedChild();
        if (!(focusedChild == null || (e2 = e(focusedChild)) == null)) {
            aiVar = d(e2);
        }
        if (aiVar == null) {
            z();
            return;
        }
        this.u.m = this.k.hasStableIds() ? aiVar.getItemId() : -1;
        this.u.l = this.q ? -1 : aiVar.isRemoved() ? aiVar.mOldPosition : aiVar.getAdapterPosition();
        C0008ag agVar = this.u;
        View view = aiVar.itemView;
        loop0:
        while (true) {
            id = view.getId();
            while (true) {
                if (view.isFocused() || !(view instanceof ViewGroup) || !view.hasFocus()) {
                    agVar.n = id;
                } else {
                    view = ((ViewGroup) view).getFocusedChild();
                    if (view.getId() != -1) {
                    }
                }
            }
        }
        agVar.n = id;
    }

    private void z() {
        this.u.m = -1;
        this.u.l = -1;
        this.u.n = -1;
    }

    /* access modifiers changed from: package-private */
    public final String a() {
        return " " + super.toString() + ", adapter:" + this.k + ", layout:" + this.l + ", context:" + getContext();
    }

    /* access modifiers changed from: package-private */
    public final void a(int i2) {
        if (i2 != this.V) {
            this.V = i2;
            if (i2 != 2) {
                this.al.b();
            }
        }
    }

    public final void a(int i2, int i3) {
        int i4;
        U u2 = this.l;
        if (u2 == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        int i5 = !u2.d() ? 0 : i2;
        int i6 = !this.l.e() ? 0 : i3;
        if (i5 != 0 || i6 != 0) {
            C0009ah ahVar = this.al;
            int abs = Math.abs(i5);
            int abs2 = Math.abs(i6);
            boolean z2 = abs > abs2;
            int sqrt = (int) Math.sqrt(0.0d);
            int sqrt2 = (int) Math.sqrt((double) ((i5 * i5) + (i6 * i6)));
            F f2 = ahVar.e;
            int width = z2 ? f2.getWidth() : f2.getHeight();
            int i7 = width / 2;
            float f3 = (float) width;
            float f4 = (float) i7;
            float a2 = f4 + (C0009ah.a(Math.min(1.0f, (((float) sqrt2) * 1.0f) / f3)) * f4);
            if (sqrt > 0) {
                i4 = Math.round(Math.abs(a2 / ((float) sqrt)) * 1000.0f) * 4;
            } else {
                if (!z2) {
                    abs = abs2;
                }
                i4 = (int) (((((float) abs) / f3) + 1.0f) * 300.0f);
            }
            int min = Math.min(i4, 2000);
            Interpolator interpolator = B;
            if (ahVar.d != interpolator) {
                ahVar.d = interpolator;
                ahVar.c = new OverScroller(ahVar.e.getContext(), interpolator);
            }
            ahVar.e.a(2);
            ahVar.b = 0;
            ahVar.a = 0;
            ahVar.c.startScroll(0, 0, i5, i6, min);
            if (Build.VERSION.SDK_INT < 23) {
                ahVar.c.computeScrollOffset();
            }
            ahVar.a();
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(int i2, int i3, boolean z2) {
        int i4 = i2 + i3;
        int b2 = this.g.b();
        for (int i5 = 0; i5 < b2; i5++) {
            C0010ai a2 = a(this.g.c(i5));
            if (a2 != null && !a2.shouldIgnore()) {
                if (a2.mPosition >= i4) {
                    a2.offsetPosition(-i3, z2);
                } else if (a2.mPosition >= i2) {
                    a2.flagRemovedAndOffsetPosition(i2 - 1, -i3, z2);
                }
                this.u.f = true;
            }
        }
        C0004ac acVar = this.e;
        for (int size = acVar.c.size() - 1; size >= 0; size--) {
            C0010ai aiVar = (C0010ai) acVar.c.get(size);
            if (aiVar != null) {
                if (aiVar.mPosition >= i4) {
                    aiVar.offsetPosition(-i3, z2);
                } else if (aiVar.mPosition >= i2) {
                    aiVar.addFlags(8);
                    acVar.b(size);
                }
            }
        }
        requestLayout();
    }

    /* access modifiers changed from: package-private */
    public final void a(int i2, int i3, int[] iArr) {
        d();
        e();
        TraceCompat.beginSection("RV Scroll");
        B();
        int a2 = i2 != 0 ? this.l.a(i2, this.e, this.u) : 0;
        int b2 = i3 != 0 ? this.l.b(i3, this.e, this.u) : 0;
        TraceCompat.endSection();
        int a3 = this.g.a();
        for (int i4 = 0; i4 < a3; i4++) {
            View b3 = this.g.b(i4);
            C0010ai d2 = d(b3);
            if (!(d2 == null || d2.mShadowingHolder == null)) {
                View view = d2.mShadowingHolder.itemView;
                int left = b3.getLeft();
                int top = b3.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
        b(true);
        a(false);
        if (iArr != null) {
            iArr[0] = a2;
            iArr[1] = b2;
        }
    }

    public final void a(M m2) {
        M m3 = this.k;
        if (m3 != null) {
            m3.unregisterAdapterDataObserver(this.E);
            this.k.onDetachedFromRecyclerView(this);
        }
        b();
        this.f.a();
        M m4 = this.k;
        this.k = m2;
        if (m2 != null) {
            m2.registerAdapterDataObserver(this.E);
            m2.onAttachedToRecyclerView(this);
        }
        C0004ac acVar = this.e;
        M m5 = this.k;
        acVar.a();
        C0002aa c2 = acVar.c();
        if (m4 != null) {
            c2.b--;
        }
        if (c2.b == 0) {
            for (int i2 = 0; i2 < c2.a.size(); i2++) {
                ((C0003ab) c2.a.valueAt(i2)).a.clear();
            }
        }
        if (m5 != null) {
            c2.b++;
        }
        this.u.f = true;
        c(false);
        requestLayout();
    }

    public final void a(U u2) {
        if (u2 != this.l) {
            k();
            if (this.l != null) {
                Q q2 = this.r;
                if (q2 != null) {
                    q2.d();
                }
                this.l.c(this.e);
                this.l.b(this.e);
                this.e.a();
                this.l.a((F) null);
                this.l = null;
            } else {
                this.e.a();
            }
            C0081d dVar = this.g;
            C0082e eVar = dVar.b;
            while (true) {
                eVar.a = 0;
                if (eVar.b == null) {
                    break;
                }
                eVar = eVar.b;
            }
            for (int size = dVar.c.size() - 1; size >= 0; size--) {
                dVar.a.d((View) dVar.c.get(size));
                dVar.c.remove(size);
            }
            dVar.a.b();
            this.l = u2;
            if (u2.b == null) {
                this.l.a(this);
                this.e.b();
                requestLayout();
                return;
            }
            throw new IllegalArgumentException("LayoutManager " + u2 + " is already attached to a RecyclerView:" + u2.b.a());
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(C0010ai aiVar) {
        View view = aiVar.itemView;
        boolean z2 = view.getParent() == this;
        this.e.b(d(view));
        if (aiVar.isTmpDetached()) {
            this.g.a(view, -1, view.getLayoutParams(), true);
            return;
        }
        C0081d dVar = this.g;
        if (!z2) {
            dVar.a(view, -1, true);
            return;
        }
        int a2 = dVar.a.a(view);
        if (a2 >= 0) {
            dVar.b.a(a2);
            dVar.a(view);
            return;
        }
        throw new IllegalArgumentException("view is not a child, cannot hide ".concat(String.valueOf(view)));
    }

    /* access modifiers changed from: package-private */
    public final void a(C0010ai aiVar, S s2) {
        aiVar.setFlags(0, 8192);
        if (this.u.h && aiVar.isUpdated() && !aiVar.isRemoved() && !aiVar.shouldIgnore()) {
            this.h.a(e(aiVar), aiVar);
        }
        this.h.a(aiVar, s2);
    }

    /* access modifiers changed from: package-private */
    public final void a(String str) {
        if (u()) {
            if (str == null) {
                throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling" + a());
            }
            throw new IllegalStateException(str);
        } else if (this.Q > 0) {
            Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException(a()));
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(boolean z2) {
        if (this.K <= 0) {
            this.K = 1;
        }
        if (!z2) {
            this.L = false;
        }
        if (this.K == 1) {
            if (z2 && this.L && this.l != null && this.k != null) {
                x();
            }
            this.L = false;
        }
        this.K--;
    }

    /* access modifiers changed from: package-private */
    public final boolean a(C0010ai aiVar, int i2) {
        if (u()) {
            aiVar.mPendingAccessibilityState = i2;
            this.ar.add(aiVar);
            return false;
        }
        ViewCompat.setImportantForAccessibility(aiVar.itemView, i2);
        return true;
    }

    public void addFocusables(ArrayList arrayList, int i2, int i3) {
        super.addFocusables(arrayList, i2, i3);
    }

    /* access modifiers changed from: package-private */
    public final void b() {
        Q q2 = this.r;
        if (q2 != null) {
            q2.d();
        }
        U u2 = this.l;
        if (u2 != null) {
            u2.c(this.e);
            this.l.b(this.e);
        }
        this.e.a();
    }

    /* access modifiers changed from: package-private */
    public final void b(int i2, int i3) {
        boolean z2;
        EdgeEffect edgeEffect = this.R;
        if (edgeEffect == null || edgeEffect.isFinished() || i2 <= 0) {
            z2 = false;
        } else {
            this.R.onRelease();
            z2 = this.R.isFinished();
        }
        EdgeEffect edgeEffect2 = this.T;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i2 < 0) {
            this.T.onRelease();
            z2 |= this.T.isFinished();
        }
        EdgeEffect edgeEffect3 = this.S;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i3 > 0) {
            this.S.onRelease();
            z2 |= this.S.isFinished();
        }
        EdgeEffect edgeEffect4 = this.U;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i3 < 0) {
            this.U.onRelease();
            z2 |= this.U.isFinished();
        }
        if (z2) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    /* access modifiers changed from: package-private */
    public final void b(boolean z2) {
        int i2 = this.P - 1;
        this.P = i2;
        if (i2 <= 0) {
            this.P = 0;
            if (z2) {
                t();
                J();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean b(C0010ai aiVar) {
        Q q2 = this.r;
        return q2 == null || q2.a(aiVar, aiVar.getUnmodifiedPayloads());
    }

    /* access modifiers changed from: package-private */
    public final void c() {
        if (!this.o || this.q) {
            TraceCompat.beginSection("RV FullInvalidate");
            x();
        } else if (this.f.d()) {
            if (this.f.a(4) && !this.f.a(11)) {
                TraceCompat.beginSection("RV PartialInvalidate");
                d();
                e();
                this.f.b();
                if (!this.L) {
                    int a2 = this.g.a();
                    boolean z2 = false;
                    int i2 = 0;
                    while (true) {
                        if (i2 < a2) {
                            C0010ai a3 = a(this.g.b(i2));
                            if (a3 != null && !a3.shouldIgnore() && a3.isUpdated()) {
                                z2 = true;
                                break;
                            }
                            i2++;
                        } else {
                            break;
                        }
                    }
                    if (z2) {
                        x();
                    } else {
                        this.f.c();
                    }
                }
                a(true);
                b(true);
            } else if (this.f.d()) {
                TraceCompat.beginSection("RV FullInvalidate");
                x();
                TraceCompat.endSection();
                return;
            } else {
                return;
            }
        } else {
            return;
        }
        TraceCompat.endSection();
    }

    /* access modifiers changed from: package-private */
    public final void c(int i2, int i3) {
        if (i2 < 0) {
            m();
            this.R.onAbsorb(-i2);
        } else if (i2 > 0) {
            n();
            this.T.onAbsorb(i2);
        }
        if (i3 < 0) {
            o();
            this.S.onAbsorb(-i3);
        } else if (i3 > 0) {
            p();
            this.U.onAbsorb(i3);
        }
        if (i2 != 0 || i3 != 0) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    /* access modifiers changed from: package-private */
    public final void c(View view) {
        C0010ai a2 = a(view);
        M m2 = this.k;
        if (m2 != null && a2 != null) {
            m2.onViewDetachedFromWindow(a2);
        }
    }

    /* access modifiers changed from: package-private */
    public final void c(boolean z2) {
        this.O = z2 | this.O;
        this.q = true;
        I();
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof Y) && U.a((Y) layoutParams);
    }

    public int computeHorizontalScrollExtent() {
        U u2 = this.l;
        if (u2 != null && u2.d()) {
            return this.l.d(this.u);
        }
        return 0;
    }

    public int computeHorizontalScrollOffset() {
        U u2 = this.l;
        if (u2 != null && u2.d()) {
            return this.l.b(this.u);
        }
        return 0;
    }

    public int computeHorizontalScrollRange() {
        U u2 = this.l;
        if (u2 != null && u2.d()) {
            return this.l.f(this.u);
        }
        return 0;
    }

    public int computeVerticalScrollExtent() {
        U u2 = this.l;
        if (u2 != null && u2.e()) {
            return this.l.e(this.u);
        }
        return 0;
    }

    public int computeVerticalScrollOffset() {
        U u2 = this.l;
        if (u2 != null && u2.e()) {
            return this.l.c(this.u);
        }
        return 0;
    }

    public int computeVerticalScrollRange() {
        U u2 = this.l;
        if (u2 != null && u2.e()) {
            return this.l.g(this.u);
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public final int d(C0010ai aiVar) {
        if (aiVar.hasAnyOfTheFlags(524) || !aiVar.isBound()) {
            return -1;
        }
        return this.f.c(aiVar.mPosition);
    }

    /* access modifiers changed from: package-private */
    public final void d() {
        int i2 = this.K + 1;
        this.K = i2;
        if (i2 == 1) {
            this.L = false;
        }
    }

    /* access modifiers changed from: package-private */
    public final void d(int i2, int i3) {
        setMeasuredDimension(U.a(i2, getPaddingLeft() + getPaddingRight(), ViewCompat.getMinimumWidth(this)), U.a(i3, getPaddingTop() + getPaddingBottom(), ViewCompat.getMinimumHeight(this)));
    }

    public boolean dispatchNestedFling(float f2, float f3, boolean z2) {
        return K().dispatchNestedFling(f2, f3, z2);
    }

    public boolean dispatchNestedPreFling(float f2, float f3) {
        return K().dispatchNestedPreFling(f2, f3);
    }

    public boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2) {
        return K().dispatchNestedPreScroll(i2, i3, iArr, iArr2);
    }

    public boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2, int i4) {
        return K().dispatchNestedPreScroll(i2, i3, iArr, iArr2, i4);
    }

    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr) {
        return K().dispatchNestedScroll(i2, i3, i4, i5, iArr);
    }

    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr, int i6) {
        return K().dispatchNestedScroll(i2, i3, i4, i5, iArr, i6);
    }

    /* access modifiers changed from: protected */
    public void dispatchRestoreInstanceState(SparseArray sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    /* access modifiers changed from: protected */
    public void dispatchSaveInstanceState(SparseArray sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    public void draw(Canvas canvas) {
        boolean z2;
        int i2;
        float f2;
        super.draw(canvas);
        int size = this.m.size();
        boolean z3 = false;
        for (int i3 = 0; i3 < size; i3++) {
            this.m.get(i3);
        }
        EdgeEffect edgeEffect = this.R;
        boolean z4 = true;
        if (edgeEffect == null || edgeEffect.isFinished()) {
            z2 = false;
        } else {
            int save = canvas.save();
            int paddingBottom = this.G ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((float) ((-getHeight()) + paddingBottom), 0.0f);
            EdgeEffect edgeEffect2 = this.R;
            z2 = edgeEffect2 != null && edgeEffect2.draw(canvas);
            canvas.restoreToCount(save);
        }
        EdgeEffect edgeEffect3 = this.S;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int save2 = canvas.save();
            if (this.G) {
                canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.S;
            z2 |= edgeEffect4 != null && edgeEffect4.draw(canvas);
            canvas.restoreToCount(save2);
        }
        EdgeEffect edgeEffect5 = this.T;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int save3 = canvas.save();
            int width = getWidth();
            int paddingTop = this.G ? getPaddingTop() : 0;
            canvas.rotate(90.0f);
            canvas.translate((float) (-paddingTop), (float) (-width));
            EdgeEffect edgeEffect6 = this.T;
            z2 |= edgeEffect6 != null && edgeEffect6.draw(canvas);
            canvas.restoreToCount(save3);
        }
        EdgeEffect edgeEffect7 = this.U;
        if (edgeEffect7 != null && !edgeEffect7.isFinished()) {
            int save4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.G) {
                f2 = (float) ((-getWidth()) + getPaddingRight());
                i2 = (-getHeight()) + getPaddingBottom();
            } else {
                f2 = (float) (-getWidth());
                i2 = -getHeight();
            }
            canvas.translate(f2, (float) i2);
            EdgeEffect edgeEffect8 = this.U;
            if (edgeEffect8 != null && edgeEffect8.draw(canvas)) {
                z3 = true;
            }
            z2 |= z3;
            canvas.restoreToCount(save4);
        }
        if (z2 || this.r == null || this.m.size() <= 0 || !this.r.b()) {
            z4 = z2;
        }
        if (z4) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public boolean drawChild(Canvas canvas, View view, long j2) {
        return super.drawChild(canvas, view, j2);
    }

    /* access modifiers changed from: package-private */
    public final void e() {
        this.P++;
    }

    /* access modifiers changed from: package-private */
    public final boolean f() {
        AccessibilityManager accessibilityManager = this.N;
        return accessibilityManager != null && accessibilityManager.isEnabled();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x01b8, code lost:
        if (r4 > 0) goto L_0x01d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x01bb, code lost:
        if (r5 > 0) goto L_0x01d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x01be, code lost:
        if (r4 < 0) goto L_0x01d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x01c1, code lost:
        if (r5 < 0) goto L_0x01d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x01ca, code lost:
        if ((r5 * r3) < 0) goto L_0x01d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x01d3, code lost:
        if ((r5 * r3) > 0) goto L_0x01d6;
     */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x01d9 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x01da  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View focusSearch(android.view.View r9, int r10) {
        /*
            r8 = this;
            com.dreamers.exoplayercore.repack.M r0 = r8.k
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0012
            com.dreamers.exoplayercore.repack.U r0 = r8.l
            if (r0 == 0) goto L_0x0012
            boolean r0 = r8.u()
            if (r0 != 0) goto L_0x0012
            r0 = 1
            goto L_0x0013
        L_0x0012:
            r0 = 0
        L_0x0013:
            android.view.FocusFinder r3 = android.view.FocusFinder.getInstance()
            r4 = 0
            if (r0 == 0) goto L_0x0090
            r5 = 2
            if (r10 == r5) goto L_0x001f
            if (r10 != r1) goto L_0x0090
        L_0x001f:
            com.dreamers.exoplayercore.repack.U r0 = r8.l
            boolean r0 = r0.e()
            if (r0 == 0) goto L_0x003d
            if (r10 != r5) goto L_0x002c
            r0 = 130(0x82, float:1.82E-43)
            goto L_0x002e
        L_0x002c:
            r0 = 33
        L_0x002e:
            android.view.View r6 = r3.findNextFocus(r8, r9, r0)
            if (r6 != 0) goto L_0x0036
            r6 = 1
            goto L_0x0037
        L_0x0036:
            r6 = 0
        L_0x0037:
            boolean r7 = C
            if (r7 == 0) goto L_0x003e
            r10 = r0
            goto L_0x003e
        L_0x003d:
            r6 = 0
        L_0x003e:
            if (r6 != 0) goto L_0x0070
            com.dreamers.exoplayercore.repack.U r0 = r8.l
            boolean r0 = r0.d()
            if (r0 == 0) goto L_0x0070
            com.dreamers.exoplayercore.repack.U r0 = r8.l
            com.dreamers.exoplayercore.repack.F r0 = r0.b
            int r0 = androidx.core.view.ViewCompat.getLayoutDirection(r0)
            if (r0 != r1) goto L_0x0054
            r0 = 1
            goto L_0x0055
        L_0x0054:
            r0 = 0
        L_0x0055:
            if (r10 != r5) goto L_0x0059
            r5 = 1
            goto L_0x005a
        L_0x0059:
            r5 = 0
        L_0x005a:
            r0 = r0 ^ r5
            if (r0 == 0) goto L_0x0060
            r0 = 66
            goto L_0x0062
        L_0x0060:
            r0 = 17
        L_0x0062:
            android.view.View r5 = r3.findNextFocus(r8, r9, r0)
            if (r5 != 0) goto L_0x006a
            r6 = 1
            goto L_0x006b
        L_0x006a:
            r6 = 0
        L_0x006b:
            boolean r5 = C
            if (r5 == 0) goto L_0x0070
            r10 = r0
        L_0x0070:
            if (r6 == 0) goto L_0x008b
            r8.c()
            android.view.View r0 = r8.e((android.view.View) r9)
            if (r0 != 0) goto L_0x007c
            return r4
        L_0x007c:
            r8.d()
            com.dreamers.exoplayercore.repack.U r0 = r8.l
            com.dreamers.exoplayercore.repack.ac r5 = r8.e
            com.dreamers.exoplayercore.repack.ag r6 = r8.u
            r0.c(r10, r5, r6)
            r8.a((boolean) r2)
        L_0x008b:
            android.view.View r0 = r3.findNextFocus(r8, r9, r10)
            goto L_0x00b4
        L_0x0090:
            android.view.View r3 = r3.findNextFocus(r8, r9, r10)
            if (r3 != 0) goto L_0x00b3
            if (r0 == 0) goto L_0x00b3
            r8.c()
            android.view.View r0 = r8.e((android.view.View) r9)
            if (r0 != 0) goto L_0x00a2
            return r4
        L_0x00a2:
            r8.d()
            com.dreamers.exoplayercore.repack.U r0 = r8.l
            com.dreamers.exoplayercore.repack.ac r3 = r8.e
            com.dreamers.exoplayercore.repack.ag r5 = r8.u
            android.view.View r0 = r0.c(r10, r3, r5)
            r8.a((boolean) r2)
            goto L_0x00b4
        L_0x00b3:
            r0 = r3
        L_0x00b4:
            if (r0 == 0) goto L_0x00cb
            boolean r3 = r0.hasFocusable()
            if (r3 != 0) goto L_0x00cb
            android.view.View r1 = r8.getFocusedChild()
            if (r1 != 0) goto L_0x00c7
            android.view.View r9 = super.focusSearch(r9, r10)
            return r9
        L_0x00c7:
            r8.a((android.view.View) r0, (android.view.View) r4)
            return r9
        L_0x00cb:
            if (r0 == 0) goto L_0x01d6
            if (r0 != r8) goto L_0x00d1
            goto L_0x01d6
        L_0x00d1:
            android.view.View r3 = r8.e((android.view.View) r0)
            if (r3 != 0) goto L_0x00d9
            goto L_0x01d6
        L_0x00d9:
            if (r9 != 0) goto L_0x00dd
            goto L_0x01d7
        L_0x00dd:
            android.view.View r3 = r8.e((android.view.View) r9)
            if (r3 != 0) goto L_0x00e5
            goto L_0x01d7
        L_0x00e5:
            android.graphics.Rect r3 = r8.i
            int r4 = r9.getWidth()
            int r5 = r9.getHeight()
            r3.set(r2, r2, r4, r5)
            android.graphics.Rect r3 = r8.H
            int r4 = r0.getWidth()
            int r5 = r0.getHeight()
            r3.set(r2, r2, r4, r5)
            android.graphics.Rect r3 = r8.i
            r8.offsetDescendantRectToMyCoords(r9, r3)
            android.graphics.Rect r3 = r8.H
            r8.offsetDescendantRectToMyCoords(r0, r3)
            com.dreamers.exoplayercore.repack.U r3 = r8.l
            com.dreamers.exoplayercore.repack.F r3 = r3.b
            int r3 = androidx.core.view.ViewCompat.getLayoutDirection(r3)
            r4 = -1
            if (r3 != r1) goto L_0x0116
            r3 = -1
            goto L_0x0117
        L_0x0116:
            r3 = 1
        L_0x0117:
            android.graphics.Rect r5 = r8.i
            int r5 = r5.left
            android.graphics.Rect r6 = r8.H
            int r6 = r6.left
            if (r5 < r6) goto L_0x012b
            android.graphics.Rect r5 = r8.i
            int r5 = r5.right
            android.graphics.Rect r6 = r8.H
            int r6 = r6.left
            if (r5 > r6) goto L_0x0137
        L_0x012b:
            android.graphics.Rect r5 = r8.i
            int r5 = r5.right
            android.graphics.Rect r6 = r8.H
            int r6 = r6.right
            if (r5 >= r6) goto L_0x0137
            r5 = 1
            goto L_0x0158
        L_0x0137:
            android.graphics.Rect r5 = r8.i
            int r5 = r5.right
            android.graphics.Rect r6 = r8.H
            int r6 = r6.right
            if (r5 > r6) goto L_0x014b
            android.graphics.Rect r5 = r8.i
            int r5 = r5.left
            android.graphics.Rect r6 = r8.H
            int r6 = r6.right
            if (r5 < r6) goto L_0x0157
        L_0x014b:
            android.graphics.Rect r5 = r8.i
            int r5 = r5.left
            android.graphics.Rect r6 = r8.H
            int r6 = r6.left
            if (r5 <= r6) goto L_0x0157
            r5 = -1
            goto L_0x0158
        L_0x0157:
            r5 = 0
        L_0x0158:
            android.graphics.Rect r6 = r8.i
            int r6 = r6.top
            android.graphics.Rect r7 = r8.H
            int r7 = r7.top
            if (r6 < r7) goto L_0x016c
            android.graphics.Rect r6 = r8.i
            int r6 = r6.bottom
            android.graphics.Rect r7 = r8.H
            int r7 = r7.top
            if (r6 > r7) goto L_0x0178
        L_0x016c:
            android.graphics.Rect r6 = r8.i
            int r6 = r6.bottom
            android.graphics.Rect r7 = r8.H
            int r7 = r7.bottom
            if (r6 >= r7) goto L_0x0178
            r4 = 1
            goto L_0x0198
        L_0x0178:
            android.graphics.Rect r6 = r8.i
            int r6 = r6.bottom
            android.graphics.Rect r7 = r8.H
            int r7 = r7.bottom
            if (r6 > r7) goto L_0x018c
            android.graphics.Rect r6 = r8.i
            int r6 = r6.top
            android.graphics.Rect r7 = r8.H
            int r7 = r7.bottom
            if (r6 < r7) goto L_0x0197
        L_0x018c:
            android.graphics.Rect r6 = r8.i
            int r6 = r6.top
            android.graphics.Rect r7 = r8.H
            int r7 = r7.top
            if (r6 <= r7) goto L_0x0197
            goto L_0x0198
        L_0x0197:
            r4 = 0
        L_0x0198:
            switch(r10) {
                case 1: goto L_0x01cd;
                case 2: goto L_0x01c4;
                case 17: goto L_0x01c1;
                case 33: goto L_0x01be;
                case 66: goto L_0x01bb;
                case 130: goto L_0x01b8;
                default: goto L_0x019b;
            }
        L_0x019b:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Invalid direction: "
            r0.<init>(r1)
            java.lang.StringBuilder r10 = r0.append(r10)
            java.lang.String r0 = r8.a()
            java.lang.StringBuilder r10 = r10.append(r0)
            java.lang.String r10 = r10.toString()
            r9.<init>(r10)
            throw r9
        L_0x01b8:
            if (r4 <= 0) goto L_0x01d6
            goto L_0x01d7
        L_0x01bb:
            if (r5 <= 0) goto L_0x01d6
            goto L_0x01d7
        L_0x01be:
            if (r4 >= 0) goto L_0x01d6
            goto L_0x01d7
        L_0x01c1:
            if (r5 >= 0) goto L_0x01d6
            goto L_0x01d7
        L_0x01c4:
            if (r4 > 0) goto L_0x01d7
            if (r4 != 0) goto L_0x01d6
            int r5 = r5 * r3
            if (r5 < 0) goto L_0x01d6
            goto L_0x01d7
        L_0x01cd:
            if (r4 < 0) goto L_0x01d7
            if (r4 != 0) goto L_0x01d6
            int r5 = r5 * r3
            if (r5 > 0) goto L_0x01d6
            goto L_0x01d7
        L_0x01d6:
            r1 = 0
        L_0x01d7:
            if (r1 == 0) goto L_0x01da
            return r0
        L_0x01da:
            android.view.View r9 = super.focusSearch(r9, r10)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dreamers.exoplayercore.repack.F.focusSearch(android.view.View, int):android.view.View");
    }

    /* access modifiers changed from: package-private */
    public final void g() {
        if (!this.x && this.n) {
            ViewCompat.postOnAnimation(this, this.as);
            this.x = true;
        }
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        U u2 = this.l;
        if (u2 != null) {
            return u2.b();
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + a());
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        if (this.l != null) {
            return U.a(getContext(), attributeSet);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + a());
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (this.l != null) {
            return U.a(layoutParams);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + a());
    }

    public int getBaseline() {
        if (this.l != null) {
            return -1;
        }
        return super.getBaseline();
    }

    /* access modifiers changed from: protected */
    public int getChildDrawingOrder(int i2, int i3) {
        return super.getChildDrawingOrder(i2, i3);
    }

    public boolean getClipToPadding() {
        return this.G;
    }

    /* access modifiers changed from: package-private */
    public final void h() {
        this.Q++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX, scrollY);
        this.Q--;
    }

    public boolean hasNestedScrollingParent() {
        return K().hasNestedScrollingParent();
    }

    public boolean hasNestedScrollingParent(int i2) {
        return K().hasNestedScrollingParent(i2);
    }

    public final boolean i() {
        return !this.o || this.q || this.f.d();
    }

    public boolean isAttachedToWindow() {
        return this.n;
    }

    public boolean isNestedScrollingEnabled() {
        return K().isNestedScrollingEnabled();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0044, code lost:
        if (r0 >= 30.0f) goto L_0x0049;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onAttachedToWindow() {
        /*
            r4 = this;
            super.onAttachedToWindow()
            r0 = 0
            r4.P = r0
            r1 = 1
            r4.n = r1
            boolean r2 = r4.o
            if (r2 == 0) goto L_0x0014
            boolean r2 = r4.isLayoutRequested()
            if (r2 != 0) goto L_0x0014
            goto L_0x0015
        L_0x0014:
            r1 = 0
        L_0x0015:
            r4.o = r1
            r4.x = r0
            boolean r0 = d
            if (r0 == 0) goto L_0x0060
            java.lang.ThreadLocal r0 = com.dreamers.exoplayercore.repack.C0095r.a
            java.lang.Object r0 = r0.get()
            com.dreamers.exoplayercore.repack.r r0 = (com.dreamers.exoplayercore.repack.C0095r) r0
            r4.s = r0
            if (r0 != 0) goto L_0x0059
            com.dreamers.exoplayercore.repack.r r0 = new com.dreamers.exoplayercore.repack.r
            r0.<init>()
            r4.s = r0
            android.view.Display r0 = androidx.core.view.ViewCompat.getDisplay(r4)
            boolean r1 = r4.isInEditMode()
            if (r1 != 0) goto L_0x0047
            if (r0 == 0) goto L_0x0047
            float r0 = r0.getRefreshRate()
            r1 = 1106247680(0x41f00000, float:30.0)
            int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r1 < 0) goto L_0x0047
            goto L_0x0049
        L_0x0047:
            r0 = 1114636288(0x42700000, float:60.0)
        L_0x0049:
            com.dreamers.exoplayercore.repack.r r1 = r4.s
            r2 = 1315859240(0x4e6e6b28, float:1.0E9)
            float r2 = r2 / r0
            long r2 = (long) r2
            r1.c = r2
            java.lang.ThreadLocal r0 = com.dreamers.exoplayercore.repack.C0095r.a
            com.dreamers.exoplayercore.repack.r r1 = r4.s
            r0.set(r1)
        L_0x0059:
            com.dreamers.exoplayercore.repack.r r0 = r4.s
            java.util.ArrayList r0 = r0.b
            r0.add(r4)
        L_0x0060:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dreamers.exoplayercore.repack.F.onAttachedToWindow():void");
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        C0095r rVar;
        super.onDetachedFromWindow();
        Q q2 = this.r;
        if (q2 != null) {
            q2.d();
        }
        k();
        this.n = false;
        this.ar.clear();
        removeCallbacks(this.as);
        C0019ar.b();
        if (d && (rVar = this.s) != null) {
            rVar.b.remove(this);
            this.s = null;
        }
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = this.m.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.m.get(i2);
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        float f2;
        float f3;
        if (this.l != null && motionEvent.getAction() == 8) {
            if ((motionEvent.getSource() & 2) != 0) {
                f3 = this.l.e() ? -motionEvent.getAxisValue(9) : 0.0f;
                if (this.l.d()) {
                    f2 = motionEvent.getAxisValue(10);
                    if (!(f3 == 0.0f && f2 == 0.0f)) {
                        a((int) (f2 * this.ai), (int) (f3 * this.aj), motionEvent);
                    }
                }
            } else {
                if ((motionEvent.getSource() & 4194304) != 0) {
                    f2 = motionEvent.getAxisValue(26);
                    if (this.l.e()) {
                        f3 = -f2;
                    } else if (this.l.d()) {
                        f3 = 0.0f;
                        a((int) (f2 * this.ai), (int) (f3 * this.aj), motionEvent);
                    }
                }
                f3 = 0.0f;
            }
            f2 = 0.0f;
            a((int) (f2 * this.ai), (int) (f3 * this.aj), motionEvent);
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z2;
        boolean z3;
        int action = motionEvent.getAction();
        if (action == 3 || action == 0) {
            this.J = null;
        }
        int size = this.I.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                z2 = false;
                break;
            }
            Z z4 = (Z) this.I.get(i2);
            if (z4.a() && action != 3) {
                this.J = z4;
                z2 = true;
                break;
            }
            i2++;
        }
        if (z2) {
            s();
            return true;
        }
        U u2 = this.l;
        if (u2 == null) {
            return false;
        }
        boolean d2 = u2.d();
        boolean e2 = this.l.e();
        if (this.aa == null) {
            this.aa = VelocityTracker.obtain();
        }
        this.aa.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        switch (actionMasked) {
            case 0:
                this.W = motionEvent.getPointerId(0);
                int x2 = (int) (motionEvent.getX() + 0.5f);
                this.ad = x2;
                this.ab = x2;
                int y2 = (int) (motionEvent.getY() + 0.5f);
                this.ae = y2;
                this.ac = y2;
                if (this.V == 2) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    a(1);
                }
                int[] iArr = this.aq;
                iArr[1] = 0;
                iArr[0] = 0;
                if (e2) {
                    d2 |= true;
                }
                startNestedScroll(d2 ? 1 : 0, 0);
                break;
            case 1:
                this.aa.clear();
                stopNestedScroll(0);
                break;
            case 2:
                int findPointerIndex = motionEvent.findPointerIndex(this.W);
                if (findPointerIndex >= 0) {
                    int x3 = (int) (motionEvent.getX(findPointerIndex) + 0.5f);
                    int y3 = (int) (motionEvent.getY(findPointerIndex) + 0.5f);
                    if (this.V != 1) {
                        int i3 = x3 - this.ab;
                        int i4 = y3 - this.ac;
                        if (!d2 || Math.abs(i3) <= this.af) {
                            z3 = false;
                        } else {
                            this.ad = x3;
                            z3 = true;
                        }
                        if (e2 && Math.abs(i4) > this.af) {
                            this.ae = y3;
                            z3 = true;
                        }
                        if (z3) {
                            a(1);
                            break;
                        }
                    }
                } else {
                    Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.W + " not found. Did any MotionEvents get skipped?");
                    return false;
                }
                break;
            case 3:
                s();
                break;
            case 5:
                this.W = motionEvent.getPointerId(actionIndex);
                int x4 = (int) (motionEvent.getX(actionIndex) + 0.5f);
                this.ad = x4;
                this.ab = x4;
                int y4 = (int) (motionEvent.getY(actionIndex) + 0.5f);
                this.ae = y4;
                this.ac = y4;
                break;
            case 6:
                a(motionEvent);
                break;
        }
        return this.V == 1;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        TraceCompat.beginSection("RV OnLayout");
        x();
        TraceCompat.endSection();
        this.o = true;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        U u2 = this.l;
        if (u2 == null) {
            d(i2, i3);
            return;
        }
        boolean z2 = false;
        if (u2.a()) {
            int mode = View.MeasureSpec.getMode(i2);
            int mode2 = View.MeasureSpec.getMode(i3);
            this.l.c(i2, i3);
            if (mode == 1073741824 && mode2 == 1073741824) {
                z2 = true;
            }
            if (!z2 && this.k != null) {
                if (this.u.d == 1) {
                    C();
                }
                this.l.a(i2, i3);
                this.u.i = true;
                D();
                this.l.b(i2, i3);
                if (this.l.f()) {
                    this.l.a(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), Declaration.MODULE_REFERENCE), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), Declaration.MODULE_REFERENCE));
                    this.u.i = true;
                    D();
                    this.l.b(i2, i3);
                    return;
                }
                return;
            }
            return;
        }
        if (this.p) {
            d();
            e();
            w();
            b(true);
            if (this.u.k) {
                this.u.g = true;
            } else {
                this.f.e();
                this.u.g = false;
            }
            this.p = false;
            a(false);
        } else if (this.u.k) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
            return;
        }
        M m2 = this.k;
        if (m2 != null) {
            this.u.e = m2.getItemCount();
        } else {
            this.u.e = 0;
        }
        d();
        this.l.c(i2, i3);
        a(false);
        this.u.g = false;
    }

    /* access modifiers changed from: protected */
    public boolean onRequestFocusInDescendants(int i2, Rect rect) {
        if (u()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i2, rect);
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof C0006ae)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        C0006ae aeVar = (C0006ae) parcelable;
        this.F = aeVar;
        super.onRestoreInstanceState(aeVar.getSuperState());
        if (this.l != null && this.F.a != null) {
            this.l.a(this.F.a);
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Parcelable c2;
        C0006ae aeVar = new C0006ae(super.onSaveInstanceState());
        C0006ae aeVar2 = this.F;
        if (aeVar2 != null) {
            c2 = aeVar2.a;
        } else {
            U u2 = this.l;
            c2 = u2 != null ? u2.c() : null;
        }
        aeVar.a = c2;
        return aeVar;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 != i4 || i3 != i5) {
            q();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:115:0x022c, code lost:
        if (r0 != false) goto L_0x0231;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0043  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r22) {
        /*
            r21 = this;
            r6 = r21
            r0 = r22
            int r1 = r22.getAction()
            com.dreamers.exoplayercore.repack.Z r2 = r6.J
            r7 = 1
            r8 = 0
            if (r2 == 0) goto L_0x001d
            r2 = 0
            if (r1 != 0) goto L_0x0014
            r6.J = r2
            goto L_0x001d
        L_0x0014:
            r3 = 3
            if (r1 == r3) goto L_0x0019
            if (r1 != r7) goto L_0x001b
        L_0x0019:
            r6.J = r2
        L_0x001b:
            r1 = 1
            goto L_0x003d
        L_0x001d:
            if (r1 == 0) goto L_0x003c
            java.util.ArrayList r1 = r6.I
            int r1 = r1.size()
            r2 = 0
        L_0x0026:
            if (r2 >= r1) goto L_0x003c
            java.util.ArrayList r3 = r6.I
            java.lang.Object r3 = r3.get(r2)
            com.dreamers.exoplayercore.repack.Z r3 = (com.dreamers.exoplayercore.repack.Z) r3
            boolean r4 = r3.a()
            if (r4 == 0) goto L_0x0039
            r6.J = r3
            goto L_0x001b
        L_0x0039:
            int r2 = r2 + 1
            goto L_0x0026
        L_0x003c:
            r1 = 0
        L_0x003d:
            if (r1 == 0) goto L_0x0043
            r21.s()
            return r7
        L_0x0043:
            com.dreamers.exoplayercore.repack.U r1 = r6.l
            if (r1 != 0) goto L_0x0048
            return r8
        L_0x0048:
            boolean r9 = r1.d()
            com.dreamers.exoplayercore.repack.U r1 = r6.l
            boolean r10 = r1.e()
            android.view.VelocityTracker r1 = r6.aa
            if (r1 != 0) goto L_0x005c
            android.view.VelocityTracker r1 = android.view.VelocityTracker.obtain()
            r6.aa = r1
        L_0x005c:
            android.view.MotionEvent r11 = android.view.MotionEvent.obtain(r22)
            int r1 = r22.getActionMasked()
            int r2 = r22.getActionIndex()
            if (r1 != 0) goto L_0x0070
            int[] r3 = r6.aq
            r3[r7] = r8
            r3[r8] = r8
        L_0x0070:
            int[] r3 = r6.aq
            r4 = r3[r8]
            float r4 = (float) r4
            r3 = r3[r7]
            float r3 = (float) r3
            r11.offsetLocation(r4, r3)
            java.lang.String r3 = "RecyclerView"
            r4 = 1056964608(0x3f000000, float:0.5)
            switch(r1) {
                case 0: goto L_0x0236;
                case 1: goto L_0x0174;
                case 2: goto L_0x00aa;
                case 3: goto L_0x00a5;
                case 4: goto L_0x0082;
                case 5: goto L_0x0089;
                case 6: goto L_0x0084;
                default: goto L_0x0082;
            }
        L_0x0082:
            goto L_0x0257
        L_0x0084:
            r21.a((android.view.MotionEvent) r22)
            goto L_0x0257
        L_0x0089:
            int r1 = r0.getPointerId(r2)
            r6.W = r1
            float r1 = r0.getX(r2)
            float r1 = r1 + r4
            int r1 = (int) r1
            r6.ad = r1
            r6.ab = r1
            float r0 = r0.getY(r2)
            float r0 = r0 + r4
            int r0 = (int) r0
            r6.ae = r0
            r6.ac = r0
            goto L_0x0257
        L_0x00a5:
            r21.s()
            goto L_0x0257
        L_0x00aa:
            int r1 = r6.W
            int r1 = r0.findPointerIndex(r1)
            if (r1 >= 0) goto L_0x00cd
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Error processing scroll; pointer index for id "
            r0.<init>(r1)
            int r1 = r6.W
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = " not found. Did any MotionEvents get skipped?"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r3, r0)
            return r8
        L_0x00cd:
            float r2 = r0.getX(r1)
            float r2 = r2 + r4
            int r12 = (int) r2
            float r0 = r0.getY(r1)
            float r0 = r0 + r4
            int r13 = (int) r0
            int r0 = r6.ad
            int r14 = r0 - r12
            int r0 = r6.ae
            int r15 = r0 - r13
            int[] r3 = r6.z
            int[] r4 = r6.ap
            r5 = 0
            r0 = r21
            r1 = r14
            r2 = r15
            boolean r0 = r0.dispatchNestedPreScroll(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0115
            int[] r0 = r6.z
            r1 = r0[r8]
            int r14 = r14 - r1
            r0 = r0[r7]
            int r15 = r15 - r0
            int[] r0 = r6.ap
            r1 = r0[r8]
            float r1 = (float) r1
            r0 = r0[r7]
            float r0 = (float) r0
            r11.offsetLocation(r1, r0)
            int[] r0 = r6.aq
            r1 = r0[r8]
            int[] r2 = r6.ap
            r3 = r2[r8]
            int r1 = r1 + r3
            r0[r8] = r1
            r1 = r0[r7]
            r2 = r2[r7]
            int r1 = r1 + r2
            r0[r7] = r1
        L_0x0115:
            int r0 = r6.V
            if (r0 == r7) goto L_0x0140
            if (r9 == 0) goto L_0x012a
            int r0 = java.lang.Math.abs(r14)
            int r1 = r6.af
            if (r0 <= r1) goto L_0x012a
            if (r14 <= 0) goto L_0x0127
            int r14 = r14 - r1
            goto L_0x0128
        L_0x0127:
            int r14 = r14 + r1
        L_0x0128:
            r0 = 1
            goto L_0x012b
        L_0x012a:
            r0 = 0
        L_0x012b:
            if (r10 == 0) goto L_0x013b
            int r1 = java.lang.Math.abs(r15)
            int r2 = r6.af
            if (r1 <= r2) goto L_0x013b
            if (r15 <= 0) goto L_0x0139
            int r15 = r15 - r2
            goto L_0x013a
        L_0x0139:
            int r15 = r15 + r2
        L_0x013a:
            r0 = 1
        L_0x013b:
            if (r0 == 0) goto L_0x0140
            r6.a((int) r7)
        L_0x0140:
            int r0 = r6.V
            if (r0 != r7) goto L_0x0257
            int[] r0 = r6.ap
            r1 = r0[r8]
            int r12 = r12 - r1
            r6.ad = r12
            r0 = r0[r7]
            int r13 = r13 - r0
            r6.ae = r13
            if (r9 == 0) goto L_0x0154
            r0 = r14
            goto L_0x0155
        L_0x0154:
            r0 = 0
        L_0x0155:
            if (r10 == 0) goto L_0x0159
            r1 = r15
            goto L_0x015a
        L_0x0159:
            r1 = 0
        L_0x015a:
            boolean r0 = r6.a((int) r0, (int) r1, (android.view.MotionEvent) r11)
            if (r0 == 0) goto L_0x0167
            android.view.ViewParent r0 = r21.getParent()
            r0.requestDisallowInterceptTouchEvent(r7)
        L_0x0167:
            com.dreamers.exoplayercore.repack.r r0 = r6.s
            if (r0 == 0) goto L_0x0257
            if (r14 != 0) goto L_0x016f
            if (r15 == 0) goto L_0x0257
        L_0x016f:
            r0.a((com.dreamers.exoplayercore.repack.F) r6, (int) r14, (int) r15)
            goto L_0x0257
        L_0x0174:
            android.view.VelocityTracker r0 = r6.aa
            r0.addMovement(r11)
            android.view.VelocityTracker r0 = r6.aa
            int r1 = r6.ah
            float r1 = (float) r1
            r2 = 1000(0x3e8, float:1.401E-42)
            r0.computeCurrentVelocity(r2, r1)
            r0 = 0
            if (r9 == 0) goto L_0x0190
            android.view.VelocityTracker r1 = r6.aa
            int r2 = r6.W
            float r1 = r1.getXVelocity(r2)
            float r1 = -r1
            goto L_0x0191
        L_0x0190:
            r1 = 0
        L_0x0191:
            if (r10 == 0) goto L_0x019d
            android.view.VelocityTracker r2 = r6.aa
            int r4 = r6.W
            float r2 = r2.getYVelocity(r4)
            float r2 = -r2
            goto L_0x019e
        L_0x019d:
            r2 = 0
        L_0x019e:
            int r4 = (r1 > r0 ? 1 : (r1 == r0 ? 0 : -1))
            if (r4 != 0) goto L_0x01a6
            int r0 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r0 == 0) goto L_0x022e
        L_0x01a6:
            int r0 = (int) r1
            int r1 = (int) r2
            com.dreamers.exoplayercore.repack.U r2 = r6.l
            if (r2 != 0) goto L_0x01b3
            java.lang.String r0 = "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument."
            android.util.Log.e(r3, r0)
            goto L_0x022b
        L_0x01b3:
            boolean r2 = r2.d()
            com.dreamers.exoplayercore.repack.U r3 = r6.l
            boolean r3 = r3.e()
            if (r2 == 0) goto L_0x01c7
            int r4 = java.lang.Math.abs(r0)
            int r5 = r6.ag
            if (r4 >= r5) goto L_0x01c8
        L_0x01c7:
            r0 = 0
        L_0x01c8:
            if (r3 == 0) goto L_0x01d2
            int r4 = java.lang.Math.abs(r1)
            int r5 = r6.ag
            if (r4 >= r5) goto L_0x01d3
        L_0x01d2:
            r1 = 0
        L_0x01d3:
            if (r0 != 0) goto L_0x01d7
            if (r1 == 0) goto L_0x022b
        L_0x01d7:
            float r4 = (float) r0
            float r5 = (float) r1
            boolean r9 = r6.dispatchNestedPreFling(r4, r5)
            if (r9 != 0) goto L_0x022b
            if (r2 != 0) goto L_0x01e6
            if (r3 == 0) goto L_0x01e4
            goto L_0x01e6
        L_0x01e4:
            r9 = 0
            goto L_0x01e7
        L_0x01e6:
            r9 = 1
        L_0x01e7:
            r6.dispatchNestedFling(r4, r5, r9)
            if (r9 == 0) goto L_0x022b
            if (r3 == 0) goto L_0x01f0
            r2 = r2 | 2
        L_0x01f0:
            r6.startNestedScroll(r2, r7)
            int r2 = r6.ah
            int r3 = -r2
            int r0 = java.lang.Math.min(r0, r2)
            int r15 = java.lang.Math.max(r3, r0)
            int r0 = r6.ah
            int r2 = -r0
            int r0 = java.lang.Math.min(r1, r0)
            int r16 = java.lang.Math.max(r2, r0)
            com.dreamers.exoplayercore.repack.ah r0 = r6.al
            com.dreamers.exoplayercore.repack.F r1 = r0.e
            r2 = 2
            r1.a((int) r2)
            r0.b = r8
            r0.a = r8
            android.widget.OverScroller r12 = r0.c
            r13 = 0
            r14 = 0
            r17 = -2147483648(0xffffffff80000000, float:-0.0)
            r18 = 2147483647(0x7fffffff, float:NaN)
            r19 = -2147483648(0xffffffff80000000, float:-0.0)
            r20 = 2147483647(0x7fffffff, float:NaN)
            r12.fling(r13, r14, r15, r16, r17, r18, r19, r20)
            r0.a()
            r0 = 1
            goto L_0x022c
        L_0x022b:
            r0 = 0
        L_0x022c:
            if (r0 != 0) goto L_0x0231
        L_0x022e:
            r6.a((int) r8)
        L_0x0231:
            r21.r()
            r8 = 1
            goto L_0x0257
        L_0x0236:
            int r1 = r0.getPointerId(r8)
            r6.W = r1
            float r1 = r22.getX()
            float r1 = r1 + r4
            int r1 = (int) r1
            r6.ad = r1
            r6.ab = r1
            float r0 = r22.getY()
            float r0 = r0 + r4
            int r0 = (int) r0
            r6.ae = r0
            r6.ac = r0
            if (r10 == 0) goto L_0x0254
            r9 = r9 | 2
        L_0x0254:
            r6.startNestedScroll(r9, r8)
        L_0x0257:
            if (r8 != 0) goto L_0x025e
            android.view.VelocityTracker r0 = r6.aa
            r0.addMovement(r11)
        L_0x025e:
            r11.recycle()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dreamers.exoplayercore.repack.F.onTouchEvent(android.view.MotionEvent):boolean");
    }

    /* access modifiers changed from: protected */
    public void removeDetachedView(View view, boolean z2) {
        C0010ai a2 = a(view);
        if (a2 != null) {
            if (a2.isTmpDetached()) {
                a2.clearTmpDetachFlag();
            } else if (!a2.shouldIgnore()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + a2 + a());
            }
        }
        view.clearAnimation();
        c(view);
        super.removeDetachedView(view, z2);
    }

    public void requestChildFocus(View view, View view2) {
        if (!u() && view2 != null) {
            a(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z2) {
        return this.l.a(this, view, rect, z2, false);
    }

    public void requestDisallowInterceptTouchEvent(boolean z2) {
        int size = this.I.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.I.get(i2);
        }
        super.requestDisallowInterceptTouchEvent(z2);
    }

    public void requestLayout() {
        if (this.K == 0) {
            super.requestLayout();
        } else {
            this.L = true;
        }
    }

    public void scrollBy(int i2, int i3) {
        U u2 = this.l;
        if (u2 == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        boolean d2 = u2.d();
        boolean e2 = this.l.e();
        if (d2 || e2) {
            if (!d2) {
                i2 = 0;
            }
            if (!e2) {
                i3 = 0;
            }
            a(i2, i3, (MotionEvent) null);
        }
    }

    public void scrollTo(int i2, int i3) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        int i2 = 0;
        if (u()) {
            int contentChangeTypes = accessibilityEvent != null ? AccessibilityEventCompat.getContentChangeTypes(accessibilityEvent) : 0;
            if (contentChangeTypes != 0) {
                i2 = contentChangeTypes;
            }
            this.M |= i2;
            i2 = 1;
        }
        if (i2 == 0) {
            super.sendAccessibilityEventUnchecked(accessibilityEvent);
        }
    }

    public void setClipToPadding(boolean z2) {
        if (z2 != this.G) {
            q();
        }
        this.G = z2;
        super.setClipToPadding(z2);
        if (this.o) {
            requestLayout();
        }
    }

    public void setNestedScrollingEnabled(boolean z2) {
        K().setNestedScrollingEnabled(z2);
    }

    public boolean startNestedScroll(int i2) {
        return K().startNestedScroll(i2);
    }

    public boolean startNestedScroll(int i2, int i3) {
        return K().startNestedScroll(i2, i3);
    }

    public void stopNestedScroll() {
        K().stopNestedScroll();
    }

    public void stopNestedScroll(int i2) {
        K().stopNestedScroll(i2);
    }
}
