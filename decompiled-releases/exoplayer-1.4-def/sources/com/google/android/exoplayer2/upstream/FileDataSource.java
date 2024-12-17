package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public final class FileDataSource extends BaseDataSource {
    private RandomAccessFile a;
    private Uri b;
    private long c;
    private boolean d;

    public class FileDataSourceException extends IOException {
        public FileDataSourceException(IOException iOException) {
            super(iOException);
        }

        public FileDataSourceException(String str, IOException iOException) {
            super(str, iOException);
        }
    }

    public FileDataSource() {
        super(false);
    }

    private static RandomAccessFile a(Uri uri) {
        try {
            return new RandomAccessFile((String) Assertions.b((Object) uri.getPath()), "r");
        } catch (FileNotFoundException e) {
            if (!TextUtils.isEmpty(uri.getQuery()) || !TextUtils.isEmpty(uri.getFragment())) {
                throw new FileDataSourceException(String.format("uri has query and/or fragment, which are not supported. Did you call Uri.parse() on a string containing '?' or '#'? Use Uri.fromFile(new File(path)) to avoid this. path=%s,query=%s,fragment=%s", new Object[]{uri.getPath(), uri.getQuery(), uri.getFragment()}), e);
            }
            throw new FileDataSourceException(e);
        }
    }

    public final int a(byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        if (this.c == 0) {
            return -1;
        }
        try {
            int read = ((RandomAccessFile) Util.a((Object) this.a)).read(bArr, i, (int) Math.min(this.c, (long) i2));
            if (read > 0) {
                this.c -= (long) read;
                a(read);
            }
            return read;
        } catch (IOException e) {
            throw new FileDataSourceException(e);
        }
    }

    public final long a(DataSpec dataSpec) {
        try {
            Uri uri = dataSpec.a;
            this.b = uri;
            d();
            RandomAccessFile a2 = a(uri);
            this.a = a2;
            a2.seek(dataSpec.f);
            long length = dataSpec.g == -1 ? this.a.length() - dataSpec.f : dataSpec.g;
            this.c = length;
            if (length >= 0) {
                this.d = true;
                b(dataSpec);
                return this.c;
            }
            throw new DataSourceException();
        } catch (IOException e) {
            throw new FileDataSourceException(e);
        }
    }

    public final Uri a() {
        return this.b;
    }

    public final void c() {
        this.b = null;
        try {
            RandomAccessFile randomAccessFile = this.a;
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            this.a = null;
            if (this.d) {
                this.d = false;
                e();
            }
        } catch (IOException e) {
            throw new FileDataSourceException(e);
        } catch (Throwable th) {
            this.a = null;
            if (this.d) {
                this.d = false;
                e();
            }
            throw th;
        }
    }
}
