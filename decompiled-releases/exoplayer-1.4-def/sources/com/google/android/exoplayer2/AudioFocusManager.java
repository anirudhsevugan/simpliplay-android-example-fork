package com.google.android.exoplayer2;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;

final class AudioFocusManager {
    float a = 1.0f;
    private final AudioManager b;
    private final AudioFocusListener c;
    private PlayerControl d;
    private int e;

    class AudioFocusListener implements AudioManager.OnAudioFocusChangeListener {
        private final Handler b;

        public AudioFocusListener(Handler handler) {
            this.b = handler;
        }

        public void onAudioFocusChange(int i) {
            this.b.post(new AudioFocusManager$AudioFocusListener$$Lambda$0(this, i));
        }
    }

    public interface PlayerControl {
        void a(int i);

        void b();
    }

    public AudioFocusManager(Context context, Handler handler, PlayerControl playerControl) {
        this.b = (AudioManager) Assertions.b((Object) (AudioManager) context.getApplicationContext().getSystemService("audio"));
        this.d = playerControl;
        this.c = new AudioFocusListener(handler);
        this.e = 0;
    }

    private void a(int i) {
        if (this.e != i) {
            this.e = i;
            float f = i == 3 ? 0.2f : 1.0f;
            if (this.a != f) {
                this.a = f;
                PlayerControl playerControl = this.d;
                if (playerControl != null) {
                    playerControl.b();
                }
            }
        }
    }

    static /* synthetic */ void a(AudioFocusManager audioFocusManager, int i) {
        switch (i) {
            case -3:
            case -2:
                if (i == -2) {
                    audioFocusManager.b(0);
                    audioFocusManager.a(2);
                    return;
                }
                audioFocusManager.a(3);
                return;
            case -1:
                audioFocusManager.b(-1);
                audioFocusManager.b();
                return;
            case 1:
                audioFocusManager.a(1);
                audioFocusManager.b(1);
                return;
            default:
                Log.c("AudioFocusManager", new StringBuilder(38).append("Unknown focus change type: ").append(i).toString());
                return;
        }
    }

    private void b() {
        if (this.e != 0) {
            if (Util.a < 26) {
                c();
            }
            a(0);
        }
    }

    private void b(int i) {
        PlayerControl playerControl = this.d;
        if (playerControl != null) {
            playerControl.a(i);
        }
    }

    private void c() {
        this.b.abandonAudioFocus(this.c);
    }

    public final int a(boolean z) {
        b();
        return z ? 1 : -1;
    }

    public final void a() {
        this.d = null;
        b();
    }
}
