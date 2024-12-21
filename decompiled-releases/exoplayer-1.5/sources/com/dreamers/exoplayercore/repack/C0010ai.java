package com.dreamers.exoplayercore.repack;

import android.util.Log;
import android.view.View;
import androidx.core.view.ViewCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: com.dreamers.exoplayercore.repack.ai  reason: case insensitive filesystem */
public abstract class C0010ai {
    static final int FLAG_ADAPTER_FULLUPDATE = 1024;
    static final int FLAG_ADAPTER_POSITION_UNKNOWN = 512;
    static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
    static final int FLAG_BOUNCED_FROM_HIDDEN_LIST = 8192;
    static final int FLAG_BOUND = 1;
    static final int FLAG_IGNORE = 128;
    static final int FLAG_INVALID = 4;
    static final int FLAG_MOVED = 2048;
    static final int FLAG_NOT_RECYCLABLE = 16;
    static final int FLAG_REMOVED = 8;
    static final int FLAG_RETURNED_FROM_SCRAP = 32;
    static final int FLAG_SET_A11Y_ITEM_DELEGATE = 16384;
    static final int FLAG_TMP_DETACHED = 256;
    static final int FLAG_UPDATE = 2;
    private static final List FULLUPDATE_PAYLOADS = Collections.emptyList();
    static final int PENDING_ACCESSIBILITY_STATE_NOT_SET = -1;
    public final View itemView;
    int mFlags;
    boolean mInChangeScrap = false;
    private int mIsRecyclableCount = 0;
    long mItemId = -1;
    int mItemViewType = -1;
    WeakReference mNestedRecyclerView;
    int mOldPosition = -1;
    F mOwnerRecyclerView;
    List mPayloads = null;
    int mPendingAccessibilityState = -1;
    int mPosition = -1;
    int mPreLayoutPosition = -1;
    C0004ac mScrapContainer = null;
    C0010ai mShadowedHolder = null;
    C0010ai mShadowingHolder = null;
    List mUnmodifiedPayloads = null;
    private int mWasImportantForAccessibilityBeforeHidden = 0;

    public C0010ai(View view) {
        if (view != null) {
            this.itemView = view;
            return;
        }
        throw new IllegalArgumentException("itemView may not be null");
    }

    private void createPayloadsIfNeeded() {
        if (this.mPayloads == null) {
            ArrayList arrayList = new ArrayList();
            this.mPayloads = arrayList;
            this.mUnmodifiedPayloads = Collections.unmodifiableList(arrayList);
        }
    }

    /* access modifiers changed from: package-private */
    public void addChangePayload(Object obj) {
        if (obj == null) {
            addFlags(1024);
        } else if ((1024 & this.mFlags) == 0) {
            createPayloadsIfNeeded();
            this.mPayloads.add(obj);
        }
    }

    /* access modifiers changed from: package-private */
    public void addFlags(int i) {
        this.mFlags = i | this.mFlags;
    }

    /* access modifiers changed from: package-private */
    public void clearOldPosition() {
        this.mOldPosition = -1;
        this.mPreLayoutPosition = -1;
    }

    /* access modifiers changed from: package-private */
    public void clearPayload() {
        List list = this.mPayloads;
        if (list != null) {
            list.clear();
        }
        this.mFlags &= -1025;
    }

    /* access modifiers changed from: package-private */
    public void clearReturnedFromScrapFlag() {
        this.mFlags &= -33;
    }

    /* access modifiers changed from: package-private */
    public void clearTmpDetachFlag() {
        this.mFlags &= -257;
    }

    /* access modifiers changed from: package-private */
    public boolean doesTransientStatePreventRecycling() {
        return (this.mFlags & 16) == 0 && ViewCompat.hasTransientState(this.itemView);
    }

    /* access modifiers changed from: package-private */
    public void flagRemovedAndOffsetPosition(int i, int i2, boolean z) {
        addFlags(8);
        offsetPosition(i2, z);
        this.mPosition = i;
    }

    public final int getAdapterPosition() {
        F f = this.mOwnerRecyclerView;
        if (f == null) {
            return -1;
        }
        return f.d(this);
    }

    public final long getItemId() {
        return this.mItemId;
    }

    public final int getItemViewType() {
        return this.mItemViewType;
    }

    public final int getLayoutPosition() {
        int i = this.mPreLayoutPosition;
        return i == -1 ? this.mPosition : i;
    }

    public final int getOldPosition() {
        return this.mOldPosition;
    }

    public final int getPosition() {
        int i = this.mPreLayoutPosition;
        return i == -1 ? this.mPosition : i;
    }

    /* access modifiers changed from: package-private */
    public List getUnmodifiedPayloads() {
        if ((this.mFlags & 1024) != 0) {
            return FULLUPDATE_PAYLOADS;
        }
        List list = this.mPayloads;
        return (list == null || list.size() == 0) ? FULLUPDATE_PAYLOADS : this.mUnmodifiedPayloads;
    }

