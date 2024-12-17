package com.dreamers.exoplayercore.repack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class bR {
    private Map a = C0042bo.a();

    public bR a(Object obj, Iterable iterable) {
        if (obj != null) {
            Collection collection = (Collection) this.a.get(obj);
            Iterator it = iterable.iterator();
            if (collection != null) {
                while (it.hasNext()) {
                    Object next = it.next();
                    C0000a.b(obj, next);
                    collection.add(next);
                }
                return this;
            } else if (!it.hasNext()) {
                return this;
            } else {
                ArrayList arrayList = new ArrayList();
                while (it.hasNext()) {
                    Object next2 = it.next();
                    C0000a.b(obj, next2);
                    arrayList.add(next2);
                }
                this.a.put(obj, arrayList);
                return this;
            }
        } else {
            throw new NullPointerException("null key in entry: null=" + C0000a.a(iterable));
        }
    }

    public bO b() {
        return bK.a((Collection) this.a.entrySet());
    }

    public bR b(Object obj, Object... objArr) {
        return a(obj, Arrays.asList(objArr));
    }
}
