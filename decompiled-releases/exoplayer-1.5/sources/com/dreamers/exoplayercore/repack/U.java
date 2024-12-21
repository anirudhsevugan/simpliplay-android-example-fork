package com.dreamers.exoplayercore.repack;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.core.view.ViewCompat;
import gnu.expr.Declaration;

public abstract class U {
    C0081d a;
    F b;
    C0015an c;
    C0015an d;
    boolean e = true;
    int f;
    boolean g;
    int h;
    int i;
    int j;
    int k;
    private final C0017ap l;
    private final C0017ap m;
    private boolean n = true;

    public U() {
        V v = new V(this);
        this.l = v;
        W w = new W(this);
        this.m = w;
        this.c = new C0015an(v);
        this.d = new C0015an(w);
    }

    public static int a(int i2, int i3, int i4) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        switch (mode) {
            case Integer.MIN_VALUE:
                return Math.min(size, Math.max(i3, i4));
            case Declaration.MODULE_REFERENCE:
                return size;
            default:
                return Math.max(i3, i4);
        }
    }

    private static int a(int i2, int i3, int i4, int i5, boolean z) {
        int max = Math.max(0, i2 - i4);
        if (z) {
            if (i5 < 0) {
                if (i5 == -1) {
                    switch (i3) {
                        case Integer.MIN_VALUE:
                        case Declaration.MODULE_REFERENCE:
                            break;
                    }
                } else if (i5 == -2) {
                    i5 = 0;
                    i3 = 0;
                    return View.MeasureSpec.makeMeasureSpec(i5, i3);
                }
                i3 = 0;
                i5 = 0;
                return View.MeasureSpec.makeMeasureSpec(i5, i3);
            }
        } else if (i5 < 0) {
            if (i5 != -1) {
                if (i5 == -2) {
                    if (i3 == Integer.MIN_VALUE || i3 == 1073741824) {
                        i3 = Integer.MIN_VALUE;
                    } else {
                        i5 = max;
                        i3 = 0;
                        return View.MeasureSpec.makeMeasureSpec(i5, i3);
                    }
                }
                i3 = 0;
                i5 = 0;
                return View.MeasureSpec.makeMeasureSpec(i5, i3);
            }
            i5 = max;
            return View.MeasureSpec.makeMeasureSpec(i5, i3);
        }
        i3 = Declaration.MODULE_REFERENCE;
        return View.MeasureSpec.makeMeasureSpec(i5, i3);
    }

    public static Y a(Context context, AttributeSet attributeSet) {
        return new Y(context, attributeSet);
    }

    public static Y a(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof Y ? new Y((Y) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new Y((ViewGroup.MarginLayoutParams) layoutParams) : new Y(layoutParams);
    }

    private void a(Rect rect, int i2, int i3) {
        e(a(i2, rect.width() + j() + l(), ViewCompat.getMinimumWidth(this.b)), a(i3, rect.height() + k() + m(), ViewCompat.getMinimumHeight(this.b)));
    }

    private void a(View view, int i2) {
        Y y = (Y) view.getLayoutParams();
        C0010ai a2 = F.a(view);
        if (a2.isRemoved()) {
            this.b.h.c(a2);
        } else {
            this.b.h.d(a2);
        }
        this.a.a(view, i2, y, a2.isRemoved());
    }

    public static void a(View view, int i2, int i3, int i4, int i5) {
        Y y = (Y) view.getLayoutParams();
        Rect rect = y.b;
        view.layout(i2 + rect.left + y.leftMargin, i3 + rect.top + y.topMargin, (i4 - rect.right) - y.rightMargin, (i5 - rect.bottom) - y.bottomMargin);
    }

    private void a(View view, int i2, boolean z) {
        C0010ai a2 = F.a(view);
        if (z || a2.isRemoved()) {
            this.b.h.c(a2);
        } else {
            this.b.h.d(a2);
        }
        Y y = (Y) view.getLayoutParams();
        if (a2.wasReturnedFromScrap() || a2.isScrap()) {
            if (a2.isScrap()) {
                a2.unScrap();
            } else {
                a2.clearReturnedFromScrapFlag();
            }
            this.a.a(view, i2, view.getLayoutParams(), false);
        } else if (view.getParent() == this.b) {
            int c2 = this.a.c(view);
            if (i2 == -1) {
                i2 = this.a.a();
            }
            if (c2 == -1) {
                throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.b.indexOfChild(view) + this.b.a());
            } else if (c2 != i2) {
                this.b.l.d(c2, i2);
            }
        } else {
            this.a.a(view, i2, false);
            y.c = true;
        }
        if (y.d) {
            a2.itemView.invalidate();
            y.d = false;
        }
    }

    private boolean a(F f2, int i2, int i3) {
        View focusedChild = f2.getFocusedChild();
        if (focusedChild == null) {
            return false;
        }
        int j2 = j();
        int k2 = k();
        int l2 = this.j - l();
        int m2 = this.k - m();
        Rect rect = this.b.i;
        F.a(focusedChild, rect);
        return rect.left - i2 < l2 && rect.right - i2 > j2 && rect.top - i3 < m2 && rect.bottom - i3 > k2;
    }

    public static boolean a(Y y) {
        return y != null;
    }

    private static boolean b(int i2, int i3, int i4) {
        int mode = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i3);
        if (i4 > 0 && i2 != i4) {
            return false;
        }
        switch (mode) {
            case Integer.MIN_VALUE:
                return size >= i2;
            case 0:
                return true;
            case Declaration.MODULE_REFERENCE:
                return size == i2;
            default:
                return false;
        }
    }

    private int[] b(View view, Rect rect) {
        int[] iArr = new int[2];
        int j2 = j();
        int k2 = k();
        int l2 = this.j - l();
        int m2 = this.k - m();
        int left = (view.getLeft() + rect.left) - view.getScrollX();
        int top = (view.getTop() + rect.top) - view.getScrollY();
        int width = rect.width() + left;
        int height = rect.height() + top;
        int i2 = left - j2;
        int min = Math.min(0, i2);
        int i3 = top - k2;
        int min2 = Math.min(0, i3);
        int i4 = width - l2;
        int max = Math.max(0, i4);
        int max2 = Math.max(0, height - m2);
        if (ViewCompat.getLayoutDirection(this.b) != 1) {
            if (min == 0) {
                min = Math.min(i2, max);
            }
            max = min;
        } else if (max == 0) {
            max = Math.max(min, i4);
        }
        if (min2 == 0) {
            min2 = Math.min(i3, max2);
        }
        iArr[0] = max;
        iArr[1] = min2;
        return iArr;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = r3.a;
        r4 = r0.a(r4);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c(int r4) {
        /*
            r3 = this;
            android.view.View r0 = r3.b((int) r4)
            if (r0 == 0) goto L_0x0024
            com.dreamers.exoplayercore.repack.d r0 = r3.a
            int r4 = r0.a((int) r4)
            com.dreamers.exoplayercore.repack.f r1 = r0.a
            android.view.View r1 = r1.b((int) r4)
            if (r1 == 0) goto L_0x0024
            com.dreamers.exoplayercore.repack.e r2 = r0.b
            boolean r2 = r2.d(r4)
            if (r2 == 0) goto L_0x001f
            r0.b((android.view.View) r1)
        L_0x001f:
            com.dreamers.exoplayercore.repack.f r0 = r0.a
            r0.a((int) r4)
        L_0x0024:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dreamers.exoplayercore.repack.U.c(int):void");
    }

    private void d(int i2) {
        b(i2);
        this.a.d(i2);
    }

    private void d(int i2, int i3) {
        View b2 = b(i2);
        if (b2 != null) {
            d(i2);
            a(b2, i3);
            return;
        }
        throw new IllegalArgumentException("Cannot move a child from non-existing index:" + i2 + this.b.toString());
    }

    public static int e(View view) {
        return ((Y) view.getLayoutParams()).a.getLayoutPosition();
    }

    private void e(int i2, int i3) {
        this.b.setMeasuredDimension(i2, i3);
    }

    public static int g(View view) {
        Rect rect = ((Y) view.getLayoutParams()).b;
        return view.getMeasuredWidth() + rect.left + rect.right;
    }

    public static int h(View view) {
        Rect rect = ((Y) view.getLayoutParams()).b;
        return view.getMeasuredHeight() + rect.top + rect.bottom;
    }

    public static int i(View view) {
        return view.getLeft() - ((Y) view.getLayoutParams()).b.left;
    }

    public static int j(View view) {
        return view.getTop() - ((Y) view.getLayoutParams()).b.top;
    }

    public static int k(View view) {
        return view.getRight() + ((Y) view.getLayoutParams()).b.right;
    }

    public static int l(View view) {
        return view.getBottom() + ((Y) view.getLayoutParams()).b.bottom;
    }

    public int a(int i2, C0004ac acVar, C0008ag agVar) {
        return 0;
    }

    public View a(int i2) {
        int i3 = i();
        for (int i4 = 0; i4 < i3; i4++) {
            View b2 = b(i4);
            C0010ai a2 = F.a(b2);
            if (a2 != null && a2.getLayoutPosition() == i2 && !a2.shouldIgnore() && (this.b.u.g || !a2.isRemoved())) {
                return b2;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final void a(int i2, int i3) {
        this.j = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i2);
        this.h = mode;
        if (mode == 0 && !F.b) {
            this.j = 0;
        }
        this.k = View.MeasureSpec.getSize(i3);
        int mode2 = View.MeasureSpec.getMode(i3);
        this.i = mode2;
        if (mode2 == 0 && !F.b) {
            this.k = 0;
        }
    }

    public void a(int i2, int i3, C0008ag agVar, X x) {
    }

    public void a(int i2, X x) {
    }

    public final void a(int i2, C0004ac acVar) {
        View b2 = b(i2);
        c(i2);
        acVar.a(b2);
    }

    public void a(Parcelable parcelable) {
    }

    public final void a(View view) {
        a(view, -1, true);
    }

    public final void a(View view, Rect rect) {
        Matrix matrix;
        Rect rect2 = ((Y) view.getLayoutParams()).b;
        rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
        if (!(this.b == null || (matrix = view.getMatrix()) == null || matrix.isIdentity())) {
            RectF rectF = this.b.j;
            rectF.set(rect);
            matrix.mapRect(rectF);
            rect.set((int) Math.floor((double) rectF.left), (int) Math.floor((double) rectF.top), (int) Math.ceil((double) rectF.right), (int) Math.ceil((double) rectF.bottom));
        }
        rect.offset(view.getLeft(), view.getTop());
    }

    public void a(AccessibilityEvent accessibilityEvent) {
        F f2 = this.b;
        if (f2 != null && accessibilityEvent != null) {
            boolean z = true;
            if (!f2.canScrollVertically(1) && !this.b.canScrollVertically(-1) && !this.b.canScrollHorizontally(-1) && !this.b.canScrollHorizontally(1)) {
                z = false;
            }
            accessibilityEvent.setScrollable(z);
            if (this.b.k != null) {
                accessibilityEvent.setItemCount(this.b.k.getItemCount());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(F f2) {
        int i2;
        if (f2 == null) {
            this.b = null;
            this.a = null;
            i2 = 0;
            this.j = 0;
        } else {
            this.b = f2;
            this.a = f2.g;
            this.j = f2.getWidth();
            i2 = f2.getHeight();
        }
        this.k = i2;
        this.h = Declaration.MODULE_REFERENCE;
        this.i = Declaration.MODULE_REFERENCE;
    }

    public final void a(C0004ac acVar) {
        for (int i2 = i() - 1; i2 >= 0; i2--) {
            View b2 = b(i2);
            C0010ai a2 = F.a(b2);
            if (!a2.shouldIgnore()) {
                if (!a2.isInvalid() || a2.isRemoved() || this.b.k.hasStableIds()) {
                    d(i2);
                    acVar.c(b2);
                    this.b.h.d(a2);
                } else {
                    c(i2);
                    acVar.a(a2);
                }
            }
        }
    }

    public void a(C0004ac acVar, C0008ag agVar) {
        Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
    }

    public void a(C0008ag agVar) {
    }

    public void a(String str) {
        F f2 = this.b;
        if (f2 != null) {
            f2.a(str);
        }
    }

    public boolean a() {
        return false;
    }

    public final boolean a(F f2, View view, Rect rect, boolean z, boolean z2) {
        int[] b2 = b(view, rect);
        int i2 = b2[0];
        int i3 = b2[1];
        if ((z2 && !a(f2, i2, i3)) || (i2 == 0 && i3 == 0)) {
            return false;
        }
        if (z) {
            f2.scrollBy(i2, i3);
        } else {
            f2.a(i2, i3);
        }
        return true;
    }

    public int b(int i2, C0004ac acVar, C0008ag agVar) {
        return 0;
    }

    public int b(C0008ag agVar) {
        return 0;
    }

    public final View b(int i2) {
        C0081d dVar = this.a;
        if (dVar != null) {
            return dVar.b(i2);
        }
        return null;
    }

    public abstract Y b();

    /* access modifiers changed from: package-private */
    public final void b(int i2, int i3) {
        int i4 = i();
        if (i4 == 0) {
            this.b.d(i2, i3);
            return;
        }
        int i5 = Integer.MIN_VALUE;
        int i6 = Integer.MIN_VALUE;
        int i7 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        int i8 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        for (int i9 = 0; i9 < i4; i9++) {
            View b2 = b(i9);
            Rect rect = this.b.i;
            F.a(b2, rect);
            if (rect.left < i7) {
                i7 = rect.left;
            }
            if (rect.right > i5) {
                i5 = rect.right;
            }
            if (rect.top < i8) {
                i8 = rect.top;
            }
            if (rect.bottom > i6) {
                i6 = rect.bottom;
            }
        }
        this.b.i.set(i7, i8, i5, i6);
        a(this.b.i, i2, i3);
    }

    public final void b(View view) {
        a(view, 0, true);
    }

    /* access modifiers changed from: package-private */
    public final void b(F f2) {
        a(View.MeasureSpec.makeMeasureSpec(f2.getWidth(), Declaration.MODULE_REFERENCE), View.MeasureSpec.makeMeasureSpec(f2.getHeight(), Declaration.MODULE_REFERENCE));
    }

    /* access modifiers changed from: package-private */
    public final void b(C0004ac acVar) {
        int size = acVar.a.size();
        for (int i2 = size - 1; i2 >= 0; i2--) {
            View view = ((C0010ai) acVar.a.get(i2)).itemView;
            C0010ai a2 = F.a(view);
            if (!a2.shouldIgnore()) {
                a2.setIsRecyclable(false);
                if (a2.isTmpDetached()) {
                    this.b.removeDetachedView(view, false);
                }
                if (this.b.r != null) {
                    this.b.r.c(a2);
                }
                a2.setIsRecyclable(true);
                acVar.b(view);
            }
        }
        acVar.a.clear();
        if (acVar.b != null) {
            acVar.b.clear();
        }
        if (size > 0) {
            this.b.invalidate();
        }
    }

    public int c(C0008ag agVar) {
        return 0;
    }

    public Parcelable c() {
        return null;
    }

    public View c(int i2, C0004ac acVar, C0008ag agVar) {
        return null;
    }

    public final void c(int i2, int i3) {
        this.b.d(i2, i3);
    }

    public final void c(View view) {
        a(view, -1, false);
    }

    public final void c(C0004ac acVar) {
        for (int i2 = i() - 1; i2 >= 0; i2--) {
            if (!F.a(b(i2)).shouldIgnore()) {
                a(i2, acVar);
            }
        }
    }

    public int d(C0008ag agVar) {
        return 0;
    }

    public final void d(View view) {
        a(view, 0, false);
    }

    public boolean d() {
        return false;
    }

    public int e(C0008ag agVar) {
        return 0;
    }

    public boolean e() {
        return false;
    }

    public int f(C0008ag agVar) {
        return 0;
    }

    public final void f(View view) {
        Rect rect;
        Y y = (Y) view.getLayoutParams();
        F f2 = this.b;
        Y y2 = (Y) view.getLayoutParams();
        boolean z = false;
        if (y2.c && (!f2.u.g || (!y2.a.isUpdated() && !y2.a.isInvalid()))) {
            Rect rect2 = y2.b;
            rect2.set(0, 0, 0, 0);
            int size = f2.m.size();
            for (int i2 = 0; i2 < size; i2++) {
                f2.i.set(0, 0, 0, 0);
                f2.m.get(i2);
                Rect rect3 = f2.i;
                ((Y) view.getLayoutParams()).a.getLayoutPosition();
                rect3.set(0, 0, 0, 0);
                rect2.left += f2.i.left;
                rect2.top += f2.i.top;
                rect2.right += f2.i.right;
                rect2.bottom += f2.i.bottom;
            }
            y2.c = false;
            rect = rect2;
        } else {
            rect = y2.b;
        }
        int a2 = a(this.j, this.h, j() + l() + y.leftMargin + y.rightMargin + rect.left + rect.right + 0, y.width, d());
        int a3 = a(this.k, this.i, k() + m() + y.topMargin + y.bottomMargin + rect.top + rect.bottom + 0, y.height, e());
        if (view.isLayoutRequested() || !this.n || !b(view.getWidth(), a2, y.width) || !b(view.getHeight(), a3, y.height)) {
            z = true;
        }
        if (z) {
            view.measure(a2, a3);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean f() {
        return false;
    }

    public int g(C0008ag agVar) {
        return 0;
    }

    public boolean g() {
        return false;
    }

    public final void h() {
        F f2 = this.b;
        if (f2 != null) {
            f2.requestLayout();
        }
    }

    public final int i() {
        C0081d dVar = this.a;
        if (dVar != null) {
            return dVar.a();
        }
        return 0;
    }

    public final int j() {
        F f2 = this.b;
        if (f2 != null) {
            return f2.getPaddingLeft();
        }
        return 0;
    }

    public final int k() {
        F f2 = this.b;
        if (f2 != null) {
            return f2.getPaddingTop();
        }
        return 0;
    }

    public final int l() {
        F f2 = this.b;
        if (f2 != null) {
            return f2.getPaddingRight();
        }
        return 0;
    }

    public final int m() {
        F f2 = this.b;
        if (f2 != null) {
            return f2.getPaddingBottom();
        }
        return 0;
    }

    public final View n() {
        View focusedChild;
        F f2 = this.b;
        if (f2 == null || (focusedChild = f2.getFocusedChild()) == null || this.a.d(focusedChild)) {
            return null;
        }
        return focusedChild;
    }
}
