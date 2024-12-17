package com.google.android.exoplayer2.source;

public interface CompositeSequenceableLoaderFactory {
    SequenceableLoader a(SequenceableLoader... sequenceableLoaderArr);
}
