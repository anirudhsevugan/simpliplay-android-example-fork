package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public interface AudioProcessor {
    public static final ByteBuffer a = ByteBuffer.allocateDirect(0).order(ByteOrder.nativeOrder());

    public final class AudioFormat {
        public static final AudioFormat a = new AudioFormat(-1, -1, -1);
        public final int b;
        public final int c;
        public final int d;
        public final int e;

        public AudioFormat(int i, int i2, int i3) {
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = Util.c(i3) ? Util.c(i3, i2) : -1;
        }

        public final String toString() {
            int i = this.b;
            int i2 = this.c;
            return new StringBuilder(83).append("AudioFormat[sampleRate=").append(i).append(", channelCount=").append(i2).append(", encoding=").append(this.d).append(']').toString();
        }
    }

    public final class UnhandledAudioFormatException extends Exception {
        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public UnhandledAudioFormatException(com.google.android.exoplayer2.audio.AudioProcessor.AudioFormat r3) {
            /*
                r2 = this;
                java.lang.String r3 = java.lang.String.valueOf(r3)
                java.lang.String r0 = java.lang.String.valueOf(r3)
                int r0 = r0.length()
                int r0 = r0 + 18
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>(r0)
                java.lang.String r0 = "Unhandled format: "
                java.lang.StringBuilder r0 = r1.append(r0)
                java.lang.StringBuilder r3 = r0.append(r3)
                java.lang.String r3 = r3.toString()
                r2.<init>(r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.audio.AudioProcessor.UnhandledAudioFormatException.<init>(com.google.android.exoplayer2.audio.AudioProcessor$AudioFormat):void");
        }
    }

    AudioFormat a(AudioFormat audioFormat);

    void a(ByteBuffer byteBuffer);

    boolean a();

    void b();

    ByteBuffer c();

    boolean d();

    void e();

    void f();
}
