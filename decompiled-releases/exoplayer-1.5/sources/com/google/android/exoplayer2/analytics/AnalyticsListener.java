package com.google.android.exoplayer2.analytics;

import android.util.SparseArray;
import com.dreamers.exoplayercore.repack.P;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.source.MediaLoadData;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ExoFlags;
import com.google.android.exoplayer2.video.VideoSize;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public interface AnalyticsListener {

    public final class EventTime {
        public final long a;
        public final Timeline b;
        public final int c;
        public final MediaSource.MediaPeriodId d;
        public final long e;
        private Timeline f;
        private int g;
        private MediaSource.MediaPeriodId h;
        private long i;
        private long j;

        public EventTime(long j2, Timeline timeline, int i2, MediaSource.MediaPeriodId mediaPeriodId, long j3, Timeline timeline2, int i3, MediaSource.MediaPeriodId mediaPeriodId2, long j4, long j5) {
            this.a = j2;
            this.b = timeline;
            this.c = i2;
            this.d = mediaPeriodId;
            this.e = j3;
            this.f = timeline2;
            this.g = i3;
            this.h = mediaPeriodId2;
            this.i = j4;
            this.j = j5;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                EventTime eventTime = (EventTime) obj;
                return this.a == eventTime.a && this.c == eventTime.c && this.e == eventTime.e && this.g == eventTime.g && this.i == eventTime.i && this.j == eventTime.j && P.a(this.b, eventTime.b) && P.a(this.d, eventTime.d) && P.a(this.f, eventTime.f) && P.a(this.h, eventTime.h);
            }
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{Long.valueOf(this.a), this.b, Integer.valueOf(this.c), this.d, Long.valueOf(this.e), this.f, Integer.valueOf(this.g), this.h, Long.valueOf(this.i), Long.valueOf(this.j)});
        }
    }

    public final class Events {
        public Events(ExoFlags exoFlags, SparseArray sparseArray) {
            SparseArray sparseArray2 = new SparseArray(exoFlags.a.size());
            for (int i = 0; i < exoFlags.a.size(); i++) {
                int b = exoFlags.b(i);
                sparseArray2.append(b, (EventTime) Assertions.b((Object) (EventTime) sparseArray.get(b)));
            }
        }
    }

    void a();

    void a(EventTime eventTime);

    void a(EventTime eventTime, int i);

    void a(EventTime eventTime, int i, int i2);

    void a(EventTime eventTime, int i, long j, long j2);

    void a(EventTime eventTime, ExoPlaybackException exoPlaybackException);

    void a(EventTime eventTime, Format format);

    void a(EventTime eventTime, PlaybackParameters playbackParameters);

    void a(EventTime eventTime, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i);

    void a(EventTime eventTime, Metadata metadata);

    void a(EventTime eventTime, MediaLoadData mediaLoadData);

    void a(EventTime eventTime, TrackSelectionArray trackSelectionArray);

    void a(EventTime eventTime, VideoSize videoSize);

    void a(EventTime eventTime, IOException iOException);

    void a(EventTime eventTime, Exception exc);

    void a(EventTime eventTime, Object obj);

    void a(EventTime eventTime, String str);

    void a(EventTime eventTime, List list);

    void a(EventTime eventTime, boolean z);

    void a(EventTime eventTime, boolean z, int i);

    void b();

    void b(EventTime eventTime);

    void b(EventTime eventTime, int i);

    void b(EventTime eventTime, Format format);

    void b(EventTime eventTime, MediaLoadData mediaLoadData);

    void b(EventTime eventTime, String str);

    void b(EventTime eventTime, boolean z);

    void c();

    void c(EventTime eventTime);

    void c(EventTime eventTime, int i);

    void c(EventTime eventTime, String str);

    void c(EventTime eventTime, boolean z);

    void d();

    void d(EventTime eventTime);

    void d(EventTime eventTime, int i);

    void d(EventTime eventTime, String str);

    void d(EventTime eventTime, boolean z);

    void e();

    void e(EventTime eventTime);

    void e(EventTime eventTime, int i);

    void f();

    void f(EventTime eventTime);

    void f(EventTime eventTime, int i);

    void g();

    void g(EventTime eventTime);

    void g(EventTime eventTime, int i);

    void h();

    void i();

    void j();

    void k();

    void l();

    void m();

    void n();

    void o();

    void p();

    void q();

    void r();

    void s();

    void t();

    void u();

    void v();

    void w();
}
