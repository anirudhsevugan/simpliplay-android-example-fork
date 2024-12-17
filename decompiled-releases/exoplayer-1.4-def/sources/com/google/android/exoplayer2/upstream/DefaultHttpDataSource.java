package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import com.dreamers.exoplayercore.repack.C0000a;
import com.dreamers.exoplayercore.repack.aE;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class DefaultHttpDataSource extends BaseDataSource implements HttpDataSource {
    private final boolean a;
    private final int b;
    private final int c;
    private final String d;
    private final HttpDataSource.RequestProperties e;
    private final HttpDataSource.RequestProperties f;
    private aE g;
    private DataSpec h;
    private HttpURLConnection i;
    private InputStream j;
    private boolean k;
    private int l;
    private long m;
    private long n;

    public final class Factory implements HttpDataSource.Factory {
        private final HttpDataSource.RequestProperties a = new HttpDataSource.RequestProperties();
        private int b = 8000;
        private int c = 8000;

        /* access modifiers changed from: private */
        /* renamed from: c */
        public DefaultHttpDataSource b() {
            return new DefaultHttpDataSource(this.b, this.c, this.a);
        }
    }

    public DefaultHttpDataSource() {
        this((byte) 0);
    }

    private DefaultHttpDataSource(byte b2) {
        this(0);
    }

    private DefaultHttpDataSource(char c2) {
        this((String) null, 8000, 8000, (HttpDataSource.RequestProperties) null);
    }

    /* synthetic */ DefaultHttpDataSource(int i2, int i3, HttpDataSource.RequestProperties requestProperties) {
        this((String) null, i2, i3, requestProperties);
    }

    private DefaultHttpDataSource(String str, int i2, int i3, HttpDataSource.RequestProperties requestProperties) {
        super(true);
        this.d = null;
        this.b = i2;
        this.c = i3;
        this.a = false;
        this.e = requestProperties;
        this.g = null;
        this.f = new HttpDataSource.RequestProperties();
    }

    private HttpURLConnection a(URL url, int i2, byte[] bArr, long j2, long j3, boolean z, boolean z2, Map map) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(this.b);
        httpURLConnection.setReadTimeout(this.c);
        HashMap hashMap = new HashMap();
        HttpDataSource.RequestProperties requestProperties = this.e;
        if (requestProperties != null) {
            hashMap.putAll(requestProperties.a());
        }
        hashMap.putAll(this.f.a());
        hashMap.putAll(map);
        for (Map.Entry entry : hashMap.entrySet()) {
            httpURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
        }
        String a2 = HttpUtil.a(j2, j3);
        if (a2 != null) {
            httpURLConnection.setRequestProperty("Range", a2);
        }
        String str = this.d;
        if (str != null) {
            httpURLConnection.setRequestProperty("User-Agent", str);
        }
        httpURLConnection.setRequestProperty("Accept-Encoding", z ? "gzip" : "identity");
        httpURLConnection.setInstanceFollowRedirects(z2);
        httpURLConnection.setDoOutput(bArr != null);
        httpURLConnection.setRequestMethod(DataSpec.a(i2));
        if (bArr != null) {
            httpURLConnection.setFixedLengthStreamingMode(bArr.length);
            httpURLConnection.connect();
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(bArr);
            outputStream.close();
        } else {
            httpURLConnection.connect();
        }
        return httpURLConnection;
    }

    private boolean a(long j2) {
        if (j2 == 0) {
            return true;
        }
        byte[] bArr = new byte[4096];
        while (j2 > 0) {
            int read = ((InputStream) Util.a((Object) this.j)).read(bArr, 0, (int) Math.min(j2, 4096));
            if (Thread.currentThread().isInterrupted()) {
                throw new InterruptedIOException();
            } else if (read == -1) {
                return false;
            } else {
                j2 -= (long) read;
                a(read);
            }
        }
        return true;
    }

    private void f() {
        HttpURLConnection httpURLConnection = this.i;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e2) {
                Log.b("DefaultHttpDataSource", "Unexpected error while disconnecting", e2);
            }
            this.i = null;
        }
    }

    public final int a(byte[] bArr, int i2, int i3) {
        if (i3 == 0) {
            return 0;
        }
        try {
            long j2 = this.m;
            if (j2 != -1) {
                long j3 = j2 - this.n;
                if (j3 == 0) {
                    return -1;
                }
                i3 = (int) Math.min((long) i3, j3);
            }
            int read = ((InputStream) Util.a((Object) this.j)).read(bArr, i2, i3);
            if (read == -1) {
                return -1;
            }
            this.n += (long) read;
            a(read);
            return read;
        } catch (IOException e2) {
            Util.a((Object) this.h);
            throw new HttpDataSource.HttpDataSourceException(e2);
        }
    }

    public final long a(DataSpec dataSpec) {
        DataSpec dataSpec2 = dataSpec;
        this.h = dataSpec2;
        long j2 = 0;
        this.n = 0;
        this.m = 0;
        d();
        try {
            HttpURLConnection a2 = a(new URL(dataSpec2.a.toString()), dataSpec2.c, dataSpec2.d, dataSpec2.f, dataSpec2.g, dataSpec2.b(1), true, dataSpec2.e);
            this.i = a2;
            try {
                this.l = a2.getResponseCode();
                a2.getResponseMessage();
                int i2 = this.l;
                long j3 = -1;
                if (i2 < 200 || i2 > 299) {
                    Map headerFields = a2.getHeaderFields();
                    if (this.l == 416) {
                        if (dataSpec2.f == HttpUtil.a(a2.getHeaderField("Content-Range"))) {
                            this.k = true;
                            b(dataSpec);
                            if (dataSpec2.g != -1) {
                                return dataSpec2.g;
                            }
                            return 0;
                        }
                    }
                    InputStream errorStream = a2.getErrorStream();
                    if (errorStream != null) {
                        try {
                            Util.a(errorStream);
                        } catch (IOException e2) {
                            byte[] bArr = Util.f;
                        }
                    } else {
                        byte[] bArr2 = Util.f;
                    }
                    f();
                    HttpDataSource.InvalidResponseCodeException invalidResponseCodeException = new HttpDataSource.InvalidResponseCodeException(this.l, headerFields);
                    if (this.l == 416) {
                        invalidResponseCodeException.initCause(new DataSourceException());
                    }
                    throw invalidResponseCodeException;
                }
                a2.getContentType();
                if (this.l == 200 && dataSpec2.f != 0) {
                    j2 = dataSpec2.f;
                }
                boolean equalsIgnoreCase = "gzip".equalsIgnoreCase(a2.getHeaderField("Content-Encoding"));
                if (equalsIgnoreCase || dataSpec2.g != -1) {
                    this.m = dataSpec2.g;
                } else {
                    long a3 = HttpUtil.a(a2.getHeaderField("Content-Length"), a2.getHeaderField("Content-Range"));
                    if (a3 != -1) {
                        j3 = a3 - j2;
                    }
                    this.m = j3;
                }
                try {
                    this.j = a2.getInputStream();
                    if (equalsIgnoreCase) {
                        this.j = new GZIPInputStream(this.j);
                    }
                    this.k = true;
                    b(dataSpec);
                    try {
                        if (a(j2)) {
                            return this.m;
                        }
                        throw new DataSourceException();
                    } catch (IOException e3) {
                        f();
                        throw new HttpDataSource.HttpDataSourceException(e3);
                    }
                } catch (IOException e4) {
                    f();
                    throw new HttpDataSource.HttpDataSourceException(e4);
                }
            } catch (IOException e5) {
                f();
                throw new HttpDataSource.HttpDataSourceException("Unable to connect", e5);
            }
        } catch (IOException e6) {
            String message = e6.getMessage();
            if (message == null || !C0000a.a(message).matches("cleartext http traffic.*not permitted.*")) {
                throw new HttpDataSource.HttpDataSourceException("Unable to connect", e6);
            }
            throw new HttpDataSource.CleartextNotPermittedException(e6);
        }
    }

    public final Uri a() {
        HttpURLConnection httpURLConnection = this.i;
        if (httpURLConnection == null) {
            return null;
        }
        return Uri.parse(httpURLConnection.getURL().toString());
    }

    public final Map b() {
        HttpURLConnection httpURLConnection = this.i;
        return httpURLConnection == null ? Collections.emptyMap() : httpURLConnection.getHeaderFields();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0038, code lost:
        if (r3 > 2048) goto L_0x003a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void c() {
        /*
            r10 = this;
            r0 = 0
            r1 = 0
            java.io.InputStream r2 = r10.j     // Catch:{ all -> 0x0092 }
            if (r2 == 0) goto L_0x0083
            long r3 = r10.m     // Catch:{ all -> 0x0092 }
            r5 = -1
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x0010
            r3 = r5
            goto L_0x0013
        L_0x0010:
            long r7 = r10.n     // Catch:{ all -> 0x0092 }
            long r3 = r3 - r7
        L_0x0013:
            java.net.HttpURLConnection r7 = r10.i     // Catch:{ all -> 0x0092 }
            if (r7 == 0) goto L_0x0073
            int r8 = com.google.android.exoplayer2.util.Util.a     // Catch:{ all -> 0x0092 }
            r9 = 19
            if (r8 < r9) goto L_0x0073
            int r8 = com.google.android.exoplayer2.util.Util.a     // Catch:{ all -> 0x0092 }
            r9 = 20
            if (r8 <= r9) goto L_0x0024
            goto L_0x0073
        L_0x0024:
            java.io.InputStream r7 = r7.getInputStream()     // Catch:{ Exception -> 0x0072 }
            int r8 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r8 != 0) goto L_0x0034
            int r3 = r7.read()     // Catch:{ Exception -> 0x0072 }
            r4 = -1
            if (r3 != r4) goto L_0x003a
            goto L_0x0073
        L_0x0034:
            r5 = 2048(0x800, double:1.0118E-320)
            int r8 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r8 <= 0) goto L_0x0073
        L_0x003a:
            java.lang.Class r3 = r7.getClass()     // Catch:{ Exception -> 0x0072 }
            java.lang.String r3 = r3.getName()     // Catch:{ Exception -> 0x0072 }
            java.lang.String r4 = "com.android.okhttp.internal.http.HttpTransport$ChunkedInputStream"
            boolean r4 = r4.equals(r3)     // Catch:{ Exception -> 0x0072 }
            if (r4 != 0) goto L_0x0052
            java.lang.String r4 = "com.android.okhttp.internal.http.HttpTransport$FixedLengthInputStream"
            boolean r3 = r4.equals(r3)     // Catch:{ Exception -> 0x0072 }
            if (r3 == 0) goto L_0x0073
        L_0x0052:
            java.lang.Class r3 = r7.getClass()     // Catch:{ Exception -> 0x0072 }
            java.lang.Class r3 = r3.getSuperclass()     // Catch:{ Exception -> 0x0072 }
            java.lang.Object r3 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r3)     // Catch:{ Exception -> 0x0072 }
            java.lang.Class r3 = (java.lang.Class) r3     // Catch:{ Exception -> 0x0072 }
            java.lang.String r4 = "unexpectedEndOfInput"
            java.lang.Class[] r5 = new java.lang.Class[r1]     // Catch:{ Exception -> 0x0072 }
            java.lang.reflect.Method r3 = r3.getDeclaredMethod(r4, r5)     // Catch:{ Exception -> 0x0072 }
            r4 = 1
            r3.setAccessible(r4)     // Catch:{ Exception -> 0x0072 }
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x0072 }
            r3.invoke(r7, r4)     // Catch:{ Exception -> 0x0072 }
            goto L_0x0073
        L_0x0072:
            r3 = move-exception
        L_0x0073:
            r2.close()     // Catch:{ IOException -> 0x0077 }
            goto L_0x0083
        L_0x0077:
            r2 = move-exception
            com.google.android.exoplayer2.upstream.HttpDataSource$HttpDataSourceException r3 = new com.google.android.exoplayer2.upstream.HttpDataSource$HttpDataSourceException     // Catch:{ all -> 0x0092 }
            com.google.android.exoplayer2.upstream.DataSpec r4 = r10.h     // Catch:{ all -> 0x0092 }
            com.google.android.exoplayer2.util.Util.a((java.lang.Object) r4)     // Catch:{ all -> 0x0092 }
            r3.<init>((java.io.IOException) r2)     // Catch:{ all -> 0x0092 }
            throw r3     // Catch:{ all -> 0x0092 }
        L_0x0083:
            r10.j = r0
            r10.f()
            boolean r0 = r10.k
            if (r0 == 0) goto L_0x0091
            r10.k = r1
            r10.e()
        L_0x0091:
            return
        L_0x0092:
            r2 = move-exception
            r10.j = r0
            r10.f()
            boolean r0 = r10.k
            if (r0 == 0) goto L_0x00a1
            r10.k = r1
            r10.e()
        L_0x00a1:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.upstream.DefaultHttpDataSource.c():void");
    }
}
