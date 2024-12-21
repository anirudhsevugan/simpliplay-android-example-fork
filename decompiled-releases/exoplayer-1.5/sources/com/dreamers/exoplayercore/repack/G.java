package com.dreamers.exoplayercore.repack;

final class G implements Runnable {
    private /* synthetic */ F a;

    G(F f) {
        this.a = f;
    }

    public final void run() {
        if (this.a.o && !this.a.isLayoutRequested()) {
            if (!this.a.n) {
                this.a.requestLayout();
            } else {
                this.a.c();
            }
        }
    }
}
