package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;

final class OggPacket {
    final OggPageHeader a = new OggPageHeader();
    final ParsableByteArray b = new ParsableByteArray(new byte[65025], 0);
    int c = -1;
    boolean d;
    private int e;

    OggPacket() {
    }

    private int a(int i) {
        int i2 = 0;
        this.e = 0;
        while (this.e + i < this.a.c) {
            int[] iArr = this.a.f;
            int i3 = this.e;
            this.e = i3 + 1;
            int i4 = iArr[i3 + i];
            i2 += i4;
            if (i4 != 255) {
                break;
            }
        }
        return i2;
    }

    public final boolean a(ExtractorInput extractorInput) {
        int i;
        Assertions.b(extractorInput != null);
        if (this.d) {
            this.d = false;
            this.b.a(0);
        }
        while (!this.d) {
            if (this.c < 0) {
                if (!this.a.a(extractorInput) || !this.a.a(extractorInput, true)) {
                    return false;
                }
                int i2 = this.a.d;
                if ((this.a.a & 1) == 1 && this.b.c == 0) {
                    i2 += a(0);
                    i = this.e + 0;
                } else {
                    i = 0;
                }
                extractorInput.b(i2);
                this.c = i;
            }
            int a2 = a(this.c);
            int i3 = this.c + this.e;
            if (a2 > 0) {
                ParsableByteArray parsableByteArray = this.b;
                parsableByteArray.b(parsableByteArray.c + a2);
                extractorInput.b(this.b.a, this.b.c, a2);
                ParsableByteArray parsableByteArray2 = this.b;
                parsableByteArray2.c(parsableByteArray2.c + a2);
                this.d = this.a.f[i3 + -1] != 255;
            }
            if (i3 == this.a.c) {
                i3 = -1;
            }
            this.c = i3;
        }
        return true;
    }
}
