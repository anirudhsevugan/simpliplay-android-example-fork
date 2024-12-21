package com.google.android.exoplayer2.source;

import android.os.Looper;
import androidx.core.location.LocationRequestCompat;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.TrackOutput$$CC;
import com.google.android.exoplayer2.source.SampleDataQueue;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import gnu.expr.Declaration;
import java.io.EOFException;

public class SampleQueue implements TrackOutput {
    private boolean A = true;
    private boolean B;
    private Format C;
    private Format D;
    private int E;
    private boolean F;
    private boolean G;
    private long H;
    public final SampleDataQueue a;
    public UpstreamFormatChangedListener b;
    public int c;
    public long d = Long.MIN_VALUE;
    public boolean e;
    private final SampleExtrasHolder f = new SampleExtrasHolder();
    private final SpannedData g = new SpannedData(SampleQueue$$Lambda$0.a);
    private final DrmSessionManager h;
    private final DrmSessionEventListener.EventDispatcher i;
    private final Looper j;
    private Format k;
    private DrmSession l;
    private int m = 1000;
    private int[] n = new int[1000];
    private long[] o = new long[1000];
    private int[] p = new int[1000];
    private int[] q = new int[1000];
    private long[] r = new long[1000];
    private TrackOutput.CryptoData[] s = new TrackOutput.CryptoData[1000];
    private int t;
    private int u;
    private int v;
    private long w = Long.MIN_VALUE;
    private long x = Long.MIN_VALUE;
    private boolean y;
    private boolean z = true;

    final class SampleExtrasHolder {
        public int a;
        public long b;
        public TrackOutput.CryptoData c;

        SampleExtrasHolder() {
        }
    }

    final class SharedSampleMetadata {
        public final Format a;
        public final DrmSessionManager.DrmSessionReference b;

        private SharedSampleMetadata(Format format, DrmSessionManager.DrmSessionReference drmSessionReference) {
            this.a = format;
            this.b = drmSessionReference;
        }

        /* synthetic */ SharedSampleMetadata(Format format, DrmSessionManager.DrmSessionReference drmSessionReference, byte b2) {
            this(format, drmSessionReference);
        }
    }

    public interface UpstreamFormatChangedListener {
        void j();
    }

    public SampleQueue(Allocator allocator, Looper looper, DrmSessionManager drmSessionManager, DrmSessionEventListener.EventDispatcher eventDispatcher) {
        this.j = looper;
        this.h = drmSessionManager;
        this.i = eventDispatcher;
        this.a = new SampleDataQueue(allocator);
    }

