package com.google.android.exoplayer2.util;

import android.os.Handler;
import android.os.Looper;

public class SystemClock implements Clock {
    protected SystemClock() {
    }

    public final long a() {
        return android.os.SystemClock.elapsedRealtime();
    }

    public final HandlerWrapper a(Looper looper, Handler.Callback callback) {
        return new SystemHandlerWrapper(new Handler(looper, callback));
    }

    public final long b() {
        return android.os.SystemClock.uptimeMillis();
    }
}
