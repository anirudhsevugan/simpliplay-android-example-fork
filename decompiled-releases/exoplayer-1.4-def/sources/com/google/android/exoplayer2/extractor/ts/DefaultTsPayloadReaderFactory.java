package com.google.android.exoplayer2.extractor.ts;

import android.util.SparseArray;
import androidx.core.view.InputDeviceCompat;
import com.dreamers.exoplayercore.repack.bG;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.util.ArrayList;
import java.util.List;

public final class DefaultTsPayloadReaderFactory implements TsPayloadReader.Factory {
    private final int a;
    private final List b;

    public DefaultTsPayloadReaderFactory() {
        this((byte) 0);
    }

    public DefaultTsPayloadReaderFactory(byte b2) {
        this(0, bG.g());
    }

    public DefaultTsPayloadReaderFactory(int i, List list) {
        this.a = i;
        this.b = list;
    }

    private SeiReader a(TsPayloadReader.EsInfo esInfo) {
        return new SeiReader(c(esInfo));
    }

    private boolean a(int i) {
        return (i & this.a) != 0;
    }

    private UserDataReader b(TsPayloadReader.EsInfo esInfo) {
        return new UserDataReader(c(esInfo));
    }

    private List c(TsPayloadReader.EsInfo esInfo) {
        String str;
        int i;
        List list;
        if (a(32)) {
            return this.b;
        }
        ParsableByteArray parsableByteArray = new ParsableByteArray(esInfo.d);
        List list2 = this.b;
        while (parsableByteArray.a() > 0) {
            int c = parsableByteArray.c();
            int c2 = parsableByteArray.b + parsableByteArray.c();
            if (c == 134) {
                list2 = new ArrayList();
                int c3 = parsableByteArray.c() & 31;
                for (int i2 = 0; i2 < c3; i2++) {
                    String f = parsableByteArray.f(3);
                    int c4 = parsableByteArray.c();
                    boolean z = true;
                    boolean z2 = (c4 & 128) != 0;
                    if (z2) {
                        i = c4 & 63;
                        str = "application/cea-708";
                    } else {
                        str = "application/cea-608";
                        i = 1;
                    }
                    byte c5 = (byte) parsableByteArray.c();
                    parsableByteArray.e(1);
                    if (z2) {
                        if ((c5 & Ev3Constants.Opcode.JR) == 0) {
                            z = false;
                        }
                        list = CodecSpecificDataUtil.a(z);
                    } else {
                        list = null;
                    }
                    Format.Builder builder = new Format.Builder();
                    builder.k = str;
                    builder.c = f;
                    builder.C = i;
                    builder.m = list;
                    list2.add(builder.a());
                }
            }
            parsableByteArray.d(c2);
        }
        return list2;
    }

    public final SparseArray a() {
        return new SparseArray();
    }

    public final TsPayloadReader a(int i, TsPayloadReader.EsInfo esInfo) {
        switch (i) {
            case 2:
                return new PesReader(new H262Reader(b(esInfo)));
            case 3:
            case 4:
                return new PesReader(new MpegAudioReader(esInfo.b));
            case 15:
                if (a(2)) {
                    return null;
                }
                return new PesReader(new AdtsReader(false, esInfo.b));
            case 16:
                return new PesReader(new H263Reader(b(esInfo)));
            case 17:
                if (a(2)) {
                    return null;
                }
                return new PesReader(new LatmReader(esInfo.b));
            case 21:
                return new PesReader(new Id3Reader());
            case 27:
                if (a(4)) {
                    return null;
                }
                return new PesReader(new H264Reader(a(esInfo), a(1), a(8)));
            case 36:
                return new PesReader(new H265Reader(a(esInfo)));
            case 89:
                return new PesReader(new DvbSubtitleReader(esInfo.c));
            case 129:
            case 135:
                return new PesReader(new Ac3Reader(esInfo.b));
            case 130:
                if (!a(64)) {
                    return null;
                }
                break;
            case 134:
                if (a(16)) {
                    return null;
                }
                return new SectionReader(new PassthroughSectionPayloadReader("application/x-scte35"));
            case 138:
                break;
            case 172:
                return new PesReader(new Ac4Reader(esInfo.b));
            case InputDeviceCompat.SOURCE_KEYBOARD:
                return new SectionReader(new PassthroughSectionPayloadReader("application/vnd.dvb.ait"));
            default:
                return null;
        }
        return new PesReader(new DtsReader(esInfo.b));
    }
}
