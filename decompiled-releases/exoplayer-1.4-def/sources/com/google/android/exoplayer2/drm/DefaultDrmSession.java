package com.google.android.exoplayer2.drm;

import android.media.NotProvisionedException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Pair;
import androidx.core.location.LocationRequestCompat;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.drm.ExoMediaDrm;
import com.google.android.exoplayer2.source.LoadEventInfo;
import com.google.android.exoplayer2.source.MediaLoadData;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Consumer;
import com.google.android.exoplayer2.util.CopyOnWriteMultiset;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

class DefaultDrmSession implements DrmSession {
    public final List a;
    final int b = 0;
    final MediaDrmCallback c;
    final UUID d;
    final ResponseHandler e;
    int f;
    byte[] g;
    private final ExoMediaDrm h;
    private final ProvisioningManager i;
    private final ReferenceCountListener j;
    private final boolean k;
    private final boolean l;
    private final HashMap m;
    private final CopyOnWriteMultiset n;
    /* access modifiers changed from: private */
    public final LoadErrorHandlingPolicy o;
    private int p;
    private HandlerThread q;
    private RequestHandler r;
    private ExoMediaCrypto s;
    private DrmSession.DrmSessionException t;
    private byte[] u;
    private ExoMediaDrm.KeyRequest v;
    private ExoMediaDrm.ProvisionRequest w;

    public interface ProvisioningManager {
        void a();

        void a(DefaultDrmSession defaultDrmSession);

        void a(Exception exc);
    }

    public interface ReferenceCountListener {
        void a(DefaultDrmSession defaultDrmSession);

        void a(DefaultDrmSession defaultDrmSession, int i);
    }

    class RequestHandler extends Handler {
        private boolean a;

        public RequestHandler(Looper looper) {
            super(looper);
        }

        private boolean a(Message message, MediaDrmCallbackException mediaDrmCallbackException) {
            RequestTask requestTask = (RequestTask) message.obj;
            if (!requestTask.a) {
                return false;
            }
            requestTask.c++;
            if (requestTask.c > DefaultDrmSession.this.o.a(3)) {
                return false;
            }
            SystemClock.elapsedRealtime();
            SystemClock.elapsedRealtime();
            new LoadEventInfo((byte) 0);
            new MediaLoadData(3);
            long b2 = DefaultDrmSession.this.o.b(new LoadErrorHandlingPolicy.LoadErrorInfo(mediaDrmCallbackException.getCause() instanceof IOException ? (IOException) mediaDrmCallbackException.getCause() : new UnexpectedDrmSessionException(mediaDrmCallbackException.getCause()), requestTask.c));
            if (b2 == -9223372036854775807L) {
                return false;
            }
            synchronized (this) {
                if (this.a) {
                    return false;
                }
                sendMessageDelayed(Message.obtain(message), b2);
                return true;
            }
        }

        public final synchronized void a() {
            removeCallbacksAndMessages((Object) null);
            this.a = true;
        }

