package com.dreamers.exoplayerui.repack;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import com.dreamers.exoplayerui.ExoplayerUi;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.ReplForm;
import com.google.appinventor.components.runtime.util.HashDbInitialize;
import java.io.FileInputStream;
import java.io.InputStream;

public final class k {
    public static final int a(String str) {
        n.b(str, "mode");
        switch (str.hashCode()) {
            case -1973567287:
                return !str.equals(ExoplayerUi.RESIZE_MODE_FIXED_WIDTH) ? 0 : 1;
            case -1484038524:
                return !str.equals(ExoplayerUi.RESIZE_MODE_FIXED_HEIGHT) ? 0 : 2;
            case -333479532:
                return !str.equals(ExoplayerUi.RESIZE_MODE_FILL) ? 0 : 3;
            case -332877852:
                return !str.equals(ExoplayerUi.RESIZE_MODE_ZOOM) ? 0 : 4;
            case -287852064:
                str.equals(ExoplayerUi.RESIZE_MODE_FIT);
                return 0;
            default:
                return 0;
        }
    }

    public static final Typeface a(Context context, String str) {
        n.b(context, "context");
        n.b(str, PropertyTypeConstants.PROPERTY_TYPE_ASSET);
        try {
            return Typeface.createFromFile(b(context, str));
        } catch (Exception e) {
            Log.e(ExoplayerUi.LOG_TAG, "getTypeface | Failed to get typeface from path : " + str + " with error : " + e);
            return null;
        }
    }

    public static final Drawable a(Form form, String str) {
        n.b(form, "form");
        n.b(str, HashDbInitialize.HashTable.COLUMN_1_NAME);
        try {
            InputStream b = b(form, str);
            Drawable createFromStream = Drawable.createFromStream(b, (String) null);
            n.a((Object) createFromStream, "createFromStream(inputStream, null)");
            if (b != null) {
                b.close();
            }
            return createFromStream;
        } catch (Exception e) {
            Log.v(ExoplayerUi.LOG_TAG, n.a("getDrawable : Error = ", (Object) e));
            return null;
        }
    }

    public static final int b(String str) {
        n.b(str, "mode");
        switch (str.hashCode()) {
            case 75160172:
                return !str.equals(ExoplayerUi.SHOW_BUFFERING_NEVER) ? 1 : 0;
            case 1922836808:
                str.equals(ExoplayerUi.SHOW_BUFFERING_WHEN_PLAYING);
                return 1;
            case 1964277295:
                return str.equals(ExoplayerUi.SHOW_BUFFERING_ALWAYS) ? 2 : 1;
            default:
                return 1;
        }
    }

    private static InputStream b(Form form, String str) {
        n.b(form, "form");
        n.b(str, "file");
        Activity $context = form.$context();
        boolean z = form instanceof ReplForm;
        if (!z) {
            return $context.getAssets().open(str);
        }
        try {
            n.a((Object) $context, "context");
            String b = b((Context) $context, str);
            Log.v(ExoplayerUi.LOG_TAG, n.a("getAsset | Filepath = ", (Object) b));
            return new FileInputStream(b);
        } catch (Exception e) {
            Log.e(ExoplayerUi.LOG_TAG, "getAsset | Debug Mode : " + z + " | Error : " + e);
            return null;
        }
    }

    private static final String b(Context context, String str) {
        String name = context.getClass().getName();
        n.a((Object) name, "context.javaClass.name");
        return p.a(name, "makeroid") ? Build.VERSION.SDK_INT >= 29 ? context.getExternalFilesDir((String) null) + "/assets/" + str : n.a("/storage/emulated/0/Kodular/assets/", (Object) str) : context.getExternalFilesDir((String) null) + "/AppInventor/assets/" + str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0035 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int c(java.lang.String r2) {
        /*
            java.lang.String r0 = "mode"
            com.dreamers.exoplayerui.repack.n.b(r2, r0)
            int r0 = r2.hashCode()
            r1 = 0
            switch(r0) {
                case 65921: goto L_0x002a;
                case 79183: goto L_0x0024;
                case 79430: goto L_0x0019;
                case 2071398445: goto L_0x000e;
                default: goto L_0x000d;
            }
        L_0x000d:
            goto L_0x0035
        L_0x000e:
            java.lang.String r0 = "One & All"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0017
            goto L_0x0035
        L_0x0017:
            r2 = 3
            return r2
        L_0x0019:
            java.lang.String r0 = "One"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0022
            goto L_0x0035
        L_0x0022:
            r2 = 1
            return r2
        L_0x0024:
            java.lang.String r0 = "Off"
            r2.equals(r0)
            return r1
        L_0x002a:
            java.lang.String r0 = "All"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0033
            goto L_0x0035
        L_0x0033:
            r2 = 2
            return r2
        L_0x0035:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dreamers.exoplayerui.repack.k.c(java.lang.String):int");
    }
}
