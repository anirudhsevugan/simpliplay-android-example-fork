package com.google.android.exoplayer2.text;

import com.google.android.exoplayer2.decoder.OutputBuffer;

final class SimpleSubtitleOutputBuffer extends SubtitleOutputBuffer {
    private final OutputBuffer.Owner c;

    public SimpleSubtitleOutputBuffer(OutputBuffer.Owner owner) {
        this.c = owner;
    }

    public final void f() {
        this.c.a(this);
    }
}
