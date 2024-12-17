package com.google.android.exoplayer2.upstream;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

public final class Loader implements LoaderErrorThrower {
    public static final LoadErrorAction a = a(false, -9223372036854775807L);
    public static final LoadErrorAction b = new LoadErrorAction(2, -9223372036854775807L, (byte) 0);
    public static final LoadErrorAction c = new LoadErrorAction(3, -9223372036854775807L, (byte) 0);
    public IOException d;
    /* access modifiers changed from: private */
    public final ExecutorService e;
    /* access modifiers changed from: private */
    public LoadTask f;

    public interface Callback {
        LoadErrorAction a(Loadable loadable, IOException iOException, int i);

        void a(Loadable loadable, long j, long j2);

        void a(Loadable loadable, boolean z);
    }

    public final class LoadErrorAction {
        /* access modifiers changed from: private */
        public final int a;
        /* access modifiers changed from: private */
        public final long b;

        private LoadErrorAction(int i, long j) {
            this.a = i;
            this.b = j;
        }

        /* synthetic */ LoadErrorAction(int i, long j, byte b2) {
            this(i, j);
        }

        public final boolean a() {
            int i = this.a;
            return i == 0 || i == 1;
        }
    }

    final class LoadTask extends Handler implements Runnable {
        public final int a;
        private final Loadable b;
        private final long c;
        private Callback d;
        private IOException e;
        private int f;
        private Thread g;
        private boolean h;
        private volatile boolean i;

        public LoadTask(Looper looper, Loadable loadable, Callback callback, int i2, long j2) {
            super(looper);
            this.b = loadable;
            this.d = callback;
            this.a = i2;
            this.c = j2;
        }

        private void a() {
            this.e = null;
            Loader.this.e.execute((Runnable) Assertions.b((Object) Loader.this.f));
        }

        private void b() {
            LoadTask unused = Loader.this.f = null;
        }

        public final void a(int i2) {
            IOException iOException = this.e;
            if (iOException != null && this.f > i2) {
                throw iOException;
            }
        }

        public final void a(long j2) {
            Assertions.b(Loader.this.f == null);
            LoadTask unused = Loader.this.f = this;
            if (j2 > 0) {
                sendEmptyMessageDelayed(0, j2);
            } else {
                a();
            }
        }

        public final void a(boolean z) {
            this.i = z;
            this.e = null;
            if (hasMessages(0)) {
                this.h = true;
                removeMessages(0);
                if (!z) {
                    sendEmptyMessage(1);
                }
            } else {
                synchronized (this) {
                    this.h = true;
                    this.b.a();
                    Thread thread = this.g;
                    if (thread != null) {
                        thread.interrupt();
                    }
                }
            }
            if (z) {
                b();
                SystemClock.elapsedRealtime();
                ((Callback) Assertions.b((Object) this.d)).a(this.b, true);
                this.d = null;
            }
        }

        public final void handleMessage(Message message) {
            if (!this.i) {
                if (message.what == 0) {
                    a();
                } else if (message.what != 3) {
                    b();
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    long j2 = elapsedRealtime - this.c;
                    Callback callback = (Callback) Assertions.b((Object) this.d);
                    if (this.h) {
                        callback.a(this.b, false);
                        return;
                    }
                    switch (message.what) {
                        case 1:
                            try {
                                callback.a(this.b, elapsedRealtime, j2);
                                return;
                            } catch (RuntimeException e2) {
                                Log.b("LoadTask", "Unexpected exception handling load completed", e2);
                                IOException unused = Loader.this.d = new UnexpectedLoaderException(e2);
                                return;
                            }
                        case 2:
                            IOException iOException = (IOException) message.obj;
                            this.e = iOException;
                            int i2 = this.f + 1;
                            this.f = i2;
                            LoadErrorAction a2 = callback.a(this.b, iOException, i2);
                            if (a2.a == 3) {
                                IOException unused2 = Loader.this.d = this.e;
                                return;
                            } else if (a2.a != 2) {
                                if (a2.a == 1) {
                                    this.f = 1;
                                }
                                a(a2.b != -9223372036854775807L ? a2.b : (long) Math.min((this.f - 1) * 1000, 5000));
                                return;
                            } else {
                                return;
                            }
                        default:
                            return;
                    }
                } else {
                    throw ((Error) message.obj);
                }
            }
        }

