package com.google.android.exoplayer2.extractor.mp4;

import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.mp4.Atom;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.mp4.MdtaMetadataEntry;
import com.google.android.exoplayer2.metadata.mp4.SmtaMetadataEntry;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.util.ArrayList;
import java.util.List;

final class AtomParsers {
    private static final byte[] a = Util.c("OpusHead");

    final class ChunkIterator {
        public final int a;
        public int b;
        public int c;
        public long d;
        private final boolean e;
        private final ParsableByteArray f;
        private final ParsableByteArray g;
        private int h;
        private int i;

        public ChunkIterator(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, boolean z) {
            this.g = parsableByteArray;
            this.f = parsableByteArray2;
            this.e = z;
            parsableByteArray2.d(12);
            this.a = parsableByteArray2.o();
            parsableByteArray.d(12);
            this.i = parsableByteArray.o();
            Assertions.b(parsableByteArray.j() != 1 ? false : true, (Object) "first_chunk must be 1");
            this.b = -1;
        }

        public final boolean a() {
            int i2 = this.b + 1;
            this.b = i2;
            if (i2 == this.a) {
                return false;
            }
            this.d = this.e ? this.f.q() : this.f.h();
            if (this.b == this.h) {
                this.c = this.g.o();
                this.g.e(4);
                int i3 = this.i - 1;
                this.i = i3;
                this.h = i3 > 0 ? this.g.o() - 1 : -1;
            }
            return true;
        }
    }

    interface SampleSizeBox {
        int a();

        int b();

        int c();
    }

    final class StsdData {
        public final TrackEncryptionBox[] a;
        public Format b;
        public int c;
        public int d = 0;

        public StsdData(int i) {
            this.a = new TrackEncryptionBox[i];
        }
    }

    final class StszSampleSizeBox implements SampleSizeBox {
        private final int a;
        private final int b;
        private final ParsableByteArray c;

        public StszSampleSizeBox(Atom.LeafAtom leafAtom, Format format) {
            ParsableByteArray parsableByteArray = leafAtom.b;
            this.c = parsableByteArray;
            parsableByteArray.d(12);
            int o = parsableByteArray.o();
            if ("audio/raw".equals(format.l)) {
                int c2 = Util.c(format.y, format.w);
                if (o == 0 || o % c2 != 0) {
                    Log.c("AtomParsers", new StringBuilder(88).append("Audio sample size mismatch. stsd sample size: ").append(c2).append(", stsz sample size: ").append(o).toString());
                    o = c2;
                }
            }
            this.a = o == 0 ? -1 : o;
            this.b = parsableByteArray.o();
        }

        public final int a() {
            return this.b;
        }

        public final int b() {
            return this.a;
        }

        public final int c() {
            int i = this.a;
            return i == -1 ? this.c.o() : i;
        }
    }

    final class Stz2SampleSizeBox implements SampleSizeBox {
        private final ParsableByteArray a;
        private final int b;
        private final int c;
        private int d;
        private int e;

        public Stz2SampleSizeBox(Atom.LeafAtom leafAtom) {
            ParsableByteArray parsableByteArray = leafAtom.b;
            this.a = parsableByteArray;
            parsableByteArray.d(12);
            this.c = parsableByteArray.o() & 255;
            this.b = parsableByteArray.o();
        }

        public final int a() {
            return this.b;
        }

        public final int b() {
            return -1;
        }

        public final int c() {
            int i = this.c;
            if (i == 8) {
                return this.a.c();
            }
            if (i == 16) {
                return this.a.d();
            }
            int i2 = this.d;
            this.d = i2 + 1;
            if (i2 % 2 != 0) {
                return this.e & 15;
            }
            int c2 = this.a.c();
            this.e = c2;
            return (c2 & 240) >> 4;
        }
    }

    final class TkhdData {
        /* access modifiers changed from: private */
        public final int a;
        /* access modifiers changed from: private */
        public final long b;
        /* access modifiers changed from: private */
        public final int c;

        public TkhdData(int i, long j, int i2) {
            this.a = i;
            this.b = j;
            this.c = i2;
        }
    }

    public static Pair a(Atom.LeafAtom leafAtom) {
        ParsableByteArray parsableByteArray = leafAtom.b;
        parsableByteArray.d(8);
        Metadata metadata = null;
        Metadata metadata2 = null;
        while (parsableByteArray.a() >= 8) {
            int i = parsableByteArray.b;
            int j = parsableByteArray.j();
            int j2 = parsableByteArray.j();
            if (j2 == 1835365473) {
                parsableByteArray.d(i);
                int i2 = i + j;
                parsableByteArray.e(8);
                a(parsableByteArray);
                while (true) {
                    if (parsableByteArray.b >= i2) {
                        break;
                    }
                    int i3 = parsableByteArray.b;
                    int j3 = parsableByteArray.j();
                    if (parsableByteArray.j() == 1768715124) {
                        parsableByteArray.d(i3);
                        int i4 = i3 + j3;
                        parsableByteArray.e(8);
                        ArrayList arrayList = new ArrayList();
                        while (parsableByteArray.b < i4) {
                            Metadata.Entry a2 = MetadataUtil.a(parsableByteArray);
                            if (a2 != null) {
                                arrayList.add(a2);
                            }
                        }
                        if (!arrayList.isEmpty()) {
                            metadata = new Metadata((List) arrayList);
                        }
                    } else {
                        parsableByteArray.d(i3 + j3);
                    }
                }
                metadata = null;
            } else if (j2 == 1936553057) {
                parsableByteArray.d(i);
                metadata2 = a(parsableByteArray, i + j);
            }
            parsableByteArray.d(i + j);
        }
        return Pair.create(metadata, metadata2);
    }

