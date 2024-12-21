package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioProcessor;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public abstract class BaseAudioProcessor implements AudioProcessor {
    protected AudioProcessor.AudioFormat b = AudioProcessor.AudioFormat.a;
    protected AudioProcessor.AudioFormat c = AudioProcessor.AudioFormat.a;
    private AudioProcessor.AudioFormat d = AudioProcessor.AudioFormat.a;
    private AudioProcessor.AudioFormat e = AudioProcessor.AudioFormat.a;
    private ByteBuffer f = a;
    private ByteBuffer g = a;
    private boolean h;

    public final AudioProcessor.AudioFormat a(AudioProcessor.AudioFormat audioFormat) {
        this.d = audioFormat;
        this.e = b(audioFormat);
        return a() ? this.e : AudioProcessor.AudioFormat.a;
    }

    /* access modifiers changed from: protected */
    public final ByteBuffer a(int i) {
        if (this.f.capacity() < i) {
            this.f = ByteBuffer.allocateDirect(i).order(ByteOrder.nativeOrder());
        } else {
            this.f.clear();
        }
        ByteBuffer byteBuffer = this.f;
        this.g = byteBuffer;
        return byteBuffer;
    }

    public boolean a() {
        return this.e != AudioProcessor.AudioFormat.a;
    }

    /* access modifiers changed from: protected */
    public AudioProcessor.AudioFormat b(AudioProcessor.AudioFormat audioFormat) {
        return AudioProcessor.AudioFormat.a;
    }

    public final void b() {
        this.h = true;
        h();
    }

    public ByteBuffer c() {
        ByteBuffer byteBuffer = this.g;
        this.g = a;
        return byteBuffer;
    }

    public boolean d() {
        return this.h && this.g == a;
    }

    public final void e() {
        this.g = a;
        this.h = false;
        this.b = this.d;
        this.c = this.e;
        i();
    }

    public final void f() {
        e();
        this.f = a;
        this.d = AudioProcessor.AudioFormat.a;
        this.e = AudioProcessor.AudioFormat.a;
        this.b = AudioProcessor.AudioFormat.a;
        this.c = AudioProcessor.AudioFormat.a;
        j();
    }

    /* access modifiers changed from: protected */
    public final boolean g() {
        return this.g.hasRemaining();
    }

    /* access modifiers changed from: protected */
    public void h() {
    }

    /* access modifiers changed from: protected */
    public void i() {
    }

    /* access modifiers changed from: protected */
    public void j() {
    }
}
