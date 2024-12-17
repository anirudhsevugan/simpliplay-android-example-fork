package com.google.android.exoplayer2.util;

import com.google.android.exoplayer2.util.ListenerSet;
import java.util.concurrent.CopyOnWriteArraySet;

final /* synthetic */ class ListenerSet$$Lambda$1 implements Runnable {
    private final CopyOnWriteArraySet a;
    private final int b;
    private final ListenerSet.Event c;

    ListenerSet$$Lambda$1(CopyOnWriteArraySet copyOnWriteArraySet, int i, ListenerSet.Event event) {
        this.a = copyOnWriteArraySet;
        this.b = i;
        this.c = event;
    }

    public final void run() {
        ListenerSet.a(this.a, this.b, this.c);
    }
}
