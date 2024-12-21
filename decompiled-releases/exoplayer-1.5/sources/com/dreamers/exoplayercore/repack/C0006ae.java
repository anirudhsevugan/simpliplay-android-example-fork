package com.dreamers.exoplayercore.repack;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.customview.view.AbsSavedState;

/* renamed from: com.dreamers.exoplayercore.repack.ae  reason: case insensitive filesystem */
public final class C0006ae extends AbsSavedState {
    Parcelable a;

    static {
        new C0007af();
    }

    C0006ae(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.a = parcel.readParcelable(classLoader == null ? U.class.getClassLoader() : classLoader);
    }

    C0006ae(Parcelable parcelable) {
        super(parcelable);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.a, 0);
    }
}
