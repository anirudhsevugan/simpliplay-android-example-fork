package com.dreamers.exoplayercore.repack;

import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.dreamers.exoplayercore.repack.h  reason: case insensitive filesystem */
final class C0085h implements Runnable {
    private /* synthetic */ ArrayList a;
    private /* synthetic */ C0084g b;

    C0085h(C0084g gVar, ArrayList arrayList) {
        this.b = gVar;
        this.a = arrayList;
    }

    public final void run() {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            C0094q qVar = (C0094q) it.next();
            C0084g gVar = this.b;
            C0010ai aiVar = qVar.a;
            int i = qVar.b;
            int i2 = qVar.c;
            int i3 = qVar.d;
            int i4 = qVar.e;
            View view = aiVar.itemView;
            int i5 = i3 - i;
            int i6 = i4 - i2;
            if (i5 != 0) {
                view.animate().translationX(0.0f);
            }
            if (i6 != 0) {
                view.animate().translationY(0.0f);
            }
            ViewPropertyAnimator animate = view.animate();
            gVar.e.add(aiVar);
            animate.setDuration(gVar.j).setListener(new C0090m(gVar, aiVar, i5, view, i6, animate)).start();
        }
        this.a.clear();
        this.b.b.remove(this.a);
    }
}
