package com.dreamers.exoplayercore.repack;

/* renamed from: com.dreamers.exoplayercore.repack.p  reason: case insensitive filesystem */
final class C0093p {
    public C0010ai a;
    public C0010ai b;
    public int c;
    public int d;
    public int e;
    public int f;

    private C0093p(C0010ai aiVar, C0010ai aiVar2) {
        this.a = aiVar;
        this.b = aiVar2;
    }

    C0093p(C0010ai aiVar, C0010ai aiVar2, int i, int i2, int i3, int i4) {
        this(aiVar, aiVar2);
        this.c = i;
        this.d = i2;
        this.e = i3;
        this.f = i4;
    }

    public final String toString() {
        return "ChangeInfo{oldHolder=" + this.a + ", newHolder=" + this.b + ", fromX=" + this.c + ", fromY=" + this.d + ", toX=" + this.e + ", toY=" + this.f + '}';
    }
}
