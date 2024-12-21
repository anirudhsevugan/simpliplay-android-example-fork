package com.google.android.exoplayer2.drm;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public final class DrmInitData implements Parcelable, Comparator {
    public final SchemeData[] a;
    public final String b;
    public final int c;
    private int d;

    public final class SchemeData implements Parcelable {
        public static final Parcelable.Creator e = new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new SchemeData(parcel);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new SchemeData[i];
            }
        };
        public final UUID a;
        public final String b;
        public final String c;
        public final byte[] d;
        private int f;

        SchemeData(Parcel parcel) {
            this.a = new UUID(parcel.readLong(), parcel.readLong());
            this.b = parcel.readString();
            this.c = (String) Util.a((Object) parcel.readString());
            this.d = parcel.createByteArray();
        }

        public SchemeData(UUID uuid, String str, String str2, byte[] bArr) {
            this.a = (UUID) Assertions.b((Object) uuid);
            this.b = str;
            this.c = (String) Assertions.b((Object) str2);
            this.d = bArr;
        }

        public SchemeData(UUID uuid, String str, byte[] bArr) {
            this(uuid, (String) null, str, bArr);
        }

        public final SchemeData a(byte[] bArr) {
            return new SchemeData(this.a, this.b, this.c, bArr);
        }

        public final boolean a() {
            return this.d != null;
        }

        public final boolean a(UUID uuid) {
            return C.a.equals(this.a) || uuid.equals(this.a);
        }

        public final int describeContents() {
            return 0;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof SchemeData)) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            SchemeData schemeData = (SchemeData) obj;
            return Util.a((Object) this.b, (Object) schemeData.b) && Util.a((Object) this.c, (Object) schemeData.c) && Util.a((Object) this.a, (Object) schemeData.a) && Arrays.equals(this.d, schemeData.d);
        }

        public final int hashCode() {
            if (this.f == 0) {
                int hashCode = this.a.hashCode() * 31;
                String str = this.b;
                this.f = ((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.c.hashCode()) * 31) + Arrays.hashCode(this.d);
            }
            return this.f;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(this.a.getMostSignificantBits());
            parcel.writeLong(this.a.getLeastSignificantBits());
            parcel.writeString(this.b);
            parcel.writeString(this.c);
            parcel.writeByteArray(this.d);
        }
    }

    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new DrmInitData(parcel);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new DrmInitData[i];
            }
        };
    }

    DrmInitData(Parcel parcel) {
        this.b = parcel.readString();
        SchemeData[] schemeDataArr = (SchemeData[]) Util.a((Object) (SchemeData[]) parcel.createTypedArray(SchemeData.e));
        this.a = schemeDataArr;
        this.c = schemeDataArr.length;
    }

    public DrmInitData(String str, List list) {
        this(str, false, (SchemeData[]) list.toArray(new SchemeData[0]));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: com.google.android.exoplayer2.drm.DrmInitData$SchemeData[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private DrmInitData(java.lang.String r1, boolean r2, com.google.android.exoplayer2.drm.DrmInitData.SchemeData... r3) {
        /*
            r0 = this;
            r0.<init>()
            r0.b = r1
            if (r2 == 0) goto L_0x000e
            java.lang.Object r1 = r3.clone()
            r3 = r1
            com.google.android.exoplayer2.drm.DrmInitData$SchemeData[] r3 = (com.google.android.exoplayer2.drm.DrmInitData.SchemeData[]) r3
        L_0x000e:
            r0.a = r3
            int r1 = r3.length
            r0.c = r1
            java.util.Arrays.sort(r3, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.drm.DrmInitData.<init>(java.lang.String, boolean, com.google.android.exoplayer2.drm.DrmInitData$SchemeData[]):void");
    }

    public DrmInitData(String str, SchemeData... schemeDataArr) {
        this(str, true, schemeDataArr);
    }

    public DrmInitData(List list) {
        this((String) null, false, (SchemeData[]) list.toArray(new SchemeData[0]));
    }

    public DrmInitData(SchemeData... schemeDataArr) {
        this((String) null, schemeDataArr);
    }

    public static DrmInitData a(DrmInitData drmInitData, DrmInitData drmInitData2) {
        String str;
        ArrayList arrayList = new ArrayList();
        if (drmInitData != null) {
            str = drmInitData.b;
            for (SchemeData schemeData : drmInitData.a) {
                if (schemeData.a()) {
                    arrayList.add(schemeData);
                }
            }
        } else {
            str = null;
        }
        if (drmInitData2 != null) {
            if (str == null) {
                str = drmInitData2.b;
            }
            int size = arrayList.size();
            for (SchemeData schemeData2 : drmInitData2.a) {
                if (schemeData2.a() && !a(arrayList, size, schemeData2.a)) {
                    arrayList.add(schemeData2);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new DrmInitData(str, (List) arrayList);
    }

    private static boolean a(ArrayList arrayList, int i, UUID uuid) {
        for (int i2 = 0; i2 < i; i2++) {
            if (((SchemeData) arrayList.get(i2)).a.equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public final DrmInitData a(String str) {
        return Util.a((Object) this.b, (Object) str) ? this : new DrmInitData(str, false, this.a);
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        SchemeData schemeData = (SchemeData) obj;
        SchemeData schemeData2 = (SchemeData) obj2;
        return C.a.equals(schemeData.a) ? C.a.equals(schemeData2.a) ? 0 : 1 : schemeData.a.compareTo(schemeData2.a);
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            DrmInitData drmInitData = (DrmInitData) obj;
            return Util.a((Object) this.b, (Object) drmInitData.b) && Arrays.equals(this.a, drmInitData.a);
        }
    }

    public final int hashCode() {
        if (this.d == 0) {
            String str = this.b;
            this.d = ((str == null ? 0 : str.hashCode()) * 31) + Arrays.hashCode(this.a);
        }
        return this.d;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.b);
        parcel.writeTypedArray(this.a, 0);
    }
}
