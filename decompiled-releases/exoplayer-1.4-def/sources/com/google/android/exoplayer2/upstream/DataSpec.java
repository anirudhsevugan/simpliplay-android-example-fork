package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import com.google.android.exoplayer2.util.Assertions;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class DataSpec {
    public final Uri a;
    public final long b;
    public final int c;
    public final byte[] d;
    public final Map e;
    public final long f;
    public final long g;
    public final String h;
    public final int i;
    public final Object j;

    public final class Builder {
        public Uri a;
        public int b;
        public byte[] c;
        public Map d;
        public long e;
        public long f;
        public String g;
        public int h;
        private long i;
        private Object j;

        public Builder() {
            this.b = 1;
            this.d = Collections.emptyMap();
            this.f = -1;
        }

        private Builder(DataSpec dataSpec) {
            this.a = dataSpec.a;
            this.i = dataSpec.b;
            this.b = dataSpec.c;
            this.c = dataSpec.d;
            this.d = dataSpec.e;
            this.e = dataSpec.f;
            this.f = dataSpec.g;
            this.g = dataSpec.h;
            this.h = dataSpec.i;
            this.j = dataSpec.j;
        }

        public /* synthetic */ Builder(DataSpec dataSpec, byte b2) {
            this(dataSpec);
        }

        public final Builder a(String str) {
            this.a = Uri.parse(str);
            return this;
        }

        public final DataSpec a() {
            Assertions.a((Object) this.a, (Object) "The uri must be set.");
            return new DataSpec(this.a, this.i, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.j, (byte) 0);
        }
    }

    private DataSpec(Uri uri, long j2, int i2, byte[] bArr, Map map, long j3, long j4, String str, int i3, Object obj) {
        long j5 = j2;
        byte[] bArr2 = bArr;
        long j6 = j3;
        long j7 = j4;
        boolean z = true;
        Assertions.a(j5 + j6 >= 0);
        Assertions.a(j6 >= 0);
        if (j7 <= 0 && j7 != -1) {
            z = false;
        }
        Assertions.a(z);
        this.a = uri;
        this.b = j5;
        this.c = i2;
        this.d = (bArr2 == null || bArr2.length == 0) ? null : bArr2;
        this.e = Collections.unmodifiableMap(new HashMap(map));
        this.f = j6;
        this.g = j7;
        this.h = str;
        this.i = i3;
        this.j = obj;
    }

    /* synthetic */ DataSpec(Uri uri, long j2, int i2, byte[] bArr, Map map, long j3, long j4, String str, int i3, Object obj, byte b2) {
        this(uri, j2, i2, bArr, map, j3, j4, str, i3, obj);
    }

    public DataSpec(Uri uri, long j2, long j3) {
        this(uri, 0, 1, (byte[]) null, Collections.emptyMap(), j2, j3, (String) null, 0, (Object) null);
    }

    private DataSpec a(long j2, long j3) {
        if (j2 == 0 && this.g == j3) {
            return this;
        }
        return new DataSpec(this.a, this.b, this.c, this.d, this.e, this.f + j2, j3, this.h, this.i, this.j);
    }

    public static String a(int i2) {
        switch (i2) {
            case 1:
                return "GET";
            case 2:
                return "POST";
            case 3:
                return "HEAD";
            default:
                throw new IllegalStateException();
        }
    }

    public final DataSpec a(long j2) {
        long j3 = this.g;
        long j4 = -1;
        if (j3 != -1) {
            j4 = j3 - j2;
        }
        return a(j2, j4);
    }

    public final boolean b(int i2) {
        return (this.i & i2) == i2;
    }

    public final String toString() {
        String a2 = a(this.c);
        String valueOf = String.valueOf(this.a);
        long j2 = this.f;
        long j3 = this.g;
        String str = this.h;
        return new StringBuilder(String.valueOf(a2).length() + 70 + String.valueOf(valueOf).length() + String.valueOf(str).length()).append("DataSpec[").append(a2).append(" ").append(valueOf).append(", ").append(j2).append(", ").append(j3).append(", ").append(str).append(", ").append(this.i).append("]").toString();
    }
}
