package com.google.android.exoplayer2.util;

import android.os.Handler;
import android.os.Message;
import com.google.android.exoplayer2.util.HandlerWrapper;
import java.util.ArrayList;
import java.util.List;

final class SystemHandlerWrapper implements HandlerWrapper {
    private static final List a = new ArrayList(50);
    private final Handler b;

    final class SystemMessage implements HandlerWrapper.Message {
        Message a;

        private SystemMessage() {
        }

        /* synthetic */ SystemMessage(byte b) {
            this();
        }

        public final void a() {
            ((Message) Assertions.b((Object) this.a)).sendToTarget();
            b();
        }

        /* access modifiers changed from: package-private */
        public final void b() {
            this.a = null;
            SystemHandlerWrapper.a(this);
        }
    }

    public SystemHandlerWrapper(Handler handler) {
        this.b = handler;
    }

    static /* synthetic */ void a(SystemMessage systemMessage) {
        List list = a;
        synchronized (list) {
            if (list.size() < 50) {
                list.add(systemMessage);
            }
        }
    }

    private static SystemMessage d() {
        SystemMessage systemMessage;
        List list = a;
        synchronized (list) {
            systemMessage = list.isEmpty() ? new SystemMessage((byte) 0) : (SystemMessage) list.remove(list.size() - 1);
        }
        return systemMessage;
    }

    public final HandlerWrapper.Message a(int i) {
        SystemMessage d = d();
        d.a = this.b.obtainMessage(i);
        return d;
    }

    public final HandlerWrapper.Message a(int i, int i2, int i3) {
        SystemMessage d = d();
        d.a = this.b.obtainMessage(i, i2, i3);
        return d;
    }

    public final HandlerWrapper.Message a(int i, int i2, int i3, Object obj) {
        SystemMessage d = d();
        d.a = this.b.obtainMessage(i, i2, i3, obj);
        return d;
    }

    public final HandlerWrapper.Message a(int i, Object obj) {
        SystemMessage d = d();
        d.a = this.b.obtainMessage(i, obj);
        return d;
    }

    public final boolean a() {
        return this.b.hasMessages(0);
    }

    public final boolean a(long j) {
        return this.b.sendEmptyMessageAtTime(2, j);
    }

    public final boolean a(HandlerWrapper.Message message) {
        SystemMessage systemMessage = (SystemMessage) message;
        boolean sendMessageAtFrontOfQueue = this.b.sendMessageAtFrontOfQueue((Message) Assertions.b((Object) systemMessage.a));
        systemMessage.b();
        return sendMessageAtFrontOfQueue;
    }

    public final boolean a(Runnable runnable) {
        return this.b.post(runnable);
    }

    public final void b() {
        this.b.removeMessages(2);
    }

    public final boolean b(int i) {
        return this.b.sendEmptyMessage(i);
    }

    public final void c() {
        this.b.removeCallbacksAndMessages((Object) null);
    }
}
