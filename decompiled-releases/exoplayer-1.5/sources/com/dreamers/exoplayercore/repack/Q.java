package com.dreamers.exoplayercore.repack;

import java.util.ArrayList;
import java.util.List;

public abstract class Q {
    private ArrayList a = new ArrayList();
    private long b = 120;
    R h = null;
    long i = 120;
    long j = 250;
    long k = 250;

    public static S d(C0010ai aiVar) {
        return new S().a(aiVar);
    }

    static int e(C0010ai aiVar) {
        int i2 = aiVar.mFlags & 14;
        if (aiVar.isInvalid()) {
            return 4;
        }
        if ((i2 & 4) != 0) {
            return i2;
        }
        int oldPosition = aiVar.getOldPosition();
        int adapterPosition = aiVar.getAdapterPosition();
        return (oldPosition == -1 || adapterPosition == -1 || oldPosition == adapterPosition) ? i2 : i2 | 2048;
    }

    public abstract void a();

    public abstract boolean a(C0010ai aiVar, S s, S s2);

    public abstract boolean a(C0010ai aiVar, C0010ai aiVar2, S s, S s2);

    public boolean a(C0010ai aiVar, List list) {
        return g(aiVar);
    }

    public abstract boolean b();

    public abstract boolean b(C0010ai aiVar, S s, S s2);

    public abstract void c(C0010ai aiVar);

    public abstract boolean c(C0010ai aiVar, S s, S s2);

    public abstract void d();

    public final long e() {
        return this.b;
    }

    public final void f() {
        int size = this.a.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.a.get(i2);
        }
        this.a.clear();
    }

    public final void f(C0010ai aiVar) {
        R r = this.h;
        if (r != null) {
            r.a(aiVar);
        }
    }

    public boolean g(C0010ai aiVar) {
        return true;
    }
}
