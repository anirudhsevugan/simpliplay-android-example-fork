package com.google.android.exoplayer2.ui;

import android.view.ViewGroup;
import java.util.List;

public interface AdViewProvider {
    List getAdOverlayInfos();

    ViewGroup getAdViewGroup();
}
