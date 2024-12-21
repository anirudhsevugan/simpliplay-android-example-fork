package com.google.android.exoplayer2.text.cea;

import com.google.android.exoplayer2.decoder.OutputBuffer;
import com.google.android.exoplayer2.text.SubtitleOutputBuffer;
import com.google.android.exoplayer2.text.cea.CeaDecoder;

final /* synthetic */ class CeaDecoder$$Lambda$0 implements OutputBuffer.Owner {
    private final CeaDecoder a;

    CeaDecoder$$Lambda$0(CeaDecoder ceaDecoder) {
        this.a = ceaDecoder;
    }

    public final void a(OutputBuffer outputBuffer) {
        CeaDecoder ceaDecoder = this.a;
        SubtitleOutputBuffer subtitleOutputBuffer = (CeaDecoder.CeaOutputBuffer) outputBuffer;
        subtitleOutputBuffer.a();
        ceaDecoder.a.add(subtitleOutputBuffer);
    }
}
