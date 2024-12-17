package com.google.android.exoplayer2.video;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public final class ColorInfo implements Parcelable {
    public final int a;
    public final int b;
    public final int c;
    public final byte[] d;
    private int e;

    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new ColorInfo(parcel);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new ColorInfo[i];
            }
        };
    }

    public ColorInfo(int i, int i2, int i3, byte[] bArr) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = bArr;
    }

    ColorInfo(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = Util.a(parcel) ? parcel.createByteArray() : null;
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            ColorInfo colorInfo = (ColorInfo) obj;
            return this.a == colorInfo.a && this.b == colorInfo.b && this.c == colorInfo.c && Arrays.equals(this.d, colorInfo.d);
        }
    }

    public final int hashCode() {
        if (this.e == 0) {
            this.e = ((((((this.a + 527) * 31) + this.b) * 31) + this.c) * 31) + Arrays.hashCode(this.d);
        }
        return this.e;
    }

    public final String toString() {
        int i = this.a;
        int i2 = this.b;
        return new StringBuilder(55).append("ColorInfo(").append(i).append(", ").append(i2).append(", ").append(this.c).append(", ").append(this.d != null).append(")").toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        Util.a(parcel, this.d != null);
        byte[] bArr = this.d;
        if (bArr != null) {
            parcel.writeByteArray(bArr);
        }
    }
}
