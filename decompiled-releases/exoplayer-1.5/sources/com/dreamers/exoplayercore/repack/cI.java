package com.dreamers.exoplayercore.repack;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

abstract class cI extends AbstractSet {
    cI() {
    }

    public boolean removeAll(Collection collection) {
        C0000a.a((Object) collection);
        if (collection instanceof C0073cs) {
            collection = ((C0073cs) collection).a();
        }
        if (!(collection instanceof Set) || collection.size() <= size()) {
            return cF.a((Set) this, collection.iterator());
        }
        Iterator it = iterator();
        C0000a.a((Object) collection);
        boolean z = false;
        while (it.hasNext()) {
            if (collection.contains(it.next())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    public boolean retainAll(Collection collection) {
        return super.retainAll((Collection) C0000a.a((Object) collection));
    }
}
