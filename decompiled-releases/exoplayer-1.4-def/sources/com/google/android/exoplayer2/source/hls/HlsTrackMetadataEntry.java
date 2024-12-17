package com.google.android.exoplayer2.source.hls;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.Metadata$Entry$$CC;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class HlsTrackMetadataEntry implements Metadata.Entry {
    public final List a;
    private String b;
    private String c;

    public final class VariantInfo implements Parcelable {
        private int a;
        private int b;
        private String c;
        private String d;
        private String e;
        private String f;

        static {
            new Parcelable.Creator() {
                public /* synthetic */ Object createFromParcel(Parcel parcel) {
                    return new VariantInfo(parcel);
                }

                public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                    return new VariantInfo[i];
                }
            };
        }

        public VariantInfo(int i, int i2, String str, String str2, String str3, String str4) {
            this.a = i;
            this.b = i2;
            this.c = str;
            this.d = str2;
            this.e = str3;
            this.f = str4;
        }

        VariantInfo(Parcel parcel) {
            this.a = parcel.readInt();
            this.b = parcel.readInt();
            this.c = parcel.readString();
            this.d = parcel.readString();
            this.e = parcel.readString();
            this.f = parcel.readString();
        }

        public final int describeContents() {
            return 0;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                VariantInfo variantInfo = (VariantInfo) obj;
                return this.a == variantInfo.a && this.b == variantInfo.b && TextUtils.equals(this.c, variantInfo.c) && TextUtils.equals(this.d, variantInfo.d) && TextUtils.equals(this.e, variantInfo.e) && TextUtils.equals(this.f, variantInfo.f);
            }
        }

        public final int hashCode() {
            int i = ((this.a * 31) + this.b) * 31;
            String str = this.c;
            int i2 = 0;
            int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.d;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.e;
            int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
            String str4 = this.f;
            if (str4 != null) {
                i2 = str4.hashCode();
            }
            return hashCode3 + i2;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
            parcel.writeString(this.c);
            parcel.writeString(this.d);
            parcel.writeString(this.e);
            parcel.writeString(this.f);
        }
    }

    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new HlsTrackMetadataEntry(parcel);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new HlsTrackMetadataEntry[i];
            }
        };
    }

    HlsTrackMetadataEntry(Parcel parcel) {
        this.b = parcel.readString();
        this.c = parcel.readString();
        int readInt = parcel.readInt();
        ArrayList arrayList = new ArrayList(readInt);
        for (int i = 0; i < readInt; i++) {
            arrayList.add((VariantInfo) parcel.readParcelable(VariantInfo.class.getClassLoader()));
        }
        this.a = Collections.unmodifiableList(arrayList);
    }

    public HlsTrackMetadataEntry(String str, String str2, List list) {
        this.b = str;
        this.c = str2;
        this.a = Collections.unmodifiableList(new ArrayList(list));
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
            HlsTrackMetadataEntry hlsTrackMetadataEntry = (HlsTrackMetadataEntry) obj;
            return TextUtils.equals(this.b, hlsTrackMetadataEntry.b) && TextUtils.equals(this.c, hlsTrackMetadataEntry.c) && this.a.equals(hlsTrackMetadataEntry.a);
        }
    }

    public final int hashCode() {
        String str = this.b;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.c;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return ((hashCode + i) * 31) + this.a.hashCode();
    }

    public final String toString() {
        String str;
        String str2 = this.b;
        if (str2 != null) {
            String str3 = this.c;
            str = new StringBuilder(String.valueOf(str2).length() + 5 + String.valueOf(str3).length()).append(" [").append(str2).append(", ").append(str3).append("]").toString();
        } else {
            str = "";
        }
        String valueOf = String.valueOf(str);
        return valueOf.length() != 0 ? "HlsTrackMetadataEntry".concat(valueOf) : new String("HlsTrackMetadataEntry");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        int size = this.a.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            parcel.writeParcelable((Parcelable) this.a.get(i2), 0);
        }
    }
}
