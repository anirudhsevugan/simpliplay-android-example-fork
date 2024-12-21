package com.dreamers.exoplayerui.repack;

import java.util.Iterator;

public abstract class m implements Iterator {
    public abstract int a();

    public /* synthetic */ Object next() {
        return Integer.valueOf(a());
    }

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
