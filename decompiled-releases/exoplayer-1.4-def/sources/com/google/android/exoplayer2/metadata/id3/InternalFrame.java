package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.util.Util;

public final class InternalFrame extends Id3Frame {
    public final String a;
    public final String b;
    public final String d;

    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new InternalFrame(parcel);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new InternalFrame[i];
            }
        };
    }

    InternalFrame(Parcel parcel) {
        super("----");
        this.a = (String) Util.a((Object) parcel.readString());
        this.b = (String) Util.a((Object) parcel.readString());
        this.d = (String) Util.a((Object) parcel.readString());
    }

    public InternalFrame(String str, String str2, String str3) {
        super("----");
        this.a = str;
        this.b = str2;
        this.d = str3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            InternalFrame internalFrame = (InternalFrame) obj;
            return Util.a((Object) this.b, (Object) internalFrame.b) && Util.a((Object) this.a, (Object) internalFrame.a) && Util.a((Object) this.d, (Object) internalFrame.d);
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
        return hashCode2 + i;
    }

    public final String toString() {
        String str = this.c;
        String str2 = this.a;
        String str3 = this.b;
        return new StringBuilder(String.valueOf(str).length() + 23 + String.valueOf(str2).length() + String.valueOf(str3).length()).append(str).append(": domain=").append(str2).append(", description=").append(str3).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.c);
        parcel.writeString(this.a);
        parcel.writeString(this.d);
    }
}
