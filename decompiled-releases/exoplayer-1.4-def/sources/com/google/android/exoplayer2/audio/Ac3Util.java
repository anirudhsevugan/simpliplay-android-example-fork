package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.appinventor.components.common.ComponentConstants;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;
import com.google.appinventor.components.runtime.util.ScreenDensityUtil;
import java.nio.ByteBuffer;

public final class Ac3Util {
    private static final int[] a = {1, 2, 3, 6};
    private static final int[] b = {48000, 44100, 32000};
    private static final int[] c = {24000, 22050, 16000};
    private static final int[] d = {2, 1, 2, 3, 3, 4, 4, 5};
    private static final int[] e = {32, 40, 48, 56, 64, 80, 96, 112, 128, ComponentConstants.TEXTBOX_PREFERRED_WIDTH, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE, 224, 256, ScreenDensityUtil.DEFAULT_NORMAL_SHORT_DIMENSION, 384, 448, 512, 576, 640};
    private static final int[] f = {69, 87, 104, 121, 139, 174, 208, 243, 278, 348, ErrorMessages.ERROR_NXT_CANNOT_DETECT_COLOR, 487, 557, 696, 835, 975, ErrorMessages.ERROR_WEB_UNABLE_TO_DELETE, 1253, 1393};

    public final class SyncFrameInfo {
        public final String a;
        public final int b;
        public final int c;
        public final int d;
        public final int e;

        private SyncFrameInfo(String str, int i, int i2, int i3, int i4) {
            this.a = str;
            this.c = i;
            this.b = i2;
            this.d = i3;
            this.e = i4;
        }

        /* synthetic */ SyncFrameInfo(String str, int i, int i2, int i3, int i4, byte b2) {
            this(str, i, i2, i3, i4);
        }
    }

    private static int a(int i, int i2) {
        int i3 = i2 / 2;
        if (i < 0 || i >= 3 || i2 < 0 || i3 >= 19) {
            return -1;
        }
        int i4 = b[i];
        if (i4 == 44100) {
            return (f[i3] + (i2 % 2)) * 2;
        }
        int i5 = e[i3];
        return i4 == 32000 ? i5 * 6 : i5 * 4;
    }

    public static int a(ByteBuffer byteBuffer) {
        int i = 3;
        if (!(((byteBuffer.get(byteBuffer.position() + 5) & 248) >> 3) > 10)) {
            return 1536;
        }
        if (((byteBuffer.get(byteBuffer.position() + 4) & Ev3Constants.Opcode.FILE) >> 6) != 3) {
            i = (byteBuffer.get(byteBuffer.position() + 4) & Ev3Constants.Opcode.MOVE8_8) >> 4;
        }
        return a[i] << 8;
    }

    public static int a(ByteBuffer byteBuffer, int i) {
        return 40 << ((byteBuffer.get((byteBuffer.position() + i) + ((byteBuffer.get((byteBuffer.position() + i) + 7) & Ev3Constants.Opcode.TST) == 187 ? 9 : 8)) >> 4) & 7);
    }

    public static int a(byte[] bArr) {
        if (bArr.length < 6) {
            return -1;
        }
        if (((bArr[5] & 248) >> 3) > 10) {
            return (((bArr[3] & Ev3Constants.Opcode.TST) | ((bArr[2] & 7) << 8)) + 1) << 1;
        }
        byte b2 = bArr[4];
        return a((b2 & Ev3Constants.Opcode.FILE) >> 6, (int) b2 & Ev3Constants.Opcode.MOVEF_F);
    }

    public static Format a(ParsableByteArray parsableByteArray, String str, String str2, DrmInitData drmInitData) {
        int i = b[(parsableByteArray.c() & FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE) >> 6];
        int c2 = parsableByteArray.c();
        int i2 = d[(c2 & 56) >> 3];
        if ((c2 & 4) != 0) {
            i2++;
        }
        Format.Builder builder = new Format.Builder();
        builder.a = str;
        builder.k = "audio/ac3";
        builder.x = i2;
        builder.y = i;
        builder.n = drmInitData;
        builder.c = str2;
        return builder.a();
    }

