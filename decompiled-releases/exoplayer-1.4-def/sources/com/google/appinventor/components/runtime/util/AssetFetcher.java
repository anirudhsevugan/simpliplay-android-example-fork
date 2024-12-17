package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.ReplForm;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import java.io.File;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;

public class AssetFetcher {
    /* access modifiers changed from: private */
    public static final String LOG_TAG = AssetFetcher.class.getSimpleName();
    private static ExecutorService background = Executors.newSingleThreadExecutor();
    private static Context context = ReplForm.getActiveForm();
    private static HashDatabase db = new HashDatabase(context);
    private static volatile boolean inError = false;
    private static final Object semaphore = new Object();

    private AssetFetcher() {
    }

    public static void fetchAssets(final String cookieValue, final String projectId, final String uri, final String asset) {
        background.submit(new Runnable() {
            public void run() {
                String str = uri;
                String str2 = projectId;
                if (AssetFetcher.getFile(str + "/ode/download/file/" + str2 + "/" + asset, cookieValue, asset, 0) != null) {
                    RetValManager.assetTransferred(asset);
                }
            }
        });
    }

    public static void upgradeCompanion(final String cookieValue, final String inputUri) {
        background.submit(new Runnable() {
            public void run() {
                String[] parts = inputUri.split("/", 0);
                File assetFile = AssetFetcher.getFile(inputUri, cookieValue, parts[parts.length - 1], 0);
                if (assetFile != null) {
                    try {
                        Form form = Form.getActiveForm();
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.setDataAndType(NougatUtil.getPackageUri(form, assetFile), "application/vnd.android.package-archive");
                        intent.setFlags(1);
                        form.startActivity(intent);
                    } catch (Exception e) {
                        Log.e(AssetFetcher.LOG_TAG, "ERROR_UNABLE_TO_GET", e);
                        RetValManager.sendError("Unable to Install new Companion Package.");
                    }
                }
            }
        });
    }

