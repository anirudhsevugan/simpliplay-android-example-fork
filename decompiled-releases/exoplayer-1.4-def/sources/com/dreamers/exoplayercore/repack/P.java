package com.dreamers.exoplayercore.repack;

import android.widget.EdgeEffect;

public final class P {
    protected static EdgeEffect a(F f) {
        return new EdgeEffect(f.getContext());
    }

    public static boolean a(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }
}