    private int a(int i2, int i3, long j2, boolean z2) {
        int i4 = -1;
        for (int i5 = 0; i5 < i3; i5++) {
            long j3 = this.r[i2];
            if (j3 > j2) {
                return i4;
            }
            if (!z2 || (this.q[i2] & 1) != 0) {
                if (j3 == j2) {
                    return i5;
                }
                i4 = i5;
            }
            i2++;
            if (i2 == this.m) {
                i2 = 0;
            }
        }
        return i4;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002a, code lost:
        return -3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized int a(com.google.android.exoplayer2.FormatHolder r5, com.google.android.exoplayer2.decoder.DecoderInputBuffer r6, boolean r7, boolean r8, com.google.android.exoplayer2.source.SampleQueue.SampleExtrasHolder r9) {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 0
            r6.d = r0     // Catch:{ all -> 0x0088 }
            boolean r0 = r4.r()     // Catch:{ all -> 0x0088 }
            r1 = -5
            r2 = -3
            r3 = -4
            if (r0 != 0) goto L_0x0030
            if (r8 != 0) goto L_0x002b
            boolean r8 = r4.y     // Catch:{ all -> 0x0088 }
            if (r8 == 0) goto L_0x0014
            goto L_0x002b
        L_0x0014:
            com.google.android.exoplayer2.Format r6 = r4.D     // Catch:{ all -> 0x0088 }
            if (r6 == 0) goto L_0x0029
            if (r7 != 0) goto L_0x001e
            com.google.android.exoplayer2.Format r7 = r4.k     // Catch:{ all -> 0x0088 }
            if (r6 == r7) goto L_0x0029
        L_0x001e:
            java.lang.Object r6 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r6)     // Catch:{ all -> 0x0088 }
            com.google.android.exoplayer2.Format r6 = (com.google.android.exoplayer2.Format) r6     // Catch:{ all -> 0x0088 }
            r4.a((com.google.android.exoplayer2.Format) r6, (com.google.android.exoplayer2.FormatHolder) r5)     // Catch:{ all -> 0x0088 }
            monitor-exit(r4)
            return r1
        L_0x0029:
            monitor-exit(r4)
            return r2
        L_0x002b:
            r5 = 4
            r6.a = r5     // Catch:{ all -> 0x0088 }
            monitor-exit(r4)
            return r3
        L_0x0030:
            com.google.android.exoplayer2.source.SpannedData r8 = r4.g     // Catch:{ all -> 0x0088 }
            int r0 = r4.e()     // Catch:{ all -> 0x0088 }
            java.lang.Object r8 = r8.a(r0)     // Catch:{ all -> 0x0088 }
            com.google.android.exoplayer2.source.SampleQueue$SharedSampleMetadata r8 = (com.google.android.exoplayer2.source.SampleQueue.SharedSampleMetadata) r8     // Catch:{ all -> 0x0088 }
            com.google.android.exoplayer2.Format r8 = r8.a     // Catch:{ all -> 0x0088 }
            if (r7 != 0) goto L_0x0083
            com.google.android.exoplayer2.Format r7 = r4.k     // Catch:{ all -> 0x0088 }
            if (r8 == r7) goto L_0x0045
            goto L_0x0083
        L_0x0045:
            int r5 = r4.v     // Catch:{ all -> 0x0088 }
            int r5 = r4.i(r5)     // Catch:{ all -> 0x0088 }
            boolean r7 = r4.f(r5)     // Catch:{ all -> 0x0088 }
            if (r7 != 0) goto L_0x0056
            r5 = 1
            r6.d = r5     // Catch:{ all -> 0x0088 }
            monitor-exit(r4)
            return r2
        L_0x0056:
            int[] r7 = r4.q     // Catch:{ all -> 0x0088 }
            r7 = r7[r5]     // Catch:{ all -> 0x0088 }
            r6.a = r7     // Catch:{ all -> 0x0088 }
            long[] r7 = r4.r     // Catch:{ all -> 0x0088 }
            r0 = r7[r5]     // Catch:{ all -> 0x0088 }
            r6.e = r0     // Catch:{ all -> 0x0088 }
            long r7 = r6.e     // Catch:{ all -> 0x0088 }
            long r0 = r4.d     // Catch:{ all -> 0x0088 }
            int r2 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r2 >= 0) goto L_0x006f
            r7 = -2147483648(0xffffffff80000000, float:-0.0)
            r6.a_(r7)     // Catch:{ all -> 0x0088 }
        L_0x006f:
            int[] r6 = r4.p     // Catch:{ all -> 0x0088 }
            r6 = r6[r5]     // Catch:{ all -> 0x0088 }
            r9.a = r6     // Catch:{ all -> 0x0088 }
            long[] r6 = r4.o     // Catch:{ all -> 0x0088 }
            r7 = r6[r5]     // Catch:{ all -> 0x0088 }
            r9.b = r7     // Catch:{ all -> 0x0088 }
            com.google.android.exoplayer2.extractor.TrackOutput$CryptoData[] r6 = r4.s     // Catch:{ all -> 0x0088 }
            r5 = r6[r5]     // Catch:{ all -> 0x0088 }
            r9.c = r5     // Catch:{ all -> 0x0088 }
            monitor-exit(r4)
            return r3
        L_0x0083:
            r4.a((com.google.android.exoplayer2.Format) r8, (com.google.android.exoplayer2.FormatHolder) r5)     // Catch:{ all -> 0x0088 }
            monitor-exit(r4)
            return r1
        L_0x0088:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.SampleQueue.a(com.google.android.exoplayer2.FormatHolder, com.google.android.exoplayer2.decoder.DecoderInputBuffer, boolean, boolean, com.google.android.exoplayer2.source.SampleQueue$SampleExtrasHolder):int");
    }

    public static SampleQueue a(Allocator allocator) {
        return new SampleQueue(allocator, (Looper) null, (DrmSessionManager) null, (DrmSessionEventListener.EventDispatcher) null);
    }

    public static SampleQueue a(Allocator allocator, Looper looper, DrmSessionManager drmSessionManager, DrmSessionEventListener.EventDispatcher eventDispatcher) {
        return new SampleQueue(allocator, (Looper) Assertions.b((Object) looper), (DrmSessionManager) Assertions.b((Object) drmSessionManager), (DrmSessionEventListener.EventDispatcher) Assertions.b((Object) eventDispatcher));
    }

    private synchronized void a(long j2, int i2, long j3, int i3, TrackOutput.CryptoData cryptoData) {
        int i4 = this.t;
        if (i4 > 0) {
            int i5 = i(i4 - 1);
            Assertions.a(this.o[i5] + ((long) this.p[i5]) <= j3);
        }
        this.y = (536870912 & i2) != 0;
        this.x = Math.max(this.x, j2);
        int i6 = i(this.t);
        this.r[i6] = j2;
        this.o[i6] = j3;
        this.p[i6] = i3;
        this.q[i6] = i2;
        this.s[i6] = cryptoData;
        this.n[i6] = this.E;
        if (this.g.c() || !((SharedSampleMetadata) this.g.a()).a.equals(this.D)) {
            DrmSessionManager drmSessionManager = this.h;
            DrmSessionManager.DrmSessionReference a2 = drmSessionManager != null ? drmSessionManager.a((Looper) Assertions.b((Object) this.j), this.i, this.D) : DrmSessionManager.DrmSessionReference.e;
            SpannedData spannedData = this.g;
            int b2 = b();
            SharedSampleMetadata sharedSampleMetadata = new SharedSampleMetadata((Format) Assertions.b((Object) this.D), a2, (byte) 0);
            if (spannedData.a == -1) {
                Assertions.b(spannedData.b.size() == 0);
                spannedData.a = 0;
            }
            if (spannedData.b.size() > 0) {
                int keyAt = spannedData.b.keyAt(spannedData.b.size() - 1);
                Assertions.a(b2 >= keyAt);
                if (keyAt == b2) {
                    spannedData.c.a(spannedData.b.valueAt(spannedData.b.size() - 1));
                }
            }
            spannedData.b.append(b2, sharedSampleMetadata);
        }
        int i7 = this.t + 1;
        this.t = i7;
        int i8 = this.m;
        if (i7 == i8) {
            int i9 = i8 + 1000;
            int[] iArr = new int[i9];
            long[] jArr = new long[i9];
            long[] jArr2 = new long[i9];
            int[] iArr2 = new int[i9];
            int[] iArr3 = new int[i9];
            TrackOutput.CryptoData[] cryptoDataArr = new TrackOutput.CryptoData[i9];
            int i10 = this.u;
            int i11 = i8 - i10;
            System.arraycopy(this.o, i10, jArr, 0, i11);
            System.arraycopy(this.r, this.u, jArr2, 0, i11);
            System.arraycopy(this.q, this.u, iArr2, 0, i11);
            System.arraycopy(this.p, this.u, iArr3, 0, i11);
            System.arraycopy(this.s, this.u, cryptoDataArr, 0, i11);
            System.arraycopy(this.n, this.u, iArr, 0, i11);
            int i12 = this.u;
            System.arraycopy(this.o, 0, jArr, i11, i12);
            System.arraycopy(this.r, 0, jArr2, i11, i12);
            System.arraycopy(this.q, 0, iArr2, i11, i12);
            System.arraycopy(this.p, 0, iArr3, i11, i12);
            System.arraycopy(this.s, 0, cryptoDataArr, i11, i12);
            System.arraycopy(this.n, 0, iArr, i11, i12);
            this.o = jArr;
            this.r = jArr2;
            this.q = iArr2;
            this.p = iArr3;
            this.s = cryptoDataArr;
            this.n = iArr;
            this.u = 0;
            this.m = i9;
        }
    }

    private void a(Format format, FormatHolder formatHolder) {
        Format format2 = this.k;
        boolean z2 = format2 == null;
        DrmInitData drmInitData = z2 ? null : format2.o;
        this.k = format;
        DrmInitData drmInitData2 = format.o;
        DrmSessionManager drmSessionManager = this.h;
        formatHolder.b = drmSessionManager != null ? format.a(drmSessionManager.a(format)) : format;
        formatHolder.a = this.l;
        if (this.h != null) {
            if (z2 || !Util.a((Object) drmInitData, (Object) drmInitData2)) {
                DrmSession drmSession = this.l;
                DrmSession b2 = this.h.b((Looper) Assertions.b((Object) this.j), this.i, format);
                this.l = b2;
                formatHolder.a = b2;
                if (drmSession != null) {
                    drmSession.b(this.i);
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002e, code lost:
        return -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized long b(long r11, boolean r13, boolean r14) {
        /*
            r10 = this;
            monitor-enter(r10)
            int r0 = r10.t     // Catch:{ all -> 0x002f }
            r1 = -1
            if (r0 == 0) goto L_0x002d
            long[] r3 = r10.r     // Catch:{ all -> 0x002f }
            int r5 = r10.u     // Catch:{ all -> 0x002f }
            r6 = r3[r5]     // Catch:{ all -> 0x002f }
            int r3 = (r11 > r6 ? 1 : (r11 == r6 ? 0 : -1))
            if (r3 >= 0) goto L_0x0012
            goto L_0x002d
        L_0x0012:
            if (r14 == 0) goto L_0x001a
            int r14 = r10.v     // Catch:{ all -> 0x002f }
            if (r14 == r0) goto L_0x001a
            int r0 = r14 + 1
        L_0x001a:
            r6 = r0
            r4 = r10
            r7 = r11
            r9 = r13
            int r11 = r4.a((int) r5, (int) r6, (long) r7, (boolean) r9)     // Catch:{ all -> 0x002f }
            r12 = -1
            if (r11 != r12) goto L_0x0027
            monitor-exit(r10)
            return r1
        L_0x0027:
            long r11 = r10.g(r11)     // Catch:{ all -> 0x002f }
            monitor-exit(r10)
            return r11
        L_0x002d:
            monitor-exit(r10)
            return r1
        L_0x002f:
            r11 = move-exception
            monitor-exit(r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.SampleQueue.b(long, boolean, boolean):long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000c, code lost:
        if (r7 <= r6.w) goto L_0x000f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000f, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean b(long r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            int r0 = r6.t     // Catch:{ all -> 0x0041 }
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0010
            long r3 = r6.w     // Catch:{ all -> 0x0041 }
            int r0 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            monitor-exit(r6)
            if (r0 <= 0) goto L_0x000f
            return r2
        L_0x000f:
            return r1
        L_0x0010:
            long r3 = r6.n()     // Catch:{ all -> 0x0041 }
            int r0 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r0 < 0) goto L_0x001a
            monitor-exit(r6)
            return r1
        L_0x001a:
            int r0 = r6.t     // Catch:{ all -> 0x0041 }
            int r1 = r0 + -1
            int r1 = r6.i(r1)     // Catch:{ all -> 0x0041 }
        L_0x0022:
            int r3 = r6.v     // Catch:{ all -> 0x0041 }
            if (r0 <= r3) goto L_0x0039
            long[] r3 = r6.r     // Catch:{ all -> 0x0041 }
            r4 = r3[r1]     // Catch:{ all -> 0x0041 }
            int r3 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r3 < 0) goto L_0x0039
            int r0 = r0 + -1
            int r1 = r1 + -1
            r3 = -1
            if (r1 != r3) goto L_0x0022
            int r1 = r6.m     // Catch:{ all -> 0x0041 }
            int r1 = r1 - r2
            goto L_0x0022
        L_0x0039:
            int r7 = r6.c     // Catch:{ all -> 0x0041 }
            int r7 = r7 + r0
            r6.e(r7)     // Catch:{ all -> 0x0041 }
            monitor-exit(r6)
            return r2
        L_0x0041:
            r7 = move-exception
            monitor-exit(r6)
            goto L_0x0045
        L_0x0044:
            throw r7
        L_0x0045:
            goto L_0x0044
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.SampleQueue.b(long):boolean");
    }

    private synchronized boolean c(Format format) {
        this.A = false;
        if (Util.a((Object) format, (Object) this.D)) {
            return false;
        }
        if (!this.g.c() && ((SharedSampleMetadata) this.g.a()).a.equals(format)) {
            format = ((SharedSampleMetadata) this.g.a()).a;
        }
        this.D = format;
        this.F = MimeTypes.a(this.D.l, this.D.i);
        this.G = false;
        return true;
    }

    private long e(int i2) {
        int b2 = b() - i2;
        boolean z2 = false;
        Assertions.a(b2 >= 0 && b2 <= this.t - this.v);
        int i3 = this.t - b2;
        this.t = i3;
        this.x = Math.max(this.w, h(i3));
        if (b2 == 0 && this.y) {
            z2 = true;
        }
        this.y = z2;
        this.g.c(i2);
        int i4 = this.t;
        if (i4 == 0) {
            return 0;
        }
        int i5 = i(i4 - 1);
        return this.o[i5] + ((long) this.p[i5]);
    }

    private boolean f(int i2) {
        DrmSession drmSession = this.l;
        if (drmSession == null || drmSession.b() == 4) {
            return true;
        }
        return (this.q[i2] & Declaration.MODULE_REFERENCE) == 0 && this.l.c();
    }

    private long g(int i2) {
        this.w = Math.max(this.w, h(i2));
        this.t -= i2;
        int i3 = this.c + i2;
        this.c = i3;
        int i4 = this.u + i2;
        this.u = i4;
        int i5 = this.m;
        if (i4 >= i5) {
            this.u = i4 - i5;
        }
        int i6 = this.v - i2;
        this.v = i6;
        if (i6 < 0) {
            this.v = 0;
        }
        this.g.b(i3);
        if (this.t != 0) {
            return this.o[this.u];
        }
        int i7 = this.u;
        if (i7 == 0) {
            i7 = this.m;
        }
        int i8 = i7 - 1;
        return this.o[i8] + ((long) this.p[i8]);
    }

    private long h(int i2) {
        long j2 = Long.MIN_VALUE;
        if (i2 == 0) {
            return Long.MIN_VALUE;
        }
        int i3 = i(i2 - 1);
        for (int i4 = 0; i4 < i2; i4++) {
            j2 = Math.max(j2, this.r[i3]);
            if ((this.q[i3] & 1) != 0) {
                break;
            }
            i3--;
            if (i3 == -1) {
                i3 = this.m - 1;
            }
        }
        return j2;
    }

    private int i(int i2) {
        int i3 = this.u + i2;
        int i4 = this.m;
        return i3 < i4 ? i3 : i3 - i4;
    }

    private synchronized long n() {
        return Math.max(this.w, h(this.v));
    }

    private synchronized void o() {
        this.v = 0;
        SampleDataQueue sampleDataQueue = this.a;
        sampleDataQueue.c = sampleDataQueue.b;
    }

    private synchronized long p() {
        int i2 = this.t;
        if (i2 == 0) {
            return -1;
        }
        return g(i2);
    }

    private void q() {
        DrmSession drmSession = this.l;
        if (drmSession != null) {
            drmSession.b(this.i);
            this.l = null;
            this.k = null;
        }
    }

    private boolean r() {
        return this.v != this.t;
    }

    public final int a(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i2, boolean z2) {
        boolean z3 = false;
        int a2 = a(formatHolder, decoderInputBuffer, (i2 & 2) != 0, z2, this.f);
        if (a2 == -4 && !decoderInputBuffer.c()) {
            if ((i2 & 1) != 0) {
                z3 = true;
            }
            if ((i2 & 4) == 0) {
                SampleDataQueue sampleDataQueue = this.a;
                SampleExtrasHolder sampleExtrasHolder = this.f;
                if (z3) {
                    sampleDataQueue.b(decoderInputBuffer, sampleExtrasHolder);
                } else {
                    sampleDataQueue.a(decoderInputBuffer, sampleExtrasHolder);
                }
            }
            if (!z3) {
                this.v++;
            }
        }
        return a2;
    }

    public final int a(DataReader dataReader, int i2, boolean z2) {
        SampleDataQueue sampleDataQueue = this.a;
        int a2 = sampleDataQueue.a(i2);
        int a3 = dataReader.a(sampleDataQueue.d.d.a, (int) (sampleDataQueue.e - sampleDataQueue.d.a), a2);
        if (a3 != -1) {
            sampleDataQueue.b(a3);
            return a3;
        } else if (z2) {
            return -1;
        } else {
            throw new EOFException();
        }
    }

    public final void a() {
        a(true);
        q();
    }

    public final void a(int i2) {
        this.E = i2;
    }

    public final void a(long j2) {
        if (this.H != j2) {
            this.H = j2;
            this.B = true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0069  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(long r12, int r14, int r15, int r16, com.google.android.exoplayer2.extractor.TrackOutput.CryptoData r17) {
        /*
            r11 = this;
            r8 = r11
            boolean r0 = r8.B
            if (r0 == 0) goto L_0x0010
            com.google.android.exoplayer2.Format r0 = r8.C
            java.lang.Object r0 = com.google.android.exoplayer2.util.Assertions.a((java.lang.Object) r0)
            com.google.android.exoplayer2.Format r0 = (com.google.android.exoplayer2.Format) r0
            r11.a((com.google.android.exoplayer2.Format) r0)
        L_0x0010:
            r0 = r14 & 1
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0018
            r3 = 1
            goto L_0x0019
        L_0x0018:
            r3 = 0
        L_0x0019:
            boolean r4 = r8.z
            if (r4 == 0) goto L_0x0022
            if (r3 != 0) goto L_0x0020
            return
        L_0x0020:
            r8.z = r1
        L_0x0022:
            long r4 = r8.H
            long r4 = r4 + r12
            boolean r6 = r8.F
            if (r6 == 0) goto L_0x0064
            long r6 = r8.d
            int r9 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r9 >= 0) goto L_0x0030
            return
        L_0x0030:
            if (r0 != 0) goto L_0x0064
            boolean r0 = r8.G
            if (r0 != 0) goto L_0x0060
            com.google.android.exoplayer2.Format r0 = r8.D
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r6 = java.lang.String.valueOf(r0)
            int r6 = r6.length()
            int r6 = r6 + 50
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>(r6)
            java.lang.String r6 = "Overriding unexpected non-sync sample for format: "
            java.lang.StringBuilder r6 = r7.append(r6)
            java.lang.StringBuilder r0 = r6.append(r0)
            java.lang.String r0 = r0.toString()
            java.lang.String r6 = "SampleQueue"
            com.google.android.exoplayer2.util.Log.c(r6, r0)
            r8.G = r2
        L_0x0060:
            r0 = r14 | 1
            r6 = r0
            goto L_0x0065
        L_0x0064:
            r6 = r14
        L_0x0065:
            boolean r0 = r8.e
            if (r0 == 0) goto L_0x0076
            if (r3 == 0) goto L_0x0075
            boolean r0 = r11.b((long) r4)
            if (r0 != 0) goto L_0x0072
            goto L_0x0075
        L_0x0072:
            r8.e = r1
            goto L_0x0076
        L_0x0075:
            return
        L_0x0076:
            com.google.android.exoplayer2.source.SampleDataQueue r0 = r8.a
            long r0 = r0.e
            r7 = r15
            long r2 = (long) r7
            long r0 = r0 - r2
            r2 = r16
            long r2 = (long) r2
            long r9 = r0 - r2
            r0 = r11
            r1 = r4
            r3 = r6
            r4 = r9
            r6 = r15
            r7 = r17
            r0.a((long) r1, (int) r3, (long) r4, (int) r6, (com.google.android.exoplayer2.extractor.TrackOutput.CryptoData) r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.SampleQueue.a(long, int, int, int, com.google.android.exoplayer2.extractor.TrackOutput$CryptoData):void");
    }

    public final void a(long j2, boolean z2, boolean z3) {
        this.a.a(b(j2, z2, z3));
    }

    public final void a(Format format) {
        Format b2 = b(format);
        this.B = false;
        this.C = format;
        boolean c2 = c(b2);
        UpstreamFormatChangedListener upstreamFormatChangedListener = this.b;
        if (upstreamFormatChangedListener != null && c2) {
            upstreamFormatChangedListener.j();
        }
    }

    public final void a(ParsableByteArray parsableByteArray, int i2) {
        SampleDataQueue sampleDataQueue = this.a;
        while (i2 > 0) {
            int a2 = sampleDataQueue.a(i2);
            parsableByteArray.a(sampleDataQueue.d.d.a, (int) (sampleDataQueue.e - sampleDataQueue.d.a), a2);
            i2 -= a2;
            sampleDataQueue.b(a2);
        }
    }

    public final void a(boolean z2) {
        this.a.a();
        this.t = 0;
        this.c = 0;
        this.u = 0;
        this.v = 0;
        this.z = true;
        this.d = Long.MIN_VALUE;
        this.w = Long.MIN_VALUE;
        this.x = Long.MIN_VALUE;
        this.y = false;
        this.g.b();
        if (z2) {
            this.C = null;
            this.D = null;
            this.A = true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003f, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean a(long r9, boolean r11) {
        /*
            r8 = this;
            monitor-enter(r8)
            r8.o()     // Catch:{ all -> 0x0040 }
            int r0 = r8.v     // Catch:{ all -> 0x0040 }
            int r2 = r8.i(r0)     // Catch:{ all -> 0x0040 }
            boolean r0 = r8.r()     // Catch:{ all -> 0x0040 }
            r7 = 0
            if (r0 == 0) goto L_0x003e
            long[] r0 = r8.r     // Catch:{ all -> 0x0040 }
            r3 = r0[r2]     // Catch:{ all -> 0x0040 }
            int r0 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r0 < 0) goto L_0x003e
            long r0 = r8.x     // Catch:{ all -> 0x0040 }
            int r3 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r3 <= 0) goto L_0x0022
            if (r11 != 0) goto L_0x0022
            goto L_0x003e
        L_0x0022:
            int r11 = r8.t     // Catch:{ all -> 0x0040 }
            int r0 = r8.v     // Catch:{ all -> 0x0040 }
            int r3 = r11 - r0
            r6 = 1
            r1 = r8
            r4 = r9
            int r11 = r1.a((int) r2, (int) r3, (long) r4, (boolean) r6)     // Catch:{ all -> 0x0040 }
            r0 = -1
            if (r11 != r0) goto L_0x0034
            monitor-exit(r8)
            return r7
        L_0x0034:
            r8.d = r9     // Catch:{ all -> 0x0040 }
            int r9 = r8.v     // Catch:{ all -> 0x0040 }
            int r9 = r9 + r11
            r8.v = r9     // Catch:{ all -> 0x0040 }
            monitor-exit(r8)
            r9 = 1
            return r9
        L_0x003e:
            monitor-exit(r8)
            return r7
        L_0x0040:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.SampleQueue.a(long, boolean):boolean");
    }

    public final int b() {
        return this.c + this.t;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0035, code lost:
        if (r9 != -1) goto L_0x0038;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0037, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0038, code lost:
        return r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003a, code lost:
        return 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized int b(long r9, boolean r11) {
        /*
            r8 = this;
            monitor-enter(r8)
            int r0 = r8.v     // Catch:{ all -> 0x003b }
            int r2 = r8.i(r0)     // Catch:{ all -> 0x003b }
            boolean r0 = r8.r()     // Catch:{ all -> 0x003b }
            r7 = 0
            if (r0 == 0) goto L_0x0039
            long[] r0 = r8.r     // Catch:{ all -> 0x003b }
            r3 = r0[r2]     // Catch:{ all -> 0x003b }
            int r0 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r0 >= 0) goto L_0x0017
            goto L_0x0039
        L_0x0017:
            long r0 = r8.x     // Catch:{ all -> 0x003b }
            int r3 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r3 <= 0) goto L_0x0026
            if (r11 == 0) goto L_0x0026
            int r9 = r8.t     // Catch:{ all -> 0x003b }
            int r10 = r8.v     // Catch:{ all -> 0x003b }
            int r9 = r9 - r10
            monitor-exit(r8)
            return r9
        L_0x0026:
            int r11 = r8.t     // Catch:{ all -> 0x003b }
            int r0 = r8.v     // Catch:{ all -> 0x003b }
            int r3 = r11 - r0
            r6 = 1
            r1 = r8
            r4 = r9
            int r9 = r1.a((int) r2, (int) r3, (long) r4, (boolean) r6)     // Catch:{ all -> 0x003b }
            r10 = -1
            monitor-exit(r8)
            if (r9 != r10) goto L_0x0038
            return r7
        L_0x0038:
            return r9
        L_0x0039:
            monitor-exit(r8)
            return r7
        L_0x003b:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.SampleQueue.b(long, boolean):int");
    }

    public final int b(DataReader dataReader, int i2, boolean z2) {
        return TrackOutput$$CC.a(this, dataReader, i2, z2);
    }

    public Format b(Format format) {
        if (this.H == 0 || format.p == LocationRequestCompat.PASSIVE_INTERVAL) {
            return format;
        }
        Format.Builder a2 = format.a();
        a2.o = format.p + this.H;
        return a2.a();
    }

    public final void b(int i2) {
        SampleDataQueue sampleDataQueue = this.a;
        sampleDataQueue.e = e(i2);
        if (sampleDataQueue.e == 0 || sampleDataQueue.e == sampleDataQueue.b.a) {
            sampleDataQueue.a(sampleDataQueue.b);
            sampleDataQueue.b = new SampleDataQueue.AllocationNode(sampleDataQueue.e, sampleDataQueue.a);
            sampleDataQueue.c = sampleDataQueue.b;
            sampleDataQueue.d = sampleDataQueue.b;
            return;
        }
        SampleDataQueue.AllocationNode allocationNode = sampleDataQueue.b;
        while (sampleDataQueue.e > allocationNode.b) {
            allocationNode = allocationNode.e;
        }
        SampleDataQueue.AllocationNode allocationNode2 = allocationNode.e;
        sampleDataQueue.a(allocationNode2);
        allocationNode.e = new SampleDataQueue.AllocationNode(allocationNode.b, sampleDataQueue.a);
        sampleDataQueue.d = sampleDataQueue.e == allocationNode.b ? allocationNode.e : allocationNode;
        if (sampleDataQueue.c == allocationNode2) {
            sampleDataQueue.c = allocationNode.e;
        }
    }

    public final void b(ParsableByteArray parsableByteArray, int i2) {
        TrackOutput$$CC.a(this, parsableByteArray, i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001b, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean b(boolean r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.r()     // Catch:{ all -> 0x003c }
            r1 = 1
            if (r0 != 0) goto L_0x001c
            if (r3 != 0) goto L_0x001a
            boolean r3 = r2.y     // Catch:{ all -> 0x003c }
            if (r3 != 0) goto L_0x001a
            com.google.android.exoplayer2.Format r3 = r2.D     // Catch:{ all -> 0x003c }
            if (r3 == 0) goto L_0x0017
            com.google.android.exoplayer2.Format r0 = r2.k     // Catch:{ all -> 0x003c }
            if (r3 == r0) goto L_0x0017
            goto L_0x001a
        L_0x0017:
            monitor-exit(r2)
            r3 = 0
            return r3
        L_0x001a:
            monitor-exit(r2)
            return r1
        L_0x001c:
            com.google.android.exoplayer2.source.SpannedData r3 = r2.g     // Catch:{ all -> 0x003c }
            int r0 = r2.e()     // Catch:{ all -> 0x003c }
            java.lang.Object r3 = r3.a(r0)     // Catch:{ all -> 0x003c }
            com.google.android.exoplayer2.source.SampleQueue$SharedSampleMetadata r3 = (com.google.android.exoplayer2.source.SampleQueue.SharedSampleMetadata) r3     // Catch:{ all -> 0x003c }
            com.google.android.exoplayer2.Format r3 = r3.a     // Catch:{ all -> 0x003c }
            com.google.android.exoplayer2.Format r0 = r2.k     // Catch:{ all -> 0x003c }
            if (r3 == r0) goto L_0x0030
            monitor-exit(r2)
            return r1
        L_0x0030:
            int r3 = r2.v     // Catch:{ all -> 0x003c }
            int r3 = r2.i(r3)     // Catch:{ all -> 0x003c }
            boolean r3 = r2.f(r3)     // Catch:{ all -> 0x003c }
            monitor-exit(r2)
            return r3
        L_0x003c:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.SampleQueue.b(boolean):boolean");
    }

    public final void c() {
        k();
        q();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0019, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean c(int r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            r3.o()     // Catch:{ all -> 0x001b }
            int r0 = r3.c     // Catch:{ all -> 0x001b }
            if (r4 < r0) goto L_0x0018
            int r1 = r3.t     // Catch:{ all -> 0x001b }
            int r1 = r1 + r0
            if (r4 <= r1) goto L_0x000e
            goto L_0x0018
        L_0x000e:
            r1 = -9223372036854775808
            r3.d = r1     // Catch:{ all -> 0x001b }
            int r4 = r4 - r0
            r3.v = r4     // Catch:{ all -> 0x001b }
            monitor-exit(r3)
            r4 = 1
            return r4
        L_0x0018:
            monitor-exit(r3)
            r4 = 0
            return r4
        L_0x001b:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.SampleQueue.c(int):boolean");
    }

    public final void d() {
        DrmSession drmSession = this.l;
        if (drmSession != null && drmSession.b() == 1) {
            throw ((DrmSession.DrmSessionException) Assertions.b((Object) this.l.d()));
        }
    }

    public final synchronized void d(int i2) {
        boolean z2;
        if (i2 >= 0) {
            try {
                if (this.v + i2 <= this.t) {
                    z2 = true;
                    Assertions.a(z2);
                    this.v += i2;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        z2 = false;
        Assertions.a(z2);
        this.v += i2;
    }

    public final int e() {
        return this.c + this.v;
    }

    public final synchronized int f() {
        int i2 = i(this.v);
        if (r()) {
            return this.n[i2];
        }
        return this.E;
    }

    public final synchronized Format g() {
        if (this.A) {
            return null;
        }
        return this.D;
    }

    public final synchronized long h() {
        return this.x;
    }

    public final synchronized boolean i() {
        return this.y;
    }

    public final synchronized long j() {
        if (this.t == 0) {
            return Long.MIN_VALUE;
        }
        return this.r[this.u];
    }

    public final void k() {
        this.a.a(p());
    }

    /* access modifiers changed from: protected */
    public final void l() {
        this.B = true;
    }

    public final synchronized long m() {
        int i2 = this.v;
        if (i2 == 0) {
            return -1;
        }
        return g(i2);
    }
}
