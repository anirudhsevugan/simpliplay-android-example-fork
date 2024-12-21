package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class ClearKeyUtil {
    private static String a(String str) {
        return str.replace('-', '+').replace('_', '/');
    }

    public static byte[] a(byte[] bArr) {
        return Util.a >= 27 ? bArr : Util.c(Util.a(bArr).replace('+', '-').replace('/', '_'));
    }

    public static byte[] b(byte[] bArr) {
        if (Util.a >= 27) {
            return bArr;
        }
        try {
            JSONObject jSONObject = new JSONObject(Util.a(bArr));
            StringBuilder sb = new StringBuilder("{\"keys\":[");
            JSONArray jSONArray = jSONObject.getJSONArray("keys");
            for (int i = 0; i < jSONArray.length(); i++) {
                if (i != 0) {
                    sb.append(",");
                }
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                sb.append("{\"k\":\"");
                sb.append(a(jSONObject2.getString("k")));
                sb.append("\",\"kid\":\"");
                sb.append(a(jSONObject2.getString("kid")));
                sb.append("\",\"kty\":\"");
                sb.append(jSONObject2.getString("kty"));
                sb.append("\"}");
            }
            sb.append("]}");
            return Util.c(sb.toString());
        } catch (JSONException e) {
            String valueOf = String.valueOf(Util.a(bArr));
            Log.b("ClearKeyUtil", valueOf.length() != 0 ? "Failed to adjust response data: ".concat(valueOf) : new String("Failed to adjust response data: "), e);
            return bArr;
        }
    }
}
