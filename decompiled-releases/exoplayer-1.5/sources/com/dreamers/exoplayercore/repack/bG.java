package com.dreamers.exoplayercore.repack;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public abstract class bG extends bC implements List, RandomAccess {
    private static final cN a = new bI(C0076cv.a, 0);

    bG() {
    }

    public static bG a(Object obj) {
        Object[] a2 = C0000a.a(obj);
        return b(a2, a2.length);
    }

    public static bG a(Object obj, Object obj2) {
        Object[] a2 = C0000a.a(obj, obj2);
        return b(a2, a2.length);
    }

    public static bG a(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        Object[] a2 = C0000a.a(obj, obj2, obj3, obj4, obj5);
        return b(a2, a2.length);
    }

    public static bG a(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        Object[] a2 = C0000a.a(obj, obj2, obj3, obj4, obj5, obj6);
        return b(a2, a2.length);
    }

    public static bG a(Collection collection) {
        if (collection instanceof bC) {
            bG e = ((bC) collection).e();
            if (!e.f()) {
                return e;
            }
            Object[] array = e.toArray();
            return b(array, array.length);
        }
        Object[] a2 = C0000a.a(collection.toArray());
        return b(a2, a2.length);
    }

    static bG a(Object[] objArr) {
        return b(objArr, objArr.length);
    }

    static bG b(Object[] objArr, int i) {
        return i == 0 ? C0076cv.a : new C0076cv(objArr, i);
    }

    public static bG g() {
        return C0076cv.a;
    }

    public static bH i() {
        return new bH();
    }

    /* access modifiers changed from: package-private */
    public int a(Object[] objArr, int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            objArr[i + i2] = get(i2);
        }
        return i + size;
    }

    /* renamed from: a */
    public bG subList(int i, int i2) {
        C0000a.a(i, i2, size());
        int i3 = i2 - i;
        return i3 == size() ? this : i3 == 0 ? C0076cv.a : new bJ(this, i, i3);
    }

    public final cM a() {
        return listIterator(0);
    }

    /* renamed from: a */
    public final cN listIterator(int i) {
        C0000a.c(i, size());
        return isEmpty() ? a : new bI(this, i);
    }

    public final void add(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    public final boolean addAll(int i, Collection collection) {
        throw new UnsupportedOperationException();
    }

    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    public final bG e() {
        return this;
    }

    public boolean equals(Object obj) {
        if (obj == C0000a.a((Object) this)) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            int size = size();
            if (size == list.size()) {
                if (list instanceof RandomAccess) {
                    int i = 0;
                    while (i < size) {
                        if (P.a(get(i), list.get(i))) {
                            i++;
                        }
                    }
                    return true;
                }
                Iterator it = iterator();
                Iterator it2 = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it2.hasNext()) {
                            if (!P.a(it.next(), it2.next())) {
                                break;
                            }
                        } else {
                            break;
                        }
                    } else if (!it2.hasNext()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final cN h() {
        return listIterator(0);
    }

    public int hashCode() {
        int size = size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            i = (((i * 31) + get(i2).hashCode()) ^ -1) ^ -1;
        }
        return i;
    }

    public int indexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        int size = size();
        int i = 0;
        if (obj == null) {
            while (i < size) {
                if (get(i) == null) {
                    return i;
                }
                i++;
            }
            return -1;
        }
        while (i < size) {
            if (obj.equals(get(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public /* synthetic */ Iterator iterator() {
        return listIterator(0);
    }

    public int lastIndexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        if (obj == null) {
            for (int size = size() - 1; size >= 0; size--) {
                if (get(size) == null) {
                    return size;
                }
            }
            return -1;
        }
        for (int size2 = size() - 1; size2 >= 0; size2--) {
            if (obj.equals(get(size2))) {
                return size2;
            }
        }
        return -1;
    }

    public /* synthetic */ ListIterator listIterator() {
        return listIterator(0);
    }

    public final Object remove(int i) {
        throw new UnsupportedOperationException();
    }

    public final Object set(int i, Object obj) {
        throw new UnsupportedOperationException();
    }
}
