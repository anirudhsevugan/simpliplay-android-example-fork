package com.dreamers.exoplayercore.repack;

import android.view.View;

final class T implements R {
    private /* synthetic */ F a;

    T(F f) {
        this.a = f;
    }

    public final void a(C0010ai aiVar) {
        boolean z = true;
        aiVar.setIsRecyclable(true);
        if (aiVar.mShadowedHolder != null && aiVar.mShadowingHolder == null) {
            aiVar.mShadowedHolder = null;
        }
        aiVar.mShadowingHolder = null;
        if (!aiVar.shouldBeKeptAsChild()) {
            F f = this.a;
            View view = aiVar.itemView;
            f.d();
            C0081d dVar = f.g;
            int a2 = dVar.a.a(view);
            if (a2 == -1) {
                dVar.b(view);
            } else if (dVar.b.c(a2)) {
                dVar.b.d(a2);
                dVar.b(view);
                dVar.a.a(a2);
            } else {
                z = false;
            }
            if (z) {
                C0010ai a3 = F.a(view);
                f.e.b(a3);
                f.e.a(a3);
            }
            f.a(!z);
            if (!z && aiVar.isTmpDetached()) {
                this.a.removeDetachedView(aiVar.itemView, false);
            }
        }
    }
}
