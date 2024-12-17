package com.google.android.exoplayer2.decoder;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.Assertions;

public final class DecoderReuseEvaluation {
    public final int a;
    public final int b;
    private String c;
    private Format d;
    private Format e;

    public DecoderReuseEvaluation(String str, Format format, Format format2, int i, int i2) {
        Assertions.a(i == 0 || i2 == 0);
        this.c = Assertions.a(str);
        this.d = (Format) Assertions.b((Object) format);
        this.e = (Format) Assertions.b((Object) format2);
        this.a = i;
        this.b = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            DecoderReuseEvaluation decoderReuseEvaluation = (DecoderReuseEvaluation) obj;
            return this.a == decoderReuseEvaluation.a && this.b == decoderReuseEvaluation.b && this.c.equals(decoderReuseEvaluation.c) && this.d.equals(decoderReuseEvaluation.d) && this.e.equals(decoderReuseEvaluation.e);
        }
    }

    public final int hashCode() {
        return ((((((((this.a + 527) * 31) + this.b) * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31) + this.e.hashCode();
    }
}
