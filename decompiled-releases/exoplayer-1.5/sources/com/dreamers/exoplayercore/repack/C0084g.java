package com.dreamers.exoplayercore.repack;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.dreamers.exoplayercore.repack.g  reason: case insensitive filesystem */
public final class C0084g extends C0014am {
    private static TimeInterpolator l;
    ArrayList a = new ArrayList();
    ArrayList b = new ArrayList();
    ArrayList c = new ArrayList();
    ArrayList d = new ArrayList();
    ArrayList e = new ArrayList();
    ArrayList f = new ArrayList();
    ArrayList g = new ArrayList();
    private ArrayList m = new ArrayList();
    private ArrayList n = new ArrayList();
    private ArrayList o = new ArrayList();
    private ArrayList p = new ArrayList();

    private void a(C0093p pVar) {
        if (pVar.a != null) {
            a(pVar, pVar.a);
        }
        if (pVar.b != null) {
            a(pVar, pVar.b);
        }
    }

    private static void a(List list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            ((C0010ai) list.get(size)).itemView.animate().cancel();
        }
    }

    private void a(List list, C0010ai aiVar) {
        for (int size = list.size() - 1; size >= 0; size--) {
            C0093p pVar = (C0093p) list.get(size);
            if (a(pVar, aiVar) && pVar.a == null && pVar.b == null) {
                list.remove(pVar);
            }
        }
    }

    private boolean a(C0093p pVar, C0010ai aiVar) {
        if (pVar.b == aiVar) {
            pVar.b = null;
        } else if (pVar.a != aiVar) {
            return false;
        } else {
            pVar.a = null;
        }
        aiVar.itemView.setAlpha(1.0f);
        aiVar.itemView.setTranslationX(0.0f);
        aiVar.itemView.setTranslationY(0.0f);
        f(aiVar);
        return true;
    }

    private void h(C0010ai aiVar) {
        if (l == null) {
            l = new ValueAnimator().getInterpolator();
        }
        aiVar.itemView.animate().setInterpolator(l);
        c(aiVar);
    }

    public final void a() {
        boolean z = !this.m.isEmpty();
        boolean z2 = !this.o.isEmpty();
        boolean z3 = !this.p.isEmpty();
        boolean z4 = !this.n.isEmpty();
        if (z || z2 || z4 || z3) {
            Iterator it = this.m.iterator();
            while (it.hasNext()) {
                C0010ai aiVar = (C0010ai) it.next();
                View view = aiVar.itemView;
                ViewPropertyAnimator animate = view.animate();
                this.f.add(aiVar);
                animate.setDuration(e()).alpha(0.0f).setListener(new C0088k(this, aiVar, animate, view)).start();
            }
            this.m.clear();
            if (z2) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(this.o);
                this.b.add(arrayList);
                this.o.clear();
                C0085h hVar = new C0085h(this, arrayList);
                if (z) {
                    ViewCompat.postOnAnimationDelayed(((C0094q) arrayList.get(0)).a.itemView, hVar, e());
                } else {
                    hVar.run();
                }
            }
            if (z3) {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(this.p);
                this.c.add(arrayList2);
                this.p.clear();
                C0086i iVar = new C0086i(this, arrayList2);
                if (z) {
                    ViewCompat.postOnAnimationDelayed(((C0093p) arrayList2.get(0)).a.itemView, iVar, e());
                } else {
                    iVar.run();
                }
            }
            if (z4) {
                ArrayList arrayList3 = new ArrayList();
                arrayList3.addAll(this.n);
                this.a.add(arrayList3);
                this.n.clear();
                C0087j jVar = new C0087j(this, arrayList3);
                if (z || z2 || z3) {
                    long j = 0;
                    long e2 = z ? e() : 0;
                    long j2 = z2 ? this.j : 0;
                    if (z3) {
                        j = this.k;
                    }
                    ViewCompat.postOnAnimationDelayed(((C0010ai) arrayList3.get(0)).itemView, jVar, e2 + Math.max(j2, j));
                    return;
                }
                jVar.run();
            }
        }
    }

    public final boolean a(C0010ai aiVar) {
        h(aiVar);
        this.m.add(aiVar);
        return true;
    }

    public final boolean a(C0010ai aiVar, int i, int i2, int i3, int i4) {
        View view = aiVar.itemView;
        int translationX = i + ((int) aiVar.itemView.getTranslationX());
        int translationY = i2 + ((int) aiVar.itemView.getTranslationY());
        h(aiVar);
        int i5 = i3 - translationX;
        int i6 = i4 - translationY;
        if (i5 == 0 && i6 == 0) {
            f(aiVar);
            return false;
        }
        if (i5 != 0) {
            view.setTranslationX((float) (-i5));
        }
        if (i6 != 0) {
            view.setTranslationY((float) (-i6));
        }
        this.o.add(new C0094q(aiVar, translationX, translationY, i3, i4));
        return true;
    }

    public final boolean a(C0010ai aiVar, C0010ai aiVar2, int i, int i2, int i3, int i4) {
        if (aiVar == aiVar2) {
            return a(aiVar, i, i2, i3, i4);
        }
        float translationX = aiVar.itemView.getTranslationX();
        float translationY = aiVar.itemView.getTranslationY();
        float alpha = aiVar.itemView.getAlpha();
        h(aiVar);
        int i5 = (int) (((float) (i3 - i)) - translationX);
        int i6 = (int) (((float) (i4 - i2)) - translationY);
        aiVar.itemView.setTranslationX(translationX);
        aiVar.itemView.setTranslationY(translationY);
        aiVar.itemView.setAlpha(alpha);
        if (aiVar2 != null) {
            h(aiVar2);
            aiVar2.itemView.setTranslationX((float) (-i5));
            aiVar2.itemView.setTranslationY((float) (-i6));
            aiVar2.itemView.setAlpha(0.0f);
        }
        this.p.add(new C0093p(aiVar, aiVar2, i, i2, i3, i4));
        return true;
    }

    public final boolean a(C0010ai aiVar, List list) {
        return !list.isEmpty() || super.a(aiVar, list);
    }

    public final boolean b() {
        return !this.n.isEmpty() || !this.p.isEmpty() || !this.o.isEmpty() || !this.m.isEmpty() || !this.e.isEmpty() || !this.f.isEmpty() || !this.d.isEmpty() || !this.g.isEmpty() || !this.b.isEmpty() || !this.a.isEmpty() || !this.c.isEmpty();
    }

    public final boolean b(C0010ai aiVar) {
        h(aiVar);
        aiVar.itemView.setAlpha(0.0f);
        this.n.add(aiVar);
        return true;
    }

    /* access modifiers changed from: package-private */
    public final void c() {
        if (!b()) {
            f();
        }
    }

    public final void c(C0010ai aiVar) {
        View view = aiVar.itemView;
        view.animate().cancel();
        int size = this.o.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            } else if (((C0094q) this.o.get(size)).a == aiVar) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                f(aiVar);
                this.o.remove(size);
            }
        }
        a((List) this.p, aiVar);
        if (this.m.remove(aiVar)) {
            view.setAlpha(1.0f);
            f(aiVar);
        }
        if (this.n.remove(aiVar)) {
            view.setAlpha(1.0f);
            f(aiVar);
        }
        for (int size2 = this.c.size() - 1; size2 >= 0; size2--) {
            ArrayList arrayList = (ArrayList) this.c.get(size2);
            a((List) arrayList, aiVar);
            if (arrayList.isEmpty()) {
                this.c.remove(size2);
            }
        }
        for (int size3 = this.b.size() - 1; size3 >= 0; size3--) {
            ArrayList arrayList2 = (ArrayList) this.b.get(size3);
            int size4 = arrayList2.size() - 1;
            while (true) {
                if (size4 < 0) {
                    break;
                } else if (((C0094q) arrayList2.get(size4)).a == aiVar) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    f(aiVar);
                    arrayList2.remove(size4);
                    if (arrayList2.isEmpty()) {
                        this.b.remove(size3);
                    }
                } else {
                    size4--;
                }
            }
        }
        for (int size5 = this.a.size() - 1; size5 >= 0; size5--) {
            ArrayList arrayList3 = (ArrayList) this.a.get(size5);
            if (arrayList3.remove(aiVar)) {
                view.setAlpha(1.0f);
                f(aiVar);
                if (arrayList3.isEmpty()) {
                    this.a.remove(size5);
                }
            }
        }
        this.f.remove(aiVar);
        this.d.remove(aiVar);
        this.g.remove(aiVar);
        this.e.remove(aiVar);
        c();
    }

    public final void d() {
        int size = this.o.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            C0094q qVar = (C0094q) this.o.get(size);
            View view = qVar.a.itemView;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            f(qVar.a);
            this.o.remove(size);
        }
        for (int size2 = this.m.size() - 1; size2 >= 0; size2--) {
            f((C0010ai) this.m.get(size2));
            this.m.remove(size2);
        }
        int size3 = this.n.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            C0010ai aiVar = (C0010ai) this.n.get(size3);
            aiVar.itemView.setAlpha(1.0f);
            f(aiVar);
            this.n.remove(size3);
        }
        for (int size4 = this.p.size() - 1; size4 >= 0; size4--) {
            a((C0093p) this.p.get(size4));
        }
        this.p.clear();
        if (b()) {
            for (int size5 = this.b.size() - 1; size5 >= 0; size5--) {
                ArrayList arrayList = (ArrayList) this.b.get(size5);
                for (int size6 = arrayList.size() - 1; size6 >= 0; size6--) {
                    C0094q qVar2 = (C0094q) arrayList.get(size6);
                    View view2 = qVar2.a.itemView;
                    view2.setTranslationY(0.0f);
                    view2.setTranslationX(0.0f);
                    f(qVar2.a);
                    arrayList.remove(size6);
                    if (arrayList.isEmpty()) {
                        this.b.remove(arrayList);
                    }
                }
            }
            for (int size7 = this.a.size() - 1; size7 >= 0; size7--) {
                ArrayList arrayList2 = (ArrayList) this.a.get(size7);
                for (int size8 = arrayList2.size() - 1; size8 >= 0; size8--) {
                    C0010ai aiVar2 = (C0010ai) arrayList2.get(size8);
                    aiVar2.itemView.setAlpha(1.0f);
                    f(aiVar2);
                    arrayList2.remove(size8);
                    if (arrayList2.isEmpty()) {
                        this.a.remove(arrayList2);
                    }
                }
            }
            for (int size9 = this.c.size() - 1; size9 >= 0; size9--) {
                ArrayList arrayList3 = (ArrayList) this.c.get(size9);
                for (int size10 = arrayList3.size() - 1; size10 >= 0; size10--) {
                    a((C0093p) arrayList3.get(size10));
                    if (arrayList3.isEmpty()) {
                        this.c.remove(arrayList3);
                    }
                }
            }
            a((List) this.f);
            a((List) this.e);
            a((List) this.d);
            a((List) this.g);
            f();
        }
    }
}
