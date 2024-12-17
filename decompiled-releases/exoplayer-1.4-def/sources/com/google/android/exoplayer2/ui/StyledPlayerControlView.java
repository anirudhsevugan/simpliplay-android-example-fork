package com.google.android.exoplayer2.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.widget.ActivityChooserView;
import com.dreamers.exoplayercore.repack.C0010ai;
import com.dreamers.exoplayercore.repack.F;
import com.dreamers.exoplayercore.repack.M;
import com.dreamers.exoplayerui.ExoplayerUi;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ControlDispatcher;
import com.google.android.exoplayer2.DefaultControlDispatcher;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.PlaybackPreparer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Player$EventListener$$CC;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.TimeBar;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.RepeatModeUtil;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class StyledPlayerControlView extends FrameLayout {
    public static final int DEFAULT_REPEAT_TOGGLE_MODES = 0;
    public static final int DEFAULT_SHOW_TIMEOUT_MS = 5000;
    public static final int DEFAULT_TIME_BAR_MIN_UPDATE_INTERVAL_MS = 200;
    public static final String LIST_ICON_TAG = "icon";
    public static final String LIST_MAIN_TEXT_TAG = "main_text";
    public static final String LIST_SUB_TEXT_TAG = "sub_text";
    private static final int MAX_UPDATE_INTERVAL_MS = 1000;
    public static final int MAX_WINDOWS_FOR_MULTI_WINDOW_TIME_BAR = 100;
    private static final int SETTINGS_AUDIO_TRACK_SELECTION_POSITION = 1;
    private static final int SETTINGS_PLAYBACK_SPEED_POSITION = 0;
    private long[] adGroupTimesMs;
    /* access modifiers changed from: private */
    public View audioTrackButton;
    /* access modifiers changed from: private */
    public final TrackSelectionAdapter audioTrackSelectionAdapter;
    private final ComponentListener componentListener;
    final Context context;
    /* access modifiers changed from: private */
    public ControlDispatcher controlDispatcher;
    /* access modifiers changed from: private */
    public final StyledPlayerControlViewLayoutManager controlViewLayoutManager;
    private long currentWindowOffset;
    /* access modifiers changed from: private */
    public final boolean debugMode;
    private final TextView durationView;
    private long[] extraAdGroupTimesMs;
    private boolean[] extraPlayedAdGroups;
    /* access modifiers changed from: private */
    public final View fastForwardButton;
    private final TextView fastForwardButtonTextView;
    private long fastForwardMs;
    /* access modifiers changed from: private */
    public final StringBuilder formatBuilder;
    /* access modifiers changed from: private */
    public final Formatter formatter;
    private final ImageView fullScreenButton;
    private final String fullScreenEnterContentDescription;
    private final Drawable fullScreenEnterDrawable;
    private final String fullScreenExitContentDescription;
    private final Drawable fullScreenExitDrawable;
    private boolean isAttachedToWindow;
    /* access modifiers changed from: private */
    public boolean isFullScreen;
    private ImageView minimalFullScreenButton;
    private boolean multiWindowTimeBar;
    /* access modifiers changed from: private */
    public boolean needToHideBars;
    /* access modifiers changed from: private */
    public final View nextButton;
    private OnFullScreenModeChangedListener onFullScreenModeChangedListener;
    /* access modifiers changed from: private */
    public OnSettingsWindowDismissListener onSettingsWindowDismissListener;
    private final Timeline.Period period;
    /* access modifiers changed from: private */
    public final View playPauseButton;
    private PlaybackPreparer playbackPreparer;
    /* access modifiers changed from: private */
    public final PlaybackSpeedAdapter playbackSpeedAdapter;
    /* access modifiers changed from: private */
    public View playbackSpeedButton;
    private boolean[] playedAdGroups;
    /* access modifiers changed from: private */
    public Player player;
    /* access modifiers changed from: private */
    public final TextView positionView;
    /* access modifiers changed from: private */
    public final View previousButton;
    private ProgressUpdateListener progressUpdateListener;
    private final String repeatAllButtonContentDescription;
    private final Drawable repeatAllButtonDrawable;
    private final String repeatOffButtonContentDescription;
    private final Drawable repeatOffButtonDrawable;
    private final String repeatOneButtonContentDescription;
    private final Drawable repeatOneButtonDrawable;
    /* access modifiers changed from: private */
    public final ImageView repeatToggleButton;
    /* access modifiers changed from: private */
    public int repeatToggleModes;
    /* access modifiers changed from: private */
    public final View rewindButton;
    private final TextView rewindButtonTextView;
    private long rewindMs;
    /* access modifiers changed from: private */
    public boolean scrubbing;
    /* access modifiers changed from: private */
    public final SettingsAdapter settingsAdapter;
    /* access modifiers changed from: private */
    public final View settingsButton;
    private final F settingsView;
    /* access modifiers changed from: private */
    public final PopupWindow settingsWindow;
    private final int settingsWindowMargin;
    private boolean showMultiWindowTimeBar;
    private int showTimeoutMs;
    /* access modifiers changed from: private */
    public final ImageView shuffleButton;
    private final Drawable shuffleOffButtonDrawable;
    private final String shuffleOffContentDescription;
    private final Drawable shuffleOnButtonDrawable;
    private final String shuffleOnContentDescription;
    final String[] speedOptions;
    final int[] speedOptionsInto100;
    /* access modifiers changed from: private */
    public final ImageView subtitleButton;
    /* access modifiers changed from: private */
    public final Drawable subtitleOffButtonDrawable;
    /* access modifiers changed from: private */
    public final String subtitleOffContentDescription;
    /* access modifiers changed from: private */
    public final Drawable subtitleOnButtonDrawable;
    /* access modifiers changed from: private */
    public final String subtitleOnContentDescription;
    /* access modifiers changed from: private */
    public final TrackSelectionAdapter textTrackSelectionAdapter;
    private final TimeBar timeBar;
    private int timeBarMinUpdateIntervalMs;
    private final TrackNameProvider trackNameProvider;
    /* access modifiers changed from: private */
    public DefaultTrackSelector trackSelector;
    private final Runnable updateProgressAction;
    private final View videoSettingsButton;
    private final CopyOnWriteArrayList visibilityListeners;
    private final View vrButton;
    private final Timeline.Window window;

    public interface OnFullScreenModeChangedListener {
        void onFullScreenModeChanged(boolean z);
    }

    public interface OnSettingsWindowDismissListener {
        void onDismiss(boolean z);
    }

    public interface VisibilityListener {
        void onVisibilityChange(int i);
    }

    final class AudioTrackSelectionAdapter extends TrackSelectionAdapter {
        private AudioTrackSelectionAdapter() {
            super();
        }

        public final void init(List list, List list2, MappingTrackSelector.MappedTrackInfo mappedTrackInfo) {
            boolean z;
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i2 >= list.size()) {
                    z = false;
                    break;
                }
                int intValue = ((Integer) list.get(i2)).intValue();
                TrackGroupArray trackGroups = mappedTrackInfo.getTrackGroups(intValue);
                if (StyledPlayerControlView.this.trackSelector != null && StyledPlayerControlView.this.trackSelector.getParameters().a(intValue, trackGroups)) {
                    z = true;
                    break;
                }
                i2++;
            }
            if (!list2.isEmpty() && z) {
                while (true) {
                    if (i >= list2.size()) {
                        break;
                    }
                    TrackInfo trackInfo = (TrackInfo) list2.get(i);
                    if (trackInfo.selected) {
                        StyledPlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, trackInfo.trackName.replaceAll("(ItemList : )|(Item : )", ""));
                        break;
                    }
                    i++;
                }
            } else {
                StyledPlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, "Auto");
            }
            this.rendererIndices = list;
            this.tracks = list2;
            this.mappedTrackInfo = mappedTrackInfo;
        }

        /* access modifiers changed from: package-private */
        public final /* synthetic */ void lambda$onBindViewHolderAtZeroPosition$0$StyledPlayerControlView$AudioTrackSelectionAdapter(View view) {
            if (StyledPlayerControlView.this.trackSelector != null) {
                DefaultTrackSelector.ParametersBuilder a = StyledPlayerControlView.this.trackSelector.getParameters().a();
                for (int i = 0; i < this.rendererIndices.size(); i++) {
                    a = a.a(((Integer) this.rendererIndices.get(i)).intValue());
                }
                ((DefaultTrackSelector) Assertions.b((Object) StyledPlayerControlView.this.trackSelector)).setParameters(a);
            }
            StyledPlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, "Auto");
            StyledPlayerControlView.this.settingsWindow.dismiss();
        }

        public final void onBindViewHolderAtZeroPosition(SubSettingViewHolder subSettingViewHolder) {
            boolean z;
            subSettingViewHolder.textView.setText("Auto");
            DefaultTrackSelector.Parameters parameters = ((DefaultTrackSelector) Assertions.b((Object) StyledPlayerControlView.this.trackSelector)).getParameters();
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i2 >= this.rendererIndices.size()) {
                    z = false;
                    break;
                }
                int intValue = ((Integer) this.rendererIndices.get(i2)).intValue();
                if (parameters.a(intValue, ((MappingTrackSelector.MappedTrackInfo) Assertions.b((Object) this.mappedTrackInfo)).getTrackGroups(intValue))) {
                    z = true;
                    break;
                }
                i2++;
            }
            View view = subSettingViewHolder.checkView;
            if (z) {
                i = 4;
            }
            view.setVisibility(i);
            subSettingViewHolder.itemView.setOnClickListener(new StyledPlayerControlView$AudioTrackSelectionAdapter$$Lambda$0(this));
        }

        public final void onTrackSelection(String str) {
            StyledPlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, str);
        }
    }

    final class ComponentListener implements View.OnClickListener, PopupWindow.OnDismissListener, Player.EventListener, TimeBar.OnScrubListener {
        private ComponentListener() {
        }

        public final void onAvailableCommandsChanged(Player.Commands commands) {
            Player$EventListener$$CC.i();
        }

        public final void onClick(View view) {
            Player access$800 = StyledPlayerControlView.this.player;
            if (access$800 != null) {
                StyledPlayerControlView.this.controlViewLayoutManager.resetHideCallbacks();
                if (StyledPlayerControlView.this.nextButton == view) {
                    StyledPlayerControlView.this.controlDispatcher.c(access$800);
                } else if (StyledPlayerControlView.this.previousButton == view) {
                    StyledPlayerControlView.this.controlDispatcher.b(access$800);
                } else if (StyledPlayerControlView.this.fastForwardButton == view) {
                    if (access$800.h() != 4) {
                        StyledPlayerControlView.this.controlDispatcher.e(access$800);
                    }
                } else if (StyledPlayerControlView.this.rewindButton == view) {
                    StyledPlayerControlView.this.controlDispatcher.d(access$800);
                } else if (StyledPlayerControlView.this.playPauseButton == view) {
                    StyledPlayerControlView.this.dispatchPlayPause(access$800);
                } else if (StyledPlayerControlView.this.repeatToggleButton == view) {
                    StyledPlayerControlView.this.controlDispatcher.a(access$800, RepeatModeUtil.a(access$800.m(), StyledPlayerControlView.this.repeatToggleModes));
                } else if (StyledPlayerControlView.this.shuffleButton == view) {
                    StyledPlayerControlView.this.controlDispatcher.b(access$800, !access$800.n());
                } else if (StyledPlayerControlView.this.settingsButton == view) {
                    StyledPlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
                    StyledPlayerControlView styledPlayerControlView = StyledPlayerControlView.this;
                    styledPlayerControlView.displaySettingsWindow(styledPlayerControlView.settingsAdapter);
                } else if (StyledPlayerControlView.this.playbackSpeedButton == view) {
                    StyledPlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
                    StyledPlayerControlView styledPlayerControlView2 = StyledPlayerControlView.this;
                    styledPlayerControlView2.displaySettingsWindow(styledPlayerControlView2.playbackSpeedAdapter);
                } else if (StyledPlayerControlView.this.audioTrackButton == view) {
                    StyledPlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
                    StyledPlayerControlView styledPlayerControlView3 = StyledPlayerControlView.this;
                    styledPlayerControlView3.displaySettingsWindow(styledPlayerControlView3.audioTrackSelectionAdapter);
                } else if (StyledPlayerControlView.this.subtitleButton == view) {
                    StyledPlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
                    StyledPlayerControlView styledPlayerControlView4 = StyledPlayerControlView.this;
                    styledPlayerControlView4.displaySettingsWindow(styledPlayerControlView4.textTrackSelectionAdapter);
                }
            }
        }

        public final void onDismiss() {
            if (StyledPlayerControlView.this.needToHideBars) {
                StyledPlayerControlView.this.controlViewLayoutManager.resetHideCallbacks();
            }
            if (StyledPlayerControlView.this.onSettingsWindowDismissListener != null) {
                StyledPlayerControlView.this.onSettingsWindowDismissListener.onDismiss(StyledPlayerControlView.this.isFullScreen);
            }
        }

        public final void onEvents(Player player, Player.Events events) {
            if (events.a(5, 6)) {
                StyledPlayerControlView.this.updatePlayPauseButton();
            }
            if (events.a(5, 6, 8)) {
                StyledPlayerControlView.this.bridge$lambda$0$StyledPlayerControlView();
            }
            if (events.a(9)) {
                StyledPlayerControlView.this.updateRepeatModeButton();
            }
            if (events.a(10)) {
                StyledPlayerControlView.this.updateShuffleButton();
            }
            if (events.a(9, 10, 12, 0)) {
                StyledPlayerControlView.this.updateNavigation();
            }
            if (events.a(12, 0)) {
                StyledPlayerControlView.this.updateTimeline();
            }
            if (events.a(13)) {
                StyledPlayerControlView.this.updatePlaybackSpeedList();
            }
            if (events.a(2)) {
                StyledPlayerControlView.this.updateTrackLists();
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

        public final void onRepeatModeChanged(int i) {
            Player$EventListener$$CC.o();
        }

        public final void onScrubMove(TimeBar timeBar, long j) {
            if (StyledPlayerControlView.this.positionView != null) {
                StyledPlayerControlView.this.positionView.setText(Util.a(StyledPlayerControlView.this.formatBuilder, StyledPlayerControlView.this.formatter, j));
            }
        }

        public final void onScrubStart(TimeBar timeBar, long j) {
            boolean unused = StyledPlayerControlView.this.scrubbing = true;
            if (StyledPlayerControlView.this.positionView != null) {
                StyledPlayerControlView.this.positionView.setText(Util.a(StyledPlayerControlView.this.formatBuilder, StyledPlayerControlView.this.formatter, j));
            }
            StyledPlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
        }

        public final void onScrubStop(TimeBar timeBar, long j, boolean z) {
            boolean unused = StyledPlayerControlView.this.scrubbing = false;
            if (!z && StyledPlayerControlView.this.player != null) {
                StyledPlayerControlView styledPlayerControlView = StyledPlayerControlView.this;
                styledPlayerControlView.seekToTimeBarPosition(styledPlayerControlView.player, j);
            }
            StyledPlayerControlView.this.controlViewLayoutManager.resetHideCallbacks();
        }

        public final void onSeekProcessed() {
            Player$EventListener$$CC.u();
        }

        public final void onShuffleModeEnabledChanged(boolean z) {
            Player$EventListener$$CC.p();
        }

        public final void onStaticMetadataChanged(List list) {
            Player$EventListener$$CC.e();
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
    }

    final class PlaybackSpeedAdapter extends M {
        private final String[] playbackSpeedTexts;
        private final int[] playbackSpeedsMultBy100;
        private int selectedIndex;

        public PlaybackSpeedAdapter(String[] strArr, int[] iArr) {
            this.playbackSpeedTexts = strArr;
            this.playbackSpeedsMultBy100 = iArr;
        }

        public final int getItemCount() {
            return this.playbackSpeedTexts.length;
        }

        public final String getSelectedText() {
            return this.playbackSpeedTexts[this.selectedIndex];
        }

        /* access modifiers changed from: package-private */
        public final /* synthetic */ void lambda$onBindViewHolder$0$StyledPlayerControlView$PlaybackSpeedAdapter(int i, View view) {
            if (i != this.selectedIndex) {
                StyledPlayerControlView.this.setPlaybackSpeed(((float) this.playbackSpeedsMultBy100[i]) / 100.0f);
            }
            StyledPlayerControlView.this.settingsWindow.dismiss();
        }

        public final void onBindViewHolder(SubSettingViewHolder subSettingViewHolder, int i) {
            if (i < this.playbackSpeedTexts.length) {
                subSettingViewHolder.textView.setText(this.playbackSpeedTexts[i]);
            }
            subSettingViewHolder.checkView.setVisibility(i == this.selectedIndex ? 0 : 4);
            subSettingViewHolder.itemView.setOnClickListener(new StyledPlayerControlView$PlaybackSpeedAdapter$$Lambda$0(this, i));
        }

        public final SubSettingViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            int parseColor = Color.parseColor("#FFFFFF");
            LinearLayout linearLayout = new LinearLayout(StyledPlayerControlView.this.context);
            linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
            linearLayout.setMinimumHeight(UiHelper.convertToDp(52.0f));
            linearLayout.setMinimumWidth(UiHelper.convertToDp(150.0f));
            linearLayout.setOrientation(0);
            linearLayout.setFocusable(true);
            UiHelper.setSelectableBackground(linearLayout, false);
            linearLayout.setGravity(8388627);
            linearLayout.setPaddingRelative(UiHelper.convertToDp(4.0f), 0, UiHelper.convertToDp(4.0f), 0);
            ImageView imageView = new ImageView(StyledPlayerControlView.this.context);
            ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(UiHelper.convertToDp(24.0f), UiHelper.convertToDp(24.0f));
            marginLayoutParams.setMargins(UiHelper.convertToDp(8.0f), 0, UiHelper.convertToDp(8.0f), 0);
            imageView.setLayoutParams(marginLayoutParams);
            imageView.setVisibility(4);
            imageView.setTag(StyledPlayerControlView.LIST_ICON_TAG);
            imageView.setImageDrawable(UiHelper.getDrawable(StyledPlayerControlView.this.context, UiHelper.IC_CHECK, StyledPlayerControlView.this.debugMode));
            TextView textView = new TextView(StyledPlayerControlView.this.context);
            ViewGroup.MarginLayoutParams marginLayoutParams2 = new ViewGroup.MarginLayoutParams(-2, -2);
            int convertToDp = UiHelper.convertToDp(4.0f);
            marginLayoutParams2.setMargins(0, 0, convertToDp, 0);
            marginLayoutParams2.setMarginEnd(convertToDp);
            textView.setLayoutParams(marginLayoutParams2);
            textView.setTextColor(parseColor);
            textView.setTextSize(2, 14.0f);
            textView.setTag(StyledPlayerControlView.LIST_MAIN_TEXT_TAG);
            textView.setMinHeight(UiHelper.convertToDp(52.0f));
            textView.setGravity(8388627);
            linearLayout.addView(imageView);
            linearLayout.addView(textView);
            return new SubSettingViewHolder(linearLayout);
        }

        public final void updateSelectedIndex(float f) {
            int round = Math.round(f * 100.0f);
            int i = 0;
            int i2 = 0;
            int i3 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            while (true) {
                int[] iArr = this.playbackSpeedsMultBy100;
                if (i < iArr.length) {
                    int abs = Math.abs(round - iArr[i]);
                    if (abs < i3) {
                        i2 = i;
                        i3 = abs;
                    }
                    i++;
                } else {
                    this.selectedIndex = i2;
                    return;
                }
            }
        }
    }

    public interface ProgressUpdateListener {
        void onProgressUpdate(long j, long j2);
    }

    final class SettingViewHolder extends C0010ai {
        /* access modifiers changed from: private */
        public final ImageView iconView;
        /* access modifiers changed from: private */
        public final TextView mainTextView;
        /* access modifiers changed from: private */
        public final TextView subTextView;

        public SettingViewHolder(View view) {
            super(view);
            if (Util.a < 26) {
                view.setFocusable(true);
            }
            this.mainTextView = (TextView) view.findViewWithTag(StyledPlayerControlView.LIST_MAIN_TEXT_TAG);
            this.subTextView = (TextView) view.findViewWithTag(StyledPlayerControlView.LIST_SUB_TEXT_TAG);
            this.iconView = (ImageView) view.findViewWithTag(StyledPlayerControlView.LIST_ICON_TAG);
            view.setOnClickListener(new StyledPlayerControlView$SettingViewHolder$$Lambda$0(this));
        }

        /* access modifiers changed from: package-private */
        public final /* synthetic */ void lambda$new$0$StyledPlayerControlView$SettingViewHolder(View view) {
            StyledPlayerControlView.this.onSettingViewClicked(getAdapterPosition());
        }
    }

    class SettingsAdapter extends M {
        private final Drawable[] iconIds;
        private final String[] mainTexts;
        private final String[] subTexts;

        public SettingsAdapter(String[] strArr, Drawable[] drawableArr) {
            this.mainTexts = strArr;
            this.subTexts = new String[strArr.length];
            this.iconIds = drawableArr;
        }

        public int getItemCount() {
            return this.mainTexts.length;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public void onBindViewHolder(SettingViewHolder settingViewHolder, int i) {
            settingViewHolder.mainTextView.setText(this.mainTexts[i]);
            if (this.subTexts[i] == null) {
                settingViewHolder.subTextView.setVisibility(8);
            } else {
                settingViewHolder.subTextView.setText(this.subTexts[i]);
            }
            Drawable drawable = this.iconIds[i];
            ImageView access$4200 = settingViewHolder.iconView;
            if (drawable == null) {
                access$4200.setVisibility(8);
            } else {
                access$4200.setImageDrawable(this.iconIds[i]);
            }
        }

        public SettingViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LinearLayout linearLayout = new LinearLayout(StyledPlayerControlView.this.context);
            linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
            linearLayout.setMinimumHeight(UiHelper.convertToDp(52.0f));
            linearLayout.setMinimumWidth(UiHelper.convertToDp(150.0f));
            linearLayout.setOrientation(0);
            ImageView imageView = new ImageView(StyledPlayerControlView.this.context);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(UiHelper.convertToDp(24.0f), UiHelper.convertToDp(24.0f));
            layoutParams.setMargins(UiHelper.convertToDp(8.0f), UiHelper.convertToDp(12.0f), UiHelper.convertToDp(8.0f), 0);
            imageView.setLayoutParams(layoutParams);
            imageView.setTag(StyledPlayerControlView.LIST_ICON_TAG);
            LinearLayout linearLayout2 = new LinearLayout(StyledPlayerControlView.this.context);
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -2);
            layoutParams2.setMargins(UiHelper.convertToDp(2.0f), 0, UiHelper.convertToDp(2.0f), 0);
            linearLayout2.setLayoutParams(layoutParams2);
            linearLayout2.setPaddingRelative(0, 0, UiHelper.convertToDp(4.0f), 0);
            linearLayout2.setPadding(0, 0, UiHelper.convertToDp(4.0f), 0);
            linearLayout2.setOrientation(1);
            linearLayout2.setGravity(8388627);
            TextView textView = new TextView(StyledPlayerControlView.this.context);
            textView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            textView.setTextColor(-1);
            textView.setTextSize(2, 14.0f);
            textView.setTag(StyledPlayerControlView.LIST_MAIN_TEXT_TAG);
            TextView textView2 = new TextView(StyledPlayerControlView.this.context);
            textView2.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            textView2.setTextColor(-1);
            textView2.setTextSize(2, 12.0f);
            textView2.setTag(StyledPlayerControlView.LIST_SUB_TEXT_TAG);
            linearLayout2.addView(textView);
            linearLayout2.addView(textView2);
            linearLayout.addView(imageView);
            linearLayout.addView(linearLayout2);
            return new SettingViewHolder(linearLayout);
        }

        public void setSubTextAtPosition(int i, String str) {
            this.subTexts[i] = str;
        }
    }

    class SubSettingViewHolder extends C0010ai {
        public final View checkView;
        public final TextView textView;

        public SubSettingViewHolder(View view) {
            super(view);
            if (Util.a < 26) {
                view.setFocusable(true);
            }
            this.textView = (TextView) view.findViewWithTag(StyledPlayerControlView.LIST_MAIN_TEXT_TAG);
            this.checkView = view.findViewWithTag(StyledPlayerControlView.LIST_ICON_TAG);
        }
    }

    final class TextTrackSelectionAdapter extends TrackSelectionAdapter {
        private TextTrackSelectionAdapter() {
            super();
        }

        public final void init(List list, List list2, MappingTrackSelector.MappedTrackInfo mappedTrackInfo) {
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= list2.size()) {
                    break;
                } else if (((TrackInfo) list2.get(i)).selected) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            if (StyledPlayerControlView.this.subtitleButton != null) {
                ImageView access$3800 = StyledPlayerControlView.this.subtitleButton;
                StyledPlayerControlView styledPlayerControlView = StyledPlayerControlView.this;
                access$3800.setImageDrawable(z ? styledPlayerControlView.subtitleOnButtonDrawable : styledPlayerControlView.subtitleOffButtonDrawable);
                StyledPlayerControlView.this.subtitleButton.setContentDescription(z ? StyledPlayerControlView.this.subtitleOnContentDescription : StyledPlayerControlView.this.subtitleOffContentDescription);
            }
            this.rendererIndices = list;
            this.tracks = list2;
            this.mappedTrackInfo = mappedTrackInfo;
        }

        /* access modifiers changed from: package-private */
        public final /* synthetic */ void lambda$onBindViewHolderAtZeroPosition$0$StyledPlayerControlView$TextTrackSelectionAdapter(View view) {
            if (StyledPlayerControlView.this.trackSelector != null) {
                DefaultTrackSelector.ParametersBuilder a = StyledPlayerControlView.this.trackSelector.getParameters().a();
                for (int i = 0; i < this.rendererIndices.size(); i++) {
                    int intValue = ((Integer) this.rendererIndices.get(i)).intValue();
                    a = a.a(intValue).a(intValue, true);
                }
                ((DefaultTrackSelector) Assertions.b((Object) StyledPlayerControlView.this.trackSelector)).setParameters(a);
                StyledPlayerControlView.this.settingsWindow.dismiss();
            }
        }

        public final void onBindViewHolder(SubSettingViewHolder subSettingViewHolder, int i) {
            super.onBindViewHolder(subSettingViewHolder, i);
            if (i > 0) {
                subSettingViewHolder.checkView.setVisibility(((TrackInfo) this.tracks.get(i + -1)).selected ? 0 : 4);
            }
        }

        public final void onBindViewHolderAtZeroPosition(SubSettingViewHolder subSettingViewHolder) {
            boolean z;
            subSettingViewHolder.textView.setText(ExoplayerUi.SURFACE_TYPE_NONE);
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i2 >= this.tracks.size()) {
                    z = true;
                    break;
                } else if (((TrackInfo) this.tracks.get(i2)).selected) {
                    z = false;
                    break;
                } else {
                    i2++;
                }
            }
            View view = subSettingViewHolder.checkView;
            if (!z) {
                i = 4;
            }
            view.setVisibility(i);
            subSettingViewHolder.itemView.setOnClickListener(new StyledPlayerControlView$TextTrackSelectionAdapter$$Lambda$0(this));
        }

        public final void onTrackSelection(String str) {
        }
    }

    final class TrackInfo {
        public final int groupIndex;
        public final int rendererIndex;
        public final boolean selected;
        public final int trackIndex;
        public final String trackName;

        public TrackInfo(int i, int i2, int i3, String str, boolean z) {
            this.rendererIndex = i;
            this.groupIndex = i2;
            this.trackIndex = i3;
            this.trackName = str;
            this.selected = z;
        }
    }

    abstract class TrackSelectionAdapter extends M {
        protected MappingTrackSelector.MappedTrackInfo mappedTrackInfo = null;
        protected List rendererIndices = new ArrayList();
        protected List tracks = new ArrayList();

        public TrackSelectionAdapter() {
        }

        public void clear() {
            this.tracks = Collections.emptyList();
            this.mappedTrackInfo = null;
        }

        public int getItemCount() {
            if (this.tracks.isEmpty()) {
                return 0;
            }
            return this.tracks.size() + 1;
        }

        public abstract void init(List list, List list2, MappingTrackSelector.MappedTrackInfo mappedTrackInfo2);

        /* access modifiers changed from: package-private */
        public final /* synthetic */ void lambda$onBindViewHolder$0$StyledPlayerControlView$TrackSelectionAdapter(TrackInfo trackInfo, String str, View view) {
            if (this.mappedTrackInfo != null && StyledPlayerControlView.this.trackSelector != null) {
                DefaultTrackSelector.ParametersBuilder a = StyledPlayerControlView.this.trackSelector.getParameters().a();
                for (int i = 0; i < this.rendererIndices.size(); i++) {
                    int intValue = ((Integer) this.rendererIndices.get(i)).intValue();
                    a = intValue == trackInfo.rendererIndex ? a.a(intValue, ((MappingTrackSelector.MappedTrackInfo) Assertions.b((Object) this.mappedTrackInfo)).getTrackGroups(intValue), new DefaultTrackSelector.SelectionOverride(trackInfo.groupIndex, trackInfo.trackIndex)).a(intValue, false) : a.a(intValue).a(intValue, true);
                }
                ((DefaultTrackSelector) Assertions.b((Object) StyledPlayerControlView.this.trackSelector)).setParameters(a);
                onTrackSelection(str);
                StyledPlayerControlView.this.settingsWindow.dismiss();
            }
        }

        public void onBindViewHolder(SubSettingViewHolder subSettingViewHolder, int i) {
            if (StyledPlayerControlView.this.trackSelector != null && this.mappedTrackInfo != null) {
                if (i == 0) {
                    onBindViewHolderAtZeroPosition(subSettingViewHolder);
                    return;
                }
                boolean z = true;
                TrackInfo trackInfo = (TrackInfo) this.tracks.get(i - 1);
                int i2 = 0;
                if (!((DefaultTrackSelector) Assertions.b((Object) StyledPlayerControlView.this.trackSelector)).getParameters().a(trackInfo.rendererIndex, this.mappedTrackInfo.getTrackGroups(trackInfo.rendererIndex)) || !trackInfo.selected) {
                    z = false;
                }
                String replaceAll = trackInfo.trackName.replaceAll("(ItemList : )|(Item : )", "");
                subSettingViewHolder.textView.setText(replaceAll);
                View view = subSettingViewHolder.checkView;
                if (!z) {
                    i2 = 4;
                }
                view.setVisibility(i2);
                subSettingViewHolder.itemView.setOnClickListener(new StyledPlayerControlView$TrackSelectionAdapter$$Lambda$0(this, trackInfo, replaceAll));
            }
        }

        public abstract void onBindViewHolderAtZeroPosition(SubSettingViewHolder subSettingViewHolder);

        public SubSettingViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LinearLayout linearLayout = new LinearLayout(StyledPlayerControlView.this.context);
            linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
            linearLayout.setMinimumHeight(UiHelper.convertToDp(52.0f));
            linearLayout.setMinimumWidth(UiHelper.convertToDp(150.0f));
            linearLayout.setOrientation(0);
            linearLayout.setFocusable(true);
            UiHelper.setSelectableBackground(linearLayout, false);
            linearLayout.setGravity(8388627);
            linearLayout.setPaddingRelative(UiHelper.convertToDp(4.0f), 0, UiHelper.convertToDp(4.0f), 0);
            ImageView imageView = new ImageView(StyledPlayerControlView.this.context);
            ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(UiHelper.convertToDp(24.0f), UiHelper.convertToDp(24.0f));
            marginLayoutParams.setMargins(UiHelper.convertToDp(8.0f), 0, UiHelper.convertToDp(8.0f), 0);
            imageView.setLayoutParams(marginLayoutParams);
            imageView.setVisibility(4);
            imageView.setTag(StyledPlayerControlView.LIST_ICON_TAG);
            imageView.setImageDrawable(UiHelper.getDrawable(StyledPlayerControlView.this.context, UiHelper.IC_CHECK, StyledPlayerControlView.this.debugMode));
            TextView textView = new TextView(StyledPlayerControlView.this.context);
            ViewGroup.MarginLayoutParams marginLayoutParams2 = new ViewGroup.MarginLayoutParams(-2, -2);
            int convertToDp = UiHelper.convertToDp(4.0f);
            marginLayoutParams2.setMargins(0, 0, convertToDp, 0);
            marginLayoutParams2.setMarginEnd(convertToDp);
            textView.setLayoutParams(marginLayoutParams2);
            textView.setTextColor(-1);
            textView.setTextSize(2, 14.0f);
            textView.setTag(StyledPlayerControlView.LIST_MAIN_TEXT_TAG);
            textView.setMinHeight(UiHelper.convertToDp(52.0f));
            textView.setGravity(8388627);
            linearLayout.addView(imageView);
            linearLayout.addView(textView);
            return new SubSettingViewHolder(linearLayout);
        }

        public abstract void onTrackSelection(String str);
    }

    static {
        ExoPlayerLibraryInfo.a("goog.exo.ui");
    }

    public StyledPlayerControlView(Context context2) {
        this(context2, TimeBarAttributes.createDefault());
    }

    public StyledPlayerControlView(Context context2, TimeBarAttributes timeBarAttributes) {
        this(context2, timeBarAttributes, PlayerAttributes.createDefault());
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public StyledPlayerControlView(android.content.Context r42, com.google.android.exoplayer2.ui.TimeBarAttributes r43, com.google.android.exoplayer2.ui.PlayerAttributes r44) {
        /*
            r41 = this;
            r0 = r41
            r1 = r42
            r2 = 0
            r3 = 0
            r0.<init>(r1, r2, r3)
            r4 = 7
            java.lang.String[] r5 = new java.lang.String[r4]
            java.lang.String r6 = "0.25x"
            r5[r3] = r6
            java.lang.String r6 = "0.5x"
            r7 = 1
            r5[r7] = r6
            java.lang.String r6 = "0.75x"
            r8 = 2
            r5[r8] = r6
            r6 = 3
            java.lang.String r9 = "Normal"
            r5[r6] = r9
            r6 = 4
            java.lang.String r9 = "1.25x"
            r5[r6] = r9
            r6 = 5
            java.lang.String r9 = "1.5x"
            r5[r6] = r9
            r6 = 6
            java.lang.String r9 = "2x"
            r5[r6] = r9
            r0.speedOptions = r5
            int[] r4 = new int[r4]
            r4 = {25, 50, 75, 100, 125, 150, 200} // fill-array
            r0.speedOptionsInto100 = r4
            r0.fastForwardButtonTextView = r2
            r0.rewindButtonTextView = r2
            r0.vrButton = r2
            r0.context = r1
            int r6 = r44.getRewindMs()
            long r9 = (long) r6
            r0.rewindMs = r9
            int r6 = r44.getFastForwardMs()
            long r9 = (long) r6
            r0.fastForwardMs = r9
            int r6 = r44.getShowTimeoutMs()
            r0.showTimeoutMs = r6
            int r6 = r44.getRepeatToggleModes()
            r0.repeatToggleModes = r6
            int r6 = r44.getTimeBarMinUpdateIntervalMs()
            r0.timeBarMinUpdateIntervalMs = r6
            boolean r6 = r44.getShowRewindButton()
            boolean r9 = r44.getShowFastForwardButton()
            boolean r10 = r44.getShowPreviousButton()
            boolean r11 = r44.getShowNextButton()
            boolean r12 = r44.getShowShuffleButton()
            boolean r13 = r44.getShowSubtitleButton()
            boolean r14 = r44.getAnimationEnabled()
            boolean r15 = r44.isDebugMode()
            r0.debugMode = r15
            boolean r2 = r44.getShowFullscreenButton()
            boolean r7 = r44.getShowVideoSettingsButton()
            android.widget.FrameLayout$LayoutParams r8 = new android.widget.FrameLayout$LayoutParams
            r3 = -1
            r8.<init>(r3, r3)
            r0.setLayoutParams(r8)
            android.view.View r8 = new android.view.View
            r8.<init>(r1)
            android.widget.FrameLayout$LayoutParams r3 = new android.widget.FrameLayout$LayoutParams
            r19 = r7
            r7 = 0
            r3.<init>(r7, r7)
            r8.setLayoutParams(r3)
            int r3 = com.google.android.exoplayer2.ui.UiHelper.CONTROLS_COLOR
            r8.setBackgroundColor(r3)
            java.lang.String r3 = "ExoplayerBG"
            r8.setTag(r3)
            android.widget.LinearLayout r3 = new android.widget.LinearLayout
            r3.<init>(r1)
            r3.setClipToPadding(r7)
            r7 = 17170445(0x106000d, float:2.461195E-38)
            r3.setBackgroundResource(r7)
            android.widget.FrameLayout$LayoutParams r7 = new android.widget.FrameLayout$LayoutParams
            r20 = r2
            r2 = -2
            r21 = r13
            r13 = 17
            r22 = r12
            r12 = -1
            r7.<init>(r12, r2, r13)
            r3.setLayoutParams(r7)
            r3.setGravity(r13)
            int r7 = com.google.android.exoplayer2.util.Util.a
            r12 = 21
            if (r7 < r12) goto L_0x00de
            r7 = 1101529088(0x41a80000, float:21.0)
            int r7 = com.google.android.exoplayer2.ui.UiHelper.convertToDp(r7)
            r3.setPadding(r7, r7, r7, r7)
        L_0x00de:
            java.lang.String r7 = "CenterControls"
            r3.setTag(r7)
            java.lang.String r7 = "ic_play.png"
            android.graphics.drawable.Drawable r7 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r7, r15)
            android.widget.ImageButton r7 = com.google.android.exoplayer2.ui.UiHelper.createCenterImageButton(r1, r7)
            r0.playPauseButton = r7
            java.lang.String r12 = "PlayPauseButton"
            r7.setTag(r12)
            java.lang.String r12 = "ic_rewind.png"
            android.graphics.drawable.Drawable r12 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r12, r15)
            android.widget.ImageButton r12 = com.google.android.exoplayer2.ui.UiHelper.createCenterImageButton(r1, r12)
            r0.rewindButton = r12
            java.lang.String r13 = "RewindButton"
            r12.setTag(r13)
            java.lang.String r13 = "ic_fast_forward.png"
            android.graphics.drawable.Drawable r13 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r13, r15)
            android.widget.ImageButton r13 = com.google.android.exoplayer2.ui.UiHelper.createCenterImageButton(r1, r13)
            r0.fastForwardButton = r13
            java.lang.String r2 = "ForwardButton"
            r13.setTag(r2)
            java.lang.String r2 = "ic_next.png"
            android.graphics.drawable.Drawable r2 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r2, r15)
            android.widget.ImageButton r2 = com.google.android.exoplayer2.ui.UiHelper.createCenterImageButton(r1, r2)
            r0.nextButton = r2
            r23 = r11
            java.lang.String r11 = "NextButton"
            r2.setTag(r11)
            java.lang.String r11 = "ic_previous.png"
            android.graphics.drawable.Drawable r11 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r11, r15)
            android.widget.ImageButton r11 = com.google.android.exoplayer2.ui.UiHelper.createCenterImageButton(r1, r11)
            r0.previousButton = r11
            r24 = r10
            java.lang.String r10 = "PreviousButton"
            r11.setTag(r10)
            r3.addView(r11)
            r3.addView(r12)
            r3.addView(r7)
            r3.addView(r13)
            r3.addView(r2)
            android.graphics.drawable.GradientDrawable r10 = new android.graphics.drawable.GradientDrawable
            r25 = r6
            android.graphics.drawable.GradientDrawable$Orientation r6 = android.graphics.drawable.GradientDrawable.Orientation.BOTTOM_TOP
            r26 = r9
            r9 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r27 = r4
            r4 = 0
            int[] r9 = new int[]{r9, r4}
            r10.<init>(r6, r9)
            android.widget.FrameLayout r6 = new android.widget.FrameLayout
            r6.<init>(r1)
            android.widget.FrameLayout$LayoutParams r9 = new android.widget.FrameLayout$LayoutParams
            r18 = 1114636288(0x42700000, float:60.0)
            int r4 = com.google.android.exoplayer2.ui.UiHelper.convertToDp(r18)
            r29 = r5
            r5 = 80
            r30 = r14
            r14 = -1
            r9.<init>(r14, r4, r5)
            r4 = 1092616192(0x41200000, float:10.0)
            int r14 = com.google.android.exoplayer2.ui.UiHelper.convertToDp(r4)
            r5 = 0
            r9.setMargins(r5, r14, r5, r5)
            r6.setLayoutParams(r9)
            int r5 = com.google.android.exoplayer2.ui.UiHelper.BOTTOM_BAR_COLOR
            r6.setBackgroundColor(r5)
            java.lang.String r5 = "BottomBar"
            r6.setTag(r5)
            r6.setBackground(r10)
            r5 = 1082130432(0x40800000, float:4.0)
            int r5 = com.google.android.exoplayer2.ui.UiHelper.convertToDp(r5)
            android.widget.LinearLayout r9 = new android.widget.LinearLayout
            r9.<init>(r1)
            android.widget.FrameLayout$LayoutParams r10 = new android.widget.FrameLayout$LayoutParams
            r14 = 8388627(0x800013, float:1.175497E-38)
            r4 = -2
            r10.<init>(r4, r4, r14)
            r9.setLayoutParams(r10)
            r4 = 1092616192(0x41200000, float:10.0)
            int r4 = com.google.android.exoplayer2.ui.UiHelper.convertToDp(r4)
            r9.setPadding(r4, r4, r4, r4)
            java.lang.String r4 = "TimeView"
            r9.setTag(r4)
            android.widget.TextView r4 = new android.widget.TextView
            r4.<init>(r1)
            r0.positionView = r4
            int r10 = com.google.android.exoplayer2.ui.UiHelper.DIM_WHITE
            r4.setTextColor(r10)
            r10 = 0
            r4.setPadding(r5, r10, r5, r10)
            r14 = 1096810496(0x41600000, float:14.0)
            r10 = 2
            r4.setTextSize(r10, r14)
            java.lang.String r10 = "00:00"
            r4.setText(r10)
            android.graphics.Typeface r14 = r4.getTypeface()
            r31 = r3
            r3 = 1
            r4.setTypeface(r14, r3)
            r3 = 0
            r4.setIncludeFontPadding(r3)
            android.widget.LinearLayout$LayoutParams r14 = new android.widget.LinearLayout$LayoutParams
            r3 = -2
            r14.<init>(r3, r3)
            r4.setLayoutParams(r14)
            android.widget.TextView r3 = new android.widget.TextView
            r3.<init>(r1)
            int r14 = com.google.android.exoplayer2.ui.UiHelper.DIM_WHITE
            r3.setTextColor(r14)
            r14 = 0
            r3.setPadding(r5, r14, r5, r14)
            r32 = r8
            r8 = 2
            r14 = 1096810496(0x41600000, float:14.0)
            r3.setTextSize(r8, r14)
            java.lang.String r8 = "/"
            r3.setText(r8)
            android.graphics.Typeface r8 = r3.getTypeface()
            r14 = 1
            r3.setTypeface(r8, r14)
            r8 = 0
            r3.setIncludeFontPadding(r8)
            android.widget.LinearLayout$LayoutParams r14 = new android.widget.LinearLayout$LayoutParams
            r8 = -2
            r14.<init>(r8, r8)
            r3.setLayoutParams(r14)
            android.widget.TextView r8 = new android.widget.TextView
            r8.<init>(r1)
            r0.durationView = r8
            int r14 = com.google.android.exoplayer2.ui.UiHelper.DIM_WHITE
            r8.setTextColor(r14)
            r14 = 0
            r8.setPadding(r5, r14, r5, r14)
            r5 = 1096810496(0x41600000, float:14.0)
            r14 = 2
            r8.setTextSize(r14, r5)
            r8.setText(r10)
            android.graphics.Typeface r5 = r8.getTypeface()
            r10 = 1
            r8.setTypeface(r5, r10)
            r5 = 0
            r8.setIncludeFontPadding(r5)
            android.widget.LinearLayout$LayoutParams r5 = new android.widget.LinearLayout$LayoutParams
            r10 = -2
            r5.<init>(r10, r10)
            r8.setLayoutParams(r5)
            r9.addView(r4)
            r9.addView(r3)
            r9.addView(r8)
            android.widget.LinearLayout r3 = new android.widget.LinearLayout
            r3.<init>(r1)
            android.widget.FrameLayout$LayoutParams r4 = new android.widget.FrameLayout$LayoutParams
            r5 = 8388629(0x800015, float:1.1754973E-38)
            r4.<init>(r10, r10, r5)
            r3.setLayoutParams(r4)
            java.lang.String r4 = "BasicControls"
            r3.setTag(r4)
            java.lang.String r4 = "ic_shuffle_off.png"
            android.graphics.drawable.Drawable r5 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r4, r15)
            android.widget.ImageButton r5 = com.google.android.exoplayer2.ui.UiHelper.createBottomImageButton(r1, r5)
            r0.shuffleButton = r5
            java.lang.String r8 = "ic_repeat_off.png"
            android.graphics.drawable.Drawable r10 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r8, r15)
            android.widget.ImageButton r10 = com.google.android.exoplayer2.ui.UiHelper.createBottomImageButton(r1, r10)
            r0.repeatToggleButton = r10
            java.lang.String r14 = "ic_subtitle_off.png"
            r28 = r4
            android.graphics.drawable.Drawable r4 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r14, r15)
            android.widget.ImageButton r4 = com.google.android.exoplayer2.ui.UiHelper.createBottomImageButton(r1, r4)
            r0.subtitleButton = r4
            r33 = r8
            java.lang.String r8 = "ic_settings.png"
            android.graphics.drawable.Drawable r8 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r8, r15)
            android.widget.ImageButton r8 = com.google.android.exoplayer2.ui.UiHelper.createBottomImageButton(r1, r8)
            r0.settingsButton = r8
            r34 = r14
            java.lang.String r14 = "ic_fullscreen_enter.png"
            r35 = r13
            android.graphics.drawable.Drawable r13 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r14, r15)
            android.widget.ImageButton r13 = com.google.android.exoplayer2.ui.UiHelper.createBottomImageButton(r1, r13)
            r0.fullScreenButton = r13
            r36 = r14
            java.lang.String r14 = "ic_video_settings.png"
            android.graphics.drawable.Drawable r14 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r14, r15)
            android.widget.ImageButton r14 = com.google.android.exoplayer2.ui.UiHelper.createBottomImageButton(r1, r14)
            r0.videoSettingsButton = r14
            r3.addView(r5)
            r3.addView(r10)
            r3.addView(r4)
            r3.addView(r14)
            r3.addView(r8)
            r3.addView(r13)
            r6.addView(r9)
            r6.addView(r3)
            r3 = 262144(0x40000, float:3.67342E-40)
            r0.setDescendantFocusability(r3)
            com.google.android.exoplayer2.ui.StyledPlayerControlView$ComponentListener r3 = new com.google.android.exoplayer2.ui.StyledPlayerControlView$ComponentListener
            r9 = 0
            r3.<init>()
            r0.componentListener = r3
            java.util.concurrent.CopyOnWriteArrayList r9 = new java.util.concurrent.CopyOnWriteArrayList
            r9.<init>()
            r0.visibilityListeners = r9
            com.google.android.exoplayer2.Timeline$Period r9 = new com.google.android.exoplayer2.Timeline$Period
            r9.<init>()
            r0.period = r9
            com.google.android.exoplayer2.Timeline$Window r9 = new com.google.android.exoplayer2.Timeline$Window
            r9.<init>()
            r0.window = r9
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r0.formatBuilder = r9
            r37 = r14
            java.util.Formatter r14 = new java.util.Formatter
            r38 = r15
            java.util.Locale r15 = java.util.Locale.getDefault()
            r14.<init>(r9, r15)
            r0.formatter = r14
            r9 = 0
            long[] r14 = new long[r9]
            r0.adGroupTimesMs = r14
            boolean[] r14 = new boolean[r9]
            r0.playedAdGroups = r14
            long[] r14 = new long[r9]
            r0.extraAdGroupTimesMs = r14
            boolean[] r14 = new boolean[r9]
            r0.extraPlayedAdGroups = r14
            com.google.android.exoplayer2.DefaultControlDispatcher r9 = new com.google.android.exoplayer2.DefaultControlDispatcher
            long r14 = r0.fastForwardMs
            r40 = r5
            r39 = r6
            long r5 = r0.rewindMs
            r9.<init>(r14, r5)
            r0.controlDispatcher = r9
            com.google.android.exoplayer2.ui.StyledPlayerControlView$$Lambda$0 r5 = new com.google.android.exoplayer2.ui.StyledPlayerControlView$$Lambda$0
            r5.<init>(r0)
            r0.updateProgressAction = r5
            com.google.android.exoplayer2.ui.StyledPlayerControlView$$Lambda$1 r5 = new com.google.android.exoplayer2.ui.StyledPlayerControlView$$Lambda$1
            r5.<init>(r0)
            initializeFullScreenButton(r13, r5)
            com.google.android.exoplayer2.ui.DefaultTimeBar r5 = new com.google.android.exoplayer2.ui.DefaultTimeBar
            r6 = r43
            r5.<init>(r1, r6)
            android.widget.FrameLayout$LayoutParams r6 = new android.widget.FrameLayout$LayoutParams
            r9 = 80
            r14 = -1
            r15 = -2
            r6.<init>(r14, r15, r9)
            r9 = 1112539136(0x42500000, float:52.0)
            int r9 = com.google.android.exoplayer2.ui.UiHelper.convertToDp(r9)
            r14 = 0
            r6.setMargins(r14, r14, r14, r9)
            r5.setLayoutParams(r6)
            java.lang.String r6 = "TimeBar"
            r5.setTag(r6)
            r0.timeBar = r5
            r5.addListener(r3)
            r7.setOnClickListener(r3)
            r11.setOnClickListener(r3)
            r2.setOnClickListener(r3)
            r12.setOnClickListener(r3)
            r6 = r35
            r6.setOnClickListener(r3)
            r4.setOnClickListener(r3)
            r10.setOnClickListener(r3)
            r7 = r40
            r7.setOnClickListener(r3)
            r8.setOnClickListener(r3)
            r8 = r32
            r0.addView(r8)
            r8 = r31
            r0.addView(r8)
            r8 = r39
            r0.addView(r8)
            r0.addView(r5)
            com.google.android.exoplayer2.ui.StyledPlayerControlViewLayoutManager r5 = new com.google.android.exoplayer2.ui.StyledPlayerControlViewLayoutManager
            r5.<init>(r0)
            r0.controlViewLayoutManager = r5
            r9 = r30
            r5.setAnimationEnabled(r9)
            r9 = 2
            java.lang.String[] r14 = new java.lang.String[r9]
            android.graphics.drawable.Drawable[] r9 = new android.graphics.drawable.Drawable[r9]
            java.lang.String r15 = "Speed"
            r17 = 0
            r14[r17] = r15
            java.lang.String r15 = "ic_speed.png"
            r30 = r13
            r13 = r38
            android.graphics.drawable.Drawable r15 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r15, r13)
            r9[r17] = r15
            java.lang.String r15 = "Audio"
            r16 = 1
            r14[r16] = r15
            java.lang.String r15 = "ic_audio_track.png"
            android.graphics.drawable.Drawable r15 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r15, r13)
            r9[r16] = r15
            com.google.android.exoplayer2.ui.StyledPlayerControlView$SettingsAdapter r15 = new com.google.android.exoplayer2.ui.StyledPlayerControlView$SettingsAdapter
            r15.<init>(r14, r9)
            r0.settingsAdapter = r15
            r9 = 1090519040(0x41000000, float:8.0)
            int r9 = com.google.android.exoplayer2.ui.UiHelper.convertToDp(r9)
            r0.settingsWindowMargin = r9
            com.dreamers.exoplayercore.repack.F r9 = new com.dreamers.exoplayercore.repack.F
            r9.<init>(r1)
            r0.settingsView = r9
            android.widget.FrameLayout$LayoutParams r14 = new android.widget.FrameLayout$LayoutParams
            r17 = r10
            r10 = -1
            r14.<init>(r10, r10)
            r9.setLayoutParams(r14)
            int r10 = com.google.android.exoplayer2.ui.UiHelper.TRANSPARENT_BLACK
            r9.setBackgroundColor(r10)
            r9.a((com.dreamers.exoplayercore.repack.M) r15)
            com.dreamers.exoplayercore.repack.v r10 = new com.dreamers.exoplayercore.repack.v
            r41.getContext()
            r10.<init>()
            r9.a((com.dreamers.exoplayercore.repack.U) r10)
            android.widget.PopupWindow r10 = new android.widget.PopupWindow
            r14 = 1
            r15 = -2
            r10.<init>(r9, r15, r15, r14)
            r0.settingsWindow = r10
            int r9 = com.google.android.exoplayer2.util.Util.a
            r15 = 23
            if (r9 >= r15) goto L_0x040a
            android.graphics.drawable.ColorDrawable r9 = new android.graphics.drawable.ColorDrawable
            r15 = 0
            r9.<init>(r15)
            r10.setBackgroundDrawable(r9)
            goto L_0x040b
        L_0x040a:
            r15 = 0
        L_0x040b:
            r10.setOnDismissListener(r3)
            r0.needToHideBars = r14
            com.google.android.exoplayer2.ui.DefaultTrackNameProvider r3 = new com.google.android.exoplayer2.ui.DefaultTrackNameProvider
            android.content.res.Resources r9 = r41.getResources()
            r3.<init>(r9)
            r0.trackNameProvider = r3
            com.google.android.exoplayer2.ui.StyledPlayerControlView$TextTrackSelectionAdapter r3 = new com.google.android.exoplayer2.ui.StyledPlayerControlView$TextTrackSelectionAdapter
            r9 = 0
            r3.<init>()
            r0.textTrackSelectionAdapter = r3
            com.google.android.exoplayer2.ui.StyledPlayerControlView$AudioTrackSelectionAdapter r3 = new com.google.android.exoplayer2.ui.StyledPlayerControlView$AudioTrackSelectionAdapter
            r3.<init>()
            r0.audioTrackSelectionAdapter = r3
            com.google.android.exoplayer2.ui.StyledPlayerControlView$PlaybackSpeedAdapter r3 = new com.google.android.exoplayer2.ui.StyledPlayerControlView$PlaybackSpeedAdapter
            r10 = r27
            r9 = r29
            r3.<init>(r9, r10)
            r0.playbackSpeedAdapter = r3
            java.lang.String r3 = "ic_subtitle_on.png"
            android.graphics.drawable.Drawable r3 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r3, r13)
            r0.subtitleOnButtonDrawable = r3
            r3 = r34
            android.graphics.drawable.Drawable r3 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r3, r13)
            r0.subtitleOffButtonDrawable = r3
            java.lang.String r3 = "ic_fullscreen_exit.png"
            android.graphics.drawable.Drawable r3 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r3, r13)
            r0.fullScreenExitDrawable = r3
            r3 = r36
            android.graphics.drawable.Drawable r3 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r3, r13)
            r0.fullScreenEnterDrawable = r3
            r3 = r33
            android.graphics.drawable.Drawable r3 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r3, r13)
            r0.repeatOffButtonDrawable = r3
            java.lang.String r3 = "ic_repeat_one.png"
            android.graphics.drawable.Drawable r3 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r3, r13)
            r0.repeatOneButtonDrawable = r3
            java.lang.String r3 = "ic_repeat_all.png"
            android.graphics.drawable.Drawable r3 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r3, r13)
            r0.repeatAllButtonDrawable = r3
            java.lang.String r3 = "ic_shuffle_on.png"
            android.graphics.drawable.Drawable r3 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r3, r13)
            r0.shuffleOnButtonDrawable = r3
            r3 = r28
            android.graphics.drawable.Drawable r1 = com.google.android.exoplayer2.ui.UiHelper.getDrawable(r1, r3, r13)
            r0.shuffleOffButtonDrawable = r1
            java.lang.String r1 = "Exit fullscreen"
            r0.fullScreenExitContentDescription = r1
            java.lang.String r1 = "Enter fullscreen"
            r0.fullScreenEnterContentDescription = r1
            java.lang.String r1 = "Repeat off"
            r0.repeatOffButtonContentDescription = r1
            java.lang.String r1 = "Repeat one"
            r0.repeatOneButtonContentDescription = r1
            java.lang.String r1 = "Repeat all"
            r0.repeatAllButtonContentDescription = r1
            java.lang.String r1 = "Shuffle on"
            r0.shuffleOnContentDescription = r1
            java.lang.String r1 = "Shuffle off"
            r0.shuffleOffContentDescription = r1
            java.lang.String r1 = "Disable Subtitles"
            r0.subtitleOnContentDescription = r1
            java.lang.String r1 = "Enable Subtitles"
            r0.subtitleOffContentDescription = r1
            r1 = 1
            r5.setShowButton(r8, r1)
            r3 = r26
            r5.setShowButton(r6, r3)
            r3 = r25
            r5.setShowButton(r12, r3)
            r3 = r24
            r5.setShowButton(r11, r3)
            r3 = r23
            r5.setShowButton(r2, r3)
            r2 = r22
            r5.setShowButton(r7, r2)
            r2 = r21
            r5.setShowButton(r4, r2)
            int r2 = r0.repeatToggleModes
            r1 = r17
            if (r2 == 0) goto L_0x04cb
            r3 = 1
            goto L_0x04cc
        L_0x04cb:
            r3 = 0
        L_0x04cc:
            r5.setShowButton(r1, r3)
            r1 = r20
            r2 = r30
            r5.setShowButton(r2, r1)
            r1 = r19
            r2 = r37
            r5.setShowButton(r2, r1)
            com.google.android.exoplayer2.ui.StyledPlayerControlView$$Lambda$2 r1 = new com.google.android.exoplayer2.ui.StyledPlayerControlView$$Lambda$2
            r1.<init>(r0)
            r0.addOnLayoutChangeListener(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.StyledPlayerControlView.<init>(android.content.Context, com.google.android.exoplayer2.ui.TimeBarAttributes, com.google.android.exoplayer2.ui.PlayerAttributes):void");
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

    private void dispatchPause(Player player2) {
        this.controlDispatcher.a(player2, false);
    }

    private void dispatchPlay(Player player2) {
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

    /* access modifiers changed from: private */
    public void dispatchPlayPause(Player player2) {
        int h = player2.h();
        if (h == 1 || h == 4 || !player2.l()) {
            dispatchPlay(player2);
        } else {
            dispatchPause(player2);
        }
    }

    /* access modifiers changed from: private */
    public void displaySettingsWindow(M m) {
        this.settingsView.a(m);
        updateSettingsWindowSize();
        this.needToHideBars = false;
        this.settingsWindow.dismiss();
        this.needToHideBars = true;
        this.settingsWindow.showAsDropDown(this, (getWidth() - this.settingsWindow.getWidth()) - this.settingsWindowMargin, (-this.settingsWindow.getHeight()) - this.settingsWindowMargin);
    }

    private void gatherTrackInfosForAdapter(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int i, List list) {
        int i2 = i;
        TrackGroupArray trackGroups = mappedTrackInfo.getTrackGroups(i);
        TrackSelection trackSelection = ((Player) Assertions.b((Object) this.player)).C().b[i2];
        for (int i3 = 0; i3 < trackGroups.length; i3++) {
            TrackGroup trackGroup = trackGroups.b[i3];
            for (int i4 = 0; i4 < trackGroup.a; i4++) {
                Format format = trackGroup.b[i4];
                if (mappedTrackInfo.getTrackSupport(i2, i3, i4) == 4) {
                    boolean z = (trackSelection == null || trackSelection.a(format) == -1) ? false : true;
                    list.add(new TrackInfo(i, i3, i4, this.trackNameProvider.getTrackName(format), z));
                } else {
                    List list2 = list;
                }
            }
            MappingTrackSelector.MappedTrackInfo mappedTrackInfo2 = mappedTrackInfo;
            List list3 = list;
        }
    }

    private void initTrackSelectionAdapter() {
        DefaultTrackSelector defaultTrackSelector;
        MappingTrackSelector.MappedTrackInfo currentMappedTrackInfo;
        this.textTrackSelectionAdapter.clear();
        this.audioTrackSelectionAdapter.clear();
        if (this.player != null && (defaultTrackSelector = this.trackSelector) != null && (currentMappedTrackInfo = defaultTrackSelector.getCurrentMappedTrackInfo()) != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            for (int i = 0; i < currentMappedTrackInfo.getRendererCount(); i++) {
                if (currentMappedTrackInfo.getRendererType(i) == 3 && this.controlViewLayoutManager.getShowButton(this.subtitleButton)) {
                    gatherTrackInfosForAdapter(currentMappedTrackInfo, i, arrayList);
                    arrayList3.add(Integer.valueOf(i));
                } else if (currentMappedTrackInfo.getRendererType(i) == 1) {
                    gatherTrackInfosForAdapter(currentMappedTrackInfo, i, arrayList2);
                    arrayList4.add(Integer.valueOf(i));
                }
            }
            this.textTrackSelectionAdapter.init(arrayList3, arrayList, currentMappedTrackInfo);
            this.audioTrackSelectionAdapter.init(arrayList4, arrayList2, currentMappedTrackInfo);
        }
    }

    private static void initializeFullScreenButton(View view, View.OnClickListener onClickListener) {
        if (view != null) {
            view.setVisibility(8);
            view.setOnClickListener(onClickListener);
        }
    }

    private static boolean isHandledMediaKey(int i) {
        return i == 90 || i == 89 || i == 85 || i == 79 || i == 126 || i == 127 || i == 87 || i == 88;
    }

    /* access modifiers changed from: private */
    /* renamed from: onFullScreenButtonClicked */
    public void bridge$lambda$1$StyledPlayerControlView(View view) {
        if (this.onFullScreenModeChangedListener != null) {
            boolean z = !this.isFullScreen;
            this.isFullScreen = z;
            updateFullScreenButtonForState(this.fullScreenButton, z);
            updateFullScreenButtonForState(this.minimalFullScreenButton, this.isFullScreen);
            OnFullScreenModeChangedListener onFullScreenModeChangedListener2 = this.onFullScreenModeChangedListener;
            if (onFullScreenModeChangedListener2 != null) {
                onFullScreenModeChangedListener2.onFullScreenModeChanged(this.isFullScreen);
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onLayoutChange */
    public void bridge$lambda$2$StyledPlayerControlView(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9 = i4 - i2;
        int i10 = i8 - i6;
        if (!(i3 - i == i7 - i5 && i9 == i10) && this.settingsWindow.isShowing()) {
            updateSettingsWindowSize();
            this.settingsWindow.update(view, (getWidth() - this.settingsWindow.getWidth()) - this.settingsWindowMargin, (-this.settingsWindow.getHeight()) - this.settingsWindowMargin, -1, -1);
        }
    }

    /* access modifiers changed from: private */
    public void onSettingViewClicked(int i) {
        if (i == 0) {
            displaySettingsWindow(this.playbackSpeedAdapter);
        } else if (i == 1) {
            displaySettingsWindow(this.audioTrackSelectionAdapter);
        } else {
            this.settingsWindow.dismiss();
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
        bridge$lambda$0$StyledPlayerControlView();
    }

    /* access modifiers changed from: private */
    public void setPlaybackSpeed(float f) {
        Player player2 = this.player;
        if (player2 != null) {
            this.controlDispatcher.a(player2, player2.o().a(f));
        }
    }

    private boolean shouldShowPauseButton() {
        Player player2 = this.player;
        return (player2 == null || player2.h() == 4 || this.player.h() == 1 || !this.player.l()) ? false : true;
    }

    private void updateButton(boolean z, View view) {
        if (view != null) {
            view.setEnabled(z);
            view.setAlpha(z ? 1.0f : 0.33f);
        }
    }

    private void updateFastForwardButton() {
        ControlDispatcher controlDispatcher2 = this.controlDispatcher;
        if (controlDispatcher2 instanceof DefaultControlDispatcher) {
            this.fastForwardMs = ((DefaultControlDispatcher) controlDispatcher2).b;
        }
        int i = (int) (this.fastForwardMs / 1000);
        TextView textView = this.fastForwardButtonTextView;
        if (textView != null) {
            textView.setText(String.valueOf(i));
        }
        View view = this.fastForwardButton;
        if (view != null) {
            view.setContentDescription("Fast forward (1) second");
        }
    }

    private void updateFullScreenButtonForState(ImageView imageView, boolean z) {
        String str;
        if (imageView != null) {
            if (z) {
                imageView.setImageDrawable(this.fullScreenExitDrawable);
                str = this.fullScreenExitContentDescription;
            } else {
                imageView.setImageDrawable(this.fullScreenEnterDrawable);
                str = this.fullScreenEnterContentDescription;
            }
            imageView.setContentDescription(str);
        }
    }

    private static void updateFullScreenButtonVisibility(View view, boolean z) {
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateNavigation() {
        /*
            r8 = this;
            boolean r0 = r8.isVisible()
            if (r0 == 0) goto L_0x00a3
            boolean r0 = r8.isAttachedToWindow
            if (r0 != 0) goto L_0x000c
            goto L_0x00a3
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
            r1 = r5
            goto L_0x007e
        L_0x007a:
            r0 = 0
            r3 = 0
            r4 = 0
            r6 = 0
        L_0x007e:
            if (r1 == 0) goto L_0x0083
            r8.updateRewindButton()
        L_0x0083:
            if (r6 == 0) goto L_0x0088
            r8.updateFastForwardButton()
        L_0x0088:
            android.view.View r2 = r8.previousButton
            r8.updateButton(r4, r2)
            android.view.View r2 = r8.rewindButton
            r8.updateButton(r1, r2)
            android.view.View r1 = r8.fastForwardButton
            r8.updateButton(r6, r1)
            android.view.View r1 = r8.nextButton
            r8.updateButton(r0, r1)
            com.google.android.exoplayer2.ui.TimeBar r0 = r8.timeBar
            if (r0 == 0) goto L_0x00a3
            r0.setEnabled(r3)
        L_0x00a3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.StyledPlayerControlView.updateNavigation():void");
    }

    /* access modifiers changed from: private */
    public void updatePlayPauseButton() {
        if (isVisible() && this.isAttachedToWindow && this.playPauseButton != null) {
            if (shouldShowPauseButton()) {
                ((ImageView) this.playPauseButton).setImageDrawable(UiHelper.getDrawable(this.context, UiHelper.IC_PAUSE, this.debugMode));
                this.playPauseButton.setContentDescription("Pause");
                return;
            }
            ((ImageView) this.playPauseButton).setImageDrawable(UiHelper.getDrawable(this.context, UiHelper.IC_PLAY, this.debugMode));
            this.playPauseButton.setContentDescription("Play");
        }
    }

    /* access modifiers changed from: private */
    public void updatePlaybackSpeedList() {
        Player player2 = this.player;
        if (player2 != null) {
            this.playbackSpeedAdapter.updateSelectedIndex(player2.o().b);
            this.settingsAdapter.setSubTextAtPosition(0, this.playbackSpeedAdapter.getSelectedText());
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: updateProgress */
    public void bridge$lambda$0$StyledPlayerControlView() {
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
        ImageView imageView2;
        String str;
        if (isVisible() && this.isAttachedToWindow && (imageView = this.repeatToggleButton) != null) {
            if (this.repeatToggleModes == 0) {
                updateButton(false, imageView);
                return;
            }
            Player player2 = this.player;
            if (player2 == null) {
                updateButton(false, imageView);
            } else {
                updateButton(true, imageView);
                switch (player2.m()) {
                    case 0:
                        break;
                    case 1:
                        this.repeatToggleButton.setImageDrawable(this.repeatOneButtonDrawable);
                        imageView2 = this.repeatToggleButton;
                        str = this.repeatOneButtonContentDescription;
                        break;
                    case 2:
                        this.repeatToggleButton.setImageDrawable(this.repeatAllButtonDrawable);
                        this.repeatToggleButton.setContentDescription(this.repeatAllButtonContentDescription);
                        return;
                    default:
                        return;
                }
            }
            this.repeatToggleButton.setImageDrawable(this.repeatOffButtonDrawable);
            imageView2 = this.repeatToggleButton;
            str = this.repeatOffButtonContentDescription;
            imageView2.setContentDescription(str);
        }
    }

    private void updateRewindButton() {
        ControlDispatcher controlDispatcher2 = this.controlDispatcher;
        if (controlDispatcher2 instanceof DefaultControlDispatcher) {
            this.rewindMs = ((DefaultControlDispatcher) controlDispatcher2).a;
        }
        int i = (int) (this.rewindMs / 1000);
        TextView textView = this.rewindButtonTextView;
        if (textView != null) {
            textView.setText(String.valueOf(i));
        }
        View view = this.rewindButton;
        if (view != null) {
            view.setContentDescription("Rewind (1) second");
        }
    }

    private void updateSettingsWindowSize() {
        this.settingsView.measure(0, 0);
        this.settingsWindow.setWidth(Math.min(this.settingsView.getMeasuredWidth(), getWidth() - (this.settingsWindowMargin << 1)));
        this.settingsWindow.setHeight(Math.min(getHeight() - (this.settingsWindowMargin << 1), this.settingsView.getMeasuredHeight()));
    }

    /* access modifiers changed from: private */
    public void updateShuffleButton() {
        ImageView imageView;
        String str;
        ImageView imageView2;
        if (isVisible() && this.isAttachedToWindow && (imageView = this.shuffleButton) != null) {
            Player player2 = this.player;
            if (!this.controlViewLayoutManager.getShowButton(imageView)) {
                updateButton(false, this.shuffleButton);
                return;
            }
            if (player2 == null) {
                updateButton(false, this.shuffleButton);
                this.shuffleButton.setImageDrawable(this.shuffleOffButtonDrawable);
                imageView2 = this.shuffleButton;
            } else {
                updateButton(true, this.shuffleButton);
                this.shuffleButton.setImageDrawable(player2.n() ? this.shuffleOnButtonDrawable : this.shuffleOffButtonDrawable);
                imageView2 = this.shuffleButton;
                if (player2.n()) {
                    str = this.shuffleOnContentDescription;
                    imageView2.setContentDescription(str);
                }
            }
            str = this.shuffleOffContentDescription;
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
            bridge$lambda$0$StyledPlayerControlView();
        }
    }

    /* access modifiers changed from: private */
    public void updateTrackLists() {
        initTrackSelectionAdapter();
        updateButton(this.textTrackSelectionAdapter.getItemCount() > 0, this.subtitleButton);
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

    public boolean getIsFullscreen() {
        return this.isFullScreen;
    }

    public Player getPlayer() {
        return this.player;
    }

    public int getRepeatToggleModes() {
        return this.repeatToggleModes;
    }

    public boolean getShowShuffleButton() {
        return this.controlViewLayoutManager.getShowButton(this.shuffleButton);
    }

    public boolean getShowSubtitleButton() {
        return this.controlViewLayoutManager.getShowButton(this.subtitleButton);
    }

    public int getShowTimeoutMs() {
        return this.showTimeoutMs;
    }

    public boolean getShowVideoSettingsButton() {
        return this.controlViewLayoutManager.getShowButton(this.videoSettingsButton);
    }

    public boolean getShowVrButton() {
        return this.controlViewLayoutManager.getShowButton(this.vrButton);
    }

    public void hide() {
        this.controlViewLayoutManager.hide();
    }

    public void hideImmediately() {
        this.controlViewLayoutManager.hideImmediately();
    }

    public boolean isAnimationEnabled() {
        return this.controlViewLayoutManager.isAnimationEnabled();
    }

    public boolean isFullyVisible() {
        return this.controlViewLayoutManager.isFullyVisible();
    }

    public boolean isVisible() {
        return getVisibility() == 0;
    }

    /* access modifiers changed from: package-private */
    public void notifyOnVisibilityChange() {
        Iterator it = this.visibilityListeners.iterator();
        while (it.hasNext()) {
            ((VisibilityListener) it.next()).onVisibilityChange(getVisibility());
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.controlViewLayoutManager.onAttachedToWindow();
        this.isAttachedToWindow = true;
        if (isFullyVisible()) {
            this.controlViewLayoutManager.resetHideCallbacks();
        }
        updateAll();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.controlViewLayoutManager.onDetachedFromWindow();
        this.isAttachedToWindow = false;
        removeCallbacks(this.updateProgressAction);
        this.controlViewLayoutManager.removeHideCallbacks();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.controlViewLayoutManager.onLayout(z, i, i2, i3, i4);
    }

    public void removeVisibilityListener(VisibilityListener visibilityListener) {
        this.visibilityListeners.remove(visibilityListener);
    }

    /* access modifiers changed from: package-private */
    public void requestPlayPauseFocus() {
        View view = this.playPauseButton;
        if (view != null) {
            view.requestFocus();
        }
    }

    public void setAnimationEnabled(boolean z) {
        this.controlViewLayoutManager.setAnimationEnabled(z);
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
            Assertions.b((Object) zArr);
            if (jArr.length == zArr.length) {
                z = true;
            }
            Assertions.a(z);
            this.extraAdGroupTimesMs = jArr;
            this.extraPlayedAdGroups = zArr;
        }
        updateTimeline();
    }

    public void setOnFullScreenModeChangedListener(OnFullScreenModeChangedListener onFullScreenModeChangedListener2) {
        this.onFullScreenModeChangedListener = onFullScreenModeChangedListener2;
        boolean z = true;
        updateFullScreenButtonVisibility(this.fullScreenButton, onFullScreenModeChangedListener2 != null);
        ImageView imageView = this.minimalFullScreenButton;
        if (onFullScreenModeChangedListener2 == null) {
            z = false;
        }
        updateFullScreenButtonVisibility(imageView, z);
    }

    public void setOnSettingsWindowDismissListener(OnSettingsWindowDismissListener onSettingsWindowDismissListener2) {
        this.onSettingsWindowDismissListener = onSettingsWindowDismissListener2;
    }

    public void setPlaybackPreparer(PlaybackPreparer playbackPreparer2) {
        this.playbackPreparer = playbackPreparer2;
    }

    public void setPlayer(Player player2) {
        DefaultTrackSelector defaultTrackSelector;
        boolean z = true;
        Assertions.b(Looper.myLooper() == Looper.getMainLooper());
        if (!(player2 == null || player2.f() == Looper.getMainLooper())) {
            z = false;
        }
        Assertions.a(z);
        Player player3 = this.player;
        if (player3 != player2) {
            if (player3 != null) {
                player3.b((Player.EventListener) this.componentListener);
            }
            this.player = player2;
            if (player2 != null) {
                player2.a((Player.EventListener) this.componentListener);
            }
            if (player2 instanceof ExoPlayer) {
                TrackSelector trackSelector2 = ((ExoPlayer) player2).getTrackSelector();
                if (trackSelector2 instanceof DefaultTrackSelector) {
                    defaultTrackSelector = (DefaultTrackSelector) trackSelector2;
                }
                updateAll();
            }
            defaultTrackSelector = null;
            this.trackSelector = defaultTrackSelector;
            updateAll();
        }
    }

    public void setProgressUpdateListener(ProgressUpdateListener progressUpdateListener2) {
        this.progressUpdateListener = progressUpdateListener2;
    }

    public void setRepeatToggleModes(int i) {
        this.repeatToggleModes = i;
        Player player2 = this.player;
        boolean z = false;
        if (player2 != null) {
            int m = player2.m();
            if (i == 0 && m != 0) {
                this.controlDispatcher.a(this.player, 0);
            } else if (i == 1 && m == 2) {
                this.controlDispatcher.a(this.player, 1);
            } else if (i == 2 && m == 1) {
                this.controlDispatcher.a(this.player, 2);
            }
        }
        StyledPlayerControlViewLayoutManager styledPlayerControlViewLayoutManager = this.controlViewLayoutManager;
        ImageView imageView = this.repeatToggleButton;
        if (i != 0) {
            z = true;
        }
        styledPlayerControlViewLayoutManager.setShowButton(imageView, z);
        updateRepeatModeButton();
    }

    public void setShowFastForwardButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.fastForwardButton, z);
        updateNavigation();
    }

    public void setShowMultiWindowTimeBar(boolean z) {
        this.showMultiWindowTimeBar = z;
        updateTimeline();
    }

    public void setShowNextButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.nextButton, z);
        updateNavigation();
    }

    public void setShowPreviousButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.previousButton, z);
        updateNavigation();
    }

    public void setShowRewindButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.rewindButton, z);
        updateNavigation();
    }

    public void setShowShuffleButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.shuffleButton, z);
        updateShuffleButton();
    }

    public void setShowSubtitleButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.subtitleButton, z);
    }

    public void setShowTimeoutMs(int i) {
        this.showTimeoutMs = i;
        if (isFullyVisible()) {
            this.controlViewLayoutManager.resetHideCallbacks();
        }
    }

    public void setShowVideoSettingsButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.videoSettingsButton, z);
        updateNavigation();
    }

    public void setShowVrButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.vrButton, z);
    }

    public void setTimeBarMinUpdateInterval(int i) {
        this.timeBarMinUpdateIntervalMs = Util.a(i, 16, 1000);
    }

    public void setVideoSettingsButtonListener(View.OnClickListener onClickListener) {
        View view = this.videoSettingsButton;
        if (view != null) {
            view.setOnClickListener(onClickListener);
            updateButton(onClickListener != null, this.videoSettingsButton);
        }
    }

    public void setVrButtonListener(View.OnClickListener onClickListener) {
        View view = this.vrButton;
        if (view != null) {
            view.setOnClickListener(onClickListener);
            updateButton(onClickListener != null, this.vrButton);
        }
    }

    public void show() {
        this.controlViewLayoutManager.show();
    }

    /* access modifiers changed from: package-private */
    public void updateAll() {
        updatePlayPauseButton();
        updateNavigation();
        updateRepeatModeButton();
        updateShuffleButton();
        updateTrackLists();
        updatePlaybackSpeedList();
        updateTimeline();
    }
}
