package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.audio.AacUtil;
import com.google.android.exoplayer2.extractor.DummyTrackOutput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.util.Arrays;
import java.util.Collections;

public final class AdtsReader implements ElementaryStreamReader {
    private static final byte[] b = {Ev3Constants.Opcode.CP_GT16, Ev3Constants.Opcode.CP_LT8, Ev3Constants.Opcode.MOVE8_F};
    long a;
    private final boolean c;
    private final ParsableBitArray d;
    private final ParsableByteArray e;
    private final String f;
    private String g;
    private TrackOutput h;
    private TrackOutput i;
    private int j;
    private int k;
    private int l;
    private boolean m;
    private boolean n;
    private int o;
    private int p;
    private int q;
    private boolean r;
    private long s;
    private int t;
    private TrackOutput u;
    private long v;

    public AdtsReader() {
        this(true, (String) null);
    }

    public AdtsReader(boolean z, String str) {
        this.d = new ParsableBitArray(new byte[7]);
        this.e = new ParsableByteArray(Arrays.copyOf(b, 10));
        d();
        this.o = -1;
        this.p = -1;
        this.s = -9223372036854775807L;
        this.c = z;
        this.f = str;
    }

    private void a(TrackOutput trackOutput, long j2, int i2, int i3) {
        this.j = 4;
        this.k = i2;
        this.u = trackOutput;
        this.v = j2;
        this.t = i3;
    }

    private static boolean a(byte b2) {
        return a((int) (b2 & Ev3Constants.Opcode.TST) | 65280);
    }

    public static boolean a(int i2) {
        return (i2 & 65526) == 65520;
    }

    private boolean a(ParsableByteArray parsableByteArray, int i2) {
        parsableByteArray.d(i2 + 1);
        if (!b(parsableByteArray, this.d.a, 1)) {
            return false;
        }
        this.d.a(4);
        int c2 = this.d.c(1);
        int i3 = this.o;
        if (i3 != -1 && c2 != i3) {
            return false;
        }
        if (this.p != -1) {
            if (!b(parsableByteArray, this.d.a, 1)) {
                return true;
            }
            this.d.a(2);
            if (this.d.c(4) != this.p) {
                return false;
            }
            parsableByteArray.d(i2 + 2);
        }
        if (!b(parsableByteArray, this.d.a, 4)) {
            return true;
        }
        this.d.a(14);
        int c3 = this.d.c(13);
        if (c3 < 7) {
            return false;
        }
        byte[] bArr = parsableByteArray.a;
        int i4 = parsableByteArray.c;
        int i5 = i2 + c3;
        if (i5 >= i4) {
            return true;
        }
        byte b2 = bArr[i5];
        if (b2 == -1) {
            int i6 = i5 + 1;
            if (i6 == i4) {
                return true;
            }
            return a(bArr[i6]) && ((bArr[i6] & 8) >> 3) == c2;
        } else if (b2 != 73) {
            return false;
        } else {
            int i7 = i5 + 1;
            if (i7 == i4) {
                return true;
            }
            if (bArr[i7] != 68) {
                return false;
            }
            int i8 = i5 + 2;
            return i8 == i4 || bArr[i8] == 51;
        }
    }

    private boolean a(ParsableByteArray parsableByteArray, byte[] bArr, int i2) {
        int min = Math.min(parsableByteArray.a(), i2 - this.k);
        parsableByteArray.a(bArr, this.k, min);
        int i3 = this.k + min;
        this.k = i3;
        return i3 == i2;
    }

    private void b(ParsableByteArray parsableByteArray) {
        int i2;
        byte[] bArr = parsableByteArray.a;
        int i3 = parsableByteArray.b;
        int i4 = parsableByteArray.c;
        while (i3 < i4) {
            int i5 = i3 + 1;
            byte b2 = bArr[i3] & Ev3Constants.Opcode.TST;
            if (this.l != 512 || !a((byte) b2) || (!this.n && !a(parsableByteArray, i5 - 2))) {
                int i6 = this.l;
                switch (b2 | i6) {
                    case 329:
                        i2 = 768;
                        break;
                    case 511:
                        this.l = 512;
                        continue;
                    case 836:
                        i2 = 1024;
                        break;
                    case 1075:
                        e();
                        parsableByteArray.d(i5);
                        return;
                    default:
                        if (i6 != 256) {
                            this.l = 256;
                            i5--;
                            break;
                        } else {
                            continue;
                        }
                }
                this.l = i2;
                i3 = i5;
            } else {
                this.q = (b2 & 8) >> 3;
                boolean z = true;
                if ((b2 & 1) != 0) {
                    z = false;
                }
                this.m = z;
                if (!this.n) {
                    g();
                } else {
                    f();
                }
                parsableByteArray.d(i5);
                return;
            }
        }
        parsableByteArray.d(i3);
    }

