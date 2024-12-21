package com.dreamers.exoplayercore.repack;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

/* renamed from: com.dreamers.exoplayercore.repack.aj  reason: case insensitive filesystem */
public final class C0011aj extends AccessibilityDelegateCompat {
    final F a;
    final AccessibilityDelegateCompat b = new C0012ak(this);

    public C0011aj(F f) {
        this.a = f;
    }

    public final void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        accessibilityEvent.setClassName(F.class.getName());
        if ((view instanceof F) && !this.a.i()) {
            F f = (F) view;
            if (f.l != null) {
                f.l.a(accessibilityEvent);
            }
        }
    }

    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        accessibilityNodeInfoCompat.setClassName(F.class.getName());
        if (!this.a.i() && this.a.l != null) {
            U u = this.a.l;
            int i = 1;
            if (u.b.canScrollVertically(-1) || u.b.canScrollHorizontally(-1)) {
                accessibilityNodeInfoCompat.addAction(8192);
                accessibilityNodeInfoCompat.setScrollable(true);
            }
            if (u.b.canScrollVertically(1) || u.b.canScrollHorizontally(1)) {
                accessibilityNodeInfoCompat.addAction(4096);
                accessibilityNodeInfoCompat.setScrollable(true);
            }
            int itemCount = (u.b == null || u.b.k == null || !u.e()) ? 1 : u.b.k.getItemCount();
            if (!(u.b == null || u.b.k == null || !u.d())) {
                i = u.b.k.getItemCount();
            }
            accessibilityNodeInfoCompat.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(itemCount, i, false, 0));
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean performAccessibilityAction(android.view.View r4, int r5, android.os.Bundle r6) {
        /*
            r3 = this;
            boolean r4 = super.performAccessibilityAction(r4, r5, r6)
            r6 = 1
            if (r4 == 0) goto L_0x0008
            return r6
        L_0x0008:
            com.dreamers.exoplayercore.repack.F r4 = r3.a
            boolean r4 = r4.i()
            r0 = 0
            if (r4 != 0) goto L_0x0089
            com.dreamers.exoplayercore.repack.F r4 = r3.a
            com.dreamers.exoplayercore.repack.U r4 = r4.l
            if (r4 == 0) goto L_0x0089
            com.dreamers.exoplayercore.repack.F r4 = r3.a
            com.dreamers.exoplayercore.repack.U r4 = r4.l
            com.dreamers.exoplayercore.repack.F r1 = r4.b
            if (r1 != 0) goto L_0x0020
            return r0
        L_0x0020:
            switch(r5) {
                case 4096: goto L_0x0054;
                case 8192: goto L_0x0026;
                default: goto L_0x0023;
            }
        L_0x0023:
            r5 = 0
        L_0x0024:
            r1 = 0
            goto L_0x007e
        L_0x0026:
            com.dreamers.exoplayercore.repack.F r5 = r4.b
            r1 = -1
            boolean r5 = r5.canScrollVertically(r1)
            if (r5 == 0) goto L_0x003d
            int r5 = r4.k
            int r2 = r4.k()
            int r5 = r5 - r2
            int r2 = r4.m()
            int r5 = r5 - r2
            int r5 = -r5
            goto L_0x003e
        L_0x003d:
            r5 = 0
        L_0x003e:
            com.dreamers.exoplayercore.repack.F r2 = r4.b
            boolean r1 = r2.canScrollHorizontally(r1)
            if (r1 == 0) goto L_0x0024
            int r1 = r4.j
            int r2 = r4.j()
            int r1 = r1 - r2
            int r2 = r4.l()
            int r1 = r1 - r2
            int r1 = -r1
            goto L_0x007e
        L_0x0054:
            com.dreamers.exoplayercore.repack.F r5 = r4.b
            boolean r5 = r5.canScrollVertically(r6)
            if (r5 == 0) goto L_0x0069
            int r5 = r4.k
            int r1 = r4.k()
            int r5 = r5 - r1
            int r1 = r4.m()
            int r5 = r5 - r1
            goto L_0x006a
        L_0x0069:
            r5 = 0
        L_0x006a:
            com.dreamers.exoplayercore.repack.F r1 = r4.b
            boolean r1 = r1.canScrollHorizontally(r6)
            if (r1 == 0) goto L_0x0024
            int r1 = r4.j
            int r2 = r4.j()
            int r1 = r1 - r2
            int r2 = r4.l()
            int r1 = r1 - r2
        L_0x007e:
            if (r5 != 0) goto L_0x0083
            if (r1 != 0) goto L_0x0083
            return r0
        L_0x0083:
            com.dreamers.exoplayercore.repack.F r4 = r4.b
            r4.a((int) r1, (int) r5)
            return r6
        L_0x0089:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dreamers.exoplayercore.repack.C0011aj.performAccessibilityAction(android.view.View, int, android.os.Bundle):boolean");
    }
}
