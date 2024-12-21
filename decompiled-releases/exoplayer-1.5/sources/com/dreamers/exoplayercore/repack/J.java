package com.dreamers.exoplayercore.repack;

import android.view.View;

final class J implements C0020as {
    private /* synthetic */ F a;

    J(F f) {
        this.a = f;
    }

    public final void a(C0010ai aiVar) {
        U u = this.a.l;
        View view = aiVar.itemView;
        C0004ac acVar = this.a.e;
        C0081d dVar = u.a;
        int a2 = dVar.a.a(view);
        if (a2 >= 0) {
            if (dVar.b.d(a2)) {
                dVar.b(view);
            }
            dVar.a.a(a2);
        }
        acVar.a(view);
    }

    public final void a(C0010ai aiVar, S s, S s2) {
        this.a.e.b(aiVar);
        F f = this.a;
        f.a(aiVar);
        aiVar.setIsRecyclable(false);
        if (f.r.a(aiVar, s, s2)) {
            f.g();
        }
    }

    public final void b(C0010ai aiVar, S s, S s2) {
        F f = this.a;
        aiVar.setIsRecyclable(false);
        if (f.r.b(aiVar, s, s2)) {
            f.g();
        }
    }

    public final void c(C0010ai aiVar, S s, S s2) {
        aiVar.setIsRecyclable(false);
        if (this.a.q) {
            if (this.a.r.a(aiVar, aiVar, s, s2)) {
                this.a.g();
            }
        } else if (this.a.r.c(aiVar, s, s2)) {
            this.a.g();
        }
    }
}
