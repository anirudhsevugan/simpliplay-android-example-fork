package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

public abstract class BinarySearchSeeker {
    public BinarySearchSeekMap a;
    private TimestampSeeker b;
    private SeekOperationParams c;
    private final int d;

    public class BinarySearchSeekMap implements SeekMap {
        final SeekTimestampConverter a;
        private final long b;
        /* access modifiers changed from: private */
        public final long c;
        /* access modifiers changed from: private */
        public final long d;
        /* access modifiers changed from: private */
        public final long e;
        /* access modifiers changed from: private */
        public final long f;

        public BinarySearchSeekMap(SeekTimestampConverter seekTimestampConverter, long j, long j2, long j3, long j4, long j5) {
            this.a = seekTimestampConverter;
            this.b = j;
            this.c = j2;
            this.d = j3;
            this.e = j4;
            this.f = j5;
        }

        static /* synthetic */ long c() {
            return 0;
        }

        public final SeekMap.SeekPoints a(long j) {
            return new SeekMap.SeekPoints(new SeekPoint(j, SeekOperationParams.a(this.a.a(j), 0, this.c, this.d, this.e, this.f)));
        }

        public final boolean a() {
            return true;
        }

        public final long b() {
            return this.b;
        }
    }

    public final class DefaultSeekTimestampConverter implements SeekTimestampConverter {
        public final long a(long j) {
            return j;
        }
    }

    public class SeekOperationParams {
        /* access modifiers changed from: private */
        public final long a;
        /* access modifiers changed from: private */
        public final long b;
        private final long c;
        private long d;
        private long e;
        /* access modifiers changed from: private */
        public long f;
        /* access modifiers changed from: private */
        public long g;
        /* access modifiers changed from: private */
        public long h;

        protected SeekOperationParams(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
            this.a = j;
            this.b = j2;
            this.d = j3;
            this.e = j4;
            this.f = j5;
            this.g = j6;
            this.c = j7;
            this.h = a(j2, j3, j4, j5, j6, j7);
        }

        protected static long a(long j, long j2, long j3, long j4, long j5, long j6) {
            if (j4 + 1 >= j5 || j2 + 1 >= j3) {
                return j4;
            }
            long j7 = (long) (((float) (j - j2)) * (((float) (j5 - j4)) / ((float) (j3 - j2))));
            return Util.a(((j7 + j4) - j6) - (j7 / 20), j4, j5 - 1);
        }

        private void a() {
            this.h = a(this.b, this.d, this.e, this.f, this.g, this.c);
        }

        static /* synthetic */ void a(SeekOperationParams seekOperationParams, long j, long j2) {
            seekOperationParams.e = j;
            seekOperationParams.g = j2;
            seekOperationParams.a();
        }

        static /* synthetic */ void b(SeekOperationParams seekOperationParams, long j, long j2) {
            seekOperationParams.d = j;
            seekOperationParams.f = j2;
            seekOperationParams.a();
        }
    }

    public interface SeekTimestampConverter {
        long a(long j);
    }

    public final class TimestampSearchResult {
        public static final TimestampSearchResult a = new TimestampSearchResult(-3, -9223372036854775807L, -1);
        /* access modifiers changed from: private */
        public final int b;
        /* access modifiers changed from: private */
        public final long c;
        /* access modifiers changed from: private */
        public final long d;

        private TimestampSearchResult(int i, long j, long j2) {
            this.b = i;
            this.c = j;
            this.d = j2;
        }

        public static TimestampSearchResult a(long j) {
            return new TimestampSearchResult(0, -9223372036854775807L, j);
        }

        public static TimestampSearchResult a(long j, long j2) {
            return new TimestampSearchResult(-1, j, j2);
        }

        public static TimestampSearchResult b(long j, long j2) {
            return new TimestampSearchResult(-2, j, j2);
        }
    }

    public interface TimestampSeeker {
        TimestampSearchResult a(ExtractorInput extractorInput, long j);

        void a();
    }

    public BinarySearchSeeker(SeekTimestampConverter seekTimestampConverter, TimestampSeeker timestampSeeker, long j, long j2, long j3, long j4, long j5, int i) {
        this.b = timestampSeeker;
        this.d = i;
        this.a = new BinarySearchSeekMap(seekTimestampConverter, j, j2, j3, j4, j5);
    }

    private static int a(ExtractorInput extractorInput, long j, PositionHolder positionHolder) {
        if (j == extractorInput.c()) {
            return 0;
        }
        positionHolder.a = j;
        return 1;
    }

    private static boolean a(ExtractorInput extractorInput, long j) {
        long c2 = j - extractorInput.c();
        if (c2 < 0 || c2 > 262144) {
            return false;
        }
        extractorInput.b((int) c2);
        return true;
    }

    private void b() {
        this.c = null;
        this.b.a();
    }

    public final int a(ExtractorInput extractorInput, PositionHolder positionHolder) {
        while (true) {
            SeekOperationParams seekOperationParams = (SeekOperationParams) Assertions.a((Object) this.c);
            long b2 = seekOperationParams.f;
            long c2 = seekOperationParams.g;
            long d2 = seekOperationParams.h;
            if (c2 - b2 <= ((long) this.d)) {
                b();
                return a(extractorInput, b2, positionHolder);
            } else if (!a(extractorInput, d2)) {
                return a(extractorInput, d2, positionHolder);
            } else {
                extractorInput.a();
                TimestampSearchResult a2 = this.b.a(extractorInput, seekOperationParams.b);
                switch (a2.b) {
                    case -3:
                        b();
                        return a(extractorInput, d2, positionHolder);
                    case -2:
                        SeekOperationParams.b(seekOperationParams, a2.c, a2.d);
                        break;
                    case -1:
                        SeekOperationParams.a(seekOperationParams, a2.c, a2.d);
                        break;
                    case 0:
                        a(extractorInput, a2.d);
                        long unused = a2.d;
                        b();
                        return a(extractorInput, a2.d, positionHolder);
                    default:
                        throw new IllegalStateException("Invalid case");
                }
            }
        }
    }

    public final void a(long j) {
        long j2 = j;
        SeekOperationParams seekOperationParams = this.c;
        if (seekOperationParams == null || seekOperationParams.a != j2) {
            SeekOperationParams seekOperationParams2 = r1;
            SeekOperationParams seekOperationParams3 = new SeekOperationParams(j, this.a.a.a(j2), BinarySearchSeekMap.c(), this.a.c, this.a.d, this.a.e, this.a.f);
            this.c = seekOperationParams2;
        }
    }

    public final boolean a() {
        return this.c != null;
    }
}
