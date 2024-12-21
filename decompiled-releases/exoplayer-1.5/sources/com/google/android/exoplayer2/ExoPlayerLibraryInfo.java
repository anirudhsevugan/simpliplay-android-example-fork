package com.google.android.exoplayer2;

import android.os.Build;
import java.util.HashSet;

public final class ExoPlayerLibraryInfo {
    private static final HashSet a = new HashSet();
    private static String b = "goog.exo.core";

    static {
        String str = Build.VERSION.RELEASE;
        new StringBuilder(String.valueOf(str).length() + 57).append("ExoPlayerLib/2.14.1 (Linux; Android ").append(str).append(") ExoPlayerLib/2.14.1");
    }

    public static synchronized String a() {
        String str;
        synchronized (ExoPlayerLibraryInfo.class) {
            str = b;
        }
        return str;
    }

    public static synchronized void a(String str) {
        synchronized (ExoPlayerLibraryInfo.class) {
            if (a.add(str)) {
                String str2 = b;
                b = new StringBuilder(String.valueOf(str2).length() + 2 + String.valueOf(str).length()).append(str2).append(", ").append(str).toString();
            }
        }
    }
}
