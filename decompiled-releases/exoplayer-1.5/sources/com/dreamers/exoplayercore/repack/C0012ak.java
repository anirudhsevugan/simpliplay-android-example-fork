package com.dreamers.exoplayercore.repack;

import android.os.Bundle;
import android.view.View;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

/* renamed from: com.dreamers.exoplayercore.repack.ak  reason: case insensitive filesystem */
public final class C0012ak extends AccessibilityDelegateCompat {
    private C0011aj a;

    public C0012ak(C0011aj ajVar) {
        this.a = ajVar;
    }

    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        if (!this.a.a.i() && this.a.a.l != null) {
            U u = this.a.a.l;
            C0010ai a2 = F.a(view);
            if (a2 != null && !a2.isRemoved() && !u.a.d(a2.itemView)) {
                accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(u.e() ? U.e(view) : 0, 1, u.d() ? U.e(view) : 0, 1, false, false));
            }
        }
    }

    public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        if (super.performAccessibilityAction(view, i, bundle)) {
            return true;
        }
        if (!this.a.a.i()) {
            U u = this.a.a.l;
        }
        return false;
    }
}
