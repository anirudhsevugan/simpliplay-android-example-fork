package com.dreamers.exoplayercore.repack;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;

public abstract class bC extends AbstractCollection implements Serializable {
    private static final Object[] a = new Object[0];

    bC() {
    }

    /* access modifiers changed from: package-private */
    public int a(Object[] objArr, int i) {
        cM a2 = iterator();
        while (a2.hasNext()) {
            objArr[i] = a2.next();
            i++;
        }
        return i;
    }

    /* renamed from: a */
    public abstract cM iterator();

    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public Object[] b() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public int c() {
        throw new UnsupportedOperationException();
    }

    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public abstract boolean contains(Object obj);

    /* access modifiers changed from: package-private */
    public int d() {
        throw new UnsupportedOperationException();
    }

    public bG e() {
        return isEmpty() ? bG.g() : bG.a(toArray());
    }

    /* access modifiers changed from: package-private */
    public abstract boolean f();

    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public final boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public final Object[] toArray() {
        return toArray(a);
    }

    public final Object[] toArray(Object[] objArr) {
        C0000a.a((Object) objArr);
        int size = size();
        if (objArr.length < size) {
            Object[] b = b();
            if (b != null) {
                return Arrays.copyOfRange(b, c(), d(), objArr.getClass());
            }
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), size);
        } else if (objArr.length > size) {
            objArr[size] = null;
        }
        a(objArr, 0);
        return objArr;
    }
}
