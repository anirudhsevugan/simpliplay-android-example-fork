package com.google.android.exoplayer2.source.dash.manifest;

import java.util.Collections;
import java.util.List;

public class AdaptationSet {
    public final int a;
    public final int b;
    public final List c;
    public final List d;
    public final List e;
    public final List f;

    public AdaptationSet(int i, int i2, List list, List list2, List list3, List list4) {
        this.a = i;
        this.b = i2;
        this.c = Collections.unmodifiableList(list);
        this.d = Collections.unmodifiableList(list2);
        this.e = Collections.unmodifiableList(list3);
        this.f = Collections.unmodifiableList(list4);
    }
}
