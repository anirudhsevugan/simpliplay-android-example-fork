package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import android.util.Base64;
import com.dreamers.exoplayercore.repack.aC;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.Util;
import java.net.URLDecoder;

public final class DataSchemeDataSource extends BaseDataSource {
    private DataSpec a;
    private byte[] b;
    private int c;
    private int d;

    public DataSchemeDataSource() {
        super(false);
    }

    public final int a(byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        int i3 = this.d;
        if (i3 == 0) {
            return -1;
        }
        int min = Math.min(i2, i3);
        System.arraycopy(Util.a((Object) this.b), this.c, bArr, i, min);
        this.c += min;
        this.d -= min;
        a(min);
        return min;
    }

    public final long a(DataSpec dataSpec) {
        d();
        this.a = dataSpec;
        Uri uri = dataSpec.a;
        String scheme = uri.getScheme();
        if (!"data".equals(scheme)) {
            String valueOf = String.valueOf(scheme);
            throw new ParserException(valueOf.length() != 0 ? "Unsupported scheme: ".concat(valueOf) : new String("Unsupported scheme: "));
        }
        String[] a2 = Util.a(uri.getSchemeSpecificPart(), ",");
        if (a2.length == 2) {
            String str = a2[1];
            if (a2[0].contains(";base64")) {
                try {
                    this.b = Base64.decode(str, 0);
                } catch (IllegalArgumentException e) {
                    String valueOf2 = String.valueOf(str);
                    throw new ParserException(valueOf2.length() != 0 ? "Error while parsing Base64 encoded string: ".concat(valueOf2) : new String("Error while parsing Base64 encoded string: "), e);
                }
            } else {
                this.b = Util.c(URLDecoder.decode(str, aC.a.name()));
            }
            if (dataSpec.f <= ((long) this.b.length)) {
                int i = (int) dataSpec.f;
                this.c = i;
                this.d = this.b.length - i;
                if (dataSpec.g != -1) {
                    this.d = (int) Math.min((long) this.d, dataSpec.g);
                }
                b(dataSpec);
                return dataSpec.g != -1 ? dataSpec.g : (long) this.d;
            }
            this.b = null;
            throw new DataSourceException();
        }
        String valueOf3 = String.valueOf(uri);
        throw new ParserException(new StringBuilder(String.valueOf(valueOf3).length() + 23).append("Unexpected URI format: ").append(valueOf3).toString());
    }

    public final Uri a() {
        DataSpec dataSpec = this.a;
        if (dataSpec != null) {
            return dataSpec.a;
        }
        return null;
    }

    public final void c() {
        if (this.b != null) {
            this.b = null;
            e();
        }
        this.a = null;
    }
}
