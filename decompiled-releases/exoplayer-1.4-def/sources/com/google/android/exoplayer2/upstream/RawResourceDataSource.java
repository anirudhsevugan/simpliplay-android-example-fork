package com.google.android.exoplayer2.upstream;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

public final class RawResourceDataSource extends BaseDataSource {
    private final Resources a;
    private final String b;
    private Uri c;
    private AssetFileDescriptor d;
    private InputStream e;
    private long f;
    private boolean g;

    public class RawResourceDataSourceException extends IOException {
        public RawResourceDataSourceException(String str) {
            super(str);
        }

        public RawResourceDataSourceException(Throwable th) {
            super(th);
        }
    }

    public RawResourceDataSource(Context context) {
        super(false);
        this.a = context.getResources();
        this.b = context.getPackageName();
    }

    public static Uri buildRawResourceUri(int i) {
        return Uri.parse(new StringBuilder(26).append("rawresource:///").append(i).toString());
    }

    public final int a(byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        long j = this.f;
        if (j == 0) {
            return -1;
        }
        if (j != -1) {
            try {
                i2 = (int) Math.min(j, (long) i2);
            } catch (IOException e2) {
                throw new RawResourceDataSourceException((Throwable) e2);
            }
        }
        int read = ((InputStream) Util.a((Object) this.e)).read(bArr, i, i2);
        if (read != -1) {
            long j2 = this.f;
            if (j2 != -1) {
                this.f = j2 - ((long) read);
            }
            a(read);
            return read;
        } else if (this.f == -1) {
            return -1;
        } else {
            throw new RawResourceDataSourceException((Throwable) new EOFException());
        }
    }

    public final long a(DataSpec dataSpec) {
        int i;
        Uri uri = dataSpec.a;
        this.c = uri;
        if (TextUtils.equals("rawresource", uri.getScheme()) || (TextUtils.equals("android.resource", uri.getScheme()) && uri.getPathSegments().size() == 1 && ((String) Assertions.b((Object) uri.getLastPathSegment())).matches("\\d+"))) {
            try {
                i = Integer.parseInt((String) Assertions.b((Object) uri.getLastPathSegment()));
            } catch (NumberFormatException e2) {
                throw new RawResourceDataSourceException("Resource identifier must be an integer.");
            }
        } else if (TextUtils.equals("android.resource", uri.getScheme())) {
            String str = (String) Assertions.b((Object) uri.getPath());
            if (str.startsWith("/")) {
                str = str.substring(1);
            }
            String host = uri.getHost();
            String valueOf = String.valueOf(TextUtils.isEmpty(host) ? "" : String.valueOf(host).concat(":"));
            String valueOf2 = String.valueOf(str);
            i = this.a.getIdentifier(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), "raw", this.b);
            if (i == 0) {
                throw new RawResourceDataSourceException("Resource not found.");
            }
        } else {
            throw new RawResourceDataSourceException("URI must either use scheme rawresource or android.resource");
        }
        d();
        try {
            AssetFileDescriptor openRawResourceFd = this.a.openRawResourceFd(i);
            this.d = openRawResourceFd;
            if (openRawResourceFd != null) {
                long length = openRawResourceFd.getLength();
                FileInputStream fileInputStream = new FileInputStream(openRawResourceFd.getFileDescriptor());
                this.e = fileInputStream;
                if (length != -1) {
                    try {
                        if (dataSpec.f > length) {
                            throw new DataSourceException();
                        }
                    } catch (IOException e3) {
                        throw new RawResourceDataSourceException((Throwable) e3);
                    }
                }
                long startOffset = openRawResourceFd.getStartOffset();
                long skip = fileInputStream.skip(dataSpec.f + startOffset) - startOffset;
                if (skip == dataSpec.f) {
                    if (length == -1) {
                        FileChannel channel = fileInputStream.getChannel();
                        if (channel.size() == 0) {
                            this.f = -1;
                        } else {
                            long size = channel.size() - channel.position();
                            this.f = size;
                            if (size < 0) {
                                throw new DataSourceException();
                            }
                        }
                    } else {
                        long j = length - skip;
                        this.f = j;
                        if (j < 0) {
                            throw new DataSourceException();
                        }
                    }
                    if (dataSpec.g != -1) {
                        long j2 = this.f;
                        this.f = j2 == -1 ? dataSpec.g : Math.min(j2, dataSpec.g);
                    }
                    this.g = true;
                    b(dataSpec);
                    return dataSpec.g != -1 ? dataSpec.g : this.f;
                }
                throw new DataSourceException();
            }
            String valueOf3 = String.valueOf(uri);
            throw new RawResourceDataSourceException(new StringBuilder(String.valueOf(valueOf3).length() + 24).append("Resource is compressed: ").append(valueOf3).toString());
        } catch (Resources.NotFoundException e4) {
            throw new RawResourceDataSourceException((Throwable) e4);
        }
    }

    public final Uri a() {
        return this.c;
    }

    public final void c() {
        this.c = null;
        try {
            InputStream inputStream = this.e;
            if (inputStream != null) {
                inputStream.close();
            }
            this.e = null;
            try {
                AssetFileDescriptor assetFileDescriptor = this.d;
                if (assetFileDescriptor != null) {
                    assetFileDescriptor.close();
                }
                this.d = null;
                if (this.g) {
                    this.g = false;
                    e();
                }
            } catch (IOException e2) {
                throw new RawResourceDataSourceException((Throwable) e2);
            } catch (Throwable th) {
                this.d = null;
                if (this.g) {
                    this.g = false;
                    e();
                }
                throw th;
            }
        } catch (IOException e3) {
            throw new RawResourceDataSourceException((Throwable) e3);
        } catch (Throwable th2) {
            this.e = null;
            try {
                AssetFileDescriptor assetFileDescriptor2 = this.d;
                if (assetFileDescriptor2 != null) {
                    assetFileDescriptor2.close();
                }
                this.d = null;
                if (this.g) {
                    this.g = false;
                    e();
                }
                throw th2;
            } catch (IOException e4) {
                throw new RawResourceDataSourceException((Throwable) e4);
            } catch (Throwable th3) {
                this.d = null;
                if (this.g) {
                    this.g = false;
                    e();
                }
                throw th3;
            }
        }
    }
}
