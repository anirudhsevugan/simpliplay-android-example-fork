package com.google.android.exoplayer2.source.hls;

import android.net.Uri;
import androidx.core.location.LocationRequestCompat;
import com.dreamers.exoplayercore.repack.C0000a;
import com.dreamers.exoplayercore.repack.bG;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.DefaultExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.id3.Id3Decoder;
import com.google.android.exoplayer2.metadata.id3.PrivFrame;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.source.hls.HlsChunkSource;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.UriUtil;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.io.InterruptedIOException;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

final class HlsMediaChunk extends MediaChunk {
    private static final AtomicInteger p = new AtomicInteger();
    private final DrmInitData A;
    private final Id3Decoder B;
    private final ParsableByteArray C;
    private final boolean D;
    private final boolean E;
    private HlsMediaChunkExtractor F;
    private HlsSampleStreamWrapper G;
    private int H;
    private boolean I;
    private volatile boolean J;
    private bG K;
    public final int a;
    public final boolean b;
    public final int c;
    boolean d;
    boolean n;
    boolean o;
    private int q;
    private Uri r;
    private final DataSource s;
    private final DataSpec t;
    private final HlsMediaChunkExtractor u;
    private final boolean v;
    private final boolean w;
    private final TimestampAdjuster x;
    private final HlsExtractorFactory y;
    private final List z;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private HlsMediaChunk(HlsExtractorFactory hlsExtractorFactory, DataSource dataSource, DataSpec dataSpec, Format format, boolean z2, DataSource dataSource2, DataSpec dataSpec2, boolean z3, Uri uri, List list, int i, Object obj, long j, long j2, long j3, int i2, boolean z4, int i3, boolean z5, boolean z6, TimestampAdjuster timestampAdjuster, DrmInitData drmInitData, HlsMediaChunkExtractor hlsMediaChunkExtractor, Id3Decoder id3Decoder, ParsableByteArray parsableByteArray, boolean z7) {
        super(dataSource, dataSpec, format, i, obj, j, j2, j3);
        DataSpec dataSpec3 = dataSpec2;
        this.D = z2;
        this.c = i2;
        this.o = z4;
        this.q = i3;
        this.t = dataSpec3;
        this.s = dataSource2;
        this.I = dataSpec3 != null;
        this.E = z3;
        this.r = uri;
        this.v = z6;
        this.x = timestampAdjuster;
        this.w = z5;
        this.y = hlsExtractorFactory;
        this.z = list;
        this.A = drmInitData;
        this.u = hlsMediaChunkExtractor;
        this.B = id3Decoder;
        this.C = parsableByteArray;
        this.b = z7;
        this.K = bG.g();
        this.a = p.getAndIncrement();
    }

    private long a(ExtractorInput extractorInput) {
        extractorInput.a();
        try {
            this.C.a(10);
            extractorInput.d(this.C.a, 0, 10);
            if (this.C.g() != 4801587) {
                return -9223372036854775807L;
            }
            this.C.e(3);
            int n2 = this.C.n();
            int i = n2 + 10;
            if (i > this.C.a.length) {
                byte[] bArr = this.C.a;
                this.C.a(i);
                System.arraycopy(bArr, 0, this.C.a, 0, 10);
            }
            extractorInput.d(this.C.a, 10, n2);
            Metadata a2 = this.B.a(this.C.a, n2);
            if (a2 == null) {
                return -9223372036854775807L;
            }
            for (Metadata.Entry entry : a2.a) {
                if (entry instanceof PrivFrame) {
                    PrivFrame privFrame = (PrivFrame) entry;
                    if ("com.apple.streaming.transportStreamTimestamp".equals(privFrame.a)) {
                        System.arraycopy(privFrame.b, 0, this.C.a, 0, 8);
                        this.C.d(0);
                        this.C.c(8);
                        return this.C.l() & 8589934591L;
                    }
                }
            }
            return -9223372036854775807L;
        } catch (EOFException e) {
            return -9223372036854775807L;
        }
    }

