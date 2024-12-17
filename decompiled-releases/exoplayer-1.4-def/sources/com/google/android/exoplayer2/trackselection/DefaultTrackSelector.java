package com.google.android.exoplayer2.trackselection;

import android.content.Context;
import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import androidx.appcompat.widget.ActivityChooserView;
import com.dreamers.exoplayercore.repack.C0052by;
import com.dreamers.exoplayercore.repack.C0075cu;
import com.dreamers.exoplayercore.repack.bG;
import com.dreamers.exoplayercore.repack.cR;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.RendererCapabilities$$CC;
import com.google.android.exoplayer2.RendererConfiguration;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.ExoTrackSelection;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionParameters;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class DefaultTrackSelector extends MappingTrackSelector {
    /* access modifiers changed from: private */
    public static final C0075cu FORMAT_VALUE_ORDERING = C0075cu.a(DefaultTrackSelector$$Lambda$0.a);
    private static final float FRACTION_TO_CONSIDER_FULLSCREEN = 0.98f;
    /* access modifiers changed from: private */
    public static final C0075cu NO_ORDER = C0075cu.a(DefaultTrackSelector$$Lambda$1.a);
    private static final int[] NO_TRACKS = new int[0];
    private final AtomicReference parametersReference;
    private final ExoTrackSelection.Factory trackSelectionFactory;

    public final class AudioTrackScore implements Comparable {
        public final boolean a;
        private final String b;
        private final Parameters c;
        private final boolean d;
        private final int e;
        private final int f;
        private final int g;
        private final int h;
        private final int i;
        private final boolean j;
        private final int k;
        private final int l;
        private final int m;
        private final int n;

        public AudioTrackScore(Format format, Parameters parameters, int i2) {
            int i3;
            int i4;
            int i5;
            this.c = parameters;
            this.b = DefaultTrackSelector.normalizeUndeterminedLanguageToNull(format.c);
            int i6 = 0;
            this.d = DefaultTrackSelector.isSupported(i2, false);
            int i7 = 0;
            while (true) {
                int size = parameters.C.size();
                i3 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
                if (i7 >= size) {
                    i7 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
                    i4 = 0;
                    break;
                }
                i4 = DefaultTrackSelector.getFormatLanguageScore(format, (String) parameters.C.get(i7), false);
                if (i4 > 0) {
                    break;
                }
                i7++;
            }
            this.f = i7;
            this.e = i4;
            this.g = Integer.bitCount(format.e & parameters.D);
            boolean z = true;
            this.j = (format.d & 1) != 0;
            this.k = format.w;
            this.l = format.x;
            this.m = format.h;
            if ((format.h != -1 && format.h > parameters.r) || (format.w != -1 && format.w > parameters.q)) {
                z = false;
            }
            this.a = z;
            String[] d2 = Util.d();
            int i8 = 0;
            while (true) {
                if (i8 >= d2.length) {
                    i8 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
                    i5 = 0;
                    break;
                }
                i5 = DefaultTrackSelector.getFormatLanguageScore(format, d2[i8], false);
                if (i5 > 0) {
                    break;
                }
                i8++;
            }
            this.h = i8;
            this.i = i5;
            while (true) {
                if (i6 < parameters.w.size()) {
                    if (format.l != null && format.l.equals(parameters.w.get(i6))) {
                        i3 = i6;
                        break;
                    }
                    i6++;
                } else {
                    break;
                }
            }
            this.n = i3;
        }

        /* renamed from: a */
        public final int compareTo(AudioTrackScore audioTrackScore) {
            C0075cu a2 = (!this.a || !this.d) ? DefaultTrackSelector.FORMAT_VALUE_ORDERING.a() : DefaultTrackSelector.FORMAT_VALUE_ORDERING;
            C0052by a3 = C0052by.a().b(this.d, audioTrackScore.d).a(Integer.valueOf(this.f), Integer.valueOf(audioTrackScore.f), C0075cu.b().a()).a(this.e, audioTrackScore.e).a(this.g, audioTrackScore.g).b(this.a, audioTrackScore.a).a(Integer.valueOf(this.n), Integer.valueOf(audioTrackScore.n), C0075cu.b().a()).a(Integer.valueOf(this.m), Integer.valueOf(audioTrackScore.m), this.c.x ? DefaultTrackSelector.FORMAT_VALUE_ORDERING.a() : DefaultTrackSelector.NO_ORDER).b(this.j, audioTrackScore.j).a(Integer.valueOf(this.h), Integer.valueOf(audioTrackScore.h), C0075cu.b().a()).a(this.i, audioTrackScore.i).a(Integer.valueOf(this.k), Integer.valueOf(audioTrackScore.k), a2).a(Integer.valueOf(this.l), Integer.valueOf(audioTrackScore.l), a2);
            Integer valueOf = Integer.valueOf(this.m);
            Integer valueOf2 = Integer.valueOf(audioTrackScore.m);
            if (!Util.a((Object) this.b, (Object) audioTrackScore.b)) {
                a2 = DefaultTrackSelector.NO_ORDER;
            }
            return a3.a(valueOf, valueOf2, a2).b();
        }
    }

    public final class OtherTrackScore implements Comparable {
        private final boolean a;
        private final boolean b;

        public OtherTrackScore(Format format, int i) {
            this.a = (format.d & 1) == 0 ? false : true;
            this.b = DefaultTrackSelector.isSupported(i, false);
        }

        /* renamed from: a */
        public final int compareTo(OtherTrackScore otherTrackScore) {
            return C0052by.a().b(this.b, otherTrackScore.b).b(this.a, otherTrackScore.a).b();
        }
    }

    public final class Parameters extends TrackSelectionParameters {
        public static final Parameters a = new ParametersBuilder().b();
        public final boolean A;
        public final boolean B;
        /* access modifiers changed from: private */
        public final SparseArray I;
        /* access modifiers changed from: private */
        public final SparseBooleanArray J;
        public final int b;
        public final int c;
        public final int d;
        public final int e;
        public final int f;
        public final int g;
        public final int h;
        public final int i;
        public final boolean j;
        public final boolean k;
        public final boolean l;
        public final int m;
        public final int n;
        public final boolean o;
        public final bG p;
        public final int q;
        public final int r;
        public final boolean s;
        public final boolean t;
        public final boolean u;
        public final boolean v;
        public final bG w;
        public final boolean x;
        public final boolean y;
        public final boolean z;

        static {
            new Parcelable.Creator() {
                public /* synthetic */ Object createFromParcel(Parcel parcel) {
                    return new Parameters(parcel);
                }

                public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                    return new Parameters[i];
                }
            };
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        Parameters(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z2, boolean z3, boolean z4, int i10, int i11, boolean z5, bG bGVar, bG bGVar2, int i12, int i13, int i14, boolean z6, boolean z7, boolean z8, boolean z9, bG bGVar3, bG bGVar4, int i15, boolean z10, int i16, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, SparseArray sparseArray, SparseBooleanArray sparseBooleanArray) {
            super(bGVar2, i12, bGVar4, i15, z10, i16);
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.e = i5;
            this.f = i6;
            this.g = i7;
            this.h = i8;
            this.i = i9;
            this.j = z2;
            this.k = z3;
            this.l = z4;
            this.m = i10;
            this.n = i11;
            this.o = z5;
            this.p = bGVar;
            this.q = i13;
            this.r = i14;
            this.s = z6;
            this.t = z7;
            this.u = z8;
            this.v = z9;
            this.w = bGVar3;
            this.x = z11;
            this.y = z12;
            this.z = z13;
            this.A = z14;
            this.B = z15;
            this.I = sparseArray;
            this.J = sparseBooleanArray;
        }

        Parameters(Parcel parcel) {
            super(parcel);
            this.b = parcel.readInt();
            this.c = parcel.readInt();
            this.d = parcel.readInt();
            this.e = parcel.readInt();
            this.f = parcel.readInt();
            this.g = parcel.readInt();
            this.h = parcel.readInt();
            this.i = parcel.readInt();
            this.j = Util.a(parcel);
            this.k = Util.a(parcel);
            this.l = Util.a(parcel);
            this.m = parcel.readInt();
            this.n = parcel.readInt();
            this.o = Util.a(parcel);
            ArrayList arrayList = new ArrayList();
            parcel.readList(arrayList, (ClassLoader) null);
            this.p = bG.a((Collection) arrayList);
            this.q = parcel.readInt();
            this.r = parcel.readInt();
            this.s = Util.a(parcel);
            this.t = Util.a(parcel);
            this.u = Util.a(parcel);
            this.v = Util.a(parcel);
            ArrayList arrayList2 = new ArrayList();
            parcel.readList(arrayList2, (ClassLoader) null);
            this.w = bG.a((Collection) arrayList2);
            this.x = Util.a(parcel);
            this.y = Util.a(parcel);
            this.z = Util.a(parcel);
            this.A = Util.a(parcel);
            this.B = Util.a(parcel);
            this.I = a(parcel);
            this.J = (SparseBooleanArray) Util.a((Object) parcel.readSparseBooleanArray());
        }

        private static SparseArray a(Parcel parcel) {
            int readInt = parcel.readInt();
            SparseArray sparseArray = new SparseArray(readInt);
            for (int i2 = 0; i2 < readInt; i2++) {
                int readInt2 = parcel.readInt();
                int readInt3 = parcel.readInt();
                HashMap hashMap = new HashMap(readInt3);
                for (int i3 = 0; i3 < readInt3; i3++) {
                    hashMap.put((TrackGroupArray) Assertions.b((Object) (TrackGroupArray) parcel.readParcelable(TrackGroupArray.class.getClassLoader())), (SelectionOverride) parcel.readParcelable(SelectionOverride.class.getClassLoader()));
                }
                sparseArray.put(readInt2, hashMap);
            }
            return sparseArray;
        }

        public static Parameters a(Context context) {
            return new ParametersBuilder(context).b();
        }

        public final ParametersBuilder a() {
            return new ParametersBuilder(this, (byte) 0);
        }

        public final boolean a(int i2) {
            return this.J.get(i2);
        }

        public final boolean a(int i2, TrackGroupArray trackGroupArray) {
            Map map = (Map) this.I.get(i2);
            return map != null && map.containsKey(trackGroupArray);
        }

        public final SelectionOverride b(int i2, TrackGroupArray trackGroupArray) {
            Map map = (Map) this.I.get(i2);
            if (map != null) {
                return (SelectionOverride) map.get(trackGroupArray);
            }
            return null;
        }

        public final int describeContents() {
            return 0;
        }

        /* JADX WARNING: Removed duplicated region for block: B:73:0x00ea  */
        /* JADX WARNING: Removed duplicated region for block: B:95:0x0158 A[RETURN] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean equals(java.lang.Object r11) {
            /*
                r10 = this;
                r0 = 1
                if (r10 != r11) goto L_0x0004
                return r0
            L_0x0004:
                r1 = 0
                if (r11 == 0) goto L_0x0159
                java.lang.Class r2 = r10.getClass()
                java.lang.Class r3 = r11.getClass()
                if (r2 == r3) goto L_0x0013
                goto L_0x0159
            L_0x0013:
                r2 = r11
                com.google.android.exoplayer2.trackselection.DefaultTrackSelector$Parameters r2 = (com.google.android.exoplayer2.trackselection.DefaultTrackSelector.Parameters) r2
                boolean r11 = super.equals(r11)
                if (r11 == 0) goto L_0x0159
                int r11 = r10.b
                int r3 = r2.b
                if (r11 != r3) goto L_0x0159
                int r11 = r10.c
                int r3 = r2.c
                if (r11 != r3) goto L_0x0159
                int r11 = r10.d
                int r3 = r2.d
                if (r11 != r3) goto L_0x0159
                int r11 = r10.e
                int r3 = r2.e
                if (r11 != r3) goto L_0x0159
                int r11 = r10.f
                int r3 = r2.f
                if (r11 != r3) goto L_0x0159
                int r11 = r10.g
                int r3 = r2.g
                if (r11 != r3) goto L_0x0159
                int r11 = r10.h
                int r3 = r2.h
                if (r11 != r3) goto L_0x0159
                int r11 = r10.i
                int r3 = r2.i
                if (r11 != r3) goto L_0x0159
                boolean r11 = r10.j
                boolean r3 = r2.j
                if (r11 != r3) goto L_0x0159
                boolean r11 = r10.k
                boolean r3 = r2.k
                if (r11 != r3) goto L_0x0159
                boolean r11 = r10.l
                boolean r3 = r2.l
                if (r11 != r3) goto L_0x0159
                boolean r11 = r10.o
                boolean r3 = r2.o
                if (r11 != r3) goto L_0x0159
                int r11 = r10.m
                int r3 = r2.m
                if (r11 != r3) goto L_0x0159
                int r11 = r10.n
                int r3 = r2.n
                if (r11 != r3) goto L_0x0159
                com.dreamers.exoplayercore.repack.bG r11 = r10.p
                com.dreamers.exoplayercore.repack.bG r3 = r2.p
                boolean r11 = r11.equals(r3)
                if (r11 == 0) goto L_0x0159
                int r11 = r10.q
                int r3 = r2.q
                if (r11 != r3) goto L_0x0159
                int r11 = r10.r
                int r3 = r2.r
                if (r11 != r3) goto L_0x0159
                boolean r11 = r10.s
                boolean r3 = r2.s
                if (r11 != r3) goto L_0x0159
                boolean r11 = r10.t
                boolean r3 = r2.t
                if (r11 != r3) goto L_0x0159
                boolean r11 = r10.u
                boolean r3 = r2.u
                if (r11 != r3) goto L_0x0159
                boolean r11 = r10.v
                boolean r3 = r2.v
                if (r11 != r3) goto L_0x0159
                com.dreamers.exoplayercore.repack.bG r11 = r10.w
                com.dreamers.exoplayercore.repack.bG r3 = r2.w
                boolean r11 = r11.equals(r3)
                if (r11 == 0) goto L_0x0159
                boolean r11 = r10.x
                boolean r3 = r2.x
                if (r11 != r3) goto L_0x0159
                boolean r11 = r10.y
                boolean r3 = r2.y
                if (r11 != r3) goto L_0x0159
                boolean r11 = r10.z
                boolean r3 = r2.z
                if (r11 != r3) goto L_0x0159
                boolean r11 = r10.A
                boolean r3 = r2.A
                if (r11 != r3) goto L_0x0159
                boolean r11 = r10.B
                boolean r3 = r2.B
                if (r11 != r3) goto L_0x0159
                android.util.SparseBooleanArray r11 = r10.J
                android.util.SparseBooleanArray r3 = r2.J
                int r4 = r11.size()
                int r5 = r3.size()
                if (r5 == r4) goto L_0x00d6
            L_0x00d4:
                r11 = 0
                goto L_0x00e8
            L_0x00d6:
                r5 = 0
            L_0x00d7:
                if (r5 >= r4) goto L_0x00e7
                int r6 = r11.keyAt(r5)
                int r6 = r3.indexOfKey(r6)
                if (r6 >= 0) goto L_0x00e4
                goto L_0x00d4
            L_0x00e4:
                int r5 = r5 + 1
                goto L_0x00d7
            L_0x00e7:
                r11 = 1
            L_0x00e8:
                if (r11 == 0) goto L_0x0159
                android.util.SparseArray r11 = r10.I
                android.util.SparseArray r2 = r2.I
                int r3 = r11.size()
                int r4 = r2.size()
                if (r4 == r3) goto L_0x00fa
            L_0x00f8:
                r11 = 0
                goto L_0x0156
            L_0x00fa:
                r4 = 0
            L_0x00fb:
                if (r4 >= r3) goto L_0x0155
                int r5 = r11.keyAt(r4)
                int r5 = r2.indexOfKey(r5)
                if (r5 < 0) goto L_0x00f8
                java.lang.Object r6 = r11.valueAt(r4)
                java.util.Map r6 = (java.util.Map) r6
                java.lang.Object r5 = r2.valueAt(r5)
                java.util.Map r5 = (java.util.Map) r5
                int r7 = r6.size()
                int r8 = r5.size()
                if (r8 == r7) goto L_0x011f
            L_0x011d:
                r5 = 0
                goto L_0x014f
            L_0x011f:
                java.util.Set r6 = r6.entrySet()
                java.util.Iterator r6 = r6.iterator()
            L_0x0127:
                boolean r7 = r6.hasNext()
                if (r7 == 0) goto L_0x014e
                java.lang.Object r7 = r6.next()
                java.util.Map$Entry r7 = (java.util.Map.Entry) r7
                java.lang.Object r8 = r7.getKey()
                com.google.android.exoplayer2.source.TrackGroupArray r8 = (com.google.android.exoplayer2.source.TrackGroupArray) r8
                boolean r9 = r5.containsKey(r8)
                if (r9 == 0) goto L_0x011d
                java.lang.Object r7 = r7.getValue()
                java.lang.Object r8 = r5.get(r8)
                boolean r7 = com.google.android.exoplayer2.util.Util.a((java.lang.Object) r7, (java.lang.Object) r8)
                if (r7 != 0) goto L_0x0127
                goto L_0x011d
            L_0x014e:
                r5 = 1
            L_0x014f:
                if (r5 != 0) goto L_0x0152
                goto L_0x00f8
            L_0x0152:
                int r4 = r4 + 1
                goto L_0x00fb
            L_0x0155:
                r11 = 1
            L_0x0156:
                if (r11 == 0) goto L_0x0159
                return r0
            L_0x0159:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.trackselection.DefaultTrackSelector.Parameters.equals(java.lang.Object):boolean");
        }

        public final int hashCode() {
            return (((((((((((((((((((((((((((((((((((((((((((((((((((((super.hashCode() * 31) + this.b) * 31) + this.c) * 31) + this.d) * 31) + this.e) * 31) + this.f) * 31) + this.g) * 31) + this.h) * 31) + this.i) * 31) + (this.j ? 1 : 0)) * 31) + (this.k ? 1 : 0)) * 31) + (this.l ? 1 : 0)) * 31) + (this.o ? 1 : 0)) * 31) + this.m) * 31) + this.n) * 31) + this.p.hashCode()) * 31) + this.q) * 31) + this.r) * 31) + (this.s ? 1 : 0)) * 31) + (this.t ? 1 : 0)) * 31) + (this.u ? 1 : 0)) * 31) + (this.v ? 1 : 0)) * 31) + this.w.hashCode()) * 31) + (this.x ? 1 : 0)) * 31) + (this.y ? 1 : 0)) * 31) + (this.z ? 1 : 0)) * 31) + (this.A ? 1 : 0)) * 31) + (this.B ? 1 : 0);
        }

        public final void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
            parcel.writeInt(this.d);
            parcel.writeInt(this.e);
            parcel.writeInt(this.f);
            parcel.writeInt(this.g);
            parcel.writeInt(this.h);
            parcel.writeInt(this.i);
            Util.a(parcel, this.j);
            Util.a(parcel, this.k);
            Util.a(parcel, this.l);
            parcel.writeInt(this.m);
            parcel.writeInt(this.n);
            Util.a(parcel, this.o);
            parcel.writeList(this.p);
            parcel.writeInt(this.q);
            parcel.writeInt(this.r);
            Util.a(parcel, this.s);
            Util.a(parcel, this.t);
            Util.a(parcel, this.u);
            Util.a(parcel, this.v);
            parcel.writeList(this.w);
            Util.a(parcel, this.x);
            Util.a(parcel, this.y);
            Util.a(parcel, this.z);
            Util.a(parcel, this.A);
            Util.a(parcel, this.B);
            SparseArray sparseArray = this.I;
            int size = sparseArray.size();
            parcel.writeInt(size);
            for (int i3 = 0; i3 < size; i3++) {
                int keyAt = sparseArray.keyAt(i3);
                Map map = (Map) sparseArray.valueAt(i3);
                int size2 = map.size();
                parcel.writeInt(keyAt);
                parcel.writeInt(size2);
                for (Map.Entry entry : map.entrySet()) {
                    parcel.writeParcelable((Parcelable) entry.getKey(), 0);
                    parcel.writeParcelable((Parcelable) entry.getValue(), 0);
                }
            }
            parcel.writeSparseBooleanArray(this.J);
        }
    }

    public final class ParametersBuilder extends TrackSelectionParameters.Builder {
        private boolean A;
        private bG B;
        private boolean C;
        private boolean D;
        private boolean E;
        private boolean F;
        private boolean G;
        private final SparseArray H;
        private final SparseBooleanArray I;
        private int g;
        private int h;
        private int i;
        private int j;
        private int k;
        private int l;
        private int m;
        private int n;
        private boolean o;
        private boolean p;
        private boolean q;
        private int r;
        private int s;
        private boolean t;
        private bG u;
        private int v;
        private int w;
        private boolean x;
        private boolean y;
        private boolean z;

        public ParametersBuilder() {
            c();
            this.H = new SparseArray();
            this.I = new SparseBooleanArray();
        }

        public ParametersBuilder(Context context) {
            super(context);
            c();
            this.H = new SparseArray();
            this.I = new SparseBooleanArray();
            Point c = Util.c(context);
            int i2 = c.x;
            int i3 = c.y;
            this.r = i2;
            this.s = i3;
            this.t = true;
        }

        private ParametersBuilder(Parameters parameters) {
            super((TrackSelectionParameters) parameters);
            this.g = parameters.b;
            this.h = parameters.c;
            this.i = parameters.d;
            this.j = parameters.e;
            this.k = parameters.f;
            this.l = parameters.g;
            this.m = parameters.h;
            this.n = parameters.i;
            this.o = parameters.j;
            this.p = parameters.k;
            this.q = parameters.l;
            this.r = parameters.m;
            this.s = parameters.n;
            this.t = parameters.o;
            this.u = parameters.p;
            this.v = parameters.q;
            this.w = parameters.r;
            this.x = parameters.s;
            this.y = parameters.t;
            this.z = parameters.u;
            this.A = parameters.v;
            this.B = parameters.w;
            this.C = parameters.x;
            this.D = parameters.y;
            this.E = parameters.z;
            this.F = parameters.A;
            this.G = parameters.B;
            SparseArray a = parameters.I;
            SparseArray sparseArray = new SparseArray();
            for (int i2 = 0; i2 < a.size(); i2++) {
                sparseArray.put(a.keyAt(i2), new HashMap((Map) a.valueAt(i2)));
            }
            this.H = sparseArray;
            this.I = parameters.J.clone();
        }

        /* synthetic */ ParametersBuilder(Parameters parameters, byte b) {
            this(parameters);
        }

        private void c() {
            this.g = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            this.h = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            this.i = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            this.j = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            this.o = true;
            this.p = false;
            this.q = true;
            this.r = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            this.s = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            this.t = true;
            this.u = bG.g();
            this.v = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            this.w = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            this.x = true;
            this.y = false;
            this.z = false;
            this.A = false;
            this.B = bG.g();
            this.C = false;
            this.D = false;
            this.E = true;
            this.F = false;
            this.G = true;
        }

        /* renamed from: a */
        public final Parameters b() {
            return new Parameters(this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t, this.u, this.a, this.b, this.v, this.w, this.x, this.y, this.z, this.A, this.B, this.c, this.d, this.e, this.f, this.C, this.D, this.E, this.F, this.G, this.H, this.I);
        }

        public final ParametersBuilder a(int i2) {
            Map map = (Map) this.H.get(i2);
            if (map != null && !map.isEmpty()) {
                this.H.remove(i2);
            }
            return this;
        }

        public final ParametersBuilder a(int i2, TrackGroupArray trackGroupArray, SelectionOverride selectionOverride) {
            Map map = (Map) this.H.get(i2);
            if (map == null) {
                map = new HashMap();
                this.H.put(i2, map);
            }
            if (map.containsKey(trackGroupArray) && Util.a(map.get(trackGroupArray), (Object) selectionOverride)) {
                return this;
            }
            map.put(trackGroupArray, selectionOverride);
            return this;
        }

        public final ParametersBuilder a(int i2, boolean z2) {
            if (this.I.get(i2) == z2) {
                return this;
            }
            if (z2) {
                this.I.put(i2, true);
            } else {
                this.I.delete(i2);
            }
            return this;
        }

        public final /* bridge */ /* synthetic */ TrackSelectionParameters.Builder a(Context context) {
            super.a(context);
            return this;
        }
    }

    public final class SelectionOverride implements Parcelable {
        public final int a;
        public final int[] b;
        public final int c;
        public final int d;

        static {
            new Parcelable.Creator() {
                public /* synthetic */ Object createFromParcel(Parcel parcel) {
                    return new SelectionOverride(parcel);
                }

                public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                    return new SelectionOverride[i];
                }
            };
        }

        public SelectionOverride(int i, int... iArr) {
            this(i, iArr, (byte) 0);
        }

        private SelectionOverride(int i, int[] iArr, byte b2) {
            this.a = i;
            int[] copyOf = Arrays.copyOf(iArr, iArr.length);
            this.b = copyOf;
            this.c = iArr.length;
            this.d = 0;
            Arrays.sort(copyOf);
        }

        SelectionOverride(Parcel parcel) {
            this.a = parcel.readInt();
            int readByte = parcel.readByte();
            this.c = readByte;
            int[] iArr = new int[readByte];
            this.b = iArr;
            parcel.readIntArray(iArr);
            this.d = parcel.readInt();
        }

        public final int describeContents() {
            return 0;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                SelectionOverride selectionOverride = (SelectionOverride) obj;
                return this.a == selectionOverride.a && Arrays.equals(this.b, selectionOverride.b) && this.d == selectionOverride.d;
            }
        }

        public final int hashCode() {
            return (((this.a * 31) + Arrays.hashCode(this.b)) * 31) + this.d;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
            parcel.writeInt(this.b.length);
            parcel.writeIntArray(this.b);
            parcel.writeInt(this.d);
        }
    }

    public final class TextTrackScore implements Comparable {
        public final boolean a;
        private final boolean b;
        private final boolean c;
        private final boolean d;
        private final int e;
        private final int f;
        private final int g;
        private final int h;
        private final boolean i;

        public TextTrackScore(Format format, Parameters parameters, int i2, String str) {
            int i3;
            boolean z = false;
            this.b = DefaultTrackSelector.isSupported(i2, false);
            int i4 = format.d & (parameters.H ^ -1);
            this.c = (i4 & 1) != 0;
            this.d = (i4 & 2) != 0;
            bG a2 = parameters.E.isEmpty() ? bG.a((Object) "") : parameters.E;
            int i5 = 0;
            while (true) {
                if (i5 >= a2.size()) {
                    i5 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
                    i3 = 0;
                    break;
                }
                i3 = DefaultTrackSelector.getFormatLanguageScore(format, (String) a2.get(i5), parameters.G);
                if (i3 > 0) {
                    break;
                }
                i5++;
            }
            this.e = i5;
            this.f = i3;
            int bitCount = Integer.bitCount(format.e & parameters.F);
            this.g = bitCount;
            this.i = (format.e & 1088) != 0;
            int formatLanguageScore = DefaultTrackSelector.getFormatLanguageScore(format, str, DefaultTrackSelector.normalizeUndeterminedLanguageToNull(str) == null);
            this.h = formatLanguageScore;
            if (i3 > 0 || ((parameters.E.isEmpty() && bitCount > 0) || this.c || (this.d && formatLanguageScore > 0))) {
                z = true;
            }
            this.a = z;
        }

        /* renamed from: a */
        public final int compareTo(TextTrackScore textTrackScore) {
            C0052by a2 = C0052by.a().b(this.b, textTrackScore.b).a(Integer.valueOf(this.e), Integer.valueOf(textTrackScore.e), C0075cu.b().a()).a(this.f, textTrackScore.f).a(this.g, textTrackScore.g).b(this.c, textTrackScore.c).a(Boolean.valueOf(this.d), Boolean.valueOf(textTrackScore.d), this.f == 0 ? C0075cu.b() : C0075cu.b().a()).a(this.h, textTrackScore.h);
            if (this.g == 0) {
                a2 = a2.a(this.i, textTrackScore.i);
            }
            return a2.b();
        }
    }

    public final class VideoTrackScore implements Comparable {
        public final boolean a;
        private final Parameters b;
        private final boolean c;
        private final boolean d;
        private final int e;
        private final int f;
        private final int g;

        public VideoTrackScore(Format format, Parameters parameters, int i, boolean z) {
            this.b = parameters;
            boolean z2 = true;
            int i2 = 0;
            this.a = z && (format.width == -1 || format.width <= parameters.b) && ((format.height == -1 || format.height <= parameters.c) && ((format.q == -1.0f || format.q <= ((float) parameters.d)) && (format.h == -1 || format.h <= parameters.e)));
            if (!z || ((format.width != -1 && format.width < parameters.f) || ((format.height != -1 && format.height < parameters.g) || ((format.q != -1.0f && format.q < ((float) parameters.h)) || (format.h != -1 && format.h < parameters.i))))) {
                z2 = false;
            }
            this.c = z2;
            this.d = DefaultTrackSelector.isSupported(i, false);
            this.e = format.h;
            this.f = format.b();
            while (true) {
                if (i2 < parameters.p.size()) {
                    if (format.l != null && format.l.equals(parameters.p.get(i2))) {
                        break;
                    }
                    i2++;
                } else {
                    i2 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
                    break;
                }
            }
            this.g = i2;
        }

        /* renamed from: a */
        public final int compareTo(VideoTrackScore videoTrackScore) {
            C0075cu a2 = (!this.a || !this.d) ? DefaultTrackSelector.FORMAT_VALUE_ORDERING.a() : DefaultTrackSelector.FORMAT_VALUE_ORDERING;
            return C0052by.a().b(this.d, videoTrackScore.d).b(this.a, videoTrackScore.a).b(this.c, videoTrackScore.c).a(Integer.valueOf(this.g), Integer.valueOf(videoTrackScore.g), C0075cu.b().a()).a(Integer.valueOf(this.e), Integer.valueOf(videoTrackScore.e), this.b.x ? DefaultTrackSelector.FORMAT_VALUE_ORDERING.a() : DefaultTrackSelector.NO_ORDER).a(Integer.valueOf(this.f), Integer.valueOf(videoTrackScore.f), a2).a(Integer.valueOf(this.e), Integer.valueOf(videoTrackScore.e), a2).b();
        }
    }

    public DefaultTrackSelector() {
        this(Parameters.a, (ExoTrackSelection.Factory) new AdaptiveTrackSelection.Factory());
    }

    public DefaultTrackSelector(Context context) {
        this(context, (ExoTrackSelection.Factory) new AdaptiveTrackSelection.Factory());
    }

    public DefaultTrackSelector(Context context, ExoTrackSelection.Factory factory) {
        this(Parameters.a(context), factory);
    }

    public DefaultTrackSelector(Parameters parameters, ExoTrackSelection.Factory factory) {
        this.trackSelectionFactory = factory;
        this.parametersReference = new AtomicReference(parameters);
    }

    public DefaultTrackSelector(ExoTrackSelection.Factory factory) {
        this(Parameters.a, factory);
    }

    private static void filterAdaptiveVideoTrackCountForMimeType(TrackGroup trackGroup, int[] iArr, int i, String str, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, List list) {
        List list2 = list;
        for (int size = list.size() - 1; size >= 0; size--) {
            int intValue = ((Integer) list2.get(size)).intValue();
            if (!isSupportedAdaptiveVideoTrack(trackGroup.b[intValue], str, iArr[intValue], i, i2, i3, i4, i5, i6, i7, i8, i9)) {
                list2.remove(size);
            }
        }
    }

    private static int[] getAdaptiveAudioTracks(TrackGroup trackGroup, int[] iArr, int i, int i2, boolean z, boolean z2, boolean z3) {
        TrackGroup trackGroup2 = trackGroup;
        int i3 = i;
        Format format = trackGroup2.b[i3];
        int[] iArr2 = new int[trackGroup2.a];
        int i4 = 0;
        for (int i5 = 0; i5 < trackGroup2.a; i5++) {
            if (i5 == i3 || isSupportedAdaptiveAudioTrack(trackGroup2.b[i5], iArr[i5], format, i2, z, z2, z3)) {
                iArr2[i4] = i5;
                i4++;
            }
        }
        return Arrays.copyOf(iArr2, i4);
    }

    private static int getAdaptiveVideoTrackCountForMimeType(TrackGroup trackGroup, int[] iArr, int i, String str, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, List list) {
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            int intValue = ((Integer) list.get(i11)).intValue();
            if (isSupportedAdaptiveVideoTrack(trackGroup.b[intValue], str, iArr[intValue], i, i2, i3, i4, i5, i6, i7, i8, i9)) {
                i10++;
            }
        }
        return i10;
    }

    private static int[] getAdaptiveVideoTracksForGroup(TrackGroup trackGroup, int[] iArr, boolean z, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, boolean z2) {
        String str;
        HashSet hashSet;
        int i12;
        int i13;
        TrackGroup trackGroup2 = trackGroup;
        if (trackGroup2.a < 2) {
            return NO_TRACKS;
        }
        List viewportFilteredTrackIndices = getViewportFilteredTrackIndices(trackGroup2, i10, i11, z2);
        if (viewportFilteredTrackIndices.size() < 2) {
            return NO_TRACKS;
        }
        if (!z) {
            HashSet hashSet2 = new HashSet();
            String str2 = null;
            int i14 = 0;
            int i15 = 0;
            while (i15 < viewportFilteredTrackIndices.size()) {
                String str3 = trackGroup2.b[((Integer) viewportFilteredTrackIndices.get(i15)).intValue()].l;
                if (hashSet2.add(str3)) {
                    String str4 = str3;
                    i13 = i14;
                    i12 = i15;
                    hashSet = hashSet2;
                    int adaptiveVideoTrackCountForMimeType = getAdaptiveVideoTrackCountForMimeType(trackGroup, iArr, i, str3, i2, i3, i4, i5, i6, i7, i8, i9, viewportFilteredTrackIndices);
                    if (adaptiveVideoTrackCountForMimeType > i13) {
                        i14 = adaptiveVideoTrackCountForMimeType;
                        str2 = str4;
                        i15 = i12 + 1;
                        hashSet2 = hashSet;
                    }
                } else {
                    i13 = i14;
                    i12 = i15;
                    hashSet = hashSet2;
                }
                i14 = i13;
                i15 = i12 + 1;
                hashSet2 = hashSet;
            }
            str = str2;
        } else {
            str = null;
        }
        filterAdaptiveVideoTrackCountForMimeType(trackGroup, iArr, i, str, i2, i3, i4, i5, i6, i7, i8, i9, viewportFilteredTrackIndices);
        return viewportFilteredTrackIndices.size() < 2 ? NO_TRACKS : cR.a((Collection) viewportFilteredTrackIndices);
    }

    protected static int getFormatLanguageScore(Format format, String str, boolean z) {
        if (!TextUtils.isEmpty(str) && str.equals(format.c)) {
            return 4;
        }
        String normalizeUndeterminedLanguageToNull = normalizeUndeterminedLanguageToNull(str);
        String normalizeUndeterminedLanguageToNull2 = normalizeUndeterminedLanguageToNull(format.c);
        if (normalizeUndeterminedLanguageToNull2 == null || normalizeUndeterminedLanguageToNull == null) {
            return (!z || normalizeUndeterminedLanguageToNull2 != null) ? 0 : 1;
        }
        if (normalizeUndeterminedLanguageToNull2.startsWith(normalizeUndeterminedLanguageToNull) || normalizeUndeterminedLanguageToNull.startsWith(normalizeUndeterminedLanguageToNull2)) {
            return 3;
        }
        return Util.b(normalizeUndeterminedLanguageToNull2, "-")[0].equals(Util.b(normalizeUndeterminedLanguageToNull, "-")[0]) ? 2 : 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000d, code lost:
        if (r1 != r3) goto L_0x0013;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.Point getMaxVideoSizeInViewport(boolean r3, int r4, int r5, int r6, int r7) {
        /*
            if (r3 == 0) goto L_0x0010
            r3 = 1
            r0 = 0
            if (r6 <= r7) goto L_0x0008
            r1 = 1
            goto L_0x0009
        L_0x0008:
            r1 = 0
        L_0x0009:
            if (r4 <= r5) goto L_0x000c
            goto L_0x000d
        L_0x000c:
            r3 = 0
        L_0x000d:
            if (r1 == r3) goto L_0x0010
            goto L_0x0013
        L_0x0010:
            r2 = r5
            r5 = r4
            r4 = r2
        L_0x0013:
            int r3 = r6 * r4
            int r0 = r7 * r5
            if (r3 < r0) goto L_0x0023
            android.graphics.Point r3 = new android.graphics.Point
            int r4 = com.google.android.exoplayer2.util.Util.a((int) r0, (int) r6)
            r3.<init>(r5, r4)
            return r3
        L_0x0023:
            android.graphics.Point r5 = new android.graphics.Point
            int r3 = com.google.android.exoplayer2.util.Util.a((int) r3, (int) r7)
            r5.<init>(r3, r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.trackselection.DefaultTrackSelector.getMaxVideoSizeInViewport(boolean, int, int, int, int):android.graphics.Point");
    }

    private static List getViewportFilteredTrackIndices(TrackGroup trackGroup, int i, int i2, boolean z) {
        ArrayList arrayList = new ArrayList(trackGroup.a);
        for (int i3 = 0; i3 < trackGroup.a; i3++) {
            arrayList.add(Integer.valueOf(i3));
        }
        if (!(i == Integer.MAX_VALUE || i2 == Integer.MAX_VALUE)) {
            int i4 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            for (int i5 = 0; i5 < trackGroup.a; i5++) {
                Format format = trackGroup.b[i5];
                if (format.width > 0 && format.height > 0) {
                    Point maxVideoSizeInViewport = getMaxVideoSizeInViewport(z, i, i2, format.width, format.height);
                    int i6 = format.width * format.height;
                    if (format.width >= ((int) (((float) maxVideoSizeInViewport.x) * FRACTION_TO_CONSIDER_FULLSCREEN)) && format.height >= ((int) (((float) maxVideoSizeInViewport.y) * FRACTION_TO_CONSIDER_FULLSCREEN)) && i6 < i4) {
                        i4 = i6;
                    }
                }
            }
            if (i4 != Integer.MAX_VALUE) {
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    int b = trackGroup.b[((Integer) arrayList.get(size)).intValue()].b();
                    if (b == -1 || b > i4) {
                        arrayList.remove(size);
                    }
                }
            }
        }
        return arrayList;
    }

    protected static boolean isSupported(int i, boolean z) {
        int b = RendererCapabilities$$CC.b(i);
        if (b != 4) {
            return z && b == 3;
        }
        return true;
    }

    private static boolean isSupportedAdaptiveAudioTrack(Format format, int i, Format format2, int i2, boolean z, boolean z2, boolean z3) {
        if (isSupported(i, false) && format.h != -1 && format.h <= i2 && ((z3 || (format.w != -1 && format.w == format2.w)) && (z || (format.l != null && TextUtils.equals(format.l, format2.l))))) {
            if (!z2) {
                return format.x != -1 && format.x == format2.x;
            }
            return true;
        }
    }

    private static boolean isSupportedAdaptiveVideoTrack(Format format, String str, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        return (format.e & 16384) == 0 && isSupported(i, false) && (i & i2) != 0 && (str == null || Util.a((Object) format.l, (Object) str)) && ((format.width == -1 || (i7 <= format.width && format.width <= i3)) && ((format.height == -1 || (i8 <= format.height && format.height <= i4)) && ((format.q == -1.0f || (((float) i9) <= format.q && format.q <= ((float) i5))) && format.h != -1 && i10 <= format.h && format.h <= i6)));
    }

    static final /* synthetic */ int lambda$static$0$DefaultTrackSelector(Integer num, Integer num2) {
        if (num.intValue() == -1) {
            return num2.intValue() == -1 ? 0 : -1;
        }
        if (num2.intValue() == -1) {
            return 1;
        }
        return num.intValue() - num2.intValue();
    }

    static final /* synthetic */ int lambda$static$1$DefaultTrackSelector(Integer num, Integer num2) {
        return 0;
    }

    private static void maybeConfigureRenderersForTunneling(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, RendererConfiguration[] rendererConfigurationArr, ExoTrackSelection[] exoTrackSelectionArr) {
        boolean z;
        boolean z2 = false;
        int i = 0;
        int i2 = -1;
        int i3 = -1;
        while (true) {
            if (i >= mappedTrackInfo.getRendererCount()) {
                z = true;
                break;
            }
            int rendererType = mappedTrackInfo.getRendererType(i);
            ExoTrackSelection exoTrackSelection = exoTrackSelectionArr[i];
            if ((rendererType == 1 || rendererType == 2) && exoTrackSelection != null && rendererSupportsTunneling(iArr[i], mappedTrackInfo.getTrackGroups(i), exoTrackSelection)) {
                if (rendererType == 1) {
                    if (i3 != -1) {
                        break;
                    }
                    i3 = i;
                } else if (i2 != -1) {
                    break;
                } else {
                    i2 = i;
                }
            }
            i++;
        }
        z = false;
        if (!(i3 == -1 || i2 == -1)) {
            z2 = true;
        }
        if (z && z2) {
            RendererConfiguration rendererConfiguration = new RendererConfiguration(true);
            rendererConfigurationArr[i3] = rendererConfiguration;
            rendererConfigurationArr[i2] = rendererConfiguration;
        }
    }

    protected static String normalizeUndeterminedLanguageToNull(String str) {
        if (TextUtils.isEmpty(str) || TextUtils.equals(str, "und")) {
            return null;
        }
        return str;
    }

    private static boolean rendererSupportsTunneling(int[][] iArr, TrackGroupArray trackGroupArray, ExoTrackSelection exoTrackSelection) {
        if (exoTrackSelection == null) {
            return false;
        }
        int a = trackGroupArray.a(exoTrackSelection.e());
        for (int i = 0; i < exoTrackSelection.f(); i++) {
            if (RendererCapabilities$$CC.d(iArr[a][exoTrackSelection.b(i)]) != 32) {
                return false;
            }
        }
        return true;
    }

    private static ExoTrackSelection.Definition selectAdaptiveVideoTrack(TrackGroupArray trackGroupArray, int[][] iArr, int i, Parameters parameters) {
        TrackGroupArray trackGroupArray2 = trackGroupArray;
        Parameters parameters2 = parameters;
        int i2 = parameters2.l ? 24 : 16;
        boolean z = parameters2.k && (i & i2) != 0;
        int i3 = 0;
        while (i3 < trackGroupArray2.length) {
            TrackGroup trackGroup = trackGroupArray2.b[i3];
            int[] iArr2 = iArr[i3];
            int i4 = parameters2.b;
            int i5 = parameters2.c;
            int i6 = parameters2.d;
            int i7 = parameters2.e;
            int i8 = parameters2.f;
            int i9 = parameters2.g;
            int i10 = parameters2.h;
            int i11 = parameters2.i;
            int i12 = parameters2.m;
            int i13 = parameters2.n;
            boolean z2 = parameters2.o;
            TrackGroup trackGroup2 = trackGroup;
            int i14 = i3;
            int[] adaptiveVideoTracksForGroup = getAdaptiveVideoTracksForGroup(trackGroup, iArr2, z, i2, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, z2);
            if (adaptiveVideoTracksForGroup.length > 0) {
                return new ExoTrackSelection.Definition(trackGroup2, adaptiveVideoTracksForGroup);
            }
            i3 = i14 + 1;
            trackGroupArray2 = trackGroupArray;
            parameters2 = parameters;
        }
        return null;
    }

    private static ExoTrackSelection.Definition selectFixedVideoTrack(TrackGroupArray trackGroupArray, int[][] iArr, Parameters parameters) {
        TrackGroupArray trackGroupArray2 = trackGroupArray;
        Parameters parameters2 = parameters;
        int i = -1;
        TrackGroup trackGroup = null;
        VideoTrackScore videoTrackScore = null;
        for (int i2 = 0; i2 < trackGroupArray2.length; i2++) {
            TrackGroup trackGroup2 = trackGroupArray2.b[i2];
            List viewportFilteredTrackIndices = getViewportFilteredTrackIndices(trackGroup2, parameters2.m, parameters2.n, parameters2.o);
            int[] iArr2 = iArr[i2];
            for (int i3 = 0; i3 < trackGroup2.a; i3++) {
                Format format = trackGroup2.b[i3];
                if ((format.e & 16384) == 0 && isSupported(iArr2[i3], parameters2.z)) {
                    VideoTrackScore videoTrackScore2 = new VideoTrackScore(format, parameters2, iArr2[i3], viewportFilteredTrackIndices.contains(Integer.valueOf(i3)));
                    if ((videoTrackScore2.a || parameters2.j) && (videoTrackScore == null || videoTrackScore2.compareTo(videoTrackScore) > 0)) {
                        trackGroup = trackGroup2;
                        i = i3;
                        videoTrackScore = videoTrackScore2;
                    }
                }
            }
        }
        if (trackGroup == null) {
            return null;
        }
        return new ExoTrackSelection.Definition(trackGroup, i);
    }

    public ParametersBuilder buildUponParameters() {
        return getParameters().a();
    }

    public Parameters getParameters() {
        return (Parameters) this.parametersReference.get();
    }

    /* access modifiers changed from: protected */
    public ExoTrackSelection.Definition[] selectAllTracks(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, int[] iArr2, Parameters parameters) {
        int i;
        String str;
        int i2;
        AudioTrackScore audioTrackScore;
        String str2;
        int i3;
        MappingTrackSelector.MappedTrackInfo mappedTrackInfo2 = mappedTrackInfo;
        Parameters parameters2 = parameters;
        int rendererCount = mappedTrackInfo.getRendererCount();
        ExoTrackSelection.Definition[] definitionArr = new ExoTrackSelection.Definition[rendererCount];
        boolean z = false;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            i = 1;
            if (i4 >= rendererCount) {
                break;
            }
            if (2 == mappedTrackInfo2.getRendererType(i4)) {
                if (!z) {
                    ExoTrackSelection.Definition selectVideoTrack = selectVideoTrack(mappedTrackInfo2.getTrackGroups(i4), iArr[i4], iArr2[i4], parameters, true);
                    definitionArr[i4] = selectVideoTrack;
                    z = selectVideoTrack != null;
                }
                if (mappedTrackInfo2.getTrackGroups(i4).length <= 0) {
                    i = 0;
                }
                i5 |= i;
            }
            i4++;
        }
        AudioTrackScore audioTrackScore2 = null;
        String str3 = null;
        int i6 = -1;
        int i7 = 0;
        while (i7 < rendererCount) {
            if (i == mappedTrackInfo2.getRendererType(i7)) {
                i3 = i6;
                audioTrackScore = audioTrackScore2;
                str2 = str3;
                i2 = i7;
                Pair selectAudioTrack = selectAudioTrack(mappedTrackInfo2.getTrackGroups(i7), iArr[i7], iArr2[i7], parameters, parameters2.B || i5 == 0);
                if (selectAudioTrack != null && (audioTrackScore == null || ((AudioTrackScore) selectAudioTrack.second).compareTo(audioTrackScore) > 0)) {
                    if (i3 != -1) {
                        definitionArr[i3] = null;
                    }
                    ExoTrackSelection.Definition definition = (ExoTrackSelection.Definition) selectAudioTrack.first;
                    definitionArr[i2] = definition;
                    str3 = definition.a.b[definition.b[0]].c;
                    audioTrackScore = (AudioTrackScore) selectAudioTrack.second;
                    i6 = i2;
                    i7 = i2 + 1;
                    audioTrackScore2 = audioTrackScore;
                    i = 1;
                }
            } else {
                i3 = i6;
                audioTrackScore = audioTrackScore2;
                str2 = str3;
                i2 = i7;
            }
            i6 = i3;
            str3 = str2;
            i7 = i2 + 1;
            audioTrackScore2 = audioTrackScore;
            i = 1;
        }
        String str4 = str3;
        TextTrackScore textTrackScore = null;
        int i8 = -1;
        int i9 = 0;
        while (i9 < rendererCount) {
            int rendererType = mappedTrackInfo2.getRendererType(i9);
            switch (rendererType) {
                case 1:
                case 2:
                    str = str4;
                    break;
                case 3:
                    str = str4;
                    Pair selectTextTrack = selectTextTrack(mappedTrackInfo2.getTrackGroups(i9), iArr[i9], parameters2, str);
                    if (selectTextTrack != null && (textTrackScore == null || ((TextTrackScore) selectTextTrack.second).compareTo(textTrackScore) > 0)) {
                        if (i8 != -1) {
                            definitionArr[i8] = null;
                        }
                        definitionArr[i9] = (ExoTrackSelection.Definition) selectTextTrack.first;
                        textTrackScore = (TextTrackScore) selectTextTrack.second;
                        i8 = i9;
                        break;
                    }
                default:
                    str = str4;
                    definitionArr[i9] = selectOtherTrack(rendererType, mappedTrackInfo2.getTrackGroups(i9), iArr[i9], parameters2);
                    break;
            }
            i9++;
            str4 = str;
        }
        return definitionArr;
    }

    /* access modifiers changed from: protected */
    public Pair selectAudioTrack(TrackGroupArray trackGroupArray, int[][] iArr, int i, Parameters parameters, boolean z) {
        TrackGroupArray trackGroupArray2 = trackGroupArray;
        Parameters parameters2 = parameters;
        ExoTrackSelection.Definition definition = null;
        AudioTrackScore audioTrackScore = null;
        int i2 = -1;
        int i3 = -1;
        for (int i4 = 0; i4 < trackGroupArray2.length; i4++) {
            TrackGroup trackGroup = trackGroupArray2.b[i4];
            int[] iArr2 = iArr[i4];
            for (int i5 = 0; i5 < trackGroup.a; i5++) {
                if (isSupported(iArr2[i5], parameters2.z)) {
                    AudioTrackScore audioTrackScore2 = new AudioTrackScore(trackGroup.b[i5], parameters2, iArr2[i5]);
                    if ((audioTrackScore2.a || parameters2.s) && (audioTrackScore == null || audioTrackScore2.compareTo(audioTrackScore) > 0)) {
                        i2 = i4;
                        i3 = i5;
                        audioTrackScore = audioTrackScore2;
                    }
                }
            }
        }
        if (i2 == -1) {
            return null;
        }
        TrackGroup trackGroup2 = trackGroupArray2.b[i2];
        if (!parameters2.y && !parameters2.x && z) {
            int[] adaptiveAudioTracks = getAdaptiveAudioTracks(trackGroup2, iArr[i2], i3, parameters2.r, parameters2.t, parameters2.u, parameters2.v);
            if (adaptiveAudioTracks.length > 1) {
                definition = new ExoTrackSelection.Definition(trackGroup2, adaptiveAudioTracks);
            }
        }
        if (definition == null) {
            definition = new ExoTrackSelection.Definition(trackGroup2, i3);
        }
        return Pair.create(definition, (AudioTrackScore) Assertions.b((Object) audioTrackScore));
    }

    /* access modifiers changed from: protected */
    public ExoTrackSelection.Definition selectOtherTrack(int i, TrackGroupArray trackGroupArray, int[][] iArr, Parameters parameters) {
        TrackGroup trackGroup = null;
        OtherTrackScore otherTrackScore = null;
        int i2 = 0;
        for (int i3 = 0; i3 < trackGroupArray.length; i3++) {
            TrackGroup trackGroup2 = trackGroupArray.b[i3];
            int[] iArr2 = iArr[i3];
            for (int i4 = 0; i4 < trackGroup2.a; i4++) {
                if (isSupported(iArr2[i4], parameters.z)) {
                    OtherTrackScore otherTrackScore2 = new OtherTrackScore(trackGroup2.b[i4], iArr2[i4]);
                    if (otherTrackScore == null || otherTrackScore2.compareTo(otherTrackScore) > 0) {
                        trackGroup = trackGroup2;
                        i2 = i4;
                        otherTrackScore = otherTrackScore2;
                    }
                }
            }
        }
        if (trackGroup == null) {
            return null;
        }
        return new ExoTrackSelection.Definition(trackGroup, i2);
    }

    /* access modifiers changed from: protected */
    public Pair selectTextTrack(TrackGroupArray trackGroupArray, int[][] iArr, Parameters parameters, String str) {
        TrackGroupArray trackGroupArray2 = trackGroupArray;
        Parameters parameters2 = parameters;
        int i = -1;
        TrackGroup trackGroup = null;
        TextTrackScore textTrackScore = null;
        for (int i2 = 0; i2 < trackGroupArray2.length; i2++) {
            TrackGroup trackGroup2 = trackGroupArray2.b[i2];
            int[] iArr2 = iArr[i2];
            for (int i3 = 0; i3 < trackGroup2.a; i3++) {
                if (isSupported(iArr2[i3], parameters2.z)) {
                    TextTrackScore textTrackScore2 = new TextTrackScore(trackGroup2.b[i3], parameters2, iArr2[i3], str);
                    if (textTrackScore2.a && (textTrackScore == null || textTrackScore2.compareTo(textTrackScore) > 0)) {
                        trackGroup = trackGroup2;
                        i = i3;
                        textTrackScore = textTrackScore2;
                    }
                } else {
                    String str2 = str;
                }
            }
            String str3 = str;
        }
        if (trackGroup == null) {
            return null;
        }
        return Pair.create(new ExoTrackSelection.Definition(trackGroup, i), (TextTrackScore) Assertions.b((Object) textTrackScore));
    }

    /* access modifiers changed from: protected */
    public final Pair selectTracks(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, int[] iArr2, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline) {
        Parameters parameters = (Parameters) this.parametersReference.get();
        int rendererCount = mappedTrackInfo.getRendererCount();
        ExoTrackSelection.Definition[] selectAllTracks = selectAllTracks(mappedTrackInfo, iArr, iArr2, parameters);
        int i = 0;
        while (true) {
            ExoTrackSelection.Definition definition = null;
            if (i >= rendererCount) {
                break;
            }
            if (!parameters.a(i)) {
                TrackGroupArray trackGroups = mappedTrackInfo.getTrackGroups(i);
                if (!parameters.a(i, trackGroups)) {
                    i++;
                } else {
                    SelectionOverride b = parameters.b(i, trackGroups);
                    if (b != null) {
                        definition = new ExoTrackSelection.Definition(trackGroups.b[b.a], b.b, b.d);
                    }
                }
            }
            selectAllTracks[i] = definition;
            i++;
        }
        ExoTrackSelection[] a = this.trackSelectionFactory.a(selectAllTracks, getBandwidthMeter());
        RendererConfiguration[] rendererConfigurationArr = new RendererConfiguration[rendererCount];
        for (int i2 = 0; i2 < rendererCount; i2++) {
            rendererConfigurationArr[i2] = !parameters.a(i2) && (mappedTrackInfo.getRendererType(i2) == 7 || a[i2] != null) ? RendererConfiguration.a : null;
        }
        if (parameters.A) {
            maybeConfigureRenderersForTunneling(mappedTrackInfo, iArr, rendererConfigurationArr, a);
        }
        return Pair.create(rendererConfigurationArr, a);
    }

    /* access modifiers changed from: protected */
    public ExoTrackSelection.Definition selectVideoTrack(TrackGroupArray trackGroupArray, int[][] iArr, int i, Parameters parameters, boolean z) {
        ExoTrackSelection.Definition selectAdaptiveVideoTrack = (parameters.y || parameters.x || !z) ? null : selectAdaptiveVideoTrack(trackGroupArray, iArr, i, parameters);
        return selectAdaptiveVideoTrack == null ? selectFixedVideoTrack(trackGroupArray, iArr, parameters) : selectAdaptiveVideoTrack;
    }

    public void setParameters(Parameters parameters) {
        Assertions.b((Object) parameters);
        if (!((Parameters) this.parametersReference.getAndSet(parameters)).equals(parameters)) {
            invalidate();
        }
    }

    public void setParameters(ParametersBuilder parametersBuilder) {
        setParameters(parametersBuilder.b());
    }
}
