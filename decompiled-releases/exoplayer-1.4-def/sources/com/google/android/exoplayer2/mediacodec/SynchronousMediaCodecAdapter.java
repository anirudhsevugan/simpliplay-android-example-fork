package com.google.android.exoplayer2.mediacodec;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.Surface;
import com.google.android.exoplayer2.decoder.CryptoInfo;
import com.google.android.exoplayer2.mediacodec.MediaCodecAdapter;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;

public class SynchronousMediaCodecAdapter implements MediaCodecAdapter {
    private final MediaCodec a;
    private ByteBuffer[] b;
    private ByteBuffer[] c;

    public class Factory implements MediaCodecAdapter.Factory {
        /* JADX WARNING: Removed duplicated region for block: B:15:0x0058  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.google.android.exoplayer2.mediacodec.MediaCodecAdapter a(com.google.android.exoplayer2.mediacodec.MediaCodecAdapter.Configuration r6) {
            /*
                r5 = this;
                r0 = 0
                com.google.android.exoplayer2.mediacodec.MediaCodecInfo r1 = r6.a     // Catch:{ IOException -> 0x0055, RuntimeException -> 0x0053 }
                com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r1)     // Catch:{ IOException -> 0x0055, RuntimeException -> 0x0053 }
                com.google.android.exoplayer2.mediacodec.MediaCodecInfo r1 = r6.a     // Catch:{ IOException -> 0x0055, RuntimeException -> 0x0053 }
                java.lang.String r1 = r1.a     // Catch:{ IOException -> 0x0055, RuntimeException -> 0x0053 }
                java.lang.String r2 = "createCodec:"
                java.lang.String r3 = java.lang.String.valueOf(r1)     // Catch:{ IOException -> 0x0055, RuntimeException -> 0x0053 }
                int r4 = r3.length()     // Catch:{ IOException -> 0x0055, RuntimeException -> 0x0053 }
                if (r4 == 0) goto L_0x001b
                java.lang.String r2 = r2.concat(r3)     // Catch:{ IOException -> 0x0055, RuntimeException -> 0x0053 }
                goto L_0x0021
            L_0x001b:
                java.lang.String r3 = new java.lang.String     // Catch:{ IOException -> 0x0055, RuntimeException -> 0x0053 }
                r3.<init>(r2)     // Catch:{ IOException -> 0x0055, RuntimeException -> 0x0053 }
                r2 = r3
            L_0x0021:
                com.google.android.exoplayer2.util.TraceUtil.a(r2)     // Catch:{ IOException -> 0x0055, RuntimeException -> 0x0053 }
                android.media.MediaCodec r1 = android.media.MediaCodec.createByCodecName(r1)     // Catch:{ IOException -> 0x0055, RuntimeException -> 0x0053 }
                com.google.android.exoplayer2.util.TraceUtil.a()     // Catch:{ IOException -> 0x0055, RuntimeException -> 0x0053 }
                java.lang.String r0 = "configureCodec"
                com.google.android.exoplayer2.util.TraceUtil.a(r0)     // Catch:{ IOException -> 0x0050, RuntimeException -> 0x004e }
                android.media.MediaFormat r0 = r6.b     // Catch:{ IOException -> 0x0050, RuntimeException -> 0x004e }
                android.view.Surface r2 = r6.c     // Catch:{ IOException -> 0x0050, RuntimeException -> 0x004e }
                android.media.MediaCrypto r6 = r6.d     // Catch:{ IOException -> 0x0050, RuntimeException -> 0x004e }
                r3 = 0
                r1.configure(r0, r2, r6, r3)     // Catch:{ IOException -> 0x0050, RuntimeException -> 0x004e }
                com.google.android.exoplayer2.util.TraceUtil.a()     // Catch:{ IOException -> 0x0050, RuntimeException -> 0x004e }
                java.lang.String r6 = "startCodec"
                com.google.android.exoplayer2.util.TraceUtil.a(r6)     // Catch:{ IOException -> 0x0050, RuntimeException -> 0x004e }
                r1.start()     // Catch:{ IOException -> 0x0050, RuntimeException -> 0x004e }
                com.google.android.exoplayer2.util.TraceUtil.a()     // Catch:{ IOException -> 0x0050, RuntimeException -> 0x004e }
                com.google.android.exoplayer2.mediacodec.SynchronousMediaCodecAdapter r6 = new com.google.android.exoplayer2.mediacodec.SynchronousMediaCodecAdapter     // Catch:{ IOException -> 0x0050, RuntimeException -> 0x004e }
                r6.<init>(r1, r3)     // Catch:{ IOException -> 0x0050, RuntimeException -> 0x004e }
                return r6
            L_0x004e:
                r6 = move-exception
                goto L_0x0051
            L_0x0050:
                r6 = move-exception
            L_0x0051:
                r0 = r1
                goto L_0x0056
            L_0x0053:
                r6 = move-exception
                goto L_0x0056
            L_0x0055:
                r6 = move-exception
            L_0x0056:
                if (r0 == 0) goto L_0x005b
                r0.release()
            L_0x005b:
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.mediacodec.SynchronousMediaCodecAdapter.Factory.a(com.google.android.exoplayer2.mediacodec.MediaCodecAdapter$Configuration):com.google.android.exoplayer2.mediacodec.MediaCodecAdapter");
        }
    }

    private SynchronousMediaCodecAdapter(MediaCodec mediaCodec) {
        this.a = mediaCodec;
        if (Util.a < 21) {
            this.b = mediaCodec.getInputBuffers();
            this.c = mediaCodec.getOutputBuffers();
        }
    }

    /* synthetic */ SynchronousMediaCodecAdapter(MediaCodec mediaCodec, byte b2) {
        this(mediaCodec);
    }