    private DefaultExtractorInput a(DataSource dataSource, DataSpec dataSpec) {
        long j;
        HlsSampleStreamWrapper hlsSampleStreamWrapper;
        DataSpec dataSpec2 = dataSpec;
        DefaultExtractorInput defaultExtractorInput = new DefaultExtractorInput(dataSource, dataSpec2.f, dataSource.a(dataSpec));
        if (this.F == null) {
            long a2 = a((ExtractorInput) defaultExtractorInput);
            defaultExtractorInput.a = 0;
            HlsMediaChunkExtractor hlsMediaChunkExtractor = this.u;
            HlsMediaChunkExtractor c2 = hlsMediaChunkExtractor != null ? hlsMediaChunkExtractor.c() : this.y.a(dataSpec2.a, this.g, this.z, this.x, dataSource.b(), defaultExtractorInput);
            this.F = c2;
            if (c2.a()) {
                hlsSampleStreamWrapper = this.G;
                j = a2 != -9223372036854775807L ? this.x.b(a2) : this.j;
            } else {
                hlsSampleStreamWrapper = this.G;
                j = 0;
            }
            hlsSampleStreamWrapper.b(j);
            this.G.i();
            this.F.a((ExtractorOutput) this.G);
        }
        this.G.a(this.A);
        return defaultExtractorInput;
    }

    public static HlsMediaChunk a(HlsExtractorFactory hlsExtractorFactory, DataSource dataSource, Format format, long j, HlsMediaPlaylist hlsMediaPlaylist, HlsChunkSource.SegmentBaseHolder segmentBaseHolder, Uri uri, List list, int i, Object obj, boolean z2, TimestampAdjusterProvider timestampAdjusterProvider, HlsMediaChunk hlsMediaChunk, byte[] bArr, byte[] bArr2, boolean z3) {
        DataSpec dataSpec;
        boolean z4;
        DataSpec dataSpec2;
        DataSource dataSource2;
        ParsableByteArray parsableByteArray;
        Id3Decoder id3Decoder;
        HlsMediaChunkExtractor hlsMediaChunkExtractor;
        DataSource dataSource3 = dataSource;
        HlsMediaPlaylist hlsMediaPlaylist2 = hlsMediaPlaylist;
        HlsChunkSource.SegmentBaseHolder segmentBaseHolder2 = segmentBaseHolder;
        TimestampAdjusterProvider timestampAdjusterProvider2 = timestampAdjusterProvider;
        HlsMediaChunk hlsMediaChunk2 = hlsMediaChunk;
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        HlsMediaPlaylist.SegmentBase segmentBase = segmentBaseHolder2.a;
        DataSpec.Builder builder = new DataSpec.Builder();
        builder.a = UriUtil.a(hlsMediaPlaylist2.s, segmentBase.c);
        builder.e = segmentBase.k;
        builder.f = segmentBase.l;
        builder.h = segmentBaseHolder2.d ? 8 : 0;
        DataSpec a2 = builder.a();
        boolean z5 = bArr3 != null;
        DataSource a3 = a(dataSource3, bArr3, z5 ? a((String) Assertions.b((Object) segmentBase.j)) : null);
        HlsMediaPlaylist.Segment segment = segmentBase.d;
        if (segment != null) {
            boolean z6 = bArr4 != null;
            byte[] a4 = z6 ? a((String) Assertions.b((Object) segment.j)) : null;
            dataSpec = a2;
            DataSpec dataSpec3 = new DataSpec(UriUtil.a(hlsMediaPlaylist2.s, segment.c), segment.k, segment.l);
            z4 = z6;
            dataSource2 = a(dataSource3, bArr4, a4);
            dataSpec2 = dataSpec3;
        } else {
            dataSpec = a2;
            dataSource2 = null;
            dataSpec2 = null;
            z4 = false;
        }
        long j2 = j + segmentBase.g;
        long j3 = j2 + segmentBase.e;
        int i2 = hlsMediaPlaylist2.f + segmentBase.f;
        if (hlsMediaChunk2 != null) {
            boolean z7 = uri.equals(hlsMediaChunk2.r) && hlsMediaChunk2.d;
            Id3Decoder id3Decoder2 = hlsMediaChunk2.B;
            ParsableByteArray parsableByteArray2 = hlsMediaChunk2.C;
            id3Decoder = id3Decoder2;
            hlsMediaChunkExtractor = (!z7 || hlsMediaChunk2.n || hlsMediaChunk2.q != i2) ? null : hlsMediaChunk2.F;
            parsableByteArray = parsableByteArray2;
        } else {
            Uri uri2 = uri;
            id3Decoder = new Id3Decoder();
            parsableByteArray = new ParsableByteArray(10);
            hlsMediaChunkExtractor = null;
        }
        long j4 = segmentBaseHolder2.b;
        int i3 = segmentBaseHolder2.c;
        boolean z8 = !segmentBaseHolder2.d;
        boolean z9 = segmentBase.m;
        TimestampAdjuster timestampAdjuster = (TimestampAdjuster) timestampAdjusterProvider2.a.get(i2);
        if (timestampAdjuster == null) {
            timestampAdjuster = new TimestampAdjuster(LocationRequestCompat.PASSIVE_INTERVAL);
            timestampAdjusterProvider2.a.put(i2, timestampAdjuster);
        }
        return new HlsMediaChunk(hlsExtractorFactory, a3, dataSpec, format, z5, dataSource2, dataSpec2, z4, uri, list, i, (Object) null, j2, j3, j4, i3, z8, i2, z9, z2, timestampAdjuster, segmentBase.h, hlsMediaChunkExtractor, id3Decoder, parsableByteArray, z3);
    }

