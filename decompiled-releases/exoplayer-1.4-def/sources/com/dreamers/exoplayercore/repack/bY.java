package com.dreamers.exoplayercore.repack;

import java.util.Iterator;
import java.util.NoSuchElementException;

enum bY implements Iterator {
    ;

    static {
        bY bYVar = new bY("INSTANCE");
        a = bYVar;
        new bY[1][0] = bYVar;
    }

    private bY(String str) {
    }

    public final boolean hasNext() {
        return false;
    }

    public final Object next() {
        throw new NoSuchElementException();
    }

    public final void remove() {
        C0000a.c(false);
    }
}
