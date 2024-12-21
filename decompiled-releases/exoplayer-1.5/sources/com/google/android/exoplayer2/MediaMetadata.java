package com.google.android.exoplayer2;

import android.net.Uri;
import android.os.Bundle;
import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;
import java.util.List;

public final class MediaMetadata implements Bundleable {
    public static final MediaMetadata a = new Builder().a();
    public final CharSequence b;
    public final CharSequence c;
    public final CharSequence d;
    public final CharSequence e;
    public final CharSequence f;
    public final CharSequence g;
    public final CharSequence h;
    public final Uri i;
    public final Rating j;
    public final Rating k;
    public final byte[] l;
    public final Uri m;
    public final Integer n;
    public final Integer o;
    public final Integer p;
    public final Boolean q;
    public final Integer r;
    public final Bundle s;

    public final class Builder {
        public CharSequence a;
        public CharSequence b;
        public CharSequence c;
        public CharSequence d;
        public Integer e;
        public Integer f;
        public Integer g;
        /* access modifiers changed from: private */
        public CharSequence h;
        /* access modifiers changed from: private */
        public CharSequence i;
        /* access modifiers changed from: private */
        public CharSequence j;
        /* access modifiers changed from: private */
        public Uri k;
        /* access modifiers changed from: private */
        public Rating l;
        /* access modifiers changed from: private */
        public Rating m;
        /* access modifiers changed from: private */
        public byte[] n;
        /* access modifiers changed from: private */
        public Uri o;
        /* access modifiers changed from: private */
        public Integer p;
        /* access modifiers changed from: private */
        public Boolean q;
        /* access modifiers changed from: private */
        public Bundle r;

        public Builder() {
        }

        private Builder(MediaMetadata mediaMetadata) {
            this.a = mediaMetadata.b;
            this.b = mediaMetadata.c;
            this.c = mediaMetadata.d;
            this.d = mediaMetadata.e;
            this.h = mediaMetadata.f;
            this.i = mediaMetadata.g;
            this.j = mediaMetadata.h;
            this.k = mediaMetadata.i;
            this.l = mediaMetadata.j;
            this.m = mediaMetadata.k;
            this.n = mediaMetadata.l;
            this.o = mediaMetadata.m;
            this.e = mediaMetadata.n;
            this.f = mediaMetadata.o;
            this.p = mediaMetadata.p;
            this.q = mediaMetadata.q;
            this.g = mediaMetadata.r;
            this.r = mediaMetadata.s;
        }

        /* synthetic */ Builder(MediaMetadata mediaMetadata, byte b2) {
            this(mediaMetadata);
        }

        public final Builder a(List list) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                Metadata metadata = (Metadata) list.get(i2);
                for (Metadata.Entry a2 : metadata.a) {
                    a2.a(this);
                }
            }
            return this;
        }

        public final Builder a(byte[] bArr) {
            this.n = bArr == null ? null : (byte[]) bArr.clone();
            return this;
        }

        public final MediaMetadata a() {
            return new MediaMetadata(this, (byte) 0);
        }
    }

    static {
        Bundleable.Creator creator = MediaMetadata$$Lambda$0.a;
    }

    private MediaMetadata(Builder builder) {
        this.b = builder.a;
        this.c = builder.b;
        this.d = builder.c;
        this.e = builder.d;
        this.f = builder.h;
        this.g = builder.i;
        this.h = builder.j;
        this.i = builder.k;
        this.j = builder.l;
        this.k = builder.m;
        this.l = builder.n;
        this.m = builder.o;
        this.n = builder.e;
        this.o = builder.f;
        this.p = builder.p;
        this.q = builder.q;
        this.r = builder.g;
        this.s = builder.r;
    }

    /* synthetic */ MediaMetadata(Builder builder, byte b2) {
        this(builder);
    }

    public final Builder a() {
        return new Builder(this, (byte) 0);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            MediaMetadata mediaMetadata = (MediaMetadata) obj;
            return Util.a((Object) this.b, (Object) mediaMetadata.b) && Util.a((Object) this.c, (Object) mediaMetadata.c) && Util.a((Object) this.d, (Object) mediaMetadata.d) && Util.a((Object) this.e, (Object) mediaMetadata.e) && Util.a((Object) this.f, (Object) mediaMetadata.f) && Util.a((Object) this.g, (Object) mediaMetadata.g) && Util.a((Object) this.h, (Object) mediaMetadata.h) && Util.a((Object) this.i, (Object) mediaMetadata.i) && Util.a((Object) this.j, (Object) mediaMetadata.j) && Util.a((Object) this.k, (Object) mediaMetadata.k) && Arrays.equals(this.l, mediaMetadata.l) && Util.a((Object) this.m, (Object) mediaMetadata.m) && Util.a((Object) this.n, (Object) mediaMetadata.n) && Util.a((Object) this.o, (Object) mediaMetadata.o) && Util.a((Object) this.p, (Object) mediaMetadata.p) && Util.a((Object) this.q, (Object) mediaMetadata.q) && Util.a((Object) this.r, (Object) mediaMetadata.r);
        }
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, Integer.valueOf(Arrays.hashCode(this.l)), this.m, this.n, this.o, this.p, this.q, this.r});
    }
}
