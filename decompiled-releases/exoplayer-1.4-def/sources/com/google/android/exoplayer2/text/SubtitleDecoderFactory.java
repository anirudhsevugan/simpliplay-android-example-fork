package com.google.android.exoplayer2.text;

import com.google.android.exoplayer2.Format;

public interface SubtitleDecoderFactory {
    public static final SubtitleDecoderFactory a = new SubtitleDecoderFactory() {
        public final boolean a(Format format) {
            String str = format.l;
            return "text/vtt".equals(str) || "text/x-ssa".equals(str) || "application/ttml+xml".equals(str) || "application/x-mp4-vtt".equals(str) || "application/x-subrip".equals(str) || "application/x-quicktime-tx3g".equals(str) || "application/cea-608".equals(str) || "application/x-mp4-cea-608".equals(str) || "application/cea-708".equals(str) || "application/dvbsubs".equals(str) || "application/pgs".equals(str);
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.google.android.exoplayer2.text.SubtitleDecoder b(com.google.android.exoplayer2.Format r4) {
            /*
                r3 = this;
                java.lang.String r0 = r4.l
                if (r0 == 0) goto L_0x00cc
                int r1 = r0.hashCode()
                switch(r1) {
                    case -1351681404: goto L_0x0074;
                    case -1248334819: goto L_0x0069;
                    case -1026075066: goto L_0x005f;
                    case -1004728940: goto L_0x0055;
                    case 691401887: goto L_0x004b;
                    case 822864842: goto L_0x0041;
                    case 930165504: goto L_0x0037;
                    case 1566015601: goto L_0x002d;
                    case 1566016562: goto L_0x0022;
                    case 1668750253: goto L_0x0018;
                    case 1693976202: goto L_0x000d;
                    default: goto L_0x000b;
                }
            L_0x000b:
                goto L_0x007f
            L_0x000d:
                java.lang.String r1 = "application/ttml+xml"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x007f
                r1 = 3
                goto L_0x0080
            L_0x0018:
                java.lang.String r1 = "application/x-subrip"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x007f
                r1 = 4
                goto L_0x0080
            L_0x0022:
                java.lang.String r1 = "application/cea-708"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x007f
                r1 = 8
                goto L_0x0080
            L_0x002d:
                java.lang.String r1 = "application/cea-608"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x007f
                r1 = 6
                goto L_0x0080
            L_0x0037:
                java.lang.String r1 = "application/x-mp4-cea-608"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x007f
                r1 = 7
                goto L_0x0080
            L_0x0041:
                java.lang.String r1 = "text/x-ssa"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x007f
                r1 = 1
                goto L_0x0080
            L_0x004b:
                java.lang.String r1 = "application/x-quicktime-tx3g"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x007f
                r1 = 5
                goto L_0x0080
            L_0x0055:
                java.lang.String r1 = "text/vtt"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x007f
                r1 = 0
                goto L_0x0080
            L_0x005f:
                java.lang.String r1 = "application/x-mp4-vtt"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x007f
                r1 = 2
                goto L_0x0080
            L_0x0069:
                java.lang.String r1 = "application/pgs"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x007f
                r1 = 10
                goto L_0x0080
            L_0x0074:
                java.lang.String r1 = "application/dvbsubs"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x007f
                r1 = 9
                goto L_0x0080
            L_0x007f:
                r1 = -1
            L_0x0080:
                switch(r1) {
                    case 0: goto L_0x00c6;
                    case 1: goto L_0x00be;
                    case 2: goto L_0x00b8;
                    case 3: goto L_0x00b2;
                    case 4: goto L_0x00ac;
                    case 5: goto L_0x00a4;
                    case 6: goto L_0x009c;
                    case 7: goto L_0x009c;
                    case 8: goto L_0x0092;
                    case 9: goto L_0x008a;
                    case 10: goto L_0x0084;
                    default: goto L_0x0083;
                }
            L_0x0083:
                goto L_0x00cc
            L_0x0084:
                com.google.android.exoplayer2.text.pgs.PgsDecoder r4 = new com.google.android.exoplayer2.text.pgs.PgsDecoder
                r4.<init>()
                return r4
            L_0x008a:
                com.google.android.exoplayer2.text.dvb.DvbDecoder r0 = new com.google.android.exoplayer2.text.dvb.DvbDecoder
                java.util.List r4 = r4.n
                r0.<init>(r4)
                return r0
            L_0x0092:
                com.google.android.exoplayer2.text.cea.Cea708Decoder r0 = new com.google.android.exoplayer2.text.cea.Cea708Decoder
                int r1 = r4.B
                java.util.List r4 = r4.n
                r0.<init>(r1, r4)
                return r0
            L_0x009c:
                com.google.android.exoplayer2.text.cea.Cea608Decoder r1 = new com.google.android.exoplayer2.text.cea.Cea608Decoder
                int r4 = r4.B
                r1.<init>(r0, r4)
                return r1
            L_0x00a4:
                com.google.android.exoplayer2.text.tx3g.Tx3gDecoder r0 = new com.google.android.exoplayer2.text.tx3g.Tx3gDecoder
                java.util.List r4 = r4.n
                r0.<init>(r4)
                return r0
            L_0x00ac:
                com.google.android.exoplayer2.text.subrip.SubripDecoder r4 = new com.google.android.exoplayer2.text.subrip.SubripDecoder
                r4.<init>()
                return r4
            L_0x00b2:
                com.google.android.exoplayer2.text.ttml.TtmlDecoder r4 = new com.google.android.exoplayer2.text.ttml.TtmlDecoder
                r4.<init>()
                return r4
            L_0x00b8:
                com.google.android.exoplayer2.text.webvtt.Mp4WebvttDecoder r4 = new com.google.android.exoplayer2.text.webvtt.Mp4WebvttDecoder
                r4.<init>()
                return r4
            L_0x00be:
                com.google.android.exoplayer2.text.ssa.SsaDecoder r0 = new com.google.android.exoplayer2.text.ssa.SsaDecoder
                java.util.List r4 = r4.n
                r0.<init>(r4)
                return r0
            L_0x00c6:
                com.google.android.exoplayer2.text.webvtt.WebvttDecoder r4 = new com.google.android.exoplayer2.text.webvtt.WebvttDecoder
                r4.<init>()
                return r4
            L_0x00cc:
                java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
                java.lang.String r0 = java.lang.String.valueOf(r0)
                int r1 = r0.length()
                java.lang.String r2 = "Attempted to create decoder for unsupported MIME type: "
                if (r1 == 0) goto L_0x00df
                java.lang.String r0 = r2.concat(r0)
                goto L_0x00e4
            L_0x00df:
                java.lang.String r0 = new java.lang.String
                r0.<init>(r2)
            L_0x00e4:
                r4.<init>(r0)
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.SubtitleDecoderFactory.AnonymousClass1.b(com.google.android.exoplayer2.Format):com.google.android.exoplayer2.text.SubtitleDecoder");
        }
    };

    boolean a(Format format);

    SubtitleDecoder b(Format format);
}
