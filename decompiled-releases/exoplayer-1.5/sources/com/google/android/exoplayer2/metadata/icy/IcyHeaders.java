package com.google.android.exoplayer2.metadata.icy;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.Metadata$Entry$$CC;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

public final class IcyHeaders implements Metadata.Entry {
    public final int a;
    public final int b;
    private String c;
    private String d;
    private String e;
    private boolean f;

    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new IcyHeaders(parcel);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new IcyHeaders[i];
            }
        };
    }

    private IcyHeaders(int i, String str, String str2, String str3, boolean z, int i2) {
        Assertions.a(i2 == -1 || i2 > 0);
        this.a = i;
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = z;
        this.b = i2;
    }

    IcyHeaders(Parcel parcel) {
        this.a = parcel.readInt();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = Util.a(parcel);
        this.b = parcel.readInt();
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0108  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x010f A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.exoplayer2.metadata.icy.IcyHeaders a(java.util.Map r14) {
        /*
            java.lang.String r0 = "Invalid metadata interval: "
            java.lang.String r1 = "icy-br"
            java.lang.Object r1 = r14.get(r1)
            java.util.List r1 = (java.util.List) r1
            java.lang.String r2 = "IcyHeaders"
            r3 = 1
            r4 = 0
            r5 = -1
            if (r1 == 0) goto L_0x005d
            java.lang.Object r1 = r1.get(r4)
            java.lang.String r1 = (java.lang.String) r1
            int r6 = java.lang.Integer.parseInt(r1)     // Catch:{ NumberFormatException -> 0x003f }
            int r6 = r6 * 1000
            if (r6 <= 0) goto L_0x0022
            r8 = r6
            r1 = 1
            goto L_0x005f
        L_0x0022:
            java.lang.String r7 = "Invalid bitrate: "
            java.lang.String r8 = java.lang.String.valueOf(r1)     // Catch:{ NumberFormatException -> 0x003d }
            int r9 = r8.length()     // Catch:{ NumberFormatException -> 0x003d }
            if (r9 == 0) goto L_0x0033
            java.lang.String r7 = r7.concat(r8)     // Catch:{ NumberFormatException -> 0x003d }
            goto L_0x0039
        L_0x0033:
            java.lang.String r8 = new java.lang.String     // Catch:{ NumberFormatException -> 0x003d }
            r8.<init>(r7)     // Catch:{ NumberFormatException -> 0x003d }
            r7 = r8
        L_0x0039:
            com.google.android.exoplayer2.util.Log.c(r2, r7)     // Catch:{ NumberFormatException -> 0x003d }
            goto L_0x005d
        L_0x003d:
            r7 = move-exception
            goto L_0x0041
        L_0x003f:
            r6 = move-exception
            r6 = -1
        L_0x0041:
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r7 = r1.length()
            java.lang.String r8 = "Invalid bitrate header: "
            if (r7 == 0) goto L_0x0052
            java.lang.String r1 = r8.concat(r1)
            goto L_0x0057
        L_0x0052:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r8)
        L_0x0057:
            com.google.android.exoplayer2.util.Log.c(r2, r1)
            r8 = r6
            r1 = 0
            goto L_0x005f
        L_0x005d:
            r1 = 0
            r8 = -1
        L_0x005f:
            java.lang.String r6 = "icy-genre"
            java.lang.Object r6 = r14.get(r6)
            java.util.List r6 = (java.util.List) r6
            r7 = 0
            if (r6 == 0) goto L_0x0073
            java.lang.Object r1 = r6.get(r4)
            java.lang.String r1 = (java.lang.String) r1
            r9 = r1
            r1 = 1
            goto L_0x0074
        L_0x0073:
            r9 = r7
        L_0x0074:
            java.lang.String r6 = "icy-name"
            java.lang.Object r6 = r14.get(r6)
            java.util.List r6 = (java.util.List) r6
            if (r6 == 0) goto L_0x0087
            java.lang.Object r1 = r6.get(r4)
            java.lang.String r1 = (java.lang.String) r1
            r10 = r1
            r1 = 1
            goto L_0x0088
        L_0x0087:
            r10 = r7
        L_0x0088:
            java.lang.String r6 = "icy-url"
            java.lang.Object r6 = r14.get(r6)
            java.util.List r6 = (java.util.List) r6
            if (r6 == 0) goto L_0x009b
            java.lang.Object r1 = r6.get(r4)
            java.lang.String r1 = (java.lang.String) r1
            r11 = r1
            r1 = 1
            goto L_0x009c
        L_0x009b:
            r11 = r7
        L_0x009c:
            java.lang.String r6 = "icy-pub"
            java.lang.Object r6 = r14.get(r6)
            java.util.List r6 = (java.util.List) r6
            if (r6 == 0) goto L_0x00b5
            java.lang.Object r1 = r6.get(r4)
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r6 = "1"
            boolean r1 = r1.equals(r6)
            r12 = r1
            r1 = 1
            goto L_0x00b6
        L_0x00b5:
            r12 = 0
        L_0x00b6:
            java.lang.String r6 = "icy-metaint"
            java.lang.Object r14 = r14.get(r6)
            java.util.List r14 = (java.util.List) r14
            if (r14 == 0) goto L_0x0104
            java.lang.Object r14 = r14.get(r4)
            java.lang.String r14 = (java.lang.String) r14
            int r4 = java.lang.Integer.parseInt(r14)     // Catch:{ NumberFormatException -> 0x00e9 }
            if (r4 <= 0) goto L_0x00ce
            r13 = r4
            goto L_0x0106
        L_0x00ce:
            java.lang.String r3 = java.lang.String.valueOf(r14)     // Catch:{ NumberFormatException -> 0x00e6 }
            int r6 = r3.length()     // Catch:{ NumberFormatException -> 0x00e6 }
            if (r6 == 0) goto L_0x00dd
            java.lang.String r3 = r0.concat(r3)     // Catch:{ NumberFormatException -> 0x00e6 }
            goto L_0x00e2
        L_0x00dd:
            java.lang.String r3 = new java.lang.String     // Catch:{ NumberFormatException -> 0x00e6 }
            r3.<init>(r0)     // Catch:{ NumberFormatException -> 0x00e6 }
        L_0x00e2:
            com.google.android.exoplayer2.util.Log.c(r2, r3)     // Catch:{ NumberFormatException -> 0x00e6 }
            goto L_0x0104
        L_0x00e6:
            r3 = move-exception
            r5 = r4
            goto L_0x00ea
        L_0x00e9:
            r3 = move-exception
        L_0x00ea:
            java.lang.String r14 = java.lang.String.valueOf(r14)
            int r3 = r14.length()
            if (r3 == 0) goto L_0x00f9
            java.lang.String r14 = r0.concat(r14)
            goto L_0x00fe
        L_0x00f9:
            java.lang.String r14 = new java.lang.String
            r14.<init>(r0)
        L_0x00fe:
            com.google.android.exoplayer2.util.Log.c(r2, r14)
            r3 = r1
            r13 = r5
            goto L_0x0106
        L_0x0104:
            r3 = r1
            r13 = -1
        L_0x0106:
            if (r3 == 0) goto L_0x010f
            com.google.android.exoplayer2.metadata.icy.IcyHeaders r14 = new com.google.android.exoplayer2.metadata.icy.IcyHeaders
            r7 = r14
            r7.<init>(r8, r9, r10, r11, r12, r13)
            return r14
        L_0x010f:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.metadata.icy.IcyHeaders.a(java.util.Map):com.google.android.exoplayer2.metadata.icy.IcyHeaders");
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
            IcyHeaders icyHeaders = (IcyHeaders) obj;
            return this.a == icyHeaders.a && Util.a((Object) this.c, (Object) icyHeaders.c) && Util.a((Object) this.d, (Object) icyHeaders.d) && Util.a((Object) this.e, (Object) icyHeaders.e) && this.f == icyHeaders.f && this.b == icyHeaders.b;
        }
    }

    public final int hashCode() {
        int i = (this.a + 527) * 31;
        String str = this.c;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.d;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.e;
        if (str3 != null) {
            i2 = str3.hashCode();
        }
        return ((((hashCode2 + i2) * 31) + (this.f ? 1 : 0)) * 31) + this.b;
    }

    public final String toString() {
        String str = this.d;
        String str2 = this.c;
        int i = this.a;
        return new StringBuilder(String.valueOf(str).length() + 80 + String.valueOf(str2).length()).append("IcyHeaders: name=\"").append(str).append("\", genre=\"").append(str2).append("\", bitrate=").append(i).append(", metadataInterval=").append(this.b).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        Util.a(parcel, this.f);
        parcel.writeInt(this.b);
    }
}
