package com.dreamers.exoplayercore.repack;

import android.view.ViewGroup;
import androidx.core.os.TraceCompat;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import java.util.List;

public abstract class M {
    private boolean mHasStableIds = false;
    private final N mObservable = new N();

    public final void bindViewHolder(C0010ai aiVar, int i) {
        aiVar.mPosition = i;
        if (hasStableIds()) {
            aiVar.mItemId = getItemId(i);
        }
        aiVar.setFlags(1, ErrorMessages.ERROR_BLUETOOTH_UNSUPPORTED_ENCODING);
        TraceCompat.beginSection("RV OnBindView");
        onBindViewHolder(aiVar, i, aiVar.getUnmodifiedPayloads());
        aiVar.clearPayload();
        ViewGroup.LayoutParams layoutParams = aiVar.itemView.getLayoutParams();
        if (layoutParams instanceof Y) {
            ((Y) layoutParams).c = true;
        }
        TraceCompat.endSection();
    }

    public final C0010ai createViewHolder(ViewGroup viewGroup, int i) {
        try {
            TraceCompat.beginSection("RV CreateView");
            C0010ai onCreateViewHolder = onCreateViewHolder(viewGroup, i);
            if (onCreateViewHolder.itemView.getParent() == null) {
                onCreateViewHolder.mItemViewType = i;
                return onCreateViewHolder;
            }
            throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
        } finally {
            TraceCompat.endSection();
        }
    }

    public abstract int getItemCount();

    public long getItemId(int i) {
        return -1;
    }

    public int getItemViewType(int i) {
        return 0;
    }

    public final boolean hasObservers() {
        return this.mObservable.a();
    }

    public final boolean hasStableIds() {
        return this.mHasStableIds;
    }

    public final void notifyDataSetChanged() {
        this.mObservable.b();
    }

    public final void notifyItemChanged(int i) {
        this.mObservable.a(i, 1);
    }

    public final void notifyItemChanged(int i, Object obj) {
        this.mObservable.a(i, 1, obj);
    }

    public final void notifyItemInserted(int i) {
        this.mObservable.b(i, 1);
    }

    public final void notifyItemMoved(int i, int i2) {
        this.mObservable.d(i, i2);
    }

    public final void notifyItemRangeChanged(int i, int i2) {
        this.mObservable.a(i, i2);
    }

    public final void notifyItemRangeChanged(int i, int i2, Object obj) {
        this.mObservable.a(i, i2, obj);
    }

    public final void notifyItemRangeInserted(int i, int i2) {
        this.mObservable.b(i, i2);
    }

    public final void notifyItemRangeRemoved(int i, int i2) {
        this.mObservable.c(i, i2);
    }

    public final void notifyItemRemoved(int i) {
        this.mObservable.c(i, 1);
    }

    public void onAttachedToRecyclerView(F f) {
    }

    public abstract void onBindViewHolder(C0010ai aiVar, int i);

    public void onBindViewHolder(C0010ai aiVar, int i, List list) {
        onBindViewHolder(aiVar, i);
    }

    public abstract C0010ai onCreateViewHolder(ViewGroup viewGroup, int i);

    public void onDetachedFromRecyclerView(F f) {
    }

    public boolean onFailedToRecycleView(C0010ai aiVar) {
        return false;
    }

    public void onViewAttachedToWindow(C0010ai aiVar) {
    }

    public void onViewDetachedFromWindow(C0010ai aiVar) {
    }

    public void onViewRecycled(C0010ai aiVar) {
    }

    public void registerAdapterDataObserver(O o) {
        this.mObservable.registerObserver(o);
    }

    public void setHasStableIds(boolean z) {
        if (!hasObservers()) {
            this.mHasStableIds = z;
            return;
        }
        throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
    }

    public void unregisterAdapterDataObserver(O o) {
        this.mObservable.unregisterObserver(o);
    }
}
