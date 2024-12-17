package com.dreamers.exoplayercore.repack;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class bK extends bO implements bZ {
    bK(bM bMVar, int i) {
        super(bMVar, i);
    }

    static bK a(Collection collection) {
        if (collection.isEmpty()) {
            return bB.a;
        }
        bN bNVar = new bN(collection.size());
        Iterator it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            bG a = bG.a((Collection) entry.getValue());
            if (!a.isEmpty()) {
                bNVar.a(key, a);
                i += a.size();
            }
        }
        return new bK(bNVar.a(), i);
    }

    public static bL a() {
        return new bL();
    }

    /* renamed from: c */
    public final bG d(Object obj) {
        bG bGVar = (bG) this.b.get(obj);
        return bGVar == null ? bG.g() : bGVar;
    }
}
