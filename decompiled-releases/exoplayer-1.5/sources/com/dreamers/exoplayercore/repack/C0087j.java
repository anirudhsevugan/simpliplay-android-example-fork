package com.dreamers.exoplayercore.repack;

import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.dreamers.exoplayercore.repack.j  reason: case insensitive filesystem */
final class C0087j implements Runnable {
    private /* synthetic */ ArrayList a;
    private /* synthetic */ C0084g b;

    C0087j(C0084g gVar, ArrayList arrayList) {
        this.b = gVar;
        this.a = arrayList;
    }

    public final void run() {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            C0010ai aiVar = (C0010ai) it.next();
            C0084g gVar = this.b;
            View view = aiVar.itemView;
            ViewPropertyAnimator animate = view.animate();
            gVar.d.add(aiVar);
            animate.alpha(1.0f).setDuration(gVar.i).setListener(new C0089l(gVar, aiVar, view, animate)).start();
        }
        this.a.clear();
        this.b.a.remove(this.a);
    }
}
