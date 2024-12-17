package com.google.android.exoplayer2.text.webvtt;

import android.graphics.Color;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.cea.Cea708Decoder$Cea708CueInfo$$ExternalSyntheticBackport0;
import com.google.android.exoplayer2.text.span.RubySpan;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class WebvttCueParser {
    public static final Pattern a = Pattern.compile("^(\\S+)\\s+-->\\s+(\\S+)(.*)?$");
    private static final Pattern b = Pattern.compile("(\\S+?):(\\S+)");
    private static final Map c;
    private static final Map d;

    class Element {
        /* access modifiers changed from: private */
        public static final Comparator a = WebvttCueParser$Element$$Lambda$0.a;
        /* access modifiers changed from: private */
        public final StartTag b;
        /* access modifiers changed from: private */
        public final int c;

        private Element(StartTag startTag, int i) {
            this.b = startTag;
            this.c = i;
        }

        /* synthetic */ Element(StartTag startTag, int i, byte b2) {
            this(startTag, i);
        }
    }

    final class StartTag {
        public final String a;
        public final int b;
        public final String c;
        public final Set d;

        private StartTag(String str, int i, String str2, Set set) {
            this.b = i;
            this.a = str;
            this.c = str2;
            this.d = set;
        }

        public static StartTag a() {
            return new StartTag("", 0, "", Collections.emptySet());
        }

        public static StartTag a(String str, int i) {
            String str2;
            String trim = str.trim();
            Assertions.a(!trim.isEmpty());
            int indexOf = trim.indexOf(" ");
            if (indexOf == -1) {
                str2 = "";
            } else {
                String trim2 = trim.substring(indexOf).trim();
                trim = trim.substring(0, indexOf);
                str2 = trim2;
            }
            String[] a2 = Util.a(trim, "\\.");
            String str3 = a2[0];
            HashSet hashSet = new HashSet();
            for (int i2 = 1; i2 < a2.length; i2++) {
                hashSet.add(a2[i2]);
            }
            return new StartTag(str3, i, str2, hashSet);
        }
    }

    final class StyleMatch implements Comparable {
        public final WebvttCssStyle a;
        private int b;

        public StyleMatch(int i, WebvttCssStyle webvttCssStyle) {
            this.b = i;
            this.a = webvttCssStyle;
        }

        public final /* synthetic */ int compareTo(Object obj) {
            return Cea708Decoder$Cea708CueInfo$$ExternalSyntheticBackport0.m(this.b, ((StyleMatch) obj).b);
        }
    }

    final class WebvttCueInfoBuilder {
        public long a = 0;
        public long b = 0;
        public CharSequence c;
        public int d = 2;
        public float e = -3.4028235E38f;
        public int f = 1;
        public int g = 0;
        public float h = -3.4028235E38f;
        public int i = Integer.MIN_VALUE;
        public float j = 1.0f;
        public int k = Integer.MIN_VALUE;

        private static float a(float f2, int i2) {
            if (f2 == -3.4028235E38f || i2 != 0 || (f2 >= 0.0f && f2 <= 1.0f)) {
                return f2 != -3.4028235E38f ? f2 : i2 == 0 ? 1.0f : -3.4028235E38f;
            }
            return 1.0f;
        }

        private static float a(int i2) {
            switch (i2) {
                case 4:
                    return 0.0f;
                case 5:
                    return 1.0f;
                default:
                    return 0.5f;
            }
        }

        private static float a(int i2, float f2) {
            switch (i2) {
                case 0:
                    return 1.0f - f2;
                case 1:
                    return f2 <= 0.5f ? f2 * 2.0f : (1.0f - f2) * 2.0f;
                case 2:
                    return f2;
                default:
                    throw new IllegalStateException(String.valueOf(i2));
            }
        }

        private static int b(int i2) {
            switch (i2) {
                case 1:
                case 4:
                    return 0;
                case 3:
                case 5:
                    return 2;
                default:
                    return 1;
            }
        }

        private static Layout.Alignment c(int i2) {
            switch (i2) {
                case 1:
                case 4:
                    return Layout.Alignment.ALIGN_NORMAL;
                case 2:
                    return Layout.Alignment.ALIGN_CENTER;
                case 3:
                case 5:
                    return Layout.Alignment.ALIGN_OPPOSITE;
                default:
                    Log.c("WebvttCueParser", new StringBuilder(34).append("Unknown textAlignment: ").append(i2).toString());
                    return null;
            }
        }

        public final Cue.Builder a() {
            float f2 = this.h;
            if (f2 == -3.4028235E38f) {
                f2 = a(this.d);
            }
            int i2 = this.i;
            if (i2 == Integer.MIN_VALUE) {
                i2 = b(this.d);
            }
            Cue.Builder builder = new Cue.Builder();
            builder.c = c(this.d);
            Cue.Builder a2 = builder.a(a(this.e, this.f), this.f);
            a2.e = this.g;
            a2.f = f2;
            a2.g = i2;
            a2.h = Math.min(this.j, a(i2, f2));
            a2.l = this.k;
            CharSequence charSequence = this.c;
            if (charSequence != null) {
                a2.a = charSequence;
            }
            return a2;
        }
    }

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("white", Integer.valueOf(Color.rgb(255, 255, 255)));
        hashMap.put("lime", Integer.valueOf(Color.rgb(0, 255, 0)));
        hashMap.put("cyan", Integer.valueOf(Color.rgb(0, 255, 255)));
        hashMap.put("red", Integer.valueOf(Color.rgb(255, 0, 0)));
        hashMap.put("yellow", Integer.valueOf(Color.rgb(255, 255, 0)));
        hashMap.put("magenta", Integer.valueOf(Color.rgb(255, 0, 255)));
        hashMap.put("blue", Integer.valueOf(Color.rgb(0, 0, 255)));
        hashMap.put("black", Integer.valueOf(Color.rgb(0, 0, 0)));
        c = Collections.unmodifiableMap(hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("bg_white", Integer.valueOf(Color.rgb(255, 255, 255)));
        hashMap2.put("bg_lime", Integer.valueOf(Color.rgb(0, 255, 0)));
        hashMap2.put("bg_cyan", Integer.valueOf(Color.rgb(0, 255, 255)));
        hashMap2.put("bg_red", Integer.valueOf(Color.rgb(255, 0, 0)));
        hashMap2.put("bg_yellow", Integer.valueOf(Color.rgb(255, 255, 0)));
        hashMap2.put("bg_magenta", Integer.valueOf(Color.rgb(255, 0, 255)));
        hashMap2.put("bg_blue", Integer.valueOf(Color.rgb(0, 0, 255)));
        hashMap2.put("bg_black", Integer.valueOf(Color.rgb(0, 0, 0)));
        d = Collections.unmodifiableMap(hashMap2);
    }

    private static int a(List list, String str, StartTag startTag) {
        List b2 = b(list, str, startTag);
        for (int i = 0; i < b2.size(); i++) {
            WebvttCssStyle webvttCssStyle = ((StyleMatch) b2.get(i)).a;
            if (webvttCssStyle.o != -1) {
                return webvttCssStyle.o;
            }
        }
        return -1;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00c9, code lost:
        if (r10.equals("i") != false) goto L_0x00e1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0172, code lost:
        if (r7.equals("gt") != false) goto L_0x0176;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static android.text.SpannedString a(java.lang.String r16, java.lang.String r17, java.util.List r18) {
        /*
            r0 = r16
            r1 = r17
            r2 = r18
            android.text.SpannableStringBuilder r3 = new android.text.SpannableStringBuilder
            r3.<init>()
            java.util.ArrayDeque r4 = new java.util.ArrayDeque
            r4.<init>()
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            r6 = 0
            r7 = 0
        L_0x0017:
            int r8 = r17.length()
            if (r7 >= r8) goto L_0x01c2
            char r8 = r1.charAt(r7)
            r10 = 62
            r11 = 2
            r12 = -1
            r13 = 1
            switch(r8) {
                case 38: goto L_0x0127;
                case 60: goto L_0x002f;
                default: goto L_0x0029;
            }
        L_0x0029:
            r3.append(r8)
            int r7 = r7 + 1
            goto L_0x0017
        L_0x002f:
            int r8 = r7 + 1
            int r14 = r17.length()
            if (r8 < r14) goto L_0x0039
        L_0x0037:
            r7 = r8
            goto L_0x0017
        L_0x0039:
            char r14 = r1.charAt(r8)
            r15 = 47
            if (r14 != r15) goto L_0x0043
            r14 = 1
            goto L_0x0044
        L_0x0043:
            r14 = 0
        L_0x0044:
            int r8 = r1.indexOf(r10, r8)
            if (r8 != r12) goto L_0x004f
            int r8 = r17.length()
            goto L_0x0051
        L_0x004f:
            int r8 = r8 + 1
        L_0x0051:
            int r10 = r8 + -2
            char r9 = r1.charAt(r10)
            if (r9 != r15) goto L_0x005b
            r9 = 1
            goto L_0x005c
        L_0x005b:
            r9 = 0
        L_0x005c:
            if (r14 == 0) goto L_0x0060
            r15 = 2
            goto L_0x0061
        L_0x0060:
            r15 = 1
        L_0x0061:
            int r7 = r7 + r15
            if (r9 == 0) goto L_0x0065
            goto L_0x0067
        L_0x0065:
            int r10 = r8 + -1
        L_0x0067:
            java.lang.String r7 = r1.substring(r7, r10)
            java.lang.String r10 = r7.trim()
            boolean r10 = r10.isEmpty()
            if (r10 != 0) goto L_0x0037
            java.lang.String r10 = r7.trim()
            boolean r15 = r10.isEmpty()
            r15 = r15 ^ r13
            com.google.android.exoplayer2.util.Assertions.a((boolean) r15)
            java.lang.String r15 = "[ \\.]"
            java.lang.String[] r10 = com.google.android.exoplayer2.util.Util.b((java.lang.String) r10, (java.lang.String) r15)
            r10 = r10[r6]
            int r15 = r10.hashCode()
            switch(r15) {
                case 98: goto L_0x00d6;
                case 99: goto L_0x00cc;
                case 105: goto L_0x00c3;
                case 117: goto L_0x00b9;
                case 118: goto L_0x00af;
                case 3650: goto L_0x00a5;
                case 3314158: goto L_0x009b;
                case 3511770: goto L_0x0091;
                default: goto L_0x0090;
            }
        L_0x0090:
            goto L_0x00e0
        L_0x0091:
            java.lang.String r11 = "ruby"
            boolean r11 = r10.equals(r11)
            if (r11 == 0) goto L_0x00e0
            r11 = 4
            goto L_0x00e1
        L_0x009b:
            java.lang.String r11 = "lang"
            boolean r11 = r10.equals(r11)
            if (r11 == 0) goto L_0x00e0
            r11 = 3
            goto L_0x00e1
        L_0x00a5:
            java.lang.String r11 = "rt"
            boolean r11 = r10.equals(r11)
            if (r11 == 0) goto L_0x00e0
            r11 = 5
            goto L_0x00e1
        L_0x00af:
            java.lang.String r11 = "v"
            boolean r11 = r10.equals(r11)
            if (r11 == 0) goto L_0x00e0
            r11 = 7
            goto L_0x00e1
        L_0x00b9:
            java.lang.String r11 = "u"
            boolean r11 = r10.equals(r11)
            if (r11 == 0) goto L_0x00e0
            r11 = 6
            goto L_0x00e1
        L_0x00c3:
            java.lang.String r15 = "i"
            boolean r15 = r10.equals(r15)
            if (r15 == 0) goto L_0x00e0
            goto L_0x00e1
        L_0x00cc:
            java.lang.String r11 = "c"
            boolean r11 = r10.equals(r11)
            if (r11 == 0) goto L_0x00e0
            r11 = 1
            goto L_0x00e1
        L_0x00d6:
            java.lang.String r11 = "b"
            boolean r11 = r10.equals(r11)
            if (r11 == 0) goto L_0x00e0
            r11 = 0
            goto L_0x00e1
        L_0x00e0:
            r11 = -1
        L_0x00e1:
            switch(r11) {
                case 0: goto L_0x00e5;
                case 1: goto L_0x00e5;
                case 2: goto L_0x00e5;
                case 3: goto L_0x00e5;
                case 4: goto L_0x00e5;
                case 5: goto L_0x00e5;
                case 6: goto L_0x00e5;
                case 7: goto L_0x00e5;
                default: goto L_0x00e4;
            }
        L_0x00e4:
            r13 = 0
        L_0x00e5:
            if (r13 == 0) goto L_0x0037
            if (r14 == 0) goto L_0x0118
        L_0x00e9:
            boolean r7 = r4.isEmpty()
            if (r7 != 0) goto L_0x0037
            java.lang.Object r7 = r4.pop()
            com.google.android.exoplayer2.text.webvtt.WebvttCueParser$StartTag r7 = (com.google.android.exoplayer2.text.webvtt.WebvttCueParser.StartTag) r7
            a((java.lang.String) r0, (com.google.android.exoplayer2.text.webvtt.WebvttCueParser.StartTag) r7, (java.util.List) r5, (android.text.SpannableStringBuilder) r3, (java.util.List) r2)
            boolean r9 = r4.isEmpty()
            if (r9 != 0) goto L_0x010b
            com.google.android.exoplayer2.text.webvtt.WebvttCueParser$Element r9 = new com.google.android.exoplayer2.text.webvtt.WebvttCueParser$Element
            int r11 = r3.length()
            r9.<init>(r7, r11, r6)
            r5.add(r9)
            goto L_0x010e
        L_0x010b:
            r5.clear()
        L_0x010e:
            java.lang.String r7 = r7.a
            boolean r7 = r7.equals(r10)
            if (r7 == 0) goto L_0x00e9
            goto L_0x0037
        L_0x0118:
            if (r9 != 0) goto L_0x0037
            int r9 = r3.length()
            com.google.android.exoplayer2.text.webvtt.WebvttCueParser$StartTag r7 = com.google.android.exoplayer2.text.webvtt.WebvttCueParser.StartTag.a(r7, r9)
            r4.push(r7)
            goto L_0x0037
        L_0x0127:
            int r7 = r7 + 1
            r9 = 59
            int r9 = r1.indexOf(r9, r7)
            r14 = 32
            int r15 = r1.indexOf(r14, r7)
            if (r9 != r12) goto L_0x0139
            r9 = r15
            goto L_0x0140
        L_0x0139:
            if (r15 != r12) goto L_0x013c
            goto L_0x0140
        L_0x013c:
            int r9 = java.lang.Math.min(r9, r15)
        L_0x0140:
            if (r9 == r12) goto L_0x01bd
            java.lang.String r7 = r1.substring(r7, r9)
            int r8 = r7.hashCode()
            switch(r8) {
                case 3309: goto L_0x016c;
                case 3464: goto L_0x0162;
                case 96708: goto L_0x0158;
                case 3374865: goto L_0x014e;
                default: goto L_0x014d;
            }
        L_0x014d:
            goto L_0x0175
        L_0x014e:
            java.lang.String r8 = "nbsp"
            boolean r8 = r7.equals(r8)
            if (r8 == 0) goto L_0x0175
            r13 = 2
            goto L_0x0176
        L_0x0158:
            java.lang.String r8 = "amp"
            boolean r8 = r7.equals(r8)
            if (r8 == 0) goto L_0x0175
            r13 = 3
            goto L_0x0176
        L_0x0162:
            java.lang.String r8 = "lt"
            boolean r8 = r7.equals(r8)
            if (r8 == 0) goto L_0x0175
            r13 = 0
            goto L_0x0176
        L_0x016c:
            java.lang.String r8 = "gt"
            boolean r8 = r7.equals(r8)
            if (r8 == 0) goto L_0x0175
            goto L_0x0176
        L_0x0175:
            r13 = -1
        L_0x0176:
            switch(r13) {
                case 0: goto L_0x01ad;
                case 1: goto L_0x01a9;
                case 2: goto L_0x01a5;
                case 3: goto L_0x01a2;
                default: goto L_0x0179;
            }
        L_0x0179:
            java.lang.String r8 = java.lang.String.valueOf(r7)
            int r8 = r8.length()
            int r8 = r8 + 33
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>(r8)
            java.lang.String r8 = "ignoring unsupported entity: '&"
            java.lang.StringBuilder r8 = r10.append(r8)
            java.lang.StringBuilder r7 = r8.append(r7)
            java.lang.String r8 = ";'"
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r7 = r7.toString()
            java.lang.String r8 = "WebvttCueParser"
            com.google.android.exoplayer2.util.Log.c(r8, r7)
            goto L_0x01b2
        L_0x01a2:
            r7 = 38
            goto L_0x01af
        L_0x01a5:
            r3.append(r14)
            goto L_0x01b2
        L_0x01a9:
            r3.append(r10)
            goto L_0x01b2
        L_0x01ad:
            r7 = 60
        L_0x01af:
            r3.append(r7)
        L_0x01b2:
            if (r9 != r15) goto L_0x01b9
            java.lang.String r7 = " "
            r3.append(r7)
        L_0x01b9:
            int r7 = r9 + 1
            goto L_0x0017
        L_0x01bd:
            r3.append(r8)
            goto L_0x0017
        L_0x01c2:
            boolean r1 = r4.isEmpty()
            if (r1 != 0) goto L_0x01d2
            java.lang.Object r1 = r4.pop()
            com.google.android.exoplayer2.text.webvtt.WebvttCueParser$StartTag r1 = (com.google.android.exoplayer2.text.webvtt.WebvttCueParser.StartTag) r1
            a((java.lang.String) r0, (com.google.android.exoplayer2.text.webvtt.WebvttCueParser.StartTag) r1, (java.util.List) r5, (android.text.SpannableStringBuilder) r3, (java.util.List) r2)
            goto L_0x01c2
        L_0x01d2:
            com.google.android.exoplayer2.text.webvtt.WebvttCueParser$StartTag r1 = com.google.android.exoplayer2.text.webvtt.WebvttCueParser.StartTag.a()
            java.util.List r4 = java.util.Collections.emptyList()
            a((java.lang.String) r0, (com.google.android.exoplayer2.text.webvtt.WebvttCueParser.StartTag) r1, (java.util.List) r4, (android.text.SpannableStringBuilder) r3, (java.util.List) r2)
            android.text.SpannedString r0 = android.text.SpannedString.valueOf(r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.webvtt.WebvttCueParser.a(java.lang.String, java.lang.String, java.util.List):android.text.SpannedString");
    }

    static Cue.Builder a(String str) {
        WebvttCueInfoBuilder webvttCueInfoBuilder = new WebvttCueInfoBuilder();
        a(str, webvttCueInfoBuilder);
        return webvttCueInfoBuilder.a();
    }

    static Cue a(CharSequence charSequence) {
        WebvttCueInfoBuilder webvttCueInfoBuilder = new WebvttCueInfoBuilder();
        webvttCueInfoBuilder.c = charSequence;
        return webvttCueInfoBuilder.a().a();
    }

    public static WebvttCueInfo a(ParsableByteArray parsableByteArray, List list) {
        String s = parsableByteArray.s();
        if (s == null) {
            return null;
        }
        Pattern pattern = a;
        Matcher matcher = pattern.matcher(s);
        if (matcher.matches()) {
            return a((String) null, matcher, parsableByteArray, list);
        }
        String s2 = parsableByteArray.s();
        if (s2 == null) {
            return null;
        }
        Matcher matcher2 = pattern.matcher(s2);
        if (matcher2.matches()) {
            return a(s.trim(), matcher2, parsableByteArray, list);
        }
        return null;
    }

    private static WebvttCueInfo a(String str, Matcher matcher, ParsableByteArray parsableByteArray, List list) {
        WebvttCueInfoBuilder webvttCueInfoBuilder = new WebvttCueInfoBuilder();
        try {
            webvttCueInfoBuilder.a = WebvttParserUtil.a((String) Assertions.b((Object) matcher.group(1)));
            webvttCueInfoBuilder.b = WebvttParserUtil.a((String) Assertions.b((Object) matcher.group(2)));
            a((String) Assertions.b((Object) matcher.group(3)), webvttCueInfoBuilder);
            StringBuilder sb = new StringBuilder();
            while (true) {
                String s = parsableByteArray.s();
                if (!TextUtils.isEmpty(s)) {
                    if (sb.length() > 0) {
                        sb.append("\n");
                    }
                    sb.append(s.trim());
                } else {
                    webvttCueInfoBuilder.c = a(str, sb.toString(), list);
                    return new WebvttCueInfo(webvttCueInfoBuilder.a().a(), webvttCueInfoBuilder.a, webvttCueInfoBuilder.b);
                }
            }
        } catch (NumberFormatException e) {
            String valueOf = String.valueOf(matcher.group());
            Log.c("WebvttCueParser", valueOf.length() != 0 ? "Skipping cue with bad header: ".concat(valueOf) : new String("Skipping cue with bad header: "));
            return null;
        }
    }

    private static void a(SpannableStringBuilder spannableStringBuilder, String str, StartTag startTag, List list, List list2) {
        int a2 = a(list2, str, startTag);
        ArrayList arrayList = new ArrayList(list.size());
        arrayList.addAll(list);
        Collections.sort(arrayList, Element.a);
        int i = startTag.b;
        int i2 = 0;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            if ("rt".equals(((Element) arrayList.get(i3)).b.a)) {
                Element element = (Element) arrayList.get(i3);
                int a3 = a(list2, str, element.b);
                if (a3 == -1) {
                    a3 = a2 != -1 ? a2 : 1;
                }
                int i4 = element.b.b - i2;
                int b2 = element.c - i2;
                CharSequence subSequence = spannableStringBuilder.subSequence(i4, b2);
                spannableStringBuilder.delete(i4, b2);
                spannableStringBuilder.setSpan(new RubySpan(subSequence.toString(), a3), i, i4, 33);
                i2 += subSequence.length();
                i = i4;
            }
        }
    }

    private static void a(SpannableStringBuilder spannableStringBuilder, Set set, int i, int i2) {
        Object backgroundColorSpan;
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            Map map = c;
            if (map.containsKey(str)) {
                backgroundColorSpan = new ForegroundColorSpan(((Integer) map.get(str)).intValue());
            } else {
                Map map2 = d;
                if (map2.containsKey(str)) {
                    backgroundColorSpan = new BackgroundColorSpan(((Integer) map2.get(str)).intValue());
                }
            }
            spannableStringBuilder.setSpan(backgroundColorSpan, i, i2, 33);
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0135  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x013d A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(java.lang.String r8, com.google.android.exoplayer2.text.webvtt.WebvttCueParser.StartTag r9, java.util.List r10, android.text.SpannableStringBuilder r11, java.util.List r12) {
        /*
            int r0 = r9.b
            int r1 = r11.length()
            java.lang.String r2 = r9.a
            int r3 = r2.hashCode()
            r4 = 2
            r5 = 0
            r6 = 1
            r7 = -1
            switch(r3) {
                case 0: goto L_0x005a;
                case 98: goto L_0x0050;
                case 99: goto L_0x0046;
                case 105: goto L_0x003c;
                case 117: goto L_0x0032;
                case 118: goto L_0x0028;
                case 3314158: goto L_0x001e;
                case 3511770: goto L_0x0014;
                default: goto L_0x0013;
            }
        L_0x0013:
            goto L_0x0064
        L_0x0014:
            java.lang.String r3 = "ruby"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0064
            r2 = 2
            goto L_0x0065
        L_0x001e:
            java.lang.String r3 = "lang"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0064
            r2 = 5
            goto L_0x0065
        L_0x0028:
            java.lang.String r3 = "v"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0064
            r2 = 6
            goto L_0x0065
        L_0x0032:
            java.lang.String r3 = "u"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0064
            r2 = 3
            goto L_0x0065
        L_0x003c:
            java.lang.String r3 = "i"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0064
            r2 = 1
            goto L_0x0065
        L_0x0046:
            java.lang.String r3 = "c"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0064
            r2 = 4
            goto L_0x0065
        L_0x0050:
            java.lang.String r3 = "b"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0064
            r2 = 0
            goto L_0x0065
        L_0x005a:
            java.lang.String r3 = ""
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0064
            r2 = 7
            goto L_0x0065
        L_0x0064:
            r2 = -1
        L_0x0065:
            r3 = 33
            switch(r2) {
                case 0: goto L_0x0081;
                case 1: goto L_0x007b;
                case 2: goto L_0x0077;
                case 3: goto L_0x0071;
                case 4: goto L_0x006b;
                case 5: goto L_0x0089;
                case 6: goto L_0x0089;
                case 7: goto L_0x0089;
                default: goto L_0x006a;
            }
        L_0x006a:
            return
        L_0x006b:
            java.util.Set r10 = r9.d
            a((android.text.SpannableStringBuilder) r11, (java.util.Set) r10, (int) r0, (int) r1)
            goto L_0x0089
        L_0x0071:
            android.text.style.UnderlineSpan r10 = new android.text.style.UnderlineSpan
            r10.<init>()
            goto L_0x0086
        L_0x0077:
            a((android.text.SpannableStringBuilder) r11, (java.lang.String) r8, (com.google.android.exoplayer2.text.webvtt.WebvttCueParser.StartTag) r9, (java.util.List) r10, (java.util.List) r12)
            goto L_0x0089
        L_0x007b:
            android.text.style.StyleSpan r10 = new android.text.style.StyleSpan
            r10.<init>(r4)
            goto L_0x0086
        L_0x0081:
            android.text.style.StyleSpan r10 = new android.text.style.StyleSpan
            r10.<init>(r6)
        L_0x0086:
            r11.setSpan(r10, r0, r1, r3)
        L_0x0089:
            java.util.List r8 = b(r12, r8, r9)
            r9 = 0
        L_0x008e:
            int r10 = r8.size()
            if (r9 >= r10) goto L_0x0141
            java.lang.Object r10 = r8.get(r9)
            com.google.android.exoplayer2.text.webvtt.WebvttCueParser$StyleMatch r10 = (com.google.android.exoplayer2.text.webvtt.WebvttCueParser.StyleMatch) r10
            com.google.android.exoplayer2.text.webvtt.WebvttCssStyle r10 = r10.a
            if (r10 == 0) goto L_0x013d
            int r12 = r10.a()
            if (r12 == r7) goto L_0x00b0
            android.text.style.StyleSpan r12 = new android.text.style.StyleSpan
            int r2 = r10.a()
            r12.<init>(r2)
            com.google.android.exoplayer2.text.span.SpanUtil.a(r11, r12, r0, r1)
        L_0x00b0:
            int r12 = r10.j
            if (r12 != r6) goto L_0x00b6
            r12 = 1
            goto L_0x00b7
        L_0x00b6:
            r12 = 0
        L_0x00b7:
            if (r12 == 0) goto L_0x00c1
            android.text.style.StrikethroughSpan r12 = new android.text.style.StrikethroughSpan
            r12.<init>()
            r11.setSpan(r12, r0, r1, r3)
        L_0x00c1:
            int r12 = r10.k
            if (r12 != r6) goto L_0x00c7
            r12 = 1
            goto L_0x00c8
        L_0x00c7:
            r12 = 0
        L_0x00c8:
            if (r12 == 0) goto L_0x00d2
            android.text.style.UnderlineSpan r12 = new android.text.style.UnderlineSpan
            r12.<init>()
            r11.setSpan(r12, r0, r1, r3)
        L_0x00d2:
            boolean r12 = r10.g
            if (r12 == 0) goto L_0x00ed
            android.text.style.ForegroundColorSpan r12 = new android.text.style.ForegroundColorSpan
            boolean r2 = r10.g
            if (r2 == 0) goto L_0x00e5
            int r2 = r10.f
            r12.<init>(r2)
            com.google.android.exoplayer2.text.span.SpanUtil.a(r11, r12, r0, r1)
            goto L_0x00ed
        L_0x00e5:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "Font color not defined"
            r8.<init>(r9)
            throw r8
        L_0x00ed:
            boolean r12 = r10.i
            if (r12 == 0) goto L_0x0108
            android.text.style.BackgroundColorSpan r12 = new android.text.style.BackgroundColorSpan
            boolean r2 = r10.i
            if (r2 == 0) goto L_0x0100
            int r2 = r10.h
            r12.<init>(r2)
            com.google.android.exoplayer2.text.span.SpanUtil.a(r11, r12, r0, r1)
            goto L_0x0108
        L_0x0100:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "Background color not defined."
            r8.<init>(r9)
            throw r8
        L_0x0108:
            java.lang.String r12 = r10.e
            if (r12 == 0) goto L_0x0116
            android.text.style.TypefaceSpan r12 = new android.text.style.TypefaceSpan
            java.lang.String r2 = r10.e
            r12.<init>(r2)
            com.google.android.exoplayer2.text.span.SpanUtil.a(r11, r12, r0, r1)
        L_0x0116:
            int r12 = r10.n
            r2 = 0
            switch(r12) {
                case 1: goto L_0x0129;
                case 2: goto L_0x0123;
                case 3: goto L_0x011d;
                default: goto L_0x011c;
            }
        L_0x011c:
            goto L_0x0131
        L_0x011d:
            android.text.style.RelativeSizeSpan r12 = new android.text.style.RelativeSizeSpan
            r12.<init>(r2)
            goto L_0x012e
        L_0x0123:
            android.text.style.RelativeSizeSpan r12 = new android.text.style.RelativeSizeSpan
            r12.<init>(r2)
            goto L_0x012e
        L_0x0129:
            android.text.style.AbsoluteSizeSpan r12 = new android.text.style.AbsoluteSizeSpan
            r12.<init>(r5, r6)
        L_0x012e:
            com.google.android.exoplayer2.text.span.SpanUtil.a(r11, r12, r0, r1)
        L_0x0131:
            boolean r10 = r10.p
            if (r10 == 0) goto L_0x013d
            com.google.android.exoplayer2.text.span.HorizontalTextInVerticalContextSpan r10 = new com.google.android.exoplayer2.text.span.HorizontalTextInVerticalContextSpan
            r10.<init>()
            r11.setSpan(r10, r0, r1, r3)
        L_0x013d:
            int r9 = r9 + 1
            goto L_0x008e
        L_0x0141:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.webvtt.WebvttCueParser.a(java.lang.String, com.google.android.exoplayer2.text.webvtt.WebvttCueParser$StartTag, java.util.List, android.text.SpannableStringBuilder, java.util.List):void");
    }

    private static void a(String str, WebvttCueInfoBuilder webvttCueInfoBuilder) {
        Matcher matcher = b.matcher(str);
        while (matcher.find()) {
            String str2 = (String) Assertions.b((Object) matcher.group(1));
            String str3 = (String) Assertions.b((Object) matcher.group(2));
            try {
                if ("line".equals(str2)) {
                    b(str3, webvttCueInfoBuilder);
                } else if ("align".equals(str2)) {
                    webvttCueInfoBuilder.d = e(str3);
                } else if ("position".equals(str2)) {
                    c(str3, webvttCueInfoBuilder);
                } else if ("size".equals(str2)) {
                    webvttCueInfoBuilder.j = WebvttParserUtil.b(str3);
                } else if ("vertical".equals(str2)) {
                    webvttCueInfoBuilder.k = d(str3);
                } else {
                    Log.c("WebvttCueParser", new StringBuilder(String.valueOf(str2).length() + 21 + String.valueOf(str3).length()).append("Unknown cue setting ").append(str2).append(":").append(str3).toString());
                }
            } catch (NumberFormatException e) {
                String valueOf = String.valueOf(matcher.group());
                Log.c("WebvttCueParser", valueOf.length() != 0 ? "Skipping bad cue setting: ".concat(valueOf) : new String("Skipping bad cue setting: "));
            }
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int b(java.lang.String r4) {
        /*
            int r0 = r4.hashCode()
            r1 = 2
            r2 = 1
            r3 = 0
            switch(r0) {
                case -1364013995: goto L_0x0029;
                case -1074341483: goto L_0x001f;
                case 100571: goto L_0x0015;
                case 109757538: goto L_0x000b;
                default: goto L_0x000a;
            }
        L_0x000a:
            goto L_0x0033
        L_0x000b:
            java.lang.String r0 = "start"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0033
            r0 = 0
            goto L_0x0034
        L_0x0015:
            java.lang.String r0 = "end"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0033
            r0 = 3
            goto L_0x0034
        L_0x001f:
            java.lang.String r0 = "middle"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0033
            r0 = 2
            goto L_0x0034
        L_0x0029:
            java.lang.String r0 = "center"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0033
            r0 = 1
            goto L_0x0034
        L_0x0033:
            r0 = -1
        L_0x0034:
            switch(r0) {
                case 0: goto L_0x004a;
                case 1: goto L_0x0049;
                case 2: goto L_0x0049;
                case 3: goto L_0x0048;
                default: goto L_0x0037;
            }
        L_0x0037:
            java.lang.String r4 = java.lang.String.valueOf(r4)
            int r0 = r4.length()
            java.lang.String r1 = "Invalid anchor value: "
            if (r0 == 0) goto L_0x004b
            java.lang.String r4 = r1.concat(r4)
            goto L_0x0050
        L_0x0048:
            return r1
        L_0x0049:
            return r2
        L_0x004a:
            return r3
        L_0x004b:
            java.lang.String r4 = new java.lang.String
            r4.<init>(r1)
        L_0x0050:
            java.lang.String r0 = "WebvttCueParser"
            com.google.android.exoplayer2.util.Log.c(r0, r4)
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.webvtt.WebvttCueParser.b(java.lang.String):int");
    }

    private static List b(List list, String str, StartTag startTag) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            WebvttCssStyle webvttCssStyle = (WebvttCssStyle) list.get(i);
            int a2 = webvttCssStyle.a(str, startTag.a, startTag.d, startTag.c);
            if (a2 > 0) {
                arrayList.add(new StyleMatch(a2, webvttCssStyle));
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    private static void b(String str, WebvttCueInfoBuilder webvttCueInfoBuilder) {
        int indexOf = str.indexOf(44);
        if (indexOf != -1) {
            webvttCueInfoBuilder.g = b(str.substring(indexOf + 1));
            str = str.substring(0, indexOf);
        }
        if (str.endsWith("%")) {
            webvttCueInfoBuilder.e = WebvttParserUtil.b(str);
            webvttCueInfoBuilder.f = 0;
            return;
        }
        webvttCueInfoBuilder.e = (float) Integer.parseInt(str);
        webvttCueInfoBuilder.f = 1;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int c(java.lang.String r4) {
        /*
            int r0 = r4.hashCode()
            r1 = 2
            r2 = 1
            r3 = 0
            switch(r0) {
                case -1842484672: goto L_0x003d;
                case -1364013995: goto L_0x0033;
                case -1276788989: goto L_0x0029;
                case -1074341483: goto L_0x001f;
                case 100571: goto L_0x0015;
                case 109757538: goto L_0x000b;
                default: goto L_0x000a;
            }
        L_0x000a:
            goto L_0x0047
        L_0x000b:
            java.lang.String r0 = "start"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0047
            r0 = 1
            goto L_0x0048
        L_0x0015:
            java.lang.String r0 = "end"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0047
            r0 = 5
            goto L_0x0048
        L_0x001f:
            java.lang.String r0 = "middle"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0047
            r0 = 3
            goto L_0x0048
        L_0x0029:
            java.lang.String r0 = "line-right"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0047
            r0 = 4
            goto L_0x0048
        L_0x0033:
            java.lang.String r0 = "center"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0047
            r0 = 2
            goto L_0x0048
        L_0x003d:
            java.lang.String r0 = "line-left"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0047
            r0 = 0
            goto L_0x0048
        L_0x0047:
            r0 = -1
        L_0x0048:
            switch(r0) {
                case 0: goto L_0x005e;
                case 1: goto L_0x005e;
                case 2: goto L_0x005d;
                case 3: goto L_0x005d;
                case 4: goto L_0x005c;
                case 5: goto L_0x005c;
                default: goto L_0x004b;
            }
        L_0x004b:
            java.lang.String r4 = java.lang.String.valueOf(r4)
            int r0 = r4.length()
            java.lang.String r1 = "Invalid anchor value: "
            if (r0 == 0) goto L_0x005f
            java.lang.String r4 = r1.concat(r4)
            goto L_0x0064
        L_0x005c:
            return r1
        L_0x005d:
            return r2
        L_0x005e:
            return r3
        L_0x005f:
            java.lang.String r4 = new java.lang.String
            r4.<init>(r1)
        L_0x0064:
            java.lang.String r0 = "WebvttCueParser"
            com.google.android.exoplayer2.util.Log.c(r0, r4)
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.webvtt.WebvttCueParser.c(java.lang.String):int");
    }

    private static void c(String str, WebvttCueInfoBuilder webvttCueInfoBuilder) {
        int indexOf = str.indexOf(44);
        if (indexOf != -1) {
            webvttCueInfoBuilder.i = c(str.substring(indexOf + 1));
            str = str.substring(0, indexOf);
        }
        webvttCueInfoBuilder.h = WebvttParserUtil.b(str);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int d(java.lang.String r2) {
        /*
            int r0 = r2.hashCode()
            r1 = 1
            switch(r0) {
                case 3462: goto L_0x0013;
                case 3642: goto L_0x0009;
                default: goto L_0x0008;
            }
        L_0x0008:
            goto L_0x001d
        L_0x0009:
            java.lang.String r0 = "rl"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x001d
            r0 = 0
            goto L_0x001e
        L_0x0013:
            java.lang.String r0 = "lr"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x001d
            r0 = 1
            goto L_0x001e
        L_0x001d:
            r0 = -1
        L_0x001e:
            switch(r0) {
                case 0: goto L_0x0034;
                case 1: goto L_0x0032;
                default: goto L_0x0021;
            }
        L_0x0021:
            java.lang.String r2 = java.lang.String.valueOf(r2)
            int r0 = r2.length()
            java.lang.String r1 = "Invalid 'vertical' value: "
            if (r0 == 0) goto L_0x0035
            java.lang.String r2 = r1.concat(r2)
            goto L_0x003a
        L_0x0032:
            r2 = 2
            return r2
        L_0x0034:
            return r1
        L_0x0035:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r1)
        L_0x003a:
            java.lang.String r0 = "WebvttCueParser"
            com.google.android.exoplayer2.util.Log.c(r0, r2)
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.webvtt.WebvttCueParser.d(java.lang.String):int");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int e(java.lang.String r6) {
        /*
            int r0 = r6.hashCode()
            r1 = 5
            r2 = 3
            r3 = 4
            r4 = 1
            r5 = 2
            switch(r0) {
                case -1364013995: goto L_0x003f;
                case -1074341483: goto L_0x0035;
                case 100571: goto L_0x002b;
                case 3317767: goto L_0x0021;
                case 108511772: goto L_0x0017;
                case 109757538: goto L_0x000d;
                default: goto L_0x000c;
            }
        L_0x000c:
            goto L_0x0049
        L_0x000d:
            java.lang.String r0 = "start"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x0049
            r0 = 0
            goto L_0x004a
        L_0x0017:
            java.lang.String r0 = "right"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x0049
            r0 = 5
            goto L_0x004a
        L_0x0021:
            java.lang.String r0 = "left"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x0049
            r0 = 1
            goto L_0x004a
        L_0x002b:
            java.lang.String r0 = "end"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x0049
            r0 = 4
            goto L_0x004a
        L_0x0035:
            java.lang.String r0 = "middle"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x0049
            r0 = 3
            goto L_0x004a
        L_0x003f:
            java.lang.String r0 = "center"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x0049
            r0 = 2
            goto L_0x004a
        L_0x0049:
            r0 = -1
        L_0x004a:
            switch(r0) {
                case 0: goto L_0x0062;
                case 1: goto L_0x0061;
                case 2: goto L_0x0060;
                case 3: goto L_0x0060;
                case 4: goto L_0x005f;
                case 5: goto L_0x005e;
                default: goto L_0x004d;
            }
        L_0x004d:
            java.lang.String r6 = java.lang.String.valueOf(r6)
            int r0 = r6.length()
            java.lang.String r1 = "Invalid alignment value: "
            if (r0 == 0) goto L_0x0063
            java.lang.String r6 = r1.concat(r6)
            goto L_0x0068
        L_0x005e:
            return r1
        L_0x005f:
            return r2
        L_0x0060:
            return r5
        L_0x0061:
            return r3
        L_0x0062:
            return r4
        L_0x0063:
            java.lang.String r6 = new java.lang.String
            r6.<init>(r1)
        L_0x0068:
            java.lang.String r0 = "WebvttCueParser"
            com.google.android.exoplayer2.util.Log.c(r0, r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.webvtt.WebvttCueParser.e(java.lang.String):int");
    }
}
