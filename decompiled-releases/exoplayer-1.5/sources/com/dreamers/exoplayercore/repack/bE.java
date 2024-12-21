package com.dreamers.exoplayercore.repack;

import androidx.appcompat.widget.ActivityChooserView;

public abstract class bE {
    bE() {
    }

    static int a(int i, int i2) {
        if (i2 >= 0) {
            int i3 = i + (i >> 1) + 1;
            if (i3 < i2) {
                i3 = Integer.highestOneBit(i2 - 1) << 1;
            }
            return i3 < 0 ? ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED : i3;
        }
        throw new AssertionError("cannot store more than MAX_VALUE elements");
    }
}
