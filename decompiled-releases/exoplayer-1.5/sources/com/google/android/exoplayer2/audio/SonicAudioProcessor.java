package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.android.exoplayer2.util.Assertions;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

public final class SonicAudioProcessor implements AudioProcessor {
    float b = 1.0f;
    float c = 1.0f;
    AudioProcessor.AudioFormat d = AudioProcessor.AudioFormat.a;
    AudioProcessor.AudioFormat e = AudioProcessor.AudioFormat.a;
    boolean f;
    Sonic g;
    long h;
    long i;
    private int j;
    private AudioProcessor.AudioFormat k = AudioProcessor.AudioFormat.a;
    private AudioProcessor.AudioFormat l = AudioProcessor.AudioFormat.a;
    private ByteBuffer m;
    private ShortBuffer n;
    private ByteBuffer o;
    private boolean p;

    public SonicAudioProcessor() {
        ByteBuffer byteBuffer = a;
        this.m = byteBuffer;
        this.n = byteBuffer.asShortBuffer();
        this.o = a;
        this.j = -1;
    }

    public final AudioProcessor.AudioFormat a(AudioProcessor.AudioFormat audioFormat) {
        if (audioFormat.d == 2) {
            int i2 = this.j;
            if (i2 == -1) {
                i2 = audioFormat.b;
            }
            this.k = audioFormat;
            AudioProcessor.AudioFormat audioFormat2 = new AudioProcessor.AudioFormat(i2, audioFormat.c, 2);
            this.l = audioFormat2;
            this.f = true;
            return audioFormat2;
        }
        throw new AudioProcessor.UnhandledAudioFormatException(audioFormat);
    }

    public final void a(ByteBuffer byteBuffer) {
        if (byteBuffer.hasRemaining()) {
            Sonic sonic = (Sonic) Assertions.b((Object) this.g);
            ShortBuffer asShortBuffer = byteBuffer.asShortBuffer();
            int remaining = byteBuffer.remaining();
            this.h += (long) remaining;
            int remaining2 = asShortBuffer.remaining() / sonic.a;
            sonic.f = sonic.a(sonic.f, sonic.g, remaining2);
            asShortBuffer.get(sonic.f, sonic.g * sonic.a, ((sonic.a * remaining2) << 1) / 2);
            sonic.g += remaining2;
            sonic.b();
            byteBuffer.position(byteBuffer.position() + remaining);
        }
    }

    public final boolean a() {
        if (this.l.b != -1) {
            return Math.abs(this.b - 1.0f) >= 1.0E-4f || Math.abs(this.c - 1.0f) >= 1.0E-4f || this.l.b != this.k.b;
        }
        return false;
    }

    public final void b() {
        Sonic sonic = this.g;
        if (sonic != null) {
            int i2 = sonic.g;
            int i3 = sonic.i + ((int) ((((((float) i2) / (sonic.b / sonic.c)) + ((float) sonic.j)) / (sonic.d * sonic.c)) + 0.5f));
            sonic.f = sonic.a(sonic.f, sonic.g, (sonic.e * 2) + i2);
            for (int i4 = 0; i4 < sonic.e * 2 * sonic.a; i4++) {
                sonic.f[(sonic.a * i2) + i4] = 0;
            }
            sonic.g += sonic.e * 2;
            sonic.b();
            if (sonic.i > i3) {
                sonic.i = i3;
            }
            sonic.g = 0;
            sonic.m = 0;
            sonic.j = 0;
        }
        this.p = true;
    }

    public final ByteBuffer c() {
        int a;
        Sonic sonic = this.g;
        if (sonic != null && (a = sonic.a()) > 0) {
            if (this.m.capacity() < a) {
                ByteBuffer order = ByteBuffer.allocateDirect(a).order(ByteOrder.nativeOrder());
                this.m = order;
                this.n = order.asShortBuffer();
            } else {
                this.m.clear();
                this.n.clear();
            }
            ShortBuffer shortBuffer = this.n;
            int min = Math.min(shortBuffer.remaining() / sonic.a, sonic.i);
            shortBuffer.put(sonic.h, 0, sonic.a * min);
            sonic.i -= min;
            System.arraycopy(sonic.h, min * sonic.a, sonic.h, 0, sonic.i * sonic.a);
            this.i += (long) a;
            this.m.limit(a);
            this.o = this.m;
        }
        ByteBuffer byteBuffer = this.o;
        this.o = a;
        return byteBuffer;
    }

    public final boolean d() {
        if (!this.p) {
            return false;
        }
        Sonic sonic = this.g;
        return sonic == null || sonic.a() == 0;
    }

    public final void e() {
        if (a()) {
            AudioProcessor.AudioFormat audioFormat = this.k;
            this.d = audioFormat;
            this.e = this.l;
            if (this.f) {
                this.g = new Sonic(audioFormat.b, this.d.c, this.b, this.c, this.e.b);
            } else {
                Sonic sonic = this.g;
                if (sonic != null) {
                    sonic.g = 0;
                    sonic.i = 0;
                    sonic.j = 0;
                    sonic.k = 0;
                    sonic.l = 0;
                    sonic.m = 0;
                    sonic.n = 0;
                    sonic.o = 0;
                    sonic.p = 0;
                    sonic.q = 0;
                }
            }
        }
        this.o = a;
        this.h = 0;
        this.i = 0;
        this.p = false;
    }

    public final void f() {
        this.b = 1.0f;
        this.c = 1.0f;
        this.k = AudioProcessor.AudioFormat.a;
        this.l = AudioProcessor.AudioFormat.a;
        this.d = AudioProcessor.AudioFormat.a;
        this.e = AudioProcessor.AudioFormat.a;
        ByteBuffer byteBuffer = a;
        this.m = byteBuffer;
        this.n = byteBuffer.asShortBuffer();
        this.o = a;
        this.j = -1;
        this.f = false;
        this.g = null;
        this.h = 0;
        this.i = 0;
        this.p = false;
    }
}
