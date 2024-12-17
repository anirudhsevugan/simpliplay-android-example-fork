package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.upstream.DataReader;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.io.InterruptedIOException;
import java.util.Arrays;

public final class DefaultExtractorInput implements ExtractorInput {
    public int a;
    private final byte[] b = new byte[4096];
    private final DataReader c;
    private final long d;
    private long e;
    private byte[] f = new byte[65536];
    private int g;

    public DefaultExtractorInput(DataReader dataReader, long j, long j2) {
        this.c = dataReader;
        this.e = j;
        this.d = j2;
    }

    private int a(byte[] bArr, int i, int i2, int i3, boolean z) {
        if (!Thread.interrupted()) {
            int a2 = this.c.a(bArr, i + i3, i2 - i3);
            if (a2 != -1) {
                return i3 + a2;
            }
            if (i3 == 0 && z) {
                return -1;
            }
            throw new EOFException();
        }
        throw new InterruptedIOException();
    }

    private boolean a(int i, boolean z) {
        d(i);
        int i2 = this.g - this.a;
        while (i2 < i) {
            i2 = a(this.f, this.a, i, i2, z);
            if (i2 == -1) {
                return false;
            }
            this.g = this.a + i2;
        }
        this.a += i;
        return true;
    }

    private void d(int i) {
        int i2 = this.a + i;
        byte[] bArr = this.f;
        if (i2 > bArr.length) {
            this.f = Arrays.copyOf(this.f, Util.a(bArr.length << 1, 65536 + i2, i2 + 524288));
        }
    }

    private int e(int i) {
        int min = Math.min(this.g, i);
        f(min);
        return min;
    }

    private int e(byte[] bArr, int i, int i2) {
        int i3 = this.g;
        if (i3 == 0) {
            return 0;
        }
        int min = Math.min(i3, i2);
        System.arraycopy(this.f, 0, bArr, i, min);
        f(min);
        return min;
    }

    private void f(int i) {
        int i2 = this.g - i;
        this.g = i2;
        this.a = 0;
        byte[] bArr = this.f;
        byte[] bArr2 = i2 < bArr.length - 524288 ? new byte[(65536 + i2)] : bArr;
        System.arraycopy(bArr, i, bArr2, 0, i2);
        this.f = bArr2;
    }

    private void g(int i) {
        if (i != -1) {
            this.e += (long) i;
        }
    }

    public final int a(int i) {
        int e2 = e(i);
        if (e2 == 0) {
            e2 = a(this.b, 0, Math.min(i, 4096), 0, true);
        }
        g(e2);
        return e2;
    }

    public final int a(byte[] bArr, int i, int i2) {
        int e2 = e(bArr, i, i2);
        if (e2 == 0) {
            e2 = a(bArr, i, i2, 0, true);
        }
        g(e2);
        return e2;
    }

    public final void a() {
        this.a = 0;
    }

    public final boolean a(byte[] bArr, int i, int i2, boolean z) {
        int e2 = e(bArr, i, i2);
        while (e2 < i2 && e2 != -1) {
            e2 = a(bArr, i, i2, e2, z);
        }
        g(e2);
        return e2 != -1;
    }

    public final long b() {
        return this.e + ((long) this.a);
    }

    public final void b(int i) {
        int e2 = e(i);
        while (e2 < i && e2 != -1) {
            e2 = a(this.b, -e2, Math.min(i, e2 + 4096), e2, false);
        }
        g(e2);
    }

    public final void b(byte[] bArr, int i, int i2) {
        a(bArr, i, i2, false);
    }

    public final boolean b(byte[] bArr, int i, int i2, boolean z) {
        if (!a(i2, z)) {
            return false;
        }
        System.arraycopy(this.f, this.a - i2, bArr, i, i2);
        return true;
    }

    public final int c(byte[] bArr, int i, int i2) {
        int i3;
        d(i2);
        int i4 = this.g;
        int i5 = this.a;
        int i6 = i4 - i5;
        if (i6 == 0) {
            i3 = a(this.f, i5, i2, 0, true);
            if (i3 == -1) {
                return -1;
            }
            this.g += i3;
        } else {
            i3 = Math.min(i2, i6);
        }
        System.arraycopy(this.f, this.a, bArr, i, i3);
        this.a += i3;
        return i3;
    }

    public final long c() {
        return this.e;
    }

    public final void c(int i) {
        a(i, false);
    }

    public final long d() {
        return this.d;
    }

    public final void d(byte[] bArr, int i, int i2) {
        b(bArr, i, i2, false);
    }
}
