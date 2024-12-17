package com.google.android.exoplayer2.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionUtil;
import com.google.android.exoplayer2.ui.TrackSelectionView;
import com.google.android.exoplayer2.util.Assertions;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class TrackSelectionDialogBuilder {
    private boolean allowAdaptiveSelections;
    private boolean allowMultipleOverrides;
    private final DialogCallback callback;
    private final Context context;
    private boolean isDisabled;
    private final MappingTrackSelector.MappedTrackInfo mappedTrackInfo;
    private List overrides;
    private final int rendererIndex;
    private boolean showDisableOption;
    private int themeResId;
    private final CharSequence title;
    private Comparator trackFormatComparator;
    private TrackNameProvider trackNameProvider;
    private final String trackSelectionTag = "TrackSelectionTag";

    public interface DialogCallback {
        void onTracksSelected(boolean z, List list);
    }

    public TrackSelectionDialogBuilder(Context context2, CharSequence charSequence, DefaultTrackSelector defaultTrackSelector, int i) {
        this.context = context2;
        this.title = charSequence;
        MappingTrackSelector.MappedTrackInfo mappedTrackInfo2 = (MappingTrackSelector.MappedTrackInfo) Assertions.b((Object) defaultTrackSelector.getCurrentMappedTrackInfo());
        this.mappedTrackInfo = mappedTrackInfo2;
        this.rendererIndex = i;
        TrackGroupArray trackGroups = mappedTrackInfo2.getTrackGroups(i);
        DefaultTrackSelector.Parameters parameters = defaultTrackSelector.getParameters();
        this.isDisabled = parameters.a(i);
        DefaultTrackSelector.SelectionOverride b = parameters.b(i, trackGroups);
        this.overrides = b == null ? Collections.emptyList() : Collections.singletonList(b);
        this.callback = new TrackSelectionDialogBuilder$$Lambda$0(defaultTrackSelector, parameters, i, trackGroups);
    }

    public TrackSelectionDialogBuilder(Context context2, CharSequence charSequence, MappingTrackSelector.MappedTrackInfo mappedTrackInfo2, int i, DialogCallback dialogCallback) {
        this.context = context2;
        this.title = charSequence;
        this.mappedTrackInfo = mappedTrackInfo2;
        this.rendererIndex = i;
        this.callback = dialogCallback;
        this.overrides = Collections.emptyList();
    }

    private Dialog buildForAndroidX() {
        try {
            Class<?> cls = Class.forName("androidx.appcompat.app.AlertDialog$Builder");
            Object newInstance = cls.getConstructor(new Class[]{Context.class, Integer.TYPE}).newInstance(new Object[]{this.context, Integer.valueOf(this.themeResId)});
            LayoutInflater.from((Context) cls.getMethod("getContext", new Class[0]).invoke(newInstance, new Object[0]));
            ScrollView scrollView = new ScrollView(this.context);
            scrollView.setLayoutParams(new FrameLayout.LayoutParams(-2, -1));
            TrackSelectionView trackSelectionView = new TrackSelectionView(this.context);
            trackSelectionView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            trackSelectionView.setTag("TrackSelectionTag");
            scrollView.addView(trackSelectionView);
            DialogInterface.OnClickListener upDialogView = setUpDialogView(scrollView);
            cls.getMethod("setTitle", new Class[]{CharSequence.class}).invoke(newInstance, new Object[]{this.title});
            cls.getMethod("setView", new Class[]{View.class}).invoke(newInstance, new Object[]{scrollView});
            cls.getMethod("setPositiveButton", new Class[]{Integer.TYPE, DialogInterface.OnClickListener.class}).invoke(newInstance, new Object[]{17039370, upDialogView});
            cls.getMethod("setNegativeButton", new Class[]{Integer.TYPE, DialogInterface.OnClickListener.class}).invoke(newInstance, new Object[]{17039360, null});
            return (Dialog) cls.getMethod("create", new Class[0]).invoke(newInstance, new Object[0]);
        } catch (ClassNotFoundException e) {
            return null;
        } catch (Exception e2) {
            throw new IllegalStateException(e2);
        }
    }

    private Dialog buildForPlatform() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context, this.themeResId);
        LayoutInflater.from(builder.getContext());
        ScrollView scrollView = new ScrollView(this.context);
        scrollView.setLayoutParams(new FrameLayout.LayoutParams(-2, -1));
        TrackSelectionView trackSelectionView = new TrackSelectionView(this.context);
        trackSelectionView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        trackSelectionView.setTag("TrackSelectionTag");
        scrollView.addView(trackSelectionView);
        return builder.setTitle(this.title).setView(scrollView).setPositiveButton(17039370, setUpDialogView(scrollView)).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).create();
    }

    static final /* synthetic */ void lambda$new$0$TrackSelectionDialogBuilder(DefaultTrackSelector defaultTrackSelector, DefaultTrackSelector.Parameters parameters, int i, TrackGroupArray trackGroupArray, boolean z, List list) {
        defaultTrackSelector.setParameters(TrackSelectionUtil.a(parameters, i, trackGroupArray, z, list.isEmpty() ? null : (DefaultTrackSelector.SelectionOverride) list.get(0)));
    }

    private DialogInterface.OnClickListener setUpDialogView(View view) {
        TrackSelectionView trackSelectionView = (TrackSelectionView) view.findViewWithTag("TrackSelectionTag");
        trackSelectionView.setAllowMultipleOverrides(this.allowMultipleOverrides);
        trackSelectionView.setAllowAdaptiveSelections(this.allowAdaptiveSelections);
        trackSelectionView.setShowDisableOption(this.showDisableOption);
        TrackNameProvider trackNameProvider2 = this.trackNameProvider;
        if (trackNameProvider2 != null) {
            trackSelectionView.setTrackNameProvider(trackNameProvider2);
        }
        trackSelectionView.init(this.mappedTrackInfo, this.rendererIndex, this.isDisabled, this.overrides, this.trackFormatComparator, (TrackSelectionView.TrackSelectionListener) null);
        return new TrackSelectionDialogBuilder$$Lambda$1(this, trackSelectionView);
    }

    public final Dialog build() {
        Dialog buildForAndroidX = buildForAndroidX();
        return buildForAndroidX == null ? buildForPlatform() : buildForAndroidX;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void lambda$setUpDialogView$1$TrackSelectionDialogBuilder(TrackSelectionView trackSelectionView, DialogInterface dialogInterface, int i) {
        this.callback.onTracksSelected(trackSelectionView.getIsDisabled(), trackSelectionView.getOverrides());
    }

    public final TrackSelectionDialogBuilder setAllowAdaptiveSelections(boolean z) {
        this.allowAdaptiveSelections = z;
        return this;
    }

    public final TrackSelectionDialogBuilder setAllowMultipleOverrides(boolean z) {
        this.allowMultipleOverrides = z;
        return this;
    }

    public final TrackSelectionDialogBuilder setIsDisabled(boolean z) {
        this.isDisabled = z;
        return this;
    }

    public final TrackSelectionDialogBuilder setOverride(DefaultTrackSelector.SelectionOverride selectionOverride) {
        return setOverrides(selectionOverride == null ? Collections.emptyList() : Collections.singletonList(selectionOverride));
    }

    public final TrackSelectionDialogBuilder setOverrides(List list) {
        this.overrides = list;
        return this;
    }

    public final TrackSelectionDialogBuilder setShowDisableOption(boolean z) {
        this.showDisableOption = z;
        return this;
    }

    public final TrackSelectionDialogBuilder setTheme(int i) {
        this.themeResId = i;
        return this;
    }

    public final void setTrackFormatComparator(Comparator comparator) {
        this.trackFormatComparator = comparator;
    }

    public final TrackSelectionDialogBuilder setTrackNameProvider(TrackNameProvider trackNameProvider2) {
        this.trackNameProvider = trackNameProvider2;
        return this;
    }
}
