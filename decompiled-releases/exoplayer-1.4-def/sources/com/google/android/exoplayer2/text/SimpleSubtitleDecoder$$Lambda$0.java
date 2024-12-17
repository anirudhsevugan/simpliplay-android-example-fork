package com.google.android.exoplayer2.text;

import com.google.android.exoplayer2.decoder.OutputBuffer;

final /* synthetic */ class SimpleSubtitleDecoder$$Lambda$0 implements OutputBuffer.Owner {
    private final SimpleSubtitleDecoder a;

    SimpleSubtitleDecoder$$Lambda$0(SimpleSubtitleDecoder simpleSubtitleDecoder) {
        this.a = simpleSubtitleDecoder;
    }

    public final void a(OutputBuffer outputBuffer) {
        SimpleSubtitleDecoder simpleSubtitleDecoder = this.a;
        OutputBuffer outputBuffer2 = (SubtitleOutputBuffer) outputBuffer;
        synchronized (simpleSubtitleDecoder.a) {
            outputBuffer2.a();
            OutputBuffer[] outputBufferArr = simpleSubtitleDecoder.b;
            int i = simpleSubtitleDecoder.c;
            simpleSubtitleDecoder.c = i + 1;
            outputBufferArr[i] = outputBuffer2;
            simpleSubtitleDecoder.f();
        }
    }
}
