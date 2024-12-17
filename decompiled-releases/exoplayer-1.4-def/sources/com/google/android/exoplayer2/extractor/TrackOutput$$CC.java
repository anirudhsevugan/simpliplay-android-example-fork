package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.upstream.DataReader;
import com.google.android.exoplayer2.util.ParsableByteArray;

public abstract /* synthetic */ class TrackOutput$$CC {
    public static int a(TrackOutput trackOutput, DataReader dataReader, int i, boolean z) {
        return trackOutput.a(dataReader, i, z);
    }

    public static void a(TrackOutput trackOutput, ParsableByteArray parsableByteArray, int i) {
        trackOutput.a(parsableByteArray, i);
    }
}
