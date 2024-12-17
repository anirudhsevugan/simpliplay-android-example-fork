package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.flac.VorbisComment;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class FlacStreamMetadata {
    public final int a;
    public final int b;
    public final int c;
    public final int d;
    public final int e;
    public final int f;
    public final int g;
    public final int h;
    public final int i;
    public final long j;
    public final SeekTable k;
    private final Metadata l;

    public class SeekTable {
        public final long[] a;
        public final long[] b;

        public SeekTable(long[] jArr, long[] jArr2) {
            this.a = jArr;
            this.b = jArr2;
        }
    }

    FlacStreamMetadata(int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j2, SeekTable seekTable, Metadata metadata) {
        this.a = i2;
        this.b = i3;
        this.c = i4;
        this.d = i5;
        this.e = i6;
        this.f = a(i6);
        this.g = i7;
        this.h = i8;
        this.i = b(i8);
        this.j = j2;
        this.k = seekTable;
        this.l = metadata;
    }

    public FlacStreamMetadata(byte[] bArr, int i2) {
        ParsableBitArray parsableBitArray = new ParsableBitArray(bArr);
        parsableBitArray.a(i2 << 3);
        this.a = parsableBitArray.c(16);
        this.b = parsableBitArray.c(16);
        this.c = parsableBitArray.c(24);
        this.d = parsableBitArray.c(24);
        int c2 = parsableBitArray.c(20);
        this.e = c2;
        this.f = a(c2);
        this.g = parsableBitArray.c(3) + 1;
        int c3 = parsableBitArray.c(5) + 1;
        this.h = c3;
        this.i = b(c3);
        this.j = Util.b(parsableBitArray.c(4), parsableBitArray.c(32));
        this.k = null;
        this.l = null;
    }

    private static int a(int i2) {
        switch (i2) {
            case 8000:
                return 4;
            case 16000:
                return 5;
            case 22050:
                return 6;
            case 24000:
                return 7;
            case 32000:
                return 8;
            case 44100:
                return 9;
            case 48000:
                return 10;
            case 88200:
                return 1;
            case 96000:
                return 11;
            case 176400:
                return 2;
            case 192000:
                return 3;
            default:
                return -1;
        }
    }

    static Metadata a(List list, List list2) {
        if (list.isEmpty() && list2.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            String str = (String) list.get(i2);
            String[] b2 = Util.b(str, "=");
            if (b2.length != 2) {
                String valueOf = String.valueOf(str);
                Log.c("FlacStreamMetadata", valueOf.length() != 0 ? "Failed to parse Vorbis comment: ".concat(valueOf) : new String("Failed to parse Vorbis comment: "));
            } else {
                arrayList.add(new VorbisComment(b2[0], b2[1]));
            }
        }
        arrayList.addAll(list2);
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata((List) arrayList);
    }

    private static int b(int i2) {
        switch (i2) {
            case 8:
                return 1;
            case 12:
                return 2;
            case 16:
                return 4;
            case 20:
                return 5;
            case 24:
                return 6;
            default:
                return -1;
        }
    }

    public final long a() {
        long j2 = this.j;
        if (j2 == 0) {
            return -9223372036854775807L;
        }
        return (j2 * 1000000) / ((long) this.e);
    }

    public final long a(long j2) {
        return Util.a((j2 * ((long) this.e)) / 1000000, 0, this.j - 1);
    }

    public final Format a(byte[] bArr, Metadata metadata) {
        bArr[4] = Byte.MIN_VALUE;
        int i2 = this.d;
        if (i2 <= 0) {
            i2 = -1;
        }
        Metadata a2 = a(metadata);
        Format.Builder builder = new Format.Builder();
        builder.k = "audio/flac";
        builder.l = i2;
        builder.x = this.g;
        builder.y = this.e;
        builder.m = Collections.singletonList(bArr);
        builder.i = a2;
        return builder.a();
    }

    public final FlacStreamMetadata a(SeekTable seekTable) {
        return new FlacStreamMetadata(this.a, this.b, this.c, this.d, this.e, this.g, this.h, this.j, seekTable, this.l);
    }

    public final Metadata a(Metadata metadata) {
        Metadata metadata2 = this.l;
        return metadata2 == null ? metadata : metadata2.a(metadata);
    }
}
