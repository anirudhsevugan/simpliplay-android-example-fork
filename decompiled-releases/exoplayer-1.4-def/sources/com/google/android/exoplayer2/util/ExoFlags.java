package com.google.android.exoplayer2.util;

import android.util.SparseBooleanArray;

public final class ExoFlags {
    public final SparseBooleanArray a;

    public final class Builder {
        private final SparseBooleanArray a = new SparseBooleanArray();
        private boolean b;

        public final Builder a(int i) {
            Assertions.b(!this.b);
            this.a.append(i, true);
            return this;
        }

        public final Builder a(int i, boolean z) {
            return z ? a(i) : this;
        }

        public final Builder a(ExoFlags exoFlags) {
            for (int i = 0; i < exoFlags.a.size(); i++) {
                a(exoFlags.b(i));
            }
            return this;
        }

        public final Builder a(int... iArr) {
            for (int a2 : iArr) {
                a(a2);
            }
            return this;
        }

        public final ExoFlags a() {
            Assertions.b(!this.b);
            this.b = true;
            return new ExoFlags(this.a, (byte) 0);
        }
    }

    private ExoFlags(SparseBooleanArray sparseBooleanArray) {
        this.a = sparseBooleanArray;
    }

    /* synthetic */ ExoFlags(SparseBooleanArray sparseBooleanArray, byte b) {
        this(sparseBooleanArray);
    }

    public final boolean a(int i) {
        return this.a.get(i);
    }

    public final int b(int i) {
        Assertions.a(i, this.a.size());
        return this.a.keyAt(i);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ExoFlags)) {
            return false;
        }
        return this.a.equals(((ExoFlags) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }
}
