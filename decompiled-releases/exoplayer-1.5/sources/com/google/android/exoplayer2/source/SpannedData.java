package com.google.android.exoplayer2.source;

import android.util.SparseArray;
import com.google.android.exoplayer2.util.Consumer;

final class SpannedData {
    int a;
    final SparseArray b;
    final Consumer c;

    public SpannedData() {
        this(SpannedData$$Lambda$0.a);
    }

    public SpannedData(Consumer consumer) {
        this.b = new SparseArray();
        this.c = consumer;
        this.a = -1;
    }

    static final /* synthetic */ void d() {
    }

    public final Object a() {
        SparseArray sparseArray = this.b;
        return sparseArray.valueAt(sparseArray.size() - 1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0019 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0014  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object a(int r3) {
        /*
            r2 = this;
            int r0 = r2.a
            r1 = -1
            if (r0 != r1) goto L_0x0008
            r0 = 0
        L_0x0006:
            r2.a = r0
        L_0x0008:
            int r0 = r2.a
            if (r0 <= 0) goto L_0x0019
            android.util.SparseArray r1 = r2.b
            int r0 = r1.keyAt(r0)
            if (r3 >= r0) goto L_0x0019
            int r0 = r2.a
            int r0 = r0 + -1
            goto L_0x0006
        L_0x0019:
            int r0 = r2.a
            android.util.SparseArray r1 = r2.b
            int r1 = r1.size()
            int r1 = r1 + -1
            if (r0 >= r1) goto L_0x0038
            android.util.SparseArray r0 = r2.b
            int r1 = r2.a
            int r1 = r1 + 1
            int r0 = r0.keyAt(r1)
            if (r3 < r0) goto L_0x0038
            int r0 = r2.a
            int r0 = r0 + 1
            r2.a = r0
            goto L_0x0019
        L_0x0038:
            android.util.SparseArray r3 = r2.b
            int r0 = r2.a
            java.lang.Object r3 = r3.valueAt(r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.SpannedData.a(int):java.lang.Object");
    }

    public final void b() {
        for (int i = 0; i < this.b.size(); i++) {
            this.c.a(this.b.valueAt(i));
        }
        this.a = -1;
        this.b.clear();
    }

    public final void b(int i) {
        int i2 = 0;
        while (i2 < this.b.size() - 1) {
            int i3 = i2 + 1;
            if (i >= this.b.keyAt(i3)) {
                this.c.a(this.b.valueAt(i2));
                this.b.removeAt(i2);
                int i4 = this.a;
                if (i4 > 0) {
                    this.a = i4 - 1;
                }
                i2 = i3;
            } else {
                return;
            }
        }
    }

    public final void c(int i) {
        int size = this.b.size() - 1;
        while (size >= 0 && i < this.b.keyAt(size)) {
            this.c.a(this.b.valueAt(size));
            this.b.removeAt(size);
            size--;
        }
        this.a = this.b.size() > 0 ? Math.min(this.a, this.b.size() - 1) : -1;
    }

    public final boolean c() {
        return this.b.size() == 0;
    }
}
