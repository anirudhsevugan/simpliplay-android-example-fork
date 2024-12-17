package com.dreamers.exoplayercore.repack;

import androidx.appcompat.widget.ActivityChooserView;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

/* renamed from: com.dreamers.exoplayercore.repack.bo  reason: case insensitive filesystem */
public final class C0042bo extends AbstractMap implements Serializable {
    transient Object[] a;
    transient Object[] b;
    transient int c;
    /* access modifiers changed from: package-private */
    public transient int d;
    private transient int[] e;
    private transient long[] f;
    private transient float g;
    private transient int h;
    private transient Set i;
    private transient Set j;
    private transient Collection k;

    C0042bo() {
        c(3);
    }

    private C0042bo(int i2) {
        this(i2, (byte) 0);
    }

    private C0042bo(int i2, byte b2) {
        c(i2);
    }

    /* access modifiers changed from: private */
    public int a(Object obj) {
        int b2 = C0000a.b(obj);
        int[] iArr = this.e;
        int i2 = iArr[(iArr.length - 1) & b2];
        while (i2 != -1) {
            long j2 = this.f[i2];
            if (((int) (j2 >>> 32)) == b2 && P.a(obj, this.a[i2])) {
                return i2;
            }
            i2 = (int) j2;
        }
        return -1;
    }

    private static long a(long j2, int i2) {
        return (j2 & -4294967296L) | (4294967295L & ((long) i2));
    }

    public static C0042bo a() {
        return new C0042bo();
    }

    public static C0042bo a(int i2) {
        return new C0042bo(i2);
    }

    /* access modifiers changed from: private */
    public Object a(Object obj, int i2) {
        int[] iArr = this.e;
        int length = (iArr.length - 1) & i2;
        int i3 = iArr[length];
        if (i3 == -1) {
            return null;
        }
        int i4 = -1;
        while (true) {
            if (((int) (this.f[i3] >>> 32)) != i2 || !P.a(obj, this.a[i3])) {
                int i5 = (int) this.f[i3];
                if (i5 == -1) {
                    return null;
                }
                int i6 = i5;
                i4 = i3;
                i3 = i6;
            } else {
                Object obj2 = this.b[i3];
                if (i4 == -1) {
                    this.e[length] = (int) this.f[i3];
                } else {
                    long[] jArr = this.f;
                    jArr[i4] = a(jArr[i4], (int) jArr[i3]);
                }
                g(i3);
                this.d--;
                this.c++;
                return obj2;
            }
        }
    }

    static int b(int i2) {
        return i2 - 1;
    }

    private void c(int i2) {
        C0000a.a(i2 >= 0, (Object) "Initial capacity must be non-negative");
        C0000a.a(true, (Object) "Illegal load factor");
        int e2 = C0000a.e(i2);
        this.e = d(e2);
        this.g = 1.0f;
        this.a = new Object[i2];
        this.b = new Object[i2];
        this.f = e(i2);
        this.h = Math.max(1, (int) ((float) e2));
    }

    private static int[] d(int i2) {
        int[] iArr = new int[i2];
        Arrays.fill(iArr, -1);
        return iArr;
    }

    private static long[] e(int i2) {
        long[] jArr = new long[i2];
        Arrays.fill(jArr, -1);
        return jArr;
    }

    private void f(int i2) {
        if (this.e.length >= 1073741824) {
            this.h = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            return;
        }
        int i3 = ((int) (((float) i2) * this.g)) + 1;
        int[] d2 = d(i2);
        long[] jArr = this.f;
        int length = d2.length - 1;
        for (int i4 = 0; i4 < this.d; i4++) {
            int i5 = (int) (jArr[i4] >>> 32);
            int i6 = i5 & length;
            int i7 = d2[i6];
            d2[i6] = i4;
            jArr[i4] = (((long) i5) << 32) | (((long) i7) & 4294967295L);
        }
        this.h = i3;
        this.e = d2;
    }

