package com.dreamers.exoplayercore.repack;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewGroup;

public final class Y extends ViewGroup.MarginLayoutParams {
    C0010ai a;
    final Rect b = new Rect();
    boolean c = true;
    boolean d = false;

    public Y() {
        super(-2, -2);
    }

    public Y(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public Y(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
    }

    public Y(ViewGroup.MarginLayoutParams marginLayoutParams) {
        super(marginLayoutParams);
    }

    public Y(Y y) {
        super(y);
    }
}
