package com.dreamers.exoplayercore.repack;

/* renamed from: com.dreamers.exoplayercore.repack.az  reason: case insensitive filesystem */
final class C0026az extends C0025ay {
    private final char a;

    C0026az(char c) {
        this.a = c;
    }

    public final boolean b(char c) {
        return c == this.a;
    }

    public final String toString() {
        return "CharMatcher.is('" + C0024ax.c(this.a) + "')";
    }
}
