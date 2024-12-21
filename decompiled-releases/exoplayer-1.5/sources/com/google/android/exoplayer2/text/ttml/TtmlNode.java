package com.google.android.exoplayer2.text.ttml;

import android.text.SpannableStringBuilder;
import android.util.Pair;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.util.Assertions;
import com.google.appinventor.components.common.ComponentDescriptorConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

final class TtmlNode {
    public final String a;
    public final long b;
    public final long c;
    public final TtmlStyle d;
    final String[] e;
    public final String f;
    public final TtmlNode g;
    private String h;
    private boolean i;
    private String j;
    private final HashMap k;
    private final HashMap l;
    private List m;

    private TtmlNode(String str, String str2, long j2, long j3, TtmlStyle ttmlStyle, String[] strArr, String str3, String str4, TtmlNode ttmlNode) {
        this.h = str;
        this.a = str2;
        this.j = str4;
        this.d = ttmlStyle;
        this.e = strArr;
        this.i = str2 != null;
        this.b = j2;
        this.c = j3;
        this.f = (String) Assertions.b((Object) str3);
        this.g = ttmlNode;
        this.k = new HashMap();
        this.l = new HashMap();
    }

    private static SpannableStringBuilder a(String str, Map map) {
        if (!map.containsKey(str)) {
            Cue.Builder builder = new Cue.Builder();
            builder.a = new SpannableStringBuilder();
            map.put(str, builder);
        }
        return (SpannableStringBuilder) Assertions.b((Object) ((Cue.Builder) map.get(str)).a);
    }

    public static TtmlNode a(String str) {
        return new TtmlNode((String) null, TtmlRenderUtil.a(str), -9223372036854775807L, -9223372036854775807L, (TtmlStyle) null, (String[]) null, "", (String) null, (TtmlNode) null);
    }

    public static TtmlNode a(String str, long j2, long j3, TtmlStyle ttmlStyle, String[] strArr, String str2, String str3, TtmlNode ttmlNode) {
        return new TtmlNode(str, (String) null, j2, j3, ttmlStyle, strArr, str2, str3, ttmlNode);
    }

