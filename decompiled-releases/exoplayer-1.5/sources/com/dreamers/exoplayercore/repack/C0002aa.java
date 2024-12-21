package com.dreamers.exoplayercore.repack;

import android.util.SparseArray;
import java.util.ArrayList;

/* renamed from: com.dreamers.exoplayercore.repack.aa  reason: case insensitive filesystem */
public final class C0002aa {
    SparseArray a = new SparseArray();
    int b = 0;

    static long a(long j, long j2) {
        return j == 0 ? j2 : ((j / 4) * 3) + (j2 / 4);
    }

    public final C0010ai a(int i) {
        C0003ab abVar = (C0003ab) this.a.get(i);
        if (abVar == null || abVar.a.isEmpty()) {
            return null;
        }
        ArrayList arrayList = abVar.a;
        return (C0010ai) arrayList.remove(arrayList.size() - 1);
    }

    /* access modifiers changed from: package-private */
    public final void a(int i, long j) {
        C0003ab b2 = b(i);
        b2.c = a(b2.c, j);
    }

    public final void a(C0010ai aiVar) {
        int itemViewType = aiVar.getItemViewType();
        ArrayList arrayList = b(itemViewType).a;
        if (((C0003ab) this.a.get(itemViewType)).b > arrayList.size()) {
            aiVar.resetInternal();
            arrayList.add(aiVar);
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean a(int i, long j, long j2) {
        long j3 = b(i).c;
        return j3 == 0 || j + j3 < j2;
    }

    /* access modifiers changed from: package-private */
    public final C0003ab b(int i) {
        C0003ab abVar = (C0003ab) this.a.get(i);
        if (abVar != null) {
            return abVar;
        }
        C0003ab abVar2 = new C0003ab();
        this.a.put(i, abVar2);
        return abVar2;
    }
}
