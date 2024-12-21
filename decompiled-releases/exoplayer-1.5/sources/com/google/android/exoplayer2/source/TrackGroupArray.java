package com.google.android.exoplayer2.source;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

public final class TrackGroupArray implements Parcelable {
    public static final TrackGroupArray a = new TrackGroupArray(new TrackGroup[0]);
    public final TrackGroup[] b;
    private int c;
    public final int length;

    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new TrackGroupArray(parcel);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new TrackGroupArray[i];
            }
        };
    }

    TrackGroupArray(Parcel parcel) {
        int readInt = parcel.readInt();
        this.length = readInt;
        this.b = new TrackGroup[readInt];
        for (int i = 0; i < this.length; i++) {
            this.b[i] = (TrackGroup) parcel.readParcelable(TrackGroup.class.getClassLoader());
        }
    }

    public TrackGroupArray(TrackGroup... trackGroupArr) {
        this.b = trackGroupArr;
        this.length = trackGroupArr.length;
    }

    public final int a(TrackGroup trackGroup) {
        for (int i = 0; i < this.length; i++) {
            if (this.b[i] == trackGroup) {
                return i;
            }
        }
        return -1;
    }

    public final boolean a() {
        return this.length == 0;
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            TrackGroupArray trackGroupArray = (TrackGroupArray) obj;
            return this.length == trackGroupArray.length && Arrays.equals(this.b, trackGroupArray.b);
        }
    }

    public final int hashCode() {
        if (this.c == 0) {
            this.c = Arrays.hashCode(this.b);
        }
        return this.c;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.length);
        for (int i2 = 0; i2 < this.length; i2++) {
            parcel.writeParcelable(this.b[i2], 0);
        }
    }
}
