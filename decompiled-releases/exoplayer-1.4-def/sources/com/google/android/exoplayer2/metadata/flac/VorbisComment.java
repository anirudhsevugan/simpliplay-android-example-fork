package com.google.android.exoplayer2.metadata.flac;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.Metadata$Entry$$CC;
import com.google.android.exoplayer2.util.Util;

public final class VorbisComment implements Metadata.Entry {
    private String a;
    private String b;

    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new VorbisComment(parcel);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new VorbisComment[i];
            }
        };
    }

    VorbisComment(Parcel parcel) {
        this.a = (String) Util.a((Object) parcel.readString());
        this.b = (String) Util.a((Object) parcel.readString());
    }

    public VorbisComment(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public final Format a() {
        return Metadata$Entry$$CC.a();
    }

    public final void a(MediaMetadata.Builder builder) {
        Metadata$Entry$$CC.c();
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
        if (obj != null && getClass() == obj.getClass()) {
            VorbisComment vorbisComment = (VorbisComment) obj;
            return this.a.equals(vorbisComment.a) && this.b.equals(vorbisComment.b);
        }
    }

    public final int hashCode() {
        return ((this.a.hashCode() + 527) * 31) + this.b.hashCode();
    }

    public final String toString() {
        String str = this.a;
        String str2 = this.b;
        return new StringBuilder(String.valueOf(str).length() + 5 + String.valueOf(str2).length()).append("VC: ").append(str).append("=").append(str2).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
    }
}
