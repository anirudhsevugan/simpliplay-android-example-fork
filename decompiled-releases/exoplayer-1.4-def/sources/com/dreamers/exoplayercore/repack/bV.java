package com.dreamers.exoplayercore.repack;

import java.util.Iterator;

public final class bV {
    public static Object a(Iterator it) {
        Object next;
        do {
            next = it.next();
        } while (it.hasNext());
        return next;
    }

    static Object b(Iterator it) {
        if (!it.hasNext()) {
            return null;
        }
        Object next = it.next();
        it.remove();
        return next;
    }

    static void c(Iterator it) {
        C0000a.a((Object) it);
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }
}
