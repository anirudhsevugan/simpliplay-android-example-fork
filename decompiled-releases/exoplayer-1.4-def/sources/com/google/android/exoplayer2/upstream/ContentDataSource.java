package com.google.android.exoplayer2.upstream;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import com.google.android.exoplayer2.util.Util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;

public final class ContentDataSource extends BaseDataSource {
    private final ContentResolver a;
    private Uri b;
    private AssetFileDescriptor c;
    private FileInputStream d;
    private long e;
    private boolean f;

    public class ContentDataSourceException extends IOException {
        public ContentDataSourceException(IOException iOException) {
            super(iOException);
        }
    }

    public ContentDataSource(Context context) {
        super(false);
        this.a = context.getContentResolver();
    }

    public final int a(byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        long j = this.e;
        if (j == 0) {
            return -1;
        }
        if (j != -1) {
            try {
                i2 = (int) Math.min(j, (long) i2);
            } catch (IOException e2) {
                throw new ContentDataSourceException(e2);
            }
        }
        int read = ((FileInputStream) Util.a((Object) this.d)).read(bArr, i, i2);
        if (read == -1) {
            return -1;
        }
        long j2 = this.e;
        if (j2 != -1) {
            this.e = j2 - ((long) read);
        }
        a(read);
        return read;
    }

    public final long a(DataSpec dataSpec) {
        try {
            Uri uri = dataSpec.a;
            this.b = uri;
            d();
            AssetFileDescriptor openAssetFileDescriptor = this.a.openAssetFileDescriptor(uri, "r");
            this.c = openAssetFileDescriptor;
            if (openAssetFileDescriptor != null) {
                long length = openAssetFileDescriptor.getLength();
                FileInputStream fileInputStream = new FileInputStream(openAssetFileDescriptor.getFileDescriptor());
                this.d = fileInputStream;
                if (length != -1) {
                    if (dataSpec.f > length) {
                        throw new DataSourceException();
                    }
                }
                long startOffset = openAssetFileDescriptor.getStartOffset();
                long skip = fileInputStream.skip(dataSpec.f + startOffset) - startOffset;
                if (skip == dataSpec.f) {
                    if (length == -1) {
                        FileChannel channel = fileInputStream.getChannel();
                        long size = channel.size();
                        if (size == 0) {
                            this.e = -1;
                        } else {
                            long position = size - channel.position();
                            this.e = position;
                            if (position < 0) {
                                throw new DataSourceException();
                            }
                        }
                    } else {
                        long j = length - skip;
                        this.e = j;
                        if (j < 0) {
                            throw new DataSourceException();
                        }
                    }
                    if (dataSpec.g != -1) {
                        long j2 = this.e;
                        this.e = j2 == -1 ? dataSpec.g : Math.min(j2, dataSpec.g);
                    }
                    this.f = true;
                    b(dataSpec);
                    return dataSpec.g != -1 ? dataSpec.g : this.e;
                }
                throw new DataSourceException();
            }
            String valueOf = String.valueOf(uri);
            throw new FileNotFoundException(new StringBuilder(String.valueOf(valueOf).length() + 36).append("Could not open file descriptor for: ").append(valueOf).toString());
        } catch (IOException e2) {
            throw new ContentDataSourceException(e2);
        }
    }

    public final Uri a() {
        return this.b;
    }

    public final void c() {
        this.b = null;
        try {
            FileInputStream fileInputStream = this.d;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            this.d = null;
            try {
                AssetFileDescriptor assetFileDescriptor = this.c;
                if (assetFileDescriptor != null) {
                    assetFileDescriptor.close();
                }
                this.c = null;
                if (this.f) {
                    this.f = false;
                    e();
                }
            } catch (IOException e2) {
                throw new ContentDataSourceException(e2);
            } catch (Throwable th) {
                this.c = null;
                if (this.f) {
                    this.f = false;
                    e();
                }
                throw th;
            }
        } catch (IOException e3) {
            throw new ContentDataSourceException(e3);
        } catch (Throwable th2) {
            this.d = null;
            try {
                AssetFileDescriptor assetFileDescriptor2 = this.c;
                if (assetFileDescriptor2 != null) {
                    assetFileDescriptor2.close();
                }
                this.c = null;
                if (this.f) {
                    this.f = false;
                    e();
                }
                throw th2;
            } catch (IOException e4) {
                throw new ContentDataSourceException(e4);
            } catch (Throwable th3) {
                this.c = null;
                if (this.f) {
                    this.f = false;
                    e();
                }
                throw th3;
            }
        }
    }
}