    private static Pair a(ParsableByteArray parsableByteArray, int i, int i2) {
        Pair b;
        int i3 = parsableByteArray.b;
        while (i3 - i < i2) {
            parsableByteArray.d(i3);
            int j = parsableByteArray.j();
            Assertions.b(j > 0, (Object) "childAtomSize should be positive");
            if (parsableByteArray.j() == 1936289382 && (b = b(parsableByteArray, i3, j)) != null) {
                return b;
            }
            i3 += j;
        }
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v55, resolved type: com.dreamers.exoplayercore.repack.bG} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v56, resolved type: com.dreamers.exoplayercore.repack.bG} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v57, resolved type: com.dreamers.exoplayercore.repack.bG} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v60, resolved type: com.dreamers.exoplayercore.repack.bG} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v62, resolved type: com.dreamers.exoplayercore.repack.bG} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v68, resolved type: com.dreamers.exoplayercore.repack.bG} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v71, resolved type: com.dreamers.exoplayercore.repack.bG} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:207:0x0321  */
    /* JADX WARNING: Removed duplicated region for block: B:269:0x04c3 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:381:0x06da  */
    /* JADX WARNING: Removed duplicated region for block: B:382:0x0706  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.exoplayer2.extractor.mp4.AtomParsers.StsdData a(com.google.android.exoplayer2.util.ParsableByteArray r29, int r30, int r31, java.lang.String r32, com.google.android.exoplayer2.drm.DrmInitData r33, boolean r34) {
        /*
            r0 = r29
            r1 = r30
            r2 = r32
            r3 = r33
            r4 = 12
            r0.d(r4)
            int r4 = r29.j()
            com.google.android.exoplayer2.extractor.mp4.AtomParsers$StsdData r5 = new com.google.android.exoplayer2.extractor.mp4.AtomParsers$StsdData
            r5.<init>(r4)
            r7 = 0
        L_0x0017:
            if (r7 >= r4) goto L_0x0717
            int r8 = r0.b
            int r9 = r29.j()
            if (r9 <= 0) goto L_0x0023
            r11 = 1
            goto L_0x0024
        L_0x0023:
            r11 = 0
        L_0x0024:
            java.lang.String r12 = "childAtomSize should be positive"
            com.google.android.exoplayer2.util.Assertions.b((boolean) r11, (java.lang.Object) r12)
            int r11 = r29.j()
            r13 = 1635148593(0x61766331, float:2.840654E20)
            r14 = 1987063864(0x76703038, float:1.21789965E33)
            r15 = 1831958048(0x6d317620, float:3.4326032E27)
            r10 = 1701733238(0x656e6376, float:7.035987E22)
            r18 = 0
            if (r11 == r13) goto L_0x04ef
            r13 = 1635148595(0x61766333, float:2.8406544E20)
            if (r11 == r13) goto L_0x04ef
            if (r11 == r10) goto L_0x04ef
            if (r11 == r15) goto L_0x04ef
            r13 = 1836070006(0x6d703476, float:4.646239E27)
            if (r11 == r13) goto L_0x04ef
            r13 = 1752589105(0x68766331, float:4.6541277E24)
            if (r11 == r13) goto L_0x04ef
            r13 = 1751479857(0x68657631, float:4.3344087E24)
            if (r11 == r13) goto L_0x04ef
            r13 = 1932670515(0x73323633, float:1.4119387E31)
            if (r11 == r13) goto L_0x04ef
            if (r11 == r14) goto L_0x04ef
            r13 = 1987063865(0x76703039, float:1.2178997E33)
            if (r11 == r13) goto L_0x04ef
            r13 = 1635135537(0x61763031, float:2.8383572E20)
            if (r11 == r13) goto L_0x04ef
            r13 = 1685479798(0x64766176, float:1.8179687E22)
            if (r11 == r13) goto L_0x04ef
            r13 = 1685479729(0x64766131, float:1.817961E22)
            if (r11 == r13) goto L_0x04ef
            r13 = 1685481573(0x64766865, float:1.8181686E22)
            if (r11 == r13) goto L_0x04ef
            r13 = 1685481521(0x64766831, float:1.8181627E22)
            if (r11 != r13) goto L_0x007c
            goto L_0x04ef
        L_0x007c:
            r10 = 1836069985(0x6d703461, float:4.6462328E27)
            r15 = 1700998451(0x65632d33, float:6.7050686E22)
            r6 = 1633889587(0x61632d33, float:2.6191674E20)
            r13 = 1701733217(0x656e6361, float:7.0359778E22)
            r14 = 1634492771(0x616c6163, float:2.7252807E20)
            if (r11 == r10) goto L_0x01b5
            if (r11 == r13) goto L_0x01b5
            if (r11 == r6) goto L_0x01b5
            if (r11 == r15) goto L_0x01b5
            r10 = 1633889588(0x61632d34, float:2.6191676E20)
            if (r11 == r10) goto L_0x01b5
            r10 = 1685353315(0x64747363, float:1.803728E22)
            if (r11 == r10) goto L_0x01b5
            r10 = 1685353317(0x64747365, float:1.8037282E22)
            if (r11 == r10) goto L_0x01b5
            r10 = 1685353320(0x64747368, float:1.8037286E22)
            if (r11 == r10) goto L_0x01b5
            r10 = 1685353324(0x6474736c, float:1.803729E22)
            if (r11 == r10) goto L_0x01b5
            r10 = 1935764850(0x73616d72, float:1.7860208E31)
            if (r11 == r10) goto L_0x01b5
            r10 = 1935767394(0x73617762, float:1.7863284E31)
            if (r11 == r10) goto L_0x01b5
            r10 = 1819304813(0x6c70636d, float:1.1624469E27)
            if (r11 == r10) goto L_0x01b5
            r10 = 1936684916(0x736f7774, float:1.89725E31)
            if (r11 == r10) goto L_0x01b5
            r10 = 1953984371(0x74776f73, float:7.841539E31)
            if (r11 == r10) goto L_0x01b5
            r10 = 778924082(0x2e6d7032, float:5.398721E-11)
            if (r11 == r10) goto L_0x01b5
            r10 = 778924083(0x2e6d7033, float:5.3987214E-11)
            if (r11 == r10) goto L_0x01b5
            r10 = 1835557169(0x6d686131, float:4.4948762E27)
            if (r11 == r10) goto L_0x01b5
            r10 = 1835560241(0x6d686d31, float:4.495783E27)
            if (r11 == r10) goto L_0x01b5
            if (r11 == r14) goto L_0x01b5
            r10 = 1634492791(0x616c6177, float:2.7252842E20)
            if (r11 == r10) goto L_0x01b5
            r10 = 1970037111(0x756c6177, float:2.9964816E32)
            if (r11 == r10) goto L_0x01b5
            r10 = 1332770163(0x4f707573, float:4.03422899E9)
            if (r11 == r10) goto L_0x01b5
            r10 = 1716281667(0x664c6143, float:2.4128923E23)
            if (r11 != r10) goto L_0x00f1
            goto L_0x01b5
        L_0x00f1:
            r6 = 1414810956(0x54544d4c, float:3.64731957E12)
            if (r11 == r6) goto L_0x014e
            r6 = 1954034535(0x74783367, float:7.865797E31)
            if (r11 == r6) goto L_0x014e
            r6 = 2004251764(0x77767474, float:4.998699E33)
            if (r11 == r6) goto L_0x014e
            r6 = 1937010800(0x73747070, float:1.9366469E31)
            if (r11 == r6) goto L_0x014e
            r6 = 1664495672(0x63363038, float:3.360782E21)
            if (r11 != r6) goto L_0x010b
            goto L_0x014e
        L_0x010b:
            r6 = 1835365492(0x6d657474, float:4.4383032E27)
            if (r11 != r6) goto L_0x0137
            int r6 = r8 + 8
            r10 = 8
            int r6 = r6 + r10
            r0.d(r6)
            r6 = 1835365492(0x6d657474, float:4.4383032E27)
            if (r11 != r6) goto L_0x01f5
            r29.r()
            java.lang.String r6 = r29.r()
            if (r6 == 0) goto L_0x01f5
            com.google.android.exoplayer2.Format$Builder r10 = new com.google.android.exoplayer2.Format$Builder
            r10.<init>()
            com.google.android.exoplayer2.Format$Builder r10 = r10.a((int) r1)
            r10.k = r6
            com.google.android.exoplayer2.Format r6 = r10.a()
            goto L_0x01ac
        L_0x0137:
            r6 = 1667329389(0x63616d6d, float:4.1584024E21)
            if (r11 != r6) goto L_0x01f5
            com.google.android.exoplayer2.Format$Builder r6 = new com.google.android.exoplayer2.Format$Builder
            r6.<init>()
            com.google.android.exoplayer2.Format$Builder r6 = r6.a((int) r1)
            java.lang.String r10 = "application/x-camera-motion"
            r6.k = r10
            com.google.android.exoplayer2.Format r6 = r6.a()
            goto L_0x01ac
        L_0x014e:
            int r6 = r8 + 8
            r10 = 8
            int r6 = r6 + r10
            r0.d(r6)
            r6 = 1414810956(0x54544d4c, float:3.64731957E12)
            r12 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            if (r11 != r6) goto L_0x0165
            java.lang.String r6 = "application/ttml+xml"
        L_0x0162:
            r10 = r18
            goto L_0x0197
        L_0x0165:
            r6 = 1954034535(0x74783367, float:7.865797E31)
            if (r11 != r6) goto L_0x017a
            int r6 = r9 + -8
            int r6 = r6 - r10
            byte[] r10 = new byte[r6]
            r11 = 0
            r0.a(r10, r11, r6)
            com.dreamers.exoplayercore.repack.bG r18 = com.dreamers.exoplayercore.repack.bG.a((java.lang.Object) r10)
            java.lang.String r6 = "application/x-quicktime-tx3g"
            goto L_0x0162
        L_0x017a:
            r6 = 2004251764(0x77767474, float:4.998699E33)
            if (r11 != r6) goto L_0x0182
            java.lang.String r6 = "application/x-mp4-vtt"
            goto L_0x0162
        L_0x0182:
            r6 = 1937010800(0x73747070, float:1.9366469E31)
            if (r11 != r6) goto L_0x018c
            java.lang.String r6 = "application/ttml+xml"
            r12 = 0
            goto L_0x0162
        L_0x018c:
            r6 = 1664495672(0x63363038, float:3.360782E21)
            if (r11 != r6) goto L_0x01af
            r6 = 1
            r5.d = r6
            java.lang.String r6 = "application/x-mp4-cea-608"
            goto L_0x0162
        L_0x0197:
            com.google.android.exoplayer2.Format$Builder r11 = new com.google.android.exoplayer2.Format$Builder
            r11.<init>()
            com.google.android.exoplayer2.Format$Builder r11 = r11.a((int) r1)
            r11.k = r6
            r11.c = r2
            r11.o = r12
            r11.m = r10
            com.google.android.exoplayer2.Format r6 = r11.a()
        L_0x01ac:
            r5.b = r6
            goto L_0x01f5
        L_0x01af:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            throw r0
        L_0x01b5:
            int r10 = r8 + 8
            r14 = 8
            int r10 = r10 + r14
            r0.d(r10)
            r10 = 6
            if (r34 == 0) goto L_0x01ca
            int r20 = r29.d()
            r0.e(r10)
            r14 = r20
            goto L_0x01ce
        L_0x01ca:
            r0.e(r14)
            r14 = 0
        L_0x01ce:
            if (r14 == 0) goto L_0x01ff
            r15 = 1
            if (r14 != r15) goto L_0x01d4
            goto L_0x01ff
        L_0x01d4:
            r15 = 2
            if (r14 != r15) goto L_0x01f5
            r10 = 16
            r0.e(r10)
            long r14 = r29.l()
            double r14 = java.lang.Double.longBitsToDouble(r14)
            long r14 = java.lang.Math.round(r14)
            int r10 = (int) r14
            int r14 = r29.o()
            r15 = 20
            r0.e(r15)
            r25 = r4
            goto L_0x0234
        L_0x01f5:
            r2 = r31
            r25 = r4
            r21 = r7
            r23 = r8
            goto L_0x0708
        L_0x01ff:
            int r15 = r29.d()
            r0.e(r10)
            byte[] r10 = r0.a
            int r6 = r0.b
            int r13 = r6 + 1
            r0.b = r13
            byte r6 = r10[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            r10 = 8
            int r6 = r6 << r10
            byte[] r10 = r0.a
            int r13 = r0.b
            r25 = r4
            int r4 = r13 + 1
            r0.b = r4
            byte r4 = r10[r13]
            r4 = r4 & 255(0xff, float:3.57E-43)
            r10 = r6 | r4
            int r4 = r0.b
            r6 = 2
            int r4 = r4 + r6
            r0.b = r4
            r4 = 1
            if (r14 != r4) goto L_0x0233
            r4 = 16
            r0.e(r4)
        L_0x0233:
            r14 = r15
        L_0x0234:
            int r4 = r0.b
            r6 = 1701733217(0x656e6361, float:7.0359778E22)
            if (r11 != r6) goto L_0x0266
            android.util.Pair r6 = a((com.google.android.exoplayer2.util.ParsableByteArray) r0, (int) r8, (int) r9)
            if (r6 == 0) goto L_0x0261
            java.lang.Object r11 = r6.first
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            if (r3 != 0) goto L_0x024e
            r13 = r18
            goto L_0x0258
        L_0x024e:
            java.lang.Object r13 = r6.second
            com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox r13 = (com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox) r13
            java.lang.String r13 = r13.b
            com.google.android.exoplayer2.drm.DrmInitData r13 = r3.a(r13)
        L_0x0258:
            com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox[] r15 = r5.a
            java.lang.Object r6 = r6.second
            com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox r6 = (com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox) r6
            r15[r7] = r6
            goto L_0x0262
        L_0x0261:
            r13 = r3
        L_0x0262:
            r0.d(r4)
            goto L_0x0267
        L_0x0266:
            r13 = r3
        L_0x0267:
            r6 = 1633889587(0x61632d33, float:2.6191674E20)
            if (r11 != r6) goto L_0x0271
            java.lang.String r6 = "audio/ac3"
        L_0x026e:
            r15 = -1
            goto L_0x0318
        L_0x0271:
            r6 = 1700998451(0x65632d33, float:6.7050686E22)
            if (r11 != r6) goto L_0x0279
            java.lang.String r6 = "audio/eac3"
            goto L_0x026e
        L_0x0279:
            r6 = 1633889588(0x61632d34, float:2.6191676E20)
            if (r11 != r6) goto L_0x0281
            java.lang.String r6 = "audio/ac4"
            goto L_0x026e
        L_0x0281:
            r6 = 1685353315(0x64747363, float:1.803728E22)
            if (r11 != r6) goto L_0x0289
            java.lang.String r6 = "audio/vnd.dts"
            goto L_0x026e
        L_0x0289:
            r6 = 1685353320(0x64747368, float:1.8037286E22)
            if (r11 == r6) goto L_0x0314
            r6 = 1685353324(0x6474736c, float:1.803729E22)
            if (r11 != r6) goto L_0x0295
            goto L_0x0314
        L_0x0295:
            r6 = 1685353317(0x64747365, float:1.8037282E22)
            if (r11 != r6) goto L_0x029d
            java.lang.String r6 = "audio/vnd.dts.hd;profile=lbr"
            goto L_0x026e
        L_0x029d:
            r6 = 1935764850(0x73616d72, float:1.7860208E31)
            if (r11 != r6) goto L_0x02a5
            java.lang.String r6 = "audio/3gpp"
            goto L_0x026e
        L_0x02a5:
            r6 = 1935767394(0x73617762, float:1.7863284E31)
            if (r11 != r6) goto L_0x02ad
            java.lang.String r6 = "audio/amr-wb"
            goto L_0x026e
        L_0x02ad:
            r6 = 1819304813(0x6c70636d, float:1.1624469E27)
            if (r11 == r6) goto L_0x0310
            r6 = 1936684916(0x736f7774, float:1.89725E31)
            if (r11 != r6) goto L_0x02b8
            goto L_0x0310
        L_0x02b8:
            r6 = 1953984371(0x74776f73, float:7.841539E31)
            if (r11 != r6) goto L_0x02c2
            java.lang.String r6 = "audio/raw"
            r15 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x0318
        L_0x02c2:
            r6 = 778924082(0x2e6d7032, float:5.398721E-11)
            if (r11 == r6) goto L_0x030c
            r6 = 778924083(0x2e6d7033, float:5.3987214E-11)
            if (r11 != r6) goto L_0x02cd
            goto L_0x030c
        L_0x02cd:
            r6 = 1835557169(0x6d686131, float:4.4948762E27)
            if (r11 != r6) goto L_0x02d5
            java.lang.String r6 = "audio/mha1"
            goto L_0x026e
        L_0x02d5:
            r6 = 1835560241(0x6d686d31, float:4.495783E27)
            if (r11 != r6) goto L_0x02dd
            java.lang.String r6 = "audio/mhm1"
            goto L_0x026e
        L_0x02dd:
            r6 = 1634492771(0x616c6163, float:2.7252807E20)
            if (r11 != r6) goto L_0x02e5
            java.lang.String r6 = "audio/alac"
            goto L_0x026e
        L_0x02e5:
            r6 = 1634492791(0x616c6177, float:2.7252842E20)
            if (r11 != r6) goto L_0x02ed
            java.lang.String r6 = "audio/g711-alaw"
            goto L_0x026e
        L_0x02ed:
            r6 = 1970037111(0x756c6177, float:2.9964816E32)
            if (r11 != r6) goto L_0x02f6
            java.lang.String r6 = "audio/g711-mlaw"
            goto L_0x026e
        L_0x02f6:
            r6 = 1332770163(0x4f707573, float:4.03422899E9)
            if (r11 != r6) goto L_0x02ff
            java.lang.String r6 = "audio/opus"
            goto L_0x026e
        L_0x02ff:
            r6 = 1716281667(0x664c6143, float:2.4128923E23)
            if (r11 != r6) goto L_0x0308
            java.lang.String r6 = "audio/flac"
            goto L_0x026e
        L_0x0308:
            r6 = r18
            goto L_0x026e
        L_0x030c:
            java.lang.String r6 = "audio/mpeg"
            goto L_0x026e
        L_0x0310:
            java.lang.String r6 = "audio/raw"
            r15 = 2
            goto L_0x0318
        L_0x0314:
            java.lang.String r6 = "audio/vnd.dts.hd"
            goto L_0x026e
        L_0x0318:
            r21 = r7
            r7 = r18
            r11 = r7
        L_0x031d:
            int r3 = r4 - r8
            if (r3 >= r9) goto L_0x04bb
            r0.d(r4)
            int r3 = r29.j()
            r22 = r9
            if (r3 <= 0) goto L_0x032e
            r9 = 1
            goto L_0x032f
        L_0x032e:
            r9 = 0
        L_0x032f:
            com.google.android.exoplayer2.util.Assertions.b((boolean) r9, (java.lang.Object) r12)
            int r9 = r29.j()
            r23 = r8
            r8 = 1835557187(0x6d686143, float:4.4948815E27)
            if (r9 != r8) goto L_0x0357
            int r7 = r3 + -13
            byte[] r8 = new byte[r7]
            int r9 = r4 + 13
            r0.d(r9)
            r9 = 0
            r0.a(r8, r9, r7)
            com.dreamers.exoplayercore.repack.bG r7 = com.dreamers.exoplayercore.repack.bG.a((java.lang.Object) r8)
        L_0x034e:
            r24 = r12
            r8 = -1
            r16 = 1
            r19 = 2
            goto L_0x04b0
        L_0x0357:
            r8 = 1702061171(0x65736473, float:7.183675E22)
            if (r9 == r8) goto L_0x0453
            if (r34 == 0) goto L_0x036d
            r8 = 2002876005(0x77617665, float:4.5729223E33)
            if (r9 != r8) goto L_0x036d
            r2 = 1702061171(0x65736473, float:7.183675E22)
            r8 = 0
            r16 = 1
            r19 = 2
            goto L_0x045b
        L_0x036d:
            r8 = 1684103987(0x64616333, float:1.6630662E22)
            if (r9 != r8) goto L_0x038b
            int r8 = r4 + 8
            r0.d(r8)
            java.lang.String r8 = java.lang.Integer.toString(r30)
            com.google.android.exoplayer2.Format r8 = com.google.android.exoplayer2.audio.Ac3Util.a(r0, r8, r2, r13)
        L_0x037f:
            r5.b = r8
            r2 = 1634492771(0x616c6163, float:2.7252807E20)
            r9 = 0
            r16 = 1
            r19 = 2
            goto L_0x044f
        L_0x038b:
            r8 = 1684366131(0x64656333, float:1.692581E22)
            if (r9 != r8) goto L_0x039e
            int r8 = r4 + 8
            r0.d(r8)
            java.lang.String r8 = java.lang.Integer.toString(r30)
            com.google.android.exoplayer2.Format r8 = com.google.android.exoplayer2.audio.Ac3Util.b(r0, r8, r2, r13)
            goto L_0x037f
        L_0x039e:
            r8 = 1684103988(0x64616334, float:1.6630663E22)
            if (r9 != r8) goto L_0x03b1
            int r8 = r4 + 8
            r0.d(r8)
            java.lang.String r8 = java.lang.Integer.toString(r30)
            com.google.android.exoplayer2.Format r8 = com.google.android.exoplayer2.audio.Ac4Util.a(r0, r8, r2, r13)
            goto L_0x037f
        L_0x03b1:
            r8 = 1684305011(0x64647473, float:1.6856995E22)
            if (r9 != r8) goto L_0x03ce
            com.google.android.exoplayer2.Format$Builder r8 = new com.google.android.exoplayer2.Format$Builder
            r8.<init>()
            com.google.android.exoplayer2.Format$Builder r8 = r8.a((int) r1)
            r8.k = r6
            r8.x = r14
            r8.y = r10
            r8.n = r13
            r8.c = r2
            com.google.android.exoplayer2.Format r8 = r8.a()
            goto L_0x037f
        L_0x03ce:
            r8 = 1682927731(0x644f7073, float:1.5306315E22)
            if (r9 != r8) goto L_0x03ed
            int r7 = r3 + -8
            byte[] r8 = a
            int r9 = r8.length
            int r9 = r9 + r7
            byte[] r9 = java.util.Arrays.copyOf(r8, r9)
            int r2 = r4 + 8
            r0.d(r2)
            int r2 = r8.length
            r0.a(r9, r2, r7)
            java.util.List r2 = com.google.android.exoplayer2.audio.OpusUtil.b((byte[]) r9)
            r7 = r2
            goto L_0x034e
        L_0x03ed:
            r2 = 1684425825(0x64664c61, float:1.6993019E22)
            if (r9 != r2) goto L_0x041d
            int r2 = r3 + -12
            int r7 = r2 + 4
            byte[] r7 = new byte[r7]
            r8 = 102(0x66, float:1.43E-43)
            r9 = 0
            r7[r9] = r8
            r8 = 76
            r16 = 1
            r7[r16] = r8
            r8 = 97
            r19 = 2
            r7[r19] = r8
            r8 = 67
            r9 = 3
            r7[r9] = r8
            int r8 = r4 + 12
            r0.d(r8)
            r8 = 4
            r0.a(r7, r8, r2)
            com.dreamers.exoplayercore.repack.bG r2 = com.dreamers.exoplayercore.repack.bG.a((java.lang.Object) r7)
            r7 = r2
            goto L_0x044f
        L_0x041d:
            r2 = 1634492771(0x616c6163, float:2.7252807E20)
            r16 = 1
            r19 = 2
            if (r9 != r2) goto L_0x044e
            int r7 = r3 + -12
            byte[] r8 = new byte[r7]
            int r9 = r4 + 12
            r0.d(r9)
            r9 = 0
            r0.a(r8, r9, r7)
            android.util.Pair r7 = com.google.android.exoplayer2.util.CodecSpecificDataUtil.a((byte[]) r8)
            java.lang.Object r10 = r7.first
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            java.lang.Object r7 = r7.second
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            com.dreamers.exoplayercore.repack.bG r8 = com.dreamers.exoplayercore.repack.bG.a((java.lang.Object) r8)
            r14 = r7
            r7 = r8
            goto L_0x044f
        L_0x044e:
            r9 = 0
        L_0x044f:
            r24 = r12
            r8 = -1
            goto L_0x04b0
        L_0x0453:
            r8 = 0
            r16 = 1
            r19 = 2
            r2 = 1702061171(0x65736473, float:7.183675E22)
        L_0x045b:
            if (r9 != r2) goto L_0x0462
            r2 = r4
            r24 = r12
        L_0x0460:
            r8 = -1
            goto L_0x048a
        L_0x0462:
            int r2 = r0.b
        L_0x0464:
            int r9 = r2 - r4
            if (r9 >= r3) goto L_0x0486
            r0.d(r2)
            int r9 = r29.j()
            if (r9 <= 0) goto L_0x0472
            r8 = 1
        L_0x0472:
            com.google.android.exoplayer2.util.Assertions.b((boolean) r8, (java.lang.Object) r12)
            int r8 = r29.j()
            r24 = r12
            r12 = 1702061171(0x65736473, float:7.183675E22)
            if (r8 != r12) goto L_0x0481
            goto L_0x0460
        L_0x0481:
            int r2 = r2 + r9
            r12 = r24
            r8 = 0
            goto L_0x0464
        L_0x0486:
            r24 = r12
            r2 = -1
            goto L_0x0460
        L_0x048a:
            if (r2 == r8) goto L_0x04b0
            android.util.Pair r2 = b(r0, r2)
            java.lang.Object r6 = r2.first
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r2 = r2.second
            byte[] r2 = (byte[]) r2
            if (r2 == 0) goto L_0x04b0
            java.lang.String r7 = "audio/mp4a-latm"
            boolean r7 = r7.equals(r6)
            if (r7 == 0) goto L_0x04ac
            com.google.android.exoplayer2.audio.AacUtil$Config r7 = com.google.android.exoplayer2.audio.AacUtil.a((byte[]) r2)
            int r10 = r7.a
            int r14 = r7.b
            java.lang.String r11 = r7.c
        L_0x04ac:
            com.dreamers.exoplayercore.repack.bG r7 = com.dreamers.exoplayercore.repack.bG.a((java.lang.Object) r2)
        L_0x04b0:
            int r4 = r4 + r3
            r2 = r32
            r9 = r22
            r8 = r23
            r12 = r24
            goto L_0x031d
        L_0x04bb:
            r23 = r8
            r22 = r9
            com.google.android.exoplayer2.Format r2 = r5.b
            if (r2 != 0) goto L_0x04e7
            if (r6 == 0) goto L_0x04e7
            com.google.android.exoplayer2.Format$Builder r2 = new com.google.android.exoplayer2.Format$Builder
            r2.<init>()
            com.google.android.exoplayer2.Format$Builder r2 = r2.a((int) r1)
            r2.k = r6
            r2.h = r11
            r2.x = r14
            r2.y = r10
            r2.z = r15
            r2.m = r7
            r2.n = r13
            r3 = r32
            r2.c = r3
            com.google.android.exoplayer2.Format r2 = r2.a()
            r5.b = r2
            goto L_0x04e9
        L_0x04e7:
            r3 = r32
        L_0x04e9:
            r2 = r31
            r9 = r22
            goto L_0x0708
        L_0x04ef:
            r3 = r2
            r25 = r4
            r21 = r7
            r23 = r8
            r22 = r9
            r24 = r12
            r8 = -1
            r16 = 1
            r19 = 2
            int r2 = r23 + 8
            r4 = 8
            int r2 = r2 + r4
            r0.d(r2)
            r2 = 16
            r0.e(r2)
            int r2 = r29.d()
            int r4 = r29.d()
            r6 = 50
            r0.e(r6)
            int r6 = r0.b
            if (r11 != r10) goto L_0x0550
            r9 = r22
            r7 = r23
            android.util.Pair r10 = a((com.google.android.exoplayer2.util.ParsableByteArray) r0, (int) r7, (int) r9)
            if (r10 == 0) goto L_0x0549
            java.lang.Object r11 = r10.first
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r12 = r33
            if (r12 != 0) goto L_0x0536
            r13 = r18
            goto L_0x0540
        L_0x0536:
            java.lang.Object r13 = r10.second
            com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox r13 = (com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox) r13
            java.lang.String r13 = r13.b
            com.google.android.exoplayer2.drm.DrmInitData r13 = r12.a(r13)
        L_0x0540:
            com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox[] r8 = r5.a
            java.lang.Object r10 = r10.second
            com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox r10 = (com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox) r10
            r8[r21] = r10
            goto L_0x054c
        L_0x0549:
            r12 = r33
            r13 = r12
        L_0x054c:
            r0.d(r6)
            goto L_0x0557
        L_0x0550:
            r12 = r33
            r9 = r22
            r7 = r23
            r13 = r12
        L_0x0557:
            if (r11 != r15) goto L_0x055c
            java.lang.String r8 = "video/mpeg"
            goto L_0x055e
        L_0x055c:
            r8 = r18
        L_0x055e:
            r10 = 1065353216(0x3f800000, float:1.0)
            r10 = r8
            r26 = r18
            r27 = r26
            r28 = r27
            r8 = -1
            r15 = 1065353216(0x3f800000, float:1.0)
            r17 = 0
        L_0x056c:
            int r14 = r6 - r7
            if (r14 >= r9) goto L_0x06d6
            r0.d(r6)
            int r14 = r0.b
            int r3 = r29.j()
            if (r3 != 0) goto L_0x0580
            int r12 = r0.b
            int r12 = r12 - r7
            if (r12 == r9) goto L_0x06d6
        L_0x0580:
            r23 = r7
            r7 = r24
            if (r3 <= 0) goto L_0x0588
            r12 = 1
            goto L_0x0589
        L_0x0588:
            r12 = 0
        L_0x0589:
            com.google.android.exoplayer2.util.Assertions.b((boolean) r12, (java.lang.Object) r7)
            int r12 = r29.j()
            r24 = r7
            r7 = 1635148611(0x61766343, float:2.8406573E20)
            if (r12 != r7) goto L_0x05b7
            if (r10 != 0) goto L_0x059b
            r7 = 1
            goto L_0x059c
        L_0x059b:
            r7 = 0
        L_0x059c:
            com.google.android.exoplayer2.util.Assertions.b((boolean) r7)
            int r14 = r14 + 8
            r0.d(r14)
            com.google.android.exoplayer2.video.AvcConfig r7 = com.google.android.exoplayer2.video.AvcConfig.a(r29)
            java.util.List r10 = r7.a
            int r12 = r7.b
            r5.c = r12
            if (r17 != 0) goto L_0x05b2
            float r15 = r7.e
        L_0x05b2:
            java.lang.String r7 = r7.f
            java.lang.String r12 = "video/avc"
            goto L_0x05d7
        L_0x05b7:
            r7 = 1752589123(0x68766343, float:4.6541328E24)
            if (r12 != r7) goto L_0x05e1
            if (r10 != 0) goto L_0x05c0
            r7 = 1
            goto L_0x05c1
        L_0x05c0:
            r7 = 0
        L_0x05c1:
            com.google.android.exoplayer2.util.Assertions.b((boolean) r7)
            int r14 = r14 + 8
            r0.d(r14)
            com.google.android.exoplayer2.video.HevcConfig r7 = com.google.android.exoplayer2.video.HevcConfig.a(r29)
            java.util.List r10 = r7.a
            int r12 = r7.b
            r5.c = r12
            java.lang.String r7 = r7.c
            java.lang.String r12 = "video/hevc"
        L_0x05d7:
            r28 = r7
            r26 = r10
        L_0x05db:
            r20 = r11
            r10 = r12
        L_0x05de:
            r11 = 3
            goto L_0x06cb
        L_0x05e1:
            r7 = 1685480259(0x64766343, float:1.8180206E22)
            if (r12 == r7) goto L_0x06bc
            r7 = 1685485123(0x64767643, float:1.8185683E22)
            if (r12 != r7) goto L_0x05ed
            goto L_0x06bc
        L_0x05ed:
            r7 = 1987076931(0x76706343, float:1.21891066E33)
            if (r12 != r7) goto L_0x0605
            if (r10 != 0) goto L_0x05f6
            r7 = 1
            goto L_0x05f7
        L_0x05f6:
            r7 = 0
        L_0x05f7:
            com.google.android.exoplayer2.util.Assertions.b((boolean) r7)
            r7 = 1987063864(0x76703038, float:1.21789965E33)
            if (r11 != r7) goto L_0x0602
            java.lang.String r10 = "video/x-vnd.on2.vp8"
            goto L_0x0615
        L_0x0602:
            java.lang.String r10 = "video/x-vnd.on2.vp9"
            goto L_0x0615
        L_0x0605:
            r7 = 1635135811(0x61763143, float:2.8384055E20)
            if (r12 != r7) goto L_0x0618
            if (r10 != 0) goto L_0x060e
            r7 = 1
            goto L_0x060f
        L_0x060e:
            r7 = 0
        L_0x060f:
            com.google.android.exoplayer2.util.Assertions.b((boolean) r7)
            java.lang.String r7 = "video/av01"
        L_0x0614:
            r10 = r7
        L_0x0615:
            r20 = r11
            goto L_0x05de
        L_0x0618:
            r7 = 1681012275(0x64323633, float:1.3149704E22)
            if (r12 != r7) goto L_0x0628
            if (r10 != 0) goto L_0x0621
            r7 = 1
            goto L_0x0622
        L_0x0621:
            r7 = 0
        L_0x0622:
            com.google.android.exoplayer2.util.Assertions.b((boolean) r7)
            java.lang.String r7 = "video/3gpp"
            goto L_0x0614
        L_0x0628:
            r7 = 1702061171(0x65736473, float:7.183675E22)
            if (r12 != r7) goto L_0x0648
            if (r10 != 0) goto L_0x0631
            r10 = 1
            goto L_0x0632
        L_0x0631:
            r10 = 0
        L_0x0632:
            com.google.android.exoplayer2.util.Assertions.b((boolean) r10)
            android.util.Pair r10 = b(r0, r14)
            java.lang.Object r12 = r10.first
            java.lang.String r12 = (java.lang.String) r12
            java.lang.Object r10 = r10.second
            byte[] r10 = (byte[]) r10
            if (r10 == 0) goto L_0x05db
            com.dreamers.exoplayercore.repack.bG r26 = com.dreamers.exoplayercore.repack.bG.a((java.lang.Object) r10)
            goto L_0x05db
        L_0x0648:
            r7 = 1885434736(0x70617370, float:2.7909473E29)
            if (r12 != r7) goto L_0x0665
            int r14 = r14 + 8
            r0.d(r14)
            int r7 = r29.o()
            int r12 = r29.o()
            float r7 = (float) r7
            float r12 = (float) r12
            float r7 = r7 / r12
            r15 = r7
            r20 = r11
            r11 = 3
            r17 = 1
            goto L_0x06cb
        L_0x0665:
            r7 = 1937126244(0x73763364, float:1.9506033E31)
            if (r12 != r7) goto L_0x069b
            int r7 = r14 + 8
        L_0x066c:
            int r12 = r7 - r14
            if (r12 >= r3) goto L_0x0695
            r0.d(r7)
            int r12 = r29.j()
            r20 = r11
            int r11 = r29.j()
            r22 = r14
            r14 = 1886547818(0x70726f6a, float:3.0012025E29)
            if (r11 != r14) goto L_0x068f
            byte[] r11 = r0.a
            int r12 = r12 + r7
            byte[] r7 = java.util.Arrays.copyOfRange(r11, r7, r12)
            r27 = r7
            goto L_0x05de
        L_0x068f:
            int r7 = r7 + r12
            r11 = r20
            r14 = r22
            goto L_0x066c
        L_0x0695:
            r20 = r11
            r27 = r18
            goto L_0x05de
        L_0x069b:
            r20 = r11
            r7 = 1936995172(0x73743364, float:1.9347576E31)
            if (r12 != r7) goto L_0x05de
            int r7 = r29.c()
            r11 = 3
            r0.e(r11)
            if (r7 != 0) goto L_0x06cb
            int r7 = r29.c()
            switch(r7) {
                case 0: goto L_0x06ba;
                case 1: goto L_0x06b8;
                case 2: goto L_0x06b6;
                case 3: goto L_0x06b4;
                default: goto L_0x06b3;
            }
        L_0x06b3:
            goto L_0x06cb
        L_0x06b4:
            r8 = 3
            goto L_0x06cb
        L_0x06b6:
            r8 = 2
            goto L_0x06cb
        L_0x06b8:
            r8 = 1
            goto L_0x06cb
        L_0x06ba:
            r8 = 0
            goto L_0x06cb
        L_0x06bc:
            r20 = r11
            r11 = 3
            com.google.android.exoplayer2.video.DolbyVisionConfig r7 = com.google.android.exoplayer2.video.DolbyVisionConfig.a(r29)
            if (r7 == 0) goto L_0x06cb
            java.lang.String r7 = r7.a
            java.lang.String r10 = "video/dolby-vision"
            r28 = r7
        L_0x06cb:
            int r6 = r6 + r3
            r3 = r32
            r12 = r33
            r11 = r20
            r7 = r23
            goto L_0x056c
        L_0x06d6:
            r23 = r7
            if (r10 == 0) goto L_0x0706
            com.google.android.exoplayer2.Format$Builder r3 = new com.google.android.exoplayer2.Format$Builder
            r3.<init>()
            com.google.android.exoplayer2.Format$Builder r3 = r3.a((int) r1)
            r3.k = r10
            r6 = r28
            r3.h = r6
            r3.p = r2
            r3.q = r4
            r3.t = r15
            r2 = r31
            r3.s = r2
            r4 = r27
            r3.u = r4
            r3.v = r8
            r4 = r26
            r3.m = r4
            r3.n = r13
            com.google.android.exoplayer2.Format r3 = r3.a()
            r5.b = r3
            goto L_0x0708
        L_0x0706:
            r2 = r31
        L_0x0708:
            int r8 = r23 + r9
            r0.d(r8)
            int r7 = r21 + 1
            r2 = r32
            r3 = r33
            r4 = r25
            goto L_0x0017
        L_0x0717:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp4.AtomParsers.a(com.google.android.exoplayer2.util.ParsableByteArray, int, int, java.lang.String, com.google.android.exoplayer2.drm.DrmInitData, boolean):com.google.android.exoplayer2.extractor.mp4.AtomParsers$StsdData");
    }

    private static TrackEncryptionBox a(ParsableByteArray parsableByteArray, int i, int i2, String str) {
        int i3;
        int i4;
        int i5 = i + 8;
        while (true) {
            byte[] bArr = null;
            if (i5 - i >= i2) {
                return null;
            }
            parsableByteArray.d(i5);
            int j = parsableByteArray.j();
            if (parsableByteArray.j() == 1952804451) {
                int a2 = Atom.a(parsableByteArray.j());
                parsableByteArray.e(1);
                if (a2 == 0) {
                    parsableByteArray.e(1);
                    i4 = 0;
                    i3 = 0;
                } else {
                    int c = parsableByteArray.c();
                    i3 = c & 15;
                    i4 = (c & 240) >> 4;
                }
                boolean z = parsableByteArray.c() == 1;
                int c2 = parsableByteArray.c();
                byte[] bArr2 = new byte[16];
                parsableByteArray.a(bArr2, 0, 16);
                if (z && c2 == 0) {
                    int c3 = parsableByteArray.c();
                    bArr = new byte[c3];
                    parsableByteArray.a(bArr, 0, c3);
                }
                return new TrackEncryptionBox(z, str, c2, bArr2, i4, i3, bArr);
            }
            i5 += j;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:107:0x0255  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0258  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x02cb  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x02dc  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0139  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.exoplayer2.extractor.mp4.TrackSampleTable a(com.google.android.exoplayer2.extractor.mp4.Track r36, com.google.android.exoplayer2.extractor.mp4.Atom.ContainerAtom r37, com.google.android.exoplayer2.extractor.GaplessInfoHolder r38) {
        /*
            r1 = r36
            r0 = r37
            r2 = r38
            r3 = 1937011578(0x7374737a, float:1.936741E31)
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r3 = r0.d(r3)
            if (r3 == 0) goto L_0x0017
            com.google.android.exoplayer2.extractor.mp4.AtomParsers$StszSampleSizeBox r4 = new com.google.android.exoplayer2.extractor.mp4.AtomParsers$StszSampleSizeBox
            com.google.android.exoplayer2.Format r5 = r1.f
            r4.<init>(r3, r5)
            goto L_0x0025
        L_0x0017:
            r3 = 1937013298(0x73747a32, float:1.9369489E31)
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r3 = r0.d(r3)
            if (r3 == 0) goto L_0x0543
            com.google.android.exoplayer2.extractor.mp4.AtomParsers$Stz2SampleSizeBox r4 = new com.google.android.exoplayer2.extractor.mp4.AtomParsers$Stz2SampleSizeBox
            r4.<init>(r3)
        L_0x0025:
            int r3 = r4.a()
            r5 = 0
            if (r3 != 0) goto L_0x0043
            com.google.android.exoplayer2.extractor.mp4.TrackSampleTable r9 = new com.google.android.exoplayer2.extractor.mp4.TrackSampleTable
            long[] r2 = new long[r5]
            int[] r3 = new int[r5]
            r4 = 0
            long[] r6 = new long[r5]
            int[] r7 = new int[r5]
            r10 = 0
            r0 = r9
            r1 = r36
            r5 = r6
            r6 = r7
            r7 = r10
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return r9
        L_0x0043:
            r6 = 1937007471(0x7374636f, float:1.9362445E31)
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r6 = r0.d(r6)
            r7 = 1
            if (r6 != 0) goto L_0x005c
            r6 = 1668232756(0x636f3634, float:4.4126776E21)
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r6 = r0.d(r6)
            java.lang.Object r6 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r6)
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r6 = (com.google.android.exoplayer2.extractor.mp4.Atom.LeafAtom) r6
            r8 = 1
            goto L_0x005d
        L_0x005c:
            r8 = 0
        L_0x005d:
            com.google.android.exoplayer2.util.ParsableByteArray r6 = r6.b
            r9 = 1937011555(0x73747363, float:1.9367382E31)
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r9 = r0.d(r9)
            java.lang.Object r9 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r9)
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r9 = (com.google.android.exoplayer2.extractor.mp4.Atom.LeafAtom) r9
            com.google.android.exoplayer2.util.ParsableByteArray r9 = r9.b
            r10 = 1937011827(0x73747473, float:1.9367711E31)
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r10 = r0.d(r10)
            java.lang.Object r10 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r10)
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r10 = (com.google.android.exoplayer2.extractor.mp4.Atom.LeafAtom) r10
            com.google.android.exoplayer2.util.ParsableByteArray r10 = r10.b
            r11 = 1937011571(0x73747373, float:1.9367401E31)
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r11 = r0.d(r11)
            if (r11 == 0) goto L_0x0089
            com.google.android.exoplayer2.util.ParsableByteArray r11 = r11.b
            goto L_0x008a
        L_0x0089:
            r11 = 0
        L_0x008a:
            r13 = 1668576371(0x63747473, float:4.5093966E21)
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r0 = r0.d(r13)
            if (r0 == 0) goto L_0x0096
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r0.b
            goto L_0x0097
        L_0x0096:
            r0 = 0
        L_0x0097:
            com.google.android.exoplayer2.extractor.mp4.AtomParsers$ChunkIterator r13 = new com.google.android.exoplayer2.extractor.mp4.AtomParsers$ChunkIterator
            r13.<init>(r9, r6, r8)
            r6 = 12
            r10.d(r6)
            int r8 = r10.o()
            int r8 = r8 - r7
            int r9 = r10.o()
            int r14 = r10.o()
            if (r0 == 0) goto L_0x00b8
            r0.d(r6)
            int r15 = r0.o()
            goto L_0x00b9
        L_0x00b8:
            r15 = 0
        L_0x00b9:
            r12 = -1
            if (r11 == 0) goto L_0x00ce
            r11.d(r6)
            int r6 = r11.o()
            if (r6 <= 0) goto L_0x00cc
            int r16 = r11.o()
            int r16 = r16 + -1
            goto L_0x00d1
        L_0x00cc:
            r11 = 0
            goto L_0x00cf
        L_0x00ce:
            r6 = 0
        L_0x00cf:
            r16 = -1
        L_0x00d1:
            int r5 = r4.b()
            com.google.android.exoplayer2.Format r7 = r1.f
            java.lang.String r7 = r7.l
            if (r5 == r12) goto L_0x00fe
            java.lang.String r12 = "audio/raw"
            boolean r12 = r12.equals(r7)
            if (r12 != 0) goto L_0x00f3
            java.lang.String r12 = "audio/g711-mlaw"
            boolean r12 = r12.equals(r7)
            if (r12 != 0) goto L_0x00f3
            java.lang.String r12 = "audio/g711-alaw"
            boolean r7 = r12.equals(r7)
            if (r7 == 0) goto L_0x00fe
        L_0x00f3:
            if (r8 != 0) goto L_0x00fe
            if (r15 != 0) goto L_0x00fe
            if (r6 != 0) goto L_0x00fe
            r37 = r8
            r12 = r9
            r7 = 1
            goto L_0x0102
        L_0x00fe:
            r37 = r8
            r12 = r9
            r7 = 0
        L_0x0102:
            if (r7 == 0) goto L_0x0139
            int r0 = r13.a
            long[] r0 = new long[r0]
            int r4 = r13.a
            int[] r4 = new int[r4]
        L_0x010c:
            boolean r6 = r13.a()
            if (r6 == 0) goto L_0x011f
            int r6 = r13.b
            long r10 = r13.d
            r0[r6] = r10
            int r6 = r13.b
            int r7 = r13.c
            r4[r6] = r7
            goto L_0x010c
        L_0x011f:
            long r6 = (long) r14
            com.google.android.exoplayer2.extractor.mp4.FixedSampleSizeRechunker$Results r0 = com.google.android.exoplayer2.extractor.mp4.FixedSampleSizeRechunker.a(r5, r0, r4, r6)
            long[] r4 = r0.a
            int[] r5 = r0.b
            int r6 = r0.c
            long[] r7 = r0.d
            int[] r10 = r0.e
            long r11 = r0.f
            r0 = r3
            r2 = r4
            r3 = r5
            r5 = r7
            r9 = r10
            r7 = r11
            r4 = r1
            goto L_0x02bd
        L_0x0139:
            long[] r5 = new long[r3]
            int[] r7 = new int[r3]
            long[] r8 = new long[r3]
            int[] r9 = new int[r3]
            r22 = r10
            r2 = r16
            r1 = 0
            r10 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r23 = 0
            r25 = 0
            r16 = r15
            r15 = r14
            r14 = r12
        L_0x0155:
            java.lang.String r12 = "AtomParsers"
            if (r1 >= r3) goto L_0x021b
            r26 = r25
            r25 = r19
            r19 = 1
        L_0x015f:
            if (r25 != 0) goto L_0x017c
            boolean r19 = r13.a()
            if (r19 == 0) goto L_0x017c
            r28 = r14
            r29 = r15
            long r14 = r13.d
            r30 = r3
            int r3 = r13.c
            r25 = r3
            r26 = r14
            r14 = r28
            r15 = r29
            r3 = r30
            goto L_0x015f
        L_0x017c:
            r30 = r3
            r28 = r14
            r29 = r15
            if (r19 != 0) goto L_0x01a0
            java.lang.String r2 = "Unexpected end of chunk data"
            com.google.android.exoplayer2.util.Log.c(r12, r2)
            long[] r5 = java.util.Arrays.copyOf(r5, r1)
            int[] r7 = java.util.Arrays.copyOf(r7, r1)
            long[] r8 = java.util.Arrays.copyOf(r8, r1)
            int[] r9 = java.util.Arrays.copyOf(r9, r1)
            r3 = r1
            r2 = r20
            r1 = r25
            goto L_0x0223
        L_0x01a0:
            if (r0 == 0) goto L_0x01b3
        L_0x01a2:
            if (r21 != 0) goto L_0x01b1
            if (r16 <= 0) goto L_0x01b1
            int r21 = r0.o()
            int r20 = r0.j()
            int r16 = r16 + -1
            goto L_0x01a2
        L_0x01b1:
            int r21 = r21 + -1
        L_0x01b3:
            r3 = r20
            r5[r1] = r26
            int r12 = r4.c()
            r7[r1] = r12
            if (r12 <= r10) goto L_0x01c0
            r10 = r12
        L_0x01c0:
            long r14 = (long) r3
            long r14 = r23 + r14
            r8[r1] = r14
            if (r11 != 0) goto L_0x01c9
            r12 = 1
            goto L_0x01ca
        L_0x01c9:
            r12 = 0
        L_0x01ca:
            r9[r1] = r12
            if (r1 != r2) goto L_0x01e0
            r12 = 1
            r9[r1] = r12
            int r6 = r6 + -1
            if (r6 <= 0) goto L_0x01e0
            java.lang.Object r2 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r11)
            com.google.android.exoplayer2.util.ParsableByteArray r2 = (com.google.android.exoplayer2.util.ParsableByteArray) r2
            int r2 = r2.o()
            int r2 = r2 - r12
        L_0x01e0:
            r15 = r2
            r12 = r3
            r14 = r29
            long r2 = (long) r14
            long r23 = r23 + r2
            int r2 = r28 + -1
            if (r2 != 0) goto L_0x01f8
            if (r37 <= 0) goto L_0x01f8
            int r2 = r22.o()
            int r3 = r22.j()
            int r14 = r37 + -1
            goto L_0x01fb
        L_0x01f8:
            r3 = r14
            r14 = r37
        L_0x01fb:
            r37 = r2
            r2 = r7[r1]
            r19 = r3
            long r2 = (long) r2
            long r2 = r26 + r2
            int r20 = r25 + -1
            int r1 = r1 + 1
            r25 = r2
            r2 = r15
            r15 = r19
            r19 = r20
            r3 = r30
            r20 = r12
            r35 = r14
            r14 = r37
            r37 = r35
            goto L_0x0155
        L_0x021b:
            r30 = r3
            r28 = r14
            r1 = r19
            r2 = r20
        L_0x0223:
            long r13 = (long) r2
            long r13 = r23 + r13
            if (r0 == 0) goto L_0x0238
        L_0x0228:
            if (r16 <= 0) goto L_0x0238
            int r2 = r0.o()
            if (r2 == 0) goto L_0x0232
            r0 = 0
            goto L_0x0239
        L_0x0232:
            r0.j()
            int r16 = r16 + -1
            goto L_0x0228
        L_0x0238:
            r0 = 1
        L_0x0239:
            if (r6 != 0) goto L_0x024d
            if (r28 != 0) goto L_0x024d
            if (r1 != 0) goto L_0x024d
            if (r37 != 0) goto L_0x024d
            r2 = r21
            if (r2 != 0) goto L_0x024f
            if (r0 != 0) goto L_0x0248
            goto L_0x024f
        L_0x0248:
            r4 = r36
            r16 = r3
            goto L_0x02b6
        L_0x024d:
            r2 = r21
        L_0x024f:
            r4 = r36
            int r11 = r4.a
            if (r0 != 0) goto L_0x0258
            java.lang.String r0 = ", ctts invalid"
            goto L_0x025a
        L_0x0258:
            java.lang.String r0 = ""
        L_0x025a:
            java.lang.String r15 = java.lang.String.valueOf(r0)
            int r15 = r15.length()
            int r15 = r15 + 262
            r16 = r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r15)
            java.lang.String r15 = "Inconsistent stbl box for track "
            java.lang.StringBuilder r3 = r3.append(r15)
            java.lang.StringBuilder r3 = r3.append(r11)
            java.lang.String r11 = ": remainingSynchronizationSamples "
            java.lang.StringBuilder r3 = r3.append(r11)
            java.lang.StringBuilder r3 = r3.append(r6)
            java.lang.String r6 = ", remainingSamplesAtTimestampDelta "
            java.lang.StringBuilder r3 = r3.append(r6)
            r6 = r28
            java.lang.StringBuilder r3 = r3.append(r6)
            java.lang.String r6 = ", remainingSamplesInChunk "
            java.lang.StringBuilder r3 = r3.append(r6)
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r3 = ", remainingTimestampDeltaChanges "
            java.lang.StringBuilder r1 = r1.append(r3)
            r3 = r37
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.String r3 = ", remainingSamplesAtTimestampOffset "
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r0 = r0.toString()
            com.google.android.exoplayer2.util.Log.c(r12, r0)
        L_0x02b6:
            r2 = r5
            r3 = r7
            r5 = r8
            r6 = r10
            r7 = r13
            r0 = r16
        L_0x02bd:
            r12 = 1000000(0xf4240, double:4.940656E-318)
            long r14 = r4.c
            r10 = r7
            long r10 = com.google.android.exoplayer2.util.Util.b((long) r10, (long) r12, (long) r14)
            long[] r1 = r4.h
            if (r1 != 0) goto L_0x02dc
            long r0 = r4.c
            com.google.android.exoplayer2.util.Util.a((long[]) r5, (long) r0)
            com.google.android.exoplayer2.extractor.mp4.TrackSampleTable r12 = new com.google.android.exoplayer2.extractor.mp4.TrackSampleTable
            r0 = r12
            r1 = r36
            r4 = r6
            r6 = r9
            r7 = r10
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return r12
        L_0x02dc:
            long[] r1 = r4.h
            int r1 = r1.length
            r10 = 1
            if (r1 != r10) goto L_0x0390
            int r1 = r4.b
            if (r1 != r10) goto L_0x0390
            int r1 = r5.length
            r10 = 2
            if (r1 < r10) goto L_0x0390
            long[] r1 = r4.i
            java.lang.Object r1 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r1)
            long[] r1 = (long[]) r1
            r10 = 0
            r11 = r1[r10]
            long[] r1 = r4.h
            r19 = r1[r10]
            long r13 = r4.c
            r15 = r11
            long r10 = r4.d
            r21 = r13
            r23 = r10
            long r10 = com.google.android.exoplayer2.util.Util.b((long) r19, (long) r21, (long) r23)
            long r10 = r10 + r15
            int r1 = r5.length
            r12 = 1
            int r1 = r1 - r12
            r12 = 4
            r13 = 0
            int r14 = com.google.android.exoplayer2.util.Util.a((int) r12, (int) r13, (int) r1)
            r19 = r0
            int r0 = r5.length
            int r0 = r0 - r12
            int r0 = com.google.android.exoplayer2.util.Util.a((int) r0, (int) r13, (int) r1)
            r20 = r5[r13]
            int r1 = (r20 > r15 ? 1 : (r20 == r15 ? 0 : -1))
            if (r1 > 0) goto L_0x0330
            r12 = r5[r14]
            int r1 = (r15 > r12 ? 1 : (r15 == r12 ? 0 : -1))
            if (r1 >= 0) goto L_0x0330
            r0 = r5[r0]
            int r12 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1))
            if (r12 >= 0) goto L_0x0330
            int r0 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r0 > 0) goto L_0x0330
            r0 = 1
            goto L_0x0331
        L_0x0330:
            r0 = 0
        L_0x0331:
            if (r0 == 0) goto L_0x0392
            long r22 = r7 - r10
            long r24 = r15 - r20
            com.google.android.exoplayer2.Format r0 = r4.f
            int r0 = r0.x
            long r0 = (long) r0
            long r10 = r4.c
            r26 = r0
            r28 = r10
            long r0 = com.google.android.exoplayer2.util.Util.b((long) r24, (long) r26, (long) r28)
            com.google.android.exoplayer2.Format r10 = r4.f
            int r10 = r10.x
            long r10 = (long) r10
            long r12 = r4.c
            r24 = r10
            r26 = r12
            long r10 = com.google.android.exoplayer2.util.Util.b((long) r22, (long) r24, (long) r26)
            r12 = 0
            int r14 = (r0 > r12 ? 1 : (r0 == r12 ? 0 : -1))
            if (r14 != 0) goto L_0x035f
            int r14 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r14 == 0) goto L_0x0392
        L_0x035f:
            r12 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r14 = (r0 > r12 ? 1 : (r0 == r12 ? 0 : -1))
            if (r14 > 0) goto L_0x0392
            int r14 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r14 > 0) goto L_0x0392
            int r1 = (int) r0
            r0 = r38
            r0.a = r1
            int r1 = (int) r10
            r0.b = r1
            long r0 = r4.c
            com.google.android.exoplayer2.util.Util.a((long[]) r5, (long) r0)
            long[] r0 = r4.h
            r1 = 0
            r10 = r0[r1]
            r12 = 1000000(0xf4240, double:4.940656E-318)
            long r14 = r4.d
            long r7 = com.google.android.exoplayer2.util.Util.b((long) r10, (long) r12, (long) r14)
            com.google.android.exoplayer2.extractor.mp4.TrackSampleTable r10 = new com.google.android.exoplayer2.extractor.mp4.TrackSampleTable
            r0 = r10
            r1 = r36
            r4 = r6
            r6 = r9
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return r10
        L_0x0390:
            r19 = r0
        L_0x0392:
            long[] r0 = r4.h
            int r0 = r0.length
            r1 = 1
            if (r0 != r1) goto L_0x03dc
            long[] r0 = r4.h
            r12 = 0
            r10 = r0[r12]
            r0 = 0
            int r13 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r13 != 0) goto L_0x03dd
            long[] r0 = r4.i
            java.lang.Object r0 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r0)
            long[] r0 = (long[]) r0
            r10 = r0[r12]
        L_0x03ad:
            int r0 = r5.length
            if (r12 >= r0) goto L_0x03c4
            r0 = r5[r12]
            long r13 = r0 - r10
            r15 = 1000000(0xf4240, double:4.940656E-318)
            long r0 = r4.c
            r17 = r0
            long r0 = com.google.android.exoplayer2.util.Util.b((long) r13, (long) r15, (long) r17)
            r5[r12] = r0
            int r12 = r12 + 1
            goto L_0x03ad
        L_0x03c4:
            long r13 = r7 - r10
            r15 = 1000000(0xf4240, double:4.940656E-318)
            long r0 = r4.c
            r17 = r0
            long r7 = com.google.android.exoplayer2.util.Util.b((long) r13, (long) r15, (long) r17)
            com.google.android.exoplayer2.extractor.mp4.TrackSampleTable r10 = new com.google.android.exoplayer2.extractor.mp4.TrackSampleTable
            r0 = r10
            r1 = r36
            r4 = r6
            r6 = r9
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return r10
        L_0x03dc:
            r12 = 0
        L_0x03dd:
            int r0 = r4.b
            r1 = 1
            if (r0 != r1) goto L_0x03e4
            r0 = 1
            goto L_0x03e5
        L_0x03e4:
            r0 = 0
        L_0x03e5:
            long[] r1 = r4.h
            int r1 = r1.length
            int[] r1 = new int[r1]
            long[] r7 = r4.h
            int r7 = r7.length
            int[] r7 = new int[r7]
            long[] r8 = r4.i
            java.lang.Object r8 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r8)
            long[] r8 = (long[]) r8
            r10 = 0
            r11 = 0
            r13 = 0
            r14 = 0
        L_0x03fb:
            long[] r15 = r4.h
            int r15 = r15.length
            if (r10 >= r15) goto L_0x0460
            r37 = r13
            r12 = r8[r10]
            r15 = -1
            int r20 = (r12 > r15 ? 1 : (r12 == r15 ? 0 : -1))
            if (r20 == 0) goto L_0x044e
            long[] r15 = r4.h
            r20 = r15[r10]
            r15 = r2
            r16 = r3
            long r2 = r4.c
            r26 = r14
            r38 = r15
            long r14 = r4.d
            r22 = r2
            r24 = r14
            long r2 = com.google.android.exoplayer2.util.Util.b((long) r20, (long) r22, (long) r24)
            r14 = 1
            int r15 = com.google.android.exoplayer2.util.Util.a((long[]) r5, (long) r12, (boolean) r14)
            r1[r10] = r15
            long r12 = r12 + r2
            int r2 = com.google.android.exoplayer2.util.Util.b((long[]) r5, (long) r12, (boolean) r0)
            r7[r10] = r2
        L_0x042f:
            r2 = r1[r10]
            r3 = r7[r10]
            if (r2 >= r3) goto L_0x043f
            r12 = r9[r2]
            r12 = r12 & r14
            if (r12 != 0) goto L_0x043f
            int r2 = r2 + 1
            r1[r10] = r2
            goto L_0x042f
        L_0x043f:
            int r12 = r3 - r2
            int r13 = r37 + r12
            r12 = r26
            if (r12 == r2) goto L_0x0449
            r12 = 1
            goto L_0x044a
        L_0x0449:
            r12 = 0
        L_0x044a:
            r2 = r11 | r12
            r11 = r2
            goto L_0x0457
        L_0x044e:
            r38 = r2
            r16 = r3
            r12 = r14
            r14 = 1
            r13 = r37
            r3 = r12
        L_0x0457:
            int r10 = r10 + 1
            r2 = r38
            r14 = r3
            r3 = r16
            r12 = 0
            goto L_0x03fb
        L_0x0460:
            r38 = r2
            r16 = r3
            r3 = r19
            r14 = 1
            if (r13 == r3) goto L_0x046a
            goto L_0x046b
        L_0x046a:
            r14 = 0
        L_0x046b:
            r0 = r11 | r14
            if (r0 == 0) goto L_0x0472
            long[] r2 = new long[r13]
            goto L_0x0474
        L_0x0472:
            r2 = r38
        L_0x0474:
            if (r0 == 0) goto L_0x0479
            int[] r3 = new int[r13]
            goto L_0x047b
        L_0x0479:
            r3 = r16
        L_0x047b:
            if (r0 == 0) goto L_0x047e
            r6 = 0
        L_0x047e:
            if (r0 == 0) goto L_0x0483
            int[] r8 = new int[r13]
            goto L_0x0484
        L_0x0483:
            r8 = r9
        L_0x0484:
            long[] r10 = new long[r13]
            r11 = 0
            r12 = 0
            r13 = 0
        L_0x048a:
            long[] r15 = r4.h
            int r15 = r15.length
            if (r11 >= r15) goto L_0x0522
            long[] r15 = r4.i
            r17 = r15[r11]
            r15 = r1[r11]
            r25 = r1
            r1 = r7[r11]
            if (r0 == 0) goto L_0x04af
            r26 = r7
            int r7 = r1 - r15
            r37 = r6
            r6 = r38
            java.lang.System.arraycopy(r6, r15, r2, r12, r7)
            r6 = r16
            java.lang.System.arraycopy(r6, r15, r3, r12, r7)
            java.lang.System.arraycopy(r9, r15, r8, r12, r7)
            goto L_0x04b5
        L_0x04af:
            r37 = r6
            r26 = r7
            r6 = r16
        L_0x04b5:
            r7 = r37
        L_0x04b7:
            if (r15 >= r1) goto L_0x0500
            r21 = 1000000(0xf4240, double:4.940656E-318)
            r27 = r8
            r16 = r9
            long r8 = r4.d
            r19 = r13
            r23 = r8
            long r8 = com.google.android.exoplayer2.util.Util.b((long) r19, (long) r21, (long) r23)
            r19 = r5[r15]
            r21 = r1
            r28 = r2
            long r1 = r19 - r17
            r19 = r13
            r13 = 0
            long r29 = java.lang.Math.max(r13, r1)
            r31 = 1000000(0xf4240, double:4.940656E-318)
            long r1 = r4.c
            r33 = r1
            long r1 = com.google.android.exoplayer2.util.Util.b((long) r29, (long) r31, (long) r33)
            long r8 = r8 + r1
            r10[r12] = r8
            if (r0 == 0) goto L_0x04f1
            r1 = r3[r12]
            if (r1 <= r7) goto L_0x04f1
            r1 = r6[r15]
            r7 = r1
        L_0x04f1:
            int r12 = r12 + 1
            int r15 = r15 + 1
            r9 = r16
            r13 = r19
            r1 = r21
            r8 = r27
            r2 = r28
            goto L_0x04b7
        L_0x0500:
            r28 = r2
            r27 = r8
            r16 = r9
            r19 = r13
            r13 = 0
            long[] r1 = r4.h
            r8 = r1[r11]
            long r1 = r19 + r8
            int r11 = r11 + 1
            r13 = r1
            r9 = r16
            r1 = r25
            r8 = r27
            r2 = r28
            r16 = r6
            r6 = r7
            r7 = r26
            goto L_0x048a
        L_0x0522:
            r28 = r2
            r37 = r6
            r27 = r8
            r19 = r13
            r21 = 1000000(0xf4240, double:4.940656E-318)
            long r0 = r4.d
            r23 = r0
            long r7 = com.google.android.exoplayer2.util.Util.b((long) r19, (long) r21, (long) r23)
            com.google.android.exoplayer2.extractor.mp4.TrackSampleTable r9 = new com.google.android.exoplayer2.extractor.mp4.TrackSampleTable
            r0 = r9
            r1 = r36
            r4 = r37
            r5 = r10
            r6 = r27
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return r9
        L_0x0543:
            com.google.android.exoplayer2.ParserException r0 = new com.google.android.exoplayer2.ParserException
            java.lang.String r1 = "Track has no sample table size information"
            r0.<init>((java.lang.String) r1)
            goto L_0x054c
        L_0x054b:
            throw r0
        L_0x054c:
            goto L_0x054b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp4.AtomParsers.a(com.google.android.exoplayer2.extractor.mp4.Track, com.google.android.exoplayer2.extractor.mp4.Atom$ContainerAtom, com.google.android.exoplayer2.extractor.GaplessInfoHolder):com.google.android.exoplayer2.extractor.mp4.TrackSampleTable");
    }

    public static Metadata a(Atom.ContainerAtom containerAtom) {
        Atom.LeafAtom d = containerAtom.d(1751411826);
        Atom.LeafAtom d2 = containerAtom.d(1801812339);
        Atom.LeafAtom d3 = containerAtom.d(1768715124);
        if (d == null || d2 == null || d3 == null || c(d.b) != 1835299937) {
            return null;
        }
        ParsableByteArray parsableByteArray = d2.b;
        parsableByteArray.d(12);
        int j = parsableByteArray.j();
        String[] strArr = new String[j];
        for (int i = 0; i < j; i++) {
            int j2 = parsableByteArray.j();
            parsableByteArray.e(4);
            strArr[i] = parsableByteArray.f(j2 - 8);
        }
        ParsableByteArray parsableByteArray2 = d3.b;
        parsableByteArray2.d(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray2.a() > 8) {
            int i2 = parsableByteArray2.b;
            int j3 = parsableByteArray2.j();
            int j4 = parsableByteArray2.j() - 1;
            if (j4 < 0 || j4 >= j) {
                Log.c("AtomParsers", new StringBuilder(52).append("Skipped metadata with unknown key index: ").append(j4).toString());
            } else {
                MdtaMetadataEntry a2 = MetadataUtil.a(parsableByteArray2, i2 + j3, strArr[j4]);
                if (a2 != null) {
                    arrayList.add(a2);
                }
            }
            parsableByteArray2.d(i2 + j3);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata((List) arrayList);
    }

    private static Metadata a(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.e(12);
        while (parsableByteArray.b < i) {
            int i2 = parsableByteArray.b;
            int j = parsableByteArray.j();
            if (parsableByteArray.j() != 1935766900) {
                parsableByteArray.d(i2 + j);
            } else if (j < 14) {
                return null;
            } else {
                parsableByteArray.e(5);
                int c = parsableByteArray.c();
                if (c != 12 && c != 13) {
                    return null;
                }
                float f = c == 12 ? 240.0f : 120.0f;
                parsableByteArray.e(1);
                return new Metadata(new SmtaMetadataEntry(f, parsableByteArray.c()));
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:58:0x01d1  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x01fc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List a(com.google.android.exoplayer2.extractor.mp4.Atom.ContainerAtom r32, com.google.android.exoplayer2.extractor.GaplessInfoHolder r33, long r34, com.google.android.exoplayer2.drm.DrmInitData r36, boolean r37, boolean r38, com.dreamers.exoplayercore.repack.aD r39) {
        /*
            r0 = r32
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
        L_0x0008:
            java.util.List r3 = r0.d
            int r3 = r3.size()
            if (r2 >= r3) goto L_0x0207
            java.util.List r3 = r0.d
            java.lang.Object r3 = r3.get(r2)
            com.google.android.exoplayer2.extractor.mp4.Atom$ContainerAtom r3 = (com.google.android.exoplayer2.extractor.mp4.Atom.ContainerAtom) r3
            int r4 = r3.a
            r5 = 1953653099(0x7472616b, float:7.681346E31)
            if (r4 != r5) goto L_0x01ff
            r4 = 1836476516(0x6d766864, float:4.7662196E27)
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r4 = r0.d(r4)
            java.lang.Object r4 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r4)
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r4 = (com.google.android.exoplayer2.extractor.mp4.Atom.LeafAtom) r4
            r5 = 1835297121(0x6d646961, float:4.4181236E27)
            com.google.android.exoplayer2.extractor.mp4.Atom$ContainerAtom r6 = r3.e(r5)
            java.lang.Object r6 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r6)
            com.google.android.exoplayer2.extractor.mp4.Atom$ContainerAtom r6 = (com.google.android.exoplayer2.extractor.mp4.Atom.ContainerAtom) r6
            r7 = 1751411826(0x68646c72, float:4.3148E24)
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r7 = r6.d(r7)
            java.lang.Object r7 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r7)
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r7 = (com.google.android.exoplayer2.extractor.mp4.Atom.LeafAtom) r7
            com.google.android.exoplayer2.util.ParsableByteArray r7 = r7.b
            int r7 = c(r7)
            r8 = 1936684398(0x736f756e, float:1.8971874E31)
            r9 = 3
            r10 = -1
            if (r7 != r8) goto L_0x0056
            r7 = 1
            r13 = 1
            goto L_0x007e
        L_0x0056:
            r8 = 1986618469(0x76696465, float:1.1834389E33)
            if (r7 != r8) goto L_0x005e
            r7 = 2
            r13 = 2
            goto L_0x007e
        L_0x005e:
            r8 = 1952807028(0x74657874, float:7.272211E31)
            if (r7 == r8) goto L_0x007d
            r8 = 1935832172(0x7362746c, float:1.7941596E31)
            if (r7 == r8) goto L_0x007d
            r8 = 1937072756(0x73756274, float:1.944137E31)
            if (r7 == r8) goto L_0x007d
            r8 = 1668047728(0x636c6370, float:4.3605968E21)
            if (r7 != r8) goto L_0x0073
            goto L_0x007d
        L_0x0073:
            r8 = 1835365473(0x6d657461, float:4.4382975E27)
            if (r7 != r8) goto L_0x007b
            r7 = 5
            r13 = 5
            goto L_0x007e
        L_0x007b:
            r13 = -1
            goto L_0x007e
        L_0x007d:
            r13 = 3
        L_0x007e:
            r7 = 1937007212(0x7374626c, float:1.9362132E31)
            r8 = 1835626086(0x6d696e66, float:4.515217E27)
            if (r13 != r10) goto L_0x008b
        L_0x0086:
            r4 = r39
            r11 = 0
            goto L_0x01c9
        L_0x008b:
            r10 = 1953196132(0x746b6864, float:7.46037E31)
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r10 = r3.d(r10)
            java.lang.Object r10 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r10)
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r10 = (com.google.android.exoplayer2.extractor.mp4.Atom.LeafAtom) r10
            com.google.android.exoplayer2.util.ParsableByteArray r10 = r10.b
            com.google.android.exoplayer2.extractor.mp4.AtomParsers$TkhdData r10 = b((com.google.android.exoplayer2.util.ParsableByteArray) r10)
            r14 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r12 = (r34 > r14 ? 1 : (r34 == r14 ? 0 : -1))
            if (r12 != 0) goto L_0x00ae
            long r16 = r10.b
            r18 = r16
            goto L_0x00b0
        L_0x00ae:
            r18 = r34
        L_0x00b0:
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r4.b
            r12 = 8
            r4.d(r12)
            int r16 = r4.j()
            int r16 = com.google.android.exoplayer2.extractor.mp4.Atom.a(r16)
            r17 = 16
            if (r16 != 0) goto L_0x00c6
            r11 = 8
            goto L_0x00c8
        L_0x00c6:
            r11 = 16
        L_0x00c8:
            r4.e(r11)
            long r24 = r4.h()
            int r4 = (r18 > r14 ? 1 : (r18 == r14 ? 0 : -1))
            if (r4 != 0) goto L_0x00d4
            goto L_0x00dd
        L_0x00d4:
            r20 = 1000000(0xf4240, double:4.940656E-318)
            r22 = r24
            long r14 = com.google.android.exoplayer2.util.Util.b((long) r18, (long) r20, (long) r22)
        L_0x00dd:
            r18 = r14
            com.google.android.exoplayer2.extractor.mp4.Atom$ContainerAtom r4 = r6.e(r8)
            java.lang.Object r4 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r4)
            com.google.android.exoplayer2.extractor.mp4.Atom$ContainerAtom r4 = (com.google.android.exoplayer2.extractor.mp4.Atom.ContainerAtom) r4
            com.google.android.exoplayer2.extractor.mp4.Atom$ContainerAtom r4 = r4.e(r7)
            java.lang.Object r4 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r4)
            com.google.android.exoplayer2.extractor.mp4.Atom$ContainerAtom r4 = (com.google.android.exoplayer2.extractor.mp4.Atom.ContainerAtom) r4
            r11 = 1835296868(0x6d646864, float:4.418049E27)
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r6 = r6.d(r11)
            java.lang.Object r6 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r6)
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r6 = (com.google.android.exoplayer2.extractor.mp4.Atom.LeafAtom) r6
            com.google.android.exoplayer2.util.ParsableByteArray r6 = r6.b
            r6.d(r12)
            int r11 = r6.j()
            int r11 = com.google.android.exoplayer2.extractor.mp4.Atom.a(r11)
            if (r11 != 0) goto L_0x0112
            r14 = 8
            goto L_0x0114
        L_0x0112:
            r14 = 16
        L_0x0114:
            r6.e(r14)
            long r14 = r6.h()
            if (r11 != 0) goto L_0x011e
            r12 = 4
        L_0x011e:
            r6.e(r12)
            int r6 = r6.d()
            int r11 = r6 >> 10
            r11 = r11 & 31
            int r11 = r11 + 96
            char r11 = (char) r11
            int r12 = r6 >> 5
            r12 = r12 & 31
            int r12 = r12 + 96
            char r12 = (char) r12
            r6 = r6 & 31
            int r6 = r6 + 96
            char r6 = (char) r6
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>(r9)
            java.lang.StringBuilder r7 = r7.append(r11)
            java.lang.StringBuilder r7 = r7.append(r12)
            java.lang.StringBuilder r6 = r7.append(r6)
            java.lang.String r6 = r6.toString()
            java.lang.Long r7 = java.lang.Long.valueOf(r14)
            android.util.Pair r6 = android.util.Pair.create(r7, r6)
            r7 = 1937011556(0x73747364, float:1.9367383E31)
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r4 = r4.d(r7)
            java.lang.Object r4 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r4)
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r4 = (com.google.android.exoplayer2.extractor.mp4.Atom.LeafAtom) r4
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r4.b
            int r27 = r10.a
            int r28 = r10.c
            java.lang.Object r7 = r6.second
            r29 = r7
            java.lang.String r29 = (java.lang.String) r29
            r26 = r4
            r30 = r36
            r31 = r38
            com.google.android.exoplayer2.extractor.mp4.AtomParsers$StsdData r4 = a(r26, r27, r28, r29, r30, r31)
            if (r37 != 0) goto L_0x0196
            r7 = 1701082227(0x65647473, float:6.742798E22)
            com.google.android.exoplayer2.extractor.mp4.Atom$ContainerAtom r7 = r3.e(r7)
            if (r7 == 0) goto L_0x0196
            android.util.Pair r7 = b((com.google.android.exoplayer2.extractor.mp4.Atom.ContainerAtom) r7)
            if (r7 == 0) goto L_0x0196
            java.lang.Object r9 = r7.first
            long[] r9 = (long[]) r9
            java.lang.Object r7 = r7.second
            long[] r7 = (long[]) r7
            goto L_0x0198
        L_0x0196:
            r7 = 0
            r9 = 0
        L_0x0198:
            com.google.android.exoplayer2.Format r11 = r4.b
            if (r11 != 0) goto L_0x019e
            goto L_0x0086
        L_0x019e:
            com.google.android.exoplayer2.extractor.mp4.Track r26 = new com.google.android.exoplayer2.extractor.mp4.Track
            int r12 = r10.a
            java.lang.Object r6 = r6.first
            java.lang.Long r6 = (java.lang.Long) r6
            long r14 = r6.longValue()
            com.google.android.exoplayer2.Format r6 = r4.b
            int r10 = r4.d
            com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox[] r11 = r4.a
            int r4 = r4.c
            r22 = r11
            r11 = r26
            r16 = r24
            r20 = r6
            r21 = r10
            r23 = r4
            r24 = r9
            r25 = r7
            r11.<init>(r12, r13, r14, r16, r18, r20, r21, r22, r23, r24, r25)
            r4 = r39
        L_0x01c9:
            java.lang.Object r6 = r4.apply(r11)
            com.google.android.exoplayer2.extractor.mp4.Track r6 = (com.google.android.exoplayer2.extractor.mp4.Track) r6
            if (r6 == 0) goto L_0x01fc
            com.google.android.exoplayer2.extractor.mp4.Atom$ContainerAtom r3 = r3.e(r5)
            java.lang.Object r3 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r3)
            com.google.android.exoplayer2.extractor.mp4.Atom$ContainerAtom r3 = (com.google.android.exoplayer2.extractor.mp4.Atom.ContainerAtom) r3
            com.google.android.exoplayer2.extractor.mp4.Atom$ContainerAtom r3 = r3.e(r8)
            java.lang.Object r3 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r3)
            com.google.android.exoplayer2.extractor.mp4.Atom$ContainerAtom r3 = (com.google.android.exoplayer2.extractor.mp4.Atom.ContainerAtom) r3
            r5 = 1937007212(0x7374626c, float:1.9362132E31)
            com.google.android.exoplayer2.extractor.mp4.Atom$ContainerAtom r3 = r3.e(r5)
            java.lang.Object r3 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r3)
            com.google.android.exoplayer2.extractor.mp4.Atom$ContainerAtom r3 = (com.google.android.exoplayer2.extractor.mp4.Atom.ContainerAtom) r3
            r5 = r33
            com.google.android.exoplayer2.extractor.mp4.TrackSampleTable r3 = a((com.google.android.exoplayer2.extractor.mp4.Track) r6, (com.google.android.exoplayer2.extractor.mp4.Atom.ContainerAtom) r3, (com.google.android.exoplayer2.extractor.GaplessInfoHolder) r5)
            r1.add(r3)
            goto L_0x0203
        L_0x01fc:
            r5 = r33
            goto L_0x0203
        L_0x01ff:
            r5 = r33
            r4 = r39
        L_0x0203:
            int r2 = r2 + 1
            goto L_0x0008
        L_0x0207:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp4.AtomParsers.a(com.google.android.exoplayer2.extractor.mp4.Atom$ContainerAtom, com.google.android.exoplayer2.extractor.GaplessInfoHolder, long, com.google.android.exoplayer2.drm.DrmInitData, boolean, boolean, com.dreamers.exoplayercore.repack.aD):java.util.List");
    }

    public static void a(ParsableByteArray parsableByteArray) {
        int i = parsableByteArray.b;
        parsableByteArray.e(4);
        if (parsableByteArray.j() != 1751411826) {
            i += 4;
        }
        parsableByteArray.d(i);
    }

    private static Pair b(Atom.ContainerAtom containerAtom) {
        Atom.LeafAtom d = containerAtom.d(1701606260);
        if (d == null) {
            return null;
        }
        ParsableByteArray parsableByteArray = d.b;
        parsableByteArray.d(8);
        int a2 = Atom.a(parsableByteArray.j());
        int o = parsableByteArray.o();
        long[] jArr = new long[o];
        long[] jArr2 = new long[o];
        int i = 0;
        while (i < o) {
            jArr[i] = a2 == 1 ? parsableByteArray.q() : parsableByteArray.h();
            jArr2[i] = a2 == 1 ? parsableByteArray.l() : (long) parsableByteArray.j();
            byte[] bArr = parsableByteArray.a;
            int i2 = parsableByteArray.b;
            parsableByteArray.b = i2 + 1;
            byte[] bArr2 = parsableByteArray.a;
            int i3 = parsableByteArray.b;
            parsableByteArray.b = i3 + 1;
            if (((short) (((bArr[i2] & Ev3Constants.Opcode.TST) << 8) | (bArr2[i3] & Ev3Constants.Opcode.TST))) == 1) {
                parsableByteArray.e(2);
                i++;
            } else {
                throw new IllegalArgumentException("Unsupported media rate.");
            }
        }
        return Pair.create(jArr, jArr2);
    }

    private static Pair b(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.d(i + 8 + 4);
        parsableByteArray.e(1);
        d(parsableByteArray);
        parsableByteArray.e(2);
        int c = parsableByteArray.c();
        if ((c & 128) != 0) {
            parsableByteArray.e(2);
        }
        if ((c & 64) != 0) {
            parsableByteArray.e(parsableByteArray.d());
        }
        if ((c & 32) != 0) {
            parsableByteArray.e(2);
        }
        parsableByteArray.e(1);
        d(parsableByteArray);
        String a2 = MimeTypes.a(parsableByteArray.c());
        if ("audio/mpeg".equals(a2) || "audio/vnd.dts".equals(a2) || "audio/vnd.dts.hd".equals(a2)) {
            return Pair.create(a2, (Object) null);
        }
        parsableByteArray.e(12);
        parsableByteArray.e(1);
        int d = d(parsableByteArray);
        byte[] bArr = new byte[d];
        parsableByteArray.a(bArr, 0, d);
        return Pair.create(a2, bArr);
    }

    private static Pair b(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        boolean z = false;
        String str = null;
        Integer num = null;
        int i4 = -1;
        int i5 = 0;
        while (i3 - i < i2) {
            parsableByteArray.d(i3);
            int j = parsableByteArray.j();
            int j2 = parsableByteArray.j();
            if (j2 == 1718775137) {
                num = Integer.valueOf(parsableByteArray.j());
            } else if (j2 == 1935894637) {
                parsableByteArray.e(4);
                str = parsableByteArray.f(4);
            } else if (j2 == 1935894633) {
                i4 = i3;
                i5 = j;
            }
            i3 += j;
        }
        if (!"cenc".equals(str) && !"cbc1".equals(str) && !"cens".equals(str) && !"cbcs".equals(str)) {
            return null;
        }
        Assertions.a((Object) num, (Object) "frma atom is mandatory");
        if (i4 != -1) {
            z = true;
        }
        Assertions.b(z, (Object) "schi atom is mandatory");
        return Pair.create(num, (TrackEncryptionBox) Assertions.a((Object) a(parsableByteArray, i4, i5, str), (Object) "tenc atom is mandatory"));
    }

    private static TkhdData b(ParsableByteArray parsableByteArray) {
        boolean z;
        int i = 8;
        parsableByteArray.d(8);
        int a2 = Atom.a(parsableByteArray.j());
        parsableByteArray.e(a2 == 0 ? 8 : 16);
        int j = parsableByteArray.j();
        parsableByteArray.e(4);
        int i2 = parsableByteArray.b;
        if (a2 == 0) {
            i = 4;
        }
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i4 >= i) {
                z = true;
                break;
            } else if (parsableByteArray.a[i2 + i4] != -1) {
                z = false;
                break;
            } else {
                i4++;
            }
        }
        long j2 = -9223372036854775807L;
        if (z) {
            parsableByteArray.e(i);
        } else {
            long h = a2 == 0 ? parsableByteArray.h() : parsableByteArray.q();
            if (h != 0) {
                j2 = h;
            }
        }
        parsableByteArray.e(16);
        int j3 = parsableByteArray.j();
        int j4 = parsableByteArray.j();
        parsableByteArray.e(4);
        int j5 = parsableByteArray.j();
        int j6 = parsableByteArray.j();
        if (j3 == 0 && j4 == 65536 && j5 == -65536 && j6 == 0) {
            i3 = 90;
        } else if (j3 == 0 && j4 == -65536 && j5 == 65536 && j6 == 0) {
            i3 = 270;
        } else if (j3 == -65536 && j4 == 0 && j5 == 0 && j6 == -65536) {
            i3 = 180;
        }
        return new TkhdData(j, j2, i3);
    }

    private static int c(ParsableByteArray parsableByteArray) {
        parsableByteArray.d(16);
        return parsableByteArray.j();
    }

    private static int d(ParsableByteArray parsableByteArray) {
        int c = parsableByteArray.c();
        int i = c & 127;
        while ((c & 128) == 128) {
            c = parsableByteArray.c();
            i = (i << 7) | (c & 127);
        }
        return i;
    }
}
