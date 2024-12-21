package com.google.android.exoplayer2.upstream;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.io.InputStream;

public final class AssetDataSource extends BaseDataSource {
    private final AssetManager a;
    private Uri b;
    private InputStream c;
    private long d;
    private boolean e;

    public final class AssetDataSourceException extends IOException {
        public AssetDataSourceException(IOException iOException) {
            super(iOException);
        }
    }

    public AssetDataSource(Context context) {
        super(false);
        this.a = context.getAssets();
    }

    public final int a(byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        long j = this.d;
        if (j == 0) {
            return -1;
        }
        if (j != -1) {
            try {
                i2 = (int) Math.min(j, (long) i2);
            } catch (IOException e2) {
                throw new AssetDataSourceException(e2);
            }
        }
        int read = ((InputStream) Util.a((Object) this.c)).read(bArr, i, i2);
        if (read == -1) {
            return -1;
        }
        long j2 = this.d;
        if (j2 != -1) {
            this.d = j2 - ((long) read);
        }
        a(read);
        return read;
    }

    public final long a(DataSpec dataSpec) {
        try {
            Uri uri = dataSpec.a;
            this.b = uri;
            String str = (String) Assertions.b((Object) uri.getPath());
            if (str.startsWith("/android_asset/")) {
                str = str.substring(15);
            } else if (str.startsWith("/")) {
                str = str.substring(1);
            }
            d();
            InputStream open = this.a.open(str, 1);
            this.c = open;
            if (open.skip(dataSpec.f) >= dataSpec.f) {
                if (dataSpec.g != -1) {
                    this.d = dataSpec.g;
                } else {
                    long available = (long) this.c.available();
                    this.d = available;
                    if (available == 2147483647L) {
                        this.d = -1;
                    }
                }
                this.e = true;
                b(dataSpec);
                return this.d;
            }
            throw new DataSourceException();
        } catch (IOException e2) {
            throw new AssetDataSourceException(e2);
        }
    }

    public final Uri a() {
        return this.b;
    }

    public final void c() {
        this.b = null;
        try {
            InputStream inputStream = this.c;
            if (inputStream != null) {
                inputStream.close();
            }
            this.c = null;
            if (this.e) {
                this.e = false;
                e();
            }
        } catch (IOException e2) {
            throw new AssetDataSourceException(e2);
        } catch (Throwable th) {
            this.c = null;
            if (this.e) {
                this.e = false;
                e();
            }
            throw th;
        }
    }
}
