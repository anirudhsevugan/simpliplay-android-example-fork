package com.google.android.exoplayer2.upstream;

import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public final class DefaultAllocator implements Allocator {
    private final Allocation[] a;
    private int b;
    private int c;
    private int d;
    private Allocation[] e;

    public DefaultAllocator() {
        this((byte) 0);
    }

    private DefaultAllocator(byte b2) {
        Assertions.a(true);
        Assertions.a(true);
        this.d = 0;
        this.e = new Allocation[100];
        this.a = new Allocation[1];
    }

    public final synchronized Allocation a() {
        Allocation allocation;
        this.c++;
        int i = this.d;
        if (i > 0) {
            Allocation[] allocationArr = this.e;
            int i2 = i - 1;
            this.d = i2;
            allocation = (Allocation) Assertions.b((Object) allocationArr[i2]);
            this.e[this.d] = null;
        } else {
            allocation = new Allocation(new byte[65536]);
        }
        return allocation;
    }

    public final synchronized void a(int i) {
        boolean z = i < this.b;
        this.b = i;
        if (z) {
            b();
        }
    }

    public final synchronized void a(Allocation allocation) {
        Allocation[] allocationArr = this.a;
        allocationArr[0] = allocation;
        a(allocationArr);
    }

    public final synchronized void a(Allocation[] allocationArr) {
        int i = this.d;
        int length = allocationArr.length + i;
        Allocation[] allocationArr2 = this.e;
        if (length >= allocationArr2.length) {
            this.e = (Allocation[]) Arrays.copyOf(allocationArr2, Math.max(allocationArr2.length << 1, i + allocationArr.length));
        }
        for (Allocation allocation : allocationArr) {
            Allocation[] allocationArr3 = this.e;
            int i2 = this.d;
            this.d = i2 + 1;
            allocationArr3[i2] = allocation;
        }
        this.c -= allocationArr.length;
        notifyAll();
    }

    public final synchronized void b() {
        int max = Math.max(0, Util.a(this.b, 65536) - this.c);
        int i = this.d;
        if (max < i) {
            Arrays.fill(this.e, max, i, (Object) null);
            this.d = max;
        }
    }

    public final synchronized void c() {
        a(0);
    }

    public final synchronized int d() {
        return this.c << 16;
    }
}
