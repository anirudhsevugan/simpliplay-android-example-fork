package com.google.android.exoplayer2.ui;

import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.util.Locale;

public class DefaultTrackNameProvider implements TrackNameProvider {
    private final Resources resources;

    public DefaultTrackNameProvider(Resources resources2) {
        this.resources = (Resources) Assertions.b((Object) resources2);
    }

    private String buildAudioChannelString(Format format) {
        int i = format.w;
        if (i == -1 || i <= 0) {
            return "";
        }
        switch (i) {
            case 1:
                return "Mono";
            case 2:
                return "Stereo";
            case 6:
            case 7:
                return "5.1 surround sound";
            case 8:
                return "7.1 surround sound";
            default:
                return "Surround sound";
        }
    }

    private String buildBitrateString(Format format) {
        int i = format.h;
        return i == -1 ? "" : "Bitrate : " + (((float) i) / 1000000.0f);
    }

    private String buildLabelString(Format format) {
        return TextUtils.isEmpty(format.b) ? "" : format.b;
    }

    private String buildLanguageOrLabelString(Format format) {
        String joinWithSeparator = joinWithSeparator(buildLanguageString(format), buildRoleString(format));
        return TextUtils.isEmpty(joinWithSeparator) ? buildLabelString(format) : joinWithSeparator;
    }

    private String buildLanguageString(Format format) {
        String str = format.c;
        if (TextUtils.isEmpty(str) || "und".equals(str)) {
            return "";
        }
        return (Util.a >= 21 ? Locale.forLanguageTag(str) : new Locale(str)).getDisplayName();
    }

    private String buildResolutionString(Format format) {
        int i = format.width;
        int i2 = format.height;
        return (i == -1 || i2 == -1) ? "" : "width :" + i + " height : " + i2;
    }

    private String buildRoleString(Format format) {
        String str = (format.e & 2) != 0 ? "Alternate" : "";
        if ((format.e & 4) != 0) {
            str = joinWithSeparator(str, "Supplementary");
        }
        if ((format.e & 8) != 0) {
            str = joinWithSeparator(str, "Commentary");
        }
        if ((format.e & 1088) == 0) {
            return str;
        }
        return joinWithSeparator(str, "CC");
    }

    private static int inferPrimaryTrackType(Format format) {
        int h = MimeTypes.h(format.l);
        if (h != -1) {
            return h;
        }
        if (MimeTypes.d(format.i) != null) {
            return 2;
        }
        if (MimeTypes.e(format.i) != null) {
            return 1;
        }
        if (format.width == -1 && format.height == -1) {
            return (format.w == -1 && format.x == -1) ? -1 : 1;
        }
        return 2;
    }

    private String joinWithSeparator(String... strArr) {
        String str = "";
        for (String str2 : strArr) {
            if (str2.length() > 0) {
                str = TextUtils.isEmpty(str) ? str2 : "ItemList : " + str + " \n Item : " + str2;
            }
        }
        return str;
    }

    public String getTrackName(Format format) {
        int inferPrimaryTrackType = inferPrimaryTrackType(format);
        String joinWithSeparator = inferPrimaryTrackType == 2 ? joinWithSeparator(buildRoleString(format), buildResolutionString(format), buildBitrateString(format)) : inferPrimaryTrackType == 1 ? joinWithSeparator(buildLanguageOrLabelString(format), buildAudioChannelString(format), buildBitrateString(format)) : buildLanguageOrLabelString(format);
        return joinWithSeparator.length() == 0 ? "Unknown" : joinWithSeparator;
    }
}
