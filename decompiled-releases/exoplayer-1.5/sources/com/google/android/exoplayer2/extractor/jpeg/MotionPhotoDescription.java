package com.google.android.exoplayer2.extractor.jpeg;

import java.util.List;

final class MotionPhotoDescription {
    public final long a;
    public final List b;

    public final class ContainerItem {
        public final String a;
        public final long b;
        public final long c;

        public ContainerItem(String str, long j, long j2) {
            this.a = str;
            this.b = j;
            this.c = j2;
        }
    }

    public MotionPhotoDescription(long j, List list) {
        this.a = j;
        this.b = list;
    }
}
