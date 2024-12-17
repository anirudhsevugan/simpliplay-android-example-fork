package com.dreamers.exoplayercore.repack;

/* renamed from: com.dreamers.exoplayercore.repack.e  reason: case insensitive filesystem */
final class C0082e {
    long a = 0;
    C0082e b;

    C0082e() {
    }

    private void a() {
        if (this.b == null) {
            this.b = new C0082e();
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(int i) {
        C0082e eVar = this;
        while (i >= 64) {
            eVar.a();
            eVar = eVar.b;
            i -= 64;
        }
        eVar.a |= 1 << i;
    }

    /* access modifiers changed from: package-private */
    public final void a(int i, boolean z) {
        C0082e eVar = this;
        while (true) {
            if (i >= 64) {
                eVar.a();
                eVar = eVar.b;
                i -= 64;
            } else {
                long j = eVar.a;
                boolean z2 = (Long.MIN_VALUE & j) != 0;
                long j2 = (1 << i) - 1;
                eVar.a = ((j & (j2 ^ -1)) << 1) | (j & j2);
                if (z) {
                    eVar.a(i);
                } else {
                    eVar.b(i);
                }
                if (z2 || eVar.b != null) {
                    eVar.a();
                    eVar = eVar.b;
                    z = z2;
                    i = 0;
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void b(int i) {
        C0082e eVar = this;
        while (i >= 64) {
            eVar = eVar.b;
            if (eVar != null) {
                i -= 64;
            } else {
                return;
            }
        }
        eVar.a &= (1 << i) ^ -1;
    }

    /* access modifiers changed from: package-private */
    public final boolean c(int i) {
        C0082e eVar = this;
        while (i >= 64) {
            eVar.a();
            eVar = eVar.b;
            i -= 64;
        }
        return (eVar.a & (1 << i)) != 0;
    }

    /* access modifiers changed from: package-private */
    public final boolean d(int i) {
        C0082e eVar = this;
        while (i >= 64) {
            eVar.a();
            eVar = eVar.b;
            i -= 64;
        }
        long j = 1 << i;
        long j2 = eVar.a;
        boolean z = (j2 & j) != 0;
        long j3 = j2 & (j ^ -1);
        eVar.a = j3;
        long j4 = j - 1;
        eVar.a = (j3 & j4) | Long.rotateRight((j4 ^ -1) & j3, 1);
        C0082e eVar2 = eVar.b;
        if (eVar2 != null) {
            if (eVar2.c(0)) {
                eVar.a(63);
            }
            eVar.b.d(0);
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public final int e(int i) {
        C0082e eVar = this.b;
        return eVar == null ? i >= 64 ? Long.bitCount(this.a) : Long.bitCount(this.a & ((1 << i) - 1)) : i < 64 ? Long.bitCount(this.a & ((1 << i) - 1)) : eVar.e(i - 64) + Long.bitCount(this.a);
    }

    public final String toString() {
        return this.b == null ? Long.toBinaryString(this.a) : this.b.toString() + "xx" + Long.toBinaryString(this.a);
    }
}
