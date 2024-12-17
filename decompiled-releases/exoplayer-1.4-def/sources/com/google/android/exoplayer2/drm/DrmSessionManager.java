package com.google.android.exoplayer2.drm;

import android.os.Looper;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.DrmSessionEventListener;

public interface DrmSessionManager {
    public static final DrmSessionManager d = new DrmSessionManager() {
        static {
            DrmSessionManager$$CC.d();
        }

        public final DrmSessionReference a(Looper looper, DrmSessionEventListener.EventDispatcher eventDispatcher, Format format) {
            return DrmSessionManager$$CC.c();
        }

        public final Class a(Format format) {
            if (format.o != null) {
                return UnsupportedMediaCrypto.class;
            }
            return null;
        }

        public final void a() {
            DrmSessionManager$$CC.a();
        }

        public final DrmSession b(Looper looper, DrmSessionEventListener.EventDispatcher eventDispatcher, Format format) {
            if (format.o == null) {
                return null;
            }
            return new ErrorStateDrmSession(new DrmSession.DrmSessionException(new UnsupportedDrmException()));
        }

        public final void b() {
            DrmSessionManager$$CC.b();
        }
    };

    public interface DrmSessionReference {
        public static final DrmSessionReference e = DrmSessionManager$DrmSessionReference$$Lambda$0.a;

        void a();
    }

    DrmSessionReference a(Looper looper, DrmSessionEventListener.EventDispatcher eventDispatcher, Format format);

    Class a(Format format);

    void a();

    DrmSession b(Looper looper, DrmSessionEventListener.EventDispatcher eventDispatcher, Format format);

    void b();
}
