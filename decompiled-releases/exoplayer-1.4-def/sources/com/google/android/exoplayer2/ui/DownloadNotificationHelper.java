package com.google.android.exoplayer2.ui;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.google.android.exoplayer2.offline.Download;
import java.util.List;

public final class DownloadNotificationHelper {
    private static final int NULL_STRING_ID = 0;
    private final NotificationCompat.Builder notificationBuilder;

    public DownloadNotificationHelper(Context context, String str) {
        this.notificationBuilder = new NotificationCompat.Builder(context.getApplicationContext(), str);
    }

    private Notification buildEndStateNotification(Context context, int i, PendingIntent pendingIntent, String str, int i2) {
        return buildNotification(context, i, pendingIntent, str, i2, 0, 0, false, false, true);
    }

    private Notification buildNotification(Context context, int i, PendingIntent pendingIntent, String str, int i2, int i3, int i4, boolean z, boolean z2, boolean z3) {
        this.notificationBuilder.setSmallIcon(i);
        NotificationCompat.BigTextStyle bigTextStyle = null;
        this.notificationBuilder.setContentTitle(i2 == 0 ? null : context.getResources().getString(i2));
        this.notificationBuilder.setContentIntent(pendingIntent);
        NotificationCompat.Builder builder = this.notificationBuilder;
        if (str != null) {
            bigTextStyle = new NotificationCompat.BigTextStyle().bigText(str);
        }
        builder.setStyle(bigTextStyle);
        this.notificationBuilder.setProgress(i3, i4, z);
        this.notificationBuilder.setOngoing(z2);
        this.notificationBuilder.setShowWhen(z3);
        return this.notificationBuilder.build();
    }

    public final Notification buildDownloadCompletedNotification(Context context, int i, PendingIntent pendingIntent, String str) {
        return buildEndStateNotification(context, i, pendingIntent, str, 0);
    }

    public final Notification buildDownloadFailedNotification(Context context, int i, PendingIntent pendingIntent, String str) {
        return buildEndStateNotification(context, i, pendingIntent, str, 0);
    }

    public final Notification buildProgressNotification(Context context, int i, PendingIntent pendingIntent, String str, List list) {
        boolean z;
        int i2;
        boolean z2 = false;
        boolean z3 = false;
        int i3 = 0;
        boolean z4 = true;
        boolean z5 = false;
        for (int i4 = 0; i4 < list.size(); i4++) {
            Download download = (Download) list.get(i4);
            if (download.a != 5 && (download.a == 7 || download.a == 2)) {
                float f = download.b.b;
                z5 |= download.b.a > 0;
                i3++;
                z3 = true;
                z4 = false;
            }
        }
        if (z3) {
            int i5 = (int) (0.0f / ((float) i3));
            if (z4 && z5) {
                z2 = true;
            }
            z = z2;
            i2 = i5;
        } else {
            i2 = 0;
            z = true;
        }
        return buildNotification(context, i, pendingIntent, str, 0, 100, i2, z, true, false);
    }
}