    private static boolean b(ParsableByteArray parsableByteArray, byte[] bArr, int i2) {
        if (parsableByteArray.a() < i2) {
            return false;
        }
        parsableByteArray.a(bArr, 0, i2);
        return true;
    }

    private void c(ParsableByteArray parsableByteArray) {
        if (parsableByteArray.a() != 0) {
            this.d.a[0] = parsableByteArray.a[parsableByteArray.b];
            this.d.a(2);
            int c2 = this.d.c(4);
            int i2 = this.p;
            if (i2 == -1 || c2 == i2) {
                if (!this.n) {
                    this.n = true;
                    this.o = this.q;
                    this.p = c2;
                }
                f();
                return;
            }
            c();
        }
    }

    private void d() {
        this.j = 0;
        this.k = 0;
        this.l = 256;
    }

    private void d(ParsableByteArray parsableByteArray) {
        int min = Math.min(parsableByteArray.a(), this.t - this.k);
        this.u.b(parsableByteArray, min);
        int i2 = this.k + min;
        this.k = i2;
        int i3 = this.t;
        if (i2 == i3) {
            this.u.a(this.a, 1, i3, 0, (TrackOutput.CryptoData) null);
            this.a += this.v;
            d();
        }
    }

    private void e() {
        this.j = 2;
        this.k = 3;
        this.t = 0;
        this.e.d(0);
    }

    private void f() {
        this.j = 3;
        this.k = 0;
    }

    private void g() {
        this.j = 1;
        this.k = 0;
    }

    private void h() {
        this.i.b(this.e, 10);
        this.e.d(6);
        a(this.i, 0, 10, this.e.n() + 10);
    }

    private void i() {
        this.d.a(0);
        if (!this.r) {
            int c2 = this.d.c(2) + 1;
            if (c2 != 2) {
                Log.c("AdtsReader", new StringBuilder(61).append("Detected audio object type: ").append(c2).append(", but assuming AAC LC.").toString());
                c2 = 2;
            }
            this.d.b(5);
            byte[] a2 = AacUtil.a(c2, this.p, this.d.c(3));
            AacUtil.Config a3 = AacUtil.a(a2);
            Format.Builder builder = new Format.Builder();
            builder.a = this.g;
            builder.k = "audio/mp4a-latm";
            builder.h = a3.c;
            builder.x = a3.b;
            builder.y = a3.a;
            builder.m = Collections.singletonList(a2);
            builder.c = this.f;
            Format a4 = builder.a();
            this.s = 1024000000 / ((long) a4.x);
            this.h.a(a4);
            this.r = true;
        } else {
            this.d.b(10);
        }
        this.d.b(4);
        int c3 = (this.d.c(13) - 2) - 5;
        if (this.m) {
            c3 -= 2;
        }
        a(this.h, this.s, 0, c3);
    }

    private void j() {
        Assertions.b((Object) this.h);
        Util.a((Object) this.u);
        Util.a((Object) this.i);
    }

    public final void a() {
        c();
    }

    public final void a(long j2, int i2) {
        this.a = j2;
    }

    public final void a(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.a();
        this.g = trackIdGenerator.c();
        TrackOutput a2 = extractorOutput.a(trackIdGenerator.b(), 1);
        this.h = a2;
        this.u = a2;
        if (this.c) {
            trackIdGenerator.a();
            TrackOutput a3 = extractorOutput.a(trackIdGenerator.b(), 5);
            this.i = a3;
            Format.Builder builder = new Format.Builder();
            builder.a = trackIdGenerator.c();
            builder.k = "application/id3";
            a3.a(builder.a());
            return;
        }
        this.i = new DummyTrackOutput();
    }

    public final void a(ParsableByteArray parsableByteArray) {
        j();
        while (parsableByteArray.a() > 0) {
            switch (this.j) {
                case 0:
                    b(parsableByteArray);
                    break;
                case 1:
                    c(parsableByteArray);
                    break;
                case 2:
                    if (!a(parsableByteArray, this.e.a, 10)) {
                        break;
                    } else {
                        h();
                        break;
                    }
                case 3:
                    if (!a(parsableByteArray, this.d.a, this.m ? 7 : 5)) {
                        break;
                    } else {
                        i();
                        break;
                    }
                case 4:
                    d(parsableByteArray);
                    break;
                default:
                    throw new IllegalStateException();
            }
        }
    }

    public final void b() {
    }

    /* access modifiers changed from: package-private */
    public final void c() {
        this.n = false;
        d();
    }
}
