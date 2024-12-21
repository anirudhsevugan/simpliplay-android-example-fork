package com.dreamers.exoplayercore.repack;

import java.util.Comparator;

/* renamed from: com.dreamers.exoplayercore.repack.cu  reason: case insensitive filesystem */
public abstract class C0075cu implements Comparator {
    protected C0075cu() {
    }

    public static C0075cu a(Comparator comparator) {
        return comparator instanceof C0075cu ? (C0075cu) comparator : new C0051bx(comparator);
    }

    public static C0075cu b() {
        return C0074ct.a;
    }

    public C0075cu a() {
        return new cD(this);
    }

    public abstract int compare(Object obj, Object obj2);
}
