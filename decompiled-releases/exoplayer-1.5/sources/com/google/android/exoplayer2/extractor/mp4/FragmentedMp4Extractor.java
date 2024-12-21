package com.google.android.exoplayer2.extractor.mp4;

import android.util.Pair;
import android.util.SparseArray;
import androidx.core.location.LocationRequestCompat;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.audio.Ac4Util;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.CeaUtil;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.GaplessInfoHolder;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.mp4.Atom;
import com.google.android.exoplayer2.metadata.emsg.EventMessageEncoder;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import gnu.expr.Declaration;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class FragmentedMp4Extractor implements Extractor {
    private static final byte[] a = {Ev3Constants.Opcode.OUTPUT_RESET, Ev3Constants.Opcode.MOVE32_16, Ev3Constants.Opcode.CP_EQF, Ev3Constants.Opcode.CP_NEQ32, Ev3Constants.Opcode.CP_GTEQ32, -101, Ev3Constants.Opcode.CP_EQF, 20, Ev3Constants.Opcode.OUTPUT_RESET, Ev3Constants.Opcode.CP_LT8, Ev3Constants.Opcode.JR_EQ8, Ev3Constants.Opcode.JR_TRUE, Ev3Constants.Opcode.INFO, Ev3Constants.Opcode.JR_LT8, Ev3Constants.Opcode.MATH, -12};
    private static final Format b;
    private TrackBundle A;
    private int B;
    private int C;
    private int D;
    private boolean E;
    private ExtractorOutput F;
    private TrackOutput[] G;
    private TrackOutput[] H;
    private boolean I;
    private final int c;
    private final List d;
    private final SparseArray e;
    private final ParsableByteArray f;
    private final ParsableByteArray g;
    private final ParsableByteArray h;
    private final byte[] i;
    private final ParsableByteArray j;
    private final TimestampAdjuster k;
    private final EventMessageEncoder l;
    private final ParsableByteArray m;
    private final ArrayDeque n;
    private final ArrayDeque o;
    private final TrackOutput p;
    private int q;
    private int r;
    private long s;
    private int t;
    private ParsableByteArray u;
    private long v;
    private int w;
    private long x;
    private long y;
    private long z;

    final class MetadataSampleInfo {
        public final long a;
        public final int b;

        public MetadataSampleInfo(long j, int i) {
            this.a = j;
            this.b = i;
        }
    }

    final class TrackBundle {
        public final TrackOutput a;
        public final TrackFragment b = new TrackFragment();
        public TrackSampleTable c;
        public DefaultSampleValues d;
        public int e;
        public int f;
        public int g;
        public int h;
        /* access modifiers changed from: package-private */
        public boolean i;
        private ParsableByteArray j = new ParsableByteArray();
        private final ParsableByteArray k = new ParsableByteArray(1);
        private final ParsableByteArray l = new ParsableByteArray();

        public TrackBundle(TrackOutput trackOutput, TrackSampleTable trackSampleTable, DefaultSampleValues defaultSampleValues) {
            this.a = trackOutput;
            this.c = trackSampleTable;
            this.d = defaultSampleValues;
            a(trackSampleTable, defaultSampleValues);
        }

        public final int a(int i2, int i3) {
            ParsableByteArray parsableByteArray;
            int i4;
            TrackEncryptionBox d2 = d();
            if (d2 == null) {
                return 0;
            }
            if (d2.d != 0) {
                parsableByteArray = this.b.o;
                i4 = d2.d;
            } else {
                byte[] bArr = (byte[]) Util.a((Object) d2.e);
                this.l.a(bArr, bArr.length);
                parsableByteArray = this.l;
                i4 = bArr.length;
            }
            boolean c2 = this.b.c(this.e);
            boolean z = c2 || i3 != 0;
            this.k.a[0] = (byte) ((z ? 128 : 0) | i4);
            this.k.d(0);
            this.a.a(this.k, 1);
            this.a.a(parsableByteArray, i4);
            if (!z) {
                return i4 + 1;
            }
            if (!c2) {
                this.j.a(8);
                byte[] bArr2 = this.j.a;
                bArr2[0] = 0;
                bArr2[1] = 1;
                bArr2[2] = (byte) (i3 >> 8);
                bArr2[3] = (byte) i3;
                bArr2[4] = (byte) (i2 >>> 24);
                bArr2[5] = (byte) (i2 >> 16);
                bArr2[6] = (byte) (i2 >> 8);
                bArr2[7] = (byte) i2;
                this.a.a(this.j, 8);
                return i4 + 1 + 8;
            }
            ParsableByteArray parsableByteArray2 = this.b.o;
            int d3 = parsableByteArray2.d();
            parsableByteArray2.e(-2);
            int i5 = (d3 * 6) + 2;
            if (i3 != 0) {
                this.j.a(i5);
                byte[] bArr3 = this.j.a;
                parsableByteArray2.a(bArr3, 0, i5);
                int i6 = (((bArr3[2] & Ev3Constants.Opcode.TST) << 8) | (bArr3[3] & Ev3Constants.Opcode.TST)) + i3;
                bArr3[2] = (byte) (i6 >> 8);
                bArr3[3] = (byte) i6;
                parsableByteArray2 = this.j;
            }
            this.a.a(parsableByteArray2, i5);
            return i4 + 1 + i5;
        }

        public final void a() {
            this.b.a();
            this.e = 0;
            this.g = 0;
            this.f = 0;
            this.h = 0;
            this.i = false;
        }

        public final void a(long j2) {
            int i2 = this.e;
            while (i2 < this.b.e && this.b.b(i2) < j2) {
                if (this.b.k[i2]) {
                    this.h = i2;
                }
                i2++;
            }
        }

        public final void a(DrmInitData drmInitData) {
            TrackEncryptionBox a2 = this.c.a.a(((DefaultSampleValues) Util.a((Object) this.b.a)).a);
            DrmInitData a3 = drmInitData.a(a2 != null ? a2.b : null);
            Format.Builder a4 = this.c.a.f.a();
            a4.n = a3;
            this.a.a(a4.a());
        }

        public final void a(TrackSampleTable trackSampleTable, DefaultSampleValues defaultSampleValues) {
            this.c = trackSampleTable;
            this.d = defaultSampleValues;
            this.a.a(trackSampleTable.a.f);
            a();
        }

        public final long b() {
            return !this.i ? this.c.c[this.e] : this.b.f[this.g];
        }

        public final boolean c() {
            this.e++;
            if (!this.i) {
                return false;
            }
            int i2 = this.f + 1;
            this.f = i2;
            int[] iArr = this.b.g;
            int i3 = this.g;
            if (i2 != iArr[i3]) {
                return true;
            }
            this.g = i3 + 1;
            this.f = 0;
            return false;
        }

        public final TrackEncryptionBox d() {
            if (!this.i) {
                return null;
            }
            TrackEncryptionBox a2 = this.b.n != null ? this.b.n : this.c.a.a(((DefaultSampleValues) Util.a((Object) this.b.a)).a);
            if (a2 == null || !a2.a) {
                return null;
            }
            return a2;
        }
    }

    static {
        ExtractorsFactory extractorsFactory = FragmentedMp4Extractor$$Lambda$1.b;
        Format.Builder builder = new Format.Builder();
        builder.k = "application/x-emsg";
        b = builder.a();
    }

    public FragmentedMp4Extractor() {
        this((byte) 0);
    }

    public FragmentedMp4Extractor(byte b2) {
        this(0);
    }

    private FragmentedMp4Extractor(char c2) {
        this(0, (TimestampAdjuster) null, Collections.emptyList());
    }

    public FragmentedMp4Extractor(int i2, TimestampAdjuster timestampAdjuster, List list) {
        this(i2, timestampAdjuster, list, (TrackOutput) null);
    }

    public FragmentedMp4Extractor(int i2, TimestampAdjuster timestampAdjuster, List list, TrackOutput trackOutput) {
        this.c = i2;
        this.k = timestampAdjuster;
        this.d = Collections.unmodifiableList(list);
        this.p = trackOutput;
        this.l = new EventMessageEncoder();
        this.m = new ParsableByteArray(16);
        this.f = new ParsableByteArray(NalUnitUtil.a);
        this.g = new ParsableByteArray(5);
        this.h = new ParsableByteArray();
        byte[] bArr = new byte[16];
        this.i = bArr;
        this.j = new ParsableByteArray(bArr);
        this.n = new ArrayDeque();
        this.o = new ArrayDeque();
        this.e = new SparseArray();
        this.y = -9223372036854775807L;
        this.x = -9223372036854775807L;
        this.z = -9223372036854775807L;
        this.F = ExtractorOutput.a;
        this.G = new TrackOutput[0];
        this.H = new TrackOutput[0];
    }

    private static int a(int i2) {
        if (i2 >= 0) {
            return i2;
        }
        throw new ParserException(new StringBuilder(38).append("Unexpected negative value: ").append(i2).toString());
    }

    private static int a(TrackBundle trackBundle, int i2, int i3, ParsableByteArray parsableByteArray, int i4) {
        boolean z2;
        int i5;
        boolean z3;
        int i6;
        boolean z4;
        boolean z5;
        TrackBundle trackBundle2 = trackBundle;
        parsableByteArray.d(8);
        int b2 = Atom.b(parsableByteArray.j());
        Track track = trackBundle2.c.a;
        TrackFragment trackFragment = trackBundle2.b;
        DefaultSampleValues defaultSampleValues = (DefaultSampleValues) Util.a((Object) trackFragment.a);
        trackFragment.g[i2] = parsableByteArray.o();
        trackFragment.f[i2] = trackFragment.b;
        if ((b2 & 1) != 0) {
            long[] jArr = trackFragment.f;
            jArr[i2] = jArr[i2] + ((long) parsableByteArray.j());
        }
        boolean z6 = (b2 & 4) != 0;
        int i7 = defaultSampleValues.d;
        if (z6) {
            i7 = parsableByteArray.j();
        }
        boolean z7 = (b2 & 256) != 0;
        boolean z8 = (b2 & 512) != 0;
        boolean z9 = (b2 & 1024) != 0;
        boolean z10 = (b2 & 2048) != 0;
        long j2 = 0;
        if (track.h != null && track.h.length == 1 && track.h[0] == 0) {
            j2 = Util.b(((long[]) Util.a((Object) track.i))[0], 1000000, track.c);
        }
        int[] iArr = trackFragment.h;
        int[] iArr2 = trackFragment.i;
        long[] jArr2 = trackFragment.j;
        int i8 = i7;
        boolean[] zArr = trackFragment.k;
        boolean z11 = track.b == 2 && (i3 & 1) != 0;
        int i9 = i4 + trackFragment.g[i2];
        boolean z12 = z11;
        long j3 = track.c;
        long j4 = j2;
        long[] jArr3 = jArr2;
        long j5 = trackFragment.q;
        int i10 = i4;
        while (i10 < i9) {
            int a2 = a(z7 ? parsableByteArray.j() : defaultSampleValues.b);
            if (z8) {
                z2 = z7;
                i5 = parsableByteArray.j();
            } else {
                z2 = z7;
                i5 = defaultSampleValues.c;
            }
            int a3 = a(i5);
            if (z9) {
                z3 = z6;
                i6 = parsableByteArray.j();
            } else if (i10 != 0 || !z6) {
                z3 = z6;
                i6 = defaultSampleValues.d;
            } else {
                z3 = z6;
                i6 = i8;
            }
            boolean z13 = z10;
            if (z10) {
                z5 = z8;
                z4 = z9;
                iArr2[i10] = (int) ((((long) parsableByteArray.j()) * 1000000) / j3);
            } else {
                z5 = z8;
                z4 = z9;
                iArr2[i10] = 0;
            }
            jArr3[i10] = Util.b(j5, 1000000, j3) - j4;
            if (!trackFragment.r) {
                jArr3[i10] = jArr3[i10] + trackBundle2.c.h;
            }
            iArr[i10] = a3;
            zArr[i10] = ((i6 >> 16) & 1) == 0 && (!z12 || i10 == 0);
            j5 += (long) a2;
            i10++;
            trackBundle2 = trackBundle;
            z7 = z2;
            j3 = j3;
            z6 = z3;
            z10 = z13;
            z8 = z5;
            z9 = z4;
        }
        trackFragment.q = j5;
        return i9;
    }

    private static Pair a(ParsableByteArray parsableByteArray) {
        parsableByteArray.d(12);
        return Pair.create(Integer.valueOf(parsableByteArray.j()), new DefaultSampleValues(parsableByteArray.j() - 1, parsableByteArray.j(), parsableByteArray.j(), parsableByteArray.j()));
    }

    private static DrmInitData a(List list) {
        int size = list.size();
        ArrayList arrayList = null;
        for (int i2 = 0; i2 < size; i2++) {
            Atom.LeafAtom leafAtom = (Atom.LeafAtom) list.get(i2);
            if (leafAtom.a == 1886614376) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                byte[] bArr = leafAtom.b.a;
                UUID b2 = PsshAtomUtil.b(bArr);
                if (b2 == null) {
                    Log.c("FragmentedMp4Extractor", "Skipped pssh atom (failed to extract uuid)");
                } else {
                    arrayList.add(new DrmInitData.SchemeData(b2, "video/mp4", bArr));
                }
            }
        }
        if (arrayList == null) {
            return null;
        }
        return new DrmInitData((List) arrayList);
    }

    private static DefaultSampleValues a(SparseArray sparseArray, int i2) {
        return (DefaultSampleValues) (sparseArray.size() == 1 ? sparseArray.valueAt(0) : Assertions.b((Object) (DefaultSampleValues) sparseArray.get(i2)));
    }

    private static TrackBundle a(ParsableByteArray parsableByteArray, SparseArray sparseArray) {
        parsableByteArray.d(8);
        int b2 = Atom.b(parsableByteArray.j());
        TrackBundle b3 = b(sparseArray, parsableByteArray.j());
        if (b3 == null) {
            return null;
        }
        if ((b2 & 1) != 0) {
            long q2 = parsableByteArray.q();
            b3.b.b = q2;
            b3.b.c = q2;
        }
        DefaultSampleValues defaultSampleValues = b3.d;
        b3.b.a = new DefaultSampleValues((b2 & 2) != 0 ? parsableByteArray.j() - 1 : defaultSampleValues.a, (b2 & 8) != 0 ? parsableByteArray.j() : defaultSampleValues.b, (b2 & 16) != 0 ? parsableByteArray.j() : defaultSampleValues.c, (b2 & 32) != 0 ? parsableByteArray.j() : defaultSampleValues.d);
        return b3;
    }

    protected static Track a(Track track) {
        return track;
    }

    private void a(long j2) {
        while (!this.n.isEmpty() && ((Atom.ContainerAtom) this.n.peek()).b == j2) {
            a((Atom.ContainerAtom) this.n.pop());
        }
        b();
    }

    private void a(Atom.ContainerAtom containerAtom) {
        if (containerAtom.a == 1836019574) {
            b(containerAtom);
        } else if (containerAtom.a == 1836019558) {
            c(containerAtom);
        } else if (!this.n.isEmpty()) {
            ((Atom.ContainerAtom) this.n.peek()).a(containerAtom);
        }
    }

    private static void a(Atom.ContainerAtom containerAtom, SparseArray sparseArray, int i2, byte[] bArr) {
        int size = containerAtom.d.size();
        for (int i3 = 0; i3 < size; i3++) {
            Atom.ContainerAtom containerAtom2 = (Atom.ContainerAtom) containerAtom.d.get(i3);
            if (containerAtom2.a == 1953653094) {
                b(containerAtom2, sparseArray, i2, bArr);
            }
        }
    }

    private static void a(Atom.ContainerAtom containerAtom, TrackBundle trackBundle, int i2) {
        List list = containerAtom.c;
        int size = list.size();
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < size; i5++) {
            Atom.LeafAtom leafAtom = (Atom.LeafAtom) list.get(i5);
            if (leafAtom.a == 1953658222) {
                ParsableByteArray parsableByteArray = leafAtom.b;
                parsableByteArray.d(12);
                int o2 = parsableByteArray.o();
                if (o2 > 0) {
                    i4 += o2;
                    i3++;
                }
            }
        }
        trackBundle.g = 0;
        trackBundle.f = 0;
        trackBundle.e = 0;
        trackBundle.b.a(i3, i4);
        int i6 = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < size; i8++) {
            Atom.LeafAtom leafAtom2 = (Atom.LeafAtom) list.get(i8);
            if (leafAtom2.a == 1953658222) {
                i7 = a(trackBundle, i6, i2, leafAtom2.b, i7);
                i6++;
            }
        }
    }

    private static void a(Atom.ContainerAtom containerAtom, String str, TrackFragment trackFragment) {
        Atom.ContainerAtom containerAtom2 = containerAtom;
        TrackFragment trackFragment2 = trackFragment;
        byte[] bArr = null;
        ParsableByteArray parsableByteArray = null;
        ParsableByteArray parsableByteArray2 = null;
        for (int i2 = 0; i2 < containerAtom2.c.size(); i2++) {
            Atom.LeafAtom leafAtom = (Atom.LeafAtom) containerAtom2.c.get(i2);
            ParsableByteArray parsableByteArray3 = leafAtom.b;
            if (leafAtom.a == 1935828848) {
                parsableByteArray3.d(12);
                if (parsableByteArray3.j() == 1936025959) {
                    parsableByteArray = parsableByteArray3;
                }
            } else if (leafAtom.a == 1936158820) {
                parsableByteArray3.d(12);
                if (parsableByteArray3.j() == 1936025959) {
                    parsableByteArray2 = parsableByteArray3;
                }
            }
        }
        if (parsableByteArray != null && parsableByteArray2 != null) {
            parsableByteArray.d(8);
            int a2 = Atom.a(parsableByteArray.j());
            parsableByteArray.e(4);
            if (a2 == 1) {
                parsableByteArray.e(4);
            }
            if (parsableByteArray.j() == 1) {
                parsableByteArray2.d(8);
                int a3 = Atom.a(parsableByteArray2.j());
                parsableByteArray2.e(4);
                if (a3 == 1) {
                    if (parsableByteArray2.h() == 0) {
                        throw new ParserException("Variable length description in sgpd found (unsupported)");
                    }
                } else if (a3 >= 2) {
                    parsableByteArray2.e(4);
                }
                if (parsableByteArray2.h() == 1) {
                    parsableByteArray2.e(1);
                    int c2 = parsableByteArray2.c();
                    int i3 = (c2 & 240) >> 4;
                    int i4 = c2 & 15;
                    if (parsableByteArray2.c() == 1) {
                        int c3 = parsableByteArray2.c();
                        byte[] bArr2 = new byte[16];
                        parsableByteArray2.a(bArr2, 0, 16);
                        if (c3 == 0) {
                            int c4 = parsableByteArray2.c();
                            bArr = new byte[c4];
                            parsableByteArray2.a(bArr, 0, c4);
                        }
                        trackFragment2.l = true;
                        trackFragment2.n = new TrackEncryptionBox(true, str, c3, bArr2, i3, i4, bArr);
                        return;
                    }
                    return;
                }
                throw new ParserException("Entry count in sgpd != 1 (unsupported).");
            }
            throw new ParserException("Entry count in sbgp != 1 (unsupported).");
        }
    }

    private static void a(TrackEncryptionBox trackEncryptionBox, ParsableByteArray parsableByteArray, TrackFragment trackFragment) {
        int i2;
        int i3 = trackEncryptionBox.d;
        parsableByteArray.d(8);
        boolean z2 = true;
        if ((Atom.b(parsableByteArray.j()) & 1) == 1) {
            parsableByteArray.e(8);
        }
        int c2 = parsableByteArray.c();
        int o2 = parsableByteArray.o();
        if (o2 <= trackFragment.e) {
            if (c2 == 0) {
                boolean[] zArr = trackFragment.m;
                i2 = 0;
                for (int i4 = 0; i4 < o2; i4++) {
                    int c3 = parsableByteArray.c();
                    i2 += c3;
                    zArr[i4] = c3 > i3;
                }
            } else {
                if (c2 <= i3) {
                    z2 = false;
                }
                i2 = (c2 * o2) + 0;
                Arrays.fill(trackFragment.m, 0, o2, z2);
            }
            Arrays.fill(trackFragment.m, o2, trackFragment.e, false);
            if (i2 > 0) {
                trackFragment.a(i2);
                return;
            }
            return;
        }
        throw new ParserException(new StringBuilder(78).append("Saiz sample count ").append(o2).append(" is greater than fragment sample count").append(trackFragment.e).toString());
    }

    private static void a(ParsableByteArray parsableByteArray, int i2, TrackFragment trackFragment) {
        parsableByteArray.d(i2 + 8);
        int b2 = Atom.b(parsableByteArray.j());
        if ((b2 & 1) == 0) {
            boolean z2 = (b2 & 2) != 0;
            int o2 = parsableByteArray.o();
            if (o2 == 0) {
                Arrays.fill(trackFragment.m, 0, trackFragment.e, false);
            } else if (o2 == trackFragment.e) {
                Arrays.fill(trackFragment.m, 0, o2, z2);
                trackFragment.a(parsableByteArray.a());
                trackFragment.a(parsableByteArray);
            } else {
                throw new ParserException(new StringBuilder(80).append("Senc sample count ").append(o2).append(" is different from fragment sample count").append(trackFragment.e).toString());
            }
        } else {
            throw new ParserException("Overriding TrackEncryptionBox parameters is unsupported.");
        }
    }

    private static void a(ParsableByteArray parsableByteArray, TrackFragment trackFragment) {
        parsableByteArray.d(8);
        int j2 = parsableByteArray.j();
        if ((Atom.b(j2) & 1) == 1) {
            parsableByteArray.e(8);
        }
        int o2 = parsableByteArray.o();
        if (o2 == 1) {
            trackFragment.c += Atom.a(j2) == 0 ? parsableByteArray.h() : parsableByteArray.q();
            return;
        }
        throw new ParserException(new StringBuilder(40).append("Unexpected saio entry count: ").append(o2).toString());
    }

    private static void a(ParsableByteArray parsableByteArray, TrackFragment trackFragment, byte[] bArr) {
        parsableByteArray.d(8);
        parsableByteArray.a(bArr, 0, 16);
        if (Arrays.equals(bArr, a)) {
            a(parsableByteArray, 16, trackFragment);
        }
    }

    static final /* synthetic */ Extractor[] a() {
        return new Extractor[]{new FragmentedMp4Extractor()};
    }

    private static long b(ParsableByteArray parsableByteArray) {
        parsableByteArray.d(8);
        return Atom.a(parsableByteArray.j()) == 0 ? parsableByteArray.h() : parsableByteArray.q();
    }

    private static TrackBundle b(SparseArray sparseArray, int i2) {
        return (TrackBundle) (sparseArray.size() == 1 ? sparseArray.valueAt(0) : sparseArray.get(i2));
    }

    private void b() {
        this.q = 0;
        this.t = 0;
    }

    private void b(ExtractorInput extractorInput) {
        int size = this.e.size();
        TrackBundle trackBundle = null;
        long j2 = LocationRequestCompat.PASSIVE_INTERVAL;
        for (int i2 = 0; i2 < size; i2++) {
            TrackFragment trackFragment = ((TrackBundle) this.e.valueAt(i2)).b;
            if (trackFragment.p && trackFragment.c < j2) {
                long j3 = trackFragment.c;
                trackBundle = (TrackBundle) this.e.valueAt(i2);
                j2 = j3;
            }
        }
        if (trackBundle == null) {
            this.q = 3;
            return;
        }
        int c2 = (int) (j2 - extractorInput.c());
        if (c2 >= 0) {
            extractorInput.b(c2);
            TrackFragment trackFragment2 = trackBundle.b;
            extractorInput.b(trackFragment2.o.a, 0, trackFragment2.o.c);
            trackFragment2.o.d(0);
            trackFragment2.p = false;
            return;
        }
        throw new ParserException("Offset to encryption data was negative.");
    }

    private void b(Atom.ContainerAtom containerAtom) {
        boolean z2 = true;
        Assertions.b(true, (Object) "Unexpected moov box.");
        DrmInitData a2 = a(containerAtom.c);
        Atom.ContainerAtom containerAtom2 = (Atom.ContainerAtom) Assertions.b((Object) containerAtom.e(1836475768));
        SparseArray sparseArray = new SparseArray();
        int size = containerAtom2.c.size();
        int i2 = 0;
        long j2 = -9223372036854775807L;
        for (int i3 = 0; i3 < size; i3++) {
            Atom.LeafAtom leafAtom = (Atom.LeafAtom) containerAtom2.c.get(i3);
            if (leafAtom.a == 1953654136) {
                Pair a3 = a(leafAtom.b);
                sparseArray.put(((Integer) a3.first).intValue(), (DefaultSampleValues) a3.second);
            } else if (leafAtom.a == 1835362404) {
                j2 = b(leafAtom.b);
            }
        }
        List a4 = AtomParsers.a(containerAtom, new GaplessInfoHolder(), j2, a2, (this.c & 16) != 0, false, new FragmentedMp4Extractor$$Lambda$0());
        int size2 = a4.size();
        if (this.e.size() == 0) {
            while (i2 < size2) {
                TrackSampleTable trackSampleTable = (TrackSampleTable) a4.get(i2);
                Track track = trackSampleTable.a;
                this.e.put(track.a, new TrackBundle(this.F.a(i2, track.b), trackSampleTable, a(sparseArray, track.a)));
                this.y = Math.max(this.y, track.e);
                i2++;
            }
            this.F.c_();
            return;
        }
        if (this.e.size() != size2) {
            z2 = false;
        }
        Assertions.b(z2);
        while (i2 < size2) {
            TrackSampleTable trackSampleTable2 = (TrackSampleTable) a4.get(i2);
            Track track2 = trackSampleTable2.a;
            ((TrackBundle) this.e.get(track2.a)).a(trackSampleTable2, a(sparseArray, track2.a));
            i2++;
        }
    }

    private static void b(Atom.ContainerAtom containerAtom, SparseArray sparseArray, int i2, byte[] bArr) {
        TrackBundle a2 = a(((Atom.LeafAtom) Assertions.b((Object) containerAtom.d(1952868452))).b, sparseArray);
        if (a2 != null) {
            TrackFragment trackFragment = a2.b;
            long j2 = trackFragment.q;
            boolean z2 = trackFragment.r;
            a2.a();
            boolean unused = a2.i = true;
            Atom.LeafAtom d2 = containerAtom.d(1952867444);
            if (d2 == null || (i2 & 2) != 0) {
                trackFragment.q = j2;
                trackFragment.r = z2;
            } else {
                trackFragment.q = c(d2.b);
                trackFragment.r = true;
            }
            a(containerAtom, a2, i2);
            TrackEncryptionBox a3 = a2.c.a.a(((DefaultSampleValues) Assertions.b((Object) trackFragment.a)).a);
            Atom.LeafAtom d3 = containerAtom.d(1935763834);
            if (d3 != null) {
                a((TrackEncryptionBox) Assertions.b((Object) a3), d3.b, trackFragment);
            }
            Atom.LeafAtom d4 = containerAtom.d(1935763823);
            if (d4 != null) {
                a(d4.b, trackFragment);
            }
            Atom.LeafAtom d5 = containerAtom.d(1936027235);
            if (d5 != null) {
                a(d5.b, 0, trackFragment);
            }
            a(containerAtom, a3 != null ? a3.b : null, trackFragment);
            int size = containerAtom.c.size();
            for (int i3 = 0; i3 < size; i3++) {
                Atom.LeafAtom leafAtom = (Atom.LeafAtom) containerAtom.c.get(i3);
                if (leafAtom.a == 1970628964) {
                    a(leafAtom.b, trackFragment, bArr);
                }
            }
        }
    }

    private static long c(ParsableByteArray parsableByteArray) {
        parsableByteArray.d(8);
        return Atom.a(parsableByteArray.j()) == 1 ? parsableByteArray.q() : parsableByteArray.h();
    }

    private void c(Atom.ContainerAtom containerAtom) {
        a(containerAtom, this.e, this.c, this.i);
        DrmInitData a2 = a(containerAtom.c);
        if (a2 != null) {
            int size = this.e.size();
            for (int i2 = 0; i2 < size; i2++) {
                ((TrackBundle) this.e.valueAt(i2)).a(a2);
            }
        }
        if (this.x != -9223372036854775807L) {
            int size2 = this.e.size();
            for (int i3 = 0; i3 < size2; i3++) {
                ((TrackBundle) this.e.valueAt(i3)).a(this.x);
            }
            this.x = -9223372036854775807L;
        }
    }

    private boolean c(ExtractorInput extractorInput) {
        int i2;
        int i3;
        ExtractorInput extractorInput2 = extractorInput;
        TrackBundle trackBundle = this.A;
        if (trackBundle == null) {
            SparseArray sparseArray = this.e;
            int size = sparseArray.size();
            long j2 = Long.MAX_VALUE;
            TrackBundle trackBundle2 = null;
            for (int i4 = 0; i4 < size; i4++) {
                TrackBundle trackBundle3 = (TrackBundle) sparseArray.valueAt(i4);
                if ((trackBundle3.i || trackBundle3.e != trackBundle3.c.b) && (!trackBundle3.i || trackBundle3.g != trackBundle3.b.d)) {
                    long b2 = trackBundle3.b();
                    if (b2 < j2) {
                        trackBundle2 = trackBundle3;
                        j2 = b2;
                    }
                }
            }
            if (trackBundle2 == null) {
                int c2 = (int) (this.v - extractorInput.c());
                if (c2 >= 0) {
                    extractorInput2.b(c2);
                    b();
                    return false;
                }
                throw new ParserException("Offset to end of mdat was negative.");
            }
            int b3 = (int) (trackBundle2.b() - extractorInput.c());
            if (b3 < 0) {
                Log.c("FragmentedMp4Extractor", "Ignoring negative offset to sample data.");
                b3 = 0;
            }
            extractorInput2.b(b3);
            this.A = trackBundle2;
            trackBundle = trackBundle2;
        }
        int i5 = 4;
        int i6 = 1;
        if (this.q == 3) {
            this.B = !trackBundle.i ? trackBundle.c.d[trackBundle.e] : trackBundle.b.h[trackBundle.e];
            if (trackBundle.e < trackBundle.h) {
                extractorInput2.b(this.B);
                TrackEncryptionBox d2 = trackBundle.d();
                if (d2 != null) {
                    ParsableByteArray parsableByteArray = trackBundle.b.o;
                    if (d2.d != 0) {
                        parsableByteArray.e(d2.d);
                    }
                    if (trackBundle.b.c(trackBundle.e)) {
                        parsableByteArray.e(parsableByteArray.d() * 6);
                    }
                }
                if (!trackBundle.c()) {
                    this.A = null;
                }
                this.q = 3;
                return true;
            }
            if (trackBundle.c.a.g == 1) {
                this.B -= 8;
                extractorInput2.b(8);
            }
            if ("audio/ac4".equals(trackBundle.c.a.f.l)) {
                this.C = trackBundle.a(this.B, 7);
                Ac4Util.a(this.B, this.j);
                trackBundle.a.b(this.j, 7);
                i3 = this.C + 7;
            } else {
                i3 = trackBundle.a(this.B, 0);
            }
            this.C = i3;
            this.B += this.C;
            this.q = 4;
            this.D = 0;
        }
        Track track = trackBundle.c.a;
        TrackOutput trackOutput = trackBundle.a;
        long b4 = !trackBundle.i ? trackBundle.c.f[trackBundle.e] : trackBundle.b.b(trackBundle.e);
        TimestampAdjuster timestampAdjuster = this.k;
        if (timestampAdjuster != null) {
            b4 = timestampAdjuster.c(b4);
        }
        long j3 = b4;
        if (track.j == 0) {
            while (true) {
                int i7 = this.C;
                int i8 = this.B;
                if (i7 >= i8) {
                    break;
                }
                this.C += trackOutput.b(extractorInput2, i8 - i7, false);
            }
        } else {
            byte[] bArr = this.g.a;
            bArr[0] = 0;
            bArr[1] = 0;
            bArr[2] = 0;
            int i9 = track.j + 1;
            int i10 = 4 - track.j;
            while (this.C < this.B) {
                int i11 = this.D;
                if (i11 == 0) {
                    extractorInput2.b(bArr, i10, i9);
                    this.g.d(0);
                    int j4 = this.g.j();
                    if (j4 > 0) {
                        this.D = j4 - 1;
                        this.f.d(0);
                        trackOutput.b(this.f, i5);
                        trackOutput.b(this.g, i6);
                        this.E = this.H.length > 0 && NalUnitUtil.a(track.f.l, bArr[i5]);
                        this.C += 5;
                        this.B += i10;
                    } else {
                        throw new ParserException("Invalid NAL length");
                    }
                } else {
                    if (this.E) {
                        this.h.a(i11);
                        extractorInput2.b(this.h.a, 0, this.D);
                        trackOutput.b(this.h, this.D);
                        i2 = this.D;
                        int a2 = NalUnitUtil.a(this.h.a, this.h.c);
                        this.h.d("video/hevc".equals(track.f.l) ? 1 : 0);
                        this.h.c(a2);
                        CeaUtil.a(j3, this.h, this.H);
                    } else {
                        i2 = trackOutput.b(extractorInput2, i11, false);
                    }
                    this.C += i2;
                    this.D -= i2;
                    i5 = 4;
                    i6 = 1;
                }
            }
        }
        int i12 = !trackBundle.i ? trackBundle.c.g[trackBundle.e] : trackBundle.b.k[trackBundle.e] ? 1 : 0;
        if (trackBundle.d() != null) {
            i12 |= Declaration.MODULE_REFERENCE;
        }
        int i13 = i12;
        TrackEncryptionBox d3 = trackBundle.d();
        long j5 = j3;
        trackOutput.a(j3, i13, this.B, 0, d3 != null ? d3.c : null);
        while (!this.o.isEmpty()) {
            MetadataSampleInfo metadataSampleInfo = (MetadataSampleInfo) this.o.removeFirst();
            this.w -= metadataSampleInfo.b;
            long j6 = j5 + metadataSampleInfo.a;
            TimestampAdjuster timestampAdjuster2 = this.k;
            if (timestampAdjuster2 != null) {
                j6 = timestampAdjuster2.c(j6);
            }
            TrackOutput[] trackOutputArr = this.G;
            int i14 = 0;
            for (int length = trackOutputArr.length; i14 < length; length = length) {
                trackOutputArr[i14].a(j6, 1, metadataSampleInfo.b, this.w, (TrackOutput.CryptoData) null);
                i14++;
            }
        }
        if (!trackBundle.c()) {
            this.A = null;
        }
        this.q = 3;
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v15, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:195:0x0431 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:197:0x043b A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x0004 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x02bb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int a(com.google.android.exoplayer2.extractor.ExtractorInput r29, com.google.android.exoplayer2.extractor.PositionHolder r30) {
        /*
            r28 = this;
            r0 = r28
            r1 = r29
        L_0x0004:
            int r2 = r0.q
            r3 = 2
            r4 = 1701671783(0x656d7367, float:7.0083103E22)
            r5 = 1936286840(0x73696478, float:1.8491255E31)
            r6 = 8
            r8 = 0
            switch(r2) {
                case 0: goto L_0x023c;
                case 1: goto L_0x001e;
                case 2: goto L_0x001a;
                default: goto L_0x0013;
            }
        L_0x0013:
            boolean r2 = r28.c((com.google.android.exoplayer2.extractor.ExtractorInput) r29)
            if (r2 == 0) goto L_0x0004
            return r8
        L_0x001a:
            r28.b((com.google.android.exoplayer2.extractor.ExtractorInput) r29)
            goto L_0x0004
        L_0x001e:
            long r9 = r0.s
            int r2 = (int) r9
            int r9 = r0.t
            int r2 = r2 - r9
            com.google.android.exoplayer2.util.ParsableByteArray r9 = r0.u
            if (r9 == 0) goto L_0x0230
            byte[] r10 = r9.a
            r1.b(r10, r6, r2)
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r2 = new com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom
            int r10 = r0.r
            r2.<init>(r10, r9)
            long r9 = r29.c()
            java.util.ArrayDeque r11 = r0.n
            boolean r11 = r11.isEmpty()
            if (r11 != 0) goto L_0x004d
            java.util.ArrayDeque r3 = r0.n
            java.lang.Object r3 = r3.peek()
            com.google.android.exoplayer2.extractor.mp4.Atom$ContainerAtom r3 = (com.google.android.exoplayer2.extractor.mp4.Atom.ContainerAtom) r3
            r3.a((com.google.android.exoplayer2.extractor.mp4.Atom.LeafAtom) r2)
            goto L_0x0233
        L_0x004d:
            int r11 = r2.a
            if (r11 != r5) goto L_0x0107
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r2.b
            r2.d(r6)
            int r4 = r2.j()
            int r4 = com.google.android.exoplayer2.extractor.mp4.Atom.a(r4)
            r5 = 4
            r2.e(r5)
            long r17 = r2.h()
            if (r4 != 0) goto L_0x0071
            long r11 = r2.h()
            long r13 = r2.h()
            goto L_0x0079
        L_0x0071:
            long r11 = r2.q()
            long r13 = r2.q()
        L_0x0079:
            long r9 = r9 + r13
            r19 = r11
            r13 = 1000000(0xf4240, double:4.940656E-318)
            r11 = r19
            r15 = r17
            long r21 = com.google.android.exoplayer2.util.Util.b((long) r11, (long) r13, (long) r15)
            r2.e(r3)
            int r3 = r2.d()
            int[] r4 = new int[r3]
            long[] r6 = new long[r3]
            long[] r15 = new long[r3]
            long[] r13 = new long[r3]
            r11 = r21
        L_0x0098:
            if (r8 >= r3) goto L_0x00e0
            int r14 = r2.j()
            r16 = -2147483648(0xffffffff80000000, float:-0.0)
            r16 = r14 & r16
            if (r16 != 0) goto L_0x00d8
            long r23 = r2.h()
            r16 = 2147483647(0x7fffffff, float:NaN)
            r14 = r14 & r16
            r4[r8] = r14
            r6[r8] = r9
            r13[r8] = r11
            long r19 = r19 + r23
            r23 = 1000000(0xf4240, double:4.940656E-318)
            r11 = r19
            r7 = r13
            r13 = r23
            r5 = r15
            r15 = r17
            long r11 = com.google.android.exoplayer2.util.Util.b((long) r11, (long) r13, (long) r15)
            r13 = r7[r8]
            long r13 = r11 - r13
            r5[r8] = r13
            r13 = 4
            r2.e(r13)
            r14 = r4[r8]
            long r14 = (long) r14
            long r9 = r9 + r14
            int r8 = r8 + 1
            r15 = r5
            r13 = r7
            r5 = 4
            goto L_0x0098
        L_0x00d8:
            com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
            java.lang.String r2 = "Unhandled indirect reference"
            r1.<init>((java.lang.String) r2)
            throw r1
        L_0x00e0:
            r7 = r13
            r5 = r15
            java.lang.Long r2 = java.lang.Long.valueOf(r21)
            com.google.android.exoplayer2.extractor.ChunkIndex r3 = new com.google.android.exoplayer2.extractor.ChunkIndex
            r3.<init>(r4, r6, r5, r7)
            android.util.Pair r2 = android.util.Pair.create(r2, r3)
            java.lang.Object r3 = r2.first
            java.lang.Long r3 = (java.lang.Long) r3
            long r3 = r3.longValue()
            r0.z = r3
            com.google.android.exoplayer2.extractor.ExtractorOutput r3 = r0.F
            java.lang.Object r2 = r2.second
            com.google.android.exoplayer2.extractor.SeekMap r2 = (com.google.android.exoplayer2.extractor.SeekMap) r2
            r3.a(r2)
            r2 = 1
            r0.I = r2
            goto L_0x0233
        L_0x0107:
            int r3 = r2.a
            if (r3 != r4) goto L_0x0233
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r2.b
            com.google.android.exoplayer2.extractor.TrackOutput[] r3 = r0.G
            int r3 = r3.length
            if (r3 == 0) goto L_0x0233
            r2.d(r6)
            int r3 = r2.j()
            int r3 = com.google.android.exoplayer2.extractor.mp4.Atom.a(r3)
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            switch(r3) {
                case 0: goto L_0x017e;
                case 1: goto L_0x0141;
                default: goto L_0x0125;
            }
        L_0x0125:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r4 = 46
            r2.<init>(r4)
            java.lang.String r4 = "Skipping unsupported emsg version: "
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = "FragmentedMp4Extractor"
            com.google.android.exoplayer2.util.Log.c(r3, r2)
            goto L_0x0233
        L_0x0141:
            long r6 = r2.h()
            long r9 = r2.q()
            r11 = 1000000(0xf4240, double:4.940656E-318)
            r13 = r6
            long r15 = com.google.android.exoplayer2.util.Util.b((long) r9, (long) r11, (long) r13)
            long r9 = r2.h()
            r11 = 1000(0x3e8, double:4.94E-321)
            long r6 = com.google.android.exoplayer2.util.Util.b((long) r9, (long) r11, (long) r13)
            long r9 = r2.h()
            java.lang.String r3 = r2.r()
            java.lang.Object r3 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r3)
            java.lang.String r3 = (java.lang.String) r3
            java.lang.String r11 = r2.r()
            java.lang.Object r11 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r11)
            java.lang.String r11 = (java.lang.String) r11
            r21 = r3
            r23 = r6
            r25 = r9
            r22 = r11
            r6 = r15
            r9 = r4
            goto L_0x01ca
        L_0x017e:
            java.lang.String r3 = r2.r()
            java.lang.Object r3 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r3)
            java.lang.String r3 = (java.lang.String) r3
            java.lang.String r6 = r2.r()
            java.lang.Object r6 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r6)
            r11 = r6
            java.lang.String r11 = (java.lang.String) r11
            long r6 = r2.h()
            long r12 = r2.h()
            r14 = 1000000(0xf4240, double:4.940656E-318)
            r16 = r6
            long r9 = com.google.android.exoplayer2.util.Util.b((long) r12, (long) r14, (long) r16)
            long r12 = r0.z
            int r14 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r14 == 0) goto L_0x01ae
            long r12 = r12 + r9
            r18 = r12
            goto L_0x01b0
        L_0x01ae:
            r18 = r4
        L_0x01b0:
            long r12 = r2.h()
            r14 = 1000(0x3e8, double:4.94E-321)
            r16 = r6
            long r6 = com.google.android.exoplayer2.util.Util.b((long) r12, (long) r14, (long) r16)
            long r12 = r2.h()
            r21 = r3
            r23 = r6
            r22 = r11
            r25 = r12
            r6 = r18
        L_0x01ca:
            int r3 = r2.a()
            byte[] r3 = new byte[r3]
            int r11 = r2.a()
            r2.a(r3, r8, r11)
            com.google.android.exoplayer2.metadata.emsg.EventMessage r2 = new com.google.android.exoplayer2.metadata.emsg.EventMessage
            r20 = r2
            r27 = r3
            r20.<init>(r21, r22, r23, r25, r27)
            com.google.android.exoplayer2.util.ParsableByteArray r3 = new com.google.android.exoplayer2.util.ParsableByteArray
            com.google.android.exoplayer2.metadata.emsg.EventMessageEncoder r11 = r0.l
            byte[] r2 = r11.a(r2)
            r3.<init>((byte[]) r2)
            int r2 = r3.a()
            com.google.android.exoplayer2.extractor.TrackOutput[] r11 = r0.G
            int r12 = r11.length
            r13 = 0
        L_0x01f3:
            if (r13 >= r12) goto L_0x0200
            r14 = r11[r13]
            r3.d(r8)
            r14.b(r3, r2)
            int r13 = r13 + 1
            goto L_0x01f3
        L_0x0200:
            int r3 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r3 != 0) goto L_0x0214
            java.util.ArrayDeque r3 = r0.o
            com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor$MetadataSampleInfo r4 = new com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor$MetadataSampleInfo
            r4.<init>(r9, r2)
            r3.addLast(r4)
            int r3 = r0.w
            int r3 = r3 + r2
            r0.w = r3
            goto L_0x0233
        L_0x0214:
            com.google.android.exoplayer2.util.TimestampAdjuster r3 = r0.k
            if (r3 == 0) goto L_0x021c
            long r6 = r3.c(r6)
        L_0x021c:
            com.google.android.exoplayer2.extractor.TrackOutput[] r3 = r0.G
            int r4 = r3.length
        L_0x021f:
            if (r8 >= r4) goto L_0x0233
            r11 = r3[r8]
            r14 = 1
            r16 = 0
            r17 = 0
            r12 = r6
            r15 = r2
            r11.a(r12, r14, r15, r16, r17)
            int r8 = r8 + 1
            goto L_0x021f
        L_0x0230:
            r1.b(r2)
        L_0x0233:
            long r2 = r29.c()
            r0.a((long) r2)
            goto L_0x0004
        L_0x023c:
            int r2 = r0.t
            if (r2 != 0) goto L_0x0265
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.m
            byte[] r2 = r2.a
            r7 = 1
            boolean r2 = r1.a(r2, r8, r6, r7)
            if (r2 != 0) goto L_0x024e
            r7 = 0
            goto L_0x042f
        L_0x024e:
            r0.t = r6
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.m
            r2.d(r8)
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.m
            long r9 = r2.h()
            r0.s = r9
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.m
            int r2 = r2.j()
            r0.r = r2
        L_0x0265:
            long r9 = r0.s
            r11 = 1
            int r2 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r2 != 0) goto L_0x0282
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.m
            byte[] r2 = r2.a
            r1.b(r2, r6, r6)
            int r2 = r0.t
            int r2 = r2 + r6
            r0.t = r2
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.m
            long r9 = r2.q()
        L_0x027f:
            r0.s = r9
            goto L_0x02b2
        L_0x0282:
            r11 = 0
            int r2 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r2 != 0) goto L_0x02b2
            long r9 = r29.d()
            r11 = -1
            int r2 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r2 != 0) goto L_0x02a4
            java.util.ArrayDeque r2 = r0.n
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L_0x02a4
            java.util.ArrayDeque r2 = r0.n
            java.lang.Object r2 = r2.peek()
            com.google.android.exoplayer2.extractor.mp4.Atom$ContainerAtom r2 = (com.google.android.exoplayer2.extractor.mp4.Atom.ContainerAtom) r2
            long r9 = r2.b
        L_0x02a4:
            int r2 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r2 == 0) goto L_0x02b2
            long r11 = r29.c()
            long r9 = r9 - r11
            int r2 = r0.t
            long r11 = (long) r2
            long r9 = r9 + r11
            goto L_0x027f
        L_0x02b2:
            long r9 = r0.s
            int r2 = r0.t
            long r11 = (long) r2
            int r2 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r2 < 0) goto L_0x043b
            long r9 = r29.c()
            int r2 = r0.t
            long r11 = (long) r2
            long r9 = r9 - r11
            int r2 = r0.r
            r7 = 1835295092(0x6d646174, float:4.4175247E27)
            r11 = 1836019558(0x6d6f6f66, float:4.6313494E27)
            if (r2 == r11) goto L_0x02cf
            if (r2 != r7) goto L_0x02e2
        L_0x02cf:
            boolean r2 = r0.I
            if (r2 != 0) goto L_0x02e2
            com.google.android.exoplayer2.extractor.ExtractorOutput r2 = r0.F
            com.google.android.exoplayer2.extractor.SeekMap$Unseekable r12 = new com.google.android.exoplayer2.extractor.SeekMap$Unseekable
            long r13 = r0.y
            r12.<init>(r13, r9)
            r2.a(r12)
            r2 = 1
            r0.I = r2
        L_0x02e2:
            int r2 = r0.r
            if (r2 != r11) goto L_0x0300
            android.util.SparseArray r2 = r0.e
            int r2 = r2.size()
            r12 = 0
        L_0x02ed:
            if (r12 >= r2) goto L_0x0300
            android.util.SparseArray r13 = r0.e
            java.lang.Object r13 = r13.valueAt(r12)
            com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor$TrackBundle r13 = (com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor.TrackBundle) r13
            com.google.android.exoplayer2.extractor.mp4.TrackFragment r13 = r13.b
            r13.c = r9
            r13.b = r9
            int r12 = r12 + 1
            goto L_0x02ed
        L_0x0300:
            int r2 = r0.r
            r12 = 0
            if (r2 != r7) goto L_0x0311
            r0.A = r12
            long r4 = r0.s
            long r9 = r9 + r4
            r0.v = r9
            r0.q = r3
        L_0x030e:
            r2 = 1
            goto L_0x042e
        L_0x0311:
            r3 = 1836019574(0x6d6f6f76, float:4.631354E27)
            if (r2 == r3) goto L_0x033e
            r3 = 1953653099(0x7472616b, float:7.681346E31)
            if (r2 == r3) goto L_0x033e
            r3 = 1835297121(0x6d646961, float:4.4181236E27)
            if (r2 == r3) goto L_0x033e
            r3 = 1835626086(0x6d696e66, float:4.515217E27)
            if (r2 == r3) goto L_0x033e
            r3 = 1937007212(0x7374626c, float:1.9362132E31)
            if (r2 == r3) goto L_0x033e
            if (r2 == r11) goto L_0x033e
            r3 = 1953653094(0x74726166, float:7.6813435E31)
            if (r2 == r3) goto L_0x033e
            r3 = 1836475768(0x6d766578, float:4.7659988E27)
            if (r2 == r3) goto L_0x033e
            r3 = 1701082227(0x65647473, float:6.742798E22)
            if (r2 != r3) goto L_0x033c
            goto L_0x033e
        L_0x033c:
            r3 = 0
            goto L_0x033f
        L_0x033e:
            r3 = 1
        L_0x033f:
            if (r3 == 0) goto L_0x0368
            long r2 = r29.c()
            long r4 = r0.s
            long r2 = r2 + r4
            r4 = 8
            long r2 = r2 - r4
            java.util.ArrayDeque r4 = r0.n
            com.google.android.exoplayer2.extractor.mp4.Atom$ContainerAtom r5 = new com.google.android.exoplayer2.extractor.mp4.Atom$ContainerAtom
            int r6 = r0.r
            r5.<init>(r6, r2)
            r4.push(r5)
            long r4 = r0.s
            int r6 = r0.t
            long r6 = (long) r6
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x0364
            r0.a((long) r2)
            goto L_0x030e
        L_0x0364:
            r28.b()
            goto L_0x030e
        L_0x0368:
            r3 = 1751411826(0x68646c72, float:4.3148E24)
            if (r2 == r3) goto L_0x03f1
            r3 = 1835296868(0x6d646864, float:4.418049E27)
            if (r2 == r3) goto L_0x03f1
            r3 = 1836476516(0x6d766864, float:4.7662196E27)
            if (r2 == r3) goto L_0x03f1
            if (r2 == r5) goto L_0x03f1
            r3 = 1937011556(0x73747364, float:1.9367383E31)
            if (r2 == r3) goto L_0x03f1
            r3 = 1937011827(0x73747473, float:1.9367711E31)
            if (r2 == r3) goto L_0x03f1
            r3 = 1668576371(0x63747473, float:4.5093966E21)
            if (r2 == r3) goto L_0x03f1
            r3 = 1937011555(0x73747363, float:1.9367382E31)
            if (r2 == r3) goto L_0x03f1
            r3 = 1937011578(0x7374737a, float:1.936741E31)
            if (r2 == r3) goto L_0x03f1
            r3 = 1937013298(0x73747a32, float:1.9369489E31)
            if (r2 == r3) goto L_0x03f1
            r3 = 1937007471(0x7374636f, float:1.9362445E31)
            if (r2 == r3) goto L_0x03f1
            r3 = 1668232756(0x636f3634, float:4.4126776E21)
            if (r2 == r3) goto L_0x03f1
            r3 = 1937011571(0x73747373, float:1.9367401E31)
            if (r2 == r3) goto L_0x03f1
            r3 = 1952867444(0x74666474, float:7.3014264E31)
            if (r2 == r3) goto L_0x03f1
            r3 = 1952868452(0x74666864, float:7.301914E31)
            if (r2 == r3) goto L_0x03f1
            r3 = 1953196132(0x746b6864, float:7.46037E31)
            if (r2 == r3) goto L_0x03f1
            r3 = 1953654136(0x74726578, float:7.6818474E31)
            if (r2 == r3) goto L_0x03f1
            r3 = 1953658222(0x7472756e, float:7.683823E31)
            if (r2 == r3) goto L_0x03f1
            r3 = 1886614376(0x70737368, float:3.013775E29)
            if (r2 == r3) goto L_0x03f1
            r3 = 1935763834(0x7361697a, float:1.785898E31)
            if (r2 == r3) goto L_0x03f1
            r3 = 1935763823(0x7361696f, float:1.7858967E31)
            if (r2 == r3) goto L_0x03f1
            r3 = 1936027235(0x73656e63, float:1.8177412E31)
            if (r2 == r3) goto L_0x03f1
            r3 = 1970628964(0x75756964, float:3.1109627E32)
            if (r2 == r3) goto L_0x03f1
            r3 = 1935828848(0x73626770, float:1.7937577E31)
            if (r2 == r3) goto L_0x03f1
            r3 = 1936158820(0x73677064, float:1.8336489E31)
            if (r2 == r3) goto L_0x03f1
            r3 = 1701606260(0x656c7374, float:6.9788014E22)
            if (r2 == r3) goto L_0x03f1
            r3 = 1835362404(0x6d656864, float:4.4373917E27)
            if (r2 == r3) goto L_0x03f1
            if (r2 != r4) goto L_0x03ef
            goto L_0x03f1
        L_0x03ef:
            r2 = 0
            goto L_0x03f2
        L_0x03f1:
            r2 = 1
        L_0x03f2:
            r3 = 2147483647(0x7fffffff, double:1.060997895E-314)
            if (r2 == 0) goto L_0x0423
            int r2 = r0.t
            if (r2 != r6) goto L_0x041b
            long r9 = r0.s
            int r2 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r2 > 0) goto L_0x0413
            com.google.android.exoplayer2.util.ParsableByteArray r2 = new com.google.android.exoplayer2.util.ParsableByteArray
            int r3 = (int) r9
            r2.<init>((int) r3)
            com.google.android.exoplayer2.util.ParsableByteArray r3 = r0.m
            byte[] r3 = r3.a
            byte[] r4 = r2.a
            java.lang.System.arraycopy(r3, r8, r4, r8, r6)
            r0.u = r2
            goto L_0x042b
        L_0x0413:
            com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
            java.lang.String r2 = "Leaf atom with length > 2147483647 (unsupported)."
            r1.<init>((java.lang.String) r2)
            throw r1
        L_0x041b:
            com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
            java.lang.String r2 = "Leaf atom defines extended atom size (unsupported)."
            r1.<init>((java.lang.String) r2)
            throw r1
        L_0x0423:
            long r5 = r0.s
            int r2 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r2 > 0) goto L_0x0433
            r0.u = r12
        L_0x042b:
            r2 = 1
            r0.q = r2
        L_0x042e:
            r7 = 1
        L_0x042f:
            if (r7 != 0) goto L_0x0004
            r1 = -1
            return r1
        L_0x0433:
            com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
            java.lang.String r2 = "Skipping atom with length > 2147483647 (unsupported)."
            r1.<init>((java.lang.String) r2)
            throw r1
        L_0x043b:
            com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
            java.lang.String r2 = "Atom size less than header length (unsupported)."
            r1.<init>((java.lang.String) r2)
            goto L_0x0444
        L_0x0443:
            throw r1
        L_0x0444:
            goto L_0x0443
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor.a(com.google.android.exoplayer2.extractor.ExtractorInput, com.google.android.exoplayer2.extractor.PositionHolder):int");
    }

    public final void a(long j2, long j3) {
        int size = this.e.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((TrackBundle) this.e.valueAt(i2)).a();
        }
        this.o.clear();
        this.w = 0;
        this.x = j3;
        this.n.clear();
        b();
    }

    public final void a(ExtractorOutput extractorOutput) {
        int i2;
        this.F = extractorOutput;
        b();
        TrackOutput[] trackOutputArr = new TrackOutput[2];
        this.G = trackOutputArr;
        TrackOutput trackOutput = this.p;
        int i3 = 0;
        if (trackOutput != null) {
            trackOutputArr[0] = trackOutput;
            i2 = 1;
        } else {
            i2 = 0;
        }
        int i4 = 100;
        if ((this.c & 4) != 0) {
            trackOutputArr[i2] = this.F.a(100, 5);
            i4 = 101;
            i2++;
        }
        TrackOutput[] trackOutputArr2 = (TrackOutput[]) Util.a((Object[]) this.G, i2);
        this.G = trackOutputArr2;
        for (TrackOutput a2 : trackOutputArr2) {
            a2.a(b);
        }
        this.H = new TrackOutput[this.d.size()];
        while (i3 < this.H.length) {
            TrackOutput a3 = this.F.a(i4, 3);
            a3.a((Format) this.d.get(i3));
            this.H[i3] = a3;
            i3++;
            i4++;
        }
    }

    public final boolean a(ExtractorInput extractorInput) {
        return Sniffer.a(extractorInput);
    }
}