        /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void run() {
            /*
                r5 = this;
                r0 = 2
                monitor-enter(r5)     // Catch:{ IOException -> 0x00a2, Exception -> 0x0089, OutOfMemoryError -> 0x0070, Error -> 0x005b }
                boolean r1 = r5.h     // Catch:{ all -> 0x0058 }
                r2 = 1
                if (r1 != 0) goto L_0x0009
                r1 = 1
                goto L_0x000a
            L_0x0009:
                r1 = 0
            L_0x000a:
                java.lang.Thread r3 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0058 }
                r5.g = r3     // Catch:{ all -> 0x0058 }
                monitor-exit(r5)     // Catch:{ all -> 0x0058 }
                if (r1 == 0) goto L_0x0045
                java.lang.String r1 = "load:"
                com.google.android.exoplayer2.upstream.Loader$Loadable r3 = r5.b     // Catch:{ IOException -> 0x00a2, Exception -> 0x0089, OutOfMemoryError -> 0x0070, Error -> 0x005b }
                java.lang.Class r3 = r3.getClass()     // Catch:{ IOException -> 0x00a2, Exception -> 0x0089, OutOfMemoryError -> 0x0070, Error -> 0x005b }
                java.lang.String r3 = r3.getSimpleName()     // Catch:{ IOException -> 0x00a2, Exception -> 0x0089, OutOfMemoryError -> 0x0070, Error -> 0x005b }
                java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ IOException -> 0x00a2, Exception -> 0x0089, OutOfMemoryError -> 0x0070, Error -> 0x005b }
                int r4 = r3.length()     // Catch:{ IOException -> 0x00a2, Exception -> 0x0089, OutOfMemoryError -> 0x0070, Error -> 0x005b }
                if (r4 == 0) goto L_0x002e
                java.lang.String r1 = r1.concat(r3)     // Catch:{ IOException -> 0x00a2, Exception -> 0x0089, OutOfMemoryError -> 0x0070, Error -> 0x005b }
                goto L_0x0034
            L_0x002e:
                java.lang.String r3 = new java.lang.String     // Catch:{ IOException -> 0x00a2, Exception -> 0x0089, OutOfMemoryError -> 0x0070, Error -> 0x005b }
                r3.<init>(r1)     // Catch:{ IOException -> 0x00a2, Exception -> 0x0089, OutOfMemoryError -> 0x0070, Error -> 0x005b }
                r1 = r3
            L_0x0034:
                com.google.android.exoplayer2.util.TraceUtil.a(r1)     // Catch:{ IOException -> 0x00a2, Exception -> 0x0089, OutOfMemoryError -> 0x0070, Error -> 0x005b }
                com.google.android.exoplayer2.upstream.Loader$Loadable r1 = r5.b     // Catch:{ all -> 0x0040 }
                r1.b()     // Catch:{ all -> 0x0040 }
                com.google.android.exoplayer2.util.TraceUtil.a()     // Catch:{ IOException -> 0x00a2, Exception -> 0x0089, OutOfMemoryError -> 0x0070, Error -> 0x005b }
                goto L_0x0045
            L_0x0040:
                r1 = move-exception
                com.google.android.exoplayer2.util.TraceUtil.a()     // Catch:{ IOException -> 0x00a2, Exception -> 0x0089, OutOfMemoryError -> 0x0070, Error -> 0x005b }
                throw r1     // Catch:{ IOException -> 0x00a2, Exception -> 0x0089, OutOfMemoryError -> 0x0070, Error -> 0x005b }
            L_0x0045:
                monitor-enter(r5)     // Catch:{ IOException -> 0x00a2, Exception -> 0x0089, OutOfMemoryError -> 0x0070, Error -> 0x005b }
                r1 = 0
                r5.g = r1     // Catch:{ all -> 0x0055 }
                java.lang.Thread.interrupted()     // Catch:{ all -> 0x0055 }
                monitor-exit(r5)     // Catch:{ all -> 0x0055 }
                boolean r1 = r5.i     // Catch:{ IOException -> 0x00a2, Exception -> 0x0089, OutOfMemoryError -> 0x0070, Error -> 0x005b }
                if (r1 != 0) goto L_0x0054
                r5.sendEmptyMessage(r2)     // Catch:{ IOException -> 0x00a2, Exception -> 0x0089, OutOfMemoryError -> 0x0070, Error -> 0x005b }
            L_0x0054:
                return
            L_0x0055:
                r1 = move-exception
                monitor-exit(r5)     // Catch:{ IOException -> 0x00a2, Exception -> 0x0089, OutOfMemoryError -> 0x0070, Error -> 0x005b }
                throw r1     // Catch:{ IOException -> 0x00a2, Exception -> 0x0089, OutOfMemoryError -> 0x0070, Error -> 0x005b }
            L_0x0058:
                r1 = move-exception
                monitor-exit(r5)     // Catch:{ IOException -> 0x00a2, Exception -> 0x0089, OutOfMemoryError -> 0x0070, Error -> 0x005b }
                throw r1     // Catch:{ IOException -> 0x00a2, Exception -> 0x0089, OutOfMemoryError -> 0x0070, Error -> 0x005b }
            L_0x005b:
                r0 = move-exception
                boolean r1 = r5.i
                if (r1 != 0) goto L_0x006f
                java.lang.String r1 = "LoadTask"
                java.lang.String r2 = "Unexpected error loading stream"
                com.google.android.exoplayer2.util.Log.b(r1, r2, r0)
                r1 = 3
                android.os.Message r1 = r5.obtainMessage(r1, r0)
                r1.sendToTarget()
            L_0x006f:
                throw r0
            L_0x0070:
                r1 = move-exception
                boolean r2 = r5.i
                if (r2 != 0) goto L_0x0088
                java.lang.String r2 = "LoadTask"
                java.lang.String r3 = "OutOfMemory error loading stream"
                com.google.android.exoplayer2.util.Log.b(r2, r3, r1)
                com.google.android.exoplayer2.upstream.Loader$UnexpectedLoaderException r2 = new com.google.android.exoplayer2.upstream.Loader$UnexpectedLoaderException
                r2.<init>(r1)
                android.os.Message r0 = r5.obtainMessage(r0, r2)
                r0.sendToTarget()
            L_0x0088:
                return
            L_0x0089:
                r1 = move-exception
                boolean r2 = r5.i
                if (r2 != 0) goto L_0x00a1
                java.lang.String r2 = "LoadTask"
                java.lang.String r3 = "Unexpected exception loading stream"
                com.google.android.exoplayer2.util.Log.b(r2, r3, r1)
                com.google.android.exoplayer2.upstream.Loader$UnexpectedLoaderException r2 = new com.google.android.exoplayer2.upstream.Loader$UnexpectedLoaderException
                r2.<init>(r1)
                android.os.Message r0 = r5.obtainMessage(r0, r2)
                r0.sendToTarget()
            L_0x00a1:
                return
            L_0x00a2:
                r1 = move-exception
                boolean r2 = r5.i
                if (r2 != 0) goto L_0x00ae
                android.os.Message r0 = r5.obtainMessage(r0, r1)
                r0.sendToTarget()
            L_0x00ae:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.upstream.Loader.LoadTask.run():void");
        }
    }

    public interface Loadable {
        void a();

        void b();
    }

    public interface ReleaseCallback {
        void g();
    }

    final class ReleaseTask implements Runnable {
        private final ReleaseCallback a;

        public ReleaseTask(ReleaseCallback releaseCallback) {
            this.a = releaseCallback;
        }

        public final void run() {
            this.a.g();
        }
    }

    public final class UnexpectedLoaderException extends IOException {
        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public UnexpectedLoaderException(java.lang.Throwable r5) {
            /*
                r4 = this;
                java.lang.Class r0 = r5.getClass()
                java.lang.String r0 = r0.getSimpleName()
                java.lang.String r1 = r5.getMessage()
                java.lang.String r2 = java.lang.String.valueOf(r0)
                int r2 = r2.length()
                int r2 = r2 + 13
                java.lang.String r3 = java.lang.String.valueOf(r1)
                int r3 = r3.length()
                int r2 = r2 + r3
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>(r2)
                java.lang.String r2 = "Unexpected "
                java.lang.StringBuilder r2 = r3.append(r2)
                java.lang.StringBuilder r0 = r2.append(r0)
                java.lang.String r2 = ": "
                java.lang.StringBuilder r0 = r0.append(r2)
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.String r0 = r0.toString()
                r4.<init>(r0, r5)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.upstream.Loader.UnexpectedLoaderException.<init>(java.lang.Throwable):void");
        }
    }

    static {
        a(true, -9223372036854775807L);
    }

    public Loader(String str) {
        String valueOf = String.valueOf("ExoPlayer:Loader:");
        String valueOf2 = String.valueOf(str);
        this.e = Util.a(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
    }

    public static LoadErrorAction a(boolean z, long j) {
        return new LoadErrorAction(z ? 1 : 0, j, (byte) 0);
    }

    public final long a(Loadable loadable, Callback callback, int i) {
        this.d = null;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        new LoadTask((Looper) Assertions.a((Object) Looper.myLooper()), loadable, callback, i, elapsedRealtime).a(0);
        return elapsedRealtime;
    }

    public final void a() {
        a(Integer.MIN_VALUE);
    }

    public final void a(int i) {
        IOException iOException = this.d;
        if (iOException == null) {
            LoadTask loadTask = this.f;
            if (loadTask != null) {
                if (i == Integer.MIN_VALUE) {
                    i = loadTask.a;
                }
                loadTask.a(i);
                return;
            }
            return;
        }
        throw iOException;
    }

    public final void a(ReleaseCallback releaseCallback) {
        LoadTask loadTask = this.f;
        if (loadTask != null) {
            loadTask.a(true);
        }
        if (releaseCallback != null) {
            this.e.execute(new ReleaseTask(releaseCallback));
        }
        this.e.shutdown();
    }

    public final boolean b() {
        return this.d != null;
    }

    public final boolean c() {
        return this.f != null;
    }

    public final void d() {
        ((LoadTask) Assertions.a((Object) this.f)).a(false);
    }
}
