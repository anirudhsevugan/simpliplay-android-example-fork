package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public final class BinaryFrame extends Id3Frame {
    private byte[] a;

    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new BinaryFrame(parcel);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new BinaryFrame[i];
            }
        };
    }

    BinaryFrame(Parcel parcel) {
        super((String) Util.a((Object) parcel.readString()));
        this.a = (byte[]) Util.a((Object) parcel.createByteArray());
    }

    public BinaryFrame(String str, byte[] bArr) {
        super(str);
        this.a = bArr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            BinaryFrame binaryFrame = (BinaryFrame) obj;
            return this.c.equals(binaryFrame.c) && Arrays.equals(this.a, binaryFrame.a);
        }
    }

    public final int hashCode() {
        return ((this.c.hashCode() + 527) * 31) + Arrays.hashCode(this.a);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.c);
        parcel.writeByteArray(this.a);
    }
}
