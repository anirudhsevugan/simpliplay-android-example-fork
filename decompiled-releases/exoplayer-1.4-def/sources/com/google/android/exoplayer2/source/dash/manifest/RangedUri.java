package com.google.android.exoplayer2.source.dash.manifest;

import com.google.android.exoplayer2.util.UriUtil;

public final class RangedUri {
    public final long a;
    public final long b;
    public final String c;
    private int d;

    public RangedUri(String str, long j, long j2) {
        this.c = str == null ? "" : str;
        this.a = j;
        this.b = j2;
    }

    private String a(String str) {
        return UriUtil.b(str, this.c);
    }

    public final RangedUri a(RangedUri rangedUri, String str) {
        String a2 = a(str);
        if (rangedUri != null && a2.equals(rangedUri.a(str))) {
            long j = this.b;
            long j2 = -1;
            if (j != -1) {
                long j3 = this.a;
                if (j3 + j == rangedUri.a) {
                    long j4 = rangedUri.b;
                    if (j4 != -1) {
                        j2 = j + j4;
                    }
                    return new RangedUri(a2, j3, j2);
                }
            }
            long j5 = rangedUri.b;
            if (j5 != -1) {
                long j6 = rangedUri.a;
                if (j6 + j5 == this.a) {
                    if (j != -1) {
                        j2 = j5 + j;
                    }
                    return new RangedUri(a2, j6, j2);
                }
            }
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            RangedUri rangedUri = (RangedUri) obj;
            return this.a == rangedUri.a && this.b == rangedUri.b && this.c.equals(rangedUri.c);
        }
    }

    public final int hashCode() {
        if (this.d == 0) {
            this.d = ((((((int) this.a) + 527) * 31) + ((int) this.b)) * 31) + this.c.hashCode();
        }
        return this.d;
    }

    public final String toString() {
        String str = this.c;
        long j = this.a;
        return new StringBuilder(String.valueOf(str).length() + 81).append("RangedUri(referenceUri=").append(str).append(", start=").append(j).append(", length=").append(this.b).append(")").toString();
    }
}
