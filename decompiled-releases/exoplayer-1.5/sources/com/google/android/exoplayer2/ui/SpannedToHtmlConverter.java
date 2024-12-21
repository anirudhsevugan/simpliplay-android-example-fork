package com.google.android.exoplayer2.ui;

import android.text.Html;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import android.util.SparseArray;
import com.dreamers.exoplayercore.repack.bM;
import com.google.android.exoplayer2.text.cea.Cea708Decoder$Cea708CueInfo$$ExternalSyntheticBackport0;
import com.google.android.exoplayer2.text.span.HorizontalTextInVerticalContextSpan;
import com.google.android.exoplayer2.text.span.RubySpan;
import com.google.android.exoplayer2.text.span.TextEmphasisSpan;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

final class SpannedToHtmlConverter {
    private static final Pattern NEWLINE_PATTERN = Pattern.compile("(&#13;)?&#10;");

    public class HtmlAndCss {
        public final Map cssRuleSets;
        public final String html;

        private HtmlAndCss(String str, Map map) {
            this.html = str;
            this.cssRuleSets = map;
        }
    }

    final class SpanInfo {
        /* access modifiers changed from: private */
        public static final Comparator FOR_CLOSING_TAGS = SpannedToHtmlConverter$SpanInfo$$Lambda$1.$instance;
        /* access modifiers changed from: private */
        public static final Comparator FOR_OPENING_TAGS = SpannedToHtmlConverter$SpanInfo$$Lambda$0.$instance;
        public final String closingTag;
        public final int end;
        public final String openingTag;
        public final int start;

        private SpanInfo(int i, int i2, String str, String str2) {
            this.start = i;
            this.end = i2;
            this.openingTag = str;
            this.closingTag = str2;
        }

        static final /* synthetic */ int lambda$static$0$SpannedToHtmlConverter$SpanInfo(SpanInfo spanInfo, SpanInfo spanInfo2) {
            int m = Cea708Decoder$Cea708CueInfo$$ExternalSyntheticBackport0.m(spanInfo2.end, spanInfo.end);
            if (m != 0) {
                return m;
            }
            int compareTo = spanInfo.openingTag.compareTo(spanInfo2.openingTag);
            return compareTo != 0 ? compareTo : spanInfo.closingTag.compareTo(spanInfo2.closingTag);
        }

        static final /* synthetic */ int lambda$static$1$SpannedToHtmlConverter$SpanInfo(SpanInfo spanInfo, SpanInfo spanInfo2) {
            int m = Cea708Decoder$Cea708CueInfo$$ExternalSyntheticBackport0.m(spanInfo2.start, spanInfo.start);
            if (m != 0) {
                return m;
            }
            int compareTo = spanInfo2.openingTag.compareTo(spanInfo.openingTag);
            return compareTo != 0 ? compareTo : spanInfo2.closingTag.compareTo(spanInfo.closingTag);
        }
    }

    final class Transition {
        /* access modifiers changed from: private */
        public final List spansAdded = new ArrayList();
        /* access modifiers changed from: private */
        public final List spansRemoved = new ArrayList();
    }

    private SpannedToHtmlConverter() {
    }

