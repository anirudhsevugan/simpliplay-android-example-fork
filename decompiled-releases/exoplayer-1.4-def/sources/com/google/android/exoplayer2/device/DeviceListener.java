package com.google.android.exoplayer2.device;

public interface DeviceListener {
    void onDeviceInfoChanged(DeviceInfo deviceInfo);

    void onDeviceVolumeChanged(int i, boolean z);
}
