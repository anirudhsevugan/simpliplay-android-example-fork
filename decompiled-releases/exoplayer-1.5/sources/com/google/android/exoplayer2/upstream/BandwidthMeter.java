package com.google.android.exoplayer2.upstream;

import android.os.Handler;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public interface BandwidthMeter {

    public interface EventListener {

        public final class EventDispatcher {
            final CopyOnWriteArrayList a = new CopyOnWriteArrayList();

            final class HandlerAndListener {
                /* access modifiers changed from: package-private */
                public boolean a;
                /* access modifiers changed from: private */
                public final Handler b;
                /* access modifiers changed from: private */
                public final EventListener c;

                public HandlerAndListener(Handler handler, EventListener eventListener) {
                    this.b = handler;
                    this.c = eventListener;
                }
            }

            public final void a(int i, long j, long j2) {
                Iterator it = this.a.iterator();
                while (it.hasNext()) {
                    HandlerAndListener handlerAndListener = (HandlerAndListener) it.next();
                    if (!handlerAndListener.a) {
                        handlerAndListener.b.post(new BandwidthMeter$EventListener$EventDispatcher$$Lambda$0(handlerAndListener, i, j, j2));
                    }
                }
            }

            public final void a(EventListener eventListener) {
                Iterator it = this.a.iterator();
                while (it.hasNext()) {
                    HandlerAndListener handlerAndListener = (HandlerAndListener) it.next();
                    if (handlerAndListener.c == eventListener) {
                        handlerAndListener.a = true;
                        this.a.remove(handlerAndListener);
                    }
                }
            }
        }

        void b(int i, long j, long j2);
    }

    long a();

    void a(Handler handler, EventListener eventListener);

    void a(EventListener eventListener);

    long b();

    TransferListener c();
}
