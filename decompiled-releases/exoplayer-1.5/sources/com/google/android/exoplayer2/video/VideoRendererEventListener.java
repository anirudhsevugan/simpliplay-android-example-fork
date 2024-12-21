package com.google.android.exoplayer2.video;

import android.os.Handler;
import android.os.SystemClock;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.decoder.DecoderReuseEvaluation;
import com.google.android.exoplayer2.util.Assertions;

public interface VideoRendererEventListener {

    public final class EventDispatcher {
        final Handler a;
        final VideoRendererEventListener b;

        public EventDispatcher(Handler handler, VideoRendererEventListener videoRendererEventListener) {
            this.a = videoRendererEventListener != null ? (Handler) Assertions.b((Object) handler) : null;
            this.b = videoRendererEventListener;
        }

        public final void a(int i, long j) {
            Handler handler = this.a;
            if (handler != null) {
                handler.post(new VideoRendererEventListener$EventDispatcher$$Lambda$3(this, i, j));
            }
        }

        public final void a(DecoderCounters decoderCounters) {
            Handler handler = this.a;
            if (handler != null) {
                handler.post(new VideoRendererEventListener$EventDispatcher$$Lambda$8(this, decoderCounters));
            }
        }

        public final void a(VideoSize videoSize) {
            Handler handler = this.a;
            if (handler != null) {
                handler.post(new VideoRendererEventListener$EventDispatcher$$Lambda$5(this, videoSize));
            }
        }

        public final void a(Object obj) {
            if (this.a != null) {
                this.a.post(new VideoRendererEventListener$EventDispatcher$$Lambda$6(this, obj, SystemClock.elapsedRealtime()));
            }
        }
    }

    void a(int i, long j);

    void a(long j, int i);

    void a(Format format, DecoderReuseEvaluation decoderReuseEvaluation);

    void a(DecoderCounters decoderCounters);

    void a(Exception exc);

    void a(Object obj, long j);

    void a(String str);

    void a(String str, long j, long j2);

    void b(DecoderCounters decoderCounters);

    void h();

    void onVideoSizeChanged(VideoSize videoSize);
}
