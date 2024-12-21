package com.dreamers.exoplayerui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.dreamers.exoplayerui.repack.a;
import com.dreamers.exoplayerui.repack.b;
import com.dreamers.exoplayerui.repack.c;
import com.dreamers.exoplayerui.repack.d;
import com.dreamers.exoplayerui.repack.e;
import com.dreamers.exoplayerui.repack.f;
import com.dreamers.exoplayerui.repack.g;
import com.dreamers.exoplayerui.repack.h;
import com.dreamers.exoplayerui.repack.i;
import com.dreamers.exoplayerui.repack.j;
import com.dreamers.exoplayerui.repack.k;
import com.dreamers.exoplayerui.repack.l;
import com.dreamers.exoplayerui.repack.n;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.CaptionStyleCompat;
import com.google.android.exoplayer2.ui.DefaultTimeBar;
import com.google.android.exoplayer2.ui.PlayerAttributes;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.android.exoplayer2.ui.SubtitleView;
import com.google.android.exoplayer2.ui.TimeBarAttributes;
import com.google.android.exoplayer2.ui.TrackNameProvider;
import com.google.android.exoplayer2.ui.TrackSelectionDialogBuilder;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HVArrangement;
import com.google.appinventor.components.runtime.OnPauseListener;
import com.google.appinventor.components.runtime.OnResumeListener;
import com.google.appinventor.components.runtime.ReplForm;

public final class ExoplayerUi extends AndroidNonvisibleComponent implements Component, OnPauseListener, OnResumeListener {
    public static final h Companion = new h((byte) 0);
    public static final String EDGE_TYPE_DEPRESSED = "Depressed";
    public static final String EDGE_TYPE_DROP_SHADOW = "Drop Shadow";
    public static final String EDGE_TYPE_NONE = "No Edge";
    public static final String EDGE_TYPE_OUTLINE = "Outline";
    public static final String EDGE_TYPE_RAISED = "Raised";
    public static final String LOG_TAG = "ExoplayerUI";
    public static final String REPEAT_MODE_All = "All";
    public static final String REPEAT_MODE_OFF = "Off";
    public static final String REPEAT_MODE_ONE = "One";
    public static final String REPEAT_MODE_ONE_ALL = "One & All";
    public static final String RESIZE_MODE_FILL = "Resize Mode Fill";
    public static final String RESIZE_MODE_FIT = "Resize Mode Fit";
    public static final String RESIZE_MODE_FIXED_HEIGHT = "Resize Mode Fixed Height";
    public static final String RESIZE_MODE_FIXED_WIDTH = "Resize Mode Fixed Width";
    public static final String RESIZE_MODE_ZOOM = "Resize Mode Zoom";
    public static final String SHOW_BUFFERING_ALWAYS = "Always";
    public static final String SHOW_BUFFERING_NEVER = "Never";
    public static final String SHOW_BUFFERING_WHEN_PLAYING = "When Playing";
    public static final String SURFACE_TYPE_NONE = "None";
    public static final String SURFACE_TYPE_SURFACE_VIEW = "Surface View";
    public static final String SURFACE_TYPE_TEXTURE_VIEW = "Texture View";
    public static final String TEXT_SIE_TYPE_ABSOLUTE = "Absolute Size";
    public static final String TEXT_SIE_TYPE_FRACTION = "Fractional Size";
    public static final String VIEW_TYPE_CANVAS = "Canvas";
    public static final String VIEW_TYPE_WEB = "Web";
    private float A = 14.0f;
    private boolean B;
    private int C;
    private int D = 1;
    private float E = 0.08f;
    private int F = -1;
    private int G = -16777216;
    private int H;
    private int I = -1;
    private int J;
    private Typeface K;
    private int L = DefaultTimeBar.DEFAULT_UNPLAYED_COLOR;
    private int M = -1;
    private int N = -1;
    private int O = DefaultTimeBar.DEFAULT_BUFFERED_COLOR;
    private int P = 4;
    private int Q = 12;
    private int R;
    private int S = 16;
    private int T = 1;
    private final Context a;
    private PlayerView b;
    private StyledPlayerView c;
    private final boolean d = (this.form instanceof ReplForm);
    private SimpleExoPlayer e;
    private String f = REPEAT_MODE_OFF;
    private String g = SHOW_BUFFERING_WHEN_PLAYING;
    private int h = 5000;
    private String i = RESIZE_MODE_FIT;
    private boolean j;
    private boolean k = true;
    private boolean l = true;
    private boolean m = true;
    private boolean n = true;
    private boolean o = true;
    private boolean p = true;
    private boolean q = true;
    private boolean r = true;
    private boolean s = true;
    private boolean t = true;
    private boolean u = true;
    private boolean v = true;
    private int w = 15000;
    private int x = 5000;
    private i y;
    private float z = 0.0533f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExoplayerUi(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        n.b(componentContainer, "container");
        this.form.registerForOnPause(this);
        this.form.registerForOnResume(this);
        Activity $context = componentContainer.$context();
        n.a((Object) $context, "container.`$context`()");
        this.a = $context;
    }

