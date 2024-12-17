package com.google.android.exoplayer2.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.location.LocationRequestCompat;
import com.google.android.exoplayer2.ui.TimeBar;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.Collections;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArraySet;

public class DefaultTimeBar extends View implements TimeBar {
    private static final String ACCESSIBILITY_CLASS_NAME = "android.widget.SeekBar";
    public static final int BAR_GRAVITY_BOTTOM = 1;
    public static final int BAR_GRAVITY_CENTER = 0;
    public static final int DEFAULT_AD_MARKER_COLOR = -1291845888;
    public static final int DEFAULT_AD_MARKER_WIDTH_DP = 4;
    public static final int DEFAULT_BAR_HEIGHT_DP = 4;
    public static final int DEFAULT_BUFFERED_COLOR = -855638017;
    private static final int DEFAULT_INCREMENT_COUNT = 20;
    public static final int DEFAULT_PLAYED_AD_MARKER_COLOR = 872414976;
    public static final int DEFAULT_PLAYED_COLOR = -1;
    public static final int DEFAULT_SCRUBBER_COLOR = -1;
    public static final int DEFAULT_SCRUBBER_DISABLED_SIZE_DP = 0;
    public static final int DEFAULT_SCRUBBER_DRAGGED_SIZE_DP = 16;
    public static final int DEFAULT_SCRUBBER_ENABLED_SIZE_DP = 12;
    public static final int DEFAULT_TOUCH_TARGET_HEIGHT_DP = 26;
    public static final int DEFAULT_UNPLAYED_COLOR = 872415231;
    private static final int FINE_SCRUB_RATIO = 3;
    private static final int FINE_SCRUB_Y_THRESHOLD_DP = -50;
    private static final float HIDDEN_SCRUBBER_SCALE = 0.0f;
    private static final float SHOWN_SCRUBBER_SCALE = 1.0f;
    private static final long STOP_SCRUBBING_TIMEOUT_MS = 1000;
    private int adGroupCount;
    private long[] adGroupTimesMs;
    private final Paint adMarkerPaint;
    private final int adMarkerWidth;
    private final int barGravity;
    private final int barHeight;
    private final Rect bufferedBar;
    private final Paint bufferedPaint;
    private long bufferedPosition;
    private final float density;
    private long duration;
    private final int fineScrubYThreshold;
    private final StringBuilder formatBuilder;
    private final Formatter formatter;
    private int keyCountIncrement;
    private long keyTimeIncrement;
    private int lastCoarseScrubXPosition;
    private Rect lastExclusionRectangle;
    private final CopyOnWriteArraySet listeners;
    private boolean[] playedAdGroups;
    private final Paint playedAdMarkerPaint;
    private final Paint playedPaint;
    private long position;
    private final Rect progressBar;
    private long scrubPosition;
    private final Rect scrubberBar;
    private final int scrubberDisabledSize;
    private final int scrubberDraggedSize;
    private final Drawable scrubberDrawable;
    private final int scrubberEnabledSize;
    private final int scrubberPadding;
    private boolean scrubberPaddingDisabled;
    private final Paint scrubberPaint;
    private float scrubberScale;
    private final ValueAnimator scrubberScalingAnimator;
    private boolean scrubbing;
    private final Rect seekBounds;
    private final Runnable stopScrubbingRunnable;
    private final Point touchPosition;
    private final int touchTargetHeight;
    private final Paint unplayedPaint;

