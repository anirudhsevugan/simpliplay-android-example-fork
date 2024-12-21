package com.google.android.exoplayer2;

import android.content.Context;

final class WakeLockManager {
    public WakeLockManager(Context context) {
        context.getApplicationContext().getSystemService("power");
    }
}
