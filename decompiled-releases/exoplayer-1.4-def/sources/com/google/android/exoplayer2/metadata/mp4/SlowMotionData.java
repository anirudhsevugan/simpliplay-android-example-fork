package com.google.android.exoplayer2.metadata.mp4;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.Metadata$Entry$$CC;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public final class SlowMotionData implements Metadata.Entry {
    private List a;

    public final class Segment implements Parcelable {
        public final long a;
        public final long b;
        private int c;

        static {
            Comparator comparator = SlowMotionData$Segment$$Lambda$0.a;
            new Parcelable.Creator() {
                public /* synthetic */ Object createFromParcel(Parcel parcel) {
                    return new Segment(parcel.readLong(), parcel.readLong(), parcel.readInt());
                }

                public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                    return new Segment[i];
                }
            };
        }

        public Segment(long j, long j2, int i) {
            Assertions.a(j < j2);
            this.a = j;
            this.b = j2;
            this.c = i;
        }

        public final int describeContents() {
            return 0;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                Segment segment = (Segment) obj;
                return this.a == segment.a && this.b == segment.b && this.c == segment.c;
            }
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{Long.valueOf(this.a), Long.valueOf(this.b), Integer.valueOf(this.c)});
        }

        public final String toString() {
            return Util.a("Segment: startTimeMs=%d, endTimeMs=%d, speedDivisor=%d", Long.valueOf(this.a), Long.valueOf(this.b), Integer.valueOf(this.c));
        }

        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(this.a);
            parcel.writeLong(this.b);
            parcel.writeInt(this.c);
        }
    }

    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                ArrayList arrayList = new ArrayList();
                parcel.readList(arrayList, Segment.class.getClassLoader());
                return new SlowMotionData(arrayList);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new SlowMotionData[i];
            }
        };
    }

    public SlowMotionData(List list) {
        this.a = list;
        Assertions.a(!a(list));
    }

    private static boolean a(List list) {
        if (list.isEmpty()) {
            return false;
        }
        long j = ((Segment) list.get(0)).b;
        for (int i = 1; i < list.size(); i++) {
            if (((Segment) list.get(i)).a < j) {
                return true;
            }
            j = ((Segment) list.get(i)).b;
        }
        return false;
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
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((SlowMotionData) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.a);
        return new StringBuilder(String.valueOf(valueOf).length() + 21).append("SlowMotion: segments=").append(valueOf).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.a);
    }
}