    public static SyncFrameInfo a(ParsableBitArray parsableBitArray) {
        int i;
        int i2;
        int i3;
        int i4;
        String str;
        int i5;
        int i6;
        int i7;
        ParsableBitArray parsableBitArray2 = parsableBitArray;
        int b2 = parsableBitArray.b();
        parsableBitArray2.b(40);
        boolean z = parsableBitArray2.c(5) > 10;
        parsableBitArray2.a(b2);
        int i8 = -1;
        if (z) {
            parsableBitArray2.b(16);
            switch (parsableBitArray2.c(2)) {
                case 0:
                    i8 = 0;
                    break;
                case 1:
                    i8 = 1;
                    break;
                case 2:
                    i8 = 2;
                    break;
            }
            parsableBitArray2.b(3);
            int c2 = (parsableBitArray2.c(11) + 1) << 1;
            int c3 = parsableBitArray2.c(2);
            if (c3 == 3) {
                i5 = c[parsableBitArray2.c(2)];
                i7 = 3;
                i6 = 6;
            } else {
                i7 = parsableBitArray2.c(2);
                i6 = a[i7];
                i5 = b[c3];
            }
            int i9 = i6 << 8;
            int c4 = parsableBitArray2.c(3);
            boolean e2 = parsableBitArray.e();
            int i10 = d[c4] + (e2 ? 1 : 0);
            parsableBitArray2.b(10);
            if (parsableBitArray.e()) {
                parsableBitArray2.b(8);
            }
            if (c4 == 0) {
                parsableBitArray2.b(5);
                if (parsableBitArray.e()) {
                    parsableBitArray2.b(8);
                }
            }
            if (i8 == 1 && parsableBitArray.e()) {
                parsableBitArray2.b(16);
            }
            if (parsableBitArray.e()) {
                if (c4 > 2) {
                    parsableBitArray2.b(2);
                }
                if ((c4 & 1) != 0 && c4 > 2) {
                    parsableBitArray2.b(6);
                }
                if ((c4 & 4) != 0) {
                    parsableBitArray2.b(6);
                }
                if (e2 && parsableBitArray.e()) {
                    parsableBitArray2.b(5);
                }
                if (i8 == 0) {
                    if (parsableBitArray.e()) {
                        parsableBitArray2.b(6);
                    }
                    if (c4 == 0 && parsableBitArray.e()) {
                        parsableBitArray2.b(6);
                    }
                    if (parsableBitArray.e()) {
                        parsableBitArray2.b(6);
                    }
                    int c5 = parsableBitArray2.c(2);
                    if (c5 == 1) {
                        parsableBitArray2.b(5);
                    } else if (c5 == 2) {
                        parsableBitArray2.b(12);
                    } else if (c5 == 3) {
                        int c6 = parsableBitArray2.c(5);
                        if (parsableBitArray.e()) {
                            parsableBitArray2.b(5);
                            if (parsableBitArray.e()) {
                                parsableBitArray2.b(4);
                            }
                            if (parsableBitArray.e()) {
                                parsableBitArray2.b(4);
                            }
                            if (parsableBitArray.e()) {
                                parsableBitArray2.b(4);
                            }
                            if (parsableBitArray.e()) {
                                parsableBitArray2.b(4);
                            }
                            if (parsableBitArray.e()) {
                                parsableBitArray2.b(4);
                            }
                            if (parsableBitArray.e()) {
                                parsableBitArray2.b(4);
                            }
                            if (parsableBitArray.e()) {
                                parsableBitArray2.b(4);
                            }
                            if (parsableBitArray.e()) {
                                if (parsableBitArray.e()) {
                                    parsableBitArray2.b(4);
                                }
                                if (parsableBitArray.e()) {
                                    parsableBitArray2.b(4);
                                }
                            }
                        }
                        if (parsableBitArray.e()) {
                            parsableBitArray2.b(5);
                            if (parsableBitArray.e()) {
                                parsableBitArray2.b(7);
                                if (parsableBitArray.e()) {
                                    parsableBitArray2.b(8);
                                }
                            }
                        }
                        parsableBitArray2.b((c6 + 2) * 8);
                        parsableBitArray.f();
                    }
                    if (c4 < 2) {
                        if (parsableBitArray.e()) {
                            parsableBitArray2.b(14);
                        }
                        if (c4 == 0 && parsableBitArray.e()) {
                            parsableBitArray2.b(14);
                        }
                    }
                    if (parsableBitArray.e()) {
                        if (i7 == 0) {
                            parsableBitArray2.b(5);
                        } else {
                            for (int i11 = 0; i11 < i6; i11++) {
                                if (parsableBitArray.e()) {
                                    parsableBitArray2.b(5);
                                }
                            }
                        }
                    }
                }
            }
            if (parsableBitArray.e()) {
                parsableBitArray2.b(5);
                if (c4 == 2) {
                    parsableBitArray2.b(4);
                }
                if (c4 >= 6) {
                    parsableBitArray2.b(2);
                }
                if (parsableBitArray.e()) {
                    parsableBitArray2.b(8);
                }
                if (c4 == 0 && parsableBitArray.e()) {
                    parsableBitArray2.b(8);
                }
                if (c3 < 3) {
                    parsableBitArray.d();
                }
            }
            if (i8 == 0 && i7 != 3) {
                parsableBitArray.d();
            }
            if (i8 == 2 && (i7 == 3 || parsableBitArray.e())) {
                parsableBitArray2.b(6);
            }
            str = (parsableBitArray.e() && parsableBitArray2.c(6) == 1 && parsableBitArray2.c(8) == 1) ? "audio/eac3-joc" : "audio/eac3";
            i2 = c2;
            i3 = i5;
            i = i9;
            i4 = i10;
        } else {
            parsableBitArray2.b(32);
            int c7 = parsableBitArray2.c(2);
            String str2 = c7 == 3 ? null : "audio/ac3";
            int a2 = a(c7, parsableBitArray2.c(6));
            parsableBitArray2.b(8);
            int c8 = parsableBitArray2.c(3);
            if (!((c8 & 1) == 0 || c8 == 1)) {
                parsableBitArray2.b(2);
            }
            if ((c8 & 4) != 0) {
                parsableBitArray2.b(2);
            }
            if (c8 == 2) {
                parsableBitArray2.b(2);
            }
            if (c7 < 3) {
                i8 = b[c7];
            }
            i3 = i8;
            str = str2;
            i2 = a2;
            i4 = d[c8] + (parsableBitArray.e() ? 1 : 0);
            i = 1536;
        }
        return new SyncFrameInfo(str, i4, i3, i2, i, (byte) 0);
    }

