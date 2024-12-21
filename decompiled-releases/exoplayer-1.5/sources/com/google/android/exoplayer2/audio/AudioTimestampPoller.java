package com.google.android.exoplayer2.audio;

import android.media.AudioTimestamp;
import android.media.AudioTrack;
import com.google.android.exoplayer2.util.Util;

final class AudioTimestampPoller {
    final AudioTimestampV19 a;
    int b;
    long c;
    long d;
    long e;
    long f;

    final class AudioTimestampV19 {
        final AudioTrack a;
        final AudioTimestamp b = new AudioTimestamp();
        long c;
        long d;
        long e;

        public AudioTimestampV19(AudioTrack audioTrack) {
            this.a = audioTrack;
        }

        public final long a() {
            return this.b.nanoTime / 1000;
        }
    }

    public AudioTimestampPoller(AudioTrack audioTrack) {
        if (Util.a >= 19) {
            this.a = new AudioTimestampV19(audioTrack);
            a();
            return;
        }
        this.a = null;
        a(3);
    }

    public final void a() {
        if (this.a != null) {
            a(0);
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(int i) {
        this.b = i;
        long j = 10000;
        switch (i) {
            case 0:
                this.e = 0;
                this.f = -1;
                this.c = System.nanoTime() / 1000;
                break;
            case 1:
                this.d = 10000;
                return;
            case 2:
            case 3:
                j = 10000000;
                break;
            case 4:
                j = 500000;
                break;
            default:
                throw new IllegalStateException();
        }
        this.d = j;
    }

    public final long b() {
        AudioTimestampV19 audioTimestampV19 = this.a;
        if (audioTimestampV19 != null) {
            return audioTimestampV19.a();
        }
        return -9223372036854775807L;
    }

    public final long c() {
        AudioTimestampV19 audioTimestampV19 = this.a;
        if (audioTimestampV19 != null) {
            return audioTimestampV19.e;
        }
        return -1;
    }
}
