package com.google.android.exoplayer2.source.hls.playlist;

import com.dreamers.exoplayercore.repack.bG;
import com.google.android.exoplayer2.drm.DrmInitData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class HlsMediaPlaylist extends HlsPlaylist {
    public final int a;
    public final long b;
    public final boolean c;
    public final long d;
    public final boolean e;
    public final int f;
    public final long g;
    public final int h;
    public final long i;
    public final long j;
    public final boolean k;
    public final boolean l;
    public final DrmInitData m;
    public final List n;
    public final List o;
    public final Map p;
    public final long q;
    public final ServerControl r;

    public final class Part extends SegmentBase {
        public final boolean a;
        public final boolean b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Part(String str, Segment segment, long j, int i, long j2, DrmInitData drmInitData, String str2, String str3, long j3, long j4, boolean z, boolean z2, boolean z3) {
            super(str, segment, j, i, j2, drmInitData, str2, str3, j3, j4, z, (byte) 0);
            this.a = z2;
            this.b = z3;
        }
    }

    public final class RenditionReport {
        public final long a;
        public final int b;

        public RenditionReport(long j, int i) {
            this.a = j;
            this.b = i;
        }
    }

    public final class Segment extends SegmentBase {
        public final List a;
        private String b;

        public Segment(String str, long j, long j2, String str2, String str3) {
            this(str, (Segment) null, "", 0, -1, -9223372036854775807L, (DrmInitData) null, str2, str3, j, j2, false, bG.g());
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Segment(String str, Segment segment, String str2, long j, int i, long j2, DrmInitData drmInitData, String str3, String str4, long j3, long j4, boolean z, List list) {
            super(str, segment, j, i, j2, drmInitData, str3, str4, j3, j4, z, (byte) 0);
            this.b = str2;
            this.a = bG.a((Collection) list);
        }

        public final Segment a(long j, int i) {
            ArrayList arrayList = new ArrayList();
            long j2 = j;
            for (int i2 = 0; i2 < this.a.size(); i2++) {
                Part part = (Part) this.a.get(i2);
                arrayList.add(new Part(part.c, part.d, part.e, i, j2, part.h, part.i, part.j, part.k, part.l, part.m, part.a, part.b));
                j2 += part.e;
            }
            return new Segment(this.c, this.d, this.b, this.e, i, j, this.h, this.i, this.j, this.k, this.l, this.m, arrayList);
        }
    }

    public class SegmentBase implements Comparable {
        public final String c;
        public final Segment d;
        public final long e;
        public final int f;
        public final long g;
        public final DrmInitData h;
        public final String i;
        public final String j;
        public final long k;
        public final long l;
        public final boolean m;

        private SegmentBase(String str, Segment segment, long j2, int i2, long j3, DrmInitData drmInitData, String str2, String str3, long j4, long j5, boolean z) {
            this.c = str;
            this.d = segment;
            this.e = j2;
            this.f = i2;
            this.g = j3;
            this.h = drmInitData;
            this.i = str2;
            this.j = str3;
            this.k = j4;
            this.l = j5;
            this.m = z;
        }

        /* synthetic */ SegmentBase(String str, Segment segment, long j2, int i2, long j3, DrmInitData drmInitData, String str2, String str3, long j4, long j5, boolean z, byte b) {
            this(str, segment, j2, i2, j3, drmInitData, str2, str3, j4, j5, z);
        }

        public /* synthetic */ int compareTo(Object obj) {
            Long l2 = (Long) obj;
            if (this.g > l2.longValue()) {
                return 1;
            }
            return this.g < l2.longValue() ? -1 : 0;
        }
    }

    public final class ServerControl {
        public final long a;
        public final boolean b;
        public final long c;
        public final long d;
        public final boolean e;

        public ServerControl(long j, boolean z, long j2, long j3, boolean z2) {
            this.a = j;
            this.b = z;
            this.c = j2;
            this.d = j3;
            this.e = z2;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x007d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public HlsMediaPlaylist(int r11, java.lang.String r12, java.util.List r13, long r14, boolean r16, long r17, boolean r19, int r20, long r21, int r23, long r24, long r26, boolean r28, boolean r29, boolean r30, com.google.android.exoplayer2.drm.DrmInitData r31, java.util.List r32, java.util.List r33, com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist.ServerControl r34, java.util.Map r35) {
        /*
            r10 = this;
            r0 = r10
            r1 = r14
            r3 = r12
            r4 = r13
            r5 = r28
            r10.<init>(r12, r13, r5)
            r3 = r11
            r0.a = r3
            r3 = r17
            r0.d = r3
            r3 = r16
            r0.c = r3
            r3 = r19
            r0.e = r3
            r3 = r20
            r0.f = r3
            r3 = r21
            r0.g = r3
            r3 = r23
            r0.h = r3
            r3 = r24
            r0.i = r3
            r3 = r26
            r0.j = r3
            r3 = r29
            r0.k = r3
            r3 = r30
            r0.l = r3
            r3 = r31
            r0.m = r3
            com.dreamers.exoplayercore.repack.bG r3 = com.dreamers.exoplayercore.repack.bG.a((java.util.Collection) r32)
            r0.n = r3
            com.dreamers.exoplayercore.repack.bG r3 = com.dreamers.exoplayercore.repack.bG.a((java.util.Collection) r33)
            r0.o = r3
            com.dreamers.exoplayercore.repack.bM r3 = com.dreamers.exoplayercore.repack.bM.a(r35)
            r0.p = r3
            boolean r3 = r33.isEmpty()
            r4 = 0
            if (r3 != 0) goto L_0x0060
            java.lang.Object r3 = com.dreamers.exoplayercore.repack.C0000a.b((java.lang.Iterable) r33)
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$Part r3 = (com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist.Part) r3
            long r6 = r3.g
            long r8 = r3.e
        L_0x005c:
            long r6 = r6 + r8
            r0.q = r6
            goto L_0x0073
        L_0x0060:
            boolean r3 = r32.isEmpty()
            if (r3 != 0) goto L_0x0071
            java.lang.Object r3 = com.dreamers.exoplayercore.repack.C0000a.b((java.lang.Iterable) r32)
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$Segment r3 = (com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist.Segment) r3
            long r6 = r3.g
            long r8 = r3.e
            goto L_0x005c
        L_0x0071:
            r0.q = r4
        L_0x0073:
            r6 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r3 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r3 != 0) goto L_0x007d
            goto L_0x008f
        L_0x007d:
            int r3 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r3 < 0) goto L_0x0088
            long r3 = r0.q
            long r6 = java.lang.Math.min(r3, r14)
            goto L_0x008f
        L_0x0088:
            long r6 = r0.q
            long r6 = r6 + r1
            long r6 = java.lang.Math.max(r4, r6)
        L_0x008f:
            r0.b = r6
            r1 = r34
            r0.r = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist.<init>(int, java.lang.String, java.util.List, long, boolean, long, boolean, int, long, int, long, long, boolean, boolean, boolean, com.google.android.exoplayer2.drm.DrmInitData, java.util.List, java.util.List, com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$ServerControl, java.util.Map):void");
    }

    public final long a() {
        return this.d + this.q;
    }

    public final /* bridge */ /* synthetic */ Object a(List list) {
        return this;
    }
}
