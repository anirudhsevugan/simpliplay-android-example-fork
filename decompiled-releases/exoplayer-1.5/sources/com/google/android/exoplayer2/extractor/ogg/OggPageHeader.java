package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.io.EOFException;

final class OggPageHeader {
    public int a;
    public long b;
    public int c;
    public int d;
    public int e;
    public final int[] f = new int[255];
    private int g;
    private final ParsableByteArray h = new ParsableByteArray(255);

    OggPageHeader() {
    }

    private static boolean a(ExtractorInput extractorInput, byte[] bArr, int i, boolean z) {
        try {
            return extractorInput.b(bArr, 0, i, z);
        } catch (EOFException e2) {
            if (z) {
                return false;
            }
            throw e2;
        }
    }

    public final void a() {
        this.g = 0;
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
    }

    public final boolean a(ExtractorInput extractorInput) {
        return a(extractorInput, -1);
    }

    public final boolean a(ExtractorInput extractorInput, long j) {
        Assertions.a(extractorInput.c() == extractorInput.b());
        this.h.a(4);
        while (true) {
            if ((j == -1 || extractorInput.c() + 4 < j) && a(extractorInput, this.h.a, 4, true)) {
                this.h.d(0);
                if (this.h.h() == 1332176723) {
                    extractorInput.a();
                    return true;
                }
                extractorInput.b(1);
            }
        }
        do {
            if ((j != -1 && extractorInput.c() >= j) || extractorInput.a(1) == -1) {
                return false;
            }
            break;
        } while (extractorInput.a(1) == -1);
        return false;
    }

    public final boolean a(ExtractorInput extractorInput, boolean z) {
        a();
        this.h.a(27);
        if (!a(extractorInput, this.h.a, 27, z) || this.h.h() != 1332176723) {
            return false;
        }
        int c2 = this.h.c();
        this.g = c2;
        if (c2 == 0) {
            this.a = this.h.c();
            this.b = this.h.m();
            this.h.i();
            this.h.i();
            this.h.i();
            int c3 = this.h.c();
            this.c = c3;
            this.d = c3 + 27;
            this.h.a(c3);
            extractorInput.d(this.h.a, 0, this.c);
            for (int i = 0; i < this.c; i++) {
                this.f[i] = this.h.c();
                this.e += this.f[i];
            }
            return true;
        } else if (z) {
            return false;
        } else {
            throw new ParserException("unsupported bit stream revision");
        }
    }
}
