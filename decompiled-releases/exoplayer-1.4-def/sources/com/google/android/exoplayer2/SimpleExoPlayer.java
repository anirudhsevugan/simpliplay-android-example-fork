package com.google.android.exoplayer2;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.media.AudioTrack;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.Looper;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import com.google.android.exoplayer2.AudioBecomingNoisyManager;
import com.google.android.exoplayer2.AudioFocusManager;
import com.google.android.exoplayer2.DefaultLivePlaybackSpeedControl;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.PlayerMessage;
import com.google.android.exoplayer2.StreamVolumeManager;
import com.google.android.exoplayer2.analytics.AnalyticsCollector;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.audio.AudioListener;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.audio.AudioRendererEventListener$$CC;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.decoder.DecoderReuseEvaluation;
import com.google.android.exoplayer2.device.DeviceInfo;
import com.google.android.exoplayer2.device.DeviceListener;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.MetadataOutput;
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory;
import com.google.android.exoplayer2.source.MediaSourceFactory;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.text.TextOutput;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.ConditionVariable;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.PriorityTaskManager;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoDecoderOutputBufferRenderer;
import com.google.android.exoplayer2.video.VideoFrameMetadataListener;
import com.google.android.exoplayer2.video.VideoListener;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import com.google.android.exoplayer2.video.VideoRendererEventListener$$CC;
import com.google.android.exoplayer2.video.VideoSize;
import com.google.android.exoplayer2.video.spherical.CameraMotionListener;
import com.google.android.exoplayer2.video.spherical.SphericalGLSurfaceView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeoutException;

public class SimpleExoPlayer extends BasePlayer implements ExoPlayer {
    private int A;
    private int B;
    private int C;
    private int D;
    private AudioAttributes E;
    private float F;
    /* access modifiers changed from: private */
    public boolean G;
    /* access modifiers changed from: private */
    public List H;
    private boolean I;
    private boolean J;
    /* access modifiers changed from: private */
    public PriorityTaskManager K;
    /* access modifiers changed from: private */
    public boolean L;
    /* access modifiers changed from: private */
    public DeviceInfo M;
    public final ExoPlayerImpl b;
    public final StreamVolumeManager c;
    private Renderer[] d;
    private final ConditionVariable e;
    private final Context f;
    private final ComponentListener g;
    private final FrameMetadataListener h;
    /* access modifiers changed from: private */
    public final CopyOnWriteArraySet i;
    private final CopyOnWriteArraySet j;
    /* access modifiers changed from: private */
    public final CopyOnWriteArraySet k;
    /* access modifiers changed from: private */
    public final CopyOnWriteArraySet l;
    /* access modifiers changed from: private */
    public final CopyOnWriteArraySet m;
    /* access modifiers changed from: private */
    public final AnalyticsCollector n;
    private final AudioBecomingNoisyManager o;
    private final AudioFocusManager p;
    private final WakeLockManager q;
    private final WifiLockManager r;
    private final long s;
    private AudioTrack t;
    /* access modifiers changed from: private */
    public Object u;
    private Surface v;
    private SurfaceHolder w;
    private SphericalGLSurfaceView x;
    /* access modifiers changed from: private */
    public boolean y;
    private TextureView z;

    public final class Builder {
        /* access modifiers changed from: private */
        public final Context a;
        /* access modifiers changed from: private */
        public final RenderersFactory b;
        /* access modifiers changed from: private */
        public Clock c;
        /* access modifiers changed from: private */
        public TrackSelector d;
        /* access modifiers changed from: private */
        public MediaSourceFactory e;
        /* access modifiers changed from: private */
        public LoadControl f;
        /* access modifiers changed from: private */
        public BandwidthMeter g;
        /* access modifiers changed from: private */
        public AnalyticsCollector h;
        /* access modifiers changed from: private */
        public Looper i;
        /* access modifiers changed from: private */
        public AudioAttributes j;
        /* access modifiers changed from: private */
        public int k;
        /* access modifiers changed from: private */
        public boolean l;
        /* access modifiers changed from: private */
        public SeekParameters m;
        /* access modifiers changed from: private */
        public LivePlaybackSpeedControl n;
        /* access modifiers changed from: private */
        public long o;
        /* access modifiers changed from: private */
        public long p;
        private boolean q;

        public Builder(Context context) {
            this(context, new DefaultRenderersFactory(context), new DefaultExtractorsFactory());
        }

        private Builder(Context context, RenderersFactory renderersFactory, ExtractorsFactory extractorsFactory) {
            this(context, renderersFactory, new DefaultTrackSelector(context), new DefaultMediaSourceFactory(context, extractorsFactory), new DefaultLoadControl(), DefaultBandwidthMeter.a(context), new AnalyticsCollector(Clock.a));
        }

