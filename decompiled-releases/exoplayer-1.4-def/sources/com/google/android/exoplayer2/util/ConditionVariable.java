package com.google.android.exoplayer2.util;

public class ConditionVariable {
    private boolean a;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ConditionVariable() {
        this((byte) 0);
        Clock clock = Clock.a;
    }

    private ConditionVariable(byte b) {
    }

    public final synchronized boolean a() {
        if (this.a) {
            return false;
        }
        this.a = true;
        notifyAll();
        return true;
    }

    public final synchronized boolean b() {
        boolean z;
        z = this.a;
        this.a = false;
        return z;
    }

    public final synchronized void c() {
        while (!this.a) {
            wait();
        }
    }

    public final synchronized void d() {
        boolean z = false;
        while (!this.a) {
            try {
                wait();
            } catch (InterruptedException e) {
                z = true;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
    }

    public final synchronized boolean e() {
        return this.a;
    }
}
