package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.SeekParameters;
import java.util.List;

public interface ChunkSource {
    int a(long j, List list);

    long a(long j, SeekParameters seekParameters);

    void a();

    void a(long j, long j2, List list, ChunkHolder chunkHolder);

    void a(Chunk chunk);

    boolean a(Chunk chunk, boolean z, Exception exc, long j);

    boolean b();
}
