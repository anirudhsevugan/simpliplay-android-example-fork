package com.google.android.exoplayer2.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.ui.SubtitleView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class CanvasSubtitleOutput extends View implements SubtitleView.Output {
    private float bottomPaddingFraction;
    private List cues;
    private final List painters;
    private CaptionStyleCompat style;
    private float textSize;
    private int textSizeType;

    public CanvasSubtitleOutput(Context context) {
        this(context, (AttributeSet) null);
    }

    public CanvasSubtitleOutput(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.painters = new ArrayList();
        this.cues = Collections.emptyList();
        this.textSizeType = 0;
        this.textSize = 0.0533f;
        this.style = CaptionStyleCompat.DEFAULT;
        this.bottomPaddingFraction = 0.08f;
    }

    private static Cue repositionVerticalCue(Cue cue) {
        Cue.Builder a = cue.a();
        a.f = -3.4028235E38f;
        a.g = Integer.MIN_VALUE;
        a.c = null;
        if (cue.g == 0) {
            a.a(1.0f - cue.f, 0);
        } else {
            a.a((-cue.f) - 1.0f, 1);
        }
        switch (cue.h) {
            case 0:
                a.e = 2;
                break;
            case 2:
                a.e = 0;
                break;
        }
        return a.a();
    }

    public final void dispatchDraw(Canvas canvas) {
        List list = this.cues;
        if (!list.isEmpty()) {
            int height = getHeight();
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int width = getWidth() - getPaddingRight();
            int paddingBottom = height - getPaddingBottom();
            if (paddingBottom > paddingTop && width > paddingLeft) {
                int i = paddingBottom - paddingTop;
                float resolveTextSize = SubtitleViewUtils.resolveTextSize(this.textSizeType, this.textSize, height, i);
                if (resolveTextSize > 0.0f) {
                    int size = list.size();
                    int i2 = 0;
                    while (i2 < size) {
                        Cue cue = (Cue) list.get(i2);
                        if (cue.q != Integer.MIN_VALUE) {
                            cue = repositionVerticalCue(cue);
                        }
                        Cue cue2 = cue;
                        float f = resolveTextSize;
                        int i3 = paddingBottom;
                        ((SubtitlePainter) this.painters.get(i2)).draw(cue2, this.style, f, SubtitleViewUtils.resolveTextSize(cue2.o, cue2.p, height, i), this.bottomPaddingFraction, canvas, paddingLeft, paddingTop, width, i3);
                        i2++;
                        size = size;
                        i = i;
                        paddingBottom = i3;
                        width = width;
                    }
                }
            }
        }
    }

    public final void update(List list, CaptionStyleCompat captionStyleCompat, float f, int i, float f2) {
        this.cues = list;
        this.style = captionStyleCompat;
        this.textSize = f;
        this.textSizeType = i;
        this.bottomPaddingFraction = f2;
        while (this.painters.size() < list.size()) {
            this.painters.add(new SubtitlePainter(getContext()));
        }
        invalidate();
    }
}
