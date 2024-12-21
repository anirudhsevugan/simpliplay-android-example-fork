package com.google.android.exoplayer2;

import android.util.Pair;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ShuffleOrder;
import com.google.android.exoplayer2.util.Assertions;

public abstract class AbstractConcatenatedTimeline extends Timeline {
    private final int a;
    private final ShuffleOrder c;

    public AbstractConcatenatedTimeline(ShuffleOrder shuffleOrder) {
        this.c = shuffleOrder;
        this.a = shuffleOrder.a();
    }

    private int a(int i, boolean z) {
        if (z) {
            return this.c.a(i);
        }
        if (i < this.a - 1) {
            return i + 1;
        }
        return -1;
    }

    public static Object a(Object obj) {
        return ((Pair) obj).first;
    }

    public static Object a(Object obj, Object obj2) {
        return Pair.create(obj, obj2);
    }

    private int b(int i, boolean z) {
        if (z) {
            return this.c.b(i);
        }
        if (i > 0) {
            return i - 1;
        }
        return -1;
    }

    public static Object b(Object obj) {
        return ((Pair) obj).second;
    }

    public final int a(int i, int i2, boolean z) {
        int c2 = c(i);
        int f = f(c2);
        int a2 = d(c2).a(i - f, i2 == 2 ? 0 : i2, z);
        if (a2 != -1) {
            return f + a2;
        }
        int a3 = a(c2, z);
        while (a3 != -1 && d(a3).c()) {
            a3 = a(a3, z);
        }
        if (a3 != -1) {
            return f(a3) + d(a3).b(z);
        }
        if (i2 == 2) {
            return b(z);
        }
        return -1;
    }

    public final int a(boolean z) {
        int i = this.a;
        if (i == 0) {
            return -1;
        }
        int b = z ? this.c.b() : i - 1;
        while (d(b).c()) {
            b = b(b, z);
            if (b == -1) {
                return -1;
            }
        }
        return f(b) + d(b).a(z);
    }

    public final Timeline.Period a(int i, Timeline.Period period, boolean z) {
        int b = b(i);
        int f = f(b);
        d(b).a(i - e(b), period, z);
        period.c += f;
        if (z) {
            period.b = Pair.create(g(b), Assertions.b(period.b));
        }
        return period;
    }

    public final Timeline.Period a(Object obj, Timeline.Period period) {
        Pair pair = (Pair) obj;
        Object obj2 = pair.first;
        Object obj3 = pair.second;
        int d = d(obj2);
        int f = f(d);
        d(d).a(obj3, period);
        period.c += f;
        period.b = obj;
        return period;
    }

    public final Timeline.Window a(int i, Timeline.Window window, long j) {
        int c2 = c(i);
        int f = f(c2);
        int e = e(c2);
        d(c2).a(i - f, window, j);
        Object g = g(c2);
        if (!Timeline.Window.a.equals(window.b)) {
            g = Pair.create(g, window.b);
        }
        window.b = g;
        window.n += e;
        window.o += e;
        return window;
    }

    public final Object a(int i) {
        int b = b(i);
        return Pair.create(g(b), d(b).a(i - e(b)));
    }

    /* access modifiers changed from: protected */
    public abstract int b(int i);

    public final int b(int i, int i2, boolean z) {
        int c2 = c(i);
        int f = f(c2);
        int b = d(c2).b(i - f, i2 == 2 ? 0 : i2, z);
        if (b != -1) {
            return f + b;
        }
        int b2 = b(c2, z);
        while (b2 != -1 && d(b2).c()) {
            b2 = b(b2, z);
        }
        if (b2 != -1) {
            return f(b2) + d(b2).a(z);
        }
        if (i2 == 2) {
            return a(z);
        }
        return -1;
    }

    public final int b(boolean z) {
        if (this.a == 0) {
            return -1;
        }
        int c2 = z ? this.c.c() : 0;
        while (d(c2).c()) {
            c2 = a(c2, z);
            if (c2 == -1) {
                return -1;
            }
        }
        return f(c2) + d(c2).b(z);
    }

    /* access modifiers changed from: protected */
    public abstract int c(int i);

    public final int c(Object obj) {
        int c2;
        if (!(obj instanceof Pair)) {
            return -1;
        }
        Pair pair = (Pair) obj;
        Object obj2 = pair.first;
        Object obj3 = pair.second;
        int d = d(obj2);
        if (d == -1 || (c2 = d(d).c(obj3)) == -1) {
            return -1;
        }
        return e(d) + c2;
    }

    /* access modifiers changed from: protected */
    public abstract int d(Object obj);

    /* access modifiers changed from: protected */
    public abstract Timeline d(int i);

    /* access modifiers changed from: protected */
    public abstract int e(int i);

    /* access modifiers changed from: protected */
    public abstract int f(int i);

    /* access modifiers changed from: protected */
    public abstract Object g(int i);
}
