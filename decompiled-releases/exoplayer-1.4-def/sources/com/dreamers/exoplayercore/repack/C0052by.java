package com.dreamers.exoplayercore.repack;

import java.util.Comparator;

/* renamed from: com.dreamers.exoplayercore.repack.by  reason: case insensitive filesystem */
public abstract class C0052by {
    /* access modifiers changed from: private */
    public static final C0052by a = new C0053bz();
    /* access modifiers changed from: private */
    public static final C0052by b = new bA(-1);
    /* access modifiers changed from: private */
    public static final C0052by c = new bA(1);

    private C0052by() {
    }

    /* synthetic */ C0052by(byte b2) {
        this();
    }

    public static C0052by a() {
        return a;
    }

    public abstract C0052by a(int i, int i2);

    public abstract C0052by a(long j, long j2);

    public abstract C0052by a(Object obj, Object obj2, Comparator comparator);

    public abstract C0052by a(boolean z, boolean z2);

    public abstract int b();

    public abstract C0052by b(boolean z, boolean z2);
}
