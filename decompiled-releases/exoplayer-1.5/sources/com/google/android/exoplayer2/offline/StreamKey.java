package com.google.android.exoplayer2.offline;

import android.os.Parcel;
import android.os.Parcelable;

public final class StreamKey implements Parcelable, Comparable {
    public final int a;
    public final int b;
    public final int c;

    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new StreamKey(parcel);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new StreamKey[i];
            }
        };
    }

    public StreamKey() {
        this.a = -1;
        this.b = -1;
        this.c = -1;
    }

    StreamKey(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        StreamKey streamKey = (StreamKey) obj;
        int i = this.a - streamKey.a;
        if (i != 0) {
            return i;
        }
        int i2 = this.b - streamKey.b;
        return i2 == 0 ? this.c - streamKey.c : i2;
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            StreamKey streamKey = (StreamKey) obj;
            return this.a == streamKey.a && this.b == streamKey.b && this.c == streamKey.c;
        }
    }

    public final int hashCode() {
        return (((this.a * 31) + this.b) * 31) + this.c;
    }

    public final String toString() {
        int i = this.a;
        int i2 = this.b;
        return new StringBuilder(35).append(i).append(".").append(i2).append(".").append(this.c).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
    }
}
