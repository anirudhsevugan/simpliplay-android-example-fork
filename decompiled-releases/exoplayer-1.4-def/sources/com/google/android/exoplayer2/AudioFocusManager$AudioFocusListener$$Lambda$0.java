package com.google.android.exoplayer2;

import com.google.android.exoplayer2.AudioFocusManager;

final /* synthetic */ class AudioFocusManager$AudioFocusListener$$Lambda$0 implements Runnable {
    private final AudioFocusManager.AudioFocusListener a;
    private final int b;

    AudioFocusManager$AudioFocusListener$$Lambda$0(AudioFocusManager.AudioFocusListener audioFocusListener, int i) {
        this.a = audioFocusListener;
        this.b = i;
    }

    public final void run() {
        AudioFocusManager.AudioFocusListener audioFocusListener = this.a;
        AudioFocusManager.a(AudioFocusManager.this, this.b);
    }
}
