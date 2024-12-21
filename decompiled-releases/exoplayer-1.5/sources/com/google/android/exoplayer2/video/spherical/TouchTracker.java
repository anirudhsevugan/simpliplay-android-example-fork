package com.google.android.exoplayer2.video.spherical;

import android.content.Context;
import android.graphics.PointF;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.exoplayer2.video.spherical.OrientationListener;

final class TouchTracker extends GestureDetector.SimpleOnGestureListener implements View.OnTouchListener, OrientationListener.Listener {
    private final PointF a = new PointF();
    private final PointF b = new PointF();
    private final Listener c;
    private final GestureDetector d;
    private volatile float e;

    public interface Listener {
        void a(PointF pointF);

        boolean a();
    }

    public TouchTracker(Context context, Listener listener) {
        this.c = listener;
        this.d = new GestureDetector(context, this);
        this.e = 3.1415927f;
    }

    public final void a(float[] fArr, float f) {
        this.e = -f;
    }

    public final boolean onDown(MotionEvent motionEvent) {
        this.a.set(motionEvent.getX(), motionEvent.getY());
        return true;
    }

    public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        float x = (motionEvent2.getX() - this.a.x) / 25.0f;
        float y = (motionEvent2.getY() - this.a.y) / 25.0f;
        this.a.set(motionEvent2.getX(), motionEvent2.getY());
        double d2 = (double) this.e;
        float cos = (float) Math.cos(d2);
        float sin = (float) Math.sin(d2);
        this.b.x -= (cos * x) - (sin * y);
        this.b.y += (sin * x) + (cos * y);
        PointF pointF = this.b;
        pointF.y = Math.max(-45.0f, Math.min(45.0f, pointF.y));
        this.c.a(this.b);
        return true;
    }

    public final boolean onSingleTapUp(MotionEvent motionEvent) {
        return this.c.a();
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        return this.d.onTouchEvent(motionEvent);
    }
}