    public static void loadExtensions(String jsonString) {
        String str = LOG_TAG;
        Log.d(str, "loadExtensions called jsonString = " + jsonString);
        try {
            ReplForm form = (ReplForm) Form.getActiveForm();
            JSONArray array = new JSONArray(jsonString);
            List<String> extensionsToLoad = new ArrayList<>();
            if (array.length() == 0) {
                Log.d(str, "loadExtensions: No Extensions");
                RetValManager.extensionsLoaded();
                return;
            }
            int i = 0;
            while (i < array.length()) {
                String extensionName = array.optString(i);
                if (extensionName != null) {
                    Log.d(LOG_TAG, "loadExtensions, extensionName = " + extensionName);
                    extensionsToLoad.add(extensionName);
                    i++;
                } else {
                    Log.e(LOG_TAG, "extensionName was null");
                    return;
                }
            }
            try {
                form.loadComponents(extensionsToLoad);
                RetValManager.extensionsLoaded();
            } catch (Exception e) {
                Log.e(LOG_TAG, "Error in form.loadComponents", e);
                RetValManager.sendError("Unable to load extensions." + e);
            }
        } catch (JSONException e2) {
            Log.e(LOG_TAG, "JSON Exception parsing extension string", e2);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x021f A[Catch:{ Exception -> 0x02a8, all -> 0x02a1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x0262 A[Catch:{ Exception -> 0x02a8, all -> 0x02a1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x02bd  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x02d0  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x0325  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x032a  */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x0331  */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x0336  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x01d3 A[Catch:{ Exception -> 0x02a8, all -> 0x02a1 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.io.File getFile(java.lang.String r23, java.lang.String r24, java.lang.String r25, int r26) {
        /*
            r1 = r23
            r2 = r24
            r3 = r25
            r4 = r26
            com.google.appinventor.components.runtime.Form r5 = com.google.appinventor.components.runtime.Form.getActiveForm()
            r6 = 0
            r0 = 1
            if (r4 <= r0) goto L_0x0028
            java.lang.Object r7 = semaphore
            monitor-enter(r7)
            boolean r8 = inError     // Catch:{ all -> 0x0025 }
            if (r8 == 0) goto L_0x0019
            monitor-exit(r7)     // Catch:{ all -> 0x0025 }
            return r6
        L_0x0019:
            inError = r0     // Catch:{ all -> 0x0025 }
            com.google.appinventor.components.runtime.util.AssetFetcher$3 r0 = new com.google.appinventor.components.runtime.util.AssetFetcher$3     // Catch:{ all -> 0x0025 }
            r0.<init>(r1)     // Catch:{ all -> 0x0025 }
            r5.runOnUiThread(r0)     // Catch:{ all -> 0x0025 }
            monitor-exit(r7)     // Catch:{ all -> 0x0025 }
            return r6
        L_0x0025:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x0025 }
            throw r0
        L_0x0028:
            r7 = r25
            java.io.File r8 = getDestinationFile(r5, r3)
            java.lang.String r9 = "/classes.jar"
            boolean r9 = r3.endsWith(r9)
            r10 = 0
            if (r9 == 0) goto L_0x0057
            java.lang.String r9 = "/"
            int r9 = r3.lastIndexOf(r9)
            int r9 = r9 + r0
            java.lang.String r9 = r3.substring(r10, r9)
            java.lang.String r11 = r8.getName()
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.StringBuilder r9 = r12.append(r9)
            java.lang.StringBuilder r9 = r9.append(r11)
            java.lang.String r7 = r9.toString()
        L_0x0057:
            java.lang.String r9 = LOG_TAG
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "target file = "
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.StringBuilder r11 = r11.append(r8)
            java.lang.String r11 = r11.toString()
            android.util.Log.d(r9, r11)
            int r11 = android.os.Build.VERSION.SDK_INT
            r12 = 34
            if (r11 < r12) goto L_0x0087
            java.lang.String r11 = "/external_comps/"
            boolean r11 = r3.contains(r11)
            if (r11 == 0) goto L_0x0087
            java.lang.String r11 = "/classes.jar"
            boolean r11 = r3.endsWith(r11)
            if (r11 == 0) goto L_0x0087
            r10 = 1
            goto L_0x0088
        L_0x0087:
        L_0x0088:
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            java.net.URL r15 = new java.net.URL     // Catch:{ Exception -> 0x0302, all -> 0x02fe }
            r15.<init>(r1)     // Catch:{ Exception -> 0x0302, all -> 0x02fe }
            java.net.URLConnection r16 = r15.openConnection()     // Catch:{ Exception -> 0x0302, all -> 0x02fe }
            java.net.HttpURLConnection r16 = (java.net.HttpURLConnection) r16     // Catch:{ Exception -> 0x0302, all -> 0x02fe }
            r11 = r16
            if (r11 == 0) goto L_0x02b7
            java.lang.String r6 = "Cookie"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0302, all -> 0x02fe }
            r0.<init>()     // Catch:{ Exception -> 0x0302, all -> 0x02fe }
            r18 = r5
            java.lang.String r5 = "AppInventor = "
            java.lang.StringBuilder r0 = r0.append(r5)     // Catch:{ Exception -> 0x02ce }
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ Exception -> 0x02ce }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x02ce }
            r11.addRequestProperty(r6, r0)     // Catch:{ Exception -> 0x02ce }
            com.google.appinventor.components.runtime.util.HashDatabase r0 = db     // Catch:{ Exception -> 0x02ce }
            com.google.appinventor.components.runtime.util.HashFile r0 = r0.getHashFile(r7)     // Catch:{ Exception -> 0x02ce }
            r5 = r0
            if (r5 == 0) goto L_0x00cd
            boolean r0 = r8.exists()     // Catch:{ Exception -> 0x02ce }
            if (r0 == 0) goto L_0x00cd
            java.lang.String r0 = "If-None-Match"
            java.lang.String r6 = r5.getHash()     // Catch:{ Exception -> 0x02ce }
            r11.addRequestProperty(r0, r6)     // Catch:{ Exception -> 0x02ce }
        L_0x00cd:
            java.lang.String r0 = "GET"
            r11.setRequestMethod(r0)     // Catch:{ Exception -> 0x02ce }
            int r0 = r11.getResponseCode()     // Catch:{ Exception -> 0x02ce }
            r12 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x02b3, all -> 0x02ae }
            r0.<init>()     // Catch:{ Exception -> 0x02b3, all -> 0x02ae }
            java.lang.String r6 = "asset = "
            java.lang.StringBuilder r0 = r0.append(r6)     // Catch:{ Exception -> 0x02b3, all -> 0x02ae }
            java.lang.StringBuilder r0 = r0.append(r3)     // Catch:{ Exception -> 0x02b3, all -> 0x02ae }
            java.lang.String r6 = " responseCode = "
            java.lang.StringBuilder r0 = r0.append(r6)     // Catch:{ Exception -> 0x02b3, all -> 0x02ae }
            java.lang.StringBuilder r0 = r0.append(r12)     // Catch:{ Exception -> 0x02b3, all -> 0x02ae }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x02b3, all -> 0x02ae }
            android.util.Log.d(r9, r0)     // Catch:{ Exception -> 0x02b3, all -> 0x02ae }
            java.io.File r0 = r8.getParentFile()     // Catch:{ Exception -> 0x02b3, all -> 0x02ae }
            r6 = r0
            java.lang.String r0 = "ETag"
            java.lang.String r0 = r11.getHeaderField(r0)     // Catch:{ Exception -> 0x02b3, all -> 0x02ae }
            r13 = r0
            r0 = 304(0x130, float:4.26E-43)
            if (r12 != r0) goto L_0x0113
            if (r10 == 0) goto L_0x010d
            r8.setReadOnly()
        L_0x010d:
            if (r11 == 0) goto L_0x0112
            r11.disconnect()
        L_0x0112:
            return r8
        L_0x0113:
            if (r6 == 0) goto L_0x0282
            boolean r0 = r6.exists()     // Catch:{ Exception -> 0x027b, all -> 0x0274 }
            if (r0 != 0) goto L_0x012a
            boolean r0 = r6.mkdirs()     // Catch:{ Exception -> 0x02ce }
            if (r0 == 0) goto L_0x0122
            goto L_0x012a
        L_0x0122:
            r17 = r5
            r20 = r12
            r21 = r13
            goto L_0x0288
        L_0x012a:
            boolean r0 = r8.canWrite()     // Catch:{ Exception -> 0x027b, all -> 0x0274 }
            if (r0 != 0) goto L_0x0134
            r0 = 1
            r8.setWritable(r0)     // Catch:{ Exception -> 0x02ce }
        L_0x0134:
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x027b, all -> 0x0274 }
            java.io.InputStream r9 = r11.getInputStream()     // Catch:{ Exception -> 0x027b, all -> 0x0274 }
            r17 = r5
            r5 = 4096(0x1000, float:5.74E-42)
            r0.<init>(r9, r5)     // Catch:{ Exception -> 0x027b, all -> 0x0274 }
            r9 = r0
            java.io.BufferedOutputStream r0 = new java.io.BufferedOutputStream     // Catch:{ Exception -> 0x027b, all -> 0x0274 }
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x027b, all -> 0x0274 }
            r5.<init>(r8)     // Catch:{ Exception -> 0x027b, all -> 0x0274 }
            r20 = r12
            r12 = 4096(0x1000, float:5.74E-42)
            r0.<init>(r5, r12)     // Catch:{ Exception -> 0x026d, all -> 0x0266 }
            r5 = r0
        L_0x0151:
            int r0 = r9.read()     // Catch:{ IOException -> 0x01c1, all -> 0x01bb }
            r12 = -1
            if (r0 != r12) goto L_0x01ad
            r5.flush()     // Catch:{ IOException -> 0x01c1, all -> 0x01bb }
            r5.close()     // Catch:{ Exception -> 0x026d, all -> 0x0266 }
            if (r10 == 0) goto L_0x01a8
            java.lang.String r0 = LOG_TAG     // Catch:{ Exception -> 0x026d, all -> 0x0266 }
            java.lang.String r12 = r8.getAbsolutePath()     // Catch:{ Exception -> 0x026d, all -> 0x0266 }
            r19 = r9
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x026d, all -> 0x0266 }
            r9.<init>()     // Catch:{ Exception -> 0x026d, all -> 0x0266 }
            r21 = r13
            java.lang.String r13 = "Making file read-only: "
            java.lang.StringBuilder r9 = r9.append(r13)     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            java.lang.StringBuilder r9 = r9.append(r12)     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            android.util.Log.i(r0, r9)     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            boolean r0 = r8.setReadOnly()     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            if (r0 == 0) goto L_0x0189
        L_0x0187:
            goto L_0x0213
        L_0x0189:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            r9.<init>()     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            java.lang.String r12 = "Unable to make "
            java.lang.StringBuilder r9 = r9.append(r12)     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            java.lang.StringBuilder r9 = r9.append(r8)     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            java.lang.String r12 = " read-only."
            java.lang.StringBuilder r9 = r9.append(r12)     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            r0.<init>(r9)     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
        L_0x01a7:
            throw r0     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
        L_0x01a8:
            r19 = r9
            r21 = r13
            goto L_0x0213
        L_0x01ad:
            r19 = r9
            r21 = r13
            r5.write(r0)     // Catch:{ IOException -> 0x01b9 }
            r9 = r19
            r13 = r21
            goto L_0x0151
        L_0x01b9:
            r0 = move-exception
            goto L_0x01c6
        L_0x01bb:
            r0 = move-exception
            r19 = r9
            r21 = r13
            goto L_0x021a
        L_0x01c1:
            r0 = move-exception
            r19 = r9
            r21 = r13
        L_0x01c6:
            java.lang.String r9 = LOG_TAG     // Catch:{ all -> 0x0219 }
            java.lang.String r12 = "copying assets"
            android.util.Log.e(r9, r12, r0)     // Catch:{ all -> 0x0219 }
            r14 = 1
            r5.close()     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            if (r10 == 0) goto L_0x0213
            java.lang.String r0 = r8.getAbsolutePath()     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            r12.<init>()     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            java.lang.String r13 = "Making file read-only: "
            java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            java.lang.StringBuilder r0 = r12.append(r0)     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            android.util.Log.i(r9, r0)     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            boolean r0 = r8.setReadOnly()     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            if (r0 == 0) goto L_0x01f4
            goto L_0x0187
        L_0x01f4:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            r9.<init>()     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            java.lang.String r12 = "Unable to make "
            java.lang.StringBuilder r9 = r9.append(r12)     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            java.lang.StringBuilder r9 = r9.append(r8)     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            java.lang.String r12 = " read-only."
            java.lang.StringBuilder r9 = r9.append(r12)     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            r0.<init>(r9)     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            goto L_0x01a7
        L_0x0213:
            r12 = r20
            r13 = r21
            goto L_0x02bb
        L_0x0219:
            r0 = move-exception
        L_0x021a:
            r5.close()     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            if (r10 == 0) goto L_0x0262
            java.lang.String r9 = LOG_TAG     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            java.lang.String r12 = r8.getAbsolutePath()     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            r13.<init>()     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            r22 = r5
            java.lang.String r5 = "Making file read-only: "
            java.lang.StringBuilder r5 = r13.append(r5)     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            java.lang.StringBuilder r5 = r5.append(r12)     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            android.util.Log.i(r9, r5)     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            boolean r5 = r8.setReadOnly()     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            if (r5 != 0) goto L_0x0264
            java.io.IOException r0 = new java.io.IOException     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            r5.<init>()     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            java.lang.String r9 = "Unable to make "
            java.lang.StringBuilder r5 = r5.append(r9)     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            java.lang.StringBuilder r5 = r5.append(r8)     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            java.lang.String r9 = " read-only."
            java.lang.StringBuilder r5 = r5.append(r9)     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            r0.<init>(r5)     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            throw r0     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
        L_0x0262:
            r22 = r5
        L_0x0264:
            throw r0     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
        L_0x0266:
            r0 = move-exception
            r21 = r13
            r12 = r20
            goto L_0x032f
        L_0x026d:
            r0 = move-exception
            r21 = r13
            r12 = r20
            goto L_0x0305
        L_0x0274:
            r0 = move-exception
            r20 = r12
            r21 = r13
            goto L_0x032f
        L_0x027b:
            r0 = move-exception
            r20 = r12
            r21 = r13
            goto L_0x0305
        L_0x0282:
            r17 = r5
            r20 = r12
            r21 = r13
        L_0x0288:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            r5.<init>()     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            java.lang.String r9 = "Unable to create assets directory "
            java.lang.StringBuilder r5 = r5.append(r9)     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            r0.<init>(r5)     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
            throw r0     // Catch:{ Exception -> 0x02a8, all -> 0x02a1 }
        L_0x02a1:
            r0 = move-exception
            r12 = r20
            r13 = r21
            goto L_0x032f
        L_0x02a8:
            r0 = move-exception
            r12 = r20
            r13 = r21
            goto L_0x0305
        L_0x02ae:
            r0 = move-exception
            r20 = r12
            goto L_0x032f
        L_0x02b3:
            r0 = move-exception
            r20 = r12
            goto L_0x0305
        L_0x02b7:
            r18 = r5
            r0 = 1
            r14 = r0
        L_0x02bb:
            if (r14 == 0) goto L_0x02d0
            int r0 = r4 + 1
            java.io.File r0 = getFile(r1, r2, r3, r0)     // Catch:{ Exception -> 0x02ce }
            if (r10 == 0) goto L_0x02c8
            r8.setReadOnly()
        L_0x02c8:
            if (r11 == 0) goto L_0x02cd
            r11.disconnect()
        L_0x02cd:
            return r0
        L_0x02ce:
            r0 = move-exception
            goto L_0x0305
        L_0x02d0:
            if (r10 == 0) goto L_0x02d5
            r8.setReadOnly()
        L_0x02d5:
            if (r11 == 0) goto L_0x02da
            r11.disconnect()
        L_0x02da:
            r0 = 200(0xc8, float:2.8E-43)
            if (r12 != r0) goto L_0x02fc
            java.util.Date r0 = new java.util.Date
            r0.<init>()
            com.google.appinventor.components.runtime.util.HashFile r5 = new com.google.appinventor.components.runtime.util.HashFile
            r5.<init>((java.lang.String) r7, (java.lang.String) r13, (java.util.Date) r0)
            com.google.appinventor.components.runtime.util.HashDatabase r6 = db
            com.google.appinventor.components.runtime.util.HashFile r6 = r6.getHashFile(r7)
            if (r6 != 0) goto L_0x02f6
            com.google.appinventor.components.runtime.util.HashDatabase r6 = db
            r6.insertHashFile(r5)
            goto L_0x02fb
        L_0x02f6:
            com.google.appinventor.components.runtime.util.HashDatabase r6 = db
            r6.updateHashFile(r5)
        L_0x02fb:
            return r8
        L_0x02fc:
            r5 = 0
            return r5
        L_0x02fe:
            r0 = move-exception
            r18 = r5
            goto L_0x032f
        L_0x0302:
            r0 = move-exception
            r18 = r5
        L_0x0305:
            java.lang.String r5 = LOG_TAG     // Catch:{ all -> 0x032e }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x032e }
            r6.<init>()     // Catch:{ all -> 0x032e }
            java.lang.String r9 = "Exception while fetching "
            java.lang.StringBuilder r6 = r6.append(r9)     // Catch:{ all -> 0x032e }
            java.lang.StringBuilder r6 = r6.append(r1)     // Catch:{ all -> 0x032e }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x032e }
            android.util.Log.e(r5, r6, r0)     // Catch:{ all -> 0x032e }
            int r5 = r4 + 1
            java.io.File r5 = getFile(r1, r2, r3, r5)     // Catch:{ all -> 0x032e }
            if (r10 == 0) goto L_0x0328
            r8.setReadOnly()
        L_0x0328:
            if (r11 == 0) goto L_0x032d
            r11.disconnect()
        L_0x032d:
            return r5
        L_0x032e:
            r0 = move-exception
        L_0x032f:
            if (r10 == 0) goto L_0x0334
            r8.setReadOnly()
        L_0x0334:
            if (r11 == 0) goto L_0x0339
            r11.disconnect()
        L_0x0339:
            goto L_0x033b
        L_0x033a:
            throw r0
        L_0x033b:
            goto L_0x033a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.AssetFetcher.getFile(java.lang.String, java.lang.String, java.lang.String, int):java.io.File");
    }

    private static File getDestinationFile(Form form, String asset) {
        String filename;
        if (!asset.contains("/external_comps/") || Build.VERSION.SDK_INT < 34) {
            return new File(QUtil.getReplAssetPath(form, true), asset.substring("assets/".length()));
        }
        File dest = new File(form.getCacheDir(), asset.substring("assets/".length()));
        File parent = dest.getParentFile();
        if (parent == null) {
            throw new IllegalStateException("Unable to determine parent directory for " + dest);
        } else if (parent.exists() || parent.mkdirs()) {
            if (asset.endsWith("/classes.jar")) {
                filename = parent.getName() + ".jar";
            } else {
                String[] parts = asset.split("/");
                filename = parts[parts.length - 1];
            }
            return new File(parent, filename);
        } else {
            throw new YailRuntimeError("Unable to create directory " + parent, "Extensions");
        }
    }

    private static String byteArray2Hex(byte[] hash) {
        Formatter formatter = new Formatter();
        int length = hash.length;
        for (int i = 0; i < length; i++) {
            formatter.format("%02x", new Object[]{Byte.valueOf(hash[i])});
        }
        return formatter.toString();
    }
}
