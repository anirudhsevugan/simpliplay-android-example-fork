package com.google.android.exoplayer2.text;

import androidx.appcompat.widget.ActivityChooserView;
import com.google.android.exoplayer2.decoder.DecoderException;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.decoder.OutputBuffer;
import com.google.android.exoplayer2.decoder.SimpleDecoder;
import com.google.android.exoplayer2.util.Assertions;
import java.nio.ByteBuffer;

public abstract class SimpleSubtitleDecoder extends SimpleDecoder implements SubtitleDecoder {
    public SimpleSubtitleDecoder() {
        super(new SubtitleInputBuffer[2], new SubtitleOutputBuffer[2]);
        e();
    }

    /* access modifiers changed from: private */
    public SubtitleDecoderException a(SubtitleInputBuffer subtitleInputBuffer, SubtitleOutputBuffer subtitleOutputBuffer, boolean z) {
        try {
            ByteBuffer byteBuffer = (ByteBuffer) Assertions.b((Object) subtitleInputBuffer.c);
            subtitleOutputBuffer.a(subtitleInputBuffer.e, a(byteBuffer.array(), byteBuffer.limit(), z), subtitleInputBuffer.g);
            subtitleOutputBuffer.a &= ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            return null;
        } catch (SubtitleDecoderException e) {
            return e;
        }
    }

    public final /* synthetic */ DecoderException a(Throwable th) {
        return new SubtitleDecoderException("Unexpected decode error", th);
    }

    /* access modifiers changed from: protected */
    public abstract Subtitle a(byte[] bArr, int i, boolean z);

    public final void a(long j) {
    }

    public final /* synthetic */ DecoderInputBuffer g() {
        return new SubtitleInputBuffer();
    }

    public final /* synthetic */ OutputBuffer h() {
        return new SimpleSubtitleOutputBuffer(new SimpleSubtitleDecoder$$Lambda$0(this));
    }
}
