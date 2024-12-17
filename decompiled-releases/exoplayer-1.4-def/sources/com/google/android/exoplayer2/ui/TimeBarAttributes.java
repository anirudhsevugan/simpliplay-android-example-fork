package com.google.android.exoplayer2.ui;

import android.graphics.drawable.Drawable;

public class TimeBarAttributes {
    private final int adMarkerColor;
    private final int adMarkerWidth;
    private final int barGravity;
    private final int barHeight;
    private final int bufferedColor;
    private final int playedAdMarkerColor;
    private final int playedColor;
    private final int scrubberColor;
    private final int scrubberDisabledSize;
    private final int scrubberDraggedSize;
    private final Drawable scrubberDrawable;
    private final int scrubberEnabledSize;
    private final int touchTargetHeight;
    private final int unplayedColor;

    public TimeBarAttributes(Drawable drawable, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13) {
        this.scrubberDrawable = drawable;
        this.barHeight = i;
        this.touchTargetHeight = i2;
        this.barGravity = i3;
        this.adMarkerWidth = i4;
        this.scrubberEnabledSize = i5;
        this.scrubberDisabledSize = i6;
        this.scrubberDraggedSize = i7;
        this.playedColor = i8;
        this.scrubberColor = i9;
        this.bufferedColor = i10;
        this.unplayedColor = i11;
        this.adMarkerColor = i12;
        this.playedAdMarkerColor = i13;
    }

    public static TimeBarAttributes createDefault() {
        return new TimeBarAttributes((Drawable) null, 4, 26, 0, 4, 12, 0, 16, -1, -1, DefaultTimeBar.DEFAULT_BUFFERED_COLOR, DefaultTimeBar.DEFAULT_UNPLAYED_COLOR, DefaultTimeBar.DEFAULT_AD_MARKER_COLOR, DefaultTimeBar.DEFAULT_PLAYED_AD_MARKER_COLOR);
    }

    public int getAdMarkerColor() {
        return this.adMarkerColor;
    }

    public int getAdMarkerWidth() {
        return this.adMarkerWidth;
    }

    public int getBarGravity() {
        return this.barGravity;
    }

    public int getBarHeight() {
        return this.barHeight;
    }

    public int getBufferedColor() {
        return this.bufferedColor;
    }

    public int getPlayedAdMarkerColor() {
        return this.playedAdMarkerColor;
    }

    public int getPlayedColor() {
        return this.playedColor;
    }

    public int getScrubberColor() {
        return this.scrubberColor;
    }

    public int getScrubberDisabledSize() {
        return this.scrubberDisabledSize;
    }

    public int getScrubberDraggedSize() {
        return this.scrubberDraggedSize;
    }

    public Drawable getScrubberDrawable() {
        return this.scrubberDrawable;
    }

    public int getScrubberEnabledSize() {
        return this.scrubberEnabledSize;
    }

    public int getTouchTargetHeight() {
        return this.touchTargetHeight;
    }

    public int getUnplayedColor() {
        return this.unplayedColor;
    }
}
