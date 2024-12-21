package com.google.android.exoplayer2;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.location.LocationRequestCompat;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.UnsupportedMediaCrypto;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.ColorInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Format implements Parcelable {
    public final int A;
    public final int B;
    public final Class C;
    private int D;
    public final String a;
    public final String b;
    public final String c;
    public final int d;
    public final int e;
    public final int f;
    public final int g;
    public final int h;
    public final int height;
    public final String i;
    public final Metadata j;
    public final String k;
    public final String l;
    public final int m;
    public final List n;
    public final DrmInitData o;
    public final long p;
    public final float q;
    public final int r;
    public final float s;
    public final byte[] t;
    public final int u;
    public final ColorInfo v;
    public final int w;
    public final int width;
    public final int x;
    public final int y;
    public final int z;

    public final class Builder {
        public int A;
        public int B;
        public int C;
        /* access modifiers changed from: package-private */
        public Class D;
        public String a;
        public String b;
        public String c;
        public int d;
        public int e;
        public int f;
        public int g;
        public String h;
        public Metadata i;
        public String j;
        public String k;
        public int l;
        public List m;
        public DrmInitData n;
        public long o;
        public int p;
        public int q;
        public float r;
        public int s;
        public float t;
        public byte[] u;
        public int v;
        public ColorInfo w;
        public int x;
        public int y;
        public int z;

        public Builder() {
            this.f = -1;
            this.g = -1;
            this.l = -1;
            this.o = LocationRequestCompat.PASSIVE_INTERVAL;
            this.p = -1;
            this.q = -1;
            this.r = -1.0f;
            this.t = 1.0f;
            this.v = -1;
            this.x = -1;
            this.y = -1;
            this.z = -1;
            this.C = -1;
        }

        private Builder(Format format) {
            this.a = format.a;
            this.b = format.b;
            this.c = format.c;
            this.d = format.d;
            this.e = format.e;
            this.f = format.f;
            this.g = format.g;
            this.h = format.i;
            this.i = format.j;
            this.j = format.k;
            this.k = format.l;
            this.l = format.m;
            this.m = format.n;
            this.n = format.o;
            this.o = format.p;
            this.p = format.width;
            this.q = format.height;
            this.r = format.q;
            this.s = format.r;
            this.t = format.s;
            this.u = format.t;
            this.v = format.u;
            this.w = format.v;
            this.x = format.w;
            this.y = format.x;
            this.z = format.y;
            this.A = format.z;
            this.B = format.A;
            this.C = format.B;
            this.D = format.C;
        }

        /* synthetic */ Builder(Format format, byte b2) {
            this(format);
        }

        public final Builder a(int i2) {
            this.a = Integer.toString(i2);
            return this;
        }

        public final Format a() {
            return new Format(this, (byte) 0);
        }
    }

    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new Format(parcel);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new Format[i];
            }
        };
    }

    Format(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readInt();
        this.e = parcel.readInt();
        int readInt = parcel.readInt();
        this.f = readInt;
        int readInt2 = parcel.readInt();
        this.g = readInt2;
        this.h = readInt2 != -1 ? readInt2 : readInt;
        this.i = parcel.readString();
        this.j = (Metadata) parcel.readParcelable(Metadata.class.getClassLoader());
        this.k = parcel.readString();
        this.l = parcel.readString();
        this.m = parcel.readInt();
        int readInt3 = parcel.readInt();
        this.n = new ArrayList(readInt3);
        for (int i2 = 0; i2 < readInt3; i2++) {
            this.n.add((byte[]) Assertions.b((Object) parcel.createByteArray()));
        }
        DrmInitData drmInitData = (DrmInitData) parcel.readParcelable(DrmInitData.class.getClassLoader());
        this.o = drmInitData;
        this.p = parcel.readLong();
        this.width = parcel.readInt();
        this.height = parcel.readInt();
        this.q = parcel.readFloat();
        this.r = parcel.readInt();
        this.s = parcel.readFloat();
        Class cls = null;
        this.t = Util.a(parcel) ? parcel.createByteArray() : null;
        this.u = parcel.readInt();
        this.v = (ColorInfo) parcel.readParcelable(ColorInfo.class.getClassLoader());
        this.w = parcel.readInt();
        this.x = parcel.readInt();
        this.y = parcel.readInt();
        this.z = parcel.readInt();
        this.A = parcel.readInt();
        this.B = parcel.readInt();
        this.C = drmInitData != null ? UnsupportedMediaCrypto.class : cls;
    }

    private Format(Builder builder) {
        this.a = builder.a;
        this.b = builder.b;
        this.c = Util.b(builder.c);
        this.d = builder.d;
        this.e = builder.e;
        int f2 = builder.f;
        this.f = f2;
        int g2 = builder.g;
        this.g = g2;
        this.h = g2 != -1 ? g2 : f2;
        this.i = builder.h;
        this.j = builder.i;
        this.k = builder.j;
        this.l = builder.k;
        this.m = builder.l;
        this.n = builder.m == null ? Collections.emptyList() : builder.m;
        DrmInitData n2 = builder.n;
        this.o = n2;
        this.p = builder.o;
        this.width = builder.p;
        this.height = builder.q;
        this.q = builder.r;
        int i2 = 0;
        this.r = builder.s == -1 ? 0 : builder.s;
        this.s = builder.t == -1.0f ? 1.0f : builder.t;
        this.t = builder.u;
        this.u = builder.v;
        this.v = builder.w;
        this.w = builder.x;
        this.x = builder.y;
        this.y = builder.z;
        this.z = builder.A == -1 ? 0 : builder.A;
        this.A = builder.B != -1 ? builder.B : i2;
        this.B = builder.C;
        this.C = (builder.D != null || n2 == null) ? builder.D : UnsupportedMediaCrypto.class;
    }

    /* synthetic */ Format(Builder builder, byte b2) {
        this(builder);
    }

    public static String c(Format format) {
        if (format == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("id=").append(format.a).append(", mimeType=").append(format.l);
        if (format.h != -1) {
            sb.append(", bitrate=").append(format.h);
        }
        if (format.i != null) {
            sb.append(", codecs=").append(format.i);
        }
        if (!(format.width == -1 || format.height == -1)) {
            sb.append(", res=").append(format.width).append("x").append(format.height);
        }
        if (format.q != -1.0f) {
            sb.append(", fps=").append(format.q);
        }
        if (format.w != -1) {
            sb.append(", channels=").append(format.w);
        }
        if (format.x != -1) {
            sb.append(", sample_rate=").append(format.x);
        }
        if (format.c != null) {
            sb.append(", language=").append(format.c);
        }
        if (format.b != null) {
            sb.append(", label=").append(format.b);
        }
        return sb.toString();
    }

    public final Builder a() {
        return new Builder(this, (byte) 0);
    }

    public final Format a(Format format) {
        String str;
        if (this == format) {
            return this;
        }
        int h2 = MimeTypes.h(this.l);
        String str2 = format.a;
        String str3 = format.b;
        if (str3 == null) {
            str3 = this.b;
        }
        String str4 = this.c;
        if ((h2 == 3 || h2 == 1) && (str = format.c) != null) {
            str4 = str;
        }
        int i2 = this.f;
        if (i2 == -1) {
            i2 = format.f;
        }
        int i3 = this.g;
        if (i3 == -1) {
            i3 = format.g;
        }
        String str5 = this.i;
        if (str5 == null) {
            String b2 = Util.b(format.i, h2);
            if (Util.f(b2).length == 1) {
                str5 = b2;
            }
        }
        Metadata metadata = this.j;
        Metadata a2 = metadata == null ? format.j : metadata.a(format.j);
        float f2 = this.q;
        if (f2 == -1.0f && h2 == 2) {
            f2 = format.q;
        }
        int i4 = this.d | format.d;
        int i5 = this.e | format.e;
        DrmInitData a3 = DrmInitData.a(format.o, this.o);
        Builder a4 = a();
        a4.a = str2;
        a4.b = str3;
        a4.c = str4;
        a4.d = i4;
        a4.e = i5;
        a4.f = i2;
        a4.g = i3;
        a4.h = str5;
        a4.i = a2;
        a4.n = a3;
        a4.r = f2;
        return a4.a();
    }

    public final Format a(Class cls) {
        Builder a2 = a();
        a2.D = cls;
        return a2.a();
    }

    public final int b() {
        int i2;
        int i3 = this.width;
        if (i3 == -1 || (i2 = this.height) == -1) {
            return -1;
        }
        return i3 * i2;
    }

    public final boolean b(Format format) {
        if (this.n.size() != format.n.size()) {
            return false;
        }
        for (int i2 = 0; i2 < this.n.size(); i2++) {
            if (!Arrays.equals((byte[]) this.n.get(i2), (byte[]) format.n.get(i2))) {
                return false;
            }
        }
        return true;
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        int i2;
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            Format format = (Format) obj;
            int i3 = this.D;
            if ((i3 == 0 || (i2 = format.D) == 0 || i3 == i2) && this.d == format.d && this.e == format.e && this.f == format.f && this.g == format.g && this.m == format.m && this.p == format.p && this.width == format.width && this.height == format.height && this.r == format.r && this.u == format.u && this.w == format.w && this.x == format.x && this.y == format.y && this.z == format.z && this.A == format.A && this.B == format.B && Float.compare(this.q, format.q) == 0 && Float.compare(this.s, format.s) == 0 && Util.a((Object) this.C, (Object) format.C) && Util.a((Object) this.a, (Object) format.a) && Util.a((Object) this.b, (Object) format.b) && Util.a((Object) this.i, (Object) format.i) && Util.a((Object) this.k, (Object) format.k) && Util.a((Object) this.l, (Object) format.l) && Util.a((Object) this.c, (Object) format.c) && Arrays.equals(this.t, format.t) && Util.a((Object) this.j, (Object) format.j) && Util.a((Object) this.v, (Object) format.v) && Util.a((Object) this.o, (Object) format.o) && b(format)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        if (this.D == 0) {
            String str = this.a;
            int i2 = 0;
            int hashCode = ((str == null ? 0 : str.hashCode()) + 527) * 31;
            String str2 = this.b;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.c;
            int hashCode3 = (((((((((hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31) + this.d) * 31) + this.e) * 31) + this.f) * 31) + this.g) * 31;
            String str4 = this.i;
            int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
            Metadata metadata = this.j;
            int hashCode5 = (hashCode4 + (metadata == null ? 0 : metadata.hashCode())) * 31;
            String str5 = this.k;
            int hashCode6 = (hashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
            String str6 = this.l;
            int hashCode7 = (((((((((((((((((((((((((((((hashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31) + this.m) * 31) + ((int) this.p)) * 31) + this.width) * 31) + this.height) * 31) + Float.floatToIntBits(this.q)) * 31) + this.r) * 31) + Float.floatToIntBits(this.s)) * 31) + this.u) * 31) + this.w) * 31) + this.x) * 31) + this.y) * 31) + this.z) * 31) + this.A) * 31) + this.B) * 31;
            Class cls = this.C;
            if (cls != null) {
                i2 = cls.hashCode();
            }
            this.D = hashCode7 + i2;
        }
        return this.D;
    }

    public final String toString() {
        String str = this.a;
        String str2 = this.b;
        String str3 = this.k;
        String str4 = this.l;
        String str5 = this.i;
        int i2 = this.h;
        String str6 = this.c;
        int i3 = this.width;
        int i4 = this.height;
        float f2 = this.q;
        int i5 = this.w;
        return new StringBuilder(String.valueOf(str).length() + 104 + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(str4).length() + String.valueOf(str5).length() + String.valueOf(str6).length()).append("Format(").append(str).append(", ").append(str2).append(", ").append(str3).append(", ").append(str4).append(", ").append(str5).append(", ").append(i2).append(", ").append(str6).append(", [").append(i3).append(", ").append(i4).append(", ").append(f2).append("], [").append(i5).append(", ").append(this.x).append("])").toString();
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
        parcel.writeInt(this.g);
        parcel.writeString(this.i);
        boolean z2 = false;
        parcel.writeParcelable(this.j, 0);
        parcel.writeString(this.k);
        parcel.writeString(this.l);
        parcel.writeInt(this.m);
        int size = this.n.size();
        parcel.writeInt(size);
        for (int i3 = 0; i3 < size; i3++) {
            parcel.writeByteArray((byte[]) this.n.get(i3));
        }
        parcel.writeParcelable(this.o, 0);
        parcel.writeLong(this.p);
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeFloat(this.q);
        parcel.writeInt(this.r);
        parcel.writeFloat(this.s);
        if (this.t != null) {
            z2 = true;
        }
        Util.a(parcel, z2);
        byte[] bArr = this.t;
        if (bArr != null) {
            parcel.writeByteArray(bArr);
        }
        parcel.writeInt(this.u);
        parcel.writeParcelable(this.v, i2);
        parcel.writeInt(this.w);
        parcel.writeInt(this.x);
        parcel.writeInt(this.y);
        parcel.writeInt(this.z);
        parcel.writeInt(this.A);
        parcel.writeInt(this.B);
    }
}