    private final SubtitleView a() {
        i iVar = this.y;
        switch (iVar == null ? -1 : j.a[iVar.ordinal()]) {
            case 1:
                PlayerView playerView = this.b;
                if (playerView != null) {
                    return playerView.getSubtitleView();
                }
                return null;
            case 2:
                StyledPlayerView styledPlayerView = this.c;
                if (styledPlayerView != null) {
                    return styledPlayerView.getSubtitleView();
                }
                return null;
            default:
                return null;
        }
    }

    private final Integer a(int i2) {
        DefaultTrackSelector b2 = b();
        MappingTrackSelector.MappedTrackInfo currentMappedTrackInfo = b2 == null ? null : b2.getCurrentMappedTrackInfo();
        int i3 = 0;
        int rendererCount = currentMappedTrackInfo == null ? 0 : currentMappedTrackInfo.getRendererCount();
        if (rendererCount > 0) {
            while (true) {
                int i4 = i3 + 1;
                Integer valueOf = currentMappedTrackInfo == null ? null : Integer.valueOf(currentMappedTrackInfo.getRendererType(i3));
                if (valueOf != null && valueOf.intValue() == i2) {
                    return Integer.valueOf(i3);
                }
                if (i4 >= rendererCount) {
                    break;
                }
                i3 = i4;
            }
        }
        return null;
    }

    public static final /* synthetic */ void a(ExoplayerUi exoplayerUi) {
        n.b(exoplayerUi, "this$0");
        exoplayerUi.OnVideoSettingsButtonClick();
    }

    public static final /* synthetic */ void a(ExoplayerUi exoplayerUi, int i2) {
        n.b(exoplayerUi, "this$0");
        exoplayerUi.OnVisibilityChanged(i2 == 0);
    }

    public static final /* synthetic */ void a(ExoplayerUi exoplayerUi, boolean z2) {
        n.b(exoplayerUi, "this$0");
        exoplayerUi.OnFullscreenChanged(z2);
    }

