package com.google.android.exoplayer2.util;

import android.text.TextUtils;
import java.net.UnknownHostException;

public final class Log {
    private static boolean a = true;

    private static String a(String str, Throwable th) {
        String a2 = a(th);
        if (TextUtils.isEmpty(a2)) {
            return str;
        }
        String valueOf = String.valueOf(str);
        String replace = a2.replace("\n", "\n  ");
        return new StringBuilder(String.valueOf(valueOf).length() + 4 + String.valueOf(replace).length()).append(valueOf).append("\n  ").append(replace).append(10).toString();
    }

    public static String a(Throwable th) {
        boolean z;
        if (th == null) {
            return null;
        }
        Throwable th2 = th;
        while (true) {
            if (th2 == null) {
                z = false;
                break;
            } else if (th2 instanceof UnknownHostException) {
                z = true;
                break;
            } else {
                th2 = th2.getCause();
            }
        }
        return z ? "UnknownHostException (no network)" : !a ? th.getMessage() : android.util.Log.getStackTraceString(th).trim().replace("\t", "    ");
    }

    public static void a(String str, String str2) {
        android.util.Log.d(str, str2);
    }

    public static void a(String str, String str2, Throwable th) {
        android.util.Log.w(str, a(str2, th));
    }

    public static void b(String str, String str2) {
        android.util.Log.i(str, str2);
    }

    public static void b(String str, String str2, Throwable th) {
        android.util.Log.e(str, a(str2, th));
    }

    public static void c(String str, String str2) {
        android.util.Log.w(str, str2);
    }

    public static void d(String str, String str2) {
        android.util.Log.e(str, str2);
    }
}
