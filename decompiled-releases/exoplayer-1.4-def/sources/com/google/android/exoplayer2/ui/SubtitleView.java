package com.google.android.exoplayer2.ui;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.accessibility.CaptioningManager;
import android.widget.FrameLayout;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.TextOutput;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SubtitleView extends FrameLayout implements TextOutput {
    public static final float DEFAULT_BOTTOM_PADDING_FRACTION = 0.08f;
    public static final float DEFAULT_TEXT_SIZE_FRACTION = 0.0533f;
    public static final int VIEW_TYPE_CANVAS = 1;
    public static final int VIEW_TYPE_WEB = 2;
    private boolean applyEmbeddedFontSizes;
    private boolean applyEmbeddedStyles;
    private float bottomPaddingFraction;
    private List cues;
    private float defaultTextSize;
    private int defaultTextSizeType;
    private View innerSubtitleView;
    private Output output;
    private CaptionStyleCompat style;
    private int viewType;

    interface Output {
        void update(List list, CaptionStyleCompat captionStyleCompat, float f, int i, float f2);
    }

    public @interface ViewType {
    }

    public SubtitleView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SubtitleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.cues = Collections.emptyList();
        this.style = CaptionStyleCompat.DEFAULT;
        this.defaultTextSizeType = 0;
        this.defaultTextSize = 0.0533f;
        this.bottomPaddingFraction = 0.08f;
        this.applyEmbeddedStyles = true;
        this.applyEmbeddedFontSizes = true;
        CanvasSubtitleOutput canvasSubtitleOutput = new CanvasSubtitleOutput(context);
        this.output = canvasSubtitleOutput;
        this.innerSubtitleView = canvasSubtitleOutput;
        addView(canvasSubtitleOutput);
        this.viewType = 1;
    }

    private List getCuesWithStylingPreferencesApplied() {
        if (this.applyEmbeddedStyles && this.applyEmbeddedFontSizes) {
            return this.cues;
        }
        ArrayList arrayList = new ArrayList(this.cues.size());
        for (int i = 0; i < this.cues.size(); i++) {
            arrayList.add(removeEmbeddedStyling((Cue) this.cues.get(i)));
        }
        return arrayList;
    }

    private float getUserCaptionFontScale() {
        CaptioningManager captioningManager;
        if (Util.a < 19 || isInEditMode() || (captioningManager = (CaptioningManager) getContext().getSystemService("captioning")) == null || !captioningManager.isEnabled()) {
            return 1.0f;
        }
        return captioningManager.getFontScale();
    }

    private CaptionStyleCompat getUserCaptionStyle() {
        if (Util.a < 19 || isInEditMode()) {
            return CaptionStyleCompat.DEFAULT;
        }
        CaptioningManager captioningManager = (CaptioningManager) getContext().getSystemService("captioning");
        return (captioningManager == null || !captioningManager.isEnabled()) ? CaptionStyleCompat.DEFAULT : CaptionStyleCompat.createFromCaptionStyle(captioningManager.getUserStyle());
    }

    private Cue removeEmbeddedStyling(Cue cue) {
        Cue.Builder a = cue.a();
        if (!this.applyEmbeddedStyles) {
            SubtitleViewUtils.removeAllEmbeddedStyling(a);
        } else if (!this.applyEmbeddedFontSizes) {
            SubtitleViewUtils.removeEmbeddedFontSizes(a);
        }
        return a.a();
    }

    private void setTextSize(int i, float f) {
        this.defaultTextSizeType = i;
        this.defaultTextSize = f;
        updateOutput();
    }

    private void setView(View view) {
        removeView(this.innerSubtitleView);
        View view2 = this.innerSubtitleView;
        if (view2 instanceof WebViewSubtitleOutput) {
            ((WebViewSubtitleOutput) view2).destroy();
        }
        this.innerSubtitleView = view;
        this.output = (Output) view;
        addView(view);
    }

    private void updateOutput() {
        this.output.update(getCuesWithStylingPreferencesApplied(), this.style, this.defaultTextSize, this.defaultTextSizeType, this.bottomPaddingFraction);
    }

    public final void onCues(List list) {
        setCues(list);
    }

    public final void setApplyEmbeddedFontSizes(boolean z) {
        this.applyEmbeddedFontSizes = z;
        updateOutput();
    }

    public final void setApplyEmbeddedStyles(boolean z) {
        this.applyEmbeddedStyles = z;
        updateOutput();
    }

    public final void setBottomPaddingFraction(float f) {
        this.bottomPaddingFraction = f;
        updateOutput();
    }

    public final void setCues(List list) {
        if (list == null) {
            list = Collections.emptyList();
        }
        this.cues = list;
        updateOutput();
    }

    public final void setFixedTextSize(int i, float f) {
        Context context = getContext();
        setTextSize(2, TypedValue.applyDimension(i, f, (context == null ? Resources.getSystem() : context.getResources()).getDisplayMetrics()));
    }

    public final void setFractionalTextSize(float f) {
        setFractionalTextSize(f, false);
    }

    public final void setFractionalTextSize(float f, boolean z) {
        setTextSize(z ? 1 : 0, f);
    }

    public final void setStyle(CaptionStyleCompat captionStyleCompat) {
        this.style = captionStyleCompat;
        updateOutput();
    }

    public final void setUserDefaultStyle() {
        setStyle(getUserCaptionStyle());
    }

    public final void setUserDefaultTextSize() {
        setFractionalTextSize(getUserCaptionFontScale() * 0.0533f);
    }

    public final void setViewType(int i) {
        View view;
        if (this.viewType != i) {
            switch (i) {
                case 1:
                    view = new CanvasSubtitleOutput(getContext());
                    break;
                case 2:
                    view = new WebViewSubtitleOutput(getContext());
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            setView(view);
            this.viewType = i;
        }
    }
}
