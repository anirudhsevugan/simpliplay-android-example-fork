package com.google.android.exoplayer2;

import android.os.Looper;
import android.view.SurfaceView;
import android.view.TextureView;
import com.dreamers.exoplayercore.repack.P;
import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.audio.AudioListener;
import com.google.android.exoplayer2.device.DeviceListener;
import com.google.android.exoplayer2.metadata.MetadataOutput;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.text.TextOutput;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.util.ExoFlags;
import com.google.android.exoplayer2.video.VideoListener;
import java.util.Arrays;
import java.util.List;

public interface Player {

    public final class Commands {
        /* access modifiers changed from: package-private */
        public final ExoFlags a;

        public final class Builder {
            private final ExoFlags.Builder a = new ExoFlags.Builder();

            public final Builder a(int i) {
                this.a.a(i);
                return this;
            }

            public final Builder a(int i, boolean z) {
                this.a.a(i, z);
                return this;
            }

            public final Builder a(Commands commands) {
                this.a.a(commands.a);
                return this;
            }

            public final Builder a(int... iArr) {
                this.a.a(iArr);
                return this;
            }

            public final Commands a() {
                return new Commands(this.a.a(), (byte) 0);
            }
        }

        static {
            new Builder().a();
        }

        private Commands(ExoFlags exoFlags) {
            this.a = exoFlags;
        }

        /* synthetic */ Commands(ExoFlags exoFlags, byte b) {
            this(exoFlags);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Commands)) {
                return false;
            }
            return this.a.equals(((Commands) obj).a);
        }

        public final int hashCode() {
            return this.a.hashCode();
        }
    }

    public interface EventListener {
        void onAvailableCommandsChanged(Commands commands);

        void onEvents(Player player, Events events);

        void onIsLoadingChanged(boolean z);

        void onIsPlayingChanged(boolean z);

        void onLoadingChanged(boolean z);

        void onMediaItemTransition(MediaItem mediaItem, int i);

        void onMediaMetadataChanged(MediaMetadata mediaMetadata);

        void onPlayWhenReadyChanged(boolean z, int i);

        void onPlaybackParametersChanged(PlaybackParameters playbackParameters);

        void onPlaybackStateChanged(int i);

        void onPlaybackSuppressionReasonChanged(int i);

        void onPlayerError(ExoPlaybackException exoPlaybackException);

        void onPlayerStateChanged(boolean z, int i);

        void onPositionDiscontinuity(int i);

        void onPositionDiscontinuity(PositionInfo positionInfo, PositionInfo positionInfo2, int i);

        void onRepeatModeChanged(int i);

        void onSeekProcessed();

        void onShuffleModeEnabledChanged(boolean z);

        void onStaticMetadataChanged(List list);

        void onTimelineChanged(Timeline timeline, int i);

        void onTimelineChanged(Timeline timeline, Object obj, int i);

        void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray);
    }

    public final class Events {
        private final ExoFlags a;

        public Events(ExoFlags exoFlags) {
            this.a = exoFlags;
        }

        public final boolean a(int i) {
            return this.a.a(i);
        }

        public final boolean a(int... iArr) {
            ExoFlags exoFlags = this.a;
            for (int a2 : iArr) {
                if (exoFlags.a(a2)) {
                    return true;
                }
            }
            return false;
        }
    }

    public interface Listener extends EventListener, AudioListener, DeviceListener, MetadataOutput, TextOutput, VideoListener {
    }

    public final class PositionInfo implements Bundleable {
        public final int a;
        public final int b;
        public final long c;
        public final long d;
        public final int e;
        public final int f;
        private Object g;
        private Object h;

        static {
            Bundleable.Creator creator = Player$PositionInfo$$Lambda$0.a;
        }

        public PositionInfo(Object obj, int i, Object obj2, int i2, long j, long j2, int i3, int i4) {
            this.g = obj;
            this.a = i;
            this.h = obj2;
            this.b = i2;
            this.c = j;
            this.d = j2;
            this.e = i3;
            this.f = i4;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                PositionInfo positionInfo = (PositionInfo) obj;
                return this.a == positionInfo.a && this.b == positionInfo.b && this.c == positionInfo.c && this.d == positionInfo.d && this.e == positionInfo.e && this.f == positionInfo.f && P.a(this.g, positionInfo.g) && P.a(this.h, positionInfo.h);
            }
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{this.g, Integer.valueOf(this.a), this.h, Integer.valueOf(this.b), Integer.valueOf(this.a), Long.valueOf(this.c), Long.valueOf(this.d), Integer.valueOf(this.e), Integer.valueOf(this.f)});
        }
    }

    long A();

    TrackGroupArray B();

    TrackSelectionArray C();

    List D();

    Timeline E();

    List F();

    void a(int i, int i2);

    void a(int i, long j);

    void a(int i, List list);

    void a(SurfaceView surfaceView);

    void a(TextureView textureView);

    void a(PlaybackParameters playbackParameters);

    void a(EventListener eventListener);

    void a(Listener listener);

    void a(boolean z);

    boolean a();

    boolean a(int i);

    int b();

    void b(int i);

    void b(SurfaceView surfaceView);

    void b(TextureView textureView);

    void b(EventListener eventListener);

    void b(Listener listener);

    void b(boolean z);

    int c();

    void c(boolean z);

    boolean d();

    Looper f();

    Commands g();

    int h();

    int i();

    ExoPlaybackException j();

    void k();

    boolean l();

    int m();

    boolean n();

    PlaybackParameters o();

    int q();

    int r();

    long s();

    long t();

    long u();

    long v();

    boolean w();

    int x();

    int y();

    long z();
}
