package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.android.exoplayer2.util.Util;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import gnu.expr.Declaration;
import java.nio.ByteBuffer;

final class FloatResamplingAudioProcessor extends BaseAudioProcessor {
    private static final int d = Float.floatToIntBits(Float.NaN);

    FloatResamplingAudioProcessor() {
    }

    private static void a(int i, ByteBuffer byteBuffer) {
        double d2 = (double) i;
        Double.isNaN(d2);
        int floatToIntBits = Float.floatToIntBits((float) (d2 * 4.656612875245797E-10d));
        if (floatToIntBits == d) {
            floatToIntBits = Float.floatToIntBits(0.0f);
        }
        byteBuffer.putInt(floatToIntBits);
    }

    public final void a(ByteBuffer byteBuffer) {
        ByteBuffer byteBuffer2;
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        int i = limit - position;
        switch (this.b.d) {
            case Declaration.EARLY_INIT:
                byteBuffer2 = a((i / 3) << 2);
                while (position < limit) {
                    a(((byteBuffer.get(position) & Ev3Constants.Opcode.TST) << 8) | ((byteBuffer.get(position + 1) & Ev3Constants.Opcode.TST) << 16) | ((byteBuffer.get(position + 2) & Ev3Constants.Opcode.TST) << 24), byteBuffer2);
                    position += 3;
                }
                break;
            case 805306368:
                byteBuffer2 = a(i);
                while (position < limit) {
                    a((byteBuffer.get(position) & Ev3Constants.Opcode.TST) | ((byteBuffer.get(position + 1) & Ev3Constants.Opcode.TST) << 8) | ((byteBuffer.get(position + 2) & Ev3Constants.Opcode.TST) << 16) | ((byteBuffer.get(position + 3) & Ev3Constants.Opcode.TST) << 24), byteBuffer2);
                    position += 4;
                }
                break;
            default:
                throw new IllegalStateException();
        }
        byteBuffer.position(byteBuffer.limit());
        byteBuffer2.flip();
    }

    public final AudioProcessor.AudioFormat b(AudioProcessor.AudioFormat audioFormat) {
        int i = audioFormat.d;
        if (Util.d(i)) {
            return i != 4 ? new AudioProcessor.AudioFormat(audioFormat.b, audioFormat.c, 4) : AudioProcessor.AudioFormat.a;
        }
        throw new AudioProcessor.UnhandledAudioFormatException(audioFormat);
    }
}
