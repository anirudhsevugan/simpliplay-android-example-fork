package com.dreamers.exoplayerui.repack;

import java.util.NoSuchElementException;

public final class q extends m {
    private final int a;
    private boolean b;
    private int c;
    private final int d = 1;

    public q(int i, int i2, int i3) {
        boolean z = true;
        this.a = i2;
        z = i > i2 ? false : z;
        this.b = z;
        this.c = !z ? i2 : i;
    }

    public final int a() {
        int i = this.c;
        if (i != this.a) {
            this.c = this.d + i;
        } else if (this.b) {
            this.b = false;
        } else {
            throw new NoSuchElementException();
        }
        return i;
    }

    public final boolean hasNext() {
        return this.b;
    }
}
