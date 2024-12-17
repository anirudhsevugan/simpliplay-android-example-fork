package com.dreamers.relativeview;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.core.view.GravityCompat;
import com.dreamers.relativeview.repack.a;
import com.dreamers.relativeview.repack.b;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.AndroidViewComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;

public final class RelativeView extends AndroidNonvisibleComponent {
    private FrameLayout a;
    private final Context b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RelativeView(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        b.b(componentContainer, "container");
        Activity $context = componentContainer.$context();
        b.a((Object) $context, "container.`$context`()");
        this.b = $context;
    }

    public final void Add(AndroidViewComponent androidViewComponent, int i) {
        b.b(androidViewComponent, "view");
        if (this.a != null) {
            View view = androidViewComponent.getView();
            view.setVisibility(0);
            if (view.getParent() != null) {
                ViewParent parent = view.getParent();
                if (parent != null) {
                    ((ViewGroup) parent).removeView(view);
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
                }
            }
            int i2 = view.getLayoutParams().height == 0 ? -1 : view.getLayoutParams().height;
            Log.v("RelativeView", "Add : View : " + androidViewComponent + " | Height : " + i2 + " | Width : " + view.getLayoutParams().width + " | Gravity : " + i);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(view.getLayoutParams().width, i2, i);
            FrameLayout frameLayout = this.a;
            if (frameLayout != null) {
                frameLayout.addView(androidViewComponent.getView(), layoutParams);
                return;
            }
            return;
        }
        throw new YailRuntimeError("Parent layout can't be null. Make sure to create parent layout before adding views.", "RelativeView");
    }

    public final int Bottom() {
        return 80;
    }

    public final int Center() {
        return 17;
    }

    public final int CenterHorizontal() {
        return 1;
    }

    public final int CenterVertical() {
        return 16;
    }

    public final void Create(AndroidViewComponent androidViewComponent) {
        b.b(androidViewComponent, "in");
        FrameLayout frameLayout = new FrameLayout(this.b);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        a aVar = a.a;
        this.a = frameLayout;
        View view = androidViewComponent.getView();
        if (view != null) {
            ((ViewGroup) view).addView(this.a);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
    }

    public final int End() {
        return GravityCompat.END;
    }

    public final int Fill() {
        return 119;
    }

    public final int FillHorizontal() {
        return 7;
    }

    public final int FillVertical() {
        return 112;
    }

    public final int Left() {
        return 3;
    }

    public final int NoGravity() {
        return 0;
    }

    public final int Right() {
        return 5;
    }

    public final int Start() {
        return GravityCompat.START;
    }

    public final int Top() {
        return 48;
    }
}
