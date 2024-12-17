package com.google.android.exoplayer2.text.pgs;

import android.graphics.Bitmap;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.zip.Inflater;

public final class PgsDecoder extends SimpleSubtitleDecoder {
    private final ParsableByteArray d = new ParsableByteArray();
    private final ParsableByteArray e = new ParsableByteArray();
    private final CueBuilder f = new CueBuilder();
    private Inflater g;

    final class CueBuilder {
        private final ParsableByteArray a = new ParsableByteArray();
        private final int[] b = new int[256];
        private boolean c;
        private int d;
        private int e;
        private int f;
        private int g;
        private int h;
        private int i;

        static /* synthetic */ void a(CueBuilder cueBuilder, ParsableByteArray parsableByteArray, int i2) {
            CueBuilder cueBuilder2 = cueBuilder;
            if (i2 % 5 == 2) {
                parsableByteArray.e(2);
                Arrays.fill(cueBuilder2.b, 0);
                int i3 = i2 / 5;
                for (int i4 = 0; i4 < i3; i4++) {
                    int c2 = parsableByteArray.c();
                    int c3 = parsableByteArray.c();
                    int c4 = parsableByteArray.c();
                    int c5 = parsableByteArray.c();
                    int c6 = parsableByteArray.c();
                    double d2 = (double) c3;
                    double d3 = (double) (c4 - 128);
                    Double.isNaN(d3);
                    Double.isNaN(d2);
                    double d4 = (double) (c5 - 128);
                    Double.isNaN(d4);
                    Double.isNaN(d2);
                    Double.isNaN(d3);
                    Double.isNaN(d4);
                    Double.isNaN(d2);
                    cueBuilder2.b[c2] = (Util.a((int) ((d2 - (0.34414d * d4)) - (d3 * 0.71414d)), 0, 255) << 8) | (c6 << 24) | (Util.a((int) ((1.402d * d3) + d2), 0, 255) << 16) | Util.a((int) (d2 + (d4 * 1.772d)), 0, 255);
                }
                cueBuilder2.c = true;
            }
        }

        static /* synthetic */ void b(CueBuilder cueBuilder, ParsableByteArray parsableByteArray, int i2) {
            int g2;
            if (i2 >= 4) {
                parsableByteArray.e(3);
                int i3 = i2 - 4;
                if ((parsableByteArray.c() & 128) != 0) {
                    if (i3 >= 7 && (g2 = parsableByteArray.g()) >= 4) {
                        cueBuilder.h = parsableByteArray.d();
                        cueBuilder.i = parsableByteArray.d();
                        cueBuilder.a.a(g2 - 4);
                        i3 -= 7;
                    } else {
                        return;
                    }
                }
                int i4 = cueBuilder.a.b;
                int i5 = cueBuilder.a.c;
                if (i4 < i5 && i3 > 0) {
                    int min = Math.min(i3, i5 - i4);
                    parsableByteArray.a(cueBuilder.a.a, i4, min);
                    cueBuilder.a.d(i4 + min);
                }
            }
        }

        static /* synthetic */ void c(CueBuilder cueBuilder, ParsableByteArray parsableByteArray, int i2) {
            if (i2 >= 19) {
                cueBuilder.d = parsableByteArray.d();
                cueBuilder.e = parsableByteArray.d();
                parsableByteArray.e(11);
                cueBuilder.f = parsableByteArray.d();
                cueBuilder.g = parsableByteArray.d();
            }
        }

        public final Cue a() {
            int c2;
            if (this.d == 0 || this.e == 0 || this.h == 0 || this.i == 0 || this.a.c == 0 || this.a.b != this.a.c || !this.c) {
                return null;
            }
            this.a.d(0);
            int i2 = this.h * this.i;
            int[] iArr = new int[i2];
            int i3 = 0;
            while (i3 < i2) {
                int c3 = this.a.c();
                if (c3 != 0) {
                    c2 = i3 + 1;
                    iArr[i3] = this.b[c3];
                } else {
                    int c4 = this.a.c();
                    if (c4 != 0) {
                        c2 = ((c4 & 64) == 0 ? c4 & 63 : ((c4 & 63) << 8) | this.a.c()) + i3;
                        Arrays.fill(iArr, i3, c2, (c4 & 128) == 0 ? 0 : this.b[this.a.c()]);
                    }
                }
                i3 = c2;
            }
            Bitmap createBitmap = Bitmap.createBitmap(iArr, this.h, this.i, Bitmap.Config.ARGB_8888);
            Cue.Builder builder = new Cue.Builder();
            builder.b = createBitmap;
            builder.f = ((float) this.f) / ((float) this.d);
            builder.g = 0;
            Cue.Builder a2 = builder.a(((float) this.g) / ((float) this.e), 0);
            a2.e = 0;
            a2.h = ((float) this.h) / ((float) this.d);
            a2.i = ((float) this.i) / ((float) this.e);
            return a2.a();
        }

        public final void b() {
            this.d = 0;
            this.e = 0;
            this.f = 0;
            this.g = 0;
            this.h = 0;
            this.i = 0;
            this.a.a(0);
            this.c = false;
        }
    }

    public final Subtitle a(byte[] bArr, int i, boolean z) {
        this.d.a(bArr, i);
        ParsableByteArray parsableByteArray = this.d;
        if (parsableByteArray.a() > 0 && parsableByteArray.b() == 120) {
            if (this.g == null) {
                this.g = new Inflater();
            }
            if (Util.a(parsableByteArray, this.e, this.g)) {
                parsableByteArray.a(this.e.a, this.e.c);
            }
        }
        this.f.b();
        ArrayList arrayList = new ArrayList();
        while (this.d.a() >= 3) {
            ParsableByteArray parsableByteArray2 = this.d;
            CueBuilder cueBuilder = this.f;
            int i2 = parsableByteArray2.c;
            int c = parsableByteArray2.c();
            int d2 = parsableByteArray2.d();
            int i3 = parsableByteArray2.b + d2;
            Cue cue = null;
            if (i3 > i2) {
                parsableByteArray2.d(i2);
            } else {
                switch (c) {
                    case 20:
                        CueBuilder.a(cueBuilder, parsableByteArray2, d2);
                        break;
                    case 21:
                        CueBuilder.b(cueBuilder, parsableByteArray2, d2);
                        break;
                    case 22:
                        CueBuilder.c(cueBuilder, parsableByteArray2, d2);
                        break;
                    case 128:
                        cue = cueBuilder.a();
                        cueBuilder.b();
                        break;
                }
                parsableByteArray2.d(i3);
            }
            if (cue != null) {
                arrayList.add(cue);
            }
        }
        return new PgsSubtitle(Collections.unmodifiableList(arrayList));
    }
}
