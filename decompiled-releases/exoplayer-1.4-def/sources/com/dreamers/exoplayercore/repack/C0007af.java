package com.dreamers.exoplayercore.repack;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.dreamers.exoplayercore.repack.af  reason: case insensitive filesystem */
final class C0007af implements Parcelable.ClassLoaderCreator {
    C0007af() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new C0006ae(parcel, (ClassLoader) null);
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return new C0006ae(parcel, classLoader);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new C0006ae[i];
    }
}
