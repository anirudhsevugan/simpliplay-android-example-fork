package com.google.android.exoplayer2.text;

import android.graphics.Bitmap;
import android.text.Layout;
import android.text.Spanned;
import android.text.SpannedString;
import com.google.android.exoplayer2.util.Assertions;

public final class Cue {
    public static final Cue a;
    public final CharSequence b;
    public final Layout.Alignment c;
    public final Layout.Alignment d;
    public final Bitmap e;
    public final float f;
    public final int g;
    public final int h;
    public final float i;
    public final int j;
    public final float k;
    public final float l;
    public final boolean m;
    public final int n;
    public final int o;
    public final float p;
    public final int q;
    public final float r;

    public final class Builder {
        public CharSequence a;
        public Bitmap b;
        public Layout.Alignment c;
        public Layout.Alignment d;
        public int e;
        public float f;
        public int g;
        public float h;
        public float i;
        public boolean j;
        public int k;
        public int l;
        public float m;
        private float n;
        private int o;
        private int p;
        private float q;

        public Builder() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.n = -3.4028235E38f;
            this.o = Integer.MIN_VALUE;
            this.e = Integer.MIN_VALUE;
            this.f = -3.4028235E38f;
            this.g = Integer.MIN_VALUE;
            this.p = Integer.MIN_VALUE;
            this.q = -3.4028235E38f;
            this.h = -3.4028235E38f;
            this.i = -3.4028235E38f;
            this.j = false;
            this.k = -16777216;
            this.l = Integer.MIN_VALUE;
        }

        private Builder(Cue cue) {
            this.a = cue.b;
            this.b = cue.e;
            this.c = cue.c;
            this.d = cue.d;
            this.n = cue.f;
            this.o = cue.g;
            this.e = cue.h;
            this.f = cue.i;
            this.g = cue.j;
            this.p = cue.o;
            this.q = cue.p;
            this.h = cue.k;
            this.i = cue.l;
            this.j = cue.m;
            this.k = cue.n;
            this.l = cue.q;
            this.m = cue.r;
        }

        /* synthetic */ Builder(Cue cue, byte b2) {
            this(cue);
        }

        public final Builder a(float f2, int i2) {
            this.n = f2;
            this.o = i2;
            return this;
        }

        public final Cue a() {
            return new Cue(this.a, this.c, this.d, this.b, this.n, this.o, this.e, this.f, this.g, this.p, this.q, this.h, this.i, this.j, this.k, this.l, this.m, (byte) 0);
        }

        public final Builder b(float f2, int i2) {
            this.q = f2;
            this.p = i2;
            return this;
        }
    }

    static {
        Builder builder = new Builder();
        builder.a = "";
        a = builder.a();
    }

    private Cue(CharSequence charSequence, Layout.Alignment alignment, Layout.Alignment alignment2, Bitmap bitmap, float f2, int i2, int i3, float f3, int i4, int i5, float f4, float f5, float f6, boolean z, int i6, int i7, float f7) {
        CharSequence charSequence2 = charSequence;
        Bitmap bitmap2 = bitmap;
        if (charSequence2 == null) {
            Assertions.b((Object) bitmap);
        } else {
            Assertions.a(bitmap2 == null);
        }
        this.b = charSequence2 instanceof Spanned ? SpannedString.valueOf(charSequence) : charSequence2 != null ? charSequence.toString() : null;
        this.c = alignment;
        this.d = alignment2;
        this.e = bitmap2;
        this.f = f2;
        this.g = i2;
        this.h = i3;
        this.i = f3;
        this.j = i4;
        this.k = f5;
        this.l = f6;
        this.m = z;
        this.n = i6;
        this.o = i5;
        this.p = f4;
        this.q = i7;
        this.r = f7;
    }

    /* synthetic */ Cue(CharSequence charSequence, Layout.Alignment alignment, Layout.Alignment alignment2, Bitmap bitmap, float f2, int i2, int i3, float f3, int i4, int i5, float f4, float f5, float f6, boolean z, int i6, int i7, float f7, byte b2) {
        this(charSequence, alignment, alignment2, bitmap, f2, i2, i3, f3, i4, i5, f4, f5, f6, z, i6, i7, f7);
    }

    public final Builder a() {
        return new Builder(this, (byte) 0);
    }
}
