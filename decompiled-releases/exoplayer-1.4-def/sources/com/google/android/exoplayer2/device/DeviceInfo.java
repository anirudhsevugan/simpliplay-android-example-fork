package com.google.android.exoplayer2.device;

import com.google.android.exoplayer2.Bundleable;

public final class DeviceInfo implements Bundleable {
    private int a;
    private int b;

    static {
        new DeviceInfo(0, 0);
        Bundleable.Creator creator = DeviceInfo$$Lambda$0.a;
    }

    public DeviceInfo(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeviceInfo)) {
            return false;
        }
        DeviceInfo deviceInfo = (DeviceInfo) obj;
        return this.a == deviceInfo.a && this.b == deviceInfo.b;
    }

    public final int hashCode() {
        return ((this.a + 16337) * 31) + this.b;
    }
}
