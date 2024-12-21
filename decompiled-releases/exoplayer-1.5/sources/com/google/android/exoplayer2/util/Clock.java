package com.google.android.exoplayer2.util;

import android.os.Handler;
import android.os.Looper;

public interface Clock {
    public static final Clock a = new SystemClock();

    long a();

    HandlerWrapper a(Looper looper, Handler.Callback callback);

    long b();
}
