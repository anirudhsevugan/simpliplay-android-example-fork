package com.dreamers.exoplayercore.repack;

import gnu.expr.Declaration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.SortedSet;

public abstract class bU extends bC implements Set {
    private transient bG a;

    bU() {
    }

    static int a(int i) {
        int max = Math.max(i, 2);
        boolean z = true;
        if (max < 751619276) {
            int highestOneBit = Integer.highestOneBit(max - 1) << 1;
            while (true) {
                double d = (double) highestOneBit;
                Double.isNaN(d);
                if (d * 0.7d >= ((double) max)) {
                    return highestOneBit;
                }
                highestOneBit <<= 1;
            }
        } else {
            if (max >= 1073741824) {
                z = false;
            }
            C0000a.a(z, (Object) "collection too large");
            return Declaration.MODULE_REFERENCE;
        }
    }

    private static bU a(int i, Object... objArr) {
        while (true) {
            switch (i) {
                case 0:
                    return cB.a;
                case 1:
                    return a(objArr[0]);
                default:
                    int a2 = a(i);
                    Object[] objArr2 = new Object[a2];
                    int i2 = a2 - 1;
                    int i3 = 0;
                    int i4 = 0;
                    for (int i5 = 0; i5 < i; i5++) {
                        Object a3 = C0000a.a(objArr[i5], i5);
                        int hashCode = a3.hashCode();
                        int d = C0000a.d(hashCode);
                        while (true) {
                            int i6 = d & i2;
                            Object obj = objArr2[i6];
                            if (obj == null) {
                                objArr[i4] = a3;
                                objArr2[i6] = a3;
                                i3 += hashCode;
                                i4++;
                            } else if (!obj.equals(a3)) {
                                d++;
                            }
                        }
                    }
                    Arrays.fill(objArr, i4, i, (Object) null);
                    if (i4 == 1) {
                        return new cK(objArr[0], i3);
                    }
                    if (a(i4) < a2 / 2) {
                        i = i4;
                    } else {
                        if (a(i4, objArr.length)) {
                            objArr = Arrays.copyOf(objArr, i4);
                        }
                        return new cB(objArr, i3, objArr2, i2, i4);
                    }
            }
        }
    }

    private static bU a(Object obj) {
        return new cK(obj);
    }

    public static bU a(Object obj, Object obj2) {
        return a(2, obj, obj2);
    }

    public static bU a(Object obj, Object obj2, Object obj3) {
        return a(3, obj, obj2, obj3);
    }

    public static bU a(Collection collection) {
        if ((collection instanceof bU) && !(collection instanceof SortedSet)) {
            bU bUVar = (bU) collection;
            if (!bUVar.f()) {
                return bUVar;
            }
        }
        Object[] array = collection.toArray();
        return a(array.length, array);
    }

    public static bU a(Object[] objArr) {
        switch (objArr.length) {
            case 0:
                return cB.a;
            case 1:
                return a(objArr[0]);
            default:
                return a(objArr.length, (Object[]) objArr.clone());
        }
    }

    private static boolean a(int i, int i2) {
        return i < (i2 >> 1) + (i2 >> 2);
    }

    /* renamed from: a */
    public abstract cM iterator();

    public bG e() {
        bG bGVar = this.a;
        if (bGVar != null) {
            return bGVar;
        }
        bG h = h();
        this.a = h;
        return h;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof bU) || !g() || !((bU) obj).g() || hashCode() == obj.hashCode()) {
            return cF.a((Set) this, obj);
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean g() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public bG h() {
        return bG.a(toArray());
    }

    public int hashCode() {
        return cF.a(this);
    }
}
