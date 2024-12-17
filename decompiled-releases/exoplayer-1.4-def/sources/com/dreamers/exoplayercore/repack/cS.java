package com.dreamers.exoplayercore.repack;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

final class cS extends AbstractList implements Serializable, RandomAccess {
    private int[] a;
    private int b;
    private int c;

    private cS(int[] iArr, int i, int i2) {
        this.a = iArr;
        this.b = i;
        this.c = i2;
    }

    /* access modifiers changed from: package-private */
    public final int[] a() {
        return Arrays.copyOfRange(this.a, this.b, this.c);
    }

    public final boolean contains(Object obj) {
        return (obj instanceof Integer) && cR.a(this.a, ((Integer) obj).intValue(), this.b, this.c) != -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof cS)) {
            return super.equals(obj);
        }
        cS cSVar = (cS) obj;
        int size = size();
        if (cSVar.size() != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (this.a[this.b + i] != cSVar.a[cSVar.b + i]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        C0000a.b(i, size());
        return Integer.valueOf(this.a[this.b + i]);
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = this.b; i2 < this.c; i2++) {
            i = (i * 31) + this.a[i2];
        }
        return i;
    }

    public final int indexOf(Object obj) {
        int a2;
        if (!(obj instanceof Integer) || (a2 = cR.a(this.a, ((Integer) obj).intValue(), this.b, this.c)) < 0) {
            return -1;
        }
        return a2 - this.b;
    }

    public final boolean isEmpty() {
        return false;
    }

    public final int lastIndexOf(Object obj) {
        if (obj instanceof Integer) {
            int[] iArr = this.a;
            int intValue = ((Integer) obj).intValue();
            int i = this.b;
            int i2 = this.c - 1;
            while (true) {
                if (i2 < i) {
                    i2 = -1;
                    break;
                } else if (iArr[i2] == intValue) {
                    break;
                } else {
                    i2--;
                }
            }
            if (i2 >= 0) {
                return i2 - this.b;
            }
        }
        return -1;
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        C0000a.b(i, size());
        int[] iArr = this.a;
        int i2 = this.b;
        int i3 = iArr[i2 + i];
        iArr[i2 + i] = ((Integer) C0000a.a((Object) (Integer) obj)).intValue();
        return Integer.valueOf(i3);
    }

    public final int size() {
        return this.c - this.b;
    }

    public final List subList(int i, int i2) {
        C0000a.a(i, i2, size());
        if (i == i2) {
            return Collections.emptyList();
        }
        int[] iArr = this.a;
        int i3 = this.b;
        return new cS(iArr, i + i3, i3 + i2);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(size() * 5);
        sb.append('[').append(this.a[this.b]);
        int i = this.b;
        while (true) {
            i++;
            if (i >= this.c) {
                return sb.append(']').toString();
            }
            sb.append(", ").append(this.a[i]);
        }
    }
}
