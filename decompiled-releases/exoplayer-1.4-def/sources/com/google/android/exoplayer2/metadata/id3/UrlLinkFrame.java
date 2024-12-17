package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.util.Util;

public final class UrlLinkFrame extends Id3Frame {
    private String a;
    private String b;

    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new UrlLinkFrame(parcel);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new UrlLinkFrame[i];
            }
        };
    }

    UrlLinkFrame(Parcel parcel) {
        super((String) Util.a((Object) parcel.readString()));
        this.a = parcel.readString();
        this.b = (String) Util.a((Object) parcel.readString());
    }

    public UrlLinkFrame(String str, String str2, String str3) {
        super(str);
        this.a = str2;
        this.b = str3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            UrlLinkFrame urlLinkFrame = (UrlLinkFrame) obj;
            return this.c.equals(urlLinkFrame.c) && Util.a((Object) this.a, (Object) urlLinkFrame.a) && Util.a((Object) this.b, (Object) urlLinkFrame.b);
        }
    }

    public final int hashCode() {
        int hashCode = (this.c.hashCode() + 527) * 31;
        String str = this.a;
        int i = 0;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.b;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode2 + i;
    }

    public final String toString() {
        String str = this.c;
        String str2 = this.b;
        return new StringBuilder(String.valueOf(str).length() + 6 + String.valueOf(str2).length()).append(str).append(": url=").append(str2).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.c);
        parcel.writeString(this.a);
        parcel.writeString(this.b);
    }
}
