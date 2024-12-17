package com.google.android.exoplayer2.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import java.util.ArrayList;
import java.util.List;

final class StyledPlayerControlViewLayoutManager {
    private static final long ANIMATION_INTERVAL_MS = 2000;
    private static final long DURATION_FOR_HIDING_ANIMATION_MS = 250;
    private static final long DURATION_FOR_SHOWING_ANIMATION_MS = 250;
    private static final int UX_STATE_ALL_VISIBLE = 0;
    private static final int UX_STATE_ANIMATING_HIDE = 3;
    private static final int UX_STATE_ANIMATING_SHOW = 4;
    private static final int UX_STATE_NONE_VISIBLE = 2;
    private static final int UX_STATE_ONLY_PROGRESS_VISIBLE = 1;
    private boolean animationEnabled;
    /* access modifiers changed from: private */
    public final ViewGroup basicControls;
    private final ViewGroup bottomBar;
    /* access modifiers changed from: private */
    public final ViewGroup centerControls;
    /* access modifiers changed from: private */
    public final View controlsBackground;
    private final ViewGroup extraControls = null;
    /* access modifiers changed from: private */
    public final ViewGroup extraControlsScrollView = null;
    private final AnimatorSet hideAllBarsAnimator;
    private final Runnable hideAllBarsRunnable;
    private final Runnable hideControllerRunnable;
    private final AnimatorSet hideMainBarAnimator;
    private final Runnable hideMainBarRunnable;
    private final AnimatorSet hideProgressBarAnimator;
    private final Runnable hideProgressBarRunnable;
    /* access modifiers changed from: private */
    public boolean isMinimalMode;
    /* access modifiers changed from: private */
    public final ViewGroup minimalControls = null;
    /* access modifiers changed from: private */
    public boolean needToShowBars;
    private final View.OnLayoutChangeListener onLayoutChangeListener;
    private final ValueAnimator overflowHideAnimator;
    private final ValueAnimator overflowShowAnimator;
    private final View overflowShowButton = null;
    private final AnimatorSet showAllBarsAnimator;
    /* access modifiers changed from: private */
    public final Runnable showAllBarsRunnable;
    private final AnimatorSet showMainBarAnimator;
    private final List shownButtons;
    private final StyledPlayerControlView styledPlayerControlView;
    /* access modifiers changed from: private */
    public final View timeBar;
    private final ViewGroup timeView;
    private int uxState;

    public StyledPlayerControlViewLayoutManager(final StyledPlayerControlView styledPlayerControlView2) {
        this.styledPlayerControlView = styledPlayerControlView2;
        this.showAllBarsRunnable = new StyledPlayerControlViewLayoutManager$$Lambda$0(this);
        this.hideAllBarsRunnable = new StyledPlayerControlViewLayoutManager$$Lambda$1(this);
        this.hideProgressBarRunnable = new StyledPlayerControlViewLayoutManager$$Lambda$2(this);
        this.hideMainBarRunnable = new StyledPlayerControlViewLayoutManager$$Lambda$3(this);
        this.hideControllerRunnable = new StyledPlayerControlViewLayoutManager$$Lambda$4(this);
        this.onLayoutChangeListener = new StyledPlayerControlViewLayoutManager$$Lambda$5(this);
        this.animationEnabled = true;
        this.uxState = 0;
        this.shownButtons = new ArrayList();
        this.controlsBackground = styledPlayerControlView2.findViewWithTag(ViewIds.exoControlsBackground);
        this.centerControls = (ViewGroup) styledPlayerControlView2.findViewWithTag(ViewIds.exoCenterControls);
        ViewGroup viewGroup = (ViewGroup) styledPlayerControlView2.findViewWithTag(ViewIds.exoBottomBar);
        this.bottomBar = viewGroup;
        this.timeView = (ViewGroup) styledPlayerControlView2.findViewWithTag(ViewIds.exoTimeView);
        View findViewWithTag = styledPlayerControlView2.findViewWithTag(ViewIds.exoTimeBar);
        this.timeBar = findViewWithTag;
        this.basicControls = (ViewGroup) styledPlayerControlView2.findViewWithTag(ViewIds.basicControls);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.addUpdateListener(new StyledPlayerControlViewLayoutManager$$Lambda$6(this));
        ofFloat.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                if (StyledPlayerControlViewLayoutManager.this.controlsBackground != null) {
                    StyledPlayerControlViewLayoutManager.this.controlsBackground.setVisibility(4);
                }
                if (StyledPlayerControlViewLayoutManager.this.centerControls != null) {
                    StyledPlayerControlViewLayoutManager.this.centerControls.setVisibility(4);
                }
                if (StyledPlayerControlViewLayoutManager.this.minimalControls != null) {
                    StyledPlayerControlViewLayoutManager.this.minimalControls.setVisibility(4);
                }
            }