    public static int b(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        int limit = byteBuffer.limit() - 10;
        for (int i = position; i <= limit; i++) {
            if ((Util.a(byteBuffer, i + 4) & -2) == -126718022) {
                return i - position;
            }
        }
        return -1;
    }

    public static int b(byte[] bArr) {
        boolean z = false;
        if (bArr[4] == -8 && bArr[5] == 114 && bArr[6] == 111) {
            byte b2 = bArr[7];
            if ((b2 & 254) == 186) {
                if ((b2 & Ev3Constants.Opcode.TST) == 187) {
                    z = true;
                }
                return 40 << ((bArr[z ? (char) 9 : 8] >> 4) & 7);
            }
        }
        return 0;
    }

    public static Format b(ParsableByteArray parsableByteArray, String str, String str2, DrmInitData drmInitData) {
        parsableByteArray.e(2);
        int i = b[(parsableByteArray.c() & FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE) >> 6];
        int c2 = parsableByteArray.c();
        int i2 = d[(c2 & 14) >> 1];
        if ((c2 & 1) != 0) {
            i2++;
        }
        if (((parsableByteArray.c() & 30) >> 1) > 0 && (2 & parsableByteArray.c()) != 0) {
            i2 += 2;
        }
        String str3 = (parsableByteArray.a() <= 0 || (parsableByteArray.c() & 1) == 0) ? "audio/eac3" : "audio/eac3-joc";
        Format.Builder builder = new Format.Builder();
        builder.a = str;
        builder.k = str3;
        builder.x = i2;
        builder.y = i;
        builder.n = drmInitData;
        builder.c = str2;
        return builder.a();
    }
}
