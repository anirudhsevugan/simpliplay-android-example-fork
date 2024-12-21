package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.util.Util;

public final class CommentFrame extends Id3Frame {
    public final String a;
    public final String b;
    private String d;

    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new CommentFrame(parcel);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new CommentFrame[i];
            }
        };
    }

    CommentFrame(Parcel parcel) {
        super("COMM");
        this.d = (String) Util.a((Object) parcel.readString());
        this.a = (String) Util.a((Object) parcel.readString());
        this.b = (String) Util.a((Object) parcel.readString());
    }

    public CommentFrame(String str, String str2, String str3) {
        super("COMM");
        this.d = str;
        this.a = str2;
        this.b = str3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            CommentFrame commentFrame = (CommentFrame) obj;
            return Util.a((Object) this.a, (Object) commentFrame.a) && Util.a((Object) this.d, (Object) commentFrame.d) && Util.a((Object) this.b, (Object) commentFrame.b);
        }
    }

    public final int hashCode() {
        String str = this.d;
        int i = 0;
        int hashCode = ((str != null ? str.hashCode() : 0) + 527) * 31;
        String str2 = this.a;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.b;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }

    public final String toString() {
        String str = this.c;
        String str2 = this.d;
        String str3 = this.a;
        return new StringBuilder(String.valueOf(str).length() + 25 + String.valueOf(str2).length() + String.valueOf(str3).length()).append(str).append(": language=").append(str2).append(", description=").append(str3).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.b);
    }
}