    public static HtmlAndCss convert(CharSequence charSequence, float f) {
        if (charSequence == null) {
            return new HtmlAndCss("", bM.a());
        }
        if (!(charSequence instanceof Spanned)) {
            return new HtmlAndCss(escapeHtml(charSequence), bM.a());
        }
        Spanned spanned = (Spanned) charSequence;
        HashSet<Integer> hashSet = new HashSet<>();
        int i = 0;
        for (BackgroundColorSpan backgroundColor : (BackgroundColorSpan[]) spanned.getSpans(0, spanned.length(), BackgroundColorSpan.class)) {
            hashSet.add(Integer.valueOf(backgroundColor.getBackgroundColor()));
        }
        HashMap hashMap = new HashMap();
        for (Integer intValue : hashSet) {
            int intValue2 = intValue.intValue();
            hashMap.put(HtmlUtils.cssAllClassDescendantsSelector("bg_".concat(String.valueOf(intValue2))), Util.a("background-color:%s;", HtmlUtils.toCssRgba(intValue2)));
        }
        SparseArray findSpanTransitions = findSpanTransitions(spanned, f);
        StringBuilder sb = new StringBuilder(spanned.length());
        int i2 = 0;
        while (i < findSpanTransitions.size()) {
            int keyAt = findSpanTransitions.keyAt(i);
            sb.append(escapeHtml(spanned.subSequence(i2, keyAt)));
            Transition transition = (Transition) findSpanTransitions.get(keyAt);
            Collections.sort(transition.spansRemoved, SpanInfo.FOR_CLOSING_TAGS);
            for (SpanInfo spanInfo : transition.spansRemoved) {
                sb.append(spanInfo.closingTag);
            }
            Collections.sort(transition.spansAdded, SpanInfo.FOR_OPENING_TAGS);
            for (SpanInfo spanInfo2 : transition.spansAdded) {
                sb.append(spanInfo2.openingTag);
            }
            i++;
            i2 = keyAt;
        }
        sb.append(escapeHtml(spanned.subSequence(i2, spanned.length())));
        return new HtmlAndCss(sb.toString(), hashMap);
    }

    private static String escapeHtml(CharSequence charSequence) {
        return NEWLINE_PATTERN.matcher(Html.escapeHtml(charSequence)).replaceAll("<br>");
    }

    private static SparseArray findSpanTransitions(Spanned spanned, float f) {
        SparseArray sparseArray = new SparseArray();
        for (Object obj : spanned.getSpans(0, spanned.length(), Object.class)) {
            String openingTag = getOpeningTag(obj, f);
            String closingTag = getClosingTag(obj);
            int spanStart = spanned.getSpanStart(obj);
            int spanEnd = spanned.getSpanEnd(obj);
            if (openingTag != null) {
                Assertions.b((Object) closingTag);
                SpanInfo spanInfo = new SpanInfo(spanStart, spanEnd, openingTag, closingTag);
                getOrCreate(sparseArray, spanStart).spansAdded.add(spanInfo);
                getOrCreate(sparseArray, spanEnd).spansRemoved.add(spanInfo);
            }
        }
        return sparseArray;
    }

    private static String getClosingTag(Object obj) {
        if ((obj instanceof StrikethroughSpan) || (obj instanceof ForegroundColorSpan) || (obj instanceof BackgroundColorSpan) || (obj instanceof HorizontalTextInVerticalContextSpan) || (obj instanceof AbsoluteSizeSpan) || (obj instanceof RelativeSizeSpan) || (obj instanceof TextEmphasisSpan)) {
            return "</span>";
        }
        if (!(obj instanceof TypefaceSpan)) {
            if (obj instanceof StyleSpan) {
                switch (((StyleSpan) obj).getStyle()) {
                    case 1:
                        return "</b>";
                    case 2:
                        return "</i>";
                    case 3:
                        return "</i></b>";
                }
            } else if (obj instanceof RubySpan) {
                return "<rt>" + escapeHtml(((RubySpan) obj).a) + "</rt></ruby>";
            } else {
                if (obj instanceof UnderlineSpan) {
                    return "</u>";
                }
            }
            return null;
        } else if (((TypefaceSpan) obj).getFamily() != null) {
            return "</span>";
        } else {
            return null;
        }
    }

