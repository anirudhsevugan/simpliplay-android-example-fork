package com.google.android.exoplayer2.extractor.flv;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.ParsableByteArray;

abstract class TagPayloadReader {
    protected final TrackOutput d;

    public final class UnsupportedFormatException extends ParserException {
        public UnsupportedFormatException(String str) {
            super(str);
        }
    }

    protected TagPayloadReader(TrackOutput trackOutput) {
        this.d = trackOutput;
    }

    /* access modifiers changed from: protected */
    public abstract boolean a(ParsableByteArray parsableByteArray);

    /* access modifiers changed from: protected */
    public abstract boolean a(ParsableByteArray parsableByteArray, long j);

    public final boolean b(ParsableByteArray parsableByteArray, long j) {
        return a(parsableByteArray) && a(parsableByteArray, j);
    }
}
