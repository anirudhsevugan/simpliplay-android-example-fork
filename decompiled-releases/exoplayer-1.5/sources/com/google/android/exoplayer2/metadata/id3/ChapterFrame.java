package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public final class ChapterFrame extends Id3Frame {
    private String a;
    private int b;
    private int d;
    private long e;
    private long f;
    private final Id3Frame[] g;

    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new ChapterFrame(parcel);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new ChapterFrame[i];
            }
        };
    }

    ChapterFrame(Parcel parcel) {
        super("CHAP");
        this.a = (String) Util.a((Object) parcel.readString());
        this.b = parcel.readInt();
        this.d = parcel.readInt();
        this.e = parcel.readLong();
        this.f = parcel.readLong();
        int readInt = parcel.readInt();
        this.g = new Id3Frame[readInt];
        for (int i = 0; i < readInt; i++) {
            this.g[i] = (Id3Frame) parcel.readParcelable(Id3Frame.class.getClassLoader());
        }
    }

    public ChapterFrame(String str, int i, int i2, long j, long j2, Id3Frame[] id3FrameArr) {
        super("CHAP");
        this.a = str;
        this.b = i;
        this.d = i2;
        this.e = j;
        this.f = j2;
        this.g = id3FrameArr;
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            ChapterFrame chapterFrame = (ChapterFrame) obj;
            return this.b == chapterFrame.b && this.d == chapterFrame.d && this.e == chapterFrame.e && this.f == chapterFrame.f && Util.a((Object) this.a, (Object) chapterFrame.a) && Arrays.equals(this.g, chapterFrame.g);
        }
    }

    public final int hashCode() {
        int i = (((((((this.b + 527) * 31) + this.d) * 31) + ((int) this.e)) * 31) + ((int) this.f)) * 31;
        String str = this.a;
        return i + (str != null ? str.hashCode() : 0);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.d);
        parcel.writeLong(this.e);
        parcel.writeLong(this.f);
        parcel.writeInt(this.g.length);
        for (Id3Frame writeParcelable : this.g) {
            parcel.writeParcelable(writeParcelable, 0);
        }
    }
}