    public final int a() {
        return this.a.dequeueInputBuffer(0);
    }

    public final int a(MediaCodec.BufferInfo bufferInfo) {
        int dequeueOutputBuffer;
        do {
            dequeueOutputBuffer = this.a.dequeueOutputBuffer(bufferInfo, 0);
            if (dequeueOutputBuffer == -3 && Util.a < 21) {
                this.c = this.a.getOutputBuffers();
                continue;
            }
        } while (dequeueOutputBuffer == -3);
        return dequeueOutputBuffer;
    }

    public final ByteBuffer a(int i) {
        return Util.a >= 21 ? this.a.getInputBuffer(i) : ((ByteBuffer[]) Util.a((Object) this.b))[i];
    }

    public final void a(int i, int i2, long j, int i3) {
        this.a.queueInputBuffer(i, 0, i2, j, i3);
    }

    public final void a(int i, long j) {
        this.a.releaseOutputBuffer(i, j);
    }

    public final void a(int i, CryptoInfo cryptoInfo, long j) {
        this.a.queueSecureInputBuffer(i, 0, cryptoInfo.d, j, 0);
    }

    public final void a(int i, boolean z) {
        this.a.releaseOutputBuffer(i, z);
    }

    public final void a(Bundle bundle) {
        this.a.setParameters(bundle);
    }

    public final void a(Surface surface) {
        this.a.setOutputSurface(surface);
    }

    public final void a(MediaCodecAdapter.OnFrameRenderedListener onFrameRenderedListener, Handler handler) {
        this.a.setOnFrameRenderedListener(new SynchronousMediaCodecAdapter$$Lambda$0(onFrameRenderedListener), handler);
    }

    public final MediaFormat b() {
        return this.a.getOutputFormat();
    }

    public final ByteBuffer b(int i) {
        return Util.a >= 21 ? this.a.getOutputBuffer(i) : ((ByteBuffer[]) Util.a((Object) this.c))[i];
    }

    public final void c() {
        this.a.flush();
    }

    public final void c(int i) {
        this.a.setVideoScalingMode(i);
    }

    public final void d() {
        this.b = null;
        this.c = null;
        this.a.release();
    }
}