    private static String getOpeningTag(Object obj, float f) {
        if (obj instanceof StrikethroughSpan) {
            return "<span style='text-decoration:line-through;'>";
        }
        if (obj instanceof ForegroundColorSpan) {
            return Util.a("<span style='color:%s;'>", HtmlUtils.toCssRgba(((ForegroundColorSpan) obj).getForegroundColor()));
        } else if (obj instanceof BackgroundColorSpan) {
            return Util.a("<span class='bg_%s'>", Integer.valueOf(((BackgroundColorSpan) obj).getBackgroundColor()));
        } else if (obj instanceof HorizontalTextInVerticalContextSpan) {
            return "<span style='text-combine-upright:all;'>";
        } else {
            if (obj instanceof AbsoluteSizeSpan) {
                AbsoluteSizeSpan absoluteSizeSpan = (AbsoluteSizeSpan) obj;
                boolean dip = absoluteSizeSpan.getDip();
                float size = (float) absoluteSizeSpan.getSize();
                if (!dip) {
                    size /= f;
                }
                return Util.a("<span style='font-size:%.2fpx;'>", Float.valueOf(size));
            } else if (obj instanceof RelativeSizeSpan) {
                return Util.a("<span style='font-size:%.2f%%;'>", Float.valueOf(((RelativeSizeSpan) obj).getSizeChange() * 100.0f));
            } else if (obj instanceof TypefaceSpan) {
                String family = ((TypefaceSpan) obj).getFamily();
                if (family == null) {
                    return null;
                }
                return Util.a("<span style='font-family:\"%s\";'>", family);
            } else if (obj instanceof StyleSpan) {
                switch (((StyleSpan) obj).getStyle()) {
                    case 1:
                        return "<b>";
                    case 2:
                        return "<i>";
                    case 3:
                        return "<b><i>";
                    default:
                        return null;
                }
            } else if (obj instanceof RubySpan) {
                switch (((RubySpan) obj).b) {
                    case -1:
                        return "<ruby style='ruby-position:unset;'>";
                    case 1:
                        return "<ruby style='ruby-position:over;'>";
                    case 2:
                        return "<ruby style='ruby-position:under;'>";
                    default:
                        return null;
                }
            } else if (obj instanceof UnderlineSpan) {
                return "<u>";
            } else {
                if (!(obj instanceof TextEmphasisSpan)) {
                    return null;
                }
                TextEmphasisSpan textEmphasisSpan = (TextEmphasisSpan) obj;
                return Util.a("<span style='-webkit-text-emphasis-style:%1$s;text-emphasis-style:%1$s;-webkit-text-emphasis-position:%2$s;text-emphasis-position:%2$s;display:inline-block;'>", getTextEmphasisStyle(textEmphasisSpan.a, textEmphasisSpan.b), getTextEmphasisPosition(textEmphasisSpan.c));
            }
        }
    }

    private static Transition getOrCreate(SparseArray sparseArray, int i) {
        Transition transition = (Transition) sparseArray.get(i);
        if (transition != null) {
            return transition;
        }
        Transition transition2 = new Transition();
        sparseArray.put(i, transition2);
        return transition2;
    }

    private static String getTextEmphasisPosition(int i) {
        switch (i) {
            case 2:
                return "under left";
            default:
                return "over right";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0020  */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x0014  */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001a  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getTextEmphasisStyle(int r1, int r2) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            switch(r2) {
                case 1: goto L_0x000c;
                case 2: goto L_0x0009;
                default: goto L_0x0008;
            }
        L_0x0008:
            goto L_0x0011
        L_0x0009:
            java.lang.String r2 = "open "
            goto L_0x000e
        L_0x000c:
            java.lang.String r2 = "filled "
        L_0x000e:
            r0.append(r2)
        L_0x0011:
            switch(r1) {
                case 0: goto L_0x0020;
                case 1: goto L_0x001d;
                case 2: goto L_0x001a;
                case 3: goto L_0x0017;
                default: goto L_0x0014;
            }
        L_0x0014:
            java.lang.String r1 = "unset"
            goto L_0x0022
        L_0x0017:
            java.lang.String r1 = "sesame"
            goto L_0x0022
        L_0x001a:
            java.lang.String r1 = "dot"
            goto L_0x0022
        L_0x001d:
            java.lang.String r1 = "circle"
            goto L_0x0022
        L_0x0020:
            java.lang.String r1 = "none"
        L_0x0022:
            r0.append(r1)
            java.lang.String r1 = r0.toString()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.SpannedToHtmlConverter.getTextEmphasisStyle(int, int):java.lang.String");
    }
}
