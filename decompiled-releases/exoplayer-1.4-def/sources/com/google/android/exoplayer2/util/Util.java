package com.google.android.exoplayer2.util;

import android.app.UiModeManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import androidx.core.location.LocationRequestCompat;
import com.dreamers.exoplayercore.repack.C0000a;
import com.dreamers.exoplayercore.repack.aC;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.appinventor.components.common.ComponentConstants;
import com.google.appinventor.components.common.YaVersion;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;
import gnu.expr.Declaration;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import kawa.Telnet;

public final class Util {
    public static final int a;
    public static final String b;
    public static final String c;
    public static final String d;
    public static final String e;
    public static final byte[] f = new byte[0];
    private static final Pattern g = Pattern.compile("(\\d\\d\\d\\d)\\-(\\d\\d)\\-(\\d\\d)[Tt](\\d\\d):(\\d\\d):(\\d\\d)([\\.,](\\d+))?([Zz]|((\\+|\\-)(\\d?\\d):?(\\d\\d)))?");
    private static final Pattern h = Pattern.compile("^(-)?P(([0-9]*)Y)?(([0-9]*)M)?(([0-9]*)D)?(T(([0-9]*)H)?(([0-9]*)M)?(([0-9.]*)S)?)?$");
    private static final Pattern i = Pattern.compile(".*\\.isml?(?:/(manifest(.*))?)?");
    private static HashMap j;
    private static final String[] k = {"alb", "sq", "arm", "hy", "baq", "eu", "bur", "my", "tib", "bo", "chi", "zh", "cze", "cs", "dut", "nl", "ger", "de", "gre", "el", "fre", "fr", "geo", "ka", "ice", "is", "mac", "mk", "mao", "mi", "may", "ms", "per", "fa", "rum", "ro", "scc", "hbs-srp", "slo", "sk", "wel", "cy", "id", "ms-ind", "iw", "he", "heb", "he", "ji", "yi", "in", "ms-ind", "ind", "ms-ind", "nb", "no-nob", "nob", "no-nob", "nn", "no-nno", "nno", "no-nno", "tw", "ak-twi", "twi", "ak-twi", "bs", "hbs-bos", "bos", "hbs-bos", "hr", "hbs-hrv", "hrv", "hbs-hrv", "sr", "hbs-srp", "srp", "hbs-srp", "cmn", "zh-cmn", "hak", "zh-hak", "nan", "zh-nan", "hsn", "zh-hsn"};
    private static final String[] l = {"i-lux", "lb", "i-hak", "zh-hak", "i-navajo", "nv", "no-bok", "no-nob", "no-nyn", "no-nno", "zh-guoyu", "zh-cmn", "zh-hakka", "zh-hak", "zh-min-nan", "zh-nan", "zh-xiang", "zh-hsn"};
    private static final int[] m = {0, 79764919, 159529838, 222504665, 319059676, 398814059, 445009330, 507990021, 638119352, 583659535, 797628118, 726387553, 890018660, 835552979, 1015980042, 944750013, 1276238704, 1221641927, 1167319070, 1095957929, 1595256236, 1540665371, 1452775106, 1381403509, 1780037320, 1859660671, 1671105958, 1733955601, 2031960084, 2111593891, 1889500026, 1952343757, -1742489888, -1662866601, -1851683442, -1788833735, -1960329156, -1880695413, -2103051438, -2040207643, -1104454824, -1159051537, -1213636554, -1284997759, -1389417084, -1444007885, -1532160278, -1603531939, -734892656, -789352409, -575645954, -646886583, -952755380, -1007220997, -827056094, -898286187, -231047128, -151282273, -71779514, -8804623, -515967244, -436212925, -390279782, -327299027, 881225847, 809987520, 1023691545, 969234094, 662832811, 591600412, 771767749, 717299826, 311336399, 374308984, 453813921, 533576470, 25881363, 88864420, 134795389, 214552010, 2023205639, 2086057648, 1897238633, 1976864222, 1804852699, 1867694188, 1645340341, 1724971778, 1587496639, 1516133128, 1461550545, 1406951526, 1302016099, 1230646740, 1142491917, 1087903418, -1398421865, -1469785312, -1524105735, -1578704818, -1079922613, -1151291908, -1239184603, -1293773166, -1968362705, -1905510760, -2094067647, -2014441994, -1716953613, -1654112188, -1876203875, -1796572374, -525066777, -462094256, -382327159, -302564546, -206542021, -143559028, -97365931, -17609246, -960696225, -1031934488, -817968335, -872425850, -709327229, -780559564, -600130067, -654598054, 1762451694, 1842216281, 1619975040, 1682949687, 2047383090, 2127137669, 1938468188, 2001449195, 1325665622, 1271206113, 1183200824, 1111960463, 1543535498, 1489069629, 1434599652, 1363369299, 622672798, 568075817, 748617968, 677256519, 907627842, 853037301, 1067152940, 995781531, 51762726, 131386257, 177728840, 240578815, 269590778, 349224269, 429104020, 491947555, -248556018, -168932423, -122852000, -60002089, -500490030, -420856475, -341238852, -278395381, -685261898, -739858943, -559578920, -630940305, -1004286614, -1058877219, -845023740, -916395085, -1119974018, -1174433591, -1262701040, -1333941337, -1371866206, -1426332139, -1481064244, -1552294533, -1690935098, -1611170447, -1833673816, -1770699233, -2009983462, -1930228819, -2119160460, -2056179517, 1569362073, 1498123566, 1409854455, 1355396672, 1317987909, 1246755826, 1192025387, 1137557660, 2072149281, 2135122070, 1912620623, 1992383480, 1753615357, 1816598090, 1627664531, 1707420964, 295390185, 358241886, 404320391, 483945776, 43990325, 106832002, 186451547, 266083308, 932423249, 861060070, 1041341759, 986742920, 613929101, 542559546, 756411363, 701822548, -978770311, -1050133554, -869589737, -924188512, -693284699, -764654318, -550540341, -605129092, -475935807, -413084042, -366743377, -287118056, -257573603, -194731862, -114850189, -35218492, -1984365303, -1921392450, -2143631769, -2063868976, -1698919467, -1635936670, -1824608069, -1744851700, -1347415887, -1418654458, -1506661409, -1561119128, -1129027987, -1200260134, -1254728445, -1309196108};
    private static final int[] n = {0, 7, 14, 9, 28, 27, 18, 21, 56, 63, 54, 49, 36, 35, 42, 45, 112, 119, 126, 121, 108, 107, 98, 101, 72, 79, 70, 65, 84, 83, 90, 93, 224, YaVersion.YOUNG_ANDROID_VERSION, 238, 233, Telnet.WONT, Telnet.WILL, LispEscapeFormat.ESCAPE_ALL, 245, 216, 223, 214, 209, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_DURATION, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_FULLSCREEN, ErrorMessages.ERROR_NO_CAMERA_PERMISSION, 205, 144, 151, 158, 153, 140, 139, 130, 133, 168, 175, 166, 161, 180, 179, 186, FullScreenVideoUtil.FULLSCREEN_VIDEO_DIALOG_FLAG, 199, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE, ErrorMessages.ERROR_CAMERA_NO_IMAGE_RETURNED, 206, 219, 220, 213, 210, 255, 248, LispEscapeFormat.ESCAPE_NORMAL, 246, 227, 228, 237, 234, 183, 176, 185, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SEEK, 171, 172, 165, 162, 143, 136, 129, 134, 147, 148, 157, 154, 39, 32, 41, 46, 59, 60, 53, 50, 31, 24, 17, 22, 3, 4, 13, 10, 87, 80, 89, 94, 75, 76, 69, 66, 111, 104, 97, 102, 115, 116, 125, 122, 137, 142, 135, 128, 149, 146, 155, 156, 177, 182, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PLAY, 184, 173, 170, 163, 164, 249, Telnet.DONT, 247, 240, 229, 226, 235, 236, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_STOP, 198, 207, 200, 221, 218, 211, 212, 105, 110, 103, 96, 117, 114, 123, 124, 81, 86, 95, 88, 77, 74, 67, 68, 25, 30, 23, 16, 5, 2, 11, 12, 33, 38, 47, 40, 61, 58, 51, 52, 78, 73, 64, 71, 82, 85, 92, 91, 118, 113, 120, 127, 106, 109, 100, 99, 62, 57, 48, 55, 34, 37, 44, 43, 6, 1, 8, 15, 26, 29, 20, 19, 174, 169, ComponentConstants.TEXTBOX_PREFERRED_WIDTH, 167, 178, 181, 188, 187, 150, 145, 152, 159, 138, 141, 132, 131, 222, 217, 208, 215, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SOURCE, 197, 204, 203, 230, 225, 232, 239, 250, Telnet.DO, 244, 243};

