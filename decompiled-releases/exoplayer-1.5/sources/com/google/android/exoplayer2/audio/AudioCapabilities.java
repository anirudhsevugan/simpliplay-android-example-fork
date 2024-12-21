package com.google.android.exoplayer2.audio;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public final class AudioCapabilities {
    private static AudioCapabilities b = new AudioCapabilities(new int[]{2}, 8);
    private static final AudioCapabilities c = new AudioCapabilities(new int[]{2, 5, 6}, 8);
    final int a;
    private final int[] d;

    private AudioCapabilities(int[] iArr, int i) {
        if (iArr != null) {
            int[] copyOf = Arrays.copyOf(iArr, iArr.length);
            this.d = copyOf;
            Arrays.sort(copyOf);
        } else {
            this.d = new int[0];
        }
        this.a = i;
    }

    public static AudioCapabilities a(Context context) {
        Intent registerReceiver = context.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.media.action.HDMI_AUDIO_PLUG"));
        return (!(Util.a >= 17 && ("Amazon".equals(Util.c) || "Xiaomi".equals(Util.c))) || Settings.Global.getInt(context.getContentResolver(), "external_surround_sound_enabled", 0) != 1) ? (registerReceiver == null || registerReceiver.getIntExtra("android.media.extra.AUDIO_PLUG_STATE", 0) == 0) ? b : new AudioCapabilities(registerReceiver.getIntArrayExtra("android.media.extra.ENCODINGS"), registerReceiver.getIntExtra("android.media.extra.MAX_CHANNEL_COUNT", 8)) : c;
    }

    public final boolean a(int i) {
        return Arrays.binarySearch(this.d, i) >= 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AudioCapabilities)) {
            return false;
        }
        AudioCapabilities audioCapabilities = (AudioCapabilities) obj;
        return Arrays.equals(this.d, audioCapabilities.d) && this.a == audioCapabilities.a;
    }

    public final int hashCode() {
        return this.a + (Arrays.hashCode(this.d) * 31);
    }

    public final String toString() {
        int i = this.a;
        String arrays = Arrays.toString(this.d);
        return new StringBuilder(String.valueOf(arrays).length() + 67).append("AudioCapabilities[maxChannelCount=").append(i).append(", supportedEncodings=").append(arrays).append("]").toString();
    }
}
