package com.dreamers.exoplayercore.repack;

/* renamed from: com.dreamers.exoplayercore.repack.cw  reason: case insensitive filesystem */
final class C0077cw extends bM {
    static final bM a = new C0077cw((int[]) null, new Object[0], 0);
    private final transient int[] b;
    private transient Object[] c;
    private final transient int d;

    private C0077cw(int[] iArr, Object[] objArr, int i) {
        this.b = iArr;
        this.c = objArr;
        this.d = i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004e, code lost:
        r3[r8] = r5;
        r1 = r1 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.dreamers.exoplayercore.repack.C0077cw a(int r11, java.lang.Object[] r12) {
        /*
            if (r11 != 0) goto L_0x0007
            com.dreamers.exoplayercore.repack.bM r11 = a
            com.dreamers.exoplayercore.repack.cw r11 = (com.dreamers.exoplayercore.repack.C0077cw) r11
            return r11
        L_0x0007:
            r0 = 0
            r1 = 0
            r2 = 1
            if (r11 != r2) goto L_0x0019
            r11 = r12[r1]
            r1 = r12[r2]
            com.dreamers.exoplayercore.repack.C0000a.b((java.lang.Object) r11, (java.lang.Object) r1)
            com.dreamers.exoplayercore.repack.cw r11 = new com.dreamers.exoplayercore.repack.cw
            r11.<init>(r0, r12, r2)
            return r11
        L_0x0019:
            int r3 = r12.length
            int r3 = r3 >> r2
            com.dreamers.exoplayercore.repack.C0000a.c(r11, r3)
            int r3 = com.dreamers.exoplayercore.repack.bU.a((int) r11)
            if (r11 != r2) goto L_0x002c
            r1 = r12[r1]
            r2 = r12[r2]
            com.dreamers.exoplayercore.repack.C0000a.b((java.lang.Object) r1, (java.lang.Object) r2)
            goto L_0x0096
        L_0x002c:
            int r0 = r3 + -1
            int[] r3 = new int[r3]
            r4 = -1
            java.util.Arrays.fill(r3, r4)
        L_0x0034:
            if (r1 >= r11) goto L_0x0095
            int r5 = r1 * 2
            r6 = r12[r5]
            int r7 = r5 + 1
            r7 = r12[r7]
            com.dreamers.exoplayercore.repack.C0000a.b((java.lang.Object) r6, (java.lang.Object) r7)
            int r8 = r6.hashCode()
            int r8 = com.dreamers.exoplayercore.repack.C0000a.d(r8)
        L_0x0049:
            r8 = r8 & r0
            r9 = r3[r8]
            if (r9 != r4) goto L_0x0053
            r3[r8] = r5
            int r1 = r1 + 1
            goto L_0x0034
        L_0x0053:
            r10 = r12[r9]
            boolean r10 = r10.equals(r6)
            if (r10 != 0) goto L_0x005e
            int r8 = r8 + 1
            goto L_0x0049
        L_0x005e:
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Multiple entries with same key: "
            r0.<init>(r1)
            java.lang.StringBuilder r0 = r0.append(r6)
            java.lang.String r1 = "="
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r7)
            java.lang.String r3 = " and "
            java.lang.StringBuilder r0 = r0.append(r3)
            r3 = r12[r9]
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.StringBuilder r0 = r0.append(r1)
            r1 = r9 ^ 1
            r12 = r12[r1]
            java.lang.StringBuilder r12 = r0.append(r12)
            java.lang.String r12 = r12.toString()
            r11.<init>(r12)
            throw r11
        L_0x0095:
            r0 = r3
        L_0x0096:
            com.dreamers.exoplayercore.repack.cw r1 = new com.dreamers.exoplayercore.repack.cw
            r1.<init>(r0, r12, r11)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dreamers.exoplayercore.repack.C0077cw.a(int, java.lang.Object[]):com.dreamers.exoplayercore.repack.cw");
    }

    /* access modifiers changed from: package-private */
    public final bU d() {
        return new C0078cx(this, this.c, this.d);
    }

    /* access modifiers changed from: package-private */
    public final bU f() {
        return new C0080cz(this, new cA(this.c, 0, this.d));
    }

    public final Object get(Object obj) {
        int[] iArr = this.b;
        Object[] objArr = this.c;
        int i = this.d;
        if (obj == null) {
            return null;
        }
        if (i == 1) {
            if (objArr[0].equals(obj)) {
                return objArr[1];
            }
            return null;
        } else if (iArr == null) {
            return null;
        } else {
            int length = iArr.length - 1;
            int d2 = C0000a.d(obj.hashCode());
            while (true) {
                int i2 = d2 & length;
                int i3 = iArr[i2];
                if (i3 == -1) {
                    return null;
                }
                if (objArr[i3].equals(obj)) {
                    return objArr[i3 ^ 1];
                }
                d2 = i2 + 1;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final bC h() {
        return new cA(this.c, 1, this.d);
    }

    public final int size() {
        return this.d;
    }
}
