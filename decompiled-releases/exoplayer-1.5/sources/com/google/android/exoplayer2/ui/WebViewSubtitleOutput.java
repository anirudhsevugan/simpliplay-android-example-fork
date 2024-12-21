package com.google.android.exoplayer2.ui;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;
import android.widget.FrameLayout;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.ui.SubtitleView;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class WebViewSubtitleOutput extends FrameLayout implements SubtitleView.Output {
    private static final float CSS_LINE_HEIGHT = 1.2f;
    private static final String DEFAULT_BACKGROUND_CSS_CLASS = "default_bg";
    private float bottomPaddingFraction;
    private final CanvasSubtitleOutput canvasSubtitleOutput;
    private float defaultTextSize;
    private int defaultTextSizeType;
    private CaptionStyleCompat style;
    private List textCues;
    private final WebView webView;

    /* renamed from: com.google.android.exoplayer2.ui.WebViewSubtitleOutput$2  reason: invalid class name */
    /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$android$text$Layout$Alignment;

        static {
            int[] iArr = new int[Layout.Alignment.values().length];
            $SwitchMap$android$text$Layout$Alignment = iArr;
            try {
                iArr[Layout.Alignment.ALIGN_NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$text$Layout$Alignment[Layout.Alignment.ALIGN_OPPOSITE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$text$Layout$Alignment[Layout.Alignment.ALIGN_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public WebViewSubtitleOutput(Context context) {
        this(context, (AttributeSet) null);
    }

    public WebViewSubtitleOutput(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.textCues = Collections.emptyList();
        this.style = CaptionStyleCompat.DEFAULT;
        this.defaultTextSize = 0.0533f;
        this.defaultTextSizeType = 0;
        this.bottomPaddingFraction = 0.08f;
        CanvasSubtitleOutput canvasSubtitleOutput2 = new CanvasSubtitleOutput(context, attributeSet);
        this.canvasSubtitleOutput = canvasSubtitleOutput2;
        AnonymousClass1 r2 = new WebView(context, attributeSet) {
            public boolean onTouchEvent(MotionEvent motionEvent) {
                super.onTouchEvent(motionEvent);
                return false;
            }

            public boolean performClick() {
                super.performClick();
                return false;
            }
        };
        this.webView = r2;
        r2.setBackgroundColor(0);
        addView(canvasSubtitleOutput2);
        addView(r2);
    }

    private static int anchorTypeToTranslatePercent(int i) {
        switch (i) {
            case 1:
                return -50;
            case 2:
                return -100;
            default:
                return 0;
        }
    }

    private static String convertAlignmentToCss(Layout.Alignment alignment) {
        if (alignment == null) {
            return "center";
        }
        switch (AnonymousClass2.$SwitchMap$android$text$Layout$Alignment[alignment.ordinal()]) {
            case 1:
                return "start";
            case 2:
                return "end";
            default:
                return "center";
        }
    }

    private static String convertCaptionStyleToCssTextShadow(CaptionStyleCompat captionStyleCompat) {
        switch (captionStyleCompat.edgeType) {
            case 1:
                return Util.a("1px 1px 0 %1$s, 1px -1px 0 %1$s, -1px 1px 0 %1$s, -1px -1px 0 %1$s", HtmlUtils.toCssRgba(captionStyleCompat.edgeColor));
            case 2:
                return Util.a("0.1em 0.12em 0.15em %s", HtmlUtils.toCssRgba(captionStyleCompat.edgeColor));
            case 3:
                return Util.a("0.06em 0.08em 0.15em %s", HtmlUtils.toCssRgba(captionStyleCompat.edgeColor));
            case 4:
                return Util.a("-0.05em -0.05em 0.15em %s", HtmlUtils.toCssRgba(captionStyleCompat.edgeColor));
            default:
                return "unset";
        }
    }

    private String convertTextSizeToCss(int i, float f) {
        float resolveTextSize = SubtitleViewUtils.resolveTextSize(i, f, getHeight(), (getHeight() - getPaddingTop()) - getPaddingBottom());
        if (resolveTextSize == -3.4028235E38f) {
            return "unset";
        }
        return Util.a("%.2fpx", Float.valueOf(resolveTextSize / getContext().getResources().getDisplayMetrics().density));
    }

    private static String convertVerticalTypeToCss(int i) {
        switch (i) {
            case 1:
                return "vertical-rl";
            case 2:
                return "vertical-lr";
            default:
                return "horizontal-tb";
        }
    }

    private static String getBlockShearTransformFunction(Cue cue) {
        if (cue.r == 0.0f) {
            return "";
        }
        return Util.a("%s(%.2fdeg)", (cue.q == 2 || cue.q == 1) ? "skewY" : "skewX", Float.valueOf(cue.r));
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x014f, code lost:
        if (r13 != false) goto L_0x015c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0152, code lost:
        r21 = "left";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0155, code lost:
        if (r13 != false) goto L_0x0152;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateWebView() {
        /*
            r26 = this;
            r0 = r26
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r2 = 4
            java.lang.Object[] r3 = new java.lang.Object[r2]
            com.google.android.exoplayer2.ui.CaptionStyleCompat r4 = r0.style
            int r4 = r4.foregroundColor
            java.lang.String r4 = com.google.android.exoplayer2.ui.HtmlUtils.toCssRgba(r4)
            r5 = 0
            r3[r5] = r4
            int r4 = r0.defaultTextSizeType
            float r6 = r0.defaultTextSize
            java.lang.String r4 = r0.convertTextSizeToCss(r4, r6)
            r6 = 1
            r3[r6] = r4
            r4 = 1067030938(0x3f99999a, float:1.2)
            java.lang.Float r7 = java.lang.Float.valueOf(r4)
            r8 = 2
            r3[r8] = r7
            com.google.android.exoplayer2.ui.CaptionStyleCompat r7 = r0.style
            java.lang.String r7 = convertCaptionStyleToCssTextShadow(r7)
            r9 = 3
            r3[r9] = r7
            java.lang.String r7 = "<body><div style='-webkit-user-select:none;position:fixed;top:0;bottom:0;left:0;right:0;color:%s;font-size:%s;line-height:%.2f;text-shadow:%s;'>"
            java.lang.String r3 = com.google.android.exoplayer2.util.Util.a((java.lang.String) r7, (java.lang.Object[]) r3)
            r1.append(r3)
            java.util.HashMap r3 = new java.util.HashMap
            r3.<init>()
            java.lang.String r7 = "default_bg"
            java.lang.String r10 = com.google.android.exoplayer2.ui.HtmlUtils.cssAllClassDescendantsSelector(r7)
            java.lang.Object[] r11 = new java.lang.Object[r6]
            com.google.android.exoplayer2.ui.CaptionStyleCompat r12 = r0.style
            int r12 = r12.backgroundColor
            java.lang.String r12 = com.google.android.exoplayer2.ui.HtmlUtils.toCssRgba(r12)
            r11[r5] = r12
            java.lang.String r12 = "background-color:%s;"
            java.lang.String r11 = com.google.android.exoplayer2.util.Util.a((java.lang.String) r12, (java.lang.Object[]) r11)
            r3.put(r10, r11)
            r10 = 0
        L_0x005d:
            java.util.List r11 = r0.textCues
            int r11 = r11.size()
            if (r10 >= r11) goto L_0x0267
            java.util.List r11 = r0.textCues
            java.lang.Object r11 = r11.get(r10)
            com.google.android.exoplayer2.text.Cue r11 = (com.google.android.exoplayer2.text.Cue) r11
            float r12 = r11.i
            r13 = -8388609(0xffffffffff7fffff, float:-3.4028235E38)
            r14 = 1120403456(0x42c80000, float:100.0)
            int r12 = (r12 > r13 ? 1 : (r12 == r13 ? 0 : -1))
            if (r12 == 0) goto L_0x007d
            float r12 = r11.i
            float r12 = r12 * r14
            goto L_0x007f
        L_0x007d:
            r12 = 1112014848(0x42480000, float:50.0)
        L_0x007f:
            int r15 = r11.j
            int r15 = anchorTypeToTranslatePercent(r15)
            float r2 = r11.f
            r17 = 1065353216(0x3f800000, float:1.0)
            java.lang.String r9 = "%.2f%%"
            int r2 = (r2 > r13 ? 1 : (r2 == r13 ? 0 : -1))
            if (r2 == 0) goto L_0x00e9
            int r2 = r11.g
            switch(r2) {
                case 1: goto L_0x00b0;
                default: goto L_0x0094;
            }
        L_0x0094:
            java.lang.Object[] r2 = new java.lang.Object[r6]
            float r8 = r11.f
            float r8 = r8 * r14
            java.lang.Float r8 = java.lang.Float.valueOf(r8)
            r2[r5] = r8
            java.lang.String r2 = com.google.android.exoplayer2.util.Util.a((java.lang.String) r9, (java.lang.Object[]) r2)
            int r8 = r11.q
            if (r8 != r6) goto L_0x00e2
            int r8 = r11.h
            int r8 = anchorTypeToTranslatePercent(r8)
            int r8 = -r8
            goto L_0x00fd
        L_0x00b0:
            float r2 = r11.f
            r18 = 0
            java.lang.String r8 = "%.2fem"
            int r2 = (r2 > r18 ? 1 : (r2 == r18 ? 0 : -1))
            if (r2 < 0) goto L_0x00cc
            java.lang.Object[] r2 = new java.lang.Object[r6]
            float r13 = r11.f
            float r13 = r13 * r4
            java.lang.Float r13 = java.lang.Float.valueOf(r13)
            r2[r5] = r13
            java.lang.String r2 = com.google.android.exoplayer2.util.Util.a((java.lang.String) r8, (java.lang.Object[]) r2)
            r8 = 0
            goto L_0x00fd
        L_0x00cc:
            java.lang.Object[] r2 = new java.lang.Object[r6]
            float r13 = r11.f
            float r13 = -r13
            float r13 = r13 - r17
            float r13 = r13 * r4
            java.lang.Float r13 = java.lang.Float.valueOf(r13)
            r2[r5] = r13
            java.lang.String r2 = com.google.android.exoplayer2.util.Util.a((java.lang.String) r8, (java.lang.Object[]) r2)
            r8 = 0
            r13 = 1
            goto L_0x00fe
        L_0x00e2:
            int r8 = r11.h
            int r8 = anchorTypeToTranslatePercent(r8)
            goto L_0x00fd
        L_0x00e9:
            java.lang.Object[] r2 = new java.lang.Object[r6]
            float r8 = r0.bottomPaddingFraction
            float r17 = r17 - r8
            float r17 = r17 * r14
            java.lang.Float r8 = java.lang.Float.valueOf(r17)
            r2[r5] = r8
            java.lang.String r2 = com.google.android.exoplayer2.util.Util.a((java.lang.String) r9, (java.lang.Object[]) r2)
            r8 = -100
        L_0x00fd:
            r13 = 0
        L_0x00fe:
            float r4 = r11.k
            r18 = -8388609(0xffffffffff7fffff, float:-3.4028235E38)
            int r4 = (r4 > r18 ? 1 : (r4 == r18 ? 0 : -1))
            if (r4 == 0) goto L_0x0118
            java.lang.Object[] r4 = new java.lang.Object[r6]
            float r6 = r11.k
            float r6 = r6 * r14
            java.lang.Float r6 = java.lang.Float.valueOf(r6)
            r4[r5] = r6
            java.lang.String r4 = com.google.android.exoplayer2.util.Util.a((java.lang.String) r9, (java.lang.Object[]) r4)
            goto L_0x011a
        L_0x0118:
            java.lang.String r4 = "fit-content"
        L_0x011a:
            android.text.Layout$Alignment r6 = r11.c
            java.lang.String r6 = convertAlignmentToCss(r6)
            int r9 = r11.q
            java.lang.String r9 = convertVerticalTypeToCss(r9)
            int r14 = r11.o
            float r5 = r11.p
            java.lang.String r5 = r0.convertTextSizeToCss(r14, r5)
            boolean r14 = r11.m
            if (r14 == 0) goto L_0x0135
            int r14 = r11.n
            goto L_0x0139
        L_0x0135:
            com.google.android.exoplayer2.ui.CaptionStyleCompat r14 = r0.style
            int r14 = r14.windowColor
        L_0x0139:
            java.lang.String r14 = com.google.android.exoplayer2.ui.HtmlUtils.toCssRgba(r14)
            r20 = r8
            int r8 = r11.q
            java.lang.String r21 = "right"
            java.lang.String r22 = "top"
            java.lang.String r23 = "left"
            switch(r8) {
                case 1: goto L_0x0155;
                case 2: goto L_0x014f;
                default: goto L_0x014a;
            }
        L_0x014a:
            if (r13 == 0) goto L_0x0158
            java.lang.String r22 = "bottom"
            goto L_0x0158
        L_0x014f:
            if (r13 == 0) goto L_0x0152
            goto L_0x015c
        L_0x0152:
            r21 = r23
            goto L_0x015c
        L_0x0155:
            if (r13 == 0) goto L_0x015c
            goto L_0x0152
        L_0x0158:
            r21 = r22
            r22 = r23
        L_0x015c:
            int r8 = r11.q
            r13 = 2
            if (r8 == r13) goto L_0x0170
            int r8 = r11.q
            r13 = 1
            if (r8 != r13) goto L_0x0167
            goto L_0x0170
        L_0x0167:
            java.lang.String r8 = "width"
            r25 = r20
            r20 = r15
            r15 = r25
            goto L_0x0172
        L_0x0170:
            java.lang.String r8 = "height"
        L_0x0172:
            java.lang.CharSequence r13 = r11.b
            android.content.Context r23 = r26.getContext()
            android.content.res.Resources r23 = r23.getResources()
            android.util.DisplayMetrics r0 = r23.getDisplayMetrics()
            float r0 = r0.density
            com.google.android.exoplayer2.ui.SpannedToHtmlConverter$HtmlAndCss r0 = com.google.android.exoplayer2.ui.SpannedToHtmlConverter.convert(r13, r0)
            java.util.Set r13 = r3.keySet()
            java.util.Iterator r13 = r13.iterator()
        L_0x018e:
            boolean r23 = r13.hasNext()
            if (r23 == 0) goto L_0x01c2
            java.lang.Object r23 = r13.next()
            r24 = r13
            r13 = r23
            java.lang.String r13 = (java.lang.String) r13
            r23 = r0
            java.lang.Object r0 = r3.get(r13)
            java.lang.Object r0 = r3.put(r13, r0)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 == 0) goto L_0x01b9
            java.lang.Object r13 = r3.get(r13)
            boolean r0 = r0.equals(r13)
            if (r0 == 0) goto L_0x01b7
            goto L_0x01b9
        L_0x01b7:
            r0 = 0
            goto L_0x01ba
        L_0x01b9:
            r0 = 1
        L_0x01ba:
            com.google.android.exoplayer2.util.Assertions.b((boolean) r0)
            r0 = r23
            r13 = r24
            goto L_0x018e
        L_0x01c2:
            r23 = r0
            r0 = 14
            java.lang.Object[] r0 = new java.lang.Object[r0]
            java.lang.Integer r13 = java.lang.Integer.valueOf(r10)
            r19 = 0
            r0[r19] = r13
            r13 = 1
            r0[r13] = r22
            java.lang.Float r12 = java.lang.Float.valueOf(r12)
            r13 = 2
            r0[r13] = r12
            r12 = 3
            r0[r12] = r21
            r16 = 4
            r0[r16] = r2
            r2 = 5
            r0[r2] = r8
            r2 = 6
            r0[r2] = r4
            r2 = 7
            r0[r2] = r6
            r2 = 8
            r0[r2] = r9
            r2 = 9
            r0[r2] = r5
            r2 = 10
            r0[r2] = r14
            r2 = 11
            java.lang.Integer r4 = java.lang.Integer.valueOf(r20)
            r0[r2] = r4
            r2 = 12
            java.lang.Integer r4 = java.lang.Integer.valueOf(r15)
            r0[r2] = r4
            r2 = 13
            java.lang.String r4 = getBlockShearTransformFunction(r11)
            r0[r2] = r4
            java.lang.String r2 = "<div style='position:absolute;z-index:%s;%s:%.2f%%;%s:%s;%s:%s;text-align:%s;writing-mode:%s;font-size:%s;background-color:%s;transform:translate(%s%%,%s%%)%s;'>"
            java.lang.String r0 = com.google.android.exoplayer2.util.Util.a((java.lang.String) r2, (java.lang.Object[]) r0)
            java.lang.StringBuilder r0 = r1.append(r0)
            r2 = 1
            java.lang.Object[] r4 = new java.lang.Object[r2]
            r5 = 0
            r4[r5] = r7
            java.lang.String r6 = "<span class='%s'>"
            java.lang.String r4 = com.google.android.exoplayer2.util.Util.a((java.lang.String) r6, (java.lang.Object[]) r4)
            r0.append(r4)
            android.text.Layout$Alignment r0 = r11.d
            if (r0 == 0) goto L_0x024d
            java.lang.Object[] r0 = new java.lang.Object[r2]
            android.text.Layout$Alignment r2 = r11.d
            java.lang.String r2 = convertAlignmentToCss(r2)
            r0[r5] = r2
            java.lang.String r2 = "<span style='display:inline-block; text-align:%s;'>"
            java.lang.String r0 = com.google.android.exoplayer2.util.Util.a((java.lang.String) r2, (java.lang.Object[]) r0)
            java.lang.StringBuilder r0 = r1.append(r0)
            r2 = r23
            java.lang.String r2 = r2.html
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r2 = "</span>"
            r0.append(r2)
            goto L_0x0254
        L_0x024d:
            r2 = r23
            java.lang.String r0 = r2.html
            r1.append(r0)
        L_0x0254:
            java.lang.String r0 = "</span></div>"
            r1.append(r0)
            int r10 = r10 + 1
            r2 = 4
            r4 = 1067030938(0x3f99999a, float:1.2)
            r5 = 0
            r8 = 2
            r9 = 3
            r0 = r26
            r6 = 1
            goto L_0x005d
        L_0x0267:
            java.lang.String r0 = "</div></body></html>"
            r1.append(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "<html><head><style>"
            r0.append(r2)
            java.util.Set r2 = r3.keySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x027e:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x02a4
            java.lang.Object r4 = r2.next()
            java.lang.String r4 = (java.lang.String) r4
            java.lang.StringBuilder r5 = r0.append(r4)
            java.lang.String r6 = "{"
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.Object r4 = r3.get(r4)
            java.lang.String r4 = (java.lang.String) r4
            java.lang.StringBuilder r4 = r5.append(r4)
            java.lang.String r5 = "}"
            r4.append(r5)
            goto L_0x027e
        L_0x02a4:
            java.lang.String r2 = "</style></head>"
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r2 = 0
            r1.insert(r2, r0)
            r0 = r26
            android.webkit.WebView r2 = r0.webView
            java.lang.String r1 = r1.toString()
            java.nio.charset.Charset r3 = com.dreamers.exoplayercore.repack.aC.c
            byte[] r1 = r1.getBytes(r3)
            r3 = 1
            java.lang.String r1 = android.util.Base64.encodeToString(r1, r3)
            java.lang.String r3 = "text/html"
            java.lang.String r4 = "base64"
            r2.loadData(r1, r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.WebViewSubtitleOutput.updateWebView():void");
    }

    public final void destroy() {
        this.webView.destroy();
    }

    /* access modifiers changed from: protected */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z && !this.textCues.isEmpty()) {
            updateWebView();
        }
    }

    public final void update(List list, CaptionStyleCompat captionStyleCompat, float f, int i, float f2) {
        this.style = captionStyleCompat;
        this.defaultTextSize = f;
        this.defaultTextSizeType = i;
        this.bottomPaddingFraction = f2;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            Cue cue = (Cue) list.get(i2);
            if (cue.e != null) {
                arrayList.add(cue);
            } else {
                arrayList2.add(cue);
            }
        }
        if (!this.textCues.isEmpty() || !arrayList2.isEmpty()) {
            this.textCues = arrayList2;
            updateWebView();
        }
        this.canvasSubtitleOutput.update(arrayList, captionStyleCompat, f, i, f2);
        invalidate();
    }
}
