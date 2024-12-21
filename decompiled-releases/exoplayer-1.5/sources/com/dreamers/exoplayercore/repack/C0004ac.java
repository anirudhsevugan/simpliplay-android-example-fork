package com.dreamers.exoplayercore.repack;

import android.view.View;
import android.view.ViewGroup;
import androidx.core.location.LocationRequestCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: com.dreamers.exoplayercore.repack.ac  reason: case insensitive filesystem */
public final class C0004ac {
    final ArrayList a;
    ArrayList b = null;
    final ArrayList c = new ArrayList();
    final List d;
    private int e;
    private int f;
    private C0002aa g;
    private /* synthetic */ F h;

    public C0004ac(F f2) {
        this.h = f2;
        ArrayList arrayList = new ArrayList();
        this.a = arrayList;
        this.d = Collections.unmodifiableList(arrayList);
        this.e = 2;
        this.f = 2;
    }

    private C0010ai a(long j, int i) {
        for (int size = this.a.size() - 1; size >= 0; size--) {
            C0010ai aiVar = (C0010ai) this.a.get(size);
            if (aiVar.getItemId() == j && !aiVar.wasReturnedFromScrap()) {
                if (i == aiVar.getItemViewType()) {
                    aiVar.addFlags(32);
                    if (aiVar.isRemoved() && !this.h.u.g) {
                        aiVar.setFlags(2, 14);
                    }
                    return aiVar;
                }
                this.a.remove(size);
                this.h.removeDetachedView(aiVar.itemView, false);
                b(aiVar.itemView);
            }
        }
        int size2 = this.c.size();
        while (true) {
            size2--;
            if (size2 < 0) {
                return null;
            }
            C0010ai aiVar2 = (C0010ai) this.c.get(size2);
            if (aiVar2.getItemId() == j) {
                if (i == aiVar2.getItemViewType()) {
                    this.c.remove(size2);
                    return aiVar2;
                }
                b(size2);
                return null;
            }
        }
    }

