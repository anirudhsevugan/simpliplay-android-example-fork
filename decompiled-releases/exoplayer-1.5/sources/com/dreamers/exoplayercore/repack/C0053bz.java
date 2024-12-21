package com.dreamers.exoplayercore.repack;

import java.util.Comparator;

/* renamed from: com.dreamers.exoplayercore.repack.bz  reason: case insensitive filesystem */
final class C0053bz extends C0052by {
    C0053bz() {
        super((byte) 0);
    }

    private static C0052by a(int i) {
        return i < 0 ? C0052by.b : i > 0 ? C0052by.c : C0052by.a;
    }

    public final C0052by a(int i, int i2) {
        return a(i < i2 ? -1 : i > i2 ? 1 : 0);
    }

    public final C0052by a(long j, long j2) {
        return a(j < j2 ? -1 : j > j2 ? 1 : 0);
    }

    public final C0052by a(Object obj, Object obj2, Comparator comparator) {
        return a(comparator.compare(obj, obj2));
    }

    public final C0052by a(boolean z, boolean z2) {
        return a(C0000a.a(z2, z));
    }

    public final int b() {
        return 0;
    }

    public final C0052by b(boolean z, boolean z2) {
        return a(C0000a.a(z, z2));
    }
}
