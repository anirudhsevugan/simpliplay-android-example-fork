package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public final class MlltFrame extends Id3Frame {
    public final int a;
    public final int b;
    public final int[] d;
    public final int[] e;
    private int f;

    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new MlltFrame(parcel);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new MlltFrame[i];
            }
        };
    }

    public MlltFrame(int i, int i2, int i3, int[] iArr, int[] iArr2) {
        super("MLLT");
        this.f = i;
        this.a = i2;
        this.b = i3;
        this.d = iArr;
        this.e = iArr2;
    }

    MlltFrame(Parcel parcel) {
        super("MLLT");
        this.f = parcel.readInt();
        this.a = parcel.readInt();
        this.b = parcel.readInt();
        this.d = (int[]) Util.a((Object) parcel.createIntArray());
        this.e = (int[]) Util.a((Object) parcel.createIntArray());
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            MlltFrame mlltFrame = (MlltFrame) obj;
            return this.f == mlltFrame.f && this.a == mlltFrame.a && this.b == mlltFrame.b && Arrays.equals(this.d, mlltFrame.d) && Arrays.equals(this.e, mlltFrame.e);
        }
    }

    public final int hashCode() {
        return ((((((((this.f + 527) * 31) + this.a) * 31) + this.b) * 31) + Arrays.hashCode(this.d)) * 31) + Arrays.hashCode(this.e);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f);
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
        parcel.writeIntArray(this.d);
        parcel.writeIntArray(this.e);
    }
}
