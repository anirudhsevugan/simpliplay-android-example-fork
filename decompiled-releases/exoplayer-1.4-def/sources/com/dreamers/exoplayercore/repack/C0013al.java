package com.dreamers.exoplayercore.repack;

import android.view.View;

/* renamed from: com.dreamers.exoplayercore.repack.al  reason: case insensitive filesystem */
final class C0013al {
    static int a(C0008ag agVar, D d, View view, View view2, U u, boolean z) {
        if (u.i() == 0 || agVar.a() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return Math.abs(U.e(view) - U.e(view2)) + 1;
        }
        return Math.min(d.e(), d.b(view2) - d.a(view));
    }

    static int a(C0008ag agVar, D d, View view, View view2, U u, boolean z, boolean z2) {
        if (u.i() == 0 || agVar.a() == 0 || view == null || view2 == null) {
            return 0;
        }
        int max = z2 ? Math.max(0, (agVar.a() - Math.max(U.e(view), U.e(view2))) - 1) : Math.max(0, Math.min(U.e(view), U.e(view2)));
        if (!z) {
            return max;
        }
        return Math.round((((float) max) * (((float) Math.abs(d.b(view2) - d.a(view))) / ((float) (Math.abs(U.e(view) - U.e(view2)) + 1)))) + ((float) (d.b() - d.a(view))));
    }

    static int b(C0008ag agVar, D d, View view, View view2, U u, boolean z) {
        if (u.i() == 0 || agVar.a() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return agVar.a();
        }
        return (int) ((((float) (d.b(view2) - d.a(view))) / ((float) (Math.abs(U.e(view) - U.e(view2)) + 1))) * ((float) agVar.a()));
    }
}
