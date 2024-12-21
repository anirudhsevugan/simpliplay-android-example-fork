package com.dreamers.exoplayercore.repack;

import java.util.Comparator;

/* renamed from: com.dreamers.exoplayercore.repack.s  reason: case insensitive filesystem */
final class C0096s implements Comparator {
    C0096s() {
    }

    public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
        C0098u uVar = (C0098u) obj;
        C0098u uVar2 = (C0098u) obj2;
        if ((uVar.d == null) != (uVar2.d == null)) {
            return uVar.d == null ? 1 : -1;
        }
        if (uVar.a != uVar2.a) {
            return uVar.a ? -1 : 1;
        }
        int i = uVar2.b - uVar.b;
        if (i != 0) {
            return i;
        }
        int i2 = uVar.c - uVar2.c;
        if (i2 != 0) {
            return i2;
        }
        return 0;
    }
}
