package com.dreamers.exoplayercore.repack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class aF {
    final C0024ax a;
    private final aJ b;

    private aF(aJ aJVar) {
        this(aJVar, C0024ax.a());
    }

    private aF(aJ aJVar, C0024ax axVar) {
        this.b = aJVar;
        this.a = axVar;
    }

    public static aF a(C0024ax axVar) {
        C0000a.a((Object) axVar);
        return new aF(new aG(axVar));
    }

    public final List a(CharSequence charSequence) {
        C0000a.a((Object) charSequence);
        Iterator a2 = this.b.a(this, charSequence);
        ArrayList arrayList = new ArrayList();
        while (a2.hasNext()) {
            arrayList.add(a2.next());
        }
        return Collections.unmodifiableList(arrayList);
    }
}
