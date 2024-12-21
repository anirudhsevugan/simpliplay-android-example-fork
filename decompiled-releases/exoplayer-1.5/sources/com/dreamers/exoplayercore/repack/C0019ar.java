package com.dreamers.exoplayercore.repack;

import androidx.core.util.Pools;

/* renamed from: com.dreamers.exoplayercore.repack.ar  reason: case insensitive filesystem */
final class C0019ar {
    private static Pools.Pool d = new Pools.SimplePool(20);
    int a;
    S b;
    S c;

    private C0019ar() {
    }

    static C0019ar a() {
        C0019ar arVar = (C0019ar) d.acquire();
        return arVar == null ? new C0019ar() : arVar;
    }

    static void a(C0019ar arVar) {
        arVar.a = 0;
        arVar.b = null;
        arVar.c = null;
        d.release(arVar);
    }

    static void b() {
        do {
        } while (d.acquire() != null);
    }
}
