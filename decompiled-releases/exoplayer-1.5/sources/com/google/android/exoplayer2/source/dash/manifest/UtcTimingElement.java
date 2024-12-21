package com.google.android.exoplayer2.source.dash.manifest;

public final class UtcTimingElement {
    public final String a;
    public final String b;

    public UtcTimingElement(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public final String toString() {
        String str = this.a;
        String str2 = this.b;
        return new StringBuilder(String.valueOf(str).length() + 2 + String.valueOf(str2).length()).append(str).append(", ").append(str2).toString();
    }
}