    private void g(int i2) {
        int size = size() - 1;
        if (i2 < size) {
            Object[] objArr = this.a;
            objArr[i2] = objArr[size];
            Object[] objArr2 = this.b;
            objArr2[i2] = objArr2[size];
            objArr[size] = null;
            objArr2[size] = null;
            long[] jArr = this.f;
            long j2 = jArr[size];
            jArr[i2] = j2;
            jArr[size] = -1;
            int[] iArr = this.e;
            int length = ((int) (j2 >>> 32)) & (iArr.length - 1);
            int i3 = iArr[length];
            if (i3 == size) {
                iArr[length] = i2;
                return;
            }
            while (true) {
                long[] jArr2 = this.f;
                long j3 = jArr2[i3];
                int i4 = (int) j3;
                if (i4 == size) {
                    jArr2[i3] = a(j3, i2);
                    return;
                }
                i3 = i4;
            }
        } else {
            this.a[i2] = null;
            this.b[i2] = null;
            this.f[i2] = -1;
        }
    }

    public final void clear() {
        this.c++;
        Arrays.fill(this.a, 0, this.d, (Object) null);
        Arrays.fill(this.b, 0, this.d, (Object) null);
        Arrays.fill(this.e, -1);
        Arrays.fill(this.f, -1);
        this.d = 0;
    }

    public final boolean containsKey(Object obj) {
        return a(obj) != -1;
    }

    public final boolean containsValue(Object obj) {
        for (int i2 = 0; i2 < this.d; i2++) {
            if (P.a(obj, this.b[i2])) {
                return true;
            }
        }
        return false;
    }

    public final Set entrySet() {
        Set set = this.j;
        if (set != null) {
            return set;
        }
        C0046bs bsVar = new C0046bs(this);
        this.j = bsVar;
        return bsVar;
    }

    public final Object get(Object obj) {
        int a2 = a(obj);
        if (a2 == -1) {
            return null;
        }
        return this.b[a2];
    }

    public final boolean isEmpty() {
        return this.d == 0;
    }

    public final Set keySet() {
        Set set = this.i;
        if (set != null) {
            return set;
        }
        C0048bu buVar = new C0048bu(this);
        this.i = buVar;
        return buVar;
    }

    public final Object put(Object obj, Object obj2) {
        long[] jArr = this.f;
        Object[] objArr = this.a;
        Object[] objArr2 = this.b;
        int b2 = C0000a.b(obj);
        int[] iArr = this.e;
        int length = (iArr.length - 1) & b2;
        int i2 = this.d;
        int i3 = iArr[length];
        if (i3 == -1) {
            iArr[length] = i2;
        } else {
            while (true) {
                long j2 = jArr[i3];
                if (((int) (j2 >>> 32)) != b2 || !P.a(obj, objArr[i3])) {
                    int i4 = (int) j2;
                    if (i4 == -1) {
                        jArr[i3] = a(j2, i2);
                        break;
                    }
                    i3 = i4;
                } else {
                    Object obj3 = objArr2[i3];
                    objArr2[i3] = obj2;
                    return obj3;
                }
            }
        }
        int i5 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        if (i2 != Integer.MAX_VALUE) {
            int i6 = i2 + 1;
            int length2 = this.f.length;
            if (i6 > length2) {
                int max = Math.max(1, length2 >>> 1) + length2;
                if (max >= 0) {
                    i5 = max;
                }
                if (i5 != length2) {
                    this.a = Arrays.copyOf(this.a, i5);
                    this.b = Arrays.copyOf(this.b, i5);
                    long[] jArr2 = this.f;
                    int length3 = jArr2.length;
                    long[] copyOf = Arrays.copyOf(jArr2, i5);
                    if (i5 > length3) {
                        Arrays.fill(copyOf, length3, i5, -1);
                    }
                    this.f = copyOf;
                }
            }
            this.f[i2] = (((long) b2) << 32) | 4294967295L;
            this.a[i2] = obj;
            this.b[i2] = obj2;
            this.d = i6;
            if (i2 >= this.h) {
                f(this.e.length * 2);
            }
            this.c++;
            return null;
        }
        throw new IllegalStateException("Cannot contain more than Integer.MAX_VALUE elements!");
    }

    public final Object remove(Object obj) {
        return a(obj, C0000a.b(obj));
    }

    public final int size() {
        return this.d;
    }

    public final Collection values() {
        Collection collection = this.k;
        if (collection != null) {
            return collection;
        }
        C0050bw bwVar = new C0050bw(this);
        this.k = bwVar;
        return bwVar;
    }
}
