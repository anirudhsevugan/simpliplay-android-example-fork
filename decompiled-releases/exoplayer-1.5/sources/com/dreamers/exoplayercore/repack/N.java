package com.dreamers.exoplayercore.repack;

import android.database.Observable;

class N extends Observable {
    N() {
    }

    public final void a(int i, int i2) {
        a(i, i2, (Object) null);
    }

    public final void a(int i, int i2, Object obj) {
        for (int size = this.mObservers.size() - 1; size >= 0; size--) {
            ((O) this.mObservers.get(size)).a(i, i2, obj);
        }
    }

    public final boolean a() {
        return !this.mObservers.isEmpty();
    }

    public final void b() {
        for (int size = this.mObservers.size() - 1; size >= 0; size--) {
            ((O) this.mObservers.get(size)).a();
        }
    }

    public final void b(int i, int i2) {
        for (int size = this.mObservers.size() - 1; size >= 0; size--) {
            ((O) this.mObservers.get(size)).a(i, i2);
        }
    }

    public final void c(int i, int i2) {
        for (int size = this.mObservers.size() - 1; size >= 0; size--) {
            ((O) this.mObservers.get(size)).b(i, i2);
        }
    }

    public final void d(int i, int i2) {
        for (int size = this.mObservers.size() - 1; size >= 0; size--) {
            ((O) this.mObservers.get(size)).c(i, i2);
        }
    }
}
