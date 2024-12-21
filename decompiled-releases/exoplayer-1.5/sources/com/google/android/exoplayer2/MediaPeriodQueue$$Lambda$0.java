package com.google.android.exoplayer2;

import com.dreamers.exoplayercore.repack.bG;
import com.dreamers.exoplayercore.repack.bH;
import com.google.android.exoplayer2.analytics.AnalyticsCollector;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.util.Assertions;
import java.util.Collection;

final /* synthetic */ class MediaPeriodQueue$$Lambda$0 implements Runnable {
    private final MediaPeriodQueue a;
    private final bH b;
    private final MediaSource.MediaPeriodId c;

    MediaPeriodQueue$$Lambda$0(MediaPeriodQueue mediaPeriodQueue, bH bHVar, MediaSource.MediaPeriodId mediaPeriodId) {
        this.a = mediaPeriodQueue;
        this.b = bHVar;
        this.c = mediaPeriodId;
    }

    public final void run() {
        MediaPeriodQueue mediaPeriodQueue = this.a;
        bH bHVar = this.b;
        MediaSource.MediaPeriodId mediaPeriodId = this.c;
        AnalyticsCollector analyticsCollector = mediaPeriodQueue.a;
        bG a2 = bHVar.a();
        AnalyticsCollector.MediaPeriodQueueTracker mediaPeriodQueueTracker = analyticsCollector.a;
        Player player = (Player) Assertions.b((Object) analyticsCollector.d);
        mediaPeriodQueueTracker.b = bG.a((Collection) a2);
        if (!a2.isEmpty()) {
            mediaPeriodQueueTracker.d = (MediaSource.MediaPeriodId) a2.get(0);
            mediaPeriodQueueTracker.e = (MediaSource.MediaPeriodId) Assertions.b((Object) mediaPeriodId);
        }
        if (mediaPeriodQueueTracker.c == null) {
            mediaPeriodQueueTracker.c = AnalyticsCollector.MediaPeriodQueueTracker.a(player, mediaPeriodQueueTracker.b, mediaPeriodQueueTracker.d, mediaPeriodQueueTracker.a);
        }
        mediaPeriodQueueTracker.a(player.E());
    }
}
