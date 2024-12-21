package com.google.android.exoplayer2;

import android.os.Looper;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Clock;

public final class PlayerMessage {
    final Target a;
    final Timeline b;
    int c;
    Object d;
    Looper e;
    int f;
    long g = -9223372036854775807L;
    boolean h = true;
    private final Sender i;
    private final Clock j;
    private boolean k;
    private boolean l;
    private boolean m;

    public interface Sender {
        void a(PlayerMessage playerMessage);
    }

    public interface Target {
        void a(int i, Object obj);
    }

    public PlayerMessage(Sender sender, Target target, Timeline timeline, int i2, Clock clock, Looper looper) {
        this.i = sender;
        this.a = target;
        this.b = timeline;
        this.e = looper;
        this.j = clock;
        this.f = i2;
    }

    public final PlayerMessage a() {
        Assertions.b(!this.k);
        if (this.g == -9223372036854775807L) {
            Assertions.a(this.h);
        }
        this.k = true;
        this.i.a(this);
        return this;
    }

    public final PlayerMessage a(int i2) {
        Assertions.b(!this.k);
        this.c = i2;
        return this;
    }

    public final PlayerMessage a(Object obj) {
        Assertions.b(!this.k);
        this.d = obj;
        return this;
    }

    public final synchronized void a(boolean z) {
        this.l = z | this.l;
        this.m = true;
        notifyAll();
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003b A[SYNTHETIC, Splitter:B:16:0x003b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean a(long r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            boolean r0 = r6.k     // Catch:{ all -> 0x0043 }
            com.google.android.exoplayer2.util.Assertions.b((boolean) r0)     // Catch:{ all -> 0x0043 }
            android.os.Looper r0 = r6.e     // Catch:{ all -> 0x0043 }
            java.lang.Thread r0 = r0.getThread()     // Catch:{ all -> 0x0043 }
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0043 }
            if (r0 == r1) goto L_0x0014
            r0 = 1
            goto L_0x0015
        L_0x0014:
            r0 = 0
        L_0x0015:
            com.google.android.exoplayer2.util.Assertions.b((boolean) r0)     // Catch:{ all -> 0x0043 }
            com.google.android.exoplayer2.util.Clock r0 = r6.j     // Catch:{ all -> 0x0043 }
            long r0 = r0.a()     // Catch:{ all -> 0x0043 }
            long r0 = r0 + r7
        L_0x001f:
            boolean r2 = r6.m     // Catch:{ all -> 0x0043 }
            if (r2 != 0) goto L_0x0035
            r3 = 0
            int r5 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x0035
            r6.wait(r7)     // Catch:{ all -> 0x0043 }
            com.google.android.exoplayer2.util.Clock r7 = r6.j     // Catch:{ all -> 0x0043 }
            long r7 = r7.a()     // Catch:{ all -> 0x0043 }
            long r7 = r0 - r7
            goto L_0x001f
        L_0x0035:
            if (r2 == 0) goto L_0x003b
            boolean r7 = r6.l     // Catch:{ all -> 0x0043 }
            monitor-exit(r6)
            return r7
        L_0x003b:
            java.util.concurrent.TimeoutException r7 = new java.util.concurrent.TimeoutException     // Catch:{ all -> 0x0043 }
            java.lang.String r8 = "Message delivery timed out."
            r7.<init>(r8)     // Catch:{ all -> 0x0043 }
            throw r7     // Catch:{ all -> 0x0043 }
        L_0x0043:
            r7 = move-exception
            monitor-exit(r6)
            goto L_0x0047
        L_0x0046:
            throw r7
        L_0x0047:
            goto L_0x0046
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.PlayerMessage.a(long):boolean");
    }
}
