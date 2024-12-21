package com.dreamers.exoplayercore.repack;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public abstract class bO extends C0041bn implements Serializable {
    final transient bM b;
    final transient int c;

    bO(bM bMVar, int i) {
        this.b = bMVar;
        this.c = i;
    }

    public final boolean a(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    public final /* bridge */ /* synthetic */ Map b() {
        return this.b;
    }

    public final boolean b(Object obj) {
        return obj != null && super.b(obj);
    }

    public final /* bridge */ /* synthetic */ boolean b(Object obj, Object obj2) {
        return super.b(obj, obj2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final cM m() {
        return new bP(this);
    }

    public final boolean c(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    public final int d() {
        return this.c;
    }

    /* renamed from: d */
    public abstract bC a(Object obj);

    public final void e() {
        throw new UnsupportedOperationException();
    }

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    /* access modifiers changed from: package-private */
    public final Set f() {
        throw new AssertionError("unreachable");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public final cM j() {
        return new bQ(this);
    }

    public final /* bridge */ /* synthetic */ Collection h() {
        return (bC) super.h();
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Collection i() {
        return new bT(this);
    }

    public final /* bridge */ /* synthetic */ Collection k() {
        return (bC) super.k();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Collection l() {
        return new bS(this);
    }

    /* access modifiers changed from: package-private */
    public final Map n() {
        throw new AssertionError("should never be called");
    }

    public final /* synthetic */ Set p() {
        return this.b.keySet();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }
}
