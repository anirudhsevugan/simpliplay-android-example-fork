package com.google.android.exoplayer2.metadata.mp4;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.Metadata$Entry$$CC;

public final class SmtaMetadataEntry implements Metadata.Entry {
    private float a;
    private int b;

    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new SmtaMetadataEntry(parcel, (byte) 0);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new SmtaMetadataEntry[i];
            }
        };
    }

    public SmtaMetadataEntry(float f, int i) {
        this.a = f;
        this.b = i;
    }

    private SmtaMetadataEntry(Parcel parcel) {
        this.a = parcel.readFloat();
        this.b = parcel.readInt();
    }

    /* synthetic */ SmtaMetadataEntry(Parcel parcel, byte b2) {
        this(parcel);
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

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            SmtaMetadataEntry smtaMetadataEntry = (SmtaMetadataEntry) obj;
            return this.a == smtaMetadataEntry.a && this.b == smtaMetadataEntry.b;
        }
    }

    public final int hashCode() {
        return ((Float.valueOf(this.a).hashCode() + 527) * 31) + this.b;
    }

    public final String toString() {
        float f = this.a;
        return new StringBuilder(73).append("smta: captureFrameRate=").append(f).append(", svcTemporalLayerCount=").append(this.b).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.a);
        parcel.writeInt(this.b);
    }
}
