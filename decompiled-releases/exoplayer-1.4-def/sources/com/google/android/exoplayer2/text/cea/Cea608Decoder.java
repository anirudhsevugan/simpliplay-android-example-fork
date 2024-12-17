package com.google.android.exoplayer2.text.cea;

import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import androidx.core.location.LocationRequestCompat;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleInputBuffer;
import com.google.android.exoplayer2.text.SubtitleOutputBuffer;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.appinventor.components.common.YaVersion;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kawa.Telnet;

public final class Cea608Decoder extends CeaDecoder {
    private static final int[] b = {11, 1, 3, 12, 14, 5, 7, 9};
    private static final int[] c = {0, 4, 8, 12, 16, 20, 24, 28};
    /* access modifiers changed from: private */
    public static final int[] d = {-1, Component.COLOR_GREEN, Component.COLOR_BLUE, Component.COLOR_CYAN, -65536, -256, Component.COLOR_MAGENTA};
    private static final int[] e = {32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 225, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 233, 93, 237, 243, 250, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, YaVersion.YOUNG_ANDROID_VERSION, 247, 209, LispEscapeFormat.ESCAPE_NORMAL, 9632};
    private static final int[] f = {174, 176, FullScreenVideoUtil.FULLSCREEN_VIDEO_DIALOG_FLAG, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PLAY, 8482, 162, 163, 9834, 224, 32, 232, 226, 234, 238, 244, Telnet.WILL};
    private static final int[] g = {FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_STOP, ErrorMessages.ERROR_CAMERA_NO_IMAGE_RETURNED, 211, 218, 220, Telnet.WONT, 8216, 161, 42, 39, 8212, 169, 8480, 8226, 8220, 8221, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SOURCE, 199, 200, ErrorMessages.ERROR_NO_CAMERA_PERMISSION, 203, 235, 206, 207, 239, 212, 217, 249, 219, 171, 187};
    private static final int[] h = {FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_FULLSCREEN, 227, 205, 204, 236, 210, LispEscapeFormat.ESCAPE_ALL, 213, 245, 123, 125, 92, 94, 95, 124, 126, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_DURATION, 228, 214, 246, 223, 165, 164, 9474, 197, 229, 216, 248, 9484, 9488, 9492, 9496};
    private static final boolean[] i = {false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false};
    private long A;
    private final ParsableByteArray j = new ParsableByteArray();
    private final int k;
    private final int l;
    private final int m;
    private final long n = 16000000;
    private final ArrayList o = new ArrayList();
    private CueBuilder p = new CueBuilder(0, 4);
    private List q;
    private List r;
    private int s;
    private int t;
    private boolean u;
    private boolean v;
    private byte w;
    private byte x;
    private int y = 0;
    private boolean z;

    final class CueBuilder {
        final List a = new ArrayList();
        final List b = new ArrayList();
        final StringBuilder c = new StringBuilder();
        /* access modifiers changed from: package-private */
        public int d;
        int e;
        int f;
        /* access modifiers changed from: private */
        public int g;
        /* access modifiers changed from: private */
        public int h;

        class CueStyle {
            public final int a;
            public final boolean b;
            public int c;

            public CueStyle(int i, boolean z, int i2) {
                this.a = i;
                this.b = z;
                this.c = i2;
            }
        }

        public CueBuilder(int i, int i2) {
            a(i);
            this.f = i2;
        }

        private static void a(SpannableStringBuilder spannableStringBuilder, int i, int i2) {
            spannableStringBuilder.setSpan(new UnderlineSpan(), i, i2, 33);
        }

        private static void a(SpannableStringBuilder spannableStringBuilder, int i, int i2, int i3) {
            if (i3 != -1) {
                spannableStringBuilder.setSpan(new ForegroundColorSpan(i3), i, i2, 33);
            }
        }

        private static void b(SpannableStringBuilder spannableStringBuilder, int i, int i2) {
            spannableStringBuilder.setSpan(new StyleSpan(2), i, i2, 33);
        }

        public final void a(char c2) {
            if (this.c.length() < 32) {
                this.c.append(c2);
            }
        }

