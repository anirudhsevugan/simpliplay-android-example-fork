package com.google.android.exoplayer2.mediacodec;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.Surface;
import com.google.android.exoplayer2.decoder.CryptoInfo;
import com.google.android.exoplayer2.mediacodec.SynchronousMediaCodecAdapter;
import java.nio.ByteBuffer;

public interface MediaCodecAdapter {

    public final class Configuration {
        public final MediaCodecInfo a;
        public final MediaFormat b;
        public final Surface c;
        public final MediaCrypto d;

        public Configuration(MediaCodecInfo mediaCodecInfo, MediaFormat mediaFormat, Surface surface, MediaCrypto mediaCrypto) {
            this.a = mediaCodecInfo;
            this.b = mediaFormat;
            this.c = surface;
            this.d = mediaCrypto;
        }
    }

    public interface Factory {
        public static final Factory a = new SynchronousMediaCodecAdapter.Factory();

        MediaCodecAdapter a(Configuration configuration);
    }

    public interface OnFrameRenderedListener {
        void a(long j);
    }

    int a();

    int a(MediaCodec.BufferInfo bufferInfo);

    ByteBuffer a(int i);

    void a(int i, int i2, long j, int i3);

    void a(int i, long j);

    void a(int i, CryptoInfo cryptoInfo, long j);

    void a(int i, boolean z);

    void a(Bundle bundle);

    void a(Surface surface);

    void a(OnFrameRenderedListener onFrameRenderedListener, Handler handler);

    MediaFormat b();

    ByteBuffer b(int i);

    void c();

    void c(int i);

    void d();
}
