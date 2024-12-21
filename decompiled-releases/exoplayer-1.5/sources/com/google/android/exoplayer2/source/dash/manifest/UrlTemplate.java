package com.google.android.exoplayer2.source.dash.manifest;

import java.util.Locale;

public final class UrlTemplate {
    private final String[] a;
    private final int[] b;
    private final String[] c;
    private final int d;

    private UrlTemplate(String[] strArr, int[] iArr, String[] strArr2, int i) {
        this.a = strArr;
        this.b = iArr;
        this.c = strArr2;
        this.d = i;
    }

    public static UrlTemplate a(String str) {
        String str2;
        String[] strArr = new String[5];
        int[] iArr = new int[4];
        String[] strArr2 = new String[4];
        strArr[0] = "";
        int i = 0;
        int i2 = 0;
        while (i < str.length()) {
            int indexOf = str.indexOf("$", i);
            char c2 = 65535;
            if (indexOf == -1) {
                String valueOf = String.valueOf(strArr[i2]);
                String valueOf2 = String.valueOf(str.substring(i));
                strArr[i2] = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                i = str.length();
            } else if (indexOf != i) {
                String valueOf3 = String.valueOf(strArr[i2]);
                String valueOf4 = String.valueOf(str.substring(i, indexOf));
                strArr[i2] = valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3);
                i = indexOf;
            } else if (str.startsWith("$$", i)) {
                strArr[i2] = String.valueOf(strArr[i2]).concat("$");
                i += 2;
            } else {
                int i3 = i + 1;
                int indexOf2 = str.indexOf("$", i3);
                String substring = str.substring(i3, indexOf2);
                if (substring.equals("RepresentationID")) {
                    iArr[i2] = 1;
                } else {
                    int indexOf3 = substring.indexOf("%0");
                    if (indexOf3 != -1) {
                        str2 = substring.substring(indexOf3);
                        if (!str2.endsWith("d") && !str2.endsWith("x")) {
                            str2 = String.valueOf(str2).concat("d");
                        }
                        substring = substring.substring(0, indexOf3);
                    } else {
                        str2 = "%01d";
                    }
                    switch (substring.hashCode()) {
                        case -1950496919:
                            if (substring.equals("Number")) {
                                c2 = 0;
                                break;
                            }
                            break;
                        case 2606829:
                            if (substring.equals("Time")) {
                                c2 = 2;
                                break;
                            }
                            break;
                        case 38199441:
                            if (substring.equals("Bandwidth")) {
                                c2 = 1;
                                break;
                            }
                            break;
                    }
                    switch (c2) {
                        case 0:
                            iArr[i2] = 2;
                            break;
                        case 1:
                            iArr[i2] = 3;
                            break;
                        case 2:
                            iArr[i2] = 4;
                            break;
                        default:
                            String valueOf5 = String.valueOf(str);
                            throw new IllegalArgumentException(valueOf5.length() != 0 ? "Invalid template: ".concat(valueOf5) : new String("Invalid template: "));
                    }
                    strArr2[i2] = str2;
                }
                i2++;
                strArr[i2] = "";
                i = indexOf2 + 1;
            }
        }
        return new UrlTemplate(strArr, iArr, strArr2, i2);
    }

    public final String a(String str, long j, int i, long j2) {
        String format;
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        while (true) {
            int i3 = this.d;
            if (i2 < i3) {
                sb.append(this.a[i2]);
                int i4 = this.b[i2];
                if (i4 == 1) {
                    sb.append(str);
                } else {
                    if (i4 == 2) {
                        format = String.format(Locale.US, this.c[i2], new Object[]{Long.valueOf(j)});
                    } else if (i4 == 3) {
                        format = String.format(Locale.US, this.c[i2], new Object[]{Integer.valueOf(i)});
                    } else if (i4 == 4) {
                        format = String.format(Locale.US, this.c[i2], new Object[]{Long.valueOf(j2)});
                    }
                    sb.append(format);
                }
                i2++;
            } else {
                sb.append(this.a[i3]);
                return sb.toString();
            }
        }
    }
}
