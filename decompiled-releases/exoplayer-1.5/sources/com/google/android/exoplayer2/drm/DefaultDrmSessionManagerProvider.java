package com.google.android.exoplayer2.drm;

import com.dreamers.exoplayercore.repack.cR;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.drm.DefaultDrmSessionManager;
import com.google.android.exoplayer2.drm.ExoMediaDrm;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public final class DefaultDrmSessionManagerProvider implements DrmSessionManagerProvider {
    private final Object a = new Object();
    private MediaItem.DrmConfiguration b;
    private DrmSessionManager c;

    public final DrmSessionManager a(MediaItem mediaItem) {
        DefaultDrmSessionManagerProvider defaultDrmSessionManagerProvider = this;
        MediaItem mediaItem2 = mediaItem;
        Assertions.b((Object) mediaItem2.b);
        MediaItem.DrmConfiguration drmConfiguration = mediaItem2.b.c;
        if (drmConfiguration == null || Util.a < 18) {
            return DrmSessionManager.d;
        }
        synchronized (defaultDrmSessionManagerProvider.a) {
            try {
                if (!Util.a((Object) drmConfiguration, (Object) defaultDrmSessionManagerProvider.b)) {
                    defaultDrmSessionManagerProvider.b = drmConfiguration;
                    HttpMediaDrmCallback httpMediaDrmCallback = new HttpMediaDrmCallback(drmConfiguration.b == null ? null : drmConfiguration.b.toString(), drmConfiguration.f, new DefaultHttpDataSource.Factory());
                    for (Map.Entry entry : drmConfiguration.c.entrySet()) {
                        httpMediaDrmCallback.a((String) entry.getKey(), (String) entry.getValue());
                    }
                    DefaultDrmSessionManager.Builder builder = new DefaultDrmSessionManager.Builder();
                    UUID uuid = drmConfiguration.a;
                    ExoMediaDrm.Provider provider = FrameworkMediaDrm.a;
                    builder.b = (UUID) Assertions.b((Object) uuid);
                    builder.c = (ExoMediaDrm.Provider) Assertions.b((Object) provider);
                    builder.d = drmConfiguration.d;
                    builder.f = drmConfiguration.e;
                    DefaultDrmSessionManager.Builder a2 = builder.a(cR.a((Collection) drmConfiguration.g));
                    DefaultDrmSessionManager defaultDrmSessionManager = r4;
                    DefaultDrmSessionManager defaultDrmSessionManager2 = new DefaultDrmSessionManager(a2.b, a2.c, httpMediaDrmCallback, a2.a, a2.d, a2.e, a2.f, a2.g, a2.h, (byte) 0);
                    byte[] a3 = drmConfiguration.a();
                    Assertions.b(defaultDrmSessionManager.a.isEmpty());
                    defaultDrmSessionManager.b = a3;
                    DefaultDrmSessionManager defaultDrmSessionManager3 = defaultDrmSessionManager;
                    defaultDrmSessionManagerProvider = this;
                    defaultDrmSessionManagerProvider.c = defaultDrmSessionManager3;
                }
                DrmSessionManager drmSessionManager = (DrmSessionManager) Assertions.b((Object) defaultDrmSessionManagerProvider.c);
                return drmSessionManager;
            } catch (Throwable th) {
                th = th;
                throw th;
            }
        }
    }
}
