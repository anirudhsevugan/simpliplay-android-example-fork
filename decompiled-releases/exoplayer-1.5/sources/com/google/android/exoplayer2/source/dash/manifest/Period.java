package com.google.android.exoplayer2.source.dash.manifest;

import java.util.Collections;
import java.util.List;

public class Period {
    public final String a;
    public final long b;
    public final List c;
    public final List d;

    public Period(String str, long j, List list, List list2) {
        this(str, j, list, list2, (byte) 0);
    }

    public Period(String str, long j, List list, List list2, byte b2) {
        this.a = str;
        this.b = j;
        this.c = Collections.unmodifiableList(list);
        this.d = Collections.unmodifiableList(list2);
    }
}