        public final void a(int i) {
            this.e = i;
            this.a.clear();
            this.b.clear();
            this.c.setLength(0);
            this.d = 15;
            this.g = 0;
            this.h = 0;
        }

        public final void a(int i, boolean z) {
            this.a.add(new CueStyle(i, z, this.c.length()));
        }

        public final boolean a() {
            return this.a.isEmpty() && this.b.isEmpty() && this.c.length() == 0;
        }

        public final Cue b(int i) {
            float f2;
            int i2 = this.g + this.h;
            int i3 = 32 - i2;
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            for (int i4 = 0; i4 < this.b.size(); i4++) {
                spannableStringBuilder.append(Util.a((CharSequence) this.b.get(i4), i3));
                spannableStringBuilder.append(10);
            }
            spannableStringBuilder.append(Util.a((CharSequence) c(), i3));
            if (spannableStringBuilder.length() == 0) {
                return null;
            }
            int length = i3 - spannableStringBuilder.length();
            int i5 = i2 - length;
            if (i == Integer.MIN_VALUE) {
                i = (this.e != 2 || (Math.abs(i5) >= 3 && length >= 0)) ? (this.e != 2 || i5 <= 0) ? 0 : 2 : 1;
            }
            switch (i) {
                case 1:
                    f2 = 0.5f;
                    break;
                case 2:
                    i2 = 32 - length;
                    break;
            }
            f2 = ((((float) i2) / 32.0f) * 0.8f) + 0.1f;
            int i6 = this.d;
            if (i6 > 7) {
                i6 = (i6 - 15) - 2;
            } else if (this.e == 1) {
                i6 -= this.f - 1;
            }
            Cue.Builder builder = new Cue.Builder();
            builder.a = spannableStringBuilder;
            builder.c = Layout.Alignment.ALIGN_NORMAL;
            Cue.Builder a2 = builder.a((float) i6, 1);
            a2.f = f2;
            a2.g = i;
            return a2.a();
        }