    private static DataSource a(DataSource dataSource, byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            return dataSource;
        }
        Assertions.b((Object) bArr2);
        return new Aes128DataSource(dataSource, bArr, bArr2);
    }

    private void a(DataSource dataSource, DataSpec dataSpec, boolean z2) {
        DataSpec dataSpec2;
        DefaultExtractorInput a2;
        long c2;
        long j;
        boolean z3 = false;
        if (z2) {
            if (this.H != 0) {
                z3 = true;
            }
            dataSpec2 = dataSpec;
        } else {
            dataSpec2 = dataSpec.a((long) this.H);
        }
        try {
            a2 = a(dataSource, dataSpec2);
            if (z3) {
                a2.b(this.H);
            }
            do {
                if (this.J || !this.F.a((ExtractorInput) a2)) {
                    break;
                }
                break;
                break;
            } while (!this.F.a((ExtractorInput) a2));
            break;
            c2 = a2.c();
            j = dataSpec.f;
        } catch (EOFException e) {
            if ((this.g.e & 16384) != 0) {
                this.F.d();
                c2 = a2.c();
                j = dataSpec.f;
            } else {
                throw e;
            }
        } catch (Throwable th) {
            Util.a(dataSource);
            throw th;
        }
        this.H = (int) (c2 - j);
        Util.a(dataSource);
    }

    public static boolean a(HlsMediaChunk hlsMediaChunk, Uri uri, HlsMediaPlaylist hlsMediaPlaylist, HlsChunkSource.SegmentBaseHolder segmentBaseHolder, long j) {
        if (hlsMediaChunk == null) {
            return false;
        }
        if (uri.equals(hlsMediaChunk.r) && hlsMediaChunk.d) {
            return false;
        }
        return !(segmentBaseHolder.a instanceof HlsMediaPlaylist.Part ? ((HlsMediaPlaylist.Part) segmentBaseHolder.a).a || (segmentBaseHolder.c == 0 && hlsMediaPlaylist.u) : hlsMediaPlaylist.u) || j + segmentBaseHolder.a.g < hlsMediaChunk.k;
    }

    private static byte[] a(String str) {
        if (C0000a.a(str).startsWith("0x")) {
            str = str.substring(2);
        }
        byte[] byteArray = new BigInteger(str, 16).toByteArray();
        byte[] bArr = new byte[16];
        int length = byteArray.length > 16 ? byteArray.length - 16 : 0;
        System.arraycopy(byteArray, length, bArr, (16 - byteArray.length) + length, byteArray.length - length);
        return bArr;
    }

    public final int a(int i) {
        Assertions.b(!this.b);
        if (i >= this.K.size()) {
            return 0;
        }
        return ((Integer) this.K.get(i)).intValue();
    }

    public final void a() {
        this.J = true;
    }

    public final void a(HlsSampleStreamWrapper hlsSampleStreamWrapper, bG bGVar) {
        this.G = hlsSampleStreamWrapper;
        this.K = bGVar;
    }

    public final void b() {
        HlsMediaChunkExtractor hlsMediaChunkExtractor;
        Assertions.b((Object) this.G);
        if (this.F == null && (hlsMediaChunkExtractor = this.u) != null && hlsMediaChunkExtractor.b()) {
            this.F = this.u;
            this.I = false;
        }
        if (this.I) {
            Assertions.b((Object) this.s);
            Assertions.b((Object) this.t);
            a(this.s, this.t, this.E);
            this.H = 0;
            this.I = false;
        }
        if (!this.J) {
            if (!this.w) {
                try {
                    this.x.a(this.v, this.j);
                    a((DataSource) this.l, this.e, this.D);
                } catch (InterruptedException e) {
                    throw new InterruptedIOException();
                }
            }
            this.d = !this.J;
        }
    }

    public final boolean h() {
        return this.d;
    }
}
