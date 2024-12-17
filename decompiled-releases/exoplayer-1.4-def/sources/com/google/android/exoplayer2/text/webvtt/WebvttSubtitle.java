package com.google.android.exoplayer2.text.webvtt;

import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class WebvttSubtitle implements Subtitle {
    private final List a;
    private final long[] b;
    private final long[] c;

    public WebvttSubtitle(List list) {
        this.a = Collections.unmodifiableList(new ArrayList(list));
        this.b = new long[(list.size() * 2)];
        for (int i = 0; i < list.size(); i++) {
            WebvttCueInfo webvttCueInfo = (WebvttCueInfo) list.get(i);
            int i2 = i << 1;
            this.b[i2] = webvttCueInfo.b;
            this.b[i2 + 1] = webvttCueInfo.c;
        }
        long[] jArr = this.b;
        long[] copyOf = Arrays.copyOf(jArr, jArr.length);
        this.c = copyOf;
        Arrays.sort(copyOf);
    }

    static final /* synthetic */ int a(WebvttCueInfo webvttCueInfo, WebvttCueInfo webvttCueInfo2) {
        return (webvttCueInfo.b > webvttCueInfo2.b ? 1 : (webvttCueInfo.b == webvttCueInfo2.b ? 0 : -1));
    }

    public final int a(long j) {
        int b2 = Util.b(this.c, j, false);
        if (b2 < this.c.length) {
            return b2;
        }
        return -1;
    }

    public final long a(int i) {
        boolean z = true;
        Assertions.a(i >= 0);
        if (i >= this.c.length) {
            z = false;
        }
        Assertions.a(z);
        return this.c[i];
    }

    public final int b() {
        return this.c.length;
    }

    public final List b(long j) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < this.a.size(); i++) {
            long[] jArr = this.b;
            int i2 = i << 1;
            if (jArr[i2] <= j && j < jArr[i2 + 1]) {
                WebvttCueInfo webvttCueInfo = (WebvttCueInfo) this.a.get(i);
                if (webvttCueInfo.a.f == -3.4028235E38f) {
                    arrayList2.add(webvttCueInfo);
                } else {
                    arrayList.add(webvttCueInfo.a);
                }
            }
        }
        Collections.sort(arrayList2, WebvttSubtitle$$Lambda$0.a);
        for (int i3 = 0; i3 < arrayList2.size(); i3++) {
            arrayList.add(((WebvttCueInfo) arrayList2.get(i3)).a.a().a((float) (-1 - i3), 1).a());
        }
        return arrayList;
    }
}
