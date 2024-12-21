package com.google.android.exoplayer2.drm;

import android.net.Uri;
import android.text.TextUtils;
import com.dreamers.exoplayercore.repack.bM;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.drm.ExoMediaDrm;
import com.google.android.exoplayer2.upstream.DataSourceInputStream;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.StatsDataSource;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import com.google.appinventor.components.runtime.util.NanoHTTPD;
import java.io.Closeable;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class HttpMediaDrmCallback implements MediaDrmCallback {
    private final HttpDataSource.Factory a;
    private final String b;
    private final boolean c;
    private final Map d;

    public HttpMediaDrmCallback(String str, boolean z, HttpDataSource.Factory factory) {
        Assertions.a(!z || !TextUtils.isEmpty(str));
        this.a = factory;
        this.b = str;
        this.c = z;
        this.d = new HashMap();
    }

    private static byte[] a(HttpDataSource.Factory factory, String str, byte[] bArr, Map map) {
        DataSourceInputStream dataSourceInputStream;
        Map map2;
        List list;
        StatsDataSource statsDataSource = new StatsDataSource(factory.b());
        DataSpec.Builder a2 = new DataSpec.Builder().a(str);
        a2.d = map;
        a2.b = 2;
        a2.c = bArr;
        a2.h = 1;
        DataSpec a3 = a2.a();
        int i = 0;
        while (true) {
            try {
                dataSourceInputStream = new DataSourceInputStream(statsDataSource, a3);
                byte[] a4 = Util.a((InputStream) dataSourceInputStream);
                Util.a((Closeable) dataSourceInputStream);
                return a4;
            } catch (HttpDataSource.InvalidResponseCodeException e) {
                String str2 = (!((e.a == 307 || e.a == 308) && i < 5) || (map2 = e.b) == null || (list = (List) map2.get("Location")) == null || list.isEmpty()) ? null : (String) list.get(0);
                if (str2 != null) {
                    i++;
                    a3 = new DataSpec.Builder(a3, (byte) 0).a(str2).a();
                    Util.a((Closeable) dataSourceInputStream);
                } else {
                    throw e;
                }
            } catch (Exception e2) {
                Assertions.b((Object) statsDataSource.b);
                statsDataSource.b();
                throw new MediaDrmCallbackException(e2);
            } catch (Throwable th) {
                Util.a((Closeable) dataSourceInputStream);
                throw th;
            }
        }
    }

    public final void a(String str, String str2) {
        Assertions.b((Object) str);
        Assertions.b((Object) str2);
        synchronized (this.d) {
            this.d.put(str, str2);
        }
    }

    public final byte[] a(ExoMediaDrm.ProvisionRequest provisionRequest) {
        String str = provisionRequest.b;
        String a2 = Util.a(provisionRequest.a);
        return a(this.a, new StringBuilder(String.valueOf(str).length() + 15 + String.valueOf(a2).length()).append(str).append("&signedRequest=").append(a2).toString(), (byte[]) null, Collections.emptyMap());
    }

    public final byte[] a(UUID uuid, ExoMediaDrm.KeyRequest keyRequest) {
        String str = keyRequest.b;
        if (this.c || TextUtils.isEmpty(str)) {
            str = this.b;
        }
        if (!TextUtils.isEmpty(str)) {
            HashMap hashMap = new HashMap();
            hashMap.put("Content-Type", C.e.equals(uuid) ? NanoHTTPD.MIME_XML : C.c.equals(uuid) ? "application/json" : NanoHTTPD.MIME_DEFAULT_BINARY);
            if (C.e.equals(uuid)) {
                hashMap.put("SOAPAction", "http://schemas.microsoft.com/DRM/2007/03/protocols/AcquireLicense");
            }
            synchronized (this.d) {
                hashMap.putAll(this.d);
            }
            return a(this.a, str, keyRequest.a, hashMap);
        }
        DataSpec.Builder builder = new DataSpec.Builder();
        builder.a = Uri.EMPTY;
        builder.a();
        Uri uri = Uri.EMPTY;
        bM.a();
        throw new MediaDrmCallbackException(new IllegalStateException("No license URL"));
    }
}
