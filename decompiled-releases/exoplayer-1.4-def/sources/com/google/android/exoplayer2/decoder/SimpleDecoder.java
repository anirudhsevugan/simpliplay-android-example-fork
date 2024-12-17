package com.google.android.exoplayer2.decoder;

import com.google.android.exoplayer2.util.Assertions;
import java.util.ArrayDeque;

public abstract class SimpleDecoder implements Decoder {
    public final Object a = new Object();
    public final OutputBuffer[] b;
    public int c;
    private final Thread d;
    private final ArrayDeque e = new ArrayDeque();
    private final ArrayDeque f = new ArrayDeque();
    private final DecoderInputBuffer[] g;
    private int h;
    private DecoderInputBuffer i;
    private DecoderException j;
    private boolean k;
    private boolean l;
    private int m;

    public SimpleDecoder(DecoderInputBuffer[] decoderInputBufferArr, OutputBuffer[] outputBufferArr) {
        this.g = decoderInputBufferArr;
        this.h = 2;
        for (int i2 = 0; i2 < this.h; i2++) {
            this.g[i2] = g();
        }
        this.b = outputBufferArr;
        this.c = 2;
        for (int i3 = 0; i3 < this.c; i3++) {
            this.b[i3] = h();
        }
        AnonymousClass1 r5 = new Thread("ExoPlayer:SimpleDecoder") {
            public void run() {
                SimpleDecoder.a(SimpleDecoder.this);
            }
        };
        this.d = r5;
        r5.start();
    }

    private void a(DecoderInputBuffer decoderInputBuffer) {
        decoderInputBuffer.a();
        DecoderInputBuffer[] decoderInputBufferArr = this.g;
        int i2 = this.h;
        this.h = i2 + 1;
        decoderInputBufferArr[i2] = decoderInputBuffer;
    }

    static /* synthetic */ void a(SimpleDecoder simpleDecoder) {
        do {
            try {
            } catch (InterruptedException e2) {
                throw new IllegalStateException(e2);
            }
        } while (simpleDecoder.l());
    }

    /* access modifiers changed from: private */
    /* renamed from: i */
    public DecoderInputBuffer a() {
        DecoderInputBuffer decoderInputBuffer;
        synchronized (this.a) {
            k();
            Assertions.b(this.i == null);
            int i2 = this.h;
            if (i2 == 0) {
                decoderInputBuffer = null;
            } else {
                DecoderInputBuffer[] decoderInputBufferArr = this.g;
                int i3 = i2 - 1;
                this.h = i3;
                decoderInputBuffer = decoderInputBufferArr[i3];
            }
            this.i = decoderInputBuffer;
        }
        return decoderInputBuffer;
    }

    /* access modifiers changed from: private */
    /* renamed from: j */
    public OutputBuffer b() {
        synchronized (this.a) {
            k();
            if (this.f.isEmpty()) {
                return null;
            }
            OutputBuffer outputBuffer = (OutputBuffer) this.f.removeFirst();
            return outputBuffer;
        }
    }

