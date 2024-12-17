package com.dreamers.exoplayercore.repack;

import java.util.Iterator;
import java.util.Map;

/* renamed from: com.dreamers.exoplayercore.repack.ca  reason: case insensitive filesystem */
public final class C0055ca {
    static Object a(Map map, Object obj) {
        C0000a.a((Object) map);
        try {
            return map.get(obj);
        } catch (ClassCastException | NullPointerException e) {
            return null;
        }
    }

    static Iterator a(Iterator it) {
        return new C0057cc(it);
    }

    public static Map.Entry a(Object obj, Object obj2) {
        return new bF(obj, obj2);
    }

    static boolean b(Map map, Object obj) {
        C0000a.a((Object) map);
        try {
            return map.containsKey(obj);
        } catch (ClassCastException | NullPointerException e) {
            return false;
        }
    }

    static Object c(Map map, Object obj) {
        C0000a.a((Object) map);
        try {
            return map.remove(obj);
        } catch (ClassCastException | NullPointerException e) {
            return null;
        }
    }
}