        /* access modifiers changed from: package-private */
        public final void a(int i, Object obj, boolean z) {
            LoadEventInfo.a();
            SystemClock.elapsedRealtime();
            obtainMessage(i, new RequestTask(z, obj)).sendToTarget();
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: com.google.android.exoplayer2.drm.MediaDrmCallbackException} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v11, resolved type: com.google.android.exoplayer2.drm.MediaDrmCallbackException} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: com.google.android.exoplayer2.drm.MediaDrmCallbackException} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: byte[]} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v14, resolved type: byte[]} */
        /* JADX WARNING: type inference failed for: r1v2, types: [java.lang.Throwable, java.lang.Exception] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void handleMessage(android.os.Message r5) {
            /*
                r4 = this;
                java.lang.Object r0 = r5.obj
                com.google.android.exoplayer2.drm.DefaultDrmSession$RequestTask r0 = (com.google.android.exoplayer2.drm.DefaultDrmSession.RequestTask) r0
                int r1 = r5.what     // Catch:{ MediaDrmCallbackException -> 0x0037, Exception -> 0x002e }
                switch(r1) {
                    case 0: goto L_0x001d;
                    case 1: goto L_0x000c;
                    default: goto L_0x0009;
                }     // Catch:{ MediaDrmCallbackException -> 0x0037, Exception -> 0x002e }
            L_0x0009:
                java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ MediaDrmCallbackException -> 0x0037, Exception -> 0x002e }
                goto L_0x002a
            L_0x000c:
                com.google.android.exoplayer2.drm.DefaultDrmSession r1 = com.google.android.exoplayer2.drm.DefaultDrmSession.this     // Catch:{ MediaDrmCallbackException -> 0x0037, Exception -> 0x002e }
                com.google.android.exoplayer2.drm.MediaDrmCallback r1 = r1.c     // Catch:{ MediaDrmCallbackException -> 0x0037, Exception -> 0x002e }
                com.google.android.exoplayer2.drm.DefaultDrmSession r2 = com.google.android.exoplayer2.drm.DefaultDrmSession.this     // Catch:{ MediaDrmCallbackException -> 0x0037, Exception -> 0x002e }
                java.util.UUID r2 = r2.d     // Catch:{ MediaDrmCallbackException -> 0x0037, Exception -> 0x002e }
                java.lang.Object r3 = r0.b     // Catch:{ MediaDrmCallbackException -> 0x0037, Exception -> 0x002e }
                com.google.android.exoplayer2.drm.ExoMediaDrm$KeyRequest r3 = (com.google.android.exoplayer2.drm.ExoMediaDrm.KeyRequest) r3     // Catch:{ MediaDrmCallbackException -> 0x0037, Exception -> 0x002e }
                byte[] r1 = r1.a(r2, r3)     // Catch:{ MediaDrmCallbackException -> 0x0037, Exception -> 0x002e }
                goto L_0x003f
            L_0x001d:
                com.google.android.exoplayer2.drm.DefaultDrmSession r1 = com.google.android.exoplayer2.drm.DefaultDrmSession.this     // Catch:{ MediaDrmCallbackException -> 0x0037, Exception -> 0x002e }
                com.google.android.exoplayer2.drm.MediaDrmCallback r1 = r1.c     // Catch:{ MediaDrmCallbackException -> 0x0037, Exception -> 0x002e }
                java.lang.Object r2 = r0.b     // Catch:{ MediaDrmCallbackException -> 0x0037, Exception -> 0x002e }
                com.google.android.exoplayer2.drm.ExoMediaDrm$ProvisionRequest r2 = (com.google.android.exoplayer2.drm.ExoMediaDrm.ProvisionRequest) r2     // Catch:{ MediaDrmCallbackException -> 0x0037, Exception -> 0x002e }
                byte[] r1 = r1.a(r2)     // Catch:{ MediaDrmCallbackException -> 0x0037, Exception -> 0x002e }
                goto L_0x003f
            L_0x002a:
                r1.<init>()     // Catch:{ MediaDrmCallbackException -> 0x0037, Exception -> 0x002e }
                throw r1     // Catch:{ MediaDrmCallbackException -> 0x0037, Exception -> 0x002e }
            L_0x002e:
                r1 = move-exception
                java.lang.String r2 = "DefaultDrmSession"
                java.lang.String r3 = "Key/provisioning request produced an unexpected exception. Not retrying."
                com.google.android.exoplayer2.util.Log.a(r2, r3, r1)
                goto L_0x003f
            L_0x0037:
                r1 = move-exception
                boolean r2 = r4.a(r5, r1)
                if (r2 == 0) goto L_0x003f
                return
            L_0x003f:
                com.google.android.exoplayer2.drm.DefaultDrmSession r2 = com.google.android.exoplayer2.drm.DefaultDrmSession.this
                com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy r2 = r2.o
                r2.a()
                monitor-enter(r4)
                boolean r2 = r4.a     // Catch:{ all -> 0x0062 }
                if (r2 != 0) goto L_0x0060
                com.google.android.exoplayer2.drm.DefaultDrmSession r2 = com.google.android.exoplayer2.drm.DefaultDrmSession.this     // Catch:{ all -> 0x0062 }
                com.google.android.exoplayer2.drm.DefaultDrmSession$ResponseHandler r2 = r2.e     // Catch:{ all -> 0x0062 }
                int r5 = r5.what     // Catch:{ all -> 0x0062 }
                java.lang.Object r0 = r0.b     // Catch:{ all -> 0x0062 }
                android.util.Pair r0 = android.util.Pair.create(r0, r1)     // Catch:{ all -> 0x0062 }
                android.os.Message r5 = r2.obtainMessage(r5, r0)     // Catch:{ all -> 0x0062 }
                r5.sendToTarget()     // Catch:{ all -> 0x0062 }
            L_0x0060:
                monitor-exit(r4)     // Catch:{ all -> 0x0062 }
                return
            L_0x0062:
                r5 = move-exception
                monitor-exit(r4)
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.drm.DefaultDrmSession.RequestHandler.handleMessage(android.os.Message):void");
        }
    }

    final class RequestTask {
        public final boolean a;
        public final Object b;
        public int c;

        public RequestTask(boolean z, Object obj) {
            this.a = z;
            this.b = obj;
        }
    }

    class ResponseHandler extends Handler {
        public ResponseHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            Pair pair = (Pair) message.obj;
            Object obj = pair.first;
            Object obj2 = pair.second;
            switch (message.what) {
                case 0:
                    DefaultDrmSession.a(DefaultDrmSession.this, obj, obj2);
                    return;
                case 1:
                    DefaultDrmSession.b(DefaultDrmSession.this, obj, obj2);
                    return;
                default:
                    return;
            }
        }
    }

    public final class UnexpectedDrmSessionException extends IOException {
        public UnexpectedDrmSessionException(Throwable th) {
            super(th);
        }
    }

    public DefaultDrmSession(UUID uuid, ExoMediaDrm exoMediaDrm, ProvisioningManager provisioningManager, ReferenceCountListener referenceCountListener, List list, boolean z, boolean z2, byte[] bArr, HashMap hashMap, MediaDrmCallback mediaDrmCallback, Looper looper, LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
        List list2;
        this.d = uuid;
        this.i = provisioningManager;
        this.j = referenceCountListener;
        this.h = exoMediaDrm;
        this.k = z;
        this.l = z2;
        if (bArr != null) {
            this.u = bArr;
            list2 = null;
        } else {
            list2 = Collections.unmodifiableList((List) Assertions.b((Object) list));
        }
        this.a = list2;
        this.m = hashMap;
        this.c = mediaDrmCallback;
        this.n = new CopyOnWriteMultiset();
        this.o = loadErrorHandlingPolicy;
        this.f = 2;
        this.e = new ResponseHandler(looper);
    }

    static /* synthetic */ void a(DefaultDrmSession defaultDrmSession, Object obj, Object obj2) {
        if (obj != defaultDrmSession.w) {
            return;
        }
        if (defaultDrmSession.f == 2 || defaultDrmSession.j()) {
            defaultDrmSession.w = null;
            if (obj2 instanceof Exception) {
                defaultDrmSession.i.a((Exception) obj2);
                return;
            }
            try {
                defaultDrmSession.h.b((byte[]) obj2);
                defaultDrmSession.i.a();
            } catch (Exception e2) {
                defaultDrmSession.i.a(e2);
            }
        }
    }

    private void a(Consumer consumer) {
        for (DrmSessionEventListener.EventDispatcher a2 : this.n.a()) {
            consumer.a(a2);
        }
    }

    private void a(byte[] bArr, int i2, boolean z) {
        try {
            this.v = this.h.a(bArr, this.a, i2, this.m);
            ((RequestHandler) Util.a((Object) this.r)).a(1, Assertions.b((Object) this.v), z);
        } catch (Exception e2) {
            b(e2);
        }
    }

    static /* synthetic */ void b(DefaultDrmSession defaultDrmSession, Object obj, Object obj2) {
        if (obj == defaultDrmSession.v && defaultDrmSession.j()) {
            defaultDrmSession.v = null;
            if (obj2 instanceof Exception) {
                defaultDrmSession.b((Exception) obj2);
                return;
            }
            try {
                byte[] a2 = defaultDrmSession.h.a(defaultDrmSession.g, (byte[]) obj2);
                if (!(defaultDrmSession.u == null || a2 == null || a2.length == 0)) {
                    defaultDrmSession.u = a2;
                }
                defaultDrmSession.f = 4;
                defaultDrmSession.a(DefaultDrmSession$$Lambda$3.a);
            } catch (Exception e2) {
                defaultDrmSession.b(e2);
            }
        }
    }

    private void b(Exception exc) {
        if (exc instanceof NotProvisionedException) {
            this.i.a(this);
        } else {
            a(exc);
        }
    }

    private boolean h() {
        try {
            this.h.b(this.g, this.u);
            return true;
        } catch (Exception e2) {
            a(e2);
            return false;
        }
    }

    private long i() {
        if (!C.d.equals(this.d)) {
            return LocationRequestCompat.PASSIVE_INTERVAL;
        }
        Pair pair = (Pair) Assertions.b((Object) WidevineUtil.a(this));
        return Math.min(((Long) pair.first).longValue(), ((Long) pair.second).longValue());
    }

    private boolean j() {
        int i2 = this.f;
        return i2 == 3 || i2 == 4;
    }

    public final void a() {
        this.w = this.h.b();
        ((RequestHandler) Util.a((Object) this.r)).a(0, Assertions.b((Object) this.w), true);
    }

    public final void a(DrmSessionEventListener.EventDispatcher eventDispatcher) {
        boolean z = false;
        Assertions.b(this.p >= 0);
        if (eventDispatcher != null) {
            CopyOnWriteMultiset copyOnWriteMultiset = this.n;
            synchronized (copyOnWriteMultiset.a) {
                ArrayList arrayList = new ArrayList(copyOnWriteMultiset.d);
                arrayList.add(eventDispatcher);
                copyOnWriteMultiset.d = Collections.unmodifiableList(arrayList);
                Integer num = (Integer) copyOnWriteMultiset.b.get(eventDispatcher);
                if (num == null) {
                    HashSet hashSet = new HashSet(copyOnWriteMultiset.c);
                    hashSet.add(eventDispatcher);
                    copyOnWriteMultiset.c = Collections.unmodifiableSet(hashSet);
                }
                copyOnWriteMultiset.b.put(eventDispatcher, Integer.valueOf(num != null ? num.intValue() + 1 : 1));
            }
        }
        int i2 = this.p + 1;
        this.p = i2;
        if (i2 == 1) {
            if (this.f == 2) {
                z = true;
            }
            Assertions.b(z);
            HandlerThread handlerThread = new HandlerThread("ExoPlayer:DrmRequestHandler");
            this.q = handlerThread;
            handlerThread.start();
            this.r = new RequestHandler(this.q.getLooper());
            if (a(true)) {
                b(true);
            }
        } else if (eventDispatcher != null && j() && this.n.a(eventDispatcher) == 1) {
            eventDispatcher.a(this.f);
        }
        this.j.a(this);
    }

    /* access modifiers changed from: package-private */
    public final void a(Exception exc) {
        this.t = new DrmSession.DrmSessionException(exc);
        Log.b("DefaultDrmSession", "DRM session error", exc);
        a((Consumer) new DefaultDrmSession$$Lambda$4(exc));
        if (this.f != 4) {
            this.f = 1;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean a(boolean z) {
        if (j()) {
            return true;
        }
        try {
            byte[] a2 = this.h.a();
            this.g = a2;
            this.s = this.h.d(a2);
            this.f = 3;
            a((Consumer) new DefaultDrmSession$$Lambda$0(3));
            Assertions.b((Object) this.g);
            return true;
        } catch (NotProvisionedException e2) {
            if (z) {
                this.i.a(this);
                return false;
            }
            a((Exception) e2);
            return false;
        } catch (Exception e3) {
            a(e3);
            return false;
        }
    }

    public final int b() {
        return this.f;
    }

    public final void b(DrmSessionEventListener.EventDispatcher eventDispatcher) {
        Assertions.b(this.p > 0);
        int i2 = this.p - 1;
        this.p = i2;
        if (i2 == 0) {
            this.f = 0;
            ((ResponseHandler) Util.a((Object) this.e)).removeCallbacksAndMessages((Object) null);
            ((RequestHandler) Util.a((Object) this.r)).a();
            this.r = null;
            ((HandlerThread) Util.a((Object) this.q)).quit();
            this.q = null;
            this.s = null;
            this.t = null;
            this.v = null;
            this.w = null;
            byte[] bArr = this.g;
            if (bArr != null) {
                this.h.a(bArr);
                this.g = null;
            }
        }
        if (eventDispatcher != null) {
            CopyOnWriteMultiset copyOnWriteMultiset = this.n;
            synchronized (copyOnWriteMultiset.a) {
                Integer num = (Integer) copyOnWriteMultiset.b.get(eventDispatcher);
                if (num != null) {
                    ArrayList arrayList = new ArrayList(copyOnWriteMultiset.d);
                    arrayList.remove(eventDispatcher);
                    copyOnWriteMultiset.d = Collections.unmodifiableList(arrayList);
                    if (num.intValue() == 1) {
                        copyOnWriteMultiset.b.remove(eventDispatcher);
                        HashSet hashSet = new HashSet(copyOnWriteMultiset.c);
                        hashSet.remove(eventDispatcher);
                        copyOnWriteMultiset.c = Collections.unmodifiableSet(hashSet);
                    } else {
                        copyOnWriteMultiset.b.put(eventDispatcher, Integer.valueOf(num.intValue() - 1));
                    }
                }
            }
            if (this.n.a(eventDispatcher) == 0) {
                eventDispatcher.c();
            }
        }
        this.j.a(this, this.p);
    }

    /* access modifiers changed from: package-private */
    public final void b(boolean z) {
        if (!this.l) {
            byte[] bArr = (byte[]) Util.a((Object) this.g);
            if (this.u == null) {
                a(bArr, 1, z);
            } else if (this.f == 4 || h()) {
                long i2 = i();
                if (i2 <= 60) {
                    Log.a("DefaultDrmSession", new StringBuilder(88).append("Offline license has expired or will expire soon. Remaining seconds: ").append(i2).toString());
                    a(bArr, 2, z);
                } else if (i2 <= 0) {
                    a((Exception) new KeysExpiredException());
                } else {
                    this.f = 4;
                    a(DefaultDrmSession$$Lambda$1.a);
                }
            }
        }
    }

    public final boolean c() {
        return this.k;
    }

    public final DrmSession.DrmSessionException d() {
        if (this.f == 1) {
            return this.t;
        }
        return null;
    }

    public final UUID e() {
        return this.d;
    }

    public final ExoMediaCrypto f() {
        return this.s;
    }

    public final Map g() {
        byte[] bArr = this.g;
        if (bArr == null) {
            return null;
        }
        return this.h.c(bArr);
    }
}
