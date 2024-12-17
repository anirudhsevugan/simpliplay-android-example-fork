package com.google.android.exoplayer2.util;

import android.os.Trace;

public final class TraceUtil {
    public static void a() {
        if (Util.a >= 18) {
            Trace.endSection();
        }
    }

    public static void a(String str) {
        if (Util.a >= 18) {
            Trace.beginSection(str);
        }
    }
}
