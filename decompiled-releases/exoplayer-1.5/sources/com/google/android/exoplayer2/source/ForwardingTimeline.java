package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.Timeline;

public abstract class ForwardingTimeline extends Timeline {
    protected final Timeline a;

    public ForwardingTimeline(Timeline timeline) {
        this.a = timeline;
    }

    public final int a() {
        return this.a.a();
    }

    public final int a(int i, int i2, boolean z) {
        return this.a.a(i, i2, z);
    }

    public final int a(boolean z) {
        return this.a.a(z);
    }

    public Timeline.Period a(int i, Timeline.Period period, boolean z) {
        return this.a.a(i, period, z);
    }

    public Timeline.Window a(int i, Timeline.Window window, long j) {
        return this.a.a(i, window, j);
    }

    public Object a(int i) {
        return this.a.a(i);
    }

    public final int b() {
        return this.a.b();
    }

    public final int b(int i, int i2, boolean z) {
        return this.a.b(i, i2, z);
    }

    public final int b(boolean z) {
        return this.a.b(z);
    }

    public int c(Object obj) {
        return this.a.c(obj);
    }
}
