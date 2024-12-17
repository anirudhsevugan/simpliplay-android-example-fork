package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.extractor.BinarySearchSeeker;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;
import com.google.appinventor.components.runtime.util.Ev3Constants;

final class PsBinarySearchSeeker extends BinarySearchSeeker {

    final class PsScrSeeker implements BinarySearchSeeker.TimestampSeeker {
        private final TimestampAdjuster a;
        private final ParsableByteArray b;

        private PsScrSeeker(TimestampAdjuster timestampAdjuster) {
            this.a = timestampAdjuster;
            this.b = new ParsableByteArray();
        }

        /* synthetic */ PsScrSeeker(TimestampAdjuster timestampAdjuster, byte b2) {
            this(timestampAdjuster);
        }

        public final BinarySearchSeeker.TimestampSearchResult a(ExtractorInput extractorInput, long j) {
            int a2;
            long c = extractorInput.c();
            int min = (int) Math.min(20000, extractorInput.d() - c);
            this.b.a(min);
            extractorInput.d(this.b.a, 0, min);
            ParsableByteArray parsableByteArray = this.b;
            int i = -1;
            long j2 = -9223372036854775807L;
            int i2 = -1;
            while (parsableByteArray.a() >= 4) {
                if (PsBinarySearchSeeker.a(parsableByteArray.a, parsableByteArray.b) != 442) {
                    parsableByteArray.e(1);
                } else {
                    parsableByteArray.e(4);
                    long a3 = PsDurationReader.a(parsableByteArray);
                    if (a3 != -9223372036854775807L) {
                        long b2 = this.a.b(a3);
                        if (b2 > j) {
                            return j2 == -9223372036854775807L ? BinarySearchSeeker.TimestampSearchResult.a(b2, c) : BinarySearchSeeker.TimestampSearchResult.a(c + ((long) i2));
                        }
                        if (100000 + b2 > j) {
                            return BinarySearchSeeker.TimestampSearchResult.a(c + ((long) parsableByteArray.b));
                        }
                        i2 = parsableByteArray.b;
                        j2 = b2;
                    }
                    int i3 = parsableByteArray.c;
                    if (parsableByteArray.a() >= 10) {
                        parsableByteArray.e(9);
                        int c2 = parsableByteArray.c() & 7;
                        if (parsableByteArray.a() >= c2) {
                            parsableByteArray.e(c2);
                            if (parsableByteArray.a() >= 4) {
                                if (PsBinarySearchSeeker.a(parsableByteArray.a, parsableByteArray.b) == 443) {
                                    parsableByteArray.e(4);
                                    int d = parsableByteArray.d();
                                    if (parsableByteArray.a() >= d) {
                                        parsableByteArray.e(d);
                                    }
                                }
                                while (true) {
                                    if (parsableByteArray.a() < 4 || (a2 = PsBinarySearchSeeker.a(parsableByteArray.a, parsableByteArray.b)) == 442 || a2 == 441 || (a2 >>> 8) != 1) {
                                        break;
                                    }
                                    parsableByteArray.e(4);
                                    if (parsableByteArray.a() < 2) {
                                        break;
                                    }
                                    parsableByteArray.d(Math.min(parsableByteArray.c, parsableByteArray.b + parsableByteArray.d()));
                                }
                                i = parsableByteArray.b;
                            }
                        }
                    }
                    parsableByteArray.d(i3);
                    i = parsableByteArray.b;
                }
            }
            return j2 != -9223372036854775807L ? BinarySearchSeeker.TimestampSearchResult.b(j2, c + ((long) i)) : BinarySearchSeeker.TimestampSearchResult.a;
        }

        public final void a() {
            this.b.a(Util.f, 0);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PsBinarySearchSeeker(TimestampAdjuster timestampAdjuster, long j, long j2) {
        super(new BinarySearchSeeker.DefaultSeekTimestampConverter(), new PsScrSeeker(timestampAdjuster, (byte) 0), j, j + 1, 0, j2, 188, 1000);
        TimestampAdjuster timestampAdjuster2 = timestampAdjuster;
    }

    static /* synthetic */ int a(byte[] bArr, int i) {
        return (bArr[i + 3] & Ev3Constants.Opcode.TST) | ((bArr[i] & Ev3Constants.Opcode.TST) << 24) | ((bArr[i + 1] & Ev3Constants.Opcode.TST) << 16) | ((bArr[i + 2] & Ev3Constants.Opcode.TST) << 8);
    }
}
