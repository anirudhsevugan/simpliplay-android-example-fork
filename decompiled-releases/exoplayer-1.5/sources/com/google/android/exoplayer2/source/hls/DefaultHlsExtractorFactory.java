package com.google.android.exoplayer2.source.hls;

import android.net.Uri;
import android.text.TextUtils;
import com.dreamers.exoplayercore.repack.cR;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.mp3.Mp3Extractor;
import com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor;
import com.google.android.exoplayer2.extractor.ts.Ac3Extractor;
import com.google.android.exoplayer2.extractor.ts.Ac4Extractor;
import com.google.android.exoplayer2.extractor.ts.AdtsExtractor;
import com.google.android.exoplayer2.extractor.ts.DefaultTsPayloadReaderFactory;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.FileTypes;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import java.io.EOFException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class DefaultHlsExtractorFactory implements HlsExtractorFactory {
    private static final int[] b = {8, 13, 11, 2, 0, 1, 7};

    public DefaultHlsExtractorFactory() {
        this((byte) 0);
    }

    private DefaultHlsExtractorFactory(byte b2) {
    }

    private static void a(int i, List list) {
        if (cR.a(b, i, 0, 7) != -1 && !list.contains(Integer.valueOf(i))) {
            list.add(Integer.valueOf(i));
        }
    }

    private static boolean a(Format format) {
        Metadata metadata = format.j;
        if (metadata == null) {
            return false;
        }
        for (Metadata.Entry entry : metadata.a) {
            if (entry instanceof HlsTrackMetadataEntry) {
                return !((HlsTrackMetadataEntry) entry).a.isEmpty();
            }
        }
        return false;
    }

    /* JADX INFO: finally extract failed */
    private static boolean a(Extractor extractor, ExtractorInput extractorInput) {
        try {
            boolean a = extractor.a(extractorInput);
            extractorInput.a();
            return a;
        } catch (EOFException e) {
            extractorInput.a();
            return false;
        } catch (Throwable th) {
            extractorInput.a();
            throw th;
        }
    }

    public final /* synthetic */ HlsMediaChunkExtractor a(Uri uri, Format format, List list, TimestampAdjuster timestampAdjuster, Map map, ExtractorInput extractorInput) {
        Object obj;
        List list2;
        int i;
        Format format2 = format;
        TimestampAdjuster timestampAdjuster2 = timestampAdjuster;
        int a = FileTypes.a(format2.l);
        int a2 = FileTypes.a(map);
        int a3 = FileTypes.a(uri);
        ArrayList arrayList = new ArrayList(7);
        a(a, (List) arrayList);
        a(a2, (List) arrayList);
        a(a3, (List) arrayList);
        int[] iArr = b;
        for (int i2 = 0; i2 < 7; i2++) {
            a(iArr[i2], (List) arrayList);
        }
        extractorInput.a();
        Extractor extractor = null;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            int intValue = ((Integer) arrayList.get(i3)).intValue();
            switch (intValue) {
                case 0:
                    obj = new Ac3Extractor();
                    break;
                case 1:
                    obj = new Ac4Extractor();
                    break;
                case 2:
                    obj = new AdtsExtractor();
                    break;
                case 7:
                    obj = new Mp3Extractor(0);
                    break;
                case 8:
                    obj = new FragmentedMp4Extractor(a(format) ? 4 : 0, timestampAdjuster2, list != null ? list : Collections.emptyList());
                    break;
                case 11:
                    if (list != null) {
                        i = 48;
                        list2 = list;
                    } else {
                        Format.Builder builder = new Format.Builder();
                        builder.k = "application/cea-608";
                        list2 = Collections.singletonList(builder.a());
                        i = 16;
                    }
                    String str = format2.i;
                    if (!TextUtils.isEmpty(str)) {
                        if (!MimeTypes.b(str, "audio/mp4a-latm")) {
                            i |= 2;
                        }
                        if (!MimeTypes.b(str, "video/avc")) {
                            i |= 4;
                        }
                    }
                    obj = new TsExtractor(timestampAdjuster2, (TsPayloadReader.Factory) new DefaultTsPayloadReaderFactory(i, list2));
                    break;
                case 13:
                    obj = new WebvttExtractor(format2.c, timestampAdjuster2);
                    break;
                default:
                    obj = null;
                    break;
            }
            Extractor extractor2 = (Extractor) Assertions.b(obj);
            if (a(extractor2, extractorInput)) {
                return new BundledHlsMediaChunkExtractor(extractor2, format2, timestampAdjuster2);
            }
            if (extractor == null && (intValue == a || intValue == a2 || intValue == a3 || intValue == 11)) {
                extractor = extractor2;
            }
        }
        return new BundledHlsMediaChunkExtractor((Extractor) Assertions.b((Object) extractor), format2, timestampAdjuster2);
    }
}
