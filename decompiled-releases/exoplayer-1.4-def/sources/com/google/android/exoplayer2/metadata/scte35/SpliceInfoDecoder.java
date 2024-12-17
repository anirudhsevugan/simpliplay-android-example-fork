package com.google.android.exoplayer2.metadata.scte35;

import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.MetadataInputBuffer;
import com.google.android.exoplayer2.metadata.SimpleMetadataDecoder;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import java.nio.ByteBuffer;

public final class SpliceInfoDecoder extends SimpleMetadataDecoder {
    private final ParsableByteArray a = new ParsableByteArray();
    private final ParsableBitArray b = new ParsableBitArray();
    private TimestampAdjuster c;

    public final Metadata a(MetadataInputBuffer metadataInputBuffer, ByteBuffer byteBuffer) {
        Metadata.Entry entry;
        if (this.c == null || metadataInputBuffer.g != this.c.c()) {
            TimestampAdjuster timestampAdjuster = new TimestampAdjuster(metadataInputBuffer.e);
            this.c = timestampAdjuster;
            timestampAdjuster.c(metadataInputBuffer.e - metadataInputBuffer.g);
        }
        byte[] array = byteBuffer.array();
        int limit = byteBuffer.limit();
        this.a.a(array, limit);
        this.b.a(array, limit);
        this.b.b(39);
        long c2 = (((long) this.b.c(1)) << 32) | ((long) this.b.c(32));
        this.b.b(20);
        int c3 = this.b.c(12);
        int c4 = this.b.c(8);
        this.a.e(14);
        switch (c4) {
            case 0:
                entry = new SpliceNullCommand();
                break;
            case 4:
                entry = SpliceScheduleCommand.a(this.a);
                break;
            case 5:
                entry = SpliceInsertCommand.a(this.a, c2, this.c);
                break;
            case 6:
                entry = TimeSignalCommand.a(this.a, c2, this.c);
                break;
            case 255:
                entry = PrivateCommand.a(this.a, c3, c2);
                break;
            default:
                entry = null;
                break;
        }
        if (entry == null) {
            return new Metadata(new Metadata.Entry[0]);
        }
        return new Metadata(entry);
    }
}
