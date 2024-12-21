package com.google.android.exoplayer2.extractor.flac;

import com.google.android.exoplayer2.extractor.BinarySearchSeeker;
import com.google.android.exoplayer2.extractor.BinarySearchSeeker$TimestampSeeker$$CC;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.FlacFrameReader;
import com.google.android.exoplayer2.extractor.FlacStreamMetadata;

final class FlacBinarySearchSeeker extends BinarySearchSeeker {

    final class FlacTimestampSeeker implements BinarySearchSeeker.TimestampSeeker {
        private final FlacStreamMetadata a;
        private final int b;
        private final FlacFrameReader.SampleNumberHolder c;

        private FlacTimestampSeeker(FlacStreamMetadata flacStreamMetadata, int i) {
            this.a = flacStreamMetadata;
            this.b = i;
            this.c = new FlacFrameReader.SampleNumberHolder();
        }

        /* synthetic */ FlacTimestampSeeker(FlacStreamMetadata flacStreamMetadata, int i, byte b2) {
            this(flacStreamMetadata, i);
        }

        private long a(ExtractorInput extractorInput) {
            while (extractorInput.b() < extractorInput.d() - 6 && !FlacFrameReader.a(extractorInput, this.a, this.b, this.c)) {
                extractorInput.c(1);
            }
            if (extractorInput.b() < extractorInput.d() - 6) {
                return this.c.a;
            }
            extractorInput.c((int) (extractorInput.d() - extractorInput.b()));
            return this.a.j;
        }

        public final BinarySearchSeeker.TimestampSearchResult a(ExtractorInput extractorInput, long j) {
            long c2 = extractorInput.c();
            long a2 = a(extractorInput);
            long b2 = extractorInput.b();
            extractorInput.c(Math.max(6, this.a.c));
            long a3 = a(extractorInput);
            return (a2 > j || a3 <= j) ? a3 <= j ? BinarySearchSeeker.TimestampSearchResult.b(a3, extractorInput.b()) : BinarySearchSeeker.TimestampSearchResult.a(a2, c2) : BinarySearchSeeker.TimestampSearchResult.a(b2);
        }

        public final void a() {
            BinarySearchSeeker$TimestampSeeker$$CC.a();
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FlacBinarySearchSeeker(com.google.android.exoplayer2.extractor.FlacStreamMetadata r15, int r16, long r17, long r19) {
        /*
            r14 = this;
            r0 = r15
            r15.getClass()
            com.google.android.exoplayer2.extractor.BinarySearchSeeker$SeekTimestampConverter r1 = com.google.android.exoplayer2.extractor.flac.FlacBinarySearchSeeker$$Lambda$0.a((com.google.android.exoplayer2.extractor.FlacStreamMetadata) r15)
            com.google.android.exoplayer2.extractor.flac.FlacBinarySearchSeeker$FlacTimestampSeeker r2 = new com.google.android.exoplayer2.extractor.flac.FlacBinarySearchSeeker$FlacTimestampSeeker
            r3 = 0
            r4 = r16
            r2.<init>(r15, r4, r3)
            long r3 = r15.a()
            long r5 = r0.j
            int r7 = r0.d
            if (r7 <= 0) goto L_0x0029
            int r7 = r0.d
            long r7 = (long) r7
            int r9 = r0.c
            long r9 = (long) r9
            long r7 = r7 + r9
            r9 = 2
            long r7 = r7 / r9
            r9 = 1
        L_0x0026:
            long r7 = r7 + r9
            r11 = r7
            goto L_0x0049
        L_0x0029:
            int r7 = r0.a
            int r8 = r0.b
            if (r7 != r8) goto L_0x0037
            int r7 = r0.a
            if (r7 <= 0) goto L_0x0037
            int r7 = r0.a
            long r7 = (long) r7
            goto L_0x0039
        L_0x0037:
            r7 = 4096(0x1000, double:2.0237E-320)
        L_0x0039:
            int r9 = r0.g
            long r9 = (long) r9
            long r7 = r7 * r9
            int r9 = r0.h
            long r9 = (long) r9
            long r7 = r7 * r9
            r9 = 8
            long r7 = r7 / r9
            r9 = 64
            goto L_0x0026
        L_0x0049:
            r7 = 6
            int r0 = r0.c
            int r13 = java.lang.Math.max(r7, r0)
            r0 = r14
            r7 = r17
            r9 = r19
            r0.<init>(r1, r2, r3, r5, r7, r9, r11, r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.flac.FlacBinarySearchSeeker.<init>(com.google.android.exoplayer2.extractor.FlacStreamMetadata, int, long, long):void");
    }
}
