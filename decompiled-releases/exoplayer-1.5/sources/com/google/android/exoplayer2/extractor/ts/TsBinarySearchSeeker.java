package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.extractor.BinarySearchSeeker;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;

final class TsBinarySearchSeeker extends BinarySearchSeeker {

    final class TsPcrSeeker implements BinarySearchSeeker.TimestampSeeker {
        private final TimestampAdjuster a;
        private final ParsableByteArray b = new ParsableByteArray();
        private final int c;
        private final int d;

        public TsPcrSeeker(int i, TimestampAdjuster timestampAdjuster, int i2) {
            this.c = i;
            this.a = timestampAdjuster;
            this.d = i2;
        }

        public final BinarySearchSeeker.TimestampSearchResult a(ExtractorInput extractorInput, long j) {
            int a2;
            int i;
            long c2 = extractorInput.c();
            int min = (int) Math.min((long) this.d, extractorInput.d() - c2);
            this.b.a(min);
            extractorInput.d(this.b.a, 0, min);
            ParsableByteArray parsableByteArray = this.b;
            int i2 = parsableByteArray.c;
            long j2 = -1;
            long j3 = -1;
            long j4 = -9223372036854775807L;
            while (parsableByteArray.a() >= 188 && (i = a2 + 188) <= i2) {
                long a3 = TsUtil.a(parsableByteArray, (a2 = TsUtil.a(parsableByteArray.a, parsableByteArray.b, i2)), this.c);
                if (a3 != -9223372036854775807L) {
                    long b2 = this.a.b(a3);
                    if (b2 > j) {
                        return j4 == -9223372036854775807L ? BinarySearchSeeker.TimestampSearchResult.a(b2, c2) : BinarySearchSeeker.TimestampSearchResult.a(c2 + j3);
                    }
                    if (100000 + b2 > j) {
                        return BinarySearchSeeker.TimestampSearchResult.a(c2 + ((long) a2));
                    }
                    j3 = (long) a2;
                    j4 = b2;
                }
                parsableByteArray.d(i);
                j2 = (long) i;
            }
            return j4 != -9223372036854775807L ? BinarySearchSeeker.TimestampSearchResult.b(j4, c2 + j2) : BinarySearchSeeker.TimestampSearchResult.a;
        }

        public final void a() {
            this.b.a(Util.f, 0);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TsBinarySearchSeeker(TimestampAdjuster timestampAdjuster, long j, long j2, int i, int i2) {
        super(new BinarySearchSeeker.DefaultSeekTimestampConverter(), new TsPcrSeeker(i, timestampAdjuster, i2), j, j + 1, 0, j2, 188, 940);
        TimestampAdjuster timestampAdjuster2 = timestampAdjuster;
    }
}
