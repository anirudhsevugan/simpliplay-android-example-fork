package com.google.android.exoplayer2;

import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.source.MediaPeriodId;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;

public final class ExoPlaybackException extends Exception implements Bundleable {
    public final int a;
    public final MediaPeriodId b;
    final boolean c;
    private String d;
    private int e;
    private Format f;
    private int g;
    private long h;
    private final Throwable i;

    static {
        Bundleable.Creator creator = ExoPlaybackException$$Lambda$0.a;
    }

    private ExoPlaybackException(int i2, Throwable th) {
        this(i2, th, (String) null, -1, (Format) null, 4, false);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private ExoPlaybackException(int r13, java.lang.Throwable r14, java.lang.String r15, int r16, com.google.android.exoplayer2.Format r17, int r18, boolean r19) {
        /*
            r12 = this;
            switch(r13) {
                case 0: goto L_0x0062;
                case 1: goto L_0x000f;
                case 2: goto L_0x0003;
                case 3: goto L_0x0009;
                default: goto L_0x0003;
            }
        L_0x0003:
            r4 = r15
            r5 = r16
            java.lang.String r0 = "Unexpected runtime error"
            goto L_0x0067
        L_0x0009:
            java.lang.String r0 = "Remote error"
            r4 = r15
            r5 = r16
            goto L_0x0067
        L_0x000f:
            java.lang.String r0 = java.lang.String.valueOf(r17)
            java.lang.String r1 = com.google.android.exoplayer2.C.a((int) r18)
            java.lang.String r2 = java.lang.String.valueOf(r15)
            int r2 = r2.length()
            int r2 = r2 + 53
            java.lang.String r3 = java.lang.String.valueOf(r0)
            int r3 = r3.length()
            int r2 = r2 + r3
            java.lang.String r3 = java.lang.String.valueOf(r1)
            int r3 = r3.length()
            int r2 = r2 + r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            r4 = r15
            java.lang.StringBuilder r2 = r3.append(r15)
            java.lang.String r3 = " error, index="
            java.lang.StringBuilder r2 = r2.append(r3)
            r5 = r16
            java.lang.StringBuilder r2 = r2.append(r5)
            java.lang.String r3 = ", format="
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r2 = ", format_supported="
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            goto L_0x0067
        L_0x0062:
            r4 = r15
            r5 = r16
            java.lang.String r0 = "Source error"
        L_0x0067:
            r1 = 0
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L_0x009c
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r2 = java.lang.String.valueOf(r0)
            int r2 = r2.length()
            int r2 = r2 + 2
            java.lang.String r3 = java.lang.String.valueOf(r1)
            int r3 = r3.length()
            int r2 = r2 + r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r2 = ": "
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
        L_0x009c:
            r1 = r0
            r8 = 0
            long r9 = android.os.SystemClock.elapsedRealtime()
            r0 = r12
            r2 = r14
            r3 = r13
            r4 = r15
            r5 = r16
            r6 = r17
            r7 = r18
            r11 = r19
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ExoPlaybackException.<init>(int, java.lang.Throwable, java.lang.String, int, com.google.android.exoplayer2.Format, int, boolean):void");
    }

    private ExoPlaybackException(String str, Throwable th, int i2, String str2, int i3, Format format, int i4, MediaPeriodId mediaPeriodId, long j, boolean z) {
        super(str, th);
        boolean z2 = true;
        if (z && i2 != 1) {
            z2 = false;
        }
        Assertions.a(z2);
        this.a = i2;
        this.i = th;
        this.d = str2;
        this.e = i3;
        this.f = format;
        this.g = i4;
        this.b = mediaPeriodId;
        this.h = j;
        this.c = z;
    }

    public static ExoPlaybackException a(IOException iOException) {
        return new ExoPlaybackException(0, iOException);
    }

    public static ExoPlaybackException a(Exception exc) {
        return new ExoPlaybackException(1, exc, (String) null, -1, (Format) null, 4, false);
    }

    public static ExoPlaybackException a(RuntimeException runtimeException) {
        return new ExoPlaybackException(2, runtimeException);
    }

    public static ExoPlaybackException a(Throwable th, String str, int i2, Format format, int i3, boolean z) {
        return new ExoPlaybackException(1, th, str, i2, format, format == null ? 4 : i3, z);
    }

    /* access modifiers changed from: package-private */
    public final ExoPlaybackException a(MediaPeriodId mediaPeriodId) {
        return new ExoPlaybackException((String) Util.a((Object) getMessage()), this.i, this.a, this.d, this.e, this.f, this.g, mediaPeriodId, this.h, this.c);
    }
}
