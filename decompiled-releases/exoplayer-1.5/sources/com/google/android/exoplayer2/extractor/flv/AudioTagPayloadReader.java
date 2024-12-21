package com.google.android.exoplayer2.extractor.flv;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.audio.AacUtil;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.flv.TagPayloadReader;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.Collections;

final class AudioTagPayloadReader extends TagPayloadReader {
    private static final int[] a = {5512, 11025, 22050, 44100};
    private boolean b;
    private boolean c;
    private int e;

    public AudioTagPayloadReader(TrackOutput trackOutput) {
        super(trackOutput);
    }

    /* access modifiers changed from: protected */
    public final boolean a(ParsableByteArray parsableByteArray) {
        Format.Builder builder;
        int i;
        if (!this.b) {
            int c2 = parsableByteArray.c();
            int i2 = (c2 >> 4) & 15;
            this.e = i2;
            if (i2 == 2) {
                i = a[(c2 >> 2) & 3];
                builder = new Format.Builder();
                builder.k = "audio/mpeg";
                builder.x = 1;
            } else if (i2 == 7 || i2 == 8) {
                String str = i2 == 7 ? "audio/g711-alaw" : "audio/g711-mlaw";
                builder = new Format.Builder();
                builder.k = str;
                builder.x = 1;
                i = 8000;
            } else {
                if (i2 != 10) {
                    throw new TagPayloadReader.UnsupportedFormatException(new StringBuilder(39).append("Audio format not supported: ").append(this.e).toString());
                }
                this.b = true;
            }
            builder.y = i;
            this.d.a(builder.a());
            this.c = true;
            this.b = true;
        } else {
            parsableByteArray.e(1);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public final boolean a(ParsableByteArray parsableByteArray, long j) {
        if (this.e == 2) {
            int a2 = parsableByteArray.a();
            this.d.b(parsableByteArray, a2);
            this.d.a(j, 1, a2, 0, (TrackOutput.CryptoData) null);
            return true;
        }
        int c2 = parsableByteArray.c();
        if (c2 == 0 && !this.c) {
            int a3 = parsableByteArray.a();
            byte[] bArr = new byte[a3];
            parsableByteArray.a(bArr, 0, a3);
            AacUtil.Config a4 = AacUtil.a(bArr);
            Format.Builder builder = new Format.Builder();
            builder.k = "audio/mp4a-latm";
            builder.h = a4.c;
            builder.x = a4.b;
            builder.y = a4.a;
            builder.m = Collections.singletonList(bArr);
            this.d.a(builder.a());
            this.c = true;
            return false;
        } else if (this.e == 10 && c2 != 1) {
            return false;
        } else {
            int a5 = parsableByteArray.a();
            this.d.b(parsableByteArray, a5);
            this.d.a(j, 1, a5, 0, (TrackOutput.CryptoData) null);
            return true;
        }
    }
}
