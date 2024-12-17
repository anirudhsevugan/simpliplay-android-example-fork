package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.android.exoplayer2.util.Assertions;
import java.nio.ByteBuffer;

final class ChannelMappingAudioProcessor extends BaseAudioProcessor {
    int[] d;
    private int[] e;

    ChannelMappingAudioProcessor() {
    }

    public final void a(ByteBuffer byteBuffer) {
        int[] iArr = (int[]) Assertions.b((Object) this.e);
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        ByteBuffer a = a(((limit - position) / this.b.e) * this.c.e);
        while (position < limit) {
            for (int i : iArr) {
                a.putShort(byteBuffer.getShort((i * 2) + position));
            }
            position += this.b.e;
        }
        byteBuffer.position(limit);
        a.flip();
    }

    public final AudioProcessor.AudioFormat b(AudioProcessor.AudioFormat audioFormat) {
        int[] iArr = this.d;
        if (iArr == null) {
            return AudioProcessor.AudioFormat.a;
        }
        if (audioFormat.d == 2) {
            boolean z = audioFormat.c != iArr.length;
            int i = 0;
            while (i < iArr.length) {
                int i2 = iArr[i];
                if (i2 < audioFormat.c) {
                    z |= i2 != i;
                    i++;
                } else {
                    throw new AudioProcessor.UnhandledAudioFormatException(audioFormat);
                }
            }
            return z ? new AudioProcessor.AudioFormat(audioFormat.b, iArr.length, 2) : AudioProcessor.AudioFormat.a;
        }
        throw new AudioProcessor.UnhandledAudioFormatException(audioFormat);
    }

    /* access modifiers changed from: protected */
    public final void i() {
        this.e = this.d;
    }

    /* access modifiers changed from: protected */
    public final void j() {
        this.e = null;
        this.d = null;
    }
}