    static void a(SpannableStringBuilder spannableStringBuilder) {
        for (DeleteTextSpan deleteTextSpan : (DeleteTextSpan[]) spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), DeleteTextSpan.class)) {
            spannableStringBuilder.replace(spannableStringBuilder.getSpanStart(deleteTextSpan), spannableStringBuilder.getSpanEnd(deleteTextSpan), "");
        }
        for (int i2 = 0; i2 < spannableStringBuilder.length(); i2++) {
            if (spannableStringBuilder.charAt(i2) == ' ') {
                int i3 = i2 + 1;
                int i4 = i3;
                while (i4 < spannableStringBuilder.length() && spannableStringBuilder.charAt(i4) == ' ') {
                    i4++;
                }
                int i5 = i4 - i3;
                if (i5 > 0) {
                    spannableStringBuilder.delete(i2, i5 + i2);
                }
            }
        }
        if (spannableStringBuilder.length() > 0 && spannableStringBuilder.charAt(0) == ' ') {
            spannableStringBuilder.delete(0, 1);
        }
        for (int i6 = 0; i6 < spannableStringBuilder.length() - 1; i6++) {
            if (spannableStringBuilder.charAt(i6) == 10) {
                int i7 = i6 + 1;
                if (spannableStringBuilder.charAt(i7) == ' ') {
                    spannableStringBuilder.delete(i7, i6 + 2);
                }
            }
        }
        if (spannableStringBuilder.length() > 0 && spannableStringBuilder.charAt(spannableStringBuilder.length() - 1) == ' ') {
            spannableStringBuilder.delete(spannableStringBuilder.length() - 1, spannableStringBuilder.length());
        }
        for (int i8 = 0; i8 < spannableStringBuilder.length() - 1; i8++) {
            if (spannableStringBuilder.charAt(i8) == ' ') {
                int i9 = i8 + 1;
                if (spannableStringBuilder.charAt(i9) == 10) {
                    spannableStringBuilder.delete(i8, i9);
                }
            }
        }
        if (spannableStringBuilder.length() > 0 && spannableStringBuilder.charAt(spannableStringBuilder.length() - 1) == 10) {
            spannableStringBuilder.delete(spannableStringBuilder.length() - 1, spannableStringBuilder.length());
        }
    }

    private void a(TreeSet treeSet, boolean z) {
        boolean equals = "p".equals(this.h);
        boolean equals2 = "div".equals(this.h);
        if (z || equals || (equals2 && this.j != null)) {
            long j2 = this.b;
            if (j2 != -9223372036854775807L) {
                treeSet.add(Long.valueOf(j2));
            }
            long j3 = this.c;
            if (j3 != -9223372036854775807L) {
                treeSet.add(Long.valueOf(j3));
            }
        }
        if (this.m != null) {
            for (int i2 = 0; i2 < this.m.size(); i2++) {
                ((TtmlNode) this.m.get(i2)).a(treeSet, z || equals);
            }
        }
    }

    private boolean a(long j2) {
        long j3 = this.b;
        if (j3 == -9223372036854775807L && this.c == -9223372036854775807L) {
            return true;
        }
        if (j3 <= j2 && this.c == -9223372036854775807L) {
            return true;
        }
        if (j3 != -9223372036854775807L || j2 >= this.c) {
            return j3 <= j2 && j2 < this.c;
        }
        return true;
    }

    public final int a() {
        List list = this.m;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public final TtmlNode a(int i2) {
        List list = this.m;
        if (list != null) {
            return (TtmlNode) list.get(i2);
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public final void a(long j2, String str, List list) {
        if (!"".equals(this.f)) {
            str = this.f;
        }
        if (!a(j2) || !"div".equals(this.h) || this.j == null) {
            for (int i2 = 0; i2 < a(); i2++) {
                a(i2).a(j2, str, list);
            }
            return;
        }
        list.add(new Pair(str, this.j));
    }

    /* access modifiers changed from: package-private */
    public final void a(long j2, Map map, Map map2, String str, Map map3) {
        if (a(j2)) {
            String str2 = "".equals(this.f) ? str : this.f;
            for (Map.Entry entry : this.l.entrySet()) {
                String str3 = (String) entry.getKey();
                int intValue = this.k.containsKey(str3) ? ((Integer) this.k.get(str3)).intValue() : 0;
                int intValue2 = ((Integer) entry.getValue()).intValue();
                if (intValue != intValue2) {
                    Cue.Builder builder = (Cue.Builder) Assertions.b((Object) (Cue.Builder) map3.get(str3));
                    int i2 = ((TtmlRegion) Assertions.b((Object) (TtmlRegion) map2.get(str2))).j;
                    TtmlStyle a2 = TtmlRenderUtil.a(this.d, this.e, map);
                    SpannableStringBuilder spannableStringBuilder = (SpannableStringBuilder) builder.a;
                    if (spannableStringBuilder == null) {
                        spannableStringBuilder = new SpannableStringBuilder();
                        builder.a = spannableStringBuilder;
                    }
                    SpannableStringBuilder spannableStringBuilder2 = spannableStringBuilder;
                    if (a2 != null) {
                        TtmlRenderUtil.a(spannableStringBuilder2, intValue, intValue2, a2, this.g, map, i2);
                        if ("p".equals(this.h)) {
                            if (a2.s != Float.MAX_VALUE) {
                                builder.m = (a2.s * -90.0f) / 100.0f;
                            }
                            if (a2.o != null) {
                                builder.c = a2.o;
                            }
                            if (a2.p != null) {
                                builder.d = a2.p;
                            }
                        }
                    }
                } else {
                    Map map4 = map;
                    Map map5 = map2;
                    Map map6 = map3;
                }
            }
            Map map7 = map;
            Map map8 = map2;
            Map map9 = map3;
            for (int i3 = 0; i3 < a(); i3++) {
                a(i3).a(j2, map, map2, str2, map3);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(long j2, boolean z, String str, Map map) {
        this.k.clear();
        this.l.clear();
        if (!ComponentDescriptorConstants.METADATA_TARGET.equals(this.h)) {
            if (!"".equals(this.f)) {
                str = this.f;
            }
            if (this.i && z) {
                a(str, map).append((CharSequence) Assertions.b((Object) this.a));
            } else if ("br".equals(this.h) && z) {
                a(str, map).append(10);
            } else if (a(j2)) {
                for (Map.Entry entry : map.entrySet()) {
                    this.k.put((String) entry.getKey(), Integer.valueOf(((CharSequence) Assertions.b((Object) ((Cue.Builder) entry.getValue()).a)).length()));
                }
                boolean equals = "p".equals(this.h);
                for (int i2 = 0; i2 < a(); i2++) {
                    a(i2).a(j2, z || equals, str, map);
                }
                if (equals) {
                    TtmlRenderUtil.a(a(str, map));
                }
                for (Map.Entry entry2 : map.entrySet()) {
                    this.l.put((String) entry2.getKey(), Integer.valueOf(((CharSequence) Assertions.b((Object) ((Cue.Builder) entry2.getValue()).a)).length()));
                }
            }
        }
    }

    public final void a(TtmlNode ttmlNode) {
        if (this.m == null) {
            this.m = new ArrayList();
        }
        this.m.add(ttmlNode);
    }

    public final long[] b() {
        TreeSet treeSet = new TreeSet();
        int i2 = 0;
        a(treeSet, false);
        long[] jArr = new long[treeSet.size()];
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            jArr[i2] = ((Long) it.next()).longValue();
            i2++;
        }
        return jArr;
    }
}
