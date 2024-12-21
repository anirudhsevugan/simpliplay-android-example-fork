package com.google.android.exoplayer2.extractor.flv;

import com.google.android.exoplayer2.extractor.DummyTrackOutput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class ScriptTagPayloadReader extends TagPayloadReader {
    long a = -9223372036854775807L;
    long[] b = new long[0];
    long[] c = new long[0];

    public ScriptTagPayloadReader() {
        super(new DummyTrackOutput());
    }

    private static Object a(ParsableByteArray parsableByteArray, int i) {
        switch (i) {
            case 0:
                return c(parsableByteArray);
            case 1:
                return b(parsableByteArray);
            case 2:
                return d(parsableByteArray);
            case 3:
                return f(parsableByteArray);
            case 8:
                return g(parsableByteArray);
            case 10:
                return e(parsableByteArray);
            case 11:
                return h(parsableByteArray);
            default:
                return null;
        }
    }

    private static Boolean b(ParsableByteArray parsableByteArray) {
        boolean z = true;
        if (parsableByteArray.c() != 1) {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    private static Double c(ParsableByteArray parsableByteArray) {
        return Double.valueOf(Double.longBitsToDouble(parsableByteArray.l()));
    }

    private static String d(ParsableByteArray parsableByteArray) {
        int d = parsableByteArray.d();
        int i = parsableByteArray.b;
        parsableByteArray.e(d);
        return new String(parsableByteArray.a, i, d);
    }

    private static ArrayList e(ParsableByteArray parsableByteArray) {
        int o = parsableByteArray.o();
        ArrayList arrayList = new ArrayList(o);
        for (int i = 0; i < o; i++) {
            Object a2 = a(parsableByteArray, parsableByteArray.c());
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        return arrayList;
    }

    private static HashMap f(ParsableByteArray parsableByteArray) {
        HashMap hashMap = new HashMap();
        while (true) {
            String d = d(parsableByteArray);
            int c2 = parsableByteArray.c();
            if (c2 == 9) {
                return hashMap;
            }
            Object a2 = a(parsableByteArray, c2);
            if (a2 != null) {
                hashMap.put(d, a2);
            }
        }
    }

    private static HashMap g(ParsableByteArray parsableByteArray) {
        int o = parsableByteArray.o();
        HashMap hashMap = new HashMap(o);
        for (int i = 0; i < o; i++) {
            String d = d(parsableByteArray);
            Object a2 = a(parsableByteArray, parsableByteArray.c());
            if (a2 != null) {
                hashMap.put(d, a2);
            }
        }
        return hashMap;
    }

    private static Date h(ParsableByteArray parsableByteArray) {
        Date date = new Date((long) c(parsableByteArray).doubleValue());
        parsableByteArray.e(2);
        return date;
    }

    /* access modifiers changed from: protected */
    public final boolean a(ParsableByteArray parsableByteArray) {
        return true;
    }

    /* access modifiers changed from: protected */
    public final boolean a(ParsableByteArray parsableByteArray, long j) {
        if (parsableByteArray.c() != 2 || !"onMetaData".equals(d(parsableByteArray)) || parsableByteArray.c() != 8) {
            return false;
        }
        HashMap g = g(parsableByteArray);
        Object obj = g.get("duration");
        if (obj instanceof Double) {
            double doubleValue = ((Double) obj).doubleValue();
            if (doubleValue > 0.0d) {
                this.a = (long) (doubleValue * 1000000.0d);
            }
        }
        Object obj2 = g.get("keyframes");
        if (obj2 instanceof Map) {
            Map map = (Map) obj2;
            Object obj3 = map.get("filepositions");
            Object obj4 = map.get("times");
            if ((obj3 instanceof List) && (obj4 instanceof List)) {
                List list = (List) obj3;
                List list2 = (List) obj4;
                int size = list2.size();
                this.b = new long[size];
                this.c = new long[size];
                int i = 0;
                while (true) {
                    if (i >= size) {
                        break;
                    }
                    Object obj5 = list.get(i);
                    Object obj6 = list2.get(i);
                    if (!(obj6 instanceof Double) || !(obj5 instanceof Double)) {
                        this.b = new long[0];
                        this.c = new long[0];
                    } else {
                        this.b[i] = (long) (((Double) obj6).doubleValue() * 1000000.0d);
                        this.c[i] = ((Double) obj5).longValue();
                        i++;
                    }
                }
                this.b = new long[0];
                this.c = new long[0];
            }
        }
        return false;
    }
}
