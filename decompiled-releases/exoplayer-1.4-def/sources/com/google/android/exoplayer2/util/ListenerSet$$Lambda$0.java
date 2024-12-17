package com.google.android.exoplayer2.util;

import android.os.Handler;
import android.os.Message;
import com.google.android.exoplayer2.util.ExoFlags;
import com.google.android.exoplayer2.util.ListenerSet;
import java.util.Iterator;

final /* synthetic */ class ListenerSet$$Lambda$0 implements Handler.Callback {
    private final ListenerSet a;

    ListenerSet$$Lambda$0(ListenerSet listenerSet) {
        this.a = listenerSet;
    }

    public final boolean handleMessage(Message message) {
        ListenerSet listenerSet = this.a;
        if (message.what == 0) {
            Iterator it = listenerSet.d.iterator();
            while (it.hasNext()) {
                ListenerSet.ListenerHolder listenerHolder = (ListenerSet.ListenerHolder) it.next();
                ListenerSet.IterationFinishedEvent iterationFinishedEvent = listenerSet.c;
                if (!listenerHolder.d && listenerHolder.c) {
                    ExoFlags a2 = listenerHolder.b.a();
                    listenerHolder.b = new ExoFlags.Builder();
                    listenerHolder.c = false;
                    iterationFinishedEvent.a(listenerHolder.a, a2);
                }
                if (listenerSet.b.a()) {
                    break;
                }
            }
        } else if (message.what == 1) {
            listenerSet.b(message.arg1, (ListenerSet.Event) message.obj);
            listenerSet.b();
        }
        return true;
    }
}
