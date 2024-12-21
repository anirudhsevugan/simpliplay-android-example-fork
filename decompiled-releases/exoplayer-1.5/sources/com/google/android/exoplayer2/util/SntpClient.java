package com.google.android.exoplayer2.util;

import android.os.SystemClock;
import com.dreamers.exoplayercore.repack.cT;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import gnu.expr.Declaration;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.ConcurrentModificationException;

public final class SntpClient {
    /* access modifiers changed from: private */
    public static final Object a = new Object();
    /* access modifiers changed from: private */
    public static final Object b = new Object();
    /* access modifiers changed from: private */
    public static boolean c;
    /* access modifiers changed from: private */
    public static long d;
    private static String e = "time.android.com";

    public interface InitializationCallback {
        void a();

        void a(IOException iOException);
    }

    final class NtpTimeCallback implements Loader.Callback {
        private final InitializationCallback a;

        public NtpTimeCallback(InitializationCallback initializationCallback) {
            this.a = initializationCallback;
        }

        public final Loader.LoadErrorAction a(Loader.Loadable loadable, IOException iOException, int i) {
            InitializationCallback initializationCallback = this.a;
            if (initializationCallback != null) {
                initializationCallback.a(iOException);
            }
            return Loader.b;
        }

        public final void a(Loader.Loadable loadable, long j, long j2) {
            if (this.a == null) {
                return;
            }
            if (!SntpClient.a()) {
                this.a.a(new IOException(new ConcurrentModificationException()));
            } else {
                this.a.a();
            }
        }

        public final void a(Loader.Loadable loadable, boolean z) {
        }
    }

    final class NtpTimeLoadable implements Loader.Loadable {
        private NtpTimeLoadable() {
        }

        /* synthetic */ NtpTimeLoadable(byte b) {
            this();
        }

        public final void a() {
        }

        public final void b() {
            synchronized (SntpClient.a) {
                synchronized (SntpClient.b) {
                    if (!SntpClient.c) {
                        long f = SntpClient.i();
                        synchronized (SntpClient.b) {
                            long unused = SntpClient.d = f;
                            boolean unused2 = SntpClient.c = true;
                        }
                    }
                }
            }
        }
    }

    private static long a(byte[] bArr, int i) {
        long b2 = b(bArr, i);
        long b3 = b(bArr, i + 4);
        if (b2 == 0 && b3 == 0) {
            return 0;
        }
        return ((b2 - 2208988800L) * 1000) + ((b3 * 1000) / Declaration.TRANSIENT_ACCESS);
    }

    public static void a(Loader loader, InitializationCallback initializationCallback) {
        if (a()) {
            initializationCallback.a();
            return;
        }
        if (loader == null) {
            loader = new Loader("SntpClient");
        }
        loader.a(new NtpTimeLoadable((byte) 0), new NtpTimeCallback(initializationCallback), 1);
    }

    public static boolean a() {
        boolean z;
        synchronized (b) {
            z = c;
        }
        return z;
    }

    public static long b() {
        long j;
        synchronized (b) {
            j = c ? d : -9223372036854775807L;
        }
        return j;
    }

    private static long b(byte[] bArr, int i) {
        byte b2 = bArr[i];
        byte b3 = bArr[i + 1];
        byte b4 = bArr[i + 2];
        byte b5 = bArr[i + 3];
        byte b6 = b2 & true;
        int i2 = b2;
        if (b6 == true) {
            i2 = (b2 & Ev3Constants.Opcode.MEMORY_READ) + 128;
        }
        byte b7 = b3 & true;
        int i3 = b3;
        if (b7 == true) {
            i3 = (b3 & Ev3Constants.Opcode.MEMORY_READ) + 128;
        }
        byte b8 = b4 & true;
        int i4 = b4;
        if (b8 == true) {
            i4 = (b4 & Ev3Constants.Opcode.MEMORY_READ) + 128;
        }
        byte b9 = b5 & true;
        int i5 = b5;
        if (b9 == true) {
            i5 = (b5 & Ev3Constants.Opcode.MEMORY_READ) + 128;
        }
        return (((long) i2) << 24) + (((long) i3) << 16) + (((long) i4) << 8) + ((long) i5);
    }

    private static String h() {
        String str;
        synchronized (b) {
            str = e;
        }
        return str;
    }

    /* access modifiers changed from: private */
    public static long i() {
        Throwable th;
        long j;
        InetAddress byName = InetAddress.getByName(h());
        DatagramSocket datagramSocket = new DatagramSocket();
        try {
            datagramSocket.setSoTimeout(10000);
            byte[] bArr = new byte[48];
            DatagramPacket datagramPacket = new DatagramPacket(bArr, 48, byName, 123);
            bArr[0] = 27;
            long currentTimeMillis = System.currentTimeMillis();
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (currentTimeMillis == 0) {
                Arrays.fill(bArr, 40, 48, (byte) 0);
                j = currentTimeMillis;
            } else {
                long j2 = currentTimeMillis / 1000;
                Long.signum(j2);
                long j3 = currentTimeMillis - (j2 * 1000);
                long j4 = j2 + 2208988800L;
                j = currentTimeMillis;
                bArr[40] = (byte) ((int) (j4 >> 24));
                bArr[41] = (byte) ((int) (j4 >> 16));
                bArr[42] = (byte) ((int) (j4 >> 8));
                bArr[43] = (byte) ((int) j4);
                long j5 = (j3 << 32) / 1000;
                bArr[44] = (byte) ((int) (j5 >> 24));
                bArr[45] = (byte) ((int) (j5 >> 16));
                bArr[46] = (byte) ((int) (j5 >> 8));
                bArr[47] = (byte) ((int) (Math.random() * 255.0d));
            }
            datagramSocket.send(datagramPacket);
            datagramSocket.receive(new DatagramPacket(bArr, 48));
            long elapsedRealtime2 = SystemClock.elapsedRealtime();
            long j6 = j + (elapsedRealtime2 - elapsedRealtime);
            byte b2 = bArr[0];
            byte b3 = (byte) ((b2 >> 6) & 3);
            byte b4 = (byte) (b2 & 7);
            byte b5 = bArr[1] & Ev3Constants.Opcode.TST;
            long a2 = a(bArr, 24);
            long a3 = a(bArr, 32);
            long a4 = a(bArr, 40);
            if (b3 != 3) {
                if (b4 != 4) {
                    if (b4 != 5) {
                        throw new IOException(new StringBuilder(26).append("SNTP: Untrusted mode: ").append(b4).toString());
                    }
                }
                if (b5 == 0 || b5 > 15) {
                    throw new IOException(new StringBuilder(36).append("SNTP: Untrusted stratum: ").append(b5).toString());
                } else if (a4 != 0) {
                    long j7 = (j6 + (((a3 - a2) + (a4 - j6)) / 2)) - elapsedRealtime2;
                    datagramSocket.close();
                    return j7;
                } else {
                    throw new IOException("SNTP: Zero transmitTime");
                }
            } else {
                throw new IOException("SNTP: Unsynchronized server");
            }
        } catch (Throwable th2) {
            cT.a(th, th2);
        }
        throw th;
    }
}
