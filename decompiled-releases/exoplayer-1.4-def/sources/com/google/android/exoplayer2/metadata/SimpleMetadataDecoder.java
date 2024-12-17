package com.google.android.exoplayer2.metadata;

import com.google.android.exoplayer2.util.Assertions;
import java.nio.ByteBuffer;

public abstract class SimpleMetadataDecoder implements MetadataDecoder {
    public final Metadata a(MetadataInputBuffer metadataInputBuffer) {
        ByteBuffer byteBuffer = (ByteBuffer) Assertions.b((Object) metadataInputBuffer.c);
        Assertions.a(byteBuffer.position() == 0 && byteBuffer.hasArray() && byteBuffer.arrayOffset() == 0);
        if (metadataInputBuffer.d_()) {
            return null;
        }
        return a(metadataInputBuffer, byteBuffer);
    }

    /* access modifiers changed from: protected */
    public abstract Metadata a(MetadataInputBuffer metadataInputBuffer, ByteBuffer byteBuffer);
}
