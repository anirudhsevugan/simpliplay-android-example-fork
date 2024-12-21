package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackParameters;
import java.nio.ByteBuffer;

public interface AudioSink {

    public final class ConfigurationException extends Exception {
        public final Format a;

        public ConfigurationException(String str, Format format) {
            super(str);
            this.a = format;
        }

        public ConfigurationException(Throwable th, Format format) {
            super(th);
            this.a = format;
        }
    }

    public final class InitializationException extends Exception {
        public final boolean a;
        public final Format b;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public InitializationException(int r4, int r5, int r6, int r7, com.google.android.exoplayer2.Format r8, boolean r9, java.lang.Exception r10) {
            /*
                r3 = this;
                if (r9 == 0) goto L_0x0005
                java.lang.String r0 = " (recoverable)"
                goto L_0x0007
            L_0x0005:
                java.lang.String r0 = ""
            L_0x0007:
                java.lang.String r1 = java.lang.String.valueOf(r0)
                int r1 = r1.length()
                int r1 = r1 + 80
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>(r1)
                java.lang.String r1 = "AudioTrack init failed "
                java.lang.StringBuilder r1 = r2.append(r1)
                java.lang.StringBuilder r4 = r1.append(r4)
                java.lang.String r1 = " Config("
                java.lang.StringBuilder r4 = r4.append(r1)
                java.lang.StringBuilder r4 = r4.append(r5)
                java.lang.String r5 = ", "
                java.lang.StringBuilder r4 = r4.append(r5)
                java.lang.StringBuilder r4 = r4.append(r6)
                java.lang.StringBuilder r4 = r4.append(r5)
                java.lang.StringBuilder r4 = r4.append(r7)
                java.lang.String r5 = ")"
                java.lang.StringBuilder r4 = r4.append(r5)
                java.lang.StringBuilder r4 = r4.append(r0)
                java.lang.String r4 = r4.toString()
                r3.<init>(r4, r10)
                r3.a = r9
                r3.b = r8
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.audio.AudioSink.InitializationException.<init>(int, int, int, int, com.google.android.exoplayer2.Format, boolean, java.lang.Exception):void");
        }
    }

    public interface Listener {
        void a();

        void a(int i, long j, long j2);

        void a(long j);

        void a(Exception exc);

        void a(boolean z);

        void b();

        void b(long j);
    }

    public final class UnexpectedDiscontinuityException extends Exception {
        public UnexpectedDiscontinuityException(long j, long j2) {
            super(new StringBuilder(103).append("Unexpected audio track timestamp discontinuity: expected ").append(j2).append(", got ").append(j).toString());
        }
    }

    public final class WriteException extends Exception {
        public final boolean a;
        public final Format b;

        public WriteException(int i, Format format, boolean z) {
            super(new StringBuilder(36).append("AudioTrack write failed: ").append(i).toString());
            this.a = z;
            this.b = format;
        }
    }

    long a(boolean z);

    void a();

    void a(float f);

    void a(int i);

    void a(Format format, int[] iArr);

    void a(PlaybackParameters playbackParameters);

    void a(AudioAttributes audioAttributes);

    void a(Listener listener);

    void a(AuxEffectInfo auxEffectInfo);

    boolean a(Format format);

    boolean a(ByteBuffer byteBuffer, long j, int i);

    int b(Format format);

    void b();

    void b(boolean z);

    void c();

    boolean d();

    boolean e();

    PlaybackParameters f();

    void g();

    void h();

    void i();

    void j();

    void k();
}
