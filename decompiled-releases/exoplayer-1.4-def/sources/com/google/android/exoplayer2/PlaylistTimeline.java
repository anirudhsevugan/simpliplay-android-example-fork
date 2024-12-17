package com.google.android.exoplayer2;

import com.google.android.exoplayer2.source.ShuffleOrder;
import com.google.android.exoplayer2.util.Util;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

final class PlaylistTimeline extends AbstractConcatenatedTimeline {
    final Timeline[] a;
    private final int c;
    private final int d;
    private final int[] e;
    private final int[] f;
    private final Object[] g;
    private final HashMap h = new HashMap();

    public PlaylistTimeline(Collection collection, ShuffleOrder shuffleOrder) {
        super(shuffleOrder);
        int size = collection.size();
        this.e = new int[size];
        this.f = new int[size];
        this.a = new Timeline[size];
        this.g = new Object[size];
        Iterator it = collection.iterator();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (it.hasNext()) {
            MediaSourceInfoHolder mediaSourceInfoHolder = (MediaSourceInfoHolder) it.next();
            this.a[i3] = mediaSourceInfoHolder.b();
            this.f[i3] = i;
            this.e[i3] = i2;
            i += this.a[i3].a();
            i2 += this.a[i3].b();
            this.g[i3] = mediaSourceInfoHolder.a();
            this.h.put(this.g[i3], Integer.valueOf(i3));
            i3++;
        }
        this.c = i;
        this.d = i2;
    }

    public final int a() {
        return this.c;
    }

    public final int b() {
        return this.d;
    }

    /* access modifiers changed from: protected */
    public final int b(int i) {
        return Util.b(this.e, i + 1);
    }

    /* access modifiers changed from: protected */
    public final int c(int i) {
        return Util.b(this.f, i + 1);
    }

    /* access modifiers changed from: protected */
    public final int d(Object obj) {
        Integer num = (Integer) this.h.get(obj);
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    /* access modifiers changed from: protected */
    public final Timeline d(int i) {
        return this.a[i];
    }

    /* access modifiers changed from: protected */
    public final int e(int i) {
        return this.e[i];
    }

    /* access modifiers changed from: protected */
    public final int f(int i) {
        return this.f[i];
    }

    /* access modifiers changed from: protected */
    public final Object g(int i) {
        return this.g[i];
    }
}
