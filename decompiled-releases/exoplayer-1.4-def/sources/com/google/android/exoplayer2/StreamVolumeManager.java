package com.google.android.exoplayer2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Handler;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;

public final class StreamVolumeManager {
    final Listener a;
    public final AudioManager b;
    public int c = 3;
    public int d;
    public boolean e;
    private final Context f;
    /* access modifiers changed from: private */
    public final Handler g;
    private VolumeChangeReceiver h;

    public interface Listener {
        void a(int i, boolean z);

        void f();
    }

    final class VolumeChangeReceiver extends BroadcastReceiver {
        private VolumeChangeReceiver() {
        }

        /* synthetic */ VolumeChangeReceiver(StreamVolumeManager streamVolumeManager, byte b) {
            this();
        }

        public final void onReceive(Context context, Intent intent) {
            StreamVolumeManager.this.g.post(new StreamVolumeManager$VolumeChangeReceiver$$Lambda$0(StreamVolumeManager.this));
        }
    }

    public StreamVolumeManager(Context context, Handler handler, Listener listener) {
        Context applicationContext = context.getApplicationContext();
        this.f = applicationContext;
        this.g = handler;
        this.a = listener;
        AudioManager audioManager = (AudioManager) Assertions.a((Object) (AudioManager) applicationContext.getSystemService("audio"));
        this.b = audioManager;
        this.d = a(audioManager, 3);
        this.e = b(audioManager, this.c);
        VolumeChangeReceiver volumeChangeReceiver = new VolumeChangeReceiver(this, (byte) 0);
        try {
            applicationContext.registerReceiver(volumeChangeReceiver, new IntentFilter("android.media.VOLUME_CHANGED_ACTION"));
            this.h = volumeChangeReceiver;
        } catch (RuntimeException e2) {
            Log.a("StreamVolumeManager", "Error registering stream volume receiver", e2);
        }
    }

    private static int a(AudioManager audioManager, int i) {
        try {
            return audioManager.getStreamVolume(i);
        } catch (RuntimeException e2) {
            Log.a("StreamVolumeManager", new StringBuilder(60).append("Could not retrieve stream volume for stream type ").append(i).toString(), e2);
            return audioManager.getStreamMaxVolume(i);
        }
    }

    private static boolean b(AudioManager audioManager, int i) {
        return Util.a >= 23 ? audioManager.isStreamMute(i) : a(audioManager, i) == 0;
    }

    public final int a() {
        if (Util.a >= 28) {
            return this.b.getStreamMinVolume(this.c);
        }
        return 0;
    }

    public final int b() {
        return this.b.getStreamMaxVolume(this.c);
    }

    public final void c() {
        VolumeChangeReceiver volumeChangeReceiver = this.h;
        if (volumeChangeReceiver != null) {
            try {
                this.f.unregisterReceiver(volumeChangeReceiver);
            } catch (RuntimeException e2) {
                Log.a("StreamVolumeManager", "Error unregistering stream volume receiver", e2);
            }
            this.h = null;
        }
    }

    public final void d() {
        int a2 = a(this.b, this.c);
        boolean b2 = b(this.b, this.c);
        if (this.d != a2 || this.e != b2) {
            this.d = a2;
            this.e = b2;
            this.a.a(a2, b2);
        }
    }
}
