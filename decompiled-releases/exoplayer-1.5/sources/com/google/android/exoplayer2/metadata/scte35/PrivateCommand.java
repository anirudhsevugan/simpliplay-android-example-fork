package com.google.android.exoplayer2.metadata.scte35;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;

public final class PrivateCommand extends SpliceCommand {
    private long a;
    private long b;
    private byte[] c;

    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new PrivateCommand(parcel, (byte) 0);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new PrivateCommand[i];
            }
        };
    }

    private PrivateCommand(long j, byte[] bArr, long j2) {
        this.a = j2;
        this.b = j;
        this.c = bArr;
    }

    private PrivateCommand(Parcel parcel) {
        this.a = parcel.readLong();
        this.b = parcel.readLong();
        this.c = (byte[]) Util.a((Object) parcel.createByteArray());
    }

    /* synthetic */ PrivateCommand(Parcel parcel, byte b2) {
        this(parcel);
    }

    static PrivateCommand a(ParsableByteArray parsableByteArray, int i, long j) {
        long h = parsableByteArray.h();
        int i2 = i - 4;
        byte[] bArr = new byte[i2];
        parsableByteArray.a(bArr, 0, i2);
        return new PrivateCommand(h, bArr, j);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.a);
        parcel.writeLong(this.b);
        parcel.writeByteArray(this.c);
    }
}
