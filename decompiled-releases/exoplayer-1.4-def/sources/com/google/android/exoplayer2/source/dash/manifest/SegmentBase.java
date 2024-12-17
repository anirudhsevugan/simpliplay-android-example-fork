package com.google.android.exoplayer2.source.dash.manifest;

import com.dreamers.exoplayercore.repack.cO;
import com.google.android.exoplayer2.util.Util;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;

public abstract class SegmentBase {
    final RangedUri a;
    final long b;
    final long c;

    public abstract class MultiSegmentBase extends SegmentBase {
        final long d;
        final long e;
        final List f;
        final long g;
        private final long h;
        private final long i;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public MultiSegmentBase(RangedUri rangedUri, long j, long j2, long j3, long j4, List list, long j5, long j6, long j7) {
            super(rangedUri, j, j2);
            this.d = j3;
            this.e = j4;
            this.f = list;
            this.g = j5;
            this.h = j6;
            this.i = j7;
        }

        public final long a(long j) {
            List list = this.f;
            return Util.b(list != null ? ((SegmentTimelineElement) list.get((int) (j - this.d))).a - this.c : (j - this.d) * this.e, 1000000, this.b);
        }

        public final long a(long j, long j2) {
            long j3 = this.d;
            long b = b(j2);
            if (b == 0) {
                return j3;
            }
            if (this.f == null) {
                long j4 = this.d + (j / ((this.e * 1000000) / this.b));
                return j4 < j3 ? j3 : b == -1 ? j4 : Math.min(j4, (j3 + b) - 1);
            }
            long j5 = (b + j3) - 1;
            long j6 = j3;
            while (j6 <= j5) {
                long j7 = ((j5 - j6) / 2) + j6;
                long a = a(j7);
                if (a < j) {
                    j6 = j7 + 1;
                } else if (a <= j) {
                    return j7;
                } else {
                    j5 = j7 - 1;
                }
            }
            return j6 == j3 ? j6 : j5;
        }

        public abstract RangedUri a(Representation representation, long j);

        public boolean a() {
            return this.f != null;
        }

        public abstract long b(long j);

        public final long b(long j, long j2) {
            long j3;
            List list = this.f;
            if (list != null) {
                j3 = ((SegmentTimelineElement) list.get((int) (j - this.d))).b;
            } else {
                long b = b(j2);
                if (b != -1 && j == (this.d + b) - 1) {
                    return j2 - a(j);
                }
                j3 = this.e;
            }
            return (j3 * 1000000) / this.b;
        }

        public final long c(long j, long j2) {
            if (b(j) == -1) {
                long j3 = this.h;
                if (j3 != -9223372036854775807L) {
                    return Math.max(this.d, a((j2 - this.i) - j3, j));
                }
            }
            return this.d;
        }

        public final long d(long j, long j2) {
            long b = b(j);
            return b != -1 ? b : (long) ((int) (a((j2 - this.i) + this.g, j) - c(j, j2)));
        }
    }

    public final class SegmentList extends MultiSegmentBase {
        final List h;

        public SegmentList(RangedUri rangedUri, long j, long j2, long j3, long j4, List list, long j5, List list2, long j6, long j7) {
            super(rangedUri, j, j2, j3, j4, list, j5, j6, j7);
            this.h = list2;
        }

        public final RangedUri a(Representation representation, long j) {
            return (RangedUri) this.h.get((int) (j - this.d));
        }

        public final boolean a() {
            return true;
        }

        public final long b(long j) {
            return (long) this.h.size();
        }
    }

    public final class SegmentTemplate extends MultiSegmentBase {
        final UrlTemplate h;
        final UrlTemplate i;
        private long j;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SegmentTemplate(RangedUri rangedUri, long j2, long j3, long j4, long j5, long j6, List list, long j7, UrlTemplate urlTemplate, UrlTemplate urlTemplate2, long j8, long j9) {
            super(rangedUri, j2, j3, j4, j6, list, j7, j8, j9);
            this.h = urlTemplate;
            this.i = urlTemplate2;
            this.j = j5;
        }

        public final RangedUri a(Representation representation) {
            UrlTemplate urlTemplate = this.h;
            return urlTemplate != null ? new RangedUri(urlTemplate.a(representation.a.a, 0, representation.a.h, 0), 0, -1) : super.a(representation);
        }

        public final RangedUri a(Representation representation, long j2) {
            Representation representation2 = representation;
            return new RangedUri(this.i.a(representation2.a.a, j2, representation2.a.h, this.f != null ? ((SegmentTimelineElement) this.f.get((int) (j2 - this.d))).a : (j2 - this.d) * this.e), 0, -1);
        }

        public final long b(long j2) {
            if (this.f != null) {
                return (long) this.f.size();
            }
            long j3 = this.j;
            if (j3 != -1) {
                return (j3 - this.d) + 1;
            }
            if (j2 != -9223372036854775807L) {
                return cO.a(BigInteger.valueOf(j2).multiply(BigInteger.valueOf(this.b)), BigInteger.valueOf(this.e).multiply(BigInteger.valueOf(1000000)), RoundingMode.CEILING).longValue();
            }
            return -1;
        }
    }

    public final class SegmentTimelineElement {
        final long a;
        final long b;

        public SegmentTimelineElement(long j, long j2) {
            this.a = j;
            this.b = j2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                SegmentTimelineElement segmentTimelineElement = (SegmentTimelineElement) obj;
                return this.a == segmentTimelineElement.a && this.b == segmentTimelineElement.b;
            }
        }

        public final int hashCode() {
            return (((int) this.a) * 31) + ((int) this.b);
        }
    }

    public class SingleSegmentBase extends SegmentBase {
        final long d;
        final long e;

        public SingleSegmentBase() {
            this((RangedUri) null, 1, 0, 0, 0);
        }

        public SingleSegmentBase(RangedUri rangedUri, long j, long j2, long j3, long j4) {
            super(rangedUri, j, j2);
            this.d = j3;
            this.e = j4;
        }
    }

    public SegmentBase(RangedUri rangedUri, long j, long j2) {
        this.a = rangedUri;
        this.b = j;
        this.c = j2;
    }

    public RangedUri a(Representation representation) {
        return this.a;
    }
}
