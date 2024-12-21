package com.google.android.exoplayer2.audio;

import android.os.Handler;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.decoder.DecoderReuseEvaluation;
import com.google.android.exoplayer2.util.Assertions;

public interface AudioRendererEventListener {

    public final class EventDispatcher {
        final Handler a;
        final AudioRendererEventListener b;

        public EventDispatcher(Handler handler, AudioRendererEventListener audioRendererEventListener) {
            this.a = audioRendererEventListener != null ? (Handler) Assertions.b((Object) handler) : null;
            this.b = audioRendererEventListener;
        }

        public final void a(DecoderCounters decoderCounters) {
            Handler handler = this.a;
            if (handler != null) {
                handler.post(new AudioRendererEventListener$EventDispatcher$$Lambda$6(this, decoderCounters));
            }
        }
    }

    void a(int i, long j, long j2);

    void a(long j);

    void b(Format format, DecoderReuseEvaluation decoderReuseEvaluation);

    void b(Exception exc);

    void b(String str);

    void b(String str, long j, long j2);

    void c(DecoderCounters decoderCounters);

    void c(Exception exc);

    void d(DecoderCounters decoderCounters);

    void g();

    void onSkipSilenceEnabledChanged(boolean z);
}
