package com.google.android.exoplayer2.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyDisplayInfo;
import android.telephony.TelephonyManager;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public final class NetworkTypeObserver {
    private static NetworkTypeObserver c;
    public final Handler a = new Handler(Looper.getMainLooper());
    public final CopyOnWriteArrayList b = new CopyOnWriteArrayList();
    private final Object d = new Object();
    private int e = 0;

    public interface Listener {
        void a(int i);
    }

    final class Receiver extends BroadcastReceiver {
        private Receiver() {
        }

        /* synthetic */ Receiver(NetworkTypeObserver networkTypeObserver, byte b) {
            this();
        }

        public final void onReceive(Context context, Intent intent) {
            int b = NetworkTypeObserver.c(context);
            if (b == 5 && Util.a >= 29) {
                try {
                    TelephonyManager telephonyManager = (TelephonyManager) Assertions.b((Object) (TelephonyManager) context.getSystemService("phone"));
                    TelephonyManagerListener telephonyManagerListener = new TelephonyManagerListener(NetworkTypeObserver.this, (byte) 0);
                    if (Util.a < 31) {
                        telephonyManager.listen(telephonyManagerListener, 1);
                    } else {
                        telephonyManager.listen(telephonyManagerListener, 1048576);
                    }
                    telephonyManager.listen(telephonyManagerListener, 0);
                    return;
                } catch (RuntimeException e) {
                }
            }
            NetworkTypeObserver.a(NetworkTypeObserver.this, b);
        }
    }

    class TelephonyManagerListener extends PhoneStateListener {
        private TelephonyManagerListener() {
        }

        /* synthetic */ TelephonyManagerListener(NetworkTypeObserver networkTypeObserver, byte b) {
            this();
        }

        public void onDisplayInfoChanged(TelephonyDisplayInfo telephonyDisplayInfo) {
            int overrideNetworkType = telephonyDisplayInfo.getOverrideNetworkType();
            NetworkTypeObserver.a(NetworkTypeObserver.this, overrideNetworkType == 3 || overrideNetworkType == 4 ? 10 : 5);
        }

        public void onServiceStateChanged(ServiceState serviceState) {
            String serviceState2 = serviceState == null ? "" : serviceState.toString();
            NetworkTypeObserver.a(NetworkTypeObserver.this, serviceState2.contains("nrState=CONNECTED") || serviceState2.contains("nrState=NOT_RESTRICTED") ? 10 : 5);
        }
    }

    private NetworkTypeObserver(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(new Receiver(this, (byte) 0), intentFilter);
    }

    public static synchronized NetworkTypeObserver a(Context context) {
        NetworkTypeObserver networkTypeObserver;
        synchronized (NetworkTypeObserver.class) {
            if (c == null) {
                c = new NetworkTypeObserver(context);
            }
            networkTypeObserver = c;
        }
        return networkTypeObserver;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0016, code lost:
        if (r0.hasNext() == false) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
        r1 = (java.lang.ref.WeakReference) r0.next();
        r2 = (com.google.android.exoplayer2.util.NetworkTypeObserver.Listener) r1.get();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0024, code lost:
        if (r2 == null) goto L_0x002a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0026, code lost:
        r2.a(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002a, code lost:
        r3.b.remove(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0030, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000c, code lost:
        r0 = r3.b.iterator();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void a(com.google.android.exoplayer2.util.NetworkTypeObserver r3, int r4) {
        /*
            java.lang.Object r0 = r3.d
            monitor-enter(r0)
            int r1 = r3.e     // Catch:{ all -> 0x0031 }
            if (r1 != r4) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
            return
        L_0x0009:
            r3.e = r4     // Catch:{ all -> 0x0031 }
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
            java.util.concurrent.CopyOnWriteArrayList r0 = r3.b
            java.util.Iterator r0 = r0.iterator()
        L_0x0012:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0030
            java.lang.Object r1 = r0.next()
            java.lang.ref.WeakReference r1 = (java.lang.ref.WeakReference) r1
            java.lang.Object r2 = r1.get()
            com.google.android.exoplayer2.util.NetworkTypeObserver$Listener r2 = (com.google.android.exoplayer2.util.NetworkTypeObserver.Listener) r2
            if (r2 == 0) goto L_0x002a
            r2.a(r4)
            goto L_0x0012
        L_0x002a:
            java.util.concurrent.CopyOnWriteArrayList r2 = r3.b
            r2.remove(r1)
            goto L_0x0012
        L_0x0030:
            return
        L_0x0031:
            r3 = move-exception
            monitor-exit(r0)
            goto L_0x0035
        L_0x0034:
            throw r3
        L_0x0035:
            goto L_0x0034
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.util.NetworkTypeObserver.a(com.google.android.exoplayer2.util.NetworkTypeObserver, int):void");
    }

    /* access modifiers changed from: private */
    public static int c(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return 0;
        }
        try {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                return 1;
            }
            switch (activeNetworkInfo.getType()) {
                case 0:
                case 4:
                case 5:
                    switch (activeNetworkInfo.getSubtype()) {
                        case 1:
                        case 2:
                            return 3;
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                        case 14:
                        case 15:
                        case 17:
                            return 4;
                        case 13:
                            return 5;
                        case 18:
                            return 2;
                        case 20:
                            return Util.a >= 29 ? 9 : 0;
                        default:
                            return 6;
                    }
                case 1:
                    return 2;
                case 6:
                    return 5;
                case 9:
                    return 7;
                default:
                    return 8;
            }
        } catch (SecurityException e2) {
            return 0;
        }
    }

    public final int a() {
        int i;
        synchronized (this.d) {
            i = this.e;
        }
        return i;
    }

    public final void b() {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            if (weakReference.get() == null) {
                this.b.remove(weakReference);
            }
        }
    }
}
