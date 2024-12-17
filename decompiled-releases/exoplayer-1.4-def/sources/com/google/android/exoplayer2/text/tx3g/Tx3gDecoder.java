package com.google.android.exoplayer2.text.tx3g;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import com.dreamers.exoplayercore.repack.aC;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.util.List;

public final class Tx3gDecoder extends SimpleSubtitleDecoder {
    private final ParsableByteArray d = new ParsableByteArray();
    private final boolean e;
    private final int f;
    private final int g;
    private final String h;
    private final float i;
    private final int j;

    public Tx3gDecoder(List list) {
        String str = "sans-serif";
        boolean z = false;
        if (list.size() == 1 && (((byte[]) list.get(0)).length == 48 || ((byte[]) list.get(0)).length == 53)) {
            byte[] bArr = (byte[]) list.get(0);
            this.f = bArr[24];
            this.g = ((bArr[26] & Ev3Constants.Opcode.TST) << 24) | ((bArr[27] & Ev3Constants.Opcode.TST) << 16) | ((bArr[28] & Ev3Constants.Opcode.TST) << 8) | (bArr[29] & Ev3Constants.Opcode.TST);
            this.h = "Serif".equals(Util.a(bArr, 43, bArr.length - 43)) ? "serif" : str;
            int i2 = bArr[25] * 20;
            this.j = i2;
            z = (bArr[0] & 32) != 0 ? true : z;
            this.e = z;
            if (z) {
                this.i = Util.a(((float) ((bArr[11] & Ev3Constants.Opcode.TST) | ((bArr[10] & Ev3Constants.Opcode.TST) << 8))) / ((float) i2), 0.0f, 0.95f);
            } else {
                this.i = 0.85f;
            }
        } else {
            this.f = 0;
            this.g = -1;
            this.h = str;
            this.e = false;
            this.i = 0.85f;
            this.j = -1;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0036  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(android.text.SpannableStringBuilder r5, int r6, int r7, int r8, int r9, int r10) {
        /*
            if (r6 == r7) goto L_0x004c
            r7 = r10 | 33
            r10 = r6 & 1
            r0 = 0
            r1 = 1
            if (r10 == 0) goto L_0x000c
            r10 = 1
            goto L_0x000d
        L_0x000c:
            r10 = 0
        L_0x000d:
            r2 = r6 & 2
            if (r2 == 0) goto L_0x0013
            r2 = 1
            goto L_0x0014
        L_0x0013:
            r2 = 0
        L_0x0014:
            if (r10 == 0) goto L_0x0023
            android.text.style.StyleSpan r3 = new android.text.style.StyleSpan
            if (r2 == 0) goto L_0x001f
            r4 = 3
            r3.<init>(r4)
            goto L_0x002b
        L_0x001f:
            r3.<init>(r1)
            goto L_0x002b
        L_0x0023:
            if (r2 == 0) goto L_0x002e
            android.text.style.StyleSpan r3 = new android.text.style.StyleSpan
            r4 = 2
            r3.<init>(r4)
        L_0x002b:
            r5.setSpan(r3, r8, r9, r7)
        L_0x002e:
            r6 = r6 & 4
            if (r6 == 0) goto L_0x0033
            goto L_0x0034
        L_0x0033:
            r1 = 0
        L_0x0034:
            if (r1 == 0) goto L_0x003e
            android.text.style.UnderlineSpan r6 = new android.text.style.UnderlineSpan
            r6.<init>()
            r5.setSpan(r6, r8, r9, r7)
        L_0x003e:
            if (r1 != 0) goto L_0x004c
            if (r10 != 0) goto L_0x004c
            if (r2 != 0) goto L_0x004c
            android.text.style.StyleSpan r6 = new android.text.style.StyleSpan
            r6.<init>(r0)
            r5.setSpan(r6, r8, r9, r7)
        L_0x004c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.tx3g.Tx3gDecoder.a(android.text.SpannableStringBuilder, int, int, int, int, int):void");
    }

    private static void a(boolean z) {
        if (!z) {
            throw new SubtitleDecoderException("Unexpected subtitle format.");
        }
    }

    private static void b(SpannableStringBuilder spannableStringBuilder, int i2, int i3, int i4, int i5, int i6) {
        if (i2 != i3) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan((i2 >>> 8) | ((i2 & 255) << 24)), i4, i5, i6 | 33);
        }
    }

    public final Subtitle a(byte[] bArr, int i2, boolean z) {
        String str;
        int i3;
        int i4;
        char c;
        this.d.a(bArr, i2);
        ParsableByteArray parsableByteArray = this.d;
        int i5 = 1;
        a(parsableByteArray.a() >= 2);
        int d2 = parsableByteArray.d();
        if (d2 == 0) {
            str = "";
        } else {
            str = parsableByteArray.a(d2, (parsableByteArray.a() < 2 || !((c = (char) (((parsableByteArray.a[parsableByteArray.b] & Ev3Constants.Opcode.TST) << 8) | (parsableByteArray.a[parsableByteArray.b + 1] & Ev3Constants.Opcode.TST))) == 65279 || c == 65534)) ? aC.c : aC.e);
        }
        if (str.isEmpty()) {
            return Tx3gSubtitle.a;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        SpannableStringBuilder spannableStringBuilder2 = spannableStringBuilder;
        a(spannableStringBuilder2, this.f, 0, 0, spannableStringBuilder.length(), 16711680);
        b(spannableStringBuilder2, this.g, -1, 0, spannableStringBuilder.length(), 16711680);
        String str2 = this.h;
        int length = spannableStringBuilder.length();
        if (str2 != "sans-serif") {
            spannableStringBuilder.setSpan(new TypefaceSpan(str2), 0, length, 16711713);
        }
        float f2 = this.i;
        for (int i6 = 8; this.d.a() >= i6; i6 = 8) {
            int i7 = this.d.b;
            int j2 = this.d.j();
            int j3 = this.d.j();
            if (j3 == 1937013100) {
                a(this.d.a() >= 2);
                int d3 = this.d.d();
                int i8 = 0;
                while (i8 < d3) {
                    ParsableByteArray parsableByteArray2 = this.d;
                    a(parsableByteArray2.a() >= 12);
                    int d4 = parsableByteArray2.d();
                    int d5 = parsableByteArray2.d();
                    parsableByteArray2.e(2);
                    int c2 = parsableByteArray2.c();
                    parsableByteArray2.e(i5);
                    int j4 = parsableByteArray2.j();
                    if (d5 > spannableStringBuilder.length()) {
                        Log.c("Tx3gDecoder", new StringBuilder(68).append("Truncating styl end (").append(d5).append(") to cueText.length() (").append(spannableStringBuilder.length()).append(").").toString());
                        i3 = spannableStringBuilder.length();
                    } else {
                        i3 = d5;
                    }
                    if (d4 >= i3) {
                        Log.c("Tx3gDecoder", new StringBuilder(60).append("Ignoring styl with start (").append(d4).append(") >= end (").append(i3).append(").").toString());
                        i4 = i8;
                    } else {
                        SpannableStringBuilder spannableStringBuilder3 = spannableStringBuilder;
                        int i9 = d4;
                        int i10 = d4;
                        int i11 = i3;
                        i4 = i8;
                        a(spannableStringBuilder3, c2, this.f, i9, i11, 0);
                        b(spannableStringBuilder3, j4, this.g, i10, i11, 0);
                    }
                    i8 = i4 + 1;
                    i5 = 1;
                }
            } else if (j3 == 1952608120 && this.e) {
                a(this.d.a() >= 2);
                f2 = Util.a(((float) this.d.d()) / ((float) this.j), 0.0f, 0.95f);
            }
            this.d.d(i7 + j2);
            i5 = 1;
        }
        Cue.Builder builder = new Cue.Builder();
        builder.a = spannableStringBuilder;
        Cue.Builder a = builder.a(f2, 0);
        a.e = 0;
        return new Tx3gSubtitle(a.a());
    }
}
