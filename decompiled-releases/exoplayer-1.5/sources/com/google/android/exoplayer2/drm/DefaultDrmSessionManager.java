package com.google.android.exoplayer2.drm;

import android.media.ResourceBusyException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.dreamers.exoplayercore.repack.bG;
import com.dreamers.exoplayercore.repack.bU;
import com.dreamers.exoplayercore.repack.cM;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DefaultDrmSession;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.ExoMediaDrm;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class DefaultDrmSessionManager implements DrmSessionManager {
    /* access modifiers changed from: package-private */
    public final List a;
    byte[] b;
    volatile MediaDrmHandler c;
    private final UUID e;
    private final ExoMediaDrm.Provider f;
    private final MediaDrmCallback g;
    private final HashMap h;
    private final boolean i;
    private final int[] j;
    private final boolean k;
    private final ProvisioningManagerImpl l;
    private final LoadErrorHandlingPolicy m;
    private final ReferenceCountListenerImpl n;
    /* access modifiers changed from: private */
    public final long o;
    /* access modifiers changed from: private */
    public final List p;
    /* access modifiers changed from: private */
    public final Set q;
    /* access modifiers changed from: private */
    public final Set r;
    /* access modifiers changed from: private */
    public int s;
    private ExoMediaDrm t;
    /* access modifiers changed from: private */
    public DefaultDrmSession u;
    /* access modifiers changed from: private */
    public DefaultDrmSession v;
    /* access modifiers changed from: private */
    public Looper w;
    /* access modifiers changed from: private */
    public Handler x;

    public final class Builder {
        final HashMap a = new HashMap();
        UUID b = C.d;
        ExoMediaDrm.Provider c = FrameworkMediaDrm.a;
        boolean d;
        int[] e = new int[0];
        boolean f;
        LoadErrorHandlingPolicy g = new DefaultLoadErrorHandlingPolicy();
        long h = 300000;

        public final Builder a(int... iArr) {
            for (int i : iArr) {
                boolean z = true;
                if (!(i == 2 || i == 1)) {
                    z = false;
                }
                Assertions.a(z);
            }
            this.e = (int[]) iArr.clone();
            return this;
        }
    }

    class MediaDrmEventListener implements ExoMediaDrm.OnEventListener {
        private MediaDrmEventListener() {
        }

        /* synthetic */ MediaDrmEventListener(DefaultDrmSessionManager defaultDrmSessionManager, byte b) {
            this();
        }

        public final void a(byte[] bArr, int i) {
            ((MediaDrmHandler) Assertions.b((Object) DefaultDrmSessionManager.this.c)).obtainMessage(i, bArr).sendToTarget();
        }
    }

    class MediaDrmHandler extends Handler {
        public MediaDrmHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            byte[] bArr = (byte[]) message.obj;
            if (bArr != null) {
                for (DefaultDrmSession defaultDrmSession : DefaultDrmSessionManager.this.a) {
                    if (Arrays.equals(defaultDrmSession.g, bArr)) {
                        switch (message.what) {
                            case 2:
                                if (defaultDrmSession.f == 4) {
                                    Util.a((Object) defaultDrmSession.g);
                                    defaultDrmSession.b(false);
                                    return;
                                }
                                return;
                            default:
                                return;
                        }
                    }
                }
            }
        }
    }

    public final class MissingSchemeDataException extends Exception {
        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private MissingSchemeDataException(java.util.UUID r3) {
            /*
                r2 = this;
                java.lang.String r3 = java.lang.String.valueOf(r3)
                java.lang.String r0 = java.lang.String.valueOf(r3)
                int r0 = r0.length()
                int r0 = r0 + 29
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>(r0)
                java.lang.String r0 = "Media does not support uuid: "
                java.lang.StringBuilder r0 = r1.append(r0)
                java.lang.StringBuilder r3 = r0.append(r3)
                java.lang.String r3 = r3.toString()
                r2.<init>(r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.drm.DefaultDrmSessionManager.MissingSchemeDataException.<init>(java.util.UUID):void");
        }

        /* synthetic */ MissingSchemeDataException(UUID uuid, byte b) {
            this(uuid);
        }
    }

    class PreacquiredSessionReference implements DrmSessionManager.DrmSessionReference {
        final DrmSessionEventListener.EventDispatcher a;
        DrmSession b;
        boolean c;

        public PreacquiredSessionReference(DrmSessionEventListener.EventDispatcher eventDispatcher) {
            this.a = eventDispatcher;
        }

        public final void a() {
            Util.a((Handler) Assertions.b((Object) DefaultDrmSessionManager.this.x), (Runnable) new DefaultDrmSessionManager$PreacquiredSessionReference$$Lambda$1(this));
        }
    }

    class ProvisioningManagerImpl implements DefaultDrmSession.ProvisioningManager {
        private ProvisioningManagerImpl() {
        }

        /* synthetic */ ProvisioningManagerImpl(DefaultDrmSessionManager defaultDrmSessionManager, byte b) {
            this();
        }

        public final void a() {
            for (DefaultDrmSession defaultDrmSession : DefaultDrmSessionManager.this.p) {
                if (defaultDrmSession.a(false)) {
                    defaultDrmSession.b(true);
                }
            }
            DefaultDrmSessionManager.this.p.clear();
        }

        public final void a(DefaultDrmSession defaultDrmSession) {
            if (!DefaultDrmSessionManager.this.p.contains(defaultDrmSession)) {
                DefaultDrmSessionManager.this.p.add(defaultDrmSession);
                if (DefaultDrmSessionManager.this.p.size() == 1) {
                    defaultDrmSession.a();
                }
            }
        }

        public final void a(Exception exc) {
            for (DefaultDrmSession a2 : DefaultDrmSessionManager.this.p) {
                a2.a(exc);
            }
            DefaultDrmSessionManager.this.p.clear();
        }
    }

    class ReferenceCountListenerImpl implements DefaultDrmSession.ReferenceCountListener {
        private ReferenceCountListenerImpl() {
        }

        /* synthetic */ ReferenceCountListenerImpl(DefaultDrmSessionManager defaultDrmSessionManager, byte b) {
            this();
        }

        public final void a(DefaultDrmSession defaultDrmSession) {
            if (DefaultDrmSessionManager.this.o != -9223372036854775807L) {
                DefaultDrmSessionManager.this.r.remove(defaultDrmSession);
                ((Handler) Assertions.b((Object) DefaultDrmSessionManager.this.x)).removeCallbacksAndMessages(defaultDrmSession);
            }
        }

        public final void a(DefaultDrmSession defaultDrmSession, int i) {
            if (i == 1 && DefaultDrmSessionManager.this.o != -9223372036854775807L) {
                DefaultDrmSessionManager.this.r.add(defaultDrmSession);
                ((Handler) Assertions.b((Object) DefaultDrmSessionManager.this.x)).postAtTime(new DefaultDrmSessionManager$ReferenceCountListenerImpl$$Lambda$0(defaultDrmSession), defaultDrmSession, SystemClock.uptimeMillis() + DefaultDrmSessionManager.this.o);
            } else if (i == 0) {
                DefaultDrmSessionManager.this.a.remove(defaultDrmSession);
                if (DefaultDrmSessionManager.this.u == defaultDrmSession) {
                    DefaultDrmSession unused = DefaultDrmSessionManager.this.u = null;
                }
                if (DefaultDrmSessionManager.this.v == defaultDrmSession) {
                    DefaultDrmSession unused2 = DefaultDrmSessionManager.this.v = null;
                }
                if (DefaultDrmSessionManager.this.p.size() > 1 && DefaultDrmSessionManager.this.p.get(0) == defaultDrmSession) {
                    ((DefaultDrmSession) DefaultDrmSessionManager.this.p.get(1)).a();
                }
                DefaultDrmSessionManager.this.p.remove(defaultDrmSession);
                if (DefaultDrmSessionManager.this.o != -9223372036854775807L) {
                    ((Handler) Assertions.b((Object) DefaultDrmSessionManager.this.x)).removeCallbacksAndMessages(defaultDrmSession);
                    DefaultDrmSessionManager.this.r.remove(defaultDrmSession);
                }
            }
            DefaultDrmSessionManager.this.d();
        }
    }

    static {
        DrmSessionManager$$CC.d();
    }

    private DefaultDrmSessionManager(UUID uuid, ExoMediaDrm.Provider provider, MediaDrmCallback mediaDrmCallback, HashMap hashMap, boolean z, int[] iArr, boolean z2, LoadErrorHandlingPolicy loadErrorHandlingPolicy, long j2) {
        Assertions.b((Object) uuid);
        Assertions.a(!C.b.equals(uuid), (Object) "Use C.CLEARKEY_UUID instead");
        this.e = uuid;
        this.f = provider;
        this.g = mediaDrmCallback;
        this.h = hashMap;
        this.i = z;
        this.j = iArr;
        this.k = z2;
        this.m = loadErrorHandlingPolicy;
        this.l = new ProvisioningManagerImpl(this, (byte) 0);
        this.n = new ReferenceCountListenerImpl(this, (byte) 0);
        this.a = new ArrayList();
        this.p = new ArrayList();
        this.q = Collections.newSetFromMap(new IdentityHashMap());
        this.r = Collections.newSetFromMap(new IdentityHashMap());
        this.o = j2;
    }

    /* synthetic */ DefaultDrmSessionManager(UUID uuid, ExoMediaDrm.Provider provider, MediaDrmCallback mediaDrmCallback, HashMap hashMap, boolean z, int[] iArr, boolean z2, LoadErrorHandlingPolicy loadErrorHandlingPolicy, long j2, byte b2) {
        this(uuid, provider, mediaDrmCallback, hashMap, z, iArr, z2, loadErrorHandlingPolicy, j2);
    }

    private DefaultDrmSession a(List list, boolean z, DrmSessionEventListener.EventDispatcher eventDispatcher) {
        Assertions.b((Object) this.t);
        DefaultDrmSession defaultDrmSession = new DefaultDrmSession(this.e, this.t, this.l, this.n, list, this.k | z, z, this.b, this.h, this.g, (Looper) Assertions.b((Object) this.w), this.m);
        defaultDrmSession.a(eventDispatcher);
        if (this.o != -9223372036854775807L) {
            defaultDrmSession.a((DrmSessionEventListener.EventDispatcher) null);
        }
        return defaultDrmSession;
    }

    private DefaultDrmSession a(List list, boolean z, DrmSessionEventListener.EventDispatcher eventDispatcher, boolean z2) {
        DefaultDrmSession a2 = a(list, z, eventDispatcher);
        if (a((DrmSession) a2) && !this.r.isEmpty()) {
            cM a3 = bU.a((Collection) this.r).iterator();
            while (a3.hasNext()) {
                ((DrmSession) a3.next()).b((DrmSessionEventListener.EventDispatcher) null);
            }
            a((DrmSession) a2, eventDispatcher);
            a2 = a(list, z, eventDispatcher);
        }
        if (!a((DrmSession) a2) || !z2 || this.q.isEmpty()) {
            return a2;
        }
        c();
        a((DrmSession) a2, eventDispatcher);
        return a(list, z, eventDispatcher);
    }

    private DrmSession a(int i2, boolean z) {
        ExoMediaDrm exoMediaDrm = (ExoMediaDrm) Assertions.b((Object) this.t);
        if ((FrameworkMediaCrypto.class.equals(exoMediaDrm.d()) && FrameworkMediaCrypto.a) || Util.a(this.j, i2) == -1 || UnsupportedMediaCrypto.class.equals(exoMediaDrm.d())) {
            return null;
        }
        DefaultDrmSession defaultDrmSession = this.u;
        if (defaultDrmSession == null) {
            DefaultDrmSession a2 = a((List) bG.g(), true, (DrmSessionEventListener.EventDispatcher) null, z);
            this.a.add(a2);
            this.u = a2;
        } else {
            defaultDrmSession.a((DrmSessionEventListener.EventDispatcher) null);
        }
        return this.u;
    }

    /* access modifiers changed from: private */
    public DrmSession a(Looper looper, DrmSessionEventListener.EventDispatcher eventDispatcher, Format format, boolean z) {
        List list;
        b(looper);
        if (format.o == null) {
            return a(MimeTypes.h(format.l), z);
        }
        DefaultDrmSession defaultDrmSession = null;
        if (this.b == null) {
            list = a((DrmInitData) Assertions.b((Object) format.o), this.e, false);
            if (list.isEmpty()) {
                MissingSchemeDataException missingSchemeDataException = new MissingSchemeDataException(this.e, (byte) 0);
                Log.b("DefaultDrmSessionMgr", "DRM error", missingSchemeDataException);
                if (eventDispatcher != null) {
                    eventDispatcher.a((Exception) missingSchemeDataException);
                }
                return new ErrorStateDrmSession(new DrmSession.DrmSessionException(missingSchemeDataException));
            }
        } else {
            list = null;
        }
        if (this.i) {
            Iterator it = this.a.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DefaultDrmSession defaultDrmSession2 = (DefaultDrmSession) it.next();
                if (Util.a((Object) defaultDrmSession2.a, (Object) list)) {
                    defaultDrmSession = defaultDrmSession2;
                    break;
                }
            }
        } else {
            defaultDrmSession = this.v;
        }
        if (defaultDrmSession == null) {
            defaultDrmSession = a(list, false, eventDispatcher, z);
            if (!this.i) {
                this.v = defaultDrmSession;
            }
            this.a.add(defaultDrmSession);
        } else {
            defaultDrmSession.a(eventDispatcher);
        }
        return defaultDrmSession;
    }

    private static List a(DrmInitData drmInitData, UUID uuid, boolean z) {
        ArrayList arrayList = new ArrayList(drmInitData.c);
        for (int i2 = 0; i2 < drmInitData.c; i2++) {
            DrmInitData.SchemeData schemeData = drmInitData.a[i2];
            if ((schemeData.a(uuid) || (C.c.equals(uuid) && schemeData.a(C.b))) && (schemeData.d != null || z)) {
                arrayList.add(schemeData);
            }
        }
        return arrayList;
    }

    private synchronized void a(Looper looper) {
        Looper looper2 = this.w;
        if (looper2 == null) {
            this.w = looper;
            this.x = new Handler(looper);
            return;
        }
        Assertions.b(looper2 == looper);
        Assertions.b((Object) this.x);
    }

    private void a(DrmSession drmSession, DrmSessionEventListener.EventDispatcher eventDispatcher) {
        drmSession.b(eventDispatcher);
        if (this.o != -9223372036854775807L) {
            drmSession.b((DrmSessionEventListener.EventDispatcher) null);
        }
    }

    private static boolean a(DrmSession drmSession) {
        if (drmSession.b() == 1) {
            return Util.a < 19 || (((DrmSession.DrmSessionException) Assertions.b((Object) drmSession.d())).getCause() instanceof ResourceBusyException);
        }
        return false;
    }

    private void b(Looper looper) {
        if (this.c == null) {
            this.c = new MediaDrmHandler(looper);
        }
    }

    private void c() {
        cM a2 = bU.a((Collection) this.q).iterator();
        while (a2.hasNext()) {
            ((PreacquiredSessionReference) a2.next()).a();
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        if (this.t != null && this.s == 0 && this.a.isEmpty() && this.q.isEmpty()) {
            ((ExoMediaDrm) Assertions.b((Object) this.t)).c();
            this.t = null;
        }
    }

    public final DrmSessionManager.DrmSessionReference a(Looper looper, DrmSessionEventListener.EventDispatcher eventDispatcher, Format format) {
        Assertions.b(this.s > 0);
        a(looper);
        PreacquiredSessionReference preacquiredSessionReference = new PreacquiredSessionReference(eventDispatcher);
        ((Handler) Assertions.b((Object) DefaultDrmSessionManager.this.x)).post(new DefaultDrmSessionManager$PreacquiredSessionReference$$Lambda$0(preacquiredSessionReference, format));
        return preacquiredSessionReference;
    }

    public final Class a(Format format) {
        Class d = ((ExoMediaDrm) Assertions.b((Object) this.t)).d();
        if (format.o == null) {
            if (Util.a(this.j, MimeTypes.h(format.l)) != -1) {
                return d;
            }
            return null;
        }
        DrmInitData drmInitData = format.o;
        boolean z = true;
        if (this.b == null) {
            if (a(drmInitData, this.e, true).isEmpty()) {
                if (drmInitData.c == 1 && drmInitData.a[0].a(C.b)) {
                    String valueOf = String.valueOf(this.e);
                    Log.c("DefaultDrmSessionMgr", new StringBuilder(String.valueOf(valueOf).length() + 72).append("DrmInitData only contains common PSSH SchemeData. Assuming support for: ").append(valueOf).toString());
                }
            }
            String str = drmInitData.b;
            if (str != null) {
                if (!"cenc".equals(str)) {
                    z = !"cbcs".equals(str) ? false : false;
                }
            }
        }
        return z ? d : UnsupportedMediaCrypto.class;
    }

    public final void a() {
        int i2 = this.s;
        this.s = i2 + 1;
        if (i2 == 0) {
            if (this.t == null) {
                ExoMediaDrm a2 = this.f.a(this.e);
                this.t = a2;
                a2.a((ExoMediaDrm.OnEventListener) new MediaDrmEventListener(this, (byte) 0));
            } else if (this.o != -9223372036854775807L) {
                for (int i3 = 0; i3 < this.a.size(); i3++) {
                    ((DefaultDrmSession) this.a.get(i3)).a((DrmSessionEventListener.EventDispatcher) null);
                }
            }
        }
    }

    public final DrmSession b(Looper looper, DrmSessionEventListener.EventDispatcher eventDispatcher, Format format) {
        Assertions.b(this.s > 0);
        a(looper);
        return a(looper, eventDispatcher, format, true);
    }

    public final void b() {
        int i2 = this.s - 1;
        this.s = i2;
        if (i2 == 0) {
            if (this.o != -9223372036854775807L) {
                ArrayList arrayList = new ArrayList(this.a);
                for (int i3 = 0; i3 < arrayList.size(); i3++) {
                    ((DefaultDrmSession) arrayList.get(i3)).b((DrmSessionEventListener.EventDispatcher) null);
                }
            }
            c();
            d();
        }
    }
}
