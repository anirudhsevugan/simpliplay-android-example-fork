package com.google.android.exoplayer2.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ControlDispatcher;
import com.google.android.exoplayer2.DefaultControlDispatcher;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
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
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.TimeBar;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.RepeatModeUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoListener$$CC;
import com.google.android.exoplayer2.video.VideoSize;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlayerControlView extends FrameLayout {
    public static final int DEFAULT_REPEAT_TOGGLE_MODES = 0;
    public static final int DEFAULT_SHOW_TIMEOUT_MS = 5000;
    public static final int DEFAULT_TIME_BAR_MIN_UPDATE_INTERVAL_MS = 200;
    private static final String IC_FAST_FORWARD = "ic_fast_forward.png";
    private static final String IC_NEXT = "ic_next.png";
    private static final String IC_PAUSE = "ic_pause.png";
    private static final String IC_PLAY = "ic_play.png";
    private static final String IC_PREVIOUS = "ic_previous.png";
    private static final String IC_REPEAT_ALL = "ic_repeat_all.png";
    private static final String IC_REPEAT_OFF = "ic_repeat_off.png";
    private static final String IC_REPEAT_ONE = "ic_repeat_one.png";
    private static final String IC_REWIND = "ic_rewind.png";
    private static final String IC_SHUFFLE_OFF = "ic_shuffle_off.png";
    private static final String IC_SHUFFLE_ON = "ic_shuffle_on.png";
    private static final String LOG_TAG = "ExoplayerUi";
    private static final int MAX_UPDATE_INTERVAL_MS = 1000;
    public static final int MAX_WINDOWS_FOR_MULTI_WINDOW_TIME_BAR = 100;
    private long[] adGroupTimesMs;
    private final ComponentListener componentListener;
    /* access modifiers changed from: private */
    public ControlDispatcher controlDispatcher;
    private long currentWindowOffset;
    private final TextView durationView;
    private long[] extraAdGroupTimesMs;
    private boolean[] extraPlayedAdGroups;
    /* access modifiers changed from: private */
    public final View fastForwardButton;
    /* access modifiers changed from: private */
    public final StringBuilder formatBuilder;
    /* access modifiers changed from: private */
    public final Formatter formatter;
    private final Runnable hideAction;
    private long hideAtMs;
    private boolean isAttachedToWindow;
    private boolean multiWindowTimeBar;
    /* access modifiers changed from: private */
    public final View nextButton;
    /* access modifiers changed from: private */
    public final View pauseButton;
    private final Timeline.Period period;
    /* access modifiers changed from: private */
    public final View playButton;
    private PlaybackPreparer playbackPreparer;
    private boolean[] playedAdGroups;
    /* access modifiers changed from: private */
    public Player player;
    /* access modifiers changed from: private */
    public final TextView positionView;
    /* access modifiers changed from: private */
    public final View previousButton;
    private ProgressUpdateListener progressUpdateListener;
    private final Drawable repeatAllButtonDrawable;
    private final Drawable repeatOffButtonDrawable;
    private final Drawable repeatOneButtonDrawable;
    /* access modifiers changed from: private */
    public final ImageView repeatToggleButton;
    /* access modifiers changed from: private */
    public int repeatToggleModes;
    /* access modifiers changed from: private */
    public final View rewindButton;
    /* access modifiers changed from: private */
    public boolean scrubbing;
    private boolean showFastForwardButton;
    private boolean showMultiWindowTimeBar;
    private boolean showNextButton;
    private boolean showPreviousButton;
    private boolean showRewindButton;
    private boolean showShuffleButton;
    private int showTimeoutMs;
    /* access modifiers changed from: private */
    public final ImageView shuffleButton;
    private final Drawable shuffleOffButtonDrawable;
    private final Drawable shuffleOnButtonDrawable;
    private final TimeBar timeBar;
    private int timeBarMinUpdateIntervalMs;
    private final Runnable updateProgressAction;
    private final CopyOnWriteArrayList visibilityListeners;
    private final View vrButton;
    private final Timeline.Window window;

    public interface VisibilityListener {
        void onVisibilityChange(int i);
    }

    final class ComponentListener implements View.OnClickListener, Player.Listener, TimeBar.OnScrubListener {
        private ComponentListener() {
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
            Player access$500 = PlayerControlView.this.player;
            if (access$500 != null) {
                if (PlayerControlView.this.nextButton == view) {
                    PlayerControlView.this.controlDispatcher.c(access$500);
                } else if (PlayerControlView.this.previousButton == view) {
                    PlayerControlView.this.controlDispatcher.b(access$500);
                } else if (PlayerControlView.this.fastForwardButton == view) {
                    if (access$500.h() != 4) {
                        PlayerControlView.this.controlDispatcher.e(access$500);
                    }
                } else if (PlayerControlView.this.rewindButton == view) {
                    PlayerControlView.this.controlDispatcher.d(access$500);
                } else if (PlayerControlView.this.playButton == view) {
                    PlayerControlView.this.dispatchPlay(access$500);
                } else if (PlayerControlView.this.pauseButton == view) {
                    PlayerControlView.this.dispatchPause(access$500);
                } else if (PlayerControlView.this.repeatToggleButton == view) {
                    PlayerControlView.this.controlDispatcher.a(access$500, RepeatModeUtil.a(access$500.m(), PlayerControlView.this.repeatToggleModes));
                } else if (PlayerControlView.this.shuffleButton == view) {
                    PlayerControlView.this.controlDispatcher.b(access$500, !access$500.n());
                }
            }
        }

        public final void onCues(List list) {
            Player$Listener$$CC.a();
        }

        public final void onDeviceInfoChanged(DeviceInfo deviceInfo) {
            DeviceListener$$CC.a();
        }

        public final void onDeviceVolumeChanged(int i, boolean z) {
            DeviceListener$$CC.b();
        }

        public final void onEvents(Player player, Player.Events events) {
            if (events.a(5, 6)) {
                PlayerControlView.this.updatePlayPauseButton();
            }
            if (events.a(5, 6, 8)) {
                PlayerControlView.this.bridge$lambda$0$PlayerControlView();
            }
            if (events.a(9)) {
                PlayerControlView.this.updateRepeatModeButton();
            }
            if (events.a(10)) {
                PlayerControlView.this.updateShuffleButton();
            }
            if (events.a(9, 10, 12, 0)) {
                PlayerControlView.this.updateNavigation();
            }
            if (events.a(12, 0)) {
                PlayerControlView.this.updateTimeline();
            }
        }

        public final void onIsLoadingChanged(boolean z) {
            Player$EventListener$$CC.g();
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
            Player$Listener$$CC.b();
        }

        public final void onPlayWhenReadyChanged(boolean z, int i) {
            Player$EventListener$$CC.l();
        }

        public final void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            Player$EventListener$$CC.t();
        }

        public final void onPlaybackStateChanged(int i) {
            Player$EventListener$$CC.k();
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

        public final void onRenderedFirstFrame() {
            VideoListener$$CC.d();
        }

        public final void onRepeatModeChanged(int i) {
            Player$EventListener$$CC.o();
        }

        public final void onScrubMove(TimeBar timeBar, long j) {
            if (PlayerControlView.this.positionView != null) {
                PlayerControlView.this.positionView.setText(Util.a(PlayerControlView.this.formatBuilder, PlayerControlView.this.formatter, j));
            }
        }

        public final void onScrubStart(TimeBar timeBar, long j) {
            boolean unused = PlayerControlView.this.scrubbing = true;
            if (PlayerControlView.this.positionView != null) {
                PlayerControlView.this.positionView.setText(Util.a(PlayerControlView.this.formatBuilder, PlayerControlView.this.formatter, j));
            }
        }

        public final void onScrubStop(TimeBar timeBar, long j, boolean z) {
            boolean unused = PlayerControlView.this.scrubbing = false;
            if (!z && PlayerControlView.this.player != null) {
                PlayerControlView playerControlView = PlayerControlView.this;
                playerControlView.seekToTimeBarPosition(playerControlView.player, j);
            }
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
            Player$EventListener$$CC.d();
        }

        public final void onVideoSizeChanged(int i, int i2, int i3, float f) {
            VideoListener$$CC.b();
        }

        public final void onVideoSizeChanged(VideoSize videoSize) {
            VideoListener$$CC.a();
        }

        public final void onVolumeChanged(float f) {
            AudioListener$$CC.c();
        }
    }

    public interface ProgressUpdateListener {
        void onProgressUpdate(long j, long j2);
    }

    static {
        ExoPlayerLibraryInfo.a("goog.exo.ui");
    }

    public PlayerControlView(Context context) {
        this(context, TimeBarAttributes.createDefault());
    }

    public PlayerControlView(Context context, TimeBarAttributes timeBarAttributes) {
        this(context, timeBarAttributes, PlayerAttributes.createDefault());
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public PlayerControlView(android.content.Context r23, com.google.android.exoplayer2.ui.TimeBarAttributes r24, com.google.android.exoplayer2.ui.PlayerAttributes r25) {
        /*
            r22 = this;
            r0 = r22
            r1 = r23
            r2 = 0
            r3 = 0
            r0.<init>(r1, r2, r3)
            r0.vrButton = r2
            int r4 = r25.getShowTimeoutMs()
            r0.showTimeoutMs = r4
            int r4 = r25.getRepeatToggleModes()
            r0.repeatToggleModes = r4
            int r4 = r25.getTimeBarMinUpdateIntervalMs()
            r0.timeBarMinUpdateIntervalMs = r4
            long r4 = r25.getHideAtMs()
            r0.hideAtMs = r4
            boolean r4 = r25.getShowRewindButton()
            r0.showRewindButton = r4
            boolean r4 = r25.getShowFastForwardButton()
            r0.showFastForwardButton = r4
            boolean r4 = r25.getShowPreviousButton()
            r0.showPreviousButton = r4
            boolean r4 = r25.getShowNextButton()
            r0.showNextButton = r4
            boolean r4 = r25.getShowShuffleButton()
            r0.showShuffleButton = r4
            int r4 = r25.getRewindMs()
            int r5 = r25.getFastForwardMs()
            boolean r6 = r25.isDebugMode()
            java.util.concurrent.CopyOnWriteArrayList r7 = new java.util.concurrent.CopyOnWriteArrayList
            r7.<init>()
            r0.visibilityListeners = r7
            com.google.android.exoplayer2.Timeline$Period r7 = new com.google.android.exoplayer2.Timeline$Period
            r7.<init>()
            r0.period = r7
            com.google.android.exoplayer2.Timeline$Window r7 = new com.google.android.exoplayer2.Timeline$Window
            r7.<init>()
            r0.window = r7
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r0.formatBuilder = r7
            java.util.Formatter r8 = new java.util.Formatter
            java.util.Locale r9 = java.util.Locale.getDefault()
            r8.<init>(r7, r9)
            r0.formatter = r8
            long[] r7 = new long[r3]
            r0.adGroupTimesMs = r7
            boolean[] r7 = new boolean[r3]
            r0.playedAdGroups = r7
            long[] r7 = new long[r3]
            r0.extraAdGroupTimesMs = r7
            boolean[] r7 = new boolean[r3]
            r0.extraPlayedAdGroups = r7
            com.google.android.exoplayer2.ui.PlayerControlView$ComponentListener r7 = new com.google.android.exoplayer2.ui.PlayerControlView$ComponentListener
            r7.<init>()
            r0.componentListener = r7
            com.google.android.exoplayer2.DefaultControlDispatcher r2 = new com.google.android.exoplayer2.DefaultControlDispatcher
            long r8 = (long) r5
            long r4 = (long) r4
            r2.<init>(r8, r4)
            r0.controlDispatcher = r2
            com.google.android.exoplayer2.ui.PlayerControlView$$Lambda$0 r2 = new com.google.android.exoplayer2.ui.PlayerControlView$$Lambda$0
            r2.<init>(r0)
            r0.updateProgressAction = r2
            com.google.android.exoplayer2.ui.PlayerControlView$$Lambda$1 r2 = new com.google.android.exoplayer2.ui.PlayerControlView$$Lambda$1
            r2.<init>(r0)
            r0.hideAction = r2
            r2 = 1086324736(0x40c00000, float:6.0)
            int r2 = com.google.android.exoplayer2.ui.UiHelper.convertToDp(r2)
            android.graphics.drawable.GradientDrawable r4 = new android.graphics.drawable.GradientDrawable
            android.graphics.drawable.GradientDrawable$Orientation r5 = android.graphics.drawable.GradientDrawable.Orientation.BOTTOM_TOP
            r8 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            int[] r8 = new int[]{r8, r3}
            r4.<init>(r5, r8)
            android.widget.LinearLayout r5 = new android.widget.LinearLayout
            r5.<init>(r1)
            android.widget.FrameLayout$LayoutParams r8 = new android.widget.FrameLayout$LayoutParams
            r9 = 80
            r10 = -1
            r11 = -2
            r8.<init>(r10, r11, r9)
            r5.setLayoutParams(r8)
            r5.setLayoutDirection(r3)
            r8 = 1
            r5.setOrientation(r8)
            r5.setBackground(r4)
            r5.setPadding(r3, r2, r3, r2)
            android.widget.LinearLayout r4 = new android.widget.LinearLayout
            r4.<init>(r1)
            android.widget.LinearLayout$LayoutParams r9 = new android.widget.LinearLayout$LayoutParams
            r9.<init>(r10, r11)
            r4.setLayoutParams(r9)
            r4.setPadding(r3, r2, r3, r2)
            r4.setOrientation(r3)
            r9 = 17
            r4.setGravity(r9)
            java.lang.String r12 = "ic_play.png"
            android.graphics.drawable.Drawable r12 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r12, r6)
            android.widget.ImageButton r12 = com.google.android.exoplayer2.ui.UiHelper.createCenterImageButton(r1, r12)
            r0.playButton = r12
            java.lang.String r13 = "ic_pause.png"
            android.graphics.drawable.Drawable r13 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r13, r6)
            android.widget.ImageButton r13 = com.google.android.exoplayer2.ui.UiHelper.createCenterImageButton(r1, r13)
            r0.pauseButton = r13
            java.lang.String r14 = "ic_rewind.png"
            android.graphics.drawable.Drawable r14 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r14, r6)
            android.widget.ImageButton r14 = com.google.android.exoplayer2.ui.UiHelper.createCenterImageButton(r1, r14)
            r0.rewindButton = r14
            java.lang.String r15 = "ic_fast_forward.png"
            android.graphics.drawable.Drawable r15 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r15, r6)
            android.widget.ImageButton r15 = com.google.android.exoplayer2.ui.UiHelper.createCenterImageButton(r1, r15)
            r0.fastForwardButton = r15
            java.lang.String r8 = "ic_next.png"
            android.graphics.drawable.Drawable r8 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r8, r6)
            android.widget.ImageButton r8 = com.google.android.exoplayer2.ui.UiHelper.createCenterImageButton(r1, r8)
            r0.nextButton = r8
            java.lang.String r3 = "ic_previous.png"
            android.graphics.drawable.Drawable r3 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r3, r6)
            android.widget.ImageButton r3 = com.google.android.exoplayer2.ui.UiHelper.createCenterImageButton(r1, r3)
            r0.previousButton = r3
            java.lang.String r9 = "ic_repeat_off.png"
            android.graphics.drawable.Drawable r10 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r9, r6)
            android.widget.ImageButton r10 = com.google.android.exoplayer2.ui.UiHelper.createCenterImageButton(r1, r10)
            r0.repeatToggleButton = r10
            java.lang.String r11 = "ic_shuffle_off.png"
            r17 = r9
            android.graphics.drawable.Drawable r9 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r11, r6)
            android.widget.ImageButton r9 = com.google.android.exoplayer2.ui.UiHelper.createCenterImageButton(r1, r9)
            r0.shuffleButton = r9
            r4.addView(r3)
            r4.addView(r10)
            r4.addView(r14)
            r4.addView(r12)
            r4.addView(r13)
            r4.addView(r15)
            r4.addView(r9)
            r4.addView(r8)
            r18 = r11
            android.widget.LinearLayout r11 = new android.widget.LinearLayout
            r11.<init>(r1)
            r19 = r6
            android.widget.LinearLayout$LayoutParams r6 = new android.widget.LinearLayout$LayoutParams
            r16 = r9
            r20 = r10
            r9 = -2
            r10 = -1
            r6.<init>(r10, r9)
            r11.setLayoutParams(r6)
            r6 = 17
            r11.setGravity(r6)
            r6 = 0
            r11.setOrientation(r6)
            r11.setPadding(r6, r2, r6, r2)
            android.widget.TextView r9 = new android.widget.TextView
            r9.<init>(r1)
            r0.positionView = r9
            int r10 = com.google.android.exoplayer2.ui.UiHelper.DIM_WHITE
            r9.setTextColor(r10)
            r9.setPadding(r2, r6, r2, r6)
            r10 = 2
            r6 = 1096810496(0x41600000, float:14.0)
            r9.setTextSize(r10, r6)
            java.lang.String r6 = "00:00"
            r9.setText(r6)
            android.graphics.Typeface r10 = r9.getTypeface()
            r21 = r8
            r8 = 1
            r9.setTypeface(r10, r8)
            r8 = 0
            r9.setIncludeFontPadding(r8)
            android.widget.LinearLayout$LayoutParams r10 = new android.widget.LinearLayout$LayoutParams
            r8 = -2
            r10.<init>(r8, r8)
            r9.setLayoutParams(r10)
            android.widget.TextView r8 = new android.widget.TextView
            r8.<init>(r1)
            r0.durationView = r8
            int r10 = com.google.android.exoplayer2.ui.UiHelper.DIM_WHITE
            r8.setTextColor(r10)
            r10 = 0
            r8.setPadding(r2, r10, r2, r10)
            r2 = 2
            r10 = 1096810496(0x41600000, float:14.0)
            r8.setTextSize(r2, r10)
            r8.setText(r6)
            android.graphics.Typeface r2 = r8.getTypeface()
            r6 = 1
            r8.setTypeface(r2, r6)
            r2 = 0
            r8.setIncludeFontPadding(r2)
            android.widget.LinearLayout$LayoutParams r6 = new android.widget.LinearLayout$LayoutParams
            r10 = -2
            r6.<init>(r10, r10)
            r8.setLayoutParams(r6)
            com.google.android.exoplayer2.ui.DefaultTimeBar r6 = new com.google.android.exoplayer2.ui.DefaultTimeBar
            r2 = r24
            r6.<init>(r1, r2)
            android.widget.LinearLayout$LayoutParams r2 = new android.widget.LinearLayout$LayoutParams
            r1 = 1065353216(0x3f800000, float:1.0)
            r25 = r3
            r3 = 0
            r2.<init>(r3, r10, r1)
            r6.setLayoutParams(r2)
            r0.timeBar = r6
            r11.addView(r9)
            r11.addView(r6)
            r11.addView(r8)
            r5.addView(r4)
            r5.addView(r11)
            r1 = 262144(0x40000, float:3.67342E-40)
            r0.setDescendantFocusability(r1)
            r6.addListener(r7)
            r12.setOnClickListener(r7)
            r13.setOnClickListener(r7)
            r14.setOnClickListener(r7)
            r15.setOnClickListener(r7)
            r1 = r25
            r1.setOnClickListener(r7)
            r1 = r21
            r1.setOnClickListener(r7)
            r1 = r16
            r1.setOnClickListener(r7)
            r1 = r20
            r1.setOnClickListener(r7)
            r1 = r23
            r3 = r17
            r2 = r19
            android.graphics.drawable.Drawable r3 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r3, r2)
            r0.repeatOffButtonDrawable = r3
            java.lang.String r3 = "ic_repeat_one.png"
            android.graphics.drawable.Drawable r3 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r3, r2)
            r0.repeatOneButtonDrawable = r3
            java.lang.String r3 = "ic_repeat_all.png"
            android.graphics.drawable.Drawable r3 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r3, r2)
            r0.repeatAllButtonDrawable = r3
            java.lang.String r3 = "ic_shuffle_on.png"
            android.graphics.drawable.Drawable r3 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r3, r2)
            r0.shuffleOnButtonDrawable = r3
            r3 = r18
            android.graphics.drawable.Drawable r1 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r3, r2)
            r0.shuffleOffButtonDrawable = r1
            r0.addView(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.PlayerControlView.<init>(android.content.Context, com.google.android.exoplayer2.ui.TimeBarAttributes, com.google.android.exoplayer2.ui.PlayerAttributes):void");
    }

    private static boolean canShowMultiWindowTimeBar(Timeline timeline, Timeline.Window window2) {
        if (timeline.a() > 100) {
            return false;
        }
        int a = timeline.a();
        for (int i = 0; i < a; i++) {
            if (timeline.a(i, window2, 0).m == -9223372036854775807L) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void dispatchPause(Player player2) {
        this.controlDispatcher.a(player2, false);
    }

    /* access modifiers changed from: private */
    public void dispatchPlay(Player player2) {
        int h = player2.h();
        if (h == 1) {
            if (this.playbackPreparer == null) {
                this.controlDispatcher.a(player2);
            }
        } else if (h == 4) {
            seekTo(player2, player2.r(), -9223372036854775807L);
        }
        this.controlDispatcher.a(player2, true);
    }

    private void dispatchPlayPause(Player player2) {
        int h = player2.h();
        if (h == 1 || h == 4 || !player2.l()) {
            dispatchPlay(player2);
        } else {
            dispatchPause(player2);
        }
    }

    private void hideAfterTimeout() {
        removeCallbacks(this.hideAction);
        if (this.showTimeoutMs > 0) {
            long uptimeMillis = SystemClock.uptimeMillis();
            int i = this.showTimeoutMs;
            this.hideAtMs = uptimeMillis + ((long) i);
            if (this.isAttachedToWindow) {
                postDelayed(this.hideAction, (long) i);
                return;
            }
            return;
        }
        this.hideAtMs = -9223372036854775807L;
    }

    private static boolean isHandledMediaKey(int i) {
        return i == 90 || i == 89 || i == 85 || i == 79 || i == 126 || i == 127 || i == 87 || i == 88;
    }

    private void requestPlayPauseFocus() {
        View view;
        View view2;
        boolean shouldShowPauseButton = shouldShowPauseButton();
        if (!shouldShowPauseButton && (view2 = this.playButton) != null) {
            view2.requestFocus();
        } else if (shouldShowPauseButton && (view = this.pauseButton) != null) {
            view.requestFocus();
        }
    }

    private boolean seekTo(Player player2, int i, long j) {
        return this.controlDispatcher.a(player2, i, j);
    }

    /* access modifiers changed from: private */
    public void seekToTimeBarPosition(Player player2, long j) {
        int i;
        Timeline E = player2.E();
        if (!this.multiWindowTimeBar || E.c()) {
            i = player2.r();
        } else {
            int a = E.a();
            i = 0;
            while (true) {
                long a2 = C.a(E.a(i, this.window, 0).m);
                if (j < a2) {
                    break;
                } else if (i == a - 1) {
                    j = a2;
                    break;
                } else {
                    j -= a2;
                    i++;
                }
            }
        }
        seekTo(player2, i, j);
        bridge$lambda$0$PlayerControlView();
    }

    private boolean shouldShowPauseButton() {
        Player player2 = this.player;
        return (player2 == null || player2.h() == 4 || this.player.h() == 1 || !this.player.l()) ? false : true;
    }

    private void updateAll() {
        updatePlayPauseButton();
        updateNavigation();
        updateRepeatModeButton();
        updateShuffleButton();
        updateTimeline();
    }

    private void updateButton(boolean z, boolean z2, View view) {
        if (view != null) {
            view.setEnabled(z2);
            view.setAlpha(z2 ? 1.0f : 0.33f);
            view.setVisibility(z ? 0 : 8);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateNavigation() {
        /*
            r8 = this;
            boolean r0 = r8.isVisible()
            if (r0 == 0) goto L_0x00a1
            boolean r0 = r8.isAttachedToWindow
            if (r0 != 0) goto L_0x000c
            goto L_0x00a1
        L_0x000c:
            com.google.android.exoplayer2.Player r0 = r8.player
            r1 = 0
            if (r0 == 0) goto L_0x007a
            com.google.android.exoplayer2.Timeline r2 = r0.E()
            boolean r3 = r2.c()
            if (r3 != 0) goto L_0x007a
            boolean r3 = r0.w()
            if (r3 != 0) goto L_0x007a
            r3 = 4
            boolean r3 = r0.a((int) r3)
            int r4 = r0.r()
            com.google.android.exoplayer2.Timeline$Window r5 = r8.window
            r6 = 0
            r2.a((int) r4, (com.google.android.exoplayer2.Timeline.Window) r5, (long) r6)
            r2 = 1
            if (r3 != 0) goto L_0x0046
            com.google.android.exoplayer2.Timeline$Window r4 = r8.window
            boolean r4 = r4.a()
            if (r4 == 0) goto L_0x0046
            r4 = 6
            boolean r4 = r0.a((int) r4)
            if (r4 == 0) goto L_0x0044
            goto L_0x0046
        L_0x0044:
            r4 = 0
            goto L_0x0047
        L_0x0046:
            r4 = 1
        L_0x0047:
            if (r3 == 0) goto L_0x0053
            com.google.android.exoplayer2.ControlDispatcher r5 = r8.controlDispatcher
            boolean r5 = r5.a()
            if (r5 == 0) goto L_0x0053
            r5 = 1
            goto L_0x0054
        L_0x0053:
            r5 = 0
        L_0x0054:
            if (r3 == 0) goto L_0x0060
            com.google.android.exoplayer2.ControlDispatcher r6 = r8.controlDispatcher
            boolean r6 = r6.b()
            if (r6 == 0) goto L_0x0060
            r6 = 1
            goto L_0x0061
        L_0x0060:
            r6 = 0
        L_0x0061:
            com.google.android.exoplayer2.Timeline$Window r7 = r8.window
            boolean r7 = r7.a()
            if (r7 == 0) goto L_0x006f
            com.google.android.exoplayer2.Timeline$Window r7 = r8.window
            boolean r7 = r7.i
            if (r7 != 0) goto L_0x0076
        L_0x006f:
            r7 = 5
            boolean r0 = r0.a((int) r7)
            if (r0 == 0) goto L_0x0077
        L_0x0076:
            r1 = 1
        L_0x0077:
            r0 = r1
            r1 = r4
            goto L_0x007e
        L_0x007a:
            r0 = 0
            r3 = 0
            r5 = 0
            r6 = 0
        L_0x007e:
            boolean r2 = r8.showPreviousButton
            android.view.View r4 = r8.previousButton
            r8.updateButton(r2, r1, r4)
            boolean r1 = r8.showRewindButton
            android.view.View r2 = r8.rewindButton
            r8.updateButton(r1, r5, r2)
            boolean r1 = r8.showFastForwardButton
            android.view.View r2 = r8.fastForwardButton
            r8.updateButton(r1, r6, r2)
            boolean r1 = r8.showNextButton
            android.view.View r2 = r8.nextButton
            r8.updateButton(r1, r0, r2)
            com.google.android.exoplayer2.ui.TimeBar r0 = r8.timeBar
            if (r0 == 0) goto L_0x00a1
            r0.setEnabled(r3)
        L_0x00a1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.PlayerControlView.updateNavigation():void");
    }

    /* access modifiers changed from: private */
    public void updatePlayPauseButton() {
        boolean z;
        if (isVisible() && this.isAttachedToWindow) {
            boolean shouldShowPauseButton = shouldShowPauseButton();
            View view = this.playButton;
            int i = 8;
            boolean z2 = true;
            if (view != null) {
                z = (shouldShowPauseButton && view.isFocused()) | false;
                this.playButton.setVisibility(shouldShowPauseButton ? 8 : 0);
            } else {
                z = false;
            }
            View view2 = this.pauseButton;
            if (view2 != null) {
                if (shouldShowPauseButton || !view2.isFocused()) {
                    z2 = false;
                }
                z |= z2;
                View view3 = this.pauseButton;
                if (shouldShowPauseButton) {
                    i = 0;
                }
                view3.setVisibility(i);
            }
            if (z) {
                requestPlayPauseFocus();
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: updateProgress */
    public void bridge$lambda$0$PlayerControlView() {
        long j;
        long j2;
        if (isVisible() && this.isAttachedToWindow) {
            Player player2 = this.player;
            if (player2 != null) {
                j2 = this.currentWindowOffset + player2.z();
                j = this.currentWindowOffset + player2.A();
            } else {
                j2 = 0;
                j = 0;
            }
            TextView textView = this.positionView;
            if (textView != null && !this.scrubbing) {
                textView.setText(Util.a(this.formatBuilder, this.formatter, j2));
            }
            TimeBar timeBar2 = this.timeBar;
            if (timeBar2 != null) {
                timeBar2.setPosition(j2);
                this.timeBar.setBufferedPosition(j);
            }
            ProgressUpdateListener progressUpdateListener2 = this.progressUpdateListener;
            if (progressUpdateListener2 != null) {
                progressUpdateListener2.onProgressUpdate(j2, j);
            }
            removeCallbacks(this.updateProgressAction);
            int h = player2 == null ? 1 : player2.h();
            long j3 = 1000;
            if (player2 != null && player2.a()) {
                TimeBar timeBar3 = this.timeBar;
                long min = Math.min(timeBar3 != null ? timeBar3.getPreferredUpdateDelay() : 1000, 1000 - (j2 % 1000));
                float f = player2.o().b;
                if (f > 0.0f) {
                    j3 = (long) (((float) min) / f);
                }
                postDelayed(this.updateProgressAction, Util.a(j3, (long) this.timeBarMinUpdateIntervalMs, 1000));
            } else if (h != 4 && h != 1) {
                postDelayed(this.updateProgressAction, 1000);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateRepeatModeButton() {
        ImageView imageView;
        String str;
        ImageView imageView2;
        if (isVisible() && this.isAttachedToWindow && (imageView = this.repeatToggleButton) != null) {
            if (this.repeatToggleModes == 0) {
                updateButton(false, false, imageView);
                return;
            }
            Player player2 = this.player;
            if (player2 == null) {
                updateButton(true, false, imageView);
                this.repeatToggleButton.setImageDrawable(this.repeatOffButtonDrawable);
                this.repeatToggleButton.setContentDescription("Repeat none");
                return;
            }
            updateButton(true, true, imageView);
            switch (player2.m()) {
                case 0:
                    this.repeatToggleButton.setImageDrawable(this.repeatOffButtonDrawable);
                    this.repeatToggleButton.setContentDescription("Repeat none");
                    break;
                case 1:
                    this.repeatToggleButton.setImageDrawable(this.repeatOneButtonDrawable);
                    imageView2 = this.repeatToggleButton;
                    str = "Repeat one";
                    break;
                case 2:
                    this.repeatToggleButton.setImageDrawable(this.repeatAllButtonDrawable);
                    imageView2 = this.repeatToggleButton;
                    str = "Repeat all";
                    break;
            }
            imageView2.setContentDescription(str);
            this.repeatToggleButton.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void updateShuffleButton() {
        ImageView imageView;
        String str;
        ImageView imageView2;
        if (isVisible() && this.isAttachedToWindow && (imageView = this.shuffleButton) != null) {
            Player player2 = this.player;
            if (!this.showShuffleButton) {
                updateButton(false, false, imageView);
                return;
            }
            if (player2 == null) {
                updateButton(true, false, imageView);
                this.shuffleButton.setImageDrawable(this.shuffleOffButtonDrawable);
                imageView2 = this.shuffleButton;
            } else {
                updateButton(true, true, imageView);
                this.shuffleButton.setImageDrawable(player2.n() ? this.shuffleOnButtonDrawable : this.shuffleOffButtonDrawable);
                imageView2 = this.shuffleButton;
                if (player2.n()) {
                    str = "Shuffle on";
                    imageView2.setContentDescription(str);
                }
            }
            str = "Shuffle off";
            imageView2.setContentDescription(str);
        }
    }

    /* access modifiers changed from: private */
    public void updateTimeline() {
        Player player2 = this.player;
        if (player2 != null) {
            this.multiWindowTimeBar = this.showMultiWindowTimeBar && canShowMultiWindowTimeBar(player2.E(), this.window);
            long j = 0;
            this.currentWindowOffset = 0;
            Timeline E = player2.E();
            if (!E.c()) {
                int r = player2.r();
                boolean z = this.multiWindowTimeBar;
                int i = z ? 0 : r;
                int a = z ? E.a() - 1 : r;
                long j2 = 0;
                while (true) {
                    if (i > a) {
                        break;
                    }
                    if (i == r) {
                        this.currentWindowOffset = C.a(j2);
                    }
                    E.a(i, this.window, 0);
                    if (this.window.m == -9223372036854775807L) {
                        Assertions.b(!this.multiWindowTimeBar);
                        break;
                    }
                    for (int i2 = this.window.n; i2 <= this.window.o; i2++) {
                        E.a(i2, this.period, false);
                    }
                    j2 += this.window.m;
                    i++;
                }
                j = j2;
            }
            long a2 = C.a(j);
            TextView textView = this.durationView;
            if (textView != null) {
                textView.setText(Util.a(this.formatBuilder, this.formatter, a2));
            }
            TimeBar timeBar2 = this.timeBar;
            if (timeBar2 != null) {
                timeBar2.setDuration(a2);
                int length = this.extraAdGroupTimesMs.length;
                long[] jArr = this.adGroupTimesMs;
                if (length > jArr.length) {
                    this.adGroupTimesMs = Arrays.copyOf(jArr, length);
                    this.playedAdGroups = Arrays.copyOf(this.playedAdGroups, length);
                }
                System.arraycopy(this.extraAdGroupTimesMs, 0, this.adGroupTimesMs, 0, length);
                System.arraycopy(this.extraPlayedAdGroups, 0, this.playedAdGroups, 0, length);
                this.timeBar.setAdGroupTimesMs(this.adGroupTimesMs, this.playedAdGroups, length);
            }
            bridge$lambda$0$PlayerControlView();
        }
    }

    public void addVisibilityListener(VisibilityListener visibilityListener) {
        Assertions.b((Object) visibilityListener);
        this.visibilityListeners.add(visibilityListener);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return dispatchMediaKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent);
    }

    public boolean dispatchMediaKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        Player player2 = this.player;
        if (player2 == null || !isHandledMediaKey(keyCode)) {
            return false;
        }
        if (keyEvent.getAction() != 0) {
            return true;
        }
        if (keyCode == 90) {
            if (player2.h() == 4) {
                return true;
            }
            this.controlDispatcher.e(player2);
            return true;
        } else if (keyCode == 89) {
            this.controlDispatcher.d(player2);
            return true;
        } else if (keyEvent.getRepeatCount() != 0) {
            return true;
        } else {
            switch (keyCode) {
                case 79:
                case 85:
                    dispatchPlayPause(player2);
                    return true;
                case 87:
                    this.controlDispatcher.c(player2);
                    return true;
                case 88:
                    this.controlDispatcher.b(player2);
                    return true;
                case 126:
                    dispatchPlay(player2);
                    return true;
                case 127:
                    dispatchPause(player2);
                    return true;
                default:
                    return true;
            }
        }
    }

    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            removeCallbacks(this.hideAction);
        } else if (motionEvent.getAction() == 1) {
            hideAfterTimeout();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public Player getPlayer() {
        return this.player;
    }

    public int getRepeatToggleModes() {
        return this.repeatToggleModes;
    }

    public boolean getShowShuffleButton() {
        return this.showShuffleButton;
    }

    public int getShowTimeoutMs() {
        return this.showTimeoutMs;
    }

    public boolean getShowVrButton() {
        View view = this.vrButton;
        return view != null && view.getVisibility() == 0;
    }

    public void hide() {
        if (isVisible()) {
            setVisibility(8);
            Iterator it = this.visibilityListeners.iterator();
            while (it.hasNext()) {
                ((VisibilityListener) it.next()).onVisibilityChange(getVisibility());
            }
            removeCallbacks(this.updateProgressAction);
            removeCallbacks(this.hideAction);
            this.hideAtMs = -9223372036854775807L;
        }
    }

    public boolean isVisible() {
        return getVisibility() == 0;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.isAttachedToWindow = true;
        long j = this.hideAtMs;
        if (j != -9223372036854775807L) {
            long uptimeMillis = j - SystemClock.uptimeMillis();
            if (uptimeMillis <= 0) {
                hide();
            } else {
                postDelayed(this.hideAction, uptimeMillis);
            }
        } else if (isVisible()) {
            hideAfterTimeout();
        }
        updateAll();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.isAttachedToWindow = false;
        removeCallbacks(this.updateProgressAction);
        removeCallbacks(this.hideAction);
    }

    public void removeVisibilityListener(VisibilityListener visibilityListener) {
        this.visibilityListeners.remove(visibilityListener);
    }

    public void setControlDispatcher(ControlDispatcher controlDispatcher2) {
        if (this.controlDispatcher != controlDispatcher2) {
            this.controlDispatcher = controlDispatcher2;
            updateNavigation();
        }
    }

    public void setExtraAdGroupMarkers(long[] jArr, boolean[] zArr) {
        boolean z = false;
        if (jArr == null) {
            this.extraAdGroupTimesMs = new long[0];
            this.extraPlayedAdGroups = new boolean[0];
        } else {
            boolean[] zArr2 = (boolean[]) Assertions.b((Object) zArr);
            if (jArr.length == zArr2.length) {
                z = true;
            }
            Assertions.a(z);
            this.extraAdGroupTimesMs = jArr;
            this.extraPlayedAdGroups = zArr2;
        }
        updateTimeline();
    }

    public void setFastForwardIncrementMs(int i) {
        ControlDispatcher controlDispatcher2 = this.controlDispatcher;
        if (controlDispatcher2 instanceof DefaultControlDispatcher) {
            ((DefaultControlDispatcher) controlDispatcher2).b = (long) i;
            updateNavigation();
        }
    }

    public void setPlaybackPreparer(PlaybackPreparer playbackPreparer2) {
        this.playbackPreparer = playbackPreparer2;
    }

    public void setPlayer(Player player2) {
        boolean z = true;
        Assertions.b(Looper.myLooper() == Looper.getMainLooper());
        if (!(player2 == null || player2.f() == Looper.getMainLooper())) {
            z = false;
        }
        Assertions.a(z);
        Player player3 = this.player;
        if (player3 != player2) {
            if (player3 != null) {
                player3.b((Player.Listener) this.componentListener);
            }
            this.player = player2;
            if (player2 != null) {
                player2.a((Player.Listener) this.componentListener);
            }
            updateAll();
        }
    }

    public void setProgressUpdateListener(ProgressUpdateListener progressUpdateListener2) {
        this.progressUpdateListener = progressUpdateListener2;
    }

    public void setRepeatToggleModes(int i) {
        int i2;
        ControlDispatcher controlDispatcher2;
        Player player2;
        this.repeatToggleModes = i;
        Player player3 = this.player;
        if (player3 != null) {
            int m = player3.m();
            if (i != 0 || m == 0) {
                i2 = 2;
                if (i == 1 && m == 2) {
                    this.controlDispatcher.a(this.player, 1);
                } else if (i == 2 && m == 1) {
                    controlDispatcher2 = this.controlDispatcher;
                    player2 = this.player;
                }
            } else {
                controlDispatcher2 = this.controlDispatcher;
                player2 = this.player;
                i2 = 0;
            }
            controlDispatcher2.a(player2, i2);
        }
        updateRepeatModeButton();
    }

    public void setRewindIncrementMs(int i) {
        ControlDispatcher controlDispatcher2 = this.controlDispatcher;
        if (controlDispatcher2 instanceof DefaultControlDispatcher) {
            ((DefaultControlDispatcher) controlDispatcher2).a = (long) i;
            updateNavigation();
        }
    }

    public void setShowFastForwardButton(boolean z) {
        this.showFastForwardButton = z;
        updateNavigation();
    }

    public void setShowMultiWindowTimeBar(boolean z) {
        this.showMultiWindowTimeBar = z;
        updateTimeline();
    }

    public void setShowNextButton(boolean z) {
        this.showNextButton = z;
        updateNavigation();
    }

    public void setShowPreviousButton(boolean z) {
        this.showPreviousButton = z;
        updateNavigation();
    }

    public void setShowRewindButton(boolean z) {
        this.showRewindButton = z;
        updateNavigation();
    }

    public void setShowShuffleButton(boolean z) {
        this.showShuffleButton = z;
        updateShuffleButton();
    }

    public void setShowTimeoutMs(int i) {
        this.showTimeoutMs = i;
        if (isVisible()) {
            hideAfterTimeout();
        }
    }

    public void setShowVrButton(boolean z) {
        View view = this.vrButton;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
    }

    public void setTimeBarMinUpdateInterval(int i) {
        this.timeBarMinUpdateIntervalMs = Util.a(i, 16, 1000);
    }

    public void setVrButtonListener(View.OnClickListener onClickListener) {
        View view = this.vrButton;
        if (view != null) {
            view.setOnClickListener(onClickListener);
            updateButton(getShowVrButton(), onClickListener != null, this.vrButton);
        }
    }

    public void show() {
        if (!isVisible()) {
            setVisibility(0);
            Iterator it = this.visibilityListeners.iterator();
            while (it.hasNext()) {
                ((VisibilityListener) it.next()).onVisibilityChange(getVisibility());
            }
            updateAll();
            requestPlayPauseFocus();
        }
        hideAfterTimeout();
    }
}
