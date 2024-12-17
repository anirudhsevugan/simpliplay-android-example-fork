package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public final class GeobFrame extends Id3Frame {
    private String a;
    private String b;
    private String d;
    private byte[] e;

    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new GeobFrame(parcel);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new GeobFrame[i];
            }
        };
    }

    GeobFrame(Parcel parcel) {
        super("GEOB");
        this.a = (String) Util.a((Object) parcel.readString());
        this.b = (String) Util.a((Object) parcel.readString());
        this.d = (String) Util.a((Object) parcel.readString());
        this.e = (byte[]) Util.a((Object) parcel.createByteArray());
    }

    public GeobFrame(String str, String str2, String str3, byte[] bArr) {
        super("GEOB");
        this.a = str;
        this.b = str2;
        this.d = str3;
        this.e = bArr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            GeobFrame geobFrame = (GeobFrame) obj;
            return Util.a((Object) this.a, (Object) geobFrame.a) && Util.a((Object) this.b, (Object) geobFrame.b) && Util.a((Object) this.d, (Object) geobFrame.d) && Arrays.equals(this.e, geobFrame.e);
        }
    }

    public final int hashCode() {
        String str = this.a;
        int i = 0;
        int hashCode = ((str != null ? str.hashCode() : 0) + 527) * 31;
        String str2 = this.b;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.d;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return ((hashCode2 + i) * 31) + Arrays.hashCode(this.e);
    }

    public final String toString() {
        String str = this.c;
        String str2 = this.a;
        String str3 = this.b;
        String str4 = this.d;
        return new StringBuilder(String.valueOf(str).length() + 36 + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(str4).length()).append(str).append(": mimeType=").append(str2).append(", filename=").append(str3).append(", description=").append(str4).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.d);
        parcel.writeByteArray(this.e);
    }
}
