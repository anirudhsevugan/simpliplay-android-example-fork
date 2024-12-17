package com.dreamers.exoplayercore.repack;

import androidx.core.location.LocationRequestCompat;
import androidx.core.os.TraceCompat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/* renamed from: com.dreamers.exoplayercore.repack.r  reason: case insensitive filesystem */
final class C0095r implements Runnable {
    static final ThreadLocal a = new ThreadLocal();
    private static Comparator f = new C0096s();
    ArrayList b = new ArrayList();
    long c;
    private long d;
    private ArrayList e = new ArrayList();

    C0095r() {
    }

    private static C0010ai a(F f2, int i, long j) {
        boolean z;
        int b2 = f2.g.b();
        int i2 = 0;
        while (true) {
            if (i2 >= b2) {
                z = false;
                break;
            }
            C0010ai a2 = F.a(f2.g.c(i2));
            if (a2.mPosition == i && !a2.isInvalid()) {
                z = true;
                break;
            }
            i2++;
        }
        if (z) {
            return null;
        }
        C0004ac acVar = f2.e;
        try {
            f2.e();
            C0010ai a3 = acVar.a(i, j);
            if (a3 != null) {
                if (!a3.isBound() || a3.isInvalid()) {
                    acVar.a(a3, false);
                } else {
                    acVar.a(a3.itemView);
                }
            }
            return a3;
        } finally {
            f2.b(false);
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(F f2, int i, int i2) {
        if (f2.isAttachedToWindow() && this.d == 0) {
            this.d = F.j();
            f2.post(this);
        }
        C0097t tVar = f2.t;
        tVar.a = i;
        tVar.b = i2;
    }

    public final void run() {
        F f2;
        C0098u uVar;
        long j = 0;
        try {
            TraceCompat.beginSection("RV Prefetch");
            if (!this.b.isEmpty()) {
                int size = this.b.size();
                long j2 = 0;
                for (int i = 0; i < size; i++) {
                    F f3 = (F) this.b.get(i);
                    if (f3.getWindowVisibility() == 0) {
                        j2 = Math.max(f3.getDrawingTime(), j2);
                    }
                }
                if (j2 != 0) {
                    long nanos = TimeUnit.MILLISECONDS.toNanos(j2) + this.c;
                    int size2 = this.b.size();
                    int i2 = 0;
                    for (int i3 = 0; i3 < size2; i3++) {
                        F f4 = (F) this.b.get(i3);
                        if (f4.getWindowVisibility() == 0) {
                            f4.t.a(f4, false);
                            i2 += f4.t.d;
                        }
                    }
                    this.e.ensureCapacity(i2);
                    int i4 = 0;
                    for (int i5 = 0; i5 < size2; i5++) {
                        F f5 = (F) this.b.get(i5);
                        if (f5.getWindowVisibility() == 0) {
                            C0097t tVar = f5.t;
                            int abs = Math.abs(tVar.a) + Math.abs(tVar.b);
                            int i6 = 0;
                            while (i6 < (tVar.d << 1)) {
                                if (i4 >= this.e.size()) {
                                    uVar = new C0098u();
                                    this.e.add(uVar);
                                } else {
                                    uVar = (C0098u) this.e.get(i4);
                                }
                                int i7 = tVar.c[i6 + 1];
                                try {
                                    uVar.a = i7 <= abs;
                                    uVar.b = abs;
                                    uVar.c = i7;
                                    uVar.d = f5;
                                    uVar.e = tVar.c[i6];
                                    i4++;
                                    i6 += 2;
                                } catch (Throwable th) {
                                    th = th;
                                    j = 0;
                                    this.d = j;
                                    TraceCompat.endSection();
                                    throw th;
                                }
                            }
                        }
                    }
                    Collections.sort(this.e, f);
                    for (int i8 = 0; i8 < this.e.size(); i8++) {
                        C0098u uVar2 = (C0098u) this.e.get(i8);
                        if (uVar2.d == null) {
                            break;
                        }
                        C0010ai a2 = a(uVar2.d, uVar2.e, uVar2.a ? LocationRequestCompat.PASSIVE_INTERVAL : nanos);
                        if (!(a2 == null || a2.mNestedRecyclerView == null || !a2.isBound() || a2.isInvalid() || (f2 = (F) a2.mNestedRecyclerView.get()) == null)) {
                            if (f2.q && f2.g.b() != 0) {
                                f2.b();
                            }
                            C0097t tVar2 = f2.t;
                            tVar2.a(f2, true);
                            if (tVar2.d != 0) {
                                TraceCompat.beginSection("RV Nested Prefetch");
                                C0008ag agVar = f2.u;
                                M m = f2.k;
                                agVar.d = 1;
                                agVar.e = m.getItemCount();
                                agVar.g = false;
                                agVar.h = false;
                                agVar.i = false;
                                for (int i9 = 0; i9 < (tVar2.d << 1); i9 += 2) {
                                    a(f2, tVar2.c[i9], nanos);
                                }
                                TraceCompat.endSection();
                            }
                        }
                        uVar2.a = false;
                        uVar2.b = 0;
                        uVar2.c = 0;
                        uVar2.d = null;
                        uVar2.e = 0;
                    }
                    j = 0;
                }
            }
            this.d = j;
            TraceCompat.endSection();
        } catch (Throwable th2) {
            th = th2;
            this.d = j;
            TraceCompat.endSection();
            throw th;
        }
    }
}
