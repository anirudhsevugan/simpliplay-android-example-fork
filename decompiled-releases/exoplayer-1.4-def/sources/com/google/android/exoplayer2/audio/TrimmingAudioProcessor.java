package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;

final class TrimmingAudioProcessor extends BaseAudioProcessor {
    int d;
    int e;
    long f;
    private boolean g;
    private int h;
    private byte[] i = Util.f;
    private int j;

    public final void a(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        int i2 = limit - position;
        if (i2 != 0) {
            int min = Math.min(i2, this.h);
            this.f += (long) (min / this.b.e);
            this.h -= min;
            byteBuffer.position(position + min);
            if (this.h <= 0) {
                int i3 = i2 - min;
                int length = (this.j + i3) - this.i.length;
                ByteBuffer a = a(length);
                int a2 = Util.a(length, 0, this.j);
                a.put(this.i, 0, a2);
                int a3 = Util.a(length - a2, 0, i3);
                byteBuffer.limit(byteBuffer.position() + a3);
                a.put(byteBuffer);
                byteBuffer.limit(limit);
                int i4 = i3 - a3;
                int i5 = this.j - a2;
                this.j = i5;
                byte[] bArr = this.i;
                System.arraycopy(bArr, a2, bArr, 0, i5);
                byteBuffer.get(this.i, this.j, i4);
                this.j += i4;
                a.flip();
            }
        }
    }

    public final AudioProcessor.AudioFormat b(AudioProcessor.AudioFormat audioFormat) {
        if (audioFormat.d == 2) {
            this.g = true;
            return (this.d == 0 && this.e == 0) ? AudioProcessor.AudioFormat.a : audioFormat;
        }
        throw new AudioProcessor.UnhandledAudioFormatException(audioFormat);
    }

    public final ByteBuffer c() {
        int i2;
        if (super.d() && (i2 = this.j) > 0) {
            a(i2).put(this.i, 0, this.j).flip();
            this.j = 0;
        }
        return super.c();
    }

    public final boolean d() {
        return super.d() && this.j == 0;
    }

    /* access modifiers changed from: protected */
    public final void h() {
        if (this.g) {
            int i2 = this.j;
            if (i2 > 0) {
                this.f += (long) (i2 / this.b.e);
            }
            this.j = 0;
        }
    }

    /* access modifiers changed from: protected */
    public final void i() {
        if (this.g) {
            this.g = false;
            this.i = new byte[(this.e * this.b.e)];
            this.h = this.d * this.b.e;
        }
        this.j = 0;
    }

    /* access modifiers changed from: protected */
    public final void j() {
        this.i = Util.f;
    }
}
