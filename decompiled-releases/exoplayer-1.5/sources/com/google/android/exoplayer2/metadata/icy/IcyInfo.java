package com.google.android.exoplayer2.metadata.icy;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.Metadata$Entry$$CC;
import com.google.android.exoplayer2.util.Assertions;
import java.util.Arrays;

public final class IcyInfo implements Metadata.Entry {
    private byte[] a;
    private String b;
    private String c;

    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new IcyInfo(parcel);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new IcyInfo[i];
            }
        };
    }

    IcyInfo(Parcel parcel) {
        this.a = (byte[]) Assertions.b((Object) parcel.createByteArray());
        this.b = parcel.readString();
        this.c = parcel.readString();
    }

    public IcyInfo(byte[] bArr, String str, String str2) {
        this.a = bArr;
        this.b = str;
        this.c = str2;
    }

    public final Format a() {
        return Metadata$Entry$$CC.a();
    }

    public final void a(MediaMetadata.Builder builder) {
        String str = this.b;
        if (str != null) {
            builder.a = str;
        }
    }

    public final byte[] b() {
        return Metadata$Entry$$CC.b();
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.a, ((IcyInfo) obj).a);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.a);
    }

    public final String toString() {
        return String.format("ICY: title=\"%s\", url=\"%s\", rawMetadata.length=\"%s\"", new Object[]{this.b, this.c, Integer.valueOf(this.a.length)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
    }
}
