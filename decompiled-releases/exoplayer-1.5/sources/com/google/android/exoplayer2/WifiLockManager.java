package com.google.android.exoplayer2;

import android.content.Context;

final class WifiLockManager {
    public WifiLockManager(Context context) {
        context.getApplicationContext().getSystemService("wifi");
    }
}
