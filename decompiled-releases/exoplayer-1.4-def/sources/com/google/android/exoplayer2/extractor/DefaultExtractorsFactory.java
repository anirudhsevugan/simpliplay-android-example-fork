package com.google.android.exoplayer2.extractor;

import android.net.Uri;
import com.google.android.exoplayer2.extractor.amr.AmrExtractor;
import com.google.android.exoplayer2.extractor.flac.FlacExtractor;
import com.google.android.exoplayer2.extractor.flv.FlvExtractor;
import com.google.android.exoplayer2.extractor.jpeg.JpegExtractor;
import com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor;
import com.google.android.exoplayer2.extractor.mp3.Mp3Extractor;
import com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor;
import com.google.android.exoplayer2.extractor.mp4.Mp4Extractor;
import com.google.android.exoplayer2.extractor.ogg.OggExtractor;
import com.google.android.exoplayer2.extractor.ts.Ac3Extractor;
import com.google.android.exoplayer2.extractor.ts.Ac4Extractor;
import com.google.android.exoplayer2.extractor.ts.AdtsExtractor;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.android.exoplayer2.extractor.wav.WavExtractor;
import com.google.android.exoplayer2.util.FileTypes;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class DefaultExtractorsFactory implements ExtractorsFactory {
    private static final int[] b = {5, 4, 12, 8, 3, 10, 9, 11, 6, 2, 0, 1, 7, 14};
    private static final Constructor c;
    private int d = 1;
    private int e = 112800;

    static {
        ExtractorsFactory$$CC.b();
        Constructor<? extends U> constructor = null;
        try {
            if (Boolean.TRUE.equals(Class.forName("com.google.android.exoplayer2.ext.flac.FlacLibrary").getMethod("isAvailable", new Class[0]).invoke((Object) null, new Object[0]))) {
                constructor = Class.forName("com.google.android.exoplayer2.ext.flac.FlacExtractor").asSubclass(Extractor.class).getConstructor(new Class[]{Integer.TYPE});
            }
        } catch (ClassNotFoundException e2) {
        } catch (Exception e3) {
            throw new RuntimeException("Error instantiating FLAC extension", e3);
        }
        c = constructor;
    }

    private void a(int i, List list) {
        Object ac3Extractor;
        switch (i) {
            case 0:
                ac3Extractor = new Ac3Extractor();
                break;
            case 1:
                ac3Extractor = new Ac4Extractor();
                break;
            case 2:
                ac3Extractor = new AdtsExtractor((byte) 0);
                break;
            case 3:
                ac3Extractor = new AmrExtractor((byte) 0);
                break;
            case 4:
                Constructor constructor = c;
                if (constructor == null) {
                    ac3Extractor = new FlacExtractor((byte) 0);
                    break;
                } else {
                    try {
                        list.add((Extractor) constructor.newInstance(new Object[]{0}));
                        return;
                    } catch (Exception e2) {
                        throw new IllegalStateException("Unexpected error creating FLAC extractor", e2);
                    }
                }
            case 5:
                ac3Extractor = new FlvExtractor();
                break;
            case 6:
                ac3Extractor = new MatroskaExtractor(0);
                break;
            case 7:
                ac3Extractor = new Mp3Extractor((byte) 0);
                break;
            case 8:
                list.add(new FragmentedMp4Extractor((byte) 0));
                ac3Extractor = new Mp4Extractor((byte) 0);
                break;
            case 9:
                ac3Extractor = new OggExtractor();
                break;
            case 10:
                ac3Extractor = new PsExtractor();
                break;
            case 11:
                ac3Extractor = new TsExtractor(this.d, this.e);
                break;
            case 12:
                ac3Extractor = new WavExtractor();
                break;
            case 14:
                list.add(new JpegExtractor());
                return;
            default:
                return;
        }
        list.add(ac3Extractor);
    }

    public final synchronized Extractor[] a() {
        return a(Uri.EMPTY, (Map) new HashMap());
    }

    public final synchronized Extractor[] a(Uri uri, Map map) {
        ArrayList arrayList;
        arrayList = new ArrayList(14);
        int a = FileTypes.a(map);
        if (a != -1) {
            a(a, (List) arrayList);
        }
        int a2 = FileTypes.a(uri);
        if (!(a2 == -1 || a2 == a)) {
            a(a2, (List) arrayList);
        }
        int[] iArr = b;
        for (int i = 0; i < 14; i++) {
            int i2 = iArr[i];
            if (!(i2 == a || i2 == a2)) {
                a(i2, (List) arrayList);
            }
        }
        return (Extractor[]) arrayList.toArray(new Extractor[arrayList.size()]);
    }
}