    private final void a(HVArrangement hVArrangement, SimpleExoPlayer simpleExoPlayer, i iVar) {
        SimpleExoPlayer simpleExoPlayer2 = simpleExoPlayer;
        i iVar2 = iVar;
        this.e = simpleExoPlayer2;
        this.y = iVar2;
        View view = hVArrangement.getView();
        if (view != null) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (this.d) {
                Log.v(LOG_TAG, "initialize | Debug mode : true");
            }
            PlayerAttributes playerAttributes = new PlayerAttributes(this.T, this.t, k.a(this.i), this.h, this.r, this.s, k.b(this.g), this.v, true, this.d, this.x, this.w, k.c(this.f), this.m, this.n, this.l, this.k, this.j, this.o, this.p, this.q, this.u);
            TimeBarAttributes timeBarAttributes = new TimeBarAttributes((Drawable) null, this.P, 26, 80, 4, this.Q, this.R, this.S, this.N, this.M, this.O, this.L, DefaultTimeBar.DEFAULT_AD_MARKER_COLOR, DefaultTimeBar.DEFAULT_PLAYED_AD_MARKER_COLOR);
            if (iVar2 == i.SimplePlayerView) {
                PlayerView playerView = new PlayerView(this.a, timeBarAttributes, playerAttributes);
                playerView.setPlayer(simpleExoPlayer2);
                playerView.setKeepContentOnPlayerReset(true);
                playerView.setControllerVisibilityListener(new a(this));
                l lVar = l.a;
                this.b = playerView;
            } else {
                StyledPlayerView styledPlayerView = new StyledPlayerView(this.a, timeBarAttributes, playerAttributes);
                styledPlayerView.setPlayer(simpleExoPlayer2);
                styledPlayerView.setKeepContentOnPlayerReset(true);
                styledPlayerView.setControllerVisibilityListener(new b(this));
                if (this.p) {
                    styledPlayerView.setControllerOnFullScreenModeChangedListener(new c(this));
                }
                styledPlayerView.setVideoSettingsButtonListener(new d(this));
                styledPlayerView.setControllerOnSettingsWindowDismissListener(new e(this));
                l lVar2 = l.a;
                this.c = styledPlayerView;
            }
            SubtitleView a2 = a();
            if (a2 != null) {
                SubtitleTextSizeFraction(this.z);
                SubtitleTextSizeAbsolute(this.A);
                a2.setViewType(this.D);
                a2.setBottomPaddingFraction(this.E);
                a2.setStyle(new CaptionStyleCompat(this.F, this.G, this.H, this.J, this.I, this.K));
            }
            viewGroup.addView(iVar2 == i.SimplePlayerView ? this.b : this.c, new ViewGroup.LayoutParams(-1, -1));
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
    }

    private final DefaultTrackSelector b() {
        SimpleExoPlayer simpleExoPlayer = this.e;
        TrackSelector trackSelector = simpleExoPlayer == null ? null : simpleExoPlayer.getTrackSelector();
        if (trackSelector instanceof DefaultTrackSelector) {
            return (DefaultTrackSelector) trackSelector;
        }
        return null;
    }

    public static final /* synthetic */ void b(ExoplayerUi exoplayerUi) {
        n.b(exoplayerUi, "this$0");
        exoplayerUi.OnSettingsWindowDismiss(exoplayerUi.isFullscreen());
    }

    public static final /* synthetic */ void b(ExoplayerUi exoplayerUi, int i2) {
        n.b(exoplayerUi, "this$0");
        exoplayerUi.OnVisibilityChanged(i2 == 0);
    }

    public static final /* synthetic */ void b(ExoplayerUi exoplayerUi, boolean z2) {
        n.b(exoplayerUi, "this$0");
        exoplayerUi.OnSettingsWindowDismiss(z2);
    }

    public final void ActiveThumbSize(int i2) {
        this.S = i2;
    }

    public final void AnimationEnabled(boolean z2) {
        this.u = z2;
    }

    public final void AutoShowController(boolean z2) {
        StyledPlayerView styledPlayerView;
        if (this.y == i.SimplePlayerView) {
            PlayerView playerView = this.b;
            if (playerView != null) {
                playerView.setControllerAutoShow(z2);
            }
        } else if (this.y == i.StyledPlayerView && (styledPlayerView = this.c) != null) {
            styledPlayerView.setControllerAutoShow(z2);
        }
        this.s = z2;
    }

    public final void BufferedColor(int i2) {
        this.O = i2;
    }

