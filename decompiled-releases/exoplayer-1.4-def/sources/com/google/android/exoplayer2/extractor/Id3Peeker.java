package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.id3.Id3Decoder;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.io.EOFException;

public final class Id3Peeker {
    private final ParsableByteArray a = new ParsableByteArray(10);

    public final Metadata a(ExtractorInput extractorInput, Id3Decoder.FramePredicate framePredicate) {
        Metadata metadata = null;
        int i = 0;
        while (true) {
            try {
                extractorInput.d(this.a.a, 0, 10);
                this.a.d(0);
                if (this.a.g() != 4801587) {
                    break;
                }
                this.a.e(3);
                int n = this.a.n();
                int i2 = n + 10;
                if (metadata == null) {
                    byte[] bArr = new byte[i2];
                    System.arraycopy(this.a.a, 0, bArr, 0, 10);
                    extractorInput.d(bArr, 10, n);
                    metadata = new Id3Decoder(framePredicate).a(bArr, i2);
                } else {
                    extractorInput.c(n);
                }
                i += i2;
            } catch (EOFException e) {
            }
        }
        extractorInput.a();
        extractorInput.c(i);
        return metadata;
    }
}
