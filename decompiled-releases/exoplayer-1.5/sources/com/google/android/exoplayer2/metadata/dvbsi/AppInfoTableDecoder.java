package com.google.android.exoplayer2.metadata.dvbsi;

import com.dreamers.exoplayercore.repack.aC;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.MetadataInputBuffer;
import com.google.android.exoplayer2.metadata.SimpleMetadataDecoder;
import com.google.android.exoplayer2.util.ParsableBitArray;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public final class AppInfoTableDecoder extends SimpleMetadataDecoder {
    public final Metadata a(MetadataInputBuffer metadataInputBuffer, ByteBuffer byteBuffer) {
        if (byteBuffer.get() == 116) {
            ParsableBitArray parsableBitArray = new ParsableBitArray(byteBuffer.array(), byteBuffer.limit());
            parsableBitArray.b(12);
            int c = (parsableBitArray.c() + parsableBitArray.c(12)) - 4;
            parsableBitArray.b(44);
            parsableBitArray.d(parsableBitArray.c(12));
            parsableBitArray.b(16);
            ArrayList arrayList = new ArrayList();
            while (parsableBitArray.c() < c) {
                parsableBitArray.b(48);
                int c2 = parsableBitArray.c(8);
                parsableBitArray.b(4);
                int c3 = parsableBitArray.c() + parsableBitArray.c(12);
                String str = null;
                String str2 = null;
                while (parsableBitArray.c() < c3) {
                    int c4 = parsableBitArray.c(8);
                    int c5 = parsableBitArray.c(8);
                    int c6 = parsableBitArray.c() + c5;
                    if (c4 == 2) {
                        int c7 = parsableBitArray.c(16);
                        parsableBitArray.b(8);
                        if (c7 != 3) {
                        }
                        while (parsableBitArray.c() < c6) {
                            str = parsableBitArray.a(parsableBitArray.c(8), aC.a);
                            int c8 = parsableBitArray.c(8);
                            for (int i = 0; i < c8; i++) {
                                parsableBitArray.d(parsableBitArray.c(8));
                            }
                        }
                    } else if (c4 == 21) {
                        str2 = parsableBitArray.a(c5, aC.a);
                    }
                    parsableBitArray.a(c6 << 3);
                }
                parsableBitArray.a(c3 << 3);
                if (!(str == null || str2 == null)) {
                    String valueOf = String.valueOf(str);
                    String valueOf2 = String.valueOf(str2);
                    arrayList.add(new AppInfoTable(c2, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)));
                }
            }
            if (!arrayList.isEmpty()) {
                return new Metadata((List) arrayList);
            }
        }
        return null;
    }
}
