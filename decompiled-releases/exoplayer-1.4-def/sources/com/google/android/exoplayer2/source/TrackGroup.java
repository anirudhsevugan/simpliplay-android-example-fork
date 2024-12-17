package com.google.android.exoplayer2.source;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.appinventor.components.common.PropertyTypeConstants;
import java.util.Arrays;

public final class TrackGroup implements Parcelable {
    public final int a;
    public final Format[] b;
    private int c;

    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new TrackGroup(parcel);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new TrackGroup[i];
            }
        };
    }

    TrackGroup(Parcel parcel) {
        int readInt = parcel.readInt();
        this.a = readInt;
        this.b = new Format[readInt];
        for (int i = 0; i < this.a; i++) {
            this.b[i] = (Format) parcel.readParcelable(Format.class.getClassLoader());
        }
    }

    public TrackGroup(Format... formatArr) {
        Assertions.b(formatArr.length > 0);
        this.b = formatArr;
        this.a = formatArr.length;
        a();
    }

    private static String a(String str) {
        return (str == null || str.equals("und")) ? "" : str;
    }

    private void a() {
        String str;
        String str2;
        String str3;
        String a2 = a(this.b[0].c);
        int i = this.b[0].e | 16384;
        int i2 = 1;
        while (true) {
            Format[] formatArr = this.b;
            if (i2 >= formatArr.length) {
                return;
            }
            if (!a2.equals(a(formatArr[i2].c))) {
                str = this.b[0].c;
                str2 = this.b[i2].c;
                str3 = PropertyTypeConstants.PROPERTY_TYPE_TEXT_TO_SPEECH_LANGUAGES;
                break;
            } else if (i != (this.b[i2].e | 16384)) {
                str = Integer.toBinaryString(this.b[0].e);
                str2 = Integer.toBinaryString(this.b[i2].e);
                str3 = "role flags";
                break;
            } else {
                i2++;
            }
        }
        a(str3, str, str2, i2);
    }

    private static void a(String str, String str2, String str3, int i) {
        Log.b("TrackGroup", "", new IllegalStateException(new StringBuilder(String.valueOf(str).length() + 78 + String.valueOf(str2).length() + String.valueOf(str3).length()).append("Different ").append(str).append(" combined in one TrackGroup: '").append(str2).append("' (track 0) and '").append(str3).append("' (track ").append(i).append(")").toString()));
    }

    public final int a(Format format) {
        int i = 0;
        while (true) {
            Format[] formatArr = this.b;
            if (i >= formatArr.length) {
                return -1;
            }
            if (format == formatArr[i]) {
                return i;
            }
            i++;
        }
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            TrackGroup trackGroup = (TrackGroup) obj;
            return this.a == trackGroup.a && Arrays.equals(this.b, trackGroup.b);
        }
    }

    public final int hashCode() {
        if (this.c == 0) {
            this.c = Arrays.hashCode(this.b) + 527;
        }
        return this.c;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        for (int i2 = 0; i2 < this.a; i2++) {
            parcel.writeParcelable(this.b[i2], 0);
        }
    }
}