    public DefaultTimeBar(Context context) {
        this(context, TimeBarAttributes.createDefault());
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DefaultTimeBar(Context context, TimeBarAttributes timeBarAttributes) {
        super(context, (AttributeSet) null, 0);
        int i;
        int i2;
        this.seekBounds = new Rect();
        this.progressBar = new Rect();
        this.bufferedBar = new Rect();
        this.scrubberBar = new Rect();
        Paint paint = new Paint();
        this.playedPaint = paint;
        Paint paint2 = new Paint();
        this.bufferedPaint = paint2;
        Paint paint3 = new Paint();
        this.unplayedPaint = paint3;
        Paint paint4 = new Paint();
        this.adMarkerPaint = paint4;
        Paint paint5 = new Paint();
        this.playedAdMarkerPaint = paint5;
        Paint paint6 = new Paint();
        this.scrubberPaint = paint6;
        paint6.setAntiAlias(true);
        this.listeners = new CopyOnWriteArraySet();
        this.touchPosition = new Point();
        float f = context.getResources().getDisplayMetrics().density;
        this.density = f;
        this.fineScrubYThreshold = dpToPx(f, FINE_SCRUB_Y_THRESHOLD_DP);
        Drawable scrubberDrawable2 = timeBarAttributes.getScrubberDrawable();
        this.scrubberDrawable = scrubberDrawable2;
        if (scrubberDrawable2 != null) {
            setDrawableLayoutDirection(scrubberDrawable2);
        }
        this.barHeight = dpToPx(f, timeBarAttributes.getBarHeight());
        this.touchTargetHeight = dpToPx(f, timeBarAttributes.getTouchTargetHeight());
        this.adMarkerWidth = dpToPx(f, timeBarAttributes.getAdMarkerWidth());
        int dpToPx = dpToPx(f, timeBarAttributes.getScrubberEnabledSize());
        this.scrubberEnabledSize = dpToPx;
        int dpToPx2 = dpToPx(f, timeBarAttributes.getScrubberDisabledSize());
        this.scrubberDisabledSize = dpToPx2;
        int dpToPx3 = dpToPx(f, timeBarAttributes.getScrubberDraggedSize());
        this.scrubberDraggedSize = dpToPx3;
        this.barGravity = timeBarAttributes.getBarGravity();
        int playedColor = timeBarAttributes.getPlayedColor();
        int scrubberColor = timeBarAttributes.getScrubberColor();
        int bufferedColor = timeBarAttributes.getBufferedColor();
        int unplayedColor = timeBarAttributes.getUnplayedColor();
        int adMarkerColor = timeBarAttributes.getAdMarkerColor();
        int i3 = dpToPx2;
        int playedAdMarkerColor = timeBarAttributes.getPlayedAdMarkerColor();
        paint.setColor(playedColor);
        paint6.setColor(scrubberColor);
        paint2.setColor(bufferedColor);
        paint3.setColor(unplayedColor);
        paint4.setColor(adMarkerColor);
        paint5.setColor(playedAdMarkerColor);
        StringBuilder sb = new StringBuilder();
        this.formatBuilder = sb;
        this.formatter = new Formatter(sb, Locale.getDefault());
        this.stopScrubbingRunnable = new DefaultTimeBar$$Lambda$0(this);
        if (scrubberDrawable2 != null) {
            i2 = scrubberDrawable2.getMinimumWidth();
            i = 1;
        } else {
            i = 1;
            i2 = Math.max(i3, Math.max(dpToPx, dpToPx3));
        }
        this.scrubberPadding = (i2 + i) / 2;
        this.scrubberScale = 1.0f;
        ValueAnimator valueAnimator = new ValueAnimator();
        this.scrubberScalingAnimator = valueAnimator;
        valueAnimator.addUpdateListener(new DefaultTimeBar$$Lambda$1(this));
        this.duration = -9223372036854775807L;
        this.keyTimeIncrement = -9223372036854775807L;
        this.keyCountIncrement = 20;
        setFocusable(true);
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
    }

    private static int dpToPx(float f, int i) {
        return (int) ((((float) i) * f) + 0.5f);
    }

    private void drawPlayhead(Canvas canvas) {
        if (this.duration > 0) {
            int a = Util.a(this.scrubberBar.right, this.scrubberBar.left, this.progressBar.right);
            int centerY = this.scrubberBar.centerY();
            Drawable drawable = this.scrubberDrawable;
            if (drawable == null) {
                canvas.drawCircle((float) a, (float) centerY, (float) ((int) ((((float) ((this.scrubbing || isFocused()) ? this.scrubberDraggedSize : isEnabled() ? this.scrubberEnabledSize : this.scrubberDisabledSize)) * this.scrubberScale) / 2.0f)), this.scrubberPaint);
                return;
            }
            int intrinsicWidth = ((int) (((float) drawable.getIntrinsicWidth()) * this.scrubberScale)) / 2;
            int intrinsicHeight = ((int) (((float) this.scrubberDrawable.getIntrinsicHeight()) * this.scrubberScale)) / 2;
            this.scrubberDrawable.setBounds(a - intrinsicWidth, centerY - intrinsicHeight, a + intrinsicWidth, centerY + intrinsicHeight);
            this.scrubberDrawable.draw(canvas);
        }
    }

    private void drawTimeBar(Canvas canvas) {
        int height = this.progressBar.height();
        int centerY = this.progressBar.centerY() - (height / 2);
        int i = height + centerY;
        if (this.duration <= 0) {
            canvas.drawRect((float) this.progressBar.left, (float) centerY, (float) this.progressBar.right, (float) i, this.unplayedPaint);
            return;
        }
        int i2 = this.bufferedBar.left;
        int i3 = this.bufferedBar.right;
        int max = Math.max(Math.max(this.progressBar.left, i3), this.scrubberBar.right);
        if (max < this.progressBar.right) {
            canvas.drawRect((float) max, (float) centerY, (float) this.progressBar.right, (float) i, this.unplayedPaint);
        }
        int max2 = Math.max(i2, this.scrubberBar.right);
        if (i3 > max2) {
            canvas.drawRect((float) max2, (float) centerY, (float) i3, (float) i, this.bufferedPaint);
        }
        if (this.scrubberBar.width() > 0) {
            canvas.drawRect((float) this.scrubberBar.left, (float) centerY, (float) this.scrubberBar.right, (float) i, this.playedPaint);
        }
        if (this.adGroupCount != 0) {
            long[] jArr = (long[]) Assertions.b((Object) this.adGroupTimesMs);
            boolean[] zArr = (boolean[]) Assertions.b((Object) this.playedAdGroups);
            int i4 = this.adMarkerWidth / 2;
            for (int i5 = 0; i5 < this.adGroupCount; i5++) {
                int min = this.progressBar.left + Math.min(this.progressBar.width() - this.adMarkerWidth, Math.max(0, ((int) ((((long) this.progressBar.width()) * Util.a(jArr[i5], 0, this.duration)) / this.duration)) - i4));
                canvas.drawRect((float) min, (float) centerY, (float) (min + this.adMarkerWidth), (float) i, zArr[i5] ? this.playedAdMarkerPaint : this.adMarkerPaint);
            }
        }
    }

    private long getPositionIncrement() {
        long j = this.keyTimeIncrement;
        if (j != -9223372036854775807L) {
            return j;
        }
        long j2 = this.duration;
        if (j2 == -9223372036854775807L) {
            return 0;
        }
        return j2 / ((long) this.keyCountIncrement);
    }

    private String getProgressText() {
        return Util.a(this.formatBuilder, this.formatter, this.position);
    }

    private long getScrubberPosition() {
        if (this.progressBar.width() <= 0 || this.duration == -9223372036854775807L) {
            return 0;
        }
        return (((long) this.scrubberBar.width()) * this.duration) / ((long) this.progressBar.width());
    }

    private boolean isInSeekBar(float f, float f2) {
        return this.seekBounds.contains((int) f, (int) f2);
    }

    private void positionScrubber(float f) {
        this.scrubberBar.right = Util.a((int) f, this.progressBar.left, this.progressBar.right);
    }

    private static int pxToDp(float f, int i) {
        return (int) (((float) i) / f);
    }

    private Point resolveRelativeTouchPosition(MotionEvent motionEvent) {
        this.touchPosition.set((int) motionEvent.getX(), (int) motionEvent.getY());
        return this.touchPosition;
    }

    private boolean scrubIncrementally(long j) {
        long j2 = this.duration;
        if (j2 <= 0) {
            return false;
        }
        long j3 = this.scrubbing ? this.scrubPosition : this.position;
        long a = Util.a(j3 + j, 0, j2);
        if (a == j3) {
            return false;
        }
        if (!this.scrubbing) {
            startScrubbing(a);
        } else {
            updateScrubbing(a);
        }
        update();
        return true;
    }

    private boolean setDrawableLayoutDirection(Drawable drawable) {
        return Util.a >= 23 && setDrawableLayoutDirection(drawable, getLayoutDirection());
    }

    private static boolean setDrawableLayoutDirection(Drawable drawable, int i) {
        return Util.a >= 23 && drawable.setLayoutDirection(i);
    }

    private void setSystemGestureExclusionRectsV29(int i, int i2) {
        Rect rect = this.lastExclusionRectangle;
        if (rect == null || rect.width() != i || this.lastExclusionRectangle.height() != i2) {
            Rect rect2 = new Rect(0, 0, i, i2);
            this.lastExclusionRectangle = rect2;
            setSystemGestureExclusionRects(Collections.singletonList(rect2));
        }
    }

    private void startScrubbing(long j) {
        this.scrubPosition = j;
        this.scrubbing = true;
        setPressed(true);
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
        Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            ((TimeBar.OnScrubListener) it.next()).onScrubStart(this, j);
        }
    }

