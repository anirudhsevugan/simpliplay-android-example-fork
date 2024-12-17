package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public final class ChapterTocFrame extends Id3Frame {
    private String a;
    private boolean b;
    private boolean d;
    private String[] e;
    private final Id3Frame[] f;

    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new ChapterTocFrame(parcel);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new ChapterTocFrame[i];
            }
        };
    }

    ChapterTocFrame(Parcel parcel) {
        super("CTOC");
        this.a = (String) Util.a((Object) parcel.readString());
        boolean z = true;
        this.b = parcel.readByte() != 0;
        this.d = parcel.readByte() == 0 ? false : z;
        this.e = (String[]) Util.a((Object) parcel.createStringArray());
        int readInt = parcel.readInt();
        this.f = new Id3Frame[readInt];
        for (int i = 0; i < readInt; i++) {
            this.f[i] = (Id3Frame) parcel.readParcelable(Id3Frame.class.getClassLoader());
        }
    }

    public ChapterTocFrame(String str, boolean z, boolean z2, String[] strArr, Id3Frame[] id3FrameArr) {
        super("CTOC");
        this.a = str;
        this.b = z;
        this.d = z2;
        this.e = strArr;
        this.f = id3FrameArr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            ChapterTocFrame chapterTocFrame = (ChapterTocFrame) obj;
            return this.b == chapterTocFrame.b && this.d == chapterTocFrame.d && Util.a((Object) this.a, (Object) chapterTocFrame.a) && Arrays.equals(this.e, chapterTocFrame.e) && Arrays.equals(this.f, chapterTocFrame.f);
        }
    }

    public final int hashCode() {
        int i = ((((this.b ? 1 : 0) + true) * 31) + (this.d ? 1 : 0)) * 31;
        String str = this.a;
        return i + (str != null ? str.hashCode() : 0);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeByte(this.b ? (byte) 1 : 0);
        parcel.writeByte(this.d ? (byte) 1 : 0);
        parcel.writeStringArray(this.e);
        parcel.writeInt(this.f.length);
        for (Id3Frame writeParcelable : this.f) {
            parcel.writeParcelable(writeParcelable, 0);
        }
    }
}
