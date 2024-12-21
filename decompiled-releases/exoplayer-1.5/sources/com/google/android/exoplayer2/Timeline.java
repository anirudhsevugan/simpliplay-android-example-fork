package com.google.android.exoplayer2;

import android.net.Uri;
import android.util.Pair;
import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.ads.AdPlaybackState;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

public abstract class Timeline implements Bundleable {
    public static final Timeline b = new Timeline() {
        public final int a() {
            return 0;
        }

        public final Period a(int i, Period period, boolean z) {
            throw new IndexOutOfBoundsException();
        }

        public final Window a(int i, Window window, long j) {
            throw new IndexOutOfBoundsException();
        }

        public final Object a(int i) {
            throw new IndexOutOfBoundsException();
        }

        public final int b() {
            return 0;
        }

        public final int c(Object obj) {
            return -1;
        }
    };

    public final class Period implements Bundleable {
        public Object a;
        public Object b;
        public int c;
        public long d;
        public long e;
        public boolean f;
        private AdPlaybackState g = AdPlaybackState.a;

        static {
            Bundleable.Creator creator = Timeline$Period$$Lambda$0.a;
        }

        public final int a(long j) {
            return this.g.a(j, this.d);
        }

        public final long a(int i) {
            return this.g.b[i];
        }

        public final Period a(Object obj, Object obj2, long j, long j2) {
            return a(obj, obj2, j, j2, AdPlaybackState.a, false);
        }

        public final Period a(Object obj, Object obj2, long j, long j2, AdPlaybackState adPlaybackState, boolean z) {
            this.a = obj;
            this.b = obj2;
            this.c = 0;
            this.d = j;
            this.e = j2;
            this.g = adPlaybackState;
            this.f = z;
            return this;
        }

        public final int b(int i) {
            return this.g.c[i].a(-1);
        }

        public final int b(long j) {
            return this.g.b(j, this.d);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass().equals(obj.getClass())) {
                Period period = (Period) obj;
                return Util.a(this.a, period.a) && Util.a(this.b, period.b) && this.c == period.c && this.d == period.d && this.e == period.e && this.f == period.f && Util.a((Object) this.g, (Object) period.g);
            }
        }

