package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.util.Util;

public final class TextInformationFrame extends Id3Frame {
    public final String a;
    private String b;

    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new TextInformationFrame(parcel);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new TextInformationFrame[i];
            }
        };
    }

    TextInformationFrame(Parcel parcel) {
        super((String) Util.a((Object) parcel.readString()));
        this.b = parcel.readString();
        this.a = (String) Util.a((Object) parcel.readString());
    }

    public TextInformationFrame(String str, String str2, String str3) {
        super(str);
        this.b = str2;
        this.a = str3;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.google.android.exoplayer2.MediaMetadata.Builder r5) {
        /*
            r4 = this;
            java.lang.String r0 = r4.c
            int r1 = r0.hashCode()
            r2 = 0
            r3 = 1
            switch(r1) {
                case 82815: goto L_0x0081;
                case 83253: goto L_0x0077;
                case 83254: goto L_0x006d;
                case 83341: goto L_0x0062;
                case 83378: goto L_0x0058;
                case 83552: goto L_0x004d;
                case 2567331: goto L_0x0043;
                case 2575251: goto L_0x0039;
                case 2581512: goto L_0x002f;
                case 2581513: goto L_0x0025;
                case 2583398: goto L_0x0019;
                case 2590194: goto L_0x000d;
                default: goto L_0x000b;
            }
        L_0x000b:
            goto L_0x008b
        L_0x000d:
            java.lang.String r1 = "TYER"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x008b
            r0 = 11
            goto L_0x008c
        L_0x0019:
            java.lang.String r1 = "TRCK"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x008b
            r0 = 9
            goto L_0x008c
        L_0x0025:
            java.lang.String r1 = "TPE2"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x008b
            r0 = 5
            goto L_0x008c
        L_0x002f:
            java.lang.String r1 = "TPE1"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x008b
            r0 = 3
            goto L_0x008c
        L_0x0039:
            java.lang.String r1 = "TIT2"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x008b
            r0 = 1
            goto L_0x008c
        L_0x0043:
            java.lang.String r1 = "TALB"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x008b
            r0 = 7
            goto L_0x008c
        L_0x004d:
            java.lang.String r1 = "TYE"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x008b
            r0 = 10
            goto L_0x008c
        L_0x0058:
            java.lang.String r1 = "TT2"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x008b
            r0 = 0
            goto L_0x008c
        L_0x0062:
            java.lang.String r1 = "TRK"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x008b
            r0 = 8
            goto L_0x008c
        L_0x006d:
            java.lang.String r1 = "TP2"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x008b
            r0 = 4
            goto L_0x008c
        L_0x0077:
            java.lang.String r1 = "TP1"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x008b
            r0 = 2
            goto L_0x008c
        L_0x0081:
            java.lang.String r1 = "TAL"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x008b
            r0 = 6
            goto L_0x008c
        L_0x008b:
            r0 = -1
        L_0x008c:
            switch(r0) {
                case 0: goto L_0x00d6;
                case 1: goto L_0x00d6;
                case 2: goto L_0x00d1;
                case 3: goto L_0x00d1;
                case 4: goto L_0x00cc;
                case 5: goto L_0x00cc;
                case 6: goto L_0x00c7;
                case 7: goto L_0x00c7;
                case 8: goto L_0x009f;
                case 9: goto L_0x009f;
                case 10: goto L_0x0090;
                case 11: goto L_0x0090;
                default: goto L_0x008f;
            }
        L_0x008f:
            goto L_0x00da
        L_0x0090:
            java.lang.String r0 = r4.a     // Catch:{ NumberFormatException -> 0x009d }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x009d }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ NumberFormatException -> 0x009d }
            r5.g = r0     // Catch:{ NumberFormatException -> 0x009d }
            return
        L_0x009d:
            r5 = move-exception
            goto L_0x00da
        L_0x009f:
            java.lang.String r0 = r4.a
            java.lang.String r1 = "/"
            java.lang.String[] r0 = com.google.android.exoplayer2.util.Util.a((java.lang.String) r0, (java.lang.String) r1)
            r1 = r0[r2]     // Catch:{ NumberFormatException -> 0x00c5 }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ NumberFormatException -> 0x00c5 }
            int r2 = r0.length     // Catch:{ NumberFormatException -> 0x00c5 }
            if (r2 <= r3) goto L_0x00bb
            r0 = r0[r3]     // Catch:{ NumberFormatException -> 0x00c5 }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x00c5 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ NumberFormatException -> 0x00c5 }
            goto L_0x00bc
        L_0x00bb:
            r0 = 0
        L_0x00bc:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ NumberFormatException -> 0x00c5 }
            r5.e = r1     // Catch:{ NumberFormatException -> 0x00c5 }
            r5.f = r0     // Catch:{ NumberFormatException -> 0x00c5 }
            return
        L_0x00c5:
            r5 = move-exception
            return
        L_0x00c7:
            java.lang.String r0 = r4.a
            r5.c = r0
            return
        L_0x00cc:
            java.lang.String r0 = r4.a
            r5.d = r0
            return
        L_0x00d1:
            java.lang.String r0 = r4.a
            r5.b = r0
            return
        L_0x00d6:
            java.lang.String r0 = r4.a
            r5.a = r0
        L_0x00da:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.metadata.id3.TextInformationFrame.a(com.google.android.exoplayer2.MediaMetadata$Builder):void");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            TextInformationFrame textInformationFrame = (TextInformationFrame) obj;
            return Util.a((Object) this.c, (Object) textInformationFrame.c) && Util.a((Object) this.b, (Object) textInformationFrame.b) && Util.a((Object) this.a, (Object) textInformationFrame.a);
        }
    }

    public final int hashCode() {
        int hashCode = (this.c.hashCode() + 527) * 31;
        String str = this.b;
        int i = 0;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.a;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode2 + i;
    }

    public final String toString() {
        String str = this.c;
        String str2 = this.b;
        String str3 = this.a;
        return new StringBuilder(String.valueOf(str).length() + 22 + String.valueOf(str2).length() + String.valueOf(str3).length()).append(str).append(": description=").append(str2).append(": value=").append(str3).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.c);
        parcel.writeString(this.b);
        parcel.writeString(this.a);
    }
}
