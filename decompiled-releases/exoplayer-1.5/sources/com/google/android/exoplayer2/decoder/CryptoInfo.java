package com.google.android.exoplayer2.decoder;

import android.media.MediaCodec;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

public final class CryptoInfo {
    public byte[] a;
    public int[] b;
    public int[] c;
    public final MediaCodec.CryptoInfo d;
    private final PatternHolderV24 e;

    final class PatternHolderV24 {
        private final MediaCodec.CryptoInfo a;
        private final MediaCodec.CryptoInfo.Pattern b;

        private PatternHolderV24(MediaCodec.CryptoInfo cryptoInfo) {
            this.a = cryptoInfo;
            this.b = new MediaCodec.CryptoInfo.Pattern(0, 0);
        }

        /* synthetic */ PatternHolderV24(MediaCodec.CryptoInfo cryptoInfo, byte b2) {
            this(cryptoInfo);
        }

        static /* synthetic */ void a(PatternHolderV24 patternHolderV24, int i, int i2) {
            patternHolderV24.b.set(i, i2);
            patternHolderV24.a.setPattern(patternHolderV24.b);
        }
    }

    public CryptoInfo() {
        MediaCodec.CryptoInfo cryptoInfo = new MediaCodec.CryptoInfo();
        this.d = cryptoInfo;
        this.e = Util.a >= 24 ? new PatternHolderV24(cryptoInfo, (byte) 0) : null;
    }

    public final void a(int i, int[] iArr, int[] iArr2, byte[] bArr, byte[] bArr2, int i2, int i3, int i4) {
        this.b = iArr;
        this.c = iArr2;
        this.a = bArr2;
        this.d.numSubSamples = i;
        this.d.numBytesOfClearData = iArr;
        this.d.numBytesOfEncryptedData = iArr2;
        this.d.key = bArr;
        this.d.iv = bArr2;
        this.d.mode = i2;
        if (Util.a >= 24) {
            PatternHolderV24.a((PatternHolderV24) Assertions.b((Object) this.e), i3, i4);
        }
    }
}
