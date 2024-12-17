package com.google.android.exoplayer2.util;

import com.google.android.exoplayer2.util.NetworkTypeObserver;

public final /* synthetic */ class NetworkTypeObserver$$Lambda$0 implements Runnable {
    private final NetworkTypeObserver a;
    private final NetworkTypeObserver.Listener b;

    public NetworkTypeObserver$$Lambda$0(NetworkTypeObserver networkTypeObserver, NetworkTypeObserver.Listener listener) {
        this.a = networkTypeObserver;
        this.b = listener;
    }

    public final void run() {
        this.b.a(this.a.a());
    }
}