    public final boolean CanShowDialog(int i2) {
        boolean z2;
        Integer a2 = a(i2);
        if (!(a2 == null || b() == null)) {
            DefaultTrackSelector b2 = b();
            n.a((Object) b2);
            int intValue = a2.intValue();
            MappingTrackSelector.MappedTrackInfo currentMappedTrackInfo = b2.getCurrentMappedTrackInfo();
            if (currentMappedTrackInfo != null) {
                TrackGroupArray trackGroups = currentMappedTrackInfo.getTrackGroups(intValue);
                n.a((Object) trackGroups, "mappedTrackInfo.getTrackGroups(rendererIndex)");
                if (trackGroups.length != 0) {
                    switch (currentMappedTrackInfo.getRendererType(intValue)) {
                        case 1:
                        case 2:
                        case 3:
                            z2 = true;
                            break;
                    }
                }
                z2 = false;
                if (z2) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void ControllerTimeout(int i2) {
        StyledPlayerView styledPlayerView;
        if (this.y == i.SimplePlayerView) {
            PlayerView playerView = this.b;
            if (playerView != null) {
                playerView.setControllerShowTimeoutMs(i2);
            }
        } else if (this.y == i.StyledPlayerView && (styledPlayerView = this.c) != null) {
            styledPlayerView.setControllerShowTimeoutMs(i2);
        }
        this.h = i2;
    }

    public final boolean ControlsVisible() {
        i iVar = this.y;
        switch (iVar == null ? -1 : j.a[iVar.ordinal()]) {
            case 1:
                PlayerView playerView = this.b;
                if (playerView != null) {
                    return playerView.isControllerVisible();
                }
                return false;
            case 2:
                StyledPlayerView styledPlayerView = this.c;
                if (styledPlayerView != null) {
                    return styledPlayerView.isControllerFullyVisible();
                }
                return false;
            default:
                return false;
        }
    }

    public final void CreateSimplePlayer(HVArrangement hVArrangement, Object obj) {
        n.b(hVArrangement, "layout");
        if (obj != null && (obj instanceof SimpleExoPlayer)) {
            a(hVArrangement, (SimpleExoPlayer) obj, i.SimplePlayerView);
        }
    }

    public final void CreateStyledPlayer(HVArrangement hVArrangement, Object obj) {
        n.b(hVArrangement, "layout");
        if (obj != null && (obj instanceof SimpleExoPlayer)) {
            a(hVArrangement, (SimpleExoPlayer) obj, i.StyledPlayerView);
        }
    }

    public final void DefaultThumbnail(String str) {
        StyledPlayerView styledPlayerView;
        n.b(str, "path");
        if (this.y == i.SimplePlayerView) {
            PlayerView playerView = this.b;
            if (playerView != null) {
                Form form = this.form;
                n.a((Object) form, "form");
                playerView.setDefaultArtwork(k.a(form, str));
            }
        } else if (this.y == i.StyledPlayerView && (styledPlayerView = this.c) != null) {
            Form form2 = this.form;
            n.a((Object) form2, "form");
            styledPlayerView.setDefaultArtwork(k.a(form2, str));
        }
    }

    public final void DisabledThumbSize(int i2) {
        this.R = i2;
    }

    public final String EdgeTypeDepressed() {
        return EDGE_TYPE_DEPRESSED;
    }

    public final String EdgeTypeDropShadow() {
        return EDGE_TYPE_DROP_SHADOW;
    }

    public final String EdgeTypeNone() {
        return EDGE_TYPE_NONE;
    }

    public final String EdgeTypeOutline() {
        return EDGE_TYPE_OUTLINE;
    }

    public final String EdgeTypeRaised() {
        return EDGE_TYPE_RAISED;
    }

    public final void FastForwardButtonVisible(boolean z2) {
        StyledPlayerView styledPlayerView;
        if (this.y == i.SimplePlayerView) {
            PlayerView playerView = this.b;
            if (playerView != null) {
                playerView.setShowFastForwardButton(z2);
            }
        } else if (this.y == i.StyledPlayerView && (styledPlayerView = this.c) != null) {
            styledPlayerView.setShowFastForwardButton(z2);
        }
        this.n = z2;
    }

    public final void FastForwardMs(int i2) {
        this.w = i2;
    }

    public final void FullscreenButtonVisible(boolean z2) {
        this.p = z2;
    }

    public final void HideControls() {
        StyledPlayerView styledPlayerView;
        if (this.y == i.SimplePlayerView) {
            PlayerView playerView = this.b;
            if (playerView != null) {
                playerView.hideController();
            }
        } else if (this.y == i.StyledPlayerView && (styledPlayerView = this.c) != null) {
            styledPlayerView.hideController();
        }
    }

    public final void HideOnTouch(boolean z2) {
        StyledPlayerView styledPlayerView;
        if (this.y == i.SimplePlayerView) {
            PlayerView playerView = this.b;
            if (playerView != null) {
                playerView.setControllerHideOnTouch(z2);
            }
        } else if (this.y == i.StyledPlayerView && (styledPlayerView = this.c) != null) {
            styledPlayerView.setControllerHideOnTouch(z2);
        }
        this.r = z2;
    }

    public final void HideSystemUI() {
        Window window = ((Activity) this.a).getWindow();
        window.getDecorView().setFitsSystemWindows(true);
        window.getDecorView().setSystemUiVisibility(5638);
    }

    public final void IgnorePadding(boolean z2) {
        this.B = z2;
    }

    public final void KeepContentOnPlayerReset(boolean z2) {
        StyledPlayerView styledPlayerView;
        if (this.y == i.SimplePlayerView) {
            PlayerView playerView = this.b;
            if (playerView != null) {
                playerView.setKeepContentOnPlayerReset(z2);
            }
        } else if (this.y == i.StyledPlayerView && (styledPlayerView = this.c) != null) {
            styledPlayerView.setKeepContentOnPlayerReset(z2);
        }
    }

    public final void KeepScreenOn(boolean z2) {
        StyledPlayerView styledPlayerView;
        if (this.y == i.SimplePlayerView) {
            PlayerView playerView = this.b;
            if (playerView != null) {
                playerView.setKeepScreenOn(z2);
            }
        } else if (this.y == i.StyledPlayerView && (styledPlayerView = this.c) != null) {
            styledPlayerView.setKeepScreenOn(z2);
        }
    }

    public final void NextButtonVisible(boolean z2) {
        StyledPlayerView styledPlayerView;
        if (this.y == i.SimplePlayerView) {
            PlayerView playerView = this.b;
            if (playerView != null) {
                playerView.setShowNextButton(z2);
            }
        } else if (this.y == i.StyledPlayerView && (styledPlayerView = this.c) != null) {
            styledPlayerView.setShowNextButton(z2);
        }
        this.k = z2;
    }

    public final void OnFullscreenChanged(boolean z2) {
        EventDispatcher.dispatchEvent(this, "OnFullscreenChanged", Boolean.valueOf(z2));
    }

    public final void OnSettingsWindowDismiss(boolean z2) {
        EventDispatcher.dispatchEvent(this, "OnSettingsWindowDismiss", Boolean.valueOf(z2));
    }

    public final void OnVideoSettingsButtonClick() {
        EventDispatcher.dispatchEvent(this, "OnVideoSettingsButtonClick", new Object[0]);
    }

    public final void OnVisibilityChanged(boolean z2) {
        EventDispatcher.dispatchEvent(this, "OnVisibilityChanged", Boolean.valueOf(z2));
    }

    public final void Player(Object obj) {
        StyledPlayerView styledPlayerView;
        if (obj != null && (obj instanceof SimpleExoPlayer)) {
            this.e = (SimpleExoPlayer) obj;
            if (this.y == i.SimplePlayerView) {
                PlayerView playerView = this.b;
                if (playerView != null) {
                    playerView.setPlayer((Player) obj);
                }
            } else if (this.y == i.StyledPlayerView && (styledPlayerView = this.c) != null) {
                styledPlayerView.setPlayer((Player) obj);
            }
        }
    }

    public final void PreviousButtonVisible(boolean z2) {
        StyledPlayerView styledPlayerView;
        if (this.y == i.SimplePlayerView) {
            PlayerView playerView = this.b;
            if (playerView != null) {
                playerView.setShowPreviousButton(z2);
            }
        } else if (this.y == i.StyledPlayerView && (styledPlayerView = this.c) != null) {
            styledPlayerView.setShowPreviousButton(z2);
        }
        this.l = z2;
    }

    public final void ProgressColor(int i2) {
        this.N = i2;
    }

    public final void RepeatMode(String str) {
        StyledPlayerView styledPlayerView;
        n.b(str, "mode");
        if (this.y == i.SimplePlayerView) {
            PlayerView playerView = this.b;
            if (playerView != null) {
                playerView.setRepeatToggleModes(k.c(str));
            }
        } else if (this.y == i.StyledPlayerView && (styledPlayerView = this.c) != null) {
            styledPlayerView.setRepeatToggleModes(k.c(str));
        }
        this.f = str;
    }

    public final String RepeatModeAll() {
        return REPEAT_MODE_All;
    }

    public final String RepeatModeOff() {
        return REPEAT_MODE_OFF;
    }

    public final String RepeatModeOne() {
        return REPEAT_MODE_ONE;
    }

    public final String RepeatModeOneAll() {
        return REPEAT_MODE_ONE_ALL;
    }

    public final void ResizeMode(String str) {
        StyledPlayerView styledPlayerView;
        n.b(str, "mode");
        if (this.y == i.SimplePlayerView) {
            PlayerView playerView = this.b;
            if (playerView != null) {
                playerView.setResizeMode(k.a(str));
            }
        } else if (this.y == i.StyledPlayerView && (styledPlayerView = this.c) != null) {
            styledPlayerView.setResizeMode(k.a(str));
        }
        this.i = str;
    }

    public final String ResizeModeFill() {
        return RESIZE_MODE_FILL;
    }

    public final String ResizeModeFit() {
        return RESIZE_MODE_FIT;
    }

    public final String ResizeModeFixedHeight() {
        return RESIZE_MODE_FIXED_HEIGHT;
    }

    public final String ResizeModeFixedWidth() {
        return RESIZE_MODE_FIXED_WIDTH;
    }

    public final String ResizeModeZoom() {
        return RESIZE_MODE_ZOOM;
    }

    public final void RewindButtonVisible(boolean z2) {
        StyledPlayerView styledPlayerView;
        if (this.y == i.SimplePlayerView) {
            PlayerView playerView = this.b;
            if (playerView != null) {
                playerView.setShowRewindButton(z2);
            }
        } else if (this.y == i.StyledPlayerView && (styledPlayerView = this.c) != null) {
            styledPlayerView.setShowRewindButton(z2);
        }
        this.m = z2;
    }

    public final void RewindMs(int i2) {
        this.x = i2;
    }

    public final void ShowControls() {
        StyledPlayerView styledPlayerView;
        if (this.y == i.SimplePlayerView) {
            PlayerView playerView = this.b;
            if (playerView != null) {
                playerView.showController();
            }
        } else if (this.y == i.StyledPlayerView && (styledPlayerView = this.c) != null) {
            styledPlayerView.showController();
        }
    }

    public final void ShowLoading(String str) {
        StyledPlayerView styledPlayerView;
        n.b(str, "show");
        if (this.y == i.SimplePlayerView) {
            PlayerView playerView = this.b;
            if (playerView != null) {
                playerView.setShowBuffering(k.b(str));
            }
        } else if (this.y == i.StyledPlayerView && (styledPlayerView = this.c) != null) {
            styledPlayerView.setShowBuffering(k.b(str));
        }
        this.g = str;
    }

    public final String ShowLoadingAlways() {
        return SHOW_BUFFERING_ALWAYS;
    }

    public final String ShowLoadingNever() {
        return SHOW_BUFFERING_NEVER;
    }

    public final String ShowLoadingWhenPlaying() {
        return SHOW_BUFFERING_WHEN_PLAYING;
    }

    public final void ShowSelectionDialog(String str, int i2, boolean z2, boolean z3, boolean z4) {
        n.b(str, "title");
        if (CanShowDialog(i2)) {
            Integer a2 = a(i2);
            TrackNameProvider trackNameProvider = i2 == 2 ? f.a : null;
            DefaultTrackSelector b2 = b();
            n.a((Object) b2);
            n.a((Object) a2);
            Dialog build = new TrackSelectionDialogBuilder(this.a, str, b2, a2.intValue()).setShowDisableOption(z2).setAllowAdaptiveSelections(z3).setAllowMultipleOverrides(z4).setTrackNameProvider(trackNameProvider).build();
            n.a((Object) build, "TrackSelectionDialogBuilder(context, title, trackSelector!!, rendererIndex!!)\n                .setShowDisableOption(showDisabledOptions)\n                .setAllowAdaptiveSelections(allowAdaptiveSelections)\n                .setAllowMultipleOverrides(allowMultipleOverrides)\n                .setTrackNameProvider(trackNameProvider)\n                .build()");
            build.setOnDismissListener(new g(this));
            build.show();
        }
    }

    public final void ShowSystemUI() {
        ((Activity) this.a).getWindow().getDecorView().setSystemUiVisibility(0);
    }

    public final void ShuffleButtonVisible(boolean z2) {
        StyledPlayerView styledPlayerView;
        if (this.y == i.SimplePlayerView) {
            PlayerView playerView = this.b;
            if (playerView != null) {
                playerView.setShowShuffleButton(z2);
            }
        } else if (this.y == i.StyledPlayerView && (styledPlayerView = this.c) != null) {
            styledPlayerView.setShowShuffleButton(z2);
        }
        this.j = z2;
    }

    public final void SubtitleBackgroundColor(int i2) {
        this.G = i2;
    }

    public final void SubtitleBottomPadding(float f2) {
        this.E = f2;
        SubtitleView a2 = a();
        if (a2 != null) {
            a2.setBottomPaddingFraction(f2);
        }
    }

    public final void SubtitleButtonVisible(boolean z2) {
        StyledPlayerView styledPlayerView = this.c;
        if (styledPlayerView != null) {
            styledPlayerView.setShowSubtitleButton(z2);
        }
        this.o = z2;
    }

    public final void SubtitleEdgeColor(int i2) {
        this.I = i2;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void SubtitleEdgeType(java.lang.String r2) {
        /*
            r1 = this;
            java.lang.String r0 = "type"
            com.dreamers.exoplayerui.repack.n.b(r2, r0)
            com.dreamers.exoplayerui.repack.n.b(r2, r0)
            int r0 = r2.hashCode()
            switch(r0) {
                case -1854553576: goto L_0x0037;
                case -579613508: goto L_0x0031;
                case 558407714: goto L_0x0026;
                case 1106537329: goto L_0x001b;
                case 1259068513: goto L_0x0010;
                default: goto L_0x000f;
            }
        L_0x000f:
            goto L_0x0042
        L_0x0010:
            java.lang.String r0 = "Depressed"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0019
            goto L_0x0042
        L_0x0019:
            r2 = 4
            goto L_0x0043
        L_0x001b:
            java.lang.String r0 = "Drop Shadow"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0024
            goto L_0x0042
        L_0x0024:
            r2 = 2
            goto L_0x0043
        L_0x0026:
            java.lang.String r0 = "Outline"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x002f
            goto L_0x0042
        L_0x002f:
            r2 = 1
            goto L_0x0043
        L_0x0031:
            java.lang.String r0 = "No Edge"
            r2.equals(r0)
            goto L_0x0042
        L_0x0037:
            java.lang.String r0 = "Raised"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0040
            goto L_0x0042
        L_0x0040:
            r2 = 3
            goto L_0x0043
        L_0x0042:
            r2 = 0
        L_0x0043:
            r1.J = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dreamers.exoplayerui.ExoplayerUi.SubtitleEdgeType(java.lang.String):void");
    }

    public final void SubtitleForegroundColor(int i2) {
        this.F = i2;
    }

    public final void SubtitleRenderViewType(String str) {
        n.b(str, "type");
        n.b(str, "type");
        this.D = (n.a((Object) str, (Object) VIEW_TYPE_CANVAS) || !n.a((Object) str, (Object) VIEW_TYPE_WEB)) ? 1 : 2;
        SubtitleView a2 = a();
        if (a2 != null) {
            a2.setViewType(this.D);
        }
    }

    public final void SubtitleTextSizeAbsolute(float f2) {
        SubtitleView a2;
        this.A = f2;
        if (this.C == 2 && (a2 = a()) != null) {
            a2.setFixedTextSize(2, this.A);
        }
    }

    public final void SubtitleTextSizeFraction(float f2) {
        SubtitleView a2;
        this.z = f2;
        if (this.C == 0 && (a2 = a()) != null) {
            a2.setFractionalTextSize(f2, this.B);
        }
    }

    public final void SubtitleTextSizeType(String str) {
        n.b(str, "type");
        n.b(str, "type");
        this.C = (n.a((Object) str, (Object) TEXT_SIE_TYPE_FRACTION) || !n.a((Object) str, (Object) TEXT_SIE_TYPE_ABSOLUTE)) ? 0 : 2;
    }

    public final void SubtitleTypeface(String str) {
        n.b(str, PropertyTypeConstants.PROPERTY_TYPE_ASSET);
        this.K = k.a(this.a, str);
    }

    public final String SubtitleViewTypeCanvas() {
        return VIEW_TYPE_CANVAS;
    }

    public final String SubtitleViewTypeWeb() {
        return VIEW_TYPE_WEB;
    }

    public final void SubtitleWindowColor(int i2) {
        this.H = i2;
    }

    public final void SurfaceType(String str) {
        n.b(str, "type");
        Log.v(LOG_TAG, n.a("SurfaceType | type: ", (Object) str));
        n.b(str, "type");
        int i2 = 1;
        switch (str.hashCode()) {
            case -1398204726:
                if (str.equals(SURFACE_TYPE_TEXTURE_VIEW)) {
                    i2 = 2;
                    break;
                }
                break;
            case -424220040:
                boolean equals = str.equals(SURFACE_TYPE_SURFACE_VIEW);
                break;
            case 2433880:
                if (str.equals(SURFACE_TYPE_NONE)) {
                    i2 = 0;
                    break;
                }
                break;
        }
        this.T = i2;
    }

    public final String SurfaceTypeNone() {
        return SURFACE_TYPE_NONE;
    }

    public final String SurfaceTypeSurfaceView() {
        return SURFACE_TYPE_SURFACE_VIEW;
    }

    public final String SurfaceTypeTextureView() {
        return SURFACE_TYPE_TEXTURE_VIEW;
    }

    public final String TextSizeTypeAbsolute() {
        return TEXT_SIE_TYPE_ABSOLUTE;
    }

    public final String TextSizeTypeFractional() {
        return TEXT_SIE_TYPE_FRACTION;
    }

    public final void ThumbColor(int i2) {
        this.M = i2;
    }

    public final void ThumbSize(int i2) {
        this.Q = i2;
    }

    public final void TrackColor(int i2) {
        this.L = i2;
    }

    public final void TrackHeight(int i2) {
        this.P = i2;
    }

    public final int TrackTypeAudio() {
        return 1;
    }

    public final int TrackTypeText() {
        return 3;
    }

    public final int TrackTypeVideo() {
        return 2;
    }

    public final void UseArtwork(boolean z2) {
        StyledPlayerView styledPlayerView;
        if (this.y == i.SimplePlayerView) {
            PlayerView playerView = this.b;
            if (playerView != null) {
                playerView.setUseArtwork(z2);
            }
        } else if (this.y == i.StyledPlayerView && (styledPlayerView = this.c) != null) {
            styledPlayerView.setUseArtwork(z2);
        }
        this.t = z2;
    }

    public final void UseController(boolean z2) {
        StyledPlayerView styledPlayerView;
        if (this.y == i.SimplePlayerView) {
            PlayerView playerView = this.b;
            if (playerView != null) {
                playerView.setUseController(z2);
            }
        } else if (this.y == i.StyledPlayerView && (styledPlayerView = this.c) != null) {
            styledPlayerView.setUseController(z2);
        }
        this.v = z2;
    }

    public final void VideoSettingsButtonVisible(boolean z2) {
        this.q = z2;
        StyledPlayerView styledPlayerView = this.c;
        if (styledPlayerView != null) {
            styledPlayerView.setShowVideoSettingsButton(z2);
        }
    }

    public final boolean isFullscreen() {
        StyledPlayerView styledPlayerView = this.c;
        if (styledPlayerView == null) {
            return false;
        }
        return styledPlayerView.getIsFullscreen();
    }

    public final void onPause() {
        StyledPlayerView styledPlayerView;
        Log.v(LOG_TAG, "onPause");
        if (Build.VERSION.SDK_INT >= 24) {
            return;
        }
        if (this.y == i.SimplePlayerView) {
            PlayerView playerView = this.b;
            if (playerView != null) {
                playerView.onPause();
            }
        } else if (this.y == i.StyledPlayerView && (styledPlayerView = this.c) != null) {
            styledPlayerView.onPause();
        }
    }

    public final void onResume() {
        StyledPlayerView styledPlayerView;
        Log.v(LOG_TAG, "onResume");
        if (Build.VERSION.SDK_INT >= 24) {
            return;
        }
        if (this.y == i.SimplePlayerView) {
            PlayerView playerView = this.b;
            if (playerView != null) {
                playerView.onResume();
            }
        } else if (this.y == i.StyledPlayerView && (styledPlayerView = this.c) != null) {
            styledPlayerView.onResume();
        }
    }
}
