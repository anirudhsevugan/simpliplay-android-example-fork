package com.google.android.exoplayer2.source.hls.playlist;

import android.net.Uri;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.offline.StreamKey;
import com.google.appinventor.components.runtime.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class HlsMasterPlaylist extends HlsPlaylist {
    public static final HlsMasterPlaylist a = new HlsMasterPlaylist("", Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), (Format) null, Collections.emptyList(), false, Collections.emptyMap(), Collections.emptyList());
    public final List b;
    public final List c;
    public final List d;
    public final List e;
    public final Format f;
    public final List g;
    public final Map h;
    public final List i;

    public final class Rendition {
        public final Uri a;
        public final Format b;
        public final String c;

        public Rendition(Uri uri, Format format, String str) {
            this.a = uri;
            this.b = format;
            this.c = str;
        }
    }

    public final class Variant {
        public final Uri a;
        public final Format b;
        public final String c;
        public final String d;
        public final String e;
        public final String f;

        public Variant(Uri uri, Format format, String str, String str2, String str3, String str4) {
            this.a = uri;
            this.b = format;
            this.c = str;
            this.d = str2;
            this.e = str3;
            this.f = str4;
        }

        public static Variant a(Uri uri) {
            Format.Builder builder = new Format.Builder();
            builder.a = Component.TYPEFACE_DEFAULT;
            builder.j = "application/x-mpegURL";
            return new Variant(uri, builder.a(), (String) null, (String) null, (String) null, (String) null);
        }
    }

    public HlsMasterPlaylist(String str, List list, List list2, List list3, List list4, List list5, List list6, Format format, List list7, boolean z, Map map, List list8) {
        super(str, list, z);
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list2.size(); i2++) {
            Uri uri = ((Variant) list2.get(i2)).a;
            if (!arrayList.contains(uri)) {
                arrayList.add(uri);
            }
        }
        a(list3, arrayList);
        a(list4, arrayList);
        a(list5, arrayList);
        a(list6, arrayList);
        this.b = Collections.unmodifiableList(arrayList);
        this.c = Collections.unmodifiableList(list2);
        Collections.unmodifiableList(list3);
        this.d = Collections.unmodifiableList(list4);
        this.e = Collections.unmodifiableList(list5);
        Collections.unmodifiableList(list6);
        this.f = format;
        this.g = list7 != null ? Collections.unmodifiableList(list7) : null;
        this.h = Collections.unmodifiableMap(map);
        this.i = Collections.unmodifiableList(list8);
    }

    public static HlsMasterPlaylist a(String str) {
        return new HlsMasterPlaylist("", Collections.emptyList(), Collections.singletonList(Variant.a(Uri.parse(str))), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), (Format) null, (List) null, false, Collections.emptyMap(), Collections.emptyList());
    }

    private static List a(List list, int i2, List list2) {
        ArrayList arrayList = new ArrayList(list2.size());
        for (int i3 = 0; i3 < list.size(); i3++) {
            Object obj = list.get(i3);
            int i4 = 0;
            while (true) {
                if (i4 >= list2.size()) {
                    break;
                }
                StreamKey streamKey = (StreamKey) list2.get(i4);
                if (streamKey.b == i2 && streamKey.c == i3) {
                    arrayList.add(obj);
                    break;
                }
                i4++;
            }
        }
        return arrayList;
    }

    private static void a(List list, List list2) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            Uri uri = ((Rendition) list.get(i2)).a;
            if (uri != null && !list2.contains(uri)) {
                list2.add(uri);
            }
        }
    }

    public final /* synthetic */ Object a(List list) {
        return new HlsMasterPlaylist(this.s, this.t, a(this.c, 0, list), Collections.emptyList(), a(this.d, 1, list), a(this.e, 2, list), Collections.emptyList(), this.f, this.g, this.u, this.h, this.i);
    }
}
