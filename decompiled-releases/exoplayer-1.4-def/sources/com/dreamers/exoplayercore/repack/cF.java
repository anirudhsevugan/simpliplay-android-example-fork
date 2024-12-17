package com.dreamers.exoplayercore.repack;

import java.util.Iterator;
import java.util.Set;

public final class cF {
    static int a(Set set) {
        Iterator it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i = ((i + (next != null ? next.hashCode() : 0)) ^ -1) ^ -1;
        }
        return i;
    }

    public static cJ a(Set set, Set set2) {
        C0000a.a((Object) set, (Object) "set1");
        C0000a.a((Object) set2, (Object) "set2");
        return new cG(set, set2);
    }

    static boolean a(Set set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set2 = (Set) obj;
            try {
                return set.size() == set2.size() && set.containsAll(set2);
            } catch (ClassCastException | NullPointerException e) {
            }
        }
        return false;
    }

    static boolean a(Set set, Iterator it) {
        boolean z = false;
        while (it.hasNext()) {
            z |= set.remove(it.next());
        }
        return z;
    }
}
