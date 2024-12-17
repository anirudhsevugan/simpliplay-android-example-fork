package com.google.android.exoplayer2.source.dash.manifest;

import android.net.Uri;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.dash.DashSegmentIndex;
import com.google.android.exoplayer2.source.dash.manifest.SegmentBase;
import com.google.android.exoplayer2.util.Util;
import java.util.Collections;
import java.util.List;

public abstract class Representation {
    public final Format a;
    public final String b;
    public final long c;
    public final List d;
    public final RangedUri e;

    public class MultiSegmentRepresentation extends Representation implements DashSegmentIndex {
        private SegmentBase.MultiSegmentBase f;

        public MultiSegmentRepresentation(Format format, String str, SegmentBase.MultiSegmentBase multiSegmentBase, List list) {
            super(format, str, multiSegmentBase, list, (byte) 0);
            this.f = multiSegmentBase;
        }

        public final long a() {
            return this.f.d;
        }

        public final long a(long j) {
            return this.f.a(j);
        }

        public final long a(long j, long j2) {
            return this.f.a(j, j2);
        }

        public final long b(long j, long j2) {
            return this.f.b(j, j2);
        }

        public final RangedUri b(long j) {
            return this.f.a((Representation) this, j);
        }

        public final boolean b() {
            return this.f.a();
        }

        public final long c(long j) {
            return this.f.b(j);
        }

        public final long c(long j, long j2) {
            return this.f.c(j, j2);
        }

        public final RangedUri c() {
            return null;
        }

        public final long d(long j, long j2) {
            return this.f.d(j, j2);
        }

        public final DashSegmentIndex d() {
            return this;
        }

        public final long e(long j, long j2) {
            SegmentBase.MultiSegmentBase multiSegmentBase = this.f;
            if (multiSegmentBase.f != null) {
                return -9223372036854775807L;
            }
            long c = multiSegmentBase.c(j, j2) + multiSegmentBase.d(j, j2);
            return (multiSegmentBase.a(c) + multiSegmentBase.b(c, j)) - multiSegmentBase.g;
        }
    }

    public class SingleSegmentRepresentation extends Representation {
        private final RangedUri f;
        private final SingleSegmentIndex g;

        public SingleSegmentRepresentation(Format format, String str, SegmentBase.SingleSegmentBase singleSegmentBase, List list) {
            super(format, str, singleSegmentBase, list, (byte) 0);
            Uri.parse(str);
            SingleSegmentIndex singleSegmentIndex = null;
            RangedUri rangedUri = singleSegmentBase.e <= 0 ? null : new RangedUri((String) null, singleSegmentBase.d, singleSegmentBase.e);
            this.f = rangedUri;
            this.g = rangedUri == null ? new SingleSegmentIndex(new RangedUri((String) null, 0, -1)) : singleSegmentIndex;
        }

        public final RangedUri c() {
            return this.f;
        }

        public final DashSegmentIndex d() {
            return this.g;
        }
    }

    private Representation(Format format, String str, SegmentBase segmentBase, List list) {
        this.a = format;
        this.b = str;
        this.d = list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
        this.e = segmentBase.a(this);
        this.c = Util.b(segmentBase.c, 1000000, segmentBase.b);
    }

    /* synthetic */ Representation(Format format, String str, SegmentBase segmentBase, List list, byte b2) {
        this(format, str, segmentBase, list);
    }

    public static Representation a(Format format, String str, SegmentBase segmentBase, List list) {
        if (segmentBase instanceof SegmentBase.SingleSegmentBase) {
            return new SingleSegmentRepresentation(format, str, (SegmentBase.SingleSegmentBase) segmentBase, list);
        }
        if (segmentBase instanceof SegmentBase.MultiSegmentBase) {
            return new MultiSegmentRepresentation(format, str, (SegmentBase.MultiSegmentBase) segmentBase, list);
        }
        throw new IllegalArgumentException("segmentBase must be of type SingleSegmentBase or MultiSegmentBase");
    }

    public abstract RangedUri c();

    public abstract DashSegmentIndex d();
}
