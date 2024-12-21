package com.google.android.exoplayer2;

public final class RendererConfiguration {
    public static final RendererConfiguration a = new RendererConfiguration(false);
    public final boolean b;

    public RendererConfiguration(boolean z) {
        this.b = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.b == ((RendererConfiguration) obj).b;
    }

    public final int hashCode() {
        return this.b ? 0 : 1;
    }
}
