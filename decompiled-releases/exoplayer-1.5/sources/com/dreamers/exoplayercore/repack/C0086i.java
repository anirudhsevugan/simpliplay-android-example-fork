package com.dreamers.exoplayercore.repack;

import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.dreamers.exoplayercore.repack.i  reason: case insensitive filesystem */
final class C0086i implements Runnable {
    private /* synthetic */ ArrayList a;
    private /* synthetic */ C0084g b;

    C0086i(C0084g gVar, ArrayList arrayList) {
        this.b = gVar;
        this.a = arrayList;
    }

    public final void run() {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            C0093p pVar = (C0093p) it.next();
            C0084g gVar = this.b;
            C0010ai aiVar = pVar.a;
            View view = null;
            View view2 = aiVar == null ? null : aiVar.itemView;
            C0010ai aiVar2 = pVar.b;
            if (aiVar2 != null) {
                view = aiVar2.itemView;
            }
            if (view2 != null) {
                ViewPropertyAnimator duration = view2.animate().setDuration(gVar.k);
                gVar.g.add(pVar.a);
                duration.translationX((float) (pVar.e - pVar.c));
                duration.translationY((float) (pVar.f - pVar.d));
                duration.alpha(0.0f).setListener(new C0091n(gVar, pVar, duration, view2)).start();
            }
            if (view != null) {
                ViewPropertyAnimator animate = view.animate();
                gVar.g.add(pVar.b);
                animate.translationX(0.0f).translationY(0.0f).setDuration(gVar.k).alpha(1.0f).setListener(new C0092o(gVar, pVar, animate, view)).start();
            }
        }
        this.a.clear();
        this.b.c.remove(this.a);
    }
}
