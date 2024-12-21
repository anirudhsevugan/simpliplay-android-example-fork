package com.google.android.exoplayer2.text.webvtt;

import android.text.TextUtils;
import gnu.expr.Declaration;
import java.util.Collections;
import java.util.Set;

public final class WebvttCssStyle {
    String a = "";
    String b = "";
    Set c = Collections.emptySet();
    String d = "";
    String e = null;
    int f;
    boolean g = false;
    int h;
    boolean i = false;
    int j = -1;
    int k = -1;
    int l = -1;
    int m = -1;
    int n = -1;
    int o = -1;
    boolean p = false;

    private static int a(int i2, String str, String str2, int i3) {
        if (str.isEmpty() || i2 == -1) {
            return i2;
        }
        if (str.equals(str2)) {
            return i2 + i3;
        }
        return -1;
    }

    public final int a() {
        int i2 = this.l;
        if (i2 == -1 && this.m == -1) {
            return -1;
        }
        int i3 = 0;
        int i4 = i2 == 1 ? 1 : 0;
        if (this.m == 1) {
            i3 = 2;
        }
        return i4 | i3;
    }

    public final int a(String str, String str2, Set set, String str3) {
        if (this.a.isEmpty() && this.b.isEmpty() && this.c.isEmpty() && this.d.isEmpty()) {
            return TextUtils.isEmpty(str2) ? 1 : 0;
        }
        int a2 = a(a(a(0, this.a, str, (int) Declaration.MODULE_REFERENCE), this.b, str2, 2), this.d, str3, 4);
        if (a2 == -1 || !set.containsAll(this.c)) {
            return 0;
        }
        return a2 + (this.c.size() << 2);
    }
}
