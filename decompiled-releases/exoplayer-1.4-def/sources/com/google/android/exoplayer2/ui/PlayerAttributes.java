package com.google.android.exoplayer2.ui;

public class PlayerAttributes {
    private final boolean animationEnabled;
    private final boolean autoShowController;
    private final int controllerTimeout;
    private final int fastForwardMs;
    private final long hideAtMs = -9223372036854775807L;
    private final boolean hideDuringAds;
    private final boolean hideOnTouch;
    private final boolean isDebugMode;
    private final int repeatToggleModes;
    private final int resizeMode;
    private final int rewindMs;
    private final int showBuffering;
    private final boolean showFastForwardButton;
    private final boolean showFullscreenButton;
    private final boolean showNextButton;
    private final boolean showPreviousButton;
    private final boolean showRewindButton;
    private final boolean showShuffleButton;
    private final boolean showSubtitleButton;
    private final int showTimeoutMs = 5000;
    private final boolean showVideoSettingsButton;
    private final int surfaceType;
    private final int timeBarMinUpdateIntervalMs = 200;
    private final boolean useArtWork;
    private final boolean useController;

    public PlayerAttributes(int i, boolean z, int i2, int i3, boolean z2, boolean z3, int i4, boolean z4, boolean z5, boolean z6, int i5, int i6, int i7, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15) {
        this.surfaceType = i;
        this.useArtWork = z;
        this.resizeMode = i2;
        this.controllerTimeout = i3;
        this.hideOnTouch = z2;
        this.autoShowController = z3;
        this.showBuffering = i4;
        this.useController = z4;
        this.hideDuringAds = z5;
        this.isDebugMode = z6;
        this.rewindMs = i5;
        this.fastForwardMs = i6;
        this.repeatToggleModes = i7;
        this.showRewindButton = z7;
        this.showFastForwardButton = z8;
        this.showPreviousButton = z9;
        this.showNextButton = z10;
        this.showShuffleButton = z11;
        this.showSubtitleButton = z12;
        this.showFullscreenButton = z13;
        this.showVideoSettingsButton = z14;
        this.animationEnabled = z15;
    }

    static PlayerAttributes createDefault() {
        return new PlayerAttributes(1, true, 0, 5000, true, true, 1, true, true, false, 5000, 5000, 1, true, true, true, true, false, true, false, false, true);
    }

    public boolean getAnimationEnabled() {
        return this.animationEnabled;
    }

    public boolean getAutoShowController() {
        return this.autoShowController;
    }

    public int getControllerTimeout() {
        return this.controllerTimeout;
    }

    public int getFastForwardMs() {
        return this.fastForwardMs;
    }

    public long getHideAtMs() {
        return -9223372036854775807L;
    }

    public boolean getHideDuringAds() {
        return this.hideDuringAds;
    }

    public boolean getHideOnTouch() {
        return this.hideOnTouch;
    }

    public int getRepeatToggleModes() {
        return this.repeatToggleModes;
    }

    public int getResizeMode() {
        return this.resizeMode;
    }

    public int getRewindMs() {
        return this.rewindMs;
    }

    public int getShowBuffering() {
        return this.showBuffering;
    }

    public boolean getShowFastForwardButton() {
        return this.showFastForwardButton;
    }

    public boolean getShowFullscreenButton() {
        return this.showFullscreenButton;
    }

    public boolean getShowNextButton() {
        return this.showNextButton;
    }

    public boolean getShowPreviousButton() {
        return this.showPreviousButton;
    }

    public boolean getShowRewindButton() {
        return this.showRewindButton;
    }

    public boolean getShowShuffleButton() {
        return this.showShuffleButton;
    }

    public boolean getShowSubtitleButton() {
        return this.showSubtitleButton;
    }

    public int getShowTimeoutMs() {
        return 5000;
    }

    public boolean getShowVideoSettingsButton() {
        return this.showVideoSettingsButton;
    }

    public int getSurfaceType() {
        return this.surfaceType;
    }

    public int getTimeBarMinUpdateIntervalMs() {
        return 200;
    }

    public boolean getUseArtWork() {
        return this.useArtWork;
    }

    public boolean getUseController() {
        return this.useController;
    }

    public boolean isDebugMode() {
        return this.isDebugMode;
    }
}
