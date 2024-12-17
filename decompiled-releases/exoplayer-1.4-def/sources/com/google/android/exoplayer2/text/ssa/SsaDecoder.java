package com.google.android.exoplayer2.text.ssa;

import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.ssa.SsaStyle;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SsaDecoder extends SimpleSubtitleDecoder {
    private static final Pattern d = Pattern.compile("(?:(\\d+):)?(\\d+):(\\d+)[:.](\\d+)");
    private final boolean e;
    private final SsaDialogueFormat f;
    private Map g;
    private float h;
    private float i;

    public SsaDecoder() {
        this((List) null);
    }

    public SsaDecoder(List list) {
        this.h = -3.4028235E38f;
        this.i = -3.4028235E38f;
        if (list == null || list.isEmpty()) {
            this.e = false;
            this.f = null;
            return;
        }
        this.e = true;
        String a = Util.a((byte[]) list.get(0));
        Assertions.a(a.startsWith("Format:"));
        this.f = (SsaDialogueFormat) Assertions.b((Object) SsaDialogueFormat.a(a));
        a(new ParsableByteArray((byte[]) list.get(1)));
    }

    private static float a(int i2) {
        switch (i2) {
            case 0:
                return 0.05f;
            case 1:
                return 0.5f;
            case 2:
                return 0.95f;
            default:
                return -3.4028235E38f;
        }
    }

    private static int a(long j, List list, List list2) {
        int i2;
        ArrayList arrayList;
        int size = list.size() - 1;
        while (true) {
            if (size < 0) {
                i2 = 0;
                break;
            } else if (((Long) list.get(size)).longValue() == j) {
                return size;
            } else {
                if (((Long) list.get(size)).longValue() < j) {
                    i2 = size + 1;
                    break;
                }
                size--;
            }
        }
        list.add(i2, Long.valueOf(j));
        if (i2 != 0) {
            arrayList = new ArrayList((Collection) list2.get(i2 - 1));
        }
        list2.add(i2, arrayList);
        return i2;
    }

    private static long a(String str) {
        Matcher matcher = d.matcher(str.trim());
        if (!matcher.matches()) {
            return -9223372036854775807L;
        }
        return (Long.parseLong((String) Util.a((Object) matcher.group(1))) * 60 * 60 * 1000000) + (Long.parseLong((String) Util.a((Object) matcher.group(2))) * 60 * 1000000) + (Long.parseLong((String) Util.a((Object) matcher.group(3))) * 1000000) + (Long.parseLong((String) Util.a((Object) matcher.group(4))) * 10000);
    }

    private void a(ParsableByteArray parsableByteArray) {
        while (true) {
            String s = parsableByteArray.s();
            if (s == null) {
                return;
            }
            if ("[Script Info]".equalsIgnoreCase(s)) {
                b(parsableByteArray);
            } else if ("[V4+ Styles]".equalsIgnoreCase(s)) {
                this.g = c(parsableByteArray);
            } else if ("[V4 Styles]".equalsIgnoreCase(s)) {
                Log.b("SsaDecoder", "[V4 Styles] are not supported");
            } else if ("[Events]".equalsIgnoreCase(s)) {
                return;
            }
        }
    }

    private void a(ParsableByteArray parsableByteArray, List list, List list2) {
        SsaDialogueFormat ssaDialogueFormat = this.e ? this.f : null;
        while (true) {
            String s = parsableByteArray.s();
            if (s == null) {
                return;
            }
            if (s.startsWith("Format:")) {
                ssaDialogueFormat = SsaDialogueFormat.a(s);
            } else if (s.startsWith("Dialogue:")) {
                if (ssaDialogueFormat == null) {
                    String valueOf = String.valueOf(s);
                    Log.c("SsaDecoder", valueOf.length() != 0 ? "Skipping dialogue line before complete format: ".concat(valueOf) : new String("Skipping dialogue line before complete format: "));
                } else {
                    a(s, ssaDialogueFormat, list, list2);
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x0158  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0168  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r20, com.google.android.exoplayer2.text.ssa.SsaDialogueFormat r21, java.util.List r22, java.util.List r23) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r2 = r21
            r3 = r22
            r4 = r23
            java.lang.String r5 = "Dialogue:"
            boolean r5 = r1.startsWith(r5)
            com.google.android.exoplayer2.util.Assertions.a((boolean) r5)
            r5 = 9
            java.lang.String r5 = r1.substring(r5)
            java.lang.String r6 = ","
            int r7 = r2.e
            java.lang.String[] r5 = r5.split(r6, r7)
            int r6 = r5.length
            int r7 = r2.e
            java.lang.String r8 = "SsaDecoder"
            if (r6 == r7) goto L_0x0042
            java.lang.String r1 = java.lang.String.valueOf(r20)
            int r2 = r1.length()
            java.lang.String r3 = "Skipping dialogue line with fewer columns than format: "
            if (r2 == 0) goto L_0x0039
            java.lang.String r1 = r3.concat(r1)
            goto L_0x003e
        L_0x0039:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r3)
        L_0x003e:
            com.google.android.exoplayer2.util.Log.c(r8, r1)
            return
        L_0x0042:
            int r6 = r2.a
            r6 = r5[r6]
            long r6 = a((java.lang.String) r6)
            java.lang.String r9 = "Skipping invalid timing: "
            r10 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r12 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r12 != 0) goto L_0x006d
            java.lang.String r1 = java.lang.String.valueOf(r20)
            int r2 = r1.length()
            if (r2 == 0) goto L_0x0064
            java.lang.String r1 = r9.concat(r1)
            goto L_0x0069
        L_0x0064:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r9)
        L_0x0069:
            com.google.android.exoplayer2.util.Log.c(r8, r1)
            return
        L_0x006d:
            int r12 = r2.b
            r12 = r5[r12]
            long r12 = a((java.lang.String) r12)
            int r14 = (r12 > r10 ? 1 : (r12 == r10 ? 0 : -1))
            if (r14 != 0) goto L_0x0091
            java.lang.String r1 = java.lang.String.valueOf(r20)
            int r2 = r1.length()
            if (r2 == 0) goto L_0x0088
            java.lang.String r1 = r9.concat(r1)
            goto L_0x008d
        L_0x0088:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r9)
        L_0x008d:
            com.google.android.exoplayer2.util.Log.c(r8, r1)
            return
        L_0x0091:
            java.util.Map r1 = r0.g
            r9 = -1
            if (r1 == 0) goto L_0x00ab
            int r1 = r2.c
            if (r1 == r9) goto L_0x00ab
            java.util.Map r1 = r0.g
            int r11 = r2.c
            r11 = r5[r11]
            java.lang.String r11 = r11.trim()
            java.lang.Object r1 = r1.get(r11)
            com.google.android.exoplayer2.text.ssa.SsaStyle r1 = (com.google.android.exoplayer2.text.ssa.SsaStyle) r1
            goto L_0x00ac
        L_0x00ab:
            r1 = 0
        L_0x00ac:
            int r2 = r2.d
            r2 = r5[r2]
            com.google.android.exoplayer2.text.ssa.SsaStyle$Overrides r5 = com.google.android.exoplayer2.text.ssa.SsaStyle.Overrides.a(r2)
            java.lang.String r2 = com.google.android.exoplayer2.text.ssa.SsaStyle.Overrides.b(r2)
            java.lang.String r11 = "\\N"
            java.lang.String r14 = "\n"
            java.lang.String r2 = r2.replace(r11, r14)
            java.lang.String r11 = "\\n"
            java.lang.String r2 = r2.replace(r11, r14)
            java.lang.String r11 = "\\h"
            java.lang.String r14 = " "
            java.lang.String r2 = r2.replace(r11, r14)
            float r11 = r0.h
            float r14 = r0.i
            android.text.SpannableString r15 = new android.text.SpannableString
            r15.<init>(r2)
            com.google.android.exoplayer2.text.Cue$Builder r2 = new com.google.android.exoplayer2.text.Cue$Builder
            r2.<init>()
            r2.a = r15
            r16 = -8388609(0xffffffffff7fffff, float:-3.4028235E38)
            if (r1 == 0) goto L_0x0175
            java.lang.Integer r9 = r1.c
            if (r9 == 0) goto L_0x00ff
            android.text.style.ForegroundColorSpan r9 = new android.text.style.ForegroundColorSpan
            java.lang.Integer r10 = r1.c
            int r10 = r10.intValue()
            r9.<init>(r10)
            int r10 = r15.length()
            r17 = r12
            r0 = 33
            r12 = 0
            r15.setSpan(r9, r12, r10, r0)
            goto L_0x0101
        L_0x00ff:
            r17 = r12
        L_0x0101:
            float r0 = r1.d
            int r0 = (r0 > r16 ? 1 : (r0 == r16 ? 0 : -1))
            if (r0 == 0) goto L_0x0112
            int r0 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r0 == 0) goto L_0x0112
            float r0 = r1.d
            float r0 = r0 / r14
            r9 = 1
            r2.b(r0, r9)
        L_0x0112:
            boolean r0 = r1.e
            if (r0 == 0) goto L_0x012b
            boolean r0 = r1.f
            if (r0 == 0) goto L_0x012b
            android.text.style.StyleSpan r0 = new android.text.style.StyleSpan
            r9 = 3
            r0.<init>(r9)
            int r9 = r15.length()
            r10 = 33
            r12 = 0
            r15.setSpan(r0, r12, r9, r10)
            goto L_0x0153
        L_0x012b:
            r10 = 33
            r12 = 0
            boolean r0 = r1.e
            if (r0 == 0) goto L_0x0140
            android.text.style.StyleSpan r0 = new android.text.style.StyleSpan
            r9 = 1
            r0.<init>(r9)
            int r13 = r15.length()
            r15.setSpan(r0, r12, r13, r10)
            goto L_0x0153
        L_0x0140:
            r9 = 1
            boolean r0 = r1.f
            if (r0 == 0) goto L_0x0153
            android.text.style.StyleSpan r0 = new android.text.style.StyleSpan
            r13 = 2
            r0.<init>(r13)
            int r9 = r15.length()
            r15.setSpan(r0, r12, r9, r10)
            goto L_0x0154
        L_0x0153:
            r13 = 2
        L_0x0154:
            boolean r0 = r1.g
            if (r0 == 0) goto L_0x0164
            android.text.style.UnderlineSpan r0 = new android.text.style.UnderlineSpan
            r0.<init>()
            int r9 = r15.length()
            r15.setSpan(r0, r12, r9, r10)
        L_0x0164:
            boolean r0 = r1.h
            if (r0 == 0) goto L_0x0178
            android.text.style.StrikethroughSpan r0 = new android.text.style.StrikethroughSpan
            r0.<init>()
            int r9 = r15.length()
            r15.setSpan(r0, r12, r9, r10)
            goto L_0x0178
        L_0x0175:
            r17 = r12
            r13 = 2
        L_0x0178:
            int r0 = r5.a
            r9 = -1
            if (r0 == r9) goto L_0x0180
            int r9 = r5.a
            goto L_0x0184
        L_0x0180:
            if (r1 == 0) goto L_0x0184
            int r9 = r1.b
        L_0x0184:
            java.lang.String r0 = "Unknown alignment: "
            r1 = 30
            switch(r9) {
                case -1: goto L_0x01a9;
                case 0: goto L_0x018b;
                case 1: goto L_0x01a6;
                case 2: goto L_0x01a3;
                case 3: goto L_0x01a0;
                case 4: goto L_0x01a6;
                case 5: goto L_0x01a3;
                case 6: goto L_0x01a0;
                case 7: goto L_0x01a6;
                case 8: goto L_0x01a3;
                case 9: goto L_0x01a0;
                default: goto L_0x018b;
            }
        L_0x018b:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>(r1)
            java.lang.StringBuilder r10 = r10.append(r0)
            java.lang.StringBuilder r10 = r10.append(r9)
            java.lang.String r10 = r10.toString()
            com.google.android.exoplayer2.util.Log.c(r8, r10)
            goto L_0x01a9
        L_0x01a0:
            android.text.Layout$Alignment r10 = android.text.Layout.Alignment.ALIGN_OPPOSITE
            goto L_0x01aa
        L_0x01a3:
            android.text.Layout$Alignment r10 = android.text.Layout.Alignment.ALIGN_CENTER
            goto L_0x01aa
        L_0x01a6:
            android.text.Layout$Alignment r10 = android.text.Layout.Alignment.ALIGN_NORMAL
            goto L_0x01aa
        L_0x01a9:
            r10 = 0
        L_0x01aa:
            r2.c = r10
            r12 = -2147483648(0xffffffff80000000, float:-0.0)
            switch(r9) {
                case -1: goto L_0x01cc;
                case 0: goto L_0x01b1;
                case 1: goto L_0x01ca;
                case 2: goto L_0x01c8;
                case 3: goto L_0x01c6;
                case 4: goto L_0x01ca;
                case 5: goto L_0x01c8;
                case 6: goto L_0x01c6;
                case 7: goto L_0x01ca;
                case 8: goto L_0x01c8;
                case 9: goto L_0x01c6;
                default: goto L_0x01b1;
            }
        L_0x01b1:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>(r1)
            java.lang.StringBuilder r10 = r10.append(r0)
            java.lang.StringBuilder r10 = r10.append(r9)
            java.lang.String r10 = r10.toString()
            com.google.android.exoplayer2.util.Log.c(r8, r10)
            goto L_0x01cc
        L_0x01c6:
            r10 = 2
            goto L_0x01ce
        L_0x01c8:
            r10 = 1
            goto L_0x01ce
        L_0x01ca:
            r10 = 0
            goto L_0x01ce
        L_0x01cc:
            r10 = -2147483648(0xffffffff80000000, float:-0.0)
        L_0x01ce:
            r2.g = r10
            switch(r9) {
                case -1: goto L_0x01ee;
                case 0: goto L_0x01d3;
                case 1: goto L_0x01ec;
                case 2: goto L_0x01ec;
                case 3: goto L_0x01ec;
                case 4: goto L_0x01ea;
                case 5: goto L_0x01ea;
                case 6: goto L_0x01ea;
                case 7: goto L_0x01e8;
                case 8: goto L_0x01e8;
                case 9: goto L_0x01e8;
                default: goto L_0x01d3;
            }
        L_0x01d3:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>(r1)
            java.lang.StringBuilder r0 = r10.append(r0)
            java.lang.StringBuilder r0 = r0.append(r9)
            java.lang.String r0 = r0.toString()
            com.google.android.exoplayer2.util.Log.c(r8, r0)
            goto L_0x01ee
        L_0x01e8:
            r10 = 0
            goto L_0x01f0
        L_0x01ea:
            r10 = 1
            goto L_0x01f0
        L_0x01ec:
            r10 = 2
            goto L_0x01f0
        L_0x01ee:
            r10 = -2147483648(0xffffffff80000000, float:-0.0)
        L_0x01f0:
            r2.e = r10
            android.graphics.PointF r0 = r5.b
            if (r0 == 0) goto L_0x020c
            int r0 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r0 == 0) goto L_0x020c
            int r0 = (r11 > r16 ? 1 : (r11 == r16 ? 0 : -1))
            if (r0 == 0) goto L_0x020c
            android.graphics.PointF r0 = r5.b
            float r0 = r0.x
            float r0 = r0 / r11
            r2.f = r0
            android.graphics.PointF r0 = r5.b
            float r0 = r0.y
            float r0 = r0 / r14
            r1 = 0
            goto L_0x021b
        L_0x020c:
            r1 = 0
            int r0 = r2.g
            float r0 = a((int) r0)
            r2.f = r0
            int r0 = r2.e
            float r0 = a((int) r0)
        L_0x021b:
            r2.a(r0, r1)
            com.google.android.exoplayer2.text.Cue r0 = r2.a()
            int r1 = a((long) r6, (java.util.List) r4, (java.util.List) r3)
            r5 = r17
            int r2 = a((long) r5, (java.util.List) r4, (java.util.List) r3)
        L_0x022c:
            if (r1 >= r2) goto L_0x023a
            java.lang.Object r4 = r3.get(r1)
            java.util.List r4 = (java.util.List) r4
            r4.add(r0)
            int r1 = r1 + 1
            goto L_0x022c
        L_0x023a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.ssa.SsaDecoder.a(java.lang.String, com.google.android.exoplayer2.text.ssa.SsaDialogueFormat, java.util.List, java.util.List):void");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0042, code lost:
        if (r2.equals("playresx") != false) goto L_0x0046;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(com.google.android.exoplayer2.util.ParsableByteArray r6) {
        /*
            r5 = this;
        L_0x0000:
            java.lang.String r0 = r6.s()
            if (r0 == 0) goto L_0x0066
            int r1 = r6.a()
            if (r1 == 0) goto L_0x0014
            int r1 = r6.b()
            r2 = 91
            if (r1 == r2) goto L_0x0066
        L_0x0014:
            java.lang.String r1 = ":"
            java.lang.String[] r0 = r0.split(r1)
            int r1 = r0.length
            r2 = 2
            if (r1 != r2) goto L_0x0000
            r1 = 0
            r2 = r0[r1]
            java.lang.String r2 = r2.trim()
            java.lang.String r2 = com.dreamers.exoplayercore.repack.C0000a.a((java.lang.String) r2)
            int r3 = r2.hashCode()
            r4 = 1
            switch(r3) {
                case 1879649548: goto L_0x003c;
                case 1879649549: goto L_0x0032;
                default: goto L_0x0031;
            }
        L_0x0031:
            goto L_0x0045
        L_0x0032:
            java.lang.String r1 = "playresy"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x0045
            r1 = 1
            goto L_0x0046
        L_0x003c:
            java.lang.String r3 = "playresx"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0045
            goto L_0x0046
        L_0x0045:
            r1 = -1
        L_0x0046:
            switch(r1) {
                case 0: goto L_0x0059;
                case 1: goto L_0x004a;
                default: goto L_0x0049;
            }
        L_0x0049:
            goto L_0x0000
        L_0x004a:
            r0 = r0[r4]     // Catch:{ NumberFormatException -> 0x0057 }
            java.lang.String r0 = r0.trim()     // Catch:{ NumberFormatException -> 0x0057 }
            float r0 = java.lang.Float.parseFloat(r0)     // Catch:{ NumberFormatException -> 0x0057 }
            r5.i = r0     // Catch:{ NumberFormatException -> 0x0057 }
            goto L_0x0000
        L_0x0057:
            r0 = move-exception
            goto L_0x0000
        L_0x0059:
            r0 = r0[r4]     // Catch:{ NumberFormatException -> 0x0057 }
            java.lang.String r0 = r0.trim()     // Catch:{ NumberFormatException -> 0x0057 }
            float r0 = java.lang.Float.parseFloat(r0)     // Catch:{ NumberFormatException -> 0x0057 }
            r5.h = r0     // Catch:{ NumberFormatException -> 0x0057 }
            goto L_0x0000
        L_0x0066:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.ssa.SsaDecoder.b(com.google.android.exoplayer2.util.ParsableByteArray):void");
    }

    private static Map c(ParsableByteArray parsableByteArray) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        SsaStyle.Format format = null;
        while (true) {
            String s = parsableByteArray.s();
            if (s == null || (parsableByteArray.a() != 0 && parsableByteArray.b() == 91)) {
                return linkedHashMap;
            }
            if (s.startsWith("Format:")) {
                format = SsaStyle.Format.a(s);
            } else if (s.startsWith("Style:")) {
                if (format == null) {
                    String valueOf = String.valueOf(s);
                    Log.c("SsaDecoder", valueOf.length() != 0 ? "Skipping 'Style:' line before 'Format:' line: ".concat(valueOf) : new String("Skipping 'Style:' line before 'Format:' line: "));
                } else {
                    SsaStyle a = SsaStyle.a(s, format);
                    if (a != null) {
                        linkedHashMap.put(a.a, a);
                    }
                }
            }
        }
        return linkedHashMap;
    }

    public final Subtitle a(byte[] bArr, int i2, boolean z) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr, i2);
        if (!this.e) {
            a(parsableByteArray);
        }
        a(parsableByteArray, (List) arrayList, (List) arrayList2);
        return new SsaSubtitle(arrayList, arrayList2);
    }
}
