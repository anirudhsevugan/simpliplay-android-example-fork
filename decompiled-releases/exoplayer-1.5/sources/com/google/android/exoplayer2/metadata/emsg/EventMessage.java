package com.google.android.exoplayer2.metadata.emsg;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.Metadata$Entry$$CC;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public final class EventMessage implements Metadata.Entry {
    private static final Format f;
    private static final Format g;
    public final String a;
    public final String b;
    public final long c;
    public final long d;
    public final byte[] e;
    private int h;

    static {
        Format.Builder builder = new Format.Builder();
        builder.k = "application/id3";
        f = builder.a();
        Format.Builder builder2 = new Format.Builder();
        builder2.k = "application/x-scte35";
        g = builder2.a();
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new EventMessage(parcel);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new EventMessage[i];
            }
        };
    }

    EventMessage(Parcel parcel) {
        this.a = (String) Util.a((Object) parcel.readString());
        this.b = (String) Util.a((Object) parcel.readString());
        this.c = parcel.readLong();
        this.d = parcel.readLong();
        this.e = (byte[]) Util.a((Object) parcel.createByteArray());
    }

    public EventMessage(String str, String str2, long j, long j2, byte[] bArr) {
        this.a = str;
        this.b = str2;
        this.c = j;
        this.d = j2;
        this.e = bArr;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.exoplayer2.Format a() {
        /*
            r2 = this;
            java.lang.String r0 = r2.a
            int r1 = r0.hashCode()
            switch(r1) {
                case -1468477611: goto L_0x001e;
                case -795945609: goto L_0x0014;
                case 1303648457: goto L_0x000a;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x0028
        L_0x000a:
            java.lang.String r1 = "https://developer.apple.com/streaming/emsg-id3"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0028
            r0 = 1
            goto L_0x0029
        L_0x0014:
            java.lang.String r1 = "https://aomedia.org/emsg/ID3"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0028
            r0 = 0
            goto L_0x0029
        L_0x001e:
            java.lang.String r1 = "urn:scte:scte35:2014:bin"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0028
            r0 = 2
            goto L_0x0029
        L_0x0028:
            r0 = -1
        L_0x0029:
            switch(r0) {
                case 0: goto L_0x0031;
                case 1: goto L_0x0031;
                case 2: goto L_0x002e;
                default: goto L_0x002c;
            }
        L_0x002c:
            r0 = 0
            return r0
        L_0x002e:
            com.google.android.exoplayer2.Format r0 = g
            return r0
        L_0x0031:
            com.google.android.exoplayer2.Format r0 = f
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.metadata.emsg.EventMessage.a():com.google.android.exoplayer2.Format");
    }

    public final void a(MediaMetadata.Builder builder) {
        Metadata$Entry$$CC.c();
    }

    public final byte[] b() {
        if (a() != null) {
            return this.e;
        }
        return null;
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            EventMessage eventMessage = (EventMessage) obj;
            return this.c == eventMessage.c && this.d == eventMessage.d && Util.a((Object) this.a, (Object) eventMessage.a) && Util.a((Object) this.b, (Object) eventMessage.b) && Arrays.equals(this.e, eventMessage.e);
        }
    }

    public final int hashCode() {
        if (this.h == 0) {
            String str = this.a;
            int i = 0;
            int hashCode = ((str != null ? str.hashCode() : 0) + 527) * 31;
            String str2 = this.b;
            if (str2 != null) {
                i = str2.hashCode();
            }
            long j = this.c;
            long j2 = this.d;
            this.h = ((((((hashCode + i) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + Arrays.hashCode(this.e);
        }
        return this.h;
    }

    public final String toString() {
        String str = this.a;
        long j = this.d;
        long j2 = this.c;
        String str2 = this.b;
        return new StringBuilder(String.valueOf(str).length() + 79 + String.valueOf(str2).length()).append("EMSG: scheme=").append(str).append(", id=").append(j).append(", durationMs=").append(j2).append(", value=").append(str2).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeLong(this.c);
        parcel.writeLong(this.d);
        parcel.writeByteArray(this.e);
    }
}
