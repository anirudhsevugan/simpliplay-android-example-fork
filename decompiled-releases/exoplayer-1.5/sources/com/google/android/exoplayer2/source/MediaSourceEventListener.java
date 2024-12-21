package com.google.android.exoplayer2.source;

import android.os.Handler;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public interface MediaSourceEventListener {

    public class EventDispatcher {
        public final int a;
        public final MediaSource.MediaPeriodId b;
        final CopyOnWriteArrayList c;
        private final long d;

        final class ListenerAndHandler {
            public Handler a;
            public MediaSourceEventListener b;

            public ListenerAndHandler(Handler handler, MediaSourceEventListener mediaSourceEventListener) {
                this.a = handler;
                this.b = mediaSourceEventListener;
            }
        }

        public EventDispatcher() {
            this(new CopyOnWriteArrayList(), 0, (MediaSource.MediaPeriodId) null, 0);
        }

        private EventDispatcher(CopyOnWriteArrayList copyOnWriteArrayList, int i, MediaSource.MediaPeriodId mediaPeriodId, long j) {
            this.c = copyOnWriteArrayList;
            this.a = i;
            this.b = mediaPeriodId;
            this.d = j;
        }

        private long a(long j) {
            long a2 = C.a(j);
            if (a2 == -9223372036854775807L) {
                return -9223372036854775807L;
            }
            return this.d + a2;
        }

        public final EventDispatcher a(int i, MediaSource.MediaPeriodId mediaPeriodId, long j) {
            return new EventDispatcher(this.c, i, mediaPeriodId, j);
        }

        public final void a(int i, long j, long j2) {
            long j3 = j;
            a(new MediaLoadData(1, i, (Format) null, 3, (Object) null, a(j), a(j2)));
        }

        public final void a(int i, Format format, int i2, Object obj, long j) {
            b(new MediaLoadData(1, i, format, i2, obj, a(j), -9223372036854775807L));
        }

        public final void a(Handler handler, MediaSourceEventListener mediaSourceEventListener) {
            Assertions.b((Object) handler);
            Assertions.b((Object) mediaSourceEventListener);
            this.c.add(new ListenerAndHandler(handler, mediaSourceEventListener));
        }

        public final void a(LoadEventInfo loadEventInfo, int i) {
            a(loadEventInfo, i, -1, (Format) null, 0, (Object) null, -9223372036854775807L, -9223372036854775807L);
        }

        public final void a(LoadEventInfo loadEventInfo, int i, int i2, Format format, int i3, Object obj, long j, long j2) {
            LoadEventInfo loadEventInfo2 = loadEventInfo;
            a(loadEventInfo, new MediaLoadData(i, i2, format, i3, obj, a(j), a(j2)));
        }

        public final void a(LoadEventInfo loadEventInfo, int i, int i2, Format format, int i3, Object obj, long j, long j2, IOException iOException, boolean z) {
            LoadEventInfo loadEventInfo2 = loadEventInfo;
            a(loadEventInfo, new MediaLoadData(i, i2, format, i3, obj, a(j), a(j2)), iOException, z);
        }

        public final void a(LoadEventInfo loadEventInfo, int i, IOException iOException, boolean z) {
            a(loadEventInfo, i, -1, (Format) null, 0, (Object) null, -9223372036854775807L, -9223372036854775807L, iOException, z);
        }

        public final void a(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                ListenerAndHandler listenerAndHandler = (ListenerAndHandler) it.next();
                Util.a(listenerAndHandler.a, (Runnable) new MediaSourceEventListener$EventDispatcher$$Lambda$0(this, listenerAndHandler.b, loadEventInfo, mediaLoadData));
            }
        }

        public final void a(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                ListenerAndHandler listenerAndHandler = (ListenerAndHandler) it.next();
                Util.a(listenerAndHandler.a, (Runnable) new MediaSourceEventListener$EventDispatcher$$Lambda$3(this, listenerAndHandler.b, loadEventInfo, mediaLoadData, iOException, z));
            }
        }

        public final void a(MediaLoadData mediaLoadData) {
            MediaSource.MediaPeriodId mediaPeriodId = (MediaSource.MediaPeriodId) Assertions.b((Object) this.b);
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                ListenerAndHandler listenerAndHandler = (ListenerAndHandler) it.next();
                Util.a(listenerAndHandler.a, (Runnable) new MediaSourceEventListener$EventDispatcher$$Lambda$4(this, listenerAndHandler.b, mediaPeriodId, mediaLoadData));
            }
        }

        public final void b(LoadEventInfo loadEventInfo, int i) {
            b(loadEventInfo, i, -1, (Format) null, 0, (Object) null, -9223372036854775807L, -9223372036854775807L);
        }

        public final void b(LoadEventInfo loadEventInfo, int i, int i2, Format format, int i3, Object obj, long j, long j2) {
            LoadEventInfo loadEventInfo2 = loadEventInfo;
            b(loadEventInfo, new MediaLoadData(i, i2, format, i3, obj, a(j), a(j2)));
        }

        public final void b(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                ListenerAndHandler listenerAndHandler = (ListenerAndHandler) it.next();
                Util.a(listenerAndHandler.a, (Runnable) new MediaSourceEventListener$EventDispatcher$$Lambda$1(this, listenerAndHandler.b, loadEventInfo, mediaLoadData));
            }
        }

        public final void b(MediaLoadData mediaLoadData) {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                ListenerAndHandler listenerAndHandler = (ListenerAndHandler) it.next();
                Util.a(listenerAndHandler.a, (Runnable) new MediaSourceEventListener$EventDispatcher$$Lambda$5(this, listenerAndHandler.b, mediaLoadData));
            }
        }

        public final void c(LoadEventInfo loadEventInfo, int i) {
            c(loadEventInfo, i, -1, (Format) null, 0, (Object) null, -9223372036854775807L, -9223372036854775807L);
        }

        public final void c(LoadEventInfo loadEventInfo, int i, int i2, Format format, int i3, Object obj, long j, long j2) {
            LoadEventInfo loadEventInfo2 = loadEventInfo;
            c(loadEventInfo, new MediaLoadData(i, i2, format, i3, obj, a(j), a(j2)));
        }

        public final void c(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                ListenerAndHandler listenerAndHandler = (ListenerAndHandler) it.next();
                Util.a(listenerAndHandler.a, (Runnable) new MediaSourceEventListener$EventDispatcher$$Lambda$2(this, listenerAndHandler.b, loadEventInfo, mediaLoadData));
            }
        }
    }

    void a(int i, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData);

    void a(int i, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z);

    void a(int i, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData);

    void b(int i, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData);

    void b(int i, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData);

    void c(int i, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData);
}
