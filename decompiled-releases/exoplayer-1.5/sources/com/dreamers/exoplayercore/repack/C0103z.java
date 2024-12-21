package com.dreamers.exoplayercore.repack;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.dreamers.exoplayercore.repack.z  reason: case insensitive filesystem */
public final class C0103z implements Parcelable {
    int a;
    int b;
    boolean c;

    static {
        new A();
    }

    public C0103z() {
    }

    C0103z(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readInt();
        this.c = parcel.readInt() != 1 ? false : true;
    }

    public C0103z(C0103z zVar) {
        this.a = zVar.a;
        this.b = zVar.b;
        this.c = zVar.c;
    }

    /* access modifiers changed from: package-private */
    public final boolean a() {
        return this.a >= 0;
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c ? 1 : 0);
    }
}
