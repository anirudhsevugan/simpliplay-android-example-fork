package com.google.android.exoplayer2.metadata;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;
import java.util.List;

public final class Metadata implements Parcelable {
    public final Entry[] a;

    public interface Entry extends Parcelable {
        Format a();

        void a(MediaMetadata.Builder builder);

        byte[] b();
    }

    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new Metadata(parcel);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new Metadata[i];
            }
        };
    }

    Metadata(Parcel parcel) {
        this.a = new Entry[parcel.readInt()];
        int i = 0;
        while (true) {
            Entry[] entryArr = this.a;
            if (i < entryArr.length) {
                entryArr[i] = (Entry) parcel.readParcelable(Entry.class.getClassLoader());
                i++;
            } else {
                return;
            }
        }
    }

    public Metadata(List list) {
        this.a = (Entry[]) list.toArray(new Entry[0]);
    }

    public Metadata(Entry... entryArr) {
        this.a = entryArr;
    }

    public final Metadata a(Metadata metadata) {
        return metadata == null ? this : a(metadata.a);
    }

    public final Metadata a(Entry... entryArr) {
        return entryArr.length == 0 ? this : new Metadata((Entry[]) Util.a((Object[]) this.a, (Object[]) entryArr));
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.a, ((Metadata) obj).a);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.a);
    }

    public final String toString() {
        String valueOf = String.valueOf(Arrays.toString(this.a));
        return valueOf.length() != 0 ? "entries=".concat(valueOf) : new String("entries=");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a.length);
        for (Entry writeParcelable : this.a) {
            parcel.writeParcelable(writeParcelable, 0);
        }
    }
}
