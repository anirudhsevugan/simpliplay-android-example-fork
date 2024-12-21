package com.google.android.exoplayer2.extractor;

import com.dreamers.exoplayercore.repack.aC;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.FlacStreamMetadata;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.flac.PictureFrame;
import com.google.android.exoplayer2.metadata.id3.Id3Decoder;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.Arrays;
import java.util.Collections;

public final class FlacMetadataReader {

    public final class FlacStreamMetadataHolder {
        public FlacStreamMetadata a;

        public FlacStreamMetadataHolder(FlacStreamMetadata flacStreamMetadata) {
            this.a = flacStreamMetadata;
        }
    }

    public static FlacStreamMetadata.SeekTable a(ParsableByteArray parsableByteArray) {
        parsableByteArray.e(1);
        int g = parsableByteArray.g();
        long j = ((long) parsableByteArray.b) + ((long) g);
        int i = g / 18;
        long[] jArr = new long[i];
        long[] jArr2 = new long[i];
        int i2 = 0;
        while (true) {
            if (i2 >= i) {
                break;
            }
            long l = parsableByteArray.l();
            if (l == -1) {
                jArr = Arrays.copyOf(jArr, i2);
                jArr2 = Arrays.copyOf(jArr2, i2);
                break;
            }
            jArr[i2] = l;
            jArr2[i2] = parsableByteArray.l();
            parsableByteArray.e(2);
            i2++;
        }
        parsableByteArray.e((int) (j - ((long) parsableByteArray.b)));
        return new FlacStreamMetadata.SeekTable(jArr, jArr2);
    }

    public static Metadata a(ExtractorInput extractorInput, boolean z) {
        Metadata a = new Id3Peeker().a(extractorInput, z ? null : Id3Decoder.a);
        if (a == null || a.a.length == 0) {
            return null;
        }
        return a;
    }

    public static boolean a(ExtractorInput extractorInput) {
        ParsableByteArray parsableByteArray = new ParsableByteArray(4);
        extractorInput.d(parsableByteArray.a, 0, 4);
        return parsableByteArray.h() == 1716281667;
    }

    public static boolean a(ExtractorInput extractorInput, FlacStreamMetadataHolder flacStreamMetadataHolder) {
        FlacStreamMetadata flacStreamMetadata;
        ExtractorInput extractorInput2 = extractorInput;
        FlacStreamMetadataHolder flacStreamMetadataHolder2 = flacStreamMetadataHolder;
        extractorInput.a();
        ParsableBitArray parsableBitArray = new ParsableBitArray(new byte[4]);
        extractorInput2.d(parsableBitArray.a, 0, 4);
        boolean e = parsableBitArray.e();
        int c = parsableBitArray.c(7);
        int c2 = parsableBitArray.c(24) + 4;
        if (c == 0) {
            byte[] bArr = new byte[38];
            extractorInput2.b(bArr, 0, 38);
            flacStreamMetadata = new FlacStreamMetadata(bArr, 4);
        } else {
            FlacStreamMetadata flacStreamMetadata2 = flacStreamMetadataHolder2.a;
            if (flacStreamMetadata2 == null) {
                throw new IllegalArgumentException();
            } else if (c == 3) {
                ParsableByteArray parsableByteArray = new ParsableByteArray(c2);
                extractorInput2.b(parsableByteArray.a, 0, c2);
                flacStreamMetadata = flacStreamMetadata2.a(a(parsableByteArray));
            } else if (c == 4) {
                ParsableByteArray parsableByteArray2 = new ParsableByteArray(c2);
                extractorInput2.b(parsableByteArray2.a, 0, c2);
                parsableByteArray2.e(4);
                Metadata a = flacStreamMetadata2.a(FlacStreamMetadata.a(Arrays.asList(VorbisUtil.a(parsableByteArray2, false, false).a), Collections.emptyList()));
                flacStreamMetadata = new FlacStreamMetadata(flacStreamMetadata2.a, flacStreamMetadata2.b, flacStreamMetadata2.c, flacStreamMetadata2.d, flacStreamMetadata2.e, flacStreamMetadata2.g, flacStreamMetadata2.h, flacStreamMetadata2.j, flacStreamMetadata2.k, a);
            } else if (c == 6) {
                ParsableByteArray parsableByteArray3 = new ParsableByteArray(c2);
                extractorInput2.b(parsableByteArray3.a, 0, c2);
                parsableByteArray3.e(4);
                int j = parsableByteArray3.j();
                String a2 = parsableByteArray3.a(parsableByteArray3.j(), aC.a);
                String f = parsableByteArray3.f(parsableByteArray3.j());
                int j2 = parsableByteArray3.j();
                int j3 = parsableByteArray3.j();
                int j4 = parsableByteArray3.j();
                int j5 = parsableByteArray3.j();
                int j6 = parsableByteArray3.j();
                byte[] bArr2 = new byte[j6];
                parsableByteArray3.a(bArr2, 0, j6);
                Metadata a3 = flacStreamMetadata2.a(FlacStreamMetadata.a(Collections.emptyList(), Collections.singletonList(new PictureFrame(j, a2, f, j2, j3, j4, j5, bArr2))));
                flacStreamMetadata = new FlacStreamMetadata(flacStreamMetadata2.a, flacStreamMetadata2.b, flacStreamMetadata2.c, flacStreamMetadata2.d, flacStreamMetadata2.e, flacStreamMetadata2.g, flacStreamMetadata2.h, flacStreamMetadata2.j, flacStreamMetadata2.k, a3);
            } else {
                extractorInput2.b(c2);
                return e;
            }
        }
        flacStreamMetadataHolder2.a = flacStreamMetadata;
        return e;
    }

    public static Metadata b(ExtractorInput extractorInput, boolean z) {
        extractorInput.a();
        long b = extractorInput.b();
        Metadata a = a(extractorInput, true);
        extractorInput.b((int) (extractorInput.b() - b));
        return a;
    }

    public static void b(ExtractorInput extractorInput) {
        ParsableByteArray parsableByteArray = new ParsableByteArray(4);
        extractorInput.b(parsableByteArray.a, 0, 4);
        if (parsableByteArray.h() != 1716281667) {
            throw new ParserException("Failed to read FLAC stream marker.");
        }
    }

    public static int c(ExtractorInput extractorInput) {
        extractorInput.a();
        ParsableByteArray parsableByteArray = new ParsableByteArray(2);
        extractorInput.d(parsableByteArray.a, 0, 2);
        int d = parsableByteArray.d();
        int i = d >> 2;
        extractorInput.a();
        if (i == 16382) {
            return d;
        }
        throw new ParserException("First frame does not start with sync code.");
    }
}
