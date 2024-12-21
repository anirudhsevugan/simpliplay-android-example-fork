package com.google.android.exoplayer2.text.ttml;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.SpannableStringBuilder;
import android.util.Base64;
import android.util.Pair;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

final class TtmlSubtitle implements Subtitle {
    private final TtmlNode a;
    private final long[] b;
    private final Map c;
    private final Map d;
    private final Map e;

    public TtmlSubtitle(TtmlNode ttmlNode, Map map, Map map2, Map map3) {
        this.a = ttmlNode;
        this.d = map2;
        this.e = map3;
        this.c = Collections.unmodifiableMap(map);
        this.b = ttmlNode.b();
    }

    public final int a(long j) {
        int b2 = Util.b(this.b, j, false);
        if (b2 < this.b.length) {
            return b2;
        }
        return -1;
    }

    public final long a(int i) {
        return this.b[i];
    }

    public final int b() {
        return this.b.length;
    }

    public final List b(long j) {
        TtmlNode ttmlNode = this.a;
        Map map = this.c;
        Map map2 = this.d;
        Map map3 = this.e;
        ArrayList<Pair> arrayList = new ArrayList<>();
        ttmlNode.a(j, ttmlNode.f, arrayList);
        TreeMap treeMap = new TreeMap();
        TtmlNode ttmlNode2 = ttmlNode;
        long j2 = j;
        ttmlNode2.a(j2, false, ttmlNode.f, treeMap);
        ttmlNode2.a(j2, map, map2, ttmlNode.f, treeMap);
        ArrayList arrayList2 = new ArrayList();
        for (Pair pair : arrayList) {
            String str = (String) map3.get(pair.second);
            if (str != null) {
                byte[] decode = Base64.decode(str, 0);
                Bitmap decodeByteArray = BitmapFactory.decodeByteArray(decode, 0, decode.length);
                TtmlRegion ttmlRegion = (TtmlRegion) Assertions.b((Object) (TtmlRegion) map2.get(pair.first));
                Cue.Builder builder = new Cue.Builder();
                builder.b = decodeByteArray;
                builder.f = ttmlRegion.b;
                builder.g = 0;
                Cue.Builder a2 = builder.a(ttmlRegion.c, 0);
                a2.e = ttmlRegion.e;
                a2.h = ttmlRegion.f;
                a2.i = ttmlRegion.g;
                a2.l = ttmlRegion.j;
                arrayList2.add(a2.a());
            }
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            TtmlRegion ttmlRegion2 = (TtmlRegion) Assertions.b((Object) (TtmlRegion) map2.get(entry.getKey()));
            Cue.Builder builder2 = (Cue.Builder) entry.getValue();
            TtmlNode.a((SpannableStringBuilder) Assertions.b((Object) builder2.a));
            builder2.a(ttmlRegion2.c, ttmlRegion2.d);
            builder2.e = ttmlRegion2.e;
            builder2.f = ttmlRegion2.b;
            builder2.h = ttmlRegion2.f;
            builder2.b(ttmlRegion2.i, ttmlRegion2.h);
            builder2.l = ttmlRegion2.j;
            arrayList2.add(builder2.a());
        }
        return arrayList2;
    }
}
