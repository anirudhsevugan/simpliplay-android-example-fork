package com.google.android.exoplayer2.extractor.flv;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.flv.TagPayloadReader;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.video.AvcConfig;
import com.google.appinventor.components.runtime.util.Ev3Constants;

final class VideoTagPayloadReader extends TagPayloadReader {
    private final ParsableByteArray a = new ParsableByteArray(NalUnitUtil.a);
    private final ParsableByteArray b = new ParsableByteArray(4);
    private int c;
    private boolean e;
    private boolean f;
    private int g;

    public VideoTagPayloadReader(TrackOutput trackOutput) {
        super(trackOutput);
    }

    /* access modifiers changed from: protected */
    public final boolean a(ParsableByteArray parsableByteArray) {
        int c2 = parsableByteArray.c();
        int i = (c2 >> 4) & 15;
        int i2 = c2 & 15;
        if (i2 == 7) {
            this.g = i;
            return i != 5;
        }
        throw new TagPayloadReader.UnsupportedFormatException(new StringBuilder(39).append("Video format not supported: ").append(i2).toString());
    }

    /* access modifiers changed from: protected */
    public final boolean a(ParsableByteArray parsableByteArray, long j) {
        int c2 = parsableByteArray.c();
        byte[] bArr = parsableByteArray.a;
        int i = parsableByteArray.b;
        parsableByteArray.b = i + 1;
        byte[] bArr2 = parsableByteArray.a;
        int i2 = parsableByteArray.b;
        parsableByteArray.b = i2 + 1;
        byte b2 = (((bArr[i] & Ev3Constants.Opcode.TST) << 24) >> 8) | ((bArr2[i2] & Ev3Constants.Opcode.TST) << 8);
        byte[] bArr3 = parsableByteArray.a;
        int i3 = parsableByteArray.b;
        parsableByteArray.b = i3 + 1;
        long j2 = j + (((long) (b2 | (bArr3[i3] & Ev3Constants.Opcode.TST))) * 1000);
        if (c2 == 0 && !this.e) {
            ParsableByteArray parsableByteArray2 = new ParsableByteArray(new byte[parsableByteArray.a()]);
            parsableByteArray.a(parsableByteArray2.a, 0, parsableByteArray.a());
            AvcConfig a2 = AvcConfig.a(parsableByteArray2);
            this.c = a2.b;
            Format.Builder builder = new Format.Builder();
            builder.k = "video/avc";
            builder.h = a2.f;
            builder.p = a2.c;
            builder.q = a2.d;
            builder.t = a2.e;
            builder.m = a2.a;
            this.d.a(builder.a());
            this.e = true;
            return false;
        } else if (c2 != 1 || !this.e) {
            return false;
        } else {
            int i4 = this.g == 1 ? 1 : 0;
            if (!this.f && i4 == 0) {
                return false;
            }
            byte[] bArr4 = this.b.a;
            bArr4[0] = 0;
            bArr4[1] = 0;
            bArr4[2] = 0;
            int i5 = 4 - this.c;
            int i6 = 0;
            while (parsableByteArray.a() > 0) {
                parsableByteArray.a(this.b.a, i5, this.c);
                this.b.d(0);
                int o = this.b.o();
                this.a.d(0);
                this.d.b(this.a, 4);
                this.d.b(parsableByteArray, o);
                i6 = i6 + 4 + o;
            }
            this.d.a(j2, i4, i6, 0, (TrackOutput.CryptoData) null);
            this.f = true;
            return true;
        }
    }
}
