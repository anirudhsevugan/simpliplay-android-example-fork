package com.google.android.exoplayer2.source.ads;

import android.net.Uri;
import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public final class AdPlaybackState implements Bundleable {
    public static final AdPlaybackState a = new AdPlaybackState(new long[0]);
    public final long[] b;
    public final AdGroup[] c = new AdGroup[0];
    private long d = -9223372036854775807L;

    public final class AdGroup implements Bundleable {
        public final int[] a;
        public final long[] b;
        private Uri[] c;

        static {
            Bundleable.Creator creator = AdPlaybackState$AdGroup$$Lambda$0.a;
        }

        public AdGroup() {
            this(new int[0], new Uri[0], new long[0]);
        }

        private AdGroup(int[] iArr, Uri[] uriArr, long[] jArr) {
            Assertions.a(true);
            this.a = iArr;
            this.c = uriArr;
            this.b = jArr;
        }

        public final int a(int i) {
            int i2;
            int i3 = i + 1;
            while (true) {
                int[] iArr = this.a;
                if (i3 >= iArr.length || (i2 = iArr[i3]) == 0 || i2 == 1) {
                    return i3;
                }
                i3++;
            }
            return i3;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                AdGroup adGroup = (AdGroup) obj;
                return Arrays.equals(this.c, adGroup.c) && Arrays.equals(this.a, adGroup.a) && Arrays.equals(this.b, adGroup.b);
            }
        }

        public final int hashCode() {
            return ((((Arrays.hashCode(this.c) - 31) * 31) + Arrays.hashCode(this.a)) * 31) + Arrays.hashCode(this.b);
        }
    }

    static {
        Bundleable.Creator creator = AdPlaybackState$$Lambda$0.a;
    }

    private AdPlaybackState(long[] jArr) {
        Assertions.a(true);
        this.b = jArr;
    }

    private boolean a(long j, long j2, int i) {
        if (j == Long.MIN_VALUE) {
            return false;
        }
        long j3 = this.b[i];
        return j3 == Long.MIN_VALUE ? j2 == -9223372036854775807L || j < j2 : j < j3;
    }

    public final int a(long j, long j2) {
        int length = this.b.length - 1;
        while (length >= 0 && a(j, j2, length)) {
            length--;
        }
        if (length >= 0) {
            return length;
        }
        return -1;
    }

    public final int b(long j, long j2) {
        long[] jArr;
        if (j != Long.MIN_VALUE && (j2 == -9223372036854775807L || j < j2)) {
            int i = 0;
            while (true) {
                jArr = this.b;
                if (i >= jArr.length) {
                    break;
                }
                long j3 = jArr[i];
                if (j3 == Long.MIN_VALUE || j3 > j) {
                    break;
                }
                i++;
            }
            if (i < jArr.length) {
                return i;
            }
        }
        return -1;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            AdPlaybackState adPlaybackState = (AdPlaybackState) obj;
            return Util.a((Object) null, (Object) null) && this.d == adPlaybackState.d && Arrays.equals(this.b, adPlaybackState.b) && Arrays.equals(this.c, adPlaybackState.c);
        }
    }

    public final int hashCode() {
        return ((((((int) this.d) + 0) * 31) + Arrays.hashCode(this.b)) * 31) + Arrays.hashCode(this.c);
    }

    public final String toString() {
        return "AdPlaybackState(adsId=" + null + ", adResumePositionUs=" + 0 + ", adGroups=[" + "])";
    }
}
