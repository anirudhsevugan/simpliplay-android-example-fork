package com.google.android.exoplayer2.util;

import java.util.concurrent.ThreadFactory;

final /* synthetic */ class Util$$Lambda$0 implements ThreadFactory {
    private final String a;

    Util$$Lambda$0(String str) {
        this.a = str;
    }

    public final Thread newThread(Runnable runnable) {
        return Util.a(this.a, runnable);
    }
}
