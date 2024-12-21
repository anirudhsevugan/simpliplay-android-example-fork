package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.upstream.DataReader;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.io.EOFException;

public final class DummyTrackOutput implements TrackOutput {
    private final byte[] a = new byte[4096];

    public final int a(DataReader dataReader, int i, boolean z) {
        int a2 = dataReader.a(this.a, 0, Math.min(4096, i));
        if (a2 != -1) {
            return a2;
        }
        if (z) {
            return -1;
        }
        throw new EOFException();
    }

    public final void a(long j, int i, int i2, int i3, TrackOutput.CryptoData cryptoData) {
    }

    public final void a(Format format) {
    }

    public final void a(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.e(i);
    }

    public final int b(DataReader dataReader, int i, boolean z) {
        return TrackOutput$$CC.a(this, dataReader, i, z);
    }

    public final void b(ParsableByteArray parsableByteArray, int i) {
        TrackOutput$$CC.a(this, parsableByteArray, i);
    }
}
