package com.dreamers.exoplayercore.repack;

final class cB extends bU {
    static final cB a = new cB(new Object[0], 0, (Object[]) null, 0, 0);
    private transient Object[] b;
    private transient Object[] c;
    private final transient int d;
    private final transient int e;
    private final transient int f;

    cB(Object[] objArr, int i, Object[] objArr2, int i2, int i3) {
        this.b = objArr;
        this.c = objArr2;
        this.d = i2;
        this.e = i;
        this.f = i3;
    }

    /* access modifiers changed from: package-private */
    public final int a(Object[] objArr, int i) {
        System.arraycopy(this.b, 0, objArr, i, this.f);
        return i + this.f;
    }

    /* renamed from: a */
    public final cM iterator() {
        return e().listIterator(0);
    }

    /* access modifiers changed from: package-private */
    public final Object[] b() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public final int c() {
        return 0;
    }

    public final boolean contains(Object obj) {
        Object[] objArr = this.c;
        if (obj == null || objArr == null) {
            return false;
        }
        int b2 = C0000a.b(obj);
        while (true) {
            int i = b2 & this.d;
            Object obj2 = objArr[i];
            if (obj2 == null) {
                return false;
            }
            if (obj2.equals(obj)) {
                return true;
            }
            b2 = i + 1;
        }
    }

    /* access modifiers changed from: package-private */
    public final int d() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public final boolean f() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean g() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public final bG h() {
        return bG.b(this.b, this.f);
    }

    public final int hashCode() {
        return this.e;
    }

    public final int size() {
        return this.f;
    }
}
