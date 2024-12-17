package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.ParsableByteArray;

public interface ElementaryStreamReader {
    void a();

    void a(long j, int i);

    void a(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator);

    void a(ParsableByteArray parsableByteArray);

    void b();
}
