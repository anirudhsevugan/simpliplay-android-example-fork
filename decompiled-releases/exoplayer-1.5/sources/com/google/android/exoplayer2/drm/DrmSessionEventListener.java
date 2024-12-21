package com.google.android.exoplayer2.drm;

import android.os.Handler;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public interface DrmSessionEventListener {

    public class EventDispatcher {
        public final int a;
        public final MediaSource.MediaPeriodId b;
        public final CopyOnWriteArrayList c;

        public final class ListenerAndHandler {
            public Handler a;
            public DrmSessionEventListener b;

            public ListenerAndHandler(Handler handler, DrmSessionEventListener drmSessionEventListener) {
                this.a = handler;
                this.b = drmSessionEventListener;
            }
        }

        public EventDispatcher() {
            this(new CopyOnWriteArrayList(), 0, (MediaSource.MediaPeriodId) null);
        }

        private EventDispatcher(CopyOnWriteArrayList copyOnWriteArrayList, int i, MediaSource.MediaPeriodId mediaPeriodId) {
            this.c = copyOnWriteArrayList;
            this.a = i;
            this.b = mediaPeriodId;
        }

        public final EventDispatcher a(int i, MediaSource.MediaPeriodId mediaPeriodId) {
            return new EventDispatcher(this.c, i, mediaPeriodId);
        }

        public final void a() {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                ListenerAndHandler listenerAndHandler = (ListenerAndHandler) it.next();
                Util.a(listenerAndHandler.a, (Runnable) new DrmSessionEventListener$EventDispatcher$$Lambda$1(this, listenerAndHandler.b));
            }
        }

        public final void a(int i) {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                ListenerAndHandler listenerAndHandler = (ListenerAndHandler) it.next();
                Util.a(listenerAndHandler.a, (Runnable) new DrmSessionEventListener$EventDispatcher$$Lambda$0(this, listenerAndHandler.b, i));
            }
        }

        public final void a(Handler handler, DrmSessionEventListener drmSessionEventListener) {
            Assertions.b((Object) handler);
            Assertions.b((Object) drmSessionEventListener);
            this.c.add(new ListenerAndHandler(handler, drmSessionEventListener));
        }

        public final void a(Exception exc) {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                ListenerAndHandler listenerAndHandler = (ListenerAndHandler) it.next();
                Util.a(listenerAndHandler.a, (Runnable) new DrmSessionEventListener$EventDispatcher$$Lambda$2(this, listenerAndHandler.b, exc));
            }
        }

        public final void b() {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                ListenerAndHandler listenerAndHandler = (ListenerAndHandler) it.next();
                Util.a(listenerAndHandler.a, (Runnable) new DrmSessionEventListener$EventDispatcher$$Lambda$3(this, listenerAndHandler.b));
            }
        }

        public final void c() {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                ListenerAndHandler listenerAndHandler = (ListenerAndHandler) it.next();
                Util.a(listenerAndHandler.a, (Runnable) new DrmSessionEventListener$EventDispatcher$$Lambda$5(this, listenerAndHandler.b));
            }
        }
    }

    void a();

    void a(int i, MediaSource.MediaPeriodId mediaPeriodId);

    void a(int i, MediaSource.MediaPeriodId mediaPeriodId, int i2);

    void a(int i, MediaSource.MediaPeriodId mediaPeriodId, Exception exc);

    void b(int i, MediaSource.MediaPeriodId mediaPeriodId);

    void c(int i, MediaSource.MediaPeriodId mediaPeriodId);
}
