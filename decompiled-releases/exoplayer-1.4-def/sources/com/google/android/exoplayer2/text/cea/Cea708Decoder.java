package com.google.android.exoplayer2.text.cea;

import android.graphics.Color;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleInputBuffer;
import com.google.android.exoplayer2.text.SubtitleOutputBuffer;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class Cea708Decoder extends CeaDecoder {
    private final ParsableByteArray b = new ParsableByteArray();
    private final ParsableBitArray c = new ParsableBitArray();
    private int d = -1;
    private final int e;
    private final CueInfoBuilder[] f;
    private CueInfoBuilder g;
    private List h;
    private List i;
    private DtvCcPacket j;
    private int k;

    final class Cea708CueInfo {
        /* access modifiers changed from: private */
        public static final Comparator b = Cea708Decoder$Cea708CueInfo$$Lambda$0.a;
        public final Cue a;
        private int c;

        public Cea708CueInfo(CharSequence charSequence, Layout.Alignment alignment, float f, int i, float f2, int i2, boolean z, int i3, int i4) {
            Cue.Builder builder = new Cue.Builder();
            builder.a = charSequence;
            builder.c = alignment;
            Cue.Builder a2 = builder.a(f, 0);
            a2.e = i;
            a2.f = f2;
            a2.g = i2;
            a2.h = -3.4028235E38f;
            if (z) {
                a2.k = i3;
                a2.j = true;
            }
            this.a = a2.a();
            this.c = i4;
        }
    }

    final class CueInfoBuilder {
        public static final int a = a(2, 2, 2, 0);
        static final int[] b = {0, 0, 0, 0, 0, 2, 0};
        static final int[] c;
        static final int[] d;
        private static int r = a(0, 0, 0, 0);
        private static int s;
        private int A;
        private int B;
        final List e = new ArrayList();
        boolean f;
        boolean g;
        int h;
        boolean i;
        int j;
        int k;
        int l;
        int m;
        boolean n;
        int o;
        int p;
        int q;
        private final SpannableStringBuilder t = new SpannableStringBuilder();
        private int u;
        private int v;
        private int w;
        private int x;
        private int y;
        private int z;

        static {
            int a2 = a(0, 0, 0, 3);
            s = a2;
            int i2 = r;
            c = new int[]{i2, a2, i2, i2, a2, i2, i2};
            d = new int[]{i2, i2, i2, i2, i2, a2, a2};
        }

        public CueInfoBuilder() {
            b();
        }

        public static int a(int i2, int i3, int i4) {
            return a(i2, i3, i4, 0);
        }

        public static int a(int i2, int i3, int i4, int i5) {
            int i6;
            Assertions.a(i2, 4);
            Assertions.a(i3, 4);
            Assertions.a(i4, 4);
            Assertions.a(i5, 4);
            int i7 = 0;
            switch (i5) {
                case 2:
                    i6 = 127;
                    break;
                case 3:
                    i6 = 0;
                    break;
                default:
                    i6 = 255;
                    break;
            }
            int i8 = i2 > 1 ? 255 : 0;
            int i9 = i3 > 1 ? 255 : 0;
            if (i4 > 1) {
                i7 = 255;
            }
            return Color.argb(i6, i8, i9, i7);
        }

        private SpannableString f() {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.t);
            int length = spannableStringBuilder.length();
            if (length > 0) {
                if (this.w != -1) {
                    spannableStringBuilder.setSpan(new StyleSpan(2), this.w, length, 33);
                }
                if (this.x != -1) {
                    spannableStringBuilder.setSpan(new UnderlineSpan(), this.x, length, 33);
                }
                if (this.y != -1) {
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(this.z), this.y, length, 33);
                }
                if (this.A != -1) {
                    spannableStringBuilder.setSpan(new BackgroundColorSpan(this.B), this.A, length, 33);
                }
            }
            return new SpannableString(spannableStringBuilder);
        }

        public final void a(char c2) {
            if (c2 == 10) {
                this.e.add(f());
                this.t.clear();
                if (this.w != -1) {
                    this.w = 0;
                }
                if (this.x != -1) {
                    this.x = 0;
                }
                if (this.y != -1) {
                    this.y = 0;
                }
                if (this.A != -1) {
                    this.A = 0;
                }
                while (true) {
                    if ((this.n && this.e.size() >= this.m) || this.e.size() >= 15) {
                        this.e.remove(0);
                    } else {
                        return;
                    }
                }
            } else {
                this.t.append(c2);
            }
        }

        public final void a(int i2, int i3) {
            this.v = i2;
            this.u = i3;
        }

        public final void a(boolean z2, boolean z3) {
            if (this.w != -1) {
                if (!z2) {
                    this.t.setSpan(new StyleSpan(2), this.w, this.t.length(), 33);
                    this.w = -1;
                }
            } else if (z2) {
                this.w = this.t.length();
            }
            if (this.x != -1) {
                if (!z3) {
                    this.t.setSpan(new UnderlineSpan(), this.x, this.t.length(), 33);
                    this.x = -1;
                }
            } else if (z3) {
                this.x = this.t.length();
            }
        }

        public final boolean a() {
            if (this.f) {
                return this.e.isEmpty() && this.t.length() == 0;
            }
            return true;
        }

        public final void b() {
            c();
            this.f = false;
            this.g = false;
            this.h = 4;
            this.i = false;
            this.j = 0;
            this.k = 0;
            this.l = 0;
            this.m = 15;
            this.n = true;
            this.u = 0;
            this.o = 0;
            this.p = 0;
            int i2 = r;
            this.v = i2;
            this.z = a;
            this.B = i2;
        }

        public final void b(int i2, int i3) {
            if (!(this.y == -1 || this.z == i2)) {
                this.t.setSpan(new ForegroundColorSpan(this.z), this.y, this.t.length(), 33);
            }
            if (i2 != a) {
                this.y = this.t.length();
                this.z = i2;
            }
            if (!(this.A == -1 || this.B == i3)) {
                this.t.setSpan(new BackgroundColorSpan(this.B), this.A, this.t.length(), 33);
            }
            if (i3 != r) {
                this.A = this.t.length();
                this.B = i3;
            }
        }

        public final void c() {
            this.e.clear();
            this.t.clear();
            this.w = -1;
            this.x = -1;
            this.y = -1;
            this.A = -1;
            this.q = 0;
        }

        public final void d() {
            int length = this.t.length();
            if (length > 0) {
                this.t.delete(length - 1, length);
            }
        }

        public final Cea708CueInfo e() {
            Layout.Alignment alignment;
            float f2;
            float f3;
            if (a()) {
                return null;
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            for (int i2 = 0; i2 < this.e.size(); i2++) {
                spannableStringBuilder.append((CharSequence) this.e.get(i2));
                spannableStringBuilder.append(10);
            }
            spannableStringBuilder.append(f());
            switch (this.u) {
                case 0:
                case 3:
                    alignment = Layout.Alignment.ALIGN_NORMAL;
                    break;
                case 1:
                    alignment = Layout.Alignment.ALIGN_OPPOSITE;
                    break;
                case 2:
                    alignment = Layout.Alignment.ALIGN_CENTER;
                    break;
                default:
                    throw new IllegalArgumentException(new StringBuilder(43).append("Unexpected justification value: ").append(this.u).toString());
            }
            Layout.Alignment alignment2 = alignment;
            if (this.i) {
                f3 = ((float) this.k) / 99.0f;
                f2 = ((float) this.j) / 99.0f;
            } else {
                f3 = ((float) this.k) / 209.0f;
                f2 = ((float) this.j) / 74.0f;
            }
            float f4 = (f3 * 0.9f) + 0.05f;
            float f5 = (f2 * 0.9f) + 0.05f;
            int i3 = this.l;
            boolean z2 = true;
            int i4 = i3 / 3 == 0 ? 0 : i3 / 3 == 1 ? 1 : 2;
            int i5 = i3 % 3 == 0 ? 0 : i3 % 3 == 1 ? 1 : 2;
            if (this.v == r) {
                z2 = false;
            }
            return new Cea708CueInfo(spannableStringBuilder, alignment2, f5, i4, f4, i5, z2, this.v, this.h);
        }
    }

    final class DtvCcPacket {
        public final int a;
        public final int b;
        public final byte[] c;
        int d = 0;

        public DtvCcPacket(int i, int i2) {
            this.a = i;
            this.b = i2;
            this.c = new byte[((i2 * 2) - 1)];
        }
    }

    public Cea708Decoder(int i2, List list) {
        this.e = i2 == -1 ? 1 : i2;
        if (list != null) {
            CodecSpecificDataUtil.a(list);
        }
        this.f = new CueInfoBuilder[8];
        for (int i3 = 0; i3 < 8; i3++) {
            this.f[i3] = new CueInfoBuilder();
        }
        this.g = this.f[0];
    }

    private void a(int i2) {
        switch (i2) {
            case 0:
                return;
            case 3:
                this.h = l();
                return;
            case 8:
                this.g.d();
                return;
            case 12:
                m();
                return;
            case 13:
                this.g.a(10);
                return;
            case 14:
                return;
            default:
                if (i2 >= 17 && i2 <= 23) {
                    Log.c("Cea708Decoder", new StringBuilder(55).append("Currently unsupported COMMAND_EXT1 Command: ").append(i2).toString());
                    this.c.b(8);
                    return;
                } else if (i2 < 24 || i2 > 31) {
                    Log.c("Cea708Decoder", new StringBuilder(31).append("Invalid C0 command: ").append(i2).toString());
                    return;
                } else {
                    Log.c("Cea708Decoder", new StringBuilder(54).append("Currently unsupported COMMAND_P16 Command: ").append(i2).toString());
                    this.c.b(16);
                    return;
                }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0232, code lost:
        if (r8 > 8) goto L_0x0248;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x023a, code lost:
        if (r0.c.e() == false) goto L_0x0245;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x023c, code lost:
        r0.f[8 - r8].b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0245, code lost:
        r8 = r8 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0248, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0263, code lost:
        if (r8 > 8) goto L_0x027b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x026b, code lost:
        if (r0.c.e() == false) goto L_0x0277;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x026d, code lost:
        r0.f[8 - r8].g = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0278, code lost:
        r8 = r8 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x027b, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0293, code lost:
        if (r8 > 8) goto L_0x02a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x029b, code lost:
        if (r0.c.e() == false) goto L_0x02a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x029d, code lost:
        r0.f[8 - r8].c();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x02a6, code lost:
        r8 = r8 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x02a9, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(int r17) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = 16
            r3 = 6
            r5 = 4
            r6 = 3
            r7 = 8
            r8 = 1
            r9 = 2
            switch(r1) {
                case 128: goto L_0x02aa;
                case 129: goto L_0x02aa;
                case 130: goto L_0x02aa;
                case 131: goto L_0x02aa;
                case 132: goto L_0x02aa;
                case 133: goto L_0x02aa;
                case 134: goto L_0x02aa;
                case 135: goto L_0x02aa;
                case 136: goto L_0x0293;
                case 137: goto L_0x027c;
                case 138: goto L_0x0263;
                case 139: goto L_0x0249;
                case 140: goto L_0x0232;
                case 141: goto L_0x022c;
                case 142: goto L_0x022b;
                case 143: goto L_0x0228;
                case 144: goto L_0x01f1;
                case 145: goto L_0x018b;
                case 146: goto L_0x015c;
                case 147: goto L_0x0010;
                case 148: goto L_0x0010;
                case 149: goto L_0x0010;
                case 150: goto L_0x0010;
                case 151: goto L_0x00f3;
                case 152: goto L_0x002c;
                case 153: goto L_0x002c;
                case 154: goto L_0x002c;
                case 155: goto L_0x002c;
                case 156: goto L_0x002c;
                case 157: goto L_0x002c;
                case 158: goto L_0x002c;
                case 159: goto L_0x002c;
                default: goto L_0x0010;
            }
        L_0x0010:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r3 = 31
            r2.<init>(r3)
            java.lang.String r3 = "Invalid C1 command: "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "Cea708Decoder"
            com.google.android.exoplayer2.util.Log.c(r2, r1)
            goto L_0x02b8
        L_0x002c:
            int r1 = r1 + -152
            com.google.android.exoplayer2.text.cea.Cea708Decoder$CueInfoBuilder[] r2 = r0.f
            r2 = r2[r1]
            com.google.android.exoplayer2.util.ParsableBitArray r10 = r0.c
            r10.b(r9)
            com.google.android.exoplayer2.util.ParsableBitArray r10 = r0.c
            boolean r10 = r10.e()
            com.google.android.exoplayer2.util.ParsableBitArray r11 = r0.c
            boolean r11 = r11.e()
            com.google.android.exoplayer2.util.ParsableBitArray r12 = r0.c
            r12.e()
            com.google.android.exoplayer2.util.ParsableBitArray r12 = r0.c
            int r12 = r12.c(r6)
            com.google.android.exoplayer2.util.ParsableBitArray r13 = r0.c
            boolean r13 = r13.e()
            com.google.android.exoplayer2.util.ParsableBitArray r14 = r0.c
            r15 = 7
            int r14 = r14.c(r15)
            com.google.android.exoplayer2.util.ParsableBitArray r15 = r0.c
            int r7 = r15.c(r7)
            com.google.android.exoplayer2.util.ParsableBitArray r15 = r0.c
            int r15 = r15.c(r5)
            com.google.android.exoplayer2.util.ParsableBitArray r4 = r0.c
            int r4 = r4.c(r5)
            com.google.android.exoplayer2.util.ParsableBitArray r5 = r0.c
            r5.b(r9)
            com.google.android.exoplayer2.util.ParsableBitArray r5 = r0.c
            r5.c(r3)
            com.google.android.exoplayer2.util.ParsableBitArray r3 = r0.c
            r3.b(r9)
            com.google.android.exoplayer2.util.ParsableBitArray r3 = r0.c
            int r3 = r3.c(r6)
            com.google.android.exoplayer2.util.ParsableBitArray r5 = r0.c
            int r5 = r5.c(r6)
            r2.f = r8
            r2.g = r10
            r2.n = r11
            r2.h = r12
            r2.i = r13
            r2.j = r14
            r2.k = r7
            r2.l = r15
            int r6 = r2.m
            int r4 = r4 + r8
            if (r6 == r4) goto L_0x00bc
            r2.m = r4
        L_0x009f:
            if (r11 == 0) goto L_0x00ab
            java.util.List r4 = r2.e
            int r4 = r4.size()
            int r6 = r2.m
            if (r4 >= r6) goto L_0x00b5
        L_0x00ab:
            java.util.List r4 = r2.e
            int r4 = r4.size()
            r6 = 15
            if (r4 < r6) goto L_0x00bc
        L_0x00b5:
            java.util.List r4 = r2.e
            r6 = 0
            r4.remove(r6)
            goto L_0x009f
        L_0x00bc:
            if (r3 == 0) goto L_0x00d0
            int r4 = r2.o
            if (r4 == r3) goto L_0x00d0
            r2.o = r3
            int r3 = r3 - r8
            int[] r4 = com.google.android.exoplayer2.text.cea.Cea708Decoder.CueInfoBuilder.c
            r4 = r4[r3]
            int[] r6 = com.google.android.exoplayer2.text.cea.Cea708Decoder.CueInfoBuilder.b
            r3 = r6[r3]
            r2.a((int) r4, (int) r3)
        L_0x00d0:
            if (r5 == 0) goto L_0x00e6
            int r3 = r2.p
            if (r3 == r5) goto L_0x00e6
            r2.p = r5
            int r5 = r5 - r8
            r3 = 0
            r2.a((boolean) r3, (boolean) r3)
            int r3 = com.google.android.exoplayer2.text.cea.Cea708Decoder.CueInfoBuilder.a
            int[] r4 = com.google.android.exoplayer2.text.cea.Cea708Decoder.CueInfoBuilder.d
            r4 = r4[r5]
            r2.b(r3, r4)
        L_0x00e6:
            int r2 = r0.k
            if (r2 == r1) goto L_0x02b8
            r0.k = r1
            com.google.android.exoplayer2.text.cea.Cea708Decoder$CueInfoBuilder[] r2 = r0.f
            r1 = r2[r1]
            r0.g = r1
            return
        L_0x00f3:
            com.google.android.exoplayer2.text.cea.Cea708Decoder$CueInfoBuilder r1 = r0.g
            boolean r1 = r1.f
            if (r1 != 0) goto L_0x0101
            com.google.android.exoplayer2.util.ParsableBitArray r1 = r0.c
            r2 = 32
            r1.b(r2)
            return
        L_0x0101:
            com.google.android.exoplayer2.util.ParsableBitArray r1 = r0.c
            int r1 = r1.c(r9)
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r0.c
            int r2 = r2.c(r9)
            com.google.android.exoplayer2.util.ParsableBitArray r3 = r0.c
            int r3 = r3.c(r9)
            com.google.android.exoplayer2.util.ParsableBitArray r4 = r0.c
            int r4 = r4.c(r9)
            int r1 = com.google.android.exoplayer2.text.cea.Cea708Decoder.CueInfoBuilder.a(r2, r3, r4, r1)
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r0.c
            r2.c(r9)
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r0.c
            int r2 = r2.c(r9)
            com.google.android.exoplayer2.util.ParsableBitArray r3 = r0.c
            int r3 = r3.c(r9)
            com.google.android.exoplayer2.util.ParsableBitArray r4 = r0.c
            int r4 = r4.c(r9)
            com.google.android.exoplayer2.text.cea.Cea708Decoder.CueInfoBuilder.a(r2, r3, r4)
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r0.c
            r2.e()
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r0.c
            r2.e()
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r0.c
            r2.c(r9)
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r0.c
            r2.c(r9)
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r0.c
            int r2 = r2.c(r9)
            com.google.android.exoplayer2.util.ParsableBitArray r3 = r0.c
            r3.b(r7)
            com.google.android.exoplayer2.text.cea.Cea708Decoder$CueInfoBuilder r3 = r0.g
            r3.a((int) r1, (int) r2)
            return
        L_0x015c:
            com.google.android.exoplayer2.text.cea.Cea708Decoder$CueInfoBuilder r1 = r0.g
            boolean r1 = r1.f
            if (r1 != 0) goto L_0x0168
            com.google.android.exoplayer2.util.ParsableBitArray r1 = r0.c
            r1.b(r2)
            return
        L_0x0168:
            com.google.android.exoplayer2.util.ParsableBitArray r1 = r0.c
            r1.b(r5)
            com.google.android.exoplayer2.util.ParsableBitArray r1 = r0.c
            int r1 = r1.c(r5)
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r0.c
            r2.b(r9)
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r0.c
            r2.c(r3)
            com.google.android.exoplayer2.text.cea.Cea708Decoder$CueInfoBuilder r2 = r0.g
            int r3 = r2.q
            if (r3 == r1) goto L_0x0188
            r3 = 10
            r2.a(r3)
        L_0x0188:
            r2.q = r1
            return
        L_0x018b:
            com.google.android.exoplayer2.text.cea.Cea708Decoder$CueInfoBuilder r1 = r0.g
            boolean r1 = r1.f
            if (r1 != 0) goto L_0x0199
            com.google.android.exoplayer2.util.ParsableBitArray r1 = r0.c
            r2 = 24
            r1.b(r2)
            return
        L_0x0199:
            com.google.android.exoplayer2.util.ParsableBitArray r1 = r0.c
            int r1 = r1.c(r9)
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r0.c
            int r2 = r2.c(r9)
            com.google.android.exoplayer2.util.ParsableBitArray r3 = r0.c
            int r3 = r3.c(r9)
            com.google.android.exoplayer2.util.ParsableBitArray r4 = r0.c
            int r4 = r4.c(r9)
            int r1 = com.google.android.exoplayer2.text.cea.Cea708Decoder.CueInfoBuilder.a(r2, r3, r4, r1)
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r0.c
            int r2 = r2.c(r9)
            com.google.android.exoplayer2.util.ParsableBitArray r3 = r0.c
            int r3 = r3.c(r9)
            com.google.android.exoplayer2.util.ParsableBitArray r4 = r0.c
            int r4 = r4.c(r9)
            com.google.android.exoplayer2.util.ParsableBitArray r5 = r0.c
            int r5 = r5.c(r9)
            int r2 = com.google.android.exoplayer2.text.cea.Cea708Decoder.CueInfoBuilder.a(r3, r4, r5, r2)
            com.google.android.exoplayer2.util.ParsableBitArray r3 = r0.c
            r3.b(r9)
            com.google.android.exoplayer2.util.ParsableBitArray r3 = r0.c
            int r3 = r3.c(r9)
            com.google.android.exoplayer2.util.ParsableBitArray r4 = r0.c
            int r4 = r4.c(r9)
            com.google.android.exoplayer2.util.ParsableBitArray r5 = r0.c
            int r5 = r5.c(r9)
            com.google.android.exoplayer2.text.cea.Cea708Decoder.CueInfoBuilder.a(r3, r4, r5)
            com.google.android.exoplayer2.text.cea.Cea708Decoder$CueInfoBuilder r3 = r0.g
            r3.b(r1, r2)
            return
        L_0x01f1:
            com.google.android.exoplayer2.text.cea.Cea708Decoder$CueInfoBuilder r1 = r0.g
            boolean r1 = r1.f
            if (r1 != 0) goto L_0x01fd
            com.google.android.exoplayer2.util.ParsableBitArray r1 = r0.c
            r1.b(r2)
            return
        L_0x01fd:
            com.google.android.exoplayer2.util.ParsableBitArray r1 = r0.c
            r1.c(r5)
            com.google.android.exoplayer2.util.ParsableBitArray r1 = r0.c
            r1.c(r9)
            com.google.android.exoplayer2.util.ParsableBitArray r1 = r0.c
            r1.c(r9)
            com.google.android.exoplayer2.util.ParsableBitArray r1 = r0.c
            boolean r1 = r1.e()
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r0.c
            boolean r2 = r2.e()
            com.google.android.exoplayer2.util.ParsableBitArray r3 = r0.c
            r3.c(r6)
            com.google.android.exoplayer2.util.ParsableBitArray r3 = r0.c
            r3.c(r6)
            com.google.android.exoplayer2.text.cea.Cea708Decoder$CueInfoBuilder r3 = r0.g
            r3.a((boolean) r1, (boolean) r2)
            return
        L_0x0228:
            r16.m()
        L_0x022b:
            return
        L_0x022c:
            com.google.android.exoplayer2.util.ParsableBitArray r1 = r0.c
            r1.b(r7)
            return
        L_0x0232:
            if (r8 > r7) goto L_0x0248
            com.google.android.exoplayer2.util.ParsableBitArray r1 = r0.c
            boolean r1 = r1.e()
            if (r1 == 0) goto L_0x0245
            com.google.android.exoplayer2.text.cea.Cea708Decoder$CueInfoBuilder[] r1 = r0.f
            int r2 = 8 - r8
            r1 = r1[r2]
            r1.b()
        L_0x0245:
            int r8 = r8 + 1
            goto L_0x0232
        L_0x0248:
            return
        L_0x0249:
            r1 = 1
        L_0x024a:
            if (r1 > r7) goto L_0x0262
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r0.c
            boolean r2 = r2.e()
            if (r2 == 0) goto L_0x025f
            com.google.android.exoplayer2.text.cea.Cea708Decoder$CueInfoBuilder[] r2 = r0.f
            int r3 = 8 - r1
            r2 = r2[r3]
            boolean r3 = r2.g
            r3 = r3 ^ r8
            r2.g = r3
        L_0x025f:
            int r1 = r1 + 1
            goto L_0x024a
        L_0x0262:
            return
        L_0x0263:
            if (r8 > r7) goto L_0x027b
            com.google.android.exoplayer2.util.ParsableBitArray r1 = r0.c
            boolean r1 = r1.e()
            if (r1 == 0) goto L_0x0277
            com.google.android.exoplayer2.text.cea.Cea708Decoder$CueInfoBuilder[] r1 = r0.f
            int r2 = 8 - r8
            r1 = r1[r2]
            r2 = 0
            r1.g = r2
            goto L_0x0278
        L_0x0277:
            r2 = 0
        L_0x0278:
            int r8 = r8 + 1
            goto L_0x0263
        L_0x027b:
            return
        L_0x027c:
            r1 = 1
        L_0x027d:
            if (r1 > r7) goto L_0x0292
            com.google.android.exoplayer2.util.ParsableBitArray r2 = r0.c
            boolean r2 = r2.e()
            if (r2 == 0) goto L_0x028f
            com.google.android.exoplayer2.text.cea.Cea708Decoder$CueInfoBuilder[] r2 = r0.f
            int r3 = 8 - r1
            r2 = r2[r3]
            r2.g = r8
        L_0x028f:
            int r1 = r1 + 1
            goto L_0x027d
        L_0x0292:
            return
        L_0x0293:
            if (r8 > r7) goto L_0x02a9
            com.google.android.exoplayer2.util.ParsableBitArray r1 = r0.c
            boolean r1 = r1.e()
            if (r1 == 0) goto L_0x02a6
            com.google.android.exoplayer2.text.cea.Cea708Decoder$CueInfoBuilder[] r1 = r0.f
            int r2 = 8 - r8
            r1 = r1[r2]
            r1.c()
        L_0x02a6:
            int r8 = r8 + 1
            goto L_0x0293
        L_0x02a9:
            return
        L_0x02aa:
            int r1 = r1 + -128
            int r2 = r0.k
            if (r2 == r1) goto L_0x02b8
            r0.k = r1
            com.google.android.exoplayer2.text.cea.Cea708Decoder$CueInfoBuilder[] r2 = r0.f
            r1 = r2[r1]
            r0.g = r1
        L_0x02b8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.cea.Cea708Decoder.b(int):void");
    }

    private void c(int i2) {
        ParsableBitArray parsableBitArray;
        int i3;
        if (i2 > 7) {
            if (i2 <= 15) {
                parsableBitArray = this.c;
                i3 = 8;
            } else if (i2 <= 23) {
                parsableBitArray = this.c;
                i3 = 16;
            } else if (i2 <= 31) {
                this.c.b(24);
                return;
            } else {
                return;
            }
            parsableBitArray.b(i3);
        }
    }

    private void d(int i2) {
        ParsableBitArray parsableBitArray;
        int i3;
        if (i2 <= 135) {
            parsableBitArray = this.c;
            i3 = 32;
        } else if (i2 <= 143) {
            parsableBitArray = this.c;
            i3 = 40;
        } else if (i2 <= 159) {
            this.c.b(2);
            this.c.b(this.c.c(6) << 3);
            return;
        } else {
            return;
        }
        parsableBitArray.b(i3);
    }

    private void e(int i2) {
        if (i2 == 127) {
            this.g.a(9835);
        } else {
            this.g.a((char) (i2 & 255));
        }
    }

    private void f(int i2) {
        this.g.a((char) (i2 & 255));
    }

    private void g(int i2) {
        switch (i2) {
            case 32:
                this.g.a(' ');
                return;
            case 33:
                this.g.a(160);
                return;
            case 37:
                this.g.a(8230);
                return;
            case 42:
                this.g.a(352);
                return;
            case 44:
                this.g.a(338);
                return;
            case 48:
                this.g.a(9608);
                return;
            case 49:
                this.g.a(8216);
                return;
            case 50:
                this.g.a(8217);
                return;
            case 51:
                this.g.a(8220);
                return;
            case 52:
                this.g.a(8221);
                return;
            case 53:
                this.g.a(8226);
                return;
            case 57:
                this.g.a(8482);
                return;
            case 58:
                this.g.a(353);
                return;
            case 60:
                this.g.a(339);
                return;
            case 61:
                this.g.a(8480);
                return;
            case 63:
                this.g.a(376);
                return;
            case 118:
                this.g.a(8539);
                return;
            case 119:
                this.g.a(8540);
                return;
            case 120:
                this.g.a(8541);
                return;
            case 121:
                this.g.a(8542);
                return;
            case 122:
                this.g.a(9474);
                return;
            case 123:
                this.g.a(9488);
                return;
            case 124:
                this.g.a(9492);
                return;
            case 125:
                this.g.a(9472);
                return;
            case 126:
                this.g.a(9496);
                return;
            case 127:
                this.g.a(9484);
                return;
            default:
                Log.c("Cea708Decoder", new StringBuilder(33).append("Invalid G2 character: ").append(i2).toString());
                return;
        }
    }

    private void h(int i2) {
        CueInfoBuilder cueInfoBuilder;
        char c2;
        if (i2 == 160) {
            cueInfoBuilder = this.g;
            c2 = 13252;
        } else {
            Log.c("Cea708Decoder", new StringBuilder(33).append("Invalid G3 character: ").append(i2).toString());
            cueInfoBuilder = this.g;
            c2 = '_';
        }
        cueInfoBuilder.a(c2);
    }

    private void i() {
        if (this.j != null) {
            k();
            this.j = null;
        }
    }

    private void k() {
        String str;
        StringBuilder sb;
        if (this.j.d != (this.j.b << 1) - 1) {
            int i2 = this.j.d;
            Log.a("Cea708Decoder", new StringBuilder(115).append("DtvCcPacket ended prematurely; size is ").append((this.j.b << 1) - 1).append(", but current index is ").append(i2).append(" (sequence number ").append(this.j.a).append(");").toString());
        }
        this.c.a(this.j.c, this.j.d);
        int c2 = this.c.c(3);
        int c3 = this.c.c(5);
        if (c2 == 7) {
            this.c.b(2);
            c2 = this.c.c(6);
            if (c2 < 7) {
                Log.c("Cea708Decoder", new StringBuilder(44).append("Invalid extended service number: ").append(c2).toString());
            }
        }
        if (c3 == 0) {
            if (c2 != 0) {
                Log.c("Cea708Decoder", new StringBuilder(59).append("serviceNumber is non-zero (").append(c2).append(") when blockSize is 0").toString());
            }
        } else if (c2 == this.e) {
            boolean z = false;
            while (this.c.a() > 0) {
                int c4 = this.c.c(8);
                if (c4 == 16) {
                    c4 = this.c.c(8);
                    if (c4 <= 31) {
                        c(c4);
                    } else if (c4 <= 127) {
                        g(c4);
                    } else if (c4 <= 159) {
                        d(c4);
                    } else if (c4 <= 255) {
                        h(c4);
                    } else {
                        sb = new StringBuilder(37);
                        str = "Invalid extended command: ";
                        Log.c("Cea708Decoder", sb.append(str).append(c4).toString());
                    }
                } else if (c4 <= 31) {
                    a(c4);
                } else if (c4 <= 127) {
                    e(c4);
                } else if (c4 <= 159) {
                    b(c4);
                } else if (c4 <= 255) {
                    f(c4);
                } else {
                    sb = new StringBuilder(33);
                    str = "Invalid base command: ";
                    Log.c("Cea708Decoder", sb.append(str).append(c4).toString());
                }
                z = true;
            }
            if (z) {
                this.h = l();
            }
        }
    }

    private List l() {
        Cea708CueInfo e2;
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < 8; i2++) {
            if (!this.f[i2].a() && this.f[i2].g && (e2 = this.f[i2].e()) != null) {
                arrayList.add(e2);
            }
        }
        Collections.sort(arrayList, Cea708CueInfo.b);
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            arrayList2.add(((Cea708CueInfo) arrayList.get(i3)).a);
        }
        return Collections.unmodifiableList(arrayList2);
    }

    private void m() {
        for (int i2 = 0; i2 < 8; i2++) {
            this.f[i2].b();
        }
    }

    public final /* bridge */ /* synthetic */ void a(long j2) {
        super.a(j2);
    }

    /* access modifiers changed from: protected */
    public final void a(SubtitleInputBuffer subtitleInputBuffer) {
        ByteBuffer byteBuffer = (ByteBuffer) Assertions.b((Object) subtitleInputBuffer.c);
        this.b.a(byteBuffer.array(), byteBuffer.limit());
        while (this.b.a() >= 3) {
            int c2 = this.b.c() & 7;
            int i2 = c2 & 3;
            boolean z = false;
            boolean z2 = (c2 & 4) == 4;
            byte c3 = (byte) this.b.c();
            byte c4 = (byte) this.b.c();
            if ((i2 == 2 || i2 == 3) && z2) {
                if (i2 == 3) {
                    i();
                    int i3 = (c3 & Ev3Constants.Opcode.FILE) >> 6;
                    int i4 = this.d;
                    if (!(i4 == -1 || i3 == (i4 + 1) % 4)) {
                        m();
                        Log.c("Cea708Decoder", new StringBuilder(71).append("Sequence number discontinuity. previous=").append(this.d).append(" current=").append(i3).toString());
                    }
                    this.d = i3;
                    byte b2 = c3 & Ev3Constants.Opcode.MOVEF_F;
                    if (b2 == 0) {
                        b2 = Ev3Constants.Opcode.JR;
                    }
                    DtvCcPacket dtvCcPacket = new DtvCcPacket(i3, b2);
                    this.j = dtvCcPacket;
                    byte[] bArr = dtvCcPacket.c;
                    DtvCcPacket dtvCcPacket2 = this.j;
                    int i5 = dtvCcPacket2.d;
                    dtvCcPacket2.d = i5 + 1;
                    bArr[i5] = c4;
                } else {
                    if (i2 == 2) {
                        z = true;
                    }
                    Assertions.a(z);
                    DtvCcPacket dtvCcPacket3 = this.j;
                    if (dtvCcPacket3 == null) {
                        Log.d("Cea708Decoder", "Encountered DTVCC_PACKET_DATA before DTVCC_PACKET_START");
                    } else {
                        byte[] bArr2 = dtvCcPacket3.c;
                        DtvCcPacket dtvCcPacket4 = this.j;
                        int i6 = dtvCcPacket4.d;
                        dtvCcPacket4.d = i6 + 1;
                        bArr2[i6] = c3;
                        byte[] bArr3 = this.j.c;
                        DtvCcPacket dtvCcPacket5 = this.j;
                        int i7 = dtvCcPacket5.d;
                        dtvCcPacket5.d = i7 + 1;
                        bArr3[i7] = c4;
                    }
                }
                if (this.j.d == (this.j.b << 1) - 1) {
                    i();
                }
            }
        }
    }

    public final /* bridge */ /* synthetic */ void b(SubtitleInputBuffer subtitleInputBuffer) {
        super.a(subtitleInputBuffer);
    }

    public final void c() {
        super.c();
        this.h = null;
        this.i = null;
        this.k = 0;
        this.g = this.f[0];
        m();
        this.j = null;
    }

    public final /* bridge */ /* synthetic */ void d() {
        super.d();
    }

    public final /* bridge */ /* synthetic */ SubtitleOutputBuffer e() {
        return super.b();
    }

    /* access modifiers changed from: protected */
    public final boolean f() {
        return this.h != this.i;
    }

    /* access modifiers changed from: protected */
    public final Subtitle g() {
        List list = this.h;
        this.i = list;
        return new CeaSubtitle((List) Assertions.b((Object) list));
    }

    public final /* bridge */ /* synthetic */ SubtitleInputBuffer h() {
        return super.a();
    }
}