    private void stopScrubbing(boolean z) {
        removeCallbacks(this.stopScrubbingRunnable);
        this.scrubbing = false;
        setPressed(false);
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
        invalidate();
        Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            ((TimeBar.OnScrubListener) it.next()).onScrubStop(this, this.scrubPosition, z);
        }
    }

    private void update() {
        this.bufferedBar.set(this.progressBar);
        this.scrubberBar.set(this.progressBar);
        long j = this.scrubbing ? this.scrubPosition : this.position;
        if (this.duration > 0) {
            this.bufferedBar.right = Math.min(this.progressBar.left + ((int) ((((long) this.progressBar.width()) * this.bufferedPosition) / this.duration)), this.progressBar.right);
            this.scrubberBar.right = Math.min(this.progressBar.left + ((int) ((((long) this.progressBar.width()) * j) / this.duration)), this.progressBar.right);
        } else {
            this.bufferedBar.right = this.progressBar.left;
            this.scrubberBar.right = this.progressBar.left;
        }
        invalidate(this.seekBounds);
    }

    private void updateDrawableState() {
        Drawable drawable = this.scrubberDrawable;
        if (drawable != null && drawable.isStateful() && this.scrubberDrawable.setState(getDrawableState())) {
            invalidate();
        }
    }

    private void updateScrubbing(long j) {
        if (this.scrubPosition != j) {
            this.scrubPosition = j;
            Iterator it = this.listeners.iterator();
            while (it.hasNext()) {
                ((TimeBar.OnScrubListener) it.next()).onScrubMove(this, j);
            }
        }
    }

    public void addListener(TimeBar.OnScrubListener onScrubListener) {
        Assertions.b((Object) onScrubListener);
        this.listeners.add(onScrubListener);
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        updateDrawableState();
    }

    public long getPreferredUpdateDelay() {
        int pxToDp = pxToDp(this.density, this.progressBar.width());
        if (pxToDp == 0) {
            return LocationRequestCompat.PASSIVE_INTERVAL;
        }
        long j = this.duration;
        return (j == 0 || j == -9223372036854775807L) ? LocationRequestCompat.PASSIVE_INTERVAL : j / ((long) pxToDp);
    }

    public void hideScrubber(long j) {
        if (this.scrubberScalingAnimator.isStarted()) {
            this.scrubberScalingAnimator.cancel();
        }
        this.scrubberScalingAnimator.setFloatValues(new float[]{this.scrubberScale, 0.0f});
        this.scrubberScalingAnimator.setDuration(j);
        this.scrubberScalingAnimator.start();
    }

    public void hideScrubber(boolean z) {
        if (this.scrubberScalingAnimator.isStarted()) {
            this.scrubberScalingAnimator.cancel();
        }
        this.scrubberPaddingDisabled = z;
        this.scrubberScale = 0.0f;
        invalidate(this.seekBounds);
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.scrubberDrawable;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void lambda$new$0$DefaultTimeBar() {
        stopScrubbing(false);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void lambda$new$1$DefaultTimeBar(ValueAnimator valueAnimator) {
        this.scrubberScale = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        invalidate(this.seekBounds);
    }

    public void onDraw(Canvas canvas) {
        canvas.save();
        drawTimeBar(canvas);
        drawPlayhead(canvas);
        canvas.restore();
    }

    /* access modifiers changed from: protected */
    public void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        if (this.scrubbing && !z) {
            stopScrubbing(false);
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (accessibilityEvent.getEventType() == 4) {
            accessibilityEvent.getText().add(getProgressText());
        }
        accessibilityEvent.setClassName(ACCESSIBILITY_CLASS_NAME);
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(ACCESSIBILITY_CLASS_NAME);
        accessibilityNodeInfo.setContentDescription(getProgressText());
        if (this.duration > 0) {
            if (Util.a >= 21) {
                accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD);
                accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD);
                return;
            }
            accessibilityNodeInfo.addAction(4096);
            accessibilityNodeInfo.addAction(8192);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (isEnabled()) {
            long positionIncrement = getPositionIncrement();
            switch (i) {
                case 21:
                    positionIncrement = -positionIncrement;
                    break;
                case 22:
                    break;
                case 23:
                case 66:
                    if (this.scrubbing) {
                        stopScrubbing(false);
                        return true;
                    }
                    break;
            }
            if (scrubIncrementally(positionIncrement)) {
                removeCallbacks(this.stopScrubbingRunnable);
                postDelayed(this.stopScrubbingRunnable, 1000);
                return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7 = i3 - i;
        int i8 = i4 - i2;
        int paddingLeft = getPaddingLeft();
        int paddingRight = i7 - getPaddingRight();
        int i9 = this.scrubberPaddingDisabled ? 0 : this.scrubberPadding;
        if (this.barGravity == 1) {
            i6 = (i8 - getPaddingBottom()) - this.touchTargetHeight;
            int i10 = this.barHeight;
            i5 = ((i8 - getPaddingBottom()) - i10) - Math.max(i9 - (i10 / 2), 0);
        } else {
            i6 = (i8 - this.touchTargetHeight) / 2;
            i5 = (i8 - this.barHeight) / 2;
        }
        this.seekBounds.set(paddingLeft, i6, paddingRight, this.touchTargetHeight + i6);
        this.progressBar.set(this.seekBounds.left + i9, i5, this.seekBounds.right - i9, this.barHeight + i5);
        if (Util.a >= 29) {
            setSystemGestureExclusionRectsV29(i7, i8);
        }
        update();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == 0) {
            size = this.touchTargetHeight;
        } else if (mode != 1073741824) {
            size = Math.min(this.touchTargetHeight, size);
        }
        setMeasuredDimension(View.MeasureSpec.getSize(i), size);
        updateDrawableState();
    }

    public void onRtlPropertiesChanged(int i) {
        Drawable drawable = this.scrubberDrawable;
        if (drawable != null && setDrawableLayoutDirection(drawable, i)) {
            invalidate();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003e, code lost:
        update();
        invalidate();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0044, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            boolean r0 = r6.isEnabled()
            r1 = 0
            if (r0 == 0) goto L_0x0067
            long r2 = r6.duration
            r4 = 0
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 > 0) goto L_0x0010
            goto L_0x0067
        L_0x0010:
            android.graphics.Point r0 = r6.resolveRelativeTouchPosition(r7)
            int r2 = r0.x
            int r0 = r0.y
            int r3 = r7.getAction()
            r4 = 3
            r5 = 1
            switch(r3) {
                case 0: goto L_0x0054;
                case 1: goto L_0x0045;
                case 2: goto L_0x0022;
                case 3: goto L_0x0045;
                default: goto L_0x0021;
            }
        L_0x0021:
            goto L_0x0067
        L_0x0022:
            boolean r7 = r6.scrubbing
            if (r7 == 0) goto L_0x0067
            int r7 = r6.fineScrubYThreshold
            if (r0 >= r7) goto L_0x0031
            int r7 = r6.lastCoarseScrubXPosition
            int r2 = r2 - r7
            int r2 = r2 / r4
            int r7 = r7 + r2
            float r7 = (float) r7
            goto L_0x0034
        L_0x0031:
            r6.lastCoarseScrubXPosition = r2
            float r7 = (float) r2
        L_0x0034:
            r6.positionScrubber(r7)
            long r0 = r6.getScrubberPosition()
            r6.updateScrubbing(r0)
        L_0x003e:
            r6.update()
            r6.invalidate()
            return r5
        L_0x0045:
            boolean r0 = r6.scrubbing
            if (r0 == 0) goto L_0x0067
            int r7 = r7.getAction()
            if (r7 != r4) goto L_0x0050
            r1 = 1
        L_0x0050:
            r6.stopScrubbing(r1)
            return r5
        L_0x0054:
            float r7 = (float) r2
            float r0 = (float) r0
            boolean r0 = r6.isInSeekBar(r7, r0)
            if (r0 == 0) goto L_0x0067
            r6.positionScrubber(r7)
            long r0 = r6.getScrubberPosition()
            r6.startScrubbing(r0)
            goto L_0x003e
        L_0x0067:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.DefaultTimeBar.onTouchEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0031, code lost:
        if (scrubIncrementally(getPositionIncrement()) != false) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001f, code lost:
        if (scrubIncrementally(-getPositionIncrement()) != false) goto L_0x0021;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean performAccessibilityAction(int r7, android.os.Bundle r8) {
        /*
            r6 = this;
            boolean r8 = super.performAccessibilityAction(r7, r8)
            r0 = 1
            if (r8 == 0) goto L_0x0008
            return r0
        L_0x0008:
            long r1 = r6.duration
            r3 = 0
            r8 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 > 0) goto L_0x0012
            return r8
        L_0x0012:
            r1 = 8192(0x2000, float:1.14794E-41)
            if (r7 != r1) goto L_0x0025
            long r1 = r6.getPositionIncrement()
            long r1 = -r1
            boolean r7 = r6.scrubIncrementally(r1)
            if (r7 == 0) goto L_0x0034
        L_0x0021:
            r6.stopScrubbing(r8)
            goto L_0x0034
        L_0x0025:
            r1 = 4096(0x1000, float:5.74E-42)
            if (r7 != r1) goto L_0x0039
            long r1 = r6.getPositionIncrement()
            boolean r7 = r6.scrubIncrementally(r1)
            if (r7 == 0) goto L_0x0034
            goto L_0x0021
        L_0x0034:
            r7 = 4
            r6.sendAccessibilityEvent(r7)
            return r0
        L_0x0039:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.DefaultTimeBar.performAccessibilityAction(int, android.os.Bundle):boolean");
    }

    public void removeListener(TimeBar.OnScrubListener onScrubListener) {
        this.listeners.remove(onScrubListener);
    }

    public void setAdGroupTimesMs(long[] jArr, boolean[] zArr, int i) {
        Assertions.a(i == 0 || !(jArr == null || zArr == null));
        this.adGroupCount = i;
        this.adGroupTimesMs = jArr;
        this.playedAdGroups = zArr;
        update();
    }

    public void setAdMarkerColor(int i) {
        this.adMarkerPaint.setColor(i);
        invalidate(this.seekBounds);
    }

    public void setBufferedColor(int i) {
        this.bufferedPaint.setColor(i);
        invalidate(this.seekBounds);
    }

    public void setBufferedPosition(long j) {
        this.bufferedPosition = j;
        update();
    }

    public void setDuration(long j) {
        this.duration = j;
        if (this.scrubbing && j == -9223372036854775807L) {
            stopScrubbing(true);
        }
        update();
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (this.scrubbing && !z) {
            stopScrubbing(true);
        }
    }

    public void setKeyCountIncrement(int i) {
        Assertions.a(i > 0);
        this.keyCountIncrement = i;
        this.keyTimeIncrement = -9223372036854775807L;
    }

    public void setKeyTimeIncrement(long j) {
        Assertions.a(j > 0);
        this.keyCountIncrement = -1;
        this.keyTimeIncrement = j;
    }

    public void setPlayedAdMarkerColor(int i) {
        this.playedAdMarkerPaint.setColor(i);
        invalidate(this.seekBounds);
    }

    public void setPlayedColor(int i) {
        this.playedPaint.setColor(i);
        invalidate(this.seekBounds);
    }

    public void setPosition(long j) {
        this.position = j;
        setContentDescription(getProgressText());
        update();
    }

    public void setScrubberColor(int i) {
        this.scrubberPaint.setColor(i);
        invalidate(this.seekBounds);
    }

    public void setUnplayedColor(int i) {
        this.unplayedPaint.setColor(i);
        invalidate(this.seekBounds);
    }

    public void showScrubber() {
        if (this.scrubberScalingAnimator.isStarted()) {
            this.scrubberScalingAnimator.cancel();
        }
        this.scrubberPaddingDisabled = false;
        this.scrubberScale = 1.0f;
        invalidate(this.seekBounds);
    }

    public void showScrubber(long j) {
        if (this.scrubberScalingAnimator.isStarted()) {
            this.scrubberScalingAnimator.cancel();
        }
        this.scrubberPaddingDisabled = false;
        this.scrubberScalingAnimator.setFloatValues(new float[]{this.scrubberScale, 1.0f});
        this.scrubberScalingAnimator.setDuration(j);
        this.scrubberScalingAnimator.start();
    }
}
