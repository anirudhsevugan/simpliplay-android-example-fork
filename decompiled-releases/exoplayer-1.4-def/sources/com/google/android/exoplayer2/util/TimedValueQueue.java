package com.google.android.exoplayer2.util;

import androidx.core.location.LocationRequestCompat;
import java.util.Arrays;

public final class TimedValueQueue {
    private long[] a;
    private Object[] b;
    private int c;
    private int d;

    public TimedValueQueue() {
        this((byte) 0);
    }

    private TimedValueQueue(byte b2) {
        this.a = new long[10];
        this.b = new Object[10];
    }

    private Object a(long j, boolean z) {
        Object obj = null;
        long j2 = LocationRequestCompat.PASSIVE_INTERVAL;
        while (this.d > 0) {
            long j3 = j - this.a[this.c];
            if (j3 < 0 && (z || (-j3) >= j2)) {
                break;
            }
            obj = d();
            j2 = j3;
        }
        return obj;
    }

    private Object d() {
        Assertions.b(this.d > 0);
        Object[] objArr = this.b;
        int i = this.c;
        Object obj = objArr[i];
        objArr[i] = null;
        this.c = (i + 1) % objArr.length;
        this.d--;
        return obj;
    }

    public final synchronized Object a(long j) {
        return a(j, true);
    }

    public final synchronized void a() {
        this.c = 0;
        this.d = 0;
        Arrays.fill(this.b, (Object) null);
    }

    public final synchronized void a(long j, Object obj) {
        int i = this.d;
        if (i > 0) {
            if (j <= this.a[((this.c + i) - 1) % this.b.length]) {
                a();
            }
        }
        int length = this.b.length;
        if (this.d >= length) {
            int i2 = length << 1;
            long[] jArr = new long[i2];
            Object[] objArr = new Object[i2];
            int i3 = this.c;
            int i4 = length - i3;
            System.arraycopy(this.a, i3, jArr, 0, i4);
            System.arraycopy(this.b, this.c, objArr, 0, i4);
            int i5 = this.c;
            if (i5 > 0) {
                System.arraycopy(this.a, 0, jArr, i4, i5);
                System.arraycopy(this.b, 0, objArr, i4, this.c);
            }
            this.a = jArr;
            this.b = objArr;
            this.c = 0;
        }
        int i6 = this.c;
        int i7 = this.d;
        Object[] objArr2 = this.b;
        int length2 = (i6 + i7) % objArr2.length;
        this.a[length2] = j;
        objArr2[length2] = obj;
        this.d = i7 + 1;
    }

    public final synchronized int b() {
        return this.d;
    }

    public final synchronized Object b(long j) {
        return a(j, false);
    }

    public final synchronized Object c() {
        if (this.d == 0) {
            return null;
        }
        return d();
    }
}
