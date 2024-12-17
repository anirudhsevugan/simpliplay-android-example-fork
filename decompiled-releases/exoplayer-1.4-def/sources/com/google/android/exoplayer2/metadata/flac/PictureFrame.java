package com.google.android.exoplayer2.metadata.flac;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.Metadata$Entry$$CC;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public final class PictureFrame implements Metadata.Entry {
    public final int a;
    public final byte[] b;
    private String c;
    private String d;
    private int e;
    private int f;
    private int g;
    private int h;

    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new PictureFrame(parcel);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new PictureFrame[i];
            }
        };
    }

    public PictureFrame(int i, String str, String str2, int i2, int i3, int i4, int i5, byte[] bArr) {
        this.a = i;
        this.c = str;
        this.d = str2;
        this.e = i2;
        this.f = i3;
        this.g = i4;
        this.h = i5;
        this.b = bArr;
    }

    PictureFrame(Parcel parcel) {
        this.a = parcel.readInt();
        this.c = (String) Util.a((Object) parcel.readString());
        this.d = (String) Util.a((Object) parcel.readString());
        this.e = parcel.readInt();
        this.f = parcel.readInt();
        this.g = parcel.readInt();
        this.h = parcel.readInt();
        this.b = (byte[]) Util.a((Object) parcel.createByteArray());
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
            PictureFrame pictureFrame = (PictureFrame) obj;
            return this.a == pictureFrame.a && this.c.equals(pictureFrame.c) && this.d.equals(pictureFrame.d) && this.e == pictureFrame.e && this.f == pictureFrame.f && this.g == pictureFrame.g && this.h == pictureFrame.h && Arrays.equals(this.b, pictureFrame.b);
        }
    }

    public final int hashCode() {
        return ((((((((((((((this.a + 527) * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31) + this.e) * 31) + this.f) * 31) + this.g) * 31) + this.h) * 31) + Arrays.hashCode(this.b);
    }

    public final String toString() {
        String str = this.c;
        String str2 = this.d;
        return new StringBuilder(String.valueOf(str).length() + 32 + String.valueOf(str2).length()).append("Picture: mimeType=").append(str).append(", description=").append(str2).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
        parcel.writeInt(this.g);
        parcel.writeInt(this.h);
        parcel.writeByteArray(this.b);
    }
}
