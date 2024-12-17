package com.google.android.exoplayer2.upstream;

import android.content.Context;
import android.net.Uri;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import com.google.appinventor.components.common.PropertyTypeConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class DefaultDataSource implements DataSource {
    private final Context a;
    private final List b = new ArrayList();
    private final DataSource c;
    private DataSource d;
    private DataSource e;
    private DataSource f;
    private DataSource g;
    private DataSource h;
    private DataSource i;
    private DataSource j;
    private DataSource k;

    public DefaultDataSource(Context context, DataSource dataSource) {
        this.a = context.getApplicationContext();
        this.c = (DataSource) Assertions.b((Object) dataSource);
    }

    private void a(DataSource dataSource) {
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            dataSource.a((TransferListener) this.b.get(i2));
        }
    }

    private static void a(DataSource dataSource, TransferListener transferListener) {
        if (dataSource != null) {
            dataSource.a(transferListener);
        }
    }

    private DataSource d() {
        if (this.e == null) {
            AssetDataSource assetDataSource = new AssetDataSource(this.a);
            this.e = assetDataSource;
            a((DataSource) assetDataSource);
        }
        return this.e;
    }

    private DataSource e() {
        if (this.g == null) {
            try {
                DataSource dataSource = (DataSource) Class.forName("com.google.android.exoplayer2.ext.rtmp.RtmpDataSource").getConstructor(new Class[0]).newInstance(new Object[0]);
                this.g = dataSource;
                a(dataSource);
            } catch (ClassNotFoundException e2) {
                Log.c("DefaultDataSource", "Attempting to play RTMP stream without depending on the RTMP extension");
            } catch (Exception e3) {
                throw new RuntimeException("Error instantiating RTMP extension", e3);
            }
            if (this.g == null) {
                this.g = this.c;
            }
        }
        return this.g;
    }

    public final int a(byte[] bArr, int i2, int i3) {
        return ((DataSource) Assertions.b((Object) this.k)).a(bArr, i2, i3);
    }

    public final long a(DataSpec dataSpec) {
        DataSource dataSource;
        Assertions.b(this.k == null);
        String scheme = dataSpec.a.getScheme();
        if (Util.a(dataSpec.a)) {
            String path = dataSpec.a.getPath();
            if (path == null || !path.startsWith("/android_asset/")) {
                if (this.d == null) {
                    FileDataSource fileDataSource = new FileDataSource();
                    this.d = fileDataSource;
                    a((DataSource) fileDataSource);
                }
                dataSource = this.d;
                this.k = dataSource;
                return this.k.a(dataSpec);
            }
        } else if (!PropertyTypeConstants.PROPERTY_TYPE_ASSET.equals(scheme)) {
            if ("content".equals(scheme)) {
                if (this.f == null) {
                    ContentDataSource contentDataSource = new ContentDataSource(this.a);
                    this.f = contentDataSource;
                    a((DataSource) contentDataSource);
                }
                dataSource = this.f;
            } else if ("rtmp".equals(scheme)) {
                dataSource = e();
            } else if ("udp".equals(scheme)) {
                if (this.h == null) {
                    UdpDataSource udpDataSource = new UdpDataSource();
                    this.h = udpDataSource;
                    a((DataSource) udpDataSource);
                }
                dataSource = this.h;
            } else if ("data".equals(scheme)) {
                if (this.i == null) {
                    DataSchemeDataSource dataSchemeDataSource = new DataSchemeDataSource();
                    this.i = dataSchemeDataSource;
                    a((DataSource) dataSchemeDataSource);
                }
                dataSource = this.i;
            } else if ("rawresource".equals(scheme) || "android.resource".equals(scheme)) {
                if (this.j == null) {
                    RawResourceDataSource rawResourceDataSource = new RawResourceDataSource(this.a);
                    this.j = rawResourceDataSource;
                    a((DataSource) rawResourceDataSource);
                }
                dataSource = this.j;
            } else {
                dataSource = this.c;
            }
            this.k = dataSource;
            return this.k.a(dataSpec);
        }
        dataSource = d();
        this.k = dataSource;
        return this.k.a(dataSpec);
    }

    public final Uri a() {
        DataSource dataSource = this.k;
        if (dataSource == null) {
            return null;
        }
        return dataSource.a();
    }

    public final void a(TransferListener transferListener) {
        Assertions.b((Object) transferListener);
        this.c.a(transferListener);
        this.b.add(transferListener);
        a(this.d, transferListener);
        a(this.e, transferListener);
        a(this.f, transferListener);
        a(this.g, transferListener);
        a(this.h, transferListener);
        a(this.i, transferListener);
        a(this.j, transferListener);
    }

    public final Map b() {
        DataSource dataSource = this.k;
        return dataSource == null ? Collections.emptyMap() : dataSource.b();
    }

    public final void c() {
        DataSource dataSource = this.k;
        if (dataSource != null) {
            try {
                dataSource.c();
            } finally {
                this.k = null;
            }
        }
    }
}