    static {
        int i2 = "S".equals(Build.VERSION.CODENAME) ? 31 : "R".equals(Build.VERSION.CODENAME) ? 30 : Build.VERSION.SDK_INT;
        a = i2;
        String str = Build.DEVICE;
        b = str;
        String str2 = Build.MANUFACTURER;
        c = str2;
        String str3 = Build.MODEL;
        d = str3;
        e = new StringBuilder(String.valueOf(str).length() + 17 + String.valueOf(str3).length() + String.valueOf(str2).length()).append(str).append(", ").append(str3).append(", ").append(str2).append(", ").append(i2).toString();
        Pattern.compile("%([A-Fa-f0-9]{2})");
    }

    public static float a(float f2, float f3, float f4) {
        return Math.max(f3, Math.min(f2, f4));
    }

    public static int a(int i2, int i3) {
        return ((i2 + i3) - 1) / i3;
    }

    public static int a(int i2, int i3, int i4) {
        return Math.max(i3, Math.min(i2, i4));
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int a(android.net.Uri r6, java.lang.String r7) {
        /*
            r0 = 4
            r1 = 3
            r2 = 1
            r3 = 0
            r4 = 2
            if (r7 != 0) goto L_0x0058
            java.lang.String r7 = r6.getScheme()
            if (r7 == 0) goto L_0x0016
            java.lang.String r5 = "rtsp"
            boolean r7 = com.dreamers.exoplayercore.repack.C0000a.a((java.lang.CharSequence) r5, (java.lang.CharSequence) r7)
            if (r7 == 0) goto L_0x0016
            return r1
        L_0x0016:
            java.lang.String r6 = r6.getPath()
            if (r6 == 0) goto L_0x0057
            java.lang.String r6 = com.dreamers.exoplayercore.repack.C0000a.a((java.lang.String) r6)
            java.lang.String r7 = ".mpd"
            boolean r7 = r6.endsWith(r7)
            if (r7 == 0) goto L_0x0029
            return r3
        L_0x0029:
            java.lang.String r7 = ".m3u8"
            boolean r7 = r6.endsWith(r7)
            if (r7 == 0) goto L_0x0032
            return r4
        L_0x0032:
            java.util.regex.Pattern r7 = i
            java.util.regex.Matcher r6 = r7.matcher(r6)
            boolean r7 = r6.matches()
            if (r7 == 0) goto L_0x0057
            java.lang.String r6 = r6.group(r4)
            if (r6 == 0) goto L_0x0056
            java.lang.String r7 = "format=mpd-time-csf"
            boolean r7 = r6.contains(r7)
            if (r7 == 0) goto L_0x004d
            return r3
        L_0x004d:
            java.lang.String r7 = "format=m3u8-aapl"
            boolean r6 = r6.contains(r7)
            if (r6 == 0) goto L_0x0056
            return r4
        L_0x0056:
            return r2
        L_0x0057:
            return r0
        L_0x0058:
            int r6 = r7.hashCode()
            switch(r6) {
                case -979127466: goto L_0x007e;
                case -156749520: goto L_0x0074;
                case 64194685: goto L_0x006a;
                case 1154777587: goto L_0x0060;
                default: goto L_0x005f;
            }
        L_0x005f:
            goto L_0x0088
        L_0x0060:
            java.lang.String r6 = "application/x-rtsp"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L_0x0088
            r6 = 3
            goto L_0x0089
        L_0x006a:
            java.lang.String r6 = "application/dash+xml"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L_0x0088
            r6 = 0
            goto L_0x0089
        L_0x0074:
            java.lang.String r6 = "application/vnd.ms-sstr+xml"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L_0x0088
            r6 = 2
            goto L_0x0089
        L_0x007e:
            java.lang.String r6 = "application/x-mpegURL"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L_0x0088
            r6 = 1
            goto L_0x0089
        L_0x0088:
            r6 = -1
        L_0x0089:
            switch(r6) {
                case 0: goto L_0x0090;
                case 1: goto L_0x008f;
                case 2: goto L_0x008e;
                case 3: goto L_0x008d;
                default: goto L_0x008c;
            }
        L_0x008c:
            return r0
        L_0x008d:
            return r1
        L_0x008e:
            return r2
        L_0x008f:
            return r4
        L_0x0090:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.util.Util.a(android.net.Uri, java.lang.String):int");
    }

    public static int a(LongArray longArray, long j2) {
        int i2 = longArray.a - 1;
        int i3 = 0;
        while (i3 <= i2) {
            int i4 = (i3 + i2) >>> 1;
            if (longArray.a(i4) < j2) {
                i3 = i4 + 1;
            } else {
                i2 = i4 - 1;
            }
        }
        int i5 = i2 + 1;
        if (i5 < longArray.a && longArray.a(i5) == j2) {
            return i5;
        }
        if (i2 == -1) {
            return 0;
        }
        return i2;
    }

    public static int a(String str, int i2) {
        int i3 = 0;
        for (String i4 : f(str)) {
            if (i2 == MimeTypes.i(i4)) {
                i3++;
            }
        }
        return i3;
    }

    public static int a(ByteBuffer byteBuffer, int i2) {
        int i3 = byteBuffer.getInt(i2);
        return byteBuffer.order() == ByteOrder.BIG_ENDIAN ? i3 : Integer.reverseBytes(i3);
    }

    public static int a(List list, Comparable comparable) {
        int binarySearch = Collections.binarySearch(list, comparable);
        if (binarySearch < 0) {
            return binarySearch ^ -1;
        }
        int size = list.size();
        do {
            binarySearch++;
            if (binarySearch >= size || ((Comparable) list.get(binarySearch)).compareTo(comparable) != 0) {
            }
            binarySearch++;
            break;
        } while (((Comparable) list.get(binarySearch)).compareTo(comparable) != 0);
        return binarySearch;
    }

    public static int a(List list, Comparable comparable, boolean z) {
        int i2;
        int binarySearch = Collections.binarySearch(list, comparable);
        if (binarySearch < 0) {
            i2 = -(binarySearch + 2);
        } else {
            do {
                binarySearch--;
                if (binarySearch < 0 || ((Comparable) list.get(binarySearch)).compareTo(comparable) != 0) {
                    i2 = binarySearch + 1;
                }
                binarySearch--;
                break;
            } while (((Comparable) list.get(binarySearch)).compareTo(comparable) != 0);
            i2 = binarySearch + 1;
        }
        return z ? Math.max(0, i2) : i2;
    }

    public static int a(byte[] bArr, int i2, int i3, int i4) {
        while (i2 < i3) {
            i4 = n[i4 ^ (bArr[i2] & Ev3Constants.Opcode.TST)];
            i2++;
        }
        return i4;
    }

    public static int a(int[] iArr, int i2) {
        for (int i3 = 0; i3 < iArr.length; i3++) {
            if (iArr[i3] == i2) {
                return i3;
            }
        }
        return -1;
    }

    public static int a(long[] jArr, long j2, boolean z) {
        int i2;
        int binarySearch = Arrays.binarySearch(jArr, j2);
        if (binarySearch < 0) {
            i2 = -(binarySearch + 2);
        } else {
            do {
                binarySearch--;
                if (binarySearch < 0 || jArr[binarySearch] != j2) {
                    i2 = binarySearch + 1;
                }
                binarySearch--;
                break;
            } while (jArr[binarySearch] != j2);
            i2 = binarySearch + 1;
        }
        return z ? Math.max(0, i2) : i2;
    }

    public static long a(long j2) {
        return j2 == -9223372036854775807L ? System.currentTimeMillis() : SystemClock.elapsedRealtime() + j2;
    }

    public static long a(long j2, float f2) {
        if (f2 == 1.0f) {
            return j2;
        }
        double d2 = (double) j2;
        double d3 = (double) f2;
        Double.isNaN(d2);
        Double.isNaN(d3);
        return Math.round(d2 * d3);
    }

    public static long a(long j2, long j3) {
        return ((j2 + j3) - 1) / j3;
    }

    public static long a(long j2, long j3, long j4) {
        return Math.max(j3, Math.min(j2, j4));
    }

    private static Point a(Context context, Display display) {
        int i2 = a;
        if (i2 <= 29 && display.getDisplayId() == 0 && b(context)) {
            if ("Sony".equals(c) && d.startsWith("BRAVIA") && context.getPackageManager().hasSystemFeature("com.sony.dtv.hardware.panel.qfhd")) {
                return new Point(3840, 2160);
            }
            String g2 = g(i2 < 28 ? "sys.display-size" : "vendor.display-size");
            if (!TextUtils.isEmpty(g2)) {
                try {
                    String[] split = g2.trim().split("x", -1);
                    if (split.length == 2) {
                        int parseInt = Integer.parseInt(split[0]);
                        int parseInt2 = Integer.parseInt(split[1]);
                        if (parseInt > 0 && parseInt2 > 0) {
                            return new Point(parseInt, parseInt2);
                        }
                    }
                } catch (NumberFormatException e2) {
                }
                String valueOf = String.valueOf(g2);
                Log.d("Util", valueOf.length() != 0 ? "Invalid display size: ".concat(valueOf) : new String("Invalid display size: "));
            }
        }
        Point point = new Point();
        int i3 = a;
        if (i3 >= 23) {
            Display.Mode mode = display.getMode();
            point.x = mode.getPhysicalWidth();
            point.y = mode.getPhysicalHeight();
        } else if (i3 >= 17) {
            display.getRealSize(point);
        } else {
            display.getSize(point);
        }
        return point;
    }

    public static Handler a() {
        return a((Handler.Callback) null);
    }

    public static Handler a(Handler.Callback callback) {
        return a((Looper) Assertions.a((Object) Looper.myLooper()), callback);
    }

    public static Handler a(Looper looper, Handler.Callback callback) {
        return new Handler(looper, callback);
    }

    public static CharSequence a(CharSequence charSequence, int i2) {
        return charSequence.length() <= i2 ? charSequence : charSequence.subSequence(0, i2);
    }

    public static Object a(Object obj) {
        return obj;
    }

    public static String a(Context context) {
        TelephonyManager telephonyManager;
        if (!(context == null || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null)) {
            String networkCountryIso = telephonyManager.getNetworkCountryIso();
            if (!TextUtils.isEmpty(networkCountryIso)) {
                return C0000a.b(networkCountryIso);
            }
        }
        return C0000a.b(Locale.getDefault().getCountry());
    }

    public static String a(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }

    public static String a(StringBuilder sb, Formatter formatter, long j2) {
        if (j2 == -9223372036854775807L) {
            j2 = 0;
        }
        String str = j2 < 0 ? "-" : "";
        long abs = (Math.abs(j2) + 500) / 1000;
        long j3 = abs % 60;
        long j4 = (abs / 60) % 60;
        long j5 = abs / 3600;
        sb.setLength(0);
        return (j5 > 0 ? formatter.format("%s%d:%02d:%02d", new Object[]{str, Long.valueOf(j5), Long.valueOf(j4), Long.valueOf(j3)}) : formatter.format("%s%02d:%02d", new Object[]{str, Long.valueOf(j4), Long.valueOf(j3)})).toString();
    }

    public static String a(Locale locale) {
        return a >= 21 ? locale.toLanguageTag() : locale.toString();
    }

    public static String a(byte[] bArr) {
        return new String(bArr, aC.c);
    }

    public static String a(byte[] bArr, int i2, int i3) {
        return new String(bArr, i2, i3, aC.c);
    }

    static final /* synthetic */ Thread a(String str, Runnable runnable) {
        return new Thread(runnable, str);
    }

    public static ExecutorService a(String str) {
        return Executors.newSingleThreadExecutor(new Util$$Lambda$0(str));
    }

    public static void a(Parcel parcel, boolean z) {
        parcel.writeInt(z ? 1 : 0);
    }

    public static void a(DataSource dataSource) {
        if (dataSource != null) {
            try {
                dataSource.c();
            } catch (IOException e2) {
            }
        }
    }

    public static void a(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e2) {
        }
    }

    public static void a(List list, int i2, int i3) {
        if (i2 < 0 || i3 > list.size() || i2 > i3) {
            throw new IllegalArgumentException();
        } else if (i2 != i3) {
            list.subList(i2, i3).clear();
        }
    }

    public static void a(List list, int i2, int i3, int i4) {
        ArrayDeque arrayDeque = new ArrayDeque();
        for (int i5 = (i3 - i2) - 1; i5 >= 0; i5--) {
            arrayDeque.addFirst(list.remove(i2 + i5));
        }
        list.addAll(Math.min(i4, list.size()), arrayDeque);
    }

    public static void a(long[] jArr, long j2) {
        int i2 = 0;
        if (j2 >= 1000000 && j2 % 1000000 == 0) {
            long j3 = j2 / 1000000;
            while (i2 < jArr.length) {
                jArr[i2] = jArr[i2] / j3;
                i2++;
            }
        } else if (j2 >= 1000000 || 1000000 % j2 != 0) {
            double d2 = (double) j2;
            Double.isNaN(d2);
            double d3 = 1000000.0d / d2;
            while (i2 < jArr.length) {
                double d4 = (double) jArr[i2];
                Double.isNaN(d4);
                jArr[i2] = (long) (d4 * d3);
                i2++;
            }
        } else {
            long j4 = 1000000 / j2;
            while (i2 < jArr.length) {
                jArr[i2] = jArr[i2] * j4;
                i2++;
            }
        }
    }

    public static boolean a(int i2) {
        return i2 == 10 || i2 == 13;
    }

    public static boolean a(Uri uri) {
        String scheme = uri.getScheme();
        return TextUtils.isEmpty(scheme) || "file".equals(scheme);
    }

    public static boolean a(Handler handler, Runnable runnable) {
        if (!handler.getLooper().getThread().isAlive()) {
            return false;
        }
        if (handler.getLooper() != Looper.myLooper()) {
            return handler.post(runnable);
        }
        runnable.run();
        return true;
    }

    public static boolean a(Parcel parcel) {
        return parcel.readInt() != 0;
    }

    public static boolean a(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, Inflater inflater) {
        if (parsableByteArray.a() <= 0) {
            return false;
        }
        if (parsableByteArray2.a.length < parsableByteArray.a()) {
            parsableByteArray2.b(parsableByteArray.a() * 2);
        }
        if (inflater == null) {
            inflater = new Inflater();
        }
        inflater.setInput(parsableByteArray.a, parsableByteArray.b, parsableByteArray.a());
        int i2 = 0;
        while (true) {
            try {
                byte[] bArr = parsableByteArray2.a;
                i2 += inflater.inflate(bArr, i2, bArr.length - i2);
                if (inflater.finished()) {
                    parsableByteArray2.c(i2);
                    inflater.reset();
                    return true;
                } else if (inflater.needsDictionary()) {
                    break;
                } else if (inflater.needsInput()) {
                    break;
                } else if (i2 == parsableByteArray2.a.length) {
                    parsableByteArray2.b(parsableByteArray2.a.length << 1);
                }
            } catch (DataFormatException e2) {
                return false;
            } finally {
                inflater.reset();
            }
        }
        return false;
    }

    public static boolean a(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    public static boolean a(Object[] objArr, Object obj) {
        for (Object a2 : objArr) {
            if (a(a2, obj)) {
                return true;
            }
        }
        return false;
    }

    public static byte[] a(InputStream inputStream) {
        byte[] bArr = new byte[4096];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    public static Object[] a(Object[] objArr) {
        return objArr;
    }

    public static Object[] a(Object[] objArr, int i2) {
        Assertions.a(i2 <= objArr.length);
        return Arrays.copyOf(objArr, i2);
    }

    public static Object[] a(Object[] objArr, Object[] objArr2) {
        Object[] copyOf = Arrays.copyOf(objArr, objArr.length + objArr2.length);
        System.arraycopy(objArr2, 0, copyOf, objArr.length, objArr2.length);
        return copyOf;
    }

    public static String[] a(String str, String str2) {
        return str.split(str2, -1);
    }

    public static int b(int i2) {
        switch (i2) {
            case 8:
                return 3;
            case 16:
                return 2;
            case 24:
                return Declaration.EARLY_INIT;
            case 32:
                return 805306368;
            default:
                return 0;
        }
    }

    public static int b(byte[] bArr, int i2, int i3) {
        for (int i4 = 0; i4 < i2; i4++) {
            i3 = m[((i3 >>> 24) ^ (bArr[i4] & Ev3Constants.Opcode.TST)) & Ev3Constants.Opcode.TST] ^ (i3 << 8);
        }
        return i3;
    }

    public static int b(int[] iArr, int i2) {
        int binarySearch = Arrays.binarySearch(iArr, i2);
        if (binarySearch < 0) {
            return -(binarySearch + 2);
        }
        do {
            binarySearch--;
            if (binarySearch < 0 || iArr[binarySearch] != i2) {
            }
            binarySearch--;
            break;
        } while (iArr[binarySearch] != i2);
        return binarySearch;
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x0016  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0019  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int b(long[] r4, long r5, boolean r7) {
        /*
            int r0 = java.util.Arrays.binarySearch(r4, r5)
            if (r0 >= 0) goto L_0x0009
            r4 = r0 ^ -1
            goto L_0x001a
        L_0x0009:
            int r0 = r0 + 1
            int r1 = r4.length
            if (r0 >= r1) goto L_0x0014
            r1 = r4[r0]
            int r3 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r3 == 0) goto L_0x0009
        L_0x0014:
            if (r7 == 0) goto L_0x0019
            int r4 = r0 + -1
            goto L_0x001a
        L_0x0019:
            r4 = r0
        L_0x001a:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.util.Util.b(long[], long, boolean):int");
    }

    public static long b(int i2, int i3) {
        return (((long) i3) & 4294967295L) | ((((long) i2) & 4294967295L) << 32);
    }

    public static long b(long j2, float f2) {
        if (f2 == 1.0f) {
            return j2;
        }
        double d2 = (double) j2;
        double d3 = (double) f2;
        Double.isNaN(d2);
        Double.isNaN(d3);
        return Math.round(d2 / d3);
    }

    public static long b(long j2, long j3) {
        long j4 = j2 + j3;
        return ((j2 ^ j4) & (j3 ^ j4)) < 0 ? LocationRequestCompat.PASSIVE_INTERVAL : j4;
    }

    public static long b(long j2, long j3, long j4) {
        if (j4 >= j3 && j4 % j3 == 0) {
            return j2 / (j4 / j3);
        }
        if (j4 < j3 && j3 % j4 == 0) {
            return j2 * (j3 / j4);
        }
        double d2 = (double) j3;
        double d3 = (double) j4;
        Double.isNaN(d2);
        Double.isNaN(d3);
        double d4 = (double) j2;
        Double.isNaN(d4);
        return (long) (d4 * (d2 / d3));
    }

    public static Handler b() {
        return a(c(), (Handler.Callback) null);
    }

    public static Format b(int i2, int i3, int i4) {
        Format.Builder builder = new Format.Builder();
        builder.k = "audio/raw";
        builder.x = i3;
        builder.y = i4;
        builder.z = i2;
        return builder.a();
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        String replace = str.replace('_', '-');
        if (!replace.isEmpty() && !replace.equals("und")) {
            str = replace;
        }
        String a2 = C0000a.a(str);
        String str2 = a2.split("-", 2)[0];
        if (j == null) {
            j = e();
        }
        String str3 = (String) j.get(str2);
        if (str3 != null) {
            String valueOf = String.valueOf(str3);
            String valueOf2 = String.valueOf(a2.substring(str2.length()));
            a2 = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
            str2 = str3;
        }
        return ("no".equals(str2) || "i".equals(str2) || "zh".equals(str2)) ? h(a2) : a2;
    }

    public static String b(String str, int i2) {
        String[] f2 = f(str);
        if (f2.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : f2) {
            if (i2 == MimeTypes.i(str2)) {
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(str2);
            }
        }
        if (sb.length() > 0) {
            return sb.toString();
        }
        return null;
    }

    public static String b(Object[] objArr) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < objArr.length; i2++) {
            sb.append(objArr[i2].getClass().getSimpleName());
            if (i2 < objArr.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public static boolean b(Context context) {
        UiModeManager uiModeManager = (UiModeManager) context.getApplicationContext().getSystemService("uimode");
        return uiModeManager != null && uiModeManager.getCurrentModeType() == 4;
    }

    public static Object[] b(Object[] objArr, int i2) {
        Assertions.a(true);
        Assertions.a(i2 <= objArr.length);
        return Arrays.copyOfRange(objArr, 1, i2);
    }

    public static Object[] b(Object[] objArr, Object obj) {
        Object[] copyOf = Arrays.copyOf(objArr, objArr.length + 1);
        copyOf[objArr.length] = obj;
        return copyOf;
    }

    public static String[] b(String str, String str2) {
        return str.split(str2, 2);
    }

    public static int c(int i2, int i3) {
        switch (i2) {
            case 2:
            case Declaration.IS_DYNAMIC /*268435456*/:
                return i3 << 1;
            case 3:
                return i3;
            case 4:
            case 805306368:
                return i3 << 2;
            case Declaration.EARLY_INIT /*536870912*/:
                return i3 * 3;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static long c(long j2, long j3) {
        long j4 = j2 - j3;
        if (((j2 ^ j4) & (j3 ^ j2)) < 0) {
            return Long.MIN_VALUE;
        }
        return j4;
    }

    public static Point c(Context context) {
        return a(context, ((WindowManager) Assertions.b((Object) (WindowManager) context.getSystemService("window"))).getDefaultDisplay());
    }

    public static Looper c() {
        Looper myLooper = Looper.myLooper();
        return myLooper != null ? myLooper : Looper.getMainLooper();
    }

    public static boolean c(int i2) {
        return i2 == 3 || i2 == 2 || i2 == 268435456 || i2 == 536870912 || i2 == 805306368 || i2 == 4;
    }

    public static byte[] c(String str) {
        return str.getBytes(aC.c);
    }

    public static int d(long j2, long j3) {
        if (j2 < j3) {
            return -1;
        }
        return j2 == j3 ? 0 : 1;
    }

    public static long d(String str) {
        Matcher matcher = h.matcher(str);
        if (!matcher.matches()) {
            return (long) (Double.parseDouble(str) * 3600.0d * 1000.0d);
        }
        boolean isEmpty = true ^ TextUtils.isEmpty(matcher.group(1));
        String group = matcher.group(3);
        double d2 = 0.0d;
        double parseDouble = group != null ? Double.parseDouble(group) * 3.1556908E7d : 0.0d;
        String group2 = matcher.group(5);
        double parseDouble2 = parseDouble + (group2 != null ? Double.parseDouble(group2) * 2629739.0d : 0.0d);
        String group3 = matcher.group(7);
        double parseDouble3 = parseDouble2 + (group3 != null ? Double.parseDouble(group3) * 86400.0d : 0.0d);
        String group4 = matcher.group(10);
        double parseDouble4 = parseDouble3 + (group4 != null ? Double.parseDouble(group4) * 3600.0d : 0.0d);
        String group5 = matcher.group(12);
        double parseDouble5 = parseDouble4 + (group5 != null ? Double.parseDouble(group5) * 60.0d : 0.0d);
        String group6 = matcher.group(14);
        if (group6 != null) {
            d2 = Double.parseDouble(group6);
        }
        long j2 = (long) ((parseDouble5 + d2) * 1000.0d);
        return isEmpty ? -j2 : j2;
    }

    public static boolean d(int i2) {
        return i2 == 536870912 || i2 == 805306368 || i2 == 4;
    }

    public static String[] d() {
        String[] strArr;
        Configuration configuration = Resources.getSystem().getConfiguration();
        if (a >= 24) {
            strArr = configuration.getLocales().toLanguageTags().split(",", -1);
        } else {
            strArr = new String[]{a(configuration.locale)};
        }
        for (int i2 = 0; i2 < strArr.length; i2++) {
            strArr[i2] = b(strArr[i2]);
        }
        return strArr;
    }

    public static int e(int i2) {
        switch (i2) {
            case 1:
                return 4;
            case 2:
                return 12;
            case 3:
                return 28;
            case 4:
                return 204;
            case 5:
                return 220;
            case 6:
                return Telnet.WONT;
            case 7:
                return 1276;
            case 8:
                int i3 = a;
                return (i3 < 23 && i3 < 21) ? 0 : 6396;
            default:
                return 0;
        }
    }

    public static long e(String str) {
        Matcher matcher = g.matcher(str);
        if (!matcher.matches()) {
            String valueOf = String.valueOf(str);
            throw new ParserException(valueOf.length() != 0 ? "Invalid date/time format: ".concat(valueOf) : new String("Invalid date/time format: "));
        }
        int i2 = 0;
        if (matcher.group(9) != null && !matcher.group(9).equalsIgnoreCase("Z")) {
            i2 = (Integer.parseInt(matcher.group(12)) * 60) + Integer.parseInt(matcher.group(13));
            if ("-".equals(matcher.group(11))) {
                i2 = -i2;
            }
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        gregorianCalendar.clear();
        gregorianCalendar.set(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)) - 1, Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)), Integer.parseInt(matcher.group(5)), Integer.parseInt(matcher.group(6)));
        if (!TextUtils.isEmpty(matcher.group(8))) {
            String valueOf2 = String.valueOf(matcher.group(8));
            gregorianCalendar.set(14, new BigDecimal(valueOf2.length() != 0 ? "0.".concat(valueOf2) : new String("0.")).movePointRight(3).intValue());
        }
        long timeInMillis = gregorianCalendar.getTimeInMillis();
        return i2 != 0 ? timeInMillis - ((long) (i2 * 60000)) : timeInMillis;
    }

    private static HashMap e() {
        String[] iSOLanguages = Locale.getISOLanguages();
        HashMap hashMap = new HashMap(iSOLanguages.length + 86);
        for (String str : iSOLanguages) {
            try {
                String iSO3Language = new Locale(str).getISO3Language();
                if (!TextUtils.isEmpty(iSO3Language)) {
                    hashMap.put(iSO3Language, str);
                }
            } catch (MissingResourceException e2) {
            }
        }
        for (int i2 = 0; i2 < 86; i2 += 2) {
            String[] strArr = k;
            hashMap.put(strArr[i2], strArr[i2 + 1]);
        }
        return hashMap;
    }

    public static int f(int i2) {
        switch (i2) {
            case 1:
            case 12:
            case 14:
                return 3;
            case 2:
                return 0;
            case 3:
                return 8;
            case 4:
                return 4;
            case 5:
            case 7:
            case 8:
            case 9:
            case 10:
                return 5;
            case 6:
                return 2;
            case 13:
                return 1;
            default:
                return 3;
        }
    }

    public static String[] f(String str) {
        return TextUtils.isEmpty(str) ? new String[0] : str.trim().split("(\\s*,\\s*)", -1);
    }

    private static String g(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class}).invoke(cls, new Object[]{str});
        } catch (Exception e2) {
            String valueOf = String.valueOf(str);
            Log.b("Util", valueOf.length() != 0 ? "Failed to read system property ".concat(valueOf) : new String("Failed to read system property "), e2);
            return null;
        }
    }

    private static String h(String str) {
        for (int i2 = 0; i2 < 18; i2 += 2) {
            String[] strArr = l;
            if (str.startsWith(strArr[i2])) {
                String valueOf = String.valueOf(strArr[i2 + 1]);
                String valueOf2 = String.valueOf(str.substring(strArr[i2].length()));
                return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
            }
        }
        return str;
    }
}
