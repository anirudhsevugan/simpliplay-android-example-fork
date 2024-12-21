package com.google.android.exoplayer2.extractor.wav;

import android.util.Pair;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;

final class WavHeaderReader {

    final class ChunkHeader {
        public final int a;
        public final long b;

        private ChunkHeader(int i, long j) {
            this.a = i;
            this.b = j;
        }

        public static ChunkHeader a(ExtractorInput extractorInput, ParsableByteArray parsableByteArray) {
            extractorInput.d(parsableByteArray.a, 0, 8);
            parsableByteArray.d(0);
            return new ChunkHeader(parsableByteArray.j(), parsableByteArray.i());
        }
    }

    public static WavHeader a(ExtractorInput extractorInput) {
        ChunkHeader a;
        byte[] bArr;
        Assertions.b((Object) extractorInput);
        ParsableByteArray parsableByteArray = new ParsableByteArray(16);
        if (ChunkHeader.a(extractorInput, parsableByteArray).a != 1380533830) {
            return null;
        }
        extractorInput.d(parsableByteArray.a, 0, 4);
        parsableByteArray.d(0);
        int j = parsableByteArray.j();
        if (j != 1463899717) {
            Log.d("WavHeaderReader", new StringBuilder(36).append("Unsupported RIFF format: ").append(j).toString());
            return null;
        }
        while (true) {
            a = ChunkHeader.a(extractorInput, parsableByteArray);
            if (a.a == 1718449184) {
                break;
            }
            extractorInput.c((int) a.b);
        }
        Assertions.b(a.b >= 16);
        extractorInput.d(parsableByteArray.a, 0, 16);
        parsableByteArray.d(0);
        int e = parsableByteArray.e();
        int e2 = parsableByteArray.e();
        int p = parsableByteArray.p();
        parsableByteArray.p();
        int e3 = parsableByteArray.e();
        int e4 = parsableByteArray.e();
        int i = ((int) a.b) - 16;
        if (i > 0) {
            byte[] bArr2 = new byte[i];
            extractorInput.d(bArr2, 0, i);
            bArr = bArr2;
        } else {
            bArr = Util.f;
        }
        return new WavHeader(e, e2, p, e3, e4, bArr);
    }

    public static Pair b(ExtractorInput extractorInput) {
        Assertions.b((Object) extractorInput);
        extractorInput.a();
        ParsableByteArray parsableByteArray = new ParsableByteArray(8);
        while (true) {
            ChunkHeader a = ChunkHeader.a(extractorInput, parsableByteArray);
            if (a.a != 1684108385) {
                if (!(a.a == 1380533830 || a.a == 1718449184)) {
                    Log.c("WavHeaderReader", new StringBuilder(39).append("Ignoring unknown WAV chunk: ").append(a.a).toString());
                }
                long j = a.b + 8;
                if (a.a == 1380533830) {
                    j = 12;
                }
                if (j <= 2147483647L) {
                    extractorInput.b((int) j);
                } else {
                    throw new ParserException(new StringBuilder(51).append("Chunk is too large (~2GB+) to skip; id: ").append(a.a).toString());
                }
            } else {
                extractorInput.b(8);
                long c = extractorInput.c();
                long j2 = a.b + c;
                long d = extractorInput.d();
                if (d != -1 && j2 > d) {
                    Log.c("WavHeaderReader", new StringBuilder(69).append("Data exceeds input length: ").append(j2).append(", ").append(d).toString());
                    j2 = d;
                }
                return Pair.create(Long.valueOf(c), Long.valueOf(j2));
            }
        }
    }
}
