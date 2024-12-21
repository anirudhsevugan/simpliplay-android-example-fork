package com.dreamers.exoplayercore.repack;

import android.view.animation.Interpolator;
import android.widget.OverScroller;
import androidx.core.view.ViewCompat;

/* renamed from: com.dreamers.exoplayercore.repack.ah  reason: case insensitive filesystem */
final class C0009ah implements Runnable {
    int a;
    int b;
    OverScroller c;
    Interpolator d = F.B;
    final /* synthetic */ F e;
    private boolean f = false;
    private boolean g = false;

    C0009ah(F f2) {
        this.e = f2;
        this.c = new OverScroller(f2.getContext(), F.B);
    }

    static float a(float f2) {
        return (float) Math.sin((double) ((f2 - 0.5f) * 0.47123894f));
    }

    /* access modifiers changed from: package-private */
    public final void a() {
        if (this.f) {
            this.g = true;
            return;
        }
        this.e.removeCallbacks(this);
        ViewCompat.postOnAnimation(this.e, this);
    }

    public final void b() {
        this.e.removeCallbacks(this);
        this.c.abortAnimation();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00b7, code lost:
        if (r7 > 0) goto L_0x00bb;
     */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r22 = this;
            r0 = r22
            com.dreamers.exoplayercore.repack.F r1 = r0.e
            com.dreamers.exoplayercore.repack.U r1 = r1.l
            if (r1 != 0) goto L_0x000c
            r22.b()
            return
        L_0x000c:
            r1 = 0
            r0.g = r1
            r2 = 1
            r0.f = r2
            com.dreamers.exoplayercore.repack.F r3 = r0.e
            r3.c()
            android.widget.OverScroller r3 = r0.c
            boolean r4 = r3.computeScrollOffset()
            if (r4 == 0) goto L_0x015c
            com.dreamers.exoplayercore.repack.F r4 = r0.e
            int[] r4 = r4.z
            int r11 = r3.getCurrX()
            int r12 = r3.getCurrY()
            int r5 = r0.a
            int r13 = r11 - r5
            int r5 = r0.b
            int r14 = r12 - r5
            r0.a = r11
            r0.b = r12
            com.dreamers.exoplayercore.repack.F r5 = r0.e
            r9 = 0
            r10 = 1
            r6 = r13
            r7 = r14
            r8 = r4
            boolean r5 = r5.dispatchNestedPreScroll(r6, r7, r8, r9, r10)
            if (r5 == 0) goto L_0x004a
            r5 = r4[r1]
            int r13 = r13 - r5
            r4 = r4[r2]
            int r14 = r14 - r4
        L_0x004a:
            com.dreamers.exoplayercore.repack.F r4 = r0.e
            com.dreamers.exoplayercore.repack.M r4 = r4.k
            if (r4 == 0) goto L_0x0068
            com.dreamers.exoplayercore.repack.F r4 = r0.e
            int[] r5 = r4.A
            r4.a((int) r13, (int) r14, (int[]) r5)
            com.dreamers.exoplayercore.repack.F r4 = r0.e
            int[] r4 = r4.A
            r4 = r4[r1]
            com.dreamers.exoplayercore.repack.F r5 = r0.e
            int[] r5 = r5.A
            r5 = r5[r2]
            int r6 = r13 - r4
            int r7 = r14 - r5
            goto L_0x006c
        L_0x0068:
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
        L_0x006c:
            com.dreamers.exoplayercore.repack.F r8 = r0.e
            java.util.ArrayList r8 = r8.m
            boolean r8 = r8.isEmpty()
            if (r8 != 0) goto L_0x007b
            com.dreamers.exoplayercore.repack.F r8 = r0.e
            r8.invalidate()
        L_0x007b:
            com.dreamers.exoplayercore.repack.F r8 = r0.e
            int r8 = r8.getOverScrollMode()
            r9 = 2
            if (r8 == r9) goto L_0x0089
            com.dreamers.exoplayercore.repack.F r8 = r0.e
            r8.b(r13, r14)
        L_0x0089:
            com.dreamers.exoplayercore.repack.F r15 = r0.e
            r20 = 0
            r21 = 1
            r16 = r4
            r17 = r5
            r18 = r6
            r19 = r7
            boolean r8 = r15.dispatchNestedScroll(r16, r17, r18, r19, r20, r21)
            if (r8 != 0) goto L_0x00df
            if (r6 != 0) goto L_0x00a1
            if (r7 == 0) goto L_0x00df
        L_0x00a1:
            float r8 = r3.getCurrVelocity()
            int r8 = (int) r8
            if (r6 == r11) goto L_0x00b0
            if (r6 >= 0) goto L_0x00ac
            int r10 = -r8
            goto L_0x00b1
        L_0x00ac:
            if (r6 <= 0) goto L_0x00b0
            r10 = r8
            goto L_0x00b1
        L_0x00b0:
            r10 = 0
        L_0x00b1:
            if (r7 == r12) goto L_0x00ba
            if (r7 >= 0) goto L_0x00b7
            int r8 = -r8
            goto L_0x00bb
        L_0x00b7:
            if (r7 <= 0) goto L_0x00ba
            goto L_0x00bb
        L_0x00ba:
            r8 = 0
        L_0x00bb:
            com.dreamers.exoplayercore.repack.F r15 = r0.e
            int r15 = r15.getOverScrollMode()
            if (r15 == r9) goto L_0x00c8
            com.dreamers.exoplayercore.repack.F r9 = r0.e
            r9.c(r10, r8)
        L_0x00c8:
            if (r10 != 0) goto L_0x00d2
            if (r6 == r11) goto L_0x00d2
            int r6 = r3.getFinalX()
            if (r6 != 0) goto L_0x00df
        L_0x00d2:
            if (r8 != 0) goto L_0x00dc
            if (r7 == r12) goto L_0x00dc
            int r6 = r3.getFinalY()
            if (r6 != 0) goto L_0x00df
        L_0x00dc:
            r3.abortAnimation()
        L_0x00df:
            if (r4 != 0) goto L_0x00e3
            if (r5 == 0) goto L_0x00e8
        L_0x00e3:
            com.dreamers.exoplayercore.repack.F r6 = r0.e
            r6.h()
        L_0x00e8:
            com.dreamers.exoplayercore.repack.F r6 = r0.e
            boolean r6 = r6.awakenScrollBars()
            if (r6 != 0) goto L_0x00f5
            com.dreamers.exoplayercore.repack.F r6 = r0.e
            r6.invalidate()
        L_0x00f5:
            if (r14 == 0) goto L_0x0105
            com.dreamers.exoplayercore.repack.F r6 = r0.e
            com.dreamers.exoplayercore.repack.U r6 = r6.l
            boolean r6 = r6.e()
            if (r6 == 0) goto L_0x0105
            if (r5 != r14) goto L_0x0105
            r5 = 1
            goto L_0x0106
        L_0x0105:
            r5 = 0
        L_0x0106:
            if (r13 == 0) goto L_0x0116
            com.dreamers.exoplayercore.repack.F r6 = r0.e
            com.dreamers.exoplayercore.repack.U r6 = r6.l
            boolean r6 = r6.d()
            if (r6 == 0) goto L_0x0116
            if (r4 != r13) goto L_0x0116
            r4 = 1
            goto L_0x0117
        L_0x0116:
            r4 = 0
        L_0x0117:
            if (r13 != 0) goto L_0x011b
            if (r14 == 0) goto L_0x0122
        L_0x011b:
            if (r4 != 0) goto L_0x0122
            if (r5 == 0) goto L_0x0120
            goto L_0x0122
        L_0x0120:
            r4 = 0
            goto L_0x0123
        L_0x0122:
            r4 = 1
        L_0x0123:
            boolean r3 = r3.isFinished()
            if (r3 != 0) goto L_0x0147
            if (r4 != 0) goto L_0x0134
            com.dreamers.exoplayercore.repack.F r3 = r0.e
            boolean r3 = r3.hasNestedScrollingParent(r2)
            if (r3 != 0) goto L_0x0134
            goto L_0x0147
        L_0x0134:
            r22.a()
            com.dreamers.exoplayercore.repack.F r2 = r0.e
            com.dreamers.exoplayercore.repack.r r2 = r2.s
            if (r2 == 0) goto L_0x015c
            com.dreamers.exoplayercore.repack.F r2 = r0.e
            com.dreamers.exoplayercore.repack.r r2 = r2.s
            com.dreamers.exoplayercore.repack.F r3 = r0.e
            r2.a((com.dreamers.exoplayercore.repack.F) r3, (int) r13, (int) r14)
            goto L_0x015c
        L_0x0147:
            com.dreamers.exoplayercore.repack.F r3 = r0.e
            r3.a((int) r1)
            boolean r3 = com.dreamers.exoplayercore.repack.F.d
            if (r3 == 0) goto L_0x0157
            com.dreamers.exoplayercore.repack.F r3 = r0.e
            com.dreamers.exoplayercore.repack.t r3 = r3.t
            r3.a()
        L_0x0157:
            com.dreamers.exoplayercore.repack.F r3 = r0.e
            r3.stopNestedScroll(r2)
        L_0x015c:
            r0.f = r1
            boolean r1 = r0.g
            if (r1 == 0) goto L_0x0165
            r22.a()
        L_0x0165:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dreamers.exoplayercore.repack.C0009ah.run():void");
    }
}
