package com.google.android.exoplayer2.trackselection;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.chunk.MediaChunkIterator;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import java.util.List;

public interface ExoTrackSelection extends TrackSelection {

    public final class Definition {
        public final TrackGroup a;
        public final int[] b;
        public final int c;

        public Definition(TrackGroup trackGroup, int... iArr) {
            this(trackGroup, iArr, 0);
        }

        public Definition(TrackGroup trackGroup, int[] iArr, int i) {
            this.a = trackGroup;
            this.b = iArr;
            this.c = i;
        }
    }

    public interface Factory {
        ExoTrackSelection[] a(Definition[] definitionArr, BandwidthMeter bandwidthMeter);
    }

    int a();

    int a(long j, List list);

    void a(float f);

    void a(long j, long j2, List list, MediaChunkIterator[] mediaChunkIteratorArr);

    boolean a(int i, long j);

    int b();

    void c();

    void d();

    Format g();

    int h();

    void i();

    void j();

    void k();

    boolean l();
}
