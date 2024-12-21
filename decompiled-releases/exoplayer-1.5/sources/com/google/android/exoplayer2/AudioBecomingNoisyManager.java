package com.google.android.exoplayer2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;

final class AudioBecomingNoisyManager {
    private final Context a;
    private final AudioBecomingNoisyReceiver b;
    /* access modifiers changed from: private */
    public boolean c;

    final class AudioBecomingNoisyReceiver extends BroadcastReceiver implements Runnable {
        private final EventListener a;
        private final Handler b;

        public AudioBecomingNoisyReceiver(Handler handler, EventListener eventListener) {
            this.b = handler;
            this.a = eventListener;
        }

        public final void onReceive(Context context, Intent intent) {
            if ("android.media.AUDIO_BECOMING_NOISY".equals(intent.getAction())) {
                this.b.post(this);
            }
        }

        public final void run() {
            if (AudioBecomingNoisyManager.this.c) {
                this.a.a();
            }
        }
    }

    public interface EventListener {
        void a();
    }

    public AudioBecomingNoisyManager(Context context, Handler handler, EventListener eventListener) {
        this.a = context.getApplicationContext();
        this.b = new AudioBecomingNoisyReceiver(handler, eventListener);
    }

    public final void a(boolean z) {
        if (z && !this.c) {
            this.a.registerReceiver(this.b, new IntentFilter("android.media.AUDIO_BECOMING_NOISY"));
            this.c = true;
        } else if (!z && this.c) {
            this.a.unregisterReceiver(this.b);
            this.c = false;
        }
    }
}