    /* access modifiers changed from: package-private */
    public boolean hasAnyOfTheFlags(int i) {
        return (i & this.mFlags) != 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isAdapterPositionUnknown() {
        return (this.mFlags & 512) != 0 || isInvalid();
    }

    /* access modifiers changed from: package-private */
    public boolean isBound() {
        return (this.mFlags & 1) != 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isInvalid() {
        return (this.mFlags & 4) != 0;
    }

    public final boolean isRecyclable() {
        return (this.mFlags & 16) == 0 && !ViewCompat.hasTransientState(this.itemView);
    }

    /* access modifiers changed from: package-private */
    public boolean isRemoved() {
        return (this.mFlags & 8) != 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isScrap() {
        return this.mScrapContainer != null;
    }

    /* access modifiers changed from: package-private */
    public boolean isTmpDetached() {
        return (this.mFlags & 256) != 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isUpdated() {
        return (this.mFlags & 2) != 0;
    }

    /* access modifiers changed from: package-private */
    public boolean needsUpdate() {
        return (this.mFlags & 2) != 0;
    }

    /* access modifiers changed from: package-private */
    public void offsetPosition(int i, boolean z) {
        if (this.mOldPosition == -1) {
            this.mOldPosition = this.mPosition;
        }
        if (this.mPreLayoutPosition == -1) {
            this.mPreLayoutPosition = this.mPosition;
        }
        if (z) {
            this.mPreLayoutPosition += i;
        }
        this.mPosition += i;
        if (this.itemView.getLayoutParams() != null) {
            ((Y) this.itemView.getLayoutParams()).c = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void onEnteredHiddenState(F f) {
        int i = this.mPendingAccessibilityState;
        if (i == -1) {
            i = ViewCompat.getImportantForAccessibility(this.itemView);
        }
        this.mWasImportantForAccessibilityBeforeHidden = i;
        f.a(this, 4);
    }

    /* access modifiers changed from: package-private */
    public void onLeftHiddenState(F f) {
        f.a(this, this.mWasImportantForAccessibilityBeforeHidden);
        this.mWasImportantForAccessibilityBeforeHidden = 0;
    }

    /* access modifiers changed from: package-private */
    public void resetInternal() {
        this.mFlags = 0;
        this.mPosition = -1;
        this.mOldPosition = -1;
        this.mItemId = -1;
        this.mPreLayoutPosition = -1;
        this.mIsRecyclableCount = 0;
        this.mShadowedHolder = null;
        this.mShadowingHolder = null;
        clearPayload();
        this.mWasImportantForAccessibilityBeforeHidden = 0;
        this.mPendingAccessibilityState = -1;
        F.c(this);
    }

    /* access modifiers changed from: package-private */
    public void saveOldPosition() {
        if (this.mOldPosition == -1) {
            this.mOldPosition = this.mPosition;
        }
    }

    /* access modifiers changed from: package-private */
    public void setFlags(int i, int i2) {
        this.mFlags = (i & i2) | (this.mFlags & (i2 ^ -1));
    }

    public final void setIsRecyclable(boolean z) {
        int i = this.mIsRecyclableCount;
        int i2 = z ? i - 1 : i + 1;
        this.mIsRecyclableCount = i2;
        if (i2 < 0) {
            this.mIsRecyclableCount = 0;
            Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for ".concat(String.valueOf(this)));
        } else if (!z && i2 == 1) {
            this.mFlags |= 16;
        } else if (z && i2 == 0) {
            this.mFlags &= -17;
        }
    }

    /* access modifiers changed from: package-private */
    public void setScrapContainer(C0004ac acVar, boolean z) {
        this.mScrapContainer = acVar;
        this.mInChangeScrap = z;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldBeKeptAsChild() {
        return (this.mFlags & 16) != 0;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldIgnore() {
        return (this.mFlags & 128) != 0;
    }

    /* access modifiers changed from: package-private */
    public void stopIgnoring() {
        this.mFlags &= -129;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ViewHolder{" + Integer.toHexString(hashCode()) + " position=" + this.mPosition + " id=" + this.mItemId + ", oldPos=" + this.mOldPosition + ", pLpos:" + this.mPreLayoutPosition);
        if (isScrap()) {
            sb.append(" scrap ").append(this.mInChangeScrap ? "[changeScrap]" : "[attachedScrap]");
        }
        if (isInvalid()) {
            sb.append(" invalid");
        }
        if (!isBound()) {
            sb.append(" unbound");
        }
        if (needsUpdate()) {
            sb.append(" update");
        }
        if (isRemoved()) {
            sb.append(" removed");
        }
        if (shouldIgnore()) {
            sb.append(" ignored");
        }
        if (isTmpDetached()) {
            sb.append(" tmpDetached");
        }
        if (!isRecyclable()) {
            sb.append(" not recyclable(" + this.mIsRecyclableCount + ")");
        }
        if (isAdapterPositionUnknown()) {
            sb.append(" undefined adapter position");
        }
        if (this.itemView.getParent() == null) {
            sb.append(" no parent");
        }
        sb.append("}");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public void unScrap() {
        this.mScrapContainer.b(this);
    }

    /* access modifiers changed from: package-private */
    public boolean wasReturnedFromScrap() {
        return (this.mFlags & 32) != 0;
    }
}
