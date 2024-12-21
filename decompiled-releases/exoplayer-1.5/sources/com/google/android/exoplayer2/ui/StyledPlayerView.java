package com.google.android.exoplayer2.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.opengl.GLSurfaceView;
import android.os.Looper;
import android.util.TypedValue;
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
import com.google.android.exoplayer2.ui.StyledPlayerControlView;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ErrorMessageProvider;
import com.google.android.exoplayer2.video.VideoListener$$CC;
import com.google.android.exoplayer2.video.VideoSize;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StyledPlayerView extends FrameLayout implements AdViewProvider {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int PICTURE_TYPE_FRONT_COVER = 3;
    private static final int PICTURE_TYPE_NOT_SET = -1;
    public static final int SHOW_BUFFERING_ALWAYS = 2;
    public static final int SHOW_BUFFERING_NEVER = 0;
    public static final int SHOW_BUFFERING_WHEN_PLAYING = 1;
    private static final int SURFACE_TYPE_NONE = 0;
    private static final int SURFACE_TYPE_SPHERICAL_GL_SURFACE_VIEW = 3;
    private static final int SURFACE_TYPE_SURFACE_VIEW = 1;
    private static final int SURFACE_TYPE_TEXTURE_VIEW = 2;
    private static final int SURFACE_TYPE_VIDEO_DECODER_GL_SURFACE_VIEW = 4;
    private final FrameLayout adOverlayFrameLayout;
    private final ImageView artworkView;
    private final View bufferingView;
    private final ComponentListener componentListener;
    /* access modifiers changed from: private */
    public final AspectRatioFrameLayout contentFrame;
    private final StyledPlayerControlView controller;
    private boolean controllerAutoShow;
    /* access modifiers changed from: private */
    public boolean controllerHideDuringAds;
    private boolean controllerHideOnTouch;
    private int controllerShowTimeoutMs;
    private StyledPlayerControlView.VisibilityListener controllerVisibilityListener;
    private CharSequence customErrorMessage;
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

    final class ComponentListener implements View.OnClickListener, View.OnLayoutChangeListener, Player.Listener, StyledPlayerControlView.VisibilityListener {
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
            boolean unused = StyledPlayerView.this.toggleControllerVisibility();
        }

        public final void onCues(List list) {
            if (StyledPlayerView.this.subtitleView != null) {
                StyledPlayerView.this.subtitleView.onCues(list);
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
            StyledPlayerView.applyTextureViewRotation((TextureView) view, StyledPlayerView.this.textureViewRotation);
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
            StyledPlayerView.this.updateBuffering();
            StyledPlayerView.this.updateControllerVisibility();
        }

        public final void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            Player$EventListener$$CC.t();
        }

        public final void onPlaybackStateChanged(int i) {
            StyledPlayerView.this.updateBuffering();
            StyledPlayerView.this.updateErrorMessage();
            StyledPlayerView.this.updateControllerVisibility();
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
            if (StyledPlayerView.this.isPlayingAd() && StyledPlayerView.this.controllerHideDuringAds) {
                StyledPlayerView.this.hideController();
            }
        }

        public final void onRenderedFirstFrame() {
            if (StyledPlayerView.this.shutterView != null) {
                StyledPlayerView.this.shutterView.setVisibility(4);
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
            Player player = (Player) Assertions.b((Object) StyledPlayerView.this.player);
            Timeline E = player.E();
            if (!E.c()) {
                if (!player.B().a()) {
                    obj = E.a(player.q(), this.period, true).b;
                    this.lastPeriodUidWithTracks = obj;
                    StyledPlayerView.this.updateForCurrentTrackSelections(false);
                }
                Object obj2 = this.lastPeriodUidWithTracks;
                if (obj2 != null) {
                    int c = E.c(obj2);
                    if (c != -1 && player.r() == E.a(c, this.period, false).c) {
                        return;
                    }
                }
                StyledPlayerView.this.updateForCurrentTrackSelections(false);
            }
            obj = null;
            this.lastPeriodUidWithTracks = obj;
            StyledPlayerView.this.updateForCurrentTrackSelections(false);
        }

        public final void onVideoSizeChanged(int i, int i2, int i3, float f) {
            float f2 = (i2 == 0 || i == 0) ? 1.0f : (((float) i) * f) / ((float) i2);
            if (StyledPlayerView.this.surfaceView instanceof TextureView) {
                if (i3 == 90 || i3 == 270) {
                    f2 = 1.0f / f2;
                }
                if (StyledPlayerView.this.textureViewRotation != 0) {
                    StyledPlayerView.this.surfaceView.removeOnLayoutChangeListener(this);
                }
                int unused = StyledPlayerView.this.textureViewRotation = i3;
                if (StyledPlayerView.this.textureViewRotation != 0) {
                    StyledPlayerView.this.surfaceView.addOnLayoutChangeListener(this);
                }
                StyledPlayerView.applyTextureViewRotation((TextureView) StyledPlayerView.this.surfaceView, StyledPlayerView.this.textureViewRotation);
            }
            StyledPlayerView styledPlayerView = StyledPlayerView.this;
            AspectRatioFrameLayout access$400 = styledPlayerView.contentFrame;
            if (StyledPlayerView.this.surfaceViewIgnoresVideoAspectRatio) {
                f2 = 0.0f;
            }
            styledPlayerView.onContentAspectRatioChanged(access$400, f2);
        }

        public final void onVideoSizeChanged(VideoSize videoSize) {
            VideoListener$$CC.a();
        }

        public final void onVisibilityChange(int i) {
            StyledPlayerView.this.updateContentDescription();
        }

        public final void onVolumeChanged(float f) {
            AudioListener$$CC.c();
        }
    }

    public @interface ShowBuffering {
    }

    public StyledPlayerView(Context context) {
        this(context, TimeBarAttributes.createDefault());
    }

    public StyledPlayerView(Context context, TimeBarAttributes timeBarAttributes) {
        this(context, timeBarAttributes, PlayerAttributes.createDefault());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0152, code lost:
        r0.surfaceView = r2;
        r2 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0155, code lost:
        r0.surfaceView.setLayoutParams(r5);
        r6 = r23;
        r0.surfaceView.setOnClickListener(r6);
        r0.surfaceView.setClickable(false);
        r3.addView(r0.surfaceView, 0);
     */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public StyledPlayerView(android.content.Context r25, com.google.android.exoplayer2.ui.TimeBarAttributes r26, com.google.android.exoplayer2.ui.PlayerAttributes r27) {
        /*
            r24 = this;
            r0 = r24
            r1 = r25
            r2 = 0
            r3 = 0
            r0.<init>(r1, r2, r3)
            com.google.android.exoplayer2.ui.StyledPlayerView$ComponentListener r4 = new com.google.android.exoplayer2.ui.StyledPlayerView$ComponentListener
            r4.<init>()
            r0.componentListener = r4
            java.lang.String r5 = "#000000"
            int r6 = android.graphics.Color.parseColor(r5)
            r0.setBackgroundColor(r6)
            boolean r6 = r24.isInEditMode()
            if (r6 == 0) goto L_0x003e
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
            android.widget.ImageView r2 = new android.widget.ImageView
            r2.<init>(r1)
        L_0x003a:
            r0.addView(r2)
            return
        L_0x003e:
            java.lang.String r6 = "#B3ffffff"
            int r6 = android.graphics.Color.parseColor(r6)
            java.lang.String r7 = "#AA000000"
            int r7 = android.graphics.Color.parseColor(r7)
            int r5 = android.graphics.Color.parseColor(r5)
            boolean r8 = r27.getUseArtWork()
            boolean r9 = r27.getUseController()
            int r10 = r27.getSurfaceType()
            int r11 = r27.getResizeMode()
            int r12 = r27.getControllerTimeout()
            boolean r13 = r27.getHideOnTouch()
            boolean r14 = r27.getAutoShowController()
            boolean r15 = r27.getHideDuringAds()
            int r2 = r27.getShowBuffering()
            android.widget.FrameLayout$LayoutParams r3 = new android.widget.FrameLayout$LayoutParams
            r16 = r9
            r9 = -1
            r3.<init>(r9, r9)
            r0.setLayoutParams(r3)
            com.google.android.exoplayer2.ui.AspectRatioFrameLayout r3 = new com.google.android.exoplayer2.ui.AspectRatioFrameLayout
            r3.<init>(r1)
            r0.contentFrame = r3
            android.widget.FrameLayout$LayoutParams r9 = r24.centeredParams()
            r3.setLayoutParams(r9)
            android.view.View r9 = new android.view.View
            r9.<init>(r1)
            r0.shutterView = r9
            r17 = r15
            android.widget.FrameLayout$LayoutParams r15 = r24.defaultParams()
            r9.setLayoutParams(r15)
            android.widget.ImageView r15 = new android.widget.ImageView
            r15.<init>(r1)
            r0.artworkView = r15
            r18 = r14
            android.widget.FrameLayout$LayoutParams r14 = r24.centeredParams()
            r15.setLayoutParams(r14)
            android.widget.ImageView$ScaleType r14 = android.widget.ImageView.ScaleType.FIT_XY
            r15.setScaleType(r14)
            com.google.android.exoplayer2.ui.SubtitleView r14 = new com.google.android.exoplayer2.ui.SubtitleView
            r14.<init>(r1)
            r0.subtitleView = r14
            r19 = r13
            android.widget.FrameLayout$LayoutParams r13 = r24.defaultParams()
            r14.setLayoutParams(r13)
            android.widget.ProgressBar r13 = new android.widget.ProgressBar
            r13.<init>(r1)
            r0.bufferingView = r13
            r20 = r12
            android.widget.FrameLayout$LayoutParams r12 = new android.widget.FrameLayout$LayoutParams
            r21 = r2
            r2 = 1115815936(0x42820000, float:65.0)
            r22 = r8
            int r8 = r0.convertToDp(r2)
            int r2 = r0.convertToDp(r2)
            r23 = r4
            r4 = 17
            r12.<init>(r8, r2, r4)
            r13.setLayoutParams(r12)
            r2 = r13
            android.widget.ProgressBar r2 = (android.widget.ProgressBar) r2
            r2 = 1
            r13.setIndeterminate(r2)
            int r4 = android.os.Build.VERSION.SDK_INT
            r8 = 21
            if (r4 < r8) goto L_0x00fa
            r4 = r13
            android.widget.ProgressBar r4 = (android.widget.ProgressBar) r4
            android.content.res.ColorStateList r4 = android.content.res.ColorStateList.valueOf(r6)
            r13.setIndeterminateTintList(r4)
        L_0x00fa:
            android.widget.TextView r4 = new android.widget.TextView
            r4.<init>(r1)
            r0.errorMessageView = r4
            android.widget.FrameLayout$LayoutParams r6 = r24.centeredParams()
            r4.setLayoutParams(r6)
            r6 = 1098907648(0x41800000, float:16.0)
            int r6 = r0.convertToDp(r6)
            r4.setPadding(r6, r6, r6, r6)
            r4.setBackgroundColor(r7)
            r3.addView(r9)
            r3.addView(r15)
            r3.addView(r14)
            r3.addView(r13)
            r3.addView(r4)
            r6 = 262144(0x40000, float:3.67342E-40)
            r0.setDescendantFocusability(r6)
            setResizeModeRaw(r3, r11)
            r9.setBackgroundColor(r5)
            if (r10 == 0) goto L_0x016d
            android.view.ViewGroup$LayoutParams r5 = new android.view.ViewGroup$LayoutParams
            r6 = -1
            r5.<init>(r6, r6)
            switch(r10) {
                case 2: goto L_0x014d;
                case 3: goto L_0x0145;
                case 4: goto L_0x013f;
                default: goto L_0x0139;
            }
        L_0x0139:
            android.view.SurfaceView r2 = new android.view.SurfaceView
            r2.<init>(r1)
            goto L_0x0152
        L_0x013f:
            com.google.android.exoplayer2.video.VideoDecoderGLSurfaceView r2 = new com.google.android.exoplayer2.video.VideoDecoderGLSurfaceView
            r2.<init>(r1)
            goto L_0x0152
        L_0x0145:
            com.google.android.exoplayer2.video.spherical.SphericalGLSurfaceView r6 = new com.google.android.exoplayer2.video.spherical.SphericalGLSurfaceView
            r6.<init>(r1)
            r0.surfaceView = r6
            goto L_0x0155
        L_0x014d:
            android.view.TextureView r2 = new android.view.TextureView
            r2.<init>(r1)
        L_0x0152:
            r0.surfaceView = r2
            r2 = 0
        L_0x0155:
            android.view.View r6 = r0.surfaceView
            r6.setLayoutParams(r5)
            android.view.View r5 = r0.surfaceView
            r6 = r23
            r5.setOnClickListener(r6)
            android.view.View r5 = r0.surfaceView
            r7 = 0
            r5.setClickable(r7)
            android.view.View r5 = r0.surfaceView
            r3.addView(r5, r7)
            goto L_0x0174
        L_0x016d:
            r6 = r23
            r7 = 0
            r2 = 0
            r0.surfaceView = r2
            r2 = 0
        L_0x0174:
            r0.surfaceViewIgnoresVideoAspectRatio = r2
            android.widget.FrameLayout r2 = new android.widget.FrameLayout
            r2.<init>(r1)
            r0.adOverlayFrameLayout = r2
            android.widget.FrameLayout$LayoutParams r5 = r24.defaultParams()
            r2.setLayoutParams(r5)
            android.widget.FrameLayout r2 = new android.widget.FrameLayout
            r2.<init>(r1)
            r0.overlayFrameLayout = r2
            android.widget.FrameLayout$LayoutParams r5 = r24.defaultParams()
            r2.setLayoutParams(r5)
            r2 = r22
            r0.useArtwork = r2
            r14.setUserDefaultStyle()
            r14.setUserDefaultTextSize()
            r2 = 8
            r13.setVisibility(r2)
            r5 = r21
            r0.showBuffering = r5
            r4.setVisibility(r2)
            com.google.android.exoplayer2.ui.StyledPlayerControlView r2 = new com.google.android.exoplayer2.ui.StyledPlayerControlView
            r4 = r26
            r5 = r27
            r2.<init>(r1, r4, r5)
            r0.controller = r2
            android.widget.FrameLayout$LayoutParams r1 = new android.widget.FrameLayout$LayoutParams
            r4 = -1
            r1.<init>(r4, r4)
            r2.setLayoutParams(r1)
            r1 = r20
            r0.controllerShowTimeoutMs = r1
            r1 = r19
            r0.controllerHideOnTouch = r1
            r1 = r18
            r0.controllerAutoShow = r1
            r1 = r17
            r0.controllerHideDuringAds = r1
            r1 = r16
            r0.useController = r1
            r2.hideImmediately()
            r2.addVisibilityListener(r6)
            r24.updateContentDescription()
            r0.addView(r3)
            goto L_0x003a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.StyledPlayerView.<init>(android.content.Context, com.google.android.exoplayer2.ui.TimeBarAttributes, com.google.android.exoplayer2.ui.PlayerAttributes):void");
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

    private int convertToDp(float f) {
        return (int) TypedValue.applyDimension(1, f, Resources.getSystem().getDisplayMetrics());
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
            boolean z2 = this.controller.isFullyVisible() && this.controller.getShowTimeoutMs() <= 0;
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
        if (!this.controllerAutoShow || this.player.E().c()) {
            return false;
        }
        return h == 1 || h == 4 || !((Player) Assertions.b((Object) this.player)).l();
    }

    private void showController(boolean z) {
        if (useController()) {
            this.controller.setShowTimeoutMs(z ? 0 : this.controllerShowTimeoutMs);
            this.controller.show();
        }
    }

    public static void switchTargetView(Player player2, StyledPlayerView styledPlayerView, StyledPlayerView styledPlayerView2) {
        if (styledPlayerView != styledPlayerView2) {
            if (styledPlayerView2 != null) {
                styledPlayerView2.setPlayer(player2);
            }
            if (styledPlayerView != null) {
                styledPlayerView.setPlayer((Player) null);
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean toggleControllerVisibility() {
        if (useController() && this.player != null) {
            if (!this.controller.isFullyVisible()) {
                maybeShowController(true);
                return true;
            } else if (this.controllerHideOnTouch) {
                this.controller.hide();
                return true;
            }
        }
        return false;
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.StyledPlayerView.updateBuffering():void");
    }

    /* access modifiers changed from: private */
    public void updateContentDescription() {
        StyledPlayerControlView styledPlayerControlView = this.controller;
        String str = null;
        if (styledPlayerControlView == null || !this.useController) {
            setContentDescription((CharSequence) null);
        } else if (styledPlayerControlView.isFullyVisible()) {
            if (this.controllerHideOnTouch) {
                str = "Hide player controls";
            }
            setContentDescription(str);
        } else {
            setContentDescription("Show player controls");
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
        if ((!isDpadKey || !useController() || this.controller.isFullyVisible()) && !dispatchMediaKeyEvent(keyEvent) && !super.dispatchKeyEvent(keyEvent)) {
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
        StyledPlayerControlView styledPlayerControlView = this.controller;
        if (styledPlayerControlView != null) {
            arrayList.add(new AdOverlayInfo(styledPlayerControlView, 0));
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

    public boolean getIsFullscreen() {
        Assertions.a((Object) this.controller);
        return this.controller.getIsFullscreen();
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
        StyledPlayerControlView styledPlayerControlView = this.controller;
        if (styledPlayerControlView != null) {
            styledPlayerControlView.hide();
        }
    }

    public boolean isControllerFullyVisible() {
        StyledPlayerControlView styledPlayerControlView = this.controller;
        return styledPlayerControlView != null && styledPlayerControlView.isFullyVisible();
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
                return performClick();
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

    public void setControllerOnFullScreenModeChangedListener(StyledPlayerControlView.OnFullScreenModeChangedListener onFullScreenModeChangedListener) {
        Assertions.a((Object) this.controller);
        this.controller.setOnFullScreenModeChangedListener(onFullScreenModeChangedListener);
    }

    public void setControllerOnSettingsWindowDismissListener(StyledPlayerControlView.OnSettingsWindowDismissListener onSettingsWindowDismissListener) {
        Assertions.a((Object) this.controller);
        this.controller.setOnSettingsWindowDismissListener(onSettingsWindowDismissListener);
    }

    public void setControllerShowTimeoutMs(int i) {
        Assertions.a((Object) this.controller);
        this.controllerShowTimeoutMs = i;
        if (this.controller.isFullyVisible()) {
            showController();
        }
    }

    public void setControllerVisibilityListener(StyledPlayerControlView.VisibilityListener visibilityListener) {
        Assertions.a((Object) this.controller);
        StyledPlayerControlView.VisibilityListener visibilityListener2 = this.controllerVisibilityListener;
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
                View view = this.surfaceView;
                if (view instanceof TextureView) {
                    player3.b((TextureView) view);
                } else if (view instanceof SurfaceView) {
                    player3.b((SurfaceView) view);
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

    public void setShowSubtitleButton(boolean z) {
        Assertions.a((Object) this.controller);
        this.controller.setShowSubtitleButton(z);
    }

    public void setShowVideoSettingsButton(boolean z) {
        Assertions.a((Object) this.controller);
        this.controller.setShowVideoSettingsButton(z);
    }

    public void setShowVrButton(boolean z) {
        Assertions.a((Object) this.controller);
        this.controller.setShowVrButton(z);
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
        StyledPlayerControlView styledPlayerControlView;
        Player player2;
        Assertions.b(!z || this.controller != null);
        if (this.useController != z) {
            this.useController = z;
            if (useController()) {
                styledPlayerControlView = this.controller;
                player2 = this.player;
            } else {
                StyledPlayerControlView styledPlayerControlView2 = this.controller;
                if (styledPlayerControlView2 != null) {
                    styledPlayerControlView2.hide();
                    styledPlayerControlView = this.controller;
                    player2 = null;
                }
                updateContentDescription();
            }
            styledPlayerControlView.setPlayer(player2);
            updateContentDescription();
        }
    }

    public void setVideoSettingsButtonListener(View.OnClickListener onClickListener) {
        Assertions.a((Object) this.controller);
        this.controller.setVideoSettingsButtonListener(onClickListener);
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