        public final void b() {
            int length = this.c.length();
            if (length > 0) {
                this.c.delete(length - 1, length);
                int size = this.a.size() - 1;
                while (size >= 0) {
                    CueStyle cueStyle = (CueStyle) this.a.get(size);
                    if (cueStyle.c == length) {
                        cueStyle.c--;
                        size--;
                    } else {
                        return;
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public final SpannableString c() {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.c);
            int length = spannableStringBuilder.length();
            int i = 0;
            int i2 = -1;
            int i3 = -1;
            int i4 = 0;
            int i5 = -1;
            int i6 = -1;
            boolean z = false;
            while (i < this.a.size()) {
                CueStyle cueStyle = (CueStyle) this.a.get(i);
                boolean z2 = cueStyle.b;
                int i7 = cueStyle.a;
                if (i7 != 8) {
                    boolean z3 = i7 == 7;
                    if (i7 != 7) {
                        i6 = Cea608Decoder.d[i7];
                    }
                    z = z3;
                }
                int i8 = cueStyle.c;
                i++;
                if (i8 != (i < this.a.size() ? ((CueStyle) this.a.get(i)).c : length)) {
                    if (i2 != -1 && !z2) {
                        a(spannableStringBuilder, i2, i8);
                        i2 = -1;
                    } else if (i2 == -1 && z2) {
                        i2 = i8;
                    }
                    if (i3 != -1 && !z) {
                        b(spannableStringBuilder, i3, i8);
                        i3 = -1;
                    } else if (i3 == -1 && z) {
                        i3 = i8;
                    }
                    if (i6 != i5) {
                        a(spannableStringBuilder, i4, i8, i5);
                        i5 = i6;
                        i4 = i8;
                    }
                }
            }
            if (!(i2 == -1 || i2 == length)) {
                a(spannableStringBuilder, i2, length);
            }
            if (!(i3 == -1 || i3 == length)) {
                b(spannableStringBuilder, i3, length);
            }
            if (i4 != length) {
                a(spannableStringBuilder, i4, length, i5);
            }
            return new SpannableString(spannableStringBuilder);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0045, code lost:
        r3.m = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0047, code lost:
        r3.l = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0049, code lost:
        a(0);
        l();
        r3.z = true;
        r3.A = -9223372036854775807L;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0058, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003f, code lost:
        r3.l = 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Cea608Decoder(java.lang.String r4, int r5) {
        /*
            r3 = this;
            r3.<init>()
            com.google.android.exoplayer2.util.ParsableByteArray r0 = new com.google.android.exoplayer2.util.ParsableByteArray
            r0.<init>()
            r3.j = r0
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r3.o = r0
            com.google.android.exoplayer2.text.cea.Cea608Decoder$CueBuilder r0 = new com.google.android.exoplayer2.text.cea.Cea608Decoder$CueBuilder
            r1 = 4
            r2 = 0
            r0.<init>(r2, r1)
            r3.p = r0
            r3.y = r2
            r0 = 16000000(0xf42400, double:7.9050503E-317)
            r3.n = r0
            java.lang.String r0 = "application/x-mp4-cea-608"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x002b
            r4 = 2
            goto L_0x002c
        L_0x002b:
            r4 = 3
        L_0x002c:
            r3.k = r4
            r4 = 1
            switch(r5) {
                case 1: goto L_0x0045;
                case 2: goto L_0x0042;
                case 3: goto L_0x003d;
                case 4: goto L_0x003a;
                default: goto L_0x0032;
            }
        L_0x0032:
            java.lang.String r5 = "Cea608Decoder"
            java.lang.String r0 = "Invalid channel. Defaulting to CC1."
            com.google.android.exoplayer2.util.Log.c(r5, r0)
            goto L_0x0045
        L_0x003a:
            r3.m = r4
            goto L_0x003f
        L_0x003d:
            r3.m = r2
        L_0x003f:
            r3.l = r4
            goto L_0x0049
        L_0x0042:
            r3.m = r4
            goto L_0x0047
        L_0x0045:
            r3.m = r2
        L_0x0047:
            r3.l = r2
        L_0x0049:
            r3.a((int) r2)
            r3.l()
            r3.z = r4
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r3.A = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.cea.Cea608Decoder.<init>(java.lang.String, int):void");
    }

    private static char a(byte b2) {
        return (char) e[(b2 & Ev3Constants.Opcode.MEMORY_READ) - 32];
    }

    private void a(int i2) {
        int i3 = this.s;
        if (i3 != i2) {
            this.s = i2;
            if (i2 == 3) {
                for (int i4 = 0; i4 < this.o.size(); i4++) {
                    ((CueBuilder) this.o.get(i4)).e = i2;
                }
                return;
            }
            l();
            if (i3 == 3 || i2 == 1 || i2 == 0) {
                this.q = Collections.emptyList();
            }
        }
    }

    private void b(int i2) {
        this.t = i2;
        this.p.f = i2;
    }

    private static boolean b(byte b2) {
        return (b2 & 224) == 0;
    }

    private List k() {
        int size = this.o.size();
        ArrayList arrayList = new ArrayList(size);
        int i2 = 2;
        for (int i3 = 0; i3 < size; i3++) {
            Cue b2 = ((CueBuilder) this.o.get(i3)).b(Integer.MIN_VALUE);
            arrayList.add(b2);
            if (b2 != null) {
                i2 = Math.min(i2, b2.j);
            }
        }
        ArrayList arrayList2 = new ArrayList(size);
        for (int i4 = 0; i4 < size; i4++) {
            Cue cue = (Cue) arrayList.get(i4);
            if (cue != null) {
                if (cue.j != i2) {
                    cue = (Cue) Assertions.b((Object) ((CueBuilder) this.o.get(i4)).b(i2));
                }
                arrayList2.add(cue);
            }
        }
        return arrayList2;
    }

    private void l() {
        this.p.a(this.s);
        this.o.clear();
        this.o.add(this.p);
    }

    public final /* bridge */ /* synthetic */ void a(long j2) {
        super.a(j2);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x024b, code lost:
        if (r0 != 3) goto L_0x0095;
     */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x0017 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x0017 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00ba  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.google.android.exoplayer2.text.SubtitleInputBuffer r14) {
        /*
            r13 = this;
            java.nio.ByteBuffer r14 = r14.c
            java.lang.Object r14 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r14)
            java.nio.ByteBuffer r14 = (java.nio.ByteBuffer) r14
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r13.j
            byte[] r1 = r14.array()
            int r14 = r14.limit()
            r0.a((byte[]) r1, (int) r14)
            r14 = 0
            r0 = 0
        L_0x0017:
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r13.j
            int r1 = r1.a()
            int r2 = r13.k
            r3 = 3
            r4 = 1
            if (r1 < r2) goto L_0x026e
            r1 = 2
            if (r2 != r1) goto L_0x0028
            r2 = -4
            goto L_0x002f
        L_0x0028:
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r13.j
            int r2 = r2.c()
            byte r2 = (byte) r2
        L_0x002f:
            com.google.android.exoplayer2.util.ParsableByteArray r5 = r13.j
            int r5 = r5.c()
            com.google.android.exoplayer2.util.ParsableByteArray r6 = r13.j
            int r6 = r6.c()
            r7 = r2 & 2
            if (r7 != 0) goto L_0x0017
            r7 = r2 & 1
            int r8 = r13.l
            if (r7 != r8) goto L_0x0017
            r7 = r5 & 127(0x7f, float:1.78E-43)
            byte r7 = (byte) r7
            r8 = r6 & 127(0x7f, float:1.78E-43)
            byte r8 = (byte) r8
            if (r7 != 0) goto L_0x004f
            if (r8 == 0) goto L_0x0017
        L_0x004f:
            boolean r9 = r13.u
            r2 = r2 & 4
            r10 = 4
            if (r2 != r10) goto L_0x0062
            boolean[] r2 = i
            boolean r5 = r2[r5]
            if (r5 == 0) goto L_0x0062
            boolean r2 = r2[r6]
            if (r2 == 0) goto L_0x0062
            r2 = 1
            goto L_0x0063
        L_0x0062:
            r2 = 0
        L_0x0063:
            r13.u = r2
            r5 = 16
            if (r2 == 0) goto L_0x0089
            r6 = r7 & 240(0xf0, float:3.36E-43)
            if (r6 != r5) goto L_0x006f
            r6 = 1
            goto L_0x0070
        L_0x006f:
            r6 = 0
        L_0x0070:
            if (r6 == 0) goto L_0x0089
            boolean r6 = r13.v
            if (r6 == 0) goto L_0x0082
            byte r6 = r13.w
            if (r6 != r7) goto L_0x0082
            byte r6 = r13.x
            if (r6 != r8) goto L_0x0082
            r13.v = r14
            r6 = 1
            goto L_0x008c
        L_0x0082:
            r13.v = r4
            r13.w = r7
            r13.x = r8
            goto L_0x008b
        L_0x0089:
            r13.v = r14
        L_0x008b:
            r6 = 0
        L_0x008c:
            if (r6 != 0) goto L_0x0017
            if (r2 != 0) goto L_0x0097
            if (r9 == 0) goto L_0x0017
        L_0x0092:
            r13.l()
        L_0x0095:
            r0 = 1
            goto L_0x0017
        L_0x0097:
            if (r7 <= 0) goto L_0x009f
            r2 = 15
            if (r7 > r2) goto L_0x009f
            r2 = 1
            goto L_0x00a0
        L_0x009f:
            r2 = 0
        L_0x00a0:
            r6 = 20
            if (r2 == 0) goto L_0x00a7
        L_0x00a4:
            r13.z = r14
            goto L_0x00b6
        L_0x00a7:
            r2 = r7 & 247(0xf7, float:3.46E-43)
            if (r2 != r6) goto L_0x00ad
            r2 = 1
            goto L_0x00ae
        L_0x00ad:
            r2 = 0
        L_0x00ae:
            if (r2 == 0) goto L_0x00b6
            switch(r8) {
                case 32: goto L_0x00b4;
                case 37: goto L_0x00b4;
                case 38: goto L_0x00b4;
                case 39: goto L_0x00b4;
                case 41: goto L_0x00b4;
                case 42: goto L_0x00a4;
                case 43: goto L_0x00a4;
                case 47: goto L_0x00b4;
                default: goto L_0x00b3;
            }
        L_0x00b3:
            goto L_0x00b6
        L_0x00b4:
            r13.z = r4
        L_0x00b6:
            boolean r2 = r13.z
            if (r2 == 0) goto L_0x0017
            boolean r2 = b((byte) r7)
            if (r2 == 0) goto L_0x00c5
            int r2 = r7 >> 3
            r2 = r2 & r4
            r13.y = r2
        L_0x00c5:
            int r2 = r13.y
            int r9 = r13.m
            if (r2 != r9) goto L_0x00cd
            r2 = 1
            goto L_0x00ce
        L_0x00cd:
            r2 = 0
        L_0x00ce:
            if (r2 == 0) goto L_0x0017
            boolean r0 = b((byte) r7)
            if (r0 == 0) goto L_0x0256
            r0 = r7 & 247(0xf7, float:3.46E-43)
            r2 = 17
            if (r0 != r2) goto L_0x00e4
            r9 = r8 & 240(0xf0, float:3.36E-43)
            r11 = 48
            if (r9 != r11) goto L_0x00e4
            r9 = 1
            goto L_0x00e5
        L_0x00e4:
            r9 = 0
        L_0x00e5:
            if (r9 == 0) goto L_0x00f2
            com.google.android.exoplayer2.text.cea.Cea608Decoder$CueBuilder r0 = r13.p
            r1 = r8 & 15
            int[] r2 = f
            r1 = r2[r1]
        L_0x00ef:
            char r1 = (char) r1
            goto L_0x0269
        L_0x00f2:
            r9 = r7 & 246(0xf6, float:3.45E-43)
            r11 = 18
            r12 = 32
            if (r9 != r11) goto L_0x0100
            r11 = r8 & 224(0xe0, float:3.14E-43)
            if (r11 != r12) goto L_0x0100
            r11 = 1
            goto L_0x0101
        L_0x0100:
            r11 = 0
        L_0x0101:
            if (r11 == 0) goto L_0x011c
            com.google.android.exoplayer2.text.cea.Cea608Decoder$CueBuilder r0 = r13.p
            r0.b()
            com.google.android.exoplayer2.text.cea.Cea608Decoder$CueBuilder r0 = r13.p
            r1 = r7 & 1
            if (r1 != 0) goto L_0x0115
            r1 = r8 & 31
            int[] r2 = g
            r1 = r2[r1]
            goto L_0x00ef
        L_0x0115:
            r1 = r8 & 31
            int[] r2 = h
            r1 = r2[r1]
            goto L_0x00ef
        L_0x011c:
            if (r0 != r2) goto L_0x0124
            r2 = r8 & 240(0xf0, float:3.36E-43)
            if (r2 != r12) goto L_0x0124
            r2 = 1
            goto L_0x0125
        L_0x0124:
            r2 = 0
        L_0x0125:
            if (r2 == 0) goto L_0x013e
            com.google.android.exoplayer2.text.cea.Cea608Decoder$CueBuilder r0 = r13.p
            r0.a((char) r12)
            r0 = r8 & 1
            if (r0 != r4) goto L_0x0132
            r0 = 1
            goto L_0x0133
        L_0x0132:
            r0 = 0
        L_0x0133:
            int r1 = r8 >> 1
            r1 = r1 & 7
            com.google.android.exoplayer2.text.cea.Cea608Decoder$CueBuilder r2 = r13.p
            r2.a((int) r1, (boolean) r0)
            goto L_0x0095
        L_0x013e:
            r2 = r7 & 240(0xf0, float:3.36E-43)
            if (r2 != r5) goto L_0x014a
            r2 = r8 & 192(0xc0, float:2.69E-43)
            r11 = 64
            if (r2 != r11) goto L_0x014a
            r2 = 1
            goto L_0x014b
        L_0x014a:
            r2 = 0
        L_0x014b:
            if (r2 == 0) goto L_0x01b1
            int[] r0 = b
            r1 = r7 & 7
            r0 = r0[r1]
            r1 = r8 & 32
            if (r1 == 0) goto L_0x0159
            r1 = 1
            goto L_0x015a
        L_0x0159:
            r1 = 0
        L_0x015a:
            if (r1 == 0) goto L_0x015e
            int r0 = r0 + 1
        L_0x015e:
            com.google.android.exoplayer2.text.cea.Cea608Decoder$CueBuilder r1 = r13.p
            int r1 = r1.d
            if (r0 == r1) goto L_0x0187
            int r1 = r13.s
            if (r1 == r4) goto L_0x0182
            com.google.android.exoplayer2.text.cea.Cea608Decoder$CueBuilder r1 = r13.p
            boolean r1 = r1.a()
            if (r1 != 0) goto L_0x0182
            com.google.android.exoplayer2.text.cea.Cea608Decoder$CueBuilder r1 = new com.google.android.exoplayer2.text.cea.Cea608Decoder$CueBuilder
            int r2 = r13.s
            int r3 = r13.t
            r1.<init>(r2, r3)
            r13.p = r1
            java.util.ArrayList r2 = r13.o
            r2.add(r1)
        L_0x0182:
            com.google.android.exoplayer2.text.cea.Cea608Decoder$CueBuilder r1 = r13.p
            int unused = r1.d = r0
        L_0x0187:
            r0 = r8 & 16
            if (r0 != r5) goto L_0x018d
            r0 = 1
            goto L_0x018e
        L_0x018d:
            r0 = 0
        L_0x018e:
            r1 = r8 & 1
            if (r1 != r4) goto L_0x0194
            r1 = 1
            goto L_0x0195
        L_0x0194:
            r1 = 0
        L_0x0195:
            int r2 = r8 >> 1
            r2 = r2 & 7
            com.google.android.exoplayer2.text.cea.Cea608Decoder$CueBuilder r3 = r13.p
            if (r0 == 0) goto L_0x01a0
            r5 = 8
            goto L_0x01a1
        L_0x01a0:
            r5 = r2
        L_0x01a1:
            r3.a((int) r5, (boolean) r1)
            if (r0 == 0) goto L_0x0095
            com.google.android.exoplayer2.text.cea.Cea608Decoder$CueBuilder r0 = r13.p
            int[] r1 = c
            r1 = r1[r2]
            int unused = r0.g = r1
            goto L_0x0095
        L_0x01b1:
            r2 = 23
            if (r0 != r2) goto L_0x01bf
            r0 = 33
            if (r8 < r0) goto L_0x01bf
            r0 = 35
            if (r8 > r0) goto L_0x01bf
            r0 = 1
            goto L_0x01c0
        L_0x01bf:
            r0 = 0
        L_0x01c0:
            if (r0 == 0) goto L_0x01cb
            com.google.android.exoplayer2.text.cea.Cea608Decoder$CueBuilder r0 = r13.p
            int r8 = r8 + -32
            int unused = r0.h = r8
            goto L_0x0095
        L_0x01cb:
            if (r9 != r6) goto L_0x01d3
            r0 = r8 & 240(0xf0, float:3.36E-43)
            if (r0 != r12) goto L_0x01d3
            r0 = 1
            goto L_0x01d4
        L_0x01d3:
            r0 = 0
        L_0x01d4:
            if (r0 == 0) goto L_0x0095
            switch(r8) {
                case 32: goto L_0x01ff;
                case 37: goto L_0x01f7;
                case 38: goto L_0x01ef;
                case 39: goto L_0x01e7;
                case 41: goto L_0x01e2;
                default: goto L_0x01d9;
            }
        L_0x01d9:
            int r0 = r13.s
            if (r0 == 0) goto L_0x0095
            switch(r8) {
                case 33: goto L_0x024f;
                case 44: goto L_0x0241;
                case 45: goto L_0x020c;
                case 46: goto L_0x0092;
                case 47: goto L_0x0204;
                default: goto L_0x01e0;
            }
        L_0x01e0:
            goto L_0x0095
        L_0x01e2:
            r13.a((int) r3)
            goto L_0x0095
        L_0x01e7:
            r13.a((int) r4)
            r13.b((int) r10)
            goto L_0x0095
        L_0x01ef:
            r13.a((int) r4)
            r13.b((int) r3)
            goto L_0x0095
        L_0x01f7:
            r13.a((int) r4)
            r13.b((int) r1)
            goto L_0x0095
        L_0x01ff:
            r13.a((int) r1)
            goto L_0x0095
        L_0x0204:
            java.util.List r0 = r13.k()
            r13.q = r0
            goto L_0x0092
        L_0x020c:
            if (r0 != r4) goto L_0x0095
            com.google.android.exoplayer2.text.cea.Cea608Decoder$CueBuilder r0 = r13.p
            boolean r0 = r0.a()
            if (r0 != 0) goto L_0x0095
            com.google.android.exoplayer2.text.cea.Cea608Decoder$CueBuilder r0 = r13.p
            java.util.List r1 = r0.b
            android.text.SpannableString r2 = r0.c()
            r1.add(r2)
            java.lang.StringBuilder r1 = r0.c
            r1.setLength(r14)
            java.util.List r1 = r0.a
            r1.clear()
            int r1 = r0.f
            int r2 = r0.d
            int r1 = java.lang.Math.min(r1, r2)
        L_0x0233:
            java.util.List r2 = r0.b
            int r2 = r2.size()
            if (r2 < r1) goto L_0x0095
            java.util.List r2 = r0.b
            r2.remove(r14)
            goto L_0x0233
        L_0x0241:
            java.util.List r0 = java.util.Collections.emptyList()
            r13.q = r0
            int r0 = r13.s
            if (r0 == r4) goto L_0x0092
            if (r0 != r3) goto L_0x0095
            goto L_0x0092
        L_0x024f:
            com.google.android.exoplayer2.text.cea.Cea608Decoder$CueBuilder r0 = r13.p
            r0.b()
            goto L_0x0095
        L_0x0256:
            com.google.android.exoplayer2.text.cea.Cea608Decoder$CueBuilder r0 = r13.p
            char r1 = a((byte) r7)
            r0.a((char) r1)
            r0 = r8 & 224(0xe0, float:3.14E-43)
            if (r0 == 0) goto L_0x0095
            com.google.android.exoplayer2.text.cea.Cea608Decoder$CueBuilder r0 = r13.p
            char r1 = a((byte) r8)
        L_0x0269:
            r0.a((char) r1)
            goto L_0x0095
        L_0x026e:
            if (r0 == 0) goto L_0x0282
            int r14 = r13.s
            if (r14 == r4) goto L_0x0276
            if (r14 != r3) goto L_0x0282
        L_0x0276:
            java.util.List r14 = r13.k()
            r13.q = r14
            long r0 = r13.j()
            r13.A = r0
        L_0x0282:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.cea.Cea608Decoder.a(com.google.android.exoplayer2.text.SubtitleInputBuffer):void");
    }

    public final /* bridge */ /* synthetic */ void b(SubtitleInputBuffer subtitleInputBuffer) {
        super.a(subtitleInputBuffer);
    }

    public final void c() {
        super.c();
        this.q = null;
        this.r = null;
        a(0);
        b(4);
        l();
        this.u = false;
        this.v = false;
        this.w = 0;
        this.x = 0;
        this.y = 0;
        this.z = true;
        this.A = -9223372036854775807L;
    }

    public final void d() {
    }

    /* renamed from: e */
    public final SubtitleOutputBuffer b() {
        SubtitleOutputBuffer subtitleOutputBuffer;
        SubtitleOutputBuffer e2 = super.b();
        if (e2 != null) {
            return e2;
        }
        boolean z2 = false;
        if (!(this.n == -9223372036854775807L || this.A == -9223372036854775807L || j() - this.A < this.n)) {
            z2 = true;
        }
        if (!z2 || (subtitleOutputBuffer = (SubtitleOutputBuffer) this.a.pollFirst()) == null) {
            return null;
        }
        this.q = Collections.emptyList();
        this.A = -9223372036854775807L;
        SubtitleOutputBuffer subtitleOutputBuffer2 = subtitleOutputBuffer;
        subtitleOutputBuffer2.a(j(), g(), LocationRequestCompat.PASSIVE_INTERVAL);
        return subtitleOutputBuffer;
    }

    /* access modifiers changed from: protected */
    public final boolean f() {
        return this.q != this.r;
    }

    /* access modifiers changed from: protected */
    public final Subtitle g() {
        List list = this.q;
        this.r = list;
        return new CeaSubtitle((List) Assertions.b((Object) list));
    }

    public final /* bridge */ /* synthetic */ SubtitleInputBuffer h() {
        return super.a();
    }
}
