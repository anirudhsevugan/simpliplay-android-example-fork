package com.google.android.exoplayer2.audio;

import android.os.Handler;
import java.util.concurrent.Executor;

final /* synthetic */ class DefaultAudioSink$StreamEventCallbackV29$$Lambda$0 implements Executor {
    private final Handler a;

    private DefaultAudioSink$StreamEventCallbackV29$$Lambda$0(Handler handler) {
        this.a = handler;
    }

    static Executor a(Handler handler) {
        return new DefaultAudioSink$StreamEventCallbackV29$$Lambda$0(handler);
    }

    public final void execute(Runnable runnable) {
        this.a.post(runnable);
    }
}
