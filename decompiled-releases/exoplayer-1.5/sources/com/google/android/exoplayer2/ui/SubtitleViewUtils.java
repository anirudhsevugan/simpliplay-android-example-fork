package com.google.android.exoplayer2.ui;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.RelativeSizeSpan;
import com.dreamers.exoplayercore.repack.aE;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.span.LanguageFeatureSpan;
import com.google.android.exoplayer2.util.Assertions;

final class SubtitleViewUtils {
    private SubtitleViewUtils() {
    }

    static final /* synthetic */ boolean lambda$removeAllEmbeddedStyling$0$SubtitleViewUtils(Object obj) {
        return !(obj instanceof LanguageFeatureSpan);
    }

    static final /* synthetic */ boolean lambda$removeEmbeddedFontSizes$1$SubtitleViewUtils(Object obj) {
        return (obj instanceof AbsoluteSizeSpan) || (obj instanceof RelativeSizeSpan);
    }

    public static void removeAllEmbeddedStyling(Cue.Builder builder) {
        builder.j = false;
        if (builder.a instanceof Spanned) {
            if (!(builder.a instanceof Spannable)) {
                builder.a = SpannableString.valueOf(builder.a);
            }
            removeSpansIf((Spannable) Assertions.b((Object) builder.a), SubtitleViewUtils$$Lambda$0.$instance);
        }
        removeEmbeddedFontSizes(builder);
    }

    public static void removeEmbeddedFontSizes(Cue.Builder builder) {
        builder.b(-3.4028235E38f, Integer.MIN_VALUE);
        if (builder.a instanceof Spanned) {
            if (!(builder.a instanceof Spannable)) {
                builder.a = SpannableString.valueOf(builder.a);
            }
            removeSpansIf((Spannable) Assertions.b((Object) builder.a), SubtitleViewUtils$$Lambda$1.$instance);
        }
    }

    private static void removeSpansIf(Spannable spannable, aE aEVar) {
        for (Object obj : spannable.getSpans(0, spannable.length(), Object.class)) {
            if (aEVar.apply(obj)) {
                spannable.removeSpan(obj);
            }
        }
    }

    public static float resolveTextSize(int i, float f, int i2, int i3) {
        float f2;
        if (f == -3.4028235E38f) {
            return -3.4028235E38f;
        }
        switch (i) {
            case 0:
                f2 = (float) i3;
                break;
            case 1:
                f2 = (float) i2;
                break;
            case 2:
                return f;
            default:
                return -3.4028235E38f;
        }
        return f * f2;
    }
}
