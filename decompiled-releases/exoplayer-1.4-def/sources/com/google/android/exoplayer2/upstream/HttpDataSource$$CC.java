package com.google.android.exoplayer2.upstream;

import android.text.TextUtils;
import com.dreamers.exoplayercore.repack.C0000a;
import com.google.appinventor.components.common.PropertyTypeConstants;

public abstract /* synthetic */ class HttpDataSource$$CC {
    static /* synthetic */ boolean a(String str) {
        if (str == null) {
            return false;
        }
        String a = C0000a.a(str);
        return !TextUtils.isEmpty(a) && (!a.contains(PropertyTypeConstants.PROPERTY_TYPE_TEXT) || a.contains("text/vtt")) && !a.contains("html") && !a.contains("xml");
    }
}