        private Builder(Context context, RenderersFactory renderersFactory, TrackSelector trackSelector, MediaSourceFactory mediaSourceFactory, LoadControl loadControl, BandwidthMeter bandwidthMeter, AnalyticsCollector analyticsCollector) {
            this.a = context;
            this.b = renderersFactory;
            this.d = trackSelector;
            this.e = mediaSourceFactory;
            this.f = loadControl;
            this.g = bandwidthMeter;
            this.h = analyticsCollector;
            this.i = Util.c();
            this.j = AudioAttributes.a;
            this.k = 1;
            this.l = true;
            this.m = SeekParameters.a;
            DefaultLivePlaybackSpeedControl.Builder builder = new DefaultLivePlaybackSpeedControl.Builder();
            this.n = new DefaultLivePlaybackSpeedControl(builder.a, builder.b, builder.c, builder.d, builder.e, builder.f, builder.g, (byte) 0);
            this.c = Clock.a;
            this.o = 500;
            this.p = 2000;
        }

        static /* synthetic */ PriorityTaskManager b() {
            return null;
        }

        static /* synthetic */ boolean c() {
            return false;
        }

        static /* synthetic */ boolean d() {
            return false;
        }

        static /* synthetic */ long e() {
            return 0;
        }

        static /* synthetic */ boolean f() {
            return false;
        }

        static /* synthetic */ boolean g() {
            return false;
        }

        static /* synthetic */ int h() {
            return 0;
        }

        public final Builder a(TrackSelector trackSelector) {
            Assertions.b(!this.q);
            this.d = trackSelector;
            return this;
        }

        public final SimpleExoPlayer a() {
            Assertions.b(!this.q);
            this.q = true;
            return new SimpleExoPlayer(this);
        }
    }

    final class ComponentListener implements SurfaceHolder.Callback, TextureView.SurfaceTextureListener, AudioBecomingNoisyManager.EventListener, AudioFocusManager.PlayerControl, ExoPlayer.AudioOffloadListener, Player.EventListener, StreamVolumeManager.Listener, AudioRendererEventListener, MetadataOutput, TextOutput, VideoRendererEventListener, SphericalGLSurfaceView.VideoSurfaceListener {
        private ComponentListener() {
        }

        /* synthetic */ ComponentListener(SimpleExoPlayer simpleExoPlayer, byte b) {
            this();
        }

        public final void a() {
            SimpleExoPlayer.this.a(false, -1, 3);
        }

        public final void a(int i) {
            boolean l = SimpleExoPlayer.this.l();
            SimpleExoPlayer.this.a(l, i, SimpleExoPlayer.b(l, i));
        }

        public final void a(int i, long j) {
            SimpleExoPlayer.this.n.a(i, j);
        }

        public final void a(int i, long j, long j2) {
            SimpleExoPlayer.this.n.a(i, j, j2);
        }

        public final void a(int i, boolean z) {
            Iterator it = SimpleExoPlayer.this.m.iterator();
            while (it.hasNext()) {
                ((DeviceListener) it.next()).onDeviceVolumeChanged(i, z);
            }
        }

        public final void a(long j) {
            SimpleExoPlayer.this.n.a(j);
        }

        public final void a(long j, int i) {
            SimpleExoPlayer.this.n.a(j, i);
        }

        public final void a(Surface surface) {
            SimpleExoPlayer.this.a((Object) surface);
        }

        public final void a(Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
            SimpleExoPlayer.a(format);
            SimpleExoPlayer.this.n.a(format, decoderReuseEvaluation);
        }

        public final void a(DecoderCounters decoderCounters) {
            SimpleExoPlayer.a(decoderCounters);
            SimpleExoPlayer.this.n.a(decoderCounters);
        }

        public final void a(Exception exc) {
            SimpleExoPlayer.this.n.a(exc);
        }

        public final void a(Object obj, long j) {
            SimpleExoPlayer.this.n.a(obj, j);
            if (SimpleExoPlayer.this.u == obj) {
                Iterator it = SimpleExoPlayer.this.i.iterator();
                while (it.hasNext()) {
                    ((VideoListener) it.next()).onRenderedFirstFrame();
                }
            }
        }

        public final void a(String str) {
            SimpleExoPlayer.this.n.a(str);
        }

        public final void a(String str, long j, long j2) {
            SimpleExoPlayer.this.n.a(str, j, j2);
        }

        public final void b() {
            SimpleExoPlayer.this.a(1, 2, (Object) Float.valueOf(SimpleExoPlayer.this.F * SimpleExoPlayer.this.p.a));
        }

        public final void b(Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
            SimpleExoPlayer.b(format);
            SimpleExoPlayer.this.n.b(format, decoderReuseEvaluation);
        }

        public final void b(DecoderCounters decoderCounters) {
            SimpleExoPlayer.this.n.b(decoderCounters);
            SimpleExoPlayer.a((Format) null);
            SimpleExoPlayer.a((DecoderCounters) null);
        }

