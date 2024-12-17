package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.extractor.DummyTrackOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.source.SampleQueue;
import com.google.android.exoplayer2.source.chunk.ChunkExtractor;
import com.google.android.exoplayer2.util.Log;

public final class BaseMediaChunkOutput implements ChunkExtractor.TrackOutputProvider {
    final SampleQueue[] a;
    private final int[] b;

    public BaseMediaChunkOutput(int[] iArr, SampleQueue[] sampleQueueArr) {
        this.b = iArr;
        this.a = sampleQueueArr;
    }

    public final TrackOutput a(int i) {
        int i2 = 0;
        while (true) {
            int[] iArr = this.b;
            if (i2 >= iArr.length) {
                Log.d("BaseMediaChunkOutput", new StringBuilder(36).append("Unmatched track of type: ").append(i).toString());
                return new DummyTrackOutput();
            } else if (i == iArr[i2]) {
                return this.a[i2];
            } else {
                i2++;
            }
        }
    }

    public final void a(long j) {
        for (SampleQueue a2 : this.a) {
            a2.a(j);
        }
    }
}
