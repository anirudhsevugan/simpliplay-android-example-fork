package com.google.android.exoplayer2.metadata.emsg;

import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.MetadataInputBuffer;
import com.google.android.exoplayer2.metadata.SimpleMetadataDecoder;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.nio.ByteBuffer;
import java.util.Arrays;

public final class EventMessageDecoder extends SimpleMetadataDecoder {
    public static EventMessage a(ParsableByteArray parsableByteArray) {
        return new EventMessage((String) Assertions.b((Object) parsableByteArray.r()), (String) Assertions.b((Object) parsableByteArray.r()), parsableByteArray.h(), parsableByteArray.h(), Arrays.copyOfRange(parsableByteArray.a, parsableByteArray.b, parsableByteArray.c));
    }

    public final Metadata a(MetadataInputBuffer metadataInputBuffer, ByteBuffer byteBuffer) {
        return new Metadata(a(new ParsableByteArray(byteBuffer.array(), byteBuffer.limit())));
    }
}