        public final void b(Exception exc) {
            SimpleExoPlayer.this.n.b(exc);
        }

        public final void b(String str) {
            SimpleExoPlayer.this.n.b(str);
        }

        public final void b(String str, long j, long j2) {
            SimpleExoPlayer.this.n.b(str, j, j2);
        }

        public final void c() {
            ExoPlayer$AudioOffloadListener$$CC.a();
        }

        public final void c(DecoderCounters decoderCounters) {
            SimpleExoPlayer.b(decoderCounters);
            SimpleExoPlayer.this.n.c(decoderCounters);
        }

        public final void c(Exception exc) {
            SimpleExoPlayer.this.n.c(exc);
        }

        public final void d() {
            SimpleExoPlayer.p(SimpleExoPlayer.this);
        }

        public final void d(DecoderCounters decoderCounters) {
            SimpleExoPlayer.this.n.d(decoderCounters);
            SimpleExoPlayer.b((Format) null);
            SimpleExoPlayer.b((DecoderCounters) null);
        }

        public final void e() {
            SimpleExoPlayer.this.a((Object) null);
        }

        public final void f() {
            DeviceInfo a2 = SimpleExoPlayer.b(SimpleExoPlayer.this.c);
            if (!a2.equals(SimpleExoPlayer.this.M)) {
                DeviceInfo unused = SimpleExoPlayer.this.M = a2;
                Iterator it = SimpleExoPlayer.this.m.iterator();
                while (it.hasNext()) {
                    ((DeviceListener) it.next()).onDeviceInfoChanged(a2);
                }
            }
        }

        public final void g() {
            AudioRendererEventListener$$CC.a();
        }

        public final void h() {
            VideoRendererEventListener$$CC.a();
        }

        public final void onAvailableCommandsChanged(Player.Commands commands) {
            Player$EventListener$$CC.i();
        }

        public final void onCues(List list) {
            List unused = SimpleExoPlayer.this.H = list;
            Iterator it = SimpleExoPlayer.this.k.iterator();
            while (it.hasNext()) {
                ((TextOutput) it.next()).onCues(list);
            }
        }

        public final void onEvents(Player player, Player.Events events) {
            Player$EventListener$$CC.v();
        }

        public final void onIsLoadingChanged(boolean z) {
            if (SimpleExoPlayer.this.K == null) {
                return;
            }
            if (z && !SimpleExoPlayer.this.L) {
                PriorityTaskManager n = SimpleExoPlayer.this.K;
                synchronized (n.a) {
                    n.b.add(0);
                    n.c = Math.max(n.c, 0);
                }
                boolean unused = SimpleExoPlayer.this.L = true;
            } else if (!z && SimpleExoPlayer.this.L) {
                SimpleExoPlayer.this.K.a();
                boolean unused2 = SimpleExoPlayer.this.L = false;
            }
        }

        public final void onIsPlayingChanged(boolean z) {
            Player$EventListener$$CC.n();
        }

        public final void onLoadingChanged(boolean z) {
            Player$EventListener$$CC.h();
        }

        public final void onMediaItemTransition(MediaItem mediaItem, int i) {
            Player$EventListener$$CC.c();
        }

        public final void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
            Player$EventListener$$CC.f();
        }

        public final void onMetadata(Metadata metadata) {
            SimpleExoPlayer.this.n.onMetadata(metadata);
            ExoPlayerImpl g = SimpleExoPlayer.this.b;
            MediaMetadata.Builder a2 = g.i.a();
            for (Metadata.Entry a3 : metadata.a) {
                a3.a(a2);
            }
            MediaMetadata a4 = a2.a();
            if (!a4.equals(g.i)) {
                g.i = a4;
                g.c.b(15, new ExoPlayerImpl$$Lambda$5(g));
            }
            Iterator it = SimpleExoPlayer.this.l.iterator();
            while (it.hasNext()) {
                ((MetadataOutput) it.next()).onMetadata(metadata);
            }
        }

        public final void onPlayWhenReadyChanged(boolean z, int i) {
            SimpleExoPlayer.p(SimpleExoPlayer.this);
        }

