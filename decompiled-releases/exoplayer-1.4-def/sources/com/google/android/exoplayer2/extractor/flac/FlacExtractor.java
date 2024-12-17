package com.google.android.exoplayer2.extractor.flac;

import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.FlacFrameReader;
import com.google.android.exoplayer2.extractor.FlacMetadataReader;
import com.google.android.exoplayer2.extractor.FlacSeekTableSeekMap;
import com.google.android.exoplayer2.extractor.FlacStreamMetadata;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;

public final class FlacExtractor implements Extractor {
    private final byte[] a;
    private final ParsableByteArray b;
    private final FlacFrameReader.SampleNumberHolder c;
    private ExtractorOutput d;
    private TrackOutput e;
    private int f;
    private Metadata g;
    private FlacStreamMetadata h;
    private int i;
    private int j;
    private FlacBinarySearchSeeker k;
    private int l;
    private long m;

    static {
        ExtractorsFactory extractorsFactory = FlacExtractor$$Lambda$0.b;
    }

    public FlacExtractor() {
        this((byte) 0);
    }

    public FlacExtractor(byte b2) {
        this.a = new byte[42];
        this.b = new ParsableByteArray(new byte[32768], 0);
        this.c = new FlacFrameReader.SampleNumberHolder();
        this.f = 0;
    }

    private long a(ParsableByteArray parsableByteArray, boolean z) {
        boolean z2;
        Assertions.b((Object) this.h);
        int i2 = parsableByteArray.b;
        while (true) {
            if (i2 <= parsableByteArray.c - 16) {
                parsableByteArray.d(i2);
                if (FlacFrameReader.a(parsableByteArray, this.h, this.j, this.c)) {
                    break;
                }
                i2++;
            } else if (z) {
                while (i2 <= parsableByteArray.c - this.i) {
                    parsableByteArray.d(i2);
                    boolean z3 = false;
                    try {
                        z2 = FlacFrameReader.a(parsableByteArray, this.h, this.j, this.c);
                    } catch (IndexOutOfBoundsException e2) {
                        z2 = false;
                    }
                    if (parsableByteArray.b <= parsableByteArray.c) {
                        z3 = z2;
                    }
                    if (!z3) {
                        i2++;
                    }
                }
                parsableByteArray.d(parsableByteArray.c);
                return -1;
            } else {
                parsableByteArray.d(i2);
                return -1;
            }
        }
        parsableByteArray.d(i2);
        return this.c.a;
    }

    static final /* synthetic */ Extractor[] a() {
        return new Extractor[]{new FlacExtractor()};
    }

    private void b() {
        ((TrackOutput) Util.a((Object) this.e)).a((this.m * 1000000) / ((long) ((FlacStreamMetadata) Util.a((Object) this.h)).e), 1, this.l, 0, (TrackOutput.CryptoData) null);
    }

    public final int a(ExtractorInput extractorInput, PositionHolder positionHolder) {
        SeekMap seekMap;
        boolean z = true;
        switch (this.f) {
            case 0:
                this.g = FlacMetadataReader.b(extractorInput, true);
                this.f = 1;
                return 0;
            case 1:
                extractorInput.d(this.a, 0, 42);
                extractorInput.a();
                this.f = 2;
                return 0;
            case 2:
                FlacMetadataReader.b(extractorInput);
                this.f = 3;
                return 0;
            case 3:
                FlacMetadataReader.FlacStreamMetadataHolder flacStreamMetadataHolder = new FlacMetadataReader.FlacStreamMetadataHolder(this.h);
                boolean z2 = false;
                while (!z2) {
                    z2 = FlacMetadataReader.a(extractorInput, flacStreamMetadataHolder);
                    this.h = (FlacStreamMetadata) Util.a((Object) flacStreamMetadataHolder.a);
                }
                Assertions.b((Object) this.h);
                this.i = Math.max(this.h.c, 6);
                ((TrackOutput) Util.a((Object) this.e)).a(this.h.a(this.a, this.g));
                this.f = 4;
                return 0;
            case 4:
                this.j = FlacMetadataReader.c(extractorInput);
                ExtractorOutput extractorOutput = (ExtractorOutput) Util.a((Object) this.d);
                long c2 = extractorInput.c();
                long d2 = extractorInput.d();
                Assertions.b((Object) this.h);
                if (this.h.k != null) {
                    seekMap = new FlacSeekTableSeekMap(this.h, c2);
                } else if (d2 == -1 || this.h.j <= 0) {
                    seekMap = new SeekMap.Unseekable(this.h.a());
                } else {
                    FlacBinarySearchSeeker flacBinarySearchSeeker = new FlacBinarySearchSeeker(this.h, this.j, c2, d2);
                    this.k = flacBinarySearchSeeker;
                    seekMap = flacBinarySearchSeeker.a;
                }
                extractorOutput.a(seekMap);
                this.f = 5;
                return 0;
            case 5:
                Assertions.b((Object) this.e);
                Assertions.b((Object) this.h);
                FlacBinarySearchSeeker flacBinarySearchSeeker2 = this.k;
                if (flacBinarySearchSeeker2 != null && flacBinarySearchSeeker2.a()) {
                    return this.k.a(extractorInput, positionHolder);
                }
                if (this.m == -1) {
                    this.m = FlacFrameReader.a(extractorInput, this.h);
                } else {
                    int i2 = this.b.c;
                    if (i2 < 32768) {
                        int a2 = extractorInput.a(this.b.a, i2, 32768 - i2);
                        if (a2 != -1) {
                            z = false;
                        }
                        if (!z) {
                            this.b.c(i2 + a2);
                        } else if (this.b.a() == 0) {
                            b();
                            return -1;
                        }
                    } else {
                        z = false;
                    }
                    int i3 = this.b.b;
                    int i4 = this.l;
                    int i5 = this.i;
                    if (i4 < i5) {
                        ParsableByteArray parsableByteArray = this.b;
                        parsableByteArray.e(Math.min(i5 - i4, parsableByteArray.a()));
                    }
                    long a3 = a(this.b, z);
                    int i6 = this.b.b - i3;
                    this.b.d(i3);
                    this.e.b(this.b, i6);
                    this.l += i6;
                    if (a3 != -1) {
                        b();
                        this.l = 0;
                        this.m = a3;
                    }
                    if (this.b.a() < 16) {
                        int a4 = this.b.a();
                        System.arraycopy(this.b.a, this.b.b, this.b.a, 0, a4);
                        this.b.d(0);
                        this.b.c(a4);
                    }
                }
                return 0;
            default:
                throw new IllegalStateException();
        }
    }

    public final void a(long j2, long j3) {
        long j4 = 0;
        if (j2 == 0) {
            this.f = 0;
        } else {
            FlacBinarySearchSeeker flacBinarySearchSeeker = this.k;
            if (flacBinarySearchSeeker != null) {
                flacBinarySearchSeeker.a(j3);
            }
        }
        if (j3 != 0) {
            j4 = -1;
        }
        this.m = j4;
        this.l = 0;
        this.b.a(0);
    }

    public final void a(ExtractorOutput extractorOutput) {
        this.d = extractorOutput;
        this.e = extractorOutput.a(0, 1);
        extractorOutput.c_();
    }

    public final boolean a(ExtractorInput extractorInput) {
        FlacMetadataReader.a(extractorInput, false);
        return FlacMetadataReader.a(extractorInput);
    }
}
