package com.google.android.exoplayer2.extractor.mp3;

import com.google.android.exoplayer2.extractor.SeekMap;

interface Seeker extends SeekMap {

    public class UnseekableSeeker extends SeekMap.Unseekable implements Seeker {
        public UnseekableSeeker() {
            super(-9223372036854775807L);
        }

        public final long c() {
            return -1;
        }

        public final long c(long j) {
            return 0;
        }
    }

    long c();

    long c(long j);
}
