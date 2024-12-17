package com.google.android.exoplayer2.source.dash.manifest;

import android.net.Uri;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.offline.FilterableManifest;
import com.google.android.exoplayer2.offline.StreamKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DashManifest implements FilterableManifest {
    public final long a;
    public final long b;
    public final long c;
    public final boolean d;
    public final long e;
    public final long f;
    public final long g;
    public final long h;
    public final UtcTimingElement i;
    public final ServiceDescriptionElement j;
    public final Uri k;
    private ProgramInformation l;
    private final List m;

    public DashManifest(long j2, long j3, long j4, boolean z, long j5, long j6, long j7, long j8, ProgramInformation programInformation, UtcTimingElement utcTimingElement, ServiceDescriptionElement serviceDescriptionElement, Uri uri, List list) {
        this.a = j2;
        this.b = j3;
        this.c = j4;
        this.d = z;
        this.e = j5;
        this.f = j6;
        this.g = j7;
        this.h = j8;
        this.l = programInformation;
        this.i = utcTimingElement;
        this.k = uri;
        this.j = serviceDescriptionElement;
        this.m = list;
    }

    private static ArrayList a(List list, LinkedList linkedList) {
        StreamKey streamKey = (StreamKey) linkedList.poll();
        int i2 = streamKey.a;
        ArrayList arrayList = new ArrayList();
        do {
            int i3 = streamKey.b;
            AdaptationSet adaptationSet = (AdaptationSet) list.get(i3);
            List list2 = adaptationSet.c;
            ArrayList arrayList2 = new ArrayList();
            do {
                arrayList2.add((Representation) list2.get(streamKey.c));
                streamKey = (StreamKey) linkedList.poll();
                if (!(streamKey.a == i2 && streamKey.b == i3)) {
                    arrayList.add(new AdaptationSet(adaptationSet.a, adaptationSet.b, arrayList2, adaptationSet.d, adaptationSet.e, adaptationSet.f));
                }
                arrayList2.add((Representation) list2.get(streamKey.c));
                streamKey = (StreamKey) linkedList.poll();
                break;
            } while (streamKey.b == i3);
            arrayList.add(new AdaptationSet(adaptationSet.a, adaptationSet.b, arrayList2, adaptationSet.d, adaptationSet.e, adaptationSet.f));
        } while (streamKey.a == i2);
        linkedList.addFirst(streamKey);
        return arrayList;
    }

    private long c(int i2) {
        long j2;
        if (i2 == this.m.size() - 1) {
            j2 = this.b;
            if (j2 == -9223372036854775807L) {
                return -9223372036854775807L;
            }
        } else {
            j2 = ((Period) this.m.get(i2 + 1)).b;
        }
        return j2 - ((Period) this.m.get(i2)).b;
    }

    public final int a() {
        return this.m.size();
    }

    public final Period a(int i2) {
        return (Period) this.m.get(i2);
    }

    public final /* synthetic */ Object a(List list) {
        LinkedList linkedList = new LinkedList(list);
        Collections.sort(linkedList);
        linkedList.add(new StreamKey());
        ArrayList arrayList = new ArrayList();
        long j2 = 0;
        for (int i2 = 0; i2 < a(); i2++) {
            if (((StreamKey) linkedList.peek()).a != i2) {
                long c2 = c(i2);
                if (c2 != -9223372036854775807L) {
                    j2 += c2;
                }
            } else {
                Period a2 = a(i2);
                arrayList.add(new Period(a2.a, a2.b - j2, a(a2.c, linkedList), a2.d));
            }
        }
        long j3 = this.b;
        return new DashManifest(this.a, j3 != -9223372036854775807L ? j3 - j2 : -9223372036854775807L, this.c, this.d, this.e, this.f, this.g, this.h, this.l, this.i, this.j, this.k, arrayList);
    }

    public final long b(int i2) {
        return C.b(c(i2));
    }
}
