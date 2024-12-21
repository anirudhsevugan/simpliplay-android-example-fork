package com.dreamers.exoplayercore.repack;

final class H implements Runnable {
    private /* synthetic */ F a;

    H(F f) {
        this.a = f;
    }

    public final void run() {
        if (this.a.r != null) {
            this.a.r.a();
        }
        this.a.x = false;
    }
}
