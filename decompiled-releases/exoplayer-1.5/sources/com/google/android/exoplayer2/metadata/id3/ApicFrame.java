package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public final class ApicFrame extends Id3Frame {
    public final int a;
    public final byte[] b;
    private String d;
    private String e;

    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new ApicFrame(parcel);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new ApicFrame[i];
            }
        };
    }

    ApicFrame(Parcel parcel) {
        super("APIC");
        this.d = (String) Util.a((Object) parcel.readString());
        this.e = parcel.readString();
        this.a = parcel.readInt();
        this.b = (byte[]) Util.a((Object) parcel.createByteArray());
    }

    public ApicFrame(String str, String str2, int i, byte[] bArr) {
        super("APIC");
        this.d = str;
        this.e = str2;
        this.a = i;
        this.b = bArr;
    }

    public final void a(MediaMetadata.Builder builder) {
        builder.a(this.b);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            ApicFrame apicFrame = (ApicFrame) obj;
            return this.a == apicFrame.a && Util.a((Object) this.d, (Object) apicFrame.d) && Util.a((Object) this.e, (Object) apicFrame.e) && Arrays.equals(this.b, apicFrame.b);
        }
    }

    public final int hashCode() {
        int i = (this.a + 527) * 31;
        String str = this.d;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.e;
        if (str2 != null) {
            i2 = str2.hashCode();
        }
        return ((hashCode + i2) * 31) + Arrays.hashCode(this.b);
    }

    public final String toString() {
        String str = this.c;
        String str2 = this.d;
        String str3 = this.e;
        return new StringBuilder(String.valueOf(str).length() + 25 + String.valueOf(str2).length() + String.valueOf(str3).length()).append(str).append(": mimeType=").append(str2).append(", description=").append(str3).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeInt(this.a);
        parcel.writeByteArray(this.b);
    }
}