        public final void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            Player$EventListener$$CC.t();
        }

        public final void onPlaybackStateChanged(int i) {
            SimpleExoPlayer.p(SimpleExoPlayer.this);
        }

        public final void onPlaybackSuppressionReasonChanged(int i) {
            Player$EventListener$$CC.m();
        }

        public final void onPlayerError(ExoPlaybackException exoPlaybackException) {
            Player$EventListener$$CC.q();
        }

        public final void onPlayerStateChanged(boolean z, int i) {
            Player$EventListener$$CC.j();
        }

        public final void onPositionDiscontinuity(int i) {
            Player$EventListener$$CC.r();
        }

        public final void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
            Player$EventListener$$CC.s();
        }

        public final void onRepeatModeChanged(int i) {
            Player$EventListener$$CC.o();
        }

        public final void onSeekProcessed() {
            Player$EventListener$$CC.u();
        }

        public final void onShuffleModeEnabledChanged(boolean z) {
            Player$EventListener$$CC.p();
        }

        public final void onSkipSilenceEnabledChanged(boolean z) {
            if (SimpleExoPlayer.this.G != z) {
                boolean unused = SimpleExoPlayer.this.G = z;
                SimpleExoPlayer.e(SimpleExoPlayer.this);
            }
        }

        public final void onStaticMetadataChanged(List list) {
            Player$EventListener$$CC.e();
        }

        public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            SimpleExoPlayer.this.a(surfaceTexture);
            SimpleExoPlayer.this.b(i, i2);
        }

        public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            SimpleExoPlayer.this.a((Object) null);
            SimpleExoPlayer.this.b(0, 0);
            return true;
        }

        public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            SimpleExoPlayer.this.b(i, i2);
        }

        public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }

        public final void onTimelineChanged(Timeline timeline, int i) {
            Player$EventListener$$CC.a();
        }

        public final void onTimelineChanged(Timeline timeline, Object obj, int i) {
            Player$EventListener$$CC.b();
        }

        public final void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
            Player$EventListener$$CC.d();
        }

        public final void onVideoSizeChanged(VideoSize videoSize) {
            SimpleExoPlayer.a(videoSize);
            SimpleExoPlayer.this.n.onVideoSizeChanged(videoSize);
            Iterator it = SimpleExoPlayer.this.i.iterator();
            while (it.hasNext()) {
                VideoListener videoListener = (VideoListener) it.next();
                videoListener.onVideoSizeChanged(videoSize);
                videoListener.onVideoSizeChanged(videoSize.b, videoSize.c, videoSize.d, videoSize.e);
            }
        }

        public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            SimpleExoPlayer.this.b(i2, i3);
        }

        public final void surfaceCreated(SurfaceHolder surfaceHolder) {
            if (SimpleExoPlayer.this.y) {
                SimpleExoPlayer.this.a((Object) surfaceHolder.getSurface());
            }
        }

        public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            if (SimpleExoPlayer.this.y) {
                SimpleExoPlayer.this.a((Object) null);
            }
            SimpleExoPlayer.this.b(0, 0);
        }
    }

    final class FrameMetadataListener implements PlayerMessage.Target, VideoFrameMetadataListener, CameraMotionListener {
        private VideoFrameMetadataListener a;
        private CameraMotionListener b;
        private VideoFrameMetadataListener c;
        private CameraMotionListener d;

        private FrameMetadataListener() {
        }

        /* synthetic */ FrameMetadataListener(byte b2) {
            this();
        }

        public final void a() {
            CameraMotionListener cameraMotionListener = this.d;
            if (cameraMotionListener != null) {
                cameraMotionListener.a();
            }
            CameraMotionListener cameraMotionListener2 = this.b;
            if (cameraMotionListener2 != null) {
                cameraMotionListener2.a();
            }
        }

        public final void a(int i, Object obj) {
            switch (i) {
                case 6:
                    this.a = (VideoFrameMetadataListener) obj;
                    return;
                case 7:
                    this.b = (CameraMotionListener) obj;
                    return;
                case 10000:
                    SphericalGLSurfaceView sphericalGLSurfaceView = (SphericalGLSurfaceView) obj;
                    if (sphericalGLSurfaceView == null) {
                        this.c = null;
                        this.d = null;
                        return;
                    }
                    this.c = sphericalGLSurfaceView.b;
                    this.d = sphericalGLSurfaceView.b;
                    return;
                default:
                    return;
            }
        }

        public final void a(long j, long j2, Format format, MediaFormat mediaFormat) {
            VideoFrameMetadataListener videoFrameMetadataListener = this.c;
            if (videoFrameMetadataListener != null) {
                videoFrameMetadataListener.a(j, j2, format, mediaFormat);
            }
            VideoFrameMetadataListener videoFrameMetadataListener2 = this.a;
            if (videoFrameMetadataListener2 != null) {
                videoFrameMetadataListener2.a(j, j2, format, mediaFormat);
            }
        }

        public final void a(long j, float[] fArr) {
            CameraMotionListener cameraMotionListener = this.d;
            if (cameraMotionListener != null) {
                cameraMotionListener.a(j, fArr);
            }
            CameraMotionListener cameraMotionListener2 = this.b;
            if (cameraMotionListener2 != null) {
                cameraMotionListener2.a(j, fArr);
            }
        }
    }

    protected SimpleExoPlayer(Builder builder) {
        SimpleExoPlayer simpleExoPlayer;
        int a;
        int i2;
        ConditionVariable conditionVariable = new ConditionVariable();
        this.e = conditionVariable;
        try {
            Context applicationContext = builder.a.getApplicationContext();
            this.f = applicationContext;
            AnalyticsCollector b2 = builder.h;
            this.n = b2;
            this.K = Builder.b();
            this.E = builder.j;
            this.A = builder.k;
            this.G = Builder.c();
            this.s = builder.p;
            ComponentListener componentListener = new ComponentListener(this, (byte) 0);
            this.g = componentListener;
            FrameMetadataListener frameMetadataListener = new FrameMetadataListener((byte) 0);
            this.h = frameMetadataListener;
            this.i = new CopyOnWriteArraySet();
            this.j = new CopyOnWriteArraySet();
            this.k = new CopyOnWriteArraySet();
            this.l = new CopyOnWriteArraySet();
            this.m = new CopyOnWriteArraySet();
            Handler handler = new Handler(builder.i);
            this.d = builder.b.a(handler, componentListener, componentListener, componentListener, componentListener);
            this.F = 1.0f;
            if (Util.a < 21) {
                AudioTrack audioTrack = this.t;
                if (!(audioTrack == null || audioTrack.getAudioSessionId() == 0)) {
                    this.t.release();
                    this.t = null;
                }
                if (this.t == null) {
                    this.t = new AudioTrack(3, 4000, 4, 2, 2, 0, 0);
                }
                a = this.t.getAudioSessionId();
            } else {
                a = C.a(applicationContext);
            }
            this.D = a;
            this.H = Collections.emptyList();
            this.I = true;
            Player.Commands a2 = new Player.Commands.Builder().a(15, 16, 17, 18, 19, 20, 21, 22).a();
            Renderer[] rendererArr = this.d;
            TrackSelector h2 = builder.d;
            MediaSourceFactory i3 = builder.e;
            LoadControl j2 = builder.f;
            BandwidthMeter k2 = builder.g;
            boolean l2 = builder.l;
            SeekParameters m2 = builder.m;
            LivePlaybackSpeedControl n2 = builder.n;
            ConditionVariable conditionVariable2 = conditionVariable;
            Handler handler2 = handler;
            LoadControl loadControl = j2;
            FrameMetadataListener frameMetadataListener2 = frameMetadataListener;
            BandwidthMeter bandwidthMeter = k2;
            FrameMetadataListener frameMetadataListener3 = frameMetadataListener2;
            boolean z2 = l2;
            FrameMetadataListener frameMetadataListener4 = frameMetadataListener3;
            LivePlaybackSpeedControl livePlaybackSpeedControl = n2;
            Handler handler3 = handler2;
            ExoPlayerImpl exoPlayerImpl = r1;
            ComponentListener componentListener2 = componentListener;
            try {
                ExoPlayerImpl exoPlayerImpl2 = new ExoPlayerImpl(rendererArr, h2, i3, loadControl, bandwidthMeter, b2, z2, m2, livePlaybackSpeedControl, builder.o, Builder.d(), builder.c, builder.i, this, a2);
                simpleExoPlayer = this;
                try {
                    simpleExoPlayer.b = exoPlayerImpl;
                    ComponentListener componentListener3 = componentListener2;
                    exoPlayerImpl.a((Player.EventListener) componentListener3);
                    exoPlayerImpl.d.add(componentListener3);
                    if (Builder.e() > 0) {
                        Builder.e();
                    }
                    Handler handler4 = handler3;
                    AudioBecomingNoisyManager audioBecomingNoisyManager = new AudioBecomingNoisyManager(builder.a, handler4, componentListener3);
                    simpleExoPlayer.o = audioBecomingNoisyManager;
                    audioBecomingNoisyManager.a(Builder.f());
                    simpleExoPlayer.p = new AudioFocusManager(builder.a, handler4, componentListener3);
                    Builder.g();
                    if (!Util.a((Object) null, (Object) null)) {
                        i2 = 1;
                        Assertions.a(true, (Object) "Automatic handling of audio focus is only available for USAGE_MEDIA and USAGE_GAME.");
                    } else {
                        i2 = 1;
                    }
                    StreamVolumeManager streamVolumeManager = new StreamVolumeManager(builder.a, handler4, componentListener3);
                    simpleExoPlayer.c = streamVolumeManager;
                    int f2 = Util.f(simpleExoPlayer.E.b);
                    if (streamVolumeManager.c != f2) {
                        streamVolumeManager.c = f2;
                        streamVolumeManager.d();
                        streamVolumeManager.a.f();
                    }
                    simpleExoPlayer.q = new WakeLockManager(builder.a);
                    Builder.h();
                    simpleExoPlayer.r = new WifiLockManager(builder.a);
                    Builder.h();
                    simpleExoPlayer.M = b(streamVolumeManager);
                    VideoSize videoSize = VideoSize.a;
                    simpleExoPlayer.a(i2, 102, (Object) Integer.valueOf(simpleExoPlayer.D));
                    simpleExoPlayer.a(2, 102, (Object) Integer.valueOf(simpleExoPlayer.D));
                    simpleExoPlayer.a(i2, 3, (Object) simpleExoPlayer.E);
                    simpleExoPlayer.a(2, 4, (Object) Integer.valueOf(simpleExoPlayer.A));
                    simpleExoPlayer.a(i2, 101, (Object) Boolean.valueOf(simpleExoPlayer.G));
                    FrameMetadataListener frameMetadataListener5 = frameMetadataListener4;
                    simpleExoPlayer.a(2, 6, (Object) frameMetadataListener5);
                    simpleExoPlayer.a(6, 7, (Object) frameMetadataListener5);
                    conditionVariable2.a();
                } catch (Throwable th) {
                    th = th;
                    simpleExoPlayer.e.a();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                simpleExoPlayer = this;
                simpleExoPlayer.e.a();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            simpleExoPlayer = this;
            simpleExoPlayer.e.a();
            throw th;
        }
    }

    private void H() {
        G();
        I();
        a((Object) null);
        b(0, 0);
    }

    private void I() {
        if (this.x != null) {
            this.b.a((PlayerMessage.Target) this.h).a(10000).a((Object) null).a();
            this.x.a(this.g);
            this.x = null;
        }
        TextureView textureView = this.z;
        if (textureView != null) {
            if (textureView.getSurfaceTextureListener() != this.g) {
                Log.c("SimpleExoPlayer", "SurfaceTextureListener already unset or replaced.");
            } else {
                this.z.setSurfaceTextureListener((TextureView.SurfaceTextureListener) null);
            }
            this.z = null;
        }
        SurfaceHolder surfaceHolder = this.w;
        if (surfaceHolder != null) {
            surfaceHolder.removeCallback(this.g);
            this.w = null;
        }
    }

    static /* synthetic */ Format a(Format format) {
        return format;
    }

    static /* synthetic */ DecoderCounters a(DecoderCounters decoderCounters) {
        return decoderCounters;
    }

    static /* synthetic */ VideoSize a(VideoSize videoSize) {
        return videoSize;
    }

    /* access modifiers changed from: private */
    public void a(int i2, int i3, Object obj) {
        for (Renderer renderer : this.d) {
            if (renderer.a() == i2) {
                this.b.a((PlayerMessage.Target) renderer).a(i3).a(obj).a();
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(SurfaceTexture surfaceTexture) {
        Surface surface = new Surface(surfaceTexture);
        a((Object) surface);
        this.v = surface;
    }

    private void a(SurfaceHolder surfaceHolder) {
        this.y = false;
        this.w = surfaceHolder;
        surfaceHolder.addCallback(this.g);
        Surface surface = this.w.getSurface();
        if (surface == null || !surface.isValid()) {
            b(0, 0);
            return;
        }
        Rect surfaceFrame = this.w.getSurfaceFrame();
        b(surfaceFrame.width(), surfaceFrame.height());
    }

    private void a(AudioListener audioListener) {
        Assertions.b((Object) audioListener);
        this.j.add(audioListener);
    }

    private void a(DeviceListener deviceListener) {
        Assertions.b((Object) deviceListener);
        this.m.add(deviceListener);
    }

    private void a(MetadataOutput metadataOutput) {
        Assertions.b((Object) metadataOutput);
        this.l.add(metadataOutput);
    }

    private void a(TextOutput textOutput) {
        Assertions.b((Object) textOutput);
        this.k.add(textOutput);
    }

    private void a(VideoListener videoListener) {
        Assertions.b((Object) videoListener);
        this.i.add(videoListener);
    }

    /* access modifiers changed from: private */
    public void a(Object obj) {
        ArrayList<PlayerMessage> arrayList = new ArrayList<>();
        for (Renderer renderer : this.d) {
            if (renderer.a() == 2) {
                arrayList.add(this.b.a((PlayerMessage.Target) renderer).a(1).a(obj).a());
            }
        }
        Object obj2 = this.u;
        if (!(obj2 == null || obj2 == obj)) {
            try {
                for (PlayerMessage a : arrayList) {
                    a.a(this.s);
                }
            } catch (InterruptedException e2) {
                Thread.currentThread().interrupt();
            } catch (TimeoutException e3) {
                this.b.a(false, ExoPlaybackException.a((Exception) new ExoTimeoutException(3)));
            }
            Object obj3 = this.u;
            Surface surface = this.v;
            if (obj3 == surface) {
                surface.release();
                this.v = null;
            }
        }
        this.u = obj;
    }

    /* access modifiers changed from: private */
    public void a(boolean z2, int i2, int i3) {
        int i4 = 0;
        boolean z3 = z2 && i2 != -1;
        if (z3 && i2 != 1) {
            i4 = 1;
        }
        this.b.a(z3, i4, i3);
    }

    /* access modifiers changed from: private */
    public static int b(boolean z2, int i2) {
        return (!z2 || i2 == 1) ? 1 : 2;
    }

    static /* synthetic */ Format b(Format format) {
        return format;
    }

    static /* synthetic */ DecoderCounters b(DecoderCounters decoderCounters) {
        return decoderCounters;
    }

    /* access modifiers changed from: private */
    public static DeviceInfo b(StreamVolumeManager streamVolumeManager) {
        return new DeviceInfo(streamVolumeManager.a(), streamVolumeManager.b());
    }

    /* access modifiers changed from: private */
    public void b(int i2, int i3) {
        if (i2 != this.B || i3 != this.C) {
            this.B = i2;
            this.C = i3;
            this.n.onSurfaceSizeChanged(i2, i3);
            Iterator it = this.i.iterator();
            while (it.hasNext()) {
                ((VideoListener) it.next()).onSurfaceSizeChanged(i2, i3);
            }
        }
    }

    private void b(AudioListener audioListener) {
        this.j.remove(audioListener);
    }

    private void b(DeviceListener deviceListener) {
        this.m.remove(deviceListener);
    }

    private void b(MetadataOutput metadataOutput) {
        this.l.remove(metadataOutput);
    }

    private void b(TextOutput textOutput) {
        this.k.remove(textOutput);
    }

    private void b(VideoListener videoListener) {
        this.i.remove(videoListener);
    }

    static /* synthetic */ void e(SimpleExoPlayer simpleExoPlayer) {
        simpleExoPlayer.n.onSkipSilenceEnabledChanged(simpleExoPlayer.G);
        Iterator it = simpleExoPlayer.j.iterator();
        while (it.hasNext()) {
            ((AudioListener) it.next()).onSkipSilenceEnabledChanged(simpleExoPlayer.G);
        }
    }

    static /* synthetic */ void p(SimpleExoPlayer simpleExoPlayer) {
        switch (simpleExoPlayer.h()) {
            case 1:
            case 4:
                return;
            case 2:
            case 3:
                simpleExoPlayer.G();
                simpleExoPlayer.l();
                simpleExoPlayer.l();
                return;
            default:
                throw new IllegalStateException();
        }
    }

    public final long A() {
        G();
        return this.b.A();
    }

    public final TrackGroupArray B() {
        G();
        return this.b.j.h;
    }

    public final TrackSelectionArray C() {
        G();
        return this.b.C();
    }

    public final List D() {
        G();
        return this.b.j.j;
    }

    public final Timeline E() {
        G();
        return this.b.j.a;
    }

    public final List F() {
        G();
        return this.H;
    }

    public final void G() {
        this.e.d();
        if (Thread.currentThread() != this.b.e.getThread()) {
            String a = Util.a("Player is accessed on the wrong thread.\nCurrent thread: '%s'\nExpected thread: '%s'\nSee https://exoplayer.dev/issues/player-accessed-on-wrong-thread", Thread.currentThread().getName(), this.b.e.getThread().getName());
            if (!this.I) {
                Log.a("SimpleExoPlayer", a, this.J ? null : new IllegalStateException());
                this.J = true;
                return;
            }
            throw new IllegalStateException(a);
        }
    }

    public final void a(int i2, int i3) {
        G();
        this.b.a(i2, i3);
    }

    public final void a(int i2, long j2) {
        G();
        this.n.c();
        this.b.a(i2, j2);
    }

    public final void a(int i2, List list) {
        G();
        this.b.a(i2, list);
    }

    public final void a(SurfaceView surfaceView) {
        G();
        if (surfaceView instanceof VideoDecoderOutputBufferRenderer) {
            I();
            a((Object) surfaceView);
            a(surfaceView.getHolder());
        } else if (surfaceView instanceof SphericalGLSurfaceView) {
            I();
            this.x = (SphericalGLSurfaceView) surfaceView;
            this.b.a((PlayerMessage.Target) this.h).a(10000).a((Object) this.x).a();
            this.x.a.add(this.g);
            a((Object) this.x.d);
            a(surfaceView.getHolder());
        } else {
            SurfaceHolder holder = surfaceView == null ? null : surfaceView.getHolder();
            G();
            if (holder == null) {
                H();
                return;
            }
            I();
            this.y = true;
            this.w = holder;
            holder.addCallback(this.g);
            Surface surface = holder.getSurface();
            if (surface == null || !surface.isValid()) {
                a((Object) null);
                b(0, 0);
                return;
            }
            a((Object) surface);
            Rect surfaceFrame = holder.getSurfaceFrame();
            b(surfaceFrame.width(), surfaceFrame.height());
        }
    }

    public final void a(TextureView textureView) {
        G();
        if (textureView == null) {
            H();
            return;
        }
        I();
        this.z = textureView;
        if (textureView.getSurfaceTextureListener() != null) {
            Log.c("SimpleExoPlayer", "Replacing existing SurfaceTextureListener.");
        }
        textureView.setSurfaceTextureListener(this.g);
        SurfaceTexture surfaceTexture = textureView.isAvailable() ? textureView.getSurfaceTexture() : null;
        if (surfaceTexture == null) {
            a((Object) null);
            b(0, 0);
            return;
        }
        a(surfaceTexture);
        b(textureView.getWidth(), textureView.getHeight());
    }

    public final void a(PlaybackParameters playbackParameters) {
        G();
        this.b.a(playbackParameters);
    }

    public final void a(Player.EventListener eventListener) {
        Assertions.b((Object) eventListener);
        this.b.a(eventListener);
    }

    public final void a(Player.Listener listener) {
        Assertions.b((Object) listener);
        a((AudioListener) listener);
        a((VideoListener) listener);
        a((TextOutput) listener);
        a((MetadataOutput) listener);
        a((DeviceListener) listener);
        a((Player.EventListener) listener);
    }

    public final void a(AnalyticsListener analyticsListener) {
        Assertions.b((Object) analyticsListener);
        this.n.a(analyticsListener);
    }

    public final void a(boolean z2) {
        G();
        AudioFocusManager audioFocusManager = this.p;
        h();
        int a = audioFocusManager.a(z2);
        a(z2, a, b(z2, a));
    }

    public final void b(int i2) {
        G();
        this.b.b(i2);
    }

    public final void b(SurfaceView surfaceView) {
        G();
        SurfaceHolder holder = surfaceView == null ? null : surfaceView.getHolder();
        G();
        if (holder != null && holder == this.w) {
            H();
        }
    }

    public final void b(TextureView textureView) {
        G();
        if (textureView != null && textureView == this.z) {
            H();
        }
    }

    public final void b(Player.EventListener eventListener) {
        this.b.b(eventListener);
    }

    public final void b(Player.Listener listener) {
        Assertions.b((Object) listener);
        b((AudioListener) listener);
        b((VideoListener) listener);
        b((TextOutput) listener);
        b((MetadataOutput) listener);
        b((DeviceListener) listener);
        b((Player.EventListener) listener);
    }

    public final void b(boolean z2) {
        G();
        this.b.b(z2);
    }

    public final void c(boolean z2) {
        G();
        this.p.a(l());
        this.b.c(z2);
        this.H = Collections.emptyList();
    }

    public final Looper f() {
        return this.b.e;
    }

    public final Player.Commands g() {
        G();
        return this.b.h;
    }

    public TrackSelector getTrackSelector() {
        G();
        return this.b.getTrackSelector();
    }

    public final int h() {
        G();
        return this.b.j.e;
    }

    public final int i() {
        G();
        return this.b.j.m;
    }

    public final ExoPlaybackException j() {
        G();
        return this.b.j.f;
    }

    public final void k() {
        G();
        boolean l2 = l();
        int a = this.p.a(l2);
        a(l2, a, b(l2, a));
        this.b.k();
    }

    public final boolean l() {
        G();
        return this.b.j.l;
    }

    public final int m() {
        G();
        return this.b.f;
    }

    public final boolean n() {
        G();
        return this.b.g;
    }

    public final PlaybackParameters o() {
        G();
        return this.b.j.n;
    }

    public final void p() {
        AudioTrack audioTrack;
        G();
        if (Util.a < 21 && (audioTrack = this.t) != null) {
            audioTrack.release();
            this.t = null;
        }
        this.o.a(false);
        this.c.c();
        this.p.a();
        this.b.p();
        this.n.b();
        I();
        Surface surface = this.v;
        if (surface != null) {
            surface.release();
            this.v = null;
        }
        if (this.L) {
            ((PriorityTaskManager) Assertions.b((Object) this.K)).a();
            this.L = false;
        }
        this.H = Collections.emptyList();
    }

    public final int q() {
        G();
        return this.b.q();
    }

    public final int r() {
        G();
        return this.b.r();
    }

    public final long s() {
        G();
        return this.b.s();
    }

    public final long t() {
        G();
        return this.b.t();
    }

    public final long u() {
        G();
        return this.b.u();
    }

    public final long v() {
        G();
        return this.b.v();
    }

    public final boolean w() {
        G();
        return this.b.w();
    }

    public final int x() {
        G();
        return this.b.x();
    }

    public final int y() {
        G();
        return this.b.y();
    }

    public final long z() {
        G();
        return this.b.z();
    }
}
