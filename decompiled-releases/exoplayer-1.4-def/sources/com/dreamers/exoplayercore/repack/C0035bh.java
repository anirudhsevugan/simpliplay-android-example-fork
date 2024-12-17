package com.dreamers.exoplayercore.repack;

import java.util.List;
import java.util.ListIterator;

/* renamed from: com.dreamers.exoplayercore.repack.bh  reason: case insensitive filesystem */
final class C0035bh extends C0033bf implements ListIterator {
    private /* synthetic */ C0034bg b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0035bh(C0034bg bgVar) {
        super(bgVar);
        this.b = bgVar;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0035bh(C0034bg bgVar, int i) {
        super(bgVar, ((List) bgVar.b).listIterator(i));
        this.b = bgVar;
    }

    private ListIterator b() {
        a();
        return (ListIterator) this.a;
    }

    public final void add(Object obj) {
        boolean isEmpty = this.b.isEmpty();
        b().add(obj);
        aQ.c(this.b.e);
        if (isEmpty) {
            this.b.c();
        }
    }

    public final boolean hasPrevious() {
        return b().hasPrevious();
    }

    public final int nextIndex() {
        return b().nextIndex();
    }

    public final Object previous() {
        return b().previous();
    }

    public final int previousIndex() {
        return b().previousIndex();
    }

    public final void set(Object obj) {
        b().set(obj);
    }
}
