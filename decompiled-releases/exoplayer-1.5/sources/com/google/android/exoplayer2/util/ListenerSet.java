package com.google.android.exoplayer2.util;

import android.os.Looper;
import com.google.android.exoplayer2.util.ExoFlags;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

public final class ListenerSet {
    public final Clock a;
    public final HandlerWrapper b;
    final IterationFinishedEvent c;
    public final CopyOnWriteArraySet d;
    private final ArrayDeque e;
    private final ArrayDeque f;
    private boolean g;

    public interface Event {
        void a(Object obj);
    }

    public interface IterationFinishedEvent {
        void a(Object obj, ExoFlags exoFlags);
    }

    final class ListenerHolder {
        public final Object a;
        ExoFlags.Builder b = new ExoFlags.Builder();
        boolean c;
        boolean d;

        public ListenerHolder(Object obj) {
            this.a = obj;
        }

        public final void a(IterationFinishedEvent iterationFinishedEvent) {
            this.d = true;
            if (this.c) {
                iterationFinishedEvent.a(this.a, this.b.a());
            }
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.a.equals(((ListenerHolder) obj).a);
        }

        public final int hashCode() {
            return this.a.hashCode();
        }
    }

    public ListenerSet(Looper looper, Clock clock, IterationFinishedEvent iterationFinishedEvent) {
        this(new CopyOnWriteArraySet(), looper, clock, iterationFinishedEvent);
    }

    public ListenerSet(CopyOnWriteArraySet copyOnWriteArraySet, Looper looper, Clock clock, IterationFinishedEvent iterationFinishedEvent) {
        this.a = clock;
        this.d = copyOnWriteArraySet;
        this.c = iterationFinishedEvent;
        this.e = new ArrayDeque();
        this.f = new ArrayDeque();
        this.b = clock.a(looper, new ListenerSet$$Lambda$0(this));
    }

    static final /* synthetic */ void a(CopyOnWriteArraySet copyOnWriteArraySet, int i, Event event) {
        Iterator it = copyOnWriteArraySet.iterator();
        while (it.hasNext()) {
            ListenerHolder listenerHolder = (ListenerHolder) it.next();
            if (!listenerHolder.d) {
                if (i != -1) {
                    listenerHolder.b.a(i);
                }
                listenerHolder.c = true;
                event.a(listenerHolder.a);
            }
        }
    }

    public final void a() {
        if (!this.f.isEmpty()) {
            if (!this.b.a()) {
                this.b.a(0).a();
            }
            boolean z = !this.e.isEmpty();
            this.e.addAll(this.f);
            this.f.clear();
            if (!z) {
                while (!this.e.isEmpty()) {
                    ((Runnable) this.e.peekFirst()).run();
                    this.e.removeFirst();
                }
            }
        }
    }

    public final void a(int i, Event event) {
        this.f.add(new ListenerSet$$Lambda$1(new CopyOnWriteArraySet(this.d), i, event));
    }

    public final void a(Object obj) {
        if (!this.g) {
            Assertions.b(obj);
            this.d.add(new ListenerHolder(obj));
        }
    }

    public final void b() {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            ((ListenerHolder) it.next()).a(this.c);
        }
        this.d.clear();
        this.g = true;
    }

    public final void b(int i, Event event) {
        a(i, event);
        a();
    }

    public final void b(Object obj) {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            ListenerHolder listenerHolder = (ListenerHolder) it.next();
            if (listenerHolder.a.equals(obj)) {
                listenerHolder.a(this.c);
                this.d.remove(listenerHolder);
            }
        }
    }
}
