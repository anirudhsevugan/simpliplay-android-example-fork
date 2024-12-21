package com.google.android.exoplayer2.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public final class AspectRatioFrameLayout extends FrameLayout {
    private static final float MAX_ASPECT_RATIO_DEFORMATION_FRACTION = 0.01f;
    public static final int RESIZE_MODE_FILL = 3;
    public static final int RESIZE_MODE_FIT = 0;
    public static final int RESIZE_MODE_FIXED_HEIGHT = 2;
    public static final int RESIZE_MODE_FIXED_WIDTH = 1;
    public static final int RESIZE_MODE_ZOOM = 4;
    /* access modifiers changed from: private */
    public AspectRatioListener aspectRatioListener;
    private final AspectRatioUpdateDispatcher aspectRatioUpdateDispatcher;
    private int resizeMode;
    private float videoAspectRatio;

    public interface AspectRatioListener {
        void onAspectRatioUpdated(float f, float f2, boolean z);
    }

    final class AspectRatioUpdateDispatcher implements Runnable {
        private boolean aspectRatioMismatch;
        private boolean isScheduled;
        private float naturalAspectRatio;
        private float targetAspectRatio;

        private AspectRatioUpdateDispatcher() {
        }

        public final void run() {
            this.isScheduled = false;
            if (AspectRatioFrameLayout.this.aspectRatioListener != null) {
                AspectRatioFrameLayout.this.aspectRatioListener.onAspectRatioUpdated(this.targetAspectRatio, this.naturalAspectRatio, this.aspectRatioMismatch);
            }
        }

        public final void scheduleUpdate(float f, float f2, boolean z) {
            this.targetAspectRatio = f;
            this.naturalAspectRatio = f2;
            this.aspectRatioMismatch = z;
            if (!this.isScheduled) {
                this.isScheduled = true;
                AspectRatioFrameLayout.this.post(this);
            }
        }
    }

    public @interface ResizeMode {
    }

    public AspectRatioFrameLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public AspectRatioFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.resizeMode = 0;
        this.aspectRatioUpdateDispatcher = new AspectRatioUpdateDispatcher();
    }

    public final int getResizeMode() {
        return this.resizeMode;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0039, code lost:
        if (r4 <= 0.0f) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003c, code lost:
        r8 = (int) (r2 * r7.videoAspectRatio);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0042, code lost:
        r0 = (int) (r1 / r7.videoAspectRatio);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0049, code lost:
        if (r4 > 0.0f) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004c, code lost:
        r7.aspectRatioUpdateDispatcher.scheduleUpdate(r7.videoAspectRatio, r3, true);
        super.onMeasure(android.view.View.MeasureSpec.makeMeasureSpec(r8, gnu.expr.Declaration.MODULE_REFERENCE), android.view.View.MeasureSpec.makeMeasureSpec(r0, gnu.expr.Declaration.MODULE_REFERENCE));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0061, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onMeasure(int r8, int r9) {
        /*
            r7 = this;
            super.onMeasure(r8, r9)
            float r8 = r7.videoAspectRatio
            r9 = 0
            int r8 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
            if (r8 > 0) goto L_0x000b
            return
        L_0x000b:
            int r8 = r7.getMeasuredWidth()
            int r0 = r7.getMeasuredHeight()
            float r1 = (float) r8
            float r2 = (float) r0
            float r3 = r1 / r2
            float r4 = r7.videoAspectRatio
            float r4 = r4 / r3
            r5 = 1065353216(0x3f800000, float:1.0)
            float r4 = r4 - r5
            float r5 = java.lang.Math.abs(r4)
            r6 = 1008981770(0x3c23d70a, float:0.01)
            int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r5 > 0) goto L_0x0031
            com.google.android.exoplayer2.ui.AspectRatioFrameLayout$AspectRatioUpdateDispatcher r8 = r7.aspectRatioUpdateDispatcher
            float r9 = r7.videoAspectRatio
            r0 = 0
            r8.scheduleUpdate(r9, r3, r0)
            return
        L_0x0031:
            int r5 = r7.resizeMode
            switch(r5) {
                case 0: goto L_0x0047;
                case 1: goto L_0x0042;
                case 2: goto L_0x003c;
                case 3: goto L_0x0036;
                case 4: goto L_0x0037;
                default: goto L_0x0036;
            }
        L_0x0036:
            goto L_0x004c
        L_0x0037:
            int r9 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r9 > 0) goto L_0x003c
            goto L_0x0042
        L_0x003c:
            float r8 = r7.videoAspectRatio
            float r2 = r2 * r8
            int r8 = (int) r2
            goto L_0x004c
        L_0x0042:
            float r9 = r7.videoAspectRatio
            float r1 = r1 / r9
            int r0 = (int) r1
            goto L_0x004c
        L_0x0047:
            int r9 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r9 <= 0) goto L_0x003c
            goto L_0x0042
        L_0x004c:
            com.google.android.exoplayer2.ui.AspectRatioFrameLayout$AspectRatioUpdateDispatcher r9 = r7.aspectRatioUpdateDispatcher
            float r1 = r7.videoAspectRatio
            r2 = 1
            r9.scheduleUpdate(r1, r3, r2)
            r9 = 1073741824(0x40000000, float:2.0)
            int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r9)
            int r9 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r9)
            super.onMeasure(r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.AspectRatioFrameLayout.onMeasure(int, int):void");
    }

    public final void setAspectRatio(float f) {
        if (this.videoAspectRatio != f) {
            this.videoAspectRatio = f;
            requestLayout();
        }
    }

    public final void setAspectRatioListener(AspectRatioListener aspectRatioListener2) {
        this.aspectRatioListener = aspectRatioListener2;
    }

    public final void setResizeMode(int i) {
        if (this.resizeMode != i) {
            this.resizeMode = i;
            requestLayout();
        }
    }
}
