package com.google.android.exoplayer2.util;

import android.net.Uri;
import android.text.TextUtils;

public final class UriUtil {
    public static Uri a(String str, String str2) {
        return Uri.parse(b(str, str2));
    }

    private static String a(StringBuilder sb, int i, int i2) {
        int i3;
        int i4;
        if (i >= i2) {
            return sb.toString();
        }
        if (sb.charAt(i) == '/') {
            i++;
        }
        int i5 = i;
        int i6 = i5;
        while (i5 <= i2) {
            if (i5 == i2) {
                i3 = i5;
            } else if (sb.charAt(i5) == '/') {
                i3 = i5 + 1;
            } else {
                i5++;
            }
            int i7 = i6 + 1;
            if (i5 == i7 && sb.charAt(i6) == '.') {
                sb.delete(i6, i3);
                i4 = i3 - i6;
            } else if (i5 == i6 + 2 && sb.charAt(i6) == '.' && sb.charAt(i7) == '.') {
                i6 = sb.lastIndexOf("/", i6 - 2) + 1;
                int i8 = i6 > i ? i6 : i;
                sb.delete(i8, i3);
                i4 = i3 - i8;
            } else {
                i6 = i5 + 1;
                i5 = i6;
            }
            i2 -= i4;
            i5 = i6;
        }
        return sb.toString();
    }

    private static int[] a(String str) {
        int i;
        int[] iArr = new int[4];
        if (TextUtils.isEmpty(str)) {
            iArr[0] = -1;
            return iArr;
        }
        int length = str.length();
        int indexOf = str.indexOf(35);
        if (indexOf != -1) {
            length = indexOf;
        }
        int indexOf2 = str.indexOf(63);
        if (indexOf2 == -1 || indexOf2 > length) {
            indexOf2 = length;
        }
        int indexOf3 = str.indexOf(47);
        if (indexOf3 == -1 || indexOf3 > indexOf2) {
            indexOf3 = indexOf2;
        }
        int indexOf4 = str.indexOf(58);
        if (indexOf4 > indexOf3) {
            indexOf4 = -1;
        }
        int i2 = indexOf4 + 2;
        if (i2 < indexOf2 && str.charAt(indexOf4 + 1) == '/' && str.charAt(i2) == '/') {
            i = str.indexOf(47, indexOf4 + 3);
            if (i == -1 || i > indexOf2) {
                i = indexOf2;
            }
        } else {
            i = indexOf4 + 1;
        }
        iArr[0] = indexOf4;
        iArr[1] = i;
        iArr[2] = indexOf2;
        iArr[3] = length;
        return iArr;
    }

    public static String b(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        int[] a = a(str2);
        if (a[0] != -1) {
            sb.append(str2);
            a(sb, a[1], a[2]);
            return sb.toString();
        }
        int[] a2 = a(str);
        if (a[3] == 0) {
            return sb.append(str, 0, a2[3]).append(str2).toString();
        }
        if (a[2] == 0) {
            return sb.append(str, 0, a2[2]).append(str2).toString();
        }
        int i = a[1];
        if (i != 0) {
            int i2 = a2[0] + 1;
            sb.append(str, 0, i2).append(str2);
            return a(sb, a[1] + i2, i2 + a[2]);
        } else if (str2.charAt(i) == '/') {
            sb.append(str, 0, a2[1]).append(str2);
            int i3 = a2[1];
            return a(sb, i3, a[2] + i3);
        } else {
            int i4 = a2[0] + 2;
            int i5 = a2[1];
            if (i4 >= i5 || i5 != a2[2]) {
                int lastIndexOf = str.lastIndexOf(47, a2[2] - 1);
                int i6 = lastIndexOf == -1 ? a2[1] : lastIndexOf + 1;
                sb.append(str, 0, i6).append(str2);
                return a(sb, a2[1], i6 + a[2]);
            }
            sb.append(str, 0, i5).append('/').append(str2);
            int i7 = a2[1];
            return a(sb, i7, a[2] + i7 + 1);
        }
    }
}
