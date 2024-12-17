package com.dreamers.exoplayercore.repack;

final class cK extends bU {
    private transient Object a;
    private transient int b;

    cK(Object obj) {
        this.a = C0000a.a(obj);
    }

    cK(Object obj, int i) {
        this.a = obj;
        this.b = i;
    }

    /* access modifiers changed from: package-private */
    public final int a(Object[] objArr, int i) {
        objArr[i] = this.a;
        return i + 1;
    }

    /* renamed from: a */
    public final cM iterator() {
        return new bW(this.a);
    }

    public final boolean contains(Object obj) {
        return this.a.equals(obj);
    }

    /* access modifiers changed from: package-private */
    public final boolean f() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean g() {
        return this.b != 0;
    }

    /* access modifiers changed from: package-private */
    public final bG h() {
        return bG.a(this.a);
    }

    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int hashCode = this.a.hashCode();
        this.b = hashCode;
        return hashCode;
    }

    public final int size() {
        return 1;
    }

    public final String toString() {
        return "[" + this.a.toString() + ']';
    }
}
