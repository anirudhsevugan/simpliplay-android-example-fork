package com.dreamers.exoplayerui.repack;

import android.content.DialogInterface;
import com.dreamers.exoplayerui.ExoplayerUi;

public final /* synthetic */ class g implements DialogInterface.OnDismissListener {
    private final ExoplayerUi a;

    public g(ExoplayerUi exoplayerUi) {
        this.a = exoplayerUi;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        ExoplayerUi.b(this.a);
    }
}
