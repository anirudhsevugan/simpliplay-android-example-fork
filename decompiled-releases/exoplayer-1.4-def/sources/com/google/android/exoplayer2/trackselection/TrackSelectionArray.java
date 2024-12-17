package com.google.android.exoplayer2.trackselection;

import java.util.Arrays;

public final class TrackSelectionArray {
    public final int a;
    public final TrackSelection[] b;
    private int c;

    public TrackSelectionArray(TrackSelection... trackSelectionArr) {
        this.b = trackSelectionArr;
        this.a = trackSelectionArr.length;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.b, ((TrackSelectionArray) obj).b);
    }

    public final int hashCode() {
        if (this.c == 0) {
            this.c = Arrays.hashCode(this.b) + 527;
        }
        return this.c;
    }
}
