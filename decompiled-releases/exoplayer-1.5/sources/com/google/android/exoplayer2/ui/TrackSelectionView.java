package com.google.android.exoplayer2.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import com.dreamers.exoplayerui.ExoplayerUi;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.util.Assertions;
import com.google.appinventor.components.runtime.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TrackSelectionView extends LinearLayout {
    private boolean allowAdaptiveSelections;
    private boolean allowMultipleOverrides;
    private final ComponentListener componentListener;
    private Context context;
    private final CheckedTextView defaultView;
    private final CheckedTextView disableView;
    private final LayoutInflater inflater;
    private boolean isDisabled;
    private TrackSelectionListener listener;
    private MappingTrackSelector.MappedTrackInfo mappedTrackInfo;
    private final SparseArray overrides;
    private int rendererIndex;
    private final int selectableItemBackgroundResourceId;
    private TrackGroupArray trackGroups;
    private Comparator trackInfoComparator;
    private TrackNameProvider trackNameProvider;
    private CheckedTextView[][] trackViews;

    class ComponentListener implements View.OnClickListener {
        private ComponentListener() {
        }

        public void onClick(View view) {
            TrackSelectionView.this.onClick(view);
        }
    }

    final class TrackInfo {
        public final Format format;
        public final int groupIndex;
        public final int trackIndex;

        public TrackInfo(int i, int i2, Format format2) {
            this.groupIndex = i;
            this.trackIndex = i2;
            this.format = format2;
        }
    }

    public interface TrackSelectionListener {
        void onTrackSelectionChanged(boolean z, List list);
    }

    public TrackSelectionView(Context context2) {
        this(context2, (AttributeSet) null);
        this.context = context2;
    }

    public TrackSelectionView(Context context2, AttributeSet attributeSet) {
        this(context2, attributeSet, 0);
        this.context = context2;
    }

    public TrackSelectionView(Context context2, AttributeSet attributeSet, int i) {
        super(context2, attributeSet, i);
        setOrientation(1);
        this.context = context2;
        this.overrides = new SparseArray();
        setSaveFromParentEnabled(false);
        TypedArray obtainStyledAttributes = context2.getTheme().obtainStyledAttributes(new int[]{16843534});
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        this.selectableItemBackgroundResourceId = resourceId;
        obtainStyledAttributes.recycle();
        LayoutInflater from = LayoutInflater.from(context2);
        this.inflater = from;
        ComponentListener componentListener2 = new ComponentListener();
        this.componentListener = componentListener2;
        this.trackNameProvider = new DefaultTrackNameProvider(getResources());
        this.trackGroups = TrackGroupArray.a;
        CheckedTextView checkedTextView = (CheckedTextView) from.inflate(17367055, this, false);
        this.disableView = checkedTextView;
        checkedTextView.setBackgroundResource(resourceId);
        checkedTextView.setText(ExoplayerUi.SURFACE_TYPE_NONE);
        checkedTextView.setEnabled(false);
        checkedTextView.setFocusable(true);
        checkedTextView.setOnClickListener(componentListener2);
        checkedTextView.setVisibility(8);
        addView(checkedTextView);
        addView(createDividerView(context2));
        CheckedTextView checkedTextView2 = (CheckedTextView) from.inflate(17367055, this, false);
        this.defaultView = checkedTextView2;
        checkedTextView2.setBackgroundResource(resourceId);
        checkedTextView2.setText("Auto");
        checkedTextView2.setEnabled(false);
        checkedTextView2.setFocusable(true);
        checkedTextView2.setOnClickListener(componentListener2);
        addView(checkedTextView2);
    }

    private View createDividerView(Context context2) {
        View view = new View(context2);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, 1));
        view.setBackgroundColor(Component.COLOR_DKGRAY);
        return view;
    }

    private static int[] getTracksAdding(int[] iArr, int i) {
        int[] copyOf = Arrays.copyOf(iArr, iArr.length + 1);
        copyOf[copyOf.length - 1] = i;
        return copyOf;
    }

    private static int[] getTracksRemoving(int[] iArr, int i) {
        int[] iArr2 = new int[(iArr.length - 1)];
        int i2 = 0;
        for (int i3 : iArr) {
            if (i3 != i) {
                iArr2[i2] = i3;
                i2++;
            }
        }
        return iArr2;
    }

    /* access modifiers changed from: private */
    public void onClick(View view) {
        if (view == this.disableView) {
            onDisableViewClicked();
        } else if (view == this.defaultView) {
            onDefaultViewClicked();
        } else {
            onTrackViewClicked(view);
        }
        updateViewStates();
        TrackSelectionListener trackSelectionListener = this.listener;
        if (trackSelectionListener != null) {
            trackSelectionListener.onTrackSelectionChanged(getIsDisabled(), getOverrides());
        }
    }

    private void onDefaultViewClicked() {
        this.isDisabled = false;
        this.overrides.clear();
    }

    private void onDisableViewClicked() {
        this.isDisabled = true;
        this.overrides.clear();
    }

    private void onTrackViewClicked(View view) {
        boolean z = false;
        this.isDisabled = false;
        TrackInfo trackInfo = (TrackInfo) Assertions.b(view.getTag());
        int i = trackInfo.groupIndex;
        int i2 = trackInfo.trackIndex;
        DefaultTrackSelector.SelectionOverride selectionOverride = (DefaultTrackSelector.SelectionOverride) this.overrides.get(i);
        Assertions.b((Object) this.mappedTrackInfo);
        if (selectionOverride == null) {
            if (!this.allowMultipleOverrides && this.overrides.size() > 0) {
                this.overrides.clear();
            }
            this.overrides.put(i, new DefaultTrackSelector.SelectionOverride(i, i2));
            return;
        }
        int i3 = selectionOverride.c;
        int[] iArr = selectionOverride.b;
        boolean isChecked = ((CheckedTextView) view).isChecked();
        boolean shouldEnableAdaptiveSelection = shouldEnableAdaptiveSelection(i);
        if (shouldEnableAdaptiveSelection || shouldEnableMultiGroupSelection()) {
            z = true;
        }
        if (!isChecked || !z) {
            if (isChecked) {
                return;
            }
            if (shouldEnableAdaptiveSelection) {
                this.overrides.put(i, new DefaultTrackSelector.SelectionOverride(i, getTracksAdding(iArr, i2)));
                return;
            }
            this.overrides.put(i, new DefaultTrackSelector.SelectionOverride(i, i2));
        } else if (i3 == 1) {
            this.overrides.remove(i);
        } else {
            this.overrides.put(i, new DefaultTrackSelector.SelectionOverride(i, getTracksRemoving(iArr, i2)));
        }
    }

    private boolean shouldEnableAdaptiveSelection(int i) {
        return this.allowAdaptiveSelections && this.trackGroups.b[i].a > 1 && this.mappedTrackInfo.getAdaptiveSupport(this.rendererIndex, i, false) != 0;
    }

    private boolean shouldEnableMultiGroupSelection() {
        return this.allowMultipleOverrides && this.trackGroups.length > 1;
    }

    private void updateViewStates() {
        boolean z;
        this.disableView.setChecked(this.isDisabled);
        this.defaultView.setChecked(!this.isDisabled && this.overrides.size() == 0);
        for (int i = 0; i < this.trackViews.length; i++) {
            DefaultTrackSelector.SelectionOverride selectionOverride = (DefaultTrackSelector.SelectionOverride) this.overrides.get(i);
            int i2 = 0;
            while (true) {
                CheckedTextView[] checkedTextViewArr = this.trackViews[i];
                if (i2 >= checkedTextViewArr.length) {
                    break;
                }
                if (selectionOverride != null) {
                    CheckedTextView checkedTextView = this.trackViews[i][i2];
                    int i3 = ((TrackInfo) Assertions.b(checkedTextViewArr[i2].getTag())).trackIndex;
                    int[] iArr = selectionOverride.b;
                    int length = iArr.length;
                    int i4 = 0;
                    while (true) {
                        if (i4 >= length) {
                            z = false;
                            break;
                        } else if (iArr[i4] == i3) {
                            z = true;
                            break;
                        } else {
                            i4++;
                        }
                    }
                    checkedTextView.setChecked(z);
                } else {
                    checkedTextViewArr[i2].setChecked(false);
                }
                i2++;
            }
        }
    }

    private void updateViews() {
        for (int childCount = getChildCount() - 1; childCount >= 3; childCount--) {
            removeViewAt(childCount);
        }
        if (this.mappedTrackInfo == null) {
            this.disableView.setEnabled(false);
            this.defaultView.setEnabled(false);
            return;
        }
        this.disableView.setEnabled(true);
        this.defaultView.setEnabled(true);
        TrackGroupArray trackGroups2 = this.mappedTrackInfo.getTrackGroups(this.rendererIndex);
        this.trackGroups = trackGroups2;
        this.trackViews = new CheckedTextView[trackGroups2.length][];
        boolean shouldEnableMultiGroupSelection = shouldEnableMultiGroupSelection();
        for (int i = 0; i < this.trackGroups.length; i++) {
            TrackGroup trackGroup = this.trackGroups.b[i];
            boolean shouldEnableAdaptiveSelection = shouldEnableAdaptiveSelection(i);
            this.trackViews[i] = new CheckedTextView[trackGroup.a];
            int i2 = trackGroup.a;
            TrackInfo[] trackInfoArr = new TrackInfo[i2];
            for (int i3 = 0; i3 < trackGroup.a; i3++) {
                trackInfoArr[i3] = new TrackInfo(i, i3, trackGroup.b[i3]);
            }
            Comparator comparator = this.trackInfoComparator;
            if (comparator != null) {
                Arrays.sort(trackInfoArr, comparator);
            }
            for (int i4 = 0; i4 < i2; i4++) {
                if (i4 == 0) {
                    addView(createDividerView(this.context));
                }
                CheckedTextView checkedTextView = (CheckedTextView) this.inflater.inflate((shouldEnableAdaptiveSelection || shouldEnableMultiGroupSelection) ? 17367056 : 17367055, this, false);
                checkedTextView.setBackgroundResource(this.selectableItemBackgroundResourceId);
                checkedTextView.setText(this.trackNameProvider.getTrackName(trackInfoArr[i4].format));
                checkedTextView.setTag(trackInfoArr[i4]);
                if (this.mappedTrackInfo.getTrackSupport(this.rendererIndex, i, i4) == 4) {
                    checkedTextView.setFocusable(true);
                    checkedTextView.setOnClickListener(this.componentListener);
                } else {
                    checkedTextView.setFocusable(false);
                    checkedTextView.setEnabled(false);
                }
                this.trackViews[i][i4] = checkedTextView;
                addView(checkedTextView);
            }
        }
        updateViewStates();
    }

    public boolean getIsDisabled() {
        return this.isDisabled;
    }

    public List getOverrides() {
        ArrayList arrayList = new ArrayList(this.overrides.size());
        for (int i = 0; i < this.overrides.size(); i++) {
            arrayList.add(this.overrides.valueAt(i));
        }
        return arrayList;
    }

    public void init(MappingTrackSelector.MappedTrackInfo mappedTrackInfo2, int i, boolean z, List list, Comparator comparator, TrackSelectionListener trackSelectionListener) {
        this.mappedTrackInfo = mappedTrackInfo2;
        this.rendererIndex = i;
        this.isDisabled = z;
        this.trackInfoComparator = comparator == null ? null : new TrackSelectionView$$Lambda$0(comparator);
        this.listener = trackSelectionListener;
        int size = this.allowMultipleOverrides ? list.size() : Math.min(list.size(), 1);
        for (int i2 = 0; i2 < size; i2++) {
            DefaultTrackSelector.SelectionOverride selectionOverride = (DefaultTrackSelector.SelectionOverride) list.get(i2);
            this.overrides.put(selectionOverride.a, selectionOverride);
        }
        updateViews();
    }

    public void setAllowAdaptiveSelections(boolean z) {
        if (this.allowAdaptiveSelections != z) {
            this.allowAdaptiveSelections = z;
            updateViews();
        }
    }

    public void setAllowMultipleOverrides(boolean z) {
        if (this.allowMultipleOverrides != z) {
            this.allowMultipleOverrides = z;
            if (!z && this.overrides.size() > 1) {
                for (int size = this.overrides.size() - 1; size > 0; size--) {
                    this.overrides.remove(size);
                }
            }
            updateViews();
        }
    }

    public void setShowDisableOption(boolean z) {
        this.disableView.setVisibility(z ? 0 : 8);
    }

    public void setTrackNameProvider(TrackNameProvider trackNameProvider2) {
        this.trackNameProvider = (TrackNameProvider) Assertions.b((Object) trackNameProvider2);
        updateViews();
    }
}