    private void a(ViewGroup viewGroup, boolean z) {
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount);
            if (childAt instanceof ViewGroup) {
                a((ViewGroup) childAt, true);
            }
        }
        if (z) {
            if (viewGroup.getVisibility() == 4) {
                viewGroup.setVisibility(0);
                viewGroup.setVisibility(4);
                return;
            }
            int visibility = viewGroup.getVisibility();
            viewGroup.setVisibility(4);
            viewGroup.setVisibility(visibility);
        }
    }

    private boolean a(C0010ai aiVar, int i, int i2, long j) {
        aiVar.mOwnerRecyclerView = this.h;
        int itemViewType = aiVar.getItemViewType();
        long j2 = F.j();
        if (j != LocationRequestCompat.PASSIVE_INTERVAL) {
            long j3 = this.g.b(itemViewType).d;
            if (!(j3 == 0 || j3 + j2 < j)) {
                return false;
            }
        }
        this.h.k.bindViewHolder(aiVar, i);
        long j4 = F.j();
        C0003ab b2 = this.g.b(aiVar.getItemViewType());
        b2.d = C0002aa.a(b2.d, j4 - j2);
        if (this.h.f()) {
            View view = aiVar.itemView;
            if (ViewCompat.getImportantForAccessibility(view) == 0) {
                ViewCompat.setImportantForAccessibility(view, 1);
            }
            if (!ViewCompat.hasAccessibilityDelegate(view)) {
                aiVar.addFlags(16384);
                ViewCompat.setAccessibilityDelegate(view, this.h.y.b);
            }
        }
        if (this.h.u.g) {
            aiVar.mPreLayoutPosition = i2;
        }
        return true;
    }

    private C0010ai c(int i) {
        int size;
        int a2;
        ArrayList arrayList = this.b;
        if (!(arrayList == null || (size = arrayList.size()) == 0)) {
            int i2 = 0;
            int i3 = 0;
            while (i3 < size) {
                C0010ai aiVar = (C0010ai) this.b.get(i3);
                if (aiVar.wasReturnedFromScrap() || aiVar.getLayoutPosition() != i) {
                    i3++;
                } else {
                    aiVar.addFlags(32);
                    return aiVar;
                }
            }
            if (this.h.k.hasStableIds() && (a2 = this.h.f.a(i, 0)) > 0 && a2 < this.h.k.getItemCount()) {
                long itemId = this.h.k.getItemId(a2);
                while (i2 < size) {
                    C0010ai aiVar2 = (C0010ai) this.b.get(i2);
                    if (aiVar2.wasReturnedFromScrap() || aiVar2.getItemId() != itemId) {
                        i2++;
                    } else {
                        aiVar2.addFlags(32);
                        return aiVar2;
                    }
                }
            }
        }
        return null;
    }

    private boolean c(C0010ai aiVar) {
        if (aiVar.isRemoved()) {
            return this.h.u.g;
        }
        if (aiVar.mPosition < 0 || aiVar.mPosition >= this.h.k.getItemCount()) {
            throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + aiVar + this.h.a());
        } else if (this.h.u.g || this.h.k.getItemViewType(aiVar.mPosition) == aiVar.getItemViewType()) {
            return !this.h.k.hasStableIds() || aiVar.getItemId() == this.h.k.getItemId(aiVar.mPosition);
        } else {
            return false;
        }
    }

    private C0010ai d(int i) {
        View view;
        int size = this.a.size();
        int i2 = 0;
        int i3 = 0;
        while (i3 < size) {
            C0010ai aiVar = (C0010ai) this.a.get(i3);
            if (aiVar.wasReturnedFromScrap() || aiVar.getLayoutPosition() != i || aiVar.isInvalid() || (!this.h.u.g && aiVar.isRemoved())) {
                i3++;
            } else {
                aiVar.addFlags(32);
                return aiVar;
            }
        }
        C0081d dVar = this.h.g;
        int size2 = dVar.c.size();
        int i4 = 0;
        while (true) {
            if (i4 >= size2) {
                view = null;
                break;
            }
            view = (View) dVar.c.get(i4);
            C0010ai b2 = dVar.a.b(view);
            if (b2.getLayoutPosition() == i && !b2.isInvalid() && !b2.isRemoved()) {
                break;
            }
            i4++;
        }
        if (view != null) {
            C0010ai a2 = F.a(view);
            C0081d dVar2 = this.h.g;
            int a3 = dVar2.a.a(view);
            if (a3 < 0) {
                throw new IllegalArgumentException("view is not a child, cannot hide ".concat(String.valueOf(view)));
            } else if (dVar2.b.c(a3)) {
                dVar2.b.b(a3);
                dVar2.b(view);
                int c2 = this.h.g.c(view);
                if (c2 != -1) {
                    this.h.g.d(c2);
                    c(view);
                    a2.addFlags(8224);
                    return a2;
                }
                throw new IllegalStateException("layout index should not be -1 after unhiding a view:" + a2 + this.h.a());
            } else {
                throw new RuntimeException("trying to unhide a view that was not hidden".concat(String.valueOf(view)));
            }
        } else {
            int size3 = this.c.size();
            while (i2 < size3) {
                C0010ai aiVar2 = (C0010ai) this.c.get(i2);
                if (aiVar2.isInvalid() || aiVar2.getLayoutPosition() != i) {
                    i2++;
                } else {
                    this.c.remove(i2);
                    return aiVar2;
                }
            }
            return null;
        }
    }

    private void d(C0010ai aiVar) {
        if (aiVar.itemView instanceof ViewGroup) {
            a((ViewGroup) aiVar.itemView, false);
        }
    }

    private void e(C0010ai aiVar) {
        if (this.h.k != null) {
            this.h.k.onViewRecycled(aiVar);
        }
        this.h.h.e(aiVar);
    }

    private void f() {
        for (int size = this.c.size() - 1; size >= 0; size--) {
            b(size);
        }
        this.c.clear();
        if (F.d) {
            this.h.t.a();
        }
    }

    /* access modifiers changed from: package-private */
    public final View a(int i) {
        return a(i, (long) LocationRequestCompat.PASSIVE_INTERVAL).itemView;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0144  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0161  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0164  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0194  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x01a2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.dreamers.exoplayercore.repack.C0010ai a(int r18, long r19) {
        /*
            r17 = this;
            r6 = r17
            r3 = r18
            if (r3 < 0) goto L_0x01be
            com.dreamers.exoplayercore.repack.F r0 = r6.h
            com.dreamers.exoplayercore.repack.ag r0 = r0.u
            int r0 = r0.a()
            if (r3 >= r0) goto L_0x01be
            com.dreamers.exoplayercore.repack.F r0 = r6.h
            com.dreamers.exoplayercore.repack.ag r0 = r0.u
            boolean r0 = r0.g
            r1 = 0
            r7 = 1
            r8 = 0
            if (r0 == 0) goto L_0x0023
            com.dreamers.exoplayercore.repack.ai r0 = r17.c((int) r18)
            if (r0 == 0) goto L_0x0024
            r2 = 1
            goto L_0x0025
        L_0x0023:
            r0 = r1
        L_0x0024:
            r2 = 0
        L_0x0025:
            if (r0 != 0) goto L_0x0057
            com.dreamers.exoplayercore.repack.ai r0 = r17.d((int) r18)
            if (r0 == 0) goto L_0x0057
            boolean r4 = r6.c((com.dreamers.exoplayercore.repack.C0010ai) r0)
            if (r4 != 0) goto L_0x0056
            r4 = 4
            r0.addFlags(r4)
            boolean r4 = r0.isScrap()
            if (r4 == 0) goto L_0x0048
            com.dreamers.exoplayercore.repack.F r4 = r6.h
            android.view.View r5 = r0.itemView
            r4.removeDetachedView(r5, r8)
            r0.unScrap()
            goto L_0x0051
        L_0x0048:
            boolean r4 = r0.wasReturnedFromScrap()
            if (r4 == 0) goto L_0x0051
            r0.clearReturnedFromScrapFlag()
        L_0x0051:
            r6.a((com.dreamers.exoplayercore.repack.C0010ai) r0)
            r0 = r1
            goto L_0x0057
        L_0x0056:
            r2 = 1
        L_0x0057:
            if (r0 != 0) goto L_0x0125
            com.dreamers.exoplayercore.repack.F r4 = r6.h
            com.dreamers.exoplayercore.repack.a r4 = r4.f
            int r4 = r4.b((int) r3)
            if (r4 < 0) goto L_0x00ea
            com.dreamers.exoplayercore.repack.F r5 = r6.h
            com.dreamers.exoplayercore.repack.M r5 = r5.k
            int r5 = r5.getItemCount()
            if (r4 >= r5) goto L_0x00ea
            com.dreamers.exoplayercore.repack.F r5 = r6.h
            com.dreamers.exoplayercore.repack.M r5 = r5.k
            int r5 = r5.getItemViewType(r4)
            com.dreamers.exoplayercore.repack.F r9 = r6.h
            com.dreamers.exoplayercore.repack.M r9 = r9.k
            boolean r9 = r9.hasStableIds()
            if (r9 == 0) goto L_0x0090
            com.dreamers.exoplayercore.repack.F r0 = r6.h
            com.dreamers.exoplayercore.repack.M r0 = r0.k
            long r9 = r0.getItemId(r4)
            com.dreamers.exoplayercore.repack.ai r0 = r6.a((long) r9, (int) r5)
            if (r0 == 0) goto L_0x0090
            r0.mPosition = r4
            r2 = 1
        L_0x0090:
            if (r0 != 0) goto L_0x00a6
            com.dreamers.exoplayercore.repack.aa r0 = r17.c()
            com.dreamers.exoplayercore.repack.ai r0 = r0.a((int) r5)
            if (r0 == 0) goto L_0x00a6
            r0.resetInternal()
            boolean r4 = com.dreamers.exoplayercore.repack.F.a
            if (r4 == 0) goto L_0x00a6
            r6.d((com.dreamers.exoplayercore.repack.C0010ai) r0)
        L_0x00a6:
            if (r0 != 0) goto L_0x0125
            long r15 = com.dreamers.exoplayercore.repack.F.j()
            r9 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            int r0 = (r19 > r9 ? 1 : (r19 == r9 ? 0 : -1))
            if (r0 == 0) goto L_0x00c2
            com.dreamers.exoplayercore.repack.aa r9 = r6.g
            r10 = r5
            r11 = r15
            r13 = r19
            boolean r0 = r9.a(r10, r11, r13)
            if (r0 != 0) goto L_0x00c2
            return r1
        L_0x00c2:
            com.dreamers.exoplayercore.repack.F r0 = r6.h
            com.dreamers.exoplayercore.repack.M r0 = r0.k
            com.dreamers.exoplayercore.repack.F r1 = r6.h
            com.dreamers.exoplayercore.repack.ai r0 = r0.createViewHolder(r1, r5)
            boolean r1 = com.dreamers.exoplayercore.repack.F.d
            if (r1 == 0) goto L_0x00df
            android.view.View r1 = r0.itemView
            com.dreamers.exoplayercore.repack.F r1 = com.dreamers.exoplayercore.repack.F.b((android.view.View) r1)
            if (r1 == 0) goto L_0x00df
            java.lang.ref.WeakReference r4 = new java.lang.ref.WeakReference
            r4.<init>(r1)
            r0.mNestedRecyclerView = r4
        L_0x00df:
            long r9 = com.dreamers.exoplayercore.repack.F.j()
            com.dreamers.exoplayercore.repack.aa r1 = r6.g
            long r9 = r9 - r15
            r1.a((int) r5, (long) r9)
            goto L_0x0125
        L_0x00ea:
            java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Inconsistency detected. Invalid item position "
            r1.<init>(r2)
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.String r2 = "(offset:"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r4)
            java.lang.String r2 = ").state:"
            java.lang.StringBuilder r1 = r1.append(r2)
            com.dreamers.exoplayercore.repack.F r2 = r6.h
            com.dreamers.exoplayercore.repack.ag r2 = r2.u
            int r2 = r2.a()
            java.lang.StringBuilder r1 = r1.append(r2)
            com.dreamers.exoplayercore.repack.F r2 = r6.h
            java.lang.String r2 = r2.a()
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0125:
            r9 = r0
            r10 = r2
            if (r10 == 0) goto L_0x0153
            com.dreamers.exoplayercore.repack.F r0 = r6.h
            com.dreamers.exoplayercore.repack.ag r0 = r0.u
            boolean r0 = r0.g
            if (r0 != 0) goto L_0x0153
            r0 = 8192(0x2000, float:1.14794E-41)
            boolean r1 = r9.hasAnyOfTheFlags(r0)
            if (r1 == 0) goto L_0x0153
            r9.setFlags(r8, r0)
            com.dreamers.exoplayercore.repack.F r0 = r6.h
            com.dreamers.exoplayercore.repack.ag r0 = r0.u
            boolean r0 = r0.j
            if (r0 == 0) goto L_0x0153
            com.dreamers.exoplayercore.repack.Q.e(r9)
            r9.getUnmodifiedPayloads()
            com.dreamers.exoplayercore.repack.S r0 = com.dreamers.exoplayercore.repack.Q.d(r9)
            com.dreamers.exoplayercore.repack.F r1 = r6.h
            r1.a((com.dreamers.exoplayercore.repack.C0010ai) r9, (com.dreamers.exoplayercore.repack.S) r0)
        L_0x0153:
            com.dreamers.exoplayercore.repack.F r0 = r6.h
            com.dreamers.exoplayercore.repack.ag r0 = r0.u
            boolean r0 = r0.g
            if (r0 == 0) goto L_0x0164
            boolean r0 = r9.isBound()
            if (r0 == 0) goto L_0x0164
            r9.mPreLayoutPosition = r3
            goto L_0x0177
        L_0x0164:
            boolean r0 = r9.isBound()
            if (r0 == 0) goto L_0x0179
            boolean r0 = r9.needsUpdate()
            if (r0 != 0) goto L_0x0179
            boolean r0 = r9.isInvalid()
            if (r0 == 0) goto L_0x0177
            goto L_0x0179
        L_0x0177:
            r0 = 0
            goto L_0x018c
        L_0x0179:
            com.dreamers.exoplayercore.repack.F r0 = r6.h
            com.dreamers.exoplayercore.repack.a r0 = r0.f
            int r2 = r0.b((int) r3)
            r0 = r17
            r1 = r9
            r3 = r18
            r4 = r19
            boolean r0 = r0.a(r1, r2, r3, r4)
        L_0x018c:
            android.view.View r1 = r9.itemView
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            if (r1 != 0) goto L_0x01a2
            com.dreamers.exoplayercore.repack.F r1 = r6.h
            android.view.ViewGroup$LayoutParams r1 = r1.generateDefaultLayoutParams()
        L_0x019a:
            com.dreamers.exoplayercore.repack.Y r1 = (com.dreamers.exoplayercore.repack.Y) r1
            android.view.View r2 = r9.itemView
            r2.setLayoutParams(r1)
            goto L_0x01b3
        L_0x01a2:
            com.dreamers.exoplayercore.repack.F r2 = r6.h
            boolean r2 = r2.checkLayoutParams(r1)
            if (r2 != 0) goto L_0x01b1
            com.dreamers.exoplayercore.repack.F r2 = r6.h
            android.view.ViewGroup$LayoutParams r1 = r2.generateLayoutParams((android.view.ViewGroup.LayoutParams) r1)
            goto L_0x019a
        L_0x01b1:
            com.dreamers.exoplayercore.repack.Y r1 = (com.dreamers.exoplayercore.repack.Y) r1
        L_0x01b3:
            r1.a = r9
            if (r10 == 0) goto L_0x01ba
            if (r0 == 0) goto L_0x01ba
            goto L_0x01bb
        L_0x01ba:
            r7 = 0
        L_0x01bb:
            r1.d = r7
            return r9
        L_0x01be:
            java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Invalid item position "
            r1.<init>(r2)
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.String r2 = "("
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.String r2 = "). Item count:"
            java.lang.StringBuilder r1 = r1.append(r2)
            com.dreamers.exoplayercore.repack.F r2 = r6.h
            com.dreamers.exoplayercore.repack.ag r2 = r2.u
            int r2 = r2.a()
            java.lang.StringBuilder r1 = r1.append(r2)
            com.dreamers.exoplayercore.repack.F r2 = r6.h
            java.lang.String r2 = r2.a()
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            goto L_0x01fa
        L_0x01f9:
            throw r0
        L_0x01fa:
            goto L_0x01f9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dreamers.exoplayercore.repack.C0004ac.a(int, long):com.dreamers.exoplayercore.repack.ai");
    }

    public final void a() {
        this.a.clear();
        f();
    }

    public final void a(View view) {
        C0010ai a2 = F.a(view);
        if (a2.isTmpDetached()) {
            this.h.removeDetachedView(view, false);
        }
        if (a2.isScrap()) {
            a2.unScrap();
        } else if (a2.wasReturnedFromScrap()) {
            a2.clearReturnedFromScrapFlag();
        }
        a(a2);
    }

    /* access modifiers changed from: package-private */
    public final void a(C0010ai aiVar) {
        boolean z;
        boolean z2 = false;
        boolean z3 = true;
        if (aiVar.isScrap() || aiVar.itemView.getParent() != null) {
            StringBuilder append = new StringBuilder("Scrapped or attached views may not be recycled. isScrap:").append(aiVar.isScrap()).append(" isAttached:");
            if (aiVar.itemView.getParent() != null) {
                z2 = true;
            }
            throw new IllegalArgumentException(append.append(z2).append(this.h.a()).toString());
        } else if (aiVar.isTmpDetached()) {
            throw new IllegalArgumentException("Tmp detached view should be removed from RecyclerView before it can be recycled: " + aiVar + this.h.a());
        } else if (!aiVar.shouldIgnore()) {
            boolean doesTransientStatePreventRecycling = aiVar.doesTransientStatePreventRecycling();
            if ((this.h.k != null && doesTransientStatePreventRecycling && this.h.k.onFailedToRecycleView(aiVar)) || aiVar.isRecyclable()) {
                if (this.f <= 0 || aiVar.hasAnyOfTheFlags(526)) {
                    z = false;
                } else {
                    int size = this.c.size();
                    if (size >= this.f && size > 0) {
                        b(0);
                        size--;
                    }
                    if (F.d && size > 0 && !this.h.t.a(aiVar.mPosition)) {
                        do {
                            size--;
                            if (size < 0) {
                                break;
                            }
                        } while (this.h.t.a(((C0010ai) this.c.get(size)).mPosition));
                        size++;
                    }
                    this.c.add(size, aiVar);
                    z = true;
                }
                if (!z) {
                    a(aiVar, true);
                    z2 = z;
                    this.h.h.e(aiVar);
                    if (!z2 && !z3 && doesTransientStatePreventRecycling) {
                        aiVar.mOwnerRecyclerView = null;
                        return;
                    }
                    return;
                }
                z2 = z;
            }
            z3 = false;
            this.h.h.e(aiVar);
            if (!z2) {
            }
        } else {
            throw new IllegalArgumentException("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle." + this.h.a());
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(C0010ai aiVar, boolean z) {
        F.c(aiVar);
        if (aiVar.hasAnyOfTheFlags(16384)) {
            aiVar.setFlags(0, 16384);
            ViewCompat.setAccessibilityDelegate(aiVar.itemView, (AccessibilityDelegateCompat) null);
        }
        if (z) {
            e(aiVar);
        }
        aiVar.mOwnerRecyclerView = null;
        c().a(aiVar);
    }

    /* access modifiers changed from: package-private */
    public final void b() {
        this.f = this.e + (this.h.l != null ? this.h.l.f : 0);
        for (int size = this.c.size() - 1; size >= 0 && this.c.size() > this.f; size--) {
            b(size);
        }
    }

    /* access modifiers changed from: package-private */
    public final void b(int i) {
        a((C0010ai) this.c.get(i), true);
        this.c.remove(i);
    }

    /* access modifiers changed from: package-private */
    public final void b(View view) {
        C0010ai a2 = F.a(view);
        a2.mScrapContainer = null;
        a2.mInChangeScrap = false;
        a2.clearReturnedFromScrapFlag();
        a(a2);
    }

    /* access modifiers changed from: package-private */
    public final void b(C0010ai aiVar) {
        (aiVar.mInChangeScrap ? this.b : this.a).remove(aiVar);
        aiVar.mScrapContainer = null;
        aiVar.mInChangeScrap = false;
        aiVar.clearReturnedFromScrapFlag();
    }

    /* access modifiers changed from: package-private */
    public final C0002aa c() {
        if (this.g == null) {
            this.g = new C0002aa();
        }
        return this.g;
    }

    /* access modifiers changed from: package-private */
    public final void c(View view) {
        ArrayList arrayList;
        C0010ai a2 = F.a(view);
        if (!a2.hasAnyOfTheFlags(12) && a2.isUpdated() && !this.h.b(a2)) {
            if (this.b == null) {
                this.b = new ArrayList();
            }
            a2.setScrapContainer(this, true);
            arrayList = this.b;
        } else if (!a2.isInvalid() || a2.isRemoved() || this.h.k.hasStableIds()) {
            a2.setScrapContainer(this, false);
            arrayList = this.a;
        } else {
            throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool." + this.h.a());
        }
        arrayList.add(a2);
    }

    /* access modifiers changed from: package-private */
    public final void d() {
        int size = this.c.size();
        for (int i = 0; i < size; i++) {
            C0010ai aiVar = (C0010ai) this.c.get(i);
            if (aiVar != null) {
                aiVar.addFlags(6);
                aiVar.addChangePayload((Object) null);
            }
        }
        if (this.h.k == null || !this.h.k.hasStableIds()) {
            f();
        }
    }

    /* access modifiers changed from: package-private */
    public final void e() {
        int size = this.c.size();
        for (int i = 0; i < size; i++) {
            ((C0010ai) this.c.get(i)).clearOldPosition();
        }
        int size2 = this.a.size();
        for (int i2 = 0; i2 < size2; i2++) {
            ((C0010ai) this.a.get(i2)).clearOldPosition();
        }
        ArrayList arrayList = this.b;
        if (arrayList != null) {
            int size3 = arrayList.size();
            for (int i3 = 0; i3 < size3; i3++) {
                ((C0010ai) this.b.get(i3)).clearOldPosition();
            }
        }
    }
}