            public void onAnimationStart(Animator animator) {
                if ((StyledPlayerControlViewLayoutManager.this.timeBar instanceof DefaultTimeBar) && !StyledPlayerControlViewLayoutManager.this.isMinimalMode) {
                    ((DefaultTimeBar) StyledPlayerControlViewLayoutManager.this.timeBar).hideScrubber(250);
                }
            }
        });
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat2.setInterpolator(new LinearInterpolator());
        ofFloat2.addUpdateListener(new StyledPlayerControlViewLayoutManager$$Lambda$7(this));
        ofFloat2.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                int i = 0;
                if (StyledPlayerControlViewLayoutManager.this.controlsBackground != null) {
                    StyledPlayerControlViewLayoutManager.this.controlsBackground.setVisibility(0);
                }
                if (StyledPlayerControlViewLayoutManager.this.centerControls != null) {
                    StyledPlayerControlViewLayoutManager.this.centerControls.setVisibility(0);
                }
                if (StyledPlayerControlViewLayoutManager.this.minimalControls != null) {
                    ViewGroup access$400 = StyledPlayerControlViewLayoutManager.this.minimalControls;
                    if (!StyledPlayerControlViewLayoutManager.this.isMinimalMode) {
                        i = 4;
                    }
                    access$400.setVisibility(i);
                }
                if ((StyledPlayerControlViewLayoutManager.this.timeBar instanceof DefaultTimeBar) && !StyledPlayerControlViewLayoutManager.this.isMinimalMode) {
                    ((DefaultTimeBar) StyledPlayerControlViewLayoutManager.this.timeBar).showScrubber(250);
                }
            }
        });
        float convertToDp = (float) UiHelper.convertToDp(58.0f);
        float convertToDp2 = (float) UiHelper.convertToDp(60.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        this.hideMainBarAnimator = animatorSet;
        animatorSet.setDuration(250);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                StyledPlayerControlViewLayoutManager.this.setUxState(1);
                if (StyledPlayerControlViewLayoutManager.this.needToShowBars) {
                    styledPlayerControlView2.post(StyledPlayerControlViewLayoutManager.this.showAllBarsRunnable);
                    boolean unused = StyledPlayerControlViewLayoutManager.this.needToShowBars = false;
                }
            }

            public void onAnimationStart(Animator animator) {
                StyledPlayerControlViewLayoutManager.this.setUxState(3);
            }
        });
        animatorSet.play(ofFloat).with(ofTranslationY(0.0f, convertToDp, findViewWithTag)).with(ofTranslationY(0.0f, convertToDp, viewGroup));
        AnimatorSet animatorSet2 = new AnimatorSet();
        this.hideProgressBarAnimator = animatorSet2;
        animatorSet2.setDuration(250);
        animatorSet2.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                StyledPlayerControlViewLayoutManager.this.setUxState(2);
                if (StyledPlayerControlViewLayoutManager.this.needToShowBars) {
                    styledPlayerControlView2.post(StyledPlayerControlViewLayoutManager.this.showAllBarsRunnable);
                    boolean unused = StyledPlayerControlViewLayoutManager.this.needToShowBars = false;
                }
            }

            public void onAnimationStart(Animator animator) {
                StyledPlayerControlViewLayoutManager.this.setUxState(3);
            }
        });
        animatorSet2.play(ofTranslationY(convertToDp, convertToDp2, findViewWithTag)).with(ofTranslationY(convertToDp, convertToDp2, viewGroup));
        AnimatorSet animatorSet3 = new AnimatorSet();
        this.hideAllBarsAnimator = animatorSet3;
        animatorSet3.setDuration(250);
        animatorSet3.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                StyledPlayerControlViewLayoutManager.this.setUxState(2);
                if (StyledPlayerControlViewLayoutManager.this.needToShowBars) {
                    styledPlayerControlView2.post(StyledPlayerControlViewLayoutManager.this.showAllBarsRunnable);
                    boolean unused = StyledPlayerControlViewLayoutManager.this.needToShowBars = false;
                }
            }

            public void onAnimationStart(Animator animator) {
                StyledPlayerControlViewLayoutManager.this.setUxState(3);
            }
        });
        animatorSet3.play(ofFloat).with(ofTranslationY(0.0f, convertToDp2, findViewWithTag)).with(ofTranslationY(0.0f, convertToDp2, viewGroup));
        AnimatorSet animatorSet4 = new AnimatorSet();
        this.showMainBarAnimator = animatorSet4;
        animatorSet4.setDuration(250);
        animatorSet4.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                StyledPlayerControlViewLayoutManager.this.setUxState(0);
            }

            public void onAnimationStart(Animator animator) {
                StyledPlayerControlViewLayoutManager.this.setUxState(4);
            }
        });
        animatorSet4.play(ofFloat2).with(ofTranslationY(convertToDp, 0.0f, findViewWithTag)).with(ofTranslationY(convertToDp, 0.0f, viewGroup));
        AnimatorSet animatorSet5 = new AnimatorSet();
        this.showAllBarsAnimator = animatorSet5;
        animatorSet5.setDuration(250);
        animatorSet5.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                StyledPlayerControlViewLayoutManager.this.setUxState(0);
            }

            public void onAnimationStart(Animator animator) {
                StyledPlayerControlViewLayoutManager.this.setUxState(4);
            }
        });
        animatorSet5.play(ofFloat2).with(ofTranslationY(convertToDp2, 0.0f, findViewWithTag)).with(ofTranslationY(convertToDp2, 0.0f, viewGroup));
        ValueAnimator ofFloat3 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.overflowShowAnimator = ofFloat3;
        ofFloat3.setDuration(250);
        ofFloat3.addUpdateListener(new StyledPlayerControlViewLayoutManager$$Lambda$8(this));
        ofFloat3.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                if (StyledPlayerControlViewLayoutManager.this.basicControls != null) {
                    StyledPlayerControlViewLayoutManager.this.basicControls.setVisibility(4);
                }
            }

            public void onAnimationStart(Animator animator) {
                if (StyledPlayerControlViewLayoutManager.this.extraControlsScrollView != null) {
                    StyledPlayerControlViewLayoutManager.this.extraControlsScrollView.setVisibility(0);
                    StyledPlayerControlViewLayoutManager.this.extraControlsScrollView.setTranslationX((float) StyledPlayerControlViewLayoutManager.this.extraControlsScrollView.getWidth());
                    StyledPlayerControlViewLayoutManager.this.extraControlsScrollView.scrollTo(StyledPlayerControlViewLayoutManager.this.extraControlsScrollView.getWidth(), 0);
                }
            }
        });
        ValueAnimator ofFloat4 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        this.overflowHideAnimator = ofFloat4;
        ofFloat4.setDuration(250);
        ofFloat4.addUpdateListener(new StyledPlayerControlViewLayoutManager$$Lambda$9(this));
        ofFloat4.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                if (StyledPlayerControlViewLayoutManager.this.extraControlsScrollView != null) {
                    StyledPlayerControlViewLayoutManager.this.extraControlsScrollView.setVisibility(4);
                }
            }

            public void onAnimationStart(Animator animator) {
                if (StyledPlayerControlViewLayoutManager.this.basicControls != null) {
                    StyledPlayerControlViewLayoutManager.this.basicControls.setVisibility(0);
                }
            }
        });
    }

    private void animateOverflow(float f) {
        ViewGroup viewGroup = this.extraControlsScrollView;
        if (viewGroup != null) {
            this.extraControlsScrollView.setTranslationX((float) ((int) (((float) viewGroup.getWidth()) * (1.0f - f))));
        }
        ViewGroup viewGroup2 = this.timeView;
        if (viewGroup2 != null) {
            viewGroup2.setAlpha(1.0f - f);
        }
        ViewGroup viewGroup3 = this.basicControls;
        if (viewGroup3 != null) {
            viewGroup3.setAlpha(1.0f - f);
        }
    }

    private static int getHeightWithMargins(View view) {
        if (view == null) {
            return 0;
        }
        int height = view.getHeight();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            return height;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        return height + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    private static int getWidthWithMargins(View view) {
        if (view == null) {
            return 0;
        }
        int width = view.getWidth();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            return width;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        return width + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
    }

    /* access modifiers changed from: private */
    /* renamed from: hideAllBars */
    public void bridge$lambda$1$StyledPlayerControlViewLayoutManager() {
        this.hideAllBarsAnimator.start();
    }

    /* access modifiers changed from: private */
    /* renamed from: hideController */
    public void bridge$lambda$4$StyledPlayerControlViewLayoutManager() {
        setUxState(2);
    }

    /* access modifiers changed from: private */
    /* renamed from: hideMainBar */
    public void bridge$lambda$3$StyledPlayerControlViewLayoutManager() {
        this.hideMainBarAnimator.start();
        postDelayedRunnable(this.hideProgressBarRunnable, ANIMATION_INTERVAL_MS);
    }

    /* access modifiers changed from: private */
    /* renamed from: hideProgressBar */
    public void bridge$lambda$2$StyledPlayerControlViewLayoutManager() {
        this.hideProgressBarAnimator.start();
    }

    private static ObjectAnimator ofTranslationY(float f, float f2, View view) {
        return ObjectAnimator.ofFloat(view, "translationY", new float[]{f, f2});
    }

    /* access modifiers changed from: private */
    /* renamed from: onLayoutChange */
    public void bridge$lambda$5$StyledPlayerControlViewLayoutManager(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        boolean useMinimalMode = useMinimalMode();
        if (this.isMinimalMode != useMinimalMode) {
            this.isMinimalMode = useMinimalMode;
            view.post(new StyledPlayerControlViewLayoutManager$$Lambda$10(this));
        }
        boolean z = i3 - i != i7 - i5;
        if (!this.isMinimalMode && z) {
            view.post(new StyledPlayerControlViewLayoutManager$$Lambda$11(this));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onLayoutWidthChanged */
    public void bridge$lambda$7$StyledPlayerControlViewLayoutManager() {
        int i;
        if (this.basicControls != null && this.extraControls != null) {
            int width = (this.styledPlayerControlView.getWidth() - this.styledPlayerControlView.getPaddingLeft()) - this.styledPlayerControlView.getPaddingRight();
            while (true) {
                if (this.extraControls.getChildCount() <= 1) {
                    break;
                }
                int childCount = this.extraControls.getChildCount() - 2;
                View childAt = this.extraControls.getChildAt(childCount);
                this.extraControls.removeViewAt(childCount);
                this.basicControls.addView(childAt, 0);
            }
            View view = this.overflowShowButton;
            if (view != null) {
                view.setVisibility(8);
            }
            int widthWithMargins = getWidthWithMargins(this.timeView);
            int childCount2 = this.basicControls.getChildCount() - 1;
            for (int i2 = 0; i2 < childCount2; i2++) {
                widthWithMargins += getWidthWithMargins(this.basicControls.getChildAt(i2));
            }
            if (widthWithMargins > width) {
                View view2 = this.overflowShowButton;
                if (view2 != null) {
                    view2.setVisibility(0);
                    widthWithMargins += getWidthWithMargins(this.overflowShowButton);
                }
                ArrayList arrayList = new ArrayList();
                for (int i3 = 0; i3 < childCount2; i3++) {
                    View childAt2 = this.basicControls.getChildAt(i3);
                    widthWithMargins -= getWidthWithMargins(childAt2);
                    arrayList.add(childAt2);
                    if (widthWithMargins <= width) {
                        break;
                    }
                }
                if (!arrayList.isEmpty()) {
                    this.basicControls.removeViews(0, arrayList.size());
                    for (i = 0; i < arrayList.size(); i++) {
                        this.extraControls.addView((View) arrayList.get(i), this.extraControls.getChildCount() - 1);
                    }
                    return;
                }
                return;
            }
            ViewGroup viewGroup = this.extraControlsScrollView;
            if (viewGroup != null && viewGroup.getVisibility() == 0 && !this.overflowHideAnimator.isStarted()) {
                this.overflowShowAnimator.cancel();
                this.overflowHideAnimator.start();
            }
        }
    }

    private void postDelayedRunnable(Runnable runnable, long j) {
        if (j >= 0) {
            this.styledPlayerControlView.postDelayed(runnable, j);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARNING: Removed duplicated region for block: B:9:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setUxState(int r4) {
        /*
            r3 = this;
            int r0 = r3.uxState
            r3.uxState = r4
            r1 = 2
            if (r4 != r1) goto L_0x000f
            com.google.android.exoplayer2.ui.StyledPlayerControlView r1 = r3.styledPlayerControlView
            r2 = 8
        L_0x000b:
            r1.setVisibility(r2)
            goto L_0x0015
        L_0x000f:
            if (r0 != r1) goto L_0x0015
            com.google.android.exoplayer2.ui.StyledPlayerControlView r1 = r3.styledPlayerControlView
            r2 = 0
            goto L_0x000b
        L_0x0015:
            if (r0 == r4) goto L_0x001c
            com.google.android.exoplayer2.ui.StyledPlayerControlView r4 = r3.styledPlayerControlView
            r4.notifyOnVisibilityChange()
        L_0x001c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.StyledPlayerControlViewLayoutManager.setUxState(int):void");
    }

    private boolean shouldHideInMinimalMode(View view) {
        String str = (String) view.getTag();
        return str == ViewIds.exoBottomBar || str == ViewIds.previousButton || str == ViewIds.nextButton || str == ViewIds.rewindButton || str == ViewIds.forwardButton;
    }

    /* access modifiers changed from: private */
    /* renamed from: showAllBars */
    public void bridge$lambda$0$StyledPlayerControlViewLayoutManager() {
        AnimatorSet animatorSet;
        if (!this.animationEnabled) {
            setUxState(0);
            resetHideCallbacks();
            return;
        }
        switch (this.uxState) {
            case 1:
                animatorSet = this.showMainBarAnimator;
                break;
            case 2:
                animatorSet = this.showAllBarsAnimator;
                break;
            case 3:
                this.needToShowBars = true;
                break;
            case 4:
                return;
        }
        animatorSet.start();
        resetHideCallbacks();
    }

    /* access modifiers changed from: private */
    /* renamed from: updateLayoutForSizeChange */
    public void bridge$lambda$6$StyledPlayerControlViewLayoutManager() {
        ViewGroup viewGroup = this.minimalControls;
        if (viewGroup != null) {
            viewGroup.setVisibility(this.isMinimalMode ? 0 : 4);
        }
        View view = this.timeBar;
        if (view != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            int convertToDp = UiHelper.convertToDp(52.0f);
            if (this.isMinimalMode) {
                convertToDp = 0;
            }
            marginLayoutParams.bottomMargin = convertToDp;
            this.timeBar.setLayoutParams(marginLayoutParams);
            View view2 = this.timeBar;
            if (view2 instanceof DefaultTimeBar) {
                DefaultTimeBar defaultTimeBar = (DefaultTimeBar) view2;
                if (this.isMinimalMode) {
                    defaultTimeBar.hideScrubber(true);
                } else {
                    int i = this.uxState;
                    if (i == 1) {
                        defaultTimeBar.hideScrubber(false);
                    } else if (i != 3) {
                        defaultTimeBar.showScrubber();
                    }
                }
            }
        }
        for (View view3 : this.shownButtons) {
            view3.setVisibility((!this.isMinimalMode || !shouldHideInMinimalMode(view3)) ? 0 : 4);
        }
    }

    private boolean useMinimalMode() {
        int width = (this.styledPlayerControlView.getWidth() - this.styledPlayerControlView.getPaddingLeft()) - this.styledPlayerControlView.getPaddingRight();
        int height = (this.styledPlayerControlView.getHeight() - this.styledPlayerControlView.getPaddingBottom()) - this.styledPlayerControlView.getPaddingTop();
        int widthWithMargins = getWidthWithMargins(this.centerControls);
        ViewGroup viewGroup = this.centerControls;
        int paddingLeft = widthWithMargins - (viewGroup != null ? viewGroup.getPaddingLeft() + this.centerControls.getPaddingRight() : 0);
        int heightWithMargins = getHeightWithMargins(this.centerControls);
        ViewGroup viewGroup2 = this.centerControls;
        return width <= Math.max(paddingLeft, getWidthWithMargins(this.timeView) + getWidthWithMargins(this.overflowShowButton)) || height <= (heightWithMargins - (viewGroup2 != null ? viewGroup2.getPaddingTop() + this.centerControls.getPaddingBottom() : 0)) + (getHeightWithMargins(this.bottomBar) * 2);
    }

    public final boolean getShowButton(View view) {
        return view != null && this.shownButtons.contains(view);
    }

    public final void hide() {
        int i = this.uxState;
        if (i != 3 && i != 2) {
            removeHideCallbacks();
            if (!this.animationEnabled) {
                bridge$lambda$4$StyledPlayerControlViewLayoutManager();
            } else if (this.uxState == 1) {
                bridge$lambda$2$StyledPlayerControlViewLayoutManager();
            } else {
                bridge$lambda$1$StyledPlayerControlViewLayoutManager();
            }
        }
    }

    public final void hideImmediately() {
        int i = this.uxState;
        if (i != 3 && i != 2) {
            removeHideCallbacks();
            bridge$lambda$4$StyledPlayerControlViewLayoutManager();
        }
    }

    public final boolean isAnimationEnabled() {
        return this.animationEnabled;
    }

    public final boolean isFullyVisible() {
        return this.uxState == 0 && this.styledPlayerControlView.isVisible();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void lambda$new$0$StyledPlayerControlViewLayoutManager(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        View view = this.controlsBackground;
        if (view != null) {
            view.setAlpha(floatValue);
        }
        ViewGroup viewGroup = this.centerControls;
        if (viewGroup != null) {
            viewGroup.setAlpha(floatValue);
        }
        ViewGroup viewGroup2 = this.minimalControls;
        if (viewGroup2 != null) {
            viewGroup2.setAlpha(floatValue);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void lambda$new$1$StyledPlayerControlViewLayoutManager(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        View view = this.controlsBackground;
        if (view != null) {
            view.setAlpha(floatValue);
        }
        ViewGroup viewGroup = this.centerControls;
        if (viewGroup != null) {
            viewGroup.setAlpha(floatValue);
        }
        ViewGroup viewGroup2 = this.minimalControls;
        if (viewGroup2 != null) {
            viewGroup2.setAlpha(floatValue);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void lambda$new$2$StyledPlayerControlViewLayoutManager(ValueAnimator valueAnimator) {
        animateOverflow(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void lambda$new$3$StyledPlayerControlViewLayoutManager(ValueAnimator valueAnimator) {
        animateOverflow(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    public final void onAttachedToWindow() {
        this.styledPlayerControlView.addOnLayoutChangeListener(this.onLayoutChangeListener);
    }

    public final void onDetachedFromWindow() {
        this.styledPlayerControlView.removeOnLayoutChangeListener(this.onLayoutChangeListener);
    }

    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View view = this.controlsBackground;
        if (view != null) {
            view.layout(0, 0, i3 - i, i4 - i2);
        }
    }

    public final void removeHideCallbacks() {
        this.styledPlayerControlView.removeCallbacks(this.hideControllerRunnable);
        this.styledPlayerControlView.removeCallbacks(this.hideAllBarsRunnable);
        this.styledPlayerControlView.removeCallbacks(this.hideMainBarRunnable);
        this.styledPlayerControlView.removeCallbacks(this.hideProgressBarRunnable);
    }

    public final void resetHideCallbacks() {
        if (this.uxState != 3) {
            removeHideCallbacks();
            int showTimeoutMs = this.styledPlayerControlView.getShowTimeoutMs();
            if (showTimeoutMs <= 0) {
                return;
            }
            if (!this.animationEnabled) {
                postDelayedRunnable(this.hideControllerRunnable, (long) showTimeoutMs);
            } else if (this.uxState == 1) {
                postDelayedRunnable(this.hideProgressBarRunnable, ANIMATION_INTERVAL_MS);
            } else {
                postDelayedRunnable(this.hideMainBarRunnable, (long) showTimeoutMs);
            }
        }
    }

    public final void setAnimationEnabled(boolean z) {
        this.animationEnabled = z;
    }

    public final void setShowButton(View view, boolean z) {
        if (view != null) {
            if (!z) {
                view.setVisibility(8);
                this.shownButtons.remove(view);
                return;
            }
            view.setVisibility((!this.isMinimalMode || !shouldHideInMinimalMode(view)) ? 0 : 4);
            this.shownButtons.add(view);
        }
    }

    public final void show() {
        if (!this.styledPlayerControlView.isVisible()) {
            this.styledPlayerControlView.setVisibility(0);
            this.styledPlayerControlView.updateAll();
            this.styledPlayerControlView.requestPlayPauseFocus();
        }
        bridge$lambda$0$StyledPlayerControlViewLayoutManager();
    }
}
