package com.google.android.exoplayer2.metadata.dvbsi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.Metadata$Entry$$CC;
import com.google.android.exoplayer2.util.Assertions;

public final class AppInfoTable implements Metadata.Entry {
    private int a;
    private String b;

    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new AppInfoTable(parcel.readInt(), (String) Assertions.b((Object) parcel.readString()));
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new AppInfoTable[i];
            }
        };
    }

    public AppInfoTable(int i, String str) {
        this.a = i;
        this.b = str;
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

    public final String toString() {
        int i = this.a;
        String str = this.b;
        return new StringBuilder(String.valueOf(str).length() + 33).append("Ait(controlCode=").append(i).append(",url=").append(str).append(")").toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.b);
        parcel.writeInt(this.a);
    }
}
