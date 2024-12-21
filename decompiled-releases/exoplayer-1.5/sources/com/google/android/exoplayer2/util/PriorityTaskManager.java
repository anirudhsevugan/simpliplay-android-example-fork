package com.google.android.exoplayer2.util;

import java.util.Collections;
import java.util.PriorityQueue;

public final class PriorityTaskManager {
    public final Object a = new Object();
    public final PriorityQueue b = new PriorityQueue(10, Collections.reverseOrder());
    public int c = Integer.MIN_VALUE;

    public final void a() {
        synchronized (this.a) {
            this.b.remove(0);
            this.c = this.b.isEmpty() ? Integer.MIN_VALUE : ((Integer) Util.a((Object) (Integer) this.b.peek())).intValue();
            this.a.notifyAll();
        }
    }
}