        public final int hashCode() {
            Object obj = this.a;
            int i = 0;
            int hashCode = ((obj == null ? 0 : obj.hashCode()) + 217) * 31;
            Object obj2 = this.b;
            if (obj2 != null) {
                i = obj2.hashCode();
            }
            long j = this.d;
            long j2 = this.e;
            return ((((((((((hashCode + i) * 31) + this.c) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + (this.f ? 1 : 0)) * 31) + this.g.hashCode();
        }
    }

    public final class Window implements Bundleable {
        public static final Object a = new Object();
        private static final MediaItem q;
        public Object b = a;
        public MediaItem c = q;
        public Object d;
        public long e;
        public long f;
        public long g;
        public boolean h;
        public boolean i;
        public MediaItem.LiveConfiguration j;
        public boolean k;
        public long l;
        public long m;
        public int n;
        public int o;
        public long p;
        private boolean r;

        static {
            new Object();
            MediaItem.Builder a2 = new MediaItem.Builder().a("com.google.android.exoplayer2.Timeline");
            a2.a = Uri.EMPTY;
            q = a2.a();
            Bundleable.Creator creator = Timeline$Window$$Lambda$0.a;
        }

        public final Window a(Object obj, MediaItem mediaItem, Object obj2, long j2, long j3, long j4, boolean z, boolean z2, MediaItem.LiveConfiguration liveConfiguration, long j5, long j6, int i2, long j7) {
            MediaItem.LiveConfiguration liveConfiguration2 = liveConfiguration;
            this.b = obj;
            this.c = mediaItem != null ? mediaItem : q;
            this.d = obj2;
            this.e = j2;
            this.f = j3;
            this.g = j4;
            this.h = z;
            this.i = z2;
            this.r = liveConfiguration2 != null;
            this.j = liveConfiguration2;
            this.l = j5;
            this.m = j6;
            this.n = 0;
            this.o = i2;
            this.p = j7;
            this.k = false;
            return this;
        }

        public final boolean a() {
            Assertions.b(this.r == (this.j != null));
            return this.j != null;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass().equals(obj.getClass())) {
                Window window = (Window) obj;
                return Util.a(this.b, window.b) && Util.a((Object) this.c, (Object) window.c) && Util.a(this.d, window.d) && Util.a((Object) this.j, (Object) window.j) && this.e == window.e && this.f == window.f && this.g == window.g && this.h == window.h && this.i == window.i && this.k == window.k && this.l == window.l && this.m == window.m && this.n == window.n && this.o == window.o && this.p == window.p;
            }
        }

        public final int hashCode() {
            int hashCode = (((this.b.hashCode() + 217) * 31) + this.c.hashCode()) * 31;
            Object obj = this.d;
            int i2 = 0;
            int hashCode2 = (hashCode + (obj == null ? 0 : obj.hashCode())) * 31;
            MediaItem.LiveConfiguration liveConfiguration = this.j;
            if (liveConfiguration != null) {
                i2 = liveConfiguration.hashCode();
            }
            long j2 = this.e;
            long j3 = this.f;
            long j4 = this.g;
            long j5 = this.l;
            long j6 = this.m;
            long j7 = this.p;
            return ((((((((((((((((((((((hashCode2 + i2) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31) + ((int) (j4 ^ (j4 >>> 32)))) * 31) + (this.h ? 1 : 0)) * 31) + (this.i ? 1 : 0)) * 31) + (this.k ? 1 : 0)) * 31) + ((int) (j5 ^ (j5 >>> 32)))) * 31) + ((int) (j6 ^ (j6 >>> 32)))) * 31) + this.n) * 31) + this.o) * 31) + ((int) (j7 ^ (j7 >>> 32)));
        }
    }

    static {
        Bundleable.Creator creator = Timeline$$Lambda$0.a;
    }

    public abstract int a();

    public int a(int i, int i2, boolean z) {
        switch (i2) {
            case 0:
                if (i == a(z)) {
                    return -1;
                }
                return i + 1;
            case 1:
                return i;
            case 2:
                return i == a(z) ? b(z) : i + 1;
            default:
                throw new IllegalStateException();
        }
    }

    public final int a(int i, Period period, Window window, int i2, boolean z) {
        int i3 = a(i, period, false).c;
        if (a(i3, window, 0).o != i) {
            return i + 1;
        }
        int a = a(i3, i2, z);
        if (a == -1) {
            return -1;
        }
        return a(a, window, 0).n;
    }

    public int a(boolean z) {
        if (c()) {
            return -1;
        }
        return a() - 1;
    }

    public final Pair a(Window window, Period period, int i, long j) {
        return (Pair) Assertions.b((Object) a(window, period, i, j, 0));
    }

    public final Pair a(Window window, Period period, int i, long j, long j2) {
        Assertions.a(i, a());
        a(i, window, j2);
        if (j == -9223372036854775807L) {
            j = window.l;
            if (j == -9223372036854775807L) {
                return null;
            }
        }
        int i2 = window.n;
        a(i2, period, false);
        while (i2 < window.o && period.e != j) {
            int i3 = i2 + 1;
            if (a(i3, period, false).e > j) {
                break;
            }
            i2 = i3;
        }
        a(i2, period, true);
        return Pair.create(Assertions.b(period.b), Long.valueOf(j - period.e));
    }

    public abstract Period a(int i, Period period, boolean z);

    public Period a(Object obj, Period period) {
        return a(c(obj), period, true);
    }

    public abstract Window a(int i, Window window, long j);

    public abstract Object a(int i);

    public abstract int b();

    public int b(int i, int i2, boolean z) {
        switch (i2) {
            case 0:
                if (i == b(z)) {
                    return -1;
                }
                return i - 1;
            case 1:
                return i;
            case 2:
                return i == b(z) ? a(z) : i - 1;
            default:
                throw new IllegalStateException();
        }
    }

    public int b(boolean z) {
        return c() ? -1 : 0;
    }

    public final boolean b(int i, Period period, Window window, int i2, boolean z) {
        return a(i, period, window, i2, z) == -1;
    }

    public abstract int c(Object obj);

    public final boolean c() {
        return a() == 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Timeline)) {
            return false;
        }
        Timeline timeline = (Timeline) obj;
        if (timeline.a() != a() || timeline.b() != b()) {
            return false;
        }
        Window window = new Window();
        Period period = new Period();
        Window window2 = new Window();
        Period period2 = new Period();
        for (int i = 0; i < a(); i++) {
            if (!a(i, window, 0).equals(timeline.a(i, window2, 0))) {
                return false;
            }
        }
        for (int i2 = 0; i2 < b(); i2++) {
            if (!a(i2, period, true).equals(timeline.a(i2, period2, true))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i;
        Window window = new Window();
        Period period = new Period();
        int a = a() + 217;
        int i2 = 0;
        while (true) {
            i = a * 31;
            if (i2 >= a()) {
                break;
            }
            a = i + a(i2, window, 0).hashCode();
            i2++;
        }
        int b2 = i + b();
        for (int i3 = 0; i3 < b(); i3++) {
            b2 = (b2 * 31) + a(i3, period, true).hashCode();
        }
        return b2;
    }
}
