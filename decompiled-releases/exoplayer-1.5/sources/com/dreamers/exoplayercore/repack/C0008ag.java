package com.dreamers.exoplayercore.repack;

/* renamed from: com.dreamers.exoplayercore.repack.ag  reason: case insensitive filesystem */
public final class C0008ag {
    int a = -1;
    int b = 0;
    int c = 0;
    int d = 1;
    int e = 0;
    boolean f = false;
    boolean g = false;
    boolean h = false;
    boolean i = false;
    boolean j = false;
    boolean k = false;
    int l;
    long m;
    int n;

    public final int a() {
        return this.g ? this.b - this.c : this.e;
    }

    /* access modifiers changed from: package-private */
    public final void a(int i2) {
        if ((this.d & i2) == 0) {
            throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(i2) + " but it is " + Integer.toBinaryString(this.d));
        }
    }

    public final String toString() {
        return "State{mTargetPosition=" + this.a + ", mData=" + null + ", mItemCount=" + this.e + ", mIsMeasuring=" + this.i + ", mPreviousLayoutItemCount=" + this.b + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.c + ", mStructureChanged=" + this.f + ", mInPreLayout=" + this.g + ", mRunSimpleAnimations=" + this.j + ", mRunPredictiveAnimations=" + this.k + '}';
    }
}
