package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableBitArray;
import gnu.expr.Declaration;

public final class AacUtil {
    private static final int[] a = {96000, 88200, 64000, 48000, 44100, 32000, 24000, 22050, 16000, 12000, 11025, 8000, 7350};
    private static final int[] b = {0, 1, 2, 3, 4, 5, 6, 8, -1, -1, -1, 7, 8, -1, 8, -1};

    public final class Config {
        public final int a;
        public final int b;
        public final String c;

        private Config(int i, int i2, String str) {
            this.a = i;
            this.b = i2;
            this.c = str;
        }

        /* synthetic */ Config(int i, int i2, String str, byte b2) {
            this(i, i2, str);
        }
    }

    public static int a(int i) {
        switch (i) {
            case 2:
                return 10;
            case 5:
                return 11;
            case 22:
                return Declaration.MODULE_REFERENCE;
            case 23:
                return 15;
            case 29:
                return 12;
            case 42:
                return 16;
            default:
                return 0;
        }
    }

    private static int a(ParsableBitArray parsableBitArray) {
        int c = parsableBitArray.c(5);
        return c == 31 ? parsableBitArray.c(6) + 32 : c;
    }

    public static Config a(ParsableBitArray parsableBitArray, boolean z) {
        int a2 = a(parsableBitArray);
        int b2 = b(parsableBitArray);
        int c = parsableBitArray.c(4);
        String sb = new StringBuilder(19).append("mp4a.40.").append(a2).toString();
        if (a2 == 5 || a2 == 29) {
            b2 = b(parsableBitArray);
            a2 = a(parsableBitArray);
            if (a2 == 22) {
                c = parsableBitArray.c(4);
            }
        }
        if (z) {
            switch (a2) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 6:
                case 7:
                case 17:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                    a(parsableBitArray, a2, c);
                    switch (a2) {
                        case 17:
                        case 19:
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                            int c2 = parsableBitArray.c(2);
                            if (c2 == 2 || c2 == 3) {
                                throw new ParserException(new StringBuilder(33).append("Unsupported epConfig: ").append(c2).toString());
                            }
                    }
                    break;
                default:
                    throw new ParserException(new StringBuilder(42).append("Unsupported audio object type: ").append(a2).toString());
            }
        }
        int i = b[c];
        if (i != -1) {
            return new Config(b2, i, sb, (byte) 0);
        }
        throw new ParserException();
    }

    public static Config a(byte[] bArr) {
        return a(new ParsableBitArray(bArr), false);
    }

    private static void a(ParsableBitArray parsableBitArray, int i, int i2) {
        if (parsableBitArray.e()) {
            Log.c("AacUtil", "Unexpected frameLengthFlag = 1");
        }
        if (parsableBitArray.e()) {
            parsableBitArray.b(14);
        }
        boolean e = parsableBitArray.e();
        if (i2 != 0) {
            if (i == 6 || i == 20) {
                parsableBitArray.b(3);
            }
            if (e) {
                if (i == 22) {
                    parsableBitArray.b(16);
                }
                if (i == 17 || i == 19 || i == 20 || i == 23) {
                    parsableBitArray.b(3);
                }
                parsableBitArray.b(1);
                return;
            }
            return;
        }
        throw new UnsupportedOperationException();
    }

    public static byte[] a(int i, int i2, int i3) {
        return new byte[]{(byte) (((i << 3) & 248) | ((i2 >> 1) & 7)), (byte) (((i2 << 7) & 128) | ((i3 << 3) & 120))};
    }

    private static int b(ParsableBitArray parsableBitArray) {
        int c = parsableBitArray.c(4);
        if (c == 15) {
            return parsableBitArray.c(24);
        }
        if (c < 13) {
            return a[c];
        }
        throw new ParserException();
    }
}
