package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.upstream.DataReader;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.Arrays;

public interface TrackOutput {

    public final class CryptoData {
        public final int a;
        public final byte[] b;
        public final int c;
        public final int d;

        public CryptoData(int i, byte[] bArr, int i2, int i3) {
            this.a = i;
            this.b = bArr;
            this.c = i2;
            this.d = i3;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                CryptoData cryptoData = (CryptoData) obj;
                return this.a == cryptoData.a && this.c == cryptoData.c && this.d == cryptoData.d && Arrays.equals(this.b, cryptoData.b);
            }
        }

        public final int hashCode() {
            return (((((this.a * 31) + Arrays.hashCode(this.b)) * 31) + this.c) * 31) + this.d;
        }
    }

    int a(DataReader dataReader, int i, boolean z);

    void a(long j, int i, int i2, int i3, CryptoData cryptoData);

    void a(Format format);

    void a(ParsableByteArray parsableByteArray, int i);

    int b(DataReader dataReader, int i, boolean z);

    void b(ParsableByteArray parsableByteArray, int i);
}
