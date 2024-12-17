package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.FlacFrameReader;
import com.google.android.exoplayer2.extractor.FlacMetadataReader;
import com.google.android.exoplayer2.extractor.FlacSeekTableSeekMap;
import com.google.android.exoplayer2.extractor.FlacStreamMetadata;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.ogg.StreamReader;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.util.Arrays;

final class FlacReader extends StreamReader {
    private FlacStreamMetadata j;
    private FlacOggSeeker k;

    final class FlacOggSeeker implements OggSeeker {
        long a = -1;
        private FlacStreamMetadata b;
        private FlacStreamMetadata.SeekTable c;
        private long d = -1;

        public FlacOggSeeker(FlacStreamMetadata flacStreamMetadata, FlacStreamMetadata.SeekTable seekTable) {
            this.b = flacStreamMetadata;
            this.c = seekTable;
        }

        public final long a(ExtractorInput extractorInput) {
            long j = this.d;
            if (j < 0) {
                return -1;
            }
            long j2 = -(j + 2);
            this.d = -1;
            return j2;
        }

        public final SeekMap a() {
            Assertions.b(this.a != -1);
            return new FlacSeekTableSeekMap(this.b, this.a);
        }

        public final void a(long j) {
            long[] jArr = this.c.a;
            this.d = jArr[Util.a(jArr, j, true)];
        }
    }

    FlacReader() {
    }

    public static boolean a(ParsableByteArray parsableByteArray) {
        return parsableByteArray.a() >= 5 && parsableByteArray.c() == 127 && parsableByteArray.h() == 1179402563;
    }

    private static boolean a(byte[] bArr) {
        return bArr[0] == -1;
    }

    /* access modifiers changed from: protected */
    public final void a(boolean z) {
        super.a(z);
        if (z) {
            this.j = null;
            this.k = null;
        }
    }

    /* access modifiers changed from: protected */
    public final boolean a(ParsableByteArray parsableByteArray, long j2, StreamReader.SetupData setupData) {
        byte[] bArr = parsableByteArray.a;
        FlacStreamMetadata flacStreamMetadata = this.j;
        if (flacStreamMetadata == null) {
            FlacStreamMetadata flacStreamMetadata2 = new FlacStreamMetadata(bArr, 17);
            this.j = flacStreamMetadata2;
            setupData.a = flacStreamMetadata2.a(Arrays.copyOfRange(bArr, 9, parsableByteArray.c), (Metadata) null);
            return true;
        } else if ((bArr[0] & Ev3Constants.Opcode.MEMORY_READ) == 3) {
            FlacStreamMetadata.SeekTable a = FlacMetadataReader.a(parsableByteArray);
            FlacStreamMetadata a2 = flacStreamMetadata.a(a);
            this.j = a2;
            this.k = new FlacOggSeeker(a2, a);
            return true;
        } else if (!a(bArr)) {
            return true;
        } else {
            FlacOggSeeker flacOggSeeker = this.k;
            if (flacOggSeeker != null) {
                flacOggSeeker.a = j2;
                setupData.b = this.k;
            }
            Assertions.b((Object) setupData.a);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public final long b(ParsableByteArray parsableByteArray) {
        if (!a(parsableByteArray.a)) {
            return -1;
        }
        int i = (parsableByteArray.a[2] & Ev3Constants.Opcode.TST) >> 4;
        if (i == 6 || i == 7) {
            parsableByteArray.e(4);
            parsableByteArray.t();
        }
        int a = FlacFrameReader.a(parsableByteArray, i);
        parsableByteArray.d(0);
        return (long) a;
    }
}
