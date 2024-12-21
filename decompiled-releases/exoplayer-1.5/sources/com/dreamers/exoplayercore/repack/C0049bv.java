package com.dreamers.exoplayercore.repack;

/* renamed from: com.dreamers.exoplayercore.repack.bv  reason: case insensitive filesystem */
final class C0049bv extends C0036bi {
    private final Object a;
    private int b;
    private /* synthetic */ C0042bo c;

    C0049bv(C0042bo boVar, int i) {
        this.c = boVar;
        this.a = boVar.a[i];
        this.b = i;
    }

    private void a() {
        int i = this.b;
        if (i == -1 || i >= this.c.size() || !P.a(this.a, this.c.a[this.b])) {
            this.b = this.c.a(this.a);
        }
    }

    public final Object getKey() {
        return this.a;
    }

    public final Object getValue() {
        a();
        if (this.b == -1) {
            return null;
        }
        return this.c.b[this.b];
    }

    public final Object setValue(Object obj) {
        a();
        if (this.b == -1) {
            this.c.put(this.a, obj);
            return null;
        }
        Object obj2 = this.c.b[this.b];
        this.c.b[this.b] = obj;
        return obj2;
    }
}
