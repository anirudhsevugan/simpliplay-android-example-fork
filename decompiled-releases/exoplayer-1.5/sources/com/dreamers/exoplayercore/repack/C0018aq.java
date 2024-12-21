package com.dreamers.exoplayercore.repack;

import androidx.collection.ArrayMap;
import androidx.collection.LongSparseArray;

/* renamed from: com.dreamers.exoplayercore.repack.aq  reason: case insensitive filesystem */
final class C0018aq {
    private ArrayMap a = new ArrayMap();
    private LongSparseArray b = new LongSparseArray();

    C0018aq() {
    }

    /* access modifiers changed from: package-private */
    public final S a(C0010ai aiVar, int i) {
        C0019ar arVar;
        S s;
        int indexOfKey = this.a.indexOfKey(aiVar);
        if (indexOfKey < 0 || (arVar = (C0019ar) this.a.valueAt(indexOfKey)) == null || (arVar.a & i) == 0) {
            return null;
        }
        arVar.a &= i ^ -1;
        if (i == 4) {
            s = arVar.b;
        } else if (i == 8) {
            s = arVar.c;
        } else {
            throw new IllegalArgumentException("Must provide flag PRE or POST");
        }
        if ((arVar.a & 12) == 0) {
            this.a.removeAt(indexOfKey);
            C0019ar.a(arVar);
        }
        return s;
    }

    /* access modifiers changed from: package-private */
    public final C0010ai a(long j) {
        return (C0010ai) this.b.get(j);
    }

    /* access modifiers changed from: package-private */
    public final void a() {
        this.a.clear();
        this.b.clear();
    }

    /* access modifiers changed from: package-private */
    public final void a(long j, C0010ai aiVar) {
        this.b.put(j, aiVar);
    }

    /* access modifiers changed from: package-private */
    public final void a(C0010ai aiVar, S s) {
        C0019ar arVar = (C0019ar) this.a.get(aiVar);
        if (arVar == null) {
            arVar = C0019ar.a();
            this.a.put(aiVar, arVar);
        }
        arVar.b = s;
        arVar.a |= 4;
    }

    /* access modifiers changed from: package-private */
    public final void a(C0020as asVar) {
        S s;
        S s2;
        for (int size = this.a.size() - 1; size >= 0; size--) {
            C0010ai aiVar = (C0010ai) this.a.keyAt(size);
            C0019ar arVar = (C0019ar) this.a.removeAt(size);
            if ((arVar.a & 3) != 3) {
                if ((arVar.a & 1) == 0) {
                    if ((arVar.a & 14) != 14) {
                        if ((arVar.a & 12) == 12) {
                            asVar.c(aiVar, arVar.b, arVar.c);
                        } else if ((arVar.a & 4) != 0) {
                            s = arVar.b;
                            s2 = null;
                        } else if ((arVar.a & 8) == 0) {
                        }
                        C0019ar.a(arVar);
                    }
                    asVar.b(aiVar, arVar.b, arVar.c);
                    C0019ar.a(arVar);
                } else if (arVar.b != null) {
                    s = arVar.b;
                    s2 = arVar.c;
                }
                asVar.a(aiVar, s, s2);
                C0019ar.a(arVar);
            }
            asVar.a(aiVar);
            C0019ar.a(arVar);
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean a(C0010ai aiVar) {
        C0019ar arVar = (C0019ar) this.a.get(aiVar);
        return (arVar == null || (arVar.a & 1) == 0) ? false : true;
    }

    /* access modifiers changed from: package-private */
    public final void b(C0010ai aiVar, S s) {
        C0019ar arVar = (C0019ar) this.a.get(aiVar);
        if (arVar == null) {
            arVar = C0019ar.a();
            this.a.put(aiVar, arVar);
        }
        arVar.a |= 2;
        arVar.b = s;
    }

    /* access modifiers changed from: package-private */
    public final boolean b(C0010ai aiVar) {
        C0019ar arVar = (C0019ar) this.a.get(aiVar);
        return (arVar == null || (arVar.a & 4) == 0) ? false : true;
    }

    /* access modifiers changed from: package-private */
    public final void c(C0010ai aiVar) {
        C0019ar arVar = (C0019ar) this.a.get(aiVar);
        if (arVar == null) {
            arVar = C0019ar.a();
            this.a.put(aiVar, arVar);
        }
        arVar.a |= 1;
    }

    /* access modifiers changed from: package-private */
    public final void c(C0010ai aiVar, S s) {
        C0019ar arVar = (C0019ar) this.a.get(aiVar);
        if (arVar == null) {
            arVar = C0019ar.a();
            this.a.put(aiVar, arVar);
        }
        arVar.c = s;
        arVar.a |= 8;
    }

    /* access modifiers changed from: package-private */
    public final void d(C0010ai aiVar) {
        C0019ar arVar = (C0019ar) this.a.get(aiVar);
        if (arVar != null) {
            arVar.a &= -2;
        }
    }

    /* access modifiers changed from: package-private */
    public final void e(C0010ai aiVar) {
        int size = this.b.size() - 1;
        while (true) {
            if (size < 0) {
                break;
            } else if (aiVar == this.b.valueAt(size)) {
                this.b.removeAt(size);
                break;
            } else {
                size--;
            }
        }
        C0019ar arVar = (C0019ar) this.a.remove(aiVar);
        if (arVar != null) {
            C0019ar.a(arVar);
        }
    }
}
