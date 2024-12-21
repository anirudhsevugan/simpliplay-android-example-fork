package com.google.android.exoplayer2.util;

import java.util.ArrayList;
import java.util.Comparator;

public class SlidingPercentile {
    public static final Comparator a = SlidingPercentile$$Lambda$0.a;
    public static final Comparator b = SlidingPercentile$$Lambda$1.a;
    public final int c;
    public final ArrayList d = new ArrayList();
    public final Sample[] e = new Sample[5];
    public int f = -1;
    public int g;
    public int h;
    public int i;

    public class Sample {
        public int a;
        public int b;
        public float c;

        private Sample() {
        }

        public /* synthetic */ Sample(byte b2) {
            this();
        }
    }

    public SlidingPercentile(int i2) {
        this.c = i2;
    }

    static final /* synthetic */ int b(Sample sample, Sample sample2) {
        return sample.a - sample2.a;
    }
}
