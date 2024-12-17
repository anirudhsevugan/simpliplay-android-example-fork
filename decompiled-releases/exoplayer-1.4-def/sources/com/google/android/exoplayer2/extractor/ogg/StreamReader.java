package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;

abstract class StreamReader {
    final OggPacket a = new OggPacket();
    TrackOutput b;
    ExtractorOutput c;
    OggSeeker d;
    long e;
    int f;
    int g;
    SetupData h = new SetupData();
    boolean i;
    private long j;
    private long k;
    private long l;
    private boolean m;

    class SetupData {
        Format a;
        OggSeeker b;

        SetupData() {
        }
    }

    final class UnseekableOggSeeker implements OggSeeker {
        private UnseekableOggSeeker() {
        }

        /* synthetic */ UnseekableOggSeeker(byte b) {
            this();
        }

        public final long a(ExtractorInput extractorInput) {
            return -1;
        }

        public final SeekMap a() {
            return new SeekMap.Unseekable(-9223372036854775807L);
        }

        public final void a(long j) {
        }
    }

    /* access modifiers changed from: package-private */
    public final int a(ExtractorInput extractorInput, PositionHolder positionHolder) {
        ExtractorInput extractorInput2 = extractorInput;
        long a2 = this.d.a(extractorInput2);
        if (a2 >= 0) {
            positionHolder.a = a2;
            return 1;
        }
        if (a2 < -1) {
            c(-(a2 + 2));
        }
        if (!this.m) {
            this.c.a((SeekMap) Assertions.a((Object) this.d.a()));
            this.m = true;
        }
        if (this.l > 0 || this.a.a(extractorInput2)) {
            this.l = 0;
            ParsableByteArray parsableByteArray = this.a.b;
            long b2 = b(parsableByteArray);
            if (b2 >= 0) {
                long j2 = this.k;
                if (j2 + b2 >= this.j) {
                    long a3 = a(j2);
                    this.b.b(parsableByteArray, parsableByteArray.c);
                    this.b.a(a3, 1, parsableByteArray.c, 0, (TrackOutput.CryptoData) null);
                    this.j = -1;
                }
            }
            this.k += b2;
            return 0;
        }
        this.f = 3;
        return -1;
    }

    /* access modifiers changed from: protected */
    public final long a(long j2) {
        return (j2 * 1000000) / ((long) this.g);
    }

    /* access modifiers changed from: package-private */
    public final void a(long j2, long j3) {
        OggPacket oggPacket = this.a;
        oggPacket.a.a();
        oggPacket.b.a(0);
        oggPacket.c = -1;
        oggPacket.d = false;
        if (j2 == 0) {
            a(!this.m);
        } else if (this.f != 0) {
            this.j = b(j3);
            ((OggSeeker) Util.a((Object) this.d)).a(this.j);
            this.f = 2;
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(ExtractorOutput extractorOutput, TrackOutput trackOutput) {
        this.c = extractorOutput;
        this.b = trackOutput;
        a(true);
    }

    /* access modifiers changed from: protected */
    public void a(boolean z) {
        int i2;
        if (z) {
            this.h = new SetupData();
            this.e = 0;
            i2 = 0;
        } else {
            i2 = 1;
        }
        this.f = i2;
        this.j = -1;
        this.k = 0;
    }

    /* access modifiers changed from: package-private */
    public final boolean a(ExtractorInput extractorInput) {
        while (this.a.a(extractorInput)) {
            this.l = extractorInput.c() - this.e;
            if (!a(this.a.b, this.e, this.h)) {
                return true;
            }
            this.e = extractorInput.c();
        }
        this.f = 3;
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract boolean a(ParsableByteArray parsableByteArray, long j2, SetupData setupData);

    /* access modifiers changed from: protected */
    public final long b(long j2) {
        return (((long) this.g) * j2) / 1000000;
    }

    /* access modifiers changed from: protected */
    public abstract long b(ParsableByteArray parsableByteArray);

    /* access modifiers changed from: protected */
    public void c(long j2) {
        this.k = j2;
    }
}
