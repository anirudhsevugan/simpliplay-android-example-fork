package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioProcessor;

final class ResamplingAudioProcessor extends BaseAudioProcessor {
    ResamplingAudioProcessor() {
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.nio.ByteBuffer r7) {
        /*
            r6 = this;
            int r0 = r7.position()
            int r1 = r7.limit()
            int r2 = r1 - r0
            com.google.android.exoplayer2.audio.AudioProcessor$AudioFormat r3 = r6.b
            int r3 = r3.d
            switch(r3) {
                case 3: goto L_0x001d;
                case 4: goto L_0x001a;
                case 268435456: goto L_0x001f;
                case 536870912: goto L_0x0017;
                case 805306368: goto L_0x001a;
                default: goto L_0x0011;
            }
        L_0x0011:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            r7.<init>()
            throw r7
        L_0x0017:
            int r2 = r2 / 3
            goto L_0x001d
        L_0x001a:
            int r2 = r2 / 2
            goto L_0x001f
        L_0x001d:
            int r2 = r2 << 1
        L_0x001f:
            java.nio.ByteBuffer r2 = r6.a((int) r2)
            com.google.android.exoplayer2.audio.AudioProcessor$AudioFormat r3 = r6.b
            int r3 = r3.d
            switch(r3) {
                case 3: goto L_0x0095;
                case 4: goto L_0x0073;
                case 268435456: goto L_0x005e;
                case 536870912: goto L_0x0047;
                case 805306368: goto L_0x0030;
                default: goto L_0x002a;
            }
        L_0x002a:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            r7.<init>()
            throw r7
        L_0x0030:
            if (r0 >= r1) goto L_0x00aa
            int r3 = r0 + 2
            byte r3 = r7.get(r3)
            r2.put(r3)
            int r3 = r0 + 3
            byte r3 = r7.get(r3)
            r2.put(r3)
            int r0 = r0 + 4
            goto L_0x0030
        L_0x0047:
            if (r0 >= r1) goto L_0x00aa
            int r3 = r0 + 1
            byte r3 = r7.get(r3)
            r2.put(r3)
            int r3 = r0 + 2
            byte r3 = r7.get(r3)
            r2.put(r3)
            int r0 = r0 + 3
            goto L_0x0047
        L_0x005e:
            if (r0 >= r1) goto L_0x00aa
            int r3 = r0 + 1
            byte r3 = r7.get(r3)
            r2.put(r3)
            byte r3 = r7.get(r0)
            r2.put(r3)
            int r0 = r0 + 2
            goto L_0x005e
        L_0x0073:
            if (r0 >= r1) goto L_0x00aa
            float r3 = r7.getFloat(r0)
            r4 = -1082130432(0xffffffffbf800000, float:-1.0)
            r5 = 1065353216(0x3f800000, float:1.0)
            float r3 = com.google.android.exoplayer2.util.Util.a((float) r3, (float) r4, (float) r5)
            r4 = 1191181824(0x46fffe00, float:32767.0)
            float r3 = r3 * r4
            int r3 = (int) r3
            short r3 = (short) r3
            byte r4 = (byte) r3
            r2.put(r4)
            int r3 = r3 >> 8
            byte r3 = (byte) r3
            r2.put(r3)
            int r0 = r0 + 4
            goto L_0x0073
        L_0x0095:
            if (r0 >= r1) goto L_0x00aa
            r3 = 0
            r2.put(r3)
            byte r3 = r7.get(r0)
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 + -128
            byte r3 = (byte) r3
            r2.put(r3)
            int r0 = r0 + 1
            goto L_0x0095
        L_0x00aa:
            int r0 = r7.limit()
            r7.position(r0)
            r2.flip()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.audio.ResamplingAudioProcessor.a(java.nio.ByteBuffer):void");
    }

    public final AudioProcessor.AudioFormat b(AudioProcessor.AudioFormat audioFormat) {
        int i = audioFormat.d;
        if (i == 3 || i == 2 || i == 268435456 || i == 536870912 || i == 805306368 || i == 4) {
            return i != 2 ? new AudioProcessor.AudioFormat(audioFormat.b, audioFormat.c, 2) : AudioProcessor.AudioFormat.a;
        }
        throw new AudioProcessor.UnhandledAudioFormatException(audioFormat);
    }
}
