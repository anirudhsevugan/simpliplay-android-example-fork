package com.google.android.exoplayer2.ui;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.opengl.GLSurfaceView;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.dreamers.exoplayercore.repack.bG;
import com.google.android.exoplayer2.ControlDispatcher;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.PlaybackPreparer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Player$EventListener$$CC;
import com.google.android.exoplayer2.Player$Listener$$CC;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.audio.AudioListener$$CC;
import com.google.android.exoplayer2.device.DeviceInfo;
import com.google.android.exoplayer2.device.DeviceListener$$CC;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.flac.PictureFrame;
import com.google.android.exoplayer2.metadata.id3.ApicFrame;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionUtil;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ErrorMessageProvider;
import com.google.android.exoplayer2.video.VideoListener$$CC;
import com.google.android.exoplayer2.video.VideoSize;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PlayerView extends FrameLayout implements AdViewProvider {
    private static final int PICTURE_TYPE_FRONT_COVER = 3;
    private static final int PICTURE_TYPE_NOT_SET = -1;
    public static final int SHOW_BUFFERING_ALWAYS = 2;
    public static final int SHOW_BUFFERING_NEVER = 0;
    public static final int SHOW_BUFFERING_WHEN_PLAYING = 1;
    public static final int SURFACE_TYPE_NONE = 0;
    public static final int SURFACE_TYPE_SPHERICAL_GL_SURFACE_VIEW = 3;
    public static final int SURFACE_TYPE_SURFACE_VIEW = 1;
    public static final int SURFACE_TYPE_TEXTURE_VIEW = 2;
    public static final int SURFACE_TYPE_VIDEO_DECODER_GL_SURFACE_VIEW = 4;
    private final FrameLayout adOverlayFrameLayout;
    private final ImageView artworkView;
    private final View bufferingView;
    private final ComponentListener componentListener;
    /* access modifiers changed from: private */
    public final AspectRatioFrameLayout contentFrame;
    private final PlayerControlView controller;
    private boolean controllerAutoShow;
    /* access modifiers changed from: private */
    public boolean controllerHideDuringAds;
    private boolean controllerHideOnTouch;
    private int controllerShowTimeoutMs;
    private PlayerControlView.VisibilityListener controllerVisibilityListener;
    private CharSequence customErrorMessage;
    private final boolean debugMode;
    private Drawable defaultArtwork;
    private ErrorMessageProvider errorMessageProvider;
    private final TextView errorMessageView;
    private boolean isTouching;
    private boolean keepContentOnPlayerReset;
    private final FrameLayout overlayFrameLayout;
    /* access modifiers changed from: private */
    public Player player;
    private int showBuffering;
    /* access modifiers changed from: private */
    public final View shutterView;
    /* access modifiers changed from: private */
    public final SubtitleView subtitleView;
    /* access modifiers changed from: private */
    public final View surfaceView;
    /* access modifiers changed from: private */
    public final boolean surfaceViewIgnoresVideoAspectRatio;
    /* access modifiers changed from: private */
    public int textureViewRotation;
    private boolean useArtwork;
    private boolean useController;

    final class ComponentListener implements View.OnClickListener, View.OnLayoutChangeListener, Player.Listener, PlayerControlView.VisibilityListener {
        private Object lastPeriodUidWithTracks;
        private final Timeline.Period period = new Timeline.Period();

        public ComponentListener() {
        }

        public final void onAudioAttributesChanged(AudioAttributes audioAttributes) {
            AudioListener$$CC.b();
        }

        public final void onAudioSessionIdChanged(int i) {
            AudioListener$$CC.a();
        }

        public final void onAvailableCommandsChanged(Player.Commands commands) {
            Player$EventListener$$CC.i();
        }

        public final void onClick(View view) {
            boolean unused = PlayerView.this.toggleControllerVisibility();
        }

        public final void onCues(List list) {
            if (PlayerView.this.subtitleView != null) {
                PlayerView.this.subtitleView.onCues(list);
            }
        }

        public final void onDeviceInfoChanged(DeviceInfo deviceInfo) {
            DeviceListener$$CC.a();
        }

        public final void onDeviceVolumeChanged(int i, boolean z) {
            DeviceListener$$CC.b();
        }

        public final void onEvents(Player player, Player.Events events) {
            Player$EventListener$$CC.v();
        }

        public final void onIsLoadingChanged(boolean z) {
            Player$EventListener$$CC.g();
        }

        public final void onIsPlayingChanged(boolean z) {
            Player$EventListener$$CC.n();
        }

        public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            PlayerView.applyTextureViewRotation((TextureView) view, PlayerView.this.textureViewRotation);
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
            Player$Listener$$CC.b();
        }

        public final void onPlayWhenReadyChanged(boolean z, int i) {
            PlayerView.this.updateBuffering();
            PlayerView.this.updateControllerVisibility();
        }

        public final void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            Player$EventListener$$CC.t();
        }

        public final void onPlaybackStateChanged(int i) {
            PlayerView.this.updateBuffering();
            PlayerView.this.updateErrorMessage();
            PlayerView.this.updateControllerVisibility();
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
            if (PlayerView.this.isPlayingAd() && PlayerView.this.controllerHideDuringAds) {
                PlayerView.this.hideController();
            }
        }

        public final void onRenderedFirstFrame() {
            if (PlayerView.this.shutterView != null) {
                PlayerView.this.shutterView.setVisibility(4);
            }
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
            AudioListener$$CC.d();
        }

        public final void onStaticMetadataChanged(List list) {
            Player$EventListener$$CC.e();
        }

        public final void onSurfaceSizeChanged(int i, int i2) {
            VideoListener$$CC.c();
        }

        public final void onTimelineChanged(Timeline timeline, int i) {
            Player$EventListener$$CC.a();
        }

        public final void onTimelineChanged(Timeline timeline, Object obj, int i) {
            Player$EventListener$$CC.b();
        }

        public final void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
            Object obj;
            Player player = (Player) Assertions.b((Object) PlayerView.this.player);
            Timeline E = player.E();
            if (!E.c()) {
                if (!player.B().a()) {
                    obj = E.a(player.q(), this.period, true).b;
                    this.lastPeriodUidWithTracks = obj;
                    PlayerView.this.updateForCurrentTrackSelections(false);
                }
                Object obj2 = this.lastPeriodUidWithTracks;
                if (obj2 != null) {
                    int c = E.c(obj2);
                    if (c != -1 && player.r() == E.a(c, this.period, false).c) {
                        return;
                    }
                }
                PlayerView.this.updateForCurrentTrackSelections(false);
            }
            obj = null;
            this.lastPeriodUidWithTracks = obj;
            PlayerView.this.updateForCurrentTrackSelections(false);
        }

        public final void onVideoSizeChanged(int i, int i2, int i3, float f) {
            VideoListener$$CC.b();
        }

        public final void onVideoSizeChanged(VideoSize videoSize) {
            int i = videoSize.b;
            int i2 = videoSize.c;
            int i3 = videoSize.d;
            float f = (i2 == 0 || i == 0) ? 1.0f : (((float) i) * videoSize.e) / ((float) i2);
            if (PlayerView.this.surfaceView instanceof TextureView) {
                if (i3 == 90 || i3 == 270) {
                    f = 1.0f / f;
                }
                if (PlayerView.this.textureViewRotation != 0) {
                    PlayerView.this.surfaceView.removeOnLayoutChangeListener(this);
                }
                int unused = PlayerView.this.textureViewRotation = i3;
                if (PlayerView.this.textureViewRotation != 0) {
                    PlayerView.this.surfaceView.addOnLayoutChangeListener(this);
                }
                PlayerView.applyTextureViewRotation((TextureView) PlayerView.this.surfaceView, PlayerView.this.textureViewRotation);
            }
            PlayerView playerView = PlayerView.this;
            AspectRatioFrameLayout access$400 = playerView.contentFrame;
            if (PlayerView.this.surfaceViewIgnoresVideoAspectRatio) {
                f = 0.0f;
            }
            playerView.onContentAspectRatioChanged(access$400, f);
        }

        public final void onVisibilityChange(int i) {
            PlayerView.this.updateContentDescription();
        }

        public final void onVolumeChanged(float f) {
            AudioListener$$CC.c();
        }
    }

    public @interface ShowBuffering {
    }

    public PlayerView(Context context) {
        this(context, TimeBarAttributes.createDefault());
    }

    public PlayerView(Context context, TimeBarAttributes timeBarAttributes) {
        this(context, timeBarAttributes, PlayerAttributes.createDefault());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0144, code lost:
        r0.surfaceView = r4;
        r11 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0147, code lost:
        r0.surfaceView.setLayoutParams(r3);
        r0.surfaceView.setOnClickListener(r5);
        r0.surfaceView.setClickable(false);
        r6.addView(r0.surfaceView, 0);
        r3 = r11;
     */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public PlayerView(android.content.Context r24, com.google.android.exoplayer2.ui.TimeBarAttributes r25, com.google.android.exoplayer2.ui.PlayerAttributes r26) {
        /*
            r23 = this;
            r0 = r23
            r1 = r24
            r2 = 0
            r3 = 0
            r0.<init>(r1, r2, r3)
            r4 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r0.setBackgroundColor(r4)
            android.widget.FrameLayout$LayoutParams r5 = new android.widget.FrameLayout$LayoutParams
            r6 = -1
            r5.<init>(r6, r6)
            r0.setLayoutParams(r5)
            com.google.android.exoplayer2.ui.PlayerView$ComponentListener r5 = new com.google.android.exoplayer2.ui.PlayerView$ComponentListener
            r5.<init>()
            r0.componentListener = r5
            boolean r7 = r23.isInEditMode()
            if (r7 == 0) goto L_0x0045
            r0.contentFrame = r2
            r0.shutterView = r2
            r0.surfaceView = r2
            r0.surfaceViewIgnoresVideoAspectRatio = r3
            r0.artworkView = r2
            r0.subtitleView = r2
            r0.bufferingView = r2
            r0.errorMessageView = r2
            r0.controller = r2
            r0.adOverlayFrameLayout = r2
            r0.overlayFrameLayout = r2
            r0.debugMode = r3
            android.widget.ImageView r2 = new android.widget.ImageView
            r2.<init>(r1)
        L_0x0041:
            r0.addView(r2)
            return
        L_0x0045:
            boolean r7 = r26.getUseArtWork()
            boolean r8 = r26.getUseController()
            int r9 = r26.getSurfaceType()
            int r10 = r26.getResizeMode()
            int r11 = r26.getControllerTimeout()
            boolean r12 = r26.getHideOnTouch()
            boolean r13 = r26.getAutoShowController()
            boolean r14 = r26.getHideDuringAds()
            int r15 = r26.getShowBuffering()
            boolean r2 = r26.isDebugMode()
            r0.debugMode = r2
            java.lang.String r2 = "#B3ffffff"
            int r2 = android.graphics.Color.parseColor(r2)
            java.lang.String r16 = "#AA000000"
            int r3 = android.graphics.Color.parseColor(r16)
            com.google.android.exoplayer2.ui.AspectRatioFrameLayout r6 = new com.google.android.exoplayer2.ui.AspectRatioFrameLayout
            r6.<init>(r1)
            r0.contentFrame = r6
            android.widget.FrameLayout$LayoutParams r4 = r23.centeredParams()
            r6.setLayoutParams(r4)
            android.view.View r4 = new android.view.View
            r4.<init>(r1)
            r0.shutterView = r4
            r17 = r8
            android.widget.FrameLayout$LayoutParams r8 = r23.defaultParams()
            r4.setLayoutParams(r8)
            android.widget.ImageView r8 = new android.widget.ImageView
            r8.<init>(r1)
            r0.artworkView = r8
            r18 = r14
            android.widget.FrameLayout$LayoutParams r14 = r23.centeredParams()
            r8.setLayoutParams(r14)
            android.widget.ImageView$ScaleType r14 = android.widget.ImageView.ScaleType.FIT_XY
            r8.setScaleType(r14)
            com.google.android.exoplayer2.ui.SubtitleView r14 = new com.google.android.exoplayer2.ui.SubtitleView
            r14.<init>(r1)
            r0.subtitleView = r14
            r19 = r13
            android.widget.FrameLayout$LayoutParams r13 = r23.defaultParams()
            r14.setLayoutParams(r13)
            android.widget.ProgressBar r13 = new android.widget.ProgressBar
            r13.<init>(r1)
            r0.bufferingView = r13
            r20 = r12
            android.widget.FrameLayout$LayoutParams r12 = new android.widget.FrameLayout$LayoutParams
            r21 = r11
            r11 = 17
            r22 = r15
            r15 = -2
            r12.<init>(r15, r15, r11)
            r13.setLayoutParams(r12)
            r11 = r13
            android.widget.ProgressBar r11 = (android.widget.ProgressBar) r11
            r11 = 1
            r13.setIndeterminate(r11)
            r12 = r13
            android.widget.ProgressBar r12 = (android.widget.ProgressBar) r12
            android.content.res.ColorStateList r2 = android.content.res.ColorStateList.valueOf(r2)
            r13.setIndeterminateTintList(r2)
            android.widget.TextView r2 = new android.widget.TextView
            r2.<init>(r1)
            r0.errorMessageView = r2
            android.widget.FrameLayout$LayoutParams r12 = r23.centeredParams()
            r2.setLayoutParams(r12)
            r12 = 1098907648(0x41800000, float:16.0)
            int r12 = com.google.android.exoplayer2.ui.UiHelper.convertToDp(r12)
            r2.setPadding(r12, r12, r12, r12)
            r2.setBackgroundColor(r3)
            r6.addView(r4)
            r6.addView(r8)
            r6.addView(r14)
            r6.addView(r13)
            r6.addView(r2)
            r0.addView(r6)
            r3 = 262144(0x40000, float:3.67342E-40)
            r0.setDescendantFocusability(r3)
            setResizeModeRaw(r6, r10)
            r3 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r4.setBackgroundColor(r3)
            if (r9 == 0) goto L_0x015e
            android.view.ViewGroup$LayoutParams r3 = new android.view.ViewGroup$LayoutParams
            r4 = -1
            r3.<init>(r4, r4)
            switch(r9) {
                case 2: goto L_0x013f;
                case 3: goto L_0x0137;
                case 4: goto L_0x0131;
                default: goto L_0x012b;
            }
        L_0x012b:
            android.view.SurfaceView r4 = new android.view.SurfaceView
            r4.<init>(r1)
            goto L_0x0144
        L_0x0131:
            com.google.android.exoplayer2.video.VideoDecoderGLSurfaceView r4 = new com.google.android.exoplayer2.video.VideoDecoderGLSurfaceView
            r4.<init>(r1)
            goto L_0x0144
        L_0x0137:
            com.google.android.exoplayer2.video.spherical.SphericalGLSurfaceView r4 = new com.google.android.exoplayer2.video.spherical.SphericalGLSurfaceView
            r4.<init>(r1)
            r0.surfaceView = r4
            goto L_0x0147
        L_0x013f:
            android.view.TextureView r4 = new android.view.TextureView
            r4.<init>(r1)
        L_0x0144:
            r0.surfaceView = r4
            r11 = 0
        L_0x0147:
            android.view.View r4 = r0.surfaceView
            r4.setLayoutParams(r3)
            android.view.View r3 = r0.surfaceView
            r3.setOnClickListener(r5)
            android.view.View r3 = r0.surfaceView
            r4 = 0
            r3.setClickable(r4)
            android.view.View r3 = r0.surfaceView
            r6.addView(r3, r4)
            r3 = r11
            goto L_0x0163
        L_0x015e:
            r3 = 0
            r4 = 0
            r0.surfaceView = r3
            r3 = 0
        L_0x0163:
            r0.surfaceViewIgnoresVideoAspectRatio = r3
            android.widget.FrameLayout r3 = new android.widget.FrameLayout
            r3.<init>(r1)
            r0.adOverlayFrameLayout = r3
            android.widget.FrameLayout$LayoutParams r4 = r23.defaultParams()
            r3.setLayoutParams(r4)
            android.widget.FrameLayout r3 = new android.widget.FrameLayout
            r3.<init>(r1)
            r0.overlayFrameLayout = r3
            android.widget.FrameLayout$LayoutParams r4 = r23.defaultParams()
            r3.setLayoutParams(r4)
            r0.useArtwork = r7
            r14.setUserDefaultStyle()
            r14.setUserDefaultTextSize()
            r3 = 8
            r13.setVisibility(r3)
            r4 = r22
            r0.showBuffering = r4
            r2.setVisibility(r3)
            com.google.android.exoplayer2.ui.PlayerControlView r2 = new com.google.android.exoplayer2.ui.PlayerControlView
            r3 = r25
            r4 = r26
            r2.<init>(r1, r3, r4)
            r0.controller = r2
            android.widget.FrameLayout$LayoutParams r1 = r23.defaultParams()
            r2.setLayoutParams(r1)
            r1 = r21
            r0.controllerShowTimeoutMs = r1
            r1 = r20
            r0.controllerHideOnTouch = r1
            r1 = r19
            r0.controllerAutoShow = r1
            r1 = r18
            r0.controllerHideDuringAds = r1
            r1 = r17
            r0.useController = r1
            r23.hideController()
            r23.updateContentDescription()
            r2.addVisibilityListener(r5)
            goto L_0x0041
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.PlayerView.<init>(android.content.Context, com.google.android.exoplayer2.ui.TimeBarAttributes, com.google.android.exoplayer2.ui.PlayerAttributes):void");
    }

    /* access modifiers changed from: private */
    public static void applyTextureViewRotation(TextureView textureView, int i) {
        Matrix matrix = new Matrix();
        float width = (float) textureView.getWidth();
        float height = (float) textureView.getHeight();
        if (!(width == 0.0f || height == 0.0f || i == 0)) {
            float f = width / 2.0f;
            float f2 = height / 2.0f;
            matrix.postRotate((float) i, f, f2);
            RectF rectF = new RectF(0.0f, 0.0f, width, height);
            RectF rectF2 = new RectF();
            matrix.mapRect(rectF2, rectF);
            matrix.postScale(width / rectF2.width(), height / rectF2.height(), f, f2);
        }
        textureView.setTransform(matrix);
    }

    private FrameLayout.LayoutParams centeredParams() {
        return new FrameLayout.LayoutParams(-1, -1, 17);
    }

    private void closeShutter() {
        View view = this.shutterView;
        if (view != null) {
            view.setVisibility(0);
        }
    }

    private FrameLayout.LayoutParams defaultParams() {
        return new FrameLayout.LayoutParams(-1, -1);
    }

    private void hideArtwork() {
        ImageView imageView = this.artworkView;
        if (imageView != null) {
            imageView.setImageResource(17170445);
            this.artworkView.setVisibility(4);
        }
    }

    private boolean isDpadKey(int i) {
        return i == 19 || i == 270 || i == 22 || i == 271 || i == 20 || i == 269 || i == 21 || i == 268 || i == 23;
    }

    /* access modifiers changed from: private */
    public boolean isPlayingAd() {
        Player player2 = this.player;
        return player2 != null && player2.w() && this.player.l();
    }

    private void maybeShowController(boolean z) {
        if ((!isPlayingAd() || !this.controllerHideDuringAds) && useController()) {
            boolean z2 = this.controller.isVisible() && this.controller.getShowTimeoutMs() <= 0;
            boolean shouldShowControllerIndefinitely = shouldShowControllerIndefinitely();
            if (z || z2 || shouldShowControllerIndefinitely) {
                showController(shouldShowControllerIndefinitely);
            }
        }
    }

    private boolean setArtworkFromMetadata(Metadata metadata) {
        byte[] bArr;
        int i;
        int i2 = 0;
        int i3 = -1;
        boolean z = false;
        while (i2 < metadata.a.length) {
            Metadata.Entry entry = metadata.a[i2];
            if (entry instanceof ApicFrame) {
                ApicFrame apicFrame = (ApicFrame) entry;
                bArr = apicFrame.b;
                i = apicFrame.a;
            } else if (entry instanceof PictureFrame) {
                PictureFrame pictureFrame = (PictureFrame) entry;
                bArr = pictureFrame.b;
                i = pictureFrame.a;
            } else {
                continue;
                i2++;
            }
            if (i3 == -1 || i == 3) {
                z = setDrawableArtwork(new BitmapDrawable(getResources(), BitmapFactory.decodeByteArray(bArr, 0, bArr.length)));
                if (i == 3) {
                    break;
                }
                i3 = i;
                i2++;
            } else {
                i2++;
            }
        }
        return z;
    }

    private boolean setDrawableArtwork(Drawable drawable) {
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            if (intrinsicWidth > 0 && intrinsicHeight > 0) {
                onContentAspectRatioChanged(this.contentFrame, ((float) intrinsicWidth) / ((float) intrinsicHeight));
                this.artworkView.setImageDrawable(drawable);
                this.artworkView.setVisibility(0);
                return true;
            }
        }
        return false;
    }

    private static void setResizeModeRaw(AspectRatioFrameLayout aspectRatioFrameLayout, int i) {
        aspectRatioFrameLayout.setResizeMode(i);
    }

    private boolean shouldShowControllerIndefinitely() {
        Player player2 = this.player;
        if (player2 == null) {
            return true;
        }
        int h = player2.h();
        if (this.controllerAutoShow) {
            return h == 1 || h == 4 || !this.player.l();
        }
        return false;
    }

    private void showController(boolean z) {
        if (useController()) {
            this.controller.setShowTimeoutMs(z ? 0 : this.controllerShowTimeoutMs);
            this.controller.show();
        }
    }

    public static void switchTargetView(Player player2, PlayerView playerView, PlayerView playerView2) {
        if (playerView != playerView2) {
            if (playerView2 != null) {
                playerView2.setPlayer(player2);
            }
            if (playerView != null) {
                playerView.setPlayer((Player) null);
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean toggleControllerVisibility() {
        if (!useController() || this.player == null) {
            return false;
        }
        if (!this.controller.isVisible()) {
            maybeShowController(true);
        } else if (this.controllerHideOnTouch) {
            this.controller.hide();
        }
        return true;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001d, code lost:
        if (r4.player.l() == false) goto L_0x0020;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateBuffering() {
        /*
            r4 = this;
            android.view.View r0 = r4.bufferingView
            if (r0 == 0) goto L_0x002b
            com.google.android.exoplayer2.Player r0 = r4.player
            r1 = 0
            if (r0 == 0) goto L_0x0020
            int r0 = r0.h()
            r2 = 2
            if (r0 != r2) goto L_0x0020
            int r0 = r4.showBuffering
            r3 = 1
            if (r0 == r2) goto L_0x0021
            if (r0 != r3) goto L_0x0020
            com.google.android.exoplayer2.Player r0 = r4.player
            boolean r0 = r0.l()
            if (r0 == 0) goto L_0x0020
            goto L_0x0021
        L_0x0020:
            r3 = 0
        L_0x0021:
            android.view.View r0 = r4.bufferingView
            if (r3 == 0) goto L_0x0026
            goto L_0x0028
        L_0x0026:
            r1 = 8
        L_0x0028:
            r0.setVisibility(r1)
        L_0x002b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.PlayerView.updateBuffering():void");
    }

    /* access modifiers changed from: private */
    public void updateContentDescription() {
        PlayerControlView playerControlView = this.controller;
        CharSequence charSequence = null;
        if (playerControlView == null || !this.useController) {
            setContentDescription((CharSequence) null);
        } else if (playerControlView.getVisibility() == 0) {
            if (this.controllerHideOnTouch) {
                charSequence = "";
            }
            setContentDescription(charSequence);
        } else {
            setContentDescription("");
        }
    }

    /* access modifiers changed from: private */
    public void updateControllerVisibility() {
        if (!isPlayingAd() || !this.controllerHideDuringAds) {
            maybeShowController(false);
        } else {
            hideController();
        }
    }

    /* access modifiers changed from: private */
    public void updateErrorMessage() {
        ErrorMessageProvider errorMessageProvider2;
        TextView textView = this.errorMessageView;
        if (textView != null) {
            CharSequence charSequence = this.customErrorMessage;
            if (charSequence != null) {
                textView.setText(charSequence);
            } else {
                Player player2 = this.player;
                if ((player2 != null ? player2.j() : null) == null || (errorMessageProvider2 = this.errorMessageProvider) == null) {
                    this.errorMessageView.setVisibility(8);
                    return;
                }
                this.errorMessageView.setText((CharSequence) errorMessageProvider2.a().second);
            }
            this.errorMessageView.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void updateForCurrentTrackSelections(boolean z) {
        Player player2 = this.player;
        if (player2 != null && !player2.B().a()) {
            if (z && !this.keepContentOnPlayerReset) {
                closeShutter();
            }
            if (TrackSelectionUtil.a(player2.C())) {
                hideArtwork();
                return;
            }
            closeShutter();
            if (useArtwork()) {
                for (Metadata artworkFromMetadata : player2.D()) {
                    if (setArtworkFromMetadata(artworkFromMetadata)) {
                        return;
                    }
                }
                if (setDrawableArtwork(this.defaultArtwork)) {
                    return;
                }
            }
            hideArtwork();
        } else if (!this.keepContentOnPlayerReset) {
            hideArtwork();
            closeShutter();
        }
    }

    private boolean useArtwork() {
        if (!this.useArtwork) {
            return false;
        }
        Assertions.a((Object) this.artworkView);
        return true;
    }

    private boolean useController() {
        if (!this.useController) {
            return false;
        }
        Assertions.a((Object) this.controller);
        return true;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        Player player2 = this.player;
        if (player2 != null && player2.w()) {
            return super.dispatchKeyEvent(keyEvent);
        }
        boolean isDpadKey = isDpadKey(keyEvent.getKeyCode());
        if ((!isDpadKey || !useController() || this.controller.isVisible()) && !dispatchMediaKeyEvent(keyEvent) && !super.dispatchKeyEvent(keyEvent)) {
            if (isDpadKey && useController()) {
                maybeShowController(true);
            }
            return false;
        }
        maybeShowController(true);
        return true;
    }

    public boolean dispatchMediaKeyEvent(KeyEvent keyEvent) {
        return useController() && this.controller.dispatchMediaKeyEvent(keyEvent);
    }

    public List getAdOverlayInfos() {
        ArrayList arrayList = new ArrayList();
        FrameLayout frameLayout = this.overlayFrameLayout;
        if (frameLayout != null) {
            arrayList.add(new AdOverlayInfo(frameLayout, 3, "Transparent overlay does not impact viewability"));
        }
        PlayerControlView playerControlView = this.controller;
        if (playerControlView != null) {
            arrayList.add(new AdOverlayInfo(playerControlView, 0));
        }
        return bG.a((Collection) arrayList);
    }

    public ViewGroup getAdViewGroup() {
        return (ViewGroup) Assertions.a((Object) this.adOverlayFrameLayout, (Object) "exo_ad_overlay must be present for ad playback");
    }

    public boolean getControllerAutoShow() {
        return this.controllerAutoShow;
    }

    public boolean getControllerHideOnTouch() {
        return this.controllerHideOnTouch;
    }

    public int getControllerShowTimeoutMs() {
        return this.controllerShowTimeoutMs;
    }

    public Drawable getDefaultArtwork() {
        return this.defaultArtwork;
    }

    public FrameLayout getOverlayFrameLayout() {
        return this.overlayFrameLayout;
    }

    public Player getPlayer() {
        return this.player;
    }

    public int getResizeMode() {
        Assertions.a((Object) this.contentFrame);
        return this.contentFrame.getResizeMode();
    }

    public SubtitleView getSubtitleView() {
        return this.subtitleView;
    }

    public boolean getUseArtwork() {
        return this.useArtwork;
    }

    public boolean getUseController() {
        return this.useController;
    }

    public View getVideoSurfaceView() {
        return this.surfaceView;
    }

    public void hideController() {
        PlayerControlView playerControlView = this.controller;
        if (playerControlView != null) {
            playerControlView.hide();
        }
    }

    public boolean isControllerVisible() {
        PlayerControlView playerControlView = this.controller;
        return playerControlView != null && playerControlView.isVisible();
    }

    /* access modifiers changed from: protected */
    public void onContentAspectRatioChanged(AspectRatioFrameLayout aspectRatioFrameLayout, float f) {
        if (aspectRatioFrameLayout != null) {
            aspectRatioFrameLayout.setAspectRatio(f);
        }
    }

    public void onPause() {
        View view = this.surfaceView;
        if (view instanceof GLSurfaceView) {
            ((GLSurfaceView) view).onPause();
        }
    }

    public void onResume() {
        View view = this.surfaceView;
        if (view instanceof GLSurfaceView) {
            ((GLSurfaceView) view).onResume();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!useController() || this.player == null) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.isTouching = true;
                return true;
            case 1:
                if (!this.isTouching) {
                    return false;
                }
                this.isTouching = false;
                performClick();
                return true;
            default:
                return false;
        }
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (!useController() || this.player == null) {
            return false;
        }
        maybeShowController(true);
        return true;
    }

    public boolean performClick() {
        super.performClick();
        return toggleControllerVisibility();
    }

    public void setAspectRatioListener(AspectRatioFrameLayout.AspectRatioListener aspectRatioListener) {
        Assertions.a((Object) this.contentFrame);
        this.contentFrame.setAspectRatioListener(aspectRatioListener);
    }

    public void setControlDispatcher(ControlDispatcher controlDispatcher) {
        Assertions.a((Object) this.controller);
        this.controller.setControlDispatcher(controlDispatcher);
    }

    public void setControllerAutoShow(boolean z) {
        this.controllerAutoShow = z;
    }

    public void setControllerHideDuringAds(boolean z) {
        this.controllerHideDuringAds = z;
    }

    public void setControllerHideOnTouch(boolean z) {
        Assertions.a((Object) this.controller);
        this.controllerHideOnTouch = z;
        updateContentDescription();
    }

    public void setControllerShowTimeoutMs(int i) {
        Assertions.a((Object) this.controller);
        this.controllerShowTimeoutMs = i;
        if (this.controller.isVisible()) {
            showController();
        }
    }

    public void setControllerVisibilityListener(PlayerControlView.VisibilityListener visibilityListener) {
        Assertions.a((Object) this.controller);
        PlayerControlView.VisibilityListener visibilityListener2 = this.controllerVisibilityListener;
        if (visibilityListener2 != visibilityListener) {
            if (visibilityListener2 != null) {
                this.controller.removeVisibilityListener(visibilityListener2);
            }
            this.controllerVisibilityListener = visibilityListener;
            if (visibilityListener != null) {
                this.controller.addVisibilityListener(visibilityListener);
            }
        }
    }

    public void setCustomErrorMessage(CharSequence charSequence) {
        Assertions.b(this.errorMessageView != null);
        this.customErrorMessage = charSequence;
        updateErrorMessage();
    }

    public void setDefaultArtwork(Drawable drawable) {
        if (this.defaultArtwork != drawable) {
            this.defaultArtwork = drawable;
            updateForCurrentTrackSelections(false);
        }
    }

    public void setErrorMessageProvider(ErrorMessageProvider errorMessageProvider2) {
        if (this.errorMessageProvider != errorMessageProvider2) {
            this.errorMessageProvider = errorMessageProvider2;
            updateErrorMessage();
        }
    }

    public void setExtraAdGroupMarkers(long[] jArr, boolean[] zArr) {
        Assertions.a((Object) this.controller);
        this.controller.setExtraAdGroupMarkers(jArr, zArr);
    }

    public void setFastForwardIncrementMs(int i) {
        Assertions.a((Object) this.controller);
        this.controller.setFastForwardIncrementMs(i);
    }

    public void setKeepContentOnPlayerReset(boolean z) {
        if (this.keepContentOnPlayerReset != z) {
            this.keepContentOnPlayerReset = z;
            updateForCurrentTrackSelections(false);
        }
    }

    public void setPlaybackPreparer(PlaybackPreparer playbackPreparer) {
        Assertions.a((Object) this.controller);
        this.controller.setPlaybackPreparer(playbackPreparer);
    }

    public void setPlayer(Player player2) {
        Assertions.b(Looper.myLooper() == Looper.getMainLooper());
        Assertions.a(player2 == null || player2.f() == Looper.getMainLooper());
        Player player3 = this.player;
        if (player3 != player2) {
            if (player3 != null) {
                player3.b((Player.Listener) this.componentListener);
                if (player3.a(21)) {
                    View view = this.surfaceView;
                    if (view instanceof TextureView) {
                        player3.b((TextureView) view);
                    } else if (view instanceof SurfaceView) {
                        player3.b((SurfaceView) view);
                    }
                }
            }
            SubtitleView subtitleView2 = this.subtitleView;
            if (subtitleView2 != null) {
                subtitleView2.setCues((List) null);
            }
            this.player = player2;
            if (useController()) {
                this.controller.setPlayer(player2);
            }
            updateBuffering();
            updateErrorMessage();
            updateForCurrentTrackSelections(true);
            if (player2 != null) {
                if (player2.a(21)) {
                    View view2 = this.surfaceView;
                    if (view2 instanceof TextureView) {
                        player2.a((TextureView) view2);
                    } else if (view2 instanceof SurfaceView) {
                        player2.a((SurfaceView) view2);
                    }
                }
                if (this.subtitleView != null && player2.a(22)) {
                    this.subtitleView.setCues(player2.F());
                }
                player2.a((Player.Listener) this.componentListener);
                maybeShowController(false);
                return;
            }
            hideController();
        }
    }

    public void setRepeatToggleModes(int i) {
        Assertions.a((Object) this.controller);
        this.controller.setRepeatToggleModes(i);
    }

    public void setResizeMode(int i) {
        Assertions.a((Object) this.contentFrame);
        this.contentFrame.setResizeMode(i);
    }

    public void setRewindIncrementMs(int i) {
        Assertions.a((Object) this.controller);
        this.controller.setRewindIncrementMs(i);
    }

    public void setShowBuffering(int i) {
        if (this.showBuffering != i) {
            this.showBuffering = i;
            updateBuffering();
        }
    }

    public void setShowFastForwardButton(boolean z) {
        Assertions.a((Object) this.controller);
        this.controller.setShowFastForwardButton(z);
    }

    public void setShowMultiWindowTimeBar(boolean z) {
        Assertions.a((Object) this.controller);
        this.controller.setShowMultiWindowTimeBar(z);
    }

    public void setShowNextButton(boolean z) {
        Assertions.a((Object) this.controller);
        this.controller.setShowNextButton(z);
    }

    public void setShowPreviousButton(boolean z) {
        Assertions.a((Object) this.controller);
        this.controller.setShowPreviousButton(z);
    }

    public void setShowRewindButton(boolean z) {
        Assertions.a((Object) this.controller);
        this.controller.setShowRewindButton(z);
    }

    public void setShowShuffleButton(boolean z) {
        Assertions.a((Object) this.controller);
        this.controller.setShowShuffleButton(z);
    }

    public void setShutterBackgroundColor(int i) {
        View view = this.shutterView;
        if (view != null) {
            view.setBackgroundColor(i);
        }
    }

    public void setUseArtwork(boolean z) {
        Assertions.b(!z || this.artworkView != null);
        if (this.useArtwork != z) {
            this.useArtwork = z;
            updateForCurrentTrackSelections(false);
        }
    }

    public void setUseController(boolean z) {
        PlayerControlView playerControlView;
        Player player2;
        Assertions.b(!z || this.controller != null);
        if (this.useController != z) {
            this.useController = z;
            if (useController()) {
                playerControlView = this.controller;
                player2 = this.player;
            } else {
                PlayerControlView playerControlView2 = this.controller;
                if (playerControlView2 != null) {
                    playerControlView2.hide();
                    playerControlView = this.controller;
                    player2 = null;
                }
                updateContentDescription();
            }
            playerControlView.setPlayer(player2);
            updateContentDescription();
        }
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        View view = this.surfaceView;
        if (view instanceof SurfaceView) {
            view.setVisibility(i);
        }
    }

    public void showController() {
        showController(shouldShowControllerIndefinitely());
    }
}