    private void k() {
        DecoderException decoderException = this.j;
        if (decoderException != null) {
            throw decoderException;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0035, code lost:
        if (r1.c() == false) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0037, code lost:
        r3.a_(4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0040, code lost:
        if (r1.d_() == false) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0042, code lost:
        r3.a_(Integer.MIN_VALUE);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r0 = a(r1, r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004f, code lost:
        r0 = a(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean l() {
        /*
            r6 = this;
            java.lang.Object r0 = r6.a
            monitor-enter(r0)
        L_0x0003:
            boolean r1 = r6.l     // Catch:{ all -> 0x0085 }
            if (r1 != 0) goto L_0x0013
            boolean r1 = r6.m()     // Catch:{ all -> 0x0085 }
            if (r1 != 0) goto L_0x0013
            java.lang.Object r1 = r6.a     // Catch:{ all -> 0x0085 }
            r1.wait()     // Catch:{ all -> 0x0085 }
            goto L_0x0003
        L_0x0013:
            boolean r1 = r6.l     // Catch:{ all -> 0x0085 }
            r2 = 0
            if (r1 == 0) goto L_0x001a
            monitor-exit(r0)     // Catch:{ all -> 0x0085 }
            return r2
        L_0x001a:
            java.util.ArrayDeque r1 = r6.e     // Catch:{ all -> 0x0085 }
            java.lang.Object r1 = r1.removeFirst()     // Catch:{ all -> 0x0085 }
            com.google.android.exoplayer2.decoder.DecoderInputBuffer r1 = (com.google.android.exoplayer2.decoder.DecoderInputBuffer) r1     // Catch:{ all -> 0x0085 }
            com.google.android.exoplayer2.decoder.OutputBuffer[] r3 = r6.b     // Catch:{ all -> 0x0085 }
            int r4 = r6.c     // Catch:{ all -> 0x0085 }
            r5 = 1
            int r4 = r4 - r5
            r6.c = r4     // Catch:{ all -> 0x0085 }
            r3 = r3[r4]     // Catch:{ all -> 0x0085 }
            boolean r4 = r6.k     // Catch:{ all -> 0x0085 }
            r6.k = r2     // Catch:{ all -> 0x0085 }
            monitor-exit(r0)     // Catch:{ all -> 0x0085 }
            boolean r0 = r1.c()
            if (r0 == 0) goto L_0x003c
            r0 = 4
            r3.a_(r0)
            goto L_0x005f
        L_0x003c:
            boolean r0 = r1.d_()
            if (r0 == 0) goto L_0x0047
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            r3.a_(r0)
        L_0x0047:
            com.google.android.exoplayer2.decoder.DecoderException r0 = r6.a(r1, r3, r4)     // Catch:{ RuntimeException -> 0x004e, OutOfMemoryError -> 0x004c }
            goto L_0x0053
        L_0x004c:
            r0 = move-exception
            goto L_0x004f
        L_0x004e:
            r0 = move-exception
        L_0x004f:
            com.google.android.exoplayer2.decoder.DecoderException r0 = r6.a((java.lang.Throwable) r0)
        L_0x0053:
            if (r0 == 0) goto L_0x005f
            java.lang.Object r1 = r6.a
            monitor-enter(r1)
            r6.j = r0     // Catch:{ all -> 0x005c }
            monitor-exit(r1)     // Catch:{ all -> 0x005c }
            return r2
        L_0x005c:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        L_0x005f:
            java.lang.Object r0 = r6.a
            monitor-enter(r0)
            boolean r4 = r6.k     // Catch:{ all -> 0x0082 }
            if (r4 == 0) goto L_0x006a
        L_0x0066:
            r3.f()     // Catch:{ all -> 0x0082 }
            goto L_0x007d
        L_0x006a:
            boolean r4 = r3.d_()     // Catch:{ all -> 0x0082 }
            if (r4 == 0) goto L_0x0076
            int r2 = r6.m     // Catch:{ all -> 0x0082 }
            int r2 = r2 + r5
            r6.m = r2     // Catch:{ all -> 0x0082 }
            goto L_0x0066
        L_0x0076:
            r6.m = r2     // Catch:{ all -> 0x0082 }
            java.util.ArrayDeque r2 = r6.f     // Catch:{ all -> 0x0082 }
            r2.addLast(r3)     // Catch:{ all -> 0x0082 }
        L_0x007d:
            r6.a((com.google.android.exoplayer2.decoder.DecoderInputBuffer) r1)     // Catch:{ all -> 0x0082 }
            monitor-exit(r0)     // Catch:{ all -> 0x0082 }
            return r5
        L_0x0082:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        L_0x0085:
            r1 = move-exception
            monitor-exit(r0)
            goto L_0x0089
        L_0x0088:
            throw r1
        L_0x0089:
            goto L_0x0088
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.decoder.SimpleDecoder.l():boolean");
    }

    private boolean m() {
        return !this.e.isEmpty() && this.c > 0;
    }

    /* access modifiers changed from: protected */
    public abstract DecoderException a(DecoderInputBuffer decoderInputBuffer, OutputBuffer outputBuffer, boolean z);

    /* access modifiers changed from: protected */
    public abstract DecoderException a(Throwable th);

    public final /* synthetic */ void a(Object obj) {
        DecoderInputBuffer decoderInputBuffer = (DecoderInputBuffer) obj;
        synchronized (this.a) {
            k();
            Assertions.a(decoderInputBuffer == this.i);
            this.e.addLast(decoderInputBuffer);
            f();
            this.i = null;
        }
    }

    public final void c() {
        synchronized (this.a) {
            this.k = true;
            this.m = 0;
            DecoderInputBuffer decoderInputBuffer = this.i;
            if (decoderInputBuffer != null) {
                a(decoderInputBuffer);
                this.i = null;
            }
            while (!this.e.isEmpty()) {
                a((DecoderInputBuffer) this.e.removeFirst());
            }
            while (!this.f.isEmpty()) {
                ((OutputBuffer) this.f.removeFirst()).f();
            }
        }
    }

    public final void d() {
        synchronized (this.a) {
            this.l = true;
            this.a.notify();
        }
        try {
            this.d.join();
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
        }
    }

    /* access modifiers changed from: protected */
    public final void e() {
        Assertions.b(this.h == this.g.length);
        for (DecoderInputBuffer d2 : this.g) {
            d2.d(1024);
        }
    }

    public final void f() {
        if (m()) {
            this.a.notify();
        }
    }

    /* access modifiers changed from: protected */
    public abstract DecoderInputBuffer g();

    /* access modifiers changed from: protected */
    public abstract OutputBuffer h();
}
