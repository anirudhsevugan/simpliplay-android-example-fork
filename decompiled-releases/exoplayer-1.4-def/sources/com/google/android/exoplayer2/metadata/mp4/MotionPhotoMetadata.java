package com.google.android.exoplayer2.metadata.mp4;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.Metadata$Entry$$CC;

public final class MotionPhotoMetadata implements Metadata.Entry {
    public final long a;
    private long b;
    private long c;
    private long d;
    private long e;

    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new MotionPhotoMetadata(parcel, (byte) 0);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new MotionPhotoMetadata[i];
            }
        };
    }

    public MotionPhotoMetadata(long j, long j2, long j3, long j4, long j5) {
        this.b = j;
        this.c = j2;
        this.d = j3;
        this.a = j4;
        this.e = j5;
    }

    private MotionPhotoMetadata(Parcel parcel) {
        this.b = parcel.readLong();
        this.c = parcel.readLong();
        this.d = parcel.readLong();
        this.a = parcel.readLong();
        this.e = parcel.readLong();
    }

    /* synthetic */ MotionPhotoMetadata(Parcel parcel, byte b2) {
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
            MotionPhotoMetadata motionPhotoMetadata = (MotionPhotoMetadata) obj;
            return this.b == motionPhotoMetadata.b && this.c == motionPhotoMetadata.c && this.d == motionPhotoMetadata.d && this.a == motionPhotoMetadata.a && this.e == motionPhotoMetadata.e;
        }
    }

    public final int hashCode() {
        long j = this.b;
        long j2 = this.c;
        long j3 = this.d;
        long j4 = this.a;
        long j5 = this.e;
        return ((((((((((int) (j ^ (j >>> 32))) + 527) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31) + ((int) (j4 ^ (j4 >>> 32)))) * 31) + ((int) (j5 ^ (j5 >>> 32)));
    }

    public final String toString() {
        long j = this.b;
        long j2 = this.c;
        long j3 = this.d;
        long j4 = this.a;
        return new StringBuilder(218).append("Motion photo metadata: photoStartPosition=").append(j).append(", photoSize=").append(j2).append(", photoPresentationTimestampUs=").append(j3).append(", videoStartPosition=").append(j4).append(", videoSize=").append(this.e).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.b);
        parcel.writeLong(this.c);
        parcel.writeLong(this.d);
        parcel.writeLong(this.a);
        parcel.writeLong(this.e);
    }
}
