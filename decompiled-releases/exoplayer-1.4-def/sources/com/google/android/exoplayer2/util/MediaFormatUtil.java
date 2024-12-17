package com.google.android.exoplayer2.util;

import android.media.MediaFormat;
import com.google.android.exoplayer2.video.ColorInfo;
import java.nio.ByteBuffer;
import java.util.List;

public final class MediaFormatUtil {
    public static void a(MediaFormat mediaFormat, ColorInfo colorInfo) {
        if (colorInfo != null) {
            a(mediaFormat, "color-transfer", colorInfo.c);
            a(mediaFormat, "color-standard", colorInfo.a);
            a(mediaFormat, "color-range", colorInfo.b);
            byte[] bArr = colorInfo.d;
            if (bArr != null) {
                mediaFormat.setByteBuffer("hdr-static-info", ByteBuffer.wrap(bArr));
            }
        }
    }

    public static void a(MediaFormat mediaFormat, String str, float f) {
        if (f != -1.0f) {
            mediaFormat.setFloat(str, f);
        }
    }

    public static void a(MediaFormat mediaFormat, String str, int i) {
        if (i != -1) {
            mediaFormat.setInteger(str, i);
        }
    }

    public static void a(MediaFormat mediaFormat, List list) {
        for (int i = 0; i < list.size(); i++) {
            mediaFormat.setByteBuffer(new StringBuilder(15).append("csd-").append(i).toString(), ByteBuffer.wrap((byte[]) list.get(i)));
        }
    }
}
